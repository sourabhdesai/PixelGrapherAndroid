/* 
 * Copyright 2012 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.PixelBin.CodeGeneration;

import java.io.File;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.example.PixelBin.Outputer;
import com.example.PixelBin.RGBUtilities;
import com.google.dexmaker.Code;
import com.google.dexmaker.DexMaker;
import com.google.dexmaker.Local;
import com.google.dexmaker.MethodId;
import com.google.dexmaker.TypeId;
/*
import com.google.imageplayground.drawing.DrawCommand;
import com.google.imageplayground.drawing.DrawOperation;
import com.google.imageplayground.util.CameraUtils;
import com.google.imageplayground.util.FaceFinder;
*/
public class DexImageScript {
    
    static enum ScriptType {
        GRAYSCALE(TypeId.INT, "getOutputColorForGrayscaleInput", Arrays.asList("y", "row", "col", "width", "height")),
        COLOR(TypeId.INT, "getOutputColorForColorInput", Arrays.asList("y", "r", "g", "b", "row", "col", "width", "height")),
        MANUAL(TypeId.VOID, "createOutputBitmap", Arrays.asList("width", "height"));
        
        public final TypeId returnType;
        public final String methodName;
        public final List<String> arguments;
        
        private ScriptType(TypeId returnType, String methodName, List<String> arguments) {
            this.returnType = returnType;
            this.methodName = methodName;
            this.arguments = arguments;
        }
    }

    ScriptType scriptType;
    int frameNumber = 0;
	Random random = new Random();
	
	ScriptType getScriptType() {
	    return scriptType;
	}
	void setScriptType(ScriptType value) {
	    scriptType = value;
	}
	
	// one of the following three methods will get created from the user-entered script
	public int getOutputColorForGrayscaleInput(int y, int row, int col, int width, int height) {
		return 0;
	}
	
	public int getOutputColorForColorInput(int y, int r, int g, int b, int row, int col, int width, int height) {
		return 0;
	}
	
	public void createOutputBitmap(int width, int height) {
	    
	}
	
	static String CLASS_NAME = "com/example/PixelBin/CodeGeneration/Gen1";  //Updated this part of the code for my projects file structure. this should fix the verify error.
	
	static TypeId generateClass(DexMaker dexMaker) {
		TypeId<?> imageScript = TypeId.get("L" + CLASS_NAME + ";");
		dexMaker.declare(imageScript, "ImageScript.generated", Modifier.PUBLIC, TypeId.get(DexImageScript.class));
		// empty constructor
		MethodId constructor = imageScript.getConstructor();
		Code constructorCode = dexMaker.declare(constructor, Modifier.PUBLIC);
		Local thisRef = constructorCode.getThis(imageScript);
		MethodId parentConstructor = TypeId.get(DexImageScript.class).getConstructor();
		constructorCode.invokeDirect(parentConstructor, null, thisRef);
		constructorCode.returnVoid();
		
		return imageScript;
	}
	
	static DexImageScript loadGeneratedClass(Context context, DexMaker dexMaker) throws Exception {
		File outputDir = context.getCacheDir();
		// TODO: clean up temporary dex files
		ClassLoader loader = dexMaker.generateAndLoad(DexImageScript.class.getClassLoader(), outputDir);
		Class<?> imageScriptClass = loader.loadClass(CLASS_NAME);
		return (DexImageScript)imageScriptClass.newInstance();
	}

	public static DexImageScript createScript(Context context, String userScript) {
		try {
			DexMaker dexMaker = new DexMaker();
			// build list of instructions to see what variables are referenced
			userScript = userScript.trim() + "\n";
			DexCodeGenerator.InstructionContext instContext = DexCodeGenerator.createInstructionList(userScript);
			
			ScriptType scriptType = null;
			// if no return statement, use createOutputImage method
			boolean hasReturn = false;
			for(DexCodeGenerator.Instruction inst : instContext.instructions) {
			    if (inst instanceof DexCodeGenerator.ReturnInstruction) {
			        hasReturn = true;
			        break;
			    }
			}
			if (hasReturn) {
	            // use color arguments if user's code requires color-specific args
			    scriptType = ScriptType.GRAYSCALE;
	            for(String localName : instContext.locals) {
	                if (ScriptType.COLOR.arguments.contains(localName)) {
	                    scriptType = ScriptType.COLOR;
	                    break;
	                }
	            }
			}
			else {
			    scriptType = ScriptType.MANUAL;
			    // HACK: void method needs a returnVoid instruction
			    instContext.instructions.add(new DexCodeGenerator.ReturnVoidInstruction());
			}
			TypeId[] parameterTypes = new TypeId[scriptType.arguments.size()];
			Arrays.fill(parameterTypes, TypeId.INT);
			
			TypeId<?> imageScriptType = generateClass(dexMaker);
			MethodId generatedMethod = imageScriptType.getMethod(scriptType.returnType, scriptType.methodName, parameterTypes);
			Code code = dexMaker.declare(generatedMethod,  Modifier.PUBLIC);
			
			Map<String, Local> localMap = new HashMap<String, Local>();
			for(int i=0; i<scriptType.arguments.size(); i++) {
				localMap.put(scriptType.arguments.get(i), code.getParameter(i, TypeId.INT));
			}
			
			DexCodeGenerator.generateMethodCode(code, localMap, imageScriptType, instContext);
			
			DexImageScript script = loadGeneratedClass(context, dexMaker);
			script.setScriptType(scriptType);
			return script;
		}
		catch(Throwable ex) {
			Log.e("DexImageScript", "Failed to create", ex);
			return null;
		}
	}

	Bitmap outputBitmap = null;
	int[] outputPixelBuffer;
	
	byte[] imageData;
	int imageWidth;
	int imageHeight;
	
//	FaceFinder faceFinder = new FaceFinder();
	int numFaces;
	
	List<Worker> workers;
	ExecutorService workerExecutor;
	
	List<DrawCommand> drawCommands = new ArrayList<DrawCommand>();
	
	void createBuffers(int width, int height) {
		if (outputPixelBuffer==null || outputPixelBuffer.length!=width*height) {
			outputPixelBuffer = new int[width*height];
		}
		if (outputBitmap==null || outputBitmap.getWidth()!=width || outputBitmap.getHeight()!=height) {
			outputBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
		}
	}
	
	public Bitmap getBitmapForImageData(byte[] imageData, int width, int height) {
		createBuffers(width, height);
		
		this.frameNumber++;
		// semi-hack: copy data to instance variables so script_ methods below can access them
		this.imageData = imageData;
		this.imageWidth = width;
		this.imageHeight = height;
		this.drawCommands.clear();
		this.numFaces = -1;
		
		if (this.getScriptType()==ScriptType.MANUAL) {
		    Arrays.fill(outputPixelBuffer, 255<<24); // solid black
		    createOutputBitmap(width, height);
		}
		else {
	        // create workers if needed and run them
	        if (workers==null) {
	            workers = new ArrayList<Worker>();
	            int numThreads = Runtime.getRuntime().availableProcessors();
	            for(int i=0; i<numThreads; i++) {
	                workers.add(new Worker());
	            }
	            workerExecutor = Executors.newFixedThreadPool(workers.size());
	        }
	        int nworkers = workers.size();
	        for(int i=0; i<workers.size(); i++) {
	            workers.get(i).setRowRange(i*height/nworkers, (i+1)*height/nworkers);
	        }
	        try {
	            workerExecutor.invokeAll((Collection)workers);
	        }
	        catch(InterruptedException ignored) {}
		}

		this.imageData = null;
		outputBitmap.setPixels(outputPixelBuffer, 0, imageWidth, 0, 0, imageWidth, imageHeight);
		if (drawCommands.size() > 0) {
		    Canvas canvas = new Canvas(outputBitmap);
		    Paint paint = new Paint();
		    paint.setARGB(255, 255, 255, 255);
		    for(DrawCommand command : drawCommands) {
		        command.execute(canvas, paint);
		    }
		}
		return outputBitmap;
	}
	
	// worker objects which run in separate threads each computing a subset of the output rows
    class Worker implements Callable<Long> {
        int rowStart;
        int rowEnd;
        
        public void setRowRange(int start, int end) {
            rowStart = start;
            rowEnd = end;
        }
        
        public Long call() {
            // interface wants us to return something, might as well collect timing data
            long t1 = System.nanoTime();
            computePixels(rowStart, rowEnd);
            return System.nanoTime() - t1;
        }
    }
	    
	void computePixels(int rowStart, int rowEnd) {
        if (this.getScriptType()==ScriptType.COLOR) {
            int[] rgb = new int[3];
            int yindex = rowStart * imageWidth;
            int uvstart = imageWidth * imageHeight;
            for(int row=rowStart; row<rowEnd; row++) {
                // VU pixels only for every other row and column
                int uvbase = uvstart + (row/2) * imageWidth;
                for(int col=0; col<imageWidth; col++) {
                    // one VU pair of values for every two pixels, round to 2 and take it and the next byte
                    //imageData is the YUV byte[]
                    int uvindex = uvbase + (col & ~1);
                    CameraUtils.yuvToRgb(imageData[yindex], imageData[uvindex+1], imageData[uvindex], rgb);
                    outputPixelBuffer[yindex] = getOutputColorForColorInput(255,0,0,0,row,col,imageWidth,imageHeight);  //My modification
                    /*
                    outputPixelBuffer[yindex] = getOutputColorForColorInput(0xff & imageData[yindex],
                            rgb[0], rgb[1], rgb[2], row, col, imageWidth, imageHeight);
                    */
                    yindex++;
                }
            }
        }
        else {
            int index = rowStart * imageWidth;
            for(int row=rowStart; row<rowEnd; row++) {
                for(int col=0; col<imageWidth; col++) {
                    /*
                    outputPixelBuffer[index] = getOutputColorForGrayscaleInput(0xff & imageData[index], row, col, 
                            imageWidth, imageHeight);
                    */
                    outputPixelBuffer[index] = getOutputColorForGrayscaleInput(255, row, col,
                            imageWidth, imageHeight);
                    index++;
                }
            }
        }
	}
	
	// Methods beginning with script_ can be called from scripts. All arguments and return values must be int.
	public int script_max(int val1, int val2) {
		return (val1 > val2) ? val1 : val2;
	}
	
	public int script_min(int val1, int val2) {
		return (val1 < val2) ? val1 : val2;
	}
	
	public int script_clamp(int val, int min, int max) {
		if (val < min) return min;
		if (val > max) return max;
		return val;
	}
	
	public int script_abs(int val) {
		return (val>=0) ? val : -val;
	}
	
	public int script_ifeq(int val, int comparison, int trueval, int falseval) {
		return (val==comparison) ? trueval : falseval;
	}
	
	public int script_ifgt(int val, int comparison, int trueval, int falseval) {
		return (val>comparison) ? trueval : falseval;
	}
    
	public int script_random(int maxRandomValue) {
		return random.nextInt(maxRandomValue);
	}
	
	public int script_gray(int gray) {
		if (gray<0) gray = 0;
		if (gray>255) gray = 255;
		return 0xff000000 | (gray << 16) | (gray << 8) | gray;
	}
	
	public int script_rgb(int r, int g, int b) {
		if (r<0) r = 0;
		if (r>255) r = 255;
		if (g<0) g = 0;
		if (g>255) g = 255;
		if (b<0) b = 0;
		if (b>255) b = 255;
		return Color.rgb(r, g, b);
	}

    
    public int script_framenumber() {
        return frameNumber;
    }
    
    // timing functions
    public int script_time() {
        return (int)System.currentTimeMillis();
    }
    
    public int script_getyear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }
    public int script_getmonth() {
        return 1 + Calendar.getInstance().get(Calendar.MONTH);
    }
    public int script_getday() {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }
    public int script_gethour() {
        return Calendar.getInstance().get(Calendar.HOUR);
    }
    public int script_getminute() {
        return Calendar.getInstance().get(Calendar.MINUTE);
    }
    public int script_getsecond() {
        return Calendar.getInstance().get(Calendar.SECOND);
    }
    
    // experimental storage APIs
    Map<Integer, Integer> scriptIntStorage = new HashMap<Integer, Integer>();
    Map<Integer, List<Integer>> scriptIntListStorage = new HashMap<Integer, List<Integer>>();
    
    public int script_putint(int key, int value) {
        scriptIntStorage.put(key, value);
        return value;
    }
    
    public int script_getint(int key) {
        Integer val = scriptIntStorage.get(key);
        if (val==null) val = 0;
        return val;
    }
    
    public int script_listclear(int key) {
        scriptIntListStorage.remove(key);
        return 0;
    }
    
    public int script_listsize(int key) {
        List<Integer> list = scriptIntListStorage.get(key);
        return (list!=null) ? list.size() : 0;
    }
    
    public int script_listread(int key, int index) {
        List<Integer> list = scriptIntListStorage.get(key);
        if (list==null || index<0 || index>=list.size()) return 0;
        return list.get(index);
    }
    
    public int script_listpush(int key, int value) {
        List<Integer> list = scriptIntListStorage.get(key);
        if (list==null) scriptIntListStorage.put(key, list=new ArrayList<Integer>());
        list.add(value);
        return list.size();
    }
    
    public int script_listpop(int key) {
        List<Integer> list = scriptIntListStorage.get(key);
        if (list==null) return 0;
        int size = list.size();
        if (size==0) return 0;
        return list.remove(size-1);
    }
    
    // math functions
    // trig functions take integer arguments and scale by pi/(Integer.MAX_VALUE+1),
    // this allows overflow to work correctly; MAX_VALUE scales to pi, MAX_VALUE+1=MIN_VALUE scales to -pi.
    
    /** Returns Math.sqrt(x*x + y*y) rounded to the nearest int.
     */
    public int script_hypot(int x, int y) {
        return (int)Math.round(Math.sqrt(x*x + y*y));
    }

    public int script_sin(int x, int scale)   {
        return (int) (scale*Math.sin(x));
    }

    public int script_cos(int x, int scale)    {
        return (int) (scale*Math.cos(x));
    }
    
    public int script_tan(int x, int scale)    {
        return (int) (scale*Math.tan(x));
    }

    public int script_asin(int x, int scale)   {
        return (int) (scale*Math.asin(x));
    }

    public int script_acos(int x, int scale)   {
        return (int) (scale*Math.acos(x));
    }

    public int script_atan(int x, int scale)   {
        return (int) (scale*Math.atan(x));
    }

    public int script_sqrt(int x)   {
        return (int) Math.sqrt(x);
    }

    public int script_cbrt(int x)   {
        return (int) Math.cbrt(x);
    }

    public int script_mapW(int x,int colorStart,int colorEnd)   {
        return script_map(x,0,this.imageWidth,colorStart,colorEnd);
    }

    public int script_mapH(int y,int colorStart,int colorEnd)   {
        return script_map(y,0,this.imageHeight,colorStart,colorEnd);
    }

    public int script_map(int x, int in_min, int in_max, int out_min, int out_max)	{
        return Outputer.mapper(x,in_min,in_max,out_min,out_max);
    }

    public int script_xor(int x, int y)    {
        return x^y;
    }

    public int script_pow(int base, int exponent) {
        return (int) Math.pow(base,exponent);
    }
    /*
    // face detection functions
    int computeFaces() {
        numFaces = faceFinder.findFacesInCameraData(this.imageData, this.imageWidth, this.imageHeight);
        return numFaces;
    }

    public int script_numfaces() {
        if (this.numFaces==-1) this.numFaces = computeFaces();
        return this.numFaces;
    }
    
    public int script_faceconfidence(int index) {
        return faceFinder.getConfidence(index);
    }
    
    public int script_facex(int index) {
        return faceFinder.getMidpointX(index);
    }
    
    public int script_facey(int index) {
        return faceFinder.getMidpointY(index);
    }
    
    public int script_facedist(int index) {
        return faceFinder.getEyesDistance(index);
    }
    */
    // drawing functions
    int addDrawCommand(DrawOperation operation, Integer... args) {
        drawCommands.add(new DrawCommand(operation, args));
        return 0;
    }
    
    public int script_setpaint(int r, int g, int b) {
        return addDrawCommand(DrawOperation.SET_PAINT, r, g, b, 255);
    }

    public int script_setpaint(int r, int g, int b, int a) {
        return addDrawCommand(DrawOperation.SET_PAINT, r, g, b, a);
    }

    public int script_drawline(int x1, int y1, int x2, int y2) {
        return addDrawCommand(DrawOperation.LINE, x1, y1, x2, y2);
    }
    
    public int script_fillrect(int x1, int y1, int x2, int y2) {
        return addDrawCommand(DrawOperation.FILL_RECT, x1, y1, x2, y2);
    }
    
    public int script_framerect(int x1, int y1, int x2, int y2) {
        return addDrawCommand(DrawOperation.FRAME_RECT, x1, y1, x2, y2);
    }
    
    public int script_fillcircle(int cx, int cy, int radius) {
        return addDrawCommand(DrawOperation.FILL_CIRCLE, cx, cy, radius);
    }

    public int script_framecircle(int cx, int cy, int radius) {
        return addDrawCommand(DrawOperation.FRAME_CIRCLE, cx, cy, radius);
    }
    
    public int script_fillsquare(int midx, int midy, int radius) {
        return addDrawCommand(DrawOperation.FILL_RECT, midx-radius, midy-radius, midx+radius, midy+radius);
    }

    public int script_framesquare(int midx, int midy, int radius) {
        return addDrawCommand(DrawOperation.FRAME_RECT, midx-radius, midy-radius, midx+radius, midy+radius);
    }

    public int script_drawint(int x, int y, int value) {
        return addDrawCommand(DrawOperation.DRAW_NUMBER, x, y, value);
    }
    
    public int script_drawchar(int x, int y, int value) {
        return addDrawCommand(DrawOperation.DRAW_CHAR, x, y, value);
    }
}

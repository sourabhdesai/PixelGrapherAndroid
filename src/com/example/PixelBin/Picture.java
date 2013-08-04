package com.example.PixelBin;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.ImageFormat;
import android.graphics.YuvImage;
import android.util.Log;
import com.example.PixelBin.CodeGeneration.DexCodeGenerator;
import com.example.PixelBin.CodeGeneration.DexImageScript;
import com.parse.ParseObject;

import java.lang.reflect.InvocationTargetException;

/**
 * Created with IntelliJ IDEA.
 * User: DESAI_628IL
 * Date: 6/30/13
 * Time: 1:44 PM
 * To change this template use File | Settings | File Templates.
 */

/*
Okay so using exp4j takes a ridiculous amount of time. Ill try to work things out with janino by integrating it with javas reflect api. It has a way to "invoke" methods by name from any given Class<?> object.
 */
public class Picture {
    private String formulaR;

    private String formulaG;

    private String formulaB;

    private int width;
    private int height;
    private String author;
    private DexImageScript imageScript;

    public Picture(String r, String g, String b,int width,int height) {
        this.formulaR = r;
        this.formulaG = g;
        this.formulaB = b;

        this.width = width;
        this.height = height;
    }

    public Picture(ParseObject pObject) {
        this.formulaR = pObject.getString("RFunction");
        this.formulaG = pObject.getString("GFunction");
        this.formulaB = pObject.getString("BFunction");
        this.width = pObject.getInt("Width");
        this.height = pObject.getInt("Height");
        this.author = pObject.getString("User");
    }

    public String getFormulaR() {
        return formulaR;
    }

    public String getFormulaG() {
        return formulaG;
    }

    public String getFormulaB() {
        return formulaB;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void evaluateScripts(Context context) throws Throwable{
        String builtString =
                "x = col \n"+
                "y = row \n"+
                "R="+this.formulaR+"\n"+
                "G="+this.formulaG+"\n"+
                "B="+this.formulaB+"\n"+
                "return rgb(R,G,B)";
        this.imageScript = DexImageScript.createScript(context,builtString);
    }

    public Bitmap generateBitmap() throws ArithmeticException, VerifyError, NoSuchMethodError{
    //    byte[] emptyByteArray = new byte[this.width * this.height * 3 ]; //tweak this ---YUV format has 3 bits per pixel

    //    (new YuvImage(emptyByteArray, ImageFormat.NV21,this.width,this.height,null)).getYuvData()     //how to create a YuvImage object
        if(this.imageScript==null)  return null;
        Bitmap img = Bitmap.createBitmap(this.width,this.height,Bitmap.Config.ARGB_8888);
        for(int x =0;x<this.width;x++)  {
            for(int y =0;y<this.height;y++) {
                int Y = this.height-1-y;
                int color = this.imageScript.getOutputColorForColorInput(255, 0, 0, 0, Y, x, this.width, this.height);
                img.setPixel(x,y,color);
            }
        }
        return img;
    }

    /**
     *
     * @param Width
     * @param Height
     * @param context
     * @return picture resized to given dimensions
     */
    public Bitmap generateSnapshot(int Width,int Height,Context context)    {
        if(this.imageScript==null) {
            try {
                this.evaluateScripts(context);
            } catch (Throwable throwable) {
                throwable.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                return null;
            }
        }
        Bitmap img = Bitmap.createBitmap(Width,Height, Bitmap.Config.ARGB_8888);
        for(int x=0;x<Width;x++)    {
            for(int y=0;y<Height;y++)   {
                int Y = this.height-1-y;
                int mappedX =Math.round(x/Width)*this.width;
                int mappedY = Math.round(Y/Height)*this.height;
                img.setPixel(mappedX,mappedY,this.imageScript.getOutputColorForColorInput(255, 0, 0, 0, mappedY, mappedX, this.width, this.height));
            }
        }
        return img;
    }

    /**
     * Must call evaluateScripts() before using this!
     * @param x
     * @param y
     * @return value at position (x,y)
     */
    public int getOutputAt(int x, int y)    {
        return this.imageScript.getOutputColorForColorInput(255,0,0,0,y,x,this.width,this.height);
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

}



package com.example.PixelBin;


import android.content.Context;
import android.graphics.Bitmap;
import com.example.PixelBin.CodeGeneration.DexCodeGenerator;
import com.example.PixelBin.CodeGeneration.DexImageScript;

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
    private DexImageScript imageScript;

    public Picture(String r, String g, String b,int width,int height) {
        this.formulaR = r;
        this.formulaG = g;
        this.formulaB = b;

        this.formulaR = this.formulaR.replaceAll("width",width+"");
        this.formulaR = this.formulaR.replaceAll("height",height+"");

        this.formulaG = this.formulaG.replaceAll("width",width+"");
        this.formulaG = this.formulaG.replaceAll("height",height+"");

        this.formulaB = this.formulaB.replaceAll("width",width+"");
        this.formulaB = this.formulaB.replaceAll("height",height+"");

        this.width = width;
        this.height = height;
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

    public Bitmap generateBitmap() {
        return generateBitmap(this);
    }

    public void evaluateScripts(Context context) throws Exception {
        String builtString = "x = col \n"+"y = row \n"+"return rgb("+this.formulaR+","+this.formulaG+","+this.formulaB+")";
        DexImageScript imageScript = DexImageScript.createScript(context,builtString);
    //    imageScript.createOutputBitmap(this.width,this.height);
        this.imageScript = imageScript;

    }

    private static Bitmap generateBitmap(Picture picture) {
        byte[] emptyByteArray = Functions_Fragment.bitmapToByteArray(Bitmap.createBitmap(picture.width,picture.height, Bitmap.Config.ARGB_8888));
        Bitmap img = picture.imageScript.getBitmapForImageData(emptyByteArray,picture.width,picture.height);
        return img;
    }

}

/*
import android.graphics.Bitmap;import com.example.PixelBin.RGBUtilities;public class JavaClass {public double getOutput(int x, int y)	{return 0;}public double getOutputR(int x, int y)	{return "+formulas[0]+";}public double getOutputG(int x, int y)	{return "+formulas[1]+";}public double getOutputB(int x,int y)	{return "+formulas[2]+";}public double getOutputA(int x,int y)	{return "+formulas[3]+";} public Bitmap quadrant1Graph(int width,int height)	{Bitmap newPic=Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888);for(int x=0; x<width; x++)	{for(int Y=height-1; Y>=0; Y--)	{int y=height-1-Y;int r=this.getOutputR(x,y);int g=this.getOutputG(x,y);int b=this.getOutputB(x, y);int a=this.getOutputA(x,y);if(r>255) r=255;if(g>255) g=255;if(b>255) b=255;if(a>255) a=255;int pixel=RGBUtilities.toRGBA(r,g,b,a);newPic.setPixel(x,y,pixel);}}return newPic;}}
*/
/*
function toInt(n){ return Math.round(Number(n)); };function genImageArr(width,height)	{var a = 255 << 24;var pic = new Array(width);for (var x = pic.length - 1; x >= 0; x--) {pic[x] = new Array(height);for (var y = height - 1; y >= 0; y--) {pic[x][y] = (( (a << 24) | (r(x,y) << 16)) | (g(x,y) << 8)) | b(x,y);};};return pic;}
 */

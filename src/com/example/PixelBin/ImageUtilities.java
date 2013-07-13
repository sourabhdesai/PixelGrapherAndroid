package com.example.PixelBin;//UIUC CS125 SPRING 2013 MP. File: ImageUtilities.java, CS125 Project: Challenge4-Photoscoop, Version: 2013-02-22T16:29:58-0600.413735000
import android.graphics.Bitmap;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/** A set of general purpose image utility methods.
 * These methods encapsulate the tricky code to interact with the java virtual machine.
 * You do not need to modify this file.
 * @author angrave
 *
 */
public class ImageUtilities   {

    /**
     * takes an int[][] of pixels a returns a corresponding bitmap of it
     * @param pixels
     * @return  Bitmap image
     */
	public static Bitmap setRGBPixels(int[][] pixels) {
		// assumes width >0
		int width = pixels.length, height = pixels[0].length;
        Bitmap target =Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++)
				target.setPixel(i, j, pixels[i][j]);
		return target;
	}

}

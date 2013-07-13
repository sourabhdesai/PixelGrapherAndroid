package com.example.PixelBin;//UIUC CS125 SPRING 2013 MP. File: RGBUtilities.java, CS125 Project: Challenge4-Photoscoop, Version: 2013-02-22T16:29:58-0600.413735000
/* Manipulates RGB values
 * 
 * Todo: Put your netid (i.e. username) in the line below
 * 
 * @author svdesai2
 */

public class RGBUtilities {

/**
 * Extracts the red component (0..255)
 * Hint: see ch12.1.2 Working With Pixels 
 * http://math.hws.edu/javanotes/c12/s1.html#GUI2.1.2
 * 
 * ... also see the notes in READ-ME-FIRST
 * 
 * @param rgb the encoded color int
 * @return the red component (0..255)
 */
	private static int shiftedFullAlpha=255 << 24;
	
	public static int toAlpha(int rgb)	{
		int a= (rgb >>> 24) & 0xFF;
		return a;
	}

	public static int toRed(int rgb) {
		int r = (rgb >> 16) & 0xFF;
		return r; 
	}

	public static int toGreen(int rgb) {
		int g = (rgb >> 8) & 0xFF;
		return g; 
	}

	public static int toBlue(int rgb) {
		int b = rgb & 0xFF;
		return b; 
	}
	

	/**
	 * 
	 * @param r the red component (0..255)
	 * @param g the green component (0..255)
	 * @param b the blue component (0..255)
	 * @return a single integer representation the rgb color (8 bits per component) rrggbb
	 */
	static int toRGB(int r, int g, int b) {
		/* Suppose that rgb is an int that encodes a color.
		   To get separate red, green, and blue color components: *;*/
		   
		
		
		   
		/* Suppose that red, green, and blue are color components in
		   the range 0 to 255.  To combine them into a single int: */
		   
		//int rgb = ((r << 16) | (g << 8)) | b;
		
		int rgb=(( shiftedFullAlpha | (r << 16)) | (g << 8)) | b;
		
		return rgb; 
	}
	
	public static int toRGBA(int r,int g, int b, int a)	{
		int rgba = (( (a << 24) | (r << 16)) | (g << 8)) | b;
		
		return rgba; 
	}

}

package com.example.PixelBin;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;



public class PixelPowerGrapher {

	/**
	 * @param args
	 */
	public static int height=1000;
	public static int width=1000;
	public static boolean needNewObj=false;
	//The following are integer arrays for common picture dimensions. First index indicates picture width, second index indicates picture height
	public static String[] dimensionList={"Screen Size","Facebook Cover Photo","Full HD","4K","iPad 3rd Generation","iPhone 4S","Select Your Own Dimensions"};
	public static int[] screenSize=new int[2];
	private static int[] fbCoverPhoto={851,315};//Cover photos are 851 pixels wide and 315 pixels tall.
	private static int[] fullHD={1920,1080};//1920 by 1080
	private static int[] fourK={4096,2304};//4096 by 2304
	private static int[] iPad3rdGen={2048,1536};//2048 by 1536
	private static int[] iPhone4S={640,960};//960 by 640
	public static int[][] dimensions={screenSize,fbCoverPhoto,fullHD,fourK,iPad3rdGen,iPhone4S};
    public static BitmapDrawable mainBackground;
    public static BitmapDrawable actionbarBackground;
	
	public static Picture currentPicture =null;

    public static Bitmap createdPicture = null;
	

	
	public static int[][] coordinateGraph() {
		int[][] newPic = new int[width][height];
		for(int X=width/2; X<height; X++)	{ //X: Starts from 0 coordinate, goes to MaxX coordinate
			for(int Y=height/2; Y<height; Y++)	{	//Y: Starts from 0 coordinate, goes to MaxY coordinate				
				for(int quadrant=1;quadrant<=4;quadrant++)	{
					int x=X-(width/2); //x: starts at 0, goes to half of width of dimension
					int y=Y-(width/2);//y: starts at 0, goes to half of height of dimension
					if(quadrant==2)	{
						x=-1*x;
					}
					if(quadrant==3)	{
						x=-1*x;
						y=-1*y;
					}
					if(quadrant==4)	{
						y=-1*y;
					}
				int r=getResultR(x,y);
				int g=getResultG(x,y);
				int b=getResultB(x, y);
				int a=getResultA(x,y);
				if(r>255) r=255;
				if(g>255) g=255;
				if(b>255) b=255;
				if(a>255) a=255;
					
				int pixel=RGBUtilities.toRGBA(r,g,b,a); 
				if(quadrant==1)	{
					newPic[X][height-Y]=pixel;	
				}
				else if(quadrant==2)	{
					newPic[X-(width/2)][height-Y]=pixel;
				} else if(quadrant==3)	{
					newPic[X-(width/2)][Y]=pixel;
				} else if(quadrant==4)	{
					newPic[X][Y]=pixel;
				}
				}
			}
		}
		return newPic;
	}//END OF METHOD---------------------------------------------------------------------------------------------------------------
	
	
	public static Bitmap quadrant1Graph(int width,int height) {
		Bitmap newPic=Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888);
		for(int x=0; x<width; x++)	{ //X: starts from 0, goes to MaxX coordinate
			for(int Y=height-1; Y>=0; Y--)	{	//Y: Starts from MaxY coordinate, goes to 0				
				int y=height-1-Y; //y: Starts from 0, goes to MaxY coordinate

				int r=getResultR(x,y);
				int g=getResultG(x,y);
				int b=getResultB(x, y);
				int a=getResultA(x,y);
				if(r>255) r=255;
				if(g>255) g=255;
				if(b>255) b=255;
				if(a>255) a=255;
				
				int pixel=RGBUtilities.toRGBA(r,g,b,a); 
				newPic.setPixel(x,y,pixel);
			}
		}
        PixelPowerGrapher.createdPicture = newPic;
		return newPic;
	}//END OF METHOD---------------------------------------------------------------------------------------------------------------
	
	public static int[][] quadrant1Graph2() {
		int[][] newPic=new int[width][height];
		for(int x=0; x<width; x++)	{ //X: starts from 0, goes to MaxX coordinate
			for(int Y=height-1; Y>=0; Y--)	{	//Y: Starts from MaxY coordinate, goes to 0				
				int y=height-1-Y; //y: Starts from 0, goes to MaxY coordinate

				int r=getResultR(x,y);
				int g=getResultG(x,y);
				int b=getResultB(x, y);
				int a=getResultA(x,y);
				if(r>255) r=255;
				if(g>255) g=255;
				if(b>255) b=255;
				if(a>255) a=255;
				
				int pixel=RGBUtilities.toRGBA(r,g,b,a); 
				newPic[x][Y]=pixel;
			}
		}
		return newPic;
	}//END OF METHOD---------------------------------------------------------------------------------------------------------------
	
	public static boolean picsEqual(int[][] pic1, int[][] pic2)	{
		boolean isEqual=true;
		if(pic1.length != pic2.length || pic1[0].length != pic2[0].length)	return false;
		int W=pic1.length;
		int H=pic2[0].length;
		for(int x=0; x<W; x++)	{
			for(int y=0; y<H; y++)	{
				if(pic1[x][y] != pic2[x][y]) isEqual=false;
			}
		}
		return isEqual;
	}//END OF METHOD---------------------------------------------------------------------------------------------------------------

	public static int[][] blur(int[][] sourceA, int blurLevel){//Can make this a lot more efficient															   
		if (sourceA==null) return null;						   //By making it color the edge and corner pixels first
		if(blurLevel==0) return sourceA;
		int[][] trgtPic= new int[sourceA.length][sourceA[0].length];
		int W= trgtPic.length;
		int H=trgtPic[0].length;
		int[][] source=sourceA;
		for(int level=0; level<blurLevel; level++)	{
		for(int x=0; x<W; x++)	{
			for(int y=0; y<H;y++)	{
				int xLeft=x-1;
				int xRight=x+1;
				int yUp=y-1;
				int yDown=y+1;
				boolean xLeftCHK= xLeft>=0;
				boolean xRightCHK= xRight<W;
				boolean yUpCHK= yUp>=0;
				boolean yDownCHK= yDown<H;
				int sumR=0;
				int sumG=0;
				int sumB=0;
				int sumA=0;
				int numPixels=0;
				if(xRightCHK)	{
					sumR+=RGBUtilities.toRed(source[xRight][y]);
					sumG+=RGBUtilities.toGreen(source[xRight][y]);
					sumB+=RGBUtilities.toBlue(source[xRight][y]);
					sumA+=RGBUtilities.toAlpha(source[xRight][y]);
					numPixels++;
					if(yUpCHK)	{
						sumR+=RGBUtilities.toRed(source[xRight][yUp]);
						sumG+=RGBUtilities.toGreen(source[xRight][yUp]);
						sumB+=RGBUtilities.toBlue(source[xRight][yUp]);
						sumA+=RGBUtilities.toAlpha(source[xRight][yUp]);
						numPixels++;
					}
					if(yDownCHK)	{
						sumR+=RGBUtilities.toRed(source[xRight][yDown]);
						sumG+=RGBUtilities.toGreen(source[xRight][yDown]);
						sumB+=RGBUtilities.toBlue(source[xRight][yDown]);
						sumA+=RGBUtilities.toAlpha(source[xRight][yDown]);
						numPixels++;
					}
				}
				if(xLeftCHK)	{
					sumR+=RGBUtilities.toRed(source[xLeft][y]);
					sumG+=RGBUtilities.toGreen(source[xLeft][y]);
					sumB+=RGBUtilities.toBlue(source[xLeft][y]);
					sumA+=RGBUtilities.toAlpha(source[xLeft][y]);
					numPixels++;
					if(yUpCHK)	{
						sumR+=RGBUtilities.toRed(source[xLeft][yUp]);
						sumG+=RGBUtilities.toGreen(source[xLeft][yUp]);
						sumB+=RGBUtilities.toBlue(source[xLeft][yUp]);
						sumA+=RGBUtilities.toAlpha(source[xLeft][yUp]);
						numPixels++;
					}
					if(yDownCHK)	{
						sumR+=RGBUtilities.toRed(source[xLeft][yDown]);
						sumG+=RGBUtilities.toGreen(source[xLeft][yDown]);
						sumB+=RGBUtilities.toBlue(source[xLeft][yDown]);
						sumA+=RGBUtilities.toAlpha(source[xLeft][yDown]);
						numPixels++;
					}
				}
				if(yUpCHK)	{
					sumR+=RGBUtilities.toRed(source[x][yUp]);
					sumG+=RGBUtilities.toGreen(source[x][yUp]);
					sumB+=RGBUtilities.toBlue(source[x][yUp]);
					sumA+=RGBUtilities.toAlpha(source[x][yUp]);
					numPixels++;
				}
				if(yDownCHK)	{
					sumR+=RGBUtilities.toRed(source[x][yDown]);
					sumG+=RGBUtilities.toGreen(source[x][yDown]);
					sumB+=RGBUtilities.toBlue(source[x][yDown]);
					sumA+=RGBUtilities.toAlpha(source[x][yDown]);
					numPixels++;
				}
			int avgR=sumR/numPixels;
			int avgG=sumG/numPixels;
			int avgB=sumB/numPixels;
			int avgA=sumA/numPixels;
			trgtPic[x][y]=RGBUtilities.toRGBA(avgR, avgG, avgB, avgA);
			}
		}
		source=PixelEffects.copy(trgtPic);
		}
	return trgtPic;
	}//END OF METHOD---------------------------------------------------------------------------------------------------------------

	public static int[][] toGrayScale(int[][] source)	{
		int[][] trgtPic=new int[source.length][source[0].length];
		int W=trgtPic.length;
		int H=trgtPic[0].length;
		for(int x=0;x<W; x++)	{
			for(int y=0; y<H; y++)	{
				int rVal=RGBUtilities.toRed(source[x][y]);
				int gVal=RGBUtilities.toGreen(source[x][y]);
				int bVal=RGBUtilities.toBlue(source[x][y]);
				int grayedPixel=(rVal+gVal+bVal)/3;
				trgtPic[x][y]=RGBUtilities.toRGB(grayedPixel, grayedPixel, grayedPixel);
			}
		}
		return trgtPic;
	}//END OF METHOD---------------------------------------------------------------------------------------------------------------
	
	public static int[][] picToRed(int[][] src)	{
		int W=src.length;
		int H=src[0].length;
		int[][] trgtPic=new int[W][H];
		for(int x=0; x<W; x++)	{
			for(int y=0; y<H; y++)	{
				trgtPic[x][y]=RGBUtilities.toRed(src[x][y]);
			}
		}
		return trgtPic;
	}//END OF METHOD---------------------------------------------------------------------------------------------------------------
	
	public static int[][] picToGreen(int[][] src)	{
		int W=src.length;
		int H=src[0].length;
		int[][] trgtPic=new int[W][H];
		for(int x=0; x<W; x++)	{
			for(int y=0; y<H; y++)	{
				trgtPic[x][y]=RGBUtilities.toGreen(src[x][y]);
			}
		}
		return trgtPic;
	}//END OF METHOD---------------------------------------------------------------------------------------------------------------
	
	public static int[][] picToBlue(int[][] src)	{
		int W=src.length;
		int H=src[0].length;
		int[][] trgtPic=new int[W][H];
		for(int x=0; x<W; x++)	{
			for(int y=0; y<H; y++)	{
				trgtPic[x][y]=RGBUtilities.toBlue(src[x][y]);
			}
		}
		return trgtPic;
	}//END OF METHOD---------------------------------------------------------------------------------------------------------------
	
	public static int[][] picsToRGB(int[][] rSrc,int[][] gSrc,int[][] bSrc)	{
		int W=rSrc.length;
		int H=rSrc[0].length;
		int[][] trgtPic=new int[W][H];
		for(int x=0; x<W; x++)	{
			for(int y=0; y<H; y++)	{
				trgtPic[x][y]=RGBUtilities.toRGB(rSrc[x][y],gSrc[x][y],bSrc[x][y]);
			}
		}
		return trgtPic;
	}//END OF METHOD---------------------------------------------------------------------------------------------------------------
	
	public static int[][] posterizeGrayScale(int[][] src)	{
		int H=src[0].length;
		int W=src.length;
		int lite= giveLightestAverage(src);
		int gray=giveMidAverage(src);
		int dark=giveDarkestAverage(src);
		int lowQuartile=RGBUtilities.toRGB((RGBUtilities.toRed(dark)+RGBUtilities.toRed(gray))/2,(RGBUtilities.toGreen(dark)+RGBUtilities.toGreen(gray))/2,(RGBUtilities.toBlue(dark)+RGBUtilities.toBlue(gray))/2);
		int uprQuartile=RGBUtilities.toRGB((RGBUtilities.toRed(lite)+RGBUtilities.toRed(gray))/2,(RGBUtilities.toGreen(lite)+RGBUtilities.toGreen(gray))/2,(RGBUtilities.toBlue(lite)+RGBUtilities.toBlue(gray))/2);
		int[][] tgtPic= new int[W][H];
		
		for(int x=0;x<W;x++)	{
			for(int y=0;y<H;y++)	{
				tgtPic[x][y]=giveClosestColor(src[x][y],lite,uprQuartile,gray,lowQuartile,dark);
			}
		}
		return tgtPic;
	}//END OF METHOD---------------------------------------------------------------------------------------------------------------
	
	private static int giveClosestColor(int inColor,int color1,int color2, int color3,int color4,int color5)	{
		int inColorCompsR=RGBUtilities.toRed(inColor);
		int inColorCompsG=RGBUtilities.toGreen(inColor);
		int inColorCompsB=RGBUtilities.toBlue(inColor);
		int closenessTo1=Math.abs(RGBUtilities.toRed(color1)-inColorCompsR)+Math.abs(RGBUtilities.toBlue(color1)-inColorCompsB)+Math.abs(RGBUtilities.toGreen(color1)-inColorCompsG);
		int closenessTo2=Math.abs(RGBUtilities.toRed(color2)-inColorCompsR)+Math.abs(RGBUtilities.toBlue(color2)-inColorCompsB)+Math.abs(RGBUtilities.toGreen(color2)-inColorCompsG);
		int closenessTo3=Math.abs(RGBUtilities.toRed(color3)-inColorCompsR)+Math.abs(RGBUtilities.toBlue(color3)-inColorCompsB)+Math.abs(RGBUtilities.toGreen(color3)-inColorCompsG);
		int closenessTo4=Math.abs(RGBUtilities.toRed(color4)-inColorCompsR)+Math.abs(RGBUtilities.toBlue(color4)-inColorCompsB)+Math.abs(RGBUtilities.toGreen(color4)-inColorCompsG);
		int closenessTo5=Math.abs(RGBUtilities.toRed(color5)-inColorCompsR)+Math.abs(RGBUtilities.toBlue(color5)-inColorCompsB)+Math.abs(RGBUtilities.toGreen(color5)-inColorCompsG);
		
		int closest=Math.min(closenessTo5, Math.min(closenessTo4,Math.min(closenessTo3, Math.min(closenessTo2,closenessTo1))));
		if(closenessTo1==closest) return color1;
		if(closenessTo2==closest) return color2;
		if(closenessTo3==closest) return color3;
		if(closenessTo4==closest) return color4;
		return color5;
	}//END OF METHOD---------------------------------------------------------------------------------------------------------------
	
	private static int giveMidAverage(int[][] src)	{
		int W=src.length;
		int H=src[0].length;
		int r=0;
		int g=0;
		int b=0;
		for(int x=0;x<W;x++)	{
			for(int y=0;y<H;y++)	{
				r+=RGBUtilities.toRed(src[x][y]);
				g+=RGBUtilities.toGreen(src[x][y]);
				b+=RGBUtilities.toBlue(src[x][y]);
			}
		}
		int numPixels=H*W;
		int avgComp=((r/numPixels)+(g/numPixels)+(b/numPixels))/3;
		return RGBUtilities.toRGB(avgComp, avgComp, avgComp);
	}//END OF METHOD---------------------------------------------------------------------------------------------------------------
	
	private static int giveDarkestAverage(int[][] src)	{
		int W=src.length;
		int H=src[0].length;
		int avgComp=0;
		for(int x=0;x<W;x++)	{
			for(int y=0;y<H;y++)	{
				int avgComp1=(RGBUtilities.toRed(src[x][y])+RGBUtilities.toGreen(src[x][y])+RGBUtilities.toBlue(src[x][y]))/3;
				if(avgComp>avgComp1) avgComp=avgComp1;
			}
		}
		return RGBUtilities.toRGB(avgComp, avgComp, avgComp);	
	}//END OF METHOD---------------------------------------------------------------------------------------------------------------
	
	private static int giveLightestAverage(int[][] src)	{
		int W=src.length;
		int H=src[0].length;
		int avgComp=0;
		for(int x=0;x<W;x++)	{
			for(int y=0;y<H;y++)	{
				int avgComp1=(RGBUtilities.toRed(src[x][y])+RGBUtilities.toGreen(src[x][y])+RGBUtilities.toBlue(src[x][y]))/3;
				if(avgComp<avgComp1) avgComp=avgComp1;
			}
		}
		return RGBUtilities.toRGB(avgComp, avgComp, avgComp);	
	}//END OF METHOD---------------------------------------------------------------------------------------------------------------
	
	public static int[][] pixelize(int pixelLength, int[][] originalPic)	{
		if(originalPic.length==0 || originalPic[0].length==0 || pixelLength<=0)	{
			return null;
		}
		if(pixelLength > originalPic.length || pixelLength > originalPic[0].length)	{
			pixelLength= Math.min(originalPic.length, originalPic[0].length);
		}
		int[][] trgtPic= new int[originalPic.length][originalPic[0].length];
		int gcd=Outputer.euclidianGCD(originalPic.length, originalPic[0].length);
		pixelLength=pixelLength-(gcd%pixelLength);
		pixelLength-=originalPic.length%pixelLength;
		pixelLength-=originalPic[0].length%pixelLength;
		
		for(int x=0; x<originalPic.length; x=x+pixelLength)	{
			for(int y=originalPic[0].length-1; y>0;y=y-pixelLength)	{
				int sumR=0;
				int sumG=0;
				int sumB=0; 
				int counter=0;
				for(int X=x;X<x+pixelLength;X++)	{
					for(int Y=y; Y> y-pixelLength; Y--)	{
						sumR+=RGBUtilities.toRed(originalPic[X][Y]);
						sumG+=RGBUtilities.toGreen(originalPic[X][Y]);
						sumB+=RGBUtilities.toBlue(originalPic[X][Y]);
						counter++;
					}
				}
				int averagePixelR=sumR/(counter);
				int averagePixelG=sumG/(counter);
				int averagePixelB=sumB/(counter);
				int averagePixel=RGBUtilities.toRGB(averagePixelR, averagePixelG, averagePixelB);
				for(int X=x;X<x+pixelLength;X++)	{
					for(int Y=y; Y> y-pixelLength; Y--)	{
						trgtPic[X][Y]=averagePixel;
					}
				}
			}
		}
		return trgtPic;
	}//END OF METHOD---------------------------------------------------------------------------------------------------------------

	public static int[][] fisheye(int[][] src)	{
		int W= src.length;
		int H= src[0].length;
		int xCenter= (W-1)/2;
		int yCenter= (H-1)/2;
		int[][] tgt = new int[W][H];
		for(int X=0; X<=xCenter; X++)	{ //X: Starts from 0 coordinate, goes to MaxX coordinate
			for(int Y=0; Y<=yCenter; Y++)	{	//Y: Starts from 0 coordinate, goes to MaxY coordinate				
				/*
				 * 	int tempX=(int)(x*magnitude)+x+xCenter;
				 *	int tempY=(int)(y*magnitude)+y+yCenter;
				 */
				int x1= X+xCenter; //lowercase x & y represent position on array, uppercase X & Y represent it on the positive number line
				int y1= yCenter-Y; 
				int x2= xCenter-X;
				int y2= yCenter-Y;
				int x3= xCenter-X;
				int y3= yCenter+Y;
				int x4= X+xCenter;
				int y4= Y+yCenter;
				
				int tempX, tempY, xNorm, yNorm;
				int magnitude= Outputer.mapper(Math.round((X^2)+(Y^2)),0,(xCenter^2)+(yCenter^2),10,200);
				if(magnitude != 0)	{
				xNorm= Math.round((long)X/(long)magnitude);
				yNorm= Math.round((long)Y/(long)magnitude);
				} else {
					xNorm=0;
					yNorm=0;
				}
				
				colorFromAtoB(x1,y1,X+(xNorm*magnitude)+xCenter,yCenter-(Y+(yNorm*magnitude)),tgt,src[x1][y1]); //Quadrant 1
				colorFromAtoB(x2,y2,xCenter-(X+(xNorm*magnitude)),yCenter-(Y+(yNorm*magnitude)),tgt,src[x2][y2]); //Quadrant 2
				colorFromAtoB(x3,y3,xCenter-(X+(xNorm*magnitude)),yCenter+(Y+(yNorm*magnitude)),tgt,src[x3][y3]); //Quadrant 3
				colorFromAtoB(x4,y4,X+(xNorm*magnitude)+xCenter,yCenter+(Y+(yNorm*magnitude)),tgt,src[x4][y4]); //Quadrant 4
			}
		}
		return tgt;
	}//END OF METHOD---------------------------------------------------------------------------------------------------------------

	
	private static void colorFromAtoB(int aX, int aY, int bX, int bY, int[][] pic, int color)	{
		if( aX >= 0 && aX< pic.length && aY >= 0 && aY< pic[0].length && bX >= 0 && bX< pic.length && bY >= 0 && bY< pic[0].length )	{
			pic[aX][aY]=color;
			while(aX!=bX && aY != bY){
				int deltaX=1,deltaY=1;
				if(bX<aX) deltaX=-1;
				if(bY<aY) deltaY=-1;
				aX+=deltaX;
				aY+=deltaY;
				if(aX >0 || aY >0 || aX<pic.length-1 || aY< pic[0].length-1) 
					pic[aX][aY]=color;
			}
		}
	}
	
	public static int[][] prototypingMethod(int[][] src)	{ //Just a method to test new ideas
		int[][] pic=(src);
		int W=pic.length;
		int H=pic[0].length;
		int[][] tgt=new int[W][H];
		for(int x=0;x<W;x++)	{
			for(int y=0;y<H;y++)	{
				int xLeft=x-1;
				int xRight=x+1;
				int yUp=y-1;
				int yDown=y+1;
				boolean xLeftCHK= xLeft>=0;
				boolean xRightCHK= xRight<W;
				boolean yUpCHK= yUp>=0;
				boolean yDownCHK= yDown<H;
				boolean xLeftDiff = false,xRightDiff = false,yUpDiff = false,yDownDiff = false;
				double differenceThresh=0.1; //Looking for a 10 percent difference here
				if(xLeftCHK)	{
					xLeftDiff=pixelDiff(pic[x][y],pic[xLeft][y])>=differenceThresh;
				}
				if(xRightCHK)	{
					xRightDiff=pixelDiff(pic[x][y],pic[xRight][y])>=differenceThresh;
				}
				if(yUpCHK)	{
					yUpDiff=pixelDiff(pic[x][y],pic[x][yUp])>=differenceThresh;
				}
				if(yDownCHK)	{
					yDownDiff=pixelDiff(pic[x][y],pic[x][yDown])>=differenceThresh;
				}
				if(xLeftDiff || xRightDiff || yUpDiff || yDownDiff)	{
					double count=0,sumR=0,sumG=0,sumB=0;
					if(xLeftDiff)	{
						count++;
						sumR+=RGBUtilities.toRed(pic[xLeft][y]);
						sumG+=RGBUtilities.toGreen(pic[xLeft][y]);
						sumB+=RGBUtilities.toBlue(pic[xLeft][y]);
					}
					if(xRightDiff)	{
						count++;
						sumR+=RGBUtilities.toRed(pic[xRight][y]);
						sumG+=RGBUtilities.toGreen(pic[xRight][y]);
						sumB+=RGBUtilities.toBlue(pic[xRight][y]);
					}
					if(yUpDiff)	{
						count++;
						sumR+=RGBUtilities.toRed(pic[x][yUp]);
						sumG+=RGBUtilities.toGreen(pic[x][yUp]);
						sumB+=RGBUtilities.toBlue(pic[x][yUp]);
					}
					if(yDownDiff)	{
						count++;
						sumR+=RGBUtilities.toRed(pic[x][yDown]);
						sumG+=RGBUtilities.toGreen(pic[x][yDown]);
						sumB+=RGBUtilities.toBlue(pic[x][yDown]);
					}
					int color=RGBUtilities.toRGB((int)Math.round(sumR/count),(int)Math.round(sumG/count), (int)Math.round(sumB/count));
					tgt[x][y]=color;
				}else tgt[x][y]=pic[x][y];
				
			}
		}
		return tgt;
	}//END OF METHOD---------------------------------------------------------------------------------------------------------------
	
	public static int[][] cartoonify(int[][] src,int numOfColors)	{
		int H=src[0].length;
		int W=src.length;
		int[] colors = new int[numOfColors]; //
		for(int i=0;i<numOfColors;i++){
			int component=Outputer.mapper(i, 0, numOfColors, 0, 255);
			colors[i]=component;
		}
		int[][] colorMap = new int[W][H];
		int[] colorsR = new int[colors.length];
		int[] colorsG = new int[colors.length];
		int[] colorsB = new int[colors.length];
		int[] counts = new int[colors.length];
		for(int x=0;x<W;x++)	{
			for(int y=0;y<H;y++)	{
				int index = giveClosestColor(src[x][y],colors);
				colorMap[x][y]=index;
				colorsR[index] += RGBUtilities.toRed(src[x][y]);
				colorsG[index] += RGBUtilities.toGreen(src[x][y]);
				colorsB[index] += RGBUtilities.toBlue(src[x][y]);
				counts[index]++;
			}
		}
		
		int[][] tgtPic= new int[W][H];

		for(int x=0;x<W;x++) {
			for(int y=0;y<H;y++) {
				int index = colorMap[x][y];
				int count = counts[index];
				tgtPic[x][y]= RGBUtilities.toRGB(colorsR[index]/count, colorsG[index]/count, colorsB[index]/count);
			}
		}
		
		return tgtPic;
	}//END OF METHOD---------------------------------------------------------------------------------------------------------------
	
	//returns index of closest color in the color int[] to inColor
	private static int giveClosestColor(int inColor,int[] colorsComps)	{
		//We know that each value in colorsComps represents a value from 0 to 255.
		
		int inColorCompsR = RGBUtilities.toRed(inColor);
		int inColorCompsG = RGBUtilities.toGreen(inColor);
		int inColorCompsB = RGBUtilities.toBlue(inColor);
		int indexOfClosest=0;
		int smallestDiff= Integer.MAX_VALUE;
		for (int i = 0; i < colorsComps.length; i++) {
			int closenessToColor = Math.abs(colorsComps[i]- inColorCompsR)+ Math.abs(colorsComps[i] - inColorCompsB)+ Math.abs(colorsComps[i] - inColorCompsG);
			if(closenessToColor<smallestDiff) {
				smallestDiff=closenessToColor;
				indexOfClosest=i;
			}
		}
		return indexOfClosest;
	}//END OF METHOD---------------------------------------------------------------------------------------------------------------
	
	public static double pixelDiff(int pixel1,int pixel2)	{
		int r1,r2,g1,g2,b1,b2;
		r1=RGBUtilities.toRed(pixel1);
		g1=RGBUtilities.toGreen(pixel1);
		b1=RGBUtilities.toBlue(pixel1);
		
		r2=RGBUtilities.toRed(pixel2);
		g2=RGBUtilities.toGreen(pixel2);
		b2=RGBUtilities.toBlue(pixel2);
		double diffR,diffG,diffB;
		if(r1==0) r1=1;
		if(g1==0) g1=1;
		if(b1==0) b1=1;
		diffR=(r1-r2)/r1; //percent difference of r2 from r1
		diffG=(g1-g2)/g1; //percent difference of g2 from g1
		diffB=(b1-b2)/b1; //percent difference of b2 from b1
		return (diffR+diffG+diffB)/3;	
	}//END OF METHOD---------------------------------------------------------------------------------------------------------------
	//The following are methods for getting the instance variable for the formulas.
    //if janino doesnt pan out, use https://code.google.com/p/dexmaker/   If that doesnt pan out either (it should), use ideone.com's api
	public static void setCurrentPicture(String[] formulas,Context context) {
        /*
		SimpleCompiler compiler=new SimpleCompiler();
        try {
            compiler.cook("import java.lang.Math; import android.graphics.Bitmap; public class JavaClass {public int getOutput(int x, int y)	{return 0;}public int getOutputR(int x, int y)	{return (int) Math.round("+formulas[0]+");}public int getOutputG(int x, int y)	{return (int) Math.round("+formulas[1]+");}public int getOutputB(int x,int y)	{return (int) Math.round("+formulas[2]+");}public int getOutputA(int x,int y)	{return (int) Math.round("+formulas[3]+");} public Bitmap quadrant1Graph(int width,int height)	{Bitmap newPic=Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888);for(int x=0; x<width; x++)	{for(int Y=height-1; Y>=0; Y--)	{int y=height-1-Y;int r=this.getOutputR(x,y);int g=this.getOutputG(x,y);int b=this.getOutputB(x, y);int a=this.getOutputA(x,y);if(r>255) r=255;if(g>255) g=255;if(b>255) b=255;if(a>255) a=255;int pixel=(( (a << 24) | (r << 16)) | (g << 8)) | b;;newPic.setPixel(x,y,pixel);}}return newPic;}}");
        } catch (CompileException e) {
            System.out.println("COMPILE EXCEPTION YO! Catch Block 2");
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        ClassLoader classLoader = compiler.getClassLoader();
        DexClassLoader dexClassLoader = new DexClassLoader("/full/path/com.example.apk",
                context.getFilesDir().getAbsolutePath(),// /data/data/foo/files
                null,  // native lib path, I haven't used this
                classLoader);
        Class<?> myClass= null;
        try {
            myClass = dexClassLoader.loadClass("JavaClass");

        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException found here yo!"+e.getMessage());
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        Object myObj=null;
		try {
			myObj=myClass.newInstance();  //NPE here  ... myClass must be null
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
            System.out.println("INSTANTIATION EXCEPTION YO!! Catch Block 5");
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
            System.out.println("ILLEGAL ACCESS EXCEPTION YO!! Catch Block 6");
		}
        */
    }

    public static void setCurrentPicture(String exprR,String exprG,String exprB,int Width, int Height) {
        fixFormulaFormatting(Width,Height,exprR,exprG,exprB);

        Picture pic = new Picture(exprR,exprG,exprB,width,height);

        PixelPowerGrapher.currentPicture = pic;
    }

	public static void fixFormulaFormatting(int Width,int Height,String... formulas)	{
		for(int i=0;i<formulas.length;i++){
			//Enclosing ternary operations with (  )
			if(formulas[i].contains("?") && formulas[i].contains(":"))	{
				formulas[i]='('+formulas[i]+')';
			}

            formulas[i] = formulas[i].replaceAll("width",Width+"");
            formulas[i] = formulas[i].replaceAll("height",Height+"");
			//Fixing Parenthesis
			int countOpen=0;
			int countClosed=0;
			for(int a=0;a<formulas[i].length();a++)	{
			    if(formulas[i].charAt(a)=='(') countOpen++;
			    if(formulas[i].charAt(a)==')') countClosed++;
			}
			for(int a=0;a<countOpen-countClosed;a++)	{
				formulas[i]=formulas[i]+')';
			}
			for(int a=0;a<countClosed-countOpen;a++)	{
				formulas[i]='('+formulas[i];
			}
		}
	}
	//These getResult functions may not be needed soon...
	public static int getResultR(int x, int y) {
		return 0;
	}
	
	public static int getResultB(int x,int y) {
		return 0;
	}
	
	public static int getResultG(int x,int y) {
		return 0;
	}
	
	public static int getResultA(int x, int y)	{
        return 255;
	//	return (int) Math.round(currentPicture.getPixelGComponent(x, y));
	}
	
	
}//END OF CLASS--------------------------------------------------------------------------------------------------------------------
/* LIST OF COOL FUNCTIONS TO TRY
 * 
 * Math.tan(tri(x)-tri(y))
 * Math.tan(x^2+y^2)
 * Math.tan(catalanNums(x)+catalanNums(y))
 * Math.pow(tri(x+y),n)
 * Math.tan(euclidianGCD(x,y))
 * exponentialDecay(255, 0.0008, y+x)
 * Math.tan(y & x)
 * Math.pow(x|y, x&y)          <--Sierpinski Triangles!!
 * 
 */

/* LIST OF COOL COMBO FUNCTIONS TO TRY
 * 					int f_xy=(int)Math.ceil(255*Math.sin(exponentialDecay(255, 0.0008, y+x))); //Try Messing around with Math.tan(x^2+y^2)  :)
					//ENTER MAIN FUNCTION ABOVE
					int r=(int)Math.ceil(255*Math.sin(exponentialDecay(255, 0.0008, y-x))); //Sometimes does to Black and White
					int g=(int)Math.ceil(255*Math.sin(exponentialDecay(255, 0.0008, x-y))); //Sometime does to Black And White
*
*
*					
*/

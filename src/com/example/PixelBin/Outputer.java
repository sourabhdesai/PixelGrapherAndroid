package com.example.PixelBin;

public class Outputer {
	public static int Width=PixelPowerGrapher.width;
	public static int Height=PixelPowerGrapher.height;
    public static int w=Width, width = Width;
    public static int h=Height,height=Height;
	
	public double getOutput(int x, int y)	{
		return 0;
	}

	public double getOutputR(int x, int y)	{
		return 0;
	}
	
	public double getOutputG(int x, int y)	{
		return 0;
	}
	
	public double getOutputB(int x,int y)	{
		return 0;
	}
	
	public double getOutputA(int x, int y)	{
		return 0;
	}

    public int map(double x, double in_min, double in_max, double out_min, double out_max)	{
        return (int) Math.round((x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min);
    }//END OF METHOD---------------------------------------------------------------------------------------------------------------
	
	//Math Functions
	
	public static int mapper(double x, double in_min, double in_max, double out_min, double out_max)	{
		return (int) Math.round((x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min);
	}//END OF METHOD---------------------------------------------------------------------------------------------------------------
	
	//RECURSIVE MATH FORMULAS
	
	public static int fib(int n)	{
		if (n==0) return 0;
		if(n==1) return 1;
		return fib(n-1)+fib(n-2);
	}//END OF METHOD---------------------------------------------------------------------------------------------------------------
	
	public static int fibFast(int n)	{
		int num1, num2, num3;
		num1 = -1; num2 = 1;	
		for (int i = 0; i <= n; i++)	{
		num3 = (num1 + num2);
		num1 = num2;
		num2 = num3;	
		}
		return num2;
	}//END OF METHOD---------------------------------------------------------------------------------------------------------------
	
	public static int tri(int n)	{
		if(n==0) return 0;
		return n+tri(n-1);
	}//END OF METHOD---------------------------------------------------------------------------------------------------------------
	
	public static int factorial(int n)	{
		if(n==1) return 1;
		int c=factorial(n-1);
		return c*n;
	}
	
	public static int catalanNums(int n)	{
		int product=1;
		for(int k=2;k<=n;k++)	{
			product+=((n+k)/k);
		}
		return product;
	}//END OF METHOD---------------------------------------------------------------------------------------------------------------
	
	public static int hanoi(int n)	{
		if(n<=1) return 1;
		return 2*hanoi(n-1)+1;
	}//END OF METHOD---------------------------------------------------------------------------------------------------------------
	
	public static int euclidianGCD(int a, int b)	{
		int x=a;
		int y=b;
		while(y>0)	{
			int r=x%y;
			x=y;
			y=r;
		}
		return x;
	}//END OF METHOD---------------------------------------------------------------------------------------------------------------
	
	
	public static int iterativeDecay(float decay,int iteration, int startValue)	{
		if(decay>1) return startValue;
		if(iteration==0) return startValue;
		return (int) decay*iterativeDecay(decay,iteration-1,startValue); //I know the closed form (decay^iteration)*startValue is faster but I think recursion is cooler
	}//END OF METHOD---------------------------------------------------------------------------------------------------------------
	
	public static int exponentialDecay(double decayRate, double decayConstant, int variable)	{
		// Larger decay constants make the quantity vanish much more rapidly.
		return (int) Math.floor(decayRate*Math.pow(Math.E,-1*decayConstant*variable));
	}//END OF METHOD---------------------------------------------------------------------------------------------------------------
	
	public static int distanceFormula(int x1, int y1, int x2, int y2)	{
		return (int) Math.sqrt(((x1-x2)^2)+((y1-y2)^2));
	}//END OF METHOD---------------------------------------------------------------------------------------------------------------
	
	public static double theta(int x1, int y1, int x2, int y2)	{
		int vector1=distanceFormula(x1, y1, x2, y2);
		if(vector1==0) return (int) Math.PI/2;
		return Math.asin(((x1*x2)+(y1*y2))/vector1);
	}//END OF METHOD---------------------------------------------------------------------------------------------------------------
	
	public static double choose(int a, int b)	{
		return 0;
	}
}




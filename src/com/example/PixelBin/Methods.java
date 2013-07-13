package com.example.PixelBin;

import android.graphics.drawable.Drawable;
import android.text.StaticLayout;

/**
 * Created with IntelliJ IDEA.
 * User: DESAI_628IL
 * Date: 7/2/13
 * Time: 8:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class Methods {
    /*
    * Here are the methods built in to the exp4j library
    *
abs: absolute value
acos: arc cosine
asin: arc sine
atan: arc tangent
cbrt: cubic root
ceil: nearest upper integer
cos: cosine
cosh: hyperbolic cosine
exp: euler's number raised to the power (e^x)
floor: nearest lower integer
log: logarithmus naturalis (base e)
sin: sine
sinh: hyperbolic sine
sqrt: square root
tan: tangent
tanh: hyperbolic tangent

*****Added  Functions are****
* map
    */

    public static String[] MethodNames = {"abs", "acos", "asin","atan","cbrt","ceil","cos","cosh","equalzero","exp","floor","greaterzero","lesszero","log","map","random","sin","sinh","sqrt","tan","tanh"};

    private static String[] singleParam = {"value"};
    private static String[] logicParams = {"condition value","true value","false value"};
    private static String[] mapParams = {"input","input minimum","input maximum","output minimum","output maximum"};

    private static MethodInfo abs = new MethodInfo("abs", "A function that takes the absolute value of your parameter. \n (It turns your negatives into positives)", null, singleParam, "absolute value of parameter", "Good if you want to create symmetric images");
    private static MethodInfo acos = new MethodInfo("acos", "Your standard inverse cosine function, just like in math class", null, singleParam, "the inverse cosine of your input", "Good for pattern based images");
    private static MethodInfo asin = new MethodInfo("asin", "Your standard inverse sine function, just like in math class", null, singleParam, "the inverse sine of your input", "Good for pattern based images");
    private static MethodInfo atan = new MethodInfo("atan", "Your standard inverse tangent function, just like in math class", null, singleParam, "the inverse tangent of your input", "You can do some pretty funky stuff with this one");
    private static MethodInfo cbrt = new MethodInfo("cbrt", "A method that takes the cube root of your parameter", null, singleParam, "the cube root of your input", "Be careful with this, it takes up quite a bit of horsepower");
    private static MethodInfo ceil = new MethodInfo("ceil", "Takes your input parameter and spits out the closest integer that is greater than or equal to it\nFor example: ceil(3.14) = 4", null, singleParam, "an integer number", "Pronounced \"seal\"\nlike in the sentence \"Watch out for that loose seal!\"");
    private static MethodInfo cos = new MethodInfo("cos", "Your standard cosine function, just like in math class", null, singleParam, "the cosine of your input\n(always a value between -1 and 1)", "Good for pattern based images");
    private static MethodInfo cosh = new MethodInfo("cosh", "Your standard hyperbolic cosine function, just like in math class", null, singleParam, "the hyperbolic cosine of your input", "No notes");
    private static MethodInfo equalzero = new MethodInfo("equalzero", "One of the three logic methods. Takes a look at your first parameter and if it equal to zero, it returns your second parameter, otherwise it returns your third parameter", null, logicParams, "Either your second or third parameter", "Ever wonder how this app's beautiful background images were made?");
    private static MethodInfo exp = new MethodInfo("cos", "Your standard cosine function, just like in math class", null, singleParam, "the cosine of your input\n(always a value between -1 and 1)", "Good for pattern based images");


    public static MethodInfo[] methodInfos = new MethodInfo[MethodNames.length];


}

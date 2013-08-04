package com.example.PixelBin;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.parse.Parse;
import com.parse.ParseUser;

/**
 * Created with IntelliJ IDEA.
 * User: DESAI_628IL
 * Date: 6/25/13
 * Time: 4:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class SplashScreen extends Activity {
    public static int galaxyS4Width = 1080;
    @Override
    public void onCreate(Bundle splashScreen) {
        super.onCreate(splashScreen);
        setContentView(R.layout.main);
        this.setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //gets screen dimensions and sets them to screenWidth and screenHeight variables
        Display display = getWindowManager().getDefaultDisplay();
        final Point size = new Point();
        display.getSize(size);
        if(PixelPowerGrapher.screenSize == null) PixelPowerGrapher.screenSize = new int[2];
        PixelPowerGrapher.screenSize[0]=size.x;
        PixelPowerGrapher.screenSize[1]=size.y;

        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayoutSplashScreen);
        if(PixelPowerGrapher.screenSize[0]> galaxyS4Width ||  getResources().getConfiguration().orientation == 2)   {
            relativeLayout.setBackgroundResource(R.drawable.funky_chalkboard_white);
        } else {
            relativeLayout.setBackgroundResource(R.drawable.funky_chalkboard_cropped_white);
        }
        if(Build.VERSION.SDK_INT >= 16) {
            new createAndCacheImage().execute(size);
        }
        else {
            Thread timer = new Thread() {
                @Override
                public void run()   {
                    try {
                        sleep(3000); //Let them look at the splash screen
                    } catch (InterruptedException e) {

                    } finally {
                        decideNextActivity();
                    }
                }
            };
            timer.start();
        }
    }

    public void moveToHomeScreen()    {
        //Code to move to home screen
        Intent intent = new Intent(this,Home_Activity.class);
        startActivity(intent);
        this.finish();
    }

    public static Bitmap createBackgroundImage(int width,int height)	{
        Bitmap newPic=Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888);
        //Green Color=#8fcb00 (143 ,203 ,0 )     Red Color=#ff3037 ( 255, 48, 55)     Blue Color=#0ad8ff ( 10, 216, 255)
        for(int x=0; x<width; x++)	{ //X: starts from 0, goes to MaxX coordinate
            for(int Y=height-1; Y>=0; Y--)	{	//Y: Starts from MaxY coordinate, goes to 0
                int y=height-1-Y; //y: Starts from 0, goes to MaxY coordinate
                boolean redCondition = (x+y)%20==0;
                boolean greenCondition = (y-x)%20==0;
                boolean blueCondition = (x|y)%2==0;   //
                int r=redCondition?Outputer.mapper(x,0,width,0,255):(greenCondition?Outputer.mapper(y - x, -1 * width, height, 0, 143):blueCondition?Outputer.mapper(y, 0, height, 0, 10):255);
                int g=greenCondition?Outputer.mapper(y - x, -1 * width, height, 0, 203):redCondition?Outputer.mapper(x, 0, width, 0, 48):blueCondition?Outputer.mapper(y, 0, height, 0, 216) :255;
                int b=blueCondition?Outputer.mapper(y, 0, height, 0, 255):greenCondition?0:redCondition?Outputer.mapper(x, 0, width, 0, 55):255;
                if(r>255) r=255;
                if(g>255) g=255;
                if(b>255) b=255;

                int pixel=RGBUtilities.toRGBA(r,g,b,255);
                newPic.setPixel(x,y,pixel);
            }
        }
        return newPic;
    }//END OF METHOD---------------------------------------------------------------------------------------------------------------

    public void decideNextActivity()    {
        Parse.initialize(getApplicationContext(), "nheqtBbDghS2JsKDLCuKpTNRsLJk6X4URfZ8tWPQ", "JSvhT0GNWjJCwQ7Hfef5ttPcAuNKJ2RW3IsTFSw8");
        ParseUser user = ParseUser.getCurrentUser();
        if(user == null)    {
            //Means user has not logged in, in the past. So move to registration screen
            Intent registrationScreen= new Intent (getApplicationContext(), Registration_Activity.class);
            //    registrationScreen.putExtra("CachedBackgroundLocation",path);
            startActivity(registrationScreen);
            finish();
        }  else {
            moveToHomeScreen();
        }
    }

    private class createAndCacheImage extends AsyncTask<Point, Integer, Object>   {

        @Override
        protected Object doInBackground(Point... params) {
            Bitmap img = createBackgroundImage(params[0].x,params[0].y); //screen dimensions
            PixelPowerGrapher.mainBackground = new BitmapDrawable(getResources(),img);
            PixelPowerGrapher.actionbarBackground= new BitmapDrawable(getResources(),img);
            return null;
        }

        protected void onProgressUpdate(Integer integer) {

        }
        @Override
        protected void onPostExecute(Object obj) {
            decideNextActivity();
        }
    }

}

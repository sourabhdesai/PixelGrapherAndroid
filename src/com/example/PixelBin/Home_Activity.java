package com.example.PixelBin;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import com.example.PixelBin.ThePixelBinActivities.PixelBin_Activity;
import com.parse.ParseUser;
import de.neofonie.mobile.app.android.widget.crouton.Crouton;
import de.neofonie.mobile.app.android.widget.crouton.Style;

/**
 * Created with IntelliJ IDEA.
 * User: DESAI_628IL
 * Date: 6/25/13
 * Time: 8:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class Home_Activity extends FragmentActivity {
    boolean loggedIn = false;
    ParseUser user;
    Resources res;

    @Override
    public void onCreate(Bundle home_activity) {
        super.onCreate(home_activity);
        setContentView(R.layout.home);
        res = getResources();
        user = ParseUser.getCurrentUser();
        loggedIn = getIntent().getBooleanExtra("LoggedIn",false); //false is a default value, shouldnt be a big deal really
        this.setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if(Build.VERSION.SDK_INT >= 16) setBackgroundImage();
        ParseUser user = ParseUser.getCurrentUser();
        if(user != null) {
            loggedIn = true;
        }
        Button genPicButton = (Button) findViewById(R.id.genapic_button_home);
        Button savedFuncsButton = (Button) findViewById(R.id.savedfunc_button_home);
        Button pixelBinButton = (Button) findViewById(R.id.pixelbin_button_home);
        Button tutorialButton = (Button) findViewById(R.id.tutorial_button_home);

        genPicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToGenPicActivity();
            }
        });

        savedFuncsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToSavedFuncsActivity();
            }
        });

        pixelBinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToPixelBinActivity(loggedIn);
            }
        });

        tutorialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToTutorialActivity();
            }
        });

        ViewPager viewPager = (ViewPager) findViewById(R.id.imageViewer_ViewPager_home);
        ImageFragmentPageAdapter adapter = new ImageFragmentPageAdapter(getSupportFragmentManager(),getFragments());
        viewPager.setAdapter(adapter);
    }

    public void moveToGenPicActivity()  {
        Intent intent = new Intent(this,GeneratePicture_Activity.class);
        startActivity(intent);
    }

    public void moveToSavedFuncsActivity()  {
        Intent intent = new Intent(this,SavedFunctionsActivity.class);
        startActivity(intent);
    }

    public void moveToPixelBinActivity(boolean LoggedIn)    {
        if(LoggedIn)    {
            Intent intent = new Intent(this, PixelBin_Activity.class);
            startActivity(intent);
        } else Crouton.makeText(this,"You must be logged in to access that feature",Style.ALERT).show();
    }

    public void moveToTutorialActivity()    {

    }

    public void moveToAboutActivity()   {
        //TODO: Create About Activity with credits
    }

    public void moveToShareActivity()   {
        //TODO: Create Share Activity
    }

    public void moveToLogInOrOutActivity()    {
        if(user !=null) ParseUser.logOut();
        Intent intent = new Intent(this,Login_Activity.class);
        startActivity(intent);
        finish();
    }

    public void setBackgroundImage()  {
        if(PixelPowerGrapher.mainBackground==null) System.out.println("Even the static one was null :(");
        findViewById(R.id.layout_home).setBackground(PixelPowerGrapher.mainBackground);
    }

    public ImageFragment[] getFragments()    {
        ImageFragment[] fragments = new ImageFragment[3];
        fragments[0] = new ImageFragment(res);
        fragments[0].setImage(R.drawable.pixelbin_icon6_square_transparent);
        fragments[0].setHeader("Welcome " + (!loggedIn ? "" : user.getUsername()));
        fragments[0].setFooter("Tap here for more info...");
        fragments[0].setOnImageClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToAboutActivity();
            }
        });
        fragments[1] = new ImageFragment(res);
        fragments[1].setImage(R.drawable.share);
        fragments[1].setHeader("Share");
        fragments[1].setFooter("Share us on Facebook or Tweet us on Twitter");
        fragments[2] = new ImageFragment(res);
        fragments[2].setImage(loggedIn?R.drawable.logout:R.drawable.login);
        fragments[2].setHeader(loggedIn?"Log Out":"Log In");
        fragments[2].setOnImageClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToLogInOrOutActivity();
            }
        });
        return fragments;
    }

    public class ImageFragmentPageAdapter extends FragmentPagerAdapter {
        private ImageFragment[] imageFragments;

        public ImageFragmentPageAdapter(FragmentManager fm, ImageFragment[] fragments) {
            super(fm);
            this.imageFragments = fragments;
        }
        @Override
        public Fragment getItem(int position) {
            return this.imageFragments[position];
        }

        @Override
        public int getCount() {
            return this.imageFragments.length;
        }
    }
}

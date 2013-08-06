package com.example.PixelBin;

import android.app.Activity;
import android.content.Context;
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.example.PixelBin.ThePixelBinActivities.PixelBin_Activity;
import com.parse.ParseUser;
import de.neofonie.mobile.app.android.widget.crouton.Crouton;
import de.neofonie.mobile.app.android.widget.crouton.Style;
import se.emilsjolander.flipview.FlipView;

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
        final ParseUser user = ParseUser.getCurrentUser();
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

        final FlipView flipView = (FlipView) findViewById(R.id.flipview_home);

        BaseAdapter adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return 3;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public Object getItem(int position) {
                return position;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public long getItemId(int position) {
                return position;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                final int pos = position;
                convertView = getLayoutInflater().inflate(R.layout.image_fragment,null);
                TextView header = (TextView) convertView.findViewById(R.id.header_textView_imagefragment);
                TextView footer = (TextView) convertView.findViewById(R.id.footer_textView_imageFragment);
                ImageView imgView = (ImageView) convertView.findViewById(R.id.imageView_imagefragment);
                switch (position)   {
                    case 0:
                        header.setText("Welcome "+(loggedIn?user.getUsername():""));
                        footer.setText("Tap here for more info...");
                        imgView.setImageResource(R.drawable.pixelbin_icon6_square_transparent);
                        break;
                    case 1:
                        header.setText("Share");
                        footer.setText("Share us on Facebook or Tweet about us on Twitter");
                        imgView.setImageResource(R.drawable.share);
                        break;
                    case 2:
                        header.setText(loggedIn?"Log Out":"Log In");
                        imgView.setImageResource(loggedIn ? R.drawable.logout : R.drawable.login);
                        break;
                }
                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch(pos) {
                            case 0:
                                moveToAboutActivity();
                                break;
                            case 1:
                                moveToShareActivity();
                                break;
                            case 2:
                                moveToLogInOrOutActivity();
                        }
                    }
                });
                return convertView;  //To change body of implemented methods use File | Settings | File Templates.
            }
        };
        flipView.setAdapter(adapter);
        final Activity activity = this;
        Thread peekTimer = new Thread() {
            public void run()   {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } finally {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            flipView.peakNext(true);
                        }
                    });
                }
            }
        };
        peekTimer.start();
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
}

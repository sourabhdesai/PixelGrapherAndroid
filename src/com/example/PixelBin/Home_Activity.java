package com.example.PixelBin;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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
public class Home_Activity extends Activity {
    boolean loggedIn = false;

    @Override
    public void onCreate(Bundle home_activity) {
        super.onCreate(home_activity);
        setContentView(R.layout.home);
        loggedIn = getIntent().getBooleanExtra("LoggedIn",false); //false is a default value, shouldnt be a big deal really
        this.setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if(Build.VERSION.SDK_INT >= 16) setBackgroundImage();
        ParseUser user = ParseUser.getCurrentUser();
        if(user != null) Crouton.makeText(this,"Welcome "+user.getUsername(), Style.INFO).show();
        Button genPicButton = (Button) findViewById(R.id.genapic_button_home);
        Button savedFuncsButton = (Button) findViewById(R.id.savedfunc_button_home);
        Button pixelBinButton = (Button) findViewById(R.id.pixelbin_button_home);
        Button tutorialButton = (Button) findViewById(R.id.tutorial_button_home);

        TextView titleTextView = (TextView) findViewById(R.id.title_textview_home);

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

        titleTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToAboutActivity();
            }
        });
    }

    public void moveToGenPicActivity()  {
        Intent intent = new Intent(this,GeneratePicture_Activity.class);
        startActivity(intent);
    }

    public void moveToSavedFuncsActivity()  {

    }

    public void moveToPixelBinActivity(boolean LoggedIn)    {
        if(LoggedIn)    {

        } else Crouton.makeText(this,"You must be logged in to access that feature",Style.ALERT).show();
    }

    public void moveToTutorialActivity()    {

    }

    public void moveToAboutActivity()   {

    }

    public void setBackgroundImage()  {
        if(PixelPowerGrapher.mainBackground==null) System.out.println("Even the static one was null :(");
        findViewById(R.id.layout_home).setBackground(PixelPowerGrapher.mainBackground);
    }
}

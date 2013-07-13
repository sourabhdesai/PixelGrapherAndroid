package com.example.PixelBin;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import de.neofonie.mobile.app.android.widget.crouton.Crouton;
import de.neofonie.mobile.app.android.widget.crouton.Style;

/**
 * Created with IntelliJ IDEA.
 * User: DESAI_628IL
 * Date: 6/25/13
 * Time: 4:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class Registration_Activity extends Activity {
    public static boolean parseInitialized=false;

    @Override
    public void onCreate(Bundle registration_activity) {
        super.onCreate(registration_activity);
        setContentView(R.layout.registration);
        this.setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if(Build.VERSION.SDK_INT >= 16) setBackgroundImage();
        final EditText usernameEditTxt = (EditText) findViewById(R.id.username_registration);
        final EditText passEditTxt = (EditText) findViewById(R.id.password_registration);
        TextView alreadyUserEditText = (TextView) findViewById(R.id.alreadyauser_textView_registration);
        TextView withoutAccountTextView = (TextView) findViewById(R.id.goWithoutAccount_registration);

        usernameEditTxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus && usernameEditTxt.getText().toString().equals("Username")) {
                    usernameEditTxt.setText("");
                }
            }
        });

        passEditTxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus&& passEditTxt.getText().toString().equals("Password")) {
                    passEditTxt.setText("");
                    passEditTxt.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });

        Button registerButton = (Button) findViewById(R.id.register_registration);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register(usernameEditTxt.getText().toString().trim(),passEditTxt.getText().toString().trim());
            }
        });

        alreadyUserEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToLogin();
            }
        });

        withoutAccountTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToHomeScreen(false);
            }
        });

    }

    public void register(String username, String password)  {
        //Connects to the parse projects server
        if(username.length()==0 || password.length()==0)    {
            Crouton.makeText(Registration_Activity.this,"You cannot have any empty fields", Style.ALERT);
            return;
        }
        ParseUser user = new ParseUser();
        user.setUsername(username);
        user.setPassword(password);
        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    // Hooray! Let them use the app now.
                    moveToHomeScreen(true);
                } else {
                    Crouton.makeText(Registration_Activity.this,e.getMessage(),Style.ALERT);
                    // Sign up didn't succeed. Look at the ParseException
                    // to figure out what went wrong
                }
            }
        });

    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        Crouton.cancelAllCroutons();
    }

    public void moveToHomeScreen(boolean loggedIn)    {
        //Code to move to home screen
        Intent intent = new Intent(this,Home_Activity.class);
        intent.putExtra("LoggedIn", loggedIn);
        startActivity(intent);
        this.finish();
    }

    public void moveToLogin()   {
        Intent intent = new Intent(this,Login_Activity.class);
        startActivity(intent);
    }

    public void setBackgroundImage()  {
    /*    Intent intent = getIntent();
        String imagePath = intent.getStringExtra("CachedBackgroundLocation");
        BitmapLruCache.Builder builder = new BitmapLruCache.Builder(getApplicationContext());
        builder.setMemoryCacheEnabled(true).setMemoryCacheMaxSizeUsingHeapSize();
        builder.setDiskCacheEnabled(true).setDiskCacheLocation(new File(imagePath));

        BitmapLruCache mCache = builder.build();
        CacheableBitmapDrawable background = mCache.get("Background");     */
        if(PixelPowerGrapher.mainBackground==null) System.out.println("Even the static one was null :(");
        findViewById(R.id.linearlayout_login).setBackground(PixelPowerGrapher.mainBackground);
    }
}

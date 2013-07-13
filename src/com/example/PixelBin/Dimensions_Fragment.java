package com.example.PixelBin;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.actionbarsherlock.app.SherlockFragment;
import de.neofonie.mobile.app.android.widget.crouton.Crouton;
import de.neofonie.mobile.app.android.widget.crouton.Style;

/**
 * Created with IntelliJ IDEA.
 * User: DESAI_628IL
 * Date: 6/27/13
 * Time: 11:52 AM
 * To change this template use File | Settings | File Templates.
 */
public class Dimensions_Fragment extends SherlockFragment {
    EditText widthEditText;
    EditText heightEditText;
    Button setDimensionsButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance)  {
//        getSherlockActivity().setSupportProgressBarIndeterminateVisibility(true);
        return inflater.inflate(R.layout.dimensions, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        getActivity().setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        if(Build.VERSION.SDK_INT >= 16)setBackgroundImage();
        View view = getView();
        Activity mainActivity = getActivity();
        final Intent thisIntent = mainActivity.getIntent();

        widthEditText = (EditText) view.findViewById(R.id.width_editText_dimensions);
        heightEditText = (EditText) view.findViewById(R.id.height_editText_dimensions);
        setDimensionsButton = (Button) view.findViewById(R.id.set_button_dimensions);

        widthEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus && widthEditText.getText().toString().equals("Width")) {
                    widthEditText.setText("");
                }
            }
        });

        heightEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus && heightEditText.getText().toString().equals("Height")) {
                    heightEditText.setText("");
                }
            }
        });

        setDimensionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String widthText = widthEditText.getText().toString();
                String heightText = heightEditText.getText().toString();
                Integer width = Integer.getInteger(widthText,-1);
                Integer height = Integer.getInteger(heightText,-1);
                if(width<0 || height<0) Crouton.makeText(getActivity(),"Please Enter Valid Dimensions", Style.ALERT);
                else    {
                    thisIntent.putExtra("Width",width.intValue());
                    thisIntent.putExtra("Height",height.intValue());
                }
            }
        });

    }

    public void setBackgroundImage()  {
    /*    Intent intent = getIntent();
        String imagePath = intent.getStringExtra("CachedBackgroundLocation");
        BitmapLruCache.Builder builder = new BitmapLruCache.Builder(getApplicationContext());
        builder.setMemoryCacheEnabled(true).setMemoryCacheMaxSizeUsingHeapSize();
        builder.setDiskCacheEnabled(true).setDiskCacheLocation(new File(imagePath));

        BitmapLruCache mCache = builder.build();
        CacheableBitmapDrawable background = mCache.get("Background");       */
        if(PixelPowerGrapher.mainBackground==null) System.out.println("Even the static one was null :(");
        getView().findViewById(R.id.relLayout_dimensions).setBackground(PixelPowerGrapher.actionbarBackground);
    }

}

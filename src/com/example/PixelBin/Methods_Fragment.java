package com.example.PixelBin;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockListFragment;

/**
 * Created with IntelliJ IDEA.
 * User: DESAI_628IL
 * Date: 6/27/13
 * Time: 2:04 AM
 * To change this template use File | Settings | File Templates.
 */
public class Methods_Fragment extends SherlockFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance)  {
//        getSherlockActivity().setSupportProgressBarIndeterminateVisibility(true);
        return inflater.inflate(R.layout.classes, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        initControls();
    }

    public void initControls() {
        getActivity().setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        if(Build.VERSION.SDK_INT >= 16)setBackgroundImage();
    }

    @SuppressLint({ "NewApi", "NewApi", "NewApi", "NewApi" })
    @SuppressWarnings("deprecation")
    @TargetApi(11)
    public void copyToClipBoard(String text)  {
        int sdk = android.os.Build.VERSION.SDK_INT;
        if(sdk < android.os.Build.VERSION_CODES.HONEYCOMB) {
            android.text.ClipboardManager clipboard = (android.text.ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
            clipboard.setText(text);

        } else {
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
            android.content.ClipData clip = android.content.ClipData.newPlainText("Method",text);
            clipboard.setPrimaryClip(clip);
        }
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
        getView().findViewById(R.id.relLayout_classes).setBackground(PixelPowerGrapher.actionbarBackground);
    }

}

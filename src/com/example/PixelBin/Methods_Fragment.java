package com.example.PixelBin;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockListFragment;
import de.neofonie.mobile.app.android.widget.crouton.Crouton;
import de.neofonie.mobile.app.android.widget.crouton.Style;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: DESAI_628IL
 * Date: 6/27/13
 * Time: 2:04 AM
 * To change this template use File | Settings | File Templates.
 */
public class Methods_Fragment extends SherlockListFragment{
    ListView lv;
    View mainView;

    public static String[] methodNames = new String[] { "max","min","clamp","abs","ifeq","ifgt","random","hypot","sin","cos","tan","asin",
            "acos","atan","sqrt","cbrt","mapW","mapH","map"};
    public static String[] params = {"(int val1, int val2)","(int val1, int val2)","(int val, int min, int max)",
            "(int val)","(int val, int comparison, int trueval, int falseval)",
            "(int val, int comparison, int trueval, int falseval)","(int maxRandomValue)","(int x, int y)","(int x, int scale)",
            "(int x, int scale)","(int x, int scale)","(int x, int scale)","(int x, int scale)","(int x, int scale)",
            "(int x)","(int x)","(int x,int colorStart,int colorEnd)","(int y,int colorStart,int colorEnd)","(long x, long in_min, long in_max, long out_min, long out_max)"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance)  {
//        getSherlockActivity().setSupportProgressBarIndeterminateVisibility(true);
        return inflater.inflate(R.layout.methods, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        initControls();
    }

    public void initControls() {
        mainView = getView();
        lv = getListView();
        if(Build.VERSION.SDK_INT >= 16)setBackgroundImage();

        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < methodNames.length; ++i) {
            list.add(methodNames[i]+params[i]);
        }
        final ArrayAdapter adapter = new ArrayAdapter(getActivity(),
                android.R.layout.simple_list_item_1, list);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String method =  methodNames[position];
                copyToClipBoard(method+"()");
                Crouton.makeText(getActivity(),"Copied "+methodNames[position], Style.CONFIRM).show();
            }
        });

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
        if(PixelPowerGrapher.mainBackground==null) System.out.println("Even the static one was null :(");
        getView().findViewById(R.id.linearLayout_methods).setBackground(PixelPowerGrapher.actionbarBackground);
    }

}

package com.example.PixelBin.ThePixelBinActivities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.actionbarsherlock.app.SherlockFragment;
import com.example.PixelBin.R;

/**
 * Created with IntelliJ IDEA.
 * User: DESAI_628IL
 * Date: 8/3/13
 * Time: 4:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class Search_Fragment extends SherlockFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance)  {
//        getSherlockActivity().setSupportProgressBarIndeterminateVisibility(true);
        return inflater.inflate(R.layout.search, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

}

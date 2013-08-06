package com.example.PixelBin.ThePixelBinActivities;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.example.PixelBin.R;
import com.example.PixelBin.TabsAdapter;

/**
 * Created with IntelliJ IDEA.
 * User: DESAI_628IL
 * Date: 8/3/13
 * Time: 3:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class PixelBin_Activity extends SherlockFragmentActivity {

    @Override
    public void onCreate(Bundle savedInstance)  {
        super.onCreate(savedInstance);

        super.setTheme(R.style.Sherlock___Theme_Light);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        ViewPager mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.pager_fragmentactivity);
        setContentView(mViewPager);
        this.setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mViewPager.setOffscreenPageLimit(3);
        //Setting Up ActionBarSherlock------------------------------------------------------------------------------------------------------------
        ActionBar actionBar=getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        TabsAdapter myTabsAdapter = new TabsAdapter(this,mViewPager);
        myTabsAdapter.addTab(actionBar.newTab().setText("Top"),Top_Fragment.class,null);
        myTabsAdapter.addTab(actionBar.newTab().setText("New"),Newest_Fragment.class,null);
        myTabsAdapter.addTab(actionBar.newTab().setText("Search"),Search_Fragment.class,null);

        //DONE Setting Up ActionBarSherlock-------------------------------------------------------------------------------------------------------
    }



}

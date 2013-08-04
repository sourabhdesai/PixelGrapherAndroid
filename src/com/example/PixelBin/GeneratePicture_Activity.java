package com.example.PixelBin;

import android.graphics.Point;
import android.support.v4.view.ViewPager;
import android.view.Display;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.app.ActionBar;

import android.os.Bundle;
import de.neofonie.mobile.app.android.widget.crouton.Crouton;

/**
 * Created with IntelliJ IDEA.
 * User: DESAI_628IL
 * Date: 6/26/13
 * Time: 10:03 AM
 * To change this template use File | Settings | File Templates.
 */
public class GeneratePicture_Activity extends SherlockFragmentActivity{
    public static int Width;
    public static int Height;

    @Override
    protected void onCreate(Bundle generatePictureSavedInstance)   {
        //gets screen dimensions and sets them to screenWidth and screenHeight variables
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        //Default picture width and height is that of the devices screen size.
        Width = size.x;
        Height = size.y;
        this.getIntent().putExtra("Width",size.x);
        this.getIntent().putExtra("Height",size.y);

        super.setTheme(R.style.Sherlock___Theme_Light);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        super.onCreate(generatePictureSavedInstance);
        ViewPager mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.pager_fragmentactivity);
        setContentView(mViewPager);

        //Setting Up ActionBarSherlock------------------------------------------------------------------------------------------------------------
        ActionBar actionBar=getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        TabsAdapter myTabsAdapter = new TabsAdapter(this,mViewPager);
        myTabsAdapter.addTab(actionBar.newTab().setIcon(R.drawable.code),Functions_Fragment.class,null);
        myTabsAdapter.addTab(actionBar.newTab().setIcon(R.drawable.toolbox),Methods_Fragment.class,null);
        myTabsAdapter.addTab(actionBar.newTab().setIcon(R.drawable.scale),Dimensions_Fragment.class,null);


        //DONE Setting Up ActionBarSherlock-------------------------------------------------------------------------------------------------------


    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        Crouton.cancelAllCroutons();
    }
}

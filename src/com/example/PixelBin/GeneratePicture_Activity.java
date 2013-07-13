package com.example.PixelBin;

import android.graphics.Point;
import android.view.Display;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import de.neofonie.mobile.app.android.widget.crouton.Crouton;

/**
 * Created with IntelliJ IDEA.
 * User: DESAI_628IL
 * Date: 6/26/13
 * Time: 10:03 AM
 * To change this template use File | Settings | File Templates.
 */
public class GeneratePicture_Activity extends SherlockFragmentActivity{

    @Override
    protected void onCreate(Bundle generatePictureSavedInstance)   {
        //gets screen dimensions and sets them to screenWidth and screenHeight variables
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        //Default picture width and height is that of the devices screen size.
        this.getIntent().putExtra("Width",size.x);
        this.getIntent().putExtra("Height",size.y);

        super.setTheme(R.style.Sherlock___Theme_DarkActionBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        super.onCreate(generatePictureSavedInstance);
        setContentView(R.layout.genapic);

        //Setting Up ActionBarSherlock------------------------------------------------------------------------------------------------------------
        ActionBar actionBar=getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.Tab functionsTab= actionBar.newTab().setText("Functions");
        ActionBar.Tab methodsTab= actionBar.newTab().setText("Methods");
        ActionBar.Tab dimensionsTab= actionBar.newTab().setText("Dimensions");

        Fragment functionsFrag=new Functions_Fragment();
        Fragment methodsFrag= new Methods_Fragment();
        Fragment dimensionsFrag = new Dimensions_Fragment(); //MUST INITIALIZE THIS AFTER YOU CREATE THE ACTIVITY!!!!!!


        functionsTab.setTabListener(new MyTabsListener(functionsFrag));
        methodsTab.setTabListener(new MyTabsListener(methodsFrag));
        dimensionsTab.setTabListener(new MyTabsListener(dimensionsFrag));

        actionBar.addTab(functionsTab);
        actionBar.addTab(methodsTab);
        actionBar.addTab(dimensionsTab);
        //DONE Setting Up ActionBarSherlock-------------------------------------------------------------------------------------------------------


    }

    class MyTabsListener implements ActionBar.TabListener {
        public Fragment fragment;

        public MyTabsListener(Fragment fragment){
            this.fragment = fragment;
        }

        @Override
        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
            // TODO Auto-generated method stub
            ft.replace(R.id.fragment_container, fragment);
        }

        @Override
        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
            // TODO Auto-generated method stub

        }
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        Crouton.cancelAllCroutons();
    }
}

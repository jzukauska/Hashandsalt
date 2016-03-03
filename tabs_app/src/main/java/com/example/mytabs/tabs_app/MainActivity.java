package com.example.mytabs.tabs_app;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;



public class MainActivity extends ActionBarActivity {

    private static final String TAG = "junk";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        setUpTabs(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //            save the selected tab's index so it's re-selected on orientation change
        outState.putInt("tabIndex", getSupportActionBar().getSelectedNavigationIndex());
    }

    private void setUpTabs(Bundle savedInstanceState) {

        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(actionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayShowTitleEnabled(false);

        //Instantiate the new tabs in the action bar
        Tab tab_one = actionBar.newTab();
        Tab tab_two = actionBar.newTab();
        Tab tab_three = actionBar.newTab();

        FirstFragment firstFragment = new FirstFragment();
        tab_one.setText("Hash")
                .setContentDescription("Hashinator")
                .setTabListener(
                        new MyTabListener<FirstFragment>(
                                firstFragment));

        SecondFragment secondFragment = new SecondFragment();
        tab_two.setText("Settings")
                .setContentDescription("Options")
                .setTabListener(
                        new MyTabListener<SecondFragment>(
                                secondFragment));

        ThirdFragment thirdFragment = new ThirdFragment();
        tab_three
                .setText("About")
                .setContentDescription("About section")
                .setTabListener(
                        new MyTabListener<ThirdFragment>(
                                thirdFragment));
        //Adds The different tabs from above into the action bar
        actionBar.addTab(tab_one);
        actionBar.addTab(tab_two);
        actionBar.addTab(tab_three);

        if (savedInstanceState != null) {
            Log.i(TAG, "setting selected tab from saved bundle");
//            get the saved selected tab's index and set that tab as selected
            actionBar.setSelectedNavigationItem(savedInstanceState.getInt("tabIndex", 0));
        }


    }

}

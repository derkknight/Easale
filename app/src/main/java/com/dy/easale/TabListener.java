package com.dy.easale;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;

/**
 * Created by Derick Yung on 9/16/2014.
 */
public class TabListener implements ActionBar.TabListener {

    Fragment fragment;

    public TabListener(Fragment fragment)
    {
        this.fragment = fragment;
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        fragmentTransaction.replace(R.id.container, fragment);
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        fragmentTransaction.remove(fragment);
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }
}

package ie.sessionfinder;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBar.TabListener;


public class SessionTabListener implements ActionBar.TabListener{
    public Fragment fragment;

    public SessionTabListener(Fragment fragment) {
    this.fragment = fragment;
    }

    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) 
    {
    }

    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
    	ft.replace(R.id.fragment_container, fragment);
    }

    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) 
    {
    	ft.remove(fragment);
    }
} 


package ie.sessionfinder;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.TabListener;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;

public class MainActivity extends ActionBarActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);	
		setContentView(R.layout.main_activity);
		
		Tab searchTab = actionBar.newTab(); 
		searchTab.setText("Search");		
		Fragment searchFragment = new SessionListFragment();		
		searchTab.setTabListener(new SessionTabListener(searchFragment)); 
		actionBar.addTab(searchTab);
		
		Tab mapTab = actionBar.newTab(); 
		mapTab.setText("Map");		
		Fragment mapFragment = new SessionMapFragment();		
		mapTab.setTabListener(new SessionTabListener(mapFragment)); 
		actionBar.addTab(mapTab);

		Tab favouritesTab = actionBar.newTab(); 
		favouritesTab.setText("Favourites");		
		Fragment favouritesFragment = new FavouriteListFragment();		
		favouritesTab.setTabListener(new SessionTabListener(favouritesFragment)); 
		actionBar.addTab(favouritesTab);		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
		return false;
	}

}

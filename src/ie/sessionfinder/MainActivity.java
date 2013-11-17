package ie.sessionfinder;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);		
		Tab tabOne = actionBar.newTab(); 
		tabOne.setText(" First Tab")
			.setIcon(R.drawable.ic_launcher)
			.setContentDescription(" Tab the First")
			.setTabListener( new TabListener < MyFragment >
			(this, R.id.fragmentContainer, MyFragment.class)); actionBar.addTab( tabOne);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

package ie.sessionfinder;

import java.net.URLEncoder;
import java.util.ArrayList;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class SessionListFragment extends ListFragment implements SessionListHandler
{	
	MenuItem searchItem;
	MenuItem geoItem;
	SearchView searchView;
	
	@Override
    public View onCreateView(LayoutInflater inflator, ViewGroup container, Bundle bundle)
    {    	    	
		return inflator.inflate(R.layout.session_list, container, false);
    }
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
		setHasOptionsMenu(true);				
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch(item.getItemId()){
	        case R.id.menu_search:
	            searchView.setIconified(false);
	            return true;
	    }	 
	    return false;
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
		inflater.inflate(R.menu.session_list, menu);
		searchItem = menu.findItem(R.id.menu_search);
		geoItem = menu.findItem(R.id.menu_nearme);
		searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
		searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
			
			@Override
			public boolean onQueryTextSubmit(String arg0) {
				SessionSearchSubmitter submitter = new SessionSearchSubmitter(getActivity(), SessionListFragment.this);
				submitter.execute("search?q=" + URLEncoder.encode("" + searchView.getQuery()));
				return true;
			}
			
			@Override
			public boolean onQueryTextChange(String arg0) {
				// TODO Auto-generated method stub
				return false;
			}
		});					
	}

	@Override
	public void onSessionListReceived(ArrayList<Session> matches) {		
		if (matches.size() == 0)
	    {
	    	Toast.makeText(getActivity(), "No matches", Toast.LENGTH_SHORT);
	    	return;
	    }
		ArrayAdapter<Session> aa= new ArrayAdapter<Session>(getActivity(), android.R.layout.simple_list_item_1, matches);
		setListAdapter(aa);		
		aa.notifyDataSetChanged();
	}	
}

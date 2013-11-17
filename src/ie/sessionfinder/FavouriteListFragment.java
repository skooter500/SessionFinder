package ie.sessionfinder;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

public class FavouriteListFragment extends ListFragment implements SessionListHandler {
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
	}
	
	@Override
	public void onSessionListReceived(ArrayList<Session> matches) {
		// TODO Auto-generated method stub		
	}
}

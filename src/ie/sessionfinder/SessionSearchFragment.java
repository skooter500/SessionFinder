package ie.sessionfinder;

import org.tunepal.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SessionSearchFragment extends ListFragment 
{
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
	
}

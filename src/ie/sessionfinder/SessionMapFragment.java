package ie.sessionfinder;

import java.util.ArrayList;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.gms.maps.SupportMapFragment;


public class SessionMapFragment extends SupportMapFragment implements SessionListHandler, LocationListener {
	MenuItem geoItem;
	double latitude;
	double longitude;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
		setHasOptionsMenu(true);		
		LocationManager lm = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE); 
		boolean gps = false, network = false;
		try
		{
			gps=lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
        try
        {
        	network=lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        }
        catch(Exception ex)
        {
			ex.printStackTrace();        	
        }

        //don't start listeners if no provider is enabled
        if(! gps && ! network)
        {
            return;
        }
        
        if(gps)
        {
            lm.requestSingleUpdate(LocationManager.GPS_PROVIDER, this, null);
        }
        else if (network)
        {
            lm.requestSingleUpdate(LocationManager.NETWORK_PROVIDER, this, null);
        }            
	}
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
		inflater.inflate(R.menu.session_list, menu);
		geoItem = menu.findItem(R.id.menu_nearme);
	}
	@Override
	public void onSessionListReceived(ArrayList<Session> matches) {
		
		
	}
	@Override
	public void onLocationChanged(Location location) {
		longitude = location.getLongitude();
        latitude = location.getLatitude();
        SessionSearchSubmitter submitter = new SessionSearchSubmitter(getActivity(), this);
        //String params = "nearby?latlon=42.34891891,-71.15130615&radius=75";
        String params = "nearby?latlon=" + latitude + "," + longitude + "&radius=100";
        submitter.execute(params);
	}
	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}
}

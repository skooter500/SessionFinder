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
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class SessionMapFragment extends SupportMapFragment implements SessionListHandler, LocationListener {
	MenuItem geoItem;
	double latitude;
	double longitude;
	boolean gotAnUpdate;
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
        
        gotAnUpdate = false;
        if(gps)
        {
            lm.requestSingleUpdate(LocationManager.GPS_PROVIDER, this, null);
        }
        if (network)
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
		GoogleMap googleMap;
	    googleMap = getMap();
	    
	    if (matches.size() == 0)
	    {
	    	Toast.makeText(getActivity(), "No nearby sessions", Toast.LENGTH_SHORT).show();
	    	return;
	    }
	    
	    for(Session session:matches)
	    {
	    	LatLng latLng = new LatLng(session.latitude, session.longitude);
	        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
	        googleMap.addMarker(new MarkerOptions()
	                .position(latLng)
	                .title(session.venue)
	                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
	        googleMap.getUiSettings().setCompassEnabled(true);
	        googleMap.getUiSettings().setZoomControlsEnabled(true);
	        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
	    }
		
	}
	@Override
	public void onLocationChanged(Location location) {
		
		if (gotAnUpdate)
		{
			return;
		}
		gotAnUpdate = true;
		longitude = location.getLongitude();
        latitude = location.getLatitude();
        SessionSearchSubmitter submitter = new SessionSearchSubmitter(getActivity(), this);
        String params = "nearby?latlon=42.34891891,-71.15130615&radius=75";
        //String params = "nearby?latlon=" + latitude + "," + longitude + "&radius=75";
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

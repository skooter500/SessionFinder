package ie.sessionfinder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.text.Html;
import android.util.Log;

// Param 0 is the param to doInBackground
// Param 1 is the progress
// Param 2 is the Return type of doInBackground
public class SessionSearchSubmitter extends AsyncTask<String, Integer, String> {
	
	ArrayList<Session> matches = new ArrayList<Session>();
	ProgressDialog progressDialog;
	Context context;
	SessionListHandler handler;
	public SessionSearchSubmitter(Context context, SessionListHandler handler)
	{
		this.context = context;
		this.handler = handler;
	}
	
	@Override
	protected String doInBackground(String... params) {
		String baseURL =  "http://thesession.org/sessions/";
		URL url = null; 
		try
		{
			url = new URL(baseURL  + params[0] + "&format=json");
			Log.v("URL", "URL: " + url);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
	        String line;
	        StringBuffer sb = new StringBuffer();
	        while ((line = in.readLine()) != null)
	        {
	            sb.append(line);
	            sb.append("\n");
	        }
	        Log.v("SessionFinder: ", "" + sb);
	        
	        JSONObject json = new JSONObject("" + sb);
			JSONArray jsonArray = json.getJSONArray("sessions");
			
			matches.clear();
			for (int i = 0 ; i < jsonArray.length() ; i ++)
			{
				JSONObject jsonSession = jsonArray.getJSONObject(i);
				Session session = new Session();
				session.id = jsonSession.getInt("id");
				session.url = jsonSession.getString("url");
				session.latitude = jsonSession.getDouble("latitude");
				session.longitude = jsonSession.getDouble("latitude");
				session.town = "" + Html.fromHtml(jsonSession.getString("town"));
				session.area = "" + Html.fromHtml(jsonSession.getString("area"));
				session.country = "" + Html.fromHtml(jsonSession.getString("country"));								
				session.venue = "" + Html.fromHtml(jsonSession.getJSONObject("venue").getString("name"));
				session.telephone = "" + Html.fromHtml(jsonSession.getJSONObject("venue").getString("telephone"));
				session.email = "" + Html.fromHtml(jsonSession.getJSONObject("venue").getString("email"));
				matches.add(session);
			}
		}
		catch (Exception e)
		{
			Log.e("SessionFinder", "Fatal Error", e);
			return "Help!";
		}		
	    return null;
	}
	
	@Override
	protected void onPreExecute()
	{
		reshowProgress();
	}
	
	@Override 
	protected void onProgressUpdate( Integer... progress)
	{
		
	}
	
	@Override protected void onPostExecute(String result)
	{
		dismissProgress();
		handler.onSessionListReceived(matches);
	}
	
	public void dismissProgress()
	{
		if ((progressDialog != null) && (progressDialog.isShowing()))
		{
			progressDialog.dismiss();
		}
	}
	
	public void reshowProgress() {
		progressDialog = null;
		progressDialog = ProgressDialog.show(context, null, "Submitting...", true, true, new DialogInterface.OnCancelListener()
        {

            public void onCancel(DialogInterface arg0)
            {
                progressDialog.dismiss();
                cancel(true);
            }
        });		
	}
}

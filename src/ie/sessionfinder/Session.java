package ie.sessionfinder;

public class Session {
	int id;
	String url;
	String venue;
	String telephone;
	String email;
	double latitude;
	double longitude;
	String town;
	String area;
	String country;
	
	public String toString()
	{
		return venue + "\t" + latitude + "\t" + longitude;
	}
}

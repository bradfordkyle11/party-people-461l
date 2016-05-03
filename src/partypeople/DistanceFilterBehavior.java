package partypeople;

import java.util.ArrayList;
import java.util.List;

public class DistanceFilterBehavior implements FilterBehavior{

	private double radius;
	private double lat;
	private double lng;
	public DistanceFilterBehavior(String radius, String latlng){
		this.radius = Double.parseDouble(radius);
		latlng = latlng.replace("(", "");
		latlng = latlng.replace(")", "");
		String[] l = latlng.split(",");
		lat = Double.parseDouble(l[0]);
		lng = Double.parseDouble(l[1]);
	}
	
	@Override
	public List<Event> apply(List<Event> events) {
		// TODO Auto-generated method stub
		ArrayList<Event> filteredEvents = new ArrayList<Event>();
		for (Event event : events){
			if (haversine(lat, lng, event.getLatitude(), event.getLongitude())<=radius){
				filteredEvents.add(event);
			}
		}
		return filteredEvents;
	}
	
	private static double haversine(
	        double lat1, double lng1, double lat2, double lng2) {
	    int r = 3959; // average radius of the earth in mi
	    double dLat = Math.toRadians(lat2 - lat1);
	    double dLon = Math.toRadians(lng2 - lng1);
	    double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
	       Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) 
	      * Math.sin(dLon / 2) * Math.sin(dLon / 2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    double d = r * c;
	    return d;
	}

}

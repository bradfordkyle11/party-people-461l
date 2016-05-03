package partypeople;

import java.util.ArrayList;
import java.util.List;

public class LocationFilterBehavior implements FilterBehavior {
	String location;
	int radius;

	public LocationFilterBehavior(String location, int radius) {

	}

	@Override
	public List<Event> apply(List<Event> events) {
		// TODO Auto-generated method stub
		ArrayList<Event> filteredEvents = new ArrayList<Event>();
		for (Event e : events) {
			if (distanceFrom(e.getLocation()) <= radius) {
				if (!e.isPrivateEvent()) {
					filteredEvents.add(e);
				}
			}
		}
		return filteredEvents;
	}

	private double distanceFrom(String otherLocation) {
		// use Google Maps api to get distance between locations
		return 0;
	}

}

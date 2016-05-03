package partypeople;

import java.util.Collections;
import java.util.List;

public class SortingBehavior implements FilterBehavior {

	private int sortType;
	
	public SortingBehavior(int sortType){
		this.sortType = sortType;
	}
	@Override
	public List<Event> apply(List<Event> events) {
		for (Event event : events){
			event.setSortType(sortType);
		}
		Collections.sort(events);
		return events;
	}

}

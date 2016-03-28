package partypeople;

import java.util.ArrayList;
import java.util.List;

public class Filter {
	// these behaviors can filter out events
	private List<FilterBehavior> filterBehaviors;

	// this behavior will just reorder events
	private FilterBehavior sortingBehavior;

	public Filter() {
		filterBehaviors = new ArrayList<FilterBehavior>();
	}

	public void addFilterBehavior(FilterBehavior behavior) {
		filterBehaviors.add(behavior);
	}

	public void removeFilterBehavior(FilterBehavior behavior) {
		filterBehaviors.remove(behavior);
	}

	public List<Event> apply(List<Event> events) {
		List<Event> filteredEvents = new ArrayList<Event>(events);
		for (FilterBehavior filterBehavior : filterBehaviors) {
			filteredEvents = filterBehavior.apply(filteredEvents);
		}
		filteredEvents = sortingBehavior.apply(filteredEvents);
		return filteredEvents;
	}

	public FilterBehavior getSortingBehavior() {
		return sortingBehavior;
	}

	public void setSortingBehavior(FilterBehavior sortingBehavior) {
		this.sortingBehavior = sortingBehavior;
	}

}

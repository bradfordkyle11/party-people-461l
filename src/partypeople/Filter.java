package partypeople;

import java.util.ArrayList;
import java.util.List;

public class Filter {
	  //these behaviors can filter out events
	  public List<FilterBehavior> filterBehaviors;

	  //this behavior will just reorder events
	  public FilterBehavior sortingBehavior;

	  public Filter(){
	    filterBehaviors = new ArrayList<FilterBehavior>();
	  }
	  public void addFilterBehavior(FilterBehavior behavior){
	    filterBehaviors.add(behavior);
	  }
	  public List<Event> apply(List<Event> events){
	    List<Event> filteredEvents = new ArrayList<Event>(events);
	    for(FilterBehavior filterBehavior : filterBehaviors){
	      filteredEvents = filterBehavior.apply(filteredEvents);
	    }
	    filteredEvents = sortingBehavior.apply(filteredEvents);
	    return filteredEvents;
	  }
	}

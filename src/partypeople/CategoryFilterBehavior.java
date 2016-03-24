package partypeople;

import java.util.ArrayList;
import java.util.List;

public class CategoryFilterBehavior implements FilterBehavior {
	  private ArrayList<String> categories;

	  public CategoryFilterBehavior(ArrayList<String> categories){
	    this.categories = categories;
	  }

	  public List<Event> apply(List<Event> events){
	    ArrayList<Event> filteredEvents = new ArrayList<Event>();
	    for (Event e : events){
	      if(categories.contains(e.getCategory())){
	        filteredEvents.add(e);
	      }
	    }
	    return filteredEvents;
	  }
	}


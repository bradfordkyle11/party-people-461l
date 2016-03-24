package partypeople;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DateFilterBehavior implements FilterBehavior{
	  private Date startDate;
	  private Date endDate;

	  public DateFilterBehavior(Date startDate, Date endDate){
	    this.startDate = startDate;
	    this.endDate = endDate;
	  }
	  public List<Event> apply(List<Event> events){
	    ArrayList<Event> filteredEvents = new ArrayList<Event>();
	    for (Event e : events) {
	      if (!e.getDate().before(startDate)&&!e.getDate().after(endDate)){
	        filteredEvents.add(e);
	      }
	    }
	    return filteredEvents;
	  }
	}


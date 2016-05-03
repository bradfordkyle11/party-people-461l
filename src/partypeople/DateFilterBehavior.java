package partypeople;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateFilterBehavior implements FilterBehavior{
	  private Date startDate;
	  private Date endDate;

	  public DateFilterBehavior(Date startDate, Date endDate){
	    this.startDate = startDate;
	    this.endDate = endDate;
	  }
	  
	  public DateFilterBehavior(String startDate, String endDate){
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		
		//zero out dates
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(0);
		this.startDate = cal.getTime();
		this.endDate = cal.getTime();
		try {
			this.startDate = dateFormat.parse(startDate);
			this.endDate = dateFormat.parse(endDate);
			this.endDate.setHours(23);
			this.endDate.setMinutes(59);
			this.endDate.setSeconds(59);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	  }
	  public List<Event> apply(List<Event> events){
	    ArrayList<Event> filteredEvents = new ArrayList<Event>();
	    for (Event e : events) {
	    	if(e.getDate()!=null){
			      if (!e.getDate().before(startDate)&&!e.getDate().after(endDate)){
			        filteredEvents.add(e);
			      }
	    	}
	    }
	    return filteredEvents;
	  }
	}


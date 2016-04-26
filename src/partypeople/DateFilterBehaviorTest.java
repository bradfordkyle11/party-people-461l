package partypeople;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class DateFilterBehaviorTest {

	Date startDate;
	Date endDate;
	ArrayList<Event> allEvents = new ArrayList<Event>();
	ArrayList<Event> filteredEvents = new ArrayList<Event>();
	
	@Before
	public void setup(){
		Calendar start = Calendar.getInstance();
		start.setTimeInMillis(0);
		start.set(2016, 4, 26, 0, 0);
		startDate = start.getTime();
		Calendar end = Calendar.getInstance();
		end.setTimeInMillis(0);
		end.set(2016, 4, 29, 23, 59);
		endDate = end.getTime();
		
		
		Event a = new Event();
		Calendar aCal = Calendar.getInstance();
		aCal.setTimeInMillis(0);
		aCal.set(2016, 4, 25, 23, 59);
		a.setDate(aCal.getTime());
		Event b = new Event();
		Calendar bCal = Calendar.getInstance();
		bCal.setTimeInMillis(0);
		bCal.set(2016, 4, 26, 0, 0);
		b.setDate(bCal.getTime());
		Event c = new Event();
		Calendar cCal = Calendar.getInstance();
		cCal.setTimeInMillis(0);
		cCal.set(2016, 4, 27, 0, 0);
		c.setDate(cCal.getTime());
		Event d = new Event();
		Calendar dCal = Calendar.getInstance();
		dCal.setTimeInMillis(0);
		dCal.set(2016, 4, 29, 23, 59);
		d.setDate(dCal.getTime());
		Event e = new Event();
		Calendar eCal = Calendar.getInstance();
		eCal.setTimeInMillis(0);
		eCal.set(2016, 4, 30, 0, 0);
		e.setDate(eCal.getTime());
		
		allEvents.add(a);
		allEvents.add(b);
		allEvents.add(c);
		allEvents.add(d);
		allEvents.add(e);
		
		//these 3 events are within the filter range
		filteredEvents.add(b);
		filteredEvents.add(c);
		filteredEvents.add(d);
		
	}
	
	@Test
	public void test() {
		DateFilterBehavior dateFilter = new DateFilterBehavior(startDate, endDate);
		ArrayList<Event> mFilteredEvents = (ArrayList<Event>) dateFilter.apply(allEvents);
		assertTrue(mFilteredEvents.equals(filteredEvents));
	}

}

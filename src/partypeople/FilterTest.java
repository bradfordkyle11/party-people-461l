	package partypeople;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class FilterTest {
	Date startDate;
	Date endDate;
	ArrayList<Event> correct = new ArrayList<Event>();
	ArrayList<String> categories = new ArrayList<String>();
	ArrayList<Event> events = new ArrayList<Event>();
	Filter f = new Filter();
	@Before
	public void setup() {
		String a = "a";
		String b = "b";
		String c = "c";
		String d = "d";
		Calendar start = Calendar.getInstance();
		start.setTimeInMillis(0);
		start.set(2016, 4, 25, 12, 0);
		startDate = start.getTime();
		Calendar end = Calendar.getInstance();
		end.setTimeInMillis(0);
		end.set(2016, 4, 27, 12, 0);
		endDate = end.getTime();
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTimeInMillis(0);
		cal2.setTimeInMillis(0);
		cal1.set(2016, 4, 26, 15, 0);
		cal2.set(2016, 4, 27, 15, 0);
		Date date1 = cal1.getTime();
		Date date2 = cal2.getTime();
		Event a1 = new Event();
		a1.setCategory(a);
		a1.setDate(date1);
		Event a2 = new Event();
		a2.setCategory(a);
		a2.setDate(date2);
		Event a3 = new Event();
		a3.setCategory(a);
		a3.setDate(date1);
		Event a4 = new Event();
		a4.setCategory(a);
		a4.setDate(date2);
		Event b1 = new Event();
		b1.setCategory(b);
		b1.setDate(date1);
		Event b2 = new Event();
		b2.setCategory(b);
		b2.setDate(date2);
		Event b3 = new Event();
		b3.setCategory(b);
		b3.setDate(date1);
		Event b4 = new Event();
		b4.setCategory(b);
		b4.setDate(date2);
		Event c1 = new Event();
		c1.setCategory(c);
		c1.setDate(date1);
		Event c2 = new Event();
		c2.setCategory(c);
		c2.setDate(date2);
		Event c3 = new Event();
		c3.setCategory(c);
		c3.setDate(date1);
		Event c4 = new Event();
		c4.setCategory(c);
		c4.setDate(date2);
		Event d1 = new Event();
		d1.setCategory(d);
		d1.setDate(date1);
		Event d2 = new Event();
		d2.setCategory(d);
		d2.setDate(date2);
		Event d3 = new Event();
		d3.setCategory(d);
		d3.setDate(date1);
		Event d4 = new Event();
		d4.setCategory(d);
		d4.setDate(date2);
		events.add(a1);
		events.add(a2);
		events.add(a3);
		events.add(a4);
		events.add(b1);
		events.add(b2);
		events.add(b3);
		events.add(b4);
		events.add(c1);
		events.add(c2);
		events.add(c3);
		events.add(c4);
		events.add(d1);
		events.add(d2);
		events.add(d3);
		events.add(d4);
		correct.add(b1);
		correct.add(b3);
		correct.add(d1);
		correct.add(d3);
	}
	
	
	@Test
	public void test() {
		categories.add("b");
		categories.add("d");
		f.addFilterBehavior(new CategoryFilterBehavior(categories));
		f.addFilterBehavior(new DateFilterBehavior(startDate, endDate));
		assertEquals(16, events.size());
		events = (ArrayList<Event>) f.apply(events);
		assertTrue(events.equals(correct));
	}

}

package partypeople;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EventTest {
	ArrayList<Event> sort = new ArrayList<Event>();
	ArrayList<Event> correct = new ArrayList<Event>();
	
	
	@Before
	public void setup() {
		Calendar a = Calendar.getInstance();
		a.setTimeInMillis(0);
		a.set(2016, 4, 27, 19, 0);
		Date futureDate = a.getTime();
		Event e1 = new Event();
		e1.setDate(futureDate);
		e1.setName("Alpha Beta Kappa");
		e1.setPrice(10);
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		a.set(2016, 4, 27, 18, 0);
		futureDate = a.getTime();
		Event e2 = new Event();
		e2.setDate(futureDate);
		e2.setName("Alpha Alpha Kappa");
		e2.setPrice(0);
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		a.set(2016, 4, 26, 5, 0);
		futureDate = a.getTime();
		Event e3 = new Event();
		e3.setDate(futureDate);
		e3.setName("Beta Alpha Kappa");
		e3.setPrice(5);
		sort.add(e1);
		sort.add(e2);
		sort.add(e3);
	}
	
	@Test
	public void SOONEST_DATEtest() {
		for (int i = 0; i < sort.size(); i++) {
			sort.get(i).setSortType(0);
		}
		correct.add(sort.get(2));
		correct.add(sort.get(1));
		correct.add(sort.get(0));
		Collections.sort(sort);
		assertTrue(sort.equals(correct));
	}
	
	@Test
	public void ALPHABETICALtest() {
		for (int i = 0; i < sort.size(); i++) {
			sort.get(i).setSortType(1);
		}
		correct.add(sort.get(1));
		correct.add(sort.get(0));
		correct.add(sort.get(2));
		Collections.sort(sort);
		assertTrue(sort.equals(correct));
	}
	
	@Test
	public void TIME_CREATEDtest() {
		for (int i = 0; i < sort.size(); i++) {
			sort.get(i).setSortType(3);
		}
		correct.add(sort.get(0));
		correct.add(sort.get(1));
		correct.add(sort.get(2));
		Collections.sort(sort);
		assertTrue(sort.equals(correct));
	}
	
	@Test
	public void PRICEtest() {
		for (int i = 0; i < sort.size(); i++) {
			sort.get(i).setSortType(4);
		}
		correct.add(sort.get(1));
		correct.add(sort.get(2));
		correct.add(sort.get(0));
		Collections.sort(sort);
		assertTrue(sort.equals(correct));
	}
	
	@After
	public void clean() {
		sort.clear();
		correct.clear();
	}

}

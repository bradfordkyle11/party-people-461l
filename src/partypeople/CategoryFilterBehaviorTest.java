package partypeople;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CategoryFilterBehaviorTest {
	ArrayList<String> categories = new ArrayList<String>();
	ArrayList<Event> events = new ArrayList<Event>();
	@Before
	public void setup() {
		String a = "a";
		String b = "b";
		String c = "c";
		String d = "d";
		Event a1 = new Event();
		a1.setCategory(a);
		Event a2 = new Event();
		a2.setCategory(a);
		Event a3 = new Event();
		a3.setCategory(a);
		Event a4 = new Event();
		a4.setCategory(a);
		Event b1 = new Event();
		b1.setCategory(b);
		Event b2 = new Event();
		b2.setCategory(b);
		Event b3 = new Event();
		b3.setCategory(b);
		Event b4 = new Event();
		b4.setCategory(b);
		Event c1 = new Event();
		c1.setCategory(c);
		Event c2 = new Event();
		c2.setCategory(c);
		Event c3 = new Event();
		c3.setCategory(c);
		Event c4 = new Event();
		c4.setCategory(c);
		Event d1 = new Event();
		d1.setCategory(d);
		Event d2 = new Event();
		d2.setCategory(d);
		Event d3 = new Event();
		d3.setCategory(d);
		Event d4 = new Event();
		d4.setCategory(d);
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
		
	}
	
	@Test
	public void singleCategoryTest() {
		categories.add("a");
		CategoryFilterBehavior a = new CategoryFilterBehavior(categories);
		assertEquals(16, events.size());
		events = (ArrayList<Event>) a.apply(events);
		for (int i = 0; i < events.size(); i++) {
			assertTrue(events.get(i).getCategory() == "a");
		}
	}
	
	@Test
	public void multipleCategoryTest() {
		categories.add("d");
		categories.add("b");
		CategoryFilterBehavior a = new CategoryFilterBehavior(categories);
		assertEquals(16, events.size());
		events = (ArrayList<Event>) a.apply(events);
		for (int i = 0; i < events.size(); i++) {
			assertTrue(events.get(i).getCategory() == "b" || events.get(i).getCategory() == "d");
		}
	}
	
	@After
	public void clean() {
		events.clear();
		categories.clear();
	}
	
}

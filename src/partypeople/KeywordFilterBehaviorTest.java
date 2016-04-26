package partypeople;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.google.appengine.api.users.User;

public class KeywordFilterBehaviorTest {

	ArrayList<Event> events;
	ArrayList<Event> correct1;
	ArrayList<Event> correct2;
	ArrayList<Event> correct3;
	@Before
	public void setup(){
		PartyPeopleUser fakeUser = new PartyPeopleUser(new User("fake@email.com", "gmail.com"));
		
		Event e1 = new Event();
		e1.setCategory("Social");
		e1.setDescription("Anyone is welcome! This is just a fun social get-together. Please bring an item on the list if you plan to come.");
		e1.setLocation("101 Fake Street St., Austin, TX");
		e1.setName("Johnny's Party");
		e1.setOwner(fakeUser);
		
		Event e2 = new Event();
		e2.setCategory("Music");
		e2.setDescription("Hey guys, come join us for a free party and concert. Food and drinks provided.");
		e2.setLocation("102 Fake Street St., Austin, TX");
		e2.setName("Concert");
		e2.setOwner(fakeUser);
		
		Event e3 = new Event();
		e3.setCategory("Graduation");
		e3.setDescription("Come celebrate my college graduation.");
		e3.setLocation("103 Fake Street St., Austin, TX");
		e3.setName("Johnny's Graduation");
		e3.setOwner(fakeUser);
		
		events = new ArrayList<Event>();
		events.add(e1);
		events.add(e2);
		events.add(e3);
		
		correct1 = new ArrayList<Event>();
		correct1.add(e1);
		correct1.add(e3);
		
		correct2 = new ArrayList<Event>();
		correct2.add(e1);
		correct2.add(e2);
		correct2.add(e3);
		
		correct3 = new ArrayList<Event>();
		correct3.add(e2);
		
	}
	
	
	@Test
	public void test() {
		KeywordFilterBehavior kfb = new KeywordFilterBehavior("Johnny");
		ArrayList<Event> l = (ArrayList<Event>) kfb.apply(events);
		assertTrue(l.equals(correct1));
		
		kfb = new KeywordFilterBehavior("fake street");
		l = (ArrayList<Event>) kfb.apply(events);
		assertTrue(l.equals(correct2));
		
		kfb = new KeywordFilterBehavior("concert");
		l = (ArrayList<Event>) kfb.apply(events);
		assertTrue(l.equals(correct3));
		
		kfb = new KeywordFilterBehavior("fun food graduation");
		l = (ArrayList<Event>) kfb.apply(events);
		assertTrue(l.isEmpty());
	}

}

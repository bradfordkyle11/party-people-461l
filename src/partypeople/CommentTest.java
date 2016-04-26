package partypeople;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

import com.google.appengine.api.users.User;

public class CommentTest {

	ArrayList<Comment> list1 = new ArrayList<Comment>();
	ArrayList<Comment> list2 = new ArrayList<Comment>();
	@Before
	public void setup() {
		PartyPeopleUser a = new PartyPeopleUser(new User("test@gmail.com" , "gmail.com"));
		String c0 = "1";
		String c1 = "2";
		String c2 = "3";
		String c3 = "4";
		Comment compare = new Comment(c0, a);
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Comment compare1 = new Comment(c1, a);
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Comment compare2 = new Comment(c2, a);
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Comment compare3 = new Comment(c3, a);
		list1.add(compare3);
		list1.add(compare2);
		list1.add(compare1);
		list1.add(compare);
		list2.add(compare2);
		list2.add(compare);
		list2.add(compare3);
		list2.add(compare1);
	}
	
	@Test
	public void sortTest() {
		Collections.sort(list2);
		assertTrue(list1.equals(list2));
	}

}

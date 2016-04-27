package partypeople;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.junit.Before;
import org.junit.Test;

import com.google.appengine.api.users.User;

public class ObserverTest {
	ArrayList<Event> events = new ArrayList<Event>();
	PartyPeopleUser c = new PartyPeopleUser(new User("test3@gmail.com", "gmail.com"));
	
	@Before
	public void setUp() {
		TestUser a = new TestUser(new User("test@gmail.com", "gmail.com"));
		TestUser b = new TestUser(new User("test2@gmail.com", "gmail.com"));

		Event observable = new Event();
		observable.setName("Wrong Party");
		observable.setOwner(c);
		observable.setLocation("123 Wrong St");
		Calendar setDate = Calendar.getInstance();
		setDate.setTimeInMillis(0);
		setDate.set(2016, 4, 26, 5, 0);
		observable.setDate(setDate.getTime());
		observable.setPrice(0);
		observable.addObserver(a);
		observable.addObserver(b);
		events.add(observable);
	}

	@Test
	public void singleChangeTest() {
		System.out.println("-------------------SINGLE CHANGE TEST-------------------");
		Event newEvent = new Event();
		newEvent.setName("Right Party");
		events.get(0).updateEvent(newEvent);
	}
	
	@Test
	public void multipleChangeTest() {
		System.out.println("-------------------MULTIPLE CHANGE TEST-------------------");
		Event newEvent = new Event();
		PartyPeopleUser a = new PartyPeopleUser(new User("testest@gmail.com", "gmail.com"));
		newEvent.setName("Right Party");
		newEvent.setOwner(a);
		newEvent.setLocation("123 Right St");
		Calendar setDate = Calendar.getInstance();
		setDate.setTimeInMillis(0);
		setDate.set(2016, 4, 26, 6, 0);
		newEvent.setDate(setDate.getTime());
		newEvent.setPrice(10);
		events.get(0).updateEvent(newEvent);
	}
	
	@Test
	public void noActualChangeTest() {
		System.out.println("-------------------NOTHING CHANGED TEST-------------------");
		Event observable = new Event();
		observable.setName("Wrong Party");
		observable.setOwner(c);
		observable.setLocation("123 Wrong St");
		Calendar setDate = Calendar.getInstance();
		setDate.setTimeInMillis(0);
		setDate.set(2016, 4, 26, 5, 0);
		observable.setDate(setDate.getTime());
		observable.setPrice(0);
		events.get(0).updateEvent(observable);
	}
	
	@Test
	public void nullEventTest() {
		System.out.println("-------------------NULL EVENT TEST-------------------");
		events.get(0).updateEvent(new Event());
	}
	
	public class TestUser implements Observer {
			private User googleUser;
			
			public TestUser(User googleUser){
				this.googleUser = googleUser;
			}

			@Override
			public void update(Observable event, Object ob) {
				Event ev = (Event) event;
				ArrayList<String> changed = (ArrayList<String>) ob;
				String msgBody = "The event " + changed.get(0)
						+ " has been changed. The following item(s) changed:\r\n";
				for (int i = 1; i < changed.size(); i++) {
					msgBody += changed.get(i) + "\r\n";
				}
				msgBody += "View changes at <link to event>";
				try {
					Properties props = new Properties();
					Session session = Session.getDefaultInstance(props, null);
					Message msg = new MimeMessage(session);
					msg.setFrom(new InternetAddress("email@appspot.com", "Party People"));
					msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
							googleUser.getEmail(), googleUser.getNickname()));
					msg.setSubject("One of your attending events has changed");
					msg.setText(msgBody);
					System.out.println(msg.getSubject());
					System.out.println(msgBody);
					
					
				} catch (AddressException e) {
					// ...
				} catch (MessagingException e) {
					// ...
				} catch (Exception e) {
					// ...
				}

			}
			
			public String toString(){
				return googleUser.getNickname();
			}
		

	}

}

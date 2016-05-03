package partypeople;

import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.List;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.google.appengine.api.users.User;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Load;

@Entity
public class PartyPeopleUser implements PartyPeopleObserver {
	private static final Logger log = Logger.getLogger(PartyPeopleUser.class
			.getName());
	private User googleUser;
	@Load
	private List<Ref<Event>> myAttending;
	@Load
	private List<Ref<Event>> myCreated;
	@Id
	Long id;

	public PartyPeopleUser(User googleUser) {
		this.googleUser = googleUser;
		myAttending = new ArrayList<Ref<Event>>();
		myCreated = new ArrayList<Ref<Event>>();
	}

	public PartyPeopleUser() {
		this.googleUser = new User("guest", "gmail.com");
		myAttending = new ArrayList<Ref<Event>>();
		myCreated = new ArrayList<Ref<Event>>();
	}

	@Override
	public void update(PartyPeopleObservable event, Object ob) {
		Event ev = (Event) event;
		log.info(ev.getName() + " is the new event name");
		ArrayList<String> changed = (ArrayList<String>) ob;
		String msgBody = "The event " + changed.get(0)
				+ " has been changed. The following item(s) changed:\r\n";
		for (int i = 1; i < changed.size(); i++) {
			msgBody += changed.get(i) + "\r\n";
		}
		try {
			Properties props = new Properties();
			Session session = Session.getDefaultInstance(props, null);
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(
					"Admin@party-people-461l.appspotmail.com", "Party People"));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
					googleUser.getEmail(), googleUser.getNickname()));
			msg.setSubject("One of your attending events has changed");
			msg.setText(msgBody);
			Transport.send(msg);
			log.info(msgBody + " is the body of the text");
			log.info(googleUser.getEmail() + " is the email address");
			log.info(googleUser.getNickname() + " is the nickname");
		} catch (AddressException e) {
			// ...
		} catch (MessagingException e) {
			// ...
		} catch (Exception e) {
			// ...
		}

	}

	public Long getId() {
		return id;
	}

	public String toString() {
		return googleUser.getNickname();
	}

	public User getGoogleUser() {
		return googleUser;
	}

	public void setGoogleUser(User googleUser) {
		this.googleUser = googleUser;
	}

	public void addCreated(Event event) {
		myCreated.add(Ref.create(event));
	}

	public List<Event> getCreated() {
		List<Event> created = new ArrayList<Event>();
		if (this.myCreated != null) {

			for (Ref<Event> event : myCreated) {
				Event temp = event.getValue();
				if (temp != null) {
					created.add(temp);
				}
			}
		}
		return created;
	}

	public void removeCreated(Event event) {
		myCreated.remove(Ref.create(event));
	}

	public void addAttending(Event event) {
		myAttending.add(Ref.create(event));
	}

	public void removeAttending(Event event) {
		myAttending.remove(Ref.create(event));
	}

	public List<Event> getAttending() {
		List<Event> attending = new ArrayList<Event>();
		if (this.myAttending != null) {
			for (Ref<Event> event : myAttending) {
				Event temp = event.getValue();
				if (temp != null) {
					attending.add(temp);
				}
			}
		}
		return attending;
	}

	public boolean equals(PartyPeopleUser other) {
		return this.googleUser.getEmail().equals(
				other.getGoogleUser().getEmail());
	}
}

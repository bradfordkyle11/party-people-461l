package partypeople;

import java.util.ArrayList;
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

import com.google.appengine.api.users.User;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class PartyPeopleUser {
	private User googleUser;
	private List<Ref<Event>> myAttending;
	private List<Ref<Event>> myCreated;
	@Id Long id;
	
	public PartyPeopleUser(User googleUser){
		this.googleUser = googleUser;
		myAttending = new ArrayList<Ref<Event>>();
		myCreated = new ArrayList<Ref<Event>>();
	}
	
	public PartyPeopleUser(){
		this.googleUser = new User("default@email.com", "gmail.com");
		myAttending = new ArrayList<Ref<Event>>();
		myCreated = new ArrayList<Ref<Event>>();
	}

/*	@Override
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
			Transport.send(msg);
		} catch (AddressException e) {
			// ...
		} catch (MessagingException e) {
			// ...
		} catch (Exception e) {
			// ...
		}

	}
	*/
	
	public String toString(){
		return googleUser.getNickname();
	}

	public User getGoogleUser() {
		return googleUser;
	}

	public void setGoogleUser(User googleUser) {
		this.googleUser = googleUser;
	}
	
	public void addCreated(Event event){
		myCreated.add(Ref.create(event));
	}
	public void addAttending(Event event){
		myAttending.add(Ref.create(event));
	}
}

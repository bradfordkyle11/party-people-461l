package partypeople;

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

public class PartyPeopleUser implements Observer {
	private User googleUser;
	private List<Event> myAttending;
	private List<Event> myCreated;

	@Override
	public void update(Observable event, Object ob) {
		Event ev = (Event) event;
		String msgBody = "The event " + ev.getName()
				+ " has been changed, view change at <link to event>";
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
}

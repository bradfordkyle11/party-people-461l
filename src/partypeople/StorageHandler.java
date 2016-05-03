package partypeople;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.google.appengine.api.users.User;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Ref;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class StorageHandler {
	static {
		ObjectifyService.register(Event.class);
		ObjectifyService.register(PartyPeopleUser.class);
		ObjectifyService.register(Item.class);
		ObjectifyService.register(Comment.class);
	}
	
	public static void save(Object o){
		ofy().save().entity(o).now();
	}
	
	public static void delete(Object o){
		ofy().delete().entity(o).now();
	}
	public static void delete(Comment c){
		c.getAssociatedEvent().removeComment(c);
		ofy().delete().entity(c).now();
	}
	public static void delete(Event e){
		//e.prepareForDelete();
		e.getOwner().removeCreated(e);
		save(e.getOwner());

		for (PartyPeopleUser attendee : e.getAttending()) {
			attendee.removeAttending(e);
			save(attendee);
		}

		for (Item item : e.getItemsNeeded()) {
			delete(item);
		}
		for (Comment comment : e.getComments()) {
			delete(comment);
		}

		ofy().delete().entity(e).now();
	}
	
	public static PartyPeopleUser getUser(User user){
        ObjectifyService.register(PartyPeopleUser.class);
        List<PartyPeopleUser> users = ObjectifyService.ofy().load().type(PartyPeopleUser.class).list();
        for (PartyPeopleUser partyPeopleUser : users){
        	if (partyPeopleUser.getGoogleUser().equals(user)){
        		return partyPeopleUser;
        	}
        }
        return null;
        
	}
	
	public static List<Event> loadEvents(){
		ObjectifyService.register(Event.class);
		List<Event> stored = ObjectifyService.ofy().load().type(Event.class).list();
		Date date;
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(0);
		date = cal.getTime();
		date.setHours(0);
		Date current = new Date();
		date.setDate(current.getDate());
		date.setMonth(current.getMonth());
		date.setYear(current.getYear());
		ArrayList<Event> result = new ArrayList<Event>();
		for (Event event : stored){
			if (!event.getDate().before(date)){
				result.add(event);
			}
		}
		return result;
	}
	
	public static Event findEventById(long id){
		return ObjectifyService.ofy().load().type(Event.class).id(id).getValue();
	}
	
	public static Comment findCommentById(long id){
		return ObjectifyService.ofy().load().type(Comment.class).id(id).getValue();
	}
	
	public static Item findItemById(Long id){
		return ObjectifyService.ofy().load().type(Item.class).id(id).getValue();
	}
}

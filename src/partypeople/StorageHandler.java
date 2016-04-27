package partypeople;

import java.util.List;

import com.google.appengine.api.users.User;
import com.googlecode.objectify.ObjectifyService;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class StorageHandler {
	static {
		ObjectifyService.register(Event.class);
	}
	
	public static void save(Object o){
		ofy().save().entity(o).now();
	}
	
	public static void delete(Object o){
		ofy().delete().entity(o).now();
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
		List<Event> result = ObjectifyService.ofy().load().type(Event.class).list();
		return result;
	}
}

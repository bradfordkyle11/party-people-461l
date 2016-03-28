package partypeople;

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
}

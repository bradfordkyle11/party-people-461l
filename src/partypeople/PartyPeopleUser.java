package partypeople;

import java.util.List;

import com.google.appengine.api.users.User;

public class PartyPeopleUser {
	private User googleUser;
	private List<Event> myAttending;
	private List<Event> myCreated;
}

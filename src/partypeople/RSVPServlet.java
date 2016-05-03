package partypeople;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class RSVPServlet extends HttpServlet{

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		
		PartyPeopleUser attendee = StorageHandler.getUser(user);
		
		String id = (String) request.getParameter("event-id");
		Event event = StorageHandler.findEventById(Long.parseLong(id));
		
		if (request.getParameter("rsvp?").equals("true")){
			event.addAttendee(attendee);
			attendee.addAttending(event);
		}
		else {
			event.removeAttendee(attendee);
			attendee.removeAttending(event);
		}
		
		StorageHandler.save(event);
		StorageHandler.save(attendee);
		
		response.sendRedirect(request.getHeader("referer"));
	}
}

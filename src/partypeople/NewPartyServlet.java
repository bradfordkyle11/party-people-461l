package partypeople;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.googlecode.objectify.ObjectifyService.ofy;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.ObjectifyService;

public class NewPartyServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		
		PartyPeopleUser owner = StorageHandler.getUser(user);
		String latlong = request.getParameter("latlong");
		//remove the parenthesis from the string
		double latitude = 0;
		double longitude = 0;
		if (latlong!=""){
			latlong = latlong.replace("(", "");
			latlong = latlong.replace(")", "");
			String[] latlng = latlong.split(",");
			latitude = Double.parseDouble(latlng[0]);
			longitude = Double.parseDouble(latlng[1]);
		}
		String name = request.getParameter("party-name");
		String description = request.getParameter("description");
		String category = request.getParameter("category");
		String date = request.getParameter("date");
		if(date==null){
			date = "";
		}
		String time = request.getParameter("time");
		String location = request.getParameter("location");
		String publicOrPrivate = request.getParameter("public-or-private");
		String password = request.getParameter("password");
		if (password==null){
			password = "";
		}
		String price = request.getParameter("price");
		String itemsNeeded = request.getParameter("items-needed");

		Event newEvent = new Event(owner, name, description, category, date,
				time, location, latitude, longitude, publicOrPrivate, password, price, itemsNeeded);
		StorageHandler.save(newEvent);
		owner.addCreated(newEvent);
		StorageHandler.save(owner);

		response.sendRedirect("/index.jsp");
	}
}

package partypeople;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class ItemSignUpServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();

		PartyPeopleUser attendee = StorageHandler.getUser(user);

		String numberOfItems = request.getParameter("num-items");
		int numItems = 0;
		if (!numberOfItems.equals("")) {
			numItems = Integer.parseInt(numberOfItems);
		}

		for (int i = 0; i < numItems; i++) {
			if (request.getParameter("item" + String.valueOf(i)) != null) {
				if (request.getParameter("id" + String.valueOf(i)) != null) {
					Long id = Long.parseLong(request.getParameter("id"
							+ String.valueOf(i)));
					Item item = StorageHandler.findItemById(id);
					if (request.getParameter("sign-up").equals("true")) {
						item.setUser(attendee);
						StorageHandler.save(item);
					}
					else {
						item.removeUser();
						StorageHandler.save(item);
					}
				}
			}
		}

		response.sendRedirect(request.getHeader("referer"));
	}
}

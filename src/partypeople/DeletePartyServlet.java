package partypeople;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class DeletePartyServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		String id = (String) request.getParameter("event-id");
		Event event = StorageHandler.findEventById(Long.parseLong(id));
		StorageHandler.delete(event);
		
		response.sendRedirect(request.getHeader("referer"));
	}
}

package partypeople;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestEditPageServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String id = (String) request.getParameter("event-id");
		Event event = StorageHandler.findEventById(Long.parseLong(id));
		request.setAttribute("event", event);

		request.getRequestDispatcher("new-party-event.jsp").forward(request, response);
	}
}

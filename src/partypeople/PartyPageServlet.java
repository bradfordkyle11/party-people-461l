package partypeople;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PartyPageServlet extends HttpServlet{

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String id = (String) request.getParameter("event-id");
		Event event = StorageHandler.findEventById(Long.parseLong(id));
		request.setAttribute("event", event);

		request.getRequestDispatcher("event-page.jsp").forward(request, response);
	}
}

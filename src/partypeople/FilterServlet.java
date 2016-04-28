package partypeople;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FilterServlet extends HttpServlet {
	private final ArrayList<String> categories = new ArrayList<String>(
			Arrays.asList("Birthday", "Graduation", "Sports", "Holiday",
					"Social", "Music", "Pool Party", "Other"));

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		Filter filter = new Filter();
		// get the categories to filter with
		ArrayList<String> desiredCategories = new ArrayList<String>();
		for (String c : categories) {
			String category = request.getParameter(c);
			// checkboxes only returned as parameters if they are checked
			if (category != null) {
				desiredCategories.add(c);
			}
		}
		if (desiredCategories.size() == 0) {
			desiredCategories = null;
		}
		if (desiredCategories != null) {
			filter.addFilterBehavior(new CategoryFilterBehavior(
					desiredCategories));
		}

		// create the date filter
		if (request.getParameter("start") != null) {
			if (!request.getParameter("start").equals("")) {
				filter.addFilterBehavior(new DateFilterBehavior(request
						.getParameter("start"), request.getParameter("end")));
			}
		}

		//create the keyword filter
		if (request.getParameter("query") != null) {
			if (!request.getParameter("query").equals("")) {
				filter.addFilterBehavior(new KeywordFilterBehavior(request
						.getParameter("query")));
			}
		}
		
		//return the filter
		request.setAttribute("filter", filter);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
}

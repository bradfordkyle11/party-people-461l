package partypeople;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class CommentServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		
		PartyPeopleUser commenter = StorageHandler.getUser(user);
		
		String id = request.getParameter("event-id");
		Event event = StorageHandler.findEventById(Long.parseLong(id));
		String content = request.getParameter("comment-content");
			
		if (request.getParameter("action").equals("post")){
			Comment comment = new Comment(content, commenter, event);
			StorageHandler.save(comment);
			event.addComment(comment);
		}
		else if (request.getParameter("action").equals("delete")) {
			String commentId = request.getParameter("comment-id");
			if(commentId!=null&&commentId!=""){
				Comment comment = StorageHandler.findCommentById(Long.parseLong(commentId));
				StorageHandler.delete(comment);
			}
		}
		StorageHandler.save(event);
		
		response.sendRedirect(request.getHeader("referer"));
	}
}

package partypeople;

import java.util.Date;

public class Comment {
	private String content;
	private PartyPeopleUser commenter;
	private Date timePosted;
	
	public Comment(String content, PartyPeopleUser commenter){
		this.content = content;
		this.commenter = commenter;
		this.timePosted = new Date();
	}
}

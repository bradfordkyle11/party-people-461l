package partypeople;

import java.util.Date;

public class Comment implements Comparable {
	private String content;
	private PartyPeopleUser commenter;
	private Date timePosted;
	
	public Comment(String content, PartyPeopleUser commenter){
		this.content = content;
		this.commenter = commenter;
		this.timePosted = new Date();
	}

	@Override
	public int compareTo(Object o) {
		//comments will just be sorted by time posted
		Comment other = (Comment) o;
		return timePosted.compareTo(other.getTimePosted());
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public PartyPeopleUser getCommenter() {
		return commenter;
	}

	public void setCommenter(PartyPeopleUser commenter) {
		this.commenter = commenter;
	}

	public Date getTimePosted() {
		return timePosted;
	}

	public void setTimePosted(Date timePosted) {
		this.timePosted = timePosted;
	}
	
}

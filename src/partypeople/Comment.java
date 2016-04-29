package partypeople;

import java.util.Date;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Load;

@Entity
public class Comment implements Comparable<Comment> {
	private String content;
	private Ref<PartyPeopleUser> commenter;
	private Date timePosted;
	@Load private Ref<Event> associatedEvent;
	@Id Long id;
	public Comment(){
		this.content = "";
		this.timePosted = new Date();
	}
	
	public Comment(String content){
		this.content = content;
		this.timePosted = new Date();
	}
	public Comment(String content, PartyPeopleUser commenter){
		this.content = content;
		this.commenter = Ref.create(commenter);
		this.timePosted = new Date();
		this.associatedEvent = null;
	}
	public Comment(String content, PartyPeopleUser commenter, Event associatedEvent){
		this.content = content;
		this.commenter = Ref.create(commenter);
		this.timePosted = new Date();
		this.associatedEvent = Ref.create(associatedEvent);
	}

	public Event getAssociatedEvent() {
		return associatedEvent.safeGet();
	}
	public void setAssociatedEvent(Event associatedEvent) {
		this.associatedEvent = Ref.create(associatedEvent);
	}
	@Override
	public int compareTo(Comment o) {
		//comments will just be sorted by time posted
		return o.getTimePosted().compareTo(timePosted);
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getId() {
		return id;
	}

	public PartyPeopleUser getCommenter() {
		return commenter.getValue();
	}

	public void setCommenter(PartyPeopleUser commenter) {
		this.commenter = Ref.create(commenter);
	}

	public Date getTimePosted() {
		return timePosted;
	}

	public void setTimePosted(Date timePosted) {
		this.timePosted = timePosted;
	}
	
	public boolean equals(Comment other){
		return this.id == other.getId();
	}
	
}

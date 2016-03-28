package partypeople;

import java.util.Date;
import java.util.List;
import java.util.Observable;

import com.google.appengine.api.users.User;

public class Event extends Observable {
	private String name;
	private PartyPeopleUser owner;
	private List<PartyPeopleUser> attending;
	private String location;
	private Date date;
	private String description;
	private List<Comment> comments;
	private boolean privateEvent;
	private String password;
	private double price;
	private List<String> itemsNeeded;
	private String category;
	//private Image picture;
	
	public void updateEvent(Event newEvent){
		this.name = newEvent.getName();
		this.owner = newEvent.getOwner();
		this.attending = newEvent.getAttending();
		this.location = newEvent.getLocation();
		this.date = newEvent.getDate();
		this.description = newEvent.getDescription();
		this.comments = newEvent.getComments();
		this.privateEvent = newEvent.isPrivateEvent();
		this.password = newEvent.getPassword();
		this.price = newEvent.getPrice();
		this.itemsNeeded = newEvent.getItemsNeeded();
		this.category = newEvent.getCategory();
		
		//figure out what changed and turn it into a string to 
		//pass to the observers, so it can be emailed.
		notifyObservers();
	}
	
	public void addAttendee(PartyPeopleUser u){
		attending.add(u);
	}
	
	public void removeAttendee(PartyPeopleUser u){
		attending.remove(u);
	}
	
	public void addComment(String content, PartyPeopleUser commenter){
		comments.add(new Comment(content, commenter));
	}
	
	public void removeComment(Comment comment){
		comments.remove(comment);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public PartyPeopleUser getOwner() {
		return owner;
	}
	public void setOwner(PartyPeopleUser owner) {
		this.owner = owner;
	}
	public List<PartyPeopleUser> getAttending() {
		return attending;
	}
	public void setAttending(List<PartyPeopleUser> attending) {
		this.attending = attending;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	public boolean isPrivateEvent() {
		return privateEvent;
	}
	public void setPrivateEvent(boolean privateEvent) {
		this.privateEvent = privateEvent;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public List<String> getItemsNeeded() {
		return itemsNeeded;
	}
	public void setItemsNeeded(List<String> itemsNeeded) {
		this.itemsNeeded = itemsNeeded;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	
}

package partypeople;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;

import com.google.appengine.api.users.User;

public class Event extends Observable implements Comparable<Event> {
	public static final int SOONEST_DATE = 0;
	public static final int ALPHABETICAL = 1;
	public static final int CLOSEST_DISTANCE = 2;
	public static final int TIME_CREATED = 3;
	public static final int PRICE = 4;
	
	private int sortType;
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
	private Date timeCreated;
	//private Image picture;
	
	public Event(){
		timeCreated = new Date();
	}
	
	public int getSortType() {
		return sortType;
	}

	public void setSortType(int sortType) {
		this.sortType = sortType;
	}

	public Date getTimeCreated() {
		return timeCreated;
	}

	public void setTimeCreated(Date timeCreated) {
		this.timeCreated = timeCreated;
	}


	public void updateEvent(Event newEvent){
		ArrayList<String> changed = new ArrayList<String>();
		boolean hasChanged = false;
		changed.add(this.name);
		if (!this.name.equals(newEvent.getName()) && !(newEvent.getName()== null)) {
			changed.add("Event Name");
			this.name = newEvent.getName();
			hasChanged = true;
		}
		if (!this.owner.equals(newEvent.getOwner()) && !(newEvent.getOwner() == null)) {
			changed.add("Host");
			this.owner = newEvent.getOwner();
			hasChanged = true;
		}
		if (!this.location.equals(newEvent.getLocation()) && !(newEvent.getLocation() == null)) {
			changed.add("Location");
			this.location = newEvent.getLocation();
			hasChanged = true;
		}
		if (!this.date.equals(newEvent.getDate()) && !(newEvent.getDate() == null)) {
			changed.add("Date");
			this.date = newEvent.getDate();
			hasChanged = true;
		}
		if (this.price != newEvent.getPrice() ) {
			changed.add("Price");
			this.price = newEvent.getPrice();
			hasChanged = true;
		}
		this.description = newEvent.getDescription();
		this.comments = newEvent.getComments();
		this.privateEvent = newEvent.isPrivateEvent();
		this.password = newEvent.getPassword();
		this.itemsNeeded = newEvent.getItemsNeeded();
		this.category = newEvent.getCategory();
		this.attending = newEvent.getAttending();
		
		if (hasChanged) {
			setChanged();
		}
		
		//figure out what changed and turn it into a string to 
		//pass to the observers, so it can be emailed.
		notifyObservers(changed);
		clearChanged();
		
	}
	
	public void addAttendee(PartyPeopleUser u){
		attending.add(u);
		addObserver(u);
	}
	
	public void removeAttendee(PartyPeopleUser u){
		attending.remove(u);
		deleteObserver(u);
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

	@Override
	public int compareTo(Event e) {
		// TODO Auto-generated method stub
		switch (sortType){
		case SOONEST_DATE:
			return date.compareTo(e.getDate());
		case ALPHABETICAL:
			return name.compareTo(e.getName());
		case CLOSEST_DISTANCE:
			return 0;
		case TIME_CREATED:
			return timeCreated.compareTo(e.getTimeCreated());
		case PRICE:
			return Double.compare(price, e.getPrice());
		default:
			return 0;
		}
	}
	
	public String toString(){
		String result = new String();
		if (isPrivateEvent()){
			if (name!=null){
				result+=name + " ";
			}
			if (owner!=null){
				result += owner.toString() + " ";
			}
			if (category!=null){
				result += category + " ";
			}
		}
		else{
			if (name!=null){
				result+=name + " ";
			}
			if (owner!=null){
				result += owner.toString() + " ";
			}
			if (location!=null){
				result += location + " ";
			}
			if (date!=null){
				result += date.toString() + " ";
			}
			if (description!=null){
				result += description + " ";
			}
			if (category!=null){
				result += category + " ";
			}

		}
		return result;
	}
	
	public boolean equals(Event other){
		return this.toString().equals(other.toString());
	}
	
	
}

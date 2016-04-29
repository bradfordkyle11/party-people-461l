package partypeople;

public class Item {
	private String itemName;
	private PartyPeopleUser userLink;
	
	public Item(String name, PartyPeopleUser user){
		itemName = name;
		userLink = user;
	}
	
	public void setUser(PartyPeopleUser user){
		userLink = user;
	}
	
	public void setName(String name){
		itemName = name;
	}
	
	public PartyPeopleUser getUser(){
		return userLink;
	}
	
	public String getName(){
		return itemName;
	}
	
}

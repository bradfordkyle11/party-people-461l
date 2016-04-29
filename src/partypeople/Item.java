package partypeople;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Item {
	@Id 
	Long id;
	private String itemName;
	private PartyPeopleUser userLink;
	
	
	
	
	public Item(){}
	
	public Item(String name, PartyPeopleUser user){
		itemName = name;
		userLink = user;
		StorageHandler.save(this);
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

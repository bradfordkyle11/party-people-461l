package partypeople;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Load;

@Entity
public class Item {
	@Id 
	Long id;
	private String itemName;
	@Load private Ref<PartyPeopleUser> userLink;
	
	
	
	
	public Item(){}
	
	public Item(String name, PartyPeopleUser user){
		itemName = name;
		if (user!=null){
			userLink = Ref.create(user);
		}
		StorageHandler.save(this);
	}
	
	public void setUser(PartyPeopleUser user){
		userLink = Ref.create(user);
	}
	
	public void setName(String name){
		itemName = name;
	}
	
	public PartyPeopleUser getUser(){
		if (userLink==null){
			return null;
		}
		return userLink.safeGet();
	}
	
	public void removeUser(){
		userLink = null;
	}
	
	public String getName(){
		return itemName;
	}

	public Long getId() {
		return id;
	}
	
}

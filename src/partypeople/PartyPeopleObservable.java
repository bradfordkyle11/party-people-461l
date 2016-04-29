package partypeople;

import java.util.ArrayList;
import java.util.List;

public class PartyPeopleObservable {
	private List<PartyPeopleObserver> observers;
	private boolean changed;
	
	public PartyPeopleObservable() {
		observers = new ArrayList<PartyPeopleObserver>();
		changed = false;
	}
	
	public void addObserver(PartyPeopleObserver o) {
		observers.add(o);
	}
	
	public void deleteObserver(PartyPeopleObserver o) {
		observers.remove(o);
	}
	
	public void setChanged() {
		changed = true;
	}
	
	public void clearChanged() {
		changed = false;
	}
	
	public boolean hasChanged() {
		return changed;
	}
	
	public int countObservers() {
		return observers.size();
	}
	
	public void deleteObservers() {
		observers.clear();
	}
	
	public void notifyObservers(Object arg) {
		if (hasChanged()) {
			for (int i = 0; i < observers.size(); i++) {
				observers.get(i).update(this, arg);
			}
			clearChanged();
		}
	}
	
	
}

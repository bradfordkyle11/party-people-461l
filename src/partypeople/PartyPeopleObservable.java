package partypeople;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Load;

public class PartyPeopleObservable {
	@Load private List<Ref<PartyPeopleObserver>> observers;
	private boolean changed;
	
	public PartyPeopleObservable() {
		observers = new ArrayList<Ref<PartyPeopleObserver>>();
		changed = false;
	}
	
	public void addObserver(PartyPeopleObserver o) {
		observers.add(Ref.create(o));
	}
	
	public void deleteObserver(PartyPeopleObserver o) {
		observers.remove(Ref.create(o));
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
				observers.get(i).getValue().update(this, arg);
			}
			clearChanged();
		}
	}
	
	
}

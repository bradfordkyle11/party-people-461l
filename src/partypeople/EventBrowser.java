package partypeople;

import java.util.List;

public class EventBrowser {
	Filter activeFilter;
	
	public EventBrowser(){
		activeFilter = new Filter();
	}
	public EventBrowser(Filter filter){
		activeFilter = filter;
	}
	
	public List<Event> getEvents(){
		List<Event> events = StorageHandler.loadEvents();
		events = activeFilter.apply(events);
		return events;
	}
}

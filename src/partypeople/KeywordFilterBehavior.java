package partypeople;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KeywordFilterBehavior implements FilterBehavior{
	
	ArrayList<String> keywordList;

	public KeywordFilterBehavior(String query){
		query = query.toLowerCase();
		String[] keywords = query.split(" ");
		keywordList = new ArrayList<String>(Arrays.asList(keywords));
	}
	@Override
	public List<Event> apply(List<Event> events) {
		ArrayList<Event> filteredEvents = new ArrayList<Event>();
		// TODO Auto-generated method stub
		for (Event e : events){
			String s = e.toString();
			s = s.toLowerCase();
			ArrayList<String> eventDetails = new ArrayList<String>(Arrays.asList(s.split("[\\W]")));
			boolean relevant = true;
			for (String keyword : keywordList){
				if (!eventDetails.contains(keyword)){
					relevant = false;
					break;
				}
			}
			if (relevant){
				filteredEvents.add(e);
			}
		}
		return filteredEvents;
	}

}

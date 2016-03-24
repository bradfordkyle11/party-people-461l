package partypeople;

import java.util.List;

public interface FilterBehavior {
  public List<Event> apply(List<Event> events);
}

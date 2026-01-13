package collections_deepdive;

import java.time.*;
import java.util.*;

public class EventScheduler {

    private final TreeSet<Event> eventsByTime = new TreeSet<>();
    private final TreeMap<LocalDate, List<Event>> eventsByDate = new TreeMap<>();

    public boolean addEvent(Event e) {
        if (!isSlotAvailable(e.getStartTime(), e.getEndTime())) return false;

        eventsByTime.add(e);
        eventsByDate
                .computeIfAbsent(e.getStartTime().toLocalDate(), d -> new ArrayList<>())
                .add(e);
        return true;
    }

    public boolean removeEvent(String id) {
        Event found = eventsByTime.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst().orElse(null);
        if (found == null) return false;

        eventsByTime.remove(found);
        eventsByDate.get(found.getStartTime().toLocalDate()).remove(found);
        return true;
    }

    public List<Event> getEventsOnDate(LocalDate date) {
        return eventsByDate.getOrDefault(date, List.of());
    }

    public List<Event> getEventsInRange(LocalDateTime start, LocalDateTime end) {
        return eventsByTime.subSet(
                new Event("", "", start, start, 1),
                true,
                new Event("", "", end, end, 5),
                true
        ).stream().toList();
    }

    public List<Event> getUpcomingEvents(int count) {
        return eventsByTime.tailSet(
                new Event("", "", LocalDateTime.now(), LocalDateTime.now(), 1),
                true
        ).stream().limit(count).toList();
    }

    public Optional<Event> getCurrentEvent() {
        LocalDateTime now = LocalDateTime.now();
        return eventsByTime.stream()
                .filter(e -> !now.isBefore(e.getStartTime())
                          && !now.isAfter(e.getEndTime()))
                .findFirst();
    }

    public boolean isSlotAvailable(LocalDateTime start, LocalDateTime end) {
        for (Event e : eventsByTime) {
            if (start.isBefore(e.getEndTime()) &&
                end.isAfter(e.getStartTime())) {
                return false;
            }
        }
        return true;
    }
}

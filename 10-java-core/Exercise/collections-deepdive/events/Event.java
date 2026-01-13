package collections_deepdive;

import java.time.LocalDateTime;
import java.util.Objects;

public class Event implements Comparable<Event> {

    private String id;
    private String title;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int priority;

    public Event(String id, String title,
                 LocalDateTime start, LocalDateTime end,
                 int priority) {
        this.id = id;
        this.title = title;
        this.startTime = start;
        this.endTime = end;
        this.priority = priority;
    }

    public String getId() { return id; }
    public LocalDateTime getStartTime() { return startTime; }
    public LocalDateTime getEndTime() { return endTime; }

    @Override
    public int compareTo(Event o) {
        int cmp = startTime.compareTo(o.startTime);
        return cmp != 0 ? cmp : Integer.compare(priority, o.priority);
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Event e && id.equals(e.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

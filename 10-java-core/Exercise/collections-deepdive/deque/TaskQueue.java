package collections_deepdive;

import java.util.*;

public class TaskQueue {

    private final Deque<Task> priority = new ArrayDeque<>();
    private final Deque<Task> normal = new ArrayDeque<>();

    public void addTask(Task t) {
        (t.isPriority() ? priority : normal).addLast(t);
    }

    public void addUrgentTask(Task t) {
        (t.isPriority() ? priority : normal).addFirst(t);
    }

    public Optional<Task> getNextTask() {
        return Optional.ofNullable(
                !priority.isEmpty() ? priority.pollFirst() : normal.pollFirst()
        );
    }

    public Optional<Task> peekNextTask() {
        return Optional.ofNullable(
                !priority.isEmpty() ? priority.peekFirst() : normal.peekFirst()
        );
    }

    public int getPendingCount() {
        return priority.size() + normal.size();
    }

    public List<Task> getAllPendingTasks() {
        List<Task> all = new ArrayList<>(priority);
        all.addAll(normal);
        return all;
    }

    public boolean prioritizeTask(String id) {
        for (Iterator<Task> it = normal.iterator(); it.hasNext();) {
            Task t = it.next();
            if (t.id().equals(id)) {
                it.remove();
                priority.addFirst(t);
                return true;
            }
        }
        return false;
    }
}

record Task(String id, String description, boolean isPriority) {}

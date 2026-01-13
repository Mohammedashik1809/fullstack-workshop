package collections_deepdive;

import java.util.*;

public class BrowserHistory {

    private final Deque<String> back = new ArrayDeque<>();
    private final Deque<String> forward = new ArrayDeque<>();
    private String current;

    public BrowserHistory(String homepage) {
        current = homepage;
    }

    public void visit(String url) {
        back.push(current);
        current = url;
        forward.clear();
    }

    public String back(int steps) {
        while (steps-- > 0 && !back.isEmpty()) {
            forward.push(current);
            current = back.pop();
        }
        return current;
    }

    public String forward(int steps) {
        while (steps-- > 0 && !forward.isEmpty()) {
            back.push(current);
            current = forward.pop();
        }
        return current;
    }

    public String getCurrentPage() {
        return current;
    }

    public boolean canGoBack() { return !back.isEmpty(); }
    public boolean canGoForward() { return !forward.isEmpty(); }

    public List<String> getFullHistory() {
        List<String> list = new ArrayList<>(back);
        Collections.reverse(list);
        list.add(current);
        list.addAll(forward);
        return list;
    }
}

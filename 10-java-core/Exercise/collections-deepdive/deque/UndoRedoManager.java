package collections_deepdive;

import java.util.*;

public class UndoRedoManager<T> {

    private final Deque<T> undo = new ArrayDeque<>();
    private final Deque<T> redo = new ArrayDeque<>();
    private T current;

    public UndoRedoManager(T initial) {
        current = initial;
    }

    public void saveState(T state) {
        undo.push(current);
        current = state;
        redo.clear();
    }

    public Optional<T> undo() {
        if (undo.isEmpty()) return Optional.empty();
        redo.push(current);
        current = undo.pop();
        return Optional.of(current);
    }

    public Optional<T> redo() {
        if (redo.isEmpty()) return Optional.empty();
        undo.push(current);
        current = redo.pop();
        return Optional.of(current);
    }

    public T getCurrentState() { return current; }
    public boolean canUndo() { return !undo.isEmpty(); }
    public boolean canRedo() { return !redo.isEmpty(); }
}

package collections_deepdive;

import java.util.*;
import java.util.stream.Stream;

public class LimitedSortedUniqueList<T> implements Iterable<T> {

    private final int maxSize;
    private final Comparator<T> comparator;
    private final List<T> list = new ArrayList<>();

    public LimitedSortedUniqueList(int maxSize, Comparator<T> comparator) {
        this.maxSize = maxSize;
        this.comparator = comparator;
    }

    public LimitedSortedUniqueList(int maxSize) {
        this(maxSize, (Comparator<T>) Comparator.naturalOrder());
    }

    public boolean add(T e) {
        if (list.contains(e)) return false;

        list.add(e);
        list.sort(comparator);

        if (list.size() > maxSize) {
            list.remove(list.size() - 1);
            return list.contains(e);
        }
        return true;
    }

    public int addAll(Collection<T> c) {
        int count = 0;
        for (T e : c) if (add(e)) count++;
        return count;
    }

    public boolean remove(T e) {
        return list.remove(e);
    }

    public boolean contains(T e) {
        return list.contains(e);
    }

    public T get(int i) {
        return list.get(i);
    }

    public Optional<T> first() {
        return list.isEmpty() ? Optional.empty() : Optional.of(list.get(0));
    }

    public Optional<T> last() {
        return list.isEmpty()
                ? Optional.empty()
                : Optional.of(list.get(list.size() - 1));
    }

    public int size() { return list.size(); }
    public boolean isFull() { return size() == maxSize; }
    public boolean isEmpty() { return list.isEmpty(); }

    public List<T> toList() {
        return Collections.unmodifiableList(list);
    }

    public void clear() {
        list.clear();
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }

    public Stream<T> stream() {
        return list.stream();
    }
}

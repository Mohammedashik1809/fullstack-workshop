package collections_deepdive;

import java.util.*;

public class CollectionUtilities {

    public static <T> void rotateList(List<T> list, int pos) {
        Collections.rotate(list, pos);
    }

    public static <T> Map<T, Integer> getFrequencyMap(Collection<T> c) {
        Map<T, Integer> map = new HashMap<>();
        for (T e : c) map.merge(e, 1, Integer::sum);
        return map;
    }

    public static <T> boolean areDisjoint(Collection<T> a, Collection<T> b) {
        return Collections.disjoint(a, b);
    }

    public static <T> Optional<T> findMostCommon(Collection<T> c) {
        return getFrequencyMap(c).entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
    }

    public static <T> MinMax<T> findMinMax(
            Collection<T> c, Comparator<T> comp) {
        T min = Collections.min(c, comp);
        T max = Collections.max(c, comp);
        return new MinMax<>(min, max);
    }
}

record Card(String suit, String rank) {}
record MinMax<T>(T min, T max) {}

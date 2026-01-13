package generics;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class CollectionUtils {

    // Find first element matching predicate
    public static <T> T findFirst(List<T> list, Predicate<T> predicate) {
        for (T item : list) {
            if (predicate.test(item)) {
                return item;
            }
        }
        return null;
    }

    // Find max element (Comparable constraint)
    public static <T extends Comparable<T>> T findMax(List<T> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }

        T max = list.get(0);
        for (T item : list) {
            if (item.compareTo(max) > 0) {
                max = item;
            }
        }
        return max;
    }

    // Filter list by predicate
    public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T item : list) {
            if (predicate.test(item)) {
                result.add(item);
            }
        }
        return result;
    }
}


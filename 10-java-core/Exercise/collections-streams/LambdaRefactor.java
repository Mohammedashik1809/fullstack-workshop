package collections_streams;

import java.util.*;
import java.util.stream.Collectors;

public class LambdaRefactor {

    public static void main(String[] args) {

        List<String> names = new ArrayList<>(List.of("Alice", "Bob", "Anna"));
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

        // 1. Sort by length
        names.sort(Comparator.comparingInt(String::length));

        // 2. Filter even numbers
        List<Integer> evens = numbers.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());

        // 3. Print each element
        names.forEach(System.out::println);

        // 4. Create thread
        Thread t = new Thread(() -> System.out.println("Running"));
        t.start();

        // 5. Transform to uppercase
        List<String> upper = names.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        System.out.println(evens);
        System.out.println(upper);
    }
}

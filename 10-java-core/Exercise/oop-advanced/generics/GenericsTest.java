package generics;

import java.util.List;

public class GenericsTest {

    public static void main(String[] args) {

        // Pair example
        Pair<String, Integer> pair = new Pair<>("Age", 25);
        System.out.println(pair);

        // Box example
        Box<String> box = new Box<>();
        box.set("Hello");
        System.out.println(box.get());

        // findMax example
        List<Integer> numbers = List.of(1, 5, 3, 9, 2);
        Integer max = CollectionUtils.findMax(numbers);
        System.out.println("Max number: " + max);

        // filter example
        List<String> names = List.of("Alice", "Bob", "Anna");
        List<String> aNames =
                CollectionUtils.filter(names, s -> s.startsWith("A"));
        System.out.println(aNames);
    }
}

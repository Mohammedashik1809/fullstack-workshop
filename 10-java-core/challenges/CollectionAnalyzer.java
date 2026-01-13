package revature;

import java.util.*;
import java.util.stream.Collectors;

public class CollectionAnalyzer {

   
    public static Map<Integer, List<String>> groupByLength(List<String> words) {
        return words.stream()
                    .collect(Collectors.groupingBy(String::length));
    }

   
    public static Map<Character, Long> charFrequency(List<String> words) {
        return words.stream()
                    .flatMap(word -> word.chars().mapToObj(c -> (char) c))
                    .collect(Collectors.groupingBy(
                            c -> c,
                            Collectors.counting()
                    ));
    }

   
    public static void main(String[] args) {

        System.out.println(
            groupByLength(Arrays.asList("hi", "bye", "hello", "ok"))
        );
       

        System.out.println(
            charFrequency(Arrays.asList("aab", "bc"))
        );
       
    }
}


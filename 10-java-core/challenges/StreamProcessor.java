package streamprocessor;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;

public class StreamProcessor {

    
    public static OptionalDouble averageOfEvens(List<Integer> numbers) {
        return numbers.stream()
                      .filter(n -> n % 2 == 0)    
                      .mapToInt(Integer::intValue)
                      .average();                
    }

    public static void main(String[] args) {

        
        OptionalDouble result1 = averageOfEvens(Arrays.asList(1, 2, 3, 4, 5, 6));
        System.out.println(result1);  


        OptionalDouble result2 = averageOfEvens(Arrays.asList(1, 3, 5));
        System.out.println(result2);   
    }
}


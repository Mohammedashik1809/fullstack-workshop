package revature;

import java.util.Optional;

public class SafeExecutor {

   
    @FunctionalInterface
    interface ThrowingSupplier<T> {
        T get() throws Exception;
    }

   
    public static <T> Optional<T> safeExecute(ThrowingSupplier<T> supplier) {
        try {
            return Optional.ofNullable(supplier.get());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    
    public static void main(String[] args) {

        Optional<Integer> result1 = safeExecute(() -> Integer.parseInt("123"));
        System.out.println(result1); 

        Optional<Integer> result2 = safeExecute(() -> Integer.parseInt("abc"));
        System.out.println(result2); 
    }
}


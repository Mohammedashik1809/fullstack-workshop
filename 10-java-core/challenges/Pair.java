package revature;
import java.util.Objects;


public class Pair<K, V> {

	    private final K key;
	    private final V value;

	    // Constructor
	    public Pair(K key, V value) {
	        this.key = key;
	        this.value = value;
	    }

	    // Getter for key
	    public K getKey() {
	        return key;
	    }

	    // Getter for value
	    public V getValue() {
	        return value;
	    }

	    // Swap method
	    public Pair<V, K> swap() {
	        return new Pair<>(value, key);
	    }

	    // Override equals
	    @Override
	    public boolean equals(Object obj) {
	        if (this == obj) return true;
	        if (!(obj instanceof Pair<?, ?> other)) return false;

	        return Objects.equals(key, other.key) &&
	               Objects.equals(value, other.value);
	    }

	    // Override hashCode
	    @Override
	    public int hashCode() {
	        return Objects.hash(key, value);
	    }

	    // Override toString
	    @Override
	    public String toString() {
	        return "Pair(" + key + ", " + value + ")";
	    }

	public static void main(String[] args) {
		 Pair<String, Integer> pair = new Pair<>("age", 25);

	        System.out.println(pair.getKey());      // age
	        System.out.println(pair.getValue());    // 25

	        Pair<Integer, String> swapped = pair.swap();
	        System.out.println(swapped);             // Pair(25, age)

	}

}

package collections_streams;

import java.util.*;
import java.util.stream.Collectors;

public class Inventory {

    private List<Product> products = new ArrayList<>();
    private Set<String> categories = new HashSet<>();
    private Map<String, Product> productMap = new HashMap<>();

    public void addProduct(Product p) {
        products.add(p);
        categories.add(p.getCategory());
        productMap.put(p.getId(), p);
    }

    public void updateProduct(Product p) {
        productMap.put(p.getId(), p);
    }

    public void deleteProduct(String id) {
        Product p = productMap.remove(id);
        if (p != null) {
            products.remove(p);
        }
    }

    public Product findById(String id) {
        return productMap.get(id);
    }

    public List<Product> getByCategory(String category) {
        return products.stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    public List<Product> getAllSortedByPrice() {
        return products.stream()
                .sorted(Comparator.comparing(Product::getPrice))
                .collect(Collectors.toList());
    }

    public Queue<Product> getLowStockAlerts() {
        return products.stream()
                .filter(p -> p.getQuantity() < 10)
                .collect(Collectors.toCollection(LinkedList::new));
    }
}

package collections_deepdive;

import java.lang.reflect.Field;
import java.util.*;

public class SortableProductList {

    private final List<Product> products;
    private Comparator<Product> comparator;

    public SortableProductList(List<Product> products) {
        this.products = new ArrayList<>(products);
    }

    public SortableProductList sortBy(Comparator<Product> comparator) {
        this.comparator = comparator;
        products.sort(comparator);
        return this;
    }

    public SortableProductList thenSortBy(Comparator<Product> next) {
        comparator = comparator.thenComparing(next);
        products.sort(comparator);
        return this;
    }

    public SortableProductList sortByField(String fieldName, boolean asc) {
        Comparator<Product> c = Comparator.comparing(p -> {
            try {
                Field f = Product.class.getDeclaredField(fieldName);
                f.setAccessible(true);
                return (Comparable<?>) f.get(p);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return sortBy(asc ? c : c.reversed());
    }

    public List<Product> top(int n) {
        return products.subList(0, Math.min(n, products.size()));
    }

    public List<Product> getPage(int page, int size) {
        int from = (page - 1) * size;
        int to = Math.min(from + size, products.size());
        return from >= products.size() ? List.of() : products.subList(from, to);
    }

    public List<Product> getProducts() {
        return products;
    }
}

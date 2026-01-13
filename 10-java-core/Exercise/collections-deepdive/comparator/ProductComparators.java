package collections_deepdive;

import java.util.*;

public class ProductComparators {

    public static Comparator<Product> byPrice() {
        return Comparator.comparingDouble(Product::getPrice);
    }

    public static Comparator<Product> byPriceDescending() {
        return Comparator.comparingDouble(Product::getPrice).reversed();
    }

    public static Comparator<Product> byName() {
        return Comparator.comparing(p -> p.getName().toLowerCase());
    }

    public static Comparator<Product> byCategoryThenName() {
        return Comparator.comparing(Product::getCategory)
                .thenComparing(ProductComparators.byName());
    }

    public static Comparator<Product> byRatingThenPrice() {
        return Comparator.comparing(
                Product::getRating,
                Comparator.nullsLast(Comparator.naturalOrder())
        ).thenComparing(Product::getPrice);
    }

    public static Comparator<Product> byLowStockFirst() {
        return Comparator.<Product>comparingInt(
                p -> p.getStockQuantity() < 10 ? 0 : 1
        ).thenComparing(Product::getName);
    }

    public static Comparator<Product> byNewest() {
        return Comparator.comparing(Product::getAddedDate).reversed();
    }

    public static Comparator<Product> multiSort(
            List<String> fields, List<Boolean> ascending) {

        Comparator<Product> result = (a, b) -> 0;

        for (int i = 0; i < fields.size(); i++) {
            String field = fields.get(i);
            boolean asc = ascending.get(i);

            Comparator<Product> next = Comparator.comparing(p -> {
                try {
                    var f = Product.class.getDeclaredField(field);
                    f.setAccessible(true);
                    return (Comparable<?>) f.get(p);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }, Comparator.nullsLast(Comparator.naturalOrder()));

            result = result.thenComparing(asc ? next : next.reversed());
        }
        return result;
    }
}

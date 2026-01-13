package collections_streams;

public class InventoryTest {

    public static void main(String[] args) {

        Inventory inventory = new Inventory();

        inventory.addProduct(new Product("P001", "Laptop", "Electronics", 999.99, 50));
        inventory.addProduct(new Product("P002", "Mouse", "Electronics", 29.99, 5));
        inventory.addProduct(new Product("P003", "Desk", "Furniture", 299.99, 15));

        inventory.getByCategory("Electronics")
                .forEach(p -> System.out.println(p.getName()));

        inventory.getAllSortedByPrice()
                .forEach(p -> System.out.println(p.getName()));

        inventory.getLowStockAlerts()
                .forEach(p -> System.out.println("Low stock: " + p.getName()));
    }
}

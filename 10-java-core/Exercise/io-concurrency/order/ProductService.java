package io_concurrency;

import java.util.concurrent.CompletableFuture;

public class ProductService {

    public CompletableFuture<Product> getProduct(Long id) {
        return CompletableFuture.supplyAsync(() -> {
            sleep(300);
            return new Product(id, "Product-" + id, 500.0);
        });
    }

    private static void sleep(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException ignored) {}
    }
}

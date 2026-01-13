package io_concurrency;

import java.util.concurrent.CompletableFuture;

public class InventoryService {

    public CompletableFuture<Boolean> checkStock(Long productId) {
        return CompletableFuture.supplyAsync(() -> {
            sleep(200);
            return true; // simulate stock available
        });
    }

    private static void sleep(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException ignored) {}
    }
}

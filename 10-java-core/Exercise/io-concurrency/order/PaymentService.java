package io_concurrency;

import java.util.concurrent.CompletableFuture;

public class PaymentService {

    public CompletableFuture<Order> processPayment(Order order) {
        return CompletableFuture.supplyAsync(() -> {
            sleep(1000);
            return order; // payment successful
        });
    }

    private static void sleep(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException ignored) {}
    }
}

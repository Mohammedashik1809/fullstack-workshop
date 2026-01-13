package io_concurrency;

import java.util.concurrent.CompletableFuture;

public class NotificationService {

    public CompletableFuture<Void> sendConfirmation(Order order) {
        return CompletableFuture.runAsync(() -> {
            sleep(400);
            System.out.println("Notification sent for order " + order.orderId());
        });
    }

    private static void sleep(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException ignored) {}
    }
}

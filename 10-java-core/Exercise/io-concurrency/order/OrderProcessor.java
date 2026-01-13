package io_concurrency;

import java.util.concurrent.CompletableFuture;

public class OrderProcessor {

    private final UserService userService = new UserService();
    private final ProductService productService = new ProductService();
    private final InventoryService inventoryService = new InventoryService();
    private final PaymentService paymentService = new PaymentService();
    private final NotificationService notificationService = new NotificationService();

    public CompletableFuture<OrderResult> processOrder(
            Long userId, Long productId, int quantity) {

        CompletableFuture<User> userFuture = userService.getUser(userId);
        CompletableFuture<Product> productFuture = productService.getProduct(productId);

        return userFuture.thenCombine(productFuture,
                (user, product) -> new Order(
                        System.currentTimeMillis(), user, product, quantity
                ))

                .thenCompose(order ->
                        inventoryService.checkStock(order.product().id())
                                .thenCompose(inStock -> {
                                    if (!inStock) {
                                        return CompletableFuture.failedFuture(
                                                new RuntimeException("Out of stock"));
                                    }
                                    return paymentService.processPayment(order);
                                })
                )

                .thenCompose(order ->
                        CompletableFuture.allOf(
                                notificationService.sendConfirmation(order)
                        ).thenApply(v -> OrderResult.success(order.orderId()))
                )

                .exceptionally(ex ->
                        OrderResult.failure(ex.getMessage())
                );
    }
}

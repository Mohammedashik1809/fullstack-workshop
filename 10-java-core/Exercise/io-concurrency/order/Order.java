package io_concurrency;

public record Order(
        Long orderId,
        User user,
        Product product,
        int quantity
) {}

package io_concurrency;

public class OrderResult {

    private final boolean success;
    private final Long orderId;
    private final String error;

    private OrderResult(boolean success, Long orderId, String error) {
        this.success = success;
        this.orderId = orderId;
        this.error = error;
    }

    public static OrderResult success(Long orderId) {
        return new OrderResult(true, orderId, null);
    }

    public static OrderResult failure(String error) {
        return new OrderResult(false, null, error);
    }

    public boolean isSuccess() {
        return success;
    }

    public Long getOrderId() {
        return orderId;
    }

    public String getError() {
        return error;
    }
}

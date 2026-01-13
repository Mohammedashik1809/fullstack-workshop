package payment;

public interface Payable {

    double getPaymentAmount();

    // Default method
    default void printPaymentInfo() {
        System.out.println("Payment Amount: $" + getPaymentAmount());
    }
}

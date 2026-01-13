package payment;

public class Invoice implements Payable {

    private String partNumber;
    private String description;
    private int quantity;
    private double pricePerItem;

    public Invoice(String partNumber, String description, int quantity, double pricePerItem) {
        this.partNumber = partNumber;
        this.description = description;
        this.quantity = quantity;
        this.pricePerItem = pricePerItem;
    }

    @Override
    public double getPaymentAmount() {
        return quantity * pricePerItem;
    }

    @Override
    public void printPaymentInfo() {
        System.out.println("Invoice [" + partNumber + " - " + description + "]");
        System.out.println("Quantity: " + quantity + ", Price per item: $" + pricePerItem);
        System.out.println("Invoice Total: $" + getPaymentAmount());
        System.out.println("----------------------------");
    }
}


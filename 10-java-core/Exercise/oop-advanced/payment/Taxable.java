package payment;

public interface Taxable {

    double calculateTax();

    // Static method
    static double getTaxRate() {
        return 0.18; 
    }
}


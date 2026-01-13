package payment;

public class Employee implements Payable, Taxable {

    private String name;
    private double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    @Override
    public double getPaymentAmount() {
        return salary - calculateTax();
    }

    @Override
    public double calculateTax() {
        return salary * Taxable.getTaxRate();
    }

    @Override
    public void printPaymentInfo() {
        System.out.println("Employee: " + name);
        System.out.println("Salary: $" + salary);
        System.out.println("Tax: $" + calculateTax());
        System.out.println("Net Payment: $" + getPaymentAmount());
        System.out.println("----------------------------");
    }
}

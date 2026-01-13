package banking;

public abstract class Account {

    protected int accountNumber;
    protected String holderName;
    protected double balance;

    private static int accountCounter = 1000;

    public Account(String holderName, double balance) {
        this.accountNumber = ++accountCounter;
        this.holderName = holderName;
        this.balance = balance;
    }

    // Abstract method
    public abstract double calculateInterest();

    // Concrete methods
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= 0 || amount > balance) {
            throw new IllegalArgumentException("Invalid withdraw amount");
        }
        balance -= amount;
    }

    public double getBalance() {
        return balance;
    }

    public String getHolderName() {
        return holderName;
    }

    public int getAccountNumber() {
        return accountNumber;
    }
}

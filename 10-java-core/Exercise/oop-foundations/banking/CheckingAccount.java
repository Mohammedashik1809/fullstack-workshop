package banking;

public class CheckingAccount extends Account {

    private static final double OVERDRAFT_LIMIT = 500;

    public CheckingAccount(String holderName, double balance) {
        super(holderName, balance);
    }

    @Override
    public void withdraw(double amount) {
        if (amount > balance + OVERDRAFT_LIMIT) {
            throw new IllegalArgumentException("Overdraft limit exceeded");
        }
        balance -= amount;
    }

    @Override
    public double calculateInterest() {
        return 0; // No interest
    }
}


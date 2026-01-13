package banking;

public class FixedDepositAccount extends Account {

    private static final double INTEREST_RATE = 0.07;
    private static final int LOCK_PERIOD_MONTHS = 12;

    public FixedDepositAccount(String holderName, double balance) {
        super(holderName, balance);
    }

    @Override
    public void withdraw(double amount) {
        throw new UnsupportedOperationException(
                "Withdrawals are not allowed for Fixed Deposit accounts (Lock period: "
                        + LOCK_PERIOD_MONTHS + " months)"
        );
    }

    @Override
    public double calculateInterest() {
        return balance * INTEREST_RATE;
    }
}


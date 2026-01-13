package bankaccount;

public class BankAccount {

    // Static fields
    private static String bankName = "MyBank";
    private static int totalAccounts = 0;
    private static int accountCounter = 1000; // for auto-generating account numbers

    // Static method
    public static String getBankInfo() {
        return bankName + " - Total Accounts: " + totalAccounts;
    }

    // Instance fields
    private int accountNumber;
    private String holderName;
    private double balance;

    // Constructor
    public BankAccount(String holderName, double initialBalance) {
        this.holderName = holderName;
        this.balance = initialBalance;

       
        this.accountNumber = ++accountCounter;

       
        totalAccounts++;
    }

    // Deposit method
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be positive");
            return;
        }
        balance += amount;
        System.out.println(amount + " deposited successfully");
    }

    // Withdraw method
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdraw amount must be positive");
            return;
        }
        if (amount > balance) {
            System.out.println("Insufficient balance");
            return;
        }
        balance -= amount;
        System.out.println(amount + " withdrawn successfully");
    }

    // Get balance
    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "BankAccount{accountNumber=" + accountNumber +
                ", holderName='" + holderName + '\'' +
                ", balance=" + balance + '}';
    }


    public static void main(String[] args) {

        BankAccount acc1 = new BankAccount("Kishore", 1000);
        BankAccount acc2 = new BankAccount("Ravi", 500);

        acc1.deposit(500);
        acc2.withdraw(200);

        System.out.println(acc1);
        System.out.println(acc2);

        
        System.out.println(BankAccount.getBankInfo());
       
    }
}


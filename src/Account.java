// Inheritance: Account is the abstract superclass and implements the Withdrawable interface.
// SavingsAccount, CheckingAccount and StudentAccount extend it.
public abstract class Account implements Withdrawable {
    protected double balance;
    protected String accountNumber;
    protected String ownerName;

    public Account(String accountNumber, String ownerName, double balance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "Kontonummer: " + accountNumber + "\n"
                + "Ägare: " + ownerName + "\n"
                + "Saldo: " + balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            return;
        }
        balance += amount;
    }

    // Abstract method from the Withdrawable interface. Each subclass overrides it.
    @Override
    public abstract boolean withdraw(double amount);

    // Overridden in every subclass. Returns the balance change from month-end.
    public double monthEnd() {
        return 0;
    }
}

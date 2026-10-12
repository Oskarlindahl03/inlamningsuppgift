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

    public void deposit(double amount) {
        if (amount <= 0) {
            return;
        }
        balance += amount;
    }

    @Override
    public abstract boolean withdraw(double amount);
}

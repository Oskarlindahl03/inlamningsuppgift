// Inheritance: CheckingAccount extends Account, which already implements Withdrawable.
public class CheckingAccount extends Account {
    private double overdraftLimit;

    public CheckingAccount(String accountNumber, String ownerName, double balance, double overdraftLimit) {
        super(accountNumber, ownerName, balance);
        this.overdraftLimit = overdraftLimit;
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    // Polymorphism: this override allows a balance down to the overdraft limit.
    @Override
    public boolean withdraw(double amount) {
        if (amount <= 0 || balance - amount < -overdraftLimit) {
            return false;
        }
        balance -= amount;
        return true;
    }

    // Checking accounts have no month end change. The override still runs from the list.
    @Override
    public void monthEnd() {
    }
}

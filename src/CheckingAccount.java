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

    @Override
    public String toString() {
        return "Typ: Transaktionskonto\n"
                + super.toString() + "\n"
                + "Övertrasseringsgräns: " + overdraftLimit;
    }

    @Override
    public double monthEnd() {
        return 0;
    }
}

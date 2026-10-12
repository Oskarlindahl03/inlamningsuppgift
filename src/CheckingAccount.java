public class CheckingAccount extends Account {
    private double overdraftLimit;

    public CheckingAccount(String accountNumber, String ownerName, double balance, double overdraftLimit) {
        super(accountNumber, ownerName, balance);
        this.overdraftLimit = overdraftLimit;
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount <= 0 || balance - amount < -overdraftLimit) {
            return false;
        }
        balance -= amount;
        return true;
    }
}

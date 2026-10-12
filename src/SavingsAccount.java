public class SavingsAccount extends Account {
    private double interestRate;
    private int withdrawalsThisMonth;

    public SavingsAccount(String accountNumber, String ownerName, double balance, double interestRate) {
        super(accountNumber, ownerName, balance);
        this.interestRate = interestRate;
        this.withdrawalsThisMonth = 0;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void applyInterest() {
        balance += balance * interestRate;
    }

    public void resetMonthlyWithdrawals() {
        withdrawalsThisMonth = 0;
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount <= 0 || amount > balance) {
            return false;
        }
        balance -= amount;
        withdrawalsThisMonth++;
        return true;
    }
}

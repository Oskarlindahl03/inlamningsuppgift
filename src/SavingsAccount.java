// Inheritance: SavingsAccount extends Account, which already implements Withdrawable.
public class SavingsAccount extends Account {
    private static final int MAX_WITHDRAWALS_PER_MONTH = 3;

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

    // Polymorphism: this override rejects a withdrawal larger than the balance.
    @Override
    public boolean withdraw(double amount) {
        if (amount <= 0 || amount > balance || withdrawalsThisMonth >= MAX_WITHDRAWALS_PER_MONTH) {
            return false;
        }
        balance -= amount;
        withdrawalsThisMonth++;
        return true;
    }

    @Override
    public String toString() {
        return "Typ: Sparkonto\n"
                + super.toString() + "\n"
                + "Ränta: " + interestRate + "\n"
                + "Uttag den här månaden: " + withdrawalsThisMonth + " av " + MAX_WITHDRAWALS_PER_MONTH;
    }

    @Override
    public double monthEnd() {
        double before = balance;
        applyInterest();
        resetMonthlyWithdrawals();
        return balance - before;
    }
}

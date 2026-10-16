// Inheritance: StudentAccount extends Account, which already implements Withdrawable.
public class StudentAccount extends Account {
    private int freeWithdrawalsRemaining;

    public StudentAccount(String accountNumber, String ownerName, double balance) {
        super(accountNumber, ownerName, balance);
        this.freeWithdrawalsRemaining = 10;
    }

    public void monthlyFee() {
    }

    public void resetFreeWithdrawals() {
        freeWithdrawalsRemaining = 10;
    }

    // Polymorphism: this override counts down the remaining free withdrawals.
    @Override
    public boolean withdraw(double amount) {
        if (amount <= 0 || amount > balance) {
            return false;
        }
        balance -= amount;
        if (freeWithdrawalsRemaining > 0) {
            freeWithdrawalsRemaining--;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Typ: Studentkonto\n"
                + super.toString() + "\n"
                + "Fria uttag kvar: " + freeWithdrawalsRemaining;
    }

    @Override
    public double monthEnd() {
        double before = balance;
        monthlyFee();
        resetFreeWithdrawals();
        return balance - before;
    }
}

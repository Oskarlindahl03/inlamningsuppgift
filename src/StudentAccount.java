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
}

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private final List<Account> accounts = new ArrayList<>();
    private final List<Transaction> transactions = new ArrayList<>();

    public List<Account> getAccounts() {
        return accounts;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public Account findAccount(String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    public boolean openAccount(Account account) {
        if (account == null || findAccount(account.getAccountNumber()) != null) {
            return false;
        }
        accounts.add(account);
        return true;
    }

    public boolean closeAccount(String accountNumber) {
        Account account = findAccount(accountNumber);
        if (account == null) {
            return false;
        }
        return accounts.remove(account);
    }

    public boolean deposit(String accountNumber, double amount) {
        Account account = findAccount(accountNumber);
        if (account == null || amount <= 0) {
            return false;
        }
        account.deposit(amount);
        transactions.add(new Transaction(accountNumber, "DEPOSIT", amount));
        return true;
    }

    public boolean withdraw(String accountNumber, double amount) {
        Account account = findAccount(accountNumber);
        if (account == null) {
            return false;
        }
        boolean success = account.withdraw(amount);
        if (success) {
            transactions.add(new Transaction(accountNumber, "WITHDRAW", amount));
        }
        return success;
    }

    public boolean transfer(String fromAccountNumber, String toAccountNumber, double amount) {
        Account from = findAccount(fromAccountNumber);
        Account to = findAccount(toAccountNumber);
        if (from == null || to == null || amount <= 0) {
            return false;
        }
        if (!from.withdraw(amount)) {
            return false;
        }
        to.deposit(amount);
        transactions.add(new Transaction(fromAccountNumber, "TRANSFER_OUT", amount));
        transactions.add(new Transaction(toAccountNumber, "TRANSFER_IN", amount));
        return true;
    }

    public List<Transaction> getHistory(String accountNumber) {
        List<Transaction> history = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getAccountNumber().equals(accountNumber)) {
                history.add(transaction);
            }
        }
        return history;
    }

    public void monthEnd() {
        for (Account account : accounts) {
            if (account instanceof SavingsAccount savingsAccount) {
                savingsAccount.applyInterest();
                savingsAccount.resetMonthlyWithdrawals();
            } else if (account instanceof StudentAccount studentAccount) {
                studentAccount.monthlyFee();
                studentAccount.resetFreeWithdrawals();
            }
        }
    }
}

import java.util.ArrayList;
import java.util.List;

public class Bank {
    // Polymorphism: the list type is Account, the objects are subclasses.
    private final List<Account> accounts = new ArrayList<>();
    private final List<Transaction> transactions = new ArrayList<>();
    private String currentUser;

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
        boolean removed = accounts.remove(account);
        if (removed && currentUser != null && !getUsers().contains(currentUser)) {
            currentUser = null;
        }
        return removed;
    }

    public List<String> getUsers() {
        List<String> users = new ArrayList<>();
        for (Account account : accounts) {
            String ownerName = account.getOwnerName();
            if (!users.contains(ownerName)) {
                users.add(ownerName);
            }
        }
        return users;
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public boolean switchUser(String userName) {
        if (!getUsers().contains(userName)) {
            return false;
        }
        currentUser = userName;
        return true;
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
        Account found = findAccount(accountNumber);
        if (found == null) {
            return false;
        }
        // Polymorphism through the interface reference. The runtime type picks the override.
        Withdrawable account = found;
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
        // The same interface reference is used for a transfer.
        Withdrawable source = from;
        if (!source.withdraw(amount)) {
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
        // Polymorphism: one call on the superclass type. Each subclass runs its own override.
        for (Account account : accounts) {
            double change = account.monthEnd();
            if (change > 0) {
                transactions.add(new Transaction(account.getAccountNumber(), "INTEREST", change));
            } else if (change < 0) {
                transactions.add(new Transaction(account.getAccountNumber(), "FEE", -change));
            }
        }
    }
}

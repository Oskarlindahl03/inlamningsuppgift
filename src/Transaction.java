import java.time.LocalDateTime;

public class Transaction {
    private final String accountNumber;
    private final String type;
    private final double amount;
    private final LocalDateTime timestamp;

    public Transaction(String accountNumber, String type, double amount) {
        this.accountNumber = accountNumber;
        this.type = type;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return timestamp + " | " + type + " | " + accountNumber + " | " + amount;
    }
}

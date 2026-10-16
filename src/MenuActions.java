import java.util.List;
import java.util.Scanner;

public class MenuActions {
    public static void openAccount(Bank bank, Scanner scanner) {
        System.out.print("Kontotyp (1=Savings, 2=Checking, 3=Student): ");
        Integer type = readInt(scanner);
        if (type == null) {
            return;
        }

        System.out.print("Kontonummer: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Ägare: ");
        String ownerName = scanner.nextLine();
        System.out.print("Startsaldo: ");
        Double balance = readDouble(scanner);
        if (balance == null) {
            return;
        }

        // Polymorphism: the variable type is Account, the object is a subclass.
        Account account;
        switch (type) {
            case 1:
                System.out.print("Ränta (t.ex. 0.02): ");
                Double interestRate = readDouble(scanner);
                if (interestRate == null) {
                    return;
                }
                account = new SavingsAccount(accountNumber, ownerName, balance, interestRate);
                break;
            case 2:
                System.out.print("Övertrasseringsgräns: ");
                Double overdraftLimit = readDouble(scanner);
                if (overdraftLimit == null) {
                    return;
                }
                account = new CheckingAccount(accountNumber, ownerName, balance, overdraftLimit);
                break;
            case 3:
                account = new StudentAccount(accountNumber, ownerName, balance);
                break;
            default:
                System.out.println("Okänd kontotyp");
                return;
        }

        if (bank.openAccount(account)) {
            System.out.println("Konto öppnat");
        } else {
            System.out.println("Kunde inte öppna konto");
        }
    }

    public static void closeAccount(Bank bank, Scanner scanner) {
        System.out.print("Kontonummer: ");
        String accountNumber = scanner.nextLine();
        if (bank.closeAccount(accountNumber)) {
            System.out.println("Konto stängt");
        } else {
            System.out.println("Konto hittades inte");
        }
    }

    public static void deposit(Bank bank, Scanner scanner) {
        System.out.print("Kontonummer: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Belopp: ");
        Double amount = readDouble(scanner);
        if (amount == null) {
            return;
        }

        if (bank.deposit(accountNumber, amount)) {
            System.out.println("Insättning klar");
        } else {
            System.out.println("Insättning misslyckades");
        }
    }

    public static void withdraw(Bank bank, Scanner scanner) {
        System.out.print("Kontonummer: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Belopp: ");
        Double amount = readDouble(scanner);
        if (amount == null) {
            return;
        }

        if (bank.withdraw(accountNumber, amount)) {
            System.out.println("Uttag klart");
        } else {
            System.out.println("Uttag misslyckades");
        }
    }

    public static void transfer(Bank bank, Scanner scanner) {
        System.out.print("Från kontonummer: ");
        String from = scanner.nextLine();
        System.out.print("Till kontonummer: ");
        String to = scanner.nextLine();
        System.out.print("Belopp: ");
        Double amount = readDouble(scanner);
        if (amount == null) {
            return;
        }

        if (bank.transfer(from, to, amount)) {
            System.out.println("Överföring klar");
        } else {
            System.out.println("Överföring misslyckades");
        }
    }

    public static void switchUser(Bank bank, Scanner scanner) {
        List<String> users = bank.getUsers();
        if (users.isEmpty()) {
            System.out.println("Inga användare. Öppna ett konto först.");
            return;
        }

        System.out.println("Användare:");
        for (int i = 0; i < users.size(); i++) {
            System.out.println((i + 1) + ". " + users.get(i));
        }

        System.out.print("Välj användare: ");
        Integer choice = readInt(scanner);
        if (choice == null) {
            return;
        }
        if (choice < 1 || choice > users.size()) {
            System.out.println("Ogiltigt val");
            return;
        }

        String selected = users.get(choice - 1);
        bank.switchUser(selected);
        System.out.println("Aktiv användare: " + selected);
    }

    public static void showAccount(Bank bank, Scanner scanner) {
        List<Account> accounts = bank.getAccounts();
        if (accounts.isEmpty()) {
            System.out.println("Inga konton");
            return;
        }

        System.out.println("Konton:");
        for (int i = 0; i < accounts.size(); i++) {
            Account account = accounts.get(i);
            System.out.println((i + 1) + ". " + account.getAccountNumber() + " " + account.getOwnerName());
        }

        System.out.print("Välj konto: ");
        Integer choice = readInt(scanner);
        if (choice == null) {
            return;
        }
        if (choice < 1 || choice > accounts.size()) {
            System.out.println("Ogiltigt val");
            return;
        }

        System.out.println(accounts.get(choice - 1));
    }

    public static void showHistory(Bank bank, Scanner scanner) {
        System.out.print("Kontonummer: ");
        String accountNumber = scanner.nextLine();
        List<Transaction> history = bank.getHistory(accountNumber);

        if (history.isEmpty()) {
            System.out.println("Ingen historik");
            return;
        }

        for (Transaction transaction : history) {
            System.out.println(transaction);
        }
    }

    private static Integer readInt(Scanner scanner) {
        String input = scanner.nextLine().trim();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Ogiltigt tal");
            return null;
        }
    }

    private static Double readDouble(Scanner scanner) {
        String input = scanner.nextLine().trim().replace(',', '.');
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            System.out.println("Ogiltigt tal");
            return null;
        }
    }
}

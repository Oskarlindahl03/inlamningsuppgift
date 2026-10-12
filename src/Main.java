import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        Bank bank = new Bank();

        System.out.println("Välkommen till banksystemet");

        while (running) {
            printMenu();
            System.out.print("Välj alternativ: ");

            if (!scanner.hasNextInt()) {
                scanner.next();
                System.out.println("Ogiltigt val");
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    handleOpenAccount(bank, scanner);
                    break;
                case 2:
                    handleCloseAccount(bank, scanner);
                    break;
                case 3:
                    handleDeposit(bank, scanner);
                    break;
                case 4:
                    handleWithdraw(bank, scanner);
                    break;
                case 5:
                    handleTransfer(bank, scanner);
                    break;
                case 6:
                    handleShowHistory(bank, scanner);
                    break;
                case 7:
                    bank.monthEnd();
                    System.out.println("Månadsslut kördes");
                    break;
                case 8:
                    System.out.println("Välj användare (stub)");
                    break;
                case 0:
                    running = false;
                    System.out.println("Avslutar");
                    break;
                default:
                    System.out.println("Ogiltigt val");
                    break;
            }
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("1. Öppna konto");
        System.out.println("2. Stäng konto");
        System.out.println("3. Insättning");
        System.out.println("4. Uttag");
        System.out.println("5. Överföring");
        System.out.println("6. Visa transaktionshistorik");
        System.out.println("7. Kör månadsslut");
        System.out.println("8. Välj användare");
        System.out.println("0. Avsluta");
    }

    private static void handleOpenAccount(Bank bank, Scanner scanner) {
        System.out.print("Kontotyp (1=Savings, 2=Checking, 3=Student): ");
        int type = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Kontonummer: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Ägare: ");
        String ownerName = scanner.nextLine();
        System.out.print("Startsaldo: ");
        double balance = scanner.nextDouble();
        scanner.nextLine();

        Account account;
        switch (type) {
            case 1:
                System.out.print("Ränta (t.ex. 0.02): ");
                double interestRate = scanner.nextDouble();
                scanner.nextLine();
                account = new SavingsAccount(accountNumber, ownerName, balance, interestRate);
                break;
            case 2:
                System.out.print("Övertrasseringsgräns: ");
                double overdraftLimit = scanner.nextDouble();
                scanner.nextLine();
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

    private static void handleCloseAccount(Bank bank, Scanner scanner) {
        System.out.print("Kontonummer: ");
        String accountNumber = scanner.nextLine();
        if (bank.closeAccount(accountNumber)) {
            System.out.println("Konto stängt");
        } else {
            System.out.println("Konto hittades inte");
        }
    }

    private static void handleDeposit(Bank bank, Scanner scanner) {
        System.out.print("Kontonummer: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Belopp: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        if (bank.deposit(accountNumber, amount)) {
            System.out.println("Insättning klar");
        } else {
            System.out.println("Insättning misslyckades");
        }
    }

    private static void handleWithdraw(Bank bank, Scanner scanner) {
        System.out.print("Kontonummer: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Belopp: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        if (bank.withdraw(accountNumber, amount)) {
            System.out.println("Uttag klart");
        } else {
            System.out.println("Uttag misslyckades");
        }
    }

    private static void handleTransfer(Bank bank, Scanner scanner) {
        System.out.print("Från kontonummer: ");
        String from = scanner.nextLine();
        System.out.print("Till kontonummer: ");
        String to = scanner.nextLine();
        System.out.print("Belopp: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        if (bank.transfer(from, to, amount)) {
            System.out.println("Överföring klar");
        } else {
            System.out.println("Överföring misslyckades");
        }
    }

    private static void handleShowHistory(Bank bank, Scanner scanner) {
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
}

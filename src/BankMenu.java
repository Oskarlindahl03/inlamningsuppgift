import java.util.Scanner;

public class BankMenu {
    public static void run(Bank bank, Scanner scanner) {
        boolean running = true;

        while (running) {
            if (bank.getCurrentUser() != null) {
                System.out.println();
                System.out.println("Aktiv användare: " + bank.getCurrentUser());
            }
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
                    MenuActions.openAccount(bank, scanner);
                    break;
                case 2:
                    MenuActions.closeAccount(bank, scanner);
                    break;
                case 3:
                    MenuActions.deposit(bank, scanner);
                    break;
                case 4:
                    MenuActions.withdraw(bank, scanner);
                    break;
                case 5:
                    MenuActions.transfer(bank, scanner);
                    break;
                case 6:
                    MenuActions.showHistory(bank, scanner);
                    break;
                case 7:
                    bank.monthEnd();
                    System.out.println("Månadsslut kördes");
                    break;
                case 8:
                    MenuActions.switchUser(bank, scanner);
                    break;
                case 9:
                    MenuActions.showAccount(bank, scanner);
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
        System.out.println("9. Visa konto");
        System.out.println("0. Avsluta");
    }
}

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bank bank = new Bank();

        System.out.println("Välkommen till banksystemet");
        BankMenu.run(bank, scanner);

        scanner.close();
    }
}

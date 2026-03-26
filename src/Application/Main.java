package Application;

import Entities.Account;
import Entities.BusinessAccount;
import Entities.SavingsAccount;
import Entities.StudentAccount;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        List<Account> accounts = new ArrayList<>();

        System.out.println("Welcome to the Bank Account Management System!");

        int choice = 0;
        while (choice != 5) {
            showMenu();
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    createAccount(sc, accounts);
                    break;
                case 2:
                    performTransfer(sc, accounts);
                    break;
                case 3:
                    performOperation(sc, accounts);
                    break;
                case 4:
                    showAccountHistory(sc, accounts);
                    break;
                case 5:
                    System.out.println("Closing system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
        sc.close();
    }

    // Metodo apenas para exibir o menu
    private static void showMenu() {
        System.out.println("\n-------------------------------------------");
        System.out.println("MAIN MENU:");
        System.out.println("1. Create a new account");
        System.out.println("2. Transfer funds");
        System.out.println("3. Deposit / Withdraw funds");
        System.out.println("4. Show transaction history");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    // Metodo para Criar Conta
    public static void createAccount(Scanner sc, List<Account> accounts) {
        System.out.println("\nSelect Account Type: 1. Business | 2. Savings | 3. Student");
        int type = sc.nextInt();

        System.out.print("Account Number: ");
        String number = sc.next();
        System.out.print("Holder Name: ");
        String name = sc.next();
        System.out.print("Initial Balance: ");
        double balance = sc.nextDouble();

        switch (type) {
            case 1:
                System.out.print("Business Name: ");
                accounts.add(new BusinessAccount(number, name, balance, sc.next()));
                break;
            case 2:
                System.out.print("Interest Rate: ");
                accounts.add(new SavingsAccount(number, name, balance, sc.nextDouble()));
                break;
            case 3:
                System.out.print("School Name: ");
                accounts.add(new StudentAccount(number, name, balance, sc.next()));
                break;
            default:
                System.out.println("Invalid Type!");
                return;
        }
        System.out.println("Account created successfully!");
    }

    // Metodo para Transferencia
    private static void performTransfer(Scanner sc, List<Account> accounts) {
        if (accounts.size() < 2) {
            System.out.println("Error: Need at least 2 accounts.");
            return;
        }
        System.out.print("From Account Index: ");
        int from = sc.nextInt();
        System.out.print("To Account Index: ");
        int to = sc.nextInt();

        if (isValidIndex(from, accounts) && isValidIndex(to, accounts)) {
            System.out.print("Amount: ");
            accounts.get(from).transferAmount(accounts.get(to), sc.nextDouble());
            System.out.println("Transfer completed.");
        } else {
            System.out.println("Invalid indices.");
        }
    }

    // Metodo para Deposito e Saque
    private static void performOperation(Scanner sc, List<Account> accounts) {
        System.out.print("Enter account index: ");
        int idx = sc.nextInt();

        if (!isValidIndex(idx, accounts)) {
            System.out.println("Account not found.");
            return;
        }

        try {
            System.out.print("Deposit (D) or Withdraw (W)? ");
            char op = sc.next().toUpperCase().charAt(0);
            System.out.print("Amount: ");
            double amt = sc.nextDouble();

            if (op == 'D') accounts.get(idx).depositAmount(amt);
            else if (op == 'W') accounts.get(idx).withdrawAmount(amt);
            else throw new IllegalArgumentException("Invalid operation.");

            System.out.println("Operation successful.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Metodo para Historico
    private static void showAccountHistory(Scanner sc, List<Account> accounts) {
        System.out.print("Enter account index: ");
        int idx = sc.nextInt();
        if (isValidIndex(idx, accounts)) {
            accounts.get(idx).showHistory();
        } else {
            System.out.println("Account not found.");
        }
    }

    // Metodo utilitario para validar indices (evita repeticao de codigo)
    private static boolean isValidIndex(int index, List<Account> accounts) {
        return index >= 0 && index < accounts.size();
    }
}
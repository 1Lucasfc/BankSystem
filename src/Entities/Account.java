package Entities;

import Services.Transaction;
import Enum.TransactionType;

import java.util.ArrayList;
import java.util.List;

public abstract class Account {

    private String number;
    private String name;
    private double balance;

    // History of transactions
    private final List<Transaction> transactions = new ArrayList<>();

    public Account(String number, String name, double balance) {
        this.number = number;
        this.name = name;
        this.balance = balance;

        transactions.add(
                new Transaction(
                        TransactionType.ACCOUNT_CREATED,
                        balance,
                        balance
                )
        );
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public double depositAmount(double amount) {

        this.balance += amount;

        transactions.add(
                new Transaction(
                        TransactionType.DEPOSIT,
                        amount,
                        balance
                )
        );

        return this.balance;
    }

    public double withdrawAmount(double amount) {

        if (amount > this.balance) {
            System.out.println("Insufficient funds.");
            return this.balance;
        }

        this.balance -= amount;

        transactions.add(
                new Transaction(
                        TransactionType.WITHDRAW,
                        amount,
                        balance
                )
        );

        return this.balance;
    }

    public double transferAmount(Account destinationAccount, double amount) {

        if (amount > this.balance) {
            System.out.println("Insufficient funds for transfer.");
            return this.balance;
        }

        this.withdrawAmount(amount);
        destinationAccount.depositAmount(amount);

        transactions.add(
                new Transaction(
                        TransactionType.TRANSFER_SENT,
                        amount,
                        balance
                )
        );

        destinationAccount.transactions.add(
                new Transaction(
                        TransactionType.TRANSFER_RECEIVED,
                        amount,
                        destinationAccount.getBalance()
                )
        );

        return this.balance;
    }

    // Method to show the history of transactions
    public void showHistory() {

        System.out.println("\n--- STATEMENT: " + this.name + " (" + this.number + ") ---");

        for (Transaction entry : transactions) {
            System.out.println(entry);
        }

        System.out.println("Current Balance: $" + String.format("%.2f", balance));
    }

    @Override
    public String toString() {
        return "Account{" +
                "number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }
}

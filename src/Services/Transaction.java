package Entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {

    private static final DateTimeFormatter fmt =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private LocalDateTime moment;
    private TransactionType type;
    private double amount;
    private double balanceAfter;

    public Transaction(TransactionType type, double amount, double balanceAfter) {
        this.moment = LocalDateTime.now();
        this.type = type;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
    }

    public LocalDateTime getMoment() {
        return moment;
    }

    public TransactionType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public double getBalanceAfter() {
        return balanceAfter;
    }

    @Override
    public String toString() {
        return fmt.format(moment)
                + " | "
                + type
                + " | $"
                + String.format("%.2f", amount)
                + " | Balance: $"
                + String.format("%.2f", balanceAfter);
    }
}

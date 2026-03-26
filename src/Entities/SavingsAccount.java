package Entities;

public class SavingsAccount extends Account {

    private double interestRate = 0.14;

    public SavingsAccount(String number, String name, double balance, double interestRate) {
        super(number, name, balance);
        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void applyInterest() {
        double interest = getBalance() * (interestRate);
        depositAmount(interest);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SavingsAccount{");
        sb.append("interestRate=").append(interestRate);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public double transferAmount(Account destinationAccount, double amount) {
        return super.transferAmount(destinationAccount, amount);
    }
}

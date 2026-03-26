package Entities;

public class BusinessAccount extends Account {

    private String businessName;

    public BusinessAccount(String number, String name, double balance, String businessName) {
        super(number, name, balance);
        this.businessName = businessName;
    }

    public String getBusinessName() {
        return businessName;
    }
    @Override
    public double withdrawAmount(double amount) {
        if (amount > getBalance()) {
            throw new IllegalArgumentException("Insufficient funds.");

        }
        return super.withdrawAmount(amount) - 1.1 * amount;
        // Apply a 10% fee on the withdrawal

    }

    @Override
    public double transferAmount(Account destinationAccount, double amount) {
        return super.transferAmount(destinationAccount, amount);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BusinessAccount{");
        sb.append("businessName='").append(businessName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

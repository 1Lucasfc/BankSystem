package Entities;

public class StudentAccount extends Account {

    private String schoolName;

    public StudentAccount(String number, String name, double balance, String schoolName) {
        super(number, name, balance);
        this.schoolName = schoolName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    @Override
    public double withdrawAmount(double amount) {
        return super.withdrawAmount(amount);
    }

    @Override
    public double depositAmount(double amount) {
        return super.depositAmount(amount);
    }

    @Override
    public double transferAmount(Account destinationAccount, double amount) {
        return super.transferAmount(destinationAccount, amount);
    }
}

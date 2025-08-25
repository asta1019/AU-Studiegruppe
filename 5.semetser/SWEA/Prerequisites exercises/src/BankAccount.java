public class BankAccount {
    private double balance;

    public BankAccount(double initialBalance){
        balance = initialBalance;
    }

    public void insert(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        balance -= amount;
    }

    public double CheckBalance() {
        return balance;
    }
}

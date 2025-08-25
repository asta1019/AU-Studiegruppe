package exercise1;

// Class
public class BankAccount {
    // Private: kan ikke kaldes udenfor denne class
    // double: decimaltal
    // balance er en instance variable da den kaldes udenfor blokken
    private double balance;

    // Public: kan kaldes udenfor denne class
    // initialBalance er input
    public BankAccount(double initialBalance){
        // Balance er lig med input
        balance = initialBalance;
    }

    // Indsæt beløb på konto
    // void: der retuneres ikke et output
    public void insert(double amount) {
        balance += amount;
    }

    // Træk beløb ud fra konto
    public void withdraw(double amount) {
        balance -= amount;
    }

    // Tjek balance på kontoen
    // return: her retuneres et output
    public double CheckBalance() {
        return balance;
    }
}

package exercise1;

public class BankApp {
    public static void main(String[] args) {

        // Lav konto
        BankAccount account1 = new BankAccount(1000);
        BankAccount account2 = new BankAccount(1500);
        BankAccount account3 = new BankAccount(2000);

        // Check balance
        System.out.println("Acc1 has balance: " + account1.CheckBalance());

        // Insert
        account1.insert(200);

        System.out.println("Acc1 has balance: " + account1.CheckBalance());

        // Withdraw
        account1.withdraw(100);

        System.out.println("Acc1 has balance: " + account1.CheckBalance());

    }
}


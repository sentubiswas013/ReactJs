class Account {

    private int balance;

    Account(int balance) {
        this.balance = balance;
    }

    synchronized void deposit(int amount) {
        balance += amount;
    }

    synchronized boolean withdraw(int amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    synchronized int getBalance() {
        return balance;
    }
}

public class Main {
    public static void main(String[] args) {

        Account account = new Account(1000);
        account.deposit(500);
        account.withdraw(200);

        System.out.println(account.getBalance());
    }
}
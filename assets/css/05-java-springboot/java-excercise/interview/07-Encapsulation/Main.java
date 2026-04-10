public class Main {
    public static void main(String[] args) {

        BankAccount acc = new BankAccount();
        acc.setOwner("Alice");
        acc.deposit(1000);          // valid deposit
        acc.withdraw(200);          // valid withdraw
        acc.withdraw(5000);         // invalid — insufficient balance

        System.out.println(acc.getOwner() + " | Balance: $" + acc.getBalance());
    }
}

class BankAccount {

    private String owner;           // private — cannot access directly from outside
    private double balance;         // private — protected from direct manipulation

    public String getOwner() {                  // Getter — read only access
        return owner;
    }

    public void setOwner(String owner) {        // Setter — controlled write access
        this.owner = owner;
    }

    public double getBalance() {                // Getter — read only, no setter (balance changes via deposit/withdraw only)
        return balance;
    }

    public void deposit(double amount) {        // Controlled way to increase balance
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: $" + amount);
        } else {
            System.out.println("Invalid deposit amount");
        }
    }

    public void withdraw(double amount) {       // Controlled way to decrease balance
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: $" + amount);
        } else {
            System.out.println("Insufficient balance");
        }
    }
}

// Output:
// Deposited: $1000.0
// Withdrawn: $200.0
// Insufficient balance
// Alice | Balance: $800.0

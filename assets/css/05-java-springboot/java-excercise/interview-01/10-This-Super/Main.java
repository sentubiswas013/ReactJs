// ─────────────────────────────────────────────────────────────
// PRACTICE CODE
// ─────────────────────────────────────────────────────────────
public class Main {
    public static void main(String[] args) {
        new Manager("Alice", "Engineering", 80000);

        // Real-world
        new PremiumAccount("Bob", 5000, 0.05);
    }
}

class Employee {
    String name, dept;

    Employee(String name, String dept) {
        this.name = name;
        this.dept = dept;
    }

    void show() { System.out.println("Employee: " + name + " | Dept: " + dept); }
}

class Manager extends Employee {
    double salary;

    Manager(String name, String dept, double salary) {
        super(name, dept);          // super() — calls Employee constructor
        this.salary = salary;       // this — refers to Manager's own field
        super.show();               // super.show() — calls Employee's show()
        System.out.println("Manager salary: $" + this.salary);
    }
}

// ─────────────────────────────────────────────────────────────
// REAL-WORLD CODE
// Bank account — super sets base balance, this sets interest rate
// ─────────────────────────────────────────────────────────────
class BaseAccount {
    String owner;
    double balance;

    BaseAccount(String owner, double balance) {
        this.owner   = owner;
        this.balance = balance;
    }

    void show() { System.out.println("Account: " + owner + " | Balance: $" + balance); }
}

class PremiumAccount extends BaseAccount {
    double interestRate;

    PremiumAccount(String owner, double balance, double interestRate) {
        super(owner, balance);              // super — sets owner and balance
        this.interestRate = interestRate;   // this — sets PremiumAccount's own field
        super.show();
        System.out.println("Interest Rate: " + (this.interestRate * 100) + "%");
    }
}

// Output:
// Employee: Alice | Dept: Engineering
// Manager salary: $80000.0
// Account: Bob | Balance: $5000.0
// Interest Rate: 5.0%

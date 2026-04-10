// ─────────────────────────────────────────────────────────────
// PRACTICE CODE
// ─────────────────────────────────────────────────────────────
public class Main {
    public static void main(String[] args) {
        BankAccount acc = new BankAccount();
        acc.setOwner("Alice");
        acc.deposit(1000);
        acc.withdraw(200);
        acc.withdraw(5000);
        System.out.println(acc.getOwner() + " | $" + acc.getBalance());

        // Real-world
        Employee emp = new Employee("Bob", 50000);
        emp.appraise(10);
        System.out.println(emp.getName() + " | $" + emp.getSalary());
    }
}

class BankAccount {
    private String owner;
    private double balance;

    public String getOwner()              { return owner; }
    public void setOwner(String owner)    { this.owner = owner; }
    public double getBalance()            { return balance; }

    public void deposit(double amount) {
        if (amount > 0) { balance += amount; System.out.println("Deposited: $" + amount); }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) { balance -= amount; System.out.println("Withdrawn: $" + amount); }
        else System.out.println("Insufficient balance");
    }
}

// ─────────────────────────────────────────────────────────────
// REAL-WORLD CODE
// Employee salary — no direct access, only via controlled method
// ─────────────────────────────────────────────────────────────
class Employee {
    private String name;
    private double salary;

    Employee(String name, double salary) { this.name = name; this.salary = salary; }

    public String getName()  { return name; }
    public double getSalary() { return salary; }

    public void appraise(double percent) {          // controlled salary update
        if (percent > 0 && percent <= 50) {
            salary += salary * percent / 100;
            System.out.println("Salary updated after " + percent + "% appraisal");
        } else {
            System.out.println("Invalid appraisal percent");
        }
    }
}

// Output:
// Deposited: $1000.0  Withdrawn: $200.0  Insufficient balance
// Alice | $800.0
// Salary updated after 10.0% appraisal
// Bob | $55000.0

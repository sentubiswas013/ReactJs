public class Main {
    public static void main(String[] args) {
        new Manager("Alice", "Engineering", 80000);
    }
}

class Employee {
    String name;
    String dept;

    Employee(String name, String dept) {
        this.name = name;           // this.name → current class field
        this.dept = dept;           // this.dept → current class field
    }

    void show() {
        System.out.println("Employee: " + name + " | Dept: " + dept);
    }
}

class Manager extends Employee {
    double salary;

    Manager(String name, String dept, double salary) {
        super(name, dept);          // super() → calls Employee constructor
        this.salary = salary;       // this.salary → Manager's own field

        super.show();               // super.show() → calls Employee's show()
        System.out.println("Manager salary: $" + this.salary);
    }
}

// Output:
// Employee: Alice | Dept: Engineering
// Manager salary: $80000.0

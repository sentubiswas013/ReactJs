public class Main {
    public static void main(String[] args) {

        // Multilevel: Manager → Employee → Person
        Manager m = new Manager();
        m.breathe();        // from Person      (Single)
        m.work();           // from Employee    (Multilevel)
        m.manage();         // own method

        // Hierarchical: Developer also extends Employee
        Developer d = new Developer();
        d.breathe();        // from Person      (Hierarchical)
        d.work();           // from Employee    (Hierarchical)
        d.code();           // own method
    }
}

class Person {                              // Base class
    void breathe() {
        System.out.println("Person: breathe");
    }
}

class Employee extends Person {            // Single Inheritance
    void work() {
        System.out.println("Employee: work");
    }
}

class Manager extends Employee {           // Multilevel Inheritance
    void manage() {
        System.out.println("Manager: manage team");
    }
}

class Developer extends Employee {         // Hierarchical Inheritance
    void code() {
        System.out.println("Developer: write code");
    }
}

// Output:
// Person: breathe
// Employee: work
// Manager: manage team
// Person: breathe
// Employee: work
// Developer: write code

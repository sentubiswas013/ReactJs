import java.util.*;
import java.util.stream.Collectors;

// ─────────────────────────────────────────────────────────────
// PRACTICE CODE
// ─────────────────────────────────────────────────────────────
public class Main {
    public static void main(String[] args) {
        EMS ems = new EMS();
        ems.add(new Employee(1, "Ana", "Engineering", 85000));
        System.out.println(ems.get(1).name);

        // Real-world: HR system — search, filter, and report
        ems.add(new Employee(2, "Bob", "Marketing", 65000));
        ems.add(new Employee(3, "Carol", "Engineering", 95000));
        ems.add(new Employee(4, "Dave", "HR", 55000));

        System.out.println("\n--- Engineering Team ---");
        ems.getByDepartment("Engineering").forEach(e ->
            System.out.println(e.name + " | $" + e.salary)
        );

        System.out.println("\nHighest paid: " + ems.highestPaid().name);
        System.out.println("Avg salary: $" + ems.averageSalary());

        ems.remove(4);
        System.out.println("After removing Dave, total: " + ems.count());
    }
}

class Employee {
    int id;
    String name, department;
    double salary;

    Employee(int id, String name, String department, double salary) {
        this.id = id; this.name = name;
        this.department = department; this.salary = salary;
    }
}

// ─────────────────────────────────────────────────────────────
// REAL-WORLD CODE
// Employee Management System with search and analytics
// ─────────────────────────────────────────────────────────────
class EMS {
    Map<Integer, Employee> employees = new HashMap<>();

    void add(Employee e) { employees.put(e.id, e); }
    Employee get(int id) { return employees.get(id); }
    void remove(int id)  { employees.remove(id); }
    int count()          { return employees.size(); }

    List<Employee> getByDepartment(String dept) {
        return employees.values().stream()
            .filter(e -> e.department.equals(dept))
            .collect(Collectors.toList());
    }

    Employee highestPaid() {
        return employees.values().stream()
            .max(Comparator.comparingDouble(e -> e.salary))
            .orElse(null);
    }

    double averageSalary() {
        return employees.values().stream()
            .mapToDouble(e -> e.salary).average().orElse(0);
    }
}

// Output:
// Ana
// --- Engineering Team ---
// Ana | $85000.0
// Carol | $95000.0
// Highest paid: Carol
// Avg salary: $75000.0
// After removing Dave, total: 3

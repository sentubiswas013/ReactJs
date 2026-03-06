import java.util.*;
import java.util.HashMap;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.io.File;
import java.io.IOException;
import java.util.Comparator;

public class Test02 {
    public static void main(String[] args) {
        System.out.println("Hello, World!");

        sortSalary();

        // sortSalaryAndDepartment();

    }

    public static void sortSalary() {
        List<Integer> salary = Arrays.asList(20000, 40000, 50000, 6000, 10000);
        salary.sort(Comparator.reverseOrder())
        .subList(0, 3)
                .forEach(System.out::println);
        // salary.stream()
        //         .sorted(Comparator.reverseOrder())
        //         .forEach(System.out::println);
    }

    // public static void sortSalaryAndDepartment() {
    //     // Create a list of employees
    //     ArrayList<Employee> employees = new ArrayList<>();
    //     employees.add(new Employee("Alice", "Engineering", 90000));
    //     employees.add(new Employee("Bob", "Marketing", 75000));
    //     employees.add(new Employee("Charlie", "Engineering", 85000));
    //     employees.add(new Employee("David", "HR", 60000));
    //     employees.add(new Employee("Eve", "Marketing", 80000));

    //     // Top 3 Highest Paid Employees
    //     System.out.println("\nTop 3 Highest Paid Employees:");
    //     employees.stream()
    //             .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
    //              // .sorted((e1, e2) -> Double.compare(e2.getSalary(), e1.getSalary()))
    //              .limit(3)
    //              .forEach(System.out::println);

    // }
}


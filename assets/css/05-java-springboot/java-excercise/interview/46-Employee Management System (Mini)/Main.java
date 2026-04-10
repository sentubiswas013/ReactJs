import java.util.*;

class Employee {
    int id;
    String name;

    Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

class EMS {

    Map<Integer, Employee> employees = new HashMap<>();

    void add(Employee e) {
        employees.put(e.id, e);
    }

    Employee get(int id) {
        return employees.get(id);
    }
}

public class Main {
    public static void main(String[] args) {

        EMS ems = new EMS();
        ems.add(new Employee(1, "Ana"));

        System.out.println(ems.get(1).name);
    }
}
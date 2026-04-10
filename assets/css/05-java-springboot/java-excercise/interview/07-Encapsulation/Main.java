public class Main {
    public static void main(String[] args) {
        Employee e = new Employee();
        e.setId(1);
        e.setName("Bob");

        System.out.println(e.getId() + ": " + e.getName());
    }
}

class Employee {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


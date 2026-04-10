import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        SMS sms = new SMS();
        sms.add(new Student(1, "Alice"));
        sms.add(new Student(2, "Bob"));

        sms.list();
        System.out.println(sms.get(1).name); // Alice
    }
}


class Student {
    int id;
    String name;

    Student(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

class SMS {
    Map<Integer, Student> students = new HashMap<>();

    void add(Student s) {
        students.put(s.id, s);
    }

    Student get(int id) {
        return students.get(id);
    }

    void list() {
        students.values().forEach(s -> System.out.println(s.id + ": " + s.name));
    }
}


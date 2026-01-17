import java.util.*;

class Student {
    int id;
    String name;

    Student(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

class SMS {
    Map<Integer, Student> map = new HashMap<>();

    void add(Student s) {
        map.put(s.id, s);
    }

    Student get(int id) {
        return map.get(id);
    }
}

public class Main {
    public static void main(String[] args) {
        SMS sms = new SMS();
        sms.add(new Student(1, "Max"));
        System.out.println(sms.get(1).name);
    }
}
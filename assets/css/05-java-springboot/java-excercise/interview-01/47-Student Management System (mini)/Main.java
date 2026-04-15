import java.util.*;
import java.util.stream.Collectors;

// ─────────────────────────────────────────────────────────────
// PRACTICE CODE
// ─────────────────────────────────────────────────────────────
public class Main {
    public static void main(String[] args) {
        SMS sms = new SMS();
        sms.add(new Student(1, "Alice", 88));
        sms.add(new Student(2, "Bob", 72));

        sms.list();
        System.out.println(sms.get(1).name); // Alice

        // Real-world: school portal — grades, top students, pass/fail
        sms.add(new Student(3, "Carol", 95));
        sms.add(new Student(4, "Dave", 45));
        sms.add(new Student(5, "Eve", 60));

        System.out.println("\n--- Top Students (>= 80) ---");
        sms.topStudents(80).forEach(s -> System.out.println(s.name + " | " + s.grade));

        System.out.println("\nClass topper: " + sms.topper().name + " (" + sms.topper().grade + ")");
        System.out.println("Class average: " + sms.average());

        System.out.println("\n--- Pass/Fail (pass >= 50) ---");
        sms.all().forEach(s ->
            System.out.println(s.name + ": " + (s.grade >= 50 ? "PASS" : "FAIL"))
        );
    }
}

class Student {
    int id;
    String name;
    int grade;

    Student(int id, String name, int grade) {
        this.id = id; this.name = name; this.grade = grade;
    }
}

// ─────────────────────────────────────────────────────────────
// REAL-WORLD CODE
// Student Management System with grade analytics
// ─────────────────────────────────────────────────────────────
class SMS {
    Map<Integer, Student> students = new HashMap<>();

    void add(Student s)    { students.put(s.id, s); }
    Student get(int id)    { return students.get(id); }
    Collection<Student> all() { return students.values(); }

    void list() {
        students.values().forEach(s -> System.out.println(s.id + ": " + s.name + " | " + s.grade));
    }

    List<Student> topStudents(int minGrade) {
        return students.values().stream()
            .filter(s -> s.grade >= minGrade)
            .sorted((a, b) -> b.grade - a.grade)
            .collect(Collectors.toList());
    }

    Student topper() {
        return students.values().stream()
            .max(Comparator.comparingInt(s -> s.grade))
            .orElse(null);
    }

    double average() {
        return students.values().stream().mapToInt(s -> s.grade).average().orElse(0);
    }
}

// Output:
// 1: Alice | 88
// 2: Bob | 72
// Alice
// --- Top Students (>= 80) ---
// Carol | 95
// Alice | 88
// Class topper: Carol (95)
// Class average: 72.0
// --- Pass/Fail ---
// Alice: PASS  Bob: PASS  Carol: PASS  Dave: FAIL  Eve: PASS

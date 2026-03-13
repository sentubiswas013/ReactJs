import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Student {

    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private String departmantName;
    private int joinedYear;
    private String city;
    private int rank;

    // Constructor
    public Student(int id, String firstName, String lastName, int age, String gender,
                   String departmantName, int joinedYear, String city, int rank) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.departmantName = departmantName;
        this.joinedYear = joinedYear;
        this.city = city;
        this.rank = rank;
    }

    // Getters
    public int getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
    public String getDepartmantName() { return departmantName; }
    public int getJoinedYear() { return joinedYear; }
    public String getCity() { return city; }
    public int getRank() { return rank; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setAge(int age) { this.age = age; }
    public void setGender(String gender) { this.gender = gender; }
    public void setDepartmantName(String departmantName) { this.departmantName = departmantName; }
    public void setJoinedYear(int joinedYear) { this.joinedYear = joinedYear; }
    public void setCity(String city) { this.city = city; }
    public void setRank(int rank) { this.rank = rank; }

    @Override
    public String toString() {
        return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName +
                ", age=" + age + ", gender=" + gender + ", departmantName=" + departmantName +
                ", joinedYear=" + joinedYear + ", city=" + city + ", rank=" + rank + "]";
    }

    public static void main(String[] args) {

        List<Student> studlist = Arrays.asList(
                new Student(1, "Rohit", "Kumar", 30, "Male", "Mechanical Engineering", 2015, "Mumbai", 122),
                new Student(2, "Pulkit", "Singh", 56, "Male", "Computer Engineering", 2018, "Delhi", 67),
                new Student(3, "Ankit", "Patil", 25, "Female", "Mechanical Engineering", 2019, "Kerala", 164),
                new Student(4, "Satish", "Malag", 30, "Male", "Mechanical Engineering", 2014, "Kerala", 26),
                new Student(5, "Roshan", "Mukd", 23, "Male", "Biotech Engineering", 2022, "Mumbai", 12),
                new Student(6, "Chetan", "Star", 24, "Male", "Mechanical Engineering", 2023, "Karnataka", 90),
                new Student(7, "Arun", "Vittal", 26, "Male", "Electronics Engineering", 2014, "Karnataka", 324),
                new Student(8, "Nam", "Dev", 31, "Male", "Computer Engineering", 2014, "Karnataka", 433),
                new Student(9, "Sonu", "Shankar", 27, "Female", "Computer Engineering", 2018, "Karnataka", 7),
                new Student(10, "Shubham", "Pandey", 26, "Male", "Instrumentation Engineering", 2017, "Mumbai", 98)
        );

        // 1. Students whose name starts with A
        List<Student> studentNameA = studlist.stream()
                .filter(s -> s.getFirstName().startsWith("A"))
                .collect(Collectors.toList());
        System.out.println("Students whose name starts with A: " + studentNameA);

        // 2. Group by department
        Map<String, List<Student>> departMentList =
                studlist.stream().collect(Collectors.groupingBy(Student::getDepartmantName));
        System.out.println("Group by department: " + departMentList);

        // 3. Count students
        long count = studlist.stream().count();
        System.out.println("Total students: " + count);

        // 4. Max age
        OptionalInt maxAge = studlist.stream().mapToInt(Student::getAge).max();
        System.out.println("Max age: " + maxAge.getAsInt());

        // 5. Unique departments
        List<String> lstDepartments = studlist.stream()
                .map(Student::getDepartmantName)
                .distinct()
                .collect(Collectors.toList());
        System.out.println("Departments: " + lstDepartments);

        // 6. Count students in each department
        Map<String, Long> countStudentEachDepartment =
                studlist.stream().collect(Collectors.groupingBy(Student::getDepartmantName, Collectors.counting()));
        System.out.println("Students in each department: " + countStudentEachDepartment);

        // 7. Students age < 30
        List<Student> lessAge30 = studlist.stream()
                .filter(s -> s.getAge() < 30)
                .collect(Collectors.toList());
        System.out.println("Students below 30: " + lessAge30);

        // 8. Rank between 50 and 100
        List<Student> rankList = studlist.stream()
                .filter(st -> st.getRank() > 50 && st.getRank() < 100)
                .collect(Collectors.toList());
        System.out.println("Rank between 50 and 100: " + rankList);

        // 9. Average age by gender
        Map<String, Double> mapAvgAge =
                studlist.stream().collect(Collectors.groupingBy(Student::getGender, Collectors.averagingInt(Student::getAge)));
        System.out.println("Average age by gender: " + mapAvgAge);

        // 10. Department with max students
        Entry<String, Long> entry = studlist.stream()
                .collect(Collectors.groupingBy(Student::getDepartmantName, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue()).get();
        System.out.println("Department with max students: " + entry);

        // 11. Students in Delhi sorted by name
        List<Student> studentLocation = studlist.stream()
                .filter(dt -> dt.getCity().equals("Delhi"))
                .sorted(Comparator.comparing(Student::getFirstName))
                .collect(Collectors.toList());
        System.out.println("Students in Delhi sorted by name: " + studentLocation);

        // 12. Average rank per department
        Map<String, Double> avgRankDept =
                studlist.stream().collect(Collectors.groupingBy(Student::getDepartmantName, Collectors.averagingInt(Student::getRank)));
        System.out.println("Average rank by department: " + avgRankDept);

        // 13. Highest rank in each department
        Map<String, Optional<Student>> studentData =
                studlist.stream().collect(Collectors.groupingBy(
                        Student::getDepartmantName,
                        Collectors.minBy(Comparator.comparing(Student::getRank))
                ));
        System.out.println("Highest rank in each department: " + studentData);

        // 14. Sort students by rank
        List<Student> stuRankSorted = studlist.stream()
                .sorted(Comparator.comparing(Student::getRank))
                .collect(Collectors.toList());
        System.out.println("Students sorted by rank: " + stuRankSorted);

        // 15. Second highest rank
        Student student = studlist.stream()
                .sorted(Comparator.comparing(Student::getRank))
                .skip(1)
                .findFirst()
                .get();
        System.out.println("Second highest rank student: " + student);
    }
}
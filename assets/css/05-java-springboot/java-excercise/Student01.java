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

    public int getId() { return id; }
    public String getFirstName() { return firstName; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
    public String getDepartmantName() { return departmantName; }
    public String getCity() { return city; }
    public int getRank() { return rank; }

    public static void main(String[] args) {

        List<Student> studlist = Arrays.asList(
                new Student(1,"Rohit","Kumar",30,"Male","Mechanical Engineering",2015,"Mumbai",122),
                new Student(2,"Pulkit","Singh",56,"Male","Computer Engineering",2018,"Delhi",67),
                new Student(3,"Ankit","Patil",25,"Female","Mechanical Engineering",2019,"Kerala",164),
                new Student(4,"Satish","Malag",30,"Male","Mechanical Engineering",2014,"Kerala",26),
                new Student(5,"Roshan","Mukd",23,"Male","Biotech Engineering",2022,"Mumbai",12),
                new Student(6,"Chetan","Star",24,"Male","Mechanical Engineering",2023,"Karnataka",90),
                new Student(7,"Arun","Vittal",26,"Male","Electronics Engineering",2014,"Karnataka",324),
                new Student(8,"Nam","Dev",31,"Male","Computer Engineering",2014,"Karnataka",433),
                new Student(9,"Sonu","Shankar",27,"Female","Computer Engineering",2018,"Karnataka",7),
                new Student(10,"Shubham","Pandey",26,"Male","Instrumentation Engineering",2017,"Mumbai",98)
        );

        // 1. Students whose name starts with A
        List<Student> studentNameA = studlist.stream()
                .filter(s -> s.getFirstName().startsWith("A"))
                .collect(Collectors.toList());

        System.out.println("1. Students whose name starts with A: " + studentNameA);


        // 2. Group students by department
        Map<String,List<Student>> deptGroup =
                studlist.stream().collect(Collectors.groupingBy(Student::getDepartmantName));

        System.out.println("2. Group by department: " + deptGroup);


        // 3. Count total students
        long count = studlist.stream().count();
        System.out.println("3. Total students: " + count);


        // 4. Find maximum age
        OptionalInt maxAge =
                studlist.stream().mapToInt(Student::getAge).max();

        System.out.println("4. Max age: " + maxAge.getAsInt());


        // 5. Get unique departments
        List<String> departments =
                studlist.stream()
                        .map(Student::getDepartmantName)
                        .distinct()
                        .collect(Collectors.toList());

        System.out.println("5. Unique departments: " + departments);


        // 6. Count students in each department
        Map<String,Long> countDept =
                studlist.stream()
                        .collect(Collectors.groupingBy(
                                Student::getDepartmantName,
                                Collectors.counting()));

        System.out.println("6. Students in each department: " + countDept);


        // 7. Students age less than 30
        List<Student> lessAge30 =
                studlist.stream()
                        .filter(s -> s.getAge() < 30)
                        .collect(Collectors.toList());

        System.out.println("7. Students age < 30: " + lessAge30);


        // 8. Students rank between 50 and 100
        List<Student> rankList =
                studlist.stream()
                        .filter(s -> s.getRank() > 50 && s.getRank() < 100)
                        .collect(Collectors.toList());

        System.out.println("8. Rank between 50 and 100: " + rankList);


        // 9. Average age by gender
        Map<String,Double> avgAgeGender =
                studlist.stream()
                        .collect(Collectors.groupingBy(
                                Student::getGender,
                                Collectors.averagingInt(Student::getAge)));

        System.out.println("9. Average age by gender: " + avgAgeGender);


        // 10. Department with maximum students
        Entry<String,Long> maxDept =
                studlist.stream()
                        .collect(Collectors.groupingBy(
                                Student::getDepartmantName,
                                Collectors.counting()))
                        .entrySet()
                        .stream()
                        .max(Map.Entry.comparingByValue())
                        .get();

        System.out.println("10. Department with max students: " + maxDept);

    }
}
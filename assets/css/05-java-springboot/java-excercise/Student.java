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
            .filter(el -> el.getAge() > 30).toList();
        
        System.out.println("Students whose name starts with A: " + studentNameA);
        // Output: Students whose name starts with A: [Student [id=3, firstName=Ankit, lastName=Patil, age=25, gender=Female, departmantName=Mechanical Engineering, joinedYear=2019, city=Kerala, rank=164], Student [id=7, firstName=Arun, lastName=Vittal, age=26, gender=Male, departmantName=Electronics Engineering, joinedYear=2014, city=Karnataka, rank=324]]

        // 2. Group by department
       
        // System.out.println("Group by department: " + departMentList);
        // Output: Group by department: {Mechanical Engineering=[Student [id=1, firstName=Rohit, lastName=Kumar, age =30, gender=Male, departmantName=Mechanical Engineering, joinedYear=2015, city=Mumbai, rank=122], Student [id=3, firstName=Ankit, lastName=Patil, age=25, gender=Female    , departmantName=Mechanical Engineering, joinedYear=2019, city=Kerala, rank=164], Student [id=4, firstName=Satish, lastName=Malag,

        // 3. Count students
        
        // System.out.println("Total students: " + count);
        // Output: Total students: 10

        // 4. Max age
       
        // System.out.println("Max age: " + maxAge.getAsInt());
        // Output: Max age: 56

        // 5. Unique departments
       
        // System.out.println("Departments: " + lstDepartments);
        // Output: Departments: [Mechanical Engineering, Computer Engineering, Biotech Engineering, Electronics Engineering, Instrumentation Engineering]

        // 6. Count students in each department
       
        // System.out.println("Students in each department: " + countStudentEachDepartment);
        // Output: Students in each department: {Mechanical Engineering=3, Computer Engineering=3, Biotech Engineering=1, Electronics Engineering=1, Instrumentation Engineering=1}

        // 7. Students age < 30
        
        // System.out.println("Students below 30: " + lessAge30);
        // Output: Students below 30: [Student [id=3, firstName=Ankit, lastName=Patil, age

        // 8. Rank between 50 and 100
        
        // System.out.println("Rank between 50 and 100: " + rankList);
        // Output: Rank between 50 and 100: [Student [id=2, firstName=Pulkit, lastName=Singh,

        // 9. Average age by gender
        
        // System.out.println("Average age by gender: " + mapAvgAge);
        // Output: Average

        // 10. Department with max students
        
        // System.out.println("Department with max students: " + entry);
        // Output: Department with max students: Computer Engineering=3

        // 11. Students in Delhi sorted by name
        
        // System.out.println("Students in Delhi sorted by name: " + studentLocation);
        // Output: Students in Delhi sorted by name: [Student [id=2, firstName=Pulkit, lastName=Singh, 

        // 12. Average rank per department
        
        // System.out.println("Average rank by department: " + avgRankDept);
        // Output: Average rank by department: {Mechanical Engineering=100.66666666666667, Computer Engineering=169.0, Biotech Engineering=12.0, Electronics Engineering=324.0, Instrumentation Engineering=98.0}

        // 13. Highest rank in each department
       
        // System.out.println("Highest rank in each department: " + studentData);
        // Output: Highest rank in each department: {Mechanical Engineering=Optional[Student [id=4, firstName=Satish, lastName=Malag, age

        // 14. Sort students by rank
        
        // System.out.println("Students sorted by rank: " + stuRankSorted);
        // Output: Students sorted by rank: [Student [id=5, firstName=Roshan, lastName=Mukd, age

        // 15. Second highest rank
        
        // System.out.println("Second highest rank student: " + student);
        // Output: Second highest rank student: Student [id=10, firstName=Shubham, lastName
    }
}
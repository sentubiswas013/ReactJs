import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

class StreamEmployee00 {
        
static class Employee {

    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private String departmentName;
    private int joinedYear;
    private String city;
    private int salary;

    // Constructor
    public Employee(int id, String firstName, String lastName, int age, String gender,
                    String departmentName, int joinedYear, String city, int salary) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.departmentName = departmentName;
        this.joinedYear = joinedYear;
        this.city = city;
        this.salary = salary;
    }

    // Getters
    public int getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
    public String getDepartmentName() { return departmentName; }
    public int getJoinedYear() { return joinedYear; }
    public String getCity() { return city; }
    public int getSalary() { return salary; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setAge(int age) { this.age = age; }
    public void setGender(String gender) { this.gender = gender; }
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public void setJoinedYear(int joinedYear) {
        this.joinedYear = joinedYear;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id +
                ", firstName=" + firstName +
                ", lastName=" + lastName +
                ", age=" + age +
                ", gender=" + gender +
                ", departmentName=" + departmentName +
                ", joinedYear=" + joinedYear +
                ", city=" + city +
                ", salary=" + salary + "]";
    }
}
    public static void main(String[] args) {

        List<Employee> empList = Arrays.asList(

                new Employee(1, "Rohit", "Kumar", 30,
                        "Male", "Mechanical", 2015,
                        "Mumbai", 55000),

                new Employee(2, "Pulkit", "Singh", 56,
                        "Male", "IT", 2018,
                        "Delhi", 85000),

                new Employee(3, "Ankit", "Patil", 25,
                        "Female", "Mechanical", 2019,
                        "Kerala", 45000),

                new Employee(4, "Satish", "Malag", 30,
                        "Male", "Mechanical", 2014,
                        "Kerala", 95000),

                new Employee(5, "Roshan", "Mukd", 23,
                        "Male", "Biotech", 2022,
                        "Mumbai", 35000),

                new Employee(6, "Chetan", "Star", 24,
                        "Male", "Mechanical", 2023,
                        "Karnataka", 50000),

                new Employee(7, "Arun", "Vittal", 26,
                        "Male", "Electronics", 2014,
                        "Karnataka", 70000),

                new Employee(8, "Nam", "Dev", 31,
                        "Male", "IT", 2014,
                        "Karnataka", 60000),

                new Employee(9, "Sonu", "Shankar", 27,
                        "Female", "IT", 2018,
                        "Karnataka", 99000),

                new Employee(10, "Shubham", "Pandey", 26,
                        "Male", "Instrumentation", 2017,
                        "Mumbai", 65000)
        );

        // 1. Employees whose name starts with A
        List<Employee> employeeNameA = empList.stream()
                .filter(e -> e.getFirstName().startsWith("A"))
                .collect(Collectors.toList());

        System.out.println("\n1. Employees whose name starts with A: " + employeeNameA);

        // Output:
        // 1. Employees whose name starts with A:
        // [Employee [id=3, firstName=Ankit, lastName=Patil, age=25,
        // gender=Female, departmentName=Mechanical, joinedYear=2019,
        // city=Kerala, salary=45000]]



        // 2. Group employees by department
        

        // System.out.println("\n2. Employees grouped by department: " + departmentList);

        // Output:
        // 2. Employees grouped by department:
        // {Mechanical=[Employee...], IT=[Employee...], Biotech=[Employee...]}



        // 3. Count employees
       

        // System.out.println("\n3. Total employees: " + count);

        // Output:
        // 3. Total employees: 10



        // 4. Maximum age
        

        // System.out.println("\n4. Maximum age: " + maxAge.getAsInt());

        // Output:
        // 4. Maximum age: 56



        // 5. Unique departments
        

        // System.out.println("\n5. Unique departments: " + departments);

        // Output:
        // 5. Unique departments:
        // [Mechanical, IT, Biotech, Electronics, Instrumentation]



        // 6. Count employees in each department
        

        // System.out.println("\n6. Employee count by department: " + employeeCountByDept);

        // Output:
        // 6. Employee count by department:
        // {Mechanical=4, IT=3, Biotech=1, Electronics=1, Instrumentation=1}



        // 7. Employees age less than 30
        

        // System.out.println("\n7. Employees age below 30: " + lessThan30);

        // Output:
        // 7. Employees age below 30:
        // [Employee [id=3,...], Employee [id=5,...]]



        // 8. Salary between 50000 and 100000
        

        // System.out.println("\n8. Employees salary between 50k and 100k: " + salaryRange);

        // Output:
        // 8. Employees salary between 50k and 100k:
        // [Employee [id=1,...], Employee [id=2,...]]



        // 9. Average age by gender
        

        // System.out.println("\n9. Average age by gender: " + avgAgeByGender);

        // Output:
        // 9. Average age by gender:
        // {Male=30.857142857142858, Female=26.0}



        // 10. Department with maximum employees
        

        // System.out.println("\n10. Department with maximum employees: " + maxDept);

        // Output:
        // 10. Department with maximum employees:
        // Mechanical=4



        // 11. Employees in Delhi sorted by name
        

        // System.out.println("\n11. Employees in Delhi sorted by name: " + delhiEmployees);

        // Output:
        // 11. Employees in Delhi sorted by name:
        // [Employee [id=2, firstName=Pulkit,...]]



        // 12. Average salary by department
        

        // System.out.println("\n12. Average salary by department: " + avgSalaryByDept);

        // Output:
        // 12. Average salary by department:
        // {Mechanical=61250.0, IT=81333.33333333333, Biotech=35000.0,
        // Electronics=70000.0, Instrumentation=65000.0}



        // 13. Highest salary employee in each department
        

        // System.out.println("\n13. Highest salary employee in each department: " + highestSalaryEmployee);

        // Output:
        // 13. Highest salary employee in each department:
        // {Mechanical=Optional[Employee...], IT=Optional[Employee...]}



        // 14. Sort employees by salary
        

        // System.out.println("\n14. Employees sorted by salary: " + sortedBySalary);

        // Output:
        // 14. Employees sorted by salary:
        // [Employee [id=5,...], Employee [id=3,...]]



        // 15. Second highest salary employee
        

        // System.out.println("\n15. Second highest salary employee: " + secondHighestSalary);

        // Output:
        // 15. Second highest salary employee:
        // Employee [id=2, firstName=Pulkit, lastName=Singh,
        // age=56, gender=Male, departmentName=IT,
        // joinedYear=2018, city=Delhi, salary=85000]
    }


}
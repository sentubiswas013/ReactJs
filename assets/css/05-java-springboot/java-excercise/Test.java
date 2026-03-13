import java.util.*;
import java.util.stream.*;

// https://medium.com/@asishpanda444/stream-api-coding-qna-8df8682b7e2a
public class Test {
    public static void main(String[] args) {
        String sentence="Java Stream API is very powerful";
        Integer nums = 14862;

        List<Integer> nums = Arrays.asList(10,20,30,40,50);
        List<String> words = Arrays.asList("Java","Stream","API","Development");


        
        System.out.println("Hello World");

    }

    
}

class Student {
    String name;
    int score;

    Student(String name,int score){
        this.name=name;
        this.score=score;
    }

    public String getName(){return name;}
    public int getScore(){return score;}
}

class Employee {
    String name;
    String department;
    int age;

    Employee(String name,String department,int age){
        this.name=name;
        this.department=department;
        this.age=age;
    }

    public String getDepartment(){return department;}
}

class EmployeeSalary {
    String name;
    String department;
    double salary;

    EmployeeSalary(String name,String department,double salary){
        this.name=name;
        this.department=department;
        this.salary=salary;
    }

    public String getDepartment(){return department;}
    public double getSalary(){return salary;}
}
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test03 {
    
    static class Employee {
        private String department;
        private double salary;

        public Employee(String department, double salary) {
            this.department = department;
            this.salary = salary;
        }

        public String getDepartment() {
            return department;
        }

        public double getSalary() {
            return salary;
        }

        @Override
        public String toString() {
            return department + " : " + salary;
        }
    }

    public static void main(String[] args) {
        String sentence="Java Stream API is very powerful";
        List<Integer> num01 = Arrays.asList(0,1,7,2,3,0,4,5,0,6,9);
        List<Integer> num02 = Arrays.asList(6,1,0,2,3,4,0,2,5,1,0);
        List<String> words = Arrays.asList("java","stream","api", "level", "madam");
        List<String> names = Arrays.asList("Alice","Bob","Annie","Alex");

        // =======================================================
        // 1. Filter Even Numbers
        List<Integer> even = num01.stream()
            .filter(n -> n%2 == 0)
            .toList();
        

        System.out.println("1. Even Numbers: " + even);
        // Output: 1. Even Numbers: [2, 4, 6]


        // =======================================================
        // 2. Find Maximum and minimum Element
        

        // System.out.println("2. Maximum: " + max);
        // System.out.println("2. Minimum: " + min);
        // Output: 2. Maximum: 6


        // =======================================================
        // 3. Sort List in Descending Order
        

        // System.out.println("3. Sorted Descending: " + sorted);
        // Output: 3. Sorted Descending: [6, 5, 4, 3, 2, 1]


        // =======================================================
        // 4. Count Strings with Specific Prefix        
        

        // System.out.println("4. Count starting with A: " + count);
        // Output: 4. Count starting with A: 3


        // =======================================================
        // 5. Find First Non-Repeated Character
        

        // System.out.println("5. First Non-Repeated: " + result);
        // Output: 5. First Non-Repeated: 


        // =======================================================
        // 6. Convert List to Uppercase        
        

        // System.out.println("6. Uppercase: " + upper);
        // Output: 6. Uppercase: [JAVA, STREAM, API]


        // =======================================================
        // 7. Sum and average of Numbers
        

        // System.out.println("7. Sum: " + sum);
        // System.out.println("7. average: " + average);
        // Output: 7. Sum: 21


        // =======================================================
        // 8. Check if Any String Contains and equals Word
    

        // System.out.println("8. Contains api: " + Contains);
        // Output: 8. Contains api: [api]


        // =======================================================
        // 9. Find Duplicate Elements        
        

        // System.out.println("9. Duplicates: " + duplicates);
        // Output: 9. Duplicates: [1, 2]


        // =======================================================
        // 10. Remove Duplicates
        

        // System.out.println("17. Unique: " + unique);
        // Output: 17. Unique: [1, 2, 3, 4, 5]


        // =======================================================
        // 11. Find Longest and smallest String
        

        // System.out.println("10. Longest: " + longest);
        // System.out.println("10. smallest: " + smallest);
        // System.out.println("10. Longest: " + longestWord);
        // Output: 10. Longest: stream


        // =======================================================
        // 12. Find Common Elements Between Two Lists
        

        // System.out.println("11. Common: " + common);
        // Output: 11. Common: [3, 4]


        // =======================================================
        // 13. Find Top N Elements
        

        // System.out.println("12. Top 3: " + top3);
        // Output: 12. Top 3: [6, 5, 4]

        // =======================================================
        // 14. Find Nth Largest Element
        

        // System.out.println("16. Third Largest: " + thirdLargest);
        // output: 16. Third Largest: 4


        // =======================================================
        // 15. Count Frequency of Characters
        

        // System.out.println("13. Frequency: " + freq);
        // Output: 13. Frequency: {s=3, u=1, c=2, e=1}


        // =======================================================
        // 16. To merge two arrays and sort the resulting array in ascending order
        

        // System.out.println(resultSort);
        // Output: 14. Result: [0, 0, 0, 1, 1, 1, 2, 2, 3, 3, 4, 6, 7]


        // =======================================================
        // 16. 0 should go to outside without change order
        

        // System.out.println(resultRight);
        // Output: [1, 7, 2, 3, 4, 5, 6, 9, 0, 0, 0]


        // =======================================================
        // 17. Partition Even and Odd Numbers
        

        // System.out.println("15. Partition: " + partition);
        // Output: 15. Partition: {false=[1, 3, 5], true=[2, 4, 6]}


        // =======================================================
        // 18. Join Strings
        

        // System.out.println("18. Joined: " + joined);
        // Output: 18. Joined: java, stream, api


        // =======================================================
        // 19. Remove Null Values
        

        // System.out.println("19. Clean: " + clean);
        // Output: 19. Clean: [java, stream, api]


        // =======================================================
        // 21. Reverse Each String
        

        // System.out.println("23. Reversed: " + reversed);
        // Output: 23. Reversed: [avaj, maerts, ipa]


        // =======================================================
        // 22. Find Palindromes
        

        // System.out.println("22. Palindromes: " + palindromes);
        // Output: 22. Palindromes: []


        // =======================================================
        // 23. Convert List to Map and word length
        

        // System.out.println("21. Map: " + map);
        // Output: 21. Map: {java=4, stream=6, api=3}
        // Map:  {3=[api], 4=[java], 5=[level, madam], 6=[stream]}


        // =======================================================
        // 24. Group by First Character
        

        // System.out.println("25. Grouped by First Char: " + mapByFirst);
        // Output: 25. Grouped by First Char: {a=[api], j=[java], s=[stream]}


        // =======================================================
        // 25. Find Salary by Department
        List<Employee> employees = Arrays.asList(
                new Employee("IT", 50000),
                new Employee("HR", 40000),
                new Employee("IT", 60000),
                new Employee("HR", 45000)
        );


        // Salary by Department
        

        // Sceond height salary 
        

        // System.out.println(salaryByDept);
        // System.out.println(salaryList);     
        //     [IT, HR, IT, HR]
        //     [50000.0, 40000.0, 60000.0, 45000.0]


        // =======================================================
        // 26. Find Average Salary by Department
        

        // System.out.println(avgSalary);
        // Output: 26. Highest Salary by Dept: {HR=42500.0, IT=55000.0, TR=40000.0}


        // =======================================================
        // 27. Count Employees in Each Department
        

        // System.out.println(countByDept);
        // Output: {HR=2, IT=2}


        // =======================================================
        // 28. Find All Employees Grouped by Department
        

        // System.out.println(employeesByDept);
        // Output: {HR=[Employee@..., Employee@...], IT=[Employee@..., Employee@...]} 


        // =======================================================
        // 29. flatMap() is used to flatten nested collections.
        List<List<Employee>> inputTemp = List.of(
                List.of(new Employee("John", 5000), new Employee("Sam", 6000)),
                List.of(new Employee("David", 7000), new Employee("Mary", 8000))
        );
        

        // System.out.println(resultTemp);
        // Output: [Employee@..., Employee@..., Employee@..., Employee@...] (all employees in a single list)


    }
}

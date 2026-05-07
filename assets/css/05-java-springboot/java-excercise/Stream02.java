import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Stream30 {
    
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
        String input = "madam";
        String sentence="Java Stream API is very powerful";
        List<Integer> num01 = Arrays.asList(0,1,7, 3,0,4,5,0,6,9);
        List<Integer> num02 = Arrays.asList(6,1,0,2,3,4,0,2,5,1,0);
        List<String> arr01 = Arrays.asList("java","stream","api", "level", "madam");
        List<String> arr02 = Arrays.asList("Alice","Bob","Annie","Alex", null);


        // =======================================================
        // 1. Filter Even Numbers   // filter: Remove unwanted elements  // toList: Convert stream to List
        // even ----------------------
        

        // System.out.println("1. Even Numbers: " + even);
        // Output: 1. Even Numbers: [2, 4, 6]



        // =======================================================
        // 2. Find Common Elements Between Two List
        

        // System.out.println("11. Common: " + common);
        // Output: 11. Common: [0, 1, 2, 3, 0, 4, 5, 0, 6]



        // =======================================================
        // 3. Count Strings with Specific Prefix        
        

        // System.out.println("4. Count starting with A: " + count);
        // Output: 4. Count starting with A: 3



        // =======================================================
        // 4. Check if Any String Contains and equals Word
        


        // System.out.println("8. Contains api: " + Contains);
        // Output: 8. Contains api: [api]



        // =======================================================
        // 5. Remove Null Values and missing number from array
        // Remove Null Values --------------------------------
        

        // System.out.println("19. Clean: " + clean);
        // Output: 19. Clean: [java, stream, api]


        // missing number from array --------------------------
       

        // System.out.println("19. Missing Number: " + missing);
        // Output: 19. Clean: [2, 8]


        // =======================================================
        // 6. Find Palindromes
        

        

        // System.out.println("22. Palindromes: " + palindromes);
        // Output: 22. Palindromes: []



        // =======================================================
        // 7. Find Duplicate Elements        
        

        // System.out.println("9. Duplicates: " + duplicates);
        // Output: 9. Duplicates: [1, 2]



        // =======================================================
        // 8. Remove Duplicates
       

        // System.out.println("17. Unique: " + unique);
        // Output: 17. Unique: [6, 1, 0, 2, 3, 4, 5]



        // =======================================================
        // 9. Find Maximum and minimum number
        // max -------------------------------
        

        // min --------------------------------
       

        // System.out.println("2. Maximum: " + max);
        // System.out.println("2. Minimum: " + min);
        // Output: 2. Maximum: 6



        // =======================================================
        // 10. Find Longest and smallest String
        // longest -------------------------------
        

        // smallest -------------------------------
        

        // longestWord ----------------------------
        

        // System.out.println("10. Longest: " + longest);
        // System.out.println("10. smallest: " + smallest);
        // System.out.println("10. Longest: " + longestWord);
        // Output: 10. Longest: stream



        // =======================================================
        // 11. Sort List in Assending and Descending Order
        // sortedAsc  --------------------
       


        // System.out.println("3. Sorted Ascending: " + sortedAsc);
        // Output: 3. Sorted Ascending: [0, 0, 0

        // sortedDsc ---------------------
       

        // System.out.println("3. Sorted Descending: " + sortedDsc);
        // Output: 3. Sorted Descending: [6, 5, 4, 3, 2, 1]


        // =======================================================
        // 12. Find Top N Elements
        

        // System.out.println("12. Top 3: " + top3);
        // Output: 12. Top 3: [6, 5, 4]

        // sortedArr -----------------------
        

        // System.out.println(sortedArr);
        // Output: [Bob, Alex, Alice, Annie]


        // =======================================================
        // 13. Find Nth Largest Element
        

        // System.out.println("16. Third Largest: " + thirdLargest);
        // output: 16. Third Largest: 4



        // =======================================================
        // 14. To merge two arrays and sort the resulting array in ascending order
        

        // System.out.println(resultSort);
        // Output: 14. Result: [0, 0, 0, 1, 1, 1, 2, 2, 3, 3, 4, 6, 7]



        // =======================================================
        // 15. 0 should go to outside without change order
        

        // System.out.println(resultRight);
        // Output: [1, 7, 2, 3, 4, 5, 6, 9, 0, 0, 0]



        // =======================================================
        // 16. Count Frequency of Characters
        

        // System.out.println("13. Frequency: " + freq);
        // Output: 13. Frequency: {s=3, u=1, c=2, e=1}



        // =======================================================
        // 17. Check Vowel numbers mapping 
       
                

        // System.out.println("vowels: " + vowels);
        // Output: 5. {a=2}                



        // =======================================================
        // 18. Find First Non-Repeated Character
        

        // System.out.println("5. First Non-Repeated: " + result);
        // Output: 5. First Non-Repeated: d



        // =======================================================
        // 19. Sum and average of Numbers

        

        // System.out.println("7. Sum: " + sum);  Output: 7

        // average --------------------------------------------
        


        // System.out.println("7. average: " + average); Output: Sum: 21


        // =======================================================
        // 20. Convert List to Uppercase    // Map: Transform elements    
        

        // System.out.println("6. Uppercase: " + upper);
        // Output: 6. Uppercase: [JAVA, STREAM, API]
        


        // =======================================================
        // 21. Reverse Each String ------------------------------
       
        // System.out.println("23. Reversed: " + reversedList);
        // Output: 23. Reversed: [avaj, maerts, ipa]


        // Check if input string is Palindrome --------------------
        

        // Output: Palindrome


        // =======================================================
        // 22. Partition Even and Odd Numbers // collect: Convert stream to collection
        

        // System.out.println("15. Partition: " + partition);
        // Output: 15. Partition: {false=[1, 3, 5], true=[2, 4, 6]}



        // =======================================================
        // 23. Join Strings
        

        // System.out.println("18. Joined: " + joined);
        // Output: 18. Joined: java, stream, api
        


        // =======================================================
        // 24. Convert List to Map and word length
        // Map from String ------------------------------
        

        // System.out.println("21. mapStr: " + mapStr);
        // Output: 21. Map: {Java=4, very=4, powerful=8, Stream=6, API=3, is=2}  

        // Map from array ------------------------------
       

        // System.out.println("21. mapArr: " + mapArr);
        // Output: 21. Map: {3=[api], 4=[java], 5=[level, madam], 6=[stream]} 



        // =======================================================
        // 25. Group by First Character
        

        // System.out.println("25. Grouped by First Char: " + mapByFirst);
        // Output: 25. Grouped by First Char: {a=[api], j=[java], s=[stream]}


        // =======================================================
        List<Employee> employees = Arrays.asList(
                new Employee("IT", 50000),
                new Employee("HR", 40000),
                new Employee("IT", 60000),
                new Employee("HR", 45000)
        );

        // =======================================================
        // 26. Get Departments 
       
        
        // System.out.println("departments: " + department);
        // Output: Departments: [IT, HR, IT, HR]

        // System.out.println("departments: " + salary);
        // Output: salary:  [50000.0, 40000.0, 60000.0, 45000.0]

        // =======================================================
        // 27. Average Salary by Department
               	
        		
        

        // System.out.println("Avg Salary by Dept: " + avgSalaryByDep);
        // Output: Avg Salary by Dept: {HR=42500.0, IT=55000.0}

        // Salary by Department ------------------------------
            		

        
        //System.out.println("Overall Avg Salary: " + salaryByDept);
        // Output: Salary by Dept: {HR=[40000.0, 45000.0], IT=[50000.0, 60000.0]}


        // Overall average  ----------------------------------
        

        // System.out.println("Overall Avg Salary: " + avgSalary);
        // Output: Overall Avg Salary: 48750.0

        // =======================================================
        // 28. Second Highest Salary
        

        // System.out.println("Second Highest Employee: " + secondHighest);
        // Output: Second Highest Salary: 50000.0

        // OR just salary --------------------------------------
        

        // System.out.println("Second Highest Salary: " + secondHighestSalary);
        // Output: Second Highest Salary: 50000.0


        // =======================================================
        // 29. Count Employees by Department
        

        // System.out.println("Count by Dept: " + countByDept);
        // Output: Count by Dept: {HR=2, IT=2}

        // =======================================================
        // 30. Group Employees by Department
        
                
        // System.out.println("Employees by Dept: " + employeesByDept);
        // Output: Employees by Dept: {HR=[Employee@..., Employee@...], IT=[Employee@..., Employee@...]}

        
        // =======================================================
        // 31. flatMap() is used to flatten nested collections.
        

        // System.out.println(resultTemp);
        // Output: [Employee@..., Employee@..., Employee@..., Employee@...] (all employees in a single list)

    }
}

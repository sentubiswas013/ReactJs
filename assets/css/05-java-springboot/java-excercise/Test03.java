import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.IntStream;

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
        List<Integer> num01 = Arrays.asList(0,1,7,2,3,0,4,5,0,6,9);
        List<Integer> num02 = Arrays.asList(6,1,0,2,3,4,0,2,5,1,0);
        List<String> arr01 = Arrays.asList("java","stream","api", "level", "madam");
        List<String> arr02 = Arrays.asList("Alice","Bob","Annie","Alex", null);


        // =======================================================
        // 1. Filter Even Numbers   // filter: Remove unwanted elements  // toList: Convert stream to List 
        
                
        // System.out.println("1. Even Numbers: " + even);
        // Output: 1. Even Numbers: [2, 4, 6]



        // =======================================================
        // 2. Find Common Elements Between Two List
        List<Integer> common = num01.stream()
                .filter(num02::contains)
                .toList();
        

        // System.out.println("11. Common: " + common);
        // Output: 11. Common: [0, 1, 2, 3, 0, 4, 5, 0, 6]



        // =======================================================
        // 3. Count Strings with Specific Prefix 
        Long count = arr01.stream()
            .filter(w -> w.startsWith("A"))
            .count();
        

        // System.out.println("4. Count starting with A: " + count);
        // Output: 4. Count starting with A: 3



        // =======================================================
        // 4. Check if Any String Contains and equals Word
        List<String> Contains = arr01.stream()
            .filter(w -> w.contains("api"))
            .toList();

        // System.out.println("8. Contains api: " + Contains);
        // Output: 8. Contains api: [api]



        // =======================================================
        // 5. Remove Null Values and missing number from array
        // Remove Null Values
        List<String> clean = arr02.stream()
            .filter(Objects::nonNull)
            .toList();
        

        // System.out.println("19. Clean: " + clean);
        // Output: 19. Clean: [java, stream, api]

        // missing number from array
        List<Integer> missing =  IntStream.range(0, 10)
            .filter(n -> !num01.contains(n))
            .boxed()
            .toList();
        

        // System.out.println("19. Missing Number: " + missing);
        // Output: 19. Clean: [2, 8]



        // =======================================================
        // 6. Find Palindromes
        List<String> palindromes = arr01.stream()
            .filter(w -> w.equals(new StringBuilder(w).reverse().toString()))
            .toList();
        

        // System.out.println("22. Palindromes: " + palindromes);
        // Output: 22. Palindromes: []



        // =======================================================
        // 7. Find Duplicate Elements
         Set<Integer> seen = new HashSet<>();
         Set<Integer> duplicates = num02.stream()
            .filter(n -> !seen.add(n))
            .collect(Collectors.toSet());

        

        // System.out.println("9. Duplicates: " + duplicates);
        // Output: 9. Duplicates: [0, 1, 2]



        // =======================================================
        // 8. Remove Duplicates
        List<Integer> unique = num02.stream()
            .distinct()
            .toList();
        

        // System.out.println("17. Unique: " + unique);
        // Output: 17. Unique: [6, 1, 0, 2, 3, 4, 5]



        // =======================================================
        // 9. Find Maximum and minimum number
        int max = num01.stream()
            .max(Integer::compareTo)
            .orElseThrow();
        
        int min = num01.stream()
            .min(Integer::compareTo)
            .orElseThrow();

        // System.out.println("2. Maximum: " + max);
        // System.out.println("2. Minimum: " + min);
        // Output: 2. Maximum: 6



        // =======================================================
        // 10. Find Longest and smallest String
        String longest = arr01.stream()
            .max(Comparator.comparingInt(String::length))
            .orElse(null);


        String smallest = arr01.stream()
            .min(Comparator.comparingInt(String::length))
            .orElse(null);


        // System.out.println("10. Longest: " + longest);
        // System.out.println("10. smallest: " + smallest);
        // System.out.println("10. Longest: " + longestWord);
        // Output: 10. Longest: stream



        // =======================================================
        // 11. Sort List in Descending Order
        List<Integer> sorted = num01.stream()
            .sorted(Comparator.reverseOrder())
            .distinct()
            .toList();
        

        // System.out.println("3. Sorted Descending: " + sorted);
        // Output: 3. Sorted Descending: [6, 5, 4, 3, 2, 1]



        // =======================================================
        // 12. Find Top N Elements
        List<Integer> top3 = num01.stream()
            .sorted(Comparator.reverseOrder())
            .skip(1)
            .limit(3)
            .toList();
        

        // System.out.println("12. Top 3: " + top3);
        // Output: 12. Top 3: [6, 5, 4]



        // =======================================================
        // 13. Find Nth Largest Element
        

        // System.out.println("16. Third Largest: " + thirdLargest);
        // output: 16. Third Largest: 4



        // =======================================================
        // 14. To merge two arrays and sort the resulting array in ascending order
        List<Integer> resultSort = Stream.concat(
            num01.stream(), 
            num02.stream()
        )
            .sorted()
            .distinct()
            .toList();

        // System.out.println(resultSort);
        // Output: 14. Result: [0, 0, 0, 1, 1, 1, 2, 2, 3, 3, 4, 6, 7]



        // =======================================================
        // 15. 0 should go to outside without change order
        List<Integer> resultRight = Stream.concat(
                num01.stream().filter(n -> n != 0),
                num01.stream().filter(n -> n == 0)
            )
            .collect(Collectors.toList());

        // System.out.println(resultRight);
        // Output: [1, 7, 2, 3, 4, 5, 6, 9, 0, 0, 0]



        // =======================================================
        // 16. Count Frequency of Characters
        // String str = "success";
        // Char freq = 
        

        // System.out.println("13. Frequency: " + freq);
        // Output: 13. Frequency: {s=3, u=1, c=2, e=1}



        // =======================================================
        // 17. Find First Non-Repeated Character
        String input = "swiss";
        Character result = input.chars()
            .mapToObj(c -> (char) c)
            .filter(c -> input.indexOf(c) == input.lastIndexOf(c))
            .findFirst()
            .orElse(null);
        

        // System.out.println("5. First Non-Repeated: " + result);
        // Output: 5. First Non-Repeated: w



        // =======================================================
        // 18. Sum and average of Numbers
        int sum = num01.stream()
            .mapToInt(Integer::intValue)
            .sum();

        double average = num01.stream()
            .mapToInt(Integer::intValue)
            .average()
            .orElse(0);
        

        // System.out.println("7. Sum: " + sum);
        // System.out.println("7. average: " + average);
        // Output: 7. Sum: 21



        // =======================================================
        // 19. Convert List to Uppercase    // Map: Transform elements  
        List<String> upper = arr01.stream()
            .map(String::toUpperCase)
            .toList();
        

        // System.out.println("6. Uppercase: " + upper);
        // Output: 6. Uppercase: [JAVA, STREAM, API]
        


        // =======================================================
        // 20. Reverse Each String
        List<String> reversed = arr01.stream()   
            .map(w -> new StringBuilder(w).reverse().toString())
            .toList();
        

        // System.out.println("23. Reversed: " + reversed);
        // Output: 23. Reversed: [avaj, maerts, ipa]



        // =======================================================
        // 21. Partition Even and Odd Numbers // collect: Convert stream to collection
        


        // System.out.println("15. Partition: " + partition);
        // Output: 15. Partition: {false=[1, 3, 5], true=[2, 4, 6]}



        // =======================================================
        // 22. Join Strings
        String joined = arr01.stream()
            .collect(Collectors.joining(" "));

        // System.out.println("18. Joined: " + joined);
        // Output: 18. Joined: java, stream, api
        


        // =======================================================
        // 23. Convert List to Map and word length
        Map<Integer, List<String>> map = arr01.stream()
            .collect(Collectors.groupingBy(String::length));
            // .collect(Collectors.groupingBy(String::length));

        System.out.println("21. Map: " + map);
        // Output: 21. Map: {java=4, stream=6, api=3}
        // Map:  {3=[api], 4=[java], 5=[level, madam], 6=[stream]}



        // =======================================================
        // 24. Group by First Character
        Map<Character, List<String>> mapByFirst = arr01.stream()
            .collect(Collectors.groupingBy(w -> w.charAt(0)));
        

         System.out.println("25. Grouped by First Char: " + mapByFirst);
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

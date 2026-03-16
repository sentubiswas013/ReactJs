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
    }

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6);
        List<Integer> nums = Arrays.asList(1,2,3,4,2,5,1);
        List<String> words = Arrays.asList("java","stream","api");
        List<String> names = Arrays.asList("Alice","Bob","Annie","Alex");

        // 1. Filter Even Numbers 


        
        // System.out.println("1. Even Numbers: " + even);
        // Output: 1. Even Numbers: [2, 4, 6]

        // =======================================================
        // 2. Find Maximum Element


        
        // System.out.println("2. Maximum: " + max);
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
        // Output: 5. First Non-Repeated: w

        // =======================================================
        // 6. Convert List to Uppercase   


        
        // System.out.println("6. Uppercase: " + upper);
        // Output: 6. Uppercase: [JAVA, STREAM, API]

        // =======================================================
        // 7. Sum of Numbers


        
        // System.out.println("7. Sum: " + sum);
        // Output: 7. Sum: 21

        // =======================================================
        // 8. Check if Any String Contains Word


        
        // System.out.println("8. Contains api: " + result8);
        // Output: 8. Contains api: true

        // =======================================================
        // 9. Find Duplicate Elements    


        
        // System.out.println("9. Duplicates: " + duplicates);
        // Output: 9. Duplicates: [1, 2]

        // =======================================================
        // 10. Find Longest String
        
        // System.out.println("10. Longest: " + longest);
        // Output: 10. Longest: stream

        // =======================================================
        // 11. Find Common Elements Between Two Lists


        
        // System.out.println("11. Common: " + common);
        // Output: 11. Common: [3, 4]

        // =======================================================
        // 12. Find Top N Elements


        
        // System.out.println("12. Top 3: " + top3);
        // Output: 12. Top 3: [6, 5, 4]

        // =======================================================
        // 13. Count Frequency of Characters


        
        // System.out.println("13. Frequency: " + freq);
        // Output: 13. Frequency: {s=3, u=1, c=2, e=1}

        // =======================================================
        // 14. Flatten List of Lists


        
        // System.out.println("14. Flattened: " + flat);
        // Output: 14. Flattened: [1, 2, 3, 4, 5, 6]

        // =======================================================
        // 15. Partition Even and Odd Numbers


        
        // System.out.println("15. Partition: " + partition);
        // Output: 15. Partition: {false=[1, 3, 5], true=[2, 4, 6]}

        // =======================================================
        // 16. Find Nth Largest Element


        
        // System.out.println("16. Third Largest: " + thirdLargest);
        // output: 16. Third Largest: 4

        // =======================================================
        // 17. Remove Duplicates


        
        // System.out.println("17. Unique: " + unique);
        // Output: 17. Unique: [1, 2, 3, 4, 5]

        // =======================================================
        // 18. Join Strings


        
        // System.out.println("18. Joined: " + joined);
        // Output: 18. Joined: java, stream, api

        // =======================================================
        // 19. Remove Null Values


        
        // System.out.println("19. Clean: " + clean);
        // Output: 19. Clean: [java, stream, api]

        // =======================================================
        // 20. Calculate Average


        
        // System.out.println("20. Average: " + avg);
        // Output: 20. Average: 3.5

        // =======================================================
        // 21. Reverse Each String
        


        // System.out.println("23. Reversed: " + reversed);
        // Output: 23. Reversed: [avaj, maerts, ipa]

        // =======================================================
        // 22. Find Palindromes
       

        // System.out.println("22. Palindromes: " + palindromes);
        // Output: 22. Palindromes: []

        // =======================================================
        // 23. Convert List to Map
        

        // System.out.println("21. Map: " + map);
        // Output: 21. Map: {java=4, stream=6, api=3}

        // =======================================================
        // 24. Group Strings by Length
        


        // System.out.println("24. Grouped by Length: " + grouped);
        // Output: 24. Grouped by Length: {3=[api], 4=[java], 6=[stream]}

        // =======================================================
        // 25. Group by First Character
        


        // System.out.println("25. Grouped by First Char: " + mapByFirst);
        // Output: 25. Grouped by First Char: {a=[api], j=[java], s=[stream]}

        // =======================================================
        // 26. Find Highest Salary by Department
        List<Employee> employees = Arrays.asList(
                new Employee("IT", 50000),
                new Employee("HR", 40000),
                new Employee("IT", 60000),
                new Employee("HR", 45000)
        );
        
        // System.out.println("26. Highest Salary by Dept: " + highest);
        // Output: 26. Highest Salary by Dept: {HR=Employee@..., IT=Employee@...}
        
        // =======================================================
        // 27. Find Average Salary by Department
        


        // System.out.println(avgSalary);
        // Output: 26. Highest Salary by Dept: {HR=Employee@..., IT=Employee@...}

        // =======================================================
        // 28. Find Employee With Highest Salary Overall
        

        // highestSalary.ifPresent(System.out::println);
        // Output: Employee@... (the employee with the highest salary) 

        // =======================================================
        // 29. Count Employees in Each Department
        


        // System.out.println(countByDept);
        // Output: {HR=2, IT=2}

        // =======================================================
        // 30. Find All Employees Grouped by Department
        

        // System.out.println(employeesByDept);
        // Output: {HR=[Employee@..., Employee@...], IT=[Employee@..., Employee@...]} 

        // =======================================================
        // 31. Find Second Highest Salary
        

        // System.out.println(secondHighest);
        // Output: Optional[55000.0] (the second highest salary)

        // =======================================================
        // 32. Generate Fibonacci Sequence
        List<Integer> fib = Stream.iterate(new int[]{0,1},
                arr -> new int[]{arr[1], arr[0] + arr[1]})
                .limit(10)
                .map(arr -> arr[0])
                .toList();
        // System.out.println("27. Fibonacci: " + fib);
        // Output: 27. Fibonacci: [0, 1, 1, 2, 3, 5, 8, 13, 21, 34]
        
        // =======================================================
        // 33. Find Pair With Given Sum
        int target = 6;
        List<List<Integer>> pairs = numbers.stream()
                .flatMap(x -> numbers.stream()
                        .filter(y -> x + y == target && x < y)
                        .map(y -> Arrays.asList(x,y)))
                .toList();
        // System.out.println("28. Pairs summing to " + target + ": " + pairs);
        // Output: 28. Pairs summing to 6: [[1, 5], [2, 4]]


        // =======================================================                
        // 29. Detect Anagrams
        List<String> anagramWords = Arrays.asList("listen","silent","enlist","google");
        Map<String, List<String>> anagrams = anagramWords.stream()
                .collect(Collectors.groupingBy(
                        w -> w.chars()
                                .sorted()
                                .mapToObj(c -> String.valueOf((char)c))
                                .collect(Collectors.joining())
                ));
        // System.out.println("29. Anagrams: " + anagrams);
        // Output: 29. Anagrams: {eilnst=[listen, silent, enlist], egglno=[google]}
        
        // =======================================================
        // 34. Sort Elements by Frequency
        List<Integer> sortedByFreq = nums.stream()
                .sorted(Comparator.comparingInt(n -> -Collections.frequency(nums, n)))
                .distinct()
                .toList();
        // System.out.println("30. Sorted by Frequency: " + sortedByFreq);
        // Output: 30. Sorted by Frequency: [1, 2, 3, 4, 5]
    }
}
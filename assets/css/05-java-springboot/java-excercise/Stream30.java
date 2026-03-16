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


        // =======================================================
        // 1. Filter Even Numbers       
        List<Integer> even = numbers.stream()
                .filter(n -> n % 2 == 0)
                .toList();
        // System.out.println("1. Even Numbers: " + even);
        // Output: 1. Even Numbers: [2, 4, 6]


        // =======================================================
        // 2. Find Maximum Element
        int max = numbers.stream()
                .max(Integer::compareTo)
                .orElseThrow();
        // System.out.println("2. Maximum: " + max);
        // Output: 2. Maximum: 6


        // =======================================================
        // 3. Sort List in Descending Order
        List<Integer> sorted = numbers.stream()
                .sorted(Comparator.reverseOrder())
                .toList();
        // System.out.println("3. Sorted Descending: " + sorted);
        // Output: 3. Sorted Descending: [6, 5, 4, 3, 2, 1]


        // =======================================================
        // 4. Count Strings with Specific Prefix        
        long count = names.stream()
                .filter(name -> name.startsWith("A"))
                .count();
        // System.out.println("4. Count starting with A: " + count);
        // Output: 4. Count starting with A: 3


        // =======================================================
        // 5. Find First Non-Repeated Character
        String input = "swiss";
        Character result = input.chars()
                .mapToObj(c -> (char)c)
                .filter(c -> input.indexOf(c) == input.lastIndexOf(c))
                .findFirst()
                .orElse(null);
        // System.out.println("5. First Non-Repeated: " + result);
        // Output: 5. First Non-Repeated: w


        // =======================================================
        // 6. Convert List to Uppercase        
        List<String> upper = words.stream()
                .map(String::toUpperCase)
                .toList();
        // System.out.println("6. Uppercase: " + upper);
        // Output: 6. Uppercase: [JAVA, STREAM, API]


        // =======================================================
        // 7. Sum of Numbers
        int sum = numbers.stream()
                .mapToInt(Integer::intValue)
                .sum();
        // System.out.println("7. Sum: " + sum);
        // Output: 7. Sum: 21


        // =======================================================
        // 8. Check if Any String Contains Word
        boolean result8 = words.stream()
                .anyMatch(s -> s.contains("api"));
        // System.out.println("8. Contains api: " + result8);
        // Output: 8. Contains api: true


        // =======================================================
        // 9. Find Duplicate Elements        
        Set<Integer> seen = new HashSet<>();
        Set<Integer> duplicates = nums.stream()
                .filter(n -> !seen.add(n))
                .collect(Collectors.toSet());
        // System.out.println("9. Duplicates: " + duplicates);
        // Output: 9. Duplicates: [1, 2]


        // =======================================================
        // 10. Find Longest String
        String longest = words.stream()
                .max(Comparator.comparingInt(String::length))
                .orElse(null);
        // System.out.println("10. Longest: " + longest);
        // Output: 10. Longest: stream


        // =======================================================
        // 11. Find Common Elements Between Two Lists
        List<Integer> a = Arrays.asList(1,2,3,4);
        List<Integer> b = Arrays.asList(3,4,5,6);
        List<Integer> common = a.stream()
                .filter(b::contains)
                .toList();
        // System.out.println("11. Common: " + common);
        // Output: 11. Common: [3, 4]


        // =======================================================
        // 12. Find Top N Elements
        List<Integer> top3 = numbers.stream()
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .toList();
        // System.out.println("12. Top 3: " + top3);
        // Output: 12. Top 3: [6, 5, 4]


        // =======================================================
        // 13. Count Frequency of Characters
        String str = "success";
        Map<Character, Long> freq = str.chars()
                .mapToObj(c -> (char)c)
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
        // System.out.println("13. Frequency: " + freq);
        // Output: 13. Frequency: {s=3, u=1, c=2, e=1}



        // =======================================================
        // 14. Flatten List of Lists
        List<List<Integer>> lists = Arrays.asList(
                Arrays.asList(1,2),
                Arrays.asList(3,4),
                Arrays.asList(5,6)
        );
        List<Integer> flat = lists.stream()
                .flatMap(List::stream)
                .toList();
        // System.out.println("14. Flattened: " + flat);
        // Output: 14. Flattened: [1, 2, 3, 4, 5, 6]


        // =======================================================
        // 15. Partition Even and Odd Numbers
        Map<Boolean, List<Integer>> partition = numbers.stream()
                .collect(Collectors.partitioningBy(n -> n % 2 == 0));
        // System.out.println("15. Partition: " + partition);
        // Output: 15. Partition: {false=[1, 3, 5], true=[2, 4, 6]}


        // =======================================================
        // 16. Find Nth Largest Element
        int thirdLargest = numbers.stream()
                .sorted(Comparator.reverseOrder())
                .skip(2)
                .findFirst()
                .orElseThrow();
        // System.out.println("16. Third Largest: " + thirdLargest);
        // output: 16. Third Largest: 4


        // =======================================================
        // 17. Remove Duplicates
        List<Integer> unique = nums.stream()
                .distinct()
                .toList();
        // System.out.println("17. Unique: " + unique);
        // Output: 17. Unique: [1, 2, 3, 4, 5]


        // =======================================================
        // 18. Join Strings
        String joined = words.stream()
                .collect(Collectors.joining(", "));
        // System.out.println("18. Joined: " + joined);
        // Output: 18. Joined: java, stream, api


        // =======================================================
        // 19. Remove Null Values
        List<String> wordsWithNull = Arrays.asList("java", null, "stream", "api");
        List<String> clean = wordsWithNull.stream()
                .filter(Objects::nonNull)
                .toList();
        // System.out.println("19. Clean: " + clean);
        // Output: 19. Clean: [java, stream, api]


        // =======================================================
        // 20. Calculate Average
        double avg = numbers.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0);
        // System.out.println("20. Average: " + avg);
        // Output: 20. Average: 3.5


        // =======================================================
        // 21. Reverse Each String
        List<String> reversed = words.stream()
                .map(w -> new StringBuilder(w).reverse().toString())
                .toList();
        // System.out.println("23. Reversed: " + reversed);
        // Output: 23. Reversed: [avaj, maerts, ipa]


        // =======================================================
        // 22. Find Palindromes
        List<String> palindromes = words.stream()
                .filter(w -> w.equals(new StringBuilder(w).reverse().toString()))
                .toList();
        // System.out.println("22. Palindromes: " + palindromes);
        // Output: 22. Palindromes: []


        // =======================================================
        // 23. Convert List to Map
        Map<String, Integer> map = words.stream()
                .collect(Collectors.toMap(w -> w, String::length));
        // System.out.println("21. Map: " + map);
        // Output: 21. Map: {java=4, stream=6, api=3}


        // =======================================================
        // 24. Group Strings by Length
        Map<Integer, List<String>> grouped = words.stream()
                .collect(Collectors.groupingBy(String::length));
        // System.out.println("24. Grouped by Length: " + grouped);
        // Output: 24. Grouped by Length: {3=[api], 4=[java], 6=[stream]}


        // =======================================================
        // 25. Group by First Character
        Map<Character, List<String>> mapByFirst = words.stream()
                .collect(Collectors.groupingBy(w -> w.charAt(0)));
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
        Map<String, Employee> highest = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparing(Employee::getSalary)),
                                Optional::get
                        )
                ));
        // System.out.println("26. Highest Salary by Dept: " + highest);
        // Output: 26. Highest Salary by Dept: {HR=Employee@..., IT=Employee@...}


        // =======================================================
        // 27. Find Average Salary by Department
        Map<String, Double> avgSalary = employees.stream()
        .collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.averagingDouble(Employee::getSalary)
        ));

        System.out.println(avgSalary);
        // Output: 26. Highest Salary by Dept: {HR=Employee@..., IT=Employee@...}


        // =======================================================
        // 28. Find Employee With Highest Salary Overall
        Optional<Employee> highestSalary = employees.stream()
        .max(Comparator.comparing(Employee::getSalary));

        highestSalary.ifPresent(System.out::println);
        // Output: Employee@... (the employee with the highest salary) 


        // =======================================================
        // 29. Count Employees in Each Department
        Map<String, Long> countByDept = employees.stream()
        .collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.counting()
        ));

        System.out.println(countByDept);
        // Output: {HR=2, IT=2}


        // =======================================================
        // 30. Find All Employees Grouped by Department
        Map<String, List<Employee>> employeesByDept = employees.stream()
        .collect(Collectors.groupingBy(Employee::getDepartment));

        System.out.println(employeesByDept);
        // Output: {HR=[Employee@..., Employee@...], IT=[Employee@..., Employee@...]} 


        // =======================================================
        // 31. Find Second Highest Salary
        Optional<Double> secondHighest = employees.stream()
                .map(Employee::getSalary)
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst();

        System.out.println(secondHighest);
        // Output: Optional[55000.0] (the second highest salary)

        // =======================================================
        // 32. flatMap() is used to flatten nested collections.
        List<List<Employee>> inputTemp = List.of(
                List.of(new Employee("John", 5000), new Employee("Sam", 6000)),
                List.of(new Employee("David", 7000), new Employee("Mary", 8000))
        );

        List<Employee> resultTemp = inputTemp.stream()
                .flatMap(List::stream)
                .toList();

        System.out.println(resultTemp);
        // Output: [Employee@..., Employee@..., Employee@..., Employee@...] (all employees in a single list)


        // =======================================================
        // 33. Detect Anagrams
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

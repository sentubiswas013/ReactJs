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
        // 1. Filter Even Numbers
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6);
        List<Integer> even = numbers.stream()
                .filter(n -> n % 2 == 0)
                .toList();
        System.out.println("1. Even Numbers: " + even);

        // 2. Find Maximum Element
        int max = numbers.stream()
                .max(Integer::compareTo)
                .orElseThrow();
        System.out.println("2. Maximum: " + max);

        // 3. Sort List in Descending Order
        List<Integer> sorted = numbers.stream()
                .sorted(Comparator.reverseOrder())
                .toList();
        System.out.println("3. Sorted Descending: " + sorted);

        // 4. Count Strings with Specific Prefix
        List<String> names = Arrays.asList("Alice","Bob","Annie","Alex");
        long count = names.stream()
                .filter(name -> name.startsWith("A"))
                .count();
        System.out.println("4. Count starting with A: " + count);

        // 5. Find First Non-Repeated Character
        String input = "swiss";
        Character result = input.chars()
                .mapToObj(c -> (char)c)
                .filter(c -> input.indexOf(c) == input.lastIndexOf(c))
                .findFirst()
                .orElse(null);
        System.out.println("5. First Non-Repeated: " + result);

        // 6. Convert List to Uppercase
        List<String> words = Arrays.asList("java","stream","api");
        List<String> upper = words.stream()
                .map(String::toUpperCase)
                .toList();
        System.out.println("6. Uppercase: " + upper);

        // 7. Sum of Numbers
        int sum = numbers.stream()
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println("7. Sum: " + sum);

        // 8. Check if Any String Contains Word
        boolean result8 = words.stream()
                .anyMatch(s -> s.contains("api"));
        System.out.println("8. Contains api: " + result8);

        // 9. Find Duplicate Elements
        List<Integer> nums = Arrays.asList(1,2,3,4,2,5,1);
        Set<Integer> seen = new HashSet<>();
        Set<Integer> duplicates = nums.stream()
                .filter(n -> !seen.add(n))
                .collect(Collectors.toSet());
        System.out.println("9. Duplicates: " + duplicates);

        // 10. Find Longest String
        String longest = words.stream()
                .max(Comparator.comparingInt(String::length))
                .orElse(null);
        System.out.println("10. Longest: " + longest);

        // 11. Find Common Elements Between Two Lists
        List<Integer> a = Arrays.asList(1,2,3,4);
        List<Integer> b = Arrays.asList(3,4,5,6);
        List<Integer> common = a.stream()
                .filter(b::contains)
                .toList();
        System.out.println("11. Common: " + common);

        // 12. Find Top N Elements
        List<Integer> top3 = numbers.stream()
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .toList();
        System.out.println("12. Top 3: " + top3);

        // 13. Count Frequency of Characters
        String str = "success";
        Map<Character, Long> freq = str.chars()
                .mapToObj(c -> (char)c)
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
        System.out.println("13. Frequency: " + freq);

        // 14. Flatten List of Lists
        List<List<Integer>> lists = Arrays.asList(
                Arrays.asList(1,2),
                Arrays.asList(3,4),
                Arrays.asList(5,6)
        );
        List<Integer> flat = lists.stream()
                .flatMap(List::stream)
                .toList();
        System.out.println("14. Flattened: " + flat);

        // 15. Partition Even and Odd Numbers
        Map<Boolean, List<Integer>> partition = numbers.stream()
                .collect(Collectors.partitioningBy(n -> n % 2 == 0));
        System.out.println("15. Partition: " + partition);

        // 16. Find Nth Largest Element
        int thirdLargest = numbers.stream()
                .sorted(Comparator.reverseOrder())
                .skip(2)
                .findFirst()
                .orElseThrow();
        System.out.println("16. Third Largest: " + thirdLargest);

        // 17. Remove Duplicates
        List<Integer> unique = nums.stream()
                .distinct()
                .toList();
        System.out.println("17. Unique: " + unique);

        // 18. Join Strings
        String joined = words.stream()
                .collect(Collectors.joining(", "));
        System.out.println("18. Joined: " + joined);

        // 19. Remove Null Values
        List<String> wordsWithNull = Arrays.asList("java", null, "stream", "api");
        List<String> clean = wordsWithNull.stream()
                .filter(Objects::nonNull)
                .toList();
        System.out.println("19. Clean: " + clean);

        // 20. Calculate Average
        double avg = numbers.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0);
        System.out.println("20. Average: " + avg);

        // 21. Convert List to Map
        Map<String, Integer> map = words.stream()
                .collect(Collectors.toMap(w -> w, String::length));
        System.out.println("21. Map: " + map);

        // 22. Find Palindromes
        List<String> palindromes = words.stream()
                .filter(w -> w.equals(new StringBuilder(w).reverse().toString()))
                .toList();
        System.out.println("22. Palindromes: " + palindromes);

        // 23. Reverse Each String
        List<String> reversed = words.stream()
                .map(w -> new StringBuilder(w).reverse().toString())
                .toList();
        System.out.println("23. Reversed: " + reversed);

        // 24. Group Strings by Length
        Map<Integer, List<String>> grouped = words.stream()
                .collect(Collectors.groupingBy(String::length));
        System.out.println("24. Grouped by Length: " + grouped);

        // 25. Group by First Character
        Map<Character, List<String>> mapByFirst = words.stream()
                .collect(Collectors.groupingBy(w -> w.charAt(0)));
        System.out.println("25. Grouped by First Char: " + mapByFirst);

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
        System.out.println("26. Highest Salary by Dept: " + highest);

        // 27. Generate Fibonacci Sequence
        List<Integer> fib = Stream.iterate(new int[]{0,1},
                arr -> new int[]{arr[1], arr[0] + arr[1]})
                .limit(10)
                .map(arr -> arr[0])
                .toList();
        System.out.println("27. Fibonacci: " + fib);

        // 28. Find Pair With Given Sum
        int target = 6;
        List<List<Integer>> pairs = numbers.stream()
                .flatMap(x -> numbers.stream()
                        .filter(y -> x + y == target && x < y)
                        .map(y -> Arrays.asList(x,y)))
                .toList();
        System.out.println("28. Pairs summing to " + target + ": " + pairs);

        // 29. Detect Anagrams
        List<String> anagramWords = Arrays.asList("listen","silent","enlist","google");
        Map<String, List<String>> anagrams = anagramWords.stream()
                .collect(Collectors.groupingBy(
                        w -> w.chars()
                                .sorted()
                                .mapToObj(c -> String.valueOf((char)c))
                                .collect(Collectors.joining())
                ));
        System.out.println("29. Anagrams: " + anagrams);

        // 30. Sort Elements by Frequency
        List<Integer> sortedByFreq = nums.stream()
                .sorted(Comparator.comparingInt(n -> -Collections.frequency(nums, n)))
                .distinct()
                .toList();
        System.out.println("30. Sorted by Frequency: " + sortedByFreq);
    }
}
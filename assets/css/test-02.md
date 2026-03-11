import java.util.*;
import java.util.stream.*;

// Most Asked Java Stream API Interview Questions
public class StreamExamples {
    public static void main(String[] args) {
        filterEvenNumbers();
    }

    //1 Filter Even Numbers from a List
    static void filterEvenNumbers() {
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6);
        List<Integer> even = numbers.stream().filter(n->n%2==0).toList();
        System.out.println(even);
        // Output: [2, 4, 6]
    }

    //2 Find Maximum in a List
    static void findMax() {
        List<Integer> numbers = Arrays.asList(10,20,30,40,50);
        int max = numbers.stream().max(Integer::compare).orElseThrow();
        System.out.println(max);
        // Output: 50
    }

    //3 Sort a List in Descending Order
    static void sortDescending() {
        List<Integer> numbers = Arrays.asList(3,5,1,4,2);
        List<Integer> sorted = numbers.stream()
                .sorted(Comparator.reverseOrder())
                .toList();
        System.out.println(sorted);
        // Output: [5, 4, 3, 2, 1]
    }

    //4 Convert List of Strings to Uppercase
    static void convertUpperCase() {
        List<String> names = Arrays.asList("java","stream","api");
        System.out.println(names.stream().map(String::toUpperCase).toList());
        // Output: [JAVA, STREAM, API]
    }

    //5 Sum of Numbers in a List
    static void sumNumbers() {
        List<Integer> numbers = Arrays.asList(1,2,3,4,5);
        int sum = numbers.stream().mapToInt(Integer::intValue).sum();
        System.out.println(sum);
        // Output: 15
    }

    //6 Find the Longest String
    static void longestString() {
        List<String> words = Arrays.asList("Java","Stream","API","Development");
        String longest = words.stream()
                .max(Comparator.comparingInt(String::length))
                .orElse(null);
        System.out.println(longest);
        // Output: Development
    }

    //7 Find Common Elements Between Two Lists
    static void commonElements() {
        List<Integer> a = Arrays.asList(1,2,3,4,5);
        List<Integer> b = Arrays.asList(3,4,5,6);
        System.out.println(a.stream().filter(b::contains).toList());
        // Output: [3, 4, 5]
    }

    //8 Count Frequency of Characters in a String
    static void charFrequency() {
        String input="success";
        Map<Character,Long> freq = input.chars()
                .mapToObj(c->(char)c)
                .collect(Collectors.groupingBy(c->c,Collectors.counting()));
        System.out.println(freq);
        // Output: {s=3, u=1, c=2, e=1}
    }

    //9 Remove Null Values
    static void removeNulls() {
        List<String> words = Arrays.asList("Java",null,"Stream",null,"API");
        System.out.println(words.stream().filter(Objects::nonNull).toList());
        // Output: [Java, Stream, API]
    }

    //10 Calculate Average of Numbers
    static void averageNumbers() {
        List<Integer> numbers = Arrays.asList(10,20,30,40,50);
        double avg = numbers.stream().mapToInt(Integer::intValue).average().orElse(0);
        System.out.println(avg);
        // Output: 30.0
    }

    //11 Collect Map from List
    static void listToMap() {
        List<String> words = Arrays.asList("Java","Stream","API");
        Map<String,Integer> map = words.stream()
                .collect(Collectors.toMap(w->w,String::length));
        System.out.println(map);
        // Output: {Java=4, Stream=6, API=3}
    }

    //12 Partition Numbers into Even and Odd
    static void partitionEvenOdd() {
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6);
        Map<Boolean,List<Integer>> map =
                numbers.stream().collect(Collectors.partitioningBy(n->n%2==0));
        System.out.println(map);
        // Output: {false=[1, 3, 5], true=[2, 4, 6]}
    }

    //13 Find Nth Largest Element in a List
    static void thirdLargest() {
        List<Integer> numbers = Arrays.asList(10,20,50,40,30);
        int val = numbers.stream().sorted(Comparator.reverseOrder())
                .skip(2).findFirst().orElseThrow();
        System.out.println(val);
        // Output: 30
    }

    //14 Find All Palindromic Strings
    static void findPalindromes() {
        List<String> words = Arrays.asList("radar","level","world","java");
        List<String> result = words.stream()
                .filter(w->w.equals(new StringBuilder(w).reverse().toString()))
                .toList();
        System.out.println(result);
        // Output: [radar, level]
    }

    //15 Remove Duplicates
    static void removeDuplicates() {
        List<Integer> numbers = Arrays.asList(1,2,3,2,4,3,5);
        System.out.println(numbers.stream().distinct().toList());
        // Output: [1, 2, 3, 4, 5]
    }

    //16 Flatten a List of Lists
    static void flattenList() {
        List<List<Integer>> lists = Arrays.asList(
                Arrays.asList(1,2,3),
                Arrays.asList(4,5),
                Arrays.asList(6,7,8)
        );
        List<Integer> result = lists.stream()
                .flatMap(List::stream)
                .toList();
        System.out.println(result);
        // Output: [1, 2, 3, 4, 5, 6, 7, 8]
    }

    //17 Find the Sum of Squares of Even Numbers
    static void sumOfSquares() {
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6);
        int sum = numbers.stream()
                .filter(n->n%2==0)
                .mapToInt(n->n*n).sum();
        System.out.println(sum);
        // Output: 56
    }

    //18 Group by First Character
    static void groupByFirstChar() {
        List<String> words = Arrays.asList("apple","banana","avocado");
        Map<Character,List<String>> map =
                words.stream().collect(Collectors.groupingBy(w->w.charAt(0)));
        System.out.println(map);
        // Output: {a=[apple, avocado], b=[banana]}
    }

    //19 Group Employees by Department
    static void groupEmployee() {
        List<Employee> list = Arrays.asList(
                new Employee("Alice","HR",25),
                new Employee("Bob","IT",30),
                new Employee("Charlie","HR",30)
        );

        Map<String,List<Employee>> map =
                list.stream().collect(Collectors.groupingBy(Employee::getDepartment));
        System.out.println(map);
        // Output: {HR=[Alice, Charlie], IT=[Bob]}
    }

    //20 Find Duplicate Elements in a List
    static void duplicateElements() {
        List<Integer> numbers = Arrays.asList(1,2,3,4,2,5,3,6);

        Set<Integer> duplicates =
                numbers.stream()
                        .filter(n -> Collections.frequency(numbers,n)>1)
                        .collect(Collectors.toSet());

        System.out.println("Duplicates: " + duplicates);
        // Output: Duplicates: [2, 3]
    }

    //21 Join Strings with Delimiter
    static void joinStrings() {
        List<String> words = Arrays.asList("Java","Stream","API");
        String joined = words.stream().collect(Collectors.joining(", "));
        System.out.println(joined);
        // Output: Java, Stream, API
    }

    //22 Find Pair of Numbers with a Given Sum
    static void pairWithGivenSum() {
        List<Integer> numbers = Arrays.asList(1,2,3,4,5);
        int target=6;

        List<List<Integer>> pairs =
                numbers.stream()
                        .flatMap(a ->
                                numbers.stream()
                                        .filter(b -> a+b==target && a<b)
                                        .map(b -> Arrays.asList(a,b))
                        ).collect(Collectors.toList());

        System.out.println("Pairs: " + pairs);
        // Output: [[1, 5], [2, 4]]
    }

    //23 Sort a List Based on Frequency of Elements
    static void sortByFrequency() {
        List<Integer> numbers = Arrays.asList(4,5,6,5,4,3);

        List<Integer> sorted =
                numbers.stream()
                        .sorted(Comparator.comparingInt(n -> -Collections.frequency(numbers,n)))
                        .distinct()
                        .collect(Collectors.toList());

        System.out.println("Sorted by frequency: " + sorted);
        // Output: [4, 5, 6, 3]
    }

    //24 Skip and Limit Elements
    static void skipLimit() {
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7);
        System.out.println(numbers.stream().skip(3).limit(2).toList());
        // Output: [4, 5]
    }

    //25 Check if Any String Matches a Condition
    static void checkContainsAPI() {
        List<String> list = Arrays.asList("Java","Stream API","Lambda");
        boolean result = list.stream().anyMatch(s->s.contains("API"));
        System.out.println(result);
        // Output: true
    }
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

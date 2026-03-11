import java.util.*;
import java.util.stream.*;

// https://medium.com/@asishpanda444/stream-api-coding-qna-8df8682b7e2a
public class StreamExamples {
    public static void main(String[] args) {
        filterEvenNumbers();
        // findMax();
        // sortDescending();
        // countPrefix();
        // convertUpperCase();
        // sumNumbers();
        // checkContainsAPI();
        // longestString();
        // commonElements();
        // longestWordSentence();
        // topStudents();
        
        // concatenateStrings();
        // charFrequency();
        // joinStrings();
        // removeNulls();
        // averageNumbers();
        // listToMap();
        // partitionEvenOdd();
        // thirdLargest();
        // findPalindromes();
        // reverseStrings();
        // mapFilterKeys();
        // removeDuplicates();
        // mostFrequentChar();
        // flattenList(); 
        // sumOfSquares();
        // partitionPalindrome();
        // skipLimit();
        // cartesianProduct();
        // groupByFirstChar();
        // reverseConcat();
        // median();
        // groupEmployee();
        // detectCycle();
        // wordWithMaxVowels();
        // runningSum();
        // fibonacci();
        // highestSalaryByDept();
        // groupWordsByLength();
        // productOfNumbers();
        // slidingWindow();
        // detectAnagrams();
        // pyramidPattern();
        // maxPathTriangle();
        // nonRepeatingCharacters();
        // allSubsequences();
        // duplicateElements();
        // mapToKeyValuePairs();
        // checkSorted();
        // subarrays();
        // combineListsToMap();
        // pairWithGivenSum();
        // randomNumbers();
        // longestIncreasingSubsequence();
        // sortByFrequency();
        // sentenceCase();

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

    //3 Sort a List
    static void sortDescending() {
        List<Integer> numbers = Arrays.asList(3,5,1,4,2);
        List<Integer> sorted = numbers.stream()
                .sorted(Comparator.reverseOrder())
                .toList();
        System.out.println(sorted);
        // Output: [5, 4, 3, 2, 1]
    }

    //4 Count Strings with Specific Prefix
    static void countPrefix() {
        List<String> names = Arrays.asList("Alice","Bob","Annie","Alex","Charlie");
        long count = names.stream().filter(n->n.startsWith("A")).count();
        System.out.println(count);
        // Output: 3
    }



    //6 Convert List of Strings to Uppercase
    static void convertUpperCase() {
        List<String> names = Arrays.asList("java","stream","api");
        System.out.println(names.stream().map(String::toUpperCase).toList());
        // Output: [JAVA, STREAM, API]
    }

    //7 Sum of Numbers in a List
    static void sumNumbers() {
        List<Integer> numbers = Arrays.asList(1,2,3,4,5);
        int sum = numbers.stream().mapToInt(Integer::intValue).sum();
        System.out.println(sum);
        // Output: 15
    }

    //8 Check if Any String Matches a Condition
    static void checkContainsAPI() {
        List<String> list = Arrays.asList("Java","Stream API","Lambda");
        boolean result = list.stream().anyMatch(s->s.contains("API"));
        System.out.println(result);
        // Output: true
    }



    //10 Find the Longest String
    static void longestString() {
        List<String> words = Arrays.asList("Java","Stream","API","Development");
        String longest = words.stream()
                .max(Comparator.comparingInt(String::length))
                .orElse(null);
        System.out.println(longest);
        // Output: Development
    }

    //11 Find Common Elements Between Two Lists
    static void commonElements() {
        List<Integer> a = Arrays.asList(1,2,3,4,5);
        List<Integer> b = Arrays.asList(3,4,5,6);
        System.out.println(a.stream().filter(b::contains).toList());
        // Output: [3, 4, 5]
    }

    //12 Find the Longest Word from a Sentence
    static void longestWordSentence() {
        String sentence="Java Stream API is very powerful";
        String longest = Arrays.stream(sentence.split(" "))
                .max(Comparator.comparingInt(String::length)).orElse(null);
        System.out.println(longest);
        // Output: powerful
    }

    //13 Find Top N Highest Scoring Students
    static void topStudents() {
        List<Student> list = Arrays.asList(
                new Student("Alice",85),
                new Student("Bob",92),
                new Student("Charlie",88)
        );

        List<String> top = list.stream()
                .sorted(Comparator.comparingInt(Student::getScore).reversed())
                .limit(2)
                .map(Student::getName)
                .toList();

        System.out.println(top);
        // Output: [Bob, Charlie]
    }

    //14 Count Frequency of Characters in a String
    static void charFrequency() {
        String input="success";
        Map<Character,Long> freq = input.chars()
                .mapToObj(c->(char)c)
                .collect(Collectors.groupingBy(c->c,Collectors.counting()));
        System.out.println(freq);
        // Output: {s=3, u=1, c=2, e=1}
    }



    //16 Create Custom Collector
    static void joinStrings() {
        List<String> words = Arrays.asList("Java","Stream","API");
        String joined = words.stream().collect(Collectors.joining(", "));
        System.out.println(joined);
        // Output: Java, Stream, API
    }

    //17 Remove Null Values
    static void removeNulls() {
        List<String> words = Arrays.asList("Java",null,"Stream",null,"API");
        System.out.println(words.stream().filter(Objects::nonNull).toList());
        // Output: [Java, Stream, API]
    }

    //18 Calculate Average of Numbers
    static void averageNumbers() {
        List<Integer> numbers = Arrays.asList(10,20,30,40,50);
        double avg = numbers.stream().mapToInt(Integer::intValue).average().orElse(0);
        System.out.println(avg);
        // Output: 30.0
    }

    //19 Collect Map from List
    static void listToMap() {
        List<String> words = Arrays.asList("Java","Stream","API");
        Map<String,Integer> map = words.stream()
                .collect(Collectors.toMap(w->w,String::length));
        System.out.println(map);
        // Output: {Java=4, Stream=6, API=3}
    }

    //20 Partition Numbers into Even and Odd
    static void partitionEvenOdd() {
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6);
        Map<Boolean,List<Integer>> map =
                numbers.stream().collect(Collectors.partitioningBy(n->n%2==0));
        System.out.println(map);
        // Output: {false=[1, 3, 5], true=[2, 4, 6]}
    }

    //21 Find Nth Largest Element in a List
    static void thirdLargest() {
        List<Integer> numbers = Arrays.asList(10,20,50,40,30);
        int val = numbers.stream().sorted(Comparator.reverseOrder())
                .skip(2).findFirst().orElseThrow();
        System.out.println(val);
        // Output: 30
    }

    //22 Find All Palindromic Strings
    static void findPalindromes() {
        List<String> words = Arrays.asList("radar","level","world","java");
        List<String> result = words.stream()
                .filter(w->w.equals(new StringBuilder(w).reverse().toString()))
                .toList();
        System.out.println(result);
        // Output: [radar, level]
    }

    //23 Reverse Each String in a List
    static void reverseStrings() {
        List<String> words = Arrays.asList("Java","Stream","API");
        System.out.println(words.stream()
                .map(w->new StringBuilder(w).reverse().toString())
                .toList());
        // Output: [avaJ, maertS, IPA]
    }

    //24 Filter and Convert Map to List
    static void mapFilterKeys() {
        Map<String,Integer> map = Map.of("A",5,"B",15,"C",10,"D",20);
        List<String> keys = map.entrySet().stream()
                .filter(e->e.getValue()>10)
                .map(Map.Entry::getKey)
                .toList();
        System.out.println(keys);
        // Output: [B, C, D]
    }

    //25 Remove Duplicates Without Collectors
    static void removeDuplicates() {
        List<Integer> numbers = Arrays.asList(1,2,3,2,4,3,5);
        System.out.println(numbers.stream().distinct().toList());
        // Output: [1, 2, 3, 4, 5]
    }

    //26 Find the Most Frequent Character in a String
    static void mostFrequentChar() {
        String input="success";
        char c = input.chars().mapToObj(ch->(char)ch)
                .collect(Collectors.groupingBy(x->x,Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .get().getKey();
        System.out.println(c);
        // Output: s
    }

    //27 Flatten a List of Lists
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

    //28 Find the Sum of Squares of Even Numbers
    static void sumOfSquares() {
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6);
        int sum = numbers.stream()
                .filter(n->n%2==0)
                .mapToInt(n->n*n).sum();
        System.out.println(sum);
        // Output: 56
    }

    //29 Partition Strings by Palindrome
    static void partitionPalindrome() {
        List<String> words = Arrays.asList("radar","level","java","stream");
        Map<Boolean,List<String>> map =
                words.stream().collect(Collectors.partitioningBy(
                        w->w.equals(new StringBuilder(w).reverse().toString())
                ));
        System.out.println(map);
        // Output: {false=[java, stream], true=[radar, level]}
    }

    //30 Skip and Limit Elements
    static void skipLimit() {
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7);
        System.out.println(numbers.stream().skip(3).limit(2).toList());
        // Output: [4, 5]
    }

    //31 Concatenate Strings
    static void concatenateStrings() {
        List<String> words = Arrays.asList("Stream","API","is","powerful");
        String s = words.stream().reduce("",(a,b)->a+" "+b).trim();
        System.out.println(s);
        // Output: Stream API is powerful
    } 

    //32 Compute Cartesian Product of Two Lists
    static void cartesianProduct() {
        List<Integer> a=Arrays.asList(1,2,3);
        List<Integer> b=Arrays.asList(4,5);
        List<String> res = a.stream()
                .flatMap(i->b.stream().map(j->"("+i+","+j+")"))
                .toList();
        System.out.println(res);
        // Output: [(1, 4), (1, 5), (2, 4), (2, 5), (3, 4), (3, 5)]
    }



    //34 Group by First Character
    static void groupByFirstChar() {
        List<String> words = Arrays.asList("apple","banana","avocado");
        Map<Character,List<String>> map =
                words.stream().collect(Collectors.groupingBy(w->w.charAt(0)));
        System.out.println(map);
        // Output: {a=[apple, avocado], b=[banana]}
    }

    //35 Custom Reduce to Concatenate Strings
    static void reverseConcat() {
        List<String> words = Arrays.asList("Stream","API","is","awesome");
        String s = words.stream().reduce((a,b)->b+" "+a).orElse("");
        System.out.println(s);
        // Output: awesome is API Stream
    }

    //36 Find Median of a List
    static void median() {
        List<Integer> numbers = Arrays.asList(3,1,4,2,5);
        List<Integer> sorted = numbers.stream().sorted().toList();
        double median = sorted.get(sorted.size()/2);
        System.out.println(median);
        // Output: 3
    }

    //37 Collect Nested Map by Grouping
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

    // 38 Detect Cycles in a Graph-Like Structure
    static void detectCycle() {
        class Node {
            int id, parentId;
            Node(int id, int parentId) {
                this.id = id;
                this.parentId = parentId;
            }
        }

        List<Node> nodes = Arrays.asList(
                new Node(1,0), new Node(2,1), new Node(3,2),
                new Node(4,3), new Node(5,4), new Node(2,5)
        );

        boolean hasCycle = nodes.stream()
                .anyMatch(node -> nodes.stream()
                        .filter(n -> n.id == node.parentId)
                        .anyMatch(n -> n.id == node.id));

        System.out.println("Cycle detected: " + hasCycle);
        // Output: Cycle detected: true
    }

    // 39 Find the Word with Maximum Vowels
    static void wordWithMaxVowels() {
        List<String> words = Arrays.asList("stream","java","programming","awesome");

        String result = words.stream()
                .max(Comparator.comparingInt(
                        w -> (int) w.chars().filter(c -> "aeiou".indexOf(c)!=-1).count()
                )).orElse("");

        System.out.println("Word with most vowels: " + result);
        // Output: Word with most vowels: programming
    }

    // 40 Compute Running Sum
    static void runningSum() {
        List<Integer> numbers = Arrays.asList(1,2,3,4,5);

        List<Integer> sum = IntStream.range(0, numbers.size())
                .mapToObj(i -> numbers.subList(0,i+1).stream()
                        .mapToInt(Integer::intValue).sum())
                .collect(Collectors.toList());

        System.out.println("Running sum: " + sum);
        // Output: Running sum: [1, 3, 6, 10, 15]
    }

    // 41 Generate Fibonacci Sequence Using Streams
    static void fibonacci() {
        int n = 10;

        List<Integer> fib = Stream.iterate(new int[]{0,1},
                        arr -> new int[]{arr[1], arr[0]+arr[1]})
                .limit(n)
                .map(arr -> arr[0])
                .collect(Collectors.toList());

        System.out.println("Fibonacci: " + fib);
        // Output: Fibonacci: [0, 1, 1, 2, 3, 5, 8, 13, 21, 34]
    }

    // 42 Find Employees with Highest Salary in Each Department
    static void highestSalaryByDept() {

        class Employee {
            String name, dept;
            double salary;

            Employee(String name,String dept,double salary){
                this.name=name;
                this.dept=dept;
                this.salary=salary;
            }

            public String toString(){
                return name;
            }
        }

        List<Employee> employees = Arrays.asList(
                new Employee("Alice","HR",50000),
                new Employee("Bob","IT",80000),
                new Employee("Charlie","IT",75000),
                new Employee("Dave","HR",60000),
                new Employee("Eve","Finance",70000)
        );

        Map<String,Employee> result =
                employees.stream().collect(Collectors.groupingBy(
                        e -> e.dept,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparingDouble(e -> e.salary)),
                                Optional::get
                        )
                ));

        System.out.println("Highest salary by dept: " + result);
        // Output: {HR= Dave, IT= Bob}
    }

    // 43 Group Words by Length and Sort Them
    static void groupWordsByLength() {
        List<String> words = Arrays.asList("java","stream","api","example","code","test");

        Map<Integer,List<String>> map =
                words.stream().collect(Collectors.groupingBy(
                        String::length,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream().sorted().collect(Collectors.toList())
                        )
                ));

        System.out.println("Grouped words: " + map);
        // Output: {3=[api, code], 4=[java, main], 7=[example], 8=[programming]}
    }

    // 44 Calculate the Product of All Numbers
    static void productOfNumbers() {
        List<Integer> numbers = Arrays.asList(1,2,3,4,5);

        int product = numbers.stream().reduce(1,(a,b)->a*b);

        System.out.println("Product: " + product);
        // Output: Product: 120
    }

    // 45 Sliding Window of N Elements
    static void slidingWindow() {
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6);

        List<List<Integer>> windows =
                IntStream.range(0,numbers.size()-2)
                        .mapToObj(i -> numbers.subList(i,i+3))
                        .collect(Collectors.toList());

        System.out.println("Sliding windows: " + windows);
        // Output: [[1, 2, 3], [2, 3, 4], [3, 4, 5], [4, 5, 6]]
    }

    // 46 Detect Anagrams in a List
    static void detectAnagrams() {
        List<String> words = Arrays.asList("listen","silent","enlist","google","elbow","below");

        Map<String,List<String>> map =
                words.stream().collect(Collectors.groupingBy(
                        word -> word.chars().sorted()
                                .mapToObj(c -> String.valueOf((char)c))
                                .collect(Collectors.joining())
                ));

        System.out.println("Anagrams: " + map);
        // Output: Anagrams: {eilnst=[listen, silent, enlist], egglno=[google], beloqw=[elbow, below]}
    }

    // 47 Generate a Pyramid Pattern
    static void pyramidPattern() {
        int levels = 5;

        List<String> pyramid =
                IntStream.rangeClosed(1,levels)
                        .mapToObj(i -> " ".repeat(levels-i) +
                                IntStream.rangeClosed(1,i)
                                        .mapToObj(String::valueOf)
                                        .collect(Collectors.joining(" "))
                        ).collect(Collectors.toList());

        pyramid.forEach(System.out::println);
        // Output:     1
        //            1 2
        //           1 2 3
        //          1 2 3 4
        //         1 2 3 4 5
    }

    // 48 Find Maximum Path Sum in a Triangle
    static void maxPathTriangle() {

        List<List<Integer>> triangle = Arrays.asList(
                Arrays.asList(3),
                Arrays.asList(7,4),
                Arrays.asList(2,4,6),
                Arrays.asList(8,5,9,3)
        );

        int max =
                IntStream.range(0,triangle.size())
                        .mapToObj(i -> triangle.get(triangle.size()-1-i))
                        .reduce((rowBelow,currentRow) ->
                                IntStream.range(0,currentRow.size())
                                        .mapToObj(j -> currentRow.get(j)
                                                + Math.max(rowBelow.get(j),rowBelow.get(j+1)))
                                        .collect(Collectors.toList())
                        ).orElse(Collections.emptyList()).get(0);

        System.out.println("Max path sum: " + max);
        // Output: Max path sum: 23 (3 + 7 + 4 + 9)
    }

    // 49 Find Non-Repeating Characters in a String
    static void nonRepeatingCharacters() {
        String input="swiss";

        List<Character> list =
                input.chars().mapToObj(c -> (char)c)
                        .filter(c -> input.chars().filter(ch->ch==c).count()==1)
                        .collect(Collectors.toList());

        System.out.println("Non repeating: " + list);
        // Output: Non repeating: [s, w, i]
    }

    // 50 Find All Subsequences of a String
    static void allSubsequences() {
        String str="abc";

        List<String> subs =
                IntStream.range(0,1<<str.length())
                        .mapToObj(i ->
                                IntStream.range(0,str.length())
                                        .filter(j -> (i&(1<<j))!=0)
                                        .mapToObj(j -> String.valueOf(str.charAt(j)))
                                        .collect(Collectors.joining())
                        ).collect(Collectors.toList());

        System.out.println("Subsequences: " + subs);
        // Output: Subsequences: [, a, b, ab, c, ac, bc, abc]
    }

    // 53 Find Duplicate Elements in a List
    static void duplicateElements() {
        List<Integer> numbers = Arrays.asList(1,2,3,4,2,5,3,6);

        Set<Integer> duplicates =
                numbers.stream()
                        .filter(n -> Collections.frequency(numbers,n)>1)
                        .collect(Collectors.toSet());

        System.out.println("Duplicates: " + duplicates);
        // Output: Duplicates: [2, 3]
    }

    // 54 Convert Map to List of Key-Value Pairs
    static void mapToKeyValuePairs() {
        Map<String,Integer> map = Map.of("A",1,"B",2,"C",3);

        List<String> list =
                map.entrySet().stream()
                        .map(e -> e.getKey()+"="+e.getValue())
                        .collect(Collectors.toList());

        System.out.println("Key value pairs: " + list);
        // Output: [A=1, B=2, C=3]
    }

    // 55 Check if a List is Sorted
    static void checkSorted() {
        List<Integer> numbers = Arrays.asList(1,2,3,4,5);

        boolean sorted =
                IntStream.range(0,numbers.size()-1)
                        .allMatch(i -> numbers.get(i)<=numbers.get(i+1));

        System.out.println("Is sorted: " + sorted);
        //Output: Is sorted: true
    }

    // 56 Find All Subarrays of a List
    static void subarrays() {
        List<Integer> numbers = Arrays.asList(1,2,3);

        List<List<Integer>> subarrays =
                IntStream.range(0,numbers.size()).boxed()
                        .flatMap(i ->
                                IntStream.rangeClosed(i+1,numbers.size())
                                        .mapToObj(j -> numbers.subList(i,j))
                        ).collect(Collectors.toList());

        System.out.println("Subarrays: " + subarrays);
        // Output: [[1], [1, 2], [1, 2, 3], [2], [2, 3], [3]]
    }

    // 57 Combine Two Lists into a Map
    static void combineListsToMap() {
        List<String> keys = Arrays.asList("A","B","C");
        List<Integer> values = Arrays.asList(1,2,3);

        Map<String,Integer> map =
                IntStream.range(0,keys.size()).boxed()
                        .collect(Collectors.toMap(keys::get,values::get));

        System.out.println("Combined map: " + map);
        // Output: {A=1, B=2, C=3}
    }

    // 58 Find Pair of Numbers with a Given Sum
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

    // 59 Generate Random Numbers Using Stream
    static void randomNumbers() {
        List<Integer> nums =
                new Random().ints(5,1,100)
                        .boxed()
                        .collect(Collectors.toList());

        System.out.println("Random numbers: " + nums);
        // output: [23, 45, 67, 12, 89] (example random numbers between 1 and 100)
    }

    // 60 Find Longest Increasing Subsequence
    static void longestIncreasingSubsequence() {
        List<Integer> numbers = Arrays.asList(10,9,2,5,3,7,101,18);

        List<Integer> lis = new ArrayList<>();

        numbers.forEach(num -> {
            int pos = Collections.binarySearch(lis,num);
            if(pos<0) pos = -(pos+1);
            if(pos<lis.size()) lis.set(pos,num);
            else lis.add(num);
        });

        System.out.println("LIS: " + lis);
        // Output: [2, 3, 7, 18] (one of the longest increasing subsequences)
    }

    // 61 Sort a List Based on Frequency of Elements
    static void sortByFrequency() {
        List<Integer> numbers = Arrays.asList(4,5,6,5,4,3);

        List<Integer> sorted =
                numbers.stream()
                        .sorted(Comparator.comparingInt(n -> -Collections.frequency(numbers,n)))
                        .distinct()
                        .collect(Collectors.toList());

        System.out.println("Sorted by frequency: " + sorted);
        // Output: [4, 5, 6, 3] (4 and 5 appear twice, 6 and 3 appear once)
    }

    // 62 Convert List of Strings to Sentence Case
    static void sentenceCase() {
        List<String> words = Arrays.asList("java","stream","api");

        List<String> result =
                words.stream()
                        .map(w -> w.substring(0,1).toUpperCase()+w.substring(1).toLowerCase())
                        .collect(Collectors.toList());

        System.out.println("Sentence case: " + result);
        // Output: [Java, Stream, Api]
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
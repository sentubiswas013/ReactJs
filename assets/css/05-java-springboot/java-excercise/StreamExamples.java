import java.util.*;
import java.util.stream.*;

// https://medium.com/@asishpanda444/stream-api-coding-qna-8df8682b7e2a
public class StreamExamples {

    public static void main(String[] args) {

        filterEvenNumbers();
        findMax();
        sortDescending();
        countPrefix();
        firstNonRepeatedChar();
        convertUpperCase();
        sumNumbers();
        checkContainsAPI();
        findDuplicates();
        groupByLength();
        flattenList();
        concatenateStrings();
        longestString();
        charFrequency();
        parallelSum();
        joinStrings();
        removeNulls();
        averageNumbers();
        listToMap();
        partitionEvenOdd();
        thirdLargest();
        findPalindromes();
        reverseStrings();
        mapFilterKeys();
        removeDuplicates();
        mostFrequentChar();
        commonElements();
        sumOfSquares();
        partitionPalindrome();
        skipLimit();
        longestWordSentence();
        cartesianProduct();
        topStudents();
        groupByFirstChar();
        reverseConcat();
        median();
        groupEmployee();
        maxSalaryDept();
        productNumbers();
        slidingWindow();
        detectAnagrams();
        runningSum();
        fibonacci();
        sortedGroupedWords();
    }

    //1
    static void filterEvenNumbers() {
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6);
        List<Integer> even = numbers.stream().filter(n->n%2==0).toList();
        System.out.println(even);
    }

    //2
    static void findMax() {
        List<Integer> numbers = Arrays.asList(10,20,30,40,50);
        int max = numbers.stream().max(Integer::compare).orElseThrow();
        System.out.println(max);
    }

    //3
    static void sortDescending() {
        List<Integer> numbers = Arrays.asList(3,5,1,4,2);
        List<Integer> sorted = numbers.stream()
                .sorted(Comparator.reverseOrder())
                .toList();
        System.out.println(sorted);
    }

    //4
    static void countPrefix() {
        List<String> names = Arrays.asList("Alice","Bob","Annie","Alex","Charlie");
        long count = names.stream().filter(n->n.startsWith("A")).count();
        System.out.println(count);
    }

    //5
    static void firstNonRepeatedChar() {
        String input="swiss";
        Character ch = input.chars()
                .mapToObj(c->(char)c)
                .filter(c->input.indexOf(c)==input.lastIndexOf(c))
                .findFirst().orElse(null);
        System.out.println(ch);
    }

    //6
    static void convertUpperCase() {
        List<String> names = Arrays.asList("java","stream","api");
        System.out.println(names.stream().map(String::toUpperCase).toList());
    }

    //7
    static void sumNumbers() {
        List<Integer> numbers = Arrays.asList(1,2,3,4,5);
        int sum = numbers.stream().mapToInt(Integer::intValue).sum();
        System.out.println(sum);
    }

    //8
    static void checkContainsAPI() {
        List<String> list = Arrays.asList("Java","Stream API","Lambda");
        boolean result = list.stream().anyMatch(s->s.contains("API"));
        System.out.println(result);
    }

    //9
    static void findDuplicates() {
        List<Integer> numbers = Arrays.asList(1,2,3,4,2,5,1);
        Set<Integer> unique = new HashSet<>();
        Set<Integer> dup = numbers.stream()
                .filter(n->!unique.add(n))
                .collect(Collectors.toSet());
        System.out.println(dup);
    }

    //10
    static void groupByLength() {
        List<String> words = Arrays.asList("Java","Stream","API","Code","Fun");
        Map<Integer,List<String>> map =
                words.stream().collect(Collectors.groupingBy(String::length));
        System.out.println(map);
    }

    //11
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
    }

    //12
    static void concatenateStrings() {
        List<String> words = Arrays.asList("Stream","API","is","powerful");
        String s = words.stream().reduce("",(a,b)->a+" "+b).trim();
        System.out.println(s);
    }

    //13
    static void longestString() {
        List<String> words = Arrays.asList("Java","Stream","API","Development");
        String longest = words.stream()
                .max(Comparator.comparingInt(String::length))
                .orElse(null);
        System.out.println(longest);
    }

    //14
    static void charFrequency() {
        String input="success";
        Map<Character,Long> freq = input.chars()
                .mapToObj(c->(char)c)
                .collect(Collectors.groupingBy(c->c,Collectors.counting()));
        System.out.println(freq);
    }

    //15
    static void parallelSum() {
        List<Integer> numbers = Arrays.asList(1,2,3,4,5);
        int sum = numbers.parallelStream().mapToInt(Integer::intValue).sum();
        System.out.println(sum);
    }

    //16
    static void joinStrings() {
        List<String> words = Arrays.asList("Java","Stream","API");
        String joined = words.stream().collect(Collectors.joining(", "));
        System.out.println(joined);
    }

    //17
    static void removeNulls() {
        List<String> words = Arrays.asList("Java",null,"Stream",null,"API");
        System.out.println(words.stream().filter(Objects::nonNull).toList());
    }

    //18
    static void averageNumbers() {
        List<Integer> numbers = Arrays.asList(10,20,30,40,50);
        double avg = numbers.stream().mapToInt(Integer::intValue).average().orElse(0);
        System.out.println(avg);
    }

    //19
    static void listToMap() {
        List<String> words = Arrays.asList("Java","Stream","API");
        Map<String,Integer> map = words.stream()
                .collect(Collectors.toMap(w->w,String::length));
        System.out.println(map);
    }

    //20
    static void partitionEvenOdd() {
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6);
        Map<Boolean,List<Integer>> map =
                numbers.stream().collect(Collectors.partitioningBy(n->n%2==0));
        System.out.println(map);
    }

    //21
    static void thirdLargest() {
        List<Integer> numbers = Arrays.asList(10,20,50,40,30);
        int val = numbers.stream().sorted(Comparator.reverseOrder())
                .skip(2).findFirst().orElseThrow();
        System.out.println(val);
    }

    //22
    static void findPalindromes() {
        List<String> words = Arrays.asList("radar","level","world","java");
        List<String> result = words.stream()
                .filter(w->w.equals(new StringBuilder(w).reverse().toString()))
                .toList();
        System.out.println(result);
    }

    //23
    static void reverseStrings() {
        List<String> words = Arrays.asList("Java","Stream","API");
        System.out.println(words.stream()
                .map(w->new StringBuilder(w).reverse().toString())
                .toList());
    }

    //24
    static void mapFilterKeys() {
        Map<String,Integer> map = Map.of("A",5,"B",15,"C",10,"D",20);
        List<String> keys = map.entrySet().stream()
                .filter(e->e.getValue()>10)
                .map(Map.Entry::getKey)
                .toList();
        System.out.println(keys);
    }

    //25
    static void removeDuplicates() {
        List<Integer> numbers = Arrays.asList(1,2,3,2,4,3,5);
        System.out.println(numbers.stream().distinct().toList());
    }

    //26
    static void mostFrequentChar() {
        String input="success";
        char c = input.chars().mapToObj(ch->(char)ch)
                .collect(Collectors.groupingBy(x->x,Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .get().getKey();
        System.out.println(c);
    }

    //27
    static void commonElements() {
        List<Integer> a = Arrays.asList(1,2,3,4,5);
        List<Integer> b = Arrays.asList(3,4,5,6);
        System.out.println(a.stream().filter(b::contains).toList());
    }

    //28
    static void sumOfSquares() {
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6);
        int sum = numbers.stream()
                .filter(n->n%2==0)
                .mapToInt(n->n*n).sum();
        System.out.println(sum);
    }

    //29
    static void partitionPalindrome() {
        List<String> words = Arrays.asList("radar","level","java","stream");
        Map<Boolean,List<String>> map =
                words.stream().collect(Collectors.partitioningBy(
                        w->w.equals(new StringBuilder(w).reverse().toString())
                ));
        System.out.println(map);
    }

    //30
    static void skipLimit() {
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7);
        System.out.println(numbers.stream().skip(3).limit(2).toList());
    }

    //31
    static void longestWordSentence() {
        String sentence="Java Stream API is very powerful";
        String longest = Arrays.stream(sentence.split(" "))
                .max(Comparator.comparingInt(String::length)).orElse(null);
        System.out.println(longest);
    }

    //32
    static void cartesianProduct() {
        List<Integer> a=Arrays.asList(1,2,3);
        List<Integer> b=Arrays.asList(4,5);
        List<String> res = a.stream()
                .flatMap(i->b.stream().map(j->"("+i+","+j+")"))
                .toList();
        System.out.println(res);
    }

    //33
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
    }

    //34
    static void groupByFirstChar() {
        List<String> words = Arrays.asList("apple","banana","avocado");
        Map<Character,List<String>> map =
                words.stream().collect(Collectors.groupingBy(w->w.charAt(0)));
        System.out.println(map);
    }

    //35
    static void reverseConcat() {
        List<String> words = Arrays.asList("Stream","API","is","awesome");
        String s = words.stream().reduce((a,b)->b+" "+a).orElse("");
        System.out.println(s);
    }

    //36
    static void median() {
        List<Integer> numbers = Arrays.asList(3,1,4,2,5);
        List<Integer> sorted = numbers.stream().sorted().toList();
        double median = sorted.get(sorted.size()/2);
        System.out.println(median);
    }

    //37
    static void groupEmployee() {
        List<Employee> list = Arrays.asList(
                new Employee("Alice","HR",25),
                new Employee("Bob","IT",30),
                new Employee("Charlie","HR",30)
        );

        Map<String,List<Employee>> map =
                list.stream().collect(Collectors.groupingBy(Employee::getDepartment));
        System.out.println(map);
    }

    //38
    static void maxSalaryDept() {
        List<EmployeeSalary> list = Arrays.asList(
                new EmployeeSalary("Alice","HR",50000),
                new EmployeeSalary("Bob","IT",80000),
                new EmployeeSalary("Charlie","IT",75000)
        );

        Map<String,Optional<EmployeeSalary>> map =
                list.stream().collect(Collectors.groupingBy(
                        EmployeeSalary::getDepartment,
                        Collectors.maxBy(Comparator.comparingDouble(EmployeeSalary::getSalary))
                ));
        System.out.println(map);
    }

    //39
    static void productNumbers() {
        List<Integer> numbers = Arrays.asList(1,2,3,4,5);
        int product = numbers.stream().reduce(1,(a,b)->a*b);
        System.out.println(product);
    }

    //40
    static void slidingWindow() {
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6);
        List<List<Integer>> windows =
                IntStream.range(0,numbers.size()-2)
                        .mapToObj(i->numbers.subList(i,i+3))
                        .toList();
        System.out.println(windows);
    }

    //41
    static void detectAnagrams() {
        List<String> words = Arrays.asList("listen","silent","enlist","google");
        Map<String,List<String>> map =
                words.stream().collect(Collectors.groupingBy(
                        w->w.chars().sorted()
                                .mapToObj(c->String.valueOf((char)c))
                                .collect(Collectors.joining())
                ));
        System.out.println(map);
    }

    //42
    static void runningSum() {
        List<Integer> numbers = Arrays.asList(1,2,3,4,5);
        List<Integer> sum =
                IntStream.range(0,numbers.size())
                        .mapToObj(i->numbers.subList(0,i+1)
                                .stream().mapToInt(Integer::intValue).sum())
                        .toList();
        System.out.println(sum);
    }

    //43
    static void fibonacci() {
        List<Integer> fib =
                Stream.iterate(new int[]{0,1},a->new int[]{a[1],a[0]+a[1]})
                        .limit(10)
                        .map(a->a[0])
                        .toList();
        System.out.println(fib);
    }

    //44
    static void sortedGroupedWords() {
        List<String> words = Arrays.asList("java","stream","api","code");
        Map<Integer,List<String>> map =
                words.stream().collect(Collectors.groupingBy(
                        String::length,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                l->l.stream().sorted().toList()
                        )
                ));
        System.out.println(map);
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
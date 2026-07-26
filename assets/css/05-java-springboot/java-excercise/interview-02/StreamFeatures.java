import java.util.*;
import java.util.function.*;
import java.util.stream.*;

// ============================================================
// 39–43 + Extra: Java 8 Features (Interview Ready)
// ============================================================

class Main {
    public static void main(String[] args) {
        StringPoolDemo();
        lambdaDemo();
        functionalInterfaceDemo();        
        methodReferenceDemo();

        comparatorLambdaDemo();
        StreamApiDemo();
        ParallelStreamDemo();     
        
    }

    //=============================================
    // 1. String Pooling is a Java memory optimization technique where string literals are stored in a special memory area called the String Pool. 
    //=============================================
    static void StringPoolDemo() {
        System.out.println("=========================== StringPoolDemo");

        String s1 = "hello";
        String s2 = new String("hello");
        String s3 = "hello";

        System.out.println(s1 == s2); // false
        System.out.println(s1 == s3); // true
    }

    //=============================================
    // 39. Lambda Expression / Functional Interfaces
    // A Functional Interface is an interface that contains exactly one abstract method 
    //=============================================
    static void lambdaDemo() {
        System.out.println("=========================== lambdaDemo");

        // Way 1
        Calculator add = (a, b) -> a + b;
        System.out.println("Sum: " + add.calculate(5, 6));

        // Way 2
        Runnable r = () -> System.out.println("Lambda Running");
        r.run();
    }

    interface Calculator {
        int calculate (int a, int b);
    }

    //=============================================
    // A Functional Interface is an interface that contains exactly one abstract method 
    // Predicate, Function, Consumer
    //=============================================
    static void functionalInterfaceDemo() {
        System.out.println("=========================== functionalInterfaceDemo");

        Predicate<Integer> isEven = n -> n % 2 == 0;
        System.out.println("Is 10 even? " + isEven.test(10));

        Function<Integer, Integer> square = n -> n * n;
        System.out.println("Square: " + square.apply(5));

        Consumer<String> print = s -> System.out.println("Hello " + s);
        print.accept("Java");

        // Way 1
        CalculatorFunc add = (a, b) -> a + b;
        System.out.println("Sum " + add.calculate(5,6));
    }

    interface CalculatorFunc {
        int calculate (int a, int b);
    }

    //=============================================
    // A Method Reference is a shorthand syntax of a lambda expression that refers to an existing method using :: operator.
    //=============================================
    static void methodReferenceDemo() {
        System.out.println("=========================== methodReferenceDemo");

        List<Student> studlist = Arrays.asList(
                new Student("Alice", 22),
                new Student("Bob", 20),
                new Student("Charlie", 23)
        );

        // Lambda Expression
        OptionalInt maxAge1 = studlist.stream()
                .mapToInt(student -> student.getAge())
                .max();

        System.out.println("Max Age (Lambda): " + maxAge1.getAsInt());

        // Method Reference
        OptionalInt maxAge2 = studlist.stream()
                .mapToInt(Student::getAge)
                .max();

        System.out.println("Max Age (Method Reference): " + maxAge2.getAsInt());

        // Constructor Reference
        Supplier<List<String>> listSupplier = ArrayList::new;

        List<String> list = listSupplier.get();
        list.add("Java");
        list.add("Spring");

        System.out.println("Constructor Reference: " + list);
    }
    

    // ============================================================
    // Comparator using Lambda
    // ============================================================
    static void comparatorLambdaDemo() {
        System.out.println("=========================== comparatorLambdaDemo");

        List<String> list = Arrays.asList("Banana", "Apple", "Mango");

        list.sort((a, b) -> a.compareTo(b));

        System.out.println("Sorted: " + list);
    }

    //=============================================
    // The Stream API is introduced in Java 8 to process collections of objects in a functional and declarative way. It allows operations like filtering, mapping, and reducing without modifying the original data source.
    // filter, map, reduce, collect
    //=============================================
    static void StreamApiDemo() {
        System.out.println("=========================== StreamApiDemo");

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        // filter + map + collect
        List<Integer> result = list.stream()
                .filter(n -> n % 2 == 0)
                .map(n -> n * 2)
                .collect(Collectors.toList());

        System.out.println("Stream result: " + result);

        // reduce
        int sum = list.stream()
            .reduce(0, Integer::sum);

        System.out.println("Sum: " + sum);
    }

    // ============================================================
    // A Parallel Stream is a type of Stream API that processes data concurrently using multiple threads, dividing the task into smaller parts and executing them in parallel to improve performance.
    // ============================================================
    static void ParallelStreamDemo() {
        System.out.println("=========================== ParallelStreamDemo");

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
            numbers.parallelStream()
            .map(n -> {
                System.out.println(Thread.currentThread().getName() + " processing " + n);
                return n * n;
            })
            .forEach(System.out::println);
    }

    //=============================================
    // Student Class
    //=============================================
    static class Student {

        private String name;
        private int age;

        Student(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }
}
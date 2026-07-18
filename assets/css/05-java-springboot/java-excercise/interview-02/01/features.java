import java.util.*;
import java.util.function.*;
import java.util.stream.*;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

// ============================================================
// 39–43 + Extra: Java 8 Features (Interview Ready)
// ============================================================

class Main {
    public static void main(String[] args) {
        StringPoolDemo();
        lambdaDemo();
        functionalInterfaceDemo();        
        methodReferenceDemo();
        optionalDemo();
        HascodeEqualsDemo();

        comparatorLambdaDemo();
        StreamApiDemo();
        ParallelStreamDemo();
        MapDemo();
        flatMapDemo();
        CopyOnWriteArrayList();
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
        System.out.println("Sum " + add.calculate(5,6));

        // Way 2
        Runnable r = () -> System.out.println("Lambda Running");
        r.run();
    }

    interface Calculator {
        int calculate (int a, int b);
    }

    //=============================================
    // 40. Functional Interfaces
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
    }

    //=============================================
    // A Method Reference is a shorthand syntax of a lambda expression that refers to an existing method using :: operator.
    //=============================================
    static void methodReferenceDemo() {
        System.out.println("=========================== methodReferenceDemo");

        List<String> list = Arrays.asList("A", "B", "C");

        // Instead of lambda
        list.forEach(System.out::println);
    }

    //=============================================
    // Optional Class is a container object introduced in Java 8 that can either hold a value or be empty. It is mainly used to avoid NullPointerException and write cleaner, safer code.
    //=============================================
    static void optionalDemo() {
        System.out.println("=========================== optionalDemo");

        Optional<String> name = Optional.ofNullable(null);

        // default value
        System.out.println(name.orElse("Default Name"));

        // check value
        name.ifPresent(System.out::println);
    }

    //=============================================
    // 43. Hashcode and Equals and Objects Utility
    //=============================================
    public static void HascodeEqualsDemo() {
        System.out.println("=========================== HascodeEqualsDemo");

        Person p1 = new Person("Banani");
        Person p2 = new Person("Banani");

        System.out.println(p1.equals(p2));

        System.out.println(p1.hashCode());
        System.out.println(p2.hashCode());
    }
    
    // import java.util.Objects;
    static class Person {
        private String name;
        Person(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object)
                return true;

            if (!(object instanceof Person person))
                return false;

            return Objects.equals(name, person.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }

    // ============================================================
    // Comparator
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

    // ============================================================
    // Map() is used to transform each element in a stream into another form. It returns one output for each input, so the structure of the stream stays the same.
    // ============================================================
    static void MapDemo() {
        System.out.println("=========================== MapDemo");
        List<String> names = List.of("alice", "bob");

        List<String> upperNames = names.stream()
               .map(String::toUpperCase)
               .toList();

        System.out.println(upperNames);   // [ALICE, BOB]
    }

    // ============================================================
    // FlatMap() is used when each element produces another stream or collection. It flattens those nested streams into a single stream, so you don’t end up with a stream of streams.
    // ============================================================
    static void flatMapDemo() {
        System.out.println("=========================== flatMapDemo");

        List<List<Integer>> list = Arrays.asList(
            Arrays.asList(1, 2),
            Arrays.asList(3, 4)
        );

        List<Integer> flatList = list.stream()
            .flatMap(Collection::stream)
            .collect(Collectors.toList());

        System.out.println("FlatMap: " + flatList);
    }

    // ============================================================
    // CopyOnWriteArrayList() is a thread-safe implementation of the List interface. Whenever an element is added, updated, or removed, it creates a new copy of the underlying array instead of modifying the existing one.
    // ============================================================
    static void CopyOnWriteArrayList() {
        System.out.println("=========================== CopyOnWriteArrayList");

        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

        list.add("Java");
        list.add("Spring");

        for (String item : list) {
            System.out.println(item);

            // Safe modification during iteration
            list.add("Docker");
        }

        System.out.println("Result: " + list);
    }

}
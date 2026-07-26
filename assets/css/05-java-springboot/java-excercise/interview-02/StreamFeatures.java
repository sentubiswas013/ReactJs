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
        System.out.println("Sum " + add.calculate(5,6));

        // Way 2
        Runnable r = () -> System.out.println("Lambda Running");
        r.run();
    }

    interface Calculator {
        int calculate (int a, int b);
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


    

}
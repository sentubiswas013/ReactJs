import java.util.*;
import java.util.function.*;
import java.util.stream.*;
import java.util.HashMap;
import java.util.Objects;

// ============================================================
// 39–43 + Extra: Java 8 Features (Interview Ready)
// ============================================================

class Main {
    public static void main(String[] args) {

        lambdaDemo();
        functionalInterfaceDemo();
        streamsDemo();
        methodReferenceDemo();
        optionalDemo();

        // 🔥 Extra Important
        comparatorLambdaDemo();
        flatMapDemo();
    }

    //=============================================
    // 39. Lambda Expression
    //=============================================
    static void lambdaDemo() {
        Runnable r = () -> System.out.println("Lambda Running");
        r.run();
    }

    //=============================================
    // 40. Functional Interfaces
    // Predicate, Function, Consumer
    //=============================================
    static void functionalInterfaceDemo() {

        Predicate<Integer> isEven = n -> n % 2 == 0;
        System.out.println("Is 10 even? " + isEven.test(10));

        Function<Integer, Integer> square = n -> n * n;
        System.out.println("Square: " + square.apply(5));

        Consumer<String> print = s -> System.out.println("Hello " + s);
        print.accept("Java");
    }

    //=============================================
    // 41. Streams API
    // filter, map, reduce, collect
    //=============================================
    static void streamsDemo() {

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

    //=============================================
    // 42. Method Reference
    //=============================================
    static void methodReferenceDemo() {
        List<String> list = Arrays.asList("A", "B", "C");

        // Instead of lambda
        list.forEach(System.out::println);
    }

    //=============================================
    // 43. Optional
    // Avoid NullPointerException
    //=============================================
    static void optionalDemo() {

        Optional<String> name = Optional.ofNullable(null);

        // default value
        System.out.println(name.orElse("Default Name"));

        // check value
        name.ifPresent(System.out::println);
    }

    //=============================================
    // 43. Hashcode and Equals and Objects Utility
    //=============================================
    class HascodeEqualsDemo {
        public static void main(String[] args) {
            Person p1 = new Person("Banani");
            Person p2 = new Person("Banani");

            System.out.println(p1.equals(p2));

            System.out.println(p1.hashCode());
            System.out.println(p2.hashCode());
        }
    }
    
    // import java.util.Objects;
    class Person {
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
    // 🔥 EXTRA IMPORTANT TOPICS
    // ============================================================

    // Comparator using Lambda
    static void comparatorLambdaDemo() {
        List<String> list = Arrays.asList("Banana", "Apple", "Mango");

        list.sort((a, b) -> a.compareTo(b));

        System.out.println("Sorted: " + list);
    }

    // flatMap
    static void flatMapDemo() {
        List<List<Integer>> list = Arrays.asList(
                Arrays.asList(1, 2),
                Arrays.asList(3, 4)
        );

        List<Integer> flatList = list.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        System.out.println("FlatMap: " + flatList);
    }
}
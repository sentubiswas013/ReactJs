import java.util.*;
import java.util.function.*;
import java.util.stream.*;
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
        HashcodeDemo();

        // 🔥 Extra Important
        comparatorLambdaDemo();
        flatMapDemo();
    }

    // ============================================================
    // 39. Lambda Expression
    // ============================================================
    public static void lambdaDemo() {
        System.out.println("=========================== lambdaDemo");
    }

    // ============================================================
    // 40. Functional Interfaces
    // Predicate, Function, Consumer
    // ============================================================
    public static void functionalInterfaceDemo() {
        System.out.println("=========================== functionalInterfaceDemo");
    }

    // ============================================================
    // 41. Streams API
    // filter, map, reduce, collect
    // ============================================================
    public static void streamsDemo() {
        System.out.println("=========================== streamsDemo");
    }

    // ============================================================
    // 42. Method Reference
    // ============================================================
    public static void methodReferenceDemo() {
        System.out.println("=========================== methodReferenceDemo");
    }

    // ============================================================
    // 43. Optional
    // Avoid NullPointerException
    // ============================================================
    public static void optionalDemo() {
        System.out.println("=========================== optionalDemo");
    }

    // ============================================================
    // 43. Hashcode
    // Override equal
    // ============================================================        
    public static void HashcodeDemo() {
        System.out.println("=========================== HashcodeDemo");
    }


    // ============================================================
    // 🔥 EXTRA IMPORTANT TOPICS
    // ============================================================
    // Comparator using Lambda
    public static void comparatorLambdaDemo() {
        System.out.println("=========================== comparatorLambdaDemo");
    }


    // flatMap
    public static void flatMapDemo() {
        System.out.println("=========================== flatMapDemo");
    }

}
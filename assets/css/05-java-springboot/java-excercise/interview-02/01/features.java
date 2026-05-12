import java.util.*;
import java.util.function.*;
import java.util.stream.*;
import java.util.Objects;

// ============================================================
// 39–43 + Extra: Java 8 Features (Interview Ready)
// ============================================================

class Main {
    public static void main(String[] args) {

        // lambdaDemo();
        // functionalInterfaceDemo();
        // streamsDemo();
        // methodReferenceDemo();
        // optionalDemo();

        // 🔥 Extra Important
        // comparatorLambdaDemo();
        // flatMapDemo();


        // ==============================
        Person p1 = new Person("Banani");
        Person p2 = new Person("Banani");

        System.out.println(p1.equals(p2));

        System.out.println(p1.hashCode());
        System.out.println(p2.hashCode());
    }

    // ─────────────────────────────────────────────
    // 39. Lambda Expression
    // ─────────────────────────────────────────────
    

    // ─────────────────────────────────────────────
    // 40. Functional Interfaces
    // Predicate, Function, Consumer
    // ─────────────────────────────────────────────
    

    // ─────────────────────────────────────────────
    // 41. Streams API
    // filter, map, reduce, collect
    // ─────────────────────────────────────────────
    

    // ─────────────────────────────────────────────
    // 42. Method Reference
    // ─────────────────────────────────────────────


    // ─────────────────────────────────────────────
    // 43. Optional
    // Avoid NullPointerException
    // ─────────────────────────────────────────────


    // ─────────────────────────────────────────────
    // 43. Hashcode
    // Avoid NullPointerException
    // ─────────────────────────────────────────────
        
    static class Person {
        private String name;
        Person (String name) {
            this.name = name;
        }

        @Override
        public boolean equals (Object object) {
            if(this == object) return true;
            if (!(object instanceof Person person)) return false;

            return Objects.equals(name, person.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }


    // static class Person {
    //     private String name;
    //     Person(String name) {
    //         this.name = name;
    //     }

    //     @Override
    //     public boolean equals(Object object) {
    //         if (this == object)
    //             return true;

    //         if (!(object instanceof Person person))
    //             return false;

    //         return Objects.equals(name, person.name);
    //     }

    //     @Override
    //     public int hashCode() {
    //         return Objects.hash(name);
    //     }
    // }


    // ============================================================
    // 🔥 EXTRA IMPORTANT TOPICS
    // ============================================================


    // Comparator using Lambda



    // flatMap

}
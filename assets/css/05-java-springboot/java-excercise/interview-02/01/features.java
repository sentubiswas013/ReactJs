import java.util.*;
import java.util.function.*;
import java.util.stream.*;

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
    class Person {
        private String name;

        private Person(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object obj) {
            if(this == obj)  return true;
            if(!(obj instanceof Person person))  return false; 
            return age = person.age && Object.equals(name, person.name);
        }

        @Override 
        public int Hashcode () {

        }
    }


    // ============================================================
    // 🔥 EXTRA IMPORTANT TOPICS
    // ============================================================


    // Comparator using Lambda



    // flatMap

}
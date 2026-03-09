import java.security.Key;
import java.util.*;
import java.util.function.Function;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.OptionalInt;


// ## ✅ Hello World Program
public class InterviewAttended01 {
    public static void main(String[] args) {
        // System.out.println("Hello, World!");
        
    }


    // ==============================================================
    // Tricky Questions
    // ==============================================================
    // **1️⃣ BreakTest**
    // ```java
    // public class BreakTest {
    //     public static void main(String[] args)
    //     {
    //     int i=1;
    //         if (i==1) {
    //             break; 
    //             }
    //     System.out.println("break");
    //     }
    // }
    // ```

    // ✅ Answer:
    // ❌ **Compilation Error**


    // ## **2️⃣ HelloFind**
    // ```java
    // List<Integer> listOfInt = Arrays.asList(1,1,2,3,3,4);
    // Set<Integer> set = new HashSet();
    // listOfInt.stream()
    //     .filter(n -> !set.add(n))
    //     .forEach(System.out::println);
    // ```
    // ### ✅ Answer:

    // ```
    // 1
    // 3
    // ```

    // ## **3️⃣ Assignment**
    // ```java
    // int k = 0;
    // for (int i = 0; i < 100; i++)
    //     k = k++; 
    // System.out.println(k);
    // System.out.println(k);
    // ```

    // ### ✅ Answer:
    // ```
    // 0
    // 0
    // ```

    // ## **4️⃣ SwitchTest**

    // ```java
    // final static short i = 2;

    // for (int n = 0; n < 3; n++)
    // {
    //     switch (n)
    //     {
    //         case i: System.out.print(" 2 ");
    //         case i-1: System.out.print(" 1 ");
    //         case i-2: System.out.print(" 3 ");  
    //     }
    // }
    // ```


    // ### ✅ Final Output:
    // 3 1 3 2 1 3

    // ==============================================================
    public class Test {
 
        public static void test(int... x) {
            System.out.println("Varargs");
        }
    
        public static void test(int x) {
            System.out.println("Single");
        }
    
        public static void main(String[] args) {
            test();
        }
    }
    // ### ✅ Final Output: Varargs

    // ==============================================================
    class A {
        void show(int i) {
            System.out.println("A int");
        }
    }
    
    class B extends A {
        void show(Integer i) {
            System.out.println("B Integer");
        }
    }
    
    public class IntTest{
        public static void main(String[] args) {
        
            B obj = new B();
            obj.show(null);
            
            A obj2 = new B();
            
            obj2.show(30);
        }
    }

    // ### ✅Output: 
    // B Integer
    // A int

    // ==============================================================
    public class Test {
    public static void main(String[] args) {
      System.out.println("Hello, World!");
	  
        HashMap<Key, String> map = new HashMap<>();
        Key k1 = new Key(1);
        map.put(k1, "Value");
        k1.id = 2;
        System.out.println(map.get(k1));      
        }
    }
    
    
    class Key {
        int id;
        Key(int id) { this.id = id; }
    
        public int hashCode() { return id; }
    
        public boolean equals(Object o) {
            return ((Key)o).id == this.id;
        }
    }

    // ### ✅Output: null

}



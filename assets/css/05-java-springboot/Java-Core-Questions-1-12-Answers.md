# Core Java Questions (1-12) - Answers with Examples

## 1. Explain `==` vs `equals()` vs `hashCode()` contract.

**Answer:**
`==` compares memory references for objects and values for primitives. `equals()` compares the actual content or logical equality of objects. The `hashCode()` contract states that if two objects are equal according to `equals()`, they must have the same hash code, but objects with the same hash code aren't necessarily equal.

```java
String s1 = new String("hello");
String s2 = new String("hello");
String s3 = s1;

System.out.println(s1 == s2);        // false (different objects)
System.out.println(s1 == s3);        // true (same reference)
System.out.println(s1.equals(s2));   // true (same content)
System.out.println(s1.hashCode() == s2.hashCode()); // true
```

## 2. Why is String immutable? What are the benefits?

**Answer:**
Strings are immutable because their internal char array is final and private with no setters. Benefits include thread safety without synchronization, string pool optimization for memory efficiency, security for sensitive data like passwords and connection URLs, and safe use as HashMap keys since hash code won't change.

```java
String str = "Java";
str.concat(" Programming"); // Creates new String, doesn't modify original
System.out.println(str);     // Still prints "Java"

String modified = str.concat(" Programming");
System.out.println(modified); // Prints "Java Programming"
```

## 3. How does HashMap work internally in Java 8+?

**Answer:**
HashMap uses an array of buckets where each bucket can hold a linked list or tree structure. It calculates the hash code of the key, applies a hash function to determine the bucket index, and stores the key-value pair. In Java 8+, when a bucket has more than 8 entries, it converts from linked list to a balanced tree (red-black tree) for better performance, reducing lookup time from O(n) to O(log n).

```java
HashMap<String, Integer> map = new HashMap<>();
map.put("John", 25);  // hash("John") -> bucket index -> store entry

// Internal process:
// 1. hash = key.hashCode()
// 2. index = (n-1) & hash  (n = array length)
// 3. Store at array[index]
```

## 4. What happens if two objects have the same hashCode?

**Answer:**
This is called a hash collision. HashMap handles it by storing multiple entries in the same bucket using a linked list or tree structure. When retrieving, it uses `equals()` to find the exact match among entries with the same hash code.

```java
class Person {
    String name;
    public int hashCode() { return 1; } // All return same hash
    public boolean equals(Object o) {
        return this.name.equals(((Person)o).name);
    }
}

HashMap<Person, String> map = new HashMap<>();
map.put(new Person("Alice"), "Engineer");
map.put(new Person("Bob"), "Doctor"); // Same bucket, different entry
```

## 5. Difference between abstract class and interface (Java 8+).

**Answer:**
Abstract classes can have constructors, instance variables, and concrete methods with implementation. Interfaces (Java 8+) can have default and static methods but no constructors or instance variables. A class can implement multiple interfaces but extend only one abstract class. Use abstract classes for "is-a" relationships with shared state, interfaces for "can-do" capabilities.

```java
abstract class Animal {
    String name;
    abstract void sound();
    void sleep() { System.out.println("Sleeping"); }
}

interface Flyable {
    default void fly() { System.out.println("Flying"); }
    static void glide() { System.out.println("Gliding"); }
}

class Bird extends Animal implements Flyable {
    void sound() { System.out.println("Chirp"); }
}
```

## 6. Explain method overloading vs overriding with covariant return types.

**Answer:**
Overloading is having multiple methods with the same name but different parameters in the same class (compile-time polymorphism). Overriding is redefining a parent class method in a child class with the same signature (runtime polymorphism). Covariant return types allow an overriding method to return a subtype of the original return type.

```java
class Parent {
    Animal getAnimal() { return new Animal(); }
    void print(int x) { }
    void print(String s) { } // Overloading
}

class Child extends Parent {
    @Override
    Dog getAnimal() { return new Dog(); } // Covariant return (Dog extends Animal)
}
```

## 7. What are default and static methods in interfaces?

**Answer:**
Default methods provide a default implementation in interfaces, allowing you to add new methods without breaking existing implementations. Static methods belong to the interface itself and can be called without an instance. Both were introduced in Java 8 to enable interface evolution.

```java
interface Vehicle {
    default void start() {
        System.out.println("Vehicle starting");
    }
    
    static void service() {
        System.out.println("Service all vehicles");
    }
}

class Car implements Vehicle {
    // Can use default start() or override it
}

Vehicle.service(); // Call static method
```

## 8. Explain autoboxing/unboxing and performance implications.

**Answer:**
Autoboxing automatically converts primitives to wrapper objects, unboxing does the reverse. Performance implications include memory overhead (wrapper objects consume more memory), potential NullPointerException during unboxing, and slower execution due to object creation and garbage collection.

```java
// Autoboxing
Integer obj = 10; // int -> Integer (automatic)

// Unboxing
int primitive = obj; // Integer -> int (automatic)

// Performance issue
Integer sum = 0;
for(int i = 0; i < 1000; i++) {
    sum += i; // Unbox, add, autobox repeatedly - slow!
}
```

## 9. Difference between `String`, `StringBuilder`, `StringBuffer`.

**Answer:**
String is immutable and thread-safe. StringBuilder is mutable, not thread-safe, and faster for single-threaded string manipulation. StringBuffer is mutable, thread-safe (synchronized methods), and slower than StringBuilder. Use String for fixed text, StringBuilder for single-threaded concatenation, StringBuffer for multi-threaded scenarios.

```java
String str = "Hello";
str += " World"; // Creates new String object each time

StringBuilder sb = new StringBuilder("Hello");
sb.append(" World"); // Modifies same object - faster

StringBuffer sbf = new StringBuffer("Hello");
sbf.append(" World"); // Thread-safe but slower
```

## 10. How does String pool and `intern()` method work?

**Answer:**
The String pool is a special memory area in the heap where Java stores string literals to optimize memory. When you create a string literal, Java checks the pool first and reuses existing strings. The `intern()` method manually adds a string to the pool or returns the existing pooled reference.

```java
String s1 = "hello";        // Goes to pool
String s2 = "hello";        // Reuses from pool
System.out.println(s1 == s2); // true

String s3 = new String("hello"); // Heap, not pool
System.out.println(s1 == s3);    // false

String s4 = s3.intern();    // Returns pool reference
System.out.println(s1 == s4);    // true
```

## 11. Explain `final`, `finally`, `finalize`.

**Answer:**
`final` is a keyword making variables constant, methods non-overridable, and classes non-inheritable. `finally` is a block that always executes after try-catch, used for cleanup. `finalize()` is a deprecated method called by garbage collector before object destruction, unreliable for cleanup.

```java
final int MAX = 100; // Constant

try {
    int x = 10/0;
} catch(Exception e) {
    System.out.println("Error");
} finally {
    System.out.println("Always executes"); // Cleanup code
}

class Resource {
    @Deprecated
    protected void finalize() {
        // Called before GC (unreliable, use try-with-resources instead)
    }
}
```

## 12. What is the difference between shallow copy and deep copy?

**Answer:**
Shallow copy creates a new object but copies references to nested objects, so both original and copy share the same nested objects. Deep copy creates a new object and recursively copies all nested objects, creating completely independent copies. Modifying nested objects in shallow copy affects both, while deep copy remains independent.

```java
class Address {
    String city;
    Address(String city) { this.city = city; }
}

class Person implements Cloneable {
    String name;
    Address address;
    
    // Shallow copy
    public Object clone() throws CloneNotSupportedException {
        return super.clone(); // Copies reference to address
    }
    
    // Deep copy
    public Person deepCopy() {
        Person copy = new Person();
        copy.name = this.name;
        copy.address = new Address(this.address.city); // New Address object
        return copy;
    }
}

Person p1 = new Person();
p1.address = new Address("NYC");

Person p2 = (Person) p1.clone(); // Shallow
p2.address.city = "LA"; // Affects p1 too!

Person p3 = p1.deepCopy(); // Deep
p3.address.city = "Boston"; // p1 unaffected
```


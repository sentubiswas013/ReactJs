# 14. Java Lambda Expressions & Streams API 

## 1. What are lambda expressions?

Lambda expressions are anonymous functions that provide a concise way to represent functional interfaces. They enable functional programming in Java and make code more readable and expressive.

- Anonymous functions without name
- Concise syntax for functional interfaces
- Enable functional programming style
- Reduce boilerplate code
- Introduced in Java 8

```java
// Before lambda - anonymous class
Runnable r1 = new Runnable() {
    public void run() {
        System.out.println("Hello");
    }
};

// With lambda - concise
Runnable r2 = () -> System.out.println("Hello");

// Lambda with parameters
List<String> names = Arrays.asList("John", "Jane");
names.forEach(name -> System.out.println(name));
```

## 2. What are functional interfaces?

Functional interfaces are interfaces with exactly one abstract method. They can be implemented using lambda expressions and serve as the foundation for functional programming in Java.

- Exactly one abstract method (SAM - Single Abstract Method)
- Can have default and static methods
- @FunctionalInterface annotation for safety
- Target type for lambda expressions
- Examples: Runnable, Callable, Comparator

```java
@FunctionalInterface
interface Calculator {
    int calculate(int a, int b); // Single abstract method
    
    default void print() { } // Default methods allowed
    static void info() { } // Static methods allowed
}

// Usage with lambda
Calculator add = (a, b) -> a + b;
Calculator multiply = (a, b) -> a * b;
```

## 3. What are method references?

Method references are shorthand notation for lambda expressions that call a single method. They make code even more concise when lambda just calls an existing method.

**Types of Method References:**
- Static method: `ClassName::methodName`
- Instance method: `object::methodName`
- Instance method of arbitrary object: `ClassName::methodName`
- Constructor: `ClassName::new`

```java
List<String> names = Arrays.asList("john", "jane", "bob");

// Lambda expression
names.forEach(name -> System.out.println(name));

// Method reference - more concise
names.forEach(System.out::println);

// Constructor reference
Supplier<List<String>> listSupplier = ArrayList::new;
```

## 4. What is the difference between lambda and anonymous class?

**Lambda Expression:**
- Only for functional interfaces
- More concise syntax
- No new class file generated
- Better performance (invokedynamic)
- 'this' refers to enclosing class

**Anonymous Class:**
- Can implement any interface or extend class
- More verbose syntax
- Creates new class file
- Slower performance
- 'this' refers to anonymous class instance

```java
// Anonymous class - verbose
Runnable r1 = new Runnable() {
    public void run() {
        System.out.println(this.getClass()); // Anonymous class
    }
};

// Lambda - concise
Runnable r2 = () -> {
    System.out.println(this.getClass()); // Enclosing class
};
```

## 5. What is Stream API?

Stream API provides a functional approach to process collections of data. It allows you to perform complex data processing operations using a pipeline of operations.

- Functional-style operations on collections
- Lazy evaluation for better performance
- Supports parallel processing
- Immutable - doesn't modify original data
- Pipeline of operations: source → intermediate → terminal

```java
List<String> names = Arrays.asList("John", "Jane", "Bob", "Alice");

// Stream pipeline
List<String> result = names.stream()
    .filter(name -> name.length() > 3)  // Intermediate
    .map(String::toUpperCase)           // Intermediate
    .sorted()                           // Intermediate
    .collect(Collectors.toList());      // Terminal
```

## 6. What is the difference between Collection and Stream?

**Collection:**
- Data structure that stores elements
- Eagerly computed (all elements present)
- Can be modified (add/remove elements)
- External iteration (for loops)
- Reusable multiple times

**Stream:**
- Abstraction for processing data
- Lazily computed (computed on demand)
- Immutable (doesn't modify source)
- Internal iteration (handled by Stream API)
- Single-use only

```java
List<String> collection = Arrays.asList("a", "b", "c");
collection.add("d"); // Modifies collection

Stream<String> stream = collection.stream();
stream.filter(s -> s.length() > 1); // Doesn't modify collection
// stream.filter(...); // Error - stream already used
```

## 7. What are intermediate and terminal operations?

**Intermediate Operations:**
- Transform stream into another stream
- Lazy evaluation (not executed until terminal operation)
- Can be chained together
- Examples: filter(), map(), sorted(), distinct()

**Terminal Operations:**
- Produce final result or side effect
- Trigger execution of entire pipeline
- Cannot be chained
- Examples: collect(), forEach(), reduce(), count()

```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

numbers.stream()
    .filter(n -> n > 2)     // Intermediate - lazy
    .map(n -> n * 2)        // Intermediate - lazy
    .sorted()               // Intermediate - lazy
    .forEach(System.out::println); // Terminal - triggers execution
```

## 8. What is the difference between map() and flatMap()?

**map():**
- One-to-one transformation
- Transforms each element to another element
- Stream<T> → Stream<R>

**flatMap():**
- One-to-many transformation
- Flattens nested structures
- Stream<T> → Stream<R> (where T contains multiple R)

```java
List<String> words = Arrays.asList("Hello", "World");

// map() - one-to-one
List<Integer> lengths = words.stream()
    .map(String::length)        // ["Hello", "World"] → [5, 5]
    .collect(Collectors.toList());

// flatMap() - one-to-many, then flatten
List<String> letters = words.stream()
    .flatMap(word -> Arrays.stream(word.split(""))) // Flatten arrays
    .collect(Collectors.toList()); // [H, e, l, l, o, W, o, r, l, d]
```

## 9. What is Optional class?

Optional is a container class that may or may not contain a value. It helps avoid NullPointerException and makes null handling more explicit and safer.

- Container for potentially null values
- Prevents NullPointerException
- Encourages explicit null handling
- Provides functional-style methods
- Should not be used for fields or parameters

```java
// Creating Optional
Optional<String> optional = Optional.of("Hello");
Optional<String> empty = Optional.empty();
Optional<String> nullable = Optional.ofNullable(getString());

// Using Optional
optional.ifPresent(System.out::println);        // Execute if present
String result = optional.orElse("Default");     // Provide default
String result2 = optional.orElseGet(() -> "Default"); // Lazy default

// Chaining operations
optional.filter(s -> s.length() > 3)
        .map(String::toUpperCase)
        .ifPresent(System.out::println);
```

Optional encourages developers to think about null cases explicitly and provides a fluent API for handling potentially missing values safely.
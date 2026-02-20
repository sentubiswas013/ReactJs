## 6) Java 8+ Features (Streams, Lambda, Optional)

## 60. What are lambda expressions and functional interfaces?

**Answer:**
Lambda expressions are anonymous functions that implement functional interfaces. A functional interface has exactly one abstract method.

**Lambda syntax:** `(parameters) -> expression` or `(parameters) -> { statements; }`

**Example:**
```java
// Functional Interface
@FunctionalInterface
interface Calculator {
    int calculate(int a, int b);
}

// Without lambda
Calculator add = new Calculator() {
    public int calculate(int a, int b) { return a + b; }
};

// With lambda
Calculator multiply = (a, b) -> a * b;
Calculator divide = (a, b) -> { return a / b; };

System.out.println(multiply.calculate(5, 3)); // 15
```

---

## 61. Explain built-in functional interfaces (Predicate, Function, Consumer, Supplier).

**Answer:**
Built-in functional interfaces in java.util.function package for common operations.

- **Predicate<T>:** Takes T, returns boolean (test condition)
- **Function<T,R>:** Takes T, returns R (transform)
- **Consumer<T>:** Takes T, returns void (consume/process)
- **Supplier<T>:** Takes nothing, returns T (supply/generate)

**Example:**
```java
// Predicate - test condition
Predicate<Integer> isEven = n -> n % 2 == 0;
System.out.println(isEven.test(4)); // true

// Function - transform
Function<String, Integer> length = s -> s.length();
System.out.println(length.apply("Hello")); // 5

// Consumer - process
Consumer<String> print = s -> System.out.println(s);
print.accept("Hi"); // Hi

// Supplier - generate
Supplier<Double> random = () -> Math.random();
System.out.println(random.get()); // 0.xyz
```

---

## 62. What is method reference and its types?

**Answer:**
Method reference is shorthand for lambda that calls an existing method using `::` operator.

**Types:** Static, Instance, Constructor, Arbitrary object

**Example:**
```java
List<String> names = Arrays.asList("John", "Jane", "Bob");

// Lambda
names.forEach(name -> System.out.println(name));

// Static method reference
names.forEach(System.out::println);

// Instance method reference
String prefix = "Hello ";
Function<String, String> greet = prefix::concat;
System.out.println(greet.apply("World")); // Hello World

// Constructor reference
Supplier<List<String>> listSupplier = ArrayList::new;

// Arbitrary object method reference
names.stream().map(String::toUpperCase);
```

---

## 63. Difference between intermediate and terminal operations in Stream.

**Answer:**
Intermediate operations return a stream and are lazy (not executed until terminal operation). Terminal operations produce result and close the stream.

**Intermediate:** map, filter, sorted, distinct (chainable)
**Terminal:** collect, forEach, reduce, count (triggers execution)

**Example:**
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

// Intermediate operations (lazy - not executed yet)
Stream<Integer> stream = numbers.stream()
    .filter(n -> n > 2)  // Intermediate
    .map(n -> n * 2);    // Intermediate

// Terminal operation (triggers execution)
List<Integer> result = stream.collect(Collectors.toList()); // Terminal
System.out.println(result); // [6, 8, 10]
```

---

## 64. Explain `map()`, `flatMap()`, `filter()`, `reduce()`.

**Answer:**
Stream operations for transformation, flattening, filtering, and aggregation.

- **map():** Transform each element
- **flatMap():** Transform and flatten nested structures
- **filter():** Select elements matching condition
- **reduce():** Combine elements into single result

**Example:**
```java
List<Integer> nums = Arrays.asList(1, 2, 3, 4);

// map - transform
List<Integer> squared = nums.stream()
    .map(n -> n * n)
    .collect(Collectors.toList()); // [1, 4, 9, 16]

// filter - select
List<Integer> evens = nums.stream()
    .filter(n -> n % 2 == 0)
    .collect(Collectors.toList()); // [2, 4]

// reduce - aggregate
int sum = nums.stream()
    .reduce(0, (a, b) -> a + b); // 10

// flatMap - flatten
List<List<Integer>> nested = Arrays.asList(
    Arrays.asList(1, 2), Arrays.asList(3, 4)
);
List<Integer> flat = nested.stream()
    .flatMap(list -> list.stream())
    .collect(Collectors.toList()); // [1, 2, 3, 4]
```

---

## 65. What is the difference between `map()` and `flatMap()`?

**Answer:**
map() transforms each element to one element (1-to-1). flatMap() transforms each element to a stream and flattens all streams into one (1-to-many).

**map:** Stream<T> → Stream<R>
**flatMap:** Stream<T> → Stream<Stream<R>> → Stream<R>

**Example:**
```java
List<String> words = Arrays.asList("Hello", "World");

// map - 1 to 1 transformation
List<Integer> lengths = words.stream()
    .map(s -> s.length())
    .collect(Collectors.toList()); // [5, 5]

// flatMap - 1 to many, then flatten
List<String> chars = words.stream()
    .flatMap(s -> Arrays.stream(s.split("")))
    .collect(Collectors.toList()); 
// [H, e, l, l, o, W, o, r, l, d]

// map would give Stream<String[]>
// flatMap gives Stream<String>
```

---

## 66. When should you use parallel streams?

**Answer:**
Use parallel streams for large datasets with CPU-intensive operations and independent elements. Avoid for small datasets, I/O operations, or when order matters.

**Use when:** Large data (>10k elements), stateless operations, multi-core CPU
**Avoid when:** Small data, I/O bound, shared mutable state

**Example:**
```java
List<Integer> numbers = IntStream.rangeClosed(1, 1000000)
    .boxed().collect(Collectors.toList());

// Sequential - single thread
long start = System.currentTimeMillis();
long sum1 = numbers.stream()
    .mapToInt(n -> n * n)
    .sum();
System.out.println("Sequential: " + (System.currentTimeMillis() - start));

// Parallel - multiple threads
start = System.currentTimeMillis();
long sum2 = numbers.parallelStream()
    .mapToInt(n -> n * n)
    .sum();
System.out.println("Parallel: " + (System.currentTimeMillis() - start));

// Don't use parallel for small data
List<Integer> small = Arrays.asList(1, 2, 3);
small.stream().forEach(System.out::println); // Better than parallel
```

---

## 67. Explain `Optional` class and its methods.

**Answer:**
Optional is a container that may or may not contain a non-null value, avoiding NullPointerException.

**Key methods:** of(), ofNullable(), isPresent(), ifPresent(), orElse(), orElseGet(), orElseThrow(), map(), filter()

**Example:**
```java
// Creating Optional
Optional<String> name = Optional.of("John"); // NPE if null
Optional<String> nullable = Optional.ofNullable(null); // Safe
Optional<String> empty = Optional.empty();

// Checking and retrieving
if (name.isPresent()) {
    System.out.println(name.get()); // John
}

// Better approach
name.ifPresent(n -> System.out.println(n)); // John

// Default values
String result = nullable.orElse("Default"); // Default
String result2 = nullable.orElseGet(() -> "Computed"); // Computed
String result3 = nullable.orElseThrow(() -> new Exception()); // Throws

// Transform
Optional<Integer> length = name.map(String::length); // Optional[4]
```

---

## 68. Difference between `orElse()` and `orElseGet()`?

**Answer:**
orElse() always evaluates the default value. orElseGet() evaluates lazily only when Optional is empty.

**orElse:** Value always computed (use for constants)
**orElseGet:** Supplier called only if empty (use for expensive operations)

**Example:**
```java
public String getDefault() {
    System.out.println("Computing default...");
    return "Default";
}

Optional<String> name = Optional.of("John");

// orElse - always executes
String result1 = name.orElse(getDefault()); 
// Prints: "Computing default..." even though name exists
// Returns: "John"

// orElseGet - lazy execution
String result2 = name.orElseGet(() -> getDefault());
// Doesn't print anything
// Returns: "John"

Optional<String> empty = Optional.empty();

// Both execute when empty
String result3 = empty.orElse(getDefault()); // Prints, returns "Default"
String result4 = empty.orElseGet(() -> getDefault()); // Prints, returns "Default"
```

---

## 69. How do you handle exceptions in streams?

**Answer:**
Wrap checked exceptions in unchecked exceptions or use helper methods, since stream lambdas don't allow checked exceptions.

**Approaches:** Try-catch in lambda, wrapper method, custom functional interface

**Example:**
```java
List<String> numbers = Arrays.asList("1", "2", "abc", "4");

// Approach 1: Try-catch in lambda
List<Integer> result1 = numbers.stream()
    .map(s -> {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return 0; // Default value
        }
    })
    .collect(Collectors.toList()); // [1, 2, 0, 4]

// Approach 2: Wrapper method
public static Integer parseOrDefault(String s) {
    try {
        return Integer.parseInt(s);
    } catch (NumberFormatException e) {
        return 0;
    }
}

List<Integer> result2 = numbers.stream()
    .map(s -> parseOrDefault(s))
    .collect(Collectors.toList());

// Approach 3: Filter invalid
List<Integer> result3 = numbers.stream()
    .filter(s -> s.matches("\\d+"))
    .map(Integer::parseInt)
    .collect(Collectors.toList()); // [1, 2, 4]
```

---

## 70. What is the `Collectors` utility class?

**Answer:**
Collectors provides reduction operations to accumulate stream elements into collections, strings, or other results.

**Common collectors:** toList(), toSet(), toMap(), joining(), groupingBy(), partitioningBy(), counting(), summingInt()

**Example:**
```java
List<String> names = Arrays.asList("John", "Jane", "Bob", "Alice");

// toList
List<String> list = names.stream().collect(Collectors.toList());

// toSet
Set<String> set = names.stream().collect(Collectors.toSet());

// joining
String joined = names.stream().collect(Collectors.joining(", ")); 
// "John, Jane, Bob, Alice"

// counting
long count = names.stream().collect(Collectors.counting()); // 4

// toMap
Map<String, Integer> map = names.stream()
    .collect(Collectors.toMap(
        name -> name,           // key
        name -> name.length()   // value
    )); // {John=4, Jane=4, Bob=3, Alice=5}

// summingInt
List<Integer> nums = Arrays.asList(1, 2, 3, 4);
int sum = nums.stream().collect(Collectors.summingInt(n -> n)); // 10
```

---

## 71. How do you group and partition data using streams?

**Answer:**
groupingBy() groups elements by classifier function into Map. partitioningBy() splits into two groups (true/false) based on predicate.

**groupingBy:** Multiple groups by key
**partitioningBy:** Two groups (true/false)

**Example:**
```java
class Person {
    String name;
    int age;
    Person(String name, int age) { this.name = name; this.age = age; }
    public String getName() { return name; }
    public int getAge() { return age; }
}

List<Person> people = Arrays.asList(
    new Person("John", 25),
    new Person("Jane", 30),
    new Person("Bob", 25),
    new Person("Alice", 30)
);

// groupingBy - group by age
Map<Integer, List<Person>> byAge = people.stream()
    .collect(Collectors.groupingBy(Person::getAge));
// {25=[John, Bob], 30=[Jane, Alice]}

// groupingBy with counting
Map<Integer, Long> ageCount = people.stream()
    .collect(Collectors.groupingBy(Person::getAge, Collectors.counting()));
// {25=2, 30=2}

// partitioningBy - split by condition
Map<Boolean, List<Person>> partitioned = people.stream()
    .collect(Collectors.partitioningBy(p -> p.getAge() >= 30));
// {false=[John, Bob], true=[Jane, Alice]}
```

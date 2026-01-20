# 🔹 Lambda Expressions and Streams

## Lambda Expressions

### 196. What are lambda expressions?

**Lambda expressions** are **anonymous functions** introduced in Java 8:

#### Key Characteristics:
- **Anonymous**: No name required
- **Functional**: Can be passed as arguments
- **Concise**: Reduce boilerplate code
- **Functional programming**: Enable functional style

#### Basic Example:
```java
// Before Java 8 - Anonymous class
Runnable oldWay = new Runnable() {
    @Override
    public void run() {
        System.out.println("Hello World");
    }
};

// Java 8+ - Lambda expression
Runnable newWay = () -> System.out.println("Hello World");
```

#### Benefits:
- **Cleaner code**: Less verbose than anonymous classes
- **Better readability**: Focus on logic, not syntax
- **Functional programming**: Enables functional style operations
- **Stream API integration**: Perfect for collections processing

#### Use Cases:
```java
// Event handling
button.setOnAction(e -> System.out.println("Button clicked"));

// Collection processing
list.forEach(item -> System.out.println(item));

// Filtering
list.stream().filter(x -> x > 10).collect(toList());
```

### 197. What is the syntax of lambda expressions?

Lambda expressions follow the pattern: **parameters -> body**

#### Basic Syntax Forms:

##### 1. No Parameters:
```java
// No parameters - use empty parentheses
Runnable task = () -> System.out.println("Task executed");
Supplier<String> supplier = () -> "Hello World";
```

##### 2. Single Parameter:
```java
// Single parameter - parentheses optional
Consumer<String> printer = s -> System.out.println(s);
Consumer<String> printer2 = (s) -> System.out.println(s); // Also valid

Function<Integer, Integer> square = x -> x * x;
```

##### 3. Multiple Parameters:
```java
// Multiple parameters - parentheses required
BinaryOperator<Integer> add = (a, b) -> a + b;
Comparator<String> comparator = (s1, s2) -> s1.compareTo(s2);
```

##### 4. Type Declaration:
```java
// Explicit type declaration (usually inferred)
BinaryOperator<Integer> multiply = (Integer a, Integer b) -> a * b;
```

##### 5. Block Body:
```java
// Multiple statements - use braces and return
Function<String, String> processor = s -> {
    String trimmed = s.trim();
    String upper = trimmed.toUpperCase();
    return upper;
};

Consumer<List<String>> listProcessor = list -> {
    list.sort(String::compareTo);
    list.forEach(System.out::println);
};
```

#### Syntax Rules:
- **Single expression**: No braces or return needed
- **Multiple statements**: Use braces and explicit return
- **Type inference**: Compiler infers parameter types
- **Final variables**: Can access effectively final variables

### 198. What are functional interfaces?

**Functional interfaces** have **exactly one abstract method**:

#### Definition:
```java
@FunctionalInterface
public interface Calculator {
    int calculate(int a, int b); // Single abstract method
    
    // Default methods allowed
    default int add(int a, int b) {
        return calculate(a, b);
    }
    
    // Static methods allowed
    static int multiply(int a, int b) {
        return a * b;
    }
}
```

#### Usage with Lambda:
```java
// Lambda implementation
Calculator adder = (a, b) -> a + b;
Calculator subtractor = (a, b) -> a - b;

int result1 = adder.calculate(5, 3);    // 8
int result2 = subtractor.calculate(5, 3); // 2
```

#### Built-in Examples:
```java
// Runnable - no parameters, no return
Runnable task = () -> System.out.println("Running");

// Comparator - two parameters, returns int
Comparator<String> comp = (s1, s2) -> s1.length() - s2.length();

// ActionListener - one parameter, no return
ActionListener listener = e -> System.out.println("Action performed");
```

#### @FunctionalInterface Annotation:
```java
@FunctionalInterface
public interface MyFunction<T, R> {
    R apply(T input);
    
    // This would cause compile error - only one abstract method allowed
    // void anotherMethod();
}
```

### 199. What are method references?

**Method references** are **shorthand for lambda expressions** that call existing methods:

#### Types of Method References:

##### 1. Static Method Reference:
```java
// Lambda
Function<String, Integer> parser1 = s -> Integer.parseInt(s);

// Method reference
Function<String, Integer> parser2 = Integer::parseInt;

// Usage
List<String> numbers = Arrays.asList("1", "2", "3");
List<Integer> integers = numbers.stream()
    .map(Integer::parseInt)
    .collect(toList());
```

##### 2. Instance Method Reference:
```java
String prefix = "Hello ";

// Lambda
Function<String, String> greeter1 = s -> prefix.concat(s);

// Method reference
Function<String, String> greeter2 = prefix::concat;

// Usage
System.out.println(greeter2.apply("World")); // Hello World
```

##### 3. Arbitrary Object Method Reference:
```java
// Lambda
Comparator<String> comp1 = (s1, s2) -> s1.compareToIgnoreCase(s2);

// Method reference
Comparator<String> comp2 = String::compareToIgnoreCase;

// Usage
List<String> names = Arrays.asList("John", "alice", "Bob");
names.sort(String::compareToIgnoreCase);
```

##### 4. Constructor Reference:
```java
// Lambda
Supplier<List<String>> listCreator1 = () -> new ArrayList<>();

// Constructor reference
Supplier<List<String>> listCreator2 = ArrayList::new;

// With parameters
Function<Integer, List<String>> sizedListCreator = ArrayList::new;
```

### 200. What are constructor references?

**Constructor references** create new instances using the **:: operator**:

#### Basic Constructor Reference:
```java
// Default constructor
Supplier<StringBuilder> sbSupplier = StringBuilder::new;
StringBuilder sb = sbSupplier.get();

// Parameterized constructor
Function<String, StringBuilder> sbWithString = StringBuilder::new;
StringBuilder sb2 = sbWithString.apply("Hello");
```

#### Array Constructor References:
```java
// Array constructor
IntFunction<String[]> arrayCreator = String[]::new;
String[] array = arrayCreator.apply(5); // Creates String[5]

// Usage in streams
List<String> list = Arrays.asList("a", "b", "c");
String[] array2 = list.stream().toArray(String[]::new);
```

#### Complex Examples:
```java
public class Person {
    private String name;
    private int age;
    
    public Person() {}
    
    public Person(String name) {
        this.name = name;
    }
    
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

// Different constructor references
Supplier<Person> defaultPerson = Person::new;
Function<String, Person> namedPerson = Person::new;
BiFunction<String, Integer, Person> fullPerson = Person::new;

// Usage
Person p1 = defaultPerson.get();
Person p2 = namedPerson.apply("John");
Person p3 = fullPerson.apply("Alice", 25);
```

#### Stream Integration:
```java
List<String> names = Arrays.asList("John", "Alice", "Bob");

// Create Person objects from names
List<Person> people = names.stream()
    .map(Person::new)  // Constructor reference
    .collect(toList());
```

### 201. What is the difference between lambda and anonymous class?

#### Key Differences:

| Aspect | Lambda Expression | Anonymous Class |
|--------|-------------------|-----------------|
| **Syntax** | Concise: `x -> x * 2` | Verbose: `new Function() {...}` |
| **Compilation** | invokedynamic | Separate .class file |
| **Performance** | Faster (no class loading) | Slower (class loading overhead) |
| **Scope** | Lexical scope | Own scope |
| **Interface Type** | Functional interfaces only | Any interface/abstract class |
| **this reference** | Refers to enclosing instance | Refers to anonymous instance |

#### Syntax Comparison:
```java
// Anonymous class
Comparator<String> comp1 = new Comparator<String>() {
    @Override
    public int compare(String s1, String s2) {
        return s1.length() - s2.length();
    }
};

// Lambda expression
Comparator<String> comp2 = (s1, s2) -> s1.length() - s2.length();
```

#### Scope Differences:
```java
public class ScopeExample {
    private String field = "enclosing";
    
    public void demonstrateScope() {
        String local = "local";
        
        // Anonymous class - has its own scope
        Runnable anonymous = new Runnable() {
            private String field = "anonymous"; // Shadows enclosing field
            
            @Override
            public void run() {
                System.out.println(this.field);     // "anonymous"
                System.out.println(ScopeExample.this.field); // "enclosing"
                // System.out.println(local); // Must be final/effectively final
            }
        };
        
        // Lambda - lexical scope
        Runnable lambda = () -> {
            System.out.println(this.field); // "enclosing" - refers to ScopeExample
            System.out.println(local);      // "local" - can access local variables
        };
    }
}
```

#### Limitations:
```java
// Lambda - only functional interfaces
Runnable lambda = () -> System.out.println("Lambda");

// Anonymous class - can extend classes
Thread thread = new Thread() {
    @Override
    public void run() {
        System.out.println("Anonymous class extending Thread");
    }
};
```

### 202. What are the built-in functional interfaces?

Java provides **functional interfaces** in `java.util.function` package:

#### Core Functional Interfaces:

##### 1. Predicate<T>:
```java
Predicate<String> isEmpty = String::isEmpty;
Predicate<Integer> isPositive = x -> x > 0;

// Usage
List<String> words = Arrays.asList("hello", "", "world", "");
List<String> nonEmpty = words.stream()
    .filter(isEmpty.negate())
    .collect(toList());
```

##### 2. Function<T, R>:
```java
Function<String, Integer> length = String::length;
Function<Integer, String> toString = Object::toString;

// Usage
List<String> words = Arrays.asList("hello", "world");
List<Integer> lengths = words.stream()
    .map(length)
    .collect(toList());
```

##### 3. Consumer<T>:
```java
Consumer<String> printer = System.out::println;
Consumer<List<String>> sorter = list -> Collections.sort(list);

// Usage
List<String> names = Arrays.asList("John", "Alice", "Bob");
names.forEach(printer);
```

##### 4. Supplier<T>:
```java
Supplier<String> randomUUID = () -> UUID.randomUUID().toString();
Supplier<List<String>> listSupplier = ArrayList::new;

// Usage
String id = randomUUID.get();
List<String> newList = listSupplier.get();
```

#### Specialized Interfaces:

##### Primitive Specializations:
```java
// Avoid boxing/unboxing
IntPredicate isEven = x -> x % 2 == 0;
IntFunction<String> intToString = Integer::toString;
IntConsumer printer = System.out::println;
IntSupplier random = () -> (int) (Math.random() * 100);
```

##### Binary Operations:
```java
BinaryOperator<Integer> add = Integer::sum;
BinaryOperator<String> concat = String::concat;
UnaryOperator<String> toUpper = String::toUpperCase;

// Usage
int sum = add.apply(5, 3);        // 8
String result = concat.apply("Hello", " World"); // "Hello World"
```

### 203. What is Predicate interface?

**Predicate<T>** tests a condition and returns boolean:

#### Basic Usage:
```java
Predicate<String> isLong = s -> s.length() > 5;
Predicate<Integer> isPositive = x -> x > 0;
Predicate<Person> isAdult = p -> p.getAge() >= 18;

// Testing
boolean result = isLong.test("Hello World"); // true
```

#### Combining Predicates:
```java
Predicate<String> isLong = s -> s.length() > 5;
Predicate<String> startsWithA = s -> s.startsWith("A");

// Logical operations
Predicate<String> longAndStartsWithA = isLong.and(startsWithA);
Predicate<String> longOrStartsWithA = isLong.or(startsWithA);
Predicate<String> notLong = isLong.negate();

// Usage
boolean test1 = longAndStartsWithA.test("Application"); // true
boolean test2 = longOrStartsWithA.test("App");         // true
boolean test3 = notLong.test("Hi");                    // true
```

#### Stream Integration:
```java
List<String> words = Arrays.asList("Apple", "Banana", "Cat", "Application");

// Filter using predicates
List<String> longWords = words.stream()
    .filter(isLong)
    .collect(toList());

List<String> aWords = words.stream()
    .filter(startsWithA)
    .collect(toList());

// Complex filtering
List<String> filtered = words.stream()
    .filter(isLong.and(startsWithA))
    .collect(toList());
```

#### Practical Examples:
```java
public class PersonFilter {
    public static List<Person> filterPeople(List<Person> people, Predicate<Person> criteria) {
        return people.stream()
            .filter(criteria)
            .collect(toList());
    }
    
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
            new Person("John", 25),
            new Person("Alice", 17),
            new Person("Bob", 30)
        );
        
        // Different filtering criteria
        Predicate<Person> isAdult = p -> p.getAge() >= 18;
        Predicate<Person> nameStartsWithA = p -> p.getName().startsWith("A");
        
        List<Person> adults = filterPeople(people, isAdult);
        List<Person> aNames = filterPeople(people, nameStartsWithA);
        List<Person> adultANames = filterPeople(people, isAdult.and(nameStartsWithA));
    }
}
```

### 204. What is Function interface?

**Function<T, R>** transforms input of type T to output of type R:

#### Basic Usage:
```java
Function<String, Integer> length = String::length;
Function<Integer, String> toString = Object::toString;
Function<String, String> toUpper = String::toUpperCase;

// Application
int len = length.apply("Hello");      // 5
String str = toString.apply(42);      // "42"
String upper = toUpper.apply("hello"); // "HELLO"
```

#### Function Composition:
```java
Function<String, String> trim = String::trim;
Function<String, String> toUpper = String::toUpperCase;
Function<String, Integer> length = String::length;

// Compose functions
Function<String, String> trimAndUpper = trim.andThen(toUpper);
Function<String, Integer> processAndLength = trim.andThen(toUpper).andThen(length);

// Usage
String result1 = trimAndUpper.apply("  hello  ");        // "HELLO"
int result2 = processAndLength.apply("  hello world  "); // 11
```

#### Stream Transformations:
```java
List<String> words = Arrays.asList("hello", "world", "java");

// Transform using Function
List<Integer> lengths = words.stream()
    .map(String::length)
    .collect(toList());

List<String> upperWords = words.stream()
    .map(String::toUpperCase)
    .collect(toList());

// Chain transformations
List<String> processed = words.stream()
    .map(String::trim)
    .map(String::toUpperCase)
    .map(s -> "Processed: " + s)
    .collect(toList());
```

#### Complex Examples:
```java
public class DataProcessor {
    // Function to parse and validate
    Function<String, Optional<Integer>> parseInteger = s -> {
        try {
            return Optional.of(Integer.parseInt(s.trim()));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    };
    
    // Function to format currency
    Function<Double, String> formatCurrency = amount -> 
        String.format("$%.2f", amount);
    
    // Function composition
    Function<String, Optional<String>> parseAndFormat = 
        parseInteger.andThen(opt -> opt.map(i -> formatCurrency.apply(i.doubleValue())));
}
```

### 205. What is Consumer interface?

**Consumer<T>** accepts input and performs side effects (returns void):

#### Basic Usage:
```java
Consumer<String> printer = System.out::println;
Consumer<List<String>> sorter = Collections::sort;
Consumer<StringBuilder> appender = sb -> sb.append(" - processed");

// Usage
printer.accept("Hello World");

List<String> list = Arrays.asList("c", "a", "b");
sorter.accept(list); // Sorts the list in-place
```

#### Consumer Chaining:
```java
Consumer<String> print = System.out::println;
Consumer<String> log = s -> logger.info("Processing: " + s);

// Chain consumers
Consumer<String> printAndLog = print.andThen(log);

// Usage
printAndLog.accept("Important message");
// Prints to console AND logs
```

#### Stream Operations:
```java
List<String> names = Arrays.asList("John", "Alice", "Bob");

// forEach with Consumer
names.forEach(System.out::println);
names.forEach(name -> System.out.println("Hello, " + name));

// Parallel processing
names.parallelStream()
    .forEach(name -> processName(name));
```

#### Practical Examples:
```java
public class FileProcessor {
    // Consumer for file operations
    Consumer<Path> deleteFile = path -> {
        try {
            Files.delete(path);
            System.out.println("Deleted: " + path);
        } catch (IOException e) {
            System.err.println("Failed to delete: " + path);
        }
    };
    
    Consumer<Path> backupFile = path -> {
        try {
            Path backup = Paths.get(path + ".backup");
            Files.copy(path, backup);
            System.out.println("Backed up: " + path);
        } catch (IOException e) {
            System.err.println("Failed to backup: " + path);
        }
    };
    
    // Chain operations
    Consumer<Path> backupAndDelete = backupFile.andThen(deleteFile);
    
    public void processFiles(List<Path> files) {
        files.forEach(backupAndDelete);
    }
}
```

#### BiConsumer for Two Parameters:
```java
BiConsumer<String, Integer> printWithIndex = (str, index) -> 
    System.out.println(index + ": " + str);

// Usage
List<String> items = Arrays.asList("apple", "banana", "cherry");
IntStream.range(0, items.size())
    .forEach(i -> printWithIndex.accept(items.get(i), i));
```

## Best Practices

### 1. Keep Lambdas Simple:
```java
// Good - simple and readable
list.stream().filter(s -> s.length() > 5).collect(toList());

// Avoid - complex logic in lambda
list.stream().filter(s -> {
    // 20 lines of complex logic
    return someComplexCondition;
}).collect(toList());
```

### 2. Use Method References When Possible:
```java
// Prefer method reference
list.forEach(System.out::println);

// Over lambda
list.forEach(item -> System.out.println(item));
```

### 3. Combine Functional Interfaces:
```java
Predicate<String> isLong = s -> s.length() > 5;
Predicate<String> startsWithA = s -> s.startsWith("A");
Predicate<String> combined = isLong.and(startsWithA);
```

### 4. Use Appropriate Functional Interface:
```java
// Use specialized interfaces for primitives
IntPredicate isEven = x -> x % 2 == 0;  // Better than Predicate<Integer>
IntFunction<String> toString = Integer::toString;  // Better than Function<Integer, String>
```

## Key Takeaways

1. **Lambdas enable functional programming** in Java with concise syntax
2. **Functional interfaces** have exactly one abstract method
3. **Method references** provide shorthand for simple lambda expressions
4. **Constructor references** create objects using :: operator
5. **Lambdas vs anonymous classes** - different scope and compilation
6. **Built-in functional interfaces** cover common use cases
7. **Predicate, Function, Consumer** are the most commonly used interfaces
8. **Combine and chain** functional interfaces for complex operations
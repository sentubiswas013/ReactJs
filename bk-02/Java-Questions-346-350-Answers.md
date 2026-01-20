# 🔹 Modern Java Features

## Modern Java Features - Questions & Answers

### 346. What are the new features in Java 8?

**Answer:** Java 8 introduced lambda expressions for functional programming, Stream API for data processing, Optional class to handle null values, method references for cleaner code, default and static methods in interfaces, new Date/Time API, and functional interfaces like Predicate and Function.

**Key Java 8 Features:**

**1. Lambda Expressions:**
```java
// Before Java 8
List<String> names = Arrays.asList("John", "Jane", "Bob");
Collections.sort(names, new Comparator<String>() {
    @Override
    public int compare(String a, String b) {
        return a.compareTo(b);
    }
});

// Java 8 with Lambda
Collections.sort(names, (a, b) -> a.compareTo(b));
// Or even simpler
Collections.sort(names, String::compareTo);
```

**2. Stream API:**
```java
List<String> names = Arrays.asList("John", "Jane", "Bob", "Alice");

// Filter and collect
List<String> filteredNames = names.stream()
    .filter(name -> name.startsWith("J"))
    .collect(Collectors.toList());

// Map and reduce
int totalLength = names.stream()
    .mapToInt(String::length)
    .sum();
```

**3. Optional Class:**
```java
// Avoid null pointer exceptions
Optional<String> optional = Optional.ofNullable(getName());

// Safe operations
String result = optional
    .filter(name -> name.length() > 3)
    .map(String::toUpperCase)
    .orElse("DEFAULT");
```

**4. Method References:**
```java
// Static method reference
Function<String, Integer> parser = Integer::parseInt;

// Instance method reference
Function<String, String> upperCase = String::toUpperCase;

// Constructor reference
Supplier<List<String>> listSupplier = ArrayList::new;
```

**5. Default Methods in Interfaces:**
```java
interface Vehicle {
    void start();
    
    // Default method
    default void stop() {
        System.out.println("Vehicle stopped");
    }
    
    // Static method
    static void honk() {
        System.out.println("Beep!");
    }
}
```

**6. New Date/Time API:**
```java
// Modern date/time handling
LocalDate today = LocalDate.now();
LocalDateTime now = LocalDateTime.now();
ZonedDateTime utc = ZonedDateTime.now(ZoneOffset.UTC);

// Date calculations
LocalDate nextWeek = today.plusWeeks(1);
Period period = Period.between(today, nextWeek);
```

---

### 347. What are the new features in Java 11?

**Answer:** Java 11 added local variable type inference with var in lambda parameters, new String methods like isBlank() and lines(), HTTP Client API for modern HTTP requests, Flight Recorder for low-overhead profiling, removed Java EE modules, and introduced single-file source code programs.

**Key Java 11 Features:**

**1. Local Variable Type Inference (var) in Lambda:**
```java
// Java 11 - var in lambda parameters
var names = List.of("John", "Jane", "Bob");
names.forEach((var name) -> System.out.println(name.toUpperCase()));

// Useful with annotations
names.forEach((@NonNull var name) -> process(name));
```

**2. New String Methods:**
```java
String text = "  Hello World  ";

// Check if blank (empty or whitespace)
boolean blank = text.isBlank(); // false
boolean empty = "".isBlank();   // true

// Strip whitespace (Unicode-aware)
String stripped = text.strip(); // "Hello World"

// Repeat string
String repeated = "Java".repeat(3); // "JavaJavaJava"

// Split into lines
String multiline = "Line 1\nLine 2\nLine 3";
List<String> lines = multiline.lines().collect(Collectors.toList());
```

**3. HTTP Client API:**
```java
// Modern HTTP client
HttpClient client = HttpClient.newHttpClient();

HttpRequest request = HttpRequest.newBuilder()
    .uri(URI.create("https://api.example.com/users"))
    .header("Content-Type", "application/json")
    .GET()
    .build();

// Synchronous
HttpResponse<String> response = client.send(request, 
    HttpResponse.BodyHandlers.ofString());

// Asynchronous
CompletableFuture<HttpResponse<String>> asyncResponse = client.sendAsync(request,
    HttpResponse.BodyHandlers.ofString());
```

**4. Single-File Source Code Programs:**
```bash
# Run Java file directly without compilation
java HelloWorld.java

# No need for javac HelloWorld.java first
```

**5. Flight Recorder (JFR):**
```bash
# Enable JFR (now free in OpenJDK)
java -XX:+FlightRecorder 
     -XX:StartFlightRecording=duration=60s,filename=app.jfr 
     MyApplication
```

**6. Removed Features:**
- Java EE modules removed (need separate dependencies)
- CORBA modules removed
- JavaFX removed from JDK (separate download)

---

### 348. What are the new features in Java 17?

**Answer:** Java 17 brought sealed classes for restricted inheritance, pattern matching for instanceof, text blocks for multiline strings, records for immutable data classes, switch expressions with yield, helpful NullPointerExceptions with detailed messages, and ZGC and Shenandoah garbage collectors.

**Key Java 17 Features:**

**1. Sealed Classes:**
```java
// Restrict which classes can extend/implement
public sealed class Shape 
    permits Circle, Rectangle, Triangle {
}

public final class Circle extends Shape {
    private final double radius;
    
    public Circle(double radius) {
        this.radius = radius;
    }
}

public non-sealed class Rectangle extends Shape {
    // Can be extended by other classes
}
```

**2. Pattern Matching for instanceof:**
```java
// Before Java 17
if (obj instanceof String) {
    String str = (String) obj;
    System.out.println(str.toUpperCase());
}

// Java 17 - pattern matching
if (obj instanceof String str) {
    System.out.println(str.toUpperCase());
}

// With additional conditions
if (obj instanceof String str && str.length() > 5) {
    System.out.println("Long string: " + str);
}
```

**3. Text Blocks:**
```java
// Multiline strings without escape characters
String json = """
    {
        "name": "John Doe",
        "age": 30,
        "email": "john@example.com"
    }
    """;

String sql = """
    SELECT u.name, u.email, o.total
    FROM users u
    JOIN orders o ON u.id = o.user_id
    WHERE u.active = true
    """;
```

**4. Records:**
```java
// Immutable data classes
public record Person(String name, int age, String email) {
    // Compact constructor for validation
    public Person {
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative");
        }
    }
    
    // Additional methods
    public boolean isAdult() {
        return age >= 18;
    }
}

// Usage
Person person = new Person("John", 30, "john@example.com");
System.out.println(person.name()); // Auto-generated accessor
```

**5. Switch Expressions:**
```java
// Switch as expression with yield
String dayType = switch (dayOfWeek) {
    case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> "Weekday";
    case SATURDAY, SUNDAY -> "Weekend";
};

// With yield for complex logic
int result = switch (operation) {
    case "ADD" -> {
        System.out.println("Adding numbers");
        yield a + b;
    }
    case "MULTIPLY" -> {
        System.out.println("Multiplying numbers");
        yield a * b;
    }
    default -> throw new IllegalArgumentException("Unknown operation");
};
```

**6. Enhanced NullPointerException:**
```java
// More helpful NPE messages
String name = person.getAddress().getStreet().getName();
// Before: NullPointerException
// Java 17: Cannot invoke "Address.getStreet()" because the return value of "Person.getAddress()" is null
```

---

### 349. What are the new features in Java 21?

**Answer:** Java 21 introduced virtual threads for lightweight concurrency, pattern matching for switch statements, sequenced collections with defined order, string templates for safe string interpolation, unnamed patterns and variables, and structured concurrency for better async programming.

**Key Java 21 Features:**

**1. Virtual Threads:**
```java
// Lightweight threads for high concurrency
try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
    // Create thousands of virtual threads efficiently
    for (int i = 0; i < 10_000; i++) {
        executor.submit(() -> {
            // Simulate I/O operation
            try {
                Thread.sleep(1000);
                System.out.println("Task completed by " + Thread.currentThread());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
    }
}

// Create virtual thread directly
Thread virtualThread = Thread.ofVirtual().start(() -> {
    System.out.println("Running in virtual thread");
});
```

**2. Pattern Matching for Switch:**
```java
// Pattern matching with switch
public String processObject(Object obj) {
    return switch (obj) {
        case String s -> "String: " + s.toUpperCase();
        case Integer i -> "Integer: " + (i * 2);
        case Person(var name, var age, var email) -> 
            "Person: " + name + " (" + age + ")";
        case null -> "Null value";
        default -> "Unknown type: " + obj.getClass().getSimpleName();
    };
}

// Guard conditions
public String categorizeNumber(Object obj) {
    return switch (obj) {
        case Integer i when i > 0 -> "Positive integer";
        case Integer i when i < 0 -> "Negative integer";
        case Integer i -> "Zero";
        default -> "Not an integer";
    };
}
```

**3. Sequenced Collections:**
```java
// Collections with defined encounter order
SequencedCollection<String> sequenced = new ArrayList<>();
sequenced.addFirst("first");
sequenced.addLast("last");

String first = sequenced.getFirst();
String last = sequenced.getLast();

// Reverse view
SequencedCollection<String> reversed = sequenced.reversed();
```

**4. String Templates (Preview):**
```java
// Safe string interpolation
String name = "John";
int age = 30;

// String template with STR processor
String message = STR."Hello, \{name}! You are \{age} years old.";

// Custom processors for validation/formatting
String sql = SQL."SELECT * FROM users WHERE name = \{name} AND age = \{age}";
```

**5. Unnamed Patterns and Variables:**
```java
// Unnamed variables with underscore
switch (obj) {
    case Point(var x, var _) -> processX(x); // Don't care about y
    case Circle(var _, var radius) -> processRadius(radius); // Don't care about center
}

// In catch blocks
try {
    riskyOperation();
} catch (IOException _) {
    // Don't need the exception variable
    handleIOError();
}
```

**6. Structured Concurrency (Preview):**
```java
// Better async programming
try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
    Future<String> user = scope.fork(() -> fetchUser(userId));
    Future<List<Order>> orders = scope.fork(() -> fetchOrders(userId));
    
    scope.join();           // Wait for all tasks
    scope.throwIfFailed();  // Propagate any failures
    
    // Both tasks completed successfully
    return new UserProfile(user.resultNow(), orders.resultNow());
}
```

---

### 350. What is the Java release cycle and LTS versions?

**Answer:** Java follows a 6-month release cycle since Java 9, with new versions released in March and September. LTS (Long Term Support) versions are released every 3 years: Java 8, 11, 17, and 21 are LTS versions with extended support, while non-LTS versions have shorter support periods.

**Java Release Timeline:**

**Release Schedule:**
```
Java 8 (LTS)    - March 2014
Java 9          - September 2017
Java 10         - March 2018
Java 11 (LTS)   - September 2018
Java 12         - March 2019
Java 13         - September 2019
Java 14         - March 2020
Java 15         - September 2020
Java 16         - March 2021
Java 17 (LTS)   - September 2021
Java 18         - March 2022
Java 19         - September 2022
Java 20         - March 2023
Java 21 (LTS)   - September 2023
Java 22         - March 2024
Java 23         - September 2024
Java 25 (LTS)   - September 2025 (planned)
```

**LTS vs Non-LTS Comparison:**

| Aspect | LTS Versions | Non-LTS Versions |
|--------|--------------|------------------|
| **Support Duration** | 8+ years | 6 months |
| **Enterprise Use** | Recommended | Limited |
| **Stability** | High | Good |
| **Feature Updates** | Backported security fixes | New features |
| **Examples** | Java 8, 11, 17, 21 | Java 9, 10, 12-16, 18-20 |

**LTS Version Characteristics:**

**Java 8 LTS (2014-2030+):**
- Most widely adopted
- Extended support until 2030+
- Lambda expressions, Stream API

**Java 11 LTS (2018-2026+):**
- First LTS after new release cycle
- HTTP Client, var in lambdas
- Removed Java EE modules

**Java 17 LTS (2021-2029+):**
- Current recommended LTS
- Sealed classes, pattern matching
- Text blocks, records

**Java 21 LTS (2023-2031+):**
- Latest LTS version
- Virtual threads, pattern matching for switch
- String templates (preview)

**Migration Strategy:**
```java
// Typical enterprise adoption pattern
Java 8 LTS (2014) → Java 11 LTS (2018-2020) → Java 17 LTS (2021-2023) → Java 21 LTS (2023+)

// Skip non-LTS versions for production
Java 17 LTS → Skip Java 18, 19, 20 → Java 21 LTS
```

**Choosing Java Version:**

**For New Projects:**
- **Java 21 LTS:** Latest features, long-term support
- **Java 17 LTS:** Stable, widely supported

**For Existing Projects:**
- **Java 8:** Legacy systems, gradual migration
- **Java 11:** Minimum modern version
- **Java 17:** Balanced features and stability

**Version Support Timeline:**
```
Java 8:  2014 ────────────────────────── 2030+ (16+ years)
Java 11: 2018 ──────────── 2026+ (8+ years)
Java 17: 2021 ──────────── 2029+ (8+ years)  
Java 21: 2023 ──────────── 2031+ (8+ years)
```

---

## Summary

Modern Java has evolved significantly with regular releases and LTS versions:

**Major Evolution:**
- **Java 8:** Functional programming foundation (lambdas, streams)
- **Java 11:** Modern APIs and tooling improvements
- **Java 17:** Enhanced language features and performance
- **Java 21:** Advanced concurrency and pattern matching

**Key Trends:**
- **Functional Programming:** Lambdas, streams, method references
- **Pattern Matching:** Enhanced instanceof, switch expressions
- **Concurrency:** Virtual threads, structured concurrency
- **Developer Experience:** Records, text blocks, var keyword
- **Performance:** Improved GC, JIT optimizations

**LTS Strategy:**
- **6-month releases:** Regular feature delivery
- **3-year LTS cycle:** Enterprise stability
- **Extended support:** 8+ years for LTS versions
- **Migration path:** LTS to LTS upgrades recommended

**Adoption Recommendations:**
- **New projects:** Use latest LTS (Java 21)
- **Enterprise:** Stick to LTS versions
- **Migration:** Plan LTS to LTS upgrades
- **Experimentation:** Try non-LTS for new features

**Future Outlook:**
- **Project Loom:** Virtual threads and structured concurrency
- **Project Amber:** Language enhancements and pattern matching
- **Project Panama:** Foreign function and memory API
- **Project Valhalla:** Value types and specialized generics
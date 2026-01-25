# 21. Modern Java Features 

## 1. What are the new features in Java 8?

Java 8 was a major release that introduced functional programming features and significantly changed how Java code is written. It's one of the most important Java releases.

**Major Features:**
- **Lambda Expressions:** Anonymous functions for functional programming
- **Stream API:** Functional-style operations on collections
- **Optional Class:** Better null handling
- **Default Methods:** Interface methods with implementation
- **Method References:** Shorthand for lambda expressions
- **Date/Time API:** New java.time package
- **Nashorn JavaScript Engine:** JavaScript execution in JVM

```java
// Lambda expressions
List<String> names = Arrays.asList("John", "Jane", "Bob");
names.forEach(name -> System.out.println(name));

// Stream API
List<String> filtered = names.stream()
    .filter(name -> name.length() > 3)
    .collect(Collectors.toList());

// Optional
Optional<String> optional = Optional.ofNullable(getString());
optional.ifPresent(System.out::println);

// Default methods in interfaces
interface Drawable {
    void draw();
    default void print() { System.out.println("Printing..."); }
}
```

## 2. What are the new features in Java 11?

Java 11 is an LTS (Long Term Support) release that introduced several useful features and improvements, particularly for modern development practices.

**Key Features:**
- **Local Variable Type Inference (var):** Enhanced for lambda parameters
- **HTTP Client API:** Built-in HTTP client
- **String Methods:** isBlank(), lines(), strip(), repeat()
- **Files Methods:** readString(), writeString()
- **Collection.toArray():** Enhanced method
- **Nest-Based Access Control:** Better inner class access
- **Flight Recorder:** Low-overhead profiling

```java
// var in lambda parameters
List<String> names = Arrays.asList("John", "Jane");
names.stream()
    .map((var name) -> name.toUpperCase())
    .forEach(System.out::println);

// HTTP Client API
HttpClient client = HttpClient.newHttpClient();
HttpRequest request = HttpRequest.newBuilder()
    .uri(URI.create("https://api.example.com/users"))
    .build();
HttpResponse<String> response = client.send(request, 
    HttpResponse.BodyHandlers.ofString());

// New String methods
String text = "  Hello World  ";
System.out.println(text.strip());        // "Hello World"
System.out.println(text.isBlank());      // false
System.out.println("Java ".repeat(3));   // "Java Java Java "

// Files utility methods
String content = Files.readString(Paths.get("file.txt"));
Files.writeString(Paths.get("output.txt"), "Hello World");
```

## 3. What are the new features in Java 17?

Java 17 is the latest LTS release with several language enhancements and performance improvements, making Java more modern and developer-friendly.

**Major Features:**
- **Sealed Classes:** Restrict class inheritance
- **Pattern Matching for instanceof:** Simplified type checking
- **Records:** Immutable data classes
- **Text Blocks:** Multi-line string literals
- **Switch Expressions:** Enhanced switch statements
- **Helpful NullPointerExceptions:** Better error messages
- **Strong Encapsulation:** JDK internals encapsulated

```java
// Sealed classes
public sealed class Shape 
    permits Circle, Rectangle, Triangle {
}

// Records - immutable data classes
public record Person(String name, int age) {
    // Automatically generates constructor, getters, equals, hashCode, toString
}

// Pattern matching for instanceof
if (obj instanceof String str) {
    System.out.println(str.toUpperCase()); // str is automatically cast
}

// Text blocks
String json = """
    {
        "name": "John",
        "age": 30,
        "city": "New York"
    }
    """;

// Switch expressions
String dayType = switch (day) {
    case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> "Weekday";
    case SATURDAY, SUNDAY -> "Weekend";
};

// Enhanced switch with pattern matching (Preview)
String result = switch (obj) {
    case Integer i -> "Integer: " + i;
    case String s -> "String: " + s;
    case null -> "null value";
    default -> "Unknown type";
};
```

## 4. What is the Java release cycle and LTS versions?

Java moved to a 6-month release cycle in 2017, providing regular updates with new features while maintaining Long Term Support versions for enterprise stability.

**Release Cycle:**
- **6-month releases:** March and September each year
- **Feature releases:** Java 9, 10, 12, 13, 14, 15, 16, 18, 19, 20, 21...
- **LTS releases:** Every 3 years (Java 8, 11, 17, 21...)
- **Support timeline:** LTS versions supported for 8+ years

**LTS Versions:**
- **Java 8 (2014):** Lambda expressions, Stream API - supported until 2030+
- **Java 11 (2018):** HTTP Client, var enhancements - supported until 2026+
- **Java 17 (2021):** Sealed classes, Records, Pattern matching - supported until 2029+
- **Java 21 (2023):** Latest LTS with Virtual Threads, Pattern matching

**Benefits of 6-month cycle:**
- **Faster innovation:** New features delivered quickly
- **Predictable releases:** Regular schedule for planning
- **Reduced risk:** Smaller changes per release
- **LTS stability:** Enterprise applications can stick to LTS versions

**Choosing Java Version:**
- **Enterprise applications:** Use LTS versions (8, 11, 17, 21)
- **New projects:** Consider latest LTS (Java 17 or 21)
- **Experimentation:** Try latest feature releases for new capabilities
- **Migration strategy:** Plan upgrades around LTS releases

The new release model allows Java to evolve faster while providing stability through LTS versions for production environments.
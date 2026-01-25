## Streams API

### 206. What is Stream API?

**Stream API** is a powerful feature introduced in **Java 8** for functional-style operations on collections:

#### Key Characteristics:
- **Functional programming**: Process data using lambda expressions
- **Lazy evaluation**: Operations execute only when needed
- **Immutable**: Original data source remains unchanged
- **Chainable**: Operations can be linked together
- **Parallel processing**: Built-in support for concurrent operations

#### Basic Example:
```java
List<String> names = Arrays.asList("John", "Alice", "Bob", "Charlie");

// Traditional approach
List<String> result = new ArrayList<>();
for (String name : names) {
    if (name.length() > 3) {
        result.add(name.toUpperCase());
    }
}

// Stream API approach
List<String> result = names.stream()
    .filter(name -> name.length() > 3)
    .map(String::toUpperCase)
    .collect(Collectors.toList());
```

#### Benefits:
- **Readable code**: Declarative style focuses on what, not how
- **Composable operations**: Chain multiple operations easily
- **Parallel processing**: Easy parallelization with parallelStream()
- **Lazy evaluation**: Better performance through deferred execution

### 207. What is the difference between Collection and Stream?

#### Key Differences:

| Aspect | Collection | Stream |
|--------|------------|--------|
| **Purpose** | Store data | Process data |
| **Mutability** | Mutable (can modify) | Immutable (read-only) |
| **Evaluation** | Eager (immediate) | Lazy (deferred) |
| **Iteration** | Multiple times | Single use (consumed) |
| **Memory** | Stores all elements | Processes on-demand |
| **Modification** | Add/remove elements | Transform without changing source |

#### Examples:

##### Collection - Data Storage:
```java
List<String> list = new ArrayList<>();
list.add("Apple");
list.add("Banana");
list.add("Cherry");

// Can iterate multiple times
for (String fruit : list) { /* process */ }
for (String fruit : list) { /* process again */ }

// Can modify
list.remove("Banana");
list.add("Date");
```

##### Stream - Data Processing:
```java
List<String> fruits = Arrays.asList("Apple", "Banana", "Cherry");

Stream<String> stream = fruits.stream()
    .filter(s -> s.startsWith("A"))
    .map(String::toUpperCase);

// Can only be consumed once
List<String> result1 = stream.collect(toList()); // Works
// List<String> result2 = stream.collect(toList()); // IllegalStateException!

// Original collection unchanged
System.out.println(fruits); // [Apple, Banana, Cherry]
```

#### Relationship:
```java
// Collection to Stream
List<String> list = Arrays.asList("a", "b", "c");
Stream<String> stream = list.stream();

// Stream to Collection
List<String> newList = stream.collect(Collectors.toList());
Set<String> newSet = stream.collect(Collectors.toSet());
```

### 208. What are intermediate and terminal operations?

#### Intermediate Operations:
**Return a new Stream** and are **lazily evaluated**:

```java
Stream<String> words = Arrays.asList("hello", "world", "java").stream();

// All intermediate operations - nothing executes yet
Stream<String> processed = words
    .filter(s -> s.length() > 4)    // Intermediate
    .map(String::toUpperCase)       // Intermediate  
    .sorted()                       // Intermediate
    .distinct();                    // Intermediate

// Only when terminal operation is called, processing begins
List<String> result = processed.collect(toList()); // Terminal
```

#### Common Intermediate Operations:

##### filter() - Select elements:
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
List<Integer> evens = numbers.stream()
    .filter(n -> n % 2 == 0)
    .collect(toList()); // [2, 4, 6]
```

##### map() - Transform elements:
```java
List<String> words = Arrays.asList("hello", "world");
List<Integer> lengths = words.stream()
    .map(String::length)
    .collect(toList()); // [5, 5]
```

##### sorted() - Sort elements:
```java
List<String> names = Arrays.asList("Charlie", "Alice", "Bob");
List<String> sorted = names.stream()
    .sorted()
    .collect(toList()); // [Alice, Bob, Charlie]
```

#### Terminal Operations:
**Produce a result** and **trigger execution**:

##### collect() - Gather results:
```java
List<String> result = stream.collect(Collectors.toList());
Set<String> uniqueItems = stream.collect(Collectors.toSet());
String joined = stream.collect(Collectors.joining(", "));
```

##### forEach() - Side effects:
```java
stream.forEach(System.out::println);
stream.forEach(item -> logger.info("Processing: " + item));
```

##### reduce() - Aggregate:
```java
Optional<Integer> sum = numbers.stream().reduce(Integer::sum);
Optional<String> longest = words.stream().reduce((a, b) -> a.length() > b.length() ? a : b);
```

#### Lazy Evaluation Example:
```java
List<String> words = Arrays.asList("apple", "banana", "cherry");

Stream<String> stream = words.stream()
    .filter(s -> {
        System.out.println("Filtering: " + s);
        return s.length() > 5;
    })
    .map(s -> {
        System.out.println("Mapping: " + s);
        return s.toUpperCase();
    });

System.out.println("Stream created, no output yet...");

// Only now does processing begin
List<String> result = stream.collect(toList());
// Output:
// Filtering: apple
// Filtering: banana
// Mapping: banana
// Filtering: cherry
```

### 209. What is the difference between map() and flatMap()?

#### map() - One-to-One Transformation:
```java
// Transform each element to exactly one new element
List<String> words = Arrays.asList("hello", "world");
List<Integer> lengths = words.stream()
    .map(String::length)  // String -> Integer (1:1)
    .collect(toList());   // [5, 5]

List<String> upperWords = words.stream()
    .map(String::toUpperCase)  // String -> String (1:1)
    .collect(toList());        // [HELLO, WORLD]
```

#### flatMap() - One-to-Many Transformation:
```java
// Transform each element to a stream, then flatten
List<String> sentences = Arrays.asList("hello world", "java stream");
List<String> words = sentences.stream()
    .flatMap(sentence -> Arrays.stream(sentence.split(" ")))  // String -> Stream<String>
    .collect(toList());  // [hello, world, java, stream]

// Without flatMap, you'd get Stream<Stream<String>>
// With flatMap, streams are flattened to Stream<String>
```

#### Complex Example:
```java
class Person {
    private List<String> hobbies;
    
    public Person(String... hobbies) {
        this.hobbies = Arrays.asList(hobbies);
    }
    
    public List<String> getHobbies() { return hobbies; }
}

List<Person> people = Arrays.asList(
    new Person("reading", "swimming"),
    new Person("cooking", "reading"),
    new Person("gaming")
);

// map() - returns Stream<List<String>>
List<List<String>> hobbyLists = people.stream()
    .map(Person::getHobbies)
    .collect(toList());  // [[reading, swimming], [cooking, reading], [gaming]]

// flatMap() - returns Stream<String>
List<String> allHobbies = people.stream()
    .flatMap(person -> person.getHobbies().stream())
    .distinct()
    .collect(toList());  // [reading, swimming, cooking, gaming]
```

#### Visual Representation:
```
map():     [1, 2, 3] -> [f(1), f(2), f(3)]
flatMap(): [[1,2], [3,4], [5]] -> [1, 2, 3, 4, 5]
```

### 210. What is the difference between filter() and map()?

#### Key Differences:

| Aspect | filter() | map() |
|--------|----------|-------|
| **Purpose** | Select elements | Transform elements |
| **Input** | Predicate (returns boolean) | Function (returns new value) |
| **Stream size** | May reduce | Maintains same size |
| **Element type** | Same type | Can change type |

#### filter() - Selection:
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

// Select only even numbers
List<Integer> evens = numbers.stream()
    .filter(n -> n % 2 == 0)  // Predicate: int -> boolean
    .collect(toList());       // [2, 4, 6, 8, 10] - fewer elements

// Select strings longer than 3 characters
List<String> words = Arrays.asList("hi", "hello", "world", "a");
List<String> longWords = words.stream()
    .filter(s -> s.length() > 3)  // Predicate: String -> boolean
    .collect(toList());           // [hello, world] - fewer elements
```

#### map() - Transformation:
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

// Transform each number to its square
List<Integer> squares = numbers.stream()
    .map(n -> n * n)      // Function: int -> int
    .collect(toList());   // [1, 4, 9, 16, 25] - same count

// Transform strings to their lengths
List<String> words = Arrays.asList("hello", "world", "java");
List<Integer> lengths = words.stream()
    .map(String::length)  // Function: String -> Integer
    .collect(toList());   // [5, 5, 4] - same count, different type
```

#### Combining filter() and map():
```java
List<String> words = Arrays.asList("apple", "hi", "banana", "a", "cherry");

List<Integer> longWordLengths = words.stream()
    .filter(s -> s.length() > 3)  // Select: [apple, banana, cherry]
    .map(String::length)          // Transform: [5, 6, 6]
    .collect(toList());

// Order matters!
List<Integer> lengthsOfLongWords = words.stream()
    .map(String::length)          // Transform first: [5, 2, 6, 1, 6]
    .filter(len -> len > 3)       // Then select: [5, 6, 6]
    .collect(toList());
```

### 211. What is parallel stream?

**Parallel streams** process elements **concurrently** using multiple threads:

#### Creating Parallel Streams:
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

// Method 1: parallelStream()
List<Integer> result1 = numbers.parallelStream()
    .map(n -> n * n)
    .collect(toList());

// Method 2: parallel()
List<Integer> result2 = numbers.stream()
    .parallel()
    .map(n -> n * n)
    .collect(toList());

// Convert back to sequential
List<Integer> result3 = numbers.parallelStream()
    .sequential()  // Back to sequential processing
    .map(n -> n * n)
    .collect(toList());
```

#### Performance Example:
```java
List<Integer> largeList = IntStream.rangeClosed(1, 10_000_000)
    .boxed()
    .collect(toList());

// Sequential processing
long start = System.currentTimeMillis();
long sequentialSum = largeList.stream()
    .mapToLong(n -> expensiveOperation(n))
    .sum();
long sequentialTime = System.currentTimeMillis() - start;

// Parallel processing
start = System.currentTimeMillis();
long parallelSum = largeList.parallelStream()
    .mapToLong(n -> expensiveOperation(n))
    .sum();
long parallelTime = System.currentTimeMillis() - start;

System.out.println("Sequential: " + sequentialTime + "ms");
System.out.println("Parallel: " + parallelTime + "ms");
```

#### Thread Pool:
```java
// Parallel streams use ForkJoinPool.commonPool()
System.out.println("Available processors: " + 
    Runtime.getRuntime().availableProcessors());

// Custom thread pool (Java 8+)
ForkJoinPool customThreadPool = new ForkJoinPool(4);
try {
    List<Integer> result = customThreadPool.submit(() ->
        numbers.parallelStream()
            .map(this::processNumber)
            .collect(toList())
    ).get();
} finally {
    customThreadPool.shutdown();
}
```

### 212. When should you use parallel streams?

#### Use Parallel Streams When:

##### 1. Large Datasets:
```java
// Good for large collections
List<Integer> largeList = IntStream.rangeClosed(1, 1_000_000)
    .boxed().collect(toList());

// Parallel processing beneficial
double average = largeList.parallelStream()
    .mapToInt(Integer::intValue)
    .average()
    .orElse(0.0);
```

##### 2. CPU-Intensive Operations:
```java
// Expensive computation per element
List<String> texts = getMillionTexts();
List<String> processed = texts.parallelStream()
    .map(this::expensiveTextProcessing)  // CPU-intensive
    .collect(toList());

private String expensiveTextProcessing(String text) {
    // Complex algorithm, encryption, etc.
    return processedText;
}
```

##### 3. Independent Operations:
```java
// Operations that don't depend on each other
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
List<Integer> results = numbers.parallelStream()
    .map(n -> n * n)           // Independent
    .filter(n -> n > 10)       // Independent
    .collect(toList());
```

#### Avoid Parallel Streams When:

##### 1. Small Datasets:
```java
// Overhead not worth it for small collections
List<Integer> smallList = Arrays.asList(1, 2, 3, 4, 5);
// Use sequential stream
List<Integer> result = smallList.stream()  // Not parallelStream()
    .map(n -> n * 2)
    .collect(toList());
```

##### 2. I/O Operations:
```java
// Don't parallelize I/O bound operations
List<String> urls = getUrls();
// Avoid this - threads will be blocked on I/O
List<String> content = urls.stream()  // Sequential better
    .map(this::downloadContent)  // I/O operation
    .collect(toList());
```

##### 3. Stateful Operations:
```java
// Avoid parallel with stateful operations
List<Integer> numbers = Arrays.asList(3, 1, 4, 1, 5, 9);
// Don't parallelize sorting - order matters
List<Integer> sorted = numbers.stream()  // Sequential
    .sorted()
    .collect(toList());
```

#### Performance Guidelines:
```java
// Rule of thumb: N * Q > 10,000
// N = number of elements
// Q = cost per element operation

// Good candidate for parallel
List<String> millionStrings = getMillion();
millionStrings.parallelStream()
    .map(this::complexProcessing)  // High Q
    .collect(toList());

// Poor candidate for parallel  
List<Integer> tenNumbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
tenNumbers.stream()  // Use sequential
    .map(n -> n * 2)  // Low Q
    .collect(toList());
```

### 213. What is the difference between findFirst() and findAny()?

#### Key Differences:

| Aspect | findFirst() | findAny() |
|--------|-------------|-----------|
| **Guarantee** | First element in encounter order | Any element (non-deterministic) |
| **Parallel performance** | May be slower | Optimized for parallel |
| **Deterministic** | Yes | No (in parallel streams) |
| **Use case** | When order matters | When any match is sufficient |

#### findFirst() - Deterministic:
```java
List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");

// Always returns first match
Optional<String> first = names.stream()
    .filter(name -> name.length() > 3)
    .findFirst();  // Always "Alice"

System.out.println(first.orElse("None")); // Alice

// Even in parallel - maintains encounter order
Optional<String> firstParallel = names.parallelStream()
    .filter(name -> name.length() > 3)
    .findFirst();  // Still "Alice" (but potentially slower)
```

#### findAny() - Non-deterministic:
```java
List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");

// May return any match
Optional<String> any = names.stream()
    .filter(name -> name.length() > 3)
    .findAny();  // Could be "Alice", "Charlie", or "David"

// In parallel - truly non-deterministic
Optional<String> anyParallel = names.parallelStream()
    .filter(name -> name.length() > 3)
    .findAny();  // Could be any matching element
```

#### Performance Comparison:
```java
List<Integer> largeList = IntStream.rangeClosed(1, 10_000_000)
    .boxed().collect(toList());

// findFirst() - must maintain order
long start = System.currentTimeMillis();
Optional<Integer> first = largeList.parallelStream()
    .filter(n -> n > 5_000_000)
    .findFirst();
long firstTime = System.currentTimeMillis() - start;

// findAny() - can return any match quickly
start = System.currentTimeMillis();
Optional<Integer> any = largeList.parallelStream()
    .filter(n -> n > 5_000_000)
    .findAny();
long anyTime = System.currentTimeMillis() - start;

System.out.println("findFirst(): " + firstTime + "ms");
System.out.println("findAny(): " + anyTime + "ms");  // Usually faster
```

#### Practical Usage:
```java
// Use findFirst() when order matters
Optional<String> firstError = logEntries.stream()
    .filter(entry -> entry.getLevel() == ERROR)
    .findFirst();  // Need the first error chronologically

// Use findAny() when any match is sufficient
Optional<String> anyAvailableServer = servers.parallelStream()
    .filter(Server::isAvailable)
    .findAny();  // Any available server is fine
```

### 214. What is Optional class?

**Optional** is a container that **may or may not contain a value**:

#### Creating Optional:
```java
// Create Optional with value
Optional<String> present = Optional.of("Hello");

// Create Optional with potentially null value
String nullableValue = getValue(); // might return null
Optional<String> maybe = Optional.ofNullable(nullableValue);

// Create empty Optional
Optional<String> empty = Optional.empty();
```

#### Checking and Retrieving Values:
```java
Optional<String> optional = Optional.of("Hello World");

// Check if value is present
if (optional.isPresent()) {
    String value = optional.get();  // Safe to call get()
    System.out.println(value);
}

// Modern approach - avoid get()
optional.ifPresent(System.out::println);

// Provide default value
String result = optional.orElse("Default Value");
String result2 = optional.orElseGet(() -> "Computed Default");

// Throw exception if empty
String result3 = optional.orElseThrow(() -> new RuntimeException("No value"));
```

#### Transforming Optional Values:
```java
Optional<String> name = Optional.of("john doe");

// Transform if present
Optional<String> upperName = name.map(String::toUpperCase);
Optional<Integer> nameLength = name.map(String::length);

// Chain transformations
Optional<String> processed = name
    .map(String::trim)
    .map(String::toUpperCase)
    .filter(s -> s.length() > 5);

// flatMap for nested Optionals
Optional<Optional<String>> nested = Optional.of(Optional.of("value"));
Optional<String> flattened = nested.flatMap(opt -> opt);
```

#### Practical Examples:
```java
public class UserService {
    
    // Method returns Optional instead of null
    public Optional<User> findUserById(Long id) {
        User user = database.findUser(id);
        return Optional.ofNullable(user);
    }
    
    // Using Optional in business logic
    public String getUserDisplayName(Long userId) {
        return findUserById(userId)
            .map(User::getName)
            .filter(name -> !name.isEmpty())
            .orElse("Anonymous User");
    }
    
    // Chaining Optional operations
    public Optional<String> getUserEmail(Long userId) {
        return findUserById(userId)
            .map(User::getProfile)
            .map(Profile::getEmail)
            .filter(email -> email.contains("@"));
    }
}
```

### 215. How do you handle null values with Optional?

#### Wrapping Potentially Null Values:
```java
// Instead of returning null
public String getConfigValue(String key) {
    String value = properties.getProperty(key);
    return Optional.ofNullable(value)
        .orElse("default-value");
}

// Handling method that might return null
String userInput = getUserInput(); // might be null
Optional<String> input = Optional.ofNullable(userInput);
```

#### Safe Transformations:
```java
// Traditional null checking
String result = null;
if (user != null) {
    Profile profile = user.getProfile();
    if (profile != null) {
        String email = profile.getEmail();
        if (email != null) {
            result = email.toUpperCase();
        }
    }
}

// Optional chaining
String result = Optional.ofNullable(user)
    .map(User::getProfile)
    .map(Profile::getEmail)
    .map(String::toUpperCase)
    .orElse(null);
```

#### Default Values and Exceptions:
```java
// Provide default values
String name = Optional.ofNullable(user.getName())
    .orElse("Unknown");

// Lazy default computation
String expensiveDefault = Optional.ofNullable(cachedValue)
    .orElseGet(() -> computeExpensiveDefault());

// Throw custom exceptions
User user = Optional.ofNullable(findUser(id))
    .orElseThrow(() -> new UserNotFoundException("User not found: " + id));
```

#### Filtering and Validation:
```java
// Validate and filter
Optional<String> validEmail = Optional.ofNullable(emailInput)
    .filter(email -> email.contains("@"))
    .filter(email -> email.length() > 5);

// Complex validation
Optional<User> validUser = Optional.ofNullable(user)
    .filter(u -> u.getAge() >= 18)
    .filter(u -> u.getEmail() != null)
    .filter(u -> !u.getName().isEmpty());
```

#### Stream Integration:
```java
List<String> names = Arrays.asList("John", null, "Alice", "", "Bob");

// Filter out nulls and empty strings
List<String> validNames = names.stream()
    .map(Optional::ofNullable)
    .filter(Optional::isPresent)
    .map(Optional::get)
    .filter(name -> !name.isEmpty())
    .collect(toList());

// More concise with flatMap
List<String> validNames2 = names.stream()
    .filter(Objects::nonNull)
    .filter(name -> !name.isEmpty())
    .collect(toList());
```

#### Best Practices:
```java
// DO: Use Optional for return types
public Optional<User> findUser(Long id) {
    return Optional.ofNullable(userRepository.findById(id));
}

// DON'T: Use Optional for parameters
public void processUser(Optional<User> user) { } // Avoid this

// DO: Use Optional for fields only when truly optional
public class Config {
    private final String required;
    private final Optional<String> optional;
}

// DON'T: Use get() without checking
Optional<String> opt = getOptionalValue();
// String value = opt.get(); // Dangerous!

// DO: Use safe methods
String value = opt.orElse("default");
opt.ifPresent(this::processValue);
```

## Best Practices

### 1. Prefer Method References:
```java
// Good
list.stream().map(String::toUpperCase).collect(toList());

// Less preferred
list.stream().map(s -> s.toUpperCase()).collect(toList());
```

### 2. Use Appropriate Collectors:
```java
// Collect to specific types
Set<String> uniqueNames = names.stream().collect(Collectors.toSet());
Map<String, Integer> nameToLength = names.stream()
    .collect(Collectors.toMap(name -> name, String::length));
```

### 3. Avoid Side Effects in Streams:
```java
// Avoid - side effects
list.stream().map(item -> {
    System.out.println(item); // Side effect
    return item.toUpperCase();
});

// Prefer - separate concerns
list.forEach(System.out::println); // Side effect
List<String> upper = list.stream().map(String::toUpperCase).collect(toList());
```

### 4. Use Optional Properly:
```java
// Good - return Optional from methods
public Optional<User> findUser(Long id) { }

// Avoid - Optional parameters
public void processUser(Optional<User> user) { } // Don't do this
```

## Key Takeaways

1. **Streams process data functionally** without modifying the source
2. **Lazy evaluation** - operations execute only when terminal operation is called
3. **Intermediate operations** return streams, **terminal operations** produce results
4. **map() transforms, flatMap() flattens** nested structures
5. **filter() selects, map() transforms** - different purposes
6. **Parallel streams** for large datasets and CPU-intensive operations
7. **findFirst() guarantees order, findAny() optimizes for parallel**
8. **Optional handles null safely** - use for return types, not parameters
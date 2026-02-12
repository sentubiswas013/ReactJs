## Core Java Fundamentals (15 questions)

## 1. You have a multi-threaded application where multiple threads access shared data. How do you ensure thread safety?

To ensure thread safety:

* **Use synchronization**

  * `synchronized` methods/blocks
  * `ReentrantLock`
* **Use concurrent collections**

  * `ConcurrentHashMap`
  * `CopyOnWriteArrayList`
* **Use atomic classes**

  * `AtomicInteger`, `AtomicReference`
* **Use immutability**

  * Make objects immutable where possible
* **Thread confinement**

  * Use `ThreadLocal`

Best practice: Prefer high-level concurrency utilities (`java.util.concurrent`) instead of manual synchronization.

---

## 2. Your application is experiencing memory leaks. How do you identify and fix them?

### Identification:

* Use profiling tools:

  * VisualVM
  * JConsole
  * Eclipse MAT (Memory Analyzer Tool)
* Check heap dumps
* Monitor GC logs
* Look for:

  * Unclosed resources
  * Static collections holding objects
  * Listeners not deregistered
  * ThreadLocal misuse

### Fix:

* Remove unnecessary references
* Use weak references (`WeakHashMap`)
* Close resources using try-with-resources
* Avoid static object retention

---

## 3. You need to process a large file (10GB+) without loading it entirely into memory. How do you approach this?

* Use **stream-based processing**

  * `BufferedReader`
  * `Files.lines()`
* Process line by line
* Use **NIO (java.nio)**

  * `FileChannel`
  * `MappedByteBuffer`
* Use chunk-based processing
* Avoid reading full file into `String` or `byte[]`

Example approach:

```java
try (BufferedReader br = new BufferedReader(new FileReader("file.txt"))) {
    String line;
    while ((line = br.readLine()) != null) {
        process(line);
    }
}
```

---

## 4. How would you implement a custom exception hierarchy for your application?

* Create base exception:

```java
public class ApplicationException extends Exception {
    public ApplicationException(String message) {
        super(message);
    }
}
```

* Create specific exceptions:

```java
public class ValidationException extends ApplicationException {}
public class DatabaseException extends ApplicationException {}
```

Best practices:

* Use checked exceptions for recoverable errors
* Use runtime exceptions for programming errors
* Maintain clear hierarchy

---

## 5. You need to compare two objects for equality. When would you override equals() and hashCode()?

Override when:

* Object is used in collections like `HashMap`, `HashSet`
* Logical equality differs from reference equality

Rules:

* If you override `equals()`, you MUST override `hashCode()`
* Equal objects must return same hash code

Use based on business identity (e.g., id field).

---

## 6. Your application needs to handle millions of objects. How do you optimize memory usage?

* Use primitive types instead of wrappers
* Avoid unnecessary object creation
* Use object pooling
* Use efficient collections
* Tune JVM heap (`-Xms`, `-Xmx`)
* Use flyweight pattern
* Remove unused references
* Use streaming instead of storing large collections

---

## 7. You need to implement a caching mechanism without using external libraries. How do you do it?

### Basic Cache:

```java
Map<String, Object> cache = new ConcurrentHashMap<>();
```

### With LRU:

Use `LinkedHashMap`:

```java
class LRUCache<K,V> extends LinkedHashMap<K,V> {
    private final int capacity;

    public LRUCache(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
        return size() > capacity;
    }
}
```

Add:

* TTL using timestamp
* Synchronization for thread safety

---

## 8. How would you handle circular references in object serialization?

Solutions:

* Use `transient` to avoid serializing back references
* Use custom serialization (`writeObject` / `readObject`)
* Use JSON libraries with annotations (like `@JsonManagedReference` / `@JsonBackReference`)
* Break circular dependency logically

---

## 9. You need to execute tasks asynchronously and get results. How do you implement this?

Use:

### ExecutorService

```java
ExecutorService executor = Executors.newFixedThreadPool(5);
Future<String> future = executor.submit(() -> "Result");
String result = future.get();
```

### Or CompletableFuture (preferred in modern Java)

---

## 10. Your application has performance issues with String concatenation in loops. How do you fix it?

Problem:

```java
String s = "";
for (...) {
    s += value;
}
```

Fix:
Use `StringBuilder`

```java
StringBuilder sb = new StringBuilder();
for (...) {
    sb.append(value);
}
String result = sb.toString();
```

Reason:
String is immutable → creates new object each time.

---

## 11. How would you implement a thread-safe singleton. What approaches would you use?

### 1. Eager Initialization

```java
private static final Singleton INSTANCE = new Singleton();
```

### 2. Synchronized method

```java
public static synchronized Singleton getInstance()
```

### 3. Double-Checked Locking (Best practice)

```java
private static volatile Singleton instance;

public static Singleton getInstance() {
    if (instance == null) {
        synchronized (Singleton.class) {
            if (instance == null) {
                instance = new Singleton();
            }
        }
    }
    return instance;
}
```

### 4. Enum Singleton (Best & safest)

```java
enum Singleton {
    INSTANCE;
}
```

---

## 12. How would you use CompletableFuture for async operations?

Example:

```java
CompletableFuture<String> future =
    CompletableFuture.supplyAsync(() -> "Hello")
        .thenApply(result -> result + " World");

future.thenAccept(System.out::println);
```

Features:

* `thenApply()` → transform
* `thenAccept()` → consume
* `thenCombine()` → combine
* `exceptionally()` → error handling
* `allOf()` → wait for multiple

Non-blocking and chainable.

---

## 13. You need to handle race conditions in your application. What synchronization mechanisms would you use?

* `synchronized`
* `ReentrantLock`
* `ReadWriteLock`
* `Atomic` classes
* `ConcurrentHashMap`
* `Semaphore`
* `CountDownLatch`
* Avoid shared mutable state

Prefer fine-grained locking over coarse-grained locking.

---

## 14. How would you implement producer-consumer pattern?

### Using BlockingQueue (Best practice)

```java
BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

Producer:
queue.put(value);

Consumer:
queue.take();
```

BlockingQueue handles:

* Synchronization
* Waiting
* Thread safety

---

## 15. How would you handle deadlock situations?

### Prevention:

* Lock ordering (always acquire locks in same order)
* Use timeout locks (`tryLock`)
* Avoid nested locks
* Reduce lock scope

### Detection:

* Thread dumps (`jstack`)
* Monitoring tools

### Recovery:

* Interrupt threads
* Restart application if needed


## Collections & Data Structures (12 questions)

I’ll keep your **questions exactly as written** and provide clear, structured answers suitable for interviews (mid–senior level).

---

## 16. You need to store unique elements in insertion order. Which collection would you use and why?

Use **`LinkedHashSet`**.

**Why:**

* Maintains **insertion order**
* Stores **unique elements** (like Set)
* Internally uses `LinkedHashMap`

Alternative:

* If key-value required → `LinkedHashMap`

---

## 17. Your application requires thread-safe collections. What are your options and trade-offs?

### 1. Synchronized Collections

```java
Collections.synchronizedList(new ArrayList<>());
```

**Pros:** Easy
**Cons:** Coarse-grained locking, lower scalability

---

### 2. Concurrent Collections (Preferred)

* `ConcurrentHashMap`
* `CopyOnWriteArrayList`
* `BlockingQueue`

**Pros:** Better scalability, fine-grained locking
**Cons:** Slightly complex behavior (e.g., weakly consistent iterators)

---

### 3. CopyOnWriteArrayList

**Best for:** More reads, fewer writes
**Trade-off:** Expensive write operations (copies entire array)

---

Best practice: Prefer `java.util.concurrent` collections over synchronized wrappers.

---

## 18. You need to implement a LRU (Least Recently Used) cache. How would you design it?

### Approach 1: Using LinkedHashMap (Best Simple Approach)

```java
class LRUCache<K,V> extends LinkedHashMap<K,V> {
    private final int capacity;

    public LRUCache(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
        return size() > capacity;
    }
}
```

* `true` → access-order
* Removes least recently accessed entry

---

### Approach 2: Custom Implementation

Use:

* HashMap (O(1) lookup)
* Doubly Linked List (maintain order)

Time Complexity:

* Get: O(1)
* Put: O(1)

---

## 19. How would you efficiently find duplicates in a large list?

### Approach 1: Using HashSet (O(n))

```java
Set<Integer> seen = new HashSet<>();
Set<Integer> duplicates = new HashSet<>();

for (Integer num : list) {
    if (!seen.add(num)) {
        duplicates.add(num);
    }
}
```

Time: O(n)
Space: O(n)

---

### For extremely large datasets:

* Use sorting (O(n log n))
* Use streaming + disk-based processing
* Use Bloom Filter (approximate)

---

## 20. You need to sort a collection of custom objects by multiple fields. How do you implement this?

Use **Comparator chaining** (Java 8+):

```java
list.sort(
    Comparator.comparing(Employee::getName)
              .thenComparing(Employee::getAge)
);
```

Or reverse order:

```java
Comparator.comparing(Employee::getSalary)
          .reversed();
```

Best practice:

* Keep comparator logic separate
* Use lambda expressions

---

## 21. Your application needs to process data in FIFO order across multiple threads. What data structure would you use?

Use **`BlockingQueue`**:

* `ArrayBlockingQueue`
* `LinkedBlockingQueue`

Why:

* Thread-safe
* FIFO ordering
* Handles blocking automatically
* Used in Producer-Consumer pattern

---

## 22. You need to find the intersection of two large lists efficiently. What's your approach?

### Best Approach (O(n)):

1. Convert smaller list into HashSet
2. Iterate over larger list

```java
Set<Integer> set = new HashSet<>(list1);
set.retainAll(list2);
```

Time: O(n)
Space: O(n)

For very large datasets:

* Sort both and use two-pointer technique
* Use parallel streams if CPU-bound

---

## 23. How would you implement a custom HashMap from scratch?

### Core Components:

* Array of buckets
* Each bucket contains:

  * LinkedList (or Tree if large)
* Hash function
* Resize logic

### Basic Steps:

1. Compute hash:

   ```java
   int index = hash(key) % capacity;
   ```
2. Handle collision:

   * Separate chaining (LinkedList)
3. Resize when load factor exceeded:

   * Default load factor: 0.75
4. Rehash entries during resize

### Time Complexity:

* Average: O(1)
* Worst: O(n)

Java 8 improvement:

* Converts bucket to Tree (Red-Black Tree) if collisions exceed threshold

---

## 24. You need to maintain a sorted collection with fast insertion and retrieval. What would you use?

Use:

### 1. TreeSet / TreeMap

* Backed by Red-Black Tree
* Sorted automatically
* Time complexity: O(log n)

### 2. PriorityQueue

* If only min/max retrieval required
* O(log n) insertion
* O(1) peek

Choice depends on:

* Need full sorted traversal → TreeSet
* Need only top element → PriorityQueue

---

## 25. Your application needs to store key-value pairs where keys can be garbage collected. What collection would you use?

Use **`WeakHashMap`**

Why:

* Keys stored as **WeakReference**
* Automatically removed when key is garbage collected
* Useful in caching scenarios

Important:

* Only keys are weak, values are strong references

---

## 26. You need to execute multiple tasks in parallel and wait for all to complete. How do you implement this?

### Option 1: ExecutorService

```java
ExecutorService executor = Executors.newFixedThreadPool(5);
List<Callable<String>> tasks = List.of(task1, task2);

List<Future<String>> futures = executor.invokeAll(tasks);
```

---

### Option 2: CompletableFuture (Modern Approach)

```java
CompletableFuture.allOf(f1, f2, f3).join();
```

Best practice:

* Use `CompletableFuture` for non-blocking async
* Use `invokeAll()` for blocking execution

---

## 27. How would you implement a thread pool for task execution?

### Using Executors (Simple)

```java
ExecutorService executor = Executors.newFixedThreadPool(10);
executor.submit(task);
```

Types:

* FixedThreadPool
* CachedThreadPool
* SingleThreadExecutor
* ScheduledThreadPool

---

### Custom ThreadPoolExecutor (Advanced)

```java
ThreadPoolExecutor executor =
    new ThreadPoolExecutor(
        5,      // core pool size
        10,     // max pool size
        60L,
        TimeUnit.SECONDS,
        new LinkedBlockingQueue<>(100)
    );
```

Key Parameters:

* Core pool size
* Max pool size
* Queue type
* Rejection policy

Best practice:

* Avoid `Executors` factory in production (can cause unbounded queue issues)
* Use bounded queues
* Set proper rejection handler

## Streams & Functional Programming (8 questions)

## 28. You need to process a large dataset and filter, transform, and aggregate it. How do you use Streams efficiently?

Use a **stream pipeline** with intermediate and terminal operations.

Example:

```java
List<String> result = data.stream()
    .filter(e -> e.isActive())
    .map(e -> e.getName())
    .collect(Collectors.toList());
```

### Best Practices for Efficiency:

* Use **single stream pipeline** (avoid multiple passes)
* Place **filter() early** to reduce dataset size
* Use **primitive streams** (`IntStream`, `LongStream`) to avoid boxing
* Avoid unnecessary `collect()` if a terminal operation like `count()` or `sum()` works
* For large datasets, consider **parallelStream()** carefully

Example aggregation:

```java
double total = orders.stream()
    .filter(o -> o.isCompleted())
    .mapToDouble(Order::getAmount)
    .sum();
```

---

## 29. How would you implement parallel processing of a collection while maintaining thread safety?

Use:

### 1. parallelStream()

```java
list.parallelStream()
    .map(this::process)
    .collect(Collectors.toList());
```

### Thread Safety Considerations:

* Avoid shared mutable state
* Use immutable objects
* Use thread-safe collectors (`Collectors.groupingByConcurrent`)
* Use atomic variables if needed

Wrong approach:

```java
List<String> result = new ArrayList<>();
list.parallelStream().forEach(result::add); // Not thread-safe
```

Correct:

```java
List<String> result = list.parallelStream()
    .map(this::process)
    .collect(Collectors.toList());
```

Best practice:

* Use parallel streams only for CPU-bound tasks
* Ensure large dataset to justify parallel overhead

---

## 30. You need to group data by multiple criteria. How do you use Collectors effectively?

Use nested `groupingBy()`:

```java
Map<String, Map<String, List<Employee>>> result =
    employees.stream()
        .collect(Collectors.groupingBy(
            Employee::getDepartment,
            Collectors.groupingBy(Employee::getRole)
        ));
```

Other useful collectors:

* `partitioningBy()`
* `mapping()`
* `counting()`
* `summarizingInt()`

Example with aggregation:

```java
Map<String, Double> salaryByDept =
    employees.stream()
        .collect(Collectors.groupingBy(
            Employee::getDepartment,
            Collectors.averagingDouble(Employee::getSalary)
        ));
```

---

## 31. Your Stream operations are causing performance issues. How do you optimize them?

Common Problems & Fixes:

### 1. Multiple traversals

Combine operations into a single stream pipeline.

### 2. Boxing overhead

Use primitive streams:

```java
mapToInt(), mapToLong(), mapToDouble()
```

### 3. Expensive operations inside stream

Move costly logic outside or cache results.

### 4. Parallel stream misuse

Avoid parallel for:

* Small datasets
* IO-bound tasks
* Stateful operations

### 5. Avoid collect when not needed

Use:

* `anyMatch()`
* `findFirst()`
* `count()`

### 6. Measure performance

Use:

* JMH (Java Microbenchmark Harness)
* Profilers

---

## 32. You need to handle exceptions within Stream operations. What's your approach?

Streams don’t handle checked exceptions directly.

### Approach 1: Wrap in RuntimeException

```java
.map(e -> {
    try {
        return process(e);
    } catch (Exception ex) {
        throw new RuntimeException(ex);
    }
})
```

---

### Approach 2: Create custom functional interface

```java
@FunctionalInterface
interface ThrowingFunction<T, R> {
    R apply(T t) throws Exception;
}
```

Wrap it into safe function.

---

### Approach 3: Handle inside lambda

```java
.map(e -> {
    try {
        return process(e);
    } catch (Exception ex) {
        return null;
    }
})
.filter(Objects::nonNull)
```

Best practice:

* Avoid complex exception logic inside streams
* Keep stream operations clean

---

## 33. How would you implement custom Collectors for complex aggregations?

Use `Collector.of()`:

Structure:

```java
Collector.of(
    supplier,
    accumulator,
    combiner,
    finisher
);
```

Example: Concatenate names

```java
Collector<String, StringBuilder, String> collector =
    Collector.of(
        StringBuilder::new,
        StringBuilder::append,
        StringBuilder::append,
        StringBuilder::toString
    );
```

Key Components:

* Supplier → create result container
* Accumulator → add element
* Combiner → merge partial results (important for parallel)
* Finisher → final transformation

Important:

* Ensure combiner works correctly for parallel streams
* Consider Collector characteristics (CONCURRENT, UNORDERED)

---

## 34. You need to process data lazily to save memory. How do you leverage Streams?

Streams are **lazy by default** for intermediate operations.

Example:

```java
Stream<String> stream = data.stream()
    .filter(e -> e.startsWith("A"))
    .map(String::toUpperCase);
```

No execution until terminal operation.

### Techniques:

* Use `Stream.generate()` or `Stream.iterate()`
* Use `Files.lines()` (lazy file reading)
* Avoid collecting into large lists

Example:

```java
Files.lines(Path.of("file.txt"))
    .filter(line -> line.contains("ERROR"))
    .forEach(System.out::println);
```

This processes line-by-line, not entire file in memory.

---

## 35. You need to implement flatMap for nested collections. How do you approach this?

When you have nested collections:

Example:

```java
List<List<String>> list;
```

Use `flatMap()`:

```java
List<String> result =
    list.stream()
        .flatMap(Collection::stream)
        .collect(Collectors.toList());
```

Why flatMap?

* `map()` → produces Stream<Stream<T>>
* `flatMap()` → flattens into Stream<T>

Example with objects:

```java
List<Order> orders;

List<Item> items =
    orders.stream()
        .flatMap(order -> order.getItems().stream())
        .collect(Collectors.toList());
```

Use case:

* Nested collections
* Optional flattening
* Transforming hierarchical data

## Spring Boot REST APIs (12 questions)



## 36. You need to build a RESTful API with proper HTTP status codes and error handling. How do you structure it?

* I follow layered architecture: **Controller → Service → Repository**
* I return proper `ResponseEntity` with correct HTTP status codes.
* I use `@ControllerAdvice` for global exception handling.
* I create a standard error response structure.

### Example:

### Controller

```java
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.save(user));
    }
}
```

### Global Exception Handler

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleNotFound(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneric(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("error", "Something went wrong"));
    }
}
```

---

## 37. How would you implement versioning for your REST APIs?

There are multiple strategies:

* URL versioning → `/api/v1/users`
* Header versioning
* Query parameter versioning

Most common: **URL versioning**

```java
@RestController
@RequestMapping("/api/v1/users")
public class UserControllerV1 { }
```

For header-based:

```java
@GetMapping(value = "/users", headers = "X-API-VERSION=1")
```

Best practice: Use URL versioning for simplicity and clarity.

---

## 38. You need to handle file uploads in a REST API. How do you implement this efficiently?

* Use `MultipartFile`
* Set file size limits in `application.properties`
* Store files in cloud (S3) or file system

```java
@PostMapping("/upload")
public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
    
    Path path = Paths.get("uploads/" + file.getOriginalFilename());
    Files.write(path, file.getBytes());

    return ResponseEntity.ok("File uploaded successfully");
}
```

application.properties:

```
spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=10MB
```

---

## 39. How would you implement pagination and sorting for large datasets?

Spring Data JPA provides built-in support using `Pageable`.

```java
@GetMapping
public ResponseEntity<Page<User>> getUsers(Pageable pageable) {
    return ResponseEntity.ok(userRepository.findAll(pageable));
}
```

Example request:

```
GET /api/users?page=0&size=5&sort=name,asc
```

Repository:

```java
public interface UserRepository extends JpaRepository<User, Long> {}
```

This handles pagination and sorting automatically.

---

## 40. You need to implement rate limiting for your API endpoints. How do you approach this?

* Use **Bucket4j**
* Or API Gateway (preferred in microservices)
* Or Redis-based rate limiting

Example using Bucket4j:

```java
@Bean
public FilterRegistrationBean<Filter> rateLimitFilter() {
    return new FilterRegistrationBean<>(new RateLimitFilter());
}
```

Concept:

* Limit requests per IP
* Return 429 (Too Many Requests)

Best practice: Implement at API Gateway level in production.

---

## 41. How would you handle CORS in a Spring Boot application?

### Global CORS Configuration

```java
@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:4200")
                        .allowedMethods("*");
            }
        };
    }
}
```

Or at controller level:

```java
@CrossOrigin(origins = "http://localhost:4200")
```

---

## 42. You need to implement content negotiation (JSON/XML). How do you configure this?

Spring Boot supports JSON by default.

Add XML dependency:

```xml
<dependency>
    <groupId>com.fasterxml.jackson.dataformat</groupId>
    <artifactId>jackson-dataformat-xml</artifactId>
</dependency>
```

Then:

```java
@GetMapping(value = "/users", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
```

Client can request:

```
Accept: application/xml
```

---

## 43. How would you implement HATEOAS in your REST APIs?

Add dependency:

```xml
spring-boot-starter-hateoas
```

Example:

```java
@GetMapping("/{id}")
public EntityModel<User> getUser(@PathVariable Long id) {
    User user = userService.getById(id);

    return EntityModel.of(user,
        linkTo(methodOn(UserController.class).getUser(id)).withSelfRel(),
        linkTo(methodOn(UserController.class).getAll()).withRel("all-users")
    );
}
```

This adds navigation links in response.

---

## 44. You need to validate request payloads. How do you implement comprehensive validation?

Use `@Valid` and Bean Validation.

DTO:

```java
public class UserDTO {

    @NotBlank
    private String name;

    @Email
    private String email;

    @Min(18)
    private int age;
}
```

Controller:

```java
@PostMapping
public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO dto) {
    return ResponseEntity.ok(userService.save(dto));
}
```

Handle validation errors globally:

```java
@ExceptionHandler(MethodArgumentNotValidException.class)
```

---

## 45. How would you implement API documentation using Swagger/OpenAPI?

Add dependency:

```xml
springdoc-openapi-starter-webmvc-ui
```

Access:

```
http://localhost:8080/swagger-ui.html
```

Add annotation:

```java
@Operation(summary = "Get user by ID")
@GetMapping("/{id}")
```

This automatically generates API documentation.

---

## 46. You need to implement API rate limiting and throttling. What's your approach?

Two levels:

1. Application level → Bucket4j
2. Infrastructure level → API Gateway (recommended)

Best approach in production:

* Use API Gateway (Spring Cloud Gateway / Nginx / AWS API Gateway)
* Redis for distributed rate limiting
* Return HTTP 429

Example concept:

* 100 requests per minute per user
* Use token bucket algorithm

Infrastructure-level is scalable and centralized.

---

## 47. How would you handle timeout and connection issues with external APIs?

Best practices:

* Set connection & read timeout
* Use Resilience4j (Circuit Breaker + Retry)
* Use fallback methods

Example using RestTemplate:

```java
@Bean
public RestTemplate restTemplate() {
    SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
    factory.setConnectTimeout(5000);
    factory.setReadTimeout(5000);
    return new RestTemplate(factory);
}
```

Using Resilience4j:

```java
@CircuitBreaker(name = "externalService", fallbackMethod = "fallback")
public String callExternalAPI() {
    return restTemplate.getForObject("http://api.com", String.class);
}

public String fallback(Exception e) {
    return "Service temporarily unavailable";
}
```

# Spring Boot Data & JPA (10 questions)

## 48. You need to fetch data from database with complex joins. How do you optimize query performance?

* First, I avoid fetching unnecessary columns.
* I use **JPQL with JOIN FETCH** to reduce extra queries.
* For very complex queries, I use **DTO projections**.
* I ensure proper **indexes** exist in the database.
* For heavy reports, I sometimes use **native queries**.

### Example – JOIN FETCH

```java
@Query("SELECT o FROM Order o JOIN FETCH o.customer WHERE o.id = :id")
Optional<Order> findOrderWithCustomer(@Param("id") Long id);
```

### DTO Projection (Better performance)

```java
@Query("SELECT new com.example.dto.OrderDTO(o.id, c.name) " +
       "FROM Order o JOIN o.customer c")
List<OrderDTO> fetchOrderSummary();
```

Best practice:

* Use pagination
* Add DB indexes on join columns
* Avoid `select *`

---

## 49. Your application has N+1 query problems. How do you identify and fix them?

### How I identify:

* Enable Hibernate SQL logs

```
spring.jpa.show-sql=true
```

* Use Hibernate statistics
* Monitor query count in logs

### How I fix:

* Use `JOIN FETCH`
* Use `@EntityGraph`
* Change FetchType strategically

### Example:

```java
@EntityGraph(attributePaths = {"orders"})
@Query("SELECT u FROM User u")
List<User> findAllWithOrders();
```

Or:

```java
@Query("SELECT u FROM User u JOIN FETCH u.orders")
```

Avoid eager everywhere — that causes other problems.

---

## 50. You need to implement soft delete functionality. How do you approach this?

* Add `deleted` boolean column.
* Override delete operation.
* Use `@Where` to filter records automatically.

### Example:

```java
@Entity
@Where(clause = "deleted = false")
public class User {

    @Id
    private Long id;

    private boolean deleted = false;
}
```

Override delete:

```java
@Transactional
public void deleteUser(Long id) {
    User user = repo.findById(id).orElseThrow();
    user.setDeleted(true);
}
```

Better approach:
Use `@SQLDelete`

```java
@SQLDelete(sql = "UPDATE user SET deleted = true WHERE id=?")
```

---

## 51. How would you handle database transactions across multiple operations?

* Use `@Transactional` at service layer.
* Keep transactions short.
* Handle rollback properly.

```java
@Service
public class OrderService {

    @Transactional
    public void createOrder(Order order) {
        orderRepo.save(order);
        paymentService.processPayment(order);
    }
}
```

By default:

* RuntimeException → rollback
* Checked exception → no rollback unless configured

```java
@Transactional(rollbackFor = Exception.class)
```

Never put `@Transactional` in controller layer.

---

## 52. You need to implement auditing (created_by, updated_by, timestamps). How do you do this?

Spring Data JPA provides auditing support.

### Step 1: Enable Auditing

```java
@EnableJpaAuditing
```

### Step 2: Base Entity

```java
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @CreatedBy
    private String createdBy;

    @LastModifiedBy
    private String updatedBy;
}
```

### Step 3: AuditorAware

```java
@Bean
public AuditorAware<String> auditorProvider() {
    return () -> Optional.of("SYSTEM_USER");
}
```

This automatically manages audit fields.

---

## 53. How would you implement custom queries that JPA doesn't support well?

Options:

1. JPQL
2. Native queries
3. Criteria API
4. QueryDSL (Best for dynamic queries)

### Native Query Example:

```java
@Query(value = "SELECT * FROM users WHERE age > :age", nativeQuery = true)
List<User> findUsersAboveAge(@Param("age") int age);
```

### Criteria API Example:

```java
CriteriaBuilder cb = entityManager.getCriteriaBuilder();
CriteriaQuery<User> query = cb.createQuery(User.class);
Root<User> root = query.from(User.class);
query.select(root).where(cb.equal(root.get("name"), "John"));
```

For complex dynamic filtering → QueryDSL is cleaner.

---

## 54. How would you implement optimistic vs pessimistic locking?

### Optimistic Locking (Most common)

* Add `@Version` field

```java
@Version
private Long version;
```

If two users update same record → `OptimisticLockException`.

Best for:

* High read, low write systems

---

### Pessimistic Locking

```java
@Lock(LockModeType.PESSIMISTIC_WRITE)
@Query("SELECT u FROM User u WHERE u.id = :id")
User findByIdForUpdate(@Param("id") Long id);
```

Best for:

* High conflict environments

Optimistic is scalable.
Pessimistic reduces concurrency.

---

## 55. You need to batch insert thousands of records efficiently. What's your approach?

* Use batch size configuration.
* Disable auto-flush.
* Use `saveAll()`
* Use JDBC batch.

application.properties:

```
spring.jpa.properties.hibernate.jdbc.batch_size=50
spring.jpa.properties.hibernate.order_inserts=true
```

Example:

```java
@Transactional
public void saveUsers(List<User> users) {
    repo.saveAll(users);
}
```

For very large data:

* Use `EntityManager`
* Flush & clear periodically

```java
for(int i=0; i<users.size(); i++){
    em.persist(users.get(i));
    if(i % 50 == 0){
        em.flush();
        em.clear();
    }
}
```

---

## 56. How would you implement database migration and versioning?

Best tools:

* Flyway
* Liquibase

### Flyway Example

Add dependency.

Create migration file:

```
V1__create_user_table.sql
```

```sql
CREATE TABLE users (
  id BIGINT PRIMARY KEY,
  name VARCHAR(100)
);
```

Flyway automatically runs scripts at startup.

Benefits:

* Version control DB changes
* Track schema history
* Safe deployments

---

## 57. How would you implement database connection pooling?

Spring Boot uses **HikariCP by default**.

Configure in `application.properties`:

```
spring.datasource.hikari.maximum-pool-size=20
spring.datasource.hikari.minimum-idle=5
spring.datasource.hikari.idle-timeout=30000
spring.datasource.hikari.connection-timeout=20000
```

Why pooling?

* Avoid creating DB connections repeatedly
* Improve performance
* Control resource usage

Best practice:

* Monitor pool usage
* Tune based on server CPU & DB capacity

# Exception Handling & Validation (8 questions)

## 58. You need to implement global exception handling for your Spring Boot application. How do you structure it?

* I use `@RestControllerAdvice` for centralized exception handling.
* I define specific handlers for business exceptions.
* I return consistent error response structure.
* I log errors properly.

### Step 1: Create Error Response DTO

```java
public class ErrorResponse {
    private String message;
    private int status;
    private LocalDateTime timestamp;

    public ErrorResponse(String message, int status) {
        this.message = message;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }
}
```

### Step 2: Global Exception Handler

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(ex.getMessage(), 404));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse("Internal Server Error", 500));
    }
}
```

This keeps controllers clean and consistent.

---

## 59. How would you create custom exceptions with meaningful error messages?

* Extend `RuntimeException`.
* Provide constructor with custom message.
* Optionally include error codes.

### Example:

```java
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
```

Usage:

```java
public User getUser(Long id) {
    return repo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
}
```

Best practice:

* Keep exception names meaningful.
* Avoid generic messages.
* Include context like ID or business value.

---

## 60. You need to validate nested objects and collections. How do you implement this?

* Use `@Valid` on nested objects.
* Use `@Valid` inside collections.
* Apply validation annotations inside nested DTO.

### Example:

```java
public class OrderDTO {

    @NotBlank
    private String orderNumber;

    @Valid
    private CustomerDTO customer;

    @Valid
    private List<ItemDTO> items;
}
```

Nested DTO:

```java
public class ItemDTO {

    @NotBlank
    private String productName;

    @Min(1)
    private int quantity;
}
```

Controller:

```java
@PostMapping
public ResponseEntity<?> createOrder(@Valid @RequestBody OrderDTO dto) {
    return ResponseEntity.ok("Valid");
}
```

Spring automatically validates nested structure.

---

## 61. How would you handle validation errors and return user-friendly messages?

* Handle `MethodArgumentNotValidException`.
* Extract field errors.
* Return structured response.

### Example:

```java
@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<Map<String, String>> handleValidationErrors(
        MethodArgumentNotValidException ex) {

    Map<String, String> errors = new HashMap<>();

    ex.getBindingResult().getFieldErrors()
            .forEach(error ->
                    errors.put(error.getField(), error.getDefaultMessage()));

    return ResponseEntity.badRequest().body(errors);
}
```

Response example:

```json
{
  "email": "must be a valid email",
  "age": "must be greater than 18"
}
```

Clean and user-friendly.

---

## 62. You need to implement custom validators for complex business rules. How do you do this?

* Create custom annotation.
* Implement `ConstraintValidator`.
* Apply to DTO field or class level.

### Step 1: Create Annotation

```java
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AgeValidator.class)
public @interface ValidAge {
    String message() default "Age must be between 18 and 60";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
```

### Step 2: Create Validator

```java
public class AgeValidator implements ConstraintValidator<ValidAge, Integer> {

    @Override
    public boolean isValid(Integer age, ConstraintValidatorContext context) {
        return age != null && age >= 18 && age <= 60;
    }
}
```

### Step 3: Use it

```java
@ValidAge
private Integer age;
```

Perfect for complex rules like cross-field validation.

---

## 63. How would you handle exceptions in async methods?

* Use `@Async`
* Handle exceptions using `CompletableFuture`
* Use `AsyncUncaughtExceptionHandler`

### Example:

```java
@Async
public CompletableFuture<String> process() {
    try {
        // logic
        return CompletableFuture.completedFuture("Success");
    } catch (Exception e) {
        return CompletableFuture.failedFuture(e);
    }
}
```

Global async exception handler:

```java
@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (ex, method, params) ->
                System.out.println("Async error: " + ex.getMessage());
    }
}
```

Better approach:
Use Resilience4j retry + circuit breaker for external calls.

---

## 64. You need to implement retry logic for transient failures?

Best approach: **Resilience4j**

### Step 1: Add dependency

### Step 2: Use @Retry

```java
@Retry(name = "externalService", fallbackMethod = "fallback")
public String callExternalService() {
    return restTemplate.getForObject("http://api.com", String.class);
}

public String fallback(Exception e) {
    return "Service unavailable";
}
```

application.properties:

```
resilience4j.retry.instances.externalService.max-attempts=3
resilience4j.retry.instances.externalService.wait-duration=2s
```

This automatically retries transient failures.

---

## 65. You need to handle different exception types differently. How do you structure your exception hierarchy?

* Create base exception.
* Extend specific business exceptions.
* Handle them separately in `@ControllerAdvice`.

### Example:

```java
public abstract class ApplicationException extends RuntimeException {
    public ApplicationException(String message) {
        super(message);
    }
}
```

Specific exceptions:

```java
public class BusinessException extends ApplicationException {
    public BusinessException(String message) {
        super(message);
    }
}

public class ValidationException extends ApplicationException {
    public ValidationException(String message) {
        super(message);
    }
}
```

Global Handler:

```java
@ExceptionHandler(BusinessException.class)
public ResponseEntity<?> handleBusiness(BusinessException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
}

@ExceptionHandler(ValidationException.class)
public ResponseEntity<?> handleValidation(ValidationException ex) {
    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(ex.getMessage());
}
```

This gives:

* Clean hierarchy
* Clear separation
* Easy maintenance


# Security & Authentication (8 questions)

### **66. You need to implement JWT-based authentication. How do you structure it?**

**Architecture Structure:**

1. **Authentication Flow**

   * User submits credentials (username/password).
   * Authenticate using `AuthenticationManager`.
   * If valid → generate JWT.
   * Return JWT to client.
   * Client sends JWT in `Authorization: Bearer <token>` header.

2. **Components**

   * **JWT Utility Class**

     * Generate token (with claims, expiration).
     * Validate token.
     * Extract username/roles.
     * Use secure secret key or RSA private/public key.

   * **Authentication Controller**

     * `/login` endpoint.
     * Validate credentials.
     * Return JWT.

   * **JWT Filter**

     * Extend `OncePerRequestFilter`.
     * Extract token from header.
     * Validate token.
     * Set authentication in `SecurityContextHolder`.

   * **Security Configuration**

     * Disable session (`SessionCreationPolicy.STATELESS`).
     * Add JWT filter before `UsernamePasswordAuthenticationFilter`.

3. **Best Practices**

   * Use short-lived access tokens.
   * Sign using HMAC SHA256 or RSA.
   * Store secret securely (Vault, env variables).
   * Do not store sensitive data inside JWT.

---

### **67. How would you implement role-based access control (RBAC)?**

**Steps:**

1. **Database Design**

   * `User`
   * `Role`
   * `User_Roles` (Many-to-Many mapping)

2. **Entity Mapping**

   * Use `@ManyToMany`
   * Map roles as `GrantedAuthority`

3. **Load Roles**

   * Implement `UserDetailsService`
   * Convert roles to `SimpleGrantedAuthority`

4. **Configure Authorization**

   * URL-based:

     ```java
     .authorizeHttpRequests(auth ->
         auth.requestMatchers("/admin/**").hasRole("ADMIN")
     )
     ```
   * Method-level:

     ```java
     @PreAuthorize("hasRole('ADMIN')")
     ```

5. **Best Practice**

   * Use principle of least privilege.
   * Use role hierarchy if needed.

---

### **68. You need to secure REST APIs with OAuth2. How do you implement this?**

**Approach (Spring Security OAuth2 Resource Server)**

1. **Add Dependencies**

   * `spring-boot-starter-oauth2-resource-server`

2. **Configure as Resource Server**

   ```yaml
   spring:
     security:
       oauth2:
         resourceserver:
           jwt:
             issuer-uri: https://auth-server.com
   ```

3. **Security Configuration**

   ```java
   http.oauth2ResourceServer(oauth2 -> oauth2.jwt());
   ```

4. **Token Validation**

   * Validate signature via JWK Set URI.
   * Verify issuer and audience.

5. **Grant Types**

   * Authorization Code (Web apps)
   * Client Credentials (Service-to-service)
   * PKCE (SPAs/mobile)

6. **Best Practices**

   * Use HTTPS.
   * Validate scopes.
   * Use centralized Authorization Server (Keycloak, Okta).

---

### **69. How would you implement password encryption and validation?**

**Implementation:**

1. **Use BCryptPasswordEncoder**

   ```java
   @Bean
   public PasswordEncoder passwordEncoder() {
       return new BCryptPasswordEncoder();
   }
   ```

2. **During Registration**

   ```java
   user.setPassword(passwordEncoder.encode(rawPassword));
   ```

3. **During Login**

   * Spring Security automatically matches using `matches()`.

4. **Why BCrypt?**

   * Salted hashing.
   * Adaptive (configurable strength).
   * Resistant to brute-force.

5. **Best Practices**

   * Never store plain text passwords.
   * Enforce strong password policy.
   * Add account lockout mechanism.

---

### **70. You need to prevent SQL injection and XSS attacks. What measures do you take?**

### Prevent SQL Injection:

* Use **Prepared Statements / JPA / Hibernate**
* Never concatenate SQL queries.
* Use parameter binding:

  ```java
  @Query("SELECT u FROM User u WHERE u.email = :email")
  ```
* Validate inputs.
* Use least-privilege DB accounts.

### Prevent XSS:

* Escape output (Thymeleaf auto-escapes).
* Use `@RestController` properly (avoid rendering raw HTML).
* Set Content Security Policy (CSP) headers.
* Validate and sanitize user input.
* Use HttpOnly & Secure cookies.

---

### **71. How would you implement refresh token mechanism?**

**Architecture:**

1. **Login Response**

   * Access Token (short-lived)
   * Refresh Token (long-lived)

2. **Store Refresh Token**

   * Database (recommended)
   * Redis (for scalability)

3. **Refresh Endpoint**

   * Validate refresh token.
   * Check expiration.
   * Issue new access token.
   * Optionally rotate refresh token.

4. **Security Best Practices**

   * Store refresh tokens securely (HttpOnly cookie).
   * Implement token rotation.
   * Invalidate on logout.
   * Track device/session.

---

### **72. You need to implement method-level security. How do you approach this?**

1. **Enable Method Security**

   ```java
   @EnableMethodSecurity
   ```

2. **Use Annotations**

   * `@PreAuthorize`
   * `@PostAuthorize`
   * `@Secured`
   * `@RolesAllowed`

3. **Example**

   ```java
   @PreAuthorize("hasRole('ADMIN')")
   public void deleteUser(Long id) {}
   ```

4. **Expression-based Access**

   ```java
   @PreAuthorize("#id == authentication.principal.id")
   ```

5. **Custom Permission Evaluator**

   * Implement `PermissionEvaluator`
   * For fine-grained control.

---

### **73. How would you implement API key-based authentication?**

**Approach:**

1. **Client Sends API Key**

   ```
   X-API-KEY: abc123
   ```

2. **Create Custom Filter**

   * Extend `OncePerRequestFilter`.
   * Extract API key from header.
   * Validate against DB or config.

3. **Authentication Object**

   * Create `Authentication` implementation.
   * Set in `SecurityContextHolder`.

4. **Security Config**

   * Add filter before `UsernamePasswordAuthenticationFilter`.

5. **Best Practices**

   * Store hashed API keys.
   * Rotate keys periodically.
   * Rate limit requests.
   * Restrict by IP if required.
   * Use HTTPS only.


# Performance Optimization (8 questions)

### **74. Your application has slow response times. How do you identify bottlenecks?**

**Step-by-step approach:**

1. **Enable Monitoring**

   * Spring Boot Actuator (`/metrics`, `/httptrace`)
   * Micrometer + Prometheus + Grafana

2. **Use Profiling Tools**

   * JVisualVM / JProfiler / YourKit
   * Check CPU, memory, threads, GC

3. **Analyze Logs**

   * Enable slow query logs in DB
   * Add request timing logs (AOP or filter)

4. **Thread Dump Analysis**

   * `jstack` to detect deadlocks/blocking threads

5. **APM Tools**

   * New Relic, Dynatrace, Elastic APM
   * Identify slow endpoints or DB calls

6. **Load Testing**

   * JMeter / Gatling to simulate traffic

**Typical Bottlenecks**

* N+1 queries
* Missing DB indexes
* Blocking I/O
* Thread pool exhaustion
* GC pauses

---

### **75. You need to implement caching to improve performance. What strategies would you use?**

**1. Enable Spring Cache**

```java
@EnableCaching
```

**2. Use `@Cacheable`**

```java
@Cacheable("users")
public User getUser(Long id) { }
```

**3. Cache Providers**

* Local: Caffeine (in-memory)
* Distributed: Redis
* Hazelcast (clustered)

**4. Strategies**

* Cache-aside (most common)
* Write-through
* Write-behind

**5. Eviction**

* TTL (time-to-live)
* LRU
* Explicit eviction:

```java
@CacheEvict(value="users", key="#id")
```

**Best Practices**

* Avoid caching frequently changing data
* Use Redis for microservices
* Monitor cache hit/miss ratio

---

### **76. How would you optimize database queries for better performance?**

1. **Use Indexing**

   * Add indexes on frequently queried columns.

2. **Avoid N+1 Problem**

   * Use `fetch join`
   * Use `@EntityGraph`

3. **Pagination**

   ```java
   PageRequest.of(page, size)
   ```

4. **Use Projections**

   * Fetch only required columns.

5. **Connection Pool Tuning**

   * HikariCP tuning (`maximumPoolSize`)

6. **Enable Query Analysis**

   * Use `EXPLAIN` in DB.

7. **Batch Updates**

   * Use `hibernate.jdbc.batch_size`

---

### **77. Your application uses too much memory. How do you optimize it?**

1. **Heap Analysis**

   * Use `jmap` and heap dump analysis.
   * Identify memory leaks.

2. **Optimize Object Creation**

   * Avoid unnecessary object instantiation.
   * Use primitive types when possible.

3. **Tune JVM**

   * Configure `-Xms`, `-Xmx`
   * Use G1GC or ZGC.

4. **Reduce Caching Size**

   * Limit cache capacity.

5. **Stream Processing**

   * Avoid loading large collections fully into memory.
   * Use streaming APIs.

6. **Remove Static References**

   * Prevent memory leaks from static collections.

---

### **78. How would you optimize Spring Boot startup time?**

1. **Lazy Initialization**

```properties
spring.main.lazy-initialization=true
```

2. **Exclude Unused Auto-configurations**

```java
@SpringBootApplication(exclude = {...})
```

3. **Reduce Classpath Size**

   * Remove unnecessary dependencies.

4. **Use Spring AOT / Native Image**

   * GraalVM native build.

5. **Disable Devtools in Production**

6. **Profile Startup**

   * `--debug` flag to analyze auto-config.

---

### **79. You need to handle high concurrent requests. What optimizations would you implement?**

1. **Thread Pool Tuning**

   * Configure Tomcat thread pool.
   * Optimize `maxThreads`.

2. **Connection Pool Optimization**

   * Tune HikariCP pool size.

3. **Stateless Design**

   * Use JWT instead of sessions.

4. **Horizontal Scaling**

   * Deploy multiple instances.
   * Use load balancer.

5. **Use Caching**

   * Reduce DB load.

6. **Use Non-blocking I/O**

   * Spring WebFlux (if required).

7. **Rate Limiting**

   * Prevent abuse (Bucket4j).

---

### **80. How would you implement lazy loading to improve performance?**

1. **JPA Lazy Loading**

```java
@OneToMany(fetch = FetchType.LAZY)
```

2. **Avoid EAGER fetch unless required**

3. **Use DTO Projections**

   * Fetch only required fields.

4. **OpenSessionInView caution**

   * Avoid LazyInitializationException properly.

5. **Spring Lazy Beans**

```java
@Lazy
@Autowired
private MyService service;
```

---

### **81. How would you implement asynchronous processing for long-running tasks?**

1. **Enable Async**

```java
@EnableAsync
```

2. **Use `@Async`**

```java
@Async
public CompletableFuture<String> process() {}
```

3. **Custom Thread Pool**

```java
@Bean
public Executor taskExecutor() { }
```

4. **Message Queues (Advanced)**

   * Kafka / RabbitMQ for decoupling.

5. **Use CompletableFuture**

   * For parallel execution.

---

# Testing (6 questions)

---

### **82. You need to write unit tests for a service with multiple dependencies. How do you structure your tests?**

1. **Use JUnit 5 + Mockito**

2. **Mock Dependencies**

```java
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserService service;
}
```

3. **Follow AAA Pattern**

* Arrange
* Act
* Assert

4. **Test Edge Cases**

   * Null values
   * Exceptions
   * Boundary conditions

5. **Avoid Testing Implementation Details**

---

### **83. How would you test REST API endpoints comprehensively?**

1. **Use MockMvc**

```java
@WebMvcTest(UserController.class)
```

2. **Test**

* Status codes
* Response body
* Validation errors
* Security (401, 403)

3. **Use TestRestTemplate (Integration)**

4. **Test JSON**

```java
.andExpect(jsonPath("$.name").value("John"))
```

5. **Test with different roles**

---

### **84. You need to test database operations. How do you set up test data?**

1. **Use @DataJpaTest**

2. **Use H2 (in-memory DB)**

3. **Insert Test Data**

   * `@Sql`
   * `TestEntityManager`
   * `data.sql`

4. **Use Testcontainers**

   * Run real DB (PostgreSQL, MySQL)

5. **Rollback After Each Test**

   * Default transactional rollback.

---

### **85. How would you implement integration tests for Spring Boot applications?**

1. **Use**

```java
@SpringBootTest
```

2. **Run on Random Port**

```java
@SpringBootTest(webEnvironment = RANDOM_PORT)
```

3. **Autowire TestRestTemplate**

4. **Use Testcontainers for DB**

5. **Test Full Flow**

   * Controller → Service → Repository

---

### **86. You need to test external API integrations. How do you mock them?**

1. **Use Mockito (Unit Level)**

2. **Use WireMock (Integration Level)**

   * Simulate external HTTP service.

3. **Mock RestTemplate**

```java
@MockBean
private RestTemplate restTemplate;
```

4. **Use WebClient Mock**

   * `WebTestClient`

5. **Test Success + Failure Scenarios**

   * Timeout
   * 500 error
   * Invalid response

---

### **87. How would you test async methods and scheduled tasks?**

### Testing Async Methods:

1. Use `@EnableAsync`
2. Use `CompletableFuture.get()` to wait.
3. Use Awaitility:

```java
await().atMost(5, SECONDS).until(...)
```

### Testing Scheduled Tasks:

1. Use `@SpringBootTest`
2. Mock dependencies.
3. Use `@SpyBean` to verify invocation.
4. Use `TaskScheduler` with controlled clock.
5. For advanced cases → disable scheduler and call method directly.

# Microservices Architecture (6 questions)

### **88. You need to implement service-to-service communication. What are your options?**

**1. Synchronous Communication**

* **REST (HTTP)** using `RestTemplate` or `WebClient`
* **Feign Client** (Declarative REST client)
* gRPC (high-performance, binary protocol)

**Use when:**

* Immediate response required
* Simple request-response flow

**2. Asynchronous Communication**

* Kafka
* RabbitMQ
* ActiveMQ

**Use when:**

* Loose coupling required
* High scalability
* Event-driven architecture

**3. Best Practices**

* Use WebClient (non-blocking) for better performance
* Use timeouts and retries
* Secure communication with OAuth2/JWT
* Use service discovery instead of hardcoding URLs

---

### **89. How would you implement service discovery in a microservices architecture?**

**1. Client-Side Discovery**

* Use Netflix Eureka
* Services register themselves
* Clients query registry to get service instances

```yaml
eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/
```

**2. Server-Side Discovery**

* Use Kubernetes (built-in DNS-based discovery)
* Use load balancer (NGINX, AWS ELB)

**3. Steps**

* Add Eureka Client dependency
* Annotate with `@EnableEurekaClient`
* Configure registry URL

**Best Practice**

* Prefer Kubernetes native discovery in cloud environments

---

### **90. You need to handle distributed transactions. What patterns would you use?**

**1. Saga Pattern (Preferred)**

* **Choreography-based**

  * Services communicate via events
  * No central coordinator

* **Orchestration-based**

  * Central orchestrator manages flow

**2. Compensation Transactions**

* Each service defines rollback action

**3. Avoid 2PC**

* Two-Phase Commit is not recommended for microservices (tight coupling, blocking).

**4. Tools**

* Axon Framework
* Camunda
* Kafka-based event orchestration

---

### **91. How would you implement API Gateway pattern?**

**Purpose:**

* Single entry point
* Routing
* Authentication
* Rate limiting

**Implementation Options**

* Spring Cloud Gateway
* Netflix Zuul
* Kong
* NGINX

**Spring Cloud Gateway Example:**

```yaml
spring:
  cloud:
    gateway:
      routes:
        - id: user-service
          uri: lb://USER-SERVICE
          predicates:
            - Path=/users/**
```

**Features**

* Centralized security
* Logging
* Load balancing
* Request filtering

---

### **92. You need to implement circuit breaker for resilient microservices. How do you do this?**

**Use Resilience4j**

1. Add dependency.
2. Configure:

```yaml
resilience4j:
  circuitbreaker:
    instances:
      myService:
        failure-rate-threshold: 50
        wait-duration-in-open-state: 10s
```

3. Annotate method:

```java
@CircuitBreaker(name = "myService", fallbackMethod = "fallback")
```

4. Fallback method:

```java
public String fallback(Exception ex) { }
```

**Other Patterns**

* Retry
* Rate Limiter
* Bulkhead

---

### **93. How would you implement event-driven communication between services?**

**1. Use Message Broker**

* Kafka (high throughput)
* RabbitMQ (complex routing)

**2. Producer Service**

```java
kafkaTemplate.send("order-topic", order);
```

**3. Consumer Service**

```java
@KafkaListener(topics = "order-topic")
public void consume(Order order) { }
```

**4. Best Practices**

* Use event versioning
* Ensure idempotency
* Use dead-letter queue
* Use schema registry

---

# Spring Boot Configuration (4 questions)

---

### **94. You need to manage different configurations for different environments. How do you structure this?**

**Use Spring Profiles**

* `application-dev.yml`
* `application-test.yml`
* `application-prod.yml`

Activate profile:

```properties
spring.profiles.active=dev
```

Use:

```java
@Profile("dev")
```

**Best Practice**

* Keep common config in `application.yml`
* Override only environment-specific values

---

### **95. How would you externalize configuration properties?**

1. Use `application.yml`
2. Environment variables:

   ```
   DB_PASSWORD=${DB_PASSWORD}
   ```
3. Command-line arguments:

   ```
   --server.port=8081
   ```
4. Spring Cloud Config (centralized config server)
5. Kubernetes ConfigMaps

---

### **96. How would you handle sensitive configuration data (passwords, API keys)?**

1. Never store in source code.

2. Use:

   * Environment variables
   * Vault (HashiCorp Vault)
   * AWS Secrets Manager
   * Kubernetes Secrets

3. Encrypt properties (Spring Cloud Config encryption).

4. Restrict access permissions.

5. Rotate secrets periodically.

---

### **97. You need to inject configuration values into your code. What are the different approaches?**

**1. @Value**

```java
@Value("${app.name}")
private String appName;
```

**2. @ConfigurationProperties**

```java
@ConfigurationProperties(prefix="app")
```

* Type-safe
* Recommended for complex configs

**3. Environment Object**

```java
environment.getProperty("app.name");
```

**4. Constructor Injection (Best Practice)**

---

# Design Patterns (3 questions)

---

### **98. You need to create objects with complex initialization. Which pattern would you use?**

**Builder Pattern**

* Useful when:

  * Many optional fields
  * Immutable objects

Example:

```java
User user = User.builder()
    .name("John")
    .age(30)
    .build();
```

**Advantages**

* Readable
* Prevents telescoping constructors
* Immutable objects

---

### **99. How would you implement observer pattern for event handling?**

**1. Using Java Interface**

* Create Observer interface.
* Maintain list of observers.
* Notify observers on state change.

**2. Using Spring Events**

Publisher:

```java
applicationEventPublisher.publishEvent(new OrderCreatedEvent(order));
```

Listener:

```java
@EventListener
public void handle(OrderCreatedEvent event) { }
```

**3. For async events**

```java
@Async
@EventListener
```

---

### **100. How would you implement strategy pattern for different algorithms?**

**1. Define Strategy Interface**

```java
public interface PaymentStrategy {
    void pay(double amount);
}
```

**2. Implement Multiple Strategies**

* CreditCardPayment
* PayPalPayment

**3. Inject Dynamically**

```java
@Service
public class PaymentService {
    private final Map<String, PaymentStrategy> strategies;
}
```

**4. Select Strategy at Runtime**

```java
strategies.get("creditCard").pay(amount);
```

**Advantages**

* Open/Closed Principle
* Easy to add new algorithms
* Eliminates if-else chains

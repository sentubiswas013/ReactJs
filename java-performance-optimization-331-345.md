# 🔵 22. Performance and Optimization

# 🔹 Performance Monitoring

### Question 331: How do you measure Java application performance?

**Answer (35 seconds):**
* **Response Time**: Time to complete requests
* **Throughput**: Requests processed per second
* **Resource Utilization**: CPU, memory, disk, network usage
* **JVM Metrics**: Heap usage, GC frequency, thread count
* **Tools**: JProfiler, VisualVM, JConsole, Micrometer
* **APM Solutions**: New Relic, AppDynamics, Dynatrace

```java
// Micrometer metrics example
@RestController
public class UserController {
    private final MeterRegistry meterRegistry;
    private final Timer requestTimer;
    
    public UserController(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
        this.requestTimer = Timer.builder("user.requests")
            .description("User API request duration")
            .register(meterRegistry);
    }
    
    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id) {
        return requestTimer.recordCallable(() -> userService.findById(id));
    }
}
```

---

### Question 332: What are the common performance bottlenecks in Java?

**Answer (40 seconds):**
* **Memory Issues**: Memory leaks, excessive GC, heap exhaustion
* **CPU Intensive**: Inefficient algorithms, excessive loops
* **I/O Bottlenecks**: Database queries, file operations, network calls
* **Threading Issues**: Synchronization overhead, thread contention
* **JVM Configuration**: Inappropriate heap size, GC settings
* **Database**: Slow queries, missing indexes, connection pooling
* **Caching**: Lack of caching or cache misses

```java
// Common bottleneck examples
public class PerformanceBottlenecks {
    
    // Memory leak - static collection grows indefinitely
    private static List<String> cache = new ArrayList<>();
    
    // CPU intensive - inefficient algorithm
    public boolean isPrime(int n) {
        for (int i = 2; i < n; i++) { // O(n) instead of O(√n)
            if (n % i == 0) return false;
        }
        return true;
    }
    
    // I/O bottleneck - N+1 query problem
    public List<OrderDto> getOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
            .map(order -> {
                Customer customer = customerRepository.findById(order.getCustomerId()); // N queries
                return new OrderDto(order, customer);
            }).collect(Collectors.toList());
    }
}
```

---

### Question 333: How do you optimize Java code for performance?

**Answer (40 seconds):**
* **Algorithm Optimization**: Use efficient data structures and algorithms
* **Memory Management**: Avoid object creation in loops, use object pools
* **Caching**: Cache expensive computations and database results
* **Lazy Loading**: Load data only when needed
* **Batch Operations**: Process data in batches instead of one-by-one
* **Asynchronous Processing**: Use CompletableFuture for non-blocking operations
* **Database Optimization**: Use proper indexes, optimize queries

```java
// Performance optimization examples
@Service
public class OptimizedUserService {
    
    // Cache expensive operations
    @Cacheable("users")
    public User findById(Long id) {
        return userRepository.findById(id);
    }
    
    // Batch processing instead of individual operations
    public void updateUsers(List<User> users) {
        userRepository.saveAll(users); // Batch instead of individual saves
    }
    
    // Asynchronous processing
    @Async
    public CompletableFuture<String> processAsync(String data) {
        // Long-running operation
        return CompletableFuture.completedFuture(processData(data));
    }
    
    // Efficient string concatenation
    public String buildMessage(List<String> parts) {
        return String.join(", ", parts); // Instead of += in loop
    }
}
```

---

### Question 334: What is profiling in Java?

**Answer (30 seconds):**
* Process of analyzing application performance to identify bottlenecks
* **CPU Profiling**: Identifies methods consuming most CPU time
* **Memory Profiling**: Tracks memory allocation and garbage collection
* **Thread Profiling**: Analyzes thread behavior and synchronization
* **Tools**: JProfiler, YourKit, VisualVM, Java Flight Recorder
* **Sampling vs Instrumentation**: Different profiling approaches

```java
// Java Flight Recorder (JFR) profiling
// JVM flags for profiling
// -XX:+FlightRecorder
// -XX:StartFlightRecording=duration=60s,filename=profile.jfr

@Component
public class ProfiledService {
    
    // Custom JFR event
    @JfrEvent(name = "UserOperation")
    public void processUser(User user) {
        // Method will be tracked in JFR
        expensiveOperation(user);
    }
    
    // Method that might need profiling
    public List<String> processLargeDataset(List<String> data) {
        return data.stream()
            .filter(this::isValid)
            .map(this::transform)
            .collect(Collectors.toList());
    }
}
```

---

### Question 335: What is JVM tuning?

**Answer (35 seconds):**
* Process of optimizing JVM parameters for better performance
* **Heap Size**: -Xms (initial) and -Xmx (maximum) heap size
* **Garbage Collection**: Choose appropriate GC algorithm
* **Thread Stack**: -Xss for thread stack size
* **Metaspace**: -XX:MetaspaceSize for class metadata
* **GC Tuning**: -XX:NewRatio, -XX:SurvivorRatio for generation sizes
* **Monitoring**: Enable GC logging and JFR

```bash
# Common JVM tuning parameters
java -Xms2g -Xmx4g \
     -XX:+UseG1GC \
     -XX:MaxGCPauseMillis=200 \
     -XX:+PrintGC \
     -XX:+PrintGCDetails \
     -XX:+PrintGCTimeStamps \
     -XX:+HeapDumpOnOutOfMemoryError \
     -XX:HeapDumpPath=/tmp/heapdump.hprof \
     -jar myapp.jar

# G1GC tuning for low latency
-XX:+UseG1GC
-XX:MaxGCPauseMillis=100
-XX:G1HeapRegionSize=16m
```

---

### Question 336: What are the JVM parameters for performance tuning?

**Answer (40 seconds):**
* **Memory**: -Xms, -Xmx for heap; -XX:NewRatio for young/old generation
* **Garbage Collection**: -XX:+UseG1GC, -XX:+UseZGC, -XX:+UseConcMarkSweepGC
* **GC Tuning**: -XX:MaxGCPauseMillis, -XX:GCTimeRatio
* **Compilation**: -XX:+TieredCompilation, -XX:CompileThreshold
* **Monitoring**: -XX:+PrintGC, -XX:+FlightRecorder
* **Debug**: -XX:+HeapDumpOnOutOfMemoryError

```bash
# Performance-focused JVM parameters
# For high-throughput applications
-Xms8g -Xmx8g
-XX:+UseParallelGC
-XX:ParallelGCThreads=8
-XX:+UseCompressedOops

# For low-latency applications
-Xms4g -Xmx4g
-XX:+UseZGC
-XX:+UnlockExperimentalVMOptions

# For microservices
-Xms512m -Xmx1g
-XX:+UseG1GC
-XX:MaxGCPauseMillis=50
-XX:+UseStringDeduplication
```

---

### Question 337: What is memory profiling?

**Answer (30 seconds):**
* Analysis of application memory usage patterns and allocation
* **Heap Analysis**: Object allocation, retention, and garbage collection
* **Memory Leaks**: Identify objects that aren't being garbage collected
* **Allocation Patterns**: Track where and how objects are created
* **Tools**: Eclipse MAT, JProfiler, VisualVM, JConsole
* **Heap Dumps**: Snapshots of memory for offline analysis

```java
// Memory profiling techniques
@Component
public class MemoryProfiledService {
    
    // Monitor memory usage
    public void checkMemoryUsage() {
        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage heapUsage = memoryBean.getHeapMemoryUsage();
        
        long used = heapUsage.getUsed();
        long max = heapUsage.getMax();
        double percentage = (double) used / max * 100;
        
        if (percentage > 80) {
            logger.warn("High memory usage: {}%", percentage);
        }
    }
    
    // Generate heap dump programmatically
    public void generateHeapDump() throws Exception {
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        HotSpotDiagnosticMXBean mxBean = ManagementFactory.newPlatformMXBeanProxy(
            server, "com.sun.management:type=HotSpotDiagnostic", HotSpotDiagnosticMXBean.class);
        mxBean.dumpHeap("/tmp/heapdump.hprof", true);
    }
}
```

---

### Question 338: What is CPU profiling?

**Answer (30 seconds):**
* Analysis of CPU usage to identify performance hotspots
* **Method Profiling**: Time spent in each method
* **Call Tree**: Method call hierarchy and execution paths
* **Sampling**: Periodic snapshots of thread stacks
* **Instrumentation**: Detailed method entry/exit tracking
* **Flame Graphs**: Visual representation of CPU usage
* **Tools**: JProfiler, async-profiler, Java Flight Recorder

```java
// CPU profiling with custom timing
@Component
public class CpuProfiledService {
    
    @Timed(name = "user.processing.time", description = "Time spent processing users")
    public void processUsers(List<User> users) {
        users.parallelStream()
            .forEach(this::processUser);
    }
    
    // Manual timing for critical sections
    public String expensiveOperation(String input) {
        long startTime = System.nanoTime();
        try {
            // CPU-intensive operation
            return performComplexCalculation(input);
        } finally {
            long duration = System.nanoTime() - startTime;
            if (duration > 1_000_000_000) { // 1 second
                logger.warn("Slow operation took {}ms", duration / 1_000_000);
            }
        }
    }
}
```

---

### Question 339: What is application performance monitoring (APM)?

**Answer (35 seconds):**
* Comprehensive monitoring of application performance in production
* **Real-time Monitoring**: Live performance metrics and alerts
* **Distributed Tracing**: Track requests across microservices
* **Error Tracking**: Capture and analyze application errors
* **User Experience**: Monitor real user interactions and page loads
* **Infrastructure**: Server resources, database performance
* **Tools**: New Relic, AppDynamics, Dynatrace, Elastic APM

```java
// APM integration with Micrometer
@Configuration
public class ApmConfig {
    
    @Bean
    public MeterRegistry meterRegistry() {
        return new PrometheusMeterRegistry(PrometheusConfig.DEFAULT);
    }
    
    @Bean
    public TimedAspect timedAspect(MeterRegistry registry) {
        return new TimedAspect(registry);
    }
}

@RestController
public class MonitoredController {
    
    @Timed(name = "api.requests", description = "API request duration")
    @Counted(name = "api.calls", description = "API call count")
    @GetMapping("/api/data")
    public ResponseEntity<String> getData() {
        return ResponseEntity.ok("data");
    }
}
```

---

### Question 340: What is code profiling?

**Answer (25 seconds):**
* Detailed analysis of code execution to identify performance issues
* **Static Analysis**: Code review without execution
* **Dynamic Analysis**: Runtime performance measurement
* **Line-by-line**: Execution time per code line
* **Method Hotspots**: Most time-consuming methods
* **Call Graphs**: Method invocation patterns
* **IDE Integration**: Built-in profilers in IDEs

```java
// Code profiling with annotations
@Component
public class ProfiledCodeService {
    
    // Profile specific methods
    @Profile("development")
    @EventListener
    public void onMethodExecution(MethodExecutionEvent event) {
        if (event.getDuration() > 100) {
            logger.info("Slow method: {} took {}ms", 
                event.getMethodName(), event.getDuration());
        }
    }
    
    // Benchmark critical code sections
    @Benchmark
    public String optimizedStringOperation(List<String> items) {
        StringBuilder sb = new StringBuilder();
        for (String item : items) {
            sb.append(item).append(",");
        }
        return sb.toString();
    }
}
```

---

### Question 341: What is database optimization?

**Answer (35 seconds):**
* Techniques to improve database query performance and efficiency
* **Indexing**: Create indexes on frequently queried columns
* **Query Optimization**: Write efficient SQL queries
* **Connection Pooling**: Reuse database connections
* **Caching**: Cache query results and frequently accessed data
* **Normalization**: Proper database design to reduce redundancy
* **Partitioning**: Split large tables for better performance

```java
// Database optimization techniques
@Repository
public class OptimizedUserRepository {
    
    // Use indexes effectively
    @Query("SELECT u FROM User u WHERE u.email = :email") // Index on email
    User findByEmail(@Param("email") String email);
    
    // Batch operations
    @Modifying
    @Query("UPDATE User u SET u.lastLogin = :now WHERE u.id IN :ids")
    void updateLastLogin(@Param("ids") List<Long> ids, @Param("now") LocalDateTime now);
    
    // Pagination for large datasets
    @Query("SELECT u FROM User u ORDER BY u.createdAt DESC")
    Page<User> findAllUsers(Pageable pageable);
    
    // Fetch joins to avoid N+1 queries
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.orders WHERE u.id = :id")
    User findUserWithOrders(@Param("id") Long id);
}
```

---

### Question 342: What is query optimization?

**Answer (35 seconds):**
* Process of improving SQL query performance and execution time
* **Index Usage**: Ensure queries use appropriate indexes
* **Query Structure**: Avoid SELECT *, use specific columns
* **JOIN Optimization**: Use proper join types and order
* **WHERE Clauses**: Filter early to reduce dataset size
* **EXPLAIN Plans**: Analyze query execution plans
* **Avoid N+1**: Use batch queries and joins instead of loops

```java
// Query optimization examples
@Repository
public class OptimizedQueryRepository {
    
    // Bad: N+1 query problem
    // List<Order> orders = orderRepository.findAll();
    // orders.forEach(order -> order.getCustomer().getName()); // N queries
    
    // Good: Single query with join
    @Query("SELECT o FROM Order o JOIN FETCH o.customer")
    List<Order> findAllOrdersWithCustomers();
    
    // Use specific columns instead of SELECT *
    @Query("SELECT new com.example.UserDto(u.id, u.name, u.email) FROM User u")
    List<UserDto> findUserSummaries();
    
    // Optimize with proper WHERE conditions
    @Query("SELECT u FROM User u WHERE u.active = true AND u.createdAt > :date")
    List<User> findActiveUsersAfter(@Param("date") LocalDateTime date);
    
    // Use native query for complex optimizations
    @Query(value = "SELECT * FROM users u WHERE u.score > (SELECT AVG(score) FROM users)", 
           nativeQuery = true)
    List<User> findAboveAverageUsers();
}
```

---

### Question 343: What is lazy loading?

**Answer (30 seconds):**
* Design pattern that defers loading of data until it's actually needed
* **JPA/Hibernate**: Load related entities only when accessed
* **Performance**: Reduces initial load time and memory usage
* **N+1 Problem**: Can cause multiple queries if not handled properly
* **Proxy Objects**: Hibernate creates proxies for lazy-loaded entities
* **Best Practice**: Use fetch joins when you know you'll need the data

```java
// Lazy loading examples
@Entity
public class User {
    @Id
    private Long id;
    private String name;
    
    // Lazy loading - orders loaded only when accessed
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Order> orders;
    
    // Eager loading - always loaded with user
    @ManyToOne(fetch = FetchType.EAGER)
    private Department department;
}

@Service
public class UserService {
    
    // Lazy loading in action
    public void processUser(Long userId) {
        User user = userRepository.findById(userId);
        // Orders not loaded yet
        
        if (needsOrders(user)) {
            user.getOrders().size(); // Now orders are loaded
        }
    }
    
    // Avoid N+1 with explicit fetch
    public List<User> getUsersWithOrders() {
        return userRepository.findAllWithOrders(); // Single query with JOIN FETCH
    }
}
```

---

### Question 344: What is eager loading?

**Answer (25 seconds):**
* Loading strategy that fetches all related data immediately
* **JPA/Hibernate**: Load associated entities along with main entity
* **Performance Trade-off**: Higher initial load time but fewer queries later
* **Memory Usage**: Uses more memory upfront
* **Use Cases**: When you know you'll need the related data
* **Configuration**: FetchType.EAGER or explicit fetch joins

```java
// Eager loading examples
@Entity
public class Order {
    @Id
    private Long id;
    
    // Eager loading - customer always loaded with order
    @ManyToOne(fetch = FetchType.EAGER)
    private Customer customer;
    
    // Lazy loading - items loaded on demand
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<OrderItem> items;
}

@Repository
public class OrderRepository extends JpaRepository<Order, Long> {
    
    // Explicit eager loading with fetch join
    @Query("SELECT o FROM Order o JOIN FETCH o.customer JOIN FETCH o.items")
    List<Order> findAllOrdersWithDetails();
    
    // Conditional eager loading
    @EntityGraph(attributePaths = {"customer", "items"})
    @Query("SELECT o FROM Order o WHERE o.status = :status")
    List<Order> findByStatusWithDetails(@Param("status") OrderStatus status);
}
```

---

### Question 345: What is pagination?

**Answer (30 seconds):**
* Technique to split large datasets into smaller, manageable chunks
* **Performance**: Reduces memory usage and improves response time
* **User Experience**: Faster page loads and better navigation
* **Database**: Uses LIMIT/OFFSET or cursor-based pagination
* **Spring Data**: Pageable interface for easy implementation
* **Cursor Pagination**: More efficient for large datasets

```java
// Pagination implementation
@RestController
public class UserController {
    
    // Basic pagination
    @GetMapping("/users")
    public Page<User> getUsers(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size,
        @RequestParam(defaultValue = "id") String sortBy) {
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return userService.findAll(pageable);
    }
    
    // Cursor-based pagination for better performance
    @GetMapping("/users/cursor")
    public List<User> getUsersCursor(
        @RequestParam(required = false) Long lastId,
        @RequestParam(defaultValue = "20") int limit) {
        
        return userService.findUsersAfter(lastId, limit);
    }
}

@Repository
public class UserRepository extends JpaRepository<User, Long> {
    
    // Cursor pagination query
    @Query("SELECT u FROM User u WHERE (:lastId IS NULL OR u.id > :lastId) ORDER BY u.id")
    List<User> findUsersAfter(@Param("lastId") Long lastId, Pageable pageable);
}
```

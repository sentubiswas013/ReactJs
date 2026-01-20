# 🔹 Performance and Optimization

## Performance Optimization

### 306. How do you identify performance bottlenecks?

**Answer:** Use profiling tools like JProfiler or VisualVM to analyze CPU and memory usage, monitor application metrics with tools like Micrometer, analyze database query performance, check network latency, and use APM tools like New Relic or AppDynamics for comprehensive monitoring.

**Performance Identification Methods:**

**1. Profiling Tools:**
- **JProfiler** - Commercial profiler with detailed analysis
- **VisualVM** - Free profiler bundled with JDK
- **Java Flight Recorder (JFR)** - Low-overhead profiling
- **async-profiler** - Production-ready profiler

**2. Application Metrics:**
```java
@RestController
public class UserController {
    
    private final MeterRegistry meterRegistry;
    
    @GetMapping("/users")
    @Timed(name = "user.fetch.time", description = "Time to fetch users")
    public List<User> getUsers() {
        return userService.findAll();
    }
}
```

**3. Database Performance:**
- Query execution plans
- Slow query logs
- Database connection metrics
- Index usage analysis

**4. System Monitoring:**
- CPU utilization
- Memory usage patterns
- Network I/O metrics
- Disk I/O performance

---

### 307. What are common performance issues in Java applications?

**Answer:** Memory leaks causing OutOfMemoryError, inefficient database queries with N+1 problems, excessive garbage collection pauses, thread contention and deadlocks, inefficient algorithms with poor time complexity, and unnecessary object creation in loops.

**Common Performance Issues:**

**1. Memory Issues:**
- **Memory Leaks:** Objects not properly garbage collected
- **OutOfMemoryError:** Heap exhaustion
- **Excessive GC:** Too frequent garbage collection

**2. Database Problems:**
```java
// N+1 Query Problem (BAD)
List<User> users = userRepository.findAll();
for (User user : users) {
    List<Order> orders = orderRepository.findByUserId(user.getId()); // N queries
}

// Optimized (GOOD)
List<User> users = userRepository.findAllWithOrders(); // 1 query with JOIN
```

**3. Threading Issues:**
- **Deadlocks:** Circular waiting for locks
- **Thread Contention:** Too many threads competing for resources
- **Context Switching:** Excessive thread switching overhead

**4. Algorithm Inefficiency:**
```java
// Inefficient (O(n²))
for (int i = 0; i < list.size(); i++) {
    if (list.contains(target)) { // O(n) operation in loop
        // process
    }
}

// Efficient (O(n))
Set<String> set = new HashSet<>(list);
if (set.contains(target)) { // O(1) operation
    // process
}
```

---

### 308. How do you optimize database queries?

**Answer:** Use proper indexing on frequently queried columns, avoid N+1 query problems with eager loading, implement query pagination for large datasets, use prepared statements to prevent SQL parsing overhead, and analyze execution plans to identify slow operations.

**Database Optimization Strategies:**

**1. Indexing:**
```sql
-- Create index on frequently queried columns
CREATE INDEX idx_user_email ON users(email);
CREATE INDEX idx_order_user_date ON orders(user_id, created_date);
```

**2. Query Optimization:**
```java
// Use JOIN instead of multiple queries
@Query("SELECT u FROM User u JOIN FETCH u.orders WHERE u.active = true")
List<User> findActiveUsersWithOrders();

// Pagination for large datasets
@Query("SELECT u FROM User u WHERE u.createdDate > :date")
Page<User> findRecentUsers(@Param("date") LocalDate date, Pageable pageable);
```

**3. Prepared Statements:**
```java
// Prepared statement (cached and secure)
@Query("SELECT u FROM User u WHERE u.email = ?1 AND u.status = ?2")
User findByEmailAndStatus(String email, String status);
```

**4. Batch Operations:**
```java
@Modifying
@Query("UPDATE User u SET u.lastLogin = :now WHERE u.id IN :ids")
void updateLastLoginBatch(@Param("ids") List<Long> ids, @Param("now") LocalDateTime now);
```

---

### 309. What is connection pooling and why is it important?

**Answer:** Connection pooling maintains a cache of database connections that can be reused across requests. It's important because creating database connections is expensive, it reduces connection overhead, improves response times, and prevents connection exhaustion under high load.

**Connection Pooling Benefits:**

**1. Performance Improvement:**
- Eliminates connection creation overhead
- Reduces database server load
- Faster response times

**2. Resource Management:**
- Prevents connection exhaustion
- Controls maximum concurrent connections
- Efficient resource utilization

**HikariCP Configuration:**
```yaml
spring:
  datasource:
    hikari:
      maximum-pool-size: 20
      minimum-idle: 5
      connection-timeout: 30000
      idle-timeout: 600000
      max-lifetime: 1800000
      leak-detection-threshold: 60000
```

**Connection Pool Monitoring:**
```java
@Component
public class ConnectionPoolMonitor {
    
    @Autowired
    private HikariDataSource dataSource;
    
    @Scheduled(fixedRate = 30000)
    public void logPoolStats() {
        HikariPoolMXBean poolBean = dataSource.getHikariPoolMXBean();
        log.info("Active connections: {}, Idle connections: {}", 
                poolBean.getActiveConnections(), 
                poolBean.getIdleConnections());
    }
}
```

---

### 310. How do you optimize memory usage?

**Answer:** Use appropriate data structures for your use case, avoid memory leaks by properly closing resources, implement object pooling for expensive objects, use primitive collections when possible, and tune garbage collection settings for your application's memory patterns.

**Memory Optimization Techniques:**

**1. Appropriate Data Structures:**
```java
// Use ArrayList for indexed access
List<String> names = new ArrayList<>();

// Use LinkedList for frequent insertions/deletions
List<String> queue = new LinkedList<>();

// Use HashMap for key-value lookups
Map<String, User> userCache = new HashMap<>();

// Use primitive collections to avoid boxing
TIntList numbers = new TIntArrayList(); // Trove library
```

**2. Resource Management:**
```java
// Always close resources
try (FileInputStream fis = new FileInputStream("file.txt");
     BufferedReader reader = new BufferedReader(new InputStreamReader(fis))) {
    // Use resources
} // Automatically closed
```

**3. Object Pooling:**
```java
@Component
public class ExpensiveObjectPool {
    
    private final Queue<ExpensiveObject> pool = new ConcurrentLinkedQueue<>();
    
    public ExpensiveObject borrowObject() {
        ExpensiveObject obj = pool.poll();
        return obj != null ? obj : new ExpensiveObject();
    }
    
    public void returnObject(ExpensiveObject obj) {
        obj.reset();
        pool.offer(obj);
    }
}
```

**4. Memory Monitoring:**
```java
MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
MemoryUsage heapUsage = memoryBean.getHeapMemoryUsage();
long usedMemory = heapUsage.getUsed();
long maxMemory = heapUsage.getMax();
```

---

### 311. What is caching and when should you use it?

**Answer:** Caching stores frequently accessed data in fast storage to reduce expensive operations. Use it for expensive database queries, external API calls, computed results, and static content. Implement when read operations significantly outnumber writes and data doesn't change frequently.

**When to Use Caching:**

**1. Expensive Operations:**
- Database queries with complex joins
- External API calls
- Heavy computational results
- File system operations

**2. Read-Heavy Workloads:**
- High read-to-write ratio
- Frequently accessed data
- Static or slowly changing data

**Spring Cache Example:**
```java
@Service
public class UserService {
    
    @Cacheable(value = "users", key = "#id")
    public User findById(Long id) {
        return userRepository.findById(id);
    }
    
    @CacheEvict(value = "users", key = "#user.id")
    public User updateUser(User user) {
        return userRepository.save(user);
    }
    
    @Cacheable(value = "expensiveOperation", key = "#param")
    public String expensiveOperation(String param) {
        // Simulate expensive operation
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "Result for " + param;
    }
}
```

**Cache Configuration:**
```yaml
spring:
  cache:
    type: caffeine
    caffeine:
      spec: maximumSize=1000,expireAfterWrite=10m
```

---

### 312. What are the different caching strategies?

**Answer:** Cache-aside where application manages cache, write-through that writes to cache and database simultaneously, write-behind that writes to cache first then database asynchronously, and refresh-ahead that proactively refreshes cache before expiration.

**Caching Strategies:**

**1. Cache-Aside (Lazy Loading):**
```java
public User getUser(Long id) {
    User user = cache.get(id);
    if (user == null) {
        user = database.findById(id);
        cache.put(id, user);
    }
    return user;
}
```

**2. Write-Through:**
```java
public void updateUser(User user) {
    database.save(user);        // Write to database first
    cache.put(user.getId(), user); // Then update cache
}
```

**3. Write-Behind (Write-Back):**
```java
public void updateUser(User user) {
    cache.put(user.getId(), user); // Write to cache first
    // Asynchronously write to database later
    asyncWriter.scheduleWrite(user);
}
```

**4. Refresh-Ahead:**
```java
@Scheduled(fixedRate = 300000) // Refresh every 5 minutes
public void refreshCache() {
    for (String key : cache.keySet()) {
        if (cache.isNearExpiry(key)) {
            Object freshData = database.findByKey(key);
            cache.put(key, freshData);
        }
    }
}
```

**Strategy Comparison:**

| Strategy | Consistency | Performance | Complexity |
|----------|-------------|-------------|------------|
| Cache-Aside | High | Good | Low |
| Write-Through | High | Medium | Medium |
| Write-Behind | Eventual | High | High |
| Refresh-Ahead | Good | High | Medium |

---

### 313. How do you optimize garbage collection?

**Answer:** Choose appropriate garbage collector for your use case, tune heap sizes based on application needs, minimize object creation in hot paths, use object pooling for expensive objects, and monitor GC logs to identify collection patterns and adjust accordingly.

**GC Optimization Strategies:**

**1. Choose Right GC:**
```bash
# G1GC for balanced latency and throughput
-XX:+UseG1GC -XX:MaxGCPauseMillis=200

# ZGC for ultra-low latency
-XX:+UseZGC

# Parallel GC for high throughput
-XX:+UseParallelGC
```

**2. Heap Tuning:**
```bash
# Set initial and maximum heap size
-Xms2g -Xmx4g

# Tune young generation
-XX:NewRatio=3  # Old:Young = 3:1
-XX:SurvivorRatio=8  # Eden:Survivor = 8:1
```

**3. Minimize Object Creation:**
```java
// Avoid creating objects in loops
StringBuilder sb = new StringBuilder(); // Create once
for (String item : items) {
    sb.append(item).append(","); // Reuse
}

// Use object pools for expensive objects
private final ObjectPool<ExpensiveObject> pool = new GenericObjectPool<>(factory);
```

**4. GC Monitoring:**
```bash
# Enable GC logging
-Xlog:gc*:gc.log:time,tags

# Monitor with JVM flags
-XX:+PrintGCDetails -XX:+PrintGCTimeStamps
```

---

### 314. What is profiling and what tools do you use?

**Answer:** Profiling analyzes application performance to identify bottlenecks. Use JProfiler for comprehensive analysis, VisualVM for free profiling, Java Flight Recorder for low-overhead monitoring, and async-profiler for production profiling with minimal impact.

**Profiling Tools:**

**1. JProfiler (Commercial):**
- CPU and memory profiling
- Thread analysis
- Database profiling
- Real-time monitoring

**2. VisualVM (Free):**
```bash
# Start with JVM flags
-Dcom.sun.management.jmxremote
-Dcom.sun.management.jmxremote.port=9999
-Dcom.sun.management.jmxremote.authenticate=false
```

**3. Java Flight Recorder:**
```bash
# Enable JFR
-XX:+FlightRecorder
-XX:StartFlightRecording=duration=60s,filename=profile.jfr

# Analyze with JMC (Java Mission Control)
```

**4. async-profiler:**
```bash
# Profile CPU for 30 seconds
java -jar async-profiler.jar -e cpu -d 30 -f profile.html <pid>

# Profile memory allocations
java -jar async-profiler.jar -e alloc -d 30 -f alloc.html <pid>
```

**Application Performance Monitoring:**
```java
@Component
public class PerformanceMonitor {
    
    private final MeterRegistry meterRegistry;
    
    @EventListener
    public void handleMethodExecution(MethodExecutionEvent event) {
        Timer.Sample sample = Timer.start(meterRegistry);
        sample.stop(Timer.builder("method.execution")
                .tag("class", event.getClassName())
                .tag("method", event.getMethodName())
                .register(meterRegistry));
    }
}
```

---

### 315. How do you optimize multithreaded applications?

**Answer:** Minimize shared state and synchronization, use concurrent collections instead of synchronized ones, implement lock-free algorithms where possible, use thread pools instead of creating threads manually, and avoid blocking operations in critical paths by using asynchronous processing.

**Multithreading Optimization:**

**1. Minimize Synchronization:**
```java
// Use concurrent collections
private final Map<String, User> userCache = new ConcurrentHashMap<>();

// Avoid synchronized blocks where possible
private final AtomicLong counter = new AtomicLong();

public void increment() {
    counter.incrementAndGet(); // Lock-free
}
```

**2. Thread Pools:**
```java
@Configuration
public class ThreadPoolConfig {
    
    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(20);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("async-");
        return executor;
    }
}

@Async
public CompletableFuture<String> processAsync(String data) {
    // Asynchronous processing
    return CompletableFuture.completedFuture(processData(data));
}
```

**3. Lock-Free Programming:**
```java
public class LockFreeCounter {
    private final AtomicLong count = new AtomicLong(0);
    
    public long increment() {
        return count.incrementAndGet();
    }
    
    public long get() {
        return count.get();
    }
}
```

**4. Avoid Blocking Operations:**
```java
// Use non-blocking I/O
@Async
public CompletableFuture<String> fetchDataAsync(String url) {
    return webClient.get()
            .uri(url)
            .retrieve()
            .bodyToMono(String.class)
            .toFuture();
}

// Use reactive streams
public Flux<User> processUsers() {
    return userRepository.findAll()
            .flatMap(this::enrichUser)
            .subscribeOn(Schedulers.parallel());
}
```

**Performance Monitoring:**
```java
// Monitor thread pool metrics
@Component
public class ThreadPoolMonitor {
    
    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;
    
    @Scheduled(fixedRate = 30000)
    public void logThreadPoolStats() {
        log.info("Active threads: {}, Pool size: {}, Queue size: {}",
                taskExecutor.getActiveCount(),
                taskExecutor.getPoolSize(),
                taskExecutor.getThreadPoolExecutor().getQueue().size());
    }
}
```

---

## Summary

Performance tuning requires systematic identification and optimization of bottlenecks:

**Identification Methods:**
- **Profiling Tools:** JProfiler, VisualVM, JFR, async-profiler
- **Monitoring:** Application metrics, database performance, system resources
- **Analysis:** CPU usage, memory patterns, thread behavior

**Common Optimizations:**
- **Database:** Proper indexing, query optimization, connection pooling
- **Memory:** Appropriate data structures, resource management, GC tuning
- **Caching:** Strategic caching with appropriate strategies
- **Threading:** Concurrent collections, thread pools, lock-free algorithms

**Best Practices:**
- Profile before optimizing
- Focus on actual bottlenecks, not perceived ones
- Measure performance improvements
- Consider trade-offs between different optimization approaches
- Monitor production performance continuously

**Key Tools:**
- **Profilers:** For detailed performance analysis
- **APM Tools:** For production monitoring
- **Metrics Libraries:** For application instrumentation
- **Load Testing:** For performance validation under stress
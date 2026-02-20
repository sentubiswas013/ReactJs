# ✅ 15) Performance & Optimization and Troubleshooting

## 183. How do you identify performance bottlenecks?

**Definition:** Performance bottlenecks are identified by monitoring application metrics, analyzing logs, profiling code execution, and measuring response times to find slow operations.

**Example:**
```java
@RestController
public class UserController {
    
    @GetMapping("/users")
    public List<User> getUsers() {
        long startTime = System.currentTimeMillis();
        
        List<User> users = userService.findAll();
        
        long duration = System.currentTimeMillis() - startTime;
        log.info("Query took {} ms", duration);
        
        return users;
    }
}

// Use Spring Boot Actuator metrics
@Component
public class PerformanceMonitor {
    private final MeterRegistry registry;
    
    public void trackOperation(String operation, Runnable task) {
        Timer.Sample sample = Timer.start(registry);
        task.run();
        sample.stop(Timer.builder(operation).register(registry));
    }
}
```

---

## 184. What tools do you use for profiling (JProfiler, VisualVM)?

**Definition:** Profiling tools analyze application performance by monitoring CPU usage, memory allocation, thread activity, and method execution times.

**Example:**
```java
// Enable JMX for VisualVM connection
// Add JVM arguments: -Dcom.sun.management.jmxremote

// Using Java Flight Recorder
public class ProfilerExample {
    public static void main(String[] args) {
        // Start JFR: java -XX:StartFlightRecording=duration=60s,filename=recording.jfr
        
        performHeavyOperation();
    }
    
    private static void performHeavyOperation() {
        for (int i = 0; i < 1000000; i++) {
            String s = new String("test" + i);
        }
    }
}

// VisualVM: Connect to running JVM and analyze CPU/Memory
// JProfiler: Attach to process and profile method calls
```

---

## 185. How do you optimize database queries?

**Definition:** Database query optimization involves using indexes, avoiding N+1 queries, using proper joins, limiting result sets, and analyzing query execution plans.

**Example:**
```java
// Bad - N+1 Query Problem
@Entity
public class Order {
    @OneToMany(fetch = FetchType.LAZY)
    private List<OrderItem> items;
}

List<Order> orders = orderRepository.findAll();
for (Order order : orders) {
    order.getItems().size(); // N+1 queries!
}

// Good - Use JOIN FETCH
@Query("SELECT o FROM Order o JOIN FETCH o.items")
List<Order> findAllWithItems();

// Use Pagination
Pageable pageable = PageRequest.of(0, 20);
Page<Order> orders = orderRepository.findAll(pageable);

// Use Indexes
@Entity
@Table(indexes = @Index(name = "idx_email", columnList = "email"))
public class User {
    @Column(unique = true)
    private String email;
}
```

---

## 186. What is connection pooling (HikariCP configuration)?

**Definition:** Connection pooling maintains a pool of reusable database connections to avoid the overhead of creating new connections for each request.

**Example:**
```yaml
# application.yml
spring:
  datasource:
    hikari:
      maximum-pool-size: 10
      minimum-idle: 5
      connection-timeout: 30000
      idle-timeout: 600000
      max-lifetime: 1800000
```

```java
@Configuration
public class DataSourceConfig {
    
    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/mydb");
        config.setUsername("user");
        config.setPassword("pass");
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(5);
        return new HikariDataSource(config);
    }
}
```

---

## 187. How do you size thread pools?

**Definition:** Thread pool sizing depends on workload type: CPU-bound tasks use cores+1, I/O-bound tasks use higher counts based on blocking time ratio.

**Example:**
```java
@Configuration
public class ThreadPoolConfig {
    
    // CPU-bound tasks
    @Bean
    public Executor cpuBoundExecutor() {
        int cores = Runtime.getRuntime().availableProcessors();
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(cores);
        executor.setMaxPoolSize(cores + 1);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("cpu-");
        executor.initialize();
        return executor;
    }
    
    // I/O-bound tasks
    @Bean
    public Executor ioBoundExecutor() {
        int cores = Runtime.getRuntime().availableProcessors();
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(cores * 2);
        executor.setMaxPoolSize(cores * 4);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("io-");
        executor.initialize();
        return executor;
    }
}
```

---

## 188. Horizontal vs vertical scaling.

**Definition:** Vertical scaling adds more resources (CPU, RAM) to existing servers. Horizontal scaling adds more servers to distribute load.

**Example:**
```java
// Vertical Scaling - Single instance with more resources
// JVM args: -Xmx8g -Xms8g (increase heap)

// Horizontal Scaling - Multiple instances with load balancer
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

// application.yml for multiple instances
server:
  port: ${PORT:8080}  # Instance 1: 8080, Instance 2: 8081

// Nginx Load Balancer config
/*
upstream backend {
    server localhost:8080;
    server localhost:8081;
    server localhost:8082;
}
*/

// Stateless design for horizontal scaling
@RestController
public class StatelessController {
    // No instance variables storing state
    // Use Redis/Database for shared state
}
```

---

## 189. How do you optimize JVM for production?

**Definition:** JVM optimization involves tuning heap size, garbage collector selection, and monitoring GC behavior for optimal performance.

**Example:**
```bash
# Production JVM flags
java -Xms4g -Xmx4g \
     -XX:+UseG1GC \
     -XX:MaxGCPauseMillis=200 \
     -XX:+HeapDumpOnOutOfMemoryError \
     -XX:HeapDumpPath=/logs/heapdump.hprof \
     -XX:+PrintGCDetails \
     -XX:+PrintGCDateStamps \
     -Xloggc:/logs/gc.log \
     -jar application.jar
```

```java
// Monitor GC in code
@Component
public class GCMonitor {
    
    @PostConstruct
    public void monitorGC() {
        List<GarbageCollectorMXBean> gcBeans = ManagementFactory.getGarbageCollectorMXBeans();
        
        for (GarbageCollectorMXBean gcBean : gcBeans) {
            log.info("GC: {}, Count: {}, Time: {}ms", 
                gcBean.getName(), 
                gcBean.getCollectionCount(), 
                gcBean.getCollectionTime());
        }
    }
}
```

---

## 190. How do you troubleshoot memory leaks?

**Definition:** Memory leaks occur when objects are unintentionally retained in memory. Troubleshoot using heap dumps, analyzing object retention paths, and monitoring memory growth.

**Example:**
```java
// Common memory leak - Static collection
public class MemoryLeakExample {
    private static List<User> cache = new ArrayList<>(); // Never cleared!
    
    public void addUser(User user) {
        cache.add(user); // Memory leak
    }
}

// Fix - Use WeakHashMap or clear cache
public class FixedExample {
    private Map<String, User> cache = new WeakHashMap<>();
    
    @Scheduled(fixedRate = 3600000)
    public void clearCache() {
        cache.clear();
    }
}

// Analyze heap dump
// 1. Generate: jmap -dump:live,format=b,file=heap.bin <pid>
// 2. Analyze with Eclipse MAT or VisualVM
// 3. Look for objects with high retained heap
```

---

## 191. How do you analyze heap dumps and thread dumps?

**Definition:** Heap dumps show memory state and object allocation. Thread dumps show thread states and stack traces for deadlock detection.

**Example:**
```bash
# Generate heap dump
jmap -dump:live,format=b,file=heap.hprof <pid>

# Generate thread dump
jstack <pid> > thread_dump.txt

# Or use kill signal
kill -3 <pid>
```

```java
// Programmatic thread dump
public class ThreadDumpGenerator {
    
    public static String generateThreadDump() {
        StringBuilder dump = new StringBuilder();
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        
        for (ThreadInfo threadInfo : threadMXBean.dumpAllThreads(true, true)) {
            dump.append(threadInfo.toString());
        }
        
        return dump.toString();
    }
}

// Analyze for deadlocks
ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
long[] deadlockedThreads = threadMXBean.findDeadlockedThreads();
if (deadlockedThreads != null) {
    log.error("Deadlock detected!");
}
```

---

## 192. How do you reduce API response time?

**Definition:** Reduce API response time by optimizing queries, using caching, implementing async processing, reducing payload size, and using CDN for static content.

**Example:**
```java
// 1. Use Caching
@Cacheable("users")
public User getUser(Long id) {
    return userRepository.findById(id).orElse(null);
}

// 2. Async Processing
@Async
public CompletableFuture<List<Order>> getOrders() {
    return CompletableFuture.completedFuture(orderRepository.findAll());
}

// 3. Pagination
@GetMapping("/users")
public Page<User> getUsers(Pageable pageable) {
    return userRepository.findAll(pageable);
}

// 4. Database Optimization
@Query("SELECT new com.example.UserDTO(u.id, u.name) FROM User u")
List<UserDTO> findAllProjected(); // Fetch only needed fields

// 5. Compression
@Configuration
public class CompressionConfig {
    @Bean
    public FilterRegistrationBean<GzipFilter> gzipFilter() {
        FilterRegistrationBean<GzipFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new GzipFilter());
        return registration;
    }
}

// 6. Connection pooling (already configured in HikariCP)
```

---

## 193. What is reactive programming and when to use it?

**Definition:** Reactive programming is a non-blocking, asynchronous programming paradigm that handles streams of data and propagates changes efficiently.

**Example:**
```java
// Traditional blocking approach
@GetMapping("/users/{id}")
public User getUser(@PathVariable Long id) {
    return userService.findById(id); // Blocks thread
}

// Reactive approach with WebFlux
@GetMapping("/users/{id}")
public Mono<User> getUser(@PathVariable Long id) {
    return userService.findById(id); // Non-blocking
}

// Reactive repository
public interface UserRepository extends ReactiveCrudRepository<User, Long> {
    Flux<User> findByAge(int age);
}

// Reactive service
@Service
public class UserService {
    
    public Mono<User> getUser(Long id) {
        return userRepository.findById(id)
            .switchIfEmpty(Mono.error(new NotFoundException()));
    }
    
    public Flux<User> getAllUsers() {
        return userRepository.findAll()
            .filter(user -> user.isActive())
            .map(this::enrichUser);
    }
}

// Use reactive when:
// - High concurrency requirements
// - I/O-bound operations
// - Streaming data
// - Backpressure handling needed
```
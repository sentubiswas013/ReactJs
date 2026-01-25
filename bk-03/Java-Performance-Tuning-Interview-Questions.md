# 20. Java Performance Tuning 

## 1. How do you identify performance bottlenecks?

Performance bottlenecks are identified through systematic monitoring, profiling, and analysis of application metrics to find the slowest components affecting overall performance.

**Identification Methods:**
- **Application Performance Monitoring (APM):** Tools like New Relic, AppDynamics
- **Profiling tools:** JProfiler, VisualVM, YourKit
- **JVM monitoring:** JConsole, JVisualVM
- **Database monitoring:** Query execution times
- **Log analysis:** Response times and error patterns
- **Load testing:** Identify limits under stress

Start with high-level metrics, then drill down to specific components causing delays.

## 2. What are common performance issues in Java applications?

Java applications face several typical performance problems that can significantly impact user experience and system efficiency.

**Common Issues:**
- **Memory leaks:** Objects not garbage collected
- **Inefficient database queries:** N+1 queries, missing indexes
- **Poor caching strategy:** Repeated expensive operations
- **Blocking I/O operations:** Synchronous file/network calls
- **Inefficient algorithms:** O(n²) instead of O(n log n)
- **Excessive object creation:** Unnecessary garbage collection pressure
- **Thread contention:** Synchronized blocks causing bottlenecks

```java
// Performance anti-patterns
// 1. String concatenation in loops
String result = "";
for (int i = 0; i < 1000; i++) {
    result += "text"; // Creates new String objects
}

// Better approach
StringBuilder sb = new StringBuilder();
for (int i = 0; i < 1000; i++) {
    sb.append("text");
}
```

## 3. What is connection pooling and why is it important?

Connection pooling maintains a cache of database connections that can be reused across multiple requests, eliminating the overhead of creating and destroying connections repeatedly.

**Benefits:**
- **Reduced connection overhead:** Avoid expensive connection creation
- **Better resource utilization:** Limit concurrent connections
- **Improved response times:** Reuse existing connections
- **Database protection:** Prevent connection exhaustion
- **Scalability:** Handle more concurrent users

**Popular Connection Pools:**
- HikariCP (fastest)
- Apache DBCP2
- C3P0
- Tomcat JDBC Pool

```java
// HikariCP configuration
HikariConfig config = new HikariConfig();
config.setJdbcUrl("jdbc:mysql://localhost:3306/mydb");
config.setUsername("user");
config.setPassword("password");
config.setMaximumPoolSize(20);        // Max connections
config.setMinimumIdle(5);             // Min idle connections
config.setConnectionTimeout(30000);   // 30 seconds timeout

HikariDataSource dataSource = new HikariDataSource(config);
```

## 4. What is caching and when should you use it?

Caching stores frequently accessed data in fast storage to reduce expensive operations like database queries, API calls, or complex calculations.

**When to use caching:**
- **Expensive operations:** Database queries, API calls
- **Frequently accessed data:** User profiles, configuration
- **Rarely changing data:** Reference data, lookup tables
- **Computed results:** Complex calculations, reports

**Caching Levels:**
- **Application level:** In-memory caches (Caffeine, Guava)
- **Database level:** Query result caching
- **Distributed level:** Redis, Hazelcast
- **HTTP level:** Browser and CDN caching

```java
// Spring Boot caching example
@Service
public class UserService {
    
    @Cacheable("users")
    public User getUserById(Long id) {
        // Expensive database operation
        return userRepository.findById(id);
    }
    
    @CacheEvict("users")
    public void updateUser(User user) {
        userRepository.save(user);
    }
}
```

## 5. What are important JVM parameters?

JVM parameters control memory allocation, garbage collection, and runtime behavior. Proper tuning can significantly improve application performance.

**Memory Parameters:**
- **-Xms:** Initial heap size
- **-Xmx:** Maximum heap size
- **-XX:NewRatio:** Ratio of old/young generation
- **-XX:MaxMetaspaceSize:** Metaspace limit

**Garbage Collection:**
- **-XX:+UseG1GC:** Use G1 garbage collector
- **-XX:MaxGCPauseMillis:** Target pause time
- **-XX:+PrintGCDetails:** GC logging

**Performance:**
- **-server:** Server mode JIT compilation
- **-XX:+TieredCompilation:** Multi-level compilation

```bash
# Example JVM parameters for production
java -Xms2g -Xmx4g \
     -XX:+UseG1GC \
     -XX:MaxGCPauseMillis=200 \
     -XX:+PrintGCDetails \
     -XX:+PrintGCTimeStamps \
     -jar myapp.jar
```

## 6. How do you tune heap size?

Heap size tuning involves setting appropriate initial and maximum heap sizes based on application memory requirements and available system resources.

**Tuning Guidelines:**
- **Start conservative:** Begin with smaller heap, monitor usage
- **Monitor GC frequency:** Too small = frequent GC, too large = long pauses
- **Leave system memory:** Don't allocate all available RAM
- **Consider GC overhead:** Aim for <5% time in GC
- **Use monitoring tools:** Track heap utilization patterns

**Best Practices:**
- Set -Xms and -Xmx to same value in production
- Allocate 25-50% of system memory to heap
- Monitor actual usage before increasing

```bash
# Heap size examples
# Small application
java -Xms512m -Xmx1g MyApp

# Large application  
java -Xms4g -Xmx8g MyApp

# Monitor heap usage
jstat -gc <pid> 5s  # GC stats every 5 seconds
```

## 7. What is the difference between -Xms and -Xmx?

**-Xms (Initial Heap Size):**
- Sets starting heap size when JVM starts
- Minimum heap allocation
- JVM allocates this memory immediately

**-Xmx (Maximum Heap Size):**
- Sets maximum heap size JVM can use
- Upper limit for heap growth
- JVM can expand heap up to this limit

```bash
# Different initial and max heap
java -Xms1g -Xmx4g MyApp  # Start with 1GB, can grow to 4GB

# Same initial and max heap (recommended for production)
java -Xms2g -Xmx2g MyApp   # Fixed 2GB heap, no expansion overhead
```

Setting them equal in production eliminates heap expansion overhead and provides predictable memory usage.

## 8. How do you analyze heap dumps?

Heap dumps are snapshots of JVM memory that help identify memory leaks, analyze object usage, and understand memory allocation patterns.

**Analysis Tools:**
- **Eclipse MAT (Memory Analyzer Tool):** Most popular
- **VisualVM:** Built-in heap dump analyzer
- **JProfiler:** Commercial profiler
- **jhat:** Command-line heap analyzer (deprecated)

**Analysis Steps:**
1. **Generate heap dump:** jcmd, jmap, or automatic on OutOfMemoryError
2. **Load in analyzer:** Open dump file in MAT or VisualVM
3. **Find memory leaks:** Look for objects with unexpected retention
4. **Analyze object references:** Trace why objects aren't garbage collected

```bash
# Generate heap dump
jcmd <pid> GC.run_finalization
jcmd <pid> VM.gc
jmap -dump:format=b,file=heapdump.hprof <pid>

# Automatic heap dump on OOM
java -XX:+HeapDumpOnOutOfMemoryError \
     -XX:HeapDumpPath=/tmp/heapdump.hprof \
     MyApp
```

## 9. What is JIT compilation?

JIT (Just-In-Time) compilation is a runtime optimization where the JVM compiles frequently executed bytecode into native machine code for better performance.

**How JIT works:**
- **Interpretation:** Initially executes bytecode in interpreter
- **Profiling:** Monitors method execution frequency and patterns
- **Compilation:** Compiles hot methods to native code
- **Optimization:** Applies advanced optimizations based on runtime data

**JIT Optimizations:**
- **Inlining:** Embed method calls directly
- **Dead code elimination:** Remove unused code
- **Loop optimization:** Optimize frequently executed loops
- **Escape analysis:** Optimize object allocations

```java
// JIT compilation example
public class JITExample {
    public static void main(String[] args) {
        // This loop will trigger JIT compilation
        for (int i = 0; i < 100000; i++) {
            calculateSum(i); // Method becomes "hot" and gets compiled
        }
    }
    
    private static int calculateSum(int n) {
        return n * (n + 1) / 2; // Simple calculation
    }
}

// JIT compilation flags
java -XX:+PrintCompilation \      # Print compilation events
     -XX:CompileThreshold=1000 \  # Compilation threshold
     JITExample
```

JIT compilation is why Java applications often get faster over time as the JVM optimizes frequently used code paths.
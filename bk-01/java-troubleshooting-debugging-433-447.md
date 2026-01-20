# 🔵 29. Troubleshooting and Problem Solving
---
# 🔹  Common Issues

#### 433. What are common Java performance issues? (25 seconds)
* **Memory leaks** - Objects not being garbage collected properly
* **CPU bottlenecks** - Inefficient algorithms or blocking operations
* **Database issues** - Slow queries or connection pool exhaustion
* **Thread contention** - Multiple threads competing for resources

```java
// Memory leak example
public class LeakExample {
    private static List<String> cache = new ArrayList<>();
    
    public void addToCache(String data) {
        cache.add(data); // Never cleared - memory leak
    }
}
```

#### 434. What are common Java memory issues? (30 seconds)
* **OutOfMemoryError** - Heap space exhausted
* **Memory leaks** - Objects referenced but not used
* **Stack overflow** - Deep recursion or large local variables
* **Metaspace issues** - Too many classes loaded

```java
// Stack overflow example
public void recursiveMethod() {
    recursiveMethod(); // No base case - stack overflow
}

// Memory optimization
List<String> list = new ArrayList<>(1000); // Pre-size collections
```

#### 435. What are common Java concurrency issues? (35 seconds)
* **Race conditions** - Multiple threads accessing shared data
* **Deadlocks** - Threads waiting for each other indefinitely
* **Thread starvation** - Threads not getting CPU time
* **Data corruption** - Unsynchronized access to shared variables

```java
// Race condition fix
private volatile boolean flag = false;
private final Object lock = new Object();

public void safeMethod() {
    synchronized(lock) {
        // Thread-safe operation
        flag = !flag;
    }
}
```

#### 436. What are common Java deployment issues? (25 seconds)
* **ClassPath problems** - Missing or conflicting JAR files
* **Version conflicts** - Different library versions
* **Configuration errors** - Wrong environment settings
* **Permission issues** - File or network access denied

```java
// Check classpath at runtime
String classpath = System.getProperty("java.class.path");
System.out.println("Classpath: " + classpath);
```

#### 437. What are common Java security issues? (30 seconds)
* **SQL injection** - Unsanitized database queries
* **XSS attacks** - Unescaped user input in web apps
* **Insecure deserialization** - Untrusted object deserialization
* **Weak authentication** - Poor password policies

```java
// Prevent SQL injection
String sql = "SELECT * FROM users WHERE id = ?";
PreparedStatement stmt = conn.prepareStatement(sql);
stmt.setInt(1, userId);
```

#### 438. What are debugging strategies? (20 seconds)
* **Reproduce the issue** - Create minimal test case
* **Use logging** - Add strategic log statements
* **Debugger tools** - Step through code execution
* **Divide and conquer** - Isolate problem areas

```java
// Strategic logging
logger.debug("Processing user: {}, status: {}", userId, status);
```

#### 439. What are problem-solving methodologies? (25 seconds)
* **Define the problem** - Understand symptoms clearly
* **Gather information** - Logs, stack traces, environment
* **Form hypothesis** - Educated guess about root cause
* **Test and verify** - Implement fix and validate

#### 440. What are root cause analysis techniques? (30 seconds)
* **5 Whys technique** - Ask "why" five times
* **Fishbone diagram** - Categorize potential causes
* **Timeline analysis** - When did problem start
* **Change analysis** - What changed recently

```java
// Add diagnostic information
try {
    processData();
} catch (Exception e) {
    logger.error("Failed processing at step: {}, data: {}", 
                currentStep, data, e);
    throw e;
}
```

# 🔹 Advanced Debugging

#### 441. What is remote debugging setup? (25 seconds)
* **JVM parameters** - Enable remote debugging port
* **IDE configuration** - Connect to remote application
* **Security considerations** - Limit access to debug port
* **Network setup** - Ensure port accessibility

```java
// JVM remote debug parameters
-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005
```

#### 442. What is conditional breakpoints? (20 seconds)
* **Condition-based stopping** - Break only when condition is true
* **Reduce noise** - Skip irrelevant iterations
* **Variable inspection** - Check specific values
* **Performance debugging** - Break on performance thresholds

```java
// Conditional breakpoint example
for (int i = 0; i < 1000; i++) {
    processItem(i); // Break when i == 500
}
```

#### 443. What is hot code replacement? (25 seconds)
* **Runtime code changes** - Modify code without restart
* **Development efficiency** - Faster debugging cycles
* **JVM support** - Limited to method body changes
* **IDE integration** - Automatic deployment of changes

```java
// Method that can be hot-swapped
public String formatMessage(String input) {
    return "Updated: " + input; // Can change this without restart
}
```

#### 444. What is debugging multithreaded applications? (35 seconds)
* **Thread-specific breakpoints** - Break in specific threads
* **Synchronization issues** - Identify deadlocks and race conditions
* **Thread state inspection** - Monitor thread status
* **Timing problems** - Use thread dumps and logging

```java
// Thread debugging
Thread.currentThread().setName("ProcessorThread-" + id);
logger.debug("Thread {} entering critical section", 
            Thread.currentThread().getName());
```

#### 445. What is debugging performance issues? (30 seconds)
* **Profiling tools** - JProfiler, VisualVM, JConsole
* **Method timing** - Measure execution time
* **Memory analysis** - Track object allocation
* **CPU usage** - Identify hot spots

```java
// Simple performance measurement
long start = System.nanoTime();
performOperation();
long duration = System.nanoTime() - start;
logger.info("Operation took {} ms", duration / 1_000_000);
```

#### 446. What is heap dump analysis? (30 seconds)
* **Memory snapshots** - Capture heap state at specific time
* **Object analysis** - Find memory leaks and large objects
* **Reference chains** - Trace object relationships
* **Tools** - Eclipse MAT, JVisualVM, JProfiler

```java
// Generate heap dump programmatically
MBeanServer server = ManagementFactory.getPlatformMBeanServer();
HotSpotDiagnosticMXBean mxBean = ManagementFactory.newPlatformMXBeanProxy(
    server, "com.sun.management:type=HotSpotDiagnostic", 
    HotSpotDiagnosticMXBean.class);
mxBean.dumpHeap("/tmp/heapdump.hprof", true);
```

#### 447. What is thread dump analysis? (25 seconds)
* **Thread state analysis** - Identify blocked or waiting threads
* **Deadlock detection** - Find circular dependencies
* **CPU usage patterns** - Identify busy threads
* **Stack trace analysis** - Understand thread execution

```java
// Generate thread dump
ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();
ThreadInfo[] threadInfos = threadBean.dumpAllThreads(true, true);
for (ThreadInfo info : threadInfos) {
    System.out.println(info.getThreadName() + ": " + info.getThreadState());
}
```

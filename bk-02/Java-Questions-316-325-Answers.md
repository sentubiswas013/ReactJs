## JVM Performance Optimization

### 316. What are important JVM parameters?

**Answer:** Key parameters include -Xms and -Xmx for heap size, -XX:NewRatio for generational sizing, -XX:+UseG1GC for garbage collector selection, -XX:MaxGCPauseMillis for GC tuning, -Xss for stack size, and -XX:MetaspaceSize for metadata space configuration.

**Essential JVM Parameters:**

**Memory Management:**
```bash
-Xms2g                    # Initial heap size
-Xmx4g                    # Maximum heap size
-XX:NewRatio=3            # Old:Young generation ratio
-XX:MetaspaceSize=256m    # Initial metaspace size
-Xss1m                    # Thread stack size
```

**Garbage Collection:**
```bash
-XX:+UseG1GC                    # Use G1 garbage collector
-XX:MaxGCPauseMillis=200        # Target GC pause time
-XX:G1HeapRegionSize=16m        # G1 region size
-XX:+UseStringDeduplication     # Deduplicate strings
```

**Performance Monitoring:**
```bash
-XX:+FlightRecorder             # Enable JFR
-XX:+UnlockCommercialFeatures   # Unlock commercial features
-Xlog:gc*:gc.log:time,tags      # GC logging
```

**JIT Compilation:**
```bash
-XX:CompileThreshold=10000      # JIT compilation threshold
-XX:+TieredCompilation          # Enable tiered compilation
-XX:+AggressiveOpts             # Enable aggressive optimizations
```

---

### 317. How do you tune heap size?

**Answer:** Set -Xms for initial heap size and -Xmx for maximum heap size. Start with -Xms equal to -Xmx to avoid dynamic resizing overhead. Size should be 25-75% of available RAM, leaving memory for OS and other processes. Monitor GC frequency and adjust based on application memory patterns.

**Heap Sizing Guidelines:**

**Basic Sizing:**
```bash
# For 8GB RAM server
-Xms4g -Xmx4g    # Use 50% of available RAM

# For 16GB RAM server  
-Xms8g -Xmx8g    # Use 50% of available RAM

# For containers with 2GB limit
-Xms1g -Xmx1g    # Leave 1GB for OS and other processes
```

**Sizing Strategy:**
1. **Start Conservative:** Begin with 25% of available RAM
2. **Monitor GC:** Watch GC frequency and duration
3. **Adjust Gradually:** Increase if frequent GC, decrease if excessive memory
4. **Set Equal Values:** -Xms = -Xmx to avoid resizing overhead

**Monitoring Commands:**
```bash
# Check current heap usage
jstat -gc <pid> 1s

# Monitor heap over time
jstat -gccapacity <pid>

# Get heap dump for analysis
jcmd <pid> GC.run_finalization
jcmd <pid> VM.gc
```

**Heap Sizing Calculation:**
```java
// Monitor heap usage programmatically
MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
MemoryUsage heapUsage = memoryBean.getHeapMemoryUsage();

long used = heapUsage.getUsed();
long max = heapUsage.getMax();
double utilization = (double) used / max * 100;

System.out.printf("Heap utilization: %.2f%%\n", utilization);
```

---

### 318. What is the difference between -Xms and -Xmx?

**Answer:** -Xms sets the initial heap size when JVM starts, while -Xmx sets the maximum heap size the JVM can grow to. Setting them equal avoids heap expansion overhead. -Xms should be large enough to handle typical load, -Xmx should handle peak load without causing OutOfMemoryError.

**Parameter Comparison:**

| Parameter | Purpose | When Applied | Impact |
|-----------|---------|--------------|---------|
| **-Xms** | Initial heap size | JVM startup | Allocates memory upfront |
| **-Xmx** | Maximum heap size | Runtime limit | Prevents OutOfMemoryError |

**Configuration Examples:**

**Different Values (Dynamic Sizing):**
```bash
# Heap can grow from 1GB to 4GB
-Xms1g -Xmx4g

# Pros: Lower initial memory usage
# Cons: Heap expansion overhead, GC overhead
```

**Equal Values (Fixed Sizing):**
```bash
# Fixed 4GB heap
-Xms4g -Xmx4g

# Pros: No expansion overhead, predictable performance
# Cons: Higher initial memory usage
```

**Best Practices:**
```bash
# Production applications (recommended)
-Xms4g -Xmx4g

# Development/testing (memory conscious)
-Xms512m -Xmx2g

# Microservices (container optimized)
-Xms256m -Xmx1g
```

**Monitoring Heap Growth:**
```java
public class HeapMonitor {
    public static void logHeapUsage() {
        Runtime runtime = Runtime.getRuntime();
        long maxMemory = runtime.maxMemory();     // -Xmx value
        long totalMemory = runtime.totalMemory(); // Current heap size
        long freeMemory = runtime.freeMemory();   // Free in current heap
        
        System.out.printf("Max: %dMB, Total: %dMB, Used: %dMB\n",
            maxMemory / 1024 / 1024,
            totalMemory / 1024 / 1024,
            (totalMemory - freeMemory) / 1024 / 1024);
    }
}
```

---

### 319. How do you analyze heap dumps?

**Answer:** Use tools like Eclipse MAT, VisualVM, or JProfiler to analyze heap dumps. Look for memory leaks by identifying objects with high retention, analyze dominator trees to find memory hogs, check for duplicate strings and large arrays, and examine GC roots to understand object retention paths.

**Heap Dump Analysis Process:**

**1. Generate Heap Dump:**
```bash
# Automatic on OutOfMemoryError
-XX:+HeapDumpOnOutOfMemoryError
-XX:HeapDumpPath=/path/to/dumps/

# Manual heap dump
jcmd <pid> GC.run_finalization
jcmd <pid> VM.gc
jcmd <pid> VM.dump_heap /path/to/heap.hprof

# Using jmap
jmap -dump:format=b,file=heap.hprof <pid>
```

**2. Analysis Tools:**

**Eclipse MAT (Memory Analyzer Tool):**
- Dominator tree analysis
- Leak suspects report
- Object query language (OQL)
- Histogram view

**VisualVM:**
- Built-in heap dump viewer
- Object browser
- GC root analysis

**3. Key Analysis Areas:**

**Memory Leaks:**
```java
// Look for these patterns in heap dumps:
// 1. Large collections that keep growing
List<Object> cache = new ArrayList<>(); // Never cleared

// 2. Event listeners not removed
button.addActionListener(listener); // Listener holds reference

// 3. Static collections
public class Cache {
    private static Map<String, Object> cache = new HashMap<>(); // Never cleared
}
```

**Dominator Tree Analysis:**
- Identifies objects consuming most memory
- Shows retained heap size
- Helps find memory bottlenecks

**4. Common Issues to Look For:**
- **Duplicate Strings:** Use string deduplication
- **Large Arrays:** Check if necessary
- **Collection Growth:** Implement proper cleanup
- **Class Loader Leaks:** Ensure proper unloading

---

### 320. How do you analyze thread dumps?

**Answer:** Use tools like VisualVM, JStack, or Eclipse MAT to analyze thread dumps. Look for deadlocks in blocked threads, identify CPU-intensive threads, check thread states and stack traces, analyze lock contention patterns, and examine thread pool utilization to identify performance issues.

**Thread Dump Analysis:**

**1. Generate Thread Dump:**
```bash
# Using jstack
jstack <pid> > threads.dump

# Using jcmd
jcmd <pid> Thread.print > threads.dump

# Using kill signal (Linux/Mac)
kill -3 <pid>  # Output goes to application logs
```

**2. Thread States:**
```java
// Thread states to analyze:
NEW          // Thread created but not started
RUNNABLE     // Thread executing or ready to execute
BLOCKED      // Thread blocked waiting for monitor lock
WAITING      // Thread waiting indefinitely
TIMED_WAITING // Thread waiting for specified time
TERMINATED   // Thread completed execution
```

**3. Analysis Focus Areas:**

**Deadlock Detection:**
```
Found Java-level deadlock:
=============================
"Thread-1":
  waiting to lock monitor 0x00007f8b1c005208 (object 0x000000076ab62208, a java.lang.Object),
  which is held by "Thread-2"
"Thread-2":
  waiting to lock monitor 0x00007f8b1c005258 (object 0x000000076ab62218, a java.lang.Object),
  which is held by "Thread-1"
```

**CPU-Intensive Threads:**
```bash
# Find high CPU threads
top -H -p <pid>

# Match thread ID to dump
printf "%x\n" <thread_id>  # Convert to hex
# Search for hex ID in thread dump
```

**Lock Contention:**
```
"pool-1-thread-1" #10 prio=5 os_prio=0 tid=0x... nid=0x... waiting for monitor entry
   java.lang.Thread.State: BLOCKED (on object monitor)
   at com.example.Service.synchronizedMethod(Service.java:45)
   - waiting to lock <0x000000076ab62208> (a java.lang.Object)
```

**4. Analysis Tools:**
- **Eclipse MAT:** Thread dump analysis
- **VisualVM:** Real-time thread monitoring
- **JConsole:** Thread MBean monitoring
- **FastThread.io:** Online thread dump analyzer

---

### 321. What is JIT compilation?

**Answer:** JIT (Just-In-Time) compilation converts frequently executed bytecode into native machine code at runtime for better performance. The JVM identifies hot spots through profiling, compiles them to optimized native code, and can deoptimize if assumptions become invalid, providing adaptive optimization.

**JIT Compilation Process:**

**1. Execution Phases:**
```
Java Source → Bytecode → Interpretation → Profiling → JIT Compilation → Native Code
```

**2. Compilation Tiers:**
```bash
# Tiered compilation levels
Level 0: Interpreter
Level 1: C1 compiler (client compiler) - fast compilation
Level 2: C1 with limited profiling
Level 3: C1 with full profiling  
Level 4: C2 compiler (server compiler) - aggressive optimization
```

**3. JIT Configuration:**
```bash
# Enable tiered compilation
-XX:+TieredCompilation

# Set compilation threshold
-XX:CompileThreshold=10000

# Disable JIT (for comparison)
-Xint

# Force compilation
-Xcomp

# Print compilation details
-XX:+PrintCompilation
-XX:+UnlockDiagnosticVMOptions
-XX:+PrintInlining
```

**4. Optimization Techniques:**
- **Method Inlining:** Small methods embedded in callers
- **Loop Optimization:** Unrolling, vectorization
- **Escape Analysis:** Stack allocation for local objects
- **Dead Code Elimination:** Remove unused code paths
- **Constant Folding:** Compute constants at compile time

**5. Monitoring JIT:**
```java
// Monitor compilation programmatically
CompilationMXBean compilationBean = ManagementFactory.getCompilationMXBean();
long compilationTime = compilationBean.getTotalCompilationTime();
System.out.println("Total compilation time: " + compilationTime + "ms");
```

**6. Deoptimization:**
```java
// JIT can deoptimize when assumptions become invalid
public void processUser(User user) {
    // JIT assumes User is always CustomerUser
    if (user instanceof CustomerUser) {
        // Optimized path
    } else {
        // If AdminUser appears, deoptimization occurs
    }
}
```

---

### 322. How do you monitor JVM performance?

**Answer:** Use JConsole, VisualVM, or JFR for built-in monitoring, enable GC logging with -Xlog:gc, use APM tools like New Relic or AppDynamics, monitor key metrics like heap usage, GC frequency, thread count, and CPU utilization through JMX beans.

**JVM Monitoring Tools:**

**1. Built-in Tools:**

**JConsole:**
```bash
# Start JConsole
jconsole

# Connect to remote JVM
jconsole <hostname>:<port>

# Enable JMX
-Dcom.sun.management.jmxremote
-Dcom.sun.management.jmxremote.port=9999
-Dcom.sun.management.jmxremote.authenticate=false
-Dcom.sun.management.jmxremote.ssl=false
```

**Java Flight Recorder (JFR):**
```bash
# Enable JFR
-XX:+FlightRecorder

# Start recording
-XX:StartFlightRecording=duration=60s,filename=app.jfr

# Runtime control
jcmd <pid> JFR.start duration=60s filename=recording.jfr
jcmd <pid> JFR.stop
jcmd <pid> JFR.dump filename=dump.jfr
```

**2. Key Metrics to Monitor:**

**Memory Metrics:**
```java
MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
MemoryUsage heapUsage = memoryBean.getHeapMemoryUsage();
MemoryUsage nonHeapUsage = memoryBean.getNonHeapMemoryUsage();

// Heap utilization
double heapUtilization = (double) heapUsage.getUsed() / heapUsage.getMax() * 100;
```

**GC Metrics:**
```java
List<GarbageCollectorMXBean> gcBeans = ManagementFactory.getGarbageCollectorMXBeans();
for (GarbageCollectorMXBean gcBean : gcBeans) {
    long collections = gcBean.getCollectionCount();
    long time = gcBean.getCollectionTime();
    System.out.printf("GC %s: %d collections, %dms total\n", 
                     gcBean.getName(), collections, time);
}
```

**Thread Metrics:**
```java
ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();
int threadCount = threadBean.getThreadCount();
int peakThreadCount = threadBean.getPeakThreadCount();
long totalStartedThreads = threadBean.getTotalStartedThreadCount();
```

**3. GC Logging:**
```bash
# Java 11+ logging
-Xlog:gc*:gc.log:time,tags

# Java 8 logging
-XX:+PrintGC
-XX:+PrintGCDetails
-XX:+PrintGCTimeStamps
-Xloggc:gc.log
```

**4. Custom Monitoring:**
```java
@Component
public class JVMMonitor {
    
    private final MeterRegistry meterRegistry;
    
    @Scheduled(fixedRate = 30000)
    public void recordJVMMetrics() {
        // Memory metrics
        MemoryUsage heapUsage = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();
        Gauge.builder("jvm.memory.heap.used")
             .register(meterRegistry, heapUsage, MemoryUsage::getUsed);
        
        // Thread metrics
        ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();
        Gauge.builder("jvm.threads.live")
             .register(meterRegistry, threadBean, ThreadMXBean::getThreadCount);
    }
}
```

---

### 323. What are the different garbage collectors and when to use them?

**Answer:** Serial GC for single-threaded applications, Parallel GC for high throughput batch processing, G1GC for balanced latency and throughput with large heaps, ZGC and Shenandoah for ultra-low latency applications, and CMS for low-latency requirements though it's deprecated.

**Garbage Collector Comparison:**

**1. Serial GC:**
```bash
-XX:+UseSerialGC

# Use cases:
# - Single-core machines
# - Small applications (<100MB heap)
# - Client applications
# - Development/testing
```

**2. Parallel GC (Default in Java 8):**
```bash
-XX:+UseParallelGC
-XX:ParallelGCThreads=4

# Use cases:
# - Multi-core servers
# - Batch processing
# - High throughput applications
# - When pause time is not critical
```

**3. G1GC (Default in Java 9+):**
```bash
-XX:+UseG1GC
-XX:MaxGCPauseMillis=200
-XX:G1HeapRegionSize=16m

# Use cases:
# - Large heaps (>4GB)
# - Low-latency requirements
# - Balanced throughput and latency
# - Most modern applications
```

**4. ZGC (Java 11+):**
```bash
-XX:+UseZGC
-XX:+UnlockExperimentalVMOptions  # Java 11-14

# Use cases:
# - Ultra-low latency (<10ms pauses)
# - Very large heaps (multi-TB)
# - Real-time applications
# - Financial trading systems
```

**5. Shenandoah (OpenJDK):**
```bash
-XX:+UseShenandoahGC
-XX:+UnlockExperimentalVMOptions

# Use cases:
# - Low-latency applications
# - Large heaps
# - Consistent pause times
# - Alternative to ZGC
```

**GC Selection Matrix:**

| Requirement | Recommended GC | Configuration |
|-------------|----------------|---------------|
| **High Throughput** | Parallel GC | `-XX:+UseParallelGC` |
| **Low Latency** | G1GC | `-XX:+UseG1GC -XX:MaxGCPauseMillis=100` |
| **Ultra-Low Latency** | ZGC/Shenandoah | `-XX:+UseZGC` |
| **Small Heap** | Serial GC | `-XX:+UseSerialGC` |
| **Large Heap** | G1GC/ZGC | `-XX:+UseG1GC` or `-XX:+UseZGC` |

---

### 324. How do you tune garbage collection?

**Answer:** Choose appropriate GC algorithm based on requirements, set reasonable heap sizes, tune young generation size with -XX:NewRatio, adjust GC pause targets with -XX:MaxGCPauseMillis for G1, enable GC logging to monitor performance, and iterate based on actual application behavior and metrics.

**GC Tuning Process:**

**1. Baseline Measurement:**
```bash
# Enable comprehensive GC logging
-Xlog:gc*:gc.log:time,tags
-XX:+UseG1GC

# Monitor key metrics
# - GC frequency
# - Pause times
# - Throughput
# - Memory utilization
```

**2. G1GC Tuning:**
```bash
# Basic G1 configuration
-XX:+UseG1GC
-XX:MaxGCPauseMillis=200        # Target pause time
-XX:G1HeapRegionSize=16m        # Region size (1-32MB)
-XX:G1NewSizePercent=20         # Min young gen size
-XX:G1MaxNewSizePercent=80      # Max young gen size
-XX:G1MixedGCCountTarget=8      # Mixed GC cycle target
```

**3. Parallel GC Tuning:**
```bash
# Parallel GC configuration
-XX:+UseParallelGC
-XX:ParallelGCThreads=8         # GC thread count
-XX:MaxGCPauseMillis=500        # Target pause time
-XX:GCTimeRatio=19              # Throughput target (95%)
-XX:NewRatio=3                  # Old:Young = 3:1
```

**4. Monitoring and Analysis:**
```java
// GC monitoring utility
public class GCMonitor {
    
    public void analyzeGCPerformance() {
        List<GarbageCollectorMXBean> gcBeans = ManagementFactory.getGarbageCollectorMXBeans();
        
        for (GarbageCollectorMXBean gcBean : gcBeans) {
            String name = gcBean.getName();
            long collections = gcBean.getCollectionCount();
            long time = gcBean.getCollectionTime();
            
            if (collections > 0) {
                double avgTime = (double) time / collections;
                System.out.printf("GC %s: %d collections, avg %.2fms\n", 
                                name, collections, avgTime);
            }
        }
    }
}
```

**5. Tuning Guidelines:**

**Memory Sizing:**
```bash
# Heap sizing (start here)
-Xms4g -Xmx4g

# Young generation sizing
-XX:NewRatio=3              # Old:Young = 3:1
-XX:SurvivorRatio=8         # Eden:Survivor = 8:1
```

**Performance Targets:**
- **Throughput:** >95% application time (GCTimeRatio=19)
- **Latency:** <200ms pause times (MaxGCPauseMillis=200)
- **Frequency:** <10 GC/minute for major collections

**6. Common Tuning Scenarios:**

**High Allocation Rate:**
```bash
# Increase young generation
-XX:NewRatio=2
-XX:G1NewSizePercent=30
```

**Long Pause Times:**
```bash
# Reduce pause target
-XX:MaxGCPauseMillis=100
# Increase parallel threads
-XX:ParallelGCThreads=16
```

**Memory Pressure:**
```bash
# Increase heap size
-Xmx8g
# Enable string deduplication
-XX:+UseStringDeduplication
```

---

### 325. What is escape analysis?

**Answer:** Escape analysis is a JVM optimization that determines if objects can be allocated on the stack instead of heap when they don't "escape" the method scope. This eliminates heap allocation overhead, reduces GC pressure, and enables scalar replacement where objects are replaced with individual fields.

**Escape Analysis Concepts:**

**1. Object Escape Scenarios:**

**No Escape (Stack Allocation):**
```java
public void processData() {
    StringBuilder sb = new StringBuilder(); // Doesn't escape method
    sb.append("Hello");
    sb.append(" World");
    String result = sb.toString();
    System.out.println(result);
    // sb can be allocated on stack
}
```

**Method Escape (Heap Allocation):**
```java
public StringBuilder createBuilder() {
    StringBuilder sb = new StringBuilder(); // Escapes via return
    sb.append("Hello");
    return sb; // Must be heap allocated
}
```

**Thread Escape (Heap Allocation):**
```java
private StringBuilder globalBuilder;

public void processData() {
    StringBuilder sb = new StringBuilder(); // Escapes to field
    sb.append("Hello");
    this.globalBuilder = sb; // Must be heap allocated
}
```

**2. Optimization Benefits:**

**Stack Allocation:**
- No GC overhead
- Faster allocation/deallocation
- Better cache locality
- Automatic cleanup when method exits

**Scalar Replacement:**
```java
// Original code
public void calculatePoint() {
    Point p = new Point(10, 20); // Object creation
    int sum = p.x + p.y;         // Field access
    System.out.println(sum);
}

// After scalar replacement
public void calculatePoint() {
    int p_x = 10;                // Scalar variables
    int p_y = 20;                // No object creation
    int sum = p_x + p_y;         // Direct variable access
    System.out.println(sum);
}
```

**3. Enable/Monitor Escape Analysis:**
```bash
# Enable escape analysis (default in server mode)
-XX:+DoEscapeAnalysis

# Disable for comparison
-XX:-DoEscapeAnalysis

# Print escape analysis results
-XX:+PrintEscapeAnalysis
-XX:+UnlockDiagnosticVMOptions
```

**4. Code Patterns for Optimization:**

**Optimizable Pattern:**
```java
public String formatMessage(String name, int age) {
    StringBuilder sb = new StringBuilder(50); // Size hint helps
    sb.append("Name: ").append(name);
    sb.append(", Age: ").append(age);
    return sb.toString(); // Only string escapes, not StringBuilder
}
```

**Non-optimizable Pattern:**
```java
public StringBuilder getBuilder(String name) {
    StringBuilder sb = new StringBuilder(); // Escapes via return
    sb.append("Hello ").append(name);
    return sb; // StringBuilder must be heap allocated
}
```

**5. Monitoring Impact:**
```java
// Measure allocation rate
public class AllocationMonitor {
    
    public void measureAllocations() {
        ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();
        
        if (threadBean.isThreadAllocatedMemorySupported()) {
            long threadId = Thread.currentThread().getId();
            long before = threadBean.getThreadAllocatedBytes(threadId);
            
            // Execute code to measure
            performOperations();
            
            long after = threadBean.getThreadAllocatedBytes(threadId);
            long allocated = after - before;
            
            System.out.println("Allocated: " + allocated + " bytes");
        }
    }
}
```

**6. Limitations:**
- Only works in server mode (-server)
- Complex object graphs may not be optimized
- Virtual calls prevent some optimizations
- Synchronization prevents stack allocation

---

## Summary

JVM tuning requires understanding of memory management, garbage collection, and runtime optimizations:

**Key Areas:**
- **Memory Management:** Proper heap sizing with -Xms/-Xmx
- **Garbage Collection:** Choose appropriate GC algorithm and tune parameters
- **Performance Monitoring:** Use JFR, JConsole, and GC logging
- **JIT Optimization:** Understand compilation tiers and escape analysis

**Essential Parameters:**
- **Heap:** `-Xms4g -Xmx4g` for fixed sizing
- **GC:** `-XX:+UseG1GC -XX:MaxGCPauseMillis=200` for balanced performance
- **Monitoring:** `-XX:+FlightRecorder -Xlog:gc*:gc.log` for observability

**Tuning Process:**
1. **Baseline:** Measure current performance
2. **Identify:** Find bottlenecks through profiling
3. **Adjust:** Make incremental parameter changes
4. **Validate:** Measure impact of changes
5. **Iterate:** Repeat until performance goals met

**Best Practices:**
- Start with default settings and measure
- Make one change at a time
- Use production-like load for testing
- Monitor long-term trends, not just peak performance
- Document configuration changes and their impact
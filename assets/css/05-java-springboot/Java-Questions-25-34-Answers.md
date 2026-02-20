## 3) Java Memory Model & Garbage Collection

### 25. Explain JVM memory structure (Heap, Stack, Metaspace).

**Answer:**
The JVM memory is divided into several areas. The Heap is where all objects and instance variables are stored, shared across all threads, and managed by the garbage collector. The Stack stores method frames, local variables, and partial results for each thread - each thread has its own stack. Metaspace replaced PermGen in Java 8 and stores class metadata, static variables, and constant pool. Unlike PermGen, Metaspace uses native memory and can grow dynamically. The heap is further divided into Young Generation and Old Generation for efficient garbage collection.

**Example:**
```java
public class MemoryExample {
    static int staticVar = 100;  // Metaspace
    
    public void method() {
        int localVar = 10;       // Stack
        String str = new String("Hello"); // "Hello" in Heap, str reference in Stack
        
        // Object created in Heap (Young Generation - Eden space)
        Employee emp = new Employee();
    }
}

// JVM flags to configure memory
// -Xms512m (initial heap)
// -Xmx2g (max heap)
// -XX:MetaspaceSize=128m
```

---

### 26. What are Young Generation (Eden, Survivor) and Old Generation?

**Answer:**
The Heap is divided into Young Generation and Old Generation for generational garbage collection. Young Generation consists of Eden space and two Survivor spaces (S0 and S1). New objects are allocated in Eden. When Eden fills up, Minor GC occurs, moving surviving objects to a Survivor space. Objects that survive multiple GC cycles are promoted to Old Generation. This design is based on the observation that most objects die young. Young Generation uses fast collection algorithms, while Old Generation uses more thorough but slower algorithms.

**Example:**
```java
public class GenerationExample {
    public static void main(String[] args) {
        // Objects created in Eden space
        for (int i = 0; i < 1000; i++) {
            String temp = new String("temp" + i); // dies quickly
        }
        
        // Long-lived object - eventually moves to Old Gen
        List<String> cache = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            cache.add("data" + i);
        }
        
        // After multiple GC cycles, cache moves to Old Generation
    }
}

// JVM flags
// -XX:NewRatio=2 (Old:Young = 2:1)
// -XX:SurvivorRatio=8 (Eden:Survivor = 8:1)
```

---

### 27. Explain different GC algorithms (G1, CMS, ZGC, Parallel).

**Answer:**
Parallel GC uses multiple threads for both Young and Old generation collection, maximizing throughput but causing stop-the-world pauses. CMS (Concurrent Mark Sweep) performs most Old Gen collection concurrently with application threads, reducing pause times but using more CPU. G1 (Garbage First) divides heap into regions, collecting regions with most garbage first, balancing throughput and latency with predictable pause times. ZGC is a low-latency collector with pause times under 10ms, using colored pointers and load barriers, suitable for large heaps. Choose based on your latency and throughput requirements.

**Example:**
```java
// Enable different GC algorithms via JVM flags

// Parallel GC (default in Java 8) - high throughput
// -XX:+UseParallelGC

// G1 GC (default in Java 9+) - balanced
// -XX:+UseG1GC
// -XX:MaxGCPauseMillis=200

// CMS (deprecated in Java 9) - low latency
// -XX:+UseConcMarkSweepGC

// ZGC (Java 11+) - ultra-low latency
// -XX:+UseZGC
// -XX:ZCollectionInterval=5

public class GCExample {
    public static void main(String[] args) {
        // Application code - GC runs automatically
        List<byte[]> data = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            data.add(new byte[1024 * 1024]); // 1MB each
        }
    }
}
```

---

### 28. What is Minor GC vs Major GC vs Full GC?

**Answer:**
Minor GC occurs in Young Generation when Eden space fills up. It's fast because most objects in Young Gen are short-lived and can be quickly collected. Surviving objects move to Survivor spaces or get promoted to Old Generation. Major GC cleans the Old Generation and is slower because objects there are long-lived. Full GC collects both Young and Old generations, causing longer stop-the-world pauses. Full GC is triggered when Old Gen is full or explicitly called via System.gc(). Frequent Full GCs indicate memory issues or improper heap sizing.

**Example:**
```java
public class GCTypesExample {
    public static void main(String[] args) {
        // Triggers Minor GC frequently
        for (int i = 0; i < 1000000; i++) {
            String temp = new String("temp"); // short-lived
        }
        
        // Long-lived objects may trigger Major GC
        List<byte[]> longLived = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            longLived.add(new byte[1024 * 1024]);
        }
        
        // Avoid explicit GC calls
        // System.gc(); // triggers Full GC - not recommended
    }
}

// Monitor GC with flags
// -XX:+PrintGCDetails
// -XX:+PrintGCTimeStamps
// -Xlog:gc*
```

---

### 29. How do you identify and fix memory leaks?

**Answer:**
Memory leaks occur when objects are unintentionally retained in memory. Common causes include unclosed resources, static collections holding references, listeners not removed, and ThreadLocal variables not cleared. To identify leaks, monitor heap usage over time - if it continuously grows without dropping after GC, you likely have a leak. Use heap dumps to analyze which objects consume memory. Fix by ensuring proper resource cleanup, removing listeners, clearing collections, using weak references where appropriate, and cleaning up ThreadLocal variables.

**Example:**
```java
// Memory leak examples and fixes

// BAD - Static collection grows indefinitely
public class LeakyCache {
    private static Map<String, Object> cache = new HashMap<>();
    
    public void addToCache(String key, Object value) {
        cache.put(key, value); // never removed
    }
}

// GOOD - Use bounded cache or weak references
public class FixedCache {
    private Map<String, Object> cache = new WeakHashMap<>();
    
    public void addToCache(String key, Object value) {
        cache.put(key, value); // auto-removed when key is GC'd
    }
}

// BAD - Unclosed resources
public void readFile() throws IOException {
    FileInputStream fis = new FileInputStream("file.txt");
    // forgot to close
}

// GOOD - Try-with-resources
public void readFile() throws IOException {
    try (FileInputStream fis = new FileInputStream("file.txt")) {
        // auto-closed
    }
}
```

---

### 30. What tools do you use for memory analysis?

**Answer:**
For memory analysis, I use JVisualVM for real-time monitoring of heap, threads, and CPU usage. Eclipse MAT (Memory Analyzer Tool) is excellent for analyzing heap dumps to find memory leaks and large object retention. JProfiler and YourKit are commercial profilers offering advanced features. For command-line analysis, jmap generates heap dumps, jstat monitors GC statistics, and jcmd provides diagnostic commands. In production, I use APM tools like New Relic or Dynatrace. Java Flight Recorder provides low-overhead profiling data that can be analyzed with JMC (Java Mission Control).

**Example:**
```bash
# Command-line tools

# Generate heap dump
jmap -dump:live,format=b,file=heap.bin <pid>

# View heap histogram
jmap -histo <pid>

# Monitor GC statistics
jstat -gc <pid> 1000

# Get thread dump
jstack <pid> > threads.txt

# JVM diagnostic commands
jcmd <pid> GC.heap_info
jcmd <pid> VM.native_memory summary
```

```java
// Programmatic heap dump
import com.sun.management.HotSpotDiagnosticMXBean;
import java.lang.management.ManagementFactory;

public class HeapDumper {
    public static void dumpHeap(String filePath) throws Exception {
        HotSpotDiagnosticMXBean mxBean = ManagementFactory
            .getPlatformMXBean(HotSpotDiagnosticMXBean.class);
        mxBean.dumpHeap(filePath, true);
    }
}
```

---

### 31. Explain different types of `OutOfMemoryError`.

**Answer:**
Java throws different OutOfMemoryError types for various scenarios. "Java heap space" occurs when the heap is full and GC can't free enough memory - increase heap size or fix memory leaks. "GC overhead limit exceeded" means the JVM spends too much time in GC with little memory recovered. "Metaspace" happens when class metadata exceeds Metaspace limit - increase Metaspace or reduce class loading. "Unable to create new native thread" occurs when OS can't create more threads - reduce thread count or increase OS limits. "Direct buffer memory" relates to NIO direct buffers exceeding limit.

**Example:**
```java
// Different OOM scenarios

// 1. Heap space OOM
public class HeapOOM {
    public static void main(String[] args) {
        List<byte[]> list = new ArrayList<>();
        while (true) {
            list.add(new byte[1024 * 1024]); // OutOfMemoryError: Java heap space
        }
    }
}
// Fix: -Xmx2g

// 2. Metaspace OOM
public class MetaspaceOOM {
    public static void main(String[] args) {
        while (true) {
            // Dynamically generate classes
            // OutOfMemoryError: Metaspace
        }
    }
}
// Fix: -XX:MaxMetaspaceSize=512m

// 3. Thread creation OOM
public class ThreadOOM {
    public static void main(String[] args) {
        while (true) {
            new Thread(() -> {
                try { Thread.sleep(1000000); } catch (Exception e) {}
            }).start(); // OutOfMemoryError: unable to create new native thread
        }
    }
}
// Fix: Reduce threads or increase OS limits
```

---

### 32. What are JVM tuning parameters (`-Xms`, `-Xmx`, `-XX`)?

**Answer:**
JVM tuning parameters control memory and performance. -Xms sets initial heap size, -Xmx sets maximum heap size - setting them equal avoids heap resizing overhead. -Xss sets thread stack size. -XX flags are advanced options: -XX:NewRatio controls Young to Old Gen ratio, -XX:MaxMetaspaceSize limits Metaspace, -XX:+UseG1GC selects garbage collector. For production, set -Xms equal to -Xmx for predictable performance, choose appropriate GC algorithm, enable GC logging, and tune based on application behavior. Monitor and adjust based on actual usage patterns.

**Example:**
```bash
# Common JVM tuning parameters

# Heap sizing
-Xms2g                    # Initial heap 2GB
-Xmx2g                    # Max heap 2GB (same as Xms)
-Xss512k                  # Thread stack size

# GC selection and tuning
-XX:+UseG1GC              # Use G1 collector
-XX:MaxGCPauseMillis=200  # Target pause time
-XX:NewRatio=2            # Old:Young = 2:1
-XX:SurvivorRatio=8       # Eden:Survivor = 8:1

# Metaspace
-XX:MetaspaceSize=256m
-XX:MaxMetaspaceSize=512m

# GC logging (Java 9+)
-Xlog:gc*:file=gc.log:time,level,tags

# Performance tuning
-XX:+UseStringDeduplication  # Reduce String memory
-XX:+AlwaysPreTouch          # Touch memory at startup
-XX:+DisableExplicitGC       # Ignore System.gc()

# Example startup command
java -Xms2g -Xmx2g -XX:+UseG1GC -XX:MaxGCPauseMillis=200 \
     -Xlog:gc*:file=gc.log -jar myapp.jar
```

---

### 33. Explain strong, soft, weak, and phantom references.

**Answer:**
Strong references are normal references that prevent garbage collection - objects are never collected while strongly referenced. Soft references are collected only when memory is low, useful for memory-sensitive caches. Weak references don't prevent collection - objects are collected in the next GC cycle if only weakly referenced, used in WeakHashMap. Phantom references are enqueued after finalization but before memory reclamation, used for cleanup actions. Reference queues notify when references are cleared. Use soft references for caches, weak references for canonical mappings, and phantom references for resource cleanup.

**Example:**
```java
import java.lang.ref.*;

public class ReferenceExample {
    public static void main(String[] args) {
        // Strong reference - never GC'd while referenced
        Object strong = new Object();
        
        // Soft reference - GC'd when memory is low
        SoftReference<Object> soft = new SoftReference<>(new Object());
        Object obj1 = soft.get(); // may return null if GC'd
        
        // Weak reference - GC'd in next cycle
        WeakReference<Object> weak = new WeakReference<>(new Object());
        Object obj2 = weak.get(); // may return null after GC
        
        // Phantom reference - for cleanup actions
        ReferenceQueue<Object> queue = new ReferenceQueue<>();
        PhantomReference<Object> phantom = new PhantomReference<>(new Object(), queue);
        // phantom.get() always returns null
        
        // Cache using soft references
        Map<String, SoftReference<byte[]>> cache = new HashMap<>();
        cache.put("key", new SoftReference<>(new byte[1024]));
    }
}
```

---

### 34. How does G1 Garbage Collector work?

**Answer:**
G1 (Garbage First) divides the heap into equal-sized regions instead of contiguous Young and Old generations. Each region can be Eden, Survivor, or Old. G1 tracks live data in each region and prioritizes collecting regions with most garbage first, hence the name. It performs concurrent marking to identify live objects, then does mixed collections that collect both Young and Old regions. G1 aims to meet pause time goals by collecting only a subset of regions in each cycle. It uses remembered sets to track cross-region references, enabling parallel and incremental collection with predictable pause times.

**Example:**
```java
// G1 GC configuration
// -XX:+UseG1GC
// -XX:MaxGCPauseMillis=200        # Target pause time
// -XX:G1HeapRegionSize=16m        # Region size (1-32MB)
// -XX:InitiatingHeapOccupancyPercent=45  # Start concurrent marking
// -XX:G1ReservePercent=10         # Reserve for evacuation
// -XX:ConcGCThreads=4             # Concurrent marking threads

public class G1Example {
    public static void main(String[] args) {
        // G1 works best with:
        // - Large heaps (> 6GB)
        // - Predictable pause time requirements
        // - Mixed object lifetimes
        
        List<Object> longLived = new ArrayList<>();
        
        for (int i = 0; i < 1000000; i++) {
            // Short-lived objects
            String temp = "temp" + i;
            
            // Some long-lived objects
            if (i % 1000 == 0) {
                longLived.add(new byte[1024]);
            }
        }
        
        // G1 efficiently handles this mixed workload
        // by collecting regions with most garbage first
    }
}

// Monitor G1 with:
// -Xlog:gc*,gc+heap=debug:file=gc.log
```

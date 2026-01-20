## Garbage Collection 

### 136. What is garbage collection?

Garbage Collection (GC) is Java's automatic memory management system that:

- **Automatically reclaims memory** used by objects no longer referenced
- **Prevents memory leaks** by cleaning unused objects
- **Runs in background** without explicit programmer intervention
- **Manages heap memory** efficiently
- **Frees developers** from manual memory management

```java
// Objects become eligible for GC when no references exist
String str = new String("Hello"); // Object created
str = null; // Object becomes eligible for GC
```

### 137. How does garbage collection work?

GC operates through a **mark-and-sweep** process:

1. **Mark Phase**: Identifies reachable objects from GC roots
2. **Sweep Phase**: Removes unreachable objects
3. **Compact Phase**: Defragments memory (optional)

**Process Flow**:
- Start from GC roots (stack variables, static variables)
- Mark all reachable objects
- Sweep away unmarked objects
- Compact remaining objects to reduce fragmentation

### 138. What are the types of garbage collectors?

| Garbage Collector | Use Case | Characteristics |
|-------------------|----------|-----------------|
| **Serial GC** | Single-threaded apps | Simple, low overhead |
| **Parallel GC** | Multi-core throughput | High throughput, stop-the-world |
| **G1 GC** | Low latency | Predictable pause times |
| **ZGC** | Ultra-low latency | Sub-millisecond pauses |
| **Shenandoah** | Low latency | Concurrent collection |

**JVM Flags**:
```bash
-XX:+UseSerialGC        # Serial GC
-XX:+UseParallelGC      # Parallel GC
-XX:+UseG1GC           # G1 GC
-XX:+UseZGC            # ZGC
```

### 139. What is generational garbage collection?

Generational GC is based on the **weak generational hypothesis**:

- **Most objects die young**
- **Objects that survive become long-lived**

**Benefits**:
- Focus GC effort on young objects
- Reduce GC overhead
- Improve overall performance

**Generations**:
- **Young Generation**: New objects (frequent GC)
- **Old Generation**: Long-lived objects (infrequent GC)

### 140. What are young generation and old generation?

#### Young Generation
- **Eden Space**: Where new objects are allocated
- **Survivor Spaces (S0, S1)**: Hold objects that survived one GC cycle
- **Fast collection**: Objects either die quickly or get promoted

#### Old Generation (Tenured Space)
- **Long-lived objects**: Survived multiple GC cycles in young generation
- **Larger space**: Accommodates objects with longer lifespans
- **Less frequent GC**: Collection happens when space fills up

```
Heap Memory Layout:
┌─────────────────┬──────────────────┐
│ Young Generation│  Old Generation  │
├─────┬─────┬─────┤                  │
│Eden │ S0  │ S1  │    Tenured       │
└─────┴─────┴─────┴──────────────────┘
```

### 141. What is the difference between minor GC and major GC?

| Aspect | Minor GC | Major GC |
|--------|----------|----------|
| **Target** | Young Generation only | Old Generation |
| **Frequency** | High (frequent) | Low (occasional) |
| **Speed** | Fast (milliseconds) | Slower (seconds) |
| **Impact** | Low pause time | Higher pause time |
| **Triggers** | Eden space full | Old generation full |

**Example Timeline**:
```
Time: 0s    1s    2s    3s    4s    5s
GC:   Minor Minor Minor Major Minor Minor
```

### 142. What is full GC?

Full GC is the **most comprehensive garbage collection**:

- **Cleans entire heap**: Young + Old generations
- **Includes method area**: Class metadata cleanup
- **Longest pause time**: Can cause application freeze
- **Should be minimized**: Indicates memory pressure

**Triggers**:
- Old generation full
- Method area full
- Explicit `System.gc()` call
- Heap analysis tools

### 143. What are GC roots?

GC roots are **starting points for reachability analysis**:

#### Types of GC Roots:
- **Local variables**: In method stack frames
- **Static variables**: Class-level references
- **JNI references**: Native method references
- **Active threads**: Thread objects and their stacks
- **Synchronization monitors**: Objects used in synchronized blocks

```java
public class GCRootExample {
    static Object staticRef;        // GC Root: Static variable
    
    public void method() {
        Object localRef = new Object(); // GC Root: Local variable
        // localRef is GC root while method executes
    }
}
```

### 144. How do you tune garbage collection?

#### Key Tuning Parameters:

```bash
# Heap Size
-Xms2g -Xmx4g                    # Initial and maximum heap

# Generation Ratios  
-XX:NewRatio=3                   # Old:Young = 3:1
-XX:SurvivorRatio=8              # Eden:Survivor = 8:1

# GC Algorithm
-XX:+UseG1GC                     # Use G1 collector
-XX:MaxGCPauseMillis=200         # Target pause time

# Monitoring
-XX:+PrintGC                     # Print GC info
-XX:+PrintGCDetails              # Detailed GC logs
```

#### Tuning Strategy:
1. **Monitor current performance**
2. **Identify bottlenecks** (throughput vs latency)
3. **Choose appropriate collector**
4. **Adjust heap sizes**
5. **Test and measure**

### 145. What are the common GC algorithms?

#### 1. Mark and Sweep
```
Mark → Sweep → (Optional Compact)
```
- Marks reachable objects
- Sweeps unmarked objects
- May cause fragmentation

#### 2. Copying GC
```
From Space → To Space (copy live objects)
```
- Copies live objects to new space
- No fragmentation
- Requires double memory

#### 3. Mark-Sweep-Compact
```
Mark → Sweep → Compact
```
- Combines mark-sweep with compaction
- Eliminates fragmentation
- Higher overhead

#### 4. Concurrent Mark Sweep (CMS)
```
Concurrent Mark → Concurrent Sweep
```
- Runs concurrently with application
- Lower pause times
- May cause fragmentation

#### 5. G1 (Garbage First)
```
Incremental + Concurrent + Low Latency
```
- Divides heap into regions
- Predictable pause times
- Good for large heaps

## Performance Comparison

| Algorithm | Throughput | Latency | Memory Overhead |
|-----------|------------|---------|-----------------|
| Serial | High | High | Low |
| Parallel | Very High | High | Low |
| CMS | Medium | Low | Medium |
| G1 | High | Low | Medium |
| ZGC | High | Very Low | High |

## Best Practices

### 1. Choose Right Collector
- **High throughput**: Parallel GC
- **Low latency**: G1, ZGC, or Shenandoah
- **Small applications**: Serial GC

### 2. Monitor GC Performance
```bash
# Enable GC logging
-XX:+PrintGC -XX:+PrintGCTimeStamps -Xloggc:gc.log
```

### 3. Optimize Object Lifecycle
- Minimize object creation
- Reuse objects when possible
- Use object pools for expensive objects

### 4. Tune Heap Sizes
- Set appropriate `-Xms` and `-Xmx`
- Monitor heap utilization
- Adjust generation ratios

### 5. Avoid Memory Leaks
- Close resources properly
- Remove listeners and callbacks
- Use weak references when appropriate

## Key Takeaways

1. **GC is automatic** but understanding it helps optimization
2. **Generational approach** is highly effective for most applications
3. **Choose collector** based on application requirements
4. **Monitor and tune** based on actual performance metrics
5. **Minimize GC pressure** through good coding practices
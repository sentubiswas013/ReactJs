# 10. Java JVM & Memory Management 

## 1. What are the different memory areas in JVM?

JVM divides memory into several distinct areas, each serving specific purposes for program execution and memory management.

**Main Memory Areas:**
- **Heap:** Object storage (Young + Old generation)
- **Stack:** Method calls and local variables
- **Method Area/Metaspace:** Class metadata and constants
- **PC Register:** Current instruction pointer
- **Native Method Stack:** Native method calls
- **Direct Memory:** Off-heap memory (NIO operations)

Each area has different characteristics for garbage collection and memory allocation strategies.

## 2. What is the difference between heap and stack?

**Heap:**
- Stores objects and instance variables
- Shared among all threads
- Garbage collected
- Slower access
- Dynamic memory allocation

**Stack:**
- Stores method calls and local variables
- Thread-specific (each thread has own stack)
- Automatic cleanup when method exits
- Faster access (LIFO structure)
- Fixed size per thread

```java
public void method() {
    int x = 10;        // Stack - local variable
    String obj = new String("Hello"); // obj reference on stack, object on heap
}
```

## 3. What is the difference between PermGen and Metaspace?

**PermGen (Java 7 and earlier):**
- Fixed size heap area
- Stored class metadata
- Caused OutOfMemoryError when full
- Part of heap memory

**Metaspace (Java 8+):**
- Native memory (outside heap)
- Dynamic size expansion
- Automatically managed by OS
- Better memory utilization
- Eliminates PermGen OutOfMemoryError

Metaspace replaced PermGen to solve memory limitations and provide better class metadata management.

## 4. What is garbage collection?

Garbage collection is JVM's automatic memory management process that reclaims memory occupied by objects that are no longer reachable or referenced by the application.

- Automatic memory cleanup
- Removes unreferenced objects
- Prevents memory leaks
- Runs periodically or when memory is low
- Frees developers from manual memory management

The GC identifies objects with no active references and deallocates their memory, making it available for new objects.

## 5. What are the types of garbage collectors?

Java provides several garbage collectors, each optimized for different application requirements and performance characteristics.

**Serial GC:**
- Single-threaded
- Good for small applications

**Parallel GC:**
- Multi-threaded
- Default for server applications

**G1 GC:**
- Low-latency collector
- Good for large heaps

**ZGC/Shenandoah:**
- Ultra-low latency
- Concurrent collection

**CMS (deprecated):**
- Concurrent mark-sweep
- Low pause times

Choose based on application needs: throughput vs latency requirements.

## 6. What is generational garbage collection?

Generational garbage collection is based on the observation that most objects die young. It divides heap into generations and applies different collection strategies.

**Young Generation:**
- Eden space (new objects)
- Survivor spaces (S0, S1)
- Frequent, fast collection

**Old Generation:**
- Long-lived objects
- Less frequent collection
- More expensive cleanup

**Collection Process:**
- Objects start in Eden
- Survivors move to Old generation
- Different algorithms for each generation

This approach optimizes GC performance by focusing on areas where most garbage exists.

## 7. What is the difference between minor GC and major GC?

**Minor GC:**
- Cleans Young Generation only
- Fast and frequent
- Typically takes milliseconds
- Triggered when Eden space fills up
- Most objects are collected here

**Major GC:**
- Cleans Old Generation (and sometimes entire heap)
- Slower and less frequent
- Can cause application pauses
- Triggered when Old Generation fills up
- Also called Full GC when entire heap is cleaned

```java
// Objects that survive multiple minor GCs get promoted to Old Generation
List<String> longLived = new ArrayList<>(); // Eventually moves to Old Gen
String temp = "temporary"; // Likely collected in minor GC
```

## 8. What is metaspace?

Metaspace is the native memory area where JVM stores class metadata, replacing PermGen from Java 8 onwards. It's allocated in native memory outside the heap.

**Characteristics:**
- Native memory allocation
- Dynamic size expansion
- No fixed size limit (limited by available system memory)
- Garbage collected when classes are unloaded
- Better memory utilization than PermGen

**Contents:**
- Class definitions and metadata
- Method bytecode
- Constant pool information
- Runtime constant pool

Metaspace eliminates PermGen OutOfMemoryError issues and provides more flexible memory management.

## 9. What are GC roots?

GC roots are objects that are always reachable and serve as starting points for garbage collection reachability analysis. Objects reachable from GC roots are considered live.

**Types of GC Roots:**
- Local variables in stack frames
- Static variables in classes
- JNI global references
- Objects in native method stacks
- Thread objects
- System class loader references

**Reachability Analysis:**
- GC starts from roots
- Marks all reachable objects
- Unreachable objects are garbage collected
- Ensures no live objects are accidentally collected

```java
public class Example {
    static String staticVar = "root";     // GC root - static variable
    
    public void method() {
        String localVar = "root";         // GC root - local variable
        Object obj = new Object();        // Reachable from localVar
    }
}
```
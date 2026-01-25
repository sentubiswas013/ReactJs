# 🔹 Memory Management and Garbage Collection

## Memory Areas in JVM

### 126. What are the different memory areas in JVM?

The JVM divides memory into several distinct areas:

- **Heap Memory**: Stores all Java objects and instance variables
- **Stack Memory**: Contains method call frames and local variables (per thread)
- **Method Area**: Stores class metadata, constant pool, and static variables
- **PC (Program Counter) Register**: Holds the address of currently executing instruction
- **Native Method Stack**: Used for native method calls

### 127. What is heap memory?

Heap memory is the runtime data area where Java objects are allocated. Key characteristics:

- Shared among all threads
- Divided into Young Generation and Old Generation
- Managed by Garbage Collector
- Objects created with `new` keyword are stored here
- Instance variables of objects reside here

### 128. What is stack memory?

Stack memory stores method-specific data:

- Each thread has its own stack
- Contains method call frames
- Stores local variables and partial results
- Follows LIFO (Last In, First Out) principle
- Automatically managed (no garbage collection needed)
- Faster access compared to heap

### 129. What is method area?

Method area is a shared memory region that contains:

- Class-level metadata and structure
- Runtime constant pool
- Static variables and methods
- Method bytecode
- Shared among all threads
- Part of non-heap memory

### 130. What is the difference between heap and stack?

| Aspect | Heap | Stack |
|--------|------|-------|
| **Storage** | Objects and instance variables | Method calls and local variables |
| **Sharing** | Shared among all threads | Per thread |
| **Management** | Garbage collected | Automatically managed |
| **Speed** | Slower access | Faster access |
| **Size** | Larger | Smaller |
| **Memory Issues** | OutOfMemoryError | StackOverflowError |

### 131. What is permgen space?

PermGen (Permanent Generation) was used in Java versions before Java 8:

- Part of heap memory with fixed size
- Stored class metadata and interned strings
- Static variables and method bytecode
- Could cause `OutOfMemoryError: PermGen space`
- Replaced by Metaspace in Java 8

### 132. What is metaspace?

Metaspace is the replacement for PermGen introduced in Java 8:

- Stores class metadata in native memory (not heap)
- Dynamic sizing based on application needs
- Limited by available system memory
- Better memory management
- Eliminates PermGen space issues

### 133. What is the difference between permgen and metaspace?

| Feature | PermGen | Metaspace |
|---------|---------|-----------|
| **Location** | Heap memory | Native memory |
| **Size** | Fixed size | Dynamic sizing |
| **Memory Limit** | JVM heap limit | System memory limit |
| **Common Issues** | OutOfMemoryError: PermGen | Rare memory issues |
| **Java Version** | Before Java 8 | Java 8 and later |

### 134. What is direct memory?

Direct memory is off-heap memory allocated directly from system RAM:

- Bypasses JVM heap allocation
- Used by NIO operations (ByteBuffer.allocateDirect())
- Not subject to garbage collection
- Faster I/O operations
- Limited by available system memory
- Must be explicitly freed

### 135. What is off-heap memory?

Off-heap memory refers to memory outside the JVM heap:

- Stored directly in system RAM
- Not managed by garbage collector
- Provides faster access for large datasets
- Used for caching and NIO operations
- Reduces GC pressure
- Examples: Direct ByteBuffers, memory-mapped files

## Key Takeaways

1. **Heap vs Stack**: Heap for objects (shared), Stack for method calls (per thread)
2. **PermGen → Metaspace**: Java 8 moved class metadata from heap to native memory
3. **Direct Memory**: Off-heap allocation for performance-critical operations
4. **Memory Management**: Understanding these areas helps in performance tuning and troubleshooting

## Best Practices

- Monitor heap usage to prevent OutOfMemoryError
- Tune stack size for deep recursion scenarios
- Use direct memory judiciously for I/O operations
- Configure metaspace size for applications with many classes
- Profile memory usage to identify bottlenecks
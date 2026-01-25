# 11. Java Input/Output (I/O) 

## 1. What are the different ways to read a file in Java?

Java provides multiple approaches to read files, each suitable for different scenarios and file sizes.

**Common Methods:**
- **FileInputStream:** Byte-based reading
- **FileReader:** Character-based reading
- **BufferedReader:** Efficient line-by-line reading
- **Scanner:** Convenient parsing with delimiters
- **Files.readAllLines():** Read entire file into List
- **Files.lines():** Stream-based reading (Java 8+)

```java
// BufferedReader - most common
try (BufferedReader br = new BufferedReader(new FileReader("file.txt"))) {
    String line = br.readLine();
}

// Files utility - modern approach
List<String> lines = Files.readAllLines(Paths.get("file.txt"));
```

## 2. What is the difference between InputStream and Reader?

**InputStream:**
- Handles raw bytes (binary data)
- Abstract class for byte streams
- Methods return int (0-255) or byte arrays
- Used for images, videos, any binary files
- Examples: FileInputStream, ByteArrayInputStream

**Reader:**
- Handles characters (text data)
- Abstract class for character streams
- Handles character encoding automatically
- Used for text files
- Examples: FileReader, StringReader

```java
// InputStream - for binary data
InputStream is = new FileInputStream("image.jpg");
int byteData = is.read(); // Returns byte as int

// Reader - for text data
Reader reader = new FileReader("text.txt");
int charData = reader.read(); // Returns character as int
```

## 3. What is BufferedReader and BufferedWriter?

BufferedReader and BufferedWriter are wrapper classes that add buffering capability to improve I/O performance by reducing the number of system calls.

**BufferedReader:**
- Buffers input for efficient reading
- Provides readLine() method
- Reduces system calls
- Default buffer size 8192 characters

**BufferedWriter:**
- Buffers output for efficient writing
- Provides newLine() method
- Flushes buffer when full or explicitly called
- Improves write performance

```java
// BufferedReader - efficient reading
try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
    String line;
    while ((line = br.readLine()) != null) {
        System.out.println(line);
    }
}

// BufferedWriter - efficient writing
try (BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
    bw.write("Hello World");
    bw.newLine();
}
```

## 4. How do you handle large files efficiently?

For large files, use streaming approaches and buffering to avoid loading entire file into memory.

**Efficient Strategies:**
- Use BufferedReader/Writer for text files
- Process line by line, not entire file
- Use NIO for better performance
- Stream API for functional processing
- Memory-mapped files for very large files

```java
// Stream processing - memory efficient
try (Stream<String> lines = Files.lines(Paths.get("largefile.txt"))) {
    lines.filter(line -> line.contains("keyword"))
         .forEach(System.out::println);
}

// BufferedReader - traditional approach
try (BufferedReader br = Files.newBufferedReader(Paths.get("largefile.txt"))) {
    String line;
    while ((line = br.readLine()) != null) {
        // Process line by line
    }
}
```

## 5. What is NIO in Java?

NIO (New I/O) is a collection of APIs introduced in Java 1.4 that provides non-blocking I/O operations and better performance for handling multiple connections.

**Key Components:**
- **Channels:** Bidirectional data connections
- **Buffers:** Containers for data
- **Selectors:** Multiplexing for non-blocking I/O
- **Non-blocking operations:** Don't wait for I/O completion

**Benefits:**
- Better scalability for server applications
- Non-blocking I/O operations
- Memory-mapped file access
- More efficient for handling many connections

```java
// NIO file reading
try (FileChannel channel = FileChannel.open(Paths.get("file.txt"))) {
    ByteBuffer buffer = ByteBuffer.allocate(1024);
    channel.read(buffer);
}
```

## 6. What is the difference between IO and NIO?

**Traditional I/O:**
- Stream-oriented (one byte/character at a time)
- Blocking operations
- Simpler API
- Good for fewer connections
- Thread per connection model

**NIO:**
- Buffer-oriented (chunks of data)
- Non-blocking operations
- More complex API
- Better for many connections
- Single thread can handle multiple connections

```java
// Traditional I/O - blocking
InputStream is = new FileInputStream("file.txt");
int data = is.read(); // Blocks until data available

// NIO - non-blocking potential
FileChannel channel = FileChannel.open(Paths.get("file.txt"));
ByteBuffer buffer = ByteBuffer.allocate(1024);
channel.read(buffer); // Can be non-blocking with proper setup
```

## 7. When would you use NIO over traditional I/O?

Use NIO when you need better performance and scalability, especially for server applications handling many concurrent connections.

**Use NIO when:**
- Building high-performance servers
- Handling thousands of concurrent connections
- Need non-blocking I/O operations
- Working with large files (memory-mapped files)
- Building chat servers, web servers

**Use Traditional I/O when:**
- Simple file operations
- Few concurrent connections
- Straightforward read/write operations
- Rapid development needed
- Working with small to medium files

**NIO is ideal for:**
- Network servers (web servers, chat servers)
- File servers handling multiple clients
- Applications requiring high throughput
- Systems with limited threads but many connections

Traditional I/O is simpler and sufficient for most basic file operations and applications with moderate concurrency requirements.
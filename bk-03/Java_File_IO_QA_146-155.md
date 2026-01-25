# 🔹 Input/Output (I/O)

## File I/O Fundamentals

### 146. What are the different ways to read a file in Java?

Java provides multiple approaches for file reading:

#### 1. **Byte-based Reading**
```java
// FileInputStream - for binary data
FileInputStream fis = new FileInputStream("file.txt");
int data = fis.read();
```

#### 2. **Character-based Reading**
```java
// FileReader - for text data
FileReader fr = new FileReader("file.txt");
int ch = fr.read();
```

#### 3. **Buffered Reading**
```java
// BufferedReader - efficient line reading
BufferedReader br = new BufferedReader(new FileReader("file.txt"));
String line = br.readLine();
```

#### 4. **Scanner Reading**
```java
// Scanner - parsing and tokenizing
Scanner scanner = new Scanner(new File("file.txt"));
String word = scanner.next();
```

#### 5. **Modern NIO Approach**
```java
// Files class - Java 7+
List<String> lines = Files.readAllLines(Paths.get("file.txt"));
String content = Files.readString(Paths.get("file.txt")); // Java 11+
```

### 147. What is the difference between InputStream and Reader?

| Aspect | InputStream | Reader |
|--------|-------------|--------|
| **Data Type** | Raw bytes | Characters |
| **Use Case** | Binary files (images, videos) | Text files |
| **Encoding** | No encoding handling | Handles character encoding |
| **Methods** | `read()` returns int (0-255) | `read()` returns int (Unicode) |
| **Subclasses** | FileInputStream, ByteArrayInputStream | FileReader, StringReader |

```java
// InputStream - byte oriented
InputStream is = new FileInputStream("image.jpg");
int byteData = is.read(); // reads single byte

// Reader - character oriented  
Reader reader = new FileReader("text.txt");
int charData = reader.read(); // reads single character
```

### 148. What is the difference between OutputStream and Writer?

| Feature | OutputStream | Writer |
|---------|--------------|--------|
| **Output Type** | Bytes | Characters |
| **Best For** | Binary data | Text data |
| **Encoding** | No encoding | Character encoding support |
| **Method** | `write(int b)` | `write(int c)` |
| **Examples** | FileOutputStream | FileWriter |

```java
// OutputStream - for binary data
OutputStream os = new FileOutputStream("data.bin");
os.write(65); // writes byte value 65

// Writer - for text data
Writer writer = new FileWriter("text.txt");
writer.write(65); // writes character 'A'
```

### 149. What is BufferedReader and BufferedWriter?

**BufferedReader** and **BufferedWriter** add **buffering capability** to improve I/O performance:

#### BufferedReader
```java
BufferedReader br = new BufferedReader(new FileReader("file.txt"));
String line;
while ((line = br.readLine()) != null) {
    System.out.println(line);
}
br.close();
```

#### BufferedWriter
```java
BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
bw.write("Hello World");
bw.newLine();
bw.flush(); // Important: flush buffer
bw.close();
```

#### Benefits:
- **Reduced system calls**: Reads/writes in chunks
- **Better performance**: Especially for small, frequent operations
- **Built-in methods**: `readLine()`, `newLine()`

### 150. What is the difference between FileInputStream and FileReader?

| Aspect | FileInputStream | FileReader |
|--------|-----------------|------------|
| **Data Handling** | Byte-oriented | Character-oriented |
| **Return Type** | `int` (0-255 bytes) | `int` (Unicode characters) |
| **Encoding** | No encoding | Default system encoding |
| **Use Case** | Binary files | Text files |
| **Performance** | Faster for binary | Better for text |

```java
// FileInputStream - reads bytes
FileInputStream fis = new FileInputStream("data.bin");
int byteValue = fis.read(); // 0-255

// FileReader - reads characters
FileReader fr = new FileReader("text.txt");
int charValue = fr.read(); // Unicode value
```

### 151. What is RandomAccessFile?

**RandomAccessFile** provides **random access** to file content:

#### Key Features:
- **Read and write** in same file
- **Seek to any position** using `seek(long pos)`
- **Get current position** with `getFilePointer()`
- **Supports different modes**: "r", "rw", "rws", "rwd"

```java
RandomAccessFile raf = new RandomAccessFile("data.txt", "rw");

// Write at beginning
raf.seek(0);
raf.writeUTF("Hello");

// Write at position 100
raf.seek(100);
raf.writeInt(42);

// Read from position 0
raf.seek(0);
String text = raf.readUTF();

raf.close();
```

#### Use Cases:
- Database-like file operations
- Updating specific parts of large files
- Binary file manipulation

### 152. What is File class?

**File class** represents **file and directory pathnames**:

#### Core Functionality:
```java
File file = new File("example.txt");

// File properties
boolean exists = file.exists();
long size = file.length();
long lastModified = file.lastModified();
boolean isDirectory = file.isDirectory();

// File operations
boolean created = file.createNewFile();
boolean deleted = file.delete();
boolean renamed = file.renameTo(new File("newname.txt"));

// Directory operations
File dir = new File("mydir");
boolean dirCreated = dir.mkdir();
String[] files = dir.list();
```

#### Limitations:
- Platform-dependent path handling
- Limited error information
- No atomic operations
- Legacy design

### 153. What is Path and Files class?

**Path** and **Files** are modern alternatives introduced in **Java 7 (NIO.2)**:

#### Path Interface:
```java
Path path = Paths.get("example.txt");
Path absolutePath = path.toAbsolutePath();
Path parent = path.getParent();
String fileName = path.getFileName().toString();
```

#### Files Utility Class:
```java
// File operations
Files.copy(source, target);
Files.move(source, target);
Files.delete(path);
boolean exists = Files.exists(path);

// Reading/Writing
List<String> lines = Files.readAllLines(path);
Files.write(path, "content".getBytes());

// Directory operations
Files.createDirectories(path);
Stream<Path> files = Files.list(directory);
```

### 154. What is the difference between File and Path?

| Feature | File (Legacy) | Path (Modern) |
|---------|---------------|---------------|
| **Introduction** | Java 1.0 | Java 7 (NIO.2) |
| **Design** | Class-based | Interface-based |
| **Immutability** | Mutable | Immutable |
| **Platform Independence** | Limited | Full |
| **Error Handling** | Boolean returns | Exceptions with details |
| **Performance** | Slower | Faster |
| **Utility Methods** | Limited | Rich (Files class) |

```java
// File - legacy approach
File file = new File("example.txt");
if (file.exists()) {
    file.delete();
}

// Path - modern approach
Path path = Paths.get("example.txt");
if (Files.exists(path)) {
    Files.delete(path);
}
```

### 155. How do you handle large files efficiently?

#### 1. **Use Buffered Streams**
```java
try (BufferedReader br = new BufferedReader(new FileReader("large.txt"))) {
    String line;
    while ((line = br.readLine()) != null) {
        // Process line by line
        processLine(line);
    }
}
```

#### 2. **Read in Chunks**
```java
byte[] buffer = new byte[8192]; // 8KB buffer
try (FileInputStream fis = new FileInputStream("large.bin")) {
    int bytesRead;
    while ((bytesRead = fis.read(buffer)) != -1) {
        // Process chunk
        processChunk(buffer, bytesRead);
    }
}
```

#### 3. **Use NIO Channels**
```java
try (FileChannel channel = FileChannel.open(Paths.get("large.txt"))) {
    ByteBuffer buffer = ByteBuffer.allocate(1024);
    while (channel.read(buffer) > 0) {
        buffer.flip();
        // Process buffer
        processBuffer(buffer);
        buffer.clear();
    }
}
```

#### 4. **Memory-Mapped Files**
```java
try (RandomAccessFile file = new RandomAccessFile("large.txt", "r");
     FileChannel channel = file.getChannel()) {
    
    MappedByteBuffer buffer = channel.map(
        FileChannel.MapMode.READ_ONLY, 0, channel.size());
    
    // Direct memory access - very fast for large files
    while (buffer.hasRemaining()) {
        byte b = buffer.get();
        // Process byte
    }
}
```

#### 5. **Streaming with Files**
```java
// Java 8+ - Stream API for large files
try (Stream<String> lines = Files.lines(Paths.get("large.txt"))) {
    lines.filter(line -> line.contains("keyword"))
         .map(String::toUpperCase)
         .forEach(System.out::println);
}
```

## Performance Comparison

| Method | Small Files | Large Files | Memory Usage |
|--------|-------------|-------------|--------------|
| **Basic FileReader** | Good | Poor | Low |
| **BufferedReader** | Excellent | Good | Low |
| **NIO Channels** | Good | Excellent | Medium |
| **Memory-Mapped** | Overhead | Excellent | High |
| **Stream API** | Good | Good | Low |

## Best Practices

### 1. **Always Use Try-with-Resources**
```java
try (BufferedReader br = Files.newBufferedReader(path)) {
    // File operations
} // Automatically closed
```

### 2. **Choose Right Buffer Size**
```java
// Good buffer sizes: 4KB, 8KB, 16KB
byte[] buffer = new byte[8192];
```

### 3. **Use Appropriate Classes**
- **Text files**: BufferedReader/Writer
- **Binary files**: BufferedInputStream/OutputStream  
- **Large files**: NIO Channels or Memory-mapped files
- **Small files**: Files utility methods

### 4. **Handle Exceptions Properly**
```java
try {
    Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
} catch (IOException e) {
    logger.error("File copy failed: " + e.getMessage());
}
```

## Key Takeaways

1. **Choose the right tool**: Bytes vs Characters, Buffered vs Unbuffered
2. **Modern approach**: Prefer Path/Files over File class
3. **Large files**: Use streaming, chunking, or memory-mapping
4. **Always close resources**: Use try-with-resources
5. **Buffer appropriately**: Balance memory usage and performance
## NIO Fundamentals

### 156. What is NIO in Java?

**NIO (New I/O)** was introduced in **Java 1.4** as an alternative to traditional I/O:

#### Key Features:
- **Non-blocking I/O operations**
- **Channel-based architecture**
- **Buffer-oriented data handling**
- **Selector for multiplexing**
- **Memory-mapped file support**

#### Core Components:
```java
// Basic NIO components
Channel channel = FileChannel.open(path);
ByteBuffer buffer = ByteBuffer.allocate(1024);
Selector selector = Selector.open();
```

#### Benefits:
- Higher scalability for server applications
- Better performance for large files
- Single thread can handle multiple connections
- Direct memory access capabilities

### 157. What is the difference between IO and NIO?

| Aspect | Traditional I/O | NIO |
|--------|-----------------|-----|
| **Model** | Stream-based | Channel-based |
| **Blocking** | Blocking | Non-blocking |
| **Direction** | Unidirectional | Bidirectional |
| **Buffer** | No built-in buffering | Buffer-oriented |
| **Selector** | Not available | Multiplexing support |
| **Performance** | Good for simple operations | Better for high concurrency |
| **Complexity** | Simple | More complex |

#### Traditional I/O Example:
```java
// Blocking I/O
FileInputStream fis = new FileInputStream("file.txt");
int data = fis.read(); // Blocks until data available
```

#### NIO Example:
```java
// Non-blocking I/O
FileChannel channel = FileChannel.open(path, StandardOpenOption.READ);
ByteBuffer buffer = ByteBuffer.allocate(1024);
int bytesRead = channel.read(buffer); // May return 0 if no data
```

### 158. What is Channel in NIO?

**Channel** is a **bidirectional conduit** for I/O operations:

#### Key Characteristics:
- **Bidirectional**: Can read and write (unlike streams)
- **Non-blocking**: Operations don't block threads
- **Buffer-based**: Work with Buffer objects
- **Selectable**: Can be used with Selector

#### Channel Types:
```java
// File operations
FileChannel fileChannel = FileChannel.open(path);

// Network operations  
SocketChannel socketChannel = SocketChannel.open();
ServerSocketChannel serverChannel = ServerSocketChannel.open();

// Pipe operations
Pipe.SourceChannel sourceChannel = pipe.source();
Pipe.SinkChannel sinkChannel = pipe.sink();
```

#### Basic Usage:
```java
try (FileChannel channel = FileChannel.open(path)) {
    ByteBuffer buffer = ByteBuffer.allocate(1024);
    
    // Read from channel to buffer
    int bytesRead = channel.read(buffer);
    
    // Write from buffer to channel
    buffer.flip();
    int bytesWritten = channel.write(buffer);
}
```

### 159. What is Buffer in NIO?

**Buffer** is a **container for data** that channels read from and write to:

#### Buffer Properties:
- **Capacity**: Maximum data it can hold
- **Position**: Current read/write position
- **Limit**: First element that shouldn't be read/written
- **Mark**: Remembered position

#### Buffer States:
```java
ByteBuffer buffer = ByteBuffer.allocate(1024);

// Writing mode
buffer.put((byte) 65);
buffer.put("Hello".getBytes());

// Switch to reading mode
buffer.flip();

// Reading mode
byte b = buffer.get();
byte[] data = new byte[buffer.remaining()];
buffer.get(data);

// Clear for reuse
buffer.clear();
```

#### Buffer Types:
```java
ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
CharBuffer charBuffer = CharBuffer.allocate(512);
IntBuffer intBuffer = IntBuffer.allocate(256);
```

#### Direct vs Heap Buffers:
```java
// Heap buffer - in JVM heap
ByteBuffer heapBuffer = ByteBuffer.allocate(1024);

// Direct buffer - outside JVM heap
ByteBuffer directBuffer = ByteBuffer.allocateDirect(1024);
```

### 160. What is Selector in NIO?

**Selector** enables **single thread to monitor multiple channels**:

#### Key Benefits:
- **Scalability**: One thread handles many connections
- **Efficiency**: No thread per connection
- **Event-driven**: React to I/O events
- **Resource optimization**: Fewer threads needed

#### Basic Usage:
```java
Selector selector = Selector.open();

// Register channel with selector
SocketChannel channel = SocketChannel.open();
channel.configureBlocking(false);
SelectionKey key = channel.register(selector, SelectionKey.OP_READ);

// Event loop
while (true) {
    int readyChannels = selector.select(); // Blocks until events
    
    Set<SelectionKey> keys = selector.selectedKeys();
    for (SelectionKey key : keys) {
        if (key.isReadable()) {
            // Handle read event
            handleRead(key);
        }
        if (key.isWritable()) {
            // Handle write event
            handleWrite(key);
        }
    }
    keys.clear();
}
```

#### Selection Keys:
```java
// Interest operations
SelectionKey.OP_READ     // Ready to read
SelectionKey.OP_WRITE    // Ready to write  
SelectionKey.OP_CONNECT  // Connection established
SelectionKey.OP_ACCEPT   // Ready to accept connections
```

### 161. What is non-blocking I/O?

**Non-blocking I/O** means operations **return immediately** without waiting:

#### Blocking vs Non-blocking:
```java
// Blocking I/O - thread waits
InputStream is = socket.getInputStream();
int data = is.read(); // Blocks until data available

// Non-blocking I/O - returns immediately
SocketChannel channel = SocketChannel.open();
channel.configureBlocking(false);
ByteBuffer buffer = ByteBuffer.allocate(1024);
int bytesRead = channel.read(buffer); // Returns 0 if no data
```

#### Benefits:
- **Higher concurrency**: Thread can handle multiple operations
- **Better resource utilization**: No idle waiting threads
- **Scalability**: Handle thousands of connections
- **Responsiveness**: Application remains responsive

#### Use Cases:
- High-performance servers
- Real-time applications
- Chat servers
- Web servers handling many connections

### 162. What is memory-mapped file?

**Memory-mapped file** maps file content **directly into memory**:

#### How it Works:
```java
try (RandomAccessFile file = new RandomAccessFile("large.dat", "rw");
     FileChannel channel = file.getChannel()) {
    
    // Map file to memory
    MappedByteBuffer buffer = channel.map(
        FileChannel.MapMode.READ_WRITE, 0, file.length());
    
    // Direct memory access - very fast
    buffer.put(0, (byte) 65);        // Write at position 0
    byte value = buffer.get(100);     // Read from position 100
}
```

#### Advantages:
- **Extremely fast**: Direct memory access
- **OS optimization**: Operating system handles caching
- **Large file support**: Can map files larger than heap
- **Shared memory**: Multiple processes can share

#### Use Cases:
- Large file processing
- Database implementations
- High-performance computing
- Inter-process communication

### 163. When would you use NIO over traditional I/O?

#### Use NIO When:
- **High concurrency**: Many simultaneous connections
- **Server applications**: Web servers, chat servers
- **Large files**: Processing big data files
- **Non-blocking operations**: Real-time applications
- **Memory efficiency**: Need direct memory access

#### Use Traditional I/O When:
- **Simple applications**: Basic file operations
- **Small scale**: Few concurrent operations
- **Ease of use**: Simpler programming model
- **Legacy systems**: Existing codebase

#### Decision Matrix:
| Scenario | Traditional I/O | NIO |
|----------|-----------------|-----|
| **Simple file read** | ✅ Better | ❌ Overkill |
| **Web server (1000+ connections)** | ❌ Poor | ✅ Excellent |
| **Large file processing** | ❌ Slow | ✅ Fast |
| **Real-time chat** | ❌ Blocking | ✅ Non-blocking |

### 164. What is NIO.2?

**NIO.2** (JSR 203) was introduced in **Java 7** as an enhancement:

#### New Features:
- **Path and Files classes**: Modern file operations
- **Asynchronous I/O**: Non-blocking with callbacks
- **File system watching**: Monitor directory changes
- **Improved file attributes**: Better metadata access

#### Path and Files:
```java
// Modern file operations
Path path = Paths.get("example.txt");
List<String> lines = Files.readAllLines(path);
Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
```

#### File Watching:
```java
WatchService watchService = FileSystems.getDefault().newWatchService();
path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);

WatchKey key = watchService.take();
for (WatchEvent<?> event : key.pollEvents()) {
    System.out.println("File created: " + event.context());
}
```

#### Asynchronous File I/O:
```java
AsynchronousFileChannel channel = AsynchronousFileChannel.open(path);
ByteBuffer buffer = ByteBuffer.allocate(1024);

Future<Integer> result = channel.read(buffer, 0);
// Do other work while reading
int bytesRead = result.get(); // Get result when ready
```

### 165. What is asynchronous I/O?

**Asynchronous I/O** allows operations to **complete in background**:

#### Key Concepts:
- **Non-blocking**: Thread continues immediately
- **Callback-based**: Notified when operation completes
- **Future-based**: Get result when ready
- **Event-driven**: React to completion events

#### Asynchronous File Operations:
```java
AsynchronousFileChannel channel = AsynchronousFileChannel.open(path);
ByteBuffer buffer = ByteBuffer.allocate(1024);

// Callback approach
channel.read(buffer, 0, null, new CompletionHandler<Integer, Object>() {
    @Override
    public void completed(Integer result, Object attachment) {
        System.out.println("Read " + result + " bytes");
    }
    
    @Override
    public void failed(Throwable exc, Object attachment) {
        exc.printStackTrace();
    }
});

// Future approach
Future<Integer> future = channel.read(buffer, 0);
// Do other work...
int bytesRead = future.get(); // Blocks until complete
```

#### Asynchronous Socket Operations:
```java
AsynchronousServerSocketChannel server = 
    AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(8080));

server.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
    @Override
    public void completed(AsynchronousSocketChannel client, Object attachment) {
        // Handle new client connection
        server.accept(null, this); // Accept next connection
    }
    
    @Override
    public void failed(Throwable exc, Object attachment) {
        exc.printStackTrace();
    }
});
```

## Performance Comparison

| Operation | Traditional I/O | NIO | NIO.2 Async |
|-----------|-----------------|-----|--------------|
| **Single file read** | Fast | Medium | Medium |
| **Multiple connections** | Poor | Excellent | Excellent |
| **Large file processing** | Slow | Fast | Fast |
| **Memory usage** | High | Medium | Low |
| **Complexity** | Low | Medium | High |

## Best Practices

### 1. **Choose Right Approach**
```java
// Simple operations - Traditional I/O
Files.readAllLines(path);

// High concurrency - NIO with Selector
Selector selector = Selector.open();

// Asynchronous operations - NIO.2
AsynchronousFileChannel.open(path);
```

### 2. **Buffer Management**
```java
// Reuse buffers
ByteBuffer buffer = ByteBuffer.allocate(8192);
buffer.clear(); // Reset for reuse

// Use direct buffers for large operations
ByteBuffer directBuffer = ByteBuffer.allocateDirect(1024 * 1024);
```

### 3. **Error Handling**
```java
try (FileChannel channel = FileChannel.open(path)) {
    // NIO operations
} catch (IOException e) {
    // Handle I/O errors
}
```

### 4. **Resource Management**
```java
// Always close channels and selectors
try (Selector selector = Selector.open();
     ServerSocketChannel server = ServerSocketChannel.open()) {
    // Use resources
} // Automatically closed
```

## Key Takeaways

1. **NIO is for scalability**: Use when handling many concurrent operations
2. **Channels are bidirectional**: Unlike streams, can read and write
3. **Buffers manage data**: Understand capacity, position, and limit
4. **Selectors enable multiplexing**: One thread, many channels
5. **Asynchronous I/O for responsiveness**: Don't block on I/O operations
6. **Choose based on requirements**: Simple vs scalable vs asynchronous
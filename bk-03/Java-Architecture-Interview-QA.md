# Java & Architecture Interview Questions & Answers

## Architecture Questions

### 1. Monolithic vs Microservices Architecture

**Monolithic Architecture:**
- Single deployable unit where all components are tightly coupled
- All functionality runs in one process/application
- Shared database and runtime environment
- Simple to develop, test, and deploy initially

**Microservices Architecture:**
- Application broken into small, independent services
- Each service has its own database and can be deployed independently
- Services communicate over well-defined APIs
- Better scalability and technology diversity

**Key Differences:**
- **Scalability**: Microservices allow scaling individual components
- **Technology Stack**: Microservices enable different technologies per service
- **Deployment**: Monoliths deploy as one unit, microservices deploy independently
- **Complexity**: Monoliths are simpler initially, microservices add operational complexity
- **Team Structure**: Microservices support autonomous teams

### 2. How do Microservices Communicate with Each Other?

**Synchronous Communication:**
- **REST APIs**: HTTP-based communication using JSON/XML
- **gRPC**: High-performance RPC framework using Protocol Buffers
- **GraphQL**: Query language for APIs

**Asynchronous Communication:**
- **Message Queues**: RabbitMQ, Apache Kafka, AWS SQS
- **Event Streaming**: Apache Kafka for real-time event processing
- **Pub/Sub**: Google Pub/Sub, AWS SNS

**Communication Patterns:**
- **Request-Response**: Direct synchronous calls
- **Event-Driven**: Services publish/subscribe to events
- **Saga Pattern**: Distributed transaction management

**Supporting Infrastructure:**
- **Service Discovery**: Eureka, Consul, Kubernetes DNS
- **API Gateway**: Single entry point for client requests
- **Load Balancers**: Distribute traffic across service instances

### 3. How do you Handle Failures in Microservices?

**Resilience Patterns:**

**Circuit Breaker Pattern:**
```java
@Component
public class PaymentService {
    
    @CircuitBreaker(name = "payment-service", fallbackMethod = "fallbackPayment")
    public PaymentResponse processPayment(PaymentRequest request) {
        // Call external payment service
        return externalPaymentService.process(request);
    }
    
    public PaymentResponse fallbackPayment(PaymentRequest request, Exception ex) {
        return PaymentResponse.builder()
            .status("PENDING")
            .message("Payment will be processed later")
            .build();
    }
}
```

**Retry Mechanism:**
```java
@Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 1000))
public String callExternalService() {
    // Service call that might fail
    return restTemplate.getForObject("/api/data", String.class);
}
```

**Timeout Configuration:**
```java
@RestTemplate
public RestTemplate restTemplate() {
    HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
    factory.setConnectTimeout(5000);
    factory.setReadTimeout(10000);
    return new RestTemplate(factory);
}
```

**Other Failure Handling Strategies:**
- **Bulkhead Pattern**: Isolate resources to prevent cascade failures
- **Health Checks**: Monitor service health and remove unhealthy instances
- **Graceful Degradation**: Provide reduced functionality when services fail
- **Distributed Tracing**: Track requests across services for debugging

## Spring Boot Questions

### 4. How do you Handle Exception Handling in Spring Boot?

**Global Exception Handler:**
```java
@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex) {
        ErrorResponse error = ErrorResponse.builder()
            .timestamp(LocalDateTime.now())
            .status(HttpStatus.NOT_FOUND.value())
            .error("Resource Not Found")
            .message(ex.getMessage())
            .build();
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> handleValidation(ValidationException ex) {
        ErrorResponse error = ErrorResponse.builder()
            .timestamp(LocalDateTime.now())
            .status(HttpStatus.BAD_REQUEST.value())
            .error("Validation Failed")
            .message(ex.getMessage())
            .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(Exception ex) {
        ErrorResponse error = ErrorResponse.builder()
            .timestamp(LocalDateTime.now())
            .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .error("Internal Server Error")
            .message("An unexpected error occurred")
            .build();
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
```

**Custom Exception:**
```java
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
```

**Validation with Custom Messages:**
```java
@RestController
public class UserController {
    
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody CreateUserRequest request) {
        User user = userService.createUser(request);
        return ResponseEntity.ok(user);
    }
}
```

### 5. How do you Improve Performance in Spring Boot Application?

**Caching:**
```java
@Service
public class UserService {
    
    @Cacheable(value = "users", key = "#id")
    public User getUserById(Long id) {
        return userRepository.findById(id);
    }
    
    @CacheEvict(value = "users", key = "#user.id")
    public User updateUser(User user) {
        return userRepository.save(user);
    }
}
```

**Database Optimization:**
```java
@Entity
public class User {
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Order> orders;
}

// Use pagination
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findByStatus(String status, Pageable pageable);
}
```

**Async Processing:**
```java
@Service
public class EmailService {
    
    @Async
    public CompletableFuture<Void> sendEmail(String to, String subject, String body) {
        // Send email asynchronously
        emailClient.send(to, subject, body);
        return CompletableFuture.completedFuture(null);
    }
}
```

**Configuration Optimizations:**
```properties
# Connection pooling
spring.datasource.hikari.maximum-pool-size=20
spring.datasource.hikari.minimum-idle=5

# JPA optimizations
spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.jdbc.batch_size=25

# Compression
server.compression.enabled=true
server.compression.mime-types=application/json,application/xml,text/html,text/xml,text/plain

# HTTP/2
server.http2.enabled=true
```

## Java Streams & Concurrency

### 6. What is a Parallel Stream?

Parallel Stream is a feature introduced in Java 8 that enables concurrent processing of stream elements across multiple threads.

**Key Characteristics:**
- Automatically splits data into chunks for parallel processing
- Uses ForkJoinPool for thread management
- Best suited for CPU-intensive operations on large datasets
- Not always faster due to overhead

**Example:**
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

// Sequential processing
long sequentialSum = numbers.stream()
    .mapToInt(Integer::intValue)
    .sum();

// Parallel processing
long parallelSum = numbers.parallelStream()
    .mapToInt(Integer::intValue)
    .sum();

// Complex processing example
List<String> results = largeDataset.parallelStream()
    .filter(item -> item.length() > 5)
    .map(String::toUpperCase)
    .collect(Collectors.toList());
```

### 7. Difference between Parallel Stream and Multithreading

| Aspect | Parallel Stream | Multithreading |
|--------|----------------|----------------|
| **Thread Management** | Automatic (ForkJoinPool) | Manual thread creation/management |
| **Complexity** | Simple, declarative | Complex, requires synchronization |
| **Use Case** | Data processing operations | Concurrent tasks, I/O operations |
| **Control** | Limited control over threads | Full control over thread lifecycle |
| **Overhead** | Built-in splitting overhead | Custom overhead management |
| **Debugging** | Harder to debug | Easier to debug with proper tools |

**Parallel Stream Example:**
```java
// Automatic parallelization
List<Integer> results = data.parallelStream()
    .filter(n -> n > 100)
    .map(n -> n * 2)
    .collect(Collectors.toList());
```

**Multithreading Example:**
```java
// Manual thread management
ExecutorService executor = Executors.newFixedThreadPool(4);

List<Future<Integer>> futures = new ArrayList<>();
for (int i = 0; i < data.size(); i++) {
    final int index = i;
    Future<Integer> future = executor.submit(() -> {
        return processData(data.get(index));
    });
    futures.add(future);
}

List<Integer> results = futures.stream()
    .map(future -> {
        try {
            return future.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    })
    .collect(Collectors.toList());

executor.shutdown();
```

### 8. How do you Handle Large Data Processing?

**Streaming Approach:**
```java
// Process large files line by line
try (Stream<String> lines = Files.lines(Paths.get("large-file.txt"))) {
    lines.filter(line -> line.contains("ERROR"))
         .map(this::parseLogEntry)
         .forEach(this::processError);
}
```

**Batch Processing:**
```java
@Service
public class DataProcessor {
    
    private static final int BATCH_SIZE = 1000;
    
    public void processLargeDataset(List<DataItem> allData) {
        for (int i = 0; i < allData.size(); i += BATCH_SIZE) {
            int endIndex = Math.min(i + BATCH_SIZE, allData.size());
            List<DataItem> batch = allData.subList(i, endIndex);
            
            processBatch(batch);
            
            // Optional: Add delay to prevent overwhelming the system
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
```

**Database Pagination:**
```java
@Repository
public class DataRepository {
    
    public Page<DataItem> findDataByStatus(String status, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return dataItemRepository.findByStatus(status, pageable);
    }
}

@Service
public class DataService {
    
    public void processAllData() {
        int page = 0;
        int size = 1000;
        Page<DataItem> dataPage;
        
        do {
            dataPage = dataRepository.findDataByStatus("PENDING", page, size);
            processDataBatch(dataPage.getContent());
            page++;
        } while (dataPage.hasNext());
    }
}
```

**Memory-Efficient Processing:**
```java
// Use CompletableFuture for async processing
public CompletableFuture<Void> processLargeDatasetAsync(Stream<DataItem> dataStream) {
    return CompletableFuture.runAsync(() -> {
        dataStream.parallel()
                 .filter(this::isValid)
                 .map(this::transform)
                 .forEach(this::save);
    });
}

// Use weak references for caching large objects
private final Map<String, WeakReference<LargeObject>> cache = new ConcurrentHashMap<>();
```

## Best Practices Summary

1. **Architecture**: Choose monolithic for simple applications, microservices for complex, scalable systems
2. **Communication**: Use async messaging for loose coupling, sync calls for immediate responses
3. **Resilience**: Implement circuit breakers, retries, and timeouts
4. **Performance**: Enable caching, optimize queries, use async processing
5. **Data Processing**: Stream large datasets, use pagination, implement batch processing
6. **Concurrency**: Use parallel streams for CPU-bound tasks, multithreading for I/O-bound tasks

---

*This document covers essential Java and architecture concepts for technical interviews. Practice implementing these patterns and understand when to apply each approach.*
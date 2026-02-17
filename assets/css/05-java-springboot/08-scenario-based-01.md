# **1️⃣ Personal Questions**

## **Q1. Tell me about yourself (Java Developer)**

> I am a Full Stack Java Developer with over 3.5 years of experience in designing, developing, and maintaining enterprise-level web applications. My primary expertise is in Java, Spring Boot, and microservices architecture, along with hands-on experience in RESTful APIs, database design, and frontend technologies like React and Angular.
>
> Currently, I am working at OqulusTech LLC, where I am involved in building scalable backend services, integrating third-party APIs, and optimizing application performance. I follow clean coding practices, write unit and integration tests, and collaborate closely with cross-functional teams to deliver high-quality software solutions.
>
> I am passionate about solving complex problems, learning new technologies, and contributing to systems that are reliable, secure, and scalable.

---

## **Q2. Current role & day-to-day responsibilities (Java Developer)**

> In my current role, my day-to-day responsibilities include designing and developing REST APIs using Java and Spring Boot, implementing business logic, and ensuring code quality through unit testing with JUnit and Mockito.
>
> I work extensively with relational databases like MySQL and PostgreSQL, writing optimized queries and managing schema changes. I also participate in code reviews, bug fixing, and performance tuning.
>
> As part of a microservices-based system, I handle service-to-service communication, implement security using Spring Security and JWT, and support CI/CD pipelines using tools like Git, Docker, and Jenkins. I collaborate daily with frontend developers, QA, and product teams in Agile/Scrum ceremonies to ensure timely delivery of features.

---

# **2️⃣ Core Java – Practical / Real-World Scenarios**

## **Q3. Your service is slow under high load. How did you identify and fix the performance issue?**

**Spoken Answer:**

> In one of my projects, our service became slow during peak traffic.
> First, I identified the bottleneck using **application metrics and logs**. We used **Spring Boot Actuator**, **Prometheus**, and **Grafana** to monitor response time, CPU, memory, and thread usage. I used **JProfiler** and **VisualVM** to identify bottlenecks.
>
> I noticed that response time increased when database calls spiked. After analyzing SQL logs and APM traces, I found an **N+1 query problem** and a blocking I/O call.  We fixed it by adding pagination, using proper indexes, caching frequent responses, and optimizing JPA queries.”
>
> To fix it, I optimized queries, added proper indexing, introduced **caching using Redis**, and moved heavy tasks to **async processing**. After that, latency dropped by more than 60%.

**Fixes Applied:**

* JProfiler / VisualVM
* JVM thread dumps
* Pagination
* Query optimization
* Redis caching
* Lazy loading fixes

**Short Example Code (Caching):**

```java
@Cacheable(value = "users", key = "#id")
public User getUserById(Long id) {
    return userRepository.findById(id).orElseThrow();
}
```

---

## **Q4. Your Java service started consuming high CPU in production. How did you identify the root cause and fix it?**

**Spoken Answer:**

> In production, CPU usage suddenly spiked.
> I took a **thread dump using jstack** and correlated it with logs.
>
> I found a thread stuck in an **infinite loop caused by improper stream usage**.
> After fixing the logic and adding safeguards, CPU returned to normal.

**Example Code (Bug Fix):**

```java
// BAD: infinite loop
while(list.stream().anyMatch(x -> x.isActive())) {
    process();
}

// FIXED
list.stream()
    .filter(Item::isActive)
    .forEach(this::process);
```

---

## **Q5. Describe a situation where you faced a memory leak in Java. How did you detect and resolve it?**
**Spoken Answer:**
> I faced a memory leak where the heap kept growing until the service crashed.
>
> I captured a **heap dump using jmap** and analyzed it in **Eclipse MAT**.
> The issue was a static map holding references to objects that were never cleared.
>
> I fixed it by removing unnecessary static references and using **WeakHashMap**.

**Example Code (Fix):**

```java
Map<String, User> cache = new WeakHashMap<>();
```

---

## **Q6. How did you handle concurrency issues in a multi-threaded Java application you worked on? Give a real example.**

**Spoken Answer:**

**Way One**
“In one of my projects, we had a **payment processing service** where multiple threads were updating the same user wallet balance at the same time. This caused **race conditions**, leading to incorrect balances.

To handle this, I first identified the shared critical section where the balance was being read and updated. I used **synchronization and concurrent utilities** to make the operation thread-safe.

For example, instead of doing a simple read-modify-write, I used a **`ReentrantLock`** to ensure only one thread could update a user’s balance at a time. In some cases where performance mattered, I used **`AtomicInteger` / `AtomicLong`** for counters and **`ConcurrentHashMap`** for shared in-memory data.

Here’s a simplified example from that scenario:”

```java
class WalletService {
    private final ReentrantLock lock = new ReentrantLock();
    private int balance = 1000;

    public void deductAmount(int amount) {
        lock.lock();
        try {
            if (balance >= amount) {
                balance -= amount;
            }
        } finally {
            lock.unlock();
        }
    }
}
```
“Additionally, we avoided long synchronized blocks, reduced lock scope, and used **thread pools** (`ExecutorService`) instead of creating threads manually. This ensured data consistency while keeping the system performant under high load.”

**Way two**
> I solved this by using **synchronization** and **database-level locking**. In some cases, I used **Optimistic Locking with @Version**, and for in-memory operations, I used **Atomic classes** and synchronized blocks.

**Short Example Code (Optimistic Locking):**

```java
@Entity
public class Account {
    @Id
    private Long id;

    @Version
    private int version; // Used by JPA for optimistic locking

    private BigDecimal balance;
}
```

**Atomic Example:**

```java
AtomicInteger counter = new AtomicInteger(0);
counter.incrementAndGet();
```

---

## **Q7. You had to process a large file (millions of records). How did you design the Java code to avoid OutOfMemory errors?**

**Spoken Answer:**

> To process millions of records, I avoided loading the entire file into memory.
>
> I used **streaming with BufferedReader**, processed data line by line, and batch-inserted records into the database.
>
> This ensured constant memory usage.

**Example Code:**

```java
try (BufferedReader br = Files.newBufferedReader(path)) {
    String line;
    while ((line = br.readLine()) != null) {
        process(line);
    }
}
```

---

## **Q8. Tell me about a time when you refactored legacy Java code. What problems did it have and what improvements did you make?**

**Spoken Answer:**

> I worked on a legacy Java application with large God classes, no separation of concerns, and hard-coded values.
>
> I refactored it by breaking it into smaller services, applying **SOLID principles**, introducing DTOs, and replacing if-else chains with **strategy patterns**.
>
> This improved readability, testability, and reduced bugs.

**Example Code (Strategy Pattern):**

```java
public interface PaymentStrategy {
    void pay();
}
```

---

## **Q9. Describe a scenario where improper object creation impacted performance.**

**Spoken Answer:**

“We noticed high GC activity and memory spikes. On investigation, we found that objects were being created repeatedly inside loops. This caused excessive garbage collection. We fixed it by reusing objects, using object pooling, and switching to immutable or cached objects where possible.”

**Example Problem:**

❌ **Bad Practice (Object creation inside loop):**

```java
for (int i = 0; i < 100000; i++) {
    String s = new String("Java");
}
```

✅ **Optimized Version:**

```java
String s = "Java";
for (int i = 0; i < 100000; i++) {
    // reuse same object
}
```

**Additional Improvements:**
* Used `StringBuilder` instead of `String`
* Reused database connections via connection pooling

---

## **Q10. Parallel processing: Threads vs ExecutorService vs Parallel Streams**

**Spoken Answer:**

“My decision depended on **control, complexity, and workload**.

* For low-level control → Threads
* For managed, scalable tasks → ExecutorService
* For data-parallel operations → Parallel Streams”

**Decision Logic:**

| Scenario                     | Choice             |
| ---------------------------- | ------------------ |
| Fine-grained control         | `Thread`           |
| Production-grade async tasks | `ExecutorService`  |
| Collection processing        | `parallelStream()` |

**ExecutorService Example:**

```java
ExecutorService executor = Executors.newFixedThreadPool(5);

executor.submit(() -> processOrder());
executor.shutdown();
```

**Parallel Stream Example:**

```java
orders.parallelStream()
      .forEach(order -> process(order));
```

---

## **Q11. Exception handling and logging in large applications**

**Spoken Answer:**

“In large systems, bad logging is as dangerous as no logging. I used **centralized exception handling**, meaningful log levels, and correlation IDs. This made production debugging much easier.”

**Best Practices Used:**

* Global exception handler
* SLF4J + Logback
* Structured logs
* Avoided `e.printStackTrace()`

**Example:**

```java
try {
    processPayment();
} catch (PaymentException e) {
    logger.error("Payment failed for orderId={}", orderId, e);
    throw e;
}
```

**Spring Global Handler Example:**

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handle(Exception ex) {
        return ResponseEntity.status(500).body("Internal Error");
    }
}
```

---

## **Q12. JVM tuning in production**

**Spoken Answer:**

“In production, we faced frequent Full GC and slow response times. After analyzing GC logs, I tuned heap size and garbage collector settings. This stabilized memory usage and reduced pause times.”

**Parameters Tuned:**

* Heap size (`-Xms`, `-Xmx`)
* GC algorithm
* Metaspace size

**Example JVM Options:**

```bash
-Xms4g
-Xmx4g
-XX:+UseG1GC
-XX:MaxMetaspaceSize=512m
```

**Why:**

* Fixed heap prevents resizing overhead
* G1GC reduces pause times
* Controlled class metadata memory

---

# **3️⃣ Spring Boot – Hands-On Implementation Questions**

## **Q13. Real scenario: Securing REST APIs using Spring Security**

**Spoken Answer:**

“In one project, we built REST APIs for a finance application. We used **JWT-based authentication** because it is stateless and scalable. Users authenticated using username and password, received a JWT token, and then passed it in the Authorization header for every request. Authorization was role-based — for example, ADMIN and USER.”

**Authentication Flow:**

1. User logs in
2. JWT token generated
3. Token sent in `Authorization` header
4. Token validated for every request

**JWT Filter Example:**

```java
String authHeader = request.getHeader("Authorization");

if (authHeader != null && authHeader.startsWith("Bearer ")) {
    String token = authHeader.substring(7);
    // validate token
}
```

**Role-Based Access Example:**

```java
@PreAuthorize("hasRole('ADMIN')")
@GetMapping("/admin/users")
public List<User> getUsers() {
    return userService.findAll();
}
```

---

## **Q14. Externalizing configuration for different environments**

**Spoken Answer:**
“We had separate environments like dev, QA, and prod. We externalized configuration using **Spring Profiles** so that environment-specific values like database URLs and API keys could be managed safely.”

**How We Did It:**

* `application-dev.yml`
* `application-qa.yml`
* `application-prod.yml`
* Active profile set via environment variable

**Example Configuration:**

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/devdb
```

**Activate Profile:**

```bash
-Dspring.profiles.active=prod
```

**Accessing Properties:**

```java
@Value("${spring.datasource.url}")
private String dbUrl;
```

---

## **Q15. Pagination, sorting, and filtering in Spring Boot**

**Spoken Answer:**
“For APIs returning large datasets, we implemented pagination and sorting using **Spring Data JPA**. Filtering was done using query parameters. This improved performance and user experience.”

**Controller Example:**

```java
@GetMapping("/products")
public Page<Product> getProducts(
        @RequestParam int page,
        @RequestParam int size,
        @RequestParam(defaultValue = "name") String sortBy) {

    Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
    return productRepository.findAll(pageable);
}
```

**Filtering Example:**

```java
@GetMapping("/products/search")
public List<Product> search(@RequestParam String category) {
    return productRepository.findByCategory(category);
}
```

---

# **4️⃣ Microservices – Production Scenarios**

## **Q16. One microservice is down. How did you prevent system failure?**

**Spoken Answer:**

> In a microservices-based system, I used **Resilience patterns** to avoid cascading failures.
>
> I implemented **Circuit Breaker**, **Retry**, and **Fallback mechanisms** using **Resilience4j**.
> If a dependent service goes down, the circuit breaker opens and returns a fallback response instead of failing the entire system.

**What We Used:**

* Circuit breaker
* Fallback responses
* Timeouts
* Cached data

**Short Example Code (Circuit Breaker):**
```java
@Service
public class OrderService {

    private final InventoryClient inventoryClient;

    public OrderService(InventoryClient inventoryClient) {
        this.inventoryClient = inventoryClient;
    }

    @CircuitBreaker(name = "inventoryService", fallbackMethod = "fallback")
    @Retry(name = "inventoryService")
    public InventoryResponse checkInventory(String productId) {
        return inventoryClient.getInventory(productId);
    }

    public InventoryResponse fallback(String productId, Exception ex) {
        return new InventoryResponse(productId, 0, "Service unavailable");
    }
}
```

**application.yml Configuration**
```java
resilience4j:
  circuitbreaker:
    instances:
      inventoryService:
        slidingWindowSize: 10
        failureRateThreshold: 50
        waitDurationInOpenState: 10s
        permittedNumberOfCallsInHalfOpenState: 3
  retry:
    instances:
      inventoryService:
        maxAttempts: 3
        waitDuration: 2s

```

---

## **Q17. How did microservices communicate with each other?**

**Spoken Answer:**
> In our system, microservices mainly communicated using **REST APIs over HTTP**.
> For synchronous communication, we used **Feign Client** with service discovery through **Eureka**.
>
> For asynchronous communication, especially for event-based workflows, we used **Kafka**. This helped us reduce tight coupling and improve scalability.

**Example Code (Feign Client):**

```java
@FeignClient(name = "payment-service")
public interface PaymentClient {

    @GetMapping("/payments/{orderId}")
    PaymentResponse getPayment(@PathVariable Long orderId);
}
```

---

## **Q18. How did you handle distributed transactions?**

**Spoken Answer:**
> Since microservices have separate databases, we avoided traditional distributed transactions like 2PC.
>
> Instead, we used the **Saga pattern**. Each service performed a local transaction and published an event. If any step failed, we executed a **compensating transaction** to rollback previous steps.
>
> This approach ensured **eventual consistency** without blocking services.

**Example Code (Saga Event Publish):**

```java
@Transactional
public void createOrder(Order order) {
    orderRepository.save(order);
    kafkaTemplate.send("order-created", order.getId());
}
```

---

## **Q19. One microservice was frequently failing and impacting others. How did you isolate it?**

**Spoken Answer:**
“One unstable service was causing cascading failures. We isolated it using **circuit breakers**, **bulkheads**, and **rate limiting**. Once failures crossed a threshold, calls to that service were stopped temporarily.”

**Isolation Techniques Used:**

* Circuit breaker
* Thread pool isolation
* Bulkhead pattern

**Example (Bulkhead):**

```java
@Bulkhead(name = "paymentService", type = Bulkhead.Type.THREADPOOL)
public PaymentResponse processPayment() {
    return paymentClient.pay();
}
```

---

## **Q20. How did you manage communication between microservices? Why synchronous vs asynchronous?**

**Spoken Answer:**
“We used **both synchronous and asynchronous communication**, based on the use case.

* For real-time user requests → **REST (synchronous)**
* For event-based workflows → **Kafka (asynchronous)**”

**Why This Approach:**

* REST → simple, immediate response
* Kafka → decoupled, scalable, fault-tolerant

**REST Example:**

```java
OrderResponse response = restTemplate.getForObject(
    "http://payment-service/pay", OrderResponse.class);
```

**Kafka Producer Example:**

```java
kafkaTemplate.send("order-events", orderEvent);
```

---

## **Q21. Real scenario: circuit breaker, retry, timeout**

**Spoken Answer:**
“In production, a downstream service was slow and unstable. We implemented **timeouts to avoid blocking**, **retries for transient failures**, and **circuit breakers** to stop repeated failures.”

**Configuration Example (Resilience4j):**

```yaml
resilience4j:
  circuitbreaker:
    instances:
      paymentService:
        failureRateThreshold: 50
        waitDurationInOpenState: 10s
```

**Retry Example:**

```java
@Retry(name = "paymentService", maxAttempts = 3)
public PaymentResponse pay() {
    return paymentClient.pay();
}
```

---

## **Q22. Distributed transactions without a single DB transaction**

**Spoken Answer:**
“We avoided distributed database transactions because they don’t scale. Instead, we used the **Saga pattern**. Each microservice performed its local transaction and published an event. If something failed, compensating actions were triggered.”

**Saga Flow Example:**

1. Order Service → creates order
2. Payment Service → charges payment
3. Inventory Service → reserves stock
4. Failure → compensating rollback

**Event Example:**

```java
kafkaTemplate.send("payment-failed", orderId);
```

**Compensation Example:**

```java
public void rollbackOrder(String orderId) {
    orderRepository.cancel(orderId);
}
```

---

## **Q23. Deploying multiple microservices — service discovery & routing**

**Spoken Answer:**
“As the number of services grew, hardcoded URLs became unmanageable. We solved this using **service discovery and an API gateway**. Services registered themselves dynamically, and routing was handled centrally.”

**Challenges Faced:**

* Dynamic service IPs
* Load balancing
* Routing
* Versioning

**Solutions Used:**

* Eureka / Consul (service discovery)
* Spring Cloud Gateway
* Load balancing

**Service Discovery Example:**

```java
@EnableDiscoveryClient
@SpringBootApplication
public class OrderServiceApplication {
}
```

**Gateway Route Example:**

```yaml
spring:
  cloud:
    gateway:
      routes:
        - id: order-service
          uri: lb://ORDER-SERVICE
          predicates:
            - Path=/orders/**
```

---

# **5️⃣ Cloud (AWS / GCP) – Practical Experience**

## **Q24. How did you deploy your Spring Boot application to the cloud?**

**Spoken Answer:**

> We deployed our Spring Boot applications using **Docker and Kubernetes** on the cloud.
>
> First, we containerized the application using Docker. Then we deployed it using Kubernetes deployments and services.
>
> CI/CD was handled using Jenkins, which automatically built, tested, and deployed the application.

**Example Code (Dockerfile):**

```dockerfile
FROM openjdk:17
COPY target/app.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
```

---

## **Q25. How did you manage configuration in cloud environments?**

**Spoken Answer:**

> We managed configurations using **Spring Cloud Config Server**.
> All environment-specific properties were stored in a Git repository.
>
> This allowed us to change configurations dynamically without redeploying services.
> Sensitive values like passwords were stored in **Vault or Kubernetes Secrets**.

**Example Code (bootstrap.yml):**

```yaml
spring:
  application:
    name: order-service
  cloud:
    config:
      uri: http://config-server:8888
```

---

## **Q26. How did you deploy a Spring Boot microservice to AWS/GCP? Walk me through the complete flow.**

**Spoken Answer (End-to-End Flow):**
“I’ll explain it end to end. First, we built the Spring Boot application and containerized it using Docker. Then we pushed the image to a container registry. After that, we deployed it to Kubernetes (EKS on AWS / GKE on GCP). Traffic came through a load balancer, and pods were auto-scaled based on load.”

### **Step-by-Step Flow**

1. Build Spring Boot JAR
2. Create Docker image
3. Push image to ECR (AWS) / Artifact Registry (GCP)
4. Deploy to EKS / GKE
5. Expose service via Load Balancer / Ingress
6. Enable auto-scaling

**Dockerfile Example:**

```dockerfile
FROM openjdk:17-jdk-slim
COPY target/app.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
```

**Kubernetes Deployment Example:**

```yaml
apiVersion: apps/v1
kind: Deployment
spec:
  replicas: 3
  template:
    spec:
      containers:
        - name: order-service
          image: myrepo/order-service:1.0
          ports:
            - containerPort: 8080
```

---

## **Q27. How did you handle application secrets in cloud environments?**

**Spoken Answer:**
“We never stored secrets in code or Git. In AWS/GCP, we used **managed secret services** and injected them securely at runtime.”

### **Tools Used**

* AWS Secrets Manager / Parameter Store
* GCP Secret Manager
* Kubernetes Secrets
* IAM-based access

**Spring Boot Example:**

```yaml
spring:
  datasource:
    password: ${DB_PASSWORD}
```

**Kubernetes Secret Example:**

```bash
kubectl create secret generic db-secret \
--from-literal=DB_PASSWORD=secret123
```

**Why This Works:**

* Secrets encrypted
* No hardcoding
* Environment-specific

---

## **Q28. Production cloud outage — how did you troubleshoot and restore?**

**Spoken Answer:**
“When the application went down, the first priority was restoration, not root cause. I checked health checks, pod status, and logs. Once the service was restored, we did a post-mortem.”

### **Troubleshooting Steps**

1. Check Load Balancer health
2. Check pod status (`CrashLoopBackOff`)
3. Review logs & metrics
4. Roll back to last stable version
5. Scale replicas if needed

**Command Examples:**

```bash
kubectl get pods
kubectl logs order-service-pod
kubectl rollout undo deployment order-service
```

---

## **Q29. Logging, monitoring, and alerting in AWS/GCP**

**Spoken Answer:**
“In microservices, observability is critical. We implemented centralized logging, real-time monitoring, and proactive alerting.”

### **What We Used**

* Logs → ELK / CloudWatch / GCP Logging
* Metrics → Prometheus + Grafana
* Alerts → CloudWatch Alarms / GCP Alerting

**Logback Example:**

```xml
<appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
    <encoder>
        <pattern>%d %-5level %logger - %msg%n</pattern>
    </encoder>
</appender>
```

**Health Endpoint:**

```java
management.endpoints.web.exposure.include=health,metrics
```

---

## **Q30. Designing for scalability and high availability**

**Spoken Answer:**
“We designed the system assuming traffic spikes and failures. The application was **stateless**, horizontally scalable, and deployed across multiple zones.”

### **Key Design Decisions**

* Stateless microservices
* Auto-scaling
* Load balancing
* Multi-AZ deployment
* Caching
* Circuit breakers

**Horizontal Pod Autoscaler Example:**

```yaml
apiVersion: autoscaling/v2
kind: HorizontalPodAutoscaler
spec:
  minReplicas: 2
  maxReplicas: 10
```
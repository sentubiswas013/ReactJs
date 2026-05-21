# Java Spring Boot Interview Q&A

---

## 🔹 Personal / Project Experience

**Q: What is your current role and responsibilities?**
> I work as a Senior Java/Spring Boot Developer. Responsibilities include designing microservices, writing REST APIs, code reviews, CI/CD pipeline management, and collaborating with frontend and DevOps teams.

**Q: Why did you leave your previous organization?**
> Looking for better growth opportunities, more challenging projects, and exposure to modern cloud-native architectures.

**Q: Describe the architecture of your recent project.**
> Microservices-based architecture using Spring Boot, deployed on AWS ECS/EC2. Each service communicates via REST APIs and messaging queues (RabbitMQ/IBM MQ). API Gateway handles routing, and services use Oracle/PostgreSQL databases. Logs are centralized in CloudWatch.

**Q: Who were the end-users of your application?**
> Internal business users (operations/finance teams) and external customers accessing via web/mobile portals.

**Q: Did you face challenges during migration or revamp?**
> Yes — migrating from monolith to microservices involved data partitioning, handling distributed transactions, managing backward compatibility, and ensuring zero-downtime deployments.

**Q: How complex was your most challenging project?**
> A real-time payment processing system with high availability requirements (99.99% uptime), complex business rules, multi-DB support, async messaging, and strict security/compliance requirements.

---

## 🔹 Java Internals

**Q: How does the Java class loader work?**
> Class loaders load `.class` files into the JVM. Three built-in loaders: Bootstrap (core Java classes), Extension (ext libs), and Application (classpath classes). They follow the **delegation model** — child delegates to parent before loading itself.

**Q: Difference between runtime vs compile-time class loading?**
> - **Compile-time**: Class is known at compile time, loaded automatically by JVM at startup.
> - **Runtime**: Class is loaded dynamically using `Class.forName()` or `ClassLoader.loadClass()` — not known until execution.

**Q: How do you load a class dynamically in Java?**
```java
Class<?> clazz = Class.forName("com.example.MyClass");
Object obj = clazz.getDeclaredConstructor().newInstance();
```

**Q: What happens internally when you create an object using `new`?**
> 1. JVM checks if class is loaded; if not, loads it.
> 2. Memory allocated in heap.
> 3. Default values assigned to fields.
> 4. Constructor is called.
> 5. Reference returned to the variable.

**Q: Can static methods be overridden?**
> No. Static methods belong to the class, not the instance. You can **hide** them in a subclass, but it's not polymorphic overriding — the method called depends on the reference type, not the object type.

**Q: How do you create an immutable class?**
> 1. Declare class as `final`
> 2. All fields `private final`
> 3. No setters
> 4. Deep copy mutable objects in constructor and getters

```java
public final class Employee {
    private final String name;
    private final List<String> roles;

    public Employee(String name, List<String> roles) {
        this.name = name;
        this.roles = new ArrayList<>(roles);
    }

    public String getName() { return name; }
    public List<String> getRoles() { return new ArrayList<>(roles); }
}
```

---

## 🔹 Exceptions

**Q: What are checked vs unchecked exceptions?**
> - **Checked**: Must be declared or handled at compile time. E.g., `IOException`, `SQLException`.
> - **Unchecked**: Subclass of `RuntimeException`, not enforced at compile time. E.g., `NullPointerException`, `IllegalArgumentException`.

**Q: Difference between ClassNotFoundException vs NoClassDefFoundError?**
> - `ClassNotFoundException`: Thrown when `Class.forName()` can't find the class at runtime — checked exception.
> - `NoClassDefFoundError`: Class was present at compile time but missing at runtime — error (not exception).

**Q: Have you created custom exceptions?**
```java
public class PaymentException extends RuntimeException {
    public PaymentException(String message) {
        super(message);
    }
}
```

**Q: How do you differentiate between compile-time and runtime exceptions?**
> Compile-time exceptions are checked — compiler forces you to handle them. Runtime exceptions are unchecked — they occur during execution due to logic errors.

**Q: What happens if a class is missing in a JAR during deployment?**
> JVM throws `NoClassDefFoundError` at runtime when it tries to use that class. Application may crash or the specific feature fails depending on where it's used.

---

## 🔹 Collections

**Q: What data structures have you used (List, Set, Map)?**
> `ArrayList`, `LinkedList`, `HashSet`, `LinkedHashSet`, `TreeSet`, `HashMap`, `LinkedHashMap`, `TreeMap`, `ConcurrentHashMap`, `PriorityQueue`.

**Q: Difference between List and Set?**
> - `List`: Ordered, allows duplicates. E.g., `ArrayList`.
> - `Set`: Unordered (except `LinkedHashSet`/`TreeSet`), no duplicates. E.g., `HashSet`.

**Q: How do you convert a List to an Array?**
```java
List<String> list = List.of("a", "b", "c");
String[] arr = list.toArray(new String[0]);
```

**Q: How do you sort a list of dates in ascending order?**
```java
List<LocalDate> dates = Arrays.asList(LocalDate.of(2023,5,1), LocalDate.of(2022,1,10));
Collections.sort(dates); // or dates.sort(Comparator.naturalOrder());
```

**Q: What is fail-fast vs fail-safe?**
> - **Fail-fast**: Throws `ConcurrentModificationException` if collection is modified during iteration. E.g., `ArrayList`, `HashMap`.
> - **Fail-safe**: Works on a copy of the collection, no exception. E.g., `CopyOnWriteArrayList`, `ConcurrentHashMap`.

**Q: Difference between synchronized vs concurrent collections?**
> - **Synchronized** (`Collections.synchronizedList`): Locks the entire collection on every operation — lower throughput.
> - **Concurrent** (`ConcurrentHashMap`): Uses segment/bucket-level locking — higher throughput, better for multi-threaded use.

**Q: How does HashMap work internally?**
> Uses an array of buckets. Key's `hashCode()` determines the bucket index. If collision occurs, entries are stored as a linked list (Java 8+: converts to a balanced tree when bucket size > 8).

**Q: How does `map.get(key)` work internally?**
> 1. Computes `hashCode()` of key → finds bucket index.
> 2. Traverses the linked list/tree in that bucket.
> 3. Uses `equals()` to find the matching key.
> 4. Returns the associated value.

---

## 🔹 Design Patterns

**Q: Which design patterns have you used?**
> Singleton, Factory, Builder, Strategy, Observer, Decorator, Proxy, Template Method, Chain of Responsibility.

**Q: Why are they called design patterns?**
> They are reusable, proven solutions to commonly occurring software design problems — not code, but templates/blueprints for solving problems in a structured way.

**Q: Can you write a Singleton class?**
```java
public class Singleton {
    private static volatile Singleton instance;

    private Singleton() {}

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) instance = new Singleton();
            }
        }
        return instance;
    }
}
```

**Q: How do you prevent multiple instances in Singleton?**
> - Private constructor (prevents `new`)
> - `volatile` keyword (prevents instruction reordering)
> - Double-checked locking with `synchronized`
> - Or use `enum` Singleton (safest — handles serialization too)

---

## 🔹 Java 8 — Functional Interfaces, Streams, Lambdas

**Q: What is the use of functional interfaces?**
> An interface with exactly one abstract method, used as the target for lambda expressions. E.g., `Runnable`, `Comparator`, `Predicate`, `Function`, `Consumer`, `Supplier`.

**Q: What are the advantages of functional interfaces?**
> Enable functional programming style, reduce boilerplate, improve readability, and allow passing behavior as parameters.

**Q: How do you use streams and lambda expressions?**
```java
List<String> names = employees.stream()
    .filter(e -> e.isActive())
    .map(Employee::getName)
    .collect(Collectors.toList());
```

**Q: How do you filter employees with salary > 50k using streams?**
```java
List<Employee> result = employees.stream()
    .filter(e -> e.getSalary() > 50000)
    .collect(Collectors.toList());
```

**Q: How do you group employees by department using streams?**
```java
Map<String, List<Employee>> grouped = employees.stream()
    .collect(Collectors.groupingBy(Employee::getDepartment));
```

**Q: What is flatMap and when do you use it?**
> `flatMap` flattens a stream of collections into a single stream. Use it when each element maps to multiple elements.
```java
List<String> allSkills = employees.stream()
    .flatMap(e -> e.getSkills().stream())
    .collect(Collectors.toList());
```

**Q: What is the use of Optional in Java 8?**
> Avoids `NullPointerException` by wrapping a potentially null value. Forces explicit null handling.
```java
Optional<String> name = Optional.ofNullable(user.getName());
name.ifPresent(System.out::println);
String val = name.orElse("Unknown");
```

---

## 🔹 Spring Boot

**Q: How do you implement the same interface in multiple classes?**
> Each class implements the interface. Use `@Qualifier` in Spring to inject the specific implementation.
```java
@Service("paypal")
public class PaypalPayment implements PaymentService { ... }

@Service("stripe")
public class StripePayment implements PaymentService { ... }

@Autowired @Qualifier("paypal")
private PaymentService paymentService;
```

**Q: How do you handle multiple DB connections in Spring Boot?**
> Define multiple `DataSource` beans with `@Primary` and `@Qualifier`. Use separate `EntityManagerFactory` and `TransactionManager` per datasource. Configure via `application.yml` with different prefixes.

**Q: Have you used Spring Boot Actuator? What is its purpose?**
> Yes. Actuator exposes production-ready endpoints like `/actuator/health`, `/actuator/metrics`, `/actuator/info`, `/actuator/env` for monitoring and management without writing custom code.

**Q: How do you check application health without Actuator?**
> Write a custom `/health` REST endpoint that checks DB connectivity, downstream API availability, and returns a status response.

**Q: How do you implement authentication and authorization?**
> Using Spring Security. Authentication via JWT tokens or OAuth2. Authorization via role-based access control using `@PreAuthorize("hasRole('ADMIN')")` or `HttpSecurity` config.

**Q: Difference between authentication vs authorization?**
> - **Authentication**: Verifying *who you are* (login with credentials).
> - **Authorization**: Verifying *what you can do* (access control based on roles/permissions).

**Q: Difference between @Component, @Service, @Repository, and @Controller?**
> All are Spring-managed beans (`@Component` is the base). Semantic differences:
> - `@Service` — business logic layer
> - `@Repository` — data access layer (also translates DB exceptions)
> - `@Controller` — web layer (handles HTTP requests)

**Q: What is @Transactional and how does it work?**
> Wraps a method in a database transaction. If the method completes successfully, changes are committed. If a `RuntimeException` is thrown, the transaction is rolled back. Spring uses AOP proxy to intercept the method call.

---

## 🔹 AWS & DevOps

**Q: How do you access application logs in AWS?**
> Via **CloudWatch Logs**. Applications push logs using the CloudWatch agent or AWS SDK. You can query logs using CloudWatch Insights with custom queries.

**Q: How do you troubleshoot production issues using correlation IDs?**
> A unique correlation ID is generated per request (at API Gateway or filter level) and propagated through all service calls via headers. Logs include this ID, so you can trace the full request flow across services in CloudWatch.

**Q: How do you deploy applications on EC2/ECS?**
> - **EC2**: SSH into instance, pull JAR/Docker image, run with systemd or Docker.
> - **ECS**: Push Docker image to ECR, update ECS task definition, deploy via ECS service (rolling update or blue/green via CodeDeploy).

**Q: What is the role of Docker in deployment?**
> Docker packages the application and its dependencies into a portable container image, ensuring consistent behavior across dev/staging/prod environments.

**Q: What is the first statement in a Dockerfile?**
> `FROM` — specifies the base image.
```dockerfile
FROM eclipse-temurin:17-jre
```

**Q: How do you use Terraform for infrastructure as code?**
> Define AWS resources (VPC, EC2, RDS, ECS, IAM) in `.tf` files. Use `terraform init`, `terraform plan`, `terraform apply` to provision. State is stored in S3 with DynamoDB for locking.

**Q: How do you monitor downstream APIs?**
> Use CloudWatch metrics, custom health-check endpoints, and alerting via SNS. Also use distributed tracing with AWS X-Ray or tools like Datadog/New Relic.

**Q: Why use custom heartbeat jobs when AWS provides monitoring?**
> AWS monitoring checks infrastructure health. Custom heartbeat jobs validate actual business logic — e.g., "can the app successfully call the downstream API and get a valid response?" — which AWS native monitoring doesn't cover.

---

## 🔹 Database

**Q: What databases have you worked with?**
> Oracle, PostgreSQL, MySQL, DynamoDB, Redis (caching).

**Q: How do you optimize queries?**
> Use proper indexes, avoid `SELECT *`, use pagination, analyze execution plans (`EXPLAIN`), avoid N+1 queries, use joins instead of subqueries where possible.

**Q: What indexing strategies have you used?**
> Single-column indexes, composite indexes, partial indexes, covering indexes. Avoid over-indexing as it slows writes.

**Q: How do you handle read-heavy operations?**
> Read replicas, caching with Redis/Memcached, database connection pooling (HikariCP), query result caching with `@Cacheable` in Spring.

---

## 🔹 Messaging

**Q: Have you used messaging systems like RabbitMQ, Artemis, or IBM MQ?**
> Yes. Used RabbitMQ for event-driven microservices and IBM MQ for enterprise integrations requiring guaranteed delivery.

**Q: How do you implement asynchronous communication?**
> Producer publishes a message to a queue/topic. Consumer listens and processes independently. In Spring Boot, use `@RabbitListener` or `@JmsListener`.

**Q: What retry mechanisms have you used?**
> Spring Retry (`@Retryable`), RabbitMQ dead-letter queues (DLQ), exponential backoff strategies, and circuit breakers with Resilience4j.

**Q: How do you handle downstream API failures?**
> Circuit breaker pattern (Resilience4j), retry with backoff, fallback methods, timeout configuration, and alerting via CloudWatch alarms.

---

## 🔹 Miscellaneous

**Q: Have you used Lombok? What is its purpose?**
> Yes. Lombok reduces boilerplate by auto-generating getters, setters, constructors, `equals`, `hashCode`, `toString` via annotations like `@Data`, `@Builder`, `@AllArgsConstructor`, `@Slf4j`.

**Q: How do you consume REST APIs in Java (e.g., RestTemplate)?**
```java
RestTemplate restTemplate = new RestTemplate();
ResponseEntity<String> response = restTemplate.getForEntity("https://api.example.com/data", String.class);
```
> For newer projects, prefer `WebClient` (non-blocking, from Spring WebFlux).

**Q: How do you process images into Oracle DB using Java APIs?**
> Read image as `byte[]`, store in Oracle `BLOB` column using JDBC `PreparedStatement.setBytes()` or JPA with `@Lob` annotation.
```java
@Lob
@Column(name = "image_data")
private byte[] imageData;
```

**Q: What is serialization and deserialization?**
> - **Serialization**: Converting a Java object to a byte stream (for storage/transmission).
> - **Deserialization**: Reconstructing the object from the byte stream.
> Implement `Serializable` interface. In REST APIs, Jackson handles JSON serialization/deserialization automatically.

**Q: What is ApplicationContext in Spring Boot? How is it different from BeanFactory?**
> - `BeanFactory`: Basic container, lazy initialization, only DI features.
> - `ApplicationContext`: Extends `BeanFactory`, adds event publishing, internationalization, AOP support, eager initialization. Always use `ApplicationContext` in Spring Boot.

**Q: Can you create custom annotations in Java?**
> Yes.
```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogExecutionTime {}
```
> Then use AOP to intercept methods annotated with `@LogExecutionTime` and add cross-cutting logic like logging execution time.

---

## 🔹 Additional Questions

**Q: When application starts, how to load data which does not change frequently without DB interaction?**

> Use `ApplicationRunner` or `CommandLineRunner` to load data once at startup and store it in an in-memory cache.

Best approaches:
- **Spring Cache (`@Cacheable` + `@PostConstruct`)** — simplest for single-instance apps
- **Caffeine / Guava Cache** — in-memory, fast, supports TTL and size eviction
- **Redis** — distributed cache, ideal for multi-instance/microservice setups

Example using `@PostConstruct` with a local map:
```java
@Service
public class ConfigCacheService {

    private Map<String, String> cache = new HashMap<>();

    @PostConstruct
    public void loadCache() {
        // load from file, config server, or a one-time DB call at startup
        cache.put("TAX_RATE", "18");
        cache.put("MAX_RETRY", "3");
    }

    public String get(String key) {
        return cache.get(key);
    }
}
```

Example using `ApplicationRunner`:
```java
@Component
public class DataLoader implements ApplicationRunner {

    private final ConfigCacheService cacheService;

    @Override
    public void run(ApplicationArguments args) {
        cacheService.loadCache();
    }
}
```

> For distributed environments, load into **Redis** at startup so all instances share the same cache without hitting the DB.

---

**Q: How would you design communication between multiple microservices without using Kafka or RabbitMQ?**

> Several alternatives depending on the use case:

| Approach | When to use |
|---|---|
| **REST (HTTP/HTTPS)** | Synchronous, request-response communication |
| **gRPC** | High-performance, low-latency, binary protocol between internal services |
| **GraphQL** | Flexible querying, client-driven data fetching |
| **AWS SQS / SNS** | Async messaging using managed AWS services |
| **Database polling** | Simple async via shared DB table (outbox pattern) |
| **WebSockets** | Real-time bidirectional communication |
| **Server-Sent Events (SSE)** | One-way real-time push from server to client |

Recommended design:
- **Sync calls** → REST with Resilience4j circuit breaker + retry
- **Async calls** → AWS SQS (fully managed, no infra to maintain)
- **Service discovery** → AWS Cloud Map or Eureka so services find each other dynamically

```java
// Async via SQS using AWS SDK
sqsClient.sendMessage(SendMessageRequest.builder()
    .queueUrl(queueUrl)
    .messageBody(payload)
    .build());
```

> For internal service-to-service calls, use **gRPC** for performance-critical paths and **REST** for standard integrations.

---

**Q: How would you troubleshoot Docker image push failures caused by storage limitations?**

> Step-by-step approach:

1. **Check the error message** — common errors:
   - `no space left on device` → Docker host disk full
   - `layer already exists` / `blob upload invalid` → registry storage issue
   - `unauthorized` → auth/token issue (not storage)

2. **Check Docker host disk usage:**
```bash
df -h                        # check disk space
docker system df             # check Docker-specific usage
```

3. **Clean up unused Docker resources on the host:**
```bash
docker system prune -a       # removes stopped containers, unused images, build cache
docker image prune -a        # removes all unused images
docker volume prune          # removes unused volumes
```

4. **Check ECR / registry storage:**
   - In AWS ECR: go to repository → check image count and size
   - Set up **ECR lifecycle policies** to auto-delete old/untagged images:
```json
{
  "rules": [{
    "rulePriority": 1,
    "selection": { "tagStatus": "untagged", "countType": "sinceImagePushed", "countUnit": "days", "countNumber": 7 },
    "action": { "type": "expire" }
  }]
}
```

5. **Use multi-stage builds** to reduce image size:
```dockerfile
FROM maven:3.9 AS build
WORKDIR /app
COPY . .
RUN mvn package -DskipTests

FROM eclipse-temurin:17-jre
COPY --from=build /app/target/app.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
```

6. **Increase disk space** on the Docker host (resize EBS volume on EC2 if on AWS).

---

**Q: How many replicas do you maintain for your application environments?**

> Typical setup per environment:

| Environment | Replicas | Reason |
|---|---|---|
| **Dev** | 1 | Cost saving, no HA needed |
| **QA / Staging** | 2 | Mirrors prod behavior, basic HA |
| **Production** | 3+ | High availability, zero-downtime deployments |

In **AWS ECS / Kubernetes**:
- Minimum 2 replicas in prod so rolling updates don't cause downtime
- Use **Auto Scaling** to scale out under load (e.g., CPU > 70%)
- Spread replicas across **multiple Availability Zones** for fault tolerance

```yaml
# ECS Service example
desiredCount: 3
deploymentConfiguration:
  minimumHealthyPercent: 50
  maximumPercent: 200
```

> In Kubernetes, use `HorizontalPodAutoscaler` to auto-scale between min/max replicas based on CPU/memory metrics.

---

**Q: What happens when a user enters a URL in the browser?**

> Full flow step by step:

1. **URL Parsing** — Browser parses the URL: protocol (`https`), domain (`example.com`), path (`/page`), query params.

2. **DNS Resolution** — Browser checks:
   - Local browser cache → OS cache → Router cache → ISP DNS → Root DNS → TLD DNS → Authoritative DNS
   - Returns the IP address of the server.

3. **TCP Connection** — Browser establishes a TCP connection with the server via **3-way handshake** (SYN → SYN-ACK → ACK).

4. **TLS Handshake (HTTPS)** — If HTTPS, TLS handshake occurs: server sends certificate, browser verifies it, session keys are exchanged for encrypted communication.

5. **HTTP Request** — Browser sends an HTTP GET request:
```
GET /page HTTP/1.1
Host: example.com
Accept: text/html
```

6. **Server Processing** — Request hits:
   - Load Balancer → routes to an available server instance
   - Application server (e.g., Spring Boot) processes the request
   - May query DB, call downstream APIs, fetch from cache

7. **HTTP Response** — Server returns response (HTML, JSON, etc.) with status code (200, 404, 500, etc.).

8. **Browser Rendering** — Browser:
   - Parses HTML → builds **DOM tree**
   - Parses CSS → builds **CSSOM tree**
   - Combines into **Render tree**
   - Fetches additional resources (JS, images, fonts)
   - Executes JavaScript
   - Paints the page on screen

9. **Connection Close / Keep-Alive** — TCP connection is closed or kept alive for subsequent requests.

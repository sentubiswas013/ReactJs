
## 11. Explain Spring Boot auto-configuration mechanism internally.

**How it works internally (step-by-step):**

1. When you start the app using `@SpringBootApplication`, it includes:

   * `@Configuration`
   * `@ComponentScan`
   * `@EnableAutoConfiguration`

2. `@EnableAutoConfiguration` triggers:

   ```java
   @Import(AutoConfigurationImportSelector.class)
   ```

3. `AutoConfigurationImportSelector`:

   * Reads metadata from:

     ```
     META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
     ```
   * Loads candidate auto-configuration classes.

4. Each auto-config class uses conditional annotations like:

   * `@ConditionalOnClass`
   * `@ConditionalOnMissingBean`
   * `@ConditionalOnProperty`

5. If conditions match → Beans are created automatically.

---

**Example:**

```java
@Configuration
@ConditionalOnClass(DataSource.class)
@ConditionalOnMissingBean(DataSource.class)
public class DataSourceAutoConfiguration {

    @Bean
    public DataSource dataSource() {
        return new HikariDataSource();
    }
}
```

If `HikariCP` is on classpath and no `DataSource` defined → Boot creates one.

---

## 12. How does Spring Bean lifecycle work in detail?

**Lifecycle Steps:**

1. Bean Instantiation
2. Dependency Injection
3. `BeanNameAware`, `BeanFactoryAware`
4. `BeanPostProcessor#postProcessBeforeInitialization`
5. `@PostConstruct`
6. `InitializingBean#afterPropertiesSet`
7. Custom `initMethod`
8. Bean ready to use
9. On shutdown:

   * `@PreDestroy`
   * `DisposableBean#destroy`
   * custom `destroyMethod`

---

**Example:**

```java
@Component
public class MyBean {

    @PostConstruct
    public void init() {
        System.out.println("Bean initialized");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Bean destroyed");
    }
}
```

---

## 13. How does @Transactional work internally?

Spring uses **AOP proxy mechanism**.

Internally:

1. `@Transactional` is detected.
2. Spring creates proxy (JDK dynamic proxy or CGLIB).
3. Proxy wraps method call.
4. Transaction interceptor logic:

   * Start transaction
   * Execute method
   * Commit if success
   * Rollback if RuntimeException

---

**Flow:**

Client → Proxy → TransactionInterceptor → Target Method

---

**Example:**

```java
@Service
public class OrderService {

    @Transactional
    public void placeOrder() {
        // DB operations
    }
}
```

Internally uses:

```
TransactionInterceptor
PlatformTransactionManager
```

---

## 14. How do you handle transaction propagation in complex business logic?

Spring supports propagation types:

* `REQUIRED` (default)
* `REQUIRES_NEW`
* `NESTED`
* `MANDATORY`
* `SUPPORTS`
* `NOT_SUPPORTED`
* `NEVER`

---

**Real scenario:**

* Main order transaction
* Audit logging should always commit

Solution:

```java
@Transactional
public void placeOrder() {
    payment();
    auditService.logAudit(); // REQUIRES_NEW
}
```

```java
@Transactional(propagation = Propagation.REQUIRES_NEW)
public void logAudit() {
    // independent transaction
}
```

Best practice:

* Use `REQUIRES_NEW` for logging
* Use `NESTED` for partial rollback
* Keep transactions short

---

## 15. How do you implement distributed tracing?

Use:

* OpenTelemetry
* Zipkin
* Jaeger
* Spring Boot 3 → Micrometer Tracing

---

**Steps:**

1. Add dependency:

```xml
<dependency>
    <groupId>io.micrometer</groupId>
    <artifactId>micrometer-tracing-bridge-otel</artifactId>
</dependency>
```

2. Configure exporter:

```java
management.tracing.sampling.probability=1.0
```

3. Add trace context propagation (HTTP headers)

Spring automatically adds:

```
traceId
spanId
```

Flow:
Service A → traceId propagated → Service B → Service C

---

## 16. How would you design centralized exception handling for 20+ microservices?

**Architecture approach:**

1. Create shared library:

   * Common ErrorResponse class
   * Base exception classes
   * Global handler

2. Use:

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusiness(BusinessException ex) {
        return ResponseEntity.badRequest()
                .body(new ErrorResponse("BUSINESS_ERROR", ex.getMessage()));
    }
}
```

3. API Gateway handles:

   * Fallback
   * Standard error format

4. Logging + tracing integrated.

Best practice:

* Standard error contract
* Error codes instead of raw messages
* Correlation ID in response

---

## 17. How do you reduce cold start time of Spring Boot apps?

Techniques:

1. Remove unnecessary auto-config:

```java
spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
```

2. Lazy initialization:

```java
spring.main.lazy-initialization=true
```

3. Use:

* GraalVM Native Image

4. Use Spring AOT processing
5. Reduce component scanning scope
6. Use lighter logging config

---

## 18. How to implement feature flags in Spring Boot?

**Approach 1: Using properties**

```java
feature.payment.enabled=true
```

```java
@ConditionalOnProperty(
  name = "feature.payment.enabled",
  havingValue = "true"
)
@Service
public class PaymentService {}
```

---

**Approach 2: Using external config**

* Config Server
* Database-based flags
* Toggle per user

Simple runtime check:

```java
@Value("${feature.newUI:false}")
private boolean newUI;

if(newUI){
   // new flow
}
```

---

## 19. How do you handle circular dependency issues?

Circular example:

A → B
B → A

---

**Solutions:**

1. Use constructor injection (detect early)
2. Refactor responsibilities
3. Use `@Lazy`

```java
@Service
public class A {

    private final B b;

    public A(@Lazy B b) {
        this.b = b;
    }
}
```

4. Use setter injection (temporary fix)

Best practice:
Redesign to remove tight coupling.

---

## 20. How do you design multi-module Spring Boot project?

**Typical structure:**

```
parent
 ├── common
 ├── user-service
 ├── order-service
 └── api-gateway
```

---

**Step 1: Parent POM**

```xml
<packaging>pom</packaging>

<modules>
   <module>common</module>
   <module>user-service</module>
</modules>
```

---

**Step 2: Common module**

* DTOs
* Exceptions
* Utilities

---

**Step 3: Service module**

Depends on common:

```xml
<dependency>
   <groupId>com.example</groupId>
   <artifactId>common</artifactId>
</dependency>
```

## 21. Design a payment API that must be idempotent.

**Goal**

Ensure multiple identical requests (due to retries/network issues) result in **only one payment** being processed.

**Steps**

1. **Client sends Idempotency-Key** (UUID).
2. Store key + request hash in DB with unique constraint.
3. If same key received:

   * If already processed → return saved response.
   * If in-progress → return 409 or wait.
4. Use DB transaction to ensure atomicity.

**Table Design

```sql
CREATE TABLE payments (
  id BIGSERIAL PRIMARY KEY,
  idempotency_key VARCHAR(255) UNIQUE,
  amount DECIMAL,
  status VARCHAR(50),
  response JSONB
);
```

**Controller Example (Spring Boot)**

```java
@PostMapping("/payments")
public ResponseEntity<?> createPayment(
    @RequestHeader("Idempotency-Key") String key,
    @RequestBody PaymentRequest request) {

    Optional<Payment> existing = repo.findByIdempotencyKey(key);
    if (existing.isPresent()) {
        return ResponseEntity.ok(existing.get().getResponse());
    }

    Payment payment = service.processPayment(request, key);
    return ResponseEntity.ok(payment.getResponse());
}
```

---

## 22. How do you implement API versioning in enterprise systems?

**Common Strategies**

1. URI Versioning
   `/api/v1/payments`
2. Header Versioning
   `Accept: application/vnd.company.v2+json`
3. Query Param
   `/payments?version=2`

**Recommended: URI Versioning (Simple & Clear)**

```java
@RestController
@RequestMapping("/api/v1/payments")
class PaymentV1Controller {}

@RestController
@RequestMapping("/api/v2/payments")
class PaymentV2Controller {}
```

**Best Practice**

* Never break existing contracts.
* Deprecate old versions gradually.
* Maintain backward compatibility.

---

## 23. How do you secure APIs using JWT?

**Flow**

1. User logs in → Server validates credentials.
2. Server generates JWT.
3. Client sends JWT in header:

   ```
   Authorization: Bearer <token>
   ```
4. Server validates signature & expiry.

**Spring Security Example**

```java
http
  .csrf().disable()
  .authorizeHttpRequests(auth -> auth
      .requestMatchers("/auth/**").permitAll()
      .anyRequest().authenticated())
  .addFilter(new JwtAuthenticationFilter());
```

**JWT Creation**

```java
String token = Jwts.builder()
    .setSubject(username)
    .setExpiration(new Date(System.currentTimeMillis() + 86400000))
    .signWith(secretKey)
    .compact();
```

**Best Practices**

* Use short expiry
* Store secret securely
* Use HTTPS only

---

## 24. How would you implement OAuth2 login?

**Flow (Authorization Code)**

1. User redirected to Provider (Google).
2. User logs in.
3. Provider redirects with authorization code.
4. Backend exchanges code for access token.
5. Fetch user info.

Example Provider: Google

**Spring Boot Config**

```yaml
spring:
  security:
    oauth2:
      client:
        registration:
          google:
            client-id: xxx
            client-secret: xxx
```

**Controller**

```java
@GetMapping("/login/oauth2/code/google")
public String loginSuccess(OAuth2AuthenticationToken auth) {
    return "Welcome " + auth.getPrincipal().getAttribute("name");
}
```

---

## 25. How do you design rate limiting in distributed systems?

**Approaches**

1. Token Bucket
2. Leaky Bucket
3. Fixed Window
4. Sliding Window (Recommended)

**Distributed Setup**

* Use Redis atomic counters.
* Use Lua script for atomicity.

**Example (Redis Sliding Window)**

```java
Long count = redisTemplate.opsForValue()
    .increment("rate:" + userId);

if (count == 1)
    redisTemplate.expire("rate:" + userId, 1, TimeUnit.MINUTES);

if (count > 100)
    throw new TooManyRequestsException();
```

**Best Practice**

* Apply at API Gateway level.
* Return HTTP 429.

---

## 26. How do you implement API Gateway pattern?

**Purpose**

Single entry point for:

* Auth
* Rate limiting
* Routing
* Logging

**Common Tools**

* Spring Cloud Gateway
* Kong
* NGINX

**Spring Cloud Gateway Example**

```yaml
spring:
  cloud:
    gateway:
      routes:
        - id: payment-service
          uri: http://localhost:8081
          predicates:
            - Path=/payments/**
```

---

## 27. How do you log sensitive data securely?

**Rules**

1. Never log passwords, tokens, card numbers.
2. Mask sensitive fields.
3. Encrypt logs at rest.
4. Restrict access (RBAC).

**Masking Example**

```java
public String maskCard(String card) {
    return "****-****-****-" + card.substring(card.length()-4);
}
```

**Logback Pattern**

```xml
<pattern>%d %p %c - %msg%n</pattern>
```

Use centralized logging (ELK).

---

## 28. How do you handle partial failures in API aggregation?

### Example: Order Service calls:

* Payment Service
* Inventory Service
* Shipping Service

**Strategies**

1. Timeout per service
2. Circuit Breaker
3. Fallback Response
4. Return partial data with status

### Example Tool

* Resilience4j

**Example**

```java
@CircuitBreaker(name = "paymentService", fallbackMethod = "fallback")
public PaymentResponse callPayment() {
    return paymentClient.pay();
}

public PaymentResponse fallback(Exception e) {
    return new PaymentResponse("PENDING");
}
```

---

## 29. How do you implement request tracing?

**Goal

Track request across microservices.

**Use Correlation ID**

1. Generate UUID at gateway.
2. Pass via header:

   ```
   X-Correlation-ID
   ```
3. Log it everywhere.

**Spring Filter**

```java
public class CorrelationFilter extends OncePerRequestFilter {
    protected void doFilterInternal(...) {
        String id = UUID.randomUUID().toString();
        response.addHeader("X-Correlation-ID", id);
        filterChain.doFilter(request, response);
    }
}
```

**Distributed Tracing Tools**

* Zipkin
* Jaeger

---

## 30. How do you design retry mechanism safely?

### ⚠️ Problem

Retries can cause:

* Duplicate payments
* Thundering herd
* Cascading failures

**Safe Retry Principles**

1. Only retry idempotent operations.
2. Use exponential backoff.
3. Add jitter.
4. Limit max retries.
5. Use circuit breaker.

**Example (Resilience4j)**

```java
@Retry(name = "paymentRetry", fallbackMethod = "fallback")
public PaymentResponse process() {
    return paymentClient.pay();
}
```

## 31. You observe N+1 query issue. How detect and fix?

### 🔎 Detection

1. Enable SQL logging:

```java
spring.jpa.show-sql=true
logging.level.org.hibernate.SQL=DEBUG
```

2. Use Hibernate statistics:

```java
spring.jpa.properties.hibernate.generate_statistics=true
```

3. Use profiling tools (e.g., DB slow query logs).

If you see:

* 1 query for parent
* N queries for child → N+1 problem.

---

### 🛠 Fix Methods

**1. Use JOIN FETCH**

```java
@Query("SELECT o FROM Order o JOIN FETCH o.items")
List<Order> findAllWithItems();
```

**2. Use EntityGraph**

```java
@EntityGraph(attributePaths = {"items"})
List<Order> findAll();
```

**3. Use batch fetching**

```java
spring.jpa.properties.hibernate.default_batch_fetch_size=50
```

---

## 32. How do you optimize slow queries in production?

**Step-by-step approach:**

1. Identify slow queries

   * DB slow query logs
   * APM tools

2. Analyze execution plan:

```sql
EXPLAIN SELECT * FROM orders WHERE user_id = 10;
```

3. Add proper indexing
4. Avoid SELECT *
5. Optimize joins
6. Use pagination
7. Reduce unnecessary fetch joins
8. Use caching

---

Example optimization:

Before:

```java
List<Order> findAll();
```

After:

```java
Page<Order> findAll(Pageable pageable);
```

---

## 33. How does Hibernate caching work?

Hibernate supports:

1. First-Level Cache (Session cache)
2. Second-Level Cache (SessionFactory cache)
3. Query Cache

Caching reduces DB hits.

Flow:

* Entity requested
* Check 1st level cache
* If not found → check 2nd level cache
* If not found → DB

---

Enable 2nd level cache:

```java
spring.jpa.properties.hibernate.cache.use_second_level_cache=true
spring.jpa.properties.hibernate.cache.region.factory_class=org.hibernate.cache.jcache.JCacheRegionFactory
```

---

## 34. Explain 1st level vs 2nd level cache.

### 1st Level Cache

* Scope: Hibernate Session
* Default enabled
* Cannot disable
* Works per transaction

Example:

```java
User user1 = entityManager.find(User.class, 1L);
User user2 = entityManager.find(User.class, 1L);
// Second call does not hit DB
```

---

### 2nd Level Cache

* Scope: SessionFactory
* Shared across sessions
* Needs provider (e.g., Ehcache)

Example:

```java
@Cacheable
@Entity
public class User {}
```

---

## 35. When would you use native queries?

Use native queries when:

1. Complex joins
2. Database-specific features
3. Performance tuning
4. Stored procedures
5. Bulk updates

Example:

```java
@Query(value = "SELECT * FROM users WHERE status = 'ACTIVE'", 
       nativeQuery = true)
List<User> findActiveUsers();
```

Best practice:

* Use JPQL first
* Use native only when required

---

## 36. How do you implement optimistic locking?

Use `@Version` field.

---

**Step 1: Add version column**

```java
@Entity
public class Product {

    @Id
    private Long id;

    @Version
    private Integer version;
}
```

---

### How it works:

Update query becomes:

```sql
UPDATE product 
SET name=?, version=version+1 
WHERE id=? AND version=?
```

If version mismatch → `OptimisticLockException`.

Best for:

* High read, low conflict systems.

---

## 37. How do you handle bulk inserts efficiently?

### Problems:

* Hibernate flush per entity
* Memory overhead

---

### Solution 1: Batch inserts

```java
spring.jpa.properties.hibernate.jdbc.batch_size=50
spring.jpa.properties.hibernate.order_inserts=true
```

---

### Solution 2: Manual flush & clear

```java
for (int i = 0; i < list.size(); i++) {
    entityManager.persist(list.get(i));
    if (i % 50 == 0) {
        entityManager.flush();
        entityManager.clear();
    }
}
```

---

**Solution 3:**

Use JDBC template for very large datasets.

---

## 38. How do you design database indexing strategy?

**Step-by-step:**

1. Analyze query patterns
2. Index frequently filtered columns
3. Use composite index for multi-column WHERE
4. Avoid over-indexing
5. Index foreign keys
6. Monitor index usage

---

Example:

```sql
CREATE INDEX idx_user_email ON users(email);
```

Composite index:

```sql
CREATE INDEX idx_order_user_status 
ON orders(user_id, status);
```

Rule:
Index order should match query order.

---

## 39. How do you implement read replica routing?

Architecture:

* Primary DB → Writes
* Replica DB → Reads

---

**Step 1: Define two datasources**

```java
@Bean
@Primary
public DataSource writeDataSource() {}

@Bean
public DataSource readDataSource() {}
```

---

**Step 2: Routing datasource**

Extend `AbstractRoutingDataSource`:

```java
public class RoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return TransactionSynchronizationManager.isCurrentTransactionReadOnly()
                ? "READ"
                : "WRITE";
    }
}
```

---

**Step 3: Use read-only transaction**

```java
@Transactional(readOnly = true)
public List<User> findAll() {}
```

---

## 40. How do you handle schema migration in CI/CD?

Use migration tools like:

* Flyway
* Liquibase

---

**Steps:**

1. Create versioned SQL file:

```
V1__create_user_table.sql
```

2. On app startup:

* Tool checks schema history table
* Runs pending migrations

---

Example dependency:

```xml
<dependency>
  <groupId>org.flywaydb</groupId>
  <artifactId>flyway-core</artifactId>
</dependency>
```

## 41. How do you implement service discovery?

**Goal**

Automatically register and discover services dynamically in microservices.

**Types**

1. **Client-Side Discovery**
2. **Server-Side Discovery**

**Common Tool**

* Netflix Eureka

---

**Steps (Client-Side with Eureka)**

1. Add Eureka Server dependency
2. Enable Eureka Server
3. Register services as clients
4. Use service name instead of IP

---

**Eureka Server**

```java
@SpringBootApplication
@EnableEurekaServer
public class DiscoveryServerApp {
    public static void main(String[] args) {
        SpringApplication.run(DiscoveryServerApp.class, args);
    }
}
```

---

**Client Configuration**

```yaml
spring:
  application:
    name: payment-service
eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/
```

---

**Calling Another Service**

```java
@LoadBalanced
@Bean
RestTemplate restTemplate() {
    return new RestTemplate();
}

restTemplate.getForObject("http://order-service/orders", String.class);
```

---

## 42. How do you implement circuit breaker?

**Goal**

Prevent cascading failures when a service is down.

**States**

* CLOSED
* OPEN
* HALF-OPEN

**Tool**

* Resilience4j

---

**Steps**

1. Configure failure threshold
2. Wrap remote call
3. Provide fallback

---

**Example**

```java
@CircuitBreaker(name = "inventoryService", fallbackMethod = "fallback")
public String callInventory() {
    return restTemplate.getForObject("http://inventory-service/items", String.class);
}

public String fallback(Exception e) {
    return "Inventory temporarily unavailable";
}
```

---

## 43. How do you design Saga pattern?

**Goal**

Manage distributed transactions without 2PC.

**Two Types**

1. Choreography (Event-driven)
2. Orchestration (Central coordinator)

---

**Example (Order Flow)**

1. Order Service → Create Order
2. Payment Service → Deduct Payment
3. Inventory Service → Reserve Stock
4. If failure → Compensation (Refund payment)

---

**Orchestrator Example**

```java
public void processOrder(Order order) {
    paymentService.pay(order);
    inventoryService.reserve(order);
}
```

On failure:

```java
public void compensate(Order order) {
    paymentService.refund(order);
}
```

---

**Messaging Tool**

* Apache Kafka

---

## 44. How do you ensure eventual consistency?

**Strategy**

1. Use asynchronous messaging.
2. Publish domain events.
3. Retry failed events.
4. Use idempotent consumers.

---

**Example**

```java
kafkaTemplate.send("order-created", order);
```

Consumer updates local DB.

---

**Key Practices**

* Outbox pattern
* Retry with backoff
* Dead Letter Queue

---

## 45. How do you handle message duplication in Kafka?

**Causes**

* Consumer retries
* Network failures
* At-least-once delivery

---

**Solutions**

1. Enable idempotent producer
2. Use unique event ID
3. Maintain processed-message table

---

**Idempotent Consumer Example**

```java
if (processedRepo.existsByEventId(eventId)) return;

processEvent();
processedRepo.save(eventId);
```

---

**Kafka Config**

```java
enable.idempotence=true
acks=all
```

---

## 46. How do you implement distributed locking?

**Options**

1. Redis
2. Zookeeper
3. Database lock

---

**Redis Lock Example**

```java
Boolean success = redisTemplate.opsForValue()
    .setIfAbsent("lock:order:123", "locked", 10, TimeUnit.SECONDS);

if (Boolean.TRUE.equals(success)) {
    try {
        processOrder();
    } finally {
        redisTemplate.delete("lock:order:123");
    }
}
```

---

**Best Practice**

* Use expiration
* Handle lock renewal
* Avoid deadlocks

---

## 47. How do you secure inter-service communication?

**Strategies**

1. mTLS (Mutual TLS)
2. OAuth2 Client Credentials
3. Service Mesh
4. Network Policies

---

**OAuth2 Example**

```java
headers.setBearerAuth(token);
```

---

**Tools**

* Istio
* Spring Security

---

## 48. How do you implement centralized configuration?

**Goal**

Manage configs for all services in one place.

**Tool**

* Spring Cloud Config

---

**Config Server**

```java
@EnableConfigServer
@SpringBootApplication
public class ConfigServerApp {}
```

---

**application.yml**

```yaml
spring:
  cloud:
    config:
      uri: http://localhost:8888
```

---

**Store Configs in Git**

Externalized config → dynamic refresh using `/actuator/refresh`

---

## 49. How do you handle blue-green deployment?

**Concept**

Two identical environments:

* Blue (Current)
* Green (New)

Switch traffic after validation.

---

**Steps**

1. Deploy new version to Green.
2. Run smoke tests.
3. Switch load balancer.
4. Monitor.
5. Rollback if needed.

---

**Kubernetes Example**

```yaml
strategy:
  type: RollingUpdate
```

Or manage with two services and switch ingress.

---

## 50. How do you monitor and observe microservices in production?

**Three Pillars**

1. Logs
2. Metrics
3. Traces

---

**Metrics**

* Prometheus
* Grafana

```yaml
management:
  endpoints:
    web:
      exposure:
        include: prometheus
```

---

**Logging**

* Centralized logging (ELK Stack)

---

**Tracing**

* Jaeger

---

**Health Check**

```java
@GetMapping("/health")
public String health() {
    return "UP";
}
```

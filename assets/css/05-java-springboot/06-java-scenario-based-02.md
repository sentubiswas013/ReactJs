# Java Full Stack · Architect Engineer – Scenario-Based Interview Questions (Part 2)

Scenario-based interview questions for **Java Full Stack Developer / Engineer / Architect** roles (Senior / Lead level), with context and expected answers.

| Part | Level | Questions | Topic Areas |
|------|-------|-----------|-------------|
| Part I | Full Stack | Q1–Q23 | System Design, Spring, Frontend, Security, DevOps, Testing, Behavioral |
| Part II | Core Java | Q24–Q41 | Core Java, Advanced Java, Design Patterns & JVM |
| Part III | Senior / Lead / Architect | Q42–Q61 | Core Java, Architecture, Performance, Microservices, DB / JPA |

---

## Part I: Full Stack Developer Scenarios

### 🏗️ System Design & Architecture

#### Q1. High-Traffic Spike Scenario

**Q:** *How would you handle a sudden spike in traffic (e.g., Black Friday sale) where your application goes from 10K to 1M requests per second?* [dev](https://dev.to/bsanju/java-interview-questions-for-experienced-full-stack-engineers-2025-edition-h47)

**Expected Answer:**
- Implement **auto-scaling** (horizontal scaling with Kubernetes pods)
- Add **load balancing** (NGINX, AWS ALB)
- Use **caching layers** (Redis, CDN) for static content
- Implement **rate limiting** and **circuit breakers** (Resilience4j)
- Enable **database connection pooling** and **read replicas**
- Use **message queues** (Kafka, RabbitMQ) for async processing
- Enable **CDN** for static assets

#### Q2. Database Scale Scenario

**Q:** *You have a table with 1 billion records and queries are taking 10+ seconds. How do you optimize it?* [usebraintrust](https://www.usebraintrust.com/hire/interview-questions/full-stack-developers)

**Expected Answer:**
- Add **proper indexing** (B-tree, composite indexes)
- Implement **database partitioning** (range, hash, list)
- Use **query optimization** (EXPLAIN ANALYZE)
- Implement **database sharding** (horizontal partitioning)
- Add **read replicas** for read-heavy workloads
- Consider **NoSQL** (MongoDB, Cassandra) for specific use cases
- Implement **caching** (Redis) for frequently accessed data

#### Q3. Microservices Communication Failure

**Q:** *Service A calls Service B, but Service B is down. How do you handle this gracefully?*

**Expected Answer:**
- Implement **circuit breaker pattern** (Resilience4j, Hystrix)
- Use **retry with exponential backoff**
- Implement **fallback mechanisms** (return cached data or default response)
- Add **timeout configurations**
- Use **message queues** for async communication
- Implement **bulkhead pattern** to isolate failures

#### Q4. Distributed Transaction Problem

**Q:** *You need to transfer money between two microservices (Service A and Service B). How do you ensure data consistency?*

**Expected Answer:**
- Use **Saga pattern** (choreography or orchestration)
- Implement **outbox pattern** for reliable event publishing
- Use **eventual consistency** with compensating transactions
- Consider **two-phase commit** (2PC) for strong consistency (but avoid if possible)
- Implement **idempotency** to handle retries

#### Q5. Memory Leak in Production

**Q:** *Your Java application is running slowly and consuming increasing memory over time. How do you diagnose and fix it?*

**Expected Answer:**
- Use **heap dump analysis** (MAT, JVisualVM)
- Enable **GC logging** (`-Xlog:gc*`)
- Identify **unreleased objects** (static collections, unclosed resources)
- Check for **connection pool leaks** (database, HTTP)
- Use **profiling tools** (JProfiler, YourKit)
- Implement **proper resource cleanup** (try-with-resources)
- Monitor with **APM tools** (New Relic, Dynatrace)

---

### ☕ Spring & Backend

#### Q6. @Transactional Not Working

**Q:** *You added `@Transactional` to a method but transactions are not being created. What could be the reason?*

**Expected Answer:**
- **Self-invocation problem** – calling method from within same class bypasses proxy [stackoverflow](https://stackoverflow.com/questions/1099025/spring-transactional-what-happens-in-background)
- Method is `private` or `final` (proxy can't intercept)
- Exception is **caught and swallowed** (rollback not triggered)
- Wrong **rollback rules** (only rolls back on `RuntimeException` by default)
- Using **`REQUIRED`** but calling from non-transactional context

**Fix:** Inject self via constructor or use `AopContext.currentProxy()`

#### Q7. Circular Dependency in Spring

**Q:** *Service A depends on Service B, and Service B depends on Service A. How do you resolve this?*

**Expected Answer:**
- Use **`@Lazy` annotation** on one of the dependencies
- Refactor to **extract common logic** into a third service (Service C)
- Use **constructor injection** instead of field injection
- Apply **event-driven architecture** (publish/subscribe)
- **Redesign** to break the dependency (often the best solution)

#### Q8. N+1 Query Problem

**Q:** *You notice 1000 database queries when loading 100 entities. How do you fix this?*

**Expected Answer:**
- Use **JOIN FETCH** in JPQL queries
- Implement **EntityGraph** in Spring Data JPA
- Use **@BatchSize** annotation for batch loading
- Enable **Hibernate statistics** to detect N+1
- Use **DTO projections** instead of full entities

#### Q9. API Response Time Too Slow

**Q:** *Your REST API endpoint takes 5 seconds to respond. How do you optimize it?*

**Expected Answer:**
- Add **database indexing** and optimize queries
- Implement **caching** (Redis, Caffeine)
- Use **async processing** for non-critical operations
- Implement **pagination** (don't return all data at once)
- Add **compression** (GZIP)
- Use **connection pooling** (HikariCP)
- Profile with **Spring Boot Actuator** and **Micrometer**

---

### 🌐 Frontend

#### Q10. Frontend Performance Issue

**Q:** *Your React/Angular application is loading slowly. How do you improve it?* [usebraintrust](https://www.usebraintrust.com/hire/interview-questions/full-stack-developers)

**Expected Answer:**
- Implement **code splitting** (lazy loading routes)
- Use **CDN** for static assets [usebraintrust](https://www.usebraintrust.com/hire/interview-questions/full-stack-developers)
- Optimize **bundle size** (tree shaking, minification)
- Implement **image optimization** (WebP, lazy loading)
- Add **service workers** for offline caching
- Use **React.memo** / **useMemo** for memoization
- Enable **HTTP/2** and **GZIP compression**

#### Q11. State Management in SPA

**Q:** *You're building a complex dashboard with real-time data. How do you manage state?*

**Expected Answer:**
- Use **Redux/Context API** (React) or **NgRx** (Angular)
- Implement **server-state caching** (React Query, SWR)
- Use **WebSockets** for real-time updates [usebraintrust](https://www.usebraintrust.com/hire/interview-questions/full-stack-developers)
- Implement **optimistic updates** for better UX
- Handle **error boundaries** and **loading states**

#### Q12. Cross-Origin Authentication

**Q:** *Your frontend is on `app.example.com` and backend on `api.example.com`. How do you handle authentication?*

**Expected Answer:**
- Use **JWT** stored in **HTTP-only cookies** (not localStorage)
- Implement **CORS** configuration on backend
- Use **OAuth2/OIDC** with proper redirect URIs
- Implement **refresh token rotation**
- Set **SameSite** cookie attributes

---

### 🔐 Security

#### Q13. SQL Injection Attack

**Q:** *You discover a SQL injection vulnerability in your legacy code. How do you fix it?*

**Expected Answer:**
- Use **parameterized queries** (PreparedStatement)
- Use **JPA/Hibernate** instead of raw SQL
- Implement **input validation** and **sanitization**
- Use **ORM** properly (avoid `nativeQuery` with string concatenation)
- Add **WAF** (Web Application Firewall)
- Implement **SQL injection scanning** in CI/CD

#### Q14. XSS Attack Prevention

**Q:** *How do you prevent Cross-Site Scripting (XSS) in your application?*

**Expected Answer:**
- **Escape output** in frontend (React auto-escapes by default)
- Use **Content Security Policy (CSP)** headers
- Sanitize **user input** on both frontend and backend
- Avoid `dangerouslySetInnerHTML` in React
- Implement **HTTP-only cookies** to prevent XSS stealing cookies

#### Q15. API Security Breach

**Q:** *You notice unauthorized API calls happening frequently. How do you secure your APIs?*

**Expected Answer:**
- Implement **rate limiting** (Token Bucket, Sliding Window)
- Use **API Gateway** with authentication
- Add **request signing** and **timestamp validation**
- Implement **OAuth2 scopes** and **RBAC**
- Use **IP whitelisting** for sensitive endpoints
- Enable **audit logging** for all API calls

---

### 🚀 DevOps & Production

#### Q16. Production Deployment Failure

**Q:** *You deployed a new version but it's causing errors in production. What do you do?*

**Expected Answer:**
- **Rollback immediately** to previous stable version
- Enable **canary deployment** or **blue-green deployment** for safer releases
- Use **feature flags** to disable problematic features
- Check **logs** (ELK stack, CloudWatch)
- Enable **health checks** and **readiness probes**
- Implement **automated rollback** in CI/CD

#### Q17. Database Migration in Production

**Q:** *You need to add a new column to a table with 1 billion records without downtime. How do you do it?*

**Expected Answer:**
- Use **expanding-contract pattern**:
  1. Add column (nullable)
  2. Deploy code that writes to both old and new columns
  3. Backfill data in batches
  4. Deploy code that reads from new column
  5. Remove old column in next deployment
- Use **online schema migration tools** (Liquibase, Flyway)
- Perform migration during **low-traffic window**

#### Q18. Container Orchestration Failure

**Q:** *Your Kubernetes pods are crashing repeatedly. How do you debug?*

**Expected Answer:**
- Check **pod logs** (`kubectl logs`)
- Check **pod events** (`kubectl describe pod`)
- Check **resource limits** (CPU/memory)
- Verify **liveness/readiness probes**
- Check **deployment config** and **configmaps**
- Use **kubectl port-forward** to debug locally

---

### 🧪 Testing

#### Q19. Flaky Tests in CI/CD

**Q:** *Your integration tests are failing intermittently. How do you fix this?*

**Expected Answer:**
- Use **TestContainers** for real database testing
- Implement **proper synchronization** (avoid race conditions)
- Use **mocking** for external dependencies
- Add **retries** for known flaky tests
- Isolate **test data** (use transactions, clean up after each test)
- Use **deterministic test data**

#### Q20. Performance Testing

**Q:** *How do you ensure your application can handle expected load before production?*

**Expected Answer:**
- Use **load testing tools** (JMeter, Gatling, k6)
- Implement **performance testing** in CI/CD pipeline
- Test with **realistic data volumes**
- Monitor **response times**, **throughput**, and **error rates**
- Use **APM tools** during load testing
- Perform **stress testing** and **spike testing**

---

### 💼 Behavioral & Leadership

#### Q21. Conflict Among Team Members

**Q:** *Describe a situation where you had conflict among team members. How did you handle it?* [youtube](https://www.youtube.com/watch?v=pZlV624Fjzk)

**Expected Answer:**
- **Facilitate open communication** between parties
- **Focus on the problem**, not the person
- **Document decisions** and **establish clear expectations**
- **Escalate to management** if unresolved
- **Follow up** to ensure resolution

#### Q22. Stakeholder Requesting Architecture Change

**Q:** *A key stakeholder requests a major architecture change close to deployment. How do you handle it?* [youtube](https://www.youtube.com/watch?v=pZlV624Fjzk)

**Expected Answer:**
- **Assess risks** and **impact analysis**
- **Communicate trade-offs** clearly to stakeholder
- **Propose phased approach** if change is critical
- **Get sign-off** on timeline delay
- **Update documentation** and **inform all stakeholders**

#### Q23. Production Incident Response

**Q:** *You discover a critical security vulnerability in production. What do you do?* [youtube](https://www.youtube.com/watch?v=pZlV624Fjzk)

**Expected Answer:**
- **Assess severity** and **scope of impact**
- **Notify stakeholders** immediately
- **Implement hotfix** with proper testing
- **Enable monitoring** and **alerting**
- **Post-mortem analysis** to prevent recurrence
- **Update security policies** and **training**

---

## Part II: Core Java Scenarios

### 🔥 Core Java Fundamentals

#### Q24. Memory Leak Scenario (HashMap)

**Q:** *Your Java application is running out of memory gradually over days. You find heap dumps showing many HashMap instances. What could be the cause and how do you fix it?*

**Expected Answer:**
- **Cause**: Static HashMap acting as cache without eviction policy (unbounded growth)
- **Cause**: Uncleared collections (ArrayList, HashMap) holding object references
- **Cause**: Unclosed resources (InputStream, Connection, Statement)
- **Fix**: Use `WeakHashMap` or `LinkedHashMap` with `removeEldestEntry()` for cache
- **Fix**: Implement proper cleanup in `finally` blocks or use try-with-resources
- **Fix**: Use profiling tools (JVisualVM, MAT) to identify leak sources [blog.techwasti](https://blog.techwasti.com/java-architect-scenario-based-interview-questions-and-answers)

#### Q25. ConcurrentModificationException Scenario

**Q:** *You're iterating over an ArrayList and removing elements inside the loop. You get `ConcurrentModificationException`. How do you fix this?*

**Expected Answer:**

```java
// ❌ Wrong - throws ConcurrentModificationException
for (String item : list) {
    if (condition) {
        list.remove(item);
    }
}

// ✅ Fix 1: Use Iterator
Iterator<String> iterator = list.iterator();
while (iterator.hasNext()) {
    if (condition) {
        iterator.remove();
    }
}

// ✅ Fix 2: Use removeIf (Java 8+)
list.removeIf(item -> condition);

// ✅ Fix 3: Use copyOnWriteArrayList for concurrent scenarios
CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
```

#### Q26. NullPointerException in Java 8 Streams

**Q:** *You're using Java 8 Streams and get a `NullPointerException`. How do you prevent it?*

**Expected Answer:**

```java
// ❌ Problem: stream() on null list
List<String> list = null;
list.stream().filter(s -> s.length() > 5).forEach(System.out::println);

// ✅ Fix 1: Optional check
Optional.ofNullable(list).orElse(Collections.emptyList())
    .stream()
    .filter(Objects::nonNull)
    .filter(s -> s.length() > 5)
    .forEach(System.out::println);

// ✅ Fix 2: Traditional null check
if (list != null) {
    list.stream().filter(s -> s != null && s.length() > 5)
        .forEach(System.out::println);
}
```

#### Q27. Integer Caching Surprise

**Q:** *Why does `Integer a = 127; Integer b = 127; System.out.println(a == b);` print `true`, but `Integer c = 128; Integer d = 128; System.out.println(c == d);` print `false`?*

**Expected Answer:**
- Java caches **Integer objects from -128 to 127** (Integer cache)
- Autoboxing reuses cached objects for values in this range
- For values outside range, new objects are created
- **Fix**: Always use `.equals()` for Integer comparison, not `==`
- **Lesson**: Never use `==` for object comparison, only for primitives [youtube](https://www.youtube.com/watch?v=I0WeVvKyf28)

#### Q28. HashMap in Multithreaded Environment

**Q:** *You're using HashMap in a multithreaded application and experiencing data corruption. What's the problem and solution?*

**Expected Answer:**
- **Problem**: HashMap is **not thread-safe** – concurrent modifications can cause infinite loops (Java 7) or data loss
- **Solution 1**: Use `ConcurrentHashMap` (best for most cases)
- **Solution 2**: Use `Collections.synchronizedMap(new HashMap<>())`
- **Solution 3**: Use `Hashtable` (legacy, slower)
- **Why ConcurrentHashMap?**: Uses **segment locking** (Java 7) or **CAS + synchronized** (Java 8+) for better performance [blog.techwasti](https://blog.techwasti.com/java-architect-scenario-based-interview-questions-and-answers)

#### Q29. String Memory Leak

**Q:** *You're parsing large text files and storing substrings. Memory usage keeps growing. What's wrong?*

**Expected Answer:**
- **Problem** (Java 6 and earlier): `substring()` shares the same char[] array, preventing garbage collection of entire string
- **Problem** (all versions): Keeping references to large strings unnecessarily
- **Fix** (Java 7+): `substring()` creates new char[], but still use carefully
- **Fix**: Use `substring().intern()` for common strings or explicitly create new String
- **Fix**: Use `StringBuilder` for string manipulation instead of repeated concatenation [youtube](https://www.youtube.com/watch?v=I0WeVvKyf28)

---

### ⚙️ Advanced Java

#### Q30. CompletableFuture Performance Issue

**Q:** *You're using `CompletableFuture.supplyAsync()` for parallel processing but performance is worse than sequential. What's wrong?*

**Expected Answer:**
- **Problem**: Default `CompletableFuture` uses `ForkJoinPool.commonPool()` (only 3 threads by default)
- **Problem**: If tasks are I/O-bound, thread pool is underutilized
- **Fix**: Create custom `ExecutorService` with appropriate thread pool size

```java
ExecutorService executor = Executors.newFixedThreadPool(50); // For I/O-bound
CompletableFuture.supplyAsync(() -> fetchData(), executor);
```

- **Fix**: Use `ThreadPoolExecutor` with custom configuration
- **Rule**: CPU-bound = `CPU cores`, I/O-bound = `CPU cores * 2` or more [blog.techwasti](https://blog.techwasti.com/java-architect-scenario-based-interview-questions-and-answers)

#### Q31. ThreadLocal Memory Leak

**Q:** *You're using ThreadLocal in a web application and seeing memory leaks after deployments. Why?*

**Expected Answer:**
- **Problem**: ThreadLocal stores values in Thread's internal map
- **Problem**: In web containers (Tomcat), threads are reused and ThreadLocal values persist
- **Fix**: Always call `threadLocal.remove()` in `finally` block

```java
threadLocal.set(value);
try {
    // use value
} finally {
    threadLocal.remove(); // Critical!
}
```

- **Fix**: Use WeakReference for ThreadLocal keys if needed
- **Lesson**: ThreadLocal requires manual cleanup in long-lived thread pools [blog.techwasti](https://blog.techwasti.com/java-architect-scenario-based-interview-questions-and-answers)

#### Q32. HashMap Key Being Modified

**Q:** *You're using a custom object as HashMap key, and after modifying a field used in `equals()`/`hashCode()`, you can't retrieve the value. Why?*

**Expected Answer:**
- **Problem**: HashMap uses `hashCode()` to find bucket. If key object is modified, `hashCode()` changes
- **Problem**: HashMap looks in wrong bucket, returns `null`
- **Fix**: Use **immutable objects** as HashMap keys (all fields final)
- **Fix**: Never modify fields used in `hashCode()` after adding to HashMap
- **Best Practice**: Make key classes `final` with `private final` fields [youtube](https://www.youtube.com/watch?v=I0WeVvKyf28)

#### Q33. Garbage Collection Pause Time

**Q:** *Your application has 2-second GC pauses affecting user experience. How do you reduce them?*

**Expected Answer:**
- **Diagnosis**: Enable GC logging (`-Xlog:gc*`) to analyze pause times
- **Solution 1**: Use **G1GC** (`-XX:+UseG1GC`) for predictable pauses
- **Solution 2**: Use **ZGC** (`-XX:+UseZGC`) for sub-millisecond pauses (Java 11+)
- **Solution 3**: Tune heap size (`-Xms`, `-Xmx`) to reduce GC frequency
- **Solution 4**: Reduce object allocation rate (object pooling, StringBuilder)
- **Solution 5**: Increase young generation (`-XX:NewRatio`) for G1GC [blog.techwasti](https://blog.techwasti.com/java-architect-scenario-based-interview-questions-and-answers)

#### Q34. Deadlock Detection

**Q:** *Your application suddenly stops responding. You suspect a deadlock. How do you detect and fix it?*

**Expected Answer:**
- **Detection**:
  - Use `jstack <pid>` to get thread dump
  - Look for "deadlock detected" in output
  - Use JVisualVM or JConsole
- **Prevention**:
  - **Lock ordering**: Always acquire locks in same order
  - **Timeout**: Use `tryLock(timeout)` instead of `lock()`
  - **Reduce lock scope**: Minimize synchronized blocks
  - **Use ConcurrentHashMap**: Instead of synchronizing entire collections

```java
// ✅ Fix: Use tryLock with timeout
if (lock1.tryLock(1, TimeUnit.SECONDS)) {
    try {
        if (lock2.tryLock(1, TimeUnit.SECONDS)) {
            // do work
        }
    } finally {
        lock1.unlock();
    }
}
```

#### Q35. Java Stream Parallel Performance

**Q:** *You're using `stream().parallel()` but it's slower than sequential. Why?*

**Expected Answer:**
- **Problem**: Parallel stream uses ForkJoinPool (limited threads)
- **Problem**: Overhead of splitting and combining results exceeds benefits for small datasets
- **Problem**: If operation is already I/O-bound, parallelization adds no value
- **Fix**: Use parallel stream only for **CPU-bound operations** on **large datasets** (>10,000 elements)
- **Fix**: Use custom `ExecutorService` for I/O-bound parallel operations
- **Rule**: Sequential is faster for small datasets due to parallelization overhead [blog.techwasti](https://blog.techwasti.com/java-architect-scenario-based-interview-questions-and-answers)

---

### 🏛️ Design Patterns, Spring Deep-Dive & JVM

#### Q36. Singleton in Multithreaded Environment

**Q:** *You implemented a singleton pattern but during testing, you find multiple instances are created. What's wrong?*

**Expected Answer:**

```java
// ❌ Wrong - not thread-safe
public static Singleton getInstance() {
    if (instance == null) {
        instance = new Singleton();
    }
    return instance;
}

// ✅ Fix 1: Synchronized method (slow)
public static synchronized Singleton getInstance() {
    if (instance == null) {
        instance = new Singleton();
    }
    return instance;
}

// ✅ Fix 2: Double-checked locking (best)
public static Singleton getInstance() {
    if (instance == null) {
        synchronized (Singleton.class) {
            if (instance == null) {
                instance = new Singleton();
            }
        }
    }
    return instance;
}

// ✅ Fix 3: Bill Pugh Singleton (best practice)
private static class SingletonHelper {
    private static final Singleton INSTANCE = new Singleton();
}
public static Singleton getInstance() {
    return SingletonHelper.INSTANCE;
}

// ✅ Fix 4: Enum Singleton (most secure)
public enum Singleton {
    INSTANCE;
}
```

#### Q37. Exception Handling in Transaction

**Q:** *You have `@Transactional` method that catches Exception and doesn't rethrow. Transaction doesn't rollback. Why?*

> Related: See also **Q6** (@Transactional Not Working)

**Expected Answer:**
- **Problem**: Spring only rolls back on **unchecked exceptions** (`RuntimeException`, `Error`) by default
- **Problem**: If you catch `Exception` and don't rethrow, transaction interceptor doesn't know to rollback
- **Fix 1**: Don't catch exception, let it propagate
- **Fix 2**: Manually trigger rollback:

```java
@Transactional
public void method() {
    try {
        // business logic
    } catch (Exception e) {
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        throw e; // Must rethrow
    }
}
```

- **Fix 3**: Use `@Transactional(rollbackFor = Exception.class)` [stackoverflow](https://stackoverflow.com/questions/1099025/spring-transactional-what-happens-in-background)

#### Q38. Circular Dependency in Constructor Injection

**Q:** *You're using constructor injection and get `BeanCurrentlyInCreationException` due to circular dependency. How do you fix it?*

> Related: See also **Q7** (Circular Dependency in Spring)

**Expected Answer:**
- **Problem**: Service A requires Service B in constructor, Service B requires Service A
- **Fix 1**: Use `@Lazy` on one constructor parameter

```java
public ServiceA(@Lazy ServiceB serviceB) { ... }
```

- **Fix 2**: Refactor to extract common logic to Service C
- **Fix 3**: Use setter injection for one dependency (not recommended)
- **Fix 4**: Use event-driven architecture (publish/subscribe)
- **Best**: Redesign architecture to eliminate circular dependency [stackoverflow](https://stackoverflow.com/questions/1099025/spring-transactional-what-happens-in-background)

#### Q39. Java 8 Optional Best Practices

**Q:** *You're using `Optional` but getting `NullPointerException`. What are common mistakes?*

**Expected Answer:**

```java
// ❌ Wrong: Optional of null
Optional<String> opt = Optional.of(null); // Throws NPE

// ✅ Fix: Use Optional.ofNullable
Optional<String> opt = Optional.ofNullable(null); // Returns Optional.empty()

// ❌ Wrong: Calling get() without isPresent()
String value = opt.get(); // Throws NoSuchElementException

// ✅ Fix: Use orElse or ifPresent
String value = opt.orElse("default");
opt.ifPresent(v -> System.out.println(v));

// ❌ Wrong: Using Optional for fields/parameters
private Optional<String> field; // Don't do this

// ✅ Fix: Only use Optional for return types
public Optional<String> getData() { ... }
```

#### Q40. ClassLoader Issue in Web Application

**Q:** *After deploying a new version, you get `ClassNotFoundException` for a library that was working before. What's wrong?*

**Expected Answer:**
- **Problem**: ClassLoader hierarchy issue in web containers
- **Problem**: Different versions of same library in different classloaders
- **Problem**: Static variables holding references to old classes
- **Fix**: Ensure all dependencies are in `WEB-INF/lib` (not provided by container)
- **Fix**: Restart application server to clear classloader cache
- **Fix**: Use Maven/Gradle dependency management to avoid version conflicts
- **Fix**: Check for duplicate JARs in classpath [blog.techwasti](https://blog.techwasti.com/java-architect-scenario-based-interview-questions-and-answers)

#### Q41. Producer-Consumer Pattern Implementation

**Q:** *You need to implement a producer-consumer pattern for processing 1 million records. How do you do it efficiently?*

**Expected Answer:**

```java
// ✅ Fix 1: Use BlockingQueue (best)
BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(1000);
ExecutorService producer = Executors.newFixedThreadPool(4);
ExecutorService consumer = Executors.newFixedThreadPool(8);

// Producer
producer.submit(() -> {
    for (int i = 0; i < 1000000; i++) {
        queue.put(i); // Blocks if queue is full
    }
});

// Consumer
consumer.submit(() -> {
    while (!done) {
        Integer item = queue.take(); // Blocks if queue is empty
        process(item);
    }
});

// ✅ Fix 2: Use CompletableFuture for parallel processing
CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
```

---

## Part III: Senior / Lead / Architect Level Scenarios

**Java Full Stack · Architect Engineer · Scenario-Based Questions · Senior / Lead Level**

20 questions · 5 topic areas · Q42–Q61

| # | Q | Topic | Difficulty | Question |
|---|---|-------|------------|----------|
| 01 | Q42 | Core Java | HARD | Memory leak in a long-running Java service |
| 02 | Q43 | Architecture | SENIOR | Rate-limiting system for an API gateway (50K RPS) |
| 03 | Q44 | Core Java | HARD | Java memory model & concurrent code visibility bug |
| 04 | Q45 | Microservices | SENIOR | Saga pattern for distributed order transaction |
| 05 | Q46 | Performance | HARD | Spring Boot endpoint optimization (800ms → 100ms) |
| 06 | Q47 | Architecture | SENIOR | Monolith to microservices migration without downtime |
| 07 | Q48 | DB / JPA | HARD | JPA queries causing database deadlocks |
| 08 | Q49 | Microservices | SENIOR | Circuit breaker with fallback behavior |
| 09 | Q50 | Core Java | HARD | CompletableFuture pipeline with error handling |
| 10 | Q51 | Architecture | SENIOR | Event-driven notification system (10M users) |
| 11 | Q52 | DB / JPA | HARD | Multi-tenant data isolation in SaaS |
| 12 | Q53 | Core Java | HARD | G1GC vs ZGC — when to choose which |
| 13 | Q54 | Microservices | HARD | Distributed tracing across 8 microservices |
| 14 | Q55 | Architecture | SENIOR | Idempotent REST API for payment processing |
| 15 | Q56 | Performance | HARD | High CPU usage during idle periods |
| 16 | Q57 | Microservices | SENIOR | CQRS + Event Sourcing for financial ledger |
| 17 | Q58 | Core Java | HARD | Custom ClassLoader for plugin isolation |
| 18 | Q59 | DB / JPA | SENIOR | Database sharding strategy for 10 billion rows |
| 19 | Q60 | Performance | SENIOR | Reactive programming with Spring WebFlux |
| 20 | Q61 | Architecture | SENIOR | Blue-green and canary deployments in Kubernetes |

---

### ☕ Core Java

#### Q42. Memory Leak in a Long-Running Java Service

**Difficulty:** HARD · **Topic:** Core Java

**Q:** *Memory leak in a long-running Java service — how do you diagnose and fix it?*

Your production microservice starts with 512MB heap. After 3 days of running, it triggers OOM and restarts. GC logs show full GC happening every 5 minutes and reclaiming less each time.

**Expected Answer:**

**Step 1 — Confirm & Capture**
- Enable JVM flags: `-XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/tmp/`
- This captures a heap dump at crash time
- Also add `-Xlog:gc*:file=/tmp/gc.log` for GC analysis

**Step 2 — Analyze with MAT / VisualVM**
- Open heap dump in Eclipse MAT → run "Leak Suspects Report"
- Check "Dominator Tree" — find objects holding most memory
- Common culprits: unbounded caches (HashMap growth), ThreadLocal not removed, static collections, unclosed streams/connections
- Check for ClassLoader leaks if using dynamic class loading

**Common Scenario — ThreadLocal Leak**

```java
// WRONG — ThreadLocal never cleaned in thread pools
static ThreadLocal<UserContext> ctx = new ThreadLocal<>();

// FIX — always remove in finally
try {
    ctx.set(new UserContext(user));
    processRequest();
} finally {
    ctx.remove(); // critical in pooled threads
}
```

**Architectural Fix**
- Use weak references for caches: `WeakHashMap` or Caffeine with size limits
- Set explicit eviction on all caches (`@Cacheable` with evict policy)
- Use try-with-resources for all `Closeable` resources
- Profile with async-profiler in production (low overhead)

**Follow-up:** How would you distinguish a memory leak from just insufficient heap sizing? What metrics would you monitor in Prometheus/Grafana?

---

#### Q44. Java Memory Model & Concurrent Code

**Difficulty:** HARD · **Topic:** Core Java

**Q:** *Explain how the Java memory model affects concurrent code — give a real bug example.*

A senior dev reviews your code and says "this has a visibility bug." Your singleton initialization looks correct to you. Walk through what the JMM guarantees and where the bug is.

**Expected Answer:**

**The Bug — Double-Checked Locking (Pre-Java 5)**

```java
// BROKEN — CPU can reorder: allocate memory → assign ref → initialize
private static Singleton instance;
public static Singleton getInstance() {
    if (instance == null) {        // Thread B sees non-null but uninitialized!
        synchronized(Singleton.class) {
            if (instance == null)
                instance = new Singleton();
        }
    }
    return instance;
}

// FIX — volatile creates happens-before fence
private static volatile Singleton instance;
```

**JMM Key Guarantees**
- `volatile` write happens-before `volatile` read of same variable
- `synchronized` block exit happens-before entry by another thread
- `Thread.start()` happens-before any code in that thread
- Without these: CPUs and JIT may reorder instructions for performance

**Better Pattern — Initialization-on-Demand Holder**

```java
public class Singleton {
    private static class Holder {
        static final Singleton INSTANCE = new Singleton();
    }
    public static Singleton get() { return Holder.INSTANCE; }
    // JVM class loading guarantees thread safety — no locks needed
}
```

**Follow-up:** What is a "spurious wakeup" and how does it affect code using `Object.wait()`? Show the correct pattern.

---

#### Q50. CompletableFuture Pipeline with Error Handling

**Difficulty:** HARD · **Topic:** Core Java

**Q:** *CompletableFuture pipeline with error handling — design a real async workflow.*

You need to: (1) fetch user from DB, (2) in parallel fetch their orders and preferences, (3) combine them to build a personalized feed, (4) if any step fails, return a degraded response, never throw to the caller.

**Expected Answer:**

**Full Async Pipeline**

```java
public CompletableFuture<Feed> buildFeed(long userId) {
    CompletableFuture<User> userF =
        CompletableFuture.supplyAsync(() -> userRepo.find(userId), executor)
            .exceptionally(e -> User.anonymous()); // degrade gracefully

    CompletableFuture<List<Order>> ordersF =
        userF.thenComposeAsync(u -> orderService.getOrders(u.getId()), executor)
             .exceptionally(e -> Collections.emptyList());

    CompletableFuture<Prefs> prefsF =
        userF.thenComposeAsync(u -> prefService.getPrefs(u.getId()), executor)
             .exceptionally(e -> Prefs.defaults());

    return userF.thenCombineAsync(
        ordersF.thenCombineAsync(prefsF, OrdersAndPrefs::new, executor),
        (user, op) -> feedBuilder.build(user, op.orders(), op.prefs()),
        executor
    ).orTimeout(2, TimeUnit.SECONDS)
     .exceptionally(e -> Feed.empty());
}
```

**Key Methods to Know**
- `thenApply` — sync transform of result (same thread)
- `thenCompose` — flatMap: returns a new CompletableFuture
- `thenCombine` — merge two independent futures
- `exceptionally` — handle errors with fallback value
- `orTimeout` — fail with TimeoutException after N time units
- `allOf` — wait for N futures; use with `thenApply` to collect results

**Follow-up:** What happens to your CompletableFuture if the thread in the executor is interrupted? How do you propagate cancellation properly?

---

#### Q53. G1GC vs ZGC — When to Choose Which

**Difficulty:** HARD · **Topic:** Core Java

**Q:** *Explain how Java garbage collectors differ — when do you choose G1 vs ZGC?*

You have two services: a batch processor that runs 30-minute jobs on 32GB heap, and a real-time API with strict P99 < 10ms SLA. Your DevOps team asks which GC to use for each and why.

**Expected Answer:**

**GC Comparison**
- **G1GC** (default since Java 9): regional heap, predictable pauses ~200ms, good for heaps 4GB–32GB, tunable with `-XX:MaxGCPauseMillis=200`
- **ZGC** (Java 15+ production-ready): concurrent GC, pauses < 1ms even on 1TB heap, slight throughput cost, ideal for latency-sensitive services
- **Shenandoah**: similar to ZGC, lower overhead, good for medium latency requirements
- **Serial/Parallel**: high throughput, high pauses — avoid for latency-critical work

**For Batch Processor (32GB, throughput matters)**

```bash
# G1GC — maximize throughput, pauses acceptable
-XX:+UseG1GC
-Xms32g -Xmx32g
-XX:MaxGCPauseMillis=500
-XX:G1HeapRegionSize=32m
-XX:+G1UseAdaptiveIHOP
```

**For Real-Time API (P99 < 10ms)**

```bash
# ZGC — ultra-low pause regardless of heap size
-XX:+UseZGC
-Xms8g -Xmx8g
-XX:+ZGenerational    # Java 21+ generational ZGC (faster)
-XX:ConcGCThreads=4
```

**Key Tuning Tips**
- Always set `-Xms = -Xmx` to prevent heap resizing pauses
- Monitor GC with `-Xlog:gc*:file=gc.log:time,uptime`
- ZGC's weakness: higher memory overhead (~10–20% more than G1)
- Java 21 Virtual Threads reduce heap pressure significantly for I/O-heavy services

**Follow-up:** What are virtual threads in Java 21 and how do they change the GC pressure compared to platform threads in a high-concurrency service?

---

#### Q58. Custom ClassLoader for Plugin Isolation

**Difficulty:** HARD · **Topic:** Core Java

**Q:** *Walk through a custom ClassLoader implementation and when you would need one.*

You are building a plugin system where customers can upload Java code that your application loads and executes at runtime without restarting. Each plugin must be isolated — a bug in Plugin A must not crash Plugin B or the host application.

**Expected Answer:**

**Custom ClassLoader for Plugin Isolation**

```java
public class PluginClassLoader extends URLClassLoader {
    private final ClassLoader parent;

    public PluginClassLoader(URL[] jarUrls, ClassLoader parent) {
        super(jarUrls, parent);
        this.parent = parent;
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve)
            throws ClassNotFoundException {
        // Reverse delegation: try plugin JAR first (not parent)
        try {
            return findClass(name); // check our JAR first
        } catch (ClassNotFoundException e) {
            return parent.loadClass(name); // fall back to host
        }
    }
}

// Load and execute plugin
PluginClassLoader loader = new PluginClassLoader(
    new URL[]{ pluginJar.toURI().toURL() }, Thread.currentThread().getContextClassLoader()
);
Class<?> pluginClass = loader.loadClass("com.customer.Plugin");
Plugin plugin = (Plugin) pluginClass.getDeclaredConstructor().newInstance();
```

**Unloading & Memory Management**
- To unload a plugin: remove all strong references to the ClassLoader and its classes
- GC will collect the ClassLoader (and all its loaded classes) when no references remain
- `WeakReference` allows GC to collect it when plugin is unregistered
- Watch for: static fields in plugin classes holding references back to ClassLoader — prevents GC

**Security Sandbox**
- Run plugin in separate Thread with custom SecurityManager (deprecated Java 17+)
- Modern approach: GraalVM Polyglot sandbox or OSGi (Apache Felix)
- Time-limit plugin execution with `Future.get(timeout)`

**Follow-up:** What is the ClassLoader hierarchy in a typical Spring Boot fat JAR application? How does it differ from a standard Java application?

---

### 🏗️ Architecture

#### Q43. Rate-Limiting System for an API Gateway

**Difficulty:** SENIOR · **Topic:** Architecture

**Q:** *Design a rate-limiting system for an API gateway serving 50,000 RPS.*

You are the architect for a fintech API gateway. One endpoint is being hammered by a rogue client causing 503s for others. You need per-client rate limiting that survives node restarts and works across 10 gateway instances.

**Expected Answer:**

**Algorithm Choice**
- Use **Token Bucket** (allows bursting) or **Sliding Window Log**
- For distributed rate limiting across 10 nodes, a local-only counter will fail — you need a shared counter in Redis

**Redis Lua Script — Atomic Sliding Window**

```java
// Spring Boot + Lettuce + Lua for atomic rate check
String luaScript = """
    local key = KEYS[1]
    local now = tonumber(ARGV[1])
    local window = tonumber(ARGV[2])
    local limit = tonumber(ARGV[3])
    redis.call('ZREMRANGEBYSCORE', key, 0, now - window)
    local count = redis.call('ZCARD', key)
    if count < limit then
        redis.call('ZADD', key, now, now)
        redis.call('EXPIRE', key, window)
        return 1
    end
    return 0
""";

// Key: "ratelimit:{clientId}:{endpoint}"
// Window: 1000ms, Limit: 100 req/sec
```

**Architecture Layers**
- **L1** — Local cache (Caffeine, 100ms TTL) for hot clients to reduce Redis round-trips by ~80%
- **L2** — Redis Cluster with Lua scripts for distributed atomic counting
- Reject with HTTP 429, `Retry-After` header, and `X-RateLimit-Remaining`
- Graceful degradation: if Redis is down, fail-open with local limiting only

**Spring Cloud Gateway Filter**

```java
@Component
public class RateLimitFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange ex, GatewayFilterChain chain) {
        String clientId = ex.getRequest().getHeaders().getFirst("X-Client-ID");
        return rateLimiter.isAllowed(clientId)
            .flatMap(allowed -> allowed
                ? chain.filter(ex)
                : tooManyRequests(ex));
    }
}
```

**Follow-up:** How would you handle rate limiting for authenticated vs anonymous users differently? What about IP-based limiting at the CDN layer?

---

#### Q47. Monolith to Microservices Migration Without Downtime

**Difficulty:** SENIOR · **Topic:** Architecture

**Q:** *How would you migrate a monolith to microservices without downtime?*

You have a 5-year-old Spring MVC monolith with 200 tables, 1M active users, and zero tests. The business needs 3 new features that require independent scaling. You cannot afford 6 months of big-bang rewrite.

**Expected Answer:**

**Strangler Fig Pattern**
Incrementally replace monolith functionality by routing traffic through a facade. New requests go to new services; old requests still hit the monolith. The monolith "strangles" over time.

**Phase 1 — Add an API Gateway (Week 1–2)**
- Put Kong / Spring Cloud Gateway in front of monolith
- 100% traffic still routes to monolith initially
- Gain: observability, rate limiting, auth centralization

**Phase 2 — Extract Bounded Contexts**
- Identify high-value, loosely-coupled domains first (e.g., Notifications)
- Use the **Anti-Corruption Layer**: new service talks to monolith DB via a read API, not direct DB access
- Dual-write period: write to both monolith DB and new service DB, read from monolith

**Phase 3 — Data Migration (Hardest Part)**
- Shadow reads: compare results from old and new service silently
- Feature flag to route 1% → 10% → 50% → 100% to new service
- Use **Change Data Capture** (Debezium) to sync monolith DB changes to new service DB during transition
- Run both in parallel until confidence is high, then remove monolith code path

**Follow-up:** How would you handle shared database tables that are used by 3 different bounded contexts during migration? What is database-per-service and when is it practical?

---

#### Q51. Event-Driven Notification System at Scale

**Difficulty:** SENIOR · **Topic:** Architecture

**Q:** *Design an event-driven notification system for 10 million users with delivery guarantees.*

Users can subscribe to events (new follower, like, comment). Events are generated at 50,000/second peak. Notifications must be delivered within 5 seconds, must not duplicate, and must survive system restarts. You have Kafka available.

**Expected Answer:**

**Architecture Overview**
- **Producers**: Order/Social/Payment services → publish to Kafka topics
- **Notification Router**: consumes events, routes to correct delivery channel
- **Delivery Workers**: push (FCM/APNs), email (SES), SMS (Twilio) — fan-out
- **State Store**: Redis for deduplication, Postgres for delivery receipts

**Exactly-Once Delivery**

```java
// Deduplication with Redis SETNX (TTL 24h)
String dedupKey = "notif:" + eventId + ":" + userId;
Boolean isNew = redis.opsForValue()
    .setIfAbsent(dedupKey, "1", Duration.ofHours(24));

if (Boolean.TRUE.equals(isNew)) {
    deliverNotification(userId, notification);
}
// Kafka consumer: manual commit AFTER successful delivery
ack.acknowledge(); // only after push/email confirmed
```

**Scaling Fan-Out for Celebrities**
- **Problem**: user with 5M followers generates 5M notification records on one event
- **Solution**: lazy fan-out — store event once, expand to followers in background
- Priority queues: premium users get fast-path processing
- Backpressure: if FCM rejects, exponential backoff with dead-letter queue

**Follow-up:** How would you handle notification preferences (user wants email but not push for "likes")? Where would you store and apply these preferences in the pipeline?

---

#### Q55. Idempotent REST API for Payment Processing

**Difficulty:** SENIOR · **Topic:** Architecture

**Q:** *How do you design an idempotent REST API for payment processing?*

A mobile client POSTs `/payments` with $100 charge. Due to a network blip, the client never receives the 200 response. It retries — but your server already processed the payment. How do you prevent double charging?

**Expected Answer:**

**Idempotency Key Pattern**

```http
POST /payments
Idempotency-Key: 550e8400-e29b-41d4-a716-446655440000
Content-Type: application/json
{ "amount": 100, "currency": "USD" }
```

```java
@PostMapping("/payments")
public ResponseEntity<Payment> createPayment(
        @RequestHeader("Idempotency-Key") String key,
        @RequestBody PaymentRequest req) {

    // Check if we already processed this key
    return idempotencyStore.get(key)
        .map(existing -> ResponseEntity.ok(existing)) // replay stored response
        .orElseGet(() -> {
            Payment payment = paymentService.charge(req);
            idempotencyStore.save(key, payment, Duration.ofDays(1));
            return ResponseEntity.status(201).body(payment);
        });
}
```

**Storage Considerations**
- Store idempotency key + full response in Redis (24h TTL)
- Must be atomic: use Redis SET NX (set if not exists) or DB unique constraint on key
- Race condition: two simultaneous requests with same key — use distributed lock on key during first processing
- Include request hash in stored record — reject if same key with different body

**HTTP Status for Replays**
- Return same 200/201 with original response body
- Add `Idempotent-Replayed: true` header so client can distinguish
- Never return 4xx for a valid replay — client should not retry differently

**Follow-up:** How would you handle idempotency for a payment that takes 30 seconds to process? What response do you give if the same key arrives while the first request is still in flight?

---

#### Q61. Blue-Green and Canary Deployments in Kubernetes

**Difficulty:** SENIOR · **Topic:** Architecture

**Q:** *How do you implement blue-green and canary deployments in a Java microservice fleet?*

You have 20 microservices, each deployed in Kubernetes. A critical service has a bug that only appears under 5% of traffic patterns. You need to deploy a fix with zero downtime and the ability to rollback in under 60 seconds.

**Expected Answer:**

**Blue-Green Deployment**
- Run two identical environments: blue (current) and green (new version)
- Route 100% traffic to blue, deploy to green, run smoke tests
- Switch: update Kubernetes Service selector from blue to green instantly
- Rollback: switch selector back to blue — takes < 5 seconds

**Canary with Argo Rollouts**

```yaml
# Argo Rollouts canary strategy
spec:
  strategy:
    canary:
      steps:
      - setWeight: 5      # 5% to canary
      - pause: {duration: 10m}
      - setWeight: 20
      - pause: {duration: 10m}
      - setWeight: 50
      - pause: {}         # manual promotion
      analysis:
        templates:
        - templateName: error-rate
        args:
        - name: service-name
          value: order-service
```

**Automated Rollback with Analysis**

```yaml
# AnalysisTemplate: rollback if error rate > 5%
spec:
  metrics:
  - name: error-rate
    interval: 1m
    failureLimit: 1
    provider:
      prometheus:
        address: http://prometheus:9090
        query: |
          rate(http_requests_total{status=~"5..",
            service="{{args.service-name}}"}[5m])
          /
          rate(http_requests_total{service="{{args.service-name}}"}[5m])
    successCondition: result[0] < 0.05
```

**Database Schema Compatibility**
- Never deploy DB migration that breaks old version — both versions must work during transition
- Expand/contract pattern: add new column as nullable, deploy, backfill, make required, drop old column
- Flyway/Liquibase: run migrations before code deployment with backward-compatible changes only

**Follow-up:** How do you handle stateful services (with local cache or session state) in a blue-green deployment? What special considerations apply compared to stateless services?

---

### ⚡ Performance

#### Q46. Spring Boot Endpoint Optimization (800ms → 100ms)

**Difficulty:** HARD · **Topic:** Performance

**Q:** *A Spring Boot endpoint takes 800ms — your target is 100ms. Walk through your optimization process.*

`GET /api/v1/dashboard` returns aggregated data for a user. Currently it fetches from 5 different tables sequentially. P99 latency is 800ms under 500 concurrent users. You need 100ms P99.

**Expected Answer:**

**1. Profile First (Never Guess)**
- Add Spring Boot Actuator + Micrometer
- Use async-profiler in prod or YourKit in staging
- Identify: is it CPU-bound, I/O-bound, or lock-contention?

**2. Fix N+1 and Sequential Queries**

```java
// BEFORE — 5 sequential DB calls = 5 * 150ms = 750ms
User user = userRepo.findById(id);
List<Order> orders = orderRepo.findByUser(id);
Stats stats = statsRepo.findByUser(id);

// AFTER — CompletableFuture parallel execution
CompletableFuture<User> userF = CompletableFuture.supplyAsync(() -> userRepo.findById(id));
CompletableFuture<List<Order>> ordersF = CompletableFuture.supplyAsync(() -> orderRepo.findByUser(id));
CompletableFuture<Stats> statsF = CompletableFuture.supplyAsync(() -> statsRepo.findByUser(id));
CompletableFuture.allOf(userF, ordersF, statsF).join();
// Now runs in max(150ms) = 150ms not 450ms
```

**3. Add Caching Layer**
- `@Cacheable` on read-heavy aggregations with 30s TTL
- Redis for distributed cache — avoid thundering herd with probabilistic early refresh
- Cache-aside pattern for user-specific data

**4. DB Optimizations**
- Add composite indexes on frequent WHERE + ORDER BY columns
- Use database views or materialized views for dashboard aggregates
- Connection pool sizing: HikariCP `minimum-idle = 10`, `maximum-pool-size = 50`
- Use `@Query` with JOIN FETCH to avoid N+1 in JPA

**Follow-up:** How do you handle cache invalidation when underlying data changes across multiple services? Describe an event-driven cache invalidation approach.

---

#### Q56. High CPU Usage During Idle Periods

**Difficulty:** HARD · **Topic:** Performance

**Q:** *Your application has high CPU usage during seemingly idle periods — diagnose it.*

Production pods show 80% CPU at 2 AM with zero user traffic. No scheduled jobs are supposed to run. GC logs look normal. How do you find the root cause?

**Expected Answer:**

**Step 1 — Capture Thread Dump + CPU Profile**

```bash
# Get PID of Java process
jps -l

# Thread dump — shows what every thread is doing
jstack <pid> > thread-dump.txt

# CPU profiling with async-profiler (low overhead, safe for prod)
./profiler.sh -d 30 -f /tmp/profile.html <pid>
# Open profile.html — flame graph shows hot methods
```

**Common Culprits at 2 AM**
- Scheduled tasks (`@Scheduled`) with misconfigured cron — `"0 * * * * *"` fires every minute not every hour
- Connection pool validation queries — HikariCP `testQuery` running on idle connections
- Prometheus/Actuator scraping triggering expensive metrics computation
- Log rotation or compression by logback consuming CPU
- Background compiler (JIT) still optimizing after warm-up — check `PrintCompilation`
- Infinite loop in background thread with no sleep/wait

**Finding Spinning Threads**

```bash
# Find threads consuming most CPU (Linux)
top -H -p <java_pid>
# Note TID (thread ID), convert to hex:
printf '%x\n' <TID>
# Find in thread dump — "nid=0x<hex>" is your culprit thread
grep -A 20 "nid=0x<hex>" thread-dump.txt
```

**Follow-up:** How would you use virtual threads (Java 21) to prevent thread starvation in a mixed I/O and CPU workload? What are the limitations of virtual threads?

---

#### Q60. Reactive Programming with Spring WebFlux

**Difficulty:** SENIOR · **Topic:** Performance

**Q:** *How would you implement reactive programming in a Spring WebFlux service?*

Your REST service must handle 100,000 concurrent connections with minimal threads. Currently your blocking Spring MVC app needs 100,000 threads (100K × 1MB stack = 100GB RAM). Redesign it.

**Expected Answer:**

**Why Reactive Solves This**
- Reactive uses event loops: 8 threads handle 100K connections by never blocking
- When I/O completes, a callback resumes the chain
- No thread sits idle waiting for DB/HTTP response

**Spring WebFlux Reactive Chain**

```java
@RestController
public class OrderController {

    @GetMapping("/orders/{id}")
    public Mono<OrderResponse> getOrder(@PathVariable String id) {
        return orderRepo.findById(id)           // R2DBC — non-blocking DB
            .zipWith(inventoryClient.getStock(id)) // parallel non-blocking HTTP
            .map(tuple -> OrderResponse.from(
                tuple.getT1(), tuple.getT2()
            ))
            .switchIfEmpty(Mono.error(new NotFoundException(id)));
    }
}
```

**Critical Rules**
- **NEVER** block in a reactive chain: no `Thread.sleep()`, no `.get()` on CompletableFuture, no JDBC (use R2DBC)
- If you must call blocking code: wrap with `Mono.fromCallable(...).subscribeOn(Schedulers.boundedElastic())`
- Error handling: `onErrorResume` for fallback, `onErrorMap` to transform exceptions
- Backpressure: `Flux.limitRate(100)` prevents overwhelming slow consumers

**Java 21 Alternative**
- Virtual threads (Project Loom) achieve similar scalability WITH blocking code
- Replace WebFlux with regular Spring MVC + virtual thread executor
- Much simpler mental model — no reactive streams learning curve
- Trade-off: reactive is more memory efficient; virtual threads are more dev-friendly

**Follow-up:** How do you propagate security context (e.g., Spring Security authentication) across reactive chains where there is no ThreadLocal?

---

### 🔗 Microservices

#### Q45. Saga Pattern for Distributed Order Transaction

**Difficulty:** SENIOR · **Topic:** Microservices

**Q:** *How do you implement Saga pattern for a distributed order transaction?*

Your e-commerce system has separate Order, Inventory, Payment, and Shipping services. A customer places an order — payment succeeds but inventory reservation fails. You have 2 minutes of partial state. How do you handle this?

**Expected Answer:**

**Two Saga Approaches**
- **Choreography**: services publish events, others react — good for simple flows, hard to track globally
- **Orchestration**: a Saga Orchestrator drives the steps — better for complex flows, easier to debug

**Orchestration with Axon / Spring State Machine**

```java
@Saga
public class OrderSaga {

    @StartSaga @SagaEventHandler(associationProperty = "orderId")
    public void on(OrderCreatedEvent e) {
        commandGateway.send(new ReserveInventoryCommand(e.orderId, e.items));
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void on(InventoryReservedEvent e) {
        commandGateway.send(new ProcessPaymentCommand(e.orderId, e.amount));
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void on(PaymentFailedEvent e) {
        // Compensating transaction
        commandGateway.send(new ReleaseInventoryCommand(e.orderId));
        commandGateway.send(new CancelOrderCommand(e.orderId));
        SagaLifecycle.end();
    }
}
```

**Key Principles**
- Each step must have a compensating transaction (idempotent)
- Use **outbox pattern** to guarantee event publishing with DB write
- Store saga state durably — it must survive crashes
- Timeouts: if step doesn't complete in X seconds, trigger compensation

**Follow-up:** What is the difference between a Saga and a 2PC (two-phase commit)? When would you actually prefer 2PC despite its drawbacks?

---

#### Q49. Circuit Breaker with Fallback Behavior

**Difficulty:** SENIOR · **Topic:** Microservices

**Q:** *Design a circuit breaker for inter-service communication with fallback behavior.*

Your Order Service calls Payment Service synchronously. Payment Service goes down for 30 seconds. During this time, Order Service creates 10,000 threads waiting for timeouts, runs out of memory, and cascades the outage to 5 other services that depend on Order Service.

**Expected Answer:**

**Resilience4j Circuit Breaker**

```yaml
# application.yml
resilience4j.circuitbreaker:
  instances:
    paymentService:
      slidingWindowSize: 10
      failureRateThreshold: 50       # open if 50% calls fail
      waitDurationInOpenState: 30s   # stay open 30s
      permittedNumberOfCallsInHalfOpenState: 3
      timeoutDuration: 2s            # fail fast, not wait 30s
```

```java
@CircuitBreaker(name = "paymentService", fallbackMethod = "paymentFallback")
@TimeLimiter(name = "paymentService")
public CompletableFuture<PaymentResult> processPayment(Order order) {
    return paymentClient.charge(order);
}

public CompletableFuture<PaymentResult> paymentFallback(Order order, Exception e) {
    // Queue for async processing, respond with "PENDING"
    outbox.save(new PendingPayment(order.getId()));
    return CompletableFuture.completedFuture(PaymentResult.PENDING);
}
```

**Circuit States & Transitions**
- **CLOSED** → normal operation, counting failures
- **OPEN** → fail fast immediately (no calls to downstream), return fallback
- **HALF-OPEN** → let N test requests through; if they succeed → CLOSED, else → OPEN

**Bulkhead Pattern (Prevent Cascade)**
- Separate thread pools per downstream service — PaymentService gets max 20 threads
- Even if payment hangs, order service still has 80 threads for other work
- Combine with Circuit Breaker for full fault isolation

**Follow-up:** What is the difference between a circuit breaker and a retry mechanism? Can using both together cause problems?

---

#### Q54. Distributed Tracing Across Microservices

**Difficulty:** HARD · **Topic:** Microservices

**Q:** *How do you implement distributed tracing across 8 microservices?*

A customer reports a slow checkout. The request touches Auth → Order → Inventory → Payment → Email services. The P99 is 4 seconds but you cannot tell which service is causing it. You have no tracing yet.

**Expected Answer:**

**OpenTelemetry + Jaeger/Zipkin Setup**

```xml
<!-- Spring Boot 3 — Micrometer Tracing (replaces Sleuth) -->
<!-- pom.xml -->
<dependency>
    <groupId>io.micrometer</groupId>
    <artifactId>micrometer-tracing-bridge-otel</artifactId>
</dependency>
<dependency>
    <groupId>io.opentelemetry</groupId>
    <artifactId>opentelemetry-exporter-otlp</artifactId>
</dependency>
```

```yaml
# application.yml — auto-propagates trace ID via HTTP headers
management.tracing:
  sampling.probability: 0.1   # 10% sampling in prod
  exporter.otlp.endpoint: http://otel-collector:4318

# Trace ID automatically propagates via W3C traceparent header
# X-B3-TraceId for legacy Zipkin compatibility
```

**Custom Spans for DB & Business Logic**

```java
@Autowired Tracer tracer;

public Order createOrder(OrderRequest req) {
    Span span = tracer.nextSpan().name("inventory-check").start();
    try (Tracer.SpanInScope ws = tracer.withSpan(span)) {
        span.tag("items.count", String.valueOf(req.items().size()));
        return inventoryService.checkAndReserve(req);
    } finally {
        span.end();
    }
}
```

**What to Tag on Spans**
- `http.method`, `http.url`, `http.status_code`
- `db.statement` (sanitized), `db.operation`, `db.rows_affected`
- `user.id`, `order.id` — business identifiers for correlation
- `error=true` + `exception.message` for error spans

**Follow-up:** How do you trace across asynchronous Kafka message boundaries where there is no HTTP header to propagate trace context?

---

#### Q57. CQRS + Event Sourcing for Financial Ledger

**Difficulty:** SENIOR · **Topic:** Microservices

**Q:** *Design a CQRS + Event Sourcing system for an auditable financial ledger.*

You need a banking ledger where: every balance change must be auditable, you must be able to reconstruct account state at any point in time, read queries (balance, statement) must be fast, and you process 10,000 transactions/second.

**Expected Answer:**

**Core Concept**
- **Event Sourcing**: never store current state, store the sequence of events that led to it
- **CQRS**: separate the write model (commands → events) from read models (projections optimized for queries)

**Write Side — Command & Event Store**

```java
// Every change is an immutable event
sealed interface LedgerEvent {}
record MoneyDebited(UUID accountId, BigDecimal amount,
    String reference, Instant at) implements LedgerEvent {}
record MoneyCredited(UUID accountId, BigDecimal amount,
    String reference, Instant at) implements LedgerEvent {}

// Append-only event store (EventStoreDB or Postgres with sequence)
// NEVER update or delete events — immutable audit trail
```

**Read Side — Projection**

```java
// Rebuild read model by replaying events
public AccountBalance project(List<LedgerEvent> events) {
    return events.stream().reduce(
        AccountBalance.zero(),
        (balance, event) -> switch (event) {
            case MoneyCredited e -> balance.add(e.amount());
            case MoneyDebited e -> balance.subtract(e.amount());
        },
        (a, b) -> a // unused combiner
    );
}
// Snapshot balance every 1000 events for fast rebuild
```

**Architecture**
- Event store: append-only Postgres table (`stream_id`, `version`, `event_type`, `data`, `created_at`)
- Read DB: separate Postgres/Redis with pre-computed balances, updated by event consumer
- Optimistic concurrency on write: reject if `expected_version != actual version` (prevents lost updates)
- Snapshots: serialize account state every N events to speed up replay

**Follow-up:** How do you handle schema evolution in Event Sourcing? If the `MoneyDebited` event gains a new required field 6 months later, how do you deal with old events that do not have it?

---

### 🗄️ DB / JPA

#### Q48. JPA Queries Causing Database Deadlocks

**Difficulty:** HARD · **Topic:** DB / JPA

**Q:** *Your JPA queries are causing database deadlocks under load. Diagnose and fix.*

Under 200 concurrent users, your order processing service throws "Deadlock found when trying to get lock" every few minutes. The operations are: update order status and update inventory count, happening simultaneously in different transactions.

**Expected Answer:**

**Why Deadlocks Happen**
- Thread A locks Order(1) then tries to lock Inventory(5)
- Thread B locks Inventory(5) then tries to lock Order(1)
- Circular wait = deadlock

**Fix 1 — Consistent Lock Ordering**

```java
// Always acquire locks in same order: Order → Inventory → Payment
@Transactional
public void processOrder(long orderId, long inventoryId) {
    // Use SELECT FOR UPDATE with explicit order
    Order order = em.createQuery(
        "SELECT o FROM Order o WHERE o.id = :id", Order.class)
        .setLockMode(LockModeType.PESSIMISTIC_WRITE)
        .setParameter("id", orderId).getSingleResult();
    // Only then lock inventory
    Inventory inv = em.find(Inventory.class, inventoryId,
        LockModeType.PESSIMISTIC_WRITE);
}
```

**Fix 2 — Optimistic Locking (Better for Low Contention)**

```java
@Entity
public class Inventory {
    @Version
    private int version; // JPA auto-checks on update
}
// Throws OptimisticLockException if another TX modified it
// Retry with @Retryable(OptimisticLockException.class, maxAttempts=3)
```

**Fix 3 — Reduce Transaction Scope**
- Keep transactions as short as possible — no external HTTP calls inside `@Transactional`
- Use `REQUIRES_NEW` for audit/logging to avoid inheriting long transactions
- Consider event-driven: process inventory update asynchronously via queue

**Follow-up:** What is the difference between optimistic and pessimistic locking? In what scenarios does optimistic locking actually perform worse than pessimistic?

---

#### Q52. Multi-Tenant Data Isolation in SaaS

**Difficulty:** HARD · **Topic:** DB / JPA

**Q:** *Design a multi-tenant data isolation strategy in a SaaS application.*

You are building a B2B SaaS. Customer A must never see Customer B data. You have 500 tenants ranging from 100 to 50,000 rows each. Some tenants need custom columns. Explain your isolation strategy.

**Expected Answer:**

**Three Approaches (Trade-offs)**
- **DB per tenant**: strongest isolation, expensive at 500 tenants, hard to query across
- **Schema per tenant**: good isolation, Postgres supports well, manageable at 500
- **Shared table + tenant_id**: simplest, highest risk of data leak, best performance

**Recommended: Row-Level Security (Postgres)**

```sql
-- Enable RLS on every tenant table
ALTER TABLE orders ENABLE ROW LEVEL SECURITY;

CREATE POLICY tenant_isolation ON orders
    USING (tenant_id = current_setting('app.tenant_id')::uuid);
```

```java
// In Spring Boot, set tenant_id per-request in HikariCP connection
@Component
public class TenantInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest req, ...) {
        String tenantId = extractTenantFromJwt(req);
        TenantContext.setTenantId(tenantId);
        dataSource.setSessionProperty("app.tenant_id", tenantId);
        return true;
    }
}
```

**Custom Columns per Tenant**
- **EAV** (Entity-Attribute-Value): flexible but terrible for queries
- **JSONB column**: store tenant-specific fields in JSON — Postgres indexes JSONB well
- **Schema-per-tenant**: each tenant can have truly custom columns via migrations

**Follow-up:** How do you perform cross-tenant analytics (e.g., average order value across all tenants) without violating isolation? What architectural pattern enables this?

---

#### Q59. Database Sharding Strategy for 10 Billion Rows

**Difficulty:** SENIOR · **Topic:** DB / JPA

**Q:** *Design a database sharding strategy for 10 billion rows.*

Your `user_events` table grows by 100M rows/day. Queries are already taking 30 seconds. You cannot afford downtime. Explain your sharding approach, shard key choice, and cross-shard query strategy.

**Expected Answer:**

**Shard Key Selection (Most Critical Decision)**
- **user_id**: most queries are per-user → data locality, hot shard risk if some users are huge
- **event_time**: range sharding, good for time-series queries, hot shard on current time period
- **Consistent hash of user_id**: even distribution, hard to do range queries
- **Composite (user_id % 256)**: 256 logical shards, map to N physical nodes, rebalance by moving logical shards

**Horizontal Sharding with Vitess**

```json
{
  "sharded": true,
  "vindexes": {
    "user_hash": { "type": "hash" }
  },
  "tables": {
    "user_events": {
      "column_vindexes": [
        { "column": "user_id", "name": "user_hash" }
      ]
    }
  }
}
```

```sql
-- Vitess routes: SELECT * FROM user_events WHERE user_id=123
-- → automatically goes to correct shard
```

**Cross-Shard Queries**
- Scatter-gather: send query to all shards in parallel, merge results in application
- Expensive — avoid or pre-aggregate with a separate analytics store (ClickHouse, BigQuery)
- Maintain a global aggregation table updated by streaming (Kafka → Flink → analytics DB)
- For JOINs across shards: co-locate related data on same shard (denormalize)

**Zero-Downtime Migration**
- Dual-write: write to both old monolithic DB and new sharded DB simultaneously
- Backfill historical data using batch jobs reading from old DB
- Read from new sharded DB when backfill reaches 100%
- Remove writes to old DB after 2-week validation period

**Follow-up:** How do you handle global unique IDs across shards? Why is auto-increment no longer sufficient and what alternatives exist (Snowflake IDs, ULIDs)?

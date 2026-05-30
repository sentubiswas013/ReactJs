# Java Full Stack – Scenario-Based Interview Questions (Part 2)

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

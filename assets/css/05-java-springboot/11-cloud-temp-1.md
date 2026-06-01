
# Java Full Stack Developer & Architecture Engineer
## Scenario-Based Interview Questions — Senior / Lead Level

> **How to use this guide:** Each question includes a realistic production scenario, what the interviewer is testing, a model answer with code, and follow-up probes. Practice answering out loud before reading the expected answer.

---

## Table of Contents

1. [Core Java & JVM Internals](#1-core-java--jvm-internals)
2. [Concurrency & Multithreading](#2-concurrency--multithreading)
3. [Memory Management & GC Tuning](#3-memory-management--gc-tuning)
4. [Design Patterns & OOP Principles](#4-design-patterns--oop-principles)
5. [Java Collections & Streams](#5-java-collections--streams)
6. [Spring Boot & Dependency Injection](#6-spring-boot--dependency-injection)
7. [Database & JPA / Hibernate](#7-database--jpa--hibernate)
8. [Microservices Architecture](#8-microservices-architecture)
9. [Security](#9-security)
10. [Performance & Scalability](#10-performance--scalability)
11. [Testing Strategy](#11-testing-strategy)
12. [Architecture & System Design](#12-architecture--system-design)

---

## 1. Core Java & JVM Internals

---

### Q1 — ClassLoader Conflict in Production

**Scenario:**  
Your team deploys a large enterprise application on JBoss. After deployment, you get `ClassCastException: com.company.UserService cannot be cast to com.company.UserService`. Both sides are literally the same class name. The app worked fine locally.

**What the interviewer is testing:**  
Understanding of ClassLoader hierarchy, parent delegation model, and isolation.

**Expected Answer:**

Java's ClassLoader follows a **parent-delegation model**: Bootstrap → Extension → Application. When two different ClassLoaders load the same class, JVM treats them as distinct types — even if bytecode is identical. In app servers like JBoss/WildFly, each deployment gets its own ClassLoader.

**Root Cause:** The class was loaded by two different ClassLoaders — e.g., once from the WAR's `WEB-INF/lib` and once from a shared module. JVM identity check fails.

**Fix:**

```java
// Diagnose: print the classloader of the object
Object obj = someFactory.get();
System.out.println(obj.getClass().getClassLoader());
System.out.println(UserService.class.getClassLoader());

// If they differ — you have a classloader isolation problem.
// Solution: remove the duplicate JAR from one scope,
// or use a shared module with proper ClassLoader delegation.
```

**Prevention:**
- Keep shared types in a dedicated shared module loaded by the parent ClassLoader.
- Avoid bundling `provided`-scoped JARs inside WAR/EAR.
- Use `jboss-deployment-structure.xml` to explicitly control ClassLoader visibility.

**Follow-up Probes:**
- How does OSGi solve ClassLoader isolation differently?
- What happens with `Class.forName()` vs `Thread.currentThread().getContextClassLoader().loadClass()`?
- How would you write a custom ClassLoader for a plugin system?

---

### Q2 — `String` Interning and Memory Leak

**Scenario:**  
A batch processing service reads millions of short String values from a database (e.g., country codes, status flags) and stores them in a `List<String>`. Heap usage grows unbounded over hours. A heap dump shows thousands of duplicate String objects with the same content.

**What the interviewer is testing:**  
String pool internals, `intern()`, memory efficiency, trade-offs.

**Expected Answer:**

Java Strings are objects on the heap. Without interning, identical strings from `new String(...)` or `ResultSet.getString(...)` are separate heap objects.

```java
// Problem: each of these is a DISTINCT heap object
String a = new String("ACTIVE");
String b = new String("ACTIVE");
System.out.println(a == b);       // false
System.out.println(a.equals(b));  // true — but wastes memory

// Fix: intern() stores in the String Pool (PermGen/Metaspace)
String a = "ACTIVE".intern();
String b = "ACTIVE".intern();
System.out.println(a == b);       // true — same reference

// Better production fix for bounded vocabularies:
private static final Map<String, String> CACHE = new ConcurrentHashMap<>();

public String deduplicate(String value) {
    return CACHE.computeIfAbsent(value, v -> v);
}
```

**Trade-off:** `intern()` writes to a native hash table — high contention under load. For bounded vocabularies, prefer a `ConcurrentHashMap`-based dedup cache or an `Enum`.

**Follow-up Probes:**
- Where does the String pool live in Java 8+ vs Java 7?
- What's the difference between `==` and `equals()` for Strings in an interview — and what does it mean in production?
- How does `compact strings` in Java 9 help memory?

---

## 2. Concurrency & Multithreading

---

### Q3 — Deadlock in Payment Processing

**Scenario:**  
A payment service transfers funds between two bank accounts. Occasionally it hangs completely under load. Thread dump shows multiple threads in `BLOCKED` state, each holding a lock the other needs.

**What the interviewer is testing:**  
Deadlock detection, lock ordering, alternative locking strategies.

**Expected Answer:**

Classic deadlock: Thread 1 locks Account A then waits for Account B. Thread 2 locks Account B then waits for Account A.

```java
// BROKEN: Lock order depends on call order → deadlock possible
public void transfer(Account from, Account to, BigDecimal amount) {
    synchronized (from) {
        synchronized (to) {         // Thread 2 might already hold 'to'
            from.debit(amount);
            to.credit(amount);
        }
    }
}

// FIX 1: Consistent lock ordering using account ID
public void transfer(Account from, Account to, BigDecimal amount) {
    Account first  = from.getId() < to.getId() ? from : to;
    Account second = from.getId() < to.getId() ? to   : from;

    synchronized (first) {
        synchronized (second) {
            from.debit(amount);
            to.credit(amount);
        }
    }
}

// FIX 2: tryLock with timeout (preferred for high-contention)
public boolean transfer(Account from, Account to, BigDecimal amount)
        throws InterruptedException {
    ReentrantLock lockA = from.getLock();
    ReentrantLock lockB = to.getLock();

    while (true) {
        if (lockA.tryLock(50, TimeUnit.MILLISECONDS)) {
            try {
                if (lockB.tryLock(50, TimeUnit.MILLISECONDS)) {
                    try {
                        from.debit(amount);
                        to.credit(amount);
                        return true;
                    } finally {
                        lockB.unlock();
                    }
                }
            } finally {
                lockA.unlock();
            }
        }
        Thread.sleep(10); // back-off before retry
    }
}
```

**Follow-up Probes:**
- How do you detect a deadlock in production without restarting the JVM?
- What is livelock? Give a realistic example.
- When would you use `StampedLock` over `ReentrantReadWriteLock`?

---

### Q4 — Race Condition in Singleton Lazy Initialization

**Scenario:**  
Your team ships a `ConfigurationManager` with lazy initialization. Under heavy startup load, two threads simultaneously detect `instance == null`, and two distinct instances are created — causing inconsistent config across the app.

**What the interviewer is testing:**  
Double-checked locking, `volatile`, initialization-on-demand holder, enum singleton.

**Expected Answer:**

```java
// BROKEN: No synchronization
public class ConfigurationManager {
    private static ConfigurationManager instance;

    public static ConfigurationManager getInstance() {
        if (instance == null) {               // Thread A and B both see null
            instance = new ConfigurationManager(); // both create instances
        }
        return instance;
    }
}

// FIX 1: Double-checked locking with volatile (Java 5+)
public class ConfigurationManager {
    // volatile prevents instruction reordering during construction
    private static volatile ConfigurationManager instance;

    public static ConfigurationManager getInstance() {
        if (instance == null) {
            synchronized (ConfigurationManager.class) {
                if (instance == null) {
                    instance = new ConfigurationManager();
                }
            }
        }
        return instance;
    }
}

// FIX 2: Initialization-on-demand holder (preferred — no sync overhead)
public class ConfigurationManager {
    private ConfigurationManager() {}

    private static class Holder {
        // Class loaded lazily by JVM; class loading is thread-safe
        static final ConfigurationManager INSTANCE = new ConfigurationManager();
    }

    public static ConfigurationManager getInstance() {
        return Holder.INSTANCE;
    }
}

// FIX 3: Enum singleton (safest, serialization-safe)
public enum ConfigurationManager {
    INSTANCE;

    public String getProperty(String key) { /* ... */ return null; }
}
```

**Follow-up Probes:**
- Why was double-checked locking broken before Java 5?
- How does `volatile` prevent the partial-construction visibility problem?
- How would you handle singleton destruction/cleanup at app shutdown?

---

### Q5 — ThreadLocal Memory Leak in App Server

**Scenario:**  
After several days, a Spring application running on Tomcat starts throwing `OutOfMemoryError`. A heap dump reveals a large number of objects reachable from `Thread` → `threadLocals` map. The threads are from Tomcat's thread pool.

**What the interviewer is testing:**  
`ThreadLocal` lifecycle, thread pool reuse, cleanup discipline.

**Expected Answer:**

Thread pools **reuse** threads. If a `ThreadLocal` is set but never removed, it persists in the thread's `threadLocalMap` for the lifetime of the thread — which is the lifetime of the app server.

```java
// PROBLEM: ThreadLocal set per request, never cleaned up
public class RequestContext {
    private static final ThreadLocal<UserSession> SESSION =
        new ThreadLocal<>();

    public static void set(UserSession session) {
        SESSION.set(session);       // set on request start
    }

    public static UserSession get() {
        return SESSION.get();
    }
    // Missing: remove() → memory leak on pooled threads!
}

// FIX: Always remove in a finally block
public class RequestFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {
        try {
            RequestContext.set(buildSession(req));
            chain.doFilter(req, res);
        } finally {
            RequestContext.remove();   // ← CRITICAL: always clean up
        }
    }
}

// BETTER: Use a try-with-resource wrapper
public class ScopedContext implements AutoCloseable {
    private static final ThreadLocal<UserSession> HOLDER = new ThreadLocal<>();

    public ScopedContext(UserSession session) {
        HOLDER.set(session);
    }

    public static UserSession get() { return HOLDER.get(); }

    @Override
    public void close() {
        HOLDER.remove();
    }
}

// Usage
try (var ctx = new ScopedContext(session)) {
    chain.doFilter(req, res);
}
```

**Follow-up Probes:**
- What is `InheritableThreadLocal` and when is it dangerous?
- How does Spring's `RequestContextHolder` handle this problem?
- How would you detect `ThreadLocal` leaks in CI before they hit production?

---

## 3. Memory Management & GC Tuning

---

### Q6 — G1GC Pause Spikes in a Low-Latency Service

**Scenario:**  
A trading platform's REST API must respond under 100ms (P99). Occasionally you see 500ms+ pauses in application logs correlating with GC activity. The heap is 8GB, using default GC settings on Java 17.

**What the interviewer is testing:**  
GC algorithm selection, tuning flags, off-heap alternatives.

**Expected Answer:**

Java 17 defaults to G1GC. Long pauses indicate large GC regions being evacuated, humongous object allocations, or mixed collection storms.

**Diagnosis steps:**
```bash
# Enable GC logging
-Xlog:gc*:file=/var/log/app-gc.log:time,uptime,level,tags:filecount=5,filesize=20m

# Watch for:
# [GC pause (G1 Evacuation Pause)] — normal young GC
# [GC pause (G1 Mixed)]            — old gen cleanup (can be long)
# [GC pause (G1 Humongous Allocation)] — objects > region/2
```

**Tuning:**
```bash
# Target max pause time
-XX:MaxGCPauseMillis=50

# Increase region size to reduce humongous allocations (if you have large byte[])
-XX:G1HeapRegionSize=16m

# Initiate mixed GC earlier (before old gen fills up)
-XX:InitiatingHeapOccupancyPercent=35

# For Java 17: try ZGC for sub-millisecond pauses
-XX:+UseZGC
```

```java
// Application-level fix: avoid humongous allocations
// BAD: allocating large temporary buffers
byte[] buffer = new byte[20 * 1024 * 1024]; // 20MB — humongous in G1

// GOOD: pool and reuse buffers
private static final ObjectPool<byte[]> BUFFER_POOL = ...;
byte[] buffer = BUFFER_POOL.borrow();
try {
    process(buffer);
} finally {
    BUFFER_POOL.release(buffer);
}
```

**Follow-up Probes:**
- When would you choose ZGC vs Shenandoah vs G1?
- What is TLAB and how does it help allocation performance?
- Explain the difference between minor GC, major GC, and full GC.

---

## 4. Design Patterns & OOP Principles

---

### Q7 — Overengineered Inheritance Hierarchy

**Scenario:**  
You join a team maintaining a `Payment` hierarchy: `AbstractPayment → CreditPayment → InternationalCreditPayment → RecurringInternationalCreditPayment`. Adding a new payment type requires touching 4 classes and understanding 600 lines of inherited state. The team says "it's OOP."

**What the interviewer is testing:**  
Composition over inheritance, SOLID principles, refactoring judgment.

**Expected Answer:**

Deep inheritance couples behavior to hierarchy. The fix is **composition with Strategy/Decorator patterns**.

```java
// BEFORE: Fragile inheritance
public abstract class AbstractPayment {
    protected BigDecimal amount;
    protected Currency currency;
    public abstract void process();
    public abstract void validate();
}

public class RecurringInternationalCreditPayment extends InternationalCreditPayment {
    // Inherits 4 levels of hidden state and behavior
}

// AFTER: Compose behaviors as strategies
public interface PaymentProcessor {
    PaymentResult process(PaymentRequest request);
}

public interface PaymentValidator {
    ValidationResult validate(PaymentRequest request);
}

public interface CurrencyConverter {
    BigDecimal convert(BigDecimal amount, Currency from, Currency to);
}

public class Payment {
    private final PaymentProcessor  processor;
    private final PaymentValidator  validator;
    private final CurrencyConverter converter;  // optional

    public Payment(PaymentProcessor processor,
                   PaymentValidator validator,
                   CurrencyConverter converter) {
        this.processor = processor;
        this.validator = validator;
        this.converter = converter;
    }

    public PaymentResult execute(PaymentRequest request) {
        ValidationResult validation = validator.validate(request);
        if (!validation.isValid()) {
            throw new PaymentValidationException(validation);
        }
        return processor.process(request);
    }
}

// Adding a new type = new implementations, zero changes to existing code (OCP)
PaymentProcessor stripeProcessor = new StripePaymentProcessor(stripeClient);
PaymentValidator internationalValidator = new InternationalPaymentValidator(sanctionsService);
Payment p = new Payment(stripeProcessor, internationalValidator, fxConverter);
```

**Follow-up Probes:**
- What SOLID principle does deep inheritance most commonly violate?
- When IS inheritance the right choice?
- How would you introduce this refactoring in a live codebase with 300 existing usages?

---

### Q8 — Decorator Pattern for Audit Logging

**Scenario:**  
You're asked to add audit logging to 15 different `Repository` implementations without modifying each one. The logging must capture method name, duration, user context, and result.

**What the interviewer is testing:**  
Decorator pattern, AOP awareness, proxy-based approaches.

**Expected Answer:**

```java
// Core interface
public interface UserRepository {
    User findById(Long id);
    void save(User user);
    void delete(Long id);
}

// Real implementation
public class JpaUserRepository implements UserRepository {
    @Override public User findById(Long id) { /* JPA logic */ return null; }
    @Override public void save(User user)   { /* JPA logic */ }
    @Override public void delete(Long id)  { /* JPA logic */ }
}

// Decorator: wraps ANY UserRepository with audit logging
public class AuditingUserRepository implements UserRepository {
    private final UserRepository delegate;
    private final AuditLogger    auditLogger;

    public AuditingUserRepository(UserRepository delegate, AuditLogger auditLogger) {
        this.delegate    = delegate;
        this.auditLogger = auditLogger;
    }

    @Override
    public User findById(Long id) {
        long start = System.nanoTime();
        try {
            User result = delegate.findById(id);
            auditLogger.log("findById", id, System.nanoTime() - start, "SUCCESS");
            return result;
        } catch (Exception e) {
            auditLogger.log("findById", id, System.nanoTime() - start, "FAILURE");
            throw e;
        }
    }

    @Override public void save(User user)   { /* similar wrapping */ }
    @Override public void delete(Long id)   { /* similar wrapping */ }
}

// Wiring (manual)
UserRepository repo = new AuditingUserRepository(
    new JpaUserRepository(), auditLogger
);

// Or: Dynamic proxy approach for all methods at once
UserRepository repo = (UserRepository) Proxy.newProxyInstance(
    UserRepository.class.getClassLoader(),
    new Class[]{UserRepository.class},
    new AuditInvocationHandler(new JpaUserRepository(), auditLogger)
);
```

**Production note:** In Spring, `@Aspect` + `@Around` achieves this declaratively without boilerplate per repository.

**Follow-up Probes:**
- What's the difference between Decorator and Proxy patterns?
- How does Spring AOP create proxies internally (JDK dynamic proxy vs CGLIB)?
- What are limitations of JDK dynamic proxies?

---

## 5. Java Collections & Streams

---

### Q9 — `ConcurrentModificationException` in Production

**Scenario:**  
A background job iterates over a `List<Order>` while another thread can add orders to the same list. Occasionally you get `ConcurrentModificationException` in production at 2 AM.

**What the interviewer is testing:**  
Fail-fast iterators, concurrent collections, copy-on-write semantics.

**Expected Answer:**

`ArrayList` uses a `modCount` counter. Any structural modification (add/remove) during iteration throws `ConcurrentModificationException` — this is **fail-fast** behavior, not a thread-safety guarantee.

```java
// BROKEN: ArrayList is not thread-safe
List<Order> orders = new ArrayList<>();

// Thread 1: iterating
for (Order o : orders) {    // uses iterator with modCount snapshot
    process(o);
}

// Thread 2 (concurrently): adding
orders.add(newOrder);       // increments modCount → CME thrown in Thread 1

// FIX 1: CopyOnWriteArrayList (best for read-heavy, rare writes)
List<Order> orders = new CopyOnWriteArrayList<>();
// Writes create a NEW internal array — iterators see the snapshot at creation time
// No CME. Reads are lock-free.

// FIX 2: Synchronized snapshot iteration
List<Order> snapshot;
synchronized (orders) {
    snapshot = new ArrayList<>(orders);
}
for (Order o : snapshot) {
    process(o);
}

// FIX 3: ConcurrentLinkedQueue (if order doesn't matter)
Queue<Order> orders = new ConcurrentLinkedQueue<>();

// FIX 4: Iterator.remove() for safe removal during iteration
Iterator<Order> it = orders.iterator();
while (it.hasNext()) {
    Order o = it.next();
    if (o.isExpired()) {
        it.remove();    // safe — uses iterator's own remove
    }
}
```

**Follow-up Probes:**
- When would you choose `CopyOnWriteArrayList` over `Collections.synchronizedList()`?
- What is the difference between fail-fast and fail-safe iterators?
- How does `ConcurrentHashMap` handle concurrent iteration differently from `HashMap`?

---

### Q10 — Stream Pipeline Performance Problem

**Scenario:**  
A report generator processes a `List<Transaction>` with 2 million entries using multiple chained stream operations. It takes 45 seconds. A colleague says "just add `.parallel()`". You disagree.

**What the interviewer is testing:**  
Stream internals, parallel stream pitfalls, when parallelism helps vs hurts.

**Expected Answer:**

```java
// Current code (sequential, 45s):
List<ReportLine> report = transactions.stream()
    .filter(t -> t.getDate().isAfter(cutoff))
    .map(t -> enrichWithAccountData(t))        // calls DB — IO-bound!
    .sorted(Comparator.comparing(Transaction::getAmount).reversed())
    .collect(Collectors.toList());

// Adding .parallel() is WRONG here because:
// 1. enrichWithAccountData() hits a database — parallel threads will
//    saturate the connection pool (e.g., HikariCP default: 10 connections)
// 2. .sorted() on parallel streams requires a merge sort — expensive
// 3. ForkJoinPool.commonPool() is shared — you'll starve other parallel ops

// CORRECT FIX: Batch the DB calls (N+1 → 1 query)
Set<Long> accountIds = transactions.stream()
    .filter(t -> t.getDate().isAfter(cutoff))
    .map(Transaction::getAccountId)
    .collect(Collectors.toSet());

Map<Long, Account> accounts = accountRepo.findAllByIdIn(accountIds)  // 1 query
    .stream()
    .collect(Collectors.toMap(Account::getId, Function.identity()));

List<ReportLine> report = transactions.stream()
    .filter(t -> t.getDate().isAfter(cutoff))
    .map(t -> toReportLine(t, accounts.get(t.getAccountId())))   // in-memory
    .sorted(Comparator.comparing(ReportLine::getAmount).reversed())
    .collect(Collectors.toList());
// Result: < 2s

// Parallel IS appropriate when:
// - Pure CPU-bound computation (no IO, no shared mutable state)
// - Large data set (overhead only pays off above ~10k elements)
// - Splittable data source (ArrayList splits well; LinkedList does not)
long sum = IntStream.range(0, 10_000_000)
    .parallel()
    .filter(n -> n % 2 == 0)
    .mapToLong(Long::valueOf)
    .sum();   // legitimate parallel use
```

**Follow-up Probes:**
- What thread pool does parallel streams use by default? How do you change it?
- Why does `LinkedList` perform poorly as a parallel stream source?
- How would you handle checked exceptions inside a stream lambda?

---

## 6. Spring Boot & Dependency Injection

---

### Q11 — Circular Dependency Causing Context Startup Failure

**Scenario:**  
After merging two feature branches, the Spring context fails to start with: `The dependencies of some of the beans in the application context form a cycle: ServiceA → ServiceB → ServiceA`. Neither developer thought they were creating a cycle.

**What the interviewer is testing:**  
DI fundamentals, how Spring resolves beans, architectural smell recognition.

**Expected Answer:**

Spring cannot instantiate beans in a circular dependency through **constructor injection** (which is correct — it's a design smell).

```java
// BROKEN: Circular dependency
@Service
public class OrderService {
    private final NotificationService notificationService;

    public OrderService(NotificationService notificationService) {  // ← constructor injection
        this.notificationService = notificationService;
    }
}

@Service
public class NotificationService {
    private final OrderService orderService;   // ← closes the cycle!

    public NotificationService(OrderService orderService) {
        this.orderService = orderService;
    }
}

// BAD FIX (avoid): @Lazy or field injection — hides the design problem
@Autowired
@Lazy
private OrderService orderService;

// REAL FIX: Break the cycle by introducing a domain event / mediator
// OrderService raises an event; NotificationService listens to it.

@Service
public class OrderService {
    private final ApplicationEventPublisher eventPublisher;

    public OrderService(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void placeOrder(Order order) {
        // business logic
        eventPublisher.publishEvent(new OrderPlacedEvent(order));
    }
}

@Service
public class NotificationService {
    @EventListener
    public void onOrderPlaced(OrderPlacedEvent event) {
        // send notification — no dependency on OrderService at all
    }
}
```

**Follow-up Probes:**
- Why does Spring allow circular dependencies with setter/field injection but not constructor injection?
- What does `@Lazy` actually do to resolve a cycle — and why is it a band-aid?
- How do you enforce constructor injection across a large codebase?

---

### Q12 — `@Transactional` Not Rolling Back

**Scenario:**  
A service method annotated `@Transactional` throws a checked exception (`InsufficientFundsException`). The transaction does NOT roll back, leaving partial data in the database. The team is confused — the annotation is definitely there.

**What the interviewer is testing:**  
Spring transaction internals, rollback rules, self-invocation, proxy mechanics.

**Expected Answer:**

Spring's `@Transactional` only rolls back on **unchecked exceptions** (`RuntimeException` and `Error`) by default. Checked exceptions commit the transaction unless explicitly configured.

```java
// BROKEN: checked exception — Spring commits by default
@Transactional
public void transferFunds(Long fromId, Long toId, BigDecimal amount)
        throws InsufficientFundsException {
    accountRepo.debit(fromId, amount);      // written to DB
    if (!accountRepo.hasFunds(fromId)) {
        throw new InsufficientFundsException("Not enough funds");
        // ← Spring sees a checked exception → COMMITS the debit!
    }
    accountRepo.credit(toId, amount);
}

// FIX 1: Declare rollback for checked exception
@Transactional(rollbackFor = InsufficientFundsException.class)
public void transferFunds(Long fromId, Long toId, BigDecimal amount)
        throws InsufficientFundsException { ... }

// FIX 2: Make it a RuntimeException
public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(String msg) { super(msg); }
}

// HIDDEN TRAP: Self-invocation bypasses the proxy
@Service
public class PaymentService {
    @Transactional
    public void outerMethod() {
        innerMethod();     // ← calls 'this.innerMethod()' directly,
    }                      //   NOT through the Spring proxy!

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void innerMethod() {
        // This @Transactional is IGNORED when called from outerMethod()
    }
}

// FIX for self-invocation: inject self or extract to a new bean
@Service
public class PaymentService {
    @Autowired
    private PaymentService self;   // Spring injects the proxy

    public void outerMethod() {
        self.innerMethod();    // goes through proxy — transaction works
    }
}
```

**Follow-up Probes:**
- What does `Propagation.REQUIRES_NEW` do? When would you use it?
- What is the difference between `@Transactional` on a class vs a method?
- How do you test that a transaction actually rolled back?

---

## 7. Database & JPA / Hibernate

---

### Q13 — N+1 Query Problem at Scale

**Scenario:**  
A product listing API returns `Product` entities with their `Category` and list of `Tag` objects. In development (10 products) it's fine. In production (10,000 products) it fires 20,001 SQL queries per request and times out.

**What the interviewer is testing:**  
Hibernate lazy loading, fetch strategies, DTO projections, query optimization.

**Expected Answer:**

By default, `@OneToMany` is `FetchType.LAZY`. Accessing the collection in a loop triggers a new SELECT per entity — the N+1 problem.

```java
// ENTITY (default: lazy collections)
@Entity
public class Product {
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<Tag> tags;
}

// BROKEN: 1 query for products + N queries for categories + N for tags
List<Product> products = productRepo.findAll();
products.forEach(p -> {
    String cat = p.getCategory().getName();   // SELECT per product
    p.getTags().forEach(t -> log(t.getName())); // SELECT per product
});

// FIX 1: JOIN FETCH (for @ManyToOne and small @OneToMany)
@Query("SELECT p FROM Product p JOIN FETCH p.category LEFT JOIN FETCH p.tags")
List<Product> findAllWithDetails();
// Warning: JOIN FETCH on a @OneToMany returns duplicate Product rows
// → add DISTINCT or use Set

// FIX 2: EntityGraph (declarative)
@EntityGraph(attributePaths = {"category", "tags"})
List<Product> findAll();

// FIX 3: Batch fetching (Hibernate-specific, no query changes)
@OneToMany
@BatchSize(size = 50)    // fetches tags for 50 products per query → ~200 queries → 1+200/50 = 5
private List<Tag> tags;

// FIX 4 (best for read-only APIs): DTO projection — never load entities
@Query("""
    SELECT new com.example.ProductDTO(p.id, p.name, c.name)
    FROM Product p JOIN p.category c
""")
List<ProductDTO> findProductSummaries();
```

**Follow-up Probes:**
- What is the Hibernate first-level cache and how does it affect N+1?
- When does `JOIN FETCH` on a collection cause a `MultipleBagFetchException`?
- How would you profile Hibernate SQL queries in production?

---

### Q14 — Optimistic Locking Conflict in High-Traffic Update

**Scenario:**  
A ticket booking system lets multiple users book the last seat simultaneously. Without concurrency control, two users both book the same seat and it appears double-booked. The team suggests `synchronized` keyword.

**What the interviewer is testing:**  
Optimistic vs pessimistic locking, `@Version`, database-level locking.

**Expected Answer:**

`synchronized` only works within a single JVM. In a clustered deployment, multiple nodes can proceed independently. You need **database-level** concurrency control.

```java
// ENTITY with optimistic locking
@Entity
public class Seat {
    @Id
    private Long id;

    private boolean booked;

    @Version                    // Hibernate increments this on every UPDATE
    private Long version;
}

// SERVICE: optimistic locking — no SELECT FOR UPDATE, high concurrency
@Transactional
public void bookSeat(Long seatId, Long userId) {
    Seat seat = seatRepo.findById(seatId)
        .orElseThrow(() -> new SeatNotFoundException(seatId));

    if (seat.isBooked()) {
        throw new SeatAlreadyBookedException(seatId);
    }

    seat.setBooked(true);
    seat.setBookedBy(userId);
    // Hibernate emits: UPDATE seat SET booked=true, version=2 WHERE id=? AND version=1
    // If another transaction already updated, version mismatch → OptimisticLockException
}

// CALLER: handle contention gracefully
@Retryable(value = OptimisticLockingFailureException.class, maxAttempts = 3)
public void bookWithRetry(Long seatId, Long userId) {
    bookSeat(seatId, userId);
}

// FOR HIGH-CONTENTION (e.g., flash sale): pessimistic locking
@Lock(LockModeType.PESSIMISTIC_WRITE)
@Query("SELECT s FROM Seat s WHERE s.id = :id")
Optional<Seat> findByIdForUpdate(@Param("id") Long id);
// Emits: SELECT ... FOR UPDATE — only one transaction proceeds at a time
```

**Follow-up Probes:**
- What exception does Spring throw when optimistic locking fails? How do you catch it?
- What is the difference between `PESSIMISTIC_READ` and `PESSIMISTIC_WRITE`?
- How would you implement this without an ORM (pure JDBC)?

---

## 8. Microservices Architecture

---

### Q15 — Cascading Failure Due to Missing Circuit Breaker

**Scenario:**  
Your `order-service` calls `inventory-service` synchronously. `inventory-service` goes down. Within 60 seconds, `order-service` thread pool is exhausted (all threads waiting on timed-out HTTP connections), and now `order-service` is also down. This cascade takes out 3 more services.

**What the interviewer is testing:**  
Circuit breaker pattern, bulkhead, timeout design, Resilience4j.

**Expected Answer:**

Synchronous inter-service calls propagate failures. The circuit breaker **detects failure rates** and short-circuits calls before they exhaust resources.

```java
// Add Resilience4j dependency, then:

// application.yml
resilience4j:
  circuitbreaker:
    instances:
      inventory:
        slidingWindowSize: 10
        failureRateThreshold: 50         # open after 50% failures in last 10 calls
        waitDurationInOpenState: 10s     # stay open for 10s before half-open
        permittedNumberOfCallsInHalfOpenState: 3
  timelimiter:
    instances:
      inventory:
        timeoutDuration: 2s              # don't wait more than 2s

// Service
@Service
public class OrderService {
    private final InventoryClient inventoryClient;

    @CircuitBreaker(name = "inventory", fallbackMethod = "inventoryFallback")
    @TimeLimiter(name = "inventory")
    @Bulkhead(name = "inventory")       // limits concurrent calls to inventory
    public CompletableFuture<InventoryStatus> checkInventory(Long productId) {
        return CompletableFuture.supplyAsync(
            () -> inventoryClient.getStatus(productId)
        );
    }

    // Fallback: degrade gracefully
    public CompletableFuture<InventoryStatus> inventoryFallback(
            Long productId, Exception ex) {
        log.warn("Inventory service unavailable for product {}: {}", productId, ex.getMessage());
        // Return a cached value, or allow order to proceed optimistically
        return CompletableFuture.completedFuture(InventoryStatus.UNKNOWN);
    }
}
```

**Circuit Breaker States:**
```
CLOSED → (failure rate > threshold) → OPEN → (wait duration) → HALF_OPEN
  ↑                                                                  |
  └────────────── (probe calls succeed) ─────────────────────────────┘
```

**Follow-up Probes:**
- What is the difference between a circuit breaker and a retry? When do you combine them?
- How does the bulkhead pattern prevent one service from consuming all threads?
- How would you monitor circuit breaker state in production (Actuator / metrics)?

---

### Q16 — Eventual Consistency: Saga vs Two-Phase Commit

**Scenario:**  
A new feature requires: (1) debit customer wallet, (2) create order record, (3) reduce inventory — each in a different microservice with its own database. You need all three to succeed or all three to roll back.

**What the interviewer is testing:**  
Distributed transaction understanding, Saga pattern (choreography vs orchestration), 2PC trade-offs.

**Expected Answer:**

**2PC (Two-Phase Commit)** works but: requires a coordinator, blocks resources during prepare phase, coordinator SPOF, poor scalability.

**Saga Pattern** is preferred for microservices:

```
Orchestration Saga (Saga Orchestrator controls the flow):

OrderSagaOrchestrator
   1. → WalletService.debit(orderId, amount)        [compensate: credit]
   2. → OrderService.createOrder(orderDetails)      [compensate: cancelOrder]
   3. → InventoryService.reserve(productId, qty)    [compensate: release]

   If step 3 fails:
   → InventoryService: no action needed (failed)
   → OrderService.cancelOrder(orderId)
   → WalletService.credit(orderId, amount)
```

```java
// Orchestrator using Spring State Machine or a simple pattern:
@Service
public class OrderSagaOrchestrator {

    public SagaResult executePlaceOrder(PlaceOrderCommand cmd) {
        SagaContext ctx = new SagaContext(cmd.getOrderId());
        Deque<Runnable> compensations = new ArrayDeque<>();

        try {
            // Step 1
            walletService.debit(cmd.getCustomerId(), cmd.getAmount());
            compensations.push(() -> walletService.credit(cmd.getCustomerId(), cmd.getAmount()));

            // Step 2
            Long orderId = orderService.createOrder(cmd);
            compensations.push(() -> orderService.cancelOrder(orderId));

            // Step 3
            inventoryService.reserve(cmd.getProductId(), cmd.getQty());
            // no compensation needed if this is last step — or add release

            return SagaResult.success(orderId);

        } catch (Exception e) {
            log.error("Saga failed at step, executing compensations", e);
            compensations.forEach(Runnable::run);  // LIFO compensation
            return SagaResult.failure(e.getMessage());
        }
    }
}
```

**Choreography alternative:** Each service publishes domain events; other services react. No central orchestrator — but harder to trace and reason about.

**Follow-up Probes:**
- What is the difference between orchestration and choreography sagas?
- What is the "outbox pattern" and why is it critical for reliable event publishing?
- How do you handle a compensation that also fails?

---

## 9. Security

---

### Q17 — SQL Injection in a Legacy Search Endpoint

**Scenario:**  
A security audit finds that a legacy search API builds SQL by string concatenation using user input. The endpoint is exposed publicly. You must fix it without a full rewrite.

**What the interviewer is testing:**  
SQL injection fundamentals, parameterized queries, ORM safety, input validation.

**Expected Answer:**

```java
// VULNERABLE: string concatenation
public List<User> searchUsers(String name) {
    String sql = "SELECT * FROM users WHERE name = '" + name + "'";
    // Input: ' OR '1'='1  →  returns ALL users
    // Input: '; DROP TABLE users; --  →  catastrophic
    return jdbcTemplate.query(sql, userRowMapper);
}

// FIX 1: Parameterized query (JDBC)
public List<User> searchUsers(String name) {
    String sql = "SELECT * FROM users WHERE name = ?";
    return jdbcTemplate.query(sql, userRowMapper, name);
    // Driver sends query and data separately — injection impossible
}

// FIX 2: Named parameters (NamedParameterJdbcTemplate)
public List<User> searchUsers(String name) {
    String sql = "SELECT * FROM users WHERE name = :name";
    return namedJdbcTemplate.query(sql, Map.of("name", name), userRowMapper);
}

// FIX 3: JPA Criteria API (dynamic queries)
public List<User> searchUsers(String name) {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<User> cq = cb.createQuery(User.class);
    Root<User> root = cq.from(User.class);
    cq.where(cb.equal(root.get("name"), name));  // parameterized internally
    return em.createQuery(cq).getResultList();
}

// ALSO: Validate and sanitize input as defence-in-depth
public List<User> searchUsers(String name) {
    if (name == null || name.length() > 100 || !name.matches("[a-zA-Z0-9 '-]+")) {
        throw new InvalidInputException("Invalid name format");
    }
    // proceed with parameterized query
}
```

**Follow-up Probes:**
- Is LIKE `%?%` still safe with parameterized queries?
- What is a second-order SQL injection?
- How would you retrofit parameterized queries across 200 legacy DAO methods efficiently?

---

## 10. Performance & Scalability

---

### Q18 — Hot Spot in a High-Traffic Cache

**Scenario:**  
Your application uses Redis to cache product data. A celebrity endorses one product. Suddenly that product's cache key gets 50,000 requests/second. Redis handles it fine, but the single app-node thread doing cache population (on cache miss) becomes the bottleneck.

**What the interviewer is testing:**  
Cache stampede / thundering herd, local caching, probabilistic early expiration.

**Expected Answer:**

When a popular cache entry expires, thousands of threads simultaneously attempt to rebuild it — the **cache stampede** / thundering herd problem.

```java
// PROBLEM: Every thread on cache miss goes to DB
public Product getProduct(Long id) {
    Product cached = redis.get("product:" + id);
    if (cached == null) {
        // 50,000 threads all reach here simultaneously!
        Product product = productRepo.findById(id).orElseThrow();
        redis.setex("product:" + id, 300, product);
        return product;
    }
    return cached;
}

// FIX 1: Mutex lock — only one thread rebuilds, others wait
private final Map<Long, Object> locks = new ConcurrentHashMap<>();

public Product getProduct(Long id) {
    Product cached = redis.get("product:" + id);
    if (cached != null) return cached;

    Object lock = locks.computeIfAbsent(id, k -> new Object());
    synchronized (lock) {
        // double-check after acquiring lock
        cached = redis.get("product:" + id);
        if (cached != null) return cached;

        Product product = productRepo.findById(id).orElseThrow();
        redis.setex("product:" + id, 300, product);
        locks.remove(id);
        return product;
    }
}

// FIX 2: Redis distributed lock (for multi-node)
public Product getProduct(Long id) {
    String cacheKey = "product:" + id;
    String lockKey  = "lock:product:" + id;

    Product cached = redis.get(cacheKey);
    if (cached != null) return cached;

    boolean locked = redis.set(lockKey, "1", "NX", "EX", 5); // SET IF NOT EXISTS
    if (locked) {
        try {
            Product product = productRepo.findById(id).orElseThrow();
            redis.setex(cacheKey, 300, product);
            return product;
        } finally {
            redis.del(lockKey);
        }
    } else {
        // Another node is rebuilding — wait briefly and retry
        Thread.sleep(50);
        return getProduct(id);
    }
}

// FIX 3: Probabilistic early expiration (XFetch algorithm)
// Start refreshing BEFORE expiry to avoid stampede entirely
public Product getProductWithEarlyRefresh(Long id) {
    CacheEntry entry = redis.getWithTTL("product:" + id);
    if (entry != null) {
        double remainingTTL = entry.getTtlSeconds();
        double beta = 1.0; // tuning factor
        if (-beta * Math.log(Math.random()) >= remainingTTL) {
            // probabilistically refresh early
            refreshAsync(id);
        }
        return entry.getValue();
    }
    return rebuild(id);
}
```

**Follow-up Probes:**
- How does Caffeine's local cache with `refreshAfterWrite` help with this?
- What is a two-tier cache (L1 local + L2 Redis) and what are its consistency trade-offs?
- How would you warm the cache before a known traffic event (product launch)?

---

## 11. Testing Strategy

---

### Q19 — Testing a Method with External Dependencies

**Scenario:**  
A `PricingService.calculateFinalPrice()` method calls three external services: a tax API, a discount service, and a currency conversion service. The team has no tests because "it's too hard to mock everything."

**What the interviewer is testing:**  
Mockito, test design, what to verify vs not, testing philosophy (state vs interaction).

**Expected Answer:**

```java
// Subject under test
@Service
public class PricingService {
    private final TaxService        taxService;
    private final DiscountService   discountService;
    private final CurrencyService   currencyService;

    // constructor injection — easy to test!
    public PricingService(TaxService taxService,
                          DiscountService discountService,
                          CurrencyService currencyService) {
        this.taxService       = taxService;
        this.discountService  = discountService;
        this.currencyService  = currencyService;
    }

    public Money calculateFinalPrice(Product product, Customer customer, Currency targetCurrency) {
        BigDecimal basePrice     = product.getBasePrice();
        BigDecimal discount      = discountService.getDiscount(customer, product);
        BigDecimal discountedPrice = basePrice.subtract(discount);
        BigDecimal taxRate       = taxService.getTaxRate(customer.getCountry());
        BigDecimal priceWithTax  = discountedPrice.multiply(BigDecimal.ONE.add(taxRate));
        return currencyService.convert(priceWithTax, Currency.USD, targetCurrency);
    }
}

// TEST
@ExtendWith(MockitoExtension.class)
class PricingServiceTest {

    @Mock TaxService      taxService;
    @Mock DiscountService discountService;
    @Mock CurrencyService currencyService;

    @InjectMocks
    PricingService pricingService;

    @Test
    void calculateFinalPrice_appliesDiscountTaxAndConversion() {
        // ARRANGE
        Product  product  = new Product(new BigDecimal("100.00"));
        Customer customer = new Customer(Country.US);

        when(discountService.getDiscount(customer, product))
            .thenReturn(new BigDecimal("10.00"));               // 10% off
        when(taxService.getTaxRate(Country.US))
            .thenReturn(new BigDecimal("0.08"));                // 8% tax
        when(currencyService.convert(any(), eq(Currency.USD), eq(Currency.EUR)))
            .thenAnswer(inv -> ((BigDecimal) inv.getArgument(0)).multiply(new BigDecimal("0.92")));

        // ACT
        Money result = pricingService.calculateFinalPrice(product, customer, Currency.EUR);

        // ASSERT — verify the math: (100 - 10) * 1.08 * 0.92 = 89.424
        assertThat(result.getAmount())
            .isEqualByComparingTo(new BigDecimal("89.424"));

        // Verify interactions
        verify(discountService, times(1)).getDiscount(customer, product);
        verify(taxService, times(1)).getTaxRate(Country.US);
        verify(currencyService, times(1)).convert(any(), any(), any());
    }

    @Test
    void calculateFinalPrice_withZeroDiscount_appliesOnlyTax() {
        when(discountService.getDiscount(any(), any())).thenReturn(BigDecimal.ZERO);
        when(taxService.getTaxRate(any())).thenReturn(new BigDecimal("0.10"));
        when(currencyService.convert(any(), any(), any()))
            .thenAnswer(inv -> inv.getArgument(0));

        Money result = pricingService.calculateFinalPrice(
            new Product(new BigDecimal("200.00")),
            new Customer(Country.UK), Currency.GBP
        );

        assertThat(result.getAmount()).isEqualByComparingTo("220.00");
    }
}
```

**Follow-up Probes:**
- What's the difference between `@Mock` and `@Spy`?
- When would you use `ArgumentCaptor` over argument matchers?
- How do you test a Spring `@Scheduled` method?

---

## 12. Architecture & System Design

---

### Q20 — Designing a Rate Limiter for a Public API

**Scenario:**  
Your public REST API is being abused by a single client firing 10,000 requests/minute, affecting other users. Design and implement a rate limiter at the API gateway / Spring filter level.

**What the interviewer is testing:**  
Algorithm knowledge (token bucket, sliding window), Redis usage, distributed state, trade-offs.

**Expected Answer:**

**Algorithms:**
| Algorithm | Pros | Cons |
|---|---|---|
| Fixed Window | Simple | Burst at window boundary |
| Sliding Window Log | Accurate | High memory |
| Token Bucket | Smooth bursts | Slightly complex |
| Leaky Bucket | Very smooth | No burst tolerance |

```java
// Token Bucket using Redis (distributed, works across nodes)
@Component
public class RateLimiterFilter extends OncePerRequestFilter {

    private final StringRedisTemplate redis;
    private static final int REQUESTS_PER_MINUTE = 100;
    private static final int BURST_CAPACITY       = 200;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {

        String clientId = extractClientId(request);   // API key or IP
        String key      = "rate:" + clientId;

        // Lua script — atomic check-and-decrement in Redis
        String luaScript = """
            local tokens = tonumber(redis.call('GET', KEYS[1]) or ARGV[2])
            local refill  = tonumber(ARGV[1])
            tokens = math.min(tokens + refill, tonumber(ARGV[2]))
            if tokens >= 1 then
                redis.call('SET', KEYS[1], tokens - 1, 'EX', 60)
                return 1
            else
                return 0
            end
        """;

        Long allowed = redis.execute(
            RedisScript.of(luaScript, Long.class),
            List.of(key),
            "1",                              // refill rate per invocation
            String.valueOf(BURST_CAPACITY)    // bucket capacity
        );

        if (allowed != null && allowed == 1L) {
            chain.doFilter(request, response);
        } else {
            response.setStatus(429);           // Too Many Requests
            response.setHeader("Retry-After", "60");
            response.setHeader("X-RateLimit-Limit", String.valueOf(REQUESTS_PER_MINUTE));
            response.getWriter().write("{\"error\": \"Rate limit exceeded\"}");
        }
    }
}
```

**Follow-up Probes:**
- Why is a Lua script essential for atomic Redis operations here?
- How would you implement per-endpoint rate limits (POST vs GET)?
- What happens to your rate limiter if Redis goes down? (fail-open vs fail-closed)

---

### Q21 — Event-Driven System: Exactly-Once Processing

**Scenario:**  
An order confirmation consumer reads from Kafka. Occasionally the consumer crashes after processing the message but before committing the offset. On restart, it reprocesses the same order — double-charging the customer.

**What the interviewer is testing:**  
Kafka semantics (at-least-once / exactly-once), idempotency, outbox pattern.

**Expected Answer:**

Kafka guarantees **at-least-once** delivery by default. Exactly-once requires idempotent consumers or transactional producers.

```java
// APPROACH 1: Idempotent consumer (simplest, most portable)
@KafkaListener(topics = "order-confirmed")
public void handleOrderConfirmed(OrderConfirmedEvent event) {
    String idempotencyKey = "order-charged:" + event.getOrderId();

    // Check if we've already processed this event
    if (redis.exists(idempotencyKey)) {
        log.info("Duplicate event for order {}, skipping", event.getOrderId());
        return;
    }

    // Process (charge)
    paymentService.charge(event.getOrderId(), event.getAmount());

    // Mark as processed with TTL (event.getOrderId() is unique per order)
    redis.setex(idempotencyKey, Duration.ofDays(7));
}

// APPROACH 2: Database idempotency key
@Transactional
public void handleOrderConfirmed(OrderConfirmedEvent event) {
    if (chargeRepo.existsByOrderId(event.getOrderId())) {
        return;  // idempotent — safe to skip
    }
    Charge charge = paymentGateway.charge(event.getOrderId(), event.getAmount());
    chargeRepo.save(charge);   // unique constraint on orderId prevents double insert
}

// APPROACH 3: Kafka Transactions (exactly-once within Kafka ecosystem)
// application.yml
spring:
  kafka:
    producer:
      transaction-id-prefix: tx-
    consumer:
      isolation-level: read_committed   # only see committed messages

@Bean
public KafkaTransactionManager<String, String> transactionManager(
        ProducerFactory<String, String> pf) {
    return new KafkaTransactionManager<>(pf);
}

@Transactional("kafkaTransactionManager")
@KafkaListener(topics = "order-confirmed")
public void handle(OrderConfirmedEvent event) {
    kafkaTemplate.send("charge-result", new ChargeResultEvent(event.getOrderId()));
    // Both the consume-offset commit and produce happen atomically
}
```

**Follow-up Probes:**
- What is the difference between `enable.idempotence=true` on the producer and exactly-once semantics?
- What is the Transactional Outbox Pattern and when is it better than Kafka transactions?
- How do you monitor consumer lag in production?

---

## Quick-Reference: What Interviewers Really Look For

| Signal | Green Flag | Red Flag |
|---|---|---|
| **Problem framing** | Asks clarifying questions, identifies constraints | Jumps to code immediately |
| **Trade-off awareness** | "It depends — here are the options" | "Always use X" |
| **Production thinking** | Mentions observability, failure modes, rollback | Only happy-path thinking |
| **Code quality** | Clean, correct, handles edge cases | Works but ignores errors |
| **System view** | Discusses impact on other services | Silo thinking |
| **Humility** | "I've seen this fail, here's what I learned" | Overconfident, dismissive |

---

## Recommended Further Depth

- **JVM:** *Java Performance* — Scott Oaks
- **Concurrency:** *Java Concurrency in Practice* — Goetz et al.
- **Design Patterns:** *Designing Data-Intensive Applications* — Kleppmann
- **Microservices:** *Building Microservices* — Sam Newman
- **Spring:** Official Spring documentation + Baeldung deep dives

---

*Last updated: 2025 · Java 17/21 · Spring Boot 3.x · Kubernetes-aware patterns*

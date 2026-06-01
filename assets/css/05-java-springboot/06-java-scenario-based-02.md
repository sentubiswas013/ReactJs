# Java Full Developer / Architecture Engineer
## Scenario-Based Interview Questions — Senior & Lead Level

> **55 questions** covering Java, Spring Boot, JVM, Microservices, Architecture, and Databases.
> Each question includes production context, root-cause analysis, code examples, and follow-up prompts.

---

## 📋 Table of Contents

### Section 1 — System Design, Spring & Backend (Q1–Q17)

| # | Question | Topic |
|---|----------|-------|
| Q1 | [High-Traffic Spike Scenario](#q1-high-traffic-spike-scenario) | System Design |
| Q2 | [Database Scale Scenario](#q2-database-scale-scenario) | System Design |
| Q3 | [Microservices Communication Failure](#q3-microservices-communication-failure) | System Design |
| Q4 | [Distributed Transaction Problem](#q4-distributed-transaction-problem) | System Design |
| Q5 | [Memory Leak in Production](#q5-memory-leak-in-production) | System Design |
| Q6 | [@Transactional Not Working](#q6-transactional-not-working) | Spring |
| Q7 | [Circular Dependency in Spring](#q7-circular-dependency-in-spring) | Spring |
| Q8 | [N+1 Query Problem](#q8-n1-query-problem) | Spring |
| Q9 | [API Response Time Too Slow](#q9-api-response-time-too-slow) | Spring |
| Q10 | [SQL Injection Attack](#q10-sql-injection-attack) | Security |
| Q11 | [XSS Attack Prevention](#q11-xss-attack-prevention) | Security |
| Q12 | [API Security Breach](#q12-api-security-breach) | Security |
| Q13 | [Production Deployment Failure](#q13-production-deployment-failure) | DevOps |
| Q14 | [Database Migration in Production](#q14-database-migration-in-production) | DevOps |
| Q15 | [Container Orchestration Failure](#q15-container-orchestration-failure) | DevOps |
| Q16 | [Flaky Tests in CI/CD](#q16-flaky-tests-in-cicd) | Testing |
| Q17 | [Performance Testing](#q17-performance-testing) | Testing |

### Section 2 — Core Java Scenarios (Q18–Q35)

| # | Question | Topic |
|---|----------|-------|
| Q18 | [Memory Leak Scenario (HashMap)](#q18-memory-leak-scenario-hashmap) | Core Java |
| Q19 | [ConcurrentModificationException](#q19-concurrentmodificationexception-scenario) | Core Java |
| Q20 | [NullPointerException in Streams](#q20-nullpointerexception-in-java-8-streams) | Core Java |
| Q21 | [Integer Caching Surprise](#q21-integer-caching-surprise) | Core Java |
| Q22 | [HashMap in Multithreaded Environment](#q22-hashmap-in-multithreaded-environment) | Core Java |
| Q23 | [String Memory Leak](#q23-string-memory-leak) | Core Java |
| Q24 | [CompletableFuture Performance Issue](#q24-completablefuture-performance-issue) | Advanced Java |
| Q25 | [ThreadLocal Memory Leak](#q25-threadlocal-memory-leak) | Advanced Java |
| Q26 | [HashMap Key Being Modified](#q26-hashmap-key-being-modified) | Advanced Java |
| Q27 | [Garbage Collection Pause Time](#q27-garbage-collection-pause-time) | Advanced Java |
| Q28 | [Deadlock Detection](#q28-deadlock-detection) | Advanced Java |
| Q29 | [Java Stream Parallel Performance](#q29-java-stream-parallel-performance) | Advanced Java |
| Q30 | [Singleton in Multithreaded Environment](#q30-singleton-in-multithreaded-environment) | Design Patterns |
| Q31 | [Exception Handling in Transaction](#q31-exception-handling-in-transaction) | Spring Deep-Dive |
| Q32 | [Circular Dependency in Constructor Injection](#q32-circular-dependency-in-constructor-injection) | Spring Deep-Dive |
| Q33 | [Java 8 Optional Best Practices](#q33-java-8-optional-best-practices) | Java 8+ |
| Q34 | [ClassLoader Issue in Web Application](#q34-classloader-issue-in-web-application) | JVM |
| Q35 | [Producer-Consumer Pattern](#q35-producer-consumer-pattern-implementation) | Concurrency |

### Section 3 — Senior / Architect Level Scenarios (Q36–Q55)

| # | Question | Topic |
|---|----------|-------|
| Q36 | [Memory Leak in Long-Running Service](#q36-memory-leak-in-a-long-running-java-service) | Core Java |
| Q37 | [Java Memory Model & Concurrent Code](#q37-java-memory-model--concurrent-code) | Core Java |
| Q38 | [CompletableFuture Pipeline with Error Handling](#q38-completablefuture-pipeline-with-error-handling) | Core Java |
| Q39 | [G1GC vs ZGC — When to Choose](#q39-g1gc-vs-zgc--when-to-choose-which) | JVM / GC |
| Q40 | [Custom ClassLoader for Plugin Isolation](#q40-custom-classloader-for-plugin-isolation) | JVM |
| Q41 | [Rate-Limiting System for API Gateway](#q41-rate-limiting-system-for-an-api-gateway) | Architecture |
| Q42 | [Monolith to Microservices Migration](#q42-monolith-to-microservices-migration-without-downtime) | Architecture |
| Q43 | [Event-Driven Notification System at Scale](#q43-event-driven-notification-system-at-scale) | Architecture |
| Q44 | [Idempotent REST API for Payment Processing](#q44-idempotent-rest-api-for-payment-processing) | Architecture |
| Q45 | [Blue-Green and Canary Deployments in K8s](#q45-blue-green-and-canary-deployments-in-kubernetes) | Architecture |
| Q46 | [Spring Boot Endpoint Optimization (800ms→100ms)](#q46-spring-boot-endpoint-optimization-800ms--100ms) | Performance |
| Q47 | [High CPU Usage During Idle Periods](#q47-high-cpu-usage-during-idle-periods) | Performance |
| Q48 | [Reactive Programming with Spring WebFlux](#q48-reactive-programming-with-spring-webflux) | Performance |
| Q49 | [Saga Pattern for Distributed Transaction](#q49-saga-pattern-for-distributed-order-transaction) | Microservices |
| Q50 | [Circuit Breaker with Fallback Behavior](#q50-circuit-breaker-with-fallback-behavior) | Microservices |
| Q51 | [Distributed Tracing Across Microservices](#q51-distributed-tracing-across-microservices) | Microservices |
| Q52 | [CQRS + Event Sourcing for Financial Ledger](#q52-cqrs--event-sourcing-for-financial-ledger) | Microservices |
| Q53 | [JPA Queries Causing Database Deadlocks](#q53-jpa-queries-causing-database-deadlocks) | DB / JPA |
| Q54 | [Multi-Tenant Data Isolation in SaaS](#q54-multi-tenant-data-isolation-in-saas) | DB / JPA |
| Q55 | [Database Sharding Strategy for 10 Billion Rows](#q55-database-sharding-strategy-for-10-billion-rows) | DB / JPA |

---

## Section 1 — System Design, Spring & Backend

### 🏗️ System Design & Architecture

---

#### Q1. High-Traffic Spike Scenario

> **Difficulty:** Medium · **Topic:** System Design · **Role:** Full Stack / Architect

**Scenario:** Your e-commerce platform is running smoothly at 10K requests/second. Black Friday hits and traffic jumps to 1M req/sec. The site goes down. How would you have designed for this, and how do you recover?

**Root Causes to Explore:**
- No horizontal scaling strategy
- Single-instance bottlenecks (DB, app server)
- No caching or CDN for static content
- Synchronous processing of all requests

**Expected Answer:**

| Layer | Strategy |
|-------|----------|
| **App Tier** | Horizontal scaling via Kubernetes HPA; stateless pods |
| **Load Balancing** | NGINX / AWS ALB with health checks |
| **Caching** | Redis for hot data; CDN (CloudFront) for static assets |
| **Resilience** | Resilience4j rate limiting + circuit breakers |
| **Database** | HikariCP connection pooling + read replicas |
| **Async Work** | Kafka / RabbitMQ for non-critical processing (emails, analytics) |

**Key Principle:** Design for _peak × 1.5_ capacity; auto-scale before you need it using predictive rules, not just reactive CPU thresholds.

> 💬 **Follow-up:** How would you do load testing in CI/CD to validate your scaling policies before Black Friday?

[↑ Back to top](#-table-of-contents)

---

#### Q2. Database Scale Scenario

> **Difficulty:** Medium · **Topic:** System Design · **Role:** Architect

**Scenario:** A `user_events` table has grown to 1 billion rows. Simple queries are taking 10+ seconds. Engineers are adding indexes but things keep getting worse. What's your systematic approach?

**Diagnosis Checklist:**
- Run `EXPLAIN ANALYZE` — is it doing sequential scans?
- Check index usage: are queries using existing indexes?
- Check data types: are you comparing `VARCHAR` to `INT`?
- Check lock contention: are writes blocking reads?

**Expected Answer:**

```sql
-- Step 1: Identify slow queries
EXPLAIN ANALYZE SELECT * FROM user_events WHERE user_id = 123 ORDER BY created_at DESC LIMIT 10;

-- Step 2: Add composite index (column order matters — most selective first)
CREATE INDEX CONCURRENTLY idx_user_events_user_time 
  ON user_events(user_id, created_at DESC);

-- Step 3: Partition the table by time range
CREATE TABLE user_events_2024 PARTITION OF user_events
  FOR VALUES FROM ('2024-01-01') TO ('2025-01-01');
```

**Scaling Ladder:**

1. ✅ Proper indexing (composite, covering indexes)
2. ✅ Query optimization (`EXPLAIN ANALYZE`, avoid `SELECT *`)
3. ✅ Table partitioning (range by date, hash by user_id)
4. ✅ Read replicas for read-heavy endpoints
5. ✅ Caching layer (Redis) for frequently accessed rows
6. ✅ Sharding / NoSQL if relational model is genuinely the bottleneck

> 💬 **Follow-up:** When does adding more indexes hurt performance? Explain the write amplification trade-off.

[↑ Back to top](#-table-of-contents)

---

#### Q3. Microservices Communication Failure

> **Difficulty:** Medium · **Topic:** System Design · **Role:** Full Stack / Architect

**Scenario:** Service A calls Service B synchronously. Service B goes down. Within 30 seconds, Service A's thread pool is exhausted and it starts failing too — cascading the outage to every service that depends on A.

**The Problem:** Tight coupling + no isolation = cascade failure.

**Expected Answer — Defense in Depth:**

```java
// Resilience4j: Circuit Breaker + Timeout + Retry combined
@CircuitBreaker(name = "serviceB", fallbackMethod = "fallback")
@TimeLimiter(name = "serviceB")       // fail fast after 2s
@Retry(name = "serviceB")             // retry 3x with backoff
public CompletableFuture<Response> callServiceB(Request req) {
    return CompletableFuture.supplyAsync(() -> client.call(req));
}

public CompletableFuture<Response> fallback(Request req, Exception e) {
    // Return cached data or a safe default — never propagate failure upward
    return CompletableFuture.completedFuture(Response.degraded());
}
```

**Pattern Summary:**

| Pattern | Purpose |
|---------|---------|
| **Circuit Breaker** | Stop calling a failing service; fail fast |
| **Timeout** | Don't wait forever — release threads quickly |
| **Retry + Backoff** | Handle transient failures gracefully |
| **Bulkhead** | Isolate thread pools so one service can't exhaust all threads |
| **Fallback** | Return cached data or default response to the caller |

> 💬 **Follow-up:** When does a retry mechanism make a cascade failure _worse_ instead of better?

[↑ Back to top](#-table-of-contents)

---

#### Q4. Distributed Transaction Problem

> **Difficulty:** Hard · **Topic:** System Design · **Role:** Architect

**Scenario:** A money transfer debits $100 from Account A in Service A, then credits $100 to Account B in Service B. Service B crashes after the debit succeeds but before the credit. How do you handle this?

**Why 2PC Fails at Scale:** Two-Phase Commit blocks all participants during the prepare phase. One slow participant blocks the entire transaction. Availability drops significantly across services.

**Expected Answer — Saga Pattern:**

```
ORCHESTRATION SAGA — Order of Events:

1. TransferInitiated  →  debit Account A  →  emit MoneyDebited
2. MoneyDebited       →  credit Account B →  emit MoneyCredited
3. [FAILURE PATH]     →  if credit fails  →  emit CreditFailed
4. CreditFailed       →  refund Account A →  emit MoneyRefunded (compensating tx)
```

**Guarantees You Need:**
- Each step has an **idempotent compensating transaction**
- Use **Outbox Pattern** to guarantee event publishing is atomic with the DB write
- Store saga state durably (database) so it survives crashes
- Set timeouts: trigger compensation if a step hangs for N seconds

> 💬 **Follow-up:** What is "eventual consistency" and how do you explain to a product manager that the account balance might be briefly incorrect?

[↑ Back to top](#-table-of-contents)

---

#### Q5. Memory Leak in Production

> **Difficulty:** Hard · **Topic:** System Design · **Role:** Full Stack / Architect

**Scenario:** Your Java service has been running 3 days. Heap usage is climbing from 512MB → 900MB → OOM. GC is running more frequently but reclaiming less each time. How do you diagnose and fix this?

**Diagnostic Playbook:**

```bash
# Step 1: Enable heap dump on OOM (add to JVM args)
-XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/tmp/heapdump.hprof

# Step 2: Enable GC logging to confirm leak vs. sizing issue
-Xlog:gc*:file=/tmp/gc.log:time,uptime

# Step 3: If service is still running, take a live heap dump
jmap -dump:format=b,file=/tmp/live.hprof <pid>

# Step 4: Analyze in Eclipse MAT
# → Run "Leak Suspects Report"
# → Check "Dominator Tree" for largest object graphs
```

**Common Culprits:**

| Root Cause | Fix |
|------------|-----|
| Static `HashMap` acting as unbounded cache | Use Caffeine with `maximumSize()` and TTL |
| `ThreadLocal` never removed in thread pool | Always call `threadLocal.remove()` in `finally` |
| Unclosed `InputStream` / DB connections | Use `try-with-resources` |
| Listeners/callbacks registered but never removed | Use `WeakReference` or explicit deregistration |

> 💬 **Follow-up:** How do you distinguish a genuine memory leak from simply under-provisioned heap? What GC metrics in Prometheus help you tell the difference?

[↑ Back to top](#-table-of-contents)

---

### ☕ Spring & Backend

---

#### Q6. @Transactional Not Working

> **Difficulty:** Medium · **Topic:** Spring · **Role:** Full Stack

**Scenario:** You annotated a method with `@Transactional` but database changes are not being rolled back on exception. The annotation appears correct. What are the possible causes?

**Most Common Causes (in order of frequency):**

| Cause | Explanation |
|-------|-------------|
| **Self-invocation** | `this.myMethod()` bypasses the Spring proxy — no transaction created |
| **Private / final method** | Spring's proxy-based AOP cannot intercept these |
| **Exception is caught** | If you swallow the exception, Spring never sees it |
| **Checked exception** | Spring only rolls back on `RuntimeException` by default |
| **Wrong transaction propagation** | Method called from a non-transactional context with `REQUIRED` |

**Fixes:**

```java
// ❌ Problem 1: Self-invocation — proxy bypassed
@Service
public class OrderService {
    public void placeOrder() {
        processPayment(); // calls this.processPayment() — NO transaction!
    }
    @Transactional
    public void processPayment() { ... }
}

// ✅ Fix 1: Inject self (Spring proxy is used instead)
@Autowired
private OrderService self;
self.processPayment();

// ❌ Problem 2: Checked exception not rolled back
@Transactional
public void save() throws IOException {
    repo.save(entity); // IOException won't trigger rollback by default
}

// ✅ Fix 2: Specify rollback for checked exceptions
@Transactional(rollbackFor = Exception.class)
public void save() throws IOException { ... }

// ✅ Fix 3: Manual rollback when you must catch
@Transactional
public void save() {
    try { repo.save(entity); }
    catch (Exception e) {
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        throw e;
    }
}
```

> 💬 **Follow-up:** What is the difference between `REQUIRES_NEW` and `NESTED` transaction propagation? When would you use each?

[↑ Back to top](#-table-of-contents)

---

#### Q7. Circular Dependency in Spring

> **Difficulty:** Medium · **Topic:** Spring · **Role:** Full Stack

**Scenario:** Service A injects Service B via constructor. Service B injects Service A via constructor. Application fails to start with `BeanCurrentlyInCreationException`. How do you resolve this?

**⚠️ Warning:** Circular dependencies are usually a design smell. Fix the root cause first.

**Resolution Options (best to worst):**

```java
// ✅ Best: Refactor — extract shared logic into Service C
// If A and B both need X, X should be its own service

// ✅ Good: Use @Lazy on one side (delays instantiation)
@Service
public class ServiceA {
    public ServiceA(@Lazy ServiceB serviceB) {
        this.serviceB = serviceB;
    }
}

// ✅ Good: Event-driven decoupling (A publishes event, B listens)
// A and B never directly reference each other

// ⚠️ Acceptable: Setter injection for one dependency
// (Spring resolves by creating A first, then sets B via setter)
@Autowired
public void setServiceB(ServiceB serviceB) { ... }
```

> 💬 **Follow-up:** Spring Boot 2.6+ disallows circular dependencies by default. How do you enable it if absolutely necessary, and why should you think twice before doing so?

[↑ Back to top](#-table-of-contents)

---

#### Q8. N+1 Query Problem

> **Difficulty:** Medium · **Topic:** Spring / JPA · **Role:** Full Stack

**Scenario:** You load 100 `Order` entities. Each order has a `Customer`. You see 101 SQL queries in the logs — one for orders, then one per customer. How do you detect and fix this?

**Detection:**

```yaml
# application.yml — enable Hibernate statistics
spring.jpa.properties.hibernate.generate_statistics: true
logging.level.org.hibernate.stat: DEBUG
# Logs: "HHH000117: HQL queries executed to database: 101"
```

**Fixes:**

```java
// ❌ Problem: Lazy loading triggers N queries
List<Order> orders = orderRepo.findAll();
orders.forEach(o -> System.out.println(o.getCustomer().getName())); // N+1

// ✅ Fix 1: JOIN FETCH in JPQL
@Query("SELECT o FROM Order o JOIN FETCH o.customer WHERE o.status = :status")
List<Order> findWithCustomer(@Param("status") String status);

// ✅ Fix 2: EntityGraph (avoids modifying query)
@EntityGraph(attributePaths = {"customer", "items"})
List<Order> findByStatus(String status);

// ✅ Fix 3: DTO Projection — only fetch what you need
@Query("SELECT new com.app.dto.OrderSummary(o.id, c.name) FROM Order o JOIN o.customer c")
List<OrderSummary> findOrderSummaries();

// ✅ Fix 4: @BatchSize for collections
@OneToMany
@BatchSize(size = 50) // loads related entities in batches of 50
private List<OrderItem> items;
```

> 💬 **Follow-up:** What is the "Open Session in View" anti-pattern? Why does it hide N+1 problems in development?

[↑ Back to top](#-table-of-contents)

---

#### Q9. API Response Time Too Slow

> **Difficulty:** Medium · **Topic:** Spring · **Role:** Full Stack

**Scenario:** `GET /api/dashboard` takes 5 seconds under moderate load. Users are complaining. Where do you start?

**Profile Before Optimizing — Never Guess:**

```java
// Add Spring Boot Actuator + Micrometer timing
@Timed(value = "dashboard.latency", percentiles = {0.5, 0.95, 0.99})
@GetMapping("/api/dashboard")
public DashboardResponse getDashboard() { ... }
```

**Optimization Ladder:**

| Step | Technique | Typical Gain |
|------|-----------|-------------|
| 1 | Add missing DB indexes | 10–100× |
| 2 | Fix N+1 queries (JOIN FETCH) | 5–50× |
| 3 | Parallelize independent calls (CompletableFuture) | 2–5× |
| 4 | Add Redis caching (`@Cacheable`) | 5–100× on cache hits |
| 5 | Add pagination (don't return 10K rows) | 10–100× |
| 6 | Enable GZIP compression | 2–3× on payload size |
| 7 | Tune HikariCP pool size | 1.5–3× under concurrent load |

> 💬 **Follow-up:** How do you handle cache invalidation when the underlying data changes across multiple microservices? Describe an event-driven cache invalidation approach.

[↑ Back to top](#-table-of-contents)

---

### 🔐 Security

---

#### Q10. SQL Injection Attack

> **Difficulty:** Medium · **Topic:** Security · **Role:** Full Stack

**Scenario:** A penetration test reveals that `/search?query=` is vulnerable to SQL injection. The legacy code uses string concatenation to build queries. How do you fix it?

**The Vulnerable Code:**

```java
// ❌ CRITICAL VULNERABILITY — never do this
String sql = "SELECT * FROM users WHERE name = '" + userInput + "'";
// Input: ' OR '1'='1 → returns all users
// Input: '; DROP TABLE users; -- → destroys data
```

**Fixes (in order of preference):**

```java
// ✅ Fix 1: Use Spring Data JPA / Hibernate (best)
List<User> findByName(String name); // parameter binding is automatic

// ✅ Fix 2: PreparedStatement with parameterized query
PreparedStatement ps = conn.prepareStatement(
    "SELECT * FROM users WHERE name = ?"
);
ps.setString(1, userInput); // input is never interpreted as SQL

// ✅ Fix 3: JPA named parameter (if native query is unavoidable)
@Query(value = "SELECT * FROM users WHERE name = :name", nativeQuery = true)
List<User> findByNameNative(@Param("name") String name);
```

**Defense in Depth:**
- Input validation (whitelist expected characters)
- Database user with least privilege (no DROP, no DDL)
- WAF (Web Application Firewall) as last line of defense
- SQL injection scanning in CI/CD (OWASP Dependency Check)

> 💬 **Follow-up:** What is a second-order SQL injection and why is input sanitization alone insufficient to prevent it?

[↑ Back to top](#-table-of-contents)

---

#### Q11. XSS Attack Prevention

> **Difficulty:** Medium · **Topic:** Security · **Role:** Full Stack

**Scenario:** A user can submit comments. Another user reports that visiting a page with a certain comment runs JavaScript in their browser and steals their session cookie.

**Defense Strategy:**

| Layer | Control |
|-------|---------|
| **Output encoding** | Escape all user-generated content before rendering in HTML |
| **CSP header** | `Content-Security-Policy: default-src 'self'` blocks inline scripts |
| **HttpOnly cookies** | `Set-Cookie: session=...; HttpOnly` — JS cannot read the cookie |
| **Input validation** | Reject or sanitize HTML on the backend |
| **React** | Auto-escapes by default — never use `dangerouslySetInnerHTML` |

```java
// Spring Security — add CSP header globally
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) {
    http.headers(headers -> headers
        .contentSecurityPolicy(csp -> csp
            .policyDirectives("default-src 'self'; script-src 'self'")
        )
    );
    return http.build();
}
```

> 💬 **Follow-up:** What is a DOM-based XSS and how does it differ from stored/reflected XSS? Which is harder to detect with automated scanners?

[↑ Back to top](#-table-of-contents)

---

#### Q12. API Security Breach

> **Difficulty:** Medium · **Topic:** Security · **Role:** Architect

**Scenario:** Your monitoring shows one client IP is making 50,000 API calls per hour — far above normal usage patterns. Some calls are accessing endpoints that should require elevated permissions.

**Expected Answer — Layered Defense:**

```java
// Rate limiting at API Gateway level (Spring Cloud Gateway)
@Bean
public RouteLocator routes(RouteLocatorBuilder builder, RateLimiter limiter) {
    return builder.routes()
        .route("secured", r -> r.path("/api/**")
            .filters(f -> f.requestRateLimiter(c -> c
                .setRateLimiter(limiter)         // Redis-backed
                .setKeyResolver(clientIdResolver) // per client, not global
            ))
            .uri("lb://backend-service"))
        .build();
}
```

**Security Controls:**

| Control | Purpose |
|---------|---------|
| **OAuth2 + RBAC** | Scoped tokens — clients only access what they're authorized for |
| **Rate Limiting** | Token bucket per client ID (not just per IP) |
| **Request signing + timestamp** | Prevents replay attacks (reject requests > 5 minutes old) |
| **Audit logging** | Log all requests with client ID, endpoint, timestamp to SIEM |
| **IP allowlisting** | For admin/sensitive endpoints |
| **Anomaly detection** | Alert on sudden usage spikes per client |

> 💬 **Follow-up:** What is SSRF (Server-Side Request Forgery)? How would you prevent your API from being used as a proxy to access internal services?

[↑ Back to top](#-table-of-contents)

---

### 🚀 DevOps & Production

---

#### Q13. Production Deployment Failure

> **Difficulty:** Medium · **Topic:** DevOps · **Role:** Full Stack

**Scenario:** You deployed v2.1.0 at 14:00. By 14:05, error rates jump from 0.1% to 12%. Customers are calling support. What do you do in the next 10 minutes?

**Incident Response — First 10 Minutes:**

```
T+0m  → Declare incident; notify on-call engineer
T+1m  → Check error rate, latency, and saturation in Grafana
T+2m  → Identify: is this the new deployment? Compare error timing vs deploy time
T+3m  → ROLLBACK — don't investigate first, restore service first
T+4m  → kubectl rollout undo deployment/my-service
T+5m  → Verify error rate dropping; confirm rollback succeeded
T+8m  → Update incident channel with status
T+10m → Begin post-mortem data collection (don't fix in panic)
```

**Rollback Command:**

```bash
# Kubernetes — instant rollback to previous ReplicaSet
kubectl rollout undo deployment/order-service

# Verify status
kubectl rollout status deployment/order-service

# If you need to go back 2 versions
kubectl rollout undo deployment/order-service --to-revision=3
```

**Preventing Recurrence:**
- Canary deployments: route 5% of traffic to new version first
- Feature flags: deploy code dark, enable feature separately
- Automated rollback: Argo Rollouts with Prometheus error-rate analysis

> 💬 **Follow-up:** How do feature flags differ from canary deployments? When would you use one vs. the other?

[↑ Back to top](#-table-of-contents)

---

#### Q14. Database Migration in Production

> **Difficulty:** Hard · **Topic:** DevOps · **Role:** Architect

**Scenario:** You need to add a `phone_number` column to a `users` table with 1 billion rows. A simple `ALTER TABLE` will lock the table for 2+ hours. How do you do this with zero downtime?

**Expand-Contract Pattern (Zero Downtime):**

```
Phase 1 — EXPAND (add column, don't require it):
  ALTER TABLE users ADD COLUMN phone_number VARCHAR(20);  -- fast, no lock
  Deploy code that writes to BOTH email and phone_number

Phase 2 — BACKFILL (populate existing rows in small batches):
  UPDATE users SET phone_number = '' WHERE id BETWEEN 1 AND 100000;
  -- Repeat in batches; never lock the whole table
  -- Use tools like pt-online-schema-change or pg_repack

Phase 3 — CUTOVER (read from new column):
  Deploy code that reads from phone_number
  Verify correctness in shadow mode first (compare old vs new)

Phase 4 — CONTRACT (clean up old column if applicable):
  Remove old logic; add NOT NULL constraint if needed
  -- Only after 100% of writes use new column
```

```java
// Flyway migration — always backward compatible
@Bean
public FlywayMigrationStrategy migrationStrategy() {
    return flyway -> {
        flyway.repair(); // fix checksums
        flyway.migrate();
    };
}
```

> 💬 **Follow-up:** What is `pt-online-schema-change` and how does it avoid table locks? What are its limitations?

[↑ Back to top](#-table-of-contents)

---

#### Q15. Container Orchestration Failure

> **Difficulty:** Medium · **Topic:** DevOps · **Role:** Full Stack

**Scenario:** Your Kubernetes pods are entering `CrashLoopBackOff`. The service was fine yesterday. How do you debug systematically?

**Debugging Playbook:**

```bash
# Step 1: Check pod status and recent events
kubectl get pods -n production
kubectl describe pod order-service-6d9f8b-xxx -n production
# Look for: OOMKilled, ImagePullBackOff, Liveness probe failed

# Step 2: Check logs (current and previous crash)
kubectl logs order-service-6d9f8b-xxx --previous
kubectl logs order-service-6d9f8b-xxx --tail=100

# Step 3: Check resource consumption
kubectl top pods -n production

# Step 4: Debug interactively
kubectl exec -it order-service-6d9f8b-xxx -- /bin/sh

# Step 5: Check ConfigMaps / Secrets are mounted correctly
kubectl get configmap order-config -o yaml
```

**Common Causes:**

| Symptom | Likely Cause |
|---------|-------------|
| `OOMKilled` | Memory limit too low; memory leak in app |
| `ImagePullBackOff` | Image tag doesn't exist; registry auth failed |
| `Liveness probe failed` | App taking too long to start; probe too aggressive |
| `CrashLoopBackOff` | App crashes on startup (check logs for exception) |
| `Pending` | Insufficient cluster resources; PVC not bound |

> 💬 **Follow-up:** What is the difference between a liveness probe and a readiness probe? What happens if you configure them incorrectly?

[↑ Back to top](#-table-of-contents)

---

### 🧪 Testing

---

#### Q16. Flaky Tests in CI/CD

> **Difficulty:** Medium · **Topic:** Testing · **Role:** Full Stack

**Scenario:** 10% of your integration tests fail intermittently in CI but always pass locally. Developers are re-running pipelines rather than fixing the root cause. What is your approach?

**Root Causes and Fixes:**

| Root Cause | Fix |
|------------|-----|
| **Shared test database state** | Use `@Transactional` (rollback after each test) or `@DirtiesContext` |
| **Race conditions in async code** | `Awaitility.await().until(...)` instead of `Thread.sleep()` |
| **External service dependency** | Mock with WireMock; use Testcontainers for real DBs |
| **Time-dependent logic** | Inject `Clock` / `Instant` as a dependency; mock it in tests |
| **Port conflicts** | Use `@SpringBootTest(webEnvironment = RANDOM_PORT)` |

```java
// ✅ Use Testcontainers for real DB tests
@Testcontainers
@SpringBootTest
class OrderRepositoryTest {
    @Container
    static PostgreSQLContainer<?> postgres =
        new PostgreSQLContainer<>("postgres:16-alpine");

    @DynamicPropertySource
    static void configure(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
    }
}

// ✅ Awaitility for async assertions
Awaitility.await()
    .atMost(5, TimeUnit.SECONDS)
    .until(() -> orderRepo.findById(id).isPresent());
```

> 💬 **Follow-up:** What is test quarantining and how do you decide when to delete vs fix a flaky test?

[↑ Back to top](#-table-of-contents)

---

#### Q17. Performance Testing

> **Difficulty:** Medium · **Topic:** Testing · **Role:** Full Stack / Architect

**Scenario:** You're launching a new feature that you expect will receive 10× current traffic. How do you validate the system can handle it before going live?

**Testing Types:**

| Test Type | Purpose | Tool |
|-----------|---------|------|
| **Load Test** | Verify behavior at expected load | JMeter, Gatling, k6 |
| **Stress Test** | Find breaking point (when does it fail?) | k6, Locust |
| **Spike Test** | Simulate sudden traffic bursts | Gatling |
| **Soak Test** | Detect memory leaks over time (run 8–24 hours) | JMeter |
| **Endurance Test** | Validate stability under sustained load | Gatling |

```javascript
// k6 — define load profile as code (version-controlled)
export const options = {
  stages: [
    { duration: '2m', target: 1000 },   // ramp up
    { duration: '5m', target: 1000 },   // sustain
    { duration: '2m', target: 5000 },   // spike
    { duration: '2m', target: 0 },      // ramp down
  ],
  thresholds: {
    http_req_duration: ['p(99)<200'],    // 99th percentile < 200ms
    http_req_failed: ['rate<0.001'],     // error rate < 0.1%
  }
};
```

> 💬 **Follow-up:** How do you make performance testing representative of real production load? What are the pitfalls of testing with artificial, uniform traffic patterns?

[↑ Back to top](#-table-of-contents)

---

## Section 2 — Core Java Scenarios

### 🔥 Core Java Fundamentals

---

#### Q18. Memory Leak Scenario (HashMap)

> **Difficulty:** Medium · **Topic:** Core Java · **Role:** Full Stack

**Scenario:** Your application runs fine for hours, then gradually slows down and eventually hits OOM. Heap dumps show thousands of `HashMap` instances with entries that never disappear. What went wrong?

**Root Cause Analysis:**

```java
// ❌ Classic leak: unbounded static cache with no eviction
public class UserCache {
    private static final Map<Long, UserProfile> cache = new HashMap<>();
    
    public static void put(Long id, UserProfile profile) {
        cache.put(id, profile); // grows forever — never evicted
    }
}
```

**Fixes:**

```java
// ✅ Fix 1: Caffeine cache with size limit and TTL
private static final Cache<Long, UserProfile> cache = Caffeine.newBuilder()
    .maximumSize(10_000)
    .expireAfterWrite(30, TimeUnit.MINUTES)
    .recordStats()
    .build();

// ✅ Fix 2: LinkedHashMap with LRU eviction
private static final Map<Long, UserProfile> cache =
    new LinkedHashMap<>(1000, 0.75f, true) { // true = access-order
        @Override
        protected boolean removeEldestEntry(Map.Entry<Long, UserProfile> eldest) {
            return size() > 10_000; // evict when over capacity
        }
    };

// ✅ Fix 3: WeakHashMap (GC can collect keys when no strong ref exists)
private static final Map<Long, UserProfile> cache = new WeakHashMap<>();
// ⚠️ Warning: entries can disappear at any time — only for soft caches
```

> 💬 **Follow-up:** What is the difference between a strong reference, soft reference, weak reference, and phantom reference in Java? When would you use each?

[↑ Back to top](#-table-of-contents)

---

#### Q19. ConcurrentModificationException Scenario

> **Difficulty:** Easy · **Topic:** Core Java · **Role:** Full Stack

**Scenario:** You're filtering elements from a list during iteration. Your code throws `ConcurrentModificationException` even though there is only one thread. Why?

**Root Cause:** `ArrayList`'s iterator tracks a `modCount`. Any structural modification (add/remove) during iteration increments `modCount`, causing the iterator to throw.

```java
// ❌ Wrong — throws ConcurrentModificationException
for (String item : list) {
    if (shouldRemove(item)) {
        list.remove(item); // modifies list while iterator is active
    }
}

// ✅ Fix 1: Iterator.remove() — the only safe in-loop removal
Iterator<String> it = list.iterator();
while (it.hasNext()) {
    if (shouldRemove(it.next())) {
        it.remove(); // uses iterator's own removal — safe
    }
}

// ✅ Fix 2: removeIf (Java 8+) — cleanest approach
list.removeIf(item -> shouldRemove(item));

// ✅ Fix 3: Collect and remove separately
List<String> toRemove = list.stream()
    .filter(item -> shouldRemove(item))
    .collect(Collectors.toList());
list.removeAll(toRemove);

// ✅ Fix 4: CopyOnWriteArrayList — for concurrent multi-thread scenarios
// (never throws CME, but creates a copy on every write — expensive for large lists)
```

> 💬 **Follow-up:** `CopyOnWriteArrayList` never throws `ConcurrentModificationException`. What is the performance trade-off, and for what use cases is it appropriate?

[↑ Back to top](#-table-of-contents)

---

#### Q20. NullPointerException in Java 8 Streams

> **Difficulty:** Easy · **Topic:** Core Java · **Role:** Full Stack

**Scenario:** A stream pipeline that has been running fine in testing suddenly throws `NullPointerException` in production. The data comes from an external API that occasionally returns `null` for optional fields.

```java
// ❌ Multiple NPE risks
List<String> names = getUsersFromApi(); // might return null
names.stream()
     .filter(name -> name.startsWith("A")) // NPE if name is null
     .collect(Collectors.toList());

// ✅ Fix 1: Defensive null check at every level
Optional.ofNullable(getUsersFromApi())
    .orElse(Collections.emptyList())
    .stream()
    .filter(Objects::nonNull)               // filter out null elements
    .filter(name -> name.startsWith("A"))
    .collect(Collectors.toList());

// ✅ Fix 2: Return empty collection from service methods (never return null)
public List<String> getUserNames() {
    List<String> result = fetchFromApi();
    return result != null ? result : Collections.emptyList();
}

// ✅ Fix 3: Stream.ofNullable (Java 9+) for single nullable values
Stream.ofNullable(getSingleUser())
      .map(User::getName)
      .findFirst();
```

**Rule of Thumb:** Service methods should never return `null` for collections — return `Collections.emptyList()` or `Optional` instead.

> 💬 **Follow-up:** What is the difference between `Optional.of()`, `Optional.ofNullable()`, and `Optional.empty()`? What pitfall can `Optional.of(null)` cause?

[↑ Back to top](#-table-of-contents)

---

#### Q21. Integer Caching Surprise

> **Difficulty:** Easy · **Topic:** Core Java · **Role:** Full Stack

**Scenario:** Your code compares two `Integer` values with `==` and gets inconsistent results — sometimes `true`, sometimes `false` — depending on the value.

```java
Integer a = 127;
Integer b = 127;
System.out.println(a == b);   // → true  (cached object reused)

Integer c = 128;
Integer d = 128;
System.out.println(c == d);   // → false (new objects created)
System.out.println(c.equals(d)); // → true  (always use equals!)
```

**Why This Happens:** Java caches `Integer` objects for values `-128` to `127` (JVM specification). Autoboxing reuses these cached instances. Outside this range, `new Integer(128)` creates distinct objects.

**The Fix:**

```java
// ✅ Always use equals() for Integer, Long, Double comparisons
if (a.equals(b)) { ... }

// ✅ Or unbox to primitives for arithmetic comparison
int x = a;
int y = b;
if (x == y) { ... }
```

**The Broader Lesson:** Never use `==` to compare boxed types (`Integer`, `Long`, `Double`, `Boolean`). Use `.equals()` or compare the underlying primitives.

> 💬 **Follow-up:** What is autoboxing and unboxing? What is the performance implication of excessive autoboxing in a tight loop?

[↑ Back to top](#-table-of-contents)

---

#### Q22. HashMap in Multithreaded Environment

> **Difficulty:** Medium · **Topic:** Core Java · **Role:** Full Stack

**Scenario:** Your application uses a `HashMap` as a shared cache accessed by multiple threads. Under load, you see occasional `StackOverflowError` or the application hangs with 100% CPU on one thread.

**Root Cause:** In Java 7, concurrent `HashMap.put()` operations during resize can create a circular linked list, causing `get()` to loop infinitely. Java 8+ changed to a tree structure to prevent infinite loops, but data loss and race conditions still occur.

**Solutions:**

| Option | Thread Safety | Performance | When to Use |
|--------|--------------|------------|-------------|
| `ConcurrentHashMap` | ✅ Full | ✅ High | General use — best default |
| `Collections.synchronizedMap()` | ✅ Full | ⚠️ Low | Legacy code; compound operations need external sync |
| `Hashtable` | ✅ Full | ❌ Poor | Don't use (legacy) |
| Read-write lock + HashMap | ✅ Full | ✅ High | Read-heavy workloads |

```java
// ✅ ConcurrentHashMap — best general choice
Map<String, User> cache = new ConcurrentHashMap<>();

// ✅ Atomic compound operations (putIfAbsent, computeIfAbsent)
cache.computeIfAbsent(userId, id -> loadFromDatabase(id));
// This is atomic — no duplicate DB calls under concurrent access
```

> 💬 **Follow-up:** What is the difference between `putIfAbsent()` and `computeIfAbsent()` in `ConcurrentHashMap`? Why is `computeIfAbsent()` safer for expensive-to-compute values?

[↑ Back to top](#-table-of-contents)

---

#### Q23. String Memory Leak

> **Difficulty:** Medium · **Topic:** Core Java · **Role:** Full Stack

**Scenario:** You're parsing large XML/JSON files and keeping substrings in a `Map<String, String>` for lookup. After processing 1000 files, memory usage is unexpectedly large.

**Root Cause (Java 6 and Earlier):**  
`String.substring()` shared the underlying `char[]` of the original string. A 1KB substring from a 10MB string kept the entire 10MB alive.

**Java 7+ Fix:**  
`substring()` now copies the `char[]`, so this specific leak no longer applies. However, you can still leak by holding references to large strings unnecessarily.

```java
// ❌ Holding a large String when only a small part is needed
Map<String, String> codes = new HashMap<>();
String largeXml = readEntireFile(); // 10MB string
codes.put("code", largeXml.substring(100, 110)); // Copies OK in Java 7+, but check pattern

// ✅ Explicitly create new String to guarantee independence (defensive)
codes.put("code", new String(largeXml.substring(100, 110)));

// ✅ Better: Use StringBuilder/CharBuffer for large text manipulation
// ✅ Process large files as streams (BufferedReader line by line) rather than loading all at once
try (BufferedReader reader = Files.newBufferedReader(path)) {
    reader.lines()
          .filter(line -> line.contains("TARGET"))
          .forEach(this::processLine); // GC can collect each line after processing
}
```

> 💬 **Follow-up:** How does `String.intern()` work? What are the benefits and risks of using it for large sets of repeating strings?

[↑ Back to top](#-table-of-contents)

---

### ⚙️ Advanced Java

---

#### Q24. CompletableFuture Performance Issue

> **Difficulty:** Medium · **Topic:** Advanced Java · **Role:** Full Stack / Senior

**Scenario:** You replaced sequential API calls with `CompletableFuture.supplyAsync()` to run them in parallel. But the total time actually increased. What went wrong?

**Root Cause Diagnosis:**

```java
// ❌ Problem 1: Default ForkJoinPool has only (CPU cores - 1) threads
// On a 4-core machine: pool has 3 threads
// 10 I/O-bound tasks still run mostly sequentially

CompletableFuture.supplyAsync(() -> callExternalApi()); // uses commonPool — too small for I/O

// ✅ Fix 1: Create a dedicated executor sized for I/O
ExecutorService ioExecutor = Executors.newFixedThreadPool(50);
CompletableFuture.supplyAsync(() -> callExternalApi(), ioExecutor);
```

**Thread Pool Sizing Rules:**

| Workload | Formula | Example (8-core machine) |
|----------|---------|--------------------------|
| CPU-bound | `N_CPU` | 8 threads |
| I/O-bound | `N_CPU × 2` | 16 threads (start here, measure) |
| Mixed I/O | `N_CPU / (1 - blocking_ratio)` | 80 threads if 90% blocking |

```java
// ✅ Full example: parallel I/O with timeout
ExecutorService executor = Executors.newFixedThreadPool(50);

List<CompletableFuture<UserProfile>> futures = userIds.stream()
    .map(id -> CompletableFuture
        .supplyAsync(() -> profileService.fetch(id), executor)
        .orTimeout(2, TimeUnit.SECONDS)
        .exceptionally(e -> UserProfile.empty(id))
    )
    .collect(Collectors.toList());

List<UserProfile> profiles = CompletableFuture
    .allOf(futures.toArray(new CompletableFuture[0]))
    .thenApply(v -> futures.stream().map(CompletableFuture::join).collect(Collectors.toList()))
    .join();
```

> 💬 **Follow-up:** What is a `ThreadPoolExecutor`'s rejection policy? What happens when the queue is full and all threads are busy?

[↑ Back to top](#-table-of-contents)

---

#### Q25. ThreadLocal Memory Leak

> **Difficulty:** Hard · **Topic:** Advanced Java · **Role:** Senior

**Scenario:** After a hot redeployment of your web app, the old deployment's classes are not being garbage collected, causing `OutOfMemoryError: Metaspace`. Heap dumps show `ClassLoader` references that shouldn't exist.

**Root Cause:**

```
Web Container (Tomcat) reuses threads across deployments.
ThreadLocal entries live in the Thread's own map.
If ThreadLocal is never removed, the Thread holds a reference 
to the old ClassLoader → old classes → old deployment never GC'd.
```

**The Fix:**

```java
// ❌ Leak: ThreadLocal set in request but never removed
public class RequestContext {
    static ThreadLocal<UserSession> session = new ThreadLocal<>();
    
    public static void setSession(UserSession s) {
        session.set(s); // set on thread from pool
        // thread goes back to pool with session still attached
    }
}

// ✅ Fix: Always remove in a finally block (or use a Filter)
@WebFilter("/*")
public class ThreadLocalCleanupFilter implements Filter {
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) 
            throws IOException, ServletException {
        try {
            chain.doFilter(req, res);
        } finally {
            RequestContext.session.remove(); // CRITICAL — clean up before thread returns to pool
            RequestContext.tenantId.remove();
            RequestContext.requestId.remove();
        }
    }
}
```

> 💬 **Follow-up:** How does `InheritableThreadLocal` work? What problems can it cause in thread pool environments?

[↑ Back to top](#-table-of-contents)

---

#### Q26. HashMap Key Being Modified

> **Difficulty:** Medium · **Topic:** Advanced Java · **Role:** Full Stack

**Scenario:** You store an object in a `HashMap` using a custom key. Later you modify a field on that key object. Now `map.get(key)` returns `null` even though the entry is still in the map.

**Root Cause:**

```java
// ❌ Mutable HashMap key — classic bug
class User {
    String name;
    
    @Override public int hashCode() { return name.hashCode(); }
    @Override public boolean equals(Object o) { return name.equals(((User)o).name); }
}

User user = new User("Alice");
map.put(user, "admin");
user.name = "Bob"; // modifies hashCode!
map.get(user); // → null (looks in wrong bucket)
```

**Why:** `HashMap` uses `hashCode()` to find the bucket. After mutation, the hash changes, so `get()` looks in the wrong bucket and finds nothing.

**Fix:**

```java
// ✅ Fix 1: Make key class immutable (best practice)
final class UserId {
    private final long id;
    private final String name;
    
    // All fields final → hashCode never changes after construction
    @Override public int hashCode() { return Long.hashCode(id); }
    @Override public boolean equals(Object o) { 
        return o instanceof UserId u && this.id == u.id; 
    }
}

// ✅ Fix 2: Use primitives or Strings as keys where possible
// String, Long, Integer are all immutable — safe as HashMap keys
```

> 💬 **Follow-up:** What contract must `hashCode()` and `equals()` satisfy? What is the consequence of implementing `equals()` without `hashCode()`?

[↑ Back to top](#-table-of-contents)

---

#### Q27. Garbage Collection Pause Time

> **Difficulty:** Hard · **Topic:** Advanced Java / JVM · **Role:** Senior

**Scenario:** Your REST API has occasional 2–3 second latency spikes. GC logs reveal `Stop-The-World` Full GC events. The heap is 8GB and you're on G1GC. How do you reduce pauses?

**Diagnosis:**

```bash
# Enable GC logging
-Xlog:gc*:file=/tmp/gc.log:time,uptime,level,tags

# Analyze with GCViewer or GCEasy.io
# Look for: Full GC frequency, pause duration, heap reclamation rate
```

**Solutions by Cause:**

| Cause | Solution |
|-------|----------|
| Humongous objects (>50% of G1 region size) | Increase G1 region size: `-XX:G1HeapRegionSize=32m` |
| Old Gen filling faster than G1 can collect | Increase heap: `-Xmx` or tune `-XX:InitiatingHeapOccupancyPercent` |
| Need < 200ms pauses | Tune G1: `-XX:MaxGCPauseMillis=100` |
| Need < 1ms pauses (Java 15+) | Switch to ZGC: `-XX:+UseZGC` |
| Too much object allocation | Object pooling; avoid large temporary objects |

```bash
# G1GC tuning for low latency
-XX:+UseG1GC
-Xms8g -Xmx8g                    # equal min/max prevents resize pauses
-XX:MaxGCPauseMillis=100          # target pause time (best effort)
-XX:G1HeapRegionSize=16m
-XX:InitiatingHeapOccupancyPercent=35

# ZGC for sub-millisecond (Java 21 recommended)
-XX:+UseZGC -XX:+ZGenerational
-Xms8g -Xmx8g
```

> 💬 **Follow-up:** What are "humongous objects" in G1GC and how do they cause premature Full GCs?

[↑ Back to top](#-table-of-contents)

---

#### Q28. Deadlock Detection

> **Difficulty:** Hard · **Topic:** Advanced Java · **Role:** Senior

**Scenario:** At 3 AM, your application stops processing requests. All service instances are unresponsive. CPU is near zero. Thread dumps show threads in `BLOCKED` state. You suspect a deadlock.

**Detection:**

```bash
# Get thread dump (safe for production)
jstack <pid> > thread-dump.txt

# Look for this in the output:
# "Found one Java-level deadlock:"
# Thread-1 is waiting for lock held by Thread-2
# Thread-2 is waiting for lock held by Thread-1

# Linux: find threads consuming CPU
top -H -p <pid>
printf '%x\n' <TID>   # convert TID to hex
grep -A 30 "nid=0x<hex>" thread-dump.txt
```

**Prevention Techniques:**

```java
// ✅ Fix 1: Consistent lock ordering (most important rule)
// Always acquire Lock A before Lock B — everywhere in the codebase
synchronized (lockA) {
    synchronized (lockB) { doWork(); }
}

// ✅ Fix 2: tryLock with timeout (never block forever)
if (lockA.tryLock(500, TimeUnit.MILLISECONDS)) {
    try {
        if (lockB.tryLock(500, TimeUnit.MILLISECONDS)) {
            try { doWork(); }
            finally { lockB.unlock(); }
        }
    } finally {
        lockA.unlock();
    }
}

// ✅ Fix 3: Use higher-level concurrency utilities
// ConcurrentHashMap, BlockingQueue, Semaphore — avoid low-level synchronized
```

> 💬 **Follow-up:** What is a livelock? How does it differ from a deadlock, and why is it harder to detect?

[↑ Back to top](#-table-of-contents)

---

#### Q29. Java Stream Parallel Performance

> **Difficulty:** Medium · **Topic:** Advanced Java · **Role:** Full Stack

**Scenario:** You added `.parallel()` to a stream processing 500 orders. The operation slowed down from 200ms to 800ms. Why?

**Root Cause Analysis:**

```java
// ❌ Parallel stream on small dataset — overhead dominates
List<Order> orders = getOrders(); // 500 items
orders.parallelStream()
      .map(order -> calculateTotal(order)) // fast CPU operation
      .collect(Collectors.toList());
// Overhead: task splitting + thread synchronization + result merging > actual work
```

**When Parallel Streams Help vs. Hurt:**

| Scenario | Sequential | Parallel |
|----------|-----------|---------|
| < 10,000 elements | ✅ Faster | ❌ Overhead dominates |
| CPU-intensive per element | ⚠️ OK | ✅ Faster |
| I/O-bound per element | ✅ OK | ❌ Won't help (use CompletableFuture instead) |
| LinkedList source | ✅ OK | ❌ Poor splitting |
| Stateful lambdas | ✅ Safe | ❌ Race conditions |
| Ordered result required | ✅ Natural | ⚠️ Use forEachOrdered |

```java
// ✅ Right use case: CPU-intensive, large dataset
IntStream.range(0, 1_000_000)
    .parallel()
    .map(n -> expensiveCpuOperation(n))
    .sum();

// ✅ For I/O-bound parallel work, use CompletableFuture with custom executor
ExecutorService pool = Executors.newFixedThreadPool(20);
List<CompletableFuture<Result>> futures = items.stream()
    .map(item -> CompletableFuture.supplyAsync(() -> callApi(item), pool))
    .collect(Collectors.toList());
```

> 💬 **Follow-up:** How does `ForkJoinPool.commonPool()` affect parallel streams across different parts of an application running simultaneously?

[↑ Back to top](#-table-of-contents)

---

### 🏛️ Design Patterns, Spring Deep-Dive & JVM

---

#### Q30. Singleton in Multithreaded Environment

> **Difficulty:** Medium · **Topic:** Design Patterns · **Role:** Full Stack

**Scenario:** Under concurrent load testing, you discover that your Singleton class is occasionally creating multiple instances. How?

```java
// ❌ Not thread-safe: race condition between null check and assignment
public static Singleton getInstance() {
    if (instance == null) {         // Thread A and B both pass this check
        instance = new Singleton(); // Both create an instance
    }
    return instance;
}
```

**Four Solutions (Best to Worst):**

```java
// ✅ #1 — Initialization-on-Demand Holder (best — zero synchronization overhead)
public class Singleton {
    private static class Holder {
        static final Singleton INSTANCE = new Singleton(); // JVM class loading is thread-safe
    }
    public static Singleton getInstance() { return Holder.INSTANCE; }
}

// ✅ #2 — Enum Singleton (most secure — also prevents serialization attacks)
public enum Singleton {
    INSTANCE;
    public void doWork() { ... }
}

// ✅ #3 — Double-Checked Locking with volatile (efficient — only syncs on first call)
private static volatile Singleton instance; // volatile prevents instruction reordering
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

// ⚠️ #4 — Synchronized method (correct but slow — synchronizes every call)
public static synchronized Singleton getInstance() { ... }
```

> 💬 **Follow-up:** Why does the Enum approach also prevent Singleton breakage via Java serialization and reflection?

[↑ Back to top](#-table-of-contents)

---

#### Q31. Exception Handling in Transaction

> **Difficulty:** Medium · **Topic:** Spring Deep-Dive · **Role:** Full Stack

> ℹ️ *Related: See Q6 (@Transactional Not Working) for proxy/self-invocation issues.*

**Scenario:** You have a `@Transactional` method that catches an exception and logs it. After the method returns, you notice the database changes were committed even though an error occurred.

```java
// ❌ Problem: exception caught and swallowed — transaction commits
@Transactional
public void processOrder(Order order) {
    try {
        inventoryService.deduct(order);
        paymentService.charge(order);
    } catch (Exception e) {
        log.error("Order processing failed", e); // swallowed! transaction commits
    }
}
```

**Fixes:**

```java
// ✅ Fix 1: Don't catch — let it propagate (cleanest)
@Transactional
public void processOrder(Order order) {
    inventoryService.deduct(order);
    paymentService.charge(order);
    // any RuntimeException will auto-rollback
}

// ✅ Fix 2: Catch, mark for rollback, rethrow
@Transactional
public void processOrder(Order order) {
    try {
        inventoryService.deduct(order);
        paymentService.charge(order);
    } catch (Exception e) {
        log.error("Processing failed", e);
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        throw new OrderProcessingException("Failed", e); // must rethrow
    }
}

// ✅ Fix 3: Rollback for checked exceptions too
@Transactional(rollbackFor = Exception.class)
public void processOrder(Order order) throws PaymentException {
    inventoryService.deduct(order);
    paymentService.charge(order); // throws checked PaymentException
}
```

> 💬 **Follow-up:** What is the default rollback behavior for `Error` vs `RuntimeException` vs checked `Exception` in Spring? What happens with `@Transactional(noRollbackFor = IllegalArgumentException.class)`?

[↑ Back to top](#-table-of-contents)

---

#### Q32. Circular Dependency in Constructor Injection

> **Difficulty:** Medium · **Topic:** Spring Deep-Dive · **Role:** Full Stack

> ℹ️ *Related: See Q7 (Circular Dependency in Spring) for general approaches.*

**Scenario:** Spring Boot 2.6+ refuses to start with `BeanCurrentlyInCreationException`. You use constructor injection throughout (as recommended). How do you resolve this without compromising your design?

**Resolution Priority:**

```java
// 🏆 Best: Extract shared logic into a third component
// If A needs B and B needs A, they likely share a responsibility
// → Create ServiceC with that shared logic
// → Both A and B depend on C (no cycle)

// ✅ Good: @Lazy delays one bean's initialization
@Service
public class ServiceA {
    private final ServiceB serviceB;
    
    public ServiceA(@Lazy ServiceB serviceB) { // resolved at first use, not at startup
        this.serviceB = serviceB;
    }
}

// ✅ Good: Event-driven decoupling (A and B never directly reference each other)
@Service
public class ServiceA {
    @Autowired ApplicationEventPublisher publisher;
    
    public void doSomething() {
        publisher.publishEvent(new SomethingHappenedEvent(this, data));
        // ServiceB @EventListener handles it — no direct dependency
    }
}

// ⚠️ Enable circular deps as last resort (not recommended)
# application.yml
spring.main.allow-circular-references: true
```

> 💬 **Follow-up:** Spring Boot 2.6 disallowed circular dependencies by default. What motivated that change and what does it tell you about code that has circular deps?

[↑ Back to top](#-table-of-contents)

---

#### Q33. Java 8 Optional Best Practices

> **Difficulty:** Easy · **Topic:** Java 8+ · **Role:** Full Stack

**Scenario:** A code review reveals `Optional` being used in ways that actually introduce new `NullPointerException` risks instead of preventing them.

```java
// ❌ Wrong 1: Optional.of() with potentially null value
Optional<String> opt = Optional.of(possiblyNull); // NPE if null!

// ✅ Correct
Optional<String> opt = Optional.ofNullable(possiblyNull);

// ❌ Wrong 2: Calling get() without checking
String value = opt.get(); // throws NoSuchElementException if empty

// ✅ Correct alternatives
String value = opt.orElse("default");
String value = opt.orElseGet(() -> computeDefault());    // lazy evaluation
String value = opt.orElseThrow(() -> new NotFoundException("Not found"));
opt.ifPresent(v -> process(v));
opt.ifPresentOrElse(v -> process(v), () -> handleEmpty()); // Java 9+

// ❌ Wrong 3: Using Optional as a field or parameter
class Order {
    private Optional<Customer> customer; // Don't — serialization issues
}

// ✅ Optional is for return types only
public Optional<Customer> findCustomer(long id) {
    return customerRepo.findById(id); // caller decides what to do with absence
}

// ❌ Wrong 4: Nested Optionals
Optional<Optional<String>> nested; // almost always wrong

// ✅ Use flatMap to flatten
Optional<String> flat = outer.flatMap(inner -> inner);
```

> 💬 **Follow-up:** Why is `Optional` not `Serializable`? What does this tell you about its intended use?

[↑ Back to top](#-table-of-contents)

---

#### Q34. ClassLoader Issue in Web Application

> **Difficulty:** Hard · **Topic:** JVM · **Role:** Senior

**Scenario:** After deploying a new version of your web application to Tomcat, you get `ClassNotFoundException` for a class that exists in your JAR. The same JAR works fine in a fresh deployment.

**Root Cause Analysis:**

```
JVM ClassLoader Hierarchy:
  Bootstrap ClassLoader (JDK classes)
    └── Platform ClassLoader (ext libs)
          └── Application ClassLoader (app classes)
                └── WebApp ClassLoader (WEB-INF/lib — per deployment)

Problem: Multiple versions of the same class loaded by different ClassLoaders
         Class loaded by WebApp ClassLoader A is not the same as Class from B
         Static variables survive redeployment if ClassLoader is leaked
```

**Diagnostic Steps:**

```bash
# Find duplicate JARs
jar tf app.war | grep "\.class" | sort | uniq -d

# Check which ClassLoader loaded a class (in code)
System.out.println(SomeClass.class.getClassLoader()); 
System.out.println(Thread.currentThread().getContextClassLoader());
```

**Fixes:**

```java
// ✅ Use TCCL for loading resources/plugins
ClassLoader cl = Thread.currentThread().getContextClassLoader();
Class<?> clazz = cl.loadClass("com.plugin.Implementation");

// ✅ In Spring Boot fat JAR — classes loaded consistently
// Avoid "provided" scope dependencies unless truly provided by container

// ✅ In OSGi/module systems — each bundle has its own ClassLoader
// Use service interfaces (not implementations) across bundle boundaries
```

**Prevention:**
- Use Maven BOM to enforce single library versions across modules
- Use `mvn dependency:tree` to detect version conflicts
- Add `<scope>provided</scope>` only for genuinely container-provided libraries

> 💬 **Follow-up:** What is "ClassLoader leak" in a servlet container and how does it eventually cause `OutOfMemoryError: Metaspace`?

[↑ Back to top](#-table-of-contents)

---

#### Q35. Producer-Consumer Pattern Implementation

> **Difficulty:** Medium · **Topic:** Concurrency · **Role:** Full Stack

**Scenario:** You need to process 1 million records from a database. A single-threaded sequential approach takes 4 hours. How do you redesign this with a producer-consumer pattern to reduce processing time?

```java
// ✅ BlockingQueue-based producer-consumer (best for most cases)
public void processAllRecords() throws InterruptedException {
    BlockingQueue<Record> queue = new ArrayBlockingQueue<>(1000); // bounded = backpressure
    
    // Producers: read from DB in pages
    int producerCount = 4;
    ExecutorService producers = Executors.newFixedThreadPool(producerCount);
    for (int i = 0; i < producerCount; i++) {
        int batchId = i;
        producers.submit(() -> {
            List<Record> batch = db.fetchBatch(batchId);
            for (Record r : batch) {
                queue.put(r); // blocks if queue full — natural backpressure
            }
        });
    }
    
    // Consumers: process records in parallel
    int consumerCount = 8; // I/O-bound: 2× producer count
    ExecutorService consumers = Executors.newFixedThreadPool(consumerCount);
    AtomicBoolean done = new AtomicBoolean(false);
    for (int i = 0; i < consumerCount; i++) {
        consumers.submit(() -> {
            while (!done.get() || !queue.isEmpty()) {
                Record record = queue.poll(100, TimeUnit.MILLISECONDS);
                if (record != null) {
                    processRecord(record);
                }
            }
        });
    }
    
    producers.shutdown();
    producers.awaitTermination(1, TimeUnit.HOURS);
    done.set(true);
    consumers.shutdown();
    consumers.awaitTermination(1, TimeUnit.HOURS);
}
```

**Key Design Decisions:**
- **Bounded queue** (`ArrayBlockingQueue`) provides backpressure — producers slow down when consumers can't keep up
- **Thread ratio**: typically `consumers = 2 × producers` for I/O-heavy processing
- **Poison pill pattern** (alternative): producers push a sentinel `DONE` record; consumers exit when they see it

> 💬 **Follow-up:** How would you handle a consumer that crashes while processing a record? What guarantees do you need to prevent record loss?

[↑ Back to top](#-table-of-contents)

---

## Section 3 — Senior / Architect Level Scenarios

### ☕ Core Java

---

#### Q36. Memory Leak in a Long-Running Java Service

> **Difficulty:** Hard · **Topic:** Core Java · **Role:** Architect

**Scenario:** Your production microservice starts with 512MB heap. After 3 days it triggers OOM and restarts. GC logs show Full GC firing every 5 minutes and reclaiming less memory each time — a classic saw-tooth pattern that never fully recovers.

**Step 1 — Instrument Before the Next Crash:**

```bash
# Add to JVM startup flags
-XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/tmp/
-Xlog:gc*:file=/tmp/gc.log:time,uptime
-XX:NativeMemoryTracking=summary  # detect off-heap leaks too
```

**Step 2 — Analyze Heap Dump in Eclipse MAT:**
1. Run "Leak Suspects Report" — gives top suspects with retention tree
2. Check "Dominator Tree" — find objects holding most memory
3. Use "Path to GC Roots" on suspect objects — shows what's preventing GC

**Common Leaks and Fixes:**

```java
// ❌ LEAK: ThreadLocal never cleaned in thread pool
static ThreadLocal<UserContext> ctx = new ThreadLocal<>();
// Thread returns to pool with old UserContext → ClassLoader reference held

// ✅ FIX: always remove in finally
try {
    ctx.set(new UserContext(user));
    processRequest();
} finally {
    ctx.remove(); // critical — runs even if exception thrown
}

// ❌ LEAK: Unbounded Caffeine / Guava cache with no eviction policy
static Cache<Long, Report> cache = CacheBuilder.newBuilder().build(); // grows forever

// ✅ FIX: explicit size and time limits
static Cache<Long, Report> cache = Caffeine.newBuilder()
    .maximumSize(5_000)
    .expireAfterWrite(1, TimeUnit.HOURS)
    .recordStats() // monitor hit rate in Micrometer
    .build();
```

**Distinguishing Leak from Undersizing:**

| Indicator | Leak | Undersizing |
|-----------|------|-------------|
| GC reclaims less each cycle | ✅ Yes | ❌ No — reclaims consistently |
| Heap at 100% after GC | ✅ Yes | ❌ Drops to ~50% after GC |
| Growth rate proportional to traffic | ❌ No | ✅ Yes |
| Dominators are internal objects | ✅ Often | ❌ Usually user data |

> 💬 **Follow-up:** How would you use `async-profiler` in production to identify allocation hotspots with minimal overhead?

[↑ Back to top](#-table-of-contents)

---

#### Q37. Java Memory Model & Concurrent Code

> **Difficulty:** Hard · **Topic:** Core Java · **Role:** Architect

**Scenario:** A senior engineer reviews your singleton and says it has a "visibility bug" even though it looks correct. You have the double-null-check pattern but are missing one keyword.

**The Broken Code:**

```java
// ❌ BROKEN: CPU can reorder instructions
// Sequence: allocate memory → assign reference → initialize object
// Thread B can see non-null reference to an uninitialized object
private static Singleton instance;

public static Singleton getInstance() {
    if (instance == null) {
        synchronized (Singleton.class) {
            if (instance == null) {
                instance = new Singleton(); // 3 operations, can be reordered
            }
        }
    }
    return instance; // Thread B may return partially constructed Singleton!
}
```

**The Fix — `volatile` Creates a Happens-Before Fence:**

```java
// ✅ CORRECT: volatile write happens-before volatile read
private static volatile Singleton instance;
// Now: initialization completes BEFORE reference is published
```

**JMM Guarantees — Know These:**

| Action | Happens-Before |
|--------|----------------|
| `volatile` write | `volatile` read of same variable |
| `synchronized` block exit | Entry of same monitor by another thread |
| `Thread.start()` | Any code in that new thread |
| Object construction completion | Any code that reads a `final` field |

**Best Pattern — Holder Idiom (no synchronization at all):**

```java
public class Singleton {
    private static class Holder {
        static final Singleton INSTANCE = new Singleton();
        // JVM class loading is atomic and thread-safe by spec
        // class initialized once before first access
    }
    public static Singleton get() { return Holder.INSTANCE; }
}
```

> 💬 **Follow-up:** What is a "spurious wakeup"? Show the correct pattern for using `Object.wait()` that handles it.

[↑ Back to top](#-table-of-contents)

---

#### Q38. CompletableFuture Pipeline with Error Handling

> **Difficulty:** Hard · **Topic:** Core Java · **Role:** Architect

**Scenario:** Design an async workflow that: (1) fetches a user, (2) fetches their orders and preferences in parallel, (3) combines them into a personalized feed. If any step fails, return a degraded response — never throw to the caller.

**Full Production Pipeline:**

```java
public CompletableFuture<Feed> buildFeed(long userId) {
    // Step 1: Fetch user (degrade to anonymous on failure)
    CompletableFuture<User> userF =
        CompletableFuture.supplyAsync(() -> userRepo.find(userId), executor)
            .exceptionally(e -> {
                log.warn("User fetch failed for {}: {}", userId, e.getMessage());
                return User.anonymous();
            });

    // Step 2a+2b: Fetch orders and preferences in parallel (both depend on user)
    CompletableFuture<List<Order>> ordersF =
        userF.thenComposeAsync(u -> orderService.getOrders(u.getId()), executor)
             .exceptionally(e -> Collections.emptyList());

    CompletableFuture<Prefs> prefsF =
        userF.thenComposeAsync(u -> prefService.getPrefs(u.getId()), executor)
             .exceptionally(e -> Prefs.defaults());

    // Step 3: Combine all results → build feed
    return userF.thenCombineAsync(
        ordersF.thenCombineAsync(prefsF, OrdersAndPrefs::new, executor),
        (user, op) -> feedBuilder.build(user, op.orders(), op.prefs()),
        executor
    )
    .orTimeout(2, TimeUnit.SECONDS)  // global timeout across entire pipeline
    .exceptionally(e -> {
        log.error("Feed build failed for {}", userId, e);
        return Feed.empty();
    });
}
```

**Key API Reference:**

| Method | Use Case |
|--------|----------|
| `thenApply` | Sync transform: `Future<A>` → `Future<B>` |
| `thenCompose` | Async flatMap: `Future<A>` → `Future<Future<B>>` → `Future<B>` |
| `thenCombine` | Merge two independent futures |
| `exceptionally` | Fallback value when pipeline fails |
| `orTimeout` | Fail with `TimeoutException` after N time units (Java 9+) |
| `allOf` | Wait for N futures; use with `thenApply` to collect results |
| `anyOf` | Return result of whichever future completes first |

> 💬 **Follow-up:** What happens when the executor's thread is interrupted while a `CompletableFuture` is running? How do you propagate cancellation through a pipeline?

[↑ Back to top](#-table-of-contents)

---

#### Q39. G1GC vs ZGC — When to Choose Which

> **Difficulty:** Hard · **Topic:** JVM / GC · **Role:** Architect

**Scenario:** You have two services: a batch processor running 30-minute jobs on a 32GB heap, and a real-time API with P99 < 10ms SLA. Your DevOps team asks for the GC strategy for each.

**GC Comparison Matrix:**

| GC | Pause Time | Throughput | Heap Size | Best For |
|----|-----------|-----------|----------|---------|
| **G1GC** | ~100–500ms | ✅ High | 4GB–32GB | General purpose; batch jobs |
| **ZGC** (Java 15+) | < 1ms | ⚠️ Slight cost | Any (up to TB) | Latency-critical APIs |
| **Shenandoah** | < 10ms | ⚠️ Slight cost | Medium–large | Red Hat JDK; moderate latency |
| **Serial/Parallel** | High | ✅ Highest | Small | Microcontainers; CLI tools |

**Batch Processor (Throughput Matters):**

```bash
-XX:+UseG1GC
-Xms32g -Xmx32g                    # equal min/max — no resize pauses
-XX:MaxGCPauseMillis=500            # tolerate 500ms pauses for throughput
-XX:G1HeapRegionSize=32m            # large regions for large heap
-XX:+G1UseAdaptiveIHOP              # auto-tune when to start concurrent mark
```

**Real-Time API (P99 < 10ms SLA):**

```bash
-XX:+UseZGC
-XX:+ZGenerational                 # Java 21+ — generational ZGC is faster
-Xms8g -Xmx8g
-XX:ConcGCThreads=4
-Xlog:gc*:file=/tmp/zgc.log:time   # monitor pauses
```

**Java 21 Virtual Threads Impact:**  
Virtual threads dramatically reduce heap pressure for I/O-heavy services — each virtual thread uses ~1KB of heap vs 1MB for platform threads. This reduces GC pressure significantly at high concurrency.

> 💬 **Follow-up:** What are "ZGC's colored pointers" and how do they allow concurrent GC without Stop-The-World pauses?

[↑ Back to top](#-table-of-contents)

---

#### Q40. Custom ClassLoader for Plugin Isolation

> **Difficulty:** Hard · **Topic:** JVM · **Role:** Architect

**Scenario:** You're building a platform where customers upload Java code that runs in your application at runtime. Plugin A must not affect Plugin B, and a buggy plugin must not crash the host.

**Custom ClassLoader Implementation:**

```java
public class PluginClassLoader extends URLClassLoader {
    
    public PluginClassLoader(URL[] pluginJarUrls, ClassLoader hostClassLoader) {
        super(pluginJarUrls, hostClassLoader);
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve)
            throws ClassNotFoundException {
        // Child-first delegation (reverse of normal parent-first)
        // Plugin's own classes take precedence over parent
        try {
            return findClass(name);         // check plugin JAR first
        } catch (ClassNotFoundException e) {
            return super.loadClass(name, resolve); // fall back to host
        }
    }
}

// Load and execute plugin
public Plugin loadPlugin(Path pluginJar) throws Exception {
    PluginClassLoader loader = new PluginClassLoader(
        new URL[]{pluginJar.toUri().toURL()},
        Thread.currentThread().getContextClassLoader()
    );
    Class<?> pluginClass = loader.loadClass("com.customer.PluginImpl");
    return (Plugin) pluginClass.getDeclaredConstructor().newInstance();
}

// Unload plugin: remove all references to loader and its classes
// GC will collect the ClassLoader when no strong references remain
pluginRegistry.remove(pluginId);     // removes the PluginClassLoader reference
// WeakReference<PluginClassLoader> allows GC to reclaim it
```

**Isolation Guarantees:**
- Each plugin has its own `ClassLoader` — version conflicts between plugins are impossible
- Plugin's static state is isolated to its `ClassLoader` — one plugin's static fields can't affect another
- To unload: null all references to the `ClassLoader` and its classes → GC collects them (including loaded classes from Metaspace)

**Security Sandboxing (Modern Approach):**
- Java's `SecurityManager` is deprecated (Java 17+)
- Use GraalVM's `Polyglot` sandbox for scripts
- Use time limits via `Future.get(timeout)`
- Run in separate JVM process for true isolation

> 💬 **Follow-up:** What is the ClassLoader hierarchy in a Spring Boot fat JAR? How does Spring Boot's `LaunchedURLClassLoader` differ from the standard application ClassLoader?

[↑ Back to top](#-table-of-contents)

---

### 🏗️ Architecture

---

#### Q41. Rate-Limiting System for an API Gateway

> **Difficulty:** Senior · **Topic:** Architecture · **Role:** Architect

**Scenario:** Your fintech API gateway serves 50,000 RPS. One rogue client is hammering the `/transactions` endpoint, causing 503s for legitimate users. You need per-client rate limiting that survives node restarts and works across 10 gateway instances.

**Why Local Counters Fail:** Each of 10 gateway nodes has its own counter. Client can make N requests to each node — effectively N×10 allowed before any limit fires.

**Architecture: Two-Level Rate Limiting**

```
Request → [L1: Local Caffeine Cache (hot clients, 100ms TTL)]
        → [L2: Redis Cluster (distributed, atomic counter)]
        → [Allow / HTTP 429 + Retry-After header]
```

**Redis Lua Script — Atomic Sliding Window:**

```java
// Atomic: check + increment in one Redis round-trip
String slidingWindowScript = """
    local key = KEYS[1]
    local now = tonumber(ARGV[1])
    local windowMs = tonumber(ARGV[2])
    local limit = tonumber(ARGV[3])
    
    redis.call('ZREMRANGEBYSCORE', key, 0, now - windowMs)  -- remove expired
    local count = redis.call('ZCARD', key)                  -- count in window
    if count < limit then
        redis.call('ZADD', key, now, now .. '-' .. math.random())
        redis.call('PEXPIRE', key, windowMs)
        return {1, limit - count - 1}  -- {allowed, remaining}
    end
    return {0, 0}
""";

// Key pattern: "ratelimit:{clientId}:{endpoint}"
// Window: 1000ms, Limit: 100 req/sec per client
```

**Spring Cloud Gateway Integration:**

```java
@Component
public class RateLimitGatewayFilter implements GlobalFilter, Ordered {
    
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String clientId = extractClientId(exchange.getRequest());
        
        return rateLimiter.isAllowed(clientId)
            .flatMap(result -> {
                exchange.getResponse().getHeaders()
                    .add("X-RateLimit-Remaining", String.valueOf(result.remaining()));
                
                return result.allowed()
                    ? chain.filter(exchange)
                    : tooManyRequests(exchange, result);
            });
    }
    
    private Mono<Void> tooManyRequests(ServerWebExchange ex, RateLimitResult result) {
        ex.getResponse().setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
        ex.getResponse().getHeaders().add("Retry-After", result.retryAfterSeconds());
        return ex.getResponse().setComplete();
    }
}
```

> 💬 **Follow-up:** How would you implement differentiated rate limits — e.g., free-tier clients get 100 req/min, premium get 10,000 req/min — without hardcoding tier logic in the gateway?

[↑ Back to top](#-table-of-contents)

---

#### Q42. Monolith to Microservices Migration Without Downtime

> **Difficulty:** Senior · **Topic:** Architecture · **Role:** Architect

**Scenario:** 5-year-old Spring MVC monolith. 200 tables, 1M active users, zero tests. Business needs 3 features requiring independent scaling. No big-bang rewrites allowed.

**Strangler Fig Pattern — 4 Phases:**

```
Phase 1 — Add Facade (Week 1-2):
  API Gateway (Kong / Spring Cloud Gateway) in front of monolith
  100% traffic still hits monolith
  Gain: observability, auth centralization, rate limiting

Phase 2 — Extract First Service (low-risk, high-value bounded context):
  Identify: what can be extracted with minimal DB coupling?
  Good candidates: Notifications, Email, Audit Log
  Bad candidates: Orders, Users (too many dependencies)
  
  Anti-Corruption Layer: new service reads monolith data via API
  (never direct DB access from new service to monolith DB)

Phase 3 — Dual Write + Shadow Mode:
  Write to BOTH monolith DB and new service DB
  New service reads: shadow mode (compare results, don't serve to users)
  Use Debezium (CDC) to sync monolith DB → new service DB during transition

Phase 4 — Traffic Shift + Sunset:
  Feature flag: 1% → 10% → 50% → 100% of traffic to new service
  Monitor error rates; rollback flag if issues
  Remove monolith code path after 2-week full-traffic validation
```

**Data Migration Strategy:**

```java
// Debezium: capture changes from monolith DB → publish to Kafka
// New service consumes Kafka → keeps its own DB in sync

// Shadow mode comparison (measure before switching)
@Component
public class ShadowOrderService {
    public Order getOrder(long id) {
        Order monolithOrder = monolithClient.getOrder(id);
        
        CompletableFuture.runAsync(() -> {
            Order newServiceOrder = newOrderService.getOrder(id);
            if (!monolithOrder.equals(newServiceOrder)) {
                metrics.increment("shadow.mismatch");
                log.warn("Mismatch for order {}: {} vs {}", id, monolithOrder, newServiceOrder);
            }
        });
        
        return monolithOrder; // still serve from monolith
    }
}
```

> 💬 **Follow-up:** How do you handle a database table used by 3 bounded contexts? At what point do you split it, and what do you do with foreign keys that cross service boundaries?

[↑ Back to top](#-table-of-contents)

---

#### Q43. Event-Driven Notification System at Scale

> **Difficulty:** Senior · **Topic:** Architecture · **Role:** Architect

**Scenario:** 10M users. Events generated at 50,000/sec peak. Notifications must arrive within 5 seconds, must not duplicate, and must survive restarts.

**Architecture Overview:**

```
[Domain Services] → Kafka topics → [Notification Router]
                                         ↓
                    ┌──────────────────────────────────┐
                    │  Push (FCM/APNs)                  │
                    │  Email (SES)       Delivery Workers│
                    │  SMS (Twilio)                      │
                    └──────────────────────────────────┘
                              ↓
                    [Redis: dedup] + [Postgres: receipts]
```

**Exactly-Once Delivery:**

```java
@KafkaListener(topics = "notifications")
public void processNotification(NotificationEvent event) {
    // Redis SETNX = atomic "set if not exists"
    String dedupKey = "notif:" + event.eventId() + ":" + event.userId();
    Boolean isNew = redis.opsForValue()
        .setIfAbsent(dedupKey, "1", Duration.ofHours(24));
    
    if (Boolean.TRUE.equals(isNew)) {
        deliveryService.deliver(event);
        // Commit Kafka offset AFTER successful delivery
        acknowledgment.acknowledge();
    }
    // Duplicate events are silently ignored
}
```

**Celebrity / Hot User Problem:**

```
❌ Eager fan-out: user with 5M followers = 5M INSERT statements on one event
✅ Lazy fan-out: store event once → expand to followers in background
   Priority queue: premium users → fast path (expanded immediately)
                  free users   → batch path (expanded over 60 seconds)
```

**Backpressure & Resilience:**
- FCM rejects with `429` → exponential backoff + dead-letter queue
- If DLQ grows: alert on-call; don't silently drop
- Circuit breaker per channel (if FCM is down, buffer; don't fail all notifications)

> 💬 **Follow-up:** How do you store per-user notification preferences (e.g., "email for likes but not push") and where in the pipeline do you evaluate them for minimal latency?

[↑ Back to top](#-table-of-contents)

---

#### Q44. Idempotent REST API for Payment Processing

> **Difficulty:** Senior · **Topic:** Architecture · **Role:** Architect

**Scenario:** Mobile client POSTs a $100 payment. Network blip — client never gets the 200. It retries. But your server already processed it. How do you prevent double-charging?

**Idempotency Key Pattern:**

```http
POST /payments HTTP/1.1
Idempotency-Key: 550e8400-e29b-41d4-a716-446655440000
Content-Type: application/json

{ "amount": 100, "currency": "USD", "to": "acct_xyz" }
```

**Implementation:**

```java
@PostMapping("/payments")
public ResponseEntity<Payment> createPayment(
        @RequestHeader("Idempotency-Key") String idempotencyKey,
        @RequestBody PaymentRequest request) {
    
    // Step 1: Check if we've seen this key before
    return idempotencyStore.find(idempotencyKey)
        .map(stored -> {
            // Replay: return exactly the same response as the first time
            log.info("Replaying idempotent response for key {}", idempotencyKey);
            return ResponseEntity.ok()
                .header("Idempotent-Replayed", "true")
                .body(stored.response());
        })
        .orElseGet(() -> {
            // First request: process and store atomically
            Payment payment = paymentService.charge(request);
            
            // Store MUST be atomic with processing (use same DB transaction or outbox)
            idempotencyStore.save(idempotencyKey, payment, Duration.ofDays(1));
            
            return ResponseEntity.status(HttpStatus.CREATED).body(payment);
        });
}
```

**Race Condition — Two Simultaneous Requests with Same Key:**

```java
// Use Redis distributed lock on idempotency key
String lockKey = "idempotency-lock:" + idempotencyKey;
Boolean locked = redis.opsForValue().setIfAbsent(lockKey, "1", Duration.ofSeconds(30));

if (!locked) {
    // Another request with same key is in-flight
    // Return 409 Conflict or poll for result
    return ResponseEntity.status(HttpStatus.CONFLICT)
        .body("Request with this key is already being processed");
}
```

**Important Details:**
- Idempotency key + request body hash must match; different body with same key = `422`
- TTL on stored keys: 24h is standard for payments
- Always return the _same_ HTTP status code on replay (return 201 if originally 201)
- Add `Idempotent-Replayed: true` header for client observability

> 💬 **Follow-up:** How do you handle idempotency for a payment that takes 30 seconds to process asynchronously? What HTTP response do you give when the same key arrives while the first request is still in flight?

[↑ Back to top](#-table-of-contents)

---

#### Q45. Blue-Green and Canary Deployments in Kubernetes

> **Difficulty:** Senior · **Topic:** Architecture · **Role:** Architect

**Scenario:** 20 microservices in Kubernetes. A critical service has a bug visible in only 5% of traffic patterns. Deploy the fix with zero downtime and < 60-second rollback capability.

**Blue-Green — Instant Switch:**

```yaml
# Both versions run simultaneously; Service selector flips traffic
apiVersion: v1
kind: Service
metadata:
  name: order-service
spec:
  selector:
    app: order-service
    version: green   # ← change to "blue" to rollback instantly (~5 seconds)
```

**Canary with Argo Rollouts — Gradual Traffic Shift:**

```yaml
apiVersion: argoproj.io/v1alpha1
kind: Rollout
spec:
  strategy:
    canary:
      steps:
      - setWeight: 5          # 5% to canary, 95% to stable
      - pause: {duration: 10m}
      - setWeight: 25
      - pause: {duration: 10m}
      - setWeight: 50
      - pause: {}             # manual gate before full rollout
      
      analysis:
        templates:
        - templateName: error-rate-check
        startingStep: 1
        args:
        - name: service-name
          value: order-service
```

**Automated Rollback — Prometheus Analysis:**

```yaml
apiVersion: argoproj.io/v1alpha1
kind: AnalysisTemplate
metadata:
  name: error-rate-check
spec:
  metrics:
  - name: error-rate
    interval: 1m
    failureLimit: 1           # rollback after 1 failed measurement
    successCondition: result[0] < 0.05   # pass if error rate < 5%
    provider:
      prometheus:
        address: http://prometheus:9090
        query: |
          sum(rate(http_requests_total{status=~"5..",service="{{args.service-name}}"}[5m]))
          /
          sum(rate(http_requests_total{service="{{args.service-name}}"}[5m]))
```

**Database Compatibility Rule:**  
During blue-green/canary, both versions run simultaneously. DB schema must be compatible with both. Use expand-contract: add nullable columns first, deploy, backfill, make required, then clean up.

> 💬 **Follow-up:** How do you handle stateful services (local cache or session state) in a blue-green deployment? What makes them harder than stateless services?

[↑ Back to top](#-table-of-contents)

---

### ⚡ Performance

---

#### Q46. Spring Boot Endpoint Optimization (800ms → 100ms)

> **Difficulty:** Hard · **Topic:** Performance · **Role:** Architect

**Scenario:** `GET /api/v1/dashboard` takes 800ms P99 under 500 concurrent users. Target: 100ms P99. Walk through your systematic optimization approach.

**Step 1 — Profile, Don't Guess:**

```java
// Add Micrometer timing to identify the actual slow method
@Timed(value = "dashboard.latency", percentiles = {0.5, 0.95, 0.99})
@GetMapping("/api/v1/dashboard")
public DashboardResponse getDashboard(@RequestParam long userId) {
    return dashboardService.build(userId);
}
```

**Step 2 — Fix Sequential DB Calls (biggest win):**

```java
// ❌ BEFORE: 5 sequential calls @ 150ms each = 750ms
User user = userRepo.findById(userId);                    // 150ms
List<Order> orders = orderRepo.findByUser(userId);        // 150ms  
Stats stats = statsRepo.findByUser(userId);               // 150ms
List<Alert> alerts = alertRepo.findByUser(userId);        // 150ms
Prefs prefs = prefRepo.findByUser(userId);                // 150ms

// ✅ AFTER: parallel calls = max(150ms) ≈ 150ms
CompletableFuture<User> userF = supplyAsync(() -> userRepo.findById(userId), executor);
CompletableFuture<List<Order>> ordersF = supplyAsync(() -> orderRepo.findByUser(userId), executor);
CompletableFuture<Stats> statsF = supplyAsync(() -> statsRepo.findByUser(userId), executor);
CompletableFuture.allOf(userF, ordersF, statsF).join();
```

**Step 3 — Add Caching:**

```java
@Cacheable(value = "dashboard", key = "#userId", 
           unless = "#result == null",
           cacheManager = "redisCacheManager") // Redis for distributed cache
@Timed(value = "dashboard.cache.latency")
public DashboardData getDashboardData(long userId) { ... }

// TTL: 30s for dashboard data (stale is acceptable; real-time isn't needed)
// Invalidate on order placement via @CacheEvict
```

**Step 4 — DB Optimizations:**

```sql
-- Add composite index covering WHERE + ORDER BY
CREATE INDEX CONCURRENTLY idx_orders_user_status_date 
  ON orders(user_id, status, created_at DESC);
  
-- Use covering index to avoid table lookup
CREATE INDEX CONCURRENTLY idx_orders_covering
  ON orders(user_id) INCLUDE (id, total, status, created_at);
```

**Optimization Impact Summary:**

| Optimization | Latency Reduction |
|-------------|-----------------|
| Parallelize 5 sequential DB calls | 750ms → 150ms |
| Add composite DB indexes | 150ms → 50ms |
| Redis cache (cache hit) | 50ms → 5ms |
| Connection pool tuning | 5–20% additional gain |

> 💬 **Follow-up:** How do you prevent "thundering herd" on a popular cache key when it expires? Describe probabilistic early expiration.

[↑ Back to top](#-table-of-contents)

---

#### Q47. High CPU Usage During Idle Periods

> **Difficulty:** Hard · **Topic:** Performance · **Role:** Architect

**Scenario:** Production pods show 80% CPU at 2 AM with zero user traffic. No scheduled jobs should be running. GC logs look normal. How do you find the root cause?

**Step 1 — Capture CPU Profile in Production:**

```bash
# Find Java process
jps -l

# Thread dump — shows what every thread is doing right now
jstack <pid> > /tmp/thread-dump.txt

# CPU profiling with async-profiler (< 1% overhead — safe for prod)
./profiler.sh -d 30 -e cpu -f /tmp/cpu-profile.html <pid>
# → Open in browser: flame graph shows hot methods

# Find which thread is consuming CPU (Linux)
top -H -p <pid>
# Note the TID, convert to hex:
printf '%x\n' <TID>
# Find in thread dump:
grep -A 30 "nid=0x<TID_HEX>" /tmp/thread-dump.txt
```

**Common Culprits at 2 AM:**

| Cause | Diagnosis | Fix |
|-------|-----------|-----|
| `@Scheduled` misconfigured (`"0 * * * * *"` = every minute not every hour) | Check all `@Scheduled` cron expressions | Fix cron; add `@ConditionalOnProperty` to disable in non-prod |
| HikariCP connection validation queries | Logs show rapid `SELECT 1` queries | Tune `keepaliveTime` and `idleTimeout` |
| Prometheus scrape triggering expensive metric computation | Check scrape interval vs metric computation cost | Cache expensive metrics |
| Log rotation (logback) compressing large files | Check file system activity | Schedule during maintenance window |
| JIT compilation (hot code still being optimized) | Check `XX:+PrintCompilation` | Normal; reduces over time |
| Infinite loop / busy-wait in background thread | Flame graph shows same method in tight loop | Fix loop to use blocking wait |

```java
// ❌ Busy-wait — 100% CPU for no reason
while (!condition) {
    checkCondition(); // spins constantly
}

// ✅ Blocking wait — yields CPU while waiting
while (!condition) {
    Thread.sleep(100);   // simple
    // or LockSupport.parkNanos(Duration.ofMillis(100).toNanos()); // precise
    // or use a BlockingQueue / condition variable
}
```

> 💬 **Follow-up:** What is the difference between CPU time and wall clock time for a thread? When would a thread consume zero CPU but appear to be "running" for a long time?

[↑ Back to top](#-table-of-contents)

---

#### Q48. Reactive Programming with Spring WebFlux

> **Difficulty:** Senior · **Topic:** Performance · **Role:** Architect

**Scenario:** Your blocking Spring MVC service needs 100K threads to handle 100K concurrent connections (100GB RAM in thread stacks alone). Redesign it for minimal thread usage.

**Why Reactive Solves the C10K Problem:**

```
Traditional (Blocking):
  1 request → 1 thread → thread blocked waiting for DB/HTTP → 100K requests = 100K threads

Reactive (Non-Blocking):
  1 event-loop thread → handles 1000s of requests
  DB call completes → callback resumes the reactive chain → no thread was blocked waiting
  8 event-loop threads → handle 100K concurrent connections
```

**Spring WebFlux Reactive Chain:**

```java
@RestController
public class OrderController {

    @GetMapping("/orders/{id}")
    public Mono<OrderResponse> getOrder(@PathVariable String id) {
        Mono<Order> orderMono = orderRepo.findById(id);        // R2DBC (non-blocking)
        Mono<Stock> stockMono = inventoryClient.getStock(id);  // WebClient (non-blocking)
        
        return Mono.zip(orderMono, stockMono)    // parallel, non-blocking
            .map(tuple -> OrderResponse.from(tuple.getT1(), tuple.getT2()))
            .switchIfEmpty(Mono.error(new NotFoundException(id)))
            .onErrorResume(TimeoutException.class, e -> Mono.just(OrderResponse.degraded()));
    }
}
```

**Critical Rules for Reactive Code:**

```java
// ❌ NEVER block in a reactive chain — kills event loop performance
Mono.fromCallable(() -> {
    Thread.sleep(1000);          // blocks event loop thread!
    return jdbcTemplate.query(); // JDBC = blocking
});

// ✅ Wrap blocking code in bounded elastic scheduler
Mono.fromCallable(() -> jdbcTemplate.queryForObject(...))
    .subscribeOn(Schedulers.boundedElastic()); // offloads to separate thread pool

// ✅ Error handling
orderMono
    .onErrorResume(NotFoundException.class, e -> Mono.empty())
    .onErrorMap(DbException.class, e -> new ServiceException("DB error", e))
    .timeout(Duration.ofSeconds(2));

// ✅ Backpressure on large streams
Flux.fromIterable(millionItems)
    .limitRate(100)              // process 100 at a time — prevent OOM
    .flatMap(item -> processAsync(item), 20) // max 20 concurrent
    .collectList();
```

**Java 21 Alternative — Virtual Threads:**
- Keep regular Spring MVC + blocking code
- Replace with: `spring.threads.virtual.enabled=true`
- Virtual threads handle 100K concurrent connections with ~1KB each (vs 1MB platform threads)
- Much simpler than reactive — no monad/flatMap learning curve

> 💬 **Follow-up:** How do you propagate Spring Security `Authentication` through a reactive chain? Why doesn't `SecurityContextHolder` (which uses `ThreadLocal`) work in WebFlux?

[↑ Back to top](#-table-of-contents)

---

### 🔗 Microservices

---

#### Q49. Saga Pattern for Distributed Order Transaction

> **Difficulty:** Senior · **Topic:** Microservices · **Role:** Architect

**Scenario:** Order → Inventory → Payment → Shipping services. Payment succeeds but inventory reservation fails. 2 minutes of partial state. How do you design this to be consistent without 2PC?

**Choreography vs. Orchestration:**

| Approach | Pros | Cons |
|----------|------|------|
| **Choreography** | No central coordinator; loose coupling | Hard to track global state; event spaghetti |
| **Orchestration** | Central control; easy to debug and monitor | Coordinator is a potential bottleneck |

**Orchestration Saga (recommended for complex flows):**

```java
@Saga
public class OrderSaga {

    @StartSaga
    @SagaEventHandler(associationProperty = "orderId")
    public void on(OrderCreatedEvent event) {
        log.info("Starting saga for order {}", event.orderId());
        commandGateway.send(new ReserveInventoryCommand(event.orderId(), event.items()));
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void on(InventoryReservedEvent event) {
        commandGateway.send(new ProcessPaymentCommand(event.orderId(), event.amount()));
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void on(PaymentSucceededEvent event) {
        commandGateway.send(new CreateShipmentCommand(event.orderId()));
    }

    // ← COMPENSATION FLOW (runs in reverse if anything fails)
    @SagaEventHandler(associationProperty = "orderId")
    public void on(PaymentFailedEvent event) {
        log.warn("Payment failed for order {} — compensating", event.orderId());
        commandGateway.send(new ReleaseInventoryCommand(event.orderId()));   // compensate step 1
        commandGateway.send(new CancelOrderCommand(event.orderId()));        // mark order cancelled
        SagaLifecycle.end();
    }
}
```

**Key Requirements:**
- Every step must have an idempotent compensating transaction
- Use Outbox Pattern: DB write + event publish in one atomic operation (prevents lost events)
- Store saga state durably (it must survive service restarts)
- Timeouts: if a step hangs > 30 seconds, trigger compensation automatically

> 💬 **Follow-up:** What is the difference between a Saga and 2PC? When would you actually choose 2PC despite its availability trade-offs?

[↑ Back to top](#-table-of-contents)

---

#### Q50. Circuit Breaker with Fallback Behavior

> **Difficulty:** Senior · **Topic:** Microservices · **Role:** Architect

**Scenario:** Order Service calls Payment Service synchronously. Payment goes down for 30 seconds. Order Service creates 10,000 threads waiting for timeouts. It runs OOM and cascades the outage.

**The Root Cause:** No fail-fast mechanism. Threads pile up waiting for a service that will never respond.

**Resilience4j Full Configuration:**

```yaml
# application.yml
resilience4j:
  circuitbreaker:
    instances:
      paymentService:
        slidingWindowSize: 10           # evaluate last 10 calls
        failureRateThreshold: 50        # open if 50% fail
        waitDurationInOpenState: 30s    # stay open for 30s
        permittedNumberOfCallsInHalfOpenState: 3
        
  timelimiter:
    instances:
      paymentService:
        timeoutDuration: 2s             # fail fast — don't wait 30s for timeout
        
  bulkhead:
    instances:
      paymentService:
        maxConcurrentCalls: 20          # max 20 threads for payment — isolates other work
```

```java
@CircuitBreaker(name = "paymentService", fallbackMethod = "paymentFallback")
@TimeLimiter(name = "paymentService")
@Bulkhead(name = "paymentService")
public CompletableFuture<PaymentResult> processPayment(Order order) {
    return CompletableFuture.supplyAsync(() -> paymentClient.charge(order));
}

// Graceful degradation: queue for async retry rather than failing the order
public CompletableFuture<PaymentResult> paymentFallback(Order order, Exception e) {
    log.warn("Payment service unavailable, queuing order {} for retry", order.id());
    pendingPaymentQueue.add(order);
    return CompletableFuture.completedFuture(PaymentResult.pending(order.id()));
}
```

**Circuit States:**

```
CLOSED → counting failures → failure rate > 50% → OPEN
OPEN → failing fast (no calls to downstream) → after 30s → HALF-OPEN
HALF-OPEN → 3 test requests → if success → CLOSED / if failure → OPEN
```

> 💬 **Follow-up:** Can combining retry + circuit breaker make things worse? Describe a scenario where aggressive retry during a brownout converts a partial failure into a total outage.

[↑ Back to top](#-table-of-contents)

---

#### Q51. Distributed Tracing Across Microservices

> **Difficulty:** Hard · **Topic:** Microservices · **Role:** Architect

**Scenario:** Customer reports slow checkout (4 seconds). Request touches Auth → Order → Inventory → Payment → Email. P99 is 4 seconds but you don't know which service is causing it. No tracing exists.

**OpenTelemetry + Jaeger Setup (Spring Boot 3):**

```xml
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
# application.yml — zero-code trace propagation
management:
  tracing:
    sampling:
      probability: 0.1          # 10% in prod (increase for debugging)
    exporter:
      otlp:
        endpoint: http://otel-collector:4318

# Trace ID auto-propagates via W3C traceparent header between services
```

**Custom Business Spans:**

```java
@Autowired Tracer tracer;

public Order processOrder(OrderRequest request) {
    // Create a child span for this specific operation
    Span span = tracer.nextSpan()
        .name("inventory-reservation")
        .tag("order.id", request.orderId())
        .tag("items.count", String.valueOf(request.items().size()))
        .start();
    
    try (Tracer.SpanInScope scope = tracer.withSpan(span)) {
        return inventoryService.reserve(request);
    } catch (Exception e) {
        span.tag("error", "true").tag("exception.message", e.getMessage());
        throw e;
    } finally {
        span.end(); // ALWAYS end spans
    }
}
```

**What to Tag on Spans:**

| Tag | Value |
|-----|-------|
| `http.method` | GET, POST, etc. |
| `http.url` | Full URL (sanitize PII) |
| `db.statement` | SQL (sanitized — no values) |
| `db.rows_affected` | Row count |
| `user.id` | For correlation to support tickets |
| `error` | `"true"` for error spans |

> 💬 **Follow-up:** How do you propagate trace context across Kafka message boundaries where there's no HTTP header? What Kafka header is used for the W3C `traceparent`?

[↑ Back to top](#-table-of-contents)

---

#### Q52. CQRS + Event Sourcing for Financial Ledger

> **Difficulty:** Senior · **Topic:** Microservices · **Role:** Architect

**Scenario:** Banking ledger requirements: every balance change must be auditable, reconstruct account state at any point in time, read queries must be fast, 10,000 transactions/second.

**Core Concepts:**

```
Event Sourcing: Don't store current state. Store the sequence of EVENTS that led to it.
  Account balance is not stored. It's computed by replaying all events for that account.
  
CQRS: Separate the WRITE model (commands → events) from READ models (pre-computed projections).
  Write path: slow, consistent, event-sourced
  Read path: fast, eventually consistent, optimized projections
```

**Write Side — Immutable Event Store:**

```java
// Every change is a sealed, immutable event
sealed interface LedgerEvent permits MoneyDebited, MoneyCredited, AccountFrozen {}

record MoneyDebited(UUID accountId, BigDecimal amount, 
                   String reference, Instant occurredAt) implements LedgerEvent {}
record MoneyCredited(UUID accountId, BigDecimal amount, 
                    String reference, Instant occurredAt) implements LedgerEvent {}

// Event store: append-only table
// CREATE TABLE events (
//   stream_id UUID, sequence BIGINT, event_type VARCHAR,
//   event_data JSONB, occurred_at TIMESTAMPTZ,
//   PRIMARY KEY (stream_id, sequence)
// );
```

**Read Side — Projections:**

```java
// Rebuild current balance by replaying events (use snapshot for performance)
public AccountBalance getBalance(UUID accountId) {
    // Load snapshot (last known good state at event N)
    Snapshot snapshot = snapshotStore.load(accountId);
    
    // Replay only events after snapshot
    List<LedgerEvent> events = eventStore.loadAfter(accountId, snapshot.version());
    
    return events.stream().reduce(snapshot.balance(), (balance, event) ->
        switch (event) {
            case MoneyCredited e -> balance.add(e.amount());
            case MoneyDebited e  -> balance.subtract(e.amount());
            case AccountFrozen e -> balance.freeze();
        },
        (a, b) -> a
    );
}
```

**Optimistic Concurrency:**

```java
// Prevent lost updates: reject if another TX has modified the stream
eventStore.append(accountId, event, expectedVersion);
// Throws OptimisticConcurrencyException if actual version != expectedVersion
// Caller must retry with latest version
```

> 💬 **Follow-up:** How do you handle schema evolution in Event Sourcing? If `MoneyDebited` gains a new required field 6 months after events already exist without it, how do you deal with those old events during replay?

[↑ Back to top](#-table-of-contents)

---

### 🗄️ DB / JPA

---

#### Q53. JPA Queries Causing Database Deadlocks

> **Difficulty:** Hard · **Topic:** DB / JPA · **Role:** Architect

**Scenario:** Under 200 concurrent users, `"Deadlock found when trying to get lock"` appears every few minutes. Operations: update order status + update inventory count, happening simultaneously in different transactions.

**Why Deadlocks Occur:**

```
Transaction A:              Transaction B:
  LOCK Order(1)               LOCK Inventory(5)
  waiting for                 waiting for
  LOCK Inventory(5)           LOCK Order(1)
  ↑                           ↑
  ← ← ← DEADLOCK → → → → → → ┘
```

**Fix 1 — Consistent Lock Ordering (most important):**

```java
// Always acquire locks in same global order: Order → Inventory → Payment
// NEVER lock Inventory first in one transaction and Order first in another
@Transactional
public void processOrder(long orderId, long inventoryId) {
    // Lower ID first — deterministic ordering prevents circular wait
    long firstId = Math.min(orderId, inventoryId);
    long secondId = Math.max(orderId, inventoryId);
    
    // Both transactions always acquire locks in same order
    em.find(Entity.class, firstId, LockModeType.PESSIMISTIC_WRITE);
    em.find(Entity.class, secondId, LockModeType.PESSIMISTIC_WRITE);
}
```

**Fix 2 — Optimistic Locking (better for low-contention):**

```java
@Entity
public class Inventory {
    @Version
    private int version; // JPA automatically checks + increments on UPDATE
}

// On concurrent update: JPA throws OptimisticLockException
// Retry with @Retryable
@Retryable(retryFor = OptimisticLockException.class, maxAttempts = 3, 
           backoff = @Backoff(delay = 100, multiplier = 2))
@Transactional
public void updateInventory(long id, int delta) {
    Inventory inv = inventoryRepo.findById(id).orElseThrow();
    inv.setQuantity(inv.getQuantity() - delta);
    inventoryRepo.save(inv); // throws OptimisticLockException if version mismatch
}
```

**Fix 3 — Reduce Transaction Scope:**

```java
// ❌ Long transaction holding locks while doing HTTP calls
@Transactional
public void processOrder(Order order) {
    inventoryRepo.reserve(order);      // LOCK acquired
    String trackingId = shippingApi.createShipment(order); // external HTTP — holds lock!
    orderRepo.updateTracking(trackingId);                   // lock finally released
}

// ✅ Short transaction — release DB lock before external call
@Transactional
public void reserveInventory(Order order) {
    inventoryRepo.reserve(order); // lock acquired and released here
}

public void processOrder(Order order) {
    reserveInventory(order); // transaction completes, lock released
    String trackingId = shippingApi.createShipment(order); // no DB lock held
    orderRepo.updateTracking(trackingId); // new short transaction
}
```

> 💬 **Follow-up:** When does optimistic locking actually perform *worse* than pessimistic? Describe the high-contention scenario where optimistic locking causes a thundering herd of retries.

[↑ Back to top](#-table-of-contents)

---

#### Q54. Multi-Tenant Data Isolation in SaaS

> **Difficulty:** Hard · **Topic:** DB / JPA · **Role:** Architect

**Scenario:** B2B SaaS with 500 tenants (100–50,000 rows each). Customer A must never see Customer B data. Some tenants need custom columns. Design the isolation strategy.

**Three Models — Trade-offs:**

| Model | Isolation | Cost | Custom Columns | Best For |
|-------|-----------|------|----------------|---------|
| **DB per tenant** | ✅ Strongest | ❌ Expensive ($) | ✅ Easy | < 50 enterprise tenants |
| **Schema per tenant** | ✅ Strong | ⚠️ Moderate | ✅ Easy | 50–500 tenants |
| **Shared table + tenant_id** | ⚠️ Weakest | ✅ Cheapest | ❌ Hard | 500+ smaller tenants |

**Recommended: Row-Level Security (Postgres):**

```sql
-- Enable RLS — database enforces isolation, not application code
ALTER TABLE orders ENABLE ROW LEVEL SECURITY;
ALTER TABLE orders FORCE ROW LEVEL SECURITY;  -- applies to table owner too

CREATE POLICY tenant_isolation ON orders
    USING (tenant_id = current_setting('app.tenant_id')::uuid);

-- This makes data leak from bugs nearly impossible
-- Even if app code omits WHERE clause, DB filters automatically
```

```java
// Spring Boot: set tenant context per-request
@Component
public class TenantContextFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ...) throws IOException, ServletException {
        String tenantId = jwtService.extractTenantId((HttpServletRequest) request);
        
        try {
            TenantContext.setTenantId(tenantId);
            dataSource.setCurrentTenantId(tenantId); // set for this request's connection
            chain.doFilter(request, response);
        } finally {
            TenantContext.clear(); // MUST clean up (thread pool reuse)
        }
    }
}
```

**Custom Columns per Tenant — JSONB Approach:**

```sql
-- Add tenant_metadata JSONB column to any table
ALTER TABLE orders ADD COLUMN tenant_metadata JSONB DEFAULT '{}';

-- GIN index for fast JSONB queries
CREATE INDEX CONCURRENTLY idx_orders_metadata ON orders USING GIN (tenant_metadata);

-- Tenant-specific query
SELECT * FROM orders WHERE tenant_metadata @> '{"contract_number": "CN-123"}';
```

> 💬 **Follow-up:** How do you perform cross-tenant analytics (average order value across all tenants) without violating isolation? What architectural pattern (data warehouse, event streaming) enables this safely?

[↑ Back to top](#-table-of-contents)

---

#### Q55. Database Sharding Strategy for 10 Billion Rows

> **Difficulty:** Senior · **Topic:** DB / JPA · **Role:** Architect

**Scenario:** `user_events` table grows by 100M rows/day. Queries already taking 30 seconds. You cannot afford downtime. Design the sharding approach: shard key choice, cross-shard queries, and zero-downtime migration.

**Step 1 — Choose Shard Key (Most Critical Decision):**

| Shard Key | Pros | Cons |
|-----------|------|------|
| `user_id` | Data locality (all user's data on same shard) | Hot shards if some users are huge |
| `event_time` | Good for time-series scans | Current time period = hot shard |
| `hash(user_id)` | Even distribution | No range queries; rebalancing is hard |
| `user_id % 256` | 256 logical shards → map to N physical nodes | Best: rebalance by moving logical shards |

**Recommended: Consistent Hashing with Virtual Nodes:**

```
256 logical shards → currently mapped to 4 physical DB nodes
Each node owns 64 logical shards
To scale: add node 5, move 20 logical shards to it (no full reshard)
```

**Vitess Configuration (Industry Standard for MySQL Sharding):**

```json
{
  "sharded": true,
  "vindexes": {
    "user_hash": { "type": "hash", "params": { "column": "user_id" } }
  },
  "tables": {
    "user_events": {
      "column_vindexes": [{ "column": "user_id", "name": "user_hash" }]
    }
  }
}
```

```sql
-- Vitess auto-routes based on shard key
SELECT * FROM user_events WHERE user_id = 123;
-- → Goes to shard hash(123) automatically

-- Cross-shard query (scatter-gather — expensive!)
SELECT COUNT(*) FROM user_events WHERE event_type = 'click';
-- → Sent to ALL shards, results merged → avoid or pre-aggregate
```

**Cross-Shard Query Strategy:**

```java
// For analytics: pre-aggregate to a separate analytics store
// Real-time: ClickHouse / BigQuery (columnar, built for this)
// Streaming: Kafka → Flink aggregation → Redis/Postgres read model

// For ad-hoc: scatter-gather with merge
List<CompletableFuture<Long>> shardCounts = shards.stream()
    .map(shard -> CompletableFuture.supplyAsync(() -> 
        shard.count("SELECT COUNT(*) FROM user_events WHERE event_type = ?", eventType)))
    .collect(Collectors.toList());

long total = CompletableFuture.allOf(shardCounts.toArray(new CompletableFuture[0]))
    .thenApply(v -> shardCounts.stream().mapToLong(CompletableFuture::join).sum())
    .join();
```

**Zero-Downtime Migration:**

```
Phase 1: Dual-write (both old DB and new sharded DB receive all writes)
Phase 2: Backfill historical data (batch jobs, rate-limited to avoid load)
Phase 3: Shadow read (compare old vs. new results silently)
Phase 4: Switch reads to new sharded DB (feature flag: 1% → 100%)
Phase 5: Remove dual-write after 2-week validation
```

**Global Unique IDs Across Shards:**

```
❌ Auto-increment: collides across shards (each shard starts at 1)
✅ Snowflake ID: 64-bit = timestamp(41) + datacenter(5) + machine(5) + sequence(12)
✅ ULID: lexicographically sortable + globally unique (26-char string)
✅ UUID v7: time-ordered UUID — sortable + globally unique (standard)
```

> 💬 **Follow-up:** How do you handle a JOIN across two tables that are sharded on different keys (e.g., `user_events` sharded by `user_id` and `products` sharded by `product_id`)?

[↑ Back to top](#-table-of-contents)

---

*End of 55 Questions — Java Full Developer / Architecture Engineer*

---

> **Tip for Interviewers:** For senior/architect roles, focus on Sections 2 and 3. Judge candidates not just on knowing the answer, but on their ability to describe the diagnostic process, trade-offs, and production impact.

> **Tip for Candidates:** For each scenario, structure your answer: (1) identify the root cause, (2) explain the immediate fix, (3) discuss the broader architectural implication, (4) mention monitoring/observability.

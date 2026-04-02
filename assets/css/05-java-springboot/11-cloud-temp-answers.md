# Java + Spring Boot + Microservices — Scenario-Based Interview Answers

---

## Java

---

## 1. Your service is slow but CPU and memory are normal. What do you check first?

I check for **I/O blocking** — the most common cause when CPU and memory look fine.

- Is the service waiting on a **slow database query**? I check slow query logs.
- Is it waiting on an **external API call** that's taking too long?
- Are threads **blocked on I/O** — file reads, network calls, or locks?
- I use tools like **thread dumps**, **APM tools (like Datadog or New Relic)**, or Spring Boot Actuator `/metrics` to find where time is being spent.

> "CPU and memory are fine means the machine isn't busy — it's *waiting*. I look for blocking I/O, slow DB queries, or external service timeouts."

---

## 2. Multiple threads update shared data and results become inconsistent. Why?

This is a **race condition** — multiple threads read and write shared data without proper synchronization.

- Thread A reads a value, Thread B reads the same value, both update it — one update gets lost.
- In Java, this happens when shared variables are accessed without `synchronized`, `volatile`, or atomic classes.

**Fix:**
```java
// Use AtomicInteger instead of plain int
AtomicInteger counter = new AtomicInteger(0);
counter.incrementAndGet(); // thread-safe

// Or use synchronized block
synchronized(this) {
    count++;
}
```

> "Without synchronization, threads interleave their reads and writes. The result depends on timing — that's a race condition."

---

## 3. Increasing thread pool size didn't improve performance. What could be the reason?

More threads don't help if the **bottleneck is not the CPU** — it's something else.

- **Database connection pool is the limit** — 100 threads but only 10 DB connections means 90 threads are waiting.
- **External API is slow** — more threads just means more threads waiting on the same slow endpoint.
- **Lock contention** — threads are blocking each other on synchronized code.
- **GC pressure** — too many threads create too many objects, causing frequent garbage collection.

> "Thread pool size only helps if threads are actually doing work. If they're all waiting on DB or I/O, adding more threads just adds more waiting threads."

---

## Spring Boot

---

## 4. API works locally but is slow in production. What might be different?

Several things differ between local and production:

- **Database** — local uses H2 or a local DB; production has network latency to RDS or a remote DB.
- **No connection pooling configured** — HikariCP defaults may not be tuned for production load.
- **Missing indexes** — local has small data, production has millions of rows.
- **N+1 query problem** — Hibernate fires one query per record; not visible with small local data.
- **Environment variables / config** — logging level set to DEBUG in production slows things down.
- **Cold start** — first request after deployment is always slow.

> "I check DB query count, connection pool config, and whether indexes exist on production data. Local data is small so problems hide there."

---

## 5. @Transactional is present but rollback doesn't happen. Why?

Common reasons:

1. **Checked exception** — by default, `@Transactional` only rolls back on `RuntimeException`. A checked exception won't trigger rollback unless you add `rollbackFor`.
```java
@Transactional(rollbackFor = Exception.class)
```

2. **Self-invocation** — calling a `@Transactional` method from within the same class bypasses the Spring proxy, so the transaction never starts.

3. **Method is not public** — Spring AOP only intercepts `public` methods.

4. **Exception is caught inside the method** — if you catch the exception and don't rethrow it, Spring never sees it.

> "Most common cause I've seen: checked exception thrown but `rollbackFor` not set, or the method calls itself internally — self-invocation bypasses the proxy."

---

## 6. Connection pool gets exhausted under load. What causes this?

- **Connections not released** — a transaction is opened but never committed or rolled back (often due to an exception that's swallowed).
- **Long-running transactions** — holding a connection for too long while doing non-DB work inside a transaction.
- **Pool size too small** — default HikariCP pool is 10; under high load that's not enough.
- **N+1 queries** — each request fires many queries, holding connections longer.

**Fix:**
```yaml
spring:
  datasource:
    hikari:
      maximum-pool-size: 20
      connection-timeout: 30000
      idle-timeout: 600000
```

> "I check if connections are being returned properly, look for long-running transactions, and tune HikariCP pool size based on load."

---

## Microservices

---

## 7. One slow service increases latency across the system. How do you handle it?

This is the **cascading latency** problem. I handle it with:

- **Timeouts** — set a max wait time on every service call. Don't wait forever.
- **Circuit Breaker (Resilience4j)** — if a service keeps failing or is slow, stop calling it and return a fallback.
- **Bulkhead** — isolate thread pools per service so one slow service doesn't consume all threads.
- **Async / non-blocking calls** — use reactive or async patterns so the caller isn't blocked.

```java
@CircuitBreaker(name = "paymentService", fallbackMethod = "fallback")
public String callPayment() { ... }

public String fallback(Exception e) {
    return "Payment service unavailable, try later";
}
```

> "Timeout + Circuit Breaker is the standard answer. Timeout stops waiting, circuit breaker stops calling when the service is unhealthy."

---

## 8. Retries between services start causing system overload. Why?

This is called a **retry storm**.

- Service B is slow → Service A retries → now Service B gets 3x the traffic → gets even slower → more retries → system collapses.
- If multiple services all retry simultaneously, the load multiplies across the system.

**Fix:**
- **Exponential backoff** — wait longer between each retry (1s, 2s, 4s...).
- **Jitter** — add randomness to retry timing so all instances don't retry at the same moment.
- **Max retry limit** — don't retry forever.
- **Circuit breaker** — stop retrying when the service is clearly down.

> "Retries without backoff turn a slow service into a completely dead one. Exponential backoff with jitter is the fix."

---

## 9. Circuit breaker remains open even when service is healthy. Why?

The circuit breaker has a **half-open state** — it lets a few test requests through to check if the service recovered. If those test requests fail, it stays open.

Reasons it stays open:

- **Health check threshold not met** — the service needs to pass a minimum number of successful calls before closing.
- **Slow response still above threshold** — service is responding but too slowly; slow calls count as failures.
- **Wrong configuration** — `waitDurationInOpenState` is too long, or `permittedNumberOfCallsInHalfOpenState` is too high.
- **Downstream dependency still unhealthy** — the service itself is up but its DB or dependency is still failing.

> "I check the Resilience4j config — specifically `waitDurationInOpenState` and the success threshold in half-open state. Also check if slow responses are being counted as failures."

---

## System Thinking

---

## 10. Adding more instances didn't improve performance. What might be the bottleneck?

Horizontal scaling only helps if the bottleneck is **compute**. If it's something else, more instances don't help.

- **Database is the bottleneck** — all instances share one DB; adding instances just adds more DB load.
- **Shared resource contention** — a single Redis, message queue, or external API that all instances hit.
- **Stateful sessions** — requests must go to a specific instance; load balancer can't distribute freely.
- **Network I/O limit** — bandwidth or DNS resolution is the ceiling.

> "I ask: what do all instances share? Usually it's the database. More app servers hitting the same DB just makes the DB slower."

---

## 11. Cache improved speed but caused inconsistent data. Why?

This is a **cache invalidation** problem — the cache has stale data that doesn't match the database.

- Data was updated in the DB but the cache still holds the old value.
- **TTL (Time To Live) is too long** — cache doesn't expire soon enough.
- **Cache not invalidated on write** — when data changes, the cache entry isn't removed or updated.
- **Multiple instances with local caches** — each instance has its own cache; one updates the DB but others still serve old data.

**Fix:**
- Use **write-through** or **cache-aside** pattern with proper invalidation on update.
- Use a **distributed cache** (Redis) instead of local in-memory cache.
- Set appropriate TTL based on how stale data is acceptable.

> "Cache consistency is hard. The fix is: on every write, either update or evict the cache entry. And use a shared cache like Redis, not local per-instance cache."

---

## 12. System works in testing but fails under real traffic. Why?

Testing environments don't replicate production conditions:

- **Volume** — test has 10 users, production has 10,000. Connection pools, thread pools, and queues get exhausted.
- **Data size** — test DB has 100 rows, production has 10 million. Queries that were fast now do full table scans.
- **Concurrency** — tests run sequentially; production has simultaneous requests causing race conditions and deadlocks.
- **Network latency** — test services are on the same machine; production services are across networks.
- **Resource limits** — memory leaks, file descriptor limits, or connection leaks only show up over time under load.
- **Config differences** — test uses H2, production uses MySQL; test has DEBUG logging, production should have INFO.

> "The gap is always load, data size, and concurrency. I recommend load testing with realistic data volumes before going to production — tools like JMeter or Gatling."

---

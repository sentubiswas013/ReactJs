
# 1. Observability & Tracing

### 1. A user reports Order API takes 20 seconds but each microservice team says their service is fast. How do you identify the delay?

**How to Identify**

* Use **Distributed Tracing** tools like **Zipkin**, **Jaeger**, or **OpenTelemetry**.
* Add a **Trace ID** to track the request across all services.
* Check the **request timeline** to see where most time is spent.
* Analyze **API Gateway**, **service-to-service calls**, **database queries**, and **external API calls**.
* Review **logs**, **metrics**, and **APM dashboards**.

**Common Reasons**

* **Network latency** between services.
* **Slow database queries**.
* Multiple **sequential service calls** instead of parallel calls.
* **External API delays**.
* **Thread pool exhaustion** or resource contention.
* **Message queue** backlog.
* High **serialization/deserialization** time.

**How to Resolve**

* Optimize **slow queries** and add proper indexes.
* Convert **sequential calls** to **parallel processing** where possible.
* Add **caching** for frequently accessed data.
* Use **async communication** for non-critical operations.
* Increase or tune **thread pools** and connection pools.
* Reduce unnecessary **service hops**.
* Monitor continuously with **tracing** and **APM tools**.


### 2. A request passes through API Gateway → Order → Payment → Inventory. How do you trace the complete flow across services?

**How to Identify**

* Use **Distributed Tracing** tools like **OpenTelemetry**, **Zipkin**, or **Jaeger**.
* Generate a unique **Trace ID** at the **API Gateway**.
* Pass the same **Trace ID** through **Order**, **Payment**, and **Inventory** services.
* Search the **Trace ID** to view the complete request journey and timings.

**Common Reasons**

* Missing **Trace ID propagation**
* Slow **database queries**
* Delayed **external API** calls
* High **network latency**
* Service **timeouts** or **retries**

**How to Resolve**

* Enable tracing in all microservices.
* Ensure **Trace ID** is forwarded in every request.
* Analyze slow **spans** to find bottlenecks.
* Optimize slow services, queries, or external calls.
* Correlate **logs**, **metrics**, and **traces** using the same **Trace ID**.


### 3. Only 5% of requests are failing in production. How does distributed tracing help find the root cause?

**How to Identify**

* Use **Distributed Tracing** (**OpenTelemetry**, **Zipkin**, **Jaeger**) to trace both **successful** and **failed** requests.
* Filter traces by **error status**, **HTTP 5xx**, or **exception type**.
* Compare failed traces with successful ones to identify where failures occur.

**Common Reasons**

* Intermittent **service failures**
* Random **database timeouts**
* Unstable **external API** calls
* Network or infrastructure issues
* Misconfigured **retries** or **circuit breakers**

**How to Resolve**

* Find the failing **service/span** in the trace.
* Check related **logs** using the same **Trace ID**.
* Fix the underlying issue such as timeout, exception, or dependency failure.
* Add proper **monitoring**, **alerts**, and **fallback mechanisms**.
* Use **sampling** and error-based tracing to capture more failed requests for analysis.


### 4. After introducing OpenTelemetry, traces are missing for some services. What would you investigate?

**How to Identify**

* Verify that **OpenTelemetry instrumentation** is enabled in all services.
* Check whether the **Trace ID** is being propagated between services.
* Review **OpenTelemetry Collector** and backend logs for dropped traces.
* Compare service logs to see where the trace chain breaks.

**Common Reasons**

* Missing **OpenTelemetry dependency** or configuration.
* **Trace ID propagation** not configured correctly.
* Low **sampling rate** dropping traces.
* Network issues between services and the **Collector**.
* Incorrect exporter or backend configuration.

**How to Resolve**

* Enable OpenTelemetry in every service.
* Ensure **Trace Context** headers are forwarded across requests.
* Increase or verify **sampling configuration**.
* Check Collector, exporter, and tracing backend connectivity.
* Validate traces end-to-end using a known **Trace ID**.


### 5. An error occurs in production but devs can't reproduce it locally. How do you use ELK/Splunk to investigate?

**How to Identify**

* Search **error logs**, **exceptions**, and **stack traces** in **ELK/Splunk**.
* Filter logs by **timestamp**, **service name**, **request ID**, or **user ID**.
* Correlate logs across multiple services to trace the failing request.

**Common Reasons**

* Production-only **configuration issues**
* Unexpected **data conditions**
* External service or **database failures**
* High load causing **timeouts**
* Environment differences between **QA** and **Production**

**How to Resolve**

* Identify the exact **exception** and affected service from logs.
* Compare **Production** and **QA** configurations.
* Fix the root cause such as invalid data, timeout, or dependency failure.
* Add better **logging**, **monitoring**, and **alerts** for faster detection.
* Reproduce the issue using production-like data and conditions.


### 6. A customer provides an Order ID and says payment failed. How do you find all related logs across services?

**How to Identify:**

* Start with the **Order ID** provided by the customer.
* Search the centralized logging tool (like **ELK**, **Splunk**, or **Grafana Loki**) using the **Order ID**, **Correlation ID**, or **Trace ID**.
* Follow the same **Trace ID** across all microservices (Order, Payment, Inventory, Notification) to see the complete request flow.

**Common Reasons:**

* **Payment gateway timeout**
* **Network or service communication failure**
* **Database or transaction rollback**
* **Third-party API error**
* Missing or incorrect **Correlation/Trace ID** in logs

**How to Resolve:**

* Trace the request using the **Correlation ID/Trace ID** to find where it failed.
* Check the error logs and stack traces in the failing service.
* Verify downstream dependencies like the **payment gateway** or **database**.
* Fix the root cause, retry or replay the failed request if supported, and ensure proper **distributed tracing** and **structured logging** are enabled for easier debugging in the future.


### 7. Application performance suddenly degrades. What logs would you analyze first?

**How to Identify:**

* First, check the **application logs** for errors or slow requests.
* Review **GC (Garbage Collection) logs**, **CPU/Memory usage logs**, and **database slow query logs**.
* Check **web server/load balancer logs** and **distributed tracing logs** to identify bottlenecks across services.

**Common Reasons:**

* **High CPU or memory usage**
* **Excessive Garbage Collection (GC)**
* **Slow database queries**
* **External API or downstream service latency**
* **Thread pool or connection pool exhaustion**

**How to Resolve:**

* Identify the slow component from the logs and metrics.
* Optimize **database queries**, increase **thread/connection pool** limits if needed, and fix **memory leaks** or GC issues.
* Check and recover any slow external dependencies, then monitor the application after the fix to ensure performance is restored.


---

# 2. Kafka Debugging

### 8. Messages are being produced but consumers are not receiving them. How do you debug?

**How to Identify:**

* Check whether messages are successfully written to the **topic/queue**.
* Verify if the **consumer service** is running and connected.
* Review **broker logs**, **consumer logs**, and check the **consumer group lag/offsets**.

**Common Reasons:**

* **Consumer service is down**
* Wrong **topic/queue** or **consumer group** configuration
* **Consumer lag** due to slow processing
* **Offset** committed incorrectly
* Network or broker connectivity issues

**How to Resolve:**

* Ensure the **consumer is active** and subscribed to the correct topic.
* Check and reset **consumer offsets** if required.
* Analyze broker and consumer logs for connection or processing errors.
* Scale consumers or optimize processing to reduce **consumer lag**, then verify that messages are being consumed successfully.


### 9. Consumer lag suddenly increases during a sale event. What do you do?

**How to Identify:**

* Check the **consumer lag metrics** and monitor the **message queue/topic**.
* Verify if producers are sending messages faster than consumers can process them.
* Review **consumer logs**, **broker logs**, and **CPU/Memory usage**.

**Common Reasons:**

* **Traffic spike** during the sale event
* **Slow consumer processing**
* **Insufficient consumer instances**
* **Database or external API latency**
* **Partition imbalance** or resource bottlenecks

**How to Resolve:**

* **Scale out consumers** by adding more instances (if partitions allow).
* Optimize slow business logic, database queries, or external API calls.
* Check broker and consumer health, and rebalance partitions if needed.
* Continuously monitor **consumer lag** and system metrics until it returns to normal.

### 10. Duplicate messages are being processed. How do you prevent this?

**How to Identify:**

* Check if the same **Message ID**, **Order ID**, or **Event ID** is processed multiple times.
* Review **consumer logs** and verify if retries or redeliveries are occurring.
* Monitor message broker metrics for repeated deliveries.

**Common Reasons:**

* **Consumer retry** after a failure
* **Offset not committed** correctly
* **Broker redelivery** due to timeout
* Network failures causing the producer to resend messages

**How to Resolve:**

* Implement **idempotency** by processing each **unique Message ID** only once.
* Store processed message IDs in a **database** or **cache** to ignore duplicates.
* Commit **offsets** only after successful processing.
* Use **deduplication mechanisms** or **exactly-once processing** features (if supported by the messaging system).

### 11. One Kafka partition receives significantly more traffic than others. How do you fix this?

**How to Identify:**

* Check **Kafka metrics** and monitor the message count per **partition**.
* Compare **consumer lag**, throughput, and broker load across all partitions.
* Review the **partition key** used by the producer.

**Common Reasons:**

* Poor or non-uniform **partition key**
* A single **hot key** generating most of the traffic
* Too few partitions for the current workload
* Custom partitioning logic causing uneven distribution

**How to Resolve:**

* Choose a better **partition key** with higher cardinality for even distribution.
* Increase the number of **partitions** if required.
* Update or optimize the **custom partitioner** to balance traffic.
* Redistribute or split **hot keys** to avoid a single overloaded partition and monitor the partition distribution after the change.


---

# 3. Performance Analysis

### 12. API response time increased from 200ms to 5 seconds after deployment. How do you diagnose?

**How to Identify:**

* Compare **application metrics** and **response time logs** before and after the deployment.
* Check **application logs**, **APM/distributed tracing**, and **database slow query logs** to find where the delay occurs.
* Review **CPU, memory, GC logs**, and external API latency.

**Common Reasons:**

* New code introducing **inefficient logic**
* **Slow database queries** or missing indexes
* **External API** or downstream service latency
* **Memory/GC issues** or thread pool exhaustion
* Incorrect **configuration** after deployment

**How to Resolve:**

* Use logs and tracing to identify the slow component.
* Roll back the deployment if the issue is critical.
* Optimize the new code, fix slow queries, and verify configuration changes.
* Monitor the application after the fix to ensure the API response time returns to normal.

### 13. CPU usage reaches 95% during peak traffic. How do you investigate and resolve?

**How to Identify:**

* Monitor **CPU metrics**, **application logs**, and **APM/tracing** to find the high-load component.
* Check **thread dumps**, **GC logs**, and identify which processes or threads are consuming the most CPU.
* Review database and external API response times for bottlenecks.

**Common Reasons:**

* **High traffic spike**
* **Inefficient code** or infinite loops
* **Excessive Garbage Collection (GC)**
* **Slow database queries** or external API delays
* **Thread contention** or resource exhaustion

**How to Resolve:**

* Identify and optimize the CPU-intensive code or slow queries.
* Tune **JVM/GC settings** and fix memory-related issues.
* Scale the application horizontally by adding more instances if needed.
* Continuously monitor CPU and application metrics to ensure the issue is resolved during peak traffic.

### 14. Application works fine in QA but becomes slow in Production. What differences do you check?

**How to Identify:**

* Compare **application logs**, **performance metrics**, and **APM traces** between **QA** and **Production**.
* Check differences in **CPU, memory, database performance**, and external service response times.
* Verify configuration and infrastructure settings.

**Common Reasons:**

* Different **hardware or resource allocation**
* **Production data volume** is much larger than QA
* Different **configuration** or JVM settings
* **Database indexing** or query performance issues
* Higher **traffic load** and external API latency

**How to Resolve:**

* Compare and align **configuration**, **JVM**, and environment settings.
* Optimize **database queries** and add missing indexes if needed.
* Increase resources or scale the application to handle production load.
* Perform **load testing** with production-like data to validate the fix before deployment.

### 15. A Spring Boot endpoint takes 800ms — your target is 100ms. Walk through your optimization process.

**How to Identify:**

* Measure the endpoint using **APM tools**, **application logs**, and **distributed tracing**.
* Break down the response time into **API logic**, **database queries**, **external API calls**, and **serialization**.
* Check **database slow query logs**, **CPU/Memory usage**, and **GC logs**.

**Common Reasons:**

* **Slow database queries** or missing indexes
* **External API latency**
* Inefficient business logic or unnecessary processing
* **N+1 query problem** in JPA/Hibernate
* Lack of **caching** or resource contention

**How to Resolve:**

* Optimize **SQL queries** and add proper **indexes**.
* Fix **N+1 queries** using `JOIN FETCH` or optimized fetching strategies.
* Add **caching** (Redis or in-memory) for frequently accessed data.
* Reduce unnecessary processing, use **asynchronous calls** where appropriate, and optimize external API interactions.
* Re-test with profiling tools and monitor until the endpoint consistently meets the **100ms** target.

### 16. Your application has high CPU usage during seemingly idle periods. Diagnose it.

**How to Identify:**

* Check **CPU metrics**, **application logs**, and monitor running threads.
* Capture and analyze **thread dumps**, **GC logs**, and **JVM metrics**.
* Use **APM/profiling tools** to identify methods or threads consuming CPU.

**Common Reasons:**

* **Infinite loops** or inefficient background tasks
* **Excessive Garbage Collection (GC)**
* Misconfigured **scheduled jobs** running too frequently
* **Thread pool** issues or busy waiting
* Memory leaks causing constant GC activity

**How to Resolve:**

* Analyze thread dumps to find CPU-intensive threads.
* Optimize or fix background jobs and remove unnecessary loops.
* Tune **JVM/GC settings** and resolve memory leaks.
* Adjust scheduled task frequency and monitor CPU usage after the changes to confirm the issue is resolved.

### 17. Your REST API endpoint takes 5 seconds to respond. How do you optimize it?

**How to Identify:**

* Use **APM tools**, **application logs**, and **distributed tracing** to find where the time is spent.
* Check **database slow query logs**, **external API calls**, and **CPU/Memory metrics**.
* Break down the response time into database, business logic, and network calls.

**Common Reasons:**

* **Slow database queries** or missing indexes
* **External API latency**
* Inefficient business logic or **N+1 query problem**
* Lack of **caching**
* High CPU, memory, or thread pool contention

**How to Resolve:**

* Optimize **SQL queries** and add proper **indexes**.
* Fix **N+1 queries** and reduce unnecessary processing.
* Use **caching** (Redis or in-memory) for frequently accessed data.
* Optimize or parallelize external API calls where appropriate.
* Monitor the endpoint after changes and verify that the response time meets the target.

---

# 4. Memory & JVM

### 18. Application memory usage continuously increases and never comes down. How do you debug a memory leak?

**How to Identify:**

* Monitor **heap memory usage**, **GC logs**, and **JVM metrics**.
* Capture and analyze a **heap dump** using tools like **MAT** or **VisualVM**.
* Check if objects are continuously increasing and not being garbage collected.

**Common Reasons:**

* **Memory leaks** due to unreleased object references
* Growing **collections, caches, or static variables**
* Improperly closed resources (connections, streams)
* Long-lived sessions or background tasks retaining objects

**How to Resolve:**

* Analyze the **heap dump** to identify the objects consuming memory.
* Remove unnecessary object references and clear unused collections or caches.
* Properly close resources and fix long-lived object retention.
* Tune **JVM/GC settings** if needed and monitor memory usage after the fix to ensure it remains stable.

### 19. Your Java service OOMs after 3 days. GC logs show full GC every 5 minutes reclaiming less each time. What's wrong?

**How to Identify:**

* Review **GC logs** and observe that **Full GC** runs frequently but frees less memory each time.
* Monitor **heap usage** and capture a **heap dump** to see which objects keep growing.
* Analyze the heap dump using **MAT** or **VisualVM**.

**Common Reasons:**

* **Memory leak** caused by objects that are still strongly referenced
* Growing **collections, caches, or static variables**
* Unclosed resources or long-lived sessions
* Background tasks retaining objects in memory

**How to Resolve:**

* Analyze the **heap dump** to identify the objects preventing garbage collection.
* Remove unnecessary object references and fix leaking collections or caches.
* Properly close resources and review long-running background processes.
* After fixing the leak, monitor **GC behavior** and heap usage to confirm that Full GC is reclaiming memory effectively and the **OOM** issue is resolved.



### 20. Your application has 2-second GC pauses affecting user experience. How do you reduce them?

**How to Identify:**

* Analyze **GC logs** and monitor **GC pause times**.
* Check **heap usage**, **allocation rate**, and **JVM metrics**.
* Use **APM/profiling tools** to identify excessive object creation.

**Common Reasons:**

* **Small or poorly tuned heap size**
* Excessive **short-lived object creation**
* Inefficient **GC configuration**
* **Memory leaks** causing frequent Full GC

**How to Resolve:**

* Tune **JVM heap** and **GC settings** (for example, use **G1GC** or **ZGC** for low-latency applications).
* Reduce unnecessary object creation and optimize memory usage.
* Fix memory leaks and clear unused caches or collections.
* Continuously monitor **GC pause times** and heap metrics to ensure the pauses are reduced and user experience improves.

### 21. You're using ThreadLocal in a web application and seeing memory leaks after deployments. Why?

**How to Identify:**

* Monitor **heap memory** and observe that it keeps increasing after redeployments.
* Analyze a **heap dump** and check for objects retained by **`ThreadLocal`**.
* Look for application server worker threads that still hold old `ThreadLocal` values.

**Common Reasons:**

* **`ThreadLocal` values are not removed** after request processing.
* **Thread pools reuse threads**, so old objects remain attached to long-lived threads.
* After deployment, old class loader references are retained through `ThreadLocal`, causing a **class loader memory leak**.

**How to Resolve:**

* Always clear `ThreadLocal` values in a `finally` block using **`threadLocal.remove()`**.
* Avoid storing large or long-lived objects in `ThreadLocal`.
* Use frameworks or filters/interceptors that automatically clean up `ThreadLocal` data after each request.
* Verify the fix by checking **heap dumps** and ensuring memory is released correctly after deployments.


### 22. You find thousands of threads running in production. How do you investigate?

**How to Identify:**

* Check **JVM metrics** and **thread count** monitoring.
* Capture and analyze **thread dumps** (`jstack`) to identify thread states (**RUNNABLE**, **WAITING**, **BLOCKED**).
* Review **application logs** and **thread pool metrics** to find abnormal thread creation.

**Common Reasons:**

* **Thread leak** due to threads not being terminated
* Misconfigured or unbounded **thread pools**
* Threads blocked by **deadlocks** or slow I/O operations
* Excessive creation of new threads instead of reusing a pool

**How to Resolve:**

* Analyze **thread dumps** to identify the source of excessive threads.
* Fix thread leaks and ensure threads are properly closed after use.
* Use a properly sized **thread pool** (`ExecutorService`) instead of creating threads manually.
* Resolve deadlocks or blocking operations, and continuously monitor thread count after the fix.


### 23. Your Java application is running slowly and consuming increasing memory over time. How do you diagnose and fix it?

**How to Identify:**

* Monitor **heap memory**, **CPU usage**, and **GC logs** for abnormal behavior.
* Capture **heap dumps** and **thread dumps** to analyze memory and thread activity.
* Use **APM/profiling tools** to identify slow methods and objects that keep growing.

**Common Reasons:**

* **Memory leak** due to unreleased object references
* Excessive **Garbage Collection (GC)** caused by growing heap usage
* Growing **collections, caches, or ThreadLocal** objects
* Inefficient code or background tasks consuming CPU and memory

**How to Resolve:**

* Analyze the **heap dump** to identify and fix memory leaks.
* Optimize or remove unnecessary object creation and clear unused caches or collections.
* Tune **JVM/GC settings** and optimize CPU-intensive code.
* Continuously monitor **memory usage**, **GC behavior**, and application performance to confirm the issue is resolved.


### 24. Your Java application is running out of memory gradually over days. You find heap dumps showing many HashMap instances. What could be the cause and how do you fix it?

**How to Identify:**

* Monitor **heap usage** and **GC logs** to confirm memory is continuously growing.
* Capture and analyze a **heap dump** using **MAT** or **VisualVM**.
* Check if **`HashMap`** objects are retaining large amounts of data and are not being garbage collected.

**Common Reasons:**

* **Memory leak** caused by `HashMap` entries that are never removed.
* Unbounded **caches** implemented using `HashMap`.
* **Static `HashMap`** variables holding references for the application's lifetime.
* Missing cleanup logic causing the map to grow indefinitely.

**How to Resolve:**

* Analyze the heap dump to identify which **`HashMap`** is growing and what objects it holds.
* Remove unused entries or implement proper cleanup logic.
* Replace unbounded `HashMap` caches with **bounded caches** (for example, LRU or cache libraries with eviction policies).
* Avoid unnecessary **static collections** and monitor heap usage after the fix to ensure memory remains stable.

---

# 5. Concurrency & Multithreading

### 25. Your application suddenly stops responding. You suspect a deadlock. How do you detect and fix it?

**How to Identify**

* Take a **Thread Dump** using **jstack** or **jcmd**.
* Look for threads in **BLOCKED** state waiting for each other.
* Check logs for **deadlock detected** messages.
* Use monitoring tools like **VisualVM** or **JConsole**.

**Common Reasons**

* Threads acquiring locks in a different order.
* Nested **synchronized** blocks.
* Multiple threads waiting for resources held by each other.
* Improper use of **Locks** and shared resources.

**How to Resolve**

* Always acquire locks in a **consistent order**.
* Reduce lock scope and avoid nested locking.
* Use **ReentrantLock** with timeout (`tryLock()`).
* Review thread dump, identify conflicting threads, and refactor the locking logic.
* Prefer concurrent collections like **ConcurrentHashMap** where possible.


### 26. You're using HashMap in a multithreaded application and experiencing data corruption. What's wrong?

**How to Identify**

* Data is **missing**, **overwritten**, or inconsistent.
* Random behavior occurs under high concurrency.
* Multiple threads are reading and writing the same **HashMap**.
* Thread dumps show concurrent access to shared data.

**Common Reasons**

* **HashMap** is **not thread-safe**.
* Concurrent updates can corrupt the internal data structure.
* Race conditions occur when multiple threads modify the map simultaneously.
* Reads and writes happen without proper synchronization.

**How to Resolve**

* Replace **HashMap** with **ConcurrentHashMap**.
* Use **synchronized** blocks if shared access must be controlled.
* Avoid modifying the same map from multiple threads without protection.
* Use thread-safe collections from `java.util.concurrent` for concurrent access.


### 27. You get `ConcurrentModificationException` while iterating and removing from an ArrayList. How do you fix it?

**How to Identify**

* Application throws **`ConcurrentModificationException`** during iteration.
* Elements are being removed inside a **for-each loop**.
* The collection is modified while it is being traversed.

**Common Reasons**

* Removing elements directly from an **ArrayList** during iteration.
* One thread modifies the collection while another is iterating.
* Using `list.remove()` inside a **for-each loop**.

**How to Resolve**

* Use an **Iterator** and call `iterator.remove()`.
* Use **`removeIf()`** for conditional removal.
* For concurrent access, use thread-safe collections like **CopyOnWriteArrayList**.

**Code Example**

```java
Iterator<String> it = list.iterator();

while (it.hasNext()) {
    if (it.next().equals("test")) {
        it.remove(); // Safe removal
    }
}
```

```java
list.removeIf(item -> item.equals("test"));
```


### 28. You're using `stream().parallel()` but it's slower than sequential. Why?

**How to Identify**

* **Parallel Stream** takes longer than a normal stream.
* CPU usage increases, but performance does not improve.
* Profiling shows excessive thread management overhead.

**Common Reasons**

* Dataset is too **small** for parallel processing.
* Tasks are **I/O-bound** instead of CPU-bound.
* High overhead from **thread creation** and coordination.
* Contention in the **ForkJoinPool**.
* Shared resources causing **locking** or synchronization delays.

**How to Resolve**

* Use **parallel streams** only for large, **CPU-intensive** workloads.
* Benchmark using **JMH** before choosing parallel processing.
* Avoid shared mutable state and synchronization.
* Use a custom **ExecutorService** if the common **ForkJoinPool** is overloaded.
* For small datasets, prefer a **sequential stream**.


### 29. You need to implement a producer-consumer pattern for processing 1 million records efficiently.

**How to Identify:**

* Check if a single thread cannot handle the workload and there is a need for **parallel processing**.
* Monitor **throughput**, **queue size**, and **consumer processing rate**.

**Common Reasons:**

* Sequential processing becomes a **performance bottleneck**.
* **Consumers are slower** than producers, causing queue buildup.
* Improper thread management or unbounded queues.

**How to Resolve:**

* Use a **Producer-Consumer pattern** with a **`BlockingQueue`** and a fixed-size **`ExecutorService`** thread pool.
* Let producers add records to the queue while multiple consumers process them concurrently.
* Tune the **thread pool size** and queue capacity based on CPU and system resources to achieve optimal throughput.
* Monitor queue size and processing metrics to ensure the system handles **1 million records** efficiently without resource exhaustion.

```java
BlockingQueue<Record> queue = new LinkedBlockingQueue<>();

// Producer
executor.submit(() -> {
    for (Record r : records) {
        queue.put(r);
    }
});

// Consumers
for (int i = 0; i < 5; i++) {
    executor.submit(() -> {
        while (true) {
            Record r = queue.take();
            process(r);
        }
    });
}
```


### 30. `CompletableFuture.supplyAsync()` for parallel processing performs worse than sequential. What's wrong?

**How to Identify:**

* Compare **parallel vs sequential execution time**.
* Monitor **CPU usage**, **thread pool utilization**, and **thread contention**.
* Check if tasks are **CPU-bound** or **I/O-bound** and review the thread pool being used.

**Common Reasons:**

* Too many small tasks causing **thread management overhead**.
* Using the default **`ForkJoinPool.commonPool()`**, which may be overloaded.
* **Thread pool exhaustion** or excessive context switching.
* Blocking I/O operations inside `supplyAsync()` reducing parallelism.

**How to Resolve:**

* Use a **custom `ExecutorService`** with an appropriate thread pool size instead of the default common pool.
* Avoid creating too many tiny tasks; **batch** or combine work where possible.
* Separate **CPU-bound** and **I/O-bound** workloads into different thread pools.
* Profile and tune the application to ensure that parallel processing provides a real performance benefit over sequential execution.

---

# 6. Spring Boot & Transactions

### 31. You added `@Transactional` to a method but transactions are not being created. What could be the reason?


### 32. A `@Transactional` method catches Exception and doesn't rethrow. Transaction doesn't rollback. Why?


### 33. Service A depends on Service B, and Service B depends on Service A. How do you resolve the circular dependency?


### 34. You notice 1000 database queries when loading 100 entities. How do you fix this?


### 35. You're using constructor injection and get `BeanCurrentlyInCreationException`. How do you fix it?

---

# 7. Database & Connection Pool

### 36. Application throws "Cannot get JDBC connection" errors intermittently. What do you check?

### 37. Connection pool becomes exhausted during peak traffic. How do you diagnose and fix?

### 38. After deployment, database connections continuously increase and never decrease. What's wrong?

### 39. You have a table with 1 billion records and queries take 10+ seconds. How do you optimize?

### 40. You need to add a new column to a table with 1 billion records without downtime. How?

---

# 8. Microservices Patterns

### 41. Service A calls Service B, but Service B is down. How do you handle this gracefully?

### 42. How do you design a circuit breaker for inter-service communication with fallback behavior?

### 43. How do you implement the Saga pattern for a distributed order transaction?

### 44. You need to transfer money between two microservices. How do you ensure data consistency?

### 45. How do you implement distributed tracing across 8 microservices, including async Kafka boundaries?

---

# 9. API Gateway & Service Discovery

### 46. All backend services are healthy, but users receive 502/504 errors. How do you investigate?

### 47. Authentication works directly against the service but fails through the Gateway. Why?

### 48. A service registers successfully in Eureka but cannot be discovered by other services.

### 49. Inter-service communication works locally but fails in Kubernetes. What could be wrong?

---

# 10. Architecture & System Design

### 50. How would you handle a sudden spike from 10K to 1M RPS? (Black Friday scenario)

### 51. Design a rate-limiting system for an API gateway serving 50,000 RPS.

### 52. How would you migrate a monolith to microservices without downtime?

### 53. Design an event-driven notification system for 10 million users with delivery guarantees.

### 54. How do you design an idempotent REST API for payment processing?

### 55. Design a CQRS + Event Sourcing system for an auditable financial ledger.

---

# 11. DevOps & Production

### 56. You deployed a new version but it's causing errors in production. What do you do first?

### 57. Your Kubernetes pods are crashing repeatedly. How do you debug?

### 58. How do you implement blue-green and canary deployments in a Java microservice fleet?

### 59. Production application suddenly becomes unavailable at midnight every day. How do you diagnose?

### 60. Your integration tests are failing intermittently in CI/CD. How do you fix flaky tests?

---

# 12. Core Java & Design Patterns

### 61. Why does `Integer a = 127 == Integer b = 127` print `true`, but `128` print `false`?

### 62. You implemented a singleton but multiple instances are created in multithreaded tests. What's wrong?

### 63. You're using a custom object as HashMap key and after modifying a field, you can't retrieve the value. Why?

### 64. You're using Java 8 Streams and get a `NullPointerException`. How do you prevent it?

### 65. You're using `Optional` but getting `NullPointerException`. What are common mistakes?

### 66. After deploying a new version, you get `ClassNotFoundException` for a library that was working before. What's wrong?

### 67. How would you implement reactive programming in a Spring WebFlux service?

### 68. How do you use Java 21 virtual threads to prevent thread starvation in mixed I/O and CPU workloads?

### 69. Why does `Integer a = 127; Integer b = 127; System.out.println(a == b);` print `true`, but `Integer c = 128; Integer d = 128; System.out.println(c == d);` print `false`?


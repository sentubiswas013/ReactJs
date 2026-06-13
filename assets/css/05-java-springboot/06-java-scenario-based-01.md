
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

**How to Identify**

* Check if the method is being called through a **Spring Proxy**.
* Enable **transaction logs** and verify whether a transaction is started.
* Check if the bean is managed by **Spring Container**.

**Common Reasons**

* **Self-invocation** (method inside the same class calls another `@Transactional` method).
* Method is **private**, **static**, or **final**.
* Class is created using **new** instead of Spring dependency injection.
* Missing **@EnableTransactionManagement** or transaction configuration.
* Wrong **TransactionManager** configuration.

**How to Resolve**

* Call the method through a **Spring-managed bean**.
* Use **public** methods for `@Transactional`.
* Avoid **self-invocation** or move transactional logic to another service.
* Ensure proper **Spring Transaction** configuration.
* Verify the correct **PlatformTransactionManager** is configured.

**Key Point:** `@Transactional` works through **Spring AOP Proxies**. If the call bypasses the proxy, the transaction will not be created.


### 32. A `@Transactional` method catches Exception and doesn't rethrow. Transaction doesn't rollback. Why?

**How to Identify**

* Data is still **committed** even though an exception occurred.
* No **rollback** messages appear in transaction logs.
* Exception is handled inside the method and never reaches Spring.

**Common Reasons**

* **Spring** rolls back transactions only when it detects an exception leaving the method.
* If the exception is **caught and swallowed**, Spring assumes the method completed successfully.
* By default, rollback happens for **RuntimeException** and **Error**, not checked exceptions.

**How to Resolve**

* **Rethrow** the exception after logging it.
* Use `rollbackFor = Exception.class` for checked exceptions.
* Manually mark the transaction for rollback if you must handle the exception.

```java
@Transactional
public void process() {
    try {
        // business logic
    } catch (Exception e) {
        throw e; // rethrow for rollback
    }
}
```

Or:

```java
@Transactional(rollbackFor = Exception.class)
public void process() throws Exception {
    // business logic
}
```

**Key Point:** If an exception is **caught and not rethrown**, Spring sees the transaction as **successful** and performs a **commit instead of a rollback**.


### 33. Service A depends on Service B, and Service B depends on Service A. How do you resolve the circular dependency?

**How to Identify**

* Application fails to start with **Circular Dependency** or **BeanCurrentlyInCreationException**.
* Service A requires Service B, and Service B requires Service A during initialization.

**Common Reasons**

* Poor **service design** where two services directly depend on each other.
* Using **constructor injection** on both sides.
* Business logic is tightly coupled between services.

**How to Resolve**

* **Refactor** and move shared logic into a separate service.
* Use **event-driven communication** instead of direct dependency.
* Use **@Lazy** as a temporary workaround.
* Prefer redesigning dependencies to avoid circular references.

```java
@Service
public class ServiceA {

    private final ServiceB serviceB;

    public ServiceA(@Lazy ServiceB serviceB) {
        this.serviceB = serviceB;
    }
}
```

**Key Point:** The best solution is usually **refactoring the design** to remove the circular dependency. **@Lazy** can help, but it should not replace a proper architectural fix.


### 34. You notice 1000 database queries when loading 100 entities. How do you fix this?

**How to Identify**

* Enable **SQL Logging** and check generated queries.
* Use **Hibernate Statistics**, **APM**, or database monitoring tools.
* Notice one query loads entities, then many additional queries load related data.

**Common Reasons**

* **N+1 Query Problem** caused by lazy-loaded relationships.
* Fetching related entities one by one inside a loop.
* Improper **JPA/Hibernate** fetch strategy.

**How to Resolve**

* Use **JOIN FETCH** to load related data in a single query.
* Use **EntityGraph** for optimized fetching.
* Fetch only required fields using **DTO Projections**.
* Review **Lazy** and **Eager** loading strategies.

```java
@Query("""
    SELECT o
    FROM Order o
    JOIN FETCH o.customer
    """)
List<Order> findAllWithCustomer();
```

**Key Point:** This is usually the **N+1 Query Problem**. Instead of executing **1 + 1000 queries**, use **JOIN FETCH**, **EntityGraph**, or **DTO Projections** to reduce it to **a single optimized query**.


### 35. You're using constructor injection and get `BeanCurrentlyInCreationException`. How do you fix it?

**How to Identify**

* Application fails to start with **BeanCurrentlyInCreationException**.
* Stack trace shows two or more beans depending on each other.
* Commonly occurs with **constructor injection**.

**Common Reasons**

* **Circular Dependency** between beans.
* Service A requires Service B, and Service B requires Service A.
* Spring cannot create either bean because both are waiting for each other.

**How to Resolve**

* **Refactor** the design and move shared logic to a separate service.
* Use **event-driven communication** if appropriate.
* Use **@Lazy** on one dependency as a temporary workaround.
* Consider **setter injection** if redesign is not immediately possible.

```java
@Service
public class ServiceA {

    private final ServiceB serviceB;

    public ServiceA(@Lazy ServiceB serviceB) {
        this.serviceB = serviceB;
    }
}
```

**Key Point:** `BeanCurrentlyInCreationException` is usually caused by a **circular dependency**. The best solution is to **remove the circular dependency through refactoring**, while **@Lazy** can be used as a short-term fix.

---

# 7. Database & Connection Pool

### 36. Application throws "Cannot get JDBC connection" errors intermittently. What do you check?

**What is the issue?**

The application is unable to obtain a **database connection** from the **connection pool**, causing requests to fail intermittently.

**How to Identify**

* Check application logs for **JDBC connection** errors.
* Monitor **connection pool metrics** (Active, Idle, Waiting connections).
* Check database logs for **connection limits** or failures.
* Verify database **CPU, memory, and network** health.
* Look for **long-running queries** holding connections.

**Common Reasons**

* **Connection pool exhaustion**
* **Connection leaks** (connections not closed)
* **Database overload**
* **Network instability**
* Incorrect **database credentials/configuration**
* Database reached **maximum connection limit**

**How to Resolve**

* Increase **connection pool size** if needed.
* Ensure connections are properly closed using **try-with-resources**.
* Fix **slow queries** and add proper indexing.
* Configure **connection timeout** and **idle timeout** settings.
* Monitor and fix **connection leaks**.
* Verify database capacity and network connectivity.

**Code Example**

```java
try (Connection conn = dataSource.getConnection();
     PreparedStatement ps = conn.prepareStatement(sql)) {

    ResultSet rs = ps.executeQuery();

} catch (SQLException e) {
    e.printStackTrace();
}
```

Using **try-with-resources** ensures connections are automatically closed and returned to the pool.


### 37. Connection pool becomes exhausted during peak traffic. How do you diagnose and fix?

**Common Symptoms**

* **High response time** or request timeout.
* Errors like **`Connection is not available`** or **`HikariPool - Connection is not available, request timed out`**.
* Threads stuck waiting for a database connection.
* Database shows many active or idle connections.

**How to Diagnose**

1. Check **application logs** for connection timeout errors.
2. Monitor pool metrics (**active, idle, pending connections**) using **Spring Boot Actuator, Micrometer, Prometheus, or Grafana**.
3. Take a **thread dump (`jstack`)** to see if threads are blocked waiting for connections.
4. Check the database for **long-running queries** using commands like `SHOW PROCESSLIST` (MySQL) or `pg_stat_activity` (PostgreSQL).
5. Verify that every connection is properly **closed and returned to the pool**.

**Common Causes**

* **Connection leak** (connections not closed).
* **Slow or unoptimized SQL queries**.
* **Long-running transactions**.
* **Pool size too small** for peak load.
* External API calls made **inside a database transaction**, keeping connections occupied.

**How to Fix**

* Always use **try-with-resources** or let **Spring `@Transactional`** manage connection lifecycle.
* **Optimize SQL queries** and add proper **indexes**.
* Keep **transactions short**; avoid network/API calls inside them.
* Tune pool settings (`maximumPoolSize`, `minimumIdle`, `connectionTimeout`) based on traffic and database capacity.
* Enable **leak detection** (for example, `leakDetectionThreshold` in **HikariCP**).
* Scale the application or database if the workload has genuinely increased.

**Example (HikariCP Configuration)**

```properties
spring.datasource.hikari.maximum-pool-size=30
spring.datasource.hikari.minimum-idle=10
spring.datasource.hikari.connection-timeout=30000
spring.datasource.hikari.leak-detection-threshold=5000
```



### 38. After deployment, database connections continuously increase and never decrease. What's wrong?

This usually indicates a **database connection leak**, where connections are **opened but not properly closed**, so they are never returned to the **connection pool**.

**How to Identify**

* **Active database connections** keep increasing over time.
* Logs show **`Connection pool exhausted`** or **`Connection timeout`** errors.
* Monitor **HikariCP metrics** (`active`, `idle`, `pending` connections).
* Enable **`leakDetectionThreshold`** to detect leaked connections.

**Common Reasons**

* Connections are **not closed** due to missing `close()`.
* Missing **try-with-resources** block.
* **Long-running transactions** or blocked queries.
* External API calls made **inside `@Transactional`** methods.
* Improper manual connection handling.

**How to Resolve**

* Always use **try-with-resources** or let **Spring `@Transactional`** manage connections.
* Ensure every connection is **closed in a `finally` block** if managed manually.
* Keep **transactions short** and avoid external calls inside them.
* Enable **connection leak detection** and monitor pool metrics.
* Optimize **slow queries** to release connections faster.


### 39. You have a table with 1 billion records and queries take 10+ seconds. How do you optimize?

**How to Identify**

* Use **EXPLAIN Plan** to analyze query execution.
* Check for **Full Table Scans**.
* Monitor **query execution time** and database metrics.
* Identify **slow queries** using database logs.

**Common Reasons**

* Missing or incorrect **Indexes**
* **Full Table Scan** on large tables
* Poorly written **SQL queries**
* Too much data being fetched
* Lack of **Partitioning**
* Outdated database **Statistics**

**How to Resolve**

* Create appropriate **Indexes** on frequently searched columns.
* Optimize and rewrite **SQL queries**.
* Use **Pagination** instead of loading all records.
* Implement **Table Partitioning** for large datasets.
* Select only required columns instead of `SELECT *`.
* Update database **Statistics** regularly.
* Use **Caching** for frequently accessed data.

**Key Optimization Techniques**

* **Indexing**
* **Partitioning**
* **Query Optimization**
* **Caching**
* **Pagination**
* **Database Tuning**


### 40. You need to add a new column to a table with 1 billion records without downtime. How?

**How to Identify**

* Table contains **billions of records**.
* Schema change may cause **table locks**.
* Application requires **zero downtime** during deployment.

**Common Reasons**

* Direct schema changes can trigger **long table locks**.
* Updating all existing rows at once can cause **performance issues**.
* Large tables require **careful migration planning**.

**How to Resolve**

* Add the new column as **NULLABLE** first to avoid rewriting the entire table.
* Deploy application code that can handle both **old and new schemas**.
* Backfill data in **small batches** instead of a single update.
* Add **default values** later if required.
* Use **online schema migration** tools supported by the database.
* Monitor database performance throughout the migration.

**Best Practice**

1. **Add nullable column**
2. **Deploy application changes**
3. **Backfill data in batches**
4. **Add constraints/defaults if needed**
5. **Remove old code after migration**

---

# 8. Microservices Patterns

### 41. Service A calls Service B, but Service B is down. How do you handle this gracefully?

**How to Identify**

* API calls to **Service B** are failing.
* Increased **timeouts** and **error rates**.
* Monitoring and logs show **Service B unavailable**.

**Common Reasons**

* **Service outage**
* **Network issues**
* **High traffic** causing overload
* **Deployment failure**
* Dependency or infrastructure problems

**How to Resolve**

* Use a **Circuit Breaker** to stop repeated failed calls.
* Configure **Timeouts** to fail fast.
* Implement **Retries** with backoff for temporary failures.
* Return a **Fallback Response** when possible.
* Use **Message Queues** for asynchronous processing.
* Monitor and alert on service health.

**Example**

If **Order Service** calls **Payment Service** and Payment Service is down:

* Return **"Payment processing temporarily unavailable"**
* Save the request for **retry**
* Prevent cascading failures using a **Circuit Breaker**

**Key Patterns**

* **Circuit Breaker**
* **Retry**
* **Timeout**
* **Fallback**
* **Bulkhead**
* **Queue-Based Processing**


### 42. How do you design a circuit breaker for inter-service communication with fallback behavior?

A **Circuit Breaker** is a design pattern that **stops calling a failing service** after a certain number of failures and returns a **fallback response** instead. This prevents **cascading failures** and improves system stability.

**How it works:**

* **Closed State:** Requests flow normally.
* **Open State:** After the failure threshold is reached, requests are blocked and the fallback method is executed.
* **Half-Open State:** After a timeout, a few requests are allowed to check if the service has recovered.

**Fallback Behavior:**

* Return a **default response**.
* Fetch **cached data**.
* Call an **alternative service**.
* Show a **graceful error message** to the user.

**How to Identify:**

* Frequent **timeouts** or **connection errors** between services.
* One failed service causes **multiple dependent services** to slow down or fail.
* High **retry traffic** increases system load.

**Common Reasons:**

* Downstream service outage.
* Network latency or instability.
* Database or third-party API failures.
* Excessive traffic causing service overload.

**How to Resolve:**

* Implement a **Circuit Breaker** using libraries like **Resilience4j** or **Hystrix**.
* Configure **failure threshold**, **timeout**, and **recovery interval**.
* Add a meaningful **fallback method**.
* Combine with **Retry**, **Timeout**, and **Bulkhead** patterns for better resilience.

**Simple Example (Resilience4j):**

```java
@CircuitBreaker(name = "paymentService", fallbackMethod = "fallback")
public String processPayment() {
    return paymentClient.pay();
}

public String fallback(Exception ex) {
    return "Payment service is temporarily unavailable. Please try again later.";
}
```


### 43. How do you implement the Saga pattern for a distributed order transaction?

The **Saga Pattern** is used to manage **distributed transactions** across multiple microservices without using a global database transaction. It breaks a transaction into **small local transactions**, and if one step fails, **compensating transactions** are executed to undo the previous successful steps.

**How it works:**

1. **Order Service** creates the order.
2. **Payment Service** processes the payment.
3. **Inventory Service** reserves the stock.
4. **Shipping Service** creates the shipment.
5. If any step fails, compensation actions are triggered (for example, **refund payment** and **release inventory**).

**How to Identify:**

* A business process involves **multiple microservices**.
* Each service has its **own database**.
* Data consistency is required without using **distributed locks** or **2PC (Two-Phase Commit)**.

**Common Reasons:**

* Need to maintain consistency across services.
* Distributed transactions are slow and tightly coupled.
* Failure in one service can leave partial data updates.

**How to Resolve:**

* Split the workflow into **local transactions**.
* Define a **compensating action** for every step.
* Use **event-driven communication** with a message broker like **Kafka** or **RabbitMQ**.
* Implement Saga using **Choreography** (events) or **Orchestration** (central coordinator).

**Simple Flow:**

```text
Create Order → Reserve Inventory → Process Payment → Ship Order
         ↓               ↓                ↓
      Cancel Order ← Release Stock ← Refund Payment (if failure)
```

### 44. You need to transfer money between two microservices. How do you ensure data consistency?

For **money transfer** between two microservices, I would use the **Saga Pattern** with **local transactions** and **compensating actions** instead of a distributed database transaction. This ensures **data consistency** and avoids partial updates.

**How it works:**

1. **Debit** the amount from the sender account.
2. Publish an **event** to the message broker.
3. **Credit** the amount to the receiver account.
4. If the credit step fails, execute a **compensating transaction** to **refund** the sender account.

**How to Identify:**

* A transaction spans **multiple microservices**.
* Each service has its **own database**.
* Partial updates can lead to **inconsistent financial data**.

**Common Reasons:**

* Service failure during transaction processing.
* Network timeout or communication issues.
* Duplicate requests due to retries.
* Message delivery failures.

**How to Resolve:**

* Implement the **Saga Pattern** with **compensating transactions**.
* Use the **Transactional Outbox Pattern** to reliably publish events.
* Make APIs and event processing **idempotent** to prevent duplicate transfers.
* Use a **message broker** like **Kafka** or **RabbitMQ** for reliable asynchronous communication.
* Add **retry** and **Circuit Breaker** mechanisms for temporary failures.

**Simple Flow:**

```text id="2l6s7k"
Debit Account → Publish Event → Credit Account
       ↓                            ↓
   Refund Account  ←  If Credit Fails
```


### 45. How do you implement distributed tracing across 8 microservices, including async Kafka boundaries?

I would use **Distributed Tracing** with a **Trace ID** and **Span ID** that are propagated across all microservices and **Kafka messages**. Tools like **OpenTelemetry**, **Jaeger**, or **Zipkin** help collect and visualize the complete request flow.

**How it works:**

1. The first service generates a **Trace ID**.
2. Each microservice creates its own **Span** and passes the Trace ID to the next service through **HTTP headers**.
3. For **Kafka**, include the Trace ID in the **message headers**.
4. Consumer services read the Trace ID from the Kafka headers and continue the same trace.

**How to Identify:**

* A request passes through **multiple microservices**.
* Logs from different services cannot be easily correlated.
* Async **Kafka events** make debugging difficult.

**Common Reasons:**

* Missing Trace ID propagation.
* Kafka producers/consumers not forwarding message headers.
* Independent logging without a common correlation ID.
* Asynchronous processing breaking the request chain.

**How to Resolve:**

* Use **OpenTelemetry** instrumentation in all services.
* Propagate **Trace ID** and **Span ID** through **HTTP** and **Kafka headers**.
* Integrate with **Jaeger** or **Zipkin** for trace visualization.
* Add the **Trace ID** to application logs for easy log correlation.

**Simple Flow:**

```text id="p5m8x2"
Client → Service A → Service B → Kafka → Service C → Service D
             │            │          │          │
        Trace ID ─────────────────────────────────▶ Same Trace
```
---

# 9. API Gateway & Service Discovery

### 46. All backend services are healthy, but users receive 502/504 errors. How do you investigate?

If all backend services are healthy but users see **502/504 errors**, I would first check the **API Gateway**, **Load Balancer**, and **network communication** between services because these errors usually indicate a **gateway or timeout issue**.

**How to Identify:**

* Backend service health checks are **passing**.
* Users receive **502 Bad Gateway** or **504 Gateway Timeout**.
* Application logs show no failures, but **gateway/load balancer logs** show timeouts or connection errors.

**Common Reasons:**

* **API Gateway** or **Load Balancer** timeout is too low.
* Network latency or connectivity issues between services.
* **Thread pool** or **database connection pool** exhaustion causing slow responses.
* DNS or service discovery problems.
* Misconfigured reverse proxy (e.g., **Nginx** or **Ingress Controller**).

**How to Resolve:**

* Check **API Gateway**, **Load Balancer**, and **Nginx/Ingress** logs.
* Verify **timeout settings** between gateway and backend services.
* Monitor **CPU**, **memory**, **thread pool**, and **database connection pool** usage.
* Check **network latency**, DNS resolution, and service discovery.
* Use **distributed tracing** and **request correlation IDs** to identify where the request is delayed.
* Review recent deployments or configuration changes and perform a rollback if needed.


### 47. Authentication works directly against the service but fails through the Gateway. Why?

If authentication works when calling the service directly but fails through the **API Gateway**, the issue is usually related to **token forwarding**, **gateway security configuration**, or **header propagation**.

**How to Identify:**

* Direct API calls succeed, but requests through the **Gateway** return **401 Unauthorized** or **403 Forbidden**.
* Authentication service is healthy, but the backend service does not receive the **Authorization** header.
* Gateway logs show authentication or routing failures.

**Common Reasons:**

* **Authorization header** is not being forwarded by the Gateway.
* Invalid or expired **JWT token**.
* Incorrect **Gateway security** or route configuration.
* Token validation keys or issuer configuration mismatch.
* **CORS** or header filtering configuration removing required headers.

**How to Resolve:**

* Verify that the **Authorization** header is forwarded to downstream services.
* Check **JWT token** validity, issuer, audience, and expiration.
* Review **API Gateway** security and routing configuration.
* Compare Gateway logs with backend service logs using a **Trace ID**.
* Validate **CORS** and header forwarding settings.
* Test the same request both directly and through the Gateway to identify where it fails.


### 48. A service registers successfully in Eureka but cannot be discovered by other services.

If a service is registered in **Eureka** but other services cannot discover it, the problem is usually related to **service registration**, **service discovery configuration**, or **network connectivity**.

**How to Identify:**

* The service appears in the **Eureka Dashboard** as **UP**.
* Direct access using IP/URL works, but **service-name-based** calls fail.
* Client logs show **`No instances available`** or **service not found** errors.

**Common Reasons:**

* Incorrect **`spring.application.name`** or service name mismatch.
* Consumer service is not configured with **`@EnableDiscoveryClient`** or Eureka client dependency.
* Stale or outdated **Eureka registry cache**.
* Network, DNS, or firewall issues between services.
* Wrong **hostname/IP** registration (for example, registering a local or unreachable address).

**How to Resolve:**

* Verify that **`spring.application.name`** matches the name used by the client.
* Check **Eureka client** configuration and ensure both services are connected to the same Eureka server.
* Refresh or restart the consumer service to update the **registry cache**.
* Validate the registered **hostname/IP** and use `prefer-ip-address=true` if required.
* Check network connectivity, firewall rules, and service-to-service communication.
* Review **Eureka server** and **client logs** for registration and discovery errors.


### 49. Inter-service communication works locally but fails in Kubernetes. What could be wrong?

If inter-service communication works locally but fails in **Kubernetes**, the issue is usually related to **service discovery**, **DNS**, **network policies**, or **service configuration**.

**How to Identify:**

* Services communicate successfully in the local environment but fail after deployment to Kubernetes.
* Requests return **connection refused**, **timeout**, or **host not found** errors.
* Pod logs show DNS resolution or connectivity issues.

**Common Reasons:**

* Incorrect **Kubernetes Service** name or namespace.
* **DNS** resolution failure inside the cluster.
* Wrong **Service**, **Pod**, or **Container Port** configuration.
* **NetworkPolicy** rules blocking traffic between pods.
* Service type or selector labels are misconfigured, so traffic is not routed to the correct pods.
* Ingress or API Gateway routing configuration is incorrect.

**How to Resolve:**

* Verify the **Service name**, **namespace**, and endpoint configuration.
* Check **DNS resolution** using tools like `nslookup` or `dig` inside a pod.
* Validate **Service**, **targetPort**, and **containerPort** mappings.
* Review **NetworkPolicy**, firewall, and security rules.
* Ensure pod **labels** and service **selectors** match correctly.
* Check **Ingress**, API Gateway, and Kubernetes service logs for routing issues.

---

# 10. Architecture & System Design

### 50. How would you handle a sudden spike from 10K to 1M RPS? (Black Friday scenario)

**How to Identify**

* Sudden increase in **RPS (Requests Per Second)**.
* High **CPU**, **Memory**, or **Database** utilization.
* Increased **latency**, **timeouts**, and **error rates**.
* Monitoring dashboards show system saturation.

**Common Reasons**

* **Flash sales** or promotional events.
* Viral traffic surge.
* Insufficient infrastructure capacity.
* Database or downstream service bottlenecks.

**How to Resolve**

* Enable **Auto Scaling** to add more instances automatically.
* Use **Load Balancers** to distribute traffic.
* Implement **Caching** (Redis/CDN) to reduce database load.
* Use **Rate Limiting** to protect services.
* Process non-critical requests asynchronously using **Queues**.
* Optimize database with **Read Replicas** and **Partitioning**.
* Apply **Circuit Breakers** and **Graceful Degradation** for dependent services.


### 51. Design a rate-limiting system for an API gateway serving 50,000 RPS.

**How to Identify**

* APIs are receiving excessive **requests** from some users.
* Increased **latency**, **timeouts**, or resource exhaustion.
* Need to protect backend services from overload.

**Common Reasons**

* Traffic spikes
* Misbehaving clients
* Bot attacks
* Lack of request throttling
* Uneven resource usage

**How to Resolve**

* Implement **Rate Limiting** at the **API Gateway**.
* Use the **Token Bucket** or **Sliding Window** algorithm.
* Store counters in a fast distributed cache like **Redis**.
* Apply limits per **User**, **API Key**, or **IP Address**.
* Return **HTTP 429 (Too Many Requests)** when limits are exceeded.
* Use **Horizontal Scaling** to handle 50,000 RPS.
* Monitor rate-limit metrics and adjust thresholds as needed.

**Example**

* Limit: **100 requests/minute per user**
* User sends **120 requests**
* First **100** requests are allowed
* Remaining **20** requests receive **HTTP 429**


### 52. How would you migrate a monolith to microservices without downtime?

**How to Identify**

* The **Monolith** is becoming difficult to scale, deploy, or maintain.
* Teams need **independent deployments** and scalability.
* Frequent changes impact the entire application.

**Common Reasons**

* Tight coupling between modules.
* Large codebase with slow releases.
* Scaling the entire application for a small feature.

**How to Resolve**

* Follow the **Strangler Fig Pattern**.
* Identify and extract one **business domain** at a time.
* Route selected requests from the monolith to the new microservice.
* Keep both systems running during migration.
* Use **API Gateway** for traffic routing.
* Synchronize data using **events** or replication.
* Gradually move traffic and monitor performance.
* Decommission monolith components only after successful migration.

**Migration Steps**

1. Identify a **bounded context**.
2. Build a new **microservice**.
3. Redirect traffic through an **API Gateway**.
4. Gradually increase traffic to the microservice.
5. Remove the corresponding functionality from the monolith.


### 53. Design an event-driven notification system for 10 million users with delivery guarantees.

For a notification system serving **10 million users**, I would use an **event-driven architecture** with a **message broker** like **Kafka** to handle high throughput and reliable delivery.

**How it works:**

1. The application publishes a **notification event** to Kafka.
2. A **Notification Service** consumes the event.
3. The service sends notifications through **Email**, **SMS**, or **Push Notification** providers.
4. After successful delivery, the event is **acknowledged**. If it fails, it is **retried** or moved to a **Dead Letter Queue (DLQ)**.

**How to Identify:**

* Large number of notifications need to be processed asynchronously.
* Temporary provider failures should not result in lost messages.
* Users expect reliable and scalable notification delivery.

**Common Reasons:**

* Consumer or notification service failure.
* Message duplication due to retries.
* Third-party Email/SMS provider downtime.
* High traffic causing queue backlogs.

**How to Resolve:**

* Use **Kafka** with **multiple partitions** for horizontal scalability.
* Implement **at-least-once delivery** with **idempotent consumers** to avoid duplicate processing.
* Add **Retry** and **Dead Letter Queue (DLQ)** mechanisms for failed events.
* Use the **Transactional Outbox Pattern** to ensure events are not lost.
* Monitor **consumer lag**, queue size, and delivery success metrics.

**Simple Flow:**

```text id="r8m3k1"
Application → Kafka Topic → Notification Service → Email/SMS/Push Provider
                     │
              Retry Queue / DLQ (on failure)
```


### 54. How do you design an idempotent REST API for payment processing?

An **idempotent REST API** ensures that **multiple identical requests produce the same result** and prevent **duplicate payments**, even if the client retries due to network failures or timeouts.

**How it works:**

1. The client sends a unique **Idempotency Key** with the payment request.
2. The server stores the key and the corresponding response in a database or cache.
3. If the same request is received again with the same key, the server returns the **previous response** instead of processing the payment again.

**How to Identify:**

* Payment APIs receive **retry requests** due to timeouts or network issues.
* Duplicate transactions are observed for the same user action.
* Multiple identical requests arrive with the same business intent.

**Common Reasons:**

* Client-side retries.
* Network timeouts or connection failures.
* Message broker redelivery.
* User clicking the payment button multiple times.

**How to Resolve:**

* Generate and validate a unique **Idempotency Key** for each payment request.
* Store the **key**, **request details**, and **response** in persistent storage.
* Return the existing response if the same key is received again.
* Use a **unique database constraint** to prevent duplicate transaction records.
* Set an appropriate **expiration time (TTL)** for stored idempotency keys.

**Simple Example:**

```java id="8k3m1p"
POST /payments
Headers:
Idempotency-Key: 12345-abcde

if (keyExists(idempotencyKey)) {
    return previousResponse;
}
processPayment();
saveKeyAndResponse();
```

### 55. Design a CQRS + Event Sourcing system for an auditable financial ledger.

For an **auditable financial ledger**, I would use **CQRS (Command Query Responsibility Segregation)** with **Event Sourcing**. Instead of storing only the latest balance, every change is stored as an **immutable event**, providing a complete audit trail.

**How it works:**

1. A **Command** (e.g., debit or credit) is received.
2. The command is validated and stored as an **event** (e.g., `MoneyDebited`, `MoneyCredited`).
3. Events are saved in an **Event Store**.
4. A **Read Model** is updated asynchronously from these events for fast queries.
5. The current account balance is calculated by **replaying events** or using **snapshots**.

**How to Identify:**

* The system requires a complete **audit history**.
* Every data change must be **traceable and immutable**.
* Read and write workloads have different scaling requirements.

**Common Reasons:**

* Financial systems require regulatory and audit compliance.
* Need to recover or rebuild the current state from historical data.
* High read traffic can affect write performance in a traditional design.

**How to Resolve:**

* Separate **write (Command)** and **read (Query)** operations using **CQRS**.
* Store every state change as an **immutable event** using **Event Sourcing**.
* Use **Kafka** or another message broker to propagate events.
* Create optimized **read models** for reporting and queries.
* Use **snapshots** periodically to avoid replaying all historical events.

**Simple Flow:**

```text id="q7v2n4"
Command → Event Store → Kafka → Read Model Database
               │                    │
        MoneyDebited          Balance Query
        MoneyCredited         Transaction History
```

---

# 11. DevOps & Production

### 56. You deployed a new version but it's causing errors in production. What do you do first?

If a new deployment is causing production errors, the **first step** is to **reduce customer impact** by **rolling back** or **switching traffic to the last stable version**, while investigating the root cause.

**How to Identify:**

* Error rate, latency, or failed requests increase immediately after deployment.
* Monitoring dashboards and alerts show abnormal behavior.
* Logs indicate failures that were not present before the release.

**Common Reasons:**

* Application bugs in the new release.
* Configuration or environment mismatch.
* Database migration issues.
* Dependency or API compatibility problems.
* Incorrect feature flag or deployment settings.

**How to Resolve:**

* **Rollback** to the previous stable version or perform a **blue-green/canary rollback**.
* Check **application logs**, **metrics**, and **distributed traces** to identify the issue.
* Compare the new release with the previous version to find recent changes.
* Verify configuration, environment variables, and database migrations.
* Fix the issue, test it in a lower environment, and redeploy safely.


### 57. Your Kubernetes pods are crashing repeatedly. How do you debug?

If **Kubernetes pods** are crashing repeatedly, I would first check the **pod status and logs** to identify the root cause, then verify resource usage and configuration.

**How to Identify:**

* Pods are in **CrashLoopBackOff** or **Error** state.
* Frequent pod restarts are visible using `kubectl get pods`.
* Application logs show startup failures or exceptions.

**Common Reasons:**

* Application startup exception or code bug.
* **Out of Memory (OOMKilled)** due to insufficient memory limits.
* Incorrect **environment variables**, secrets, or configuration.
* Failed **liveness** or **readiness probes**.
* Image, dependency, or database connection issues.

**How to Resolve:**

* Check pod status using **`kubectl get pods`** and **`kubectl describe pod <pod-name>`**.
* View application logs with **`kubectl logs <pod-name>`**.
* Verify **memory and CPU limits** and check for **OOMKilled** events.
* Validate **environment variables**, **ConfigMaps**, and **Secrets**.
* Review **liveness** and **readiness probe** configurations.
* Check connectivity to dependent services like databases or external APIs, fix the issue, and redeploy.



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


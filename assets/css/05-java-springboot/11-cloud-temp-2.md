# ✅ Java Senior Interview Questions — Real-Time Spoken Answers


---

## 1. How does the Java Memory Model (JMM) ensure visibility between threads?

So the JMM defines rules about how threads read and write to shared memory.
By default, each thread has its own local cache — so changes made by one thread may not be visible to another.

The JMM ensures visibility through:
- **`volatile` keyword** — forces reads/writes to go directly to main memory, not the thread's local cache
- **`synchronized` blocks** — when a thread exits a synchronized block, all its writes are flushed to main memory; when another thread enters, it reads fresh values
- **`happens-before` relationship** — JMM guarantees that actions in one thread are visible to another if a happens-before relationship exists

```java
class SharedFlag {
    private volatile boolean running = true; // visible across threads

    public void stop() {
        running = false;
    }

    public void run() {
        while (running) {
            // without volatile, this loop may never stop
        }
        System.out.println("Stopped");
    }
}
```

> Without `volatile` or `synchronized`, the JVM can cache `running` in the thread's local memory and the loop never exits.

---

## 2. How do threads communicate with each other in Java?

Threads communicate using **wait(), notify(), and notifyAll()** — these are defined on `Object` and must be called inside a `synchronized` block.

- `wait()` — releases the lock and puts the thread in waiting state
- `notify()` — wakes up one waiting thread
- `notifyAll()` — wakes up all waiting threads

```java
class SharedBuffer {
    private int data;
    private boolean hasData = false;

    public synchronized void produce(int value) throws InterruptedException {
        while (hasData) wait();         // wait if buffer is full
        data = value;
        hasData = true;
        notify();                       // wake up consumer
    }

    public synchronized int consume() throws InterruptedException {
        while (!hasData) wait();        // wait if buffer is empty
        hasData = false;
        notify();                       // wake up producer
        return data;
    }
}
```

> Modern approach: use `BlockingQueue` — it handles all the wait/notify internally and is much cleaner.

```java
BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(1);

// Producer
queue.put(42);

// Consumer
int val = queue.take();
```

---

## 3. If both `application.properties` and `application.yml` are present, which one takes priority?

`application.properties` takes priority over `application.yml` when both are present in the same location.

Spring Boot loads both files, but if the same key exists in both, the value from `application.properties` wins.

```
# application.properties
server.port=8081

# application.yml
server:
  port: 8082

# Result → server.port = 8081 (properties wins)
```

> Best practice: use only one format in a project to avoid confusion. Most teams pick `.yml` for readability.

---

## 4. What is the difference between `@PathVariable` and `@RequestParam`? When would you use each?

- `@PathVariable` — extracts value from the **URL path** itself
- `@RequestParam` — extracts value from the **query string** (after `?`)

```java
// @PathVariable → /users/101
@GetMapping("/users/{id}")
public User getUser(@PathVariable Long id) {
    return userService.findById(id);
}

// @RequestParam → /users?page=2&size=10
@GetMapping("/users")
public List<User> getUsers(
    @RequestParam int page,
    @RequestParam int size) {
    return userService.findAll(page, size);
}
```

**When to use which:**
- Use `@PathVariable` for **resource identity** — `/orders/55`, `/products/abc`
- Use `@RequestParam` for **filters, pagination, search** — `?status=active&page=1`

---

## 5. ConcurrentHashMap vs synchronized HashMap — not just what, but when and why

Both are thread-safe, but they work very differently.

**synchronized HashMap** — puts a lock on the **entire map** for every read and write. Only one thread can access it at a time.

**ConcurrentHashMap** — uses **segment-level locking** (Java 7) or **CAS + bucket-level locking** (Java 8+). Multiple threads can read and write to different buckets simultaneously.

```java
// synchronized HashMap — full lock, low throughput
Map<String, Integer> syncMap = Collections.synchronizedMap(new HashMap<>());

// ConcurrentHashMap — fine-grained locking, high throughput
Map<String, Integer> concurrentMap = new ConcurrentHashMap<>();

// Atomic operation — only available in ConcurrentHashMap
concurrentMap.putIfAbsent("key", 1);
concurrentMap.computeIfAbsent("key", k -> expensiveLoad(k));
```

**When to use which:**
| Scenario | Use |
|---|---|
| High read/write concurrency | `ConcurrentHashMap` |
| Simple thread-safe wrapping of existing map | `synchronizedMap` |
| Need atomic compound operations (`putIfAbsent`, `compute`) | `ConcurrentHashMap` |
| Low concurrency, simple use case | Either works |

> In production, always prefer `ConcurrentHashMap` for concurrent access — `synchronizedMap` is a bottleneck under load.

---

## 6. Difference between CachedThreadPool and FixedThreadPool, and what are the tunable parameters for an Executor

**FixedThreadPool** — fixed number of threads. If all threads are busy, tasks wait in a queue.

**CachedThreadPool** — creates new threads as needed, reuses idle threads. Threads die after 60 seconds of inactivity.

```java
// FixedThreadPool — predictable, bounded
ExecutorService fixed = Executors.newFixedThreadPool(10);

// CachedThreadPool — elastic, unbounded threads (dangerous under spike load)
ExecutorService cached = Executors.newCachedThreadPool();
```

**Tunable parameters via `ThreadPoolExecutor`:**

```java
ThreadPoolExecutor executor = new ThreadPoolExecutor(
    5,                                  // corePoolSize
    20,                                 // maximumPoolSize
    60L, TimeUnit.SECONDS,              // keepAliveTime
    new LinkedBlockingQueue<>(100),     // workQueue (bounded!)
    new ThreadPoolExecutor.CallerRunsPolicy() // rejection policy
);
```

| Parameter | Purpose |
|---|---|
| `corePoolSize` | Threads always kept alive |
| `maximumPoolSize` | Max threads allowed |
| `keepAliveTime` | How long idle threads above core survive |
| `workQueue` | Queue for pending tasks |
| `RejectedExecutionHandler` | What to do when queue is full |

**Rejection Policies:**
- `AbortPolicy` — throws exception (default)
- `CallerRunsPolicy` — caller thread runs the task (natural backpressure)
- `DiscardPolicy` — silently drops the task
- `DiscardOldestPolicy` — drops oldest queued task

> In production, always use `ThreadPoolExecutor` directly with a **bounded queue** — never use `CachedThreadPool` for uncontrolled workloads.

---

## 7. Find the second largest element in an array — analyze time complexity between heap and sorting

**Approach 1: Sorting — O(n log n)**

```java
int[] arr = {5, 1, 9, 3, 7};
Arrays.sort(arr);
System.out.println(arr[arr.length - 2]); // 7
```

Simple but wasteful — we sort everything just to find one value.

**Approach 2: Single pass — O(n)** ✅ Best

```java
int findSecondLargest(int[] arr) {
    int first = Integer.MIN_VALUE, second = Integer.MIN_VALUE;
    for (int n : arr) {
        if (n > first) {
            second = first;
            first = n;
        } else if (n > second && n != first) {
            second = n;
        }
    }
    return second;
}
```

**Approach 3: Min-Heap of size 2 — O(n log 2) = O(n)**

```java
int findSecondLargest(int[] arr) {
    PriorityQueue<Integer> minHeap = new PriorityQueue<>(2);
    for (int n : arr) {
        if (minHeap.size() < 2) {
            minHeap.offer(n);
        } else if (n > minHeap.peek()) {
            minHeap.poll();
            minHeap.offer(n);
        }
    }
    minHeap.poll(); // remove largest
    return minHeap.peek(); // second largest
}
```

**Complexity comparison:**

| Approach | Time | Space |
|---|---|---|
| Sorting | O(n log n) | O(1) |
| Single pass | O(n) | O(1) |
| Min-Heap (size k) | O(n log k) | O(k) |

> For second largest, single pass is best. Heap shines when you need **top-K elements**.

---

## 8. Implement a program to print "Thread1" and "Thread2" from two parallel threads in sequential order

The challenge here is enforcing order between two independent threads. I use a shared `volatile` flag or a `Semaphore`.

```java
import java.util.concurrent.Semaphore;

public class SequentialThreads {

    static Semaphore sem1 = new Semaphore(1); // Thread1 starts first
    static Semaphore sem2 = new Semaphore(0); // Thread2 waits

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                sem1.acquire();
                System.out.println("Thread1");
                sem2.release(); // signal Thread2
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                sem2.acquire(); // wait for Thread1
                System.out.println("Thread2");
                sem1.release();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        t2.start();
        t1.start();
    }
}
```

**Output (always in order):**
```
Thread1
Thread2
```

> `Semaphore(1)` means Thread1 can go immediately. `Semaphore(0)` means Thread2 blocks until Thread1 releases it.

---

## 9. Synchronized block vs synchronized method vs Locks

All three provide mutual exclusion, but with different granularity and flexibility.

**Synchronized method** — locks the entire method on `this` (or the class for static methods)

```java
public synchronized void increment() {
    count++;
}
```

**Synchronized block** — locks only a specific section, on any object you choose

```java
public void increment() {
    synchronized (this) {
        count++;
    }
    // other non-critical code runs freely
}
```

**ReentrantLock** — explicit lock with more control

```java
private final ReentrantLock lock = new ReentrantLock();

public void increment() {
    lock.lock();
    try {
        count++;
    } finally {
        lock.unlock(); // always unlock in finally
    }
}
```

**When to use which:**

| Option | Use When |
|---|---|
| `synchronized` method | Simple, whole-method protection |
| `synchronized` block | Need finer control, lock on specific object |
| `ReentrantLock` | Need `tryLock()`, timeout, fairness, or multiple conditions |

```java
// ReentrantLock extras
lock.tryLock(500, TimeUnit.MILLISECONDS); // try, don't block forever
new ReentrantLock(true);                  // fair lock — threads served in order
lock.newCondition();                      // multiple wait/notify conditions
```

> In most cases `synchronized` is enough. Use `ReentrantLock` when you need `tryLock` or fairness.

---

## 10. How do you propagate an exception from a worker thread back to the main thread?

A plain `Thread` swallows exceptions — they don't bubble up to the caller. Use `Future` + `ExecutorService` to capture and rethrow them.

```java
ExecutorService executor = Executors.newSingleThreadExecutor();

Future<String> future = executor.submit(() -> {
    if (true) throw new RuntimeException("Worker failed!");
    return "OK";
});

try {
    String result = future.get(); // blocks, rethrows as ExecutionException
} catch (ExecutionException e) {
    System.out.println("Caught from worker: " + e.getCause().getMessage());
} catch (InterruptedException e) {
    Thread.currentThread().interrupt();
} finally {
    executor.shutdown();
}
```

**Using `CompletableFuture` (modern approach):**

```java
CompletableFuture.supplyAsync(() -> {
    if (true) throw new RuntimeException("Worker failed!");
    return "OK";
}).exceptionally(ex -> {
    System.out.println("Handled: " + ex.getMessage());
    return "fallback";
}).thenAccept(System.out::println);
```

**Using `Thread.UncaughtExceptionHandler` (for raw threads):**

```java
Thread t = new Thread(() -> {
    throw new RuntimeException("Uncaught!");
});
t.setUncaughtExceptionHandler((thread, ex) ->
    System.out.println("Caught: " + ex.getMessage())
);
t.start();
```

> `Future.get()` is the standard way. `CompletableFuture` is preferred in modern code for non-blocking pipelines.

---

## 11. Why would you prefer a SingleThreadExecutor over a manually created Thread?

A manually created thread is fire-and-forget — no lifecycle management, no reuse, no error handling.

`SingleThreadExecutor` gives you:
- **Task queue** — tasks are queued and executed one by one in order
- **Thread reuse** — the same thread handles all tasks, no creation overhead
- **Graceful shutdown** — `shutdown()` / `awaitTermination()`
- **Future support** — you can track results and exceptions

```java
// Manual thread — no control
new Thread(() -> processTask()).start();
new Thread(() -> processTask()).start(); // order not guaranteed

// SingleThreadExecutor — ordered, managed
ExecutorService executor = Executors.newSingleThreadExecutor();
executor.submit(() -> processTask()); // task 1
executor.submit(() -> processTask()); // task 2 — always after task 1
executor.shutdown();
```

**Real use case:** audit logging, sequential DB writes, ordered event processing — anywhere you need tasks to run one at a time in submission order.

> If the thread dies due to an exception, `SingleThreadExecutor` automatically creates a replacement thread. A manual thread is just gone.

---

## 12. Design and implement a thread-safe in-memory key-value store with TTL (Time-To-Live)

```java
import java.util.concurrent.*;

public class TTLCache<K, V> {

    private static class Entry<V> {
        final V value;
        final long expiryMs;

        Entry(V value, long ttlMs) {
            this.value = value;
            this.expiryMs = System.currentTimeMillis() + ttlMs;
        }

        boolean isExpired() {
            return System.currentTimeMillis() > expiryMs;
        }
    }

    private final ConcurrentHashMap<K, Entry<V>> store = new ConcurrentHashMap<>();
    private final ScheduledExecutorService cleaner = Executors.newSingleThreadScheduledExecutor();

    public TTLCache() {
        // background cleanup every 30 seconds
        cleaner.scheduleAtFixedRate(this::evictExpired, 30, 30, TimeUnit.SECONDS);
    }

    public void put(K key, V value, long ttlMs) {
        store.put(key, new Entry<>(value, ttlMs));
    }

    public V get(K key) {
        Entry<V> entry = store.get(key);
        if (entry == null || entry.isExpired()) {
            store.remove(key);
            return null;
        }
        return entry.value;
    }

    public void remove(K key) {
        store.remove(key);
    }

    private void evictExpired() {
        store.entrySet().removeIf(e -> e.getValue().isExpired());
    }

    public void shutdown() {
        cleaner.shutdown();
    }

    // --- Usage ---
    public static void main(String[] args) throws InterruptedException {
        TTLCache<String, String> cache = new TTLCache<>();

        cache.put("session:abc", "user123", 2000); // expires in 2s
        System.out.println(cache.get("session:abc")); // user123

        Thread.sleep(3000);
        System.out.println(cache.get("session:abc")); // null (expired)

        cache.shutdown();
    }
}
```

**Design decisions:**
- `ConcurrentHashMap` — thread-safe without full locking
- Lazy expiry on `get()` — expired entries removed on access
- Background `ScheduledExecutorService` — periodic cleanup to prevent memory leak
- `Entry` wrapper — stores value + expiry time together

> This is the foundation of how Redis TTL works. In production you'd add eviction policies (LRU), max size, and metrics.

---

## 13. What is the N+1 problem in Hibernate?

The N+1 problem happens when Hibernate fires **1 query to load a list**, then **N more queries** — one for each item — to load their associations.

```java
// Suppose we have Order → List<OrderItem> (lazy loaded)

List<Order> orders = session.createQuery("FROM Order", Order.class).list();
// 1 query: SELECT * FROM orders

for (Order order : orders) {
    order.getItems().size(); // triggers 1 query per order!
    // N queries: SELECT * FROM order_items WHERE order_id = ?
}
// Total: 1 + N queries
```

**Fix 1: JOIN FETCH (JPQL)**

```java
List<Order> orders = session.createQuery(
    "SELECT o FROM Order o JOIN FETCH o.items", Order.class
).list();
// 1 query with JOIN — fetches everything at once
```

**Fix 2: `@EntityGraph`**

```java
@EntityGraph(attributePaths = {"items"})
List<Order> findAll();
```

**Fix 3: Batch fetching**

```java
@OneToMany
@BatchSize(size = 20) // loads 20 collections in one query
private List<OrderItem> items;
```

> Always check your SQL logs in development. If you see repeated queries with different IDs, you have N+1. Use JOIN FETCH or EntityGraph to fix it.

---

## 14. What is Feign Client?

Feign is a **declarative HTTP client** from Netflix, integrated into Spring Cloud. Instead of writing `RestTemplate` boilerplate, you define an interface and Feign generates the implementation.

```java
// Define the client
@FeignClient(name = "user-service", url = "http://user-service")
public interface UserClient {

    @GetMapping("/users/{id}")
    UserDTO getUser(@PathVariable Long id);

    @PostMapping("/users")
    UserDTO createUser(@RequestBody UserDTO user);
}

// Use it like a regular bean
@Service
public class OrderService {

    @Autowired
    private UserClient userClient;

    public void placeOrder(Long userId) {
        UserDTO user = userClient.getUser(userId); // Feign handles the HTTP call
        // ...
    }
}
```

**Enable in Spring Boot:**

```java
@SpringBootApplication
@EnableFeignClients
public class Application { }
```

---

## 15. Why Feign may not always be recommended in production?

Feign is convenient but has real limitations at scale:

**1. Blocking by default**
Feign uses synchronous HTTP — each call blocks a thread. Under high load this exhausts the thread pool.

```java
// Feign — blocking
UserDTO user = userClient.getUser(id); // thread blocked until response

// WebClient — non-blocking (preferred for high throughput)
Mono<UserDTO> user = webClient.get().uri("/users/{id}", id).retrieve().bodyToMono(UserDTO.class);
```

**2. Limited timeout and retry control**
Default Feign has no timeout. You must configure it explicitly or calls hang forever.

```java
@Bean
public Request.Options feignOptions() {
    return new Request.Options(2000, 5000); // connectTimeout, readTimeout
}
```

**3. No built-in circuit breaker**
You need to add Resilience4j or Hystrix separately.

```java
@FeignClient(name = "user-service", fallback = UserClientFallback.class)
public interface UserClient {
    @GetMapping("/users/{id}")
    UserDTO getUser(@PathVariable Long id);
}

@Component
class UserClientFallback implements UserClient {
    public UserDTO getUser(Long id) {
        return new UserDTO("fallback-user"); // graceful degradation
    }
}
```

**4. Error handling is verbose**
You need a custom `ErrorDecoder` to handle non-2xx responses properly.

**When to use Feign:**
- Internal microservice calls with moderate load
- Teams that want clean, readable HTTP client code

**When to avoid Feign:**
- High-throughput reactive services (use `WebClient`)
- Services needing fine-grained retry/circuit-breaker logic
- Streaming or async responses

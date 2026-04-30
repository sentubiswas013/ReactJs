# Spring Boot & Java Interview Questions

---

## 1. How to create a simple bean using Spring and use it step by step

**Step 1: Create a plain Java class**
```java
public class MyService {
    public String greet() {
        return "Hello from MyService!";
    }
}
```

**Step 2: Register it as a Bean (two ways)**

**Option A – Using `@Component` (auto-detection)**
```java
@Component
public class MyService {
    public String greet() {
        return "Hello from MyService!";
    }
}
```

**Option B – Using `@Bean` in a `@Configuration` class**
```java
@Configuration
public class AppConfig {
    @Bean
    public MyService myService() {
        return new MyService();
    }
}
```

**Step 3: Inject and use it**
```java
@RestController
public class MyController {

    @Autowired
    private MyService myService;

    @GetMapping("/greet")
    public String greet() {
        return myService.greet();
    }
}
```

**Step 4: Spring Boot auto-scans and wires everything on startup via `@SpringBootApplication`.**

---



---

---

## 4. Difference between `transient` and `volatile`

| Feature       | `transient`                                      | `volatile`                                          |
|---------------|--------------------------------------------------|-----------------------------------------------------|
| Purpose       | Excludes field from **serialization**            | Ensures **visibility** of field across threads      |
| Context       | Used with Java Serialization (`Serializable`)    | Used in multi-threaded programming                  |
| Effect        | Field is skipped when object is serialized       | Field is always read/written from main memory       |
| Thread safety | No effect on threads                             | Provides visibility guarantee (not atomicity)       |

```java
class User implements Serializable {
    String name;
    transient String password; // not saved during serialization

    volatile boolean isLoggedIn; // always visible across threads
}
```

---

## 6. Singleton vs Prototype Scope

| Feature              | Singleton                              | Prototype                                  |
|----------------------|----------------------------------------|--------------------------------------------|
| Instances created    | One per Spring container               | New instance every time it is requested    |
| Default scope        | Yes (default in Spring)                | No                                         |
| Memory               | Shared, memory efficient               | More memory usage                          |
| State                | Shared state — be careful with mutable fields | Each caller gets its own state        |
| Annotation           | `@Scope("singleton")` or default       | `@Scope("prototype")`                      |

```java
@Component
@Scope("prototype")
public class ReportGenerator { ... }
```

**When to use:**
- `Singleton` — stateless services, repositories, controllers (most Spring beans).
- `Prototype` — stateful beans where each caller needs an independent instance (e.g., report generators, command objects).

**Gotcha:** Injecting a prototype bean into a singleton bean won't give a new instance each time — use `ApplicationContext.getBean()` or `@Lookup` to get a fresh prototype.

---

## 7. What happens if the thread pool is exhausted?

When all threads in the pool are busy and the task queue is also full:

1. **New tasks are rejected** — the `RejectedExecutionHandler` is triggered.
2. Default policy in `ThreadPoolExecutor` is `AbortPolicy` — throws `RejectedExecutionException`.

**Other rejection policies:**
| Policy               | Behavior                                                   |
|----------------------|------------------------------------------------------------|
| `AbortPolicy`        | Throws `RejectedExecutionException` (default)              |
| `CallerRunsPolicy`   | The calling thread runs the task itself (slows the caller) |
| `DiscardPolicy`      | Silently drops the new task                                |
| `DiscardOldestPolicy`| Drops the oldest queued task and retries submission        |

**In Spring Boot (Tomcat):**
- If the request thread pool is exhausted, incoming HTTP requests are **queued** up to `server.tomcat.accept-count` limit.
- Beyond that, connections are **refused** with a connection timeout or HTTP 503.

**Best practices:**
- Monitor thread pool metrics.
- Use `CallerRunsPolicy` as a backpressure mechanism.
- Size the pool appropriately: `(CPU cores * (1 + wait time / compute time))`.

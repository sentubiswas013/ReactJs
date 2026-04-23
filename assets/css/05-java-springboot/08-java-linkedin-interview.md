## 🔹 Core Java & OOP

- What is the static keyword?
- Explain OOP concepts with real examples.
- Explain OOP principles.
- Difference between Abstract Class and Interface.
- Difference between abstract class vs interface.
- Difference between final, finally, and finalize.
- How do you create an immutable object?
- What is immutability in Java? How does it provide security?
- Explain immutability in Java – how to create an immutable class.
- String vs StringBuilder vs StringBuffer.
- Difference between StringBuffer and StringBuilder.
- Why String is immutable?
- Types of exceptions in Java.
- What is an Exception in Java?
- How do you handle NullPointerException?
- Difference between == and .equals()
- How equals() and hashCode() work internally.
- Difference between HashMap and ConcurrentHashMap.
- Difference between ArrayList and LinkedList.
- Difference between ArrayList vs LinkedList – internal working.
- How does HashMap resolve collisions? What is treeification?
- Difference between Comparable vs Comparator.
- Heap memory vs Stack memory.
- Explain Min Heap and Max Heap.
- What is volatile, and when do you need it?
- What is volatile keyword?
- What is the difference between "transient" and "volatile", what are their uses?
- Can we override a static method?
- What are Fail-Fast and Fail-Safe mechanisms, how do they work internally, and why are they important in collections?
- Explain fail-fast vs fail-safe iterators.
- What is try-with-resources, how does it work internally, and why is it better than traditional try-catch-finally?
- When does Java throw OutOfMemoryError and how to fix it?
- Explain Garbage Collection in brief.

---

## 🔹 Java 8 & Functional Programming

- What are the major features introduced in Java 8?
- What is a Functional Interface? Name some predefined ones. What happens if @FunctionalInterface is not used?
- What are functional interfaces?
- Write a Lambda expression for a functional interface. How do you convert pre-Java 8 code to Lambda?
- What is Java Stream API? Why prefer Streams over traditional loops?
- Stream API intermediate operations with examples.
- How does map() differ from flatMap()?
- Difference between map() vs flatMap().
- Use cases of Stream API.
- Explain Java Streams API.
- In what places have you used Java Streams in your projects?
- Find distinct elements using Java Streams.
- Print N numbers while skipping specified numbers using streams.
- Program to find 2nd highest number using streams.
- Find the occurrence of each string in a list using the Stream API.
- Stream API: filter even numbers, group employees by dept & get max salary, remove duplicates & sort custom objects.
- Convert List<List<String>> → List<String> using flatMap.
- How do you merge two lists using Java 8 Streams? (e.g. empList3 = empList1 + empList2)
- What is a Parallel Stream? How does it work internally? When should it be avoided?
- Difference between Parallel Stream and Multithreading.
- What is the Optional class? When should it be used and avoided?
- Explain Optional and why it is used.
- How to handle null checks using Optional in Java 8?
- How to handle null checks using annotations (e.g., @NotNull, @NotBlank)?
- What is the difference between Collection and Collections in Java?
- What improvements related to memory management were introduced in Java 8, and how do they impact performance?
- How is Java 8 used in multithreading? What improvements were introduced?

---

## 🔹 Multithreading & Concurrency

- Difference between Runnable vs Callable.
- How ExecutorService works.
- What is ExecutorService and different thread pools?
- What is the Executor Framework, why was it introduced, and how does it improve thread management compared to traditional approaches?
- Difference between synchronized block vs method.
- Difference between synchronized vs Lock API.
- What is deadlock and how to avoid it.
- How do you avoid deadlocks in Java?
- Basics of CompletableFuture.
- How does ConcurrentHashMap achieve thread-safety?
- What is ConcurrentHashMap? Explain its internal working.
- How will you make a class thread-safe?
- If two threads are using the same resource, how will you handle it?
- Write thread-safe caching mechanism.

### Scenario-Based – Concurrency

- You have a shared in-memory cache accessed by multiple threads. How do you prevent race conditions and ensure high performance?
- Multiple threads are updating the same user balance. How do you ensure data consistency without hurting scalability?
- Your system processes 10,000 requests/sec. How would you design a thread pool strategy?
- Threads must execute tasks in order (like logs or transactions). How would you enforce ordering?
- A thread is blocked due to a slow API call. How do you prevent thread starvation?

### Machine Coding – Concurrency (FAANG Level)

- Design a Job Scheduler – schedule and execute multiple jobs concurrently with dependencies and priorities.
- Thread-Safe Job Queue – supports adding, removing, and retrieving the next job efficiently.
- Thread-safe LRU Cache – concurrent read and write operations.
- Concurrent Task Scheduler – execute multiple tasks concurrently while limiting max parallel executions.
- Print N Numbers Using Different Even-Odd Threads – one thread prints even, other prints odd, in order.
- Parallel Matrix Multiplication – using multiple threads for parallel processing.
- Thread Safety in Singleton – only one instance created in multi-threaded environment.
- Priority Job Scheduler – supports job priorities and dynamic priority changes.
- Distributed Lock Manager – coordinate job execution across multiple nodes.
- Rate Limiter – control rate of job execution.

---

## 🔹 Spring Boot & Spring Framework

- What is Spring Boot and why is it preferred over Spring?
- Explain Spring Boot auto-configuration.
- How does Spring Boot auto-configuration work?
- How does Spring Boot decide the order of auto-configurations?
- How does Spring Boot handle classpath scanning internally?
- What is Spring Boot Starter and why do we use it?
- Which Spring Boot annotations have you used and what are their purposes?
- Difference between @Component, @Service, @Repository, and @Configuration.
- What is the difference between @Primary and @Qualifier?
- What are the different types of beans in Spring? What are session bean types?
- Explain Bean Scopes – prototype, request, session.
- Singleton vs Prototype scope.
- Explain IOC & Dependency Injection.
- How does Spring IoC container work internally?
- What is Dependency Injection?
- How do you perform Dependency Injection in a REST Controller?
- What is @PatchMapping and when should it be used?
- Default port, server, and logging library in Spring Boot?
- What happens if port is not mentioned in application.properties?
- What happens when application.yml and application.properties both exist?
- Where do we configure DB credentials in Spring Boot?
- How do you establish a database connection in Spring Boot?
- How do you implement Global Exception Handler?
- How do you handle exceptions in a Spring Boot application?
- How do you handle exception handling in Spring Boot? Write a custom exception and global exception handler using @ExceptionHandler.
- Why do custom exception handlers sometimes not trigger?
- How do you implement global exception handling in Spring Boot, and why is it preferred over local exception handling?
- Global exception handling using @ControllerAdvice.
- Explain AOP with real use case (audit logging/security).
- What is WebClient vs RestTemplate?
- How do you secure APIs using JWT + Spring Security?
- How do you set up profiles for multiple environments?
- How does Spring Boot isolate environment-specific configs?
- How do you safely reload configs without restarting?
- Why does @Value sometimes fail to inject properties?
- Why does @ConfigurationProperties fail silently?
- How do you avoid startup failure due to missing configs?
- What happens if @PostConstruct throws an exception?
- What happens if a BeanFactoryPostProcessor fails?
- What causes duplicate bean registration in multi-module projects?
- What causes classloader issues in fat JARs?
- What are the risks of enabling too many Actuator endpoints?
- What is the real impact of using too many interceptors?
- How do you manage feature toggles safely?
- Why does @Cacheable sometimes not cache?
- How do you control thread usage in Spring Boot applications?
- How do you handle large payloads without killing performance?
- Why does your app behave differently after scaling pods?
- Why does logging behave differently in prod vs local?
- How do you prevent breaking changes during deployments?
- What Spring Boot decision has caused you a real production issue?
- Why does Spring Boot retry DB connections on startup?
- What is Spring Cloud? How is it useful?
- How to implement async processing in Spring Boot?
- ExecutorService use case in a real-time project.
- What are circular dependencies in Spring, why do they occur, and how do you resolve them?
- How to validate incoming JSON requests?
- If we don't write getters & setters, which annotation can we use?
- How would you rate yourself in Java, Spring Boot, and Microservices, and why?

---

## 🔹 REST API

- What are RESTful APIs? Which HTTP methods are supported?
- What are the different HTTP methods used in REST APIs?
- Difference between PUT vs PATCH.
- How do you fetch an employee by ID?
- How would you design a REST endpoint like /employee/details/123?city=kolkata?
- How would you treat 123 as an employeeId in a Spring Boot controller?
- What are common mistakes in REST API design?
- What is API versioning? Why is it required, and how do you implement it in REST APIs?
- Design API for high concurrent users (rate limiting, caching).

---

## 🔹 Microservices

- Monolithic vs Microservices architecture.
- How do microservices communicate with each other?
- What is service discovery?
- What is API Gateway and why is it needed?
- How do you handle failures/fallback when a microservice is down?
- How will you implement Circuit Breaker in Spring Boot?
- What is circuit breaker real use case?
- Difference between Feign vs WebClient (which to use under high load)?
- When multiple services are updating the same data, how do you handle data consistency?
- Suppose there are three microservices A, B, and C. A calls B, B calls C, and C is failing. What will you do?
- How do you debug Kafka consumer lag?
- What if messages are duplicated?
- How to handle idempotency in retries?
- How to implement retry with backoff?
- How do you implement distributed tracing?

---

## 🔹 JPA, Hibernate & SQL

- What is Spring Data JPA?
- JPA vs Hibernate.
- Why do we use Long in JpaRepository<Employee, Long>?
- Difference between persist(), merge(), save().
- Difference between save(), persist(), saveAndFlush().
- Explain lazy loading vs eager loading with real examples.
- Difference between LAZY vs EAGER fetching.
- Lazy vs Eager loading.
- What is N+1 problem and how to avoid it?
- Explain @OneToMany, @ManyToOne, @ManyToMany.
- What is a Many-to-One mapping in JPA? Which attributes are used and why?
- What is CascadeType.ALL vs orphanRemoval?
- How do you perform pagination & sorting in JPA?
- Explain 1st level vs 2nd level cache.
- How do you optimize slow SQL queries? (indexes, EXPLAIN PLAN)
- How do you optimize database queries?
- If a table has 100+ fields and performance is slow, how do you fetch only 3–4 required fields?
- How to perform batch insert/update in Hibernate?
- What is a transaction isolation level?
- How do you handle deadlocks in SQL databases?
- How @Transactional works internally.
- Explain @Transactional propagation with a use case.
- In case transaction rollback is not happening, what could be the reason?
- Write JPA custom query (JPQL + Native).
- Why does Hibernate generate unexpected queries?
- Entity, Repository, Service Layer implementation.
- Write a complete Spring Boot REST controller with validation & exception handling.
- Query to find 2nd highest salary.
- Difference between INNER JOIN VS LEFT JOIN.
- What are indexes and their pros/cons?
- What are database indexes, why are they used, and what are their different types?
- How do you identify missing indexes?
- What are stored procedures?
- Given an Employee table with employee_name, employee_id, department_id, and salary — write a query to fetch employees whose salary is greater than the average salary of their department.

---

## 🔹 Design & Architecture

- Explain SOLID principles.
- Explain the Repository Pattern.
- What is Dependency Injection?
- Singleton vs Prototype scope.

---

## 🔹 Production Debugging & Performance

- API is slow in production — where do you start debugging?
- High CPU usage after deployment — what will you check?
- Memory usage keeps increasing — possible causes?
- How do you identify memory leaks in Java?
- How do you analyze heap dump?
- How do you analyze thread dump?
- What if DB queries suddenly become slow?
- How to debug connection pool exhaustion?
- What happens if thread pool is exhausted?
- How do you handle high latency in APIs?
- What if one microservice is slowing entire system?
- How to debug timeout issues?
- How do you handle cascading failures?
- What if logs are not sufficient?
- How to improve observability?
- What metrics do you monitor in production?
- How to detect slow endpoints?
- What is p95 vs p99 latency?
- How to debug GC pauses?
- What causes OutOfMemoryError?
- How to fix memory fragmentation?
- How to handle traffic spikes?
- How to scale application quickly?
- What is autoscaling strategy?
- What if deployment breaks production?
- How do you rollback safely?
- What is blue-green deployment?
- How to validate canary release?
- How to handle config issues in prod?
- What if cache is not working?
- Cache miss vs cache stampede?
- How to debug Redis issues?
- How to prevent stale cache?
- How to debug authentication failures?
- What if JWT expires frequently?
- How to debug CORS issues?
- How to debug network latency?
- What if third-party API fails?
- How to design fallback mechanism?
- What if disk space is full?
- How to monitor logs efficiently?
- What tools do you use for debugging?
- Biggest production issue you handled?

### Scenario-Based – Production Issues

1. Your Spring Boot app starts returning 500 errors after deployment. How will you debug it?
2. You hit BeanCreationException at startup. What could be wrong?
3. A bean works locally but throws NoSuchBeanDefinitionException in prod. How will you fix it?
4. You get CircularDependencyException between beans. How will you resolve it?
5. Your API throws HttpMessageNotReadableException for valid payloads. How will you debug it?
6. LazyInitializationException appears in prod only. How will you fix it?
7. Wrong values are picked from config after deploy. How will you manage properties safely?
8. DataIntegrityViolationException occurs during save. What checks will you add?
9. TransactionRequiredException appears during updates. How will you fix transaction boundaries?
10. After enabling debug logs, response time increases. How will you optimize logging?
11. Memory usage keeps growing in the app. How will you detect and fix leaks?
12. Intermittent DB connection failures occur. How will you debug datasource issues?
13. Downstream REST calls time out. How will you add resilience and timeouts?
14. Wrong profile is active in prod. How will you enforce correct environment configs?
15. Duplicate requests are processed due to retries. How will you ensure idempotency?
16. Unhandled exceptions crash the service. How will you design global exception handling?
17. High thread usage is observed. How will you tune executors/thread pools?
18. A scheduled job runs multiple times across instances. How will you prevent it?
19. App fails due to dependency/version conflicts after build. How will you resolve it?
20. Concurrent transactions cause inconsistent data. How will you handle isolation and locking?
21. Logs are insufficient to trace issues. How will you improve structured logging?
22. API Gateway returns errors due to downstream failures. How will you add fallbacks?
23. App becomes unresponsive under load. How will you investigate bottlenecks?
24. You need zero-downtime deployment. What rollout strategy will you use?
25. Behavior differs between local and prod. How will you systematically debug it?

### Scenario-Based – Java & Spring Boot Issues

1. Your Spring Boot app throws OutOfMemoryError after running for some time. How will you identify and fix the root cause?
2. You start seeing NullPointerException in production but not locally. How will you debug it?
3. A REST API becomes slow due to heavy object creation. How will you optimize Java performance?
4. Multiple threads update shared data causing inconsistent results. How will you handle concurrency?
5. Your application throws ConcurrentModificationException. How will you fix it?
6. A thread pool gets exhausted under load. How will you tune and manage it?
7. Your Spring Boot service fails due to improper exception handling. How will you design global exception handling?
8. You face LazyInitializationException in production. How will you resolve it?
9. A scheduled task runs multiple times across instances. How will you control execution?
10. Your application shows high GC pauses affecting performance. How will you optimize memory usage?
11. You encounter DataIntegrityViolationException while saving data. What checks will you add?
12. A transaction fails midway causing inconsistent data. How will you handle rollback properly?
13. Your service crashes due to unhandled runtime exceptions. How will you prevent it?
14. You observe thread starvation in your application. How will you resolve it?
15. A service behaves differently in production due to environment configs. How will you debug it?
16. Your API processes duplicate requests due to retries. How will you ensure idempotency?
17. You face deadlocks due to improper synchronization. How will you detect and resolve them?
18. Your application fails due to class loading issues. How will you debug ClassNotFoundException?
19. A long-running task blocks request threads. How will you redesign it?
20. Your logs are not enough to debug issues. How will you improve logging and monitoring?

---

## 🔹 Spring Boot – Advanced / Production

1. Why does a Spring Boot app consume more memory over time?
2. How do you detect bean initialization issues in large applications?
3. What happens if @PostConstruct throws an exception?
4. Why does @Value sometimes fail to inject properties?
5. How does Spring Boot decide the order of auto-configurations?
6. What are the risks of enabling too many Actuator endpoints?
7. Why does your app behave differently after scaling pods?
8. How does Spring Boot handle classpath scanning internally?
9. What causes duplicate bean registration in multi-module projects?
10. Why does your API return correct data but response time fluctuates?
11. How do you control thread usage in Spring Boot applications?
12. What happens when application.yml and application.properties both exist?
13. Why do custom exception handlers sometimes not trigger?
14. How do you handle large payloads without killing performance?
15. Why does Hibernate generate unexpected queries?
16. How do you debug a deadlock in a Spring Boot service?
17. What happens if a BeanFactoryPostProcessor fails?
18. How do you avoid startup failure due to missing configs?
19. Why does Spring Boot retry DB connections on startup?
20. How do you manage feature toggles safely?
21. Why does @Cacheable sometimes not cache?
22. How does Spring Boot isolate environment-specific configs?
23. What causes classloader issues in fat JARs?
24. How do you safely reload configs without restarting?
25. Why does logging behave differently in prod vs local?
26. What is the real impact of using too many interceptors?
27. How do you prevent breaking changes during deployments?
28. Why does @ConfigurationProperties fail silently?
29. What Spring Boot decision has caused you a real production issue?

---

## 🔹 DSA & Coding

- Reverse a string using Java.
- Find duplicate elements in an array.
- Count occurrences of characters in a string.

### Code Question – Exception Handling (Output Prediction)
What will be the output of the following program, and why?

```java
public class Main {
    void a() {
        try {
            System.out.println("a(): Main called");
            b();
        } catch (Exception e) {
            System.out.println("Exception is caught");
        }
    }

    void b() throws Exception {
        try {
            System.out.println("b(): Main called");
            c();
        } catch (Exception e) {
            throw new Exception();
        } finally {
            System.out.println("finally block is called");
        }
    }

    void c() throws Exception {
        throw new Exception();
    }

    public static void main(String args[]) {
        Main m = new Main();
        m.a();
    }
}
```

---

## 🔹 Testing & Tools

- Difference between @Mock and @MockBean.
- Which version control tool are you using?
- How do you handle merge conflicts while pushing code to a developer branch?
- Have you used Jenkins? Why is it used?

---

## 🔹 AI in Development

- Are you using AI tools for coding?
- What are their pros, cons, and risks?
- How do you validate AI-generated code?

---

## 🔹 Behavioral / HR

- Tell me about yourself. Current role and responsibilities.
- Tell me about a challenging issue you faced in a project.
- How do you handle production incidents?
- How do you manage tight deadlines?
- How do you handle disagreements with teammates?
- Have you worked with clients directly? How do you explain a technical solution to a client?
- Why are you looking for a change?
- Notice period & availability. Salary expectations.

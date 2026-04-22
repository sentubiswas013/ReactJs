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

## 🔹 Core Java

- What is the static keyword?
- Difference between StringBuffer and StringBuilder.
- What is an Exception in Java?
- Difference between HashMap and ConcurrentHashMap.
- Difference between ArrayList and LinkedList.
- Heap memory vs Stack memory.
- What is immutability in Java? How does it provide security?
- Explain Min Heap and Max Heap.
- Difference between == and .equals()
- Explain OOP concepts with real examples.

---

## 🔹 Java 8 Features

- What are the major features introduced in Java 8?
- What is a Functional Interface? Name some predefined ones. What happens if @FunctionalInterface is not used?
- Write a Lambda expression for a functional interface. How do you convert pre-Java 8 code to Lambda?
- What is Java Stream API? Why prefer Streams over traditional loops?
- Stream API intermediate operations with examples.
- How does map() differ from flatMap()?
- What is a Parallel Stream? How does it work internally? When should it be avoided?
- Difference between Parallel Stream and Multithreading.
- What is the Optional class? When should it be used and avoided?
- What is the difference between Collection and Collections in Java?
- How do you merge two lists using Java 8 Streams? (e.g. empList3 = empList1 + empList2)
- How is Java 8 used in multithreading? What improvements were introduced?

---

## 🔹 Spring Boot & JPA

- What is Spring Boot and why is it preferred over Spring?
- Explain Spring Boot auto-configuration.
- Which Spring Boot annotations have you used and what are their purposes?
- Difference between @Component, @Service, @Repository, and @Configuration.
- What is the difference between @Primary and @Qualifier?
- What are the different types of beans in Spring? What are session bean types?
- What is @PatchMapping and when should it be used?
- Default port, server, and logging library in Spring Boot?
- What happens if port is not mentioned in application.properties?
- Where do we configure DB credentials in Spring Boot?
- What is Spring Data JPA?
- Why do we use Long in JpaRepository<Employee, Long>?
- If we don't write getters & setters, which annotation can we use?
- What is a Many-to-One mapping in JPA? Which attributes are used and why?
- If a table has 100+ fields and performance is slow, how do you fetch only 3–4 required fields?
- Write JPA custom query (JPQL + Native).
- In case transaction rollback is not happening, what could be the reason?
- Explain @Transactional propagation with a use case.

---

## 🔹 REST API

- What are RESTful APIs? Which HTTP methods are supported?
- How do you perform Dependency Injection in a REST Controller?
- How do you fetch an employee by ID?
- How would you design a REST endpoint like /employee/details/123?city=kolkata?
- How would you treat 123 as an employeeId in a Spring Boot controller?
- What are common mistakes in REST API design?
- How do you handle exception handling in Spring Boot? Write a custom exception and global exception handler using @ExceptionHandler.
- Design API for high concurrent users (rate limiting, caching).

---

## 🔹 Microservices

- Monolithic vs Microservices architecture.
- How do microservices communicate with each other?
- What is service discovery?
- What is API Gateway and why is it needed?
- How do you handle failures/fallback when a microservice is down?
- How will you implement Circuit Breaker in Spring Boot?
- Difference between Feign vs WebClient (which to use under high load)?
- When multiple services are updating the same data, how do you handle data consistency?
- How to implement async processing in Spring Boot?
- ExecutorService use case in a real-time project.

---

## 🔹 Coding Questions

- Stream API: filter even numbers, group employees by dept & get max salary, remove duplicates & sort custom objects.
- Convert List<List<String>> → List<String> using flatMap.
- Write thread-safe caching mechanism.
- Entity, Repository, Service Layer implementation.
- Write a complete Spring Boot REST controller with validation & exception handling.

---

## 🔹 DSA

- Reverse a string using Java.
- Find duplicate elements in an array.
- Count occurrences of characters in a string.

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

=============
🔹 1. How does HashMap work internally?  
→ It uses hashing to store key-value pairs in buckets.  
→ Collision is handled using LinkedList / Tree (after Java 8).

🔹 2. HashMap vs ConcurrentHashMap?  
→ HashMap is not thread-safe.  
→ ConcurrentHashMap allows concurrent access using segment-level locking.

🔹 3. synchronized vs ReentrantLock?  
→ synchronized is simple but less flexible.  
→ ReentrantLock provides tryLock(), fairness, and better control.

🔹 4. What does volatile keyword do?  
→ Ensures visibility of variable changes across threads.  
→ Does NOT guarantee atomicity.

🔹 5. Explain JVM memory structure.  
→ Heap (objects), Stack (method calls), Metaspace (class metadata).  
→ Each thread has its own stack.

🔹 6. What is Garbage Collection?  
→ Automatic memory cleanup of unused objects.  
→ G1 GC is commonly used for better performance.

🔹 7. Why String is immutable?  
→ Ensures security, caching, and thread safety.  
→ Used in String pool for performance.

🔹 8. Comparable vs Comparator?  
→ Comparable = natural sorting (inside class).  
→ Comparator = custom sorting (external logic).

🔹 9. What is dependency injection?  
→ Providing dependencies from outside instead of creating them.  
→ Promotes loose coupling.

🔹 10. Component vs Service vs Repository?  
→ All are Spring beans.  
→ Service → business logic, Repository → DB layer.

🔹 11. What does Transactional do?  
→ Manages database transactions automatically.  
→ Rolls back on runtime exceptions.

🔹 12. How do you design REST APIs?  
→ Use proper HTTP methods (GET, POST, PUT, DELETE).  
→ Follow naming conventions and status codes.

🔹 13. What is idempotency in APIs?  
→ Multiple requests give same result.  
→ Example: PUT, GET.

🔹 14. How does indexing improve SQL performance?  
→ Reduces full table scan.  
→ Works like a lookup pointer.

🔹 15. WHERE vs HAVING?  
→ WHERE filters rows before grouping.  
→ HAVING filters after GROUP BY.


🔹 16. What is normalization?  
→ Reduces redundancy by splitting tables.  
→ Improves data integrity.

🔹 17. What is connection pooling?  
→ Reuses DB connections instead of creating new ones.  
→ Improves performance.

🔹 18. How do you handle exceptions in Spring Boot?  
→ Use ControllerAdvice + ExceptionHandler.  
→ Centralized error handling.

🔹 19. What is caching?  
→ Storing frequently used data (e.g., Redis).  
→ Reduces DB load.

🔹 20. How to improve API performance?  
→ Use caching, indexing, async processing.  
→ Optimize DB queries.

=================

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

--------------------
- How would you rate yourself in Java, Spring Boot, and Microservices, and why?

- What is the difference between "transient" and "volatile", what are their uses?

- What are Fail-Fast and Fail-Safe mechanisms, how do they work internally, and why are they important in collections?

- What is immutability, why is it important, and how would you design a fully immutable class in Java? (Explain all steps)

- What is the Executor Framework, why was it introduced, and how does it improve thread management compared to traditional approaches?

- What is try-with-resources, how does it work internally, and why is it better than traditional try-catch-finally?

∆ Code Question – Exception Handling (Output Prediction)
What will be the output of the following program, and why?

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

∆ Java 8:

-Find the occurrence of each string in a list using the Stream API.

- What improvements related to memory management were introduced in Java 8, and how do they impact performance?

∆ SQL:

- What are database indexes, why are they used, and what are their different types?

- Given a Employee table with "employee_name" "employee_id", "department_id", and "salary", how would you write a query to fetch employees whose salary is greater than the average salary of their department?

∆ Spring Boot and Microservices:

- Suppose there are three microservices, A, B and C, A is calling B , B is calling C and C is getting failed. What you will do?

- What is API versioning, why is it required, and how do you implement it in REST APIs?

- How do you implement global exception handling in Spring Boot, and why is it preferred over local exception handling?

- What are circular dependencies in Spring, why do they occur, and how do you resolve them?
----------
1. API is slow in production — where do you start debugging?
2. High CPU usage after deployment — what will you check?
3. Memory usage keeps increasing — possible causes?
4. How do you identify memory leaks in Java?
5. How do you analyze heap dump?
6. How do you analyze thread dump?
7. What if DB queries suddenly become slow?
8. How do you identify missing indexes?
9. How to debug connection pool exhaustion?
10. What happens if thread pool is exhausted?
11. How do you handle high latency in APIs?
12. What if one microservice is slowing entire system?
13. How to debug timeout issues?
14. How do you handle cascading failures?
15. What is circuit breaker real use case?
16. How do you debug Kafka consumer lag?
17. What if messages are duplicated?
18. How to handle idempotency in retries?
19. What if logs are not sufficient?
20. How to improve observability?
21. How do you implement distributed tracing?
22. What metrics do you monitor in production?
23. How to detect slow endpoints?
24. What is p95 vs p99 latency?
25. How to debug GC pauses?
26. What causes OutOfMemoryError?
27. How to fix memory fragmentation?
28. How to handle traffic spikes?
29. How to scale application quickly?
30. What is autoscaling strategy?
31. What if deployment breaks production?
32. How do you rollback safely?
33. What is blue-green deployment?
34. How to validate canary release?
35. How to handle config issues in prod?
36. What if cache is not working?
37. Cache miss vs cache stampede?
38. How to debug Redis issues?
39. How to prevent stale cache?
40. How to debug authentication failures?
41. What if JWT expires frequently?
42. How to debug CORS issues?
43. How to debug network latency?
44. What if third-party API fails?
45. How to design fallback mechanism?
46. How to implement retry with backoff?
47. What if disk space is full?
48. How to monitor logs efficiently?
49. What tools do you use for debugging?
50. Biggest production issue you handled?
=======================
One of the TOUGHEST scenario questions asked in a Java Backend interview

Question: "Your payment service is processing 10,000 transactions per second. Suddenly response time jumps from 20ms to 8 seconds. No errors in logs. No alerts fired. Business is losing money every second. How do you debug this?"

This question separates tutorial developers from production engineers.
STAR Framework Answer (with real project reflection)

Situation: In production, our payment service response time spiked from 20ms to 8 seconds during peak traffic. No exceptions in logs. No OOM errors. No infrastructure alerts. But transactions were timing out and customers were getting charged without order confirmation.

Task: As the backend engineer on call, my task was to identify the root cause across the entire request chain without taking the service down and restore normal response times within minutes.

Action: First I checked what changed. Recent deployments. Config changes. Traffic patterns. Nothing obvious. Then I followed the request.

Step 1: Check thread pool ExecutorService thread pool was at 100% utilization. All threads waiting. No threads available to process new requests. This was the symptom not the cause.

Step 2: Find what threads were waiting on Thread dump showed all threads blocked on database connection pool. HikariCP pool size was 10. All 10 connections held open. None being released.

Step 3: Find why connections weren't releasing One specific query introduced in last deployment. Missing index on a foreign key column. Full table scan on every transaction. 100ms query became 7 second query under load. Connection held for 7 seconds per transaction. Pool exhausted in seconds.

Step 4: Immediate fix Increased connection pool size temporarily bought 10 minutes. Added database index query dropped from 7 seconds to 8ms. Thread pool cleared instantly. Response time back to 20ms.

Step 5: Permanent fix Added query performance testing to CI/CD pipeline. EXPLAIN ANALYZE on every new query before deployment. Connection pool monitoring alert at 70% utilization. Never caught this way again.

Result: Service restored in 11 minutes. Root cause identified and fixed permanently. Incident led to mandatory query review process for all future deployments. Zero recurrence in 14 months.

This is what separates a developer who builds features from an engineer who owns systems.
Anyone can write code that works at 100 users.
Production engineers write code that survives at 100,000.

The concepts behind this answer thread pools, connection pooling, query optimization, HikariCP internals are exactly what I documented in my Java Backend Engineering Guide.
Not theory. Real production scenarios.
----------------
• Core Java
Explain OOPS concepts with real-time examples Difference between abstract class vs interface
Why String is immutable?
How equals) and hashCode() work internally
Difference between HashMap vs ConcurrentHashMap
What is volatile keyword?
Can we override a static method?

• Java 8 & Functional Programming
What are functional interfaces?
Difference between map() vs flatMap()
Use cases of Stream API
Program to find 2nd highest number using streams
Explain Optional and why it is used

• Multithreading & Concurrency
Difference between Runnable vs Callable
How ExecutorService works
What is deadlock and how to avoid it
Difference between synchronized block vs method
Basics of CompletableFuture

• Spring / Spring Boot
Explain IOC & Dependency Injection
Difference between @Component, @Service, @Repository
How Spring Boot auto-configuration works
How @Transactional works internally
Difference between PUT vs PATCH
Global exception handling using @ControllerAdvice

• Hibernate / JPA
Difference between LAZY vs EAGER fetching
What is N+1 problem?
Difference between save ), persist), saveAndFlush ()
Explain 1st level vs 2nd level cache

• SQL
Query to find 2nd highest salary
Difference between INNER JOIN VS LEFT JOIN
What are indexes and their pros/cons

=============================

🔹 Core Java & OOP

- Explain OOP principles.
- Difference between Abstract Class and Interface.
- Difference between final, finally, and finalize.
- How do you create an immutable object?
- String vs StringBuilder vs StringBuffer.
- Types of exceptions in Java.
- How do you handle NullPointerException?

🔹 Java 8 & Best Practices

- How to handle null checks using Optional in Java 8?
- How to handle null checks using annotations (e.g., @NotNull, @NotBlank)?

🔹 Multithreading & Concurrency

- If two threads are using the same resource, how will you handle it?

🔹 Spring Boot & Backend Development

- How to validate incoming JSON requests?
- What is Spring Cloud? How is it useful?
- How do you handle exceptions in a Spring Boot application?
- Difference between @Component, @Service, and @Repository.
- How do microservices communicate with each other?
- What are the different HTTP methods used in REST APIs?
- What is API versioning?

🔹 Design & Architecture

- Explain SOLID principles.
- Singleton vs Prototype scope.
- What is Dependency Injection?
- Explain the Repository Pattern.

🔹 Database & Persistence

- JPA vs Hibernate.
- How do you establish a database connection in            Spring Boot?
- What are stored procedures?
- How do you optimize database queries?
- Lazy vs Eager loading.

🔹 Testing & Tools

- Difference between @Mock and @MockBean.
- Which version control tool are you using?
- How do you handle merge conflicts while pushing code to a developer branch?
- Have you used Jenkins? Why is it used?

🔹 AI in Development

- Are you using AI tools for coding?
- What are their pros, cons, and risks?
- How do you validate AI-generated code?

===========================

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

======================


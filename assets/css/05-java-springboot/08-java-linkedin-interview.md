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
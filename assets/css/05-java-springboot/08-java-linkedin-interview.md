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

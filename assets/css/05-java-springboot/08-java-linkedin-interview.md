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
26. How do you handle partial failures in dependent services?
27. What is the real impact of using too many interceptors?
28. How do you prevent breaking changes during deployments?
29. Why does @ConfigurationProperties fail silently?
30. What Spring Boot decision has caused you a real production issue?


/// =========== Done
🔹 Core Java
 Write a Lambda expression for a functional interface.
 Can you make this reference generic?
 How do you handle changing requirements in Java?
 How do you filter data in Java?
 What is ArrayList? For sorting & filtering, which feature will you use?
 What is the static keyword?
 Difference between StringBuffer and StringBuilder.
 What is an Exception in Java?
 Difference between HashMap and ConcurrentHashMap.
 Difference between ArrayList and LinkedList.
 Heap memory vs Stack memory.
 What is immutability in Java?
 How does immutability provide security?
 Explain Min Heap and Max Heap.

🔹 Spring Boot / JPA / REST
What is JDBC in Spring Boot? Steps to connect.
Default port used in REST APIs?
What happens if port is not mentioned in application.properties?
Default server used by Spring Boot?
Default logging library in Spring Boot?
How do you print logs?
What is Spring Data JPA?
If we don’t write getters & setters, which annotation can we use?
Why do we use Long in JpaRepository<Employee, Long>?
If a table has 100+ fields and performance is slow, how do you fetch only required 3–4 fields?

🔹 REST & Exception Handling
How do you perform Dependency Injection in REST Controller?
Which HTTP methods are supported in REST APIs?
How do you fetch an employee by ID?
How do you throw a custom exception if employee not found write a code.
Explain JDBC flow internally.
Where do we configure DB credentials in Spring Boot?

🔹 Coding Questions
Functional Interface example
Lambda expression
Stream API filter() for ArrayList
Entity, Repository, Service Layer implementation
Custom Exception class
Global Exception Handler using @ExceptionHandler

// ========== Done

🔹 Round 1 – Technical (Java + Spring Boot)
Java Questions
Explain OOP concepts with real examples
Difference between HashMap and ConcurrentHashMap
How does Java handle memory (Heap vs Stack)?
What are functional interfaces?
Difference between == and .equals()
Spring Boot Questions
What is Spring Boot and why is it preferred over Spring?
Explain Spring Boot auto-configuration
What are RESTful APIs?
Difference between @Component, @Service, and @Repository
How do you handle exception handling in Spring Boot?
Microservices Architecture Questions
Monolithic vs Microservices architecture
How do microservices communicate with each other?
What is service discovery?
How do you handle failures in microservices?
What is API Gateway and why is it needed?
DSA (Easy Level)
Reverse a string using Java
Find duplicate elements in an array
Count occurrences of characters in a string

🔹 Round 2 – Techno-Managerial (Streams + Design)
Stream API Questions
What is Java Stream API?
Difference between Stream and Collection
Write a Stream API logic to filter even numbers
How does map() differ from flatMap()?
Parallel Stream Questions (IMPORTANT)
What is a Parallel Stream?
Why do we need Parallel Streams?
How does Parallel Stream work internally?
When should Parallel Streams be avoided?
Difference between Parallel Stream and Multithreading
Consultant / Design Questions
How do you improve performance in a Spring Boot application?
How do you handle large data processing?
How do you explain a technical solution to a client?

🔹 Behavioral Questions (Consultant Focus)
Tell me about a challenging issue you faced in a project
How do you handle production incidents?
How do you manage tight deadlines?
How do you handle disagreements with teammates?
Have you worked with clients directly?

🔹 Round 3 – HR / Background Check
Tell me about yourself
Current role and responsibilities day to day
Why are you looking for a change?
Notice period & availability
Salary expectations

================
HCl Interview

 🔹Java 8 & Core Java 
What are the major features introduced in Java 8?
How do you convert pre–Java 8 code into Lambda expressions? Can you give an example?
What happens if the @FunctionalInterface annotation is not used?
What is a Functional Interface? Name some predefined functional interfaces in Java 8.
How is Java 8 used in multithreading? What improvements were introduced?
What are the differences in methods used in the Collections framework?
What are Stream API intermediate operations? Can you explain with examples?
What is the Optional class? What methods does it provide and where should it be used?

🔹 Collections & Streams 
What is the difference between Collection and Collections in Java?
How do you merge two lists using Java 8 Streams?
(Example: empList3 = empList1 + empList2)

🔹 Spring Boot & JPA 
Which Spring Boot annotations have you used and what are their purposes?
What is @PatchMapping and when should it be used?
What is the difference between @Primary and @Qualifier?
What is the difference between @Component and @Configuration?
What are the different types of beans in Spring?
What are session bean types in Spring Boot?
What is a Many-to-One mapping in JPA?
Which attributes are used in Many-to-One mapping and why?

🔹 REST API Scenario 
How would you design a REST endpoint like:
/employee/details/123?city=kolkata?
How would you treat 123 as an employeeId in a Spring Boot controller?
Can you write a complete Spring Boot REST controller for this endpoint?
Can you explain the controller code line by line?

💡 Depth-Checking Questions
Why should we prefer Streams over traditional loops?
When should we avoid using Optional?
What are common mistakes in REST API design?
How do Java 8 features improve code readability and performance?

==========

𝐧𝐭𝐞𝐫𝐯𝐢𝐞𝐰 𝐐𝐮𝐞𝐬𝐭𝐢𝐨𝐧𝐬 𝐀𝐬𝐤𝐞𝐝
1️⃣ If API response time suddenly increased, how will you debug?
2️⃣ In case one microservice is down, then how will you handle fallback?
3️⃣ If duplicate records are getting inserted, then how do you find the root cause & fix it?
4️⃣ When there is a high DB load, then how do you optimize queries & indexing?
5️⃣ Scenario: Memory leak in production → how will you identify?
6️⃣ Write thread-safe caching mechanism (Java)
7️⃣ Group employees by dept & get max salary with Stream API
8️⃣ Remove duplicates & sort custom object with Stream API
9️⃣ Convert List<List<String>> → List<String> using flatMap
🔟 Write Spring Boot REST API with validation & exception handling
1️⃣1️⃣ Write down code to implement global exception handling
1️⃣2️⃣ Write JPA custom query (JPQL + Native)
1️⃣3️⃣ In case the transaction rollback is not happening, what will be the reason?
1️⃣4️⃣ Explain @Transactional propagation with use case.
1️⃣5️⃣ Design API for high concurrent users (rate limiting, caching)
1️⃣6️⃣ When multiple services are updating the same data, how do you handle data consistency?
1️⃣7️⃣ How will you implement Circuit Breaker in Spring Boot?
1️⃣8️⃣ Difference between Feign vs WebClient (which in high load?)
1️⃣9️⃣ Scenario: Need async processing → how to implement?
2️⃣0️⃣ ExecutorService use case in real-time project

## 1. Observability & Tracing

1. A user reports Order API takes 20 seconds but each microservice team says their service is fast. How do you identify the delay?

2. A request passes through API Gateway → Order → Payment → Inventory. How do you trace the complete flow across services?

3. Only 5% of requests are failing in production. How does distributed tracing help find the root cause?

4. After introducing OpenTelemetry, traces are missing for some services. What would you investigate?

5. An error occurs in production but devs can't reproduce it locally. How do you use ELK/Splunk to investigate?

6. A customer provides an Order ID and says payment failed. How do you find all related logs across services?

7. Application performance suddenly degrades. What logs would you analyze first?

---

## 2. Kafka Debugging

8. Messages are being produced but consumers are not receiving them. How do you debug?

9. Consumer lag suddenly increases during a sale event. What do you do?

10. Duplicate messages are being processed. How do you prevent this?

11. One Kafka partition receives significantly more traffic than others. How do you fix this?

---

## 3. Performance Analysis

12. API response time increased from 200ms to 5 seconds after deployment. How do you diagnose?

13. CPU usage reaches 95% during peak traffic. How do you investigate and resolve?

14. Application works fine in QA but becomes slow in Production. What differences do you check?

15. A Spring Boot endpoint takes 800ms — your target is 100ms. Walk through your optimization process.

16. Your application has high CPU usage during seemingly idle periods. Diagnose it.

17. Your REST API endpoint takes 5 seconds to respond. How do you optimize it?

---

## 4. Memory & JVM

18. Application memory usage continuously increases and never comes down. How do you debug a memory leak?

19. Your Java service OOMs after 3 days. GC logs show full GC every 5 minutes reclaiming less each time. What's wrong?

20. Your application has 2-second GC pauses affecting user experience. How do you reduce them?

21. You're using ThreadLocal in a web application and seeing memory leaks after deployments. Why?

22. You find thousands of threads running in production. How do you investigate?

23. Your Java application is running slowly and consuming increasing memory over time. How do you diagnose and fix it?

24. Your Java application is running out of memory gradually over days. You find heap dumps showing many HashMap instances. What could be the cause and how do you fix it?

---

## 5. Concurrency & Multithreading

25. Your application suddenly stops responding. You suspect a deadlock. How do you detect and fix it?

26. You're using HashMap in a multithreaded application and experiencing data corruption. What's wrong?

27. You get `ConcurrentModificationException` while iterating and removing from an ArrayList. How do you fix it?

28. You're using `stream().parallel()` but it's slower than sequential. Why?

29. You need to implement a producer-consumer pattern for processing 1 million records efficiently.

30. `CompletableFuture.supplyAsync()` for parallel processing performs worse than sequential. What's wrong?

---

## 6. Spring Boot & Transactions

31. You added `@Transactional` to a method but transactions are not being created. What could be the reason?

32. A `@Transactional` method catches Exception and doesn't rethrow. Transaction doesn't rollback. Why?

33. Service A depends on Service B, and Service B depends on Service A. How do you resolve the circular dependency?

34. You notice 1000 database queries when loading 100 entities. How do you fix this?

35. You're using constructor injection and get `BeanCurrentlyInCreationException`. How do you fix it?

---

## 7. Database & Connection Pool

36. Application throws "Cannot get JDBC connection" errors intermittently. What do you check?

37. Connection pool becomes exhausted during peak traffic. How do you diagnose and fix?

38. After deployment, database connections continuously increase and never decrease. What's wrong?

39. You have a table with 1 billion records and queries take 10+ seconds. How do you optimize?

40. You need to add a new column to a table with 1 billion records without downtime. How?

---

## 8. Microservices Patterns

41. Service A calls Service B, but Service B is down. How do you handle this gracefully?

42. How do you design a circuit breaker for inter-service communication with fallback behavior?

43. How do you implement the Saga pattern for a distributed order transaction?

44. You need to transfer money between two microservices. How do you ensure data consistency?

45. How do you implement distributed tracing across 8 microservices, including async Kafka boundaries?

---

## 9. API Gateway & Service Discovery

46. All backend services are healthy, but users receive 502/504 errors. How do you investigate?

47. Authentication works directly against the service but fails through the Gateway. Why?

48. A service registers successfully in Eureka but cannot be discovered by other services.

49. Inter-service communication works locally but fails in Kubernetes. What could be wrong?

---

## 10. Architecture & System Design

50. How would you handle a sudden spike from 10K to 1M RPS? (Black Friday scenario)

51. Design a rate-limiting system for an API gateway serving 50,000 RPS.

52. How would you migrate a monolith to microservices without downtime?

53. Design an event-driven notification system for 10 million users with delivery guarantees.

54. How do you design an idempotent REST API for payment processing?

55. Design a CQRS + Event Sourcing system for an auditable financial ledger.

---

## 11. DevOps & Production

56. You deployed a new version but it's causing errors in production. What do you do first?

57. Your Kubernetes pods are crashing repeatedly. How do you debug?

58. How do you implement blue-green and canary deployments in a Java microservice fleet?

59. Production application suddenly becomes unavailable at midnight every day. How do you diagnose?

60. Your integration tests are failing intermittently in CI/CD. How do you fix flaky tests?

---

## 12. Core Java & Design Patterns

61. Why does `Integer a = 127 == Integer b = 127` print `true`, but `128` print `false`?

62. You implemented a singleton but multiple instances are created in multithreaded tests. What's wrong?

63. You're using a custom object as HashMap key and after modifying a field, you can't retrieve the value. Why?

64. You're using Java 8 Streams and get a `NullPointerException`. How do you prevent it?

65. You're using `Optional` but getting `NullPointerException`. What are common mistakes?

66. After deploying a new version, you get `ClassNotFoundException` for a library that was working before. What's wrong?

67. How would you implement reactive programming in a Spring WebFlux service?

68. How do you use Java 21 virtual threads to prevent thread starvation in mixed I/O and CPU workloads?

69. Why does `Integer a = 127; Integer b = 127; System.out.println(a == b);` print `true`, but `Integer c = 128; Integer d = 128; System.out.println(c == d);` print `false`?


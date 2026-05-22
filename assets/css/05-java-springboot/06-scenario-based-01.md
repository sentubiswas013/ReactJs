# Java Fullstack Developer – 50 Real-Time Scenario-Based Interview Questions

---

## Spring Boot & REST API

**1.** Your REST API is returning `500 Internal Server Error` in production but works fine locally. How do you debug it?

**2.** A client reports that your `/api/orders` endpoint is very slow when the order count exceeds 100,000 records. How do you fix it?

**3.** You need to expose a REST API that accepts a file upload along with JSON metadata in a single request. How do you implement it?

**4.** Your Spring Boot app starts fine but one of the `@Scheduled` tasks stops running after a few hours. What could be the reason and how do you fix it?

**5.** You have a multi-module Maven Spring Boot project. One module needs to share common DTOs with another. How do you structure this?

**6.** A new requirement says all API responses must include a `requestId` for tracing. How do you implement this without modifying every controller?

**7.** Your Spring Boot app needs to support both v1 and v2 of an API simultaneously. How do you implement API versioning?

**8.** You need to call an external third-party REST API that sometimes times out. How do you handle retries and fallback gracefully?

**9.** Your application needs to process 10,000 records from a CSV file uploaded by the user without blocking the HTTP thread. How do you handle this?

**10.** A `@Transactional` method is not rolling back on a `RuntimeException`. What are the possible reasons?

---

## Database & JPA/Hibernate

**11.** Your JPA query is causing an `N+1 select problem`. How do you identify and fix it?

**12.** You need to migrate an existing database schema without losing data when deploying a new version. How do you handle this using Flyway or Liquibase?

**13.** Two microservices need to update their own databases atomically as part of one business transaction. How do you handle distributed transactions?

**14.** A `LazyInitializationException` is thrown when accessing a collection outside a transaction. How do you resolve it?

**15.** Your application needs to support multi-tenancy where each tenant has its own schema. How do you implement this in Spring Boot + JPA?

**16.** A query using `LIKE '%keyword%'` is very slow on a large table. How do you optimize it?

**17.** You need to implement soft delete (mark as deleted instead of removing rows). How do you implement this cleanly in Spring Data JPA?

**18.** Your application needs to handle optimistic locking to prevent lost updates in a concurrent environment. How do you implement it?

**19.** You need to bulk insert 50,000 records efficiently using JPA. What approach do you take?

**20.** A developer used `FetchType.EAGER` everywhere. What problems can this cause and how do you fix it?

---

## Security

**21.** Your Spring Security config is blocking all requests even after adding `permitAll()`. What could be wrong?

**22.** You need to implement JWT-based authentication. A user logs out but the token is still valid until expiry. How do you handle token invalidation?

**23.** A user with `ROLE_USER` is able to access an admin endpoint. How do you investigate and fix this?

**24.** Your application needs to support OAuth2 login via Google and also allow username/password login. How do you configure both?

**25.** How do you prevent SQL injection in a Spring Boot application that uses native queries?

**26.** Your API is being hit by a bot making thousands of requests per minute. How do you implement rate limiting?

**27.** Sensitive data like passwords and API keys are hardcoded in `application.properties`. How do you fix this for production?

**28.** You need to implement row-level security so users can only access their own data. How do you enforce this in Spring Boot?

---

## Microservices

**29.** One of your microservices is down and it's causing a cascading failure across other services. How do you prevent this?

**30.** You need to implement service discovery so microservices can find each other dynamically. What do you use and how?

**31.** Two microservices need to communicate asynchronously. How do you implement this using Kafka or RabbitMQ?

**32.** You need to aggregate data from three different microservices into a single API response. How do you do this efficiently?

**33.** Your microservice deployment requires zero downtime. How do you achieve this with Spring Boot on Kubernetes?

**34.** How do you implement distributed tracing across multiple microservices to track a single user request end-to-end?

**35.** A microservice needs to maintain its own copy of reference data from another service. How do you keep it in sync?

**36.** You need to implement the Saga pattern for a multi-step order processing workflow. How do you approach it?

---

## React (Frontend)

**37.** Your React app re-renders too frequently, causing performance issues. How do you diagnose and fix it?

**38.** You need to fetch data from a Spring Boot API and display it in a table with pagination, sorting, and filtering. How do you implement this?

**39.** A user fills out a long form and accidentally refreshes the page, losing all data. How do you prevent this?

**40.** Your React app needs to show real-time notifications from the backend. How do you implement this (WebSocket / SSE)?

**41.** You need to protect certain React routes so only authenticated users can access them. How do you implement this with JWT?

**42.** Your React app makes multiple API calls on page load, causing a waterfall effect. How do you optimize this?

**43.** You need to implement an infinite scroll list that loads more items as the user scrolls down. How do you build this?

**44.** A React component is throwing `Cannot read properties of undefined`. How do you debug and handle this gracefully?

---

## DevOps & Deployment

**45.** Your Spring Boot Docker container keeps restarting in production. How do you troubleshoot it?

**46.** You need to configure different `application.properties` for dev, staging, and production environments. How do you manage this?

**47.** Your CI/CD pipeline is failing at the test stage due to a flaky integration test that depends on a real database. How do you fix this?

**48.** Your application memory usage keeps growing over time and eventually crashes. How do you detect and fix a memory leak in a Spring Boot app?

**49.** You need to implement a blue-green deployment strategy for your Spring Boot application. How do you set this up?

**50.** Your application logs are scattered across multiple pods in Kubernetes. How do you centralize and search them effectively?

---

> **Tip:** For each scenario, structure your answer as: **Problem → Root Cause Analysis → Solution → Best Practice**

# ✅ 1. Java Architecture 

1. Load Balancing
2. Caching
3. Content Delivery Network (CDN)
4. Message Queue
5. Publish-Subscribe (Pub/Sub)
6. API Gateway
7. Circuit Breaker
8. Service Discovery
9. Sharding
10. Rate Limiting
11. Consistent Hashing
12. Auto Scaling



## 1. What happens when a user enters a URL in the browser?

When you type a URL in the browser and press Enter, many things happen behind the scenes before the webpage appears.

```text
https://www.google.com
```


When a user enters a URL in the browser, the browser follows several steps to load the webpage:

1. **DNS Lookup** – The browser converts the domain name (e.g., `google.com`) into an IP address.
2. **TCP Connection** – The browser establishes a connection with the server.
3. **SSL/TLS Handshake** (for HTTPS) – A secure connection is created between the browser and the server.
4. **HTTP Request** – The browser sends an HTTP request to the server.
5. **Server Processing** – The server processes the request, executes business logic, and fetches data from databases if needed.
6. **HTTP Response** – The server sends back HTML, CSS, JavaScript, and other resources.
7. **Browser Rendering** – The browser parses the content, builds the page, and displays it to the user.

**Short Flow Diagram**

```text
User Enters URL
        ↓
DNS Lookup
        ↓
TCP Connection
        ↓
SSL/TLS Handshake (HTTPS)
        ↓
HTTP Request
        ↓
Server Processing
        ↓
HTTP Response
        ↓
Browser Renders Page
```


**High-Level Flow**

```text
User types: https://www.example.com/products?id=42
│
┌──────────────────────────────────────────────┐
│ 1. User                                      │
│ - Enters URL in browser                      │
│ - Presses Enter                              │
└────────────────┬─────────────────────────────┘
                 │
┌────────────────▼─────────────────────────────┐
│ 2. Browser                                   │
│ - Parses URL                                 │
│ - Checks browser cache                       │
│ - Checks DNS cache                           │
└────────────────┬─────────────────────────────┘
                 │
┌────────────────▼─────────────────────────────┐
│ 3. DNS Lookup                                │
│ - Finds domain IP address                    │
│ - Contacts DNS server if needed              │
│ - Resolves www.example.com                   │
└────────────────┬─────────────────────────────┘
                 │
┌────────────────▼─────────────────────────────┐
│ 4. Get Server IP Address                     │
│ - Example: 142.250.183.78                    │
│ - Browser now knows target server            │
└────────────────┬─────────────────────────────┘
                 │
┌────────────────▼─────────────────────────────┐
│ 5. TCP Connection                            │
│ - Starts 3-way handshake                     │
│ - SYN → SYN-ACK → ACK                        │
│ - Reliable connection established            │
└────────────────┬─────────────────────────────┘
                 │
┌────────────────▼─────────────────────────────┐
│ 6. SSL/TLS Handshake (HTTPS)                 │
│ - Server sends SSL certificate               │
│ - Browser validates certificate              │
│ - Encryption keys exchanged                  │
│ - Secure connection established              │
└────────────────┬─────────────────────────────┘
                 │
┌────────────────▼─────────────────────────────┐
│ 7. HTTP Request                              │
│ - Sends GET /products?id=42                  │
│ - Includes headers & cookies                 │
│ - Authentication token may be included       │
└────────────────┬─────────────────────────────┘
                 │
┌────────────────▼─────────────────────────────┐
│ 8. Load Balancer / API Gateway               │
│ - Distributes traffic                        │
│ - Routes request to healthy server           │
│ - Handles authentication/rate limiting       │
└────────────────┬─────────────────────────────┘
                 │
┌────────────────▼─────────────────────────────┐
│ 9. Web Server                                │
│ - NGINX / Apache receives request            │
│ - Handles static files                       │
│ - Forwards dynamic requests                  │
└────────────────┬─────────────────────────────┘
                 │
┌────────────────▼─────────────────────────────┐
│ 10. Application Server                       │
│ - Spring Boot/Node.js app executes logic     │
│ - Controller → Service → Repository          │
│ - Processes business rules                   │
└────────────────┬─────────────────────────────┘
                 │
┌────────────────▼─────────────────────────────┐
│ 11. Database / Cache                         │
│ - Queries MySQL/PostgreSQL                   │
│ - Reads cache from Redis if available        │
│ - Fetches required data                      │
└────────────────┬─────────────────────────────┘
                 │
┌────────────────▼─────────────────────────────┐
│ 12. HTTP Response                            │
│ - Returns HTML/JSON/CSS/JS                   │
│ - Response code: 200 OK                      │
│ - Data sent back to browser                  │
└────────────────┬─────────────────────────────┘
                 │
┌────────────────▼─────────────────────────────┐
│ 13. Browser Renders Page                     │
│ - Parses HTML                                │
│ - Builds DOM & CSSOM                         │
│ - Executes JavaScript                        │
│ - Displays final webpage                     │
└──────────────────────────────────────────────┘
```


## 2. System Design Diagram of your application?

Here’s a clean and interview-friendly **Generic System Design Diagram** you can use for most HLD interviews like WhatsApp, Netflix, Uber, Food Delivery, E-commerce, etc.

---

**Generic System Design Interview Diagram**

```text
                         +-------------------+
                         |       Client      |
                         | Web / Mobile App  |
                         +---------+---------+
                                   |
                                   v
                         +-------------------+
                         |   CDN / WAF       |
                         | Static Content    |
                         +---------+---------+
                                   |
                                   v
                         +-------------------+
                         |   Load Balancer   |
                         +---------+---------+
                                   |
                                   v
                         +-------------------+
                         |    API Gateway    |
                         | Auth / Routing    |
                         | Rate Limiting     |
                         +---------+---------+
                                   |
         ---------------------------------------------------
         |                 |                |               |
         v                 v                v               v

+----------------+ +----------------+ +----------------+ +----------------+
| User Service   | | Order Service  | | Payment Service| | Notification   |
| Spring Boot    | | Spring Boot    | | Spring Boot    | | Service        |
+-------+--------+ +-------+--------+ +-------+--------+ +-------+--------+
        |                  |                  |                  |
        ----------------------------------------------------------
                                   |
                                   v

                         +-------------------+
                         |   Message Queue   |
                         | Kafka/RabbitMQ    |
                         +---------+---------+
                                   |
                ----------------------------------------
                |                                      |
                v                                      v

      +-------------------+                 +-------------------+
      | Cache Layer       |                 | Search Engine     |
      | Redis             |                 | Elasticsearch     |
      +---------+---------+                 +---------+---------+
                |                                      |
                ----------------------------------------
                                   |
                                   v

                         +-------------------+
                         |   Database Layer  |
                         |  /MySQL  |
                         | MongoDB           |
                         +---------+---------+
                                   |
                                   v

                         +-------------------+
                         | Object Storage    |
                         | S3 / Blob Storage |
                         +-------------------+
```



## 3. How to Start System Design From Scratch in High-Level?

When I design a system, I follow a simple step-by-step approach:

1. **Understand Requirements**

   * Gather functional requirements (what the system should do).
   * Gather non-functional requirements (scalability, security, availability, performance).

2. **Estimate Scale**

   * Calculate expected users, requests per second, storage, and traffic.
   * This helps choose the right architecture and database.

3. **Design High-Level Architecture**

   * Identify major components such as Client, Load Balancer, API Gateway, Services, Database, Cache, and Message Queue.

4. **Design Database & APIs**

   * Choose SQL or NoSQL based on the use case.
   * Define key entities and REST APIs.

5. **Plan for Scalability**

   * Use load balancing, caching, auto-scaling, and database replication/sharding if needed.

6. **Ensure Reliability**

   * Add retries, circuit breakers, failover, backups, and monitoring.

7. **Secure the System**

   * Implement authentication, authorization, HTTPS, encryption, and rate limiting.

8. **Design Low-Level Components**

   * Apply SOLID principles, design patterns, and clean architecture.

```text
Requirements
    ↓
Scale Estimation
    ↓
High-Level Design
    ↓
Database & APIs
    ↓
Scalability
    ↓
Reliability
    ↓
Security
    ↓
Low-Level Design
```


## 4. How to Start System Design From Scratch in details?

**Diagram 1**
```text
╔══════════════════════════════════════════════════════════════════════════════╗
║                    COMPLETE SYSTEM DESIGN ROADMAP                           ║
║          Scalable • Secure • Distributed • Cloud Native                     ║
╚══════════════════════════════════════════════════════════════════════════════╝


 ┌──────────────────────────────────────────────────────────────────────────┐
 │ 1. REQUIREMENTS GATHERING                                               │
 ├──────────────────────────────────────────────────────────────────────────┤
 │ Functional Requirements                                                 │
 │ ─ Login                                                                 │
 │ ─ Product Search                                                        │
 │ ─ Cart Management                                                       │
 │ ─ Order Placement                                                       │
 │ ─ Payment Processing                                                    │
 │ ─ Order Tracking                                                        │
 │                                                                          │
 │ Non-Functional Requirements                                             │
 │ ─ Scalability                                                           │
 │ ─ High Availability                                                     │
 │ ─ Low Latency                                                           │
 │ ─ Security                                                              │
 │ ─ Fault Tolerance                                                       │
 └───────────────────────────────┬──────────────────────────────────────────┘
                                 │
                                 ▼

 ┌──────────────────────────────────────────────────────────────────────────┐
 │ 2. CAPACITY & SCALE ESTIMATION                                          │
 ├──────────────────────────────────────────────────────────────────────────┤
 │ Users              → 1 Million DAU                                      │
 │ Requests           → 5000 RPS                                           │
 │ Orders             → 100K / day                                         │
 │ Storage            → TB / PB Scale                                      │
 │ Traffic            → CDN + Compression                                  │
 │ Availability       → 99.99%                                             │
 └───────────────────────────────┬──────────────────────────────────────────┘
                                 │
                                 ▼

 ┌──────────────────────────────────────────────────────────────────────────┐
 │ 3. CORE DOMAIN ENTITIES                                                 │
 ├──────────────────────────────────────────────────────────────────────────┤
 │ User                                                                    │
 │ Product                                                                 │
 │ Inventory                                                               │
 │ Cart                                                                    │
 │ Order                                                                   │
 │ Payment                                                                 │
 │ Shipment                                                                │
 │ Notification                                                            │
 └───────────────────────────────┬──────────────────────────────────────────┘
                                 │
                                 ▼

╔══════════════════════════════════════════════════════════════════════════════╗
║                        HIGH LEVEL ARCHITECTURE                              ║
╚══════════════════════════════════════════════════════════════════════════════╝


                    ┌────────────────────┐
                    │    Client Apps     │
                    │ Web / Mobile / API │
                    └─────────┬──────────┘
                              │
                              ▼
                    ┌────────────────────┐
                    │        CDN         │
                    │  Static Content    │
                    └─────────┬──────────┘
                              │
                              ▼
                    ┌────────────────────┐
                    │   Load Balancer    │
                    │ Nginx / AWS ALB    │
                    └─────────┬──────────┘
                              │
                              ▼
                    ┌────────────────────┐
                    │    API Gateway     │
                    │ Auth • Routing     │
                    │ Rate Limiting      │
                    └─────────┬──────────┘
                              │
      ┌───────────────────────┼────────────────────────┐
      │                       │                        │
      ▼                       ▼                        ▼

┌───────────────┐   ┌────────────────┐      ┌────────────────┐
│ Auth Service  │   │ Product Service│      │ Order Service  │
│ JWT/OAuth2    │   │ Search/Catalog │      │ Order Mgmt     │
└──────┬────────┘   └───────┬────────┘      └───────┬────────┘
       │                    │                        │
       ▼                    ▼                        ▼

┌───────────────┐   ┌────────────────┐      ┌────────────────┐
│ User DB       │   │ Product DB     │      │ Order DB       │
│ PostgreSQL    │   │ MongoDB        │      │ PostgreSQL     │
└───────────────┘   └────────────────┘      └────────────────┘


                              │
                              ▼

                   ┌─────────────────────┐
                   │   Message Broker    │
                   │ Kafka / RabbitMQ    │
                   └─────────┬───────────┘
                             │
      ┌──────────────────────┼──────────────────────────┐
      │                      │                          │
      ▼                      ▼                          ▼

┌───────────────┐   ┌────────────────┐      ┌────────────────┐
│ Payment Svc   │   │ Inventory Svc  │      │ Notification   │
│ Transactions  │   │ Stock Mgmt     │      │ Email/SMS      │
└──────┬────────┘   └───────┬────────┘      └───────┬────────┘
       │                    │                        │
       ▼                    ▼                        ▼

┌───────────────┐   ┌────────────────┐      ┌────────────────┐
│ Payment DB    │   │ Inventory DB   │      │ Notification DB│
└───────────────┘   └────────────────┘      └────────────────┘


╔══════════════════════════════════════════════════════════════════════════════╗
║                          DATA LAYER ARCHITECTURE                            ║
╚══════════════════════════════════════════════════════════════════════════════╝

        ┌────────────────────────────────────────────────┐
        │                 DATABASES                      │
        ├────────────────────────────────────────────────┤
        │ SQL        → PostgreSQL / MySQL               │
        │ NoSQL      → MongoDB                          │
        │ Cache      → Redis                            │
        │ Search     → Elasticsearch                    │
        │ Object     → S3 / Blob Storage                │
        └────────────────────────────────────────────────┘


╔══════════════════════════════════════════════════════════════════════════════╗
║                           SCALABILITY LAYER                                 ║
╚══════════════════════════════════════════════════════════════════════════════╝

 ┌──────────────────────────────────────────────────────────────────────────┐
 │ Horizontal Scaling                                                      │
 │ Kubernetes Auto Scaling                                                 │
 │ Multiple Service Replicas                                               │
 │ Distributed Cache                                                       │
 │ Read Replicas                                                           │
 │ Partitioning & Sharding                                                 │
 └──────────────────────────────────────────────────────────────────────────┘


╔══════════════════════════════════════════════════════════════════════════════╗
║                         PERFORMANCE OPTIMIZATION                            ║
╚══════════════════════════════════════════════════════════════════════════════╝

 ┌──────────────────────────────────────────────────────────────────────────┐
 │ Redis Cache                                                             │
 │ CDN                                                                     │
 │ DB Indexing                                                             │
 │ Compression                                                             │
 │ Lazy Loading                                                            │
 │ Async Processing                                                        │
 │ Connection Pooling                                                      │
 └──────────────────────────────────────────────────────────────────────────┘


╔══════════════════════════════════════════════════════════════════════════════╗
║                       RELIABILITY & FAULT TOLERANCE                         ║
╚══════════════════════════════════════════════════════════════════════════════╝

 ┌──────────────────────────────────────────────────────────────────────────┐
 │ Retry Mechanism                                                         │
 │ Circuit Breaker (Resilience4j)                                          │
 │ Kafka Replication                                                       │
 │ Database Replication                                                    │
 │ Backup & Recovery                                                       │
 │ Failover                                                                │
 │ Distributed Transactions (Saga Pattern)                                 │
 └──────────────────────────────────────────────────────────────────────────┘


╔══════════════════════════════════════════════════════════════════════════════╗
║                              SECURITY LAYER                                 ║
╚══════════════════════════════════════════════════════════════════════════════╝

 ┌──────────────────────────────────────────────────────────────────────────┐
 │ Authentication → JWT / OAuth2                                           │
 │ Authorization  → RBAC                                                   │
 │ HTTPS                                                                    │
 │ API Rate Limiting                                                       │
 │ Encryption                                                              │
 │ Secrets Management                                                      │
 │ WAF Protection                                                          │
 └──────────────────────────────────────────────────────────────────────────┘


╔══════════════════════════════════════════════════════════════════════════════╗
║                         MONITORING & OBSERVABILITY                          ║
╚══════════════════════════════════════════════════════════════════════════════╝

 ┌──────────────────────────────────────────────────────────────────────────┐
 │ Metrics        → Prometheus                                             │
 │ Dashboards     → Grafana                                                │
 │ Logs           → ELK Stack                                              │
 │ Tracing        → Zipkin / Jaeger                                        │
 │ Alerts         → PagerDuty / Slack                                      │
 │ Cloud Monitor  → CloudWatch                                             │
 └──────────────────────────────────────────────────────────────────────────┘


╔══════════════════════════════════════════════════════════════════════════════╗
║                             DEPLOYMENT PIPELINE                             ║
╚══════════════════════════════════════════════════════════════════════════════╝

 Developer
     │
     ▼
 GitHub / GitLab
     │
     ▼
 Jenkins / GitHub Actions
     │
     ▼
 Docker Build
     │
     ▼
 Container Registry
     │
     ▼
 Kubernetes Cluster
     │
     ▼
 Production Deployment


╔══════════════════════════════════════════════════════════════════════════════╗
║                            LOW LEVEL DESIGN                                 ║
╚══════════════════════════════════════════════════════════════════════════════╝

 ┌──────────────────────────────────────────────────────────────────────────┐
 │ SOLID Principles                                                        │
 │ Design Patterns                                                         │
 │ UML Diagrams                                                            │
 │ OOP Concepts                                                            │
 │ Interfaces & Abstractions                                               │
 │ Clean Architecture                                                      │
 └──────────────────────────────────────────────────────────────────────────┘


╔══════════════════════════════════════════════════════════════════════════════╗
║                         FINAL SYSTEM OUTPUT                                 ║
╚══════════════════════════════════════════════════════════════════════════════╝

        Scalable + Secure + Reliable + Maintainable
              Cloud-Native Distributed System

```


System design should start with understanding requirements, estimating scale, designing high-level architecture, choosing databases/APIs, and then improving scalability, reliability, and maintainability.


**1. Understand Requirements**

First ask questions.

**Functional Requirements**

What system should do?

Example for Food Delivery:

* User login
* Search restaurants
* Place order
* Payment
* Track delivery

**Non-Functional Requirements**

How system should behave?

* Scalability
* Security
* High availability
* Low latency
* Fault tolerance

---

**2. Estimate Scale**

Estimate:

* Daily active users
* Requests per second (RPS)
* Storage
* Traffic

Example:

```text id="7g1vgh"
1 million users
100k daily orders
500 requests/sec
```

This helps decide architecture.

---

**3. Identify Core Entities**

Find main objects.

Example for E-commerce:

```text id="o49gpk"
User
Product
Cart
Order
Payment
Inventory
```

---

**4. Design High-Level Architecture (HLD)**

Draw big components.

Example:

```text id="h83x71"
Client → Load Balancer → API Gateway
                        ↓
              Microservices
                        ↓
              Database / Cache / Queue
```

Components:

* Frontend
* Backend
* Database
* Cache
* Messaging queue
* CDN
* Storage

---

**5. Database Design**

Choose DB:

| Use Case           | Database         |
| ------------------ | ---------------- |
| Structured data    | MySQL/PostgreSQL |
| Huge scalable data | MongoDB          |
| Fast caching       | Redis            |
| Search             | Elasticsearch    |

Create tables/schema.

Example:

```text id="n5s2uw"
User(id, name, email)
Order(id, userId, total)
```

---

**6. API Design**

Design REST APIs.

Example:

```http id="9qkhtm"
POST /orders
GET /products
PUT /cart
```

Think about:

* Request
* Response
* Status codes
* Pagination

---

**7. Decide Architecture Style**

Choose:

| Type          | When Used              |
| ------------- | ---------------------- |
| Monolith      | Small projects         |
| Microservices | Large scalable systems |
| Event Driven  | Async processing       |

---

**8. Add Scalability**

Think:

**Horizontal Scaling**

```text id="ggxyl4"
Multiple backend servers
```

Use:

* Load balancer
* Auto scaling

---

**9. Add Performance Optimization**

Use:

* Redis cache
* CDN
* DB indexing
* Lazy loading
* Compression

---

**10. Handle Reliability**

Add:

* Retry mechanism
* Circuit breaker
* Replication
* Backup
* Failover

---

**11. Security Design**

Think about:

* Authentication
* Authorization
* JWT/OAuth
* HTTPS
* Rate limiting

---

**12. Monitoring & Logging**

Use:

* ELK Stack
* Prometheus
* Grafana
* CloudWatch

---

**13. Deep Dive (LLD)**

Now design classes.

Example:

```java id="crd5dn"
interface PaymentStrategy {
    void pay();
}
```

Use:

* SOLID principles
* Design patterns
* UML
* OOP

---

**Example Interview Flow**

If interviewer asks:

Design WhatsApp

You should answer in order:

1. Requirements
2. Scale estimation
3. HLD
4. DB design
5. Message flow
6. Real-time communication
7. Scaling
8. Reliability
9. Security

---

**Common Technologies**

| Component  | Technology     |
| ---------- | -------------- |
| API        | Spring Boot    |
| Database   | PostgreSQL     |
| Cache      | Redis          |
| Queue      | Kafka/RabbitMQ |
| Search     | Elasticsearch  |
| Storage    | S3             |
| Monitoring | Grafana        |

---

**Golden Rule**

Start with:

```text id="c0f6wj"
Requirements → Scale → HLD → DB → APIs → Scaling → Reliability → Security
```

## 5. Describe the Architecture of Your Recent Project

In my recent project, we followed a **Microservices Architecture**.

The system was divided into multiple independent services such as **User Service**, **Order Service**, **Payment Service**, and **Notification Service**. Each service had its own business logic and database.

Client requests first came through an **API Gateway**, which handled routing, authentication, and security.

Services communicated with each other using **REST APIs** for synchronous communication and **Kafka** for asynchronous event-driven communication.

We used **Spring Boot** for developing microservices, **Spring Data JPA/Hibernate** for database operations, and **MySQL** as the database.

To improve performance, we used **Redis Cache**. For service discovery, we used **Eureka**. Applications were containerized using **Docker** and deployed through a **CI/CD pipeline** using Jenkins.

Monitoring and logging were handled using tools like **ELK Stack** and **Prometheus/Grafana**.

**Architecture Flow**

```text
Client
   ↓
API Gateway
   ↓
Microservices
(User, Order, Payment, Notification)
   ↓
MySQL Database
   ↓
Kafka (Event Communication)
   ↓
Redis Cache
```

# ✅ 2. Java Principal Engineer interviews

# 1. Technology Selection

## 1. If Node.js and Java both can build APIs, why choose Java?

**Interview Answer:**

I would choose Java when I need enterprise-grade applications, high scalability, strong type safety, and long-term maintainability. Java has a mature ecosystem like Spring Boot, better multithreading support, and is commonly used in banking, insurance, and large-scale systems.

Node.js is excellent for lightweight APIs and real-time applications, but Java is usually preferred for complex business systems handling heavy workloads.

**Example:** Banking, payment gateways, large e-commerce platforms.

---

## 2. If MySQL and PostgreSQL both store data, why choose PostgreSQL?

**Interview Answer:**

I would choose PostgreSQL when I need advanced SQL features, better data integrity, complex queries, JSON support, and high reliability.

MySQL is simpler and faster for basic CRUD applications, but PostgreSQL is more feature-rich and enterprise-oriented.

**Example:** Financial systems, analytics platforms, ERP systems.

---

## 3. If PostgreSQL supports JSON, why use MongoDB?

**Interview Answer:**

PostgreSQL supports JSON, but it is still primarily a relational database.

MongoDB is designed for document-based storage where the schema changes frequently. It provides better flexibility for storing semi-structured and rapidly evolving data.

**Example:**

* PostgreSQL → Order Management System
* MongoDB → Product Catalog with varying attributes

---

## 4. If SQL databases scale well, why use NoSQL?

**Interview Answer:**

SQL databases scale very well vertically and can scale horizontally with additional effort.

NoSQL databases are chosen when we need massive horizontal scaling, flexible schemas, and very high write throughput across distributed systems.

**Example:**

* SQL → Banking System
* NoSQL → Social Media Platform storing billions of posts

---

## 5. If Redis exists, why use Memcached?

**Interview Answer:**

Redis provides caching plus advanced features like persistence, pub/sub, streams, transactions, and data structures.

Memcached is simpler and consumes less memory for pure key-value caching. If I only need a lightweight cache, Memcached can be a good choice.

**Example:**

* Redis → Session Store, Rate Limiting
* Memcached → Simple Page Caching

---

## 6. If GraphQL exists, why still use REST?

**Interview Answer:**

GraphQL gives clients flexibility to request exactly the data they need.

REST is simpler, easier to cache, easier to secure, and widely adopted. For straightforward CRUD APIs, REST is usually the better choice.

**Example:**

* REST → Employee Management System
* GraphQL → Mobile App requiring customized responses

---

## 7. If REST works, why use gRPC?

**Interview Answer:**

REST is ideal for communication between browsers and external clients.

gRPC uses Protocol Buffers and HTTP/2, making it much faster and more efficient for service-to-service communication in microservices architectures.

**Example:**

* REST → Frontend ↔ Backend
* gRPC → Microservice ↔ Microservice

---

## 8. If OAuth2 exists, why use JWT?

**Interview Answer:**

OAuth2 is an authorization framework that defines how access is granted.

JWT is a token format often used within OAuth2. They solve different problems and are commonly used together.

**Simple Rule:**

* OAuth2 → Authorization mechanism
* JWT → Token carrying user information

**Example:** Login using Google OAuth2 returns JWT access tokens.

---

## 9. If Cloud is available, why use On-Premise?

**Interview Answer:**

Cloud provides scalability, flexibility, and reduced infrastructure management.

On-Premise is chosen when organizations require strict security, regulatory compliance, complete infrastructure control, or low-latency access to local systems.

**Example:**

* Cloud → Startup applications
* On-Premise → Government, Defense, Banking

---

## 10. If Kubernetes exists, why use Serverless?

**Interview Answer:**

Kubernetes provides full control over infrastructure, networking, deployment, and scaling.

Serverless removes infrastructure management completely and automatically scales based on requests. It's ideal for event-driven workloads.

**Example:**

* Kubernetes → Large Microservices Platform
* Serverless → Image Processing, Scheduled Jobs, Event Handlers

---

## One-Line Architect Summary

| Question                   | Choose When                             |
| -------------------------- | --------------------------------------- |
| Java vs Node.js            | Complex enterprise systems              |
| PostgreSQL vs MySQL        | Advanced SQL and reliability            |
| MongoDB vs PostgreSQL JSON | Flexible document data                  |
| NoSQL vs SQL               | Massive horizontal scale                |
| Memcached vs Redis         | Simple lightweight caching              |
| GraphQL vs REST            | Client-specific data needs              |
| gRPC vs REST               | High-performance internal communication |
| JWT vs OAuth2              | JWT = Token, OAuth2 = Authorization     |
| On-Premise vs Cloud        | Compliance and full control             |
| Serverless vs Kubernetes   | No infrastructure management needed     |

**Architect mindset:** There is no "best" technology. Every technology is chosen based on **business requirements, scalability needs, operational complexity, cost, and team expertise.**


# 2. Architecture & System Design

## 11. If Monolith can handle the business, why choose Microservices?

**Interview Answer:**

I would choose Microservices when different modules need to scale independently, be deployed separately, or be owned by different teams.

A Monolith is simpler and works well initially, but as the system grows, deployments, scaling, and maintenance can become difficult.

**Example:**

* Monolith → Small e-commerce startup
* Microservices → Amazon-like platform with Orders, Payments, Inventory, and Shipping services

---

## 12. If Microservices are modern, why start with a Monolith?

**Interview Answer:**

I usually start with a Monolith because it is faster to develop, easier to test, and simpler to deploy.

Many companies adopt Microservices too early and end up managing unnecessary complexity.

First prove the business, then split services when there is a clear need.

**Example:**

* Startup with 5 developers → Monolith
* Company with 100 developers and scaling issues → Microservices

**Rule:**

Start simple, evolve when required.

---

## 13. If Microservices work well, why consider a Modular Monolith?

**Interview Answer:**

A Modular Monolith provides clear module boundaries while keeping deployment simple.

It gives many benefits of Microservices without network calls, distributed transactions, service discovery, or operational overhead.

**Example Modules:**

* User Module
* Product Module
* Order Module
* Payment Module

All run in one application but remain logically separated.

**Rule:**

Modular Monolith is often the best middle ground between Monolith and Microservices.

---

## 14. If REST APIs work fine, why choose Event-Driven Architecture?

**Interview Answer:**

REST is synchronous. The caller waits for a response.

Event-Driven Architecture is asynchronous. Services publish events and continue processing without waiting.

This reduces coupling and improves scalability.

**Example:**

Order Created

Instead of:

```
Order → Payment → Inventory → Notification
```

Use:

```
Order Created Event

Payment Service consumes
Inventory Service consumes
Notification Service consumes
```

**Benefit:**

* Loose coupling
* Better scalability
* Easier integration of new services

---

## 15. If Event-Driven Architecture exists, why use Synchronous Communication?

**Interview Answer:**

Event-Driven communication is great when immediate responses are not required.

Synchronous communication is necessary when the caller needs an instant result.

**Example:**

Login API:

```
Client → Auth Service
```

User cannot wait for an event.

Need immediate response:

```
Success or Failure
```

**Rule:**

* Need immediate answer → Synchronous
* Can process later → Event-Driven

---

## 16. If Service Discovery exists, why use an API Gateway?

**Interview Answer:**

Service Discovery helps services find each other.

API Gateway manages external traffic entering the system.

**API Gateway Responsibilities:**

* Authentication
* Authorization
* Rate Limiting
* Request Routing
* Logging
* SSL Termination

**Example:**

Without Gateway:

```
Client → 20 Microservices
```

With Gateway:

```
Client → API Gateway → Microservices
```

**Rule:**

Service Discovery is for internal communication, API Gateway is for external access.

---

## 17. If Distributed Systems scale better, why keep some systems centralized?

**Interview Answer:**

Distributed systems improve scalability but introduce complexity such as network failures, consistency issues, monitoring, and debugging challenges.

Some components are better centralized to maintain a single source of truth.

**Examples:**

* User Authentication
* Configuration Management
* Master Data
* Audit Logs

**Rule:**

Distribute what needs scale; centralize what needs consistency.

---

## 18. If One Database can store everything, why use Polyglot Persistence?

**Interview Answer:**

Different databases are optimized for different workloads.

Polyglot Persistence means choosing the best database for each use case instead of forcing one database to handle everything.

**Example**

| Requirement     | Database      |
| --------------- | ------------- |
| Transactions    | PostgreSQL    |
| Caching         | Redis         |
| Product Catalog | MongoDB       |
| Search          | Elasticsearch |
| Relationships   | Neo4j         |

**Example Architecture:**

```
Orders      → PostgreSQL
Cache       → Redis
Products    → MongoDB
Search      → Elasticsearch
```

**Rule:**

Use the right database for the right problem.

---

**Architect-Level Summary** (One-Liners)

| Question                          | Short Answer                                    |
| --------------------------------- | ----------------------------------------------- |
| Monolith vs Microservices         | Independent scaling and deployments             |
| Why start with Monolith           | Simplicity and faster delivery                  |
| Modular Monolith vs Microservices | Separation without distributed complexity       |
| REST vs Event-Driven              | Immediate response vs asynchronous processing   |
| Event-Driven vs Sync              | Real-time response requirements                 |
| Service Discovery vs API Gateway  | Internal service lookup vs external entry point |
| Distributed vs Centralized        | Scalability vs consistency                      |
| One DB vs Polyglot Persistence    | Best database for each workload                 |

**Architecture Golden Rule**

**Don't choose architecture because it is popular. Choose it because it solves a specific business, scalability, reliability, or operational problem.**

# 3. Messaging & Event Streaming

## 19. If REST APIs are enough, why introduce Kafka?

**Interview Answer:**

REST works well for synchronous communication where the caller needs an immediate response.

Kafka is used when systems need asynchronous communication, high throughput, event streaming, and loose coupling between services.

Instead of services calling each other directly, they communicate through events.

**Example**

Without Kafka:

```text
Order Service → Payment Service → Inventory Service → Notification Service
```

With Kafka:

```text
Order Service → Kafka Topic

Payment Service consumes
Inventory Service consumes
Notification Service consumes
```

**Benefit**

* Loose coupling
* Better scalability
* High throughput
* Event replay capability

**Rule**

REST for request-response. Kafka for event-driven communication.

---

## 20. If Kafka handles events, why use REST at all?

**Interview Answer:**

Kafka is not a replacement for REST.

Kafka is asynchronous, while REST is synchronous.

Users and external systems often need an immediate response, which Kafka cannot provide directly.

**Example**

Customer Login:

```text
Client → Auth Service
```

Customer expects:

```text
Login Success
```

immediately.

Using Kafka would introduce unnecessary delay.

**Rule**

* Need instant response → REST
* Can process later → Kafka

**Real System**

```text
Client → REST API

REST API → Kafka Event
```

Both usually work together.

---

## 21. If Kafka exists, why use RabbitMQ?

**Interview Answer:**

Kafka is designed for event streaming and handling massive volumes of events.

RabbitMQ is designed for reliable message delivery and complex routing patterns.

**Choose Kafka When**

* Millions of events per second
* Event replay required
* Log aggregation
* Analytics pipelines
* Event sourcing

**Choose RabbitMQ When**

* Task queues
* Job processing
* Email sending
* Workflow orchestration
* Complex message routing

**Example**

```text
User Registration
        ↓
RabbitMQ
        ↓
Send Welcome Email
```

```text
Website Click Events
        ↓
Kafka
        ↓
Analytics Platform
```

**Rule**

Kafka = Event Streaming Platform.

RabbitMQ = Message Queue.

---

## 22. If Kafka stores messages, why use a Database?

**Interview Answer:**

Kafka stores events, not business data.

Databases store the current state of the application.

**Example**

Kafka Event:

```text
Order Created
Order Paid
Order Shipped
```

Database Record:

```text
Order ID: 101
Status: SHIPPED
```

**Difference**

| Kafka               | Database          |
| ------------------- | ----------------- |
| Event History       | Current State     |
| Append Only         | CRUD Operations   |
| Stream Processing   | Business Queries  |
| Temporary Retention | Permanent Storage |

**Rule**

Kafka tells us what happened.

Database tells us what the current state is.

---

## 23. If Retries exist, why use Circuit Breakers?

**Interview Answer:**

Retries help recover from temporary failures.

But if a service is completely down, retries can make the situation worse by sending even more requests.

Circuit Breaker prevents repeated calls to a failing service.

**Example**

Without Circuit Breaker:

```text
Payment Service Down

Order Service
  → Retry 1
  → Retry 2
  → Retry 3
  → Retry 4
```

This increases load and delays recovery.

**With Circuit Breaker**

```text
Payment Service Down

Circuit Opens
↓
Fail Fast
↓
Fallback Response
```

**Rule**

* Retry = Handle temporary failures
* Circuit Breaker = Stop calling unhealthy services

**Real Production Pattern**

```text
Retry
   +
Timeout
   +
Circuit Breaker
   +
Fallback
```

Used together for resilient microservices.

---

**Architect-Level Summary**

| Question                 | Short Answer                                             |
| ------------------------ | -------------------------------------------------------- |
| REST vs Kafka            | Request-response vs event streaming                      |
| Kafka vs REST            | Immediate response vs async processing                   |
| Kafka vs RabbitMQ        | Event streaming vs message queue                         |
| Kafka vs Database        | Event history vs current state                           |
| Retry vs Circuit Breaker | Recover temporary failures vs prevent cascading failures |

# 4. Database & Data Architecture

## 24. If Database queries work fine, why introduce Redis Cache?

**Interview Answer:**

Database queries work well, but repeatedly querying the database for the same data increases latency and database load.

Redis stores frequently accessed data in memory, making retrieval much faster.

**Example**

Without Redis:

```text
Application → Database
```

Response Time:

```text
20-100 ms
```

With Redis:

```text
Application → Redis Cache
                 ↓
              Database (if cache miss)
```

Response Time:

```text
< 1 ms
```

**Use Cases**

* Product Catalog
* User Sessions
* Frequently Accessed Data
* Rate Limiting

**Rule**

Database is for persistence. Redis is for speed.

---

## 25. If Caching exists, why optimize Database Queries?

**Interview Answer:**

Cache reduces database calls, but cache misses still hit the database.

If queries are inefficient, the system remains slow whenever data is not available in cache.

**Example**

Bad Query:

```sql
SELECT * FROM orders;
```

Optimized Query:

```sql
SELECT order_id, status
FROM orders
WHERE customer_id = 100;
```

**Why Optimization Still Matters**

* Cache can expire
* Cache can fail
* New data may not be cached
* Reports often bypass cache

**Rule**

Cache improves performance; query optimization improves efficiency.

---

## 26. If Read Replicas exist, why use Sharding?

**Interview Answer:**

Read Replicas help distribute read traffic.

However, all writes still go to the primary database.

**Example**

Read Replica:

```text
           Primary DB
          /     |     \
   Replica  Replica  Replica
```

Reads scale well.

But writes still hit:

```text
Primary DB
```

**Sharding**

Data is split across multiple databases.

```text
Users A-M → Shard 1
Users N-Z → Shard 2
```

Now both reads and writes are distributed.

**Rule**

Read Replicas solve read scaling.

Sharding solves read and write scaling.

---

## 27. If Elasticsearch stores data, why not use it as the Primary Database?

**Interview Answer:**

Elasticsearch is optimized for searching and analyzing data, not for transactional business operations.

**Example**

Excellent For:

```text
Search Products
Search Documents
Search Logs
Full Text Search
```

Not Ideal For:

```text
Money Transfer
Inventory Updates
Order Processing
```

**Typical Architecture**

```text
PostgreSQL
      ↓
 Elasticsearch
```

Database remains the source of truth.

**Rule**

Elasticsearch is a search engine, not a transactional database.

---

## 28. If Strong Consistency is better, why use Eventual Consistency?

**Interview Answer:**

Strong consistency guarantees everyone sees the latest data immediately.

However, achieving this across distributed systems can reduce availability and scalability.

**Example**

Bank Transfer:

```text
Account A → Account B
```

Strong consistency is required.

**Example**

Social Media Like Count:

```text
100 Likes
```

Showing:

```text
99 Likes
```

for a few seconds is acceptable.

**Rule**

* Critical business data → Strong Consistency
* High-scale distributed systems → Eventual Consistency

---

## 29. If ACID Transactions exist, why use Eventual Consistency?

**Interview Answer:**

ACID transactions work perfectly inside a single database.

In microservices, data is often spread across multiple services and databases.

**Example**

Order Service:

```text
Create Order
```

Payment Service:

```text
Process Payment
```

Inventory Service:

```text
Reserve Stock
```

A single ACID transaction cannot easily span all these independent services.

Instead:

```text
Order Created Event
       ↓
Payment Success Event
       ↓
Inventory Reserved Event
```

The system becomes consistent over time.

**Rule**

ACID works best within one database.

Eventual Consistency works across distributed services.

---

**Architect-Level Summary**

| Question                                   | Short Answer                                  |
| ------------------------------------------ | --------------------------------------------- |
| Redis vs Database                          | Speed vs persistence                          |
| Cache vs Query Optimization                | Faster access vs efficient processing         |
| Read Replica vs Sharding                   | Read scaling vs read/write scaling            |
| Elasticsearch vs Database                  | Search engine vs source of truth              |
| Strong Consistency vs Eventual Consistency | Accuracy vs scalability                       |
| ACID vs Eventual Consistency               | Single DB transactions vs distributed systems |

# 5. Scalability & Performance

## 30. If Vertical Scaling is possible, why use Horizontal Scaling?

**Interview Answer:**

Vertical scaling means increasing resources of a single server, such as CPU, RAM, or storage.

Horizontal scaling means adding more servers and distributing traffic among them.

Vertical scaling has hardware limits, while horizontal scaling can continue growing by adding more machines.

**Example**

Vertical Scaling:

```text
Server
4 CPU → 8 CPU → 16 CPU
```

Eventually you hit a hardware limit.

Horizontal Scaling:

```text
Server 1
Server 2
Server 3
Server 4
```

Traffic is distributed across all servers.

**Benefit**s of Horizontal Scaling

* Higher scalability
* Better fault tolerance
* High availability
* No single point of failure

**Rule**

Vertical scaling has limits. Horizontal scaling can grow almost indefinitely.

---

## 31. If Horizontal Scaling is better, why ever scale Vertically?

**Interview Answer:**

Horizontal scaling is powerful, but it also introduces complexity such as load balancing, distributed transactions, caching, and synchronization.

Sometimes increasing CPU or RAM is the fastest and simplest solution.

**Example**

Current Server:

```text
CPU Usage = 90%
RAM Usage = 95%
```

Instead of redesigning the architecture:

```text
8 GB RAM → 32 GB RAM
```

Problem solved in minutes.

**When Vertical Scaling Makes Sense**

* Small applications
* Early-stage startups
* Databases that are difficult to shard
* Temporary traffic increases

**Rule**

Scale vertically first if it solves the problem quickly. Scale horizontally when growth continues.

---

## 32. If Load Balancers exist, why use a CDN?

**Interview Answer:**

Load Balancers distribute requests among application servers.

CDNs move static content closer to users worldwide.

**Example**

Without CDN:

```text
User (India)
      ↓
US Server
```

High latency.

With CDN:

```text
User (India)
      ↓
Nearest CDN Edge Server
```

Much faster response.

**What CDN Typically Serves**

* Images
* CSS
* JavaScript
* Videos
* Static Files

**Difference**

| Load Balancer               | CDN             |
| --------------------------- | --------------- |
| Distributes traffic         | Caches content  |
| Protects backend            | Reduces latency |
| Works inside infrastructure | Works globally  |

**Rule**

Load Balancer scales applications.

CDN accelerates content delivery.

---

## 33. If Auto Scaling exists, why optimize code?

**Interview Answer:**

Auto Scaling adds more servers when traffic increases.

But inefficient code wastes CPU, memory, database connections, and infrastructure costs.

**Example**

Bad Code:

```java
for(User user : users){
    userRepository.findOrders(user.getId());
}
```

This creates an N+1 query problem.

Even with:

```text
20 Servers
```

the application remains inefficient.

Optimized Code:

```java
JOIN FETCH
Batch Processing
Caching
Indexes
```

Now fewer resources are needed.

**Why Optimization Still Matters**

* Lower infrastructure cost
* Better response time
* Reduced database load
* Improved user experience

**Rule**

Auto Scaling treats the symptom.

Code optimization fixes the root cause.

---

**Architect-Level Summary**

| Question                          | Short Answer                             |
| --------------------------------- | ---------------------------------------- |
| Vertical vs Horizontal Scaling    | Simplicity vs unlimited growth           |
| Horizontal vs Vertical Scaling    | Scalability vs operational simplicity    |
| Load Balancer vs CDN              | Traffic distribution vs content delivery |
| Auto Scaling vs Code Optimization | More servers vs better efficiency        |



## 6. Security Architecture

## 34. If JWT exists, why use Sessions?

**Interview Answer:**

JWT is stateless, meaning the server does not store user session information.

Sessions are stateful, meaning the server keeps track of logged-in users.

JWT is great for distributed systems and microservices, while Sessions are often simpler and more secure for traditional web applications.

**Example**

JWT:

```text
User → JWT Token → API
```

Server validates the token.

Session:

```text
User → Session ID → Server Session Store
```

Server looks up user information.

**Choose JWT When**

* Microservices
* Mobile Applications
* Multiple APIs
* Stateless Architecture

**Choose Sessions When**

* Traditional Web Applications
* Need immediate logout everywhere
* Higher control over user sessions

**Rule**

JWT improves scalability.

Sessions improve control and simplicity.

---

## 35. If HTTPS is enabled, why encrypt data at rest?

**Interview Answer:**

HTTPS protects data while it is moving across the network.

Encryption at rest protects data when it is stored in databases, backups, or disks.

**Example**

HTTPS Protects:

```text
Browser ↔ Application
```

Encryption At Rest Protects:

```text
Database
Backup Files
Hard Disks
Cloud Storage
```

**Attack Scenario**

If someone steals a database backup:

```text
Without Encryption
↓
Can read all data
```

```text
With Encryption
↓
Data remains unreadable
```

**Rule**

HTTPS protects data in transit.

Encryption protects data at rest.

---

## 36. If an API Gateway provides security, why secure services individually?

**Interview Answer:**

API Gateway is the first security layer, not the only security layer.

Internal services should never blindly trust incoming requests.

**Example**

API Gateway:

```text
Authentication
Rate Limiting
Request Filtering
```

Service Security:

```text
Authorization
Role Validation
Input Validation
Business Rules
```

**Example** Flow

```text
Client
   ↓
API Gateway
   ↓
Order Service
```

Even if a request passes the Gateway:

```text
Order Service
```

must still verify:

```text
Can this user access this order?
```

**Rule**

Security should exist at every layer.

Never trust a request just because it passed the API Gateway.

---

## 7. Cloud, DevOps & Operations

## 37. If Docker works, why use Kubernetes?

**Interview Answer:**

Docker packages and runs containers.

Kubernetes manages containers at scale.

**Example**

Docker:

```text
Run 1 Container
Run 5 Containers
```

Simple and easy.

Kubernetes:

```text
100+ Containers
Auto Scaling
Self Healing
Rolling Updates
Load Balancing
```

**Difference**

| Docker            | Kubernetes         |
| ----------------- | ------------------ |
| Runs containers   | Manages containers |
| Single host focus | Cluster focus      |
| Manual scaling    | Automatic scaling  |

**Rule**

Docker creates containers.

Kubernetes orchestrates containers.

---

## 38. If Kubernetes exists, why deploy directly on VMs?

**Interview Answer:**

Kubernetes provides powerful orchestration but also introduces operational complexity.

For smaller applications, VMs may be simpler and more cost-effective.

**Example**

Small Internal Application:

```text
2 Servers
Low Traffic
```

Kubernetes may be unnecessary.

Large Platform:

```text
50 Services
Hundreds of Containers
```

Kubernetes becomes valuable.

**Rule**

Use VMs for simplicity.

Use Kubernetes when orchestration benefits outweigh complexity.

---

## 39. If CI/CD exists, why have Release Approvals?

**Interview Answer:**

CI/CD automates building, testing, and deployment.

Release approvals add business and operational control before production releases.

**Example**

CI/CD verifies:

```text
Build Passed
Tests Passed
Security Checks Passed
```

But someone may still need to confirm:

```text
Is this the right release?
Has business approved it?
Is production ready?
```

**Common Approval Cases**

* Banking Systems
* Healthcare Systems
* Government Applications
* High-Risk Production Releases

**Rule**

CI/CD ensures technical quality.

Release approvals ensure business readiness.

---

**Architect-Level Summary**

| Question                        | Short Answer                       |
| ------------------------------- | ---------------------------------- |
| JWT vs Session                  | Scalability vs control             |
| HTTPS vs Encryption at Rest     | Data in transit vs stored data     |
| API Gateway vs Service Security | First defense vs defense in depth  |
| Docker vs Kubernetes            | Container runtime vs orchestration |
| Kubernetes vs VMs               | Scalability vs simplicity          |
| CI/CD vs Release Approval       | Automation vs governance           |

==============================

## 8. Reliability, Monitoring & Observability

## 40. If Monitoring exists, why need Distributed Tracing?

**Interview Answer:**

Monitoring tells us **that a problem exists**.

Distributed Tracing tells us **where the problem occurred** across multiple services.

**Example**

Monitoring Alert:

```text
API Response Time = 10 seconds
```

Monitoring tells us:

```text
Something is slow
```

But not:

```text
Which service is causing the delay?
```

Distributed Tracing shows:

```text
Client
  ↓
API Gateway (20ms)
  ↓
Order Service (50ms)
  ↓
Payment Service (8 sec) ❌
  ↓
Database (100ms)
```

Now we know exactly where the bottleneck is.

**Rule**

Monitoring tells you there is a problem.

Distributed Tracing tells you where the problem is.

---

## 41. If Logging exists, why use Observability Platforms?

**Interview Answer:**

Logs provide individual events and error messages.

Observability platforms combine logs, metrics, and traces to provide a complete system view.

**Example**

Log Entry:

```text
Payment Failed
```

Questions remain:

```text
How many users were affected?
When did it start?
Which service caused it?
```

Observability Platform shows:

```text
Logs
+
Metrics
+
Distributed Traces
+
Dashboards
+
Alerts
```

**Example** Tools

* Grafana
* Datadog
* New Relic
* OpenTelemetry

**Rule**

Logs show individual events.

Observability explains overall system behavior.

---

## 42. If Backups exist, why need Disaster Recovery?

**Interview Answer:**

Backups help recover lost data.

Disaster Recovery helps recover the entire system after a major failure.

**Example**

Backup Handles:

```text
Accidental Data Deletion
Database Corruption
```

Disaster Recovery Handles:

```text
Data Center Failure
Cloud Region Outage
Cyber Attack
Natural Disaster
```

**Example**

You have a database backup:

```text
Backup Available ✅
```

But production servers are destroyed:

```text
Application Servers ❌
Network ❌
Database Server ❌
```

Backup alone cannot restore the complete system quickly.

**Rule**

Backup protects data.

Disaster Recovery protects business continuity.

---

## 43. If High Availability exists, why need Disaster Recovery?

**Interview Answer:**

High Availability (HA) minimizes downtime caused by normal infrastructure failures.

Disaster Recovery (DR) handles large-scale catastrophic failures.

**Example**

High Availability:

```text
Server A ❌
      ↓
Server B takes over ✅
```

Users may not even notice the failure.

**Disaster Recovery:**

```text
Entire Region Down ❌
Entire Data Center Down ❌
```

Need recovery in another location.

```text
Region A ❌
      ↓
Region B ✅
```

**Difference**

| High Availability       | Disaster Recovery             |
| ----------------------- | ----------------------------- |
| Handles server failures | Handles catastrophic failures |
| Seconds or minutes      | Minutes or hours              |
| Same region/data center | Different region/data center  |
| Focus on uptime         | Focus on recovery             |

**Rule**

High Availability prevents outages.

Disaster Recovery recovers from disasters.

---

**Architect-Level Summary**

| Question                               | Short Answer                          |
| -------------------------------------- | ------------------------------------- |
| Monitoring vs Distributed Tracing      | Detect problem vs locate problem      |
| Logging vs Observability               | Events vs complete system visibility  |
| Backup vs Disaster Recovery            | Recover data vs recover entire system |
| High Availability vs Disaster Recovery | Survive failures vs survive disasters |




# ✅ 3. Java System Design features

## 1. Load Balancing

**What it is:**
Load Balancing distributes incoming network traffic across multiple servers to ensure no single server is overwhelmed, improving availability and reliability.

**How it works:**
- A Load Balancer sits between the client and server pool
- It routes each request to one of the available servers based on an algorithm
- If a server goes down, traffic is rerouted to healthy servers automatically

**Algorithms:**
| Algorithm | Description |
|---|---|
| Round Robin | Requests distributed sequentially to each server |
| Least Connections | Routes to server with fewest active connections |
| IP Hash | Same client always goes to same server (sticky sessions) |
| Weighted Round Robin | Servers with higher capacity get more traffic |
| Random | Randomly picks a server |

**Types:**
- **Layer 4 (Transport):** Routes based on IP/TCP — fast, no content inspection (e.g., AWS NLB)
- **Layer 7 (Application):** Routes based on HTTP headers, URL, cookies — smarter routing (e.g., AWS ALB, Nginx)

**Real-world tools:** AWS ALB/NLB, Nginx, HAProxy, Traefik

**Example (Spring Boot + AWS ALB):**
```
Client → ALB → [Service Instance 1]
              → [Service Instance 2]
              → [Service Instance 3]
```

**Key benefits:**
- High availability — no single point of failure
- Horizontal scalability — add more servers as load grows
- Health checks — automatically removes unhealthy instances

---

## 2. Caching

**What it is:**
Caching stores frequently accessed data in fast storage (memory) to reduce latency and database load.

**Cache levels:**
| Level | Example | Latency |
|---|---|---|
| CPU Cache | L1/L2/L3 | Nanoseconds |
| In-process Cache | Guava Cache, Caffeine | Microseconds |
| Distributed Cache | Redis, Memcached | ~1ms |
| CDN Cache | CloudFront | Milliseconds |
| Database Cache | Query cache | Milliseconds |

**Caching strategies:**
- **Cache-Aside (Lazy Loading):** App checks cache first; on miss, loads from DB and populates cache
- **Write-Through:** Write to cache and DB simultaneously; always consistent, higher write latency
- **Write-Behind (Write-Back):** Write to cache first, async write to DB; fast writes, risk of data loss
- **Read-Through:** Cache sits in front of DB; cache handles DB reads automatically

**Cache eviction policies:**
- **LRU (Least Recently Used):** Evicts least recently accessed item — most common
- **LFU (Least Frequently Used):** Evicts least accessed item over time
- **TTL (Time To Live):** Expires after a set duration

**Cache invalidation challenges:**
"There are only two hard things in Computer Science: cache invalidation and naming things." — Phil Karlton

- Stale data if not invalidated properly
- Solutions: TTL expiry, event-driven invalidation, versioned cache keys

**Spring Boot + Redis example:**
```java
@Cacheable(value = "products", key = "#id")
public Product getProduct(Long id) {
    return productRepository.findById(id).orElseThrow();
}

@CacheEvict(value = "products", key = "#id")
public void updateProduct(Long id, Product product) {
    productRepository.save(product);
}
```

**Key metrics:** Cache hit ratio (aim for 90%), eviction rate, memory usage

---

## 3. Content Delivery Network (CDN)

**What it is:**
A CDN is a geographically distributed network of servers (edge nodes / PoPs) that cache and serve content to users from the nearest location, reducing latency.

**How it works:**
```
User (India) → DNS resolves to nearest edge (Mumbai PoP) → Cache Hit → Content served
                                                          → Cache Miss → Fetch from Origin → Cache → Serve
```

**What CDN caches:**
- Static assets: images, CSS, JS, fonts, videos
- Dynamic content (with proper cache headers)
- API responses (with short TTL)

**CDN components:**
| Component | Role |
|---|---|
| Origin Server | Source of truth for content |
| Edge Node (PoP) | Caches and serves content near users |
| DNS Routing | Directs user to nearest edge (Anycast/GeoDNS) |
| Cache Control | HTTP headers define what/how long to cache |

**Cache control headers:**
```
Cache-Control: public, max-age=86400       → cache for 1 day
Cache-Control: no-cache                    → always revalidate
Cache-Control: private                     → browser only, not CDN
ETag / Last-Modified                       → conditional requests
```

**Cache invalidation:**
- TTL expiry (automatic)
- Purge API (immediate, e.g., CloudFront invalidation)
- Versioned URLs: `app.v2.js` — no invalidation needed

**Real-world tools:** AWS CloudFront, Cloudflare, Akamai, Fastly

**Benefits:**
- Reduced latency (serve from 50ms vs 300ms)
- Reduced origin load (offload 80-90% of traffic)
- DDoS protection (absorbs traffic at edge)
- High availability (edge nodes independent of origin)

---

## 4. Message Queue

**What it is:**
A Message Queue is an asynchronous communication mechanism where producers send messages to a queue and consumers process them independently, decoupling services.

**How it works:**
```
Producer → [Queue] → Consumer
```
- Producer sends message and continues (fire and forget)
- Queue stores messages durably until consumed
- Consumer polls or receives messages and processes them

**Key concepts:**
| Concept | Description |
|---|---|
| Producer | Sends messages to the queue |
| Consumer | Reads and processes messages |
| Queue | Buffer that holds messages |
| Acknowledgement (ACK) | Consumer confirms successful processing |
| Dead Letter Queue (DLQ) | Holds failed/unprocessable messages |
| Visibility Timeout | Message hidden from other consumers while being processed |

**Message delivery guarantees:**
- **At-most-once:** Message delivered 0 or 1 time (possible loss)
- **At-least-once:** Message delivered 1 or more times (possible duplicates) — most common
- **Exactly-once:** Delivered exactly once — hardest, requires idempotency

**Use cases:**
- Order processing: Order service → Queue → Inventory, Payment, Notification services
- Email/SMS sending: API → Queue → Email worker
- Log processing: App → Queue → Log aggregator
- Decoupling microservices to handle traffic spikes

**Real-world tools:** AWS SQS, RabbitMQ, ActiveMQ, IBM MQ

**Spring Boot + SQS example:**
```java
// Producer
sqsTemplate.send("order-queue", orderEvent);

// Consumer
@SqsListener("order-queue")
public void processOrder(OrderEvent event) {
    orderService.process(event);
}
```

**Message Queue vs Message Broker:** Queue is point-to-point (one consumer); Broker (Kafka/RabbitMQ) supports routing, topics, multiple consumers

---

## 5. Publish-Subscribe (Pub/Sub)

**What it is:**
Pub/Sub is a messaging pattern where publishers send messages to a topic (not directly to consumers), and all subscribers to that topic receive the message — one-to-many communication.

**How it works:**
```
Publisher → [Topic] → Subscriber 1
                    → Subscriber 2
                    → Subscriber 3
```

**Pub/Sub vs Message Queue:**
| Feature | Message Queue | Pub/Sub |
|---|---|---|
| Consumers | One consumer per message | All subscribers get the message |
| Coupling | Point-to-point | Broadcast / fan-out |
| Use case | Task distribution | Event notification |
| Example | SQS | SNS, Kafka topics |

**Key concepts:**
- **Topic:** Named channel publishers send to
- **Subscription:** Consumer's interest in a topic
- **Fan-out:** One message delivered to N subscribers
- **Filtering:** Subscribers can filter messages by attributes

**Use cases:**
- User signup → Pub/Sub → Email service, Analytics service, CRM service all notified
- Order placed → Pub/Sub → Inventory, Shipping, Notification all react
- Real-time dashboards: metrics published → multiple dashboards subscribe
- Event-driven microservices architecture

**Real-world tools:** AWS SNS, Google Pub/Sub, Kafka, Redis Pub/Sub, RabbitMQ (fanout exchange)

**AWS SNS + SQS Fan-out pattern:**
```
SNS Topic (order-events)
    ├── SQS Queue → Inventory Service
    ├── SQS Queue → Shipping Service
    └── SQS Queue → Notification Service
```

**Spring Boot + Kafka example:**
```java
// Publisher
kafkaTemplate.send("order-events", orderEvent);

// Subscriber
@KafkaListener(topics = "order-events", groupId = "inventory-service")
public void handleOrder(OrderEvent event) {
    inventoryService.reserve(event);
}
```

---

## 6. API Gateway

**What it is:**
An API Gateway is a single entry point for all client requests to backend microservices. It handles cross-cutting concerns like routing, authentication, rate limiting, and load balancing.

**How it works:**
```
Client → API Gateway → [Auth] → [Rate Limit] → [Route] → Microservice A
                                                         → Microservice B
                                                         → Microservice C
```

**Core responsibilities:**
| Function | Description |
|---|---|
| Routing | Forward requests to correct microservice |
| Authentication/Authorization | Validate JWT/OAuth tokens centrally |
| Rate Limiting | Throttle requests per client/IP |
| SSL Termination | Handle HTTPS, forward HTTP internally |
| Request/Response Transformation | Modify headers, body format |
| Load Balancing | Distribute across service instances |
| Caching | Cache responses for repeated requests |
| Logging & Monitoring | Centralized access logs, metrics |
| Circuit Breaking | Stop forwarding to unhealthy services |

**API Gateway vs Load Balancer:**
| | API Gateway | Load Balancer |
|---|---|---|
| Layer | Layer 7 (Application) | Layer 4 or 7 |
| Awareness | Business logic aware | Traffic distribution only |
| Features | Auth, rate limit, transform | Health check, routing |
| Example | AWS API Gateway, Kong | AWS ALB, Nginx |

**Real-world tools:** AWS API Gateway, Kong, Nginx, Spring Cloud Gateway, Zuul, Traefik

**Spring Cloud Gateway example:**
```yaml
spring:
  cloud:
    gateway:
      routes:
        - id: order-service
          uri: lb://ORDER-SERVICE
          predicates:
            - Path=/api/orders/**
          filters:
            - StripPrefix=1
            - name: CircuitBreaker
              args:
                name: orderCircuitBreaker
```

**Benefits:**
- Simplifies client (one endpoint instead of N service URLs)
- Centralized security and cross-cutting concerns
- Enables backend for frontend (BFF) pattern
- Decouples client from internal service topology

---

## 7. Circuit Breaker

**What it is:**
The Circuit Breaker pattern prevents cascading failures in distributed systems by stopping calls to a failing service and providing a fallback response, allowing the service time to recover.

**States:**
```
[CLOSED] → (failure threshold exceeded) → [OPEN] → (wait timeout) → [HALF-OPEN]
   ↑                                                                      |
   └──────────────── (test call succeeds) ──────────────────────────────┘
```

| State | Behavior |
|---|---|
| Closed | Normal operation; requests pass through; failures counted |
| Open | All requests fail fast (no call to service); fallback returned |
| Half-Open | Limited test requests allowed; if success → Closed; if fail → Open |

**Configuration parameters:**
- `failureRateThreshold`: % of failures to trip to OPEN (e.g., 50%)
- `slowCallRateThreshold`: % of slow calls to trip (e.g., 80%)
- `waitDurationInOpenState`: How long to stay OPEN before trying HALF-OPEN (e.g., 30s)
- `slidingWindowSize`: Number of calls to evaluate (e.g., last 10 calls)
- `permittedCallsInHalfOpenState`: Test calls in HALF-OPEN (e.g., 3)

**Spring Boot + Resilience4j:**
```java
@CircuitBreaker(name = "paymentService", fallbackMethod = "paymentFallback")
public PaymentResponse processPayment(PaymentRequest request) {
    return paymentClient.process(request);
}

public PaymentResponse paymentFallback(PaymentRequest request, Exception ex) {
    return PaymentResponse.pending("Payment service unavailable, will retry");
}
```

```yaml
resilience4j:
  circuitbreaker:
    instances:
      paymentService:
        failureRateThreshold: 50
        waitDurationInOpenState: 30s
        slidingWindowSize: 10
        permittedNumberOfCallsInHalfOpenState: 3
```

**Why it matters:**
- Without circuit breaker: one slow service causes thread pool exhaustion → entire app hangs
- With circuit breaker: failing service is isolated; rest of system continues normally

**Related patterns:** Retry (with backoff), Bulkhead (isolate thread pools), Timeout

---

## 8. Service Discovery

**What it is:**
Service Discovery is the mechanism by which microservices automatically find and communicate with each other without hardcoded IP addresses or ports, since instances are dynamic in cloud environments.

**The problem:**
```
# Static config (bad in cloud)
payment-service.url=192.168.1.10:8080  ← IP changes on restart/scaling
```

**Types:**

**Client-Side Discovery:**
- Client queries Service Registry to get list of instances
- Client performs load balancing and picks an instance
- Example: Netflix Eureka + Ribbon
```
Service A → Eureka (get instances of B) → picks instance → calls Service B
```

**Server-Side Discovery:**
- Client calls Load Balancer/Router
- Router queries registry and forwards request
- Client doesn't know about registry
- Example: AWS ALB + ECS, Kubernetes Service
```
Service A → Load Balancer → (queries registry internally) → Service B instance
```

**Service Registry:**
- Central store of service instances (name, IP, port, health status)
- Services register on startup, deregister on shutdown
- Heartbeat mechanism detects dead instances

**Real-world tools:** Netflix Eureka, Consul, Zookeeper, AWS Cloud Map, Kubernetes DNS

**Spring Boot + Eureka:**
```java
// Eureka Server
@SpringBootApplication
@EnableEurekaServer
public class DiscoveryServer { }

// Eureka Client (microservice)
@SpringBootApplication
@EnableDiscoveryClient
public class OrderService { }
```

```yaml
# application.yml
eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:8761/eureka/
spring:
  application:
    name: order-service
```

**Calling another service by name (not IP):**
```java
@LoadBalanced  // Ribbon/Spring Cloud LoadBalancer resolves service name
RestTemplate restTemplate;

restTemplate.getForObject("http://payment-service/api/pay", PaymentResponse.class);
```

---

## 9. Sharding

**What it is:**
Sharding (horizontal partitioning) splits a large database into smaller, faster, more manageable pieces called shards, each holding a subset of the data, distributed across multiple servers.

**Why sharding:**
- Single DB server hits limits: storage, CPU, memory, connections
- Vertical scaling (bigger server) has limits and is expensive
- Sharding scales horizontally — add more servers as data grows

**Sharding strategies:**

**Range-Based Sharding:**
```
Shard 1: user_id 1 - 1,000,000
Shard 2: user_id 1,000,001 - 2,000,000
Shard 3: user_id 2,000,001 - 3,000,000
```
- ✅ Easy range queries
- ❌ Hotspots if data not evenly distributed

**Hash-Based Sharding:**
```
shard = hash(user_id) % number_of_shards
```
- ✅ Even distribution
- ❌ Range queries require hitting all shards
- ❌ Rebalancing when adding shards (use consistent hashing to solve)

**Directory-Based Sharding:**
- Lookup table maps key → shard
- ✅ Flexible, easy to move data
- ❌ Lookup table is a bottleneck/single point of failure

**Geo-Based Sharding:**
- Data partitioned by geography (US users → US shard, EU users → EU shard)
- ✅ Data residency compliance, low latency
- ❌ Uneven load if regions have different traffic

**Challenges:**
| Challenge | Solution |
|---|---|
| Cross-shard joins | Denormalize data, application-level joins |
| Distributed transactions | Saga pattern, avoid cross-shard transactions |
| Rebalancing shards | Consistent hashing minimizes data movement |
| Hotspot keys | Add random suffix to key, split hot shard |
| Schema changes | Apply to all shards, use online schema change tools |

**Sharding vs Partitioning:**
- Partitioning: splitting data within a single DB instance (logical)
- Sharding: splitting data across multiple DB instances (physical)

---

## 10. Rate Limiting

**What it is:**
Rate Limiting controls the number of requests a client can make to an API within a time window, protecting services from abuse, DDoS attacks, and ensuring fair usage.

**Why it matters:**
- Prevents API abuse and scraping
- Protects backend from traffic spikes
- Ensures fair resource allocation across clients
- Reduces infrastructure costs

**Rate limiting algorithms:**

**Token Bucket:**
```
Bucket capacity: 100 tokens
Refill rate: 10 tokens/second
Each request consumes 1 token
If bucket empty → reject request (429)
```
- ✅ Allows bursts up to bucket capacity
- ✅ Smooth average rate
- Most widely used algorithm

**Leaky Bucket:**
```
Requests enter bucket → processed at fixed rate → excess overflows (rejected)
```
- ✅ Smooths out traffic bursts
- ❌ No burst allowance

**Fixed Window Counter:**
```
Window: 0-60 seconds → max 100 requests
Counter resets at 60s boundary
```
- ✅ Simple to implement
- ❌ Boundary spike: 100 requests at 59s + 100 at 61s = 200 in 2 seconds

**Sliding Window Log:**
- Store timestamp of each request
- Count requests in last N seconds
- ✅ Accurate, no boundary spike
- ❌ High memory usage

**Sliding Window Counter:**
- Hybrid of fixed window + sliding calculation
- ✅ Accurate, memory efficient
- Most practical for production

**Implementation with Redis:**
```java
// Atomic increment with expiry (Fixed Window)
Long count = redisTemplate.opsForValue().increment("rate:" + userId);
if (count == 1) redisTemplate.expire("rate:" + userId, 60, TimeUnit.SECONDS);
if (count 100) throw new RateLimitExceededException();
```

**Rate limit response:**
```
HTTP 429 Too Many Requests
Headers:
  X-RateLimit-Limit: 100
  X-RateLimit-Remaining: 0
  X-RateLimit-Reset: 1700000060
  Retry-After: 30
```

**Rate limiting levels:**
- Per IP address (unauthenticated)
- Per API key / user (authenticated)
- Per endpoint (stricter limits on expensive operations)
- Global (protect entire service)

**Tools:** AWS API Gateway throttling, Kong Rate Limiting plugin, Bucket4j (Java), Resilience4j RateLimiter

---

## 11. Consistent Hashing

**What it is:**
Consistent Hashing is a technique that minimizes data redistribution when nodes are added or removed from a distributed system, solving the rebalancing problem of simple modulo hashing.

**The problem with simple hashing:**
```
shard = hash(key) % N   (N = number of nodes)

If N changes from 3 to 4:
  Almost ALL keys map to different shards → massive data movement
```

**How Consistent Hashing works:**
1. Arrange a virtual ring of hash values (0 to 2³²)
2. Map each server node to a position on the ring using hash(node)
3. Map each key to a position using hash(key)
4. Key is assigned to the first node clockwise from its position

```
Ring: 0 ──────────────────────────── 2³²
         Node A    Node B    Node C
           │         │         │
    Key1──►A    Key2─►B   Key3─►C
```

**Adding a node:**
- New node takes over only the keys between it and its predecessor
- Only ~K/N keys need to move (K = total keys, N = nodes)
- All other keys unaffected

**Removing a node:**
- Only that node's keys move to the next node clockwise
- All other keys unaffected

**Virtual nodes (vnodes):**
- Each physical node maps to multiple positions on the ring (e.g., 150 virtual nodes)
- Ensures even distribution even with heterogeneous nodes
- Allows fine-grained load balancing

```
Node A → positions [45, 190, 320, 500, ...]
Node B → positions [80, 210, 380, 600, ...]
Node C → positions [30, 150, 290, 450, ...]
```

**Use cases:**
- Distributed caches (Memcached, Redis Cluster)
- Database sharding
- Distributed hash tables (DHT)
- Load balancing with session affinity
- Content routing in CDNs

**Real-world usage:**
- Amazon DynamoDB — consistent hashing for partition routing
- Apache Cassandra — token ring with virtual nodes
- Redis Cluster — hash slots (16384 slots distributed across nodes)

**Java implementation concept:**
```java
TreeMap<Long, Stringring = new TreeMap<();

// Add node
void addNode(String node) {
    for (int i = 0; i < VIRTUAL_NODES; i++) {
        long hash = hash(node + "#" + i);
        ring.put(hash, node);
    }
}

// Get node for key
String getNode(String key) {
    long hash = hash(key);
    Map.Entry<Long, Stringentry = ring.ceilingEntry(hash);
    return entry != null ? entry.getValue() : ring.firstEntry().getValue();
}
```

---

## 12. Auto Scaling

**What it is:**
Auto Scaling automatically adjusts the number of compute resources (servers/containers) based on current demand, ensuring performance during peaks and cost efficiency during low traffic.

**Why it matters:**
- Traffic is unpredictable — spikes during sales, events, viral moments
- Over-provisioning wastes money
- Under-provisioning causes outages
- Auto scaling handles both automatically

**Types of Auto Scaling:**

**Horizontal Scaling (Scale Out/In):**
- Add/remove instances (servers, containers, pods)
- ✅ No downtime, unlimited scale
- ✅ Preferred for stateless services
- Example: Add 5 more EC2 instances when CPU 70%

**Vertical Scaling (Scale Up/Down):**
- Increase/decrease resources of existing instance (CPU, RAM)
- ❌ Requires restart, has upper limit
- Used for stateful services (DBs) or when horizontal isn't possible

**Scaling triggers (metrics):**
| Metric | Example Threshold |
|---|---|
| CPU Utilization | Scale out if 70% for 5 min |
| Memory Usage | Scale out if 80% |
| Request Count | Scale out if 1000 req/sec |
| Queue Depth | Scale out if SQS queue 100 messages |
| Custom Metrics | Business metrics via CloudWatch |
| Schedule | Scale out every weekday at 9 AM |

**Scaling policies:**

**Target Tracking:** Maintain a target metric value
```
Keep average CPU at 50% → automatically adds/removes instances
```

**Step Scaling:** Different actions at different thresholds
```
CPU 60-70% → add 1 instance
CPU 70-80% → add 2 instances
CPU 80%  → add 4 instances
```

**Scheduled Scaling:** Pre-planned scaling for known traffic patterns
```
Every Friday 6 PM → scale to 10 instances (weekend traffic)
Every Monday 6 AM → scale back to 3 instances
```

**Predictive Scaling:** ML-based, forecasts future traffic and scales proactively

**AWS Auto Scaling components:**
```
Auto Scaling Group (ASG)
  ├── Launch Template (AMI, instance type, security groups)
  ├── Min capacity: 2
  ├── Max capacity: 20
  ├── Desired capacity: 4 (current)
  └── Scaling Policies (target tracking / step / scheduled)
```

**Kubernetes (HPA - Horizontal Pod Autoscaler):**
```yaml
apiVersion: autoscaling/v2
kind: HorizontalPodAutoscaler
metadata:
  name: order-service-hpa
spec:
  scaleTargetRef:
    apiVersion: apps/v1
    kind: Deployment
    name: order-service
  minReplicas: 2
  maxReplicas: 20
  metrics:
    - type: Resource
      resource:
        name: cpu
        target:
          type: Utilization
          averageUtilization: 70
```

**Best practices:**
- Always set min 1 for high availability (multi-AZ)
- Use warm-up period — new instances need time to be ready
- Combine with load balancer health checks
- Test scaling policies with load testing before production
- Use lifecycle hooks for graceful startup/shutdown
- Monitor scale-in protection for instances processing long jobs

---

## 13. Database Replication

**What it is:**
Database Replication is the process of copying data from one database server (primary) to one or more servers (replicas) to improve read performance, availability, and fault tolerance.

**Types:**

**Master-Slave (Primary-Replica):**
```
Writes → Primary DB → replicates → Replica 1 (reads)
                               → Replica 2 (reads)
```
- All writes go to primary; reads distributed across replicas
- ✅ Simple, scales reads well
- ❌ Primary is single point of failure for writes

**Master-Master (Multi-Primary):**
```
Writes → Primary 1 ↔ Primary 2 ← Writes
```
- Both nodes accept reads and writes
- ✅ No single point of failure
- ❌ Conflict resolution needed for concurrent writes

**Replication modes:**
| Mode | Description | Trade-off |
|---|---|---|
| Synchronous | Primary waits for replica to confirm write | Strong consistency, higher latency |
| Asynchronous | Primary doesn't wait for replica | Low latency, possible data loss on failover |
| Semi-synchronous | Wait for at least one replica | Balance between both |

**Replication lag:**
- Replica may be seconds behind primary
- Problem: Read-your-own-writes — user writes then immediately reads from replica and sees stale data
- Solutions: Route reads to primary for critical operations, use session consistency, wait for replication

**Use cases:**
- Read-heavy apps: route 80% reads to replicas, writes to primary
- Disaster recovery: replica in different region/AZ
- Analytics: run heavy queries on replica without impacting production
- Zero-downtime failover: promote replica to primary if primary fails

**Real-world tools:** MySQL Replication, PostgreSQL Streaming Replication, AWS RDS Multi-AZ, MongoDB Replica Set

---

## 14. Bulkhead Pattern

**What it is:**
The Bulkhead pattern isolates different parts of a system into separate resource pools (thread pools, connection pools) so that a failure or overload in one part doesn't cascade and exhaust resources for the entire system.

**Origin:** Named after ship bulkheads — watertight compartments that prevent the whole ship from sinking if one section floods.

**The problem without Bulkhead:**
```
All services share one thread pool (200 threads)
Payment service becomes slow → consumes all 200 threads
Order service, User service → starved of threads → entire app hangs
```

**With Bulkhead:**
```
Payment service  → Thread pool: 50 threads
Order service    → Thread pool: 80 threads
User service     → Thread pool: 70 threads
Each isolated — one slow service can't starve others
```

**Types:**

**Thread Pool Bulkhead:**
- Separate thread pool per downstream dependency
- Requests to a service run in its dedicated pool
- If pool is full → fail fast, don't block other services

**Semaphore Bulkhead:**
- Limit concurrent calls using a semaphore counter
- Lighter than thread pool (no extra threads)
- Suitable for non-blocking/reactive code

**Spring Boot + Resilience4j Bulkhead:**
```java
@Bulkhead(name = "paymentService", type = Bulkhead.Type.THREADPOOL)
public CompletableFuture<PaymentResponseprocessPayment(PaymentRequest req) {
    return CompletableFuture.supplyAsync(() -paymentClient.process(req));
}
```

```yaml
resilience4j:
  bulkhead:
    instances:
      paymentService:
        maxConcurrentCalls: 10
        maxWaitDuration: 500ms
  thread-pool-bulkhead:
    instances:
      paymentService:
        maxThreadPoolSize: 10
        coreThreadPoolSize: 5
        queueCapacity: 20
```

**Bulkhead + Circuit Breaker together:**
- Bulkhead: limits concurrent calls (prevents resource exhaustion)
- Circuit Breaker: stops calls when failure rate is high (prevents cascading failures)
- Use both together for robust fault isolation

---

## 15. Distributed Tracing

**What it is:**
Distributed Tracing tracks a single request as it flows through multiple microservices, providing end-to-end visibility into latency, errors, and service dependencies.

**The problem:**
```
User request → API Gateway → Order Service → Payment Service → Notification Service
                                          → Inventory Service
If request is slow, which service is the bottleneck? Logs are scattered across 5 services.
```

**Key concepts:**
| Term | Description |
|---|---|
| Trace | Complete journey of one request across all services |
| Span | A single unit of work within one service (has start/end time) |
| Trace ID | Unique ID propagated across all services for one request |
| Span ID | Unique ID for each individual span |
| Parent Span ID | Links child span to parent span |
| Baggage | Key-value pairs propagated with the trace (e.g., user ID) |

**How it works:**
```
Request enters API Gateway
  → Generate Trace ID: abc-123
  → Span 1: API Gateway (0ms - 5ms)
    → Span 2: Order Service (5ms - 50ms)
      → Span 3: Payment Service (10ms - 45ms)   ← bottleneck!
      → Span 4: Inventory Service (10ms - 20ms)
    → Span 5: Notification Service (50ms - 55ms)
```

**Spring Boot + Micrometer Tracing (Zipkin):**
```xml
<dependency
    <groupIdio.micrometer</groupId
    <artifactIdmicrometer-tracing-bridge-brave</artifactId
</dependency
<dependency
    <groupIdio.zipkin.reporter2</groupId
    <artifactIdzipkin-reporter-brave</artifactId
</dependency
```

```yaml
management:
  tracing:
    sampling:
      probability: 1.0   # 100% sampling (use 0.1 in production)
  zipkin:
    tracing:
      endpoint: http://zipkin:9411/api/v2/spans
```

- Trace ID automatically propagated via HTTP headers (`X-B3-TraceId`, `traceparent`)
- Logs automatically include trace ID for correlation

**Real-world tools:** Zipkin, Jaeger, AWS X-Ray, Datadog APM, OpenTelemetry (standard)

**Benefits:**
- Identify latency bottlenecks across services
- Root cause analysis for errors
- Visualize service dependency map
- SLA monitoring per service

---

## 16. Saga Pattern (Distributed Transactions)

**What it is:**
The Saga pattern manages distributed transactions across multiple microservices by breaking them into a sequence of local transactions, each publishing events or messages to trigger the next step, with compensating transactions for rollback.

**Why not 2PC (Two-Phase Commit):**
- 2PC requires all services to lock resources until coordinator confirms
- Blocking, slow, single point of failure (coordinator)
- Doesn't work well across microservices with different DBs

**Saga types:**

**Choreography-based Saga:**
```
Order Service → publishes OrderCreated
  → Payment Service listens → processes payment → publishes PaymentCompleted
    → Inventory Service listens → reserves stock → publishes StockReserved
      → Shipping Service listens → creates shipment
```
- ✅ Decentralized, no single point of failure
- ❌ Hard to track overall flow, risk of cyclic dependencies

**Orchestration-based Saga:**
```
Saga Orchestrator:
  1. Call Payment Service → success
  2. Call Inventory Service → success
  3. Call Shipping Service → FAIL
  4. Compensate: Cancel Inventory → Cancel Payment
```
- ✅ Centralized flow, easy to monitor and debug
- ❌ Orchestrator can become a bottleneck

**Compensating transactions:**
| Step | Action | Compensation |
|---|---|---|
| 1 | Create Order | Cancel Order |
| 2 | Charge Payment | Refund Payment |
| 3 | Reserve Inventory | Release Inventory |
| 4 | Create Shipment | Cancel Shipment |

**Real-world tools:** Axon Framework, Eventuate Tram, Apache Camel, custom Kafka-based implementation

**Key challenges:**
- Idempotency: compensating transactions must be safe to retry
- Isolation: partial state visible to other transactions during saga execution
- Debugging: harder to trace than a single ACID transaction

---

## 17. Idempotency in Distributed Systems

**What it is:**
An operation is idempotent if executing it multiple times produces the same result as executing it once. Critical in distributed systems where retries, duplicate messages, and network failures are common.

**Why it matters:**
```
Client sends payment request → network timeout → client retries
Without idempotency: customer charged twice
With idempotency: second request detected as duplicate → ignored
```

**Common scenarios requiring idempotency:**
- Payment processing (most critical)
- Order creation
- Message queue consumers (at-least-once delivery)
- API retries with exponential backoff
- Webhook handlers

**Implementation techniques:**

**Idempotency Key (Client-generated):**
```
POST /api/payments
Headers:
  Idempotency-Key: uuid-550e8400-e29b-41d4-a716-446655440000

Server:
  1. Check if key exists in Redis/DB
  2. If exists → return cached response
  3. If not → process → store key + response → return response
```

```java
@PostMapping("/payments")
public ResponseEntity<PaymentResponseprocessPayment(
        @RequestHeader("Idempotency-Key") String idempotencyKey,
        @RequestBody PaymentRequest request) {

    // Check cache
    PaymentResponse cached = redisTemplate.opsForValue().get("idem:" + idempotencyKey);
    if (cached != null) return ResponseEntity.ok(cached);

    // Process
    PaymentResponse response = paymentService.process(request);

    // Store with TTL
    redisTemplate.opsForValue().set("idem:" + idempotencyKey, response, 24, TimeUnit.HOURS);
    return ResponseEntity.ok(response);
}
```

**Database unique constraint:**
```sql
-- Prevent duplicate orders with same reference
ALTER TABLE orders ADD CONSTRAINT uk_order_reference UNIQUE (client_reference_id);
```

**Optimistic locking (version-based):**
```java
@Version
private Long version;  // JPA optimistic lock — prevents concurrent updates
```

**Kafka consumer idempotency:**
- Store processed message offsets/IDs in DB
- Before processing: check if already processed
- After processing: mark as processed atomically

---

## 18. Microservices Communication Patterns

**What it is:**
Defines how microservices communicate with each other — synchronous (real-time response needed) or asynchronous (fire and forget / event-driven).

**Synchronous Communication:**

**REST (HTTP/HTTPS):**
```java
// Spring Boot RestTemplate / WebClient
webClient.get()
    .uri("http://inventory-service/api/stock/{productId}", productId)
    .retrieve()
    .bodyToMono(StockResponse.class);
```
- ✅ Simple, widely understood, easy debugging
- ❌ Tight coupling, caller waits, cascading failures

**gRPC:**
```protobuf
service InventoryService {
  rpc CheckStock (StockRequest) returns (StockResponse);
}
```
- ✅ Binary protocol (faster), strongly typed, streaming support
- ✅ Auto-generated client/server code
- ❌ Less human-readable, harder to debug

**Asynchronous Communication:**

**Event-Driven (Kafka/SQS):**
```
Order Service → publishes OrderPlaced event → Kafka
  ← Inventory Service consumes → reserves stock
  ← Notification Service consumes → sends email
```
- ✅ Loose coupling, resilient, handles spikes
- ❌ Eventual consistency, harder to debug

**Choosing sync vs async:**
| Scenario | Recommendation |
|---|---|
| Need immediate response (payment status) | Synchronous REST/gRPC |
| Fire and forget (send email) | Async message queue |
| Multiple services need same event | Async Pub/Sub |
| Long-running process | Async + polling or webhooks |
| Real-time streaming | gRPC streaming or WebSocket |

**Service Mesh (Istio/Linkerd):**
- Handles service-to-service communication at infrastructure level
- mTLS encryption, retries, circuit breaking, observability — without code changes
- Sidecar proxy (Envoy) injected alongside each service

---

## 19. Database Indexing Strategies

**What it is:**
Database indexes are data structures that improve query performance by allowing the database to find rows without scanning the entire table, at the cost of additional storage and slower writes.

**How indexes work:**
```
Without index: SELECT * FROM orders WHERE customer_id = 123
  → Full table scan: reads every row → O(n)

With index on customer_id:
  → B-Tree lookup → O(log n) → much faster
```

**Types of indexes:**

**B-Tree Index (default):**
- Balanced tree structure, sorted
- ✅ Equality (`=`), range (``, `<`, `BETWEEN`), ORDER BY, GROUP BY
- Most common index type

**Hash Index:**
- Hash map structure
- ✅ Only equality lookups (`=`) — extremely fast
- ❌ No range queries, no sorting

**Composite Index:**
```sql
CREATE INDEX idx_order_customer_date ON orders(customer_id, order_date);
-- Efficient for: WHERE customer_id = ? AND order_date ?
-- Also efficient for: WHERE customer_id = ?  (leftmost prefix rule)
-- NOT efficient for: WHERE order_date ?  (skips leftmost column)
```
- **Leftmost prefix rule:** Index used only if query starts with leftmost columns

**Partial Index:**
```sql
CREATE INDEX idx_active_users ON users(email) WHERE status = 'ACTIVE';
-- Smaller index, faster for active user queries
```

**Covering Index:**
- Index contains all columns needed by query — no table lookup needed
```sql
CREATE INDEX idx_covering ON orders(customer_id, order_date, total_amount);
SELECT order_date, total_amount FROM orders WHERE customer_id = 123;
-- All data in index — zero table access
```

**When NOT to index:**
- Small tables (full scan is faster)
- Columns with very low cardinality (e.g., boolean, status with 2 values)
- Tables with very high write rate (index maintenance overhead)
- Columns rarely used in WHERE/JOIN/ORDER BY

**Index best practices:**
- Analyze slow queries with `EXPLAIN ANALYZE`
- Index foreign keys (JOINs)
- Avoid over-indexing — each index slows writes
- Use composite indexes for multi-column WHERE clauses
- Monitor index usage — drop unused indexes

---

## 20. Designing for Observability (Logs, Metrics, Traces)

**What it is:**
Observability is the ability to understand the internal state of a system from its external outputs — logs, metrics, and traces (the "three pillars"). It enables teams to detect, diagnose, and resolve issues in production.

**Three Pillars:**

**1. Logs — What happened:**
- Timestamped records of discrete events
- Structured logging (JSON) preferred over plain text
```java
// Structured log with context
log.info("Order processed", kv("orderId", orderId), kv("userId", userId),
         kv("amount", amount), kv("duration_ms", duration));
```
```json
{"timestamp":"2024-01-15T10:30:00Z","level":"INFO","message":"Order processed",
 "orderId":"ORD-123","userId":"USR-456","amount":99.99,"duration_ms":45,"traceId":"abc-123"}
```
- Tools: ELK Stack (Elasticsearch, Logstash, Kibana), AWS CloudWatch Logs, Splunk

**2. Metrics — How the system is performing:**
- Numeric measurements over time (counters, gauges, histograms)
```java
// Micrometer metrics in Spring Boot
Counter.builder("orders.processed")
    .tag("status", "success")
    .register(meterRegistry)
    .increment();

Timer.builder("payment.duration")
    .register(meterRegistry)
    .record(duration, TimeUnit.MILLISECONDS);
```
- Key metrics: request rate, error rate, latency (p50/p95/p99), saturation (CPU/memory)
- Tools: Prometheus + Grafana, AWS CloudWatch, Datadog

**3. Traces — Where time was spent:**
- End-to-end request flow across services (covered in Q15)
- Tools: Zipkin, Jaeger, AWS X-Ray, OpenTelemetry

**Spring Boot Actuator setup:**
```yaml
management:
  endpoints:
    web:
      exposure:
        include: health, metrics, prometheus, info
  metrics:
    export:
      prometheus:
        enabled: true
  tracing:
    sampling:
      probability: 0.1  # 10% sampling in production
```

**Golden Signals (Google SRE):**
| Signal | Description | Example Alert |
|---|---|---|
| Latency | Time to serve a request | p99 2s for 5 min |
| Traffic | Request rate | Sudden 10x spike |
| Errors | Rate of failed requests | Error rate 1% |
| Saturation | Resource utilization | CPU 85% for 10 min |

**Alerting best practices:**
- Alert on symptoms (user impact), not causes
- Avoid alert fatigue — only page for actionable issues
- Define SLOs (Service Level Objectives): 99.9% requests < 500ms
- Use error budgets to balance reliability vs feature velocity

**Observability stack example:**
```
Spring Boot App
  → Logs → Logback → ELK / CloudWatch Logs
  → Metrics → Micrometer → Prometheus → Grafana dashboards + alerts
  → Traces → OpenTelemetry → Jaeger / Zipkin / AWS X-Ray
```

---

## 21. Blue-Green and Canary Deployments

**What it is:**
Deployment strategies that minimize downtime and risk when releasing new versions of software to production.

**Blue-Green Deployment:**
```
Blue (v1 - live)   ← 100% traffic
Green (v2 - new)   ← 0% traffic (deployed, tested)

Switch:
Blue (v1 - standby) ← 0% traffic
Green (v2 - live)   ← 100% traffic

Rollback: switch back to Blue instantly
```
- ✅ Zero downtime, instant rollback
- ✅ Full testing of new version before traffic switch
- ❌ Requires double infrastructure (cost)
- ❌ Database migrations must be backward compatible

**Canary Deployment:**
```
v1 (stable) ← 95% traffic
v2 (canary) ←  5% traffic  → monitor errors/latency

If healthy → gradually increase: 10% → 25% → 50% → 100%
If issues  → route 0% to canary, rollback
```
- ✅ Gradual rollout, real user testing
- ✅ Limits blast radius of bad deployments
- ✅ A/B testing capability
- ❌ Both versions run simultaneously (DB compatibility needed)
- ❌ More complex traffic routing

**Feature Flags (Feature Toggles):**
```java
if (featureFlags.isEnabled("new-checkout-flow", userId)) {
    return newCheckoutService.process(request);
} else {
    return legacyCheckoutService.process(request);
}
```
- Deploy code without activating feature
- Enable for specific users/percentage/region
- Instant rollback without redeployment
- Tools: LaunchDarkly, AWS AppConfig, Unleash

**Rolling Deployment:**
```
10 instances running v1
Replace 2 at a time with v2:
  Step 1: 8×v1 + 2×v2
  Step 2: 6×v1 + 4×v2
  ...
  Step 5: 0×v1 + 10×v2
```
- ✅ No extra infrastructure needed
- ❌ Both versions run simultaneously
- ❌ Slower rollback

**AWS tools:** CodeDeploy (Blue/Green, Canary, Linear), ECS deployment strategies, API Gateway canary releases

---

## 22. Security in Microservices (JWT, OAuth2, mTLS)

**What it is:**
Securing microservices involves authentication (who are you?), authorization (what can you do?), and securing service-to-service communication.

**JWT (JSON Web Token):**
```
Header.Payload.Signature
eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJ1c2VyMTIzIiwicm9sZXMiOlsiVVNFUiJdfQ.signature
```
- Stateless — no DB lookup needed to validate
- Contains claims: sub (subject), roles, exp (expiry), iat (issued at)
- Signed with secret (HMAC) or private key (RSA/EC)
- ❌ Cannot be revoked before expiry — use short TTL (15 min) + refresh tokens

**OAuth2 + OpenID Connect flow:**
```
User → Login → Authorization Server (Keycloak/Cognito)
             → Issues Access Token (JWT) + Refresh Token
User → API call with Bearer token → API Gateway validates token
                                  → Routes to microservice
```

**Spring Boot Security + JWT:**
```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
            .oauth2ResourceServer(oauth2 -oauth2.jwt(Customizer.withDefaults()))
            .authorizeHttpRequests(auth -auth
                .requestMatchers("/api/public/**").permitAll()
                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated())
            .sessionManagement(s -s.sessionCreationPolicy(STATELESS))
            .build();
    }
}
```

**Service-to-Service security:**

**mTLS (Mutual TLS):**
- Both client and server present certificates
- Cryptographic proof of identity — no tokens needed
- Used in service mesh (Istio auto-provisions certificates)
```
Service A → presents cert → Service B verifies
Service B → presents cert → Service A verifies
Both authenticated → encrypted channel established
```

**API Key (internal services):**
```
X-Internal-Api-Key: <secret-key
```
- Simple but less secure — rotate regularly, store in secrets manager

**Security best practices:**
- Never store secrets in code — use AWS Secrets Manager / Vault
- Use HTTPS everywhere, even internal traffic
- Principle of least privilege for service accounts
- Validate and sanitize all inputs
- Rate limit authentication endpoints
- Audit log all sensitive operations

---

## 16. How do you monitor application health in production?

Monitoring ensures the application is **running correctly and performing well**.

**Key Monitoring Areas**

* CPU usage
* Memory usage
* Response time
* Error rate
* Database performance

**Monitoring Tools**

* Prometheus – collects metrics
* Grafana – visual dashboards
* Spring Boot Actuator – health endpoints
* New Relic – APM monitoring

**Example Health Endpoint**

```
/actuator/health
/actuator/metrics
```

These endpoints show application status.

---

## 17. How do you handle rollback strategies?

A **rollback strategy** restores the previous stable version if a deployment fails.

**Common Rollback Methods**

1. **Version rollback**

   * Deploy the previous stable build.

2. **Blue-Green rollback**

   * Switch traffic back to the old environment.

3. **Canary rollback**

   * If errors occur in small traffic rollout, revert immediately.

4. **Database rollback**

   * Use migration tools to revert schema changes.

**Tools**

* Jenkins
* Kubernetes
* Git

---

## 18. How do you manage database migrations?

Database migrations handle **schema changes safely across environments**.

**Best Practices**

1. Use migration tools
2. Version control schema changes
3. Apply migrations automatically during deployment
4. Maintain backward compatibility

**Popular Tools**

* Flyway
* Liquibase

**Example Migration**

```
V1__create_users_table.sql
V2__add_email_column.sql
```

Each migration runs **once and sequentially**.

---

## 19. How do you ensure zero downtime deployments?

Zero downtime means **users experience no service interruption during deployment**.

**Common Techniques**

1. **Blue-Green Deployment**

   * Two environments (Blue and Green)
   * Switch traffic after deployment.

2. **Rolling Deployment**

   * Update servers one by one.

3. **Canary Deployment**

   * Deploy to a small percentage of users first.

4. **Feature Flags**

   * Enable features without redeploying.

Tools:

* Kubernetes
* Docker

---

## 20. How do you manage logs across microservices?

Microservices generate logs from multiple services, so **centralized logging** is required.

**Approach**

1. Each service generates logs
2. Logs are collected centrally
3. Search and analyze logs

**Popular Logging Stack**

* Elasticsearch
* Logstash
* Kibana

This is called the **ELK Stack**.

Example flow:

```
Microservices → Logstash → Elasticsearch → Kibana Dashboard
```

---

## 21. How do you implement auto-scaling?

**Auto-scaling** automatically increases or decreases the number of servers based on load.

**Scaling Types**

1. **Horizontal scaling**

   * Add more servers

2. **Vertical scaling**

   * Increase CPU/RAM

**Example:**

If CPU usage > 70% → add new instances.

Tools:

* Kubernetes **HPA (Horizontal Pod Autoscaler)**
* Amazon Web Services **Auto Scaling Groups**

---

## 22. What is Rate Limiting and how it works?

**Rate limiting** controls how many requests a client can send in a specific time period.

It protects systems from:

* DDoS attacks
* Abuse
* API overload

**Example**

```
100 requests per minute per user
```

If a user exceeds the limit → request rejected with **HTTP 429 Too Many Requests**.

**Common Algorithms**

1. **Token Bucket**
2. **Leaky Bucket**
3. **Fixed Window Counter**
4. **Sliding Window**

**Tools**

* Redis
* Spring Cloud Gateway
* NGINX

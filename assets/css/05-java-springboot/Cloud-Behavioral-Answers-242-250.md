# ✅ 21) Cloud Computing (AWS, Azure)

## 242. What is IaaS vs PaaS vs SaaS?

**IaaS (Infrastructure as a Service)**: Rent virtual machines, storage, networks. You manage OS, runtime, apps.

**PaaS (Platform as a Service)**: Managed platform for deploying apps. Provider manages OS, runtime.

**SaaS (Software as a Service)**: Ready-to-use software. Provider manages everything.

**Example:**
```
IaaS: AWS EC2, Azure VMs
- You install Java, Tomcat, deploy app
- Full control, more management

PaaS: AWS Elastic Beanstalk, Azure App Service
- Upload JAR, platform handles deployment
- Less control, less management

SaaS: Gmail, Salesforce, Office 365
- Just use the software
- No control, no management
```

```java
// Deploying Spring Boot on PaaS (Elastic Beanstalk)
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
// Just upload JAR - platform handles scaling, load balancing
```

---

## 243. What AWS/Azure services do you use for Spring Boot apps?

**Compute**: EC2, ECS, Lambda (AWS) | VMs, Container Instances, Functions (Azure)

**Database**: RDS, DynamoDB | Azure SQL, Cosmos DB

**Cache**: ElastiCache | Azure Cache for Redis

**Storage**: S3 | Blob Storage

**Messaging**: SQS, SNS, Kinesis | Service Bus, Event Hubs

**Example:**
```java
// AWS S3 Integration
@Service
public class FileService {
    @Autowired
    private AmazonS3 s3Client;
    
    public void uploadFile(MultipartFile file) {
        s3Client.putObject("my-bucket", 
            file.getOriginalFilename(), 
            file.getInputStream(), 
            new ObjectMetadata());
    }
}
```

```properties
# application.properties
cloud.aws.credentials.access-key=${AWS_ACCESS_KEY}
cloud.aws.credentials.secret-key=${AWS_SECRET_KEY}
cloud.aws.region.static=us-east-1
```

```java
// AWS RDS (PostgreSQL)
spring.datasource.url=jdbc:postgresql://mydb.abc.rds.amazonaws.com:5432/mydb
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
```

---

## 244. How do you manage secrets in cloud (AWS Secrets Manager)?

Store sensitive data (passwords, API keys) securely in cloud secret management services instead of hardcoding.

**Example:**
```xml
<!-- pom.xml -->
<dependency>
    <groupId>com.amazonaws.secretsmanager</groupId>
    <artifactId>aws-secretsmanager-jdbc</artifactId>
</dependency>
```

```java
// AWS Secrets Manager
@Configuration
public class SecretsConfig {
    @Bean
    public String getDatabasePassword() {
        AWSSecretsManager client = AWSSecretsManagerClientBuilder
            .standard()
            .withRegion("us-east-1")
            .build();
        
        GetSecretValueRequest request = new GetSecretValueRequest()
            .withSecretId("prod/db/password");
        
        GetSecretValueResult result = client.getSecretValue(request);
        return result.getSecretString();
    }
}
```

```properties
# application.properties - Reference secrets
spring.datasource.url=jdbc:postgresql://mydb.rds.amazonaws.com:5432/db
spring.datasource.username=admin
spring.datasource.password=${DB_PASSWORD} # From Secrets Manager
```

```bash
# Store secret via AWS CLI
aws secretsmanager create-secret \
    --name prod/db/password \
    --secret-string "mySecurePassword123"
```

---

## 245. What is auto-scaling in cloud?

Automatically adjusts number of running instances based on demand (CPU, memory, request count).

**Example:**
```yaml
# AWS Auto Scaling Configuration
AutoScalingGroup:
  MinSize: 2
  MaxSize: 10
  DesiredCapacity: 3
  TargetTrackingScaling:
    TargetValue: 70  # CPU percentage
    ScaleOutCooldown: 300
    ScaleInCooldown: 300
```

```java
// Spring Boot app - stateless for auto-scaling
@RestController
public class OrderController {
    @Autowired
    private RedisTemplate redis; // Shared state
    
    @GetMapping("/orders")
    public List<Order> getOrders() {
        // No local state - can scale horizontally
        return orderService.findAll();
    }
}
```

**Scaling Policies:**
```
Scale Out: CPU > 70% → Add 2 instances
Scale In: CPU < 30% → Remove 1 instance

Example:
10 AM: 3 instances (normal load)
12 PM: 8 instances (lunch rush, high CPU)
3 PM: 4 instances (load decreased)
```

---

## 246. How do you design for disaster recovery?

Plan for failures with backups, replication, and multi-region deployment.

**Example:**
```yaml
# Multi-Region Architecture
Primary Region (us-east-1):
  - Application Servers
  - RDS Primary Database
  - S3 Bucket (versioning enabled)

Secondary Region (us-west-2):
  - Standby Application Servers
  - RDS Read Replica (auto-failover)
  - S3 Cross-Region Replication

Recovery Objectives:
  RTO (Recovery Time Objective): 15 minutes
  RPO (Recovery Point Objective): 5 minutes
```

```java
// Database failover configuration
@Configuration
public class DatabaseConfig {
    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        // Primary and failover endpoints
        config.setJdbcUrl("jdbc:postgresql://primary.rds.amazonaws.com:5432," +
                         "secondary.rds.amazonaws.com:5432/mydb");
        config.setConnectionTimeout(5000);
        return new HikariDataSource(config);
    }
}
```

```properties
# Backup strategy
spring.datasource.hikari.connection-timeout=5000
# Automated daily backups
aws.rds.backup.retention-period=7
aws.rds.backup.window=03:00-04:00
```

**DR Strategies:**
- **Backup & Restore**: Cheapest, slowest (RTO: hours)
- **Pilot Light**: Minimal resources running (RTO: 10-30 min)
- **Warm Standby**: Scaled-down version running (RTO: minutes)
- **Multi-Site Active-Active**: Full capacity both regions (RTO: seconds)

# ✅ 22) Behavioral & Team Handling

## 247. Tell me about a challenging production bug you fixed.

**Structure**: Situation → Problem → Action → Result (STAR method)

**Example Answer:**
```
Situation: E-commerce checkout failing for 20% of users during Black Friday.

Problem: 
- Intermittent 500 errors
- No clear pattern in logs
- High-pressure situation (revenue loss)

Action:
1. Analyzed logs - found database connection timeouts
2. Checked connection pool metrics - all connections exhausted
3. Identified N+1 query problem in order processing
4. Root cause: Lazy loading in loop fetching order items

Code Issue:
for (Order order : orders) {
    order.getItems().size(); // N+1 queries
}

Fix Applied:
@Query("SELECT o FROM Order o JOIN FETCH o.items WHERE o.id IN :ids")
List<Order> findOrdersWithItems(@Param("ids") List<Long> ids);

Result:
- Reduced DB queries from 1000+ to 1
- Response time: 5s → 200ms
- Zero errors, processed $2M in sales
- Added monitoring alerts for connection pool
```

---

## 248. How do you handle disagreements with team members?

**Approach**: Listen → Understand → Discuss → Data-driven decision → Respect outcome

**Example Answer:**
```
Situation: Disagreed on using microservices vs monolith for new feature.

My Approach:
1. Listen: Understood teammate's concerns about complexity
2. Present Data: Showed metrics on current monolith bottlenecks
3. Discuss Trade-offs:
   - Microservices: Better scaling, more complexity
   - Monolith: Simpler, but hitting limits
4. Compromise: Started with modular monolith, plan to extract later
5. Document Decision: Created ADR (Architecture Decision Record)

Outcome:
- Team aligned on approach
- Delivered feature on time
- Maintained good relationship
- Learned from teammate's perspective
```

**Key Principles:**
- Focus on problem, not person
- Use data and facts
- Be open to being wrong
- Find win-win solutions

---

## 249. How do you prioritize tasks when everything is urgent?

**Framework**: Impact vs Effort matrix + Business value

**Example Answer:**
```
Approach:
1. Assess Impact: Customer-facing > Internal tools
2. Evaluate Urgency: Deadlines, dependencies
3. Estimate Effort: Quick wins vs long tasks
4. Communicate: Set expectations with stakeholders

Real Example:
Monday morning - 5 "urgent" tasks:

Task A: Production bug (checkout broken)
  Impact: HIGH | Urgency: CRITICAL | Effort: 2h
  Priority: 1 - Do immediately

Task B: New feature demo for client
  Impact: HIGH | Urgency: HIGH | Effort: 4h
  Priority: 2 - Do today

Task C: Code review for teammate
  Impact: MEDIUM | Urgency: MEDIUM | Effort: 30m
  Priority: 3 - Quick win, do next

Task D: Refactoring old code
  Impact: LOW | Urgency: LOW | Effort: 8h
  Priority: 4 - Schedule for next sprint

Task E: Update documentation
  Impact: MEDIUM | Urgency: LOW | Effort: 1h
  Priority: 5 - Delegate or schedule

Action:
- Fixed production bug first (A)
- Communicated delays to stakeholders (B, C)
- Delegated documentation (E)
- Moved refactoring to backlog (D)
```

---

## 250. How do you mentor junior developers?

**Approach**: Teach → Guide → Review → Encourage independence

**Example Answer:**
```
My Mentoring Strategy:

1. Pair Programming (Week 1-2):
   - Work together on tasks
   - Explain thought process
   - Show debugging techniques

Example:
"Let's debug this NullPointerException together.
First, check the stack trace - line 45.
Now, let's add breakpoint and inspect variables.
See how user object is null? Let's trace back why."

2. Code Reviews (Ongoing):
   - Provide constructive feedback
   - Explain "why" not just "what"
   - Share best practices

Example Review Comment:
"Good start! Consider using Optional here to avoid NPE.
Instead of: if (user != null) { user.getName(); }
Try: Optional.ofNullable(user).map(User::getName).orElse('Unknown');
This makes null handling explicit. Here's a resource: [link]"

3. Assign Graduated Tasks:
   - Week 1: Bug fixes
   - Week 2: Small features
   - Month 2: Own feature end-to-end
   - Month 3: Lead small project

4. Regular 1-on-1s:
   - Discuss challenges
   - Answer questions
   - Set learning goals

5. Encourage Learning:
   - Share articles, courses
   - Recommend books (Effective Java, Clean Code)
   - Encourage experimentation

Result:
- Junior dev became productive in 1 month
- Independently delivered features by month 3
- Now mentoring others
```

**Key Principles:**
- Be patient and approachable
- Teach problem-solving, not just solutions
- Celebrate small wins
- Create safe environment for questions

---

**End of Answers (Questions 242-250)**

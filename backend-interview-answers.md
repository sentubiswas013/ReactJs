# Backend Interview Answers

1. **Can you discuss how you have used Spring Boot and microservices architecture to build scalable backend services, highlighting how you handled service discovery, configuration management, and data persistence?**

**Answer (30 seconds):**
* Built microservices using Spring Boot with Eureka for service discovery
* Used Spring Cloud Config for centralized configuration management
* Implemented JPA with MySQL for data persistence and Redis for caching
* Each service registers with Eureka server automatically

**Example Code:**
```java
@SpringBootApplication
@EnableEurekaClient
public class UserService {
    @Autowired
    private UserRepository userRepository;
    
    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id) {
        return userRepository.findById(id);
    }
}

// application.yml
eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka
```

2. **How would you design and implement a Docker-based CI/CD pipeline using Jenkins to deploy a full-stack application with zero downtime and automated rollback?**

**Answer (35 seconds):**
* Created Jenkins pipeline with Docker containers for build and deployment
* Used blue-green deployment strategy for zero downtime
* Implemented health checks and automated rollback on failure
* Pipeline builds Docker image, runs tests, deploys to staging, then production

**Example Code:**
```groovy
pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh 'docker build -t myapp:${BUILD_NUMBER} .'
            }
        }
        stage('Deploy') {
            steps {
                sh '''
                docker stop myapp-blue || true
                docker run -d --name myapp-green -p 8081:8080 myapp:${BUILD_NUMBER}
                # Health check
                sleep 30
                curl -f http://localhost:8081/health || exit 1
                # Switch traffic
                docker stop myapp-current || true
                docker run -d --name myapp-current -p 8080:8080 myapp:${BUILD_NUMBER}
                '''
            }
        }
    }
    post {
        failure {
            sh 'docker rollback-script.sh'
        }
    }
}
```

**Dockerfile:**
```dockerfile
FROM openjdk:11-jre-slim
COPY target/app.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

3. **What considerations do you take into account when orchestrating microservices with Kubernetes, including configuration management, rolling updates, and monitoring?**

**Answer (35 seconds):**
* Use ConfigMaps and Secrets for configuration management
* Implement rolling updates with readiness/liveness probes
* Deploy Prometheus and Grafana for monitoring and alerting
* Use Helm charts for consistent deployments across environments

**Example Code:**
```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: user-service
spec:
  replicas: 3
  strategy:
    type: RollingUpdate
    rollingUpdate:
      maxUnavailable: 1
      maxSurge: 1
  template:
    spec:
      containers:
      - name: user-service
        image: user-service:v1.2
        env:
        - name: DB_URL
          valueFrom:
            configMapKeyRef:
              name: app-config
              key: database.url
        readinessProbe:
          httpGet:
            path: /health
            port: 8080
          initialDelaySeconds: 30
```

4. **Explain your approach to database schema design and SQL query optimization for a microservices-based system when you encounter latency issues in PostgreSQL or MySQL.**

**Answer (40 seconds):**
* Design normalized schemas with proper indexing strategies
* Use connection pooling and read replicas for scaling
* Optimize queries with EXPLAIN plans and add composite indexes
* Implement database per service pattern to avoid cross-service queries

**Example Code:**
```sql
-- Optimized query with proper indexing
CREATE INDEX idx_user_email_status ON users(email, status);
CREATE INDEX idx_order_user_date ON orders(user_id, created_date);

-- Before optimization
SELECT * FROM orders o 
JOIN users u ON o.user_id = u.id 
WHERE u.email = 'user@example.com';

-- After optimization
SELECT o.id, o.amount, o.created_date 
FROM orders o 
INNER JOIN users u ON o.user_id = u.id 
WHERE u.email = 'user@example.com' 
AND u.status = 'active'
LIMIT 100;
```

**Connection Pool Config:**
```properties
# application.properties
spring.datasource.hikari.maximum-pool-size=20
spring.datasource.hikari.minimum-idle=5
spring.datasource.hikari.connection-timeout=30000
```
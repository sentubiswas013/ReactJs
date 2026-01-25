# 22. Cloud and Containerization 

## 1. What is containerization?

Containerization is a lightweight virtualization technology that packages applications and their dependencies into portable containers that can run consistently across different environments.

- **Lightweight virtualization:** Shares OS kernel, unlike VMs
- **Application packaging:** Bundles code, runtime, libraries, dependencies
- **Environment consistency:** Same behavior across dev, test, production
- **Resource efficiency:** Lower overhead than virtual machines
- **Portability:** Run anywhere containers are supported

Containers solve the "it works on my machine" problem by ensuring consistent runtime environments.

## 2. What is Docker?

Docker is a containerization platform that allows developers to package applications into lightweight, portable containers. It's the most popular containerization technology.

- **Container platform:** Create, deploy, and manage containers
- **Docker images:** Read-only templates for creating containers
- **Docker containers:** Running instances of images
- **Dockerfile:** Text file with instructions to build images
- **Docker Hub:** Cloud-based registry for sharing images

```dockerfile
# Dockerfile example
FROM openjdk:17-jre-slim
COPY target/myapp.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

```bash
# Docker commands
docker build -t myapp .              # Build image
docker run -p 8080:8080 myapp        # Run container
docker ps                            # List running containers
docker images                        # List images
```

## 3. What is Kubernetes?

Kubernetes is an open-source container orchestration platform that automates deployment, scaling, and management of containerized applications across clusters of machines.

- **Container orchestration:** Manages multiple containers
- **Automatic scaling:** Scale applications based on demand
- **Service discovery:** Containers can find and communicate
- **Load balancing:** Distributes traffic across containers
- **Self-healing:** Restarts failed containers automatically
- **Rolling updates:** Deploy new versions without downtime

**Key Components:**
- **Pods:** Smallest deployable units
- **Services:** Expose applications to network
- **Deployments:** Manage application replicas
- **ConfigMaps/Secrets:** Configuration management

```yaml
# Kubernetes deployment example
apiVersion: apps/v1
kind: Deployment
metadata:
  name: myapp-deployment
spec:
  replicas: 3
  selector:
    matchLabels:
      app: myapp
  template:
    metadata:
      labels:
        app: myapp
    spec:
      containers:
      - name: myapp
        image: myapp:latest
        ports:
        - containerPort: 8080
```

## 4. What is cloud computing?

Cloud computing delivers computing services over the internet, providing on-demand access to resources like servers, storage, databases, and software without managing physical infrastructure.

**Service Models:**
- **IaaS (Infrastructure as a Service):** Virtual machines, storage, networks
- **PaaS (Platform as a Service):** Development platforms, databases
- **SaaS (Software as a Service):** Ready-to-use applications

**Deployment Models:**
- **Public Cloud:** AWS, Azure, Google Cloud
- **Private Cloud:** Organization's dedicated infrastructure
- **Hybrid Cloud:** Combination of public and private

**Benefits:**
- **Cost efficiency:** Pay for what you use
- **Scalability:** Scale resources up/down on demand
- **Accessibility:** Access from anywhere
- **Reliability:** High availability and disaster recovery

## 5. What is distributed system?

A distributed system is a collection of independent computers that work together as a single coherent system, appearing to users as one unified system despite running on multiple machines.

**Characteristics:**
- **Multiple nodes:** Components run on different machines
- **Network communication:** Nodes communicate over network
- **Shared state:** Coordinate to maintain consistency
- **Fault tolerance:** Continue operating despite failures
- **Scalability:** Add more nodes to handle increased load

**Challenges:**
- **Network failures:** Partial failures and partitions
- **Consistency:** Keeping data synchronized across nodes
- **Latency:** Network communication delays
- **Complexity:** Debugging and monitoring difficulties

**Examples:**
- Microservices architectures
- Database clusters
- Content delivery networks (CDNs)
- Web applications with load balancers

## 6. What is load balancing?

Load balancing distributes incoming network traffic across multiple servers to ensure no single server becomes overwhelmed, improving application availability and performance.

**Load Balancing Algorithms:**
- **Round Robin:** Requests distributed sequentially
- **Least Connections:** Route to server with fewest active connections
- **Weighted Round Robin:** Assign weights based on server capacity
- **IP Hash:** Route based on client IP hash
- **Health Check:** Only route to healthy servers

**Types:**
- **Layer 4 (Transport):** Routes based on IP and port
- **Layer 7 (Application):** Routes based on HTTP content
- **Hardware Load Balancers:** Dedicated physical devices
- **Software Load Balancers:** Nginx, HAProxy, AWS ALB

```nginx
# Nginx load balancer configuration
upstream backend {
    server backend1.example.com weight=3;
    server backend2.example.com weight=2;
    server backend3.example.com weight=1;
}

server {
    listen 80;
    location / {
        proxy_pass http://backend;
    }
}
```

## 7. What are caching strategies?

Caching strategies determine when and how to store frequently accessed data in fast storage to improve application performance and reduce load on backend systems.

**Common Caching Strategies:**

**Cache-Aside (Lazy Loading):**
- Application manages cache directly
- Load data into cache on cache miss
- Good for read-heavy workloads

**Write-Through:**
- Write to cache and database simultaneously
- Ensures data consistency
- Higher write latency

**Write-Behind (Write-Back):**
- Write to cache immediately, database later
- Better write performance
- Risk of data loss

**Refresh-Ahead:**
- Proactively refresh cache before expiration
- Reduces cache misses
- Complex to implement

```java
// Cache-aside pattern example
@Service
public class UserService {
    
    @Autowired
    private RedisTemplate<String, User> redisTemplate;
    
    @Autowired
    private UserRepository userRepository;
    
    public User getUser(Long id) {
        String key = "user:" + id;
        
        // Try cache first
        User user = redisTemplate.opsForValue().get(key);
        if (user != null) {
            return user; // Cache hit
        }
        
        // Cache miss - load from database
        user = userRepository.findById(id);
        if (user != null) {
            // Store in cache for future requests
            redisTemplate.opsForValue().set(key, user, Duration.ofMinutes(30));
        }
        
        return user;
    }
}
```

**Cache Levels:**
- **Browser Cache:** Client-side caching
- **CDN Cache:** Geographic distribution
- **Application Cache:** In-memory caching (Redis, Memcached)
- **Database Cache:** Query result caching

Choose caching strategy based on read/write patterns, consistency requirements, and performance goals.
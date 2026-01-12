# 🔹 Cloud and Containerization

### Question 306: What is containerization?

**Answer (30 seconds):**
* Technology that packages applications with their dependencies into containers
* **Isolation**: Applications run in isolated environments
* **Portability**: Containers run consistently across different environments
* **Lightweight**: Share OS kernel, more efficient than virtual machines
* **Scalability**: Easy to scale up/down container instances
* Popular platforms: Docker, Podman, containerd

```dockerfile
# Dockerfile for Java application
FROM openjdk:17-jre-slim
COPY target/myapp.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

---

### Question 307: What is Docker?

**Answer (30 seconds):**
* Platform for developing, shipping, and running applications in containers
* **Images**: Read-only templates for creating containers
* **Containers**: Running instances of Docker images
* **Dockerfile**: Text file with instructions to build images
* **Registry**: Repository for storing and sharing images (Docker Hub)
* Simplifies deployment and environment consistency

```bash
# Build and run Java application
docker build -t myapp:latest .
docker run -p 8080:8080 myapp:latest

# Docker Compose for multi-service setup
version: '3'
services:
  app:
    build: .
    ports: ["8080:8080"]
  db:
    image: mysql:8.0
```

---

### Question 308: What is Kubernetes?

**Answer (35 seconds):**
* Container orchestration platform for managing containerized applications
* **Pods**: Smallest deployable units containing one or more containers
* **Services**: Expose applications and provide load balancing
* **Deployments**: Manage application lifecycle and scaling
* **Auto-scaling**: Automatically scale based on resource usage
* **Self-healing**: Restart failed containers automatically

```yaml
# Kubernetes deployment
apiVersion: apps/v1
kind: Deployment
metadata:
  name: java-app
spec:
  replicas: 3
  selector:
    matchLabels:
      app: java-app
  template:
    spec:
      containers:
      - name: app
        image: myapp:latest
        ports:
        - containerPort: 8080
```

---

### Question 309: What is cloud computing?

**Answer (30 seconds):**
* Delivery of computing services over the internet
* **IaaS**: Infrastructure as a Service (virtual machines, storage)
* **PaaS**: Platform as a Service (runtime environments, databases)
* **SaaS**: Software as a Service (complete applications)
* **Benefits**: Scalability, cost-effectiveness, global accessibility
* Major providers: AWS, Azure, Google Cloud Platform

```java
// Cloud-native Java application
@SpringBootApplication
@EnableCloudConfig
public class CloudApplication {
    @Value("${cloud.database.url}")
    private String databaseUrl;
    
    public static void main(String[] args) {
        SpringApplication.run(CloudApplication.class, args);
    }
}
```

---

### Question 310: What is distributed system?

**Answer (35 seconds):**
* System where components are located on different networked computers
* **Communication**: Components communicate via message passing
* **Coordination**: Distributed consensus and synchronization
* **Fault Tolerance**: System continues operating despite component failures
* **Scalability**: Add more machines to handle increased load
* **Challenges**: Network partitions, consistency, latency

```java
// Distributed system example - microservices communication
@RestController
public class OrderController {
    @Autowired
    private PaymentServiceClient paymentService;
    
    @PostMapping("/orders")
    public Order createOrder(@RequestBody Order order) {
        Payment payment = paymentService.processPayment(order.getPayment());
        return orderService.createOrder(order, payment);
    }
}
```

---

### Question 311: What is load balancing?

**Answer (30 seconds):**
* Technique to distribute incoming requests across multiple servers
* **Round Robin**: Requests distributed sequentially
* **Least Connections**: Route to server with fewest active connections
* **Weighted**: Assign different weights to servers based on capacity
* **Health Checks**: Remove unhealthy servers from rotation
* Improves availability, performance, and fault tolerance

```java
// Spring Cloud Load Balancer
@Configuration
public class LoadBalancerConfig {
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

// Usage - automatically load balances
restTemplate.getForObject("http://user-service/users/1", User.class);
```

---

### Question 312: What is caching strategies?

**Answer (35 seconds):**
* Techniques to store frequently accessed data for faster retrieval
* **Cache-Aside**: Application manages cache manually
* **Write-Through**: Write to cache and database simultaneously
* **Write-Behind**: Write to cache first, database later
* **Refresh-Ahead**: Proactively refresh cache before expiration
* **Levels**: L1 (application), L2 (distributed), L3 (CDN)

```java
// Spring Cache example
@Service
public class UserService {
    @Cacheable("users")
    public User findById(Long id) {
        return userRepository.findById(id);
    }
    
    @CacheEvict("users")
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
```

# 🔵 21. Security
---
# 🔹 Java Security

### Question 313: What is Java security model?

**Answer (35 seconds):**
* Comprehensive security framework built into Java platform
* **Bytecode Verification**: Ensures code follows Java language rules
* **Class Loading**: Secure loading and verification of classes
* **Security Manager**: Controls access to system resources
* **Access Control**: Permission-based security for operations
* **Cryptography**: Built-in encryption and digital signature support
* **Sandbox**: Restricted execution environment for untrusted code

```java
// Security Manager example
public class MySecurityManager extends SecurityManager {
    @Override
    public void checkRead(String file) {
        if (file.startsWith("/etc/")) {
            throw new SecurityException("Access denied to system files");
        }
        super.checkRead(file);
    }
}

// Enable security manager
System.setSecurityManager(new MySecurityManager());
```

---

### Question 314: What is sandbox in Java?

**Answer (25 seconds):**
* Restricted execution environment for running untrusted code
* **Applets**: Web-based Java programs run in browser sandbox
* **Limited Access**: Restricted file system, network, and system access
* **Security Policies**: Define what operations are allowed
* **Isolation**: Prevents malicious code from affecting host system
* Largely replaced by modern web technologies

```java
// Applet sandbox restrictions
public class MyApplet extends Applet {
    public void init() {
        // Cannot read local files
        // Cannot make network connections to other hosts
        // Cannot execute system commands
        // Limited to browser security policies
    }
}
```

---

### Question 315: What is bytecode verification?

**Answer (30 seconds):**
* Process that ensures Java bytecode follows language safety rules
* **Static Analysis**: Checks code structure without execution
* **Type Safety**: Verifies correct use of data types
* **Control Flow**: Ensures proper program flow and stack usage
* **Security**: Prevents buffer overflows and illegal memory access
* Performed by JVM class loader before class execution

```java
// Bytecode verification checks:
// 1. Stack overflow/underflow prevention
// 2. Type consistency
// 3. Proper exception handling
// 4. Valid bytecode instructions

// Example: This would fail verification
// Attempting to call method on wrong type
// String s = new Integer(5);
// s.charAt(0); // Type mismatch caught by verifier
```

---

### Question 316: What is class loader security?

**Answer (30 seconds):**
* Security mechanism that controls how classes are loaded into JVM
* **Namespace Isolation**: Classes from different loaders are isolated
* **Parent Delegation**: Child loaders delegate to parent first
* **Trust Boundaries**: Different security policies for different sources
* **Code Source**: Associates classes with their origin (URL, certificates)
* Prevents malicious class replacement and ensures code integrity

```java
// Custom secure class loader
public class SecureClassLoader extends ClassLoader {
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        // Verify class signature
        byte[] classData = loadClassData(name);
        if (!verifySignature(classData)) {
            throw new SecurityException("Invalid class signature");
        }
        return defineClass(name, classData, 0, classData.length);
    }
}
```

---

### Question 317: What is the security manager?

**Answer (30 seconds):**
* Component that enforces security policies in Java applications
* **Permission Checks**: Controls access to system resources
* **Policy Files**: Define what operations are allowed
* **Runtime Security**: Enforces security at runtime, not compile time
* **Deprecated**: Removed in Java 17, replaced by module system
* Used to control file access, network connections, system properties

```java
// Security Manager usage (deprecated)
SecurityManager sm = System.getSecurityManager();
if (sm != null) {
    sm.checkRead("/etc/passwd"); // Throws SecurityException if not allowed
}

// Policy file example
grant {
    permission java.io.FilePermission "/tmp/*", "read,write";
    permission java.net.SocketPermission "localhost:8080", "connect";
};
```

---

### Question 318: What are digital signatures in Java?

**Answer (30 seconds):**
* Cryptographic mechanism to verify code authenticity and integrity
* **JAR Signing**: Sign JAR files with private key
* **Certificate Verification**: Verify signature with public key
* **Code Integrity**: Ensures code hasn't been tampered with
* **Trust**: Establishes trust in code publisher
* Required for applets and some enterprise deployments

```java
// Creating digital signature
Signature signature = Signature.getInstance("SHA256withRSA");
signature.initSign(privateKey);
signature.update(data);
byte[] digitalSignature = signature.sign();

// Verifying signature
signature.initVerify(publicKey);
signature.update(data);
boolean isValid = signature.verify(digitalSignature);
```

---

### Question 319: What is encryption and decryption in Java?

**Answer (35 seconds):**
* Process of converting data to/from unreadable format for security
* **Symmetric**: Same key for encryption and decryption (AES)
* **Asymmetric**: Different keys for encryption/decryption (RSA)
* **JCA**: Java Cryptography Architecture provides APIs
* **Key Management**: Secure generation and storage of keys
* **Common Uses**: Password storage, data transmission, file protection

```java
// AES encryption example
Cipher cipher = Cipher.getInstance("AES");
KeyGenerator keyGen = KeyGenerator.getInstance("AES");
SecretKey secretKey = keyGen.generateKey();

// Encrypt
cipher.init(Cipher.ENCRYPT_MODE, secretKey);
byte[] encrypted = cipher.doFinal("Hello World".getBytes());

// Decrypt
cipher.init(Cipher.DECRYPT_MODE, secretKey);
byte[] decrypted = cipher.doFinal(encrypted);
```

---

### Question 320: What is SSL/TLS in Java?

**Answer (35 seconds):**
* Secure communication protocols for encrypted data transmission
* **HTTPS**: HTTP over SSL/TLS for secure web communication
* **Handshake**: Establishes secure connection and exchanges keys
* **Certificates**: Verify server identity and establish trust
* **JSSE**: Java Secure Socket Extension provides SSL/TLS support
* **KeyStore/TrustStore**: Manage certificates and keys

```java
// SSL/TLS client example
SSLContext sslContext = SSLContext.getInstance("TLS");
sslContext.init(null, null, null);

SSLSocketFactory factory = sslContext.getSocketFactory();
SSLSocket socket = (SSLSocket) factory.createSocket("example.com", 443);

// HTTPS with RestTemplate
RestTemplate restTemplate = new RestTemplate();
ResponseEntity<String> response = restTemplate.getForEntity(
    "https://api.example.com/data", String.class);
```

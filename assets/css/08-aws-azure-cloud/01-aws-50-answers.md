# AWS Cloud - Interview Questions Answers (1-8)

## 1. AWS Fundamentals (1-8)

### 1. What is AWS and what are its key benefits?

**Answer:**
AWS (Amazon Web Services) is a comprehensive cloud computing platform provided by Amazon that offers over 200 services including computing power, storage, databases, networking, and more on a pay-as-you-go basis.

**Key Benefits:**
- **Cost-Effective**: Pay only for what you use, no upfront costs
- **Scalability**: Scale resources up or down based on demand
- **Reliability**: 99.99% uptime SLA with multiple availability zones
- **Security**: Enterprise-grade security with compliance certifications
- **Global Reach**: Data centers in 30+ regions worldwide
- **Flexibility**: Wide range of services and programming language support

**Minimal Example:**
```java
// AWS SDK for Java - Basic S3 client initialization
import software.amazon.awssdk.services.s3.S3Client;

S3Client s3 = S3Client.builder()
    .region(Region.US_EAST_1)
    .build();
```

---

### 2. Explain AWS Global Infrastructure - Regions, Availability Zones, and Edge Locations

**Answer:**
AWS Global Infrastructure is designed for high availability, fault tolerance, and low latency.

**Components:**
- **Regions**: Geographic areas (e.g., us-east-1, eu-west-1) containing multiple isolated data centers. Currently 30+ regions worldwide.
- **Availability Zones (AZs)**: Isolated data centers within a region (2-6 per region), connected via low-latency links. Each AZ has independent power, cooling, and networking.
- **Edge Locations**: CDN endpoints (400+) for CloudFront to cache content closer to users for faster delivery.

**Example Configuration:**
```java
// Specifying region in AWS SDK
import software.amazon.awssdk.regions.Region;

Region region = Region.US_EAST_1; // N. Virginia
Region region2 = Region.EU_WEST_1; // Ireland

// Multi-AZ deployment example
RdsInstanceConfig config = RdsInstanceConfig.builder()
    .multiAZ(true) // Deploys across multiple AZs
    .build();
```

---

### 3. What is IAM and how do you manage access control in AWS?

**Answer:**
IAM (Identity and Access Management) is a service that controls who can access AWS resources and what actions they can perform. It follows the principle of least privilege.

**Key Components:**
- **Users**: Individual identities with credentials
- **Groups**: Collection of users with shared permissions
- **Roles**: Temporary credentials for services or federated users
- **Policies**: JSON documents defining permissions

**Example Policy:**
```json
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Effect": "Allow",
      "Action": [
        "s3:GetObject",
        "s3:PutObject"
      ],
      "Resource": "arn:aws:s3:::my-bucket/*"
    }
  ]
}
```

```java
// Programmatic access using IAM credentials
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;

ProfileCredentialsProvider credentialsProvider = 
    ProfileCredentialsProvider.create();
```

---

### 4. What is the difference between IAM Role and IAM User?

**Answer:**

**IAM User:**
- Permanent identity with long-term credentials (access key/secret key)
- Used for individual people or applications
- Has username and password for console access
- Credentials stored and managed by user

**IAM Role:**
- Temporary security credentials (auto-rotated)
- Assumed by AWS services, applications, or federated users
- No permanent credentials
- More secure for service-to-service communication

**Example:**
```java
// IAM User approach (less secure)
BasicAWSCredentials credentials = new BasicAWSCredentials(
    "AKIAIOSFODNN7EXAMPLE", 
    "wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY"
);

// IAM Role approach (recommended for EC2/Lambda)
InstanceProfileCredentialsProvider roleCredentials = 
    InstanceProfileCredentialsProvider.create();

S3Client s3 = S3Client.builder()
    .credentialsProvider(roleCredentials)
    .build();
```

---

### 5. What is AWS Free Tier and what services are included?

**Answer:**
AWS Free Tier provides limited free usage of AWS services for new customers (12 months) and some services that are always free.

**Three Types:**
- **12 Months Free**: 750 hours EC2 t2.micro, 5GB S3 storage, 750 hours RDS
- **Always Free**: 1M Lambda requests/month, 1GB DynamoDB storage, 10 custom CloudWatch metrics
- **Trials**: Short-term free trials for services like SageMaker, Redshift

**Example Usage:**
```java
// Free tier eligible EC2 instance
InstanceType freeInstance = InstanceType.T2_MICRO;

// Free tier Lambda (1M requests/month free)
@Handler
public String handleRequest(Map<String, String> event, Context context) {
    return "Hello from Lambda!";
}

// Free tier DynamoDB (25GB storage free)
DynamoDbClient dynamoDB = DynamoDbClient.create();
```

---

### 6. Explain the AWS Shared Responsibility Model

**Answer:**
The Shared Responsibility Model defines security responsibilities between AWS and the customer.

**AWS Responsibility (Security OF the Cloud):**
- Physical infrastructure (data centers, hardware)
- Network infrastructure
- Hypervisor and virtualization layer
- Managed services infrastructure

**Customer Responsibility (Security IN the Cloud):**
- Data encryption and protection
- IAM and access management
- OS patching and updates (for EC2)
- Application security
- Network configuration (Security Groups, NACLs)

**Example Implementation:**
```java
// Customer responsibility: Encrypt data before storing
import javax.crypto.Cipher;

// Encrypt sensitive data
String encryptedData = encrypt(sensitiveData);

// Store in S3 with server-side encryption (shared responsibility)
PutObjectRequest request = PutObjectRequest.builder()
    .bucket("my-bucket")
    .key("data.txt")
    .serverSideEncryption(ServerSideEncryption.AES256) // AWS manages keys
    .build();

s3Client.putObject(request, RequestBody.fromString(encryptedData));
```

---

### 7. What is AWS CLI and how do you configure it?

**Answer:**
AWS CLI (Command Line Interface) is a unified tool to manage AWS services from the command line, enabling automation through scripts.

**Configuration Steps:**
1. Install AWS CLI
2. Run `aws configure`
3. Provide Access Key ID, Secret Access Key, Region, Output format

**Example Commands:**
```bash
# Configure AWS CLI
aws configure
# AWS Access Key ID: AKIAIOSFODNN7EXAMPLE
# AWS Secret Access Key: wJalrXUtnFEMI/K7MDENG/bPxRfiCY
# Default region: us-east-1
# Default output format: json

# List S3 buckets
aws s3 ls

# Upload file to S3
aws s3 cp myfile.txt s3://my-bucket/

# Create EC2 instance
aws ec2 run-instances --image-id ami-0c55b159cbfafe1f0 --instance-type t2.micro

# List Lambda functions
aws lambda list-functions
```

**Java equivalent:**
```java
// Execute AWS CLI from Java
ProcessBuilder pb = new ProcessBuilder("aws", "s3", "ls");
Process process = pb.start();
```

---

### 8. What is AWS SDK and how do you use it in Java applications?

**Answer:**
AWS SDK (Software Development Kit) provides libraries to interact with AWS services programmatically. The AWS SDK for Java allows Java applications to access AWS services using Java APIs.

**Key Features:**
- Type-safe API calls
- Automatic retry logic
- Built-in authentication
- Support for sync and async operations

**Maven Dependency:**
```xml
<dependency>
    <groupId>software.amazon.awssdk</groupId>
    <artifactId>s3</artifactId>
    <version>2.20.0</version>
</dependency>
```

**Example Usage:**
```java
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.regions.Region;

public class AwsSdkExample {
    
    public static void main(String[] args) {
        // Initialize S3 client
        S3Client s3 = S3Client.builder()
            .region(Region.US_EAST_1)
            .build();
        
        // Upload file
        PutObjectRequest request = PutObjectRequest.builder()
            .bucket("my-bucket")
            .key("file.txt")
            .build();
        
        s3.putObject(request, RequestBody.fromString("Hello AWS!"));
        
        // List buckets
        ListBucketsResponse response = s3.listBuckets();
        response.buckets().forEach(bucket -> 
            System.out.println(bucket.name())
        );
    }
}
```

**Spring Boot Integration:**
```java
@Configuration
public class AwsConfig {
    
    @Bean
    public S3Client s3Client() {
        return S3Client.builder()
            .region(Region.US_EAST_1)
            .credentialsProvider(DefaultCredentialsProvider.create())
            .build();
    }
}

@Service
public class S3Service {
    
    @Autowired
    private S3Client s3Client;
    
    public void uploadFile(String bucket, String key, byte[] data) {
        s3Client.putObject(
            PutObjectRequest.builder().bucket(bucket).key(key).build(),
            RequestBody.fromBytes(data)
        );
    }
}
```

---

---

## 2. Compute Services (9-14)

### 9. What is EC2 and what are the different instance types?

**Answer:**
EC2 (Elastic Compute Cloud) is a web service that provides resizable compute capacity in the cloud. It's essentially a virtual server that you can launch in minutes and scale up or down based on your needs.

**Instance Type Categories:**
- **General Purpose** (t2, t3, m5): Balanced CPU, memory, networking - web servers, small databases
- **Compute Optimized** (c5, c6): High-performance processors - batch processing, gaming servers
- **Memory Optimized** (r5, x1): Large memory - in-memory databases, big data analytics
- **Storage Optimized** (i3, d2): High sequential read/write - data warehousing, Hadoop
- **Accelerated Computing** (p3, g4): GPU instances - machine learning, graphics rendering

**Example:**
```java
// Launch EC2 instance using AWS SDK
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.*;

Ec2Client ec2 = Ec2Client.create();

RunInstancesRequest request = RunInstancesRequest.builder()
    .imageId("ami-0c55b159cbfafe1f0") // Amazon Linux 2
    .instanceType(InstanceType.T2_MICRO)
    .minCount(1)
    .maxCount(1)
    .keyName("my-key-pair")
    .build();

RunInstancesResponse response = ec2.runInstances(request);
```

---

### 10. How do you deploy a Spring Boot application on EC2?

**Answer:**
Deploying a Spring Boot application on EC2 involves launching an instance, installing Java, transferring your JAR file, and running it.

**Deployment Steps:**
1. Launch EC2 instance (Amazon Linux 2 or Ubuntu)
2. Install Java Runtime (JDK 11 or 17)
3. Upload Spring Boot JAR file
4. Configure security groups (open port 8080)
5. Run the application
6. Optional: Set up as systemd service for auto-restart

**Example Commands:**
```bash
# SSH into EC2
ssh -i my-key.pem ec2-user@ec2-ip-address

# Install Java
sudo yum install java-17-amazon-corretto -y

# Upload JAR (from local machine)
scp -i my-key.pem app.jar ec2-user@ec2-ip:/home/ec2-user/

# Run Spring Boot app
java -jar app.jar

# Run in background
nohup java -jar app.jar > app.log 2>&1 &
```

**Systemd Service:**
```bash
# /etc/systemd/system/myapp.service
[Unit]
Description=Spring Boot App

[Service]
User=ec2-user
ExecStart=/usr/bin/java -jar /home/ec2-user/app.jar
SuccessExitStatus=143

[Install]
WantedBy=multi-user.target
```

---

### 11. What is AWS Lambda and when would you use it?

**Answer:**
AWS Lambda is a serverless compute service that runs your code in response to events without provisioning or managing servers. You pay only for the compute time you consume.

**When to Use Lambda:**
- Event-driven processing (S3 uploads, DynamoDB changes)
- Scheduled tasks (cron jobs)
- API backends (with API Gateway)
- Real-time file/stream processing
- Microservices with unpredictable traffic
- Short-running tasks (max 15 minutes)

**Example:**
```java
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class HelloLambda implements RequestHandler<Map<String, String>, String> {
    
    @Override
    public String handleRequest(Map<String, String> event, Context context) {
        String name = event.get("name");
        return "Hello, " + name + "!";
    }
}
```

**Maven Dependency:**
```xml
<dependency>
    <groupId>com.amazonaws</groupId>
    <artifactId>aws-lambda-java-core</artifactId>
    <version>1.2.2</version>
</dependency>
```

---

### 12. Explain the difference between EC2, Lambda, and Elastic Beanstalk

**Answer:**

**EC2 (Infrastructure as a Service):**
- Full control over virtual servers
- You manage OS, runtime, scaling
- Pay for running instances (hourly)
- Best for: Long-running applications, custom configurations

**Lambda (Function as a Service):**
- Serverless, no server management
- Event-driven, auto-scales
- Pay per request and execution time
- Best for: Event processing, microservices, short tasks

**Elastic Beanstalk (Platform as a Service):**
- Managed application platform
- Automatically handles deployment, scaling, monitoring
- Uses EC2 under the hood
- Best for: Quick deployments, standard web applications

**Comparison Example:**
```java
// EC2: You manage everything
// SSH, install Java, deploy JAR, configure load balancer

// Lambda: Just code
public String handleRequest(String input, Context context) {
    return "Processed: " + input;
}

// Elastic Beanstalk: Deploy and forget
// Just upload JAR/WAR, Beanstalk handles rest
eb init
eb create
eb deploy
```

---

### 13. What is AWS Elastic Beanstalk and how does it simplify deployment?

**Answer:**
Elastic Beanstalk is a Platform as a Service (PaaS) that automatically handles deployment, capacity provisioning, load balancing, auto-scaling, and health monitoring for your applications.

**Key Features:**
- Supports Java, .NET, Node.js, Python, Ruby, Go, Docker
- Automatic capacity provisioning and load balancing
- Built-in monitoring and health checks
- Easy rollback to previous versions
- You retain full control of underlying resources

**Deployment Example:**
```bash
# Install EB CLI
pip install awsebcli

# Initialize Beanstalk application
eb init -p java-17 my-app --region us-east-1

# Create environment and deploy
eb create production-env

# Deploy new version
eb deploy

# Check status
eb status

# View logs
eb logs
```

**Configuration File (.ebextensions/options.config):**
```yaml
option_settings:
  aws:elasticbeanstalk:application:environment:
    SERVER_PORT: 5000
    SPRING_PROFILES_ACTIVE: production
  aws:autoscaling:launchconfiguration:
    InstanceType: t2.micro
```

---

### 14. How do you implement serverless architecture using AWS Lambda for Java applications?

**Answer:**
Serverless architecture with Lambda involves breaking your application into small, independent functions triggered by events, eliminating server management.

**Architecture Components:**
- **Lambda Functions**: Business logic
- **API Gateway**: REST API endpoints
- **DynamoDB**: NoSQL database
- **S3**: File storage
- **EventBridge/SNS**: Event routing

**Example Implementation:**
```java
// Lambda Handler
import com.amazonaws.services.lambda.runtime.*;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

public class UserHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    
    private final DynamoDbClient dynamoDB = DynamoDbClient.create();
    
    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, Context context) {
        String userId = request.getPathParameters().get("id");
        
        // Get from DynamoDB
        GetItemResponse response = dynamoDB.getItem(GetItemRequest.builder()
            .tableName("Users")
            .key(Map.of("userId", AttributeValue.builder().s(userId).build()))
            .build());
        
        return new APIGatewayProxyResponseEvent()
            .withStatusCode(200)
            .withBody("{\"user\":" + response.item() + "}");
    }
}
```

**SAM Template (template.yaml):**
```yaml
AWSTemplateFormatVersion: '2010-09-09'
Transform: AWS::Serverless-2016-10-31

Resources:
  UserFunction:
    Type: AWS::Serverless::Function
    Properties:
      Handler: com.example.UserHandler::handleRequest
      Runtime: java17
      MemorySize: 512
      Timeout: 30
      Events:
        GetUser:
          Type: Api
          Properties:
            Path: /users/{id}
            Method: get
```

**Deploy:**
```bash
sam build
sam deploy --guided
```

---

## 3. Storage Services (15-20)

### 15. What is Amazon S3 and what are its storage classes?

**Answer:**
Amazon S3 (Simple Storage Service) is an object storage service that offers industry-leading scalability, data availability, security, and performance. You can store and retrieve any amount of data at any time from anywhere on the web.

**Storage Classes:**
- **S3 Standard**: Frequently accessed data, low latency, high throughput
- **S3 Intelligent-Tiering**: Automatic cost optimization by moving data between tiers
- **S3 Standard-IA** (Infrequent Access): Less frequent access, lower cost, retrieval fee
- **S3 One Zone-IA**: Single AZ storage, 20% cheaper than Standard-IA
- **S3 Glacier Instant Retrieval**: Archive with millisecond retrieval
- **S3 Glacier Flexible Retrieval**: Archive with minutes to hours retrieval
- **S3 Glacier Deep Archive**: Lowest cost, 12-hour retrieval

**Example:**
```java
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

S3Client s3 = S3Client.create();

// Upload with storage class
PutObjectRequest request = PutObjectRequest.builder()
    .bucket("my-bucket")
    .key("document.pdf")
    .storageClass(StorageClass.STANDARD_IA)
    .build();

s3.putObject(request, RequestBody.fromFile(new File("document.pdf")));
```

---

### 16. How do you upload and download files from S3 using Spring Boot?

**Answer:**
Spring Boot integrates with AWS SDK to perform S3 operations. You configure the S3 client as a bean and inject it into your service layer to handle file uploads and downloads.

**Maven Dependency:**
```xml
<dependency>
    <groupId>software.amazon.awssdk</groupId>
    <artifactId>s3</artifactId>
    <version>2.20.0</version>
</dependency>
```

**Configuration:**
```java
@Configuration
public class S3Config {
    @Bean
    public S3Client s3Client() {
        return S3Client.builder()
            .region(Region.US_EAST_1)
            .build();
    }
}
```

**Service Implementation:**
```java
@Service
public class S3Service {
    @Autowired
    private S3Client s3Client;
    
    private static final String BUCKET = "my-bucket";
    
    // Upload
    public String uploadFile(MultipartFile file) throws IOException {
        String key = UUID.randomUUID() + "-" + file.getOriginalFilename();
        s3Client.putObject(
            PutObjectRequest.builder().bucket(BUCKET).key(key).build(),
            RequestBody.fromBytes(file.getBytes())
        );
        return key;
    }
    
    // Download
    public byte[] downloadFile(String key) {
        ResponseBytes<GetObjectResponse> response = s3Client.getObjectAsBytes(
            GetObjectRequest.builder().bucket(BUCKET).key(key).build()
        );
        return response.asByteArray();
    }
}
```

**Controller:**
```java
@RestController
@RequestMapping("/api/files")
public class FileController {
    @Autowired
    private S3Service s3Service;
    
    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam MultipartFile file) throws IOException {
        String key = s3Service.uploadFile(file);
        return ResponseEntity.ok(key);
    }
    
    @GetMapping("/download/{key}")
    public ResponseEntity<byte[]> download(@PathVariable String key) {
        byte[] data = s3Service.downloadFile(key);
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + key + "\"")
            .body(data);
    }
}
```

---

### 17. What is the difference between S3, EBS, and EFS?

**Answer:**

**S3 (Simple Storage Service):**
- Object storage (files as objects)
- Accessed via HTTP/HTTPS (REST API)
- Unlimited storage, 5TB max object size
- Use case: Static websites, backups, media files, data lakes

**EBS (Elastic Block Store):**
- Block storage (like a hard drive)
- Attached to single EC2 instance
- Fixed size (1GB to 16TB)
- Use case: Database storage, boot volumes, high-performance apps

**EFS (Elastic File System):**
- Network file system (NFS)
- Shared across multiple EC2 instances
- Auto-scales, pay for what you use
- Use case: Shared application data, content management, web serving

**Comparison Example:**
```java
// S3 - Object storage via API
S3Client s3 = S3Client.create();
s3.putObject(request, RequestBody.fromFile(file));

// EBS - Block storage (mounted to EC2)
// /dev/xvdf mounted to /data
// Used like regular disk: File file = new File("/data/myfile.txt");

// EFS - Network file system (mounted to multiple EC2)
// Mounted to /mnt/efs
// Shared across instances: File shared = new File("/mnt/efs/shared.txt");
```

---

### 18. Explain S3 bucket policies and CORS configuration

**Answer:**

**S3 Bucket Policies:**
JSON-based access policies that define who can access your bucket and what actions they can perform. Applied at bucket level.

**CORS (Cross-Origin Resource Sharing):**
Allows web applications running in one domain to access S3 resources in another domain.

**Bucket Policy Example:**
```json
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Sid": "PublicReadGetObject",
      "Effect": "Allow",
      "Principal": "*",
      "Action": "s3:GetObject",
      "Resource": "arn:aws:s3:::my-bucket/*"
    }
  ]
}
```

**CORS Configuration:**
```json
[
  {
    "AllowedHeaders": ["*"],
    "AllowedMethods": ["GET", "PUT", "POST"],
    "AllowedOrigins": ["https://example.com"],
    "ExposeHeaders": ["ETag"],
    "MaxAgeSeconds": 3000
  }
]
```

**Java Implementation:**
```java
// Set bucket policy
PutBucketPolicyRequest policyRequest = PutBucketPolicyRequest.builder()
    .bucket("my-bucket")
    .policy(policyJson)
    .build();
s3Client.putBucketPolicy(policyRequest);

// Set CORS configuration
CORSRule corsRule = CORSRule.builder()
    .allowedMethods("GET", "PUT", "POST")
    .allowedOrigins("https://example.com")
    .allowedHeaders("*")
    .maxAgeSeconds(3000)
    .build();

PutBucketCorsRequest corsRequest = PutBucketCorsRequest.builder()
    .bucket("my-bucket")
    .corsConfiguration(CORSConfiguration.builder().corsRules(corsRule).build())
    .build();
s3Client.putBucketCors(corsRequest);
```

---

### 19. What is S3 versioning and lifecycle management?

**Answer:**

**S3 Versioning:**
Keeps multiple versions of an object in the same bucket. Protects against accidental deletion and allows you to retrieve previous versions.

**Lifecycle Management:**
Automates transitioning objects between storage classes or deleting them after a specified time to reduce costs.

**Benefits:**
- Version control for files
- Protection from accidental overwrites/deletes
- Automatic cost optimization
- Compliance and archival requirements

**Enable Versioning:**
```java
// Enable versioning
PutBucketVersioningRequest versioningRequest = PutBucketVersioningRequest.builder()
    .bucket("my-bucket")
    .versioningConfiguration(VersioningConfiguration.builder()
        .status(BucketVersioningStatus.ENABLED)
        .build())
    .build();
s3Client.putBucketVersioning(versioningRequest);
```

**Lifecycle Policy:**
```java
// Lifecycle rule: Move to IA after 30 days, Glacier after 90 days, delete after 365 days
LifecycleRule rule = LifecycleRule.builder()
    .id("archive-rule")
    .status(ExpirationStatus.ENABLED)
    .transitions(
        Transition.builder().days(30).storageClass(TransitionStorageClass.STANDARD_IA).build(),
        Transition.builder().days(90).storageClass(TransitionStorageClass.GLACIER).build()
    )
    .expiration(LifecycleExpiration.builder().days(365).build())
    .build();

PutBucketLifecycleConfigurationRequest lifecycleRequest = 
    PutBucketLifecycleConfigurationRequest.builder()
        .bucket("my-bucket")
        .lifecycleConfiguration(BucketLifecycleConfiguration.builder().rules(rule).build())
        .build();
s3Client.putBucketLifecycleConfiguration(lifecycleRequest);
```

---

### 20. How do you secure S3 buckets and prevent public access?

**Answer:**
Securing S3 buckets involves multiple layers: blocking public access, encryption, access policies, and monitoring.

**Security Best Practices:**
- **Block Public Access**: Enable at account and bucket level
- **Encryption**: Server-side encryption (SSE-S3, SSE-KMS)
- **IAM Policies**: Least privilege access
- **Bucket Policies**: Restrict by IP, VPC, or conditions
- **Access Logging**: Track all requests
- **MFA Delete**: Require MFA for object deletion

**Block Public Access:**
```java
// Block all public access
PublicAccessBlockConfiguration blockConfig = PublicAccessBlockConfiguration.builder()
    .blockPublicAcls(true)
    .ignorePublicAcls(true)
    .blockPublicPolicy(true)
    .restrictPublicBuckets(true)
    .build();

PutPublicAccessBlockRequest blockRequest = PutPublicAccessBlockRequest.builder()
    .bucket("my-bucket")
    .publicAccessBlockConfiguration(blockConfig)
    .build();
s3Client.putPublicAccessBlock(blockRequest);
```

**Enable Encryption:**
```java
// Server-side encryption
PutObjectRequest request = PutObjectRequest.builder()
    .bucket("my-bucket")
    .key("sensitive-data.txt")
    .serverSideEncryption(ServerSideEncryption.AES256)
    .build();
s3Client.putObject(request, RequestBody.fromString(data));
```

**Secure Bucket Policy (VPC Only):**
```json
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Effect": "Deny",
      "Principal": "*",
      "Action": "s3:*",
      "Resource": "arn:aws:s3:::my-bucket/*",
      "Condition": {
        "StringNotEquals": {
          "aws:SourceVpc": "vpc-12345678"
        }
      }
    }
  ]
}
```

---

## 4. Database Services (21-26)

### 21. What is Amazon RDS and which databases does it support?

**Answer:**
Amazon RDS (Relational Database Service) is a managed database service that makes it easy to set up, operate, and scale relational databases in the cloud. AWS handles routine tasks like provisioning, patching, backup, recovery, and scaling.

**Supported Database Engines:**
- **Amazon Aurora**: MySQL and PostgreSQL compatible
- **MySQL**: Community edition
- **PostgreSQL**: Open-source database
- **MariaDB**: MySQL fork
- **Oracle**: Enterprise database
- **Microsoft SQL Server**: Windows-based database

**Key Benefits:**
- Automated backups and snapshots
- Multi-AZ deployment for high availability
- Read replicas for scalability
- Automatic software patching
- Monitoring with CloudWatch

**Example:**
```java
import software.amazon.awssdk.services.rds.RdsClient;
import software.amazon.awssdk.services.rds.model.*;

RdsClient rds = RdsClient.create();

// Create RDS instance
CreateDbInstanceRequest request = CreateDbInstanceRequest.builder()
    .dbInstanceIdentifier("mydb-instance")
    .dbInstanceClass("db.t3.micro")
    .engine("mysql")
    .masterUsername("admin")
    .masterUserPassword("password123")
    .allocatedStorage(20)
    .build();

rds.createDBInstance(request);
```

---

### 22. How do you connect Spring Boot application to AWS RDS?

**Answer:**
Connecting Spring Boot to RDS involves configuring the database connection properties with the RDS endpoint, credentials, and JDBC driver.

**application.properties:**
```properties
spring.datasource.url=jdbc:mysql://mydb.abc123.us-east-1.rds.amazonaws.com:3306/mydb
spring.datasource.username=admin
spring.datasource.password=password123
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

**Maven Dependency:**
```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.33</version>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```

**Entity and Repository:**
```java
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    // getters and setters
}

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
```

---

### 23. What is the difference between RDS and DynamoDB?

**Answer:**

**RDS (Relational Database Service):**
- SQL-based relational database
- Fixed schema with tables, rows, columns
- ACID transactions
- Complex queries with JOINs
- Vertical scaling (increase instance size)
- Use case: Traditional applications, complex queries, reporting

**DynamoDB (NoSQL Database):**
- NoSQL key-value and document database
- Flexible schema (schemaless)
- Eventually consistent (or strongly consistent)
- Simple queries by primary key
- Horizontal scaling (automatic)
- Use case: High-traffic web apps, gaming, IoT, real-time applications

**Comparison Example:**
```java
// RDS - SQL queries
@Query("SELECT u FROM User u WHERE u.email = :email AND u.age > :age")
List<User> findByEmailAndAge(@Param("email") String email, @Param("age") int age);

// DynamoDB - Key-value access
DynamoDbClient dynamoDB = DynamoDbClient.create();
GetItemResponse response = dynamoDB.getItem(GetItemRequest.builder()
    .tableName("Users")
    .key(Map.of("userId", AttributeValue.builder().s("123").build()))
    .build());
```

---

### 24. Explain Multi-AZ deployment and Read Replicas in RDS

**Answer:**

**Multi-AZ Deployment:**
- Synchronous replication to standby instance in different AZ
- Automatic failover in case of primary failure (1-2 minutes)
- Used for high availability and disaster recovery
- Same endpoint, no application changes needed
- Slightly higher cost

**Read Replicas:**
- Asynchronous replication for read scalability
- Up to 5 read replicas per primary
- Can be in different regions
- Separate endpoints for each replica
- Used to offload read traffic from primary
- Can be promoted to standalone database

**Key Differences:**
- Multi-AZ: High availability (failover)
- Read Replicas: Performance (read scaling)

**Example:**
```java
// Create Multi-AZ RDS instance
CreateDbInstanceRequest multiAzRequest = CreateDbInstanceRequest.builder()
    .dbInstanceIdentifier("mydb-ha")
    .multiAZ(true)  // Enable Multi-AZ
    .engine("mysql")
    .dbInstanceClass("db.t3.micro")
    .build();

// Create Read Replica
CreateDbInstanceReadReplicaRequest replicaRequest = 
    CreateDbInstanceReadReplicaRequest.builder()
        .dbInstanceIdentifier("mydb-replica")
        .sourceDBInstanceIdentifier("mydb-ha")
        .build();

rds.createDBInstanceReadReplica(replicaRequest);
```

**Spring Boot Configuration:**
```properties
# Primary (write)
spring.datasource.url=jdbc:mysql://primary.rds.amazonaws.com:3306/mydb

# Read Replica (read-only)
spring.datasource.read-replica.url=jdbc:mysql://replica.rds.amazonaws.com:3306/mydb
```

---

### 25. What is Amazon Aurora and how is it different from RDS?

**Answer:**
Amazon Aurora is a MySQL and PostgreSQL-compatible relational database built for the cloud. It's part of RDS but offers superior performance and availability.

**Key Differences:**
- **Performance**: 5x faster than MySQL, 3x faster than PostgreSQL
- **Storage**: Auto-scales from 10GB to 128TB
- **Replication**: 6 copies across 3 AZs automatically
- **Failover**: Faster failover (< 30 seconds)
- **Backtrack**: Rewind database to previous point in time
- **Global Database**: Cross-region replication with < 1 second latency
- **Serverless**: Aurora Serverless auto-scales based on demand

**When to Use Aurora:**
- High-performance requirements
- Need for automatic scaling
- Global applications
- Mission-critical workloads

**Example:**
```java
// Create Aurora cluster
CreateDbClusterRequest clusterRequest = CreateDbClusterRequest.builder()
    .dbClusterIdentifier("aurora-cluster")
    .engine("aurora-mysql")
    .engineVersion("8.0.mysql_aurora.3.02.0")
    .masterUsername("admin")
    .masterUserPassword("password123")
    .build();

rds.createDBCluster(clusterRequest);

// Create Aurora instance in cluster
CreateDbInstanceRequest instanceRequest = CreateDbInstanceRequest.builder()
    .dbInstanceIdentifier("aurora-instance-1")
    .dbClusterIdentifier("aurora-cluster")
    .dbInstanceClass("db.t3.medium")
    .engine("aurora-mysql")
    .build();

rds.createDBInstance(instanceRequest);
```

**Spring Boot Connection:**
```properties
spring.datasource.url=jdbc:mysql://aurora-cluster.cluster-abc123.us-east-1.rds.amazonaws.com:3306/mydb
spring.datasource.username=admin
spring.datasource.password=password123
```

---

### 26. How do you perform database backup and restore in AWS?

**Answer:**
AWS provides automated backups and manual snapshots for RDS databases. Backups are stored in S3 and can be restored to any point in time.

**Backup Types:**
- **Automated Backups**: Daily full backup + transaction logs (1-35 days retention)
- **Manual Snapshots**: User-initiated, retained until manually deleted
- **Point-in-Time Recovery**: Restore to any second within retention period

**Backup Methods:**
1. Automated backups (enabled by default)
2. Manual DB snapshots
3. Export to S3 (for Aurora)

**Example:**
```java
import software.amazon.awssdk.services.rds.RdsClient;
import software.amazon.awssdk.services.rds.model.*;

RdsClient rds = RdsClient.create();

// Create manual snapshot
CreateDbSnapshotRequest snapshotRequest = CreateDbSnapshotRequest.builder()
    .dbSnapshotIdentifier("mydb-snapshot-2024")
    .dbInstanceIdentifier("mydb-instance")
    .build();
rds.createDBSnapshot(snapshotRequest);

// Restore from snapshot
RestoreDbInstanceFromDbSnapshotRequest restoreRequest = 
    RestoreDbInstanceFromDbSnapshotRequest.builder()
        .dbInstanceIdentifier("mydb-restored")
        .dbSnapshotIdentifier("mydb-snapshot-2024")
        .build();
rds.restoreDBInstanceFromDBSnapshot(restoreRequest);

// Point-in-time restore
RestoreDbInstanceToPointInTimeRequest pitRequest = 
    RestoreDbInstanceToPointInTimeRequest.builder()
        .sourceDBInstanceIdentifier("mydb-instance")
        .targetDBInstanceIdentifier("mydb-restored-pit")
        .restoreTime(Instant.parse("2024-01-15T10:30:00Z"))
        .build();
rds.restoreDBInstanceToPointInTime(pitRequest);
```

**CLI Commands:**
```bash
# Create snapshot
aws rds create-db-snapshot --db-instance-identifier mydb --db-snapshot-identifier mydb-backup

# List snapshots
aws rds describe-db-snapshots --db-instance-identifier mydb

# Restore from snapshot
aws rds restore-db-instance-from-db-snapshot \
  --db-instance-identifier mydb-restored \
  --db-snapshot-identifier mydb-backup
```

---

## 5. Networking & Content Delivery (27-30)

### 27. What is VPC and how do you configure it?

**Answer:**
VPC (Virtual Private Cloud) is a logically isolated virtual network in AWS where you can launch AWS resources. It gives you complete control over your network environment including IP ranges, subnets, route tables, and gateways.

**VPC Components:**
- **Subnets**: Public (internet-facing) and Private (internal)
- **Route Tables**: Control traffic routing
- **Internet Gateway**: Connect to internet
- **NAT Gateway**: Allow private subnets to access internet
- **Security Groups**: Instance-level firewall
- **Network ACLs**: Subnet-level firewall

**Example Configuration:**
```java
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.*;

Ec2Client ec2 = Ec2Client.create();

// Create VPC
CreateVpcRequest vpcRequest = CreateVpcRequest.builder()
    .cidrBlock("10.0.0.0/16")
    .build();
CreateVpcResponse vpcResponse = ec2.createVpc(vpcRequest);
String vpcId = vpcResponse.vpc().vpcId();

// Create public subnet
CreateSubnetRequest subnetRequest = CreateSubnetRequest.builder()
    .vpcId(vpcId)
    .cidrBlock("10.0.1.0/24")
    .availabilityZone("us-east-1a")
    .build();
ec2.createSubnet(subnetRequest);
```

**CLI Commands:**
```bash
# Create VPC
aws ec2 create-vpc --cidr-block 10.0.0.0/16

# Create subnet
aws ec2 create-subnet --vpc-id vpc-123 --cidr-block 10.0.1.0/24

# Create internet gateway
aws ec2 create-internet-gateway
aws ec2 attach-internet-gateway --vpc-id vpc-123 --internet-gateway-id igw-123
```

---

### 28. Explain Security Groups vs Network ACLs

**Answer:**

**Security Groups:**
- Instance-level firewall (attached to ENI)
- Stateful (return traffic automatically allowed)
- Only ALLOW rules (no DENY rules)
- Evaluates all rules before deciding
- Applied to EC2, RDS, Lambda, etc.

**Network ACLs:**
- Subnet-level firewall
- Stateless (return traffic must be explicitly allowed)
- Both ALLOW and DENY rules
- Rules processed in order (lowest number first)
- Applied to entire subnet

**Key Differences:**
| Feature | Security Group | Network ACL |
|---------|---------------|-------------|
| Level | Instance | Subnet |
| State | Stateful | Stateless |
| Rules | Allow only | Allow + Deny |
| Processing | All rules | Order matters |

**Example:**
```java
// Create Security Group
CreateSecurityGroupRequest sgRequest = CreateSecurityGroupRequest.builder()
    .groupName("web-sg")
    .description("Allow HTTP/HTTPS")
    .vpcId(vpcId)
    .build();
String sgId = ec2.createSecurityGroup(sgRequest).groupId();

// Add inbound rule (allow HTTP)
AuthorizeSecurityGroupIngressRequest ingressRequest = 
    AuthorizeSecurityGroupIngressRequest.builder()
        .groupId(sgId)
        .ipPermissions(IpPermission.builder()
            .ipProtocol("tcp")
            .fromPort(80)
            .toPort(80)
            .ipRanges(IpRange.builder().cidrIp("0.0.0.0/0").build())
            .build())
        .build();
ec2.authorizeSecurityGroupIngress(ingressRequest);

// Network ACL rule
CreateNetworkAclEntryRequest aclRequest = CreateNetworkAclEntryRequest.builder()
    .networkAclId("acl-123")
    .ruleNumber(100)
    .protocol("6")  // TCP
    .ruleAction(RuleAction.ALLOW)
    .cidrBlock("0.0.0.0/0")
    .portRange(PortRange.builder().from(80).to(80).build())
    .build();
ec2.createNetworkAclEntry(aclRequest);
```

---

### 29. What is the difference between Internet Gateway and NAT Gateway?

**Answer:**

**Internet Gateway (IGW):**
- Allows bidirectional internet access
- Used for public subnets
- Resources must have public IP
- Free (no additional cost)
- Horizontally scaled, redundant, highly available
- Use case: Web servers, public-facing applications

**NAT Gateway:**
- Allows outbound-only internet access
- Used for private subnets
- Resources use private IP
- Charged per hour + data processed
- Managed by AWS (high availability)
- Use case: Private instances downloading updates, accessing APIs

**Architecture:**
```
Public Subnet (with IGW):
EC2 (public IP) <-> Internet Gateway <-> Internet

Private Subnet (with NAT):
EC2 (private IP) -> NAT Gateway (in public subnet) -> Internet Gateway -> Internet
```

**Example:**
```java
// Create Internet Gateway
CreateInternetGatewayRequest igwRequest = CreateInternetGatewayRequest.builder().build();
String igwId = ec2.createInternetGateway(igwRequest).internetGateway().internetGatewayId();

// Attach to VPC
AttachInternetGatewayRequest attachRequest = AttachInternetGatewayRequest.builder()
    .vpcId(vpcId)
    .internetGatewayId(igwId)
    .build();
ec2.attachInternetGateway(attachRequest);

// Create NAT Gateway (requires Elastic IP)
AllocateAddressRequest eipRequest = AllocateAddressRequest.builder()
    .domain(DomainType.VPC)
    .build();
String allocationId = ec2.allocateAddress(eipRequest).allocationId();

CreateNatGatewayRequest natRequest = CreateNatGatewayRequest.builder()
    .subnetId(publicSubnetId)  // Must be in public subnet
    .allocationId(allocationId)
    .build();
ec2.createNatGateway(natRequest);
```

---

### 30. What is Amazon Route 53 and how does it work?

**Answer:**
Amazon Route 53 is a scalable DNS (Domain Name System) web service that routes end users to applications by translating domain names to IP addresses. It also provides domain registration and health checking.

**Key Features:**
- **DNS Management**: Host and manage domain names
- **Traffic Routing**: Multiple routing policies
- **Health Checks**: Monitor endpoint health
- **Domain Registration**: Buy and manage domains
- **100% SLA**: Highly available and reliable

**Routing Policies:**
- **Simple**: Single resource (one IP)
- **Weighted**: Distribute traffic by percentage
- **Latency**: Route to lowest latency region
- **Failover**: Active-passive failover
- **Geolocation**: Route based on user location
- **Geoproximity**: Route based on resource location
- **Multi-value**: Return multiple IPs (simple load balancing)

**Example:**
```java
import software.amazon.awssdk.services.route53.Route53Client;
import software.amazon.awssdk.services.route53.model.*;

Route53Client route53 = Route53Client.create();

// Create hosted zone
CreateHostedZoneRequest zoneRequest = CreateHostedZoneRequest.builder()
    .name("example.com")
    .callerReference(String.valueOf(System.currentTimeMillis()))
    .build();
String hostedZoneId = route53.createHostedZone(zoneRequest).hostedZone().id();

// Create A record
ResourceRecordSet recordSet = ResourceRecordSet.builder()
    .name("www.example.com")
    .type(RRType.A)
    .ttl(300L)
    .resourceRecords(ResourceRecord.builder().value("192.0.2.1").build())
    .build();

Change change = Change.builder()
    .action(ChangeAction.CREATE)
    .resourceRecordSet(recordSet)
    .build();

ChangeResourceRecordSetsRequest recordRequest = 
    ChangeResourceRecordSetsRequest.builder()
        .hostedZoneId(hostedZoneId)
        .changeBatch(ChangeBatch.builder().changes(change).build())
        .build();

route53.changeResourceRecordSets(recordRequest);
```

**CLI Commands:**
```bash
# Create hosted zone
aws route53 create-hosted-zone --name example.com --caller-reference 2024-01-15

# List hosted zones
aws route53 list-hosted-zones

# Create DNS record
aws route53 change-resource-record-sets --hosted-zone-id Z123 --change-batch file://record.json
```

---

## 6. Containerization & Orchestration (31-33)

### 31. What is Docker and how do you containerize a Java application?

**Answer:**
Docker is a platform that packages applications and their dependencies into containers - lightweight, portable, and self-sufficient units that run consistently across different environments.

**Key Concepts:**
- **Image**: Blueprint for containers (read-only template)
- **Container**: Running instance of an image
- **Dockerfile**: Instructions to build an image
- **Registry**: Storage for images (Docker Hub, ECR)

**Benefits:**
- Consistent environments (dev, test, prod)
- Faster deployment
- Resource efficiency
- Easy scaling

**Dockerfile for Spring Boot:**
```dockerfile
FROM openjdk:17-slim
WORKDIR /app
COPY target/myapp.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

**Build and Run:**
```bash
# Build image
docker build -t myapp:1.0 .

# Run container
docker run -p 8080:8080 myapp:1.0

# Push to ECR
aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin <account-id>.dkr.ecr.us-east-1.amazonaws.com
docker tag myapp:1.0 <account-id>.dkr.ecr.us-east-1.amazonaws.com/myapp:1.0
docker push <account-id>.dkr.ecr.us-east-1.amazonaws.com/myapp:1.0
```

---

### 32. What is Amazon ECS and how is it different from EKS?

**Answer:**

**ECS (Elastic Container Service):**
- AWS-native container orchestration
- Simpler to set up and manage
- Two launch types: EC2 and Fargate (serverless)
- Integrated with AWS services
- No additional cost (pay for resources only)
- Use case: AWS-centric applications, simpler deployments

**EKS (Elastic Kubernetes Service):**
- Managed Kubernetes service
- Industry-standard orchestration
- More complex but more powerful
- Portable across cloud providers
- Additional cost for control plane
- Use case: Multi-cloud, complex orchestration, Kubernetes expertise

**Key Differences:**
| Feature | ECS | EKS |
|---------|-----|-----|
| Orchestration | AWS proprietary | Kubernetes |
| Complexity | Simple | Complex |
| Portability | AWS only | Multi-cloud |
| Cost | Free (resources only) | Control plane fee |
| Learning Curve | Easy | Steep |

**ECS Task Definition:**
```json
{
  "family": "myapp-task",
  "containerDefinitions": [{
    "name": "myapp",
    "image": "<account-id>.dkr.ecr.us-east-1.amazonaws.com/myapp:1.0",
    "memory": 512,
    "cpu": 256,
    "portMappings": [{
      "containerPort": 8080,
      "protocol": "tcp"
    }]
  }]
}
```

**EKS Deployment:**
```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: myapp
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
        image: <account-id>.dkr.ecr.us-east-1.amazonaws.com/myapp:1.0
        ports:
        - containerPort: 8080
```

---

### 33. How do you deploy a microservices application using ECS or EKS?

**Answer:**
Deploying microservices involves containerizing each service, creating task definitions/deployments, setting up service discovery, and configuring load balancing.

**ECS Deployment:**
```bash
# Create ECS cluster
aws ecs create-cluster --cluster-name microservices-cluster

# Register task definition
aws ecs register-task-definition --cli-input-json file://task-definition.json

# Create service with load balancer
aws ecs create-service \
  --cluster microservices-cluster \
  --service-name user-service \
  --task-definition user-service:1 \
  --desired-count 2 \
  --launch-type FARGATE \
  --network-configuration "awsvpcConfiguration={subnets=[subnet-123],securityGroups=[sg-123],assignPublicIp=ENABLED}" \
  --load-balancers "targetGroupArn=arn:aws:elasticloadbalancing:...,containerName=user-service,containerPort=8080"
```

**EKS Deployment:**
```bash
# Create EKS cluster
eksctl create cluster --name microservices-cluster --region us-east-1

# Deploy services
kubectl apply -f user-service-deployment.yaml
kubectl apply -f order-service-deployment.yaml

# Expose services
kubectl expose deployment user-service --type=LoadBalancer --port=80 --target-port=8080
```

**Service Discovery (ECS):**
```java
// application.properties
order.service.url=http://order-service.local:8080

// AWS Cloud Map integration
aws servicediscovery create-service \
  --name order-service \
  --dns-config "NamespaceId=ns-123,DnsRecords=[{Type=A,TTL=60}]"
```

---

## 7. CI/CD & DevOps (34-36)

### 34. What is AWS CodePipeline and how do you set up CI/CD?

**Answer:**
AWS CodePipeline is a fully managed continuous delivery service that automates build, test, and deployment phases of your release process.

**Pipeline Stages:**
1. **Source**: CodeCommit, GitHub, S3
2. **Build**: CodeBuild (compile, test, package)
3. **Test**: Run automated tests
4. **Deploy**: CodeDeploy, ECS, Lambda, Elastic Beanstalk

**Key Benefits:**
- Automated releases
- Fast feedback
- Consistent deployments
- Integration with AWS services

**Pipeline Configuration (buildspec.yml):**
```yaml
version: 0.2
phases:
  pre_build:
    commands:
      - echo Logging in to Amazon ECR...
      - aws ecr get-login-password --region $AWS_DEFAULT_REGION | docker login --username AWS --password-stdin $AWS_ACCOUNT_ID.dkr.ecr.$AWS_DEFAULT_REGION.amazonaws.com
  build:
    commands:
      - echo Build started on `date`
      - mvn clean package
      - docker build -t myapp .
      - docker tag myapp:latest $AWS_ACCOUNT_ID.dkr.ecr.$AWS_DEFAULT_REGION.amazonaws.com/myapp:latest
  post_build:
    commands:
      - docker push $AWS_ACCOUNT_ID.dkr.ecr.$AWS_DEFAULT_REGION.amazonaws.com/myapp:latest
artifacts:
  files:
    - target/*.jar
```

**Create Pipeline:**
```bash
aws codepipeline create-pipeline --cli-input-json file://pipeline.json
```

---

### 35. Explain AWS CodeBuild, CodeDeploy, and CodeCommit

**Answer:**

**CodeCommit:**
- Managed Git repository service
- Secure, scalable source control
- Integrated with IAM
- Alternative to GitHub/GitLab

**CodeBuild:**
- Fully managed build service
- Compiles code, runs tests, produces artifacts
- Pay per build minute
- Supports multiple languages (Java, Node.js, Python)

**CodeDeploy:**
- Automated deployment service
- Deploys to EC2, Lambda, ECS, on-premises
- Blue/green and rolling deployments
- Automatic rollback on failure

**Usage Example:**
```bash
# CodeCommit - Clone repository
git clone https://git-codecommit.us-east-1.amazonaws.com/v1/repos/myapp

# CodeBuild - Start build
aws codebuild start-build --project-name myapp-build

# CodeDeploy - Create deployment
aws deploy create-deployment \
  --application-name myapp \
  --deployment-group-name production \
  --s3-location bucket=mybucket,key=myapp.zip,bundleType=zip
```

**appspec.yml (CodeDeploy):**
```yaml
version: 0.0
os: linux
files:
  - source: /
    destination: /home/ec2-user/app
hooks:
  ApplicationStop:
    - location: scripts/stop_server.sh
  ApplicationStart:
    - location: scripts/start_server.sh
```

---

### 36. How do you automate deployment of Spring Boot application using AWS services?

**Answer:**
Automating Spring Boot deployment involves setting up a complete CI/CD pipeline using CodePipeline, CodeBuild, and CodeDeploy or ECS.

**Complete Pipeline:**
1. Developer pushes code to CodeCommit/GitHub
2. CodePipeline triggers automatically
3. CodeBuild compiles and packages JAR
4. CodeDeploy deploys to EC2/ECS
5. CloudWatch monitors application

**buildspec.yml:**
```yaml
version: 0.2
phases:
  install:
    runtime-versions:
      java: corretto17
  build:
    commands:
      - mvn clean package -DskipTests
artifacts:
  files:
    - target/myapp.jar
    - appspec.yml
    - scripts/**
```

**appspec.yml:**
```yaml
version: 0.0
os: linux
files:
  - source: target/myapp.jar
    destination: /home/ec2-user
hooks:
  ApplicationStop:
    - location: scripts/stop.sh
  ApplicationStart:
    - location: scripts/start.sh
```

**start.sh:**
```bash
#!/bin/bash
cd /home/ec2-user
nohup java -jar myapp.jar > app.log 2>&1 &
```

**Pipeline JSON:**
```json
{
  "pipeline": {
    "name": "SpringBootPipeline",
    "roleArn": "arn:aws:iam::123456789012:role/CodePipelineRole",
    "stages": [
      {
        "name": "Source",
        "actions": [{
          "name": "SourceAction",
          "actionTypeId": {
            "category": "Source",
            "owner": "AWS",
            "provider": "CodeCommit",
            "version": "1"
          },
          "configuration": {
            "RepositoryName": "myapp",
            "BranchName": "main"
          }
        }]
      },
      {
        "name": "Build",
        "actions": [{
          "name": "BuildAction",
          "actionTypeId": {
            "category": "Build",
            "owner": "AWS",
            "provider": "CodeBuild",
            "version": "1"
          },
          "configuration": {
            "ProjectName": "myapp-build"
          }
        }]
      },
      {
        "name": "Deploy",
        "actions": [{
          "name": "DeployAction",
          "actionTypeId": {
            "category": "Deploy",
            "owner": "AWS",
            "provider": "CodeDeploy",
            "version": "1"
          },
          "configuration": {
            "ApplicationName": "myapp",
            "DeploymentGroupName": "production"
          }
        }]
      }
    ]
  }
}
```

---

## 8. Monitoring & Logging (37-39)

### 37. What is Amazon CloudWatch and how do you monitor applications?

**Answer:**
Amazon CloudWatch is a monitoring and observability service that collects metrics, logs, and events from AWS resources and applications.

**Key Features:**
- **Metrics**: CPU, memory, disk, custom metrics
- **Logs**: Centralized log management
- **Alarms**: Automated notifications
- **Dashboards**: Visual monitoring
- **Events**: Respond to state changes

**Monitoring Spring Boot:**
```java
// Add CloudWatch dependency
// Maven
<dependency>
    <groupId>io.micrometer</groupId>
    <artifactId>micrometer-registry-cloudwatch2</artifactId>
</dependency>

// application.properties
management.metrics.export.cloudwatch.namespace=MyApp
management.metrics.export.cloudwatch.enabled=true
management.endpoints.web.exposure.include=health,metrics,prometheus

// Custom metrics
@Service
public class OrderService {
    private final MeterRegistry meterRegistry;
    
    public OrderService(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }
    
    public void createOrder(Order order) {
        meterRegistry.counter("orders.created").increment();
        // business logic
    }
}
```

**CloudWatch Logs:**
```java
// logback-spring.xml
<configuration>
    <appender name="CLOUDWATCH" class="ca.pjer.logback.AwsLogsAppender">
        <logGroupName>/aws/springboot/myapp</logGroupName>
        <logStreamName>instance-1</logStreamName>
        <layout>
            <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
        </layout>
    </appender>
    <root level="INFO">
        <appender-ref ref="CLOUDWATCH" />
    </root>
</configuration>
```

**CLI Commands:**
```bash
# View metrics
aws cloudwatch get-metric-statistics \
  --namespace AWS/EC2 \
  --metric-name CPUUtilization \
  --dimensions Name=InstanceId,Value=i-1234567890abcdef0 \
  --start-time 2024-01-15T00:00:00Z \
  --end-time 2024-01-15T23:59:59Z \
  --period 3600 \
  --statistics Average

# View logs
aws logs tail /aws/springboot/myapp --follow
```

---

### 38. How do you set up CloudWatch alarms and notifications?

**Answer:**
CloudWatch Alarms monitor metrics and send notifications via SNS when thresholds are breached. You can trigger automated actions like auto-scaling or Lambda functions.

**Alarm States:**
- **OK**: Metric within threshold
- **ALARM**: Metric breached threshold
- **INSUFFICIENT_DATA**: Not enough data

**Create Alarm:**
```java
import software.amazon.awssdk.services.cloudwatch.CloudWatchClient;
import software.amazon.awssdk.services.cloudwatch.model.*;

CloudWatchClient cloudWatch = CloudWatchClient.create();

// Create alarm for high CPU
PutMetricAlarmRequest alarmRequest = PutMetricAlarmRequest.builder()
    .alarmName("HighCPUAlarm")
    .comparisonOperator(ComparisonOperator.GREATER_THAN_THRESHOLD)
    .evaluationPeriods(2)
    .metricName("CPUUtilization")
    .namespace("AWS/EC2")
    .period(60)
    .statistic(Statistic.AVERAGE)
    .threshold(80.0)
    .actionsEnabled(true)
    .alarmActions("arn:aws:sns:us-east-1:123456789012:AlertTopic")
    .dimensions(Dimension.builder().name("InstanceId").value("i-123456").build())
    .build();

cloudWatch.putMetricAlarm(alarmRequest);
```

**CLI Commands:**
```bash
# Create SNS topic
aws sns create-topic --name AlertTopic

# Subscribe email to topic
aws sns subscribe \
  --topic-arn arn:aws:sns:us-east-1:123456789012:AlertTopic \
  --protocol email \
  --notification-endpoint admin@example.com

# Create alarm
aws cloudwatch put-metric-alarm \
  --alarm-name HighCPU \
  --alarm-description "Alert when CPU exceeds 80%" \
  --metric-name CPUUtilization \
  --namespace AWS/EC2 \
  --statistic Average \
  --period 300 \
  --threshold 80 \
  --comparison-operator GreaterThanThreshold \
  --evaluation-periods 2 \
  --alarm-actions arn:aws:sns:us-east-1:123456789012:AlertTopic
```

---

### 39. What is AWS CloudTrail and how is it different from CloudWatch?

**Answer:**

**CloudTrail:**
- Audit and compliance service
- Records API calls and user activity
- "Who did what, when, and from where"
- Governance, compliance, risk auditing
- Logs stored in S3

**CloudWatch:**
- Monitoring and observability service
- Tracks performance metrics and logs
- "How is my application performing"
- Operational monitoring
- Real-time metrics and alarms

**Key Differences:**
| Feature | CloudTrail | CloudWatch |
|---------|-----------|------------|
| Purpose | Audit/Compliance | Performance Monitoring |
| Data | API calls, user actions | Metrics, logs, events |
| Use Case | Security, compliance | Operations, troubleshooting |
| Storage | S3 | CloudWatch Logs |
| Focus | Who/What/When | How/Performance |

**CloudTrail Example:**
```java
import software.amazon.awssdk.services.cloudtrail.CloudTrailClient;
import software.amazon.awssdk.services.cloudtrail.model.*;

CloudTrailClient cloudTrail = CloudTrailClient.create();

// Create trail
CreateTrailRequest trailRequest = CreateTrailRequest.builder()
    .name("MyTrail")
    .s3BucketName("my-cloudtrail-bucket")
    .isMultiRegionTrail(true)
    .build();

cloudTrail.createTrail(trailRequest);

// Start logging
StartLoggingRequest startRequest = StartLoggingRequest.builder()
    .name("MyTrail")
    .build();

cloudTrail.startLogging(startRequest);
```

**Query CloudTrail Logs:**
```bash
# Lookup events
aws cloudtrail lookup-events \
  --lookup-attributes AttributeKey=EventName,AttributeValue=CreateBucket \
  --max-results 10
```

---

## 9. Architecture & Best Practices (40)

### 40. How would you design a highly available and scalable full-stack application on AWS?

**Answer:**
A highly available and scalable architecture uses multiple AZs, auto-scaling, load balancing, managed services, and follows AWS Well-Architected Framework principles.

**Architecture Components:**

**1. Frontend (React):**
- Host on S3 + CloudFront (CDN)
- Route 53 for DNS
- SSL/TLS with ACM

**2. Backend (Spring Boot):**
- ECS Fargate or EKS for containers
- Application Load Balancer
- Auto Scaling Group (2+ AZs)
- API Gateway for REST APIs

**3. Database:**
- RDS Multi-AZ or Aurora (primary)
- Read Replicas for read scaling
- ElastiCache (Redis) for caching
- DynamoDB for NoSQL needs

**4. Storage:**
- S3 for file storage
- CloudFront for content delivery

**5. Security:**
- VPC with public/private subnets
- Security Groups and NACLs
- IAM roles (no hardcoded credentials)
- Secrets Manager for sensitive data
- WAF for application protection

**6. Monitoring & Logging:**
- CloudWatch for metrics and logs
- CloudTrail for audit logs
- X-Ray for distributed tracing

**7. CI/CD:**
- CodePipeline for automation
- CodeBuild for builds
- CodeDeploy for deployments

**Architecture Diagram (Text):**
```
Users
  |
  v
Route 53 (DNS)
  |
  v
CloudFront (CDN) --> S3 (React App)
  |
  v
Application Load Balancer (Multi-AZ)
  |
  +-- ECS/Fargate (AZ-1) --> RDS Primary (AZ-1)
  |                      --> ElastiCache
  |                      --> S3
  |
  +-- ECS/Fargate (AZ-2) --> RDS Standby (AZ-2)
                         --> Read Replica
```

**Infrastructure as Code (CloudFormation snippet):**
```yaml
Resources:
  # VPC
  VPC:
    Type: AWS::EC2::VPC
    Properties:
      CidrBlock: 10.0.0.0/16
      EnableDnsHostnames: true
  
  # Load Balancer
  ALB:
    Type: AWS::ElasticLoadBalancingV2::LoadBalancer
    Properties:
      Subnets:
        - !Ref PublicSubnet1
        - !Ref PublicSubnet2
      SecurityGroups:
        - !Ref ALBSecurityGroup
  
  # ECS Cluster
  ECSCluster:
    Type: AWS::ECS::Cluster
    Properties:
      ClusterName: production-cluster
  
  # RDS Multi-AZ
  Database:
    Type: AWS::RDS::DBInstance
    Properties:
      Engine: mysql
      MultiAZ: true
      DBInstanceClass: db.t3.medium
      AllocatedStorage: 100
  
  # Auto Scaling
  AutoScalingTarget:
    Type: AWS::ApplicationAutoScaling::ScalableTarget
    Properties:
      MaxCapacity: 10
      MinCapacity: 2
      ResourceId: !Sub service/${ECSCluster}/${ECSService}
      ScalableDimension: ecs:service:DesiredCount
      ServiceNamespace: ecs
```


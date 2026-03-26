## 1ï¸âƒ£ Basic AWS Questions

### 1. What is Amazon Web Services?

**Answer:**

**Amazon Web Services**, or AWS, is a cloud computing platform provided by **Amazon**.
It offers services like computing power, storage, databases, networking, and AI over the internet.

Instead of buying and maintaining physical servers, companies can rent resources from AWS and pay only for what they use.

---

### 2. What are the main advantages of AWS cloud?

**Answer:**

Some main advantages of AWS are:

* **Cost-effective** â€“ Pay only for the resources you use.
* **Scalability** â€“ Easily increase or decrease resources based on demand.
* **High availability** â€“ Services run across multiple data centers.
* **Security** â€“ Provides strong security features and compliance.
* **Global infrastructure** â€“ Services are available in many locations worldwide.

---

### 3. What are Regions and Availability Zones in AWS?

**Answer:**

In **Amazon Web Services**, a **Region** is a geographical area where AWS has data centers.

Each region contains multiple **Availability Zones**, which are isolated data centers with separate power, networking, and infrastructure.

This design helps improve **fault tolerance, reliability, and high availability**.

---

### 4. What are the cloud service models (IaaS, PaaS, SaaS)?

**Answer:**

Cloud services are mainly divided into three models:

* **IaaS (Infrastructure as a Service)** â€“ Provides virtual servers, storage, and networking. Example: **Amazon EC2**.
* **PaaS (Platform as a Service)** â€“ Provides a platform to build and deploy applications without managing infrastructure. Example: **AWS Elastic Beanstalk**.
* **SaaS (Software as a Service)** â€“ Software applications delivered over the internet. Example: **Google Workspace**.

---

### 5. What is Elasticity and Scalability in cloud computing?

**Answer:**

**Scalability** means increasing or decreasing resources to handle workload changes.

**Elasticity** means automatically adjusting resources in real time based on demand.

In **Amazon Web Services**, services can scale automatically so applications can handle high traffic without downtime.

---

### 6. What is API Gateway?

**Answer:**

**Amazon API Gateway** is a service used to create, publish, and manage APIs. It acts as an entry point where clients send requests, and it forwards them to backend services like Lambda or EC2.

**Example:** Client â†’ API Gateway â†’ Lambda â†’ Response.

---

### 7. What is AWS Elastic Beanstalk?

**Answer:**

**AWS Elastic Beanstalk** is a platform service used to deploy and manage applications without worrying about infrastructure.

You upload code, and Beanstalk automatically handles:

* EC2
* Load balancer
* Scaling
* Monitoring

---

### 8. What is AWS Fargate?

**Answer:**

**AWS Fargate** is a serverless compute engine for containers used with ECS or EKS. You run containers without managing servers.

**Example:** Run Docker containers without provisioning EC2 instances.

---

### 9. What is a Route Table?

**Answer:**

A **Route Table** controls how network traffic moves inside a VPC.

It defines rules like:

* Where traffic should go
* Whether traffic goes to Internet Gateway, NAT Gateway, or another subnet.

---

### 10. What is an AWS Load Balancer?

**Answer:**

**Elastic Load Balancing** distributes incoming traffic across multiple servers to improve availability and performance.

Types:

* Application Load Balancer (ALB)
* Network Load Balancer (NLB)
* Classic Load Balancer

**Example:** User requests â†’ Load Balancer â†’ Multiple EC2 instances.

---

### 11. Difference Between Lambda and EC2?

**Answer:**

| Lambda                       | EC2                    |
| ---------------------------- | ---------------------- |
| Serverless                   | Server-based           |
| No infrastructure management | Need to manage servers |
| Event-driven                 | Continuous running     |
| Auto scaling                 | Manual or auto scaling |

---

### 12. How API Gateway and Lambda work together?

**Answer:**

Flow:
Client â†’ API Gateway â†’ Lambda â†’ Response

API Gateway receives the request and triggers Lambda to process the request.


## ðŸŽ¯ Top 10 AWS Services â€” Interview Explanation with Code

### 1. ðŸ–¥ï¸ Amazon EC2 â€” Virtual Servers

**What it is:**
EC2 (Elastic Compute Cloud) provides resizable virtual machines in the cloud. You choose OS, CPU, RAM, and storage.

**Interview Answer:**
> EC2 is a virtual server on AWS. I use it to host Java Spring Boot applications. It supports auto-scaling and load balancing.

**Steps:**
1. Go to AWS Console â†’ EC2 â†’ Launch Instance
2. Choose AMI (Amazon Linux / Ubuntu)
3. Choose instance type (t2.micro for free tier)
4. Configure security group (open port 8080)
5. Launch and SSH into the server

```bash
# SSH into EC2
ssh -i my-key.pem ec2-user@<EC2-PUBLIC-IP>

# Install Java
sudo yum install java-17 -y

# Run Spring Boot JAR
java -jar myapp.jar
```

---

### 2. ðŸ“¦ Amazon S3 â€” Object Storage

**What it is:**
S3 (Simple Storage Service) stores files, images, backups, and static websites. Highly durable (99.999999999%).

**Interview Answer:**
> I use S3 to store user-uploaded files and serve static React frontends. It integrates with CloudFront for CDN.

**Steps:**
1. Go to S3 â†’ Create Bucket
2. Upload files
3. Set bucket policy for public access (if static site)
4. Enable static website hosting

```java
// Spring Boot â€” Upload file to S3
@Autowired
AmazonS3 s3Client;

public String uploadFile(MultipartFile file) throws IOException {
    String fileName = file.getOriginalFilename();
    s3Client.putObject("my-bucket", fileName, file.getInputStream(), new ObjectMetadata());
    return "https://my-bucket.s3.amazonaws.com/" + fileName;
}
```

```bash
# AWS CLI â€” Upload file
aws s3 cp myfile.txt s3://my-bucket/
```

---

### 3. ðŸ—„ï¸ Amazon RDS â€” Managed Database

**What it is:**
RDS (Relational Database Service) is a managed database service supporting MySQL, PostgreSQL, Oracle, SQL Server.

**Interview Answer:**
> I use RDS with MySQL for production databases. AWS handles backups, patching, and multi-AZ failover automatically.

**Steps:**
1. Go to RDS â†’ Create Database
2. Choose MySQL / PostgreSQL
3. Set DB name, username, password
4. Configure VPC and security group (port 3306)
5. Connect from Spring Boot

```yaml
# application.yml â€” Spring Boot RDS connection
spring:
  datasource:
    url: jdbc:mysql://<RDS-ENDPOINT>:3306/mydb
    username: admin
    password: <password>
    driver-class-name: com.mysql.cj.jdbc.Driver
```

---

### 4. âš¡ AWS Lambda â€” Serverless Functions

**What it is:**
Lambda runs code without provisioning servers. Triggered by events (API Gateway, S3, SQS). Pay per execution.

**Interview Answer:**
> I use Lambda for event-driven tasks like processing S3 file uploads or handling API requests. No server management needed.

**Steps:**
1. Go to Lambda â†’ Create Function
2. Choose runtime (Java 17 / Node.js)
3. Write handler code
4. Set trigger (API Gateway / S3)
5. Deploy

```java
// Java Lambda Handler
public class MyHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent event, Context context) {
        return new APIGatewayProxyResponseEvent()
            .withStatusCode(200)
            .withBody("Hello from Lambda!");
    }
}
```

---

### 5. ðŸŒ Amazon API Gateway â€” REST API Manager

**What it is:**
API Gateway creates, publishes, and secures REST/HTTP APIs. Acts as the front door to Lambda or EC2.

**Interview Answer:**
> I use API Gateway to expose Lambda functions as REST endpoints. It handles throttling, auth, and CORS.

**Steps:**
1. Go to API Gateway â†’ Create API â†’ REST API
2. Create Resource (e.g., `/users`)
3. Create Method (GET, POST)
4. Integrate with Lambda
5. Deploy to a Stage (dev/prod)

```
Flow:
Client â†’ https://api.execute-api.us-east-1.amazonaws.com/prod/users
       â†’ API Gateway
       â†’ Lambda (MyHandler)
       â†’ Response
```

```bash
# Test API
curl -X GET https://<api-id>.execute-api.us-east-1.amazonaws.com/prod/users
```

---

### 6. ðŸš€ Amazon CloudFront â€” CDN

**What it is:**
CloudFront is a Content Delivery Network that caches content at edge locations worldwide for fast delivery.

**Interview Answer:**
> I use CloudFront in front of S3 to serve React static files globally with low latency and HTTPS support.

**Steps:**
1. Go to CloudFront â†’ Create Distribution
2. Set Origin = S3 bucket or EC2
3. Configure cache behavior
4. Deploy â€” get a CloudFront URL

```
Flow:
User (India) â†’ CloudFront Edge (Mumbai) â†’ Cache Hit â†’ Fast Response
User (India) â†’ CloudFront Edge (Mumbai) â†’ Cache Miss â†’ Fetch from S3 (US)
```

---

### 7. ðŸ³ Amazon ECS / â˜¸ï¸ EKS â€” Container Services

**What it is:**
- ECS = Elastic Container Service (AWS-native Docker orchestration)
- EKS = Elastic Kubernetes Service (managed Kubernetes)

**Interview Answer:**
> I use ECS with Fargate to run Docker containers without managing EC2. For complex microservices, EKS with Kubernetes is preferred.

**Steps (ECS):**
1. Create Docker image â†’ Push to ECR
2. Create ECS Cluster
3. Create Task Definition (Docker config)
4. Create Service â†’ Run tasks

```dockerfile
# Dockerfile â€” Spring Boot
FROM openjdk:17
COPY target/myapp.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

```bash
# Push image to ECR
aws ecr get-login-password | docker login --username AWS --password-stdin <account>.dkr.ecr.us-east-1.amazonaws.com
docker build -t myapp .
docker tag myapp:latest <account>.dkr.ecr.us-east-1.amazonaws.com/myapp:latest
docker push <account>.dkr.ecr.us-east-1.amazonaws.com/myapp:latest
```

---

### 8. ðŸ” AWS IAM â€” Identity & Access Management

**What it is:**
IAM manages users, groups, roles, and permissions. Controls who can access what in AWS.

**Interview Answer:**
> I use IAM roles to give EC2 or Lambda permission to access S3 or RDS without hardcoding credentials.

**Steps:**
1. Go to IAM â†’ Create Role
2. Choose trusted entity (EC2 / Lambda)
3. Attach policy (e.g., AmazonS3FullAccess)
4. Assign role to EC2 or Lambda

```json
// IAM Policy â€” Allow S3 read access
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Effect": "Allow",
      "Action": ["s3:GetObject", "s3:PutObject"],
      "Resource": "arn:aws:s3:::my-bucket/*"
    }
  ]
}
```

---

### 9. ðŸ“Š Amazon CloudWatch â€” Monitoring & Logs

**What it is:**
CloudWatch collects logs, metrics, and events. Set alarms to notify when something goes wrong.

**Interview Answer:**
> I use CloudWatch to monitor Lambda execution errors, EC2 CPU usage, and set alarms for auto-scaling triggers.

**Steps:**
1. Go to CloudWatch â†’ Log Groups (auto-created for Lambda)
2. Create Metric Filter
3. Create Alarm (e.g., CPU > 80%)
4. Set SNS notification

```java
// Spring Boot â€” Send custom metric to CloudWatch
@Autowired
AmazonCloudWatch cloudWatch;

public void sendMetric(String metricName, double value) {
    cloudWatch.putMetricData(new PutMetricDataRequest()
        .withNamespace("MyApp")
        .withMetricData(new MetricDatum()
            .withMetricName(metricName)
            .withValue(value)
            .withUnit(StandardUnit.Count)));
}
```

---

### 10. âš™ï¸ AWS Elastic Beanstalk â€” Easy App Deployment

**What it is:**
Elastic Beanstalk deploys and manages applications automatically. You just upload code â€” AWS handles EC2, load balancer, scaling.

**Interview Answer:**
> I use Elastic Beanstalk to deploy Spring Boot apps quickly. It provisions EC2, sets up load balancing, and handles scaling automatically.

**Steps:**
1. Build JAR: `mvn clean package`
2. Go to Elastic Beanstalk â†’ Create Application
3. Choose platform: Java
4. Upload JAR file
5. Beanstalk auto-provisions EC2, ELB, Auto Scaling

```bash
# Deploy using EB CLI
eb init my-app --platform java --region us-east-1
eb create my-env
eb deploy
eb open
```

```yaml
# .elasticbeanstalk/config.yml
branch-defaults:
  main:
    environment: my-env
global:
  application_name: my-app
  default_platform: Java 17
  default_region: us-east-1
```



---


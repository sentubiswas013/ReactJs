# AWS Interview Questions - Answers (Questions 1-15)

## Basic AWS Concepts

### 1. What is AWS and why is it used?

**Answer:** AWS (Amazon Web Services) is a cloud computing platform that provides on-demand computing resources like servers, storage, databases, and networking over the internet. It's used because you only pay for what you use, you can scale resources up or down instantly, and you don't need to maintain physical hardware. Companies use AWS to reduce infrastructure costs, deploy applications faster, and handle traffic spikes automatically.

```javascript
// Example: Connecting to AWS S3 from Node.js
const AWS = require('aws-sdk');
const s3 = new AWS.S3({
  region: 'us-east-1',
  accessKeyId: process.env.AWS_ACCESS_KEY,
  secretAccessKey: process.env.AWS_SECRET_KEY
});
```

---

### 2. What are the main AWS services you have used?

**Answer:** The main AWS services commonly used are EC2 for virtual servers, S3 for file storage, RDS for managed databases, Lambda for serverless functions, VPC for networking, IAM for security and access control, CloudWatch for monitoring, and Elastic Beanstalk for easy application deployment. Each service solves a specific problem in building cloud applications.

```yaml
# Example: Common AWS services in a typical application
Services:
  - EC2: Host application servers
  - S3: Store images, videos, backups
  - RDS: MySQL/PostgreSQL database
  - Lambda: Background jobs, API endpoints
  - CloudFront: CDN for fast content delivery
```

---

### 3. What is the difference between **EC2 and S3**?

**Answer:** EC2 is a virtual server where you run your applications - it's like renting a computer in the cloud. S3 is object storage for files like images, videos, and backups - it's like a massive hard drive. EC2 runs code and processes data, while S3 just stores and retrieves files. You pay EC2 by the hour for compute power, and S3 by the gigabyte for storage.

```python
# EC2 - Running application code
# (Runs on EC2 instance)
from flask import Flask
app = Flask(__name__)

@app.route('/')
def home():
    return "Running on EC2"

# S3 - Storing files
import boto3
s3 = boto3.client('s3')
s3.upload_file('photo.jpg', 'my-bucket', 'photo.jpg')
```

---

### 4. What is **IAM** and why is it important?

**Answer:** IAM (Identity and Access Management) controls who can access your AWS resources and what they can do. It's important because it prevents unauthorized access and follows the principle of least privilege - giving users only the permissions they need. Without IAM, anyone could delete your databases or rack up huge bills. You create users, groups, and roles with specific permissions.

```json
{
  "Version": "2012-10-17",
  "Statement": [{
    "Effect": "Allow",
    "Action": ["s3:GetObject", "s3:PutObject"],
    "Resource": "arn:aws:s3:::my-bucket/*"
  }]
}
```

---

### 5. What is an **AMI (Amazon Machine Image)**?

**Answer:** An AMI is a template that contains the operating system, application server, and applications needed to launch an EC2 instance. Think of it as a snapshot or blueprint of a configured server. You can create custom AMIs with your application pre-installed, then launch multiple identical servers from that AMI. This makes deployment faster and consistent.

```bash
# Creating an AMI from existing EC2 instance
aws ec2 create-image \
  --instance-id i-1234567890abcdef0 \
  --name "MyApp-v1.0" \
  --description "Production app with Java 17"

# Launching EC2 from AMI
aws ec2 run-instances \
  --image-id ami-0abcdef1234567890 \
  --instance-type t2.micro
```

---

### 6. What is **AWS Region and Availability Zone**?

**Answer:** A Region is a geographic area like US East (Virginia) or Asia Pacific (Mumbai) where AWS has data centers. Each Region has multiple Availability Zones (AZs), which are separate data centers with independent power and networking. If one AZ fails, your application in another AZ keeps running. You choose Regions based on where your users are located to reduce latency.

```javascript
// Deploying to multiple AZs for high availability
const config = {
  region: 'us-east-1',
  availabilityZones: ['us-east-1a', 'us-east-1b', 'us-east-1c'],
  multiAZ: true  // Distribute across AZs
};
```

---

### 7. What is **VPC (Virtual Private Cloud)**?

**Answer:** VPC is your own private network in AWS where you launch resources like EC2 instances and databases. It's isolated from other AWS customers and you control the IP address range, subnets, route tables, and network gateways. Think of it as your own data center in the cloud. You can make parts of it public (accessible from internet) or private (internal only).

```bash
# Creating a VPC
aws ec2 create-vpc --cidr-block 10.0.0.0/16

# Creating subnets within VPC
aws ec2 create-subnet \
  --vpc-id vpc-12345 \
  --cidr-block 10.0.1.0/24 \
  --availability-zone us-east-1a
```

---

### 8. What is the difference between **Public Subnet and Private Subnet**?

**Answer:** A Public Subnet has a route to the Internet Gateway, so resources in it can directly access the internet and be accessed from the internet. A Private Subnet doesn't have direct internet access - resources can only communicate within the VPC. You put web servers in public subnets and databases in private subnets for security. Private subnets use NAT Gateway to access internet for updates.

```yaml
# Network Architecture
VPC: 10.0.0.0/16
  Public Subnet: 10.0.1.0/24
    - Web Servers (accessible from internet)
    - Load Balancers
    - Route: 0.0.0.0/0 → Internet Gateway
  
  Private Subnet: 10.0.2.0/24
    - Application Servers
    - Databases
    - Route: 0.0.0.0/0 → NAT Gateway
```

---

### 9. What is **Elastic Load Balancer (ELB)**?

**Answer:** ELB automatically distributes incoming traffic across multiple EC2 instances or containers. If one server fails, ELB stops sending traffic to it and routes to healthy servers. There are three types: Application Load Balancer (for HTTP/HTTPS), Network Load Balancer (for TCP/UDP), and Classic Load Balancer (legacy). It helps achieve high availability and handles traffic spikes.

```javascript
// Express app behind ELB
const express = require('express');
const app = express();

app.get('/health', (req, res) => {
  res.status(200).send('OK');  // ELB health check
});

app.get('/', (req, res) => {
  res.send(`Served by ${process.env.HOSTNAME}`);
});

app.listen(3000);
```

---

### 10. What is **Auto Scaling**?

**Answer:** Auto Scaling automatically adds or removes EC2 instances based on demand. When traffic increases, it launches more servers. When traffic decreases, it terminates unused servers to save money. You define minimum, maximum, and desired capacity, plus scaling policies based on metrics like CPU usage or request count. It works with ELB to distribute traffic to new instances.

```json
{
  "AutoScalingGroupName": "my-app-asg",
  "MinSize": 2,
  "MaxSize": 10,
  "DesiredCapacity": 3,
  "ScalingPolicy": {
    "TargetValue": 70,
    "MetricType": "CPUUtilization"
  }
}
```

---

## Compute & Deployment

### 11. How do you deploy a **Spring Boot application on AWS**?

**Answer:** You can deploy Spring Boot on AWS in several ways: (1) Package as JAR and run on EC2 instance with Java installed, (2) Use Elastic Beanstalk which handles infrastructure automatically, (3) Containerize with Docker and run on ECS or EKS, or (4) Deploy as serverless using Lambda with Spring Cloud Function. Most common is Elastic Beanstalk for simplicity or EC2 for full control.

```bash
# Method 1: Deploy JAR to EC2
mvn clean package
scp target/myapp.jar ec2-user@ec2-instance:/home/ec2-user/
ssh ec2-user@ec2-instance
java -jar myapp.jar

# Method 2: Elastic Beanstalk
eb init -p java-17 my-spring-app
eb create production-env
eb deploy
```

---

### 12. What is **AWS Elastic Beanstalk**?

**Answer:** Elastic Beanstalk is a Platform-as-a-Service (PaaS) that automatically handles deployment, load balancing, auto-scaling, and monitoring. You just upload your code (JAR, WAR, ZIP) and Beanstalk provisions EC2 instances, load balancers, and databases. It's perfect when you want to focus on code, not infrastructure. You still have access to underlying resources if needed.

```yaml
# .ebextensions/options.config
option_settings:
  aws:elasticbeanstalk:application:environment:
    SERVER_PORT: 5000
    SPRING_PROFILES_ACTIVE: production
  aws:autoscaling:launchconfiguration:
    InstanceType: t3.medium
  aws:autoscaling:asg:
    MinSize: 2
    MaxSize: 8
```

---

### 13. What is **AWS Lambda**?

**Answer:** Lambda is serverless computing - you run code without managing servers. You upload a function, and AWS executes it when triggered by events like HTTP requests, file uploads to S3, or database changes. You only pay for execution time (per millisecond), not idle time. It's perfect for APIs, background jobs, data processing, and microservices. It auto-scales from zero to thousands of concurrent executions.

```javascript
// Lambda function triggered by API Gateway
exports.handler = async (event) => {
  const body = JSON.parse(event.body);
  
  // Process request
  const result = {
    message: 'Hello from Lambda',
    input: body
  };
  
  return {
    statusCode: 200,
    body: JSON.stringify(result)
  };
};
```

---

### 14. What is **containerization** and how does AWS support it?

**Answer:** Containerization packages your application with all dependencies into a container (using Docker) that runs consistently anywhere. AWS supports containers through ECS (Elastic Container Service) for running Docker containers, EKS (Elastic Kubernetes Service) for Kubernetes orchestration, and Fargate for serverless containers where you don't manage servers. Containers are lighter than VMs and deploy faster.

```dockerfile
# Dockerfile for Spring Boot
FROM openjdk:17-slim
COPY target/myapp.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

```bash
# Deploy to ECS
docker build -t myapp .
docker tag myapp:latest 123456.dkr.ecr.us-east-1.amazonaws.com/myapp
docker push 123456.dkr.ecr.us-east-1.amazonaws.com/myapp
aws ecs update-service --cluster prod --service myapp --force-new-deployment
```

---

### 15. What is the difference between **EC2 and Lambda**?

**Answer:** EC2 is a virtual server that runs continuously - you pay by the hour even if idle, you manage the OS and scaling, and it's good for long-running applications. Lambda is serverless - you pay only for execution time (milliseconds), AWS manages everything, and it auto-scales automatically. Use EC2 for traditional apps, databases, or when you need full control. Use Lambda for APIs, event-driven tasks, and microservices to save costs.

```javascript
// EC2 - Always running server
const express = require('express');
const app = express();
app.listen(3000);  // Runs 24/7, pays hourly

// Lambda - Runs only when invoked
exports.handler = async (event) => {
  return { statusCode: 200, body: 'Done' };
};  // Pays only per execution
```

---

## Summary Comparison Table

| Feature | EC2 | Lambda |
|---------|-----|--------|
| **Billing** | Per hour/second | Per millisecond |
| **Management** | You manage OS, patches | AWS manages everything |
| **Scaling** | Manual or Auto Scaling | Automatic, instant |
| **Idle Cost** | Yes, always running | No, pay per execution |
| **Use Case** | Long-running apps | Event-driven, APIs |
| **Execution Time** | Unlimited | Max 15 minutes |

---

*Generated for AWS Interview Preparation - Questions 1-15*

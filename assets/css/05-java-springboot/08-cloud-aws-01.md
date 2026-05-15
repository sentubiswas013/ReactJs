## Basic AWS Concepts

### 1. What is AWS and why is it used?

**AWS (Amazon Web Services)** is a cloud platform that provides services like **servers, storage, databases, and networking over the internet**. It is used to **build, deploy, and scale applications without managing physical hardware**, and you only **pay for the resources you use**.


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

The main AWS services I have used are **EC2 for running applications, S3 for file storage, RDS for databases, IAM for access control, VPC for networking, and CloudWatch for monitoring**. These services help deploy, manage, and scale applications in the cloud.


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

**EC2** is a virtual server used to **run applications**, while **S3** is a storage service used to **store files like images, videos, and backups**.
In simple terms, **EC2 is for computing and S3 is for storage**.


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

**IAM (Identity and Access Management)** is used to **manage access to AWS resources**. It allows you to create **users, roles, and permissions** to control who can access and perform actions in AWS securely.


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

An **AMI (Amazon Machine Image)** is a **template used to launch EC2 instances**. It contains the **operating system, software, and configuration**, so we can quickly create and run new servers.


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

An **AWS Region** is a **geographical location where AWS has data centers**.
An **Availability Zone (AZ)** is a **separate data center within a region**, used to improve **high availability and fault tolerance**.


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

**VPC (Virtual Private Cloud)** is a **private network in AWS** where we launch resources like **EC2 instances and databases securely**. It allows us to control **IP addresses, subnets, and network access**.

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

A **Public Subnet** allows resources to **access the internet directly** through an Internet Gateway.
A **Private Subnet** does **not allow direct internet access** and is mainly used for secure resources like **databases**.


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

**Elastic Load Balancer (ELB)** distributes incoming traffic across **multiple EC2 instances** to improve **performance and availability**. If one server fails, it automatically sends traffic to the **healthy servers**.


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

**Auto Scaling** automatically **adds or removes EC2 instances based on traffic or demand**. It helps maintain **application performance and availability** while also **reducing cost when traffic is low**.


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

## Storage

### 16. What is **Amazon S3**?

**Answer:** Amazon S3 (Simple Storage Service) is object storage for storing and retrieving any amount of data from anywhere on the web. You organize files into buckets (like folders), and each file gets a unique URL. It's highly durable (99.999999999% - eleven 9s), scalable to unlimited storage, and perfect for backups, static websites, images, videos, and data lakes. You pay only for what you store and transfer.

```javascript
// Upload file to S3
const AWS = require('aws-sdk');
const s3 = new AWS.S3();

const params = {
  Bucket: 'my-app-bucket',
  Key: 'uploads/photo.jpg',
  Body: fileBuffer,
  ContentType: 'image/jpeg'
};

await s3.putObject(params).promise();

// Get file URL
const url = `https://my-app-bucket.s3.amazonaws.com/uploads/photo.jpg`;
```

---

### 17. What is the difference between **S3, EBS, and EFS**?

**Answer:** S3 is object storage for files accessed via HTTP - unlimited storage, accessed from anywhere, good for static content. EBS (Elastic Block Store) is block storage that attaches to one EC2 instance like a hard drive - used for databases and application data. EFS (Elastic File System) is network file storage that multiple EC2 instances can mount simultaneously - good for shared application files. S3 is cheapest, EBS is fastest, EFS is for sharing.

```bash
# S3 - Object storage (HTTP access)
aws s3 cp myfile.txt s3://my-bucket/

# EBS - Block storage (attached to EC2)
aws ec2 attach-volume --volume-id vol-123 --instance-id i-456 --device /dev/sdf

# EFS - Network file system (mount on multiple EC2)
sudo mount -t efs fs-123:/ /mnt/efs
```

---

### 18. What is an **S3 bucket policy**?

**Answer:** An S3 bucket policy is a JSON document that defines who can access your bucket and what actions they can perform. It controls permissions at the bucket level - like making a bucket public for website hosting, allowing specific AWS accounts to read files, or restricting access to certain IP addresses. It's different from IAM policies which control what users can do.

```json
{
  "Version": "2012-10-17",
  "Statement": [{
    "Sid": "PublicReadAccess",
    "Effect": "Allow",
    "Principal": "*",
    "Action": "s3:GetObject",
    "Resource": "arn:aws:s3:::my-website-bucket/*"
  }]
}
```

---

## Database

### 19. What is **Amazon RDS**?

**Answer:** Amazon RDS (Relational Database Service) is a managed database service that handles setup, patching, backups, and scaling automatically. You choose a database engine (MySQL, PostgreSQL, etc.), and AWS manages the infrastructure. It handles automated backups, software updates, monitoring, and failure detection. You focus on your application while AWS handles database administration tasks.

```python
# Connect to RDS MySQL
import pymysql

connection = pymysql.connect(
    host='mydb.abc123.us-east-1.rds.amazonaws.com',
    user='admin',
    password='password',
    database='myapp',
    port=3306
)

cursor = connection.cursor()
cursor.execute("SELECT * FROM users")
```

---

### 20. What databases are supported in **RDS**?

**Answer:** RDS supports six database engines: MySQL, PostgreSQL, MariaDB, Oracle, Microsoft SQL Server, and Amazon Aurora (AWS's own MySQL and PostgreSQL-compatible database). Aurora is the fastest and most scalable option. You choose based on your application's needs - MySQL/PostgreSQL for open-source, Oracle/SQL Server for enterprise apps, and Aurora for high performance.

```yaml
# RDS Database Options
Supported Engines:
  - MySQL: 5.7, 8.0
  - PostgreSQL: 11, 12, 13, 14, 15
  - MariaDB: 10.x
  - Oracle: 12c, 19c, 21c
  - SQL Server: 2016, 2017, 2019, 2022
  - Aurora: MySQL-compatible, PostgreSQL-compatible
```

---

### 21. What is **Multi-AZ deployment**?

**Answer:** Multi-AZ deployment creates a standby replica of your database in a different Availability Zone for high availability. If the primary database fails, RDS automatically fails over to the standby (usually within 60 seconds) with no data loss. The standby is synchronously replicated, meaning every write to primary is immediately copied. It's for disaster recovery, not for scaling reads - use Read Replicas for that.

```bash
# Enable Multi-AZ for RDS
aws rds modify-db-instance \
  --db-instance-identifier mydb \
  --multi-az \
  --apply-immediately

# Architecture
# Primary DB (us-east-1a) ←→ Standby DB (us-east-1b)
# Automatic failover if primary fails
```

---

### 22. What is a **Read Replica in AWS RDS**?

**Answer:** A Read Replica is a read-only copy of your database that handles read queries to reduce load on the primary database. It's asynchronously replicated, meaning there's a slight delay. You can create up to 5 read replicas per primary database, and they can be in different regions. Use them to scale read-heavy workloads like reporting, analytics, or serving read traffic from multiple locations.

```javascript
// Application using Read Replica
const mysql = require('mysql2');

// Write to primary
const primaryDB = mysql.createConnection({
  host: 'primary.abc123.us-east-1.rds.amazonaws.com'
});
primaryDB.query('INSERT INTO users VALUES (...)');

// Read from replica
const replicaDB = mysql.createConnection({
  host: 'replica.abc123.us-east-1.rds.amazonaws.com'
});
replicaDB.query('SELECT * FROM users');
```

---

## Networking

### 23. What is **Internet Gateway**?

**Answer:** An Internet Gateway (IGW) is a VPC component that allows communication between your VPC and the internet. It's horizontally scaled, redundant, and highly available. You attach it to your VPC and add a route in your public subnet's route table pointing to the IGW. Resources in public subnets with public IP addresses can then send and receive traffic from the internet.

```bash
# Create and attach Internet Gateway
aws ec2 create-internet-gateway
aws ec2 attach-internet-gateway --vpc-id vpc-123 --internet-gateway-id igw-456

# Add route to route table
aws ec2 create-route \
  --route-table-id rtb-789 \
  --destination-cidr-block 0.0.0.0/0 \
  --gateway-id igw-456
```

---

### 24. What is **NAT Gateway**?

**Answer:** A NAT Gateway allows resources in private subnets to access the internet for updates and downloads, but prevents the internet from initiating connections to them. It's placed in a public subnet and private subnets route their outbound traffic through it. This keeps databases and application servers secure while allowing them to download patches. You pay per hour and per GB of data processed.

```yaml
# Network Flow with NAT Gateway
Private Subnet (Database):
  - Needs to download updates
  - Route: 0.0.0.0/0 → NAT Gateway (in public subnet)
  
NAT Gateway:
  - Located in public subnet
  - Has Elastic IP
  - Route: 0.0.0.0/0 → Internet Gateway
  
Result: Database can reach internet, but internet cannot reach database
```

---

### 25. Difference between **Security Group and NACL**?

**Answer:** Security Groups are stateful firewalls at the instance level - if you allow inbound traffic, the response is automatically allowed. They only have allow rules. NACLs (Network Access Control Lists) are stateless firewalls at the subnet level - you must explicitly allow both inbound and outbound traffic. They have both allow and deny rules. Security Groups are your first line of defense, NACLs are the second layer.

```bash
# Security Group (Instance level, Stateful)
Inbound: Allow port 80 from 0.0.0.0/0
Outbound: Automatically allowed (stateful)

# NACL (Subnet level, Stateless)
Inbound Rule 100: Allow port 80 from 0.0.0.0/0
Outbound Rule 100: Allow port 1024-65535 to 0.0.0.0/0  # Must explicitly allow response
```

---

### 26. What is **Route 53**?

**Answer:** Route 53 is AWS's DNS (Domain Name System) service that routes users to your application by translating domain names like example.com to IP addresses. It offers domain registration, DNS routing, and health checking. You can route traffic based on latency, geography, or weighted distribution. It integrates with other AWS services and provides 100% uptime SLA.

```javascript
// Route 53 routing policies example
const dnsConfig = {
  domain: 'myapp.com',
  records: [
    {
      type: 'A',
      name: 'myapp.com',
      value: '54.123.45.67',  // ELB IP
      ttl: 300
    },
    {
      type: 'CNAME',
      name: 'www.myapp.com',
      value: 'myapp.com'
    }
  ]
};
```

---

## Monitoring & Security

### 27. What is **CloudWatch**?

**Answer:** CloudWatch is AWS's monitoring and observability service that collects metrics, logs, and events from your resources. It tracks CPU usage, disk I/O, network traffic, and custom application metrics. You can set alarms to trigger actions like auto-scaling or send notifications. CloudWatch Logs stores application logs, and you can create dashboards to visualize everything in one place.

```javascript
// Send custom metric to CloudWatch
const AWS = require('aws-sdk');
const cloudwatch = new AWS.CloudWatch();

await cloudwatch.putMetricData({
  Namespace: 'MyApp',
  MetricData: [{
    MetricName: 'OrdersProcessed',
    Value: 150,
    Unit: 'Count',
    Timestamp: new Date()
  }]
}).promise();
```

---

### 28. What is **AWS CloudTrail**?

**Answer:** CloudTrail records all API calls and actions taken in your AWS account - who did what, when, and from where. It's for auditing, compliance, and security analysis. Every time someone creates an EC2 instance, deletes an S3 bucket, or changes IAM permissions, CloudTrail logs it. You can track unauthorized access attempts, troubleshoot issues, and meet regulatory requirements.

```json
{
  "eventName": "DeleteBucket",
  "userIdentity": {
    "userName": "john.doe",
    "principalId": "AIDAI123456"
  },
  "eventTime": "2024-01-15T10:30:00Z",
  "requestParameters": {
    "bucketName": "production-data"
  },
  "sourceIPAddress": "203.0.113.42"
}
```

---

### 29. What is the difference between **IAM Role and IAM User**?

**Answer:** An IAM User is a permanent identity with long-term credentials (username/password or access keys) for a specific person or application. An IAM Role is a temporary identity that can be assumed by anyone who needs it - no permanent credentials. Roles are better for EC2 instances, Lambda functions, or cross-account access because credentials rotate automatically. Users are for humans who need console access.

```bash
# IAM User - Permanent credentials
aws configure  # Enter access key and secret key

# IAM Role - Temporary credentials (for EC2)
# Attach role to EC2 instance, no keys needed
aws ec2 run-instances \
  --image-id ami-123 \
  --iam-instance-profile Name=MyAppRole

# Application automatically gets temporary credentials
```

---

## Architecture / Real Project

### 30. How would you design a **highly available application in AWS**?

**Answer:** A highly available application uses multiple Availability Zones, load balancing, auto-scaling, and managed services. Deploy EC2 instances or containers across at least 2 AZs behind an Application Load Balancer. Use Auto Scaling to handle failures and traffic spikes. Store data in Multi-AZ RDS for databases and S3 for files. Use CloudFront CDN for static content. Monitor with CloudWatch and set up alarms. Use Route 53 for DNS with health checks.

```yaml
# High Availability Architecture
Route 53 (DNS with health checks)
  ↓
CloudFront (CDN for static content)
  ↓
Application Load Balancer (distributes traffic)
  ↓
Auto Scaling Group (2-10 instances)
  ├─ EC2 in us-east-1a (AZ1)
  ├─ EC2 in us-east-1b (AZ2)
  └─ EC2 in us-east-1c (AZ3)
  ↓
RDS Multi-AZ (Primary + Standby)
  ├─ Primary DB in AZ1
  └─ Standby DB in AZ2
  ↓
S3 (File storage - 99.999999999% durability)

Monitoring: CloudWatch + CloudTrail
Backup: Automated RDS snapshots + S3 versioning
```

```javascript
// Application code with retry logic
const axios = require('axios');

async function callDatabaseWithRetry() {
  const maxRetries = 3;
  for (let i = 0; i < maxRetries; i++) {
    try {
      return await database.query('SELECT * FROM users');
    } catch (error) {
      if (i === maxRetries - 1) throw error;
      await new Promise(resolve => setTimeout(resolve, 1000 * (i + 1)));
    }
  }
}
```

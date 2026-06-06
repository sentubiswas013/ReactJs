# AWS Interview Notes for Java / Spring Boot Developers

---

# 1. What is AWS and why is it used?

**AWS (Amazon Web Services)** is a cloud computing platform that provides services like servers, databases, storage, networking, monitoring, and security over the internet.

It helps companies:

* Deploy applications quickly
* Scale applications automatically
* Reduce infrastructure cost
* Avoid managing physical servers
* Improve availability and reliability

AWS follows a **pay-as-you-go** model.

```javascript
// Example: Connecting to AWS S3 using Node.js

const AWS = require('aws-sdk');

const s3 = new AWS.S3({
  region: 'us-east-1',
  accessKeyId: process.env.AWS_ACCESS_KEY,
  secretAccessKey: process.env.AWS_SECRET_KEY
});
```

---

# 2. Main AWS Services Used

Common AWS services used in real projects:

| Service      | Purpose                                  |
| ------------ | ---------------------------------------- |
| EC2          | Virtual servers for hosting applications |
| S3           | File storage                             |
| RDS          | Managed relational database              |
| IAM          | Authentication and authorization         |
| VPC          | Private network setup                    |
| CloudWatch   | Monitoring and alerts                    |
| ELB          | Load balancing                           |
| Auto Scaling | Automatic server scaling                 |
| Lambda       | Serverless computing                     |
| Route 53     | DNS management                           |

```yaml
Services:
  - EC2: Application hosting
  - S3: Images, videos, backups
  - RDS: MySQL/PostgreSQL database
  - Lambda: Background jobs
  - CloudFront: CDN
  - CloudWatch: Monitoring
```

---

# 3. Difference Between EC2 and S3

| EC2                   | S3                               |
| --------------------- | -------------------------------- |
| Compute service       | Storage service                  |
| Runs applications     | Stores files                     |
| Virtual machine       | Object storage                   |
| Used for backend apps | Used for images, videos, backups |

```python
# Running application on EC2

from flask import Flask

app = Flask(__name__)

@app.route('/')
def home():
    return "Running on EC2"
```

```python
# Upload file to S3

import boto3

s3 = boto3.client('s3')

s3.upload_file('photo.jpg', 'my-bucket', 'photo.jpg')
```

---

# 4. What is IAM?

**IAM (Identity and Access Management)** controls access to AWS resources securely.

Using IAM we can:

* Create users and roles
* Assign permissions
* Restrict access
* Improve security

```json
{
  "Version": "2012-10-17",
  "Statement": [{
    "Effect": "Allow",
    "Action": [
      "s3:GetObject",
      "s3:PutObject"
    ],
    "Resource": "arn:aws:s3:::my-bucket/*"
  }]
}
```

---

# 5. What is AMI?

**AMI (Amazon Machine Image)** is a template used to launch EC2 instances.

It contains:

* Operating System
* Installed software
* Configurations
* Application dependencies

```bash
# Create AMI

aws ec2 create-image \
  --instance-id i-123456789 \
  --name "MyApp-AMI"

# Launch EC2 from AMI

aws ec2 run-instances \
  --image-id ami-123456 \
  --instance-type t2.micro
```

---

# 6. AWS Region and Availability Zone

| Region              | Availability Zone         |
| ------------------- | ------------------------- |
| Geographic location | Data center inside region |
| Example: us-east-1  | Example: us-east-1a       |

Availability Zones improve:

* High availability
* Fault tolerance
* Disaster recovery

```javascript
const config = {
  region: 'us-east-1',
  availabilityZones: [
    'us-east-1a',
    'us-east-1b'
  ]
};
```

---

# 7. What is VPC?

**VPC (Virtual Private Cloud)** is a private network inside AWS.

Using VPC we can control:

* IP ranges
* Subnets
* Routing
* Security

```bash
# Create VPC

aws ec2 create-vpc --cidr-block 10.0.0.0/16
```

---

# 8. Public vs Private Subnet

| Public Subnet         | Private Subnet            |
| --------------------- | ------------------------- |
| Internet accessible   | No direct internet access |
| Used for web servers  | Used for databases        |
| Uses Internet Gateway | Uses NAT Gateway          |

```yaml
VPC: 10.0.0.0/16

Public Subnet:
  - Load Balancer
  - Web Servers

Private Subnet:
  - Application Servers
  - Databases
```

---

# 9. What is ELB?

**Elastic Load Balancer (ELB)** distributes traffic across multiple servers.

Benefits:

* High availability
* Better performance
* Fault tolerance
* Health checks

```javascript
app.get('/health', (req, res) => {
  res.status(200).send('OK');
});
```

---

# 10. What is Auto Scaling?

Auto Scaling is a cloud feature that automatically increases or decreases the number of application instances based on traffic, CPU usage, memory usage, or other metrics. It helps maintain performance during high load and reduces cost during low traffic.

Benefits:

* Handles high traffic
* Reduces cost
* Improves availability

```json
{
  "MinSize": 2,
  "MaxSize": 10,
  "DesiredCapacity": 3
}
```
Suppose your application normally runs with **2 servers**.

* If CPU usage goes above **80%**, Auto Scaling adds more servers.
* If CPU usage drops below **30%**, Auto Scaling removes extra servers.

```text
Normal Traffic:
2 Instances

High Traffic:
2 → 4 → 6 Instances

Low Traffic:
6 → 4 → 2 Instances
```


```yaml
Min Instances: 2
Desired Instances: 2
Max Instances: 10

Scale Out:
CPU > 80% for 5 minutes

Scale In:
CPU < 30% for 10 minutes
```

---

# 11. Deploying Spring Boot Application on AWS

Common deployment methods:

1. EC2
2. Elastic Beanstalk
3. ECS/EKS using Docker
4. AWS Lambda

Most common:

* EC2 for full control
* Elastic Beanstalk for easier deployment

```bash
# Build Spring Boot app

mvn clean package

# Run application

java -jar myapp.jar
```

---

# 12. What is Elastic Beanstalk?

Elastic Beanstalk is a Platform as a Service (PaaS).

AWS automatically manages:

* EC2
* Load Balancer
* Auto Scaling
* Monitoring

You only deploy your code.

```bash
eb init
eb create production-env
eb deploy
```

---

# 13. What is AWS Lambda?

Lambda is a serverless service where AWS runs code without managing servers.

Benefits:

* No server management
* Pay only for execution
* Automatic scaling

```javascript
exports.handler = async (event) => {

  return {
    statusCode: 200,
    body: 'Hello from Lambda'
  };
};
```

---

# 14. What is Containerization?

Containerization packages application code with dependencies using Docker.

AWS container services:

| Service | Purpose                     |
| ------- | --------------------------- |
| ECS     | AWS container orchestration |
| EKS     | Kubernetes service          |
| Fargate | Serverless containers       |

```dockerfile
FROM openjdk:17-slim

COPY target/app.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
```

---

# 15. EC2 vs Lambda

| EC2               | Lambda                     |
| ----------------- | -------------------------- |
| Server-based      | Serverless                 |
| Runs continuously | Runs on demand             |
| Pay hourly        | Pay per execution          |
| Full OS control   | AWS manages infrastructure |

---

# 16. What is Amazon S3?

S3 is object storage used for:

* Images
* Videos
* Backups
* Static websites
* Logs

Features:

* Highly scalable
* Durable
* Secure

```javascript
await s3.putObject({
  Bucket: 'my-bucket',
  Key: 'photo.jpg',
  Body: fileBuffer
}).promise();
```

---

# 17. Difference Between S3, EBS, and EFS

| Service | Type                | Usage         |
| ------- | ------------------- | ------------- |
| S3      | Object storage      | Files         |
| EBS     | Block storage       | EC2 disks     |
| EFS     | Shared file storage | Shared access |

---

# 18. What is S3 Bucket Policy?

Bucket policy controls access to S3 buckets.

Example:

* Public read access
* Restrict IP access
* Cross-account access

```json
{
  "Effect": "Allow",
  "Principal": "*",
  "Action": "s3:GetObject"
}
```

---

# 19. What is Amazon RDS?

RDS is a managed relational database service.

AWS handles:

* Backups
* Patching
* Scaling
* Monitoring
* Failover

Supported databases:

* MySQL
* PostgreSQL
* Oracle
* SQL Server
* MariaDB
* Aurora

```python
connection = pymysql.connect(
    host='mydb.rds.amazonaws.com',
    user='admin',
    password='password'
)
```

---

# 20. What is Multi-AZ?

Multi-AZ creates a standby database in another Availability Zone.

Benefits:

* High availability
* Automatic failover
* Disaster recovery

```bash
aws rds modify-db-instance \
  --db-instance-identifier mydb \
  --multi-az
```

---

# 21. What is Read Replica?

Read Replica is a read-only copy of a database.

Used for:

* Scaling read traffic
* Reporting
* Analytics

```javascript
// Write to primary DB
primaryDB.query("INSERT INTO users VALUES (...)");

// Read from replica
replicaDB.query("SELECT * FROM users");
```

---

# 22. What is Internet Gateway?

Internet Gateway allows communication between VPC and internet.

```bash
aws ec2 create-internet-gateway
```

---

# 23. What is NAT Gateway?

NAT Gateway allows private subnet resources to access internet securely.

Example:

* Download patches
* Software updates

But internet cannot directly access private resources.

---

# 24. Security Group vs NACL

| Security Group   | NACL                 |
| ---------------- | -------------------- |
| Instance level   | Subnet level         |
| Stateful         | Stateless            |
| Allow rules only | Allow and Deny rules |

---

# 25. What is Route 53?

Route 53 is AWS DNS service.

Features:

* Domain registration
* DNS routing
* Health checks
* Traffic routing

```javascript
{
  type: 'A',
  name: 'myapp.com',
  value: '54.123.45.67'
}
```

---

# 26. What is CloudWatch?

CloudWatch monitors AWS resources.

Used for:

* Metrics
* Logs
* Alerts
* Dashboards

```javascript
await cloudwatch.putMetricData({
  Namespace: 'MyApp',
  MetricData: [{
    MetricName: 'OrdersProcessed',
    Value: 100
  }]
}).promise();
```

---

# 27. What is CloudTrail?

CloudTrail records all AWS API activities.

Used for:

* Auditing
* Security monitoring
* Troubleshooting

```json
{
  "eventName": "DeleteBucket",
  "userName": "john.doe"
}
```

---

# 28. IAM User vs IAM Role

| IAM User           | IAM Role              |
| ------------------ | --------------------- |
| Permanent identity | Temporary identity    |
| Used by humans     | Used by services      |
| Uses access keys   | Temporary credentials |

---

# 29. High Availability Architecture in AWS

A highly available AWS architecture includes:

* Multiple Availability Zones
* Load Balancer
* Auto Scaling
* RDS Multi-AZ
* S3 storage
* CloudFront CDN
* Route 53 health checks

```yaml
Route53
  ↓
CloudFront
  ↓
Application Load Balancer
  ↓
Auto Scaling Group
  ├── EC2 - AZ1
  ├── EC2 - AZ2
  └── EC2 - AZ3
  ↓
RDS Multi-AZ
  ↓
S3 Storage
```

---

# 30. Real-Time AWS Project Flow

```text
User Request
    ↓
Route 53
    ↓
CloudFront CDN
    ↓
Application Load Balancer
    ↓
Spring Boot Application (EC2/ECS)
    ↓
RDS Database
    ↓
S3 Storage
```

Monitoring:

* CloudWatch
* CloudTrail

Security:

* IAM
* Security Groups
* VPC

Scalability:

* Auto Scaling
* ELB

---

# Quick Revision Summary

| Service      | Purpose           |
| ------------ | ----------------- |
| EC2          | Virtual server    |
| S3           | Storage           |
| RDS          | Managed database  |
| IAM          | Access management |
| VPC          | Private network   |
| ELB          | Load balancing    |
| Auto Scaling | Automatic scaling |
| Lambda       | Serverless        |
| CloudWatch   | Monitoring        |
| CloudTrail   | Auditing          |
| Route 53     | DNS               |
| ECS/EKS      | Containers        |
| CloudFront   | CDN               |

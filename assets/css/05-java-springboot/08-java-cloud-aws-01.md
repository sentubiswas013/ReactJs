

# **1. AWS Core Concepts**

**1. What is AWS?**
**AWS (Amazon Web Services)** is a **cloud computing platform** by Amazon that provides **on-demand IT resources** like servers, storage, databases, and networking over the internet.

**Key Features:**

* **Pay-as-you-go pricing**
* **Highly scalable infrastructure**
* **Global availability (regions & AZs)**
* **Managed services**

**How it works:**
AWS provides resources through the internet. You select a service (like EC2 or S3), configure it, and use it without managing physical hardware.

**Example:**
Hosting a website using **EC2 + S3 + RDS** instead of physical servers.

---

**2. What is cloud computing?**
**Cloud computing** is the delivery of **computing services (servers, storage, databases, networking)** over the internet on a **pay-per-use model**.

**Key Features:**

* **On-demand resources**
* **Elastic scalability**
* **Cost efficiency**
* **No hardware maintenance**

**How it works:**
Instead of owning infrastructure, you access resources from cloud providers like AWS and scale them up/down as needed.

**Example:**
Using **Google Drive or AWS S3** to store files instead of local storage.

---

**3. What is AMI?**
An **AMI (Amazon Machine Image)** is a **pre-configured template** used to launch **EC2 instances**.

**Key Features:**

* Contains **OS + software + configuration**
* Can be **custom or public**
* Used to launch multiple identical servers

**How it works:**
You select an AMI → AWS creates an EC2 instance using that image → same environment is replicated.

**Example:**
Using a **Linux AMI with Java + Tomcat installed** to launch multiple servers.

---

**4. What is EC2?**
**EC2 (Elastic Compute Cloud)** is a service that provides **virtual servers in the cloud** to run applications.

**Key Features:**

* **Resizable compute capacity**
* Multiple instance types (CPU, memory optimized)
* Secure via **security groups**
* Auto scaling support

**How it works:**
You choose an AMI, select instance type, configure storage/network, and launch a virtual server.

**Example:**
Deploying a **Spring Boot application** on an EC2 Linux instance.

---

**5. What is AWS Lambda?**
**AWS Lambda** is a **serverless compute service** that runs code without provisioning servers.

**Key Features:**

* **No server management**
* **Event-driven execution**
* **Auto scaling**
* Pay only for execution time

**How it works:**
You upload code → define trigger (API Gateway, S3, etc.) → Lambda runs code when event occurs.

**Example (Java Lambda):**

```java
public class HelloLambda implements RequestHandler<String, String> {
    @Override
    public String handleRequest(String input, Context context) {
        return "Hello " + input;
    }
}
```

---

# **2. IAM & Security**

**6. What is IAM?**
**IAM (Identity and Access Management)** is an AWS service used to **manage users, groups, roles, and permissions** securely.

**Key Features:**

* **Centralized access control**
* **Fine-grained permissions**
* **Secure authentication & authorization**
* **Supports MFA**

**How it works:**
You create **users/groups/roles** and attach **policies** to control what resources they can access in AWS.

**Example:**
Giving a developer access only to **S3 read-only** instead of full AWS access.

---

**7. What is IAM Role?**
An **IAM Role** is an AWS identity with **temporary permissions** that can be assumed by **users, services, or applications**.

**Key Features:**

* **No long-term credentials**
* Used by **AWS services (EC2, Lambda)**
* Supports **cross-account access**

**How it works:**
A service (like EC2) assumes a role → gets **temporary security credentials** → accesses AWS resources.

**Example:**
EC2 accessing **S3 bucket** without storing access keys.

---

**8. What is IAM Policy?**
An **IAM Policy** is a **JSON document** that defines **permissions (allow/deny)** for AWS resources.

**Key Features:**

* Written in **JSON format**
* Defines **Allow/Deny actions**
* Attached to **users, groups, roles**

**How it works:**
Policy is evaluated when a request is made → AWS checks if action is allowed or denied.

**Example Policy (S3 Read Only):**

```json
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Effect": "Allow",
      "Action": "s3:GetObject",
      "Resource": "arn:aws:s3:::my-bucket/*"
    }
  ]
}
```

---

**9. What is MFA in AWS?**
**MFA (Multi-Factor Authentication)** adds an extra layer of security requiring **password + second verification factor**.

**Key Features:**

* **Two-step authentication**
* Supports **virtual/hardware devices**
* Prevents unauthorized access even if password is stolen

**How it works:**
User logs in with password → enters **OTP from MFA device** → access granted.

**Example:**
AWS Console login using password + **Google Authenticator OTP**.

---

**10. What is AWS STS?**
**AWS STS (Security Token Service)** provides **temporary security credentials** for AWS access.

**Key Features:**

* Issues **temporary credentials**
* Used with **IAM roles**
* Supports **cross-account access**
* Short-lived security tokens

**How it works:**
A user/service requests STS → receives **temporary access keys** → uses them to access AWS resources.

**Example (Java STS assume role):**

```java id="sts_example"
AWSSecurityTokenService stsClient = AWSSecurityTokenServiceClientBuilder.defaultClient();

AssumeRoleRequest request = new AssumeRoleRequest()
        .withRoleArn("arn:aws:iam::123456789012:role/MyRole")
        .withRoleSessionName("session1");

AssumeRoleResult response = stsClient.assumeRole(request);

Credentials creds = response.getCredentials();
System.out.println(creds.getAccessKeyId());
```


---

# **3. Compute Services**

**11. What is EC2 Auto Scaling?**
**EC2 Auto Scaling** is an AWS service that automatically **adds or removes EC2 instances** based on demand to maintain performance and optimize cost.

**Key Features:**

* **Automatic scaling (up/down)**
* **High availability & fault tolerance**
* **Cost optimization**
* Works with **CloudWatch metrics**

**How it works:**
You define a **scaling group + policies (CPU, memory, requests)** → AWS monitors load → automatically launches or terminates EC2 instances.

**Example:**
During high traffic (e.g., sale event), EC2 instances increase automatically.

---

**12. What is Elastic Beanstalk?**
**AWS Elastic Beanstalk** is a **Platform as a Service (PaaS)** that deploys and manages applications automatically.

**Key Features:**

* **Easy deployment (upload code only)**
* Manages **EC2, load balancer, scaling**
* Supports multiple platforms (**Java, Node.js, Python**)
* Built-in monitoring

**How it works:**
You upload application code → Beanstalk automatically provisions infrastructure → deploys and manages application lifecycle.

**Example:**
Deploying a **Spring Boot app without manually creating EC2 instances**.

---

**13. What is ECS?**
**Amazon ECS (Elastic Container Service)** is a **container orchestration service** to run **Docker containers** on AWS.

**Key Features:**

* Runs **Docker containers**
* Integrated with **EC2 or Fargate**
* High scalability
* Deep AWS integration

**How it works:**
You define a **task definition (container config)** → ECS schedules containers on EC2/Fargate → manages lifecycle.

**Example:**
Running a **microservice in a Docker container on ECS cluster**.

---

**14. What is EKS?**
**Amazon EKS (Elastic Kubernetes Service)** is a **managed Kubernetes service** to run and manage **containerized applications**.

**Key Features:**

* Fully managed **Kubernetes control plane**
* Auto scaling & self-healing
* Works with Kubernetes tools
* High availability

**How it works:**
You define Kubernetes objects (pods, deployments) → EKS manages cluster control plane → runs workloads on worker nodes.

**Example:**
Deploying a **Spring Boot microservice using Kubernetes deployment YAML**.

---

**15. What is Fargate?**
**AWS Fargate** is a **serverless compute engine for containers** used with ECS and EKS.

**Key Features:**

* **No server management**
* Pay per **container execution**
* Auto scaling
* Works with **ECS & EKS**

**How it works:**
You define container task → Fargate runs it without provisioning EC2 → AWS manages infrastructure.

**Example (ECS Task Definition using Fargate):**

```json id="fargate_task"
{
  "family": "my-task",
  "networkMode": "awsvpc",
  "requiresCompatibilities": ["FARGATE"],
  "cpu": "512",
  "memory": "1024",
  "containerDefinitions": [
    {
      "name": "my-app",
      "image": "myrepo/myapp:latest",
      "portMappings": [
        {
          "containerPort": 8080
        }
      ]
    }
  ]
}
```


---

# **4. Storage Services**

**16. What is Amazon S3?**
**Amazon S3 (Simple Storage Service)** is an AWS service used to store **objects (files, images, videos, backups)** in a highly scalable and durable way.

**Key Features:**

* **Object storage (bucket-based)**
* **Highly durable (11 9’s durability)**
* **Scalable & secure**
* Supports **versioning & lifecycle rules**

**How it works:**
You create a **bucket** → upload objects (files) → access via URL or AWS SDK.

**Example:**
Storing **user profile images or application backups**.

---

**17. What is EBS?**
**EBS (Elastic Block Store)** provides **persistent block storage** for EC2 instances.

**Key Features:**

* **Block-level storage**
* **Persistent data (even if EC2 stops)**
* High performance (**SSD/HDD options**)
* Attached to **single EC2 instance at a time**

**How it works:**
You create an EBS volume → attach it to EC2 → OS treats it like a **hard disk**.

**Example:**
Storing **database files on EC2 instance**.

---

**18. What is EFS?**
**EFS (Elastic File System)** is a **shared file storage system** for multiple EC2 instances.

**Key Features:**

* **Shared storage across instances**
* **Auto scaling storage**
* Fully managed
* Supports **Linux-based systems**

**How it works:**
You create an EFS file system → mount it on multiple EC2 instances → all instances access same data.

**Example:**
Shared uploads folder for **multiple web servers**.

---

**19. What is S3 Versioning?**
**S3 Versioning** is a feature that keeps **multiple versions of an object** in a bucket.

**Key Features:**

* Stores **old versions of files**
* Protects against **accidental deletion/overwrite**
* Enables **data recovery**

**How it works:**
When versioning is enabled → every update creates a **new object version instead of replacing old one**.

**Example:**
Recovering an accidentally deleted **configuration file**.

---

**20. What is S3 Lifecycle Policy?**
**S3 Lifecycle Policy** automatically manages objects by **moving or deleting them based on rules and time**.

**Key Features:**

* Automates **storage cost optimization**
* Moves data to **cheaper storage classes**
* Can **delete expired objects**

**How it works:**
You define rules → AWS applies them based on object age or conditions.

**Example Policy (JSON):**

```json id="s3_lifecycle"
{
  "Rules": [
    {
      "ID": "MoveToGlacier",
      "Status": "Enabled",
      "Prefix": "",
      "Transitions": [
        {
          "Days": 30,
          "StorageClass": "GLACIER"
        }
      ],
      "Expiration": {
        "Days": 365
      }
    }
  ]
}
```


---

# **5. Networking & VPC**

**21. What is VPC?**
**VPC (Virtual Private Cloud)** is a **logically isolated virtual network** in AWS where you can launch and control AWS resources.

**Key Features:**

* **Complete network isolation**
* Control over **IP ranges (CIDR)**
* Supports **subnets, routing, gateways**
* Secure cloud networking

**How it works:**
You create a VPC → define IP range → create subnets, route tables, and gateways → deploy resources inside it.

**Example:**
Running a **secure application network in AWS isolated from other users**.

---

**22. What is Subnet?**
A **Subnet** is a **subdivision of a VPC** used to group resources in specific IP ranges.

**Key Features:**

* Public or **private subnets**
* Defined by **CIDR block**
* Used for resource organization
* Improves **security & architecture design**

**How it works:**
VPC is divided into subnets → resources like EC2 are placed in subnets based on accessibility.

**Example:**
Placing **web servers in public subnet and database in private subnet**.

---

**23. What is Internet Gateway?**
An **Internet Gateway (IGW)** is a component that allows **communication between VPC and the internet**.

**Key Features:**

* Enables **internet access for public subnets**
* Highly available
* Horizontally scalable
* Attached to **VPC**

**How it works:**
VPC attaches IGW → route table directs traffic → EC2 in public subnet accesses internet.

**Example:**
Allowing users to access a **public website hosted on EC2**.

---

**24. What is NAT Gateway?**
A **NAT Gateway** allows **private subnet instances to access the internet securely without being exposed**.

**Key Features:**

* Outbound internet access only
* Located in **public subnet**
* Managed & scalable
* Secure for private resources

**How it works:**
Private EC2 → sends request to NAT Gateway → NAT accesses internet → response returned back.

**Example:**
Private EC2 downloading **software updates from the internet**.

---

**25. What is Route 53?**
**Amazon Route 53** is a **scalable DNS (Domain Name System) service** that routes users to applications.

**Key Features:**

* Domain registration
* **DNS routing (latency, failover, weighted)**
* High availability
* Health checks

**How it works:**
User enters domain → Route 53 resolves DNS → routes traffic to correct AWS resource.

**Example:**
Mapping **[www.myapp.com](http://www.myapp.com) → EC2 Load Balancer**.

---

**26. What is Security Group?**
A **Security Group** is a **virtual firewall for EC2 instances** controlling inbound and outbound traffic.

**Key Features:**

* Works at **instance level**
* **Allow rules only (no deny rules)**
* Stateful (return traffic allowed automatically)
* Multiple rules supported

**How it works:**
You define rules → AWS filters traffic before reaching EC2 instance.

**Example:**
Allowing **HTTP (80) and SSH (22)** access to a web server.

---

**27. What is NACL?**
**NACL (Network Access Control List)** is a **subnet-level firewall** that controls inbound and outbound traffic.

**Key Features:**

* Works at **subnet level**
* Supports **Allow and Deny rules**
* Stateless (both inbound & outbound rules needed)
* Rule evaluation order matters

**How it works:**
Traffic enters subnet → NACL checks rules → allows or denies traffic.

**Example:**
Blocking traffic from a **specific IP range at subnet level**.


---

# **6. Load Balancing**

**28. What is ELB?**
**ELB (Elastic Load Balancer)** is an AWS service that **distributes incoming traffic across multiple targets (EC2 instances, containers, IPs)** to improve **availability and reliability**.

**Key Features:**

* **Traffic distribution (load balancing)**
* Improves **fault tolerance**
* Supports **health checks**
* Auto scaling integration

**How it works:**
User request → ELB receives traffic → routes to healthy backend instances → ensures no single server is overloaded.

**Example:**
A website handling traffic across **multiple EC2 instances** using ELB.

---

**29. What are types of load balancers in AWS?**
AWS provides **three main types of Elastic Load Balancers**:

**1. ALB (Application Load Balancer)**

* Works at **Layer 7 (HTTP/HTTPS)**
* Best for **web applications & microservices**
* Supports **path-based routing**

**2. NLB (Network Load Balancer)**

* Works at **Layer 4 (TCP/UDP)**
* Ultra **high performance & low latency**
* Best for **real-time apps**

**3. CLB (Classic Load Balancer)**

* Legacy load balancer
* Works at **both Layer 4 & 7**
* Not recommended for new applications

**How it works:**
Traffic enters ELB → ELB chooses target based on type and routing rules → forwards request to healthy instance.

---

**30. What is the difference between ALB and NLB?**

**Application Load Balancer (ALB)** vs **Network Load Balancer (NLB)**

**ALB (Application Load Balancer):**

* Works at **Layer 7 (HTTP/HTTPS)**
* Supports **advanced routing (path, host-based)**
* Best for **web apps & APIs**
* Slower compared to NLB but feature-rich

**NLB (Network Load Balancer):**

* Works at **Layer 4 (TCP/UDP)**
* Handles **millions of requests per second**
* Ultra **low latency**
* Best for **gaming, real-time, high throughput apps**

**Key Difference:**

* **ALB = Smart routing (application-aware)**
* **NLB = Fast routing (network-level performance)**

**How it works:**
Client request → ALB/NLB receives traffic → routes to target group based on rules (ALB) or IP/port (NLB).

**Example:**

* **ALB:** Routing `/login` → auth service, `/orders` → order service
* **NLB:** Real-time trading system handling millions of TCP requests quickly


---

# **7. Databases**

**31. What is Amazon RDS?**
**Amazon RDS (Relational Database Service)** is a **managed relational database service** that supports engines like **MySQL, PostgreSQL, Oracle, SQL Server, and MariaDB**.

**Key Features:**

* **Fully managed database service**
* Automated **backups & patching**
* **High availability (Multi-AZ support)**
* Easy **scaling & monitoring**

**How it works:**
You choose a database engine → AWS provisions DB instance → you connect using standard SQL tools → AWS manages infrastructure.

**Example:**
Running a **Spring Boot application with MySQL database on RDS**.

---

**32. What is DynamoDB?**
**Amazon DynamoDB** is a **fully managed NoSQL database** that provides **fast and predictable performance at any scale**.

**Key Features:**

* **NoSQL (key-value & document database)**
* **Serverless and highly scalable**
* Millisecond **low latency**
* Automatic scaling

**How it works:**
You create a **table with primary key** → store items (JSON-like data) → AWS handles scaling and performance.

**Example:**
Storing **user sessions or real-time application data**.

**Example (Java AWS SDK):**

```java id="dynamodb_example"
DynamoDbClient dynamoDb = DynamoDbClient.create();

Map<String, AttributeValue> item = new HashMap<>();
item.put("UserId", AttributeValue.builder().s("101").build());
item.put("Name", AttributeValue.builder().s("John").build());

PutItemRequest request = PutItemRequest.builder()
        .tableName("Users")
        .item(item)
        .build();

dynamoDb.putItem(request);
```

---

**33. What is Multi-AZ deployment?**
**Multi-AZ (Availability Zone) deployment** is a **high availability feature** where AWS automatically replicates data to a **standby instance in another AZ**.

**Key Features:**

* **Automatic failover**
* Synchronous data replication
* High **availability & durability**
* Zero manual intervention during failure

**How it works:**
Primary database writes data → data is synchronously replicated to standby in another AZ → if primary fails, standby becomes active automatically.

**Example:**
A production **RDS MySQL database running in Multi-AZ for high availability**.

---

**34. What is Read Replica?**
A **Read Replica** is a **read-only copy of a primary database** used to handle **read-heavy traffic and improve performance**.

**Key Features:**

* **Asynchronous replication**
* Improves **read scalability**
* Offloads traffic from primary DB
* Can be promoted to standalone DB

**How it works:**
Primary DB handles writes → changes are copied to replica → applications send read queries to replica.


---

# **8. Messaging & Event-Driven**

**35. What is SQS?**
**Amazon SQS (Simple Queue Service)** is a **fully managed message queue service** used to decouple distributed systems.

**Key Features:**

* **Message queuing (producer-consumer model)**
* **Decouples microservices**
* Supports **standard & FIFO queues**
* Automatically scales

**How it works:**
Producer sends message → message stored in SQS queue → consumer reads and processes message asynchronously.

**Example:**
Order service sends order events → payment service processes them from SQS.

**Example (Java AWS SDK):**

```java id="sqs_example"
SqsClient sqs = SqsClient.create();

SendMessageRequest request = SendMessageRequest.builder()
        .queueUrl("https://sqs.us-east-1.amazonaws.com/123456789/orders")
        .messageBody("OrderPlaced")
        .build();

sqs.sendMessage(request);
```

---

**36. What is SNS?**
**Amazon SNS (Simple Notification Service)** is a **pub/sub messaging service** used to send notifications to multiple subscribers.

**Key Features:**

* **Publish-subscribe model**
* Supports **email, SMS, HTTP, SQS, Lambda**
* Real-time notifications
* Fan-out messaging

**How it works:**
Publisher sends message → SNS topic → message delivered to all subscribers.

**Example:**
Order confirmation sent via **email + SMS + Lambda processing**.

**Example (Java AWS SDK):**

```java id="sns_example"
SnsClient sns = SnsClient.create();

PublishRequest request = PublishRequest.builder()
        .topicArn("arn:aws:sns:us-east-1:123456789:OrderTopic")
        .message("Order Confirmed")
        .build();

sns.publish(request);
```

---

**37. What is EventBridge?**
**Amazon EventBridge** is a **serverless event bus service** used to connect applications using **events in real time**.

**Key Features:**

* **Event-driven architecture**
* Integrates with **AWS services & SaaS apps**
* Rule-based routing
* Fully serverless

**How it works:**
Event source generates event → EventBridge receives it → routes to target services based on rules.

**Example:**
S3 file upload event triggers **Lambda processing pipeline**.

---

**38. What is Kinesis?**
**Amazon Kinesis** is a **real-time data streaming service** used to collect, process, and analyze streaming data.

**Key Features:**

* **Real-time data streaming**
* High throughput & scalability
* Multiple services: **Kinesis Data Streams, Firehose, Analytics**
* Low latency processing

**How it works:**
Producers send data streams → Kinesis collects and stores data → consumers process data in real time.

**Example:**
Processing **clickstream data from a website in real time**.

**Example (Java AWS SDK):**

```java id="kinesis_example"
KinesisClient kinesis = KinesisClient.create();

PutRecordRequest request = PutRecordRequest.builder()
        .streamName("ClickStream")
        .partitionKey("user1")
        .data(SdkBytes.fromUtf8String("page_view"))
        .build();

kinesis.putRecord(request);
```


---

# **9. Monitoring & Logging**

**39. What is CloudWatch?**
**Amazon CloudWatch** is a **monitoring and observability service** used to collect **metrics, logs, and alarms** from AWS resources and applications.

**Key Features:**

* Collects **metrics (CPU, memory, latency)**
* Centralized **logging system**
* **Alarms & notifications** (via SNS)
* Dashboards for visualization

**How it works:**
AWS services send metrics/logs → CloudWatch stores and monitors them → triggers alarms if thresholds are breached.

**Example:**
Trigger an alert when **EC2 CPU usage > 80%**.

**Example (AWS CLI):**

```bash
aws cloudwatch put-metric-alarm \
  --alarm-name HighCPUAlarm \
  --metric-name CPUUtilization \
  --namespace AWS/EC2 \
  --statistic Average \
  --period 300 \
  --threshold 80 \
  --comparison-operator GreaterThanThreshold \
  --evaluation-periods 2 \
  --alarm-actions arn:aws:sns:us-east-1:123456789:NotifyMe
```

---

**40. What is CloudTrail?**
**AWS CloudTrail** is a **governance and auditing service** that records **all API calls and actions** made within an AWS account.

**Key Features:**

* Tracks **user activity & API calls**
* Provides **audit and compliance logs**
* Stores event history in **S3**
* Integrates with **CloudWatch for alerts**

**How it works:**
User/service performs action → CloudTrail logs API request → stores logs in S3 → used for audit, security, and troubleshooting.

**Example:**
Tracking who **deleted an S3 bucket or modified IAM policies**.

**Example (AWS CLI to enable trail):**

```bash
aws cloudtrail create-trail \
  --name MyTrail \
  --s3-bucket-name my-cloudtrail-logs
```


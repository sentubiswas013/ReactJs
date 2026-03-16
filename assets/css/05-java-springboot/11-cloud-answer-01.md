## 1️⃣ Basic AWS Questions

### 1. What is Amazon Web Services?

**Answer:**

**Amazon Web Services**, or AWS, is a cloud computing platform provided by **Amazon**.
It offers services like computing power, storage, databases, networking, and AI over the internet.

Instead of buying and maintaining physical servers, companies can rent resources from AWS and pay only for what they use.

---

### 2. What are the main advantages of AWS cloud?

**Answer:**

Some main advantages of AWS are:

* **Cost-effective** – Pay only for the resources you use.
* **Scalability** – Easily increase or decrease resources based on demand.
* **High availability** – Services run across multiple data centers.
* **Security** – Provides strong security features and compliance.
* **Global infrastructure** – Services are available in many locations worldwide.

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

* **IaaS (Infrastructure as a Service)** – Provides virtual servers, storage, and networking. Example: **Amazon EC2**.
* **PaaS (Platform as a Service)** – Provides a platform to build and deploy applications without managing infrastructure. Example: **AWS Elastic Beanstalk**.
* **SaaS (Software as a Service)** – Software applications delivered over the internet. Example: **Google Workspace**.

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

**Example:** Client → API Gateway → Lambda → Response.

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

**Example:** User requests → Load Balancer → Multiple EC2 instances.

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
Client → API Gateway → Lambda → Response

API Gateway receives the request and triggers Lambda to process the request.

---

## 2️⃣ Compute Services

### 13. What is Amazon EC2?

**Answer:**

**Amazon EC2** stands for **Elastic Compute Cloud**.
It is a service provided by **Amazon Web Services** that allows users to launch virtual servers in the cloud.

With EC2, we can run applications without buying physical hardware.
We can choose different operating systems, configure CPU, memory, and storage, and scale resources based on demand.

---

### 14. What are different EC2 instance types?

**Answer:**

In **Amazon EC2**, instance types are different configurations of CPU, memory, storage, and networking.

Main categories include:

* **General Purpose** – Balanced compute, memory, and networking.
* **Compute Optimized** – High CPU performance for compute-heavy tasks.
* **Memory Optimized** – High RAM for memory-intensive applications.
* **Storage Optimized** – High disk throughput for large data processing.

Each instance type is designed for different workloads.

---

### 15. What is AWS Lambda?

**Answer:**

**AWS Lambda** is a **serverless computing service** from **Amazon Web Services**.

It allows developers to run code without managing servers.
The code runs automatically when triggered by events like file uploads, API requests, or database changes.

AWS automatically handles scaling, infrastructure, and server management.

---

### 16. What is Amazon ECS?

**Answer:**

**Amazon Elastic Container Service**, or ECS, is a container management service from **Amazon Web Services**.

It allows users to run and manage **Docker containers** on AWS easily.

ECS helps deploy, manage, and scale containerized applications without needing to manage complex container orchestration systems.

---

### 17. What is Amazon EKS?

**Answer:**

**Amazon Elastic Kubernetes Service**, or EKS, is a managed Kubernetes service provided by **Amazon Web Services**.

It allows users to run **Kubernetes clusters** on AWS without managing the control plane.

EKS makes it easier to deploy, manage, and scale containerized applications using Kubernetes.

---

## 3️⃣ Storage Services

### 18. What is Amazon S3?

**Answer:**

**Amazon S3** stands for **Simple Storage Service** provided by **Amazon Web Services**.

It is an **object storage service** used to store and retrieve any amount of data from anywhere on the internet.
Data in S3 is stored as **objects inside buckets**.

It is highly **scalable, durable, and secure**, and commonly used for backups, static website hosting, and data storage.

---

### 19. What are S3 storage classes?

**Answer:**

In **Amazon S3**, storage classes are different tiers designed for different access needs and costs.

Some common storage classes include:

* **S3 Standard** – For frequently accessed data
* **S3 Intelligent-Tiering** – Automatically moves data based on usage
* **S3 Standard-IA** – For infrequently accessed data
* **S3 Glacier** – For long-term archive storage

Each class helps optimize **cost and performance** depending on how often data is accessed.

---

### 20. What is Amazon EBS?

**Answer:**

**Amazon Elastic Block Store** or EBS is a **block storage service** used with **Amazon EC2** instances.

It works like a **virtual hard drive** that can be attached to EC2 servers to store operating systems, applications, and data.

EBS provides **high performance, durability, and persistent storage**.

---

### 21. What is Amazon EFS?

**Answer:**

**Amazon Elastic File System** or EFS is a **managed file storage service** provided by **Amazon Web Services**.

It allows multiple **EC2 instances** to access the same file system simultaneously.

EFS is scalable and commonly used for **shared storage, content management systems, and big data workloads**.

---

### 22. What is S3 Versioning?

**Answer:**

**S3 Versioning** in **Amazon S3** is a feature that keeps **multiple versions of an object** in a bucket.

When versioning is enabled, if a file is modified or deleted, the previous versions are still stored.

This helps with **data recovery, protection from accidental deletion, and tracking changes**.

---

## 4️⃣ Networking

### 23. What is Amazon VPC?

**Answer:**

**Amazon Virtual Private Cloud**, or VPC, is a service from **Amazon Web Services** that allows users to create a **private network in the AWS cloud**.

Inside a VPC, we can launch AWS resources like **EC2 instances**, define IP address ranges, create subnets, and configure routing and security.

It gives full control over the network environment.

---

### 24. What is a Subnet in VPC?

**Answer:**

A **Subnet** is a smaller network inside a **Amazon Virtual Private Cloud**.

It divides the VPC network into segments to organize and secure resources.

There are two main types:

* **Public Subnet** – Resources can access the internet
* **Private Subnet** – Resources cannot directly access the internet

Subnets help improve **security and network management**.

---

### 25. What is Elastic Load Balancing?

**Answer:**

**Elastic Load Balancing** is a service from **Amazon Web Services** that automatically distributes incoming traffic across multiple servers.

This improves **application availability, fault tolerance, and performance**.

If one server fails, the load balancer redirects traffic to healthy servers.

---

### 26. What is Amazon Route 53?

**Answer:**

**Amazon Route 53** is a **scalable Domain Name System (DNS) service** provided by **Amazon Web Services**.

It translates domain names like **example.com** into IP addresses so users can access websites.

Route 53 also supports **domain registration, traffic routing, and health checks**.

---

### 27. What is Security Group vs Network ACL?

**Answer:**

Both **Security Groups** and **Network ACLs** control network traffic in **Amazon Virtual Private Cloud**, but they work differently.

**Security Group**

* Works at the **instance level**
* Supports **only allow rules**
* It is **stateful** (responses are automatically allowed)

**Network ACL**

* Works at the **subnet level**
* Supports **allow and deny rules**
* It is **stateless** (rules must be defined for both directions)

---

## 5️⃣ Security

### 28. What is AWS Identity and Access Management (IAM)?

**Answer:**

**AWS Identity and Access Management**, or IAM, is a security service provided by **Amazon Web Services**.

It allows administrators to **securely control access to AWS resources**.

With IAM, we can create **users, groups, and roles**, and assign permissions to control who can access specific AWS services and resources.

---

### 29. What are IAM roles and policies?

**Answer:**

In **AWS Identity and Access Management**, **policies** are JSON documents that define **permissions**, such as what actions are allowed or denied on AWS resources.

An **IAM Role** is an identity that provides temporary permissions to access AWS services.

Roles are commonly used by **applications, EC2 instances, or AWS services** to securely access resources without storing credentials.

---

### 30. What is Multi-Factor Authentication (MFA) in AWS?

**Answer:**

**Multi-Factor Authentication (MFA)** in **AWS Identity and Access Management** adds an extra layer of security.

In addition to the username and password, the user must provide a **second authentication factor**, such as a code from a mobile authenticator app or hardware device.

This helps protect AWS accounts from **unauthorized access**.

---

### 31. What is AWS Key Management Service?

**Answer:**

**AWS Key Management Service**, or KMS, is a service from **Amazon Web Services** used to **create and manage encryption keys**.

It helps secure sensitive data by encrypting it using managed cryptographic keys.

Many AWS services like **Amazon S3** and **Amazon Elastic Block Store** can integrate with KMS for data encryption.

---

### 32. What is the Shared Responsibility Model in AWS?

**Answer:**

The **Shared Responsibility Model** in **Amazon Web Services** explains how **security responsibilities are shared between AWS and the customer**.

* **AWS is responsible for security of the cloud**, including physical data centers, hardware, and infrastructure.
* **Customers are responsible for security in the cloud**, such as managing access, configuring services, and protecting their data.

This model ensures both AWS and customers maintain cloud security.

---

## 6️⃣ Monitoring & DevOps

### 33. What is Amazon CloudWatch?

**Answer:**

**Amazon CloudWatch** is a monitoring service provided by **Amazon Web Services**.

It collects and tracks **metrics, logs, and events** from AWS resources and applications.

CloudWatch helps monitor performance, set alarms, and automatically respond to changes in the system.

---

### 34. What is AWS CloudTrail?

**Answer:**

**AWS CloudTrail** is a service that records **all API calls and user activities** in an AWS account.

It helps track **who accessed which resource and what actions were performed**.

CloudTrail is mainly used for **security monitoring, auditing, and compliance**.

---

### 35. What is AWS CodePipeline?

**Answer:**

**AWS CodePipeline** is a continuous integration and continuous delivery (CI/CD) service from **Amazon Web Services**.

It automates the process of **building, testing, and deploying applications** whenever there is a code change.

This helps developers deliver updates quickly and reliably.

---

### 36. What is Auto Scaling in AWS?

**Answer:**

**AWS Auto Scaling** automatically adjusts the number of resources based on application demand.

For example, it can automatically increase or decrease **Amazon EC2** instances depending on traffic.

This improves **performance, availability, and cost efficiency**.

---

### 37. What is Infrastructure as Code in AWS?

**Answer:**

**Infrastructure as Code (IaC)** is the practice of managing and provisioning infrastructure using **code instead of manual configuration**.

In **Amazon Web Services**, tools like **AWS CloudFormation** allow users to define infrastructure using templates.

This makes infrastructure **automated, repeatable, and version-controlled**.

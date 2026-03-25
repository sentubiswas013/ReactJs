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


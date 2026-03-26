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


## 🎯 Top 10 AWS Services — Interview Explanation with Code

### 1. 🖥️ Amazon EC2 — Virtual Servers

**What it is:**
EC2 (Elastic Compute Cloud) provides resizable virtual machines in the cloud. You choose OS, CPU, RAM, and storage.

**Interview Answer:**
> EC2 is a virtual server on AWS. I use it to host Java Spring Boot applications. It supports auto-scaling and load balancing.

**Steps:**
1. Go to AWS Console → EC2 → Launch Instance
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

### 2. 📦 Amazon S3 — Object Storage

**What it is:**
S3 (Simple Storage Service) stores files, images, backups, and static websites. Highly durable (99.999999999%).

**Interview Answer:**
> I use S3 to store user-uploaded files and serve static React frontends. It integrates with CloudFront for CDN.

**Steps:**
1. Go to S3 → Create Bucket
2. Upload files
3. Set bucket policy for public access (if static site)
4. Enable static website hosting

```java
// Spring Boot — Upload file to S3
@Autowired
AmazonS3 s3Client;

public String uploadFile(MultipartFile file) throws IOException {
    String fileName = file.getOriginalFilename();
    s3Client.putObject("my-bucket", fileName, file.getInputStream(), new ObjectMetadata());
    return "https://my-bucket.s3.amazonaws.com/" + fileName;
}
```

```bash
# AWS CLI — Upload file
aws s3 cp myfile.txt s3://my-bucket/
```

---

### 3. 🗄️ Amazon RDS — Managed Database

**What it is:**
RDS (Relational Database Service) is a managed database service supporting MySQL, PostgreSQL, Oracle, SQL Server.

**Interview Answer:**
> I use RDS with MySQL for production databases. AWS handles backups, patching, and multi-AZ failover automatically.

**Steps:**
1. Go to RDS → Create Database
2. Choose MySQL / PostgreSQL
3. Set DB name, username, password
4. Configure VPC and security group (port 3306)
5. Connect from Spring Boot

```yaml
# application.yml — Spring Boot RDS connection
spring:
  datasource:
    url: jdbc:mysql://<RDS-ENDPOINT>:3306/mydb
    username: admin
    password: <password>
    driver-class-name: com.mysql.cj.jdbc.Driver
```

---

### 4. ⚡ AWS Lambda — Serverless Functions

**What it is:**
Lambda runs code without provisioning servers. Triggered by events (API Gateway, S3, SQS). Pay per execution.

**Interview Answer:**
> I use Lambda for event-driven tasks like processing S3 file uploads or handling API requests. No server management needed.

**Steps:**
1. Go to Lambda → Create Function
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

### 5. 🌐 Amazon API Gateway — REST API Manager

**What it is:**
API Gateway creates, publishes, and secures REST/HTTP APIs. Acts as the front door to Lambda or EC2.

**Interview Answer:**
> I use API Gateway to expose Lambda functions as REST endpoints. It handles throttling, auth, and CORS.

**Steps:**
1. Go to API Gateway → Create API → REST API
2. Create Resource (e.g., `/users`)
3. Create Method (GET, POST)
4. Integrate with Lambda
5. Deploy to a Stage (dev/prod)

```
Flow:
Client → https://api.execute-api.us-east-1.amazonaws.com/prod/users
       → API Gateway
       → Lambda (MyHandler)
       → Response
```

```bash
# Test API
curl -X GET https://<api-id>.execute-api.us-east-1.amazonaws.com/prod/users
```

---

### 6. 🚀 Amazon CloudFront — CDN

**What it is:**
CloudFront is a Content Delivery Network that caches content at edge locations worldwide for fast delivery.

**Interview Answer:**
> I use CloudFront in front of S3 to serve React static files globally with low latency and HTTPS support.

**Steps:**
1. Go to CloudFront → Create Distribution
2. Set Origin = S3 bucket or EC2
3. Configure cache behavior
4. Deploy — get a CloudFront URL

```
Flow:
User (India) → CloudFront Edge (Mumbai) → Cache Hit → Fast Response
User (India) → CloudFront Edge (Mumbai) → Cache Miss → Fetch from S3 (US)
```

---

### 7. 🐳 Amazon ECS / ☸️ EKS — Container Services

**What it is:**
- ECS = Elastic Container Service (AWS-native Docker orchestration)
- EKS = Elastic Kubernetes Service (managed Kubernetes)

**Interview Answer:**
> I use ECS with Fargate to run Docker containers without managing EC2. For complex microservices, EKS with Kubernetes is preferred.

**Steps (ECS):**
1. Create Docker image → Push to ECR
2. Create ECS Cluster
3. Create Task Definition (Docker config)
4. Create Service → Run tasks

```dockerfile
# Dockerfile — Spring Boot
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

### 8. 🔐 AWS IAM — Identity & Access Management

**What it is:**
IAM manages users, groups, roles, and permissions. Controls who can access what in AWS.

**Interview Answer:**
> I use IAM roles to give EC2 or Lambda permission to access S3 or RDS without hardcoding credentials.

**Steps:**
1. Go to IAM → Create Role
2. Choose trusted entity (EC2 / Lambda)
3. Attach policy (e.g., AmazonS3FullAccess)
4. Assign role to EC2 or Lambda

```json
// IAM Policy — Allow S3 read access
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

### 9. 📊 Amazon CloudWatch — Monitoring & Logs

**What it is:**
CloudWatch collects logs, metrics, and events. Set alarms to notify when something goes wrong.

**Interview Answer:**
> I use CloudWatch to monitor Lambda execution errors, EC2 CPU usage, and set alarms for auto-scaling triggers.

**Steps:**
1. Go to CloudWatch → Log Groups (auto-created for Lambda)
2. Create Metric Filter
3. Create Alarm (e.g., CPU > 80%)
4. Set SNS notification

```java
// Spring Boot — Send custom metric to CloudWatch
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

### 10. ⚙️ AWS Elastic Beanstalk — Easy App Deployment

**What it is:**
Elastic Beanstalk deploys and manages applications automatically. You just upload code — AWS handles EC2, load balancer, scaling.

**Interview Answer:**
> I use Elastic Beanstalk to deploy Spring Boot apps quickly. It provisions EC2, sets up load balancing, and handles scaling automatically.

**Steps:**
1. Build JAR: `mvn clean package`
2. Go to Elastic Beanstalk → Create Application
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


---

## 🔄 CI/CD Complete Pipeline — Code Push to Deployment

---

### Pipeline Overview

```
STEP 1 — Write code, git push to GitHub
STEP 2 — GitHub Actions triggers automatically
STEP 3 — Run Tests  (mvn test)
STEP 4 — Build JAR  (mvn package)
STEP 5 — Build Docker Image  (Dockerfile)
STEP 6 — Push Docker Image to AWS ECR
STEP 7 — Deploy to AWS ECS Fargate
STEP 8 — App is LIVE 🚀
```

---

### Project Files

```
my-springboot-app/
├── src/main/java/com/example/HelloController.java
├── pom.xml
├── Dockerfile
├── taskdef.json
└── .github/workflows/cicd.yml
```

---

### STEP 1 — Spring Boot Code

```java
// HelloController.java
@RestController
public class HelloController {
    @GetMapping("/")
    public String hello() {
        return "Hello from CI/CD Pipeline!";
    }
}
```

```xml
<!-- pom.xml -->
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>3.2.0</version>
</parent>
<groupId>com.example</groupId>
<artifactId>myapp</artifactId>
<version>1.0.0</version>
```

---

### STEP 2 — Dockerfile

Stage 1 builds the JAR. Stage 2 runs it. Keeps the final image small.

```dockerfile
# Stage 1 — Build
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2 — Run
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/myapp-1.0.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

---

### STEP 3 — One-Time AWS Setup (run once before first deploy)

```bash
# 1. Create ECR repo to store Docker images
aws ecr create-repository --repository-name my-springboot-app --region us-east-1

# 2. Create ECS Cluster
aws ecs create-cluster --cluster-name my-cluster

# 3. Register Task Definition (tells ECS how to run the container)
aws ecs register-task-definition --cli-input-json file://taskdef.json

# 4. Create ECS Service (keeps container running)
aws ecs create-service \
  --cluster my-cluster \
  --service-name my-service \
  --task-definition my-task \
  --desired-count 1 \
  --launch-type FARGATE \
  --network-configuration "awsvpcConfiguration={subnets=[subnet-xxxx],securityGroups=[sg-xxxx],assignPublicIp=ENABLED}"
```

---

### STEP 4 — ECS Task Definition (taskdef.json)

Tells ECS: which image to run, which port, how much CPU/memory.

```json
{
  "family": "my-task",
  "networkMode": "awsvpc",
  "requiresCompatibilities": ["FARGATE"],
  "cpu": "512",
  "memory": "1024",
  "executionRoleArn": "arn:aws:iam::<account-id>:role/ecsTaskExecutionRole",
  "containerDefinitions": [
    {
      "name": "my-container",
      "image": "<account-id>.dkr.ecr.us-east-1.amazonaws.com/my-springboot-app:latest",
      "portMappings": [
        { "containerPort": 8080, "protocol": "tcp" }
      ],
      "logConfiguration": {
        "logDriver": "awslogs",
        "options": {
          "awslogs-group": "/ecs/my-task",
          "awslogs-region": "us-east-1",
          "awslogs-stream-prefix": "ecs"
        }
      }
    }
  ]
}
```

---

### STEP 5 — Add GitHub Secrets

Go to: GitHub Repo → Settings → Secrets → Actions → New secret

| Secret Name | Value |
|---|---|
| `AWS_ACCESS_KEY_ID` | IAM user access key |
| `AWS_SECRET_ACCESS_KEY` | IAM user secret key |

> IAM user must have: `AmazonECR_FullAccess` + `AmazonECS_FullAccess`

---

### STEP 6 — GitHub Actions Pipeline (.github/workflows/cicd.yml)

This file runs automatically on every `git push` to `main`.
3 jobs run in order: **build → docker → deploy**

```yaml
name: CI/CD Pipeline

on:
  push:
    branches: [ main ]

env:
  AWS_REGION: us-east-1
  ECR_REPOSITORY: my-springboot-app
  ECS_CLUSTER: my-cluster
  ECS_SERVICE: my-service
  CONTAINER_NAME: my-container

jobs:

  # JOB 1 — Run Tests and Build JAR
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3

      - uses: actions/setup-java@v3
        with:
          java-version: '17'
          distribution: 'temurin'

      - name: Run tests
        run: mvn test

      - name: Build JAR
        run: mvn clean package -DskipTests

      - name: Save JAR for next job
        uses: actions/upload-artifact@v3
        with:
          name: app-jar
          path: target/*.jar

  # JOB 2 — Build Docker Image and Push to ECR
  docker:
    runs-on: ubuntu-latest
    needs: build
    outputs:
      image: ${{ steps.push.outputs.image }}
    steps:
      - uses: actions/checkout@v3

      - name: Download JAR from JOB 1
        uses: actions/download-artifact@v3
        with:
          name: app-jar
          path: target/

      - name: Login to AWS
        uses: aws-actions/configure-aws-credentials@v2
        with:
          aws-access-key-id: ${{ secrets.AWS_ACCESS_KEY_ID }}
          aws-secret-access-key: ${{ secrets.AWS_SECRET_ACCESS_KEY }}
          aws-region: ${{ env.AWS_REGION }}

      - name: Login to ECR
        id: login-ecr
        uses: aws-actions/amazon-ecr-login@v1

      - name: Build and push Docker image
        id: push
        env:
          REGISTRY: ${{ steps.login-ecr.outputs.registry }}
          TAG: ${{ github.sha }}
        run: |
          docker build -t $REGISTRY/$ECR_REPOSITORY:$TAG .
          docker push $REGISTRY/$ECR_REPOSITORY:$TAG
          echo "image=$REGISTRY/$ECR_REPOSITORY:$TAG" >> $GITHUB_OUTPUT

  # JOB 3 — Deploy new image to ECS
  deploy:
    runs-on: ubuntu-latest
    needs: docker
    steps:
      - uses: actions/checkout@v3

      - name: Login to AWS
        uses: aws-actions/configure-aws-credentials@v2
        with:
          aws-access-key-id: ${{ secrets.AWS_ACCESS_KEY_ID }}
          aws-secret-access-key: ${{ secrets.AWS_SECRET_ACCESS_KEY }}
          aws-region: ${{ env.AWS_REGION }}

      - name: Get current ECS task definition
        run: |
          aws ecs describe-task-definition \
            --task-definition my-task \
            --query taskDefinition > taskdef.json

      - name: Update task definition with new image
        id: task-def
        uses: aws-actions/amazon-ecs-render-task-definition@v1
        with:
          task-definition: taskdef.json
          container-name: ${{ env.CONTAINER_NAME }}
          image: ${{ needs.docker.outputs.image }}

      - name: Deploy to ECS
        uses: aws-actions/amazon-ecs-deploy-task-definition@v1
        with:
          task-definition: ${{ steps.task-def.outputs.task-definition }}
          service: ${{ env.ECS_SERVICE }}
          cluster: ${{ env.ECS_CLUSTER }}
          wait-for-service-stability: true
```

---

### STEP 7 — Push Code and Watch It Deploy

```bash
git add .
git commit -m "new feature"
git push origin main
# GitHub Actions starts automatically
```

Watch it: GitHub Repo → Actions tab → see each job running live

---

### What Happens End to End

```
git push origin main
    |
    |-- GitHub Actions starts
          |
          |-- JOB 1: mvn test  -->  mvn package  -->  saves myapp.jar
          |
          |-- JOB 2: docker build  -->  docker push  -->  image stored in ECR
          |
          |-- JOB 3: update ECS task def  -->  ECS pulls new image  -->  rolling deploy
                                                                              |
                                                                         App LIVE 🚀
```

---

### Interview Answer

> I set up CI/CD using GitHub Actions with 3 jobs.
> On every git push to main:
> - JOB 1 runs Maven tests and builds the JAR
> - JOB 2 builds a Docker image using a multi-stage Dockerfile and pushes it to AWS ECR tagged with the commit SHA
> - JOB 3 updates the ECS task definition with the new image and triggers a rolling deployment on ECS Fargate
>
> This gives zero-downtime deployments — every code push is automatically tested and deployed.

---

### CI/CD Tools Comparison

| Tool | Use Case |
|---|---|
| GitHub Actions | CI/CD for GitHub repos — free & simple |
| AWS CodePipeline | Native AWS CI/CD pipeline |
| AWS CodeBuild | Build & test stage (AWS-native) |
| AWS CodeDeploy | Deploy to EC2 / ECS / Lambda |
| Jenkins | Self-hosted CI/CD server |
| GitLab CI | CI/CD for GitLab repos |

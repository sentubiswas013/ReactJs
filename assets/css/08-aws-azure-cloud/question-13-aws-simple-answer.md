## AWS Core Services

### 1. What is AWS, and how is it used for deployment?

**Answer:**  
AWS (Amazon Web Services) is a cloud computing platform provided by Amazon that offers a wide range of services, including compute power, storage options, and networking capabilities. AWS enables businesses to deploy and manage applications without the need for physical hardware. Deployment in AWS can be done through services such as EC2 (for compute), S3 (for storage), RDS (for relational databases), Lambda (for serverless functions), Elastic Beanstalk (for managing environments), and CodePipeline/CodeDeploy (for CI/CD).

### 2. What is AWS Elastic Beanstalk, and how does it help in application deployment?

**Answer:**  
AWS Elastic Beanstalk is a Platform as a Service (PaaS) that simplifies the deployment of applications by automatically handling the provisioning, load balancing, scaling, and monitoring. You can deploy your web applications (e.g., in Java, .NET, Node.js, Python, etc.) to Elastic Beanstalk with minimal configuration.

**Key Features:**
- Auto-scaling and load balancing
- Simple management through AWS Management Console, CLI, or API
- Supports multiple programming languages and frameworks
- Provides monitoring via CloudWatch

---

## Deployment & CI/CD

### 3. What is Continuous Deployment/Continuous Integration (CI/CD) in AWS?

**Answer:**
CI/CD is a method to automate the software deployment pipeline, making the deployment process faster and more reliable.

- **Continuous Integration (CI):** Developers integrate code changes into a shared repository frequently. Tools like AWS CodeCommit and AWS CodeBuild are used.
- **Continuous Deployment (CD):** Automates the release process, ensuring that code changes passing automated tests are deployed to production without manual intervention.

**Benefits:**
- Faster development cycles
- Reduced human errors
- Higher confidence in code releases

### 4. How does AWS CodeDeploy work in deployment automation?

**Answer:**  
AWS CodeDeploy is a fully managed deployment service that automates software deployments to EC2 instances, Lambda functions, and on-premise servers.

**Key Features:**
- **Deployment strategies:** Rolling updates, blue/green deployments, and canary deployments
- **Monitoring and rollback:** Provides detailed deployment logs and automatic rollback in case of failure
- **Integration:** Works with CodeCommit, CodeBuild, and third-party tools like GitHub

### 5. What is the difference between a rolling update and a blue/green deployment in AWS?

**Answer:**
- **Rolling Update:** New versions are deployed gradually across instances. Minimizes downtime but can result in partial application versions running during the update.
- **Blue/Green Deployment:** Involves deploying a new version (green) alongside the current version (blue). Once ready, traffic is switched to the green version, allowing easy rollback.

---

## Compute Services

### 6. What is the difference between EC2 and Lambda in terms of deployment?

**Answer:**
- **Amazon EC2:** Infrastructure as a Service (IaaS) providing virtual servers. Suitable for long-running applications requiring complete OS control.
  - Use case: Hosting websites, enterprise applications, custom server setups
- **AWS Lambda:** Serverless compute service running code without managing servers. Automatically scales based on events.
  - Use case: Backend functions, event processing, microservices

---

## Infrastructure as Code

### 7. What are AWS CloudFormation and its role in deployment?

**Answer:**  
AWS CloudFormation is an Infrastructure as Code (IaC) service that allows you to define and provision AWS infrastructure using JSON or YAML templates.

**Key Features:**
- **Declarative approach:** Specify desired resources, CloudFormation provisions them
- **Version control:** Templates can be stored in version control systems
- **Automated rollback:** Automatic rollback to previous state if deployment fails
- **Infrastructure automation:** Easy replication of environments (dev, staging, production)

---

## Networking & DNS

### 8. What is the purpose of Amazon Route 53 in the deployment process?

**Answer:**  
Amazon Route 53 is a scalable DNS web service for routing internet traffic to resources like EC2 instances, load balancers, or S3 buckets.

**Key Features:**
- **DNS management:** Routes user requests to appropriate resources
- **Health checks:** Monitors resource health and routes traffic away from unhealthy resources
- **DNS failover:** Provides high availability by directing traffic to healthy endpoints
- **Domain registration:** Manages domain names and DNS records

---

## High Availability & Scaling

### 9. What is an Auto Scaling Group, and how does it assist in application deployment?

**Answer:**  
An Auto Scaling Group (ASG) automatically adjusts the number of EC2 instances based on demand, ensuring optimal resource utilization.

**Key Features:**
- **Automatic Scaling:** Increases/decreases instances based on traffic or performance metrics
- **Health Checks:** Automatically replaces unhealthy instances
- **Integration with ELB:** Works with Elastic Load Balancing to distribute traffic evenly

### 10. How do you ensure high availability during deployment in AWS?

**Answer:**
- **Multiple Availability Zones:** Deploy instances across different AZs
- **Elastic Load Balancer:** Distribute traffic across multiple EC2 instances
- **Auto Scaling:** Automatically adjust instances based on demand
- **Backup and Recovery:** Use RDS Multi-AZ, configure snapshots for EC2 and EBS

---

## Node.js on AWS

### 11. How do you deploy a Node.js application to AWS?

**Answer:**
Several deployment methods:

- **EC2 Instances:** Deploy on virtual machines with full control
- **Elastic Beanstalk:** PaaS solution automating infrastructure management
- **AWS Lambda:** Serverless deployment for event-driven applications

**EC2 Deployment Steps:**
1. Launch EC2 instance
2. Install Node.js and npm
3. Upload application files
4. Install dependencies with `npm install`
5. Configure application (environment variables, ports)
6. Run with process manager (PM2 or Forever)
7. Configure load balancer for high availability

### 12. What is the best way to manage environment variables in AWS for a Node.js app?

**Answer:**
- **Elastic Beanstalk:** Configure through console under Environment Properties
- **Lambda:** Set environment variables in function configuration
- **EC2:** Use `.env` files or AWS Systems Manager Parameter Store
- **Secrets Manager:** Store sensitive data securely and retrieve programmatically

### 13. How do you manage database connections in AWS for a Node.js application?

**Answer:**
- **Amazon RDS:** Use database drivers (mysql2, pg, sequelize) to connect to RDS instances
- **DynamoDB:** Use AWS SDK to interact with NoSQL database
- **Security:** Store credentials in Secrets Manager or Parameter Store
- **Connection Pooling:** Implement proper connection management for performance

---

## Angular & Full-Stack Deployment

### 14. How do you deploy an Angular application to AWS?

**Answer:**
Deploy Angular as static assets:

1. **Build:** Run `ng build --prod` to generate static files
2. **S3 Bucket:** Create bucket and enable static website hosting
3. **Upload:** Upload dist/ folder contents to S3
4. **CloudFront:** Create distribution for CDN and better performance
5. **DNS:** Configure Route 53 for custom domain (optional)

### 15. How can you deploy a full-stack Angular and Node.js application on AWS?

**Answer:**
- **Frontend (Angular):** Deploy to S3 + CloudFront for static hosting
- **Backend (Node.js):** Deploy to Elastic Beanstalk, EC2, or Lambda
- **Integration:** Configure Angular API requests to point to backend endpoints
- **API Gateway:** Use for managing backend API endpoints robustly

---

## Security & Environment Management

### 16. How do you secure the connection between Angular frontend and Node.js backend on AWS?

**Answer:**
1. **HTTPS:** Use SSL/TLS encryption via CloudFront and ELB
2. **API Gateway:** Implement authorization, throttling, and CORS
3. **IAM Roles:** Secure access between AWS services
4. **Environment Variables:** Store credentials securely in Secrets Manager
5. **WAF:** Use Web Application Firewall for protection against exploits

---

## Monitoring & Troubleshooting

### 17. How do you monitor and troubleshoot Node.js applications in AWS?

**Answer:**
- **CloudWatch Logs:** Capture and analyze application logs
- **CloudWatch Alarms:** Monitor metrics and set up notifications
- **AWS X-Ray:** Application performance monitoring and tracing
- **CloudTrail:** Log and monitor API calls for auditing
- **ELB Logs:** Monitor load balancer access logs

### 18. How can you monitor a full-stack Angular + Node.js application on AWS?

**Answer:**
- **CloudWatch:** Monitor both frontend (via CloudFront logs) and backend metrics
- **X-Ray:** Trace requests across frontend and backend services
- **Custom Logging:** Implement application-specific logging using winston or bunyan
- **Performance Monitoring:** Track CPU, memory, request count, and response times
- **Error Tracking:** Set up alerts for application errors and performance degradation

---

## Additional Best Practices

### 19. What are the key considerations for cost optimization in AWS deployments?

**Answer:**
- **Right-sizing:** Choose appropriate instance types and sizes
- **Auto Scaling:** Scale resources based on actual demand
- **Reserved Instances:** Use for predictable workloads
- **Spot Instances:** Use for fault-tolerant applications
- **S3 Storage Classes:** Choose appropriate storage classes for different data access patterns
- **CloudWatch:** Monitor usage and set up billing alerts

### 20. How do you implement disaster recovery for AWS applications?

**Answer:**
- **Multi-AZ Deployments:** Deploy across multiple Availability Zones
- **Cross-Region Replication:** Replicate critical data across regions
- **Automated Backups:** Configure regular snapshots and backups
- **RTO/RPO Planning:** Define Recovery Time and Recovery Point Objectives
- **Testing:** Regularly test disaster recovery procedures
- **Documentation:** Maintain updated disaster recovery runbooks
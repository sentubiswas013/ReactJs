# 🔵 24. DevOps and Build Tools
---
# 🔹 Build Tools

### Question 361: What is Maven?

**Answer (30 seconds):**
* Build automation and project management tool for Java projects
* **POM**: Project Object Model (pom.xml) defines project structure
* **Dependencies**: Automatic dependency management from central repository
* **Lifecycle**: Standard build phases (compile, test, package, deploy)
* **Plugins**: Extensible through plugins for various tasks
* **Convention**: Convention over configuration approach

```xml
<!-- pom.xml example -->
<project>
    <groupId>com.example</groupId>
    <artifactId>my-app</artifactId>
    <version>1.0.0</version>
    <packaging>jar</packaging>
    
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
            <version>2.7.0</version>
        </dependency>
    </dependencies>
    
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>
```

---

### Question 362: What is Gradle?

**Answer (30 seconds):**
* Modern build automation tool that combines Maven and Ant features
* **Groovy/Kotlin DSL**: More flexible than XML configuration
* **Performance**: Faster builds with incremental compilation and caching
* **Multi-project**: Better support for multi-module projects
* **Dependency Management**: Compatible with Maven repositories
* **Customization**: Highly customizable build scripts

```gradle
// build.gradle example
plugins {
    id 'java'
    id 'org.springframework.boot' version '2.7.0'
}

group = 'com.example'
version = '1.0.0'
sourceCompatibility = '17'

repositories {
    mavenCentral()
}

dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-web'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
}

tasks.named('test') {
    useJUnitPlatform()
}
```

---

### Question 363: What is the difference between Maven and Gradle?

**Answer (35 seconds):**
* **Configuration**: Maven uses XML, Gradle uses Groovy/Kotlin DSL
* **Performance**: Gradle is faster with incremental builds and build cache
* **Flexibility**: Gradle more flexible, Maven more standardized
* **Learning Curve**: Maven easier to learn, Gradle more powerful
* **Multi-project**: Gradle better for complex multi-module projects
* **Ecosystem**: Maven has larger ecosystem, Gradle growing rapidly

```xml
<!-- Maven dependency -->
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.13.2</version>
    <scope>test</scope>
</dependency>
```

```gradle
// Gradle dependency
testImplementation 'junit:junit:4.13.2'

// Gradle custom task
task customTask {
    doLast {
        println 'Custom build logic'
    }
}
```

# 🔹 CI/CD and DevOp

### Question 364: What is continuous integration?

**Answer (35 seconds):**
* Development practice of frequently integrating code changes into shared repository
* **Automated Builds**: Every commit triggers automated build and test
* **Early Detection**: Catch integration issues and bugs early
* **Fast Feedback**: Developers get quick feedback on code changes
* **Quality Gates**: Automated tests must pass before integration
* **Tools**: Jenkins, GitLab CI, GitHub Actions, Azure DevOps

```yaml
# GitHub Actions CI example
name: CI Pipeline
on: [push, pull_request]

jobs:
  test:
    runs-on: ubuntu-latest
    steps:
    - uses: actions/checkout@v2
    - name: Set up JDK 17
      uses: actions/setup-java@v2
      with:
        java-version: '17'
    - name: Run tests
      run: ./mvnw test
    - name: Build application
      run: ./mvnw package
```

---

### Question 365: What is continuous deployment?

**Answer (30 seconds):**
* Automated deployment of code changes to production after passing all tests
* **Fully Automated**: No manual intervention in deployment process
* **Fast Delivery**: Features reach users quickly
* **Risk Mitigation**: Small, frequent deployments reduce risk
* **Rollback**: Quick rollback capabilities for issues
* **Prerequisites**: Requires robust testing, monitoring, and automation

```yaml
# CD Pipeline example
deploy:
  stage: deploy
  script:
    - docker build -t myapp:$CI_COMMIT_SHA .
    - docker push registry.com/myapp:$CI_COMMIT_SHA
    - kubectl set image deployment/myapp myapp=registry.com/myapp:$CI_COMMIT_SHA
  only:
    - main
  when: manual  # or 'on_success' for full automation
```

---

### Question 366: What is Jenkins?

**Answer (30 seconds):**
* Open-source automation server for CI/CD pipelines
* **Pipeline as Code**: Jenkinsfile defines build pipeline
* **Plugins**: Extensive plugin ecosystem for integrations
* **Distributed Builds**: Master-slave architecture for scalability
* **Web Interface**: User-friendly web-based configuration
* **Integration**: Integrates with Git, Maven, Docker, Kubernetes

```groovy
// Jenkinsfile example
pipeline {
    agent any
    
    stages {
        stage('Build') {
            steps {
                sh './mvnw clean compile'
            }
        }
        stage('Test') {
            steps {
                sh './mvnw test'
            }
        }
        stage('Package') {
            steps {
                sh './mvnw package'
                archiveArtifacts artifacts: 'target/*.jar'
            }
        }
        stage('Deploy') {
            when { branch 'main' }
            steps {
                sh 'docker build -t myapp .'
                sh 'kubectl apply -f k8s/'
            }
        }
    }
}
```

---

### Question 367: What is Git?

**Answer (30 seconds):**
* Distributed version control system for tracking code changes
* **Distributed**: Every developer has complete project history
* **Branching**: Lightweight branching and merging capabilities
* **Performance**: Fast operations for most commands
* **Integrity**: Cryptographic hashing ensures data integrity
* **Collaboration**: Enables team collaboration through remote repositories

```bash
# Basic Git commands
git init                          # Initialize repository
git add .                         # Stage changes
git commit -m "Add new feature"   # Commit changes
git branch feature-branch         # Create branch
git checkout feature-branch       # Switch branch
git merge feature-branch          # Merge branch
git push origin main              # Push to remote
git pull origin main              # Pull from remote
```

---

### Question 368: What is version control?

**Answer (25 seconds):**
* System for tracking and managing changes to files over time
* **History**: Complete history of all changes and versions
* **Collaboration**: Multiple developers can work on same project
* **Branching**: Parallel development streams
* **Backup**: Distributed copies serve as backups
* **Rollback**: Ability to revert to previous versions
* **Types**: Centralized (SVN) vs Distributed (Git)

```bash
# Version control workflow
git status                    # Check current state
git log --oneline            # View commit history
git diff HEAD~1              # Compare with previous version
git checkout HEAD~2 -- file.java  # Restore file from 2 commits ago
git tag v1.0.0               # Tag release version
git revert abc123            # Revert specific commit
```

---

### Question 369: What is infrastructure as code?

**Answer (35 seconds):**
* Managing and provisioning infrastructure through code rather than manual processes
* **Declarative**: Define desired state, tools ensure it's achieved
* **Version Control**: Infrastructure changes tracked like application code
* **Reproducible**: Consistent environments across dev, test, production
* **Automation**: Automated provisioning and configuration
* **Tools**: Terraform, CloudFormation, Ansible, Kubernetes manifests

```yaml
# Kubernetes deployment (IaC)
apiVersion: apps/v1
kind: Deployment
metadata:
  name: java-app
spec:
  replicas: 3
  selector:
    matchLabels:
      app: java-app
  template:
    spec:
      containers:
      - name: app
        image: myapp:1.0.0
        ports:
        - containerPort: 8080
        env:
        - name: DATABASE_URL
          value: "jdbc:postgresql://db:5432/mydb"
```

```hcl
# Terraform example
resource "aws_instance" "web" {
  ami           = "ami-0c55b159cbfafe1d0"
  instance_type = "t2.micro"
  
  tags = {
    Name = "JavaApp"
  }
}
```

---

### Question 370: What is deployment strategies?

**Answer (35 seconds):**
* Different approaches for releasing applications to production
* **Rolling Deployment**: Gradually replace old instances with new ones
* **Blue-Green**: Switch between two identical environments
* **Canary**: Deploy to small subset of users first
* **A/B Testing**: Compare different versions with user groups
* **Recreate**: Stop old version, start new version (downtime)
* **Shadow**: Route copy of traffic to new version for testing

```yaml
# Rolling deployment strategy
apiVersion: apps/v1
kind: Deployment
spec:
  strategy:
    type: RollingUpdate
    rollingUpdate:
      maxUnavailable: 1
      maxSurge: 1
  replicas: 5
  template:
    spec:
      containers:
      - name: app
        image: myapp:v2.0.0
```

---

### Question 371: What is blue-green deployment?

**Answer (30 seconds):**
* Deployment strategy using two identical production environments
* **Blue**: Current live environment serving users
* **Green**: New environment with updated application
* **Switch**: Instant switch from blue to green after validation
* **Zero Downtime**: No service interruption during deployment
* **Quick Rollback**: Instant rollback by switching back to blue
* **Resource Cost**: Requires double the infrastructure resources

```yaml
# Blue-Green deployment with Kubernetes
# Blue environment (current)
apiVersion: v1
kind: Service
metadata:
  name: app-service
spec:
  selector:
    app: myapp
    version: blue  # Currently pointing to blue
  ports:
  - port: 80
    targetPort: 8080

---
# Green deployment (new version)
apiVersion: apps/v1
kind: Deployment
metadata:
  name: myapp-green
spec:
  replicas: 3
  selector:
    matchLabels:
      app: myapp
      version: green
  template:
    metadata:
      labels:
        app: myapp
        version: green
    spec:
      containers:
      - name: app
        image: myapp:v2.0.0
```

---

### Question 372: What is canary deployment?

**Answer (35 seconds):**
* Deployment strategy that releases new version to small subset of users first
* **Gradual Rollout**: Start with 5-10% of traffic, gradually increase
* **Risk Mitigation**: Limit blast radius of potential issues
* **Monitoring**: Monitor metrics and user feedback during rollout
* **Automated Rollback**: Automatic rollback if metrics degrade
* **A/B Testing**: Can be combined with A/B testing for feature validation
* **Traffic Splitting**: Use load balancers or service mesh for traffic control

```yaml
# Canary deployment with Istio
apiVersion: networking.istio.io/v1alpha3
kind: VirtualService
metadata:
  name: myapp
spec:
  http:
  - match:
    - headers:
        canary:
          exact: "true"
    route:
    - destination:
        host: myapp
        subset: v2
  - route:
    - destination:
        host: myapp
        subset: v1
      weight: 90  # 90% to stable version
    - destination:
        host: myapp
        subset: v2
      weight: 10  # 10% to canary version
```

```java
// Feature flag for canary deployment
@RestController
public class UserController {
    
    @Autowired
    private FeatureToggleService featureToggle;
    
    @GetMapping("/users")
    public List<User> getUsers() {
        if (featureToggle.isEnabled("new-user-api", getCurrentUser())) {
            return newUserService.getUsers(); // Canary version
        }
        return userService.getUsers(); // Stable version
    }
}
```

# ✅ 18) Git & CI/CD Basics

## 214. Explain Git branching strategies (GitFlow, trunk-based).

**GitFlow**: Uses multiple long-lived branches (main, develop) with feature, release, and hotfix branches for structured releases.

**Trunk-based**: Developers commit directly to main/trunk frequently with short-lived feature branches (< 1 day).

**Example:**
```bash
# GitFlow
git checkout -b feature/login develop
git checkout -b release/1.0 develop

# Trunk-based
git checkout -b feature/login main
# Merge within hours/1 day
```

---

## 215. `git merge` vs `git rebase` - when to use which?

**git merge**: Combines branches preserving history with a merge commit.

**git rebase**: Rewrites history by moving commits on top of another branch, creating linear history.

**Example:**
```bash
# Merge - preserves history
git checkout main
git merge feature/login

# Rebase - linear history
git checkout feature/login
git rebase main
```

**Use merge** for public branches, **use rebase** for cleaning local history before pushing.

---

## 216. How do you resolve merge conflicts?

Conflicts occur when same lines are modified in different branches. Manually edit files, choose changes, then commit.

**Example:**
```bash
git merge feature/login
# Conflict occurs

# Edit conflicted file
<<<<<<< HEAD
String name = "John";
=======
String name = "Jane";
>>>>>>> feature/login

# Choose one or combine
String name = "John";

git add .
git commit -m "Resolved conflict"
```

---

## 217. What is CI/CD pipeline?

**CI (Continuous Integration)**: Automatically build and test code on every commit.

**CD (Continuous Deployment)**: Automatically deploy tested code to production.

**Example:**
```yaml
# .gitlab-ci.yml
stages:
  - build
  - test
  - deploy

build:
  stage: build
  script:
    - mvn clean package

test:
  stage: test
  script:
    - mvn test

deploy:
  stage: deploy
  script:
    - kubectl apply -f deployment.yml
```

---

## 218. Explain Jenkins pipeline stages.

Stages organize pipeline into logical steps: checkout, build, test, deploy.

**Example:**
```groovy
pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/repo.git'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Deploy') {
            steps {
                sh './deploy.sh'
            }
        }
    }
}
```

---

## 219. What is declarative vs scripted pipeline?

**Declarative**: Structured, simpler syntax with predefined sections (pipeline, stages, steps).

**Scripted**: Groovy-based, more flexible but complex.

**Example:**
```groovy
// Declarative
pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
    }
}

// Scripted
node {
    stage('Build') {
        sh 'mvn clean package'
    }
}
```

---

## 220. What is blue-green deployment?

Two identical environments (blue=current, green=new). Deploy to green, test, then switch traffic. Instant rollback if issues.

**Example:**
```yaml
# Blue environment (current)
apiVersion: v1
kind: Service
metadata:
  name: app-service
spec:
  selector:
    version: blue

# Deploy to green
kubectl apply -f green-deployment.yml

# Switch traffic
kubectl patch service app-service -p '{"spec":{"selector":{"version":"green"}}}'
```

---

## 221. What is canary deployment?

Gradually roll out new version to small subset of users (5-10%), monitor, then increase traffic if stable.

**Example:**
```yaml
# 90% traffic to v1, 10% to v2
apiVersion: v1
kind: Service
metadata:
  name: app-service
spec:
  selector:
    app: myapp
---
apiVersion: apps/v1
kind: Deployment
metadata:
  name: app-v1
spec:
  replicas: 9
---
apiVersion: apps/v1
kind: Deployment
metadata:
  name: app-v2
spec:
  replicas: 1
```

---

## 222. How do you handle database migrations in CI/CD?

Use migration tools (Flyway, Liquibase) to version and apply schema changes automatically during deployment.

**Example:**
```java
// Flyway migration - V1__create_user_table.sql
CREATE TABLE users (
    id BIGINT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100)
);

// application.properties
spring.flyway.enabled=true
spring.flyway.locations=classpath:db/migration
```

```yaml
# CI/CD pipeline
deploy:
  script:
    - mvn flyway:migrate  # Run migrations
    - kubectl apply -f deployment.yml
```

**Flyway** automatically tracks applied migrations in `flyway_schema_history` table and applies only new ones.


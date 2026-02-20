# ✅ 17) Build Tools (Maven/Gradle)

## 206. What is Maven and POM?

**Definition:** Maven is a build automation and dependency management tool. POM (Project Object Model) is an XML file that contains project configuration, dependencies, plugins, and build settings.

**Example:**
```xml
<!-- pom.xml -->
<project xmlns="http://maven.apache.org/POM/4.0.0">
    <modelVersion>4.0.0</modelVersion>
    
    <groupId>com.example</groupId>
    <artifactId>my-app</artifactId>
    <version>1.0.0</version>
    <packaging>jar</packaging>
    
    <properties>
        <java.version>17</java.version>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
    </properties>
    
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
            <version>3.1.0</version>
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

## 207. Explain Maven lifecycle phases.

**Definition:** Maven has three built-in lifecycles: clean, default, and site. The default lifecycle has phases like validate, compile, test, package, install, and deploy that execute in order.

**Example:**
```bash
# Clean lifecycle
mvn clean                    # Removes target directory

# Default lifecycle phases (executed in order)
mvn validate                 # Validates project structure
mvn compile                  # Compiles source code
mvn test                     # Runs unit tests
mvn package                  # Creates JAR/WAR
mvn verify                   # Runs integration tests
mvn install                  # Installs to local repository
mvn deploy                   # Deploys to remote repository

# Common commands
mvn clean install            # Clean + compile + test + package + install
mvn clean package -DskipTests  # Package without running tests
```

```xml
<!-- Custom plugin execution in specific phase -->
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-surefire-plugin</artifactId>
            <version>3.0.0</version>
            <executions>
                <execution>
                    <phase>test</phase>
                    <goals>
                        <goal>test</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```

---

## 208. What are Maven dependency scopes?

**Definition:** Dependency scopes control when dependencies are available in the classpath during compile, test, and runtime phases.

**Example:**
```xml
<dependencies>
    <!-- compile (default) - Available in all classpaths -->
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-core</artifactId>
        <version>6.0.0</version>
        <scope>compile</scope>
    </dependency>
    
    <!-- provided - Available at compile/test, not packaged -->
    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>servlet-api</artifactId>
        <version>4.0.1</version>
        <scope>provided</scope>
    </dependency>
    
    <!-- runtime - Not needed for compilation, only runtime -->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.33</version>
        <scope>runtime</scope>
    </dependency>
    
    <!-- test - Only available during test phase -->
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.13.2</version>
        <scope>test</scope>
    </dependency>
    
    <!-- system - Similar to provided, must specify path -->
    <dependency>
        <groupId>com.custom</groupId>
        <artifactId>custom-lib</artifactId>
        <version>1.0</version>
        <scope>system</scope>
        <systemPath>${project.basedir}/lib/custom.jar</systemPath>
    </dependency>
    
    <!-- import - Only for dependency management in POM -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-dependencies</artifactId>
        <version>3.1.0</version>
        <type>pom</type>
        <scope>import</scope>
    </dependency>
</dependencies>
```

---

## 209. What is transitive dependency?

**Definition:** Transitive dependencies are dependencies of your direct dependencies. Maven automatically includes them in your project's classpath.

**Example:**
```xml
<!-- Your pom.xml -->
<dependencies>
    <!-- Direct dependency -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
        <version>3.1.0</version>
    </dependency>
    <!-- This brings transitive dependencies:
         - spring-web
         - spring-webmvc
         - jackson-databind
         - tomcat-embed-core
         etc. -->
</dependencies>

<!-- View dependency tree -->
<!-- Command: mvn dependency:tree -->

<!-- Exclude transitive dependency -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <version>3.1.0</version>
    <exclusions>
        <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```

---

## 210. How do you resolve dependency conflicts?

**Definition:** Dependency conflicts occur when different versions of the same library are required. Maven uses nearest definition and first declaration rules to resolve conflicts.

**Example:**
```bash
# Analyze dependency conflicts
mvn dependency:tree
mvn dependency:analyze

# Find specific dependency
mvn dependency:tree -Dincludes=commons-logging
```

```xml
<!-- Method 1: Explicit version declaration (nearest wins) -->
<dependencies>
    <dependency>
        <groupId>commons-logging</groupId>
        <artifactId>commons-logging</artifactId>
        <version>1.2</version>  <!-- This version will be used -->
    </dependency>
</dependencies>

<!-- Method 2: Dependency Management -->
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>commons-logging</groupId>
            <artifactId>commons-logging</artifactId>
            <version>1.2</version>
        </dependency>
    </dependencies>
</dependencyManagement>

<!-- Method 3: Exclude conflicting transitive dependency -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-core</artifactId>
    <version>5.3.0</version>
    <exclusions>
        <exclusion>
            <groupId>commons-logging</groupId>
            <artifactId>commons-logging</artifactId>
        </exclusion>
    </exclusions>
</dependency>

<!-- Method 4: Use Maven Enforcer Plugin -->
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-enforcer-plugin</artifactId>
    <version>3.0.0</version>
    <executions>
        <execution>
            <goals>
                <goal>enforce</goal>
            </goals>
            <configuration>
                <rules>
                    <dependencyConvergence/>
                </rules>
            </configuration>
        </execution>
    </executions>
</plugin>
```

---

## 211. What is Maven BOM (Bill of Materials)?

**Definition:** BOM is a special POM that provides a centralized dependency management for a set of related artifacts, ensuring version consistency across modules.

**Example:**
```xml
<!-- Parent POM with BOM -->
<project>
    <groupId>com.example</groupId>
    <artifactId>parent-bom</artifactId>
    <version>1.0.0</version>
    <packaging>pom</packaging>
    
    <dependencyManagement>
        <dependencies>
            <!-- Import Spring Boot BOM -->
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-dependencies</artifactId>
                <version>3.1.0</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            
            <!-- Define versions for your modules -->
            <dependency>
                <groupId>com.example</groupId>
                <artifactId>common-utils</artifactId>
                <version>1.0.0</version>
            </dependency>
        </dependencies>
    </dependencyManagement>
</project>

<!-- Child module using BOM -->
<project>
    <parent>
        <groupId>com.example</groupId>
        <artifactId>parent-bom</artifactId>
        <version>1.0.0</version>
    </parent>
    
    <artifactId>my-service</artifactId>
    
    <dependencies>
        <!-- No version needed - inherited from BOM -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        
        <dependency>
            <groupId>com.example</groupId>
            <artifactId>common-utils</artifactId>
        </dependency>
    </dependencies>
</project>
```

---

## 212. How do you create multi-module Maven project?

**Definition:** Multi-module projects organize related modules under a parent POM, enabling shared configuration and coordinated builds across modules.

**Example:**
```xml
<!-- Parent POM (root/pom.xml) -->
<project>
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.example</groupId>
    <artifactId>parent-project</artifactId>
    <version>1.0.0</version>
    <packaging>pom</packaging>
    
    <modules>
        <module>common</module>
        <module>service</module>
        <module>web</module>
    </modules>
    
    <properties>
        <java.version>17</java.version>
    </properties>
    
    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-dependencies</artifactId>
                <version>3.1.0</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>
</project>

<!-- Common Module (common/pom.xml) -->
<project>
    <parent>
        <groupId>com.example</groupId>
        <artifactId>parent-project</artifactId>
        <version>1.0.0</version>
    </parent>
    
    <artifactId>common</artifactId>
    <packaging>jar</packaging>
</project>

<!-- Service Module (service/pom.xml) -->
<project>
    <parent>
        <groupId>com.example</groupId>
        <artifactId>parent-project</artifactId>
        <version>1.0.0</version>
    </parent>
    
    <artifactId>service</artifactId>
    <packaging>jar</packaging>
    
    <dependencies>
        <dependency>
            <groupId>com.example</groupId>
            <artifactId>common</artifactId>
            <version>${project.version}</version>
        </dependency>
    </dependencies>
</project>

<!-- Web Module (web/pom.xml) -->
<project>
    <parent>
        <groupId>com.example</groupId>
        <artifactId>parent-project</artifactId>
        <version>1.0.0</version>
    </parent>
    
    <artifactId>web</artifactId>
    <packaging>jar</packaging>
    
    <dependencies>
        <dependency>
            <groupId>com.example</groupId>
            <artifactId>service</artifactId>
            <version>${project.version}</version>
        </dependency>
    </dependencies>
</project>

<!-- Build all modules: mvn clean install -->
```

---

## 213. Maven vs Gradle - when to use which?

**Definition:** Maven uses XML configuration with convention over configuration. Gradle uses Groovy/Kotlin DSL with more flexibility and better performance. Choose based on project needs and team preference.

**Example:**
```xml
<!-- Maven (pom.xml) -->
<project>
    <groupId>com.example</groupId>
    <artifactId>my-app</artifactId>
    <version>1.0.0</version>
    
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
            <version>3.1.0</version>
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

```groovy
// Gradle (build.gradle)
plugins {
    id 'java'
    id 'org.springframework.boot' version '3.1.0'
}

group = 'com.example'
version = '1.0.0'

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

```kotlin
// Gradle Kotlin DSL (build.gradle.kts)
plugins {
    java
    id("org.springframework.boot") version "3.1.0"
}

group = "com.example"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.test {
    useJUnitPlatform()
}
```

**When to use Maven:**
- Standard enterprise projects
- Team familiar with XML
- Need strict conventions
- Extensive plugin ecosystem
- Corporate environments with Maven standards

**When to use Gradle:**
- Need faster builds (incremental compilation)
- Complex build logic required
- Multi-module projects with many dependencies
- Android development (default)
- Prefer concise DSL over XML
- Need build caching and parallel execution
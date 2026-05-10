**Step 1 → Add Dependency :: Maven**

```xml id="8m2xqa"
<dependency>
    <groupId>org.flywaydb</groupId>
    <artifactId>flyway-core</artifactId>
</dependency>
```

**Step 2 → Configure Database :: application.yml**

```yaml id="5v9kzp"
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/testdb
    username: root
    password: root

  flyway:
    enabled: true
```

**Step 3 → Create Migration Folder :: Create folder**

```txt id="3x1mqa"
src/main/resources/db/migration
```

**Step 4 → Create SQL Migration File :: File name format**

```txt id="9k4wzp"
V1__create_employee_table.sql
```

```txt id="4q8mvn"
// Important:
V<version>__<description>.sql
```

**Step 5 → Add SQL :: V1__create_employee_table.sql**

```sql id="7m2xqp"
CREATE TABLE employee (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    salary DOUBLE
);
```

**Step 6 → Start Application :: When Spring Boot starts:**

```txt id="1w9kza"
Flyway automatically:
- checks migration history
- executes new scripts
- updates flyway_schema_history table
```

**Step 7 → Add New Migration :: Create new file:**

```txt id="6x4mqp"
V2__add_email_column.sql
```

```sql id="8v1wzk"
ALTER TABLE employee
ADD email VARCHAR(100);
```

Restart app → Flyway runs only V2.

---

**Internal Working

```txt id="2m7xqa"
Application Start
      ↓
Check flyway_schema_history
      ↓
Find pending migrations
      ↓
Execute SQL scripts
      ↓
Update migration history
```

---

**Generated Table

Flyway creates:

```txt id="5k8wzp"
flyway_schema_history
```

Stores:

* version
* script name
* execution time
* success/failure

---

**Interview One-Line Answer

Flyway automatically executes versioned SQL migration files during application startup and tracks executed changes using the `flyway_schema_history` table.

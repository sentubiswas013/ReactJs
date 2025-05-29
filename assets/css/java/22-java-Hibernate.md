# Top 1000 Java Interview Question & Answers

## Hibernate

### 712. **What is Hibernate framework?**

**Hibernate** is a powerful, open-source **Object-Relational Mapping (ORM)** framework that simplifies the process of mapping Java objects to database tables and vice versa. It provides a high-level abstraction to handle database operations, such as **CRUD (Create, Read, Update, Delete)** operations, without needing to write much SQL code. Hibernate allows Java developers to interact with relational databases using Java objects, reducing the complexity of handling SQL queries directly.

**Key Features of Hibernate:**
1. **ORM (Object-Relational Mapping)**: It automatically maps Java classes to database tables and Java objects to database records.
2. **Database Independence**: Hibernate abstracts database interaction, enabling applications to be independent of specific database technologies (e.g., MySQL, PostgreSQL, Oracle).
3. **Cache Mechanism**: Hibernate provides a caching mechanism to reduce database access time and increase performance.
4. **Query Language**: Hibernate supports HQL (Hibernate Query Language) and Criteria API to perform database queries in a database-agnostic way.
5. **Automatic Table Generation**: Hibernate can automatically generate database tables based on Java classes (entity beans).
6. **Lazy Loading**: Hibernate supports lazy loading, fetching data from the database only when it is needed, improving performance.
7. **Transaction Management**: Hibernate integrates well with Java Transaction API (JTA) and supports both programmatic and declarative transaction management.

---

### 713. **What is an Object Relational Mapping (ORM)?**

**Object-Relational Mapping (ORM)** is a programming technique used to convert data between incompatible systems (object-oriented programming languages and relational databases). ORM allows developers to work with database records as objects in their programming language, and vice versa, without directly writing SQL queries.

**Benefits of ORM**:
1. **Abstraction**: Developers can work with Java objects and don't need to deal with database specifics, like writing SQL queries manually.
2. **Maintainability**: ORM helps to reduce boilerplate code, making the system more maintainable.
3. **Performance**: ORM frameworks, like Hibernate, optimize queries and use techniques like caching and lazy loading to improve performance.
4. **Database Independence**: ORM allows for easy switching between different relational database systems without changing the application's business logic.

**Key Concepts in ORM**:
- **Entity**: A Java object representing a row in a table.
- **Session**: A connection between the application and the database.
- **Transaction**: A unit of work performed within a session, which is committed or rolled back.
- **Query**: Used to retrieve data from the database (e.g., HQL in Hibernate).

---

### 714. **What is the purpose of Configuration Interface in Hibernate?**

The **`Configuration` interface** in Hibernate is used to configure Hibernate ORM settings and resources. It is responsible for setting up Hibernate’s environment, like database connections, Hibernate properties, mapping files, and Hibernate's metadata. This interface is used to configure various Hibernate settings, such as specifying the database connection, defining entity classes, and enabling caching.

**Main Uses**:
1. **Configuring Database Connection**: It provides the means to configure database properties such as JDBC URL, username, password, and JDBC driver.
2. **Loading Hibernate Mappings**: The `Configuration` interface allows for specifying the location of mapping files or annotated entity classes.
3. **SessionFactory Creation**: It is used to create the **SessionFactory** object, which is required for interacting with the database.
4. **Setting Hibernate Properties**: You can set various Hibernate-specific properties, such as the SQL dialect, connection pool settings, and cache settings.

**Example**:
```java
Configuration configuration = new Configuration();
configuration.configure();  // Load hibernate.cfg.xml file
SessionFactory sessionFactory = configuration.buildSessionFactory();
```

---

### 715. **What is Object Relational Impedance Mismatch?**

**Object-Relational Impedance Mismatch** refers to the set of challenges that arise when trying to map data between object-oriented programming languages (like Java) and relational databases. The mismatch occurs because object-oriented systems and relational databases have different concepts, structures, and behaviors.

**Key Differences Leading to Impedance Mismatch**:
1. **Objects vs Tables**: In an object-oriented system, you deal with objects (which encapsulate both data and behavior), while relational databases store data in tables with rows and columns, which only represent data.
2. **Inheritance vs Joins**: Object-oriented languages support inheritance (e.g., classes inheriting from other classes), but relational databases don’t have a direct way of representing inheritance. In databases, inheritance relationships are typically represented using complex joins.
3. **Associations**: In object-oriented programming, objects can have direct references to each other (e.g., one object can reference another object), but in a relational database, relationships between entities are represented using foreign keys, which do not directly map to object references.
4. **Data Types**: Object-oriented systems can represent complex data types (like classes with multiple fields), while relational databases use primitive data types (like integers, strings, etc.).
5. **Identity**: In object-oriented systems, an object has its identity based on its memory address, while in relational databases, identity is based on unique identifiers (primary keys).

---

### 716. **What are the main problems of Object Relational Impedance Mismatch?**

The main problems of **Object-Relational Impedance Mismatch** are:

1. **Inheritance Representation**:
   - In an object-oriented system, classes can inherit properties and methods from other classes, creating hierarchies. Relational databases do not have direct support for inheritance. This forces developers to use complex join tables, or other workarounds, to represent inheritance.

2. **Mapping Associations**:
   - In object-oriented systems, relationships between objects are represented by references or pointers. However, in relational databases, relationships are represented by foreign keys. This can lead to difficulties in mapping one-to-one, one-to-many, and many-to-many associations.

3. **Object Identity**:
   - In object-oriented programming, an object has identity, and it’s identified by a reference or memory address. In relational databases, records are identified by a unique primary key. This difference can cause issues when trying to match objects with their database records.

4. **Complex Data Types**:
   - Objects can hold complex data types (like collections, maps, and nested objects), whereas relational databases are limited to primitive types (strings, integers, dates, etc.). Mapping these complex structures from objects to database rows can require complex transformations.

5. **Behavior vs Data**:
   - Objects contain both **data** (attributes) and **behavior** (methods), while relational databases only store data in tables, with no concept of behavior. This can lead to difficulty in modeling behaviors, especially in business logic encapsulated in methods of objects.

6. **Granularity of Data**:
   - Objects often represent real-world concepts and may have methods that operate on data, while relational databases work with rows and columns of data. Mapping objects to tables may result in a loss of granularity or extra complexity when translating complex objects into relational structures.

**Solutions**:
- **ORM Frameworks** (like Hibernate, JPA) provide solutions by mapping objects to database tables and abstracting away the complexities of handling these mismatches.
- **DTOs (Data Transfer Objects)** are often used to transfer data between layers and mitigate mismatches.

### 718. **Can you tell us about the core interfaces of Hibernate framework?**

Hibernate provides several core interfaces that are essential for its operation. These interfaces are designed to manage the lifecycle of entities, facilitate database transactions, and provide query capabilities. The core interfaces include:

1. **`Session`**:  
   The `Session` interface is the primary interface used by Hibernate to interact with the database. It represents a single unit of work and is used to perform CRUD operations (Create, Read, Update, Delete), as well as to begin and commit transactions.

   - **Key Methods**:
     - `save()`: Save an object to the database.
     - `update()`: Update an existing object in the database.
     - `delete()`: Delete an object from the database.
     - `createQuery()`: Create a query to retrieve data.
     - `get()`, `load()`: Retrieve an entity by ID.

2. **`SessionFactory`**:  
   The `SessionFactory` interface is responsible for creating `Session` objects and is essentially the main factory for creating the Hibernate session. It is created once per application lifecycle and provides configuration and caching.

   - **Key Methods**:
     - `openSession()`: Open a new session.
     - `getCurrentSession()`: Get the current session (supports automatic transaction management).
     - `close()`: Close the session factory.

3. **`Transaction`**:  
   The `Transaction` interface is used to manage transactions within a Hibernate session. It allows for commit and rollback operations on a transaction.

   - **Key Methods**:
     - `begin()`: Begin a new transaction.
     - `commit()`: Commit the current transaction.
     - `rollback()`: Rollback the current transaction.

4. **`Query`**:  
   The `Query` interface is used to represent a query that retrieves data from the database. It is used to execute HQL (Hibernate Query Language) or native SQL queries.

   - **Key Methods**:
     - `list()`: Return the result list from a query.
     - `uniqueResult()`: Return a single result from a query.

5. **`Criteria`**:  
   The `Criteria` interface is used to create a type-safe, object-oriented query mechanism in Hibernate. It allows developers to create queries without writing HQL or SQL.

6. **`Configuration`**:  
   The `Configuration` interface is used to configure Hibernate and provides methods to load configuration files, configure database properties, and build a `SessionFactory`.

---

### 719. **How will you map the columns of a DB table to the properties of a Java class in Hibernate?**

In Hibernate, you can map the columns of a database table to the properties of a Java class using **annotations** or **XML-based mapping files**. The most common and modern approach is to use **annotations**.

#### **Mapping with Annotations**:
To map the columns of a database table to the properties of a Java class, you need to annotate your Java class with Hibernate annotations. The key annotations include:

1. **`@Entity`**: Marks the Java class as an entity that will be mapped to a database table.
2. **`@Table`**: Specifies the name of the database table to which the entity will be mapped.
3. **`@Id`**: Specifies the primary key of the entity.
4. **`@Column`**: Specifies the database column that the property is mapped to.

**Example**:
```java
import javax.persistence.*;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    // Getters and setters
}
```
In this example:
- The class `Student` is mapped to the `student` table.
- The `id`, `first_name`, `last_name`, and `email` properties of the `Student` class are mapped to the respective columns in the `student` table.

#### **Mapping with XML**:
If you prefer XML-based configuration, you can use the `hibernate.cfg.xml` file to define mappings using the `<class>` and `<property>` elements.

**Example** (`student.hbm.xml`):
```xml
<hibernate-mapping>
    <class name="com.example.Student" table="student">
        <id name="id" column="id">
            <generator class="identity"/>
        </id>
        <property name="firstName" column="first_name"/>
        <property name="lastName" column="last_name"/>
        <property name="email" column="email"/>
    </class>
</hibernate-mapping>
```
This mapping file does the same as the annotations but uses XML configuration.

---

### 720. **Does Hibernate make it mandatory for a mapping file to have .hbm.xml extension?**

No, **Hibernate does not require mapping files to have the `.hbm.xml` extension**. However, this is the conventional extension used for Hibernate mapping files. You can name your mapping files with any valid extension, but it is recommended to follow the `.hbm.xml` convention for consistency and readability.

However, when using **annotations** for entity mapping, no XML mapping file is necessary. Annotations are the preferred approach in modern Hibernate versions.

---

### 721. **What are the steps for creating a SessionFactory in Hibernate?**

To create a **SessionFactory** in Hibernate, follow these steps:

1. **Create Hibernate Configuration File (`hibernate.cfg.xml`)**:
   The `hibernate.cfg.xml` file contains all the configuration details such as database connection, dialect, and Hibernate properties.

   Example of `hibernate.cfg.xml`:
   ```xml
   <!DOCTYPE hibernate-configuration PUBLIC "-//Hibernate/Hibernate Configuration DTD 3.0//EN" "hibernate-configuration-3.0.dtd">
   <hibernate-configuration>
       <session-factory>
           <!-- JDBC Database connection settings -->
           <property name="hibernate.dialect">org.hibernate.dialect.MySQLDialect</property>
           <property name="hibernate.connection.driver_class">com.mysql.cj.jdbc.Driver</property>
           <property name="hibernate.connection.url">jdbc:mysql://localhost:3306/your_database</property>
           <property name="hibernate.connection.username">root</property>
           <property name="hibernate.connection.password">password</property>

           <!-- JDBC connection pool settings -->
           <property name="hibernate.c3p0.min_size">5</property>
           <property name="hibernate.c3p0.max_size">20</property>

           <!-- Specify dialect -->
           <property name="hibernate.dialect">org.hibernate.dialect.MySQLDialect</property>

           <!-- Enable Hibernate's automatic session context management -->
           <property name="hibernate.current_session_context_class">thread</property>

           <!-- Echo all executed SQL to stdout -->
           <property name="hibernate.show_sql">true</property>

           <!-- Drop and re-create the database schema on startup -->
           <property name="hibernate.hbm2ddl.auto">update</property>
       </session-factory>
   </hibernate-configuration>
   ```

2. **Create a Configuration Object**:
   Create a `Configuration` object to load the `hibernate.cfg.xml` file.

   ```java
   Configuration configuration = new Configuration();
   configuration.configure();  // Load hibernate.cfg.xml file
   ```

3. **Build the SessionFactory**:
   Use the `buildSessionFactory()` method of the `Configuration` class to create a `SessionFactory`.

   ```java
   SessionFactory sessionFactory = configuration.buildSessionFactory();
   ```

4. **Open a Session**:
   Once the `SessionFactory` is created, you can open a session to interact with the database.

   ```java
   Session session = sessionFactory.openSession();
   ```

5. **Close the SessionFactory**:
   After your work is done, close the `SessionFactory` to release resources.

   ```java
   sessionFactory.close();
   ```

---

### 722. **Why do we use POJO in Hibernate?**

**POJOs (Plain Old Java Objects)** are used in Hibernate to represent entities that map to database tables. The key benefits of using POJOs in Hibernate are:

1. **Simplicity**: POJOs are simple Java objects with no dependencies on Hibernate or any other framework. This makes them easier to work with and more flexible across different frameworks.
   
2. **Persistence**: POJOs represent the data of an entity. Hibernate maps the POJO class to a database table, and its properties to columns in the table. POJOs do not need to extend any special classes or implement any special interfaces to be persisted.

3. **Portability**: POJOs are framework-independent and can be used with any Java framework, not just Hibernate. This allows for easy switching between different persistence mechanisms, making the application more adaptable.

4. **Encapsulation**: POJOs provide the benefit of **encapsulation**, where the class properties (fields) are private, and getter and setter methods are used to access them, ensuring a well-organized and maintainable codebase.

5. **Customization**: POJOs can include business logic methods in addition to the basic data properties, making them more than just containers for data.

### 723. **What is Hibernate Query Language (HQL)?**

Hibernate Query Language (HQL) is an object-oriented query language used in Hibernate for querying the database. It is similar to SQL but operates on objects and their properties instead of directly working with database tables and columns.

**Key features of HQL:**
1. **Object-Oriented**: HQL queries operate on persistent objects, not tables. It uses class names, property names, and their relationships to construct queries.
2. **Database Independent**: Since HQL is based on the object model, it is independent of the underlying database. It abstracts the SQL syntax and allows developers to write database-independent queries.
3. **Supports SQL-like operations**: HQL supports SQL-like operations, including `SELECT`, `INSERT`, `UPDATE`, and `DELETE`.
4. **Uses dot notation**: In HQL, dot notation is used to reference object properties (e.g., `student.name` instead of `student_table.name`).
   
**Example of HQL:**
```java
// Selecting all students
String hql = "FROM Student WHERE age > :age";
Query query = session.createQuery(hql);
query.setParameter("age", 18);
List<Student> students = query.list();
```

In the above query:
- `Student` is a persistent class (not a table).
- The query retrieves a list of students whose age is greater than 18.

---

### 724. **How will you call a stored procedure in Hibernate?**

To call a stored procedure in Hibernate, you can use the `Session.createSQLQuery()` method and provide the appropriate SQL stored procedure call. Here’s an example:

**Example:**
```java
// Calling a stored procedure in Hibernate
String sql = "{call GetStudentDetails(:studentId)}";
Query query = session.createSQLQuery(sql);
query.setParameter("studentId", 101);
List<Object[]> result = query.list();

// Process the result
for (Object[] row : result) {
    System.out.println("Student Name: " + row[0]);
}
```

In this example:
- `GetStudentDetails` is a stored procedure defined in the database.
- The procedure is called with the `studentId` parameter.
- The result of the procedure is retrieved as a list of objects.

---

### 725. **What is Criteria API in Hibernate?**

The **Criteria API** in Hibernate is an object-oriented approach to query the database. It allows you to build queries programmatically without writing HQL or SQL. It is part of Hibernate's **criteria-based querying** and is especially useful for building dynamic queries.

**Key Features of Criteria API:**
1. **Type-safe**: The Criteria API uses Java types for queries, which makes it type-safe, eliminating issues with incorrect column names or types.
2. **Dynamic Queries**: Criteria API is useful for building dynamic queries, where query conditions are added conditionally at runtime.
3. **Object-Oriented**: Instead of using SQL strings, the Criteria API allows you to work with Java objects, making it more readable and flexible.
4. **Supports Aggregates and Joins**: It supports SQL features such as joins, grouping, and aggregation.

**Example of using Criteria API:**
```java
CriteriaBuilder builder = session.getCriteriaBuilder();
CriteriaQuery<Student> criteriaQuery = builder.createQuery(Student.class);
Root<Student> root = criteriaQuery.from(Student.class);
criteriaQuery.select(root).where(builder.greaterThan(root.get("age"), 18));

List<Student> students = session.createQuery(criteriaQuery).getResultList();
```

This example uses the `CriteriaBuilder` to create a query for retrieving students whose age is greater than 18.

---

### 726. **Why do we use HibernateTemplate?**

`HibernateTemplate` is a part of **Spring's Hibernate Integration** and is used to simplify the use of Hibernate in a Spring-based application. It provides a set of methods that manage Hibernate sessions and transactions, reducing the amount of boilerplate code required for interacting with the database.

**Benefits of HibernateTemplate:**
1. **Simplifies Session Management**: It handles the opening, closing, and managing of Hibernate `Session` objects, reducing the manual session handling code.
2. **Transaction Management**: It simplifies transaction management by automatically beginning and committing transactions (or rolling them back in case of exceptions).
3. **Exception Translation**: HibernateTemplate translates Hibernate exceptions into Spring’s DataAccessException, making it easier to handle errors.
4. **Code Reduction**: It reduces the amount of code required for common database operations, such as saving, updating, deleting, and querying entities.
   
**Example of using HibernateTemplate:**
```java
@Autowired
private HibernateTemplate hibernateTemplate;

public void saveStudent(Student student) {
    hibernateTemplate.save(student);
}
```

In this example:
- `HibernateTemplate` is used to save the `student` object to the database, without manually managing the session and transaction.

---

### 727. **How can you see SQL code generated by Hibernate on console?**

To see the SQL code generated by Hibernate, you can enable Hibernate’s logging functionality. This allows you to log all the SQL queries and their execution times.

**Steps to enable SQL logging in Hibernate:**

1. **In `hibernate.cfg.xml`**:
   You can set the `hibernate.show_sql` property to `true` to print the SQL statements to the console.

   Example:
   ```xml
   <hibernate-configuration>
       <session-factory>
           <!-- Other configurations -->
           <property name="hibernate.show_sql">true</property>
           <property name="hibernate.format_sql">true</property>
       </session-factory>
   </hibernate-configuration>
   ```

   - `hibernate.show_sql=true`: This will print SQL queries to the console.
   - `hibernate.format_sql=true`: This will format the SQL queries for better readability (optional).

2. **Using a logging framework (like Log4j or SLF4J)**:
   You can configure the logging framework to log SQL queries at the `DEBUG` or `TRACE` level. Hibernate uses a logger named `org.hibernate.SQL` for logging SQL queries.

   Example using Log4j:
   ```xml
   <log4j:logger name="org.hibernate.SQL" level="debug"/>
   ```

   This configuration will print SQL queries to the console as they are generated by Hibernate. You can also log the binding of parameters by enabling `hibernate.type` logging.

   Example:
   ```xml
   <log4j:logger name="org.hibernate.type" level="trace"/>
   ```

### 728. **What are the different types of collections supported by Hibernate?**

Hibernate supports several types of collections, which are used to model relationships between entities. The collection types in Hibernate are primarily:

1. **List**: An ordered collection that allows duplicates. The order of elements in a list is maintained.
   - Mapped to a collection table or an indexed field in the database.
   - Typically uses a `List` or `ArrayList` in Java.

   **Example**:
   ```java
   @OneToMany
   @JoinColumn(name = "student_id")
   private List<Course> courses;
   ```

2. **Set**: A collection that does not allow duplicate elements. It does not maintain any order of elements.
   - Typically uses a `Set` or `HashSet` in Java.

   **Example**:
   ```java
   @OneToMany
   @JoinColumn(name = "student_id")
   private Set<Course> courses;
   ```

3. **Map**: A collection of key-value pairs. Hibernate can map `Map` collections where each key is unique, and values are mapped to entities.
   - Typically uses a `Map` or `HashMap` in Java.

   **Example**:
   ```java
   @ElementCollection
   private Map<String, String> studentDetails;
   ```

4. **Bag**: Similar to a List but without any ordering guarantees. Hibernate uses this for collections that might have duplicates, and the order is not guaranteed.
   - Typically uses a `List` in Java but Hibernate uses it for collections that may have duplicates and unordered entries.

5. **SortedSet**: A set that maintains its order. Typically used when elements should be sorted based on a comparator.
   - Typically uses a `SortedSet` or `TreeSet` in Java.

6. **SortedMap**: A map that maintains its keys in a sorted order. Typically used when the keys are sorted by a comparator.

7. **Collection**: The most generic form of a collection that can be used with any type of collection. It is the parent interface for all other collection types.

### 729. **What is the difference between session.save() and session.saveOrUpdate() methods in Hibernate?**

Both `session.save()` and `session.saveOrUpdate()` are used for saving entities to the database, but they have distinct behaviors:

1. **`session.save()`**:
   - **Purpose**: Used to save a new entity to the database.
   - If the entity already exists (based on the primary key), it throws an exception.
   - It assigns a new identifier (primary key) to the entity if it does not already have one.
   
   **Example**:
   ```java
   session.save(student);  // Inserts a new row
   ```

2. **`session.saveOrUpdate()`**:
   - **Purpose**: It checks if an entity exists (based on its identifier). If the entity is new (i.e., the identifier is null or does not exist in the database), it performs a `save()`. If the entity already exists (i.e., the identifier matches an existing record), it performs an `update()` to modify the existing entity.
   - It is a combination of both `save()` and `update()`.
   
   **Example**:
   ```java
   session.saveOrUpdate(student);  // Saves if new, updates if exists
   ```

**Key Difference**:
- `save()` inserts a new entity and throws an exception if the entity already exists.
- `saveOrUpdate()` checks for the existence of the entity and either saves a new entity or updates the existing one.

### 730. **What are the advantages of Hibernate framework over JDBC?**

Hibernate offers several advantages over using raw JDBC for database interaction:

1. **Object-Relational Mapping (ORM)**: Hibernate allows developers to work with Java objects and their relationships instead of manually writing SQL to manage database rows and columns. This reduces the need to write complex SQL statements.

2. **Automatic Table Generation**: Hibernate can automatically generate database tables from Java classes based on annotations or XML configuration.

3. **Transparent Persistence**: Objects can be made persistent without changing the code. Hibernate automatically manages the lifecycle of objects.

4. **Database Independence**: Hibernate abstracts the underlying database. You can change the database without changing the code by modifying the Hibernate configuration.

5. **Caching**: Hibernate supports first-level and second-level caching, which improves performance by reducing the number of database queries.

6. **Lazy Loading**: Hibernate supports lazy loading, where related entities are loaded only when accessed, which can improve performance.

7. **Query Language**: Hibernate provides HQL (Hibernate Query Language) that is object-oriented and database-agnostic, unlike JDBC, which is tied to SQL and requires specific dialects for different databases.

8. **Transaction Management**: Hibernate provides built-in transaction management, simplifying the management of database transactions and supporting both declarative and programmatic transaction management.

9. **Batch Processing**: Hibernate supports batch processing, reducing the number of database round-trips required for operations like bulk inserts, updates, and deletes.

10. **Reduced Boilerplate Code**: Hibernate reduces the need for boilerplate code such as handling result sets, prepared statements, and connection management, which is a common requirement when using JDBC.

### 731. **How can we get statistics of a SessionFactory in Hibernate?**

To get statistics of a `SessionFactory` in Hibernate, you can enable Hibernate’s statistics feature, which provides insights into various operations performed by Hibernate, such as the number of queries executed, cache hits, cache misses, and more.

To enable statistics in Hibernate:

1. **In Hibernate Configuration**:
   Set the `hibernate.generate_statistics` property to `true` in the `hibernate.cfg.xml` configuration file.

   **Example**:
   ```xml
   <hibernate-configuration>
       <session-factory>
           <!-- Other properties -->
           <property name="hibernate.generate_statistics">true</property>
       </session-factory>
   </hibernate-configuration>
   ```

2. **Accessing the Statistics**:
   You can retrieve the `Statistics` object from the `SessionFactory` and query various statistics.

   **Example**:
   ```java
   // Obtain SessionFactory and statistics
   SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
   Statistics stats = sessionFactory.getStatistics();
   
   // Get the number of queries executed
   System.out.println("Query Count: " + stats.getQueryExecutionCount());
   
   // Get the number of cache hits
   System.out.println("Cache Hit Count: " + stats.getSecondLevelCacheHitCount());
   
   // Get the number of cache misses
   System.out.println("Cache Miss Count: " + stats.getSecondLevelCacheMissCount());
   ```

**Note**: 
- Make sure to call `sessionFactory.getStatistics()` after the Hibernate session has been initialized.
- The statistics feature provides valuable insights for performance tuning, such as monitoring query execution count, cache hits, and other resource utilization metrics.


### 732. **What is the Transient state of an object in Hibernate?**

In Hibernate, the **Transient** state refers to an object that has been instantiated but is not associated with a Hibernate `Session` and is not stored in the database. It is a plain Java object and has no representation in the persistence context (i.e., no associated database row).

- **Characteristics of a Transient Object**:
  - It is not connected to the Hibernate session.
  - It has not been persisted in the database.
  - It does not have a database identifier (primary key).
  
- **Example**:
  ```java
  Student student = new Student();
  student.setName("John"); // student is transient, it hasn't been saved to DB yet
  ```

  In this case, the `student` object is in the **Transient state** as it is a regular Java object, and Hibernate has no knowledge of it.

### 733. **What is the Detached state of an object in Hibernate?**

The **Detached** state refers to an object that was previously associated with a Hibernate session but is no longer associated with it. This can happen when the session that was used to load or save the object is closed, or if the object is explicitly disconnected from the session.

- **Characteristics of a Detached Object**:
  - It was once persistent (associated with a session and persisted in the database).
  - It has an identifier (primary key) corresponding to a database record.
  - It is no longer associated with a session, so changes made to the object are not automatically synchronized with the database.
  - It can be reattached to a session to become persistent again.

- **Example**:
  ```java
  // Assuming a Student object was loaded in an open session
  Session session = sessionFactory.openSession();
  Student student = session.get(Student.class, 1); // Persistent object

  session.close(); // The session is closed, and the object becomes detached

  student.setName("Updated Name"); // The object is detached and changes will not be reflected in DB unless reattached
  ```

  In this case, the `student` object becomes **Detached** once the session is closed. To persist changes, the object would need to be reattached to a new session.

### 734. **What is the use of Dirty Checking in Hibernate?**

**Dirty Checking** is a feature of Hibernate that automatically detects changes to persistent objects (entities) and synchronizes those changes with the database when the session is flushed (either explicitly or during a commit).

- **How Dirty Checking Works**:
  - Hibernate keeps track of the original state of an entity when it is loaded into the session.
  - When an entity is modified (i.e., its properties are changed), Hibernate compares the current state of the entity with its original state to detect changes.
  - If the entity's state is different from the original, Hibernate marks it as "dirty" and updates the corresponding database record when the session is flushed.
  
- **Benefits**:
  - **Automatic Synchronization**: Dirty checking reduces the need for explicit updates. It ensures that any changes made to the object are automatically propagated to the database.
  - **Performance**: It ensures that only modified entities are updated, minimizing unnecessary database writes.
  
- **Example**:
  ```java
  Session session = sessionFactory.openSession();
  Student student = session.get(Student.class, 1); // Load an entity
  student.setName("Updated Name"); // Modify the entity (dirty checking will track this)

  // At this point, Hibernate will detect that the entity has changed
  session.flush(); // Hibernate will automatically update the database without explicit update()
  ```

In this example, when the `student.setName()` method is called, Hibernate detects that the entity has been modified and will update the corresponding database record when the session is flushed.

### 735. **What is the purpose of Callback interface in Hibernate?**

The **Callback** interface in Hibernate provides a way to define lifecycle callback methods that can be executed at various stages of an entity's lifecycle. This interface allows you to hook into the persistence operations and execute custom logic when certain events occur, such as when an entity is loaded, saved, updated, or deleted.

- **Common Callback Methods**:
  - **`onSave()`**: Called when an entity is being saved (inserted into the database).
  - **`onLoad()`**: Called when an entity is being loaded from the database.
  - **`onDelete()`**: Called when an entity is about to be deleted.
  - **`onUpdate()`**: Called when an entity is about to be updated.

- **Benefits of Using Callbacks**:
  - You can add custom behavior before or after certain operations, such as logging, validation, or auditing.
  - Helps in maintaining consistency and centralizing logic for entity lifecycle events.
  - Enables better control over entity persistence without modifying the core application logic.

- **Example of Callback Interface Implementation**:
  ```java
  public class Student implements Callback {
      private int id;
      private String name;
      
      // Callback method for onSave
      public void onSave() {
          System.out.println("Entity is being saved: " + this.name);
      }

      // Callback method for onLoad
      public void onLoad() {
          System.out.println("Entity is being loaded: " + this.name);
      }
  }
  ```

In this case, the `onSave()` and `onLoad()` methods will be called automatically when the `Student` entity is saved or loaded, respectively, provided the `Callback` interface is properly configured in Hibernate.

To enable lifecycle callbacks, you can also annotate methods with the `@PrePersist`, `@PreUpdate`, and `@PostLoad` annotations, depending on the type of callback.

### 736. **What are the different ORM levels in Hibernate?**

Hibernate ORM (Object Relational Mapping) is a framework that allows mapping of Java objects to database tables. The ORM levels in Hibernate can be described based on the level of abstraction and interaction provided between Java objects and the database. These include:

1. **Session Level**:
   - The `Session` is the primary interface used to interact with the database.
   - It provides CRUD operations like `save()`, `update()`, `delete()`, and `find()`.
   - It represents a single unit of work and operates on persistent objects, maintaining the session cache (first-level cache).

2. **Transaction Level**:
   - Transactions in Hibernate are handled by the `Transaction` interface. It allows grouping of operations that should be atomic.
   - Hibernate integrates with the underlying database transaction model, ensuring consistency and isolation of operations.
   
3. **Query Level**:
   - Hibernate provides a rich query language called Hibernate Query Language (HQL), which is object-oriented and works with Java classes and their properties.
   - It also supports Criteria API for type-safe querying and native SQL for more complex queries.

4. **Cache Level**:
   - Hibernate provides a two-level caching system:
     - **First-Level Cache**: Session-level cache, automatically enabled, which stores objects in memory during the lifecycle of a session.
     - **Second-Level Cache**: Shared cache between sessions, which can be configured with a caching provider (e.g., EhCache, Infinispan).

5. **Object Mapping Level**:
   - This level defines how Java objects are mapped to database tables. This includes the use of annotations or XML configuration to map entities, properties, and relationships (e.g., `@Entity`, `@OneToMany`, `@ManyToOne`).

6. **Connection Level**:
   - Hibernate manages database connections, abstracting the complexity of direct JDBC calls. This level provides connection pooling and database interaction via `SessionFactory`.

### 737. **What are the different ways to configure a Hibernate application?**

There are multiple ways to configure a Hibernate application, which primarily include:

1. **XML-based Configuration**:
   - Hibernate configuration can be provided using an XML file named `hibernate.cfg.xml`.
   - The configuration file contains essential information like database connection details, mapping files, and session factory properties.
   - Example:
     ```xml
     <hibernate-configuration>
         <session-factory>
             <property name="hibernate.dialect">org.hibernate.dialect.MySQLDialect</property>
             <property name="hibernate.connection.driver_class">com.mysql.jdbc.Driver</property>
             <property name="hibernate.connection.url">jdbc:mysql://localhost:3306/mydb</property>
             <property name="hibernate.connection.username">root</property>
             <property name="hibernate.connection.password">password</property>
             <property name="hibernate.hbm2ddl.auto">update</property>
         </session-factory>
     </hibernate-configuration>
     ```

2. **Annotation-based Configuration**:
   - The configuration can also be done using Java-based annotations, eliminating the need for XML configuration.
   - `@Entity`, `@Table`, `@Id`, `@Column`, etc., are used to define the entity mappings directly in Java code.

3. **Java-based Configuration (via `Configuration` class)**:
   - Hibernate can be configured programmatically in Java using the `Configuration` class and the `SessionFactory`.
   - Example:
     ```java
     Configuration configuration = new Configuration();
     configuration.configure("hibernate.cfg.xml"); // Load hibernate.cfg.xml
     SessionFactory sessionFactory = configuration.buildSessionFactory();
     ```

4. **Spring Hibernate Integration**:
   - Spring’s `HibernateTemplate` can be used to configure Hibernate in a Spring-based application. Spring provides a wrapper for the Hibernate configuration using `LocalSessionFactoryBean` and integrates it with the Spring IoC container.

### 738. **What is Query Cache in Hibernate?**

The **Query Cache** in Hibernate is a caching mechanism that stores the results of queries, reducing the overhead of database calls when the same query is executed multiple times. It improves the performance of an application by avoiding redundant database queries.

- **Usage**: It is often used in conjunction with the **Second-Level Cache**, but can also function independently.
- **How It Works**:
  - When a query is executed for the first time, the result is stored in the cache.
  - Subsequent executions of the same query will fetch the results from the cache instead of executing the SQL against the database again.
  
- **Configuration**:
  - Query cache needs to be explicitly enabled in Hibernate configuration, and the cache provider (e.g., EhCache, Infinispan) should support query caching.
  - Example of enabling query cache:
    ```xml
    <property name="hibernate.cache.use_query_cache">true</property>
    <property name="hibernate.cache.region.factory_class">org.hibernate.cache.ehcache.EhCacheRegionFactory</property>
    ```

- **Limitation**:
  - The query cache works for queries that are not dependent on session-level data. Also, cached results can be invalidated when the underlying data changes.

### 739. **What are the different types of Association mappings supported by Hibernate?**

Hibernate supports the following types of **association mappings** to map relationships between different entity classes (tables):

1. **One-to-One Mapping**:
   - A one-to-one association means that each instance of one entity is associated with only one instance of another entity.
   - Example: A `Person` class has one `Passport` (one-to-one relationship).
     ```java
     @OneToOne
     @JoinColumn(name = "passport_id")
     private Passport passport;
     ```

2. **One-to-Many Mapping**:
   - A one-to-many relationship represents a single entity associated with multiple entities. One record in the first table is related to many records in the second table.
   - Example: A `Department` has many `Employees` (one-to-many relationship).
     ```java
     @OneToMany(mappedBy = "department")
     private Set<Employee> employees;
     ```

3. **Many-to-One Mapping**:
   - A many-to-one relationship represents multiple entities related to a single entity.
   - Example: Many `Employees` belong to one `Department` (many-to-one relationship).
     ```java
     @ManyToOne
     @JoinColumn(name = "department_id")
     private Department department;
     ```

4. **Many-to-Many Mapping**:
   - A many-to-many relationship represents entities that are related to multiple other entities.
   - Example: A `Student` can enroll in many `Courses`, and each `Course` can have many `Students` (many-to-many relationship).
     ```java
     @ManyToMany
     @JoinTable(name = "student_course", 
                joinColumns = @JoinColumn(name = "student_id"), 
                inverseJoinColumns = @JoinColumn(name = "course_id"))
     private Set<Course> courses;
     ```

Each of these mappings can be customized with annotations like `@OneToOne`, `@OneToMany`, `@ManyToOne`, and `@ManyToMany`, and can also be further refined with additional annotations such as `@JoinColumn`, `@JoinTable`, etc.

### 740. **What are the different types of Unidirectional Association mappings in Hibernate?**

In Hibernate, **unidirectional association mappings** are those where one entity knows about the other, but the reverse is not true. The mapping is **one-way** only, meaning one entity has a reference to another, but the other does not have a reference to the first one.

Here are the main types of unidirectional association mappings in Hibernate:

1. **Unidirectional One-to-One**:
   - A one-to-one relationship where one entity references the other, but the second entity does not have a reference back to the first entity.
   - Example: A `Person` has a `Passport`, but the `Passport` does not have a reference to `Person`.
     ```java
     @Entity
     public class Person {
         @Id
         private Long id;
         @OneToOne
         private Passport passport;
     }
     ```

2. **Unidirectional One-to-Many**:
   - A one-to-many relationship where one entity references multiple entities, but the child entities do not know about the parent entity.
   - Example: A `Department` has many `Employees`, but `Employee` does not have a reference to `Department`.
     ```java
     @Entity
     public class Department {
         @Id
         private Long id;
         @OneToMany
         private Set<Employee> employees;
     }
     ```

3. **Unidirectional Many-to-One**:
   - A many-to-one relationship where multiple entities refer to a single entity, but the single entity does not reference back to the multiple entities.
   - Example: Multiple `Employee` objects can belong to one `Department`, but the `Department` does not know about the `Employees`.
     ```java
     @Entity
     public class Employee {
         @Id
         private Long id;
         @ManyToOne
         private Department department;
     }
     ```

4. **Unidirectional Many-to-Many**:
   - A many-to-many relationship where multiple entities refer to multiple other entities, but the reverse relationship is not explicitly defined in the child entity.
   - Example: A `Student` can enroll in many `Courses`, but the `Course` does not have a reference back to the `Students`.
     ```java
     @Entity
     public class Student {
         @Id
         private Long id;
         @ManyToMany
         private Set<Course> courses;
     }
     ```

### 741. **What is the Unit of Work design pattern?**

The **Unit of Work** design pattern is used to manage changes made to a set of objects in a transactional manner. It ensures that all changes to the objects are committed as a single unit. This pattern is especially useful for managing persistence and minimizing the number of database calls.

**Key Points**:
- Tracks changes made to objects and ensures that they are saved to the database in a single operation.
- Manages the transaction boundaries and ensures consistency by coordinating the writing of changes.
- It is especially useful in ORM frameworks like Hibernate to keep track of all entities that need to be persisted or updated.

**Steps in Unit of Work**:
1. **Track Changes**: Keeps track of all objects that are modified, added, or deleted.
2. **Commit**: When a commit is called, all changes are sent to the database in a single transaction.
3. **Rollback**: If an error occurs, all changes can be rolled back to ensure consistency.

### 742. **In Hibernate, how can an object go in Detached state?**

In Hibernate, an object goes into the **Detached state** when it is no longer associated with the **current Hibernate session** but still exists in memory. There are several ways an object can become detached:

1. **Session is closed**: When a Hibernate session is closed, all entities loaded in that session are detached.
   ```java
   session.close();
   ```

2. **Eviction**: An object can be evicted from the session cache using the `evict()` method.
   ```java
   session.evict(myEntity);
   ```

3. **Session.clear()**: This method clears the session, removing all objects from the session cache.
   ```java
   session.clear();
   ```

4. **Session.saveOrUpdate() after being in persistent state**: If you save or update an object after it has been in persistent state (associated with a session) and the session is closed or disconnected, the object becomes detached.

5. **Session.merge()**: If an object is not associated with any session, calling `session.merge()` can merge the changes with a new session.

### 743. **How will you order the results returned by a Criteria in Hibernate?**

In Hibernate, when using the `Criteria` API to query the database, you can use the `addOrder()` method to specify how the results should be ordered. You can order by one or more properties in either ascending or descending order.

**Example**:
```java
Criteria criteria = session.createCriteria(Employee.class);
criteria.addOrder(Order.asc("name"));  // Orders results by 'name' in ascending order
criteria.addOrder(Order.desc("salary")); // Orders results by 'salary' in descending order
List<Employee> employees = criteria.list();
```

- `Order.asc("property")` will order the results in ascending order.
- `Order.desc("property")` will order the results in descending order.

### 744. **How does Example criterion work in Hibernate?**

The **Example criterion** in Hibernate is used for querying entities based on an example entity. It allows you to search for entities that match the properties of a provided example object.

- **Example** is a class in Hibernate that provides a way to search using an entity instance as a template.
- It is a way to create a "query by example" that finds entities that match the values of the fields in the provided example.

**Steps**:
1. Create an **Example** object using a filled entity with properties you want to match.
2. Use `Example.create()` to generate the example criteria.
3. Use the `add()` method to add additional restrictions, if needed.
4. Execute the query and return the matching results.

**Example**:
```java
Employee exampleEmployee = new Employee();
exampleEmployee.setName("John");

Example example = Example.create(exampleEmployee).enableLike().ignoreCase();

Criteria criteria = session.createCriteria(Employee.class);
criteria.add(example);
List<Employee> employees = criteria.list();
```

In the example:
- It searches for employees with a name like "John".
- The `enableLike()` method makes the query use `LIKE` in the SQL, and `ignoreCase()` ignores case sensitivity.

This method is useful when you want to find records that match an example entity's properties without writing complex queries.

### 745. **How does Transaction management work in Hibernate?**

Transaction management in Hibernate is crucial to ensure that database operations are completed successfully and that the system maintains consistency. Hibernate supports both **programmatic** and **declarative** transaction management, and it integrates seamlessly with Java's JTA (Java Transaction API) or JDBC transactions.

**Key points in Hibernate Transaction Management**:
1. **Session.beginTransaction()**: 
   - This method starts a new transaction.
   - All changes made to the entities (insert, update, delete) within the transaction are not committed to the database until the transaction is completed.
   ```java
   Transaction tx = session.beginTransaction();
   ```

2. **Committing the Transaction**: 
   - Once all operations are successfully executed, the transaction should be committed using `transaction.commit()`.
   ```java
   tx.commit();
   ```

3. **Rolling Back the Transaction**:
   - If there is an exception or failure, you can roll back the transaction to undo all changes made within the current session.
   ```java
   tx.rollback();
   ```

4. **Auto-commit**: 
   - If you are using Hibernate with JDBC, you can control the auto-commit setting via Hibernate's `hibernate.jdbc.autocommit` property. By default, auto-commit is off in Hibernate.

5. **Transaction Boundaries**:
   - A transaction starts with `beginTransaction()` and ends with either `commit()` or `rollback()`. 
   - It's important to ensure that every transaction is either committed or rolled back to maintain data integrity.

6. **Declarative Transaction Management**: 
   - In a Spring-based application, you can use Spring's `@Transactional` annotation to manage transactions declaratively. The transaction is automatically managed at the service layer.
   ```java
   @Transactional
   public void someServiceMethod() {
       // Perform database operations
   }
   ```

### 746. **How can we mark an entity/collection as immutable in Hibernate?**

In Hibernate, to mark an entity or collection as immutable, you use the `@Immutable` annotation. This annotation indicates that the entity or collection cannot be modified once it's saved. It also helps Hibernate optimize queries as it knows the data won't change.

1. **Marking an entity as immutable**:
   You can annotate your entity class with `@Immutable` to indicate that the entity's state is immutable.
   ```java
   @Entity
   @Immutable
   public class Product {
       @Id
       private Long id;
       private String name;
   }
   ```

2. **Marking a collection as immutable**:
   Similarly, you can mark a collection (such as a list, set, or map) as immutable by using `@Immutable` on the collection mapping:
   ```java
   @OneToMany
   @Immutable
   private Set<Order> orders;
   ```

By using the `@Immutable` annotation, Hibernate will ensure that no modifications can be made to the entity or collection once it's persisted, which can improve performance by avoiding unnecessary dirty checking.

### 747. **What are the different options to retrieve an object from database in Hibernate?**

Hibernate provides several ways to retrieve objects from the database. Here are the most common approaches:

1. **Using `session.get()`**:
   - The `session.get()` method retrieves an object by its primary key.
   - It returns `null` if the entity with the given ID does not exist.
   ```java
   Employee emp = session.get(Employee.class, 1L);
   ```

2. **Using `session.load()`**:
   - Similar to `get()`, but it returns a proxy object if the entity is not yet loaded (lazy loading).
   - If the entity doesn't exist, it throws an exception (`ObjectNotFoundException`).
   ```java
   Employee emp = session.load(Employee.class, 1L);
   ```

3. **Using HQL (Hibernate Query Language)**:
   - You can use HQL to retrieve objects based on specific conditions.
   - This allows for more complex queries, such as joins or filtering.
   ```java
   Query query = session.createQuery("from Employee where name = :name");
   query.setParameter("name", "John");
   List<Employee> employees = query.list();
   ```

4. **Using Criteria API**:
   - The Criteria API provides a programmatic way to build queries. It is used for dynamic queries where conditions may change.
   ```java
   Criteria criteria = session.createCriteria(Employee.class);
   criteria.add(Restrictions.eq("name", "John"));
   List<Employee> employees = criteria.list();
   ```

5. **Using `session.createQuery()` with Native SQL**:
   - You can also use native SQL to query the database.
   ```java
   SQLQuery sqlQuery = session.createSQLQuery("SELECT * FROM employee WHERE name = :name");
   sqlQuery.setParameter("name", "John");
   List<Employee> employees = sqlQuery.list();
   ```

6. **Using `session.createNativeQuery()`** (JPA 2.0+):
   - With JPA, you can use native SQL queries directly.
   ```java
   List<Employee> employees = session.createNativeQuery("SELECT * FROM Employee", Employee.class).getResultList();
   ```

### 748. **How can we auto-generate primary key in Hibernate?**

In Hibernate, primary keys can be auto-generated using different strategies, which Hibernate manages for you. The strategies can be specified using the `@GeneratedValue` annotation.

**Here are the different strategies to auto-generate primary keys:**

1. **GenerationType.IDENTITY**:
   - Uses an auto-increment field in the database (typically in MySQL or PostgreSQL).
   - The primary key is generated by the database when the record is inserted.
   ```java
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   ```

2. **GenerationType.SEQUENCE**:
   - Uses a sequence in the database to generate unique primary key values (commonly used in Oracle, PostgreSQL).
   - This approach requires the database to have a sequence.
   ```java
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen")
   @SequenceGenerator(name = "seq_gen", sequenceName = "employee_seq")
   private Long id;
   ```

3. **GenerationType.TABLE**:
   - Uses a database table to generate primary key values. It involves using a table dedicated to storing primary key values, which Hibernate will update.
   ```java
   @Id
   @GeneratedValue(strategy = GenerationType.TABLE, generator = "table_gen")
   @TableGenerator(name = "table_gen", table = "hibernate_sequence", pkColumnName = "seq_name", valueColumnName = "seq_value")
   private Long id;
   ```

4. **GenerationType.AUTO**:
   - Hibernate will choose the best strategy based on the database dialect. This strategy might choose **IDENTITY**, **SEQUENCE**, or **TABLE** depending on what the database supports.
   ```java
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;
   ```

The `@GeneratedValue` annotation is used in conjunction with the `@Id` annotation to specify how the primary key should be generated when a new entity is persisted.

### 749. **How will you re-attach an object in Detached state in Hibernate?**

In Hibernate, when an object is in the **detached state**, it means that the object is no longer associated with a Hibernate session but still has its state (i.e., it has been serialized and deserialized or the session has been closed).

To re-attach an object from a detached state to the current session (make it persistent again), you can use one of the following methods:

1. **Using `session.update()`**:
   - The `update()` method can be used to re-attach a detached object to the session.
   - This method forces the object to be re-synchronized with the current session.
   ```java
   session.update(detachedObject);
   ```

2. **Using `session.merge()`**:
   - The `merge()` method can be used to either attach the object if it is detached or to update it if it has been modified.
   - It will return the persistent instance (useful if the detached object does not match the persistent version).
   ```java
   Object mergedObject = session.merge(detachedObject);
   ```

3. **Using `session.saveOrUpdate()`**:
   - The `saveOrUpdate()` method can be used to either save or update the entity based on whether it exists in the current session or not.
   ```java
   session.saveOrUpdate(detachedObject);
   ```

The `merge()` method is usually recommended for handling detached objects because it ensures the consistency of the object, regardless of whether it's new or modified.

---

### 750. **What is the first level of cache in Hibernate?**

The **first level cache** in Hibernate is associated with the **Session** object. It is enabled by default and is used to store objects that have been loaded or saved during the current session. 

**Key characteristics of the first-level cache:**
- **Session-specific**: The cache is limited to the session. Each session has its own cache and does not share it with other sessions.
- **Automatic**: Whenever an entity is loaded, updated, or saved within a session, it is automatically cached in the session's first-level cache.
- **Eviction**: The first-level cache is automatically cleared when the session is closed, or it can be manually cleared using `session.clear()`.
- **Object Identity**: If the same entity is requested multiple times during the session, the first-level cache ensures that only one instance of the entity is created in the session, providing **object identity**.

This cache helps to improve performance by reducing the number of database queries within a session.

---

### 751. **What are the different second-level caches available in Hibernate?**

The **second-level cache** in Hibernate is a session factory-wide cache and is shared across sessions. It is used to store data that doesn't change frequently, improving performance by reducing database calls for commonly accessed data.

Some popular second-level cache providers are:

1. **EHCache**:
   - EHCache is one of the most commonly used second-level cache providers. It's a robust, flexible, and highly configurable caching solution.
   - It can be configured for both local (in-memory) and distributed caching scenarios.
   
2. **Infinispan**:
   - Infinispan is a highly scalable, distributed caching system that can be used as a second-level cache.
   - It provides advanced features like distributed caching, clustering, and persistence support.
   
3. **OSCache**:
   - OSCache is another option for second-level caching in Hibernate. It supports basic caching capabilities, similar to EHCache.

4. **JCache (JSR-107)**:
   - JCache is the official caching standard for Java. Hibernate supports JCache as a second-level cache provider, which allows you to integrate third-party caching systems like Hazelcast, Apache Ignite, and others.

5. **Redis**:
   - Redis is often used as a caching solution. It can be integrated with Hibernate for second-level caching, providing a fast, in-memory data store.

**Configuring second-level cache** typically requires enabling it in Hibernate configuration (`hibernate.cfg.xml`) and choosing the cache provider.

---

### 752. **Which is the default transaction factory in Hibernate?**

The **default transaction factory** in Hibernate is the **JDBC transaction factory**. It is used when Hibernate interacts with the underlying database via JDBC, managing database transactions such as `begin`, `commit`, and `rollback`.

To use it, you don't need to explicitly configure anything, as Hibernate automatically uses it unless you specifically choose another transaction management option.

To configure a custom transaction factory (such as JTA or others), you can modify the Hibernate configuration like this:
```xml
<property name="hibernate.transaction.factory_class">
   org.hibernate.transaction.JDBCTransactionFactory
</property>
```

For more advanced transaction management (such as JTA or Spring integration), you may configure a different transaction factory, but by default, **JDBCTransactionFactory** is used.

---

### 753. **What are the options to disable second-level cache in Hibernate?**

To **disable the second-level cache** in Hibernate, you can configure your Hibernate settings as follows:

1. **In `hibernate.cfg.xml`**:
   - You can explicitly disable the second-level cache by setting the property `hibernate.cache.use_second_level_cache` to `false`.
   ```xml
   <property name="hibernate.cache.use_second_level_cache">false</property>
   ```

2. **In `hibernate.properties`**:
   - You can also disable the cache by setting the property in the `hibernate.properties` file:
   ```properties
   hibernate.cache.use_second_level_cache=false
   ```

3. **Using `hibernate.cache.region.factory_class`**:
   - If the cache region is not specified or if it's set to `org.hibernate.cache.internal.NoCacheProvider`, the second-level cache will be effectively disabled:
   ```xml
   <property name="hibernate.cache.region.factory_class">org.hibernate.cache.internal.NoCacheProvider</property>
   ```

4. **For Specific Entity**:
   - You can also disable caching for specific entities or collections by setting the `@Cache` annotation as `false`.
   ```java
   @Entity
   @Cache(usage = CacheConcurrencyStrategy.NONE)
   public class Employee {
       // entity code here
   }
   ```

Disabling the second-level cache is typically useful in cases where you don't want to store data in memory or need the freshest data from the database without worrying about caching issues.

### 754. **What are the different fetching strategies in Hibernate?**

Hibernate supports two primary fetching strategies:

1. **Eager Fetching**:
   - Eager fetching means that Hibernate will immediately load the associated entities or collections when the parent entity is loaded. This can result in additional database queries if there are many associations, potentially affecting performance.
   - **Example**:
     ```java
     @OneToMany(fetch = FetchType.EAGER)
     private List<Order> orders;
     ```
   - This will load the `orders` list as soon as the parent object (e.g., `Customer`) is loaded.

2. **Lazy Fetching**:
   - Lazy fetching means that the associated entities or collections are not loaded immediately. They are only fetched when accessed, which can reduce the initial database query overhead.
   - **Example**:
     ```java
     @OneToMany(fetch = FetchType.LAZY)
     private List<Order> orders;
     ```
   - In this case, the `orders` list is only fetched when explicitly accessed, rather than being loaded at the same time as the parent object.

3. **Join Fetching** (for specific queries):
   - Using a `JOIN FETCH` in HQL or Criteria queries allows you to eagerly fetch associated collections or entities for that particular query without modifying the mapping strategy globally.
   - **Example**:
     ```java
     Query query = session.createQuery("FROM Customer c JOIN FETCH c.orders");
     ```

---

### 755. **What is the difference between Immediate fetching and Lazy collection fetching?**

- **Immediate Fetching**:
  - Immediate fetching is also called **eager fetching**. When an association (like a collection or a related entity) is fetched eagerly, Hibernate loads the associated data along with the parent entity in the same query or in an additional query depending on the fetch type defined (e.g., `FetchType.EAGER`).
  - **Advantage**: The associated data is readily available when the parent entity is loaded, preventing potential lazy loading issues.
  - **Disadvantage**: It can lead to performance issues if many associations are fetched eagerly, leading to unnecessary database queries (N+1 problem).

- **Lazy Fetching**:
  - In lazy fetching (also known as lazy loading), the associated entities or collections are not loaded immediately when the parent entity is loaded. Instead, they are loaded only when explicitly accessed, which can help in optimizing performance.
  - **Advantage**: It improves performance by avoiding unnecessary queries when the associated data is not needed.
  - **Disadvantage**: It can lead to `LazyInitializationException` if the session is closed before accessing the lazy-loaded associations.

---

### 756. **What is ‘Extra lazy fetching’ in Hibernate?**

**Extra lazy fetching** is a strategy in Hibernate that further optimizes **lazy loading** of collections. When `extra-lazy` is enabled, Hibernate avoids loading the collection or related entities until absolutely necessary. Specifically, it defers fetching elements in the collection until they are accessed individually (rather than fetching them in batches).

**Key features of Extra Lazy Fetching**:
- For collections, Hibernate will not load the entire collection when the parent entity is fetched. Instead, it will load the collection only when individual elements are accessed.
- It also avoids executing an additional query to fetch the entire collection when the collection is iterated or when its size is fetched.

**Configuration**:
To enable extra-lazy fetching, use the `@OneToMany` or `@ManyToMany` annotation along with the `fetch = FetchType.LAZY` and `@BatchSize(size = x)` or `@Fetch(FetchMode.EXTRA)`.

Example:
```java
@OneToMany(fetch = FetchType.LAZY)
@Fetch(FetchMode.EXTRA)
private Set<Order> orders;
```

---

### 757. **How can we check if a collection is initialized or not under Lazy Initialization strategy?**

You can check if a collection is initialized by using the `Hibernate.isInitialized()` method. This method returns `true` if the collection has been loaded (initialized) from the database and `false` if it is still a proxy and has not been loaded yet.

Example:
```java
if (Hibernate.isInitialized(customer.getOrders())) {
    System.out.println("Orders are initialized.");
} else {
    System.out.println("Orders are not initialized.");
}
```

You can also check if a collection is initialized by checking if the collection is empty or not. However, `Hibernate.isInitialized()` is a more explicit way to handle this.

---

### 758. **What are the different strategies for cache mapping in Hibernate?**

Hibernate provides several strategies for caching the entities or collections to improve the performance of database access. The following cache mapping strategies can be used in Hibernate:

1. **Read-Only Cache**:
   - This strategy is used when the entity's state never changes, i.e., it is not updated after being loaded into memory. This cache strategy is highly efficient because the cached object is never modified.
   - Example:
     ```java
     @Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
     ```

2. **Read-Write Cache**:
   - This strategy is used for entities that may be modified, but the cache will reflect changes immediately. The cache is updated when the entity is updated in the database.
   - Example:
     ```java
     @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
     ```

3. **Transactional Cache**:
   - This strategy is used for entities where changes should be visible only within the context of a transaction. It is similar to `READ_WRITE` but ensures that changes are not visible to other transactions until committed.
   - Example:
     ```java
     @Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
     ```

4. **Non-Strict Read-Write Cache**:
   - This strategy provides an intermediate level of cache consistency. It is similar to the `READ_WRITE` strategy, but updates to the cache are not always synchronized with the database. It works well for situations where slight inconsistencies are tolerable.
   - Example:
     ```java
     @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
     ```

5. **Nonstrict Read-Write (with custom time-to-live or eviction policies)**:
   - Hibernate allows the configuration of a custom eviction policy for cache entries, setting how long data stays in cache before it is evicted or refreshed.

6. **Second-Level Cache**:
   - It is a session factory-wide cache that stores objects between sessions. It is not restricted to a particular session and can be used to cache objects globally across different sessions.
   - Common cache providers include **EHCache**, **Infinispan**, and **OSCache**.

7. **Query Cache**:
   - A query cache stores the results of queries, which can be re-used when the same query is executed again, provided the underlying data hasn't changed. It is commonly used alongside the second-level cache.

### 759. **What is the difference between a Set and a Bag in Hibernate?**

In Hibernate, both `Set` and `Bag` are used to represent a collection of associated entities. The key differences between the two are related to how they handle uniqueness and ordering:

1. **Set**:
   - A `Set` is a collection that does not allow duplicate elements. In Hibernate, a `Set` collection is mapped to a database table with a unique constraint on the association, which ensures that each element appears only once.
   - It is an **unordered** collection, meaning that it does not guarantee any specific order of elements.
   - Example:
     ```java
     @OneToMany
     @JoinColumn(name = "customer_id")
     private Set<Order> orders;
     ```

2. **Bag**:
   - A `Bag` is a collection that **allows duplicates**. It is used when you do not need the uniqueness of a `Set`. This means multiple instances of the same element can exist in the collection.
   - A `Bag` is also **unordered** and does not maintain any specific order of elements.
   - Example:
     ```java
     @OneToMany
     @JoinColumn(name = "customer_id")
     private Bag<Order> orders;
     ```

**Summary**:
- **Set**: No duplicates, unordered.
- **Bag**: Allows duplicates, unordered.

---

### 760. **How can we monitor the performance of Hibernate in an application?**

To monitor Hibernate performance, you can utilize several tools and techniques:

1. **Hibernate Statistics**:
   - Hibernate provides built-in support to track statistics related to sessions, queries, transactions, and more. You can enable Hibernate statistics in the configuration to gather performance metrics.
   - Example:
     ```java
     SessionFactory sessionFactory = configuration.buildSessionFactory();
     Session session = sessionFactory.openSession();
     session.getSessionFactory().getStatistics().setStatisticsEnabled(true);
     System.out.println(sessionFactory.getStatistics().getEntityCount());
     ```

2. **SQL Logging**:
   - You can enable SQL logging in Hibernate to view the SQL queries being executed. This helps in identifying performance bottlenecks related to the number of queries or complex SQL operations.
   - To enable logging, modify `hibernate.cfg.xml` or use loggers in your `log4j` or `logback` configuration:
     ```xml
     <property name="hibernate.show_sql">true</property>
     <property name="hibernate.format_sql">true</property>
     ```

3. **Hibernate Profiler**:
   - A Hibernate Profiler tool, such as **Hibernate Profiler** or **JProfiler**, can be used to capture detailed insights into session interactions, database queries, transaction performance, and more.
   
4. **Database Monitoring Tools**:
   - Use database monitoring tools like **MySQL Workbench**, **Oracle Enterprise Manager**, or **pgAdmin** to monitor SQL query execution times, query count, and resource utilization.

5. **Third-Party Tools**:
   - You can use APM (Application Performance Management) tools such as **New Relic**, **AppDynamics**, or **Dynatrace** to monitor the overall performance of your Hibernate-based application in real-time.

6. **Query Optimization**:
   - Use Hibernate's `Criteria` and `HQL` to write efficient queries. Ensure proper indexing in the database, avoid N+1 query problems, and leverage caching strategies like second-level cache or query cache.

---

### 761. **How can we check if an Object is in Persistent, Detached, or Transient state in Hibernate?**

In Hibernate, the state of an object can be checked using the following methods:

1. **Persistent State**:
   - The object is in the persistent state when it is associated with a Hibernate session, and any changes to it will be automatically synchronized with the database at the end of the transaction.
   - **Check**: If the object is in the session and has a valid ID (database primary key).
   - Example:
     ```java
     Session session = sessionFactory.openSession();
     Employee emp = session.get(Employee.class, 1);
     if (session.contains(emp)) {
         System.out.println("Persistent State");
     }
     ```

2. **Detached State**:
   - The object is in the detached state when it was once associated with a session but is no longer part of the current session. Changes made to the detached object are not automatically synchronized with the database until reattached.
   - **Check**: If the object has an ID and is no longer part of the current session.
   - Example:
     ```java
     Session session = sessionFactory.openSession();
     Employee emp = session.get(Employee.class, 1);
     session.close();
     // After closing the session
     if (!session.contains(emp)) {
         System.out.println("Detached State");
     }
     ```

3. **Transient State**:
   - The object is in the transient state when it is a new object and has not been saved or persisted to the database yet. It is not associated with any session.
   - **Check**: If the object does not have an ID (or the ID is null), it is in the transient state.
   - Example:
     ```java
     Employee emp = new Employee();
     if (emp.getId() == null) {
         System.out.println("Transient State");
     }
     ```

---

### 762. **What is ‘the inverse side of association’ in a mapping?**

In Hibernate, when you have an association between two entities (e.g., `OneToMany`, `ManyToMany`), one side is typically considered the "owner" of the association, and the other side is the "inverse" side.

- **Owner Side**: The side that is responsible for managing the association, i.e., it will have the foreign key or join column.
- **Inverse Side**: The side that does not manage the association and is not responsible for maintaining the foreign key or join column. In other words, the inverse side is not responsible for saving, updating, or deleting the associated entities.

You can specify the inverse side in Hibernate with the `@OneToMany` or `@ManyToMany` annotation using the `mappedBy` attribute, which tells Hibernate that the association is managed by the other side.

**Example:**
```java
@Entity
public class Customer {
    @OneToMany(mappedBy = "customer")
    private Set<Order> orders;
}

@Entity
public class Order {
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
```

In this example:
- The **`Order`** entity has the foreign key (`customer_id`), so it is the owner side of the association.
- The **`Customer`** entity has the `mappedBy` attribute in the `@OneToMany` annotation, making it the **inverse** side of the association.

### 763. **What is ORM metadata?**

**ORM (Object-Relational Mapping) metadata** in Hibernate refers to the configuration information that defines how the Java objects (entities) are mapped to the database tables. This metadata provides the necessary details about the object-to-table mapping and relationship details (like primary keys, foreign keys, and column mappings). 

ORM metadata can be provided in several ways:
- **XML-based mapping**: In this case, the metadata is defined in XML configuration files (e.g., `hibernate.cfg.xml` and `.hbm.xml`).
- **Annotations-based mapping**: With Java annotations, ORM metadata can be provided directly in the entity classes.

For example, annotations like `@Entity`, `@Table`, `@Id`, and `@Column` in Hibernate define the ORM metadata for a class.

---

### 764. **What is the difference between load() and get() method in Hibernate?**

Both `load()` and `get()` are methods used to retrieve entities in Hibernate, but they behave differently in certain scenarios:

1. **`get()` Method**:
   - It retrieves an object from the database immediately when invoked.
   - It can return `null` if the entity does not exist in the database.
   - `get()` always hits the database to fetch the entity.
   - Example:
     ```java
     Employee emp = session.get(Employee.class, 1);
     ```

2. **`load()` Method**:
   - It retrieves a proxy object (lazy loading) for the entity when invoked.
   - If the entity is not found, it throws an exception (`ObjectNotFoundException`), unlike `get()`, which returns `null`.
   - `load()` uses lazy loading, so it may not immediately hit the database if the object is accessed later.
   - Example:
     ```java
     Employee emp = session.load(Employee.class, 1);
     ```

**Key Differences**:
- **Return value**: `get()` returns `null` if the entity does not exist; `load()` throws an exception (`ObjectNotFoundException`) if the entity is not found.
- **Loading behavior**: `get()` loads the object immediately, while `load()` uses a proxy and delays loading until the entity is accessed.

---

### 765. **When should we use get() method or load() method in Hibernate?**

- **Use `get()`** when:
  - You are sure that the entity may not exist, and you want to avoid exceptions.
  - You want to retrieve the object immediately and are okay with potentially hitting the database.
  - You are working with a scenario where lazy loading is not required.

- **Use `load()`** when:
  - You are certain the entity exists and do not mind working with a proxy (lazy loading).
  - You expect the object to be loaded only when its properties are accessed.
  - You want to use lazy loading and do not want to immediately fetch the entity from the database.

In general, `get()` is safer and more commonly used in most scenarios since it gracefully handles cases where the entity might not exist, whereas `load()` is suited for situations where you know the entity exists and require lazy loading.

---

### 766. **What is a derived property in Hibernate?**

A **derived property** in Hibernate refers to a property of an entity that is not directly mapped to a column in the database but is computed based on other properties or database columns. It is calculated dynamically, usually within the Java code or through custom queries, rather than being persisted as part of the entity.

For example:
- A derived property can be a calculated field, such as the total price based on quantity and unit price, or the full name derived from the first name and last name.

**Example**:
```java
@Entity
public class Product {
    @Id
    private Long id;
    
    private int quantity;
    private double unitPrice;

    @Transient  // This annotation ensures that this field is not mapped to the database
    public double getTotalPrice() {
        return quantity * unitPrice;
    }
}
```
In this example, `getTotalPrice()` is a derived property because it calculates a value based on other fields (`quantity` and `unitPrice`) but is not stored in the database.

---

### 767. **How can we use Named Query in Hibernate?**

A **Named Query** in Hibernate is a query that is defined with a specific name, and it is typically written in HQL (Hibernate Query Language) or SQL. Named Queries can be declared in the `hibernate.cfg.xml` file or using annotations in the entity classes. Named queries allow you to reuse predefined queries efficiently across the application.

#### 1. **Using Named Queries with Annotations**:

You can define a named query in an entity class using the `@NamedQuery` annotation.

Example:
```java
@Entity
@NamedQuery(name = "Employee.findByDepartment", 
              query = "FROM Employee e WHERE e.department = :department")
public class Employee {
    @Id
    private Long id;
    private String name;
    private String department;
}
```

#### 2. **Using Named Queries with XML**:

Alternatively, you can define named queries in the `hibernate.cfg.xml` or `hbm.xml` file.

Example:
```xml
<hibernate-mapping>
    <class name="Employee" table="employees">
        <query name="Employee.findByDepartment">
            <![CDATA[
                FROM Employee e WHERE e.department = :department
            ]]>
        </query>
    </class>
</hibernate-mapping>
```

#### 3. **Using Named Queries in Code**:

Once defined, named queries can be used in code by referring to the query name.

Example:
```java
Session session = sessionFactory.openSession();
Query query = session.getNamedQuery("Employee.findByDepartment");
query.setParameter("department", "Sales");
List<Employee> employees = query.list();
```

#### Benefits of Named Queries:
- **Reusability**: Named queries can be reused across the application, preventing duplication of query definitions.
- **Compile-time checking**: Since they are predefined, named queries can be checked for errors at compile time (when using annotations).
- **Performance**: Hibernate caches named queries and their execution plans, improving performance over time.


### 768. **What are the two locking strategies in Hibernate?**

In Hibernate, there are two main **locking strategies** used to manage concurrent access to the data and prevent issues like **lost updates** or **dirty reads** in a multi-user environment:

1. **Optimistic Locking**:
   - **Optimistic Locking** assumes that no other transactions will modify the data during the current transaction. It doesn't lock the database record when reading it but instead checks whether the data was modified by another transaction when the data is updated.
   - This is typically achieved by adding a **version column** to the entity, which is automatically updated every time the entity is modified.
   - **How it works**: When an entity is updated, Hibernate checks if the version number in the database is the same as the version in the entity. If they don't match, it indicates that another transaction modified the data, leading to a `StaleObjectStateException`.
   
   Example of using optimistic locking:
   ```java
   @Version
   private int version;
   ```

2. **Pessimistic Locking**:
   - **Pessimistic Locking** assumes that conflicts between transactions are likely and that the data should be locked when it is read. This prevents other transactions from modifying the data until the lock is released.
   - Pessimistic locking is achieved using database-level locks, and Hibernate provides support for this through the `LockMode` class.
   - **Locking modes**: 
     - **PESSIMISTIC_READ**: Allows shared read locks.
     - **PESSIMISTIC_WRITE**: Locks the record exclusively for writing, preventing other transactions from reading or writing.
   
   Example of using pessimistic locking:
   ```java
   session.lock(entity, LockMode.PESSIMISTIC_WRITE);
   ```

---

### 769. **What is the use of version number in Hibernate?**

The **version number** in Hibernate is used to implement **optimistic locking**. It is a mechanism that helps to detect concurrent updates to an entity. When a version column is present, Hibernate uses it to track the version of the entity, ensuring that updates to the entity happen in a controlled manner.

- **Purpose of the version number**:
  - Prevents the **lost update** problem (i.e., when two transactions update the same record simultaneously without knowing about each other).
  - Ensures that when a transaction tries to save changes, it checks whether the entity has been modified by another transaction.
  
- **How it works**: Each time an entity is modified, its version number is incremented automatically. When an update occurs, Hibernate compares the version number of the entity in the database with the version number of the entity in the session. If the version numbers match, the update is allowed to proceed; otherwise, a `StaleObjectStateException` is thrown to signal a conflict.
  
  Example:
  ```java
  @Version
  private int version;
  ```

---

### 770. **What is the use of session.lock() method in Hibernate?**

The `session.lock()` method in Hibernate is used to apply **pessimistic locking** to an entity. It locks the entity in the current session to prevent other transactions from modifying the same entity concurrently. This method helps in cases where you want to guarantee that a record cannot be updated by other transactions during your transaction.

- **Purpose**: The `lock()` method is used to enforce **pessimistic locking** and manage concurrent access to entities, ensuring data consistency by locking the entity at the database level.

- **How it works**: The method accepts the entity and a lock mode. Lock modes like `LockMode.PESSIMISTIC_READ` and `LockMode.PESSIMISTIC_WRITE` are used to lock the record for reading or writing, respectively.

Example:
```java
session.lock(entity, LockMode.PESSIMISTIC_WRITE);
```
In this case, `LockMode.PESSIMISTIC_WRITE` locks the entity, ensuring no other transaction can modify it until the current transaction is complete.

---

### 771. **What inheritance mapping strategies are supported by Hibernate?**

Hibernate provides several strategies for mapping **inheritance relationships** between classes to database tables. These strategies are used to handle cases where a class hierarchy (superclass and subclass) needs to be mapped to one or more tables in the database. The four primary inheritance mapping strategies in Hibernate are:

1. **Single Table Inheritance** (`@Inheritance(strategy = InheritanceType.SINGLE_TABLE)`):
   - All classes in the hierarchy are mapped to a single database table.
   - A discriminator column is used to differentiate between the different types of entities in the hierarchy.
   - **Advantages**: Efficient in terms of queries because all data is in one table.
   - **Disadvantages**: Can result in many `NULL` values in the table if subclasses have different properties.
   
   Example:
   ```java
   @Entity
   @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
   @DiscriminatorColumn(name = "vehicle_type")
   public class Vehicle { ... }
   
   @Entity
   public class Car extends Vehicle { ... }
   
   @Entity
   public class Bike extends Vehicle { ... }
   ```

2. **Table per Class Inheritance** (`@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)`):
   - Each class in the hierarchy is mapped to its own separate table.
   - This strategy doesn't use a discriminator column and results in different tables for each entity in the inheritance hierarchy.
   - **Advantages**: Each class has its own table, avoiding `NULL` values.
   - **Disadvantages**: Can lead to inefficient queries when joining tables for subclasses.

   Example:
   ```java
   @Entity
   @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
   public class Vehicle { ... }
   
   @Entity
   public class Car extends Vehicle { ... }
   
   @Entity
   public class Bike extends Vehicle { ... }
   ```

3. **Joined Subclass Inheritance** (`@Inheritance(strategy = InheritanceType.JOINED)`):
   - A separate table is created for each class in the hierarchy, but the tables are joined in queries using foreign keys.
   - The superclass is stored in one table, and the subclass table contains a foreign key referencing the superclass table.
   - **Advantages**: Avoids redundancy in storage, no `NULL` values in the subclass tables.
   - **Disadvantages**: The performance may be impacted due to the need to perform joins between the tables.
   
   Example:
   ```java
   @Entity
   @Inheritance(strategy = InheritanceType.JOINED)
   public class Vehicle { ... }
   
   @Entity
   public class Car extends Vehicle { ... }
   
   @Entity
   public class Bike extends Vehicle { ... }
   ```

4. **Mapped Superclass** (`@MappedSuperclass`):
   - This is not an inheritance strategy per se but is used for mapping the properties of a superclass to a table, without the superclass being an entity itself.
   - The superclass cannot be queried directly, but its fields can be inherited by its subclasses.
   
   Example:
   ```java
   @MappedSuperclass
   public abstract class Vehicle {
       private String make;
       private String model;
       // No database table is created for this class.
   }
   
   @Entity
   public class Car extends Vehicle { ... }
   
   @Entity
   public class Bike extends Vehicle { ... }
   ```

**Summary**:
- **Single Table**: One table for all entities in the hierarchy.
- **Table per Class**: Separate tables for each class in the hierarchy.
- **Joined Subclass**: Separate tables with foreign keys for each class, joined at runtime.
- **Mapped Superclass**: No table for the superclass, but its fields are inherited by subclasses.

These strategies provide flexibility in how inheritance is mapped in a relational database, each having its own advantages and trade-offs regarding performance and design.

# 🔹  RESTful Web Services

### Question 271: What are RESTful web services?

**Answer (30 seconds):**
* REST stands for Representational State Transfer
* Architectural style for designing web services using HTTP protocol
* Resources identified by URLs, operations via HTTP methods
* Stateless communication between client and server
* Uses standard HTTP status codes and supports multiple data formats

```java
@RestController
@RequestMapping("/api/users")
public class UserController {
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.findById(id);
    }
}
```

---

### Question 272: What are the principles of REST?

**Answer (35 seconds):**
* **Stateless**: Each request contains all necessary information
* **Client-Server**: Separation of concerns between client and server
* **Cacheable**: Responses should be cacheable when appropriate
* **Uniform Interface**: Consistent way to interact with resources
* **Layered System**: Architecture can have multiple layers
* **Code on Demand**: Optional - server can send executable code

```java
// Uniform interface example
GET    /api/users     // Get all users
GET    /api/users/1   // Get user by ID
POST   /api/users     // Create new user
PUT    /api/users/1   // Update user
DELETE /api/users/1   // Delete user
```

---

### Question 273: What are HTTP methods used in REST?

**Answer (30 seconds):**
* **GET**: Retrieve data, safe and idempotent
* **POST**: Create new resources, not idempotent
* **PUT**: Update/replace entire resource, idempotent
* **PATCH**: Partial update of resource
* **DELETE**: Remove resource, idempotent
* **HEAD**: Get headers only, **OPTIONS**: Get allowed methods

```java
@GetMapping("/users")           // Retrieve users
@PostMapping("/users")          // Create user
@PutMapping("/users/{id}")      // Update user
@PatchMapping("/users/{id}")    // Partial update
@DeleteMapping("/users/{id}")   // Delete user
```

---

### Question 274: What is JSON?

**Answer (25 seconds):**
* JavaScript Object Notation - lightweight data interchange format
* Human-readable text format for data exchange
* Language-independent but uses JavaScript-like syntax
* Supports objects, arrays, strings, numbers, booleans, null
* Most popular format for REST APIs

```java
// Java object to JSON
{
    "id": 1,
    "name": "John Doe",
    "email": "john@example.com",
    "active": true,
    "roles": ["user", "admin"]
}

// Spring Boot automatically converts
@GetMapping("/user")
public User getUser() { return new User("John", "john@email.com"); }
```

---

### Question 275: What is XML?

**Answer (25 seconds):**
* eXtensible Markup Language - markup language for data representation
* Uses tags to define structure and meaning of data
* More verbose than JSON but supports attributes and namespaces
* Self-documenting with schema validation capabilities
* Still used in enterprise applications and SOAP services

```xml
<?xml version="1.0" encoding="UTF-8"?>
<user>
    <id>1</id>
    <name>John Doe</name>
    <email>john@example.com</email>
    <active>true</active>
</user>
```

---

### Question 276: What is the difference between JSON and XML?

**Answer (35 seconds):**
* **Size**: JSON is more compact, XML is verbose
* **Parsing**: JSON faster to parse, XML requires more processing
* **Data Types**: JSON supports native types, XML treats everything as strings
* **Arrays**: JSON has native array support, XML needs workarounds
* **Attributes**: XML supports attributes, JSON doesn't
* **Usage**: JSON preferred for REST APIs, XML for enterprise systems

```java
// JSON - compact
{"name": "John", "age": 30}

// XML - verbose
<person>
    <name>John</name>
    <age>30</age>
</person>
```

---

### Question 277: What is JAX-RS?

**Answer (30 seconds):**
* Java API for RESTful Web Services
* Standard Java specification for building REST APIs
* Annotation-based approach for defining REST endpoints
* Implementations: Jersey, RESTEasy, Apache CXF
* Provides annotations like @Path, @GET, @POST, @Produces

```java
@Path("/users")
public class UserResource {
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(@PathParam("id") Long id) {
        return userService.findById(id);
    }
}
```

---

### Question 278: What is Spring REST?

**Answer (30 seconds):**
* Spring Framework's approach to building RESTful web services
* Uses @RestController, @RequestMapping annotations
* Built on Spring MVC with automatic JSON/XML conversion
* Provides features like validation, exception handling, content negotiation
* More popular than JAX-RS in Spring ecosystem

```java
@RestController
@RequestMapping("/api")
public class UserController {
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User saved = userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}
```

---

### Question 279: What is SOAP vs REST?

**Answer (40 seconds):**
* **SOAP**: Protocol with strict standards, XML-only, stateful
* **REST**: Architectural style, multiple formats, stateless
* **Performance**: REST is faster and lighter
* **Security**: SOAP has built-in security, REST relies on HTTPS
* **Caching**: REST supports caching, SOAP doesn't
* **Usage**: REST for web/mobile apps, SOAP for enterprise systems

```java
// REST - simple
@GetMapping("/users/{id}")
public User getUser(@PathVariable Long id) { return user; }

// SOAP - complex with WSDL, envelope, headers
```

---

### Question 280: What is API versioning?

**Answer (35 seconds):**
* Strategy to manage changes in API without breaking existing clients
* **URL Versioning**: /api/v1/users, /api/v2/users
* **Header Versioning**: Accept: application/vnd.api.v1+json
* **Parameter Versioning**: /api/users?version=1
* **Media Type Versioning**: Content-Type with version info
* Maintains backward compatibility while allowing evolution

```java
// URL versioning
@RequestMapping("/api/v1/users")
public class UserV1Controller { }

@RequestMapping("/api/v2/users")
public class UserV2Controller { }

// Header versioning
@GetMapping(value = "/users", headers = "API-Version=1")
public List<UserV1> getUsersV1() { }
```

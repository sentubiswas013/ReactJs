# 🔹 Microservices and Web Services

## RESTful Services 

### 286. What are RESTful web services?

**Answer:** RESTful web services are architectural style web services that follow REST principles. They use HTTP methods for operations, are stateless, cacheable, and provide a uniform interface for client-server communication using standard HTTP protocols.

**Key Characteristics:**
- **Resource-based:** Everything is treated as a resource with unique URIs
- **HTTP Methods:** Use standard HTTP verbs (GET, POST, PUT, DELETE)
- **Stateless:** Each request contains all necessary information
- **JSON/XML:** Common data exchange formats
- **Platform Independent:** Works across different technologies

**Example:**
```
GET /api/users/123        # Get user with ID 123
POST /api/users           # Create new user
PUT /api/users/123        # Update user 123
DELETE /api/users/123     # Delete user 123
```

---

### 287. What are the principles of REST?

**Answer:** Six key principles: Client-Server architecture for separation of concerns, Stateless communication where each request contains all needed information, Cacheable responses for performance, Uniform Interface using standard HTTP methods, Layered System for scalability, and Code on Demand which is optional.

**REST Principles:**

1. **Client-Server Architecture**
   - Separation of concerns
   - Independent evolution of client and server

2. **Stateless**
   - No client state stored on server
   - Each request is self-contained

3. **Cacheable**
   - Responses must define if they're cacheable
   - Improves performance and scalability

4. **Uniform Interface**
   - Standard HTTP methods and status codes
   - Resource identification through URIs

5. **Layered System**
   - Architecture can have multiple layers
   - Enables load balancing and caching

6. **Code on Demand** (Optional)
   - Server can send executable code to client
   - Rarely used in practice

---

### 288. What are HTTP methods and their usage?

**Answer:** GET retrieves data, POST creates new resources, PUT updates entire resources, PATCH partially updates resources, DELETE removes resources, HEAD gets headers only, and OPTIONS returns allowed methods. Each method has specific semantics and idempotency characteristics.

**HTTP Methods:**

| Method | Purpose | Idempotent | Safe |
|--------|---------|------------|------|
| GET | Retrieve data | Yes | Yes |
| POST | Create resource | No | No |
| PUT | Update/Replace resource | Yes | No |
| PATCH | Partial update | No | No |
| DELETE | Remove resource | Yes | No |
| HEAD | Get headers only | Yes | Yes |
| OPTIONS | Get allowed methods | Yes | Yes |

**Usage Examples:**
```java
@GetMapping("/users/{id}")
public User getUser(@PathVariable Long id) { }

@PostMapping("/users")
public User createUser(@RequestBody User user) { }

@PutMapping("/users/{id}")
public User updateUser(@PathVariable Long id, @RequestBody User user) { }

@DeleteMapping("/users/{id}")
public void deleteUser(@PathVariable Long id) { }
```

---

### 289. What is the difference between PUT and POST?

**Answer:** PUT is idempotent and updates entire resources at specific URLs, while POST is non-idempotent and creates new resources where the server determines the URL. PUT replaces the entire resource, POST can create or perform any operation.

**Key Differences:**

| Aspect | PUT | POST |
|--------|-----|------|
| **Idempotency** | Idempotent | Non-idempotent |
| **Purpose** | Update/Replace | Create/Process |
| **URL** | Client specifies | Server determines |
| **Resource** | Entire resource | Partial or new |
| **Safety** | Predictable | May have side effects |

**Examples:**
```java
// PUT - Update entire user at specific URL
PUT /api/users/123
{
  "name": "John Doe",
  "email": "john@example.com",
  "age": 30
}

// POST - Create new user, server assigns ID
POST /api/users
{
  "name": "Jane Doe",
  "email": "jane@example.com"
}
```

---

### 290. What is idempotency in REST?

**Answer:** Idempotency means multiple identical requests have the same effect as a single request. GET, PUT, DELETE are idempotent - calling them multiple times produces the same result. POST is not idempotent as it creates new resources each time.

**Idempotent Methods:**
- **GET:** Always returns same data
- **PUT:** Same update applied multiple times
- **DELETE:** Resource remains deleted
- **HEAD/OPTIONS:** No side effects

**Non-Idempotent Methods:**
- **POST:** Creates new resource each time
- **PATCH:** May have different effects

**Example:**
```java
// Idempotent - same result every time
DELETE /api/users/123  // User deleted
DELETE /api/users/123  // Still deleted (404 or 204)

// Non-idempotent - different result each time
POST /api/users {"name": "John"}  // Creates user with ID 1
POST /api/users {"name": "John"}  // Creates user with ID 2
```

---

### 291. What are HTTP status codes?

**Answer:** Status codes indicate request results: 2xx for success (200 OK, 201 Created), 3xx for redirection (301 Moved), 4xx for client errors (400 Bad Request, 404 Not Found), and 5xx for server errors (500 Internal Server Error).

**Status Code Categories:**

**2xx Success:**
- `200 OK` - Request successful
- `201 Created` - Resource created
- `204 No Content` - Success, no response body

**3xx Redirection:**
- `301 Moved Permanently` - Resource moved
- `304 Not Modified` - Use cached version

**4xx Client Error:**
- `400 Bad Request` - Invalid request
- `401 Unauthorized` - Authentication required
- `403 Forbidden` - Access denied
- `404 Not Found` - Resource not found
- `409 Conflict` - Resource conflict

**5xx Server Error:**
- `500 Internal Server Error` - Server error
- `502 Bad Gateway` - Gateway error
- `503 Service Unavailable` - Service down

---

### 292. What is content negotiation?

**Answer:** Content negotiation allows clients and servers to agree on response format using Accept headers. Clients specify preferred formats like JSON or XML, and servers respond accordingly. It enables the same endpoint to serve multiple formats.

**How it works:**
```http
# Client request
GET /api/users/123
Accept: application/json

# Server response
Content-Type: application/json
{"id": 123, "name": "John Doe"}
```

**Common Accept Headers:**
- `application/json` - JSON format
- `application/xml` - XML format
- `text/html` - HTML format
- `application/pdf` - PDF format

**Spring Boot Example:**
```java
@GetMapping(value = "/users/{id}", 
           produces = {MediaType.APPLICATION_JSON_VALUE, 
                      MediaType.APPLICATION_XML_VALUE})
public User getUser(@PathVariable Long id) {
    return userService.findById(id);
}
```

---

### 293. How do you handle versioning in REST APIs?

**Answer:** Common approaches include URL versioning (/api/v1/users), header versioning (Accept: application/vnd.api+json;version=1), query parameter versioning (?version=1), and media type versioning. URL versioning is most common and explicit.

**Versioning Strategies:**

**1. URL Versioning (Most Common):**
```
/api/v1/users
/api/v2/users
```

**2. Header Versioning:**
```http
GET /api/users
Accept: application/vnd.api+json;version=1
```

**3. Query Parameter:**
```
/api/users?version=1
```

**4. Media Type Versioning:**
```http
Accept: application/vnd.company.user-v1+json
```

**Spring Boot Implementation:**
```java
@RestController
@RequestMapping("/api/v1/users")
public class UserV1Controller { }

@RestController
@RequestMapping("/api/v2/users")
public class UserV2Controller { }
```

---

### 294. What is HATEOAS?

**Answer:** HATEOAS (Hypermedia as the Engine of Application State) means responses include links to related actions. Clients discover available operations through hypermedia links rather than prior knowledge, making APIs more discoverable and self-documenting.

**HATEOAS Response Example:**
```json
{
  "id": 123,
  "name": "John Doe",
  "email": "john@example.com",
  "_links": {
    "self": {"href": "/api/users/123"},
    "edit": {"href": "/api/users/123"},
    "delete": {"href": "/api/users/123"},
    "orders": {"href": "/api/users/123/orders"}
  }
}
```

**Benefits:**
- **Discoverability:** Clients learn available actions
- **Loose Coupling:** Reduces client-server dependencies
- **Self-Documentation:** API describes itself
- **Evolution:** Server can change URLs without breaking clients

**Spring HATEOAS Example:**
```java
@GetMapping("/users/{id}")
public EntityModel<User> getUser(@PathVariable Long id) {
    User user = userService.findById(id);
    return EntityModel.of(user)
        .add(linkTo(UserController.class).slash(id).withSelfRel())
        .add(linkTo(UserController.class).slash(id).withRel("edit"));
}
```

---

### 295. How do you secure REST APIs?

**Answer:** Use HTTPS for encryption, implement authentication (JWT tokens, OAuth), authorization for access control, input validation to prevent injection attacks, rate limiting to prevent abuse, and CORS configuration for cross-origin requests.

**Security Measures:**

**1. HTTPS/TLS:**
- Encrypt data in transit
- Prevent man-in-the-middle attacks

**2. Authentication:**
```java
// JWT Token
@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody LoginRequest request) {
    String token = jwtService.generateToken(user);
    return ResponseEntity.ok(new JwtResponse(token));
}
```

**3. Authorization:**
```java
@PreAuthorize("hasRole('ADMIN')")
@DeleteMapping("/users/{id}")
public void deleteUser(@PathVariable Long id) { }
```

**4. Input Validation:**
```java
@PostMapping("/users")
public User createUser(@Valid @RequestBody User user) {
    return userService.save(user);
}
```

**5. Rate Limiting:**
```java
@RateLimiter(name = "userService", fallbackMethod = "fallback")
@GetMapping("/users")
public List<User> getUsers() { }
```

**6. CORS Configuration:**
```java
@CrossOrigin(origins = "https://trusted-domain.com")
@RestController
public class UserController { }
```

**Security Headers:**
- `Authorization: Bearer <token>`
- `X-API-Key: <api-key>`
- `Content-Security-Policy`
- `X-Frame-Options`

---

## Summary

RESTful web services provide a standardized approach to building web APIs:

**Core Concepts:**
- **REST Principles:** Stateless, cacheable, uniform interface
- **HTTP Methods:** GET, POST, PUT, DELETE with specific semantics
- **Status Codes:** Meaningful response indicators
- **Idempotency:** Predictable behavior for repeated requests

**Advanced Features:**
- **Content Negotiation:** Multiple response formats
- **Versioning:** API evolution strategies
- **HATEOAS:** Self-describing APIs with hypermedia links
- **Security:** Authentication, authorization, and protection measures

**Best Practices:**
- Use appropriate HTTP methods and status codes
- Implement proper error handling
- Secure APIs with authentication and authorization
- Version APIs for backward compatibility
- Follow REST principles for consistency and scalability
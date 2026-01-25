# 18. RESTful Services 

## 1. What are RESTful web services?

RESTful web services are web services that follow REST architectural principles. They use HTTP methods to perform operations on resources identified by URLs, providing a stateless and scalable approach to web communication.

- Based on REST architectural style
- Use HTTP methods for operations
- Resources identified by URLs
- Stateless communication
- Platform and language independent
- JSON/XML data exchange

RESTful services provide a simple, standardized way for different systems to communicate over the web using standard HTTP protocols.

## 2. What are the principles of REST?

REST (Representational State Transfer) is based on six key architectural principles that guide the design of web services.

**REST Principles:**
- **Stateless:** Each request contains all necessary information
- **Client-Server:** Separation of concerns between client and server
- **Cacheable:** Responses can be cached for better performance
- **Uniform Interface:** Consistent way to interact with resources
- **Layered System:** Architecture can have multiple layers
- **Code on Demand:** Optional - server can send executable code

These principles ensure scalability, reliability, and maintainability of web services.

## 3. What are HTTP methods and their usage?

HTTP methods define the type of operation to be performed on a resource. Each method has a specific purpose and semantic meaning in RESTful services.

**Common HTTP Methods:**
- **GET:** Retrieve data (read-only)
- **POST:** Create new resources
- **PUT:** Update/replace entire resource
- **PATCH:** Partial update of resource
- **DELETE:** Remove resource
- **HEAD:** Get headers only (no body)
- **OPTIONS:** Get allowed methods

```java
@RestController
@RequestMapping("/users")
public class UserController {
    
    @GetMapping("/{id}")        // GET /users/1
    public User getUser(@PathVariable Long id) { }
    
    @PostMapping               // POST /users
    public User createUser(@RequestBody User user) { }
    
    @PutMapping("/{id}")       // PUT /users/1
    public User updateUser(@PathVariable Long id, @RequestBody User user) { }
    
    @DeleteMapping("/{id}")    // DELETE /users/1
    public void deleteUser(@PathVariable Long id) { }
}
```

## 4. What is the difference between PUT and POST?

**PUT:**
- Updates or replaces entire resource
- Idempotent (same result when called multiple times)
- Requires complete resource data
- Used for updates when you know the resource ID

**POST:**
- Creates new resources
- Not idempotent (creates new resource each time)
- Can send partial data
- Server typically generates resource ID

```java
// POST - Create new user (server generates ID)
POST /users
{
    "name": "John",
    "email": "john@example.com"
}
// Response: 201 Created with generated ID

// PUT - Update/replace entire user resource
PUT /users/123
{
    "id": 123,
    "name": "John Updated",
    "email": "john.updated@example.com"
}
// Response: 200 OK or 204 No Content
```

## 5. What is idempotency in REST?

Idempotency means that making the same request multiple times produces the same result as making it once. It's a crucial property for reliable and predictable web services.

**Idempotent Methods:**
- **GET:** Always returns same data
- **PUT:** Same update result
- **DELETE:** Resource remains deleted
- **HEAD, OPTIONS:** Same metadata

**Non-Idempotent Methods:**
- **POST:** Creates new resource each time

```java
// Idempotent - GET always returns same user
GET /users/123  // Returns user data
GET /users/123  // Returns same user data

// Idempotent - PUT produces same result
PUT /users/123 {"name": "John"}  // Updates user
PUT /users/123 {"name": "John"}  // Same result

// Non-idempotent - POST creates new resource each time
POST /users {"name": "John"}  // Creates user with ID 1
POST /users {"name": "John"}  // Creates user with ID 2
```

## 6. What are HTTP status codes?

HTTP status codes indicate the result of an HTTP request. They're grouped into categories and provide standardized way to communicate request outcomes.

**Status Code Categories:**
- **1xx:** Informational responses
- **2xx:** Success responses
- **3xx:** Redirection responses
- **4xx:** Client error responses
- **5xx:** Server error responses

**Common Status Codes:**
- **200 OK:** Request successful
- **201 Created:** Resource created successfully
- **204 No Content:** Success with no response body
- **400 Bad Request:** Invalid request syntax
- **401 Unauthorized:** Authentication required
- **403 Forbidden:** Access denied
- **404 Not Found:** Resource not found
- **409 Conflict:** Request conflicts with current state
- **500 Internal Server Error:** Server error

```java
@RestController
public class UserController {
    
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User user = userService.findById(id);
        if (user != null) {
            return ResponseEntity.ok(user);           // 200 OK
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }
    
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User created = userService.create(user);
        return ResponseEntity.status(HttpStatus.CREATED)  // 201 Created
                           .body(created);
    }
    
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();    // 204 No Content
    }
}
```

Proper use of HTTP status codes helps clients understand the result of their requests and handle responses appropriately.
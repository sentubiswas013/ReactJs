# Java Web Development - Servlets and JSP Interview Questions - Quick Answers

## 1. What is servlet in Java?

A servlet is a Java class that runs on a web server and handles HTTP requests and responses. It's the foundation of Java web applications, providing a way to create dynamic web content.

Servlets extend the capabilities of web servers by processing client requests, accessing databases, and generating dynamic responses. They run in a servlet container like Tomcat or Jetty.

```java
// Basic servlet example
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String name = request.getParameter("name");
        out.println("<html><body>");
        out.println("<h1>Hello " + (name != null ? name : "World") + "!</h1>");
        out.println("</body></html>");
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Handle POST requests
        String data = request.getParameter("data");
        // Process data and send response
    }
}
```

## 2. What is the servlet lifecycle?

The servlet lifecycle consists of three main phases managed by the servlet container: initialization, service, and destruction. The container controls when each phase occurs.

**Lifecycle Methods:**
- **init():** Called once when servlet is first loaded
- **service():** Called for each client request (doGet, doPost, etc.)
- **destroy():** Called once when servlet is unloaded

```java
public class LifecycleServlet extends HttpServlet {
    
    @Override
    public void init() throws ServletException {
        // Called once when servlet is loaded
        System.out.println("Servlet initialized");
        // Initialize resources, database connections, etc.
    }
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Called for each request
        // Delegates to doGet, doPost, etc. based on HTTP method
        super.service(request, response);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Handle GET requests
        response.getWriter().println("Processing GET request");
    }
    
    @Override
    public void destroy() {
        // Called once when servlet is unloaded
        System.out.println("Servlet destroyed");
        // Clean up resources, close connections, etc.
    }
}
```

## 3. What is JSP (JavaServer Pages)?

JSP is a technology that allows you to create dynamic web pages by embedding Java code directly into HTML. It's compiled into servlets by the container but provides a more convenient way to create web pages.

JSP separates presentation from business logic by allowing HTML designers to work with familiar markup while developers add dynamic functionality through Java code and JSP tags.

```jsp
<!-- Basic JSP example -->
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>User Profile</title>
</head>
<body>
    <h1>Welcome, <%= request.getParameter("username") %>!</h1>
    
    <%-- Java code in scriptlet --%>
    <%
        String currentTime = new java.util.Date().toString();
        int visitCount = 0;
        
        // Get session attribute
        if (session.getAttribute("visitCount") != null) {
            visitCount = (Integer) session.getAttribute("visitCount");
        }
        visitCount++;
        session.setAttribute("visitCount", visitCount);
    %>
    
    <p>Current time: <%= currentTime %></p>
    <p>Visit count: <%= visitCount %></p>
    
    <%-- Using JSTL tags (cleaner approach) --%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
    <c:if test="${not empty users}">
        <ul>
            <c:forEach var="user" items="${users}">
                <li>${user.name} - ${user.email}</li>
            </c:forEach>
        </ul>
    </c:if>
</body>
</html>
```

## 4. What is the difference between servlet and JSP?

**Servlet:**
- Pure Java class that generates HTML
- Better for business logic and data processing
- Requires recompilation for HTML changes
- More control over request/response handling

**JSP:**
- HTML with embedded Java code
- Better for presentation and UI
- Automatically compiled to servlet
- Easier for web designers to work with

```java
// Servlet approach - Java code generating HTML
@WebServlet("/user-servlet")
public class UserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        List<User> users = userService.getAllUsers();
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        out.println("<html><body>");
        out.println("<h1>Users List</h1>");
        out.println("<ul>");
        
        for (User user : users) {
            out.println("<li>" + user.getName() + " - " + user.getEmail() + "</li>");
        }
        
        out.println("</ul>");
        out.println("</body></html>");
    }
}
```

```jsp
<!-- JSP approach - HTML with embedded Java -->
<%@ page import="java.util.List" %>
<%@ page import="com.example.User" %>

<!DOCTYPE html>
<html>
<body>
    <h1>Users List</h1>
    <ul>
        <%
            List<User> users = (List<User>) request.getAttribute("users");
            for (User user : users) {
        %>
            <li><%= user.getName() %> - <%= user.getEmail() %></li>
        <%
            }
        %>
    </ul>
</body>
</html>
```

## 5. What is JPA and how it works?

JPA (Java Persistence API) is a specification for managing relational data in Java applications. It provides an object-relational mapping approach to handle relational data using Java objects.

JPA works by mapping Java classes to database tables, allowing you to work with database records as Java objects. Popular implementations include Hibernate, EclipseLink, and OpenJPA.

```java
// JPA Entity
@Entity
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    
    @Column(name = "email")
    private String email;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Order> orders = new ArrayList<>();
    
    // Constructors, getters, setters
    public User() {}
    
    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }
    
    // getters and setters...
}

// JPA Repository
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    User findByUsername(String username);
    
    @Query("SELECT u FROM User u WHERE u.email = :email")
    User findByEmail(@Param("email") String email);
    
    @Query("SELECT u FROM User u WHERE u.username LIKE %:keyword%")
    List<User> searchByUsername(@Param("keyword") String keyword);
}

// Service using JPA
@Service
@Transactional
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    public User createUser(String username, String email) {
        User user = new User(username, email);
        return userRepository.save(user);
    }
    
    public User findUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }
    
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}

// Configuration
# application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/mydb
spring.datasource.username=root
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

JPA abstracts away the complexity of SQL and JDBC, allowing developers to work with objects instead of database tables, while providing features like automatic table generation, relationship mapping, and query optimization.
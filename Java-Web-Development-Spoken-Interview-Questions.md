# Java Web Development - Servlets and JSP

## 1. What is servlet in Java?

A servlet is a Java class that handles HTTP requests and responses on a web server. It's like a controller that processes incoming requests, performs business logic, and sends back responses. Servlets run inside containers like Tomcat and are the foundation of Java web applications.

```java
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h1>Hello World!</h1>");
    }
}
```

## 2. What is the servlet lifecycle?

The servlet lifecycle has three main phases managed by the container. First is init() called once when the servlet loads to initialize resources. Then service() is called for every request and delegates to doGet, doPost methods. Finally destroy() is called once when the servlet unloads to clean up resources.

```java
public class LifecycleServlet extends HttpServlet {
    
    public void init() throws ServletException {
        // Called once when servlet loads
        System.out.println("Servlet initialized");
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Called for each request
        response.getWriter().println("Processing request");
    }
    
    public void destroy() {
        // Called once when servlet unloads
        System.out.println("Servlet destroyed");
    }
}
```

## 3. What is JSP (JavaServer Pages)?

JSP allows you to create dynamic web pages by embedding Java code directly into HTML. It's much easier than servlets for creating user interfaces because you write mostly HTML with some Java mixed in. JSP pages get automatically compiled into servlets by the container behind the scenes.

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<body>
    <h1>Welcome, <%= request.getParameter("username") %>!</h1>
    
    <%
        String currentTime = new java.util.Date().toString();
    %>
    
    <p>Current time: <%= currentTime %></p>
</body>
</html>
```

## 4. What is the difference between servlet and JSP?

Servlets are pure Java classes that generate HTML programmatically, while JSP is HTML with embedded Java code. Servlets are better for business logic and data processing because you have full control over the response. JSP is better for presentation and creating web pages because it's easier for designers to work with HTML.

Key differences:
- Servlets: Java code generating HTML
- JSP: HTML with embedded Java
- Servlets: Better for logic
- JSP: Better for presentation
- JSP gets converted to servlets anyway

```java
// Servlet approach
public class UserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>User List</h1>");
        out.println("</body></html>");
    }
}
```

```jsp
<!-- JSP approach -->
<!DOCTYPE html>
<html>
<body>
    <h1>User List</h1>
    <% for(User user : users) { %>
        <p><%= user.getName() %></p>
    <% } %>
</body>
</html>
```

## 5. What is JPA and how it works?

JPA stands for Java Persistence API and it's a way to work with databases using Java objects instead of SQL. You annotate your classes to tell JPA how to map them to database tables, and JPA handles all the SQL generation and object conversion automatically. Popular implementations include Hibernate and EclipseLink.

```java
@Entity
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "username")
    private String username;
    
    @Column(name = "email")
    private String email;
    
    // constructors, getters, setters
}

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    public User createUser(String username, String email) {
        User user = new User(username, email);
        return userRepository.save(user); // JPA handles the SQL
    }
}
```
# 🔵 17. Java Web Development 
---
# 🔹 Servlets and JSP
### Question 262: What is servlet in Java?

**Answer (30 seconds):**
* Server-side Java program that handles HTTP requests and responses
* Runs inside servlet container like Tomcat, Jetty
* Extends HttpServlet class and overrides doGet(), doPost() methods
* Platform-independent way to build web applications
* Manages dynamic web content generation

```java
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h1>Hello World!</h1>");
    }
}
```

---

### Question 263: What is the servlet lifecycle?

**Answer (35 seconds):**
* **Loading**: Container loads servlet class
* **Instantiation**: Creates servlet instance
* **Initialization**: Calls init() method once
* **Service**: Calls service() method for each request (doGet/doPost)
* **Destruction**: Calls destroy() method before removing servlet
* Container manages entire lifecycle automatically

```java
public class MyServlet extends HttpServlet {
    public void init() { /* Initialize resources */ }
    public void service(HttpServletRequest req, HttpServletResponse res) { /* Handle requests */ }
    public void destroy() { /* Cleanup resources */ }
}
```

---

### Question 264: What is JSP (JavaServer Pages)?

**Answer (30 seconds):**
* Server-side technology for creating dynamic web pages
* HTML with embedded Java code using special tags
* Compiled into servlets by container automatically
* Separates presentation layer from business logic
* Easier to write than pure servlets for UI-heavy applications

```jsp
<%@ page language="java" contentType="text/html" %>
<html>
<body>
    <h1>Welcome <%= request.getParameter("name") %>!</h1>
    <% String time = new Date().toString(); %>
    <p>Current time: <%= time %></p>
</body>
</html>
```

---

### Question 265: What is the difference between servlet and JSP?

**Answer (35 seconds):**
* **Servlet**: Pure Java code, HTML embedded in Java
* **JSP**: HTML with embedded Java code
* **Performance**: Servlets slightly faster, JSPs compiled to servlets
* **Development**: JSPs easier for UI, servlets better for business logic
* **Maintenance**: JSPs better for designers, servlets for developers
* **Use Case**: Combine both - servlets for logic, JSPs for presentation

```java
// Servlet - Java with HTML
out.println("<html><body><h1>" + message + "</h1></body></html>");

// JSP - HTML with Java
<html><body><h1><%= message %></h1></body></html>
```

---

### Question 266: What is JSTL (JSP Standard Tag Library)?

**Answer (30 seconds):**
* Collection of custom tags for common JSP tasks
* Eliminates need for scriptlets (Java code) in JSP pages
* Core tags for loops, conditions, formatting, SQL operations
* Makes JSP pages cleaner and more maintainable
* Standard library supported by all JSP containers

```jsp
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:forEach var="user" items="${users}">
    <p>${user.name} - ${user.email}</p>
</c:forEach>

<c:if test="${user.age >= 18}">
    <p>Adult user</p>
</c:if>
```

---

### Question 267: What is session management in web applications?

**Answer (35 seconds):**
* Technique to maintain user state across multiple HTTP requests
* HTTP is stateless - each request is independent
* **Methods**: Cookies, URL rewriting, Hidden form fields, HttpSession
* **HttpSession**: Most common approach in Java web apps
* Session data stored on server, session ID sent to client

```java
// Create/get session
HttpSession session = request.getSession();

// Store data
session.setAttribute("username", "john");
session.setAttribute("cart", shoppingCart);

// Retrieve data
String username = (String) session.getAttribute("username");
```

---

### Question 268: What are cookies in Java web applications?

**Answer (30 seconds):**
* Small pieces of data stored on client browser
* Sent automatically with each request to same domain
* Used for session tracking, user preferences, authentication
* Have expiration time and domain/path restrictions
* Created on server, stored on client

```java
// Create cookie
Cookie userCookie = new Cookie("username", "john");
userCookie.setMaxAge(3600); // 1 hour
response.addCookie(userCookie);

// Read cookies
Cookie[] cookies = request.getCookies();
for(Cookie cookie : cookies) {
    if("username".equals(cookie.getName())) {
        String username = cookie.getValue();
    }
}
```

---

### Question 269: What is URL rewriting?

**Answer (25 seconds):**
* Session tracking technique when cookies are disabled
* Appends session ID to every URL as parameter
* Fallback mechanism for session management
* Less user-friendly but works without client-side storage
* Automatically handled by servlet container

```java
// URL rewriting
String encodedURL = response.encodeURL("welcome.jsp");
// Result: welcome.jsp;jsessionid=ABC123

// In JSP
<a href="<%= response.encodeURL("profile.jsp") %>">Profile</a>
// Becomes: <a href="profile.jsp;jsessionid=ABC123">Profile</a>
```

---

### Question 270: What is HttpSession?

**Answer (35 seconds):**
* Interface representing user session in web application
* Provides way to identify user across multiple requests
* Stores session data on server side
* Automatically created by servlet container
* **Methods**: getAttribute(), setAttribute(), invalidate(), getId()
* Session timeout configurable in web.xml

```java
HttpSession session = request.getSession(); // Get existing or create new

// Session operations
session.setAttribute("user", userObject);
User user = (User) session.getAttribute("user");
session.removeAttribute("tempData");
session.invalidate(); // End session

// Check session
if(session.isNew()) { /* First request */ }
String sessionId = session.getId();
```

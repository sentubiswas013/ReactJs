### 1. What is CSRF Protection?

**CSRF (Cross-Site Request Forgery)** — attacker tricks a logged-in user's browser into sending an unwanted request to your server.

**Example attack:**
- User is logged into `bank.com`
- Attacker sends a link: `<img src="http://bank.com/transfer?to=attacker&amount=1000">`
- Browser auto-sends the request with the user's cookies → money transferred

**How CSRF Token fixes it:**
- Server generates a unique token per session
- Every form/request must include this token
- Server validates it — attacker can't guess it

---

#### ✅ Implementation Steps (Spring Boot)

**Step 1: Add Spring Security dependency**
```xml
<!-- pom.xml -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

**Step 2: CSRF is ENABLED by default in Spring Security**
```java
// Spring Security enables CSRF protection automatically
// No extra config needed for traditional form-based apps
```

**Step 3: For REST APIs — disable CSRF (stateless, uses JWT)**
```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())          // disable for stateless REST APIs
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }
}
```

**Step 4: For form-based apps — include CSRF token in forms (Thymeleaf does it auto)**
```html
<!-- Thymeleaf auto-injects CSRF token -->
<form th:action="@{/submit}" method="post">
    <input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}"/>
    <button type="submit">Submit</button>
</form>
```

**Interview Answer:**
> "CSRF is enabled by default in Spring Security. For REST APIs with JWT, I disable it since requests are stateless and don't rely on cookies. For form-based apps, Spring Security + Thymeleaf handles CSRF tokens automatically."

---

### 2. What is XSS Protection?

**XSS (Cross-Site Scripting)** — attacker injects malicious JavaScript into your web page, which runs in other users' browsers.

**Example attack:**
```
User submits comment: <script>document.cookie</script>
Server stores it → next user loads page → script runs → cookie stolen
```

**Types:**
- **Stored XSS** — malicious script saved in DB, served to all users
- **Reflected XSS** — script in URL, reflected back in response
- **DOM-based XSS** — script manipulates DOM directly

---

#### ✅ Implementation Steps (Spring Boot)

**Step 1: Add security headers via Spring Security**
```java
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .headers(headers -> headers
            .xssProtection(xss -> xss.enable())                        // X-XSS-Protection header
            .contentSecurityPolicy(csp ->
                csp.policyDirectives("script-src 'self'"))             // CSP header
        );
    return http.build();
}
```

**Step 2: Sanitize user input using OWASP Java HTML Sanitizer**
```xml
<!-- pom.xml -->
<dependency>
    <groupId>com.googlecode.owasp-java-html-sanitizer</groupId>
    <artifactId>owasp-java-html-sanitizer</artifactId>
    <version>20220608.1</version>
</dependency>
```

```java
import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;

@Service
public class SanitizationService {

    private static final PolicyFactory POLICY = Sanitizers.FORMATTING.and(Sanitizers.LINKS);

    public String sanitize(String input) {
        return POLICY.sanitize(input);   // strips <script> tags etc.
    }
}
```

**Step 3: Encode output in Thymeleaf (auto-escapes by default)**
```html
<!-- Thymeleaf escapes HTML by default — safe -->
<p th:text="${userComment}"></p>

<!-- th:utext is UNSAFE — renders raw HTML, avoid it -->
<p th:utext="${userComment}"></p>
```

**Step 4: Use Content Security Policy (CSP) header**
```java
.contentSecurityPolicy(csp ->
    csp.policyDirectives("default-src 'self'; script-src 'self'; object-src 'none'"))
```

**Interview Answer:**
> "I prevent XSS by: 1) enabling X-XSS-Protection and CSP headers via Spring Security, 2) sanitizing user input with OWASP HTML Sanitizer before storing, 3) using Thymeleaf which auto-escapes output by default."

---

### 3. What is Input Validation?

**Input Validation** — ensuring data received from the user is correct, safe, and expected before processing it.

**Why it matters:**
- Prevents SQL Injection, XSS, buffer overflows
- Ensures business rules are enforced (e.g., age > 0)
- Fails fast before bad data reaches DB

---

#### ✅ Implementation Steps (Spring Boot)

**Step 1: Add validation dependency**
```xml
<!-- pom.xml -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

**Step 2: Annotate your DTO/model with constraints**
```java
public class UserRequest {

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be 2-50 chars")
    private String name;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    @Min(value = 18, message = "Age must be at least 18")
    @Max(value = 120, message = "Age must be under 120")
    private int age;

    @Pattern(regexp = "^[0-9]{10}$", message = "Phone must be 10 digits")
    private String phone;

    // getters + setters
}
```

**Step 3: Enable validation in controller with `@Valid`**
```java
@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping
    public ResponseEntity<String> createUser(@Valid @RequestBody UserRequest request) {
        // if validation fails, MethodArgumentNotValidException is thrown automatically
        return ResponseEntity.ok("User created");
    }
}
```

**Step 4: Handle validation errors globally**
```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
          .forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));

        return ResponseEntity.badRequest().body(errors);
    }
}
```

**Step 5: Response when validation fails**
```json
{
  "name": "Name is required",
  "email": "Invalid email format",
  "age": "Age must be at least 18"
}
```

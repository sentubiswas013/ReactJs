# ✅ 13) Security (Basic – JWT, Spring Security)

## 160. What are OWASP Top 10 vulnerabilities?

**Answer:** OWASP Top 10 lists the most critical web application security risks: Broken Access Control, Cryptographic Failures, Injection, Insecure Design, Security Misconfiguration, Vulnerable Components, Authentication Failures, Data Integrity Failures, Logging Failures, and SSRF.

**Example:**
```java
// Broken Access Control - BAD
@GetMapping("/user/{id}")
public User getUser(@PathVariable Long id) {
    return userService.findById(id); // Any user can access any ID
}

// Fixed with proper authorization
@GetMapping("/user/{id}")
@PreAuthorize("@userSecurity.canAccessUser(#id)")
public User getUser(@PathVariable Long id) {
    return userService.findById(id);
}
```

---

## 161. What is SQL injection? How to prevent it?

**Answer:** SQL injection occurs when untrusted input is directly concatenated into SQL queries, allowing attackers to execute malicious SQL. Prevent using parameterized queries or prepared statements.

**Example:**
```java
// Vulnerable to SQL Injection - BAD
String query = "SELECT * FROM users WHERE username = '" + username + "'";

// Safe - Using PreparedStatement
String query = "SELECT * FROM users WHERE username = ?";
PreparedStatement stmt = connection.prepareStatement(query);
stmt.setString(1, username);

// Safe - Using JPA
@Query("SELECT u FROM User u WHERE u.username = :username")
User findByUsername(@Param("username") String username);
```

---

## 162. What is XSS and CSRF?

**Answer:**
- **XSS (Cross-Site Scripting)**: Injecting malicious scripts into web pages viewed by other users
- **CSRF (Cross-Site Request Forgery)**: Forcing authenticated users to execute unwanted actions

**Example:**
```java
// XSS Prevention - Escape output
@GetMapping("/profile")
public String profile(Model model, @RequestParam String name) {
    model.addAttribute("name", HtmlUtils.htmlEscape(name)); // Escape HTML
    return "profile";
}

// CSRF Prevention - Spring Security enables by default
@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
        return http.build();
    }
}
```

---

## 163. Explain JWT structure (header.payload.signature).

**Answer:** JWT has three parts separated by dots:
- **Header**: Algorithm and token type
- **Payload**: Claims (user data)
- **Signature**: Verification hash

**Example:**
```java
// JWT Structure: xxxxx.yyyyy.zzzzz

// Header (Base64 encoded)
{
  "alg": "HS256",
  "typ": "JWT"
}

// Payload (Base64 encoded)
{
  "sub": "user123",
  "name": "John Doe",
  "exp": 1735689600
}

// Signature
HMACSHA256(
  base64UrlEncode(header) + "." + base64UrlEncode(payload),
  secret
)

// Creating JWT
String jwt = Jwts.builder()
    .setSubject("user123")
    .claim("name", "John Doe")
    .setExpiration(new Date(System.currentTimeMillis() + 3600000))
    .signWith(SignatureAlgorithm.HS256, "secretKey")
    .compact();
```

---

## 164. How do you validate JWT tokens?

**Answer:** Validate JWT by verifying signature, checking expiration, and validating claims.

**Example:**
```java
@Component
public class JwtTokenValidator {
    
    private final String SECRET_KEY = "mySecretKey";
    
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            return false; // Token expired
        } catch (JwtException e) {
            return false; // Invalid token
        }
    }
    
    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
            .setSigningKey(SECRET_KEY)
            .parseClaimsJws(token)
            .getBody();
        return claims.getSubject();
    }
}
```

---

## 165. Stateless vs stateful authentication.

**Answer:**
- **Stateless**: No server-side session storage, uses tokens (JWT). Scalable but token can't be revoked easily
- **Stateful**: Server stores session data. Easy to revoke but requires session storage

**Example:**
```java
// Stateless - JWT based
@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody LoginRequest request) {
    authenticate(request.getUsername(), request.getPassword());
    String token = jwtUtil.generateToken(request.getUsername());
    return ResponseEntity.ok(new JwtResponse(token));
}

// Stateful - Session based
@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody LoginRequest request, HttpSession session) {
    User user = authenticate(request.getUsername(), request.getPassword());
    session.setAttribute("user", user); // Stored on server
    return ResponseEntity.ok("Login successful");
}
```

---

## 166. How do you implement refresh token mechanism?

**Answer:** Use short-lived access tokens with long-lived refresh tokens. When access token expires, use refresh token to get new access token without re-authentication.

**Example:**
```java
@Service
public class TokenService {
    
    public TokenResponse generateTokens(String username) {
        String accessToken = generateAccessToken(username); // 15 min
        String refreshToken = generateRefreshToken(username); // 7 days
        
        // Store refresh token in DB
        refreshTokenRepository.save(new RefreshToken(username, refreshToken));
        
        return new TokenResponse(accessToken, refreshToken);
    }
    
    public String refreshAccessToken(String refreshToken) {
        if (!isValidRefreshToken(refreshToken)) {
            throw new InvalidTokenException();
        }
        String username = getUsernameFromToken(refreshToken);
        return generateAccessToken(username);
    }
    
    private String generateAccessToken(String username) {
        return Jwts.builder()
            .setSubject(username)
            .setExpiration(new Date(System.currentTimeMillis() + 900000)) // 15 min
            .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
            .compact();
    }
}
```

---

## 167. How do you store passwords securely (BCrypt)?

**Answer:** Never store plain text passwords. Use BCrypt hashing algorithm which includes salt and is computationally expensive to prevent brute-force attacks.

**Example:**
```java
@Service
public class UserService {
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    // Registration - Hash password
    public User registerUser(String username, String password) {
        String hashedPassword = passwordEncoder.encode(password);
        User user = new User(username, hashedPassword);
        return userRepository.save(user);
    }
    
    // Login - Verify password
    public boolean authenticate(String username, String rawPassword) {
        User user = userRepository.findByUsername(username);
        return passwordEncoder.matches(rawPassword, user.getPassword());
    }
}

// Configuration
@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12); // Strength factor
    }
}
```

---

## 168. What is the principle of least privilege?

**Answer:** Grant users only the minimum permissions necessary to perform their job. Reduces security risk if account is compromised.

**Example:**
```java
@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
            .requestMatchers("/public/**").permitAll()
            .requestMatchers("/admin/**").hasRole("ADMIN")
            .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
            .anyRequest().authenticated()
        );
        return http.build();
    }
}

// Method level
@Service
public class DocumentService {
    
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteDocument(Long id) {
        documentRepository.deleteById(id);
    }
    
    @PreAuthorize("hasRole('USER') and #userId == authentication.principal.id")
    public Document viewDocument(Long id, Long userId) {
        return documentRepository.findById(id).orElse(null);
    }
}
```

---

## 169. How do you handle secrets management?

**Answer:** Never hardcode secrets in code. Use environment variables, secret management services (AWS Secrets Manager, HashiCorp Vault), or encrypted configuration files.

**Example:**
```java
// BAD - Hardcoded secrets
String apiKey = "sk_live_12345abcde";

// GOOD - Environment variables
@Value("${api.key}")
private String apiKey;

// application.yml
// api:
//   key: ${API_KEY}

// BETTER - AWS Secrets Manager
@Configuration
public class SecretsConfig {
    
    @Bean
    public AWSSecretsManager secretsManager() {
        return AWSSecretsManagerClientBuilder.standard()
            .withRegion("us-east-1")
            .build();
    }
    
    public String getSecret(String secretName) {
        GetSecretValueRequest request = new GetSecretValueRequest()
            .withSecretId(secretName);
        GetSecretValueResult result = secretsManager().getSecretValue(request);
        return result.getSecretString();
    }
}

// BEST - Spring Cloud Config with encryption
// bootstrap.yml
// spring:
//   cloud:
//     config:
//       uri: http://config-server:8888
// 
// Config Server stores: {cipher}AQA3eHj8...encrypted_value
```


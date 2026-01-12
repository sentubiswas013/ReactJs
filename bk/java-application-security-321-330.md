# 🔹 Application Security

### Question 321: What is authentication vs authorization?

**Answer (30 seconds):**
* **Authentication**: Verifies "who you are" - identity verification
* **Authorization**: Determines "what you can do" - access control
* **Authentication First**: Must authenticate before authorization
* **Examples**: Login (authentication), accessing admin panel (authorization)
* **Mechanisms**: Passwords, tokens, certificates for auth; roles, permissions for authz
* Both essential for complete security

```java
// Authentication - verify identity
@PostMapping("/login")
public ResponseEntity<String> authenticate(@RequestBody LoginRequest request) {
    if (userService.validateCredentials(request.getUsername(), request.getPassword())) {
        String token = jwtService.generateToken(request.getUsername());
        return ResponseEntity.ok(token);
    }
    return ResponseEntity.status(401).body("Invalid credentials");
}

// Authorization - check permissions
@PreAuthorize("hasRole('ADMIN')")
@GetMapping("/admin/users")
public List<User> getUsers() { return userService.getAllUsers(); }
```

---

### Question 322: What is OAuth?

**Answer (35 seconds):**
* Open standard for access delegation and authorization
* Allows third-party applications to access user resources without passwords
* **Resource Owner**: User who owns the data
* **Client**: Application requesting access
* **Authorization Server**: Issues access tokens
* **Resource Server**: Hosts protected resources
* **Flow**: Authorization code, implicit, client credentials, password

```java
// OAuth 2.0 Spring Security configuration
@Configuration
@EnableOAuth2Client
public class OAuth2Config {
    @Bean
    public OAuth2RestTemplate oauth2RestTemplate() {
        return new OAuth2RestTemplate(clientCredentialsResourceDetails());
    }
    
    @Bean
    public ClientCredentialsResourceDetails clientCredentialsResourceDetails() {
        ClientCredentialsResourceDetails details = new ClientCredentialsResourceDetails();
        details.setClientId("my-client-id");
        details.setClientSecret("my-client-secret");
        details.setAccessTokenUri("https://auth-server.com/oauth/token");
        return details;
    }
}
```

---

### Question 323: What is JWT (JSON Web Token)?

**Answer (35 seconds):**
* Compact, URL-safe token format for securely transmitting information
* **Structure**: Header.Payload.Signature (three Base64-encoded parts)
* **Stateless**: Contains all necessary information, no server-side storage
* **Self-contained**: Includes user info, permissions, expiration
* **Use Cases**: Authentication, information exchange, API authorization
* **Security**: Signed (and optionally encrypted) for integrity

```java
// JWT creation and validation
@Service
public class JwtService {
    private String secretKey = "mySecretKey";
    
    public String generateToken(String username) {
        return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 24 hours
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact();
    }
    
    public String extractUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
            .getBody().getSubject();
    }
}
```

---

### Question 324: What is CSRF protection?

**Answer (35 seconds):**
* Cross-Site Request Forgery protection prevents unauthorized actions
* **Attack**: Malicious site tricks user into performing unwanted actions
* **CSRF Token**: Unique token included in forms and validated on server
* **SameSite Cookies**: Restrict cookie sending to same-site requests
* **Double Submit**: Send token in both cookie and request parameter
* **Spring Security**: Automatically provides CSRF protection

```java
// CSRF protection configuration
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(csrf -> csrf
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .ignoringRequestMatchers("/api/public/**"))
            .build();
    }
}

// Include CSRF token in forms
// <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
```

---

### Question 325: What is XSS protection?

**Answer (35 seconds):**
* Cross-Site Scripting protection prevents malicious script injection
* **Reflected XSS**: Script in URL parameters executed immediately
* **Stored XSS**: Malicious script stored in database and executed later
* **DOM XSS**: Client-side script manipulation
* **Protection**: Input validation, output encoding, Content Security Policy
* **Sanitization**: Remove or escape dangerous characters

```java
// XSS protection methods
@Service
public class XssProtectionService {
    
    // Input validation
    public boolean isValidInput(String input) {
        return input != null && !input.matches(".*<script.*>.*");
    }
    
    // Output encoding
    public String encodeForHtml(String input) {
        return StringEscapeUtils.escapeHtml4(input);
    }
    
    // Sanitize input
    public String sanitizeInput(String input) {
        return Jsoup.clean(input, Whitelist.basic());
    }
}

// Content Security Policy header
// response.setHeader("Content-Security-Policy", "script-src 'self'");
```

---

### Question 326: What is input validation?

**Answer (30 seconds):**
* Process of checking user input for correctness and security
* **Client-side**: JavaScript validation for user experience
* **Server-side**: Essential validation for security (never trust client)
* **Whitelist**: Allow only known good input patterns
* **Sanitization**: Clean input by removing dangerous characters
* **Bean Validation**: Use annotations like @Valid, @NotNull, @Pattern

```java
// Input validation with Bean Validation
public class UserRegistration {
    @NotBlank(message = "Username is required")
    @Pattern(regexp = "^[a-zA-Z0-9_]{3,20}$", message = "Invalid username format")
    private String username;
    
    @Email(message = "Invalid email format")
    private String email;
    
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;
}

@PostMapping("/register")
public ResponseEntity<String> register(@Valid @RequestBody UserRegistration user) {
    // Validation automatically applied
    return ResponseEntity.ok("User registered successfully");
}
```

---

### Question 327: What is secure coding practices?

**Answer (40 seconds):**
* Guidelines and techniques to write secure, vulnerability-free code
* **Input Validation**: Validate all user inputs
* **Output Encoding**: Encode data before displaying
* **Authentication**: Strong password policies, multi-factor authentication
* **Authorization**: Principle of least privilege
* **Error Handling**: Don't expose sensitive information in errors
* **Logging**: Log security events, protect log files
* **Dependencies**: Keep libraries updated, scan for vulnerabilities

```java
// Secure coding examples
@Service
public class SecureUserService {
    
    // Secure password handling
    public void createUser(String username, String password) {
        // Hash password with salt
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
        userRepository.save(new User(username, hashedPassword));
    }
    
    // Secure database query (prevent SQL injection)
    public User findByEmail(String email) {
        return userRepository.findByEmail(email); // Using JPA, not raw SQL
    }
    
    // Secure error handling
    public ResponseEntity<String> login(String username, String password) {
        try {
            User user = authenticate(username, password);
            return ResponseEntity.ok("Login successful");
        } catch (Exception e) {
            logger.warn("Failed login attempt for user: {}", username);
            return ResponseEntity.status(401).body("Invalid credentials"); // Generic message
        }
    }
}
```

---

### Question 328: What is OAuth 2.0?

**Answer (35 seconds):**
* Updated version of OAuth protocol for authorization
* **Authorization Code Flow**: Most secure, uses authorization code exchange
* **Implicit Flow**: For browser-based apps (deprecated)
* **Client Credentials Flow**: For server-to-server communication
* **Resource Owner Password Flow**: Direct username/password (discouraged)
* **PKCE**: Proof Key for Code Exchange for enhanced security
* **Scopes**: Define specific permissions granted

```java
// OAuth 2.0 Authorization Server configuration
@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {
    
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
            .withClient("my-client")
            .secret(passwordEncoder.encode("my-secret"))
            .authorizedGrantTypes("authorization_code", "refresh_token")
            .scopes("read", "write")
            .redirectUris("http://localhost:8080/callback");
    }
}

// Resource server protection
@EnableResourceServer
@RestController
public class ApiController {
    @GetMapping("/api/data")
    @PreAuthorize("#oauth2.hasScope('read')")
    public String getData() { return "Protected data"; }
}
```

---

### Question 329: What is OpenID Connect?

**Answer (35 seconds):**
* Identity layer built on top of OAuth 2.0 protocol
* **Authentication**: Provides user identity information (who you are)
* **ID Token**: JWT containing user identity claims
* **UserInfo Endpoint**: Additional user profile information
* **Standard Claims**: sub, name, email, picture, etc.
* **Discovery**: Automatic configuration discovery
* **Single Sign-On**: Enable SSO across multiple applications

```java
// OpenID Connect configuration
@Configuration
@EnableOAuth2Sso
public class OpenIdConnectConfig {
    
    @Bean
    public OAuth2RestTemplate oauth2RestTemplate(OAuth2ClientContext context) {
        return new OAuth2RestTemplate(openIdResource(), context);
    }
    
    @Bean
    public AuthorizationCodeResourceDetails openIdResource() {
        AuthorizationCodeResourceDetails details = new AuthorizationCodeResourceDetails();
        details.setClientId("my-client-id");
        details.setClientSecret("my-client-secret");
        details.setAccessTokenUri("https://provider.com/oauth/token");
        details.setUserAuthorizationUri("https://provider.com/oauth/authorize");
        details.setScope(Arrays.asList("openid", "profile", "email"));
        return details;
    }
}

// Extract user info from ID token
@GetMapping("/user")
public Map<String, Object> user(Principal principal) {
    OAuth2Authentication auth = (OAuth2Authentication) principal;
    return (Map<String, Object>) auth.getUserAuthentication().getDetails();
}
```

---

### Question 330: What is SAML?

**Answer (35 seconds):**
* Security Assertion Markup Language for exchanging authentication data
* **XML-based**: Uses XML for security assertions
* **SSO**: Enables single sign-on across different domains
* **Identity Provider (IdP)**: Authenticates users and issues assertions
* **Service Provider (SP)**: Consumes assertions to grant access
* **Assertions**: Statements about user authentication and attributes
* **Enterprise**: Popular in enterprise environments

```java
// SAML configuration with Spring Security
@Configuration
@EnableWebSecurity
public class SamlConfig {
    
    @Bean
    public SAMLAuthenticationProvider samlAuthenticationProvider() {
        SAMLAuthenticationProvider provider = new SAMLAuthenticationProvider();
        provider.setUserDetails(samlUserDetailsService());
        return provider;
    }
    
    @Bean
    public MetadataManager metadata() throws Exception {
        List<MetadataProvider> providers = new ArrayList<>();
        providers.add(idpMetadata());
        return new CachingMetadataManager(providers);
    }
    
    @Bean
    public ExtendedMetadata extendedMetadata() {
        ExtendedMetadata metadata = new ExtendedMetadata();
        metadata.setIdpDiscoveryEnabled(true);
        metadata.setSignMetadata(false);
        return metadata;
    }
}

// SAML assertion processing
@Component
public class SamlUserDetailsService implements SAMLUserDetailsService {
    public Object loadUserBySAML(SAMLCredential credential) {
        String username = credential.getNameID().getValue();
        List<String> roles = credential.getAttributeAsStringArray("Role");
        return new SamlUser(username, roles);
    }
}
```
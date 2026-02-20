## 8) Spring Boot and Spring Security

## 85. What is Spring Boot auto-configuration?

**Answer:**
Auto-configuration automatically configures Spring application based on dependencies in classpath. It uses `@Conditional` annotations to apply configurations only when certain conditions are met.

**Example:**
```java
// Spring Boot detects H2 in classpath and auto-configures DataSource
// pom.xml
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
</dependency>

// No manual configuration needed - Spring Boot auto-configures
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

---

## 86. Explain `@SpringBootApplication` annotation.

**Answer:**
`@SpringBootApplication` is a combination of three annotations: `@Configuration` (Java config), `@EnableAutoConfiguration` (auto-config), and `@ComponentScan` (scan components).

**Example:**
```java
@SpringBootApplication
// Equivalent to:
// @Configuration
// @EnableAutoConfiguration
// @ComponentScan
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

// Exclude specific auto-configuration
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class CustomApp { }
```

---

## 87. What are Spring Boot starters?

**Answer:**
Starters are dependency descriptors that bundle related dependencies together. They simplify dependency management by providing pre-configured sets of libraries.

**Example:**
```xml
<!-- pom.xml -->
<!-- Web starter includes Spring MVC, Tomcat, Jackson -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<!-- JPA starter includes Hibernate, Spring Data JPA -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<!-- Security starter -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

---

## 88. How do you externalize configuration?

**Answer:**
Use `application.properties` or `application.yml` files, environment variables, command-line arguments, or `@ConfigurationProperties` to externalize configuration.

**Example:**
```properties
# application.properties
app.name=MyApp
app.version=1.0
server.port=8080
spring.datasource.url=jdbc:mysql://localhost:3306/db
```

```java
@Component
@ConfigurationProperties(prefix = "app")
public class AppConfig {
    private String name;
    private String version;
    
    // getters/setters
}

@RestController
public class ConfigController {
    @Value("${app.name}")
    private String appName;
    
    @Autowired
    private AppConfig config;
}
```

---

## 89. What are Spring profiles and how do you use them?

**Answer:**
Profiles allow different configurations for different environments (dev, test, prod). Activate using `spring.profiles.active` property.

**Example:**
```properties
# application-dev.properties
spring.datasource.url=jdbc:h2:mem:testdb

# application-prod.properties
spring.datasource.url=jdbc:mysql://prod-server:3306/db
```

```java
@Configuration
@Profile("dev")
public class DevConfig {
    @Bean
    public DataSource dataSource() {
        return new H2DataSource();
    }
}

@Configuration
@Profile("prod")
public class ProdConfig {
    @Bean
    public DataSource dataSource() {
        return new MySQLDataSource();
    }
}

// Activate: java -jar app.jar --spring.profiles.active=prod
```

---

## 90. Explain Spring Boot Actuator endpoints.

**Answer:**
Actuator provides production-ready features like health checks, metrics, and monitoring endpoints. Common endpoints: `/health`, `/metrics`, `/info`, `/env`.

**Example:**
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

```properties
# application.properties
management.endpoints.web.exposure.include=health,metrics,info
management.endpoint.health.show-details=always
```

```java
// Custom health indicator
@Component
public class CustomHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {
        boolean isHealthy = checkService();
        if (isHealthy) {
            return Health.up().withDetail("service", "available").build();
        }
        return Health.down().withDetail("service", "unavailable").build();
    }
}
```

---

## 91. How do you create custom auto-configuration?

**Answer:**
Create a configuration class with `@Configuration` and `@Conditional` annotations, then register it in `META-INF/spring.factories`.

**Example:**
```java
@Configuration
@ConditionalOnClass(MyService.class)
@EnableConfigurationProperties(MyProperties.class)
public class MyAutoConfiguration {
    
    @Bean
    @ConditionalOnMissingBean
    public MyService myService(MyProperties props) {
        return new MyService(props.getName());
    }
}

@ConfigurationProperties(prefix = "my.service")
public class MyProperties {
    private String name;
    // getters/setters
}
```

```properties
# META-INF/spring.factories
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
com.example.MyAutoConfiguration
```

---

## 92. What is Spring Boot DevTools?

**Answer:**
DevTools provides development-time features like automatic restart, live reload, and configurations for faster development cycle.

**Example:**
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <scope>runtime</scope>
    <optional>true</optional>
</dependency>
```

```properties
# application.properties
spring.devtools.restart.enabled=true
spring.devtools.livereload.enabled=true
```

---

## 93. Explain Spring Security filter chain.

**Answer:**
Security filter chain intercepts requests and applies security checks. Filters include: SecurityContextPersistenceFilter, UsernamePasswordAuthenticationFilter, FilterSecurityInterceptor.

**Example:**
```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/public/**").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(Customizer.withDefaults());
        return http.build();
    }
}
```

---

## 94. What is authentication vs authorization?

**Answer:**
Authentication verifies who you are (identity). Authorization determines what you can access (permissions).

**Example:**
```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // Authentication - verify user identity
            .httpBasic(Customizer.withDefaults())
            // Authorization - check permissions
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/user/**").hasRole("USER")
                .anyRequest().authenticated()
            );
        return http.build();
    }
}
```

---

## 95. How do you implement JWT authentication?

**Answer:**
Create JWT token on login, validate token on each request using a filter. Token contains user info and is signed with secret key.

**Example:**
```java
@Component
public class JwtUtil {
    private String secret = "mySecretKey";
    
    public String generateToken(String username) {
        return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + 86400000))
            .signWith(SignatureAlgorithm.HS256, secret)
            .compact();
    }
    
    public String extractUsername(String token) {
        return Jwts.parser().setSigningKey(secret)
            .parseClaimsJws(token).getBody().getSubject();
    }
}

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, 
                                    HttpServletResponse response, 
                                    FilterChain chain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            String jwt = token.substring(7);
            String username = jwtUtil.extractUsername(jwt);
            // Set authentication in SecurityContext
        }
        chain.doFilter(request, response);
    }
}
```

---

## 96. What is OAuth2 and OpenID Connect?

**Answer:**
OAuth2 is an authorization framework for delegated access. OpenID Connect is an identity layer on top of OAuth2 for authentication.

**Example:**
```java
@Configuration
@EnableWebSecurity
public class OAuth2Config {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .oauth2Login(oauth -> oauth
                .loginPage("/login")
            )
            .authorizeHttpRequests(auth -> auth
                .anyRequest().authenticated()
            );
        return http.build();
    }
}
```

```properties
# application.properties
spring.security.oauth2.client.registration.google.client-id=your-client-id
spring.security.oauth2.client.registration.google.client-secret=your-secret
spring.security.oauth2.client.registration.google.scope=profile,email
```

---

## 97. Explain OAuth2 authorization flows.

**Answer:**
- **Authorization Code**: For web apps (most secure)
- **Implicit**: For SPAs (deprecated)
- **Client Credentials**: For service-to-service
- **Password**: For trusted apps (not recommended)

**Example:**
```java
// Authorization Code Flow
@RestController
public class OAuth2Controller {
    
    @GetMapping("/login")
    public String login() {
        // Redirect to authorization server
        return "redirect:/oauth2/authorization/google";
    }
    
    @GetMapping("/callback")
    public String callback(@RequestParam String code) {
        // Exchange code for access token
        return "success";
    }
}

// Client Credentials Flow
@Configuration
public class OAuth2ClientConfig {
    
    @Bean
    public OAuth2AuthorizedClientManager authorizedClientManager(
            ClientRegistrationRepository clientRegistrationRepository,
            OAuth2AuthorizedClientRepository authorizedClientRepository) {
        
        OAuth2AuthorizedClientProvider provider = 
            OAuth2AuthorizedClientProviderBuilder.builder()
                .clientCredentials()
                .build();
        
        DefaultOAuth2AuthorizedClientManager manager = 
            new DefaultOAuth2AuthorizedClientManager(
                clientRegistrationRepository, authorizedClientRepository);
        manager.setAuthorizedClientProvider(provider);
        return manager;
    }
}
```

---

## 98. What is access token vs refresh token?

**Answer:**
Access token is short-lived token for API access. Refresh token is long-lived token used to obtain new access tokens without re-authentication.

**Example:**
```java
public class TokenResponse {
    private String accessToken;
    private String refreshToken;
    private long expiresIn;
}

@Service
public class TokenService {
    
    public TokenResponse generateTokens(String username) {
        String accessToken = generateAccessToken(username); // 15 min
        String refreshToken = generateRefreshToken(username); // 7 days
        return new TokenResponse(accessToken, refreshToken, 900);
    }
    
    public TokenResponse refreshAccessToken(String refreshToken) {
        if (validateRefreshToken(refreshToken)) {
            String username = extractUsername(refreshToken);
            String newAccessToken = generateAccessToken(username);
            return new TokenResponse(newAccessToken, refreshToken, 900);
        }
        throw new InvalidTokenException();
    }
}

@RestController
public class AuthController {
    
    @PostMapping("/refresh")
    public TokenResponse refresh(@RequestBody RefreshRequest request) {
        return tokenService.refreshAccessToken(request.getRefreshToken());
    }
}
```

---

## 99. How do you implement method-level security (`@PreAuthorize`)?

**Answer:**
Enable method security with `@EnableMethodSecurity` and use `@PreAuthorize`, `@PostAuthorize` annotations to secure methods based on expressions.

**Example:**
```java
@Configuration
@EnableMethodSecurity
public class MethodSecurityConfig { }

@Service
public class UserService {
    
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow();
    }
    
    @PreAuthorize("#username == authentication.principal.username")
    public void updateProfile(String username, UserDTO dto) {
        // User can only update their own profile
    }
    
    @PostAuthorize("returnObject.owner == authentication.principal.username")
    public Document getDocument(Long id) {
        return documentRepository.findById(id).orElseThrow();
    }
}
```

---

## 100. What is CORS and CSRF? When to disable CSRF?

**Answer:**
CORS (Cross-Origin Resource Sharing) allows requests from different domains. CSRF (Cross-Site Request Forgery) protection prevents unauthorized commands. Disable CSRF for stateless REST APIs using JWT.

**Example:**
```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // Enable CORS
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            // Disable CSRF for stateless API
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
            .sessionManagement(session -> 
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }
    
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        config.setAllowedHeaders(Arrays.asList("*"));
        config.setAllowCredentials(true);
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
```

---

## 101. How do you implement role-based access control?

**Answer:**
Define roles and authorities, assign them to users, and configure access rules using `hasRole()` or `hasAuthority()` in security configuration.

**Example:**
```java
@Entity
public class User {
    @Id
    private Long id;
    private String username;
    private String password;
    
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;
}

@Entity
public class Role {
    @Id
    private Long id;
    private String name; // ROLE_USER, ROLE_ADMIN
}

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        
        return org.springframework.security.core.userdetails.User
            .withUsername(user.getUsername())
            .password(user.getPassword())
            .authorities(user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList()))
            .build();
    }
}

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                .requestMatchers("/public/**").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(Customizer.withDefaults());
        return http.build();
    }
}

@RestController
public class AdminController {
    
    @GetMapping("/admin/users")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAllUsers() {
        return userService.findAll();
    }
}
```
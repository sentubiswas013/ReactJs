# Spring Boot Interview Questions

## Basic Spring Boot Questions

1. What is Spring Boot, and why is it used?
2. What are the main features of Spring Boot?
3. How does Spring Boot differ from the traditional Spring Framework?
4. What is the purpose of `@SpringBootApplication` annotation?
5. What are Spring Boot starters? Give some examples.
6. What is the difference between `application.properties` and `application.yml` files in Spring Boot?
7. What is the role of `@EnableAutoConfiguration` in Spring Boot?
8. How do you create a Spring Boot application from scratch?
9. What are some of the commonly used Spring Boot starters?
10. How can you run a Spring Boot application from the command line?

## Spring Boot Configuration

11. How can you externalize configuration in Spring Boot?
12. What are profiles in Spring Boot, and how are they used?
13. How do you inject properties from `application.properties` into Spring Beans?
14. Explain the difference between `@Value` and `@ConfigurationProperties`.
15. How do you manage multiple configuration files in Spring Boot?
16. How can you create a custom configuration in Spring Boot?
17. What is the purpose of `application.properties` or `application.yml` files in Spring Boot?
18. How do you configure logging in Spring Boot?
19. How can you configure different profiles for different environments (e.g., dev, prod)?
20. How would you set up a logging level for Spring Boot (e.g., debug, info, error)?

## Auto Configuration in Spring Boot

21. What is Spring Boot auto-configuration, and how does it work?
22. How can you disable specific auto-configurations in Spring Boot?
23. How do you create a custom auto-configuration in Spring Boot?
24. What is `@ConditionalOnProperty` in Spring Boot?
25. Explain `@ConditionalOnClass` and `@ConditionalOnMissingBean` in Spring Boot auto-configuration.

## Spring Boot Annotations

26. What is the role of `@RestController` and `@Controller` in Spring Boot?
27. How does `@RequestMapping` differ from `@GetMapping`, `@PostMapping`, etc.?
28. What is the difference between `@RequestParam` and `@PathVariable`?
29. What are the key uses of `@Component`, `@Service`, `@Repository`, and `@Controller` annotations?
30. What is the purpose of `@Autowired` in Spring Boot?
31. How does Spring Boot handle dependency injection?
32. What is the purpose of `@Value` annotation in Spring Boot?
33. Explain `@Bean` and `@Configuration` in Spring Boot.
34. What is `@PostConstruct` and `@PreDestroy` in Spring Boot?
35. What is the `@Scope` annotation in Spring Boot?

## Dependency Injection and Spring Beans

36. How do you manage Spring Beans in Spring Boot?
37. What is the default bean scope in Spring Boot?
38. Explain the difference between `@Singleton`, `@Prototype`, and `@RequestScope`.
39. How does Spring Boot handle the lifecycle of beans?
40. What are `@Primary` and `@Qualifier` annotations used for?
41. How do you manage circular dependencies in Spring Boot?

## Spring Boot Data & Persistence

42. How do you connect Spring Boot to a relational database?
43. What is Spring Data JPA, and how does it integrate with Spring Boot?
44. What is the difference between `@OneToMany` and `@ManyToOne` in Spring Boot?
45. How can you configure pagination and sorting in Spring Boot with Spring Data JPA?
46. What is `@Entity` and `@Table` in Spring Boot JPA?
47. What is `@Transactional` used for in Spring Boot?
48. How does Spring Boot handle database migrations (e.g., Flyway, Liquibase)?
49. How do you implement a many-to-many relationship using JPA in Spring Boot?
50. What are `CrudRepository`, `JpaRepository`, and `PagingAndSortingRepository` in Spring Data JPA?

## Spring Boot Security

51. How do you configure Spring Security in Spring Boot?
52. What is `@PreAuthorize` annotation in Spring Security?
53. What are the steps to enable basic authentication in Spring Boot?
54. How can you configure JWT (JSON Web Tokens) authentication in Spring Boot?
55. What is OAuth2, and how do you implement it in Spring Boot?
56. How do you manage authorization and roles in Spring Security?
57. How can you protect REST APIs with Spring Security in Spring Boot?
58. How do you customize the login page in Spring Security with Spring Boot?
59. What is CSRF protection in Spring Boot, and how is it implemented?
60. How do you secure the Spring Boot Actuator endpoints?

## Spring Boot Testing

61. What is `@SpringBootTest` used for in testing Spring Boot applications?
62. How do you test a Spring Boot controller using `MockMvc`?
63. How do you mock services and repositories in Spring Boot tests?
64. What is the difference between `@WebMvcTest` and `@DataJpaTest`?
65. How can you write unit tests for Spring Boot services using `@MockBean`?
66. What are the best practices for testing Spring Boot applications?
67. How do you perform integration testing in Spring Boot?
68. How do you use `@AutoConfigureMockMvc` for testing controllers in Spring Boot?
69. How do you handle database integration tests in Spring Boot without polluting the real database?

## Spring Boot Actuator

70. What is Spring Boot Actuator, and how does it enhance your application?
71. What are the default endpoints provided by Spring Boot Actuator?
72. How can you expose custom Actuator endpoints in Spring Boot?
73. What is the role of `health` and `metrics` endpoints in Spring Boot Actuator?
74. How do you secure actuator endpoints in a production environment?
75. How can you monitor and gather metrics using Spring Boot Actuator?
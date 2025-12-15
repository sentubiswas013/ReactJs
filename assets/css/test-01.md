## Core Java Fundamentals

### Basic Concepts
1. What is Java and what are its key features?
2. Explain the difference between JDK, JRE, and JVM.
3. What is platform independence in Java?
4. How does Java achieve "write once, run anywhere"?
5. What are the main principles of Object-Oriented Programming?
6. Explain the difference between class and object.
7. What is encapsulation and how is it implemented in Java?
8. What is inheritance and what are its types?
9. What is polymorphism? Explain with examples.
10. What is abstraction and how is it achieved in Java?

### Data Types and Variables
11. What are primitive data types in Java?
12. What is the difference between primitive and reference types?
13. What is autoboxing and unboxing?
14. Explain the concept of wrapper classes.
15. What is the difference between == and equals() method?
16. What is the difference between String, StringBuilder, and StringBuffer?
17. Why are strings immutable in Java?
18. What is string pooling?
19. How do you compare strings in Java?
20. What is the difference between final, finally, and finalize?

### Control Structures
21. What are the different types of loops in Java?
22. What is the difference between break and continue?
23. What is enhanced for loop (for-each)?
24. When would you use switch vs if-else?
25. What are the rules for switch statement in Java?

## Object-Oriented Programming

### Classes and Objects
26. What is a constructor in Java?
27. What are the types of constructors?
28. What is constructor chaining?
29. Can you call a constructor from another constructor?
30. What is the difference between this and super keywords?
31. What is method overloading?
32. What is method overriding?
33. What are the rules for method overriding?
34. What is the difference between overloading and overriding?
35. What is dynamic method dispatch?

### Inheritance
36. What is single inheritance in Java?
37. Why doesn't Java support multiple inheritance?
38. What is the diamond problem?
39. How does Java solve the diamond problem?
40. What is the use of super keyword?
41. What is method hiding?
42. Can you override static methods?
43. Can you override private methods?
44. What is covariant return type?
45. What is the difference between IS-A and HAS-A relationship?

### Interfaces and Abstract Classes
46. What is an interface in Java?
47. What is an abstract class?
48. What is the difference between interface and abstract class?
49. Can an interface have constructors?
50. What are default methods in interfaces?
51. What are static methods in interfaces?
52. Can you have private methods in interfaces?
53. What is marker interface?
54. What is functional interface?
55. Can an interface extend another interface?

## Exception Handling

### Exception Basics
56. What is an exception in Java?
57. What is the difference between error and exception?
58. What is the exception hierarchy in Java?
59. What are checked and unchecked exceptions?
60. What is the difference between throw and throws?
61. What is try-catch-finally block?
62. Can you have multiple catch blocks?
63. What is try-with-resources?
64. What happens if an exception occurs in finally block?
65. Can you throw an exception from finally block?

### Custom Exceptions
66. How do you create custom exceptions?
67. When should you create custom exceptions?
68. What is exception chaining?
69. What is suppressed exception?
70. How do you handle multiple exceptions in a single catch?

## Collections Framework

### Collection Basics
71. What is Java Collections Framework?
72. What is the difference between Collection and Collections?
73. What are the main interfaces in Collections Framework?
74. What is the difference between List, Set, and Map?
75. What is the difference between ArrayList and LinkedList?
76. When would you use ArrayList vs LinkedList?
77. What is the difference between HashSet and TreeSet?
78. What is the difference between HashMap and TreeMap?
79. What is the difference between HashMap and Hashtable?
80. What is the difference between HashMap and ConcurrentHashMap?

### Advanced Collections
81. How does HashMap work internally?
82. What is hash collision and how is it handled?
83. What is the load factor in HashMap?
84. What happens when HashMap reaches its capacity?
85. What is the difference between fail-fast and fail-safe iterators?
86. What is WeakHashMap?
87. What is IdentityHashMap?
88. What is LinkedHashMap?
89. What is PriorityQueue?
90. What is the difference between Comparable and Comparator?

### Collection Performance
91. What is the time complexity of ArrayList operations?
92. What is the time complexity of HashMap operations?
93. What is the time complexity of TreeMap operations?
94. How do you choose the right collection for your use case?
95. What are the best practices for using collections?

## Multithreading and Concurrency

### Thread Basics
96. What is multithreading?
97. What is the difference between process and thread?
98. How do you create threads in Java?
99. What is the difference between extending Thread and implementing Runnable?
100. What are the states of a thread?
101. What is the difference between start() and run() methods?
102. What is thread priority?
103. What is daemon thread?
104. What is the difference between user thread and daemon thread?
105. How do you stop a thread?

### Synchronization
106. What is synchronization in Java?
107. What is the synchronized keyword?
108. What is the difference between synchronized method and synchronized block?
109. What is deadlock?
110. How do you prevent deadlock?
111. What is race condition?
112. What is thread safety?
113. What is volatile keyword?
114. What is the difference between synchronized and volatile?
115. What is atomic operation?

### Advanced Concurrency
116. What is java.util.concurrent package?
117. What is ExecutorService?
118. What is ThreadPoolExecutor?
119. What are the types of thread pools?
120. What is Future and CompletableFuture?
121. What is CountDownLatch?
122. What is CyclicBarrier?
123. What is Semaphore?
124. What is ReentrantLock?
125. What is the difference between ReentrantLock and synchronized?

## Memory Management and Garbage Collection

### Memory Areas
126. What are the different memory areas in JVM?
127. What is heap memory?
128. What is stack memory?
129. What is method area?
130. What is the difference between heap and stack?
131. What is permgen space?
132. What is metaspace?
133. What is the difference between permgen and metaspace?
134. What is direct memory?
135. What is off-heap memory?

### Garbage Collection
136. What is garbage collection?
137. How does garbage collection work?
138. What are the types of garbage collectors?
139. What is generational garbage collection?
140. What are young generation and old generation?
141. What is the difference between minor GC and major GC?
142. What is full GC?
143. What are GC roots?
144. How do you tune garbage collection?
145. What are the common GC algorithms?

## Input/Output (I/O)

### File I/O
146. What are the different ways to read a file in Java?
147. What is the difference between InputStream and Reader?
148. What is the difference between OutputStream and Writer?
149. What is BufferedReader and BufferedWriter?
150. What is the difference between FileInputStream and FileReader?
151. What is RandomAccessFile?
152. What is File class?
153. What is Path and Files class?
154. What is the difference between File and Path?
155. How do you handle large files efficiently?

### NIO (New I/O)
156. What is NIO in Java?
157. What is the difference between IO and NIO?
158. What is Channel in NIO?
159. What is Buffer in NIO?
160. What is Selector in NIO?
161. What is non-blocking I/O?
162. What is memory-mapped file?
163. When would you use NIO over traditional I/O?
164. What is NIO.2?
165. What is asynchronous I/O?

## Generics

### Generic Basics
166. What are generics in Java?
167. Why were generics introduced?
168. What is type erasure?
169. What are bounded type parameters?
170. What is the difference between <?> and <? extends Object>?
171. What is the difference between <? extends T> and <? super T>?
172. What is PECS principle?
173. Can you create generic arrays?
174. What are generic methods?
175. What are the limitations of generics?

## Annotations and Reflection

### Annotations
176. What are annotations in Java?
177. What are built-in annotations?
178. How do you create custom annotations?
179. What is retention policy?
180. What is the difference between @Override and @Overload?
181. What is @SuppressWarnings?
182. What is @Deprecated?
183. What is @FunctionalInterface?
184. How do you process annotations at runtime?
185. What is annotation processing?

### Reflection
186. What is reflection in Java?
187. How do you get Class object?
188. How do you create objects using reflection?
189. How do you invoke methods using reflection?
190. How do you access private fields using reflection?
191. What are the performance implications of reflection?
192. When should you use reflection?
193. What are the security implications of reflection?
194. What is the difference between Class.forName() and ClassLoader.loadClass()?
195. How do you handle exceptions in reflection?

## Lambda Expressions and Streams

### Lambda Expressions
196. What are lambda expressions?
197. What is the syntax of lambda expressions?
198. What are functional interfaces?
199. What are method references?
200. What are constructor references?
201. What is the difference between lambda and anonymous class?
202. What are the built-in functional interfaces?
203. What is Predicate interface?
204. What is Function interface?
205. What is Consumer interface?

### Streams API
206. What is Stream API?
207. What is the difference between Collection and Stream?
208. What are intermediate and terminal operations?
209. What is the difference between map() and flatMap()?
210. What is the difference between filter() and map()?
211. What is parallel stream?
212. When should you use parallel streams?
213. What is the difference between findFirst() and findAny()?
214. What is Optional class?
215. How do you handle null values with Optional?

## Database Connectivity (JDBC)

### JDBC Basics
216. What is JDBC?
217. What are the steps to connect to a database using JDBC?
218. What are JDBC drivers?
219. What are the types of JDBC drivers?
220. What is Connection interface?
221. What is Statement interface?
222. What is PreparedStatement?
223. What is CallableStatement?
224. What is the difference between Statement and PreparedStatement?
225. What is ResultSet?

### Advanced JDBC
226. What is connection pooling?
227. What is transaction management in JDBC?
228. What is batch processing in JDBC?
229. What is SQL injection and how to prevent it?
230. What is the difference between execute(), executeQuery(), and executeUpdate()?
231. What is ResultSetMetaData?
232. What is DatabaseMetaData?
233. How do you handle large objects (LOB) in JDBC?
234. What is scrollable ResultSet?
235. What is updatable ResultSet?

## Design Patterns

### Creational Patterns
236. What are design patterns?
237. What is Singleton pattern?
238. How do you implement thread-safe Singleton?
239. What is Factory pattern?
240. What is Abstract Factory pattern?
241. What is Builder pattern?
242. What is Prototype pattern?
243. When would you use each creational pattern?
244. What are the pros and cons of Singleton pattern?
245. How do you break Singleton pattern?

### Behavioral Patterns
246. What is Observer pattern?
247. What is Strategy pattern?
248. What is Command pattern?
249. What is Template Method pattern?
250. What is State pattern?
251. What is Chain of Responsibility pattern?
252. What is Iterator pattern?
253. What is Visitor pattern?
254. When would you use behavioral patterns?
255. How do you implement Observer pattern in Java?

### Structural Patterns
256. What is Adapter pattern?
257. What is Decorator pattern?
258. What is Facade pattern?
259. What is Proxy pattern?
260. What is Composite pattern?
261. What is Bridge pattern?
262. What is Flyweight pattern?
263. When would you use structural patterns?
264. How do you implement Decorator pattern?
265. What is the difference between Adapter and Facade?

## Spring Framework

### Spring Core
266. What is Spring Framework?
267. What are the benefits of Spring?
268. What is Inversion of Control (IoC)?
269. What is Dependency Injection?
270. What are the types of dependency injection?
271. What is ApplicationContext?
272. What is BeanFactory?
273. What is the difference between BeanFactory and ApplicationContext?
274. What are Spring beans?
275. What are the bean scopes in Spring?

### Spring Boot
276. What is Spring Boot?
277. What are the advantages of Spring Boot?
278. What is auto-configuration in Spring Boot?
279. What are Spring Boot starters?
280. What is @SpringBootApplication annotation?
281. What is application.properties file?
282. What is the difference between @Component, @Service, and @Repository?
283. What is @Autowired annotation?
284. What is @Qualifier annotation?
285. How do you create REST APIs with Spring Boot?

## Microservices and Web Services

### RESTful Services
286. What are RESTful web services?
287. What are the principles of REST?
288. What are HTTP methods and their usage?
289. What is the difference between PUT and POST?
290. What is idempotency in REST?
291. What are HTTP status codes?
292. What is content negotiation?
293. How do you handle versioning in REST APIs?
294. What is HATEOAS?
295. How do you secure REST APIs?

### Microservices
296. What are microservices?
297. What are the advantages of microservices?
298. What are the challenges of microservices?
299. What is service discovery?
300. What is API Gateway?
301. What is circuit breaker pattern?
302. What is distributed tracing?
303. How do you handle data consistency in microservices?
304. What is event-driven architecture?
305. How do you test microservices?

## Performance and Optimization

### Performance Tuning
306. How do you identify performance bottlenecks?
307. What are common performance issues in Java applications?
308. How do you optimize database queries?
309. What is connection pooling and why is it important?
310. How do you optimize memory usage?
311. What is caching and when should you use it?
312. What are the different caching strategies?
313. How do you optimize garbage collection?
314. What is profiling and what tools do you use?
315. How do you optimize multithreaded applications?

### JVM Tuning
316. What are important JVM parameters?
317. How do you tune heap size?
318. What is the difference between -Xms and -Xmx?
319. How do you analyze heap dumps?
320. How do you analyze thread dumps?
321. What is JIT compilation?
322. How do you monitor JVM performance?
323. What are the different garbage collectors and when to use them?
324. How do you tune garbage collection?
325. What is escape analysis?

## Testing

### Unit Testing
326. What is unit testing?
327. What is JUnit?
328. What are the important JUnit annotations?
329. What is the difference between @Before and @BeforeEach?
330. What is mocking?
331. What is Mockito?
332. How do you mock static methods?
333. What is the difference between mock and spy?
334. What is test-driven development (TDD)?
335. What are testing best practices?

### Integration Testing
336. What is integration testing?
337. How do you test Spring Boot applications?
338. What is @SpringBootTest?
339. How do you test REST APIs?
340. How do you test database operations?
341. What is TestContainers?
342. How do you test microservices?
343. What is contract testing?
344. What is end-to-end testing?
345. How do you handle test data management?

## Modern Java Features

### Recent Java Versions
346. What are the new features in Java 8?
347. What are the new features in Java 11?
348. What are the new features in Java 17?
349. What are the new features in Java 21?
350. What is the Java release cycle and LTS versions?

### Cloud and Containerization
351. What is containerization?
352. What is Docker?
353. What is Kubernetes?
354. What is cloud computing?
355. What is distributed system?
356. What is load balancing?
357. What is caching strategies?
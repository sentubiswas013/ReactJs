# Java Interview Questions - Top 250 for Real-World Hiring

## Core Java Fundamentals (50 Questions)

### Basic Concepts (15)
1. What is Java and what are its key features?
2. Explain JVM, JRE, and JDK. What are the differences?
3. What is bytecode and how does Java achieve platform independence?
4. What are the main principles of OOP in Java?
5. What is the difference between JIT compiler and interpreter?
6. Explain the Java compilation process.
7. What are access modifiers in Java and their scope?
8. What is the difference between public, protected, default, and private?
9. What are wrapper classes and why are they needed?
10. What is autoboxing and unboxing?
11. What are the primitive data types in Java?
12. What is the difference between == and equals() method?
13. What is the difference between String, StringBuffer, and StringBuilder?
14. What is immutability in Java? Give examples.
15. What is the String pool in Java?

### Object-Oriented Programming (20)
16. What is inheritance and what are its types?
17. What is method overloading and method overriding?
18. What are the rules for method overriding?
19. What is polymorphism? Explain with examples.
20. What is abstraction and how is it achieved in Java?
21. What is encapsulation and why is it important?
22. What is the difference between abstract class and interface?
23. When would you use abstract class vs interface?
24. What are default methods in interfaces (Java 8)?
25. What is multiple inheritance and how does Java handle it?
26. What is composition vs inheritance?
27. What is the super keyword and its uses?
28. What is the this keyword and its uses?
29. What are constructors and their types?
30. What is constructor chaining?
31. What is the difference between constructor and method?
32. What is method hiding vs method overriding?
33. What is covariant return type?
34. What is the instanceof operator?
35. What is dynamic method dispatch?

### Exception Handling (15)
36. What is exception handling in Java?
37. What is the difference between checked and unchecked exceptions?
38. What is the exception hierarchy in Java?
39. What are the keywords used in exception handling?
40. What is the difference between throw and throws?
41. What is the difference between final, finally, and finalize?
42. Can we have try without catch block?
43. What is try-with-resources statement?
44. What are custom exceptions and how to create them?
45. What is exception propagation?
46. What happens if an exception is thrown in catch block?
47. What is the difference between Error and Exception?
48. What are some common runtime exceptions?
49. How do you handle multiple exceptions in a single catch block?
50. What are best practices for exception handling?

## Collections Framework (40 Questions)

### Collection Basics (15)
51. What is the Collections Framework in Java?
52. What is the difference between Collection and Collections?
53. What are the main interfaces in Collections Framework?
54. What is the difference between List, Set, and Map?
55. What is the difference between ArrayList and LinkedList?
56. When would you use ArrayList vs LinkedList?
57. What is the difference between Vector and ArrayList?
58. What is the difference between HashSet and TreeSet?
59. What is the difference between HashMap and TreeMap?
60. What is the difference between HashMap and Hashtable?
61. What is the difference between HashMap and ConcurrentHashMap?
62. What is the load factor in HashMap?
63. How does HashMap work internally?
64. What happens when two objects have the same hashcode?
65. What is the difference between Comparable and Comparator?

### Advanced Collections (25)
66. What is the difference between Iterator and ListIterator?
67. What is fail-fast and fail-safe iterators?
68. What is the difference between synchronized and concurrent collections?
69. What is CopyOnWriteArrayList and when to use it?
70. What is BlockingQueue and its implementations?
71. What is the difference between poll() and remove() in Queue?
72. What is PriorityQueue and how does it work?
73. What is WeakHashMap and when to use it?
74. What is IdentityHashMap?
75. What is EnumSet and EnumMap?
76. How do you make a collection thread-safe?
77. What is the difference between synchronizedList() and CopyOnWriteArrayList?
78. What are the time complexities of different collection operations?
79. How do you sort a collection in Java?
80. What is the difference between Arrays.sort() and Collections.sort()?
81. How do you reverse a collection?
82. How do you find duplicates in a collection?
83. How do you convert Array to List and vice versa?
84. What is the difference between peek() and poll() in Queue?
85. How does TreeMap maintain sorted order?
86. What is NavigableMap and NavigableSet?
87. What is the difference between remove() and removeAll()?
88. How do you iterate over a Map?
89. What is the diamond problem and how does Java solve it?
90. What are the best practices for using collections?

## Multithreading and Concurrency (35 Questions)

### Threading Basics (15)
91. What is multithreading and why is it important?
92. What is the difference between process and thread?
93. How do you create threads in Java?
94. What is the difference between extending Thread and implementing Runnable?
95. What are the different states of a thread?
96. What is the difference between start() and run() methods?
97. What is thread synchronization and why is it needed?
98. What are the different ways to achieve synchronization?
99. What is the synchronized keyword and how does it work?
100. What is the difference between synchronized method and synchronized block?
101. What is deadlock and how to prevent it?
102. What is the difference between wait() and sleep()?
103. What is the difference between notify() and notifyAll()?
104. What is thread priority and how does it work?
105. What is daemon thread?

### Advanced Concurrency (20)
106. What is the java.util.concurrent package?
107. What is ExecutorService and how to use it?
108. What is the difference between execute() and submit()?
109. What are different types of thread pools?
110. What is Future and CompletableFuture?
111. What is the difference between Callable and Runnable?
112. What are locks in Java and their types?
113. What is ReentrantLock and how is it different from synchronized?
114. What is ReadWriteLock?
115. What is volatile keyword and when to use it?
116. What is atomic variables and AtomicInteger?
117. What is CountDownLatch?
118. What is CyclicBarrier?
119. What is Semaphore?
120. What is ThreadLocal and when to use it?
121. What is the Fork/Join framework?
122. What is parallel stream and when to use it?
123. What are the best practices for concurrent programming?
124. What is happens-before relationship?
125. What is the difference between concurrent and parallel execution?

## Memory Management and JVM (25 Questions)

### Memory Management (15)
126. Explain the JVM memory structure.
127. What is heap memory and its generations?
128. What is the difference between stack and heap memory?
129. What is garbage collection and how does it work?
130. What are different types of garbage collectors?
131. What is the difference between minor and major GC?
132. What are memory leaks and how to prevent them?
133. What is OutOfMemoryError and its types?
134. What is the difference between -Xms and -Xmx?
135. What is PermGen and Metaspace?
136. What is the difference between strong, weak, soft, and phantom references?
137. What is finalize() method and why is it deprecated?
138. How do you analyze memory dumps?
139. What tools do you use for memory profiling?
140. What are best practices for memory management?

### JVM Internals (10)
141. What is JIT compilation?
142. What is class loading and the class loader hierarchy?
143. What is the difference between static and dynamic loading?
144. What is reflection and when to use it?
145. What are the performance implications of reflection?
146. What is bytecode manipulation?
147. What are JVM tuning parameters?
148. How do you monitor JVM performance?
149. What is escape analysis?
150. What is TLAB (Thread Local Allocation Buffer)?

## Java 8+ Features (25 Questions)

### Lambda and Functional Programming (15)
151. What are lambda expressions and their syntax?
152. What are functional interfaces?
153. What are method references and their types?
154. What is the difference between lambda and anonymous class?
155. What are the built-in functional interfaces?
156. What is Predicate, Function, Consumer, and Supplier?
157. What is the Stream API?
158. What is the difference between intermediate and terminal operations?
159. What is the difference between map() and flatMap()?
160. What is the difference between filter() and map()?
161. What is parallel stream and when to use it?
162. What is Optional class and how to use it?
163. What is the difference between findFirst() and findAny()?
164. How do you handle exceptions in streams?
165. What are collectors and custom collectors?

### Modern Java Features (10)
166. What are the new features in Java 9?
167. What are modules in Java 9?
168. What are the new features in Java 11?
169. What are the new features in Java 17?
170. What are the new features in Java 21?
171. What is var keyword in Java 10?
172. What are text blocks in Java 13?
173. What are records in Java 14?
174. What are sealed classes in Java 17?
175. What is pattern matching in Java?

## Spring Framework (30 Questions)

### Spring Core (15)
176. What is Spring Framework and its benefits?
177. What is Inversion of Control (IoC)?
178. What is Dependency Injection and its types?
179. What is ApplicationContext and BeanFactory?
180. What are Spring beans and their scopes?
181. What is the Spring bean lifecycle?
182. What are the different ways to configure Spring?
183. What is @Component, @Service, @Repository, and @Controller?
184. What is @Autowired and its alternatives?
185. What is @Qualifier and when to use it?
186. What is @Primary annotation?
187. What is circular dependency and how to resolve it?
188. What is AOP and how is it implemented in Spring?
189. What are different types of advice in AOP?
190. What is the difference between JDK proxy and CGLIB proxy?

### Spring Boot (15)
191. What is Spring Boot and its advantages?
192. What is auto-configuration in Spring Boot?
193. What are Spring Boot starters?
194. What is @SpringBootApplication annotation?
195. What is the difference between application.properties and application.yml?
196. What are profiles in Spring Boot?
197. What is Spring Boot Actuator?
198. How do you create REST APIs with Spring Boot?
199. What are the common Spring Boot annotations?
200. How do you handle exceptions in Spring Boot?
201. What is Spring Boot DevTools?
202. How do you test Spring Boot applications?
203. What is @MockBean and @TestConfiguration?
204. How do you deploy Spring Boot applications?
205. What are best practices for Spring Boot development?

## Database and JDBC (20 Questions)

### JDBC Basics (10)
206. What is JDBC and its architecture?
207. What are the steps to connect to a database using JDBC?
208. What are JDBC drivers and their types?
209. What is the difference between Statement, PreparedStatement, and CallableStatement?
210. What is ResultSet and its types?
211. What is connection pooling and why is it important?
212. What is transaction management in JDBC?
213. What is batch processing in JDBC?
214. What is SQL injection and how to prevent it?
215. What are best practices for JDBC programming?

### JPA and Hibernate (10)
216. What is JPA and its benefits?
217. What is Hibernate and how does it work?
218. What is the difference between JPA and Hibernate?
219. What are JPA annotations?
220. What is the difference between @Entity and @Table?
221. What are different types of relationships in JPA?
222. What is lazy loading vs eager loading?
223. What is the N+1 problem and how to solve it?
224. What is caching in Hibernate?
225. What are best practices for JPA/Hibernate?

## Design Patterns and Best Practices (25 Questions)

### Common Design Patterns (15)
226. What are design patterns and why are they important?
227. What is Singleton pattern and how to implement it thread-safely?
228. What is Factory pattern and when to use it?
229. What is Builder pattern and its advantages?
230. What is Observer pattern and its implementation?
231. What is Strategy pattern?
232. What is Decorator pattern?
233. What is Adapter pattern?
234. What is MVC pattern?
235. What is DAO pattern?
236. What is Template Method pattern?
237. What is Command pattern?
238. What is Proxy pattern?
239. What is Facade pattern?
240. When would you use each of these patterns?

### Best Practices and Code Quality (10)
241. What are SOLID principles?
242. What is clean code and its principles?
243. What are code smells and how to avoid them?
244. What is refactoring and when to do it?
245. What are unit testing best practices?
246. What is TDD and its benefits?
247. What are the principles of RESTful API design?
248. What are microservices and their benefits/challenges?
249. What are performance optimization techniques in Java?
250. What are security best practices in Java development?

---

## Interview Tips

### Preparation Strategy
- Focus on understanding concepts rather than memorizing answers
- Practice coding problems on platforms like LeetCode, HackerRank
- Build projects to demonstrate practical knowledge
- Review your resume and be ready to discuss your experience
- Prepare questions to ask the interviewer

### Common Interview Formats
- **Technical Phone Screen**: Basic Java concepts, simple coding problems
- **Coding Interview**: Data structures, algorithms, system design
- **System Design**: Architecture, scalability, trade-offs
- **Behavioral Interview**: Past experiences, problem-solving approach

### Key Areas to Master
1. **Core Java**: OOP, collections, exceptions, multithreading
2. **Frameworks**: Spring/Spring Boot, Hibernate/JPA
3. **Databases**: SQL, JDBC, transaction management
4. **Testing**: JUnit, Mockito, integration testing
5. **Tools**: Maven/Gradle, Git, IDE proficiency
6. **Modern Java**: Java 8+ features, functional programming

### Red Flags to Avoid
- Not knowing basic Java concepts
- Unable to write simple code without IDE
- No understanding of time/space complexity
- Cannot explain your own project work
- No questions about the role/company

Remember: Quality over quantity. Deep understanding of fewer topics is better than surface knowledge of many topics.
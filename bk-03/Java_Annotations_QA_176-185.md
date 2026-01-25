# 🔹 Annotations and Reflection

## Annotations Fundamentals

### 176. What are annotations in Java?

**Annotations** are **metadata** that provide information about code without changing its behavior:

#### Key Characteristics:
- **Metadata**: Information about code for tools and runtime
- **Non-functional**: Don't affect program execution directly
- **Declarative**: Describe what, not how
- **Tool support**: Used by compilers, IDEs, and frameworks

#### Basic Syntax:
```java
@AnnotationName
public class MyClass {
    
    @Override
    public String toString() {
        return "MyClass";
    }
    
    @Deprecated
    public void oldMethod() {
        // Legacy code
    }
}
```

#### Use Cases:
- **Compiler instructions**: @Override, @SuppressWarnings
- **Runtime processing**: @Entity, @Component
- **Code generation**: @Getter, @Setter (Lombok)
- **Documentation**: @Author, @Since

### 177. What are built-in annotations?

Java provides several **standard annotations**:

#### Core Annotations:

##### @Override
```java
class Parent {
    public void method() {}
}

class Child extends Parent {
    @Override
    public void method() {  // Ensures correct overriding
        super.method();
    }
}
```

##### @Deprecated
```java
public class Calculator {
    @Deprecated
    public int add(int a, int b) {  // Marks as obsolete
        return a + b;
    }
    
    public int sum(int a, int b) {  // New preferred method
        return a + b;
    }
}
```

##### @SuppressWarnings
```java
@SuppressWarnings("unchecked")
public void rawTypeMethod() {
    List list = new ArrayList();  // Suppresses unchecked warning
    list.add("item");
}
```

##### @FunctionalInterface
```java
@FunctionalInterface
public interface Calculator {
    int calculate(int a, int b);  // Exactly one abstract method
}
```

##### @SafeVarargs
```java
@SafeVarargs
public static <T> void printAll(T... items) {
    for (T item : items) {
        System.out.println(item);
    }
}
```

#### Meta-Annotations:
- **@Retention**: How long annotation is retained
- **@Target**: Where annotation can be applied
- **@Documented**: Include in Javadoc
- **@Inherited**: Inherited by subclasses

### 178. How do you create custom annotations?

Create custom annotations using **@interface** keyword:

#### Basic Custom Annotation:
```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MyAnnotation {
    String value() default "";
    int priority() default 1;
}
```

#### Usage:
```java
public class Service {
    @MyAnnotation(value = "important", priority = 5)
    public void processData() {
        // Method implementation
    }
}
```

#### Complex Custom Annotation:
```java
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface ApiEndpoint {
    String path();
    HttpMethod method() default HttpMethod.GET;
    String[] roles() default {};
    boolean authenticated() default true;
}

enum HttpMethod {
    GET, POST, PUT, DELETE
}
```

#### Usage Example:
```java
@ApiEndpoint(
    path = "/users", 
    method = HttpMethod.POST,
    roles = {"ADMIN", "USER"},
    authenticated = true
)
public class UserController {
    
    @ApiEndpoint(path = "/users/{id}", method = HttpMethod.GET)
    public User getUser(Long id) {
        return userService.findById(id);
    }
}
```

### 179. What is retention policy?

**Retention policy** determines **how long annotations are kept**:

#### Retention Types:

##### SOURCE
```java
@Retention(RetentionPolicy.SOURCE)
public @interface CompileTimeOnly {
    String value();
}
```
- **Available**: Compile-time only
- **Use case**: Code generation, compile-time checks
- **Examples**: @Override, @SuppressWarnings

##### CLASS (Default)
```java
@Retention(RetentionPolicy.CLASS)
public @interface BytecodeLevel {
    String value();
}
```
- **Available**: Compile-time and bytecode
- **Not available**: Runtime
- **Use case**: Bytecode analysis tools

##### RUNTIME
```java
@Retention(RetentionPolicy.RUNTIME)
public @interface RuntimeProcessing {
    String value();
}
```
- **Available**: Compile-time, bytecode, and runtime
- **Use case**: Reflection, frameworks
- **Examples**: @Entity, @Component

#### Comparison:
| Policy | Compile-Time | Bytecode | Runtime | Use Case |
|--------|--------------|----------|---------|----------|
| **SOURCE** | ✅ | ❌ | ❌ | Code generation |
| **CLASS** | ✅ | ✅ | ❌ | Bytecode tools |
| **RUNTIME** | ✅ | ✅ | ✅ | Frameworks |

### 180. What is the difference between @Override and @Overload?

#### @Override (Exists):
```java
class Animal {
    public void makeSound() {
        System.out.println("Some sound");
    }
}

class Dog extends Animal {
    @Override
    public void makeSound() {  // Overriding parent method
        System.out.println("Woof!");
    }
}
```

#### @Overload (Does NOT exist):
**There is no @Overload annotation in Java**. Method overloading happens automatically:

```java
public class Calculator {
    // Method overloading - no annotation needed
    public int add(int a, int b) {
        return a + b;
    }
    
    public double add(double a, double b) {
        return a + b;
    }
    
    public int add(int a, int b, int c) {
        return a + b + c;
    }
}
```

#### Key Differences:
| Aspect | @Override | Method Overloading |
|--------|-----------|-------------------|
| **Annotation** | @Override exists | No annotation |
| **Purpose** | Ensures correct overriding | Automatic by compiler |
| **Inheritance** | Requires parent method | Same class |
| **Signature** | Same signature | Different parameters |

### 181. What is @SuppressWarnings?

**@SuppressWarnings** tells compiler to **ignore specific warnings**:

#### Common Warning Types:
```java
public class WarningExamples {
    
    @SuppressWarnings("unchecked")
    public void uncheckedWarning() {
        List list = new ArrayList();  // Raw type
        list.add("item");
    }
    
    @SuppressWarnings("deprecation")
    public void deprecationWarning() {
        Date date = new Date(2023, 1, 1);  // Deprecated constructor
    }
    
    @SuppressWarnings("unused")
    public void unusedWarning() {
        int unusedVariable = 42;  // Unused variable
    }
    
    @SuppressWarnings({"unchecked", "rawtypes"})
    public void multipleWarnings() {
        Map map = new HashMap();  // Multiple warnings suppressed
        map.put("key", "value");
    }
}
```

#### Common Values:
- **"unchecked"**: Raw type operations
- **"deprecation"**: Using deprecated APIs
- **"unused"**: Unused variables/methods
- **"rawtypes"**: Raw type usage
- **"serial"**: Missing serialVersionUID
- **"all"**: All warnings (use carefully)

#### Best Practices:
```java
// Good - specific and localized
@SuppressWarnings("unchecked")
List<String> list = (List<String>) rawList;

// Avoid - too broad
@SuppressWarnings("all")
public class MyClass { }
```

### 182. What is @Deprecated?

**@Deprecated** marks code elements as **obsolete** and **discourages use**:

#### Basic Usage:
```java
public class FileUtils {
    
    @Deprecated
    public static String readFile(String path) {
        // Old implementation
        return "content";
    }
    
    // Preferred new method
    public static String readFileContent(Path path) throws IOException {
        return Files.readString(path);
    }
}
```

#### With Documentation:
```java
public class Calculator {
    
    /**
     * @deprecated Use {@link #divide(double, double)} instead.
     * This method doesn't handle division by zero properly.
     */
    @Deprecated
    public int divide(int a, int b) {
        return a / b;  // Problematic - no zero check
    }
    
    public double divide(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException("Division by zero");
        }
        return a / b;
    }
}
```

#### Since Java 9 - Enhanced @Deprecated:
```java
public class EnhancedDeprecation {
    
    @Deprecated(since = "1.5", forRemoval = true)
    public void oldMethod() {
        // Will be removed in future version
    }
    
    @Deprecated(since = "2.0", forRemoval = false)
    public void legacyMethod() {
        // Deprecated but will remain for compatibility
    }
}
```

### 183. What is @FunctionalInterface?

**@FunctionalInterface** marks interfaces with **exactly one abstract method**:

#### Basic Example:
```java
@FunctionalInterface
public interface Calculator {
    int calculate(int a, int b);  // Single abstract method
    
    // Default methods allowed
    default int add(int a, int b) {
        return calculate(a, b);
    }
    
    // Static methods allowed
    static int multiply(int a, int b) {
        return a * b;
    }
}
```

#### Usage with Lambda:
```java
public class FunctionalExample {
    public static void main(String[] args) {
        // Lambda expression
        Calculator adder = (a, b) -> a + b;
        Calculator multiplier = (a, b) -> a * b;
        
        System.out.println(adder.calculate(5, 3));      // 8
        System.out.println(multiplier.calculate(5, 3)); // 15
    }
}
```

#### Built-in Functional Interfaces:
```java
// Predicate<T> - takes T, returns boolean
Predicate<String> isEmpty = String::isEmpty;

// Function<T, R> - takes T, returns R
Function<String, Integer> length = String::length;

// Consumer<T> - takes T, returns void
Consumer<String> printer = System.out::println;

// Supplier<T> - takes nothing, returns T
Supplier<String> supplier = () -> "Hello";
```

### 184. How do you process annotations at runtime?

Use **reflection** to process annotations at runtime:

#### Basic Annotation Processing:
```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Test {
    String value() default "";
    int timeout() default 1000;
}

public class TestRunner {
    
    @Test(value = "Addition test", timeout = 5000)
    public void testAddition() {
        // Test implementation
    }
    
    @Test("Subtraction test")
    public void testSubtraction() {
        // Test implementation
    }
    
    public void regularMethod() {
        // Not a test method
    }
}
```

#### Processing Code:
```java
public class AnnotationProcessor {
    
    public static void runTests(Class<?> testClass) {
        Method[] methods = testClass.getDeclaredMethods();
        
        for (Method method : methods) {
            if (method.isAnnotationPresent(Test.class)) {
                Test testAnnotation = method.getAnnotation(Test.class);
                
                System.out.println("Running test: " + testAnnotation.value());
                System.out.println("Timeout: " + testAnnotation.timeout() + "ms");
                
                try {
                    Object instance = testClass.getDeclaredConstructor().newInstance();
                    method.invoke(instance);
                    System.out.println("✅ Test passed");
                } catch (Exception e) {
                    System.out.println("❌ Test failed: " + e.getMessage());
                }
            }
        }
    }
    
    public static void main(String[] args) {
        runTests(TestRunner.class);
    }
}
```

#### Advanced Processing:
```java
public class AdvancedProcessor {
    
    public static void processClass(Class<?> clazz) {
        // Check class-level annotations
        if (clazz.isAnnotationPresent(ApiEndpoint.class)) {
            ApiEndpoint api = clazz.getAnnotation(ApiEndpoint.class);
            System.out.println("API Path: " + api.path());
        }
        
        // Process field annotations
        for (Field field : clazz.getDeclaredFields()) {
            Annotation[] annotations = field.getAnnotations();
            for (Annotation annotation : annotations) {
                System.out.println("Field " + field.getName() + 
                                 " has annotation: " + annotation);
            }
        }
        
        // Process method annotations
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Test.class)) {
                processTestMethod(method);
            }
        }
    }
}
```

### 185. What is annotation processing?

**Annotation processing** happens at **compile-time** to generate code or validate annotations:

#### Annotation Processor:
```java
@SupportedAnnotationTypes("com.example.MyAnnotation")
@SupportedSourceVersion(SourceVersion.RELEASE_11)
public class MyAnnotationProcessor extends AbstractProcessor {
    
    @Override
    public boolean process(Set<? extends TypeElement> annotations,
                          RoundEnvironment roundEnv) {
        
        for (Element element : roundEnv.getElementsAnnotatedWith(MyAnnotation.class)) {
            // Process annotated elements
            MyAnnotation annotation = element.getAnnotation(MyAnnotation.class);
            
            // Generate code, validate, or create resources
            generateCode(element, annotation);
        }
        
        return true; // Claim the annotations
    }
    
    private void generateCode(Element element, MyAnnotation annotation) {
        try {
            // Create new source file
            JavaFileObject sourceFile = processingEnv.getFiler()
                .createSourceFile("Generated" + element.getSimpleName());
            
            try (PrintWriter writer = new PrintWriter(sourceFile.openWriter())) {
                writer.println("// Generated code");
                writer.println("public class Generated" + element.getSimpleName() + " {");
                writer.println("    // Generated content");
                writer.println("}");
            }
        } catch (IOException e) {
            processingEnv.getMessager().printMessage(
                Diagnostic.Kind.ERROR, "Failed to generate code: " + e.getMessage());
        }
    }
}
```

#### Registration (META-INF/services/javax.annotation.processing.Processor):
```
com.example.MyAnnotationProcessor
```

#### Build Integration:
```xml
<!-- Maven -->
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-compiler-plugin</artifactId>
    <configuration>
        <annotationProcessors>
            <annotationProcessor>com.example.MyAnnotationProcessor</annotationProcessor>
        </annotationProcessors>
    </configuration>
</plugin>
```

#### Popular Examples:
- **Lombok**: Generates getters, setters, constructors
- **MapStruct**: Generates mapping code
- **Dagger**: Generates dependency injection code
- **AutoValue**: Generates value classes

## Best Practices

### 1. Choose Appropriate Retention:
```java
@Retention(RetentionPolicy.SOURCE)   // Compile-time only
public @interface Generated { }

@Retention(RetentionPolicy.RUNTIME)  // Framework processing
public @interface Entity { }
```

### 2. Use Meaningful Names:
```java
// Good
@Transactional
@CacheEvict
@RequestMapping

// Avoid
@A
@Util
@Helper
```

### 3. Provide Defaults:
```java
public @interface Cache {
    String value() default "";
    int timeout() default 3600;
    boolean enabled() default true;
}
```

### 4. Document Custom Annotations:
```java
/**
 * Marks methods that should be executed asynchronously.
 * 
 * @since 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Async {
    /**
     * The name of the executor to use.
     * @return executor name
     */
    String executor() default "default";
}
```

## Key Takeaways

1. **Annotations are metadata** that don't change program behavior directly
2. **Built-in annotations** provide compiler instructions and documentation
3. **Custom annotations** use @interface with meta-annotations
4. **Retention policy** determines annotation lifetime
5. **@Override exists, @Overload doesn't** - overloading is automatic
6. **Runtime processing** uses reflection to examine annotations
7. **Compile-time processing** generates code and validates structure
8. **Choose appropriate retention** based on use case
## Reflection Fundamentals

### 186. What is reflection in Java?

**Reflection** is Java's ability to **examine and modify program structure at runtime**:

#### Key Capabilities:
- **Inspect classes**: Get class information, methods, fields, constructors
- **Create objects**: Instantiate classes dynamically
- **Invoke methods**: Call methods by name at runtime
- **Access fields**: Read and modify field values
- **Bypass access control**: Access private members

#### Basic Example:
```java
// Get class information
Class<?> clazz = String.class;
System.out.println("Class name: " + clazz.getName());
System.out.println("Methods count: " + clazz.getMethods().length);

// Create object dynamically
Object obj = clazz.getDeclaredConstructor().newInstance();
```

#### Use Cases:
- **Frameworks**: Spring, Hibernate dependency injection
- **Testing**: JUnit, Mockito
- **Serialization**: JSON/XML libraries
- **Development tools**: IDEs, debuggers

### 187. How do you get Class object?

There are **three main ways** to obtain Class objects:

#### 1. Class Literal (.class):
```java
// Most efficient - compile-time constant
Class<String> stringClass = String.class;
Class<Integer> intClass = Integer.class;
Class<List> listClass = List.class;
```

#### 2. Object.getClass():
```java
// Runtime class of object instance
String str = "Hello";
Class<?> clazz = str.getClass();
System.out.println(clazz.getName()); // java.lang.String

List<String> list = new ArrayList<>();
Class<?> listClazz = list.getClass();
System.out.println(listClazz.getName()); // java.util.ArrayList
```

#### 3. Class.forName():
```java
// Dynamic loading by name
try {
    Class<?> clazz = Class.forName("java.lang.String");
    Class<?> dbDriver = Class.forName("com.mysql.cj.jdbc.Driver");
} catch (ClassNotFoundException e) {
    e.printStackTrace();
}
```

#### Comparison:
| Method | When to Use | Performance | Exception |
|--------|-------------|-------------|-----------|
| **.class** | Known at compile-time | Fastest | None |
| **getClass()** | Have object instance | Fast | None |
| **forName()** | Dynamic class name | Slower | ClassNotFoundException |

### 188. How do you create objects using reflection?

Use **constructors** to create objects dynamically:

#### Default Constructor:
```java
try {
    Class<?> clazz = String.class;
    
    // Get default constructor
    Constructor<?> constructor = clazz.getDeclaredConstructor();
    
    // Create instance
    Object instance = constructor.newInstance();
    
} catch (Exception e) {
    e.printStackTrace();
}
```

#### Parameterized Constructor:
```java
try {
    Class<?> clazz = String.class;
    
    // Get constructor with specific parameter types
    Constructor<?> constructor = clazz.getDeclaredConstructor(String.class);
    
    // Create instance with arguments
    Object instance = constructor.newInstance("Hello World");
    
    System.out.println(instance); // Hello World
    
} catch (Exception e) {
    e.printStackTrace();
}
```

#### Complex Example:
```java
public class Person {
    private String name;
    private int age;
    
    public Person() {}
    
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + "}";
    }
}

// Create Person using reflection
try {
    Class<?> personClass = Person.class;
    
    // Create with parameterized constructor
    Constructor<?> constructor = personClass.getDeclaredConstructor(String.class, int.class);
    Object person = constructor.newInstance("John", 25);
    
    System.out.println(person); // Person{name='John', age=25}
    
} catch (Exception e) {
    e.printStackTrace();
}
```

### 189. How do you invoke methods using reflection?

Use **Method** objects to invoke methods dynamically:

#### Public Method Invocation:
```java
try {
    String str = "Hello World";
    Class<?> clazz = str.getClass();
    
    // Get method by name and parameter types
    Method method = clazz.getMethod("substring", int.class);
    
    // Invoke method
    Object result = method.invoke(str, 6);
    System.out.println(result); // World
    
} catch (Exception e) {
    e.printStackTrace();
}
```

#### Private Method Invocation:
```java
public class Calculator {
    private int add(int a, int b) {
        return a + b;
    }
    
    public int multiply(int a, int b) {
        return a * b;
    }
}

// Invoke private method
try {
    Calculator calc = new Calculator();
    Class<?> clazz = calc.getClass();
    
    // Get private method
    Method privateMethod = clazz.getDeclaredMethod("add", int.class, int.class);
    
    // Make accessible
    privateMethod.setAccessible(true);
    
    // Invoke
    Object result = privateMethod.invoke(calc, 5, 3);
    System.out.println(result); // 8
    
} catch (Exception e) {
    e.printStackTrace();
}
```

#### Static Method Invocation:
```java
try {
    Class<?> mathClass = Math.class;
    
    // Get static method
    Method maxMethod = mathClass.getMethod("max", int.class, int.class);
    
    // Invoke static method (null as instance)
    Object result = maxMethod.invoke(null, 10, 20);
    System.out.println(result); // 20
    
} catch (Exception e) {
    e.printStackTrace();
}
```

### 190. How do you access private fields using reflection?

Use **Field** objects to access and modify fields:

#### Reading Private Fields:
```java
public class Student {
    private String name;
    private int grade;
    
    public Student(String name, int grade) {
        this.name = name;
        this.grade = grade;
    }
}

// Access private fields
try {
    Student student = new Student("Alice", 95);
    Class<?> clazz = student.getClass();
    
    // Get private field
    Field nameField = clazz.getDeclaredField("name");
    Field gradeField = clazz.getDeclaredField("grade");
    
    // Make accessible
    nameField.setAccessible(true);
    gradeField.setAccessible(true);
    
    // Read values
    String name = (String) nameField.get(student);
    int grade = (int) gradeField.get(student);
    
    System.out.println("Name: " + name);   // Alice
    System.out.println("Grade: " + grade); // 95
    
} catch (Exception e) {
    e.printStackTrace();
}
```

#### Modifying Private Fields:
```java
try {
    Student student = new Student("Bob", 80);
    Class<?> clazz = student.getClass();
    
    Field gradeField = clazz.getDeclaredField("grade");
    gradeField.setAccessible(true);
    
    // Modify field value
    gradeField.set(student, 90);
    
    // Verify change
    int newGrade = (int) gradeField.get(student);
    System.out.println("New grade: " + newGrade); // 90
    
} catch (Exception e) {
    e.printStackTrace();
}
```

#### Static Field Access:
```java
public class Config {
    private static String version = "1.0";
    public static final String APP_NAME = "MyApp";
}

// Access static fields
try {
    Class<?> configClass = Config.class;
    
    // Private static field
    Field versionField = configClass.getDeclaredField("version");
    versionField.setAccessible(true);
    String version = (String) versionField.get(null); // null for static
    
    // Public static field
    Field appNameField = configClass.getField("APP_NAME");
    String appName = (String) appNameField.get(null);
    
    System.out.println("Version: " + version);   // 1.0
    System.out.println("App Name: " + appName); // MyApp
    
} catch (Exception e) {
    e.printStackTrace();
}
```

### 191. What are the performance implications of reflection?

Reflection has **significant performance overhead**:

#### Performance Comparison:
```java
public class PerformanceTest {
    private int value = 42;
    
    public int getValue() {
        return value;
    }
    
    public static void main(String[] args) throws Exception {
        PerformanceTest obj = new PerformanceTest();
        Class<?> clazz = obj.getClass();
        Method method = clazz.getMethod("getValue");
        
        int iterations = 1_000_000;
        
        // Direct method call
        long start = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            obj.getValue();
        }
        long directTime = System.nanoTime() - start;
        
        // Reflection method call
        start = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            method.invoke(obj);
        }
        long reflectionTime = System.nanoTime() - start;
        
        System.out.println("Direct: " + directTime + " ns");
        System.out.println("Reflection: " + reflectionTime + " ns");
        System.out.println("Reflection is " + (reflectionTime / directTime) + "x slower");
    }
}
```

#### Performance Factors:
- **Method lookup**: Finding methods by name
- **Security checks**: Access control verification
- **Type checking**: Parameter validation
- **Boxing/Unboxing**: Primitive type conversion
- **Exception handling**: Checked exception overhead

#### Optimization Strategies:
```java
public class OptimizedReflection {
    // Cache Method objects
    private static final Map<String, Method> methodCache = new HashMap<>();
    
    public static Method getCachedMethod(Class<?> clazz, String methodName, Class<?>... paramTypes) {
        String key = clazz.getName() + "." + methodName;
        return methodCache.computeIfAbsent(key, k -> {
            try {
                return clazz.getMethod(methodName, paramTypes);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
```

### 192. When should you use reflection?

#### Appropriate Use Cases:

##### 1. Framework Development:
```java
// Dependency injection
@Component
public class UserService {
    @Autowired
    private UserRepository repository; // Injected via reflection
}
```

##### 2. Testing Frameworks:
```java
// JUnit test discovery
@Test
public void testUserCreation() {
    // Test methods found via reflection
}
```

##### 3. Serialization Libraries:
```java
// JSON serialization
public class JsonSerializer {
    public String serialize(Object obj) {
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        // Convert fields to JSON using reflection
        return "{}";
    }
}
```

##### 4. Configuration and Plugins:
```java
// Dynamic plugin loading
String pluginClassName = config.getProperty("plugin.class");
Class<?> pluginClass = Class.forName(pluginClassName);
Plugin plugin = (Plugin) pluginClass.getDeclaredConstructor().newInstance();
```

#### When NOT to Use:
- **Regular application logic**: Use direct method calls
- **Performance-critical code**: Reflection is slow
- **Simple object creation**: Use constructors directly
- **Known types at compile-time**: Use normal Java syntax

### 193. What are the security implications of reflection?

Reflection can **bypass Java's access control**:

#### Security Risks:

##### 1. Access Private Members:
```java
// Can access private fields and methods
Field privateField = clazz.getDeclaredField("password");
privateField.setAccessible(true); // Bypasses private access
String password = (String) privateField.get(obj);
```

##### 2. Modify Final Fields:
```java
public class SecurityTest {
    private final String secret = "classified";
}

// Modify final field
Field secretField = SecurityTest.class.getDeclaredField("secret");
secretField.setAccessible(true);

Field modifiersField = Field.class.getDeclaredField("modifiers");
modifiersField.setAccessible(true);
modifiersField.setInt(secretField, secretField.getModifiers() & ~Modifier.FINAL);

secretField.set(instance, "compromised");
```

#### Security Measures:

##### 1. SecurityManager:
```java
// Restrict reflection usage
System.setSecurityManager(new SecurityManager() {
    @Override
    public void checkPermission(Permission perm) {
        if (perm instanceof ReflectPermission) {
            throw new SecurityException("Reflection not allowed");
        }
    }
});
```

##### 2. Input Validation:
```java
public Object createInstance(String className) {
    // Validate class name
    if (!isAllowedClass(className)) {
        throw new SecurityException("Class not allowed: " + className);
    }
    
    try {
        return Class.forName(className).getDeclaredConstructor().newInstance();
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
}

private boolean isAllowedClass(String className) {
    return className.startsWith("com.mycompany.safe.");
}
```

### 194. What is the difference between Class.forName() and ClassLoader.loadClass()?

#### Key Differences:

| Aspect | Class.forName() | ClassLoader.loadClass() |
|--------|-----------------|-------------------------|
| **Initialization** | Initializes class | Only loads class |
| **Static blocks** | Executes static blocks | Doesn't execute |
| **Performance** | Slower (full initialization) | Faster (load only) |
| **Use case** | Need initialized class | Just need class reference |

#### Examples:

##### Class.forName() - Full Initialization:
```java
public class DatabaseDriver {
    static {
        System.out.println("Driver initialized!");
        // Register driver with DriverManager
    }
}

// This will print "Driver initialized!"
Class<?> driverClass = Class.forName("com.example.DatabaseDriver");
```

##### ClassLoader.loadClass() - Load Only:
```java
// This will NOT print "Driver initialized!"
ClassLoader loader = Thread.currentThread().getContextClassLoader();
Class<?> driverClass = loader.loadClass("com.example.DatabaseDriver");

// To initialize later
Class.forName(driverClass.getName(), true, loader);
```

#### Practical Example:
```java
public class InitializationTest {
    static {
        System.out.println("Static block executed");
    }
    
    public static void main(String[] args) throws Exception {
        System.out.println("=== Using Class.forName() ===");
        Class.forName("InitializationTest"); // Prints static block
        
        System.out.println("=== Using ClassLoader.loadClass() ===");
        ClassLoader.getSystemClassLoader()
            .loadClass("InitializationTest"); // Doesn't print
    }
}
```

### 195. How do you handle exceptions in reflection?

Reflection operations throw **multiple checked exceptions**:

#### Common Exceptions:

##### 1. ClassNotFoundException:
```java
try {
    Class<?> clazz = Class.forName("com.nonexistent.Class");
} catch (ClassNotFoundException e) {
    System.err.println("Class not found: " + e.getMessage());
    // Handle missing class
}
```

##### 2. NoSuchMethodException:
```java
try {
    Method method = String.class.getMethod("nonExistentMethod");
} catch (NoSuchMethodException e) {
    System.err.println("Method not found: " + e.getMessage());
    // Try alternative method or fail gracefully
}
```

##### 3. IllegalAccessException:
```java
try {
    Field privateField = MyClass.class.getDeclaredField("privateField");
    // privateField.setAccessible(true); // Uncomment to fix
    Object value = privateField.get(instance);
} catch (IllegalAccessException e) {
    System.err.println("Cannot access field: " + e.getMessage());
    // Handle access denied
}
```

##### 4. InvocationTargetException:
```java
public class TestClass {
    public void throwingMethod() {
        throw new RuntimeException("Method failed!");
    }
}

try {
    Method method = TestClass.class.getMethod("throwingMethod");
    method.invoke(new TestClass());
} catch (InvocationTargetException e) {
    // Get the actual exception thrown by the method
    Throwable cause = e.getCause();
    System.err.println("Method threw: " + cause.getMessage());
} catch (Exception e) {
    System.err.println("Reflection error: " + e.getMessage());
}
```

#### Comprehensive Exception Handling:
```java
public class ReflectionUtils {
    
    public static Object safeInvoke(Object obj, String methodName, Object... args) {
        try {
            Class<?> clazz = obj.getClass();
            Class<?>[] paramTypes = Arrays.stream(args)
                .map(Object::getClass)
                .toArray(Class[]::new);
            
            Method method = clazz.getMethod(methodName, paramTypes);
            return method.invoke(obj, args);
            
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Method not found: " + methodName, e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Cannot access method: " + methodName, e);
        } catch (InvocationTargetException e) {
            // Re-throw the original exception
            Throwable cause = e.getCause();
            if (cause instanceof RuntimeException) {
                throw (RuntimeException) cause;
            } else if (cause instanceof Error) {
                throw (Error) cause;
            } else {
                throw new RuntimeException("Method execution failed", cause);
            }
        }
    }
}
```

## Best Practices

### 1. Cache Reflection Objects:
```java
private static final Map<String, Method> methodCache = new ConcurrentHashMap<>();

public static Method getMethod(Class<?> clazz, String name, Class<?>... paramTypes) {
    String key = clazz.getName() + "." + name;
    return methodCache.computeIfAbsent(key, k -> {
        try {
            return clazz.getMethod(name, paramTypes);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    });
}
```

### 2. Use setAccessible() Carefully:
```java
// Check if accessible first
if (!field.isAccessible()) {
    field.setAccessible(true);
}
```

### 3. Handle Exceptions Properly:
```java
try {
    // Reflection operations
} catch (ReflectiveOperationException e) {
    // Handle all reflection exceptions
    logger.error("Reflection failed", e);
    throw new ServiceException("Operation failed", e);
}
```

### 4. Validate Input:
```java
public Object createInstance(String className) {
    Objects.requireNonNull(className, "Class name cannot be null");
    
    if (!isValidClassName(className)) {
        throw new IllegalArgumentException("Invalid class name: " + className);
    }
    
    // Proceed with reflection
}
```

## Key Takeaways

1. **Reflection enables runtime introspection** and dynamic behavior
2. **Performance cost is significant** - use caching and avoid in hot paths
3. **Security implications** - can bypass access controls
4. **Multiple ways to get Class objects** - choose based on use case
5. **Exception handling is crucial** - many checked exceptions to handle
6. **Use for frameworks and tools** - avoid in regular application logic
7. **Class.forName() initializes, loadClass() doesn't** - important distinction
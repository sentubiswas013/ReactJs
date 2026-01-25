# 13. Java Annotations & Reflection 

## 1. What are annotations in Java?

Annotations are metadata that provide information about code without affecting its execution. They're used by compilers, development tools, and frameworks to process code automatically.

- Metadata attached to code elements
- Start with @ symbol
- Don't change program behavior directly
- Used by tools and frameworks for processing
- Can be applied to classes, methods, fields, parameters

```java
@Override
public String toString() {
    return "Example";
}

@Deprecated
public void oldMethod() {
    // Legacy code
}
```

## 2. What are built-in annotations?

Java provides several built-in annotations for common use cases like method overriding, deprecation warnings, and compiler instructions.

**Common Built-in Annotations:**
- **@Override:** Indicates method overrides parent method
- **@Deprecated:** Marks code as outdated
- **@SuppressWarnings:** Suppresses compiler warnings
- **@FunctionalInterface:** Marks functional interfaces
- **@SafeVarargs:** Suppresses varargs warnings

```java
@Override
public void method() { } // Ensures proper overriding

@Deprecated
public void legacyMethod() { } // Warns users about deprecation

@SuppressWarnings("unchecked")
List list = new ArrayList(); // Suppresses unchecked warning
```

## 3. How do you create custom annotations?

Custom annotations are created using @interface keyword and can include elements with default values. They require retention and target policies.

```java
// Custom annotation definition
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MyAnnotation {
    String value() default "default";
    int priority() default 1;
}

// Usage
@MyAnnotation(value = "important", priority = 5)
public void annotatedMethod() {
    // Method implementation
}

// Processing annotation
Method method = MyClass.class.getMethod("annotatedMethod");
MyAnnotation annotation = method.getAnnotation(MyAnnotation.class);
String value = annotation.value(); // "important"
```

## 4. What is retention policy?

Retention policy determines how long annotations are retained - in source code, class files, or at runtime.

**Retention Policies:**
- **SOURCE:** Discarded by compiler (e.g., @Override)
- **CLASS:** Stored in class files but not available at runtime
- **RUNTIME:** Available at runtime via reflection

```java
@Retention(RetentionPolicy.SOURCE)   // Compile-time only
@interface CompileTime { }

@Retention(RetentionPolicy.RUNTIME)  // Available at runtime
@interface RuntimeAnnotation { }
```

## 5. What is the difference between @Override and @Overload?

**@Override:**
- Built-in annotation
- Indicates method overrides parent method
- Compile-time verification
- Prevents accidental method signature mistakes

**@Overload:**
- Not a standard Java annotation
- Method overloading happens automatically
- No special annotation needed
- Multiple methods with same name, different parameters

```java
class Parent {
    public void method() { }
}

class Child extends Parent {
    @Override
    public void method() { } // Overriding - uses @Override
    
    public void method(int x) { } // Overloading - no annotation needed
    public void method(String s) { } // Another overload
}
```

## 6. What is reflection in Java?

Reflection is the ability to inspect and manipulate classes, methods, fields, and other code elements at runtime. It allows programs to examine their own structure dynamically.

- Examine class structure at runtime
- Create objects dynamically
- Invoke methods dynamically
- Access private fields and methods
- Used by frameworks like Spring, Hibernate

```java
// Get class information
Class<?> clazz = String.class;
Method[] methods = clazz.getMethods();

// Create instance dynamically
Object obj = clazz.newInstance();

// Invoke method dynamically
Method method = clazz.getMethod("length");
int length = (int) method.invoke("Hello");
```

## 7. When should you use reflection?

Use reflection when you need dynamic behavior that cannot be achieved with normal Java code, typically in frameworks and libraries.

**Appropriate Use Cases:**
- Building frameworks (Spring, Hibernate)
- Serialization/deserialization libraries
- Testing frameworks (JUnit)
- Dependency injection containers
- Code analysis tools
- Plugin architectures

**Avoid reflection for:**
- Normal application logic
- Performance-critical code
- When compile-time solutions exist

## 8. What are the performance implications of reflection?

Reflection is significantly slower than direct method calls due to runtime type checking, security checks, and method resolution overhead.

**Performance Issues:**
- Method lookup is expensive
- Security permission checks
- Type validation at runtime
- No compiler optimizations
- Boxing/unboxing for primitives

**Optimization Strategies:**
- Cache Method/Field objects
- Use MethodHandle (Java 7+) for better performance
- Minimize reflection usage in hot paths

```java
// Slow - repeated reflection
for (int i = 0; i < 1000000; i++) {
    Method method = obj.getClass().getMethod("toString");
    method.invoke(obj);
}

// Better - cache method
Method method = obj.getClass().getMethod("toString");
for (int i = 0; i < 1000000; i++) {
    method.invoke(obj);
}
```

## 9. What are the security implications of reflection?

Reflection can bypass normal access controls and security restrictions, potentially exposing private implementation details and creating security vulnerabilities.

**Security Risks:**
- Access private fields and methods
- Bypass encapsulation
- Modify final fields
- Create instances of restricted classes
- Invoke restricted methods

**Security Measures:**
- SecurityManager can restrict reflection
- Use setAccessible() carefully
- Validate inputs when using reflection
- Consider security policies in production

```java
// Reflection can access private members
Field privateField = obj.getClass().getDeclaredField("privateField");
privateField.setAccessible(true); // Bypass access control
Object value = privateField.get(obj); // Access private field
```

## 10. How do you handle exceptions in reflection?

Reflection operations throw various checked exceptions that must be handled properly to ensure robust code.

**Common Reflection Exceptions:**
- **ClassNotFoundException:** Class not found
- **NoSuchMethodException:** Method doesn't exist
- **IllegalAccessException:** Access denied
- **InvocationTargetException:** Exception in invoked method
- **InstantiationException:** Cannot create instance

```java
try {
    Class<?> clazz = Class.forName("com.example.MyClass");
    Method method = clazz.getMethod("myMethod");
    Object result = method.invoke(obj);
} catch (ClassNotFoundException e) {
    // Handle class not found
} catch (NoSuchMethodException e) {
    // Handle method not found
} catch (IllegalAccessException e) {
    // Handle access denied
} catch (InvocationTargetException e) {
    // Handle exception from invoked method
    Throwable cause = e.getCause(); // Get actual exception
}
```
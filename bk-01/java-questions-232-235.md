# 🔹 Enums and Other Features

### Question 232: What is autoboxing and unboxing?

**Answer (25 seconds):**
* Autoboxing automatically converts primitive types to wrapper objects
* Unboxing converts wrapper objects back to primitives
* Happens automatically during assignments and method calls
* Improves code readability but has performance overhead

```java
// Autoboxing
Integer num = 10; // int to Integer

// Unboxing  
int value = num; // Integer to int

// In collections
List<Integer> list = new ArrayList<>();
list.add(5); // autoboxing
```

---

### Question 233: What is enum in Java?

**Answer (30 seconds):**
* Enum is a special class representing a group of constants
* More powerful than traditional constants - can have methods and constructors
* Type-safe and prevents invalid values
* Commonly used for fixed sets of values like days, colors, states

```java
public enum Status {
    ACTIVE, INACTIVE, PENDING;
    
    public boolean isActive() {
        return this == ACTIVE;
    }
}

Status status = Status.ACTIVE;
```

---

### Question 234: What are the advantages of using enum?

**Answer (35 seconds):**
* **Type Safety**: Compile-time checking prevents invalid values
* **Readability**: More meaningful than integer constants
* **Maintainability**: Adding new values is easy and safe
* **Built-in Methods**: toString(), valueOf(), ordinal() methods
* **Switch Support**: Works perfectly with switch statements
* **Singleton**: Each enum constant is a singleton by default

```java
public enum Priority {
    LOW(1), MEDIUM(5), HIGH(10);
    
    private final int value;
    Priority(int value) { this.value = value; }
    public int getValue() { return value; }
}
```

---

### Question 235: What is varargs in Java?

**Answer (30 seconds):**
* Varargs allows methods to accept variable number of arguments
* Uses three dots (...) syntax after parameter type
* Internally treated as an array
* Must be the last parameter in method signature
* Eliminates need for method overloading with different parameter counts

```java
public void print(String... messages) {
    for(String msg : messages) {
        System.out.println(msg);
    }
}

// Usage
print("Hello");
print("Hello", "World");
print("A", "B", "C");
```

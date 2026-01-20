# 🔹 Generics

## Generics Fundamentals

### 166. What are generics in Java?

**Generics** enable **parameterized types** for compile-time type safety:

#### Key Benefits:
- **Type safety**: Compile-time type checking
- **Elimination of casting**: No explicit type conversion needed
- **Generic algorithms**: Write code that works with multiple types
- **Better readability**: Clear intent of what types are expected

#### Basic Syntax:
```java
// Generic class
public class Box<T> {
    private T content;
    
    public void set(T content) {
        this.content = content;
    }
    
    public T get() {
        return content;
    }
}

// Usage
Box<String> stringBox = new Box<>();
stringBox.set("Hello");
String value = stringBox.get(); // No casting needed
```

#### Before vs After Generics:
```java
// Before generics (Java 1.4 and earlier)
List list = new ArrayList();
list.add("Hello");
String str = (String) list.get(0); // Casting required, runtime risk

// With generics (Java 5+)
List<String> list = new ArrayList<>();
list.add("Hello");
String str = list.get(0); // No casting, compile-time safety
```

### 167. Why were generics introduced?

Generics were introduced in **Java 5** to solve several problems:

#### Problems Before Generics:
1. **Runtime ClassCastException**
2. **Explicit casting everywhere**
3. **No compile-time type checking**
4. **Code not self-documenting**

#### Example of Problems:
```java
// Pre-generics code
List list = new ArrayList();
list.add("String");
list.add(42); // Oops! Integer added to "string" list

// Runtime error!
String str = (String) list.get(1); // ClassCastException
```

#### Solutions Provided:
```java
// With generics - compile-time safety
List<String> list = new ArrayList<>();
list.add("String");
// list.add(42); // Compile error - prevents runtime issues

String str = list.get(0); // No casting needed
```

#### Benefits Achieved:
- **Compile-time error detection**
- **Elimination of casting**
- **Better code documentation**
- **Generic algorithm implementation**

### 168. What is type erasure?

**Type erasure** removes generic type information at runtime:

#### How It Works:
1. **Compile-time**: Generics provide type checking
2. **Runtime**: Generic information is erased
3. **Bytecode**: Contains no generic type information
4. **Backward compatibility**: Works with pre-generic code

#### Example:
```java
// Source code
List<String> stringList = new ArrayList<>();
List<Integer> intList = new ArrayList<>();

// After type erasure (runtime)
List stringList = new ArrayList(); // Raw type
List intList = new ArrayList();    // Raw type

// Both have same runtime type
System.out.println(stringList.getClass() == intList.getClass()); // true
```

#### Implications:
```java
// Cannot do this due to type erasure
if (list instanceof List<String>) { } // Compile error

// Can only check raw type
if (list instanceof List) { } // OK

// Cannot create generic array
T[] array = new T[10]; // Compile error
```

### 169. What are bounded type parameters?

**Bounded type parameters** restrict the types that can be used:

#### Upper Bounds (extends):
```java
// T must extend Number
public class NumberBox<T extends Number> {
    private T value;
    
    public void setValue(T value) {
        this.value = value;
    }
    
    // Can call Number methods
    public double getDoubleValue() {
        return value.doubleValue(); // OK - Number method
    }
}

// Usage
NumberBox<Integer> intBox = new NumberBox<>(); // OK
NumberBox<Double> doubleBox = new NumberBox<>(); // OK
// NumberBox<String> stringBox = new NumberBox<>(); // Compile error
```

#### Multiple Bounds:
```java
// T must extend Number AND implement Comparable
public class SortableNumber<T extends Number & Comparable<T>> {
    public int compare(T a, T b) {
        return a.compareTo(b); // Can use both Number and Comparable methods
    }
}
```

#### Lower Bounds (super):
```java
// Used with wildcards
public void addNumbers(List<? super Integer> list) {
    list.add(42);        // OK - can add Integer
    list.add(3.14);      // Compile error - cannot add Double
}
```

### 170. What is the difference between <?> and <? extends Object>?

Both represent **unbounded wildcards** and are functionally identical:

#### Syntax Comparison:
```java
List<?> list1 = new ArrayList<String>();
List<? extends Object> list2 = new ArrayList<String>();

// Both are equivalent
System.out.println(list1.getClass() == list2.getClass()); // true
```

#### Usage Examples:
```java
// Both accept any parameterized type
public void printList(List<?> list) {
    for (Object item : list) {
        System.out.println(item);
    }
}

public void printList2(List<? extends Object> list) {
    for (Object item : list) {
        System.out.println(item);
    }
}

// Both work with any type
printList(Arrays.asList("a", "b"));
printList(Arrays.asList(1, 2, 3));
printList2(Arrays.asList("a", "b"));
printList2(Arrays.asList(1, 2, 3));
```

#### Best Practice:
- Use `<?>` for simplicity and readability
- `<? extends Object>` is more explicit but verbose

### 171. What is the difference between <? extends T> and <? super T>?

These are **bounded wildcards** with opposite purposes:

#### Upper Bound Wildcard (? extends T):
```java
// Can read T or its subtypes
List<? extends Number> numbers = new ArrayList<Integer>();

// Reading is safe
Number num = numbers.get(0); // OK - returns Number or subtype

// Writing is restricted
// numbers.add(42);          // Compile error
// numbers.add(3.14);        // Compile error
numbers.add(null);           // Only null allowed
```

#### Lower Bound Wildcard (? super T):
```java
// Can write T or its subtypes
List<? super Integer> numbers = new ArrayList<Number>();

// Writing is safe
numbers.add(42);             // OK - Integer
numbers.add((byte) 1);       // OK - Byte (subtype of Integer)

// Reading returns Object
Object obj = numbers.get(0); // Only Object guaranteed
// Integer num = numbers.get(0); // Compile error
```

#### Practical Example:
```java
// Producer - use extends (reading)
public static double sum(List<? extends Number> numbers) {
    double total = 0;
    for (Number num : numbers) {
        total += num.doubleValue(); // Reading - safe
    }
    return total;
}

// Consumer - use super (writing)
public static void addIntegers(List<? super Integer> list) {
    list.add(1);
    list.add(2);
    list.add(3); // Writing - safe
}
```

### 172. What is PECS principle?

**PECS** stands for **Producer Extends, Consumer Super**:

#### The Rule:
- **Producer Extends**: Use `<? extends T>` when **reading** from a collection
- **Consumer Super**: Use `<? super T>` when **writing** to a collection

#### Producer Example (Reading):
```java
// Collection produces/provides data - use extends
public static void printNumbers(List<? extends Number> numbers) {
    for (Number num : numbers) { // Reading from collection
        System.out.println(num);
    }
}

// Works with any Number subtype
printNumbers(Arrays.asList(1, 2, 3));        // Integer
printNumbers(Arrays.asList(1.1, 2.2, 3.3)); // Double
```

#### Consumer Example (Writing):
```java
// Collection consumes/accepts data - use super
public static void fillWithNumbers(List<? super Integer> list) {
    list.add(1);  // Writing to collection
    list.add(2);
    list.add(3);
}

// Works with Integer or its supertypes
List<Integer> intList = new ArrayList<>();
List<Number> numList = new ArrayList<>();
List<Object> objList = new ArrayList<>();

fillWithNumbers(intList); // OK
fillWithNumbers(numList); // OK  
fillWithNumbers(objList); // OK
```

#### Real-world Example:
```java
// Collections.copy() uses PECS
public static <T> void copy(List<? super T> dest,    // Consumer
                           List<? extends T> src) {  // Producer
    for (T item : src) {      // Reading from source (producer)
        dest.add(item);       // Writing to destination (consumer)
    }
}
```

### 173. Can you create generic arrays?

**No, you cannot create arrays of parameterized types** due to type erasure:

#### What Doesn't Work:
```java
// Compile errors
List<String>[] stringLists = new List<String>[10];     // Error
T[] array = new T[10];                                 // Error
E[] elements = new E[capacity];                        // Error
```

#### Why It's Prohibited:
```java
// If this were allowed (hypothetically)
List<String>[] stringLists = new List<String>[2];
Object[] objects = stringLists;                        // Arrays are covariant
objects[0] = Arrays.asList(42);                       // Would put List<Integer> in List<String>[]
String s = stringLists[0].get(0);                     // ClassCastException!
```

#### Workarounds:

##### 1. Use Collections:
```java
// Instead of arrays, use collections
List<List<String>> listOfLists = new ArrayList<>();
```

##### 2. Raw Type Array:
```java
@SuppressWarnings("unchecked")
List<String>[] stringLists = new List[10]; // Raw type array
```

##### 3. Bounded Array:
```java
// In generic class
public class GenericArray<T> {
    private T[] array;
    
    @SuppressWarnings("unchecked")
    public GenericArray(int size) {
        array = (T[]) new Object[size]; // Cast Object[] to T[]
    }
}
```

### 174. What are generic methods?

**Generic methods** have their own type parameters, independent of class generics:

#### Syntax:
```java
// Type parameter <T> before return type
public static <T> void swap(T[] array, int i, int j) {
    T temp = array[i];
    array[i] = array[j];
    array[j] = temp;
}
```

#### Multiple Type Parameters:
```java
public static <T, U> void print(T first, U second) {
    System.out.println(first + " " + second);
}
```

#### Bounded Generic Methods:
```java
public static <T extends Comparable<T>> T max(T a, T b) {
    return a.compareTo(b) > 0 ? a : b;
}
```

#### Generic Methods in Non-Generic Classes:
```java
public class Utility {
    // Generic method in regular class
    public static <T> List<T> createList(T... elements) {
        return Arrays.asList(elements);
    }
}

// Usage
List<String> strings = Utility.createList("a", "b", "c");
List<Integer> numbers = Utility.createList(1, 2, 3);
```

#### Type Inference:
```java
// Explicit type specification
List<String> list1 = Utility.<String>createList("a", "b");

// Type inference (preferred)
List<String> list2 = Utility.createList("a", "b");
```

### 175. What are the limitations of generics?

Generics have several limitations due to **type erasure** and **design decisions**:

#### 1. Cannot Instantiate Type Parameters:
```java
public class GenericClass<T> {
    // T t = new T(); // Compile error
}
```

#### 2. Cannot Create Arrays of Parameterized Types:
```java
// List<String>[] array = new List<String>[10]; // Compile error
```

#### 3. Cannot Use Primitives as Type Arguments:
```java
// List<int> numbers = new ArrayList<>(); // Compile error
List<Integer> numbers = new ArrayList<>(); // Use wrapper classes
```

#### 4. Cannot Catch or Throw Parameterized Exceptions:
```java
// class MyException<T> extends Exception {} // Compile error

public <T extends Exception> void method() throws T {
    // try {
    //     // code
    // } catch (T e) { } // Compile error
}
```

#### 5. Cannot Overload with Type Erasure Conflicts:
```java
public class Example {
    public void method(List<String> list) {}
    // public void method(List<Integer> list) {} // Compile error - same erasure
}
```

#### 6. Static Context Limitations:
```java
public class GenericClass<T> {
    // private static T staticField; // Compile error
    // public static T staticMethod() {} // Compile error
}
```

#### 7. Runtime Type Information Lost:
```java
List<String> strings = new ArrayList<>();
// Cannot check: strings instanceof List<String>
// Can only check: strings instanceof List
```

#### 8. Cannot Create Generic Exception Classes:
```java
// class GenericException<T> extends Exception {} // Not allowed
```

## Best Practices

### 1. Use Diamond Operator (Java 7+):
```java
List<String> list = new ArrayList<>(); // Preferred
// List<String> list = new ArrayList<String>(); // Verbose
```

### 2. Follow PECS Principle:
```java
// Producer extends, Consumer super
public void copy(List<? super T> dest, List<? extends T> src) {}
```

### 3. Use Bounded Wildcards When Appropriate:
```java
// More flexible than exact type
public double sum(List<? extends Number> numbers) {}
```

### 4. Prefer Generic Methods Over Raw Types:
```java
// Good
public static <T> void swap(List<T> list, int i, int j) {}

// Avoid
public static void swap(List list, int i, int j) {}
```

### 5. Use @SuppressWarnings Judiciously:
```java
@SuppressWarnings("unchecked")
T[] array = (T[]) new Object[size]; // Only when necessary
```

## Key Takeaways

1. **Generics provide compile-time type safety** without runtime overhead
2. **Type erasure** removes generic information at runtime for backward compatibility
3. **PECS principle** guides wildcard usage: Producer Extends, Consumer Super
4. **Bounded type parameters** restrict and enable method calls on type parameters
5. **Generic methods** can be more flexible than generic classes
6. **Limitations exist** due to type erasure and design decisions
7. **Use collections instead of arrays** for generic types
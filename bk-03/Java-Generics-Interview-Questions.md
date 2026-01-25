# 12. Java Generics 

## 1. What are generics in Java?

Generics allow you to write type-safe code by parameterizing types. They enable classes, interfaces, and methods to work with different types while providing compile-time type checking.

- Parameterized types using angle brackets <>
- Provide compile-time type safety
- Eliminate need for explicit casting
- Enable writing reusable code
- Introduced in Java 5

```java
// Without generics - requires casting
List list = new ArrayList();
list.add("Hello");
String str = (String) list.get(0); // Casting required

// With generics - type safe
List<String> list = new ArrayList<>();
list.add("Hello");
String str = list.get(0); // No casting needed
```

## 2. Why were generics introduced?

Generics were introduced to provide compile-time type safety, eliminate casting, and enable programmers to write more robust and readable code.

**Main Benefits:**
- **Type Safety:** Catch type errors at compile time
- **Eliminate Casting:** No need for explicit type casting
- **Code Reusability:** Same code works with different types
- **Better Performance:** No boxing/unboxing overhead
- **Clearer APIs:** Self-documenting code

```java
// Before generics - runtime error possible
List list = new ArrayList();
list.add("String");
list.add(123);
String str = (String) list.get(1); // ClassCastException at runtime

// With generics - compile-time error
List<String> list = new ArrayList<>();
list.add("String");
list.add(123); // Compile error - type mismatch
```

## 3. What is type erasure?

Type erasure is the process where generic type information is removed during compilation. The compiler replaces generic types with their bounds or Object, maintaining backward compatibility.

- Generic type information removed at runtime
- Replaced with raw types or bounds
- Maintains backward compatibility with pre-Java 5 code
- Bridge methods created for polymorphism
- Cannot access generic type at runtime

```java
// Source code
List<String> stringList = new ArrayList<>();
List<Integer> intList = new ArrayList<>();

// After type erasure (bytecode equivalent)
List stringList = new ArrayList();
List intList = new ArrayList();

// Both have same runtime type
System.out.println(stringList.getClass() == intList.getClass()); // true
```

## 4. What is the difference between &lt;? extends T&gt; and &lt;? super T&gt;?

**&lt;? extends T&gt; (Upper Bounded Wildcard):**
- Accepts T and its subtypes
- Read-only operations (Producer)
- Cannot add elements (except null)

**&lt;? super T&gt; (Lower Bounded Wildcard):**
- Accepts T and its supertypes
- Write operations allowed (Consumer)
- Can add T and its subtypes

```java
// ? extends T - can read, cannot write
List<? extends Number> numbers = new ArrayList<Integer>();
Number num = numbers.get(0); // OK - reading
// numbers.add(10); // Compile error - cannot write

// ? super T - can write, limited reading
List<? super Integer> integers = new ArrayList<Number>();
integers.add(10); // OK - writing Integer
// Integer val = integers.get(0); // Compile error - returns Object
Object val = integers.get(0); // OK - can read as Object
```

## 5. What is PECS principle?

PECS stands for "Producer Extends, Consumer Super" - a guideline for choosing between extends and super wildcards based on how you use the collection.

**Producer Extends:**
- Use `<? extends T>` when you only read from collection
- Collection produces/provides elements
- You're consuming what the collection produces

**Consumer Super:**
- Use `<? super T>` when you only write to collection
- Collection consumes/accepts elements
- You're producing elements for the collection

```java
// Producer - use extends
public void processNumbers(List<? extends Number> numbers) {
    for (Number num : numbers) { // Reading - OK
        System.out.println(num);
    }
}

// Consumer - use super
public void addNumbers(List<? super Integer> numbers) {
    numbers.add(10); // Writing - OK
    numbers.add(20);
}
```

## 6. What are the limitations of generics?

Generics have several limitations due to type erasure and backward compatibility requirements.

**Key Limitations:**
- Cannot instantiate generic types: `new T()`
- Cannot create arrays of parameterized types: `new List<String>[10]`
- Cannot use primitives as type parameters: `List<int>` (use `List<Integer>`)
- Cannot catch parameterized exceptions
- Static context cannot access type parameters
- Cannot overload methods that differ only in generic parameters

```java
public class GenericClass<T> {
    // Cannot do these:
    // T instance = new T(); // Error - cannot instantiate
    // T[] array = new T[10]; // Error - cannot create array
    // static T staticField; // Error - static context
    
    // Cannot catch generic exception
    // try { } catch (T e) { } // Error
    
    // Cannot overload based on generics only
    // void method(List<String> list) { }
    // void method(List<Integer> list) { } // Error - same erasure
}

// Workarounds
public class GenericClass<T> {
    private Class<T> type;
    
    public GenericClass(Class<T> type) {
        this.type = type;
    }
    
    public T createInstance() throws Exception {
        return type.newInstance(); // Reflection workaround
    }
}
```

These limitations exist primarily due to type erasure and the need to maintain backward compatibility with pre-generic Java code.
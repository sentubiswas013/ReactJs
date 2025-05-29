# Top 1000 Java Interview Question & Answers

## Collections
### 156. **What are the differences between the two data structures: a Vector and an ArrayList?**

`Vector` and `ArrayList` are both part of the Java collection framework and implement the `List` interface, but there are several differences between them:

| **Property**              | **Vector**                          | **ArrayList**                        |
|---------------------------|-------------------------------------|--------------------------------------|
| **Thread-safety**          | Vector is **synchronized**, making it thread-safe (but slower). | ArrayList is **not synchronized**, making it faster in single-threaded scenarios. |
| **Growth Policy**          | Vector doubles its size when it runs out of space. | ArrayList grows by 50% of its size when it runs out of space. |
| **Performance**            | Slower due to synchronization and larger size increase. | Faster as it is not synchronized and has a more efficient growth policy. |
| **Legacy**                 | Vector is considered a **legacy class** and is part of the original version of Java. | ArrayList is more commonly used today and is part of the modern Java collection framework. |
| **Use Case**               | Used in multi-threaded environments where thread safety is required. | Preferred for single-threaded applications where performance is critical. |

### Example:
```java
// Vector example (Thread-safe)
Vector<Integer> vector = new Vector<>();
vector.add(10);
vector.add(20);

// ArrayList example (Not thread-safe)
ArrayList<Integer> arrayList = new ArrayList<>();
arrayList.add(10);
arrayList.add(20);
```

### 157. **What are the differences between Collection and Collections in Java?**

`Collection` and `Collections` are two different concepts in Java:

| **Property**              | **Collection**                        | **Collections**                          |
|---------------------------|---------------------------------------|------------------------------------------|
| **Type**                   | `Collection` is an interface. It is the root interface in the collection framework and is implemented by other interfaces like `List`, `Set`, etc. | `Collections` is a **utility class** that provides static methods for operating on collections (e.g., sorting, searching). |
| **Purpose**                | Defines the basic operations for all collection types. | Provides utility methods for working with collections, like sorting, reversing, etc. |
| **Example**                | `List`, `Set`, `Queue` are types of collections. | `Collections.sort()`, `Collections.reverse()`, etc. |

### Example:
```java
// Collection example (interface)
Collection<String> collection = new ArrayList<>();
collection.add("Apple");
collection.add("Banana");

// Collections example (utility class)
Collections.sort(new ArrayList<>(collection));
Collections.reverse(new ArrayList<>(collection));
```

### 158. **In which scenario, LinkedList is better than ArrayList in Java?**

`LinkedList` is often preferred over `ArrayList` in scenarios where:

1. **Frequent Insertions/Deletions**: 
   - LinkedList is ideal for scenarios where you need to insert or delete elements frequently at the beginning or in the middle of the list, as it provides **constant-time** O(1) insertion/deletion operations.
   - ArrayList requires shifting of elements when an element is inserted or removed, which leads to O(n) time complexity for these operations.

2. **Memory Consumption**: 
   - LinkedList stores data as a series of nodes where each node contains a reference to the previous and next node. This results in more memory overhead than ArrayList, which stores data in a contiguous block of memory. However, for large-scale insertions or deletions, LinkedList is more efficient.

3. **Queue or Deque Usage**: 
   - If you need to implement **queue** or **deque** (double-ended queue) behavior, `LinkedList` is a good choice because it supports fast insertions and deletions at both ends (head and tail).

**Example**:
```java
LinkedList<Integer> list = new LinkedList<>();
list.addFirst(1);  // O(1)
list.addLast(2);   // O(1)
list.removeFirst(); // O(1)
```

For **ArrayList**, inserting at the beginning or in the middle is slower due to the need to shift elements. `ArrayList` is generally better for scenarios where elements are accessed randomly and not modified frequently.

---

### 159. **What are the differences between a List and Set collection in Java?**

`List` and `Set` are both interfaces in the Java Collections Framework, but they differ in several key aspects:

| **Property**              | **List**                               | **Set**                                |
|---------------------------|----------------------------------------|----------------------------------------|
| **Ordering**               | List preserves the **insertion order** of elements. | Set does not guarantee any specific order (although some implementations like `LinkedHashSet` preserve order). |
| **Duplicates**             | List allows **duplicates** (the same element can appear more than once). | Set does **not allow duplicates** (each element must be unique). |
| **Common Implementations** | `ArrayList`, `LinkedList`, `Vector`, etc. | `HashSet`, `LinkedHashSet`, `TreeSet`, etc. |
| **Access**                 | List supports **indexed access** (i.e., you can access elements using their index). | Set does **not support indexing**; elements are accessed via iteration. |
| **Performance**            | `ArrayList` provides O(1) time complexity for access but O(n) for insertions/deletions in the middle. | `HashSet` typically provides O(1) time complexity for insertion, deletion, and searching. |
| **Use Case**               | Used when the order of elements matters or when duplicate values are needed. | Used when uniqueness of elements is required and order doesn’t matter. |

**Example:**
```java
// List example (allows duplicates and preserves order)
List<String> list = new ArrayList<>();
list.add("Apple");
list.add("Banana");
list.add("Apple");  // Duplicate allowed
System.out.println(list);  // Output: [Apple, Banana, Apple]

// Set example (no duplicates and no guaranteed order)
Set<String> set = new HashSet<>();
set.add("Apple");
set.add("Banana");
set.add("Apple");  // Duplicate will be ignored
System.out.println(set);  // Output: [Apple, Banana] (order not guaranteed)
```

---

### Summary:
- **`Vector` vs. `ArrayList`**: `Vector` is synchronized and thread-safe, while `ArrayList` is not. `Vector` also grows by doubling the size, while `ArrayList` grows by 50%.
- **`Collection` vs. `Collections`**: `Collection` is an interface defining basic collection operations, while `Collections` is a utility class providing static methods for collection operations like sorting and searching.
- **LinkedList vs. ArrayList**: `LinkedList` is better for frequent insertions and deletions, especially at the beginning or middle of the list. `ArrayList` is better for random access.
- **List vs. Set**: `List` preserves insertion order and allows duplicates, while `Set` does not allow duplicates and doesn’t guarantee order.

### 160. **What are the differences between a HashSet and TreeSet collection in Java?**

`HashSet` and `TreeSet` are both implementations of the `Set` interface, but they differ in several aspects:

| **Property**              | **HashSet**                               | **TreeSet**                              |
|---------------------------|-------------------------------------------|------------------------------------------|
| **Ordering**               | `HashSet` does not guarantee any specific order of elements. | `TreeSet` stores elements in a **sorted order** (natural ordering or according to a specified comparator). |
| **Performance**            | `HashSet` offers O(1) time complexity for basic operations like add, remove, and contains. | `TreeSet` offers O(log n) time complexity for these operations due to the underlying **Red-Black Tree** structure. |
| **Null Elements**          | `HashSet` allows **one null element**.    | `TreeSet` **does not allow null elements** because it needs to compare them using the comparator or natural ordering. |
| **Implementation**         | `HashSet` is backed by a **hash table**.   | `TreeSet` is backed by a **TreeMap** (which implements `NavigableSet`). |
| **Use Case**               | Use `HashSet` when the order of elements is not important, and you need fast performance for insertions and lookups. | Use `TreeSet` when you need elements sorted in a natural order or a custom order. |

**Example:**
```java
HashSet<Integer> hashSet = new HashSet<>();
hashSet.add(5);
hashSet.add(2);
hashSet.add(8);
System.out.println(hashSet);  // Output: [5, 2, 8] (order is not guaranteed)

TreeSet<Integer> treeSet = new TreeSet<>();
treeSet.add(5);
treeSet.add(2);
treeSet.add(8);
System.out.println(treeSet);  // Output: [2, 5, 8] (sorted order)
```

---

### 161. **In Java, how will you decide when to use a List, Set, or a Map collection?**

The choice between `List`, `Set`, or `Map` depends on the requirements of your data structure and operations:

- **Use a `List`** when:
  - The order of elements matters, and you need **indexed access**.
  - You may have **duplicate elements**.
  - Operations like adding, removing, or accessing elements by index are needed.
  - Common implementations: `ArrayList`, `LinkedList`.

- **Use a `Set`** when:
  - The order of elements does **not matter** (unless using `LinkedHashSet` or `TreeSet`).
  - You need to ensure that **elements are unique** (no duplicates).
  - Common implementations: `HashSet`, `LinkedHashSet`, `TreeSet`.

- **Use a `Map`** when:
  - You need a **key-value pair** mapping.
  - You need fast retrieval and insertion of values based on keys.
  - A key can map to only one value, but the same value can be mapped to multiple keys.
  - Common implementations: `HashMap`, `TreeMap`, `LinkedHashMap`.

**Summary:**
- **List**: Use when order matters and duplicates are allowed.
- **Set**: Use when uniqueness of elements is required, and order doesn't matter.
- **Map**: Use when you need a key-value association, for fast lookups based on keys.

---

### 162. **What are the differences between a HashMap and a Hashtable in Java?**

`HashMap` and `Hashtable` are both key-value pair collections in Java, but they differ in several important ways:

| **Property**              | **HashMap**                               | **Hashtable**                            |
|---------------------------|-------------------------------------------|------------------------------------------|
| **Thread-safety**          | `HashMap` is **not synchronized** and thus not thread-safe by default. | `Hashtable` is **synchronized**, making it thread-safe (but slower). |
| **Null Keys and Values**   | `HashMap` allows **one null key** and **multiple null values**. | `Hashtable` **does not allow null keys or values**. |
| **Performance**            | `HashMap` is faster due to lack of synchronization. | `Hashtable` is slower due to synchronization overhead. |
| **Introduced**             | `HashMap` was introduced in Java 1.2 as part of the **Java Collections Framework**. | `Hashtable` is part of the **legacy collection classes**, introduced in earlier versions of Java. |
| **Use Case**               | Use `HashMap` when you do not need thread-safety, and performance is important. | Use `Hashtable` when thread-safety is required (though `ConcurrentHashMap` is usually preferred). |

**Example:**
```java
// HashMap example
HashMap<String, Integer> map = new HashMap<>();
map.put("One", 1);
map.put("Two", 2);
map.put(null, 3); // Allowed
map.put("Three", null); // Allowed

// Hashtable example
Hashtable<String, Integer> table = new Hashtable<>();
table.put("One", 1);
table.put("Two", 2);
// table.put(null, 3); // Throws NullPointerException
// table.put("Three", null); // Throws NullPointerException
```

---

### 163. **What are the differences between a HashMap and a TreeMap?**

`HashMap` and `TreeMap` are both implementations of the `Map` interface, but they differ in several ways:

| **Property**              | **HashMap**                               | **TreeMap**                               |
|---------------------------|-------------------------------------------|-------------------------------------------|
| **Ordering**               | `HashMap` does not guarantee any specific order. | `TreeMap` stores keys in **sorted order** (natural ordering or using a comparator). |
| **Performance**            | `HashMap` provides O(1) time complexity for basic operations (insertion, deletion, lookup). | `TreeMap` provides O(log n) time complexity due to the underlying **Red-Black Tree** structure. |
| **Null Keys and Values**   | `HashMap` allows **one null key** and **multiple null values**. | `TreeMap` **does not allow null keys** (because it requires a comparator to order keys). |
| **Implementation**         | `HashMap` is backed by a **hash table**.   | `TreeMap` is backed by a **Red-Black Tree**. |
| **Use Case**               | Use `HashMap` when you do not need order and need faster performance for insertion, deletion, and lookup. | Use `TreeMap` when you need **sorted order** or a custom sorting of keys. |

**Example:**
```java
// HashMap example (no order)
HashMap<Integer, String> hashMap = new HashMap<>();
hashMap.put(3, "Three");
hashMap.put(1, "One");
hashMap.put(2, "Two");
System.out.println(hashMap);  // Output: {1=One, 2=Two, 3=Three} (order not guaranteed)

// TreeMap example (sorted order)
TreeMap<Integer, String> treeMap = new TreeMap<>();
treeMap.put(3, "Three");
treeMap.put(1, "One");
treeMap.put(2, "Two");
System.out.println(treeMap);  // Output: {1=One, 2=Two, 3=Three} (sorted order)
```

---

### 164. **What are the differences between Comparable and Comparator?**

Both `Comparable` and `Comparator` are used to compare objects in Java, but they differ in how they are used and their implementation:

| **Property**              | **Comparable**                                | **Comparator**                             |
|---------------------------|-----------------------------------------------|--------------------------------------------|
| **Method to Implement**    | Implements `compareTo()` method.              | Implements `compare()` method.             |
| **Interface Location**     | `Comparable` is an interface implemented by the class whose objects need to be compared. | `Comparator` is a separate interface used to compare objects of **any class**. |
| **Purpose**                | Used when a class **naturally** orders its objects, i.e., **in-place comparison**. | Used when we want to define a custom ordering **external to the class**. |
| **Flexibility**            | Less flexible because it modifies the class that is being compared (modifies the object itself). | More flexible because you can create multiple comparators for the same class. |
| **Use Case**               | Use `Comparable` when the class has a natural ordering. | Use `Comparator` when you need multiple ways to compare objects or don't want to modify the class. |

**Example:**
```java
// Comparable example
class Person implements Comparable<Person> {
    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Person other) {
        return this.age - other.age;  // Sorting by age
    }
}

// Comparator example
class PersonComparator implements Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2) {
        return p1.name.compareTo(p2.name);  // Sorting by name
    }
}
```
### 165. **In Java, what is the purpose of a Properties file?**

A **Properties file** in Java is used to store **configuration data** in key-value pairs. It is commonly used to maintain project configuration settings such as database credentials, application settings, or environment variables. These files are text files with a `.properties` extension and can be easily read and modified.

- **Usage**: The properties file allows external configuration without modifying the source code.
- **Format**: A properties file consists of key-value pairs, where the key and value are both strings.
- **Example**:
  ```properties
  username=admin
  password=secret
  host=localhost
  ```

You can load a properties file in Java using the `Properties` class:
```java
Properties properties = new Properties();
InputStream input = new FileInputStream("config.properties");
properties.load(input);

String username = properties.getProperty("username");
String password = properties.getProperty("password");
```

---

### 166. **What is the reason for overriding the `equals()` method?**

The `equals()` method is overridden to compare the **logical equality** of two objects. By default, the `equals()` method in the `Object` class checks for **reference equality**, meaning it checks whether two references point to the exact same object in memory. This is often not the desired behavior when comparing objects with meaningful data.

- **Purpose**: Override `equals()` to check if two objects are "equal" in terms of their content, not just their memory address.
- **When to Override**: If you want to define custom criteria for equality based on the object's attributes (e.g., two `Person` objects should be considered equal if they have the same name and age).
  
Example:
```java
class Person {
    String name;
    int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;  // Check if same reference
        if (obj == null || getClass() != obj.getClass()) return false;
        Person person = (Person) obj;
        return age == person.age && name.equals(person.name);
    }
}
```

---

### 167. **How does `hashCode()` method work in Java?**

The `hashCode()` method in Java returns an integer value that represents the **memory address** of the object or a **computed hash value** based on the object's data. It is used primarily for the efficient searching of objects in hash-based collections like `HashMap` or `HashSet`.

- **Purpose**: `hashCode()` is used to quickly locate the bucket where the object is stored in a hash-based collection.
- **Contract**:
  - **Consistency**: The hash code should remain constant as long as the object is not modified.
  - **Equality**: If two objects are equal (as per the `equals()` method), they must have the same hash code.
  
The `hashCode()` method must be consistent with `equals()`. If `equals()` returns `true` for two objects, their `hashCode()` must return the same value.

Example:
```java
class Person {
    String name;
    int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person person = (Person) obj;
        return age == person.age && name.equals(person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);  // Combine name and age to generate hash code
    }
}
```

---

### 168. **Is it a good idea to use Generics in collections?**

Yes, it is a good idea to use **Generics** in collections for the following reasons:

1. **Type Safety**: Generics allow you to define the type of elements stored in a collection. This ensures that only the specified type can be added to the collection, preventing `ClassCastException` at runtime.
   - Without generics, you might add any type of object to a collection and later face casting errors.
  
   Example without generics:
   ```java
   List list = new ArrayList();
   list.add("String");
   list.add(100);  // No compile-time check, runtime error possible
   ```

   Example with generics:
   ```java
   List<String> list = new ArrayList<>();
   list.add("String");
   // list.add(100);  // Compile-time error: incompatible types
   ```

2. **Compile-time Checking**: Generics allow type errors to be caught at compile-time rather than runtime. This helps in writing safer and more predictable code.
   
3. **Code Reusability**: Generics allow you to write more generic code that can work with different types without duplicating logic.

4. **Eliminating Type Casting**: With generics, there is no need for casting elements when retrieving them from a collection, making the code cleaner and more maintainable.

5. **Improved Readability**: Using generics makes your code more readable and expressive. It’s clear what type of data is being handled in the collection.

### Example:
```java
List<String> stringList = new ArrayList<>();
stringList.add("Hello");
String str = stringList.get(0);  // No need for casting
```

**Conclusion**: Using generics in collections is highly recommended because it ensures type safety, reduces the need for casting, and leads to better readability and maintainability of your code.
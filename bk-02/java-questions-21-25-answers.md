## Control Structures

### 21. What are the different types of loops in Java?

Java provides **4 types of loops**:

#### 1. **Traditional for loop**
```java
for (int i = 0; i < 10; i++) {
    System.out.println(i);
}
```
- **Use when:** You know the number of iterations
- **Components:** initialization, condition, increment/decrement

#### 2. **Enhanced for loop (for-each)**
```java
int[] array = {1, 2, 3, 4, 5};
for (int element : array) {
    System.out.println(element);
}
```
- **Use when:** Iterating over collections/arrays
- **Advantage:** Cleaner syntax, no index management

#### 3. **While loop (pre-test)**
```java
int i = 0;
while (i < 10) {
    System.out.println(i);
    i++;
}
```
- **Use when:** Number of iterations unknown
- **Condition:** Checked before each iteration

#### 4. **Do-while loop (post-test)**
```java
int i = 0;
do {
    System.out.println(i);
    i++;
} while (i < 10);
```
- **Use when:** Loop must execute at least once
- **Condition:** Checked after each iteration

### 22. What is the difference between break and continue?

| Statement | Purpose | Effect | Usage |
|-----------|---------|--------|-------|
| `break` | Exit loop completely | Terminates entire loop | Exit when condition met |
| `continue` | Skip current iteration | Jumps to next iteration | Skip specific cases |

#### **break example:**
```java
for (int i = 0; i < 10; i++) {
    if (i == 5) {
        break;  // Exits loop completely
    }
    System.out.println(i);  // Prints: 0, 1, 2, 3, 4
}
```

#### **continue example:**
```java
for (int i = 0; i < 10; i++) {
    if (i == 5) {
        continue;  // Skips only i=5
    }
    System.out.println(i);  // Prints: 0, 1, 2, 3, 4, 6, 7, 8, 9
}
```

#### **Labeled break/continue:**
```java
outer: for (int i = 0; i < 3; i++) {
    for (int j = 0; j < 3; j++) {
        if (i == 1 && j == 1) {
            break outer;  // Breaks outer loop
        }
        System.out.println(i + "," + j);
    }
}
```

### 23. What is enhanced for loop (for-each)?

**Enhanced for loop** simplifies iteration over arrays and collections.

#### **Syntax:**
```java
for (dataType variable : collection/array) {
    // Use variable
}
```

#### **Examples:**

**With Arrays:**
```java
int[] numbers = {1, 2, 3, 4, 5};
for (int num : numbers) {
    System.out.println(num);
}
```

**With Collections:**
```java
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
for (String name : names) {
    System.out.println(name);
}
```

#### **Advantages:**
- **Cleaner syntax** - no index management
- **Safer** - eliminates ArrayIndexOutOfBoundsException
- **More readable** - intent is clearer
- **Less error-prone** - no off-by-one errors

#### **Limitations:**
- **No index access** - can't get current position
- **Read-only** - can't modify array/collection structure
- **Single direction** - only forward iteration
- **No partial iteration** - can't start from middle

#### **When to use traditional vs enhanced:**
```java
// Use enhanced for - simple iteration
for (String item : list) {
    System.out.println(item);
}

// Use traditional for - need index
for (int i = 0; i < list.size(); i++) {
    System.out.println(i + ": " + list.get(i));
}
```

### 24. When would you use switch vs if-else?

#### **Use Switch When:**
- **Single variable** with multiple discrete values
- **Equality comparisons** only
- **Better readability** for many conditions
- **Performance matters** (compiler optimization)

#### **Use If-Else When:**
- **Complex conditions** (ranges, multiple variables)
- **Boolean expressions**
- **Different comparison operators** (>, <, >=, etc.)
- **Object method calls** in conditions

#### **Switch Example (Good Use Case):**
```java
// Menu selection - perfect for switch
int choice = scanner.nextInt();
switch (choice) {
    case 1:
        addStudent();
        break;
    case 2:
        deleteStudent();
        break;
    case 3:
        viewStudents();
        break;
    default:
        System.out.println("Invalid choice");
}
```

#### **If-Else Example (Good Use Case):**
```java
// Complex conditions - better with if-else
int score = 85;
if (score >= 90) {
    grade = "A";
} else if (score >= 80) {
    grade = "B";
} else if (score >= 70) {
    grade = "C";
} else {
    grade = "F";
}
```

#### **Performance Comparison:**
- **Switch:** O(1) with jump table for many cases
- **If-Else:** O(n) linear search through conditions

#### **Readability Comparison:**
```java
// Switch - cleaner for discrete values
switch (dayOfWeek) {
    case MONDAY: return "Start of work week";
    case FRIDAY: return "TGIF!";
    case SATURDAY: case SUNDAY: return "Weekend!";
}

// If-else - better for ranges
if (age < 13) return "Child";
else if (age < 20) return "Teenager";
else if (age < 60) return "Adult";
else return "Senior";
```

### 25. What are the rules for switch statement in Java?

#### **Expression Type Rules:**
Switch expression must be one of:
- **Integral types:** `byte`, `short`, `int`, `char`
- **Wrapper classes:** `Byte`, `Short`, `Integer`, `Character`
- **String** (Java 7+)
- **Enum** types

```java
// Valid switch expressions
switch (intValue) { }      // int
switch (stringValue) { }   // String
switch (enumValue) { }     // Enum
switch (charValue) { }     // char

// Invalid switch expressions
// switch (floatValue) { }    // float - NOT allowed
// switch (booleanValue) { } // boolean - NOT allowed
```

#### **Case Label Rules:**

**1. Must be compile-time constants:**
```java
final int CONSTANT = 10;
int variable = 20;

switch (x) {
    case 5:         // OK - literal
    case CONSTANT:  // OK - final variable
    // case variable:  // ERROR - not constant
}
```

**2. Must be unique:**
```java
switch (x) {
    case 1:
        // code
        break;
    // case 1:  // ERROR - duplicate case
}
```

**3. Must be compatible with switch expression type:**
```java
byte b = 10;
switch (b) {
    case 100:   // OK - within byte range
    // case 200:   // ERROR - outside byte range (-128 to 127)
}
```

#### **Break Statement Rules:**
```java
switch (x) {
    case 1:
        System.out.println("One");
        break;  // Prevents fall-through
    case 2:
        System.out.println("Two");
        // No break - falls through to case 3
    case 3:
        System.out.println("Two or Three");
        break;
    default:
        System.out.println("Other");
}
```

#### **Default Case Rules:**
- **Optional** but recommended
- **Can appear anywhere** (usually at end)
- **Only one default** allowed per switch

```java
switch (x) {
    default:        // Can be first
        System.out.println("Default");
        break;
    case 1:
        System.out.println("One");
        break;
}
```

#### **Modern Switch Features (Java 12+):**

**Switch Expressions:**
```java
String result = switch (day) {
    case MONDAY, FRIDAY, SUNDAY -> "6";
    case TUESDAY -> "7";
    case THURSDAY, SATURDAY -> "8";
    case WEDNESDAY -> "9";
};
```

**Yield Statement:**
```java
int result = switch (x) {
    case 1 -> {
        System.out.println("Processing 1");
        yield 10;
    }
    case 2 -> 20;
    default -> 0;
};
```

#### **Best Practices:**
1. **Always include default** case
2. **Use break** to prevent fall-through (unless intentional)
3. **Group related cases** together
4. **Keep cases simple** - complex logic in separate methods
5. **Consider enums** for type safety



# Java Stream API - Interview Practice Questions

import java.util.Arrays;
import java.util.List;

public class StreamExamples {
    public static void main(String[] args) {
        
        SumofAllElements();

    }



    
}

## 1. Sum of All Elements
**Question:** Write a program to find the sum of all elements in a list using Java Stream API
public void SumofAllElements() {
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
    int sum = numbers.stream()
                    .mapToInt(Integer::intValue)
                    .sum();
    System.out.println("Sum: " + sum);
}

**Output:** `Sum: 15`


## 2. Find Maximum Element
**Question:** Given a list of integers, write a program to find and print the maximum element using Java Stream API

```java
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(2, 4, 8, 6, 10);
        int max = numbers.stream()
                        .mapToInt(Integer::intValue)
                        .max()
                        .orElseThrow();
        System.out.println("Max element: " + max);
    }
}
```
**Output:** `Max element: 10`

---

## 3. Filter Even Numbers
**Question:** Write a program to filter out all the even numbers from a list using Java Stream API

```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> evenNumbers = numbers.stream()
                                            .filter(num -> num % 2 == 0)
                                            .collect(Collectors.toList());
        System.out.println("Even numbers: " + evenNumbers);
    }
}
```
**Output:** `Even numbers: [2, 4]`

---

## 4. Count Strings Containing Character
**Question:** Given a list of strings, write a program to count the number of strings containing a specific character 'a' using Java Stream API

```java
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("apple", "banana", "orange", "grape");
        char searchChar = 'a';
        long count = strings.stream()
                            .filter(str -> str.contains(String.valueOf(searchChar)))
                            .count();
        System.out.println("Number of strings containing '" + searchChar + "': " + count);
    }
}
```
**Output:** `Number of strings containing 'a': 4`

---

## 5. Convert to Uppercase
**Question:** Write a program to convert a list of strings to uppercase using Java Stream API

```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("apple", "banana", "orange", "grape");
        List<String> upperCaseStrings = strings.stream()
                                                .map(String::toUpperCase)
                                                .collect(Collectors.toList());
        System.out.println("Uppercase strings: " + upperCaseStrings);
    }
}
```
**Output:** `Uppercase strings: [APPLE, BANANA, ORANGE, GRAPE]`

---

## 6. Calculate Average
**Question:** Given a list of integers, write a program to calculate the average of all the numbers using Java Stream API

```java
import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        OptionalDouble average = numbers.stream()
                                        .mapToDouble(Integer::doubleValue)
                                        .average();
        System.out.println("Average: " + (average.isPresent() ? average.getAsDouble() : "N/A"));
    }
}
```
**Output:** `Average: 3.0`

---

## 7. Sort Strings Alphabetically
**Question:** Write a program to sort a list of strings in alphabetical order using Java Stream API

```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("banana", "orange", "apple", "grape");
        List<String> sortedStrings = strings.stream()
                                            .sorted()
                                            .collect(Collectors.toList());
        System.out.println("Sorted strings: " + sortedStrings);
    }
}
```
**Output:** `Sorted strings: [apple, banana, grape, orange]`

---

## 8. Concatenate Strings
**Question:** Given a list of strings, write a program to concatenate all the strings using Java Stream API

```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("apple", "banana", "orange", "grape");
        String concatenatedString = strings.stream()
                                            .collect(Collectors.joining());
        System.out.println("Concatenated string: " + concatenatedString);
    }
}
```
**Output:** `Concatenated string: applebananaorangegrape`

---

## 9. Find Longest String
**Question:** Write a program to find the longest string in a list of strings using Java Stream API

```java
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("apple", "banana", "orange", "grape");
        Optional<String> longestString = strings.stream()
                                                .max((str1, str2) -> str1.length() - str2.length());
        System.out.println("Longest string: " + (longestString.isPresent() ? longestString.get() : "N/A"));
    }
}
```
**Output:** `Longest string: banana`

---

## 10. Find Second Largest Number
**Question:** Given a list of integers, write a program to find and print the second largest number using Java Stream API

```java
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(4, 2, 8, 6, 10);
        Optional<Integer> secondLargest = numbers.stream()
                                                .sorted((num1, num2) -> num2 - num1)
                                                .skip(1)
                                                .findFirst();
        System.out.println("Second largest number: " + (secondLargest.isPresent() ? secondLargest.get() : "N/A"));
    }
}
```
**Output:** `Second largest number: 8`

---

## 11. Remove Duplicates
**Question:** Write a program to remove all the duplicate elements from a list using Java Stream API

```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 2, 5, 6, 3, 7, 8, 1);
        List<Integer> uniqueNumbers = numbers.stream()
                                            .distinct()
                                            .collect(Collectors.toList());
        System.out.println("Original list: " + numbers);
        System.out.println("List with duplicates removed: " + uniqueNumbers);
    }
}
```
**Output:**
```
Original list: [1, 2, 3, 4, 2, 5, 6, 3, 7, 8, 1]
List with duplicates removed: [1, 2, 3, 4, 5, 6, 7, 8]
```

---

## 12. Find Shortest String
**Question:** Given a list of strings, write a program to find and print the shortest string using Java Stream API

```java
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("apple", "banana", "kiwi", "orange", "pear");
        String shortestString = strings.stream()
                                      .min(Comparator.comparingInt(String::length))
                                      .orElse(null);
        System.out.println("Shortest string: " + shortestString);
    }
}
```
**Output:** `Shortest string: kiwi`

---

## 13. Convert to Squares
**Question:** Write a program to convert a list of integers to a list of their squares using Java Stream API

```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> squares = numbers.stream()
                                      .map(n -> n * n)
                                      .collect(Collectors.toList());
        System.out.println("Original list: " + numbers);
        System.out.println("List of squares: " + squares);
    }
}
```
**Output:**
```
Original list: [1, 2, 3, 4, 5]
List of squares: [1, 4, 9, 16, 25]
```

---

## 14. Filter Strings by Prefix
**Question:** Given a list of strings, write a program to find and print the strings starting with a specific prefix 'a' using Java Stream API

```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("apple", "banana", "kiwi", "orange", "pear");
        String prefix = "a";
        List<String> stringsWithPrefix = strings.stream()
                                               .filter(s -> s.startsWith(prefix))
                                               .collect(Collectors.toList());
        System.out.println("Strings starting with '" + prefix + "': " + stringsWithPrefix);
    }
}
```
**Output:** `Strings starting with 'a': [apple]`

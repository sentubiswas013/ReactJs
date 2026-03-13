# 1. Filter Even Numbers

```java
List<Integer> numbers = Arrays.asList(1,2,3,4,5,6);

List<Integer> even = numbers.stream()
        .filter(n -> n % 2 == 0)
        .toList();

System.out.println(even);
```

---

# 2. Find Maximum Element

```java
int max = numbers.stream()
        .max(Integer::compareTo)
        .orElseThrow();
```

---

# 3. Sort List in Descending Order

```java
List<Integer> sorted = numbers.stream()
        .sorted(Comparator.reverseOrder())
        .toList();
```

---

# 4. Count Strings with Specific Prefix

```java
List<String> names = Arrays.asList("Alice","Bob","Annie","Alex");

long count = names.stream()
        .filter(name -> name.startsWith("A"))
        .count();
```

---

# 5. Find First Non-Repeated Character

```java
String input = "swiss";

Character result = input.chars()
        .mapToObj(c -> (char)c)
        .filter(c -> input.indexOf(c) == input.lastIndexOf(c))
        .findFirst()
        .orElse(null);
```

---

# 6. Convert List to Uppercase

```java
List<String> words = Arrays.asList("java","stream","api");

List<String> upper = words.stream()
        .map(String::toUpperCase)
        .toList();
```

---

# 7. Sum of Numbers

```java
int sum = numbers.stream()
        .mapToInt(Integer::intValue)
        .sum();
```

---

# 8. Check if Any String Contains Word

```java
boolean result = words.stream()
        .anyMatch(s -> s.contains("api"));
```

---

# 9. Find Duplicate Elements

```java
List<Integer> nums = Arrays.asList(1,2,3,4,2,5,1);

Set<Integer> seen = new HashSet<>();

Set<Integer> duplicates = nums.stream()
        .filter(n -> !seen.add(n))
        .collect(Collectors.toSet());
```

---

# 10. Find Longest String

```java
String longest = words.stream()
        .max(Comparator.comparingInt(String::length))
        .orElse(null);
```

---

# 11. Find Common Elements Between Two Lists

```java
List<Integer> a = Arrays.asList(1,2,3,4);
List<Integer> b = Arrays.asList(3,4,5,6);

List<Integer> common = a.stream()
        .filter(b::contains)
        .toList();
```

---

# 12. Find Top N Elements

```java
List<Integer> top3 = numbers.stream()
        .sorted(Comparator.reverseOrder())
        .limit(3)
        .toList();
```

---

# 13. Count Frequency of Characters

```java
String str = "success";

Map<Character, Long> freq = str.chars()
        .mapToObj(c -> (char)c)
        .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
```

---

# 14. Flatten List of Lists

```java
List<List<Integer>> lists = Arrays.asList(
        Arrays.asList(1,2),
        Arrays.asList(3,4),
        Arrays.asList(5,6)
);

List<Integer> flat = lists.stream()
        .flatMap(List::stream)
        .toList();
```

---

# 15. Partition Even and Odd Numbers

```java
Map<Boolean, List<Integer>> partition =
        numbers.stream()
        .collect(Collectors.partitioningBy(n -> n % 2 == 0));
```

---

# 16. Find Nth Largest Element

```java
int thirdLargest = numbers.stream()
        .sorted(Comparator.reverseOrder())
        .skip(2)
        .findFirst()
        .orElseThrow();
```

---

# 17. Remove Duplicates

```java
List<Integer> unique = nums.stream()
        .distinct()
        .toList();
```

---

# 18. Join Strings

```java
String joined = words.stream()
        .collect(Collectors.joining(", "));
```

---

# 19. Remove Null Values

```java
List<String> clean = words.stream()
        .filter(Objects::nonNull)
        .toList();
```

---

# 20. Calculate Average

```java
double avg = numbers.stream()
        .mapToInt(Integer::intValue)
        .average()
        .orElse(0);
```

---

# 21. Convert List to Map

```java
Map<String, Integer> map = words.stream()
        .collect(Collectors.toMap(w -> w, String::length));
```

---

# 22. Find Palindromes

```java
List<String> palindromes = words.stream()
        .filter(w -> w.equals(new StringBuilder(w).reverse().toString()))
        .toList();
```

---

# 23. Reverse Each String

```java
List<String> reversed = words.stream()
        .map(w -> new StringBuilder(w).reverse().toString())
        .toList();
```

---

# 24. Group Strings by Length

```java
Map<Integer, List<String>> grouped =
        words.stream()
        .collect(Collectors.groupingBy(String::length));
```

---

# 25. Group by First Character

```java
Map<Character, List<String>> map =
        words.stream()
        .collect(Collectors.groupingBy(w -> w.charAt(0)));
```

---

# 26. Find Highest Salary by Department

```java
Map<String, Employee> highest =
        employees.stream()
        .collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.collectingAndThen(
                        Collectors.maxBy(Comparator.comparing(Employee::getSalary)),
                        Optional::get
                )
        ));
```

---

# 27. Generate Fibonacci Sequence

```java
List<Integer> fib = Stream.iterate(new int[]{0,1},
        arr -> new int[]{arr[1], arr[0] + arr[1]})
        .limit(10)
        .map(arr -> arr[0])
        .toList();
```

---

# 28. Find Pair With Given Sum

```java
int target = 6;

List<List<Integer>> pairs =
        numbers.stream()
        .flatMap(a -> numbers.stream()
                .filter(b -> a + b == target && a < b)
                .map(b -> Arrays.asList(a,b)))
        .toList();
```

---

# 29. Detect Anagrams

```java
List<String> words = Arrays.asList("listen","silent","enlist","google");

Map<String, List<String>> anagrams =
        words.stream()
        .collect(Collectors.groupingBy(
                w -> w.chars()
                        .sorted()
                        .mapToObj(c -> String.valueOf((char)c))
                        .collect(Collectors.joining())
        ));
```

---

# 30. Sort Elements by Frequency

```java
List<Integer> sorted =
        nums.stream()
        .sorted(Comparator.comparingInt(n -> -Collections.frequency(nums, n)))
        .distinct()
        .toList();
```

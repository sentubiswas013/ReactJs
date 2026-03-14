# 1. Stream Creation Methods

| Method              | When to Use                   | Example                 |
| ------------------- | ----------------------------- | ----------------------- |
| `stream()`          | Create stream from collection | `list.stream()`         |
| `parallelStream()`  | Process data in parallel      | `list.parallelStream()` |
| `Stream.of()`       | Create stream from values     | `Stream.of(1,2,3)`      |
| `Arrays.stream()`   | Stream from array             | `Arrays.stream(arr)`    |
| `Stream.iterate()`  | Generate infinite sequence    | Fibonacci               |
| `Stream.generate()` | Generate values dynamically   | random numbers          |
| `IntStream.range()` | numeric range                 | loops                   |

Example

```java
Stream<Integer> s = Stream.of(1,2,3,4);
```

---

# 2. Filtering Methods

| Method        | When to Use                  |
| ------------- | ---------------------------- |
| `filter()`    | Remove unwanted elements     |
| `distinct()`  | Remove duplicates            |
| `limit()`     | Restrict to first N elements |
| `skip()`      | Ignore first N elements      |
| `takeWhile()` | Process until condition true |
| `dropWhile()` | Skip until condition false   |

Example

```java
numbers.stream()
       .filter(n -> n % 2 == 0)
       .distinct()
       .limit(5);
```

---

# 3. Mapping / Transformation Methods

| Method           | When to Use                |
| ---------------- | -------------------------- |
| `map()`          | Transform each element     |
| `mapToInt()`     | Convert to IntStream       |
| `mapToLong()`    | Convert to LongStream      |
| `mapToDouble()`  | Convert to DoubleStream    |
| `flatMap()`      | Flatten nested collections |
| `flatMapToInt()` | Flatten to IntStream       |

Example

```java
names.stream()
     .map(String::toUpperCase);
```

Example (Nested Lists)

```java
listOfLists.stream()
           .flatMap(List::stream);
```

---

# 4. Sorting Methods

| Method               | When to Use    |
| -------------------- | -------------- |
| `sorted()`           | Sort elements  |
| `sorted(Comparator)` | Custom sorting |

Example

```java
numbers.stream()
       .sorted()
```

Descending

```java
numbers.stream()
       .sorted(Comparator.reverseOrder())
```

---

# 5. Debugging / Inspection Methods

| Method   | When to Use               |
| -------- | ------------------------- |
| `peek()` | Debugging inside pipeline |

Example

```java
numbers.stream()
       .peek(System.out::println)
```

Used mostly for **logging during stream processing**.

---

# 6. Terminal Operations (End of Stream)

These **trigger execution**.

| Method             | When to Use                   |
| ------------------ | ----------------------------- |
| `forEach()`        | Iterate elements              |
| `forEachOrdered()` | Ordered iteration in parallel |
| `toList()`         | Convert to list               |
| `collect()`        | Convert to List, Map, Set     |
| `count()`          | Count elements                |

Example

```java
numbers.stream().forEach(System.out::println);
```

---

# 7. Matching / Checking Methods

| Method        | When to Use                      |
| ------------- | -------------------------------- |
| `anyMatch()`  | If any element matches condition |
| `allMatch()`  | If all elements match            |
| `noneMatch()` | If none match                    |

Example

```java
boolean result =
numbers.stream()
       .anyMatch(n -> n > 10);
```

---

# 8. Finding Methods

| Method        | When to Use                        |
| ------------- | ---------------------------------- |
| `findFirst()` | Get first element                  |
| `findAny()`   | Get any element (parallel streams) |

Example

```java
Optional<Integer> n =
numbers.stream()
       .findFirst();
```

---

# 9. Reduction / Aggregation Methods

| Method      | When to Use    |
| ----------- | -------------- |
| `reduce()`  | Combine values |
| `sum()`     | Sum numbers    |
| `average()` | Average        |
| `min()`     | Minimum        |
| `max()`     | Maximum        |

Example

```java
int sum = numbers.stream()
                 .reduce(0, Integer::sum);
```

---

# 10. Collectors (Used with collect())

Very important for interviews.

| Collector                        | When to Use         |
| -------------------------------- | ------------------- |
| `Collectors.toList()`            | Convert to list     |
| `Collectors.toSet()`             | Convert to set      |
| `Collectors.toMap()`             | Convert to map      |
| `Collectors.joining()`           | Join strings        |
| `Collectors.groupingBy()`        | Group elements      |
| `Collectors.partitioningBy()`    | Split true/false    |
| `Collectors.counting()`          | Count elements      |
| `Collectors.summingInt()`        | Sum values          |
| `Collectors.mapping()`           | Map inside grouping |
| `Collectors.collectingAndThen()` | Post processing     |

Example

```java
Map<Integer,List<String>> map =
words.stream()
     .collect(Collectors.groupingBy(String::length));
```

---

# 11. Numeric Stream Methods

For **IntStream, LongStream, DoubleStream**

| Method          | When to Use     |
| --------------- | --------------- |
| `range()`       | range numbers   |
| `rangeClosed()` | inclusive range |
| `sum()`         | sum numbers     |
| `average()`     | average         |
| `max()`         | maximum         |
| `min()`         | minimum         |

Example

```java
IntStream.range(1,5).sum();
```

---

# 12. Parallel Processing Methods

| Method             | When to Use           |
| ------------------ | --------------------- |
| `parallel()`       | convert to parallel   |
| `sequential()`     | convert to sequential |
| `parallelStream()` | parallel stream       |

Example

```java
numbers.parallelStream()
       .map(n -> n*n);
```

---

# 13. Stream Closing Methods

| Method      | When to Use           |
| ----------- | --------------------- |
| `close()`   | manually close stream |
| `onClose()` | execute close handler |

Rarely used.

---

# Quick Summary (Most Used 15)

These **15 methods cover 90% of real work**:

```
stream()
filter()
map()
flatMap()
sorted()
distinct()
limit()
skip()
peek()
collect()
forEach()
findFirst()
anyMatch()
reduce()
count()
```

---

# Interview Tip (Very Important)

Interviewers most frequently ask about:

1. `map()` vs `flatMap()`
2. `findFirst()` vs `findAny()`
3. `collect()` vs `reduce()`
4. `forEach()` vs `peek()`
5. `groupingBy()` vs `partitioningBy()`


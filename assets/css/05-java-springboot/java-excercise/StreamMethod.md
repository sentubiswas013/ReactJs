# 1. String Methods (Commonly Used with Streams)

| Method               | When to Use                        | Example                                                           |
| -------------------- | ---------------------------------- | ----------------------------------------------------------------- |
| `startsWith()`       | Check if string starts with prefix | `names.stream().filter(n -> n.startsWith("A")).toList()`          |
| `endsWith()`         | Check if string ends with suffix   | `names.stream().filter(n -> n.endsWith("n")).toList()`            |
| `contains()`         | Check if string contains substring | `names.stream().filter(n -> n.contains("av")).toList()`           |
| `equals()`           | Exact string comparison            | `names.stream().filter(n -> n.equals("Java")).toList()`           |
| `equalsIgnoreCase()` | Compare strings ignoring case      | `names.stream().filter(n -> n.equalsIgnoreCase("java")).toList()` |
| `length()`           | Get string length                  | `names.stream().filter(n -> n.length() > 4).toList()`             |
| `toUpperCase()`      | Convert string to uppercase        | `names.stream().map(String::toUpperCase).toList()`                |
| `toLowerCase()`      | Convert string to lowercase        | `names.stream().map(String::toLowerCase).toList()`                |
| `substring()`        | Extract part of string             | `names.stream().map(n -> n.substring(0,2)).toList()`              |
| `trim()`             | Remove spaces at start/end         | `names.stream().map(String::trim).toList()`                       |
| `isEmpty()`          | Check if string empty              | `names.stream().filter(String::isEmpty).toList()`                 |
| `charAt()`           | Get character at index             | `names.stream().map(n -> n.charAt(0)).toList()`                   |

---

# 2. Number / Numeric Stream Methods

| Method                    | When to Use                      | Example                                                  |
| ------------------------- | -------------------------------- | -------------------------------------------------------- |
| `sum()`                   | Sum numeric elements             | `numbers.stream().mapToInt(Integer::intValue).sum()`     |
| `average()`               | Calculate average                | `numbers.stream().mapToInt(Integer::intValue).average()` |
| `max()`                   | Find maximum value               | `numbers.stream().max(Integer::compareTo)`               |
| `min()`                   | Find minimum value               | `numbers.stream().min(Integer::compareTo)`               |
| `reduce()`                | Combine numbers into one result  | `numbers.stream().reduce(0,Integer::sum)`                |
| `mapToInt()`              | Convert elements to IntStream    | `list.stream().mapToInt(Integer::intValue)`              |
| `mapToLong()`             | Convert elements to LongStream   | `list.stream().mapToLong(Long::valueOf)`                 |
| `mapToDouble()`           | Convert elements to DoubleStream | `list.stream().mapToDouble(Double::valueOf)`             |
| `IntStream.range()`       | Generate number range            | `IntStream.range(1,5)`                                   |
| `IntStream.rangeClosed()` | Inclusive number range           | `IntStream.rangeClosed(1,5)`                             |

---

# 3. Array Methods

| Method            | When to Use             | Example                    |
| ----------------- | ----------------------- | -------------------------- |
| `Arrays.stream()` | Convert array to stream | `Arrays.stream(arr)`       |
| `Arrays.sort()`   | Sort array              | `Arrays.sort(arr)`         |
| `Arrays.asList()` | Convert array to list   | `Arrays.asList(arr)`       |
| `Arrays.copyOf()` | Copy array elements     | `Arrays.copyOf(arr,5)`     |
| `Arrays.equals()` | Compare two arrays      | `Arrays.equals(arr1,arr2)` |

---

# 4. Collection Stream Methods

| Method               | When to Use                    | Example                                           |
| -------------------- | ------------------------------ | ------------------------------------------------- |
| `stream()`           | Create stream from collection  | `list.stream()`                                   |
| `parallelStream()`   | Process collection in parallel | `list.parallelStream()`                           |
| `filter()`           | Remove unwanted elements       | `list.stream().filter(x -> x > 10)`               |
| `map()`              | Transform elements             | `list.stream().map(x -> x * 2)`                   |
| `flatMap()`          | Flatten nested collections     | `lists.stream().flatMap(List::stream)`            |
| `distinct()`         | Remove duplicates              | `list.stream().distinct()`                        |
| `sorted()`           | Sort elements                  | `list.stream().sorted()`                          |
| `sorted(Comparator)` | Custom sorting                 | `list.stream().sorted(Comparator.reverseOrder())` |
| `limit()`            | Limit results                  | `list.stream().limit(5)`                          |
| `skip()`             | Skip elements                  | `list.stream().skip(2)`                           |
| `peek()`             | Debug or log elements          | `list.stream().peek(System.out::println)`         |
| `forEach()`          | Iterate elements               | `list.stream().forEach(System.out::println)`      |
| `collect()`          | Convert stream to collection   | `list.stream().collect(Collectors.toList())`      |
| `toList()`           | Convert stream to List         | `list.stream().toList()`                          |
| `count()`            | Count elements                 | `list.stream().count()`                           |
| `findFirst()`        | Get first element              | `list.stream().findFirst()`                       |
| `findAny()`          | Get any element                | `list.stream().findAny()`                         |
| `anyMatch()`         | Check if any element matches   | `list.stream().anyMatch(x -> x > 10)`             |
| `allMatch()`         | Check if all match             | `list.stream().allMatch(x -> x > 0)`              |
| `noneMatch()`        | Check if none match            | `list.stream().noneMatch(x -> x < 0)`             |

---

# 5. Collectors (Used with collect())

| Method                        | When to Use                           | Example                                                       |
| ----------------------------- | ------------------------------------- | ------------------------------------------------------------- |
| `Collectors.toList()`         | Convert stream to list                | `stream.collect(Collectors.toList())`                         |
| `Collectors.toSet()`          | Convert stream to set                 | `stream.collect(Collectors.toSet())`                          |
| `Collectors.toMap()`          | Convert elements to map               | `stream.collect(Collectors.toMap(k -> k, v -> v.length()))`   |
| `Collectors.groupingBy()`     | Group elements by field               | `stream.collect(Collectors.groupingBy(String::length))`       |
| `Collectors.partitioningBy()` | Split elements into true/false groups | `stream.collect(Collectors.partitioningBy(n -> n % 2 == 0))`  |
| `Collectors.joining()`        | Join strings                          | `stream.collect(Collectors.joining(","))`                     |
| `Collectors.counting()`       | Count elements in grouping            | `Collectors.groupingBy(String::length,Collectors.counting())` |

---

# Most Important Methods (Interview Focus)

These **15 methods cover most real problems**:

| Method        | When to Use                | Example                        |
| ------------- | -------------------------- | ------------------------------ |
| `filter()`    | Filter elements            | `filter(n -> n > 10)`          |
| `map()`       | Transform elements         | `map(String::toUpperCase)`     |
| `flatMap()`   | Flatten nested collections | `flatMap(List::stream)`        |
| `sorted()`    | Sort elements              | `sorted()`                     |
| `distinct()`  | Remove duplicates          | `distinct()`                   |
| `limit()`     | Limit results              | `limit(5)`                     |
| `skip()`      | Skip elements              | `skip(2)`                      |
| `collect()`   | Convert to collection      | `collect(Collectors.toList())` |
| `forEach()`   | Iterate elements           | `forEach(System.out::println)` |
| `count()`     | Count elements             | `count()`                      |
| `findFirst()` | Get first element          | `findFirst()`                  |
| `anyMatch()`  | Check condition            | `anyMatch(n -> n > 100)`       |
| `reduce()`    | Aggregate values           | `reduce(0,Integer::sum)`       |
| `max()`       | Find maximum               | `max(Integer::compare)`        |
| `min()`       | Find minimum               | `min(Integer::compare)`        |

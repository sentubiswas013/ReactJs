Below is a **clean reference table of commonly used Java Stream API methods** showing **when to use each method with an example**.

---

# Java Stream API – When to Use Which Method

| Method                    | When to Use                                | Example                                                        |
| ------------------------- | ------------------------------------------ | -------------------------------------------------------------- |
| `stream()`                | Create a stream from a collection          | `list.stream()`                                                |
| `parallelStream()`        | Process elements in parallel               | `list.parallelStream()`                                        |
| `Stream.of()`             | Create stream from values                  | `Stream.of(1,2,3)`                                             |
| `Arrays.stream()`         | Convert array to stream                    | `Arrays.stream(arr)`                                           |
| `filter()`                | Remove elements that don't match condition | `numbers.stream().filter(n -> n % 2 == 0)`                     |
| `map()`                   | Transform each element                     | `names.stream().map(String::toUpperCase)`                      |
| `mapToInt()`              | Convert elements to IntStream              | `list.stream().mapToInt(Integer::intValue)`                    |
| `mapToLong()`             | Convert elements to LongStream             | `list.stream().mapToLong(Long::valueOf)`                       |
| `mapToDouble()`           | Convert elements to DoubleStream           | `list.stream().mapToDouble(Double::valueOf)`                   |
| `flatMap()`               | Flatten nested collections                 | `listOfLists.stream().flatMap(List::stream)`                   |
| `flatMapToInt()`          | Flatten to IntStream                       | `lists.stream().flatMapToInt(Arrays::stream)`                  |
| `distinct()`              | Remove duplicate elements                  | `numbers.stream().distinct()`                                  |
| `sorted()`                | Sort elements naturally                    | `numbers.stream().sorted()`                                    |
| `sorted(Comparator)`      | Sort with custom comparator                | `numbers.stream().sorted(Comparator.reverseOrder())`           |
| `limit()`                 | Restrict to first N elements               | `numbers.stream().limit(5)`                                    |
| `skip()`                  | Skip first N elements                      | `numbers.stream().skip(3)`                                     |
| `peek()`                  | Debug/log elements inside stream           | `numbers.stream().peek(System.out::println)`                   |
| `forEach()`               | Iterate through elements                   | `numbers.stream().forEach(System.out::println)`                |
| `forEachOrdered()`        | Maintain order in parallel streams         | `numbers.parallelStream().forEachOrdered(System.out::println)` |
| `collect()`               | Convert stream to collection               | `stream.collect(Collectors.toList())`                          |
| `toList()`                | Convert stream directly to List            | `stream.toList()`                                              |
| `count()`                 | Count number of elements                   | `stream.count()`                                               |
| `min()`                   | Find minimum element                       | `numbers.stream().min(Integer::compareTo)`                     |
| `max()`                   | Find maximum element                       | `numbers.stream().max(Integer::compareTo)`                     |
| `reduce()`                | Combine elements into single value         | `numbers.stream().reduce(0, Integer::sum)`                     |
| `anyMatch()`              | Check if any element matches condition     | `numbers.stream().anyMatch(n -> n > 10)`                       |
| `allMatch()`              | Check if all elements match condition      | `numbers.stream().allMatch(n -> n > 0)`                        |
| `noneMatch()`             | Check if none match condition              | `numbers.stream().noneMatch(n -> n < 0)`                       |
| `findFirst()`             | Return first element                       | `numbers.stream().findFirst()`                                 |
| `findAny()`               | Return any element (parallel optimization) | `numbers.stream().findAny()`                                   |
| `takeWhile()`             | Process elements until condition fails     | `numbers.stream().takeWhile(n -> n < 10)`                      |
| `dropWhile()`             | Skip elements while condition is true      | `numbers.stream().dropWhile(n -> n < 5)`                       |
| `IntStream.range()`       | Generate sequence of numbers               | `IntStream.range(1,5)`                                         |
| `IntStream.rangeClosed()` | Generate inclusive range                   | `IntStream.rangeClosed(1,5)`                                   |
| `Stream.iterate()`        | Generate infinite sequence                 | `Stream.iterate(1, n -> n + 1)`                                |
| `Stream.generate()`       | Generate values dynamically                | `Stream.generate(Math::random)`                                |

---

# Common Collectors (Used with `collect()`)

| Collector Method                 | When to Use                           | Example                                                                                                |
| -------------------------------- | ------------------------------------- | ------------------------------------------------------------------------------------------------------ |
| `Collectors.toList()`            | Convert stream to List                | `stream.collect(Collectors.toList())`                                                                  |
| `Collectors.toSet()`             | Convert to Set                        | `stream.collect(Collectors.toSet())`                                                                   |
| `Collectors.toMap()`             | Convert elements to Map               | `list.stream().collect(Collectors.toMap(k -> k, v -> v.length()))`                                     |
| `Collectors.groupingBy()`        | Group elements by property            | `list.stream().collect(Collectors.groupingBy(String::length))`                                         |
| `Collectors.partitioningBy()`    | Split elements into true/false groups | `numbers.stream().collect(Collectors.partitioningBy(n -> n % 2 == 0))`                                 |
| `Collectors.joining()`           | Join strings                          | `list.stream().collect(Collectors.joining(","))`                                                       |
| `Collectors.counting()`          | Count elements in grouping            | `Collectors.groupingBy(String::length, Collectors.counting())`                                         |
| `Collectors.summingInt()`        | Sum integer values                    | `Collectors.summingInt(Employee::getSalary)`                                                           |
| `Collectors.mapping()`           | Apply mapping inside grouping         | `Collectors.groupingBy(Employee::getDept, Collectors.mapping(Employee::getName, Collectors.toList()))` |
| `Collectors.collectingAndThen()` | Post-process collected result         | `Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList)`                     |

---

# Most Important Methods (Used in 90% Real Code)

If you remember **these 15 methods**, you can solve most problems:

| Method        | When to Use                | Example                        |
| ------------- | -------------------------- | ------------------------------ |
| `filter()`    | Remove unwanted elements   | `filter(n -> n > 10)`          |
| `map()`       | Transform elements         | `map(String::toUpperCase)`     |
| `flatMap()`   | Flatten nested collections | `flatMap(List::stream)`        |
| `sorted()`    | Sort elements              | `sorted()`                     |
| `distinct()`  | Remove duplicates          | `distinct()`                   |
| `limit()`     | Restrict results           | `limit(5)`                     |
| `skip()`      | Skip elements              | `skip(2)`                      |
| `collect()`   | Convert to collection      | `collect(Collectors.toList())` |
| `forEach()`   | Iterate elements           | `forEach(System.out::println)` |
| `count()`     | Count elements             | `count()`                      |
| `findFirst()` | Get first element          | `findFirst()`                  |
| `anyMatch()`  | Check condition            | `anyMatch(n -> n > 100)`       |
| `reduce()`    | Aggregate values           | `reduce(0,Integer::sum)`       |
| `max()`       | Find maximum               | `max(Integer::compare)`        |
| `min()`       | Find minimum               | `min(Integer::compare)`        |

---

If you want, I can also give you **the complete Stream API pipeline flow (with diagrams)** which helps **very much in interviews to explain how streams execute internally**.

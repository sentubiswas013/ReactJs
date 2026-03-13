

Write a program to find the sum of all elements in a list using Java Stream API
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        int sum = numbers.stream()
                        .mapToInt(Integer::intValue)
                        .sum();
        System.out.println("Sum: " + sum);
    }
}

Output:
Sum: 15
2. Given a list of integers, write a program to find and print the maximum element using Java Stream API

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

Output:
Max element: 10
3. Write a program to filter out all the even numbers from a list using Java Stream API

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

Output:
Even numbers: [2, 4]
4. Given a list of strings, write a program to count the number of strings containing a specific character ‘a’ using Java Stream API.

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

Output:
Number of strings containing 'a': 4
5. Write a program to convert a list of strings to uppercase using Java Stream API.

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

Output:
Uppercase strings: [APPLE, BANANA, ORANGE, GRAPE]
6. Given a list of integers, write a program to calculate the average of all the numbers using Java Stream API.

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

Output:
Average: 3.0
7. Write a program to sort a list of strings in alphabetical order using Java Stream API.

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

Output:
Sorted strings: [apple, banana, grape, orange]
8. Given a list of strings, write a program to concatenate all the strings using Java Stream API.

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

Output:
Concatenated string: applebananaorangegrape
9. Write a program to find the longest string in a list of strings using Java Stream API.

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

Output:
Longest string: banana
10. Given a list of integers, write a program to find and print the second largest number using Java Stream API.

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

Output:
Second largest number: 8
11. Write a program to remove all the duplicate elements from a list using Java Stream API.

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

Output:
Original list: [1, 2, 3, 4, 2, 5, 6, 3, 7, 8, 1]
List with duplicates removed: [1, 2, 3, 4, 5, 6, 7, 8]
12. Given a list of strings, write a program to find and print the shortest string using Java Stream API.

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

Output:
Shortest string: kiwi
13. Write a program to convert a list of integers to a list of their squares using Java Stream API.

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

Output:
Original list: [1, 2, 3, 4, 5]
List of squares: [1, 4, 9, 16, 25]
14. Given a list of strings, write a program to find and print the strings starting with a specific prefix ‘a’ using Java Stream API.

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
        System.out.println("Strings starting with prefix '" + prefix + "': " + stringsWithPrefix);
    }
}

Output:
Strings starting with prefix 'a': [apple]
15. Write a program to find the product of all elements in a list of integers using Java Stream API.

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        int product = numbers.stream()
                            .reduce(1, (a, b) -> a * b);
        System.out.println("Product of all elements: " + product);
    }
}

Output:
Product of all elements: 120
16. Given a list of integers, write a program to find and print the prime numbers using Java Stream API.

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
        List<Integer> primes = numbers.stream()
                                      .filter(Main::isPrime)
                                      .collect(Collectors.toList());
        System.out.println("Prime numbers: " + primes);
    }

    private static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}

Output:
Prime numbers: [2, 3, 5, 7, 11]
17. Write a program to check if a list of strings contains a specific string using Java Stream API.

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("apple", "banana", "kiwi", "orange", "pear");
        String target = "banana";
        boolean containsString = strings.stream()
                                       .anyMatch(s -> s.equals(target));
        System.out.println("List contains string '" + target + "': " + containsString);
    }
}

Output:
List contains string 'banana': true
18. Given a list of strings, write a program to find and print the strings with length greater than a specified value 5 using Java Stream API.

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("apple", "banana", "kiwi", "orange", "pear");
        int minLength = 5;
        List<String> longStrings = strings.stream()
                                         .filter(s -> s.length() > minLength)
                                         .collect(Collectors.toList());
        System.out.println("Strings with length greater than " + minLength + ": " + longStrings);
    }
}

Output:
Strings with length greater than 5: [banana, orange]
19. Write a program to filter out all the elements divisible by 3 and 5 from a list of integers using Java Stream API.

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);
        List<Integer> divisibleBy3And5 = numbers.stream()
                                               .filter(n -> n % 3 == 0 && n % 5 == 0)
                                               .collect(Collectors.toList());
        System.out.println("Numbers divisible by 3 and 5: " + divisibleBy3And5);
    }
}

Output:
Numbers divisible by 3 and 5: [15]
20. Given a list of strings, write a program to find and print the strings with the maximum length using Java Stream API.

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("apple", "banana", "kiwi", "orange", "pear");
        Optional<String> maxLengthString = strings.stream()
                                                 .max(Comparator.comparingInt(String::length));
        maxLengthString.ifPresent(s -> System.out.println("String with maximum length: " + s));
    }
}

Output:
String with maximum length: banana
21. Write a program to reverse a list of strings using Java Stream API.

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("apple", "banana", "kiwi", "orange", "pear");
        Collections.reverse(strings);
        System.out.println("Reversed list: " + strings);
    }
}

Output:
Reversed list: [pear, orange, kiwi, banana, apple]
22. Given a list of integers, write a program to find and print the distinct odd numbers using Java Stream API.

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> distinctOddNumbers = numbers.stream()
                                                  .filter(n -> n % 2 != 0)
                                                  .distinct()
                                                  .collect(Collectors.toList());
        System.out.println("Distinct odd numbers: " + distinctOddNumbers);
    }
}

Output:
Distinct odd numbers: [1, 3, 5, 7, 9]
23. Write a program to remove all null values from a list of strings using Java Stream API.

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("apple", null, "banana", null, "kiwi", "orange", null, "pear");
        List<String> nonNullStrings = strings.stream()
                                             .filter(s -> s != null)
                                             .collect(Collectors.toList());
        System.out.println("List with null values removed: " + nonNullStrings);
    }
}

Output:
List with null values removed: [apple, banana, kiwi, orange, pear]
24. Given a list of integers, write a program to find and print the sum of all odd numbers using Java Stream API.

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        int sumOfOddNumbers = numbers.stream()
                                    .filter(n -> n % 2 != 0)
                                    .mapToInt(Integer::intValue)
                                    .sum();
        System.out.println("Sum of odd numbers: " + sumOfOddNumbers);
    }
}

Output:
Sum of odd numbers: 25
25. Write a program to find the intersection of two lists of strings using Java Stream API.

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> list1 = Arrays.asList("apple", "banana", "kiwi", "orange", "pear");
        List<String> list2 = Arrays.asList("banana", "orange", "grape", "watermelon");
        List<String> intersection = list1.stream()
                                        .filter(list2::contains)
                                        .collect(Collectors.toList());
        System.out.println("Intersection of lists: " + intersection);
    }
}

Output:
Intersection of lists: [banana, orange]
26. Given a list of strings, write a program to find and print the strings containing only vowels using Java Stream API.

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("apple", "banana", "kiwi", "orange", "pear", "oai");
        List<String> vowelStrings = strings.stream()
                                          .filter(s -> s.matches("[aeiouAEIOU]+"))
                                          .collect(Collectors.toList());
        System.out.println("Strings containing only vowels: " + vowelStrings);
    }
}

Output:
Strings containing only vowels: [oai]
27. Write a program to convert a list of strings to a comma-separated string using Java Stream API.

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("apple", "banana", "kiwi", "orange", "pear");
        String commaSeparatedString = strings.stream()
                                            .collect(Collectors.joining(", "));
        System.out.println("Comma-separated string: " + commaSeparatedString);
    }
}

Output:
Comma-separated string: apple, banana, kiwi, orange, pear
28. Given a list of integers, write a program to find and print the index of the first occurrence of a specific number using Java Stream API.

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 3, 5, 7, 9, 2, 4, 6, 8, 10);
        int targetNumber = 7;
        int index = numbers.indexOf(targetNumber);
        System.out.println("Index of " + targetNumber + ": " + index);
    }
}

Output:
Index of 7: 3
29. Write a program to find the union of two lists of integers using Java Stream API.

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> list2 = Arrays.asList(4, 5, 6, 7, 8);
        List<Integer> union = Stream.concat(list1.stream(), list2.stream())
                                    .distinct()
                                    .collect(Collectors.toList());
        System.out.println("Union of lists: " + union);
    }
}

Output:
Union of lists: [1, 2, 3, 4, 5, 6, 7, 8]
30. Given a list of strings, write a program to find and print the strings containing duplicate characters using Java Stream API.

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("apple", "banana", "kiwi", "orange", "pear", "strawberry", "watermelon");
        List<String> duplicateStrings = strings.stream()
                                              .filter(s -> s.length() != s.chars().distinct().count())
                                              .collect(Collectors.toList());
        System.out.println("Strings containing duplicate characters: " + duplicateStrings);
    }
}

Output:
Strings containing duplicate characters: [apple, banana, kiwi, strawberry, watermelon]
31. Write a program to check if all elements in a list of strings are of the same length using Java Stream API.

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("apple", "banana", "kiwi", "orange", "pear");
        boolean sameLength = strings.stream()
                                   .map(String::length)
                                   .distinct()
                                   .count() == 1;
        System.out.println("All elements have the same length: " + sameLength);
    }
}

Output:
All elements have the same length: false
32. Given a list of integers, write a program to find and print the difference between the maximum and minimum numbers using Java Stream API.

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(10, 5, 7, 18, 3, 15);
        OptionalInt max = numbers.stream().mapToInt(Integer::intValue).max();
        OptionalInt min = numbers.stream().mapToInt(Integer::intValue).min();
        int difference = max.getAsInt() - min.getAsInt();
        System.out.println("Difference between maximum and minimum numbers: " + difference);
    }
}

Output:
Difference between maximum and minimum numbers: 15
33. Write a program to remove all whitespace from a list of strings using Java Stream API.

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("apple", "ba nana", "kiwi", "oran ge", "pear");
        List<String> noWhitespace = strings.stream().map(s -> s.replaceAll("\\s", "")).collect(Collectors.toList());
        System.out.println("List with whitespace removed: " + noWhitespace);
    }
}

Output:
List with whitespace removed: [apple, banana, kiwi, orange, pear]
34. Given a list of strings, write a program to find and print the strings containing a specific substring using Java Stream API.

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("apple", "banana", "kiwi", "orange", "pear");
        String substring = "an";
        List<String> containingSubstring = strings.stream().filter(s -> s.contains(substring)).collect(Collectors.toList());
        System.out.println("Strings containing \"" + substring + "\": " + containingSubstring);
    }
}

Output:
Strings containing "an": [banana, orange]
35. Write a program to find the mode of a list of integers using Java Stream API.

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 3, 4, 4, 4, 5, 5);
        Map<Integer, Long> frequencyMap = numbers.stream().collect(Collectors.groupingBy(i -> i, Collectors.counting()));
        long maxFrequency = frequencyMap.values().stream().mapToLong(Long::longValue).max().orElse(0);
        List<Integer> modes = frequencyMap.entrySet().stream()
                .filter(entry -> entry.getValue() == maxFrequency)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        System.out.println("Mode(s): " + modes);
    }
}

Output:
Mode(s): [4]
36. Given a list of strings, write a program to find and print the strings with the minimum length using Java Stream API.

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("apple", "banana", "kiwi", "orange", "pear");
        Optional<String> minLengthString = strings.stream().min(Comparator.comparingInt(String::length));
        System.out.println("String with minimum length: " + minLengthString.orElse("No strings in the list"));
    }
}

Output:
String with minimum length: kiwi
37. Write a program to find the frequency of each element in a list of integers using Java Stream API.

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 3, 4, 4, 4, 5, 5);
        Map<Integer, Long> frequencyMap = numbers.stream().collect(Collectors.groupingBy(i -> i, Collectors.counting()));
        System.out.println("Frequency of each element: " + frequencyMap);
    }
}

Output:
Frequency of each element: {1=1, 2=1, 3=2, 4=3, 5=2}
38. Given a list of strings, write a program to find and print the strings with the maximum number of vowels using Java Stream API.

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("apple", "banana", "kiwi", "orange", "pear");
        Map<String, Long> frequencyMap = strings.stream()
                .collect(Collectors.toMap(s -> s, s -> s.chars().filter(c -> "AEIOUaeiou".indexOf(c) != -1).count()));
        long maxVowelCount = frequencyMap.values().stream().mapToLong(Long::longValue).max().orElse(0);
        List<String> maxVowelStrings = frequencyMap.entrySet().stream()
                .filter(entry -> entry.getValue() == maxVowelCount)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        System.out.println("String(s) with maximum number of vowels: " + maxVowelStrings);
    }
}

Output:
String(s) with maximum number of vowels: [banana, orange]
39. Write a program to check if a list of integers is sorted in ascending order using Java Stream API.

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 5, 4);
        boolean sortedAscending = numbers.stream().sorted().collect(Collectors.toList()).equals(numbers);
        System.out.println("Is the list sorted in ascending order? " + sortedAscending);
    }
}

Output:
Is the list sorted in ascending order? false
40. Given a list of strings, write a program to find and print the strings with the minimum number of vowels using Java Stream API.

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("apple", "banana", "kiwi", "orange", "pear");
        Map<String, Long> frequencyMap = strings.stream()
                .collect(Collectors.toMap(s -> s, s -> s.chars().filter(c -> "AEIOUaeiou".indexOf(c) != -1).count()));
        long minVowelCount = frequencyMap.values().stream().mapToLong(Long::longValue).min().orElse(0);
        List<String> minVowelStrings = frequencyMap.entrySet().stream()
                .filter(entry -> entry.getValue() == minVowelCount)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        System.out.println("String(s) with minimum number of vowels: " + minVowelStrings);
    }
}

Output:
String(s) with minimum number of vowels: [apple, kiwi, pear]
41. Write a program to find the median of a list of integers using Java Stream API.

import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        OptionalDouble median = numbers.stream().mapToInt(Integer::intValue).sorted()
                                        .skip((numbers.size() - 1) / 2)
                                        .limit(numbers.size() % 2 == 0 ? 2 : 1)
                                        .average();
        System.out.println("Median of the list: " + (median.isPresent() ? median.getAsDouble() : "N/A"));
    }
}

Output:
Median of the list: 3.0
42. Given a list of strings, write a program to find and print the strings containing a specific character at least twice using Java Stream API.

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("apple", "banana", "kiwi", "orange", "pear");
        char targetChar = 'a';
        List<String> containingCharTwice = strings.stream()
                .filter(s -> s.chars().filter(c -> c == targetChar).count() >= 2)
                .collect(Collectors.toList());
        System.out.println("Strings containing \"" + targetChar + "\" at least twice: " + containingCharTwice);
    }
}

Output:
Strings containing "a" at least twice: [banana]
43. Write a program to find the kth smallest element in a list of integers using Java Stream API.

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6, 5);
        int k = 3; // Find the 3rd smallest element
        Optional<Integer> kthSmallest = numbers.stream().sorted().skip(k - 1).findFirst();
        System.out.println("The " + k + "th smallest element: " + (kthSmallest.isPresent() ? kthSmallest.get() : "N/A"));
    }
}

Output:
The 3th smallest element: 2
44. Given a list of strings, write a program to find and print the strings with the maximum number of consonants using Java Stream API.

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("apple", "banana", "kiwi", "orange", "pear");
        Map<String, Long> frequencyMap = strings.stream()
                .collect(Collectors.toMap(s -> s, s -> s.chars().filter(c -> "BCDFGHJKLMNPQRSTVWXYZbcdfghjklmnpqrstvwxyz".indexOf(c) != -1).count()));
        long maxConsonantCount = frequencyMap.values().stream().mapToLong(Long::longValue).max().orElse(0);
        List<String> maxConsonantStrings = frequencyMap.entrySet().stream()
                .filter(entry -> entry.getValue() == maxConsonantCount)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        System.out.println("String(s) with maximum number of consonants: " + maxConsonantStrings);
    }
}

Output:
String(s) with maximum number of consonants: [banana, orange, apple]
45. Write a program to check if a list of strings is palindrome using Java Stream API.

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("apple", "banana", "kiwi", "banana", "apple");
        boolean isPalindrome = strings.stream()
                .skip(strings.size() / 2)
                .allMatch(s -> s.equals(strings.get(strings.size() - 1 - strings.indexOf(s))));
        System.out.println("Is the list a palindrome? " + isPalindrome);
    }
}

Output:
Is the list a palindrome? true
46. Given a list of integers, write a program to find and print the elements with the highest frequency using Java Stream API.

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 3, 4, 4, 4, 5, 5);
        Map<Integer, Long> frequencyMap = numbers.stream().collect(Collectors.groupingBy(i -> i, Collectors.counting()));
        long maxFrequency = frequencyMap.values().stream().mapToLong(Long::longValue).max().orElse(0);
        List<Integer> elementsWithMaxFrequency = frequencyMap.entrySet().stream()
                .filter(entry -> entry.getValue() == maxFrequency)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        System.out.println("Element(s) with highest frequency: " + elementsWithMaxFrequency);
    }
}

Output:
Element(s) with highest frequency: [4]
47. Write a program to remove all non-numeric characters from a list of strings using Java Stream API.

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("a1b2c3", "1a2b3c", "123abc");
        Pattern pattern = Pattern.compile("[^0-9]");
        List<String> numericStrings = strings.stream()
                .map(s -> pattern.matcher(s).replaceAll(""))
                .collect(Collectors.toList());
        System.out.println("List with non-numeric characters removed: " + numericStrings);
    }
}

Output:
List with non-numeric characters removed: [123, 123, 123]
48. Given a list of strings, write a program to find and print the strings containing only digits using Java Stream API.

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("123", "abc", "456", "789", "def");
        Predicate<String> containsOnlyDigits = s -> s.matches("\\d+");
        List<String> digitStrings = strings.stream().filter(containsOnlyDigits).collect(Collectors.toList());
        System.out.println("Strings containing only digits: " + digitStrings);
    }
}

Output:
Strings containing only digits: [123, 456, 789]
49. Write a program to find the kth largest element in a list of integers using Java Stream API.

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6, 5);
        int k = 3; // Find the 3rd largest element
        Collections.sort(numbers, Collections.reverseOrder());
        Integer kthLargest = numbers.stream().distinct().skip(k - 1).findFirst().orElse(null);
        System.out.println("The " + k + "th largest element: " + kthLargest);
    }
}

Output:
The 3th largest element: 5
50. Given a list of integers, write a program to find and print the elements with the lowest frequency using Java Stream API.

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 3, 4, 4, 4, 5, 5);
        Map<Integer, Long> frequencyMap = numbers.stream().collect(Collectors.groupingBy(i -> i, Collectors.counting()));
        long minFrequency = frequencyMap.values().stream().mapToLong(Long::longValue).min().orElse(0);
        List<Integer> elementsWithMinFrequency = frequencyMap.entrySet().stream()
                .filter(entry -> entry.getValue() == minFrequency)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        System.out.println("Element(s) with lowest frequency: " + elementsWithMinFrequency);
    }
}

Output:
Element(s) with lowest frequency: [1, 2]
Thank you for taking the time to read this article. I hope you found the Java Stream API interview coding questions and their solutions helpful in
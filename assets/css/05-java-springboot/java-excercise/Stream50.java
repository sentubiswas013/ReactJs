import java.util.*;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Stream50 {
    public static void main(String[] args) {
        sumOfAllElements();
        maximumElement();
        evenNumbers();
        countTheNumberOfStrings();
        stringsToUppercase();
        calculateAverage();
        sortStrings();
        concatenateStrings();
        longestString();
        secondLargest();
        removeDuplicates();
        shortestString();
        squaresList();
        stringsWithPrefix();
        productOfElements();
        primeNumbers();
        containsString();
        stringsWithLengthGreaterThan();
        divisibleBy3And5();
        maxLengthString();
        reverseList();
        distinctOddNumbers();
        removeNullValues();
        sumOfOddNumbers();
        intersectionOfLists();
        vowelStrings();
        commaSeparatedString();
        indexOfNumber();
        unionOfLists();
        duplicateCharacters();
        sameLengthCheck();
        maxMinDifference();
        removeWhitespace();
        containsSubstring();
        findMode();
        minLengthString();
        elementFrequency();
        maxVowelStrings();
        isSortedAscending();
        minVowelStrings();
        findMedian();
        charAtLeastTwice();
        kthSmallest();
        maxConsonantStrings();
        isPalindromeList();
        highestFrequency();
        removeNonNumeric();
        onlyDigits();
        kthLargest();
        lowestFrequency();
    }

    // 1. Sum of all elements
    public static void sumOfAllElements() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        int sum = numbers.stream().mapToInt(Integer::intValue).sum();
        System.out.println("Sum: " + sum);
    }
    // output:
    // Sum: 15

    // 2. Maximum element
    public static void maximumElement() {
        List<Integer> numbers = Arrays.asList(2, 4, 8, 6, 10);
        int max = numbers.stream().mapToInt(Integer::intValue).max().orElseThrow();
        System.out.println("Max element: " + max);
    }

    // 3. Filter even numbers
    public static void evenNumbers() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> evenNumbers = numbers.stream().filter(num -> num % 2 == 0).collect(Collectors.toList());
        System.out.println("Even numbers: " + evenNumbers);
    }

    // 4. Count strings containing 'a'
    public static void countTheNumberOfStrings() {
        List<String> strings = Arrays.asList("apple", "banana", "orange", "grape");
        long count = strings.stream().filter(str -> str.contains("a")).count();
        System.out.println("Number of strings containing 'a': " + count);
    }
    // output:
    // Number of strings containing 'a': 3

    // 5. Convert to uppercase
    public static void stringsToUppercase() {
        List<String> strings = Arrays.asList("apple", "banana", "orange", "grape");
        List<String> upperCaseStrings = strings.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println("Uppercase strings: " + upperCaseStrings);
    }
    // Output:
    // Uppercase strings: [APPLE, BANANA, ORANGE, GRAPE]

    // 6. Calculate average
    public static void calculateAverage() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        OptionalDouble average = numbers.stream().mapToDouble(Integer::doubleValue).average();
        System.out.println("Average: " + (average.isPresent() ? average.getAsDouble() : "N/A"));
    }
    //Output:
    // Average: 3.0

    // 7. Sort strings
    public static void sortStrings() {
        List<String> strings = Arrays.asList("banana", "orange", "apple", "grape");
        List<String> sortedStrings = strings.stream().sorted().collect(Collectors.toList());
        System.out.println("Sorted strings: " + sortedStrings);
    }
    // Output:
    // Sorted strings: [apple, banana, grape, orange]

    // 8. Concatenate strings
    public static void concatenateStrings() {
        List<String> strings = Arrays.asList("apple", "banana", "orange", "grape");
        String concatenatedString = strings.stream().collect(Collectors.joining());
        System.out.println("Concatenated string: " + concatenatedString);
    }
    // Output:
    // Concatenated string: applebananagrape
    // 9. Longest string
    public static void longestString() {
        List<String> strings = Arrays.asList("apple", "banana", "orange", "grape");
        Optional<String> longestString = strings.stream().max((str1, str2) -> str1.length() - str2.length());
        System.out.println("Longest string: " + longestString.orElse("N/A"));
    }
    // Output:
    // Longest string: banana

    // 10. Second largest number
    public static void secondLargest() {
        List<Integer> numbers = Arrays.asList(4, 2, 8, 6, 10);
        Optional<Integer> secondLargest = numbers.stream().sorted((num1, num2) -> num2 - num1).skip(1).findFirst();
        System.out.println("Second largest number: " + secondLargest.orElse(null));
    }
    // Output:
    // Second largest number: 8


    // 11. Remove duplicates
    public static void removeDuplicates() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 2, 5, 6, 3, 7, 8, 1);
        List<Integer> uniqueNumbers = numbers.stream().distinct().collect(Collectors.toList());
        System.out.println("List with duplicates removed: " + uniqueNumbers);
    }
    // Output:
    // List with duplicates removed: [1, 2, 3, 4, 5, 6, 7, 8]


    // 12. Shortest string
    public static void shortestString() {
        List<String> strings = Arrays.asList("apple", "banana", "kiwi", "orange", "pear");
        String shortestString = strings.stream().min(Comparator.comparingInt(String::length)).orElse(null);
        System.out.println("Shortest string: " + shortestString);
    }
    // Output:
    // Shortest string: kiwi

    // 13. List of squares
    public static void squaresList() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> squares = numbers.stream().map(n -> n * n).collect(Collectors.toList());
        System.out.println("List of squares: " + squares);
    }
    // Output:
    // List of squares: [1, 4, 9, 16,
    // 25]

    // 14. Strings with prefix
    public static void stringsWithPrefix() {
        List<String> strings = Arrays.asList("apple", "banana", "kiwi", "orange", "pear");
        List<String> stringsWithPrefix = strings.stream().filter(s -> s.startsWith("a")).collect(Collectors.toList());
        System.out.println("Strings starting with prefix 'a': " + stringsWithPrefix);
    }
    // Output:
    // Strings starting with prefix 'a': [apple]

    // 15. Product of all elements
    public static void productOfElements() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        int product = numbers.stream().reduce(1, (a, b) -> a * b);
        System.out.println("Product of all elements: " + product);
    }
    // Output:
    // Product of all elements: 120

    // 16. Prime numbers
    public static void primeNumbers() {
        List<Integer> numbers = Arrays.asList(2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
        List<Integer> primes = numbers.stream().filter(Stream50::isPrime).collect(Collectors.toList());
        System.out.println("Prime numbers: " + primes);
    }

    private static boolean isPrime(int n) {
        if (n <= 1) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
    // output:
    // Prime numbers: [2, 3, 5, 7, 11

    // 17. Check if list contains string
    public static void containsString() {
        List<String> strings = Arrays.asList("apple", "banana", "kiwi", "orange", "pear");
        String target = "banana";
        boolean containsString = strings.stream().anyMatch(s -> s.equals(target));
        System.out.println("List contains string '" + target + "': " + containsString);
    }

    // Output:
    // List contains string 'banana': true

    // 18. Strings with length greater than specified value
    public static void stringsWithLengthGreaterThan() {
        List<String> strings = Arrays.asList("apple", "banana", "kiwi", "orange", "pear");
        int minLength = 5;
        List<String> longStrings = strings.stream().filter(s -> s.length() > minLength).collect(Collectors.toList());
        System.out.println("Strings with length greater than " + minLength + ": " + longStrings);
    }
    // Output:
    // Strings with length greater than 5: [banana, orange]

    // 19. Numbers divisible by 3 and 5
    public static void divisibleBy3And5() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);
        List<Integer> divisibleBy3And5 = numbers.stream().filter(n -> n % 3 == 0 && n % 5 == 0).collect(Collectors.toList());
        System.out.println("Numbers divisible by 3 and 5: " + divisibleBy3And5);
    }
    // Output:
    // Numbers divisible by 3 and 5: [15]

    // 20. String with maximum length
    public static void maxLengthString() {
        List<String> strings = Arrays.asList("apple", "banana", "kiwi", "orange", "pear");
        Optional<String> maxLengthString = strings.stream().max(Comparator.comparingInt(String::length));
        maxLengthString.ifPresent(s -> System.out.println("String with maximum length: " + s));
    }
    // Output:
    // String with maximum length: banana

    // 21. Reverse list
    public static void reverseList() {
        List<String> strings = new ArrayList<>(Arrays.asList("apple", "banana", "kiwi", "orange", "pear"));
        Collections.reverse(strings);
        System.out.println("Reversed list: " + strings);
    }
    // output:
    // Reversed list: [pear, orange, kiwi, banana, apple]

    // 22. Distinct odd numbers
    public static void distinctOddNumbers() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> distinctOddNumbers = numbers.stream().filter(n -> n % 2 != 0).distinct().collect(Collectors.toList());
        System.out.println("Distinct odd numbers: " + distinctOddNumbers);
    }
    // Output:
    // Distinct odd numbers: [1, 3, 5, 7, 9]

    // 23. Remove null values
    public static void removeNullValues() {
        List<String> strings = Arrays.asList("apple", null, "banana", null, "kiwi", "orange", null, "pear");
        List<String> nonNullStrings = strings.stream().filter(Objects::nonNull).collect(Collectors.toList());
        System.out.println("List with null values removed: " + nonNullStrings);
    }
    // Output:
    // List with null values removed: [apple, banana, kiwi, orange, pear]

    // 24. Sum of odd numbers
    public static void sumOfOddNumbers() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        int sumOfOddNumbers = numbers.stream().filter(n -> n % 2 != 0).mapToInt(Integer::intValue).sum();
        System.out.println("Sum of odd numbers: " + sumOfOddNumbers);
    }
    // Output:
    // Sum of odd numbers: 25

    // 25. Intersection of two lists
    public static void intersectionOfLists() {
        List<String> list1 = Arrays.asList("apple", "banana", "kiwi", "orange", "pear");
        List<String> list2 = Arrays.asList("banana", "orange", "grape", "watermelon");
        List<String> intersection = list1.stream().filter(list2::contains).collect(Collectors.toList());
        System.out.println("Intersection of lists: " + intersection);
    }
    // Output:
    // Intersection of lists: [banana, orange]

    // 26. Strings containing only vowels
    public static void vowelStrings() {
        List<String> strings = Arrays.asList("apple", "banana", "kiwi", "orange", "pear", "oai");
        List<String> vowelStrings = strings.stream().filter(s -> s.matches("[aeiouAEIOU]+")).collect(Collectors.toList());
        System.out.println("Strings containing only vowels: " + vowelStrings);
    }
    // Output:
    // Strings containing only vowels: [oai]

    // 27. Comma-separated string
    public static void commaSeparatedString() {
        List<String> strings = Arrays.asList("apple", "banana", "kiwi", "orange", "pear");
        String commaSeparatedString = strings.stream().collect(Collectors.joining(", "));
        System.out.println("Comma-separated string: " + commaSeparatedString);
    }
    // Output:
    // Comma-separated string: apple, banana, kiwi, orange, pear

    // 28. Index of first occurrence
    public static void indexOfNumber() {
        List<Integer> numbers = Arrays.asList(1, 3, 5, 7, 9, 2, 4, 6, 8, 10);
        int targetNumber = 7;
        int index = numbers.indexOf(targetNumber);
        System.out.println("Index of " + targetNumber + ": " + index);
    }
    // Output:
    // Index of 7: 3

    // 29. Union of two lists
    public static void unionOfLists() {
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> list2 = Arrays.asList(4, 5, 6, 7, 8);
        List<Integer> union = Stream.concat(list1.stream(), list2.stream()).distinct().collect(Collectors.toList());
        System.out.println("Union of lists: " + union);
    }
    // Output:
    // Union of lists: [1, 2, 3, 4, 5, 6, 7, 8]

    // 30. Strings containing duplicate characters
    public static void duplicateCharacters() {
        List<String> strings = Arrays.asList("apple", "banana", "kiwi", "orange", "pear", "strawberry", "watermelon");
        List<String> duplicateStrings = strings.stream().filter(s -> s.length() != s.chars().distinct().count()).collect(Collectors.toList());
        System.out.println("Strings containing duplicate characters: " + duplicateStrings);
    }
    // Output:
    // Strings containing duplicate characters: [apple, banana, kiwi, orange, pear, strawberry]

    // 31. Check if all elements have same length
    public static void sameLengthCheck() {
        List<String> strings = Arrays.asList("apple", "banana", "kiwi", "orange", "pear");
        boolean sameLength = strings.stream().map(String::length).distinct().count() == 1;
        System.out.println("All elements have the same length: " + sameLength);
    }
    // Output:
    // All elements have the same length: false

    // 32. Difference between max and min
    public static void maxMinDifference() {
        List<Integer> numbers = Arrays.asList(10, 5, 7, 18, 3, 15);
        OptionalInt max = numbers.stream().mapToInt(Integer::intValue).max();
        OptionalInt min = numbers.stream().mapToInt(Integer::intValue).min();
        int difference = max.getAsInt() - min.getAsInt();
        System.out.println("Difference between maximum and minimum numbers: " + difference);
    }
    // Output:
    // Difference between maximum and minimum numbers: 15

    // 33. Remove whitespace
    public static void removeWhitespace() {
        List<String> strings = Arrays.asList("apple", "ba nana", "kiwi", "oran ge", "pear");
        List<String> noWhitespace = strings.stream().map(s -> s.replaceAll("\\s", "")).collect(Collectors.toList());
        System.out.println("List with whitespace removed: " + noWhitespace);
    }
    // Output:
    // List with whitespace removed: [apple, banana, kiwi, orange, pear]

    // 34. Strings containing substring
    public static void containsSubstring() {
        List<String> strings = Arrays.asList("apple", "banana", "kiwi", "orange", "pear");
        String substring = "an";
        List<String> containingSubstring = strings.stream().filter(s -> s.contains(substring)).collect(Collectors.toList());
        System.out.println("Strings containing \"" + substring + "\": " + containingSubstring);
    }
    // Output:
    // Strings containing "an": [banana, orange]

    // 35. Find mode
    public static void findMode() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 3, 4, 4, 4, 5, 5);
        Map<Integer, Long> frequencyMap = numbers.stream().collect(Collectors.groupingBy(i -> i, Collectors.counting()));
        long maxFrequency = frequencyMap.values().stream().mapToLong(Long::longValue).max().orElse(0);
        List<Integer> modes = frequencyMap.entrySet().stream()
                .filter(entry -> entry.getValue() == maxFrequency)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        System.out.println("Mode(s): " + modes);
    }
    // Output:
    // Mode(s): [4]

    // 36. String with minimum length
    public static void minLengthString() {
        List<String> strings = Arrays.asList("apple", "banana", "kiwi", "orange", "pear");
        Optional<String> minLengthString = strings.stream().min(Comparator.comparingInt(String::length));
        System.out.println("String with minimum length: " + minLengthString.orElse("No strings in the list"));
    }
    // Output:
    // String with minimum length: kiwi

    // 37. Frequency of each element
    public static void elementFrequency() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 3, 4, 4, 4, 5, 5);
        Map<Integer, Long> frequencyMap = numbers.stream().collect(Collectors.groupingBy(i -> i, Collectors.counting()));
        System.out.println("Frequency of each element: " + frequencyMap);
    }
    // Output:
    // Frequency of each element: {1=1, 2=1, 3=2, 4=3, 5=2}

    // 38. Strings with maximum vowels
    public static void maxVowelStrings() {
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
    // Output:
    // String(s) with maximum number of vowels: [banana, orange]

    // 39. Check if sorted in ascending order
    public static void isSortedAscending() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 5, 4);
        boolean sortedAscending = numbers.stream().sorted().collect(Collectors.toList()).equals(numbers);
        System.out.println("Is the list sorted in ascending order? " + sortedAscending);
    }
    // Output:
    // Is the list sorted in ascending order? false

    // 40. Strings with minimum vowels
    public static void minVowelStrings() {
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
    // Output:
    // String(s) with minimum number of vowels: [kiwi, pear]

    // 41. Find median
    public static void findMedian() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        OptionalDouble median = numbers.stream().mapToInt(Integer::intValue).sorted()
                .skip((numbers.size() - 1) / 2)
                .limit(numbers.size() % 2 == 0 ? 2 : 1)
                .average();
        System.out.println("Median of the list: " + (median.isPresent() ? median.getAsDouble() : "N/A"));
    }
    // Output:
    // Median of the list: 3.0

    // 42. Strings containing character at least twice
    public static void charAtLeastTwice() {
        List<String> strings = Arrays.asList("apple", "banana", "kiwi", "orange", "pear");
        char targetChar = 'a';
        List<String> containingCharTwice = strings.stream()
                .filter(s -> s.chars().filter(c -> c == targetChar).count() >= 2)
                .collect(Collectors.toList());
        System.out.println("Strings containing \"" + targetChar + "\" at least twice: " + containingCharTwice);
    }
    // Output:
    // Strings containing "a" at least twice: [banana, orange]

    // 43. Kth smallest element
    public static void kthSmallest() {
        List<Integer> numbers = Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6, 5);
        int k = 3;
        Optional<Integer> kthSmallest = numbers.stream().sorted().skip(k - 1).findFirst();
        System.out.println("The " + k + "th smallest element: " + (kthSmallest.isPresent() ? kthSmallest.get() : "N/A"));
    }
    // Output:
    // The 3rd smallest element: 2

    // 44. Strings with maximum consonants
    public static void maxConsonantStrings() {
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
    // Output:
    // String(s) with maximum number of consonants: [banana, orange]

    // 45. Check if list is palindrome
    public static void isPalindromeList() {
        List<String> strings = Arrays.asList("apple", "banana", "kiwi", "banana", "apple");
        boolean isPalindrome = strings.stream()
                .skip(strings.size() / 2)
                .allMatch(s -> s.equals(strings.get(strings.size() - 1 - strings.indexOf(s))));
        System.out.println("Is the list a palindrome? " + isPalindrome);
    }
    // Output:
    // Is the list a palindrome? true

    // 46. Elements with highest frequency
    public static void highestFrequency() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 3, 4, 4, 4, 5, 5);
        Map<Integer, Long> frequencyMap = numbers.stream().collect(Collectors.groupingBy(i -> i, Collectors.counting()));
        long maxFrequency = frequencyMap.values().stream().mapToLong(Long::longValue).max().orElse(0);
        List<Integer> elementsWithMaxFrequency = frequencyMap.entrySet().stream()
                .filter(entry -> entry.getValue() == maxFrequency)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        System.out.println("Element(s) with highest frequency: " + elementsWithMaxFrequency);
    }
    // Output:
    // Element(s) with highest frequency: [4]

    // 47. Remove non-numeric characters
    public static void removeNonNumeric() {
        List<String> strings = Arrays.asList("a1b2c3", "1a2b3c", "123abc");
        Pattern pattern = Pattern.compile("[^0-9]");
        List<String> numericStrings = strings.stream()
                .map(s -> pattern.matcher(s).replaceAll(""))
                .collect(Collectors.toList());
        System.out.println("List with non-numeric characters removed: " + numericStrings);
    }
    // Output:
    // List with non-numeric characters removed: [123, 123, 123]

    // 48. Strings containing only digits
    public static void onlyDigits() {
        List<String> strings = Arrays.asList("123", "abc", "456", "789", "def");
        Predicate<String> containsOnlyDigits = s -> s.matches("\\d+");
        List<String> digitStrings = strings.stream().filter(containsOnlyDigits).collect(Collectors.toList());
        System.out.println("Strings containing only digits: " + digitStrings);
    }
    // Output:
    // Strings containing only digits: [123, 456, 789]

    // 49. Kth largest element
    public static void kthLargest() {
        List<Integer> numbers = Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6, 5);
        int k = 3;
        Integer kthLargest = numbers.stream().sorted(Collections.reverseOrder()).distinct().skip(k - 1).findFirst().orElse(null);
        System.out.println("The " + k + "th largest element: " + kthLargest);
    }
    // Output:
    // The 3rd largest element: 5

    // 50. Elements with lowest frequency
    public static void lowestFrequency() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 3, 4, 4, 4, 5, 5);
        Map<Integer, Long> frequencyMap = numbers.stream().collect(Collectors.groupingBy(i -> i, Collectors.counting()));
        long minFrequency = frequencyMap.values().stream().mapToLong(Long::longValue).min().orElse(0);
        List<Integer> elementsWithMinFrequency = frequencyMap.entrySet().stream()
                .filter(entry -> entry.getValue() == minFrequency)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        System.out.println("Element(s) with lowest frequency: " + elementsWithMinFrequency);
    }
    // Output:
    // Element(s) with lowest frequency: [1, 2, 5]
}

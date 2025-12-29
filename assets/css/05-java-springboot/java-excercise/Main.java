import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.io.File;
import java.io.IOException;

public class Main01 {
    public static void main(String[] args) {
        System.out.println("Hello, World!");


        // ReverseString();
        // ReverseStringNoReverse();
        // SwapWithThirdVariable();
        // SwapWithoutThirdVariable();
        // CountWordsUsingHashMap();
        // IterateHashMap();
        // PrimeNumber();
        // Palindrome();
        // FibonacciSeries();
        // IterateArrayList();
        // DuplicateCharacters();
        // findDuplicateNums();
        // SecondHighestNumber();
        // ArmstrongNumber();
        // RemoveWhiteSpacesWithReplace();
        // RemoveWhiteSpacesWithoutReplace();
        // Factorial();
        // EvenOdd();
        // SumOfDigits();
        // MultiplicationTable();
        // LargestElement();
        // SmallestElement();
        // VowelConsonantCount();
        // AnagramCheck();
        // PrimeNumbers();
        // ReverseInteger();
        // StringToInteger();
        // StarTriangle();
        // SumOfNaturalNumbers();
        // PerfectNumber();
        // GreatestCommonDivisor();
        // LeastCommonMultiple();
        // PowerOfNumber();
        // CountOccurrences();
        // MergeArrays();
        // SortArray();
        // SumArray();
        // MedianArray();
        // NumberPattern();
        // ContainsSubstring();
        // ReplaceCharacter();
        // RemoveDuplicates();
        // ArrayToString();
        // SwapStrings();
        // LongestWord();
        // PerfectSquare();
        // BinarySearch();
        // binarySearch();
        // CommonElements();

        // Question: 48 ====
        // Stack stack = new Stack(5);  // Stack of size 5
        // stack.push(10);
        // stack.push(20);
        // System.out.println("Top element: " + stack.peek());
        // System.out.println("Popped element: " + stack.pop());

        // Question: 49 ====
        // Queue queue = new Queue(5);  // Queue of size 5
        // queue.enqueue(10);
        // queue.enqueue(20);
        // System.out.println("Front element: " + queue.peek());
        // System.out.println("Dequeued element: " + queue.dequeue());

        // FibonacciNumber();
    }

    // 1. Reverse a String without using String inbuilt function
    public static void ReverseString() {
        String str = "HelloWorld";
        char[] strArray = str.toCharArray();
        int start = 0;
        int end = strArray.length - 1;

        while (start < end) {
            // Swap characters
            char temp = strArray[start];
            strArray[start] = strArray[end];
            strArray[end] = temp;

            start++;
            end--;
        }

        System.out.println("Reversed string: " + new String(strArray));
    }


    // 2. Reverse a String without using String inbuilt function reverse()
    public static void ReverseStringNoReverse() {
        String str = "HelloWorld";
        char[] strArray = str.toCharArray();
        String reversedStr = "";

        for (int i = strArray.length - 1; i >= 0; i--) {
            reversedStr += strArray[i];
        }

        System.out.println("Reversed string: " + reversedStr);
    }


    // 3. Swap Two Numbers Using the Third Variable
    public static void SwapWithThirdVariable() {
        int a = 5, b = 10;

        System.out.println("Before swapping: a = " + a + ", b = " + b);

        // Using third variable
        int temp = a;
        a = b;
        b = temp;

        System.out.println("After swapping: a = " + a + ", b = " + b);
    }


    // 4. Swap Two Numbers Without Using the Third Variable
    public static void SwapWithoutThirdVariable() {
        int a = 5, b = 10;

        System.out.println("Before swapping: a = " + a + ", b = " + b);

        // Without using third variable
        a = a + b; // a becomes 15
        b = a - b; // b becomes 5
        a = a - b; // a becomes 10

        System.out.println("After swapping: a = " + a + ", b = " + b);
    }


    // 5. Count the Number of Words in a String Using HashMap
    public static void CountWordsUsingHashMap() {
        String str = "This is a test string and this is a test";
        String[] words = str.split("\\s+");

        HashMap<String, Integer> wordCount = new HashMap<>();

        for (String word : words) {
            word = word.toLowerCase();  // Optional: Convert to lowercase for case-insensitive count
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }

        // Print word counts
        for (String key : wordCount.keySet()) {
            System.out.println(key + ": " + wordCount.get(key));
        }
    }



    // 6. Iterate HashMap using While and Advanced For Loop
    public static void IterateHashMap() {
        // Create a HashMap
        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "Apple");
        map.put(2, "Banana");
        map.put(3, "Cherry");

        // Iterate using while loop
        System.out.println("Iterating HashMap using While Loop:");
        var iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> entry = iterator.next();
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // Iterate using advanced for loop (for-each loop)
        System.out.println("\nIterating HashMap using Advanced For Loop:");
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }



    // 7. Find Whether a Number is Prime or Not
    public static void PrimeNumber() {
        int number = 29;
        boolean isPrime = true;

        // Check if number is prime
        if (number <= 1) {
            isPrime = false;  // Prime numbers are greater than 1
        } else {
            for (int i = 2; i <= Math.sqrt(number); i++) {
                if (number % i == 0) {
                    isPrime = false;
                    break;
                }
            }
        }

        if (isPrime) {
            System.out.println(number + " is a prime number.");
        } else {
            System.out.println(number + " is not a prime number.");
        }
    }



    // 8. Find Whether a String or Number is Palindrome or Not
    public static void Palindrome() {
        String str = "madam";
        boolean isPalindrome = true;

        // Check if string is palindrome
        int length = str.length();
        for (int i = 0; i < length / 2; i++) {
            if (str.charAt(i) != str.charAt(length - 1 - i)) {
                isPalindrome = false;
                break;
            }
        }

        if (isPalindrome) {
            System.out.println(str + " is a palindrome.");
        } else {
            System.out.println(str + " is not a palindrome.");
        }

        // Check for a number
        int number = 121;
        int originalNumber = number;
        int reversedNumber = 0;

        while (number != 0) {
            int digit = number % 10;
            reversedNumber = reversedNumber * 10 + digit;
            number /= 10;
        }

        if (originalNumber == reversedNumber) {
            System.out.println(originalNumber + " is a palindrome number.");
        } else {
            System.out.println(originalNumber + " is not a palindrome number.");
        }
    }


    // 9. Fibonacci Series
    public static void FibonacciSeries() {
        int n = 10;  // Number of terms in the Fibonacci series
        int a = 0, b = 1;

        System.out.println("Fibonacci Series up to " + n + " terms:");

        for (int i = 0; i < n; i++) {
            System.out.print(a + " ");
            int nextTerm = a + b;
            a = b;
            b = nextTerm;
        }
    }


    // 10. Iterate ArrayList Using For-loop, While-loop, and Advanced For-loop
    public static void IterateArrayList() {
        // Create an ArrayList
        ArrayList<String> list = new ArrayList<>();
        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");

        // Iterate using for-loop
        System.out.println("Iterating ArrayList using For-loop:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        // Iterate using while-loop
        System.out.println("\nIterating ArrayList using While-loop:");
        int index = 0;
        while (index < list.size()) {
            System.out.println(list.get(index));
            index++;
        }

        // Iterate using advanced for-loop (for-each loop)
        System.out.println("\nIterating ArrayList using Advanced For-loop:");
        for (String item : list) {
            System.out.println(item);
        }
    }



    // 11. Find the Duplicate Characters in a String
    public static void DuplicateCharacters() {
        String str = "programming";
        HashMap<Character, Integer> charCount = new HashMap<>();

        // Count each character's frequency
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            charCount.put(ch, charCount.getOrDefault(ch, 0) + 1);
        }

        System.out.println("Duplicate characters in the string:");
        // Print characters that have a frequency greater than 1
        for (Character key : charCount.keySet()) {
            if (charCount.get(key) > 1) {
                System.out.println(key + ": " + charCount.get(key));
            }
        }
    }



    // 11. Find the Duplicate Characters in a Numbers
    public static void findDuplicateNums() {
        int[] nums = {2, 5, 2, 7, 8, 9, 5, 3};

        System.out.print("Duplicate numbers: ");

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    System.out.print(nums[i] + " ");
                    break;
                }
            }
        }
    }

    // 12. Find the Second-Highest Number in an Array
    public static void SecondHighestNumber() {
        int[] arr = {10, 5, 20, 20, 15, 5, 30};
        int highest = Integer.MIN_VALUE;
        int secondHighest = Integer.MIN_VALUE;

        // Iterate through the array to find the highest and second-highest numbers
        for (int num : arr) {
            if (num > highest) {
                secondHighest = highest;
                highest = num;
            } else if (num > secondHighest && num < highest) {
                secondHighest = num;
            }
        }

        if (secondHighest == Integer.MIN_VALUE) {
            System.out.println("There is no second-highest number in the array.");
        } else {
            System.out.println("Second-highest number: " + secondHighest);
        }
    }



    // 13. Check Armstrong Number
    public static void ArmstrongNumber() {
        int number = 153;
        int originalNumber = number;
        int sum = 0;
        int digits = String.valueOf(number).length();

        // Calculate the sum of the digits raised to the power of the number of digits
        while (number != 0) {
            int digit = number % 10;
            sum += Math.pow(digit, digits);
            number /= 10;
        }

        if (sum == originalNumber) {
            System.out.println(originalNumber + " is an Armstrong number.");
        } else {
            System.out.println(originalNumber + " is not an Armstrong number.");
        }
    }


    // 14. Remove All White Spaces from a String Using `replace()`
    public static void RemoveWhiteSpacesWithReplace() {
        String str = "   Hello World!   ";
        String result = str.replace(" ", "");

        System.out.println("String after removing white spaces: '" + result + "'");
    }


    // 15. Remove All White Spaces from a String Without Using `replace()`
    public static void RemoveWhiteSpacesWithoutReplace() {
        String str = "   Hello World!   ";
        StringBuilder result = new StringBuilder();

        // Iterate through the string and append non-space characters to StringBuilder
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != ' ') {
                result.append(str.charAt(i));
            }
        }

        System.out.println("String after removing white spaces: '" + result.toString() + "'");
    }


    // 16. Find the Factorial of a Number
    public static void Factorial() {
        int number = 5;  // Example number
        long factorial = 1;

        // Loop to calculate factorial
        for (int i = 1; i <= number; i++) {
            factorial *= i;
        }

        System.out.println("Factorial of " + number + " is: " + factorial);
    }


    // 17. Check if a Number is Even or Odd
    public static void EvenOdd() {
        int number = 7;  // Example number

        // Check if the number is even or odd
        if (number % 2 == 0) {
            System.out.println(number + " is an even number.");
        } else {
            System.out.println(number + " is an odd number.");
        }
    }


    // 18. Find the Sum of Digits of a Number
    public static void SumOfDigits() {
        int number = 1234;  // Example number
        int sum = 0;

        // Loop to find the sum of digits
        while (number != 0) {
            sum += number % 10;  // Add last digit
            number /= 10;         // Remove last digit
        }

        System.out.println("Sum of digits: " + sum);
    }


    // 19. Print the Multiplication Table of a Number
    public static void MultiplicationTable() {
        int number = 5;  // Example number
        int limit = 10;   // Table limit (multiplying by numbers 1 to 10)

        System.out.println("Multiplication Table of " + number + ":");
        for (int i = 1; i <= limit; i++) {
            System.out.println(number + " x " + i + " = " + (number * i));
        }
    }


    // 20. Find the Largest Element in an Array
    public static void LargestElement() {
        int[] arr = {10, 20, 5, 30, 15};  // Example array
        int largest = arr[0];

        // Loop to find the largest element
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > largest) {
                largest = arr[i];
            }
        }

        System.out.println("Largest element in the array is: " + largest);
    }


    // 21. Find the Smallest Element in an Array
    public static void SmallestElement() {
        int[] arr = {10, 20, 5, 30, 15};  // Example array
        int smallest = arr[0];

        // Loop to find the smallest element
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < smallest) {
                smallest = arr[i];
            }
        }

        System.out.println("Smallest element in the array is: " + smallest);
    }


    // 22. Count Vowels and Consonants in a String
    public static void VowelConsonantCount() {
        String str = "Hello World";  // Example string
        int vowels = 0, consonants = 0;

        // Convert the string to lowercase to make the check case-insensitive
        str = str.toLowerCase();

        // Loop through each character and count vowels and consonants
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch >= 'a' && ch <= 'z') {
                if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                    vowels++;
                } else {
                    consonants++;
                }
            }
        }

        System.out.println("Vowels: " + vowels);
        System.out.println("Consonants: " + consonants);
    }


    // 23. Check if Two Strings are Anagrams
    public static void AnagramCheck() {
        String str1 = "listen";  // Example strings
        String str2 = "silent";

        // Convert strings to character arrays
        char[] arr1 = str1.toCharArray();
        char[] arr2 = str2.toCharArray();

        // Sort the arrays
        Arrays.sort(arr1);
        Arrays.sort(arr2);

        // Compare sorted arrays
        if (Arrays.equals(arr1, arr2)) {
            System.out.println(str1 + " and " + str2 + " are anagrams.");
        } else {
            System.out.println(str1 + " and " + str2 + " are not anagrams.");
        }
    }

    // 24. Print the Prime Numbers Between 1 and 100
    public static void PrimeNumbers() {
        System.out.println("Prime numbers between 1 and 100:");

        // Loop through numbers from 1 to 100
        for (int num = 2; num <= 100; num++) {
            boolean isPrime = true;

            // Check if num is prime
            for (int i = 2; i <= Math.sqrt(num); i++) {
                if (num % i == 0) {
                    isPrime = false;
                    break;
                }
            }

            // If the number is prime, print it
            if (isPrime) {
                System.out.print(num + " ");
            }
        }
    }


    // 25. Reverse an Integer Number
    public static void ReverseInteger() {
        int number = 12345;  // Example number
        int reversed = 0;

        // Reverse the integer
        while (number != 0) {
            int digit = number % 10;  // Extract the last digit
            reversed = reversed * 10 + digit;  // Append the digit to the reversed number
            number /= 10;  // Remove the last digit
        }

        System.out.println("Reversed number: " + reversed);
    }


    // 26. Convert a String to an Integer
    public static void StringToInteger() {
        String str = "12345";  // Example string
        int number = Integer.parseInt(str);  // Convert string to integer

        System.out.println("The integer value is: " + number);
    }


    // 27. Print a Triangle of Stars
    public static void StarTriangle() {
        int rows = 5;  // Number of rows in the triangle

        // Loop to print the triangle
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("* ");  // Print stars
            }
            System.out.println();  // Move to the next line after each row
        }
    }


    // 28. Find the Sum of Natural Numbers
    public static void SumOfNaturalNumbers() {
        int n = 10;  // Example: sum of first 10 natural numbers
        int sum = 0;

        // Calculate the sum of natural numbers
        for (int i = 1; i <= n; i++) {
            sum += i;
        }

        System.out.println("Sum of first " + n + " natural numbers is: " + sum);
    }


    // 29. Check if a Number is a Perfect Number
    public static void PerfectNumber() {
        int number = 28;  // Example number
        int sum = 0;

        // Find the sum of divisors of the number
        for (int i = 1; i < number; i++) {
            if (number % i == 0) {
                sum += i;
            }
        }

        // Check if the number is a perfect number
        if (sum == number) {
            System.out.println(number + " is a perfect number.");
        } else {
            System.out.println(number + " is not a perfect number.");
        }
    }


    // 30. Find the GCD (Greatest Common Divisor) of Two Numbers
    public static void GreatestCommonDivisor() {
        int num1 = 56;  // Example numbers
        int num2 = 98;

        // Calculate the GCD using Euclidean algorithm
        while (num1 != num2) {
            if (num1 > num2) {
                num1 -= num2;  // Subtract smaller from larger
            } else {
                num2 -= num1;  // Subtract smaller from larger
            }
        }

        System.out.println("The GCD of the two numbers is: " + num1);
    }


    // 31. Find the LCM (Least Common Multiple) of Two Numbers

    public class LeastCommonMultiple {
        public static void main() {
            int num1 = 12, num2 = 18;  // Example numbers
            int lcm;

            // Find the LCM using the formula: LCM(a, b) = (a * b) / GCD(a, b)
            lcm = (num1 * num2) / findGCD(num1, num2);

            System.out.println("The LCM of " + num1 + " and " + num2 + " is: " + lcm);
        }

        // Function to find the GCD of two numbers
        public static int findGCD(int a, int b) {
            while (a != b) {
                if (a > b) {
                    a -= b;
                } else {
                    b -= a;
                }
            }
            return a;  // GCD is when a == b
        }
    }


    // 32. Calculate the Power of a Number
    public static void PowerOfNumber() {
        int base = 2;  // Base number
        int exponent = 5;  // Exponent

        long result = 1;
        
        // Loop to calculate power
        for (int i = 1; i <= exponent; i++) {
            result *= base;
        }

        System.out.println(base + " raised to the power " + exponent + " is: " + result);
    }

    // 33. Count the Number of Occurrences of a Character in a String
    public static void CountOccurrences() {
        String str = "programming";  // Example string
        char targetChar = 'r';  // Character to count
        int count = 0;

        // Loop through the string and count occurrences
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == targetChar) {
                count++;
            }
        }

        System.out.println("The character '" + targetChar + "' occurs " + count + " times in the string.");
    }


    // 34. Merge Two Arrays
    public static void MergeArrays() {
        int[] array1 = {1, 2, 3};  // First array
        int[] array2 = {4, 5, 6};  // Second array

        // Create a new array to hold the merged result
        int[] mergedArray = new int[array1.length + array2.length];

        // Copy elements from the first array
        System.arraycopy(array1, 0, mergedArray, 0, array1.length);

        // Copy elements from the second array
        System.arraycopy(array2, 0, mergedArray, array1.length, array2.length);

        System.out.println("Merged Array: " + Arrays.toString(mergedArray));
    }


    // 35. Sort an Array of Integers in Ascending Order
    public static void SortArray() {
        int[] arr = {5, 2, 9, 1, 5, 6};  // Example array

        // Sort the array using Arrays.sort()
        Arrays.sort(arr);

        System.out.println("Sorted Array in Ascending Order: " + Arrays.toString(arr));
    }


    // 36. Sum All Elements of an Array
    public static void SumArray() {
        int[] arr = {1, 2, 3, 4, 5};  // Example array
        int sum = 0;

        // Loop through the array to sum all elements
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }

        System.out.println("Sum of all elements in the array: " + sum);
    }


    // 37. Find the Median of an Array
    public static void MedianArray() {
        int[] arr = {12, 3, 5, 7, 19};  // Example array
        Arrays.sort(arr);  // Sort the array to find the median
        
        double median;
        int length = arr.length;

        if (length % 2 == 0) {
            // If the length is even, median is the average of the two middle elements
            median = (arr[length / 2 - 1] + arr[length / 2]) / 2.0;
        } else {
            // If the length is odd, median is the middle element
            median = arr[length / 2];
        }

        System.out.println("The median is: " + median);
    }


    // 38. Create a Pattern of Numbers (e.g., 1, 12, 123, etc.)
    public static void NumberPattern() {
        int rows = 5;  // Number of rows in the pattern

        // Loop to create the pattern
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j);  // Print numbers from 1 to i
            }
            System.out.println();  // Move to the next line after each row
        }
    }


    // 39. Check if a String Contains a Substring
    public static void ContainsSubstring() {
        String str = "Hello World";  // Example string
        String substring = "World";  // Substring to check

        // Check if the string contains the substring
        if (str.contains(substring)) {
            System.out.println("The string contains the substring.");
        } else {
            System.out.println("The string does not contain the substring.");
        }
    }


    // 40. Replace a Character in a String Without Using `String.replace()`
    public static void ReplaceCharacter() {
        String str = "Hello World";  // Example string
        char oldChar = 'o';  // Character to replace
        char newChar = 'a';  // New character to replace with

        StringBuilder result = new StringBuilder();

        // Loop through the string and replace characters
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == oldChar) {
                result.append(newChar);  // Replace old character with new one
            } else {
                result.append(str.charAt(i));  // Keep the character as it is
            }
        }

        System.out.println("Modified string: " + result.toString());
    }


    // 41. Remove Duplicates from an Array
    public static void RemoveDuplicates() {
        int[] arr = {1, 2, 3, 4, 4, 5, 5, 6};  // Example array
        arr = Arrays.stream(arr).distinct().toArray();  // Remove duplicates using Streams

        System.out.println("Array without duplicates: " + Arrays.toString(arr));
    }


    // 42. Convert an Array to a String
    public static void ArrayToString() {
        int[] arr = {1, 2, 3, 4, 5};  // Example array
        String arrayString = Arrays.toString(arr);  // Convert array to string

        System.out.println("Array as String: " + arrayString);
    }


    // 43. Swap Two Strings
    public static void SwapStrings() {
        String str1 = "Hello";  // First string
        String str2 = "World";  // Second string

        // Swap the strings
        String temp = str1;
        str1 = str2;
        str2 = temp;

        System.out.println("After swapping:");
        System.out.println("str1: " + str1);
        System.out.println("str2: " + str2);
    }


    // 44. Find the Longest Word in a Sentence
    public static void LongestWord() {
        String sentence = "This is a Java programming challenge.";  // Example sentence
        String[] words = sentence.split(" ");  // Split the sentence into words
        String longestWord = "";

        // Loop through the words and find the longest one
        for (String word : words) {
            if (word.length() > longestWord.length()) {
                longestWord = word;
            }
        }

        System.out.println("The longest word in the sentence is: " + longestWord);
    }


    // 45. Check if a Number is a Perfect Square
    public static void PerfectSquare() {
        int number = 25;  // Example number

        // Check if the square root of the number is an integer
        double sqrt = Math.sqrt(number);
        if (sqrt == (int) sqrt) {
            System.out.println(number + " is a perfect square.");
        } else {
            System.out.println(number + " is not a perfect square.");
        }
    }

    // 46. Implement Binary Search
    public class BinarySearch {
        public static void main() {
            int[] arr = {1, 3, 5, 7, 9, 11, 13, 15};  // Example sorted array
            int target = 7;  // Number to search for
            int result = binarySearch(arr, target);

            if (result == -1) {
                System.out.println("Element not found.");
            } else {
                System.out.println("Element found at index: " + result);
            }
        }

        // Binary search method
        public static int binarySearch(int[] arr, int target) {
            int left = 0, right = arr.length - 1;

            while (left <= right) {
                int mid = left + (right - left) / 2;

                // Check if target is present at mid
                if (arr[mid] == target) {
                    return mid;
                }

                // If target is greater, ignore left half
                if (arr[mid] < target) {
                    left = mid + 1;
                }
                // If target is smaller, ignore right half
                else {
                    right = mid - 1;
                }
            }
            return -1;  // Element not found
        }
    }


    // 47. Find the Common Elements Between Two Arrays
    public static void CommonElements() {
        int[] arr1 = {1, 2, 3, 4, 5};
        int[] arr2 = {3, 4, 5, 6, 7};

        // Using a HashSet to find common elements
        HashSet<Integer> set = new HashSet<>();
        for (int num : arr1) {
            set.add(num);
        }

        System.out.println("Common elements:");
        for (int num : arr2) {
            if (set.contains(num)) {
                System.out.print(num + " ");
            }
        }
    }



    // 48. Implement a Stack Using an Array
    public class Stack {
        private int[] stack;
        private int top;

        // Constructor to initialize the stack
        public Stack(int size) {
            stack = new int[size];
            top = -1;
        }

        // Push an element onto the stack
        public void push(int element) {
            if (top == stack.length - 1) {
                System.out.println("Stack Overflow");
            } else {
                stack[++top] = element;
                System.out.println("Pushed " + element + " to the stack.");
            }
        }

        // Pop an element from the stack
        public int pop() {
            if (top == -1) {
                System.out.println("Stack Underflow");
                return -1;
            } else {
                return stack[top--];
            }
        }

        // Peek at the top element of the stack
        public int peek() {
            if (top == -1) {
                System.out.println("Stack is empty");
                return -1;
            } else {
                return stack[top];
            }
        }
    }


    // 49. Implement a Queue Using an Array
    public class Queue {
        private int[] queue;
        private int front, rear, size;

        // Constructor to initialize the queue
        public Queue(int size) {
            queue = new int[size];
            front = 0;
            rear = 0;
            this.size = size;
        }

        // Enqueue an element to the queue
        public void enqueue(int element) {
            if (rear == size) {
                System.out.println("Queue Overflow");
            } else {
                queue[rear++] = element;
                System.out.println("Enqueued " + element + " to the queue.");
            }
        }

        // Dequeue an element from the queue
        public int dequeue() {
            if (front == rear) {
                System.out.println("Queue Underflow");
                return -1;
            } else {
                return queue[front++];
            }
        }

        // Peek at the front element of the queue
        public int peek() {
            if (front == rear) {
                System.out.println("Queue is empty");
                return -1;
            } else {
                return queue[front];
            }
        }
    }


    // 50. Check if a Number is a Fibonacci Number

    public class FibonacciNumber {
        public static void main() {
            int number = 21;  // Example number
            if (isFibonacci(number)) {
                System.out.println(number + " is a Fibonacci number.");
            } else {
                System.out.println(number + " is not a Fibonacci number.");
            }
        }

        // Function to check if a number is a Fibonacci number
        public static boolean isFibonacci(int num) {
            // A number is a Fibonacci number if and only if one or both of (5*n*n + 4) or (5*n*n - 4) is a perfect square
            return isPerfectSquare(5 * num * num + 4) || isPerfectSquare(5 * num * num - 4);
        }

        // Helper function to check if a number is a perfect square
        public static boolean isPerfectSquare(int n) {
            int sqrt = (int) Math.sqrt(n);
            return (sqrt * sqrt == n);
        }
    }
}
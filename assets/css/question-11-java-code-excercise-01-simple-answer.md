# Java Coding Questions - Complete Collection

## String Manipulation

### ✅ **1. Reverse a String without using String inbuilt function**
```java
public class ReverseString {
    public static void main(String[] args) {
        String str = "Hello World";  // Example string
        char[] charArray = str.toCharArray();  // Convert string to character array
        int length = charArray.length;
        
        // Reverse the string using a loop
        for (int i = 0; i < length / 2; i++) {
            char temp = charArray[i];
            charArray[i] = charArray[length - i - 1];
            charArray[length - i - 1] = temp;
        }

        System.out.println("Reversed string: " + new String(charArray));
    }
}
// Output: Reversed string: dlroW olleH
```

### ✅ **2. Count the Number of Words in a String Using HashMap**
```java
import java.util.HashMap;

public class WordCountUsingHashMap {
    public static void main(String[] args) {
        String input = "hello world hello java world";
        HashMap<String, Integer> wordCount = new HashMap<>();

        // Split the string into words
        String[] words = input.trim().split("\\s+");

        for (String word : words) {
            // Put word in map or update existing count
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }
        // Print result
        System.out.println("Word Count: " + wordCount);
    }
}
 // Output: Word Count: {java=1, world=2, hello=2}
```

### ✅ **3. Find the Duplicate Characters in a String**
```java
import java.util.HashMap;

public class DuplicateCharacters {
    public static void main(String[] args) {
        String str = "programming";  // Example string
        HashMap<Character, Integer> charCount = new HashMap<>();

        // Count the frequency of each character
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }

        // Print duplicate characters
        System.out.println("Duplicate characters:");
        for (char c : charCount.keySet()) {
            if (charCount.get(c) > 1) {
                System.out.println(c + ": " + charCount.get(c));
            }
        }
    }
}
// Output: Duplicate characters: r: 2 g: 2 m: 2
```

### ✅ **4. Remove All White Spaces from a String Using `replace()`**
```java
public class RemoveWhiteSpacesWithReplace {
    public static void main(String[] args) {
        String str = " Hello World! Java Programming ";  // Example string

        // Remove white spaces using replace()
        String result = str.replace(" ", "");

        System.out.println("String without spaces: \"" + result + "\"");
    }
}
// Output: String without spaces: "HelloWorld!JavaProgramming"
```

### ✅ **5. Remove All White Spaces from a String Without Using `replace()**
```java
public class RemoveWhiteSpacesWithoutReplace {
    public static void main(String[] args) {
        String str = " Hello World! Java Programming ";  // Example string

        // Remove white spaces without using replace()
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != ' ') {
                result.append(str.charAt(i));
            }
        }

        System.out.println("String without spaces: \"" + result.toString() + "\"");
    }
}
// Output: String without spaces: "HelloWorld!JavaProgramming"
```

### ✅ **7. Find Whether a String is Palindrome or Not**
```java
public class Palindrome {
    public static void main(String[] args) {
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
}
// Output:
// madam is a palindrome.
// 121 is a palindrome number.
```

### ✅ **7. Count Vowels and Consonants in a String**
```java
public class VowelConsonantCount {
    public static void main(String[] args) {
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
}
// Output: 
// Vowels: 3
// Consonants: 7
```

### ✅ **8. Check if Two Strings are Anagrams**
```java
import java.util.Arrays;

public class AnagramCheck {
    public static void main(String[] args) {
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
}
// Output: listen and silent are anagrams.
```

### ✅ **9. Check if a String Contains a Substring**
```java
public class ContainsSubstring {
    public static void main(String[] args) {
        String str = "Hello World";  // Example string
        String substring = "World";  // Substring to check

        // Check if the string contains the substring
        if (str.contains(substring)) {
            System.out.println("The string contains the substring.");
        } else {
            System.out.println("The string does not contain the substring.");
        }
    }
}
// Output: The string contains the substring.
```

### ✅ **10. Replace a Character in a String Without Using `String.replace()`**
```java
public class ReplaceCharacter {
    public static void main(String[] args) {
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
}
```

### ✅ **11. Find the Longest Word in a Sentence**
```java
public class LongestWord {
    public static void main(String[] args) {
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
}
```

### ✅ **12. Count the Number of Occurrences of a Character in a String**
```java
public class CountOccurrences {
    public static void main(String[] args) {
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
}
```

### ✅ **13. Swap Two Strings**
```java
public class SwapStrings {
    public static void main(String[] args) {
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
}
```

### ✅ **14. Remove All Special Characters From String**
```java
public class RemoveSpecialChars {
    public static void main(String[] args) {
        String input = "Hell@o# Wo$r%ld!123";
        
        // Remove all characters except letters, numbers, and spaces
        String output = input.replaceAll("[^a-zA-Z0-9 ]", "");

        System.out.println(output); // Hello World123
    }
}
```

### ✅ **15. Reverse Words in a Sentence**
Input:
`"Hello World Java"`
Output:
`"Java World Hello"`

###✅ **Solution 1: Using Split (Most Common in Interviews)**

```java
public class ReverseWords {
    public static void main(String[] args) {
        String sentence = "Hello World Java";

        String[] words = sentence.split(" ");
        StringBuilder result = new StringBuilder();

        for (int i = words.length - 1; i >= 0; i--) {
            result.append(words[i]).append(" ");
        }

        System.out.println(result.toString().trim());
    }
}
```

### 📝 **Explanation**

1. Split the sentence into words using `" "`.
2. Loop from the end to the beginning.
3. Append words in reverse order.
4. `trim()` removes the last extra space.

### ✅ **Solution 2: One-Line Using Collections**

```java
import java.util.*;

public class ReverseWords {
    public static void main(String[] args) {
        String sentence = "Hello World Java";

        List<String> list = Arrays.asList(sentence.split(" "));
        Collections.reverse(list);

        System.out.println(String.join(" ", list));
    }
}
```

##### ✅ **Solution 3: Using Stack (Asked in some interviews)**

```java
import java.util.Stack;

public class ReverseWords {
    public static void main(String[] args) {
        String sentence = "I love programming";

        Stack<String> stack = new Stack<>();
        for (String word : sentence.split(" ")) {
            stack.push(word);
        }

        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.append(stack.pop()).append(" ");
        }

        System.out.println(result.toString().trim());
    }
}
```

### ✅ **16. Remove Characters That Appear More Than Once**
```java
import java.util.*;

public class RemoveRepeatedChars {
    public static void main(String[] args) {
        String input = "programming";

        Map<Character, Integer> map = new LinkedHashMap<>();

        // Count frequency
        for (char c : input.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        StringBuilder result = new StringBuilder();

        // Add only characters that appear once
        for (char c : input.toCharArray()) {
            if (map.get(c) == 1) {
                result.append(c);
            }
        }

        System.out.println(result.toString()); // Output: poai
    }
}
```

### ✅ **17. Find First Non-Repeating Character in String**
```java
import java.util.*;

public class FirstNonRepeatingChar {
    public static void main(String[] args) {
        String input = "swiss";

        Map<Character, Integer> map = new LinkedHashMap<>();

        // Count frequency of each character
        for (char c : input.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        // Find first char with frequency 1
        for (char c : map.keySet()) {
            if (map.get(c) == 1) {
                System.out.println("First non-repeating character: " + c);
                return;
            }
        }

        System.out.println("No non-repeating character found");
        // Output: w
    }
}
```

### ✅ **18. Validate Email Format (Regex)**
```java
public class ValidateEmail {
    public static void main(String[] args) {
        String email = "test@example.com";

        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        if (email.matches(regex)) {
            System.out.println("Valid Email");
        } else {
            System.out.println("Invalid Email");
        }
    }
}
// Output: Valid Email
```

### ✅ **19. Check if Two Strings Are Rotations of Each Other**
```java
public class StringRotation {
    public static void main(String[] args) {
        String s1 = "ABCD";
        String s2 = "CDAB";

        if (s1.length() != s2.length()) {
            System.out.println("Not Rotation");
            return;
        }

        String temp = s1 + s1;

        if (temp.contains(s2)) {
            System.out.println("Strings are rotation of each other");
        } else {
            System.out.println("Not Rotation");
        }
    }
}
// Output: Strings are rotation of each other
```

### ✅ **20. Longest Palindromic Substring**
```java
public class LongestPalindrome {
    
    public static String longestPalindrome(String s) {
        if (s == null || s.length() < 1)
            return "";

        int start = 0, end = 0;

        for (int i = 0; i < s.length(); i++) {
            int len1 = expand(s, i, i);       // odd
            int len2 = expand(s, i, i + 1);   // even
            int len = Math.max(len1, len2);

            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }

        return s.substring(start, end + 1);
    }

    private static int expand(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1; // palindrome length
    }

    public static void main(String[] args) {
        String str = "babad";
        System.out.println("Longest Palindrome: " + longestPalindrome(str));
    }
}

// Output: Longest Palindrome: bab
```

## Number Operations

### ✅ **21. Swap Two Numbers Using the Third Variable**
```java
public class SwapWithThird {
    public static void main(String[] args) {
        int a = 10, b = 20;

        int temp = a;
        a = b;
        b = temp;

        System.out.println("a = " + a + ", b = "b);
    }
}

// ✔ Output: a = 20, b = 10
```

### ✅ **22. Swap Two Numbers Without Using Third Variable**
```java
public class SwapWithoutThird {
    public static void main(String[] args) {
        int a = 10, b = 20;

        a = a + b;
        b = a - b;
        a = a - b;

        System.out.println("a = " + a + ", b = "b);
    }
}

// Output:a = 20, b = 10
```

### ✅ **23. Find Whether a Number is Prime or Not**
```java
public class PrimeCheck {
    public static void main(String[] args) {
        int n = 29;
        boolean isPrime = true;

        if (n <= 1) {
            isPrime = false;
        } else {
            for (int i = 2; i <= Math.sqrt(n); i++) {
                if (n % i == 0) {
                    isPrime = false;
                    break;
                }
            }
        }

        System.out.println(n + " is prime? " + isPrime);
    }
}
// Output: 29 is prime? true
```

### ✅ **24. Find Whether a Number is Palindrome or Not**
```java
public class PalindromeNumber {
    public static void main(String[] args) {
        int num = 121, temp = num;
        int rev = 0;

        while (temp > 0) {
            rev = rev * 10 + temp % 10;
            temp = temp / 10;
        }

        if (num == rev)
            System.out.println("Palindrome Number");
        else
            System.out.println("Not Palindrome");
    }
}
// Output: Palindrome Number
```

### ✅ **25. Fibonacci Series**
```java
public class FibonacciSeries {
    public static void main(String[] args) {
        int n = 10;
        int a = 0, b = 1;

        System.out.print(a + " " + b + " ");

        for (int i = 2; i < n; i++) {
            int c = a + b;
            System.out.print(c + " ");
            a = b;
            b = c;
        }
    }
}

// Output: 0 1 1 2 3 5 8 13 21 34
```

### ✅ **26. Find the Factorial of a Number**

```java
public class Factorial {
    public static void main(String[] args) {
        int n = 5;
        long fact = 1;

        for (int i = 1; i <= n; i++) {
            fact *= i;
        }

        System.out.println("Factorial = " + fact);
    }
}
// Output: Factorial = 120
```

### ✅ **27. Check if a Number is Even or Odd**
```java
public class EvenOdd {
    public static void main(String[] args) {
        int n = 7;

        if (n % 2 == 0)
            System.out.println("Even Number");
        else
            System.out.println("Odd Number");
    }
}
// Output: Odd Number
```

### ✅ **28. Find the Sum of Digits of a Number**
```java
public class SumOfDigits {
    public static void main(String[] args) {
        int num = 987;
        int sum = 0;

        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }

        System.out.println("Sum of digits = " + sum);
    }
}
// Output: Sum of digits = 24
```

### ✅ **29. Print the Multiplication Table of a Number**
```java
public class MultiplicationTable {
    public static void main(String[] args) {
        int n = 5;

        for (int i = 1; i <= 10; i++) {
            System.out.println(n + " x " + i + " = " + (n * i));
        }
    }
}

// Output: 
// 5 x 1 = 5  
// 5 x 2 = 10  
// 5 x 3 = 15  
```

### ✅ **30. Check Armstrong Number**
```java
public class Armstrong {
    public static void main(String[] args) {
        int num = 153;
        int temp = num;
        int sum = 0;

        while (temp > 0) {
            int digit = temp % 10;
            sum += digit * digit * digit;  // cube
            temp /= 10;
        }

        if (sum == num)
            System.out.println("Armstrong Number");
        else
            System.out.println("Not Armstrong");
    }
}

// Output: Armstrong Number
```

### ✅ **31. Print Prime Numbers Between 1 and 100**
```java
public class PrimeBetween1To100 {
    public static void main(String[] args) {
        for (int num = 2; num <= 100; num++) {
            boolean isPrime = true;

            for (int i = 2; i * i <= num; i++) {
                if (num % i == 0) {
                    isPrime = false;
                    break;
                }
            }

            if (isPrime) {
                System.out.print(num + " ");
            }
        }
    }
}
// Output: 2 3 5 7 11 13 17 19 23 29 ...
```


### ✅ **32. Reverse an Integer Number**
```java
public class ReverseInteger {
    public static void main(String[] args) {
        int num = 12345;
        int reversed = 0;

        while (num != 0) {
            int digit = num % 10;
            reversed = reversed * 10 + digit;
            num /= 10;
        }

        System.out.println("Reversed Number = " + reversed);
    }
}
// Output: Reversed Number = 54321
```

### ✅ **33. Convert String to Integer**

```java
public class StringToInt {
    public static void main(String[] args) {
        String str = "1234";
        int num = Integer.parseInt(str);

        System.out.println("Converted Integer = " + num);
    }
}
// Output: Converted Integer = 1234
```

### ✅ **34. Find the Sum of Natural Numbers**
```java
public class SumNaturalNumbers {
    public static void main(String[] args) {
        int n = 10;
        int sum = n * (n + 1) / 2;

        System.out.println("Sum = " + sum);
    }
}
// Output: Sum = 55
```

### ✅ **35. Check if a Number is a Perfect Number**
```java
public class PerfectNumber {
    public static void main(String[] args) {
        int num = 28;
        int sum = 0;

        for (int i = 1; i <= num / 2; i++) {
            if (num % i == 0) {
                sum += i;
            }
        }

        if (sum == num)
            System.out.println("Perfect Number");
        else
            System.out.println("Not Perfect Number");
    }
}
// Output: Perfect Number
```

### ✅ **36. Find the GCD (Greatest Common Divisor) of Two Numbers**

### ✔ Using Euclidean Algorithm

```java
public class GCD {
    public static int findGCD(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static void main(String[] args) {
        int a = 48, b = 18;
        System.out.println("GCD: " + findGCD(a, b));
    }
}

// Output:
```

### ✅ **37. Find the LCM (Least Common Multiple) of Two Numbers**

### ✔ Relation:

**LCM(a, b) = (a × b) / GCD(a, b)**

```java
public class LCM {
    
    public static int findGCD(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static int findLCM(int a, int b) {
        return (a * b) / findGCD(a, b);
    }

    public static void main(String[] args) {
        int a = 12, b = 18;
        System.out.println("LCM: " + findLCM(a, b));
    }
}

// Output:
```

### ✅ **38. Calculate the Power of a Number**

### ✔ Using a loop

```java
public class Power {
    public static long power(int base, int exp) {
        long result = 1;
        for (int i = 1; i <= exp; i++) {
            result *= base;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(power(2, 5));  // 2⁵ = 32
    }
}

// Output:
```

### ✔ Using `Math.pow`

```java
public class PowerUsingMath {
    public static void main(String[] args) {
        double result = Math.pow(3, 4);
        System.out.println(result); // 3⁴ = 81
    }
}

// Output:
```

### ✅ **39. Check if a Number is a Perfect Square**

### ✔ Logic:

A number is a **perfect square** if
`sqrt(n) * sqrt(n) == n`

```java
public class PerfectSquare {
    public static boolean isPerfectSquare(int n) {
        if (n < 0) return false;

        int sqrt = (int) Math.sqrt(n);
        return sqrt * sqrt == n;
    }

    public static void main(String[] args) {
        int n = 49;
        System.out.println(n + " is perfect square? " + isPerfectSquare(n));
    }
}

// Output:
```

If you want, I can prepare:

✅ All 40 Java coding questions
✅ One combined PDF
✅ With explanations + optimized code

Just say **"Make PDF"** or **"Combine all answers"**.

Here are **clean and simple Java solutions** for problems **40–43**:

### ✅ **40. Check if a Number is a Fibonacci Number**

### ✔ Formula:

A number **N** is Fibonacci if and only if one or both of these are perfect squares:

* `5*N*N + 4`
* `5*N*N – 4`

```java
public class FibonacciCheck {

    // method to check perfect square
    static boolean isPerfectSquare(int n) {
        int s = (int) Math.sqrt(n);
        return s * s == n;
    }

    // method to check fibonacci
    static boolean isFibonacci(int n) {
        return isPerfectSquare(5 * n * n + 4) ||
               isPerfectSquare(5 * n * n - 4);
    }

    public static void main(String[] args) {
        int n = 34;
        System.out.println(n + " is Fibonacci? " + isFibonacci(n));
    }
}

// Output:
```

### ✅ **41. Convert String to Integer Without parseInt()**

### ✔ Logic:

Convert each character digit → subtract `'0'` → build number.

```java
public class StringToInt {

    public static int convert(String str) {
        int num = 0;
        boolean negative = false;

        if (str.charAt(0) == '-') {
            negative = true;
            str = str.substring(1);
        }

        for (char c : str.toCharArray()) {
            num = num * 10 + (c - '0');
        }
        
        return negative ? -num : num;
    }

    public static void main(String[] args) {
        System.out.println(convert("1234")); 
        System.out.println(convert("-567"));
    }
}

// Output:
```

### ✅ **42. Convert Integer to String Without toString()**

### ✔ Logic:

Extract digits → build reverse → reverse it back.

```java
public class IntToString {

    public static String convert(int num) {
        boolean negative = false;
        if (num < 0) {
            negative = true;
            num = -num;
        }

        StringBuilder sb = new StringBuilder();

        while (num > 0) {
            int digit = num % 10;
            sb.append((char) ('0' + digit));
            num /= 10;
        }

        if (negative) sb.append('-');

        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(convert(1234)); 
        System.out.println(convert(-987));
    }
}

// Output:
```

### ✅ **43. Find All Prime Numbers up to N**

### ✔ Simple loop-based method

```java
public class PrimeUpToN {

    public static boolean isPrime(int n) {
        if (n <= 1) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public static void printPrimes(int n) {
        for (int i = 2; i <= n; i++) {
            if (isPrime(i)) {
                System.out.print(i + " ");
            }
        }
    }

    public static void main(String[] args) {
        printPrimes(50);
    }
}

// Output:
```

## Array Operations

### ✅ **44. Find the Largest Element in an Array**

```java
public class LargestInArray {

    public static int findLargest(int[] arr) {
        int max = arr[0];
        for (int num : arr) {
            if (num > max) max = num;
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = {10, 25, 3, 47, 5};
        System.out.println("Largest: " + findLargest(arr));
    }
}

// Output:
```

### ✅ **45. Find the Smallest Element in an Array**

```java
public class SmallestInArray {

    public static int findSmallest(int[] arr) {
        int min = arr[0];
        for (int num : arr) {
            if (num < min) min = num;
        }
        return min;
    }

    public static void main(String[] args) {
        int[] arr = {10, 25, 3, 47, 5};
        System.out.println("Smallest: " + findSmallest(arr));
    }
}

// Output:
```

### ✅ **46. Find the Second-Highest Number in an Array**

```java
public class SecondHighest {

    public static int findSecondHighest(int[] arr) {
        int max = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;

        for (int num : arr) {
            if (num > max) {
                second = max;
                max = num;
            } else if (num > second && num != max) {
                second = num;
            }
        }
        return second;
    }

    public static void main(String[] args) {
        int[] arr = {10, 50, 30, 50, 20};
        System.out.println("Second Highest: " + findSecondHighest(arr));
    }
}

// Output:
```

### ✅ **47. Merge Two Arrays**

```java
import java.util.Arrays;

public class MergeArrays {

    public static int[] merge(int[] arr1, int[] arr2) {
        int[] merged = new int[arr1.length + arr2.length];

        int index = 0;
        for (int num : arr1) merged[index++] = num;
        for (int num : arr2) merged[index++] = num;

        return merged;
    }

    public static void main(String[] args) {
        int[] a = {1, 3, 5};
        int[] b = {2, 4, 6};

        System.out.println("Merged: " + Arrays.toString(merge(a, b)));
    }
}

// Output:
```

If you want the next set of Java coding questions (48–60), just say **"next"**.


Here are **clean, simple, and interview-friendly Java solutions** for questions **48–54**:

### ✅ **48. Sort an Array of Integers in Ascending Order**

```java
import java.util.Arrays;

public class SortArray {

    public static void main(String[] args) {
        int[] arr = {5, 2, 9, 1, 3};
        Arrays.sort(arr);
        System.out.println("Sorted Array: " + Arrays.toString(arr));
    }
}

// Output:
```

### ✅ **49. Sum All Elements of an Array**

```java
public class SumArray {

    public static int sum(int[] arr) {
        int total = 0;
        for (int num : arr) total += num;
        return total;
    }

    public static void main(String[] args) {
        int[] arr = {5, 2, 9, 1, 3};
        System.out.println("Sum: " + sum(arr));
    }
}

// Output:
```

### ✅ **50. Find the Median of an Array**

```java
import java.util.Arrays;

public class MedianArray {

    public static double findMedian(int[] arr) {
        Arrays.sort(arr);
        int n = arr.length;
        
        if (n % 2 == 1) return arr[n/2];
        else return (arr[n/2 - 1] + arr[n/2]) / 2.0;
    }

    public static void main(String[] args) {
        int[] arr = {7, 3, 1, 4};
        System.out.println("Median: " + findMedian(arr));
    }
}

// Output:
```

### ✅ **51. Remove Duplicates from an Array**

Using **LinkedHashSet** (keeps order):

```java
import java.util.*;

public class RemoveDuplicates {

    public static int[] removeDuplicates(int[] arr) {
        Set<Integer> set = new LinkedHashSet<>();
        for (int num : arr) set.add(num);

        int[] result = new int[set.size()];
        int i = 0;
        for (int num : set) result[i++] = num;

        return result;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 3, 4, 4, 5};
        System.out.println(Arrays.toString(removeDuplicates(arr)));
    }
}

// Output:
```

### ✅ **52. Convert an Array to a String**

```java
import java.util.Arrays;

public class ArrayToString {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};
        String str = Arrays.toString(arr);
        System.out.println("Array as String: " + str);
    }
}

// Output:
```

### ✅ **53. Find the Common Elements Between Two Arrays**

```java
import java.util.*;

public class CommonElements {

    public static Set<Integer> findCommon(int[] a, int[] b) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> common = new HashSet<>();

        for (int num : a) set1.add(num);
        for (int num : b) if (set1.contains(num)) common.add(num);

        return common;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4};
        int[] arr2 = {3, 4, 5, 6};

        System.out.println("Common Elements: " + findCommon(arr1, arr2));
    }
}

// Output:
```

### ✅ **54. Find Min and Max in Array (Single Loop)**

```java
public class MinMaxSingleLoop {

    public static void main(String[] args) {
        int[] arr = {8, 3, 10, 1, 6};

        int min = arr[0];
        int max = arr[0];

        for (int num : arr) {
            if (num < min) min = num;
            if (num > max) max = num;
        }

        System.out.println("Min: " + min);
        System.out.println("Max: " + max);
    }
}

// Output:
```

Here are **clean, simple, and interview-friendly Java solutions** for questions **48–54**:

### ✅ **48. Sort an Array of Integers in Ascending Order**

```java
import java.util.Arrays;

public class SortArray {

    public static void main(String[] args) {
        int[] arr = {5, 2, 9, 1, 3};
        Arrays.sort(arr);
        System.out.println("Sorted Array: " + Arrays.toString(arr));
    }
}

// Output:
```

### ✅ **49. Sum All Elements of an Array**

```java
public class SumArray {

    public static int sum(int[] arr) {
        int total = 0;
        for (int num : arr) total += num;
        return total;
    }

    public static void main(String[] args) {
        int[] arr = {5, 2, 9, 1, 3};
        System.out.println("Sum: " + sum(arr));
    }
}


// Output:
```

### ✅ **50. Find the Median of an Array**

```java
import java.util.Arrays;

public class MedianArray {

    public static double findMedian(int[] arr) {
        Arrays.sort(arr);
        int n = arr.length;
        
        if (n % 2 == 1) return arr[n/2];
        else return (arr[n/2 - 1] + arr[n/2]) / 2.0;
    }

    public static void main(String[] args) {
        int[] arr = {7, 3, 1, 4};
        System.out.println("Median: " + findMedian(arr));
    }
}

// Output:
```

### ✅ **51. Remove Duplicates from an Array**

Using **LinkedHashSet** (keeps order):

```java
import java.util.*;

public class RemoveDuplicates {

    public static int[] removeDuplicates(int[] arr) {
        Set<Integer> set = new LinkedHashSet<>();
        for (int num : arr) set.add(num);

        int[] result = new int[set.size()];
        int i = 0;
        for (int num : set) result[i++] = num;

        return result;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 3, 4, 4, 5};
        System.out.println(Arrays.toString(removeDuplicates(arr)));
    }
}
```

### ✅ **52. Convert an Array to a String**

```java
import java.util.Arrays;

public class ArrayToString {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};
        String str = Arrays.toString(arr);
        System.out.println("Array as String: " + str);
    }
}

// Output:
```

### ✅ **53. Find the Common Elements Between Two Arrays**

```java
import java.util.*;

public class CommonElements {

    public static Set<Integer> findCommon(int[] a, int[] b) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> common = new HashSet<>();

        for (int num : a) set1.add(num);
        for (int num : b) if (set1.contains(num)) common.add(num);

        return common;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4};
        int[] arr2 = {3, 4, 5, 6};

        System.out.println("Common Elements: " + findCommon(arr1, arr2));
    }
}

// Output:
```

### ✅ **54. Find Min and Max in Array (Single Loop)**

```java
public class MinMaxSingleLoop {

    public static void main(String[] args) {
        int[] arr = {8, 3, 10, 1, 6};

        int min = arr[0];
        int max = arr[0];

        for (int num : arr) {
            if (num < min) min = num;
            if (num > max) max = num;
        }

        System.out.println("Min: " + min);
        System.out.println("Max: " + max);
    }
}

// Output:
```

### ✅ **55. Find Even & Odd Numbers in Array**

```java
public class EvenOddArray {

    public static void main(String[] args) {
        int[] arr = {2, 5, 8, 11, 14};

        System.out.print("Even: ");
        for (int n : arr) if (n % 2 == 0) System.out.print(n + " ");

        System.out.print("\nOdd: ");
        for (int n : arr) if (n % 2 != 0) System.out.print(n + " ");
    }
}
```

### ✅ **56. Merge Two Sorted Arrays Without Using Extra Space**

This is the **Gap Method** (like Shell Sort).

```java
import java.util.Arrays;

public class MergeSortedArrays {

    static int nextGap(int gap) {
        if (gap <= 1) return 0;
        return (gap / 2) + (gap % 2);
    }

    public static void merge(int[] a, int[] b) {
        int n = a.length, m = b.length;
        int gap = nextGap(n + m);

        while (gap > 0) {

            int i, j;

            // Compare elements in first array
            for (i = 0; i + gap < n; i++) {
                if (a[i] > a[i + gap]) {
                    int temp = a[i];
                    a[i] = a[i + gap];
                    a[i + gap] = temp;
                }
            }

            // Compare elements between arrays
            for (j = gap > n ? gap - n : 0; i < n && j < m; i++, j++) {
                if (a[i] > b[j]) {
                    int temp = a[i];
                    a[i] = b[j];
                    b[j] = temp;
                }
            }

            // Compare elements in second array
            for (j = j; j + gap < m; j++) {
                if (b[j] > b[j + gap]) {
                    int temp = b[j];
                    b[j] = b[j + gap];
                    b[j + gap] = temp;
                }
            }

            gap = nextGap(gap);
        }
    }

    public static void main(String[] args) {
        int[] a = {1, 4, 7, 8};
        int[] b = {2, 3, 9};

        merge(a, b);
        System.out.println("A: " + Arrays.toString(a));
        System.out.println("B: " + Arrays.toString(b));
    }
}
```

### ✅ **57. Find Majority Element (Boyer–Moore Algorithm)**

Time: O(n), Space: O(1)

```java
public class MajorityElement {

    public static int findMajority(int[] nums) {
        int count = 0, candidate = 0;

        for (int n : nums) {
            if (count == 0) candidate = n;
            count += (n == candidate) ? 1 : -1;
        }

        return candidate; // Assumes majority element always exists
    }

    public static void main(String[] args) {
        int[] arr = {2, 2, 1, 1, 2, 2, 2};
        System.out.println("Majority Element: " + findMajority(arr));
    }
}
```

### ✅ **58. Rotate Array Left/Right by K Positions**

### Rotate Right (Optimized)

```java
import java.util.Arrays;

public class RotateArray {

    public static void reverse(int[] arr, int left, int right) {
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }

    public static void rotateRight(int[] arr, int k) {
        k = k % arr.length;

        reverse(arr, 0, arr.length - 1);
        reverse(arr, 0, k - 1);
        reverse(arr, k, arr.length - 1);
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        rotateRight(arr, 2);
        System.out.println("Rotated Right: " + Arrays.toString(arr));
    }
}
```

### ✅ **59. Find All Subarrays With Given Sum (Sliding Window)**

Works for **positive numbers**.

```java
public class SubarraySum {

    public static void findSubarrays(int[] arr, int target) {
        int left = 0, sum = 0;

        for (int right = 0; right < arr.length; right++) {
            sum += arr[right];

            while (sum > target) {
                sum -= arr[left];
                left++;
            }

            if (sum == target) {
                System.out.println("Subarray: [" + left + ", " + right + "]");
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 7, 5};
        findSubarrays(arr, 12);
    }
}
```

### ✅ **60. Top K Frequent Elements (HashMap + PriorityQueue)**

```java
import java.util.*;

public class TopKFrequent {

    public static List<Integer> topK(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();

        for (int n : nums) freq.put(n, freq.getOrDefault(n, 0) + 1);

        PriorityQueue<Map.Entry<Integer, Integer>> heap =
            new PriorityQueue<>((a, b) -> a.getValue() - b.getValue());

        for (Map.Entry<Integer, Integer> e : freq.entrySet()) {
            heap.add(e);
            if (heap.size() > k) heap.poll();
        }

        List<Integer> result = new ArrayList<>();
        while (!heap.isEmpty()) result.add(heap.poll().getKey());

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1,1,1,2,2,3};
        System.out.println(topK(nums, 2));
    }
}
```

## Pattern Printing
### ✅ **61. Print a Triangle of Stars**

### **Input:** `n = 5`

### **Output:**

```
*
**
***
****
*****
```

### **Java Code**

```java
public class StarTriangle {
    public static void main(String[] args) {
        int n = 5;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}

// Output:
```

### ✅ **62. Create a Pattern of Numbers (1, 12, 123, 1234...)**

### **Input:** `n = 5`

### **Output:**

```
1
12
123
1234
1235
```

### **Java Code**

```java
public class NumberPattern {
    public static void main(String[] args) {
        int n = 5;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j);
            }
            System.out.println();
        }
    }
}

// Output:
```

### ✅ **63. Print Right-Aligned Triangle**

### **Input:** `n = 5`

### **Output:**

```
    *
   **
  ***
 ****
*****
```

### **Java Code**

```java
public class RightTrianglePattern {
    public static void main(String[] args) {
        int n = 5;

        for (int i = 1; i <= n; i++) {

            // Print spaces
            for (int s = 1; s <= n - i; s++) {
                System.out.print(" ");
            }

            // Print stars
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }

            System.out.println();
        }
    }
}

// Output:
```

## Collections (HashMap & ArrayList)
### ✅ **64. Iterate HashMap using While Loop and Advanced For Loop**

### **Java Code**

```java
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HashMapIteration {
    public static void main(String[] args) {

        HashMap<Integer, String> map = new HashMap<>();
        map.put(101, "Amit");
        map.put(102, "Rahul");
        map.put(103, "Sneha");

        System.out.println("---- Using While Loop (Iterator) ----");
        Iterator<Map.Entry<Integer, String>> itr = map.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry<Integer, String> entry = itr.next();
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        System.out.println("\n---- Using Advanced For Loop ----");
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}

// Output:
```

### ✅ **65. Iterate ArrayList Using For-loop, While-loop, and Advanced For-loop**

### **Java Code**

```java
import java.util.ArrayList;
import java.util.Iterator;

public class ArrayListIteration {
    public static void main(String[] args) {

        ArrayList<String> list = new ArrayList<>();
        list.add("Apple");
        list.add("Banana");
        list.add("Orange");

        System.out.println("---- Using Normal For Loop ----");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        System.out.println("\n---- Using While Loop ----");
        int index = 0;
        while (index < list.size()) {
            System.out.println(list.get(index));
            index++;
        }

        System.out.println("\n---- Using Advanced For Loop ----");
        for (String item : list) {
            System.out.println(item);
        }
    }
}

// Output:
```

## Data Structures

### ✅ **66. Implement a Stack Using an Array**

```java
class StackArray {
    private int[] arr;
    private int top;
    private int size;

    public StackArray(int size) {
        this.size = size;
        arr = new int[size];
        top = -1;
    }

    public void push(int x) {
        if (top == size - 1) {
            System.out.println("Stack Overflow");
            return;
        }
        arr[++top] = x;
    }

    public int pop() {
        if (top == -1) {
            System.out.println("Stack Underflow");
            return -1;
        }
        return arr[top--];
    }

    public int peek() {
        return (top == -1) ? -1 : arr[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }
}

// Output:
```

### ✅ **67. Implement Queue Using LinkedList**

```java
class QueueLL {
    private static class Node {
        int val;
        Node next;
        Node(int v) { val = v; }
    }

    Node front, rear;

    public void enqueue(int x) {
        Node n = new Node(x);
        if (rear != null) rear.next = n;
        else front = n;
        rear = n;
    }

    public int dequeue() {
        if (front == null) return -1;
        int val = front.val;
        front = front.next;
        if (front == null) rear = null;
        return val;
    }

    public boolean isEmpty() {
        return front == null;
    }
}

// Output:
```

### ✅ **68. Implement Binary Search (Iterative)**

```java
public class BinarySearch {
    public static int search(int[] arr, int target) {
        int left = 0, right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) return mid;
            else if (target > arr[mid]) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }
}

// Output:
```

### ✅ **69. Implement Binary Search Recursively**

```java
public class BinarySearchRecursive {
    public static int search(int[] arr, int target, int left, int right) {
        if (left > right) return -1;

        int mid = left + (right - left) / 2;

        if (arr[mid] == target) return mid;
        else if (target < arr[mid])
            return search(arr, target, left, mid - 1);
        else
            return search(arr, target, mid + 1, right);
    }
}

// Output:
```

### ✅ **70. Implement MinStack (getMin() in O(1))**

Maintain two stacks:
✔ `mainStack` → stores values
✔ `minStack` → stores minimum values

```java
import java.util.Stack;

class MinStack {
    Stack<Integer> stack = new Stack<>();
    Stack<Integer> minStack = new Stack<>();

    public void push(int x) {
        stack.push(x);
        if (minStack.isEmpty() || x <= minStack.peek()) {
            minStack.push(x);
        }
    }

    public int pop() {
        int removed = stack.pop();
        if (removed == minStack.peek()) {
            minStack.pop();
        }
        return removed;
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}

// Output:
```

### ✅ **71. Implement Trie (Insert / Search / StartsWith)**

```java
class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean endOfWord;
}

class Trie {
    TrieNode root = new TrieNode();

    public void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int i = c - 'a';
            if (node.children[i] == null)
                node.children[i] = new TrieNode();
            node = node.children[i];
        }
        node.endOfWord = true;
    }

    public boolean search(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int i = c - 'a';
            if (node.children[i] == null) return false;
            node = node.children[i];
        }
        return node.endOfWord;
    }

    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            int i = c - 'a';
            if (node.children[i] == null) return false;
            node = node.children[i];
        }
        return true;
    }
}

// Output:
```

### ✅ **72. Detect and Remove Loop in Linked List (Floyd’s Algorithm)**

### **Steps**

1. Use **slow** and **fast** pointers to detect a loop.
2. When they meet, reset slow to head.
3. Move both pointers one step to find loop start.
4. Break the loop.

### **Java Code**

```java
class LinkedListLoop {
    static class Node {
        int data;
        Node next;
        Node(int d) { data = d; }
    }

    Node head;

    // Detect & Remove Loop
    public void detectAndRemoveLoop() {
        Node slow = head, fast = head;

        // Detect loop
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) break; // loop found
        }

        if (slow != fast) return; // no loop

        // Reset slow to head
        slow = head;

        // Find loop start
        while (slow.next != fast.next) {
            slow = slow.next;
            fast = fast.next;
        }

        // Remove loop
        fast.next = null;
    }
}

// Output:
```

## Tree Operations
### ✅ **73. Serialize and Deserialize a Binary Tree**

```java
import java.util.*;

// Definition for binary tree
class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int x) { val = x; }
}

class Codec {
    // Serialize tree to string
    public String serialize(TreeNode root) {
        if (root == null) return "#";
        return root.val + "," + serialize(root.left) + "," + serialize(root.right);
    }

    // Deserialize string to tree
    public TreeNode deserialize(String data) {
        Queue<String> q = new LinkedList<>(Arrays.asList(data.split(",")));
        return buildTree(q);
    }

    private TreeNode buildTree(Queue<String> q) {
        String val = q.poll();
        if (val.equals("#")) return null;
        TreeNode node = new TreeNode(Integer.parseInt(val));
        node.left = buildTree(q);
        node.right = buildTree(q);
        return node;
    }
}
```

**Explanation:**

* `#` represents `null`.
* Preorder traversal is used for serialization and deserialization.

### ✅ **74. Find the Diameter of a Binary Tree**

```java
class TreeNodeDiameter {
    int val;
    TreeNodeDiameter left, right;
    TreeNodeDiameter(int x) { val = x; }
}

class BinaryTree {
    int maxDiameter = 0;

    public int diameter(TreeNodeDiameter root) {
        height(root);
        return maxDiameter;
    }

    private int height(TreeNodeDiameter node) {
        if (node == null) return 0;
        int left = height(node.left);
        int right = height(node.right);

        // Update max diameter at this node
        maxDiameter = Math.max(maxDiameter, left + right);
        return 1 + Math.max(left, right);
    }
}

// Output:
```

**Explanation:**

* Diameter = longest path between any two nodes.
* Recursively calculate height and update diameter at each node.

## Graph Operations
### ✅ **75. Find Number of Islands (DFS)**

```java
class NumberOfIslands {
    public int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private void dfs(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == '0') return;
        grid[i][j] = '0'; // mark visited
        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);
    }
}

// Output:
```

**Explanation:**

* Traverse grid, start DFS on each '1', mark connected land as '0'.
* Each DFS represents one island.

## Multithreading
### ✅ **76. Producer–Consumer Problem (Threads + wait/notify)**

```java
import java.util.*;

class ProducerConsumer {
    private final Queue<Integer> buffer = new LinkedList<>();
    private final int capacity = 5;

    public void produce() throws InterruptedException {
        int value = 0;
        while (true) {
            synchronized (buffer) {
                while (buffer.size() == capacity) buffer.wait();
                buffer.add(value);
                System.out.println("Produced: " + value);
                value++;
                buffer.notify();
            }
            Thread.sleep(500);
        }
    }

    public void consume() throws InterruptedException {
        while (true) {
            synchronized (buffer) {
                while (buffer.isEmpty()) buffer.wait();
                int val = buffer.poll();
                System.out.println("Consumed: " + val);
                buffer.notify();
            }
            Thread.sleep(1000);
        }
    }

    public static void main(String[] args) {
        ProducerConsumer pc = new ProducerConsumer();

        Thread producer = new Thread(() -> {
            try { pc.produce(); } catch (InterruptedException e) {}
        });

        Thread consumer = new Thread(() -> {
            try { pc.consume(); } catch (InterruptedException e) {}
        });

        producer.start();
        consumer.start();
    }
}

// Output:
// Produced: 0
```

**Explanation:**

* `buffer` = shared queue.
* `wait()` → thread sleeps until notified.
* `notify()` → wakes up waiting thread.
* Ensures thread-safe production and consumption.

I can continue with **next set of advanced Java problems**:

* Graph algorithms (BFS, DFS, Dijkstra)
* Dynamic Programming questions
* More multithreading problems

Do you want me to continue with that?


Here are 50 Java coding questions based on various topics that will help you practice and sharpen your Java programming skills:

Here are the Java programs for the problems you've provided:

### 1. Reverse a String without using String inbuilt function
```java
public class ReverseString {
    public static void main(String[] args) {
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
}
```

### 2. Reverse a String without using String inbuilt function reverse()
```java
public class ReverseStringNoReverse {
    public static void main(String[] args) {
        String str = "HelloWorld";
        char[] strArray = str.toCharArray();
        String reversedStr = "";

        for (int i = strArray.length - 1; i >= 0; i--) {
            reversedStr += strArray[i];
        }

        System.out.println("Reversed string: " + reversedStr);
    }
}
```

### 3. Swap Two Numbers Using the Third Variable
```java
public class SwapWithThirdVariable {
    public static void main(String[] args) {
        int a = 5, b = 10;

        System.out.println("Before swapping: a = " + a + ", b = " + b);

        // Using third variable
        int temp = a;
        a = b;
        b = temp;

        System.out.println("After swapping: a = " + a + ", b = " + b);
    }
}
```

### 4. Swap Two Numbers Without Using the Third Variable
```java
public class SwapWithoutThirdVariable {
    public static void main(String[] args) {
        int a = 5, b = 10;

        System.out.println("Before swapping: a = " + a + ", b = " + b);

        // Without using third variable
        a = a + b; // a becomes 15
        b = a - b; // b becomes 5
        a = a - b; // a becomes 10

        System.out.println("After swapping: a = " + a + ", b = " + b);
    }
}
```

### 5. Count the Number of Words in a String Using HashMap
```java
import java.util.HashMap;

public class CountWordsUsingHashMap {
    public static void main(String[] args) {
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
}
```

### Explanation:
1. **Reverse String without inbuilt function**: The first program reverses a string by converting it into a character array and then swapping characters from both ends until they meet in the middle.
  
2. **Reverse String without `reverse()` method**: The second program uses a loop to reverse the string by concatenating the characters from the end to the start.

3. **Swap Two Numbers using Third Variable**: The third program swaps two integers by using a temporary variable to hold one value during the swap process.

4. **Swap Two Numbers without Third Variable**: The fourth program swaps two numbers without using a third variable by performing arithmetic operations.

5. **Count Words Using HashMap**: The fifth program splits the string into words and counts the occurrences of each word using a `HashMap`, where the key is the word and the value is its count.

Here are the Java programs for the given problems:

### 6. Iterate HashMap using While and Advanced For Loop
```java
import java.util.HashMap;
import java.util.Map;

public class IterateHashMap {
    public static void main(String[] args) {
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
}
```

### 7. Find Whether a Number is Prime or Not
```java
public class PrimeNumber {
    public static void main(String[] args) {
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
}
```

### 8. Find Whether a String or Number is Palindrome or Not
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
```

### 9. Fibonacci Series
```java
public class FibonacciSeries {
    public static void main(String[] args) {
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
}
```

### 10. Iterate ArrayList Using For-loop, While-loop, and Advanced For-loop
```java
import java.util.ArrayList;

public class IterateArrayList {
    public static void main(String[] args) {
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
}
```

### Explanation:
1. **Iterate HashMap using While and Advanced For Loop**: The program demonstrates two ways to iterate through a `HashMap`: using a `while` loop with an iterator and using an enhanced `for` loop (for-each loop).
  
2. **Find Whether a Number is Prime or Not**: The program checks if a number is prime by checking divisibility starting from 2 up to the square root of the number.

3. **Find Whether a String or Number is Palindrome or Not**: The program checks if a string is a palindrome by comparing characters from the start and end, and it also checks if a number is a palindrome by reversing the digits and comparing it with the original number.

4. **Fibonacci Series**: The program generates the Fibonacci series up to `n` terms. Each term is the sum of the previous two terms.

5. **Iterate ArrayList Using For-loop, While-loop, and Advanced For-loop**: This program demonstrates how to iterate through an `ArrayList` using a regular `for` loop, a `while` loop, and an advanced `for` loop (for-each loop).


Here are the Java programs for the given problems:

### 11. Find the Duplicate Characters in a String
```java
import java.util.HashMap;

public class DuplicateCharacters {
    public static void main(String[] args) {
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
}
```

### 12. Find the Second-Highest Number in an Array
```java
public class SecondHighestNumber {
    public static void main(String[] args) {
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
}
```

### 13. Check Armstrong Number
```java
public class ArmstrongNumber {
    public static void main(String[] args) {
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
}
```

### 14. Remove All White Spaces from a String Using `replace()`
```java
public class RemoveWhiteSpacesWithReplace {
    public static void main(String[] args) {
        String str = "   Hello World!   ";
        String result = str.replace(" ", "");

        System.out.println("String after removing white spaces: '" + result + "'");
    }
}
```

### 15. Remove All White Spaces from a String Without Using `replace()`
```java
public class RemoveWhiteSpacesWithoutReplace {
    public static void main(String[] args) {
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
}
```

### Explanation:
1. **Find Duplicate Characters in a String**: The program counts the frequency of each character in the string using a `HashMap` and prints characters with a count greater than 1.
   
2. **Find the Second-Highest Number in an Array**: The program iterates through the array, maintaining the highest and second-highest numbers found. It prints the second-highest value after the loop.

3. **Check Armstrong Number**: The program checks whether a number is an Armstrong number by checking if the sum of each digit raised to the power of the number of digits equals the original number.

4. **Remove White Spaces from a String Using `replace()`**: The program uses the `replace()` method to remove all spaces in the string.

5. **Remove White Spaces from a String Without Using `replace()`**: The program uses a `StringBuilder` to construct a new string by iterating through the original string and appending characters that are not spaces.


Here are the Java programs for the problems you've provided:

### 16. Find the Factorial of a Number
```java
public class Factorial {
    public static void main(String[] args) {
        int number = 5;  // Example number
        long factorial = 1;

        // Loop to calculate factorial
        for (int i = 1; i <= number; i++) {
            factorial *= i;
        }

        System.out.println("Factorial of " + number + " is: " + factorial);
    }
}
```

### 17. Check if a Number is Even or Odd
```java
public class EvenOdd {
    public static void main(String[] args) {
        int number = 7;  // Example number

        // Check if the number is even or odd
        if (number % 2 == 0) {
            System.out.println(number + " is an even number.");
        } else {
            System.out.println(number + " is an odd number.");
        }
    }
}
```

### 18. Find the Sum of Digits of a Number
```java
public class SumOfDigits {
    public static void main(String[] args) {
        int number = 1234;  // Example number
        int sum = 0;

        // Loop to find the sum of digits
        while (number != 0) {
            sum += number % 10;  // Add last digit
            number /= 10;         // Remove last digit
        }

        System.out.println("Sum of digits: " + sum);
    }
}
```

### 19. Print the Multiplication Table of a Number
```java
public class MultiplicationTable {
    public static void main(String[] args) {
        int number = 5;  // Example number
        int limit = 10;   // Table limit (multiplying by numbers 1 to 10)

        System.out.println("Multiplication Table of " + number + ":");
        for (int i = 1; i <= limit; i++) {
            System.out.println(number + " x " + i + " = " + (number * i));
        }
    }
}
```

### 20. Find the Largest Element in an Array
```java
public class LargestElement {
    public static void main(String[] args) {
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
}
```

### Explanation:
1. **Factorial of a Number**: The program calculates the factorial of a given number using a loop. The result is stored in the `factorial` variable and displayed.
   
2. **Check if a Number is Even or Odd**: The program checks if a number is divisible by 2 (`number % 2 == 0`) to determine if it's even or odd.

3. **Sum of Digits of a Number**: The program calculates the sum of the digits of a given number by extracting each digit (using modulus operation) and adding it to the sum.

4. **Multiplication Table of a Number**: The program generates the multiplication table for a given number by multiplying it with values from 1 to 10 and printing the result.

5. **Largest Element in an Array**: The program iterates through the array and compares each element to find the largest one.



Here are the Java programs for the problems you've provided:

### 21. Find the Smallest Element in an Array
```java
public class SmallestElement {
    public static void main(String[] args) {
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
}
```

### 22. Count Vowels and Consonants in a String
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
```

### 23. Check if Two Strings are Anagrams
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
```

### 24. Print the Prime Numbers Between 1 and 100
```java
public class PrimeNumbers {
    public static void main(String[] args) {
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
}
```

### 25. Reverse an Integer Number
```java
public class ReverseInteger {
    public static void main(String[] args) {
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
}
```

### Explanation:
1. **Find the Smallest Element in an Array**: The program iterates through the array and compares each element to find the smallest one.

2. **Count Vowels and Consonants in a String**: The program converts the string to lowercase for case-insensitive comparison and counts the vowels and consonants by checking each character. It ensures only alphabetic characters are considered.

3. **Check if Two Strings are Anagrams**: The program converts both strings to character arrays, sorts them, and then compares the sorted arrays. If the arrays are identical, the strings are anagrams.

4. **Print the Prime Numbers Between 1 and 100**: The program loops through numbers from 2 to 100, checks if each number is prime by dividing it by numbers up to its square root, and prints the prime numbers.

5. **Reverse an Integer Number**: The program reverses an integer by extracting its digits using the modulus operator (`%`) and reconstructing the number by appending each digit to the reversed value.



Here are the Java programs for the problems you've provided:

### 26. Convert a String to an Integer
```java
public class StringToInteger {
    public static void main(String[] args) {
        String str = "12345";  // Example string
        int number = Integer.parseInt(str);  // Convert string to integer

        System.out.println("The integer value is: " + number);
    }
}
```

### 27. Print a Triangle of Stars
```java
public class StarTriangle {
    public static void main(String[] args) {
        int rows = 5;  // Number of rows in the triangle

        // Loop to print the triangle
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("* ");  // Print stars
            }
            System.out.println();  // Move to the next line after each row
        }
    }
}
```

### 28. Find the Sum of Natural Numbers
```java
public class SumOfNaturalNumbers {
    public static void main(String[] args) {
        int n = 10;  // Example: sum of first 10 natural numbers
        int sum = 0;

        // Calculate the sum of natural numbers
        for (int i = 1; i <= n; i++) {
            sum += i;
        }

        System.out.println("Sum of first " + n + " natural numbers is: " + sum);
    }
}
```

### 29. Check if a Number is a Perfect Number
```java
public class PerfectNumber {
    public static void main(String[] args) {
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
}
```

### 30. Find the GCD (Greatest Common Divisor) of Two Numbers
```java
public class GCD {
    public static void main(String[] args) {
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
}
```

### Explanation:
1. **Convert a String to an Integer**: The program uses `Integer.parseInt()` to convert a string representing a number into an integer.
   
2. **Print a Triangle of Stars**: The program uses nested loops to print a triangle of stars (`*`). The outer loop controls the rows, and the inner loop prints the stars in each row.

3. **Sum of Natural Numbers**: The program calculates the sum of the first `n` natural numbers using a loop. In the example, it sums numbers from 1 to 10.

4. **Check if a Number is a Perfect Number**: A perfect number is one that equals the sum of its proper divisors. The program checks if the sum of divisors equals the number itself.

5. **Find the GCD of Two Numbers**: The program uses the Euclidean algorithm to calculate the GCD. It repeatedly subtracts the smaller number from the larger one until the numbers are equal, which will be the GCD.


Here are the Java programs for the problems you've provided:

### 31. Find the LCM (Least Common Multiple) of Two Numbers
```java
public class LCM {
    public static void main(String[] args) {
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
```

### 32. Calculate the Power of a Number
```java
public class PowerOfNumber {
    public static void main(String[] args) {
        int base = 2;  // Base number
        int exponent = 5;  // Exponent

        long result = 1;
        
        // Loop to calculate power
        for (int i = 1; i <= exponent; i++) {
            result *= base;
        }

        System.out.println(base + " raised to the power " + exponent + " is: " + result);
    }
}
```

### 33. Count the Number of Occurrences of a Character in a String
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

### 34. Merge Two Arrays
```java
import java.util.Arrays;

public class MergeArrays {
    public static void main(String[] args) {
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
}
```

### 35. Sort an Array of Integers in Ascending Order
```java
import java.util.Arrays;

public class SortArray {
    public static void main(String[] args) {
        int[] arr = {5, 2, 9, 1, 5, 6};  // Example array

        // Sort the array using Arrays.sort()
        Arrays.sort(arr);

        System.out.println("Sorted Array in Ascending Order: " + Arrays.toString(arr));
    }
}
```

### Explanation:
1. **LCM of Two Numbers**: The program calculates the Least Common Multiple (LCM) using the formula:  
   \[
   \text{LCM}(a, b) = \frac{a \times b}{\text{GCD}(a, b)}
   \]  
   The `findGCD` method uses the Euclidean algorithm to compute the Greatest Common Divisor (GCD), and then the LCM is calculated.

2. **Power of a Number**: The program calculates the power of a base number raised to an exponent using a loop. It multiplies the base number repeatedly for the number of times equal to the exponent.

3. **Count Occurrences of a Character in a String**: The program loops through the string and counts how many times the specified character (`targetChar`) appears in the string.

4. **Merge Two Arrays**: The program uses the `System.arraycopy()` method to merge two arrays into a new array. It copies the elements of both arrays into the new array.

5. **Sort an Array of Integers**: The program uses the `Arrays.sort()` method to sort the array in ascending order and then prints the sorted array.


Here are the Java programs for the problems you've provided:

### 36. Sum All Elements of an Array
```java
public class SumArray {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};  // Example array
        int sum = 0;

        // Loop through the array to sum all elements
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }

        System.out.println("Sum of all elements in the array: " + sum);
    }
}
```

### 37. Find the Median of an Array
```java
import java.util.Arrays;

public class MedianArray {
    public static void main(String[] args) {
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
}
```

### 38. Create a Pattern of Numbers (e.g., 1, 12, 123, etc.)
```java
public class NumberPattern {
    public static void main(String[] args) {
        int rows = 5;  // Number of rows in the pattern

        // Loop to create the pattern
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j);  // Print numbers from 1 to i
            }
            System.out.println();  // Move to the next line after each row
        }
    }
}
```

### 39. Check if a String Contains a Substring
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
```

### 40. Replace a Character in a String Without Using `String.replace()`
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

### Explanation:
1. **Sum All Elements of an Array**: The program iterates through the array and adds up all the elements to get the sum.

2. **Find the Median of an Array**: The program first sorts the array, and then checks if the array length is odd or even. If odd, it picks the middle element; if even, it calculates the average of the two middle elements.

3. **Create a Pattern of Numbers**: The program uses nested loops to generate a pattern of numbers, where the first row contains `1`, the second contains `12`, the third `123`, and so on.

4. **Check if a String Contains a Substring**: The program uses the `contains()` method to check if the string contains the specified substring.

5. **Replace a Character in a String Without Using `String.replace()`**: The program uses a `StringBuilder` to construct a new string by iterating over the original string and replacing the target character with the new character.

Here are the Java programs for the problems you've provided:

### 41. Remove Duplicates from an Array
```java
import java.util.Arrays;

public class RemoveDuplicates {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 4, 5, 5, 6};  // Example array
        arr = Arrays.stream(arr).distinct().toArray();  // Remove duplicates using Streams

        System.out.println("Array without duplicates: " + Arrays.toString(arr));
    }
}
```

### 42. Convert an Array to a String
```java
import java.util.Arrays;

public class ArrayToString {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};  // Example array
        String arrayString = Arrays.toString(arr);  // Convert array to string

        System.out.println("Array as String: " + arrayString);
    }
}
```

### 43. Swap Two Strings
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

### 44. Find the Longest Word in a Sentence
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

### 45. Check if a Number is a Perfect Square
```java
public class PerfectSquare {
    public static void main(String[] args) {
        int number = 25;  // Example number

        // Check if the square root of the number is an integer
        double sqrt = Math.sqrt(number);
        if (sqrt == (int) sqrt) {
            System.out.println(number + " is a perfect square.");
        } else {
            System.out.println(number + " is not a perfect square.");
        }
    }
}
```

### Explanation:
1. **Remove Duplicates from an Array**: The program uses Java Streams to convert the array to a stream, apply the `distinct()` method to remove duplicates, and then convert the stream back to an array.

2. **Convert an Array to a String**: The program uses `Arrays.toString()` to convert an array to a string representation, which is easy to display.

3. **Swap Two Strings**: This program swaps two strings using a temporary variable. The contents of `str1` and `str2` are exchanged.

4. **Find the Longest Word in a Sentence**: The program splits the sentence into words using the `split()` method and iterates through the array of words to find the longest one.

5. **Check if a Number is a Perfect Square**: The program uses `Math.sqrt()` to calculate the square root of the number. It checks if the square root is an integer by comparing it to its integer cast.


Here are the Java programs for the problems you've provided:

### 46. Implement Binary Search
```java
public class BinarySearch {
    public static void main(String[] args) {
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
```

### 47. Find the Common Elements Between Two Arrays
```java
import java.util.HashSet;

public class CommonElements {
    public static void main(String[] args) {
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
}
```

### 48. Implement a Stack Using an Array
```java
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

    public static void main(String[] args) {
        Stack stack = new Stack(5);  // Stack of size 5
        stack.push(10);
        stack.push(20);
        System.out.println("Top element: " + stack.peek());
        System.out.println("Popped element: " + stack.pop());
    }
}
```

### 49. Implement a Queue Using an Array
```java
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

    public static void main(String[] args) {
        Queue queue = new Queue(5);  // Queue of size 5
        queue.enqueue(10);
        queue.enqueue(20);
        System.out.println("Front element: " + queue.peek());
        System.out.println("Dequeued element: " + queue.dequeue());
    }
}
```

### 50. Check if a Number is a Fibonacci Number
```java
public class FibonacciNumber {
    public static void main(String[] args) {
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
```

### Explanation:
1. **Binary Search**: The program implements binary search, which works by repeatedly dividing the sorted array into halves. It compares the middle element with the target and eliminates one half based on the comparison.

2. **Find Common Elements Between Two Arrays**: The program uses a `HashSet` to store elements from the first array and checks for each element in the second array whether it exists in the set, printing the common elements.

3. **Implement a Stack Using an Array**: A stack follows the LIFO (Last In First Out) principle. This program uses an array to implement the stack and provides methods to push, pop, and peek elements.

4. **Implement a Queue Using an Array**: A queue follows the FIFO (First In First Out) principle. This program uses an array to implement the queue and provides methods to enqueue, dequeue, and peek elements.

5. **Check if a Number is a Fibonacci Number**: A number is a Fibonacci number if one or both of the expressions `(5*n*n + 4)` or `(5*n*n - 4)` is a perfect square. The program checks this condition to determine if a number is a Fibonacci number.

Here are the Java programs for the problems you've provided:

### 51. Reverse a String Without Using String Inbuilt Function
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
```

### 52. Reverse a String Without Using String Inbuilt Function reverse()
```java
public class ReverseStringWithoutReverse {
    public static void main(String[] args) {
        String str = "Java Programming";  // Example string
        String reversedStr = "";  // Variable to hold the reversed string
        
        // Loop through the string from end to start
        for (int i = str.length() - 1; i >= 0; i--) {
            reversedStr += str.charAt(i);  // Append characters in reverse order
        }

        System.out.println("Reversed string: " + reversedStr);
    }
}
```

### 53. Swap Two Numbers Using the Third Variable
```java
public class SwapWithThirdVariable {
    public static void main(String[] args) {
        int a = 5, b = 10;  // Example numbers
        System.out.println("Before swapping: a = " + a + ", b = " + b);

        // Swap the numbers using a third variable
        int temp = a;
        a = b;
        b = temp;

        System.out.println("After swapping: a = " + a + ", b = " + b);
    }
}
```

### 54. Swap Two Numbers Without Using the Third Variable
```java
public class SwapWithoutThirdVariable {
    public static void main(String[] args) {
        int a = 5, b = 10;  // Example numbers
        System.out.println("Before swapping: a = " + a + ", b = " + b);

        // Swap the numbers without using a third variable
        a = a + b;  // a becomes the sum of a and b
        b = a - b;  // b becomes the original value of a
        a = a - b;  // a becomes the original value of b

        System.out.println("After swapping: a = " + a + ", b = " + b);
    }
}
```

### 55. Count the Number of Words in a String Using HashMap
```java
import java.util.HashMap;

public class CountWordsWithHashMap {
    public static void main(String[] args) {
        String str = "This is a test string and this is great";  // Example string
        String[] words = str.split(" ");  // Split string into words
        
        HashMap<String, Integer> wordCountMap = new HashMap<>();

        // Count the frequency of each word using HashMap
        for (String word : words) {
            word = word.toLowerCase();  // Convert to lowercase to count case-insensitive
            wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
        }

        // Display the word count
        for (String word : wordCountMap.keySet()) {
            System.out.println(word + ": " + wordCountMap.get(word));
        }
    }
}
```

### Explanation:
1. **Reverse a String Without Using String Inbuilt Function**: This program converts the string to a character array, and then swaps characters from both ends of the array until the entire string is reversed.

2. **Reverse a String Without Using String Inbuilt Function reverse()**: This program reverses a string by looping through it from the last character to the first, appending each character to a new string.

3. **Swap Two Numbers Using the Third Variable**: This program swaps two numbers using a temporary variable to hold one of the values during the swapping process.

4. **Swap Two Numbers Without Using the Third Variable**: This program swaps two numbers using arithmetic operations (addition and subtraction), avoiding the use of a third variable.

5. **Count the Number of Words in a String Using HashMap**: This program splits the input string into words, counts the frequency of each word using a `HashMap`, and prints out the word counts. It also handles case insensitivity by converting all words to lowercase.

Here are the Java programs for the problems you've provided:

### 56. Iterate HashMap Using While and Advanced For Loop
```java
import java.util.HashMap;
import java.util.Map;

public class IterateHashMap {
    public static void main(String[] args) {
        // Create a sample HashMap
        HashMap<String, Integer> map = new HashMap<>();
        map.put("Apple", 1);
        map.put("Banana", 2);
        map.put("Cherry", 3);

        // Iterate using a While loop
        System.out.println("Using While loop:");
        var iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // Iterate using an Advanced For loop (Enhanced for-loop)
        System.out.println("\nUsing Advanced For loop:");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
```

### 57. Find Whether a Number is Prime or Not
```java
public class PrimeNumber {
    public static void main(String[] args) {
        int num = 29;  // Example number
        boolean isPrime = true;

        // Check for prime number
        if (num <= 1) {
            isPrime = false;
        } else {
            for (int i = 2; i <= Math.sqrt(num); i++) {
                if (num % i == 0) {
                    isPrime = false;
                    break;
                }
            }
        }

        if (isPrime) {
            System.out.println(num + " is a prime number.");
        } else {
            System.out.println(num + " is not a prime number.");
        }
    }
}
```

### 58. Find Whether a String or Number is Palindrome or Not
```java
public class Palindrome {
    public static void main(String[] args) {
        String str = "madam";  // Example string
        int num = 121;  // Example number

        // Check if string is palindrome
        if (isPalindrome(str)) {
            System.out.println("\"" + str + "\" is a palindrome.");
        } else {
            System.out.println("\"" + str + "\" is not a palindrome.");
        }

        // Check if number is palindrome
        if (isPalindrome(num)) {
            System.out.println(num + " is a palindrome.");
        } else {
            System.out.println(num + " is not a palindrome.");
        }
    }

    // Function to check if a string is palindrome
    public static boolean isPalindrome(String str) {
        String reversed = new StringBuilder(str).reverse().toString();
        return str.equals(reversed);
    }

    // Function to check if a number is palindrome
    public static boolean isPalindrome(int num) {
        int original = num;
        int reversed = 0;
        
        while (num > 0) {
            reversed = reversed * 10 + num % 10;
            num /= 10;
        }

        return original == reversed;
    }
}
```

### 59. Fibonacci Series
```java
public class FibonacciSeries {
    public static void main(String[] args) {
        int n = 10;  // Number of terms in the Fibonacci series
        int first = 0, second = 1;

        System.out.println("Fibonacci Series:");
        System.out.print(first + " " + second + " ");
        
        for (int i = 2; i < n; i++) {
            int next = first + second;
            System.out.print(next + " ");
            first = second;
            second = next;
        }
    }
}
```

### 60. Iterate ArrayList Using For-loop, While-loop, and Advanced For-loop
```java
import java.util.ArrayList;

public class IterateArrayList {
    public static void main(String[] args) {
        // Create a sample ArrayList
        ArrayList<String> list = new ArrayList<>();
        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");

        // Using for-loop
        System.out.println("Using For-loop:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        // Using While-loop
        System.out.println("\nUsing While-loop:");
        int index = 0;
        while (index < list.size()) {
            System.out.println(list.get(index));
            index++;
        }

        // Using Advanced For-loop (Enhanced for-loop)
        System.out.println("\nUsing Advanced For-loop:");
        for (String item : list) {
            System.out.println(item);
        }
    }
}
```

### Explanation:

1. **Iterate HashMap Using While and Advanced For Loop**: The program iterates through a `HashMap` first using a `while` loop and then using an advanced (enhanced) for-loop. The `Map.Entry` is used in the `while` loop to access both keys and values.

2. **Find Whether a Number is Prime or Not**: The program checks if a number is prime by iterating from `2` to the square root of the number. If it finds any divisor, it determines the number is not prime.

3. **Find Whether a String or Number is Palindrome or Not**: The program checks if a string is a palindrome by comparing it with its reverse. Similarly, it checks if a number is a palindrome by reversing its digits and comparing it with the original number.

4. **Fibonacci Series**: The program generates the Fibonacci sequence by starting with the first two numbers `0` and `1`, and calculating subsequent terms by adding the previous two numbers.

5. **Iterate ArrayList Using For-loop, While-loop, and Advanced For-loop**: The program demonstrates how to iterate through an `ArrayList` using a regular `for` loop, a `while` loop, and an enhanced `for` loop. Each loop method prints out all the elements in the list.

Here are the Java programs for the problems you've provided:

### 61. Find the Duplicate Characters in a String
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
```

### 62. Find the Second-Highest Number in an Array
```java
import java.util.Arrays;

public class SecondHighestNumber {
    public static void main(String[] args) {
        int[] arr = {12, 35, 1, 10, 34, 1};  // Example array

        // Sort the array
        Arrays.sort(arr);

        // Find second highest number
        int secondHighest = arr[arr.length - 2];

        System.out.println("Second highest number: " + secondHighest);
    }
}
```

### 63. Check Armstrong Number
```java
public class ArmstrongNumber {
    public static void main(String[] args) {
        int num = 153;  // Example number
        int original = num;
        int sum = 0;
        int digits = String.valueOf(num).length();

        // Check if the number is Armstrong
        while (num != 0) {
            int digit = num % 10;
            sum += Math.pow(digit, digits);
            num /= 10;
        }

        if (sum == original) {
            System.out.println(original + " is an Armstrong number.");
        } else {
            System.out.println(original + " is not an Armstrong number.");
        }
    }
}
```

### 64. Remove All White Spaces from a String Using `replace()`
```java
public class RemoveWhiteSpacesWithReplace {
    public static void main(String[] args) {
        String str = " Hello World! Java Programming ";  // Example string

        // Remove white spaces using replace()
        String result = str.replace(" ", "");

        System.out.println("String without spaces: \"" + result + "\"");
    }
}
```

### 65. Remove All White Spaces from a String Without Using `replace()`
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
```

### Explanation:

1. **Find Duplicate Characters in a String**: This program uses a `HashMap` to store each character and its frequency. It then checks the map for characters that appear more than once and prints those characters along with their frequencies.

2. **Find the Second-Highest Number in an Array**: The program sorts the array in ascending order and retrieves the second-highest number by accessing the second-to-last element of the sorted array.

3. **Check Armstrong Number**: An Armstrong number is a number that is equal to the sum of its own digits raised to the power of the number of digits. The program calculates this sum and compares it with the original number to determine if it's an Armstrong number.

4. **Remove All White Spaces from a String Using `replace()`**: This program removes all spaces from the string using the `replace()` method, which replaces all spaces with an empty string.

5. **Remove All White Spaces from a String Without Using `replace()`**: This program removes spaces by manually iterating over the string and appending each non-space character to a `StringBuilder`.

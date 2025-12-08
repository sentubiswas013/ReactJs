# Java Coding Questions - Complete Collection

## String Manipulation

1. Reverse a String without using String inbuilt function
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

2. Count the Number of Words in a String Using HashMap
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

3. Find the Duplicate Characters in a String
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

4. Remove All White Spaces from a String Using `replace()`
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

5. Remove All White Spaces from a String Without Using `replace()`
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

6. Find Whether a String is Palindrome or Not
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

7. Count Vowels and Consonants in a String
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

8. Check if Two Strings are Anagrams
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

9. Check if a String Contains a Substring
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

10. Replace a Character in a String Without Using `String.replace()`
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

11. Find the Longest Word in a Sentence
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

12. Count the Number of Occurrences of a Character in a String
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

13. Swap Two Strings
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

14. Remove All Special Characters From String
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

15. Reverse Words in a Sentence
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

---

### 📝 **Explanation**

1. Split the sentence into words using `" "`.
2. Loop from the end to the beginning.
3. Append words in reverse order.
4. `trim()` removes the last extra space.

---

# ✅ **Solution 2: One-Line Using Collections**

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

---

### ✅ **Solution 3: Using Stack (Asked in some interviews)**

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


16. Remove Characters That Appear More Than Once
17. Find First Non-Repeating Character in String
18. Validate Email Format (Regex)
19. Check if Two Strings Are Rotations of Each Other
20. Longest Palindromic Substring

## Number Operations

21. Swap Two Numbers Using the Third Variable
22. Swap Two Numbers Without Using the Third Variable
23. Find Whether a Number is Prime or Not
24. Find Whether a Number is Palindrome or Not
25. Fibonacci Series
26. Find the Factorial of a Number
27. Check if a Number is Even or Odd
28. Find the Sum of Digits of a Number
29. Print the Multiplication Table of a Number
30. Check Armstrong Number
31. Print the Prime Numbers Between 1 and 100
32. Reverse an Integer Number
33. Convert a String to an Integer
34. Find the Sum of Natural Numbers
35. Check if a Number is a Perfect Number
36. Find the GCD (Greatest Common Divisor) of Two Numbers
37. Find the LCM (Least Common Multiple) of Two Numbers
38. Calculate the Power of a Number
39. Check if a Number is a Perfect Square
40. Check if a Number is a Fibonacci Number
41. Convert String to Integer Without parseInt()
42. Convert Integer to String Without toString()
43. Find All Prime Numbers up to N

## Array Operations

44. Find the Largest Element in an Array
45. Find the Smallest Element in an Array
46. Find the Second-Highest Number in an Array
47. Merge Two Arrays
48. Sort an Array of Integers in Ascending Order
49. Sum All Elements of an Array
50. Find the Median of an Array
51. Remove Duplicates from an Array
52. Convert an Array to a String
53. Find the Common Elements Between Two Arrays
54. Find Min and Max in Array (Single Loop)
55. Find Even & Odd Numbers in Array
56. Merge Two Sorted Arrays Without Using Extra Space
57. Find Majority Element in Array (Boyer–Moore Algorithm)
58. Rotate Array Left/Right by K Positions
59. Find All Subarrays With Given Sum (Sliding Window)
60. Top K Frequent Elements (Heap + HashMap)

## Pattern Printing

61. Print a Triangle of Stars
62. Create a Pattern of Numbers (e.g., 1, 12, 123, etc.)
63. Print Pattern (Right Triangle)

## Collections (HashMap & ArrayList)

64. Iterate HashMap using While and Advanced For Loop
65. Iterate ArrayList Using For-loop, While-loop, and Advanced For-loop

## Data Structures

66. Implement a Stack Using an Array
67. Implement Queue Using LinkedList
68. Implement Binary Search
69. Implement Binary Search Recursively
70. Implement MinStack (Stack with getMin() in O(1))
71. Implement Trie (Insert / Search / StartsWith)
72. Detect and Remove Loop in Linked List

## Tree Operations

73. Serialize and Deserialize a Binary Tree
74. Find the Diameter of a Binary Tree

## Graph Operations

75. Find Number of Islands (DFS or BFS)

## Multithreading

76. Implement Producer–Consumer Problem (Threads + wait/notify)

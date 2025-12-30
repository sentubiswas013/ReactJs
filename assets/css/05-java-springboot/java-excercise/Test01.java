import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.io.File;
import java.io.IOException;

public class Test01 {
    public static void main(String[] args) {
        // System.out.println("Hello, World!");


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
        DuplicateCharacters();
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

        // Question: 46 ====
        // int[] arr = {1, 3, 5, 7, 9, 11, 13, 15};  // Example sorted array
        // int target = 7;  // Number to search for
        // int result = binarySearch(arr, target);

        // if (result == -1) {
        //     System.out.println("Element not found.");
        // } else {
        //     System.out.println("Element found at index: " + result);
        // }

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

        // Question: 50 ====
        // int number = 21;  // Example number
        // if (isFibonacci(number)) {
        //     System.out.println(number + " is a Fibonacci number.");
        // } else {
        //     System.out.println(number + " is not a Fibonacci number.");
        // }
    }

    // 1. Reverse a String without using String inbuilt function
    public static void ReverseString() {
        System.out.println("Hello Workd");
    }


    // 2. Reverse a String without using String inbuilt function reverse()
    public static void ReverseStringNoReverse() {
        String name = "Hello World";
        char[] charArr = name.toCharArray();
        ArrayList<Character> result = new ArrayList<>();
        
        String reversed = "";
        for(int i = charArr.length-1; i >= 0; i--) {
            System.out.println("reversed ---" +charArr[i]);
            reversed += charArr[i];
            result.add(charArr[i]);
        }     
        System.out.println("reversed ---" +reversed);
        System.out.println("result -----" +result);
    }


    // 3. Swap Two Numbers Using the Third Variable
    public static void SwapWithThirdVariable() {
        int a = 10;
        int b = 20;
        int c;
        
        c = a;
        a = b;
        b = c;
        System.out.println("result -----" + a +"---"+ b);       
    }


    // 4. Swap Two Numbers Without Using the Third Variable
    public static void SwapWithoutThirdVariable() {
        int a = 10;
        int b = 20;
        
        a = a + b;
        b = a - b;
        a = a - b;
        System.out.println("result -----" + a +"---"+ b);
    }


    // 5. Count the Number of Words in a String Using HashMap
    public static void CountWordsUsingHashMap() {
        String str = "This is a test string and this is a test";
        // String[] words = str.split("");
        String[] words = str.split("\\s+");
        HashMap<String, Integer> wordCount = new HashMap<>();
        
        for(String word: words) {
            word = word.toLowerCase();  // Optional: Convert to lowercase for case-insensitive count
            // System.out.println(word.length());   
            wordCount.put(word, word.length());
            
        }
        System.out.println(wordCount);
    }



    // 6. Iterate HashMap using While and Advanced For Loop
    public static void IterateHashMap() {
        HashMap<Integer, String> cars = new HashMap<>();
        cars.put(1, "Tata");
        cars.put(2, "Mahindra");
        cars.put(3, "Bajaj");
        cars.put(4, "Ola");
        
        for (Map.Entry<Integer, String> entry : cars.entrySet()) {
            System.out.println("Car " + entry.getKey() +" : "+entry.getValue());
        }
    }



    // 7. Find Whether a Number is Prime or Not
    public static void PrimeNumber() {
        int number = 10;
        boolean isPrime = true;
        
        System.out.println("Hello " + Math.sqrt(number));
        for(int i = 3; i <= Math.sqrt(number); i++) {
            if(number % i == 0) {
                isPrime = false;
                break;
            }            
        }
        
        if (isPrime) {
            System.out.println(number + " is a Prime number");
        } else {
            System.out.println(number + " is NOT a Prime number");
        }
    }



    // 8. Find Whether a String or Number is Palindrome or Not
    public static void Palindrome() {
        String str = "madam";
        boolean isPalindrome = true;
        
        for(int i = 0; i <= str.length() / 2; i++ ) {
            System.out.println("Hello :" + i);
            if(str.charAt(i) != str.charAt(str.length()-1-i)) {
                isPalindrome = false;
                break;
            }
        }
        if (isPalindrome) { 
            System.out.println(str + " is a palindrome.");
        } else {
            System.out.println(str + " is not a palindrome.");
        }
    }


    // 9. Fibonacci Series
    public static void FibonacciSeries() {
        int num  = 10;
        int a = 0, b = 1;
        
        System.out.println("Fibonacci Series :");       
        for(int i = 0; i< num; i++) {
            System.out.print(a + ", ");
            int nextTerm = a + b;
            a = b;
            b = nextTerm;           
        }
        
    }


    // 10. Iterate ArrayList Using For-loop, While-loop, and Advanced For-loop
    public static void IterateArrayList() {
        ArrayList<String> cars = new ArrayList<>();
        cars.add("Tata");
        cars.add("Mahindra");
        cars.add("Bajaj");
        cars.add("Ola");
        
        for(int i=0; i<cars.size(); i++) {
            System.out.println("Hello :" +i+ " : " + cars.get(i));
        }       
        
        for(String row: cars) {
            System.out.println(row + ", ");
        }
    }



    // 11. Find the Duplicate Characters in a String
    public static void DuplicateCharacters() {
        
    }



    // 11. Find the Duplicate Characters in a Numbers
    public static void findDuplicateNums() {
        
    }

    // 12. Find the Second-Highest Number in an Array
    public static void SecondHighestNumber() {
        
    }



    // 13. Check Armstrong Number
    public static void ArmstrongNumber() {
        
    }


    // 14. Remove All White Spaces from a String Using `replace()`
    public static void RemoveWhiteSpacesWithReplace() {
        
    }


    // 15. Remove All White Spaces from a String Without Using `replace()`
    public static void RemoveWhiteSpacesWithoutReplace() {
        
    }


    // 16. Find the Factorial of a Number
    public static void Factorial() {
        
    }


    // 17. Check if a Number is Even or Odd
    public static void EvenOdd() {
        
    }


    // 18. Find the Sum of Digits of a Number
    public static void SumOfDigits() {
        
    }


    // 19. Print the Multiplication Table of a Number
    public static void MultiplicationTable() {
        
    }


    // 20. Find the Largest Element in an Array
    public static void LargestElement() {
        
    }


    // 21. Find the Smallest Element in an Array
    public static void SmallestElement() {
        
    }


    // 22. Count Vowels and Consonants in a String
    public static void VowelConsonantCount() {
        
    }


    // 23. Check if Two Strings are Anagrams
    public static void AnagramCheck() {
        
    }

    // 24. Print the Prime Numbers Between 1 and 100
    public static void PrimeNumbers() {
        
    }


    // 25. Reverse an Integer Number
    public static void ReverseInteger() {
        
    }


    // 26. Convert a String to an Integer
    public static void StringToInteger() {
        
    }


    // 27. Print a Triangle of Stars
    public static void StarTriangle() {
        
    }


    // 28. Find the Sum of Natural Numbers
    public static void SumOfNaturalNumbers() {
        
    }


    // 29. Check if a Number is a Perfect Number
    public static void PerfectNumber() {
        
    }


    // 30. Find the GCD (Greatest Common Divisor) of Two Numbers
    public static void GreatestCommonDivisor() {
        
    }


    // 31. Find the LCM (Least Common Multiple) of Two Numbers

    public static void LeastCommonMultiple() {
        
    }


    // 32. Calculate the Power of a Number
    public static void PowerOfNumber() {
        
    }

    // 33. Count the Number of Occurrences of a Character in a String
    public static void CountOccurrences() {
        
    }


    // 34. Merge Two Arrays
    public static void MergeArrays() {
        
    }


    // 35. Sort an Array of Integers in Ascending Order
    public static void SortArray() {
        
    }


    // 36. Sum All Elements of an Array
    public static void SumArray() {
        
    }


    // 37. Find the Median of an Array
    public static void MedianArray() {
        
    }


    // 38. Create a Pattern of Numbers (e.g., 1, 12, 123, etc.)
    public static void NumberPattern() {
        
    }


    // 39. Check if a String Contains a Substring
    public static void ContainsSubstring() {
        
    }


    // 40. Replace a Character in a String Without Using `String.replace()`
    public static void ReplaceCharacter() {
        
    }


    // 41. Remove Duplicates from an Array
    public static void RemoveDuplicates() {
        
    }


    // 42. Convert an Array to a String
    public static void ArrayToString() {
        
    }


    // 43. Swap Two Strings
    public static void SwapStrings() {
        
    }


    // 44. Find the Longest Word in a Sentence
    public static void LongestWord() {
        
    }


    // 45. Check if a Number is a Perfect Square
    public static void PerfectSquare() {
        
    }

    // 46. Implement Binary Search
    // Binary search method
    public static void binarySearch() {
        
    }
  


    // 47. Find the Common Elements Between Two Arrays
    public static void CommonElements() {
        
    }



    // 48. Implement a Stack Using an Array
    public class Stack {
        
    }


    // 49. Implement a Queue Using an Array
    public class Queue {
        
    }


    // 50. Check if a Number is a Fibonacci Number

    public class FibonacciNumber {
        
    }
}
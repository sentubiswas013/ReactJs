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

        // 01. String Operations  ============================
        // ReverseString();
        // RemoveWhiteSpacesWithReplace();
        // RemoveWhiteSpacesWithoutReplace();
        // ContainsSubstring();
        // ReplaceCharacter();
        // ArrayToString();
        // SwapStrings();
        // SwapWithThirdVariable();
        // SwapWithoutThirdVariable();
        // LongestWord();
		// shortWord();

        // 02. String Analysis ================================
        // Palindrome();
        // FirstNonRepeatedChar();
        // DuplicateCharacters();
        // RemoveDuplicates();
        VowelConsonantCount();
        // AnagramCheck();
        // CountOccurrences();

        // 03. Number Operations  ==============================
        // EvenOdd();
        // ReverseInteger();
        // SumOfDigitsOne();
        // SumOfDigitsTwo();
        // SumOfNaturalNumbers();
        // StringToInteger();
        // PrimeNumber();
        // PrimeNumbers();
        // PerfectNumber();
        // PerfectSquare();
        // ArmstrongNumber();
        // GreatestCommonDivisor();
        // LeastCommonMultiple();
        // Factorial();
        // PowerOfNumber();

        // 04. Array Operations  ==============================
        // findSimilarNumsFromTwoArray();
        // findDuplicateNums();
        // SecondHighestNumber();
        // LargestElement();
        // SmallestElement();
        // MergeArrays();
        // SortArray();
        // SortArrayTwo();
        // SumArray();
        // MedianArray();
        // CommonElements();
        // IterateArrayList();

        // 05. Data Structures  ================================
        // CountWordsUsingHashMap();
        // IterateHashMap();

        // 06. Series & Patterns  ==============================
        // FibonacciSeries();
        // MultiplicationTable();
        // StarTriangle();
        // NumberPattern();

        // 07. Implement Star Pattern ==============================
        // SquareStarPattern();
        // RightTriangleStarPattern();
        // InvertedTriangleStarPattern();
        // PyramidStarPattern();
        // DiamondStarPattern();
    }

    // String Operations ==========================================================
    // 1. Reverse a String without using String inbuilt function
    public static void ReverseString() {
        String str = "HelloWorld";
        
        System.out.println("result: ");
    }

    // 2. Remove All White Spaces from a String Using `replace()`
    public static void RemoveWhiteSpacesWithReplace() {
        String str = "Hello World Java";
        
        System.out.println("result: ");
    }

    // 3. Remove All White Spaces from a String Without Using `replace()`
   public static void RemoveWhiteSpacesWithoutReplace() {
        String str = "   Hello World!   ";

       

        System.out.println("result: ");
    }

    // 4. Check if a String Contains a Substring
    public static void ContainsSubstring() {
        String str = "Hello World";  // Example string
        String substring = "World";  // Substring to check

        
		System.out.println("result: ");
    }

    // 5. Replace a Character in a String Without Using `String.replace()`
    public static void ReplaceCharacter() {
        String str = "Hello World";  // Example string
        char oldChar = 'o';  // Character to replace
        char newChar = 'a';  // New character to replace with
		StringBuilder result = new StringBuilder();
		for(int i = 0; i<str.length(); i++) {
			if(str.charAt(i) == oldChar) {
				result.append(newChar);
			}
			else {
				result.append(str.charAt(i));
			}
		}
        

        System.out.println("result: " + result);
    }

    // 6. Convert an Array to a String
    public static void ArrayToString() {
        int[] arr = {1, 2, 3, 4, 5};  // Example array
		
		System.out.println("result: " + Arrays.toString(arr));
		System.out.println("result: " + arr.toString());
    }

    // 7. Swap Two Strings
    public static void SwapStrings() {
        String str1 = "Hello";  // First string
        String str2 = "World";  // Second string
        
		String temp = str2;
		str2 = str1;
		str1 = temp;
		
		System.out.println("result: " + str1 + "--" + str2);
    }

    // 8. Swap Two Numbers Using the Third Variable
    public static void SwapWithThirdVariable() {
        int a = 5, b = 10;
		int c = b;
		b = a;
		a = c;
        System.out.println("result: " + a + "--" + b);
    }

    // 9. Swap Two Numbers Without Using the Third Variable
    public static void SwapWithoutThirdVariable() {
        int a = 5, b = 10;
		a = a + b;
		System.out.println("result: " + a);
		b = a - b;
		System.out.println("result: " + b);
		a = a - b;
		System.out.println("result: " + a);

        System.out.println("result: " + a + "--" + b);
    }

    // 10. Find the Longest Word in a Sentence
    public static void LongestWord() {
        String sentence = "This is a Java programming challenge.";  // Example sentence
		String[] words = sentence.split(" ");
		String longest = "";
		
		for(int i = 0; i<words.length; i++) {
			// System.out.println("result: " + words[i]);
			if(words[i].length() > longest.length()) {
				longest = words[i];
			}
		}
		

        System.out.println("result:2 " + longest);
    }
	
	// 11. Find the shortest Word in a Sentence
	public static void shortWord() {
		String sentence = "This is a Java programming challenge.";
		String[] words = sentence.split(" ");
		String shortest = words[0];
		for(int i = 0; i < words.length; i++) {
			// System.out.println("result: " + i);
			if(words[i].length() < shortest.length()) {
				shortest = words[i];
			}
		}
		
		System.out.println("result: " + shortest);
	}

    // String Analysis  ==========================================================
    // 1. Find Whether a String or Number is Palindrome or Not
    public static void Palindrome() {
        String str = "madam";
        boolean isPalindrome = true;
		
		for(int i = 0; i < str.length() / 2; i++) {
			System.out.println("result: " + i);
			if(str.charAt(i) != str.charAt(str.length()-1-i) ) {
				isPalindrome = false;
				break;
			}
		}

        System.out.println("result: " + isPalindrome);
    }

     // 1. Find the First Non-Repeated Character in a String
    public static void FirstNonRepeatedChar () {
        String str = "stress";
        char result = ' ';
        

        System.out.println(result); // Output: t
    }

    // 2. Find the Duplicate Characters in a String
    public static void DuplicateCharacters() {
        String str = "programming";
		char[] chars = str.toCharArray();

        for(int i = 0; i<chars.length; i++) {			
			for(int j = i+1; j<chars.length; j++ ) {
				if(chars[i] == chars[j]) {
					System.out.println("result: " + chars[i]);
					break;
				}
			}
		}
		
		// System.out.println("result: " + chars);
    }

    // 3. Remove Duplicates from an Array
    public static void RemoveDuplicates() {
        int[] arr = {1, 2, 3, 4, 4, 5, 5, 6};  // Example array
        ArrayList<Integer> result = new ArrayList<>();
		for(int i = 0; i<arr.length; i++) {		
			boolean isDuplicate = false;
		
			for(int j = i+1; j<arr.length; j++ ) {
				if(arr[i] == arr[j]) {
					// System.out.println("result: " + arr[i]);
					isDuplicate = true;
					break;
				}
			}
			if(!isDuplicate) {
			 result.add(arr[i]);
			 // System.out.println("result: " + arr[i]);
			}
		}
		System.out.println("result: " + result);
    }

    // 4. Count Vowels and Consonants in a String
    public static void VowelConsonantCount() {
        String str = "Hello World";  // Example string
        int vowels = 0, consonants = 0;

        char[] strArr = str.toCharArray();
		for(int i = 0; i<strArr.length; i++ ) {
			char ch = Character.toLowerCase(strArr[i]);
			
			if(ch >= 'a' && ch <= 'z') {
				// if(ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                if(ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
					System.out.println("This is vowels : " + strArr[i]);
                    break;
				} else {
                    System.out.println("This is vowels : " + strArr[i]);
                }
			}
			
		}

        System.out.println("result: ");
    }

    // 5. Check if Two Strings are Anagrams
    public static void AnagramCheck() {
        String str1 = "listen";  // Example strings
        String str2 = "silent";

        
		
		System.out.println("result: ");
    }

    // 6. Count the Number of Occurrences of a Character in a String
    public static void CountOccurrences() {
        String str = "programming";  // Example string
        char targetChar = 'r';  // Character to count
        int count = 0;

		System.out.println("result: ");
    }
    
    // 03. Number Operations  ==========================================================
   // 1. Check if a Number is Even or Odd
    public static void EvenOdd() {
        int number = 7;  // Example number

        
		
		System.out.println("result: ");
    }

   // 2. Reverse an Integer Number
    public static void ReverseInteger() {
        int number = 12345;
        
		
		System.out.println("result: ");
    }

    // 3. Find the Sum of Digits of a Number
    public static void SumOfDigitsOne() {
        int number = 1234;  // Example number
        int sum = 0;
        
		
		System.out.println("result: ");
    }

    // 4. Find the Sum of Digits of a Number
    public static void SumOfDigitsTwo() {
        int number = 1230;
        int sum = 0;
        

        System.out.println("result: ");
    }


    // 5. Find the Sum of Natural Numbers
    public static void SumOfNaturalNumbers() {
        int n = 10;  // Example: sum of first 10 natural numbers
        int sum = 0;
        

        System.out.println("result: ");
    }

    // 6. Convert a String to an Integer
    public static void StringToInteger() {
        String str = "12345";  // Example string

        System.out.println("result: ");
    }

    // 7. Find Whether a Number is Prime or Not
    public static void PrimeNumber() {
        int number = 29;
        boolean isPrime = true;
        
		
		System.out.println("result: ");
    }

    // 8. Print the Prime Numbers Between 1 and 100
    public static void PrimeNumbers() {
		int startNum = 2;
		int endNum = 100;
	
        
		
		System.out.println("result: ");
    }

    // 9. Check if a Number is a Perfect Number
    public static void PerfectNumber() {
        int number = 28;  // Example number
        int sum = 0;
		
		System.out.println("result: ");
    }

    // 10. Check if a Number is a Perfect Square
    public static void PerfectSquare() {
        int number = 25;  // Example number

        		
		System.out.println("result: ");
    }

    // 11. Check Armstrong Number
    public static void ArmstrongNumber() {
        int number = 153;
		
		System.out.println("result: ");
    }

    // 12. Find the GCD (Greatest Common Divisor) of Two Numbers
    public static void GreatestCommonDivisor() {
        int num1 = 56;  // Example numbers
        int num2 = 98;


        System.out.println("result: ");
    }

    // 13. Find the LCM (Least Common Multiple) of Two Numbers
    public static void LeastCommonMultiple() {
       
		
		System.out.println("result: ");
    }

    // 14. Find the Factorial of a Number
    public static void Factorial() {
        int number = 5;  // Example number

        System.out.println("result: ");
    }

    // 15. Calculate the Power of a Number
    public static void PowerOfNumber() {
        int base = 2;  // Base number
        int exponent = 5;  // Exponent

        System.out.println("result: ");
    }

    // 04. Array Operations  ==========================================================   
    // 1. Find Similar Numbers from Two Arrays
    public static void findSimilarNumsFromTwoArray () {
        int[] numOne = {2, 4, 6, 8, 9};
        int[] numTwo = {3, 8, 6, 2, 5};

        
        System.out.println("result: ");

        // Output: 
        // 2
        // 6
        // 8
    }
    
    // 1. Find the Duplicate Characters in a Numbers
    public static void findDuplicateNums() {
        int[] nums = {2, 5, 2, 7, 8, 9, 5, 3};

        System.out.println("result: ");
    }

    // 2. Find the Second-Highest Number in an Array
    public static void SecondHighestNumber() {
        int[] arr = {10, 5, 20, 20, 15, 5, 30};

        System.out.println("result: ");
    }

    // 3. Find the Largest Element in an Array
    public static void LargestElement() {
        int[] arr = {10, 20, 5, 30, 15};  // Example array
        int largest = arr[0];


        System.out.println("result: ");
    }

    // 4. Find the Smallest Element in an Array
    public static void SmallestElement() {
        int[] arr = {10, 20, 5, 30, 15};  // Example array
        int smallest = arr[0];

        System.out.println("result: ");
    }

    // 5. Merge Two Arrays
    public static void MergeArrays() {
        int[] array1 = {1, 2, 3};  // First array
        int[] array2 = {4, 5, 6};  // Second array
		
		System.out.println("result: ");
    }

    // 6. Sort an Array of Integers in Ascending Order
    public static void SortArray() {
        int[] arr = {5, 2, 9, 1, 5, 6};  // Example array


        System.out.println("result: ");
    }

    // 6. Sort an Array of Integers in Ascending Order
    public static void SortArrayTwo() {
        int[] arr = {5, 2, 9, 1, 5, 6};

        
        System.out.println("result: ");
        // 1 2 5 5 6 9
    }

    // 7. Sum All Elements of an Array
    public static void SumArray() {
        int[] arr = {1, 2, 3, 4, 5};  // Example array
        int sum = 0;


        System.out.println("result: ");
    }

    // 8. Find the Median of an Array
    public static void MedianArray() {
        int[] arr = {12, 3, 5, 7, 19};  // Example array


        System.out.println("result: ");
    }

    // 9. Find the Common Elements Between Two Arrays
    public static void CommonElements() {
        int[] arr1 = {1, 2, 3, 4, 5};
        int[] arr2 = {3, 4, 5, 6, 7};

		
		System.out.println("result: ");
    }

    // 10. Iterate Array List Arrays
    public static void IterateArrayList() {
		
		System.out.println("result: ");
    }

    // 05. Data Structures =========================================================
    // 1. Count the Number of Words in a String Using HashMap
    public static void CountWordsUsingHashMap() {
        String str = "This is a test string and this is a test";
       
		
		System.out.println("result: ");
    }

    // 2. Iterate HashMap using While and Advanced For Loop
    public static void IterateHashMap() {
        // Create a HashMap
        HashMap<Integer, String> map = new HashMap<>();
		
		System.out.println("result: ");
    }

    // 06. Series & Patterns ========================================================
    // 1. Fibonacci Series
    public static void FibonacciSeries() {
        int n = 10;  // Number of terms in the Fibonacci series
        
		
		System.out.println("result: ");
    }

    // 2. Print the Multiplication Table of a Number
    public static void MultiplicationTable() {
        int number = 5;  // Example number
        int limit = 10;   // Table limit (multiplying by numbers 1 to 10);
		
		System.out.println("result: ");
    }

    // 3. Print a Triangle of Stars
    public static void StarTriangle() {
        int rows = 5;  // Number of rows in the triangle

        		
		System.out.println("result: ");
    }

    // 4. Create a Pattern of Numbers (e.g., 1, 12, 123, etc.)
    public static void NumberPattern() {
        int rows = 5;  // Number of rows in the pattern

		
		System.out.println("result: ");
    }

    // 07. Implement Star Pattern ========================================================
    // 1️. Square Star Pattern
    public static void SquareStarPattern() {
        
		System.out.println("result: ");
    }
    // * * * *
    // * * * *
    // * * * *
    // * * * *


    // 2. Right Triangle  Star Pattern
    public static void RightTriangleStarPattern() {
        
		System.out.println("result: ");
    }
    // *
    // * *
    // * * *
    // * * * *

    // 3. Inverted Triangle Star Pattern
    public static void InvertedTriangleStarPattern() {
        
		System.out.println("result: ");
    }
    // * * * *
    // * * *
    // * *
    // *

    // 4. Pyramid Star Pattern
    public static void PyramidStarPattern() {
        int n = 4;

        System.out.println("result: ");
    }
    //    *
    //   * *
    //  * * *
    // * * * *

    // 5. Diamond Star Pattern
    public static void DiamondStarPattern() {
        int n = 3;

        System.out.println("result: ");
    }
    //    *
    //   * *
    //  * * *
    //   * *
    //    *
}


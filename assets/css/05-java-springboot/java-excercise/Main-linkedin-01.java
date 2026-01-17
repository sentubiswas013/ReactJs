import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.LinkedHashMap;
import java.util.Map;

// ## ✅ Hello World Program
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        // fnSumTwoNumbers();
        // fnCheckEvenOrOdd();
        // fnLargestOfTwoNumbers();
        // fnLargestOfThreeNumbers();
        // fnPositiveNegativeOrZeroCheck();
        // fnFeapYearCheck();
        // fnSwapTwoNumbers();
        // fnFactorialOfNumber();
        // fnFibonacciSeries();
        // fnReverseNumberAndString();
        // fnPalindromeNumberAndStringCheck();
        // fnArmstrongNumberCheck();
        // fnPrimeNumberCheck();
        // fnPrintAllPrimeNumbers();
        // fnMultiplicationTableFfNumber();
        // fnSumAllElements();
        // fnSumOfDigitsOfNumber();
        // fnPrintNumbersWithoutLoop();
        // fnSquarePattern();
        // fnRightTrianglePattern();
        // fnIvertedTrianglePattern();
        // fnPyramidPattern();
        // fnDiamondPattern();
        // fnNumberPattern();
        // fnAlphabetPattern();
        // fnFindLargestAndSmallestElement();
        // fneRverseArray();
        // fnSortAnArrayWithBuildMethodAndWithoutMethod();
        // fnDuplicateCharacters();
        // fnFindDuplicateElementsWithInbuildMethodandWithout();
        // fnFindMissingNumberInArray();
        // fnSecondHighestNumber();
        // fnMatrixAdditionSubtractionMultiplication();
        // fnTransposeOfMatrix();
        // fnVowelConsonantCount();
        // fnFirstNonRepeatedChar();
        // fnAnagramCheck();
        // fnCommonElementsInTwoArrays();
        // fnFindSubstring();
    }

    // ## ✅ Sum of Two Numbers
    public static void fnSumTwoNumbers() {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        System.out.println(a + b);
        sc.close();
    }

    // ## ✅ Check Even or Odd
    public static void fnCheckEvenOrOdd() {
        int n = 23;
        System.out.println(n % 2 == 0 ? "Even" : "Odd");
    }

    
    // ## ✅ Largest of Two Numbers
    public static void fnLargestOfTwoNumbers() {
        int a = 23;
        int b = 11;
        System.out.println(a > b ? a : b);
    }

    // ## ✅ Largest of Three Numbers
    public static void fnLargestOfThreeNumbers() {
        int a = 22;
        int b = 11;
        int c = 55;

        int max = a;
        if (b > max) max = b;
        if (c > max) max = c;

        System.out.println(max);
    }

    // ## ✅ Positive, Negative, or Zero Check
    public static void fnPositiveNegativeOrZeroCheck() {
        int n = 30;

        if (n > 0)
            System.out.println("Positive");
        else if (n < 0)
            System.out.println("Negative");
        else
            System.out.println("Zero");
    }

    // ## ✅ Leap Year Check
    public static void fnFeapYearCheck() {
        int year = 2024;

        boolean leap = (year % 400 == 0) ||
                       (year % 4 == 0 && year % 100 != 0);

        System.out.println(leap ? "Leap Year" : "Not Leap Year");
    }

    // ## ✅ Swap Two Numbers (With & Without Third Variable)
    public static void fnSwapTwoNumbers () {

        int a = 10, b = 20;

        // With third variable
        int temp = a;
        a = b;
        b = temp;
        System.out.println(a + " " + b);

        // Without third variable (XOR)
        a = 10;
        b = 20;
        a ^= b;
        b ^= a;
        a ^= b;
        System.out.println(a + " " + b);
    }

    // ## ✅ Factorial of a Number (Iterative)
    public static void fnFactorialOfNumber() {
        int n = 5;
        long fact = 1;

        for (int i = 2; i <= n; i++) {
            fact *= i;
        }

        System.out.println(fact);
    }

    // ## ✅ Fibonacci Series (First N Terms)
    public static void fnFibonacciSeries() {
        int n = 5;
        long a = 0, b = 1;

        for (int i = 0; i < n; i++) {
            System.out.print(a + (i < n - 1 ? " " : ""));
            long c = a + b;
            a = b;
            b = c;
        }
    }

    // ## ✅ Reverse a Number  and string
    public static void fnReverseNumberAndString() {
        
        // Reverse Numbers
        int number = 12345;
        char[] charArray = String.valueOf(number).toCharArray();

        for (int i = charArray.length - 1; i >= 0; i--) {
            System.out.print(charArray[i]);
        }
        // 54321  
        
        // Reverse String 1
        String str = "HelloWorld";
        String reversedStr = "";
        for (int i = str.length() - 1; i >= 0; i--) {
            reversedStr += str.charAt(i);
        }
        System.out.println("------------");
        System.out.println("Reversed string: " + reversedStr);
        // Reversed string: dlroWolleH

        // Reverse String 2
        String strTemp = "HelloWorld";
        char[] strArr = strTemp.toCharArray();
        String reversedStrTemp = "";
        for (int i = strArr.length - 1; i >= 0; i--) {
            reversedStrTemp += strArr[i];
        }
        System.out.println("Reversed string: " + reversedStrTemp);
        // Reversed string: dlroWolleH

        // Reverse String 3
        String s = "hello";
        String reversed = new StringBuilder(s).reverse().toString();
        System.out.println(reversed);
    }

    // ## ✅ Palindrome Number Check
    public static void fnPalindromeNumberAndStringCheck() {
        // check palindrome with string
        String str = "madam";
        boolean isPalindromeNum = true;

        // Check if string is palindrome
        int length = str.length();
        for (int i = 0; i < length / 2; i++) {
            if (str.charAt(i) != str.charAt(length - 1 - i)) {
                isPalindromeNum = false;
                break;
            }
        }

        if (isPalindromeNum) {
            System.out.println(str + " is a palindrome.");
        } else {
            System.out.println(str + " is not a palindrome.");
        }
        // madam is a palindrome.
        
        // check palindrome with number
        int number = 121;
        char[] charArray = String.valueOf(number).toCharArray();
        
        boolean isPalindrome = true;
        
        for (int i = 0; i < charArray.length / 2; i++) {
            if (charArray[i] != charArray[charArray.length - 1 - i]) {
                isPalindrome = false;
                break;
            }
        }
        
        if (isPalindrome) {
            System.out.println(number + " is a palindrome number.");
        } else {
            System.out.println(number + " is not a palindrome number.");
        }
        // 121 is a palindrome number.

        // ============
        String s = "madam";
        String rev = new StringBuilder(s).reverse().toString();

        System.out.println(s.equals(rev) ? "Palindrome" : "Not Palindrome");
        // Palindrome
    }

    // ## ✅ Armstrong Number Check (3-digit)
    public static void fnArmstrongNumberCheck () {
        int n = 12;
        int temp = n, sum = 0;

        while (temp != 0) {
            int d = temp % 10;
            sum += d * d * d;
            temp /= 10;
        }

        System.out.println(sum == n ? "Armstrong" : "Not Armstrong");
    }

    // Prime Number Check
    public static void fnPrimeNumberCheck() {
        int n = 12;

        if (n < 2) {
            System.out.println("Not Prime");
            return;
        }

        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                System.out.println("Not Prime");
                return;
            }
        }

        System.out.println("Prime");
    }

    // ## ✅ Print All Prime Numbers Between 1 and N
    public static void fnPrintAllPrimeNumbers() {
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
        // Prime numbers between 1 and 100: 2 3 5 7 11 13 17 19 23 29 31 37 41 43 47 53 59 61 67 71 73 79 83 89 97
    }

    // ## ✅ Multiplication Table of a Number
    public static void fnMultiplicationTableFfNumber() {
        int n = 5;

        for (int i = 1; i <= 10; i++) {
            System.out.println(n + " x " + i + " = " + (n * i));
        }

        // 5 x 1 = 5
        // 5 x 2 = 10
        // 5 x 3 = 15
        // 5 x 4 = 20
        // 5 x 5 = 25
    }

    // ## ✅ Sum All Elements of an Array
    public static void fnSumAllElements() {
        int[] arr = {1, 2, 3, 4, 5};  // Example array
        int sum = 0;

        // Loop through the array to sum all elements
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }

        System.out.println("Sum of all elements in the array: " + sum);
        // Sum of all elements in the array: 15
    }

    // ## ✅ Sum of Digits of a Number
    public static void fnSumOfDigitsOfNumber() {
        int n = 100;
        int sum = 0;

        while (n != 0) {
            sum += n % 10;
            n /= 10;
        }

        System.out.println(sum);
        // 5
    }

    // ✅ Print Numbers 1 to 100W ithout Loop (Using Recursion)
    public static void fnPrintNumbersWithoutLoop (int i) {
        if (i > 10) return;

        System.out.println(i);
        fnPrintNumbersWithoutLoop(i + 1);
        // 12345678910
    }

    // ## ✅ Square Pattern (`****`)
    public static void fnSquarePattern () {
        int n = 4;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
        // ****
        // ****
        // ****
        // ****
    }

    // ## ✅ Right Triangle Pattern
    public static void fnRightTrianglePattern() {
        int n = 5;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
        // *
        // **
        // ***
        // ****
        // *****
    }

    // ## ✅ Inverted Triangle Pattern
    public static void fnIvertedTrianglePattern() {
        int n = 5;

        for (int i = n; i >= 1; i--) {
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
        
        // *****
        // ****
        // ***
        // **
        // *
    }

    //## ✅ Pyramid Pattern
    public static void fnPyramidPattern() {
        int n = 5;

        for (int i = 1; i <= n; i++) {
            for (int s = 1; s <= n - i; s++) {
                System.out.print(" ");
            }

            for (int j = 1; j <= 2 * i - 1; j++) {
                System.out.print("*");
            }

            System.out.println();
        }
        //     *
        //    ***
        //   *****
        //  *******
        // *********           
    }

    // ## ✅ Diamond Pattern
    public static void fnDiamondPattern() {
        int n = 5;

        // Upper half
        for (int i = 1; i <= n; i++) {
            for (int s = 1; s <= n - i; s++) {
                System.out.print(" ");
            }

            for (int j = 1; j <= 2 * i - 1; j++) {
                System.out.print("*");
            }

            System.out.println();
        }

        // Lower half
        for (int i = n - 1; i >= 1; i--) {
            for (int s = 1; s <= n - i; s++) {
                System.out.print(" ");
            }

            for (int j = 1; j <= 2 * i - 1; j++) {
                System.out.print("*");
            }

            System.out.println();
        }
        //     *
        //    ***
        //   *****
        //  *******
        // *********
        //  *******
        //   *****
        //    ***
        //     *
    }

    // ## ✅ Number Pattern (1, 12, 123, ...)
    public static void fnNumberPattern () {
        int n = 5;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j);
            }
            System.out.println();
        }
        // 1
        // 12
        // 123
        // 1234
        // 12345
    }

    // ## ✅ Alphabet Pattern (A, AB, ABC, ...)
    public static void fnAlphabetPattern () {
        int n = 5;

        for (int i = 1; i <= n; i++) {
            for (char c = 'A'; c < 'A' + i; c++) {
                System.out.print(c);
            }
            System.out.println();
        }
        // A
        // AB
        // ABC
        // ABCD
        // ABCDE
    }

    // ## ✅ Find Largest and Smallest Element in an Array
    public static void fnFindLargestAndSmallestElement () {
        int[] arr = {5, 2, 9, -1, 7};

        int min = arr[0];
        int max = arr[0];

        for (int v : arr) {
            if (v < min) min = v;
            if (v > max) max = v;
        }

        System.out.println("Min = " + min + ", Max = " + max);
        // Min = -1, Max = 9
    }
    

    // ## ✅ Reverse an Array
    public static void fneRverseArray() {
        int[] arr = {1, 2, 3, 4, 5};
        int[] reversed = new int[arr.length];
    
        int index = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            reversed[index++] = arr[i];
        }
    
        System.out.println(Arrays.toString(reversed));
        // [5, 4, 3, 2, 1]
    }

    // ## ✅ Sort an Array (Ascending / Descending)
    public static void fnSortAnArrayWithBuildMethodAndWithoutMethod () {
        // with in built function
        Integer[] arrData = {5, 1, 4, 2, 3};

        // Ascending
        Arrays.sort(arrData);
        System.out.println(Arrays.toString(arrData));

        // Descending
        Arrays.sort(arrData, Collections.reverseOrder());
        System.out.println(Arrays.toString(arrData));
        
        // [1, 2, 3, 4, 5]
        // [5, 4, 3, 2, 1]
        
        // Using loop  =========
        int[] arr = {5, 2, 9, 1, 5, 6};

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }

        // 1 2 5 5 6 9
    }

    // ## ✅ Find the Duplicate Characters in a String
    public static void fnDuplicateCharacters() {
        // Way 1 ====
		String str = "programming";
		char[] chars = str.toCharArray();

		System.out.print("Duplicate characters: ");

		for (int i = 0; i < chars.length; i++) {
			for (int j = i + 1; j < chars.length; j++) {
				if (chars[i] == chars[j]) {
					System.out.print(chars[i] + " ");
					break; // avoid printing same character again
				}
			}
		}

        // Duplicate characters: r g m 

        // way 2 ========
        String s = "programming";

        Set<Character> set = new LinkedHashSet<>();
        StringBuilder sb = new StringBuilder();

        for (char ch : s.toCharArray()) {
            if (set.add(ch)) {
                sb.append(ch);
            }
        }

        System.out.println(sb.toString());
        // progamin
	}

    // ## ✅ Find Duplicate Elements in an Array
    public static void fnFindDuplicateElementsWithInbuildMethodandWithout () {
        // With Inbuild functions
        int[] arr = {1, 2, 3, 1, 2, 4, 5};

        Set<Integer> seen = new HashSet<>();
        Set<Integer> duplicates = new HashSet<>();

        for (int v : arr) {
            if (!seen.add(v)) {
                duplicates.add(v);
            }
        }

        System.out.println(duplicates);
        // [1, 2]
        
        // using without inbuilt method ======
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
        // 2 5 
    }

    // ## ✅ Find Missing Number in an Array (1 to N)
    public static void fnFindMissingNumberInArray () {
        int[] arr = {1, 2, 4, 5};
        int n = 5;

        int totalSum = n * (n + 1) / 2;
        int arrSum = 0;

        for (int v : arr) {
            arrSum += v;
        }

        System.out.println(totalSum - arrSum);
        // 3
    }

    // ## ✅ Find Second Largest Element in an Array
    public static void fnSecondHighestNumber() {
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
        // Second-highest number: 20
    }

    // ## ✅ Matrix Addition, Subtraction, and Multiplication
    public static void fnMatrixAdditionSubtractionMultiplication() {
        int[][] A = {{1, 2}, {3, 4}};
        int[][] B = {{5, 6}, {7, 8}};
        int n = 2;

        int[][] add = new int[n][n];
        int[][] sub = new int[n][n];
        int[][] mul = new int[n][n];

        // Addition & Subtraction
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                add[i][j] = A[i][j] + B[i][j];
                sub[i][j] = A[i][j] - B[i][j];
            }
        }

        // Multiplication
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    mul[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        // Print multiplication result
        for (int[] row : mul) {
            for (int v : row) {
                System.out.print(v + " ");
            }
            System.out.println();
        }
        
        // 19 22 
        // 43 50 
    }

    // ## ✅ Transpose of a Matrix
    public static void fnTransposeOfMatrix() {
        int[][] A = {{1, 2, 3}, {4, 5, 6}};
        int rows = A.length;
        int cols = A[0].length;

        int[][] T = new int[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                T[j][i] = A[i][j];
            }
        }

        for (int[] row : T) {
            for (int v : row) {
                System.out.print(v + " ");
            }
            System.out.println();
        }
        
        // 1 4 
        // 2 5 
        // 3 6 
    }

    // ## ✅ Count Vowels and Consonants in a String
    public static void fnVowelConsonantCount() {
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

        // Vowels: 3
        // Consonants: 7
    }

    // ## ✅ Find First Non-Repeated Character in a String
    public static void fnFirstNonRepeatedChar () {
        // way one ==================
        String str = "stress";
        char result = ' ';
        char[] strArr = str.toCharArray();

        for (int i = 0; i < strArr.length; i++) {
            char ch = strArr[i];
            boolean repeated = false;

            for (int j = 0; j < strArr.length; j++) {
                if (i != j && ch == strArr[j]) {
                    repeated = true;
                    break;
                }
            }

            if (!repeated) {
                result = ch;
                break;
            }
        }

        System.out.println(result); // Output: t

        // way two ==================
        String s = "swiss";
        Map<Character, Integer> map = new LinkedHashMap<>();

        for (char ch : s.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        for (Map.Entry<Character, Integer> e : map.entrySet()) {
            if (e.getValue() == 1) {
                System.out.println(e.getKey());
                return;
            }
        }

        System.out.println("None");
        // w
    }

    // ## ✅ Check Anagram Strings
    public static void fnAnagramCheck() {
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
        // listen and silent are anagrams.
    }

    // ## ✅ Find the Common Elements Between Two Arrays
    public static void fnCommonElementsInTwoArrays() {
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
        // 3 4 5 
    }

    // ✅ Find Substring in a String (Without using contains())
    static void fnFindSubstring(String sub, String s) {
        // Way one: without contain
        boolean found = false;

        for (int i = 0; i + sub.length() <= s.length(); i++) {
            int j = 0;

            while (j < sub.length() && s.charAt(i + j) == sub.charAt(j)) {
                j++;
            }

            if (j == sub.length()) {
                found = true;
                break; // substring found, no need to check further
            }
        }
        System.out.println(found); // Print the result - true

        // way two: using contain
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

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


## ✅ Class and Object Demo

```java
class Person {
    String name;
    int age;

    void introduce() {
        System.out.println(name + ", " + age);
    }
}

public class Main {
    public static void main(String[] args) {
        Person p = new Person();
        p.name = "Alice";
        p.age = 25;
        p.introduce();
    }
}
```

## ✅ Constructor Demo (Default, Parameterized, Copy)

```java
class Box {
    int w, h;

    Box() {
        this.w = 1;
        this.h = 1;
    }

    Box(int w, int h) {
        this.w = w;
        this.h = h;
    }

    Box(Box b) {
        this.w = b.w;
        this.h = b.h;
    }

    int area() {
        return w * h;
    }
}

public class Main {
    public static void main(String[] args) {
        Box a = new Box();
        Box b = new Box(2, 3);
        Box c = new Box(b);

        System.out.println(a.area() + " " + b.area() + " " + c.area());
    }
}
```

## ✅ Method Overloading & Overriding

```java
class Calc {
    int add(int a, int b) {
        return a + b;
    }

    int add(int a, int b, int c) {   // Overloading
        return a + b + c;
    }
}

class AdvCalc extends Calc {
    @Override
    int add(int a, int b) {           // Overriding
        return super.add(a, b) + 1;
    }
}

public class Main {
    public static void main(String[] args) {
        Calc c = new Calc();
        AdvCalc ac = new AdvCalc();

        System.out.println(c.add(1, 2));
        System.out.println(ac.add(1, 2));
    }
}
```

## ✅ Inheritance Example (Single, Multilevel, Hierarchical)

```java
class A {
    void f() {
        System.out.println("A");
    }
}

class B extends A {                  // Single
    void g() {
        System.out.println("B");
    }
}

class C extends B {                  // Multilevel
    void h() {
        System.out.println("C");
    }
}

class D extends A {                  // Hierarchical
    void i() {
        System.out.println("D");
    }
}

public class Main {
    public static void main(String[] args) {
        C c = new C();
        c.f();
        c.g();
        c.h();

        D d = new D();
        d.f();
        d.i();
    }
}
```

## ✅ Abstract Class Program

```java
abstract class Shape {
    abstract double area();
}

class Circle extends Shape {
    double r;

    Circle(double r) {
        this.r = r;
    }

    double area() {
        return Math.PI * r * r;
    }
}

public class Main {
    public static void main(String[] args) {
        Shape s = new Circle(2.0);
        System.out.println(s.area());
    }
}
```

## ✅ Interface Example

```java
interface Drawable {
    void draw();
}

class Square implements Drawable {
    public void draw() {
        System.out.println("Drawing square");
    }
}

public class Main {
    public static void main(String[] args) {
        Drawable d = new Square();
        d.draw();
    }
}
```

## ✅ Encapsulation (Getters & Setters)

```java
class Employee {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

public class Main {
    public static void main(String[] args) {
        Employee e = new Employee();
        e.setId(1);
        e.setName("Bob");

        System.out.println(e.getId() + ": " + e.getName());
    }
}
```

## ✅ Polymorphism Demo

```java
class Animal {
    void sound() {
        System.out.println("some sound");
    }
}

class Dog extends Animal {
    void sound() {
        System.out.println("bark");
    }
}

class Cat extends Animal {
    void sound() {
        System.out.println("meow");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal a = new Dog();
        a.sound();

        a = new Cat();
        a.sound();
    }
}
```

## ✅ Static Keyword Demo

```java
public class Main {

    static int count = 0;

    public Main() {
        count++;
    }

    public static void main(String[] args) {
        new Main();
        new Main();

        System.out.println(Main.count);
    }
}
```

## ✅ `this` and `super` Keyword Usage

```java
class Base {
    int x = 10;

    Base(int x) {
        this.x = x;
    }
}

class Derived extends Base {
    int x = 20;

    Derived() {
        super(5);
        System.out.println(this.x + ", " + super.x);
    }
}

public class Main {
    public static void main(String[] args) {
        new Derived();
    }
}
```

## ✅ Try–Catch–Finally Example

```java
public class Main {
    public static void main(String[] args) {

        try {
            int x = 10 / 0;
        } catch (ArithmeticException e) {
            System.out.println("Cannot divide by zero");
        } finally {
            System.out.println("Finally always runs");
        }
    }
}
```

## ✅ `throw` and `throws` Usage

```java
public class Main {

    static int div(int a, int b) throws ArithmeticException {
        if (b == 0) {
            throw new ArithmeticException("b cannot be zero");
        }
        return a / b;
    }

    public static void main(String[] args) {
        System.out.println(div(10, 2));
    }
}
```

## ✅ Custom Exception Program

```java
class InvalidAgeException extends Exception {
    InvalidAgeException(String msg) {
        super(msg);
    }
}

public class Main {

    static void vote(int age) throws InvalidAgeException {
        if (age < 18) {
            throw new InvalidAgeException("Underage");
        }
    }

    public static void main(String[] args) {
        try {
            vote(16);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
```

## ✅ Read from a File

```java
import java.io.BufferedReader;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) throws Exception {

        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            String line;

            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }
    }
}
```

## ✅ Write to a File

```java
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Main {
    public static void main(String[] args) throws Exception {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
            bw.write("Hello file");
        }
    }
}
```

## ✅ Count Words in a File

```java
import java.io.BufferedReader;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) throws Exception {

        int words = 0;

        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                if (parts.length == 1 && parts[0].isEmpty()) continue;
                words += parts.length;
            }
        }

        System.out.println(words);
    }
}
```

## ✅ Copy Content of One File to Another

```java
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Main {
    public static void main(String[] args) throws Exception {

        try (
            BufferedInputStream in =
                    new BufferedInputStream(new FileInputStream("in.txt"));
            BufferedOutputStream out =
                    new BufferedOutputStream(new FileOutputStream("out.txt"))
        ) {
            byte[] buffer = new byte[4096];
            int len;

            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
        }
    }
}
```

## ✅ ArrayList Demo (Add, Remove, Iterate)

```java
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<String> list = new ArrayList<>();

        list.add("a");
        list.add("b");
        list.remove("a");

        for (String s : list) {
            System.out.println(s);
        }
    }
}
```

## ✅ LinkedList Demo

```java
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {

        LinkedList<Integer> queue = new LinkedList<>();

        queue.add(1);
        queue.addFirst(0);
        queue.addLast(2);

        System.out.println(queue);
    }
}
```

## ✅ HashSet & TreeSet Demo

```java
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {

        Set<Integer> hs = new HashSet<>();
        hs.add(3);
        hs.add(1);
        hs.add(3);

        System.out.println(hs);

        Set<Integer> ts = new TreeSet<>(hs);
        System.out.println(ts);
    }
}
```

## ✅ HashMap & TreeMap Demo

```java
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {

        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 2);

        System.out.println(map);

        Map<String, Integer> tmap = new TreeMap<>(map);
        System.out.println(tmap);
    }
}
```

## ✅ Iterate Through HashMap (entrySet, keySet, values)

```java
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        Map<String, Integer> map = new HashMap<>();
        map.put("x", 10);
        map.put("y", 20);

        for (Map.Entry<String, Integer> e : map.entrySet()) {
            System.out.println(e.getKey() + "=" + e.getValue());
        }

        for (String key : map.keySet()) {
            System.out.println(key);
        }

        for (Integer value : map.values()) {
            System.out.println(value);
        }
    }
}
```

## ✅ Sort Elements Using `Collections.sort()`

```java
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(5, 3, 1, 4, 2);

        Collections.sort(list);
        System.out.println(list);

        Collections.sort(list, Collections.reverseOrder());
        System.out.println(list);
    }
}
```

## ✅ Convert Array to List and Vice Versa

```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        String[] arr = {"a", "b", "c"};

        List<String> list = new ArrayList<>(Arrays.asList(arr));
        String[] back = list.toArray(new String[0]);

        System.out.println(list + " | " + Arrays.toString(back));
    }
}
```

## ✅ Frequency of Elements Using HashMap

```java
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        int[] arr = {1, 2, 2, 3, 3, 3};

        Map<Integer, Integer> freq = new HashMap<>();

        for (int v : arr) {
            freq.put(v, freq.getOrDefault(v, 0) + 1);
        }

        System.out.println(freq);
    }
}
```

## ✅ Remove Duplicates from List Using Set

```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Integer> list =
                new ArrayList<>(Arrays.asList(1, 2, 2, 3, 3, 4));

        List<Integer> unique =
                new ArrayList<>(new LinkedHashSet<>(list));

        System.out.println(unique);
    }
}
```

## ✅ Reverse Words in a Sentence

```java
public class Main {
    public static void main(String[] args) {

        String s = "Java is fun";
        String[] parts = s.split("\\s+");

        StringBuilder sb = new StringBuilder();

        for (int i = parts.length - 1; i >= 0; i--) {
            sb.append(parts[i]);
            if (i != 0) {
                sb.append(" ");
            }
        }

        System.out.println(sb.toString());
    }
}
```

## ✅ Check Balanced Parentheses Using Stack

```java
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class Main {

    static boolean balanced(String s) {

        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');

        Deque<Character> stack = new ArrayDeque<>();

        for (char ch : s.toCharArray()) {

            if (map.containsValue(ch)) {
                stack.push(ch);
            } else if (map.containsKey(ch)) {
                if (stack.isEmpty() || stack.pop() != map.get(ch)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(balanced("{[()]}"));
    }
}
```

## ✅ Find Factorial Using Recursion

```java
public class Main {

    static long factorial(int n) {
        return n <= 1 ? 1 : n * factorial(n - 1);
    }

    public static void main(String[] args) {
        System.out.println(factorial(5));
    }
}
```

## ✅ Find Nth Fibonacci Number Using Recursion

```java
public class Main {

    static long fibonacci(int n) {
        return n <= 1 ? n : fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static void main(String[] args) {
        System.out.println(fibonacci(10));
    }
}
```

## ✅ Binary Search Implementation

```java
public class Main {

    static int binarySearch(int[] arr, int key) {

        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (arr[mid] == key) {
                return mid;
            }

            if (arr[mid] < key) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {

        int[] arr = {1, 3, 5, 7, 9};
        System.out.println(binarySearch(arr, 7));
    }
}
```

## ✅ Linear Search Implementation

```java
public class Main {

    static int linearSearch(int[] arr, int key) {

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {

        int[] arr = {4, 2, 7, 1};
        System.out.println(linearSearch(arr, 7));
    }
}
```

## ✅ Bubble Sort, Selection Sort, Insertion Sort

```java
import java.util.Arrays;

public class Main {

    static void bubbleSort(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < a.length - 1 - i; j++) {
                if (a[j] > a[j + 1]) {
                    int t = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = t;
                }
            }
        }
    }

    static void selectionSort(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < a[min]) {
                    min = j;
                }
            }
            int t = a[i];
            a[i] = a[min];
            a[min] = t;
        }
    }

    static void insertionSort(int[] a) {
        for (int i = 1; i < a.length; i++) {
            int key = a[i];
            int j = i - 1;

            while (j >= 0 && a[j] > key) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        int[] a = {5, 1, 4, 2, 8};

        int[] b1 = a.clone();
        bubbleSort(b1);
        System.out.println("Bubble Sort: " + Arrays.toString(b1));

        int[] b2 = a.clone();
        selectionSort(b2);
        System.out.println("Selection Sort: " + Arrays.toString(b2));

        int[] b3 = a.clone();
        insertionSort(b3);
        System.out.println("Insertion Sort: " + Arrays.toString(b3));
    }
}
```

## ✅ Quick Sort & Merge Sort

```java
import java.util.Arrays;

public class Main {

    static void quickSort(int[] a, int left, int right) {
        if (left >= right) return;

        int i = left, j = right;
        int pivot = a[left + (right - left) / 2];

        while (i <= j) {
            while (a[i] < pivot) i++;
            while (a[j] > pivot) j--;

            if (i <= j) {
                int t = a[i];
                a[i] = a[j];
                a[j] = t;
                i++;
                j--;
            }
        }

        if (left < j) quickSort(a, left, j);
        if (i < right) quickSort(a, i, right);
    }

    static void mergeSort(int[] a, int left, int right) {
        if (left >= right) return;

        int mid = (left + right) / 2;

        mergeSort(a, left, mid);
        mergeSort(a, mid + 1, right);

        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            temp[k++] = (a[i] <= a[j]) ? a[i++] : a[j++];
        }

        while (i <= mid) temp[k++] = a[i++];
        while (j <= right) temp[k++] = a[j++];

        System.arraycopy(temp, 0, a, left, temp.length);
    }

    public static void main(String[] args) {
        int[] a = {5, 2, 9, 1, 5, 6};

        int[] q = a.clone();
        quickSort(q, 0, q.length - 1);
        System.out.println("Quick Sort: " + Arrays.toString(q));

        int[] m = a.clone();
        mergeSort(m, 0, m.length - 1);
        System.out.println("Merge Sort: " + Arrays.toString(m));
    }
}
```

## ✅ Count Occurrences of Each Word in a String

```java
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        String s = "to be or not to be";
        Map<String, Integer> map = new LinkedHashMap<>();

        for (String word : s.toLowerCase().split("\\s+")) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        System.out.println(map);
    }
}
```

## ✅ Longest Substring Without Repeating Characters

```java
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        String s = "abcabcbb";

        Map<Character, Integer> indexMap = new HashMap<>();
        int left = 0;
        int maxLength = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            if (indexMap.containsKey(c) && indexMap.get(c) >= left) {
                left = indexMap.get(c) + 1;
            }

            indexMap.put(c, right);
            maxLength = Math.max(maxLength, right - left + 1);
        }

        System.out.println(maxLength);
    }
}
```

## ✅ LRU Cache Implementation using `LinkedHashMap`

```java
import java.util.LinkedHashMap;
import java.util.Map;

class LRU<K, V> extends LinkedHashMap<K, V> {

    private final int capacity;

    LRU(int capacity) {
        super(capacity, 0.75f, true); // accessOrder = true
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }
}

public class Main {
    public static void main(String[] args) {

        LRU<Integer, Integer> cache = new LRU<>(2);

        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);      // access 1
        cache.put(3, 3);   // evicts key 2

        System.out.println(cache.keySet()); // [1, 3]
    }
}
```

## ✅ Producer–Consumer Problem using Threads

```java
import java.util.concurrent.ArrayBlockingQueue;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(2);

        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    queue.put(i);
                    System.out.println("Produced: " + i);
                }
            } catch (InterruptedException ignored) {}
        });

        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    System.out.println("Consumed: " + queue.take());
                }
            } catch (InterruptedException ignored) {}
        });

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();
    }
}
```

## ✅ Deadlock Example in Java

```java
public class Main {
    public static void main(String[] args) throws InterruptedException {

        final Object A = new Object();
        final Object B = new Object();

        Thread t1 = new Thread(() -> {
            synchronized (A) {
                try { Thread.sleep(100); } catch (Exception ignored) {}
                synchronized (B) {
                    System.out.println("Thread 1 acquired A and B");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (B) {
                try { Thread.sleep(100); } catch (Exception ignored) {}
                synchronized (A) {
                    System.out.println("Thread 2 acquired B and A");
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}
```

🔹 **Interview Tip**:
Deadlock occurs due to
1️⃣ Mutual exclusion
2️⃣ Hold and wait
3️⃣ No preemption
4️⃣ Circular wait

## ✅ Singleton Design Pattern (Thread-Safe)

```java
class Singleton {

    private static volatile Singleton instance;

    private Singleton() {}

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println(Singleton.getInstance());
    }
}
```

## ✅ Create Thread using `Thread` Class

```java
public class Main extends Thread {

    @Override
    public void run() {
        System.out.println("Hello from Thread");
    }

    public static void main(String[] args) {
        new Main().start();
    }
}
```

## ✅ Create Thread using `Runnable` Interface

```java
public class Main {

    public static void main(String[] args) {
        Runnable r = () -> System.out.println("Hello from Runnable");
        new Thread(r).start();
    }
}
```

## ✅ Synchronization Example

```java
class Counter {

    int count = 0;

    synchronized void increment() {
        count++;
    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Counter counter = new Counter();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(counter.count);
    }
}
```

## ✅ Inter-Thread Communication (`wait()` / `notify()`)

```java
class Shared {

    private int data;
    private boolean ready = false;

    synchronized void produce(int value) throws InterruptedException {
        while (ready) {
            wait();
        }
        data = value;
        ready = true;
        notify();
    }

    synchronized int consume() throws InterruptedException {
        while (!ready) {
            wait();
        }
        ready = false;
        notify();
        return data;
    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Shared shared = new Shared();

        Thread producer = new Thread(() -> {
            try {
                shared.produce(42);
            } catch (InterruptedException ignored) {}
        });

        Thread consumer = new Thread(() -> {
            try {
                System.out.println(shared.consume());
            } catch (InterruptedException ignored) {}
        });

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();
    }
}
```

## ✅ Thread Pool Example (`ExecutorService`)

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {

        ExecutorService pool = Executors.newFixedThreadPool(2);

        for (int i = 1; i <= 4; i++) {
            final int taskId = i;
            pool.submit(() ->
                System.out.println("Task " + taskId + " executed by " +
                        Thread.currentThread().getName())
            );
        }

        pool.shutdown();
    }
}
```

## ✅ Lambda Expression Example

```java
interface Operation {
    int apply(int a, int b);
}

public class Main {
    public static void main(String[] args) {

        Operation add = (a, b) -> a + b;
        System.out.println(add.apply(2, 3));
    }
}
```

## ✅ Functional Interfaces (`Predicate`, `Function`, `Consumer`)

```java
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {

        Predicate<Integer> isEven = x -> x % 2 == 0;
        Function<Integer, Integer> square = x -> x * x;
        Consumer<Integer> print = System.out::println;

        if (isEven.test(4)) {
            print.accept(square.apply(4));
        }
    }
}
```

## ✅ Streams API (`filter`, `map`, `reduce`, `collect`)

```java
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5);

        int sumSquaresOfEven = nums.stream()
                .filter(x -> x % 2 == 0)
                .map(x -> x * x)
                .reduce(0, Integer::sum);

        List<Integer> doubled = nums.stream()
                .map(x -> x * 2)
                .collect(Collectors.toList());

        System.out.println(sumSquaresOfEven + " " + doubled);
    }
}
```

## ✅ Method References

```java
import java.util.*;

public class Main {

    static void print(Integer x) {
        System.out.println(x);
    }

    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(1, 2, 3);
        list.forEach(Main::print);
    }
}
```

## ✅ Optional Class Usage

```java
import java.util.Optional;

public class Main {
    public static void main(String[] args) {

        Optional<String> optional = Optional.ofNullable(null);
        System.out.println(optional.orElse("default"));
    }
}
```

## ✅ Library Management System (Mini Demo)

```java
import java.util.*;

class Book {
    String id;
    String title;
    boolean issued;

    Book(String id, String title) {
        this.id = id;
        this.title = title;
    }
}

class Library {

    Map<String, Book> books = new HashMap<>();

    void add(Book book) {
        books.put(book.id, book);
    }

    boolean issue(String id) {
        Book book = books.get(id);
        if (book != null && !book.issued) {
            book.issued = true;
            return true;
        }
        return false;
    }

    void list() {
        books.values().forEach(
            b -> System.out.println(b.id + " : " + b.title + " : " + b.issued)
        );
    }
}

public class Main {
    public static void main(String[] args) {

        Library lib = new Library();
        lib.add(new Book("1", "Java Basics"));
        lib.add(new Book("2", "DSA"));

        lib.issue("1");
        lib.list();
    }
}
```

## ✅ Banking System (Deposit, Withdraw, Balance)

```java
class Account {

    private int balance;

    Account(int balance) {
        this.balance = balance;
    }

    synchronized void deposit(int amount) {
        balance += amount;
    }

    synchronized boolean withdraw(int amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    synchronized int getBalance() {
        return balance;
    }
}

public class Main {
    public static void main(String[] args) {

        Account account = new Account(1000);
        account.deposit(500);
        account.withdraw(200);

        System.out.println(account.getBalance());
    }
}
```

## ✅ Employee Management System (Mini)

```java
import java.util.*;

class Employee {
    int id;
    String name;

    Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

class EMS {

    Map<Integer, Employee> employees = new HashMap<>();

    void add(Employee e) {
        employees.put(e.id, e);
    }

    Employee get(int id) {
        return employees.get(id);
    }
}

public class Main {
    public static void main(String[] args) {

        EMS ems = new EMS();
        ems.add(new Employee(1, "Ana"));

        System.out.println(ems.get(1).name);
    }
}
```

## ✅ Student Management System (mini)

```java
import java.util.*;

class Student {
    int id;
    String name;

    Student(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

class SMS {
    Map<Integer, Student> map = new HashMap<>();

    void add(Student s) {
        map.put(s.id, s);
    }

    Student get(int id) {
        return map.get(id);
    }
}

public class Main {
    public static void main(String[] args) {
        SMS sms = new SMS();
        sms.add(new Student(1, "Max"));
        System.out.println(sms.get(1).name);
    }
}
```
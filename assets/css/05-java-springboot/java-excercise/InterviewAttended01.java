import java.security.Key;
import java.util.*;
import java.util.function.Function;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Map;


// ## ✅ Hello World Program
public class InterviewAttended01 {
    public static void main(String[] args) {
        // System.out.println("Hello, World!");
        // Qn. ✅ ===================================
        // mostUsedWord()

        // Qn. ✅ ===================================
        // SecondHighestNumber()

        // Qn. ✅ ===================================
        // findEvenNumbers()

        // Qn. ✅ ===================================
        // sortEmployeeBySalaryandDepartment();

        // Qn. ✅====================================
        // Program to find first non-repeated character using java8 streams 
        // firstNonRepeatedCharacter();

        // Qn. ✅ ===================================
        // int[] nums = {1,2,3,4,5,6,7};
        // int k = 3;

        // rotate(nums, k);

        // System.out.println(Arrays.toString(nums));
        // Output: [5, 6, 7, 1, 2, 3, 4]

        // Qn. ✅ ===================================
        // Search in Rotated Sorted Array
        // int[] nums1 = {4,5,6,7,0,1,2};
        // System.out.println(search(nums1, 0));  // Output: 4

        // int[] nums2 = {4,5,6,7,0,1,2};
        // System.out.println(search(nums2, 3));  // Output: -1

        // int[] nums3 = {1};
        // System.out.println(search(nums3, 0));  // Output: -1

        // Qn. ✅ ===================================
        // productExceptSelf(new int[]{1,2,3,4});  // Output: [24, 12, 8, 6]

        // Qn. ✅ ===================================
        // removeStars("leet**cod*e");  // Output: "lecoe"
        
    }

    // Qn. ✅ Find the most used word using Java 8 Streams
    public static void mostUsedWord() {
        String str = "Ram is employee of ABC company, ram is from Blore, RAM! is good in algorithms.";

        Map<String, Long> wordCount =
                Arrays.stream(str.toLowerCase().replaceAll("[^a-z ]", "").split("\\s+"))
                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        String mostUsedWord = wordCount.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .get()
                .getKey();

        System.out.println("Word Count: " + wordCount);
        System.out.println("Most Used Word: " + mostUsedWord);
        
        // Word Count: {ram=3, is=3, employee=1, of=1, abc=1, company=1, from=1, blore=1, good=1, in=1, algorithms=1}
        // Most Used Word: ram
    }


    // Qn. ✅ Find the Sceond-Highest Number in an Array
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

    // Qn. ✅ Find the even numbers from this array using stream
    public static void findEvenNumbers() {
        int[] arr = {10, 5, 20, 15, 30};

        List<Integer> evenNumbers = Arrays.stream(arr)
                .filter(num -> num % 2 == 0)
                .boxed()
                .collect(Collectors.toList());

        System.out.println("Even Numbers: " + evenNumbers);
    }

    // Qn. ✅ Top 3 highest paid employee, employee list by department
    public static void sortEmployeeBySalaryandDepartment() {

        List<Employee> employees = List.of(
            new Employee(101, "Alice Johnson", "HR", 55000.0),
            new Employee(102, "Bob Smith", "IT", 72000.0),
            new Employee(103, "Charlie Brown", "Finance", 68000.0),
            new Employee(104, "Diana Prince", "Marketing", 60000.0),
            new Employee(105, "Ethan Hunt", "Operations", 75000.0),
            new Employee(106, "Fiona Gallagher", "Sales", 58000.0),
            new Employee(107, "George Miller", "IT", 82000.0),
            new Employee(108, "Hannah Davis", "Finance", 64000.0),
            new Employee(109, "Ian Wright", "HR", 53000.0),
            new Employee(110, "Julia Roberts", "Marketing", 70000.0)
        );

        // Qn. ✅ Highest Paid Employees
        // Comparator is used to define custom sorting logic for objects. comparingDouble() is a static method inside:
        System.out.println("\nTop 3 Highest Paid Employees:");
        employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                // .sorted((e1, e2) -> Double.compare(e2.getSalary(), e1.getSalary()))
                .limit(3)
                .forEach(System.out::println);


        // Employee List Grouped By Department
        System.out.println("\nEmployees Grouped By Department:");

        Map<String, List<Employee>> grouped =
                employees.stream()
                        .collect(Collectors.groupingBy(Employee::getDepartment));

        grouped.forEach((dept, empList) -> {
            System.out.println("\nDepartment: " + dept);
            empList.forEach(System.out::println);
        });
    }

    // Qn. ✅ Program to find first non-repeated character using java8 streams 
    public static void firstNonRepeatedCharacter() {
        String str = "character";

        Character result = str.chars()                 // IntStream
                .mapToObj(c -> (char) c)               // convert to Character
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        LinkedHashMap::new,            // maintain insertion order
                        Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() == 1) // non repeated
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);

        System.out.println(result);
    }


    // ✅ rotate the array to the right by k steps, where k is non-negative
    public static void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;  // Important when k > n
        
        reverse(nums, 0, n - 1);      // Step 1
        reverse(nums, 0, k - 1);      // Step 2
        reverse(nums, k, n - 1);      // Step 3
    }

    private static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    // ===========================================================
    // ✅  Search in Rotated Sorted Array
    // There is an integer array nums sorted in ascending order (with distinct values).
    
    // Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
    
    // Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.
    
    // You must write an algorithm with O(log n) runtime complexity.
    

    // Example 1:

    // Input: nums = [4,5,6,7,0,1,2], target = 0
    // Output: 4
    // Example 2:
    
    // Input: nums = [4,5,6,7,0,1,2], target = 3
    // Output: -1
    // Example 3:

    // Input: nums = [1], target = 0
    // Output: -1

    public static int search(int[] nums, int target) {

        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            // ✅ Target found
            if (nums[mid] == target) {
                return mid;
            }

            // ✅ Left half is sorted
            if (nums[left] <= nums[mid]) {

                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;  // Search left
                } else {
                    left = mid + 1;   // Search right
                }

            }
            // ✅ Right half is sorted
            else {

                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;   // Search right
                } else {
                    right = mid - 1;  // Search left
                }
            }
        }

        return -1;  // Not found
    }

    // ===========================================================
    // Question No: 1	 
    // Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].   
    // The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.   
    // You must write an algorithm that runs in O(n) time and without using the division operation.   
    // Example 1: Input: nums = [1,2,3,4] Output: [24,12,8,6]   
    // Example 2: Input: nums = [-1,1,0,-3,3] Output: [0,0,9,0,0] 

    public int[] productExceptSelf(int[] nums) {

        int n = nums.length;
        int[] answer = new int[n];

        // Step 1: store prefix (left) products
        answer[0] = 1;
        for (int i = 1; i < n; i++) {
            answer[i] = answer[i - 1] * nums[i - 1];
        }

        // Step 2: multiply with suffix (right) products
        int rightProduct = 1;
        for (int i = n - 1; i >= 0; i--) {
            answer[i] *= rightProduct;
            rightProduct *= nums[i];
        }

        return answer;
    }
    
    // ===========================================================
    // Question No 2.	 
    // you are given a string s, which contains stars *. In one operation, you can: · Choose a star in s. · Remove the closest non-star character to its left, as well as remove the star itself.  
    // Return the string after all stars have been removed.   
    // Note:   
    // · The input will be generated such that the operation is always possible.   
    // · It can be shown that the resulting string will always be unique.   
    
    // Example 1:   
    // Input: s = ""leet**cod*e"" Output: ""lecoe"" Explanation: Performing the removals from left to right: - The closest character to the 1st star is 't' in ""leet**cod*e"". s becomes ""lee*cod*e"". - The closest character to the 2nd star is 'e' in ""lee*cod*e"". s becomes ""lecod*e"". - The closest character to the 3rd star is 'd' in ""lecod*e"". s becomes ""lecoe"". There are no more stars, so we return ""lecoe"".    
    
    // Example 2:   
    // Input: s = ""erase*****"" Output: """" Explanation: The entire string is removed, so we return an empty string. 

    public String removeStars(String s) {

        StringBuilder sb = new StringBuilder();

        for (char ch : s.toCharArray()) {
            if (ch == '*') {
                sb.deleteCharAt(sb.length() - 1);
            } else {
                sb.append(ch);
            }
        }

        return sb.toString();
    }


    // ==============================================================
    // Tricky Questions
    // ==============================================================
    // **1️⃣ BreakTest**
    // ```java
    // public class BreakTest {
    //     public static void main(String[] args)
    //     {
    //     int i=1;
    //         if (i==1) {
    //             break; 
    //             }
    //     System.out.println("break");
    //     }
    // }
    // ```

    // ✅ Answer:
    // ❌ **Compilation Error**


    // ## **2️⃣ HelloFind**
    // ```java
    // List<Integer> listOfInt = Arrays.asList(1,1,2,3,3,4);
    // Set<Integer> set = new HashSet();
    // listOfInt.stream()
    //     .filter(n -> !set.add(n))
    //     .forEach(System.out::println);
    // ```
    // ### ✅ Answer:

    // ```
    // 1
    // 3
    // ```

    // ## **3️⃣ Assignment**
    // ```java
    // int k = 0;
    // for (int i = 0; i < 100; i++)
    //     k = k++; 
    // System.out.println(k);
    // System.out.println(k);
    // ```

    // ### ✅ Answer:
    // ```
    // 0
    // 0
    // ```

    // ## **4️⃣ SwitchTest**

    // ```java
    // final static short i = 2;

    // for (int n = 0; n < 3; n++)
    // {
    //     switch (n)
    //     {
    //         case i: System.out.print(" 2 ");
    //         case i-1: System.out.print(" 1 ");
    //         case i-2: System.out.print(" 3 ");  
    //     }
    // }
    // ```


    // ### ✅ Final Output:
    // 3 1 3 2 1 3

    // ==============================================================
    public class Test {
 
        public static void test(int... x) {
            System.out.println("Varargs");
        }
    
        public static void test(int x) {
            System.out.println("Single");
        }
    
        public static void main(String[] args) {
            test();
        }
    }
    // ### ✅ Final Output: Varargs

    // ==============================================================
    class A {
        void show(int i) {
            System.out.println("A int");
        }
    }
    
    class B extends A {
        void show(Integer i) {
            System.out.println("B Integer");
        }
    }
    
    public class IntTest{
        public static void main(String[] args) {
        
            B obj = new B();
            obj.show(null);
            
            A obj2 = new B();
            
            obj2.show(30);
        }
    }

    // ### ✅Output: 
    // B Integer
    // A int

    // ==============================================================
    public class Test { 
    public static void main(String[] args) {
      System.out.println("Hello, World!");
	  
        HashMap<Key, String> map = new HashMap<>();
        Key k1 = new Key(1);
        map.put(k1, "Value");
        k1.id = 2;
        System.out.println(map.get(k1));      
        }
    }
    
    
    class Key {
        int id;
        Key(int id) { this.id = id; }
    
        public int hashCode() { return id; }
    
        public boolean equals(Object o) {
            return ((Key)o).id == this.id;
        }
    }

    // ### ✅Output: null

}



// ====================================================================
// ✅ Employee Class
class Employee {
    private int id;
    private String name;
    private String department;
    private double salary;

    public Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }

    @Override
    public String toString() {
        return id + " - " + name + " - " + department + " - " + salary;
    }
}
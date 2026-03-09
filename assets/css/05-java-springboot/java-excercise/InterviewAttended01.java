import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InterviewAttended01 {

    // Employee List (Accessible to all methods)
    static List<Employee> employees = List.of(
        new Employee(101,"Alice Johnson","HR",55000,"Female","Delhi",28,80),
        new Employee(102,"Bob Smith","IT",72000,"Male","Mumbai",32,60),
        new Employee(103,"Charlie Brown","Finance",68000,"Male","Delhi",29,55),
        new Employee(104,"Diana Prince","Marketing",60000,"Female","Delhi",26,90),
        new Employee(105,"Ethan Hunt","Operations",75000,"Male","Chennai",35,45),
        new Employee(106,"Fiona Gallagher","Sales",58000,"Female","Delhi",27,70),
        new Employee(107,"George Miller","IT",82000,"Male","Pune",31,30),
        new Employee(108,"Hannah Davis","Finance",64000,"Female","Delhi",24,75),
        new Employee(109,"Ian Wright","HR",53000,"Male","Delhi",33,50),
        new Employee(110,"Julia Roberts","Marketing",70000,"Female","Bangalore",29,40)
    );

    public static void main(String[] args) {

        findNameStartsWithA();
        groupByDepartment();
        countEmployees();
        findMaxAge();
        uniqueDepartments();
        countEmployeesEachDepartment();
        employeesBelow30();
        rankBetween50And100();
        averageAgeByGender();
        departmentWithMaxEmployees();
        employeesInDelhiSorted();
        averageRankEachDepartment();
        highestRankEachDepartment();
        sortEmployeesByRank();
        secondHighestRankEmployee();

        // ==========================================================
        findLongestString();
        averageAge();
        checkPrimeNumber();
        mergeSortedLists();
        findIntersection();
        removeDuplicates();
        sumTransactionsByDay();
        kthSmallestElement();
        wordFrequency();
        partitionEvenOdd();
        // ==========================================================

        mostUsedWord();
        SecondHighestNumber();
        findEvenNumbers();
        sortEmployeeBySalaryandDepartment();
        firstNonRepeatedCharacter();

        int[] nums = {1,2,3,4,5,6,7};
        rotate(nums,3);
        System.out.println(Arrays.toString(nums));

        int[] nums1 = {4,5,6,7,0,1,2};
        System.out.println(search(nums1,0));

        System.out.println(Arrays.toString(productExceptSelf(new int[]{1,2,3,4})));

        System.out.println(removeStars("leet**cod*e"));
    }

    // ==========================================================

    // 1️⃣ Employees whose name starts with A
    public static void findNameStartsWithA(){
        List<Employee> result = employees.stream()
                .filter(e -> e.getName().startsWith("A"))
                .collect(Collectors.toList());

        System.out.println("Name starts with A: " + result);

        // Output: Name starts with A: [101 Alice Johnson HR 55000.0 Delhi age:28 rank:80]
    }

    // 2️⃣ Group employees by department
    public static void groupByDepartment(){
        Map<String,List<Employee>> map =
                employees.stream()
                        .collect(Collectors.groupingBy(Employee::getDepartment));

        System.out.println("Employees by Department: " + map);

        //Output: Employees by Department: {HR=[101 Alice Johnson HR 55000.0 Delhi age:28 rank:80, 109 Ian Wright HR 53000.0 Delhi age:33 rank:50], IT=[102 Bob Smith IT 72000.0 Mumbai age:32 rank:60, 107 George Miller IT 82000.0 Pune age:31 rank:30], Finance=[103 Charlie Brown Finance 68000.0 Delhi age:29 rank:55, 108 Hannah Davis Finance 64000.0 Delhi age:24 rank:75], Marketing=[104 Diana Prince Marketing 60000.0 Delhi age:26 rank:90, 110 Julia Roberts Marketing 70000.0 Bangalore age:29 rank:40], Operations=[105 Ethan Hunt Operations 75000.0 Chennai age:35 rank:45], Sales=[106 Fiona Gallagher Sales 58000.0 Delhi age:27 rank:70]}
    }

    // 3️⃣ Count employees
    public static void countEmployees(){
        long count = employees.stream().count();
        System.out.println("Total Employees: " + count);

        // Output: Total Employees: 10
    }

    // 4️⃣ Maximum age
    public static void findMaxAge(){
        int maxAge = employees.stream()
                .mapToInt(Employee::getAge)
                .max()
                .orElse(0);

        System.out.println("Max Age: " + maxAge);

        // Output: Max Age: 35
    }

    // 5️⃣ Unique departments
    public static void uniqueDepartments(){
        List<String> dept =
                employees.stream()
                        .map(Employee::getDepartment)
                        .distinct()
                        .collect(Collectors.toList());

        System.out.println("Departments: " + dept);

        // Output: Departments: [HR, IT, Finance, Marketing, Operations, Sales]
    }

    // 6️⃣ Count employees in each department
    public static void countEmployeesEachDepartment(){
        Map<String,Long> map =
                employees.stream()
                        .collect(Collectors.groupingBy(
                                Employee::getDepartment,
                                Collectors.counting()));

        System.out.println("Employees per Department: " + map);

        // Output: Employees per Department: {HR=2, IT=2, Finance=2, Marketing=2, Operations=1, Sales=1}
    }

    // 7️⃣ Employees below age 30
    public static void employeesBelow30(){
        List<Employee> list =
                employees.stream()
                        .filter(e -> e.getAge() < 30)
                        .collect(Collectors.toList());

        System.out.println("Employees age < 30: " + list);

        // Output: Employees age < 30: [101 Alice Johnson HR 55000.0 Delhi age:28 rank:80, 103 Charlie Brown Finance 68000.0 Delhi age:29 rank:55, 104 Diana Prince Marketing 60000.0 Delhi age:26 rank:90, 106 Fiona Gallagher Sales 58000.0 Delhi age:27 rank:70, 108 Hannah Davis Finance 64000.0 Delhi age:24 rank:75, 110 Julia Roberts Marketing 70000.0 Bangalore age:29 rank:40]
    }

    // 8️⃣ Rank between 50 and 100
    public static void rankBetween50And100(){
        List<Employee> list =
                employees.stream()
                        .filter(e -> e.getRank() > 50 && e.getRank() < 100)
                        .collect(Collectors.toList());

        System.out.println("Rank between 50 and 100: " + list);
        // Output: Rank between 50 and 100: [101 Alice Johnson HR 55000.0 Delhi age:28 rank:80, 102 Bob Smith IT 72000.0 Mumbai age:32 rank:60, 103 Charlie Brown Finance 68000.0 Delhi age:29 rank:55, 104 Diana Prince Marketing 60000.0 Delhi age:26 rank:90, 108 Hannah Davis Finance 64000.0 Delhi age:24 rank:75, 109 Ian Wright HR 53000.0 Delhi age:33 rank:50, 106 Fiona Gallagher Sales 58000.0 Delhi age:27 rank:70]
    }

    // 9️⃣ Average age by gender
    public static void averageAgeByGender(){
        Map<String,Double> map =
                employees.stream()
                        .collect(Collectors.groupingBy(
                                Employee::getGender,
                                Collectors.averagingInt(Employee::getAge)));

        System.out.println("Average age by gender: " + map);
        // Output: Average age by gender: {Male=30.0, Female=28.0}
    }

    // 🔟 Department with max employees
    public static void departmentWithMaxEmployees(){

        Map.Entry<String,Long> entry =
                employees.stream()
                        .collect(Collectors.groupingBy(
                                Employee::getDepartment,
                                Collectors.counting()))
                        .entrySet()
                        .stream()
                        .max(Map.Entry.comparingByValue())
                        .get();

        System.out.println("Department with max employees: " + entry);
        // Output: Department with max employees: HR=2
    }

    // 1️⃣1️⃣ Employees in Delhi sorted by name
    public static void employeesInDelhiSorted(){
        List<Employee> list =
                employees.stream()
                        .filter(e -> e.getCity().equals("Delhi"))
                        .sorted(Comparator.comparing(Employee::getName))
                        .collect(Collectors.toList());

        System.out.println("Employees in Delhi sorted: " + list);
        // Output: Employees in Delhi sorted: [101 Alice Johnson HR 55000.0 Delhi age:28 rank:80, 103 Charlie Brown Finance 68000.0 Delhi age:29 rank:55, 104 Diana Prince Marketing 60000.0 Delhi age:26 rank:90, 108 Hannah Davis Finance 64000.0 Delhi age:24 rank:75, 109 Ian Wright HR 53000.0 Delhi age:33 rank:50, 106 Fiona Gallagher Sales 58000.0 Delhi age:27 rank:70]
    }

    // 1️⃣2️⃣ Average rank per department
    public static void averageRankEachDepartment(){
        Map<String,Double> map =
                employees.stream()
                        .collect(Collectors.groupingBy(
                                Employee::getDepartment,
                                Collectors.averagingInt(Employee::getRank)));

        System.out.println("Average rank by department: " + map);

        // Output: Average rank by department: {HR=65.0, IT=45.0, Finance=65.0, Marketing=65.0, Operations=45.0, Sales=70.0}
    }

    // 1️⃣3️⃣ Highest rank (lowest number) in each department
    public static void highestRankEachDepartment(){

        Map<String,Optional<Employee>> map =
                employees.stream()
                        .collect(Collectors.groupingBy(
                                Employee::getDepartment,
                                Collectors.minBy(Comparator.comparing(Employee::getRank))));

        System.out.println("Highest rank per department: " + map);


        // Output: Highest rank per department: {HR=Optional[101 Alice Johnson HR 55000.0 Delhi age:28 rank:80], IT=Optional[102 Bob Smith IT 72000.0 Mumbai age:32 rank:60], Finance=Optional[108 Hannah Davis Finance 64000.0 Delhi age:24 rank:75], Marketing=Optional[104 Diana Prince Marketing 60000.0 Delhi age:26 rank:90], Operations=Optional[105 Ethan Hunt Operations 75000.0 Chennai age:35 rank:45], Sales=Optional[106 Fiona Gallagher Sales 58000.0 Delhi age:27 rank:70]}
    }

    // 1️⃣4️⃣ Sort employees by rank
    public static void sortEmployeesByRank(){
        List<Employee> list =
                employees.stream()
                        .sorted(Comparator.comparing(Employee::getRank))
                        .collect(Collectors.toList());

        System.out.println("Sorted by Rank: " + list);

        // Output: Sorted by Rank: [104 Diana Prince Marketing 60000.0 Delhi age:26 rank:90, 101 Alice Johnson HR 55000.0 Delhi age:28 rank:80, 108 Hannah Davis Finance 64000.0 Delhi age:24 rank:75, 106 Fiona Gallagher Sales 58000.0 Delhi age:27 rank:70, 102 Bob Smith IT 72000.0 Mumbai age:32 rank:60, 103 Charlie Brown Finance 68000.0 Delhi age:29 rank:55, 109 Ian Wright HR 53000.0 Delhi age:33 rank:50, 110 Julia Roberts Marketing 70000.0 Bangalore age:29 rank:40, 105 Ethan Hunt Operations 75000.0 Chennai age:35 rank:45, 107 George Miller IT 82000.0 Pune age:31 rank:30]
    }

    // 1️⃣5️⃣ Second highest rank employee
    public static void secondHighestRankEmployee(){
        Employee emp =
                employees.stream()
                        .sorted(Comparator.comparing(Employee::getRank))
                        .skip(1)
                        .findFirst()
                        .orElse(null);

        System.out.println("Second highest rank employee: " + emp);

        // Output: Second highest rank employee: 102 Bob Smith IT 72000.0 Mumbai age:32 rank:60
    }

    // 1️⃣5️⃣ Most Used Word
    public static void mostUsedWord() {
        String str = "Ram is employee of ABC company, ram is from Blore, RAM! is good in algorithms.";

        Map<String, Long> wordCount =
                Arrays.stream(str.toLowerCase()
                .replaceAll("[^a-z ]", "")
                .split("\\s+"))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        String mostUsed =
                wordCount.entrySet()
                        .stream()
                        .max(Map.Entry.comparingByValue())
                        .get()
                        .getKey();

        System.out.println("Most Used Word: " + mostUsed);

        // Output: Most Used Word: ram
    }

    // ==========================================================
    // 1️⃣5️⃣ Second Highest Number
    public static void SecondHighestNumber() {
        int[] arr = {10,5,20,20,15,5,30};

        int highest = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;

        for(int num : arr){

            if(num > highest){
                second = highest;
                highest = num;
            }
            else if(num > second && num < highest){
                second = num;
            }
        }

        System.out.println("Second Highest: " + second);

        // Output: Second Highest: 20
    }

    // ==========================================================
    // 1️⃣5️⃣ Even Numbers using Stream
    public static void findEvenNumbers(){
        int[] arr = {10,5,20,15,30};

        List<Integer> even =
                Arrays.stream(arr)
                        .filter(n -> n%2==0)
                        .boxed()
                        .collect(Collectors.toList());

        System.out.println("Even Numbers: " + even);

        // Output: Even Numbers: [10, 20, 30]
    }

    // ==========================================================
    // 1️⃣5️⃣ Top 3 Salary + Group by Department
    public static void sortEmployeeBySalaryandDepartment(){
        System.out.println("\nTop 3 Highest Paid Employees");

        employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .limit(3)
                .forEach(System.out::println);

        System.out.println("\nGrouped By Department");

        Map<String,List<Employee>> grouped =
                employees.stream()
                        .collect(Collectors.groupingBy(Employee::getDepartment));

        grouped.forEach((d,e) -> {
            System.out.println("\nDepartment: " + d);
            e.forEach(System.out::println);
        });

        // Output:
        // Top 3 Highest Paid Employees 
        // 102 Bob Smith IT 72000.0 Mumbai age:32 rank:60
        // 110 Julia Roberts Marketing 70000.0 Bangalore age:29 rank:40
    }

    // 1️⃣ Find longest string
    public static void findLongestString() {

        List<String> strings =
                Arrays.asList("apple","banana","cherry","date","grapefruit");

        Optional<String> longest =
                strings.stream()
                        .max(Comparator.comparingInt(String::length));

        System.out.println("Longest String: " + longest.orElse("None"));

        // Output: Longest String: grapefruit
    }

    // 2️⃣ Average age
    public static void averageAge(){

        List<Person> persons = Arrays.asList(
                new Person("Alice",25),
                new Person("Bob",30),
                new Person("Charlie",35)
        );

        double avg =
                persons.stream()
                        .mapToInt(Person::getAge)
                        .average()
                        .orElse(0);

        System.out.println("Average Age: " + avg);

        // Output: Average Age: 30.0
    }

    // 3️⃣ Check if list contains prime number
    public static void checkPrimeNumber(){

        List<Integer> numbers =
                Arrays.asList(2,4,6,8,10,11,12,13,14,15);

        boolean containsPrime =
                numbers.stream()
                        .anyMatch(InterviewAttended01::isPrime);

        System.out.println("Contains Prime: " + containsPrime);

        // Output: Contains Prime: true (because of 2, 11, 13)
    }

    public static boolean isPrime(int number){

        if(number <= 1)
            return false;

        for(int i=2;i<=Math.sqrt(number);i++){
            if(number % i == 0)
                return false;
        }

        return true;

        // Output: Contains Prime: true (because of 2, 11, 13)
    }

    // 4️⃣ Merge two sorted lists
    public static void mergeSortedLists(){

        List<Integer> list1 = Arrays.asList(1,3,5,7,9);
        List<Integer> list2 = Arrays.asList(2,4,6,8,10);

        List<Integer> merged =
                Stream.concat(list1.stream(),list2.stream())
                        .sorted()
                        .collect(Collectors.toList());

        System.out.println("Merged List: " + merged);
        // Output: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
    }

    // 5️⃣ Intersection of two lists
    public static void findIntersection(){

        List<Integer> list1 = Arrays.asList(1,2,3,4,5);
        List<Integer> list2 = Arrays.asList(3,4,5,6,7);

        List<Integer> intersection =
                list1.stream()
                        .filter(list2::contains)
                        .collect(Collectors.toList());

        System.out.println("Intersection: " + intersection);
        // Output: [3, 4, 5]
    }

    // 6️⃣ Remove duplicates while preserving order
    public static void removeDuplicates(){

        List<Integer> numbers =
                Arrays.asList(1,2,3,2,4,1,5,6,5);

        List<Integer> unique =
                numbers.stream()
                        .distinct()
                        .collect(Collectors.toList());

        System.out.println("Unique Numbers: " + unique);
        // Output: [1, 2, 3, 4, 5, 6]
    }

    // 7️⃣ Sum transactions by day
    public static void sumTransactionsByDay(){

        List<Transaction> transactions = Arrays.asList(
                new Transaction("2022-01-01",100),
                new Transaction("2022-01-01",200),
                new Transaction("2022-01-02",300),
                new Transaction("2022-01-02",400),
                new Transaction("2022-01-03",500)
        );

        Map<String,Integer> sumByDay =
                transactions.stream()
                        .collect(Collectors.groupingBy(
                                Transaction::getDate,
                                Collectors.summingInt(Transaction::getAmount)
                        ));

        System.out.println("Sum By Day: " + sumByDay);
        // Output: {2022-01-01=300, 2022-01-02=700, 2022-01-03=500}
    }

    // 8️⃣ Kth smallest element
    public static void kthSmallestElement(){

        int[] array = {4,2,7,1,5,3,6};
        int k = 3;

        int kth =
                Arrays.stream(array)
                        .sorted()
                        .skip(k-1)
                        .findFirst()
                        .orElse(-1);

        System.out.println("Kth Smallest: " + kth);
        // Output: 3
    }

    // 9️⃣ Word frequency
    public static void wordFrequency(){

        List<String> words =
                Arrays.asList("apple","banana","apple","cherry","banana","apple");

        Map<String,Long> frequency =
                words.stream()
                        .collect(Collectors.groupingBy(
                                Function.identity(),
                                Collectors.counting()
                        ));

        System.out.println("Word Frequency: " + frequency);
        // output: {banana=2, cherry=1, apple=3}
    }

    // 🔟 Partition even and odd numbers
    public static void partitionEvenOdd(){

        List<Integer> numbers =
                Arrays.asList(1,2,3,4,5,6,7,8,9);

        Map<Boolean,List<Integer>> result =
                numbers.stream()
                        .collect(Collectors.partitioningBy(n -> n % 2 == 0));

        System.out.println("Even Numbers: " + result.get(true));
        System.out.println("Odd Numbers: " + result.get(false));
        //Output:
        //Even Numbers: [2, 4, 6, 8]
    }

    // ==========================================================
    // ==========================================================
    // 1️⃣5️⃣ First Non-Repeated Character
    public static void firstNonRepeatedCharacter(){

        String str = "character";

        Character result =
                str.chars()
                        .mapToObj(c -> (char)c)
                        .collect(Collectors.groupingBy(
                                Function.identity(),
                                LinkedHashMap::new,
                                Collectors.counting()))
                        .entrySet()
                        .stream()
                        .filter(e -> e.getValue()==1)
                        .map(Map.Entry::getKey)
                        .findFirst()
                        .orElse(null);

        System.out.println("First Non-Repeated: " + result);
    }





    // ==========================================================
    // 1️⃣5️⃣ Rotate Array
    public static void rotate(int[] nums,int k){

        int n = nums.length;
        k = k % n;

        reverse(nums,0,n-1);
        reverse(nums,0,k-1);
        reverse(nums,k,n-1);
    }

    private static void reverse(int[] nums,int start,int end){

        while(start < end){

            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;

            start++;
            end--;
        }
    }

    // ==========================================================
    // 1️⃣5️⃣ Search in Rotated Sorted Array
    public static int search(int[] nums,int target){

        int left = 0;
        int right = nums.length-1;

        while(left <= right){

            int mid = left + (right-left)/2;

            if(nums[mid] == target)
                return mid;

            if(nums[left] <= nums[mid]){

                if(target >= nums[left] && target < nums[mid])
                    right = mid-1;
                else
                    left = mid+1;

            } else {

                if(target > nums[mid] && target <= nums[right])
                    left = mid+1;
                else
                    right = mid-1;
            }
        }

        return -1;
    }

    // ==========================================================
    // 1️⃣5️⃣ Product Except Self
    public static int[] productExceptSelf(int[] nums){

        int n = nums.length;
        int[] answer = new int[n];

        answer[0] = 1;

        for(int i=1;i<n;i++)
            answer[i] = answer[i-1] * nums[i-1];

        int right = 1;

        for(int i=n-1;i>=0;i--){

            answer[i] *= right;
            right *= nums[i];
        }

        return answer;
    }

    // ==========================================================
    // 1️⃣5️⃣ Remove Stars
    public static String removeStars(String s){

        StringBuilder sb = new StringBuilder();

        for(char ch : s.toCharArray()){

            if(ch == '*')
                sb.deleteCharAt(sb.length()-1);
            else
                sb.append(ch);
        }

        return sb.toString();
    }
}

// ==========================================================
class Employee {

    private int id;
    private String name;
    private String department;
    private double salary;
    private String gender;
    private String city;
    private int age;
    private int rank;

    public Employee(int id,String name,String department,double salary,
                    String gender,String city,int age,int rank){

        this.id=id;
        this.name=name;
        this.department=department;
        this.salary=salary;
        this.gender=gender;
        this.city=city;
        this.age=age;
        this.rank=rank;
    }

    public int getId(){ return id; }
    public String getName(){ return name; }
    public String getDepartment(){ return department; }
    public double getSalary(){ return salary; }
    public String getGender(){ return gender; }
    public String getCity(){ return city; }
    public int getAge(){ return age; }
    public int getRank(){ return rank; }

    public String toString(){
        return id+" "+name+" "+department+" "+salary+" "+city+" age:"+age+" rank:"+rank;
    }
}


class Person{

    String name;
    int age;

    public Person(String name,int age){
        this.name=name;
        this.age=age;
    }

    public int getAge(){
        return age;
    }
}

class Transaction{

    String date;
    int amount;

    public Transaction(String date,int amount){
        this.date=date;
        this.amount=amount;
    }

    public String getDate(){
        return date;
    }

    public int getAmount(){
        return amount;
    }
}
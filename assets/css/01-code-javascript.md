For JavaScript Theorytical questions: "https://github.com/sudheerj/javascript-interview-questions"
For Reactjs theory questions: https://github.com/sudheerj/reactjs-interview-questions/tree/master/.github/workflows

https://learnersbucket.com/javascript-sde-cheat-sheet/
https://javascript.info/
https://frontenddeveloperinterview.netlify.app/

------------------------------------------
### Code 0: How to remove duplicates from an array of objects using JavaScript ?

#### Way 1: Using one of the keys as an index
```javascript
  var books = [
      { title: "C++", author: "Bjarne" },
      { title: "Java", author: "James" },
      { title: "Python", author: "Guido" },
      { title: "Java", author: "James" },
  ];

   let arrLeft = [];
    let arrRight = [];
    let result = [];
    function getDuplicate(books) {
        for (let index = 0; index < books.length; index++) {
            if(!arrLeft.includes(books[index].title)) {
                arrLeft.push(books[index].title);                
            } else {
                arrRight.push(books[index].title);
                result.push(books[index]);
            }
        }

        console.log("result----", result);
    }
   
    getDuplicate(books);
```

#### Way 2: Using filter() and includes() Method

```javascript
const books = [
    { title: "C++", author: "Bjarne" },
    { title: "Java", author: "James" },
    { title: "Python", author: "Guido" },
    { title: "Java", author: "James" },
];
 
const ids = books.map(({ title }) => title);
const filtered = books.filter(({ title }, index) =>
    !ids.includes(title, index + 1));
 
console.log(filtered);
```
#### Way 3: Using filter() and findIndex() Method
```javascript
const books = [
    { title: "C++", author: "Bjarne" },
    { title: "Java", author: "James" },
    { title: "Python", author: "Guido" },
    { title: "Java", author: "James" },
];
 
const unique = books.filter((obj, index) => {
    return index === books.findIndex(o => obj.title === o.title);
});
 
console.log(unique);
```

------------------------------------------
### Code 3: Remove Duplicate characters from String
```javascript
#### Way 1: 
function removeDuplicateCharacters() {
    var string = 'priya riya supriya';
    let result = '';
    
    // Loop through each character in the string
    for (let i = 0; i < string.length; i++) {
        let char = string[i];

        if (!result.includes(char)) {
            result += char;
        }

    }
    
    return result;
    }
console.log(removeDuplicateCharacters()); // Output: priya su

#### Way 2: 
function removeDuplicateCharacters() {
  var string = 'priya riya supriya';
  let result = '';
  
  // Loop through each character in the string
  for (let i = 0; i < string.length; i++) {
    let char = string[i];
    
    // Check if the character is already in the result
    if (result.indexOf(char) === -1) {
      result += char;  // Add the character to the result if it's not a duplicate
    }
  }
  
  return result;
}

console.log(removeDuplicateCharacters()); // Output: priya su

#### Way 3: 
function removeDuplicateCharacters() {
  var string='priya riya supriya'
  let result= string.split('').filter((item, index, arr)=> {
               return arr.indexOf(item) == index;
               }).join('');
  return result;
}
console.log(removeDuplicateCharacters());
// output: priya su
```

------------------------------------------
### Code 4: Remove Duplicate characters from array of element using filter or for loop
```javascript

#### Way 1
let arrLeft = [];
    let arrRight = [];
    let result = [];
    function getDuplicate(arr) {
        for (let index = 0; index < arr.length; index++) {
            if(!arrLeft.includes(arr[index])) {
                arrLeft.push(arr[index]);                
            } else {
                arrRight.push(arr[index]);
                result.push(arr[index]);
            }
        }

        console.log("result----", result);
    }
   
getDuplicate([1,2,3,4,3,7,4,5]); // output  [3, 4]

#### Way 2
var myArray = ['a', 1, 'a', 2, '1'];
var unique = myArray.filter((value, index, arr) => arr.indexOf(value) === index);
// output: ['a', 1, 2, '1']
```
------------------------------------------
### Code 5: String reverse without reversing of individual words (Array of elements can be reverse with reverse() method but for string it is won't possible so required to split 

#### Way 1: Using split, reverse and join
```javascript
function removeDuplicates(){
   var string ="India is my country"
   let result = string.split('').reverse().join('').split(' ').reverse().join(' ')
   return result
}
console.log(removeDuplicates()) 
output = "aidnI si ym yrtnuoc"
```

#### Way 2: Using loop
```javascript
function removeDuplicates() {
   var string = "India is my country";
   
   // Initialize an empty string to store the result
   let reversedString = "";
   
   // Reverse the whole string manually
   for (let i = string.length - 1; i >= 0; i--) {
       reversedString += string[i];
   }
   
   // Now we reverse the words manually
   let words = "";
   let tempWord = "";
   for (let i = reversedString.length - 1; i >= 0; i--) {
       if (reversedString[i] === ' ') {
           // Add the word to the words string with a space
           words = tempWord + ' ' + words;
           tempWord = "";  // Reset the temporary word
       } else {
           tempWord = reversedString[i] + tempWord;
       }
   }
   // Add the last word
   words = tempWord + ' ' + words;
   
   // Return the final result
   return words.trim();
}

console.log(removeDuplicates());
```

### Code 6: String reverse with reversing of individual words
#### Way 1: Using split, reverse and join
```javascript
function withoutReverse(){
   var string ="India is my country"
   let result = string.split('').reverse().join('')
   return result
}
console.log(withoutReverse())
output = "yrtnuoc ym si aidnI"
```
#### Way 2: Using for loop
```javascript
function Reverse(){
   var string ="India is my country";
   var result="";
   for( var i=string.length-1; i>=0 ; i-- ) {
      result = result+string[i] }
   return result
}
console.log(Reverse())
output = "yrtnuoc ym si aidnI"
```

------------------------------------------
### Code 7: Swapping of 2 numbers with third variable
```javascript
let a=10;
let b=20;
let c;
   c=a;
   a=b;
   b=c;
console.log (a,b,c)
// output : 20, 10, 10
```
------------------------------------------
### Code 8: Swapping of 2 numbers without third variable
```javascript
let a=10; 
let b=20;
   a=a+b //30
   b=a-b //10
   a=a-b //20
console.log (a,b)
// output : 20, 10
```
------------------------------------------
### Code 9: To check the string or number is palindrome or not( ex: 121,madam,anna) using diving length by 2 and then comparing
#### Way 1
```javascript
  function checkPalindrome() {
      const string = "12321";
      let isPalindrome = true;  // Initial assumption is that the string is a palindrome
      let len = string.length;
      let j = len - 1;

          for (let i = 0; i < len / 2; i++) {  // Only need to loop halfway through the string
              if (string[i] !== string[j]) {
                  isPalindrome = false;  // If a mismatch is found, set to false
                  break;  // Exit the loop early as we already know it's not a palindrome
              }
              j--;  // Move the pointer towards the middle
          }
      return isPalindrome;
  }

  console.log(checkPalindrome());  // Returns true if palindrome, false otherwise
```
#### Way 2
```javascript
function checkPalindrome(){
   const string = "12321"
   let len = string.length;
   for (i=0; i<len/2;i++){
     if (string[i]!==string[len-1-i]){
         console.log("Not Palindrome")
     }
     else{
         console.log(" Palindrome")
    }
   }
}
checkPalindrome()
// output: "Palindrome"
```
------------------------------------------
### Code 10: To find Max and Min num from array using functions
#### Way 1 to get max num using function
```javascript
function maxValue(arr) {
  let max = arr[0];

  for (let val of arr) {
    if (val > max) {
      max = val;
    }
  }
  return max;
}
console.log(maxValue([3,6,7,8,0]));
// output: 8
```
#### Way 2 to get min num using function
```javascript
function minValue(arr) {
let min = arr[0];

for (let val of arr) {
    if (val < min) {
        min = val;
    }
}
    return min;
}
console.log(minValue([3,6,7,8,1])); // output: 1
```
#### Way 3 to get min num using loop
```javascript
function minValue(arr) {
    let min = Infinity;  // Initialize min to a very large number
    for (let index = 0; index < arr.length; index++) {
        if (arr[index] < min) {  // Compare the current value with the current min
            min = arr[index];  // Update min if a smaller value is found
        }
    }
    console.log("min----", min);  // Log the minimum value
}

minValue([4, 5, 3, 1, 9]); // output: 1
```
#### Way 4 using inbuilt function
```javascript
#### Way 3 max using max method
Math.max.apply(null,[4,6,1,9])  // output: 9

#### Way 3 max using min method
Math.min.apply(null,[4,6,1,9])  // output: 1
```
------------------------------------------
### Code 11: To find longest snd shortesh word from a string using custom code

#### Way1 to get longest number using loop
```javascript
function longest() {
  var str ="Priya is a good girl and having hardworking skills"
  var words = str.split(' ');
  var longest = ''; 
  for (var i = 0; i < words.length; i++) {
    if (words[i].length > longest.length) {
      longest = words[i]; 
    }
  }
  console.log(longest)
  return longest;
}
longest();
// Output: hardworking
```
#### Way1 to get shortest number using loop
```javascript
function shortest() {
  var str ="Priya is a good girl and having hardworking skills"
  var words = str.split(' ');
  var shortest = ''; 
  let shortestNum = Infinity;
  for (var i = 0; i < words.length; i++) {
    if (words[i].length < shortestNum) {
        shortestNum = words[i].length; 
        shortest = words[i]
    }
  }
  console.log("====", shortest)
  return shortest;
}
shortest();
```

------------------------------------------
### Code 12: To find longest common string from array of strings
```javascript
function longestCommonString(){
 let array=["go","google","gosh"]
  var arr = array.sort((a,b)=>a.length-b.length)
  let result =""
  for(let i=0; i<arr[0].length; i++){
    if(arr[0][i]===arr[arr.length-1][i]){
      result+=arr[0][i]
    }
  }
  console.log("result---", result);
  return result
}
console.log(longestCommonString())
// output : go
```
------------------------------------------
### Code 13: To find vowels and its count in a given string
#### Way 1 using while loop
```javascript
function vowelCounts(){
  vowels=["a","i","e","o","u"]
  var str ="priya"
  count=0;
  for(var letter of str.toLowerCase())
  {
    if(vowels.includes(letter))
    {
      count++;
      console.log(letter)
    }
  }
  console.log(count)
  return count
}
vowelCounts()
// output
// "i"
// "a"
// 2

```
#### Way 2 using loop
```javascript
function countVowels(str) {
  let vowels = 'aeiouAEIOU'; // String containing all vowels (both lowercase and uppercase)
  let vowelCount = 0; // Variable to keep track of the count of vowels
  let vowelList = []; // Array to store vowels found in the string

  // Loop through each character of the string
  for (let i = 0; i < str.length; i++) {
    let char = str[i];

    // Check if the current character is a vowel
    if (vowels.includes(char)) {
      vowelCount++; // Increment the vowel count
      vowelList.push(char); // Add the vowel to the list
    }
  }

  // Return the count and the list of vowels found
  return {
    count: vowelCount,
    vowels: vowelList
  };
}

// Example usage:
let inputString = "Hello World!";
let result = countVowels(inputString);

console.log("Vowels found:", result.vowels.join(", "));
console.log("Total vowels count:", result.count);

```
------------------------------------------
### Code 14:sort array data in javascript using loop
You can sort an array in JavaScript using a loop, though the built-in `Array.prototype.sort()` is usually more efficient. However, if you want to implement sorting manually using a loop, you can use sorting algorithms like **Bubble Sort**, **Selection Sort**, or **Insertion Sort**.

### 1\. **Sort data suign simple way**
```javascript
let arr = [3, 5, 2, 5, 6, 2, 9, 0];

// Bubble Sort implementation
for (let i = 0; i < arr.length; i++) {
    for (let j = 0; j < arr.length - 1 - i; j++) {
        if (arr[j] > arr[j + 1]) {
            // Swap the elements
            let temp = arr[j];
            arr[j] = arr[j + 1];
            arr[j + 1] = temp;
        }
    }
}

console.log(arr); // [0,2,2,3,5,5,6,9]
```

### 1\. **Bubble Sort (Simple and easy to understand)**

Bubble Sort repeatedly steps through the list, compares adjacent items, and swaps them if they are in the wrong order.

```javascript
javascriptCopy codefunction bubbleSort(arr) {
  const n = arr.length;
  for (let i = 0; i < n - 1; i++) {
    for (let j = 0; j < n - 1 - i; j++) {
      if (arr[j] > arr[j + 1]) {
        // Swap elements
        let temp = arr[j];
        arr[j] = arr[j + 1];
        arr[j + 1] = temp;
      }
    }
  }
  return arr;
}

const numbers = [5, 3, 8, 4, 2];
console.log(bubbleSort(numbers)); // [2, 3, 4, 5, 8]
```

### 2\. **Selection Sort**

Selection Sort works by repeatedly finding the minimum (or maximum) element from the unsorted part and putting it at the beginning (or end).

```javascript
javascriptCopy codefunction selectionSort(arr) {
  const n = arr.length;
  for (let i = 0; i < n - 1; i++) {
    let minIndex = i;
    for (let j = i + 1; j < n; j++) {
      if (arr[j] < arr[minIndex]) {
        minIndex = j;
      }
    }
    // Swap the found minimum element with the first element
    let temp = arr[i];
    arr[i] = arr[minIndex];
    arr[minIndex] = temp;
  }
  return arr;
}

const numbers = [5, 3, 8, 4, 2];
console.log(selectionSort(numbers)); // [2, 3, 4, 5, 8]
```

### 3\. **Insertion Sort**

Insertion Sort works by taking one element at a time and inserting it into the correct position in the already sorted part of the array.

```javascript
codefunction insertionSort(arr) {
  const n = arr.length;
  for (let i = 1; i < n; i++) {
    let key = arr[i];
    let j = i - 1;
    // Move elements of arr[0..i-1], that are greater than key, to one position ahead
    while (j >= 0 && arr[j] > key) {
      arr[j + 1] = arr[j];
      j = j - 1;
    }
    arr[j + 1] = key;
  }
  return arr;
}

const numbers = [5, 3, 8, 4, 2];
console.log(insertionSort(numbers)); // [2, 3, 4, 5, 8]
```

### 4\. **Example with `Array.sort()` (Built-in Method)**

While using loops is great for learning, the built-in `Array.prototype.sort()` method is much more efficient for real-world use cases.

```javascript
javascriptCopy codeconst numbers = [5, 3, 8, 4, 2];
const sortedArray = numbers.sort((a, b) => a - b); // Ascending order
console.log(sortedArray); // [2, 3, 4, 5, 8]
```
------------------------------------------
### Code 15: Fibonacci Series (0,1,1,2,3,5,8,13....) where keeping in array
```javascript
function listFibonacci(n) {
var arr = [0, 1]
  for (var  i = 1; i < n; i++) 
    arr.push(arr[i] + arr[i - 1])

  return arr
}
console.log(listFibonacci(4))
// output: [0, 1, 1, 2, 3]
```
-----------------------------------------------------------------------
```javascript
function listFibonacci(n) {
var arr = [0, 1]
  for (var  i = 0; i < n; i++) 
    arr.push(arr[i] + arr[i + 1])

  return arr
}
console.log(listFibonacci(4))
// output: [0, 1, 1, 2, 3, 5]
```
------------------------------------------
### Code 16: Find factorial of user input number
```javascript
const number = parseInt(prompt('Enter a positive integer: '));
if (number < 0) { console.log('Error! Factorial for negative number does not exist.')}
else if (number === 0) { console.log(`The factorial is 1.`)}
else {
    let fact = 1;
    for (i = 1; i <= number; i++) {
        fact *= i;
    }
    console.log(`The factorial is ${fact}.`);
}
```
------------------------------------------
### Code 17:Anagram
```javascript
function checkStringsAnagram() {
var a="Army";
var b="Mary"
   let str1 =  a.toLowerCase().split('').sort().join('');
   let str2 =  b.toLowerCase().split('').sort().join('');
   if(str1 === str2){
      console.log("True");
   } 
   else { 
      console.log("False");
   }
}
```
------------------------------------------
### Code 18:To find character occurance fro the string
```javascript
function characterOccurance(str,letter){
   let count =0;
  for(var i=0; i<str.length-1; i++){
    if(str.charAt(i)===letter)
      {
        count++
      }
  }
  console.log(count)
  return count
}
characterOccurance("priyapri", "p")
// output: 2
```
------------------------------------------
### Code 19: To find a first pair whose sum is zero
```javascript
function getSumPairZero(array)
{
  for(let number of array)
  {
     for(let i=1; i<array.length; i++)
     {
         if(number+array[i]===0)
         {
            return [number, array[i]]
         }
     }
  }
}
const result = getSumPairZero([-5,-4,-3,-2,-1,0,1,2,3,4,5])
console.log(result)
// output : -5, 5]
```
------------------------------------------------
```javascript
function getSumPairZero(array)
{
  for(let j=0; j<array.length;j++)
  {
     for(let i=1; i<array.length; i++)
     {
         if(array[j]+array[i]===0)
         {
            return [array[j], array[i]]
         }
     }
  }
}
const result = getSumPairZero([-5,-4,-3,-2,-1,0,1,2,3,4,5])
console.log(result)
// output : -5, 5]
```
------------------------------------------
### Code 20: To find a first pair whose sum is zero using indexing //Firstly do a sort here
```javascript
function getSumPairZero(array)
{
  let left = 0;
  let right = array.length-1;
  while(left<right)
  {
    sum = array[left]+array[right]
    if(sum===0){
       return [array[left],array[right]]
    }else if(sum>0){
      right--;
    }else{
      left++;
    }
  }
}
const result = getSumPairZero([-5,-4,-3,-2,-1,0,2,4,6,8])
console.log(result)
// output : [-4, 4]
```
------------------------------------------
### Code 21: To find the largest pair of the 2 elements using indexing with unsorted elements
```javascript
function largestPairSumofTwo(numbers){
    const num = numbers.sort((a, b) => b - a);
    console.log(num)
    return num[0] + num[1];
}
const result = largestPairSumofTwo([9,7,8,4,5,6,1,2,3])
console.log(result)
// output
// [9, 8, 7, 6, 5, 4, 3, 2, 1]
// 17
```
------------------------------------------
### Code 22: To find the largest pair of the 2 elements using indexing with sorted elements
```javascript
function largestPairSumofTwo(num){   
    return num[num.length-1] + num[num.length-2];
}
const result = largestPairSumofTwo([1,2,3,4,5,6,7,8,9])
console.log(result)
// output: 17
```
------------------------------------------
### Code 23: To find the index of an element from an array
```javascript
const letters = ['a', 'b', 'c']
const index = letters.indexOf('b')
console.log(index) 
// output: `1`
```
------------------------------------------
### Code 24: Fibonacci Series (0,1,1,2,3,5,8,13....)
```javascript
function fibonacciSeries(){
   const number = parseInt(prompt('Enter the number of terms: '));
   let n1 = 0, n2 = 1, nextTerm, arr=[]
   arr.push(n1)
   arr.push(n2)
   for (let i = 1; i <= number; i++) 
   {
     console.log(n1);
     nextTerm = n1 + n2;
     n1 = n2;
     n2 = nextTerm;
     arr.push(nextTerm)
   }
   return arr
}
console.log(fibonacciSeries())
// output: enter 3
// 0
// 1
// 1
// [0, 1, 1, 2, 3]
```
------------------------------------------
### Code 35: Uppercase of each first letter of a words 
```javascript
function upperCaseFirsstLetter(){
   var string ="India is my country";
   var words = string.toLowerCase().split(" ")
   for( var i=0; i<words.length; i++) {
      words[i]=words[i][0].toUpperCase() + words[i].slice(1) //slice is used here to give all the letters except first letter.
      }
   return words.join(" ")
}
console.log(upperCaseFirsstLetter());
// output: "India Is My Country"
```
------------------------------------------
### Code 53: Unique values only from 2 arrays
```javascript
function diffArrayElement(arr1, arr2) {
  var result =[]
  for(var i=0; i<arr1.length; i++){
    if(arr2.indexOf(arr1[i]) === -1){
      result.push(arr1[i])
    }
  }

  for(var j=0; j<arr2.length; j++){
    if(arr1.indexOf(arr2[j]) === -1){
      result.push(arr2[j])
    }
  }
  return result
}
console.log(diffArrayElement([1,2,3,4], [2,3,4,5])) // output:  [1,5]
```
------------------------------------------
### Code 56: Sum of all numbers from start to end given number
```javascript
function sumFromStartToEnd(arr){
  var start = Math.min(arr[0], arr[1])
  var end = Math.max(arr[0], arr[1])
  sum =0
  for(var i= start; i<=end; i++){
    sum+=i
  }
  return sum
}
console.log(sumFromStartToEnd([1,4]))
// output: 10
```


------------------------------------------
### Code 72: Star Pattern
```javascript
for(var i=1; i<=5;i++){ //use to create new row
  for(var j=i; j<=5; j++){ //use to add in existing row
    document.write("*")
  }
  document.write("<br/>")
}
*****
****
***
**
*
```
------------------------------------------
### Code 73: Star Pattern
```javascript
for(var i=1; i<=5;i++){ //use to create new row
  for(var j=1; j<=5; j++){ //use to add in existing row
    document.write("*")
  }
  document.write("<br/>")
}
*****
*****
*****
*****
*****
```
------------------------------------------
### Code 74: Star Pattern
```javascript
for(var i=1; i<=5;i++){ //use to create new row
  for(var j=i; j<=5; j++){ //use to add in existing row
    document.write(i)
  }
  document.write("<br/>")
}
11111
2222
333
44
5
```
------------------------------------------
### Code 75: Star Pattern
```javascript
for(var i=1; i<=5;i++){ //use to create new row
  for(var j=i; j<=5; j++){ //use to add in existing row
    document.write(j)
  }
  document.write("<br/>")
}
12345
2345
345
45
5
```
------------------------------------------
### Code 76: Star Pattern
```javascript
for(var i=1; i<=5;i++){ //use to create new row
  for(var j=1; j<=i; j++){ //use to add in existing row
    document.write("*")
  }
  document.write("<br/>")
}
*
**
***
****
*****
```

### Code 0: longest substring of non repeating characters javascript

```javascript

function lengthOfLongestSubstring(string) {
    var max = 0, current_string = "", i, char, pos;

    for (i = 0; i < string.length; i += 1) {
        char = string.charAt(i);
        pos = current_string.indexOf(char);
        if (pos !== -1) {
            // cut "dv" to "v" when you see another "d"
            current_string = current_string.substr(pos + 1);
        }
        current_string += char;
        max = Math.max(max, current_string.length);
    }
    return max;
}

lengthOfLongestSubstring("dvdf"); // 3
```

------------------------------------------
### Code 26: Finding a missing elements in an array and then add with existing elements. (-1 means if elements not found then it will return always -1 as per rule)
```javascript
function missingElement(){
  var a = [1,2,5]
  var missing = [];
  for (var i = 1; i <= 6; i++) 
   {
    if (a.indexOf(i) == -1) 
     {
       missing.push(i); 
     }
   }
 console.log(missing) //missing array
 console.log(a.concat(missing).sort()); //actual+missing elements
}
missingElement()
// output
// [3, 4, 6]
// [1, 2, 3, 4, 5, 6]
```
------------------------------------------
### Code 27: Find the missing no. in an array
```javascript
function missing(arr) {
    var x = 0;
    for (var i = 0; i < arr.length; i++) {
        x = x + 1;
        if (arr[i] != x) {
            return(x); //9
        }
    }
}
var data = missing([1, 2, 3, 4, 5, 6, 7, 8, 10])
console.log(data)
// output : 9
```
-------------------------------------------
```javascript
function missing(arr) {
    for (var i = 0, x=1; i < arr.length; x++,i++) {
        if (arr[i] != x) { //index value comparing with pointer
            return x; //9
        }
    }
}
console.log(missing([1, 2, 3, 4, 5, 6, 7, 8, 10]))
// output : 9
```
------------------------------------------
### Code 28: Sorting of an string/character
```javascript
function sorting(arr) {
 return  arr.sort()
}
console.log(sorting(["d","g","y","e","r","p"]))
// output : ["d", "e", "g", "p", "r", "y"]
```
------------------------------------------
### Code 29: Sorting of an number
```javascript
function sorting(arr) {
 return  arr.sort((a,b)=>{return a-b}) 
}
console.log(sorting([1,23,34,2,76,78])) //[1, 2, 23, 34, 76, 78]
// output: [1, 2, 23, 34, 76
```
------------------------------------------
### Code 30: To check if given number is prime or not
```javascript
function isPrime(num) {
  if(num < 2) return false;
  for (let k = 2; k < num; k++){
    if( num % k == 0){ return false}
  }
  return true;
}
console.log(isPrime(17)) 
//output : true
```
------------------------------------------
### Code 31: To print all the numbers from 2 to 100
```javascript
for (let i = 2; i <= 10; i++) {
    let flag = 0;
    for (let j = 2; j < i; j++) {
        if (i % j == 0) {
            flag = 1;
            break;
        }
    }
    if (i > 1 && flag == 0) {
        console.log(i);
    }
}
// output
// 2
// 3
// 5
// 7
```
--------------------------------------
```javascript
for (let i = 2; i <= 100; i++) 
{
    let flag = 0;
    for (let j = 2; j < i; j++) { //2<2 //2<3 //3<4
        if (i % j == 0) {
            flag = 1;
            break;
        }
    }
    if (i > 1 && flag == 0) 
    {
        document.write(i+ "</br>");
    }
}
```
------------------------------------------
### Code 32: To find unique values from 2 arrays and keep into one array.
```javascript
function uniqueElements(arr1,arr2){
   let arr =[...arr1,...arr2];
   let array =[...new Set(arr)]
   console.log(array)
}
uniqueElements([1,2,3,4,4],[2,3,4,5,6])
// output: [1, 2, 3, 4, 5, 6]
```
------------------------------------------
### Code 33: Find first duplicate element from an array
```javascript
function firstDuplicate() {
    let arr = [1,2,2,5,5];
    let data = {};
    for (var item of arr) {
        if (data[item]) {
            return item
        } else {
            data[item] = item
            console.log(data[item])
        }
    }
    return -1
}
console.log(firstDuplicate()) 
// output
// 1
// 2
// 2
```
------------------------------------------
### Code 34: Write a program that prints the numbers from 1 to 100. But for multiples of three, print "Fizz" instead of the number, and for the multiples of five, print "Buzz". 
```javascript
For numbers which are multiples of both three and five, print "FizzBuzz"
for (var i=1; i <= 20; i++)
{
    if (i % 15 == 0)
        console.log("FizzBuzz");
    else if (i % 3 == 0)
        console.log("Fizz");
    else if (i % 5 == 0)
        console.log("Buzz");
    else
        console.log(i);
}
// output
// 1
// 2
// "Fizz"
// 4
// "Buzz"
```
------------------------------------------
### Code 37: To check ending of the string with given character/s using inbuild function
```javascript
function confirmEnding(str,target){
   return str.endsWith(target) //true
}
console.log(confirmEnding("priya","a"));
// output: true
```
===============================================================================================================================================================================
### Code 38: To check ending of the string with given character/s using custom
```javascript
function confirmEnding(str,target){
   return str.substr(-target.length)===target
}
console.log(confirmEnding("priya","a"));
// output: true
```
===============================================================================================================================================================================
### Code 39: To find the largest elements fro the 2 dimensional array 
```javascript
function largestFromArray(arr){
   var max=[];
   for(var i=0; i<arr.length;i++){
     var tempMax =arr[i][0] //first elements of the 4 internal arrays i,e(1,5,45,89
     for(var j=0; j<arr[i].length; j++){
        var currElement = arr[i][j];
        if(currElement>=tempMax){
          tempMax = currElement
        }
     }
      max.push(tempMax)
   }
  console.log(max)
   return max;
}
largestFromArray([[1,2,3,4],[5,6,7,9],[45,76,2,1],[89,90,87,9]]);
// output: [4, 9, 76, 90]
```
------------------------------------------
### Code 40: To find the largest elements fro the 2 dimensional array in another way
```javascript
function largestFromArray(arr){
   var max=[0,0,0,0];
   for(var i=0; i<arr.length;i++){
      for(var j=0; j<arr[i].length; j++)
      {
          if(arr[i][j]>=max[i]){
          max[i] = arr[i][j]
        }
      }
   }
  console.log(max)
  return max;
}
largestFromArray([[1,2,3,4],[5,6,7,9],[45,76,2,1],[89,90,87,9]]);
//output: [4, 9, 76, 90]
```
------------------------------------------
### Code 41: Print string n times using inbuilt function
```javascript
function repeatStrinNumTimes(str, num){
if (num<1) return ""
return str.repeat(num)
}
console.log(repeatStrinNumTimes("priya",3))
//output: "priyapriyapriya"
```
------------------------------------------
### Code 42: Print string n times in custom way
```javascript
function repeatStrinNumTimes(str, num){
var final="";
if(num<0) return ""
for(var i=0; i<num;i++)
{
  final=final+str
}
return final
}
console.log(repeatStrinNumTimes("priya",3))
//output: priyapriyapriya
```
------------------------------------------
### Code 43:Print string n times in custom way
```javascript
function repeatStrinNumTimes(str, num){
  if(num<0) return ""
  if(num===1) return str
  return str+ repeatStrinNumTimes(str, num-1)
}
console.log(repeatStrinNumTimes("priya",3))
// priyapriyapriya
```
------------------------------------------
### Code 44: Truncate the string
```javascript
function truncateString(str, num){
  if(num<=3) return str.slice(0,num)
  return str.slice(0,num-3)+"..." //retuen only 4 digits thats why subtracted from 3
}
console.log(truncateString("priyabagde",2)) //pr
console.log(truncateString("priyabagde",4)) //p... //retuen only 4 digits
// output: 
// "pr"
// "p..."
```
------------------------------------------
### Code 45: Converting one dimensional array into n dimensional array using slice
```javascript
function chunkArrayInGroup(arr, size){
  var group=[]
  while(arr.length>0){
  group.push(arr.slice(0, size))
  arr = arr.slice(size)
  }
  return group
}
console.log (chunkArrayInGroup(['a','b','c','d'],2)) // output: [["a", "b"], ["c", "d"]]
```
------------------------------------------
### Code 46: Converting one dimensional array into n dimensional array using splice
```javascript
function chunkArrayInGroup(arr, size){
  var group=[]
  while(arr.length>0){
  group.push(arr.splice(0, size))
  }
  return group
}
console.log (chunkArrayInGroup(['a','b','c','d'],2)) //output: [["a", "b"], ["c", "d"]]
```
------------------------------------------
### Code 47: To find only truthy values
```javascript
function removeFalseValue(arr){
 var trueth = []
 for (var item of arr){
   if(item){
      trueth.push(item)
   }
 }
 return trueth
}
console.log(removeFalseValue(["priya", 0 ,"", false, null,undefined, "ate", Nan ,9 ])) //output: ["priya","ate",9]
```
------------------------------------------
### Code 49:  To find only truthy values using filter
```javascript
function removeFalseValue(arr){
  return arr.filter((item)=>{
                return item})
}
console.log(removeFalseValue(["priya", 0,"", false, null,undefined, "ate", 9 ]))
// output: ["priya", "ate", 9]
```
------------------------------------------
### Code 50: Checking all letters of second words should present in first word, in the same order using include function
```javascript
function characterPresent(arr){
  var first = arr[0].toLowerCase()
  var second = arr[1].toLowerCase()
  for (var letter of second){
    if(!first.includes(letter)){
      return false
    }
  }
  return true
}
console.log(characterPresent(["hello","hey"]))
// output: false
```
------------------------------------------
### Code 51: Checking all letters of second words should present in first word, in the same order using indexOf without indexing i.e for-of loop
```javascript
function characterPresent(arr){
  var first = arr[0].toLowerCase()
  var second = arr[1].toLowerCase()
  for (var letter of second){
    if(first.indexOf(letter)== -1){ //-1 means not found in array
      return false
    }
  }
  return true
}
console.log(characterPresent(["hello","he"]));
// output: true
```
---------------------------------------------------
```javascript
function characterPresent(arr){
  var first = arr[0].toLowerCase()
  var second = arr[1].toLowerCase()
  for (var i=0; i<second.length; i++){
    if(!first.includes(second[i])){ //-1 means not found in array
      return false
    }
  }
  return true
}
console.log(characterPresent(["hello","he"]))
// output: true
```
------------------------------------------
### Code 52: Checking all letters of second words should present in first word, in the same order using indexOf with indexing
```javascript
function characterPresent(arr){
  var first = arr[0].toLowerCase()
  var second = arr[1].toLowerCase()
  for (var i=0; i<second.length; i++){
    if(first.indexOf(second)== -1){ //-1 means not found in array
      return false
    }
  }
  return true
}
console.log(characterPresent(["hello","he"]));
// output: true
```
------------------------------------------
### Code 57: Remove or Delete elements from an array using various ways
```javascript
Way 1: Removing Elements from End of a JavaScript Array
       var ar = [1, 2, 3, 4, 5, 6]; 
       ar.length = 4; // set length to remove elements
       console.log( ar ); // [1, 2, 3, 4]
    
Way 2: Removing Elements from Beginning of a JavaScript Array
        var ar = ['zero', 'one', 'two', 'three'];
        ar.shift(); // returns "zero"
        console.log( ar ); // ["one", "two", "three"]
        
Way 3: Using Splice to Remove Array Elements in JavaScript
        var list = ["bar", "baz", "foo", "qux"];
        list.splice(0, 2); // Starting at index position 0, remove two elements ["bar", "baz"] and retains ["foo", "qux"].
        
Way 4: Removing Array Items By Value Using Splice
       var arr = [1, 2, 3, 4, 5, 6, 7, 8, 9, 0];
       for( var i = 0; i < arr.length; i++){ 
           if ( arr[i] === 5) { 
              arr.splice(i, 1); 
           }
       } // [1, 2, 3, 4, 6, 7, 8, 9, 0]
       
       OR
       
        var arr = [1, 2, 3, 4, 5, 5, 6, 7, 8, 5, 9, 0];
        for( var i = 0; i < arr.length; i++){                             
        if ( arr[i] === 5) { 
            arr.splice(i, 1); 
            i--; 
          }
        } // [1, 2, 3, 4, 6, 7, 8, 9, 0]
        
Way 5: Using the Array filter Method to Remove Items By Value
        var array = [1, 2, 3, 4, 5, 6, 7, 8, 9, 0];
        var filtered = array.filter(function(value, index, arr){ 
        return value > 5;
        }); //filtered => [6, 7, 8, 9]
   
Way 6: Making a Remove Method
       function arrayRemove(arr, value) { 
        return arr.filter(function(ele){ 
            return ele != value; 
        });
    }
    var result = arrayRemove(array, 6); // result = [1, 2, 3, 4, 5, 7, 8, 9, 0]
    
Way 7: Explicitly Remove Array Elements Using the Delete Operator
         var ar = [1, 2, 3, 4, 5, 6];
         delete ar[4]; // delete element with index 4
         console.log( ar ); // [1, 2, 3, 4, undefined, 6]
```
------------------------------------------
### Code 58 : Spiral Matrix Printing | Print the elements of a matrix in spiral form
```javascript
var input = [[1,  2,   3,  4],
             [5,  6,   7,  8],
             [9,  10, 11, 12],
             [13, 14, 15, 16]];
function run(input, result) {
    // add the first row to result
    result = result.concat(input.shift());
    console.log("res1", result) //[1, 2, 3, 4] //[1, 2, 3, 4, 8, 12, 16, 15, 14, 13, 9, 5, 6, 7]
    console.log("in1", input)   //[[5, 6, 7, 8], [9, 10, 11, 12], [13, 14, 15, 16]] // [[10, 11]]

    // add the last element of each remaining row
    input.forEach(function(rightEnd) {
        result.push(rightEnd.pop());
    });
    console.log("res2", result) //[1, 2, 3, 4, 8, 12, 16] //[1, 2, 3, 4, 8, 12, 16, 15, 14, 13, 9, 5, 6, 7, 11]
    console.log("in2", input)   //[[5, 6, 7], [9, 10, 11], [13, 14, 15]] // [[10]]

    // add the last row in reverse order
    result = result.concat(input.pop().reverse());
    console.log("res3", result) //[1, 2, 3, 4, 8, 12, 16, 15, 14, 13] //[1, 2, 3, 4, 8, 12, 16, 15, 14, 13, 9, 5, 6, 7, 11, 10]
    console.log("in3", input)   //[[5, 6, 7], [9, 10, 11]]

    // add the first element in each remaining row (going upwards)
    var tmp = [];
    input.forEach(function(leftEnd) {    
        tmp.push(leftEnd.shift());
    });
    console.log("res4", result) //[1, 2, 3, 4, 8, 12, 16, 15, 14, 13]
    console.log("in4", input)   //[[6, 7], [10, 11]]
    
    result = result.concat(tmp.reverse());
    console.log("temp", temp) //[9, 5]
    console.log("res5", result) //[1, 2, 3, 4, 8, 12, 16, 15, 14, 13, 9, 5]
    console.log("in5", input)   //[[6, 7], [10, 11]]
    
    //again start the function
    return run(input, result); 
}
console.log('result', run(input, [])); // [1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10]
```
------------------------------------------
### Code 59: Currying function i.e sum of multiple argument functions //inner function can access outer function variables but outer functions can't able to acceess inner function.
```javascript
function sum(a){
  return function sum(b){
    return function sum(c){
      return function sum(d){
         return a+b+c+d;
      }
    }
  }
}
console.log(sum(1)(2)(3)(4))

OR

const sum = (a) => (b) => (c) => (d) => a+b+c+d // using ES6
console.log(sum(1)(2)(3)(4))
```
------------------------------------------
### Code 60: Find SUM, PRODUCT AND AVERAGE of the numbers //accumulation means collection
```javascript
let arr=[1,2,3,4,5]
let sum = arr.reduce((accum, curr) =>{
    return accum + curr;
})
console.log(sum) //15

OR

let sum = arr.reduce((accum, curr) =>{
    return accum + curr;
},5) // can set initial value as 5 also
console.log(sum) //20


let product = arr.reduce((accum, curr) =>{
    return accum * curr;
})
console.log(product)//120

let average = arr.reduce((accum, curr, index, array) =>{
    let total = accum + curr;
    if(index === array.length-1){
       return total/array.length
    }
    return total
})
console.log(average)//3
```
------------------------------------------
### Code 61: Convert 2D/3D array into 1D using reduce function and inbuilt function i.e flat
```javascript
const arr = [
               ['a','b'],
               ['c','d'],
               ['e','f'],
            ]
const flatArr = arr.reduce((accum, curr)=>{
return accum.concat(curr)
})
console.log(flatArr) //["a", "b", "c", "d", "e", "f"]

OR

const arr = [
['a','b'],
['c','d'],
['e',['f','g']],
]
console.log(arr.flat(2)) //["a", "b", "c", "d", "e", "f"] //bydefault 1 hota h as a argument

OR

const arr = [
['a','b'],
['c','d'],
['e',['f',['g','h']]],
]
console.log(arr.flat(3)) //["a", "b", "c", "d", "e", "f", "g", "h"]
```
------------------------------------------
### Code 62: Reverse of a nuber using converting into string
```javascript
function reverseNumber(input){
return(
    parseFloat(input.toString().split('').reverse().join(''))*Math.sign(input)
)
}
console.log(reverseNumber(123)) //321
```
------------------------------------------
### Code 63: Reverse of a nuber
```javascript
function reverseNumber(input){
var result=0;
while(input!=0){ //123 //12 //1
    result = result *10; //0*10=0 //3*10=30 // 32*10 =320
    result = result + (input%10) //give reminder // 0+3=3 // 30+2=32 //320+1=321
    input = Math.floor(input/10) //12 //1
   // console.log("in", input)
  }
  
  return result
}
console.log(reverseNumber(123)) //321
```
------------------------------------------
### Code 64: Check Armstrong Number
```javascript
function CheckArmstrongNum(num){ //153
  var temp = num;
  var result =0;
  var a;
  while(temp>0){ //153 //15 //1
    a= temp%10; //3 //5 //1
    temp= parseInt(temp/10) //15 // 1
    result= result+a*a*a //0+3*3*3 // 27+ 5*5*5 // 27+ 5*5*5 +1*1*1
  }
  if(result==num){
    return true
  }
  return false
}
console.log(CheckArmstrongNum(153)) //3*3*3 + 5*5*5 + 1*1*1 
```
------------------------------------------
### Code 65: To find the closest number in an array
```javascript
const needle = 5;
const numbers = [1, 10, 7, 2, 4, 9];
numbers.sort((a, b) => {
    return Math.abs(needle - a) - Math.abs(needle - b);
})
console.log(numbers[0]);

```
------------------------------------------
### Code 66: To find the second largest number
```javascript
function secondLargestNum(arr){
  return arr.sort((a, b)=> b - a )[1]
}
console.log(secondLargestNum(['1', '2', '3', '4', '9']))
// output: "4"
```
------------------------------------------
### Code 67: To check whether particular word/number present in sentence or not using inbuilt function
```javascript
function wordInSentence(str){
  return str.includes("world"); //true
}
console.log(wordInSentence("Hello world, welcome to the universe."))
OR
var nums =[0,1,3,5,6,7,8,9,7]
console.log(nums.includes(9)) //true
OR
var item=3
console.log(nums.some(x => x === item)) //true
```
------------------------------------------
### Code 68: To check whether particular word/number present in sentence or not using custom function
```javascript
function checkValueExist(arr, item){
  var status = "Not Exist"
  for(var i=0; i<arr.length; i++){
    if(arr[i]===item){
      status = "Exist"
      break;
    }
  }
  return status
}
console.log(checkValueExist(['priya', 'riya', 'supriya'], 'priya'))
```
------------------------------------------
### Code 69: To check wheather property exist or not in object
```javascript
let student ={
  name : "priya",
  age: 20
}
console.log('name' in student)
OR
console.log(student.hasOwnProperty('name'))
```
------------------------------------------
### Code 70: To dlete the property of an object
```javascript
let student ={
  name : "priya",
  age: 20,
  city: "pune"
}
delete student.age;
console.log(student)
OR
delete student['name']
console.log(student)
```
------------------------------------------
### Code 71: To find the length of the array in custom way
```javascript
function findLength(arr){
  var len =0;
  while(arr[len]!==undefined){
    len++
  }
  return len;
}
console.log(findLength([50,60,70,80,90]))
OR
function findLength(arr){
  return arr.length;
}
console.log(findLength([50,60,70,80,90]))
```
------------------------------------------
### Code 77: To find the square root
```javascript
var num = [4, 9, 16, 25, 36]
var result = num.map(Math.sqrt)
console.log(result) //[2,3,4,5,6]
```
------------------------------------------
### Code 78: Make alternate character to upper case
```javascript
function alternateText(str){
  var char = str.toLowerCase().split('')
      for(var i=0; i <char.length; i=i+2){
         char[i]=char[i].toUpperCase()
      }
  return char.join('')
}
console.log(alternateText("Priya Bagde")) //"PrIyA BaGdE"
OR
let alt = "Priya Bagde"
alt = alt.split("")
  .map((letter,index)=>(index%2)==0 ? letter.toUpperCase(): letter.toLowerCase())
  .join("")
console.log(alt) //"PrIyA BaGdE"
```
------------------------------------------
### Code 79:  To find the negative values in an array or 2D Array
```javascript
function countNegative(arr){
  let count = 0;
  for(let i=0;i<arr.length; i++){
    for(let j=0; j<arr[i].length; j++){
      if(arr[i][j]<0){
        count++
      }
    }
  }
  return count;
}
console.log(countNegative([[1,-1],[-1,-1]]))
```
------------------------------------------
### Code 80: Find first repeating character with its index from an array
```javascript
function firstRepeatingIndex(arr){
  let count = {};
  for(let i=0;i<arr.length; i++){
      if(count[arr[i]])
      {
        console.log("character", arr[i])
        console.log("index", count[arr[i]])
        return count[arr[i]] //if exist
      }
      else
      {
        count[arr[i]]=i //if not exist keep at count
      }
      console.log("count", count) 
    }
  return count   
}
firstRepeatingIndex([1,0,2,3,4,4,5,7,7])
```
------------------------------------------
### Code 81: To find all the subsets of the set
```javascript
function generateSubsets (arr) { //[1,2]
  let subsets = [];
  for (const item of arr) 
  {
    const tempSubsets = [...subsets];
    console.log("tempSubsets",tempSubsets) //[]//[[1]]
    for (const currSubset of tempSubsets) 
    {
      subsets.push([...currSubset, item]);
      console.log("subsets",subsets) //not came//[[1], [1,2]]
    }
    subsets.push([item]);
    console.log("subsets1",subsets) //[[1]]//[[1], [1,2],[2]]
  }
  subsets.push([]);
  console.log("subsets2",subsets) //[[1], [1, 2], [2], []]
  return subsets;
}
generateSubsets([1, 2]);
OR
function generateSubsets (arr) {
  let subsets = [];
  for (const item of arr) //[1,2] 
  {
    const tempSubsets = [...subsets];//[]//[[1]]
    for (const currSubset of tempSubsets) 
    {
      subsets.push([...currSubset, item]);//not came//[[1], [1,2]]
    }
    subsets.push([item]); //[[1]]//[[1], [1,2],[2]]
  }
  subsets.push([]);//[[1], [1, 2], [2], []]
  return subsets;
}
generateSubsets([1, 2]);
OR
function findAllSubsetsoOfGivenSet(arr) 
{
   var result= arr.reduce((subsets, value) => subsets.concat(subsets.map(set => [value,...set])),
                          [[]]) //[[]] is used to pass initial value
  return result
}
console.log(findAllSubsetsoOfGivenSet([8,9]));
```
--------------------------------------------------------
```javascript
function findAllSubsets(arr){
  var result = []
  for(var item of arr){
    let tempSub = [...result]
    for(var curr of tempSub){
      result.push([...curr, item])
    }
    result.push([item])
  }
  result.push([])
  return result
}

console.log(findAllSubsets([1,2]))
```
------------------------------------------
### Code 82: To find the maximum repetation of the character in a string  
```javascript
 function maxRepeating(str)
    {
        let count = 0;
        let character = str[0];
        for (let i=0; i<str.length; i++)
        {
            let tempCount = 1;
            for (let j=i+1; j<str.length; j++)
            {
                if (str[i] == str[j]) //if a is equal to a
                tempCount++; //use to find out the counts of character i.e a
            }
            if (tempCount > count)
            {
                count = tempCount;
                character = str[i];
            }
        }
        console.log(count, character)
        return character;
    }
maxRepeating("aaaabbaaccccccccccccccccccde");
```
------------------------------------------
### Code 83: To find all the missing numbers from an array
```javascript
function MissingElements(arr)
{
    for(let i = 0; i < arr.length; i++)
    {
        if (arr[i] - i != arr[0]) //1-0==1 //2-1==1 //6-2!=1 //checking for consecutive numbers
        {
            while (arr[0] < arr[i] - i)//1<4 //2<4 //3<4 //finding missing numbers
            {
                console.log(i + arr[0]);//2+1 //3+1 //3+1
                arr[0]++; //2 //3 //4
            }
        }
    }
}
MissingElements([1,2,6]); //3,4,5
```
---------------------------------------------
```javascript
function MissingElements(arr)
{
    for(let i = 0; i < arr.length; i++)
    {
        if (arr[0] != arr[i] - i) 
        {
            while (arr[0] < arr[i] - i)
            {
                console.log(arr[0]+i);
                arr[0]++; 
            }
        }
    }
}
MissingElements([1,2,6])
```
------------------------------------------
### Code 84: Adding an elements to the array when elements are consecutive numbers
```javascript
const as = [1,2,3,4];
for (let index = 5; index <= 10; ++index) {
    as.push(index);
}
console.log(as); //[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
```
------------------------------------------
### Code 85: Create a new array by adding one to each elements of the existing array
```javascript
function plusOne(arr){
  var output=[]
    for (let i = 0; i < arr.length; ++i) {
        output.push(arr[i]+1);
    }
  return output
}
console.log(plusOne([1,2,3,4]));
```
------------------------------------------
### Code 86: To find kth smallest or largest element in an array
```javascript
function findKthSmallestOrLargest(arr, num) {
  arr.sort(function(a, b) { return a - b});
  console.log(arr)
  console.log("kth smallest", arr[num- 1]) //kth smallest
  console.log("kth largest", arr[arr.length-num]) //kth smallest

};
console.log(findKthSmallestOrLargest([2,1,4,3,6,5,7], 3)); //kth is 3rd //3,5
```
------------------------------------------====
### Code 87: sort by frequency of the letters
```javascript
function frequencySort(str) {
   let map = {}
   for (const letter of str) {
      map[letter] = (map[letter] || 0) + 1; //to count the occurance
     };
     console.log(map) //{a: 2,A: 2,b: 3,B: 3,c: 1,C: 1}
   let res = "";
   let sorted = Object.keys(map).sort((a, b) => map[b] - map[a])
   console.log("sorted", sorted)// ["b", "B", "a", "A", "c", "C"]
   for (let letter of sorted) {
      for (let count = 0; count < map[letter]; count++) {
         res += letter
         console.log(res)
      }
   }
   return res;
};
console.log(frequencySort("cCaaAAbbbBBB")); //"bbbBBBaaAAcC"
```
-------------------------------------------------------------------
```javascript
function frequencySort(str) {
 let map = {}, res = "", sortedArr;
 for (const letter of str)map[letter]=(map[letter] || 0) + 1; 
 sortedArr = Object.keys(map).sort((a, b) => map[b] - map[a]);
 for (let letter of sortedArr) {
      for (let count = 0; count < map[letter]; count++) {
         res += letter
      }
 }
 return res;
};
console.log(frequencySort("cCaaAAbbbBBB"));
```
------------------------------------------
### Code 88: To find the OCCURANCE of the character
```javascript
function frequencySort(str) {
   let map = {}
   for (var i=0; i<str.length; i++) 
   {
     map[str[i]] = map[str[i]] ? map[str[i]]+1 : 1;  //Adding an element to the object, if already present then incrementing by 1
   }
   console.log(map)////{"c":1, "C:1", "a":2, "A":2, "b":3, "B":3}
};
frequencySort("cCaaAAbbbBBB"); 
OR
function frequencySortArr(arr) {
   let map = {}
   arr.forEach((element)=>{map[element] = map[element]+1 || 1 }) // will get occurance of the number
      return [...arr].sort((a,b)=> map[b]-map[a])
};
console.log(frequencySortArr([2,5,67,89,2,3,4,4,4]));  //[4,4,4,2,2,5,67,89,3]
```
------------------------------------------
### Code 89: Permutation // Need to debug
```javascript
let perm= (str, result)=> {
  if(str.length==0){console.log("result", result)} //let //lte //elt //etl //tle //tel
  
  for(var i=0; i<str.length; i++){
    let rest= str.substring(0,i)+ str.substring(i+1) 
   // console.log("rest", rest) //et//t//"" //e//"" //lt//t//"" //l//"" //le//e//"" //l//""
    console.log("finalresult",result+str[i])  //l//le//let  //lt//lte  //e//el//elt  //et//etl  //t//tl//tle  //te//tel 
    perm(rest, result+str[i]) 
  }
}
perm('let',''); 
//"result" "let"
//"result" "lte"
//"result" "elt"
//"result" "etl"
//"result" "tle"
//"result" "tel"
```
------------------------------------------
### Code 90: To find the power of x
```javascript
   var r = 1, i = 1;
    var b = 2;e =3 ;
    while(i <= e) //1<3//2<3//3=3
    {
        r *= b; //1*2//2*2//4*2
        i++;
    }
console.log(r) //8
OR
let number = 2;
let exponent = 3;
console.log( number ** exponent);
console.log( Math.pow(number, exponent));
```
------------------------------------------
### Code 91: To find even and odd number by user input
```javascript
const number = prompt("Enter a number: ");
if(number % 2 == 0) {
    console.log("The number is even.");
}
else {
    console.log("The number is odd.");
}
```
------------------------------------------
### Code 92: Grouping of an Anagram
```javascript
let collectAnagrams = (words) => {
    let anagrams = {}
    let collectedAnagrams = []
    for (let word of words)
    {
        let sortedWord = word.split('').sort().join('') //arrange ervery word in alphabetical order
        anagrams[sortedWord] = anagrams[sortedWord]  || [] //console.log(".",anagrams) //creating keys 
        anagrams[sortedWord].push(word) // assigning exact values to keys //console.log("..", anagrams) 
    }
    console.log(anagrams)
    for (let item in anagrams)
    {
        collectedAnagrams.push(anagrams[item])  // add their values as subarrays of the collectedAnagrams array
    }
    return collectedAnagrams
}
console.log(collectAnagrams(['bag', 'gab', 'foo', 'abg', 'oof', 'ofo'])) //[["bag", "gab", "abg"], ["foo", "oof", "ofo"]]
```
------------------------------------------
### Code 93: Sort an array of an element by parity means even then odd elements
```javascript
function sortByParity(arr){
 let even =[]
 let odd =[]
 let result=[]
 for(let i=0; i<arr.length; i++){
   if(arr[i]%2 ===0 ) even.push(arr[i]) 
   else odd.push(arr[i])
 }
result = even.concat(odd)
return result
}
console.log(sortByParity([1,2,3,4,5,6,7,8,9]))
```
------------------------------------------
### Code 94: Move all the zeroes at the end of an elements
```javascript
var array = [1,0,2,0,0,9,0,6,7];
array.sort(function(a, b) {
        if(a==0 && b!=0)
            return 1;
        else if (a!=0 && b==0)
            return -1;
        else 
            return 0;
    });
console.log(array)
OR
var moveZeroes = function(arr) {
    for ( var i = 0; i < arr.length-1; i++)
    {
        if(arr[i] === 0) { //if place x here then move x last to the array
          var  temp = arr.splice(i, 1);
          console.log(temp[0])
            arr.push(temp[0]);
        }
    }
    return arr; 
};
console.log(moveZeroes([1,0,4,8,6,0,8,3,39,0])) //[1,4,8,6,8,3,39,0,0,0]
OR
var moveZeroes = function(arr) {
    for ( var i = 0; i < arr.length-1; i++)
    {
        if(arr[i] === 0) {
            arr.splice(i, 1);
            arr.push(0);
        }
    }
    return arr; 
};
console.log(moveZeroes([1,0,4,8,6,0,8,3,39,0]))
```
------------------------------------------
### Code 95: Print consecutive numbers
```javascript
function range(num) 
{
    var result =[]
    for(var i =0; i<num; i++)
    {
    result.push(i) 
    }
    return result
};
console.log(range(10)); //[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
```
------------------------------------------
### Code 96: 4 Ways to empty an array
```javascript
var arrayList =  ['a', 'b', 'c', 'd', 'e', 'f'];
console.log("1", arrayList = [])
console.log("2", arrayList.length = 0)
console.log("3", arrayList.splice(0, arrayList.length))
var result= function(){while(arrayList.length) {
  arrayList.pop();
}}
console.log("4", arrayList)
```
------------------------------------------
### Code 97: Create a function to calculate the sum of all the numbers in a jagged array
```javascript
function sumArray(ar)
{
    var sum = 0;
    for(var el of ar)
    {
        if (Array.isArray(el))
        {
            el = sumArray(el); //recursion
        }   
        sum += el;
    }   
    return sum;
}
console.log(sumArray([1, 2, [3, [4], [5, 6]], [7]])) //28
```
------------------------------------------
### Code 98: To check weather perfect number or not
```javascript
function is_perfect(number)
{
     var temp = 0;
     for(var i=1;i<=number/2;i++)
     {
         if(number%i === 0)
          {
            console.log(i) //1,2,4,7,14
            temp += i;
          }
     }
     if(temp === number && temp !== 0)
        {
       console.log("It is a perfect number.");
        } 
     else
        {
       console.log("It is not a perfect number.");
        }   
 } 
is_perfect(28); 
```
------------------------------------------
### Code 99: Number of days between 2 dates calculation
```javascript
date1 = "2020-01-01", date2 = "2020-01-30"
function daysBetweenDates (date1, date2) {
  const days = (new Date(date2) - new Date(date1)) / (1000 * 60 * 60 * 24)
  return days
}
console.log(daysBetweenDates(date1,date2))
```
------------------------------------------
### Code 100: To find todays date
```javascript
var today = new Date();
var date = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();
console.log(date)
```
------------------------------------------
### Code 100: String Compression (Microsoft, Amazon etc)
```javascript
function stringCompression (str) {
  if (str.length ==0) {
    console.log('Please enter valid string.');
    return;
  }
  var output = '';
  var count = 0; 
  for (var i = 0; i < str.length; i++) 
  {
    count++;
    if (str[i] != str[i+1]) //if a is not equal to b
    {
      output += str[i] + count; //a+4
      count = 0; //for b it will start from zero
    }
  }
  console.log(output);
}
stringCompression(''); //Please enter valid string.
stringCompression('aaaa'); //a4
stringCompression('aaaabbc'); //a4b2c1
stringCompression('aaaabbcaabb'); //a4b2c1a2b2
```
---------------------------------------------------------------
```javascript
function stringCompression (str) {
  var output = '';
  var count = 0; 
  for (var i = 0; i < str.length; i++){
    count++;
    if (str[i] != str[i+1]) {
      output += str[i] + count; 
      count = 0; 
    }
  }
  console.log(output);
}
stringCompression('aaaab');
```
------------------------------------------
### Code 102: Given the roots of two binary trees root and subRoot, return true if there is a subtree of root with the same structure and node values of subRoot and false otherwise
```javascript
A subtree of a binary tree tree is a tree that consists of a node in tree and all of this node's descendants. The tree tree could also be considered as a subtree of itself.
Given tree s:
     3
    / \
   4   5
  / \
 1   2
Given tree t:
   4 
  / \
 1   2
-----------------------
class Node {
	constructor(val) {
		this.data = val;
		this.left = null;
		this.right = null;
	}}
    var root1,root2;
		root1 = new Node(26); // TREE 1
		root1.right = new Node(3);
		root1.right.right = new Node(3);
		root1.left = new Node(10);
		root1.left.left = new Node(4);
		root1.left.left.right = new Node(30);
		root1.left.right = new Node(6);
		root2 = new Node(10); 		// TREE 2
		root2.right = new Node(6);
		root2.left = new Node(4);
		root2.left.right = new Node(30);
	function areIdentical(root1, root2) //to check for same
	{
		if (root1 == null && root2 == null)
			return true;
		if (root1 == null || root2 == null)
			return false;
		return (root1.data == root2.data && areIdentical(root1.left, root2.left) && areIdentical(root1.right, root2.right));
	}
	function isSubtree(T, S) //main function
	{
		if (S == null)
			return true;
		if (T == null)
			return false;
		if (areIdentical(T, S))
			return true;
		return isSubtree(T.left, S) || isSubtree(T.right, S);
	}
 console.log(isSubtree(root1, root2))
 ```
------------------------------------------
### Code 103: Find triplets whose sum is zero
```javascript
function findTriplets(arr, n) {
    arr.sort();
    for (var i = 0; i < arr.length; i++) {
        var j = i + 1,
            k = arr.length - 1;
        while (j < k) {
            if (arr[i] + arr[j] + arr[k] < n) {
                j++;
            } else if (arr[i] + arr[j] + arr[k] > n) {
                k--;
            } else {
                console.log(arr[i] + "," + arr[j] + "," + arr[k]);
                j++;
                k--;
            }
        }
    }
    return true;
}
var arr = [-1, -4, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9];
findTriplets(arr, 0);
------------------------------------------------------------------------
function findTriplets(arr, n) {
 arr.sort((a, b) => a - b);
 for (let i = 0; i < arr.length - 2; i++) {
  for (let j = i + 1; j < arr.length - 1; j++) {
   for (let k = j + 1; k < arr.length; k++) {
    if (arr[i] + arr[j] + arr[k] === 0) {
     console.log(`${arr[i]}, ${arr[j]}, ${arr[k]}`);
    }
   }
  }
 }
}
let arr = [1, 2, 5, 3, -2, 0, 1, -1, 5, 6, -2, -1];
findTriplets(arr, 0);
 ```
------------------------------------------
### Code 104: Convert Array into object:-
```javascript
const arr = ["John", "Peter", "Sally", "Jane"];
const updatedArr={...arr};
console.log(updatedArr)//{0: 'John', 1: 'Peter', 2: 'Sally', 3: 'Jane'}
 ```
------------------------------------------
### Code 105: 3 main use-cases of #map( ) function:-
```javascript
1 . Used for rendering a list of data to the Dom in React
2. Used to modify an element in array depending on some requirement, which means calling a function on each item in Array
3. Used to convert String to Array
 ```
------------------------------------------
### Code 106:  Find common elements:
```javascript
function commonElements(a, b){
 return a.filter((item) => b.includes(item))
}
A = [1.2,3,4,5];
B = [4, 5, 6, 7, 8];
console.log(commonElements(A, B)); //[4,5]
 ```
------------------------------------------


JAVASCRIPTS Inbuilt Functions:

### Code 107: JAVASCRIPT substr concept
```javascript
var sentence ="I'm priya and having sounds kowledge."
console.log(sentence.substr(0,5)) //(startigIndex, NoOfCharatersWants-->take 1 less)//I'm p
console.log(sentence.substr(2,5)) //m pri
console.log(sentence.substr(2))   //m priya and having sounds kowledge.
console.log(sentence.substr(4))   //priya and having sounds kowledge.
console.log(sentence.substr(-4))  //Negative goes From ending of the string //dge.
console.log(sentence.substr(-5)) //edge.
```
### Code 108: JAVASCRIPT slice concept i.e, it doesn't change the original array
```javascript
var sentence ="I'm priya and having sounds kowledge."
console.log(sentence.slice(0,5)) //"I'm p"
console.log(sentence.slice(2,5)) //"m p"
console.log(sentence.slice(2))   //"m priya and having sounds kowledge."
console.log(sentence.slice(4))   //"priya and having sounds kowledge."
console.log(sentence.slice(-4))  //"dge."
console.log(sentence.slice(-5))  //"edge."

var sentence =['a','b','c','d']
console.log(sentence.slice(0,2)) //['a','b']
console.log(sentence) //['a','b','c','d']
```
------------------------------------------
### Code 109: JAVASCRIPT splice concept i.e, it changes the original array
```javascript
var sentence =['a','b','c','d']
console.log(sentence.splice(0,2)) //['a','b']
console.log(sentence) //['c','d']
```
------------------------------------------
### Code 110: JAVASCRIPT indexOf concept
```javascript
 var greeting = "Hello"
console.log(greeting.indexOf("e")) //1
```
------------------------------------------
### Code 111: JAVASCRIPT split concept
```javascript
var name ="Priya Bagde"
console.log(name.split("")) //["P","r","i","y","a"," ","B","a","g","d","e"]
console.log(name.split(" ")) //["Priya","Bagde"]
------------------------------------------
### Code 112: JAVASCRIPT join concept
```javascript
var arr = ['a','b','c','d','e']
console.log(arr.join()) //"a,b,c,d,e"
```
------------------------------------------
### Code 113: JAVASCRIPT join concept
```javascript
var arr = ['a','b','c','d','e']
for(var i=0; i<arr.length; i++){
  console.log(arr[i])
}
"a"
"b"
"c"
"d"
"e"
```





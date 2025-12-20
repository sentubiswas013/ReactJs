Here are **short, natural, real-time spoken-style answers**, each with **simple examples** and **practical focus** 👇

---
## **Basic JavaScript Concepts**
### **1. Different data types in JavaScript**

* JavaScript has **primitive** and **non-primitive** data types
* **Primitive:** `string`, `number`, `boolean`, `null`, `undefined`, `symbol`, `bigint`
* **Non-primitive:** `object` (arrays, functions, objects)
* Primitives store **single values**, objects store **collections**

```js
let name = "Alex";        // string
let age = 25;            // number
let isActive = true;     // boolean
let user = { id: 1 };    // object
```

---

### **2. Difference between `var`, `let`, and `const`**

* `var` is **function-scoped** and can cause bugs
* `let` is **block-scoped** and can be reassigned
* `const` is **block-scoped** and cannot be reassigned
* Use `let` and `const` in modern JavaScript

```js
let count = 1;
count = 2;

const PI = 3.14;
// PI = 3; ❌ error
```

---

### **3. What is a closure?**

* A closure is when a function **remembers variables** from its outer scope
* Even after the outer function finishes
* Very useful for **data privacy** and **callbacks**

```js
function outer() {
  let count = 0;
  return function inner() {
    count++;
    console.log(count);
  };
}

const counter = outer();
counter(); // 1
counter(); // 2
```

---

### **4. Difference between `==` and `===`**

* `==` checks **value only** (type conversion happens)
* `===` checks **value and type**
* Always prefer `===` to avoid bugs

```js
5 == "5";   // true
5 === "5";  // false
```

---

### **5. Truthy and Falsy values**

* **Falsy values:** `false`, `0`, `""`, `null`, `undefined`, `NaN`
* Everything else is **truthy**
* Used commonly in `if` conditions

```js
if ("hello") {
  console.log("Truthy");
}

if (0) {
  console.log("Won't run");
}
```

---

### **6. JavaScript data structures & when to use them**

* **Array:** ordered list of items
* **Object:** key-value pairs
* **Map:** ordered keys of any type
* **Set:** unique values only

```js
let arr = [1, 2, 3];
let obj = { name: "Sam" };
let set = new Set([1, 1, 2]);
let map = new Map([["id", 1]]);
```

---

### **7. What is `undefined`?**

* Means a variable is **declared but not assigned**
* It’s automatically assigned by JavaScript

```js
let x;
console.log(x); // undefined
```

---

### **8. What is `null` and how is it different from `undefined`?**

* `null` means **intentional empty value**
* `undefined` means **not assigned**
* `null` is set by the developer

```js
let data = null;     // intentional
let value;          // undefined
```

---

### **9. Different ways to create objects**

* **Object literal** (most common)
* **Constructor function**
* **Class syntax**
* **Object.create()**

```js
// Object literal
let user = { name: "Alex" };

// Constructor
function User(name) {
  this.name = name;
}
let u1 = new User("Sam");

// Class
class Person {
  constructor(name) {
    this.name = name;
  }
}
```

---

### **10. What is hoisting in JavaScript?**

* Hoisting means JavaScript **moves declarations to the top** of the scope
* `var` variables are hoisted and set to `undefined`
* `let` and `const` are hoisted but **not accessible before declaration**
* Function declarations are fully hoisted

```js
console.log(a); // undefined
var a = 10;

sayHi(); // works
function sayHi() {
  console.log("Hi");
}
```

---

### **11. What is a pure function?**

* A pure function always returns the **same output for the same input**
* It does **not change external data**
* Easy to test and debug

```js
function add(a, b) {
  return a + b;
}

add(2, 3); // always 5
```

---

### **12. What are higher-order functions?**

* Functions that **take other functions as arguments**
* Or **return functions**
* Very common with arrays like `map`, `filter`, `reduce`

```js
function greet(fn) {
  fn();
}

greet(() => console.log("Hello"));
```

---

### **13. What is a callback function?**

* A callback is a function passed into another function
* It runs **after a task is completed**
* Common in async operations like APIs or timers

```js
setTimeout(function () {
  console.log("Done");
}, 1000);
```

---

### **14. What are closures and why are they important?**

* A closure allows a function to **remember outer variables**
* Even after the outer function finishes
* Used for **data privacy** and state management

```js
function counter() {
  let count = 0;
  return () => ++count;
}

const inc = counter();
inc(); // 1
inc(); // 2
```

---

### **15. How does the `this` keyword work?**

* `this` refers to the **object calling the function**
* Its value depends on **how the function is called**
* Arrow functions **do not have their own `this`**

```js
const user = {
  name: "Alex",
  greet() {
    console.log(this.name);
  }
};

user.greet(); // Alex
```

---

### **16. Synchronous vs Asynchronous code**

* **Synchronous:** blocks execution, one task at a time
* **Asynchronous:** non-blocking, tasks run in background
* Used for APIs, timers, file operations

```js
console.log("Start");

setTimeout(() => {
  console.log("Async");
}, 1000);

console.log("End");
```

---

### **17. Purpose of `call()`, `apply()`, and `bind()`**

* Used to **control the value of `this`**
* `call()` → arguments one by one
* `apply()` → arguments as an array
* `bind()` → returns a new function

```js
function greet(city) {
  console.log(this.name, city);
}

greet.call({ name: "Sam" }, "NY");
```

---

### **18. What is `async/await`?**

* A cleaner way to handle **asynchronous code**
* Built on top of Promises
* Makes async code look synchronous

```js
async function fetchData() {
  const data = await Promise.resolve("Done");
  console.log(data);
}

fetchData();
```

---

### **19. What is a Promise in JavaScript? How does it work?**

* A Promise represents a value that will be available **in the future**
* It handles **async operations** like API calls
* You use `.then()` for success and `.catch()` for errors

```js
const promise = new Promise((resolve, reject) => {
  resolve("Success");
});

promise.then(data => console.log(data));
```

---

### **20. What is a Promise chain?**

* A promise chain is when multiple `.then()` calls are linked
* Each `.then()` receives the result of the previous one
* Helps avoid **callback hell**

```js
Promise.resolve(1)
  .then(num => num + 1)
  .then(num => console.log(num)); // 2
```

---

### **21. What are the states of a Promise?**

* **Pending:** initial state
* **Fulfilled:** operation completed successfully
* **Rejected:** operation failed

```js
new Promise((resolve, reject) => {
  resolve("Done");
});
```

---

### **22. Difference between `async/await` and Promises**

* Promises use `.then()` and `.catch()`
* `async/await` makes async code **look synchronous**
* Both are built on Promises

```js
// Promise
fetchData().then(data => console.log(data));

// async/await
const data = await fetchData();
```

---

### **23. Difference between callback and promise**

* **Callbacks** can lead to nested code (callback hell)
* **Promises** provide cleaner, readable flow
* Promises handle errors better

```js
// Callback
setTimeout(() => console.log("Done"), 1000);

// Promise
Promise.resolve("Done").then(console.log);
```

---

### **24. What is an Observable?**

* An Observable emits **multiple values over time**
* Commonly used in **RxJS** and Angular
* You can subscribe and unsubscribe

```js
observable.subscribe(value => console.log(value));
```

---

### **25. Differences between Promises and Observables**

* Promise → **single value**
* Observable → **multiple values**
* Observables can be **cancelled**
* Promises run immediately, Observables start on subscribe

```js
// Promise
Promise.resolve(1);

// Observable
// emits many values over time
```

---

### **26. What is currying in JavaScript?**

* Currying transforms a function with many arguments
* Into a sequence of **single-argument functions**
* Improves reusability

```js
function multiply(a) {
  return b => a * b;
}

multiply(2)(3); // 6
```

---

### **27. What is an anonymous function?**

* A function **without a name**
* Often used as callbacks
* Useful for short, one-time logic

```js
setTimeout(function () {
  console.log("Hello");
}, 1000);
```

---

### **28. Event delegation in JavaScript**

* Event delegation means attaching **one event listener to a parent**
* Instead of adding listeners to many child elements
* Uses **event bubbling** to catch events
* Improves performance and handles dynamic elements

```js
document.getElementById("list").addEventListener("click", e => {
  if (e.target.tagName === "LI") {
    console.log(e.target.textContent);
  }
});
```

---

### **29. What is the call stack?**

* The call stack keeps track of **function calls**
* Works in **Last In, First Out (LIFO)** order
* When a function finishes, it’s removed from the stack

```js
function a() {
  b();
}

function b() {
  console.log("Hello");
}

a();
```

---

### **30. What is the event loop?**

* The event loop manages **async code execution**
* It checks the call stack and task queues
* Moves callbacks to the stack when it’s empty

```js
console.log("Start");

setTimeout(() => console.log("Async"), 0);

console.log("End");
```

---

### **31. Event propagation in JavaScript**

* Event propagation is how events **travel through the DOM**
* It has **three phases**

  1. Capturing
  2. Target
  3. Bubbling

```js
element.addEventListener("click", handler);
```

---

### **32. Event bubbling and capturing**

* **Bubbling:** event moves from child to parent (default)
* **Capturing:** event moves from parent to child
* You can control this with the third parameter

```js
btn.addEventListener("click", handler, true); // capturing
btn.addEventListener("click", handler);      // bubbling
```

---

### **33. Prevent default action of an event**

* Use `event.preventDefault()`
* Stops the browser’s default behavior
* Common with forms and links

```js
form.addEventListener("submit", e => {
  e.preventDefault();
});
```

---

### **34. Attach multiple event listeners to the same event**

* You can add multiple listeners using `addEventListener`
* All listeners will execute in order added

```js
btn.addEventListener("click", () => console.log("One"));
btn.addEventListener("click", () => console.log("Two"));
```

---

### **35. Concept of memoization**

* Memoization stores **previous results**
* Avoids repeating expensive calculations
* Improves performance

```js
const memo = {};

function square(n) {
  if (memo[n]) return memo[n];
  return memo[n] = n * n;
}
```

---

### **36. Debounce and throttle**

* **Debounce:** runs function after a delay
* **Throttle:** runs function at fixed intervals
* Used in scroll, resize, search input

```js
// Debounce example
function debounce(fn, delay) {
  let timer;
  return function () {
    clearTimeout(timer);
    timer = setTimeout(fn, delay);
  };
}
```

---
## **Object-Oriented Programming in JavaScript**
### **37. OOP concepts in JavaScript**

* JavaScript supports OOP using **objects and prototypes**
* Main concepts are **encapsulation, inheritance, polymorphism, abstraction**
* Helps organize code and make it reusable

```js
const user = {
  name: "Alex",
  login() {
    console.log("Logged in");
  }
};
```

---

### **38. Classes and objects in JavaScript**

* A **class** is a blueprint
* An **object** is an instance of a class
* Classes were added in ES6 for cleaner syntax

```js
class User {
  constructor(name) {
    this.name = name;
  }
}

const u1 = new User("Sam");
```

---

### **39. Encapsulation in JavaScript**

* Encapsulation means **hiding internal details**
* Only expose what’s necessary
* Prevents accidental data changes

```js
class Bank {
  #balance = 0;

  deposit(amount) {
    this.#balance += amount;
  }
}
```

---

### **40. Inheritance in JavaScript**

* Inheritance allows one class to **reuse another class**
* Achieved using `extends`
* Avoids code duplication

```js
class Animal {
  speak() {
    console.log("Animal sound");
  }
}

class Dog extends Animal {
  speak() {
    console.log("Bark");
  }
}
```

---

### **41. Polymorphism in JavaScript**

* Polymorphism means **same method, different behavior**
* Child classes can override parent methods

```js
class Shape {
  draw() {
    console.log("Drawing shape");
  }
}

class Circle extends Shape {
  draw() {
    console.log("Drawing circle");
  }
}
```

---

### **42. Abstraction in JavaScript**

* Abstraction means **hiding complex logic**
* Show only what the user needs
* Achieved using methods and interfaces-like patterns

```js
class Car {
  start() {
    this.#igniteEngine();
  }

  #igniteEngine() {
    console.log("Engine started");
  }
}
```

---

### **43. Prototype-based inheritance**

* JavaScript uses **prototypes**, not classical inheritance
* Objects inherit properties from another object
* Prototype chain handles property lookup

```js
const parent = {
  greet() {
    console.log("Hello");
  }
};

const child = Object.create(parent);
child.greet();
```

---

### **44. Arrow functions vs regular functions**

* Arrow functions have **shorter syntax**
* They **do not have their own `this`**
* Not suitable as object methods or constructors

```js
const add = (a, b) => a + b;

function sum(a, b) {
  return a + b;
}
```

---

### **45. JavaScript built-in objects**

* Common built-ins include:

  * `Object`, `Array`, `String`
  * `Number`, `Math`, `Date`
  * `Promise`, `Map`, `Set`

```js
const arr = new Array(1, 2, 3);
const today = new Date();
```

---

### **46. What is a prototype in JavaScript?**

* Every JavaScript object has a **prototype**
* It’s a hidden object used for **property and method sharing**
* If a property isn’t found, JS looks up the **prototype chain**

```js
function Person(name) {
  this.name = name;
}

Person.prototype.sayHi = function () {
  console.log("Hi");
};
```

---

### **47. Purpose of `WeakMap` and `WeakSet`**

* They store objects **weakly**
* Objects can be garbage collected automatically
* Used for **private data** and **memory-efficient caching**

```js
const wm = new WeakMap();
let user = {};

wm.set(user, "private");
```

---

### **48. Memory management & garbage collection**

* JavaScript automatically manages memory
* Unused objects are removed by **garbage collector**
* Uses a **mark-and-sweep** algorithm

```js
let obj = { name: "Alex" };
obj = null; // eligible for garbage collection
```

---

### **49. Scope and closures**

* Scope defines **where variables are accessible**
* JavaScript uses **lexical scope**
* Closures allow inner functions to remember outer variables

```js
function outer() {
  let msg = "Hello";
  return function () {
    console.log(msg);
  };
}
```

---

### **50. Use of the `window` object**

* `window` is the **global object** in browsers
* Global variables become properties of `window`
* Provides browser APIs like `alert`, `setTimeout`

```js
window.alert("Hi");
console.log(window.innerWidth);
```

---

### **51. How the `new` keyword works**

* Creates a new empty object
* Sets prototype linkage
* Binds `this` to the new object
* Returns the object automatically

```js
function User(name) {
  this.name = name;
}

const u1 = new User("Sam");
```

---

### **52. What is an IIFE?**

* A function that runs **immediately**
* Used to avoid global scope pollution
* Common before ES6 modules

```js
(function () {
  console.log("Runs immediately");
})();
```

---

### **53. Function declaration vs function expression**

* **Declarations** are hoisted
* **Expressions** are not hoisted
* Expressions can be anonymous

```js
// Declaration
function sayHi() {}

// Expression
const greet = function () {};
```

---

### **54. `setTimeout()` vs `setInterval()`**

* `setTimeout()` runs once after a delay
* `setInterval()` runs repeatedly
* Used for timers and animations

```js
setTimeout(() => console.log("Once"), 1000);
setInterval(() => console.log("Repeat"), 2000);
```

---

### **55. How do you clone an object in JavaScript?**

* You can clone objects **shallowly** or **deeply**
* Common ways: spread operator, `Object.assign`
* For deep copy, use `JSON` or libraries

```js
const obj = { a: 1 };
const clone = { ...obj };
```

---

### **56. `JSON.stringify()` and `JSON.parse()`**

* `JSON.stringify()` converts an object to a string
* `JSON.parse()` converts the string back to an object
* Commonly used for API data and deep copying

```js
const str = JSON.stringify({ name: "Sam" });
const obj = JSON.parse(str);
```

---

### **57. How to create a class in JavaScript**

* Classes are blueprints for objects
* Introduced in ES6
* Use `constructor` for initialization

```js
class User {
  constructor(name) {
    this.name = name;
  }
}
```

---

### **58. Prototype inheritance**

* Objects inherit from other objects using prototypes
* JavaScript searches properties up the prototype chain
* Memory-efficient and powerful

```js
const parent = {
  greet() {
    console.log("Hello");
  }
};

const child = Object.create(parent);
child.greet();
```

---

### **59. Different methods of creating objects**

* Object literal
* Constructor function
* `Object.create()`
* ES6 classes

```js
const obj1 = {};
const obj2 = new Object();
```

---

### **60. `Object.create()` vs class inheritance**

* `Object.create()` links directly to a prototype
* Classes use constructor + prototype internally
* `Object.create()` gives more low-level control

```js
const child = Object.create(parent);
```

---

### **61. `class` vs `constructor`**

* `class` is a blueprint
* `constructor` is a method inside a class
* Constructor runs automatically when creating objects

```js
class User {
  constructor(name) {
    this.name = name;
  }
}
```

---

### **62. Getter and setter methods**

* Used to **control access** to object properties
* Improve encapsulation
* Look like properties but act like methods

```js
class Person {
  get name() {
    return this._name;
  }

  set name(value) {
    this._name = value;
  }
}
```

---
## **Arrays and Objects**
### **63. How can you merge two arrays?**

* Most common way is the **spread operator**
* Keeps original arrays unchanged
* Clean and readable

```js
const a = [1, 2];
const b = [3, 4];

const merged = [...a, ...b];
```

---

### **64. `slice()` vs `splice()`**

* `slice()` **does not change** the original array
* `splice()` **modifies** the original array
* `slice()` is for copying, `splice()` is for adding/removing

```js
arr.slice(1, 3);     // copy
arr.splice(1, 1);   // remove
```

---

### **65. Remove duplicates from an array**

* Use `Set` to keep **unique values**
* Simple and efficient

```js
const nums = [1, 2, 2, 3];
const unique = [...new Set(nums)];
```

---

### **66. Ways to loop through an array**

* `for` loop
* `forEach`
* `map`
* `for...of`

```js
arr.forEach(item => console.log(item));
```

---

### **67. Sort an array of objects**

* Use `sort()` with a compare function
* Compare based on property

```js
users.sort((a, b) => a.age - b.age);
```

---

### **68. `Object.assign()` vs spread operator**

* Both create **shallow copies**
* Spread is cleaner and more modern
* `Object.assign()` mutates the target object

```js
const copy = { ...obj };
```

---

### **69. Shallow copy vs deep copy**

* **Shallow copy** copies references
* **Deep copy** copies actual values
* Nested objects need deep copy

```js
const deep = JSON.parse(JSON.stringify(obj));
```

---

### **70. Check if an object is an array**

* Use `Array.isArray()`
* Reliable and recommended

```js
Array.isArray([1, 2]); // true
```

---

### **71. Object destructuring**

* Extract values from objects easily
* Improves readability

```js
const user = { name: "Sam", age: 25 };
const { name, age } = user;
```

---

### **72. Merge two objects**

* Use spread operator or `Object.assign()`
* Spread is preferred

```js
const merged = { ...obj1, ...obj2 };
```

---

### **73. `for...in` vs `for...of`**

* `for...in` loops over **keys**
* `for...of` loops over **values**
* Use `for...of` for arrays

```js
for (let key in obj) {}
for (let value of arr) {}
```

---
## **Functions and Scope**
### **74. Local scope vs global scope**

* **Global scope** variables are accessible everywhere
* **Local scope** variables exist only inside functions or blocks
* Local scope prevents naming conflicts

```js
let globalVar = "Hi";

function test() {
  let localVar = "Hello";
}
```

---

### **75. Lexical scoping**

* Scope is determined by **where the function is written**
* Inner functions can access outer variables
* Forms the base for closures

```js
function outer() {
  let msg = "Hello";
  function inner() {
    console.log(msg);
  }
}
```

---

### **76. Function expression vs declaration**

* **Declarations** are hoisted
* **Expressions** are not hoisted
* Expressions can be anonymous

```js
function greet() {}        // declaration
const sayHi = function(){} // expression
```

---

### **77. Use of `arguments` object**

* Holds all arguments passed to a function
* Useful when argument count is unknown
* Not available in arrow functions

```js
function sum() {
  console.log(arguments);
}
```

---

### **78. Purpose of `default` keyword**

* Provides default values to parameters
* Prevents `undefined` errors
* Makes functions safer

```js
function greet(name = "Guest") {
  console.log(name);
}
```

---

### **79. Returning multiple values**

* Return an **array** or an **object**
* Object is clearer for named values

```js
function getUser() {
  return { name: "Sam", age: 25 };
}
```

---

### **80. What is recursion?**

* A function that calls itself
* Must have a base condition
* Useful for trees and nested data

```js
function count(n) {
  if (n === 0) return;
  count(n - 1);
}
```

---

### **81. Handling multiple callbacks**

* JavaScript queues callbacks
* Uses the event loop
* Callbacks execute when the stack is clear

```js
setTimeout(() => console.log("One"), 0);
setTimeout(() => console.log("Two"), 0);
```

---

### **82. Handling errors in JavaScript**

* Use `try...catch` for runtime errors
* Use `.catch()` for promises

```js
try {
  throw new Error("Oops");
} catch (e) {
  console.log(e.message);
}
```

---

### **83. Handling asynchronous operations**

* Uses callbacks, promises, and `async/await`
* Event loop manages execution
* Keeps app responsive

```js
async function load() {
  await fetchData();
}
```

---
## **Asynchronous JavaScript**
### **84. Handling asynchronous operations**

* JavaScript handles async work using **callbacks, promises, and async/await**
* `async/await` is the cleanest approach today
* Keeps the UI responsive

```js
async function loadData() {
  const data = await fetchData();
  console.log(data);
}
```

---

### **85. JavaScript’s single-threaded model**

* JavaScript runs on **one main thread**
* It executes **one task at a time**
* Async tasks are handled using the **event loop**

```js
console.log("A");
setTimeout(() => console.log("B"), 0);
console.log("C");
```

---

### **86. Mutable data types in JavaScript**

* **Objects and arrays** are mutable
* Their values can be changed after creation
* Primitive types are immutable

```js
const arr = [1, 2];
arr.push(3); // allowed
```

---

### **87. What is a function & how to declare it**

* A function is a reusable block of code
* Declared using function keyword or arrow syntax

```js
function add(a, b) {
  return a + b;
}

const sum = (a, b) => a + b;
```

---

### **88. Handling async operations in sequence**

* Use **promise chaining** or `async/await`
* Ensures tasks run one after another

```js
async function run() {
  await task1();
  await task2();
}
```

---

### **89. `Promise.all()` and `Promise.race()`**

* `Promise.all()` waits for **all promises**
* `Promise.race()` resolves with the **first one**

```js
Promise.all([p1, p2]).then(console.log);
Promise.race([p1, p2]).then(console.log);
```

---

### **90. Handling errors in async functions**

* Use `try...catch`
* Catches rejected promises

```js
async function load() {
  try {
    await fetchData();
  } catch (err) {
    console.log(err);
  }
}
```

---

### **91. Callback hell & how to avoid it**

* Callback hell happens due to **nested callbacks**
* Makes code hard to read
* Avoid using promises or async/await

```js
// Avoid this
doA(() => doB(() => doC()));

// Prefer this
await doA();
```

---

### **92. Ways to loop through an array**

* `for`
* `forEach`
* `map`
* `for...of`

```js
arr.map(item => console.log(item));
```

---
## **ES6 and Beyond**
### **93. Template literals**

* Template literals allow **string interpolation**
* Use backticks instead of quotes
* Support multi-line strings

```js
const name = "Sam";
console.log(`Hello, ${name}`);
```

---

### **94. New features introduced in ES6**

* `let` and `const`
* Arrow functions
* Classes
* Promises
* Destructuring
* Spread/rest operators
* Modules

```js
const add = (a, b) => a + b;
```

---

### **95. Set and Map**

* **Set** stores unique values
* **Map** stores key-value pairs
* Keys in Map can be any type

```js
const set = new Set([1, 2, 2]);
const map = new Map([["id", 1]]);
```

---

### **96. Symbols**

* Symbols create **unique identifiers**
* Avoid property name collisions
* Often used for internal object properties

```js
const id = Symbol("id");
obj[id] = 123;
```

---

### **97. Generator functions**

* Functions that can **pause and resume**
* Use `yield` keyword
* Useful for iterators

```js
function* gen() {
  yield 1;
  yield 2;
}
```

---

### **98. Destructuring**

* Extract values from arrays or objects
* Makes code cleaner

```js
const { name, age } = user;
const [a, b] = arr;
```

---

### **99. Spread operator**

* Expands arrays or objects
* Used for copying and merging

```js
const newArr = [...arr];
const newObj = { ...obj };
```

---

### **100. Rest parameter**

* Collects remaining arguments
* Used in function parameters

```js
function sum(...nums) {
  return nums.reduce((a, b) => a + b);
}
```

---

### **101. `Promise.allSettled()`, `Promise.any()`, `Promise.finally()`**

* `allSettled()` → waits for all, success or failure
* `any()` → first fulfilled promise
* `finally()` → runs cleanup code

```js
Promise.allSettled([p1, p2]).then(console.log);
```

---

### **102. Creating a class**

* Use `class` keyword
* Constructor initializes values

```js
class User {
  constructor(name) {
    this.name = name;
  }
}
```

---

### **103. `class` vs function constructors**

* Classes are **syntactic sugar**
* Classes are easier to read
* Function constructors use prototypes manually

```js
function User(name) {
  this.name = name;
}

class Person {
  constructor(name) {
    this.name = name;
  }
}
```

---
## **Error Handling and Debugging**
### **104. What is `try...catch` in JavaScript?**

* `try...catch` is used to **handle runtime errors**
* Code inside `try` is monitored for errors
* If an error occurs, `catch` runs instead of crashing the app
* Optional `finally` runs no matter what

```js
try {
  JSON.parse("{ bad json }");
} catch (err) {
  console.log("Error handled");
}
```

---

### **105. Difference between `throw` and `return`**

* `throw` creates and sends an **error**
* It **stops execution** immediately
* `return` sends a value back and ends the function normally

```js
function test(x) {
  if (!x) throw new Error("Invalid");
  return x;
}
```

---

### **106. Handling exceptions in JavaScript**

* Use `try...catch` for synchronous errors
* Use `.catch()` for promise errors
* Use `try...catch` with `async/await`

```js
async function load() {
  try {
    await fetchData();
  } catch (e) {
    console.log(e.message);
  }
}
```

---

### **107. Common JavaScript debugging techniques**

* Use `console.log()` to inspect values
* Use browser **DevTools**
* Set breakpoints
* Use `debugger` keyword

```js
debugger;
console.log("Check value");
```

---

### **108. `Error` vs `TypeError`**

* `Error` is a **generic error object**
* `TypeError` occurs when a value is not of the expected type
* `TypeError` is automatically thrown by JavaScript

```js
throw new Error("Custom error");

let x = 5;
x(); // TypeError
```

---
## **JavaScript Modules**
### **109. `import` vs `require`**

* `import` is part of **ES Modules**
* `require` is used in **CommonJS** (Node.js)
* `import` is **static** and supports tree-shaking
* `require` is **dynamic**

```js
// ES Module
import { add } from "./math.js";

// CommonJS
const add = require("./math");
```

---

### **110. Purpose of `export`**

* `export` makes variables, functions, or classes available outside a file
* Helps share code across files
* Improves maintainability

```js
export function greet() {
  console.log("Hello");
}
```

---

### **111. JavaScript arrays & manipulation**

* Arrays store **ordered collections**
* Manipulated using methods like `push`, `pop`, `map`, `filter`

```js
const arr = [1, 2, 3];
arr.push(4);
const doubled = arr.map(n => n * 2);
```

---

### **112. Implementing modules in JavaScript**

* Split code into separate files
* Use `export` and `import`
* Supported in modern browsers and Node.js

```js
// math.js
export const sum = (a, b) => a + b;

// app.js
import { sum } from "./math.js";
```

---

### **113. Default export**

* A file can have **only one default export**
* Imported without curly braces
* Name can be anything

```js
// user.js
export default function user() {}

// app.js
import user from "./user.js";
```

---

### **114. Benefits of using modules**

* Better code organization
* Reusability
* Avoids global scope pollution
* Easier testing and maintenance

```js
// clean, reusable, modular code
```

---
## **Browser and DOM**
### **115. Making an AJAX request**

* AJAX lets you fetch data **without reloading the page**
* Older way used `XMLHttpRequest`
* Modern approach uses **Fetch API**

```js
const xhr = new XMLHttpRequest();
xhr.open("GET", "/api/data");
xhr.send();
```

---

### **116. What is the Fetch API?**

* Fetch is a modern way to make HTTP requests
* It returns a **Promise**
* Cleaner than `XMLHttpRequest`

```js
fetch("/api/data")
  .then(res => res.json())
  .then(data => console.log(data));
```

---

### **117. Single-Page Application (SPA)**

* SPA loads **one HTML page**
* Content updates dynamically
* Faster and smoother user experience

```js
// React, Angular, Vue use SPA concept
```

---

### **118. What is the DOM?**

* DOM is a **tree-like structure** of HTML elements
* JavaScript uses it to read and modify the page
* Every element is a node

```js
document.getElementById("title");
```

---

### **119. Manipulating the DOM**

* Select elements
* Change content, styles, or attributes
* Add or remove elements

```js
const el = document.querySelector("h1");
el.textContent = "Updated";
```

---

### **120. `Object.freeze()` vs `Object.seal()`**

* `freeze()` → no changes allowed
* `seal()` → can modify existing properties, not add/remove
* Both prevent adding new properties

```js
Object.freeze(obj);
Object.seal(obj);
```

---

### **121. Prevent default action**

* Use `event.preventDefault()`
* Stops browser’s default behavior
* Common in forms and links

```js
form.addEventListener("submit", e => {
  e.preventDefault();
});
```

---

### **122. `addEventListener()` vs `onclick`**

* `onclick` allows **only one handler**
* `addEventListener()` allows **multiple handlers**
* `addEventListener()` supports capturing and bubbling
* Preferred in modern JavaScript

```js
btn.onclick = () => console.log("One");

btn.addEventListener("click", () => console.log("Two"));
```

---

### **123. `localStorage` and `sessionStorage`**

* Both store data in the browser
* `localStorage` persists even after closing the browser
* `sessionStorage` clears when the tab closes
* Store data as strings

```js
localStorage.setItem("user", "Sam");
sessionStorage.setItem("token", "123");
```

---

### **124. `localStorage` vs cookies**

* `localStorage` has **larger storage**
* Cookies are sent with **every HTTP request**
* Cookies can expire and be secured
* `localStorage` is client-side only

```js
document.cookie = "theme=dark";
```

---

### **125. Handling CORS**

* CORS is controlled by the **server**
* Server must send correct headers
* Client cannot fully bypass CORS
* Use proxies in development

```js
// Server sets:
Access-Control-Allow-Origin: *
```

---

### **126. Making an AJAX request**

* Use `XMLHttpRequest` or Fetch API
* Fetch is cleaner and modern

```js
fetch("/api/data")
  .then(res => res.json())
  .then(console.log);
```

---

### **127. `XMLHttpRequest` vs Fetch API**

* `XMLHttpRequest` is older and callback-based
* Fetch is promise-based and cleaner
* Fetch does not reject on HTTP errors automatically

```js
// Fetch
fetch("/data");
```

---

### **128. `Function.prototype.bind()`**

* `bind()` creates a **new function**
* Permanently binds `this`
* Useful in callbacks

```js
const greet = function () {
  console.log(this.name);
};

const bound = greet.bind({ name: "Sam" });
bound();
```

---

### **129. Handling cookies**

* Use `document.cookie`
* Cookies are stored as key-value strings
* Often handled by libraries or server

```js
document.cookie = "user=Sam; path=/";
```

---
## **Advanced Concepts**
### **130. Functional Programming vs OOP**

* Functional programming focuses on **pure functions** and immutability
* Avoids shared state and side effects
* OOP focuses on **objects and classes**
* JavaScript supports **both styles**

```js
// Functional
const add = (a, b) => a + b;

// OOP
class Calc {
  add(a, b) {
    return a + b;
  }
}
```

---

### **131. Abstract class in JavaScript**

* JavaScript doesn’t have true abstract classes
* You simulate them using base classes
* Methods are meant to be overridden

```js
class Shape {
  draw() {
    throw new Error("Must implement");
  }
}
```

---
## **Performance Optimization**
### **132. Memory leaks & prevention**

* Memory leaks happen when unused memory isn’t released
* Common causes: global variables, timers, DOM references
* Prevent by clearing timers and removing listeners

```js
clearInterval(timer);
element.removeEventListener("click", handler);
```

---

### **133. Decorators in JavaScript**

* Decorators modify classes or methods
* Mostly used in frameworks like Angular
* Still a proposal, not fully standard

```js
@log
class User {}
```

---

### **134. Optimizing JS performance**

* Minimize DOM access
* Debounce expensive events
* Use async operations
* Avoid unnecessary re-renders

```js
debounce(handleScroll, 300);
```

---

### **135. `requestAnimationFrame()`**

* Runs code before the browser repaints
* Ideal for animations
* More efficient than `setInterval`

```js
function animate() {
  requestAnimationFrame(animate);
}
animate();
```

---

### **136. Lazy loading**

* Load resources **only when needed**
* Improves initial load time
* Common for images and modules

```js
const img = new Image();
img.loading = "lazy";
```

---
## **Frameworks and Libraries**
### **137. Improving rendering performance**

* Reduce layout thrashing
* Batch DOM updates
* Use virtual DOM frameworks

```js
requestAnimationFrame(updateUI);
```

---

### **138. Frameworks vs libraries**

* **Library:** you call it (React, Lodash)
* **Framework:** it calls you (Angular)
* Frameworks provide full structure

```js
// React = library
// Angular = framework
```

---
## **Testing and Tools**
### **139. What is unit testing in JavaScript?**

* Unit testing tests **small pieces of code**, usually functions
* Ensures each unit works as expected
* Makes code safer to refactor

```js
function add(a, b) {
  return a + b;
}
// test expects add(2,3) === 5
```

---

### **140. Popular JavaScript testing frameworks**

* **Jest** – very popular, all-in-one
* **Mocha** – flexible test runner
* **Chai** – assertion library
* **Cypress** – end-to-end testing

```js
test("adds numbers", () => {
  expect(add(2, 3)).toBe(5);
});
```

---

### **141. What is TDD?**

* TDD means **write tests first**
* Then write code to pass tests
* Improves design and reliability

```js
// 1. Write test
// 2. Write code
// 3. Refactor
```

---

### **142. Writing asynchronous tests**

* Use `async/await`
* Or return a promise
* Or use callback-based `done`

```js
test("async test", async () => {
  const data = await fetchData();
  expect(data).toBeDefined();
});
```

---

### **143. `assert` vs `expect`**

* `assert` uses **function-style checks**
* `expect` uses **chainable, readable syntax**
* `expect` is more common in Jest

```js
assert.equal(sum(2, 2), 4);
expect(sum(2, 2)).toBe(4);
```

---
## **Advanced JavaScript Features**
### **144. Using regular expressions**

* Regex is used for **pattern matching**
* Common in validation and search
* Use `.test()` or `.match()`

```js
const emailRegex = /\S+@\S+\.\S+/;
emailRegex.test("a@b.com");
```

---

### **145. What are Web Workers?**

* Web Workers run JavaScript in **background threads**
* Prevent UI blocking
* Used for heavy computations

```js
const worker = new Worker("worker.js");
```

---

### **146. Service workers**

* Run in the background
* Enable **offline support**, caching, push notifications
* Used in Progressive Web Apps (PWA)

```js
navigator.serviceWorker.register("sw.js");
```

---

### **147. Comparing two objects**

* Objects are compared by **reference**
* Deep comparison requires manual or library logic

```js
JSON.stringify(obj1) === JSON.stringify(obj2);
```

---

### **148. Function expression vs declaration**

* Declarations are **hoisted**
* Expressions are **not hoisted**
* Expressions can be anonymous

```js
function test() {}
const demo = function () {};
```

---
## **JavaScript Best Practices**
### **149. `slice()` vs `splice()`**

* `slice()` does **not modify** the array
* `splice()` **changes** the array
* `splice()` can add or remove items

```js
arr.slice(1, 3);
arr.splice(1, 1);
```
---

### **150. Importance of code minification**

* Minification removes **spaces, comments, and unused code**
* Reduces file size
* Improves page load speed and performance
* Common in production builds

```js
// Original
function add(a, b) { return a + b; }

// Minified
function add(a,b){return a+b;}
```

---

### **151. Handling large-scale JavaScript applications**

* Break code into **modules**
* Use frameworks like React or Angular
* Follow consistent folder structure
* Write tests and use state management

```js
// feature-based folder structure
```

---

### **152. JavaScript security considerations**

* Prevent **XSS** by escaping user input
* Avoid `eval()`
* Use HTTPS
* Secure cookies and tokens

```js
element.textContent = userInput; // safer
```

---

### **153. Managing JavaScript dependencies**

* Use package managers like **npm** or **yarn**
* Lock dependency versions
* Keep dependencies updated

```bash
npm install lodash
```

---

### **154. Importance of modularity**

* Makes code **reusable and maintainable**
* Easier testing and debugging
* Avoids global scope pollution

```js
export function sum(a, b) {}
```

---

### **155. Documenting JavaScript code**

* Use **JSDoc comments**
* Write clear function descriptions
* Document parameters and return values

```js
/**
 * Adds two numbers
 */
function add(a, b) {}
```

---

### **156. Ensuring cross-browser compatibility**

* Use modern JS with **Babel**
* Avoid unsupported APIs
* Test across browsers
* Use polyfills when needed

```js
// Babel transpiles modern JS
```

---

### **157. Design patterns in JavaScript**

* Reusable solutions to common problems
* Examples: Module, Singleton, Observer
* Improve code structure and scalability

```js
const Module = (() => {
  return { greet() { console.log("Hi"); } };
})();
```


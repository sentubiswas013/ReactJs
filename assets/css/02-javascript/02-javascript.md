## 1. 📌 JavaScript Interview Questions – Fundamentals & Syntax
### Data Types, Variables, and Operators
---

### 1. What are the different data types in JavaScript?

**Answer:**
JavaScript has two categories of data types:

1. **Primitive Types (immutable):**

   * `string`
   * `number`
   * `boolean`
   * `null`
   * `undefined`
   * `symbol` (ES6)
   * `bigint` (ES2020)

2. **Non-primitive (Reference) Types:**

   * `object` (includes arrays, functions, dates, etc.)

**Example:**

```javascript
let str = "Hello";       // string
let num = 42;            // number
let isTrue = false;      // boolean
let nothing = null;      // null
let notDefined;          // undefined
let sym = Symbol("id");  // symbol
let big = 12345678901234567890n; // bigint
let obj = { name: "JS" }; // object
```

---

### 2. What is the difference between `null` and `undefined`?

**Answer:**

* `null`: Represents intentional absence of any value (manually assigned).
* `undefined`: Represents a variable that has been declared but not assigned a value.

**Example:**

```javascript
let a;            // undefined
let b = null;     // null

console.log(a);   // undefined
console.log(b);   // null
console.log(a == b);  // true  (values are loosely equal)
console.log(a === b); // false (different types)
```

---

### 3. What is the difference between `==` and `===`?

**Answer:**

* `==` → **Loose Equality** (performs type coercion before comparison).
* `===` → **Strict Equality** (checks both value and type).

**Example:**

```javascript
console.log(2 == "2");   // true (string "2" converted to number)
console.log(2 === "2");  // false (number vs string)
console.log(null == undefined);  // true
console.log(null === undefined); // false
```

---

### 4. What is the difference between `var`, `let`, and `const`?

**Answer:**

* `var`: Function-scoped, can be re-declared and updated, hoisted with `undefined`.
* `let`: Block-scoped, can be updated but not re-declared in the same scope.
* `const`: Block-scoped, must be initialized, cannot be updated or re-declared.

**Example:**

```javascript
var x = 10;
var x = 20;   // allowed

let y = 30;
// let y = 40;  // Error: re-declaration not allowed
y = 40;       // allowed

const z = 50;
// z = 60;     // Error: cannot reassign
```

---

### 5. Explain type coercion in JavaScript.

**Answer:**
Type coercion is the automatic conversion of one data type to another when using operators or comparisons.

**Example:**

```javascript
console.log("5" + 2);   // "52" (number → string)
console.log("5" - 2);   // 3   (string → number)
console.log(true + 1);  // 2   (true → 1)
console.log(false == 0); // true (boolean → number)
```

---

### 6. What are truthy and falsy values?

**Answer:**

* **Falsy values:** `false`, `0`, `""` (empty string), `null`, `undefined`, `NaN`.
* **Truthy values:** Everything else (non-empty strings, numbers except 0, objects, arrays).

**Example:**

```javascript
if (0) console.log("Falsy"); // won't run
if ("hello") console.log("Truthy"); // runs
```

---

### 7. What is the difference between primitive and reference data types?

**Answer:**

* **Primitive**: Stored directly in the stack, immutable.
* **Reference**: Stored in heap memory, variables hold a reference (pointer).

**Example:**

```javascript
let a = 10;
let b = a;
b = 20;
console.log(a); // 10 (copy independent)

let obj1 = { value: 10 };
let obj2 = obj1;
obj2.value = 20;
console.log(obj1.value); // 20 (both reference same object)
```

---

### 8. What is `NaN` in JavaScript?

**Answer:**

* `NaN` stands for **Not-a-Number**.
* It is a special numeric value indicating an invalid number operation.

**Example:**

```javascript
console.log(0 / 0);        // NaN
console.log("abc" * 3);    // NaN
console.log(NaN === NaN);  // false
console.log(Number.isNaN(NaN)); // true
```

---

### 9. What is the difference between `typeof` and `instanceof`?

**Answer:**

* `typeof` → Returns the type of a variable (string result).
* `instanceof` → Checks if an object is an instance of a constructor.

**Example:**

```javascript
console.log(typeof "hello");   // string
console.log(typeof 42);        // number
console.log(typeof {});        // object

console.log([] instanceof Array);  // true
console.log({} instanceof Object); // true
```

---

### 10. What is the difference between `Object.is()` and `===`?

**Answer:**

* `===` considers `+0` and `-0` as equal, and `NaN !== NaN`.
* `Object.is()` handles these edge cases correctly.

**Example:**

```javascript
console.log(+0 === -0);        // true
console.log(Object.is(+0, -0)); // false

console.log(NaN === NaN);        // false
console.log(Object.is(NaN, NaN)); // true
```

### Truthy/Falsy, Scope, Hoisting, and Closures
### 🔹 Truthy / Falsy

---

### 1. What are truthy and falsy values in JavaScript?

**Answer:**

* **Falsy values:** `false`, `0`, `-0`, `0n` (BigInt zero), `""` (empty string), `null`, `undefined`, `NaN`.
* **Truthy values:** Everything else.

**Example:**

```javascript
if ("hello") console.log("Truthy");  // runs
if (0) console.log("Falsy");         // doesn't run
```

---

### 2. How does JavaScript handle type coercion with truthy/falsy values?

**Answer:**
In conditional statements, JavaScript converts values into `true` or `false`.

**Example:**

```javascript
console.log(Boolean(""));     // false
console.log(Boolean(" "));    // true (non-empty string)
console.log(Boolean([]));     // true (empty array is truthy)
console.log(Boolean({}));     // true (empty object is truthy)
```

---

### 3. Give an example where falsy values can cause bugs.

**Answer:**
When checking if a variable has a value, `0` or `""` may be wrongly treated as `false`.

**Example:**

```javascript
let count = 0;
if (count) {
  console.log("Has value"); // Won’t run
} else {
  console.log("Falsy but valid"); // runs
}
```

---

### 🔹 Scope

---

### 4. What are the different types of scope in JavaScript?

**Answer:**

1. **Global Scope** – Accessible everywhere.
2. **Function Scope** – Variables declared inside a function are accessible only inside.
3. **Block Scope** – Variables declared with `let` and `const` inside `{}` are limited to that block.
4. **Lexical Scope** – Inner functions can access outer variables.

**Example:**

```javascript
let globalVar = "I’m global";

function example() {
  let localVar = "I’m local";
  if (true) {
    let blockVar = "I’m block scoped";
    console.log(blockVar); // accessible
  }
  // console.log(blockVar); // error
}

example();
```

---

### 5. What is the difference between `var`, `let`, and `const` in terms of scope?

**Answer:**

* `var` → Function scoped, not block scoped.
* `let` & `const` → Block scoped.

**Example:**

```javascript
if (true) {
  var x = 10;
  let y = 20;
}
console.log(x); // 10
// console.log(y); // Error (block scoped)
```

---

### 6. What is lexical scope?

**Answer:**
Lexical scope means functions are executed using the scope chain from where they were **defined**, not where they were **called**.

**Example:**

```javascript
function outer() {
  let name = "JS";
  function inner() {
    console.log(name); // can access outer variable
  }
  inner();
}
outer(); // JS
```

### 🔹 Hoisting

---

### 7. What is hoisting in JavaScript?

**Answer:**
Hoisting is JavaScript’s behavior of moving variable and function declarations to the top of their scope before execution.

**Example:**

```javascript
console.log(a); // undefined
var a = 5;

sayHi(); // "Hi"
function sayHi() {
  console.log("Hi");
}
```

---

### 8. Do `let` and `const` get hoisted?

**Answer:**
Yes, but they are placed in a **Temporal Dead Zone (TDZ)** until initialized, meaning you cannot access them before declaration.

**Example:**

```javascript
console.log(a); // undefined
var a = 5;

console.log(b); // ReferenceError
let b = 10;
```

---

### 9. What gets hoisted first: functions or variables?

**Answer:**
Function declarations are hoisted **before variables**, so they can be used even before their declaration.

**Example:**

```javascript
foo(); // works
function foo() { console.log("function hoisted"); }

console.log(x); // undefined
var x = 10;
```

### 🔹 Closures

---

### 10. What is a closure in JavaScript?

**Answer:**
A closure is a function that “remembers” the variables from its outer scope even after the outer function has finished executing.

**Example:**

```javascript
function outer() {
  let count = 0;
  return function inner() {
    count++;
    return count;
  };
}

const counter = outer();
console.log(counter()); // 1
console.log(counter()); // 2
```

---

### 11. Give a real-world use case for closures.

**Answer:**
Closures are often used for **data privacy** or **encapsulation**.

**Example:**

```javascript
function bankAccount(initialBalance) {
  let balance = initialBalance;
  return {
    deposit(amount) { balance += amount; },
    getBalance() { return balance; }
  };
}

const myAcc = bankAccount(100);
myAcc.deposit(50);
console.log(myAcc.getBalance()); // 150
```

---

### 12. What is the difference between a closure and a scope?

**Answer:**

* **Scope** → Defines variable accessibility.
* **Closure** → Happens when an inner function retains access to variables from its outer scope even after the outer function has returned.

---

### 13. Can closures lead to memory leaks?

**Answer:**
Yes, if not managed properly. Since closures keep references to outer variables, they may prevent garbage collection.

**Example:**

```javascript
function createBigClosure() {
  let largeArray = new Array(1000000).fill("data");
  return function() {
    console.log(largeArray.length);
  };
}
let leak = createBigClosure(); // keeps largeArray in memory
```

### 🔹 Function Declaration & Expression

---

### 1. What is the difference between a function declaration and a function expression?

**Answer:**

* **Function Declaration** → Named function, hoisted (can be called before definition).
* **Function Expression** → Function assigned to a variable, not hoisted.

**Example:**

```javascript
// Function Declaration
sayHello(); // works (hoisted)
function sayHello() {
  console.log("Hello!");
}

// Function Expression
// greet(); // Error: Cannot access before initialization
const greet = function() {
  console.log("Hi!");
};
greet(); // Hi!
```

---

### 2. What are the advantages of using function expressions?

**Answer:**

* Useful for **callbacks** and **closures**.
* Helps avoid polluting global scope.
* Allows use of **anonymous functions**.

**Example:**

```javascript
setTimeout(function() {
  console.log("Executed later!");
}, 1000);
```

### 🔹 Arrow Functions

---

### 3. What are arrow functions and how are they different from regular functions?

**Answer:**

* Introduced in ES6.
* Shorter syntax.
* Do not have their own `this`, `arguments`, or `prototype`.
* Useful for callbacks.

**Example:**

```javascript
// Regular function
const add = function(a, b) {
  return a + b;
};

// Arrow function
const addArrow = (a, b) => a + b;

console.log(add(2,3));       // 5
console.log(addArrow(2,3));  // 5
```

---

### 4. Why can’t arrow functions be used as constructors?

**Answer:**
Arrow functions don’t have their own `this` or `prototype`, so they cannot be used with `new`.

**Example:**

```javascript
const Person = (name) => { this.name = name; };
// const p = new Person("John"); // Error: not a constructor
```

---

### 5. How do arrow functions handle `this` differently than regular functions?

**Answer:**

* Regular functions: `this` depends on how the function is called.
* Arrow functions: `this` is lexically inherited from the enclosing scope.

**Example:**

```javascript
function Timer() {
  this.seconds = 0;
  setInterval(() => {
    this.seconds++;
    console.log(this.seconds); // uses outer "this"
  }, 1000);
}
new Timer(); // increments correctly
```

### 🔹 IIFE (Immediately Invoked Function Expression)

---

### 6. What is an IIFE and why is it used?

**Answer:**

* **IIFE** = Immediately Invoked Function Expression.
* Runs immediately after definition.
* Used to create a private scope and avoid polluting global variables.

**Example:**

```javascript
(function() {
  let privateVar = "hidden";
  console.log("IIFE executed!");
})(); // runs immediately

// console.log(privateVar); // Error: not defined
```

---

### 7. Can IIFEs be written with arrow functions?

**Answer:**
Yes, but need parentheses around them.

**Example:**

```javascript
(() => {
  console.log("Arrow IIFE!");
})(); 
```

### 🔹 Recursion

---

### 8. What is recursion? Provide an example.

**Answer:**
Recursion is when a function calls itself until a condition is met.

**Example: Factorial function**

```javascript
function factorial(n) {
  if (n === 0) return 1; // base case
  return n * factorial(n - 1);
}

console.log(factorial(5)); // 120
```

---

### 9. What are the risks of recursion?

**Answer:**

* Can cause **stack overflow** if base case is missing.
* Less performant than iteration for very deep calls.

**Example:**

```javascript
function infiniteRecursion() {
  return infiniteRecursion(); // No base case → stack overflow
}
```

---

### 10. Give a real-world use case for recursion.

**Answer:**

* Traversing a **file system tree**
* Searching nested objects
* Computing **Fibonacci sequence**

**Example: Fibonacci**

```javascript
function fib(n) {
  if (n <= 1) return n;
  return fib(n - 1) + fib(n - 2);
}
console.log(fib(6)); // 8
```

### 🔹 Default Parameters

---

### 11. What are default parameters in JavaScript?

**Answer:**
Introduced in ES6. Allows setting default values for function parameters.

**Example:**

```javascript
function greet(name = "Guest") {
  console.log(`Hello, ${name}`);
}
greet();         // Hello, Guest
greet("Alice");  // Hello, Alice
```

---

### 12. Can default parameters be functions or expressions?

**Answer:**
Yes, they can be any expression.

**Example:**

```javascript
function getTime() {
  return new Date().toLocaleTimeString();
}

function log(message, time = getTime()) {
  console.log(`[${time}] ${message}`);
}

log("Started"); // prints with current time
```

### 🔹 Function Arguments

---

### 13. What is the `arguments` object in JavaScript?

**Answer:**

* Array-like object available inside regular functions.
* Contains all passed arguments.
* Not available in arrow functions.

**Example:**

```javascript
function sum() {
  let total = 0;
  for (let i = 0; i < arguments.length; i++) {
    total += arguments[i];
  }
  return total;
}
console.log(sum(1,2,3,4)); // 10
```

---

### 14. What is the difference between `arguments` object and rest parameters (`...args`)?

**Answer:**

* `arguments`: Array-like, not real array, not available in arrow functions.
* `...args`: Real array, modern ES6 feature, works with arrow functions.

**Example:**

```javascript
function sumOld() {
  console.log(arguments); // array-like
}

const sumNew = (...args) => {
  console.log(args); // real array
};

sumOld(1,2,3);
sumNew(1,2,3);
```

---

### 15. What are higher-order functions in JavaScript?

**Answer:**
Functions that **take another function as argument** or **return a function**.

**Example:**

```javascript
function multiplyBy(factor) {
  return function(num) {
    return num * factor;
  };
}

const double = multiplyBy(2);
console.log(double(5)); // 10
```

## 2. 📌 JavaScript Interview Questions – Error Handling & Anonymous Functions
### 🔹 Error Handling

---

### 1. What are the different types of errors in JavaScript?

**Answer:**

1. **Syntax Error** – Mistake in code structure.
2. **Reference Error** – Refers to an undeclared variable.
3. **Type Error** – Performing an invalid operation on a value.
4. **Range Error** – Number outside the allowed range.
5. **Eval Error** – Error related to `eval()` (rare).
6. **URI Error** – Incorrect use of URI functions (`decodeURI`, etc.).

**Example:**

```javascript
// SyntaxError
// console.log("missing quote);

// ReferenceError
// console.log(notDefined);

// TypeError
let num = 5;
// num.toUpperCase(); // TypeError
```

---

### 2. How does `try...catch` work in JavaScript?

**Answer:**
`try` block contains code that may throw errors.
`catch` handles the error gracefully instead of crashing the program.

**Example:**

```javascript
try {
  let result = 10 / x; // x is not defined
} catch (error) {
  console.log("Error caught:", error.message);
}
```

---

### 3. What is the difference between `throw` and `return` in JavaScript?

**Answer:**

* `return` → Exits function and gives a value.
* `throw` → Generates an error that must be handled with `try...catch`.

**Example:**

```javascript
function divide(a, b) {
  if (b === 0) throw new Error("Division by zero!");
  return a / b;
}

try {
  console.log(divide(10, 0));
} catch (err) {
  console.log("Caught:", err.message);
}
```

---

### 4. How do you create custom errors in JavaScript?

**Answer:**
You can extend the built-in `Error` class.

**Example:**

```javascript
class ValidationError extends Error {
  constructor(message) {
    super(message);
    this.name = "ValidationError";
  }
}

try {
  throw new ValidationError("Invalid email!");
} catch (e) {
  console.log(e.name, e.message); // ValidationError Invalid email!
}
```

---

### 5. What is the `finally` block used for?

**Answer:**

* Always executes whether an error occurs or not.
* Useful for cleanup tasks (closing DB, clearing timers).

**Example:**

```javascript
try {
  console.log("Try block");
  throw new Error("Oops!");
} catch (e) {
  console.log("Caught error");
} finally {
  console.log("Cleanup always runs");
}
```

---

### 6. What is the difference between synchronous and asynchronous error handling?

**Answer:**

* **Synchronous:** Handled using `try...catch`.
* **Asynchronous (Promises/async):** Errors caught with `.catch()` or `try...catch` inside `async` functions.

**Example (Promise):**

```javascript
fetch("wrong-url")
  .then(res => res.json())
  .catch(err => console.log("Async error:", err.message));
```

**Example (async/await):**

```javascript
async function getData() {
  try {
    let res = await fetch("wrong-url");
  } catch (e) {
    console.log("Caught async error:", e.message);
  }
}
getData();
```

### 🔹 Anonymous Functions

---

### 7. What is an anonymous function in JavaScript?

**Answer:**
A function without a name, often used as a **callback** or in **function expressions**.

**Example:**

```javascript
setTimeout(function() {
  console.log("Anonymous function executed");
}, 1000);
```

---

### 8. What are the advantages of anonymous functions?

**Answer:**

* Concise and useful for one-time use.
* Perfect for callbacks.
* Reduce global scope pollution.

**Example:**

```javascript
[1, 2, 3].forEach(function(num) {
  console.log(num * 2);
});
```

---

### 9. What are the disadvantages of anonymous functions?

**Answer:**

* Hard to debug (stack trace doesn’t show function name).
* Cannot be reused elsewhere.
* Less readable in complex code.

---

### 10. How are anonymous functions different from arrow functions?

**Answer:**

* Arrow functions are always anonymous, but with special behavior:

  * Lexical `this` binding.
  * Cannot be used as constructors.
* Regular anonymous functions create their own `this`.

**Example:**

```javascript
function Regular() {
  this.value = 10;
  setTimeout(function() {
    console.log(this.value); // undefined (different `this`)
  }, 100);
}

function Arrow() {
  this.value = 20;
  setTimeout(() => {
    console.log(this.value); // 20 (lexical `this`)
  }, 100);
}

new Regular();
new Arrow();
```

---

### 11. Can anonymous functions be assigned to variables?

**Answer:**
Yes, when assigned, they become **function expressions**.

**Example:**

```javascript
const add = function(a, b) {
  return a + b;
};
console.log(add(2, 3)); // 5
```

---

### 12. Can anonymous functions be used inside IIFEs?

**Answer:**
Yes, they often are.

**Example:**

```javascript
(function() {
  console.log("Anonymous IIFE executed!");
})();
```

## 3. 📌 JavaScript Interview Questions – Objects & Object-Oriented Programming
### 🔹 Objects Basics

---

### 1. What is an object in JavaScript?

**Answer:**
An object is a collection of key-value pairs, where keys are strings (or symbols) and values can be of any type (including other objects or functions).

**Example:**

```javascript
let person = {
  name: "Alice",
  age: 25,
  greet: function() {
    console.log("Hello, " + this.name);
  }
};
person.greet(); // Hello, Alice
```

---

### 2. How can you create objects in JavaScript?

**Answer:**

1. **Object literal:**

```javascript
let obj1 = { a: 1, b: 2 };
```

2. **Using `new Object()`:**

```javascript
let obj2 = new Object();
obj2.a = 1;
```

3. **Constructor function:**

```javascript
function Person(name) {
  this.name = name;
}
let p = new Person("John");
```

4. **`Object.create()`:**

```javascript
let proto = { greet() { console.log("Hello"); } };
let obj3 = Object.create(proto);
```

5. **Class syntax (ES6):**

```javascript
class Animal {
  constructor(type) { this.type = type; }
}
let dog = new Animal("Dog");
```

---

### 3. What is the difference between dot notation and bracket notation in objects?

**Answer:**

* **Dot notation** → Only works with valid identifiers.
* **Bracket notation** → Works with dynamic keys or special characters.

**Example:**

```javascript
let obj = { name: "JS", "user-id": 101 };
console.log(obj.name);       // JS
console.log(obj["user-id"]); // 101

let key = "name";
console.log(obj[key]);       // JS
```

---

### 4. How do you check if a property exists in an object?

**Answer:**

1. `in` operator
2. `hasOwnProperty()`
3. `Object.hasOwn()` (ES2022)

**Example:**

```javascript
let obj = { x: 10 };
console.log("x" in obj);              // true
console.log(obj.hasOwnProperty("x")); // true
console.log(Object.hasOwn(obj, "x")); // true
```

---

### 5. What is object destructuring?

**Answer:**
A shorthand way to extract properties from objects.

**Example:**

```javascript
let user = { id: 1, name: "Alice" };
let { id, name } = user;
console.log(id, name); // 1 Alice
```

##W 🔹 OOP Concepts

---

### 6. How does prototypal inheritance work in JavaScript?

**Answer:**
Objects inherit properties/methods from other objects via the prototype chain.

**Example:**

```javascript
let animal = { eats: true };
let dog = Object.create(animal);
dog.barks = true;

console.log(dog.eats);  // true (inherited)
console.log(dog.barks); // true (own property)
```

---

### 7. What is the difference between classical OOP and prototypal OOP?

**Answer:**

* **Classical OOP** (Java, C++): Objects created from classes.
* **Prototypal OOP** (JavaScript): Objects created from other objects (via prototypes).

---

### 8. How do classes work in JavaScript?

**Answer:**

* ES6 introduced `class` syntax as syntactic sugar over prototypes.
* Supports constructors, methods, inheritance with `extends`.

**Example:**

```javascript
class Animal {
  constructor(name) {
    this.name = name;
  }
  speak() {
    console.log(`${this.name} makes a sound`);
  }
}

class Dog extends Animal {
  speak() {
    console.log(`${this.name} barks`);
  }
}

let dog = new Dog("Rex");
dog.speak(); // Rex barks
```

---

### 9. What is the difference between instance methods and static methods in classes?

**Answer:**

* **Instance methods** → Available on objects created from the class.
* **Static methods** → Available only on the class itself.

**Example:**

```javascript
class MathUtils {
  static add(a, b) {
    return a + b;
  }
}

console.log(MathUtils.add(2, 3)); // 5
```

---

### 10. What is encapsulation in JavaScript OOP?

**Answer:**
Encapsulation means restricting direct access to some object properties/methods. Achieved using closures or **private fields** (`#` in ES2022).

**Example:**

```javascript
class BankAccount {
  #balance = 0;
  deposit(amount) { this.#balance += amount; }
  getBalance() { return this.#balance; }
}

let acc = new BankAccount();
acc.deposit(100);
console.log(acc.getBalance()); // 100
// console.log(acc.#balance); // Error
```

---

### 11. What is polymorphism in JavaScript?

**Answer:**
Polymorphism allows different classes to define the same method differently.

**Example:**

```javascript
class Shape {
  area() { return 0; }
}
class Circle extends Shape {
  constructor(r) { super(); this.r = r; }
  area() { return Math.PI * this.r * this.r; }
}
let c = new Circle(5);
console.log(c.area()); // 78.5
```

---

### 12. What is abstraction in JavaScript?

**Answer:**
Abstraction hides implementation details and exposes only the necessary functionality.
In JS, achieved using abstract-like base classes or interfaces with documentation.

**Example:**

```javascript
class Vehicle {
  start() { throw new Error("Method must be implemented"); }
}
class Car extends Vehicle {
  start() { console.log("Car starts with key"); }
}
new Car().start();
```

### 🔹 Advanced Object Features

---

### 13. What is object destructuring with rest properties?

**Answer:**
Extracts some properties and groups the rest into another object.

**Example:**

```javascript
let user = { id: 1, name: "Alice", age: 25 };
let { name, ...rest } = user;
console.log(name); // Alice
console.log(rest); // { id: 1, age: 25 }
```

---

### 14. What is object immutability? How do you achieve it?

**Answer:**

* Prevents modification of objects.
* Achieved with:

  * `Object.freeze(obj)` → Prevents changes.
  * `Object.seal(obj)` → Prevents adding/removing, but allows modification.

**Example:**

```javascript
let obj = { a: 1 };
Object.freeze(obj);
obj.a = 2; // ignored
console.log(obj.a); // 1
```

---

### 15. What is the difference between shallow copy and deep copy of objects?

**Answer:**

* **Shallow copy** → Copies only first-level properties. Nested objects still reference original.
* **Deep copy** → Copies everything recursively.

**Example:**

```javascript
let obj = { a: 1, nested: { b: 2 } };

let shallow = { ...obj };
shallow.nested.b = 10;
console.log(obj.nested.b); // 10 (linked!)

let deep = JSON.parse(JSON.stringify(obj));
deep.nested.b = 20;
console.log(obj.nested.b); // 10 (independent)
```

## 4. 📌 JavaScript Interview Questions – Arrays & Data Structures

---

### 1. What are arrays in JavaScript?

**Answer:**
An **array** is a special object in JavaScript used to store multiple values in a single variable.
Arrays are **zero-indexed** and can hold different data types.

**Example:**

```javascript
let arr = [1, "hello", true, { name: "JS" }];
console.log(arr[0]); // 1
console.log(arr[3].name); // JS
```

---

### 2. What are common ways to create arrays in JavaScript?

**Answer:**

1. **Array literal (recommended):**

```javascript
let arr = [1, 2, 3];
```

2. **Array constructor:**

```javascript
let arr = new Array(3); // [empty × 3]
```

3. **Using Array.of():**

```javascript
let arr = Array.of(5, 10); // [5, 10]
```

---

### 3. What is the difference between `Array.of()` and `Array()`?

**Answer:**

* `Array.of(5)` → `[5]` (creates array with elements).
* `Array(5)` → empty array with **length 5**.

**Example:**

```javascript
console.log(Array.of(5)); // [5]
console.log(Array(5));    // [ <5 empty items> ]
```

---

### 4. How do you check if a value is an array?

**Answer:**
Use `Array.isArray(value)` (recommended).

**Example:**

```javascript
console.log(Array.isArray([1,2,3])); // true
console.log(Array.isArray("test"));  // false
```

---

### 5. What is the difference between `for...of`, `for...in`, and `forEach()` for arrays?

**Answer:**

* `for...of` → Iterates over values.
* `for...in` → Iterates over keys (not recommended for arrays).
* `forEach()` → Calls callback for each element.

**Example:**

```javascript
let arr = ["a", "b", "c"];
for (let val of arr) console.log(val);  // a b c
for (let key in arr) console.log(key);  // 0 1 2
arr.forEach(v => console.log(v));       // a b c
```

---

### 6. Explain `map()`, `filter()`, and `reduce()` with examples.

**Answer:**

* `map()` → Transforms each element, returns a new array.
* `filter()` → Filters elements based on condition.
* `reduce()` → Accumulates values into a single result.

**Example:**

```javascript
let nums = [1, 2, 3, 4];

let doubled = nums.map(n => n * 2);       // [2,4,6,8]
let evens = nums.filter(n => n % 2 === 0);// [2,4]
let sum = nums.reduce((acc, n) => acc + n, 0); // 10
```

---

### 7. What’s the difference between `slice()` and `splice()`?

**Answer:**

* `slice(start, end)` → Returns a **shallow copy**, does not modify original.
* `splice(start, deleteCount, items...)` → Modifies array by removing/replacing elements.

**Example:**

```javascript
let arr = [1, 2, 3, 4];
console.log(arr.slice(1, 3)); // [2,3]
arr.splice(1, 2, 10, 20);
console.log(arr); // [1,10,20,4]
```

---

### 8. How do you flatten nested arrays?

**Answer:**
Use `flat(depth)`.

**Example:**

```javascript
let arr = [1, [2, [3, 4]]];
console.log(arr.flat());     // [1,2,[3,4]]
console.log(arr.flat(2));    // [1,2,3,4]
```

---

### 9. What is the difference between `find()` and `filter()`?

**Answer:**

* `find()` → Returns the **first matching element**.
* `filter()` → Returns **all matching elements** in an array.

**Example:**

```javascript
let nums = [5, 10, 15, 20];
console.log(nums.find(n => n > 10));  // 15
console.log(nums.filter(n => n > 10));// [15,20]
```

---

### 10. How do you remove duplicates from an array?

**Answer:**
Use `Set`.

**Example:**

```javascript
let nums = [1, 2, 2, 3, 4, 4];
let unique = [...new Set(nums)];
console.log(unique); // [1,2,3,4]
```

---

### 11. What is the difference between `some()` and `every()`?

**Answer:**

* `some()` → Returns `true` if **at least one** element passes.
* `every()` → Returns `true` if **all elements** pass.

**Example:**

```javascript
let nums = [1, 2, 3];
console.log(nums.some(n => n > 2)); // true
console.log(nums.every(n => n > 0));// true
```

---

### 12. How do you sort numbers correctly in JavaScript?

**Answer:**
By default, `sort()` treats elements as strings. Use comparator.

**Example:**

```javascript
let nums = [10, 2, 30];
console.log(nums.sort());          // [10,2,30] (wrong)
console.log(nums.sort((a,b)=>a-b));// [2,10,30] (correct)
```

---

### 13. How do you merge arrays?

**Answer:**
Use `concat()` or spread operator.

**Example:**

```javascript
let a = [1, 2], b = [3, 4];
console.log(a.concat(b));   // [1,2,3,4]
console.log([...a, ...b]);  // [1,2,3,4]
```

---

### 14. How can arrays be used to implement a stack and queue?

**Answer:**

* **Stack (LIFO):** `push()` + `pop()`.
* **Queue (FIFO):** `push()` + `shift()`.

**Example:**

```javascript
let stack = [];
stack.push(1); stack.push(2);
console.log(stack.pop()); // 2

let queue = [];
queue.push(1); queue.push(2);
console.log(queue.shift()); // 1
```

---

### 15. What is the difference between `null` array elements and "holes"?

**Answer:**

* `null` → Explicit empty value.
* "holes" → Undefined spots in sparse arrays.

**Example:**

```javascript
let arr = [1, , 3]; // hole at index 1
console.log(arr[1]);       // undefined
console.log(arr.length);   // 3
```

---

### 16. How do you convert array-like objects to arrays?

**Answer:**
Use `Array.from()` or spread.

**Example:**

```javascript
function test() {
  console.log(Array.from(arguments));
  console.log([...arguments]);
}
test(1, 2, 3); // [1,2,3]
```

---

### 17. What is a TypedArray in JavaScript?

**Answer:**

* Special array-like objects for binary data.
* Examples: `Int8Array`, `Float32Array`.

**Example:**

```javascript
let buf = new ArrayBuffer(8);
let intArr = new Int32Array(buf);
intArr[0] = 42;
console.log(intArr[0]); // 42
```

---

### 18. How do you deep clone an array?

**Answer:**

* Shallow copy → `slice()`, spread.
* Deep copy → `structuredClone()`, `JSON.parse(JSON.stringify())`.

**Example:**

```javascript
let arr = [[1,2], [3,4]];
let deep = structuredClone(arr);
deep[0][0] = 99;
console.log(arr[0][0]); // 1 (not affected)
```

---

### 19. How do you find the intersection of two arrays?

**Answer:**
Use `filter()` + `Set`.

**Example:**

```javascript
let a = [1,2,3,4], b = [3,4,5];
let inter = a.filter(x => b.includes(x));
console.log(inter); // [3,4]
```

---

### 20. How do you implement a linked list in JavaScript?

**Answer:**
Manually with objects.

**Example:**

```javascript
class Node {
  constructor(value) {
    this.value = value;
    this.next = null;
  }
}

let head = new Node(1);
head.next = new Node(2);
head.next.next = new Node(3);

console.log(head.value); // 1
console.log(head.next.value); // 2
```

## 5. 📌 JavaScript Interview Questions – Events & DOM

---

### 1. What is the DOM in JavaScript?

**Answer:**

* The **DOM (Document Object Model)** is a tree-like representation of an HTML document.
* JavaScript can manipulate the DOM to change structure, style, and content dynamically.

**Example:**

```javascript
document.getElementById("title").innerText = "Updated!";
```

---

### 2. How do you select elements in the DOM?

**Answer:**

* `getElementById()` → Single element by ID.
* `getElementsByClassName()` → Collection by class.
* `getElementsByTagName()` → Collection by tag.
* `querySelector()` → First match by CSS selector.
* `querySelectorAll()` → All matches by CSS selector.

**Example:**

```javascript
let el = document.querySelector(".btn");
console.log(el.innerText);
```

---

### 3. What is the difference between `innerHTML`, `innerText`, and `textContent`?

**Answer:**

* `innerHTML` → Gets/sets HTML content.
* `innerText` → Gets/sets visible text (ignores hidden).
* `textContent` → Gets/sets all text (including hidden).

**Example:**

```javascript
let el = document.getElementById("demo");
console.log(el.innerHTML);   // "<b>Hello</b>"
console.log(el.innerText);   // "Hello"
console.log(el.textContent); // "Hello"
```

---

### 4. How do you create and append DOM elements dynamically?

**Answer:**
Use `createElement()` and `appendChild()` / `append()`.

**Example:**

```javascript
let div = document.createElement("div");
div.innerText = "New Element";
document.body.appendChild(div);
```

---

### 5. What is the difference between `append()` and `appendChild()`?

**Answer:**

* `appendChild()` → Only accepts nodes, returns appended node.
* `append()` → Accepts nodes & text, returns nothing.

**Example:**

```javascript
let el = document.createElement("p");
document.body.appendChild(el);   // ✅ Works
document.body.append("Extra");   // ✅ Works with text
```

---

### 6. What are event listeners in JavaScript?

**Answer:**

* Used to handle user interactions like clicks, keypresses, etc.
* `addEventListener(type, listener)` attaches an event handler.

**Example:**

```javascript
document.getElementById("btn").addEventListener("click", () => {
  alert("Button clicked!");
});
```

---

### 7. What is the difference between inline event handlers and `addEventListener`?

**Answer:**

* **Inline:** Written in HTML (`onclick="fn()"`). Bad practice.
* **addEventListener:** Cleaner, allows multiple handlers.

**Example:**

```html
<button onclick="alert('inline')">Click</button>
<script>
document.querySelector("button").addEventListener("click", () => alert("Better!"));
</script>
```

---

### 8. What is event bubbling and capturing?

**Answer:**

* **Bubbling:** Event travels **from target → up to root** (default).
* **Capturing:** Event travels **from root → down to target**.

**Example:**

```javascript
document.querySelector("#child").addEventListener("click", () => console.log("Child"), true);  // capturing
document.querySelector("#parent").addEventListener("click", () => console.log("Parent"));      // bubbling
```

---

### 9. How do you stop event bubbling?

**Answer:**
Use `event.stopPropagation()`.

**Example:**

```javascript
document.getElementById("child").addEventListener("click", (e) => {
  e.stopPropagation();
  console.log("Only child clicked");
});
```

---

### 10. What is `preventDefault()` in event handling?

**Answer:**

* Stops the default browser behavior (like form submit, link navigation).

**Example:**

```javascript
document.querySelector("form").addEventListener("submit", (e) => {
  e.preventDefault();
  console.log("Form not submitted!");
});
```

---

### 11. What is event delegation in JavaScript?

**Answer:**

* Instead of attaching listeners to many child elements, attach one to a parent and use `event.target`.

**Example:**

```javascript
document.getElementById("list").addEventListener("click", (e) => {
  if (e.target.tagName === "LI") {
    console.log("Clicked:", e.target.innerText);
  }
});
```

---

### 12. What is the difference between `target` and `currentTarget` in events?

**Answer:**

* `event.target` → The actual element clicked.
* `event.currentTarget` → The element with the listener.

**Example:**

```javascript
document.getElementById("parent").addEventListener("click", (e) => {
  console.log("Target:", e.target.id); 
  console.log("Current Target:", e.currentTarget.id);
});
```

---

### 13. How do you remove an event listener?

**Answer:**
Use `removeEventListener()`. The function reference must match.

**Example:**

```javascript
function sayHi() { console.log("Hi"); }
let btn = document.getElementById("btn");

btn.addEventListener("click", sayHi);
btn.removeEventListener("click", sayHi);
```

---

### 14. What are custom events in JavaScript?

**Answer:**

* You can create and dispatch events with `CustomEvent`.

**Example:**

```javascript
let event = new CustomEvent("greet", { detail: { name: "John" } });
document.addEventListener("greet", e => console.log("Hello", e.detail.name));
document.dispatchEvent(event); // Hello John
```

---

### 15. What is the difference between HTMLCollection and NodeList?

**Answer:**

* **HTMLCollection:** Live (updates with DOM changes), returned by methods like `getElementsByClassName`.
* **NodeList:** Static, returned by `querySelectorAll`.

**Example:**

```javascript
let list1 = document.getElementsByClassName("item"); // live
let list2 = document.querySelectorAll(".item");      // static
```

---

### 16. How do you traverse the DOM?

**Answer:**

* `parentNode`, `children`, `firstChild`, `lastChild`, `nextSibling`, `previousSibling`.

**Example:**

```javascript
let el = document.getElementById("child");
console.log(el.parentNode.id); // parent id
```

---

### 17. What is the difference between `document.readyState` and `DOMContentLoaded`?

**Answer:**

* `DOMContentLoaded` fires when HTML is loaded & parsed (before images, CSS).
* `readyState` shows loading status (`loading`, `interactive`, `complete`).

**Example:**

```javascript
document.addEventListener("DOMContentLoaded", () => console.log("DOM ready!"));
```

---

### 18. How do you manipulate CSS styles with JavaScript?

**Answer:**

* Inline via `element.style` or dynamically with classes.

**Example:**

```javascript
let box = document.getElementById("box");
box.style.backgroundColor = "red";
box.classList.add("highlight");
```

---

### 19. What is the difference between `classList.add()` and `className`?

**Answer:**

* `classList.add()` adds/removes/toggles classes safely.
* `className` overrides all classes.

**Example:**

```javascript
el.classList.add("active");
el.className = "new-class"; // overwrites old
```

---

### 20. What is the Shadow DOM?

**Answer:**

* A way to encapsulate styles and markup inside web components, preventing conflicts.

**Example:**

```javascript
let host = document.createElement("div");
let shadow = host.attachShadow({ mode: "open" });
shadow.innerHTML = "<p>Inside Shadow DOM</p>";
document.body.appendChild(host);
```

## 6. 📌 JavaScript Interview Questions – ES6 and Beyond

---

### 1. What are the key features introduced in ES6?

**Answer:**
Some major ones:

* `let` and `const`
* Arrow functions
* Template literals
* Default parameters
* Destructuring assignment
* Rest/Spread operators
* Classes
* Modules (`import`/`export`)
* Promises

---

### 2. What is the difference between `var`, `let`, and `const`?

**Answer:**

* `var` → function-scoped, hoisted, can be re-declared.
* `let` → block-scoped, hoisted but not initialized (temporal dead zone).
* `const` → block-scoped, cannot be reassigned (but objects can be mutated).

**Example:**

```javascript
var x = 1; 
let y = 2; 
const z = 3;
```

---

### 3. What are arrow functions and how do they differ from normal functions?

**Answer:**

* Shorter syntax.
* Do **not** have their own `this`, they inherit from enclosing scope.
* Cannot be used as constructors.

**Example:**

```javascript
let add = (a, b) => a + b;
console.log(add(2, 3)); // 5
```

---

### 4. What are template literals in ES6?

**Answer:**

* Strings enclosed by backticks `` ` ``, support interpolation `${}` and multiline strings.

**Example:**

```javascript
let name = "John";
console.log(`Hello, ${name}!`);
```

---

### 5. What are default parameters in ES6 functions?

**Answer:**

* Allows setting default values when arguments are missing.

**Example:**

```javascript
function greet(name = "Guest") {
  console.log(`Hello, ${name}`);
}
greet(); // Hello, Guest
```

---

### 6. What is destructuring in ES6?

**Answer:**

* Unpacks values from arrays/objects into variables.

**Example:**

```javascript
let [a, b] = [1, 2];
let {name, age} = {name: "Alice", age: 25};
```

---

### 7. What is the difference between rest and spread operators?

**Answer:**

* **Rest (`...`)** → Collects values into an array.
* **Spread (`...`)** → Expands an array/object into elements.

**Example:**

```javascript
function sum(...nums) { return nums.reduce((a, b) => a+b, 0); }
console.log(sum(1, 2, 3)); // 6

let arr = [1, 2, 3];
console.log([...arr, 4]); // [1, 2, 3, 4]
```

---

### 8. How are ES6 classes different from constructor functions?

**Answer:**

* Syntactic sugar over prototypes.
* Supports inheritance via `extends` and `super`.

**Example:**

```javascript
class Person {
  constructor(name) { this.name = name; }
  greet() { console.log(`Hello ${this.name}`); }
}
class Student extends Person {
  study() { console.log(`${this.name} is studying`); }
}
```

---

### 9. What are ES6 modules and how do you use them?

**Answer:**

* ES6 supports `import` and `export` for modular code.

**Example:**
`math.js`

```javascript
export function add(a, b) { return a + b; }
```

`app.js`

```javascript
import { add } from './math.js';
console.log(add(2, 3));
```

---

### 10. **What is a promise chain in JavaScript?**

A **promise chain** is a sequence of `.then()` or `.catch()` methods chained together, allowing for a series of asynchronous operations to be executed in order. Each `.then()` receives the result of the previous promise.

* Represents eventual completion of async operations.
* States: `pending`, `fulfilled`, `rejected`.

Example:
```javascript
fetchData()
  .then(data => {
    console.log("Data received:", data);
    return processData(data);
  })
  .then(processedData => {
    console.log("Processed data:", processedData);
    return saveData(processedData);
  })
  .catch(error => {
    console.error("Error occurred:", error);
  });
```

---

### 11. What is async/await?

**Answer:**

* Syntactic sugar over Promises.
* Makes async code look synchronous.

**Example:**

```javascript
async function fetchData() {
  let res = await fetch("https://api.example.com/data");
  let data = await res.json();
  console.log(data);
}
```

---

### 12. What are Symbols in ES6?

**Answer:**

* A unique and immutable primitive type.
* Often used as object keys to avoid conflicts.

**Example:**

```javascript
let sym = Symbol("id");
let obj = { [sym]: 123 };
```

---

### 13. What is a Map in ES6?

**Answer:**

* A collection of key-value pairs (keys can be any type).

**Example:**

```javascript
let map = new Map();
map.set("a", 1);
map.set({x:1}, 2);
console.log(map.get("a"));
```

---

### 14. What is a Set in ES6?

**Answer:**

* A collection of unique values.

**Example:**

```javascript
let set = new Set([1, 2, 2, 3]);
console.log(set); // Set {1, 2, 3}
```

---

### 15. What are WeakMap and WeakSet?

**Answer:**

* Similar to Map/Set but keys must be objects.
* Do not prevent garbage collection.

**Example:**

```javascript
let wm = new WeakMap();
let obj = {};
wm.set(obj, "data");
```

---

### 16. What is object shorthand property in ES6?

**Answer:**

* If variable name = key name, you can omit key.

**Example:**

```javascript
let name = "Alice", age = 25;
let person = { name, age }; // { name: "Alice", age: 25 }
```

---

### 17. What are computed property names in ES6?

**Answer:**

* Allows dynamic keys inside object literals.

**Example:**

```javascript
let key = "id";
let obj = { [key]: 101 };
```

---

### 18. What is object destructuring with rest?

**Answer:**

* Collect remaining properties.

**Example:**

```javascript
let {a, ...rest} = {a: 1, b: 2, c: 3};
console.log(rest); // { b: 2, c: 3 }
```

---

### 19. What is optional chaining (`?.`) in JavaScript?

**Answer:**

* Prevents errors when accessing nested properties.

**Example:**

```javascript
let user = {};
console.log(user.profile?.name); // undefined, no error
```

---

### 20. What is the nullish coalescing operator (`??`)?

**Answer:**

* Returns the right-hand side only if left-hand side is `null` or `undefined`.

**Example:**

```javascript
let val = null ?? "default";
console.log(val); // "default"
```

---

### 21. What is the difference between `for...of` and `for...in`?

**Answer:**

* `for...in` → Iterates over keys (objects).
* `for...of` → Iterates over values (iterables like arrays, strings).

**Example:**

```javascript
for (let i in ["a", "b"]) console.log(i); // 0,1
for (let v of ["a", "b"]) console.log(v); // a,b
```

---

### 22. What are generators in ES6?

**Answer:**

* Functions that can pause & resume with `yield`.

**Example:**

```javascript
function* gen() {
  yield 1;
  yield 2;
}
let g = gen();
console.log(g.next().value); // 1
console.log(g.next().value); // 2
```

---

### 23. What are async generators?

**Answer:**

* Generators that yield Promises using `async function*`.

**Example:**

```javascript
async function* gen() {
  yield await Promise.resolve(1);
}
for await (let val of gen()) console.log(val);
```

---

### 24. What is the difference between `export default` and named exports?

**Answer:**

* **Default export:** one per file, imported without braces.
* **Named export:** multiple per file, imported with braces.

**Example:**
`math.js`

```javascript
export default function add(a, b) { return a+b; }
export function sub(a, b) { return a-b; }
```

`app.js`

```javascript
import add, { sub } from './math.js';
```

---

### 25. What are dynamic imports in ES2020?

**Answer:**

* `import()` returns a Promise, allows lazy loading.

**Example:**

```javascript
import("./math.js").then(module => {
  console.log(module.add(2, 3));
});
```

## 7. 📌 JavaScript Interview Questions – Error Handling & Debugging
## 🔹 Error Handling

---

### 1. What are the different types of errors in JavaScript?

**Answer:**

1. **Syntax Error** → Invalid code structure (caught during parsing).
2. **Reference Error** → Using an undeclared variable.
3. **Type Error** → Performing invalid operation on a type.
4. **Range Error** → Value outside the allowed range.
5. **URI Error** → Malformed URI functions (`decodeURI`, etc.).
6. **Eval Error** → Errors from `eval()` (rare).

**Example:**

```javascript
// ReferenceError
console.log(x); // x is not defined
```

---

### 2. How does `try...catch` work in JavaScript?

**Answer:**

* `try` contains code that may throw.
* `catch` handles the error.
* `finally` executes regardless.

**Example:**

```javascript
try {
  let result = 10 / x; // x not defined
} catch (err) {
  console.log("Caught:", err.message);
} finally {
  console.log("Always runs");
}
```

---

### 3. What is the difference between `throw` and `return`?

**Answer:**

* `return` → exits function normally.
* `throw` → raises an exception, must be caught.

**Example:**

```javascript
function divide(a, b) {
  if (b === 0) throw new Error("Division by zero!");
  return a / b;
}
```

---

### 4. How do you create custom errors?

**Answer:**
By extending the built-in `Error` class.

**Example:**

```javascript
class ValidationError extends Error {
  constructor(message) {
    super(message);
    this.name = "ValidationError";
  }
}
throw new ValidationError("Invalid input!");
```

---

### 5. What is error propagation in JavaScript?

**Answer:**
If an error is not caught in the current function, it propagates up the call stack until caught or program crashes.

---

### 6. How is asynchronous error handling different from synchronous?

**Answer:**

* **Synchronous**: handled with `try...catch`.
* **Asynchronous**: handled with `.catch()` or `try...catch` inside `async/await`.

**Example:**

```javascript
async function fetchData() {
  try {
    let res = await fetch("invalid-url");
  } catch (err) {
    console.log("Caught async:", err.message);
  }
}
```

---

### 7. What is the role of the `finally` block?

**Answer:**
Always runs, useful for cleanup (closing DB, clearing timers).

---

### 8. What is `window.onerror` used for?

**Answer:**

* A global error handler for uncaught errors in the browser.

**Example:**

```javascript
window.onerror = function(msg, url, line) {
  console.log(`Error: ${msg} at ${url}:${line}`);
};
```

---

### 9. What is `try...catch` limitation with asynchronous code?

**Answer:**
`try...catch` cannot catch errors inside callbacks unless wrapped.

**Example:**

```javascript
try {
  setTimeout(() => { throw new Error("Async error"); }, 100);
} catch (e) {
  console.log("Will not catch here");
}
```

---

### 10. What is stack trace in JavaScript?

**Answer:**

* A report showing the active call stack at error time.
* Helps trace where error originated.

### 🔹 Debugging

---

### 11. How do you debug JavaScript code?

**Answer:**

* `console.log()`
* Browser DevTools (breakpoints, watch variables, step-through).
* `debugger` keyword.

**Example:**

```javascript
let x = 5;
debugger; // pauses execution in DevTools
console.log(x);
```

---

### 12. What is the `debugger` statement?

**Answer:**
Pauses execution if DevTools are open.

---

### 13. What tools do you use for debugging Node.js applications?

**Answer:**

* `console.log`
* Node.js Inspector (`node --inspect`)
* Chrome DevTools
* VS Code Debugger

---

### 14. How do you debug memory leaks in JavaScript?

**Answer:**

* Use Chrome DevTools → Memory tab → Heap snapshots.
* Identify uncollected references (closures, event listeners).
* Use WeakMap/WeakSet for temporary references.

---

### 15. What are common causes of memory leaks in JS?

**Answer:**

* Unremoved event listeners.
* Global variables.
* Closures holding references.
* Detached DOM nodes.

---

### 16. What is source mapping and why is it important in debugging?

**Answer:**

* Maps minified/bundled JS back to original source.
* Helps debug in DevTools with readable code.

---

### 17. How do you handle unhandled Promise rejections?

**Answer:**

* Use `.catch()`
* Use `process.on("unhandledRejection")` in Node.js.

---

### 18. What is the difference between `console.error`, `console.warn`, and `console.log`?

**Answer:**

* `console.log` → general info.
* `console.warn` → warning (yellow in DevTools).
* `console.error` → error (red in DevTools, stack trace).

---

### 19. What is the role of `try...catch` in async/await debugging?

**Answer:**
Ensures errors in `await` calls don’t crash the program.

---

### 20. How do you test error handling in JavaScript?

**Answer:**

* Use unit testing frameworks (Jest, Mocha).
* Mock errors and verify code handles them correctly.

**Example (Jest):**

```javascript
test("division by zero throws", () => {
  expect(() => divide(5, 0)).toThrow("Division by zero!");
});
```

## 8. 📌 JavaScript Interview Questions – Modules & Tooling
### 🔹 Modules

---

### 1. What are JavaScript modules and why are they useful?

**Answer:**

* A **module** is a reusable block of code encapsulated in its own scope.
* Benefits: maintainability, reusability, avoids global scope pollution, supports lazy loading.

---

### 2. What is the difference between CommonJS and ES6 modules?

**Answer:**

* **CommonJS (CJS)** → used in Node.js (`require`, `module.exports`).
* **ES6 Modules (ESM)** → standard in modern JS (`import`, `export`).

**Example:**

```javascript
// CommonJS
const math = require("./math");
module.exports = add;

// ES6
import { add } from "./math.js";
export default add;
```

---

### 3. What is the difference between named export and default export?

**Answer:**

* **Named export**: Multiple exports per file, imported with `{}`.
* **Default export**: One per file, imported without `{}`.

**Example:**

```javascript
// utils.js
export function sum(a, b) { return a+b; }
export default function greet() { console.log("Hello"); }

// app.js
import greet, { sum } from "./utils.js";
```

---

### 4. What are dynamic imports in ES2020?

**Answer:**

* `import()` function loads modules **lazily** and returns a Promise.

**Example:**

```javascript
import("./math.js").then(module => {
  console.log(module.sum(2, 3));
});
```

---

### 5. What are tree-shaking and dead code elimination?

**Answer:**

* **Tree-shaking**: Removing unused exports during bundling.
* Requires **static imports** (not dynamic).

---

### 6. What are IIFEs and how do they relate to modules?

**Answer:**

* **IIFE**: Immediately Invoked Function Expression.
* Before ES6 modules, IIFEs were used to create private scope.

**Example:**

```javascript
(function() {
  let secret = 42;
  console.log("Module-like scope");
})();
```

### 🔹 Tooling

---

### 7. What is a bundler and why is it needed?

**Answer:**

* A **bundler** (Webpack, Rollup, Parcel, Vite) combines multiple JS/CSS files into optimized bundles.
* Needed for performance, dependency resolution, tree-shaking, asset management.

---

### 8. What is the difference between Webpack, Rollup, and Parcel?

**Answer:**

* **Webpack** → most popular, highly configurable, good for complex apps.
* **Rollup** → optimized for libraries (smaller, tree-shaking).
* **Parcel/Vite** → zero-config, fast HMR (Hot Module Replacement).

---

### 9. What is transpilation and why is Babel used?

**Answer:**

* **Transpilation**: Converting modern JS → backward-compatible JS.
* **Babel**: Popular transpiler for ES6+ to ES5.

**Example (Babel config):**

```json
{
  "presets": ["@babel/preset-env"]
}
```

---

### 10. What are polyfills in JavaScript?

**Answer:**

* Code that **adds support for newer features** in older environments.
* Example: `core-js`, `polyfill.io`.

**Example:**

```javascript
if (!Array.prototype.includes) {
  Array.prototype.includes = function(val) {
    return this.indexOf(val) !== -1;
  };
}
```

---

### 11. What is the difference between transpilation and polyfills?

**Answer:**

* **Transpilation**: Converts syntax (`let`, `class`, arrow functions\`).
* **Polyfills**: Implements missing APIs (`Promise`, `fetch`, `includes`).

---

### 12. What is npm and yarn, and how do they differ?

**Answer:**

* **npm**: Node.js default package manager.
* **Yarn**: Alternative package manager, faster installs, better caching.

---

### 13. What are devDependencies vs dependencies in package.json?

**Answer:**

* **dependencies** → Required at runtime.
* **devDependencies** → Required only during development (build tools, test libraries).

**Example:**

```json
"dependencies": { "react": "^18.0.0" },
"devDependencies": { "jest": "^29.0.0" }
```

---

### 14. What are peerDependencies in npm?

**Answer:**

* Specify that your package requires a dependency that should be installed in the host project.
* Common in plugins/libraries.

**Example:**

```json
"peerDependencies": { "react": ">=17" }
```

---

### 15. What is a package lock file and why is it important?

**Answer:**

* `package-lock.json` (npm) or `yarn.lock` ensures **consistent dependency versions** across environments.

---

### 16. What is ESLint and why is it used?

**Answer:**

* A **linter** for enforcing coding style & finding errors.

**Example config:**

```json
{
  "extends": "eslint:recommended",
  "rules": { "semi": ["error", "always"] }
}
```

---

### 17. What is Prettier and how does it differ from ESLint?

**Answer:**

* **Prettier**: Auto-formats code.
* **ESLint**: Finds problems and enforces coding rules.
* Often used together.

---

### 18. What is Hot Module Replacement (HMR)?

**Answer:**

* Feature in Webpack/Vite where updated modules replace old ones **without full page reload**.

---

### 19. What is the difference between development and production builds?

**Answer:**

* **Dev Build** → Faster builds, includes debugging info, not optimized.
* **Prod Build** → Minified, tree-shaken, optimized for performance.

---

### 20. What are source maps and why are they useful?

**Answer:**

* Maps minified code back to original source.
* Helps debugging in DevTools.

**Example:**
`//# sourceMappingURL=app.js.map`

---

### 21. What is code splitting and why is it important?

**Answer:**

* Breaking bundle into smaller chunks for lazy loading.
* Improves performance.

**Example (Webpack):**

```javascript
import("./math.js").then(module => console.log(module.add(2, 3)));
```

---

### 22. What is a monorepo and what tools are used?

**Answer:**

* **Monorepo**: Single repo for multiple packages.
* Tools: **Lerna**, **Nx**, **Turborepo**.

---

### 23. What is the difference between npm scripts and task runners like Gulp/Grunt?

**Answer:**

* **npm scripts**: Run simple commands (`npm run build`).
* **Gulp/Grunt**: Task runners for automation (file watch, minification).

---

### 24. What are environment variables in JS projects and how do you manage them?

**Answer:**

* Store API keys, secrets, configs.
* Managed with `.env` files + `dotenv` package.

**Example:**

```bash
API_KEY=12345
```

```javascript
console.log(process.env.API_KEY);
```

---

### 25. What is the difference between `require` and `import`?

**Answer:**

* `require` → CommonJS, synchronous, runtime.
* `import` → ES6, static, supports tree-shaking.


## 10. 📌 JavaScript Interview Questions – ModuPerformance Optimization
---
### 1. What are some common techniques to optimize JavaScript performance?

**Answer:**

* Minify and bundle JavaScript files.
* Use lazy loading for scripts and images.
* Debounce/throttle expensive events like `scroll` or `resize`.
* Use efficient loops (`for` vs `forEach`).
* Avoid memory leaks (remove unused event listeners, clear intervals).
* Cache DOM queries instead of querying repeatedly.

✅ Example:

```javascript
// Inefficient
document.querySelector("#btn").addEventListener("click", () => {
  document.querySelector("#output").innerText = "Clicked!";
});

// Optimized (cache DOM element)
const btn = document.querySelector("#btn");
const output = document.querySelector("#output");
btn.addEventListener("click", () => {
  output.innerText = "Clicked!";
});
```

---

### 2. What is debouncing and throttling?

**Answer:**

* **Debouncing** → Ensures a function runs only after a certain time has passed since the last event.
* **Throttling** → Ensures a function runs at most once in a specified time interval.

✅ Example:

```javascript
// Debounce
function debounce(fn, delay) {
  let timer;
  return function(...args) {
    clearTimeout(timer);
    timer = setTimeout(() => fn.apply(this, args), delay);
  };
}

// Throttle
function throttle(fn, interval) {
  let lastTime = 0;
  return function(...args) {
    const now = Date.now();
    if (now - lastTime >= interval) {
      fn.apply(this, args);
      lastTime = now;
    }
  };
}
```

---

### 3. How can you optimize DOM manipulation?

**Answer:**

* Minimize reflows and repaints.
* Use `document.createDocumentFragment()` for batch updates.
* Use `requestAnimationFrame()` for smooth animations.
* Cache references to DOM elements.

✅ Example:

```javascript
// Inefficient: multiple reflows
for (let i = 0; i < 1000; i++) {
  const div = document.createElement("div");
  div.textContent = i;
  document.body.appendChild(div);
}

// Optimized with DocumentFragment
const fragment = document.createDocumentFragment();
for (let i = 0; i < 1000; i++) {
  const div = document.createElement("div");
  div.textContent = i;
  fragment.appendChild(div);
}
document.body.appendChild(fragment);
```

---

### 4. What are memory leaks in JavaScript, and how do you prevent them?

**Answer:**
Memory leaks happen when allocated memory is not released. Causes:

* Global variables not cleared.
* Forgotten `setInterval` or event listeners.
* DOM elements kept in memory after removal.

✅ Prevention:

```javascript
let timer = setInterval(() => console.log("Running"), 1000);

// Prevent memory leak
clearInterval(timer);

const btn = document.querySelector("#btn");
function handleClick() { console.log("clicked"); }
btn.addEventListener("click", handleClick);

// Later cleanup
btn.removeEventListener("click", handleClick);
```

---

### 5. How does lazy loading improve performance?

**Answer:**
Lazy loading defers loading of resources (like images or scripts) until they are needed, reducing initial load time.

✅ Example:

```html
<img src="placeholder.jpg" data-src="large-image.jpg" loading="lazy" />
```

---

### 6. What is the difference between synchronous and asynchronous loading of scripts?

**Answer:**

* **Normal `<script>`** blocks HTML parsing.
* **`async`** loads script in parallel and executes immediately.
* **`defer`** loads script in parallel but executes after HTML parsing.

✅ Example:

```html
<script src="file.js" async></script>   <!-- Executes ASAP -->
<script src="file.js" defer></script>   <!-- Executes after HTML parse -->
```

---

### 7. How can you optimize loops in JavaScript?

**Answer:**

* Cache array length.
* Use efficient iteration (`for` instead of `forEach` in performance-sensitive areas).
* Avoid nested loops when possible.

✅ Example:

```javascript
// Inefficient
for (let i = 0; i < arr.length; i++) {
  console.log(arr[i]);
}

// Optimized
for (let i = 0, len = arr.length; i < len; i++) {
  console.log(arr[i]);
}
```

---

### 8. What is tree shaking?

**Answer:**
Tree shaking is the process of removing unused code during bundling (common in ES6 module-based apps). Tools like Webpack and Rollup implement it.

✅ Example:

```javascript
// utils.js
export function used() {}
export function unused() {}

// index.js
import { used } from './utils.js';
used(); // "unused" will be removed in bundling
```

---

### 9. How can you optimize network performance in JS apps?

**Answer:**

* Use **code splitting** to load only needed JS.
* Use **CDN caching**.
* Use **gzip/brotli compression**.
* Prefetch & preconnect critical resources.

✅ Example:

```html
<link rel="preload" href="styles.css" as="style" />
<link rel="dns-prefetch" href="//example.com" />
```

---

### 10. What are Web Workers, and how do they improve performance?

**Answer:**
Web Workers allow running scripts in background threads, preventing blocking of the main UI thread.

✅ Example:

```javascript
// worker.js
onmessage = function(e) {
  postMessage(e.data * 2);
};

// main.js
const worker = new Worker("worker.js");
worker.postMessage(10);
worker.onmessage = function(e) {
  console.log("Result:", e.data); // 20
};
```

## 11. 📌 JavaScript Interview Questions – Advanced Topics & Best Practices
---

### 1. What is functional programming in JavaScript?

**Answer:**
Functional programming (FP) is a paradigm where functions are **first-class citizens** and emphasizes immutability, pure functions, and avoiding side effects.

✅ Example:

```javascript
// Pure function (no side effects, same input -> same output)
function add(a, b) {
  return a + b;
}

// Higher-order function
function applyOperation(arr, fn) {
  return arr.map(fn);
}

console.log(applyOperation([1, 2, 3], x => x * 2)); // [2, 4, 6]
```

---

### 2. What are design patterns in JavaScript?

**Answer:**
Design patterns are reusable solutions to common programming problems. Some common JS patterns:

* **Singleton**: Ensures only one instance exists.
* **Factory**: Creates objects without exposing creation logic.
* **Observer**: Notifies dependents when state changes.

✅ Example (Singleton):

```javascript
const Singleton = (function() {
  let instance;
  function createInstance() {
    return { id: Date.now() };
  }
  return {
    getInstance: function() {
      if (!instance) instance = createInstance();
      return instance;
    }
  };
})();
console.log(Singleton.getInstance() === Singleton.getInstance()); // true
```

---

### 3. How do you handle security best practices in JavaScript?

**Answer:**

* Avoid `eval()` (can run malicious code).
* Escape user input (to prevent XSS).
* Use HTTPS.
* Implement CSP (Content Security Policy).
* Sanitize data before rendering.

✅ Example (preventing XSS):

```javascript
// Unsafe
element.innerHTML = userInput;

// Safe
element.textContent = userInput;
```

---

### 4. What is immutability, and how do you enforce it in JS?

**Answer:**
Immutability means once data is created, it cannot be changed. This prevents bugs and improves predictability.

✅ Example:

```javascript
const obj = { name: "John" };
const frozen = Object.freeze(obj);

frozen.name = "Mike"; // ignored
console.log(frozen.name); // John
```

---

### 5. What are best practices for error handling in production JavaScript apps?

**Answer:**

* Use `try/catch` for synchronous code.
* Use `.catch()` for Promises.
* Use `async/await` with `try/catch`.
* Implement a global error handler.
* Log errors (to monitoring tools like Sentry).

✅ Example:

```javascript
window.onerror = function(message, source, lineno, colno, error) {
  console.error("Global error caught:", message);
};
```

---

### 6. What is the difference between composition and inheritance?

**Answer:**

* **Inheritance** → “is-a” relationship (class extends another).
* **Composition** → “has-a” relationship (combine objects/functions).

✅ Example (Composition preferred for flexibility):

```javascript
function canWalk(obj) {
  obj.walk = () => console.log("Walking...");
  return obj;
}
function canEat(obj) {
  obj.eat = () => console.log("Eating...");
  return obj;
}

const person = canWalk(canEat({}));
person.walk(); // Walking...
person.eat();  // Eating...
```

---

### 7. How do you manage large JavaScript codebases?

**Answer:**

* Modularize using **ES modules**.
* Use **linting tools** (ESLint).
* Follow consistent naming conventions.
* Write **unit tests**.
* Use **TypeScript** for type safety.

---

### 8. What are best practices for asynchronous code?

**Answer:**

* Prefer **Promises/async-await** over callbacks.
* Handle rejections properly.
* Use `Promise.all` for parallel tasks.
* Use `Promise.race` when only first result matters.

✅ Example:

```javascript
async function fetchData() {
  try {
    const [users, posts] = await Promise.all([
      fetch("/users").then(r => r.json()),
      fetch("/posts").then(r => r.json())
    ]);
    console.log(users, posts);
  } catch (err) {
    console.error("Error fetching data", err);
  }
}
```

---

### 9. What is defensive programming, and why is it important?

**Answer:**
Defensive programming anticipates possible errors and writes code to handle them gracefully.
✅ Example:

```javascript
function divide(a, b) {
  if (b === 0) throw new Error("Division by zero");
  return a / b;
}
console.log(divide(10, 2)); // 5
```

---

### 10. How do you ensure scalability in JavaScript applications?

**Answer:**

* Optimize algorithms & data structures.
* Implement caching.
* Break monoliths into microservices.
* Use message queues for heavy tasks.
* Apply load balancing on backend.

---

### 11. What is memoization, and how does it optimize performance?

**Answer:**
Memoization caches results of function calls to avoid recalculating expensive operations.

✅ Example:

```javascript
function memoize(fn) {
  const cache = {};
  return function(x) {
    if (cache[x]) return cache[x];
    const result = fn(x);
    cache[x] = result;
    return result;
  };
}
const factorial = memoize(function(n) {
  if (n <= 1) return 1;
  return n * factorial(n - 1);
});
console.log(factorial(5)); // 120
```

---

### 12. What are some common anti-patterns in JavaScript?

**Answer:**

* Polluting the global namespace.
* Using `eval()`.
* Callback hell.
* Ignoring error handling in Promises.
* Deeply nested `if`/`else`.

---

### 13. How do you prevent race conditions in async code?

**Answer:**
Race conditions occur when multiple async tasks depend on shared state.
✅ Mitigation:

* Use **locks/mutexes**.
* Use `await` properly.
* Avoid global mutable state.

---

### 14. What is code splitting, and why is it important?

**Answer:**
Code splitting divides code into smaller bundles to reduce initial load time.
✅ Example (Webpack):

```javascript
import("./math.js").then(math => {
  console.log(math.add(2, 3));
});
```

---

### 15. How do you ensure code readability and maintainability in JS?

**Answer:**

* Follow **naming conventions**.
* Write **small functions** (single responsibility).
* Use **comments** wisely.
* Follow **linting rules**.
* Use **prettier** or similar formatters.

---
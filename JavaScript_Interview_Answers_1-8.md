# JavaScript Interview Questions - Answers (Questions 1-8)

## 1. What are the different data types in JavaScript?

JavaScript has **8 data types** - 7 primitives and 1 non-primitive. The primitives are: **string, number, boolean, undefined, null, symbol, and bigint**. The non-primitive is **object**, which includes arrays, functions, and objects themselves.

```javascript
// Primitives
let name = "John";           // string
let age = 25;               // number
let isActive = true;        // boolean
let data;                   // undefined
let empty = null;           // null
let id = Symbol('id');      // symbol
let bigNum = 123n;          // bigint

// Non-primitive
let person = { name: "John" }; // object
let numbers = [1, 2, 3];      // array (object)
let greet = function() {};     // function (object)
```

## 2. What is the difference between `var`, `let`, and `const`?

**var** is function-scoped and gets hoisted. **let** and **const** are block-scoped. **const** can't be reassigned, but objects and arrays declared with const can still be modified.

```javascript
// var - function scoped, hoisted
function example() {
    if (true) {
        var x = 1;
    }
    console.log(x); // 1 - accessible outside block
}

// let - block scoped
function example2() {
    if (true) {
        let y = 1;
    }
    // console.log(y); // Error - not accessible
}

// const - block scoped, can't reassign
const name = "John";
// name = "Jane"; // Error

const person = { name: "John" };
person.name = "Jane"; // OK - modifying object property
```

## 3. What is a closure in JavaScript?

A **closure** is when an inner function has access to variables from its outer function, even after the outer function has finished executing. It's like the inner function "remembers" the outer function's variables.

```javascript
function outerFunction(x) {
    // Outer variable
    return function innerFunction(y) {
        // Inner function has access to x
        return x + y;
    };
}

const addFive = outerFunction(5);
console.log(addFive(3)); // 8

// Practical example - counter
function createCounter() {
    let count = 0;
    return function() {
        return ++count;
    };
}

const counter = createCounter();
console.log(counter()); // 1
console.log(counter()); // 2
```

## 4. What is the difference between `==` and `===` in JavaScript?

**Double equals (==)** does type coercion - it converts types before comparing. **Triple equals (===)** is strict equality - it checks both value and type without any conversion.

```javascript
// == (loose equality) - type coercion
console.log(5 == "5");     // true - string converted to number
console.log(true == 1);    // true - boolean converted to number
console.log(null == undefined); // true - special case

// === (strict equality) - no type coercion
console.log(5 === "5");    // false - different types
console.log(true === 1);   // false - different types
console.log(null === undefined); // false - different types

// Best practice: always use ===
const age = 25;
if (age === 25) {
    console.log("Exactly 25");
}
```

## 5. Explain the concept of "truthy" and "falsy" values in JavaScript.

**Falsy values** are values that evaluate to false in boolean context. There are only **6 falsy values**: false, 0, -0, 0n, "", null, undefined, and NaN. Everything else is **truthy**.

```javascript
// Falsy values (only 6)
console.log(Boolean(false));     // false
console.log(Boolean(0));         // false
console.log(Boolean(""));        // false
console.log(Boolean(null));      // false
console.log(Boolean(undefined)); // false
console.log(Boolean(NaN));       // false

// Truthy values (everything else)
console.log(Boolean("hello"));   // true
console.log(Boolean(1));         // true
console.log(Boolean([]));        // true - empty array is truthy!
console.log(Boolean({}));        // true - empty object is truthy!

// Practical use in conditions
const name = "";
if (name) {
    console.log("Name exists"); // Won't run
}

const users = [];
if (users.length) {
    console.log("Has users"); // Won't run
}
```

## 6. What are the JavaScript data structures, and when would you use them?

JavaScript has **built-in data structures**: Arrays for ordered lists, Objects for key-value pairs, Maps for any-type keys, Sets for unique values, and WeakMap/WeakSet for memory-efficient collections.

```javascript
// Array - ordered collection
const fruits = ["apple", "banana", "orange"];
fruits.push("grape");

// Object - key-value pairs (string keys)
const person = {
    name: "John",
    age: 30,
    city: "New York"
};

// Map - key-value pairs (any type keys)
const userRoles = new Map();
userRoles.set(1, "admin");
userRoles.set("guest", "viewer");

// Set - unique values only
const uniqueNumbers = new Set([1, 2, 2, 3, 3, 4]);
console.log(uniqueNumbers); // Set {1, 2, 3, 4}

// Use cases:
// Array: lists, queues, stacks
// Object: records, configurations
// Map: caching, lookups with non-string keys
// Set: removing duplicates, membership testing
```

## 7. What is the `undefined` value in JavaScript?

**undefined** means a variable has been declared but not assigned a value, or a function doesn't return anything, or accessing a non-existent object property. It's JavaScript's way of saying "no value here yet."

```javascript
// Variable declared but not assigned
let name;
console.log(name); // undefined

// Function with no return
function greet() {
    console.log("Hello");
}
console.log(greet()); // undefined

// Non-existent object property
const person = { name: "John" };
console.log(person.age); // undefined

// Array element that doesn't exist
const numbers = [1, 2, 3];
console.log(numbers[10]); // undefined

// Function parameter not provided
function add(a, b) {
    console.log(b); // undefined if not provided
}
add(5); // b is undefined
```

## 8. What is the `null` value in JavaScript? How is it different from `undefined`?

**null** is an intentional absence of value - you explicitly set it. **undefined** means "no value assigned yet." null is what you use when you want to say "this should be empty," while undefined is what JavaScript gives you automatically.

```javascript
// null - intentional empty value
let data = null; // explicitly set to empty
let user = null; // will be filled later

// undefined - automatic "no value"
let name; // undefined by default
console.log(name); // undefined

// Key differences
console.log(typeof null);      // "object" (JavaScript quirk)
console.log(typeof undefined); // "undefined"

console.log(null == undefined);  // true (loose equality)
console.log(null === undefined); // false (strict equality)

// Practical usage
function findUser(id) {
    if (id < 0) {
        return null; // intentionally no user
    }
    // return undefined; // implicitly no user found
}

// Checking for both
const value = getData();
if (value != null) { // checks both null and undefined
    console.log("Has value");
}
```
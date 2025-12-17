# JavaScript Interview Questions - Answers (Questions 1-8)

## 1. What are the different data types in JavaScript?

• **Primitive types**: Number, String, Boolean, Undefined, Null, Symbol, BigInt
• **Non-primitive**: Object (includes arrays, functions, dates)
• JavaScript is dynamically typed - variables can hold any type

```javascript
let num = 42;           // Number
let str = "Hello";      // String
let bool = true;        // Boolean
let obj = {name: "John"}; // Object
let arr = [1, 2, 3];    // Array (object type)
```

## 2. What is the difference between `var`, `let`, and `const`?

• **var**: Function-scoped, hoisted, can be redeclared
• **let**: Block-scoped, not hoisted, cannot be redeclared
• **const**: Block-scoped, must be initialized, cannot be reassigned

```javascript
var x = 1;    // Function scoped
let y = 2;    // Block scoped
const z = 3;  // Block scoped, immutable

if (true) {
  var x = 10;  // Same variable
  let y = 20;  // Different variable
  // const z = 30; // Would create new variable
}
```

## 3. What is a closure in JavaScript?

• **Definition**: Function that has access to outer function's variables even after outer function returns
• **Use cases**: Data privacy, function factories, callbacks
• **Key benefit**: Creates private variables and maintains state

```javascript
function outerFunction(x) {
  return function innerFunction(y) {
    return x + y; // Access to 'x' from outer scope
  };
}

const addFive = outerFunction(5);
console.log(addFive(3)); // 8
```

## 4. What is the difference between `==` and `===` in JavaScript?

• **== (loose equality)**: Compares values with type coercion
• **=== (strict equality)**: Compares values and types without coercion
• **Best practice**: Always use === to avoid unexpected behavior

```javascript
console.log(5 == "5");   // true (type coercion)
console.log(5 === "5");  // false (different types)
console.log(null == undefined);  // true
console.log(null === undefined); // false
```

## 5. Explain the concept of "truthy" and "falsy" values in JavaScript.

• **Falsy values**: false, 0, "", null, undefined, NaN
• **Truthy values**: Everything else (including empty arrays/objects)
• **Usage**: Conditional statements, logical operators

```javascript
// Falsy values
if (!false && !0 && !"" && !null && !undefined && !NaN) {
  console.log("All falsy values");
}

// Truthy values
if ([] && {} && "0" && -1) {
  console.log("All truthy values");
}
```

## 6. What are the JavaScript data structures, and when would you use them?

• **Array**: Ordered list, use for sequences and iterations
• **Object**: Key-value pairs, use for structured data
• **Map**: Key-value with any key type, better performance for frequent additions/deletions
• **Set**: Unique values collection, use for removing duplicates

```javascript
let arr = [1, 2, 3];              // Array
let obj = {name: "John", age: 30}; // Object
let map = new Map([["key", "value"]]); // Map
let set = new Set([1, 2, 2, 3]);  // Set: {1, 2, 3}
```

## 7. What is the `undefined` value in JavaScript?

• **Definition**: Variable declared but not assigned a value
• **Automatic assignment**: JavaScript assigns undefined by default
• **Common scenarios**: Uninitialized variables, missing function parameters, non-existent object properties

```javascript
let x;                    // undefined
console.log(x);          // undefined

function test(param) {
  console.log(param);    // undefined if not passed
}

let obj = {};
console.log(obj.name);   // undefined
```

## 8. What is the `null` value in JavaScript? How is it different from `undefined`?

• **null**: Intentional absence of value, explicitly assigned
• **undefined**: Variable exists but no value assigned
• **Type difference**: null is object type, undefined is undefined type
• **Usage**: null for intentional "no value", undefined for uninitialized

```javascript
let intentionallyEmpty = null;     // Explicitly no value
let notAssigned;                   // undefined

console.log(typeof null);          // "object" (JavaScript quirk)
console.log(typeof undefined);     // "undefined"

console.log(null == undefined);    // true
console.log(null === undefined);   // false
```

## 9. What are the different ways to create objects in JavaScript?

There are several ways to create objects in JavaScript. The most common methods are object literals, constructor functions, Object.create(), and ES6 classes.

**Object Literal:**
```javascript
const person = {
  name: "John",
  age: 30
};
```

**Constructor Function:**
```javascript
function Person(name, age) {
  this.name = name;
  this.age = age;
}
const person = new Person("John", 30);
```

**Object.create():**
```javascript
const personProto = { greet() { return "Hello"; } };
const person = Object.create(personProto);
person.name = "John";
```

**ES6 Class:**
```javascript
class Person {
  constructor(name, age) {
    this.name = name;
    this.age = age;
  }
}
const person = new Person("John", 30);
```

---

## 10. What is hoisting in JavaScript?

Hoisting is JavaScript's behavior of moving variable and function declarations to the top of their scope during compilation. Variables declared with `var` are hoisted but initialized as `undefined`, while `let` and `const` are hoisted but not accessible until declared.

**Function Hoisting:**
```javascript
console.log(sayHello()); // "Hello!" - works due to hoisting

function sayHello() {
  return "Hello!";
}
```

**Variable Hoisting:**
```javascript
console.log(x); // undefined (not error)
var x = 5;

// This is how JavaScript interprets it:
// var x;
// console.log(x);
// x = 5;
```

**Let/Const Hoisting:**
```javascript
console.log(y); // ReferenceError
let y = 10;
```

---

## 11. What is a pure function in JavaScript? Can you give an example?

A pure function is a function that always returns the same output for the same input and has no side effects. It doesn't modify external variables or perform I/O operations.

**Pure Function Example:**
```javascript
// Pure function - same input, same output, no side effects
function add(a, b) {
  return a + b;
}

console.log(add(2, 3)); // Always returns 5
```

**Impure Function Example:**
```javascript
let counter = 0;

// Impure - modifies external variable
function increment() {
  counter++;
  return counter;
}
```

**Pure Array Function:**
```javascript
// Pure - doesn't modify original array
function doubleArray(arr) {
  return arr.map(x => x * 2);
}

const nums = [1, 2, 3];
console.log(doubleArray(nums)); // [2, 4, 6]
console.log(nums); // [1, 2, 3] - unchanged
```

---

## 12. What are higher-order functions in JavaScript?

Higher-order functions are functions that either take other functions as arguments or return functions as results. They're fundamental to functional programming and are commonly used with arrays.

**Function that takes another function:**
```javascript
function processArray(arr, callback) {
  return arr.map(callback);
}

const numbers = [1, 2, 3];
const doubled = processArray(numbers, x => x * 2);
console.log(doubled); // [2, 4, 6]
```

**Function that returns a function:**
```javascript
function createMultiplier(factor) {
  return function(number) {
    return number * factor;
  };
}

const double = createMultiplier(2);
console.log(double(5)); // 10
```

**Built-in higher-order functions:**
```javascript
const numbers = [1, 2, 3, 4, 5];

const evens = numbers.filter(n => n % 2 === 0); // [2, 4]
const sum = numbers.reduce((acc, n) => acc + n, 0); // 15
```

---

## 13. What is a callback function in JavaScript?

A callback function is a function passed as an argument to another function and executed at a specific time. It's a way to ensure certain code runs after an operation completes.

**Simple Callback:**
```javascript
function greet(name, callback) {
  console.log("Hello " + name);
  callback();
}

function afterGreeting() {
  console.log("Nice to meet you!");
}

greet("John", afterGreeting);
// Output: "Hello John"
//         "Nice to meet you!"
```

**Asynchronous Callback:**
```javascript
function fetchData(callback) {
  setTimeout(() => {
    const data = { id: 1, name: "User" };
    callback(data);
  }, 1000);
}

fetchData(function(result) {
  console.log("Received:", result);
});
```

**Array Method Callbacks:**
```javascript
const numbers = [1, 2, 3, 4];

numbers.forEach(function(num) {
  console.log(num * 2);
});
// Output: 2, 4, 6, 8
```

---

## 14. What are closures in JavaScript, and why are they important?

Closures occur when an inner function has access to variables from its outer function's scope, even after the outer function has finished executing. They're important for data privacy, creating modules, and maintaining state.

**Basic Closure:**
```javascript
function outerFunction(x) {
  return function innerFunction(y) {
    return x + y; // Inner function accesses outer variable
  };
}

const addFive = outerFunction(5);
console.log(addFive(3)); // 8
```

**Data Privacy with Closures:**
```javascript
function createCounter() {
  let count = 0; // Private variable
  
  return {
    increment: () => ++count,
    decrement: () => --count,
    getCount: () => count
  };
}

const counter = createCounter();
console.log(counter.increment()); // 1
console.log(counter.getCount()); // 1
// count is not directly accessible
```

**Module Pattern:**
```javascript
const calculator = (function() {
  let result = 0;
  
  return {
    add: (x) => result += x,
    multiply: (x) => result *= x,
    getResult: () => result
  };
})();
```

---

## 15. How does the `this` keyword work in JavaScript?

The `this` keyword refers to the object that is currently executing the function. Its value depends on how the function is called, not where it's defined.

**Object Method:**
```javascript
const person = {
  name: "John",
  greet() {
    return `Hello, I'm ${this.name}`;
  }
};

console.log(person.greet()); // "Hello, I'm John"
```

**Arrow Functions (no own `this`):**
```javascript
const obj = {
  name: "John",
  regularFunction() {
    return this.name; // "John"
  },
  arrowFunction: () => {
    return this.name; // undefined (inherits from global)
  }
};
```

**Explicit Binding:**
```javascript
function introduce() {
  return `Hi, I'm ${this.name}`;
}

const person1 = { name: "Alice" };
const person2 = { name: "Bob" };

console.log(introduce.call(person1)); // "Hi, I'm Alice"
console.log(introduce.apply(person2)); // "Hi, I'm Bob"

const boundIntroduce = introduce.bind(person1);
console.log(boundIntroduce()); // "Hi, I'm Alice"
```

---

## 16. What is the difference between synchronous and asynchronous code in JavaScript?

Synchronous code executes line by line, blocking subsequent code until the current operation completes. Asynchronous code allows other operations to continue while waiting for long-running tasks to finish.

**Synchronous Code:**
```javascript
console.log("Start");

function slowOperation() {
  // Simulating slow operation
  for(let i = 0; i < 1000000000; i++) {}
  return "Done";
}

console.log(slowOperation()); // Blocks execution
console.log("End");
// Output: Start, Done, End (in order, but with delay)
```

**Asynchronous Code:**
```javascript
console.log("Start");

setTimeout(() => {
  console.log("Async operation complete");
}, 1000);

console.log("End");
// Output: Start, End, Async operation complete
```

**Promises (Asynchronous):**
```javascript
console.log("Start");

fetch('/api/data')
  .then(response => response.json())
  .then(data => console.log("Data received:", data))
  .catch(error => console.log("Error:", error));

console.log("End");
// "Start" and "End" execute immediately
// "Data received" executes when promise resolves
```

**Async/Await:**
```javascript
async function fetchData() {
  console.log("Start");
  
  try {
    const response = await fetch('/api/data');
    const data = await response.json();
    console.log("Data:", data);
  } catch (error) {
    console.log("Error:", error);
  }
  
  console.log("End");
}
```

---

## Summary

These fundamental JavaScript concepts form the backbone of modern web development:

- **Object Creation**: Multiple approaches for different use cases
- **Hoisting**: Understanding variable and function declaration behavior
- **Pure Functions**: Predictable, testable code without side effects
- **Higher-Order Functions**: Powerful abstraction for functional programming
- **Callbacks**: Foundation for asynchronous programming
- **Closures**: Data privacy and state management
- **`this` Keyword**: Context-dependent object reference
- **Sync vs Async**: Managing blocking vs non-blocking operations

Mastering these concepts enables you to write more efficient, maintainable, and scalable JavaScript applications.

## Question 17: What is the purpose of the `call()`, `apply()` and `bind()` methods in JavaScript?

**Answer:**
These methods let you control what `this` refers to in a function. `call()` invokes the function immediately with individual arguments, `apply()` does the same but takes an array of arguments, and `bind()` returns a new function with `this` permanently set.

```javascript
const person = { name: 'John' };

function greet(greeting, punctuation) {
  return `${greeting} ${this.name}${punctuation}`;
}

// call() - immediate execution with individual args
console.log(greet.call(person, 'Hello', '!')); // "Hello John!"

// apply() - immediate execution with array
console.log(greet.apply(person, ['Hi', '.'])); // "Hi John."

// bind() - returns new function
const boundGreet = greet.bind(person);
console.log(boundGreet('Hey', '?')); // "Hey John?"
```

---

## Question 18: What is `async/await` in JavaScript?

**Answer:**
`async/await` is syntactic sugar for promises that makes asynchronous code look synchronous. `async` declares a function as asynchronous, and `await` pauses execution until the promise resolves. It's cleaner than promise chains and easier to debug.

```javascript
// Promise version
function fetchUserData() {
  return fetch('/api/user')
    .then(response => response.json())
    .then(data => console.log(data))
    .catch(error => console.error(error));
}

// async/await version
async function fetchUserData() {
  try {
    const response = await fetch('/api/user');
    const data = await response.json();
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}
```

---

## Question 19: What is a promise in JavaScript? How does it work?

**Answer:**
A promise represents the eventual completion or failure of an asynchronous operation. It's an object with three states: pending, fulfilled, or rejected. You create promises with the Promise constructor and handle results with `.then()` and `.catch()`.

```javascript
// Creating a promise
const myPromise = new Promise((resolve, reject) => {
  setTimeout(() => {
    const success = Math.random() > 0.5;
    if (success) {
      resolve('Operation successful!');
    } else {
      reject('Operation failed!');
    }
  }, 1000);
});

// Using the promise
myPromise
  .then(result => console.log(result))
  .catch(error => console.error(error));
```

---

## Question 20: What is a promise chain in JavaScript?

**Answer:**
Promise chaining lets you execute multiple asynchronous operations in sequence by returning promises from `.then()` handlers. Each `.then()` receives the result of the previous promise, creating a clean flow without callback hell.

```javascript
fetch('/api/user/1')
  .then(response => response.json())
  .then(user => {
    console.log('User:', user.name);
    return fetch(`/api/posts/${user.id}`);
  })
  .then(response => response.json())
  .then(posts => {
    console.log('Posts:', posts.length);
    return posts.filter(post => post.published);
  })
  .then(publishedPosts => {
    console.log('Published:', publishedPosts.length);
  })
  .catch(error => console.error('Error:', error));
```

---

## Question 21: What are the states of a promise?

**Answer:**
A promise has three states: **Pending** (initial state, neither fulfilled nor rejected), **Fulfilled** (operation completed successfully), and **Rejected** (operation failed). Once a promise is fulfilled or rejected, it's settled and cannot change states.

```javascript
// Pending promise
const pendingPromise = new Promise((resolve, reject) => {
  // Still waiting...
});

// Fulfilled promise
const fulfilledPromise = Promise.resolve('Success!');

// Rejected promise
const rejectedPromise = Promise.reject('Error!');

// Check promise state (not directly accessible, but you can test)
console.log(Promise.resolve('test')); // Promise {<fulfilled>: "test"}
console.log(Promise.reject('error')); // Promise {<rejected>: "error"}
```

---

## Question 22: What is the difference between `async/await` and promises?

**Answer:**
`async/await` is syntactic sugar built on top of promises. Promises use `.then()` chains which can get nested, while `async/await` makes code look synchronous and easier to read. Error handling is also cleaner with try-catch blocks instead of `.catch()` chains.

```javascript
// Promise approach
function getUserPosts() {
  return fetch('/api/user')
    .then(response => response.json())
    .then(user => fetch(`/api/posts/${user.id}`))
    .then(response => response.json())
    .catch(error => console.error(error));
}

// async/await approach
async function getUserPosts() {
  try {
    const userResponse = await fetch('/api/user');
    const user = await userResponse.json();
    const postsResponse = await fetch(`/api/posts/${user.id}`);
    return await postsResponse.json();
  } catch (error) {
    console.error(error);
  }
}
```

---

## Question 23: What is the difference between callback and promise?

**Answer:**
Callbacks are functions passed as arguments to handle asynchronous results, but they can lead to callback hell with nested functions. Promises provide better structure with `.then()` chains, built-in error handling, and can be combined with `async/await` for cleaner code.

```javascript
// Callback approach (callback hell)
getData(function(a) {
  getMoreData(a, function(b) {
    getEvenMoreData(b, function(c) {
      console.log(c);
    });
  });
});

// Promise approach
getData()
  .then(a => getMoreData(a))
  .then(b => getEvenMoreData(b))
  .then(c => console.log(c))
  .catch(error => console.error(error));

// async/await with promises
async function fetchData() {
  try {
    const a = await getData();
    const b = await getMoreData(a);
    const c = await getEvenMoreData(b);
    console.log(c);
  } catch (error) {
    console.error(error);
  }
}
```

## 24. What is an observable?

• An observable is a data stream that can emit multiple values over time
• It's like a promise but can handle multiple values instead of just one
• Observables are lazy - they don't execute until you subscribe to them
• They're commonly used in RxJS library for reactive programming

```javascript
// Basic observable example
const { Observable } = require('rxjs');

const observable = new Observable(subscriber => {
  subscriber.next(1);
  subscriber.next(2);
  subscriber.next(3);
  subscriber.complete();
});

observable.subscribe(value => console.log(value)); // 1, 2, 3
```

## 25. What are the differences between promises and observables?

• **Promises**: Handle single async value, eager execution, not cancellable
• **Observables**: Handle multiple values over time, lazy execution, cancellable
• Promises resolve once, observables can emit continuously
• Observables provide operators for data transformation

```javascript
// Promise - single value
const promise = fetch('/api/data');
promise.then(data => console.log(data));

// Observable - multiple values
const { interval } = require('rxjs');
const observable = interval(1000);
const subscription = observable.subscribe(value => console.log(value));
subscription.unsubscribe(); // Can cancel
```

## 26. What is the concept of currying in JavaScript? Can you provide an example?

• Currying transforms a function with multiple arguments into nested functions
• Each function takes one argument and returns another function
• It enables partial application and function composition
• Useful for creating reusable, specialized functions

```javascript
// Regular function
function add(a, b, c) {
  return a + b + c;
}

// Curried version
function curriedAdd(a) {
  return function(b) {
    return function(c) {
      return a + b + c;
    };
  };
}

const addFive = curriedAdd(5);
const addFiveAndThree = addFive(3);
console.log(addFiveAndThree(2)); // 10
```

## 27. What is an anonymous function?

• An anonymous function is a function without a name
• Often used as callback functions or immediately invoked
• Can be assigned to variables or passed as arguments
• Arrow functions are a modern way to write anonymous functions

```javascript
// Anonymous function expression
const greet = function() {
  console.log("Hello!");
};

// Anonymous arrow function
const multiply = (a, b) => a * b;

// Anonymous function as callback
[1, 2, 3].map(function(x) { return x * 2; });

// IIFE (Immediately Invoked Function Expression)
(function() {
  console.log("Executed immediately!");
})();
```

## 28. Explain event delegation in JavaScript.

• Event delegation uses event bubbling to handle events on parent elements
• Instead of adding listeners to each child, add one to the parent
• More efficient for dynamic content and large lists
• Uses event.target to identify which child triggered the event

```javascript
// Instead of adding listeners to each button
document.getElementById('container').addEventListener('click', function(e) {
  if (e.target.classList.contains('button')) {
    console.log('Button clicked:', e.target.textContent);
  }
});

// HTML: <div id="container"><button class="button">Click me</button></div>
```

## 29. What is a "call stack"?

• The call stack tracks function calls in JavaScript execution
• It's a LIFO (Last In, First Out) data structure
• When a function is called, it's pushed onto the stack
• When it returns, it's popped off the stack

```javascript
function first() {
  console.log('First function');
  second();
}

function second() {
  console.log('Second function');
  third();
}

function third() {
  console.log('Third function');
}

first();
// Call stack: first() -> second() -> third() -> second() -> first()
```

## 30. What is the event loop in JavaScript?

• The event loop manages asynchronous operations in JavaScript's single-threaded environment
• It continuously checks the call stack and task queues
• Moves tasks from queues to call stack when stack is empty
• Handles callbacks, promises, and other async operations

```javascript
console.log('1'); // Synchronous - executes first

setTimeout(() => {
  console.log('2'); // Async - goes to task queue
}, 0);

Promise.resolve().then(() => {
  console.log('3'); // Microtask - higher priority
});

console.log('4'); // Synchronous - executes second

// Output: 1, 4, 3, 2
// Event loop prioritizes microtasks over macrotasks
```

## Question 31: What is event propagation in JavaScript?

Event propagation is how events flow through the DOM tree when triggered. There are three phases: capturing (top-down), target (at the element), and bubbling (bottom-up). By default, events bubble up from child to parent elements.

```javascript
// Event propagation example
document.getElementById('parent').addEventListener('click', () => {
    console.log('Parent clicked');
});

document.getElementById('child').addEventListener('click', () => {
    console.log('Child clicked');
});
// Clicking child logs: "Child clicked", then "Parent clicked"
```

## Question 32: What is event bubbling and capturing in JavaScript?

Event bubbling means events start at the target element and bubble up to parent elements. Event capturing is the opposite - events start at the root and capture down to the target. You can control this with the third parameter in addEventListener.

```javascript
// Bubbling (default)
element.addEventListener('click', handler, false);

// Capturing
element.addEventListener('click', handler, true);

// Stop propagation
function handler(e) {
    e.stopPropagation(); // Stops bubbling/capturing
}
```

## Question 33: How do you prevent the default action of an event in JavaScript?

Use `preventDefault()` method on the event object to stop the browser's default behavior. This is commonly used with form submissions, link clicks, or keyboard events where you want custom handling instead of default browser actions.

```javascript
// Prevent form submission
document.getElementById('myForm').addEventListener('submit', (e) => {
    e.preventDefault();
    console.log('Form submission prevented');
});

// Prevent link navigation
document.getElementById('myLink').addEventListener('click', (e) => {
    e.preventDefault();
    console.log('Link click prevented');
});
```

## Question 34: How do you attach multiple event listeners to the same event?

You can attach multiple event listeners to the same event by calling `addEventListener()` multiple times. Each listener will execute in the order they were added. This is useful for modular code where different parts need to respond to the same event.

```javascript
const button = document.getElementById('myButton');

// First listener
button.addEventListener('click', () => {
    console.log('First listener');
});

// Second listener
button.addEventListener('click', () => {
    console.log('Second listener');
});

// Both will execute when button is clicked
```

## Question 35: What is the concept of memoization in JavaScript?

Memoization is an optimization technique that caches function results based on input parameters. When the same inputs are used again, it returns the cached result instead of recalculating, improving performance for expensive operations.

```javascript
// Memoization example
function memoize(fn) {
    const cache = {};
    return function(...args) {
        const key = JSON.stringify(args);
        if (cache[key]) {
            return cache[key];
        }
        const result = fn.apply(this, args);
        cache[key] = result;
        return result;
    };
}

const expensiveFunction = memoize((n) => {
    console.log('Computing...');
    return n * n;
});
```

## Question 36: What is debounce and throttle in JavaScript?

Debounce delays function execution until after a specified time has passed since the last call. Throttle limits function execution to once per specified time interval. Debounce is great for search inputs, throttle is perfect for scroll or resize events.

```javascript
// Debounce - waits for pause in calls
function debounce(func, delay) {
    let timeoutId;
    return function(...args) {
        clearTimeout(timeoutId);
        timeoutId = setTimeout(() => func.apply(this, args), delay);
    };
}

// Throttle - limits execution frequency
function throttle(func, limit) {
    let inThrottle;
    return function(...args) {
        if (!inThrottle) {
            func.apply(this, args);
            inThrottle = true;
            setTimeout(() => inThrottle = false, limit);
        }
    };
}

// Usage examples
const debouncedSearch = debounce(searchFunction, 300);
const throttledScroll = throttle(scrollHandler, 100);
```


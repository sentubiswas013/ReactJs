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

# JavaScript OOP Interview Questions (37-45) - Answers

## 37. What are Object-Oriented Programming (OOP) concepts in JavaScript?

**Answer:** JavaScript supports four main OOP concepts: encapsulation, inheritance, polymorphism, and abstraction. While JavaScript is prototype-based rather than class-based, ES6 introduced class syntax that makes OOP more familiar. These concepts help organize code, promote reusability, and make applications more maintainable.

```javascript
// Basic OOP example
class Animal {
  constructor(name) {
    this.name = name;
  }
  
  speak() {
    console.log(`${this.name} makes a sound`);
  }
}

const dog = new Animal("Rex");
dog.speak(); // "Rex makes a sound"
```

## 38. What are classes and objects in JavaScript?

**Answer:** Classes are blueprints for creating objects with shared properties and methods. Objects are instances of classes that contain actual data. ES6 classes provide a cleaner syntax over JavaScript's prototype-based inheritance. Classes define structure, objects hold the actual values.

```javascript
class Car {
  constructor(brand, model) {
    this.brand = brand;
    this.model = model;
  }
  
  start() {
    return `${this.brand} ${this.model} is starting`;
  }
}

const myCar = new Car("Toyota", "Camry");
console.log(myCar.start()); // "Toyota Camry is starting"
```

## 39. What is encapsulation in JavaScript?

**Answer:** Encapsulation means bundling data and methods together while hiding internal implementation details. In JavaScript, we use private fields (with #) or closures to achieve true encapsulation. This protects data from external interference and ensures controlled access through public methods.

```javascript
class BankAccount {
  #balance = 0; // Private field
  
  deposit(amount) {
    if (amount > 0) {
      this.#balance += amount;
    }
  }
  
  getBalance() {
    return this.#balance;
  }
}

const account = new BankAccount();
account.deposit(100);
console.log(account.getBalance()); // 100
// account.#balance; // Error: Private field
```

## 40. What is inheritance in JavaScript?

**Answer:** Inheritance allows one class to inherit properties and methods from another class using the `extends` keyword. The child class can access parent methods via `super()` and can override them. This promotes code reuse and creates hierarchical relationships between classes.

```javascript
class Animal {
  constructor(name) {
    this.name = name;
  }
  
  speak() {
    return `${this.name} makes a sound`;
  }
}

class Dog extends Animal {
  speak() {
    return `${this.name} barks`;
  }
}

const dog = new Dog("Rex");
console.log(dog.speak()); // "Rex barks"
```

## 41. What is polymorphism in JavaScript?

**Answer:** Polymorphism means "many forms" - the same method name can behave differently in different classes. In JavaScript, this happens through method overriding where child classes provide their own implementation of parent methods. This allows treating different objects uniformly while getting specific behavior.

```javascript
class Shape {
  area() {
    return 0;
  }
}

class Circle extends Shape {
  constructor(radius) {
    super();
    this.radius = radius;
  }
  
  area() {
    return Math.PI * this.radius ** 2;
  }
}

class Rectangle extends Shape {
  constructor(width, height) {
    super();
    this.width = width;
    this.height = height;
  }
  
  area() {
    return this.width * this.height;
  }
}

const shapes = [new Circle(5), new Rectangle(4, 6)];
shapes.forEach(shape => console.log(shape.area()));
```

## 42. What is abstraction in JavaScript?

**Answer:** Abstraction hides complex implementation details and shows only essential features. In JavaScript, we achieve this through abstract classes (using conventions) or interfaces. It simplifies interaction by providing a clean, simple interface while hiding the complexity underneath.

```javascript
class Database {
  connect() {
    throw new Error("connect() must be implemented");
  }
  
  query() {
    throw new Error("query() must be implemented");
  }
}

class MySQL extends Database {
  connect() {
    return "Connected to MySQL";
  }
  
  query(sql) {
    return `Executing: ${sql}`;
  }
}

const db = new MySQL();
console.log(db.connect()); // "Connected to MySQL"
```

## 43. What is prototype-based inheritance in JavaScript?

**Answer:** JavaScript uses prototype-based inheritance where objects inherit directly from other objects. Every object has a prototype property that points to another object. When accessing a property, JavaScript looks up the prototype chain. This is the foundation of JavaScript's inheritance system.

```javascript
function Animal(name) {
  this.name = name;
}

Animal.prototype.speak = function() {
  return `${this.name} makes a sound`;
};

function Dog(name) {
  Animal.call(this, name);
}

Dog.prototype = Object.create(Animal.prototype);
Dog.prototype.bark = function() {
  return `${this.name} barks`;
};

const dog = new Dog("Rex");
console.log(dog.speak()); // "Rex makes a sound"
console.log(dog.bark());  // "Rex barks"
```

## 44. What are arrow functions, and how do they differ from regular functions?

**Answer:** Arrow functions provide shorter syntax and don't have their own `this` binding - they inherit `this` from the enclosing scope. They can't be used as constructors, don't have `arguments` object, and can't be hoisted. Perfect for callbacks and functional programming patterns.

```javascript
// Regular function
function regular(x) {
  return x * 2;
}

// Arrow function
const arrow = x => x * 2;

// 'this' binding difference
class Counter {
  constructor() {
    this.count = 0;
  }
  
  increment() {
    // Arrow function inherits 'this'
    setTimeout(() => {
      this.count++;
      console.log(this.count);
    }, 1000);
  }
}

const counter = new Counter();
counter.increment(); // Works correctly
```

## 45. What are JavaScript's built-in objects?

**Answer:** JavaScript provides many built-in objects like Object, Array, String, Number, Date, Math, RegExp, Promise, and Map. These objects come with predefined properties and methods that handle common programming tasks. They form the foundation of JavaScript programming and provide essential functionality.

```javascript
// Common built-in objects
const str = new String("Hello");
const arr = new Array(1, 2, 3);
const date = new Date();
const regex = new RegExp("\\d+");

// Static methods
console.log(Math.max(1, 5, 3)); // 5
console.log(Object.keys({a: 1, b: 2})); // ["a", "b"]

// Prototype methods
console.log("hello".toUpperCase()); // "HELLO"
console.log([1, 2, 3].map(x => x * 2)); // [2, 4, 6]
```

# JavaScript Interview Questions 46-53 - Answers

## 46. What is a prototype in JavaScript?

**Answer:**
• Every JavaScript object has a prototype - it's like a blueprint or template
• Prototypes let objects inherit properties and methods from other objects
• When you access a property, JavaScript first checks the object, then its prototype chain
• It's the foundation of JavaScript's inheritance system

```javascript
function Person(name) {
  this.name = name;
}

Person.prototype.greet = function() {
  return `Hello, I'm ${this.name}`;
};

const john = new Person("John");
console.log(john.greet()); // "Hello, I'm John"
```

---

## 47. What is the purpose of WeakMap and WeakSet in JavaScript?

**Answer:**
• WeakMap and WeakSet hold "weak" references to objects - they don't prevent garbage collection
• Keys in WeakMap must be objects, not primitives
• They're perfect for storing private data or metadata without memory leaks
• You can't iterate over them or check their size

```javascript
// WeakMap for private data
const privateData = new WeakMap();

class User {
  constructor(name) {
    privateData.set(this, { secret: 'hidden data' });
    this.name = name;
  }
  
  getSecret() {
    return privateData.get(this).secret;
  }
}

const user = new User("Alice");
console.log(user.getSecret()); // "hidden data"
```

---

## 48. How does JavaScript handle memory management and garbage collection?

**Answer:**
• JavaScript automatically manages memory - you don't manually allocate or free it
• Garbage collection removes objects that are no longer reachable or referenced
• Uses mark-and-sweep algorithm - marks reachable objects, sweeps away the rest
• Memory leaks happen when you keep references to objects you don't need anymore

```javascript
// Memory leak example
let users = [];

function addUser(name) {
  users.push({ name, data: new Array(1000000) });
}

// Clear references to prevent leaks
function clearUsers() {
  users = [];
}

addUser("John");
clearUsers(); // Important to clear references
```

---

## 49. How does JavaScript handle scope and closures?

**Answer:**
• Scope determines where variables can be accessed - global, function, or block scope
• Closures happen when inner functions remember variables from outer functions
• Even after outer function finishes, inner function keeps access to those variables
• Closures are created every time a function is created

```javascript
function createCounter() {
  let count = 0;
  
  return function() {
    count++;
    return count;
  };
}

const counter = createCounter();
console.log(counter()); // 1
console.log(counter()); // 2
// 'count' is still accessible via closure
```

---

## 50. What is the use of the window object in JavaScript?

**Answer:**
• Window object represents the browser window and is the global object in browsers
• All global variables and functions become properties of window
• Provides access to browser APIs like location, history, and localStorage
• In Node.js, the global object is 'global', not 'window'

```javascript
// Global variable becomes window property
var message = "Hello World";
console.log(window.message); // "Hello World"

// Browser APIs through window
window.location.href = "https://example.com";
window.localStorage.setItem("key", "value");
window.alert("Hello!");
```

---

## 51. How does the new keyword work in JavaScript?

**Answer:**
• The 'new' keyword creates a new object instance from a constructor function
• It creates an empty object, sets its prototype, calls the constructor with 'this' bound to the new object
• If constructor doesn't return an object, 'new' returns the created object
• Without 'new', 'this' would refer to the global object

```javascript
function Car(brand) {
  this.brand = brand;
  this.start = function() {
    return `${this.brand} is starting`;
  };
}

const myCar = new Car("Toyota");
console.log(myCar.brand); // "Toyota"
console.log(myCar.start()); // "Toyota is starting"

// Without 'new' - wrong way
const wrongCar = Car("Honda"); // undefined
```

---

## 52. What is an IIFE (Immediately Invoked Function Expression)?

**Answer:**
• IIFE is a function that runs immediately after it's defined
• Creates its own scope to avoid polluting the global namespace
• Commonly used in modules and to create private variables
• Wrapped in parentheses and called immediately with ()

```javascript
// Basic IIFE
(function() {
  const secret = "This won't pollute global scope";
  console.log("IIFE executed!");
})();

// IIFE with parameters
(function(name) {
  console.log(`Hello ${name}!`);
})("World");

// Arrow function IIFE
(() => {
  const privateVar = "hidden";
  console.log("Arrow IIFE");
})();
```

---

## 53. What is the difference between a function declaration and a function expression?

**Answer:**
• Function declarations are hoisted - you can call them before they're defined
• Function expressions are not hoisted - they're created when code reaches them
• Declarations create named functions, expressions can be anonymous
• Expressions can be assigned to variables or passed as arguments immediately

```javascript
// Function Declaration - hoisted
console.log(declared()); // Works! Returns "I'm declared"

function declared() {
  return "I'm declared";
}

// Function Expression - not hoisted
console.log(expressed()); // Error! Cannot access before initialization

const expressed = function() {
  return "I'm an expression";
};

// Named function expression
const namedExpr = function myFunc() {
  return "Named expression";
};
```

# JavaScript Interview Questions 54-62 - Answers

## 54. What is the use of `setTimeout()` and `setInterval()`?

• **setTimeout()** executes code once after a delay
• **setInterval()** executes code repeatedly at intervals
• Both are asynchronous and don't block the main thread
• Use clearTimeout() and clearInterval() to cancel them

```javascript
// setTimeout - runs once after 2 seconds
setTimeout(() => {
    console.log("Executed after 2 seconds");
}, 2000);

// setInterval - runs every 1 second
const intervalId = setInterval(() => {
    console.log("Repeats every second");
}, 1000);

// Clear the interval after 5 seconds
setTimeout(() => clearInterval(intervalId), 5000);
```

## 55. How do you clone an object in JavaScript?

• **Shallow copy**: Object.assign() or spread operator
• **Deep copy**: JSON methods or custom recursive function
• Shallow copy only copies first level properties
• Deep copy handles nested objects and arrays

```javascript
const original = { name: "John", address: { city: "NYC" } };

// Shallow copy
const shallow1 = Object.assign({}, original);
const shallow2 = { ...original };

// Deep copy
const deep = JSON.parse(JSON.stringify(original));

// Custom deep clone function
function deepClone(obj) {
    if (obj === null || typeof obj !== "object") return obj;
    if (obj instanceof Date) return new Date(obj);
    if (obj instanceof Array) return obj.map(item => deepClone(item));
    
    const cloned = {};
    for (let key in obj) {
        cloned[key] = deepClone(obj[key]);
    }
    return cloned;
}
```

## 56. How does `JSON.stringify()` and `JSON.parse()` work in JavaScript?

• **JSON.stringify()** converts JavaScript objects to JSON strings
• **JSON.parse()** converts JSON strings back to JavaScript objects
• Useful for data transmission and storage
• Can't handle functions, undefined, or symbols

```javascript
const obj = { name: "Alice", age: 30, active: true };

// Convert to JSON string
const jsonString = JSON.stringify(obj);
console.log(jsonString); // '{"name":"Alice","age":30,"active":true}'

// Convert back to object
const parsedObj = JSON.parse(jsonString);
console.log(parsedObj); // { name: "Alice", age: 30, active: true }

// With formatting
const formatted = JSON.stringify(obj, null, 2);
console.log(formatted);
// {
//   "name": "Alice",
//   "age": 30,
//   "active": true
// }
```

## 57. How can you create a class in JavaScript?

• Use the **class** keyword (ES6+)
• Define constructor for initialization
• Add methods directly in the class body
• Supports inheritance with extends keyword

```javascript
class Person {
    constructor(name, age) {
        this.name = name;
        this.age = age;
    }
    
    greet() {
        return `Hello, I'm ${this.name}`;
    }
    
    static getSpecies() {
        return "Homo sapiens";
    }
}

// Create instance
const john = new Person("John", 25);
console.log(john.greet()); // "Hello, I'm John"

// Inheritance
class Student extends Person {
    constructor(name, age, grade) {
        super(name, age);
        this.grade = grade;
    }
    
    study() {
        return `${this.name} is studying`;
    }
}
```

## 58. Explain the concept of prototype inheritance in JavaScript.

• Every object has a **prototype** property
• Objects inherit properties and methods from their prototype
• Forms a prototype chain up to Object.prototype
• More flexible than classical inheritance

```javascript
// Constructor function
function Animal(name) {
    this.name = name;
}

// Add method to prototype
Animal.prototype.speak = function() {
    return `${this.name} makes a sound`;
};

// Create instance
const dog = new Animal("Rex");
console.log(dog.speak()); // "Rex makes a sound"

// Inheritance
function Dog(name, breed) {
    Animal.call(this, name);
    this.breed = breed;
}

// Set up prototype chain
Dog.prototype = Object.create(Animal.prototype);
Dog.prototype.constructor = Dog;

Dog.prototype.bark = function() {
    return `${this.name} barks`;
};

const myDog = new Dog("Buddy", "Golden Retriever");
console.log(myDog.speak()); // "Buddy makes a sound"
console.log(myDog.bark());  // "Buddy barks"
```

## 59. What are the different methods of creating objects in JavaScript?

• **Object literal**: Simple and direct
• **Constructor function**: Reusable object template
• **Object.create()**: Specify prototype directly
• **Class syntax**: Modern ES6 approach

```javascript
// 1. Object literal
const obj1 = { name: "John", age: 30 };

// 2. Constructor function
function Person(name, age) {
    this.name = name;
    this.age = age;
}
const obj2 = new Person("Alice", 25);

// 3. Object.create()
const personProto = {
    greet() { return `Hello, I'm ${this.name}`; }
};
const obj3 = Object.create(personProto);
obj3.name = "Bob";

// 4. Class syntax
class Employee {
    constructor(name, role) {
        this.name = name;
        this.role = role;
    }
}
const obj4 = new Employee("Carol", "Developer");

// 5. Factory function
function createUser(name, email) {
    return {
        name,
        email,
        login() { return `${this.name} logged in`; }
    };
}
const obj5 = createUser("Dave", "dave@email.com");
```

## 60. What is the difference between `Object.create()` and class-based inheritance?

• **Object.create()** directly sets prototype relationship
• **Classes** provide syntactic sugar over prototype inheritance
• Object.create() is more explicit about prototype chain
• Classes are more familiar to OOP developers

```javascript
// Object.create() approach
const animalProto = {
    speak() {
        return `${this.name} makes a sound`;
    }
};

const dog = Object.create(animalProto);
dog.name = "Rex";
dog.bark = function() {
    return `${this.name} barks`;
};

console.log(dog.speak()); // "Rex makes a sound"

// Class-based approach
class Animal {
    constructor(name) {
        this.name = name;
    }
    
    speak() {
        return `${this.name} makes a sound`;
    }
}

class Dog extends Animal {
    bark() {
        return `${this.name} barks`;
    }
}

const myDog = new Dog("Buddy");
console.log(myDog.speak()); // "Buddy makes a sound"

// Key difference: Object.create gives more control
const customObj = Object.create(null); // No prototype chain
customObj.name = "Custom";
console.log(customObj.toString); // undefined (no Object.prototype)
```

## 61. What is the difference between `class` and `constructor` in JavaScript?

• **Constructor function**: Traditional way, uses function keyword
• **Class**: Modern ES6 syntax, cleaner and more readable
• Both create objects with shared methods
• Classes have better syntax for inheritance

```javascript
// Constructor function approach
function Car(brand, model) {
    this.brand = brand;
    this.model = model;
}

Car.prototype.start = function() {
    return `${this.brand} ${this.model} started`;
};

Car.prototype.stop = function() {
    return `${this.brand} ${this.model} stopped`;
};

const car1 = new Car("Toyota", "Camry");

// Class approach (ES6+)
class Vehicle {
    constructor(brand, model) {
        this.brand = brand;
        this.model = model;
    }
    
    start() {
        return `${this.brand} ${this.model} started`;
    }
    
    stop() {
        return `${this.brand} ${this.model} stopped`;
    }
    
    static getType() {
        return "Vehicle";
    }
}

const car2 = new Vehicle("Honda", "Civic");

// Both work the same way
console.log(car1.start()); // "Toyota Camry started"
console.log(car2.start()); // "Honda Civic started"

// Classes support static methods more cleanly
console.log(Vehicle.getType()); // "Vehicle"
```

## 62. What are getter and setter methods in JavaScript?

• **Getters** retrieve property values with custom logic
• **Setters** modify property values with validation
• Use **get** and **set** keywords
• Accessed like regular properties, not method calls

```javascript
class Rectangle {
    constructor(width, height) {
        this._width = width;
        this._height = height;
    }
    
    // Getter
    get area() {
        return this._width * this._height;
    }
    
    get perimeter() {
        return 2 * (this._width + this._height);
    }
    
    // Setter with validation
    set width(value) {
        if (value > 0) {
            this._width = value;
        } else {
            throw new Error("Width must be positive");
        }
    }
    
    set height(value) {
        if (value > 0) {
            this._height = value;
        } else {
            throw new Error("Height must be positive");
        }
    }
    
    get width() {
        return this._width;
    }
    
    get height() {
        return this._height;
    }
}

const rect = new Rectangle(5, 3);
console.log(rect.area);      // 15 (getter called)
console.log(rect.perimeter); // 16 (getter called)

rect.width = 10;  // setter called
console.log(rect.area); // 30

// Object literal with getters/setters
const person = {
    firstName: "John",
    lastName: "Doe",
    
    get fullName() {
        return `${this.firstName} ${this.lastName}`;
    },
    
    set fullName(value) {
        [this.firstName, this.lastName] = value.split(" ");
    }
};

console.log(person.fullName); // "John Doe"
person.fullName = "Jane Smith";
console.log(person.firstName); // "Jane"
```

## **Arrays and Objects**

### 63. How can you merge two arrays in JavaScript?

There are several ways to merge arrays. The most common and modern approach is using the spread operator. It's clean, readable, and creates a new array without modifying the originals.

```javascript
// Using spread operator (ES6+)
const arr1 = [1, 2, 3];
const arr2 = [4, 5, 6];
const merged = [...arr1, ...arr2]; // [1, 2, 3, 4, 5, 6]

// Using concat method
const merged2 = arr1.concat(arr2);

// Using push with spread
arr1.push(...arr2); // Modifies arr1
```

### 64. What is the difference between `slice()` and `splice()` in JavaScript?

The key difference is that `slice()` returns a copy without modifying the original array, while `splice()` modifies the original array. Think of slice as "view" and splice as "edit".

```javascript
const arr = [1, 2, 3, 4, 5];

// slice() - returns copy, doesn't modify original
const sliced = arr.slice(1, 3); // [2, 3]
console.log(arr); // [1, 2, 3, 4, 5] - unchanged

// splice() - modifies original array
const spliced = arr.splice(1, 2); // removes 2 elements from index 1
console.log(arr); // [1, 4, 5] - modified
console.log(spliced); // [2, 3] - removed elements
```

### 65. How do you remove duplicates from an array in JavaScript?

The easiest way is using `Set` with spread operator. Set automatically removes duplicates because it only stores unique values. For objects, you'll need a different approach.

```javascript
// For primitive values
const arr = [1, 2, 2, 3, 3, 4];
const unique = [...new Set(arr)]; // [1, 2, 3, 4]

// For objects (by property)
const users = [{id: 1, name: 'John'}, {id: 2, name: 'Jane'}, {id: 1, name: 'John'}];
const uniqueUsers = users.filter((user, index, self) => 
  index === self.findIndex(u => u.id === user.id)
);
```

### 66. What are the different ways to loop through an array in JavaScript?

There are many ways, but the most common are `for`, `forEach`, `for...of`, and `map`. Choose based on your needs: `forEach` for side effects, `map` for transformation, `for...of` for readability.

```javascript
const arr = [1, 2, 3];

// Traditional for loop
for (let i = 0; i < arr.length; i++) {
  console.log(arr[i]);
}

// forEach - for side effects
arr.forEach(item => console.log(item));

// for...of - clean syntax
for (const item of arr) {
  console.log(item);
}

// map - for transformation
const doubled = arr.map(item => item * 2);
```

### 67. How do you sort an array of objects based on a property in JavaScript?

Use the `sort()` method with a custom compare function. For strings, use `localeCompare()` for proper alphabetical sorting. For numbers, subtract values to get the correct order.

```javascript
const users = [
  {name: 'John', age: 30},
  {name: 'Jane', age: 25},
  {name: 'Bob', age: 35}
];

// Sort by age (ascending)
users.sort((a, b) => a.age - b.age);

// Sort by name (alphabetical)
users.sort((a, b) => a.name.localeCompare(b.name));

// Sort by age (descending)
users.sort((a, b) => b.age - a.age);
```

### 68. What is the difference between `Object.assign()` and the spread operator (`...`)?

Both merge objects, but spread operator is more modern and readable. The main difference is that `Object.assign()` can take multiple sources and has some edge cases with getters, while spread is simpler and more predictable.

```javascript
const obj1 = {a: 1, b: 2};
const obj2 = {b: 3, c: 4};

// Using spread operator (ES6+)
const merged1 = {...obj1, ...obj2}; // {a: 1, b: 3, c: 4}

// Using Object.assign()
const merged2 = Object.assign({}, obj1, obj2); // {a: 1, b: 3, c: 4}

// Object.assign modifies first argument if not empty object
Object.assign(obj1, obj2); // obj1 is now {a: 1, b: 3, c: 4}
```

## Question 69: What is the difference between shallow copy and deep copy in JavaScript?

**Answer:**
• **Shallow copy** copies only the first level of properties - nested objects still share references
• **Deep copy** creates completely independent copies of all nested levels
• Shallow copy is faster but can cause unexpected mutations in nested data
• Use shallow copy for simple objects, deep copy for complex nested structures

**Example Code:**
```javascript
// Shallow Copy
const original = { name: 'John', address: { city: 'NYC' } };
const shallow = { ...original };
shallow.address.city = 'LA'; // This changes original too!

// Deep Copy
const deep = JSON.parse(JSON.stringify(original));
deep.address.city = 'Chicago'; // Original remains unchanged

// Better deep copy with structuredClone (modern browsers)
const betterDeep = structuredClone(original);
```

---

## Question 70: How do you check if an object is an array in JavaScript?

**Answer:**
• **Array.isArray()** is the most reliable and recommended method
• **instanceof Array** works but can fail with arrays from different frames/windows
• **Object.prototype.toString.call()** is the most robust cross-frame solution
• Avoid using **typeof** - it returns 'object' for arrays, not helpful

**Example Code:**
```javascript
const arr = [1, 2, 3];
const obj = { a: 1 };

// Best method
console.log(Array.isArray(arr)); // true
console.log(Array.isArray(obj)); // false

// Alternative methods
console.log(arr instanceof Array); // true
console.log(Object.prototype.toString.call(arr) === '[object Array]'); // true

// Don't use this
console.log(typeof arr); // 'object' - not helpful!
```

---

## Question 71: What is object destructuring in JavaScript?

**Answer:**
• **Object destructuring** extracts properties from objects into individual variables
• You can **rename variables** during destructuring using colon syntax
• **Default values** can be assigned if properties don't exist
• Works great with **function parameters** and **nested objects**
• Makes code cleaner and more readable than dot notation

**Example Code:**
```javascript
const user = { name: 'Alice', age: 30, city: 'Boston' };

// Basic destructuring
const { name, age } = user;

// Renaming variables
const { name: userName, age: userAge } = user;

// Default values
const { name, country = 'USA' } = user;

// Function parameters
function greet({ name, age = 25 }) {
  return `Hello ${name}, you are ${age}`;
}

// Nested destructuring
const person = { info: { firstName: 'John', lastName: 'Doe' } };
const { info: { firstName } } = person;
```

---

## Question 72: How can you merge two objects in JavaScript?

**Answer:**
• **Spread operator** (...) is the modern, clean way to merge objects
• **Object.assign()** is the traditional method, modifies the first object
• **Spread operator** creates a new object, Object.assign can mutate existing ones
• Later properties **override earlier ones** in both methods
• For deep merging, you need custom solutions or libraries like Lodash

**Example Code:**
```javascript
const obj1 = { a: 1, b: 2 };
const obj2 = { b: 3, c: 4 };

// Spread operator (recommended)
const merged1 = { ...obj1, ...obj2 }; // { a: 1, b: 3, c: 4 }

// Object.assign()
const merged2 = Object.assign({}, obj1, obj2); // { a: 1, b: 3, c: 4 }

// Multiple objects
const obj3 = { d: 5 };
const merged3 = { ...obj1, ...obj2, ...obj3 };

// With additional properties
const merged4 = { ...obj1, ...obj2, e: 6 };
```

---

## Question 73: What is the difference between `for...in` and `for...of` loops in JavaScript?

**Answer:**
• **for...in** iterates over **object keys/property names** - works with objects and arrays
• **for...of** iterates over **values** - works with iterables like arrays, strings, Maps
• **for...in** can access inherited properties, **for...of** only iterates own values
• Use **for...in** for objects, **for...of** for arrays and other iterables
• **for...of** is generally preferred for arrays because it's cleaner and safer

**Example Code:**
```javascript
const arr = ['a', 'b', 'c'];
const obj = { x: 1, y: 2, z: 3 };

// for...in - gets indices/keys
for (let key in arr) {
  console.log(key); // '0', '1', '2'
}

for (let key in obj) {
  console.log(key); // 'x', 'y', 'z'
}

// for...of - gets values
for (let value of arr) {
  console.log(value); // 'a', 'b', 'c'
}

// for...of with strings
for (let char of 'hello') {
  console.log(char); // 'h', 'e', 'l', 'l', 'o'
}

// for...of doesn't work with plain objects
// for (let value of obj) {} // TypeError!
```

---

## Summary

These five questions cover essential JavaScript concepts that every developer should understand:

1. **Shallow vs Deep Copy** - Critical for avoiding mutation bugs
2. **Array Detection** - Essential for type checking and validation
3. **Object Destructuring** - Modern syntax for cleaner code
4. **Object Merging** - Common operation in data manipulation
5. **Loop Types** - Fundamental iteration patterns

Each concept has practical applications in real-world development and demonstrates JavaScript's flexibility and power.
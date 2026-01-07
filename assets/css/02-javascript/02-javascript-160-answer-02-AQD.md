## 🔹1. **Basic JavaScript Concepts**

###  1. What are the different data types in JavaScript?

* **Primitive types**: Number, String, Boolean, Undefined, Null, Symbol, BigInt
* **Non-primitive**: Object (includes arrays, functions, dates)
*  JavaScript is dynamically typed - variables can hold any type

```javascript
let num = 42;           // Number
let str = "Hello";      // String
let bool = true;        // Boolean
let obj = {name: "John"}; // Object
let arr = [1, 2, 3];    // Array (object type)
```

###  2. What is the difference between `var`, `let`, and `const`?

*  **var**: Function-scoped, hoisted, can be redeclared
*  **let**: Block-scoped, not hoisted, cannot be redeclared
*  **const**: Block-scoped, must be initialized, cannot be reassigned

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

###  3. What is a closure in JavaScript?

*  **Definition**: Function that has access to outer function's variables even after outer function returns
*  **Use cases**: Data privacy, function factories, callbacks
*  **Key benefit**: Creates private variables and maintains state

```javascript
function outerFunction(x) {
  return function innerFunction(y) {
    return x + y; // Access to 'x' from outer scope
  };
}

const addFive = outerFunction(5);
console.log(addFive(3)); // 8
```

###  4. What is the difference between `==` and `===` in JavaScript?

*  **== (loose equality)**: Compares values with type coercion
*  **=== (strict equality)**: Compares values and types without coercion
*  **Best practice**: Always use === to avoid unexpected behavior

```javascript
console.log(5 == "5");   // true (type coercion)
console.log(5 === "5");  // false (different types)
console.log(null == undefined);  // true
console.log(null === undefined); // false
```

###  5. Explain the concept of "truthy" and "falsy" values in JavaScript.

*  **Falsy values**: false, 0, "", null, undefined, NaN
*  **Truthy values**: Everything else (including empty arrays/objects)
*  **Usage**: Conditional statements, logical operators

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

###  6. What are the JavaScript data structures, and when would you use them?

*  **Array**: Ordered list, use for sequences and iterations
*  **Object**: Key-value pairs, use for structured data
*  **Map**: Key-value with any key type, better performance for frequent additions/deletions
*  **Set**: Unique values collection, use for removing duplicates

```javascript
let arr = [1, 2, 3];              // Array
let obj = {name: "John", age: 30}; // Object
let map = new Map([["key", "value"]]); // Map
let set = new Set([1, 2, 2, 3]);  // Set: {1, 2, 3}
```

###  7. What is the `undefined` value in JavaScript?

*  **Definition**: Variable declared but not assigned a value
*  **Automatic assignment**: JavaScript assigns undefined by default
*  **Common scenarios**: Uninitialized variables, missing function parameters, non-existent object properties

```javascript
let x;                    // undefined
console.log(x);          // undefined

function test(param) {
  console.log(param);    // undefined if not passed
}

let obj = {};
console.log(obj.name);   // undefined
```

###  8. What is the `null` value in JavaScript? How is it different from `undefined`?

*  **null**: Intentional absence of value, explicitly assigned
*  **undefined**: Variable exists but no value assigned
*  **Type difference**: null is object type, undefined is undefined type
*  **Usage**: null for intentional "no value", undefined for uninitialized

```javascript
let intentionallyEmpty = null;     // Explicitly no value
let notAssigned;                   // undefined

console.log(typeof null);          // "object" (JavaScript quirk)
console.log(typeof undefined);     // "undefined"

console.log(null == undefined);    // true
console.log(null === undefined);   // false
```

###  9. What are the different ways to create objects in JavaScript?

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

###  10. What is hoisting in JavaScript?

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

###  11. What is a pure function in JavaScript? Can you give an example?

A pure function is a function that always returns the same output for the same input and has no side effects. It doesn't modify external variables or perform I/O operations.

**Pure Function Example:**
```javascript
// Pure function - same input, same output, no side effects
function add(a, b) {
  return a + b;
}

console.log(add(2, 3)); // Always returns 5
```

---

###  12. What are higher-order functions in JavaScript?

Higher-order functions are functions that either take other functions as arguments or return functions as results. They're fundamental to functional programming and are commonly used with arrays.

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

###  13. What is a callback function in JavaScript?

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

**Array Method Callbacks:**
```javascript
const numbers = [1, 2, 3, 4];

numbers.forEach(function(num) {
  console.log(num * 2);
});
// Output: 2, 4, 6, 8
```

---

###  14. What are closures in JavaScript, and why are they important?

* A closure allows a function to **remember outer variables**
* Even after the outer function finishes
* Used for **data privacy** and state management

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

---

###  15. How does the `this` keyword work in JavaScript?

* `this` refers to the **object calling the function**
* Its value depends on **how the function is called**
* Arrow functions **do not have their own `this`**

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

---

###  16. What is the difference between synchronous and asynchronous code in JavaScript?

* **Synchronous:** Synchronous code executes line by line, blocking subsequent code until the current operation completes.
* **Asynchronous:** Asynchronous code allows other operations to continue while waiting for long-running tasks to finish.

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

###  17: What is the purpose of the `call()`, `apply()` and `bind()` methods in JavaScript?

**Answer:**
These methods let you control what `this` refers to in a function. 
* `call()` invokes the function immediately with individual arguments, 
* `apply()` does the same but takes an array of arguments, and 
* `bind()` returns a new function with `this` permanently set.

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

###  18: What is `async/await` in JavaScript?

**Answer:**
* `async/await` is syntactic sugar for promises that makes asynchronous code look synchronous. 
* `async` declares a function as asynchronous, and 
* `await` pauses execution until the promise resolves. It's cleaner than promise chains and easier to debug.

```javascript
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

###  19: What is a promise in JavaScript? How does it work?

**Answer:**
* A promise represents the eventual completion or failure of an asynchronous operation. 
* It's an object with three states: pending, fulfilled, or rejected. 
* You create promises with the Promise constructor and handle results with `.then()` and `.catch()`.

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

###  20: What is a promise chain in JavaScript?

**Answer:**
* Promise chaining lets you execute multiple asynchronous operations in sequence by returning promises from `.then()` handlers. 
* Each `.then()` receives the result of the previous promise, 
* creating a clean flow without callback hell.

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

###  21: What are the states of a promise?

**Answer:**
A promise has three states: 
* **Pending** (initial state, neither fulfilled nor rejected), 
* **Fulfilled** (operation completed successfully), and 
* **Rejected** (operation failed). Once a promise is fulfilled or rejected, it's settled and cannot change states.

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

###  22: What is the difference between `async/await` and promises?

**Answer:**
* `async/await` is syntactic sugar built on top of promises. 
* Promises use `.then()` chains which can get nested, 
* while `async/await` makes code look synchronous and easier to read. Error handling is also cleaner with try-catch blocks instead of `.catch()` chains.

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

###  23: What is the difference between callback and promise?

* **Callbacks** are functions passed as arguments to handle asynchronous results, but they can lead to callback hell with nested functions. 
* **Promises** provide better structure with `.then()` chains, built-in error handling, and can be combined with `async/await` for cleaner code.

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

###  24. What is an observable?

*  An observable is a data stream that can emit multiple values over time
*  It's like a promise but can handle multiple values instead of just one
*  Observables are lazy - they don't execute until you subscribe to them
*  They're commonly used in RxJS library for reactive programming

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

###  25. What are the differences between promises and observables?

*  **Promises**: Handle single async value, eager execution, not cancellable
*  **Observables**: Handle multiple values over time, lazy execution, cancellable
*  Promises resolve once, observables can emit continuously
*  Observables provide operators for data transformation

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

###  26. What is the concept of currying in JavaScript? Can you provide an example?

*  Currying transforms a function with multiple arguments into nested functions
*  Each function takes one argument and returns another function
*  It enables partial application and function composition
*  Useful for creating reusable, specialized functions

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

###  27. What is an anonymous function?

*  An anonymous function is a function without a name
*  Often used as callback functions or immediately invoked
*  Can be assigned to variables or passed as arguments
*  Arrow functions are a modern way to write anonymous functions

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

###  28. Explain event delegation in JavaScript.

*  Event delegation uses event bubbling to handle events on parent elements
*  Instead of adding listeners to each child, add one to the parent
*  More efficient for dynamic content and large lists
*  Uses event.target to identify which child triggered the event

```javascript
// Instead of adding listeners to each button
document.getElementById('container').addEventListener('click', function(e) {
  if (e.target.classList.contains('button')) {
    console.log('Button clicked:', e.target.textContent);
  }
});

// HTML: <div id="container"><button class="button">Click me</button></div>
```

###  29. What is a "call stack"?

*  The call stack tracks function calls in JavaScript execution
*  It's a LIFO (Last In, First Out) data structure
*  When a function is called, it's pushed onto the stack
*  When it returns, it's popped off the stack

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

###  30. What is the event loop in JavaScript?

*  The event loop manages asynchronous operations in JavaScript's single-threaded environment
*  It continuously checks the call stack and task queues
*  Moves tasks from queues to call stack when stack is empty
*  Handles callbacks, promises, and other async operations

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

###  31: What is event propagation in JavaScript?

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

###  32: What is event bubbling and capturing in JavaScript?

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

###  33: How do you prevent the default action of an event in JavaScript?

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

###  34: How do you attach multiple event listeners to the same event?

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

###  35: What is the concept of memoization in JavaScript?

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

###  36: What is debounce and throttle in JavaScript?

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

## 🔹2. **Object-Oriented Programming in JavaScript**

###  37. What are Object-Oriented Programming (OOP) concepts in JavaScript?

* JavaScript supports OOP using **objects and prototypes**
* Main concepts are **encapsulation, inheritance, polymorphism, abstraction**
* Helps organize code and make it reusable

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

###  38. What are classes and objects in JavaScript?

* Classes are blueprints for creating objects with shared properties and methods. 
* Objects are instances of classes that contain actual data. ES6 classes provide a cleaner syntax over JavaScript's prototype-based inheritance. 
* Classes define structure, objects hold the actual values.

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

###  39. What is encapsulation in JavaScript?

* Encapsulation means bundling data and methods together while hiding internal implementation details. 
* In JavaScript, we use private fields (with #) or closures to achieve true encapsulation. 
* This protects data from external interference and ensures controlled access through public methods.

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

###  40. What is inheritance in JavaScript?

* Inheritance allows one class to inherit properties and methods from another class using the `extends` keyword. 
* The child class can access parent methods via `super()` and can override them. 
* This promotes code reuse and creates hierarchical relationships between classes.

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

###  41. What is polymorphism in JavaScript?

* Polymorphism means "many forms" - the same method name can behave differently in different classes. 
* In JavaScript, this happens through method overriding where child classes provide their own implementation of parent methods. 
* This allows treating different objects uniformly while getting specific behavior.

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

###  42. What is abstraction in JavaScript?

* Abstraction hides complex implementation details and shows only essential features. 
* In JavaScript, we achieve this through abstract classes (using conventions) or interfaces. 
* It simplifies interaction by providing a clean, simple interface while hiding the complexity underneath.

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

###  43. What is prototype-based inheritance in JavaScript?

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

###  44. What are arrow functions, and how do they differ from regular functions?

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

###  45. What are JavaScript's built-in objects?

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

###  46. What is a prototype in JavaScript?

**Answer:**
*  Every JavaScript object has a prototype - it's like a blueprint or template
*  Prototypes let objects inherit properties and methods from other objects
*  When you access a property, JavaScript first checks the object, then its prototype chain
*  It's the foundation of JavaScript's inheritance system

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

###  47. What is the purpose of WeakMap and WeakSet in JavaScript?

**Answer:**
*  WeakMap and WeakSet hold "weak" references to objects - they don't prevent garbage collection
*  Keys in WeakMap must be objects, not primitives
*  They're perfect for storing private data or metadata without memory leaks
*  You can't iterate over them or check their size

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

###  48. How does JavaScript handle memory management and garbage collection?

**Answer:**
*  JavaScript automatically manages memory - you don't manually allocate or free it
*  Garbage collection removes objects that are no longer reachable or referenced
*  Uses mark-and-sweep algorithm - marks reachable objects, sweeps away the rest
*  Memory leaks happen when you keep references to objects you don't need anymore

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

###  49. How does JavaScript handle scope and closures?

**Answer:**
*  Scope determines where variables can be accessed - global, function, or block scope
*  Closures happen when inner functions remember variables from outer functions
*  Even after outer function finishes, inner function keeps access to those variables
*  Closures are created every time a function is created

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

###  50. What is the use of the window object in JavaScript?

**Answer:**
*  Window object represents the browser window and is the global object in browsers
*  All global variables and functions become properties of window
*  Provides access to browser APIs like location, history, and localStorage
*  In Node.js, the global object is 'global', not 'window'

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

###  51. How does the new keyword work in JavaScript?

**Answer:**
*  The 'new' keyword creates a new object instance from a constructor function
*  It creates an empty object, sets its prototype, calls the constructor with 'this' bound to the new object
*  If constructor doesn't return an object, 'new' returns the created object
*  Without 'new', 'this' would refer to the global object

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

###  52. What is an IIFE (Immediately Invoked Function Expression)?

**Answer:**
*  IIFE is a function that runs immediately after it's defined
*  Creates its own scope to avoid polluting the global namespace
*  Commonly used in modules and to create private variables
*  Wrapped in parentheses and called immediately with ()

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

###  53. What is the difference between a function declaration and a function expression?

**Answer:**
*  Function declarations are hoisted - you can call them before they're defined
*  Function expressions are not hoisted - they're created when code reaches them
*  Declarations create named functions, expressions can be anonymous
*  Expressions can be assigned to variables or passed as arguments immediately

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


###  54. What is the use of `setTimeout()` and `setInterval()`?

*  **setTimeout()** executes code once after a delay
*  **setInterval()** executes code repeatedly at intervals
*  Both are asynchronous and don't block the main thread
*  Use clearTimeout() and clearInterval() to cancel them

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

###  55. How do you clone an object in JavaScript?

*  **Shallow copy**: Object.assign() or spread operator
*  **Deep copy**: JSON methods or custom recursive function
*  Shallow copy only copies first level properties
*  Deep copy handles nested objects and arrays

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

###  56. How does `JSON.stringify()` and `JSON.parse()` work in JavaScript?

*  **JSON.stringify()** converts JavaScript objects to JSON strings
*  **JSON.parse()** converts JSON strings back to JavaScript objects
*  Useful for data transmission and storage
*  Can't handle functions, undefined, or symbols

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

###  57. How can you create a class in JavaScript?

*  Use the **class** keyword (ES6+)
*  Define constructor for initialization
*  Add methods directly in the class body
*  Supports inheritance with extends keyword

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

###  58. Explain the concept of prototype inheritance in JavaScript.

*  Every object has a **prototype** property
*  Objects inherit properties and methods from their prototype
*  Forms a prototype chain up to Object.prototype
*  More flexible than classical inheritance

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

###  59. What are the different methods of creating objects in JavaScript?

*  **Object literal**: Simple and direct
*  **Constructor function**: Reusable object template
*  **Object.create()**: Specify prototype directly
*  **Class syntax**: Modern ES6 approach

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

###  60. What is the difference between `Object.create()` and class-based inheritance?

*  **Object.create()** directly sets prototype relationship
*  **Classes** provide syntactic sugar over prototype inheritance
*  Object.create() is more explicit about prototype chain
*  Classes are more familiar to OOP developers

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

###  61. What is the difference between `class` and `constructor` in JavaScript?

*  **Constructor function**: Traditional way, uses function keyword
*  **Class**: Modern ES6 syntax, cleaner and more readable
*  Both create objects with shared methods
*  Classes have better syntax for inheritance

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

###  62. What are getter and setter methods in JavaScript?

*  **Getters** retrieve property values with custom logic
*  **Setters** modify property values with validation
*  Use **get** and **set** keywords
*  Accessed like regular properties, not method calls

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

## 🔹3.Arrays and Objects**

###  63. How can you merge two arrays in JavaScript?

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

###  64. What is the difference between `slice()` and `splice()` in JavaScript?

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

###  65. How do you remove duplicates from an array in JavaScript?

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

###  66. What are the different ways to loop through an array in JavaScript?

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

###  67. How do you sort an array of objects based on a property in JavaScript?

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

###  68. What is the difference between `Object.assign()` and the spread operator (`...`)?

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

###  69: What is the difference between shallow copy and deep copy in JavaScript?

**Answer:**
*  **Shallow copy** copies only the first level of properties - nested objects still share references
*  **Deep copy** creates completely independent copies of all nested levels
*  Shallow copy is faster but can cause unexpected mutations in nested data
*  Use shallow copy for simple objects, deep copy for complex nested structures

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

###  70: How do you check if an object is an array in JavaScript?

**Answer:**
*  **Array.isArray()** is the most reliable and recommended method
*  **instanceof Array** works but can fail with arrays from different frames/windows
*  **Object.prototype.toString.call()** is the most robust cross-frame solution
*  Avoid using **typeof** - it returns 'object' for arrays, not helpful

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

###  71: What is object destructuring in JavaScript?

**Answer:**
*  **Object destructuring** extracts properties from objects into individual variables
*  You can **rename variables** during destructuring using colon syntax
*  **Default values** can be assigned if properties don't exist
*  Works great with **function parameters** and **nested objects**
*  Makes code cleaner and more readable than dot notation

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

###  72: How can you merge two objects in JavaScript?

**Answer:**
*  **Spread operator** (...) is the modern, clean way to merge objects
*  **Object.assign()** is the traditional method, modifies the first object
*  **Spread operator** creates a new object, Object.assign can mutate existing ones
*  Later properties **override earlier ones** in both methods
*  For deep merging, you need custom solutions or libraries like Lodash

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

###  73: What is the difference between `for...in` and `for...of` loops in JavaScript?

**Answer:**
*  **for...in** iterates over **object keys/property names** - works with objects and arrays
*  **for...of** iterates over **values** - works with iterables like arrays, strings, Maps
*  **for...in** can access inherited properties, **for...of** only iterates own values
*  Use **for...in** for objects, **for...of** for arrays and other iterables
*  **for...of** is generally preferred for arrays because it's cleaner and safer

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

## 🔹4.Functions and Scope**

###  74. What is the difference between local scope and global scope in JavaScript?

*  **Global scope** - Variables declared outside functions, accessible everywhere
*  **Local scope** - Variables declared inside functions, only accessible within that function
*  Global variables can cause naming conflicts and memory issues

```javascript
let globalVar = "I'm global"; // Global scope

function myFunction() {
    let localVar = "I'm local"; // Local scope
    console.log(globalVar); // Can access global
    console.log(localVar);  // Can access local
}

console.log(globalVar); // Works
// console.log(localVar); // Error - not accessible
```

###  75. What is lexical scoping in JavaScript?

*  **Lexical scoping** means inner functions have access to outer function variables
*  Scope is determined by where variables are declared in the code
*  Inner functions "remember" their outer environment

```javascript
function outerFunction(x) {
    let outerVar = x;
    
    function innerFunction(y) {
        console.log(outerVar + y); // Can access outerVar
    }
    
    return innerFunction;
}

const myFunc = outerFunction(10);
myFunc(5); // Outputs: 15
```

###  76. What is the difference between function expressions and function declarations?

*  **Function declarations** are hoisted - can be called before they're defined
*  **Function expressions** are not hoisted - must be defined before use
*  Declarations use `function` keyword, expressions assign to variables

```javascript
// Function Declaration (hoisted)
console.log(declared()); // Works - outputs "Hello"

function declared() {
    return "Hello";
}

// Function Expression (not hoisted)
// console.log(expressed()); // Error - cannot access before initialization

const expressed = function() {
    return "Hi";
};
```

###  77. What is the use of the `arguments` object in JavaScript?

*  **Arguments object** contains all parameters passed to a function
*  Available in regular functions (not arrow functions)
*  Useful for functions with variable number of parameters

```javascript
function sum() {
    let total = 0;
    for (let i = 0; i < arguments.length; i++) {
        total += arguments[i];
    }
    return total;
}

console.log(sum(1, 2, 3, 4)); // 10
console.log(sum(5, 10));      // 15
```

###  78. What is the purpose of the `default` keyword in JavaScript functions?

*  **Default parameters** provide fallback values when arguments aren't passed
*  Prevents undefined errors and makes functions more robust
*  Can use expressions or function calls as defaults

```javascript
function greet(name = "Guest", greeting = "Hello") {
    return `${greeting}, ${name}!`;
}

console.log(greet());              // "Hello, Guest!"
console.log(greet("John"));        // "Hello, John!"
console.log(greet("Jane", "Hi"));  // "Hi, Jane!"
```

###  79. How can you return multiple values from a function in JavaScript?

*  Use **arrays** to return multiple values in order
*  Use **objects** to return named values
*  Use **destructuring** to unpack returned values easily

```javascript
// Using array
function getCoordinates() {
    return [10, 20];
}
const [x, y] = getCoordinates();

// Using object
function getUserInfo() {
    return {
        name: "John",
        age: 30,
        email: "john@email.com"
    };
}
const {name, age} = getUserInfo();
```

###  80. What is recursion in JavaScript? Can you provide an example?

*  **Recursion** is when a function calls itself
*  Must have a base case to stop the recursion
*  Useful for problems that can be broken into smaller similar problems

```javascript
function factorial(n) {
    // Base case
    if (n <= 1) {
        return 1;
    }
    // Recursive case
    return n * factorial(n - 1);
}

console.log(factorial(5)); // 120
console.log(factorial(3)); // 6
```

###  81. How does JavaScript handle multiple callback functions?

*  **Callbacks** can be chained but lead to "callback hell"
*  Each callback executes after the previous one completes
*  Modern solutions use Promises or async/await

```javascript
function getData(callback) {
    setTimeout(() => {
        callback("Data received");
    }, 1000);
}

function processData(data, callback) {
    setTimeout(() => {
        callback(`Processed: ${data}`);
    }, 500);
}

// Callback chaining
getData((data) => {
    console.log(data);
    processData(data, (result) => {
        console.log(result);
    });
});
```

###  82. How do you handle errors in JavaScript?

*  Use **try-catch** blocks to handle synchronous errors
*  Use **catch()** method for Promise errors
*  Use **try-catch with async/await** for async errors

```javascript
// Synchronous error handling
try {
    let result = JSON.parse("invalid json");
} catch (error) {
    console.log("Error:", error.message);
}

// Async error handling
async function fetchData() {
    try {
        const response = await fetch('/api/data');
        const data = await response.json();
        return data;
    } catch (error) {
        console.log("Fetch error:", error);
    }
}
```

###  83. How does JavaScript handle asynchronous operations?

*  JavaScript uses **event loop** to handle async operations
*  **Callbacks, Promises, and async/await** are the main patterns
*  Non-blocking - other code continues while waiting for async operations

```javascript
// Using Promises
fetch('/api/data')
    .then(response => response.json())
    .then(data => console.log(data))
    .catch(error => console.log(error));

// Using async/await
async function loadData() {
    try {
        const response = await fetch('/api/data');
        const data = await response.json();
        console.log(data);
    } catch (error) {
        console.log(error);
    }
}
```

## 🔹5.Asynchronous JavaScript**

###  84. How can you handle asynchronous operations in JavaScript?

*  **Three main approaches**: Callbacks, Promises, and async/await
*  **Promises** are preferred over callbacks to avoid callback hell
*  **Async/await** makes asynchronous code look synchronous and easier to read

```javascript
// Using Promises
fetch('/api/data')
    .then(response => response.json())
    .then(data => console.log(data));

// Using async/await
async function getData() {
    const response = await fetch('/api/data');
    const data = await response.json();
    return data;
}
```

###  85. Explain the concept of JavaScript's single-threaded model.

*  **JavaScript runs on a single thread** - only one operation at a time
*  **Event loop** manages asynchronous operations without blocking
*  **Call stack** executes code, **Web APIs** handle async tasks, **callback queue** waits for execution

```javascript
console.log('1'); // Executes first

setTimeout(() => {
    console.log('2'); // Executes last (async)
}, 0);

console.log('3'); // Executes second

// Output: 1, 3, 2
```

###  86. What are the data types that are mutable in JavaScript?

*  **Objects and arrays** are mutable - can be changed after creation
*  **Primitives** (string, number, boolean) are immutable
*  **Reference types** can be modified, primitive types create new values

```javascript
// Mutable (objects/arrays)
const obj = { name: 'John' };
obj.name = 'Jane'; // Changes original object

const arr = [1, 2, 3];
arr.push(4); // Modifies original array

// Immutable (primitives)
let str = 'hello';
str.toUpperCase(); // Creates new string, doesn't change original
```

###  87. What is a function in JavaScript? How do you declare one?

*  **Functions** are reusable blocks of code that perform specific tasks
*  **Three ways to declare**: function declaration, function expression, arrow function
*  Functions can take parameters and return values

```javascript
// Function declaration
function greet(name) {
    return `Hello, ${name}!`;
}

// Function expression
const greet2 = function(name) {
    return `Hi, ${name}!`;
};

// Arrow function
const greet3 = (name) => `Hey, ${name}!`;
```

###  88. How does JavaScript handle multiple asynchronous operations in sequence?

*  **Promise chaining** with .then() for sequential execution
*  **Async/await** makes sequential code more readable
*  **Promise.all()** for parallel execution when order doesn't matter

```javascript
// Sequential with async/await
async function processData() {
    const user = await fetchUser();
    const profile = await fetchProfile(user.id);
    const posts = await fetchPosts(user.id);
    return { user, profile, posts };
}

// Sequential with Promise chaining
fetchUser()
    .then(user => fetchProfile(user.id))
    .then(profile => fetchPosts(profile.userId));
```

###  89. What is the purpose of `Promise.all()` and `Promise.race()`?

*  **Promise.all()** waits for all promises to resolve, fails if any reject
*  **Promise.race()** resolves with the first promise that settles (resolves or rejects)
*  Use Promise.all() for parallel operations, Promise.race() for timeout scenarios

```javascript
// Promise.all() - all must succeed
const results = await Promise.all([
    fetch('/api/users'),
    fetch('/api/posts'),
    fetch('/api/comments')
]);

// Promise.race() - first to finish wins
const result = await Promise.race([
    fetch('/api/data'),
    new Promise((_, reject) => 
        setTimeout(() => reject('Timeout'), 5000)
    )
]);
```

###  90. How do you handle errors in an async function?

*  **Try-catch blocks** with async/await for clean error handling
*  **Catch method** with promises for error handling
*  Always handle both network errors and application errors

```javascript
async function fetchData() {
    try {
        const response = await fetch('/api/data');
        if (!response.ok) {
            throw new Error('Network error');
        }
        const data = await response.json();
        return data;
    } catch (error) {
        console.error('Error:', error.message);
        throw error; // Re-throw if needed
    }
}
```

###  91. What is a callback hell, and how can you avoid it?

*  **Callback hell** occurs when callbacks are nested deeply, making code hard to read
*  **Promises** and **async/await** solve this problem
*  Modern JavaScript favors Promise-based approaches

```javascript
// Callback hell (avoid this)
getData(function(a) {
    getMoreData(a, function(b) {
        getEvenMoreData(b, function(c) {
            // deeply nested...
        });
    });
});

// Solution with async/await
async function processData() {
    const a = await getData();
    const b = await getMoreData(a);
    const c = await getEvenMoreData(b);
    return c;
}
```

###  92. What are the different ways to loop through an array in JavaScript?

*  **For loop** - traditional, full control over iteration
*  **forEach()** - functional approach, can't break early
*  **for...of** - modern, works with iterables, can break

```javascript
const arr = [1, 2, 3, 4, 5];

// Traditional for loop
for (let i = 0; i < arr.length; i++) {
    console.log(arr[i]);
}

// forEach method
arr.forEach(item => console.log(item));

// for...of loop
for (const item of arr) {
    console.log(item);
}

// Map for transformation
const doubled = arr.map(x => x * 2);
```

## 🔹6.ES6 and Beyond**

###  93. What are template literals in JavaScript?

*  Template literals use backticks instead of quotes for strings
*  They allow embedded expressions with `${}`
*  Support multi-line strings without escape characters
*  Much cleaner than string concatenation

```javascript
const name = "John";
const age = 25;

// Old way
const message1 = "Hello " + name + ", you are " + age + " years old";

// Template literal
const message2 = `Hello ${name}, you are ${age} years old`;

// Multi-line
const html = `
  <div>
    <h1>${name}</h1>
    <p>Age: ${age}</p>
  </div>
`;
```

###  94. What are the new features introduced in ES6?

*  Let and const for block scoping
*  Arrow functions for cleaner syntax
*  Template literals for string interpolation
*  Destructuring for extracting values
*  Classes for object-oriented programming
*  Modules for code organization

```javascript
// Arrow functions
const add = (a, b) => a + b;

// Destructuring
const [x, y] = [1, 2];
const {name, age} = {name: "John", age: 25};

// Classes
class Person {
  constructor(name) {
    this.name = name;
  }
}

// Modules
export default Person;
import Person from './person.js';
```

###  95. What is a set and a map in JavaScript?

*  Set stores unique values only, no duplicates
*  Map stores key-value pairs with any type of keys
*  Both are iterable and have size property
*  More efficient than arrays for certain operations

```javascript
// Set - unique values
const numbers = new Set([1, 2, 2, 3, 3, 4]);
console.log(numbers); // Set {1, 2, 3, 4}
numbers.add(5);
numbers.has(3); // true

// Map - key-value pairs
const userRoles = new Map();
userRoles.set("john", "admin");
userRoles.set("jane", "user");
userRoles.get("john"); // "admin"
userRoles.size; // 2
```

###  96. What are symbols in JavaScript? When would you use them?

*  Symbols create unique identifiers that can't be duplicated
*  Useful for object properties that should be private
*  Often used in libraries to avoid naming conflicts
*  Each symbol is completely unique

```javascript
// Creating symbols
const id = Symbol("id");
const id2 = Symbol("id");
console.log(id === id2); // false - always unique

// Using as object properties
const user = {
  name: "John",
  [id]: 123 // private-like property
};

// Won't show in normal iteration
console.log(Object.keys(user)); // ["name"]
console.log(user[id]); // 123
```

###  97. What are generator functions in JavaScript?

*  Functions that can pause and resume execution
*  Use `function*` syntax and `yield` keyword
*  Return an iterator object
*  Great for creating sequences or handling async operations

```javascript
function* numberGenerator() {
  yield 1;
  yield 2;
  yield 3;
}

const gen = numberGenerator();
console.log(gen.next().value); // 1
console.log(gen.next().value); // 2
console.log(gen.next().value); // 3

// Infinite sequence
function* fibonacci() {
  let a = 0, b = 1;
  while (true) {
    yield a;
    [a, b] = [b, a + b];
  }
}
```

###  98. How does destructuring work in JavaScript?

*  Extracts values from arrays or objects into variables
*  Makes code cleaner and more readable
*  Can set default values
*  Works with nested structures too

```javascript
// Array destructuring
const colors = ["red", "green", "blue"];
const [first, second] = colors;
console.log(first); // "red"

// Object destructuring
const person = {name: "John", age: 25, city: "NYC"};
const {name, age} = person;
console.log(name); // "John"

// With defaults
const {country = "USA"} = person;
console.log(country); // "USA"

// Nested destructuring
const user = {profile: {email: "john@email.com"}};
const {profile: {email}} = user;
```

###  99. What is the spread operator, and how do you use it?

*  Three dots `...` that spreads elements
*  Works with arrays, objects, and function arguments
*  Great for copying and merging
*  Makes code more concise

```javascript
// Array spreading
const arr1 = [1, 2, 3];
const arr2 = [...arr1, 4, 5]; // [1, 2, 3, 4, 5]

// Object spreading
const obj1 = {a: 1, b: 2};
const obj2 = {...obj1, c: 3}; // {a: 1, b: 2, c: 3}

// Function arguments
function sum(a, b, c) {
  return a + b + c;
}
const numbers = [1, 2, 3];
sum(...numbers); // 6

// Copying arrays
const copy = [...arr1];
```

###  100. What is the rest parameter in JavaScript?

*  Collects remaining arguments into an array
*  Uses same `...` syntax but in function parameters
*  Must be the last parameter
*  Replaces the old `arguments` object

```javascript
// Rest parameters
function sum(...numbers) {
  return numbers.reduce((total, num) => total + num, 0);
}

sum(1, 2, 3, 4); // 10

// With other parameters
function greet(greeting, ...names) {
  return `${greeting} ${names.join(", ")}`;
}

greet("Hello", "John", "Jane", "Bob"); // "Hello John, Jane, Bob"

// Array destructuring with rest
const [first, ...rest] = [1, 2, 3, 4];
console.log(rest); // [2, 3, 4]
```

###  101. What are `Promise.allSettled()`, `Promise.any()`, and `Promise.finally()`?

*  `allSettled()` waits for all promises, returns all results
*  `any()` resolves when first promise succeeds
*  `finally()` runs code regardless of promise outcome
*  These give more control over promise handling

```javascript
// Promise.allSettled() - waits for all
const promises = [
  Promise.resolve(1),
  Promise.reject("error"),
  Promise.resolve(3)
];

Promise.allSettled(promises).then(results => {
  console.log(results);
  // [{status: "fulfilled", value: 1}, {status: "rejected", reason: "error"}, ...]
});

// Promise.any() - first success
Promise.any(promises).then(value => {
  console.log(value); // 1 (first successful)
});

// Promise.finally()
fetch("/api/data")
  .then(response => response.json())
  .catch(error => console.error(error))
  .finally(() => console.log("Request completed"));
```

###  102. How do you create a class in JavaScript?

*  Use `class` keyword followed by class name
*  Constructor method for initialization
*  Methods defined without `function` keyword
*  Supports inheritance with `extends`

```javascript
class Person {
  constructor(name, age) {
    this.name = name;
    this.age = age;
  }
  
  greet() {
    return `Hello, I'm ${this.name}`;
  }
  
  static species() {
    return "Homo sapiens";
  }
}

// Creating instances
const john = new Person("John", 25);
console.log(john.greet()); // "Hello, I'm John"

// Inheritance
class Student extends Person {
  constructor(name, age, grade) {
    super(name, age);
    this.grade = grade;
  }
}
```

###  103. What is the difference between `class` and `function` constructors in JavaScript?

*  Classes are syntactic sugar over function constructors
*  Classes must use `new` keyword, functions are more flexible
*  Classes have cleaner syntax and better inheritance
*  Function constructors are the traditional way

```javascript
// Function constructor
function PersonFunc(name, age) {
  this.name = name;
  this.age = age;
}

PersonFunc.prototype.greet = function() {
  return `Hello, I'm ${this.name}`;
};

// Class syntax
class PersonClass {
  constructor(name, age) {
    this.name = name;
    this.age = age;
  }
  
  greet() {
    return `Hello, I'm ${this.name}`;
  }
}

// Both create similar objects
const person1 = new PersonFunc("John", 25);
const person2 = new PersonClass("Jane", 30);

// Classes are stricter
PersonClass(); // TypeError: Class constructor cannot be invoked without 'new'
PersonFunc(); // Works but creates issues
```

## 🔹7.Error Handling and Debugging**

###  104. What is try-catch in JavaScript? How does it work?

**Answer:**
*  Try-catch is JavaScript's error handling mechanism that lets you catch and handle runtime errors gracefully
*  The `try` block contains code that might throw an error, while `catch` handles any errors that occur
*  If no error occurs, the catch block is skipped entirely
*  You can also add a `finally` block that always executes regardless of errors

```javascript
try {
    let result = JSON.parse('invalid json');
    console.log(result);
} catch (error) {
    console.log('Error occurred:', error.message);
} finally {
    console.log('This always runs');
}
```

###  105. What is the difference between `throw` and `return` in JavaScript?

**Answer:**
*  `return` exits a function normally and passes a value back to the caller
*  `throw` creates an exception that stops normal execution and can be caught by try-catch blocks
*  `return` is for normal function completion, `throw` is for error conditions
*  When you throw an error, execution stops immediately unless caught

```javascript
function validateAge(age) {
    if (age < 0) {
        throw new Error('Age cannot be negative'); // Stops execution
    }
    return age; // Normal return
}

try {
    let age = validateAge(-5);
} catch (error) {
    console.log(error.message); // "Age cannot be negative"
}
```

###  106. How do you handle exceptions in JavaScript?

**Answer:**
*  Use try-catch blocks to handle exceptions gracefully
*  Create custom error types by extending the Error class
*  Use specific error handling for different types of exceptions
*  Always provide meaningful error messages for debugging

```javascript
class ValidationError extends Error {
    constructor(message) {
        super(message);
        this.name = 'ValidationError';
    }
}

function processUser(user) {
    try {
        if (!user.email) {
            throw new ValidationError('Email is required');
        }
        // Process user...
    } catch (error) {
        if (error instanceof ValidationError) {
            console.log('Validation failed:', error.message);
        } else {
            console.log('Unexpected error:', error);
        }
    }
}
```

###  107. What are some common JavaScript debugging techniques?

**Answer:**
*  Use `console.log()` for basic debugging and variable inspection
*  Use browser developer tools with breakpoints for step-by-step debugging
*  Use `console.table()` for arrays and objects, `console.error()` for errors
*  Use `debugger;` statement to pause execution in dev tools

```javascript
function calculateTotal(items) {
    console.log('Input items:', items); // Basic logging
    
    let total = 0;
    items.forEach((item, index) => {
        debugger; // Pauses execution here
        console.table({index, item, currentTotal: total}); // Structured display
        total += item.price;
    });
    
    console.error('Debug: Final total is', total); // Error-level logging
    return total;
}
```

###  108. What is the difference between `Error` and `TypeError` in JavaScript?

**Answer:**
*  `Error` is the base class for all JavaScript errors - it's the generic error type
*  `TypeError` is a specific type of error that occurs when a value is not of the expected type
*  `TypeError` extends `Error` and provides more specific information about type-related issues
*  Use `TypeError` when checking types, use generic `Error` for other issues

```javascript
// Generic Error
function divide(a, b) {
    if (b === 0) {
        throw new Error('Division by zero is not allowed');
    }
    return a / b;
}

// TypeError - specific type error
function processString(str) {
    if (typeof str !== 'string') {
        throw new TypeError('Expected a string, got ' + typeof str);
    }
    return str.toUpperCase();
}

try {
    processString(123); // Throws TypeError
} catch (error) {
    console.log(error instanceof TypeError); // true
    console.log(error instanceof Error); // also true (inheritance)
}
```

## JavaScript Modules 

###  109: What is the difference between `import` and `require` in JavaScript?

*  **`require`** is CommonJS syntax used in Node.js - it's synchronous and loads modules at runtime
*  **`import`** is ES6 module syntax - it's static, analyzed at compile time, and supports tree shaking
*  `require` returns whatever is assigned to `module.exports`, while `import` can destructure named exports
*  `import` statements are hoisted and must be at the top level, `require` can be called conditionally

```javascript
// CommonJS (require)
const fs = require('fs');
const { readFile } = require('fs');

// ES6 Modules (import)
import fs from 'fs';
import { readFile } from 'fs';
```

---

###  110: What is the purpose of the `export` keyword in JavaScript?

*  **`export`** makes functions, objects, or values available to other modules
*  You can use **named exports** for multiple items or **default export** for one main item
*  Named exports must be imported with exact names, default exports can be imported with any name
*  It enables modular programming by creating reusable code components

```javascript
// Named exports
export const PI = 3.14159;
export function calculate(x) { return x * 2; }

// Default export
export default class Calculator {
  add(a, b) { return a + b; }
}
```

---

###  111: What are JavaScript arrays and how do you manipulate them?

*  **Arrays** are ordered collections that can hold any data type - numbers, strings, objects, even other arrays
*  Key methods: `push()` adds to end, `pop()` removes from end, `shift()` removes from start, `unshift()` adds to start
*  `map()` transforms elements, `filter()` selects elements, `reduce()` combines elements into single value
*  Arrays are zero-indexed and have dynamic length

```javascript
const fruits = ['apple', 'banana'];
fruits.push('orange');           // ['apple', 'banana', 'orange']
const doubled = [1,2,3].map(x => x * 2);  // [2, 4, 6]
const evens = [1,2,3,4].filter(x => x % 2 === 0);  // [2, 4]
```

---

###  112: How do you implement modules in JavaScript?

*  **ES6 modules**: Use `export` to expose functionality and `import` to consume it
*  **CommonJS**: Use `module.exports` to expose and `require()` to import
*  Create separate files for different functionalities to keep code organized
*  Use default exports for main functionality, named exports for utilities

```javascript
// math.js - ES6 module
export const add = (a, b) => a + b;
export default function multiply(a, b) { return a * b; }

// main.js
import multiply, { add } from './math.js';
console.log(add(2, 3));      // 5
console.log(multiply(2, 3)); // 6
```

---

###  113: What is the `default` export in JavaScript modules?

*  **Default export** allows you to export one main thing from a module without specifying a name
*  Each module can have only **one default export** but multiple named exports
*  When importing, you can give the default export any name you want
*  It's typically used for the primary functionality of a module

```javascript
// calculator.js
export default class Calculator {
  add(a, b) { return a + b; }
}

// main.js - can import with any name
import Calc from './calculator.js';
import MyCalculator from './calculator.js';  // Same thing, different name
```

---

###  114: What are the benefits of using modules in JavaScript?

*  **Code organization**: Split large applications into smaller, manageable files
*  **Reusability**: Write once, use in multiple places without code duplication
*  **Namespace management**: Avoid global variable pollution and naming conflicts
*  **Dependency management**: Clear dependencies between different parts of your application
*  **Tree shaking**: Bundlers can remove unused code, reducing bundle size

```javascript
// Before modules - everything in global scope
var userUtils = { getName: function() {...} };
var apiUtils = { fetchData: function() {...} };

// With modules - clean separation
// userUtils.js
export const getName = () => {...};

// apiUtils.js  
export const fetchData = () => {...};
```

## Browser and DOM

###  115: How do you make an AJAX request in JavaScript?

*  **AJAX** lets you send HTTP requests without refreshing the page - it's asynchronous communication with servers
*  **XMLHttpRequest** is the traditional way, but **Fetch API** is modern and cleaner
*  You can send GET, POST, PUT, DELETE requests and handle responses with promises
*  Always handle both success and error cases for robust applications

```javascript
// Modern Fetch API
fetch('https://api.example.com/data')
  .then(response => response.json())
  .then(data => console.log(data))
  .catch(error => console.error('Error:', error));

// POST request
fetch('/api/users', {
  method: 'POST',
  headers: { 'Content-Type': 'application/json' },
  body: JSON.stringify({ name: 'John', age: 30 })
});
```

---

###  116: What is the Fetch API in JavaScript?

*  **Fetch API** is the modern replacement for XMLHttpRequest - it's promise-based and cleaner
*  Returns a **Promise** that resolves to the Response object representing the response
*  You need to call `.json()`, `.text()`, or `.blob()` to extract data from the response
*  Built-in support for request/response headers, different HTTP methods, and error handling

```javascript
// Basic GET request
const response = await fetch('/api/data');
const data = await response.json();

// With error handling
fetch('/api/data')
  .then(response => {
    if (!response.ok) throw new Error('Network error');
    return response.json();
  })
  .then(data => console.log(data));
```

---

###  117: Explain the concept of a single-page application (SPA).

*  **SPA** loads one HTML page and dynamically updates content without full page reloads
*  JavaScript handles routing, navigation, and content updates - making it feel like a desktop app
*  **Benefits**: Faster navigation, better user experience, reduced server load
*  **Challenges**: SEO complexity, initial load time, browser history management

```javascript
// Simple SPA routing example
function navigate(page) {
  const content = document.getElementById('content');
  
  switch(page) {
    case 'home':
      content.innerHTML = '<h1>Home Page</h1>';
      break;
    case 'about':
      content.innerHTML = '<h1>About Page</h1>';
      break;
  }
  
  history.pushState({page}, '', `/${page}`);
}
```

---

###  118: What is the DOM (Document Object Model) in JavaScript?

*  **DOM** is a programming interface that represents HTML/XML documents as a tree of objects
*  Each HTML element becomes a **node** that you can access, modify, add, or remove with JavaScript
*  It's the bridge between your HTML structure and JavaScript code
*  DOM provides methods like `getElementById`, `querySelector`, `createElement` for manipulation

```javascript
// Accessing DOM elements
const title = document.getElementById('title');
const buttons = document.querySelectorAll('.btn');

// Modifying DOM
title.textContent = 'New Title';
title.style.color = 'blue';

// Creating new elements
const newDiv = document.createElement('div');
newDiv.innerHTML = '<p>Hello World</p>';
document.body.appendChild(newDiv);
```

---

###  119: How do you manipulate the DOM using JavaScript?

*  **Select elements** using `getElementById`, `querySelector`, or `getElementsByClassName`
*  **Modify content** with `textContent`, `innerHTML`, or `innerText`
*  **Change styles** through the `style` property or by adding/removing CSS classes
*  **Create and append** new elements using `createElement` and `appendChild`

```javascript
// Selecting and modifying
const element = document.querySelector('.my-class');
element.textContent = 'Updated text';
element.classList.add('active');

// Creating new elements
const list = document.getElementById('myList');
const newItem = document.createElement('li');
newItem.textContent = 'New item';
list.appendChild(newItem);

// Event handling
element.addEventListener('click', () => alert('Clicked!'));
```

---

###  120: What is the difference between `Object.freeze()` and `Object.seal()` in JavaScript?

*  **`Object.freeze()`** makes an object completely immutable - no adding, deleting, or modifying properties
*  **`Object.seal()`** prevents adding/deleting properties but allows modifying existing ones
*  Both prevent extension of the object, but seal is less restrictive than freeze
*  Use freeze for constants, seal when you want fixed structure but changeable values

```javascript
const frozenObj = Object.freeze({ name: 'John', age: 30 });
frozenObj.age = 31;        // Ignored - no change
frozenObj.city = 'NYC';    // Ignored - can't add

const sealedObj = Object.seal({ name: 'Jane', age: 25 });
sealedObj.age = 26;        // Works - can modify
sealedObj.city = 'LA';     // Ignored - can't add
delete sealedObj.name;     // Ignored - can't delete
```

---

###  121: How do you prevent the default action of an event in JavaScript?

*  Use **`preventDefault()`** method on the event object to stop the browser's default behavior
*  Common use cases: preventing form submission, stopping link navigation, custom drag behavior
*  **Important**: `preventDefault()` stops default action but doesn't stop event propagation
*  Always check if the event object exists before calling preventDefault

```javascript
// Prevent form submission
document.querySelector('form').addEventListener('submit', (e) => {
  e.preventDefault();
  console.log('Form submission prevented');
});

// Prevent link navigation
document.querySelector('a').addEventListener('click', (e) => {
  e.preventDefault();
  console.log('Link click prevented');
});

// Prevent context menu
document.addEventListener('contextmenu', (e) => e.preventDefault());
```

---

###  122: What is the difference between `addEventListener()` and `onclick`?

*  **`addEventListener()`** can attach multiple listeners to the same event, `onclick` can only have one
*  **`addEventListener()`** provides better control with options like `once`, `passive`, `capture`
*  **`onclick`** is simpler but overwrites previous handlers, addEventListener adds to them
*  addEventListener is the modern, recommended approach for event handling

```javascript
const button = document.querySelector('button');

// onclick - only one handler
button.onclick = () => console.log('First handler');
button.onclick = () => console.log('Second handler'); // Overwrites first

// addEventListener - multiple handlers
button.addEventListener('click', () => console.log('Handler 1'));
button.addEventListener('click', () => console.log('Handler 2')); // Both run

// Advanced options
button.addEventListener('click', handler, { 
  once: true,     // Run only once
  passive: true   // Never calls preventDefault
});
```

###  123: What is `localStorage` and `sessionStorage` in JavaScript?

*  **`localStorage`** stores data permanently until manually cleared - survives browser restarts and tab closures
*  **`sessionStorage`** stores data only for the current tab session - cleared when tab is closed
*  Both store **key-value pairs as strings** and have the same API methods
*  **Storage limit** is typically 5-10MB per origin, much larger than cookies

```javascript
// localStorage - persists across sessions
localStorage.setItem('username', 'john');
localStorage.setItem('theme', 'dark');
const user = localStorage.getItem('username');
localStorage.removeItem('theme');

// sessionStorage - only for current tab
sessionStorage.setItem('tempData', 'value');
const temp = sessionStorage.getItem('tempData');
sessionStorage.clear(); // Clear all session data
```

---

###  124: What is the difference between `localStorage` and `cookies` in JavaScript?

*  **Storage size**: localStorage holds 5-10MB, cookies only 4KB per cookie
*  **Automatic sending**: Cookies are sent with every HTTP request, localStorage stays client-side
*  **Expiration**: localStorage persists until cleared, cookies can have expiration dates
*  **Security**: Cookies can be httpOnly and secure, localStorage is always accessible via JavaScript

```javascript
// localStorage - client-side only
localStorage.setItem('userPrefs', JSON.stringify({theme: 'dark'}));

// Cookies - sent with requests
document.cookie = 'sessionId=abc123; expires=Fri, 31 Dec 2024 23:59:59 GMT';
document.cookie = 'theme=dark; path=/';

// Reading cookies requires parsing
const cookies = document.cookie.split(';').reduce((acc, cookie) => {
  const [key, value] = cookie.trim().split('=');
  acc[key] = value;
  return acc;
}, {});
```

---

###  125: How do you handle CORS (Cross-Origin Resource Sharing) in JavaScript?

*  **CORS** is a browser security feature that blocks requests between different origins (domain, port, protocol)
*  **Server-side solution**: Add proper CORS headers like `Access-Control-Allow-Origin`
*  **Client-side**: Use proxy servers, JSONP (legacy), or configure your development server
*  **Preflight requests** are sent for complex requests to check permissions first

```javascript
// CORS error - different origins
fetch('https://api.example.com/data') // Might fail due to CORS

// Solutions:
// 1. Proxy in development
fetch('/api/proxy/data') // Your server proxies to external API

// 2. JSONP for GET requests (legacy)
function handleResponse(data) { console.log(data); }
const script = document.createElement('script');
script.src = 'https://api.example.com/data?callback=handleResponse';

// 3. Server adds CORS headers
// Access-Control-Allow-Origin: *
// Access-Control-Allow-Methods: GET, POST, PUT
```

---

###  126: How do you make an AJAX request in JavaScript?

*  **AJAX** enables asynchronous communication with servers without page refresh
*  **Modern approach**: Use Fetch API with promises for cleaner, more readable code
*  **Legacy approach**: XMLHttpRequest for older browser support
*  Always handle both **success and error cases** for robust applications

```javascript
// Modern Fetch API
async function fetchData() {
  try {
    const response = await fetch('/api/users');
    const data = await response.json();
    return data;
  } catch (error) {
    console.error('Request failed:', error);
  }
}

// Legacy XMLHttpRequest
const xhr = new XMLHttpRequest();
xhr.open('GET', '/api/users');
xhr.onload = () => {
  if (xhr.status === 200) {
    const data = JSON.parse(xhr.responseText);
    console.log(data);
  }
};
xhr.send();
```

---

###  127: What are `XMLHttpRequest` and `Fetch` API in JavaScript?

*  **XMLHttpRequest** is the original way to make HTTP requests - callback-based and more verbose
*  **Fetch API** is modern, promise-based replacement - cleaner syntax and better error handling
*  **Fetch** doesn't reject on HTTP error status (404, 500), you need to check `response.ok`
*  **XMLHttpRequest** has built-in request timeout and progress events, Fetch needs AbortController

```javascript
// XMLHttpRequest - legacy but still used
const xhr = new XMLHttpRequest();
xhr.open('POST', '/api/data');
xhr.setRequestHeader('Content-Type', 'application/json');
xhr.onload = () => console.log(xhr.responseText);
xhr.send(JSON.stringify({name: 'John'}));

// Fetch API - modern approach
fetch('/api/data', {
  method: 'POST',
  headers: {'Content-Type': 'application/json'},
  body: JSON.stringify({name: 'John'})
})
.then(response => response.json())
.then(data => console.log(data));
```

---

###  128: What is the `Function.prototype.bind()` method in JavaScript?

*  **`bind()`** creates a new function with a specific `this` context and optionally pre-filled arguments
*  Unlike `call()` and `apply()`, **bind doesn't execute** the function immediately
*  **Partial application**: You can pre-fill some arguments and provide others later
*  Commonly used in event handlers and callback functions to maintain context

```javascript
const person = {
  name: 'John',
  greet(greeting) {
    return `${greeting}, I'm ${this.name}`;
  }
};

// Bind specific context
const boundGreet = person.greet.bind(person);
console.log(boundGreet('Hello')); // "Hello, I'm John"

// Partial application
const sayHello = person.greet.bind(person, 'Hello');
console.log(sayHello()); // "Hello, I'm John"

// Event handler context
button.addEventListener('click', person.greet.bind(person, 'Hi'));
```

---

###  129: How do you handle cookies in JavaScript?

*  **Reading cookies**: Access via `document.cookie` - returns all cookies as a single string
*  **Setting cookies**: Assign to `document.cookie` with key=value format and optional attributes
*  **Cookie attributes**: `expires`, `path`, `domain`, `secure`, `httpOnly`, `SameSite`
*  **Parsing required**: Cookies come as one string, you need to split and parse them

```javascript
// Setting cookies
document.cookie = 'username=john; expires=Fri, 31 Dec 2024 23:59:59 GMT; path=/';
document.cookie = 'theme=dark; max-age=3600'; // Expires in 1 hour

// Reading cookies - need to parse
function getCookie(name) {
  const cookies = document.cookie.split(';');
  for (let cookie of cookies) {
    const [key, value] = cookie.trim().split('=');
    if (key === name) return value;
  }
  return null;
}

// Deleting cookies - set past expiration
document.cookie = 'username=; expires=Thu, 01 Jan 1970 00:00:00 GMT; path=/';
```

## 🔹8.Advanced Concepts**

###  130. What is functional programming in JavaScript? How is it different from object-oriented programming?

**Functional Programming:**
*  Treats computation as evaluation of mathematical functions
*  Emphasizes immutability and pure functions
*  Functions are first-class citizens
*  Avoids changing state and mutable data

```javascript
// Functional approach
const add = (a, b) => a + b;
const multiply = (a, b) => a * b;
const calculate = (x, y) => multiply(add(x, 2), y);

// Pure function - same input, same output
const users = [{name: 'John', age: 25}];
const addAge = (users, years) => 
  users.map(user => ({...user, age: user.age + years}));
```

**vs Object-Oriented:**
*  Organizes code around objects and classes
*  Uses encapsulation, inheritance, polymorphism
*  Focuses on objects that contain both data and methods

```javascript
// OOP approach
class Calculator {
  constructor() {
    this.result = 0;
  }
  
  add(value) {
    this.result += value;
    return this;
  }
  
  multiply(value) {
    this.result *= value;
    return this;
  }
}
```

###  131. What is an abstract class in JavaScript?

**Abstract Class Concept:**
*  JavaScript doesn't have built-in abstract classes
*  Can simulate using constructor checks or throwing errors
*  Defines interface that subclasses must implement
*  Cannot be instantiated directly

```javascript
// Simulated abstract class
class Animal {
  constructor() {
    if (this.constructor === Animal) {
      throw new Error("Cannot instantiate abstract class");
    }
  }
  
  // Abstract method
  makeSound() {
    throw new Error("Must implement makeSound method");
  }
  
  // Concrete method
  sleep() {
    console.log("Sleeping...");
  }
}

class Dog extends Animal {
  makeSound() {
    return "Woof!";
  }
}

// const animal = new Animal(); // Error!
const dog = new Dog(); // Works
```

## 🔹9.Performance Optimization**
###  132. What are memory leaks in JavaScript, and how do you prevent them?

**Memory Leaks:**
*  Occur when objects are no longer needed but not garbage collected
*  Common causes: global variables, event listeners, closures, timers

```javascript
// Memory leak examples
let globalVar = []; // Global variables persist

function createLeak() {
  const element = document.getElementById('button');
  element.onclick = function() {
    // Event listener not removed
  };
}

// Prevention techniques
function preventLeaks() {
  const element = document.getElementById('button');
  
  function handleClick() {
    console.log('Clicked');
  }
  
  element.addEventListener('click', handleClick);
  
  // Cleanup
  return function cleanup() {
    element.removeEventListener('click', handleClick);
  };
}

// Use WeakMap for weak references
const weakMap = new WeakMap();
```

###  133. What are decorators in JavaScript?

**Decorators:**
*  Experimental feature for modifying classes and methods
*  Currently stage 3 proposal, available with transpilers
*  Provide declarative way to modify behavior

```javascript
// Method decorator example
function log(target, propertyKey, descriptor) {
  const originalMethod = descriptor.value;
  
  descriptor.value = function(...args) {
    console.log(`Calling ${propertyKey} with`, args);
    return originalMethod.apply(this, args);
  };
  
  return descriptor;
}

class Calculator {
  @log
  add(a, b) {
    return a + b;
  }
}

// Class decorator
function sealed(constructor) {
  Object.seal(constructor);
  Object.seal(constructor.prototype);
}

@sealed
class Person {
  constructor(name) {
    this.name = name;
  }
}
```

###  134. How can you optimize the performance of a JavaScript application?

**Performance Optimization:**
*  Minimize DOM manipulation
*  Use efficient algorithms and data structures
*  Implement lazy loading and code splitting
*  Optimize loops and reduce function calls

```javascript
// Efficient DOM manipulation
const fragment = document.createDocumentFragment();
for (let i = 0; i < 1000; i++) {
  const div = document.createElement('div');
  fragment.appendChild(div);
}
document.body.appendChild(fragment); // Single DOM update

// Debouncing for performance
function debounce(func, delay) {
  let timeoutId;
  return function(...args) {
    clearTimeout(timeoutId);
    timeoutId = setTimeout(() => func.apply(this, args), delay);
  };
}

// Efficient array operations
const numbers = [1, 2, 3, 4, 5];
const doubled = numbers.map(n => n * 2); // Better than for loop for readability
```

###  135. What is the significance of `requestAnimationFrame()` in JavaScript?

**requestAnimationFrame:**
*  Optimizes animations by syncing with browser refresh rate
*  Typically 60fps (16.67ms intervals)
*  Pauses when tab is not visible
*  Better performance than setTimeout/setInterval

```javascript
// Smooth animation with requestAnimationFrame
function animate() {
  const element = document.getElementById('box');
  let position = 0;
  
  function frame() {
    position += 2;
    element.style.left = position + 'px';
    
    if (position < 300) {
      requestAnimationFrame(frame);
    }
  }
  
  requestAnimationFrame(frame);
}

// Performance comparison
function badAnimation() {
  setInterval(() => {
    // Runs regardless of browser refresh rate
    updatePosition();
  }, 16); // May not sync with display
}

function goodAnimation() {
  function update() {
    updatePosition();
    requestAnimationFrame(update);
  }
  requestAnimationFrame(update);
}
```

###  136. What is lazy loading in JavaScript?

**Lazy Loading:**
*  Delays loading of resources until they're needed
*  Improves initial page load performance
*  Common for images, modules, and components

```javascript
// Lazy loading images
const images = document.querySelectorAll('img[data-src]');

const imageObserver = new IntersectionObserver((entries) => {
  entries.forEach(entry => {
    if (entry.isIntersecting) {
      const img = entry.target;
      img.src = img.dataset.src;
      img.removeAttribute('data-src');
      imageObserver.unobserve(img);
    }
  });
});

images.forEach(img => imageObserver.observe(img));

// Lazy loading modules
async function loadModule() {
  const module = await import('./heavyModule.js');
  return module.default;
}

// Component lazy loading
function LazyComponent() {
  return new Promise(resolve => {
    setTimeout(() => {
      resolve(document.createElement('div'));
    }, 100);
  });
}
```

###  137. How do you improve the rendering performance of a website using JavaScript?

**Rendering Performance:**
*  Minimize reflows and repaints
*  Use CSS transforms instead of changing layout properties
*  Batch DOM reads and writes
*  Use virtual scrolling for large lists

```javascript
// Avoid layout thrashing
function badPerformance() {
  const element = document.getElementById('box');
  element.style.left = '100px'; // Causes reflow
  element.style.top = '100px';  // Causes reflow
}

function goodPerformance() {
  const element = document.getElementById('box');
  element.style.transform = 'translate(100px, 100px)'; // Single composite
}

// Batch DOM operations
function batchOperations() {
  const elements = document.querySelectorAll('.item');
  const heights = []; // Read phase
  
  elements.forEach(el => heights.push(el.offsetHeight));
  
  // Write phase
  elements.forEach((el, i) => {
    el.style.height = (heights[i] * 2) + 'px';
  });
}

// Virtual scrolling example
class VirtualList {
  constructor(container, items, itemHeight) {
    this.container = container;
    this.items = items;
    this.itemHeight = itemHeight;
    this.visibleItems = Math.ceil(container.clientHeight / itemHeight);
  }
  
  render(scrollTop) {
    const startIndex = Math.floor(scrollTop / this.itemHeight);
    const endIndex = startIndex + this.visibleItems;
    
    // Only render visible items
    return this.items.slice(startIndex, endIndex);
  }
}
```

## 🔹10.Frameworks and Libraries**
###  138. What are JavaScript frameworks, and how do they differ from libraries?

**Frameworks vs Libraries:**

**Framework:**
*  Provides structure and controls application flow
*  You write code that framework calls (Inversion of Control)
*  Examples: Angular, Vue.js, Svelte

**Library:**
*  Collection of functions you call when needed
*  You control when and how to use it
*  Examples: React, Lodash, jQuery

```javascript
// Library example (React)
import React from 'react';

function MyComponent() {
  return React.createElement('div', null, 'Hello World');
  // You call React functions
}

// Framework example (Angular-style)
class MyController {
  constructor() {
    this.message = 'Hello World';
  }
  
  // Framework calls your methods
  ngOnInit() {
    console.log('Component initialized');
  }
}

// Library usage pattern
const _ = require('lodash');
const result = _.map([1, 2, 3], n => n * 2); // You call library

// Framework usage pattern
// Framework defines structure, you fill in the blanks
const app = new Framework({
  routes: {
    '/home': HomeComponent,
    '/about': AboutComponent
  },
  // Framework manages lifecycle
});
```

**Key Differences:**
*  **Control**: Framework controls flow, library is controlled by you
*  **Structure**: Framework provides architecture, library provides utilities
*  **Learning curve**: Frameworks often steeper, libraries more flexible
*  **Size**: Frameworks typically larger, libraries can be minimal


## 🔹11.Testing and Tools**

###  139. What is unit testing in JavaScript?

*  Unit testing means testing individual pieces of code in isolation
*  You test one function or component at a time to make sure it works correctly
*  It helps catch bugs early and makes your code more reliable
*  Think of it like testing each part of a car engine separately before putting it all together

```javascript
// Function to test
function add(a, b) {
  return a + b;
}

// Simple unit test
function testAdd() {
  const result = add(2, 3);
  if (result === 5) {
    console.log('Test passed!');
  } else {
    console.log('Test failed!');
  }
}
```

###  140. What are some popular testing frameworks in JavaScript?

*  **Jest** - Most popular, works great with React, has built-in mocking
*  **Mocha** - Flexible framework, you can choose your own assertion library
*  **Jasmine** - Behavior-driven development style, good for beginners
*  **Cypress** - End-to-end testing, tests real user interactions
*  **Vitest** - Fast and modern, great for Vite projects

```javascript
// Jest example
test('adds 1 + 2 to equal 3', () => {
  expect(add(1, 2)).toBe(3);
});

// Mocha example
describe('Calculator', () => {
  it('should add two numbers', () => {
    assert.equal(add(1, 2), 3);
  });
});
```

###  141. What is TDD (Test-Driven Development)?

*  Write the test first, then write the code to make it pass
*  It's like writing the answer key before writing the exam
*  Red-Green-Refactor cycle: fail, pass, improve
*  Helps you think about what your code should do before writing it
*  Results in better design and fewer bugs

```javascript
// Step 1: Write failing test
test('multiply should return product of two numbers', () => {
  expect(multiply(3, 4)).toBe(12);
});

// Step 2: Write minimal code to pass
function multiply(a, b) {
  return a * b;
}

// Step 3: Refactor if needed
```

###  142. How do you write asynchronous tests in JavaScript?

*  Use async/await in your test functions
*  Return promises from your tests
*  Use done callbacks for older frameworks
*  Test both success and error cases
*  Mock external APIs to avoid real network calls

```javascript
// Jest with async/await
test('fetches user data', async () => {
  const userData = await fetchUser(1);
  expect(userData.name).toBe('John');
});

// Testing promises
test('promise resolves with correct value', () => {
  return fetchData().then(data => {
    expect(data).toBe('expected value');
  });
});

// Testing errors
test('handles API errors', async () => {
  await expect(fetchInvalidUser()).rejects.toThrow('User not found');
});
```

###  143. What is the difference between `assert` and `expect` in JavaScript testing?

*  **Assert** - Traditional style, throws errors when conditions fail
*  **Expect** - More readable, chainable methods, better error messages
*  Assert is more basic, expect is more expressive
*  Expect allows fluent syntax like "expect(x).to.be.true"
*  Most modern frameworks prefer expect style

```javascript
// Assert style (Node.js built-in)
const assert = require('assert');
assert.equal(actual, expected);
assert.strictEqual(actual, expected);
assert.throws(() => { throw new Error(); });

// Expect style (Jest, Chai)
expect(actual).toBe(expected);
expect(actual).toEqual(expected);
expect(() => { throw new Error(); }).toThrow();
expect(array).toContain(item);
```

## 🔹12.Advanced JavaScript Features**

###  144. How do you use regular expressions in JavaScript?

*  RegEx helps you find and match patterns in text
*  Use forward slashes or RegExp constructor
*  Common methods: test(), match(), replace(), search()
*  Flags like 'g' for global, 'i' for case-insensitive
*  Great for validation, searching, and text manipulation

```javascript
// Creating regex
const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
const phonePattern = new RegExp('\\d{3}-\\d{3}-\\d{4}');

// Testing patterns
const isValidEmail = emailPattern.test('user@example.com'); // true

// Finding matches
const text = 'Call me at 123-456-7890';
const phoneNumber = text.match(/\d{3}-\d{3}-\d{4}/); // ['123-456-7890']

// Replacing text
const cleaned = 'Hello World!'.replace(/[!@#$%^&*]/g, ''); // 'Hello World'
```

###  145. What are Web Workers in JavaScript?

*  Web Workers run JavaScript in background threads
*  They don't block the main UI thread
*  Perfect for heavy computations or data processing
*  Can't directly access DOM, communicate via messages
*  Great for keeping your app responsive during intensive tasks

```javascript
// main.js
const worker = new Worker('worker.js');

// Send data to worker
worker.postMessage({numbers: [1, 2, 3, 4, 5]});

// Receive result from worker
worker.onmessage = function(e) {
  console.log('Result:', e.data.sum);
};

// worker.js
self.onmessage = function(e) {
  const numbers = e.data.numbers;
  const sum = numbers.reduce((a, b) => a + b, 0);
  self.postMessage({sum: sum});
};
```

###  146. What are service workers in JavaScript, and how do they work?

*  Service workers are scripts that run in the background
*  They act like a proxy between your app and the network
*  Enable offline functionality and push notifications
*  Can cache resources for faster loading
*  Essential for Progressive Web Apps (PWAs)

```javascript
// Register service worker
if ('serviceWorker' in navigator) {
  navigator.serviceWorker.register('/sw.js');
}

// sw.js - Service Worker file
self.addEventListener('install', event => {
  event.waitUntil(
    caches.open('v1').then(cache => {
      return cache.addAll(['/index.html', '/styles.css']);
    })
  );
});

self.addEventListener('fetch', event => {
  event.respondWith(
    caches.match(event.request).then(response => {
      return response || fetch(event.request);
    })
  );
});
```

###  147. How do you compare two objects in JavaScript?

*  Objects are compared by reference, not by value
*  Use JSON.stringify() for simple deep comparison
*  Write custom functions for complex comparisons
*  Libraries like Lodash provide isEqual() method
*  Be careful with nested objects and arrays

```javascript
// Reference comparison (usually false)
const obj1 = {name: 'John'};
const obj2 = {name: 'John'};
console.log(obj1 === obj2); // false

// JSON stringify method (simple cases)
function compareObjects(a, b) {
  return JSON.stringify(a) === JSON.stringify(b);
}

// Custom deep comparison
function deepEqual(a, b) {
  if (a === b) return true;
  if (typeof a !== 'object' || typeof b !== 'object') return false;
  
  const keysA = Object.keys(a);
  const keysB = Object.keys(b);
  
  if (keysA.length !== keysB.length) return false;
  
  return keysA.every(key => deepEqual(a[key], b[key]));
}
```

###  148. What are the differences between a function expression and a function declaration?

*  **Function declarations** are hoisted, available before they're defined
*  **Function expressions** are not hoisted, only available after assignment
*  Declarations create named functions, expressions can be anonymous
*  Expressions are often used in callbacks and assignments
*  Both create functions, just different timing and syntax

```javascript
// Function Declaration - hoisted
console.log(declared()); // Works! Returns "Hello"

function declared() {
  return "Hello";
}

// Function Expression - not hoisted
console.log(expressed()); // Error! Cannot access before initialization

const expressed = function() {
  return "Hello";
};

// Arrow function expression
const arrow = () => "Hello";

// Named function expression
const named = function myFunction() {
  return "Hello";
};
```

###  149. What is the difference between `slice()` and `splice()` methods in JavaScript?

*  **slice()** extracts a portion without changing the original array
*  **splice()** modifies the original array by adding/removing elements
*  slice() returns a new array, splice() returns removed elements
*  slice() is non-destructive, splice() is destructive
*  Remember: slice is safe, splice changes things

```javascript
const fruits = ['apple', 'banana', 'orange', 'grape'];

// slice() - non-destructive
const sliced = fruits.slice(1, 3); // ['banana', 'orange']
console.log(fruits); // ['apple', 'banana', 'orange', 'grape'] - unchanged

// splice() - destructive
const removed = fruits.splice(1, 2, 'kiwi', 'mango');
console.log(removed); // ['banana', 'orange'] - removed elements
console.log(fruits); // ['apple', 'kiwi', 'mango', 'grape'] - modified

// Common use cases
const copy = arr.slice(); // Copy entire array
arr.splice(2, 0, 'new item'); // Insert at index 2
arr.splice(-1, 1); // Remove last element
```

## 🔹13.JavaScript Best Practices**

###  150. What is the importance of code minification in JavaScript?

*  **Reduces file size** - Removes whitespace, comments, and shortens variable names
*  **Faster loading** - Smaller files download quicker, improving page performance
*  **Bandwidth savings** - Less data transfer, especially important for mobile users
*  **Production optimization** - Essential for deployment, not development

```javascript
// Before minification
function calculateTotal(price, tax) {
    const taxAmount = price * tax;
    return price + taxAmount;
}

// After minification
function calculateTotal(a,b){return a+a*b}
```

---

###  151. How do you handle large-scale JavaScript applications?

*  **Modular architecture** - Break code into small, reusable modules
*  **Code splitting** - Load only what's needed when needed
*  **State management** - Use Redux, Vuex, or similar for complex state
*  **Build tools** - Webpack, Vite for bundling and optimization

```javascript
// Module structure
// utils/api.js
export const fetchData = async (url) => {
    return await fetch(url).then(res => res.json());
};

// components/UserList.js
import { fetchData } from '../utils/api.js';
export default class UserList {
    async loadUsers() {
        return await fetchData('/api/users');
    }
}
```

---

###  152. What are some security considerations when working with JavaScript?

*  **Input validation** - Always sanitize user input to prevent XSS attacks
*  **Avoid eval()** - Never use eval() with user-provided data
*  **HTTPS only** - Secure data transmission
*  **Content Security Policy** - Restrict script sources

```javascript
// Bad - XSS vulnerable
document.innerHTML = userInput;

// Good - Safe approach
const div = document.createElement('div');
div.textContent = userInput; // Automatically escapes HTML
document.body.appendChild(div);

// Input validation
function validateEmail(email) {
    const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return regex.test(email);
}
```

---

###  153. How do you manage JavaScript dependencies in a project?

*  **Package managers** - Use npm or yarn for dependency management
*  **Lock files** - package-lock.json ensures consistent installs
*  **Version control** - Specify exact versions to avoid breaking changes
*  **Regular updates** - Keep dependencies current for security

```javascript
// package.json
{
  "dependencies": {
    "lodash": "^4.17.21",
    "axios": "~1.6.0"
  },
  "devDependencies": {
    "webpack": "^5.88.0"
  }
}

// Install and use
npm install lodash
import _ from 'lodash';
const users = _.uniqBy(userArray, 'id');
```

---

###  154. What is the importance of modularity in JavaScript development?

*  **Reusability** - Write once, use everywhere
*  **Maintainability** - Easier to debug and update isolated modules
*  **Testing** - Smaller modules are easier to unit test
*  **Team collaboration** - Different developers can work on separate modules

```javascript
// auth.js module
export const auth = {
    login: (credentials) => fetch('/login', { method: 'POST', body: credentials }),
    logout: () => localStorage.removeItem('token'),
    isAuthenticated: () => !!localStorage.getItem('token')
};

// main.js
import { auth } from './auth.js';
if (auth.isAuthenticated()) {
    loadDashboard();
}
```

---

###  155. How do you document JavaScript code effectively?

*  **JSDoc comments** - Standard documentation format for functions
*  **README files** - Project overview and setup instructions
*  **Inline comments** - Explain complex logic, not obvious code
*  **Type annotations** - Use TypeScript or JSDoc for better clarity

```javascript
/**
 * Calculates the total price including tax
 * @param {number} price - The base price
 * @param {number} taxRate - Tax rate as decimal (0.1 for 10%)
 * @returns {number} Total price with tax
 * @example
 * calculateTotal(100, 0.1); // Returns 110
 */
function calculateTotal(price, taxRate) {
    return price * (1 + taxRate);
}

// Good inline comment
const users = data.filter(user => user.active && user.verified); // Only active verified users
```

---

###  156. How do you ensure cross-browser compatibility with JavaScript?

*  **Feature detection** - Check if features exist before using them
*  **Polyfills** - Add missing functionality for older browsers
*  **Transpilation** - Use Babel to convert modern JS to older syntax
*  **Testing** - Test on multiple browsers and versions

```javascript
// Feature detection
if ('fetch' in window) {
    fetch('/api/data');
} else {
    // Fallback to XMLHttpRequest
    const xhr = new XMLHttpRequest();
    xhr.open('GET', '/api/data');
}

// Polyfill example
if (!Array.prototype.includes) {
    Array.prototype.includes = function(searchElement) {
        return this.indexOf(searchElement) !== -1;
    };
}
```

---

###  157. What are design patterns in JavaScript?

*  **Module Pattern** - Encapsulate code and create private variables
*  **Observer Pattern** - Event-driven communication between objects
*  **Singleton Pattern** - Ensure only one instance exists
*  **Factory Pattern** - Create objects without specifying exact classes

```javascript
// Module Pattern
const Calculator = (function() {
    let result = 0; // Private variable
    
    return {
        add: (x) => result += x,
        subtract: (x) => result -= x,
        getResult: () => result
    };
})();

// Observer Pattern
class EventEmitter {
    constructor() {
        this.events = {};
    }
    
    on(event, callback) {
        if (!this.events[event]) this.events[event] = [];
        this.events[event].push(callback);
    }
    
    emit(event, data) {
        if (this.events[event]) {
            this.events[event].forEach(callback => callback(data));
        }
    }
}
```

###  **JavaScript Okta API**
###  1. **How does authentication with Okta work in a frontend application?**

**What interviewers look for:**

* Redirect-based login (Authorization Code Flow + PKCE)
* User authenticates with Okta → tokens returned → frontend consumes ID/Access token
* Separation of auth (who you are) vs authorization (what you can access)

---

###  2. **Which OAuth 2.0 / OIDC flow would you use for a SPA and why?**

**Expected answer:**

* **Authorization Code Flow with PKCE**
* More secure than Implicit Flow
* Tokens never exposed in URL
* Recommended by Okta for SPAs

---

###  3. **How do you handle access tokens and refresh tokens securely in the frontend?**

**Key points:**

* Store tokens **in memory** or **httpOnly cookies**
* Avoid localStorage/sessionStorage when possible
* Use refresh tokens with rotation (if allowed)
* Handle token expiration and silent renewal

---

###  4. **How is SAML different from OIDC, and when would you use each?**

**Expected explanation:**

| SAML                  | OIDC                          |
| --------------------- | ----------------------------- |
| XML-based             | JSON / JWT                    |
| Uses assertions       | Uses ID tokens                |
| Older, enterprise SSO | Modern, mobile & SPA friendly |
| Heavy redirects       | Lightweight REST              |

**Migration note:**

* SiteMinder → Okta often means **SAML → OIDC**

---

###  5. **How would you migrate SiteMinder policies to Okta?**

**Frontend-focused answer:**

* Understand existing SiteMinder auth rules (URLs, roles, headers)
* Map them to:

  * Okta Groups
  * Claims in ID/Access Tokens
  * App Sign-On policies
* Update frontend to consume **claims instead of headers**

---

###  6. **How do you implement role-based or attribute-based authorization in the frontend?**

**Expected approach:**

* Decode JWT access/ID token
* Read claims like:

  * `groups`
  * `roles`
  * `permissions`
* Conditionally:

  * Show/hide UI elements
  * Protect routes
* Backend remains the source of truth

---

###  7. **How does the frontend integrate with platform services using Okta-issued tokens?**

**Key points:**

* Frontend sends **access token** in `Authorization: Bearer`
* APIs validate token via:

  * JWT signature
  * Issuer & audience
  * Scopes/claims
* Use different tokens for different resource servers

---

###  8. **How do you handle session management and logout in Okta?**

**Expected answer:**

* Local session (app state) + Okta session
* Logout flow:

  * Clear frontend tokens
  * Call Okta `/logout` endpoint
* Handle:

  * Single Logout (SLO)
  * Session timeout
  * Forced re-authentication
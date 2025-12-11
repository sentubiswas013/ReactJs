
## **Basic JavaScript Concepts**
### 1. What are the different data types in JavaScript?

JavaScript has 8 data types: 7 primitives and 1 non-primitive. Primitives are `string`, `number`, `boolean`, `undefined`, `null`, `symbol`, and `bigint`. The non-primitive is `object`, `arrays`, and `functions`.

```javascript
let str = "Hello";        // string
let num = 42;            // number
let bool = true;         // boolean
let obj = {name: "John"}; // object
```

### 2. What is the difference between `var`, `let`, and `const`?

`var` is function-scoped and hoisted, `let` and `const` are block-scoped. `const` cannot be reassigned after declaration, while `let` can be. `var` can be redeclared, but `let` and `const` cannot.

```javascript
var x = 1;    // function-scoped, hoisted
let y = 2;    // block-scoped, can reassign
const z = 3;  // block-scoped, cannot reassign
```

### 3. What is a closure in JavaScript?

A closure is when an inner function has access to variables from its outer function's scope even after the outer function has finished executing. It "closes over" the variables.

```javascript
function outer(x) {
  return function inner(y) {
    return x + y; // inner accesses outer's x
  };
}
const add5 = outer(5);
console.log(add5(3)); // 8
```

### 4. What is the difference between `==` and `===` in JavaScript?

`==` performs type coercion before comparison, while `===` checks both value and type without conversion. Always prefer `===` for strict equality checks.

```javascript
"5" == 5   // true (type coercion)
"5" === 5  // false (different types)
null == undefined  // true
null === undefined // false
```

### 5. Explain the concept of "truthy" and "falsy" values in JavaScript.

Falsy values are `false`, `0`, `-0`, `0n`, `""`, `null`, `undefined`, and `NaN`. Everything else is truthy. These are used in boolean contexts like if statements.

```javascript
if ("") console.log("Won't run"); // falsy
if ("hello") console.log("Will run"); // truthy
if (0) console.log("Won't run"); // falsy
if (42) console.log("Will run"); // truthy
```

### 6. What are the JavaScript data structures, and when would you use them?

Main data structures: Arrays for ordered lists, Objects for key-value pairs, Maps for any key types, Sets for unique values, WeakMap/WeakSet for garbage collection benefits.

```javascript
let arr = [1, 2, 3];           // Array
let obj = {name: "John"};      // Object
let map = new Map();           // Map
let set = new Set([1, 2, 2]);  // Set (removes duplicates)
```

### 7. What is the `undefined` value in JavaScript?

`undefined` means a variable has been declared but not assigned a value, or a function doesn't return anything. It's JavaScript's default value for uninitialized variables.

```javascript
let x;              // undefined
function test() {}  // returns undefined
let obj = {};
console.log(obj.missing); // undefined
```

### 8. What is the `null` value in JavaScript? How is it different from `undefined`?

`null` is an intentional absence of value, while `undefined` means not yet assigned. `null` is explicitly set by developers, `undefined` is JavaScript's default.

```javascript
let empty = null;      // intentionally empty
let notSet;           // undefined by default
console.log(typeof null);      // "object" (quirk)
console.log(typeof undefined); // "undefined"
```

### 9. What are the different ways to create objects in JavaScript?

You can create objects using object literals, constructor functions, `Object.create()`, or ES6 classes. Each method has different use cases and inheritance patterns.

```javascript
let obj1 = {name: "John"};              // literal
let obj2 = new Object();                // constructor
let obj3 = Object.create(null);         // Object.create
class Person { constructor(name) { this.name = name; } }
let obj4 = new Person("John");          // class
```

### 10. What is hoisting in JavaScript?

Hoisting moves variable and function declarations to the top of their scope during compilation. `var` declarations are hoisted and initialized with `undefined`, while `let`/`const` are hoisted but not initialized.

```javascript
console.log(x); // undefined (not error)
var x = 5;

console.log(y); // ReferenceError
let y = 10;

sayHello(); // "Hello!" (works)
function sayHello() { console.log("Hello!"); }
```

### 11. What is a pure function in JavaScript? Can you give an example?

A pure function always returns the same output for the same input and has no side effects. It doesn't modify external state or depend on external variables.

```javascript
// Pure function
function add(a, b) {
  return a + b;
}

// Impure function (side effect)
let count = 0;
function increment() {
  count++; // modifies external state
  return count;
}
```

### 12. What are higher-order functions in JavaScript?

Higher-order functions either take other functions as arguments or return functions. They're fundamental to functional programming and are used extensively with arrays.

```javascript
// Takes function as argument
function applyOperation(arr, operation) {
  return arr.map(operation);
}

// Returns function
function multiplier(factor) {
  return function(num) { return num * factor; };
}

const double = multiplier(2);
console.log(double(5)); // 10

[1,2,3].map(x => x*2); // map is a HOF
```

### 13. What is a callback function in JavaScript?

A callback is a function passed as an argument to another function and executed later. It's used for asynchronous operations, event handling, and functional programming patterns.

```javascript
function processData(data, callback) {
  const result = data.toUpperCase();
  callback(result);
}

processData("hello", function(result) {
  console.log(result); // "HELLO"
});

// Common with arrays
[1, 2, 3].forEach(function(item) {
  console.log(item);
});
```

### 14. What are closures in JavaScript, and why are they important?

A closure happens when a function "remembers" variables from its outer scope even after the outer function has finished.
They're crucial for data privacy, module patterns, and maintaining state in functional programming.

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
// count is private and persistent
```

### 15. How does the `this` keyword work in JavaScript?

`this` refers to the object that calls the function. In regular functions, it depends on how the function is called. In arrow functions, `this` is lexically bound to the surrounding scope.

```javascript
const obj = {
  name: "John",
  greet: function() {
    console.log(this.name); // "John"
  },
  arrowGreet: () => {
    console.log(this.name); // undefined (lexical this)
  }
};

obj.greet();      // this = obj
obj.arrowGreet(); // this = global/window
```

### 16. What is the difference between synchronous and asynchronous code in JavaScript?

Synchronous code executes line by line, blocking the next line until current one finishes. Asynchronous code doesn't block - it starts an operation and continues executing while waiting for the result.

```javascript
// Synchronous
console.log("First");
console.log("Second"); // waits for first to complete

// Asynchronous
console.log("Start");
setTimeout(() => console.log("Async"), 0);
console.log("End"); // Output: Start, End, Async
```

### 17. What is the purpose of the `call()`, `apply()` and `bind()` methods in JavaScript?

These methods control the `this` context. `call()` invokes immediately with arguments, `apply()` invokes with an array of arguments, `bind()` returns a new function with bound `this`.

```javascript
const obj = {name: "John"};

function greet(age, city) {
  console.log(`${this.name}, ${age}, ${city}`);
}

greet.call(obj, 25, "NYC");        // John, 25, NYC
greet.apply(obj, [25, "NYC"]);     // John, 25, NYC
const boundGreet = greet.bind(obj);
boundGreet(25, "NYC");             // John, 25, NYC
```

### 18. What is `async/await` in JavaScript?

`async/await` is syntactic sugar for promises that makes asynchronous code look synchronous. `async` functions always return promises, `await` pauses execution until the promise resolves.

```javascript
// Promise way
function fetchData() {
  return fetch('/api/data').then(res => res.json());
}

// Async/await way
async function fetchData() {
  const response = await fetch('/api/data');
  return await response.json();
}
```

### 19. What is a promise in JavaScript? How does it work?

A promise represents the eventual completion of an asynchronous operation. It has three states: pending, fulfilled, or rejected. You handle results with `.then()` and errors with `.catch()`.

```javascript
const promise = new Promise((resolve, reject) => {
  setTimeout(() => {
    Math.random() > 0.5 ? resolve("Success!") : reject("Failed!");
  }, 1000);
});

promise
  .then(result => console.log(result))
  .catch(error => console.log(error));
```

### 20. What is a promise chain in JavaScript?

Promise chaining allows you to perform sequential asynchronous operations by returning promises from `.then()` handlers. Each `.then()` receives the result of the previous promise.

```javascript
fetch('/api/user')
  .then(response => response.json())
  .then(user => fetch(`/api/posts/${user.id}`))
  .then(response => response.json())
  .then(posts => console.log(posts))
  .catch(error => console.log(error));
```

### 21. What are the states of a promise?

A promise has three states: **Pending** (initial state), **Fulfilled** (operation completed successfully), and **Rejected** (operation failed). Once settled (fulfilled or rejected), a promise cannot change state.

```javascript
const pending = new Promise(() => {}); // stays pending
const fulfilled = Promise.resolve("Success");
const rejected = Promise.reject("Error");

console.log(pending);   // Promise {<pending>}
console.log(fulfilled); // Promise {<fulfilled>: "Success"}
console.log(rejected);  // Promise {<rejected>: "Error"}
```

### 22. What is the difference between `async/await` and promises?

**Promise** is the primitive — an object representing a future value. 
**async/await** is syntax sugar over Promises that makes code easier to read and reason about; `await` pauses inside an `async` function and returns the resolved value or throws on rejection.

```javascript
// Promises
function getData() {
  return fetch('/api')
    .then(res => res.json())
    .catch(err => console.log(err));
}

// Async/await
async function getData() {
  try {
    const res = await fetch('/api');
    return await res.json();
  } catch (err) {
    console.log(err);
  }
}
```

### 23. What is the difference between callback and promise?

Callbacks are functions passed to other functions, leading to callback hell with nested operations. Promises provide better error handling, chaining, and avoid the pyramid of doom structure.

```javascript
// Callback hell
getData(function(a) {
  getMoreData(a, function(b) {
    getEvenMoreData(b, function(c) {
      // nested callbacks
    });
  });
});

// Promise chain
getData()
  .then(a => getMoreData(a))
  .then(b => getEvenMoreData(b))
  .then(c => console.log(c));
```

### 24. What is an observable?

An observable is a data stream that can emit multiple values over time, unlike promises which resolve once. Observables are lazy, cancellable, and support operators for data transformation. They're used in RxJS.

```javascript
// RxJS Observable
import { Observable } from 'rxjs';

const observable = new Observable(observer => {
  observer.next(1);
  observer.next(2);
  observer.complete();
});

observable.subscribe(value => console.log(value)); // 1, 2
```

### 25. What are the differences between promises and observables?

Promises handle single async values and are eager (execute immediately). Observables handle multiple values over time, are lazy (execute on subscription), cancellable, and provide rich operators for data transformation.

```javascript
// Promise - single value, eager
const promise = fetch('/api'); // executes immediately

// Observable - multiple values, lazy
const observable = new Observable(observer => {
  setInterval(() => observer.next(Date.now()), 1000);
}); // only executes when subscribed
```

### 26. What is the concept of currying in JavaScript? Can you provide an example?

Currying transforms a function with multiple arguments into a sequence of functions, each taking a single argument. It enables partial application and function composition.

```javascript
// Regular function
function add(a, b, c) {
  return a + b + c;
}

// Curried function
function curriedAdd(a) {
  return function(b) {
    return function(c) {
      return a + b + c;
    };
  };
}

const add5 = curriedAdd(5);
const add5And3 = add5(3);
console.log(add5And3(2)); // 10
```

### 27. What is an anonymous function?

An anonymous function is a function without a name. They're commonly used as callbacks, event handlers, or immediately invoked function expressions (IIFEs).

```javascript
// Anonymous function as callback
[1, 2, 3].map(function(x) { return x * 2; });

// Anonymous arrow function
[1, 2, 3].map(x => x * 2);

// Anonymous IIFE
(function() {
  console.log("Executed immediately");
})();
```

### 28. Explain event delegation in JavaScript.

Event delegation uses event bubbling to handle events on parent elements instead of individual child elements. It's efficient for dynamic content and reduces memory usage by having fewer event listeners.

```javascript
// Instead of adding listeners to each button
document.getElementById('container').addEventListener('click', function(e) {
  if (e.target.tagName === 'BUTTON') {
    console.log('Button clicked:', e.target.textContent);
  }
});

// Works for dynamically added buttons too
```

### 29. What is a "call stack"?

The call stack is a data structure that tracks function calls in JavaScript. When a function is called, it's pushed onto the stack. When it returns, it's popped off. Stack overflow occurs when too many functions are called without returning.

```javascript
function first() {
  console.log("First");
  second();
}

function second() {
  console.log("Second");
  third();
}

function third() {
  console.log("Third");
}

first(); // Call stack: first -> second -> third -> second -> first
```

### 30. What is the event loop in JavaScript?

The event loop enables JavaScript's non-blocking behavior by managing the call stack, callback queue, and microtask queue. It continuously checks if the call stack is empty and processes queued callbacks.

```javascript
console.log("1");

setTimeout(() => console.log("2"), 0); // macro task

Promise.resolve().then(() => console.log("3")); // micro task

console.log("4");

// Output: 1, 4, 3, 2
// Microtasks (promises) have higher priority than macrotasks (setTimeout)
```

### 31. What is event propagation in JavaScript?

Event propagation is the process of how events travel through the DOM tree. It has three phases: capturing (top to target), target (at the element), and bubbling (target to top). Most events bubble by default.

```javascript
document.getElementById('parent').addEventListener('click', () => {
  console.log('Parent clicked');
});

document.getElementById('child').addEventListener('click', () => {
  console.log('Child clicked');
});
// Clicking child logs: "Child clicked", "Parent clicked"
```

### 32. What is event Bubbling, Capturing, Delegation in JavaScript?

Event bubbling means events start at the target element and bubble up to parent elements. Event capturing is the opposite - events start at the root and capture down to the target element.

**Bubbling** — event goes from the target element up the DOM tree (child → parent → document). Default behavior for most events.

**Capturing** — event goes from the root down to the target (document → parent → child). To use it, pass `{ capture: true }` or `true` to `addEventListener`.

**Event Delegation** — attach one handler to a parent and let it handle events for many child elements using `event.target` or `closest()`. Efficient for dynamic lists.

```html
<ul id="list">
  <li data-id="1">Item 1</li>
  <li data-id="2">Item 2</li>
</ul>
```

```javascript
const list = document.getElementById('list');
list.addEventListener('click', (e) => {
  const li = e.target.closest('li');
  if (!li) return;
  console.log('clicked id:', li.dataset.id);
});
```

**How to say it aloud:** "Capturing goes top-down, bubbling goes bottom-up. Delegation uses one listener on a parent to manage many child events — great for performance and dynamic elements."

### 33. How do you prevent the default action of an event in JavaScript?

Use `preventDefault()` method to stop the browser's default behavior for an event, like preventing form submission or link navigation. This doesn't stop event propagation.

```javascript
// Prevent form submission
form.addEventListener('submit', (e) => {
  e.preventDefault();
  // Custom validation logic
});

// Prevent link navigation
link.addEventListener('click', (e) => {
  e.preventDefault();
  // Custom navigation logic
});
```

### 34. How do you attach multiple event listeners to the same event?

You can add multiple event listeners to the same event using `addEventListener()` multiple times. Each listener will execute in the order they were added.

```javascript
const button = document.getElementById('btn');

button.addEventListener('click', () => console.log('First'));
button.addEventListener('click', () => console.log('Second'));
button.addEventListener('click', () => console.log('Third'));

// Clicking button logs: "First", "Second", "Third"
```

### 35. What is the concept of memoization in JavaScript?

Memoization is an optimization technique that caches function results based on input parameters. It improves performance for expensive computations by avoiding redundant calculations.

```javascript
function memoize(fn) {
  const cache = {};
  return function(...args) {
    const key = JSON.stringify(args);
    if (key in cache) return cache[key];
    cache[key] = fn.apply(this, args);
    return cache[key];
  };
}

const expensiveFunction = memoize((n) => {
  console.log('Computing...');
  return n * n;
});
```

### 36. What is Debouncing and Throttling in JavaScript?
**Debouncing** means: *“Wait until the user stops doing something, then run the function.”*
Example: Search input suggestions — don’t call API on every keystroke.

```js
function debounce(fn, delay) {
  let timer;
  return function (...args) {
    clearTimeout(timer);
    timer = setTimeout(() => fn.apply(this, args), delay);
  };
}

const search = debounce(() => console.log("API call"), 300);
```

**Throttling** means: *“Run the function only once in a fixed time window.”*
Example: Handling scroll or resize events.

```js
function throttle(fn, delay) {
  let last = 0;
  return function (...args) {
    const now = Date.now();
    if (now - last >= delay) {
      fn.apply(this, args);
      last = now;
    }
  };
}

const onScroll = throttle(() => console.log("scroll!"), 500);
```

## **Object-Oriented Programming in JavaScript**
### 37. What are Object-Oriented Programming (OOP) concepts in JavaScript?

JavaScript supports four main OOP concepts: Encapsulation (bundling data and methods), Inheritance (extending functionality), Polymorphism (same interface, different implementations), and Abstraction (hiding complexity).

* **Class** — blueprint for objects.
* **Object** — instance of a class.
* **Encapsulation** — hide internal state, expose methods.
* **Inheritance** — subclass shares behavior from parent (`extends`).
* **Polymorphism** — same interface, different implementations.
* **Abstraction** — expose essential features, hide complexity.

```javascript
class Animal {
  constructor(name) {
    this.name = name; // Encapsulation
  }
  
  speak() { // Abstraction
    throw new Error('Must implement speak method');
  }
}

class Dog extends Animal { // Inheritance
  speak() { return `${this.name} barks`; } // Polymorphism
}
```

### 38. What are classes and objects in JavaScript?

Classes are blueprints for creating objects with shared properties and methods. Objects are instances of classes containing actual data. ES6 introduced class syntax as syntactic sugar over prototype-based inheritance.

```javascript
class Person {
  constructor(name, age) {
    this.name = name;
    this.age = age;
  }
  
  greet() {
    return `Hello, I'm ${this.name}`;
  }
}

const john = new Person('John', 30); // Object instance
console.log(john.greet()); // "Hello, I'm John"
```

### 39. What is encapsulation in JavaScript?

Encapsulation bundles data and methods together while controlling access to internal state. JavaScript uses closures, private fields (#), or conventions (_) to achieve data privacy and protect object integrity.

```javascript
class BankAccount {
  #balance = 0; // Private field
  
  deposit(amount) {
    if (amount > 0) this.#balance += amount;
  }
  
  getBalance() {
    return this.#balance; // Controlled access
  }
}

const account = new BankAccount();
// account.#balance; // SyntaxError - private field
```

### 40. What is inheritance in JavaScript?

Inheritance allows objects to inherit properties and methods from parent objects. JavaScript uses prototype-based inheritance or ES6 class syntax with `extends` keyword to create parent-child relationships.

```javascript
class Vehicle {
  constructor(brand) {
    this.brand = brand;
  }
  
  start() {
    return `${this.brand} is starting`;
  }
}

class Car extends Vehicle {
  constructor(brand, model) {
    super(brand); // Call parent constructor
    this.model = model;
  }
}

const myCar = new Car('Toyota', 'Camry');
```

### 41. What is polymorphism in JavaScript?

Polymorphism allows different objects to respond to the same method call in their own way. Objects can have the same interface but different implementations, enabling flexible and reusable code.

```javascript
class Shape {
  area() {
    throw new Error('Must implement area method');
  }
}

class Circle extends Shape {
  constructor(radius) { super(); this.radius = radius; }
  area() { return Math.PI * this.radius ** 2; }
}

class Rectangle extends Shape {
  constructor(width, height) { super(); this.width = width; this.height = height; }
  area() { return this.width * this.height; }
}
```

### 42. What is abstraction in JavaScript?

Abstraction hides complex implementation details and exposes only essential features. It's achieved through abstract classes, interfaces (conceptually), or by defining common methods that subclasses must implement.

```javascript
class Database {
  connect() {
    throw new Error('Must implement connect method');
  }
  
  query(sql) {
    throw new Error('Must implement query method');
  }
}

class MySQL extends Database {
  connect() { /* MySQL specific connection */ }
  query(sql) { /* MySQL specific query */ }
}
```

### 43. What is prototype-based inheritance in JavaScript?

JavaScript uses prototypes instead of classical inheritance. Every object has a prototype chain that allows it to inherit properties and methods from other objects. Functions have a `prototype` property for creating instances.

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
Dog.prototype.constructor = Dog;
```

### 44. What are differ functions Arrow vs normal function?

Arrow functions are a concise syntax for writing functions. They don't have their own `this`, `arguments`, or `super` binding. when you want lexical `this`; use normal functions when you need dynamic `this`, `arguments`, or to create instances..

```js
const obj = {
  value: 10,
  // arrow — inherits this (bad for methods)
  arrowMethod: () => console.log(this.value),
  // normal — has own this (good)
  normalMethod() { console.log(this.value); }
};
```

#### Generators & Iterators
“**Iterators** are objects with `next()` returning `{value, done}`. **Generators** (`function*`) are a convenient way to create iterators — they can pause (`yield`) and resume, which is useful for lazy sequences or cooperative async flow.”

```js
// Generator
function* count(n=3){
  for(let i=1;i<=n;i++) yield i;
}
const it = count();
console.log(it.next()); // {value:1, done:false}
console.log([...count(5)]); // [1,2,3,4,5]
```

### 45. What are JavaScript's built-in objects?

JavaScript provides built-in objects like `Object`, `Array`, `String`, `Number`, `Boolean`, `Date`, `RegExp`, `Math`, `JSON`, `Promise`, `Map`, `Set`, and global objects like `console` and `window` (in browsers).

```javascript
// Common built-in objects
const arr = new Array(1, 2, 3);
const date = new Date();
const regex = new RegExp('\\d+');
const map = new Map();
const set = new Set();

// Global objects
console.log('Hello');
Math.random();
JSON.stringify({name: 'John'});
```

### 46. What is a prototype in JavaScript?

A prototype is an object that serves as a template for other objects. Every JavaScript object has a prototype chain that allows it to inherit properties and methods. Functions have a `prototype` property used when creating instances with `new`.

```javascript
function Person(name) {
  this.name = name;
}

Person.prototype.greet = function() {
  return `Hello, I'm ${this.name}`;
};

const john = new Person('John');
console.log(john.greet()); // "Hello, I'm John"
console.log(john.__proto__ === Person.prototype); // true
```

### 47. What is the purpose of `WeakMap` and `WeakSet` in JavaScript?

`WeakMap` and `WeakSet` hold weak references to objects, allowing garbage collection when no other references exist. Keys in `WeakMap` must be objects, and they're not enumerable, making them ideal for private data storage.

```javascript
const weakMap = new WeakMap();
const obj = {name: 'John'};

weakMap.set(obj, 'private data');
console.log(weakMap.get(obj)); // 'private data'

// When obj is deleted, the WeakMap entry is automatically garbage collected
const weakSet = new WeakSet();
weakSet.add(obj);
```

### 48. How does JavaScript handle memory management and garbage collection?

JavaScript uses automatic memory management with garbage collection. The engine automatically allocates memory for objects and frees it when objects are no longer reachable. Modern engines use mark-and-sweep algorithms to identify and clean up unused memory.

```javascript
function createObjects() {
  const obj = {data: new Array(1000000)}; // Memory allocated
  return obj.data.length;
} // obj becomes unreachable and eligible for garbage collection

// Memory leaks to avoid
let globalArray = [];
function leak() {
  globalArray.push(new Array(1000000)); // Never freed
}
```

### 49. How does JavaScript handle scope and closures?

JavaScript uses lexical scoping where inner functions have access to outer function variables. Closures occur when inner functions retain access to outer scope variables even after the outer function returns, creating persistent local environments.

```javascript
function outerFunction(x) {
  // Outer scope
  return function innerFunction(y) {
    // Inner scope has access to x
    return x + y; // Closure over x
  };
}

const addFive = outerFunction(5);
console.log(addFive(3)); // 8 - x (5) is still accessible
```

### 50. What is the use of the `window` object in JavaScript?

The `window` object is the global object in browsers, representing the browser window. It contains global variables, functions, and provides access to browser APIs like `localStorage`, `location`, `history`, and DOM manipulation methods.

```javascript
// Global variables become window properties
var globalVar = 'Hello';
console.log(window.globalVar); // 'Hello'

// Browser APIs
window.alert('Message');
window.location.href = 'https://example.com';
window.localStorage.setItem('key', 'value');
console.log(window.innerWidth); // Browser width
```

### 51. How does the `new` keyword work in JavaScript?

The `new` keyword creates a new object instance. It creates an empty object, sets its prototype to the constructor's prototype, calls the constructor with `this` bound to the new object, and returns the object (or constructor's return value if it's an object).

```javascript
function Person(name) {
  this.name = name;
  this.greet = function() { return `Hi, ${this.name}`; };
}

const john = new Person('John');
// 1. Creates empty object {}
// 2. Sets john.__proto__ = Person.prototype
// 3. Calls Person.call(john, 'John')
// 4. Returns john
```

### 52. What is an IIFE (Immediately Invoked Function Expression)?

An IIFE is a function that executes immediately after being defined. It creates a private scope to avoid polluting the global namespace and is commonly used for module patterns and initialization code.

```javascript
// Basic IIFE
(function() {
  const privateVar = 'Hidden';
  console.log('IIFE executed');
})();

// IIFE with parameters
(function(name) {
  console.log(`Hello, ${name}`);
})('John');

// Arrow function IIFE
(() => console.log('Arrow IIFE'))();
```

### 53. What is the difference between a function declaration and a function expression?

Function declarations are hoisted and can be called before they're defined. Function expressions are not hoisted and must be defined before use. Declarations create named functions, expressions can be anonymous or named.

```javascript
// Function Declaration (hoisted)
console.log(declared()); // Works - "Hello"
function declared() {
  return 'Hello';
}

// Function Expression (not hoisted)
console.log(expressed()); // Error - Cannot access before initialization
const expressed = function() {
  return 'Hello';
};
```

### 54. What is the use of `setTimeout()` and `setInterval()`?

`setTimeout()` executes a function once after a specified delay. `setInterval()` executes a function repeatedly at specified intervals. Both return IDs that can be used with `clearTimeout()` and `clearInterval()` to cancel execution.

```javascript
// setTimeout - runs once
const timeoutId = setTimeout(() => {
  console.log('Executed after 2 seconds');
}, 2000);

// setInterval - runs repeatedly
const intervalId = setInterval(() => {
  console.log('Executed every 1 second');
}, 1000);

// Cancel timers
clearTimeout(timeoutId);
clearInterval(intervalId);
```

### 55. How do you clone an object in JavaScript?

For shallow cloning, use spread operator or `Object.assign()`. For deep cloning, use `JSON.parse(JSON.stringify())` for simple objects or libraries like Lodash for complex objects with functions, dates, or circular references.

```javascript
const original = {name: 'John', age: 30, hobbies: ['reading']};

// Shallow clone
const shallow1 = {...original};
const shallow2 = Object.assign({}, original);

// Deep clone (limited)
const deep = JSON.parse(JSON.stringify(original));

// Modifying nested arrays affects shallow clones
shallow1.hobbies.push('gaming'); // Affects original.hobbies too
```

### 56. How does `JSON.stringify()` and `JSON.parse()` work in JavaScript?

`JSON.stringify()` converts JavaScript objects to JSON strings. `JSON.parse()` converts JSON strings back to JavaScript objects. They're used for data serialization, storage, and API communication, but don't handle functions, undefined, or circular references.

```javascript
const obj = {name: 'John', age: 30, active: true};

// Object to JSON string
const jsonString = JSON.stringify(obj);
console.log(jsonString); // '{"name":"John","age":30,"active":true}'

// JSON string to object
const parsed = JSON.parse(jsonString);
console.log(parsed.name); // 'John'
```

### 57. How can you create a class in JavaScript?

Use ES6 class syntax with `class` keyword, constructor method for initialization, and methods for functionality. Classes are syntactic sugar over prototype-based inheritance and support inheritance with `extends` keyword.

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
    return 'Homo sapiens';
  }
}

const john = new Person('John', 30);
console.log(john.greet()); // "Hello, I'm John"
```

### 58. Explain the concept of prototype inheritance in JavaScript.

Prototype inheritance allows objects to inherit properties and methods from other objects through the prototype chain. When accessing a property, JavaScript looks up the chain until it finds the property or reaches the end.

```javascript
// Parent constructor
function Animal(name) {
  this.name = name;
}

Animal.prototype.speak = function() {
  return `${this.name} makes a sound`;
};

// Child constructor
function Dog(name, breed) {
  Animal.call(this, name);
  this.breed = breed;
}

// Set up inheritance
Dog.prototype = Object.create(Animal.prototype);
Dog.prototype.constructor = Dog;
```

### 59. What are the different methods of creating objects in JavaScript?

You can create objects using object literals, constructor functions, `Object.create()`, ES6 classes, or factory functions. Each method has different use cases for inheritance, performance, and code organization.

```javascript
// Object literal
const obj1 = {name: 'John'};

// Constructor function
function Person(name) { this.name = name; }
const obj2 = new Person('John');

// Object.create()
const obj3 = Object.create({name: 'John'});

// ES6 Class
class PersonClass { constructor(name) { this.name = name; } }
const obj4 = new PersonClass('John');
```

### 60. What is the difference between `Object.create()` and class-based inheritance?

`Object.create()` creates objects with a specific prototype directly, while class-based inheritance uses constructor functions or ES6 classes. `Object.create()` is more flexible for prototype manipulation, classes provide cleaner syntax and better tooling support.

```javascript
// Object.create() - direct prototype setting
const animal = {
  speak() { return `${this.name} makes sound`; }
};
const dog = Object.create(animal);
dog.name = 'Rex';

// Class-based inheritance
class Animal {
  speak() { return `${this.name} makes sound`; }
}
class Dog extends Animal {
  constructor(name) { super(); this.name = name; }
}
```

### 61. What is the difference between `class` and `constructor` in JavaScript?

ES6 classes are syntactic sugar over constructor functions. Classes provide cleaner syntax, built-in inheritance with `extends`, and stricter mode by default. Constructor functions use `prototype` for methods and require manual inheritance setup.

```javascript
// Constructor function
function Person(name) {
  this.name = name;
}
Person.prototype.greet = function() { return `Hi ${this.name}`; };

// ES6 Class
class Person {
  constructor(name) { this.name = name; }
  greet() { return `Hi ${this.name}`; }
}
```

### 62. What are getter and setter methods in JavaScript?

Getters and setters allow you to define object properties that execute functions when accessed or modified. They provide controlled access to object properties and enable computed properties or validation logic.

```javascript
class Circle {
  constructor(radius) {
    this._radius = radius;
  }
  
  get radius() {
    return this._radius;
  }
  
  set radius(value) {
    if (value > 0) this._radius = value;
  }
  
  get area() {
    return Math.PI * this._radius ** 2;
  }
}
```

## **Arrays and Objects**
### 63. How can you merge two arrays in JavaScript?

You can merge arrays using the spread operator, `concat()` method, or `push()` with spread. The spread operator is the most modern and readable approach for combining arrays.

```javascript
const arr1 = [1, 2, 3];
const arr2 = [4, 5, 6];

// Spread operator (most common)
const merged1 = [...arr1, ...arr2];

// Concat method
const merged2 = arr1.concat(arr2);

// Push with spread
arr1.push(...arr2); // Modifies arr1
```

### 64. What is the difference between `slice()` and `splice()` in JavaScript?

`slice()` returns a shallow copy of a portion of an array without modifying the original. `splice()` changes the original array by removing, replacing, or adding elements and returns the removed elements.

```javascript
const arr = [1, 2, 3, 4, 5];

// slice() - doesn't modify original
const sliced = arr.slice(1, 3); // [2, 3]
console.log(arr); // [1, 2, 3, 4, 5] - unchanged

// splice() - modifies original
const spliced = arr.splice(1, 2, 'a', 'b'); // [2, 3]
console.log(arr); // [1, 'a', 'b', 4, 5] - modified
```

### 65. How do you remove duplicates from an array in JavaScript?

Use `Set` with spread operator for primitive values, or `filter()` with `indexOf()` for more control. For objects, use `filter()` with custom comparison logic or libraries like Lodash.

```javascript
const arr = [1, 2, 2, 3, 3, 4];

// Using Set (most efficient for primitives)
const unique1 = [...new Set(arr)]; // [1, 2, 3, 4]

// Using filter + indexOf
const unique2 = arr.filter((item, index) => arr.indexOf(item) === index);

// For objects
const objects = [{id: 1}, {id: 2}, {id: 1}];
const uniqueObjects = objects.filter((obj, index, self) => 
  self.findIndex(o => o.id === obj.id) === index
);
```

### 66. What are the different ways to loop through an array in JavaScript?

JavaScript provides multiple iteration methods: `for` loop, `forEach()`, `for...of`, `map()`, `filter()`, `reduce()`. Choose based on whether you need to transform data, break early, or maintain immutability.

```javascript
const arr = [1, 2, 3];

// Traditional for loop
for (let i = 0; i < arr.length; i++) { console.log(arr[i]); }

// forEach (no return value)
arr.forEach(item => console.log(item));

// for...of (can break)
for (const item of arr) { console.log(item); }

// map (returns new array)
const doubled = arr.map(item => item * 2);
```

### 67. How do you sort an array of objects based on a property in JavaScript?

Use the `sort()` method with a custom comparator function that compares the desired property. For strings, use `localeCompare()` for proper alphabetical sorting including special characters.

```javascript
const users = [
  {name: 'John', age: 30},
  {name: 'Alice', age: 25},
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

Both create shallow copies, but spread operator has cleaner syntax and better performance. `Object.assign()` can merge multiple sources and trigger setters, while spread operator is more readable for simple copying.

```javascript
const obj1 = {a: 1, b: 2};
const obj2 = {c: 3};

// Object.assign()
const merged1 = Object.assign({}, obj1, obj2);

// Spread operator (preferred)
const merged2 = {...obj1, ...obj2};

// Object.assign can modify target
Object.assign(obj1, obj2); // Modifies obj1
```

### 69. What is the difference between shallow copy and deep copy in JavaScript?

Shallow copy copies only the first level properties, sharing references to nested objects. Deep copy creates completely independent copies of all nested levels, preventing unintended mutations.

**Shallow copy** copies top-level properties; nested objects are shared by reference.
**Deep copy** recursively copies all nested structures so the new object is fully independent.

```javascript
const original = {
  name: 'John',
  hobbies: ['reading', 'gaming']
};

// Shallow copy
const shallow = {...original};
shallow.hobbies.push('cooking'); // Affects original.hobbies

// Deep copy (simple objects)
const deep = JSON.parse(JSON.stringify(original));
deep.hobbies.push('swimming'); // Doesn't affect original
```
**When to use:** shallow copy is fine for flat objects or when you intentionally share nested refs; deep copy is needed when you must fully isolate the clone.

### 70. How do you check if an object is an array in JavaScript?

Use `Array.isArray()` method, which is the most reliable way. Avoid `typeof` (returns 'object') or `instanceof` (can fail across frames). `Array.isArray()` works in all contexts.

```javascript
const arr = [1, 2, 3];
const obj = {length: 3};

console.log(Array.isArray(arr)); // true
console.log(Array.isArray(obj)); // false
console.log(typeof arr); // 'object' - not reliable
console.log(arr instanceof Array); // true - but can fail across frames
```

### 71. What is object destructuring in JavaScript?

Object destructuring extracts properties from objects into variables using a concise syntax. It supports default values, renaming, and nested destructuring, making code more readable and reducing repetitive property access.

```javascript
const user = {name: 'John', age: 30, city: 'NYC'};

// Basic destructuring
const {name, age} = user;

// With default values
const {name, country = 'USA'} = user;

// Renaming variables
const {name: userName, age: userAge} = user;

// Nested destructuring
const {address: {street}} = {address: {street: '123 Main St'}};
```

### 72. How can you merge two objects in JavaScript?

Use the spread operator for simple merging, `Object.assign()` for multiple sources, or libraries for deep merging. The spread operator is most common for shallow merging with clean syntax.

```javascript
const obj1 = {a: 1, b: 2};
const obj2 = {b: 3, c: 4};

// Spread operator (obj2 overwrites obj1)
const merged1 = {...obj1, ...obj2}; // {a: 1, b: 3, c: 4}

// Object.assign()
const merged2 = Object.assign({}, obj1, obj2);

// Multiple objects
const merged3 = {...obj1, ...obj2, d: 5};
```

### 73. What is the difference between `for...in` and `for...of` loops in JavaScript?

`for...in` iterates over enumerable property names (keys) of objects, including inherited properties. `for...of` iterates over iterable values like arrays, strings, Maps, and Sets, providing the actual values.

```javascript
const arr = ['a', 'b', 'c'];
const obj = {x: 1, y: 2, z: 3};

// for...in - iterates over keys/indices
for (const key in arr) {
  console.log(key); // '0', '1', '2'
}

// for...of - iterates over values
for (const value of arr) {
  console.log(value); // 'a', 'b', 'c'
}

// for...in with objects
for (const key in obj) {
  console.log(key, obj[key]); // 'x' 1, 'y' 2, 'z' 3
}
```

## **Functions and Scope**
### 74. What is the difference between local scope and global scope in JavaScript?

Global scope variables are accessible throughout the entire program. Local scope variables are only accessible within their containing function or block. JavaScript uses lexical scoping where inner scopes can access outer scope variables.

```javascript
let globalVar = 'I am global'; // Global scope

function myFunction() {
  let localVar = 'I am local'; // Local scope
  console.log(globalVar); // Can access global
  console.log(localVar);  // Can access local
}

console.log(globalVar); // Works
console.log(localVar);  // ReferenceError - not accessible
```

### 75. What is lexical scoping in JavaScript?

Lexical scoping means functions have access to variables in their outer scope at the time they were defined, not when they're executed. This creates closures and enables powerful patterns like module patterns and callbacks.

```javascript
function outerFunction() {
  const outerVar = 'I am outer';
  
  function innerFunction() {
    console.log(outerVar); // Can access outerVar
  }
  
  return innerFunction;
}

const myFunc = outerFunction();
myFunc(); // 'I am outer' - lexical scoping in action
```

### 76. What is the difference between function expressions and function declarations?

Function declarations are hoisted and can be called before they're defined. Function expressions are not hoisted and must be defined before use. Declarations create named functions, expressions can be anonymous and are often assigned to variables.

```javascript
// Function Declaration (hoisted)
console.log(declared()); // Works - "Hello"
function declared() {
  return 'Hello';
}

// Function Expression (not hoisted)
console.log(expressed()); // Error
const expressed = function() {
  return 'Hello';
};
```

### 77. What is the use of the `arguments` object in JavaScript?

The `arguments` object is an array-like object available inside functions that contains all passed arguments. It's useful for functions with variable parameters, but ES6 rest parameters are preferred for modern code.

```javascript
function sum() {
  let total = 0;
  for (let i = 0; i < arguments.length; i++) {
    total += arguments[i];
  }
  return total;
}

console.log(sum(1, 2, 3, 4)); // 10

// Modern approach with rest parameters
const modernSum = (...numbers) => numbers.reduce((a, b) => a + b, 0);
```

### 78. What is the purpose of the `default` keyword in JavaScript functions?

Default parameters allow you to set fallback values for function parameters when no argument is passed or `undefined` is passed. They make functions more robust and reduce the need for manual parameter checking.

```javascript
function greet(name = 'Guest', greeting = 'Hello') {
  return `${greeting}, ${name}!`;
}

console.log(greet()); // "Hello, Guest!"
console.log(greet('John')); // "Hello, John!"
console.log(greet('John', 'Hi')); // "Hi, John!"
console.log(greet(undefined, 'Hey')); // "Hey, Guest!"
```

### 79. How can you return multiple values from a function in JavaScript?

JavaScript functions can only return one value, but you can return multiple values using arrays, objects, or destructuring assignment. Objects are preferred when values have semantic meaning, arrays for ordered data.

```javascript
// Using array
function getCoordinates() {
  return [10, 20];
}
const [x, y] = getCoordinates();

// Using object (preferred)
function getUserInfo() {
  return {name: 'John', age: 30, email: 'john@email.com'};
}
const {name, age} = getUserInfo();
```

### 80. What is recursion in JavaScript? Can you provide an example?

Recursion is when a function calls itself to solve a problem by breaking it into smaller subproblems. It needs a base case to stop the recursion and avoid infinite loops.

```javascript
// Factorial example
function factorial(n) {
  if (n <= 1) return 1; // Base case
  return n * factorial(n - 1); // Recursive call
}

console.log(factorial(5)); // 120

// Fibonacci example
function fibonacci(n) {
  if (n <= 1) return n;
  return fibonacci(n - 1) + fibonacci(n - 2);
}
```

### 81. How does JavaScript handle multiple callback functions?

JavaScript executes callbacks asynchronously through the event loop. Multiple callbacks can be chained, nested, or managed using promises and async/await to avoid callback hell and maintain readable code.

```javascript
// Nested callbacks (callback hell)
getData(function(a) {
  processData(a, function(b) {
    saveData(b, function(c) {
      console.log('Done');
    });
  });
});

// Better approach with promises
getData()
  .then(processData)
  .then(saveData)
  .then(() => console.log('Done'));
```

### 82. How do you handle errors in JavaScript?

Use try-catch blocks for synchronous code, .catch() for promises, and try-catch with async/await. Always handle errors gracefully to prevent application crashes and provide meaningful error messages to users.

```javascript
// Synchronous error handling
try {
  const result = riskyOperation();
  console.log(result);
} catch (error) {
  console.error('Error:', error.message);
}

// Async error handling
async function handleAsync() {
  try {
    const data = await fetchData();
    return data;
  } catch (error) {
    console.error('Fetch failed:', error);
  }
}
```

### 83. How does JavaScript handle asynchronous operations?

JavaScript uses the event loop, callback queue, and microtask queue to handle async operations. Promises and async/await provide cleaner syntax than callbacks. The event loop processes tasks when the call stack is empty.

```javascript
console.log('1');

setTimeout(() => console.log('2'), 0); // Macro task

Promise.resolve().then(() => console.log('3')); // Micro task

console.log('4');

// Output: 1, 4, 3, 2
// Microtasks (promises) have higher priority than macrotasks (setTimeout)
```

## **Asynchronous JavaScript**
### 84. How can you handle asynchronous operations in JavaScript?

Use callbacks, promises, or async/await. Async/await is preferred for readability. For multiple operations, use Promise.all() for parallel execution or sequential await calls for dependent operations.

```javascript
// Callbacks
fetchData(function(data) {
  console.log(data);
});

// Promises
fetchData().then(data => console.log(data));

// Async/await (preferred)
async function getData() {
  const data = await fetchData();
  console.log(data);
}

// Multiple operations
const [user, posts] = await Promise.all([fetchUser(), fetchPosts()]);
```

### 85. Explain the concept of JavaScript's single-threaded model.

JavaScript runs on a single thread with an event loop that manages asynchronous operations. While the main thread executes code, async operations are handled by browser APIs or Node.js, then callbacks are queued for execution.

```javascript
console.log('Start'); // Synchronous

setTimeout(() => {
  console.log('Timeout'); // Asynchronous - queued for later
}, 0);

console.log('End'); // Synchronous

// Output: Start, End, Timeout
// Even with 0ms delay, setTimeout is asynchronous
```

### 86. What are the data types that are mutable in JavaScript?

Objects, arrays, functions, and dates are mutable - their contents can be changed after creation. Primitives (string, number, boolean, null, undefined, symbol, bigint) are immutable - operations create new values.

```javascript
// Mutable (objects, arrays)
const obj = {name: 'John'};
obj.name = 'Jane'; // Mutates the object
const arr = [1, 2, 3];
arr.push(4); // Mutates the array

// Immutable (primitives)
let str = 'Hello';
str.toUpperCase(); // Returns new string, doesn't change str
console.log(str); // Still 'Hello'
```

### 87. What is a function in JavaScript? How do you declare one?

A function is a reusable block of code that performs a specific task. You can declare functions using function declarations, function expressions, arrow functions, or method definitions in objects and classes.

```javascript
// Function declaration
function add(a, b) {
  return a + b;
}

// Function expression
const multiply = function(a, b) {
  return a * b;
};

// Arrow function
const divide = (a, b) => a / b;

// Method in object
const math = {
  subtract(a, b) { return a - b; }
};
```

### 88. How does JavaScript handle multiple asynchronous operations in sequence?

Use async/await with sequential calls for dependent operations, or Promise.all() for parallel execution. Chain promises with .then() or use for-await-of loops for iterating over async operations.

```javascript
// Sequential (one after another)
async function sequential() {
  const user = await fetchUser();
  const posts = await fetchUserPosts(user.id);
  return posts;
}

// Parallel (all at once)
async function parallel() {
  const [users, posts, comments] = await Promise.all([
    fetchUsers(),
    fetchPosts(),
    fetchComments()
  ]);
  return {users, posts, comments};
}
```

### 89. What is the purpose of `Promise.all()` and `Promise.race()`?

`Promise.all()` waits for all promises to resolve and returns an array of results, failing if any promise rejects. `Promise.race()` returns the first promise to settle (resolve or reject), useful for timeouts.

```javascript
// Promise.all() - waits for all
const results = await Promise.all([
  fetch('/api/users'),
  fetch('/api/posts'),
  fetch('/api/comments')
]); // All must succeed

// Promise.race() - first to finish wins
const fastest = await Promise.race([
  fetch('/api/data'),
  new Promise((_, reject) => 
    setTimeout(() => reject(new Error('Timeout')), 5000)
  )
]); // Either data or timeout
```

### 90. How do you handle errors in an async function?

Use try-catch blocks around await statements to catch promise rejections. You can also use .catch() on the promise or handle errors at the call site when invoking the async function.

```javascript
async function fetchUserData(id) {
  try {
    const user = await fetch(`/api/users/${id}`);
    if (!user.ok) throw new Error('User not found');
    return await user.json();
  } catch (error) {
    console.error('Failed to fetch user:', error.message);
    return null; // Return fallback value
  }
}

// Handle at call site
fetchUserData(123).catch(error => {
  console.error('Error in main:', error);
});
```

### 91. What is a callback hell, and how can you avoid it?

Callback hell occurs when multiple nested callbacks create deeply indented, hard-to-read code. Avoid it by using promises, async/await, or breaking callbacks into named functions. Modern approaches provide better error handling and readability.

```javascript
// Callback hell
getData(function(a) {
  getMoreData(a, function(b) {
    getEvenMoreData(b, function(c) {
      getFinalData(c, function(d) {
        console.log(d); // Pyramid of doom
      });
    });
  });
});

// Solution with async/await
async function fetchAllData() {
  const a = await getData();
  const b = await getMoreData(a);
  const c = await getEvenMoreData(b);
  const d = await getFinalData(c);
  console.log(d);
}
```

### 92. What are the different ways to loop through an array in JavaScript?

Use `for` loop for performance, `forEach()` for side effects, `map()` for transformations, `filter()` for filtering, `for...of` when you need to break early, and `reduce()` for accumulating values.

```javascript
const arr = [1, 2, 3, 4, 5];

// Traditional for loop
for (let i = 0; i < arr.length; i++) { console.log(arr[i]); }

// forEach (no return value)
arr.forEach(item => console.log(item));

// for...of (can break/continue)
for (const item of arr) { console.log(item); }

// map (returns new array)
const doubled = arr.map(x => x * 2);

// filter + map chain
const evenDoubled = arr.filter(x => x % 2 === 0).map(x => x * 2);
```

## **ES6 and Beyond**
### 93. What are template literals in JavaScript?

Template literals use backticks and allow embedded expressions with `${}`, multi-line strings, and tagged templates. They're more readable than string concatenation and support dynamic content insertion.

```javascript
const name = 'John';
const age = 30;

// Template literal
const message = `Hello, ${name}! 
You are ${age} years old.
Next year you'll be ${age + 1}.`;

// Compared to concatenation
const oldWay = 'Hello, ' + name + '!\nYou are ' + age + ' years old.';

// Tagged template
function highlight(strings, ...values) {
  return strings.reduce((result, string, i) => 
    result + string + (values[i] ? `<mark>${values[i]}</mark>` : ''), '');
}
```

### 94. What are the new features introduced in ES6?

ES6 introduced let/const, arrow functions, classes, template literals, destructuring, spread/rest operators, modules, promises, Map/Set, symbols, and default parameters. These features modernized JavaScript development significantly.

* `let` / `const` (block scope)
* Arrow functions `=>`
* Template literals `` `...${x}` ``
* Destructuring `{a, b}` / `[x,y]`
* Default params `fn(a=1)`
* Rest/spread `...`
* Classes (`class`)
* Promises
* `import` / `export` (modules)
* `Map`, `Set`, `Symbol`
  (Use these to write cleaner, safer modern JS.)

```javascript
// Key ES6 features
const [a, b] = [1, 2]; // Destructuring
const obj = {name, age}; // Shorthand properties
const arr = [...oldArr, newItem]; // Spread operator
const {name, ...rest} = user; // Rest operator

class Person { // Classes
  constructor(name) { this.name = name; }
}

const greet = name => `Hello ${name}`; // Arrow functions
```

### 95. What is a set and a map in JavaScript?

Set stores unique values of any type, automatically removing duplicates. Map stores key-value pairs where keys can be any type, unlike objects which only accept strings/symbols as keys.

```javascript
// Set - unique values
const uniqueNumbers = new Set([1, 2, 2, 3, 3, 4]);
console.log(uniqueNumbers); // Set {1, 2, 3, 4}
uniqueNumbers.add(5);
uniqueNumbers.has(3); // true

// Map - any key type
const userRoles = new Map();
userRoles.set('john', 'admin');
userRoles.set(123, 'user');
userRoles.set(true, 'guest');
console.log(userRoles.get('john')); // 'admin'
```

### 96. What are symbols in JavaScript? When would you use them?

Symbols are unique primitive values used as object property keys to avoid naming conflicts. They're useful for creating private-like properties, defining well-known symbols, and implementing protocols like iterators.

```javascript
// Creating symbols
const id = Symbol('id');
const anotherId = Symbol('id');
console.log(id === anotherId); // false - always unique

// Using as object keys
const user = {
  name: 'John',
  [id]: 12345 // Symbol key - won't conflict
};

// Well-known symbols
const obj = {
  [Symbol.iterator]() {
    // Custom iterator implementation
  }
};
```

### 97. What are generator functions in JavaScript?

Generator functions can pause and resume execution using `yield` keyword. They return iterator objects and are useful for creating custom iterators, handling async operations, and implementing lazy evaluation.

```javascript
function* numberGenerator() {
  yield 1;
  yield 2;
  yield 3;
}

const gen = numberGenerator();
console.log(gen.next()); // {value: 1, done: false}
console.log(gen.next()); // {value: 2, done: false}

// Infinite sequence
function* fibonacci() {
  let [a, b] = [0, 1];
  while (true) {
    yield a;
    [a, b] = [b, a + b];
  }
}
```

### 98. How does destructuring work in JavaScript?

Destructuring extracts values from arrays or properties from objects into variables using a concise syntax. It supports default values, renaming, nested destructuring, and rest patterns.

```javascript
// Array destructuring
const [first, second, ...rest] = [1, 2, 3, 4, 5];

// Object destructuring
const {name, age, city = 'Unknown'} = user;

// Renaming variables
const {name: userName, age: userAge} = user;

// Nested destructuring
const {address: {street, zipCode}} = user;

// Function parameters
function greet({name, age}) {
  return `Hello ${name}, age ${age}`;
}
```

### 99. What is the spread operator, and how do you use it?

The spread operator (`...`) expands iterables into individual elements. Use it for array/object copying, merging, function arguments, and converting iterables to arrays. It creates shallow copies.

```javascript
// Array operations
const arr1 = [1, 2, 3];
const arr2 = [...arr1, 4, 5]; // [1, 2, 3, 4, 5]
const combined = [...arr1, ...arr2];

// Object operations
const obj1 = {a: 1, b: 2};
const obj2 = {...obj1, c: 3}; // {a: 1, b: 2, c: 3}

// Function arguments
const numbers = [1, 2, 3];
Math.max(...numbers); // Same as Math.max(1, 2, 3)
```

### 100. What is the rest parameter in JavaScript?

Rest parameter (`...`) collects multiple arguments into an array. It's used in function parameters to handle variable arguments and in destructuring to collect remaining elements.

```javascript
// Function rest parameters
function sum(...numbers) {
  return numbers.reduce((total, num) => total + num, 0);
}
sum(1, 2, 3, 4); // 10

// Destructuring rest
const [first, ...remaining] = [1, 2, 3, 4, 5];
console.log(remaining); // [2, 3, 4, 5]

const {name, ...otherProps} = {name: 'John', age: 30, city: 'NYC'};
console.log(otherProps); // {age: 30, city: 'NYC'}
```

### 101. What are `Promise.allSettled()`, `Promise.any()`, and `Promise.finally()`?

`Promise.allSettled()` waits for all promises to settle, returning results and errors. `Promise.any()` resolves with the first successful promise. `Promise.finally()` executes cleanup code regardless of promise outcome.

```javascript
// Promise.allSettled() - waits for all, never rejects
const results = await Promise.allSettled([
  Promise.resolve('success'),
  Promise.reject('error'),
  Promise.resolve('another success')
]);
// [{status: 'fulfilled', value: 'success'}, {status: 'rejected', reason: 'error'}, ...]

// Promise.any() - first success wins
const first = await Promise.any([
  Promise.reject('fail'),
  Promise.resolve('success'),
  Promise.resolve('another')
]); // 'success'

// Promise.finally() - cleanup
promise.finally(() => console.log('Cleanup'));
```

#### `Promise.all` * Waits for **all** promises to fulfill. If any reject → it rejects immediately.
```js
Promise.all([p1, p2]).then(values => ...).catch(err => ...);
```

#### `Promise.allSettled` * Waits for **all** to settle (either fulfilled or rejected), returns array of results with `{status, value/reason}`.
```js
Promise.allSettled([p1, p2]).then(results => console.log(results));
```

#### `Promise.race` * Resolves or rejects as soon as **one** promise settles (first settled).
```js
Promise.race([p1, p2]).then(first => ...).catch(err => ...);
```

#### `Promise.any` (ES2021) * Resolves as soon as **one** promise fulfills. If all reject → it rejects with `AggregateError`.
```js
Promise.any([p1, p2]).then(value => console.log(value)).catch(err => console.error(err));
```

#### Practical notes to say:

* Use `Promise.all` for parallel tasks where all results are required.
* Use `allSettled` when you want results for all regardless of failures (reporting).
* Use `race` for timeouts or first-response logic.
* Use `any` when you need the first success and can ignore failures until all fail.

### 102. How do you create a class in JavaScript?

Use the `class` keyword with constructor method for initialization, instance methods for behavior, static methods for class-level functionality, and `extends` for inheritance. Classes are syntactic sugar over prototypes.

```javascript
class Animal {
  constructor(name) {
    this.name = name;
  }
  
  speak() {
    return `${this.name} makes a sound`;
  }
  
  static species() {
    return 'Unknown species';
  }
}

class Dog extends Animal {
  constructor(name, breed) {
    super(name);
    this.breed = breed;
  }
  
  speak() {
    return `${this.name} barks`;
  }
}
```

### 103. Fetch vs Axios (quick comparison)

* **Fetch**

  * Built-in in browsers.
  * Returns a `Promise` that resolves for network-level success — must check `response.ok`.
  * No automatic JSON error handling; no request cancellation natively (AbortController available).
* **Axios**

  * Library with convenience: automatic JSON parsing, interceptors, timeouts, request cancellation, transforms, and better error objects.
  * Works in Node + browser.

When to use which:

* Use `fetch` for small apps or when you want no dependency.
* Use `axios` when you want easier interceptors, timeout, error handling, or older browser support.

Fetch example:

```js
async function getJSON(url){
  const res = await fetch(url);
  if (!res.ok) throw new Error(res.statusText);
  return res.json();
}
```

Axios example:

```js
// axios already does JSON parse and throws on non-2xx
const { data } = await axios.get('/api/items');
```

### 103. What is `class`, `constructor`, `super` in JavaScript?
Classes provide cleaner syntax, automatic strict mode, built-in inheritance with `extends`, and better tooling support. Function constructors require manual prototype setup but offer more flexibility and are the underlying mechanism.

* `class` defines a constructor + methods.
* `constructor` runs when creating an instance.
* `super(...)` calls parent class constructor (must do in subclass before `this`).

Example:

```js
class Animal {
  constructor(name){ this.name = name; }
  speak(){ console.log(`${this.name} makes a noise`); }
}

class Dog extends Animal {
  constructor(name, breed){
    super(name);         // call parent constructor
    this.breed = breed;
  }
  speak(){ console.log(`${this.name} barks`); }
}

const d = new Dog('Rex', 'Beagle');
d.speak(); // "Rex barks"
```

## **Error Handling and Debugging**
### 104. What is try-catch in JavaScript? How does it work?

Try-catch handles runtime errors gracefully. Code in the `try` block executes normally, and if an error occurs, control jumps to the `catch` block. Use `finally` for cleanup code that always runs.

```javascript
try {
  const data = JSON.parse(invalidJson);
  console.log(data);
} catch (error) {
  console.error('Parsing failed:', error.message);
} finally {
  console.log('Cleanup code runs regardless');
}

// Async try-catch
async function fetchData() {
  try {
    const response = await fetch('/api/data');
    return await response.json();
  } catch (error) {
    console.error('Fetch failed:', error);
    return null;
  }
}
```

### 105. What is the difference between `throw` and `return` in JavaScript?

`return` exits a function normally with a value, while `throw` exits abnormally by raising an exception. Thrown errors can be caught by try-catch blocks, while returned values are handled by the caller.

```javascript
function validateAge(age) {
  if (age < 0) {
    throw new Error('Age cannot be negative'); // Abnormal exit
  }
  if (age < 18) {
    return false; // Normal exit with value
  }
  return true; // Normal exit with value
}

try {
  const isValid = validateAge(-5); // Throws error
} catch (error) {
  console.log(error.message); // 'Age cannot be negative'
}
```

### 106. How do you handle exceptions in JavaScript?

Use try-catch blocks to handle exceptions gracefully. Catch specific error types, provide meaningful error messages, and implement fallback behavior. Always log errors for debugging and consider user experience when handling failures.

```javascript
try {
  const data = JSON.parse(userInput);
  processData(data);
} catch (error) {
  if (error instanceof SyntaxError) {
    console.error('Invalid JSON format');
    showUserError('Please check your input format');
  } else {
    console.error('Unexpected error:', error);
    showUserError('Something went wrong');
  }
}
```

### 107. What are some common JavaScript debugging techniques?

Use browser DevTools, console.log for quick debugging, debugger statements for breakpoints, console.table for objects, console.time for performance, and error boundaries in production. Modern tools offer advanced debugging features.

```javascript
// Console debugging
console.log('Value:', variable);
console.table(arrayOfObjects);
console.time('operation');
// ... code to measure
console.timeEnd('operation');

// Debugger statement
function complexFunction(data) {
  debugger; // Pauses execution in DevTools
  return processData(data);
}

// Conditional debugging
if (DEBUG_MODE) console.log('Debug info:', data);
```

### 108. What is the difference between `Error` and `TypeError` in JavaScript?

`Error` is the base error class for all errors. `TypeError` is a specific error type thrown when a value is not of the expected type. Use specific error types for better error handling and debugging.

```javascript
// Generic Error
throw new Error('Something went wrong');

// TypeError - wrong type
function multiply(a, b) {
  if (typeof a !== 'number' || typeof b !== 'number') {
    throw new TypeError('Arguments must be numbers');
  }
  return a * b;
}

// Catching specific errors
try {
  multiply('5', 10);
} catch (error) {
  if (error instanceof TypeError) {
    console.log('Type error:', error.message);
  }
}
```

## **JavaScript Modules**
### 109. What is the difference between `import` and `require` in JavaScript?

* **`import` (ES6 modules):**
  Statically analyzed, hoisted at compile time, supports tree-shaking, and preferred in modern JavaScript.

* **`require` (CommonJS):**
  Loaded dynamically at runtime, not hoisted, and mainly used in older Node.js/CommonJS environments.


```javascript
// ES6 import (static)
import { functionName } from './module.js';
import defaultExport from './module.js';
import * as utils from './utils.js';

// CommonJS require (dynamic)
const { functionName } = require('./module.js');
const module = require('./module.js');

// Dynamic import (ES2020)
const module = await import('./module.js');
```

### 110. What is the purpose of the `export` keyword in JavaScript?

`export` makes functions, objects, or values available to other modules. Use named exports for multiple items or default export for a single main item. This enables modular code organization and reusability.

```javascript
// Named exports
export const PI = 3.14159;
export function calculateArea(radius) {
  return PI * radius * radius;
}

// Default export
export default class Calculator {
  add(a, b) { return a + b; }
}

// Export existing variables
const utils = { format, validate };
export { utils };
```

### 111. What are JavaScript arrays and how do you manipulate them?

Arrays are ordered collections of elements. Manipulate them using methods like push/pop, shift/unshift, slice/splice, map/filter/reduce, and sort. Modern methods return new arrays for immutability.

```javascript
const arr = [1, 2, 3];

// Adding/removing
arr.push(4); // [1, 2, 3, 4]
arr.pop(); // [1, 2, 3]
arr.unshift(0); // [0, 1, 2, 3]

// Transforming (immutable)
const doubled = arr.map(x => x * 2);
const evens = arr.filter(x => x % 2 === 0);
const sum = arr.reduce((acc, x) => acc + x, 0);

// Copying/slicing
const copy = [...arr];
const slice = arr.slice(1, 3);
```

### 112. How do you implement modules in JavaScript?

Create modules by exporting functions/objects from files and importing them where needed. Use ES6 modules for modern applications or CommonJS for Node.js. Organize code into logical, reusable units.

```javascript
// math.js - module file
export const add = (a, b) => a + b;
export const multiply = (a, b) => a * b;
export default class Calculator {
  constructor() { this.result = 0; }
}

// main.js - using the module
import Calculator, { add, multiply } from './math.js';

const calc = new Calculator();
const result = add(5, multiply(2, 3));
```

### 113. What is the `default` export in JavaScript modules?

Default export allows exporting a single main value from a module without specifying a name. Import it with any name you choose. Use for the primary functionality of a module, while named exports are for utilities.

```javascript
// user.js - default export
export default class User {
  constructor(name) { this.name = name; }
}

// Also can export function or object as default
export default function createUser(name) {
  return new User(name);
}

// main.js - importing default
import User from './user.js'; // Can name it anything
import CreateUser from './user.js'; // Same import, different name
```

### 114. What are the benefits of using modules in JavaScript?

Modules provide code organization, reusability, namespace isolation, dependency management, and better maintainability. They enable tree-shaking for smaller bundles and make testing easier through isolated units.

```javascript
// Benefits demonstrated:

// 1. Namespace isolation
// utils.js
export const format = (text) => text.trim();

// 2. Reusability
// Multiple files can import and use utils

// 3. Dependency management
import { format } from './utils.js';
import { validate } from './validation.js';

// 4. Tree-shaking - only imported functions are bundled
import { specificFunction } from './large-library.js';
```

## **Browser and DOM**
### 115. How do you make an AJAX request in JavaScript?

Use the Fetch API for modern applications or XMLHttpRequest for older browsers. Fetch returns promises and has cleaner syntax. Handle responses, errors, and different HTTP methods appropriately.

```javascript
// Modern Fetch API
async function fetchData() {
  try {
    const response = await fetch('/api/users');
    if (!response.ok) throw new Error('Network error');
    const data = await response.json();
    return data;
  } catch (error) {
    console.error('Fetch failed:', error);
  }
}

// POST request
fetch('/api/users', {
  method: 'POST',
  headers: { 'Content-Type': 'application/json' },
  body: JSON.stringify({ name: 'John', age: 30 })
});
```

### 116. What is the Fetch API in JavaScript?

Fetch API is a modern, promise-based interface for making HTTP requests. It's more powerful and flexible than XMLHttpRequest, with better error handling and support for streaming responses.

```javascript
// Basic fetch
fetch('/api/data')
  .then(response => response.json())
  .then(data => console.log(data));

// With options
fetch('/api/users', {
  method: 'POST',
  headers: {
    'Content-Type': 'application/json',
    'Authorization': 'Bearer token'
  },
  body: JSON.stringify({ name: 'John' })
})
.then(response => {
  if (!response.ok) throw new Error('Request failed');
  return response.json();
});
```

### 117. Explain the concept of a single-page application (SPA).

SPAs load a single HTML page and dynamically update content using JavaScript without full page reloads. They provide faster navigation, better user experience, but require client-side routing and state management.

```javascript
// SPA routing example (conceptual)
class Router {
  constructor() {
    this.routes = {};
    window.addEventListener('popstate', this.handleRoute.bind(this));
  }
  
  addRoute(path, handler) {
    this.routes[path] = handler;
  }
  
  navigate(path) {
    history.pushState(null, null, path);
    this.handleRoute();
  }
  
  handleRoute() {
    const path = window.location.pathname;
    const handler = this.routes[path];
    if (handler) handler();
  }
}
```

### 118. What is the DOM (Document Object Model) in JavaScript?

The DOM is a programming interface that represents HTML/XML documents as a tree structure. JavaScript can manipulate DOM elements to change content, structure, and styling dynamically, enabling interactive web pages.

**DOM vs BOM**

**DOM (Document Object Model)** is the API for HTML/XML document structure — nodes, elements, attributes. **BOM (Browser Object Model)** covers browser-specific objects not part of the page — `window`, `navigator`, `location`, `history`, `screen`. Use DOM to manipulate page content and BOM to interact with the browser environment.

```js
// DOM
document.querySelector('#title').textContent = 'Hello';

// BOM
console.log(window.location.href);
```

```javascript
// DOM manipulation examples
const element = document.getElementById('myElement');
const elements = document.querySelectorAll('.className');

// Changing content
element.textContent = 'New text';
element.innerHTML = '<strong>Bold text</strong>';

// Changing attributes
element.setAttribute('class', 'newClass');
element.style.color = 'red';

// Creating elements
const newDiv = document.createElement('div');
newDiv.textContent = 'Hello World';
document.body.appendChild(newDiv);
```

### 119. How do you manipulate the DOM using JavaScript?

Use DOM methods to select, create, modify, and remove elements. Modern approaches prefer `querySelector` over older methods, and consider using frameworks for complex applications to manage DOM updates efficiently.

```javascript
// Selecting elements
const element = document.querySelector('#myId');
const elements = document.querySelectorAll('.myClass');

// Modifying elements
element.textContent = 'New content';
element.classList.add('active');
element.style.display = 'none';

// Creating and adding elements
const newElement = document.createElement('p');
newElement.textContent = 'Hello';
element.appendChild(newElement);

// Event handling
element.addEventListener('click', () => {
  console.log('Element clicked');
});
```

### 120. What is the difference between `Object.freeze()` and `Object.seal()` in JavaScript?

`Object.freeze()` makes an object completely immutable - no properties can be added, removed, or modified. `Object.seal()` prevents adding/removing properties but allows modifying existing ones. Both affect only the first level.

```javascript
const obj = { name: 'John', age: 30 };

// Object.freeze() - completely immutable
const frozen = Object.freeze({...obj});
frozen.name = 'Jane'; // Ignored in strict mode, error in strict mode
frozen.city = 'NYC'; // Ignored
console.log(frozen); // { name: 'John', age: 30 }

// Object.seal() - can modify existing properties
const sealed = Object.seal({...obj});
sealed.name = 'Jane'; // Works
sealed.city = 'NYC'; // Ignored
console.log(sealed); // { name: 'Jane', age: 30 }
```

### 121. How do you prevent the default action of an event in JavaScript?

Use `preventDefault()` method on the event object to stop the browser's default behavior. This prevents form submissions, link navigation, or other default actions while still allowing event propagation to continue.

```javascript
// Prevent form submission
form.addEventListener('submit', (e) => {
  e.preventDefault();
  // Custom form handling
  validateAndSubmit();
});

// Prevent link navigation
link.addEventListener('click', (e) => {
  e.preventDefault();
  // Custom navigation logic
  handleCustomNavigation();
});

// Prevent context menu
document.addEventListener('contextmenu', (e) => e.preventDefault());
```

### 122. What is the difference between `addEventListener()` and `onclick`?

`addEventListener()` can attach multiple listeners to the same event and provides more control with options like capture phase. `onclick` is a property that can only hold one function and is simpler but less flexible.

```javascript
const button = document.getElementById('btn');

// onclick - only one handler
button.onclick = () => console.log('First');
button.onclick = () => console.log('Second'); // Overwrites first

// addEventListener - multiple handlers
button.addEventListener('click', () => console.log('First'));
button.addEventListener('click', () => console.log('Second')); // Both execute

// addEventListener with options
button.addEventListener('click', handler, { once: true, passive: true });
```

### 123. What is `localStorage` and `sessionStorage` in JavaScript?

Both are web storage APIs for storing data in the browser. `localStorage` persists until manually cleared, while `sessionStorage` clears when the tab closes. They store strings and have a larger capacity than cookies.

```javascript
// localStorage - persists across sessions
localStorage.setItem('user', JSON.stringify({name: 'John', id: 123}));
const user = JSON.parse(localStorage.getItem('user'));
localStorage.removeItem('user');
localStorage.clear(); // Clear all

// sessionStorage - clears when tab closes
sessionStorage.setItem('tempData', 'value');
const tempData = sessionStorage.getItem('tempData');

// Check availability
if (typeof Storage !== 'undefined') {
  // Storage supported
}
```

### 124. What is the difference between `localStorage` and `cookies` in JavaScript?

`localStorage` stores larger amounts of data (5-10MB) that persists until cleared, accessible only via JavaScript. Cookies are smaller (4KB), sent with HTTP requests, have expiration dates, and can be accessed by both client and server.

```javascript
// localStorage - client-side only, larger storage
localStorage.setItem('preferences', JSON.stringify({theme: 'dark'}));

// Cookies - sent to server, smaller, with expiration
document.cookie = 'sessionId=abc123; expires=Thu, 18 Dec 2024 12:00:00 UTC; path=/';

// Reading cookies
function getCookie(name) {
  const value = `; ${document.cookie}`;
  const parts = value.split(`; ${name}=`);
  if (parts.length === 2) return parts.pop().split(';').shift();
}
```

### 125. How do you handle CORS (Cross-Origin Resource Sharing) in JavaScript?

CORS is handled by the server setting appropriate headers. In JavaScript, you can make requests to CORS-enabled endpoints. For development, use proxies or CORS browser extensions, but production requires proper server configuration.

```javascript
// CORS request (server must allow it)
fetch('https://api.example.com/data', {
  method: 'POST',
  headers: {
    'Content-Type': 'application/json',
  },
  body: JSON.stringify({data: 'value'})
})
.then(response => response.json())
.catch(error => {
  if (error.name === 'TypeError') {
    console.log('Possible CORS error');
  }
});

// Server needs headers like:
// Access-Control-Allow-Origin: *
// Access-Control-Allow-Methods: GET, POST, PUT, DELETE
```

### 126. How do you make an AJAX request in JavaScript?

Use the modern Fetch API for clean promise-based requests, or XMLHttpRequest for older browser support. Fetch is preferred for its simplicity and better error handling capabilities.

```javascript
// Modern Fetch API
async function fetchData() {
  try {
    const response = await fetch('/api/users');
    if (!response.ok) throw new Error(`HTTP ${response.status}`);
    return await response.json();
  } catch (error) {
    console.error('Request failed:', error);
  }
}

// XMLHttpRequest (legacy)
const xhr = new XMLHttpRequest();
xhr.open('GET', '/api/users');
xhr.onload = () => {
  if (xhr.status === 200) {
    const data = JSON.parse(xhr.responseText);
  }
};
xhr.send();
```

### 127. What are `XMLHttpRequest` and `Fetch` API in JavaScript?

`XMLHttpRequest` is the older API for making HTTP requests with callback-based syntax. `Fetch` API is modern, promise-based, and provides cleaner syntax with better error handling and streaming support.

```javascript
// XMLHttpRequest - older, callback-based
const xhr = new XMLHttpRequest();
xhr.open('POST', '/api/data');
xhr.setRequestHeader('Content-Type', 'application/json');
xhr.onreadystatechange = function() {
  if (xhr.readyState === 4 && xhr.status === 200) {
    console.log(JSON.parse(xhr.responseText));
  }
};
xhr.send(JSON.stringify({name: 'John'}));

// Fetch API - modern, promise-based
fetch('/api/data', {
  method: 'POST',
  headers: {'Content-Type': 'application/json'},
  body: JSON.stringify({name: 'John'})
}).then(res => res.json()).then(console.log);
```

### 128. What is the `Function.prototype.bind()` method in JavaScript?

`bind()` creates a new function with a specific `this` context and optionally pre-filled arguments. Unlike `call()` and `apply()`, it doesn't execute immediately but returns a bound function for later use.

```javascript
const person = {
  name: 'John',
  greet(greeting, punctuation) {
    return `${greeting}, I'm ${this.name}${punctuation}`;
  }
};

// Bind with specific context
const boundGreet = person.greet.bind(person);
console.log(boundGreet('Hello', '!')); // "Hello, I'm John!"

// Partial application
const sayHello = person.greet.bind(person, 'Hello');
console.log(sayHello('.')); // "Hello, I'm John."

// Event handlers
button.addEventListener('click', person.greet.bind(person, 'Hi', '!'));
```

### 129. How do you handle cookies in JavaScript?

Access cookies through `document.cookie` property. Create helper functions for setting, getting, and deleting cookies since the native API is string-based and requires manual parsing.

```javascript
// Set cookie
function setCookie(name, value, days) {
  const expires = new Date(Date.now() + days * 864e5).toUTCString();
  document.cookie = `${name}=${value}; expires=${expires}; path=/`;
}

// Get cookie
function getCookie(name) {
  return document.cookie.split('; ').find(row => row.startsWith(name + '='))
    ?.split('=')[1];
}

// Delete cookie
function deleteCookie(name) {
  document.cookie = `${name}=; expires=Thu, 01 Jan 1970 00:00:00 GMT; path=/`;
}

// Usage
setCookie('user', 'john123', 7);
const user = getCookie('user');
```

## **Advanced Concepts**
### 130. What is functional programming in JavaScript? How is it different from object-oriented programming?

Functional programming emphasizes pure functions, immutability, and function composition. OOP focuses on objects and classes with encapsulation and inheritance. FP avoids side effects, while OOP manages state through objects.

* **FP**

  * Pure functions, immutability, no side effects.
  * Compose small functions (`map`, `reduce`, `filter`).
  * Easier to reason about and test concurrent code.
* **OOP**

  * Encapsulates data and behavior in objects/classes.
  * Use inheritance/composition, mutable state is common.
  * Good for modeling domain entities and stateful systems.

```javascript
// Functional Programming
const add = (a, b) => a + b;
const multiply = (a, b) => a * b;
const compose = (f, g) => (x) => f(g(x));

const addThenMultiply = compose(x => multiply(x, 2), x => add(x, 5));
console.log(addThenMultiply(3)); // (3 + 5) * 2 = 16

// Object-Oriented Programming
class Calculator {
  constructor() { this.result = 0; }
  add(n) { this.result += n; return this; }
  multiply(n) { this.result *= n; return this; }
}

const calc = new Calculator().add(5).multiply(2); // 10
```

### 131. What is an abstract class in JavaScript?

JavaScript doesn't have built-in abstract classes, but you can simulate them by throwing errors in base class methods that must be implemented by subclasses. This enforces a contract for derived classes.

```javascript
class Animal {
  constructor(name) {
    if (this.constructor === Animal) {
      throw new Error('Cannot instantiate abstract class');
    }
    this.name = name;
  }
  
  // Abstract method
  makeSound() {
    throw new Error('makeSound method must be implemented');
  }
  
  // Concrete method
  sleep() {
    return `${this.name} is sleeping`;
  }
}

class Dog extends Animal {
  makeSound() {
    return `${this.name} barks`;
  }
}

// const animal = new Animal('Generic'); // Error
const dog = new Dog('Rex'); // Works
```

## **Performance Optimization**
### 132. What are memory leaks in JavaScript, and how do you prevent them?

Memory leaks occur when objects remain in memory despite being no longer needed. Common causes include global variables, event listeners, closures, and circular references. Prevent by cleaning up references and using weak references.

```javascript
// Common memory leaks and fixes

// 1. Global variables
// Bad: window.myData = largeObject;
// Good: Use local scope or clean up when done

// 2. Event listeners
const element = document.getElementById('button');
const handler = () => console.log('clicked');
element.addEventListener('click', handler);
// Clean up: element.removeEventListener('click', handler);

// 3. Timers
const intervalId = setInterval(() => {}, 1000);
// Clean up: clearInterval(intervalId);

// 4. Closures holding references
function createHandler() {
  const largeData = new Array(1000000);
  return () => console.log('handler'); // largeData stays in memory
}
```

### 133. What are decorators in JavaScript?

Decorators are a proposed JavaScript feature (currently stage 3) that allow you to modify classes and class members. They're functions that wrap other functions or classes to extend their behavior without modifying the original code.

```javascript
// Decorator proposal syntax (not yet standard)
function log(target, propertyKey, descriptor) {
  const originalMethod = descriptor.value;
  descriptor.value = function(...args) {
    console.log(`Calling ${propertyKey} with`, args);
    return originalMethod.apply(this, args);
  };
}

class Calculator {
  @log
  add(a, b) {
    return a + b;
  }
}

// Current workaround using higher-order functions
function withLogging(fn) {
  return function(...args) {
    console.log(`Calling function with`, args);
    return fn.apply(this, args);
  };
}

const add = withLogging((a, b) => a + b);
```

### 134. How can you optimize the performance of a JavaScript application?

Optimize by minimizing DOM manipulation, using efficient algorithms, lazy loading, code splitting, caching, debouncing events, and avoiding memory leaks. Profile with browser DevTools to identify bottlenecks.

* **Minimize DOM work**: batch DOM reads/writes, use `DocumentFragment`, limit reflows.
* **Debounce/throttle** expensive handlers (scroll, resize, input).
* **Avoid long-running sync code** — split work with `requestIdleCallback`, `setTimeout`, or Web Workers.
* **Use efficient data structures & algorithms** (avoid O(n²) loops when possible).
* **Cache results** (memoization) for repeated computations.
* **Use modern APIs**: `for...of`, `map/filter` where appropriate, but prefer simple loops for hot code.
* **Bundle & ship less**: tree-shaking, code-splitting, compress assets, serve over HTTP/2.
* **Profile** with browser DevTools and fix actual hotspots.

```javascript
// Performance optimization techniques

// 1. Debounce expensive operations
const debouncedSearch = debounce((query) => {
  // Expensive search operation
}, 300);

// 2. Use document fragments for multiple DOM updates
const fragment = document.createDocumentFragment();
items.forEach(item => {
  const element = document.createElement('div');
  element.textContent = item;
  fragment.appendChild(element);
});
container.appendChild(fragment); // Single DOM update

// 3. Lazy loading
const lazyLoad = (entries, observer) => {
  entries.forEach(entry => {
    if (entry.isIntersecting) {
      loadContent(entry.target);
      observer.unobserve(entry.target);
    }
  });
};
```

### 135. What is the significance of `requestAnimationFrame()` in JavaScript?

`requestAnimationFrame()` schedules animations to run at the browser's optimal frame rate (usually 60fps). It's more efficient than `setTimeout` for animations because it syncs with the display refresh rate and pauses when the tab is inactive.

```javascript
// Smooth animation with requestAnimationFrame
function animate() {
  // Update animation state
  element.style.left = position + 'px';
  position += speed;
  
  if (position < targetPosition) {
    requestAnimationFrame(animate); // Continue animation
  }
}

requestAnimationFrame(animate); // Start animation

// Compared to setTimeout (less efficient)
function animateWithTimeout() {
  element.style.left = position + 'px';
  position += speed;
  if (position < targetPosition) {
    setTimeout(animateWithTimeout, 16); // ~60fps, but not synced
  }
}

// Cancel animation
const animationId = requestAnimationFrame(animate);
cancelAnimationFrame(animationId);
```

### 136. What is lazy loading in JavaScript?

Lazy loading defers loading of resources until they're actually needed, improving initial page load performance. Common for images, components, and modules. Use Intersection Observer API for images or dynamic imports for code splitting.

```javascript
// Lazy loading images with Intersection Observer
const imageObserver = new IntersectionObserver((entries) => {
  entries.forEach(entry => {
    if (entry.isIntersecting) {
      const img = entry.target;
      img.src = img.dataset.src;
      img.classList.remove('lazy');
      imageObserver.unobserve(img);
    }
  });
});

document.querySelectorAll('img[data-src]').forEach(img => {
  imageObserver.observe(img);
});

// Lazy loading modules
const loadModule = async () => {
  const module = await import('./heavy-module.js');
  return module.default;
};
```

### 137. How do you improve the rendering performance of a website using JavaScript?

Minimize DOM manipulation, use document fragments, debounce scroll events, implement virtual scrolling, optimize CSS animations, and use `requestAnimationFrame` for smooth animations. Avoid layout thrashing and excessive reflows.

```javascript
// Batch DOM updates with document fragment
const fragment = document.createDocumentFragment();
items.forEach(item => {
  const element = document.createElement('div');
  element.textContent = item;
  fragment.appendChild(element);
});
container.appendChild(fragment); // Single reflow

// Debounce scroll events
const debouncedScroll = debounce(() => {
  // Handle scroll
}, 16); // ~60fps
window.addEventListener('scroll', debouncedScroll);

// Use CSS transforms for animations (GPU accelerated)
element.style.transform = `translateX(${position}px)`;
```

## **Frameworks and Libraries**
### 138. What are JavaScript frameworks, and how do they differ from libraries?

Frameworks provide a complete structure and control the application flow (inversion of control), while libraries are tools you call when needed. Frameworks like Angular dictate architecture, libraries like Lodash provide utilities you use as needed.

```javascript
// Library usage - you control the flow
import _ from 'lodash';
const result = _.map(array, item => item.value);

// Framework usage - framework controls the flow
// React component (library-like)
function MyComponent() {
  return <div>Hello World</div>;
}

// Angular component (framework)
@Component({
  selector: 'app-my-component',
  template: '<div>Hello World</div>'
})
export class MyComponent {}
```

## **Testing and Tools**
### 139. What is unit testing in JavaScript?

Unit testing verifies individual functions or components work correctly in isolation. Write small, focused tests that check specific behavior, use mocks for dependencies, and aim for high code coverage to catch bugs early.

```javascript
// Function to test
function add(a, b) {
  return a + b;
}

// Unit test with Jest
describe('add function', () => {
  test('should add two numbers correctly', () => {
    expect(add(2, 3)).toBe(5);
    expect(add(-1, 1)).toBe(0);
    expect(add(0, 0)).toBe(0);
  });
  
  test('should handle edge cases', () => {
    expect(add(0.1, 0.2)).toBeCloseTo(0.3);
  });
});
```

### 140. What are some popular testing frameworks in JavaScript?

Popular frameworks include Jest (comprehensive testing), Mocha (flexible test runner), Jasmine (behavior-driven), Cypress (end-to-end), and Vitest (fast Vite-based). Choose based on project needs and ecosystem compatibility.

```javascript
// Jest - most popular, built-in assertions
test('should calculate total', () => {
  expect(calculateTotal([1, 2, 3])).toBe(6);
});

// Mocha with Chai - flexible
describe('Calculator', () => {
  it('should add numbers', () => {
    chai.expect(add(2, 3)).to.equal(5);
  });
});

// Cypress - E2E testing
cy.visit('/login');
cy.get('[data-testid="username"]').type('user@example.com');
cy.get('[data-testid="submit"]').click();
```

### 141. What is TDD (Test-Driven Development)?

TDD follows Red-Green-Refactor cycle: write failing tests first (Red), write minimal code to pass (Green), then improve code quality (Refactor). This ensures code meets requirements and maintains high test coverage.

```javascript
// 1. Red - Write failing test first
test('should calculate user age', () => {
  expect(calculateAge('1990-01-01')).toBe(34); // This will fail initially
});

// 2. Green - Write minimal code to pass
function calculateAge(birthDate) {
  const birth = new Date(birthDate);
  const today = new Date();
  return today.getFullYear() - birth.getFullYear();
}

// 3. Refactor - Improve implementation
function calculateAge(birthDate) {
  const birth = new Date(birthDate);
  const today = new Date();
  let age = today.getFullYear() - birth.getFullYear();
  const monthDiff = today.getMonth() - birth.getMonth();
  if (monthDiff < 0 || (monthDiff === 0 && today.getDate() < birth.getDate())) {
    age--;
  }
  return age;
}
```

### 142. How do you write asynchronous tests in JavaScript?

Use async/await in test functions, return promises, or use done callbacks for older frameworks. Test both success and error scenarios, and set appropriate timeouts for async operations.

```javascript
// Jest with async/await
test('should fetch user data', async () => {
  const userData = await fetchUser(123);
  expect(userData.name).toBe('John Doe');
});

// Testing promises
test('should handle API errors', () => {
  return expect(fetchUser(999)).rejects.toThrow('User not found');
});

// Testing with done callback (older style)
test('should call callback', (done) => {
  asyncFunction((result) => {
    expect(result).toBe('success');
    done();
  });
});

// Mock async functions
jest.mock('./api');
const mockFetch = jest.fn().mockResolvedValue({name: 'John'});
```

### 143. What is the difference between `assert` and `expect` in JavaScript testing?

`assert` is Node.js built-in module with basic assertions, while `expect` (from libraries like Jest/Chai) provides more readable, chainable syntax with better error messages and more assertion methods.

```javascript
// Node.js assert - basic, throws on failure
const assert = require('assert');
assert.strictEqual(actual, expected);
assert.ok(value);

// Jest expect - more readable, better errors
expect(actual).toBe(expected);
expect(array).toContain(item);
expect(object).toHaveProperty('key', value);
expect(fn).toThrow('Error message');

// Chai expect - very expressive
expect(result).to.be.a('string').that.includes('hello');
expect(array).to.have.lengthOf(3);
```

## **Advanced JavaScript Features**
### 144. How do you use regular expressions in JavaScript?

Regular expressions match patterns in strings. Create with `/pattern/flags` or `new RegExp()`. Use methods like `test()`, `match()`, `replace()`, and `split()` for string operations and validation.

```javascript
// Creating regex
const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
const phoneRegex = new RegExp('^\\d{3}-\\d{3}-\\d{4}$');

// Testing patterns
emailRegex.test('user@example.com'); // true

// Extracting matches
const text = 'Call me at 555-123-4567';
const phoneMatch = text.match(/\d{3}-\d{3}-\d{4}/);

// Replacing patterns
const cleaned = text.replace(/\d/g, 'X'); // Replace all digits

// Common flags: g (global), i (case-insensitive), m (multiline)
const caseInsensitive = /hello/i;
```

### 145. What are Web Workers in JavaScript?

Web Workers run JavaScript in background threads, enabling parallel processing without blocking the main UI thread. Use for CPU-intensive tasks like data processing, image manipulation, or complex calculations.

```javascript
// Main thread
const worker = new Worker('worker.js');

worker.postMessage({data: largeArray, operation: 'sort'});

worker.onmessage = (event) => {
  const sortedData = event.data;
  console.log('Sorting completed:', sortedData);
};

// worker.js
self.onmessage = (event) => {
  const {data, operation} = event.data;
  
  if (operation === 'sort') {
    const sorted = data.sort((a, b) => a - b);
    self.postMessage(sorted);
  }
};

// Terminate worker when done
worker.terminate();
```

### 146. What are service workers in JavaScript, and how do they work?

Service workers are scripts that run in the background, acting as a proxy between your app and network. They enable offline functionality, push notifications, and background sync by intercepting network requests.

```javascript
// Register service worker
if ('serviceWorker' in navigator) {
  navigator.serviceWorker.register('/sw.js')
    .then(reg => console.log('SW registered'))
    .catch(err => console.log('SW registration failed'));
}

// sw.js - Service Worker
self.addEventListener('install', (event) => {
  event.waitUntil(
    caches.open('v1').then(cache => {
      return cache.addAll(['/index.html', '/app.js', '/styles.css']);
    })
  );
});

self.addEventListener('fetch', (event) => {
  event.respondWith(
    caches.match(event.request).then(response => {
      return response || fetch(event.request);
    })
  );
});
```

### 147. How do you compare two objects in JavaScript?

JavaScript objects are compared by reference, not value. For deep comparison, use libraries like Lodash or write custom recursive functions. JSON.stringify works for simple cases but has limitations.

```javascript
// Reference comparison (default)
const obj1 = {a: 1};
const obj2 = {a: 1};
console.log(obj1 === obj2); // false - different references

// Shallow comparison
function shallowEqual(obj1, obj2) {
  const keys1 = Object.keys(obj1);
  const keys2 = Object.keys(obj2);
  
  if (keys1.length !== keys2.length) return false;
  
  return keys1.every(key => obj1[key] === obj2[key]);
}

// Deep comparison (simple version)
function deepEqual(obj1, obj2) {
  return JSON.stringify(obj1) === JSON.stringify(obj2);
}

// Using Lodash
const isEqual = _.isEqual(obj1, obj2);
```

### 148. What are the differences between a function expression and a function declaration?

Function declarations are hoisted and can be called before definition. Function expressions are not hoisted and must be defined before use. Declarations create named functions, expressions can be anonymous.

```javascript
// Function Declaration - hoisted
console.log(declared()); // Works - "Hello"
function declared() {
  return 'Hello';
}

// Function Expression - not hoisted
console.log(expressed()); // Error - Cannot access before initialization
const expressed = function() {
  return 'Hello';
};

// Named function expression
const namedExpr = function myFunction() {
  return 'Hello';
};

// Arrow function expression
const arrow = () => 'Hello';
```

### 149. What is the difference between `slice()` and `splice()` methods in JavaScript?

`slice()` returns a shallow copy of a portion without modifying the original array. `splice()` changes the original array by removing, replacing, or adding elements and returns the removed elements.

```javascript
const arr = [1, 2, 3, 4, 5];

// slice() - non-destructive, returns new array
const sliced = arr.slice(1, 4); // [2, 3, 4]
console.log(arr); // [1, 2, 3, 4, 5] - unchanged

// splice() - destructive, modifies original
const spliced = arr.splice(1, 2, 'a', 'b'); // [2, 3] - removed elements
console.log(arr); // [1, 'a', 'b', 4, 5] - modified

// splice parameters: (start, deleteCount, ...itemsToAdd)
arr.splice(2, 0, 'inserted'); // Insert without removing
```

## **JavaScript Best Practices**
### 150. What is the importance of code minification in JavaScript?

Code minification removes whitespace, comments, and shortens variable names to reduce file size, improving load times and bandwidth usage. It's essential for production deployments but requires source maps for debugging.

```javascript
// Original code
function calculateTotal(items) {
  let total = 0;
  for (let i = 0; i < items.length; i++) {
    total += items[i].price;
  }
  return total;
}

// Minified version
function calculateTotal(t){let e=0;for(let l=0;l<t.length;l++)e+=t[l].price;return e}

// Build tools like Webpack, Rollup, or Terser handle minification
// webpack.config.js
module.exports = {
  optimization: {
    minimize: true,
    minimizer: [new TerserPlugin()]
  }
};

// Benefits: 30-90% size reduction, faster downloads, less bandwidth
```

### 151. How do you handle large-scale JavaScript applications?

Use modular architecture, code splitting, state management libraries, TypeScript for type safety, proper bundling strategies, and establish coding standards. Implement micro-frontends for very large applications and use performance monitoring tools.

```javascript
// Module structure
// src/
//   components/
//   services/
//   utils/
//   store/
//   types/

// Code splitting with dynamic imports
const LazyComponent = React.lazy(() => import('./HeavyComponent'));

// State management (Redux/Zustand)
const useAppStore = create((set) => ({
  user: null,
  setUser: (user) => set({ user }),
}));

// Service layer
class ApiService {
  static async getUser(id) {
    return fetch(`/api/users/${id}`).then(res => res.json());
  }
}
```

### 152. What are some security considerations when working with JavaScript?

Validate all inputs, sanitize data to prevent XSS attacks, use HTTPS, implement CSP headers, avoid eval(), secure API endpoints, use proper authentication, and keep dependencies updated to prevent vulnerabilities.

```javascript
// Input validation
function sanitizeInput(input) {
  return input.replace(/<script\b[^<]*(?:(?!<\/script>)<[^<]*)*<\/script>/gi, '');
}

// Avoid dangerous functions
// Bad: eval(userInput);
// Good: JSON.parse(userInput);

// CSP header (server-side)
// Content-Security-Policy: default-src 'self'; script-src 'self'

// Secure API calls
const token = localStorage.getItem('authToken');
fetch('/api/data', {
  headers: { 'Authorization': `Bearer ${token}` }
});

// Environment variables for sensitive data
const API_KEY = process.env.REACT_APP_API_KEY;
```

### 153. How do you manage JavaScript dependencies in a project?

Use package managers like npm or yarn, maintain package.json with exact versions, regularly audit for vulnerabilities, use lock files, implement dependency injection, and consider bundle size when adding new dependencies.

```javascript
// package.json - specify exact versions
{
  "dependencies": {
    "react": "18.2.0",
    "lodash": "^4.17.21"
  },
  "devDependencies": {
    "webpack": "~5.75.0"
  }
}

// Audit dependencies
// npm audit
// npm audit fix

// Bundle analysis
// npm install --save-dev webpack-bundle-analyzer

// Dependency injection pattern
class UserService {
  constructor(apiClient, logger) {
    this.api = apiClient;
    this.logger = logger;
  }
}

// Tree shaking - import only what you need
import { debounce } from 'lodash/debounce';
```

### 154. What is the importance of modularity in JavaScript development?

Modularity promotes code reusability, maintainability, testability, and team collaboration. It enables separation of concerns, easier debugging, and better organization. Use ES6 modules, design patterns, and clear interfaces between components.

```javascript
// Modular structure
// auth/
//   login.js
//   logout.js
//   index.js

// auth/login.js
export const login = async (credentials) => {
  // Login logic
};

// auth/index.js
export { login } from './login.js';
export { logout } from './logout.js';

// Usage
import { login, logout } from './auth';

// Module pattern for encapsulation
const UserModule = (() => {
  let users = [];
  
  return {
    addUser: (user) => users.push(user),
    getUsers: () => [...users],
    getUserCount: () => users.length
  };
})();
```

### 155. How do you document JavaScript code effectively?

Use JSDoc comments for functions and classes, write clear README files, maintain API documentation, use descriptive variable names, and include code examples. Consider tools like Storybook for component documentation.

```javascript
/**
 * Calculates the total price including tax
 * @param {number} price - The base price
 * @param {number} taxRate - Tax rate as decimal (0.1 for 10%)
 * @param {Object} options - Additional options
 * @param {boolean} options.includeShipping - Whether to include shipping
 * @returns {number} The total price including tax
 * @example
 * const total = calculateTotal(100, 0.08, { includeShipping: true });
 * console.log(total); // 108
 */
function calculateTotal(price, taxRate, options = {}) {
  let total = price * (1 + taxRate);
  if (options.includeShipping) {
    total += 10; // Standard shipping fee
  }
  return total;
}

// README.md structure
// # Project Name
// ## Installation
// ## Usage
// ## API Reference
// ## Contributing
```

### 156. How do you ensure cross-browser compatibility with JavaScript?

Use feature detection instead of browser detection, implement polyfills for missing features, test on multiple browsers, use transpilation tools like Babel, and follow web standards. Consider progressive enhancement strategies.

```javascript
// Feature detection
if ('serviceWorker' in navigator) {
  navigator.serviceWorker.register('/sw.js');
}

// Polyfill for older browsers
if (!Array.prototype.includes) {
  Array.prototype.includes = function(searchElement) {
    return this.indexOf(searchElement) !== -1;
  };
}

// Babel configuration for transpilation
// .babelrc
{
  "presets": [
    ["@babel/preset-env", {
      "targets": "> 0.25%, not dead"
    }]
  ]
}

// Progressive enhancement
const button = document.getElementById('submit');
if (button) {
  button.addEventListener('click', handleSubmit);
}
```

### 157. What are design patterns in JavaScript?

Design patterns are reusable solutions to common programming problems. Key patterns include Module, Observer, Singleton, Factory, and MVC. They improve code organization, maintainability, and communication between developers.

```javascript
// Singleton pattern
class Database {
  constructor() {
    if (Database.instance) {
      return Database.instance;
    }
    Database.instance = this;
    this.connection = null;
  }
  
  connect() {
    if (!this.connection) {
      this.connection = 'Connected to DB';
    }
    return this.connection;
  }
}

// Observer pattern
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

// Factory pattern
class UserFactory {
  static createUser(type, data) {
    switch (type) {
      case 'admin': return new AdminUser(data);
      case 'regular': return new RegularUser(data);
      default: throw new Error('Invalid user type');
    }
  }
}
```


# JavaScript Interview Quick Answers (Q1–Q7)

## **1. Coercion Rules**

JavaScript automatically converts values from one type to another when needed.
Two types:

### **Implicit Coercion (JS does it automatically)**

```js
"5" + 2   // "52"  (number → string)
"5" - 2   // 3     (string → number)
true + 1  // 2     (boolean → number)
```

Real-life usage:
When reading user input from a form, the value is a string, so coercion happens when you perform arithmetic.

### **Explicit Coercion (we do it)**

```js
Number("10")  // 10
String(20)    // "20"
Boolean(0)    // false
```

---

## **2. let, var, const (TDZ, Reference Error, Hoisting)**

### **var**

* Function-scoped
* Hoisted with **default value = undefined**
* No TDZ

```js
console.log(a)  // undefined
var a = 10
```

### **let / const**

* Block-scoped
* Hoisted but kept in **Temporal Dead Zone (TDZ)** until the line of declaration
* Access before declaration → **ReferenceError**

```js
console.log(b)  // ReferenceError (TDZ)
let b = 20
```

### **const**

* Must be initialized at declaration
* Cannot be reassigned (object properties *can* be changed)

---

## **3. Hoisting**

Hoisting means variable and function declarations are moved to the top of their scope during compilation.

### **Function declarations are fully hoisted**

```js
sayHi();        // Works
function sayHi() { console.log("Hi"); }
```

### **var is hoisted with undefined**

```js
console.log(x); // undefined
var x = 10;
```

### **let/const are hoisted but not initialized (TDZ)**

```js
console.log(y); // ReferenceError
let y = 20;
```

---

## **4. Primitive vs Non-Primitive Data Types**

### **Primitive (immutable, stored by value)**

* string
* number
* boolean
* null
* undefined
* bigint
* symbol

```js
let a = "Hello";
let b = a;
b = "Hi";  // 'a' stays same
```

### **Non-Primitive (mutable, stored by reference)**

* Object
* Array
* Function

```js
let obj1 = { name: "Sam" };
let obj2 = obj1;
obj2.name = "Mike";  // obj1 also updated
```

---

## **5. Async Execution: Event Loop, Microtask Queue, Macrotask Queue**

JavaScript is single-threaded. Async tasks are handled by the **Event Loop**, which decides what to run next.

### **Macrotask Queue**

* setTimeout
* setInterval
* DOM events

### **Microtask Queue**

* Promises (`.then`)
* MutationObserver
* queueMicrotask

**Microtasks always run before macrotasks.**

Example:

```js
console.log("Start");

setTimeout(() => console.log("Timeout"), 0);

Promise.resolve().then(() => console.log("Promise"));

console.log("End");
```

Output:

```
Start
End
Promise     // microtask first
Timeout     // macrotask
```

---

## **6. Closures (with simple code)**

A closure happens when a function "remembers" variables from its outer scope even after the outer function has finished.

```js
function outer() {
  let counter = 0;

  return function inner() {
    counter++;
    console.log(counter);
  };
}

const inc = outer();
inc(); // 1
inc(); // 2
```

Real use case: private variables.

---

## **7. Currying — Normal & Infinite**

### **Normal Currying**

Converts a function of multiple arguments into multiple single-argument functions.

```js
function add(a) {
  return function(b) {
    return function(c) {
      return a + b + c;
    };
  };
}

console.log(add(1)(2)(3)); // 6
```

### **Infinite Currying**

Useful for building dynamic parameter chains.

```js
function sum(a) {
  return function(b) {
    if (b !== undefined) return sum(a + b);
    return a;
  };
}

console.log(sum(1)(2)(3)(4)()); // 10
```

## **8. IIFE (Immediately Invoked Function Expression)**

An IIFE is a function that runs as soon as it’s defined — useful for creating private scope and avoiding global variables.

Classic IIFE:

```js
(function () {
  const secret = "shh";
  console.log("IIFE ran:", secret);
})();
```

Arrow IIFE (ES6):

```js
(() => {
  const x = 42;
  console.log("Arrow IIFE:", x);
})();
```

Real use: init code on module load, isolating temporary variables.

---

## **9. Normal vs Arrow Function**

**Normal function**

* Has its own `this` (dynamic, depends on call-site).
* Can be used as constructor (`new`).
* Has `arguments` object.

```js
function normal(a, b) { return a + b; }
```

**Arrow function**

* No own `this` — lexically inherits `this` from surrounding scope.
* Can't be used with `new`.
* No `arguments` object (use rest `...args` instead).

```js
const arrow = (a, b) => a + b;
```

When to use: arrow functions for short callbacks and preserving `this` from outer scope; normal functions when you need a dynamic `this`, `arguments`, or constructors.

---

## **10. `this` Keyword (with code examples)**

`this` value depends on how a function is called.

### As method:

```js
const user = {
  name: "Asha",
  greet() { console.log(this.name); }
};
user.greet(); // "Asha" — 'this' is the object
```

### As a plain function:

```js
function show() { console.log(this); }
show(); // in strict mode: undefined; otherwise global object
```

### With `call` / `apply` / `bind`:

```js
function say(g) { console.log(g, this.name); }
const o = { name: "Rohit" };

say.call(o, "Hello");            // Hello Rohit
const bound = say.bind(o, "Hi");
bound();                         // Hi Rohit
```

### Arrow function `this` (lexical):

```js
const obj = {
  name: "Neha",
  arrow: () => console.log(this?.name),
  method() { setTimeout(() => console.log(this.name), 0); }
};
obj.arrow();   // likely undefined (inherits outside 'this')
obj.method();  // "Neha" (arrow inside preserves obj's this)
```

Short explanation to say aloud: *“`this` is determined by the call-site — methods use the object, plain functions use global/undefined, arrow functions inherit `this` from the surrounding scope.”*

---

## **11. Prototypes and Prototypal Inheritance (code)**

Every JS object has an internal link to a prototype object. Properties are looked up the prototype chain.

### Example with constructor function:

```js
function Person(name) {
  this.name = name;
}
Person.prototype.greet = function () {
  return `Hi, I'm ${this.name}`;
};

const p = new Person("Rita");
console.log(p.greet());          // Hi, I'm Rita
console.log(Object.getPrototypeOf(p) === Person.prototype); // true
```

### Example with `class` (syntactic sugar):

```js
class Animal {
  constructor(kind) { this.kind = kind; }
  speak() { return `${this.kind} makes a sound`; }
}
const a = new Animal("Dog");
console.log(a.speak()); // Dog makes a sound
```

Explain briefly: *“Methods live on the prototype so instances share them — memory efficient, and the engine walks prototypes when a property isn’t on the object.”*

---

## **12. `map`, `reduce`, `filter`, `forEach` (practice questions)**

Short recap:

* `map` — transform array → returns new array of same length.
* `filter` — pick items that pass a test → returns new array (≤ length).
* `reduce` — fold array to single value (or object) via accumulator.
* `forEach` — iterate for side-effects, returns `undefined`.

### Quick examples:

```js
const nums = [1,2,3,4];

// map
const doubled = nums.map(n => n*2); // [2,4,6,8]

// filter
const evens = nums.filter(n => n%2 === 0); // [2,4]

// reduce (sum)
const sum = nums.reduce((acc, n) => acc + n, 0); // 10

// forEach
nums.forEach(n => console.log(n)); // logs 1 2 3 4
```

### Practice interview-style questions (say these out loud and walk through answers)

1. **Q:** Use `map` to convert `["1","2","3"]` to numbers.
   **A:** `arr.map(Number)`

2. **Q:** Use `filter` to remove falsy values from `[0,1,"",null,2]`.
   **A:** `arr.filter(Boolean)` → `[1,2]`

3. **Q:** Find max value with `reduce`.
   **A:** `arr.reduce((max, x) => x > max ? x : max, -Infinity)`

4. **Q:** Group items by property using `reduce`.
   **A:**

   ```js
   items.reduce((acc, item) => {
     (acc[item.type] = acc[item.type] || []).push(item);
     return acc;
   }, {});
   ```

5. **Q:** Convert `[[1,2],[3,4]]` to `[1,2,3,4]` using `reduce`.
   **A:** `arr.reduce((a,b) => a.concat(b), [])` (or use `flat()`)

These are excellent live-coding prompts — practice small variants.

---

## **13. `for…of` vs `for…in`**

* `for…in` iterates **enumerable property keys** (strings) of an object — includes inherited enumerable props. Use for objects (but beware of inherited keys).

```js
for (let key in obj) console.log(key);
```

* `for…of` iterates **iterable values** (Array, Map, Set, String, etc.) — uses the iterator protocol.

```js
for (let val of [10,20]) console.log(val);
```

Tip to say: *“Use `for...of` for arrays and iterables; use `for...in` when you must enumerate object keys (but prefer `Object.keys()` + `forEach` to avoid surprises).”*

---

## **14. Callback hell, Callbacks vs Higher-Order Functions (HOF)**

**Callback hell**

* When async callbacks are nested deeply and unreadably:

```js
doA(arg, (err, a) => {
  if (err) return;
  doB(a, (err, b) => {
    if (err) return;
    doC(b, (err, c) => {
      // ...
    });
  });
});
```

* Problems: readability, error handling, maintenance.

**Callback vs HOF**

* **Callback**: function passed as argument to another function to be invoked later.

  ```js
  arr.map(x => x*2); // arrow is a callback
  ```
* **Higher-Order Function (HOF)**: a function that takes functions as arguments or returns a function.

  ```js
  function makeAdder(a) { return b => a + b; } // returns a function
  [1,2,3].map(x => x*2); // map is a HOF
  ```

How to fix callback hell: Promises, async/await, modular helpers, or libraries that support composition.

---

## **15. Promises (`.then`, `.catch`, and static methods)**

### Basic Promise

```js
const p = new Promise((resolve, reject) => {
  setTimeout(() => resolve(42), 100);
});

p.then(value => console.log(value))    // 42
 .catch(err => console.error(err));
```

### Chaining & error handling

```js
doAsync()
  .then(result => doNext(result))
  .then(final => console.log(final))
  .catch(err => console.error("Any error here:", err));
```

`catch` handles rejections in the chain above it.

### `Promise.all`

* Waits for **all** promises to fulfill. If any reject → it rejects immediately.

```js
Promise.all([p1, p2]).then(values => ...).catch(err => ...);
```

### `Promise.allSettled`

* Waits for **all** to settle (either fulfilled or rejected), returns array of results with `{status, value/reason}`.

```js
Promise.allSettled([p1, p2]).then(results => console.log(results));
```

### `Promise.race`

* Resolves or rejects as soon as **one** promise settles (first settled).

```js
Promise.race([p1, p2]).then(first => ...).catch(err => ...);
```

### `Promise.any` (ES2021)

* Resolves as soon as **one** promise fulfills. If all reject → it rejects with `AggregateError`.

```js
Promise.any([p1, p2]).then(value => console.log(value)).catch(err => console.error(err));
```

### Practical notes to say:

* Use `Promise.all` for parallel tasks where all results are required.
* Use `allSettled` when you want results for all regardless of failures (reporting).
* Use `race` for timeouts or first-response logic.
* Use `any` when you need the first success and can ignore failures until all fail.

### Example: timeout with `race`

```js
function fetchWithTimeout(url, ms) {
  const fetchPromise = fetch(url);
  const timeout = new Promise((_, reject) =>
    setTimeout(() => reject(new Error("Timeout")), ms)
  );
  return Promise.race([fetchPromise, timeout]);
}
```

## 16. Bubbling, Capturing, Delegation

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

## 17. Local Storage (LS) vs Session Storage (SS) vs Cookies

**LocalStorage**
- Persistent until explicitly cleared
- Synchronous, stores strings, ~5–10MB
- Accessible only on the client (JS), not sent to server

**SessionStorage**
- Lives for the page session (tab). Cleared when tab/window closes
- Same API as localStorage, limited size

**Cookies**
- Small (~4KB), sent with every HTTP request by default (so used for authentication if configured)
- Can set expiry, HttpOnly, Secure, SameSite flags
- Server and client can read (unless HttpOnly)

**Quick advice:** Use localStorage for client-only persistent data (non-sensitive), sessionStorage for tab-lifetime state, and cookies for server-reading data (auth tokens — but prefer HttpOnly cookies or other secure patterns).

```javascript
// set localStorage
localStorage.setItem('theme', 'dark');
// get sessionStorage
const tmp = sessionStorage.getItem('temp');
// set cookie (simple)
document.cookie = 'token=abc; path=/; max-age=3600';
```

## 18. Strict Mode

`"use strict";` enables strict mode which tightens JavaScript semantics and catches common bugs.

**Effects:**
- Disallows undeclared variables (throws ReferenceError)
- `this` in functions defaults to `undefined` (not global)
- Prevents duplicate parameter names
- Disallows `with` statement
- Throws on assigning to non-writable properties

```javascript
'use strict';
function f() {
  // x = 10; // ReferenceError: x is not defined
  console.log(this); // undefined in strict mode
}
```

**Why use it:** better error-checking and safer code; recommended in modules (modules are strict by default).

## 19. Web Workers

Web Workers let you run scripts in background threads so CPU-heavy tasks don't block the main UI thread.

**Types:**
- **Dedicated Worker** (`new Worker('worker.js')`) — one-to-one with the script
- **Shared Worker** — can be shared across multiple scripts/tabs (limited browser support)

**Communication:** message passing via `postMessage` and `onmessage` (no shared mutable state, data is cloned or transferred).

```javascript
// main.js
const w = new Worker('worker.js');
w.postMessage({ cmd: 'start', data: 1000000 });
w.onmessage = (e) => console.log('result', e.data);
```

```javascript
// worker.js
self.onmessage = (e) => {
  const n = e.data.data;
  let total = 0;
  for (let i = 0; i < n; i++) total += i;
  self.postMessage(total);
};
```

**When to use:** heavy computations, image processing, large data parsing; not for DOM access (workers don't have DOM access).

## 20. call, apply, bind (code)

- `call(thisArg, ...args)` — invoke immediately with provided this and args
- `apply(thisArg, argsArray)` — same as call but arguments passed as array
- `bind(thisArg, ...args)` — returns a new function with bound this and optionally preset args

```javascript
function greet(greeting, punc) {
  console.log(greeting + ', ' + this.name + punc);
}
const person = { name: 'Sam' };

greet.call(person, 'Hello', '!');          // Hello, Sam!
greet.apply(person, ['Hi', '...']);        // Hi, Sam...

const bound = greet.bind(person, 'Hey');
bound('!!!');                              // Hey, Sam!!!
```

**Practical tip:** bind is great for keeping `this` correct when passing methods as callbacks (e.g., event handlers).

## 21. Shallow vs Deep Copy (code)

**Shallow copy** copies top-level properties; nested objects are shared by reference.

**Deep copy** recursively copies all nested structures so the new object is fully independent.

**Shallow copy examples:**

```javascript
const obj = { a: 1, b: { c: 2 } };
const shallow = Object.assign({}, obj);
// or
const spread = { ...obj };
shallow.b.c = 99; // obj.b.c also changes (shared reference)
```

**Deep copy examples:**

```javascript
// 1) Simple/fast (only for JSON-safe data: no functions, undefined, Dates, Maps, Sets)
const deep1 = JSON.parse(JSON.stringify(obj));

// 2) Structured clone (modern, handles more types)
const deep2 = structuredClone(obj); // browser/node support varies

// 3) Utility libraries e.g., lodash's _.cloneDeep(obj)
```

**When to use:** shallow copy is fine for flat objects or when you intentionally share nested refs; deep copy is needed when you must fully isolate the clone.

## 22. Object.freeze, Object.seal

`Object.freeze(obj)` — makes object immutable: properties cannot be added, removed, or changed (shallow freeze). Attempts to modify fail silently in non-strict mode or throw in strict mode.

`Object.seal(obj)` — prevents adding/removing properties but allows changing existing property values. Also shallow.

```javascript
const o = { a: 1 };
Object.seal(o);
o.a = 2;       // allowed
// o.b = 3;    // ignored or throws in strict mode

const f = { x: 10 };
Object.freeze(f);
// f.x = 20;   // ignored or throws in strict mode
```

**Note:** to fully freeze nested objects you must recursively freeze each nested object.

```javascript
function deepFreeze(obj) {
  Object.keys(obj).forEach(k => {
    if (typeof obj[k] === 'object' && obj[k] !== null) deepFreeze(obj[k]);
  });
  return Object.freeze(obj);
}
```

# **23. Debouncing & Throttling**

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

---

# **24. SOLID Principles (with quick JavaScript examples)**

**S — Single Responsibility**
One class = one job.

```js
class Logger {
  log(msg) { console.log(msg); }
}
```

**O — Open/Closed**
Open for extension, closed for modification.

```js
class Payment {
  pay() {}
}
class UpiPayment extends Payment {
  pay() { console.log("UPI Pay"); }
}
```

**L — Liskov Substitution**
Child should replace parent without breaking.

**I — Interface Segregation**
Don’t force unused methods.

**D — Dependency Injection**

```js
class Service {
  constructor(db) { this.db = db; }
}
```

---

# **25. Design Patterns (simple JS examples)**

### **Factory Pattern**

```js
function createUser(type) {
  if (type === "admin") return { role: "admin" };
  return { role: "user" };
}
```

### **Singleton Pattern**

```js
const appConfig = (function () {
  let instance;
  return {
    getInstance() {
      if (!instance) instance = { url: "prod.com" };
      return instance;
    }
  };
})();
```

### **Observer Pattern**

```js
class Observable {
  constructor() { this.subs = []; }
  subscribe(fn) { this.subs.push(fn); }
  notify(data) { this.subs.forEach(fn => fn(data)); }
}
```

---

# **26. Array, String, Object Methods (quick real-world ones)**

### **Array**

* `map()` → transform list
* `filter()` → remove unwanted items
* `reduce()` → sum, aggregate
* `find()` → find one item
* `some()/every()`
* `flat()/flatMap()`
* `push(), pop(), shift(), unshift()`

```js
const nums = [1,2,3];
nums.map(x => x*2); // [2,4,6]
```

### **String**

* `includes()`
* `trim()`
* `split()`
* `replace()`
* `toLowerCase()`, `toUpperCase()`

```js
" Hello ".trim(); 
```

### **Object**

* `Object.keys()`, `Object.values()`, `Object.entries()`
* `Object.assign()`
* Spread `...`

```js
const obj = {a:1, b:2};
Object.keys(obj); // ["a","b"]
```

---

# **27. Nullish Coalescing Operator (??)**

It returns the **right side only if the left side is null or undefined**.

```js
let value = 0 ?? 10;  // 0  (because 0 is not null/undefined)
let name = null ?? "Guest"; // "Guest"
```

Best use: default values without breaking on falsy values.

---

# **28. null vs undefined**

**undefined** → variable declared but not assigned.
**null** → developer intentionally sets “no value”.

```js
let a;
console.log(a); // undefined

let b = null;
console.log(b); // null
```

# 29. Ways to write async code

“JavaScript async patterns: **callbacks**, **Promises**, **async/await**, and **Observables** (RxJS) for streams. Callbacks are old-school but lead to nesting; Promises normalize async results; async/await makes Promise code look synchronous; Observables handle many-value streams like UI events or websockets.”

```js
// Promise
fetch(url).then(r => r.json()).then(data => console.log(data));

// async/await
async function getData(){ const res = await fetch(url); console.log(await res.json()); }
```

# 30. Promise vs async-await

“**Promise** is the primitive — an object representing a future value. **async/await** is syntax sugar over Promises that makes code easier to read and reason about; `await` pauses inside an `async` function and returns the resolved value or throws on rejection.”

```js
// Promise
fetch(url).then(r => r.json()).catch(err => console.error(err));

// async/await
async function f(){
  try {
    const r = await fetch(url);
    const data = await r.json();
  } catch(err) { console.error(err); }
}
```

# 31. DOM vs BOM

“**DOM (Document Object Model)** is the API for HTML/XML document structure — nodes, elements, attributes. **BOM (Browser Object Model)** covers browser-specific objects not part of the page — `window`, `navigator`, `location`, `history`, `screen`. Use DOM to manipulate page content and BOM to interact with the browser environment.”

```js
// DOM
document.querySelector('#title').textContent = 'Hello';

// BOM
console.log(window.location.href);
```

# 32. setTimeout, setInterval (how to stop setInterval)

“`setTimeout` schedules a one-off callback after delay; `setInterval` repeats a callback every interval. Save the returned id and call `clearInterval(id)` (or `clearTimeout(id)` for timeouts) to stop them.”

```js
// setTimeout
const t = setTimeout(() => console.log('once'), 1000);
clearTimeout(t);

// setInterval
const id = setInterval(() => console.log('tick'), 1000);
setTimeout(() => clearInterval(id), 5000); // stop after 5s
```

# 33. Arrow vs normal function

“Arrow functions have concise syntax, **no own `this`/`arguments`**, and cannot be used as constructors. Use arrows for short callbacks and when you want lexical `this`; use normal functions when you need dynamic `this`, `arguments`, or to create instances.”

```js
const obj = {
  value: 10,
  // arrow — inherits this (bad for methods)
  arrowMethod: () => console.log(this.value),
  // normal — has own this (good)
  normalMethod() { console.log(this.value); }
};
```

# 34. Generators & Iterators

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

### 35. How to increase performance of JS

* **Minimize DOM work**: batch DOM reads/writes, use `DocumentFragment`, limit reflows.
* **Debounce/throttle** expensive handlers (scroll, resize, input).
* **Avoid long-running sync code** — split work with `requestIdleCallback`, `setTimeout`, or Web Workers.
* **Use efficient data structures & algorithms** (avoid O(n²) loops when possible).
* **Cache results** (memoization) for repeated computations.
* **Use modern APIs**: `for...of`, `map/filter` where appropriate, but prefer simple loops for hot code.
* **Bundle & ship less**: tree-shaking, code-splitting, compress assets, serve over HTTP/2.
* **Profile** with browser DevTools and fix actual hotspots.

Example — debounce:

```js
function debounce(fn, ms=200){
  let t;
  return (...args) => { clearTimeout(t); t = setTimeout(() => fn(...args), ms); };
}
window.addEventListener('resize', debounce(()=> console.log('resized'), 150));
```

---

### 36. New ES6 features (short list)

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

---

### 37. `class`, `constructor`, `super` (example)

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

---

### 38. Fetch vs Axios (quick comparison)

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

---

### 39. REST API vs GraphQL (short)

* **REST**

  * Resources as endpoints (GET `/users/1`).
  * Multiple endpoints for different resources; over-/under-fetching possible.
  * Simple caching with HTTP semantics.
* **GraphQL**

  * Single endpoint; client requests exactly the shape of data.
  * Prevents over-/under-fetching, good for complex UI needs.
  * Strongly typed schema; easier to evolve fields.
  * More complex caching & batching; more server logic needed (resolvers).

Use REST when APIs are simple or you want HTTP caching. Use GraphQL when the client needs flexible queries, joins, or reduces round-trips.

---

### 40. Functional programming (FP) vs Object-oriented programming (OOP)

* **FP**

  * Pure functions, immutability, no side effects.
  * Compose small functions (`map`, `reduce`, `filter`).
  * Easier to reason about and test concurrent code.
* **OOP**

  * Encapsulates data and behavior in objects/classes.
  * Use inheritance/composition, mutable state is common.
  * Good for modeling domain entities and stateful systems.

They’re complementary: use FP for data transforms and pure logic; OOP for stateful models and structure. Example mixing both:

```js
// FP-style transform on objects (OOP data)
const users = [{name:'A',age:20}, {name:'B', age:30}];
const names = users.filter(u => u.age>21).map(u => u.name);
```

---

### 41. OOP concepts (short definitions + tiny example)

* **Class** — blueprint for objects.
* **Object** — instance of a class.
* **Encapsulation** — hide internal state, expose methods.
* **Inheritance** — subclass shares behavior from parent (`extends`).
* **Polymorphism** — same interface, different implementations.
* **Abstraction** — expose essential features, hide complexity.

Tiny example summarizing:

```js
class Vehicle {               // class, abstraction
  constructor(speed){ this._speed = speed; } // encapsulation (_private by convention)
  move(){ console.log('moving'); } // polymorphism via override
}
class Car extends Vehicle {    // inheritance
  move(){ console.log('car driving'); } // polymorphism
}
```


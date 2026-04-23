1 What is JavaScript?
JavaScript is a lightweight, interpreted, high-level programming language primarily used for web
development. It is single-threaded, dynamically typed, and supports event-driven, functional, and
object-oriented programming paradigms. JS runs in browsers (via engines like V8, SpiderMonkey)
and on servers via Node.js.
■ It is the only language natively supported by all browsers.
2 Difference between var, let, const?
var is function-scoped, hoisted (initialised as undefined), and can be redeclared. let is
block-scoped, hoisted but NOT initialised (Temporal Dead Zone), cannot be redeclared. const is
block-scoped, hoisted but NOT initialised, must be initialised at declaration, and cannot be
reassigned (though object properties can change).
Example / Code:
var x = 1; let y = 2; const Z = 3; { var x = 10; }console.log(x); // 10 (leaked) {
let y = 20; } console.log(y); // 2 (block scoped)
■ Prefer const by default, use let when re-assignment is needed, avoid var.
3 What is hoisting?
Hoisting is JavaScript's behaviour of moving declarations to the top of their scope before execution.
var declarations are hoisted and initialised with undefined. Function declarations are fully hoisted
(name + body). let/const are hoisted but remain in the Temporal Dead Zone until the declaration
line.
Example / Code:
console.log(a); // undefined (hoisted) var a = 5; console.log(b); //
ReferenceError (TDZ) let b = 10;
4 What is Temporal Dead Zone (TDZ)?
TDZ is the period between entering a block scope and the actual declaration of a let/const variable.
Accessing the variable in this zone throws a ReferenceError. It exists to catch
usage-before-declaration bugs.
Example / Code:
{ console.log(x); // ReferenceError (TDZ) let x = 5; console.log(x); // 5 }
5 What are data types in JS?
Primitive: string, number, bigint, boolean, undefined, null, symbol (7 types). Reference/Object:
object, array, function, date, map, set, etc. Primitives are immutable and stored by value; objects
are stored by reference.
Example / Code:
typeof 'hello' // 'string' typeof 42 // 'number' typeof true // 'boolean' typeof
undefined// 'undefined' typeof null // 'object' (famous bug) typeof {} //
'object' typeof [] // 'object'
6 Difference between null and undefined?
undefined means a variable has been declared but not assigned a value. null is an intentional
absence of value — explicitly set by the programmer. typeof undefined === 'undefined'; typeof null
=== 'object' (legacy bug).
Example / Code:
let a; // undefined let b = null; // null console.log(a == b); // true (loose)
console.log(a === b); // false (strict)
7 What is NaN?
NaN (Not a Number) is a special numeric value that represents an invalid or unrepresentable
mathematical result. It is of type 'number'. NaN is the only value in JS that is not equal to itself.
Example / Code:
console.log(0/0); // NaN console.log('abc' * 2); // NaN console.log(NaN === NaN);
// false console.log(Number.isNaN(NaN)); // true
■ Always use Number.isNaN() instead of isNaN() which coerces the argument first.
8 typeof NaN returns what?
typeof NaN returns 'number'. This is because NaN is part of the IEEE 754 floating-point
specification and is technically a numeric value that represents an invalid number.
Example / Code:
console.log(typeof NaN); // 'number'
9 What is type coercion?
Type coercion is JavaScript's automatic conversion of values from one type to another. Implicit
coercion happens automatically (e.g., '5' + 3 = '53'); explicit coercion uses Number(), String(),
Boolean() etc.
Example / Code:
console.log('5' + 3); // '53' (string concat) console.log('5' - 3); // 2 (numeric
sub) console.log('' == false); // true (coercion) console.log(Number('5')); // 5
(explicit)
10 == vs ===?
== is loose equality — it performs type coercion before comparing. === is strict equality — it
compares both value AND type without coercion. Always prefer === to avoid unexpected bugs.
Example / Code:
console.log(0 == '0'); // true (coerced) console.log(0 === '0'); // false
(different types) console.log(null == undefined); // true console.log(null ===
undefined); // false
11 What is truthy and falsy?
Falsy values are those that evaluate to false in a boolean context: false, 0, -0, 0n, '', null, undefined,
NaN. Everything else is truthy. This matters in if-conditions, logical operators, and ternary
expressions.
Example / Code:
if ('') console.log('no'); // falsy - skipped if ('hello') console.log('yes'); //
truthy if (0) {} // falsy if ([]) {} // truthy (even empty array!)
■ Empty array [] and empty object {} are TRUTHY.
12 What are primitive types?
Primitives are immutable data types stored directly on the stack: string, number, bigint, boolean,
undefined, null, symbol. They are copied by value when assigned. Operations on primitives return
new values; they never mutate in place.
Example / Code:
let a = 'hello'; let b = a; // copy b = 'world'; console.log(a); // 'hello'
(unchanged)
13 What are reference types?
Reference types (objects, arrays, functions) are stored on the heap; variables hold a reference
(pointer) to the location. Assignment copies the reference, not the value — so two variables can
point to the same object.
Example / Code:
let obj1 = { x: 1 }; let obj2 = obj1; // same ref obj2.x = 99;
console.log(obj1.x); // 99 (both changed)
14 What is a function?
A function is a reusable block of code that performs a specific task. In JS, functions are first-class
citizens — they can be assigned to variables, passed as arguments, and returned from other
functions.
Example / Code:
function greet(name) { return 'Hello, ' + name; } console.log(greet('Alice')); //
Hello, Alice
15 Function declaration vs expression?
Declaration: uses the 'function' keyword at the statement level — fully hoisted. Expression: a
function assigned to a variable — NOT hoisted (the variable is hoisted but as undefined). Arrow
functions are always expressions.
Example / Code:
// Declaration (hoisted) console.log(add(2,3)); // 5 function add(a,b){ return
a+b; } // Expression (not hoisted) console.log(mul(2,3)); // TypeError const mul
= function(a,b){ return a*b; };
16 What is an arrow function?
Arrow functions are a concise ES6 syntax for functions using =>. They do NOT have their own 'this',
'arguments', 'super', or 'new.target'. They cannot be used as constructors.
Example / Code:
const add = (a, b) => a + b; const square = x => x * x; const greet = () =>
'Hello'; console.log(add(2, 3)); // 5
17 Difference between arrow and normal function?
1) this: Arrow inherits from enclosing scope; normal has its own this. 2) arguments: Arrow has none;
normal has arguments object. 3) Constructor: Arrow cannot be used with new. 4) Hoisting: Normal
declarations are hoisted; arrow expressions are not. 5) prototype: Arrow functions have no
prototype property.
Example / Code:
function Foo() { this.x = 1; } const Bar = () => {}; new Foo(); // OK new Bar();
// TypeError: Bar is not a constructor
18 What is IIFE?
Immediately Invoked Function Expression — a function that is defined and called immediately.
Used to create a private scope, avoid polluting the global namespace, and encapsulate logic.
Common pattern before ES6 modules.
Example / Code:
(function() { var secret = 42; console.log(secret); // 42 })();
console.log(typeof secret); // 'undefined'
19 What is scope?
Scope determines where variables are accessible. JS has lexical scoping — scope is determined at
write-time, not runtime. Variables are looked up by the engine by traversing from the innermost
scope outward until found.
Example / Code:
let global = 'G'; function outer() { let out = 'O'; function inner() { let inn =
'I'; console.log(global, out, inn); // G O I } inner(); }
20 Types of scope?
1) Global scope — accessible everywhere. 2) Function scope — inside a function (var). 3) Block
scope — inside {} (let, const). 4) Module scope — variables in ES modules are scoped to the
module. 5) Lexical scope — functions remember where they were defined.
Example / Code:
var g = 'global'; function fn() { var f = 'function'; if(true) { let b = 'block';
console.log(b); // 'block' } // console.log(b); // ReferenceError }
■ Advanced Core (Q21–Q50)
21 What is closure?
A closure is a function that remembers and accesses variables from its outer (enclosing) scope
even after that outer function has finished executing. Closures are created every time a function is
created.
Example / Code:
function counter() { let count = 0; return function() { count++; return count; };
} const inc = counter(); console.log(inc()); // 1 console.log(inc()); // 2
■ Closures are the basis for data privacy, module pattern, memoization.
22 Practical use of closures?
1) Data privacy / encapsulation. 2) Function factories. 3) Memoization. 4) Partial application /
currying. 5) Event handlers that remember state. 6) Module pattern (before ES6 modules).
Example / Code:
// Factory example function multiplier(x) { return (y) => x * y; } const double =
multiplier(2); console.log(double(5)); // 10 console.log(double(9)); // 18
23 What is lexical scope?
Lexical scope means a function's scope is determined by where it is defined in the source code, not
where it is called. Inner functions have access to variables of outer functions due to the scope chain
formed at parse time.
Example / Code:
function outer() { let x = 10; function inner() { console.log(x); // 10 - lexical
scope } return inner; } const fn = outer(); fn(); // 10 (even after outer
finished)
24 What is execution context?
An Execution Context is the environment in which JS code runs. It contains: Variable Object
(variables, functions), Scope Chain, and 'this'. Types: Global EC (created on start), Function EC
(per function call), Eval EC (rare).
Example / Code:
// Each function call creates a new EC function A() { let a = 1; function B() {
let b = 2; // EC has: b, scope chain -> A's EC -> global EC } B(); }
25 What is call stack?
The call stack is a LIFO (Last In First Out) data structure that tracks active execution contexts.
When a function is called, its EC is pushed; when it returns, it's popped. Stack overflow happens
when recursion is too deep.
Example / Code:
function A() { B(); } function B() { C(); } function C() { console.log('C'); }
A(); // Stack: [Global, A, B, C] -> C pops -> B pops -> A pops
26 What is event loop?
The event loop continuously monitors the call stack and callback queue. When the call stack is
empty, it takes the first callback from the queue and pushes it onto the stack. This allows
asynchronous operations (setTimeout, I/O) in a single-threaded environment.
Example / Code:
console.log('1'); setTimeout(() => console.log('2'), 0);
Promise.resolve().then(() => console.log('3')); console.log('4'); // Output: 1,
4, 3, 2 // (microtasks before macrotasks)
■ Microtask queue (Promises) is drained before the next macrotask (setTimeout).
27 Microtask vs macrotask?
Microtasks (Promise callbacks, queueMicrotask, MutationObserver) run after the current task and
before any macrotask. Macrotasks (setTimeout, setInterval, setImmediate, I/O) are processed one
per event loop tick. All microtasks are flushed before the next macrotask.
Example / Code:
setTimeout(() => console.log('macro'), 0); Promise.resolve().then(() =>
console.log('micro')); // Output: micro, macro
28 setTimeout vs setImmediate?
setTimeout(fn, 0) schedules after the minimum delay (1ms+), in the timers phase of the event loop.
setImmediate (Node.js only) schedules after I/O events, in the check phase. In the top-level script
the order is non-deterministic; inside I/O callbacks setImmediate always fires first.
Example / Code:
// Inside I/O callback (Node.js): fs.readFile('f', () => { setTimeout(() =>
console.log('timeout'), 0); setImmediate(() => console.log('immediate')); }); //
Always: immediate, timeout
29 What is callback?
A callback is a function passed as an argument to another function, to be executed later
(asynchronously or after some task). It was the original pattern for async code in JavaScript before
Promises and async/await.
Example / Code:
function fetchData(url, callback) { // simulate async setTimeout(() =>
callback(null, {data: 'result'}), 1000); } fetchData('/api', (err, data) => {
if(err) return console.error(err); console.log(data); });
30 What is callback hell?
Callback hell (pyramid of doom) is deeply nested callbacks that make code hard to read, debug,
and maintain. Caused by multiple sequential async operations using callbacks. Solved by
Promises, async/await, or named functions.
Example / Code:
getUser(id, (user) => { getPosts(user, (posts) => { getComments(posts[0],
(comments) => { // deeply nested - hard to maintain }); }); });
■ Use async/await to flatten this into readable sequential code.
31 What is a promise?
A Promise is an object representing the eventual completion or failure of an asynchronous
operation. It has three states: pending, fulfilled, rejected. Created with new Promise((resolve, reject)
=> {}). Enables chaining with .then()/.catch()/.finally().
Example / Code:
const p = new Promise((resolve, reject) => { setTimeout(() => resolve('done'),
1000); }); p.then(val => console.log(val)) // 'done' .catch(err =>
console.error(err));
32 Promise states?
1) Pending — initial state, neither fulfilled nor rejected. 2) Fulfilled — operation completed
successfully, resolve() was called. 3) Rejected — operation failed, reject() was called. Once settled
(fulfilled or rejected) a promise is immutable.
Example / Code:
let p = new Promise(res => setTimeout(res, 1000)); console.log(p); // Promise {
<pending> } setTimeout(() => console.log(p), 1100); // Promise { 'undefined' }
(fulfilled)
33 async/await?
async/await is syntactic sugar over Promises. An async function always returns a Promise. await
pauses execution inside the async function until the Promise resolves, making async code look
synchronous. Errors are caught with try/catch.
Example / Code:
async function fetchUser(id) { try { const res = await fetch(`/api/users/${id}`);
const user = await res.json(); return user; } catch(err) {
console.error('Error:', err); } }
34 Error handling in async/await?
Use try/catch blocks inside async functions to handle rejected Promises. Alternatively, chain
.catch() on the async function call. Unhandled Promise rejections should be caught at the top level
with process.on('unhandledRejection') in Node.
Example / Code:
async function getData() { try { const data = await riskyOperation(); return
data; } catch(error) { console.error('Caught:', error.message); throw error; //
re-throw if needed } finally { console.log('Always runs'); } }
35 What is 'this' keyword?
'this' refers to the context in which a function is called. Its value depends on HOW the function is
called: 1) Global context: window (non-strict) or undefined (strict). 2) Method call: the object. 3)
Constructor: the new object. 4) Arrow: inherits from enclosing scope. 5) call/apply/bind: explicit this.
Example / Code:
const obj = { name: 'Alice', greet() { return 'Hi ' + this.name; }, greetArrow: ()
=> 'Hi ' + this.name // this = global! }; console.log(obj.greet()); // Hi Alice
console.log(obj.greetArrow()); // Hi undefined
36 this in arrow vs normal function?
Normal functions get 'this' dynamically based on how they are called. Arrow functions do NOT have
their own 'this' — they capture 'this' from the surrounding lexical context at definition time. This
makes arrow functions ideal for callbacks inside methods.
Example / Code:
function Timer() { this.ticks = 0; // Arrow captures Timer's 'this'
setInterval(() => this.ticks++, 1000); } const t = new Timer(); // works
correctly
37 bind vs call vs apply?
call(thisArg, arg1, arg2) — invokes the function immediately with given this and individual
arguments. apply(thisArg, [args]) — same as call but arguments as array. bind(thisArg, arg1) —
returns a NEW function with this permanently bound; doesn't call immediately.
Example / Code:
function greet(greeting, punct) { return `${greeting}, ${this.name}${punct}`; }
const obj = { name: 'Bob' }; greet.call(obj, 'Hello', '!'); // Hello, Bob!
greet.apply(obj, ['Hi', '.']); // Hi, Bob. const fn = greet.bind(obj, 'Hey');
fn('?'); // Hey, Bob?
38 What is prototype?
Every JavaScript object has an internal [[Prototype]] property pointing to another object (its
prototype). Property lookup walks the prototype chain — if a property isn't found on the object, JS
looks on its prototype, and so on until null.
Example / Code:
function Animal(name) { this.name = name; } Animal.prototype.speak = function() {
return this.name + ' makes a sound'; }; const dog = new Animal('Dog');
console.log(dog.speak()); // Dog makes a sound
console.log(dog.hasOwnProperty('speak')); // false
39 Prototype chain?
The prototype chain is the series of linked prototypes from an object up to Object.prototype (whose
prototype is null). This is how inheritance works in JS. Object.getPrototypeOf() retrieves the
prototype of an object.
Example / Code:
const arr = [1, 2, 3]; // arr -> Array.prototype -> Object.prototype -> null
console.log(arr.__proto__ === Array.prototype); // true
console.log(Array.prototype.__proto__ === Object.prototype); // true
40 What is inheritance in JS?
JS uses prototypal inheritance — objects inherit directly from other objects via the prototype chain.
ES6 classes provide syntactic sugar over this. With classes, 'extends' sets up the prototype chain,
and 'super' calls the parent constructor.
Example / Code:
class Animal { constructor(name) { this.name = name; } speak() { return
`${this.name} speaks`; } } class Dog extends Animal { speak() { return
super.speak() + ' (woof)'; } } const d = new Dog('Rex'); console.log(d.speak());
// Rex speaks (woof)
41 Object.create vs class?
Object.create(proto) creates a new object with the specified prototype — direct prototypal
inheritance, no constructor needed. Class syntax is more familiar OOP style, uses constructors,
supports extends and super. Both use prototype chains under the hood.
Example / Code:
// Object.create const animal = { speak() { return 'generic sound'; } }; const dog
= Object.create(animal); dog.name = 'Rex'; console.log(dog.speak()); // generic
sound
42 What is class in JS?
ES6 classes are syntactic sugar over constructor functions and prototype chains. They support
constructor, methods, static methods, inheritance (extends), and private fields (#). They are NOT
hoisted like function declarations.
Example / Code:
class Person { #age; // private field constructor(name, age) { this.name = name;
this.#age = age; } getAge() { return this.#age; } static create(n, a) { return new
Person(n, a); } } const p = Person.create('Alice', 30); console.log(p.getAge());
// 30
43 Constructor function?
A constructor function is a regular function called with 'new'. When called with new: 1) A new empty
object is created. 2) 'this' is set to that object. 3) The prototype is linked. 4) The object is returned
(unless an object is explicitly returned).
Example / Code:
function Person(name) { this.name = name; this.greet = function() { return 'Hi, I
am ' + this.name; }; } const p = new Person('Alice'); console.log(p.greet()); //
Hi, I am Alice
44 What is the new keyword?
The new keyword: 1) Creates a blank object. 2) Sets its [[Prototype]] to the constructor's .prototype.
3) Executes the constructor with 'this' = new object. 4) Returns the object (or the explicitly returned
object if it's an object).
Example / Code:
function Car(model) { this.model = model; } Car.prototype.drive = function() {
return 'Driving ' + this.model; }; const c = new Car('Tesla');
console.log(c.drive()); // Driving Tesla console.log(c instanceof Car); // true
45 What is shallow copy?
A shallow copy copies the top-level properties. Nested objects/arrays still reference the same
memory — modifying them in the copy affects the original. Created via Object.assign(), spread
operator {...obj}, or Array.from().
Example / Code:
const original = { a: 1, nested: { b: 2 } }; const copy = { ...original }; copy.a
= 99; // does NOT affect original copy.nested.b = 99; // DOES affect original!
console.log(original.nested.b); // 99
46 What is deep copy?
A deep copy creates completely independent copies of all nested values. Methods: 1)
JSON.parse(JSON.stringify(obj)) — simple but loses functions, dates, undefined, circular refs. 2)
structuredClone() — modern, handles most types. 3) lodash _.cloneDeep() — most complete.
Example / Code:
const original = { a: 1, nested: { b: 2 } }; const deep =
structuredClone(original); deep.nested.b = 99; console.log(original.nested.b); //
2 (unaffected)
47 JSON.parse/stringify limitations?
Limitations: 1) Functions are stripped. 2) undefined values are removed. 3) Date objects become
strings. 4) Circular references throw TypeError. 5) Symbols are ignored. 6) BigInt throws. 7)
Prototype chain is not preserved.
Example / Code:
const obj = { fn: () => 42, date: new Date(), undef: undefined }; const copy =
JSON.parse(JSON.stringify(obj)); console.log(copy); // { date: '2024-01-01T...' }
fn & undef removed
48 structuredClone?
structuredClone() is a modern browser/Node.js API (Node 17+) for deep cloning. It handles: circular
references, Date, RegExp, Map, Set, ArrayBuffer, Blob, ImageBitmap. It does NOT handle:
functions, DOM nodes, class instances with methods.
Example / Code:
const map = new Map([['key', { nested: 1 }]]); const clone =
structuredClone(map); clone.get('key').nested = 99;
console.log(map.get('key').nested); // 1 (unaffected)
49 What is immutability?
Immutability means a value cannot be changed after it is created. Primitives are inherently
immutable. Objects can be made immutable with Object.freeze() (shallow) or deep-freeze
techniques. Immutability prevents accidental mutations and makes code predictable.
Example / Code:
const obj = Object.freeze({ x: 1, y: 2 }); obj.x = 99; // silently fails (strict
mode throws) console.log(obj.x); // 1
50 Why is immutability important?
1) Predictability — no unexpected mutations. 2) Easier debugging — trace changes clearly. 3) Pure
functions — no side effects. 4) React performance — shallow comparison works for change
detection. 5) Concurrent programming safety. 6) Time-travel debugging (Redux).
Example / Code:
// React: shallow comparison const prev = { count: 1 }; const next = { ...prev,
count: 2 }; console.log(prev === next); // false -> React knows to re-render
■ ES6+ Features (Q51–Q80)
51 What is destructuring?
Destructuring allows unpacking values from arrays or properties from objects into distinct variables
using concise syntax. Supports default values, renaming, and nested destructuring.
Example / Code:
// Object destructuring const { name, age = 25 } = { name: 'Alice' };
console.log(name, age); // Alice 25 // Array destructuring const [first, , third]
= [1, 2, 3]; console.log(first, third); // 1 3
52 Array vs object destructuring?
Array destructuring uses position — order matters, uses []. Object destructuring uses property
names — order doesn't matter, uses {}. Both support defaults, rest patterns, and nested
destructuring.
Example / Code:
// Array - position based const [a, b] = [10, 20]; // Object - name based const {
x: myX, y } = { x: 1, y: 2 }; console.log(myX); // 1 (renamed)
53 Spread vs rest operator?
Both use ... syntax but in different contexts. Spread expands an iterable into individual elements (in
function calls or array/object literals). Rest collects remaining elements into an array/object (in
function parameters or destructuring).
Example / Code:
// Spread const arr = [1, 2, 3]; console.log(Math.max(...arr)); // 3 const merged
= { ...obj1, ...obj2 }; // Rest function sum(first, ...rest) { return first +
rest.reduce((a, b) => a + b, 0); } sum(1, 2, 3, 4); // 10
54 Template literals?
Template literals use backticks (`) and allow: 1) Multi-line strings. 2) Expression interpolation with
${expr}. 3) Tagged templates (for libraries like styled-components or SQL queries).
Example / Code:
const name = 'World'; const msg = `Hello, ${name}!\nToday is ${new
Date().toDateString()}`; // Tagged template function tag(strings, ...vals) {
return strings.raw[0] + vals[0].toUpperCase(); } console.log(tag`Hello ${name}`);
// Hello WORLD
55 Default parameters?
Default parameters allow function parameters to have fallback values if no argument (or undefined)
is provided. They are evaluated at call time, not at definition time — enabling dynamic defaults.
Example / Code:
function greet(name = 'Guest', greeting = 'Hello') { return `${greeting},
${name}!`; } console.log(greet()); // Hello, Guest! console.log(greet('Alice',
'Hi')); // Hi, Alice!
56 Optional chaining (?.) ?
Optional chaining ?. safely accesses nested object properties without throwing if an intermediate
value is null or undefined. Returns undefined instead of throwing TypeError. Works with methods
?.() and bracket notation ?.[].
Example / Code:
const user = { profile: { name: 'Alice' } }; console.log(user?.profile?.name); //
'Alice' console.log(user?.address?.city); // undefined (no error)
console.log(user?.getName?.()); // undefined (no error)
57 Nullish coalescing (??)?
The ?? operator returns the right-hand side only when the left-hand side is null or undefined. Unlike
||, it does NOT trigger on falsy values like 0, '', or false. Use ?? for default values that should allow 0
and empty strings.
Example / Code:
const count = 0; console.log(count || 10); // 10 (wrong!) console.log(count ??
10); // 0 (correct!) const name = null; console.log(name ?? 'Guest'); // 'Guest'
58 Modules in JS?
ES6 modules (ESM) provide a standard for splitting code into files. Each module has its own scope.
Use export to expose values and import to consume them. Modules are statically analyzed
(tree-shaking possible) and always in strict mode.
Example / Code:
// math.js export const add = (a, b) => a + b; export default function multiply(a,
b) { return a * b; } // app.js import multiply, { add } from './math.js';
console.log(add(2, 3)); // 5 console.log(multiply(2, 3)); // 6
59 import vs require?
import is ES6 (ESM) — static, analyzed at parse time, supports tree-shaking, always async in
browsers, cannot be inside conditionals. require is CommonJS (Node.js) — dynamic, synchronous,
evaluated at runtime, can be inside conditionals.
Example / Code:
// CommonJS const fs = require('fs'); if(condition) { const mod =
require('./mod'); } // ESM import fs from 'fs'; // at top level only
import('./mod').then(m => {}); // dynamic import
60 Dynamic import?
import() is a function-like syntax that loads a module asynchronously and returns a Promise. Used
for code splitting, conditional loading, and lazy loading. Supported in both browser and Node.js
environments.
Example / Code:
// Lazy load a heavy module async function loadChart() { const { Chart } = await
import('./chart.js'); return new Chart(); } // Conditional if (feature) { const
mod = await import('./feature.js'); }
61 Map vs Object?
Map: keys can be ANY type, insertion order is preserved, has size property, better for frequent
additions/deletions, iterable directly. Object: keys are strings/symbols only, has prototype properties
(can clash), better for JSON, structured data, and static configs.
Example / Code:
const map = new Map(); map.set(42, 'number key'); map.set({}, 'object key');
console.log(map.size); // 2 console.log(map.get(42)); // 'number key' for(const
[k, v] of map) console.log(k, v);
62 Set vs Array?
Set stores UNIQUE values only — duplicates are automatically removed. Has O(1) lookup with
.has(). Array allows duplicates, has O(n) indexOf. Use Set for unique collections and fast
membership checks; use Array for ordered lists with duplicates.
Example / Code:
const set = new Set([1, 2, 2, 3, 3]); console.log([...set]); // [1, 2, 3]
console.log(set.has(2)); // true // Fast deduplication const unique = [...new
Set(array)];
63 WeakMap?
WeakMap is like Map but keys must be objects and are held weakly — if no other reference to the
key object exists, it can be garbage collected. Has no size, no iteration. Used for private data,
caching, and avoiding memory leaks.
Example / Code:
let obj = {}; const cache = new WeakMap(); cache.set(obj, 'cached data');
console.log(cache.get(obj)); // 'cached data' obj = null; // obj can now be GC'd;
cache entry removed
64 WeakSet?
WeakSet stores weakly referenced objects only (no primitives). No duplicates, no iteration, no size.
Used to track object membership without preventing garbage collection — e.g., tracking 'seen'
objects during traversal.
Example / Code:
const seen = new WeakSet(); function process(obj) { if(seen.has(obj)) return
'already processed'; seen.add(obj); return 'processed'; }
65 Symbol?
Symbol is a primitive type that creates unique, immutable identifiers. Every Symbol() call creates a
completely unique value — even with the same description. Used for: unique property keys,
well-known symbols (Symbol.iterator), private-like properties.
Example / Code:
const id = Symbol('id'); const obj = { [id]: 123, name: 'Alice' };
console.log(obj[id]); // 123 console.log(Object.keys(obj)); // ['name'] (symbols
hidden) console.log(Symbol('x') === Symbol('x')); // false
66 Iterators?
An iterator is an object with a next() method that returns { value, done }. done is true when iteration
is complete. Any object implementing the iterator protocol can be iterated with for...of, spread,
destructuring.
Example / Code:
function range(start, end) { return { [Symbol.iterator]() { let cur = start;
return { next() { return cur <= end ? { value: cur++, done: false } : { value:
undefined, done: true }; }}; } }; } console.log([...range(1, 5)]); // [1,2,3,4,5]
67 Generators?
Generator functions (function*) return a Generator object. They can pause execution at yield and
resume later via next(). Each next() call runs to the next yield and returns { value, done }. Useful for
lazy sequences, async control flow, and infinite streams.
Example / Code:
function* fibonacci() { let [a, b] = [0, 1]; while(true) { yield a; [a, b] = [b, a
+ b]; } } const fib = fibonacci(); console.log(fib.next().value); // 0
console.log(fib.next().value); // 1 console.log(fib.next().value); // 1
68 for...in vs for...of?
for...in iterates over enumerable property KEYS of an object (including prototype chain). for...of
iterates over ITERABLE values (arrays, strings, Maps, Sets, generators). Use for...of for
arrays/iterables; for...in for object properties.
Example / Code:
const arr = [10, 20, 30]; for(const i in arr) console.log(i); // '0' '1' '2'
(keys) for(const v of arr) console.log(v); // 10 20 30 (values) const obj = { a:
1, b: 2 }; for(const k in obj) console.log(k); // a, b
69 Promise.all vs Promise.allSettled?
Promise.all(promises) — runs in parallel, resolves with all results if ALL succeed, rejects
immediately if ANY fails. Promise.allSettled(promises) — runs in parallel, ALWAYS resolves with
array of { status, value/reason } for every promise regardless of success/failure.
Example / Code:
const p1 = Promise.resolve(1); const p2 = Promise.reject('err'); Promise.all([p1,
p2]).catch(e => console.log(e)); // 'err' Promise.allSettled([p1,
p2]).then(results => results.forEach(r => console.log(r.status)) ); // fulfilled,
rejected
70 Promise.race?
Promise.race(promises) resolves or rejects as soon as the FIRST promise settles (either way).
Useful for timeouts: race a fetch against a timeout promise. The result/error is that of the fastest
promise.
Example / Code:
const slow = new Promise(r => setTimeout(() => r('slow'), 2000)); const fast =
new Promise(r => setTimeout(() => r('fast'), 500)); Promise.race([slow,
fast]).then(console.log); // 'fast'
71 Promise.any?
Promise.any(promises) resolves with the value of the FIRST fulfilled promise. Rejects only if ALL
promises reject, with an AggregateError. Opposite of Promise.all in behavior — optimistic.
Example / Code:
const p1 = Promise.reject('err1'); const p2 = Promise.reject('err2'); const p3 =
Promise.resolve('success'); Promise.any([p1, p2, p3]).then(v => console.log(v));
// 'success'
72 What is debounce?
Debouncing delays a function's execution until after a specified time has elapsed since the last
invocation. If called again before the delay ends, the timer resets. Used for search boxes, resize
handlers, window scroll — prevents excessive calls.
Example / Code:
function debounce(fn, delay) { let timer; return function(...args) {
clearTimeout(timer); timer = setTimeout(() => fn.apply(this, args), delay); }; }
const onSearch = debounce((q) => fetchResults(q), 300);
73 What is throttle?
Throttling limits a function to execute at most ONCE per specified time window. Unlike debounce, it
guarantees the function fires at regular intervals during continuous events. Used for scroll listeners,
game loops, real-time updates.
Example / Code:
function throttle(fn, limit) { let lastCall = 0; return function(...args) { const
now = Date.now(); if(now - lastCall >= limit) { lastCall = now; return
fn.apply(this, args); } }; } window.addEventListener('scroll', throttle(updateUI,
100));
74 How to implement debounce?
Store a timer reference. On each call, clear the previous timer and set a new one. The actual
function only runs if no new call comes in within the delay period. Optionally support 'immediate'
mode to call on the leading edge.
Example / Code:
function debounce(fn, delay, immediate = false) { let timer; return
function(...args) { const callNow = immediate && !timer; clearTimeout(timer);
timer = setTimeout(() => { timer = null; if(!immediate) fn.apply(this, args); },
delay); if(callNow) fn.apply(this, args); }; }
75 How to implement throttle?
Track the timestamp of the last call. On each invocation, check if enough time has passed. If yes,
execute and update the timestamp. If no, skip. Can also use a flag (leading/trailing edge control).
Example / Code:
function throttle(fn, limit) { let inThrottle = false; return function(...args) {
if(!inThrottle) { fn.apply(this, args); inThrottle = true; setTimeout(() =>
inThrottle = false, limit); } }; }
76 What is currying?
Currying transforms a function with multiple arguments into a sequence of functions, each taking a
single argument. f(a, b, c) becomes f(a)(b)(c). Enables partial application and function reuse.
Example / Code:
// Manual curry const multiply = a => b => a * b; const double = multiply(2);
const triple = multiply(3); console.log(double(5)); // 10 console.log(triple(5));
// 15 // Generic curry const curry = fn => { const arity = fn.length; return
function curried(...args) { return args.length >= arity ? fn(...args) : (...more)
=> curried(...args, ...more); }; };
77 What is function composition?
Function composition combines two or more functions so that the output of one becomes the input
of the next. compose(f, g)(x) = f(g(x)). pipe() is left-to-right composition. Used in functional
programming to build complex logic from simple functions.
Example / Code:
const compose = (...fns) => x => fns.reduceRight((acc, fn) => fn(acc), x); const
pipe = (...fns) => x => fns.reduce((acc, fn) => fn(acc), x); const double = x => x
* 2; const addOne = x => x + 1; const square = x => x * x; const transform =
pipe(double, addOne, square); console.log(transform(3)); // ((3*2)+1)^2 = 49
78 What is memoization?
Memoization caches the results of expensive function calls and returns the cached result when the
same inputs occur again. Trades memory for speed. Pure functions are ideal candidates. Used in
dynamic programming and expensive computations.
Example / Code:
function memoize(fn) { const cache = new Map(); return function(...args) { const
key = JSON.stringify(args); if(cache.has(key)) return cache.get(key); const
result = fn.apply(this, args); cache.set(key, result); return result; }; } const
memoFib = memoize(function fib(n) { return n <= 1 ? n : memoFib(n-1) +
memoFib(n-2); });
79 What is a pure function?
A pure function: 1) Always returns the same output for the same inputs (deterministic). 2) Has no
side effects (doesn't modify external state, no I/O, no mutations). Pure functions are predictable,
testable, and composable.
Example / Code:
// Pure const add = (a, b) => a + b; // same input -> same output, no side effects
// Impure let total = 0; const addToTotal = (n) => { total += n; return total; };
// modifies external state
80 What are side effects?
A side effect is any observable effect outside a function's scope: modifying global state, mutating
arguments, making API calls, reading/writing files, DOM manipulation, logging. Side effects are
necessary but should be managed carefully and isolated from pure logic.
Example / Code:
// Side effects: let count = 0; function increment() { count++; } // modifies
external var function fetchData() { return fetch('/api'); } // network call
function log(msg) { console.log(msg); } // I/O
■ Browser & Performance (Q81–Q110)
81 What is DOM?
The Document Object Model (DOM) is a tree-structured representation of an HTML/XML document.
The browser creates it from HTML. JavaScript can manipulate the DOM to dynamically change
content, structure, and styles.
Example / Code:
document.getElementById('btn').addEventListener('click', () => {
document.querySelector('h1').textContent = 'Clicked!'; });
82 DOM vs Virtual DOM?
Real DOM: slow to manipulate, re-renders the entire tree on changes, direct browser API. Virtual
DOM (React): an in-memory JS object tree. React diffs old vs new virtual DOM (reconciliation) and
applies only the minimal real DOM changes (patching). Much faster for frequent updates.
■ Virtual DOM is not inherently faster — it's the batching and minimal updates that make it efficient.
83 Event delegation?
Event delegation attaches a single listener on a parent element to handle events from ALL its
children via event bubbling. More performant than attaching listeners to each child, especially for
dynamic content.
Example / Code:
// Instead of 100 listeners on <li> elements:
document.getElementById('list').addEventListener('click', (e) => {
if(e.target.matches('li')) { console.log('Clicked:', e.target.textContent); } });
84 Bubbling vs capturing?
Bubbling (default): event propagates from target element UP to root. Capturing: event propagates
DOWN from root to target. Use addEventListener(event, fn, true) for capture phase.
stopPropagation() stops both.
Example / Code:
// Capture phase (top-down) parent.addEventListener('click', fn, true); //
capture // Bubble phase (bottom-up, default) child.addEventListener('click', fn,
false); // bubble
85 stopPropagation vs preventDefault?
stopPropagation() stops the event from propagating further (up for bubbling, down for capture) —
other listeners on parent elements won't fire. preventDefault() prevents the browser's default action
(e.g., form submission, link navigation) — event still propagates.
Example / Code:
link.addEventListener('click', (e) => { e.preventDefault(); // don't navigate
e.stopPropagation(); // don't bubble up handleClick(); });
86 What is reflow?
Reflow (layout) happens when the browser recalculates the position and size of all elements.
Triggered by: adding/removing elements, changing CSS geometry (width, height, margin), font
changes, viewport resize. Expensive — avoid in loops.
Example / Code:
// Bad: reads layout, then writes -> causes reflow per loop for(let i = 0; i <
100; i++) { el.style.width = el.offsetWidth + 1 + 'px'; // read+write } // Good:
read all first, then write all const w = el.offsetWidth; for(let i = 0; i < 100;
i++) { el.style.width = (w+i) + 'px'; }
87 What is repaint?
Repaint happens when visual styles (color, background, visibility) change without affecting layout.
Less expensive than reflow but still costly if frequent. Caused by: color changes, box-shadow,
outline, opacity. Reflow always causes repaint.
■ Use opacity and transform for animations — they are GPU-composited and avoid reflow/repaint.
88 What is lazy loading?
Lazy loading defers loading of non-critical resources (images, videos, scripts, route components)
until they are needed (near viewport). Reduces initial page load time and bandwidth. Native:
loading='lazy' attribute on <img>/<iframe>. In JS: IntersectionObserver.
Example / Code:
<img src='photo.jpg' loading='lazy' alt='...'> // Or with IntersectionObserver:
const observer = new IntersectionObserver((entries) => { entries.forEach(e => {
if(e.isIntersecting) { e.target.src = e.target.dataset.src;
observer.unobserve(e.target); } }); });
89 What is code splitting?
Code splitting divides your JS bundle into smaller chunks that are loaded on demand. Reduces
initial load time. In webpack/Vite: dynamic import(). In React: React.lazy() + Suspense.
Route-based splitting is the most common strategy.
Example / Code:
// React route-based splitting import { lazy, Suspense } from 'react'; const
Dashboard = lazy(() => import('./Dashboard')); <Suspense fallback={<Spinner />}>
<Dashboard /> </Suspense>
90 What is tree shaking?
Tree shaking is the elimination of dead code (exports that are never imported) from the final bundle.
Enabled by ES6 static imports (which are statically analyzable). Tools: webpack, Rollup, Vite.
CommonJS (require) is not tree-shakeable.
Example / Code:
// math.js exports add, subtract, multiply import { add } from './math'; // only
'add' included in bundle // subtract, multiply are tree-shaken away
91 What is CORS?
Cross-Origin Resource Sharing (CORS) is a browser security mechanism that controls which
cross-origin requests are allowed. Server must set Access-Control-Allow-Origin headers. Preflight
OPTIONS requests are sent for non-simple requests.
Example / Code:
// Server (Express) CORS setup: app.use((req, res, next) => {
res.header('Access-Control-Allow-Origin', 'https://myapp.com');
res.header('Access-Control-Allow-Methods', 'GET,POST,PUT,DELETE');
res.header('Access-Control-Allow-Headers', 'Content-Type,Authorization'); next();
});
92 What is same-origin policy?
The same-origin policy is a browser security restriction that prevents scripts on one origin (protocol
+ domain + port) from reading responses from a different origin. Protects against CSRF and data
theft. CORS is the mechanism to selectively relax this.
■ https://a.com and http://a.com are different origins (different protocol).
93 LocalStorage vs SessionStorage?
Both are Web Storage APIs. localStorage: persists across sessions (until manually cleared),
~5-10MB, shared across all tabs of same origin. sessionStorage: cleared when tab/window is
closed, per-tab, ~5MB. Neither sends to server automatically (unlike cookies).
Example / Code:
localStorage.setItem('theme', 'dark');
console.log(localStorage.getItem('theme')); // 'dark'
sessionStorage.setItem('step', '2'); // cleared when tab closes
94 Cookies vs Storage?
Cookies: sent with every HTTP request (including to server), ~4KB limit, can be
HTTP-only/Secure/SameSite, can have expiry, suitable for session IDs.
localStorage/sessionStorage: browser-only (not sent to server), larger capacity, simpler API, no
expiry for localStorage.
■ Store JWTs in HTTP-only cookies for security — prevents XSS access.
95 What is service worker?
A Service Worker is a script running in the background (separate thread), intercepting network
requests, enabling offline caching (via Cache API), push notifications, and background sync.
Fundamental to Progressive Web Apps (PWAs).
Example / Code:
// sw.js self.addEventListener('fetch', event => { event.respondWith(
caches.match(event.request).then(cached => cached || fetch(event.request) ) );
});
96 What is web worker?
Web Workers run JavaScript in a background thread separate from the main thread, enabling
CPU-intensive operations without blocking the UI. Workers communicate with the main thread via
postMessage(). Cannot access the DOM directly.
Example / Code:
// main.js const worker = new Worker('worker.js'); worker.postMessage({ data:
largeArray }); worker.onmessage = (e) => console.log('Result:', e.data); //
worker.js self.onmessage = (e) => { const result = heavyComputation(e.data);
self.postMessage(result); };
97 Single-threaded meaning?
JavaScript has a single call stack — it can execute only one piece of code at a time. Long
operations block everything else. The browser/Node.js provides async APIs (Web APIs, libuv) that
run operations in background threads and return results via callbacks to the event loop.
■ This is why you should never run CPU-heavy tasks synchronously in the main thread.
98 How JS handles concurrency?
JS uses the event loop + async APIs: 1) Synchronous code runs on call stack. 2) Async operations
(I/O, timers) are delegated to browser/OS APIs. 3) On completion, callbacks are placed in the
queue. 4) Event loop pushes them to call stack when it's empty.
99 Memory leaks?
Memory leaks occur when memory that is no longer needed is not released. Common causes: 1)
Unremoved event listeners. 2) Forgotten timers/intervals. 3) Detached DOM nodes still referenced.
4) Closures holding large data. 5) Global variables accumulating.
Example / Code:
// Leak: interval keeps reference to element const el =
document.getElementById('btn'); const timer = setInterval(() => el.style.color =
'red', 100); // Fix: clear when done clearInterval(timer);
100 How to avoid memory leaks?
1) Remove event listeners with removeEventListener when done. 2) Clear timers with
clearInterval/clearTimeout. 3) Nullify references to large objects when no longer needed. 4) Use
WeakMap/WeakSet for caches keyed by objects. 5) Avoid global variables. 6) Use Chrome
DevTools Memory panel.
101 Garbage collection?
JS uses automatic garbage collection — memory is automatically freed when objects are no longer
reachable (no references). The GC periodically scans memory and reclaims unreachable objects.
V8 uses generational garbage collection (young/old generation).
102 Mark and sweep?
Mark-and-sweep is the main GC algorithm. Phase 1 (Mark): starting from roots (global, stack),
traverse all reachable objects and mark them. Phase 2 (Sweep): scan all memory and free
unmarked objects. V8 also uses incremental and concurrent marking to reduce pauses.
103 What is event listener leak?
Attaching event listeners without removing them creates memory leaks because the listener holds a
closure reference. Even if the DOM element is removed from the tree, it stays in memory if the
listener reference exists.
Example / Code:
// Leak const btn = document.getElementById('btn'); btn.addEventListener('click',
heavyHandler); // Later remove the element without removing listener // Fix
btn.removeEventListener('click', heavyHandler); btn.remove();
104 Passive event listeners?
Passive listeners (addEventListener('touchstart', fn, { passive: true })) promise the browser that
preventDefault() won't be called. The browser can optimize scroll performance by not waiting for the
listener to complete before scrolling.
Example / Code:
// Passive - browser doesn't wait for JS before scrolling
window.addEventListener('wheel', onScroll, { passive: true });
105 requestAnimationFrame?
requestAnimationFrame(callback) schedules a function to run before the next browser repaint
(~60fps). Ideal for animations — synchronized with display refresh rate, paused when tab is hidden,
more efficient than setTimeout for animations.
Example / Code:
function animate() { element.style.left = (parseFloat(element.style.left) + 1) +
'px'; if(parseFloat(element.style.left) < 500) { requestAnimationFrame(animate);
} } requestAnimationFrame(animate);
106 requestIdleCallback?
requestIdleCallback(callback) schedules work during browser idle time (when no user interactions
or animations). Ideal for non-critical background work: analytics, pre-fetching, cleanup. Receives an
IdleDeadline object with timeRemaining().
Example / Code:
requestIdleCallback((deadline) => { while(deadline.timeRemaining() > 0 &&
tasksQueue.length) { processTask(tasksQueue.shift()); } }, { timeout: 2000 });
107 IntersectionObserver?
IntersectionObserver watches when elements enter/leave the viewport (or a parent container).
Replaces scroll event listeners for visibility detection. Used for lazy loading, infinite scroll, analytics,
animations on scroll.
Example / Code:
const observer = new IntersectionObserver((entries) => { entries.forEach(entry =>
{ if(entry.isIntersecting) { entry.target.classList.add('visible');
observer.unobserve(entry.target); // stop watching } }); }, { threshold: 0.1 });
observer.observe(document.querySelector('.card'));
108 MutationObserver?
MutationObserver watches for changes to the DOM (attributes, child nodes, text content). Replaces
deprecated Mutation Events. Used for: monitoring dynamic content, implementing undo, detecting
ad injection, lazy hydration.
Example / Code:
const observer = new MutationObserver((mutations) => { mutations.forEach(m => {
console.log('Changed:', m.type, m.target); }); });
observer.observe(document.body, { childList: true, subtree: true, attributes:
true });
109 Performance optimization techniques?
1) Code splitting and lazy loading. 2) Debounce/throttle event handlers. 3) Virtual DOM / minimize
reflows. 4) Cache API calls (memoize). 5) Web Workers for heavy computation. 6) Compression
(gzip/brotli). 7) CDN. 8) Image optimization (WebP, lazy). 9) Tree shaking. 10) Service Worker
caching.
110 Critical rendering path?
The CRP is the sequence of steps to render initial content: 1) Parse HTML -> DOM. 2) Parse CSS
-> CSSOM. 3) Combine -> Render Tree. 4) Layout (reflow). 5) Paint. 6) Composite. Optimize:
minimize render-blocking resources, inline critical CSS, defer JS.
■ Use <link rel='preload'> for critical assets and <script defer> for JS.
■ Tricky & Interview Questions (Q111–Q140)
111 Closure loop output (var vs let)?
Classic interview trap: using var in a for loop creates one shared variable. All callbacks close over
the same variable (its final value). Using let creates a new binding per iteration — each callback
captures its own i.
Example / Code:
// var - all print 3 for(var i = 0; i < 3; i++) { setTimeout(() => console.log(i),
0); // 3, 3, 3 } // let - prints 0, 1, 2 for(let i = 0; i < 3; i++) {
setTimeout(() => console.log(i), 0); // 0, 1, 2 }
112 Output of setTimeout in loop?
When setTimeout(fn, 0) is in a loop, ALL synchronous code finishes first (the loop completes),
THEN all setTimeout callbacks run. With var, i has reached its final value by then.
Example / Code:
for(var i = 0; i < 3; i++) { setTimeout(() => console.log(i), 0); } // Synchronous
loop finishes (i=3), then callbacks run // Output: 3, 3, 3
■ Fix: use let, or wrap in IIFE: (function(j){ setTimeout(()=>console.log(j),0); })(i);
113 var vs let in loop?
var: function-scoped, one binding for the entire loop. let: block-scoped, new binding per iteration.
This is why let is always preferred in loops — each iteration gets its own closed-over variable.
114 Explain event loop with example?
The event loop: 1) Execute all synchronous code (call stack). 2) Process all microtasks (Promises).
3) Take one macrotask (setTimeout) and process it. 4) Repeat. Stack -> Microtask queue ->
Macrotask queue.
Example / Code:
console.log('sync 1'); setTimeout(() => console.log('macro'), 0);
Promise.resolve().then(() => console.log('micro')); console.log('sync 2'); //
Output: sync 1, sync 2, micro, macro
115 Promise chaining output?
Each .then() returns a new Promise. Return values are wrapped in resolved Promises. Throwing or
returning rejected Promise propagates to .catch(). Understanding the return value is key.
Example / Code:
Promise.resolve(1) .then(v => v + 1) // 2 .then(v => { throw new Error('oops'); })
.catch(e => e.message) // 'oops' .then(v => console.log(v)); // 'oops'
116 async/await vs .then()?
Both handle Promises. async/await is synchronous-looking, easier to read/debug, stack traces are
cleaner, try/catch for errors. .then() is functional-style, better for parallel chains, no special function
needed. They are interchangeable in most cases.
Example / Code:
// .then() fetch(url).then(r => r.json()).then(data =>
use(data)).catch(handleError); // async/await async function load() { try { const
r = await fetch(url); const data = await r.json(); use(data); } catch(e) {
handleError(e); } }
117 Why can't arrow functions be constructors?
Arrow functions do not have their own 'this' binding or 'prototype' property. Constructors need both:
'this' to attach properties and 'prototype' for the prototype chain. Calling new on an arrow function
throws TypeError.
Example / Code:
const Foo = () => {}; new Foo(); // TypeError: Foo is not a constructor
console.log(Foo.prototype); // undefined
118 What happens when you return an object in a constructor?
If a constructor function explicitly returns an OBJECT, that object is returned instead of 'this'. If it
returns a primitive (or nothing), 'this' is returned as normal.
Example / Code:
function Person(name) { this.name = name; return { custom: true }; // returns
this object } const p = new Person('Alice'); console.log(p.name); // undefined
console.log(p.custom); // true
119 How JS handles memory?
JS uses a heap for dynamic allocations and a stack for execution contexts. The garbage collector
automatically reclaims unreachable heap memory (mark-and-sweep). V8 uses generational GC:
short-lived objects in 'young generation', long-lived in 'old generation'.
120 How to polyfill bind?
Polyfilling Function.prototype.bind from scratch: create a new function that uses apply to call the
original with the given this and merged arguments.
Example / Code:
Function.prototype.myBind = function(ctx, ...partial) { const fn = this; return
function(...args) { return fn.apply(ctx, [...partial, ...args]); }; }; function
greet(g, p) { return `${g}, ${this.name}${p}`; } const hi =
greet.myBind({name:'Bob'}, 'Hi'); console.log(hi('!')); // Hi, Bob!
121 How to polyfill Array.map?
Implement map by iterating over the array, calling the callback for each element (with value, index,
array), and collecting results in a new array.
Example / Code:
Array.prototype.myMap = function(callback) { const result = []; for(let i = 0; i
< this.length; i++) { if(Object.prototype.hasOwnProperty.call(this, i)) {
result.push(callback(this[i], i, this)); } } return result; };
console.log([1,2,3].myMap(x => x*2)); // [2,4,6]
122 What is call stack overflow?
A stack overflow happens when the call stack exceeds its maximum size — usually caused by
infinite recursion or excessively deep function calls. Throws 'Maximum call stack size exceeded'
(RangeError).
Example / Code:
function infinite() { return infinite(); } try { infinite(); } catch(e) {
console.log(e.message); } // Maximum call stack size exceeded
123 What is tail call optimization?
TCO is an optimization where the engine reuses the current stack frame for a tail call (a function
call as the last action), preventing stack growth. ES6 specifies TCO but only Safari fully implements
it. Enables stack-safe recursion.
Example / Code:
// Tail recursive (can be optimized) function factorial(n, acc = 1) { if(n <= 1)
return acc; return factorial(n - 1, n * acc); // tail call }
124 Why is JS single-threaded?
JS was designed for the browser where DOM manipulation must be safe — two threads modifying
the DOM simultaneously would cause race conditions and corruption. Single-threaded keeps it
simple and safe. Async is handled via event loop, not multiple threads.
125 Can JS be multi-threaded?
Sort of. Web Workers run JS in background threads but cannot access the DOM.
SharedArrayBuffer + Atomics enable shared memory between workers. Node.js worker_threads
allow multi-threading. The MAIN thread is always single-threaded for DOM operations.
126 What is Proxy?
Proxy wraps an object and intercepts fundamental operations (get, set, delete, apply, etc.) via
'traps'. Used for validation, logging, reactivity systems (Vue 3), mocking.
Example / Code:
const handler = { get(target, prop) { return prop in target ? target[prop] :
`Property ${prop} not found`; }, set(target, prop, value) { if(typeof value !==
'number') throw TypeError('Numbers only'); target[prop] = value; return true; }
}; const obj = new Proxy({}, handler); obj.x = 42; // OK console.log(obj.x); // 42
console.log(obj.y); // Property y not found
127 What is Reflect?
Reflect is a built-in object with static methods mirroring Proxy traps. It provides default behavior for
intercepted operations and is the companion to Proxy. Useful in Proxy traps to call default behavior
after custom logic.
Example / Code:
const p = new Proxy({}, { set(target, prop, value, receiver) {
console.log(`Setting ${prop} = ${value}`); return Reflect.set(target, prop,
value, receiver); } }); p.x = 10; // logs: Setting x = 10
128 What is Intl API?
The Internationalization API (Intl) provides locale-sensitive string comparison, number formatting,
date/time formatting, and more. Handles localization without external libraries.
Example / Code:
// Number formatting new Intl.NumberFormat('en-IN', { style: 'currency',
currency: 'INR' }).format(1234567); // '■12,34,567.00' // Date new
Intl.DateTimeFormat('hi-IN').format(new Date()); // Hindi date format
129 What is BigInt?
BigInt is a primitive type for integers of arbitrary precision, beyond Number.MAX_SAFE_INTEGER
(2^53-1). Created by appending n to a literal or using BigInt(). Cannot be mixed with regular
numbers in arithmetic.
Example / Code:
const big = 9007199254740991n + 1n; console.log(big); // 9007199254740992n
console.log(typeof big); // 'bigint' // Cannot mix: 1n + 1 -> TypeError
130 What is Temporal API?
Temporal is a modern TC39 proposal (stage 3) to replace the broken Date API. Provides immutable
date/time objects, proper timezone handling, calendar support, and arithmetic. Not yet in all
browsers but polyfills exist.
Example / Code:
// Future syntax (Temporal API): const now = Temporal.Now.plainDateTimeISO();
const tomorrow = now.add({ days: 1 }); console.log(tomorrow.toString());
131 What is shadow DOM?
Shadow DOM is a web standard allowing encapsulated DOM trees attached to elements. Styles
and scripts inside shadow DOM don't leak out, and external styles don't leak in. Used by web
components for style encapsulation.
Example / Code:
const host = document.getElementById('widget'); const shadow =
host.attachShadow({ mode: 'open' }); shadow.innerHTML = ` <style>p { color: red;
}</style> <p>Encapsulated content</p>`;
132 What is hydration?
Hydration is the process of attaching JavaScript event listeners to server-rendered HTML, making it
interactive. In SSR, the server sends HTML (fast first paint), then the browser downloads JS and
'hydrates' the static HTML into a live React/Vue app.
■ Hydration mismatch errors occur when server HTML differs from what client-side rendering would produce.
133 CSR vs SSR?
CSR (Client-Side Rendering): browser downloads blank HTML + JS, JS renders content. Fast after
initial load, poor SEO, slow first paint. SSR (Server-Side Rendering): server renders full HTML, sent
to browser. Good SEO, fast first paint, but TTFB may be higher. SSG pre-renders at build time.
134 What is a module bundler?
A module bundler (webpack, Vite, Rollup, Parcel) takes many JS modules and their dependencies
and bundles them into one or few files for the browser. Also handles: transpilation, tree-shaking,
code splitting, asset optimization.
135 What is V8 engine?
V8 is Google's open-source JS engine (used in Chrome and Node.js). It compiles JS to native
machine code using JIT (Just-In-Time) compilation. Features: hidden classes, inline caching,
generational GC, Ignition interpreter + TurboFan compiler.
136 How JS code is executed internally?
1) Parsing: source code -> AST (Abstract Syntax Tree). 2) Compilation: AST -> bytecode (Ignition).
3) JIT optimization: hot functions -> optimized machine code (TurboFan). 4) Execution on call stack.
5) Deoptimization if assumptions break.
137 Parsing vs compiling?
Parsing converts source code (text) into an AST — a tree structure representing the code's syntax.
Compiling converts the AST to bytecode or machine code. V8 parses eagerly for top-level code but
lazily parses function bodies (only compiles when called).
138 JIT compilation?
Just-In-Time compilation compiles code at runtime rather than ahead of time. JS starts interpreted
(fast startup), identifies hot code paths, and compiles them to optimized machine code (fast
execution). Best of both worlds vs pure interpretation or AOT.
139 Hidden classes?
V8 creates hidden classes (shapes) for objects with the same property structure. Objects sharing a
hidden class share optimized property access paths. Adding properties in a different order creates
different hidden classes, deoptimizing access. Always initialize properties in the same order.
Example / Code:
// Good: same hidden class const a = { x: 1, y: 2 }; const b = { x: 3, y: 4 }; //
Bad: different hidden classes const c = { x: 1 }; c.y = 2; const d = { y: 1 }; d.x
= 2;
140 Inline caching?
Inline caching (IC) is a V8 optimization that caches the result of property lookups. After a property
access is resolved once, V8 caches the offset. Subsequent accesses of the same property skip the
lookup. Monomorphic ICs (one type) are fastest; megamorphic (many types) are slowest.


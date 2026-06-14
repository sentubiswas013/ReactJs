**1. What is JavaScript?**

**JavaScript** is a **high-level, interpreted, and dynamically typed programming language** used to make web pages **interactive and dynamic**.

**Key Features**

* **Client-side scripting** language
* Supports **Object-Oriented Programming (OOP)**
* **Event-driven** and **asynchronous**
* Runs directly in the browser

**How It Works**

* Browser reads and executes JavaScript code.
* It can manipulate **HTML**, **CSS**, handle events, and communicate with servers.

**Example**

```javascript
console.log("Hello World");
```

**Output**

```text
Hello World
```

**2. Difference between var, let, const**

| Feature    | **var**        | **let**     | **const**   |
| ---------- | -------------- | ----------- | ----------- |
| Scope      | Function Scope | Block Scope | Block Scope |
| Re-declare | Yes            | No          | No          |
| Re-assign  | Yes            | Yes         | No          |
| Hoisted    | Yes            | Yes (TDZ)   | Yes (TDZ)   |

**Key Points**

* **var**: Old way of declaring variables.
* **let**: Preferred when value can change.
* **const**: Preferred when value should not change.

**Example**

```javascript
var a = 10;
let b = 20;
const c = 30;

a = 15; // Allowed
b = 25; // Allowed
// c = 35; // Error
```

**3. What is Hoisting?**

**Hoisting** is JavaScript's behavior of moving **declarations** to the top of their scope before code execution.

**Key Features**

* Variable and function declarations are hoisted.
* Only declarations are hoisted, not initializations.
* **var** gets initialized with **undefined**.
* **let** and **const** stay in **TDZ** until declared.

**How It Works**

* JavaScript first scans the code and allocates memory for declarations.
* Then it executes the code line by line.

**Example**

```javascript
console.log(x);
var x = 10;
```

**Output**

```text
undefined
```

Internally:

```javascript
var x;
console.log(x);
x = 10;
```

**4. What is Temporal Dead Zone (TDZ)?**

**Temporal Dead Zone (TDZ)** is the period between entering a scope and declaring a **let** or **const** variable.

**Key Features**

* Applies only to **let** and **const**.
* Accessing the variable before declaration causes an error.
* Helps prevent accidental usage of variables before initialization.

**How It Works**

* Memory is allocated for the variable.
* Variable remains inaccessible until its declaration line is executed.

**Example**

```javascript
console.log(name);
let name = "John";
```

**Output**

```text
ReferenceError: Cannot access 'name' before initialization
```

**5. What are Data Types in JavaScript?**

**Data Types** define the type of value a variable can store.

**Primitive Data Types**

1. **String**
2. **Number**
3. **Boolean**
4. **Undefined**
5. **Null**
6. **BigInt**
7. **Symbol**

**Non-Primitive Data Type**

1. **Object** (Arrays, Functions, Objects)

**Example**

```javascript
let name = "John";        // String
let age = 25;            // Number
let isActive = true;     // Boolean
let value;               // Undefined
let data = null;         // Null
let big = 123n;          // BigInt
let id = Symbol();       // Symbol
let user = {name: "John"}; // Object
```

**6. Difference between null and undefined?**

**null** and **undefined** both represent absence of a value, but they are used differently.

| Feature     | **null**                     | **undefined**                      |
| ----------- | ---------------------------- | ---------------------------------- |
| Meaning     | Intentional absence of value | Variable declared but not assigned |
| Assigned By | Developer                    | JavaScript Engine                  |
| Type        | Object                       | Undefined                          |

**Key Features**

* **null** means a value is intentionally empty.
* **undefined** means a value has not been assigned yet.

**How It Works**

* Use **null** when you want to explicitly indicate "no value".
* JavaScript automatically assigns **undefined** to uninitialized variables.

**Example**

```javascript
let a = null;
let b;

console.log(a); // null
console.log(b); // undefined
```

**7. What is NaN?**

**NaN (Not a Number)** is a special value that represents an invalid numeric result.

**Key Features**

* Stands for **Not a Number**.
* Belongs to the **Number** type.
* Produced when a mathematical operation fails.

**How It Works**

* JavaScript cannot convert the value into a valid number.
* Instead of throwing an error, it returns **NaN**.

**Example**

```javascript
console.log("abc" / 2);
```

**Output**

```text
NaN
```

**8. typeof NaN returns what?**

**typeof NaN** returns **"number"**.

**Key Features**

* **NaN** is technically a special numeric value.
* It belongs to the JavaScript **Number** type.

**Example**

```javascript
console.log(typeof NaN);
```

**Output**

```text
number
```

**Interview One-Liner**

**NaN** means an invalid numeric result, but JavaScript still treats it as a **Number**, so `typeof NaN` returns **"number"**.

**9. What is Type Coercion?**

**Type Coercion** is the automatic conversion of one data type into another by JavaScript.

**Key Features**

* Happens automatically during operations.
* Common with comparison and arithmetic operators.
* Can lead to unexpected results if not understood.

**How It Works**

* JavaScript converts values to compatible types before performing the operation.

**Example**

```javascript
console.log("5" + 2);
console.log("5" - 2);
```

**Output**

```text
52
3
```

Explanation:

* `"5" + 2` → String concatenation → `"52"`
* `"5" - 2` → Number conversion → `3`

**10. == vs ===?**

Both are comparison operators, but they work differently.

| Operator | Description                                |
| -------- | ------------------------------------------ |
| **==**   | Compares values after type conversion      |
| **===**  | Compares value and type without conversion |

**Key Features**

* **==** performs **type coercion**.
* **===** performs **strict comparison**.
* **===** is recommended in most cases.

**How It Works**

* **==** converts operands to the same type before comparing.
* **===** checks both value and data type directly.

**Example**

```javascript
console.log(5 == "5");
console.log(5 === "5");
```

**Output**

```text
true
false
```

**11. What is Truthy and Falsy?**

In JavaScript, every value is treated as either **true** or **false** when used in a boolean context.

**Falsy Values**
There are only **7 falsy values**:

* **false**
* **0**
* **-0**
* **0n**
* **""** (empty string)
* **null**
* **undefined**
* **NaN**

Everything else is **truthy**.

**Key Features**

* Used in conditions like **if**, **while**, and logical operators.
* JavaScript automatically converts values to boolean.

**How It Works**

* If the value is truthy, the condition executes.
* If the value is falsy, the condition is skipped.

**Example**

```javascript
if ("Hello") {
    console.log("Truthy");
}

if (0) {
    console.log("Falsy");
}
```

**Output**

```text
Truthy
```

**12. What are Primitive Types?**

**Primitive Types** are basic data types that store a **single value** and are **immutable**.

**Types**

1. **String**
2. **Number**
3. **Boolean**
4. **Undefined**
5. **Null**
6. **BigInt**
7. **Symbol**

**Key Features**

* Stored by **value**.
* Immutable (cannot be changed directly).
* Faster and lightweight.

**How It Works**

* A copy of the actual value is assigned to another variable.

**Example**

```javascript
let a = 10;
let b = a;

b = 20;

console.log(a);
console.log(b);
```

**Output**

```text
10
20
```

**13. What are Reference Types?**

**Reference Types** store references (memory addresses) instead of actual values.

**Types**

* **Object**
* **Array**
* **Function**

**Key Features**

* Stored by **reference**.
* Multiple variables can point to the same object.
* Mutable (can be modified).

**How It Works**

* Variables store the memory location of the object.

**Example**

```javascript
let obj1 = { name: "John" };
let obj2 = obj1;

obj2.name = "Mike";

console.log(obj1.name);
```

**Output**

```text
Mike
```

**14. What is a Function?**

A **Function** is a reusable block of code designed to perform a specific task.

**Key Features**

* Promotes code reusability.
* Can accept parameters.
* Can return a value.
* Executes only when called.

**How It Works**

* Define the function.
* Call the function whenever needed.

**Example**

```javascript
function greet(name) {
    return "Hello " + name;
}

console.log(greet("John"));
```

**Output**

```text
Hello John
```

**15. Function Declaration vs Function Expression**

A **Function Declaration** defines a function using the **function** keyword with a name.

A **Function Expression** stores a function inside a variable.

| Feature                 | **Function Declaration** | **Function Expression** |
| ----------------------- | ------------------------ | ----------------------- |
| Name Required           | Yes                      | Optional                |
| Hoisted                 | Yes                      | No                      |
| Usage Before Definition | Allowed                  | Not Allowed             |

**Function Declaration Example**

```javascript
sayHello();

function sayHello() {
    console.log("Hello");
}
```

**Output**

```text
Hello
```

**Function Expression Example**

```javascript
const sayHello = function() {
    console.log("Hello");
};

sayHello();
```

**Output**

```text
Hello
```

**16. What is an Arrow Function?**

An **Arrow Function** is a shorter syntax for writing functions introduced in **ES6**.

**Key Features**

* Short and concise syntax.
* Implicit return for single expressions.
* Does not have its own **this**.
* Commonly used in callbacks and array methods.

**How It Works**

* Uses the **=>** syntax.
* Can take parameters and return values like a normal function.

**Example**

```javascript
const add = (a, b) => {
    return a + b;
};

console.log(add(2, 3));
```

**Output**

```text
5
```

**Short Form**

```javascript
const add = (a, b) => a + b;
```

**17. Difference between Arrow and Normal Function?**

| Feature            | **Normal Function** | **Arrow Function**         |
| ------------------ | ------------------- | -------------------------- |
| Syntax             | Longer              | Shorter                    |
| Own `this`         | Yes                 | No                         |
| Constructor        | Can be used         | Cannot be used             |
| `arguments` Object | Available           | Not Available              |
| Best Use           | Object methods      | Callbacks, short functions |

**Key Features**

* Normal functions create their own **this** context.
* Arrow functions inherit **this** from the surrounding scope.

**Example**

```javascript
const person = {
    name: "John",
    greet: function() {
        console.log(this.name);
    }
};

person.greet();
```

**Output**

```text
John
```

**Interview One-Liner**

**Arrow functions** are shorter and inherit **this** from the surrounding scope, while **normal functions** create their own **this**.

**18. What is IIFE?**

**IIFE (Immediately Invoked Function Expression)** is a function that executes immediately after it is created.

**Key Features**

* Runs only once.
* Creates a private scope.
* Prevents global variable pollution.

**How It Works**

* Function is wrapped in parentheses.
* Another pair of parentheses invokes it immediately.

**Example**

```javascript
(function() {
    console.log("IIFE Executed");
})();
```

**Output**

```text
IIFE Executed
```

**19. What is Scope?**

**Scope** determines where variables and functions can be accessed in a program.

**Key Features**

* Controls variable visibility.
* Helps avoid naming conflicts.
* Improves code organization.

**How It Works**

* Variables are accessible only within the scope where they are declared.

**Example**

```javascript
function test() {
    let name = "John";
    console.log(name);
}

test();
```

**Output**

```text
John
```

Outside the function:

```javascript
console.log(name);
```

**Output**

```text
ReferenceError
```

**20. Types of Scope?**

JavaScript mainly has **3 types of scope**.

**1. Global Scope**

* Variables declared outside any function or block.
* Accessible everywhere.

**Example**

```javascript
let company = "OpenAI";

function show() {
    console.log(company);
}
```

**2. Function Scope**

* Variables declared inside a function.
* Accessible only within that function.

**Example**

```javascript
function test() {
    let age = 25;
    console.log(age);
}
```

**3. Block Scope**

* Variables declared using **let** and **const** inside `{}`.
* Accessible only within that block.

**Example**

```javascript
{
    let city = "Delhi";
    console.log(city);
}
```

**21. What is Closure?**

A **Closure** is a function that remembers and can access variables from its **outer function scope** even after the outer function has finished executing.

**Key Features**

* Remembers outer scope variables.
* Preserves data between function calls.
* Helps create private variables.
* Commonly used in JavaScript applications.

**How It Works**

* An inner function keeps a reference to variables of its outer function.
* Those variables remain available even after the outer function returns.

**Example**

```javascript
function outer() {
    let count = 0;

    return function inner() {
        count++;
        return count;
    };
}

const counter = outer();

console.log(counter());
console.log(counter());
```

**Output**

```text
1
2
```

**22. Practical Use of Closures?**

Closures are mainly used for **data privacy**, **state management**, and **callbacks**.

**Key Features**

* Create private variables.
* Maintain state between function calls.
* Used in event handlers and asynchronous code.
* Improve encapsulation.

**How It Works**

* Private data is accessible only through the returned function.

**Example**

```javascript
function createBankAccount() {
    let balance = 1000;

    return {
        getBalance: function() {
            return balance;
        }
    };
}

const account = createBankAccount();

console.log(account.getBalance());
```

**Output**

```text
1000
```

Here, **balance** cannot be accessed directly from outside.

**23. What is Lexical Scope?**

**Lexical Scope** means a function can access variables based on where it is **defined**, not where it is called.

**Key Features**

* Scope is determined during code writing.
* Inner functions can access outer variables.
* Forms the foundation of closures.

**How It Works**

* JavaScript looks for variables in the current scope first.
* If not found, it searches outer scopes.

**Example**

```javascript
function outer() {
    let name = "John";

    function inner() {
        console.log(name);
    }

    inner();
}

outer();
```

**Output**

```text
John
```

**24. What is Execution Context?**

An **Execution Context** is the environment in which JavaScript code is executed.

**Key Features**

* Created whenever code runs.
* Stores variables, functions, and the value of **this**.
* Determines how code is executed.

**Types**

1. **Global Execution Context**
2. **Function Execution Context**
3. **Eval Execution Context**

**How It Works**

* JavaScript creates an execution context.
* Allocates memory for variables and functions.
* Executes code line by line.

**Example**

```javascript
let name = "John";

function greet() {
    console.log("Hello");
}

greet();
```

The global code runs in the **Global Execution Context**, and `greet()` creates a new **Function Execution Context**.

**25. What is Call Stack?**

The **Call Stack** is a data structure that keeps track of function execution in JavaScript.

**Key Features**

* Follows **LIFO (Last In, First Out)**.
* Stores execution contexts.
* Manages function calls and returns.

**How It Works**

* When a function is called, it is pushed onto the stack.
* When it finishes, it is removed from the stack.

**Example**

```javascript
function one() {
    two();
}

function two() {
    three();
}

function three() {
    console.log("Done");
}

one();
```

**Execution Order**

```text
one()
two()
three()
```

**Output**

```text
Done
```

Stack Flow:

```text
Push one()
Push two()
Push three()
Pop three()
Pop two()
Pop one()
```

**26. What is Event Loop?**

The **Event Loop** is a mechanism that allows JavaScript to perform **asynchronous operations** even though JavaScript is **single-threaded**.

**Key Features**

* Handles asynchronous tasks.
* Works with the **Call Stack** and **Task Queues**.
* Ensures non-blocking execution.
* Executes tasks in the correct order.

**How It Works**

1. JavaScript executes synchronous code from the **Call Stack**.
2. Async tasks are sent to Web APIs.
3. Completed tasks move to queues.
4. The Event Loop checks if the Call Stack is empty.
5. If empty, it pushes queued tasks to the Call Stack.

**Example**

```javascript id="n0p2iy"
console.log("Start");

setTimeout(() => {
    console.log("Timeout");
}, 0);

console.log("End");
```

**Output**

```text id="ib9jza"
Start
End
Timeout
```

**27. Microtask vs Macrotask?**

JavaScript maintains separate queues for **Microtasks** and **Macrotasks**.

| Feature  | **Microtask**           | **Macrotask**           |
| -------- | ----------------------- | ----------------------- |
| Priority | Higher                  | Lower                   |
| Executes | Before Macrotasks       | After Microtasks        |
| Examples | Promise, queueMicrotask | setTimeout, setInterval |

**Key Features**

* Microtasks always run first.
* Event Loop processes all Microtasks before moving to Macrotasks.

**How It Works**

* Synchronous Code
* Microtask Queue
* Macrotask Queue

**Example**

```javascript id="52efk7"
console.log("Start");

setTimeout(() => {
    console.log("Timeout");
}, 0);

Promise.resolve().then(() => {
    console.log("Promise");
});

console.log("End");
```

**Output**

```text id="9qmbqn"
Start
End
Promise
Timeout
```

**28. setTimeout vs setImmediate?**

Both are used for asynchronous execution, but they are scheduled differently.

| Feature     | **setTimeout()**    | **setImmediate()**              |
| ----------- | ------------------- | ------------------------------- |
| Execution   | After timer expires | Immediately after current cycle |
| Queue       | Timer Queue         | Check Queue                     |
| Environment | Browser & Node.js   | Node.js                         |

**Key Features**

* `setTimeout(fn, 0)` does not execute immediately.
* `setImmediate()` is mainly available in Node.js.

**How It Works**

* `setTimeout()` waits for the timer phase.
* `setImmediate()` executes during the check phase.

**Example**

```javascript id="mzjvto"
setTimeout(() => {
    console.log("Timeout");
}, 0);

setImmediate(() => {
    console.log("Immediate");
});
```

**Output**

```text id="4f7jvh"
Execution order may vary depending on environment.
```

**29. What is Callback?**

A **Callback Function** is a function passed as an argument to another function and executed later.

**Key Features**

* Enables asynchronous programming.
* Promotes code reusability.
* Commonly used in event handling and API calls.

**How It Works**

* One function receives another function as a parameter.
* The received function is executed when needed.

**Example**

```javascript id="5v2b3z"
function greet(name, callback) {
    console.log("Hello " + name);
    callback();
}

function done() {
    console.log("Completed");
}

greet("John", done);
```

**Output**

```text id="tq9tn5"
Hello John
Completed
```

**30. What is Callback Hell?**

**Callback Hell** occurs when multiple nested callbacks make code difficult to read, understand, and maintain.

**Key Features**

* Deeply nested code structure.
* Hard to debug and maintain.
* Common in complex asynchronous operations.
* Solved using **Promises** and **Async/Await**.

**How It Works**

* One callback contains another callback, which contains another callback, and so on.

**Example**

```javascript id="f2e5f0"
getUser(function(user) {
    getOrders(user, function(orders) {
        getPayment(orders, function(payment) {
            console.log(payment);
        });
    });
});
```

This structure becomes difficult to manage as nesting increases.

**31. What is a Promise?**

A **Promise** is an object that represents the **eventual result** of an asynchronous operation.

**Key Features**

* Handles asynchronous tasks.
* Avoids callback hell.
* Supports chaining using `.then()`.
* Provides error handling using `.catch()`.

**How It Works**

* A Promise is created.
* It eventually succeeds or fails.
* The result is handled using `.then()` and `.catch()`.

**Example**

```javascript
const promise = new Promise((resolve, reject) => {
    resolve("Success");
});

promise.then(result => {
    console.log(result);
});
```

**Output**

```text
Success
```

**32. Promise States?**

A **Promise** can be in one of **3 states**.

**1. Pending**

* Initial state.
* Operation is still running.

**2. Fulfilled**

* Operation completed successfully.

**3. Rejected**

* Operation failed.

**Key Features**

* A Promise starts as **Pending**.
* It can become either **Fulfilled** or **Rejected**.
* Once settled, its state cannot change.

**Example**

```javascript
const promise = new Promise((resolve, reject) => {
    resolve("Done");
});
```

State Flow:

```text
Pending → Fulfilled
```

Or

```text
Pending → Rejected
```

**33. What is async/await?**

**async/await** is a modern way to work with Promises that makes asynchronous code look synchronous.

**Key Features**

* Cleaner and more readable code.
* Built on top of Promises.
* Simplifies asynchronous programming.
* Reduces callback nesting.

**How It Works**

* `async` makes a function return a Promise.
* `await` pauses execution until the Promise is resolved.

**Example**

```javascript
async function getData() {
    return "Hello";
}

getData().then(data => {
    console.log(data);
});
```

**Output**

```text
Hello
```

**Using await**

```javascript
async function getData() {
    let result = await Promise.resolve("Hello");
    console.log(result);
}

getData();
```

**Output**

```text
Hello
```

**34. Error Handling in async/await?**

Errors in **async/await** are handled using **try...catch** blocks.

**Key Features**

* Cleaner error handling.
* Works like synchronous exception handling.
* Catches rejected Promises.

**How It Works**

* Place asynchronous code inside `try`.
* Handle errors inside `catch`.

**Example**

```javascript
async function getData() {
    try {
        let result = await Promise.reject("Error Occurred");
        console.log(result);
    } catch (error) {
        console.log(error);
    }
}

getData();
```

**Output**

```text
Error Occurred
```

**35. What is 'this' Keyword?**

The **this** keyword refers to the object that is currently executing the function.

**Key Features**

* Value depends on how the function is called.
* Used to access object properties.
* Changes based on execution context.
* Arrow functions do not have their own `this`.

**How It Works**

* In an object method, `this` refers to that object.
* In a normal function, `this` depends on the calling context.

**Example**

```javascript
const person = {
    name: "John",
    greet() {
        console.log(this.name);
    }
};

person.greet();
```

**Output**

```text
John
```

**Example with Normal Function**

```javascript
function show() {
    console.log(this);
}

show();
```

The value of `this` depends on where and how the function is executed.

**36. this in Arrow vs Normal Function?**

The value of **this** behaves differently in **arrow functions** and **normal functions**.

| Feature      | **Normal Function** | **Arrow Function**         |
| ------------ | ------------------- | -------------------------- |
| Own `this`   | Yes                 | No                         |
| `this` Value | Depends on caller   | Inherited from outer scope |
| Common Use   | Object methods      | Callbacks                  |

**Key Features**

* Normal functions create their own **this**.
* Arrow functions inherit **this** from the surrounding scope.

**How It Works**

* Normal function: `this` is determined when the function is called.
* Arrow function: `this` is determined where the function is defined.

**Example**

```javascript id="4b6yhb"
const person = {
    name: "John",

    normal: function() {
        console.log(this.name);
    },

    arrow: () => {
        console.log(this.name);
    }
};

person.normal();
person.arrow();
```

**Output**

```text id="kmrcru"
John
undefined
```

**Interview One-Liner**

**Normal functions** have their own **this**, while **arrow functions** inherit **this** from the outer scope.

**37. bind vs call vs apply?**

All three methods are used to control the value of **this**.

| Method      | Execution            | Arguments  |
| ----------- | -------------------- | ---------- |
| **call()**  | Immediately          | Separately |
| **apply()** | Immediately          | Array      |
| **bind()**  | Returns New Function | Separately |

**Key Features**

* Used to borrow methods from other objects.
* Allows explicit control of **this**.
* `bind()` does not execute immediately.

**How It Works**

**call()**

```javascript id="8vpx67"
function greet(city) {
    console.log(this.name + " " + city);
}

const person = { name: "John" };

greet.call(person, "Delhi");
```

**Output**

```text id="m0v6uc"
John Delhi
```

**apply()**

```javascript id="5gkzkt"
greet.apply(person, ["Delhi"]);
```

**Output**

```text id="qj7jlp"
John Delhi
```

**bind()**

```javascript id="w79m1q"
const newFunc = greet.bind(person, "Delhi");
newFunc();
```

**Output**

```text id="06wwsy"
John Delhi
```

**Interview One-Liner**

* **call()**: Executes immediately with arguments passed separately.
* **apply()**: Executes immediately with arguments passed as an array.
* **bind()**: Returns a new function with a fixed `this`.

**38. What is Prototype?**

A **Prototype** is an object from which other objects inherit properties and methods.

**Key Features**

* Every JavaScript object has a prototype.
* Enables code sharing.
* Supports inheritance.
* Reduces memory usage.

**How It Works**

* Methods added to a prototype are shared by all object instances.

**Example**

```javascript id="c4tl7f"
function Person(name) {
    this.name = name;
}

Person.prototype.greet = function() {
    console.log("Hello");
};

const p1 = new Person("John");
p1.greet();
```

**Output**

```text id="lqagfr"
Hello
```

**39. What is Prototype Chain?**

A **Prototype Chain** is the mechanism JavaScript uses to search for properties and methods.

**Key Features**

* Supports inheritance.
* Enables property lookup.
* Continues searching until found.
* Ends at `null`.

**How It Works**

1. JavaScript looks in the current object.
2. If not found, it checks the prototype.
3. Continues up the chain.
4. Stops when it reaches `null`.

**Example**

```javascript id="7gr0bh"
const person = {
    greet() {
        console.log("Hello");
    }
};

const user = Object.create(person);

user.greet();
```

**Output**

```text id="rl0f57"
Hello
```

Here, `greet()` is found through the prototype chain.

**40. What is Inheritance in JS?**

**Inheritance** is the ability of one object to access properties and methods of another object.

**Key Features**

* Promotes code reuse.
* Avoids duplication.
* Implemented using prototypes.
* Supports hierarchical relationships.

**How It Works**

* A child object inherits features from a parent object through the prototype chain.

**Example**

```javascript id="e2n6sl"
const animal = {
    eat() {
        console.log("Eating");
    }
};

const dog = Object.create(animal);

dog.eat();
```

**Output**

```text id="w4xchd"
Eating
```

`dog` inherits the `eat()` method from `animal`.


**41. Object.create vs Class?**

Both **Object.create()** and **class** are used to create objects and implement inheritance.

| Feature       | **Object.create()**    | **Class**          |
| ------------- | ---------------------- | ------------------ |
| Style         | Prototype-Based        | Class-Based Syntax |
| Inheritance   | Directly via Prototype | Using `extends`    |
| Readability   | Less Readable          | More Readable      |
| Introduced In | ES5                    | ES6                |

**Key Features**

* `Object.create()` creates an object with a specified prototype.
* `class` provides a cleaner syntax over prototypes.
* Both use prototypes internally.

**How It Works**

**Object.create() Example**

```javascript id="1d0u6e"
const person = {
    greet() {
        console.log("Hello");
    }
};

const user = Object.create(person);

user.greet();
```

**Output**

```text id="v7i48f"
Hello
```

**Class Example**

```javascript id="93tujk"
class Person {
    greet() {
        console.log("Hello");
    }
}

const user = new Person();
user.greet();
```

**Output**

```text id="6k3ndw"
Hello
```

**Interview One-Liner**

`Object.create()` directly creates objects using prototypes, while **class** provides a cleaner and more readable syntax for the same prototype-based inheritance.

**42. What is Class in JS?**

A **Class** is a blueprint for creating objects with properties and methods.

**Key Features**

* Introduced in **ES6**.
* Supports object creation.
* Supports inheritance using `extends`.
* Internally uses prototypes.

**How It Works**

* Define a class.
* Create objects using the `new` keyword.

**Example**

```javascript id="4i9rlz"
class Person {
    constructor(name) {
        this.name = name;
    }

    greet() {
        console.log("Hello " + this.name);
    }
}

const user = new Person("John");
user.greet();
```

**Output**

```text id="3cbv4i"
Hello John
```

**43. Constructor Function?**

A **Constructor Function** is a regular function used to create multiple objects with the same structure.

**Key Features**

* Used before ES6 classes.
* Creates object instances.
* Works with the `new` keyword.
* Reusable object template.

**How It Works**

* Define a function.
* Use `this` to assign properties.
* Create objects using `new`.

**Example**

```javascript id="l7g89m"
function Person(name) {
    this.name = name;
}

const user = new Person("John");

console.log(user.name);
```

**Output**

```text id="bgwz1s"
John
```

**44. What is the new Keyword?**

The **new** keyword creates a new object from a constructor function or class.

**Key Features**

* Creates a new empty object.
* Links the object to the prototype.
* Assigns `this` to the new object.
* Returns the created object.

**How It Works**

1. Creates an empty object.
2. Sets the prototype.
3. Binds `this` to the new object.
4. Returns the object.

**Example**

```javascript id="p5lmri"
function Person(name) {
    this.name = name;
}

const user = new Person("John");

console.log(user.name);
```

**Output**

```text id="08vy6r"
John
```

**45. What is Shallow Copy?**

A **Shallow Copy** creates a new object, but nested objects are still shared by reference.

**Key Features**

* Copies only the first level.
* Nested objects remain linked.
* Faster than deep copy.
* Changes to nested objects affect both copies.

**How It Works**

* Top-level properties are copied.
* Reference-type properties still point to the same memory location.

**Example**

```javascript id="3n80vn"
const user1 = {
    name: "John",
    address: {
        city: "Delhi"
    }
};

const user2 = { ...user1 };

user2.address.city = "Mumbai";

console.log(user1.address.city);
```

**Output**

```text id="k8thc4"
Mumbai
```

Both objects share the same nested `address` object.

**46. What is Deep Copy?**

A **Deep Copy** creates a completely independent copy of an object, including all nested objects.

**Key Features**

* Copies all levels of an object.
* No shared references.
* Changes in one object do not affect the other.
* Safer for complex data structures.

**How It Works**

* Creates new copies of nested objects and arrays.
* Every level gets its own memory location.

**Example**

```javascript
const user1 = {
    name: "John",
    address: {
        city: "Delhi"
    }
};

const user2 = structuredClone(user1);

user2.address.city = "Mumbai";

console.log(user1.address.city);
console.log(user2.address.city);
```

**Output**

```text
Delhi
Mumbai
```

**47. JSON.parse/stringify Limitations?**

`JSON.parse(JSON.stringify())` is a common way to create a deep copy, but it has limitations.

**Key Features**

* Works only for JSON-compatible data.
* Loses special object types.
* Cannot handle circular references.
* Removes functions and undefined values.

**Limitations**

| Data Type          | Result              |
| ------------------ | ------------------- |
| Function           | Removed             |
| Undefined          | Removed             |
| Date               | Converted to String |
| Map                | Lost                |
| Set                | Lost                |
| Circular Reference | Error               |

**Example**

```javascript
const obj = {
    date: new Date(),
    fn: function() {}
};

const copy = JSON.parse(JSON.stringify(obj));

console.log(copy);
```

**Output**

```text
{ date: "2025-01-01T..." }
```

The function is removed and Date becomes a string.

**48. What is structuredClone?**

**structuredClone()** is a modern JavaScript method used to create a true deep copy of an object.

**Key Features**

* Creates deep copies.
* Supports nested objects.
* Supports Map, Set, Date, ArrayBuffer, etc.
* Better than JSON.parse/stringify for complex objects.

**How It Works**

* Recursively copies all supported data structures.
* Produces a completely independent clone.

**Example**

```javascript
const user1 = {
    name: "John",
    address: {
        city: "Delhi"
    }
};

const user2 = structuredClone(user1);

user2.address.city = "Mumbai";

console.log(user1.address.city);
```

**Output**

```text
Delhi
```

**49. What is Immutability?**

**Immutability** means an object or value cannot be changed after it is created.

**Key Features**

* Original data remains unchanged.
* New data is created instead of modifying existing data.
* Makes applications more predictable.
* Commonly used in React and Redux.

**How It Works**

* Instead of updating an object, create a new one with updated values.

**Example**

```javascript
const user = {
    name: "John"
};

const updatedUser = {
    ...user,
    name: "Mike"
};

console.log(user.name);
console.log(updatedUser.name);
```

**Output**

```text
John
Mike
```

**50. Why is Immutability Important?**

**Immutability** improves code quality and application performance.

**Key Features**

* Predictable state changes.
* Easier debugging.
* Prevents accidental modifications.
* Simplifies change detection.
* Improves maintainability.

**How It Works**

* Since data never changes directly, tracking updates becomes easier.
* Frameworks like React can efficiently detect changes.

**Example**

```javascript
const numbers = [1, 2, 3];

const newNumbers = [...numbers, 4];

console.log(numbers);
console.log(newNumbers);
```

**Output**

```text
[1, 2, 3]
[1, 2, 3, 4]
```

The original array remains unchanged.

**51. What is Destructuring?**

**Destructuring** is an ES6 feature that allows you to extract values from **arrays** or **objects** and assign them to variables in a shorter way.

**Key Features**

* Cleaner and shorter syntax.
* Works with arrays and objects.
* Improves code readability.
* Reduces repetitive code.

**How It Works**

* JavaScript extracts values and assigns them directly to variables.

**Example**

```javascript
const user = {
    name: "John",
    age: 25
};

const { name, age } = user;

console.log(name);
console.log(age);
```

**Output**

```text
John
25
```

**52. Array vs Object Destructuring?**

Both are used to extract values, but they work differently.

| Feature         | **Array Destructuring** | **Object Destructuring** |
| --------------- | ----------------------- | ------------------------ |
| Based On        | Position                | Property Name            |
| Syntax          | `[]`                    | `{}`                     |
| Order Important | Yes                     | No                       |

**Key Features**

* Arrays use index positions.
* Objects use property names.
* Object destructuring is more flexible.

**Array Destructuring Example**

```javascript
const colors = ["Red", "Blue"];

const [first, second] = colors;

console.log(first);
```

**Output**

```text
Red
```

**Object Destructuring Example**

```javascript
const user = {
    name: "John",
    age: 25
};

const { age, name } = user;

console.log(name);
```

**Output**

```text
John
```

**53. Spread vs Rest Operator?**

Both use the same syntax `...` but serve different purposes.

| Feature | **Spread Operator**             | **Rest Operator**                  |
| ------- | ------------------------------- | ---------------------------------- |
| Purpose | Expands Values                  | Collects Values                    |
| Usage   | Arrays, Objects, Function Calls | Function Parameters, Destructuring |

**Key Features**

* **Spread** breaks apart elements.
* **Rest** gathers elements into a single variable.

**How It Works**

**Spread Example**

```javascript
const arr1 = [1, 2];
const arr2 = [...arr1, 3, 4];

console.log(arr2);
```

**Output**

```text
[1, 2, 3, 4]
```

**Rest Example**

```javascript
function sum(...numbers) {
    console.log(numbers);
}

sum(1, 2, 3);
```

**Output**

```text
[1, 2, 3]
```

**Interview One-Liner**

**Spread** expands values, while **Rest** collects multiple values into a single variable.

**54. What are Template Literals?**

**Template Literals** are strings enclosed in backticks (`` ` ``) that allow variable interpolation and multi-line strings.

**Key Features**

* Introduced in ES6.
* Supports embedded expressions.
* Supports multi-line strings.
* Improves readability.

**How It Works**

* Use `${}` to insert variables or expressions into a string.

**Example**

```javascript
const name = "John";

console.log(`Hello ${name}`);
```

**Output**

```text
Hello John
```

**Multi-Line Example**

```javascript
const message = `
Hello
World
`;

console.log(message);
```

**55. What are Default Parameters?**

**Default Parameters** allow function parameters to have predefined values if no argument is provided.

**Key Features**

* Introduced in ES6.
* Prevents undefined values.
* Makes functions more flexible.
* Reduces extra conditional checks.

**How It Works**

* If an argument is missing, the default value is used.

**Example**

```javascript
function greet(name = "Guest") {
    console.log(`Hello ${name}`);
}

greet();
greet("John");
```

**Output**

```text
Hello Guest
Hello John
```

**56. What is Optional Chaining (`?.`)?**

**Optional Chaining (`?.`)** is an operator that safely accesses nested object properties without causing an error if a property is `null` or `undefined`.

**Key Features**

* Prevents runtime errors.
* Simplifies nested property access.
* Returns `undefined` if the value does not exist.
* Improves code readability.

**How It Works**

* JavaScript stops evaluation if a property is `null` or `undefined`.
* Returns `undefined` instead of throwing an error.

**Example**

```javascript
const user = {
    name: "John"
};

console.log(user.address?.city);
```

**Output**

```text
undefined
```

Without optional chaining:

```javascript
console.log(user.address.city);
```

**Output**

```text
TypeError
```

**57. What is Nullish Coalescing (`??`)?**

**Nullish Coalescing (`??`)** provides a default value only when the left side is `null` or `undefined`.

**Key Features**

* Handles missing values.
* Does not treat `0`, `false`, or `""` as missing.
* Safer than `||` in many cases.

**How It Works**

* Returns the right value only if the left value is `null` or `undefined`.

**Example**

```javascript
const name = null;

console.log(name ?? "Guest");
```

**Output**

```text
Guest
```

**Example**

```javascript
const count = 0;

console.log(count ?? 10);
```

**Output**

```text
0
```

**58. What are Modules in JS?**

**Modules** allow code to be split into separate files and reused across applications.

**Key Features**

* Improves code organization.
* Promotes reusability.
* Avoids global scope pollution.
* Supports import and export.

**How It Works**

* One file exports functionality.
* Another file imports and uses it.

**math.js**

```javascript
export const add = (a, b) => a + b;
```

**app.js**

```javascript
import { add } from "./math.js";

console.log(add(2, 3));
```

**Output**

```text
5
```

**59. import vs require?**

Both are used to load modules, but they belong to different module systems.

| Feature       | **import**        | **require**    |
| ------------- | ----------------- | -------------- |
| Module System | ES Modules        | CommonJS       |
| Loading       | Static            | Dynamic        |
| Syntax        | Modern            | Traditional    |
| Environment   | Browser & Node.js | Mainly Node.js |

**Key Features**

* `import` is the modern ES6 approach.
* `require` is used in CommonJS modules.
* `import` is generally preferred for new projects.

**import Example**

```javascript
import { add } from "./math.js";
```

**require Example**

```javascript
const add = require("./math");
```

**Interview One-Liner**

**import** belongs to **ES Modules**, while **require** belongs to **CommonJS**.

**60. What is Dynamic Import?**

**Dynamic Import** allows modules to be loaded only when needed.

**Key Features**

* Loads modules on demand.
* Improves application performance.
* Supports code splitting.
* Returns a Promise.

**How It Works**

* Uses the `import()` function.
* Loads the module asynchronously.

**Example**

```javascript
async function loadModule() {
    const math = await import("./math.js");

    console.log(math.add(2, 3));
}

loadModule();
```

**Output**

```text
5
```

**61. Map vs Object?**

Both **Map** and **Object** store data as key-value pairs, but they have important differences.

| Feature   | **Map**           | **Object**                  |
| --------- | ----------------- | --------------------------- |
| Key Types | Any Data Type     | String or Symbol            |
| Order     | Preserved         | Not Guaranteed Historically |
| Size      | `size` Property   | Manual Counting             |
| Iteration | Directly Iterable | Requires Methods            |

**Key Features**

* **Map** allows any value as a key.
* **Map** provides better performance for frequent additions and deletions.
* **Object** is mainly used for structured data.

**How It Works**

**Map Example**

```javascript id="2l4t83"
const map = new Map();

map.set("name", "John");

console.log(map.get("name"));
```

**Output**

```text id="7u7lm5"
John
```

**Object Example**

```javascript id="6j6mcu"
const user = {
    name: "John"
};

console.log(user.name);
```

**Output**

```text id="k84fij"
John
```

**Interview One-Liner**

**Map** supports any type of key and provides built-in utilities, while **Object** is mainly used to represent structured data.

**62. Set vs Array?**

Both store collections of values, but they behave differently.

| Feature          | **Set**         | **Array**         |
| ---------------- | --------------- | ----------------- |
| Duplicate Values | Not Allowed     | Allowed           |
| Index Access     | No              | Yes               |
| Uniqueness       | Automatic       | Manual            |
| Size             | `size` Property | `length` Property |

**Key Features**

* **Set** stores only unique values.
* **Array** stores ordered values with indexes.
* **Set** is useful for removing duplicates.

**How It Works**

**Set Example**

```javascript id="lhw6m9"
const set = new Set([1, 2, 2, 3]);

console.log(set);
```

**Output**

```text id="kjj29v"
Set(3) {1, 2, 3}
```

**Array Example**

```javascript id="dzqch4"
const arr = [1, 2, 2, 3];

console.log(arr);
```

**Output**

```text id="k8tr7j"
[1, 2, 2, 3]
```

**63. What is WeakMap?**

A **WeakMap** is a collection of key-value pairs where keys must be objects.

**Key Features**

* Keys must be objects.
* Keys are weakly referenced.
* Supports automatic garbage collection.
* Not iterable.

**How It Works**

* If the key object is removed from memory, its entry is automatically removed from the WeakMap.

**Example**

```javascript id="tqof5s"
let user = {};

const weakMap = new WeakMap();

weakMap.set(user, "John");

console.log(weakMap.get(user));
```

**Output**

```text id="grs7mc"
John
```

**Interview One-Liner**

A **WeakMap** stores object keys with automatic memory cleanup when the key object is no longer referenced.

**64. What is WeakSet?**

A **WeakSet** is a collection that stores only objects and holds them weakly.

**Key Features**

* Stores only objects.
* No duplicate objects.
* Supports garbage collection.
* Not iterable.

**How It Works**

* When an object has no references elsewhere, it can be removed automatically.

**Example**

```javascript id="zgwg4w"
let user = {
    name: "John"
};

const weakSet = new WeakSet();

weakSet.add(user);

console.log(weakSet.has(user));
```

**Output**

```text id="s4kecm"
true
```

**Interview One-Liner**

A **WeakSet** stores unique objects and automatically removes them when they are no longer referenced.

**65. What is Symbol?**

A **Symbol** is a unique and immutable primitive data type introduced in ES6.

**Key Features**

* Always unique.
* Used as object property keys.
* Prevents property name collisions.
* Primitive data type.

**How It Works**

* Every Symbol created is unique, even if they have the same description.

**Example**

```javascript id="jlwm83"
const id1 = Symbol("id");
const id2 = Symbol("id");

console.log(id1 === id2);
```

**Output**

```text id="u6dr6r"
false
```

**Using Symbol as Object Key**

```javascript id="aq0qen"
const id = Symbol("id");

const user = {
    [id]: 101
};

console.log(user[id]);
```

**Output**

```text id="zrlnzo"
101
```

**66. What are Iterators?**

An **Iterator** is an object that allows you to traverse a collection one element at a time.

**Key Features**

* Provides sequential access to data.
* Uses the `next()` method.
* Returns value and completion status.
* Used internally by loops like `for...of`.

**How It Works**

* Each call to `next()` returns the next value.
* When all values are processed, `done` becomes `true`.

**Example**

```javascript
const arr = [10, 20, 30];

const iterator = arr[Symbol.iterator]();

console.log(iterator.next());
console.log(iterator.next());
console.log(iterator.next());
```

**Output**

```text
{ value: 10, done: false }
{ value: 20, done: false }
{ value: 30, done: false }
```

**Interview One-Liner**

An **Iterator** is an object that provides a standard way to access elements one by one using the `next()` method.

**67. What are Generators?**

A **Generator** is a special function that can pause and resume its execution.

**Key Features**

* Defined using `function*`.
* Uses the `yield` keyword.
* Returns an iterator.
* Generates values on demand.

**How It Works**

* `yield` pauses execution.
* Calling `next()` resumes execution from the last pause point.

**Example**

```javascript
function* numbers() {
    yield 1;
    yield 2;
    yield 3;
}

const gen = numbers();

console.log(gen.next());
console.log(gen.next());
console.log(gen.next());
```

**Output**

```text
{ value: 1, done: false }
{ value: 2, done: false }
{ value: 3, done: false }
```

**Interview One-Liner**

A **Generator** is a special function that produces values one at a time using `yield`.

**68. for...in vs for...of?**

Both are used for iteration, but they iterate over different things.

| Feature       | **for...in**  | **for...of**               |
| ------------- | ------------- | -------------------------- |
| Iterates Over | Property Keys | Values                     |
| Used For      | Objects       | Arrays, Strings, Iterables |
| Returns       | Keys          | Values                     |

**Key Features**

* `for...in` is mainly used for objects.
* `for...of` is mainly used for arrays and iterables.

**for...in Example**

```javascript
const user = {
    name: "John",
    age: 25
};

for (let key in user) {
    console.log(key);
}
```

**Output**

```text
name
age
```

**for...of Example**

```javascript
const arr = [10, 20, 30];

for (let value of arr) {
    console.log(value);
}
```

**Output**

```text
10
20
30
```

**Interview One-Liner**

* **for...in** iterates over object keys.
* **for...of** iterates over iterable values.

**69. Promise.all vs Promise.allSettled?**

Both execute multiple promises together, but they handle failures differently.

| Feature | **Promise.all()** | **Promise.allSettled()** |
| ------- | ----------------- | ------------------------ |
| Success | All must succeed  | Returns all results      |
| Failure | Fails immediately | Never fails immediately  |
| Result  | Values Only       | Status + Value/Reason    |

**Key Features**

* `Promise.all()` is faster when all promises must succeed.
* `Promise.allSettled()` is useful when every result is needed.

**How It Works**

**Promise.all Example**

```javascript
Promise.all([
    Promise.resolve("A"),
    Promise.resolve("B")
])
.then(console.log);
```

**Output**

```text
["A", "B"]
```

**Promise.allSettled Example**

```javascript
Promise.allSettled([
    Promise.resolve("A"),
    Promise.reject("Error")
])
.then(console.log);
```

**Output**

```text
[
  { status: "fulfilled", value: "A" },
  { status: "rejected", reason: "Error" }
]
```

**Interview One-Liner**

* **Promise.all()** fails if any promise fails.
* **Promise.allSettled()** returns results of all promises regardless of success or failure.

**70. What is Promise.race()?**

**Promise.race()** returns the result of the first promise that settles, whether it succeeds or fails.

**Key Features**

* Returns the fastest result.
* Can return success or failure.
* Useful for timeouts and competing tasks.

**How It Works**

* Multiple promises start together.
* The first settled promise determines the result.

**Example**

```javascript
const p1 = new Promise(resolve =>
    setTimeout(() => resolve("Fast"), 1000)
);

const p2 = new Promise(resolve =>
    setTimeout(() => resolve("Slow"), 2000)
);

Promise.race([p1, p2])
    .then(console.log);
```

**Output**

```text
Fast
```

**71. What is Promise.any()?**

**Promise.any()** returns the result of the **first fulfilled promise** and ignores rejected promises.

**Key Features**

* Returns the first successful result.
* Ignores rejected promises.
* Fails only if all promises are rejected.
* Useful when any successful response is enough.

**How It Works**

* Starts all promises simultaneously.
* Resolves as soon as one promise is fulfilled.
* Rejects with **AggregateError** if all fail.

**Example**

```javascript id="zvtq76"
const p1 = Promise.reject("Error 1");
const p2 = Promise.resolve("Success");
const p3 = Promise.reject("Error 2");

Promise.any([p1, p2, p3])
    .then(console.log);
```

**Output**

```text id="6yb7el"
Success
```

**Interview One-Liner**

**Promise.any()** returns the first successful promise and ignores failures unless all promises fail.

**72. What is Debounce?**

**Debounce** limits a function execution until a specified delay has passed since the last event.

**Key Features**

* Reduces unnecessary function calls.
* Improves performance.
* Commonly used for search inputs.
* Executes only after user stops triggering events.

**How It Works**

* Every new event resets the timer.
* Function executes only after no new events occur during the delay period.

**Example**

```javascript id="q8n8j8"
searchInput.addEventListener("input", debounce(search, 500));
```

If a user types continuously, the search function runs only once after typing stops.

**Interview One-Liner**

**Debounce** delays execution until events stop occurring for a specified time.

**73. What is Throttle?**

**Throttle** limits a function to execute at most once within a specified time interval.

**Key Features**

* Controls execution frequency.
* Improves performance.
* Commonly used for scroll and resize events.
* Ensures regular execution intervals.

**How It Works**

* First call executes immediately.
* Additional calls are ignored until the time limit expires.

**Example**

```javascript id="s61f44"
window.addEventListener("scroll", throttle(loadData, 1000));
```

The function executes at most once every second.

**Interview One-Liner**

**Throttle** ensures a function executes only once during a specified time interval.

**74. How to Implement Debounce?**

**Debounce Implementation**

**How It Works**

* Stores a timer.
* Clears the previous timer on every call.
* Starts a new timer.
* Executes the function only after the delay.

**Example**

```javascript id="2f8l8h"
function debounce(fn, delay) {
    let timer;

    return function (...args) {
        clearTimeout(timer);

        timer = setTimeout(() => {
            fn(...args);
        }, delay);
    };
}
```

**Usage**

```javascript id="qvph5y"
const search = debounce(() => {
    console.log("Searching...");
}, 500);

search();
```

**Interview One-Liner**

A **Debounce Function** delays execution and runs only after the specified waiting period.

**75. How to Implement Throttle?**

**Throttle Implementation**

**How It Works**

* Uses a flag to track execution.
* Executes immediately.
* Blocks additional calls until the delay ends.

**Example**

```javascript id="2aq4cw"
function throttle(fn, delay) {
    let shouldWait = false;

    return function (...args) {
        if (shouldWait) return;

        fn(...args);
        shouldWait = true;

        setTimeout(() => {
            shouldWait = false;
        }, delay);
    };
}
```

**Usage**

```javascript id="34p9cq"
const scrollHandler = throttle(() => {
    console.log("Scrolling...");
}, 1000);

scrollHandler();
```

**Interview One-Liner**

A **Throttle Function** limits execution to once per specified time interval.


**76. What is Currying?**

**Currying** is a technique of transforming a function with multiple arguments into a sequence of functions that take one argument at a time.

**Key Features**

* Breaks a function into smaller functions.
* Improves reusability.
* Enables partial application.
* Common in functional programming.

**How It Works**

* Instead of taking all arguments at once, each function takes one argument and returns another function.

**Example**

```javascript
function add(a) {
    return function(b) {
        return a + b;
    };
}

console.log(add(2)(3));
```

**Output**

```text
5
```

**Interview One-Liner**

**Currying** converts a function with multiple parameters into a chain of functions that take one parameter at a time.

**77. What is Function Composition?**

**Function Composition** is the process of combining multiple functions to create a new function.

**Key Features**

* Promotes code reuse.
* Creates small reusable functions.
* Improves readability.
* Common in functional programming.

**How It Works**

* Output of one function becomes the input of another function.

**Example**

```javascript
const double = x => x * 2;
const square = x => x * x;

const result = square(double(3));

console.log(result);
```

**Output**

```text
36
```

**Interview One-Liner**

**Function Composition** combines multiple functions where the output of one function becomes the input of the next.

**78. What is Memoization?**

**Memoization** is an optimization technique that stores previously computed results to avoid repeated calculations.

**Key Features**

* Improves performance.
* Avoids repeated computations.
* Uses caching.
* Useful for expensive operations.

**How It Works**

* Check if the result already exists in cache.
* Return cached value if available.
* Otherwise calculate and store it.

**Example**

```javascript
function memoizedSquare() {
    const cache = {};

    return function(num) {
        if (cache[num]) {
            return cache[num];
        }

        cache[num] = num * num;
        return cache[num];
    };
}

const square = memoizedSquare();

console.log(square(5));
console.log(square(5));
```

**Output**

```text
25
25
```

**Interview One-Liner**

**Memoization** stores computed results in a cache to improve performance for repeated calls.

**79. What is a Pure Function?**

A **Pure Function** is a function that always produces the same output for the same input and has no side effects.

**Key Features**

* Predictable output.
* No dependency on external data.
* No side effects.
* Easy to test and debug.

**How It Works**

* Uses only its input parameters.
* Does not modify external variables.

**Example**

```javascript
function add(a, b) {
    return a + b;
}

console.log(add(2, 3));
```

**Output**

```text
5
```

**Interview One-Liner**

A **Pure Function** always returns the same result for the same input and does not affect anything outside the function.

**80. What are Side Effects?**

A **Side Effect** is any operation that modifies data or interacts with resources outside the function.

**Key Features**

* Changes external state.
* Makes functions less predictable.
* Can affect application behavior.
* Often avoided in pure functions.

**How It Works**

* Function modifies variables, DOM, files, databases, or APIs outside its scope.

**Example**

```javascript
let count = 0;

function increment() {
    count++;
}

increment();

console.log(count);
```

**Output**

```text
1
```

Here, the function changes an external variable, which is a side effect.

**81. What is DOM?**

**DOM (Document Object Model)** is a programming interface that represents an HTML document as a tree of objects.

**Key Features**

* Represents HTML elements as objects.
* Allows JavaScript to access and modify web pages.
* Supports dynamic content updates.
* Organized in a tree structure.

**How It Works**

* Browser converts HTML into a DOM tree.
* JavaScript interacts with DOM nodes to update content, styles, and events.

**Example**

```javascript
const heading = document.getElementById("title");

heading.textContent = "Hello JavaScript";
```

**Interview One-Liner**

**DOM** is a tree-like representation of an HTML document that allows JavaScript to manipulate web page content and structure.

**82. DOM vs Virtual DOM?**

Both are used to represent UI elements, but they work differently.

| Feature      | **DOM**          | **Virtual DOM**             |
| ------------ | ---------------- | --------------------------- |
| Type         | Real Browser DOM | Lightweight Copy of DOM     |
| Update Speed | Slower           | Faster                      |
| Re-rendering | Updates Directly | Updates Differences Only    |
| Usage        | Browser          | React and Similar Libraries |

**Key Features**

* DOM updates are expensive.
* Virtual DOM improves performance by minimizing real DOM updates.
* Virtual DOM compares old and new versions before updating.

**How It Works**

1. Virtual DOM creates a copy of the UI.
2. Changes are made in the Virtual DOM.
3. Differences are calculated.
4. Only changed parts are updated in the real DOM.

**Interview One-Liner**

**DOM** is the actual page structure, while **Virtual DOM** is a lightweight copy used to optimize updates and improve performance.

**83. What is Event Delegation?**

**Event Delegation** is a technique where a parent element handles events for its child elements.

**Key Features**

* Uses event bubbling.
* Reduces memory usage.
* Improves performance.
* Works for dynamically added elements.

**How It Works**

* Attach one event listener to the parent.
* Detect which child triggered the event using `event.target`.

**Example**

```javascript
document.getElementById("list")
    .addEventListener("click", function(event) {
        console.log(event.target.textContent);
    });
```

```html
<ul id="list">
    <li>Item 1</li>
    <li>Item 2</li>
</ul>
```

**Interview One-Liner**

**Event Delegation** uses a single event listener on a parent element to manage events for multiple child elements.

**84. Bubbling vs Capturing?**

These are two phases of event propagation in JavaScript.

| Feature          | **Bubbling**    | **Capturing**   |
| ---------------- | --------------- | --------------- |
| Direction        | Child to Parent | Parent to Child |
| Default Behavior | Yes             | No              |
| Execution Order  | Bottom Up       | Top Down        |

**Key Features**

* Most events use bubbling by default.
* Capturing must be explicitly enabled.
* Both are part of event propagation.

**How It Works**

**Bubbling Order**

```text
Button → Div → Body
```

**Capturing Order**

```text
Body → Div → Button
```

**Example**

```javascript
element.addEventListener("click", handler, true);
```

`true` enables the capturing phase.

**Interview One-Liner**

* **Bubbling**: Event travels from child to parent.
* **Capturing**: Event travels from parent to child.

**85. stopPropagation vs preventDefault?**

Both control event behavior, but they serve different purposes.

| Method                | Purpose                        |
| --------------------- | ------------------------------ |
| **stopPropagation()** | Stops event propagation        |
| **preventDefault()**  | Stops default browser behavior |

**Key Features**

* `stopPropagation()` prevents the event from moving to parent elements.
* `preventDefault()` prevents the browser's default action.
* They can be used together.

**How It Works**

**stopPropagation() Example**

```javascript
button.addEventListener("click", function(event) {
    event.stopPropagation();
});
```

Parent click handlers will not execute.

**preventDefault() Example**

```javascript
document.querySelector("a")
    .addEventListener("click", function(event) {
        event.preventDefault();
    });
```

**86. What is Reflow?**

**Reflow** is the process where the browser recalculates the **layout and position** of elements on a web page.

**Key Features**

* Recalculates element size and position.
* More expensive operation.
* Can impact performance.
* Triggered by layout changes.

**How It Works**

* Browser recalculates the layout when element dimensions or structure change.

**Example**

```javascript
const box = document.getElementById("box");

box.style.width = "300px";
```

Changing the width may trigger a **Reflow** because the layout needs to be recalculated.

**Interview One-Liner**

**Reflow** is the browser process of recalculating element layout, size, and position after structural changes.

**87. What is Repaint?**

**Repaint** is the process where the browser redraws an element without changing its layout.

**Key Features**

* Updates visual appearance.
* Does not affect layout.
* Faster than reflow.
* Triggered by style changes.

**How It Works**

* Browser redraws pixels when visual properties change.

**Example**

```javascript
const box = document.getElementById("box");

box.style.backgroundColor = "red";
```

Changing color triggers a **Repaint** because only the appearance changes.

**Interview One-Liner**

**Repaint** is the browser process of redrawing an element when only its visual appearance changes.

**88. What is Lazy Loading?**

**Lazy Loading** is a technique where resources are loaded only when they are needed.

**Key Features**

* Improves page load speed.
* Reduces initial loading time.
* Saves bandwidth.
* Enhances user experience.

**How It Works**

* Resources such as images or components load only when they enter the viewport or are required.

**Example**

```html
<img src="image.jpg" loading="lazy" alt="Image">
```

The image loads only when the user scrolls near it.

**Interview One-Liner**

**Lazy Loading** delays loading resources until they are actually needed.

**89. What is Code Splitting?**

**Code Splitting** is a technique that breaks a large JavaScript bundle into smaller chunks.

**Key Features**

* Reduces initial bundle size.
* Improves application performance.
* Loads code on demand.
* Works well with lazy loading.

**How It Works**

* Only the required code is downloaded initially.
* Additional code loads when needed.

**Example**

```javascript
import("./math.js")
    .then(module => {
        console.log(module.add(2, 3));
    });
```

The module is loaded only when required.

**Interview One-Liner**

**Code Splitting** divides a large JavaScript bundle into smaller files that load when needed.

**90. What is Tree Shaking?**

**Tree Shaking** is a build optimization technique that removes unused code from the final bundle.

**Key Features**

* Reduces bundle size.
* Improves application performance.
* Works with ES Modules.
* Removes dead code.

**How It Works**

* Bundlers analyze imports and exports.
* Unused code is excluded from the final build.

**Example**

```javascript
// math.js
export const add = () => {};
export const subtract = () => {};
```

```javascript
// app.js
import { add } from "./math.js";
```

Only `add` is included in the final bundle, while `subtract` is removed.


**91. What is CORS?**

**CORS (Cross-Origin Resource Sharing)** is a browser security mechanism that allows a website to request resources from a different origin (domain, protocol, or port).

**Key Features**

* Controls **cross-origin requests**
* Uses **HTTP headers**
* Improves security while allowing approved access

**How it Works**

1. Browser sends a request to another origin.
2. Server responds with CORS headers.
3. Browser checks the headers.
4. If allowed, the response is accessible.

**Example**

```javascript
fetch("https://api.example.com/users")
  .then(res => res.json())
  .then(data => console.log(data));
```

**Common Header**

```http
Access-Control-Allow-Origin: *
```

---

**92. What is Same-Origin Policy?**

**Same-Origin Policy (SOP)** is a browser security rule that prevents a webpage from accessing resources from a different origin.

**Key Features**

* Protects user data
* Blocks unauthorized cross-origin access
* Origin = **Protocol + Domain + Port**

**How it Works**

* Allowed:

  ```
  https://example.com → https://example.com
  ```
* Blocked:

  ```
  https://example.com → https://api.example.com
  ```

**Example**

```javascript
// Browser may block this request
fetch("https://other-site.com/data");
```

**Purpose**

* Prevents malicious websites from stealing sensitive information.

---

**93. LocalStorage vs SessionStorage**

| Feature   | LocalStorage                      | SessionStorage          |
| --------- | --------------------------------- | ----------------------- |
| Lifetime  | Permanent until deleted           | Exists until tab closes |
| Scope     | Shared across tabs of same origin | Only current tab        |
| Capacity  | ~5-10 MB                          | ~5 MB                   |
| Data Type | String                            | String                  |

**LocalStorage Example**

```javascript
localStorage.setItem("user", "John");

console.log(localStorage.getItem("user"));
```

**SessionStorage Example**

```javascript
sessionStorage.setItem("theme", "dark");

console.log(sessionStorage.getItem("theme"));
```

**When to Use**

* **LocalStorage**: User preferences, theme settings.
* **SessionStorage**: Temporary session data.

---

**94. Cookies vs Storage**

| Feature        | Cookies                     | LocalStorage / SessionStorage |
| -------------- | --------------------------- | ----------------------------- |
| Sent to Server | Yes                         | No                            |
| Storage Size   | ~4 KB                       | ~5-10 MB                      |
| Expiration     | Can expire                  | Depends on storage type       |
| Performance    | Slower (sent with requests) | Faster                        |
| Use Case       | Authentication, tracking    | Client-side data storage      |

**Cookie Example**

```javascript
document.cookie = "username=John";
```

**Storage Example**

```javascript
localStorage.setItem("username", "John");
```

**When to Use**

* **Cookies**: Authentication tokens, session management.
* **Storage**: User settings, cached data.

---

**95. What is Service Worker?**

A **Service Worker** is a background JavaScript file that runs separately from a web page and enables features like **offline support**, **caching**, and **push notifications**.

**Key Features**

* Works in the background
* Supports offline applications
* Caches network requests
* Enables push notifications

**How it Works**

1. Browser registers the service worker.
2. Service worker installs and caches files.
3. Future requests can be served from cache.
4. App can work even without internet.

**Example**

```javascript
if ("serviceWorker" in navigator) {
  navigator.serviceWorker.register("/sw.js")
    .then(() => console.log("Service Worker Registered"));
}
```

**sw.js**

```javascript
self.addEventListener("install", () => {
  console.log("Service Worker Installed");
});
```

**96. What is Web Worker?**

A **Web Worker** is a JavaScript feature that allows code to run in a **background thread** without blocking the main UI thread.

**Key Features**

* Runs tasks in a separate thread
* Keeps UI responsive
* Useful for heavy computations
* Communicates using messages

**How it Works**

1. Main thread creates a worker.
2. Worker runs code separately.
3. Data is exchanged using `postMessage()`.
4. Main thread remains responsive.

**main.js**

```javascript
const worker = new Worker("worker.js");

worker.postMessage("Start");

worker.onmessage = (e) => {
  console.log(e.data);
};
```

**worker.js**

```javascript
self.onmessage = () => {
  self.postMessage("Task Completed");
};
```

**Use Cases**

* Data processing
* Image manipulation
* Large calculations

**97. What does Single-Threaded mean?**

**Single-Threaded** means JavaScript executes **one task at a time** using a single call stack.

**Key Features**

* One execution thread
* Executes code sequentially
* Prevents simultaneous code execution

**How it Works**

1. Code enters the call stack.
2. Executes line by line.
3. One task must finish before the next starts.

**Example**

```javascript
console.log("A");
console.log("B");
console.log("C");
```

**Output**

```javascript
A
B
C
```

**Benefit**

* Simpler execution model.
* Avoids many thread-related issues.

---

**98. How does JavaScript handle Concurrency?**

JavaScript handles **concurrency** using the **Event Loop**, **Callback Queue**, and **Web APIs**, even though it is single-threaded.

**Key Features**

* Uses asynchronous operations
* Non-blocking behavior
* Event Loop manages task execution

**How it Works**

1. Async task goes to Web APIs.
2. After completion, callback moves to Queue.
3. Event Loop checks the Call Stack.
4. If stack is empty, callback executes.

**Example**

```javascript
console.log("Start");

setTimeout(() => {
  console.log("Async Task");
}, 1000);

console.log("End");
```

**Output**

```javascript
Start
End
Async Task
```

**Tools Used**

* Call Stack
* Web APIs
* Callback Queue
* Event Loop

---

**99. What are Memory Leaks?**

A **Memory Leak** occurs when memory that is no longer needed is not released, causing unnecessary memory consumption.

**Key Features**

* Wastes memory
* Slows application performance
* Can cause browser crashes

**Common Causes**

* Unused global variables
* Unremoved event listeners
* Forgotten timers
* Detached DOM elements

**Example**

```javascript
let data = [];

function addData() {
  data.push(new Array(1000000));
}
```

Repeated calls keep increasing memory usage.

**Result**

* Memory grows continuously.
* Application becomes slower.

---

**100. How to Avoid Memory Leaks?**

Memory leaks can be prevented by properly releasing unused resources.

**Key Features**

* Remove unnecessary references
* Clean up event listeners
* Clear timers
* Avoid excessive global variables

**Remove Event Listener**

```javascript
const btn = document.getElementById("btn");

function handleClick() {
  console.log("Clicked");
}

btn.addEventListener("click", handleClick);

// Cleanup
btn.removeEventListener("click", handleClick);
```

**Clear Timer**

```javascript
const id = setInterval(() => {
  console.log("Running");
}, 1000);

clearInterval(id);
```

**101. What is Garbage Collection?**

**Garbage Collection (GC)** is an automatic memory management process in JavaScript that removes objects that are no longer reachable or used.

**Key Features**

* Automatic memory cleanup
* Prevents unnecessary memory usage
* Managed by the JavaScript engine

**How it Works**

1. JavaScript creates objects in memory.
2. GC checks which objects are still reachable.
3. Unreachable objects are removed.
4. Memory is freed automatically.

**Example**

```javascript
let user = {
  name: "John"
};

user = null; // Object becomes unreachable
```

The object can now be removed by the garbage collector.

**Benefit**

* Reduces memory leaks.
* Improves application performance.

**102. What is Mark and Sweep?**

**Mark and Sweep** is the most common **Garbage Collection algorithm** used in JavaScript.

**Key Features**

* Finds reachable objects
* Removes unreachable objects
* Efficient memory cleanup

**How it Works**

1. **Mark Phase**

   * Starts from root objects (global objects).
   * Marks all reachable objects.

2. **Sweep Phase**

   * Removes all unmarked objects.
   * Frees their memory.

**Example**

```javascript
let user = {
  name: "John"
};

user = null;
```

Since the object is no longer reachable, it is not marked and gets removed during the sweep phase.

**Simple Rule**

* **Reachable = Keep**
* **Unreachable = Delete**

**103. What is Event Listener Leak?**

An **Event Listener Leak** happens when an event listener remains in memory even after the related DOM element is removed.

**Key Features**

* Causes memory leaks
* Wastes memory
* Can slow down applications

**Problem Example**

```javascript
const btn = document.getElementById("btn");

btn.addEventListener("click", () => {
  console.log("Clicked");
});

// Later button is removed
btn.remove();
```

The listener may still remain in memory.

**Solution**

```javascript
function handleClick() {
  console.log("Clicked");
}

btn.addEventListener("click", handleClick);

btn.removeEventListener("click", handleClick);
```

**Best Practice**

* Always remove listeners when no longer needed.

**104. What are Passive Event Listeners?**

**Passive Event Listeners** tell the browser that the event handler will **not call `preventDefault()`**, allowing better scrolling performance.

**Key Features**

* Faster scrolling
* Better performance
* Reduces browser waiting time

**How it Works**
Normally the browser waits to see if `preventDefault()` is called.

With passive listeners, the browser can continue scrolling immediately.

**Example**

```javascript
window.addEventListener(
  "scroll",
  () => {
    console.log("Scrolling");
  },
  { passive: true }
);
```

**Benefits**

* Smoother scrolling
* Better mobile performance
* Improved user experience

**105. What is requestAnimationFrame()?**

**requestAnimationFrame()** is a browser API used to create smooth and efficient animations.

**Key Features**

* Optimized for animations
* Runs before the next repaint
* Better performance than `setInterval()`

**How it Works**

1. Browser prepares the next screen repaint.
2. Executes the callback.
3. Updates the animation.
4. Repeats for the next frame.

**Example**

```javascript
let position = 0;

function animate() {
  position += 5;

  console.log(position);

  requestAnimationFrame(animate);
}

requestAnimationFrame(animate);
```

**Why Use It Instead of setInterval()?**

* Smoother animations
* Better CPU usage
* Automatically pauses in inactive tabs

**Common Use Cases**

* Moving elements
* Game development
* Canvas animations
* Visual effects


**106. What is requestIdleCallback()?**

**requestIdleCallback()** is a browser API that executes tasks when the browser is **idle**, so it doesn't affect important UI work.

**Key Features**

* Runs low-priority tasks
* Improves performance
* Prevents UI blocking
* Uses idle browser time

**How it Works**

1. Browser completes important tasks.
2. When idle time is available, the callback runs.
3. Background work is executed without affecting the user experience.

**Example**

```javascript
requestIdleCallback(() => {
  console.log("Background task executed");
});
```

**Common Use Cases**

* Analytics processing
* Data cleanup
* Prefetching resources
* Non-critical calculations

---

**107. What is IntersectionObserver?**

**IntersectionObserver** is an API used to detect when an element enters or leaves the viewport.

**Key Features**

* Efficient visibility detection
* Better than scroll event listeners
* Improves performance
* Supports lazy loading

**How it Works**

1. Observe an element.
2. Browser monitors its visibility.
3. Callback runs when visibility changes.

**Example**

```javascript
const observer = new IntersectionObserver((entries) => {
  entries.forEach(entry => {
    if (entry.isIntersecting) {
      console.log("Element is visible");
    }
  });
});

observer.observe(document.getElementById("box"));
```

**Common Use Cases**

* Lazy loading images
* Infinite scrolling
* Animations on scroll
* Ad visibility tracking

---

**108. What is MutationObserver?**

**MutationObserver** is an API that watches for changes in the **DOM**.

**Key Features**

* Detects DOM changes
* Replaces older mutation events
* Efficient monitoring
* Runs asynchronously

**How it Works**

1. Create an observer.
2. Start watching a DOM element.
3. Callback executes when changes occur.

**Example**

```javascript
const observer = new MutationObserver(() => {
  console.log("DOM changed");
});

observer.observe(document.body, {
  childList: true,
  subtree: true
});
```

**Common Use Cases**

* Tracking dynamic content
* Monitoring DOM updates
* Third-party integrations
* UI synchronization

---

**109. Performance Optimization Techniques?**

**Performance Optimization** means improving application speed, responsiveness, and resource usage.

**Key Techniques**

* **Lazy Loading**
* **Code Splitting**
* **Caching**
* **Debouncing**
* **Throttling**
* **Minification**
* **Tree Shaking**
* **Using Web Workers**
* **Optimizing Images**
* **Reducing DOM Manipulations**

**Example (Debouncing)**

```javascript
let timer;

function search() {
  clearTimeout(timer);

  timer = setTimeout(() => {
    console.log("Searching...");
  }, 300);
}
```

**Benefits**

* Faster page load
* Better user experience
* Reduced memory usage
* Improved application performance

---

**110. What is Critical Rendering Path?**

The **Critical Rendering Path (CRP)** is the sequence of steps the browser follows to convert HTML, CSS, and JavaScript into pixels on the screen.

**Key Features**

* Determines page load speed
* Affects rendering performance
* Helps optimize First Paint

**How it Works**

1. Browser downloads HTML.
2. Builds the **DOM Tree**.
3. Downloads and parses CSS.
4. Builds the **CSSOM Tree**.
5. Creates the **Render Tree**.
6. Performs **Layout**.
7. Performs **Paint**.

**Simple Flow**

```text
HTML → DOM
CSS → CSSOM
DOM + CSSOM → Render Tree
Render Tree → Layout
Layout → Paint
```

**Example**

```html
<!DOCTYPE html>
<html>
<head>
  <link rel="stylesheet" href="style.css">
</head>
<body>
  <h1>Hello World</h1>
</body>
</html>
```

**Optimization Techniques**

* Minimize CSS and JavaScript
* Defer non-critical scripts
* Inline critical CSS
* Reduce render-blocking resources
* Optimize images and fonts

**Goal**

* Render content as quickly as possible for a faster user experience.

**111. Closure Loop Output (var vs let)?**

A **Closure** remembers variables from its outer scope even after the outer function has finished executing.

**Key Difference**

* **var** shares one variable across all iterations.
* **let** creates a new variable for each iteration.

**Using var**

```javascript
for (var i = 0; i < 3; i++) {
  setTimeout(() => {
    console.log(i);
  }, 100);
}
```

**Output**

```javascript
3
3
3
```

**Why?**

* All callbacks share the same `i`.
* Loop ends with `i = 3`.

**Using let**

```javascript
for (let i = 0; i < 3; i++) {
  setTimeout(() => {
    console.log(i);
  }, 100);
}
```

**Output**

```javascript
0
1
2
```

**Why?**

* Each iteration gets its own `i`.

---

**112. Output of setTimeout in Loop?**

`setTimeout()` is asynchronous and executes after the loop finishes.

**Example with var**

```javascript
for (var i = 1; i <= 3; i++) {
  setTimeout(() => {
    console.log(i);
  }, 0);
}
```

**Output**

```javascript
4
4
4
```

**Example with let**

```javascript
for (let i = 1; i <= 3; i++) {
  setTimeout(() => {
    console.log(i);
  }, 0);
}
```

**Output**

```javascript
1
2
3
```

**Key Point**

* `setTimeout()` callback runs later through the **Event Loop**.
* `let` preserves each iteration value.

---

**113. var vs let in Loop?**

**var**

* Function scoped
* Single variable shared across iterations
* Can cause closure issues

**let**

* Block scoped
* New variable per iteration
* Preferred in loops

**Example**

```javascript
for (var i = 0; i < 3; i++) {
  setTimeout(() => console.log(i));
}
```

**Output**

```javascript
3
3
3
```

**Example**

```javascript
for (let i = 0; i < 3; i++) {
  setTimeout(() => console.log(i));
}
```

**Output**

```javascript
0
1
2
```

**Interview Answer**

* Use **let** in loops because it creates a separate binding for every iteration.

---

**114. Explain Event Loop with Example?**

The **Event Loop** is a mechanism that allows JavaScript to handle asynchronous operations while remaining single-threaded.

**Key Components**

* **Call Stack**
* **Web APIs**
* **Callback Queue**
* **Event Loop**

**How it Works**

1. Synchronous code runs first.
2. Async tasks move to Web APIs.
3. Completed callbacks enter the Queue.
4. Event Loop pushes callbacks to the Call Stack when it's empty.

**Example**

```javascript
console.log("Start");

setTimeout(() => {
  console.log("Timeout");
}, 0);

console.log("End");
```

**Output**

```javascript
Start
End
Timeout
```

**Execution Flow**

1. Print `"Start"`.
2. `setTimeout()` moves to Web API.
3. Print `"End"`.
4. Callback enters Queue.
5. Event Loop executes callback.
6. Print `"Timeout"`.

**Key Point**

* Async code does not block synchronous code.

---

**115. Promise Chaining Output?**

**Promise Chaining** means connecting multiple `.then()` methods where each receives the result of the previous one.

**Key Features**

* Sequential execution
* Cleaner async code
* Avoids callback nesting

**Example**

```javascript
Promise.resolve(1)
  .then(value => {
    console.log(value);
    return value + 1;
  })
  .then(value => {
    console.log(value);
    return value + 1;
  })
  .then(value => {
    console.log(value);
  });
```

**Output**

```javascript
1
2
3
```

**How it Works**

1. Promise resolves with `1`.
2. First `.then()` logs `1` and returns `2`.
3. Second `.then()` logs `2` and returns `3`.
4. Third `.then()` logs `3`.

**Key Point**

* Whatever is returned from one `.then()` becomes the input of the next `.then()`.

**116. async/await vs .then()?**

Both **async/await** and **.then()** are used to handle **Promises**.

**Difference**

| async/await                      | .then()                    |
| -------------------------------- | -------------------------- |
| Looks like synchronous code      | Uses callback chaining     |
| Easier to read                   | Can become nested          |
| Uses `try...catch` for errors    | Uses `.catch()` for errors |
| Cleaner for multiple async calls | Good for simple chains     |

**Using .then()**

```javascript
fetch("/api")
  .then(res => res.json())
  .then(data => console.log(data))
  .catch(err => console.log(err));
```

**Using async/await**

```javascript
async function getData() {
  try {
    const res = await fetch("/api");
    const data = await res.json();

    console.log(data);
  } catch (err) {
    console.log(err);
  }
}
```

**Interview Answer**

* **async/await** is built on top of Promises and provides cleaner, more readable asynchronous code.

---

**117. Why can't Arrow Functions be Constructors?**

**Arrow Functions** cannot be used as constructors because they do not have their own **`this`** and **`prototype`**.

**Key Features**

* No own `this`
* No `prototype`
* Cannot be called with `new`

**Example**

```javascript
const Person = (name) => {
  this.name = name;
};

new Person("John");
```

**Output**

```javascript
TypeError: Person is not a constructor
```

**Regular Function**

```javascript
function Person(name) {
  this.name = name;
}

const p = new Person("John");
```

**Interview Answer**

* Arrow functions lack the internal constructor behavior required by the `new` keyword.

---

**118. What Happens When You Return an Object in a Constructor?**

Normally, a constructor returns the newly created object.

If you explicitly return an **object**, that object replaces the newly created instance.

**Example**

```javascript
function Person() {
  this.name = "John";

  return {
    name: "Mike"
  };
}

const p = new Person();

console.log(p.name);
```

**Output**

```javascript
Mike
```

**Returning Primitive**

```javascript
function Person() {
  this.name = "John";

  return 100;
}

const p = new Person();

console.log(p.name);
```

**Output**

```javascript
John
```

**Key Rule**

* Return **Object** → replaces instance.
* Return **Primitive** → ignored.

---

**119. How Does JavaScript Handle Memory?**

JavaScript automatically manages memory using **Memory Allocation**, **Usage**, and **Garbage Collection**.

**Key Steps**

1. **Allocate Memory**

```javascript
let name = "John";
let user = {};
```

2. **Use Memory**

```javascript
console.log(name);
```

3. **Release Memory**

* Unused objects are removed by Garbage Collection.

**Example**

```javascript
let user = {
  name: "John"
};

user = null;
```

The object becomes unreachable and can be cleaned up.

**Memory Areas**

* **Stack** → Primitive values and function calls.
* **Heap** → Objects and complex data.

**Interview Answer**

* JavaScript automatically manages memory through Garbage Collection, mainly using the **Mark and Sweep** algorithm.

---

**120. How to Polyfill bind()?**

A **Polyfill** is custom code that implements a feature if it is not available in the environment.

**Purpose of bind()**

* Creates a new function with a fixed **`this`** value.

**Native bind()**

```javascript
function greet() {
  console.log(this.name);
}

const user = { name: "John" };

const fn = greet.bind(user);

fn();
```

**Output**

```javascript
John
```

**Simple Polyfill**

```javascript
Function.prototype.myBind = function(context) {
  const fn = this;

  return function() {
    return fn.apply(context);
  };
};
```

**Usage**

```javascript
function greet() {
  console.log(this.name);
}

const user = {
  name: "John"
};

const fn = greet.myBind(user);

fn();
```

**Output**

```javascript
John
```

**How it Works**

1. Store the original function.
2. Return a new function.
3. Execute the original function using `apply()`.
4. Set `this` to the provided context.


**121. How to Polyfill Array.map?**

A **Polyfill** for `Array.map()` creates a new array by applying a callback function to each element.

**Key Features**

* Returns a new array
* Does not modify the original array
* Executes callback for each element

**Native map()**

```javascript
const nums = [1, 2, 3];

const result = nums.map(num => num * 2);

console.log(result);
```

**Output**

```javascript
[2, 4, 6]
```

**Polyfill**

```javascript
Array.prototype.myMap = function(callback) {
  const result = [];

  for (let i = 0; i < this.length; i++) {
    result.push(callback(this[i], i, this));
  }

  return result;
};
```

**Usage**

```javascript
const nums = [1, 2, 3];

const result = nums.myMap(num => num * 2);

console.log(result);
```

**Output**

```javascript
[2, 4, 6]
```

**How it Works**

1. Create an empty array.
2. Loop through each element.
3. Execute callback.
4. Store the result in a new array.
5. Return the new array.

---

**122. What is Call Stack Overflow?**

A **Call Stack Overflow** occurs when the call stack exceeds its maximum size, usually because of infinite or excessive recursion.

**Key Features**

* Happens in recursive functions
* Stack memory becomes full
* Causes program failure

**Example**

```javascript
function run() {
  run();
}

run();
```

**Output**

```javascript
RangeError: Maximum call stack size exceeded
```

**Why it Happens**

* Every function call is pushed onto the stack.
* Infinite recursion keeps adding frames.
* Stack memory eventually runs out.

**Solution**

```javascript
function run(n) {
  if (n === 0) return;

  run(n - 1);
}

run(5);
```

---

**123. What is Tail Call Optimization?**

**Tail Call Optimization (TCO)** is a technique where the JavaScript engine reuses the current function's stack frame if the recursive call is the last operation.

**Key Features**

* Reduces stack usage
* Improves recursion performance
* Prevents stack overflow

**Without TCO**

```javascript
function sum(n) {
  if (n === 0) return 0;

  return n + sum(n - 1);
}
```

**With Tail Recursion**

```javascript
function sum(n, total = 0) {
  if (n === 0) return total;

  return sum(n - 1, total + n);
}
```

**How it Works**

* Recursive call is the final statement.
* Current stack frame can be reused.

**Note**

* Modern JavaScript engines have limited support for TCO.

---

**124. Why is JavaScript Single-Threaded?**

JavaScript is **Single-Threaded** because it executes one task at a time using a single call stack.

**Key Features**

* One call stack
* Sequential execution
* Simpler memory management

**Why Was It Designed This Way?**

* Avoids race conditions.
* Makes browser programming simpler.
* Prevents multiple threads from modifying the DOM simultaneously.

**Example**

```javascript
console.log("A");
console.log("B");
console.log("C");
```

**Output**

```javascript
A
B
C
```

**How JS Handles Async Tasks**

* Uses **Web APIs**
* Uses **Callback Queue**
* Uses **Event Loop**

This provides concurrency without multiple main threads.

---

**125. Can JavaScript Be Multi-Threaded?**

**Yes**, JavaScript can perform multi-threaded work using **Web Workers** (browser) and **Worker Threads** (Node.js).

**Key Features**

* Background thread execution
* Heavy tasks run separately
* Main UI remains responsive

**Web Worker Example**

```javascript
const worker = new Worker("worker.js");

worker.postMessage("Start");
```

**worker.js**

```javascript
self.onmessage = () => {
  self.postMessage("Task Completed");
};
```

**How it Works**

1. Main thread creates a worker.
2. Worker runs in a separate thread.
3. Communication happens through messages.
4. Main thread continues running normally.

**126. What is Proxy?**

A **Proxy** is a special object that allows you to intercept and customize operations performed on another object.

**Key Features**

* Intercepts property access
* Intercepts property updates
* Adds validation and logging
* Useful for reactive programming

**How it Works**

1. Create a target object.
2. Create a proxy around it.
3. Define handlers (traps).
4. Proxy controls object operations.

**Example**

```javascript
const user = {
  name: "John"
};

const proxy = new Proxy(user, {
  get(target, prop) {
    console.log(`Getting ${prop}`);
    return target[prop];
  }
});

console.log(proxy.name);
```

**Output**

```javascript
Getting name
John
```

**Common Use Cases**

* Validation
* Logging
* Data binding
* State management

---

**127. What is Reflect?**

**Reflect** is a built-in object that provides methods for performing object operations programmatically.

**Key Features**

* Works well with Proxy
* Cleaner object manipulation
* Provides standard object operations

**How it Works**

* Similar to object operations but as methods.

**Example**

```javascript
const user = {
  name: "John"
};

console.log(
  Reflect.get(user, "name")
);
```

**Output**

```javascript
John
```

**Set Property**

```javascript
Reflect.set(user, "age", 25);

console.log(user.age);
```

**Output**

```javascript
25
```

**Common Methods**

* `Reflect.get()`
* `Reflect.set()`
* `Reflect.has()`
* `Reflect.deleteProperty()`

---

**128. What is Intl API?**

The **Intl API (Internationalization API)** provides language-sensitive formatting for dates, numbers, currencies, and more.

**Key Features**

* Date formatting
* Currency formatting
* Number formatting
* Multi-language support

**How it Works**

* Formats values based on locale settings.

**Example (Currency)**

```javascript
const amount = 1000;

console.log(
  new Intl.NumberFormat("en-US", {
    style: "currency",
    currency: "USD"
  }).format(amount)
);
```

**Output**

```javascript
$1,000.00
```

**Date Example**

```javascript
console.log(
  new Intl.DateTimeFormat("en-IN")
    .format(new Date())
);
```

**Use Cases**

* International applications
* Localization
* Currency display
* Date formatting

---

**129. What is BigInt?**

**BigInt** is a numeric type used to represent integers larger than JavaScript's safe integer limit.

**Key Features**

* Handles very large integers
* Avoids precision loss
* Introduced in ES2020

**Problem**

```javascript
console.log(
  Number.MAX_SAFE_INTEGER
);
```

**Output**

```javascript
9007199254740991
```

Numbers beyond this may lose precision.

**BigInt Example**

```javascript
const big =
  90071992547409999999n;

console.log(big);
```

**Output**

```javascript
90071992547409999999n
```

**Another Way**

```javascript
const big = BigInt(
  "90071992547409999999"
);
```

**Use Cases**

* Financial calculations
* Scientific computations
* Large identifiers

---

**130. What is Temporal API?**

The **Temporal API** is a modern JavaScript API for working with **dates and times** more accurately than the traditional `Date` object.

**Key Features**

* Better date handling
* Time zone support
* Immutable objects
* Easier date calculations

**Why Needed?**
The traditional `Date` API has many limitations and timezone-related issues.

**Example**

```javascript
const date =
  Temporal.PlainDate.from(
    "2026-01-15"
  );

console.log(date.year);
```

**Output**

```javascript
2026
```

**Current Date-Time**

```javascript
const now =
  Temporal.Now.plainDateISO();

console.log(now);
```

**Benefits**

* Predictable behavior
* Easier date arithmetic
* Better timezone handling
* Cleaner API than `Date`

**131. What is Shadow DOM?**

**Shadow DOM** is a browser feature that creates an isolated DOM tree inside an element, preventing styles and code from affecting the outside DOM.

**Key Features**

* DOM encapsulation
* Style isolation
* Reusable components
* Part of Web Components

**How it Works**

1. Create a shadow root.
2. Add HTML and CSS inside it.
3. Content remains isolated from the main DOM.

**Example**

```javascript
const element = document.getElementById("box");

const shadow = element.attachShadow({
  mode: "open"
});

shadow.innerHTML = `
  <style>
    p { color: red; }
  </style>
  <p>Hello Shadow DOM</p>
`;
```

**Use Cases**

* Custom elements
* Reusable UI components
* Design systems

---

**132. What is Hydration?**

**Hydration** is the process of attaching JavaScript behavior to HTML that was already rendered on the server.

**Key Features**

* Used with SSR
* Makes static HTML interactive
* Improves initial page load

**How it Works**

1. Server sends pre-rendered HTML.
2. Browser displays content immediately.
3. JavaScript loads.
4. Event handlers are attached.

**Example**

```html
<button>Click Me</button>
```

After hydration:

```javascript
button.addEventListener("click", () => {
  alert("Clicked");
});
```

**Benefits**

* Faster first render
* Better SEO
* Improved user experience

---

**133. CSR vs SSR?**

**CSR (Client-Side Rendering)**

**Key Features**

* Rendering happens in the browser
* Requires JavaScript
* Slower first load
* Faster page navigation after load

**Example**

```javascript
fetch("/api")
  .then(res => res.json())
  .then(data => render(data));
```

**SSR (Server-Side Rendering)**

**Key Features**

* Rendering happens on the server
* HTML is sent ready to display
* Faster initial load
* Better SEO

**Comparison**

| Feature               | CSR           | SSR    |
| --------------------- | ------------- | ------ |
| Rendering             | Browser       | Server |
| Initial Load          | Slower        | Faster |
| SEO                   | Less Friendly | Better |
| JavaScript Dependency | High          | Lower  |

**Interview Answer**

* **CSR** renders pages in the browser, while **SSR** renders pages on the server before sending them to the client.

---

**134. What is a Module Bundler?**

A **Module Bundler** is a tool that combines multiple JavaScript files and assets into optimized bundles.

**Key Features**

* Combines modules
* Optimizes code
* Reduces HTTP requests
* Supports modern JavaScript

**Popular Bundlers**

* Webpack
* Vite
* Parcel
* Rollup

**How it Works**

1. Reads project files.
2. Resolves dependencies.
3. Creates optimized bundles.
4. Outputs production-ready files.

**Example**

```javascript
import add from "./math.js";

console.log(add(2, 3));
```

The bundler combines all required files into a single optimized bundle.

**Benefits**

* Faster loading
* Code splitting
* Tree shaking
* Better performance

---

**135. What is V8 Engine?**

**V8** is Google's high-performance JavaScript engine used in **Google Chrome** and **Node.js**.

**Key Features**

* Executes JavaScript code
* Uses Just-In-Time (JIT) compilation
* Fast performance
* Handles memory management

**How it Works**

1. Reads JavaScript code.
2. Parses code into an Abstract Syntax Tree (AST).
3. Compiles code into machine code.
4. Executes the machine code.

**Example**

```javascript
console.log("Hello World");
```

The V8 engine converts this JavaScript into machine code and executes it.

**Main Components**

* Parser
* Compiler
* Optimizer
* Garbage Collector

**136. How JS Code is Executed Internally?**

JavaScript code goes through several steps before execution.

**Key Steps**

1. **Parsing**
2. **Compilation**
3. **Execution**

**How it Works**

1. JavaScript source code is read.
2. Parser checks syntax.
3. An **AST (Abstract Syntax Tree)** is created.
4. Engine compiles code into machine code.
5. Code executes in the **Call Stack**.

**Example**

```javascript
let a = 10;
let b = 20;

console.log(a + b);
```

**Execution Flow**

```text
Source Code
     ↓
Parser
     ↓
AST
     ↓
Compiler
     ↓
Machine Code
     ↓
Execution
```

**Interview Answer**

* JavaScript code is parsed, compiled into machine code, and then executed by the JavaScript engine.

---

**137. Parsing vs Compiling?**

**Parsing** and **Compiling** are two different stages of code execution.

| Parsing             | Compiling                     |
| ------------------- | ----------------------------- |
| Checks syntax       | Converts code to machine code |
| Creates AST         | Produces executable code      |
| Finds syntax errors | Optimizes code                |
| First step          | Second step                   |

**Parsing Example**

```javascript
let x = ;
```

**Result**

```text
Syntax Error
```

The parser detects the invalid syntax.

**Compiling Example**

```javascript
let x = 10;

console.log(x);
```

The compiler converts this into machine code for execution.

**Interview Answer**

* **Parsing** checks and understands the code structure, while **Compiling** converts it into executable machine code.

---

**138. What is JIT Compilation?**

**JIT (Just-In-Time) Compilation** is a technique where JavaScript is compiled into machine code during execution.

**Key Features**

* Faster execution
* Runtime optimization
* Used by modern JS engines
* Improves performance

**How it Works**

1. Code is parsed.
2. Initially compiled.
3. Frequently executed code is identified.
4. Engine generates optimized machine code.

**Example**

```javascript
function add(a, b) {
  return a + b;
}

add(1, 2);
add(3, 4);
add(5, 6);
```

The engine notices repeated execution and optimizes the function.

**Benefits**

* Faster execution
* Better performance
* Runtime optimization

**Interview Answer**

* JIT compilation converts JavaScript into optimized machine code while the program is running.

---

**139. What are Hidden Classes?**

**Hidden Classes** are internal structures used by JavaScript engines to optimize object property access.

**Key Features**

* Internal engine optimization
* Faster property lookup
* Used by V8
* Improves performance

**Example**

```javascript
const user1 = {
  name: "John",
  age: 25
};

const user2 = {
  name: "Mike",
  age: 30
};
```

Since both objects have the same property structure, the engine can use the same hidden class.

**Different Structure**

```javascript
const user3 = {
  age: 25,
  name: "John"
};
```

A different property order may create a different hidden class.

**Best Practice**

* Create objects with consistent property order.

**Interview Answer**

* Hidden classes help JavaScript engines optimize object access by grouping objects with the same structure.

---

**140. What is Inline Caching?**

**Inline Caching (IC)** is an optimization technique that speeds up repeated property access and method calls.

**Key Features**

* Faster property lookup
* Works with hidden classes
* Improves execution speed
* Used by modern JS engines

**How it Works**

1. Property is accessed for the first time.
2. Engine remembers where the property exists.
3. Future accesses use the cached location.
4. Lookup becomes much faster.

**Example**

```javascript
const user = {
  name: "John"
};

console.log(user.name);
console.log(user.name);
console.log(user.name);
```

The engine caches the location of `name` after the first lookup.

**Benefits**

* Reduced lookup time
* Faster object access
* Better overall performance


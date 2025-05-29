### **1. Basic JavaScript Concepts**

#### 1. **What are the different data types in JavaScript?**
   JavaScript has both **primitive** and **non-primitive** (or **reference**) data types:
   - **Primitive data types**: 
     - `string`: Represents a sequence of characters. Example: `"hello"`
     - `number`: Represents numeric values. Example: `42`
     - `bigint`: Represents large integers. Example: `123456789012345678901234567890n`
     - `boolean`: Represents `true` or `false`. Example: `true`
     - `undefined`: Represents an uninitialized variable or an absent value. Example: `let a;`
     - `symbol`: Represents a unique identifier, often used for object property keys. Example: `Symbol('description')`
     - `null`: Represents the intentional absence of any object value. Example: `let a = null;`
   - **Non-primitive data types**:
     - `object`: Includes collections of key-value pairs, such as arrays, functions, and plain objects. Example: `let obj = { key: 'value' };`
     - `Array`:
     - `Function`:

#### 2. **What is the difference between `var`, `let`, and `const`?**
   - **`var`**:
     - Declares variables with function or global scope (depending on where it's declared).
     - Variables are **hoisted** (the declaration is moved to the top of its scope).
     - Can be reassigned and redeclared.
   - **`let`**:
     - Declares block-scoped variables (limited to the block, statement, or expression in which it is used).
     - Cannot be redeclared in the same scope, but can be reassigned.
     - **Not hoisted** in the same way as `var` (it’s in a "temporal dead zone" from the start of the block until the declaration is encountered).
   - **`const`**:
     - Declares block-scoped variables.
     - **Cannot be reassigned** after initial assignment.
     - Its value must be assigned at declaration time.

#### 3. **What is a closure in JavaScript?**
   A **closure** is a function that **retains access** to variables from its lexical scope, even after the outer function has finished execution. This happens because the inner function "remembers" the environment in which it was created.
   Example:
   ```javascript
   function outer() {
     let count = 0;
     function inner() {
       return count++;
     }
     return inner;
   }
   const increment = outer();
   console.log(increment()); // 0
   console.log(increment()); // 1
   ```


#### 6. **What is the difference between `==` and `===` in JavaScript?**
   - **`==` (loose equality)**: Compares values for equality **after type coercion** (converts values to the same type before comparison).
   - **`===` (strict equality)**: Compares values for equality **without type coercion** (both the value and the type must be the same).

   Example:
   ```javascript
   5 == '5';  // true (because '5' is converted to number)
   5 === '5'; // false (different types: number vs string)
   ```


### 11. **Explain the concept of “truthy” and “falsy” values in JavaScript.**

In JavaScript, values are evaluated as either **truthy** or **falsy** when they are used in a boolean context (such as an `if` statement). These values determine the flow of control based on their evaluation.

- **Falsy values** are values that are considered false when encountered in a boolean context. There are only 6 falsy values in JavaScript:
  - `false`
  - `0` (zero)
  - `""` (empty string)
  - `null`
  - `undefined`
  - `NaN`

- **Truthy values** are values that are considered true in a boolean context. Essentially, any value that is not falsy is truthy. For example:
  - `"hello"` (non-empty string)
  - `42` (any non-zero number)
  - `[]` (empty array)
  - `{}` (empty object)

Example:
```javascript
if (0) {
  console.log("This will not run");
}

if ("Hello") {
  console.log("This will run"); // "Hello" is truthy
}
```

#### 7. **What are the JavaScript data structures, and when would you use them?**
   - **Arrays**: Ordered collections, used when the order of elements matters.
   - **Objects**: Key-value pairs, used to represent a collection of related data.
   - **Sets**: A collection of unique values (no duplicates).
   - **Maps**: A collection of key-value pairs where keys can be any type, useful for storing and retrieving values based on custom keys.

#### 8. **What is the `undefined` value in JavaScript?**
   `undefined` is a primitive value in JavaScript that indicates the absence of a value. It is automatically assigned to variables that are declared but not initialized or to function parameters that are not passed any arguments.
   Example:
   ```javascript
   let a;
   console.log(a); // undefined
   ```

#### 9. **What is the `null` value in JavaScript? How is it different from `undefined`?**
   `null` is a special value in JavaScript that represents the intentional absence of any object or value. It is explicitly assigned to variables to indicate that they have no value.
   - **`undefined`** is the default value when a variable is declared but not initialized.
   - **`null`** is assigned to indicate the absence of a value intentionally.

   Example:
   ```javascript
   let a = null;    // Explicitly assigned
   let b;           // Implicitly undefined
   ```

### 9. **What are the different ways to create objects in JavaScript?**

There are several ways to create objects in JavaScript:
1. **Object literal**:
   ```javascript
   const obj = { name: 'Alice', age: 25 };
   ```

2. **Using `new Object()`**:
   ```javascript
   const obj = new Object();
   obj.name = 'Alice';
   obj.age = 25;
   ```

3. **Using `Object.create()`**:
   ```javascript
   const obj = Object.create(null);
   obj.name = 'Alice';
   obj.age = 25;
   ```

4. **Using a constructor function**:
   ```javascript
   function Person(name, age) {
     this.name = name;
     this.age = age;
   }
   const person1 = new Person('Alice', 25);
   ```

5. **Using a class (ES6)**:
   ```javascript
   class Person {
     constructor(name, age) {
       this.name = name;
       this.age = age;
     }
   }
   const person1 = new Person('Alice', 25);
   ```

#### 10. **What is hoisting in JavaScript?**
   Hoisting is a JavaScript behavior where **declarations** (but not initializations) of variables and functions are moved to the top of their containing scope during the compilation phase.
   - **Function declarations** are hoisted with their definitions.
   - **`var` variables** are hoisted, but only their declarations, not their assignments.
   - **`let` and `const`** are hoisted, but they are not initialized until the code execution reaches the declaration line. Accessing them before that results in a ReferenceError (due to the "temporal dead zone").

   Example:
   ```javascript
   console.log(a); // undefined (hoisted but not assigned)
   var a = 5;

   myFunction();   // works because function declarations are hoisted
   function myFunction() {
     console.log('Hello');
   }
   ```

### 86. **What is a pure function in JavaScript? Can you give an example?**

A **pure function** is a function that:
1. Always produces the same output for the same input.
2. Has no side effects (it does not modify external state).

**Example**:

```javascript
function add(a, b) {
  return a + b; // Always returns the same result for the same inputs
}
```

Here, the `add()` function is pure because it always returns the same result and does not modify any external variables.

### **37. What are higher-order functions in JavaScript?**
  Higher-order function is a function that accepts another function as an argument or returns a function as a return value or both.

   A **higher-order function** is a function that:
   - Takes one or more functions as arguments.
   - Returns a function as a result.

   Higher-order functions are a key feature in functional programming. They allow you to abstract operations and create more flexible, reusable code.

   Example:
   ```javascript
    function multiplier(factor) {
      return function(number) {
        return number * factor;
      };
    }
    const double = multiplier(2);
    console.log(double(5)); // 10
   ```

### **39. What is a callback function in JavaScript?**
   A **callback function** is a function passed as an argument to another function, which is then executed after some operation (e.g., asynchronous code) is completed.

   Example:
   ```javascript
   function fetchData(callback) {
     setTimeout(() => {
       console.log('Data fetched');
       callback(); // Calling the callback function
     }, 1000);
   }

   fetchData(function() {
     console.log('Callback executed');
   });
   ```

   In this example, `fetchData` accepts a callback function that is executed once the data fetching operation completes.

### 74. **What are closures in JavaScript, and why are they important?**

  A **closure** is a function that retains access to its lexical scope, even when the function is executed outside of that scope. In other words, closures allow a function to "remember" its environment.

  Closures are important for:
  - **Data encapsulation**: Protecting data from being directly accessed or modified by the outside code.
  - **Function factories**: Generating functions dynamically with specific states.
    
  **Example:**

  ```javascript
  function outer() {
    let count = 0;
    return function inner() {
      count++;
      console.log(count);
    };
  }

  const increment = outer();
  increment(); // 1
  increment(); // 2
  ```

  Here, `inner` has access to `count` even after `outer` has finished execution, demonstrating closure.


### 12. **How does the `this` keyword work in JavaScript?**

In JavaScript, the **`this`** keyword refers to the context in which a function is called. The value of `this` depends on how a function is invoked:

- **In a regular function**: `this` refers to the global object (`window` in browsers) in non-strict mode, or `undefined` in strict mode.
  
- **In a method**: If the function is a method of an object, `this` refers to the object that called the method.

- **In an arrow function**: `this` is lexically inherited from the surrounding context (it does not have its own `this`).

- **With `call()`, `apply()`, or `bind()`**: You can explicitly set the value of `this`.

  Example:
  ```javascript
  const person = {
    name: 'Alice',
    greet: function() {
      console.log(this.name);
    }
  };

  person.greet(); // "Alice", 'this' refers to the 'person' object
  ```
### 89. **What is the difference between synchronous and asynchronous code in JavaScript?**

- **Synchronous code**: Executes line by line. Each operation waits for the previous one to finish before continuing, blocking further code execution.

  **Example**:
  
  ```javascript
  console.log('Start');
  console.log('End');
  ```

  Both `console.log()` statements are executed one after the other.

- **Asynchronous code**: Allows operations to run in the background without blocking the main execution thread. It continues executing other code while waiting for the asynchronous operation (like fetching data, setTimeout) to complete.

  **Example**:

  ```javascript
  console.log('Start');
  setTimeout(() => {
    console.log('Middle');
  }, 1000);
  console.log('End');
  ```

  Output: 
  ```
  Start
  End
  Middle
  ```

  Here, `setTimeout` runs asynchronously, and the `console.log('End')` statement executes before the `setTimeout` callback.

  
### Key Differences:

| **Aspect**               | **Synchronous**                             | **Asynchronous**                              |
|--------------------------|---------------------------------------------|-----------------------------------------------|
| **Execution Flow**        | Tasks are executed one after another.      | Tasks can be executed concurrently.          |
| **Blocking**              | Blocks the program until the task finishes. | Does not block the program; can continue with other tasks. |
| **Complexity**            | Easier to understand and implement.        | More complex but more efficient for I/O-bound tasks. |
| **Efficiency**            | Not efficient for tasks that take time (e.g., I/O operations). | More efficient for handling many I/O-bound tasks concurrently. |
| **Common Use Cases**      | Simple tasks where timing is not critical. | Web servers, network requests, background jobs, etc. |
| **Example**               | A function waits for a file read to finish before continuing. | A function initiates a file read but continues doing other work until the file is read. |



### **17. What is the purpose of the `call()`,  `apply()` and `bind()` methods in JavaScript?**
   Both `call()` and `apply()` are used to invoke a function with a specific `this` context and pass arguments to it. The primary difference between them is how arguments are passed:

  The difference between Call, Apply and Bind can be explained with below examples,

   **Call:** The call() method invokes a function with a given `this` value and arguments provided one by one

   ```javascript
   var employee1 = { firstName: "John", lastName: "Rodson" };
   var employee2 = { firstName: "Jimmy", lastName: "Baily" };

   function invite(greeting1, greeting2) {
     console.log(
       greeting1 + " " + this.firstName + " " + this.lastName + ", " + greeting2
     );
   }

   invite.call(employee1, "Hello", "How are you?"); // Hello John Rodson, How are you?
   invite.call(employee2, "Hello", "How are you?"); // Hello Jimmy Baily, How are you?
   ```

   **Apply:** Invokes the function with a given `this` value and allows you to pass in arguments as an array

   ```javascript
   var employee1 = { firstName: "John", lastName: "Rodson" };
   var employee2 = { firstName: "Jimmy", lastName: "Baily" };

   function invite(greeting1, greeting2) {
     console.log(
       greeting1 + " " + this.firstName + " " + this.lastName + ", " + greeting2
     );
   }

   invite.apply(employee1, ["Hello", "How are you?"]); // Hello John Rodson, How are you?
   invite.apply(employee2, ["Hello", "How are you?"]); // Hello Jimmy Baily, How are you?
   ```

   **bind:** returns a new function, allowing you to pass any number of arguments

   ```javascript
   var employee1 = { firstName: "John", lastName: "Rodson" };
   var employee2 = { firstName: "Jimmy", lastName: "Baily" };

   function invite(greeting1, greeting2) {
     console.log(
       greeting1 + " " + this.firstName + " " + this.lastName + ", " + greeting2
     );
   }

   var inviteEmployee1 = invite.bind(employee1);
   var inviteEmployee2 = invite.bind(employee2);
   inviteEmployee1("Hello", "How are you?"); // Hello John Rodson, How are you?
   inviteEmployee2("Hello", "How are you?"); // Hello Jimmy Baily, How are you?
   ```

   Call and apply are pretty interchangeable. Both execute the current function immediately. You need to decide whether it’s easier to send in an array or a comma separated list of arguments. You can remember by treating Call is for **comma** (separated list) and Apply is for **Array**.

   Whereas Bind creates a new function that will have `this` set to the first parameter passed to bind().

   
### **45. What is `async/await` in JavaScript?**
  An async function is a function declared with the `async` keyword which enables asynchronous, promise-based behavior to be written in a cleaner style by avoiding promise chains. These functions can contain zero or more `await` expressions.

   **`async`/`await`** are syntax improvements in JavaScript to work with promises in a cleaner and more readable way:
   - **`async`** is used to define a function that returns a promise.
   - **`await`** can be used inside an `async` function to pause execution until the promise is resolved.

   Example:
   ```javascript
   async function fetchData() {
     let data = await new Promise(resolve => setTimeout(() => resolve('Data fetched'), 1000));
     console.log(data);
   }
   fetchData(); // Output: Data fetched
   ```

   With `async/await`, you can handle asynchronous operations in a synchronous-like manner, avoiding the need for chaining `.then()` and `.catch()`.


### **42. What is a promise in JavaScript? How does it work?**
   A **Promise** in JavaScript is an object representing the eventual completion (or failure) of an asynchronous operation and its resulting value. It is used to handle asynchronous operations and avoid callback hell.

   A promise can be in one of the following states:
   - **Pending**: The promise is still being processed.
   - **Resolved** (Fulfilled): The operation was successful, and the promise has a result.
   - **Rejected**: The operation failed, and the promise has an error.

   Example:
   ```javascript
   let promise = new Promise((resolve, reject) => {
     let success = true;
     if (success) {
       resolve('Operation was successful');
     } else {
       reject('Operation failed');
     }
   });

   promise.then(result => console.log(result))
          .catch(error => console.log(error));
   ```

   The promise is initially in the "pending" state, and depending on the outcome, it either resolves or rejects.


### 41. **What is a promise chain in JavaScript?**

A **promise chain** is a sequence of `.then()` or `.catch()` methods chained together, allowing for a series of asynchronous operations to be executed in order. Each `.then()` receives the result of the previous promise.

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
Here, `fetchData` returns a promise, which is passed to the first `.then()`. The subsequent `.then()` methods chain the results of the previous promises.

### **43. What are the states of a promise?**
   A promise has three states:
   1. **Pending**: The promise is neither resolved nor rejected; it is still in progress.
   2. **Fulfilled** (Resolved): The asynchronous operation has completed successfully, and the promise now has a result.
   3. **Rejected**: The asynchronous operation has failed, and the promise is rejected with an error or failure reason.

   The state transitions occur like this:
   - A promise starts in the `pending` state.
   - It can transition to `fulfilled` if the operation completes successfully.
   - It can transition to `rejected` if the operation encounters an error.

### **46. What is the difference between `async/await` and promises?**
   - **Promises** are the core mechanism for handling asynchronous operations, and you can chain `.then()` and `.catch()` for handling the result or error.
   - **`async/await`** is a syntactical sugar over promises. It makes asynchronous code look synchronous, making it easier to read and write.

   **Key differences**:
   - `async/await` makes asynchronous code look more like synchronous code, improving readability.
   - Promises involve chaining `.then()` for success and `.catch()` for error handling.
   - `async/await` avoids callback-style nesting and makes error handling easier with `try/catch`.

   Example using promises:
   ```javascript
   getData()
     .then(result => console.log(result))
     .catch(error => console.log(error));
   ```

   Example using async/await:
   ```javascript
   try {
     const result = await getData();
     console.log(result);
   } catch (error) {
     console.log(error);
   }
   ```

### **11. Differenct between callback and promise**
The difference between a **callback** and a **promise** in JavaScript lies primarily in how they handle asynchronous operations, their structure, and how they deal with results or errors.

### 1. **Callback**
A callback is a function that is passed as an argument to another function and is executed once the asynchronous operation is complete.

#### Characteristics of Callbacks:
- **Execution**: A callback is executed directly when the asynchronous task finishes. 
- **Error Handling**: Callbacks typically follow an **error-first pattern**, where the first argument is an error (if any) and the second argument is the result.
- **Nested Callbacks**: Callbacks can be nested, but this often leads to what is called **callback hell**, where multiple nested functions can make the code hard to read and maintain.
  
#### Example of Callback:
```javascript
function fetchData(callback) {
  setTimeout(() => {
    const data = { message: "Hello, world!" };
    callback(null, data);  // No error, just data
  }, 1000);
}

fetchData(function (error, result) {
  if (error) {
    console.error(error);
  } else {
    console.log(result);
  }
});
```

### 2. **Promise**
A promise is a more structured and modern way to handle asynchronous operations. It represents a value that might be available now, or in the future, or never (rejected).

#### Characteristics of Promises:
- **States**: A promise can be in one of three states:
  1. **Pending**: The operation is still in progress.
  2. **Fulfilled**: The operation completed successfully.
  3. **Rejected**: The operation failed (error).
  
- **Chaining**: Promises allow chaining of `.then()` for success and `.catch()` for error handling. This avoids the nested structure of callbacks and is easier to read and maintain.
  
- **Error Handling**: With promises, errors are propagated through the `.catch()` method, making it cleaner and more consistent to handle errors.
  
#### Example of a Promise:
```javascript
function fetchData() {
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      const data = { message: "Hello, world!" };
      resolve(data);  // Successful resolution
      // reject("Error occurred"); // If there was an error
    }, 1000);
  });
}

fetchData()
  .then((result) => {
    console.log(result);  // Handles success
  })
  .catch((error) => {
    console.error(error);  // Handles errors
  });
```

### Key Differences:

| Feature              | Callback                               | Promise                                    |
|----------------------|----------------------------------------|--------------------------------------------|
| **Handling Flow**     | Nested functions (callback hell)       | Chainable with `.then()` and `.catch()`    |
| **Error Handling**    | Error-first pattern (often manually)   | Uses `.catch()` for error handling         |
| **State Management**  | No explicit state management           | Has states: Pending, Fulfilled, Rejected  |
| **Code Readability**  | Can become hard to read with nesting   | More readable with chaining and `.catch()` |
| **Multiple Async Ops**| Difficult to manage multiple async ops | Can easily handle multiple async ops with `.all()` or `async/await` |
| **Use Case**          | Simple, small asynchronous tasks       | Complex async tasks or chains of async ops |

### Modern Alternative: **Async/Await**
`async/await` is built on top of promises and allows you to write asynchronous code that looks synchronous, making it easier to read and maintain.

#### Example of Async/Await:
```javascript
async function fetchData() {
  const result = await someAsyncOperation();
  console.log(result);
}
```

07. ### What is an observable

     An Observable is basically a function that can return a stream of values either synchronously or asynchronously to an observer over time. The consumer can get the value by calling `subscribe()` method.
     Let's look at a simple example of an Observable

     ```javascript
     import { Observable } from "rxjs";

     const observable = new Observable((observer) => {
       setTimeout(() => {
         observer.next("Message from a Observable!");
       }, 3000);
     });

     observable.subscribe((value) => console.log(value));
     ```

     ![Screenshot](images/observables.png)

     **Note:** Observables are not part of the JavaScript language yet but they are being proposed to be added to the language

08. ### What are the differences between promises and observables

     Some of the major difference in a tabular form

     | Promises                                                           | Observables                                                                              |
     | ------------------------------------------------------------------ | ---------------------------------------------------------------------------------------- |
     | Emits only a single value at a time                                | Emits multiple values over a period of time(stream of values ranging from 0 to multiple) |
     | Eager in nature; they are going to be called immediately           | Lazy in nature; they require subscription to be invoked                                  |
     | Promise is always asynchronous even though it resolved immediately | Observable can be either synchronous or asynchronous                                     |
     | Doesn't provide any operators                                      | Provides operators such as map, forEach, filter, reduce, retry, and retryWhen etc        |
     | Cannot be canceled                                                 | Canceled by using unsubscribe() method                                                   |

08. ### What are the differences between Callback Functions and Higher-Order Components

### Key Differences:
| Feature               | Callback Functions                          | Higher-Order Components (HOCs)                |
|-----------------------|---------------------------------------------|----------------------------------------------|
| **Definition**         | A function passed as an argument to be executed later. | A function that takes a component and returns a new component with additional behavior. |
| **Context**            | General JavaScript, often used for async operations (e.g., event handlers, APIs). | Primarily used in React to enhance or modify components. |
| **Purpose**            | To handle asynchronous operations or custom actions after a task is complete. | To add reusable functionality to components without changing the component itself. |
| **Return Value**       | Returns nothing directly; it executes some code when called. | Returns a new component, often with added props or logic. |
| **Usage**              | Used in async operations (e.g., `setTimeout`, API calls). | Used in React to modify or augment component behavior. |

### 70. **What is the concept of currying in JavaScript? Can you provide an example?**

**Currying** is a technique in functional programming where a function that takes multiple arguments is transformed into a sequence of functions, each taking one argument.

In simpler terms, currying allows you to break a function that takes multiple parameters into a series of functions that each take one parameter and return a new function until all parameters are provided.

**Example:**

```javascript
function multiply(a) {
  return function(b) {
    return a * b;
  };
}

const multiplyBy2 = multiply(2);
console.log(multiplyBy2(3)); // 6
```

Here, `multiply` is a curried function that first takes `a`, then returns a new function that takes `b` and performs the multiplication.   

214. ### What is an anonymous function

     An anonymous function is a function without a name! Anonymous functions are commonly assigned to a variable name or used as a callback function. The syntax would be as below,

     ```javascript
     function (optionalParameters) {
       //do something
     }

     const myFunction = function(){ //Anonymous function assigned to a variable
       //do something
     };

     [1, 2, 3].map(function(element){ //Anonymous function used as a callback function
       //do something
     });
     ```

     Let's see the above anonymous function in an example,

     ```javascript
     var x = function (a, b) {
       return a * b;
     };
     var z = x(5, 10);
     console.log(z); // 50
     ```



### **11. Explain event delegation in JavaScript.**
   **Event delegation** is a technique where you attach a single event listener to a parent element, and the event is triggered by the child elements that match a specified selector. This method relies on the event bubbling process, where an event that occurs on a child element bubbles up to its parent elements. It improves performance, especially when you have a large number of child elements, by reducing the number of event listeners attached.

   Example:
   ```javascript
   document.getElementById('parent').addEventListener('click', function(event) {
     if (event.target && event.target.matches('button.classname')) {
       console.log('Button clicked:', event.target);
     }
   });
   ```

   In this example, even though multiple buttons may be added dynamically, only one event listener is attached to the parent element (`#parent`).

### **41. What is the event loop in JavaScript?**
   The **event loop** is a fundamental part of JavaScript's concurrency model. It allows JavaScript to perform non-blocking operations, even though JavaScript is single-threaded. The event loop continuously checks the call stack and the message queue (also called the event queue).
   
   - If the call stack is empty, it pushes the first task in the event queue to the call stack and executes it.
   - The event loop allows JavaScript to handle asynchronous operations (like I/O tasks, setTimeout, and network requests) without blocking the main execution thread.

   Example:
   ```javascript
   console.log('Start');
   setTimeout(() => console.log('Inside Timeout'), 0);
   console.log('End');
   // Output: Start, End, Inside Timeout
   ```

   Here, the `setTimeout` function doesn't block the main thread; the event loop ensures that the timeout callback executes after the current call stack is empty.

   
### **73. What is event propagation in JavaScript?**
   **Event propagation** refers to the way events move through the DOM when they are triggered. It happens in two phases: **bubbling** and **capturing**.

   - **Capturing phase**: The event starts at the root of the DOM and travels down to the target element.
   - **Bubbling phase**: The event starts from the target element and bubbles up to the root.

   The default behavior is event bubbling, where events trigger handlers in the order from the target element to the root.

### **74. What is event bubbling and capturing in JavaScript?**
   - **Event Bubbling**: In this phase, when an event is triggered on an element, it first triggers the event handler of that element, then propagates up to its parent elements, and continues up to the root.
   
     Example:
     ```javascript
     document.getElementById('parent').addEventListener('click', () => {
       console.log('Parent clicked');
     });
     document.getElementById('child').addEventListener('click', () => {
       console.log('Child clicked');
     });
     // Clicking on #child will trigger both the child and parent events (bubbling).
     ```
   
   - **Event Capturing**: The event starts from the root of the DOM and propagates down to the target element. This phase can be used by specifying the third argument as `true` in `addEventListener()`.

     Example:
     ```javascript
     document.getElementById('parent').addEventListener('click', () => {
       console.log('Parent clicked');
     }, true); // Capturing phase
     ```

### 50. **How do you prevent the default action of an event in JavaScript?**

To prevent the default behavior associated with an event (e.g., submitting a form or following a link), you use the `preventDefault()` method.

Example:
```javascript
document.getElementById("myLink").addEventListener("click", function(event) {
  event.preventDefault(); // Prevents the link from navigating
  console.log("Link clicked but not navigated.");
});
```

This prevents the default behavior (in this case, navigating to the URL) from occurring when the event is triggered.

---

### 51. **How do you attach multiple event listeners to the same event?**

You can attach multiple event listeners to the same event by calling `addEventListener` multiple times for the same event on the same element.

Example:
```javascript
const button = document.getElementById("myButton");

button.addEventListener("click", function() {
  console.log("First event listener");
});

button.addEventListener("click", function() {
  console.log("Second event listener");
});
```

Both event listeners will be executed when the button is clicked.


### 71. **What is the concept of memoization in JavaScript?**

**Memoization** is an optimization technique where the results of expensive function calls are cached, and when the same inputs occur again, the cached result is returned instead of recalculating it. This can greatly improve performance for functions that are called repeatedly with the same arguments.

**Example:**

```javascript
function memoize(fn) {
  const cache = {};
  return function(arg) {
    if (cache[arg]) {
      return cache[arg];
    }
    const result = fn(arg);
    cache[arg] = result;
    return result;
  };
}

const square = memoize(function(n) {
  console.log('Calculating...');
  return n * n;
});

console.log(square(4)); // Calculating... 16
console.log(square(4)); // 16 (cached)
```

Here, the `memoize` function caches results, so the second time `square(4)` is called, it returns the cached result without recalculating.

### **85. What is debounce and throttle in JavaScript?**
   **Debounce** and **throttle** are techniques used to optimize performance for frequently occurring events like scrolling, resizing, or keypresses.

   - **Debounce**: Limits the rate at which a function is invoked. It ensures the function is only called once after a specified delay, triggered by the end of a series of rapid events. It’s useful for search input or resizing events where you don't want to trigger the action every time an event occurs.

     Example (Debouncing a keypress event):
     ```javascript
     function debounce(func, delay) {
       let timeout;
       return function() {
         clearTimeout(timeout);
         timeout = setTimeout(() => func.apply(this, arguments), delay);
       };
     }

     const searchInput = document.getElementById('search');
     searchInput.addEventListener('input', debounce(function() {
       console.log('Search query:', this.value);
     }, 300));
     ```

   - **Throttle**: Ensures a function is called at most once every specified interval, regardless of how many times the event is triggered. It’s useful for events like scrolling or window resizing where you don't want to perform the action too frequently.

     Example (Throttling a scroll event):
     ```javascript
     function throttle(func, limit) {
       let lastFunc;
       let lastTime;
       return function() {
         const now = new Date();
         if (!lastTime || now - lastTime >= limit) {
           func.apply(this, arguments);
           lastTime = now;
         } else {
           clearTimeout(lastFunc);
           lastFunc = setTimeout(() => {
             func.apply(this, arguments);
             lastTime = now;
           }, limit - (now - lastTime));
         }
       };
     }

     window.addEventListener('scroll', throttle(() => {
       console.log('Scrolling...');
     }, 1000));
     ```

   **Key Difference**:
   - **Debounce** delays the execution of a function until after a certain amount of idle time has passed (ideal for scenarios like searching).
   - **Throttle** ensures that the function is called at regular intervals (ideal for scenarios like scrolling or resizing).


### **12. What are Object-Oriented Programming (OOP) in javascript**
**Object-Oriented Programming (OOP) in JavaScript** is a programming paradigm that uses objects and classes to organize code into reusable, modular units. JavaScript supports OOP concepts like encapsulation, inheritance, and polymorphism, but it differs from traditional OOP languages like Java or C++ because it is prototype-based, rather than class-based (though with ES6, it introduced class syntax for easier readability).

- **Encapsulation:** Wrapping data and methods into a single unit (class) and restricting access to some components using access modifiers.
- **Inheritance:** The ability of a class to inherit the properties and behaviors of another class.
- **Polymorphism:** Allowing one entity to take multiple forms (e.g., method overloading, method overriding).
- **Abstraction:** Hiding the implementation details and showing only the necessary functionality to the user.

Here’s a breakdown of the key concepts and how they are implemented in JavaScript:

### 1. **Classes and Objects**
A **class** is a blueprint for creating objects. It defines the properties and methods that an object of that class will have. In JavaScript, classes are a newer addition (introduced in ES6), but you can still use the older prototype-based approach.

#### Example: Class and Object in JavaScript

```javascript
// ES6 Class Syntax
class Animal {
  constructor(name, sound) {
    this.name = name;
    this.sound = sound;
  }

  makeSound() {
    console.log(`${this.name} says ${this.sound}`);
  }
}

// Creating an object from the class
const dog = new Animal('Dog', 'Woof');
dog.makeSound();  // Output: Dog says Woof

const cat = new Animal('Cat', 'Meow');
cat.makeSound();  // Output: Cat says Meow
```

- **Constructor**: The `constructor` method is a special method used for initializing new objects.
- **Method**: `makeSound` is a method that is available to every object created from the `Animal` class.

### 2. **Encapsulation**
Encapsulation is the concept of bundling data (properties) and methods that operate on that data into a single unit called an object. In JavaScript, this is done using objects and methods, where access to the properties is controlled (often through getters and setters).

#### Example: Encapsulation with Getters and Setters

```javascript
class Person {
  constructor(name, age) {
    this.name = name;
    this._age = age; // _age is a convention for private-like fields
  }

  // Getter
  get age() {
    return this._age;
  }

  // Setter
  set age(value) {
    if (value >= 0) {
      this._age = value;
    } else {
      console.log("Age cannot be negative");
    }
  }
}

const person = new Person("John", 30);
console.log(person.age);  // Output: 30
person.age = 25;          // Changes the age
console.log(person.age);  // Output: 25
person.age = -5;          // Output: Age cannot be negative
```

### 3. **Inheritance**
Inheritance allows a class to inherit properties and methods from another class. In JavaScript, inheritance is prototype-based by default, but it can be achieved using the `extends` keyword in class-based syntax (introduced in ES6).

#### Example: Inheritance in JavaScript

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
  constructor(name, breed) {
    super(name);  // Calls the constructor of the parent class
    this.breed = breed;
  }

  speak() {
    console.log(`${this.name} barks`);
  }
}

const dog = new Dog('Rex', 'Golden Retriever');
dog.speak();  // Output: Rex barks
```

- `extends`: This keyword allows `Dog` to inherit from `Animal`.
- `super`: The `super` keyword is used to call the parent class constructor and methods.

### 4. **Polymorphism**
Polymorphism is the ability to use a method in different ways. In JavaScript, polymorphism is achieved when a subclass redefines (overrides) a method of its parent class.

#### Example: Polymorphism in JavaScript

```javascript
class Animal {
  speak() {
    console.log("Animal makes a sound");
  }
}

class Dog extends Animal {
  speak() {
    console.log("Dog barks");
  }
}

class Cat extends Animal {
  speak() {
    console.log("Cat meows");
  }
}

const animal = new Animal();
const dog = new Dog();
const cat = new Cat();

animal.speak();  // Output: Animal makes a sound
dog.speak();     // Output: Dog barks
cat.speak();     // Output: Cat meows
```

### 5. **Abstraction**
Abstraction is the process of hiding the complex implementation details and showing only the necessary functionality to the user. In JavaScript, this can be done by defining methods inside classes that provide high-level interfaces without exposing the underlying implementation.

#### Example: Abstraction in JavaScript

```javascript
class BankAccount {
  constructor(owner, balance) {
    this.owner = owner;
    this.balance = balance;
  }

  // Public method for deposit
  deposit(amount) {
    if (amount > 0) {
      this.balance += amount;
      console.log(`${this.owner} deposited ${amount}. Balance: ${this.balance}`);
    } else {
      console.log("Deposit amount must be positive");
    }
  }

  // Private method to check balance
  #checkBalance() {
    console.log(`Balance: ${this.balance}`);
  }
}

const account = new BankAccount('Alice', 1000);
account.deposit(500);  // Output: Alice deposited 500. Balance: 1500
// account.#checkBalance(); // Error: Private method, cannot be accessed outside the class
```

- **Private Fields**: JavaScript now supports private fields (using the `#` syntax) that cannot be accessed directly outside the class.

### 6. **Prototype-Based Inheritance (Before ES6)**
Before the introduction of ES6 classes, JavaScript used prototypes for inheritance. Every object has a prototype, which is another object from which it inherits properties and methods.

#### Example: Prototype-Based Inheritance

```javascript
function Animal(name) {
  this.name = name;
}

Animal.prototype.speak = function() {
  console.log(`${this.name} makes a sound`);
};

const dog = new Animal('Dog');
dog.speak();  // Output: Dog makes a sound
```

In this example, `Animal` is a constructor function, and methods like `speak` are added to the prototype of `Animal`. All instances of `Animal` will have access to these methods.

### **12. What are arrow functions, and how do they differ from regular functions?**
   **Arrow functions** are a shorthand way of writing functions in JavaScript. They differ from regular functions in several ways:
   - Syntax: Arrow functions are more concise.
     ```javascript
     // Regular function:
     const add = function(a, b) { return a + b; };

     // Arrow function:
     const add = (a, b) => a + b;
     ```
   - **`this` binding**: In regular functions, `this` refers to the context in which the function was called. In arrow functions, `this` is lexically bound, meaning it inherits `this` from the surrounding context at the time the function is defined.
     ```javascript
     function RegularFunction() {
       this.value = 1;
       setTimeout(function() {
         this.value++; // `this` refers to the global object or undefined in strict mode
       }, 1000);
     }

     const ArrowFunction = () => {
       this.value = 1;
       setTimeout(() => {
         this.value++; // `this` refers to the outer function's `this`
       }, 1000);
     };
     ```
### 32. **What are JavaScript’s built-in objects?**

JavaScript provides several built-in objects, which are available globally. Some of the commonly used built-in objects include:

1. **Object**: The base object for all objects in JavaScript.
2. **Array**: Used to store ordered collections of data.
3. **String**: Used to represent and manipulate sequences of characters.
4. **Number**: Used to represent numerical values.
5. **Boolean**: Represents a logical entity with `true` or `false`.
6. **Date**: Provides methods to work with dates and times.
7. **Math**: A math utility object that provides mathematical constants and functions (e.g., `Math.random()`, `Math.sqrt()`).
8. **RegExp**: Used for working with regular expressions.
9. **JSON**: Provides methods for working with JSON data (e.g., `JSON.stringify()`, `JSON.parse()`).
10. **Error**: Used for creating error objects.

Example:
```javascript
const date = new Date();
console.log(date.getFullYear()); // Built-in Date object
```

### **13. What is a prototype in JavaScript?**
   Every JavaScript object has a **prototype**. A prototype is an object that is associated with every function and object by default. It allows objects to inherit properties and methods from other objects. When you try to access a property or method on an object, JavaScript will look for it on the object itself. If it's not found, JavaScript will look for it on the object's prototype, and so on, up the prototype chain.
   
   Example:
   ```javascript
   function Person(name) {
     this.name = name;
   }

   Person.prototype.greet = function() {
     console.log('Hello ' + this.name);
   };

   const john = new Person('John');
   john.greet(); // Hello John

### 73. **What is the purpose of `WeakMap` and `WeakSet` in JavaScript?**

- **`WeakMap`** is a collection of key-value pairs, where the keys are objects, and the values can be any arbitrary data type. The key objects are weakly held, meaning if there are no other references to a key, it will be garbage collected, preventing memory leaks.

**Use case**: Storing metadata associated with objects without preventing them from being garbage-collected.

**Example:**

```javascript
let obj = {};
let weakMap = new WeakMap();
weakMap.set(obj, 'value');
console.log(weakMap.get(obj)); // 'value'
```

- **`WeakSet`** is similar to `WeakMap` but only stores objects (no key-value pairs), and like `WeakMap`, its elements are weakly referenced.

**Use case**: Storing unique objects without preventing them from being garbage collected.

**Example:**

```javascript
let obj = {};
let weakSet = new WeakSet();
weakSet.add(obj);
console.log(weakSet.has(obj)); // true
```

Both `WeakMap` and `WeakSet` help manage memory more efficiently by not preventing garbage collection of objects.


### 75. **How does JavaScript handle memory management and garbage collection?**

JavaScript automatically manages memory through **garbage collection**, which means it automatically frees up memory when objects are no longer in use. The garbage collector identifies and removes unreachable objects (those that are no longer referenced in the code) to avoid memory leaks.

- **Mark-and-sweep algorithm**: JavaScript uses the "mark-and-sweep" garbage collection algorithm. It first marks all the objects that are reachable (directly or indirectly) from the root (global scope or variables). Then, it sweeps through and deletes the unmarked objects.

**Example:**
```javascript
let obj = { name: "John" };
obj = null; // The object is now eligible for garbage collection since no references exist.
```


### 54. **How does JavaScript handle scope and closures?**

- **Scope**: Refers to the context in which variables are defined and accessible.
  - **Global scope**: Variables are accessible throughout the entire code.
  - **Local scope**: Variables defined inside functions or blocks are only accessible within that function/block.
  - **Block scope**: Variables defined using `let` or `const` are block-scoped, meaning they are confined to the block in which they are defined.

- **Closures**: A closure is a function that "remembers" its lexical scope, even when the function is executed outside that scope.
  - A closure allows a function to access variables from its outer (enclosing) function even after the outer function has finished executing.

Example:
```javascript
function outer() {
  let outerVar = "I am from outer";
  
  function inner() {
    console.log(outerVar); // Inner function has access to outerVar (closure)
  }

  return inner;
}

const closureFunc = outer();
closureFunc(); // "I am from outer"
```

---

### 55. **What is the use of the `window` object in JavaScript?**

The `window` object represents the global context in the browser and provides methods, properties, and events for interacting with the browser window. It is the top-level object in the browser's environment.

Key uses:
- **Global object**: Variables declared globally are properties of the `window` object.
- **Browser manipulation**: Methods like `alert()`, `setTimeout()`, `setInterval()`, `location`, etc.
- **Access to DOM**: Through `window.document`, you can manipulate the DOM.

Example:
```javascript
console.log(window.innerWidth); // Width of the window's content area
alert("Hello, world!"); // Uses window.alert() method
```

---

### 56. **How does the `new` keyword work in JavaScript?**

The `new` keyword is used to create an instance of an object from a constructor function or a class. It does the following:

1. Creates a new empty object.
2. Sets the `this` context of the constructor to that new object.
3. Executes the constructor function, assigning properties and methods to the new object.
4. Returns the new object, unless the constructor explicitly returns a different object.

Example:
```javascript
function Person(name, age) {
  this.name = name;
  this.age = age;
}

const person1 = new Person('Alice', 25);
console.log(person1); // Person { name: 'Alice', age: 25 }
```   

### **14. What is an IIFE (Immediately Invoked Function Expression)?**
   An **IIFE** is a function that is defined and executed immediately after its creation. It is often used to create a local scope and avoid polluting the global namespace.
   
   Example:
   ```javascript
   (function() {
     console.log('I am an IIFE');
   })();
   
   // OR with an arrow function:
   (() => {
     console.log('I am an IIFE with arrow function');
   })();
   ```

   IIFEs are commonly used to avoid polluting the global scope and to create closures.

### **15. What is the difference between a function declaration and a function expression?**
   - **Function Declaration**: A function is declared using the `function` keyword, and it is **hoisted** to the top of its scope. This means the function can be called before it is defined in the code.
     ```javascript
     function greet() {
       console.log('Hello');
     }
     greet(); // works even before the function declaration
     ```
   - **Function Expression**: A function is assigned to a variable or property. It is **not hoisted**, so it can only be called after the expression is executed.
     ```javascript
     const greet = function() {
       console.log('Hello');
     };
     greet(); // works only after the function expression
     ```

### **18. What is the use of `setTimeout()` and `setInterval()`?**
   - **`setTimeout()`**: Executes a function or a specified block of code once after a specified delay (in milliseconds).
     ```javascript
     setTimeout(() => {
       console.log('Hello after 2 seconds');
     }, 2000);
     ```
   - **`setInterval()`**: Executes a function or block of code repeatedly with a specified time interval (in milliseconds).
     ```javascript
     setInterval(() => {
       console.log('Hello every 2 seconds');
     }, 2000);
     ```

### **20. How do you clone an object in JavaScript?**
   There are multiple ways to clone an object in JavaScript:
   - **Using `Object.assign()`** (shallow copy):
     ```javascript
     const original = { a: 1, b: 2 };
     const clone = Object.assign({}, original);
     ```
   - **Using the spread operator** (shallow copy):
     ```javascript
     const original = { a: 1, b: 2 };
     const clone = { ...original };
     ```
   - **Using `JSON.parse()` and `JSON.stringify()`** (deep copy, but works only for objects with JSON-safe values):
     ```javascript
     const original = { a: 1, b: { c: 3 } };
     const clone = JSON.parse(JSON.stringify(original));
     ```
   - **Using libraries** like Lodash (`_.cloneDeep()`) for deep cloning with more control over complex objects.

### 33. **How does `JSON.stringify()` and `JSON.parse()` work in JavaScript?**

- **`JSON.stringify()`**: Converts a JavaScript object or value to a JSON string. This is useful for sending data over the network or saving it to local storage.
  - Converts objects, arrays, and primitive values to JSON-formatted strings.

  Example:
  ```javascript
  const person = { name: "Alice", age: 25 };
  const jsonStr = JSON.stringify(person);
  console.log(jsonStr); // '{"name":"Alice","age":25}'
  ```

- **`JSON.parse()`**: Converts a JSON string back into a JavaScript object. This is useful when retrieving data from local storage or APIs.
  - Returns the corresponding JavaScript object or array.

  Example:
  ```javascript
  const jsonStr = '{"name":"Alice","age":25}';
  const obj = JSON.parse(jsonStr);
  console.log(obj); // { name: 'Alice', age: 25 }
  ```


### 34. **How can you create a class in JavaScript?**

In JavaScript, classes can be created using the `class` keyword (introduced in ES6). A class is a blueprint for creating objects with shared properties and methods.

Syntax:
```javascript
class Person {
  constructor(name, age) {
    this.name = name;
    this.age = age;
  }

  greet() {
    console.log(`Hello, my name is ${this.name}.`);
  }
}

const person1 = new Person('Alice', 25);
person1.greet(); // "Hello, my name is Alice."
```

- **`constructor()`**: A special method used for initializing new objects created from the class.
- Methods defined inside a class are automatically added to the prototype of the class.


### 76. **Explain the concept of prototype inheritance in JavaScript.**

In JavaScript, every object has a prototype, which is another object from which it inherits properties and methods. This is known as **prototype inheritance**. If you try to access a property or method on an object, and it’s not found, JavaScript will look for it in the object's prototype.

This allows objects to share common functionality without having to duplicate code.

**Example:**

```javascript
function Animal(name) {
  this.name = name;
}

Animal.prototype.speak = function() {
  console.log(this.name + ' makes a noise.');
};

const dog = new Animal('Dog');
dog.speak(); // Dog makes a noise.
```

Here, the `dog` object inherits the `speak` method from `Animal.prototype`.

---

### 77. **What are the different methods of creating objects in JavaScript?**

In JavaScript, objects can be created in various ways:

1. **Object literal syntax**:
   ```javascript
   const obj = { name: 'John', age: 30 };
   ```

2. **Using a constructor function**:
   ```javascript
   function Person(name, age) {
     this.name = name;
     this.age = age;
   }
   const person1 = new Person('Alice', 25);
   ```

3. **Using `Object.create()`**:
   ```javascript
   const obj = Object.create(null); // Creates an object with no prototype
   ```

4. **Using `class` (ES6)**:
   ```javascript
   class Car {
     constructor(make, model) {
       this.make = make;
       this.model = model;
     }
   }
   const myCar = new Car('Toyota', 'Corolla');
   ```

---

### 78. **What is the difference between `Object.create()` and class-based inheritance?**

- **`Object.create()`**:
  - Creates a new object, optionally inheriting from another object.
  - Allows for more flexible and dynamic inheritance.
  - You can create an object with a custom prototype, but it does not involve constructors or class syntax.

**Example:**

```javascript
const person = { name: 'John' };
const student = Object.create(person);
student.age = 20;
console.log(student.name); // 'John' (inherited from person)
```

- **Class-based inheritance**:
  - Introduced in ES6, it provides a clearer and more structured way to create objects and handle inheritance using `class` and `extends`.
  - It involves constructors, methods, and inheritance using `extends`.

**Example:**

```javascript
class Person {
  constructor(name) {
    this.name = name;
  }
}

class Student extends Person {
  constructor(name, age) {
    super(name);
    this.age = age;
  }
}

const student = new Student('Alice', 20);
console.log(student.name); // 'Alice'
```

In summary, `Object.create()` is more flexible and used for direct prototype-based inheritance, while class-based inheritance offers a more structured and syntactic approach to creating objects and managing inheritance.

### 35. **What is the difference between `class` and `constructor` in JavaScript?**

- **`class`**: A class is a blueprint for creating objects, defining properties and methods that can be shared by all instances of the class.
  
  - It is a syntactic sugar over JavaScript's existing prototype-based inheritance.
  - You can create instances of a class using the `new` keyword.
  
  Example:
  ```javascript
  class Animal {
    constructor(name) {
      this.name = name;
    }
    speak() {
      console.log(`${this.name} makes a sound.`);
    }
  }
  ```

- **`constructor`**: A special method inside a class, used to initialize a newly created object.
  - It is executed when an instance of the class is created using the `new` keyword.
  
  Example:
  ```javascript
  class Animal {
    constructor(name) {
      this.name = name; // Initializes 'name' property
    }
  }
  ```

In summary, `class` is the entire structure defining an object template, while `constructor` is a function used to initialize the properties of an object when an instance of the class is created.

---

### 36. **What are getter and setter methods in JavaScript?**

Getter and setter methods allow you to define how to access or modify the properties of an object.

- **Getter**: A method that gets the value of a property.
- **Setter**: A method that sets the value of a property.

Both are defined using `get` and `set` keywords.

Example:
```javascript
class Person {
  constructor(name) {
    this._name = name; // Private property
  }

  // Getter
  get name() {
    return this._name;
  }

  // Setter
  set name(value) {
    if (value.length > 0) {
      this._name = value;
    } else {
      console.log("Name cannot be empty.");
    }
  }
}

const person = new Person("Alice");
console.log(person.name); // "Alice" - Calls the getter
person.name = "Bob"; // Calls the setter
console.log(person.name); // "Bob"
```  

## **2. Arrays and Objects**
Here are the answers to the next set of JavaScript questions:

### **21. How can you merge two arrays in JavaScript?**
   You can merge two arrays in JavaScript using several methods:
   - **Using the `concat()` method**:
     ```javascript
     const array1 = [1, 2, 3];
     const array2 = [4, 5, 6];
     const mergedArray = array1.concat(array2);
     ```
   - **Using the spread operator** (ES6):
     ```javascript
     const array1 = [1, 2, 3];
     const array2 = [4, 5, 6];
     const mergedArray = [...array1, ...array2];
     ```

   Both methods result in a new array with elements from both arrays.

### **22. What is the difference between `slice()` and `splice()` in JavaScript?**
   - **`slice()`**:
     - Does not modify the original array.
     - Returns a shallow copy of a portion of the array.
     - Takes two arguments: start index (inclusive) and end index (exclusive).
     - Syntax: `array.slice(startIndex, endIndex)`
     ```javascript
     const arr = [1, 2, 3, 4, 5];
     const slicedArray = arr.slice(1, 4); // [2, 3, 4]
     ```

   - **`splice()`**:
     - Modifies the original array.
     - Can remove elements, add new elements, or replace elements.
     - Takes at least two arguments: the starting index and the number of elements to remove (optional). Additional arguments can be added to insert new elements.
     - Syntax: `array.splice(startIndex, deleteCount, item1, item2, ...)`
     ```javascript
     const arr = [1, 2, 3, 4, 5];
     arr.splice(2, 2, 'a', 'b'); // arr becomes [1, 2, 'a', 'b', 5]
     ```

### **23. How do you remove duplicates from an array in JavaScript?**
   There are a few ways to remove duplicates:
   - **Using a `Set`** (modern and simple):
     ```javascript
     const arr = [1, 2, 3, 2, 4, 1];
     const uniqueArray = [...new Set(arr)];
     ```
   - **Using `filter()` and `indexOf()`**:
     ```javascript
     const arr = [1, 2, 3, 2, 4, 1];
     const uniqueArray = arr.filter((value, index, self) => self.indexOf(value) === index);
     ```

### **24. What are the different ways to loop through an array in JavaScript?**
   There are multiple ways to loop through an array in JavaScript:
   - **For loop**:
     ```javascript
     const arr = [1, 2, 3, 4];
     for (let i = 0; i < arr.length; i++) {
       console.log(arr[i]);
     }
     ```
   - **For...of loop** (ES6):
     ```javascript
     for (const value of arr) {
       console.log(value);
     }
     ```
   - **forEach()**:
     ```javascript
     arr.forEach(value => {
       console.log(value);
     });
     ```
   - **map()** (for transforming arrays):
     ```javascript
     const squaredArr = arr.map(value => value * value);
     ```
   - **for...in loop** (not typically recommended for arrays, used for object keys):
     ```javascript
     for (const index in arr) {
       console.log(arr[index]);
     }
     ```

### **25. How do you sort an array of objects based on a property in JavaScript?**
   You can use the `sort()` method to sort an array of objects based on a specific property:
   - **Example**: Sorting by `age` property in an array of objects.
   ```javascript
   const people = [
     { name: 'John', age: 25 },
     { name: 'Jane', age: 30 },
     { name: 'Bob', age: 20 }
   ];
   people.sort((a, b) => a.age - b.age); // Sorts by age in ascending order
   console.log(people);
   ```

   For descending order, simply reverse the subtraction:
   ```javascript
   people.sort((a, b) => b.age - a.age); // Sorts by age in descending order
   ```

### **26. What is the difference between `Object.assign()` and the spread operator (`...`)?**
   Both **`Object.assign()`** and the **spread operator** are used to create shallow copies of objects or merge them, but they differ in syntax and behavior:
   - **`Object.assign()`**:
     - Copies all properties from one or more source objects to a target object.
     - Modifies the target object and returns it.
     ```javascript
     const obj1 = { a: 1, b: 2 };
     const obj2 = { c: 3 };
     const mergedObj = Object.assign({}, obj1, obj2); // Creates a shallow copy
     ```
   - **Spread operator**:
     - Creates a shallow copy of an object.
     - More concise syntax compared to `Object.assign()`.
     ```javascript
     const obj1 = { a: 1, b: 2 };
     const obj2 = { c: 3 };
     const mergedObj = { ...obj1, ...obj2 }; // Creates a shallow copy
     ```

   Both methods perform shallow copies, meaning nested objects are still referenced by the original object.


### 97. **What is the difference between shallow copy and deep copy in JavaScript?**

- **Shallow Copy**:
  A shallow copy means creating a new object, but nested objects or arrays within it are still referenced (not copied). It only copies the top-level properties.

  **Example**:

  ```javascript
  const obj1 = { a: 1, b: { c: 2 } };
  const shallowCopy = { ...obj1 };
  
  shallowCopy.b.c = 3;  // Modifying nested object
  console.log(obj1.b.c);  // 3, both obj1 and shallowCopy share the same reference to b
  ```

- **Deep Copy**:
  A deep copy means creating a completely independent copy of the original object, including all nested objects. It ensures that no references are shared between the copied object and the original.

  **Example**:

  ```javascript
  const obj1 = { a: 1, b: { c: 2 } };
  const deepCopy = JSON.parse(JSON.stringify(obj1));  // Simple deep copy method
  
  deepCopy.b.c = 3;
  console.log(obj1.b.c);  // 2, obj1 and deepCopy are independent
  ```

### **27. How do you check if an object is an array in JavaScript?**
   You can check if an object is an array using:
   - **`Array.isArray()`**:
     ```javascript
     const arr = [1, 2, 3];
     console.log(Array.isArray(arr)); // true
     ```
   - **`instanceof`**:
     ```javascript
     console.log(arr instanceof Array); // true
     ```

   `Array.isArray()` is the recommended method, as it works across all environments (including iframes).

### **28. What is object destructuring in JavaScript?**
   **Object destructuring** allows you to extract properties from objects and assign them to variables in a more concise syntax.
   - **Example**:
     ```javascript
     const person = { name: 'John', age: 25, city: 'New York' };
     const { name, age } = person; // Destructuring
     console.log(name, age); // 'John', 25
     ```
   You can also assign default values and rename properties during destructuring:
   ```javascript
   const { name: fullName = 'Unknown' } = person;
   ```

### **29. How can you merge two objects in JavaScript?**
   You can merge two objects in JavaScript using:
   - **`Object.assign()`**:
     ```javascript
     const obj1 = { a: 1, b: 2 };
     const obj2 = { b: 3, c: 4 };
     const mergedObj = Object.assign({}, obj1, obj2); // Merges obj1 and obj2
     ```
   - **Spread operator (`...`)**:
     ```javascript
     const obj1 = { a: 1, b: 2 };
     const obj2 = { b: 3, c: 4 };
     const mergedObj = { ...obj1, ...obj2 }; // Merges obj1 and obj2
     ```

   In both methods, if the objects have properties with the same name, the value from the second object will overwrite the value from the first object.

### **30. What is the difference between `for...in` and `for...of` loops in JavaScript?**
   - **`for...in`**:
     - Iterates over the **keys** (or property names) of an object or the indexes of an array.
     - Not recommended for arrays, as it can iterate over inherited properties.
     ```javascript
     const arr = [1, 2, 3];
     for (const index in arr) {
       console.log(index); // 0, 1, 2 (index)
     }
     ```
   - **`for...of`**:
     - Iterates over the **values** of an iterable object (like an array, string, or set).
     - Works well with arrays to get the values directly.
     ```javascript
     const arr = [1, 2, 3];
     for (const value of arr) {
       console.log(value); // 1, 2, 3 (values)
     }
     ```

   - **Summary**:
     - Use `for...in` for objects (to iterate over properties).
     - Use `for...of` for arrays and other iterable objects (to iterate over values).


## **3. Functions and Scope**
Here are the answers to the next set of JavaScript questions:

### **31. What is the difference between local scope and global scope in JavaScript?**
   - **Local Scope**: A variable or function declared within a function is in the **local scope** and can only be accessed within that function. Once the function execution is complete, local variables are destroyed.
     ```javascript
     function example() {
       let localVar = 'I am local';
       console.log(localVar); // Accessible here
     }
     console.log(localVar); // Error: localVar is not defined
     ```

   - **Global Scope**: A variable or function declared outside any function is in the **global scope** and can be accessed from anywhere in the code.
     ```javascript
     let globalVar = 'I am global';
     function example() {
       console.log(globalVar); // Accessible here
     }
     console.log(globalVar); // Accessible here too
     ```

   In JavaScript, global variables can be accessed throughout the entire program, whereas local variables are confined to the function where they are declared.

### **32. What is lexical scoping in JavaScript?**
   **Lexical scoping** means that the accessibility of variables is determined by where they are defined in the source code. In JavaScript, this refers to the scope in which a function is created. The inner function can access variables from its own scope as well as from any outer (enclosing) function's scope, but not vice versa.

   Example:
   ```javascript
   function outer() {
     let outerVar = 'I am from outer';
     function inner() {
       console.log(outerVar); // inner function can access outerVar
     }
     inner();
   }
   outer();
   ```

   In this example, the inner function can access `outerVar` because `inner()` is lexically scoped within `outer()`.

### **33. What is the difference between function expressions and function declarations?**
   - **Function Declaration**: A function is declared with the `function` keyword, and it is **hoisted** to the top of its scope, meaning it can be called before its definition.
     ```javascript
     function greet() {
       console.log('Hello');
     }
     greet(); // Works even before the function definition
     ```

   - **Function Expression**: A function is assigned to a variable and is **not hoisted**. It can only be called after the function expression is assigned to the variable.
     ```javascript
     const greet = function() {
       console.log('Hello');
     };
     greet(); // Works only after the function expression
     ```

   The key difference is that **function declarations are hoisted** while function expressions are **not**.

### **34. What is the use of the `arguments` object in JavaScript?**
   The `arguments` object is an **array-like object** available inside all non-arrow functions that contains all the arguments passed to the function, regardless of the number of parameters defined in the function.

   Example:
   ```javascript
   function sum() {
     let total = 0;
     for (let i = 0; i < arguments.length; i++) {
       total += arguments[i];
     }
     return total;
   }
   console.log(sum(1, 2, 3)); // 6
   ```

   The `arguments` object does not work with arrow functions and cannot be used with them. It is useful for functions where the number of arguments is not fixed.

### **35. What is the purpose of the `default` keyword in JavaScript functions?**
   The `default` keyword is used to assign default values to function parameters when no argument or `undefined` is passed.

   Example:
   ```javascript
   function greet(name = 'Guest') {
     console.log('Hello, ' + name);
   }
   greet(); // Hello, Guest
   greet('John'); // Hello, John
   ```

   In this example, if no value is passed for the `name` parameter, it defaults to `'Guest'`.

### **36. How can you return multiple values from a function in JavaScript?**
   JavaScript functions can only return one value. However, you can return multiple values using objects, arrays, or other data structures.

   - **Using an object**:
     ```javascript
     function getPersonInfo() {
       return { name: 'John', age: 30 };
     }
     const person = getPersonInfo();
     console.log(person.name, person.age); // John, 30
     ```

   - **Using an array**:
     ```javascript
     function getCoordinates() {
       return [10, 20];
     }
     const [x, y] = getCoordinates();
     console.log(x, y); // 10, 20
     ```

   This way, you can return multiple values by packaging them into a single object or array.

### **38. What is recursion in JavaScript? Can you provide an example?**
   **Recursion** is when a function calls itself to solve a problem. A base case is usually defined to prevent infinite recursion.

   Example (factorial function):
   ```javascript
   function factorial(n) {
     if (n === 0) return 1; // base case
     return n * factorial(n - 1); // recursive case
   }
   console.log(factorial(5)); // 120
   ```

   In this example, the `factorial` function calls itself with a reduced value of `n` until it reaches the base case (`n === 0`).


### 81. **How does JavaScript handle multiple callback functions?**

JavaScript handles multiple callback functions by adding them to the **call stack** sequentially. When an asynchronous event occurs, callbacks are added to the event queue, and the event loop processes them one at a time.

In case of **nested callbacks**, JavaScript will add each function to the call stack in the order they are invoked. However, callbacks can be executed in parallel in asynchronous functions like `setTimeout` or with promises.

**Example with `setTimeout`:**

```javascript
setTimeout(() => console.log('First'), 1000);
setTimeout(() => console.log('Second'), 500);

console.log('Third');
```

**Output**:

```
Third
Second
First
```

The event loop handles `setTimeout` and logs `Second` after 500ms, followed by `First` after 1000ms.

### **40. How do you handle errors in JavaScript?**
   Errors in JavaScript can be handled using `try`, `catch`, and `finally` blocks:
   - **`try`**: Wraps code that may throw an error.
   - **`catch`**: Handles the error if one is thrown.
   - **`finally`**: Executes code after the `try` and `catch`, regardless of whether an error was thrown or not.

   Example:
   ```javascript
   try {
     const result = someUndefinedFunction();
   } catch (error) {
     console.error('An error occurred:', error);
   } finally {
     console.log('This will always run');
   }
   ```

   This ensures that errors are caught and handled properly, and cleanup code is always executed with the `finally` block.


### 40. **How does JavaScript handle asynchronous operations?**

JavaScript handles asynchronous operations using:

1. **Callbacks**: Functions passed as arguments to other functions, to be executed later.
   ```javascript
   function fetchData(callback) {
     setTimeout(() => {
       console.log("Data fetched");
       callback();
     }, 1000);
   }

   fetchData(() => {
     console.log("Callback executed");
   });
   ```

2. **Promises**: Represent the eventual completion (or failure) of an asynchronous operation and its resulting value. Promises allow chaining of asynchronous operations with `.then()`, `.catch()`, and `.finally()`.
   ```javascript
   const myPromise = new Promise((resolve, reject) => {
     setTimeout(() => resolve("Data fetched"), 1000);
   });

   myPromise.then(result => console.log(result)); // "Data fetched"
   ```

3. **Async/Await**: Makes asynchronous code look synchronous. The `async` function returns a promise, and `await` pauses execution until the promise is resolved.
   ```javascript
   async function fetchData() {
     let result = await myPromise;
     console.log(result); // "Data fetched"
   }

   fetchData();
   ```

## **4. Asynchronous JavaScript**
Here are the answers to the next set of JavaScript questions:

### **44. How can you handle asynchronous operations in JavaScript?**
   Asynchronous operations can be handled in JavaScript in several ways:
   1. **Callbacks**: Functions passed as arguments to be executed once an operation is complete.
   2. **Promises**: Allow chaining `.then()` for success and `.catch()` for error handling.
   3. **Async/Await**: A more readable and concise way to work with asynchronous code, based on promises.

   Example using `Promise`:
   ```javascript
   function fetchData() {
     return new Promise((resolve, reject) => {
       setTimeout(() => resolve('Data fetched'), 1000);
     });
   }

   fetchData().then(result => console.log(result));
   ```

### 14. **Explain the concept of JavaScript’s single-threaded model.**

JavaScript operates on a **single-threaded** model, meaning it can only execute one task at a time in a single execution thread. This is different from multi-threaded environments where multiple tasks can run simultaneously.

However, JavaScript uses an **event loop** and **asynchronous operations** (such as `setTimeout`, `Promises`, and AJAX) to handle tasks like I/O without blocking the main thread. These asynchronous tasks are queued and executed when the main thread is free, enabling non-blocking behavior while still being single-threaded.

This single-threaded nature can cause performance issues if long-running tasks block the event loop, which is why techniques like **web workers** (for parallelism) or breaking tasks into smaller chunks are used in JavaScript.

---

### 15. **What are the data types that are mutable in JavaScript?**

In JavaScript, **mutable** data types are those whose values can be changed after they are created. The mutable data types are:

- **Objects**: You can add, modify, or delete properties.
  ```javascript
  const obj = { name: "Alice", age: 25 };
  obj.age = 26; // mutable property
  ```

- **Arrays**: You can add, remove, or change elements.
  ```javascript
  const arr = [1, 2, 3];
  arr[0] = 10; // mutable element
  ```

- **Functions**: Functions themselves are objects, and their properties can be modified.
  ```javascript
  function greet() { return "Hello"; }
  greet.message = "Welcome"; // Adding property to function
  ```

---

### 16. **What is a function in JavaScript? How do you declare one?**

A **function** in JavaScript is a block of reusable code that performs a specific task. Functions can accept inputs (parameters) and return outputs (values).

You can declare a function in several ways:

- **Function declaration (function statement)**:
  ```javascript
  function greet(name) {
    return `Hello, ${name}!`;
  }
  ```

- **Function expression**:
  ```javascript
  const greet = function(name) {
    return `Hello, ${name}!`;
  };
  ```

- **Arrow function** (ES6+):
  ```javascript
  const greet = (name) => `Hello, ${name}!`;
  ```

### **47. How does JavaScript handle multiple asynchronous operations in sequence?**
   JavaScript handles multiple asynchronous operations in sequence by chaining promises or using `async/await`. When one asynchronous operation completes, the next one can be executed.

   - **With Promises**:
     ```javascript
     fetchData1()
       .then(result1 => {
         return fetchData2(result1);
       })
       .then(result2 => {
         return fetchData3(result2);
       })
       .catch(error => console.log(error));
     ```

   - **With Async/Await**:
     ```javascript
     async function runAsync() {
       try {
         const result1 = await fetchData1();
         const result2 = await fetchData2(result1);
         const result3 = await fetchData3(result2);
       } catch (error) {
         console.log(error);
       }
     }
     runAsync();
     ```

   Both methods ensure that each asynchronous operation completes before the next one begins.

### **48. What is the purpose of `Promise.all()` and `Promise.race()`?**
   - **`Promise.all()`**: Waits for all promises to resolve or one to reject. It returns a promise that resolves when all input promises are resolved.
     ```javascript
     Promise.all([fetchData1(), fetchData2()])
       .then(results => console.log(results))
       .catch(error => console.log(error));
     ```

   - **`Promise.race()`**: Waits for the first promise to resolve or reject, and returns the result of that promise. It is useful when you want to handle the first completion (either resolved or rejected).
     ```javascript
     Promise.race([fetchData1(), fetchData2()])
       .then(result => console.log(result))
       .catch(error => console.log(error));
     ```

### **49. How do you handle errors in an async function?**
   Errors in an `async` function can be handled using a `try/catch` block. If an error occurs inside an `await` expression, it will be caught in the `catch` block.

   Example:
   ```javascript
   async function fetchData() {
     try {
       const data = await fetch('https://api.example.com');
       const jsonData = await data.json();
       console.log(jsonData);
     } catch (error) {
       console.log('Error fetching data:', error);
     }
   }
   fetchData();
   ```

   This ensures that any errors in the asynchronous operation are properly handled.

### **50. What is a callback hell, and how can you avoid it?**
   **Callback hell** refers to the situation where multiple nested callbacks lead to code that is difficult to read and maintain, typically resulting in deeply nested structures.

   Example of callback hell:
   ```javascript
   getData(function(a) {
     getData2(a, function(b) {
       getData3(b, function(c) {
         // More callbacks...
       });
     });
   });
   ```

   To avoid callback hell:
   - **Use Promises**: This allows chaining, making the code more readable.
   - **Use `async/await`**: This flattens the nested structure and provides a more synchronous flow of control.

   Example using `async/await`:
   ```javascript
   async function fetchData() {
     const a = await getData();
     const b = await getData2(a);
     const c = await getData3(b);
     // More code...
   }
   fetchData();
   ```

   By using promises or `async/await`, you can avoid the nested callbacks and make the code cleaner and more maintainable.


### 21. **What are the different ways to loop through an array in JavaScript?**

There are several ways to loop through an array in JavaScript:

1. **`for` loop**: Traditional loop that allows manual control over index.
   ```javascript
   const arr = [1, 2, 3];
   for (let i = 0; i < arr.length; i++) {
     console.log(arr[i]);
   }
   ```

2. **`forEach()`**: A higher-order function that executes a callback for each item in the array.
   ```javascript
   const arr = [1, 2, 3];
   arr.forEach(item => {
     console.log(item);
   });
   ```

3. **`for...of`**: Simplified iteration over array values.
   ```javascript
   const arr = [1, 2, 3];
   for (const item of arr) {
     console.log(item);
   }
   ```

4. **`for...in`**: Iterates over the array indices (not recommended for arrays).
   ```javascript
   const arr = [1, 2, 3];
   for (const index in arr) {
     console.log(arr[index]);
   }
   ```

5. **`map()`**: Creates a new array by applying a function to each element.
   ```javascript
   const arr = [1, 2, 3];
   const doubled = arr.map(item => item * 2);
   console.log(doubled);
   ```

6. **`filter()`**: Creates a new array with elements that pass a test.
   ```javascript
   const arr = [1, 2, 3, 4];
   const even = arr.filter(item => item % 2 === 0);
   console.log(even);
   ```

## **5. ES6 and Beyond**
Here are the answers to the next set of JavaScript questions:

### **51. What are template literals in JavaScript?**
   **Template literals** are a new way to work with strings in JavaScript introduced in ES6. They allow for easier string interpolation, multi-line strings, and embedding expressions inside strings.

   Key features:
   - **String interpolation**: Using `${}` to embed expressions inside strings.
   - **Multi-line strings**: Template literals allow strings to span multiple lines without needing escape characters.

   Example:
   ```javascript
   const name = 'Alice';
   const age = 25;
   const greeting = `Hello, my name is ${name} and I am ${age} years old.`;
   console.log(greeting); // Hello, my name is Alice and I am 25 years old.
   ```

   - Multi-line example:
   ```javascript
   const message = `This is
   a multi-line
   string.`;
   console.log(message);
   ```

### **52. What are the new features introduced in ES6?**
   ES6 (ECMAScript 2015) introduced several important features:
   - **Let and Const**: New ways to declare variables with block-scoping (`let`) and constants (`const`).
   - **Arrow Functions**: A concise way to define functions with lexical `this`.
   - **Classes**: Syntactic sugar over prototype-based inheritance.
   - **Template Literals**: String interpolation and multi-line strings.
   - **Destructuring Assignment**: A way to unpack values from arrays or objects into variables.
   - **Default Parameters**: Allows default values for function parameters.
   - **Rest/Spread Operators**: For handling variable numbers of arguments and copying objects/arrays.
   - **Promises**: For handling asynchronous operations more effectively.
   - **Modules**: `import` and `export` for modular code.
   - **Map and Set**: New collection types for unique values and key-value pairs.
   - **Generators**: Functions that can pause and resume execution (`yield`).

### **53. What is a set and a map in JavaScript?**
   - **Set**: A collection of unique values. A set does not allow duplicate values, and the values are ordered.
   
     Example:
     ```javascript
     const mySet = new Set([1, 2, 3, 2, 1]);
     console.log(mySet); // Set {1, 2, 3}
     ```
     A `Set` can be iterated in the order of insertion.

   - **Map**: A collection of key-value pairs where keys can be of any data type, and the keys are unique.
   
     Example:
     ```javascript
     const myMap = new Map();
     myMap.set('name', 'Alice');
     myMap.set('age', 25);
     console.log(myMap.get('name')); // Alice
     ```
     A `Map` maintains the insertion order of the keys.

### 37. **What are symbols in JavaScript? When would you use them?**

A **symbol** is a primitive data type introduced in ES6, used to create unique, immutable identifiers for object properties. Symbols are often used to add properties to objects without the risk of name clashes.

- **Uniqueness**: Each symbol is guaranteed to be unique.
- **Immutability**: Once created, a symbol cannot be altered.

Symbols are commonly used in situations like:
- Creating private properties.
- Adding unique keys to objects to avoid collisions in libraries or frameworks.

Example:
```javascript
const uniqueKey = Symbol("key");
const obj = {};
obj[uniqueKey] = "value";

console.log(obj[uniqueKey]); // "value"
```

### **54. What are generator functions in JavaScript?**
   **Generator functions** are functions that can yield multiple values over time, allowing you to pause their execution and resume it later. Generator functions use the `function*` syntax and the `yield` keyword.

   Example:
   ```javascript
   function* countUpTo(max) {
     let count = 0;
     while (count <= max) {
       yield count; // Pauses execution and returns the value
       count++;
     }
   }

   const counter = countUpTo(3);
   console.log(counter.next().value); // 0
   console.log(counter.next().value); // 1
   console.log(counter.next().value); // 2
   console.log(counter.next().value); // 3
   console.log(counter.next().value); // undefined (end of generator)
   ```

   The `next()` method is used to resume the function from where it was paused.

### **55. How does destructuring work in JavaScript?**
   **Destructuring** allows you to unpack values from arrays or properties from objects into variables.

   - **Array Destructuring**:
     ```javascript
     const arr = [1, 2, 3];
     const [a, b, c] = arr;
     console.log(a, b, c); // 1, 2, 3
     ```

   - **Object Destructuring**:
     ```javascript
     const person = { name: 'Alice', age: 25 };
     const { name, age } = person;
     console.log(name, age); // Alice, 25
     ```

   You can also use default values, renaming, and nested destructuring.

### **56. What is the spread operator, and how do you use it?**
   The **spread operator** (`...`) allows you to unpack elements from arrays or objects and insert them into other arrays or objects. It can also be used to copy arrays/objects or merge them.

   - **In Arrays**:
     ```javascript
     const arr1 = [1, 2, 3];
     const arr2 = [...arr1, 4, 5];
     console.log(arr2); // [1, 2, 3, 4, 5]
     ```

   - **In Objects**:
     ```javascript
     const obj1 = { a: 1, b: 2 };
     const obj2 = { ...obj1, c: 3 };
     console.log(obj2); // { a: 1, b: 2, c: 3 }
     ```

   The spread operator is used to spread elements of an iterable (like an array or object) into a new array or object.

### **57. What is the rest parameter in JavaScript?**
   The **rest parameter** (`...`) is used to collect all remaining arguments passed to a function into an array. It allows for a variable number of arguments to be passed to a function.

   Example:
   ```javascript
   function sum(...numbers) {
     return numbers.reduce((acc, num) => acc + num, 0);
   }
   console.log(sum(1, 2, 3, 4)); // 10
   ```

   The rest parameter must be the last parameter in the function definition, and it gathers all the remaining arguments into an array.

### **58. What are `Promise.allSettled()`, `Promise.any()`, and `Promise.finally()`?**
   - **`Promise.allSettled()`**: Returns a promise that resolves after all of the input promises have settled (either resolved or rejected). It provides an array of results, each being an object with `status` and either `value` or `reason`.

     Example:
     ```javascript
     const promises = [Promise.resolve(1), Promise.reject('Error')];
     Promise.allSettled(promises).then(results => console.log(results));
     // Output: [{ status: 'fulfilled', value: 1 }, { status: 'rejected', reason: 'Error' }]
     ```

   - **`Promise.any()`**: Returns a promise that resolves as soon as one of the input promises resolves. If all promises reject, it returns an aggregate error.

     Example:
     ```javascript
     const promises = [Promise.reject('Error 1'), Promise.resolve(2), Promise.resolve(3)];
     Promise.any(promises).then(result => console.log(result)); // 2
     ```

   - **`Promise.finally()`**: Executes a callback when the promise is settled (resolved or rejected), regardless of the outcome. It's used for cleanup actions.

     Example:
     ```javascript
     Promise.resolve('Done').finally(() => console.log('Cleanup actions here'));
     ```

### **59. How do you create a class in JavaScript?**
   You can create a class using the `class` keyword, introduced in ES6. A class defines a blueprint for creating objects with shared properties and methods.

   Example:
   ```javascript
   class Person {
     constructor(name, age) {
       this.name = name;
       this.age = age;
     }

     greet() {
       console.log(`Hello, my name is ${this.name} and I am ${this.age} years old.`);
     }
   }

   const person1 = new Person('Alice', 25);
   person1.greet(); // Hello, my name is Alice and I am 25 years old.
   ```

   The `constructor` method is used to initialize the object's properties, and methods can be added to the class to define behavior.

### **60. What is the difference between `class` and `function` constructors in JavaScript?**
   - **Class Constructors**: Introduced in ES6, classes are a syntactical sugar over JavaScript's prototype-based inheritance. The `constructor` method is used to initialize new instances of the class.

     Example:
     ```javascript
     class Person {
       constructor(name, age) {
         this.name = name;
         this.age = age;
       }
     }
     ```

   - **Function Constructors**: Before classes, functions were used to create objects with shared methods via prototypes. A function constructor is a regular function used to initialize an object.

     Example:
     ```javascript
     function Person(name, age) {
       this.name = name;
       this.age = age;
     }
     ```

   **Differences**:
   - Classes use the `class` keyword and have a `constructor` method.
   - Function constructors are created using regular functions and set properties directly via `this`.
   - Class constructors provide better readability and use the modern object-oriented approach compared to function constructors.

---


## **6. Error Handling and Debugging**
Here are the answers to the next set of JavaScript questions:

### **61. What is try-catch in JavaScript? How does it work?**
   The **`try...catch`** statement in JavaScript is used to handle exceptions (runtime errors). It allows you to define a block of code to execute, and if an error occurs, it can be caught and handled gracefully.

   - **`try` block**: Contains code that may throw an exception.
   - **`catch` block**: Contains code that handles the error if an exception is thrown in the `try` block.

   Example:
   ```javascript
   try {
     let result = 10 / 0;
     console.log(result); // This won't throw an error, but division by zero is problematic.
   } catch (error) {
     console.error('An error occurred:', error.message);
   }
   ```

   - Optionally, there is also a **`finally` block** that runs after `try` and `catch`, whether an error occurred or not. It's often used for cleanup code.

   Example with `finally`:
   ```javascript
   try {
     let data = JSON.parse('{"name": "John"}');
     console.log(data);
   } catch (error) {
     console.error('Error parsing JSON:', error);
   } finally {
     console.log('This will always run, regardless of an error.');
   }
   ```

### **62. What is the difference between `throw` and `return` in JavaScript?**
   - **`throw`**: The `throw` statement is used to throw an exception or an error manually. Once an exception is thrown, the execution of the current function is stopped, and control is passed to the nearest `catch` block (if available). It is used for error handling.

     Example:
     ```javascript
     function checkNumber(num) {
       if (num < 0) {
         throw new Error('Negative number is not allowed');
       }
       return num;
     }
     ```

   - **`return`**: The `return` statement is used to return a value from a function. It stops the execution of the function and passes the value back to the calling code.

     Example:
     ```javascript
     function add(a, b) {
       return a + b; // Return the sum of a and b
     }
     ```

   **Difference**:
   - `throw` is used for raising errors and interrupting execution flow.
   - `return` is used to exit a function and return a result to the caller.

### **63. How do you handle exceptions in JavaScript?**
   Exceptions are handled using the **`try...catch`** statement in JavaScript. This allows you to handle potential errors without breaking the flow of your program.

   Example:
   ```javascript
   try {
     let result = riskyFunction();
     console.log(result);
   } catch (error) {
     console.error('An error occurred:', error.message);
   }
   ```

   - **`try` block**: Wraps the code that may throw an error.
   - **`catch` block**: Catches the error if one occurs and allows you to handle it.
   - Optionally, a **`finally` block** can be used to execute code that should run after the `try` and `catch`, regardless of whether an error occurred.

   For example, you might use `try...catch` to handle network requests, parsing errors, or accessing properties of undefined objects.

### **64. What are some common JavaScript debugging techniques?**
   Some common debugging techniques in JavaScript include:
   - **Console logging (`console.log()`)**: The simplest and most commonly used debugging technique. You can log variables, functions, and execution flow.
     ```javascript
     console.log(myVariable);
     ```
   - **Using `debugger` statement**: Inserting a `debugger;` statement in the code will pause execution at that point, allowing you to inspect variables and execution flow in the browser's developer tools.
     ```javascript
     function myFunction() {
       debugger; // Code execution pauses here
     }
     ```
   - **Browser Developer Tools**: All modern browsers provide developer tools (DevTools), where you can set breakpoints, inspect variables, and step through code. This is a powerful way to track down issues.
   - **Error stack traces**: When an error occurs, JavaScript provides a stack trace in the console, showing where the error originated.
   - **Using `try...catch` blocks**: To catch runtime errors and log or handle them, you can use `try...catch`.
   - **Linting tools**: Use tools like ESLint to detect potential issues before running the code.

### **65. What is the difference between `Error` and `TypeError` in JavaScript?**
   - **`Error`**: The generic `Error` object is used when an error occurs that doesn't fit into any specific category. It is the base class for other error types in JavaScript. You can throw an instance of the `Error` class or extend it to create custom errors.

     Example:
     ```javascript
     const error = new Error('Something went wrong');
     throw error; // This throws a generic error
     ```

   - **`TypeError`**: A `TypeError` occurs when a value is not of the expected type. It is a specific subclass of `Error`, and it is thrown when an operation is performed on an inappropriate data type.

     Example:
     ```javascript
     let num = null;
     console.log(num.toUpperCase()); // Throws a TypeError because `null` does not have a `toUpperCase` method.
     ```

   **Difference**:
   - `Error` is the base class for general errors.
   - `TypeError` is a specialized error that specifically deals with invalid data types or operations that cannot be performed on the given data type.

---


## **7. JavaScript Modules**
Here are the answers to the next set of JavaScript questions:

### **66. What is the difference between `import` and `require` in JavaScript?**
   - **`import`**: The `import` statement is part of the **ES6 Modules** (ESM) system. It is used to load modules in a more structured and standardized way in modern JavaScript (especially for client-side JavaScript and modules in Node.js with ES module support).
     - Syntax:
       ```javascript
       import { myFunction } from './myModule.js';
       ```
     - It is **asynchronous** and **hoisted** (meaning imports are resolved before the code is executed).
     - `import` only works with JavaScript modules that use the `export` syntax.

   - **`require`**: The `require` function is used in the **CommonJS** module system (mainly in Node.js for server-side code).
     - Syntax:
       ```javascript
       const myModule = require('./myModule');
       ```
     - It is **synchronous** and **not hoisted** (you must call `require()` before using the imported values).
     - CommonJS uses `module.exports` and `exports` to export objects and functions.

   **Key Differences**:
   - `import` is part of ES6 Modules, whereas `require` is used with CommonJS.
   - `import` is asynchronous and allows static analysis (more optimized for bundlers), while `require` is synchronous and used mostly on the server side.
   - `import` has better support for tree-shaking (removing unused code).

### **67. What is the purpose of the `export` keyword in JavaScript?**
   The **`export`** keyword is used to make functions, objects, or variables accessible to other files or modules. It is a way to define which parts of a module should be shared or exposed.

   - **Named export**: Allows you to export multiple items from a module.
     ```javascript
     // myModule.js
     export const name = 'Alice';
     export function greet() {
       console.log('Hello!');
     }
     ```

   - **Default export**: You can use the default keyword to export a single value, function, or object.
     ```javascript
     // myModule.js
     export default function() {
       console.log('This is a default export');
     }
     ```

   The **`export`** keyword helps to separate the module's internal logic from what is made available to other parts of the program, providing a clean and modular code structure.


### 44. **What are JavaScript arrays and how do you manipulate them?**

An **array** is a collection of ordered values (elements). Arrays in JavaScript are dynamic, meaning they can store any type of data and grow or shrink in size.

Common array manipulation methods include:

1. **`push()`**: Adds elements to the end of an array.
   ```javascript
   let arr = [1, 2, 3];
   arr.push(4); // [1, 2, 3, 4]
   ```

2. **`pop()`**: Removes the last element from an array.
   ```javascript
   arr.pop(); // [1, 2, 3]
   ```

3. **`shift()`**: Removes the first element from an array.
   ```javascript
   arr.shift(); // [2, 3]
   ```

4. **`unshift()`**: Adds elements to the beginning of an array.
   ```javascript
   arr.unshift(0); // [0, 2, 3]
   ```

5. **`splice()`**: Adds or removes elements at a specific index.
   ```javascript
   arr.splice(1, 1, 10); // [0, 10, 3] (removes 2 and adds 10 at index 1)
   ```

6. **`map()`**: Creates a new array by applying a function to each element.
   ```javascript
   const doubled = arr.map(x => x * 2); // [0, 20, 6]
   ```

7. **`filter()`**: Creates a new array with elements that pass a test.
   ```javascript
   const even = arr.filter(x => x % 2 === 0); // [0, 10]
   ```

8. **`forEach()`**: Iterates over all elements in the array.
   ```javascript
   arr.forEach(item => console.log(item));
   ```

### **68. How do you implement modules in JavaScript?**
   JavaScript modules can be implemented using **ES6 modules** (introduced in ECMAScript 2015). A module is simply a file that can export its code (functions, variables, or objects) to be used by other files. To implement modules, you need to use `export` and `import` to share and receive functionality.

   - **Exporting**:
     You can either use **named exports** or **default exports** to share functionality from a module.

     - **Named exports**:
       ```javascript
       // myModule.js
       export const foo = 'bar';
       export function greet() {
         console.log('Hello!');
       }
       ```

     - **Default export**:
       ```javascript
       // myModule.js
       export default function() {
         console.log('This is the default function');
       }
       ```

   - **Importing**:
     To use the exported code, you need to import it into another module.
     
     - **Named imports**:
       ```javascript
       // anotherModule.js
       import { foo, greet } from './myModule.js';
       console.log(foo); // bar
       greet(); // Hello!
       ```

     - **Default import**:
       ```javascript
       // anotherModule.js
       import myFunction from './myModule.js';
       myFunction(); // This is the default function
       ```

   JavaScript modules can be used in both client-side (in modern browsers) and server-side (Node.js) environments, though the syntax and support may vary.

### **69. What is the `default` export in JavaScript modules?**
   The **`default` export** allows you to export a single entity from a module. When a module has a default export, you can import it without using curly braces `{}`.

   - **Example of default export**:
     ```javascript
     // myModule.js
     export default function greet() {
       console.log('Hello!');
     }
     ```

   - **Importing the default export**:
     ```javascript
     // anotherModule.js
     import greet from './myModule.js';
     greet(); // Hello!
     ```

   You can only have one **default export** per module, but you can have multiple named exports alongside it.

### **70. What are the benefits of using modules in JavaScript?**
   The use of modules in JavaScript provides several benefits:

   - **Encapsulation**: Modules allow you to encapsulate your code, hiding implementation details and exposing only the necessary functionality.
   - **Maintainability**: By dividing your code into smaller, reusable modules, you make your codebase easier to maintain and test.
   - **Reusability**: Modules can be easily reused in different parts of your project or across different projects.
   - **Avoiding global namespace pollution**: Using modules prevents the need to put everything in the global scope, which can lead to naming conflicts.
   - **Code organization**: Modules help to organize code in a more logical and modular way, making it easier to navigate and collaborate in large codebases.
   - **Tree-shaking**: With modern bundlers like Webpack, unused code can be removed automatically (tree-shaking), leading to smaller bundle sizes and improved performance.

   Overall, JavaScript modules promote better software architecture by enabling separation of concerns and better code organization.


### 45. **How do you make an AJAX request in JavaScript?**

An **AJAX** (Asynchronous JavaScript and XML) request allows you to fetch data from a server without refreshing the page. In JavaScript, you can use the `XMLHttpRequest` object or the `Fetch API`.

- **Using `XMLHttpRequest`**:
  ```javascript
  const xhr = new XMLHttpRequest();
  xhr.open("GET", "https://api.example.com/data", true);
  xhr.onload = function() {
    if (xhr.status === 200) {
      console.log(xhr.responseText);
    }
  };
  xhr.send();
  ```

- **Using `Fetch API` (modern approach)**:
  ```javascript
  fetch("https://api.example.com/data")
    .then(response => response.json())
    .then(data => console.log(data))
    .catch(error => console.error("Error:", error));
  ```

---

### 46. **What is the Fetch API in JavaScript?**

The **Fetch API** is a modern and more flexible way to make HTTP requests in JavaScript. It returns a **Promise** and provides a simple interface to handle network requests.

Example:
```javascript
fetch('https://api.example.com/data')
  .then(response => response.json())
  .then(data => console.log(data))
  .catch(error => console.log('Error:', error));
```

The `fetch()` function accepts the URL and an optional configuration object, which allows you to specify the HTTP method, headers, body, etc.

---

### 47. **Explain the concept of a single-page application (SPA).**

A **Single-Page Application (SPA)** is a web application or website that dynamically updates the content of a single HTML page, without loading a new page from the server. SPAs provide a smooth and fast user experience by avoiding full page reloads.

- **How it works**: SPAs use AJAX or Fetch API to load content and interact with the server asynchronously. The page is loaded once, and navigation happens by updating the URL and dynamically rendering new content via JavaScript.

- **Popular SPA frameworks**: React, Angular, and Vue.js.

Example:
- When you click on a navigation link, JavaScript updates the content without reloading the entire page.


## **8. Browser and DOM**
Here are the answers to the next set of JavaScript questions:

### **71. What is the DOM (Document Object Model) in JavaScript?**
   The **Document Object Model (DOM)** is an interface that allows JavaScript to interact with and manipulate the structure of an HTML or XML document. It represents the document as a tree of nodes, where each node is an object corresponding to part of the document (elements, attributes, text, etc.).

   In simpler terms, the DOM provides a way for JavaScript to access and modify the content, structure, and style of web pages dynamically. Every HTML element on a page is represented as a node in the DOM.

   Example:
   ```html
   <div id="myDiv">Hello, World!</div>
   ```
   In JavaScript, you can manipulate this element:
   ```javascript
   const myDiv = document.getElementById('myDiv');
   myDiv.textContent = 'New Text';
   ```

### **72. How do you manipulate the DOM using JavaScript?**
   Manipulating the DOM in JavaScript involves accessing and changing elements of the page. Here are some common operations:

   - **Selecting elements**: 
     - `getElementById()`, `getElementsByClassName()`, `getElementsByTagName()`, `querySelector()`, `querySelectorAll()`.
   - **Changing content**: 
     - `textContent`, `innerText`, `innerHTML`.
   - **Changing attributes**: 
     - `setAttribute()`, `getAttribute()`.
   - **Creating elements**: 
     - `createElement()`, `appendChild()`, `removeChild()`, `insertBefore()`.
   - **Adding/Removing classes**: 
     - `classList.add()`, `classList.remove()`, `classList.toggle()`.
   - **Event handling**: 
     - `addEventListener()`.

   Example:
   ```javascript
   const button = document.querySelector('button');
   button.addEventListener('click', () => {
     const div = document.createElement('div');
     div.textContent = 'New content';
     document.body.appendChild(div);
   });
   ```

### 52. **What is the difference between `Object.freeze()` and `Object.seal()` in JavaScript?**

- **`Object.freeze()`**: Freezes an object, preventing new properties from being added, existing properties from being removed, and existing properties from being modified (i.e., making the object immutable).
  ```javascript
  const obj = { name: "Alice" };
  Object.freeze(obj);
  obj.name = "Bob"; // This will not change the property value
  console.log(obj.name); // "Alice"
  ```

- **`Object.seal()`**: Seals an object, preventing new properties from being added or existing properties from being deleted, but allows modification of the existing properties.
  ```javascript
  const obj = { name: "Alice" };
  Object.seal(obj);
  obj.name = "Bob"; // This will change the property value
  delete obj.name; // This will not delete the property
  console.log(obj.name); // "Bob"
  ```

In summary:
- **`freeze()`**: Prevents changes to properties, making them immutable.
- **`seal()`**: Prevents property addition/removal but allows modification of values.


### **75. How do you prevent the default action of an event in JavaScript?**
   You can prevent the default behavior of an event by calling the **`preventDefault()`** method on the event object. This is commonly used to stop the browser’s default behavior, like preventing form submission or link navigation.

   Example:
   ```javascript
   document.querySelector('form').addEventListener('submit', (event) => {
     event.preventDefault(); // Prevents form submission
     console.log('Form submission prevented');
   });
   ```

### **76. What is the difference between `addEventListener()` and `onclick`?**
   - **`addEventListener()`**: This method allows you to add multiple event listeners to an element, enabling multiple functions to handle the same event. It is more flexible and allows you to specify the phase of event propagation (capturing or bubbling).

     Example:
     ```javascript
     element.addEventListener('click', function() {
       console.log('Clicked!');
     });
     ```

   - **`onclick`**: The `onclick` property can only be used to assign a single event handler to an element. If you assign a new function to `onclick`, it will replace the existing one.

     Example:
     ```javascript
     element.onclick = function() {
       console.log('Clicked!');
     };
     ```

   **Key Difference**:
   - `addEventListener()` can handle multiple events for the same element, while `onclick` can only bind one event handler.

### **77. What is `localStorage` and `sessionStorage` in JavaScript?**
   Both **`localStorage`** and **`sessionStorage`** are used to store data on the client-side in the browser's storage, but they differ in terms of persistence:

   - **`localStorage`**: Stores data persistently with no expiration time. The data remains available even after the browser is closed and reopened.
     - Example:
       ```javascript
       localStorage.setItem('name', 'Alice');
       const name = localStorage.getItem('name');
       ```

   - **`sessionStorage`**: Stores data for the duration of the page session (as long as the browser tab is open). The data is cleared when the tab or browser is closed.
     - Example:
       ```javascript
       sessionStorage.setItem('name', 'Alice');
       const name = sessionStorage.getItem('name');


### 85. **What is the difference between `localStorage` and `cookies` in JavaScript?**

- **`localStorage`**:
  - Stores data on the client-side with no expiration time.
  - Data persists even after the browser is closed and reopened.
  - Can store up to 5-10 MB of data.
  - Data is only accessible from the same origin.
  - Syntax: `localStorage.setItem('key', 'value')`.

- **Cookies**:
  - Store small pieces of data (max 4KB).
  - Can have an expiration date.
  - Sent with every HTTP request to the server, making them slower than `localStorage`.
  - Often used for session management or storing user preferences.
  - Syntax: `document.cookie = "key=value; expires=date; path=/"`.


### 87. **How do you handle CORS (Cross-Origin Resource Sharing) in JavaScript?**

**CORS** is a security feature that restricts web pages from making requests to a domain other than the one from which the page was served. To handle CORS in JavaScript, you must configure the server to allow cross-origin requests.

- **On the server-side**: Add appropriate CORS headers, like `Access-Control-Allow-Origin`, to the HTTP response.
  
- **In the browser**: If you are making a request from the client, you can set the `mode` of the request to `'cors'` or `'no-cors'` depending on the scenario.

**Example (fetch with CORS):**

```javascript
fetch('https://api.example.com/data', {
  method: 'GET',
  headers: {
    'Content-Type': 'application/json'
  },
  mode: 'cors'
})
.then(response => response.json())
.then(data => console.log(data))
.catch(error => console.error('Error:', error));
```

### **78. How do you make an AJAX request in JavaScript?**
   AJAX (Asynchronous JavaScript and XML) is a technique used to make asynchronous requests to the server without refreshing the page. The simplest way is using **`XMLHttpRequest`** or **`Fetch API`**.

   Example using **`XMLHttpRequest`**:
   ```javascript
   const xhr = new XMLHttpRequest();
   xhr.open('GET', 'https://api.example.com/data', true);
   xhr.onload = () => {
     if (xhr.status === 200) {
       console.log(xhr.responseText);
     }
   };
   xhr.send();
   ```

   Example using **`Fetch API`** (modern approach):
   ```javascript
   fetch('https://api.example.com/data')
     .then(response => response.json())
     .then(data => console.log(data))
     .catch(error => console.error('Error:', error));
   ```

### **79. What are `XMLHttpRequest` and `Fetch` API in JavaScript?**
   - **`XMLHttpRequest`**: This is the older way to make asynchronous requests in JavaScript. It is used to send HTTP requests to the server and handle responses asynchronously.
   - **`Fetch API`**: This is a modern, Promise-based alternative to `XMLHttpRequest`. It provides a more flexible and cleaner syntax for handling HTTP requests and responses, supporting asynchronous operations.

   **Difference**:
   - `Fetch` is Promise-based, making it easier to work with asynchronous code compared to `XMLHttpRequest`, which uses callbacks.
   - `Fetch` also allows better handling of response types, such as JSON, and avoids some of the complexity of `XMLHttpRequest`.

### 84. **What is the `Function.prototype.bind()` method in JavaScript?**

**`bind()`** creates a new function that, when called, has its `this` value set to the specified context, and any initial arguments passed to `bind()` are pre-set.

**Use case**: Changing the context of a function while preserving its original behavior.

**Example**:

```javascript
const person = {
  name: 'John',
  greet() {
    console.log('Hello, ' + this.name);
  }
};

const greetJohn = person.greet.bind(person);
greetJohn(); // 'Hello, John'
```

Here, `bind()` ensures that the `this` inside `greetJohn` refers to the `person` object.


### **80. How do you handle cookies in JavaScript?**
   In JavaScript, cookies can be accessed and modified using the `document.cookie` property. To create, read, or delete cookies, you can set the cookie string in the format `name=value`, optionally with additional attributes like `expires`, `path`, and `domain`.

   Example:
   - **Creating a cookie**:
     ```javascript
     document.cookie = "username=JohnDoe; expires=Thu, 18 Dec 2024 12:00:00 UTC; path=/";
     ```

   - **Reading cookies**:
     ```javascript
     const cookies = document.cookie;
     console.log(cookies); // Displays all cookies as a string
     ```

   - **Deleting a cookie**:
     ```javascript
     document.cookie = "username=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/";
     ```

   **Note**: Cookies are small pieces of data stored in the user's browser and sent with each HTTP request to the same domain.


### 98. **What is functional programming in JavaScript? How is it different from object-oriented programming?**

- **Functional Programming**:
  Functional programming (FP) is a programming paradigm where you treat computation as the evaluation of mathematical functions and avoid changing state and mutable data. It focuses on immutability, higher-order functions, pure functions, and declarative code.

  **Features of FP**:
  - **Pure functions**: Functions that always return the same output for the same input and have no side effects.
  - **Immutability**: Data is not modified, but instead, new data is returned.
  - **First-class functions**: Functions can be passed as arguments and returned as values.
  - **Higher-order functions**: Functions that take other functions as arguments or return functions.

  **Example**:

  ```javascript
  const add = (a, b) => a + b;
  const multiply = (a, b) => a * b;

  const result = multiply(add(2, 3), 4);  // Using FP
  ```

- **Object-Oriented Programming (OOP)**:
  OOP is based on objects and classes. It focuses on data and methods that operate on that data, organizing code around objects that represent real-world entities.

  **Features of OOP**:
  - **Classes and objects**: Encapsulating data and behavior into objects.
  - **Inheritance**: Allowing classes to inherit properties and methods from other classes.
  - **Encapsulation**: Hiding the internal state and requiring all interaction to be done through methods.
  - **Polymorphism**: Allowing objects of different classes to be treated as objects of a common superclass.

  **Example**:

  ```javascript
  class Animal {
    constructor(name) {
      this.name = name;
    }
    speak() {
      console.log(this.name + ' makes a sound');
    }
  }

  const dog = new Animal('Rex');
  dog.speak();
  ```

**Key Differences**:
- **OOP** is about organizing code into objects and their interactions, while **FP** is about using pure functions and avoiding shared state.
- OOP typically uses mutable state, while FP emphasizes immutability.

---

### 99. **What is an abstract class in JavaScript?**

JavaScript does not have built-in support for **abstract classes** in the same way that languages like Java or C# do. However, you can simulate abstract classes by creating a class with methods that are not implemented, and then using subclasses to implement those methods.

**Example**:

```javascript
class Animal {
  constructor(name) {
    if (this.constructor === Animal) {
      throw new Error("Abstract class cannot be instantiated.");
    }
    this.name = name;
  }

  speak() {
    throw new Error("Method 'speak()' must be implemented.");
  }
}

class Dog extends Animal {
  speak() {
    console.log(`${this.name} barks`);
  }
}

const dog = new Dog('Rex');
dog.speak();  // Rex barks

// const animal = new Animal('Generic');  // Error: Abstract class cannot be instantiated.
```

Here, `Animal` is treated as an abstract class by throwing an error if you try to instantiate it directly. Subclasses like `Dog` implement the abstract `speak()` method.


## **9. Performance Optimization**
Here are the answers to the next set of JavaScript questions:

### **81. What are memory leaks in JavaScript, and how do you prevent them?**
   **Memory leaks** occur when the memory that is no longer in use (for example, unused objects or variables) is not properly released, leading to unnecessary memory consumption that could eventually slow down the application or cause it to crash.

   **Common causes of memory leaks** in JavaScript include:
   - **Global variables**: Variables not properly scoped, especially when they are unintentionally declared in the global scope.
   - **Event listeners**: Not removing event listeners that are no longer needed, leading to retaining references to DOM elements.
   - **Closures**: Functions holding references to outer scope variables that are no longer necessary.
   - **Detached DOM elements**: When elements are removed from the DOM but are still referenced by JavaScript, they remain in memory.

   **How to prevent memory leaks**:
   - **Proper variable scoping**: Use `let`, `const`, or block-level scoping to avoid unnecessary global variables.
   - **Remove event listeners**: Always remove event listeners with `removeEventListener()` when they are no longer needed.
   - **Clear intervals/timeouts**: Use `clearInterval()` or `clearTimeout()` to cancel any setInterval or setTimeout when they are not required.
   - **Avoid circular references**: Ensure objects don't reference each other in such a way that they prevent garbage collection.
   - **Garbage collection**: JavaScript's automatic garbage collection should handle most memory management tasks, but developers should be mindful of potential issues that prevent it.

   **Example**:
   ```javascript
   const element = document.getElementById('myElement');
   const handleClick = () => {
     console.log('Element clicked');
   };
   element.addEventListener('click', handleClick);

   // When no longer needed, remove the event listener
   element.removeEventListener('click', handleClick);
   ```

### 72. **What are decorators in JavaScript?**

**Decorators** are a special kind of declaration that can be used to modify the behavior of a class, method, accessor, property, or parameter in JavaScript. They are usually used in frameworks like TypeScript, Angular, or Babel, as part of the class syntax.

Decorators are functions that are applied to a class or method and can be used to add additional behavior or modify how things work.

**Example (in TypeScript):**

```typescript
function log(target: any, key: string) {
  console.log(`Method ${key} is called`);
}

class MyClass {
  @log
  sayHello() {
    console.log("Hello!");
  }
}

const obj = new MyClass();
obj.sayHello();
```

In the above example, the `@log` decorator is applied to the `sayHello` method, which will log every time the method is invoked.

### **82. How can you optimize the performance of a JavaScript application?**
   Optimizing the performance of a JavaScript application can involve several strategies, including:

   - **Minimize DOM manipulations**: Access the DOM as little as possible. Batch DOM updates together to minimize reflows and repaints.
   - **Use event delegation**: Instead of attaching event listeners to every single item, attach one listener to a parent and handle events at the parent level.
   - **Debounce and throttle**: Limit the rate at which functions like scroll or resize events are executed to reduce the load.
   - **Asynchronous operations**: Use `async/await`, `Promise`, or web workers to handle long-running operations in the background.
   - **Lazy loading**: Load non-essential resources only when they are needed (e.g., images, scripts, etc.).
   - **Minify and bundle assets**: Reduce the size of your JavaScript and CSS files by minifying and bundling them.
   - **Optimize loops and recursion**: Avoid unnecessary computations and deep recursion. Use `for` loops or optimized methods like `map()`, `reduce()`.
   - **Use requestAnimationFrame**: For animations or visual updates, `requestAnimationFrame` should be used instead of `setTimeout` or `setInterval` to optimize rendering.
   - **Leverage caching**: Use browser caching, service workers, or cache-control headers for assets that don't change often.


### 82. **What is the significance of `requestAnimationFrame()` in JavaScript?**

**`requestAnimationFrame()`** is used for creating smooth animations in the browser. It tells the browser to call a specified function before the next repaint, which is typically around 60 frames per second (fps). Using `requestAnimationFrame()` helps improve performance and ensures that animations are synchronized with the display refresh rate.

It is more efficient than using `setTimeout` or `setInterval`, as it allows the browser to optimize the frame rate and avoid unnecessary repaints.

**Example**:

```javascript
function animate() {
  // Update animation logic
  console.log("Animating...");
  requestAnimationFrame(animate);
}

requestAnimationFrame(animate);
```

This continuously animates by calling the `animate` function before each repaint.

### **83. What is lazy loading in JavaScript?**
   **Lazy loading** is a technique where resources (such as images, videos, or even JavaScript files) are loaded only when they are needed. This can significantly reduce the initial load time of the application, improving the overall performance.

   **Lazy loading use cases**:
   - **Images**: Only load images when they enter the viewport.
   - **JavaScript modules**: Load JavaScript modules only when a user interacts with certain parts of the page, like clicking a button or scrolling.

   **How to implement lazy loading**:
   - For images:
     ```html
     <img src="placeholder.jpg" data-src="actual-image.jpg" class="lazyload">
     ```

     ```javascript
     // Lazy load script
     document.addEventListener('DOMContentLoaded', () => {
       const images = document.querySelectorAll('img.lazyload');
       const observer = new IntersectionObserver((entries) => {
         entries.forEach(entry => {
           if (entry.isIntersecting) {
             entry.target.src = entry.target.dataset.src;
             entry.target.classList.remove('lazyload');
             observer.unobserve(entry.target);
           }
         });
       });
       images.forEach(img => observer.observe(img));
     });
     ```

   - For JavaScript modules:
     ```javascript
     document.getElementById('loadButton').addEventListener('click', () => {
       import('./myModule.js').then(module => {
         module.doSomething();
       });
     });
     ```

### **84. How do you improve the rendering performance of a website using JavaScript?**
   Improving rendering performance is critical to making your website feel fast and responsive. Here are strategies to improve the rendering performance of a website:

   - **Minimize reflows and repaints**: Reflows occur when changes in DOM elements (e.g., size, layout) cause the browser to recalculate styles and render. Avoid making multiple changes to the DOM in a single event handler.
   - **Use `requestAnimationFrame` for animations**: Instead of using `setTimeout` or `setInterval`, use `requestAnimationFrame` for smoother animations that sync with the browser’s rendering cycle.
   - **Use CSS transitions and animations instead of JavaScript**: CSS is optimized for animations and transitions, whereas JavaScript animations may cause performance issues.
   - **Avoid large complex DOM trees**: Minimize the number of elements on a page to reduce the overhead of rendering.
   - **Use GPU acceleration for heavy CSS effects**: Leverage CSS properties like `transform` and `opacity`, which are handled by the GPU and provide smoother animations.
   - **Limit the use of third-party scripts**: Each third-party script can add overhead to your page load time and render performance, so use them judiciously.

   Example:
   ```javascript
   // Using requestAnimationFrame for smoother animation
   function animate() {
     // Perform animation logic here
     requestAnimationFrame(animate);
   }
   animate();
   ```
  **Summary** 
  1. **Avoid memory leaks**: Use tools like Chrome DevTools to identify memory leaks.
  2. **Minimize DOM manipulation**: Batch DOM changes and avoid reflow/repaint.
  3. **Use event delegation**: Instead of adding event listeners to many elements, delegate events to a parent element.
  4. **Debouncing and throttling**: Limit the frequency of function executions (e.g., for scroll or resize events).
  5. **Lazy loading**: Load resources only when they are needed, such as images or scripts.
  6. **Use Web Workers**: Offload intensive computations to background threads.
  7. **Minify code**: Minify and compress JavaScript files to reduce file size and loading time.
  8. **Leverage caching**: Cache static assets and data to reduce redundant network requests.
  9. **Use `requestAnimationFrame`** for animations instead of `setTimeout`.


### 92. **What are JavaScript frameworks, and how do they differ from libraries?**

- **JavaScript frameworks**: A framework is a collection of prewritten code that provides a structure for building applications. It usually dictates the structure of your code and is opinionated about how things should be done. Frameworks provide a lot of functionality out of the box and are generally used to build large-scale applications (e.g., **Angular**, **Vue.js**, **React**).

  **Example**: Angular is a framework because it provides structure, conventions, and tools for building applications.

- **JavaScript libraries**: A library is a collection of functions and utilities that you can use to perform specific tasks (e.g., manipulating the DOM, making AJAX requests). Libraries are not opinionated, meaning they allow you to structure your application as you choose (e.g., **Lodash**, **jQuery**).

  **Example**: Lodash is a library because it provides utility functions, but it doesn't dictate how your app should be structured.

## **10. Testing and Tools**
Here are the answers to the next set of JavaScript testing-related questions:

### **86. What is unit testing in JavaScript?**
   **Unit testing** is the process of testing individual units or components of a program to ensure they work as expected. In JavaScript, unit testing typically involves testing small pieces of functionality, such as a single function, method, or class. The goal is to verify that each part of the application performs as intended, with tests written for specific scenarios to catch bugs early in development.

   **Example of unit test in JavaScript**:
   ```javascript
   // Function to be tested
   function add(a, b) {
     return a + b;
   }

   // Unit test for the add function
   describe('add function', () => {
     it('should add two numbers correctly', () => {
       assert.strictEqual(add(2, 3), 5);
     });
   });
   ```

### **87. What are some popular testing frameworks in JavaScript?**
   Some popular **JavaScript testing frameworks** include:

   1. **Jest**: A widely used testing framework developed by Facebook. It is great for unit and integration tests and supports mocking, code coverage, and asynchronous testing.
   2. **Mocha**: A flexible testing framework for JavaScript that can be used with assertion libraries like Chai. Mocha is highly configurable and widely used in Node.js testing.
   3. **Jasmine**: A behavior-driven testing framework for JavaScript. It provides an easy-to-use syntax and comes with built-in assertions.
   4. **Ava**: A minimalistic testing framework that supports parallel test execution, making it faster for large test suites.
   5. **QUnit**: A powerful testing framework, often used for testing jQuery code but can be used for any JavaScript testing.
   6. **Karma**: A test runner that can execute tests in multiple browsers and integrate with other frameworks like Jasmine, Mocha, and QUnit.

### **88. What is TDD (Test-Driven Development)?**
   **Test-Driven Development (TDD)** is a software development methodology where tests are written before the code itself. The process typically follows this cycle:
   1. **Write a failing test**: Write a test that defines the expected behavior of a small piece of functionality.
   2. **Write code to pass the test**: Write the minimum amount of code necessary to make the test pass.
   3. **Refactor**: Clean up the code while ensuring the test still passes.

   TDD helps ensure that the code is thoroughly tested from the outset, promotes better design, and encourages small, manageable changes.

   **TDD Example**:
   - **Test**: Write a test for a function that checks if a number is even.
   ```javascript
   it('should return true for even numbers', () => {
     assert.isTrue(isEven(2));
   });
   ```
   - **Code**: Write just enough code to pass the test.
   ```javascript
   function isEven(number) {
     return number % 2 === 0;
   }
   ```
   - **Refactor**: After the test passes, you can improve or optimize the code.

### **89. How do you write asynchronous tests in JavaScript?**
   Asynchronous tests are written to test functions or code that operate asynchronously (e.g., functions using `setTimeout`, promises, or async/await).

   - **Using Jest**:
     Jest supports asynchronous tests through `done`, `async/await`, and promises.

     **Example with `done`**:
     ```javascript
     it('should fetch data from the server', (done) => {
       fetchData((data) => {
         expect(data).toBe('some data');
         done();  // Inform Jest that the test is complete
       });
     });
     ```

     **Example with async/await**:
     ```javascript
     it('should fetch data asynchronously', async () => {
       const data = await fetchData();
       expect(data).toBe('some data');
     });
     ```

     **Example with Promises**:
     ```javascript
     it('should fetch data from the server', () => {
       return fetchData().then((data) => {
         expect(data).toBe('some data');
       });
     });
     ```

### **90. What is the difference between `assert` and `expect` in JavaScript testing?**
   - **`assert`** is a basic function provided by most assertion libraries, including Node.js’ built-in `assert` module and others like Chai. It provides a straightforward way to check whether an expression evaluates to true. If it doesn't, the test fails.

     **Example with `assert`**:
     ```javascript
     const assert = require('assert');
     assert.strictEqual(add(2, 3), 5);
     ```

   - **`expect`** is a more readable and expressive way to write assertions. It is commonly used in frameworks like Jest and Chai. `expect` provides a variety of chained methods (e.g., `.toBe()`, `.toEqual()`, `.toHaveLength()`) for more specific checks, making tests easier to read and understand.

     **Example with `expect`**:
     ```javascript
     test('adds 2 + 3 to equal 5', () => {
       expect(add(2, 3)).toBe(5);
     });
     ```

   **Key Differences**:
   - **`assert`** is simpler and more bare-bones, often requiring fewer lines of code but less flexibility for more complex assertions.
   - **`expect`** is more powerful and expressive, allowing for more readable tests with better syntax for complex assertions.


### 60. **How do you use regular expressions in JavaScript?**

Regular expressions (regex) are used for pattern matching and text manipulation in JavaScript. They can be created using the `RegExp` constructor or using a literal syntax.

- **Creating a regex**:
  ```javascript
  const regex = /hello/; // regex literal
  const regex2 = new RegExp("hello"); // using RegExp constructor
  ```

- **Using regex with methods**:
  - `test()`: Tests if a pattern exists in a string.
  ```javascript
  const regex = /hello/;
  console.log(regex.test("hello world")); // true
  ```

  - `exec()`: Executes a search for a match in a string and returns detailed information.
  ```javascript
  const regex = /hello/;
  console.log(regex.exec("hello world")); // ["hello"]
  ```

  - `match()`: Matches a string against a regex pattern and returns the result.
  ```javascript
  const str = "hello world";
  console.log(str.match(/world/)); // ["world"]
  ```

  - `replace()`: Replaces part of a string that matches a pattern.
  ```javascript
  const str = "hello world";
  console.log(str.replace(/world/, "JavaScript")); // "hello JavaScript"
  ```

### 61. **What are Web Workers in JavaScript?**

Web Workers are JavaScript scripts that run in the background, separate from the main thread, enabling you to perform tasks like calculations, data processing, or making network requests without blocking the UI or affecting performance.

- **Creating a Worker**:
  ```javascript
  const worker = new Worker('worker.js'); // worker.js is the script file

  worker.onmessage = function(event) {
    console.log('Message from worker:', event.data);
  };

  worker.postMessage('Hello, worker!');
  ```

In `worker.js`:
```javascript
self.onmessage = function(event) {
  console.log('Message from main thread:', event.data);
  self.postMessage('Hello from worker!');
};
```

Web Workers are useful for offloading CPU-intensive tasks and keeping the user interface responsive.


### 94. **What are service workers in JavaScript, and how do they work?**

**Service workers** are a type of web worker that runs in the background, separate from the web page, enabling features like caching, push notifications, and background synchronization. They allow for offline-first web experiences and can intercept network requests, enabling you to cache resources or serve content while offline.

**How they work**:
1. **Registration**: The service worker is registered in the browser via `navigator.serviceWorker.register()`.
2. **Installation**: After registration, the service worker goes through the installation phase, where it caches resources.
3. **Activation**: Once installed, it activates and begins intercepting network requests, allowing caching or returning responses from the cache.
4. **Fetching**: The service worker intercepts network requests and serves cached data, allowing for offline functionality.

**Example**:

```javascript
if ('serviceWorker' in navigator) {
  navigator.serviceWorker.register('/service-worker.js').then(function(registration) {
    console.log('Service Worker registered with scope:', registration.scope);
  }).catch(function(error) {
    console.log('Service Worker registration failed:', error);
  });
}
```

Service workers enable powerful features like offline support, caching strategies, and background tasks for modern web applications.

---

### 62. **How do you compare two objects in JavaScript?**

In JavaScript, objects are compared by reference, not by value. To compare objects by their values, you need to compare their properties individually.

- **Shallow comparison**: Compare the properties of two objects.
  ```javascript
  function shallowEqual(obj1, obj2) {
    return JSON.stringify(obj1) === JSON.stringify(obj2);
  }

  const obj1 = { name: "Alice", age: 25 };
  const obj2 = { name: "Alice", age: 25 };

  console.log(shallowEqual(obj1, obj2)); // true
  ```

- **Deep comparison**: You can implement or use libraries for deep comparison, which compares nested objects or arrays.
  ```javascript
  function deepEqual(a, b) {
    if (typeof a !== 'object' || typeof b !== 'object' || a === null || b === null) {
      return a === b;
    }

    const keysA = Object.keys(a);
    const keysB = Object.keys(b);

    if (keysA.length !== keysB.length) return false;

    for (let key of keysA) {
      if (!keysB.includes(key) || !deepEqual(a[key], b[key])) {
        return false;
      }
    }

    return true;
  }
  ```


### 64. **What are the differences between a function expression and a function declaration?**

- **Function Declaration**:
  ```javascript
  function greet() {
    console.log("Hello");
  }
  ```

  - Can be called before it's defined (due to hoisting).
  - Has function scope.

- **Function Expression**:
  ```javascript
  const greet = function() {
    console.log("Hello");
  };
  ```

  - Cannot be called before it's defined.
  - Is treated as an expression and can be anonymous.

In summary, **function declarations** are hoisted and can be called before their declaration, while **function expressions** are not hoisted and must be defined before usage.


### 65. **What is the difference between `slice()` and `splice()` methods in JavaScript?**

- **`slice()`**: Creates a shallow copy of a portion of an array and returns a new array without modifying the original array.
  ```javascript
  const arr = [1, 2, 3, 4, 5];
  const newArr = arr.slice(1, 4); // [2, 3, 4]
  ```

- **`splice()`**: Changes the original array by adding/removing elements at a specified index.
  ```javascript
  const arr = [1, 2, 3, 4, 5];
  arr.splice(2, 2, 'a', 'b'); // Removes 3 and 4, and adds 'a', 'b'
  console.log(arr); // [1, 2, 'a', 'b', 5]
  ```

### **11. JavaScript Best Practices**
Here are the answers to your final set of JavaScript-related questions:


### **93. What is the importance of code minification in JavaScript?**
   **Code minification** reduces the size of JavaScript files by removing unnecessary characters (like spaces, newlines, and comments), which results in faster loading times for web pages. Minification is crucial for:
   - **Reducing bandwidth usage**: Smaller files consume less data, leading to faster downloads, especially important for mobile users.
   - **Improving page load time**: Smaller files reduce the time it takes to load and execute JavaScript on the client-side.
   - **Obfuscation**: Minification can make it harder to reverse-engineer your code.

   Tools like `UglifyJS`, `Terser`, or Webpack's built-in minification capabilities are commonly used for this purpose.

### **94. How do you handle large-scale JavaScript applications?**
   Handling large-scale JavaScript applications involves using best practices to ensure the code is modular, maintainable, and scalable:
   - **Modularize code**: Break the application into smaller, reusable modules using **ES6 modules** or **CommonJS**.
   - **Use state management**: In large applications, managing state becomes important. Use tools like **Redux** or **Vuex** for centralized state management.
   - **Follow a component-based architecture**: In frameworks like React or Vue.js, structure your code around reusable components.
   - **Implement lazy loading**: Load only necessary parts of the application on demand to optimize initial loading time.
   - **Automate testing and build processes**: Use testing frameworks and build tools to automate the testing and deployment of your application.
   - **Use a task runner**: Tools like **Webpack** or **Parcel** help bundle your code, optimize assets, and handle different environments.

### **95. What are some security considerations when working with JavaScript?**
   Some key security considerations when working with JavaScript include:
   - **Cross-Site Scripting (XSS)**: Avoid injecting user input directly into the DOM. Use methods like `textContent` and `setAttribute` to safely insert dynamic content.
   - **Cross-Site Request Forgery (CSRF)**: Protect against CSRF by using tokens for state-changing requests and checking them on the server.
   - **Secure HTTP headers**: Set appropriate HTTP headers such as **Content Security Policy (CSP)**, **X-Content-Type-Options**, and **X-XSS-Protection**.
   - **Avoid eval()**: `eval()` can execute arbitrary code, making it a security risk. Avoid using it whenever possible.
   - **Use HTTPS**: Ensure all communication between the client and server happens over HTTPS to prevent man-in-the-middle attacks.

### **96. How do you manage JavaScript dependencies in a project?**
   To manage JavaScript dependencies, use **package managers** such as:
   - **npm (Node Package Manager)**: The most common package manager for JavaScript projects. It is used to install, update, and manage project dependencies.
   - **Yarn**: A fast, secure, and reliable alternative to npm, offering features like deterministic dependency resolution.
   - **Webpack**: A module bundler that also helps manage JavaScript dependencies by bundling various assets together.

   **Example** of installing a dependency with npm:
   ```bash
   npm install lodash
   ```

   Then, you can import the package in your code:
   ```javascript
   import _ from 'lodash';
   ```

### **97. What is the importance of modularity in JavaScript development?**
   **Modularity** is crucial in JavaScript development for several reasons:
   - **Reusability**: Code can be reused across different parts of the application, reducing duplication.
   - **Maintainability**: Smaller, self-contained modules are easier to maintain and test.
   - **Collaboration**: Multiple developers can work on different modules simultaneously without stepping on each other’s toes.
   - **Scalability**: Modular code makes it easier to scale the application by adding new features without breaking existing functionality.

   JavaScript provides native support for modules via **ES6 modules** (`import/export`), but you can also use **CommonJS** for server-side Node.js development.

### **98. How do you document JavaScript code effectively?**
   Documenting JavaScript code involves explaining the purpose, functionality, and usage of your code so that others (and your future self) can easily understand it. Best practices include:
   - **Inline comments**: Use comments to explain non-obvious logic or important decisions.
   - **Function/method documentation**: Use comments to describe what each function does, its parameters, and its return value.
   - **JSDoc**: JSDoc is a popular tool to generate documentation for your JavaScript code from specially formatted comments.

   **Example of JSDoc**:
   ```javascript
   /**
    * Adds two numbers together.
    * @param {number} a - The first number.
    * @param {number} b - The second number.
    * @returns {number} The sum of a and b.
    */
   function add(a, b) {
     return a + b;
   }
   ```

### **99. How do you ensure cross-browser compatibility with JavaScript?**
   Ensuring **cross-browser compatibility** involves writing code that works consistently across all major browsers:
   - **Use feature detection**: Avoid browser detection. Use libraries like **Modernizr** to check if a feature is supported by the browser.
   - **Polyfills**: Use polyfills to add support for newer JavaScript features in older browsers.
   - **Test across multiple browsers**: Regularly test your code in different browsers (Chrome, Firefox, Safari, Internet Explorer, Edge, etc.).
   - **Use CSS prefixes**: For experimental CSS features, use vendor-specific prefixes (e.g., `-webkit-` for Chrome/Safari).
   - **Avoid browser-specific quirks**: Be mindful of known differences in JavaScript engines across browsers and avoid relying on browser-specific behaviors.
   - **Use CSS resets**: Normalize styles with CSS resets or libraries like **Normalize.css** to address cross-browser style inconsistencies.

### **100. What are design patterns in JavaScript?**
   **Design patterns** are general solutions to commonly occurring problems in software design. In JavaScript, common design patterns include:
   - **Singleton**: Ensures a class has only one instance and provides a global access point to that instance.
   - **Module**: Organizes code into separate, reusable modules (e.g., using ES6 `import/export` or IIFEs).
   - **Observer**: Allows objects to observe the state changes in another object, useful for event-driven programming.
   - **Factory**: Creates objects without having to specify the exact class of object that will be created.
   - **Prototype**: Involves creating new objects based on an existing prototype object, leveraging inheritance.
   - **Decorator**: Allows the behavior of an object to be modified or extended dynamically.
   - **Command**: Encapsulates a request as an object, allowing for parameterization of clients with queues or requests.

   Design patterns help structure the code more efficiently and solve problems in a reusable manner.

# JavaScript Interview Questions & Answers

### 1. **What are the different data types in JavaScript?**
   JavaScript has two main categories of data types:

- **Primitive types** (immutable):
  1. **String**: A sequence of characters, e.g., `"Hello"`.
  2. **Number**: Represents both integer and floating-point numbers, e.g., `42`, `3.14`.
  3. **BigInt**: Large integers (used for numbers larger than `Number.MAX_SAFE_INTEGER`), e.g., `123456789012345678901234567890n`.
  4. **Boolean**: Represents logical values, e.g., `true`, `false`.
  5. **undefined**: A variable that has been declared but not assigned a value.
  6. **null**: Represents the intentional absence of any object value.
  7. **Symbol**: A unique identifier used to create object property keys that are unique, e.g., `Symbol('id')`.
  
- **Non-primitive types** (mutable):
  1. **Object**: A collection of key-value pairs, e.g., `{ name: 'Alice', age: 30 }`.
  2. **Array**: A special type of object used for ordered collections, e.g., `[1, 2, 3]`.

### 2. **What is the difference between `var`, `let`, and `const`?**
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

01. ### What is a pure function
    Pure functions do not depend on any state or data change during a program's execution, but only on their input arguments.

    To be considered pure, a function must pass two tests:

    1. Same inputs always return the same outputs, and
    2. No side-effects are produced after running it

    ```javascript
    //Pure function
    function add(a, b) {
      return a + b;
    }
    sumPure(1, 1); // OutPut: 2
    ```

  => This function takes two arguments and returns their sum. It does not modify any external state or produce any side effects. It will always return the same output when given the same input.


  ```javascript
    //Impure function
    let tax = 0.1;

    function calculateTotal(productPrice) {
      return productPrice + (productPrice * tax);
    }
    calculateTotal(4) // output : 4.4
  ```  

  => This function calculates the total price of a product by adding the product price to the product price multiplied by the tax rate.

  The tax rate is an external variable that can be modified outside of the function. Therefore, this function is impure because its output depends on an external state.

  If the tax rate is updated, the output of the function will change even if the input remains the same.
    

13. ### What is a higher order function

    Higher-order function is a function that accepts another function as an argument or returns a function as a return value or both.

    ```javascript
    const firstOrderFunc = () =>
      console.log("Hello, I am a First order function");
    const higherOrder = (ReturnFirstOrderFunc) => ReturnFirstOrderFunc();
    higherOrder(firstOrderFunc);
    ```

    ```javascript
    // Callback function, passed as a parameter in the higher order function
    function callbackFunction(){
        console.log('I am  a callback function');
    }

    // higher order function
    function higherOrderFunction(func){
        console.log('I am higher order function')
        func()
    }

    higherOrderFunction(callbackFunction);
    // Output
    // I am higher order function
    // I am  a callback function
    ```

    **[⬆ Back to Top](#table-of-contents)**

03. ### What is a callback function
    A callback function is a function passed into another function as an argument. This function is invoked inside the outer function to complete an action.
    Let's take a simple example of how to use callback function


    ```javascript
    function callbackFunction(name) {
      console.log("Hello " + name);
    }

    function outerFunction(callback) {
      // let name = prompt("Please enter your name.");
      let name = "Sentu Biswas";
      callback(name);
    }

    outerFunction(callbackFunction);
    // Output: Hello Sentu Biswas
    ```


04. ### What are closures

  A closure is a function that "remembers" the lexical environment in which it was created, even after the outer function has finished executing. i.e, It is an inner function that has access to the outer or enclosing function’s variables. The closure has three scope chains

  1. Own scope where variables defined between its curly brackets
  2. Outer function’s variables
  3. Global variables

  Let's take an example of closure concept,

  ```javascript
  function Welcome(name) {
    var greetingInfo = function (message) {
      console.log(message + " " + name);
    };
    return greetingInfo;
  }
  var myFunction = Welcome("John");
  myFunction("Welcome "); //Output: Welcome John
  myFunction("Hello Mr."); //output: Hello Mr.John
  ```

  As per the above code, the inner function(i.e, greetingInfo) has access to the variables in the outer function scope(i.e, Welcome) even after the outer function has returned.

### 4. **What is the `this` keyword in JavaScript?**
   The `this` keyword refers to the **context** in which a function is called. It can vary depending on how the function is invoked:
   - In a **regular function**, `this` refers to the global object (in non-strict mode) or `undefined` (in strict mode).
   - In an **object method**, `this` refers to the object the method is called on.
   - In **arrow functions**, `this` is **lexically bound** (it doesn't create its own `this` value but inherits it from the outer function).


05. ### What is the difference between synchronous and asynchronous

****Synchronous**** code runs in sequence. This means that each operation must wait for the previous one to complete before executing.

```javascript
function f1(){
  console.log("it is my 1st function");
}

function f2(){
  console.log("it is my 2nd function");
  f1();
}

f2();

// Output
// it is my 2nd function
// it is my 1st function
```

****Asynchronous code**** runs in parallel. This means that an operation can occur while another one is still being processed.

```javascript
function delay_Greeting() {
 setTimeout(function() {
   console.log("Hello, Ninja!");
 }, 5000); // Wait for 5000 milliseconds (5 seconds)
}

console.log("Start");
delay_Greeting();
console.log("End");

//output
// Start
// End
// Hello, Ninja!
```

### Key Differences:

| **Aspect**               | **Synchronous**                             | **Asynchronous**                              |
|--------------------------|---------------------------------------------|-----------------------------------------------|
| **Execution Flow**        | Tasks are executed one after another.      | Tasks can be executed concurrently.          |
| **Blocking**              | Blocks the program until the task finishes. | Does not block the program; can continue with other tasks. |
| **Complexity**            | Easier to understand and implement.        | More complex but more efficient for I/O-bound tasks. |
| **Efficiency**            | Not efficient for tasks that take time (e.g., I/O operations). | More efficient for handling many I/O-bound tasks concurrently. |
| **Common Use Cases**      | Simple tasks where timing is not critical. | Web servers, network requests, background jobs, etc. |
| **Example**               | A function waits for a file read to finish before continuing. | A function initiates a file read but continues doing other work until the file is read. |

### Use Cases

- **Synchronous**: 
  - Suitable for CPU-bound operations, where each task depends on the completion of the previous one (e.g., calculations, data transformations).
  - Simpler applications with fewer concurrency needs.

- **Asynchronous**: 
  - Ideal for I/O-bound operations, such as making network requests, reading from disk, or interacting with databases.
  - Used in environments that require high concurrency, like web servers, real-time applications, or when dealing with a large number of external systems.


05. ### What is the difference between Call, Apply and Bind

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


407. ### What is an async function

     An async function is a function declared with the `async` keyword which enables asynchronous, promise-based behavior to be written in a cleaner style by avoiding promise chains. These functions can contain zero or more `await` expressions.

     Let's take a below async function example,

     ```javascript
     async function logger() {
       let data = await fetch("http://someapi.com/users"); // pause until fetch returns
       console.log(data);
     }
     logger();
     ```

     ```javascript
     function resolveAfter2Seconds() {
        return new Promise((resolve) => {
          setTimeout(() => {
            resolve('resolved');
          }, 2000);
        });
      }

      async function asyncCall() {
        console.log('calling');
        const result = await resolveAfter2Seconds();
        console.log(result);
        // Expected output: "resolved"
      }
      asyncCall();

      // Output:
      // "calling"
      // "resolved"
     ```

     It is basically syntax sugar over ES2015 promises and generators.

     **[⬆ Back to Top](#table-of-contents)**

06. ### What is a promise
    A promise in Javascript is an object which represent the eventual completion or failure of an asynchronous operation. Promises represent a proxy for a value which are getting in some point in the future.

    A promise can have 3 states which are the following:

    ***Pending:*** This is the initial state of the promise, the promise is now waiting for either to be resolved or rejected. For example, when are reaching out to the web with an AJAX request and wrapping the request in a promise. Then the promise will be pending in the time window in which the request is not returned.


    ***Fulfilled:*** When the operation is completed succesfully, the promise is fulfilled. For example, when we are reaching out to be web using AJAX for some JSON data and wrapping it in a promise. When we are succesfully getting data back the promise is said to be fulfilled.


    ***Rejected:*** When the operation has failed, the promise is rejected. For example, when we are reaching out to be web using AJAX for some JSON data and wrapping it in a promise. When we are getting a 404 error the promise has been rejected.

    The syntax of Promise creation looks like below,

    ```javascript
    const promise = new Promise(function (resolve, reject) {
      // promise description
    });
    ```

    The usage of a promise would be as below,

    ```javascript
    let promise = new Promise((res, rej) => {
      console.log('synchronously executed');
      if (Math.random() > 0.5) {
        res('Success');
      } else {
        rej('Error');
      }
    })


    promise.then((val) => {
      console.log('asynchronously executed: ' + val);
    }).catch((err) => {
      console.log('asynchronously executed: ' + err);
    }).finally(() => {
      console.log('promise done executing');
    });
    console.log('last log');    

    // Output
    // synchronously executed
    // last log
    // asynchronously executed: Success
    // promise done executing
    ```


    Promises have three states:

    ***Pending:*** This is an initial state of the Promise before an operation begins

    ***Fulfilled:*** This state indicates that the specified operation was completed.
    
    ***Rejected:*** This state indicates that the operation did not complete. In this case an error value will be thrown.


    The action flow of a promise will be as below,

    ![Screenshot](images/promises.png)


63. ### What is promise chaining

    The process of executing a sequence of asynchronous tasks one after another using promises is known as Promise chaining. Let's take an example of promise chaining for calculating the final result,

    ```javascript
    new Promise(function (resolve, reject) {
      setTimeout(() => resolve(1), 1000);
    })
      .then(function (result) {
        console.log(result); // 1
        return result * 2;
      })
      .then(function (result) {
        console.log(result); // 2
        return result * 3;
      })
      .then(function (result) {
        console.log(result); // 6
        return result * 4;
      });
    ```

    In the above handlers, the result is passed to the chain of .then() handlers with the below work flow,

    1. The initial promise resolves in 1 second,
    2. After that `.then` handler is called by logging the result(1) and then return a promise with the value of result \* 2.
    3. After that the value passed to the next `.then` handler by logging the result(2) and return a promise with result \* 3.
    4. Finally the value passed to the last `.then` handler by logging the result(6) and return a promise with result \* 4.

    **[⬆ Back to Top](#table-of-contents)**

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

     **[⬆ Back to Top](#table-of-contents)**

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

401. ### What is RxJS

     RxJS (Reactive Extensions for JavaScript) is a library for implementing reactive programming using observables that makes it easier to compose asynchronous or callback-based code. It also provides utility functions for creating and working with observables.

     **[⬆ Back to Top](#table-of-contents)**

14. ### What is a unary function

    Unary function (i.e. monadic) is a function that accepts exactly one argument. It stands for a single argument accepted by a function.

    Let us take an example of unary function,

    ```javascript
    const unaryFunction = (a) => console.log(a + 10); // Add 10 to the given argument and display the value
    ```

    **[⬆ Back to Top](#table-of-contents)**

15. ### What is the currying function

    Currying is a powerful technique in JavaScript (and functional programming in general) that allows a function to be broken down into multiple smaller functions, each accepting one argument. It promotes code reusability and flexibility, particularly when working with functions that take multiple parameters.

    Let's take an example of n-ary function and how it turns into a currying function,

    ```javascript
    function multiply(a) {
      return function(b) {
        return function(c) {
          return a * b * c;
        };
      };
    }

    const result = multiply(2)(3)(4); // Output: 24
    console.log(result);
    ```

    ```javascript
    function handleEvent(eventType) {
      return function(message) {
        console.log(`${eventType}: ${message}`);
      };
    }

    const logClick = handleEvent('Click');
    const logSubmit = handleEvent('Submit');

    logClick('Button clicked!'); // Output: Click: Button clicked!
    logSubmit('Form submitted!'); // Output: Submit: Form submitted!
    ```

    Curried functions are great to improve **code reusability** and **functional composition**.

    **[⬆ Back to Top](#table-of-contents)**

   

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

     **[⬆ Back to Top](#table-of-contents)**

### What are the concepts introduced in OOPs?
Following are the concepts introduced in OOPs:

**Object** - A real-world entity having a particular state and behavior. We can define it as an instance of a class.

**Class** - A logical entity that defines the blueprint from which an object can be created or instantiated.

**Inheritance** - A concept that refers to an object gaining all the properties and behaviors of a parent object. It provides code reusability.

**Polymorphism** - A concept that allows a task to be performed in different ways. In Java, we use method overloading and method overriding to achieve polymorphism.

**Abstraction** - A concept that hides the internal details of an application and only shows the functionality. In Java, we use abstract class and interface to achieve abstraction.

**Encapsulation** - A concept that refers to the wrapping of code and data together into a single unit.

This is one of the very common coding interview questions, that often allows the interviewer to branch out into related topics based on the candidate’s answers

1. ### What are the possible ways to create objects in JavaScript

   There are many ways to create objects in javascript as below

   1. **Object constructor:**

      The simplest way to create an empty object is using the Object constructor. Currently this approach is not recommended.

      ```javascript
      var object = new Object();
      ```

      The `Object()` is a built-in constructor function so "new" keyword is not required. the above can be written as:

      ```javascript
      var object = Object();
      ```

   2. **Object's create method:**

      The create method of Object creates a new object by passing the prototype object as a parameter

      ```javascript
      var object = Object.create(null);
      ```

   3. **Object literal syntax:**

      The object literal syntax (or object initializer), is a comma-separated set of name-value pairs wrapped in curly braces.

      ```javascript
      var object = {
           name: "Sudheer",
           age: 34
      };

      Object literal property values can be of any data type, including array, function, and nested object.
      ```

      **Note:** This is an easiest way to create an object

   4. **Function constructor:**

      Create any function and apply the new operator to create object instances,

      ```javascript
      function Person(name) {
        this.name = name;
        this.age = 21;
      }
      var object = new Person("Sudheer");
      ```

   5. **Function constructor with prototype:**

      This is similar to function constructor but it uses prototype for their properties and methods,

      ```javascript
      function Person() {}
      Person.prototype.name = "Sudheer";
      var object = new Person();
      ```

      This is equivalent to an instance created with an object create method with a function prototype and then call that function with an instance and parameters as arguments.

      ```javascript
      function func() {}

      new func(x, y, z);
      ```

      **(OR)**

      ```javascript
      // Create a new instance using function prototype.
      var newInstance = Object.create(func.prototype)

      // Call the function
      var result = func.call(newInstance, x, y, z),

      // If the result is a non-null object then use it otherwise just use the new instance.
      console.log(result && typeof result === 'object' ? result : newInstance);
      ```

   6. **ES6 Class syntax:**

      ES6 introduces class feature to create the objects

      ```javascript
      class Person {
        constructor(name) {
          this.name = name;
        }
      }

      var object = new Person("Sudheer");
      ```

   7. **Singleton pattern:**

      A Singleton is an object which can only be instantiated one time. Repeated calls to its constructor return the same instance and this way one can ensure that they don't accidentally create multiple instances.

      ```javascript
      var object = new (function () {
        this.name = "Sudheer";
      })();
      ```

      **[⬆ Back to Top](#table-of-contents)**

5. ### What is the purpose of the array slice method

   The **slice()** method returns the selected elements in an array as a new array object. It selects the elements starting at the given start argument, and ends at the given optional end argument without including the last element. If you omit the second argument then it selects till the end.

   Some of the examples of this method are,

   ```javascript
   let arrayIntegers = [1, 2, 3, 4, 5];
   let arrayIntegers1 = arrayIntegers.slice(0, 2); // returns [1,2]
   let arrayIntegers2 = arrayIntegers.slice(2, 3); // returns [3]
   let arrayIntegers3 = arrayIntegers.slice(4); //returns [5]
   ```

   **Note:** Slice method won't mutate the original array but it returns the subset as a new array.

   **[⬆ Back to Top](#table-of-contents)**

6. ### What is the purpose of the array splice method

   The **splice()** method is used either adds/removes items to/from an array, and then returns the removed item. The first argument specifies the array position for insertion or deletion whereas the optional second argument indicates the number of elements to be deleted. Each additional argument is added to the array.

   Some of the examples of this method are,

   ```javascript
   let arrayIntegersOriginal1 = [1, 2, 3, 4, 5];
   let arrayIntegersOriginal2 = [1, 2, 3, 4, 5];
   let arrayIntegersOriginal3 = [1, 2, 3, 4, 5];

   let arrayIntegers1 = arrayIntegersOriginal1.splice(0, 2); // returns [1, 2]; original array: [3, 4, 5]
   let arrayIntegers2 = arrayIntegersOriginal2.splice(3); // returns [4, 5]; original array: [1, 2, 3]
   let arrayIntegers3 = arrayIntegersOriginal3.splice(3, 1, "a", "b", "c"); //returns [4]; original array: [1, 2, 3, "a", "b", "c", 5]
   ```

   **Note:** Splice method modifies the original array and returns the deleted array.

   **[⬆ Back to Top](#table-of-contents)**

7. ### What is the difference between slice and splice

   Some of the major difference in a tabular form

   | Slice                                        | Splice                                          |
   | -------------------------------------------- | ----------------------------------------------- |
   | Doesn't modify the original array(immutable) | Modifies the original array(mutable)            |
   | Returns the subset of original array         | Returns the deleted elements as array           |
   | Used to pick the elements from array         | Used to insert or delete elements to/from array |

   **[⬆ Back to Top](#table-of-contents)**

17. ### What is the purpose of the let keyword

    The `let` statement declares a **block scope local variable**. Hence the variables defined with let keyword are limited in scope to the block, statement, or expression on which it is used. Whereas variables declared with the `var` keyword used to define a variable globally, or locally to an entire function regardless of block scope.

    Let's take an example to demonstrate the usage,

    ```javascript
    let counter = 30;
    if (counter === 30) {
      let counter = 31;
      console.log(counter); // 31
    }
    console.log(counter); // 30 (because the variable in if block won't exist here)
    ```

    **[⬆ Back to Top](#table-of-contents)**

18. ### What is the difference between let and var

    You can list out the differences in a tabular format

    | var                                                   | let                         |
    | ----------------------------------------------------- | --------------------------- |
    | It is been available from the beginning of JavaScript | Introduced as part of ES6   |
    | It has function scope                                 | It has block scope          |
    | Variables will be hoisted                             | Hoisted but not initialized |

    Let's take an example to see the difference,

    ```javascript
    function userDetails(username) {
      if (username) {
        console.log(salary); // undefined due to hoisting
        console.log(age); // ReferenceError: Cannot access 'age' before initialization
        let age = 30;
        var salary = 10000;
      }
      console.log(salary); //10000 (accessible due to function scope)
      console.log(age); //error: age is not defined(due to block scope)
    }
    userDetails("John");
    ```

    **[⬆ Back to Top](#table-of-contents)**


23. ### How do you decode or encode a URL in JavaScript?

    `encodeURI()` function is used to encode an URL. This function requires a URL string as a parameter and return that encoded string.
    `decodeURI()` function is used to decode an URL. This function requires an encoded URL string as parameter and return that decoded string.

    **Note:** If you want to encode characters such as `/ ? : @ & = + $ #` then you need to use `encodeURIComponent()`.

    ```javascript
    let uri = "employeeDetails?name=john&occupation=manager";
    let encoded_uri = encodeURI(uri);
    let decoded_uri = decodeURI(encoded_uri);
    ```

    **[⬆ Back to Top](#table-of-contents)**

24. ### What is memoization

    Memoization is a programming technique which attempts to increase a function’s performance by caching its previously computed results. Each time a memoized function is called, its parameters are used to index the cache. If the data is present, then it can be returned, without executing the entire function. Otherwise the function is executed and then the result is added to the cache.
    Let's take an example of adding function with memoization,

    ```javascript
    const memoizAddition = () => {
      let cache = {};
      return (value) => {
        if (value in cache) {
          console.log("Fetching from cache");
          return cache[value]; // Here, cache.value cannot be used as property name starts with the number which is not a valid JavaScript  identifier. Hence, can only be accessed using the square bracket notation.
        } else {
          console.log("Calculating result");
          let result = value + 20;
          cache[value] = result;
          return result;
        }
      };
    };
    // returned function from memoizAddition
    const addition = memoizAddition();
    console.log(addition(20)); //output: 40 calculated
    console.log(addition(20)); //output: 40 cached
    ```

    **[⬆ Back to Top](#table-of-contents)**

25. ### What is Hoisting

    Hoisting is a JavaScript mechanism where variables, function declarations and classes are moved to the top of their scope before code execution. Remember that JavaScript only hoists declarations, not initialisation.
    Let's take a simple example of variable hoisting,

    ```javascript
    console.log(message); //output : undefined
    var message = "The variable Has been hoisted";
    ```

    The above code looks like as below to the interpreter,

    ```javascript
    var message;
    console.log(message);
    message = "The variable Has been hoisted";
    ```

    In the same fashion, function declarations are hoisted too

    ```javascript
    message("Good morning"); //Good morning

    function message(name) {
      console.log(name);
    }
    ```

    This hoisting makes functions to be safely used in code before they are declared.

    **[⬆ Back to Top](#table-of-contents)**

26. ### What are classes in ES6

    In ES6, Javascript classes are primarily syntactic sugar over JavaScript’s existing prototype-based inheritance.
    For example, the prototype based inheritance written in function expression as below,

    ```javascript
    function Bike(model, color) {
      this.model = model;
      this.color = color;
    }

    Bike.prototype.getDetails = function () {
      return this.model + " bike has" + this.color + " color";
    };
    ```

    Whereas ES6 classes can be defined as an alternative

    ```javascript
    class Bike {
      constructor(color, model) {
        this.color = color;
        this.model = model;
      }

      getDetails() {
        return this.model + " bike has" + this.color + " color";
      }
    }
    ```

    **[⬆ Back to Top](#table-of-contents)**


28. ### What are modules

    Modules refer to small units of independent, reusable code and also act as the foundation of many JavaScript design patterns. Most of the JavaScript modules export an object literal, a function, or a constructor

    **[⬆ Back to Top](#table-of-contents)**

30. ### What is scope in javascript

    Scope is the accessibility of variables, functions, and objects in some particular part of your code during runtime. In other words, scope determines the visibility of variables and other resources in areas of your code.

    **[⬆ Back to Top](#table-of-contents)**

31. ### What is a service worker

    A Service worker is basically a script (JavaScript file) that runs in the background, separate from a web page and provides features that don't need a web page or user interaction. Some of the major features of service workers are Rich offline experiences(offline first web application development), periodic background syncs, push notifications, intercept and handle network requests and programmatically managing a cache of responses.

    **[⬆ Back to Top](#table-of-contents)**


34. ### What is IndexedDB

    IndexedDB is a low-level API for client-side storage of larger amounts of structured data, including files/blobs. This API uses indexes to enable high-performance searches of this data.

    **[⬆ Back to Top](#table-of-contents)**

35. ### What is web storage

    Web storage is an API that provides a mechanism by which browsers can store key/value pairs locally within the user's browser, in a much more intuitive fashion than using cookies. The web storage provides two mechanisms for storing data on the client.

    1. **Local storage:** It stores data for current origin with no expiration date.
    2. **Session storage:** It stores data for one session and the data is lost when the browser tab is closed.

    **[⬆ Back to Top](#table-of-contents)**

36. ### What is a post message

    Post message is a method that enables cross-origin communication between Window objects.(i.e, between a page and a pop-up that it spawned, or between a page and an iframe embedded within it). Generally, scripts on different pages are allowed to access each other if and only if the pages follow same-origin policy(i.e, pages share the same protocol, port number, and host).

    **[⬆ Back to Top](#table-of-contents)**

37. ### What is a Cookie

    A cookie is a piece of data that is stored on your computer to be accessed by your browser. Cookies are saved as key/value pairs.
    For example, you can create a cookie named username as below,

    ```javascript
    document.cookie = "username=John";
    ```

    ![Screenshot](images/cookie.png)

    **[⬆ Back to Top](#table-of-contents)**

38. ### Why do you need a Cookie

    Cookies are used to remember information about the user profile(such as username). It basically involves two steps,

    1. When a user visits a web page, the user profile can be stored in a cookie.
    2. Next time the user visits the page, the cookie remembers the user profile.

    **[⬆ Back to Top](#table-of-contents)**

39. ### What are the options in a cookie

    There are few below options available for a cookie,

    1. By default, the cookie is deleted when the browser is closed but you can change this behavior by setting expiry date (in UTC time).

    ```javascript
    document.cookie = "username=John; expires=Sat, 8 Jun 2019 12:00:00 UTC";
    ```

    1. By default, the cookie belongs to a current page. But you can tell the browser what path the cookie belongs to using a path parameter.

    ```javascript
    document.cookie = "username=John; path=/services";
    ```

    **[⬆ Back to Top](#table-of-contents)**

40. ### How do you delete a cookie

    You can delete a cookie by setting the expiry date as a passed date. You don't need to specify a cookie value in this case.
    For example, you can delete a username cookie in the current page as below.

    ```javascript
    document.cookie =
      "username=; expires=Fri, 07 Jun 2019 00:00:00 UTC; path=/;";
    ```

    **Note:** You should define the cookie path option to ensure that you delete the right cookie. Some browsers doesn't allow to delete a cookie unless you specify a path parameter.

    **[⬆ Back to Top](#table-of-contents)**

41. ### What are the differences between cookie, local storage and session storage

    Below are some of the differences between cookie, local storage and session storage,

    | Feature                           | Cookie                             | Local storage    | Session storage     |
    | --------------------------------- | ---------------------------------- | ---------------- | ------------------- |
    | Accessed on client or server side | Both server-side & client-side     | client-side only | client-side only    |
    | Lifetime                          | As configured using Expires option | until deleted    | until tab is closed |
    | SSL support                       | Supported                          | Not supported    | Not supported       |
    | Maximum data size                 | 4KB                                | 5 MB             | 5MB                 |

    **[⬆ Back to Top](#table-of-contents)**

42. ### What is the main difference between localStorage and sessionStorage

    LocalStorage is the same as SessionStorage but it persists the data even when the browser is closed and reopened(i.e it has no expiration time) whereas in sessionStorage data gets cleared when the page session ends.

    **[⬆ Back to Top](#table-of-contents)**

43. ### How do you access web storage

    The Window object implements the `WindowLocalStorage` and `WindowSessionStorage` objects which has `localStorage`(window.localStorage) and `sessionStorage`(window.sessionStorage) properties respectively. These properties create an instance of the Storage object, through which data items can be set, retrieved and removed for a specific domain and storage type (session or local).
    For example, you can read and write on local storage objects as below

    ```javascript
    localStorage.setItem("logo", document.getElementById("logo").value);
    localStorage.getItem("logo");
    ```

    **[⬆ Back to Top](#table-of-contents)**

44. ### What are the methods available on session storage

    The session storage provided methods for reading, writing and clearing the session data

    ```javascript
    // Save data to sessionStorage
    sessionStorage.setItem("key", "value");

    // Get saved data from sessionStorage
    let data = sessionStorage.getItem("key");

    // Remove saved data from sessionStorage
    sessionStorage.removeItem("key");

    // Remove all saved data from sessionStorage
    sessionStorage.clear();
    ```

    **[⬆ Back to Top](#table-of-contents)**


55. ### Why do we need callbacks

    The callbacks are needed because javascript is an event driven language. That means instead of waiting for a response javascript will keep executing while listening for other events.
    Let's take an example with the first function invoking an API call(simulated by setTimeout) and the next function which logs the message.

    ```javascript
    function firstFunction() {
      // Simulate a code delay
      setTimeout(function () {
        console.log("First function called");
      }, 1000);
    }
    function secondFunction() {
      console.log("Second function called");
    }
    firstFunction();
    secondFunction();

    Output;
    // Second function called
    // First function called
    ```

    As observed from the output, javascript didn't wait for the response of the first function and the remaining code block got executed. So callbacks are used in a way to make sure that certain code doesn’t execute until the other code finishes execution.

    **[⬆ Back to Top](#table-of-contents)**

56. ### What is a callback hell

    Callback Hell is an anti-pattern with multiple nested callbacks which makes code hard to read and debug when dealing with asynchronous logic. The callback hell looks like below,

    ```javascript
    async1(function(){
        async2(function(){
            async3(function(){
                async4(function(){
                    ....
                });
            });
        });
    });
    ```

    **[⬆ Back to Top](#table-of-contents)**

57. ### What are server-sent events

    Server-sent events (SSE) is a server push technology enabling a browser to receive automatic updates from a server via HTTP connection without resorting to polling. These are a one way communications channel - events flow from server to client only. This has been used in Facebook/Twitter updates, stock price updates, news feeds etc.

    **[⬆ Back to Top](#table-of-contents)**


66. ### What is a strict mode in javascript

    Strict Mode is a new feature in ECMAScript 5 that allows you to place a program, or a function, in a “strict” operating context. This way it prevents certain actions from being taken and throws more exceptions. The literal expression `"use strict";` instructs the browser to use the javascript code in the Strict mode.

    ```javascript
    "use strict";
    x = 3.14; // This will cause an error because x is not declared
    ```

    **[⬆ Back to Top](#table-of-contents)**


71. ### What is typeof operator

    You can use the JavaScript typeof operator to find the type of a JavaScript variable. It returns the type of a variable or an expression.

    ```javascript
    typeof "John Abraham"; // Returns "string"
    typeof (1 + 2); // Returns "number"
    typeof [1, 2, 3]; // Returns "object" because all arrays are also objects
    ```

    **[⬆ Back to Top](#table-of-contents)**

72. ### What is undefined property

    The undefined property indicates that a variable has not been assigned a value, or declared but not initialized at all. The type of undefined value is undefined too.

    ```javascript
    var user; // Value is undefined, type is undefined
    console.log(typeof user); //undefined
    ```

    Any variable can be emptied by setting the value to undefined.

    ```javascript
    user = undefined;
    ```

    **[⬆ Back to Top](#table-of-contents)**

73. ### What is null value

    The value null represents the intentional absence of any object value. It is one of JavaScript's primitive values. The type of null value is object.
    You can empty the variable by setting the value to null.

    ```javascript
    var user = null;
    console.log(typeof user); //object
    ```

    **[⬆ Back to Top](#table-of-contents)**

74. ### What is the difference between null and undefined

    Below are the main differences between null and undefined,

    | Null                                                                                            | Undefined                                                                                               |
    | ----------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------- |
    | It is an assignment value which indicates that variable points to no object.                    | It is not an assignment value where a variable has been declared but has not yet been assigned a value. |
    | Type of null is object                                                                          | Type of undefined is undefined                                                                          |
    | The null value is a primitive value that represents the null, empty, or non-existent reference. | The undefined value is a primitive value used when a variable has not been assigned a value.            |
    | Indicates the absence of a value for a variable                                                 | Indicates absence of variable itself                                                                    |
    | Converted to zero (0) while performing primitive operations                                     | Converted to NaN while performing primitive operations                                                  |

    **[⬆ Back to Top](#table-of-contents)**

75. ### What is eval

    The eval() function evaluates JavaScript code represented as a string. The string can be a JavaScript expression, variable, statement, or sequence of statements.

    ```javascript
    console.log(eval("1 + 2")); //  3
    ```

    **[⬆ Back to Top](#table-of-contents)**

76. ### What is the difference between window and document

    Below are the main differences between window and document,

    | Window                                                                        | Document                                                                                      |
    | ----------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------- |
    | It is the root level element in any web page                                  | It is the direct child of the window object. This is also known as Document Object Model(DOM) |
    | By default window object is available implicitly in the page                  | You can access it via window.document or document.                                            |
    | It has methods like alert(), confirm() and properties like document, location | It provides methods like getElementById, getElementsByTagName, createElement etc              |

    **[⬆ Back to Top](#table-of-contents)**

77. ### How do you access history in javascript

    The window.history object contains the browser's history. You can load previous and next URLs in the history using back() and next() methods.

    ```javascript
    function goBack() {
      window.history.back();
    }
    function goForward() {
      window.history.forward();
    }
    ```

    **Note:** You can also access history without window prefix.

    **[⬆ Back to Top](#table-of-contents)**


79. ### What is isNaN

    The isNaN() function is used to determine whether a value is an illegal number (Not-a-Number) or not. i.e, This function returns true if the value equates to NaN. Otherwise it returns false.

    ```javascript
    isNaN("Hello"); //true
    isNaN("100"); //false
    ```

    **[⬆ Back to Top](#table-of-contents)**

80. ### What are the differences between undeclared and undefined variables

    Below are the major differences between undeclared(not defined) and undefined variables,

    | undeclared                                                                                  | undefined                                                                              |
    | ------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------- |
    | These variables do not exist in a program and are not declared                              | These variables declared in the program but have not assigned any value                |
    | If you try to read the value of an undeclared variable, then a runtime error is encountered | If you try to read the value of an undefined variable, an undefined value is returned. |

    **[⬆ Back to Top](#table-of-contents)**

81. ### What are global variables

    Global variables are those that are available throughout the length of the code without any scope. The var keyword is used to declare a local variable but if you omit it then it will become global variable

    ```javascript
    msg = "Hello"; // var is missing, it becomes global variable
    ```

    **[⬆ Back to Top](#table-of-contents)**

82. ### What are the problems with global variables

    The problem with global variables is the conflict of variable names of local and global scope. It is also difficult to debug and test the code that relies on global variables.

    **[⬆ Back to Top](#table-of-contents)**

83. ### What is NaN property

    The NaN property is a global property that represents "Not-a-Number" value. i.e, It indicates that a value is not a legal number. It is very rare to use NaN in a program but it can be used as return value for few cases

    ```javascript
    Math.sqrt(-1);
    parseInt("Hello");
    ```

    **[⬆ Back to Top](#table-of-contents)**

84. ### What is the purpose of isFinite function

    The isFinite() function is used to determine whether a number is a finite, legal number. It returns false if the value is +infinity, -infinity, or NaN (Not-a-Number), otherwise it returns true.

    ```javascript
    isFinite(Infinity); // false
    isFinite(NaN); // false
    isFinite(-Infinity); // false

    isFinite(100); // true
    ```

    **[⬆ Back to Top](#table-of-contents)**

85. ### What is an event flow

    Event flow is the order in which event is received on the web page. When you click an element that is nested in various other elements, before your click actually reaches its destination, or target element, it must trigger the click event for each of its parent elements first, starting at the top with the global window object.
    There are two ways of event flow

    1. Top to Bottom(Event Capturing)
    2. Bottom to Top (Event Bubbling)

    **[⬆ Back to Top](#table-of-contents)**

86. ### What is event bubbling

    Event bubbling is a type of event propagation where the event first triggers on the innermost target element, and then successively triggers on the ancestors (parents) of the target element in the same nesting hierarchy till it reaches the outermost DOM element.

    **[⬆ Back to Top](#table-of-contents)**

87. ### What is event capturing

    Event capturing is a type of event propagation where the event is first captured by the outermost element, and then successively triggers on the descendants (children) of the target element in the same nesting hierarchy till it reaches the innermost DOM element.

    **[⬆ Back to Top](#table-of-contents)**


89. ### How do you find operating system details

    The window.navigator object contains information about the visitor's browser OS details. Some of the OS properties are available under platform property,

    ```javascript
    console.log(navigator.platform);
    ```

    **[⬆ Back to Top](#table-of-contents)**

93. ### What are the pros and cons of promises over callbacks

    Below are the list of pros and cons of promises over callbacks,

    **Pros:**

    1. It avoids callback hell which is unreadable
    2. Easy to write sequential asynchronous code with .then()
    3. Easy to write parallel asynchronous code with Promise.all()
    4. Solves some of the common problems of callbacks(call the callback too late, too early, many times and swallow errors/exceptions)

    **Cons:**

    1. It makes little complex code
    2. You need to load a polyfill if ES6 is not supported

    **[⬆ Back to Top](#table-of-contents)**

94. ### What is the difference between an attribute and a property

    Attributes are defined on the HTML markup whereas properties are defined on the DOM. For example, the below HTML element has 2 attributes type and value,

    ```javascript
    <input type="text" value="Name:">
    ```

    You can retrieve the attribute value as below,

    ```javascript
    const input = document.querySelector("input");
    console.log(input.getAttribute("value")); // Good morning
    console.log(input.value); // Good morning
    ```

    And after you change the value of the text field to "Good evening", it becomes like

    ```javascript
    console.log(input.getAttribute("value")); // Good evening
    console.log(input.value); // Good evening
    ```

    **[⬆ Back to Top](#table-of-contents)**

95. ### What is same-origin policy

    The same-origin policy is a policy that prevents JavaScript from making requests across domain boundaries. An origin is defined as a combination of URI scheme, hostname, and port number. If you enable this policy then it prevents a malicious script on one page from obtaining access to sensitive data on another web page using Document Object Model(DOM).

    **[⬆ Back to Top](#table-of-contents)**

96. ### What is the purpose of void 0

    Void(0) is used to prevent the page from refreshing. This will be helpful to eliminate the unwanted side-effect, because it will return the undefined primitive value. It is commonly used for HTML documents that use href="JavaScript:Void(0);" within an `<a>` element. i.e, when you click a link, the browser loads a new page or refreshes the same page. But this behavior will be prevented using this expression.
    For example, the below link notify the message without reloading the page

    ```javascript
    <a href="JavaScript:void(0);" onclick="alert('Well done!')">
      Click Me!
    </a>
    ```

    **[⬆ Back to Top](#table-of-contents)**


100. ### What are events

     Events are "things" that happen to HTML elements. When JavaScript is used in HTML pages, JavaScript can `react` on these events. Some of the examples of HTML events are,

     1. Web page has finished loading
     2. Input field was changed
     3. Button was clicked

     Let's describe the behavior of click event for button element,

     ```javascript
     <!doctype html>
     <html>
      <head>
        <script>
          function greeting() {
            alert('Hello! Good morning');
          }
        </script>
      </head>
      <body>
        <button type="button" onclick="greeting()">Click me</button>
      </body>
     </html>
     ```

     **[⬆ Back to Top](#table-of-contents)**

101. ### Who created javascript

     JavaScript was created by Brendan Eich in 1995 during his time at Netscape Communications. Initially it was developed under the name `Mocha`, but later the language was officially called `LiveScript` when it first shipped in beta releases of Netscape.

     **[⬆ Back to Top](#table-of-contents)**

102. ### What is the use of preventDefault method

     The preventDefault() method cancels the event if it is cancelable, meaning that the default action or behaviour that belongs to the event will not occur. For example, prevent form submission when clicking on submit button and prevent opening the page URL when clicking on hyperlink are some common use cases.

     ```javascript
     document
       .getElementById("link")
       .addEventListener("click", function (event) {
         event.preventDefault();
       });
     ```

     **Note:** Remember that not all events are cancelable.

     **[⬆ Back to Top](#table-of-contents)**

103. ### What is the use of stopPropagation method

     The stopPropagation method is used to stop the event from bubbling up the event chain. For example, the below nested divs with stopPropagation method prevents default event propagation when clicking on nested div(Div1)

     ```javascript
     <p>Click DIV1 Element</p>
     <div onclick="secondFunc()">DIV 2
       <div onclick="firstFunc(event)">DIV 1</div>
     </div>

     <script>
     function firstFunc(event) {
       alert("DIV 1");
       event.stopPropagation();
     }

     function secondFunc() {
       alert("DIV 2");
     }
     </script>
     ```

     **[⬆ Back to Top](#table-of-contents)**


105. ### What is BOM

     The Browser Object Model (BOM) allows JavaScript to "talk to" the browser. It consists of the objects navigator, history, screen, location and document which are children of the window. The Browser Object Model is not standardized and can change based on different browsers.

     ![Screenshot](images/bom.png)

     **[⬆ Back to Top](#table-of-contents)**

106. ### What is the use of setTimeout

     The setTimeout() method is used to call a function or evaluate an expression after a specified number of milliseconds. For example, let's log a message after 2 seconds using setTimeout method,

     ```javascript
     setTimeout(function () {
       console.log("Good morning");
     }, 2000);
     ```

     **[⬆ Back to Top](#table-of-contents)**

107. ### What is the use of setInterval

     The setInterval() method is used to call a function or evaluate an expression at specified intervals (in milliseconds). For example, let's log a message after 2 seconds using setInterval method,

     ```javascript
     setInterval(function () {
       console.log("Good morning");
     }, 2000);
     ```

     **[⬆ Back to Top](#table-of-contents)**

108. ### Why is JavaScript treated as Single threaded

     JavaScript is a single-threaded language. Because the language specification does not allow the programmer to write code so that the interpreter can run parts of it in parallel in multiple threads or processes. Whereas languages like java, go, C++ can make multi-threaded and multi-process programs.

     **[⬆ Back to Top](#table-of-contents)**

109. ### What is an event delegation

      **Event delegation** is a technique in JavaScript where instead of attaching an event listener to each individual element (e.g., each list item), you attach a single event listener to a parent element. This parent listens for events triggered by its child elements, taking advantage of event bubbling.

      Event delegation improves performance, especially when dealing with a large number of elements, and makes it easier to handle events dynamically, even for elements added after the initial page load.

      Example:
      ```javascript
      // Attaching event listener to the parent
      const parentElement = document.getElementById("parent");

      parentElement.addEventListener("click", (event) => {
        // Only handle clicks on list items
        if (event.target && event.target.matches("li")) {
          console.log("List item clicked:", event.target.textContent);
        }
      });
      ```
      Here, the `click` event listener is added to the parent (`#parent`), but the event handler only triggers when a list item (`<li>`) is clicked.

     **[⬆ Back to Top](#table-of-contents)**

110. ### What is ECMAScript

     ECMAScript is the scripting language that forms the basis of JavaScript. ECMAScript standardized by the ECMA International standards organization in the ECMA-262 and ECMA-402 specifications. The first edition of ECMAScript was released in 1997.

     **[⬆ Back to Top](#table-of-contents)**

111. ### What is JSON

     JSON (JavaScript Object Notation) is a lightweight format that is used for data interchanging. It is based on a subset of JavaScript language in the way objects are built in JavaScript.

     **[⬆ Back to Top](#table-of-contents)**

113. ### What is the purpose JSON stringify

     When sending data to a web server, the data has to be in a string format. You can achieve this by converting JSON object into a string using stringify() method.

      Below are the list of syntax rules of JSON

     1. The data is in name/value pairs
     2. The data is separated by commas
     3. Curly braces hold objects
     4. Square brackets hold arrays

     ```javascript
     var userJSON = { name: "John", age: 31 };
     var userString = JSON.stringify(userJSON);
     console.log(userString); //"{"name":"John","age":31}"
     ```

     **[⬆ Back to Top](#table-of-contents)**

114. ### How do you parse JSON string

     When receiving the data from a web server, the data is always in a string format. But you can convert this string value to a javascript object using parse() method.

     ```javascript
     var userString = '{"name":"John","age":31}';
     var userJSON = JSON.parse(userString);
     console.log(userJSON); // {name: "John", age: 31}
     ```

     **[⬆ Back to Top](#table-of-contents)**


116. ### What are PWAs

     Progressive web applications (PWAs) are a type of mobile app delivered through the web, built using common web technologies including HTML, CSS and JavaScript. These PWAs are deployed to servers, accessible through URLs, and indexed by search engines.

     **[⬆ Back to Top](#table-of-contents)**

117. ### What is the purpose of clearTimeout method

     The clearTimeout() function is used in javascript to clear the timeout which has been set by setTimeout()function before that. i.e, The return value of setTimeout() function is stored in a variable and it’s passed into the clearTimeout() function to clear the timer.

     For example, the below setTimeout method is used to display the message after 3 seconds. This timeout can be cleared by the clearTimeout() method.

     ```javascript
     <script>
     var msg;
     function greeting() {
        alert('Good morning');
     }
     function start() {
       msg =setTimeout(greeting, 3000);

     }

     function stop() {
         clearTimeout(msg);
     }
     </script>
     ```

     **[⬆ Back to Top](#table-of-contents)**

118. ### What is the purpose of clearInterval method

     The clearInterval() function is used in javascript to clear the interval which has been set by setInterval() function. i.e, The return value returned by setInterval() function is stored in a variable and it’s passed into the clearInterval() function to clear the interval.

     For example, the below setInterval method is used to display the message for every 3 seconds. This interval can be cleared by the clearInterval() method.

     ```javascript
     <script>
     var msg;
     function greeting() {
        alert('Good morning');
     }
     function start() {
       msg = setInterval(greeting, 3000);

     }

     function stop() {
         clearInterval(msg);
     }
     </script>
     ```

     **[⬆ Back to Top](#table-of-contents)**

119. ### How do you redirect new page in javascript

     In vanilla javascript, you can redirect to a new page using the `location` property of window object. The syntax would be as follows,

     ```javascript
     function redirect() {
       window.location.href = "newPage.html";
     }
     ```

     **[⬆ Back to Top](#table-of-contents)**

120. ### How do you check whether a string contains a substring

     There are 3 possible ways to check whether a string contains a substring or not,

     1. **Using includes:** ES6 provided `String.prototype.includes` method to test a string contains a substring

     ```javascript
     var mainString = "hello",
       subString = "hell";
     mainString.includes(subString);
     ```

     1. **Using indexOf:** In an ES5 or older environment, you can use `String.prototype.indexOf` which returns the index of a substring. If the index value is not equal to -1 then it means the substring exists in the main string.

     ```javascript
     var mainString = "hello",
       subString = "hell";
     mainString.indexOf(subString) !== -1;
     ```

     1. **Using RegEx:** The advanced solution is using Regular expression's test method(`RegExp.test`), which allows for testing for against regular expressions

     ```javascript
     var mainString = "hello",
       regex = /hell/;
     regex.test(mainString);
     ```

     **[⬆ Back to Top](#table-of-contents)**

121. ### How do you validate an email in javascript

     You can validate an email in javascript using regular expressions. It is recommended to do validations on the server side instead of the client side. Because the javascript can be disabled on the client side.

     ```javascript
     function validateEmail(email) {
       var re =
         /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
       return re.test(String(email).toLowerCase());
     }
     ```

     **[⬆ Back to Top](#table-of-contents)**

     The above regular expression accepts unicode characters.

122. ### How do you get the current url with javascript

     You can use `window.location.href` expression to get the current url path and you can use the same expression for updating the URL too. You can also use `document.URL` for read-only purposes but this solution has issues in FF.

     ```javascript
     console.log("location.href", window.location.href); // Returns full URL
     ```

     **[⬆ Back to Top](#table-of-contents)**

123. ### What are the various url properties of location object

     The below `Location` object properties can be used to access URL components of the page,

     1. href - The entire URL
     2. protocol - The protocol of the URL
     3. host - The hostname and port of the URL
     4. hostname - The hostname of the URL
     5. port - The port number in the URL
     6. pathname - The path name of the URL
     7. search - The query portion of the URL
     8. hash - The anchor portion of the URL

     **[⬆ Back to Top](#table-of-contents)**

124. ### How do get query string values in javascript

     You can use URLSearchParams to get query string values in javascript. Let's see an example to get the client code value from URL query string,

     ```javascript
     const urlParams = new URLSearchParams(window.location.search);
     const clientCode = urlParams.get("clientCode");
     ```

     **[⬆ Back to Top](#table-of-contents)**


130. ### What are the pros and cons of for loop

     The for-loop is a commonly used iteration syntax in javascript. It has both pros and cons

     #### Pros

     1. Works on every environment
     2. You can use break and continue flow control statements

     #### Cons

     1. Too verbose
     2. Imperative
     3. You might face one-by-off errors

     **[⬆ Back to Top](#table-of-contents)**

131. ### How do you display the current date in javascript

     You can use `new Date()` to generate a new Date object containing the current date and time. For example, let's display the current date in mm/dd/yyyy

     ```javascript
     var today = new Date();
     var dd = String(today.getDate()).padStart(2, "0");
     var mm = String(today.getMonth() + 1).padStart(2, "0"); //January is 0!
     var yyyy = today.getFullYear();

     today = mm + "/" + dd + "/" + yyyy;
     document.write(today);
     ```

     **[⬆ Back to Top](#table-of-contents)**

132. ### How do you compare two date objects

     You need to use date.getTime() method to compare date values instead of comparison operators (==, !=, ===, and !== operators)

     ```javascript
     var d1 = new Date();
     var d2 = new Date(d1);
     console.log(d1.getTime() === d2.getTime()); //True
     console.log(d1 === d2); // False
     ```

     **[⬆ Back to Top](#table-of-contents)**

133. ### How do you check if a string starts with another string

     You can use ECMAScript 6's `String.prototype.startsWith()` method to check if a string starts with another string or not. But it is not yet supported in all browsers. Let's see an example to see this usage,

     ```javascript
     "Good morning".startsWith("Good"); // true
     "Good morning".startsWith("morning"); // false
     ```

     **[⬆ Back to Top](#table-of-contents)**

134. ### How do you trim a string in javascript

     JavaScript provided a trim method on string types to trim any whitespaces present at the beginning or ending of the string.

     ```javascript
     "  Hello World   ".trim(); //Hello World
     ```

     If your browser(<IE9) doesn't support this method then you can use below polyfill.

     ```javascript
     if (!String.prototype.trim) {
       (function () {
         // Make sure we trim BOM and NBSP
         var rtrim = /^[\s\uFEFF\xA0]+|[\s\uFEFF\xA0]+$/g;
         String.prototype.trim = function () {
           return this.replace(rtrim, "");
         };
       })();
     }
     ```

     **[⬆ Back to Top](#table-of-contents)**

135. ### How do you add a key value pair in javascript

     There are two possible solutions to add new properties to an object. Let's take a simple object to explain these solutions.

     ```javascript
     var object = {
       key1: value1,
       key2: value2,
     };
     ```

     1. **Using dot notation:** This solution is useful when you know the name of the property

     ```javascript
     object.key3 = "value3";
     ```

     1. **Using square bracket notation:** This solution is useful when the name of the property is dynamically determined.

     ```javascript
     obj["key3"] = "value3";
     ```

     **[⬆ Back to Top](#table-of-contents)**


140. ### Can we define properties for functions

     Yes, We can define properties for functions because functions are also objects.

     ```javascript
     fn = function (x) {
       //Function code goes here
     };

     fn.name = "John";

     fn.profile = function (y) {
       //Profile code goes here
     };
     ```

     **[⬆ Back to Top](#table-of-contents)**

141. ### What is the way to find the number of parameters expected by a function

     You can use `function.length` syntax to find the number of parameters expected by a function. Let's take an example of `sum` function to calculate the sum of numbers,

     ```javascript
     function sum(num1, num2, num3, num4) {
       return num1 + num2 + num3 + num4;
     }
     sum.length; // 4 is the number of parameters expected.
     ```

     **[⬆ Back to Top](#table-of-contents)**

142. ### What is a polyfill

     A polyfill is a piece of JS code used to provide modern functionality on older browsers that do not natively support it. For example, Silverlight plugin polyfill can be used to mimic the functionality of an HTML Canvas element on Microsoft Internet Explorer 7.

     **[⬆ Back to Top](#table-of-contents)**

143. ### What are break and continue statements

     The break statement is used to "jump out" of a loop. i.e, It breaks the loop and continues executing the code after the loop.

     ```javascript
     for (i = 0; i < 10; i++) {
       if (i === 5) {
         break;
       }
       text += "Number: " + i + "<br>";
     }
     ```

     The continue statement is used to "jump over" one iteration in the loop. i.e, It breaks one iteration (in the loop), if a specified condition occurs, and continues with the next iteration in the loop.

     ```javascript
     for (i = 0; i < 10; i++) {
       if (i === 5) {
         continue;
       }
       text += "Number: " + i + "<br>";
     }
     ```

     **[⬆ Back to Top](#table-of-contents)**

144. ### What are js labels

     The label statement allows us to name loops and blocks in JavaScript. We can then use these labels to refer back to the code later. For example, the below code with labels avoids printing the numbers when they are same,

     ```javascript
     var i, j;

     loop1: for (i = 0; i < 3; i++) {
       loop2: for (j = 0; j < 3; j++) {
         if (i === j) {
           continue loop1;
         }
         console.log("i = " + i + ", j = " + j);
       }
     }

     // Output is:
     //   "i = 1, j = 0"
     //   "i = 2, j = 0"
     //   "i = 2, j = 1"
     ```

     **[⬆ Back to Top](#table-of-contents)**


147. ### What are the recommendations to create new object

     It is recommended to avoid creating new objects using `new Object()`. Instead you can initialize values based on it's type to create the objects.

     1. Assign {} instead of new Object()
     2. Assign "" instead of new String()
     3. Assign 0 instead of new Number()
     4. Assign false instead of new Boolean()
     5. Assign [] instead of new Array()
     6. Assign /()/ instead of new RegExp()
     7. Assign function (){} instead of new Function()

     You can define them as an example,

     ```javascript
     var v1 = {};
     var v2 = "";
     var v3 = 0;
     var v4 = false;
     var v5 = [];
     var v6 = /()/;
     var v7 = function () {};
     ```

     **[⬆ Back to Top](#table-of-contents)**

148. ### How do you define JSON arrays

     JSON arrays are written inside square brackets and arrays contain javascript objects. For example, the JSON array of users would be as below,

     ```javascript
     "users":[
       {"firstName":"John", "lastName":"Abrahm"},
       {"firstName":"Anna", "lastName":"Smith"},
       {"firstName":"Shane", "lastName":"Warn"}
     ]
     ```

     **[⬆ Back to Top](#table-of-contents)**


154. ### What is a Regular Expression

     A regular expression is a sequence of characters that forms a search pattern. You can use this search pattern for searching data in a text. These can be used to perform all types of text search and text replace operations. Let's see the syntax format now,

     ```javascript
     /pattern/modifiers;
     ```

     For example, the regular expression or search pattern with case-insensitive username would be,

     ```javascript
     /John/i;
     ```

     **[⬆ Back to Top](#table-of-contents)**

155. ### What are the string methods available in Regular expression

     Regular Expressions has two string methods: search() and replace().
     The search() method uses an expression to search for a match, and returns the position of the match.

     ```javascript
     var msg = "Hello John";
     var n = msg.search(/John/i); // 6
     ```

     The replace() method is used to return a modified string where the pattern is replaced.

     ```javascript
     var msg = "Hello John";
     var n = msg.replace(/John/i, "Buttler"); // Hello Buttler
     ```

     **[⬆ Back to Top](#table-of-contents)**

156. ### What are modifiers in regular expression

     Modifiers can be used to perform case-insensitive and global searches. Let's list down some of the modifiers,

     | Modifier | Description                                             |
     | -------- | ------------------------------------------------------- |
     | i        | Perform case-insensitive matching                       |
     | g        | Perform a global match rather than stops at first match |
     | m        | Perform multiline matching                              |

     Let's take an example of global modifier,

     ```javascript
     var text = "Learn JS one by one";
     var pattern = /one/g;
     var result = text.match(pattern); // one,one
     ```

     **[⬆ Back to Top](#table-of-contents)**

157. ### What are regular expression patterns

     Regular Expressions provide a group of patterns in order to match characters. Basically they are categorized into 3 types,

     1. **Brackets:** These are used to find a range of characters.
        For example, below are some use cases,
        1. [abc]: Used to find any of the characters between the brackets(a,b,c)
        2. [0-9]: Used to find any of the digits between the brackets
        3. (a|b): Used to find any of the alternatives separated with |
     2. **Metacharacters:** These are characters with a special meaning
        For example, below are some use cases,
        1. \\d: Used to find a digit
        2. \\s: Used to find a whitespace character
        3. \\b: Used to find a match at the beginning or ending of a word
     3. **Quantifiers:** These are useful to define quantities
        For example, below are some use cases,
        1. n+: Used to find matches for any string that contains at least one n
        2. n\*: Used to find matches for any string that contains zero or more occurrences of n
        3. n?: Used to find matches for any string that contains zero or one occurrences of n

     **[⬆ Back to Top](#table-of-contents)**

158. ### What is a RegExp object

     RegExp object is a regular expression object with predefined properties and methods. Let's see the simple usage of RegExp object,

     ```javascript
     var regexp = new RegExp("\\w+");
     console.log(regexp);
     // expected output: /\w+/
     ```

     **[⬆ Back to Top](#table-of-contents)**

159. ### How do you search a string for a pattern

     You can use the test() method of regular expression in order to search a string for a pattern, and return true or false depending on the result.

     ```javascript
     var pattern = /you/;
     console.log(pattern.test("How are you?")); //true
     ```

     **[⬆ Back to Top](#table-of-contents)**

160. ### What is the purpose of exec method

     The purpose of exec method is similar to test method but it executes a search for a match in a specified string and returns a result array, or null instead of returning true/false.

     ```javascript
     var pattern = /you/;
     console.log(pattern.exec("How are you?")); //["you", index: 8, input: "How are you?", groups: undefined]
     ```

     **[⬆ Back to Top](#table-of-contents)**

161. ### How do you change the style of a HTML element

     You can change inline style or classname of a HTML element using javascript

     1. **Using style property:** You can modify inline style using style property

     ```javascript
     document.getElementById("title").style.fontSize = "30px";
     ```

     1. **Using ClassName property:** It is easy to modify element class using className property

     ```javascript
     document.getElementById("title").className = "custom-title";
     ```

     **[⬆ Back to Top](#table-of-contents)**


167. ### How do you detect a mobile browser without regexp

     You can detect mobile browsers by simply running through a list of devices and checking if the useragent matches anything. This is an alternative solution for RegExp usage,

     ```javascript
     function detectmob() {
       if (
         navigator.userAgent.match(/Android/i) ||
         navigator.userAgent.match(/webOS/i) ||
         navigator.userAgent.match(/iPhone/i) ||
         navigator.userAgent.match(/iPad/i) ||
         navigator.userAgent.match(/iPod/i) ||
         navigator.userAgent.match(/BlackBerry/i) ||
         navigator.userAgent.match(/Windows Phone/i)
       ) {
         return true;
       } else {
         return false;
       }
     }
     ```

     **[⬆ Back to Top](#table-of-contents)**

168. ### How do you get the image width and height using JS

     You can programmatically get the image and check the dimensions(width and height) using Javascript.

     ```javascript
     var img = new Image();
     img.onload = function () {
       console.log(this.width + "x" + this.height);
     };
     img.src = "http://www.google.com/intl/en_ALL/images/logo.gif";
     ```

     **[⬆ Back to Top](#table-of-contents)**

169. ### How do you make synchronous HTTP request

     Browsers provide an XMLHttpRequest object which can be used to make synchronous HTTP requests from JavaScript

     ```javascript
     function httpGet(theUrl) {
       var xmlHttpReq = new XMLHttpRequest();
       xmlHttpReq.open("GET", theUrl, false); // false for synchronous request
       xmlHttpReq.send(null);
       return xmlHttpReq.responseText;
     }
     ```

     **[⬆ Back to Top](#table-of-contents)**

170. ### How do you make asynchronous HTTP request

     Browsers provide an XMLHttpRequest object which can be used to make asynchronous HTTP requests from JavaScript by passing the 3rd parameter as true.

     ```javascript
     function httpGetAsync(theUrl, callback) {
       var xmlHttpReq = new XMLHttpRequest();
       xmlHttpReq.onreadystatechange = function () {
         if (xmlHttpReq.readyState == 4 && xmlHttpReq.status == 200)
           callback(xmlHttpReq.responseText);
       };
       xmlHttp.open("GET", theUrl, true); // true for asynchronous
       xmlHttp.send(null);
     }
     ```

     **[⬆ Back to Top](#table-of-contents)**

171. ### How do you convert date to another timezone in javascript

     You can use the toLocaleString() method to convert dates in one timezone to another. For example, let's convert current date to British English timezone as below,

     ```javascript
     console.log(event.toLocaleString("en-GB", { timeZone: "UTC" })); //29/06/2019, 09:56:00
     ```

     **[⬆ Back to Top](#table-of-contents)**

172. ### What are the properties used to get size of window

     You can use innerWidth, innerHeight, clientWidth, clientHeight properties of windows, document element and document body objects to find the size of a window. Let's use them combination of these properties to calculate the size of a window or document,

     ```javascript
     var width =
       window.innerWidth ||
       document.documentElement.clientWidth ||
       document.body.clientWidth;

     var height =
       window.innerHeight ||
       document.documentElement.clientHeight ||
       document.body.clientHeight;
     ```

     **[⬆ Back to Top](#table-of-contents)**

173. ### What is a conditional operator in javascript

     The conditional (ternary) operator is the only JavaScript operator that takes three operands which acts as a shortcut for if statements.

     ```javascript
     var isAuthenticated = false;
     console.log(
       isAuthenticated ? "Hello, welcome" : "Sorry, you are not authenticated"
     ); //Sorry, you are not authenticated
     ```

     **[⬆ Back to Top](#table-of-contents)**

174. ### Can you apply chaining on conditional operator

     Yes, you can apply chaining on conditional operators similar to if … else if … else if … else chain. The syntax is going to be as below,

     ```javascript
     function traceValue(someParam) {
       return condition1
         ? value1
         : condition2
         ? value2
         : condition3
         ? value3
         : value4;
     }

     // The above conditional operator is equivalent to:

     function traceValue(someParam) {
       if (condition1) {
         return value1;
       } else if (condition2) {
         return value2;
       } else if (condition3) {
         return value3;
       } else {
         return value4;
       }
     }
     ```

     **[⬆ Back to Top](#table-of-contents)**


176. ### What is the difference between proto and prototype

     The `__proto__` object is the actual object that is used in the lookup chain to resolve methods, etc. Whereas `prototype` is the object that is used to build `__proto__` when you create an object with new.

     ```javascript
     new Employee().__proto__ === Employee.prototype;
     new Employee().prototype === undefined;
     ```

     There are few more differences,

     | feature    | Prototype                                                    | proto                                                      |
     | ---------- | ------------------------------------------------------------ | ---------------------------------------------------------- |
     | Access     | All the function constructors have prototype properties.     | All the objects have \_\_proto\_\_ property                |
     | Purpose    | Used to reduce memory wastage with a single copy of function | Used in lookup chain to resolve methods, constructors etc. |
     | ECMAScript | Introduced in ES6                                            | Introduced in ES5                                          |
     | Usage      | Frequently used                                              | Rarely used                                                |

     **[⬆ Back to Top](#table-of-contents)**


178. ### What is a freeze method

     The **freeze()** method is used to freeze an object. Freezing an object does not allow adding new properties to an object,prevents from removing and prevents changing the enumerability, configurability, or writability of existing properties. i.e, It returns the passed object and does not create a frozen copy.

     ```javascript
     const obj = {
       prop: 100,
     };

     Object.freeze(obj);
     obj.prop = 200; // Throws an error in strict mode

     console.log(obj.prop); //100
     ```

     Remember freezing is only applied to the top-level properties in objects but not for nested objects.
     For example, let's try to freeze user object which has employment details as nested object and observe that details have been changed.

     ```javascript
     const user = {
       name: "John",
       employment: {
         department: "IT",
       },
     };

     Object.freeze(user);
     user.employment.department = "HR";
     ```

     **Note:** It causes a TypeError if the argument passed is not an object.

     **[⬆ Back to Top](#table-of-contents)**

179. ### What is the purpose of freeze method

     Below are the main benefits of using freeze method,

     1. It is used for freezing objects and arrays.
     2. It is used to make an object immutable.

     **[⬆ Back to Top](#table-of-contents)**


181. ### How do you detect a browser language preference

     You can use navigator object to detect a browser language preference as below,

     ```javascript
     var language =
       (navigator.languages && navigator.languages[0]) || // Chrome / Firefox
       navigator.language || // All browsers
       navigator.userLanguage; // IE <= 10

     console.log(language);
     ```

     **[⬆ Back to Top](#table-of-contents)**

182. ### How to convert string to title case with javascript

     Title case means that the first letter of each word is capitalized. You can convert a string to title case using the below function,

     ```javascript
     function toTitleCase(str) {
       return str.replace(/\w\S*/g, function (txt) {
         return txt.charAt(0).toUpperCase() + txt.substring(1).toLowerCase();
       });
     }
     toTitleCase("good morning john"); // Good Morning John
     ```

     **[⬆ Back to Top](#table-of-contents)**

185. ### What is a rest parameter

     Rest parameter is an improved way to handle function parameters which allows us to represent an indefinite number of arguments as an array. The syntax would be as below,

     ```javascript
     function f(a, b, ...theArgs) {
       // ...
     }
     ```

     For example, let's take a sum example to calculate on dynamic number of parameters,

     ```javascript
     function sum(...args) {
       let total = 0;
       for (const i of args) {
         total += i;
       }
       return total;
     }

     console.log(sum(1, 2)); //3
     console.log(sum(1, 2, 3)); //6
     console.log(sum(1, 2, 3, 4)); //13
     console.log(sum(1, 2, 3, 4, 5)); //15
     ```

     **Note:** Rest parameter is added in ES2015 or ES6

     **[⬆ Back to Top](#table-of-contents)**


188. ### What is a spread operator

     Spread operator allows iterables( arrays / objects / strings ) to be expanded into single arguments/elements. Let's take an example to see this behavior,

     ```javascript
     function calculateSum(x, y, z) {
       return x + y + z;
     }

     const numbers = [1, 2, 3];

     console.log(calculateSum(...numbers)); // 6
     ```

     **[⬆ Back to Top](#table-of-contents)**


194. ### What is a proxy object

     The Proxy object is used to define custom behavior for fundamental operations such as property lookup, assignment, enumeration, function invocation, etc. The syntax would be as follows,

     ```javascript
     var p = new Proxy(target, handler);
     ```

     Let's take an example of proxy object,

     ```javascript
     var handler = {
       get: function (obj, prop) {
         return prop in obj ? obj[prop] : 100;
       },
     };

     var p = new Proxy({}, handler);
     p.a = 10;
     p.b = null;

     console.log(p.a, p.b); // 10, null
     console.log("c" in p, p.c); // false, 100
     ```

     In the above code, it uses `get` handler which define the behavior of the proxy when an operation is performed on it

     **[⬆ Back to Top](#table-of-contents)**



203. ### What is a WeakSet

     WeakSet is used to store a collection of weakly(weak references) held objects. The syntax would be as follows,

     ```javascript
     new WeakSet([iterable]);
     ```

     Let's see the below example to explain it's behavior,

     ```javascript
     var ws = new WeakSet();
     var user = {};
     ws.add(user);
     ws.has(user); // true
     ws.delete(user); // removes user from the set
     ws.has(user); // false, user has been removed
     ```

     **[⬆ Back to Top](#table-of-contents)**


206. ### What is a WeakMap

     The WeakMap object is a collection of key/value pairs in which the keys are weakly referenced. In this case, keys must be objects and the values can be arbitrary values. The syntax is looking like as below,

     ```javascript
     new WeakMap([iterable]);
     ```

     Let's see the below example to explain it's behavior,

     ```javascript
     var ws = new WeakMap();
     var user = {};
     ws.set(user);
     ws.has(user); // true
     ws.delete(user); // removes user from the map
     ws.has(user); // false, user has been removed
     ```

     **[⬆ Back to Top](#table-of-contents)**

210. ### How do you encode an URL

     The encodeURI() function is used to encode complete URI which has special characters except (, / ? : @ & = + $ #) characters.

     ```javascript
     var uri = "https://mozilla.org/?x=шеллы";
     var encoded = encodeURI(uri);
     console.log(encoded); // https://mozilla.org/?x=%D1%88%D0%B5%D0%BB%D0%BB%D1%8B
     ```

     **[⬆ Back to Top](#table-of-contents)**

211. ### How do you decode an URL

     The decodeURI() function is used to decode a Uniform Resource Identifier (URI) previously created by encodeURI().

     ```javascript
     var uri = "https://mozilla.org/?x=шеллы";
     var encoded = encodeURI(uri);
     console.log(encoded); // https://mozilla.org/?x=%D1%88%D0%B5%D0%BB%D0%BB%D1%8B
     try {
       console.log(decodeURI(encoded)); // "https://mozilla.org/?x=шеллы"
     } catch (e) {
       // catches a malformed URI
       console.error(e);
     }
     ```

     **[⬆ Back to Top](#table-of-contents)**

216. ### What are javascript accessors

     ECMAScript 5 introduced javascript object accessors or computed properties through getters and setters. Getters uses the `get` keyword whereas Setters uses the `set` keyword.

     ```javascript
     var user = {
       firstName: "John",
       lastName: "Abraham",
       language: "en",
       get lang() {
         return this.language;
       },
       set lang(lang) {
         this.language = lang;
       },
     };
     console.log(user.lang); // getter access lang as en
     user.lang = "fr";
     console.log(user.lang); // setter used to set lang as fr
     ```

     **[⬆ Back to Top](#table-of-contents)**


219. ### What are the advantages of Getters and Setters

     Below are the list of benefits of Getters and Setters,

     1. They provide simpler syntax
     2. They are used for defining computed properties, or accessors in JS.
     3. Useful to provide equivalence relation between properties and methods
     4. They can provide better data quality
     5. Useful for doing things behind the scenes with the encapsulated logic.

     **[⬆ Back to Top](#table-of-contents)**

220. ### Can I add getters and setters using defineProperty method

     Yes, You can use the `Object.defineProperty()` method to add Getters and Setters. For example, the below counter object uses increment, decrement, add and subtract properties,

     ```javascript
     var obj = { counter: 0 };

     // Define getters
     Object.defineProperty(obj, "increment", {
       get: function () {
         this.counter++;
       },
     });
     Object.defineProperty(obj, "decrement", {
       get: function () {
         this.counter--;
       },
     });

     // Define setters
     Object.defineProperty(obj, "add", {
       set: function (value) {
         this.counter += value;
       },
     });
     Object.defineProperty(obj, "subtract", {
       set: function (value) {
         this.counter -= value;
       },
     });

     obj.add = 10;
     obj.subtract = 5;
     console.log(obj.increment); //6
     console.log(obj.decrement); //5
     ```

     **[⬆ Back to Top](#table-of-contents)**

221. ### What is the purpose of switch-case

     The switch case statement in JavaScript is used for decision making purposes. In a few cases, using the switch case statement is going to be more convenient than if-else statements. The syntax would be as below,

     ```javascript
     switch (expression)
     {
         case value1:
             statement1;
             break;
         case value2:
             statement2;
             break;
         .
         .
         case valueN:
             statementN;
             break;
         default:
             statementDefault;
     }
     ```

     The above multi-way branch statement provides an easy way to dispatch execution to different parts of code based on the value of the expression.

     **[⬆ Back to Top](#table-of-contents)**


223. ### What are primitive data types

     A primitive data type is data that has a primitive value (which has no properties or methods). There are 7 types of primitive data types.

     1. string
     2. number
     3. boolean
     4. null
     5. undefined
     6. bigint
     7. symbol

     **[⬆ Back to Top](#table-of-contents)**

224. ### What are the different ways to access object properties

     There are 3 possible ways for accessing the property of an object.

     1. **Dot notation:** It uses dot for accessing the properties

     ```javascript
     objectName.property;
     ```

     1. **Square brackets notation:** It uses square brackets for property access

     ```javascript
     objectName["property"];
     ```

     1. **Expression notation:** It uses expression in the square brackets

     ```javascript
     objectName[expression];
     ```

     **[⬆ Back to Top](#table-of-contents)**


226. ### What is an error object

     An error object is a built in error object that provides error information when an error occurs. It has two properties: name and message. For example, the below function logs error details,

     ```javascript
     try {
       greeting("Welcome");
     } catch (err) {
       console.log(err.name + "<br>" + err.message);
     }
     ```

     **[⬆ Back to Top](#table-of-contents)**


228. ### What are the different error names from error object

     There are 6 different types of error names returned from error object,
     | Error Name | Description |
     |---- | ---------
     | EvalError | An error has occurred in the eval() function |
     | RangeError | An error has occurred with a number "out of range" |
     | ReferenceError | An error due to an illegal reference|
     | SyntaxError | An error due to a syntax error|
     | TypeError | An error due to a type error |
     | URIError | An error due to encodeURI() |

     **[⬆ Back to Top](#table-of-contents)**

229. ### What are the various statements in error handling

     Below are the list of statements used in an error handling,

     1. **try:** This statement is used to test a block of code for errors
     2. **catch:** This statement is used to handle the error
     3. **throw:** This statement is used to create custom errors.
     4. **finally:** This statement is used to execute code after try and catch regardless of the result.

     **[⬆ Back to Top](#table-of-contents)**


231. ### What is nodejs

     Node.js is a server-side platform built on Chrome's JavaScript runtime for easily building fast and scalable network applications. It is an event-based, non-blocking, asynchronous I/O runtime that uses Google's V8 JavaScript engine and libuv library.

     **[⬆ Back to Top](#table-of-contents)**

232. ### What is an Intl object

     The Intl object is the namespace for the ECMAScript Internationalization API, which provides language sensitive string comparison, number formatting, and date and time formatting. It provides access to several constructors and language sensitive functions.

     **[⬆ Back to Top](#table-of-contents)**


234. ### What is an Iterator

     An iterator is an object which defines a sequence and a return value upon its termination. It implements the Iterator protocol with a `next()` method which returns an object with two properties: `value` (the next value in the sequence) and `done` (which is true if the last value in the sequence has been consumed).

     **[⬆ Back to Top](#table-of-contents)**

236. ### What is an event loop

     The event loop is a process that continuously monitors both the call stack and the event queue and checks whether or not the call stack is empty. If the call stack is empty and there are pending events in the event queue, the event loop dequeues the event from the event queue and pushes it to the call stack. The call stack executes the event, and any additional events generated during the execution are added to the end of the event queue.

     **Note:** The event loop allows Node.js to perform non-blocking I/O operations, even though JavaScript is single-threaded, by offloading operations to the system kernel whenever possible. Since most modern kernels are multi-threaded, they can handle multiple operations executing in the background.

     **[⬆ Back to Top](#table-of-contents)**

237. ### What is call stack

     Call Stack is a data structure for javascript interpreters to keep track of function calls(creates execution context) in the program. It has two major actions,

     1. Whenever you call a function for its execution, you are pushing it to the stack.
     2. Whenever the execution is completed, the function is popped out of the stack.

     Let's take an example and it's state representation in a diagram format

     ```javascript
     function hungry() {
       eatFruits();
     }
     function eatFruits() {
       return "I'm eating fruits";
     }

     // Invoke the `hungry` function
     hungry();
     ```

     The above code processed in a call stack as below,

     1. Add the `hungry()` function to the call stack list and execute the code.
     2. Add the `eatFruits()` function to the call stack list and execute the code.
     3. Delete the `eatFruits()` function from our call stack list.
     4. Delete the `hungry()` function from the call stack list since there are no items anymore.

     ![Screenshot](images/call-stack.png)

     **[⬆ Back to Top](#table-of-contents)**

238. ### What is an event queue

     The event queue follows the queue data structure. It stores async callbacks to be added to the call stack. It is also known as the Callback Queue or Macrotask Queue.

     Whenever the call stack receives an async function, it is moved into the Web API. Based on the function, Web API executes it and awaits the result. Once it is finished, it moves the callback into the event queue (the callback of the promise is moved into the microtask queue).

     The event loop constantly checks whether or not the call stack is empty. Once the call stack is empty and there is a callback in the event queue, the event loop moves the callback into the call stack. But if there is a callback in the microtask queue as well, it is moved first. The microtask queue has a higher priority than the event queue.

     **[⬆ Back to Top](#table-of-contents)**

239. ### What is a decorator

     A decorator is an expression that evaluates to a function and that takes the target, name, and decorator descriptor as arguments. Also, it optionally returns a decorator descriptor to install on the target object. Let's define admin decorator for user class at design time,

     ```javascript
     function admin(isAdmin) {
        return function(target) {
            target.isAdmin = isAdmin;
        }
     }

     @admin(true)
     class User() {
     }
     console.log(User.isAdmin); //true

      @admin(false)
      class User() {
      }
      console.log(User.isAdmin); //false
     ```

     **[⬆ Back to Top](#table-of-contents)**

241. ### What is an Unary operator

     The unary(+) operator is used to convert a variable to a number.If the variable cannot be converted, it will still become a number but with the value NaN. Let's see this behavior in an action.

     ```javascript
     var x = "100";
     var y = +x;
     console.log(typeof x, typeof y); // string, number

     var a = "Hello";
     var b = +a;
     console.log(typeof a, typeof b, b); // string, number, NaN
     ```

     **[⬆ Back to Top](#table-of-contents)**


244. ### How do you reversing an array

     You can use the reverse() method to reverse the elements in an array. This method is useful to sort an array in descending order. Let's see the usage of reverse() method in an example,

     ```javascript
     let numbers = [1, 2, 5, 3, 4];
     numbers.sort((a, b) => b - a);
     numbers.reverse();
     console.log(numbers); // [1, 2, 3, 4 ,5]
     ```

     **[⬆ Back to Top](#table-of-contents)**

245. ### How do you find min and max value in an array

     You can use `Math.min` and `Math.max` methods on array variables to find the minimum and maximum elements within an array. Let's create two functions to find the min and max value with in an array,

     ```javascript
     var marks = [50, 20, 70, 60, 45, 30];
     function findMin(arr) {
       return Math.min.apply(null, arr);
     }
     function findMax(arr) {
       return Math.max.apply(null, arr);
     }

     console.log(findMin(marks));
     console.log(findMax(marks));
     ```

     **[⬆ Back to Top](#table-of-contents)**

246. ### How do you find min and max values without Math functions

     You can write functions which loop through an array comparing each value with the lowest value or highest value to find the min and max values. Let's create those functions to find min and max values,

     ```javascript
     var marks = [50, 20, 70, 60, 45, 30];
     function findMin(arr) {
       var length = arr.length;
       var min = Infinity;
       while (length--) {
         if (arr[length] < min) {
           min = arr[length];
         }
       }
       return min;
     }

     function findMax(arr) {
       var length = arr.length;
       var max = -Infinity;
       while (length--) {
         if (arr[length] > max) {
           max = arr[length];
         }
       }
       return max;
     }

     console.log(findMin(marks));
     console.log(findMax(marks));
     ```

     **[⬆ Back to Top](#table-of-contents)**


248. ### How do you get metadata of a module

     You can use the `import.meta` object which is a meta-property exposing context-specific meta data to a JavaScript module. It contains information about the current module, such as the module's URL. In browsers, you might get different meta data than NodeJS.

     ```javascript
     <script type="module" src="welcome-module.js"></script>;
     console.log(import.meta); // { url: "file:///home/user/welcome-module.js" }
     ```

     **[⬆ Back to Top](#table-of-contents)**

251. ### What is typescript

     TypeScript is a typed superset of JavaScript created by Microsoft that adds optional types, classes, async/await, and many other features, and compiles to plain JavaScript. Angular built entirely in TypeScript and used as a primary language. You can install it globally as

     ```bash
     npm install -g typescript
     ```

     Let's see a simple example of TypeScript usage,

     ```typescript
     function greeting(name: string): string {
       return "Hello, " + name;
     }

     let user = "Sudheer";

     console.log(greeting(user));
     ```

     The greeting method allows only string type as argument.

     **[⬆ Back to Top](#table-of-contents)**

252. ### What are the differences between javascript and typescript

     Below are the list of differences between javascript and typescript,

     | feature             | typescript                            | javascript                                      |
     | ------------------- | ------------------------------------- | ----------------------------------------------- |
     | Language paradigm   | Object oriented programming language  | Scripting language                              |
     | Typing support      | Supports static typing                | It has dynamic typing                           |
     | Modules             | Supported                             | Not supported                                   |
     | Interface           | It has interfaces concept             | Doesn't support interfaces                      |
     | Optional parameters | Functions support optional parameters | No support of optional parameters for functions |

     **[⬆ Back to Top](#table-of-contents)**

253. ### What are the advantages of typescript over javascript

     Below are some of the advantages of typescript over javascript,

     1. TypeScript is able to find compile time errors at the development time only and it makes sures less runtime errors. Whereas javascript is an interpreted language.
     2. TypeScript is strongly-typed or supports static typing which allows for checking type correctness at compile time. This is not available in javascript.
     3. TypeScript compiler can compile the .ts files into ES3,ES4 and ES5 unlike ES6 features of javascript which may not be supported in some browsers.

     **[⬆ Back to Top](#table-of-contents)**

255. ### What is a constructor method

     The constructor method is a special method for creating and initializing an object created within a class. If you do not specify a constructor method, a default constructor is used. The example usage of constructor would be as below,

     ```javascript
     class Employee {
       constructor() {
         this.name = "John";
       }
     }

     var employeeObject = new Employee();

     console.log(employeeObject.name); // John
     ```

     **[⬆ Back to Top](#table-of-contents)**


257. ### How do you call the constructor of a parent class

     You can use the `super` keyword to call the constructor of a parent class. Remember that `super()` must be called before using 'this' reference. Otherwise it will cause a reference error. Let's the usage of it,

     ```javascript
     class Square extends Rectangle {
       constructor(length) {
         super(length, length);
         this.name = "Square";
       }

       get area() {
         return this.width * this.height;
       }

       set area(value) {
         this.area = value;
       }
     }
     ```

     **[⬆ Back to Top](#table-of-contents)**

265. ### What is MEAN in javascript

     The MEAN (MongoDB, Express, AngularJS, and Node.js) stack is the most popular open-source JavaScript software tech stack available for building dynamic web apps where you can write both the server-side and client-side halves of the web project entirely in JavaScript.

     **[⬆ Back to Top](#table-of-contents)**

266. ### What Is Obfuscation in javascript

     Obfuscation is the deliberate act of creating obfuscated javascript code(i.e, source or machine code) that is difficult for humans to understand. It is something similar to encryption, but a machine can understand the code and execute it.
     Let's see the below function before Obfuscation,

     ```javascript
     function greeting() {
       console.log("Hello, welcome to JS world");
     }
     ```

     And after the code Obfuscation, it would be appeared as below,

     ```javascript
     eval(
       (function (p, a, c, k, e, d) {
         e = function (c) {
           return c;
         };
         if (!"".replace(/^/, String)) {
           while (c--) {
             d[c] = k[c] || c;
           }
           k = [
             function (e) {
               return d[e];
             },
           ];
           e = function () {
             return "\\w+";
           };
           c = 1;
         }
         while (c--) {
           if (k[c]) {
             p = p.replace(new RegExp("\\b" + e(c) + "\\b", "g"), k[c]);
           }
         }
         return p;
       })(
         "2 1(){0.3('4, 7 6 5 8')}",
         9,
         9,
         "console|greeting|function|log|Hello|JS|to|welcome|world".split("|"),
         0,
         {}
       )
     );
     ```

     **[⬆ Back to Top](#table-of-contents)**


268. ### What is Minification

     Minification is the process of removing all unnecessary characters(empty spaces are removed) and variables will be renamed without changing it's functionality. It is also a type of obfuscation .

     **[⬆ Back to Top](#table-of-contents)**

269. ### What are the advantages of minification

     Normally it is recommended to use minification for heavy traffic and intensive requirements of resources. It reduces file sizes with below benefits,

     1. Decreases loading times of a web page
     2. Saves bandwidth usages

     **[⬆ Back to Top](#table-of-contents)**

270. ### What are the differences between Obfuscation and Encryption

     Below are the main differences between Obfuscation and Encryption,

     | Feature            | Obfuscation                                     | Encryption                                                              |
     | ------------------ | ----------------------------------------------- | ----------------------------------------------------------------------- |
     | Definition         | Changing the form of any data in any other form | Changing the form of information to an unreadable format by using a key |
     | A key to decode    | It can be decoded without any key               | It is required                                                          |
     | Target data format | It will be converted to a complex form          | Converted into an unreadable format                                     |

     **[⬆ Back to Top](#table-of-contents)**


279. ### What is an enum

     An enum is a type restricting variables to one value from a predefined set of constants. JavaScript has no enums but typescript provides built-in enum support.

     ```javascript
     enum Color {
      RED, GREEN, BLUE
     }
     ```

     **[⬆ Back to Top](#table-of-contents)**


283. ### How do you extend classes

     The `extends` keyword is used in class declarations/expressions to create a class which is a child of another class. It can be used to subclass custom classes as well as built-in objects. The syntax would be as below,

     ```javascript
     class ChildClass extends ParentClass { ... }
     ```

     Let's take an example of Square subclass from Polygon parent class,

     ```javascript
     class Square extends Rectangle {
       constructor(length) {
         super(length, length);
         this.name = "Square";
       }

       get area() {
         return this.width * this.height;
       }

       set area(value) {
         this.area = value;
       }
     }
     ```

     **[⬆ Back to Top](#table-of-contents)**


285. ### How do you check whether an array includes a particular value or not

     The `Array#includes()` method is used to determine whether an array includes a particular value among its entries by returning either true or false. Let's see an example to find an element(numeric and string) within an array.

     ```javascript
     var numericArray = [1, 2, 3, 4];
     console.log(numericArray.includes(3)); // true

     var stringArray = ["green", "yellow", "blue"];
     console.log(stringArray.includes("blue")); //true
     ```

     **[⬆ Back to Top](#table-of-contents)**


289. ### What is the difference between java and javascript

     Both are totally unrelated programming languages and no relation between them. Java is statically typed, compiled, runs on its own VM. Whereas Javascript is dynamically typed, interpreted, and runs in a browser and nodejs environments. Let's see the major differences in a tabular format,
     | Feature | Java | JavaScript |
     |---- | ---- | -----
     | Typed | It's a strongly typed language | It's a dynamic typed language |
     | Paradigm | Object oriented programming | Prototype based programming |
     | Scoping | Block scoped | Function-scoped |
     | Concurrency | Thread based | event based |
     | Memory | Uses more memory | Uses less memory. Hence it will be used for web pages |

     **[⬆ Back to Top](#table-of-contents)**

290. ### Does JavaScript supports namespace

     JavaScript doesn’t support namespace by default. So if you create any element(function, method, object, variable) then it becomes global and pollutes the global namespace. Let's take an example of defining two functions without any namespace,

     ```javascript
     function func1() {
       console.log("This is a first definition");
     }
     function func1() {
       console.log("This is a second definition");
     }
     func1(); // This is a second definition
     ```

     It always calls the second function definition. In this case, namespace will solve the name collision problem.

     **[⬆ Back to Top](#table-of-contents)**

291. ### How do you declare namespace

     Even though JavaScript lacks namespaces, we can use Objects , IIFE to create namespaces.

     1. **Using Object Literal Notation:** Let's wrap variables and functions inside an Object literal which acts as a namespace. After that you can access them using object notation

     ```javascript
     var namespaceOne = {
        function func1() {
            console.log("This is a first definition");
        }
     }
     var namespaceTwo = {
          function func1() {
              console.log("This is a second definition");
          }
      }
     namespaceOne.func1(); // This is a first definition
     namespaceTwo.func1(); // This is a second definition
     ```

     1. **Using IIFE (Immediately invoked function expression):** The outer pair of parentheses of IIFE creates a local scope for all the code inside of it and makes the anonymous function a function expression. Due to that, you can create the same function in two different function expressions to act as a namespace.

     ```javascript
     (function () {
       function fun1() {
         console.log("This is a first definition");
       }
       fun1();
     })();

     (function () {
       function fun1() {
         console.log("This is a second definition");
       }
       fun1();
     })();
     ```

     1. **Using a block and a let/const declaration:** In ECMAScript 6, you can simply use a block and a let declaration to restrict the scope of a variable to a block.

     ```javascript
     {
       let myFunction = function fun1() {
         console.log("This is a first definition");
       };
       myFunction();
     }
     //myFunction(): ReferenceError: myFunction is not defined.

     {
       let myFunction = function fun1() {
         console.log("This is a second definition");
       };
       myFunction();
     }
     //myFunction(): ReferenceError: myFunction is not defined.
     ```

     **[⬆ Back to Top](#table-of-contents)**

292. ### How do you invoke javascript code in an iframe from parent page

     Initially iFrame needs to be accessed using either `document.getElementBy` or `window.frames`. After that `contentWindow` property of iFrame gives the access for targetFunction

     ```javascript
     document.getElementById("targetFrame").contentWindow.targetFunction();
     window.frames[0].frameElement.contentWindow.targetFunction(); // Accessing iframe this way may not work in latest versions chrome and firefox
     ```

     **[⬆ Back to Top](#table-of-contents)**

293. ### How do get the timezone offset from date

     You can use the `getTimezoneOffset` method of the date object. This method returns the time zone difference, in minutes, from current locale (host system settings) to UTC

     ```javascript
     var offset = new Date().getTimezoneOffset();
     console.log(offset); // -480
     ```

     **[⬆ Back to Top](#table-of-contents)**

294. ### How do you load CSS and JS files dynamically

     You can create both link and script elements in the DOM and append them as child to head tag. Let's create a function to add script and style resources as below,

     ```javascript
     function loadAssets(filename, filetype) {
       if (filetype == "css") {
         // External CSS file
         var fileReference = document.createElement("link");
         fileReference.setAttribute("rel", "stylesheet");
         fileReference.setAttribute("type", "text/css");
         fileReference.setAttribute("href", filename);
       } else if (filetype == "js") {
         // External JavaScript file
         var fileReference = document.createElement("script");
         fileReference.setAttribute("type", "text/javascript");
         fileReference.setAttribute("src", filename);
       }
       if (typeof fileReference != "undefined")
         document.getElementsByTagName("head")[0].appendChild(fileReference);
     }
     ```

     **[⬆ Back to Top](#table-of-contents)**

295. ### What are the different methods to find HTML elements in DOM

     If you want to access any element in an HTML page, you need to start with accessing the document object. Later you can use any of the below methods to find the HTML element,

     1. document.getElementById(id): It finds an element by Id
     2. document.getElementsByTagName(name): It finds an element by tag name
     3. document.getElementsByClassName(name): It finds an element by class name

     **[⬆ Back to Top](#table-of-contents)**

296. ### What is jQuery

     jQuery is a popular cross-browser JavaScript library that provides Document Object Model (DOM) traversal, event handling, animations and AJAX interactions by minimizing the discrepancies across browsers. It is widely famous with its philosophy of “Write less, do more”. For example, you can display welcome message on the page load using jQuery as below,

     ```javascript
     $(document).ready(function () {
       // It selects the document and apply the function on page load
       alert("Welcome to jQuery world");
     });
     ```

     **Note:** You can download it from jquery's official site or install it from CDNs, like google.

     **[⬆ Back to Top](#table-of-contents)**

297. ### What is V8 JavaScript engine

     V8 is an open source high-performance JavaScript engine used by the Google Chrome browser, written in C++. It is also being used in the node.js project. It implements ECMAScript and WebAssembly, and runs on Windows 7 or later, macOS 10.12+, and Linux systems that use x64, IA-32, ARM, or MIPS processors.
     **Note:** It can run standalone, or can be embedded into any C++ application.

     **[⬆ Back to Top](#table-of-contents)**


299. ### What is a void operator

     The `void` operator evaluates the given expression and then returns undefined(i.e, without returning value). The syntax would be as below,

     ```javascript
     void expression;
     void expression;
     ```

     Let's display a message without any redirection or reload

     ```javascript
     <a href="javascript:void(alert('Welcome to JS world'))">
       Click here to see a message
     </a>
     ```

     **Note:** This operator is often used to obtain the undefined primitive value, using "void(0)".

     **[⬆ Back to Top](#table-of-contents)**

300. ### How to set the cursor to wait

     The cursor can be set to wait in JavaScript by using the property "cursor". Let's perform this behavior on page load using the below function.

     ```javascript
     function myFunction() {
       window.document.body.style.cursor = "wait";
     }
     ```

     and this function invoked on page load

     ```html
     <body onload="myFunction()"></body>
     ```

     **[⬆ Back to Top](#table-of-contents)**



304. ### List down some of the features of ES6

     Below are the list of some new features of ES6,

     1. Support for constants or immutable variables
     2. Block-scope support for variables, constants and functions
     3. Arrow functions
     4. Default parameters
     5. Rest and Spread Parameters
     6. Template Literals
     7. Multi-line Strings
     8. Destructuring Assignment
     9. Enhanced Object Literals
     10. Promises
     11. Classes
     12. Modules

     **[⬆ Back to Top](#table-of-contents)**

305. ### What is ES6

     ES6 is the sixth edition of the javascript language and it was released in June 2015. It was initially known as ECMAScript 6 (ES6) and later renamed to ECMAScript 2015. Almost all the modern browsers support ES6 but for the old browsers there are many transpilers, like Babel.js etc.

     **[⬆ Back to Top](#table-of-contents)**

306. ### Can I redeclare let and const variables

     No, you cannot redeclare let and const variables. If you do, it throws below error

     ```bash
     Uncaught SyntaxError: Identifier 'someVariable' has already been declared
     ```

     **Explanation:** The variable declaration with `var` keyword refers to a function scope and the variable is treated as if it were declared at the top of the enclosing scope due to hoisting feature. So all the multiple declarations contributing to the same hoisted variable without any error. Let's take an example of re-declaring variables in the same scope for both var and let/const variables.

     ```javascript
     var name = "John";
     function myFunc() {
       var name = "Nick";
       var name = "Abraham"; // Re-assigned in the same function block
       alert(name); // Abraham
     }
     myFunc();
     alert(name); // John
     ```

     The block-scoped multi-declaration throws syntax error,

     ```javascript
     let name = "John";
     function myFunc() {
       let name = "Nick";
       let name = "Abraham"; // Uncaught SyntaxError: Identifier 'name' has already been declared
       alert(name);
     }

     myFunc();
     alert(name);
     ```

     **[⬆ Back to Top](#table-of-contents)**

307. ### Is const variable makes the value immutable

     No, the const variable doesn't make the value immutable. But it disallows subsequent assignments(i.e, You can declare with assignment but can't assign another value later)

     ```javascript
     const userList = [];
     userList.push("John"); // Can mutate even though it can't re-assign
     console.log(userList); // ['John']
     ```

     **[⬆ Back to Top](#table-of-contents)**

308. ### What are default parameters

     In ES5, we need to depend on logical OR operators to handle default values of function parameters. Whereas in ES6, Default function parameters feature allows parameters to be initialized with default values if no value or undefined is passed. Let's compare the behavior with an examples,

     ```javascript
     //ES5
     var calculateArea = function (height, width) {
       height = height || 50;
       width = width || 60;

       return width * height;
     };
     console.log(calculateArea()); //300
     ```

     The default parameters makes the initialization more simpler,

     ```javascript
     //ES6
     var calculateArea = function (height = 50, width = 60) {
       return width * height;
     };

     console.log(calculateArea()); //300
     ```

     **[⬆ Back to Top](#table-of-contents)**

309. ### What are template literals

     Template literals or template strings are string literals allowing embedded expressions. These are enclosed by the back-tick (`) character instead of double or single quotes.
     In ES6, this feature enables using dynamic expressions as below,

     ```javascript
     var greeting = `Welcome to JS World, Mr. ${firstName} ${lastName}.`;
     ```

     In ES5, you need break string like below,

     ```javascript
     var greeting = 'Welcome to JS World, Mr. ' + firstName + ' ' + lastName.`
     ```

     **Note:** You can use multi-line strings and string interpolation features with template literals.

     **[⬆ Back to Top](#table-of-contents)**

310. ### How do you write multi-line strings in template literals

     In ES5, you would have to use newline escape characters('\\n') and concatenation symbols(+) in order to get multi-line strings.

     ```javascript
     console.log("This is string sentence 1\n" + "This is string sentence 2");
     ```

     Whereas in ES6, You don't need to mention any newline sequence character,

     ```javascript
     console.log(`This is string sentence
     'This is string sentence 2`);
     ```

     **[⬆ Back to Top](#table-of-contents)**

311. ### What are nesting templates

     The nesting template is a feature supported within template literals syntax to allow inner backticks inside a placeholder ${ } within the template. For example, the below nesting template is used to display the icons based on user permissions whereas outer template checks for platform type,

     ```javascript
     const iconStyles = `icon ${
       isMobilePlatform()
         ? ""
         : `icon-${user.isAuthorized ? "submit" : "disabled"}`
     }`;
     ```

     You can write the above use case without nesting template features as well. However, the nesting template feature is more compact and readable.

     ```javascript
     //Without nesting templates
     const iconStyles = `icon ${
       isMobilePlatform()
         ? ""
         : user.isAuthorized
         ? "icon-submit"
         : "icon-disabled"
     }`;
     ```

     **[⬆ Back to Top](#table-of-contents)**

312. ### What are tagged templates

     Tagged templates are the advanced form of templates in which tags allow you to parse template literals with a function. The tag function accepts the first parameter as an array of strings and remaining parameters as expressions. This function can also return manipulated strings based on parameters. Let's see the usage of this tagged template behavior of an IT professional skill set in an organization,

     ```javascript
     var user1 = "John";
     var skill1 = "JavaScript";
     var experience1 = 15;

     var user2 = "Kane";
     var skill2 = "JavaScript";
     var experience2 = 5;

     function myInfoTag(strings, userExp, experienceExp, skillExp) {
       var str0 = strings[0]; // "Mr/Ms. "
       var str1 = strings[1]; // " is a/an "
       var str2 = strings[2]; // "in"

       var expertiseStr;
       if (experienceExp > 10) {
         expertiseStr = "expert developer";
       } else if (skillExp > 5 && skillExp <= 10) {
         expertiseStr = "senior developer";
       } else {
         expertiseStr = "junior developer";
       }

       return `${str0}${userExp}${str1}${expertiseStr}${str2}${skillExp}`;
     }

     var output1 = myInfoTag`Mr/Ms. ${user1} is a/an ${experience1} in ${skill1}`;
     var output2 = myInfoTag`Mr/Ms. ${user2} is a/an ${experience2} in ${skill2}`;

     console.log(output1); // Mr/Ms. John is a/an expert developer in JavaScript
     console.log(output2); // Mr/Ms. Kane is a/an junior developer in JavaScript
     ```

     **[⬆ Back to Top](#table-of-contents)**

313. ### What are raw strings

     ES6 provides a raw strings feature using the `String.raw()` method which is used to get the raw string form of template strings. This feature allows you to access the raw strings as they were entered, without processing escape sequences. For example, the usage would be as below,

     ```javascript
     var calculationString = String.raw`The sum of numbers is \n${
       1 + 2 + 3 + 4
     }!`;
     console.log(calculationString); // The sum of numbers is \n10!
     ```

     If you don't use raw strings, the newline character sequence will be processed by displaying the output in multiple lines

     ```javascript
     var calculationString = `The sum of numbers is \n${1 + 2 + 3 + 4}!`;
     console.log(calculationString);
     // The sum of numbers is
     // 10!
     ```

     Also, the raw property is available on the first argument to the tag function

     ```javascript
     function tag(strings) {
       console.log(strings.raw[0]);
     }
     ```

     **[⬆ Back to Top](#table-of-contents)**

314. ### What is destructuring assignment

     The destructuring assignment is a JavaScript expression that makes it possible to unpack values from arrays or properties from objects into distinct variables.
     Let's get the month values from an array using destructuring assignment

     ```javascript
     var [one, two, three] = ["JAN", "FEB", "MARCH"];

     console.log(one); // "JAN"
     console.log(two); // "FEB"
     console.log(three); // "MARCH"
     ```

     and you can get user properties of an object using destructuring assignment,

     ```javascript
     var { name, age } = { name: "John", age: 32 };

     console.log(name); // John
     console.log(age); // 32
     ```

     **[⬆ Back to Top](#table-of-contents)**

315. ### What are default values in destructuring assignment

     A variable can be assigned a default value when the value unpacked from the array or object is undefined during destructuring assignment. It helps to avoid setting default values separately for each assignment. Let's take an example for both arrays and object use cases,

     **Arrays destructuring:**

     ```javascript
     var x, y, z;

     [x = 2, y = 4, z = 6] = [10];
     console.log(x); // 10
     console.log(y); // 4
     console.log(z); // 6
     ```

     **Objects destructuring:**

     ```javascript
     var { x = 2, y = 4, z = 6 } = { x: 10 };

     console.log(x); // 10
     console.log(y); // 4
     console.log(z); // 6
     ```

     **[⬆ Back to Top](#table-of-contents)**

316. ### How do you swap variables in destructuring assignment

     If you don't use destructuring assignment, swapping two values requires a temporary variable. Whereas using a destructuring feature, two variable values can be swapped in one destructuring expression. Let's swap two number variables in array destructuring assignment,

     ```javascript
     var x = 10,
       y = 20;

     [x, y] = [y, x];
     console.log(x); // 20
     console.log(y); // 10
     ```

     **[⬆ Back to Top](#table-of-contents)**

317. ### What are enhanced object literals

     Object literals make it easy to quickly create objects with properties inside the curly braces. For example, it provides shorter syntax for common object property definition as below.

     ```javascript
     //ES6
     var x = 10,
       y = 20;
     obj = { x, y };
     console.log(obj); // {x: 10, y:20}
     //ES5
     var x = 10,
       y = 20;
     obj = { x: x, y: y };
     console.log(obj); // {x: 10, y:20}
     ```

     **[⬆ Back to Top](#table-of-contents)**

318. ### What are dynamic imports

     The dynamic imports using `import()` function syntax allows us to load modules on demand by using promises or the async/await syntax. Currently this feature is in [stage4 proposal](https://github.com/tc39/proposal-dynamic-import). The main advantage of dynamic imports is reduction of our bundle's sizes, the size/payload response of our requests and overall improvements in the user experience.
     The syntax of dynamic imports would be as below,

     ```javascript
     import("./Module").then((Module) => Module.method());
     ```

     **[⬆ Back to Top](#table-of-contents)**


321. ### What are the advantages of module loaders

     The module loaders provides the below features,

     1. Dynamic loading
     2. State isolation
     3. Global namespace isolation
     4. Compilation hooks
     5. Nested virtualization

     **[⬆ Back to Top](#table-of-contents)**

322. ### What is collation

     Collation is used for sorting a set of strings and searching within a set of strings. It is parameterized by locale and aware of Unicode. Let's take comparison and sorting features,

     1. **Comparison:**

     ```javascript
     var list = ["ä", "a", "z"]; // In German,  "ä" sorts with "a" Whereas in Swedish, "ä" sorts after "z"
     var l10nDE = new Intl.Collator("de");
     var l10nSV = new Intl.Collator("sv");
     console.log(l10nDE.compare("ä", "z") === -1); // true
     console.log(l10nSV.compare("ä", "z") === +1); // true
     ```

     1. **Sorting:**

     ```javascript
     var list = ["ä", "a", "z"]; // In German,  "ä" sorts with "a" Whereas in Swedish, "ä" sorts after "z"
     var l10nDE = new Intl.Collator("de");
     var l10nSV = new Intl.Collator("sv");
     console.log(list.sort(l10nDE.compare)); // [ "a", "ä", "z" ]
     console.log(list.sort(l10nSV.compare)); // [ "a", "z", "ä" ]
     ```

     **[⬆ Back to Top](#table-of-contents)**

323. ### What is for...of statement

     The for...of statement creates a loop iterating over iterable objects or elements such as built-in String, Array, Array-like objects (like arguments or NodeList), TypedArray, Map, Set, and user-defined iterables. The basic usage of for...of statement on arrays would be as below,

     ```javascript
     let arrayIterable = [10, 20, 30, 40, 50];

     for (let value of arrayIterable) {
       value++;
       console.log(value); // 11 21 31 41 51
     }
     ```

     **[⬆ Back to Top](#table-of-contents)**


325. ### Is PostMessage secure

     Yes, postMessages can be considered very secure as long as the programmer/developer is careful about checking the origin and source of an arriving message. But if you try to send/receive a message without verifying its source will create cross-site scripting attacks.

     **[⬆ Back to Top](#table-of-contents)**

330. ### What paradigm is Javascript

     JavaScript is a multi-paradigm language, supporting imperative/procedural programming, Object-Oriented Programming and functional programming. JavaScript supports Object-Oriented Programming with prototypical inheritance.

     **[⬆ Back to Top](#table-of-contents)**

331. ### What is the difference between internal and external javascript

     **Internal JavaScript:** It is the source code within the script tag.
     **External JavaScript:** The source code is stored in an external file(stored with .js extension) and referred with in the tag.

     **[⬆ Back to Top](#table-of-contents)**

332. ### Is JavaScript faster than server side script

     Yes, JavaScript is faster than server side scripts. Because JavaScript is a client-side script it does not require any web server’s help for its computation or calculation. So JavaScript is always faster than any server-side script like ASP, PHP, etc.

     **[⬆ Back to Top](#table-of-contents)**

333. ### How do you get the status of a checkbox

     You can apply the `checked` property on the selected checkbox in the DOM. If the value is `true` it means the checkbox is checked, otherwise it is unchecked. For example, the below HTML checkbox element can be access using javascript as below:

     ```html
     <input type="checkbox" id="checkboxname" value="Agree" /> Agree the
     conditions<br />
     ```

     ```javascript
     console.log(document.getElementById(‘checkboxname’).checked); // true or false
     ```

     **[⬆ Back to Top](#table-of-contents)**

334. ### What is the purpose of double tilde operator

     The double tilde operator(~~) is known as double NOT bitwise operator. This operator is a slightly quicker substitute for Math.floor().

     **[⬆ Back to Top](#table-of-contents)**

335. ### How do you convert character to ASCII code

     You can use the `String.prototype.charCodeAt()` method to convert string characters to ASCII numbers. For example, let's find ASCII code for the first letter of 'ABC' string,

     ```javascript
     "ABC".charCodeAt(0); // returns 65
     ```

     Whereas `String.fromCharCode()` method converts numbers to equal ASCII characters.

     ```javascript
     String.fromCharCode(65, 66, 67); // returns 'ABC'
     ```

     **[⬆ Back to Top](#table-of-contents)**

336. ### What is ArrayBuffer

     An ArrayBuffer object is used to represent a generic, fixed-length raw binary data buffer. You can create it as below,

     ```javascript
     let buffer = new ArrayBuffer(16); // create a buffer of length 16
     alert(buffer.byteLength); // 16
     ```

     To manipulate an ArrayBuffer, we need to use a “view” object.

     ```javascript
     //Create a DataView referring to the buffer
     let view = new DataView(buffer);
     ```

     **[⬆ Back to Top](#table-of-contents)**


342. ### What is the difference between a parameter and an argument

     Parameter is the variable name of a function definition whereas an argument represents the value given to a function when it is invoked. Let's explain this with a simple function

     ```javascript
     function myFunction(parameter1, parameter2, parameter3) {
       console.log(arguments[0]); // "argument1"
       console.log(arguments[1]); // "argument2"
       console.log(arguments[2]); // "argument3"
     }
     myFunction("argument1", "argument2", "argument3");
     ```

     **[⬆ Back to Top](#table-of-contents)**

343. ### What is the purpose of some method in arrays

     The some() method is used to test whether at least one element in the array passes the test implemented by the provided function. The method returns a boolean value. Let's take an example to test for any odd elements,

     ```javascript
     var array = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];

     var odd = (element) => element % 2 !== 0;

     console.log(array.some(odd)); // true (the odd element exists)
     ```

     **[⬆ Back to Top](#table-of-contents)**

344. ### How do you combine two or more arrays

     The concat() method is used to join two or more arrays by returning a new array containing all the elements. The syntax would be as below,

     ```javascript
     array1.concat(array2, array3, ..., arrayX)
     ```

     Let's take an example of array's concatenation with veggies and fruits arrays,

     ```javascript
     var veggies = ["Tomato", "Carrot", "Cabbage"];
     var fruits = ["Apple", "Orange", "Pears"];
     var veggiesAndFruits = veggies.concat(fruits);
     console.log(veggiesAndFruits); // Tomato, Carrot, Cabbage, Apple, Orange, Pears
     ```

     **[⬆ Back to Top](#table-of-contents)**

345. ### What is the difference between Shallow and Deep copy

     There are two ways to copy an object,

     **Shallow Copy:**
     Shallow copy is a bitwise copy of an object. A new object is created that has an exact copy of the values in the original object. If any of the fields of the object are references to other objects, just the reference addresses are copied i.e., only the memory address is copied.

     **Example**

     ```javascript
     var empDetails = {
       name: "John",
       age: 25,
       expertise: "Software Developer",
     };
     ```

     to create a duplicate

     ```javascript
     var empDetailsShallowCopy = empDetails; //Shallow copying!
     ```

     if we change some property value in the duplicate one like this:

     ```javascript
     empDetailsShallowCopy.name = "Johnson";
     ```

     The above statement will also change the name of `empDetails`, since we have a shallow copy. That means we're losing the original data as well.

     **Deep copy:**
     A deep copy copies all fields, and makes copies of dynamically allocated memory pointed to by the fields. A deep copy occurs when an object is copied along with the objects to which it refers.

     **Example**

     ```javascript
     var empDetails = {
       name: "John",
       age: 25,
       expertise: "Software Developer",
     };
     ```

     Create a deep copy by using the properties from the original object into new variable

     ```javascript
     var empDetailsDeepCopy = {
       name: empDetails.name,
       age: empDetails.age,
       expertise: empDetails.expertise,
     };
     ```

     Now if you change `empDetailsDeepCopy.name`, it will only affect `empDetailsDeepCopy` & not `empDetails`

     **[⬆ Back to Top](#table-of-contents)**


348. ### How do you trim a string at the beginning or ending

     The `trim` method of string prototype is used to trim on both sides of a string. But if you want to trim especially at the beginning or ending of the string then you can use `trimStart/trimLeft` and `trimEnd/trimRight` methods. Let's see an example of these methods on a greeting message,

     ```javascript
     var greeting = "   Hello, Goodmorning!   ";

     console.log(greeting); // "   Hello, Goodmorning!   "
     console.log(greeting.trimStart()); // "Hello, Goodmorning!   "
     console.log(greeting.trimLeft()); // "Hello, Goodmorning!   "

     console.log(greeting.trimEnd()); // "   Hello, Goodmorning!"
     console.log(greeting.trimRight()); // "   Hello, Goodmorning!"
     ```

     **[⬆ Back to Top](#table-of-contents)**


350. ### Does javascript uses mixins

     Mixin is a generic object-oriented programming term - is a class containing methods that can be used by other classes without a need to inherit from it. In JavaScript we can only inherit from a single object. ie. There can be only one `[[prototype]]` for an object.

     But sometimes we require to extend more than one, to overcome this we can use Mixin which helps to copy methods to the prototype of another class.

     Say for instance, we've two classes `User` and `CleanRoom`. Suppose we need to add `CleanRoom` functionality to `User`, so that user can clean the room at demand. Here's where concept called mixins comes into picture.

     ```javascript
     // mixin
     let cleanRoomMixin = {
       cleanRoom() {
         alert(`Hello ${this.name}, your room is clean now`);
       },
       sayBye() {
         alert(`Bye ${this.name}`);
       },
     };

     // usage:
     class User {
       constructor(name) {
         this.name = name;
       }
     }

     // copy the methods
     Object.assign(User.prototype, cleanRoomMixin);

     // now User can clean the room
     new User("Dude").cleanRoom(); // Hello Dude, your room is clean now!
     ```

     **[⬆ Back to Top](#table-of-contents)**

351. ### What is a thunk function

     A thunk is just a function which delays the evaluation of the value. It doesn’t take any arguments but gives the value whenever you invoke the thunk. i.e, It is used not to execute now but it will be sometime in the future. Let's take a synchronous example,

     ```javascript
     const add = (x, y) => x + y;

     const thunk = () => add(2, 3);

     thunk(); // 5
     ```

     **[⬆ Back to Top](#table-of-contents)**

352. ### What are asynchronous thunks

     The asynchronous thunks are useful to make network requests. Let's see an example of network requests,

     ```javascript
     function fetchData(fn) {
       fetch("https://jsonplaceholder.typicode.com/todos/1")
         .then((response) => response.json())
         .then((json) => fn(json));
     }

     const asyncThunk = function () {
       return fetchData(function getData(data) {
         console.log(data);
       });
     };

     asyncThunk();
     ```

     The `getData` function won't be called immediately but it will be invoked only when the data is available from API endpoint. The setTimeout function is also used to make our code asynchronous. The best real time example is redux state management library which uses the asynchronous thunks to delay the actions to dispatch.

     **[⬆ Back to Top](#table-of-contents)**


357. ### What happens if we add two arrays

     If you add two arrays together, it will convert them both to strings and concatenate them. For example, the result of adding arrays would be as below,

     ```javascript
     console.log(["a"] + ["b"]); // "ab"
     console.log([] + []); // ""
     console.log(![] + []); // "false", because ![] returns false.
     ```

     **[⬆ Back to Top](#table-of-contents)**

358. ### What is the output of prepend additive operator on falsy values

     If you prepend the additive(+) operator on falsy values(null, undefined, NaN, false, ""), the falsy value converts to a number value zero. Let's display them on browser console as below,

     ```javascript
     console.log(+null); // 0
     console.log(+undefined); // NaN
     console.log(+false); // 0
     console.log(+NaN); // NaN
     console.log(+""); // 0
     ```

     **[⬆ Back to Top](#table-of-contents)**


360. ### How do you remove falsy values from an array

     You can apply the filter method on the array by passing Boolean as a parameter. This way it removes all falsy values(0, undefined, null, false and "") from the array.

     ```javascript
     const myArray = [false, null, 1, 5, undefined];
     myArray.filter(Boolean); // [1, 5] // is same as myArray.filter(x => x);
     ```

     **[⬆ Back to Top](#table-of-contents)**

361. ### How do you get unique values of an array

     You can get unique values of an array with the combination of `Set` and rest expression/spread(...) syntax.

     ```javascript
     console.log([...new Set([1, 2, 4, 4, 3])]); // [1, 2, 4, 3]
     ```

     **[⬆ Back to Top](#table-of-contents)**

362. ### What is destructuring aliases

     Sometimes you would like to have a destructured variable with a different name than the property name. In that case, you'll use a `: newName` to specify a name for the variable. This process is called destructuring aliases.

     ```javascript
     const obj = { x: 1 };
     // Grabs obj.x as as { otherName }
     const { x: otherName } = obj;
     ```

     **[⬆ Back to Top](#table-of-contents)**

363. ### How do you map the array values without using map method

     You can map the array values without using the `map` method by just using the `from` method of Array. Let's map city names from Countries array,

     ```javascript
     const countries = [
       { name: "India", capital: "Delhi" },
       { name: "US", capital: "Washington" },
       { name: "Russia", capital: "Moscow" },
       { name: "Singapore", capital: "Singapore" },
       { name: "China", capital: "Beijing" },
       { name: "France", capital: "Paris" },
     ];

     const cityNames = Array.from(countries, ({ capital }) => capital);
     console.log(cityNames); // ['Delhi, 'Washington', 'Moscow', 'Singapore', 'Beijing', 'Paris']
     ```

     **[⬆ Back to Top](#table-of-contents)**

364. ### How do you empty an array

     You can empty an array quickly by setting the array length to zero.

     ```javascript
     let cities = ["Singapore", "Delhi", "London"];
     cities.length = 0; // cities becomes []
     ```

     **[⬆ Back to Top](#table-of-contents)**

365. ### How do you rounding numbers to certain decimals

     You can round numbers to a certain number of decimals using `toFixed` method from native javascript.

     ```javascript
     let pie = 3.141592653;
     pie = pie.toFixed(3); // 3.142
     ```

     **[⬆ Back to Top](#table-of-contents)**

366. ### What is the easiest way to convert an array to an object

     You can convert an array to an object with the same data using spread(...) operator.

     ```javascript
     var fruits = ["banana", "apple", "orange", "watermelon"];
     var fruitsObject = { ...fruits };
     console.log(fruitsObject); // {0: "banana", 1: "apple", 2: "orange", 3: "watermelon"}
     ```

     **[⬆ Back to Top](#table-of-contents)**

367. ### How do you create an array with some data

     You can create an array with some data or an array with the same values using `fill` method.

     ```javascript
     var newArray = new Array(5).fill("0");
     console.log(newArray); // ["0", "0", "0", "0", "0"]
     ```

     **[⬆ Back to Top](#table-of-contents)**



372. ### How do you display data in a tabular format using console object

     The `console.table()` is used to display data in the console in a tabular format to visualize complex arrays or objects.

     ```js
     const users = [
       { name: "John", id: 1, city: "Delhi" },
       { name: "Max", id: 2, city: "London" },
       { name: "Rod", id: 3, city: "Paris" },
     ];
     console.table(users);
     ```

     The data visualized in a table format,

     ![Screenshot](images/console-table.png)
     **Not:** Remember that `console.table()` is not supported in IE.

     **[⬆ Back to Top](#table-of-contents)**

373. ### How do you verify that an argument is a Number or not

     The combination of IsNaN and isFinite methods are used to confirm whether an argument is a number or not.

     ```javascript
     function isNumber(n) {
       return !isNaN(parseFloat(n)) && isFinite(n);
     }
     ```

     **[⬆ Back to Top](#table-of-contents)**

374. ### How do you create copy to clipboard button

     You need to select the content(using .select() method) of the input element and execute the copy command with execCommand (i.e, execCommand('copy')). You can also execute other system commands like cut and paste.

     ```javascript
     document.querySelector("#copy-button").onclick = function () {
       // Select the content
       document.querySelector("#copy-input").select();
       // Copy to the clipboard
       document.execCommand("copy");
     };
     ```

     **[⬆ Back to Top](#table-of-contents)**

375. ### What is the shortcut to get timestamp

     You can use `new Date().getTime()` to get the current timestamp. There is an alternative shortcut to get the value.

     ```javascript
     console.log(+new Date());
     console.log(Date.now());
     ```

     **[⬆ Back to Top](#table-of-contents)**

376. ### How do you flattening multi dimensional arrays

     Flattening bi-dimensional arrays is trivial with Spread operator.

     ```javascript
     const biDimensionalArr = [11, [22, 33], [44, 55], [66, 77], 88, 99];
     const flattenArr = [].concat(...biDimensionalArr); // [11, 22, 33, 44, 55, 66, 77, 88, 99]
     ```

     But you can make it work with multi-dimensional arrays by recursive calls,

     ```javascript
     function flattenMultiArray(arr) {
       const flattened = [].concat(...arr);
       return flattened.some((item) => Array.isArray(item))
         ? flattenMultiArray(flattened)
         : flattened;
     }
     const multiDimensionalArr = [11, [22, 33], [44, [55, 66, [77, [88]], 99]]];
     const flatArr = flattenMultiArray(multiDimensionalArr); // [11, 22, 33, 44, 55, 66, 77, 88, 99]
     ```

     Also you can use the `flat` method of Array.

     ```javascript
     const arr = [1, [2, 3], 4, 5, [6, 7]];
     const fllattenArr = arr.flat(); // [1, 2, 3, 4, 5, 6, 7]

     // And for multiDemensional arrays
     const multiDimensionalArr = [11, [22, 33], [44, [55, 66, [77, [88]], 99]]];
     const oneStepFlat = multiDimensionalArr.flat(1); // [11, 22, 33, 44, [55, 66, [77, [88]], 99]]
     const towStep = multiDimensionalArr.flat(2); // [11, 22, 33, 44, 55, 66, [77, [88]], 99]
     const fullyFlatArray = multiDimensionalArr.flat(Infinity); // [11, 22, 33, 44, 55, 66, 77, 88, 99]
     ```

     **[⬆ Back to Top](#table-of-contents)**

377. ### What is the easiest multi condition checking

     You can use `indexOf` to compare input with multiple values instead of checking each value as one condition.

     ```javascript
     // Verbose approach
     if (
       input === "first" ||
       input === 1 ||
       input === "second" ||
       input === 2
     ) {
       someFunction();
     }
     // Shortcut
     if (["first", 1, "second", 2].indexOf(input) !== -1) {
       someFunction();
     }
     ```

     **[⬆ Back to Top](#table-of-contents)**

378. ### How do you capture browser back button

     The `window.onbeforeunload` method is used to capture browser back button events. This is helpful to warn users about losing the current data.

     ```javascript
     window.onbeforeunload = function () {
       alert("You work will be lost");
     };
     ```

     **[⬆ Back to Top](#table-of-contents)**

379. ### How do you disable right click in the web page

     The right click on the page can be disabled by returning false from the `oncontextmenu` attribute on the body element.

     ```html
     <body oncontextmenu="return false;"></body>
     ```

     **[⬆ Back to Top](#table-of-contents)**

380. ### What are wrapper objects

     Primitive Values like string,number and boolean don't have properties and methods but they are temporarily converted or coerced to an object(Wrapper object) when you try to perform actions on them. For example, if you apply toUpperCase() method on a primitive string value, it does not throw an error but returns uppercase of the string.

     ```javascript
     let name = "john";

     console.log(name.toUpperCase()); // Behind the scenes treated as console.log(new String(name).toUpperCase());
     ```

     i.e, Every primitive except null and undefined have Wrapper Objects and the list of wrapper objects are String,Number,Boolean,Symbol and BigInt.

     **[⬆ Back to Top](#table-of-contents)**

381. ### What is AJAX

     AJAX stands for Asynchronous JavaScript and XML and it is a group of related technologies(HTML, CSS, JavaScript, XMLHttpRequest API etc) used to display data asynchronously. i.e. We can send data to the server and get data from the server without reloading the web page.

     **[⬆ Back to Top](#table-of-contents)**

382. ### What are the different ways to deal with Asynchronous Code

     Below are the list of different ways to deal with Asynchronous code.

     1. Callbacks
     2. Promises
     3. Async/await
     4. Third-party libraries such as async.js,bluebird etc

     **[⬆ Back to Top](#table-of-contents)**

383. ### How to cancel a fetch request

     Until a few days back, One shortcoming of native promises is no direct way to cancel a fetch request. But the new `AbortController` from js specification allows you to use a signal to abort one or multiple fetch calls.
     The basic flow of cancelling a fetch request would be as below,

     1. Create an `AbortController` instance
     2. Get the signal property of an instance and pass the signal as a fetch option for signal
     3. Call the AbortController's abort property to cancel all fetches that use that signal
        For example, let's pass the same signal to multiple fetch calls will cancel all requests with that signal,

     ```javascript
     const controller = new AbortController();
     const { signal } = controller;

     fetch("http://localhost:8000", { signal })
       .then((response) => {
         console.log(`Request 1 is complete!`);
       })
       .catch((e) => {
         if (e.name === "AbortError") {
           // We know it's been canceled!
         }
       });

     fetch("http://localhost:8000", { signal })
       .then((response) => {
         console.log(`Request 2 is complete!`);
       })
       .catch((e) => {
         if (e.name === "AbortError") {
           // We know it's been canceled!
         }
       });

     // Wait 2 seconds to abort both requests
     setTimeout(() => controller.abort(), 2000);
     ```

     **[⬆ Back to Top](#table-of-contents)**

384. ### What is web speech API

     Web speech API is used to enable modern browsers recognize and synthesize speech(i.e, voice data into web apps). This API has been introduced by W3C Community in the year 2012. It has two main parts,

     1. **SpeechRecognition (Asynchronous Speech Recognition or Speech-to-Text):** It provides the ability to recognize voice context from an audio input and respond accordingly. This is accessed by the `SpeechRecognition` interface.
        The below example shows on how to use this API to get text from speech,

     ```javascript
     window.SpeechRecognition =
       window.webkitSpeechRecognition || window.SpeechRecognition; // webkitSpeechRecognition for Chrome and SpeechRecognition for FF
     const recognition = new window.SpeechRecognition();
     recognition.onresult = (event) => {
       // SpeechRecognitionEvent type
       const speechToText = event.results[0][0].transcript;
       console.log(speechToText);
     };
     recognition.start();
     ```

     In this API, browser is going to ask you for permission to use your microphone

     1. **SpeechSynthesis (Text-to-Speech):** It provides the ability to recognize voice context from an audio input and respond. This is accessed by the `SpeechSynthesis` interface.
        For example, the below code is used to get voice/speech from text,

     ```javascript
     if ("speechSynthesis" in window) {
       var speech = new SpeechSynthesisUtterance("Hello World!");
       speech.lang = "en-US";
       window.speechSynthesis.speak(speech);
     }
     ```

     The above examples can be tested on chrome(33+) browser's developer console.
     **Note:** This API is still a working draft and only available in Chrome and Firefox browsers(ofcourse Chrome only implemented the specification)

     **[⬆ Back to Top](#table-of-contents)**


388. ### What is microtask

     Microtask is the javascript code which needs to be executed immediately after the currently executing task/microtask is completed. They are kind of blocking in nature. i.e, The main thread will be blocked until the microtask queue is empty.
     The main sources of microtasks are Promise.resolve, Promise.reject, MutationObservers, IntersectionObservers etc

     **Note:** All of these microtasks are processed in the same turn of the event loop.
     **[⬆ Back to Top](#table-of-contents)**

389. ### What are different event loops

     In JavaScript, there are multiple event loops that can be used depending on the context of your application. The most common event loops are:

390. The Browser Event Loop
391. The Node.js Event Loop


    - Browser Event Loop: The Browser Event Loop is used in client-side JavaScript applications and is responsible for handling events that occur within the browser environment, such as user interactions (clicks, keypresses, etc.), HTTP requests, and other asynchronous actions.

    - The Node.js Event Loop is used in server-side JavaScript applications and is responsible for handling events that occur within the Node.js runtime environment, such as file I/O, network I/O, and other asynchronous actions.

     **[⬆ Back to Top](#table-of-contents)**


393. ### What is heap

     Heap(Or memory heap) is the memory location where objects are stored when we define variables. i.e, This is the place where all the memory allocations and de-allocation take place. Both heap and call-stack are two containers of JS runtime.
     Whenever runtime comes across variables and function declarations in the code it stores them in the Heap.

     ![Screenshot](images/heap.png)

     **[⬆ Back to Top](#table-of-contents)**

394. ### What is an event table

     Event Table is a data structure that stores and keeps track of all the events which will be executed asynchronously like after some time interval or after the resolution of some API requests. i.e Whenever you call a setTimeout function or invoke async operation, it is added to the Event Table.
     It doesn't not execute functions on it’s own. The main purpose of the event table is to keep track of events and send them to the Event Queue as shown in the below diagram.

     ![Screenshot](images/event-table.png)

     **[⬆ Back to Top](#table-of-contents)**

395. ### What is a microTask queue

     Microtask Queue is the new queue where all the tasks initiated by promise objects get processed before the callback queue.
     The microtasks queue are processed before the next rendering and painting jobs. But if these microtasks are running for a long time then it leads to visual degradation.

     **[⬆ Back to Top](#table-of-contents)**


398. ### What is babel

     Babel is a JavaScript transpiler to convert ECMAScript 2015+ code into a backwards compatible version of JavaScript in current and older browsers or environments. Some of the main features are listed below,

     1. Transform syntax
     2. Polyfill features that are missing in your target environment (using @babel/polyfill)
     3. Source code transformations (or codemods)

     **[⬆ Back to Top](#table-of-contents)**

399. ### Is Node.js completely single threaded

     Node is a single thread, but some of the functions included in the Node.js standard library(e.g, fs module functions) are not single threaded. i.e, Their logic runs outside of the Node.js single thread to improve the speed and performance of a program.

     **[⬆ Back to Top](#table-of-contents)**

402. ### What is the difference between Function constructor and function declaration

     The functions which are created with `Function constructor` do not create closures to their creation contexts but they are always created in the global scope. i.e, the function can access its own local variables and global scope variables only. Whereas function declarations can access outer function variables(closures) too.

     Let's see this difference with an example,

     **Function Constructor:**

     ```javascript
     var a = 100;
     function createFunction() {
       var a = 200;
       return new Function("return a;");
     }
     console.log(createFunction()()); // 100
     ```

     **Function declaration:**

     ```javascript
     var a = 100;
     function createFunction() {
       var a = 200;
       return function func() {
         return a;
       };
     }
     console.log(createFunction()()); // 200
     ```

     **[⬆ Back to Top](#table-of-contents)**

403. ### What is a Short circuit condition

     Short circuit conditions are meant for condensed way of writing simple if statements. Let's demonstrate the scenario using an example. If you would like to login to a portal with an authentication condition, the expression would be as below,

     ```javascript
     if (authenticate) {
       loginToPorta();
     }
     ```

     Since the javascript logical operators evaluated from left to right, the above expression can be simplified using && logical operator

     ```javascript
     authenticate && loginToPorta();
     ```

     **[⬆ Back to Top](#table-of-contents)**

404. ### What is the easiest way to resize an array

     The length property of an array is useful to resize or empty an array quickly. Let's apply length property on number array to resize the number of elements from 5 to 2,

     ```javascript
     var array = [1, 2, 3, 4, 5];
     console.log(array.length); // 5

     array.length = 2;
     console.log(array.length); // 2
     console.log(array); // [1,2]
     ```

     and the array can be emptied too

     ```javascript
     var array = [1, 2, 3, 4, 5];
     array.length = 0;
     console.log(array.length); // 0
     console.log(array); // []
     ```

     **[⬆ Back to Top](#table-of-contents)**


406. ### What is the difference between function and class declarations

     The main difference between function declarations and class declarations is `hoisting`. The function declarations are hoisted but not class declarations.

     **Classes:**

     ```javascript
     const user = new User(); // ReferenceError

     class User {}
     ```

     **Constructor Function:**

     ```javascript
     const user = new User(); // No error

     function User() {}
     ```

     **[⬆ Back to Top](#table-of-contents)**


409. ### What is deno

     Deno is a simple, modern and secure runtime for JavaScript and TypeScript that uses V8 JavaScript engine and the Rust programming language.

     **[⬆ Back to Top](#table-of-contents)**

410. ### How do you make an object iterable in javascript

     By default, plain objects are not iterable. But you can make the object iterable by defining a `Symbol.iterator` property on it.

     Let's demonstrate this with an example,

     ```javascript
     const collection = {
       one: 1,
       two: 2,
       three: 3,
       [Symbol.iterator]() {
         const values = Object.keys(this);
         let i = 0;
         return {
           next: () => {
             return {
               value: this[values[i++]],
               done: i > values.length,
             };
           },
         };
       },
     };

     const iterator = collection[Symbol.iterator]();

     console.log(iterator.next()); // → {value: 1, done: false}
     console.log(iterator.next()); // → {value: 2, done: false}
     console.log(iterator.next()); // → {value: 3, done: false}
     console.log(iterator.next()); // → {value: undefined, done: true}
     ```

     The above process can be simplified using a generator function,

     ```javascript
     const collection = {
       one: 1,
       two: 2,
       three: 3,
       [Symbol.iterator]: function* () {
         for (let key in this) {
           yield this[key];
         }
       },
     };
     const iterator = collection[Symbol.iterator]();
     console.log(iterator.next()); // {value: 1, done: false}
     console.log(iterator.next()); // {value: 2, done: false}
     console.log(iterator.next()); // {value: 3, done: false}
     console.log(iterator.next()); // {value: undefined, done: true}
     ```

     **[⬆ Back to Top](#table-of-contents)**

412. ### How do you check an object is a promise or not

     If you don't know if a value is a promise or not, wrapping the value as `Promise.resolve(value)` which returns a promise

     ```javascript
     function isPromise(object) {
       if (Promise && Promise.resolve) {
         return Promise.resolve(object) == object;
       } else {
         throw "Promise not supported in your environment";
       }
     }

     var i = 1;
     var promise = new Promise(function (resolve, reject) {
       resolve();
     });

     console.log(isPromise(i)); // false
     console.log(isPromise(promise)); // true
     ```

     Another way is to check for `.then()` handler type

     ```javascript
     function isPromise(value) {
       return Boolean(value && typeof value.then === "function");
     }
     var i = 1;
     var promise = new Promise(function (resolve, reject) {
       resolve();
     });

     console.log(isPromise(i)); // false
     console.log(isPromise(promise)); // true
     ```

     **[⬆ Back to Top](#table-of-contents)**

413. ### How to detect if a function is called as constructor

     You can use `new.target` pseudo-property to detect whether a function was called as a constructor(using the new operator) or as a regular function call.

     1. If a constructor or function invoked using the new operator, new.target returns a reference to the constructor or function.
     2. For function calls, new.target is undefined.

     ```javascript
     function Myfunc() {
       if (new.target) {
         console.log("called with new");
       } else {
         console.log("not called with new");
       }
     }

     new Myfunc(); // called with new
     Myfunc(); // not called with new
     Myfunc.call({}); // not called with new
     ```

     **[⬆ Back to Top](#table-of-contents)**

414. ### What are the differences between arguments object and rest parameter

     There are three main differences between arguments object and rest parameters

     1. The arguments object is an array-like but not an array. Whereas the rest parameters are array instances.
     2. The arguments object does not support methods such as sort, map, forEach, or pop. Whereas these methods can be used in rest parameters.
     3. The rest parameters are only the ones that haven’t been given a separate name, while the arguments object contains all arguments passed to the function

     **[⬆ Back to Top](#table-of-contents)**

415. ### What are the differences between spread operator and rest parameter

     Rest parameter collects all remaining elements into an array. Whereas Spread operator allows iterables( arrays / objects / strings ) to be expanded into single arguments/elements. i.e, Rest parameter is opposite to the spread operator.

     **[⬆ Back to Top](#table-of-contents)**


417. ### What are the built-in iterables

     Below are the list of built-in iterables in javascript,

     1. Arrays and TypedArrays
     2. Strings: Iterate over each character or Unicode code-points
     3. Maps: iterate over its key-value pairs
     4. Sets: iterates over their elements
     5. arguments: An array-like special variable in functions
     6. DOM collection such as NodeList

     **[⬆ Back to Top](#table-of-contents)**

418. ### What are the differences between for...of and for...in statements

     Both for...in and for...of statements iterate over js data structures. The only difference is over what they iterate:

     1. for..in iterates over all enumerable property keys of an object
     2. for..of iterates over the values of an iterable object.

     Let's explain this difference with an example,

     ```javascript
     let arr = ["a", "b", "c"];

     arr.newProp = "newVlue";

     // key are the property keys
     for (let key in arr) {
       console.log(key); // 0, 1, 2 & newValue
     }

     // value are the property values
     for (let value of arr) {
       console.log(value); // a, b, c
     }
     ```

     Since for..in loop iterates over the keys of the object, the first loop logs 0, 1, 2 and newProp while iterating over the array object. The for..of loop iterates over the values of a arr data structure and logs a, b, c in the console.

     **[⬆ Back to Top](#table-of-contents)**


420. ### What is the difference between isNaN and Number.isNaN?

     1. **isNaN**: The global function `isNaN` converts the argument to a Number and returns true if the resulting value is NaN.
     2. **Number.isNaN**: This method does not convert the argument. But it returns true when the type is a Number and value is NaN.

     Let's see the difference with an example,

     ```javascript
     isNaN(‘hello’);   // true
     Number.isNaN('hello'); // false
     ```

     **[⬆ Back to Top](#table-of-contents)**


422. ### Is that possible to use expressions in switch cases?

     You might have seen expressions used in switch condition but it is also possible to use for switch cases by assigning true value for the switch condition. Let's see the weather condition based on temparature as an example,

     ```js
     const weather = (function getWeather(temp) {
       switch (true) {
         case temp < 0:
           return "freezing";
         case temp < 10:
           return "cold";
         case temp < 24:
           return "cool";
         default:
           return "unknown";
       }
     })(10);
     ```

     **[⬆ Back to Top](#table-of-contents)**


424. ### How do style the console output using CSS?

     You can add CSS styling to the console output using the CSS format content specifier %c. The console string message can be appended after the specifier and CSS style in another argument. Let's print the red the color text using console.log and CSS specifier as below,

     ```js
     console.log("%cThis is a red text", "color:red");
     ```

     It is also possible to add more styles for the content. For example, the font-size can be modified for the above text

     ```js
     console.log(
       "%cThis is a red text with bigger font",
       "color:red; font-size:20px"
     );
     ```

     **[⬆ Back to Top](#table-of-contents)**

425. ### What is nullish coalescing operator (??)?

     It is a logical operator that returns its right-hand side operand when its left-hand side operand is null or undefined, and otherwise returns its left-hand side operand. This can be contrasted with the logical OR (||) operator, which returns the right-hand side operand if the left operand is any falsy value, not only null or undefined.

     ```js
     console.log(null ?? true); // true
     console.log(false ?? true); // false
     console.log(undefined ?? true); // true
     ```

     **[⬆ Back to Top](#table-of-contents)**

426. ### How do you group and nest console output?

     The `console.group()` can be used to group related log messages to be able to easily read the logs and use console.groupEnd()to close the group. Along with this, you can also nest groups which allows to output message in hierarchical manner.

     For example, if you’re logging a user’s details:

     ```js
     console.group("User Details");
     console.log("name: Sudheer Jonna");
     console.log("job: Software Developer");

     // Nested Group
     console.group("Address");
     console.log("Street: Commonwealth");
     console.log("City: Los Angeles");
     console.log("State: California");

     // Close nested group
     console.groupEnd();

     // Close outer group
     console.groupEnd();
     ```

     You can also use `console.groupCollapsed()` instead of `console.group()` if you want the groups to be collapsed by default.

     **[⬆ Back to Top](#table-of-contents)**

427. ### What is the difference between dense and sparse arrays?

     An array contains items at each index starting from first(0) to last(array.length - 1) is called as Dense array. Whereas if at least one item is missing at any index, the array is called as sparse.

     Let's see the below two kind of arrays,

     ```js
     const avengers = ["Ironman", "Hulk", "CaptainAmerica"];
     console.log(avengers[0]); // 'Ironman'
     console.log(avengers[1]); // 'Hulk'
     console.log(avengers[2]); // 'CaptainAmerica'
     console.log(avengers.length); // 3

     const justiceLeague = ["Superman", "Aquaman", , "Batman"];
     console.log(justiceLeague[0]); // 'Superman'
     console.log(justiceLeague[1]); // 'Aquaman'
     console.log(justiceLeague[2]); // undefined
     console.log(justiceLeague[3]); // 'Batman'
     console.log(justiceLeague.length); // 4
     ```

     **[⬆ Back to Top](#table-of-contents)**

428. ### What are the different ways to create sparse arrays?

     There are 4 different ways to create sparse arrays in JavaScript

     1. **Array literal:** Omit a value when using the array literal
        ```js
        const justiceLeague = ["Superman", "Aquaman", , "Batman"];
        console.log(justiceLeague); // ['Superman', 'Aquaman', empty ,'Batman']
        ```
     2. **Array() constructor:** Invoking Array(length) or new Array(length)
        ```js
        const array = Array(3);
        console.log(array); // [empty, empty ,empty]
        ```
     3. **Delete operator:** Using delete array[index] operator on the array
        ```js
        const justiceLeague = ["Superman", "Aquaman", "Batman"];
        delete justiceLeague[1];
        console.log(justiceLeague); // ['Superman', empty, ,'Batman']
        ```
     4. **Increase length property:** Increasing length property of an array
        ```js
        const justiceLeague = ["Superman", "Aquaman", "Batman"];
        justiceLeague.length = 5;
        console.log(justiceLeague); // ['Superman', 'Aquaman', 'Batman', empty, empty]
        ```

     **[⬆ Back to Top](#table-of-contents)**

429. ### What is the difference between setTimeout, setImmediate and process.nextTick?

     1. **Set Timeout:** setTimeout() is to schedule execution of a one-time callback after delay milliseconds.
     2. **Set Immediate:** The setImmediate function is used to execute a function right after the current event loop finishes.
     3. **Process NextTick:** If process.nextTick() is called in a given phase, all the callbacks passed to process.nextTick() will be resolved before the event loop continues. This will block the event loop and create I/O Starvation if process.nextTick() is called recursively.

     **[⬆ Back to Top](#table-of-contents)**

430. ### How do you reverse an array without modifying original array?

     The `reverse()` method reverses the order of the elements in an array but it mutates the original array. Let's take a simple example to demonistrate this case,

     ```javascript
     const originalArray = [1, 2, 3, 4, 5];
     const newArray = originalArray.reverse();

     console.log(newArray); // [ 5, 4, 3, 2, 1]
     console.log(originalArray); // [ 5, 4, 3, 2, 1]
     ```

     There are few solutions that won't mutate the original array. Let's take a look.

     1. **Using slice and reverse methods:**
        In this case, just invoke the `slice()` method on the array to create a shallow copy followed by `reverse()` method call on the copy.

        ```javascript
        const originalArray = [1, 2, 3, 4, 5];
        const newArray = originalArray.slice().reverse(); //Slice an array gives a new copy

        console.log(originalArray); // [1, 2, 3, 4, 5]
        console.log(newArray); // [ 5, 4, 3, 2, 1]
        ```

     2. **Using spread and reverse methods:**
        In this case, let's use the spread syntax (...) to create a copy of the array followed by `reverse()` method call on the copy.

        ```javascript
        const originalArray = [1, 2, 3, 4, 5];
        const newArray = [...originalArray].reverse();

        console.log(originalArray); // [1, 2, 3, 4, 5]
        console.log(newArray); // [ 5, 4, 3, 2, 1]
        ```

     3. **Using reduce and spread methods:**
        Here execute a reducer function on an array elements and append the accumulated array on right side using spread syntax

        ```javascript
        const originalArray = [1, 2, 3, 4, 5];
        const newArray = originalArray.reduce((accumulator, value) => {
          return [value, ...accumulator];
        }, []);

        console.log(originalArray); // [1, 2, 3, 4, 5]
        console.log(newArray); // [ 5, 4, 3, 2, 1]
        ```

     4. **Using reduceRight and spread methods:**
        Here execute a right reducer function(i.e. opposite direction of reduce method) on an array elements and append the accumulated array on left side using spread syntax

        ```javascript
        const originalArray = [1, 2, 3, 4, 5];
        const newArray = originalArray.reduceRight((accumulator, value) => {
          return [...accumulator, value];
        }, []);

        console.log(originalArray); // [1, 2, 3, 4, 5]
        console.log(newArray); // [ 5, 4, 3, 2, 1]
        ```

     5. **Using reduceRight and push methods:**
        Here execute a right reducer function(i.e. opposite direction of reduce method) on an array elements and push the iterated value to the accumulator

        ```javascript
        const originalArray = [1, 2, 3, 4, 5];
        const newArray = originalArray.reduceRight((accumulator, value) => {
          accumulator.push(value);
          return accumulator;
        }, []);

        console.log(originalArray); // [1, 2, 3, 4, 5]
        console.log(newArray); // [ 5, 4, 3, 2, 1]
        ```

     **[⬆ Back to Top](#table-of-contents)**


432. ### What is global execution context?

     The global execution context is the default or first execution context that is created by the JavaScript engine before any code is executed(i.e, when the file first loads in the browser). All the global code that is not inside a function or object will be executed inside this global execution context. Since JS engine is single threaded there will be only one global environment and there will be only one global execution context.

     For example, the below code other than code inside any function or object is executed inside the global execution context.

     ```javascript
     var x = 10;

     function A() {
       console.log("Start function A");

       function B() {
         console.log("In function B");
       }

       B();
     }

     A();

     console.log("GlobalContext");
     ```

     **[⬆ Back to Top](#table-of-contents)**

433. ### What is function execution context?

     Whenever a function is invoked, the JavaScript engine creates a different type of Execution Context known as a Function Execution Context (FEC) within the Global Execution Context (GEC) to evaluate and execute the code within that function.

     **[⬆ Back to Top](#table-of-contents)**

434. ### What is debouncing?

     Debouncing is a programming pattern that allows delaying execution of some piece of code until a specified time to avoid unnecessary _CPU cycles, API calls and improve performance_. The debounce function make sure that your code is only triggered once per user input. The common usecases are Search box suggestions, text-field auto-saves, and eliminating double-button clicks.

     Let's say you want to show suggestions for a search query, but only after a visitor has finished typing it. So here you write a debounce function where the user keeps writing the characters with in 500ms then previous timer cleared out using `clearTimeout` and reschedule API call/DB query for a new time—300 ms in the future.

     ```js
     function debounce(func, timeout = 500) {
       let timer;
       return (...args) => {
         clearTimeout(timer);
         timer = setTimeout(() => {
           func.apply(this, args);
         }, timeout);
       };
     }
     function fetchResults() {
       console.log("Fetching input suggestions");
     }
     const processChange = debounce(() => fetchResults());
     ```

     The _debounce()_ function can be used on input, button and window events

     **Input:**

     ```html
     <input type="text" onkeyup="processChange()" />
     ```

     **Button:**

     ```html
     <button onclick="processChange()">Click me</button>
     ```

     **Windows event:**

     ```html
     window.addEventListener("scroll", processChange);
     ```

     **[⬆ Back to Top](#table-of-contents)**

435. ### What is throttling?

     Throttling is a technique used to limit the execution of an event handler function, even when this event triggers continuously due to user actions. The common use cases are browser resizing, window scrolling etc.

     The below example creates a throttle function to reduce the number of events for each pixel change and trigger scroll event for each 100ms except for the first event.

     ```js
     const throttle = (func, limit) => {
       let inThrottle;
       return (...args) => {
         if (!inThrottle) {
           func.apply(this, args);
           inThrottle = true;
           setTimeout(() => (inThrottle = false), limit);
         }
       };
     };
     window.addEventListener("scroll", () => {
       throttle(handleScrollAnimation, 100);
     });
     ```

     **[⬆ Back to Top](#table-of-contents)**

436. ### What is optional chaining?

     According to MDN official docs, the optional chaining operator (?.) permits reading the value of a property located deep within a chain of connected objects without having to expressly validate that each reference in the chain is valid.

     The ?. operator is like the . chaining operator, except that instead of causing an error if a reference is nullish (null or undefined), the expression short-circuits with a return value of undefined. When used with function calls, it returns undefined if the given function does not exist.

     ```js
     const adventurer = {
       name: "Alice",
       cat: {
         name: "Dinah",
       },
     };

     const dogName = adventurer.dog?.name;
     console.log(dogName);
     // expected output: undefined

     console.log(adventurer.someNonExistentMethod?.());
     // expected output: undefined
     ```

     **[⬆ Back to Top](#table-of-contents)**

437. ### What is an environment record?

     According to ECMAScript specification 262 (9.1):

     > [Environment Record](https://262.ecma-international.org/12.0/#sec-environment-records) is a specification type used to define the association of Identifiers to specific variables and functions, based upon the lexical nesting structure of ECMAScript code.

     Usually an Environment Record is associated with some specific syntactic structure of ECMAScript code such as a FunctionDeclaration, a BlockStatement, or a Catch clause of a TryStatement.

     Each time such code is evaluated, a new Environment Record is created to record the identifier bindings that are created by that code.

     **[⬆ Back to Top](#table-of-contents)**

438. ### How to verify if a variable is an array?

     It is possible to check if a variable is an array instance using 3 different ways,

     1. Array.isArray() method:

        The `Array.isArray(value)` utility function is used to determine whether value is an array or not. This function returns a true boolean value if the variable is an array and a false value if it is not.

        ```javascript
        const numbers = [1, 2, 3];
        const user = { name: "John" };
        Array.isArray(numbers); // true
        Array.isArray(user); //false
        ```

     2. instanceof operator:

        The instanceof operator is used to check the type of an array at run time. It returns true if the type of a variable is an Array other false for other type.

        ```javascript
        const numbers = [1, 2, 3];
        const user = { name: "John" };
        console.log(numbers instanceof Array); // true
        console.log(user instanceof Array); // false
        ```

     3. Checking constructor type:

        The constructor property of the variable is used to determine whether the variable Array type or not.

        ```javascript
        const numbers = [1, 2, 3];
        const user = { name: "John" };
        console.log(numbers.constructor === Array); // true
        console.log(user.constructor === Array); // false
        ```

     **[⬆ Back to Top](#table-of-contents)**

439. ### What is pass by value and pass by reference?

     Pass-by-value creates a new space in memory and makes a copy of a value. Primitives such as string, number, boolean etc will actually create a new copy. Hence, updating one value doesn't impact the other value. i.e, The values are independent of each other.

     ```javascript
     let a = 5;
     let b = a;

     b++;
     console.log(a, b); //5, 6
     ```

     In the above code snippet, the value of `a` is assigned to `b` and the variable `b` has been incremented. Since there is a new space created for variable `b`, any update on this variable doesn't impact the variable `a`.

     Pass by reference doesn't create a new space in memory but the new variable adopts a memory address of an initial variable. Non-primitives such as objects, arrays and functions gets the reference of the initiable variable. i.e, updating one value will impact the other variable.

     ```javascript
     let user1 = {
       name: "John",
       age: 27,
     };
     let user2 = user1;
     user2.age = 30;

     console.log(user1.age, user2.age); // 30, 30
     ```

     In the above code snippet, updating the `age` property of one object will impact the other property due to the same reference.

     **[⬆ Back to Top](#table-of-contents)**

440. ### What are the differences between primitives and non-primitives?

     JavaScript language has both primitives and non-primitives but there are few differences between them as below,

     | Primitives                 | Non-primitives       |
     | -------------------------- | -------------------- |
     | These types are predefined | Created by developer |
     | These are immutable        | Mutable              |
     | Compare by value           | Compare by reference |
     | Stored in Stack            | Stored in heap       |
     | Contain certain value      | Can contain NULL too |

     **[⬆ Back to Top](#table-of-contents)**


442. ### What are the differences between pure and impure functions?

     Some of the major differences between pure and impure function are as below,

| Pure function                       | Impure function                                                       |
| ----------------------------------- | --------------------------------------------------------------------- |
| It has no side effects              | It causes side effects                                                |
| Does not modify the state of the    | It can modify the state of the application                           | application
| It is always return the same result | It returns different result on each call                              |
| Easy to read and debug              | It can modify the state of the application |

**[⬆ Back to Top](#table-of-contents)**


448. ### What is module pattern?

     Module pattern is a designed pattern used to wrap a set of variables and functions together in a single scope returned as an object. JavaScript doesn't have access specifiers similar to other languages(Java, Python, etc) to provide private scope. It uses IIFE (Immediately invoked function expression) to allow for private scopes. i.e., a closure that protect variables and methods.

     The module pattern looks like below,

     ```javascript
     (function () {
       // Private variables or functions goes here.

       return {
         // Return public variables or functions here.
       };
     })();
     ```

     Let's see an example of a module pattern for an employee with private and public access,

     ```javascript
     const createEmployee = (function () {
       // Private
       const name = "John";
       const department = "Sales";
       const getEmployeeName = () => name;
       const getDepartmentName = () => department;

       // Public
       return {
         name,
         department,
         getName: () => getEmployeeName(),
         getDepartment: () => getDepartmentName(),
       };
     })();

     console.log(createEmployee.name);
     console.log(createEmployee.department);
     console.log(createEmployee.getName());
     console.log(createEmployee.getDepartment());
     ```

     **Note:** It mimic the concepts of classes with private variables and methods.

     **[⬆ Back to Top](#table-of-contents)**

449. ### What is Function Composition?

     It is an approach where the result of one function is passed on to the next function, which is passed to another until the final function is executed for the final result.

     ```javascript
     //example
     const double = (x) => x * 2;
     const square = (x) => x * x;

     var output1 = double(2);
     var output2 = square(output1);
     console.log(output2);

     var output_final = square(double(2));
     console.log(output_final);
     ```

     **[⬆ Back to Top](#table-of-contents)**


### Coding Exercise

#### 1. What is the output of below code

```javascript
var car = new Vehicle("Honda", "white", "2010", "UK");
console.log(car);

function Vehicle(model, color, year, country) {
  this.model = model;
  this.color = color;
  this.year = year;
  this.country = country;
}
```

- 1: Undefined
- 2: ReferenceError
- 3: null
- 4: {model: "Honda", color: "white", year: "2010", country: "UK"}

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 4

The function declarations are hoisted similar to any variables. So the placement for `Vehicle` function declaration doesn't make any difference.

</p>
</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 2. What is the output of below code

```javascript
function foo() {
  let x = (y = 0);
  x++;
  y++;
  return x;
}

console.log(foo(), typeof x, typeof y);
```

- 1: 1, undefined and undefined
- 2: ReferenceError: X is not defined
- 3: 1, undefined and number
- 4: 1, number and number

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 3

Of course the return value of `foo()` is 1 due to the increment operator. But the statement `let x = y = 0` declares a local variable x. Whereas y declared as a global variable accidentally. This statement is equivalent to,

```javascript
let x;
window.y = 0;
x = window.y;
```

Since the block scoped variable x is undefined outside of the function, the type will be undefined too. Whereas the global variable `y` is available outside the function, the value is 0 and type is number.

</p>
</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 3. What is the output of below code

```javascript
function main() {
  console.log("A");
  setTimeout(function print() {
    console.log("B");
  }, 0);
  console.log("C");
}
main();
```

- 1: A, B and C
- 2: B, A and C
- 3: A and C
- 4: A, C and B

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 4

The statements order is based on the event loop mechanism. The order of statements follows the below order,

1. At first, the main function is pushed to the stack.
2. Then the browser pushes the first statement of the main function( i.e, A's console.log) to the stack, executing and popping out immediately.
3. But `setTimeout` statement moved to Browser API to apply the delay for callback.
4. In the meantime, C's console.log added to stack, executed and popped out.
5. The callback of `setTimeout` moved from Browser API to message queue.
6. The `main` function popped out from stack because there are no statements to execute
7. The callback moved from message queue to the stack since the stack is empty.
8. The console.log for B is added to the stack and display on the console.

</p>
</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 4. What is the output of below equality check

```javascript
console.log(0.1 + 0.2 === 0.3);
```

- 1: false
- 2: true

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 1

This is due to the float point math problem. Since the floating point numbers are encoded in binary format, the addition operations on them lead to rounding errors. Hence, the comparison of floating points doesn't give expected results.
You can find more details about the explanation here [0.30000000000000004.com/](https://0.30000000000000004.com/)

</p>
</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 5. What is the output of below code

```javascript
var y = 1;
if (function f() {}) {
  y += typeof f;
}
console.log(y);
```

- 1: 1function
- 2: 1object
- 3: ReferenceError
- 4: 1undefined

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 4

The main points in the above code snippets are,

1. You can see function expression instead function declaration inside if statement. So it always returns true.
2. Since it is not declared(or assigned) anywhere, f is undefined and typeof f is undefined too.

In other words, it is same as

```javascript
var y = 1;
if ("foo") {
  y += typeof f;
}
console.log(y);
```

**Note:** It returns 1object for MS Edge browser

</p>
</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 6. What is the output of below code

```javascript
function foo() {
  return;
  {
    message: "Hello World";
  }
}
console.log(foo());
```

- 1: Hello World
- 2: Object {message: "Hello World"}
- 3: Undefined
- 4: SyntaxError

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 3

This is a semicolon issue. Normally semicolons are optional in JavaScript. So if there are any statements(in this case, return) missing semicolon, it is automatically inserted immediately. Hence, the function returned as undefined.

Whereas if the opening curly brace is along with the return keyword then the function is going to be returned as expected.

```javascript
function foo() {
  return {
    message: "Hello World",
  };
}
console.log(foo()); // {message: "Hello World"}
```

</p>
</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 7. What is the output of below code

```javascript
var myChars = ["a", "b", "c", "d"];
delete myChars[0];
console.log(myChars);
console.log(myChars[0]);
console.log(myChars.length);
```

- 1: [empty, 'b', 'c', 'd'], empty, 3
- 2: [null, 'b', 'c', 'd'], empty, 3
- 3: [empty, 'b', 'c', 'd'], undefined, 4
- 4: [null, 'b', 'c', 'd'], undefined, 4

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 3

The `delete` operator will delete the object property but it will not reindex the array or change its length. So the number or elements or length of the array won't be changed.
If you try to print myChars then you can observe that it doesn't set an undefined value, rather the property is removed from the array. The newer versions of Chrome use `empty` instead of `undefined` to make the difference a bit clearer.

</p>
</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 8. What is the output of below code in latest Chrome

```javascript
var array1 = new Array(3);
console.log(array1);

var array2 = [];
array2[2] = 100;
console.log(array2);

var array3 = [, , ,];
console.log(array3);
```

- 1: [undefined × 3], [undefined × 2, 100], [undefined × 3]
- 2: [empty × 3], [empty × 2, 100], [empty × 3]
- 3: [null × 3], [null × 2, 100], [null × 3]
- 4: [], [100], []

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 2

The latest chrome versions display `sparse array`(they are filled with holes) using this empty x n notation. Whereas the older versions have undefined x n notation.
**Note:** The latest version of FF displays `n empty slots` notation.

</p>
</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 9. What is the output of below code

```javascript
const obj = {
  prop1: function () {
    return 0;
  },
  prop2() {
    return 1;
  },
  ["prop" + 3]() {
    return 2;
  },
};

console.log(obj.prop1());
console.log(obj.prop2());
console.log(obj.prop3());
```

- 1: 0, 1, 2
- 2: 0, { return 1 }, 2
- 3: 0, { return 1 }, { return 2 }
- 4: 0, 1, undefined

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 1

ES6 provides method definitions and property shorthands for objects. So both prop2 and prop3 are treated as regular function values.

</p>
</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 10. What is the output of below code

```javascript
console.log(1 < 2 < 3);
console.log(3 > 2 > 1);
```

- 1: true, true
- 2: true, false
- 3: SyntaxError, SyntaxError,
- 4: false, false

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 2

The important point is that if the statement contains the same operators(e.g, < or >) then it can be evaluated from left to right.
The first statement follows the below order,

1. console.log(1 < 2 < 3);
2. console.log(true < 3);
3. console.log(1 < 3); // True converted as `1` during comparison
4. True

Whereas the second statement follows the below order,

1. console.log(3 > 2 > 1);
2. console.log(true > 1);
3. console.log(1 > 1); // False converted as `0` during comparison
4. False

</p>
</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 11. What is the output of below code in non-strict mode

```javascript
function printNumbers(first, second, first) {
  console.log(first, second, first);
}
printNumbers(1, 2, 3);
```

- 1: 1, 2, 3
- 2: 3, 2, 3
- 3: SyntaxError: Duplicate parameter name not allowed in this context
- 4: 1, 2, 1

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 2

In non-strict mode, the regular JavaScript functions allow duplicate named parameters. The above code snippet has duplicate parameters on 1st and 3rd parameters.
The value of the first parameter is mapped to the third argument which is passed to the function. Hence, the 3rd argument overrides the first parameter.

**Note:** In strict mode, duplicate parameters will throw a Syntax Error.

</p>
</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 12. What is the output of below code

```javascript
const printNumbersArrow = (first, second, first) => {
  console.log(first, second, first);
};
printNumbersArrow(1, 2, 3);
```

- 1: 1, 2, 3
- 2: 3, 2, 3
- 3: SyntaxError: Duplicate parameter name not allowed in this context
- 4: 1, 2, 1

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 3

Unlike regular functions, the arrow functions doesn't not allow duplicate parameters in either strict or non-strict mode. So you can see `SyntaxError` in the console.

</p>
</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 13. What is the output of below code

```javascript
const arrowFunc = () => arguments.length;
console.log(arrowFunc(1, 2, 3));
```

- 1: ReferenceError: arguments is not defined
- 2: 3
- 3: undefined
- 4: null

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 1

Arrow functions do not have an `arguments, super, this, or new.target` bindings. So any reference to `arguments` variable tries to resolve to a binding in a lexically enclosing environment. In this case, the arguments variable is not defined outside of the arrow function. Hence, you will receive a reference error.

Where as the normal function provides the number of arguments passed to the function

```javascript
const func = function () {
  return arguments.length;
};
console.log(func(1, 2, 3));
```

But If you still want to use an arrow function then rest operator on arguments provides the expected arguments

```javascript
const arrowFunc = (...args) => args.length;
console.log(arrowFunc(1, 2, 3));
```

</p>
</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 14. What is the output of below code

```javascript
console.log(String.prototype.trimLeft.name === "trimLeft");
console.log(String.prototype.trimLeft.name === "trimStart");
```

- 1: True, False
- 2: False, True

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 2

In order to be consistent with functions like `String.prototype.padStart`, the standard method name for trimming the whitespaces is considered as `trimStart`. Due to web web compatibility reasons, the old method name 'trimLeft' still acts as an alias for 'trimStart'. Hence, the prototype for 'trimLeft' is always 'trimStart'

</p>
</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 15. What is the output of below code

```javascript
console.log(Math.max());
```

- 1: undefined
- 2: Infinity
- 3: 0
- 4: -Infinity

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 4

-Infinity is the initial comparant because almost every other value is bigger. So when no arguments are provided, -Infinity is going to be returned.
**Note:** Zero number of arguments is a valid case.

</p>
</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 16. What is the output of below code

```javascript
console.log(10 == [10]);
console.log(10 == [[[[[[[10]]]]]]]);
```

- 1: True, True
- 2: True, False
- 3: False, False
- 4: False, True

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 1

As per the comparison algorithm in the ECMAScript specification(ECMA-262), the above expression converted into JS as below

```javascript
10 === Number([10].valueOf().toString()); // 10
```

So it doesn't matter about number brackets([]) around the number, it is always converted to a number in the expression.

</p>
</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 17. What is the output of below code

```javascript
console.log(10 + "10");
console.log(10 - "10");
```

- 1: 20, 0
- 2: 1010, 0
- 3: 1010, 10-10
- 4: NaN, NaN

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 2

The concatenation operator(+) is applicable for both number and string types. So if any operand is string type then both operands concatenated as strings. Whereas subtract(-) operator tries to convert the operands as number type.

</p>
</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 18. What is the output of below code

```javascript
console.log([0] == false);
if ([0]) {
  console.log("I'm True");
} else {
  console.log("I'm False");
}
```

- 1: True, I'm True
- 2: True, I'm False
- 3: False, I'm True
- 4: False, I'm False

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 1

In comparison operators, the expression `[0]` converted to Number([0].valueOf().toString()) which is resolved to false. Whereas `[0]` just becomes a truthy value without any conversion because there is no comparison operator.

</p>
</details>

#### 19. What is the output of below code

```javascript
console.log([1, 2] + [3, 4]);
```

- 1: [1,2,3,4]
- 2: [1,2][3,4]
- 3: SyntaxError
- 4: 1,23,4

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 4

The + operator is not meant or defined for arrays. So it converts arrays into strings and concatenates them.

</p>
</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 20. What is the output of below code

```javascript
const numbers = new Set([1, 1, 2, 3, 4]);
console.log(numbers);

const browser = new Set("Firefox");
console.log(browser);
```

- 1: {1, 2, 3, 4}, {"F", "i", "r", "e", "f", "o", "x"}
- 2: {1, 2, 3, 4}, {"F", "i", "r", "e", "o", "x"}
- 3: [1, 2, 3, 4], ["F", "i", "r", "e", "o", "x"]
- 4: {1, 1, 2, 3, 4}, {"F", "i", "r", "e", "f", "o", "x"}

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 1

Since `Set` object is a collection of unique values, it won't allow duplicate values in the collection. At the same time, it is case sensitive data structure.

</p>
</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 21. What is the output of below code

```javascript
console.log(NaN === NaN);
```

- 1: True
- 2: False

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 2

JavaScript follows IEEE 754 spec standards. As per this spec, NaNs are never equal for floating-point numbers.

</p>
</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 22. What is the output of below code

```javascript
let numbers = [1, 2, 3, 4, NaN];
console.log(numbers.indexOf(NaN));
```

- 1: 4
- 2: NaN
- 3: SyntaxError
- 4: -1

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 4

The `indexOf` uses strict equality operator(===) internally and `NaN === NaN` evaluates to false. Since indexOf won't be able to find NaN inside an array, it returns -1 always.
But you can use `Array.prototype.findIndex` method to find out the index of NaN in an array or You can use `Array.prototype.includes` to check if NaN is present in an array or not.

```javascript
let numbers = [1, 2, 3, 4, NaN];
console.log(numbers.findIndex(Number.isNaN)); // 4

console.log(numbers.includes(NaN)); // true
```

</p>
</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 23. What is the output of below code

```javascript
let [a, ...b] = [1, 2, 3, 4, 5];
console.log(a, b);
```

- 1: 1, [2, 3, 4, 5]
- 2: 1, {2, 3, 4, 5}
- 3: SyntaxError
- 4: 1, [2, 3, 4]

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 3

When using rest parameters, trailing commas are not allowed and will throw a SyntaxError.
If you remove the trailing comma then it displays 1st answer

```javascript
let [a, ...b] = [1, 2, 3, 4, 5];
console.log(a, b); // 1, [2, 3, 4, 5]
```

</p>
</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 25. What is the output of below code

```javascript
async function func() {
  return 10;
}
console.log(func());
```

- 1: Promise {\<fulfilled\>: 10}
- 2: 10
- 3: SyntaxError
- 4: Promise {\<rejected\>: 10}

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 1

Async functions always return a promise. But even if the return value of an async function is not explicitly a promise, it will be implicitly wrapped in a promise. The above async function is equivalent to below expression,

```javascript
function func() {
  return Promise.resolve(10);
}
```

</p>
</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 26. What is the output of below code

```javascript
async function func() {
  await 10;
}
console.log(func());
```

- 1: Promise {\<fulfilled\>: 10}
- 2: 10
- 3: SyntaxError
- 4: Promise {\<resolved\>: undefined}

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 4

The await expression returns value 10 with promise resolution and the code after each await expression can be treated as existing in a `.then` callback. In this case, there is no return expression at the end of the function. Hence, the default return value of `undefined` is returned as the resolution of the promise. The above async function is equivalent to below expression,

```javascript
function func() {
  return Promise.resolve(10).then(() => undefined);
}
```

</p>
</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 27. What is the output of below code

```javascript
function delay() {
  return new Promise(resolve => setTimeout(resolve, 2000));
}

async function delayedLog(item) {
  await delay();
  console.log(item);
}

async function processArray(array) {
  array.forEach(item => {
    await delayedLog(item);
  })
}

processArray([1, 2, 3, 4]);
```

- 1: SyntaxError
- 2: 1, 2, 3, 4
- 3: 4, 4, 4, 4
- 4: 4, 3, 2, 1

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 1

Even though “processArray” is an async function, the anonymous function that we use for `forEach` is synchronous. If you use await inside a synchronous function then it throws a syntax error.

</p>

</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 28. What is the output of below code

```javascript
function delay() {
  return new Promise((resolve) => setTimeout(resolve, 2000));
}

async function delayedLog(item) {
  await delay();
  console.log(item);
}

async function process(array) {
  array.forEach(async (item) => {
    await delayedLog(item);
  });
  console.log("Process completed!");
}
process([1, 2, 3, 5]);
```

- 1: 1 2 3 5 and Process completed!
- 2: 5 5 5 5 and Process completed!
- 3: Process completed! and 5 5 5 5
- 4: Process completed! and 1 2 3 5

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 4

The forEach method will not wait until all items are finished but it just runs the tasks and goes next. Hence, the last statement is displayed first followed by a sequence of promise resolutions.

But you control the array sequence using for..of loop,

```javascript
async function processArray(array) {
  for (const item of array) {
    await delayedLog(item);
  }
  console.log("Process completed!");
}
```

</p>
</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 29. What is the output of below code

```javascript
var set = new Set();
set.add("+0").add("-0").add(NaN).add(undefined).add(NaN);
console.log(set);
```

- 1: Set(4) {"+0", "-0", NaN, undefined}
- 2: Set(3) {"+0", NaN, undefined}
- 3: Set(5) {"+0", "-0", NaN, undefined, NaN}
- 4: Set(4) {"+0", NaN, undefined, NaN}

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 1

Set has few exceptions from equality check,

1. All NaN values are equal
2. Both +0 and -0 considered as different values

</p>
</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 30. What is the output of below code

```javascript
const sym1 = Symbol("one");
const sym2 = Symbol("one");

const sym3 = Symbol.for("two");
const sym4 = Symbol.for("two");

console.log(sym1 === sym2, sym3 === sym4);
```

- 1: true, true
- 2: true, false
- 3: false, true
- 4: false, false

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 3

Symbol follows below conventions,

1. Every symbol value returned from Symbol() is unique irrespective of the optional string.
2. `Symbol.for()` function creates a symbol in a global symbol registry list. But it doesn't necessarily create a new symbol on every call, it checks first if a symbol with the given key is already present in the registry and returns the symbol if it is found. Otherwise a new symbol created in the registry.

**Note:** The symbol description is just useful for debugging purposes.

</p>

</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 31. What is the output of below code

```javascript
const sym1 = new Symbol("one");
console.log(sym1);
```

- 1: SyntaxError
- 2: one
- 3: Symbol('one')
- 4: Symbol

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 1

`Symbol` is a just a standard function and not an object constructor(unlike other primitives new Boolean, new String and new Number). So if you try to call it with the new operator will result in a TypeError

</p>

</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 32. What is the output of below code

```javascript
let myNumber = 100;
let myString = "100";

if (!typeof myNumber === "string") {
  console.log("It is not a string!");
} else {
  console.log("It is a string!");
}

if (!typeof myString === "number") {
  console.log("It is not a number!");
} else {
  console.log("It is a number!");
}
```

- 1: SyntaxError
- 2: It is not a string!, It is not a number!
- 3: It is not a string!, It is a number!
- 4: It is a string!, It is a number!

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 4

The return value of `typeof myNumber` or `typeof myString` is always a truthy value (either "number" or "string"). The ! operator operates on either `typeof myNumber` or `typeof myString`, converting them to boolean values. Since the value of both `!typeof myNumber` and `!typeof myString` is false, the if condition fails, and control goes to else block.

To make the ! operator operate on the equality expression, one needs to add parentheses:

```
if (!(typeof myNumber === "string"))
```

Or simply use the inequality operator:

```
if (typeof myNumber !== "string")
```

</p>

</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 33. What is the output of below code

```javascript
console.log(
  JSON.stringify({ myArray: ["one", undefined, function () {}, Symbol("")] })
);
console.log(
  JSON.stringify({ [Symbol.for("one")]: "one" }, [Symbol.for("one")])
);
```

- 1: {"myArray":['one', undefined, {}, Symbol]}, {}
- 2: {"myArray":['one', null,null,null]}, {}
- 3: {"myArray":['one', null,null,null]}, "{ [Symbol.for('one')]: 'one' }, [Symbol.for('one')]"
- 4: {"myArray":['one', undefined, function(){}, Symbol('')]}, {}

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 2

The symbols has below constraints,

1. The undefined, Functions, and Symbols are not valid JSON values. So those values are either omitted (in an object) or changed to null (in an array). Hence, it returns null values for the value array.
2. All Symbol-keyed properties will be completely ignored. Hence it returns an empty object({}).

</p>

</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 34. What is the output of below code

```javascript
class A {
  constructor() {
    console.log(new.target.name);
  }
}

class B extends A {
  constructor() {
    super();
  }
}

new A();
new B();
```

- 1: A, A
- 2: A, B

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 2

Using constructors, `new.target` refers to the constructor (points to the class definition of class which is initialized) that was directly invoked by new. This also applies to the case if the constructor is in a parent class and was delegated from a child constructor.

</p>

</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 35. What is the output of below code

```javascript
const [x, ...y, z] = [1, 2, 3, 4];
console.log(x, y, z);
```

- 1: 1, [2, 3], 4
- 2: 1, [2, 3, 4], undefined
- 3: 1, [2], 3
- 4: SyntaxError

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 4

It throws a syntax error because the rest element should not have a trailing comma. You should always consider using a rest operator as the last element.

</p>

</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 36. What is the output of below code

```javascript
const { a: x = 10, b: y = 20 } = { a: 30 };

console.log(x);
console.log(y);
```

- 1: 30, 20
- 2: 10, 20
- 3: 10, undefined
- 4: 30, undefined

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 1

The object property follows below rules,

1. The object properties can be retrieved and assigned to a variable with a different name
2. The property assigned a default value when the retrieved value is `undefined`

</p>

</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 37. What is the output of below code

```javascript
function area({ length = 10, width = 20 }) {
  console.log(length * width);
}

area();
```

- 1: 200
- 2: Error
- 3: undefined
- 4: 0

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 2

If you leave out the right-hand side assignment for the destructuring object, the function will look for at least one argument to be supplied when invoked. Otherwise you will receive an error `Error: Cannot read property 'length' of undefined` as mentioned above.

You can avoid the error with either of the below changes,

1. **Pass at least an empty object:**

```javascript
function area({ length = 10, width = 20 }) {
  console.log(length * width);
}

area({});
```

2. **Assign default empty object:**

```javascript
function area({ length = 10, width = 20 } = {}) {
  console.log(length * width);
}

area();
```

</p>

</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 38. What is the output of below code

```javascript
const props = [
  { id: 1, name: "John" },
  { id: 2, name: "Jack" },
  { id: 3, name: "Tom" },
];

const [, , { name }] = props;
console.log(name);
```

- 1: Tom
- 2: Error
- 3: undefined
- 4: John

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 1

It is possible to combine Array and Object destructuring. In this case, the third element in the array props accessed first followed by name property in the object.

</p>

</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 39. What is the output of below code

```javascript
function checkType(num = 1) {
  console.log(typeof num);
}

checkType();
checkType(undefined);
checkType("");
checkType(null);
```

- 1: number, undefined, string, object
- 2: undefined, undefined, string, object
- 3: number, number, string, object
- 4: number, number, number, number

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 3

If the function argument is set implicitly(not passing argument) or explicitly to undefined, the value of the argument is the default parameter. Whereas for other falsy values('' or null), the value of the argument is passed as a parameter.

Hence, the result of function calls categorized as below,

1. The first two function calls logs number type since the type of default value is number
2. The type of '' and null values are string and object type respectively.

</p>

</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 40. What is the output of below code

```javascript
function add(item, items = []) {
  items.push(item);
  return items;
}

console.log(add("Orange"));
console.log(add("Apple"));
```

- 1: ['Orange'], ['Orange', 'Apple']
- 2: ['Orange'], ['Apple']

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 2

Since the default argument is evaluated at call time, a new object is created each time the function is called. So in this case, the new array is created and an element pushed to the default empty array.

</p>

</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 41. What is the output of below code

```javascript
function greet(greeting, name, message = greeting + " " + name) {
  console.log([greeting, name, message]);
}

greet("Hello", "John");
greet("Hello", "John", "Good morning!");
```

- 1: SyntaxError
- 2: ['Hello', 'John', 'Hello John'], ['Hello', 'John', 'Good morning!']

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 2

Since parameters defined earlier are available to later default parameters, this code snippet doesn't throw any error.

</p>

</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 42. What is the output of below code

```javascript
function outer(f = inner()) {
  function inner() {
    return "Inner";
  }
}
outer();
```

- 1: ReferenceError
- 2: Inner

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 1

The functions and variables declared in the function body cannot be referred from default value parameter initializers. If you still try to access, it throws a run-time ReferenceError(i.e, `inner` is not defined).

</p>

</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 43. What is the output of below code

```javascript
function myFun(x, y, ...manyMoreArgs) {
  console.log(manyMoreArgs);
}

myFun(1, 2, 3, 4, 5);
myFun(1, 2);
```

- 1: [3, 4, 5], undefined
- 2: SyntaxError
- 3: [3, 4, 5], []
- 4: [3, 4, 5], [undefined]

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 3

The rest parameter is used to hold the remaining parameters of a function and it becomes an empty array if the argument is not provided.

</p>

</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 44. What is the output of below code

```javascript
const obj = { key: "value" };
const array = [...obj];
console.log(array);
```

- 1: ['key', 'value']
- 2: TypeError
- 3: []
- 4: ['key']

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 2

Spread syntax can be applied only to iterable objects. By default, Objects are not iterable, but they become iterable when used in an Array, or with iterating functions such as `map(), reduce(), and assign()`. If you still try to do it, it still throws `TypeError: obj is not iterable`.

</p>

</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 45. What is the output of below code

```javascript
function* myGenFunc() {
  yield 1;
  yield 2;
  yield 3;
}
var myGenObj = new myGenFunc();
console.log(myGenObj.next().value);
```

- 1: 1
- 2: undefined
- 3: SyntaxError
- 4: TypeError

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 4

Generators are not constructible type. But if you still proceed to do, there will be an error saying "TypeError: myGenFunc is not a constructor"

</p>

</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 46. What is the output of below code

```javascript
function* yieldAndReturn() {
  yield 1;
  return 2;
  yield 3;
}

var myGenObj = yieldAndReturn();
console.log(myGenObj.next());
console.log(myGenObj.next());
console.log(myGenObj.next());
```

- 1: { value: 1, done: false }, { value: 2, done: true }, { value: undefined, done: true }
- 2: { value: 1, done: false }, { value: 2, done: false }, { value: undefined, done: true }
- 3: { value: 1, done: false }, { value: 2, done: true }, { value: 3, done: true }
- 4: { value: 1, done: false }, { value: 2, done: false }, { value: 3, done: true }

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 1

A return statement in a generator function will make the generator finish. If a value is returned, it will be set as the value property of the object and done property to true. When a generator is finished, subsequent next() calls return an object of this form: `{value: undefined, done: true}`.

</p>

</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 47. What is the output of below code

```javascript
const myGenerator = (function* () {
  yield 1;
  yield 2;
  yield 3;
})();
for (const value of myGenerator) {
  console.log(value);
  break;
}

for (const value of myGenerator) {
  console.log(value);
}
```

- 1: 1,2,3 and 1,2,3
- 2: 1,2,3 and 4,5,6
- 3: 1 and 1
- 4: 1

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 4

The generator should not be re-used once the iterator is closed. i.e, Upon exiting a loop(on completion or using break & return), the generator is closed and trying to iterate over it again does not yield any more results. Hence, the second loop doesn't print any value.

</p>

</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 48. What is the output of below code

```javascript
const num = 0o38;
console.log(num);
```

- 1: SyntaxError
- 2: 38

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 1

If you use an invalid number(outside of 0-7 range) in the octal literal, JavaScript will throw a SyntaxError. In ES5, it treats the octal literal as a decimal number.

</p>

</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 49. What is the output of below code

```javascript
const squareObj = new Square(10);
console.log(squareObj.area);

class Square {
  constructor(length) {
    this.length = length;
  }

  get area() {
    return this.length * this.length;
  }

  set area(value) {
    this.area = value;
  }
}
```

- 1: 100
- 2: ReferenceError

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 2

Unlike function declarations, class declarations are not hoisted. i.e, First You need to declare your class and then access it, otherwise it will throw a ReferenceError "Uncaught ReferenceError: Square is not defined".

**Note:** Class expressions also applies to the same hoisting restrictions of class declarations.

</p>

</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 50. What is the output of below code

```javascript
function Person() {}

Person.prototype.walk = function () {
  return this;
};

Person.run = function () {
  return this;
};

let user = new Person();
let walk = user.walk;
console.log(walk());

let run = Person.run;
console.log(run());
```

- 1: undefined, undefined
- 2: Person, Person
- 3: SyntaxError
- 4: Window, Window

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 4

When a regular or prototype method is called without a value for **this**, the methods return an initial this value if the value is not undefined. Otherwise global window object will be returned. In our case, the initial `this` value is undefined so both methods return window objects.

</p>

</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 51. What is the output of below code

```javascript
class Vehicle {
  constructor(name) {
    this.name = name;
  }

  start() {
    console.log(`${this.name} vehicle started`);
  }
}

class Car extends Vehicle {
  start() {
    console.log(`${this.name} car started`);
    super.start();
  }
}

const car = new Car("BMW");
console.log(car.start());
```

- 1: SyntaxError
- 2: BMW vehicle started, BMW car started
- 3: BMW car started, BMW vehicle started
- 4: BMW car started, BMW car started

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 3

The super keyword is used to call methods of a superclass. Unlike other languages the super invocation doesn't need to be a first statement. i.e, The statements will be executed in the same order of code.

</p>

</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 52. What is the output of below code

```javascript
const USER = { age: 30 };
USER.age = 25;
console.log(USER.age);
```

- 1: 30
- 2: 25
- 3: Uncaught TypeError
- 4: SyntaxError

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 2

Even though we used constant variables, the content of it is an object and the object's contents (e.g properties) can be altered. Hence, the change is going to be valid in this case.

</p>

</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 53. What is the output of below code

```javascript
console.log("🙂" === "🙂");
```

- 1: false
- 2: true

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 2

Emojis are unicodes and the unicode for smile symbol is "U+1F642". The unicode comparision of same emojies is equivalent to string comparison. Hence, the output is always true.

</p>

</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 54. What is the output of below code?

```javascript
console.log(typeof typeof typeof true);
```

- 1: string
- 2: boolean
- 3: NaN
- 4: number

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 1

The typeof operator on any primitive returns a string value. So even if you apply the chain of typeof operators on the return value, it is always string.

</p>

</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 55. What is the output of below code?

```javascript
let zero = new Number(0);

if (zero) {
  console.log("If");
} else {
  console.log("Else");
}
```

- 1: If
- 2: Else
- 3: NaN
- 4: SyntaxError

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 1

1. The type of operator on new Number always returns object. i.e, typeof new Number(0) --> object.
2. Objects are always truthy in if block

Hence the above code block always goes to if section.

</p>

</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 55. What is the output of below code in non strict mode?

```javascript
let msg = "Good morning!!";

msg.name = "John";

console.log(msg.name);
```

- 1: ""
- 2: Error
- 3: John
- 4: Undefined

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 4

It returns undefined for non-strict mode and returns Error for strict mode. In non-strict mode, the wrapper object is going to be created and get the mentioned property. But the object get disappeared after accessing the property in next line.

</p>

</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 56. What is the output of below code?

```javascript
let count = 10;

(function innerFunc() {
  if (count === 10) {
    let count = 11;
    console.log(count);
  }
  console.log(count);
})();
```

- 1: 11, 10
- 2: 11, 11
- 3: 10, 11
- 4: 10, 10

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 1

11 and 10 is logged to the console.

The innerFunc is a closure which captures the count variable from the outerscope. i.e, 10. But the conditional has another local variable `count` which overwrites the ourter `count` variable. So the first console.log displays value 11.
Whereas the second console.log logs 10 by capturing the count variable from outerscope.

</p>

</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 57. What is the output of below code ?

- 1: console.log(true && 'hi');
- 2: console.log(true && 'hi' && 1);
- 3: console.log(true && '' && 0);

<details><summary><b>Answer</b></summary>
  
 - 1: hi
 - 2: 1
 - 3: ''
  
 Reason : The operator returns the value of the first falsy operand encountered when evaluating from left to right, or the value of the last operand if they are all truthy.

**Note:** Below these values are consider as falsy value

- 1: 0
- 2: ''
- 3: null
- 4: undefined
- 5: NAN

</p>
</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 58. What is the output of below code ?

```javascript
let arr = [1, 2, 3];
let str = "1,2,3";

console.log(arr == str);
```

- 1: false
- 2: Error
- 3: true

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 3

Arrays have their own implementation of `toString` method that returns a comma-separated list of elements. So the above code snippet returns true. In order to avoid conversion of array type, we should use === for comparison.

</p>

</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 59. What is the output of below code?

```javascript
getMessage();

var getMessage = () => {
  console.log("Good morning");
};
```

- 1: Good morning
- 2: getMessage is not a function
- 3: getMessage is not defined
- 4: Undefined

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 2

Hoisting will move variables and functions to be the top of scope. Even though getMessage is an arrow function the above function will considered as a varible due to it's variable declaration or assignment. So the variables will have undefined value in memory phase and throws an error '`getMessage` is not a function' at the code execution phase.

</p>

</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 60. What is the output of below code?

```javascript
let quickPromise = Promise.resolve();

quickPromise.then(() => console.log("promise finished"));

console.log("program finished");
```

- 1: program finished
- 2: Cannot predict the order
- 3: program finished, promise finished
- 4: promise finished, program finished

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 3

Even though a promise is resolved immediately, it won't be executed immediately because its **.then/catch/finally** handlers or callbacks(aka task) are pushed into the queue. Whenever the JavaScript engine becomes free from the current program, it pulls a task from the queue and executes it. This is the reason why last statement is printed first before the log of promise handler.

**Note:** We call the above queue as "MicroTask Queue"

</p>

</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 61. What is the output of below code?

```javascript
console
  .log("First line")
  [("a", "b", "c")].forEach((element) => console.log(element));
console.log("Third line");
```

- 1: `First line`, then print `a, b, c` in a new line, and finally print `Third line` as next line
- 2: `First line`, then print `a, b, c` in a first line, and print `Third line` as next line
- 3: Missing semi-colon error
- 4: Cannot read properties of undefined

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 4

When JavaScript encounters a line break without a semicolon, the JavaScript parser will automatically add a semicolon based on a set of rules called `Automatic Semicolon Insertion` which determines whether line break as end of statement or not to insert semicolon. But it does not assume a semicolon before square brackets [...]. So the first two lines considered as a single statement as below.

```javascript
console
  .log("First line")
  [("a", "b", "c")].forEach((element) => console.log(element));
```

Hence, there will be **cannot read properties of undefined** error while applying the array square bracket on log function.

</p>

</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 62. Write a function that returns a random HEX color

<details><summary><b>Solution 1 (Iterative generation)</b></summary>
<p>

```javascript
const HEX_ALPHABET = [
  "0",
  "1",
  "2",
  "3",
  "4",
  "5",
  "6",
  "7",
  "8",
  "9",
  "a",
  "b",
  "c",
  "d",
  "e",
  "f",
];
const HEX_PREFIX = "#";
const HEX_LENGTH = 6;

function generateRandomHex() {
  let randomHex = "";

  for (let i = 0; i < HEX_LENGTH; i++) {
    const randomIndex = Math.floor(Math.random() * HEX_ALPHABET.length);
    randomHex += HEX_ALPHABET[randomIndex];
  }

  return HEX_PREFIX + randomHex;
}
```

</p>

</details>

<details><summary><b>Solution 2 (One-liner)</b></summary>
<p>

```javascript
const HEX_PREFIX = "#";
const HEX_RADIX = 16;
const HEX_LENGTH = 6;

function generateRandomHex() {
  return (
    HEX_PREFIX +
    Math.floor(Math.random() * 0xffffff)
      .toString(HEX_RADIX)
      .padStart(HEX_LENGTH, "0")
  );
}
```

</p>

</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 63. What is the output of below code?

```javascript
var of = ["of"];
for (var of of of) {
  console.log(of);
}
```

- 1: of
- 2: SyntaxError: Unexpected token of
- 3: SyntaxError: Identifier 'of' has already been declared
- 4: ReferenceError: of is not defined

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 1

In JavaScript, `of` is not considered as a reserved keyword. So the variable declaration with `of` is accepted and prints the array value `of` using for..of loop.

But if you use reserved keyword such as `in` then there will be a syntax error saying `SyntaxError: Unexpected token in`,

```javascript
var in = ['in'];
for(var in in in) {
  console.log(in[in]);
}
```

</p>

</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 64. What is the output of below code?

```javascript
const numbers = [11, 25, 31, 23, 33, 18, 200];
numbers.sort();
console.log(numbers);
```

- 1: [11, 18, 23, 25, 31, 33, 200]
- 2: [11, 18, 200, 23, 25, 31, 33]
- 3: [11, 25, 31, 23, 33, 18, 200]
- 4: Cannot sort numbers

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 2

By default, the sort method sorts elements alphabetically. This is because elemented converted to strings and strings compared in UTF-16 code units order. Hence, you will see the above numbers not sorted as expected. In order to sort numerically just supply a comparator function which handles numeric sorts.

```javascript
const numbers = [11, 25, 31, 23, 33, 18, 200];
numbers.sort((a, b) => a - b);
console.log(numbers);
```

**Note:** Sort() method changes the original array.

</p>

</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 65. What is the output order of below code?

```javascript
setTimeout(() => {
  console.log("1");
}, 0);
Promise.resolve("hello").then(() => console.log("2"));
console.log("3");
```

- 1: 1, 2, 3
- 2: 1, 3, 2
- 3: 3, 1, 2
- 4: 3, 2, 1

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 4

When the JavaScript engine parses the above code, the first two statements are asynchronous which will be executed later and third statement is synchronous statement which will be moved to callstack, executed and prints the number 3 in the console. Next, Promise is native in ES6 and it will be moved to Job queue which has high priority than callback queue in the execution order. At last, since setTimeout is part of WebAPI the callback function moved to callback queue and executed. Hence, you will see number 2 printed first followed by 1.

</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 66. What is the output of below code?

```javascript
console.log(name);
console.log(message());
var name = "John";
(function message() {
  console.log("Hello John: Welcome");
});
```

- 1: John, Hello John: Welcome
- 2: undefined, Hello John, Welcome
- 3: Reference error: name is not defined, Reference error: message is not defined
- 4: undefined, Reference error: message is not defined

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 4

IIFE(Immediately Invoked Function Expression) is just like any other function expression which won't be hoisted. Hence, there will be a reference error for message call.
The behavior would be the same with below function expression of message1,

```javascript
console.log(name);
console.log(message());
var name = 'John';
var message = function () {
   console.log('Hello John: Welcome');
});
```

</p>
</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 67. What is the output of below code?

```javascript
message();

function message() {
  console.log("Hello");
}
function message() {
  console.log("Bye");
}
```

- 1: Reference error: message is not defined
- 2: Hello
- 3: Bye
- 4: Compile time error

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 3

As part of hoisting, initially JavaScript Engine or compiler will store first function in heap memory but later rewrite or replaces with redefined function content.

</p>
</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 68. What is the output of below code?

```javascript
var currentCity = "NewYork";

var changeCurrentCity = function () {
  console.log("Current City:", currentCity);
  var currentCity = "Singapore";
  console.log("Current City:", currentCity);
};

changeCurrentCity();
```

- 1: NewYork, Singapore
- 2: NewYork, NewYork
- 3: undefined, Singapore
- 4: Singapore, Singapore

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 3

Due to hositing feature, the variables declared with `var` will have `undefined` value in the creation phase so the outer variable `currentCity` will get same `undefined` value. But after few lines of code JavaScript engine found a new function call(`changeCurrentCity()`) to update the current city with `var` re-declaration. Since each function call will create a new execution context, the same variable will have `undefined` value before the declaration and new value(`Singapore`) after the declarion. Hence, the value `undefined` print first followed by new value `Singapore` in the execution phase.

</p>
</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 69. What is the output of below code in an order?

```javascript
function second() {
  var message;
  console.log(message);
}

function first() {
  var message = "first";
  second();
  console.log(message);
}

var message = "default";
first();
console.log(message);
```

- 1: undefined, first, default
- 2: default, default, default
- 3: first, first, default
- 4: undefined, undefined, undefined

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 1

Each context(global or functional) has it's own variable environment and the callstack of variables in a LIFO order. So you can see the message variable value from second, first functions in an order followed by global context message variable value at the end.

</p>
</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 70. What is the output of below code?

```javascript
var expressionOne = function functionOne() {
  console.log("functionOne");
};
functionOne();
```

- 1: functionOne is not defined
- 2: functionOne
- 3: console.log("functionOne")
- 4: undefined

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 1

The function call `functionOne` is not going to be part of scope chain and it has it's own execution context with the enclosed variable environment. i.e, It won't be accessed from global context. Hence, there will be an error while invoking the function as `functionOne is not defined`.

</p>
</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 71. What is the output of below code?

```javascript
const user = {
  name: "John",
  eat() {
    console.log(this);
    var eatFruit = function () {
      console.log(this);
    };
    eatFruit();
  },
};
user.eat();
```

- 1: {name: "John", eat: f}, {name: "John", eat: f}
- 2: Window {...}, Window {...}
- 3: {name: "John", eat: f}, undefined
- 4: {name: "John", eat: f}, Window {...}

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 4

`this` keyword is dynamic scoped but not lexically scoped . In other words, it doesn't matter where `this` has been written but how it has been invoked really matter. In the above code snippet, the `user` object invokes `eat` function so `this` keyword refers to `user` object but `eatFruit` has been invoked by `eat` function and `this` will have default `Window` object.

The above pit fall fixed by three ways,

1. In ES6, the arrow function will make `this` keyword as lexically scoped. Since the surrounding object of `this` object is `user` object, the `eatFruit` function will contain `user` object for `this` object.

```javascript
const user = {
  name: "John",
  eat() {
    console.log(this);
    var eatFruit = () => {
      console.log(this);
    };
    eatFruit();
  },
};
user.eat();
```

The next two solutions have been used before ES6 introduced.

2.  It is possible create a reference of `this` into a separate variable and use that new variable inplace of `this` keyword inside `eatFruit` function. This is a common practice in jQuery and AngularJS before ES6 introduced.

```javascript
const user = {
  name: "John",
  eat() {
    console.log(this);
    var self = this;
    var eatFruit = () => {
      console.log(self);
    };
    eatFruit();
  },
};
user.eat();
```

3. The `eatFruit` function can bind explicitly with `this` keyword where it refers `Window` object.

```javascript
const user = {
  name: "John",
  eat() {
    console.log(this);
    var eatFruit = function () {
      console.log(this);
    };
    return eatFruit.bind(this);
  },
};
user.eat()();
```

</p>
</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 72. What is the output of below code?

```javascript
let message = "Hello World!";
message[0] = "J";
console.log(message);

let name = "John";
name = name + " Smith";
console.log(name);
```

- 1: Jello World!, John Smith
- 2: Jello World!, John
- 3: Hello World!, John Smith
- 4: Hello World!, John

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 3

In JavaScript, primitives are immutable i.e. there is no way to change a primitive value once it gets created. So when you try to update the string's first character, there is no change in the string value and prints the same initial value `Hello World!`. Whereas in the later example, the concatenated value is re-assigned to the same variable which will result into creation of new memory block with the reference pointing to `John Smith` value and the old memory block value(`John`) will be garbage collected.

</p>
</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 73. What is the output of below code?

```javascript
let user1 = {
  name: "Jacob",
  age: 28,
};

let user2 = {
  name: "Jacob",
  age: 28,
};

console.log(user1 === user2);
```

- 1: True
- 2: False
- 3: Compile time error

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 2

In JavaScript, the variables such as objects, arrays and functions comes under pass by reference. When you try to compare two objects with same content, it is going to compare memory address or reference of those variables. These variables always create separate memory blocks hence the comparison is always going to return false value.

</p>
</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 74. What is the output of below code?

```javascript
function greeting() {
  setTimeout(function () {
    console.log(message);
  }, 5000);
  const message = "Hello, Good morning";
}
greeting();
```

- 1: Undefined
- 2: Reference error:
- 3: Hello, Good morning
- 4: null

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 3

The variable `message` is still treated as closure(since it has been used in inner function) eventhough it has been declared after setTimeout function. The function with in setTimeout function will be sent to WebAPI and the variable declaration executed with in 5 seconds with the assigned value. Hence, the text declared for the variable will be displayed.

</p>
</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 75. What is the output of below code?

```javascript
const a = new Number(10);
const b = 10;
console.log(a === b);
```

- 1: False
- 2: True

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 1

Eventhough both variables `a` and `b` refer a number value, the first declaration is based on constructor function and the type of the variable is going to be `object` type. Whereas the second declaration is primitive assignment with a number and the type is `number` type. Hence, the equality operator `===` will output `false` value.

</p>
</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 76. What is the type of below function?

```javascript
function add(a, b) {
  console.log("The input arguments are: ", a, b);
  return a + b;
}
```

- 1: Pure function
- 2: Impure function

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 2

Eventhough the above function returns the same result for the same arguments(input) that are passed in the function, the `console.log()` statement causes a function to have side effects because it affects the state of an external code. i.e, the `console` object's state and depends on it to perform the job. Hence, the above function considered as impure function.

</p>
</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 77. What is the output of below code?

```javascript
const promiseOne = new Promise((resolve, reject) => setTimeout(resolve, 4000));
const promiseTwo = new Promise((resolve, reject) => setTimeout(reject, 4000));

Promise.all([promiseOne, promiseTwo]).then((data) => console.log(data));
```

- 1: [{status: "fullfilled", value: undefined}, {status: "rejected", reason: undefined}]
- 2: [{status: "fullfilled", value: undefined}, Uncaught(in promise)]
- 3: Uncaught (in promise)
- 4: [Uncaught(in promise), Uncaught(in promise)]

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 3

The above promises settled at the same time but one of them resolved and other one rejected. When you use `.all` method on these promises, the result will be short circuted by throwing an error due to rejection in second promise. But If you use `.allSettled` method then result of both the promises will be returned irrespective of resolved or rejected promise status without throwing any error.

```javascript
Promise.allSettled([promiseOne, promiseTwo]).then((data) => console.log(data));
```

</p>
</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 78. What is the output of below code?

```javascript
try {
  setTimeout(() => {
    console.log("try block");
    throw new Error(`An exception is thrown`);
  }, 1000);
} catch (err) {
  console.log("Error: ", err);
}
```

- 1: try block, Error: An exception is thrown
- 2: Error: An exception is thrown
- 3: try block, Uncaught Error: Exception is thrown
- 4: Uncaught Error: Exception is thrown

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 3

If you put `setTimeout` and `setInterval` methods inside the try clause and an exception is thrown, the catch clause will not catch any of them. This is because the try...catch statement works synchronously, and the function in the above code is executed asynchronously after a certain period of time. Hence, you will see runtime exception without catching the error. To resolve this issue, you have to put the try...catch block inside the function as below,

```javascript
setTimeout(() => {
  try {
    console.log("try block");
    throw new Error(`An exception is thrown`);
  } catch (err) {
    console.log("Error: ", err);
  }
}, 1000);
```

You can use `.catch()` function in promises to avoid these issues with asynchronous code.

</p>
</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 79. What is the output of below code?

```javascript
let a = 10;
if (true) {
  let a = 20;
  console.log(a, "inside");
}
console.log(a, "outside");
```

- 1: 20, "inside" and 20, "outside"
- 2: 20, "inside" and 10, "outside"
- 3: 10, "inside" and 10, "outside"
- 4: 10, "inside" and 20, "outside"

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 2

The variable "a" declared inside "if" has block scope and does not affect the value of the outer "a" variable.

</p>
</details>

---

**[⬆ Back to Top](#table-of-contents)**

#### 80. What is the output of below code?

```javascript
let arr = [1, 2, 3, 4, 5, -6, 7];
arr.length = 0;
console.log(arr);
```

- 1: 0
- 2: Undefined
- 3: null
- 4: [ ]

<details><summary><b>Answer</b></summary>
<p>

##### Answer: 4

The length of the array 'arr' has been set to 0, so the array becomes empty.

</p>
</details>

---

**[⬆ Back to Top](#table-of-contents)**



## Disclaimer

The questions provided in this repository are the summary of frequently asked questions across numerous companies. We cannot guarantee that these questions will actually be asked during your interview process, nor should you focus on memorizing all of them. The primary purpose is for you to get a sense of what some companies might ask — do not get discouraged if you don't know the answer to all of them ⁠— that is ok!

Good luck with your interview 😊

---

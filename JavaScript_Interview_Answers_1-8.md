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
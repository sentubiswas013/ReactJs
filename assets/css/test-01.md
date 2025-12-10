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
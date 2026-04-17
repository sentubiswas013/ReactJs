## 🔹 1. Basics

**1. What is Node.js?**

* Node.js is a runtime environment built on Chrome’s V8 engine to execute JavaScript outside the browser.
* It is asynchronous, event-driven, and non-blocking.

---

**2. Why use Node.js?**

* High performance (non-blocking I/O)
* Scalable for real-time apps
* Same language (JavaScript) for frontend & backend

---

**3. Is Node.js single-threaded?**

* Yes (event loop runs on a single thread)
* Uses internal thread pool for async operations

---

**4. What is the event loop?**

* Core mechanism that handles async operations
* Executes callbacks from queue after completing tasks

---

**5. What is non-blocking I/O?**

* Executes tasks asynchronously without waiting for completion

---

## 🔹 2. Core Concepts

**6. What is callback?**

* Function passed as argument and executed after operation completes

---

**7. What is callback hell?**

* Nested callbacks leading to unreadable code

---

**8. How to avoid callback hell?**

* Promises
* Async/Await
* Modularization

---

**9. What is a Promise?**

* Object representing future completion (resolve/reject)

---

**10. What is async/await?**

* Syntax sugar over Promises for cleaner async code

---

## 🔹 3. Modules & Architecture

**11. What is a module in Node.js?**

* Reusable block of code (CommonJS / ES Modules)

---

**12. Difference between require and import?**

* `require` → CommonJS
* `import` → ES Modules (modern)

---

**13. What is package.json?**

* Metadata file for project dependencies and scripts

---

**14. What is npm?**

* Node Package Manager to install/manage libraries

---

**15. What is npx?**

* Runs packages without installing globally

---

## 🔹 4. File System & Streams

**16. What is fs module?**

* Built-in module to handle file operations

---

**17. What are streams?**

* Process data in chunks instead of loading whole data

---

**18. Types of streams?**

* Readable
* Writable
* Duplex
* Transform

---

## 🔹 5. APIs & Frameworks

**19. What is Express.js?**

* Minimal web framework for Node.js

---

**20. What is middleware?**

* Functions executed between request and response

---

**21. Types of middleware?**

* Application-level
* Router-level
* Error-handling
* Built-in

---

**22. What is REST API?**

* Architecture using HTTP methods (GET, POST, PUT, DELETE)

---

## 🔹 6. Advanced Concepts

**23. What is clustering?**

* Running multiple Node.js instances to utilize CPU cores

---

**24. What is worker thread?**

* Used for CPU-intensive tasks

---

**25. What is event emitter?**

* Handles custom events using `on()` and `emit()`

---

**26. What is buffer?**

* Temporary storage for binary data

---

## 🔹 7. Performance & Security

**27. How to improve performance?**

* Use caching
* Streams
* Clustering
* Async operations

---

**28. What is rate limiting?**

* Restricts number of requests to prevent abuse

---

**29. What are common security practices?**

* Input validation
* Use HTTPS
* Helmet.js
* Avoid SQL injection

---

**30. What is CORS?**

* Cross-Origin Resource Sharing allows/restricts API access from different domains

## Basic Concepts
### 1. What is Node.js?

Node.js is a JavaScript runtime built on Chrome's V8 engine that lets you run JavaScript on the server side. It's not a language or framework - it's an environment that executes JavaScript outside the browser.

```javascript
// Simple Node.js server
const http = require('http');
const server = http.createServer((req, res) => {
  res.end('Hello from Node.js!');
});
server.listen(3000);
```

### 2. What is the difference between Node.js and JavaScript in the browser?

Browser JavaScript runs in a sandbox with DOM access but no file system. Node.js has no DOM but can access files, databases, and system resources. Browser uses window object, Node uses global object.

```javascript
// Browser only
document.getElementById('myDiv');

// Node.js only
const fs = require('fs');
fs.readFileSync('file.txt');
```

### 3. Why is Node.js considered asynchronous and event-driven?

Node.js uses non-blocking I/O operations. Instead of waiting for operations to complete, it continues executing other code and handles results through callbacks or events when they're ready.

```javascript
// Asynchronous file reading
fs.readFile('data.txt', (err, data) => {
  console.log('File read complete');
});
console.log('This runs immediately');
```

### 4. What is the event loop in Node.js?

The event loop is Node's mechanism for handling asynchronous operations. It continuously checks for pending operations and executes their callbacks when ready, allowing single-threaded JavaScript to handle multiple operations efficiently.

```javascript
console.log('1');
setTimeout(() => console.log('2'), 0);
console.log('3');
// Output: 1, 3, 2
```

### 5. How does Node.js handle concurrency?

Node.js uses a single-threaded event loop for JavaScript execution but delegates I/O operations to a thread pool. This allows handling thousands of concurrent connections without creating new threads for each request.

```javascript
// Handles multiple requests concurrently
server.on('request', (req, res) => {
  // Each request is handled asynchronously
  database.query('SELECT * FROM users', (err, results) => {
    res.json(results);
  });
});
```

### 6. What are the advantages of using Node.js?

Fast execution with V8 engine, same language for frontend and backend, huge npm ecosystem, excellent for real-time applications, lightweight and efficient for I/O intensive applications.

```javascript
// Real-time chat example
const io = require('socket.io')(server);
io.on('connection', (socket) => {
  socket.on('message', (msg) => {
    io.emit('message', msg); // Broadcast to all clients
  });
});
```

## Package Management
### 7. What is npm (Node Package Manager)?

npm is Node's package manager that helps install, manage, and share JavaScript packages. It comes bundled with Node.js and provides access to the world's largest software registry.

```bash
# Install a package
npm install express

# Install globally
npm install -g nodemon
```

### 8. How do you install a package using npm?

Use `npm install` followed by the package name. Add `--save` for production dependencies or `--save-dev` for development dependencies. Modern npm adds to package.json automatically.

```bash
# Local installation
npm install lodash

# Development dependency
npm install --save-dev jest

# Specific version
npm install express@4.18.0
```

### 9. What is a package.json file?

package.json is the manifest file that contains project metadata, dependencies, scripts, and configuration. It's essential for npm to manage your project and its dependencies.

```json
{
  "name": "my-app",
  "version": "1.0.0",
  "scripts": {
    "start": "node server.js",
    "dev": "nodemon server.js"
  },
  "dependencies": {
    "express": "^4.18.0"
  }
}
```

## Asynchronous Programming
### 10. What is the role of the callback function in Node.js?

Callbacks are functions passed as arguments to handle asynchronous operations. They're called when the operation completes, following the error-first convention where the first parameter is an error object.

```javascript
// Error-first callback pattern
fs.readFile('file.txt', (err, data) => {
  if (err) {
    console.error('Error reading file:', err);
    return;
  }
  console.log('File content:', data.toString());
});
```

### 11. What is a callback hell, and how can you avoid it in Node.js?

Callback hell is deeply nested callbacks that make code hard to read and maintain. You can avoid it using Promises, async/await, or modularizing functions.

```javascript
// Callback hell
getData((err, data) => {
  processData(data, (err, processed) => {
    saveData(processed, (err, result) => {
      // Too nested!
    });
  });
});

// Solution with async/await
async function handleData() {
  const data = await getData();
  const processed = await processData(data);
  return await saveData(processed);
}
```

### 12. What is the difference between synchronous and asynchronous functions in Node.js?

Synchronous functions block execution until they complete, while asynchronous functions don't block and use callbacks, promises, or async/await to handle results later.

```javascript
// Synchronous - blocks execution
const data = fs.readFileSync('file.txt');
console.log('This waits');

// Asynchronous - non-blocking
fs.readFile('file.txt', (err, data) => {
  console.log('This runs when ready');
});
console.log('This runs immediately');
```

### 13. Explain the concept of Promises in Node.js.

Promises represent eventual completion or failure of asynchronous operations. They have three states: pending, fulfilled, or rejected. They provide cleaner alternatives to callbacks with .then() and .catch().

```javascript
// Creating a Promise
const readFilePromise = (filename) => {
  return new Promise((resolve, reject) => {
    fs.readFile(filename, (err, data) => {
      if (err) reject(err);
      else resolve(data);
    });
  });
};

// Using Promise
readFilePromise('file.txt')
  .then(data => console.log(data))
  .catch(err => console.error(err));
```

### 14. What is async/await in Node.js? How is it different from Promises?

async/await is syntactic sugar over Promises that makes asynchronous code look synchronous. It's easier to read and debug than Promise chains, but both achieve the same result.

```javascript
// Promise chain
getData()
  .then(data => processData(data))
  .then(result => console.log(result))
  .catch(err => console.error(err));

// async/await - cleaner syntax
async function handleData() {
  try {
    const data = await getData();
    const result = await processData(data);
    console.log(result);
  } catch (err) {
    console.error(err);
  }
}
```

### 15. How do you handle errors in asynchronous code?

Use try/catch with async/await, .catch() with Promises, or error-first callbacks. Always handle errors to prevent crashes and provide meaningful feedback.

```javascript
// async/await error handling
async function safeOperation() {
  try {
    const result = await riskyOperation();
    return result;
  } catch (error) {
    console.error('Operation failed:', error.message);
    throw error;
  }
}

// Promise error handling
getData()
  .then(data => processData(data))
  .catch(error => {
    console.error('Error:', error);
  });
```

## Core Node.js Modules
### 16. What are core modules in Node.js?

Core modules are built-in modules that come with Node.js installation. They provide essential functionality like file system access, HTTP server creation, and path manipulation without needing external packages.

```javascript
// Common core modules
const fs = require('fs');        // File system
const http = require('http');    // HTTP server/client
const path = require('path');    // Path utilities
const os = require('os');        // Operating system
const crypto = require('crypto'); // Cryptography
```

### 17. How would you use the 'fs' module to read and write files in Node.js?

The fs module provides both synchronous and asynchronous methods for file operations. Use async methods for better performance in production applications.

```javascript
const fs = require('fs');

// Read file asynchronously
fs.readFile('input.txt', 'utf8', (err, data) => {
  if (err) throw err;
  console.log(data);
});

// Write file asynchronously
fs.writeFile('output.txt', 'Hello World', (err) => {
  if (err) throw err;
  console.log('File saved!');
});

// Promise-based (modern approach)
const fsPromises = require('fs').promises;
const data = await fsPromises.readFile('file.txt', 'utf8');
```

### 18. Explain the purpose of the 'http' module in Node.js.

The http module creates HTTP servers and clients. It's the foundation for web applications in Node.js, allowing you to handle HTTP requests and responses.

```javascript
const http = require('http');

// Create HTTP server
const server = http.createServer((req, res) => {
  res.writeHead(200, { 'Content-Type': 'text/plain' });
  res.end('Hello World!');
});

server.listen(3000, () => {
  console.log('Server running on port 3000');
});

// Make HTTP request
http.get('http://api.example.com', (res) => {
  console.log('Status:', res.statusCode);
});
```

### 19. What is the 'path' module used for in Node.js?

The path module provides utilities for working with file and directory paths. It handles cross-platform path differences and provides methods for path manipulation.

```javascript
const path = require('path');

// Join paths safely
const fullPath = path.join('/users', 'john', 'documents', 'file.txt');

// Get file extension
const ext = path.extname('file.txt'); // '.txt'

// Get filename without extension
const name = path.basename('file.txt', '.txt'); // 'file'

// Get directory name
const dir = path.dirname('/users/john/file.txt'); // '/users/john'
```

### 20. What does the 'events' module do in Node.js?

The events module provides EventEmitter class for creating and handling custom events. It's the foundation of Node.js event-driven architecture, allowing objects to emit and listen for events.

```javascript
const EventEmitter = require('events');

class MyEmitter extends EventEmitter {}
const myEmitter = new MyEmitter();

// Listen for event
myEmitter.on('data', (message) => {
  console.log('Received:', message);
});

// Emit event
myEmitter.emit('data', 'Hello World!');

// One-time listener
myEmitter.once('start', () => {
  console.log('Started only once');
});
```

### 21. How does the 'stream' module work in Node.js?

Streams handle data in chunks rather than loading everything into memory at once. They're perfect for processing large files or real-time data. There are four types: readable, writable, duplex, and transform streams.

```javascript
const fs = require('fs');

// Read large file as stream
const readStream = fs.createReadStream('largefile.txt');
const writeStream = fs.createWriteStream('output.txt');

// Pipe data from read to write stream
readStream.pipe(writeStream);

// Handle stream events
readStream.on('data', (chunk) => {
  console.log('Received chunk:', chunk.length);
});
```

### 22. What is the 'util' module in Node.js?

The util module provides utility functions for debugging and formatting. It includes promisify for converting callbacks to promises, inspect for object debugging, and deprecate for marking deprecated functions.

```javascript
const util = require('util');
const fs = require('fs');

// Convert callback to promise
const readFile = util.promisify(fs.readFile);

// Use as promise
const data = await readFile('file.txt', 'utf8');

// Debug object inspection
console.log(util.inspect(myObject, { colors: true, depth: 2 }));
```

### 23. What is the 'cluster' module in Node.js and how is it used?

The cluster module creates child processes that share the same server port, allowing you to utilize multiple CPU cores. It's essential for scaling Node.js applications on multi-core systems.

```javascript
const cluster = require('cluster');
const http = require('http');
const numCPUs = require('os').cpus().length;

if (cluster.isMaster) {
  // Fork workers
  for (let i = 0; i < numCPUs; i++) {
    cluster.fork();
  }
} else {
  // Worker process
  http.createServer((req, res) => {
    res.end('Hello from worker ' + process.pid);
  }).listen(3000);
}
```

## Express.js Framework
### 24. What is Express.js and how does it relate to Node.js?

Express.js is a minimal web framework built on top of Node.js. It simplifies creating web servers and APIs by providing routing, middleware support, and request/response handling that's much easier than raw Node.js.

```javascript
const express = require('express');
const app = express();

// Simple route
app.get('/', (req, res) => {
  res.send('Hello Express!');
});

// JSON middleware
app.use(express.json());

app.listen(3000, () => {
  console.log('Express server running on port 3000');
});
```

### 25. How do you set up a basic Express server?

Install Express with npm, create an app instance, define routes, and start listening on a port. It's much simpler than creating an HTTP server with raw Node.js.

```javascript
const express = require('express');
const app = express();

// Middleware for parsing JSON
app.use(express.json());

// Basic routes
app.get('/', (req, res) => {
  res.json({ message: 'Welcome to Express!' });
});

app.post('/users', (req, res) => {
  res.json({ user: req.body });
});

app.listen(3000, () => {
  console.log('Server running on http://localhost:3000');
});
```

### 26. What is middleware in Express.js?

Middleware are functions that execute during the request-response cycle. They can modify request/response objects, end the cycle, or call the next middleware. They're perfect for authentication, logging, and data parsing.

```javascript
// Custom middleware
const logger = (req, res, next) => {
  console.log(`${req.method} ${req.url}`);
  next(); // Call next middleware
};

// Use middleware
app.use(logger);
app.use(express.json()); // Built-in middleware

// Route-specific middleware
app.get('/protected', authenticate, (req, res) => {
  res.json({ message: 'Protected route' });
});
```

### 27. What are the differences between app.use() and app.get() in Express?

app.use() applies middleware to all routes or specific paths, while app.get() defines route handlers for GET requests only. app.use() runs for every request, app.get() runs only for matching GET requests.

```javascript
// app.use() - runs for all requests
app.use('/api', (req, res, next) => {
  console.log('API middleware');
  next();
});

// app.get() - only for GET requests
app.get('/users', (req, res) => {
  res.json({ users: [] });
});

// app.use() without path - runs for every request
app.use(express.json());
```

### 28. How do you handle HTTP requests in Express.js?

Express provides methods for each HTTP verb: app.get(), app.post(), app.put(), app.delete(). Each method takes a path and handler function that receives request and response objects.

```javascript
// GET request
app.get('/users/:id', (req, res) => {
  const userId = req.params.id;
  res.json({ user: { id: userId } });
});

// POST request
app.post('/users', (req, res) => {
  const newUser = req.body;
  res.status(201).json({ created: newUser });
});

// PUT request
app.put('/users/:id', (req, res) => {
  res.json({ updated: req.params.id });
});
```

### 29. What is the purpose of the 'next' function in Express middleware?

The next function passes control to the next middleware in the stack. Call next() to continue, next(error) to trigger error handling, or don't call it to end the request cycle.

```javascript
// Authentication middleware
const authenticate = (req, res, next) => {
  const token = req.headers.authorization;
  
  if (!token) {
    return res.status(401).json({ error: 'No token' });
  }
  
  // Verify token logic here
  req.user = { id: 1 }; // Add user to request
  next(); // Continue to next middleware
};

// Error handling
const errorHandler = (req, res, next) => {
  try {
    // Some operation
    next();
  } catch (error) {
    next(error); // Pass error to error handler
  }
};
```

## Web Servers and APIs
### 30. What is RESTful API, and how would you implement it in Node.js?

REST is an architectural style using HTTP methods (GET, POST, PUT, DELETE) to perform CRUD operations on resources. Each resource has a unique URL, and responses are typically JSON.

```javascript
const express = require('express');
const app = express();

app.use(express.json());

// GET /users - Get all users
app.get('/users', (req, res) => {
  res.json({ users: [] });
});

// GET /users/:id - Get specific user
app.get('/users/:id', (req, res) => {
  res.json({ user: { id: req.params.id } });
});

// POST /users - Create user
app.post('/users', (req, res) => {
  res.status(201).json({ created: req.body });
});

// PUT /users/:id - Update user
app.put('/users/:id', (req, res) => {
  res.json({ updated: req.params.id });
});

// DELETE /users/:id - Delete user
app.delete('/users/:id', (req, res) => {
  res.status(204).send();
});
```

### 31. What is the difference between GET and POST requests in HTTP?

GET requests retrieve data and parameters are visible in the URL. POST requests send data in the request body, typically for creating or updating resources. GET is idempotent and cacheable, POST is not.

```javascript
// GET request - data in URL
app.get('/users', (req, res) => {
  const { page, limit } = req.query; // ?page=1&limit=10
  res.json({ users: [], page, limit });
});

// POST request - data in body
app.post('/users', (req, res) => {
  const userData = req.body; // JSON data from request body
  res.status(201).json({ created: userData });
});
```

### 32. How would you handle errors in a Node.js REST API?

Use try-catch blocks for async operations, create error middleware, and return consistent error responses with proper HTTP status codes. Always handle both operational and programming errors.

```javascript
// Error handling middleware
app.use((err, req, res, next) => {
  console.error(err.stack);
  res.status(err.status || 500).json({
    error: err.message,
    status: err.status || 500
  });
});

// Route with error handling
app.get('/users/:id', async (req, res, next) => {
  try {
    const user = await User.findById(req.params.id);
    if (!user) {
      return res.status(404).json({ error: 'User not found' });
    }
    res.json(user);
  } catch (error) {
    next(error); // Pass to error middleware
  }
});
```

### 33. Explain how to use query parameters and route parameters in Express.js.

Route parameters are part of the URL path defined with colons, while query parameters come after the question mark. Access them via req.params and req.query respectively.

```javascript
// Route parameters - /users/123/posts/456
app.get('/users/:userId/posts/:postId', (req, res) => {
  const { userId, postId } = req.params;
  res.json({ userId, postId });
});

// Query parameters - /search?q=nodejs&page=1&limit=10
app.get('/search', (req, res) => {
  const { q, page = 1, limit = 10 } = req.query;
  res.json({ query: q, page: Number(page), limit: Number(limit) });
});
```

### 34. How do you secure a REST API in Node.js?

Use HTTPS, implement authentication (JWT tokens), validate input data, use rate limiting, enable CORS properly, and sanitize user inputs to prevent injection attacks.

```javascript
const jwt = require('jsonwebtoken');
const rateLimit = require('express-rate-limit');

// Rate limiting
const limiter = rateLimit({
  windowMs: 15 * 60 * 1000, // 15 minutes
  max: 100 // limit each IP to 100 requests per windowMs
});

// JWT authentication middleware
const authenticate = (req, res, next) => {
  const token = req.header('Authorization')?.replace('Bearer ', '');
  if (!token) {
    return res.status(401).json({ error: 'Access denied' });
  }
  
  try {
    const decoded = jwt.verify(token, process.env.JWT_SECRET);
    req.user = decoded;
    next();
  } catch (error) {
    res.status(400).json({ error: 'Invalid token' });
  }
};

app.use(limiter);
app.use('/api/protected', authenticate);
```

## Databases
### 35. What is the role of MongoDB in a Node.js application?

MongoDB is a NoSQL database that stores data as JSON-like documents. It's popular with Node.js because both use JavaScript, making data exchange seamless. It's great for flexible schemas and rapid development.

```javascript
const { MongoClient } = require('mongodb');

// Connect to MongoDB
const client = new MongoClient('mongodb://localhost:27017');
await client.connect();

const db = client.db('myapp');
const users = db.collection('users');

// Insert document
await users.insertOne({ name: 'John', email: 'john@example.com' });

// Find documents
const allUsers = await users.find({}).toArray();
```

### 36. How do you connect a Node.js application to a MongoDB database?

Use the official MongoDB driver or Mongoose ODM. Install the package, create a connection string, and establish the connection. Always handle connection errors and close connections properly.

```javascript
const { MongoClient } = require('mongodb');

// Connection string
const uri = 'mongodb://localhost:27017/myapp';

async function connectDB() {
  try {
    const client = new MongoClient(uri);
    await client.connect();
    console.log('Connected to MongoDB');
    return client.db('myapp');
  } catch (error) {
    console.error('Connection failed:', error);
    process.exit(1);
  }
}

// Using Mongoose (alternative)
const mongoose = require('mongoose');
await mongoose.connect('mongodb://localhost:27017/myapp');
```

### 37. What is Mongoose, and how does it help in working with MongoDB in Node.js?

Mongoose is an ODM (Object Document Mapper) that provides schema validation, middleware, and a more structured way to work with MongoDB. It adds type safety and validation to MongoDB operations.

```javascript
const mongoose = require('mongoose');

// Define schema
const userSchema = new mongoose.Schema({
  name: { type: String, required: true },
  email: { type: String, required: true, unique: true },
  age: { type: Number, min: 0 }
});

// Create model
const User = mongoose.model('User', userSchema);

// Use model
const user = new User({ name: 'John', email: 'john@example.com', age: 25 });
await user.save();

// Query with validation
const users = await User.find({ age: { $gte: 18 } });
```

### 38. What is an ORM, and why would you use it in Node.js?

ORM (Object-Relational Mapping) maps database tables to JavaScript objects. It provides abstraction, type safety, query builders, and migrations. Popular Node.js ORMs include Sequelize for SQL and Mongoose for MongoDB.

```javascript
// Sequelize ORM example
const { Sequelize, DataTypes } = require('sequelize');
const sequelize = new Sequelize('database', 'username', 'password');

// Define model
const User = sequelize.define('User', {
  name: DataTypes.STRING,
  email: { type: DataTypes.STRING, unique: true }
});

// Use model
const user = await User.create({ name: 'John', email: 'john@example.com' });
const allUsers = await User.findAll();
```

### 39. How do you perform CRUD operations in MongoDB using Node.js?

Use MongoDB driver methods or Mongoose models to Create (insertOne/save), Read (find/findOne), Update (updateOne/findByIdAndUpdate), and Delete (deleteOne/findByIdAndDelete) documents.

```javascript
// Using Mongoose
const User = require('./models/User');

// Create
const user = await User.create({ name: 'John', email: 'john@example.com' });

// Read
const users = await User.find({ age: { $gte: 18 } });
const user = await User.findById(userId);

// Update
await User.findByIdAndUpdate(userId, { name: 'Jane' });

// Delete
await User.findByIdAndDelete(userId);
```

## Performance and Scalability
### 40. How can you improve the performance of a Node.js application?

Use clustering, implement caching (Redis), optimize database queries, use compression middleware, enable gzip, minimize dependencies, use CDNs, and implement proper error handling to prevent crashes.

```javascript
// Clustering
const cluster = require('cluster');
const numCPUs = require('os').cpus().length;

if (cluster.isMaster) {
  for (let i = 0; i < numCPUs; i++) {
    cluster.fork();
  }
} else {
  // Worker process
  const app = require('./app');
  app.listen(3000);
}

// Caching with Redis
const redis = require('redis');
const client = redis.createClient();

app.get('/users/:id', async (req, res) => {
  const cached = await client.get(`user:${req.params.id}`);
  if (cached) {
    return res.json(JSON.parse(cached));
  }
  
  const user = await User.findById(req.params.id);
  await client.setex(`user:${req.params.id}`, 3600, JSON.stringify(user));
  res.json(user);
});

// Compression
const compression = require('compression');
app.use(compression());
```

### 41. What is the purpose of caching in Node.js?

Caching stores frequently accessed data in memory to reduce database queries and improve response times. It's essential for performance optimization, especially for expensive operations or frequently requested data.

```javascript
const redis = require('redis');
const client = redis.createClient();

// Cache middleware
const cache = (duration) => {
  return async (req, res, next) => {
    const key = req.originalUrl;
    const cached = await client.get(key);
    
    if (cached) {
      return res.json(JSON.parse(cached));
    }
    
    res.sendResponse = res.json;
    res.json = (body) => {
      client.setex(key, duration, JSON.stringify(body));
      res.sendResponse(body);
    };
    next();
  };
};

app.get('/users', cache(300), (req, res) => {
  res.json({ users: [] });
});
```

### 42. How would you scale a Node.js application to handle more traffic?

Use horizontal scaling with load balancers, implement clustering, use microservices architecture, add caching layers, optimize database queries, and consider using PM2 for process management.

```javascript
// PM2 ecosystem file
module.exports = {
  apps: [{
    name: 'api',
    script: 'app.js',
    instances: 'max', // Use all CPU cores
    exec_mode: 'cluster',
    env: {
      NODE_ENV: 'production'
    }
  }]
};

// Load balancing with nginx
// upstream backend {
//   server 127.0.0.1:3000;
//   server 127.0.0.1:3001;
//   server 127.0.0.1:3002;
// }

// Microservice example
const userService = require('./services/userService');
const orderService = require('./services/orderService');

app.get('/users', userService.getUsers);
app.get('/orders', orderService.getOrders);
```

### 43. What are worker threads in Node.js? How do they improve performance?

Worker threads allow running JavaScript in parallel threads for CPU-intensive tasks. They prevent blocking the main event loop, improving performance for computational work while maintaining Node.js's asynchronous nature.

```javascript
const { Worker, isMainThread, parentPort, workerData } = require('worker_threads');

if (isMainThread) {
  // Main thread
  const worker = new Worker(__filename, {
    workerData: { numbers: [1, 2, 3, 4, 5] }
  });
  
  worker.on('message', (result) => {
    console.log('Result:', result);
  });
} else {
  // Worker thread
  const { numbers } = workerData;
  const sum = numbers.reduce((a, b) => a + b, 0);
  parentPort.postMessage(sum);
}

// Express route using worker
app.get('/calculate', (req, res) => {
  const worker = new Worker('./calculator.js', {
    workerData: req.body.data
  });
  
  worker.on('message', (result) => {
    res.json({ result });
  });
});
```

## Security
### 44. How do you prevent SQL injection in Node.js applications?

Use parameterized queries, prepared statements, input validation, and ORM/query builders. Never concatenate user input directly into SQL strings. Always sanitize and validate input data.

```javascript
const mysql = require('mysql2');
const pool = mysql.createPool({ host: 'localhost', user: 'root', database: 'test' });

// BAD - Vulnerable to SQL injection
const badQuery = `SELECT * FROM users WHERE id = ${userId}`;

// GOOD - Parameterized query
const safeQuery = 'SELECT * FROM users WHERE id = ? AND email = ?';
pool.execute(safeQuery, [userId, email], (err, results) => {
  console.log(results);
});

// Using Sequelize ORM (automatically prevents injection)
const user = await User.findOne({
  where: {
    id: userId,
    email: userEmail
  }
});
```

### 45. What is CORS, and how can you handle it in Node.js?

CORS (Cross-Origin Resource Sharing) controls which domains can access your API from browsers. Use the cors middleware to configure allowed origins, methods, and headers for cross-domain requests.

```javascript
const cors = require('cors');

// Enable CORS for all routes
app.use(cors());

// Configure CORS options
const corsOptions = {
  origin: ['http://localhost:3000', 'https://myapp.com'],
  methods: ['GET', 'POST', 'PUT', 'DELETE'],
  allowedHeaders: ['Content-Type', 'Authorization'],
  credentials: true
};

app.use(cors(corsOptions));

// Manual CORS headers
app.use((req, res, next) => {
  res.header('Access-Control-Allow-Origin', '*');
  res.header('Access-Control-Allow-Methods', 'GET,PUT,POST,DELETE');
  res.header('Access-Control-Allow-Headers', 'Content-Type, Authorization');
  next();
});
```

### 46. How do you secure sensitive data like passwords in a Node.js app?

Hash passwords using bcrypt, store secrets in environment variables, use HTTPS, implement proper session management, and never store plain text passwords or API keys in code.

```javascript
const bcrypt = require('bcrypt');
const jwt = require('jsonwebtoken');

// Hash password before saving
const hashPassword = async (password) => {
  const saltRounds = 12;
  return await bcrypt.hash(password, saltRounds);
};

// Verify password
const verifyPassword = async (password, hashedPassword) => {
  return await bcrypt.compare(password, hashedPassword);
};

// User registration
app.post('/register', async (req, res) => {
  const { email, password } = req.body;
  const hashedPassword = await hashPassword(password);
  
  const user = await User.create({ email, password: hashedPassword });
  res.json({ message: 'User created' });
});

// Environment variables for secrets
const JWT_SECRET = process.env.JWT_SECRET;
const DB_PASSWORD = process.env.DB_PASSWORD;
```

### 47. What is Helmet.js and how does it help with security in Express.js?

Helmet.js sets various HTTP headers to secure Express apps against common vulnerabilities like XSS, clickjacking, and MIME sniffing. It's a collection of middleware functions for security best practices.

```javascript
const helmet = require('helmet');

// Use all helmet defaults
app.use(helmet());

// Configure specific options
app.use(helmet({
  contentSecurityPolicy: {
    directives: {
      defaultSrc: ["'self'"],
      styleSrc: ["'self'", "'unsafe-inline'"],
      scriptSrc: ["'self'"]
    }
  },
  hsts: {
    maxAge: 31536000,
    includeSubDomains: true
  }
}));

// Individual helmet middlewares
app.use(helmet.xssFilter());
app.use(helmet.noSniff());
app.use(helmet.frameguard({ action: 'deny' }));
```

### 48. How would you implement authentication and authorization in a Node.js app?

Use JWT tokens for stateless authentication, implement role-based access control, secure routes with middleware, and store user sessions securely. Always validate tokens and check permissions.

```javascript
const jwt = require('jsonwebtoken');

// Login endpoint
app.post('/login', async (req, res) => {
  const { email, password } = req.body;
  const user = await User.findOne({ email });
  
  if (user && await bcrypt.compare(password, user.password)) {
    const token = jwt.sign(
      { userId: user.id, role: user.role },
      process.env.JWT_SECRET,
      { expiresIn: '24h' }
    );
    res.json({ token });
  } else {
    res.status(401).json({ error: 'Invalid credentials' });
  }
});

// Authentication middleware
const authenticate = (req, res, next) => {
  const token = req.header('Authorization')?.replace('Bearer ', '');
  if (!token) return res.status(401).json({ error: 'Access denied' });
  
  try {
    const decoded = jwt.verify(token, process.env.JWT_SECRET);
    req.user = decoded;
    next();
  } catch (error) {
    res.status(400).json({ error: 'Invalid token' });
  }
};

// Authorization middleware
const authorize = (roles) => {
  return (req, res, next) => {
    if (!roles.includes(req.user.role)) {
      return res.status(403).json({ error: 'Insufficient permissions' });
    }
    next();
  };
};

// Protected routes
app.get('/admin', authenticate, authorize(['admin']), (req, res) => {
  res.json({ message: 'Admin only content' });
});
```

## Testing and Debugging
### 49. What is the Mocha testing framework, and how is it used in Node.js?

Mocha is a JavaScript test framework that provides structure for organizing and running tests. It supports async testing, hooks, and various assertion libraries. It's popular for both unit and integration testing.

```javascript
// test/user.test.js
const { expect } = require('chai');
const request = require('supertest');
const app = require('../app');

describe('User API', () => {
  before(async () => {
    // Setup before all tests
    await connectTestDB();
  });

  beforeEach(async () => {
    // Setup before each test
    await User.deleteMany({});
  });

  describe('POST /users', () => {
    it('should create a new user', async () => {
      const userData = { name: 'John', email: 'john@test.com' };
      
      const response = await request(app)
        .post('/users')
        .send(userData)
        .expect(201);
      
      expect(response.body.name).to.equal('John');
    });

    it('should return error for invalid email', async () => {
      const userData = { name: 'John', email: 'invalid' };
      
      await request(app)
        .post('/users')
        .send(userData)
        .expect(400);
    });
  });
});

// package.json script
// "scripts": { "test": "mocha test/**/*.test.js" }
```

### 50. How do you debug a Node.js application?

Use console.log for basic debugging, Node.js built-in debugger, VS Code debugger, or tools like nodemon for auto-restart. Use debugging breakpoints, inspect variables, and check error stack traces.

```javascript
// Built-in debugger
node --inspect app.js
// Then open chrome://inspect in Chrome

// VS Code launch.json
{
  "type": "node",
  "request": "launch",
  "name": "Debug App",
  "program": "${workspaceFolder}/app.js",
  "env": { "NODE_ENV": "development" }
}

// Debug with breakpoints
app.get('/debug', (req, res) => {
  debugger; // Breakpoint here
  const data = processData(req.body);
  console.log('Processed data:', data);
  res.json(data);
});

// Error logging
const winston = require('winston');
const logger = winston.createLogger({
  level: 'info',
  format: winston.format.json(),
  transports: [
    new winston.transports.File({ filename: 'error.log', level: 'error' }),
    new winston.transports.Console()
  ]
});

// Use logger instead of console.log
logger.error('Database connection failed', { error: err.message });
logger.info('User created', { userId: user.id });
```
## 🟢 1. React Fundamentals

### 1. What is React and why is it used?

React is a JavaScript library for building user interfaces, especially web applications. It's used because:

* **Component-based architecture** - Build encapsulated components that manage their own state
* **Virtual DOM** - Efficient updates and rendering performance
* **Declarative** - Describe what the UI should look like, React handles the how
* **Reusable components** - Write once, use anywhere
* **Large ecosystem** - Huge community and third-party libraries

```jsx
// Simple React component
function Welcome() {
  return <h1>Hello, World!</h1>;
}
```

### 2. What is JSX and how does it work?

JSX is a syntax extension for JavaScript that looks like HTML but gets compiled to JavaScript function calls.

* **Syntax sugar** - Makes writing React elements easier and more readable
* **Compiled by Babel** - Transforms JSX into React.createElement calls
* **JavaScript expressions** - Use curly braces {} to embed JS code
* **Must return single parent** - Wrap multiple elements in a fragment or div

```jsx
// JSX syntax
const element = <h1>Hello, {name}!</h1>;

// What it compiles to
const element = React.createElement('h1', null, 'Hello, ', name, '!');
```

### 3. Difference between JSX and React.createElement

JSX is the syntactic sugar, React.createElement is the underlying function call.

* **JSX** - More readable, HTML-like syntax
* **React.createElement** - Verbose but shows what's actually happening
* **Same output** - Both create React elements
* **Performance** - No difference, JSX compiles to createElement

```jsx
// JSX version
const element = <div className="container">Hello</div>;

// React.createElement version
const element = React.createElement(
  'div',
  { className: 'container' },
  'Hello'
);
```

### 4. Difference between React and ReactDOM

React and ReactDOM are separate packages with different responsibilities.

* **React** - Core library for creating components and elements
* **ReactDOM** - Renders React components to the DOM
* **Separation of concerns** - React can target different platforms
* **React Native** - Uses React core but different renderer

```jsx
import React from 'react';
import ReactDOM from 'react-dom/client';

// React creates the component
function App() {
  return <h1>Hello World</h1>;
}

// ReactDOM renders it to the DOM
const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(<App />);
```

### 5. What are components in React?

Components are independent, reusable pieces of UI that return JSX elements.

* **Building blocks** - Like custom HTML elements
* **Encapsulation** - Own state and logic
* **Reusable** - Use the same component multiple times
* **Composable** - Combine components to build complex UIs

```jsx
// Simple component
function Button({ text, onClick }) {
  return <button onClick={onClick}>{text}</button>;
}

// Using the component
<Button text="Click me" onClick={() => alert('Clicked!')} />
```

### 6. Types of components in React

There are two main types of components in React.

* **Functional components** - JavaScript functions that return JSX
* **Class components** - ES6 classes that extend React.Component
* **Modern preference** - Functional components with hooks
* **Legacy** - Class components still supported but less common

```jsx
// Functional component
function Welcome(props) {
  return <h1>Hello, {props.name}</h1>;
}

// Class component
class Welcome extends React.Component {
  render() {
    return <h1>Hello, {this.props.name}</h1>;
  }
}
```

### 7. Functional vs Class components

Key differences between the two component types.

* **Syntax** - Functions vs ES6 classes
* **State management** - Hooks vs this.state
* **Lifecycle** - useEffect vs lifecycle methods
* **Performance** - Functional components are slightly more optimized
* **Modern standard** - Hooks make functional components more powerful

```jsx
// Functional with hooks
function Counter() {
  const [count, setCount] = useState(0);
  
  return (
    <div>
      <p>{count}</p>
      <button onClick={() => setCount(count + 1)}>+</button>
    </div>
  );
}

// Class component
class Counter extends React.Component {
  constructor(props) {
    super(props);
    this.state = { count: 0 };
  }
  
  render() {
    return (
      <div>
        <p>{this.state.count}</p>
        <button onClick={() => this.setState({ count: this.state.count + 1 })}>+</button>
      </div>
    );
  }
}
```

### 8. What is the Virtual DOM?

The Virtual DOM is React's in-memory representation of the real DOM.

* **JavaScript object** - Lightweight copy of the actual DOM
* **Diffing algorithm** - Compares old and new virtual DOM trees
* **Reconciliation** - Updates only changed parts in real DOM
* **Performance** - Batch updates and minimize expensive DOM operations
* **Predictable** - Makes UI updates more predictable and debuggable

```jsx
// When state changes, React creates new Virtual DOM
function App() {
  const [count, setCount] = useState(0);
  
  return (
    <div>
      <h1>Count: {count}</h1>  {/* Only this text node updates in real DOM */}
      <button onClick={() => setCount(count + 1)}>Increment</button>
    </div>
  );
}
```

### 9. What are props?

Props are arguments passed to React components, like function parameters.

* **Read-only data** - Components cannot modify their props
* **Parent to child** - Data flows down from parent components
* **Any data type** - Strings, numbers, objects, functions, etc.
* **Default values** - Can set defaultProps for missing props
* **Destructuring** - Extract props for cleaner code

```jsx
// Passing props
function App() {
  return <Welcome name="John" age={25} />;
}

// Receiving props
function Welcome({ name, age }) {
  return <h1>Hello {name}, you are {age} years old</h1>;
}

// Or without destructuring
function Welcome(props) {
  return <h1>Hello {props.name}</h1>;
}
```

### 10. What is state and how is it different from props?

State is internal component data that can change, props are external data passed from parent.

* **State** - Mutable data owned by the component
* **Props** - Immutable data passed from parent to child
* **State changes** - Triggers re-renders when updated
* **Props are read-only** - Cannot be modified by the receiving component
* **Data flow** - Props flow down, state changes bubble up via callbacks

```jsx
// State - internal and mutable
function Counter() {
  const [count, setCount] = useState(0);
  return <div>{count}</div>;
}

// Props - external and immutable
function Display({ count }) {
  return <div>{count}</div>; // Cannot modify count here
}
```

### 11. What is the `render()` method?

The render method returns JSX that describes what the UI should look like.

* **Class components** - Required method that returns JSX
* **Functional components** - The entire function body is like render
* **Pure function** - Should not modify state or cause side effects
* **Called automatically** - React calls it when state or props change
* **Must return** - JSX, null, or array of elements

```jsx
// Class component render method
class Welcome extends React.Component {
  render() {
    return <h1>Hello, {this.props.name}</h1>;
  }
}

// Functional component (entire function is like render)
function Welcome({ name }) {
  return <h1>Hello, {name}</h1>;
}
```

### 12. What is the `key` prop and why is it important?

The key prop helps React identify which list items have changed, added, or removed.

* **Unique identifier** - Should be unique among siblings
* **Performance optimization** - Helps React reuse DOM elements
* **Avoid array index** - Use stable, unique values when possible
* **Required for lists** - React warns if missing in mapped arrays
* **Reconciliation** - Helps Virtual DOM diffing algorithm

```jsx
// Good - using unique ID
const items = users.map(user => 
  <li key={user.id}>{user.name}</li>
);

// Bad - using array index
const items = users.map((user, index) => 
  <li key={index}>{user.name}</li>
);
```

### 13. What are controlled components?

Controlled components have their form data handled by React state.

* **React controls value** - Input value comes from state
* **onChange handler** - Updates state when user types
* **Single source of truth** - State is the authoritative data source
* **Predictable** - You always know the current value
* **Validation** - Easy to add real-time validation

```jsx
function LoginForm() {
  const [email, setEmail] = useState('');
  
  return (
    <input 
      type="email" 
      value={email} 
      onChange={(e) => setEmail(e.target.value)} 
    />
  );
}
```

### 14. What are uncontrolled components?

Uncontrolled components manage their own state internally, like traditional HTML forms.

* **DOM controls value** - Input manages its own state
* **Refs for access** - Use refs to get current value when needed
* **Less React code** - Simpler for basic forms
* **Integration** - Good for integrating with non-React libraries
* **Default values** - Use defaultValue instead of value

```jsx
function LoginForm() {
  const emailRef = useRef();
  
  const handleSubmit = () => {
    console.log(emailRef.current.value);
  };
  
  return (
    <input 
      type="email" 
      ref={emailRef} 
      defaultValue="user@example.com" 
    />
  );
}
```

### 15. What are React Fragments?

Fragments let you group multiple elements without adding extra DOM nodes.

* **No wrapper div** - Avoid unnecessary DOM elements
* **Two syntaxes** - React.Fragment or shorthand <></>
* **Key prop** - Only React.Fragment supports key prop
* **Cleaner HTML** - Keeps DOM structure clean
* **Performance** - Slightly better performance, less memory

```jsx
// Using React.Fragment
function App() {
  return (
    <React.Fragment>
      <h1>Title</h1>
      <p>Description</p>
    </React.Fragment>
  );
}

// Using shorthand syntax
function App() {
  return (
    <>
      <h1>Title</h1>
      <p>Description</p>
    </>
  );
}
```

### 16. What are synthetic events?

Synthetic events are React's wrapper around native DOM events for cross-browser compatibility.

* **Cross-browser** - Same API across all browsers
* **Event pooling** - Reuses event objects for performance (React 16 and below)
* **Same interface** - Same methods as native events (preventDefault, stopPropagation)
* **Automatic binding** - No need to worry about 'this' context
* **Access native event** - Use e.nativeEvent if needed

```jsx
function Button() {
  const handleClick = (e) => {
    e.preventDefault(); // Synthetic event method
    console.log(e.type); // 'click'
    console.log(e.nativeEvent); // Access native event
  };
  
  return <button onClick={handleClick}>Click me</button>;
}
```

### 17. What is `React.StrictMode` and why is it used?

StrictMode is a development tool that highlights potential problems in your application.

* **Development only** - No impact on production builds
* **Double rendering** - Helps find side effects in render methods
* **Deprecated warnings** - Warns about unsafe lifecycle methods
* **Future-proofing** - Prepares code for future React versions
* **Wrapper component** - Wrap parts of your app to enable checks

```jsx
// Wrap your app or components
function App() {
  return (
    <React.StrictMode>
      <Header />
      <Main />
      <Footer />
    </React.StrictMode>
  );
}

// In index.js
root.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);
```

## 🟢 2. React Hooks (Core + Advanced)

---

### 1. What are React Hooks and why were they introduced?

**Hooks are functions that let you use state and lifecycle features in functional components.**

* **Functional components**: No more class components needed
* **Reusable logic**: Share stateful logic between components
* **Simpler code**: Less boilerplate than class components
* **Better testing**: Easier to test and reason about
* **Gradual adoption**: Can use alongside existing class components

```jsx
// Before Hooks - Class Component
class Counter extends React.Component {
  constructor(props) {
    super(props);
    this.state = { count: 0 };
  }
  
  render() {
    return (
      <div>
        <p>Count: {this.state.count}</p>
        <button onClick={() => this.setState({ count: this.state.count + 1 })}>
          +
        </button>
      </div>
    );
  }
}

// After Hooks - Functional Component
import { useState } from 'react';

function Counter() {
  const [count, setCount] = useState(0);
  
  return (
    <div>
      <p>Count: {count}</p>
      <button onClick={() => setCount(count + 1)}>+</button>
    </div>
  );
}
```

---

### 2. Rules of Hooks and why they exist

**Hooks must follow specific rules to work correctly with React's internal mechanisms.**

* **Top level only**: Never call hooks inside loops, conditions, or nested functions
* **React functions only**: Only call hooks from React function components or custom hooks
* **Same order**: Hooks must be called in the same order every time
* **Consistent calls**: React relies on call order to track hook state

```jsx
// ❌ WRONG - Breaking Rules of Hooks
function BadComponent({ condition }) {
  if (condition) {
    const [count, setCount] = useState(0); // Don't do this!
  }
  
  for (let i = 0; i < 3; i++) {
    useEffect(() => {}); // Don't do this!
  }
  
  return <div>Bad example</div>;
}

// ✅ CORRECT - Following Rules of Hooks
function GoodComponent({ condition }) {
  const [count, setCount] = useState(0);
  const [name, setName] = useState('');
  
  useEffect(() => {
    if (condition) {
      // Conditional logic inside hook is fine
      console.log('Condition is true');
    }
  }, [condition]);
  
  return (
    <div>
      <p>Count: {count}</p>
      <input value={name} onChange={(e) => setName(e.target.value)} />
    </div>
  );
}
```

---

### 3. What is `useState` and how does it work?

**useState adds state to functional components and returns current state and setter function.**

* **State management**: Manages component's local state
* **Returns array**: [currentState, setterFunction]
* **Triggers re-render**: Calling setter causes component to re-render
* **Initial value**: Can be a value or function

```jsx
import { useState } from 'react';

function StateExamples() {
  // Basic usage
  const [count, setCount] = useState(0);
  
  // With object
  const [user, setUser] = useState({ name: '', email: '' });
  
  // With array
  const [items, setItems] = useState(['apple', 'banana']);
  
  // Lazy initial state (function runs only once)
  const [expensiveValue, setExpensiveValue] = useState(() => {
    return computeExpensiveValue();
  });
  
  const updateUser = () => {
    setUser(prevUser => ({
      ...prevUser,
      name: 'John'
    }));
  };
  
  const addItem = () => {
    setItems(prevItems => [...prevItems, 'orange']);
  };
  
  return (
    <div>
      <p>Count: {count}</p>
      <button onClick={() => setCount(count + 1)}>Increment</button>
      <button onClick={() => setCount(prev => prev + 1)}>Increment (functional)</button>
      
      <p>User: {user.name}</p>
      <button onClick={updateUser}>Update User</button>
      
      <ul>
        {items.map((item, index) => <li key={index}>{item}</li>)}
      </ul>
      <button onClick={addItem}>Add Item</button>
    </div>
  );
}

function computeExpensiveValue() {
  console.log('Computing expensive value...');
  return Math.random() * 1000;
}
```

---

### 4. What is `useEffect` and how does it work?

**useEffect handles side effects in functional components like API calls, subscriptions, and DOM manipulation.**

* **Side effects**: Data fetching, subscriptions, manual DOM changes
* **Lifecycle replacement**: Combines componentDidMount, componentDidUpdate, componentWillUnmount
* **Dependency array**: Controls when effect runs
* **Cleanup function**: Return function for cleanup (like componentWillUnmount)

```jsx
import { useState, useEffect } from 'react';

function EffectExamples() {
  const [count, setCount] = useState(0);
  const [user, setUser] = useState(null);
  
  // Runs after every render (no dependency array)
  useEffect(() => {
    document.title = `Count: ${count}`;
  });
  
  // Runs only once (empty dependency array)
  useEffect(() => {
    fetchUser().then(setUser);
  }, []);
  
  // Runs when count changes
  useEffect(() => {
    console.log('Count changed:', count);
  }, [count]);
  
  // Effect with cleanup
  useEffect(() => {
    const timer = setInterval(() => {
      setCount(prev => prev + 1);
    }, 1000);
    
    // Cleanup function
    return () => {
      clearInterval(timer);
    };
  }, []);
  
  return (
    <div>
      <p>Count: {count}</p>
      <p>User: {user?.name || 'Loading...'}</p>
      <button onClick={() => setCount(count + 1)}>Manual Increment</button>
    </div>
  );
}

async function fetchUser() {
  const response = await fetch('/api/user');
  return response.json();
}
```

---

### 5. Difference between `useState` and `useEffect`

**useState manages state, useEffect handles side effects - they serve different purposes.**

* **useState**: State management, triggers re-renders
* **useEffect**: Side effects, doesn't return state
* **When they run**: useState during render, useEffect after render
* **Purpose**: useState for data, useEffect for actions

```jsx
import { useState, useEffect } from 'react';

function ComparisonExample() {
  // useState - manages component state
  const [count, setCount] = useState(0);        // State management
  const [loading, setLoading] = useState(true); // State management
  const [data, setData] = useState(null);       // State management
  
  // useEffect - handles side effects
  useEffect(() => {
    // Side effect: API call
    fetchData()
      .then(result => {
        setData(result);    // Updates state
        setLoading(false);  // Updates state
      });
  }, []); // Runs once
  
  useEffect(() => {
    // Side effect: DOM manipulation
    document.title = `Count: ${count}`;
  }, [count]); // Runs when count changes
  
  useEffect(() => {
    // Side effect: subscription
    const subscription = subscribeToUpdates();
    
    return () => {
      // Side effect: cleanup
      subscription.unsubscribe();
    };
  }, []);
  
  if (loading) return <div>Loading...</div>;
  
  return (
    <div>
      <p>Count: {count}</p>
      <p>Data: {data?.message}</p>
      <button onClick={() => setCount(count + 1)}>Increment</button>
    </div>
  );
}

async function fetchData() {
  const response = await fetch('/api/data');
  return response.json();
}

function subscribeToUpdates() {
  return {
    unsubscribe: () => console.log('Unsubscribed')
  };
}
```

---

### 6. Difference between `useEffect`, `useLayoutEffect`, and `useInsertionEffect`

**Three effect hooks that run at different phases of the render cycle.**

* **useEffect**: Runs after DOM updates (asynchronous)
* **useLayoutEffect**: Runs before browser paint (synchronous)
* **useInsertionEffect**: Runs before DOM mutations (CSS-in-JS libraries)

```jsx
function EffectTimingExample() {
  const [count, setCount] = useState(0);
  
  useEffect(() => {
    console.log('3. useEffect - after paint');
  }, [count]);
  
  useLayoutEffect(() => {
    console.log('2. useLayoutEffect - before paint');
  }, [count]);
  
  useInsertionEffect(() => {
    console.log('1. useInsertionEffect - before DOM mutations');
  }, []);
  
  return (
    <div>
      <p>{count}</p>
      <button onClick={() => setCount(count + 1)}>Increment</button>
    </div>
  );
}
```

### 7. When should you avoid `useEffect`?

**Avoid useEffect for calculations, event handlers, and operations that don't need side effects.**

* **Calculations**: Use regular variables or useMemo instead
* **Event handlers**: Define functions directly, don't wrap in useEffect
* **Transforming data**: Do it during render, not in useEffect
* **Initializing state**: Use useState initializer or useMemo

```jsx
// ❌ WRONG - Don't use useEffect for calculations
function BadExample({ items }) {
  const [total, setTotal] = useState(0);
  
  useEffect(() => {
    setTotal(items.reduce((sum, item) => sum + item.price, 0));
  }, [items]); // Unnecessary effect!
  
  return <div>Total: ${total}</div>;
}

// ✅ CORRECT - Calculate during render
function GoodExample({ items }) {
  const total = items.reduce((sum, item) => sum + item.price, 0);
  
  return <div>Total: ${total}</div>;
}

// ❌ WRONG - Don't use useEffect for event handlers
function BadButton() {
  const [count, setCount] = useState(0);
  
  useEffect(() => {
    const handleClick = () => setCount(count + 1);
    // This creates problems with stale closures
  }, [count]);
  
  return <button>Bad approach</button>;
}

// ✅ CORRECT - Define event handlers directly
function GoodButton() {
  const [count, setCount] = useState(0);
  
  const handleClick = () => setCount(prev => prev + 1);
  
  return <button onClick={handleClick}>Count: {count}</button>;
}
```

---

### 8. How does the dependency array work internally?

**React compares each dependency with its previous value using Object.is() to decide if effect should run.**

* **Shallow comparison**: Uses Object.is() for each dependency
* **Array order**: Dependencies must be in same order every render
* **Reference equality**: Objects/arrays compared by reference, not content
* **Missing deps**: Can cause stale closures and bugs

```jsx
import { useState, useEffect } from 'react';

function DependencyExample() {
  const [count, setCount] = useState(0);
  const [user, setUser] = useState({ name: 'John' });
  
  // React internally does something like this:
  // if (count !== prevCount) { runEffect(); }
  useEffect(() => {
    console.log('Count changed:', count);
  }, [count]); // Object.is(count, prevCount)
  
  // ❌ WRONG - Object reference changes every render
  const config = { theme: 'dark' }; // New object every render!
  useEffect(() => {
    console.log('Config changed'); // Runs every render
  }, [config]);
  
  // ✅ CORRECT - Stable reference
  useEffect(() => {
    const config = { theme: 'dark' }; // Create inside effect
    console.log('Effect with stable config');
  }, []); // No dependencies
  
  // ✅ CORRECT - Primitive values work fine
  useEffect(() => {
    console.log('User name:', user.name);
  }, [user.name]); // Primitive string comparison
  
  return (
    <div>
      <p>Count: {count}</p>
      <button onClick={() => setCount(count + 1)}>+</button>
      <button onClick={() => setUser({ name: 'Jane' })}>Change User</button>
    </div>
  );
}

// How React compares dependencies internally (simplified)
function compareDependencies(prevDeps, nextDeps) {
  if (prevDeps.length !== nextDeps.length) return false;
  
  for (let i = 0; i < prevDeps.length; i++) {
    if (!Object.is(prevDeps[i], nextDeps[i])) {
      return false; // Dependencies changed, run effect
    }
  }
  
  return true; // Dependencies same, skip effect
}
```

---

### 9. What is stale closure in hooks?

**Stale closure occurs when a function captures old values from previous renders.**

* **Closure problem**: Function remembers old values
* **Common with**: useEffect, event handlers, timers
* **Symptoms**: Using outdated state or props
* **Solution**: Use functional updates or useRef

```jsx
import { useState, useEffect, useRef } from 'react';

// ❌ PROBLEM - Stale closure
function StaleClosureExample() {
  const [count, setCount] = useState(0);
  
  useEffect(() => {
    const timer = setInterval(() => {
      console.log('Count:', count); // Always logs 0!
      setCount(count + 1);          // Always sets to 1!
    }, 1000);
    
    return () => clearInterval(timer);
  }, []); // Empty deps = closure captures initial count (0)
  
  return <div>Count: {count}</div>;
}

// ✅ SOLUTION 1 - Functional update
function FixedWithFunctionalUpdate() {
  const [count, setCount] = useState(0);
  
  useEffect(() => {
    const timer = setInterval(() => {
      setCount(prev => {
        console.log('Current count:', prev); // Always current!
        return prev + 1;
      });
    }, 1000);
    
    return () => clearInterval(timer);
  }, []); // No stale closure!
  
  return <div>Count: {count}</div>;
}

// ✅ SOLUTION 2 - useRef for mutable reference
function FixedWithRef() {
  const [count, setCount] = useState(0);
  const countRef = useRef(count);
  
  // Keep ref in sync
  useEffect(() => {
    countRef.current = count;
  });
  
  useEffect(() => {
    const timer = setInterval(() => {
      console.log('Count:', countRef.current); // Always current!
      setCount(prev => prev + 1);
    }, 1000);
    
    return () => clearInterval(timer);
  }, []);
  
  return <div>Count: {count}</div>;
}
```

---

### 10. How do you fix stale state issues?

**Use functional updates, useRef, or include dependencies to avoid stale state.**

* **Functional updates**: Use prev => prev + 1 instead of direct values
* **useRef**: Store mutable references that don't cause re-renders
* **Dependencies**: Include all used values in dependency array
* **useCallback**: Memoize functions that depend on state

```jsx
import { useState, useEffect, useRef, useCallback } from 'react';

function StaleStateFixes() {
  const [count, setCount] = useState(0);
  const [name, setName] = useState('John');
  
  // ✅ FIX 1 - Functional updates
  const incrementWithFunctional = () => {
    setCount(prev => prev + 1); // Always uses latest state
  };
  
  // ✅ FIX 2 - useRef for mutable values
  const latestCount = useRef(count);
  latestCount.current = count; // Always keep in sync
  
  const logCurrentCount = () => {
    console.log('Current count:', latestCount.current);
  };
  
  // ✅ FIX 3 - Include dependencies
  useEffect(() => {
    const timer = setTimeout(() => {
      console.log(`${name} has count: ${count}`);
    }, 1000);
    
    return () => clearTimeout(timer);
  }, [count, name]); // Include all used values
  
  // ✅ FIX 4 - useCallback with dependencies
  const handleClick = useCallback(() => {
    console.log(`Clicked with count: ${count}`);
    setCount(prev => prev + 1);
  }, [count]); // Recreate when count changes
  
  // ✅ FIX 5 - Custom hook for stable references
  const stableCallback = useStableCallback((value) => {
    console.log('Stable callback with:', value, count);
  });
  
  return (
    <div>
      <p>Count: {count}</p>
      <p>Name: {name}</p>
      <button onClick={incrementWithFunctional}>Increment</button>
      <button onClick={logCurrentCount}>Log Count</button>
      <button onClick={handleClick}>Handle Click</button>
      <button onClick={() => stableCallback('test')}>Stable</button>
      <input value={name} onChange={(e) => setName(e.target.value)} />
    </div>
  );
}

// Custom hook for stable callbacks
function useStableCallback(callback) {
  const callbackRef = useRef(callback);
  
  useEffect(() => {
    callbackRef.current = callback;
  });
  
  return useCallback((...args) => {
    return callbackRef.current(...args);
  }, []);
}
```

---

### 11. When would you use `useRef` instead of `useState`?

**Use useRef for mutable values that don't need to trigger re-renders.**

* **No re-renders**: Changing ref.current doesn't cause re-render
* **DOM access**: Direct access to DOM elements
* **Mutable values**: Store values that change but don't affect UI
* **Previous values**: Keep track of previous state/props

```jsx
import { useState, useRef, useEffect } from 'react';

function RefVsStateExample() {
  const [count, setCount] = useState(0);        // Triggers re-render
  const renderCount = useRef(0);                // No re-render
  const inputRef = useRef(null);                // DOM reference
  const previousCount = useRef(0);              // Previous value
  const timerRef = useRef(null);                // Mutable value
  
  // Track render count (doesn't trigger re-render)
  renderCount.current += 1;
  
  // Store previous count
  useEffect(() => {
    previousCount.current = count;
  });
  
  const startTimer = () => {
    if (timerRef.current) return; // Already running
    
    timerRef.current = setInterval(() => {
      setCount(prev => prev + 1);
    }, 1000);
  };
  
  const stopTimer = () => {
    if (timerRef.current) {
      clearInterval(timerRef.current);
      timerRef.current = null;
    }
  };
  
  const focusInput = () => {
    inputRef.current?.focus(); // DOM manipulation
  };
  
  return (
    <div>
      <p>Count: {count}</p>
      <p>Previous: {previousCount.current}</p>
      <p>Renders: {renderCount.current}</p>
      
      <input ref={inputRef} placeholder="Focus me" />
      
      <div>
        <button onClick={() => setCount(count + 1)}>+1 (re-render)</button>
        <button onClick={focusInput}>Focus Input</button>
        <button onClick={startTimer}>Start Timer</button>
        <button onClick={stopTimer}>Stop Timer</button>
      </div>
    </div>
  );
}

// When to use each:
function UsageGuide() {
  // useState - when you need re-renders
  const [visible, setVisible] = useState(true);     // UI state
  const [items, setItems] = useState([]);           // Data that affects UI
  
  // useRef - when you DON'T need re-renders
  const clickCount = useRef(0);                     // Counter (no UI update)
  const cache = useRef(new Map());                  // Cache (no UI update)
  const elementRef = useRef(null);                  // DOM reference
  const intervalRef = useRef(null);                 // Timer reference
  
  const handleClick = () => {
    clickCount.current += 1;  // No re-render
    console.log('Clicked:', clickCount.current);
  };
  
  return (
    <div ref={elementRef}>
      {visible && <p>Visible content</p>}
      <button onClick={handleClick}>Click (no re-render)</button>
      <button onClick={() => setVisible(!visible)}>Toggle (re-render)</button>
    </div>
  );
}
```

---

### 12. What is `useMemo` and when should you use it?

**useMemo memoizes expensive calculations and prevents unnecessary re-computations.**

* **Performance optimization**: Avoid expensive calculations on every render
* **Referential equality**: Keep same object reference between renders
* **Dependency-based**: Only recalculates when dependencies change
* **Don't overuse**: Only for expensive operations or referential equality

```jsx
import { useState, useMemo, useCallback } from 'react';

function MemoExample({ items, filter }) {
  const [count, setCount] = useState(0);
  
  // ✅ GOOD - Expensive calculation
  const expensiveValue = useMemo(() => {
    console.log('Computing expensive value...');
    return items
      .filter(item => item.category === filter)
      .reduce((sum, item) => sum + item.price * item.quantity, 0);
  }, [items, filter]); // Only recalculate when these change
  
  // ✅ GOOD - Referential equality for child components
  const sortedItems = useMemo(() => {
    return [...items].sort((a, b) => a.name.localeCompare(b.name));
  }, [items]);
  
  // ❌ BAD - Don't memo simple calculations
  const badExample = useMemo(() => {
    return count * 2; // Too simple, not worth memoizing
  }, [count]);
  
  // ✅ GOOD - Complex object that child components depend on
  const config = useMemo(() => ({
    theme: 'dark',
    settings: { showDetails: true },
    handlers: {
      onEdit: (id) => console.log('Edit:', id),
      onDelete: (id) => console.log('Delete:', id)
    }
  }), []); // Stable reference
  
  return (
    <div>
      <p>Count: {count}</p>
      <p>Total: ${expensiveValue}</p>
      <button onClick={() => setCount(count + 1)}>Increment</button>
      
      <ItemList items={sortedItems} config={config} />
    </div>
  );
}

// Child component that benefits from memoization
const ItemList = React.memo(({ items, config }) => {
  console.log('ItemList rendered'); // Only when props actually change
  
  return (
    <ul>
      {items.map(item => (
        <li key={item.id}>
          {item.name} - ${item.price}
          <button onClick={() => config.handlers.onEdit(item.id)}>Edit</button>
        </li>
      ))}
    </ul>
  );
});

// When to use useMemo:
function UseMemoGuide() {
  const [search, setSearch] = useState('');
  const [data, setData] = useState([]);
  
  // ✅ USE - Expensive filtering/sorting
  const filteredData = useMemo(() => {
    return data
      .filter(item => item.name.toLowerCase().includes(search.toLowerCase()))
      .sort((a, b) => a.score - b.score);
  }, [data, search]);
  
  // ✅ USE - Complex object for child props
  const chartConfig = useMemo(() => ({
    type: 'bar',
    data: filteredData,
    options: { responsive: true }
  }), [filteredData]);
  
  // ❌ DON'T USE - Simple operations
  const simpleCalc = search.length > 0 ? 'searching' : 'idle'; // Just calculate directly
  
  return (
    <div>
      <input value={search} onChange={(e) => setSearch(e.target.value)} />
      <p>Status: {simpleCalc}</p>
      <Chart config={chartConfig} />
    </div>
  );
}

const Chart = React.memo(({ config }) => {
  // This won't re-render unless config reference changes
  return <div>Chart with {config.data.length} items</div>;
});
```

---

### 13. What is `useCallback` and when should you use it?

**useCallback memoizes functions to prevent unnecessary re-creations and child re-renders.**

* **Function memoization**: Returns same function reference between renders
* **Child optimization**: Prevents unnecessary re-renders of child components
* **Dependency-based**: Only recreates when dependencies change
* **Event handlers**: Especially useful for event handlers passed to children

```jsx
import { useState, useCallback, memo } from 'react';

function CallbackExample() {
  const [count, setCount] = useState(0);
  const [name, setName] = useState('John');
  
  // ❌ BAD - New function every render
  const badHandler = () => {
    console.log('Clicked with count:', count);
  };
  
  // ✅ GOOD - Memoized function
  const goodHandler = useCallback(() => {
    console.log('Clicked with count:', count);
  }, [count]); // Only recreate when count changes
  
  // ✅ GOOD - Stable function (no dependencies)
  const incrementCount = useCallback(() => {
    setCount(prev => prev + 1);
  }, []); // Never recreates
  
  const updateName = useCallback((newName) => {
    setName(newName);
  }, []);
  
  return (
    <div>
      <p>Count: {count}, Name: {name}</p>
      
      {/* Child will re-render every time with badHandler */}
      <ExpensiveChild onClick={badHandler} label="Bad" />
      
      {/* Child only re-renders when goodHandler changes */}
      <ExpensiveChild onClick={goodHandler} label="Good" />
      
      {/* Child never re-renders (stable function) */}
      <Button onClick={incrementCount}>Increment</Button>
      <NameInput onUpdate={updateName} />
    </div>
  );
}

// Memoized child component
const ExpensiveChild = memo(({ onClick, label }) => {
  console.log(`${label} child rendered`);
  return <button onClick={onClick}>{label} Button</button>;
});

const Button = memo(({ onClick, children }) => {
  console.log('Button rendered');
  return <button onClick={onClick}>{children}</button>;
});

const NameInput = memo(({ onUpdate }) => {
  return (
    <input 
      onChange={(e) => onUpdate(e.target.value)}
      placeholder="Enter name"
    />
  );
});
```

---

### 14. Difference between `useMemo` and `useCallback`

**useMemo memoizes values, useCallback memoizes functions - both prevent unnecessary recalculations.**

* **useMemo**: Returns memoized value (result of computation)
* **useCallback**: Returns memoized function (the function itself)
* **Purpose**: useMemo for expensive calculations, useCallback for stable function references
* **Equivalent**: useCallback(fn, deps) === useMemo(() => fn, deps)

```jsx
import { useState, useMemo, useCallback } from 'react';

function MemoVsCallbackExample({ items }) {
  const [count, setCount] = useState(0);
  const [filter, setFilter] = useState('');
  
  // useMemo - memoizes the RESULT (value)
  const expensiveValue = useMemo(() => {
    console.log('Computing expensive value...');
    return items
      .filter(item => item.name.includes(filter))
      .reduce((sum, item) => sum + item.price, 0);
  }, [items, filter]); // Returns: number
  
  // useCallback - memoizes the FUNCTION itself
  const handleFilter = useCallback((newFilter) => {
    console.log('Filter function called');
    setFilter(newFilter);
  }, []); // Returns: function
  
  // They're equivalent in this case:
  const memoizedFunction = useMemo(() => {
    return (newFilter) => setFilter(newFilter);
  }, []); // Same as useCallback above
  
  // Practical example showing the difference
  const processedData = useMemo(() => {
    // Heavy computation - return the VALUE
    return items.map(item => ({
      ...item,
      displayName: item.name.toUpperCase(),
      isExpensive: item.price > 100
    }));
  }, [items]);
  
  const handleItemClick = useCallback((itemId) => {
    // Event handler - return the FUNCTION
    console.log('Item clicked:', itemId);
    // Some logic here
  }, []);
  
  return (
    <div>
      <p>Count: {count}</p>
      <p>Total: ${expensiveValue}</p>
      
      <input 
        onChange={(e) => handleFilter(e.target.value)}
        placeholder="Filter items"
      />
      
      <ItemList 
        items={processedData}     // Memoized value
        onItemClick={handleItemClick}  // Memoized function
      />
      
      <button onClick={() => setCount(count + 1)}>Increment</button>
    </div>
  );
}

// Summary of differences:
function ComparisonSummary() {
  const [data, setData] = useState([]);
  
  // useMemo: "Remember this calculated VALUE"
  const sortedData = useMemo(() => {
    return [...data].sort((a, b) => a.name.localeCompare(b.name));
  }, [data]); // Type: Array
  
  // useCallback: "Remember this FUNCTION"
  const handleSort = useCallback((sortKey) => {
    setData(prev => [...prev].sort((a, b) => a[sortKey].localeCompare(b[sortKey])));
  }, []); // Type: Function
  
  return { sortedData, handleSort };
}
```

---

### 15. What is `useReducer` and how is it different from `useState`?

**useReducer manages complex state with predictable updates through reducer functions.**

* **Complex state**: Better for objects with multiple related values
* **Predictable updates**: All state changes go through reducer function
* **Action-based**: Update state by dispatching actions
* **Redux-like**: Similar pattern to Redux but local to component

```jsx
import { useReducer, useState } from 'react';

// Reducer function - pure function that returns new state
function counterReducer(state, action) {
  switch (action.type) {
    case 'increment':
      return { count: state.count + 1 };
    case 'decrement':
      return { count: state.count - 1 };
    case 'reset':
      return { count: 0 };
    case 'set':
      return { count: action.payload };
    default:
      throw new Error(`Unknown action: ${action.type}`);
  }
}

function ReducerExample() {
  // useReducer: [state, dispatch]
  const [state, dispatch] = useReducer(counterReducer, { count: 0 });
  
  return (
    <div>
      <p>Count: {state.count}</p>
      <button onClick={() => dispatch({ type: 'increment' })}>+</button>
      <button onClick={() => dispatch({ type: 'decrement' })}>-</button>
      <button onClick={() => dispatch({ type: 'reset' })}>Reset</button>
      <button onClick={() => dispatch({ type: 'set', payload: 10 })}>Set to 10</button>
    </div>
  );
}

// Complex state example - better with useReducer
function FormExample() {
  // Complex state with multiple related values
  const initialState = {
    name: '',
    email: '',
    errors: {},
    isSubmitting: false
  };
  
  function formReducer(state, action) {
    switch (action.type) {
      case 'SET_FIELD':
        return {
          ...state,
          [action.field]: action.value,
          errors: { ...state.errors, [action.field]: null }
        };
      case 'SET_ERROR':
        return {
          ...state,
          errors: { ...state.errors, [action.field]: action.error }
        };
      case 'SET_SUBMITTING':
        return { ...state, isSubmitting: action.value };
      case 'RESET':
        return initialState;
      default:
        return state;
    }
  }
  
  const [state, dispatch] = useReducer(formReducer, initialState);
  
  const handleSubmit = async (e) => {
    e.preventDefault();
    dispatch({ type: 'SET_SUBMITTING', value: true });
    
    try {
      await submitForm(state);
      dispatch({ type: 'RESET' });
    } catch (error) {
      dispatch({ type: 'SET_ERROR', field: 'general', error: error.message });
    } finally {
      dispatch({ type: 'SET_SUBMITTING', value: false });
    }
  };
  
  return (
    <form onSubmit={handleSubmit}>
      <input
        value={state.name}
        onChange={(e) => dispatch({ type: 'SET_FIELD', field: 'name', value: e.target.value })}
        placeholder="Name"
      />
      {state.errors.name && <span>{state.errors.name}</span>}
      
      <input
        value={state.email}
        onChange={(e) => dispatch({ type: 'SET_FIELD', field: 'email', value: e.target.value })}
        placeholder="Email"
      />
      {state.errors.email && <span>{state.errors.email}</span>}
      
      <button disabled={state.isSubmitting}>Submit</button>
      {state.errors.general && <div>{state.errors.general}</div>}
    </form>
  );
}

// When to use each:
function ComparisonExample() {
  // useState - simple, independent state
  const [count, setCount] = useState(0);
  const [name, setName] = useState('');
  
  // useReducer - complex, related state
  const [todoState, dispatch] = useReducer(todoReducer, {
    todos: [],
    filter: 'all',
    isLoading: false
  });
  
  return (
    <div>
      {/* Simple state updates */}
      <button onClick={() => setCount(count + 1)}>Count: {count}</button>
      
      {/* Complex state updates */}
      <button onClick={() => dispatch({ type: 'ADD_TODO', text: 'New todo' })}>
        Add Todo
      </button>
    </div>
  );
}

function todoReducer(state, action) {
  switch (action.type) {
    case 'ADD_TODO':
      return {
        ...state,
        todos: [...state.todos, { id: Date.now(), text: action.text, done: false }]
      };
    case 'TOGGLE_TODO':
      return {
        ...state,
        todos: state.todos.map(todo =>
          todo.id === action.id ? { ...todo, done: !todo.done } : todo
        )
      };
    case 'SET_FILTER':
      return { ...state, filter: action.filter };
    default:
      return state;
  }
}

async function submitForm(data) {
  // Simulate API call
  await new Promise(resolve => setTimeout(resolve, 1000));
}
```

---

### 16. Can hooks replace all lifecycle methods? (mapping)

**Yes, hooks can replace all class lifecycle methods with useEffect and other hooks.**

* **useEffect**: Covers most lifecycle methods
* **useLayoutEffect**: For synchronous effects
* **Custom hooks**: Can encapsulate complex lifecycle logic
* **Cleaner code**: More predictable than class lifecycles

```jsx
import { useState, useEffect, useLayoutEffect, useRef } from 'react';

// Class component lifecycle mapping
class ClassComponent extends React.Component {
  constructor(props) {
    super(props);
    this.state = { count: 0 };
  }
  
  componentDidMount() {
    console.log('Mounted');
    document.title = 'Component mounted';
  }
  
  componentDidUpdate(prevProps, prevState) {
    if (prevState.count !== this.state.count) {
      console.log('Count updated');
    }
  }
  
  componentWillUnmount() {
    console.log('Unmounting');
  }
  
  render() {
    return <div>Count: {this.state.count}</div>;
  }
}

// Hooks equivalent
function HooksComponent() {
  const [count, setCount] = useState(0);
  const prevCount = useRef();
  
  // componentDidMount
  useEffect(() => {
    console.log('Mounted');
    document.title = 'Component mounted';
  }, []); // Empty deps = mount only
  
  // componentDidUpdate (for count)
  useEffect(() => {
    if (prevCount.current !== undefined) {
      console.log('Count updated');
    }
    prevCount.current = count;
  }, [count]); // Runs when count changes
  
  // componentWillUnmount
  useEffect(() => {
    return () => {
      console.log('Unmounting');
    };
  }, []); // Cleanup function
  
  return <div>Count: {count}</div>;
}

// Complete lifecycle mapping
function LifecycleMappingExample({ userId }) {
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);
  const mountedRef = useRef(true);
  
  // constructor equivalent
  const initialState = useState(() => {
    console.log('Constructor equivalent - runs once');
    return { user: null, loading: true };
  });
  
  // componentDidMount + componentDidUpdate
  useEffect(() => {
    console.log('Effect runs on mount and when userId changes');
    
    const fetchUser = async () => {
      setLoading(true);
      try {
        const userData = await fetch(`/api/users/${userId}`);
        if (mountedRef.current) {
          setUser(userData);
        }
      } finally {
        if (mountedRef.current) {
          setLoading(false);
        }
      }
    };
    
    fetchUser();
  }, [userId]); // Dependency array controls when it runs
  
  // componentWillUnmount
  useEffect(() => {
    return () => {
      console.log('Cleanup - componentWillUnmount equivalent');
      mountedRef.current = false;
    };
  }, []);
  
  // getSnapshotBeforeUpdate equivalent (rare)
  useLayoutEffect(() => {
    const snapshot = document.documentElement.scrollTop;
    return () => {
      // Use snapshot if needed
    };
  });
  
  if (loading) return <div>Loading...</div>;
  return <div>User: {user?.name}</div>;
}

// Advanced lifecycle patterns
function AdvancedLifecycleHooks() {
  const [data, setData] = useState(null);
  
  // Multiple effects for separation of concerns
  useEffect(() => {
    // Data fetching logic
    fetchData().then(setData);
  }, []);
  
  useEffect(() => {
    // Analytics tracking
    trackPageView();
  }, []);
  
  useEffect(() => {
    // Event listeners
    const handleResize = () => console.log('Resized');
    window.addEventListener('resize', handleResize);
    
    return () => {
      window.removeEventListener('resize', handleResize);
    };
  }, []);
  
  return <div>Advanced component</div>;
}

async function fetchData() {
  const response = await fetch('/api/data');
  return response.json();
}

function trackPageView() {
  console.log('Page viewed');
}
```

---

### 17. How do you create custom hooks?

**Custom hooks are functions that start with 'use' and can call other hooks.**

* **Reusable logic**: Extract and share stateful logic between components
* **Naming convention**: Must start with 'use'
* **Composition**: Can use other hooks inside
* **Return anything**: Can return values, functions, or objects

```jsx
import { useState, useEffect, useCallback } from 'react';

// Simple custom hook
function useCounter(initialValue = 0) {
  const [count, setCount] = useState(initialValue);
  
  const increment = useCallback(() => setCount(prev => prev + 1), []);
  const decrement = useCallback(() => setCount(prev => prev - 1), []);
  const reset = useCallback(() => setCount(initialValue), [initialValue]);
  
  return { count, increment, decrement, reset };
}

// Data fetching hook
function useFetch(url) {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  
  useEffect(() => {
    const fetchData = async () => {
      try {
        setLoading(true);
        setError(null);
        const response = await fetch(url);
        const result = await response.json();
        setData(result);
      } catch (err) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    };
    
    fetchData();
  }, [url]);
  
  return { data, loading, error };
}

// Local storage hook
function useLocalStorage(key, initialValue) {
  const [storedValue, setStoredValue] = useState(() => {
    try {
      const item = window.localStorage.getItem(key);
      return item ? JSON.parse(item) : initialValue;
    } catch (error) {
      return initialValue;
    }
  });
  
  const setValue = useCallback((value) => {
    try {
      setStoredValue(value);
      window.localStorage.setItem(key, JSON.stringify(value));
    } catch (error) {
      console.error('Error saving to localStorage:', error);
    }
  }, [key]);
  
  return [storedValue, setValue];
}

// Using custom hooks
function ComponentUsingCustomHooks() {
  const { count, increment, decrement, reset } = useCounter(10);
  const { data, loading, error } = useFetch('/api/users');
  const [name, setName] = useLocalStorage('userName', '');
  
  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error}</div>;
  
  return (
    <div>
      <p>Count: {count}</p>
      <button onClick={increment}>+</button>
      <button onClick={decrement}>-</button>
      <button onClick={reset}>Reset</button>
      
      <p>Users: {data?.length}</p>
      
      <input 
        value={name} 
        onChange={(e) => setName(e.target.value)}
        placeholder="Your name"
      />
      <p>Saved name: {name}</p>
    </div>
  );
}

// Advanced custom hook with multiple features
function useApi(baseUrl) {
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  
  const request = useCallback(async (endpoint, options = {}) => {
    setLoading(true);
    setError(null);
    
    try {
      const response = await fetch(`${baseUrl}${endpoint}`, {
        headers: { 'Content-Type': 'application/json' },
        ...options
      });
      
      if (!response.ok) throw new Error('Request failed');
      return await response.json();
    } catch (err) {
      setError(err.message);
      throw err;
    } finally {
      setLoading(false);
    }
  }, [baseUrl]);
  
  const get = useCallback((endpoint) => request(endpoint), [request]);
  const post = useCallback((endpoint, data) => 
    request(endpoint, { method: 'POST', body: JSON.stringify(data) }), [request]);
  
  return { request, get, post, loading, error };
}
```

---

### 18. How do you share logic without HOCs?

**Use custom hooks to share stateful logic between components without wrapper components.**

* **Custom hooks**: Extract logic into reusable functions
* **Composition**: Combine multiple hooks for complex logic
* **No wrapper hell**: Avoid nested HOC components
* **Better testing**: Easier to test isolated logic

```jsx
import { useState, useEffect, useCallback } from 'react';

// OLD WAY - Higher Order Component (HOC)
function withAuth(WrappedComponent) {
  return function AuthenticatedComponent(props) {
    const [user, setUser] = useState(null);
    const [loading, setLoading] = useState(true);
    
    useEffect(() => {
      checkAuth().then(user => {
        setUser(user);
        setLoading(false);
      });
    }, []);
    
    if (loading) return <div>Loading...</div>;
    if (!user) return <div>Please login</div>;
    
    return <WrappedComponent {...props} user={user} />;
  };
}

// Usage with HOC (creates wrapper components)
const AuthenticatedProfile = withAuth(Profile);
const AuthenticatedDashboard = withAuth(Dashboard);

// NEW WAY - Custom Hook
function useAuth() {
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  
  useEffect(() => {
    checkAuth()
      .then(setUser)
      .catch(setError)
      .finally(() => setLoading(false));
  }, []);
  
  const login = useCallback(async (credentials) => {
    setLoading(true);
    try {
      const user = await authenticate(credentials);
      setUser(user);
      return user;
    } catch (err) {
      setError(err.message);
      throw err;
    } finally {
      setLoading(false);
    }
  }, []);
  
  const logout = useCallback(() => {
    setUser(null);
    localStorage.removeItem('token');
  }, []);
  
  return { user, loading, error, login, logout };
}

// Usage with custom hook (no wrapper components)
function Profile() {
  const { user, loading, error, logout } = useAuth();
  
  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error}</div>;
  if (!user) return <div>Please login</div>;
  
  return (
    <div>
      <h1>Profile: {user.name}</h1>
      <button onClick={logout}>Logout</button>
    </div>
  );
}

function Dashboard() {
  const { user, loading } = useAuth();
  
  if (loading) return <div>Loading...</div>;
  if (!user) return <div>Please login</div>;
  
  return <div>Dashboard for {user.name}</div>;
}

// Complex logic sharing with multiple hooks
function useFormWithValidation(initialValues, validationRules) {
  const [values, setValues] = useState(initialValues);
  const [errors, setErrors] = useState({});
  const [touched, setTouched] = useState({});
  
  const validate = useCallback((fieldName, value) => {
    const rule = validationRules[fieldName];
    if (!rule) return null;
    
    if (rule.required && !value) {
      return 'This field is required';
    }
    if (rule.minLength && value.length < rule.minLength) {
      return `Minimum length is ${rule.minLength}`;
    }
    return null;
  }, [validationRules]);
  
  const setValue = useCallback((fieldName, value) => {
    setValues(prev => ({ ...prev, [fieldName]: value }));
    
    const error = validate(fieldName, value);
    setErrors(prev => ({ ...prev, [fieldName]: error }));
  }, [validate]);
  
  const setTouched = useCallback((fieldName) => {
    setTouched(prev => ({ ...prev, [fieldName]: true }));
  }, []);
  
  const isValid = Object.values(errors).every(error => !error);
  
  return {
    values,
    errors,
    touched,
    setValue,
    setTouched,
    isValid
  };
}

// Multiple components using the same form logic
function LoginForm() {
  const form = useFormWithValidation(
    { email: '', password: '' },
    {
      email: { required: true },
      password: { required: true, minLength: 6 }
    }
  );
  
  return (
    <form>
      <input
        value={form.values.email}
        onChange={(e) => form.setValue('email', e.target.value)}
        onBlur={() => form.setTouched('email')}
      />
      {form.touched.email && form.errors.email && (
        <span>{form.errors.email}</span>
      )}
      
      <input
        type="password"
        value={form.values.password}
        onChange={(e) => form.setValue('password', e.target.value)}
        onBlur={() => form.setTouched('password')}
      />
      {form.touched.password && form.errors.password && (
        <span>{form.errors.password}</span>
      )}
      
      <button disabled={!form.isValid}>Login</button>
    </form>
  );
}

function SignupForm() {
  const form = useFormWithValidation(
    { name: '', email: '', password: '' },
    {
      name: { required: true },
      email: { required: true },
      password: { required: true, minLength: 8 }
    }
  );
  
  // Same form logic, different fields
  return (
    <form>
      {/* Similar structure with different fields */}
    </form>
  );
}

// Benefits of custom hooks over HOCs:
// 1. No wrapper components
// 2. Better composition
// 3. Easier testing
// 4. More flexible
// 5. Better TypeScript support

async function checkAuth() {
  const token = localStorage.getItem('token');
  if (!token) return null;
  
  const response = await fetch('/api/me', {
    headers: { Authorization: `Bearer ${token}` }
  });
  
  return response.ok ? response.json() : null;
}

async function authenticate(credentials) {
  const response = await fetch('/api/login', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(credentials)
  });
  
  if (!response.ok) throw new Error('Authentication failed');
  
  const { user, token } = await response.json();
  localStorage.setItem('token', token);
  return user;
}
```

---

## 🟡 3. Component Lifecycle & Internals

---

### 1. Lifecycle of class components

**Class components have three main phases: Mounting, Updating, and Unmounting with specific methods for each.**

* **Mounting**: Component is being created and inserted into DOM
* **Updating**: Component is being re-rendered as result of changes to props or state
* **Unmounting**: Component is being removed from DOM
* **Error Handling**: Component catches JavaScript errors anywhere in child component tree

```jsx
class LifecycleExample extends React.Component {
  constructor(props) {
    super(props);
    this.state = { count: 0 };
    console.log('1. Constructor - Initialize state');
  }
  
  static getDerivedStateFromProps(props, state) {
    console.log('2. getDerivedStateFromProps - Sync state with props');
    // Return object to update state, or null to update nothing
    return null;
  }
  
  componentDidMount() {
    console.log('3. componentDidMount - Component mounted');
    // API calls, subscriptions, DOM manipulation
    this.timer = setInterval(() => {
      this.setState(prev => ({ count: prev.count + 1 }));
    }, 1000);
  }
  
  shouldComponentUpdate(nextProps, nextState) {
    console.log('4. shouldComponentUpdate - Should re-render?');
    // Return false to prevent re-render
    return nextState.count !== this.state.count;
  }
  
  getSnapshotBeforeUpdate(prevProps, prevState) {
    console.log('5. getSnapshotBeforeUpdate - Before DOM update');
    // Capture info from DOM before update
    return { scrollPosition: window.scrollY };
  }
  
  componentDidUpdate(prevProps, prevState, snapshot) {
    console.log('6. componentDidUpdate - After DOM update');
    // Use snapshot if needed
    if (snapshot && snapshot.scrollPosition) {
      // Restore scroll position if needed
    }
  }
  
  componentWillUnmount() {
    console.log('7. componentWillUnmount - Cleanup');
    // Cleanup subscriptions, timers, etc.
    clearInterval(this.timer);
  }
  
  static getDerivedStateFromError(error) {
    console.log('8. getDerivedStateFromError - Error boundary');
    return { hasError: true };
  }
  
  componentDidCatch(error, errorInfo) {
    console.log('9. componentDidCatch - Log error');
    // Log error to service
  }
  
  render() {
    console.log('Render - Return JSX');
    if (this.state.hasError) {
      return <h1>Something went wrong.</h1>;
    }
    
    return (
      <div>
        <h1>Count: {this.state.count}</h1>
        <button onClick={() => this.setState({ count: this.state.count + 1 })}>
          Increment
        </button>
      </div>
    );
  }
}

// Lifecycle order:
// MOUNTING: constructor → getDerivedStateFromProps → render → componentDidMount
// UPDATING: getDerivedStateFromProps → shouldComponentUpdate → render → getSnapshotBeforeUpdate → componentDidUpdate
// UNMOUNTING: componentWillUnmount
// ERROR: getDerivedStateFromError → componentDidCatch
```

---

### 2. Lifecycle of functional components

**Functional components use hooks to replicate lifecycle behavior with useEffect handling most lifecycle needs.**

* **Mounting**: useEffect with empty dependency array
* **Updating**: useEffect with dependencies
* **Unmounting**: useEffect cleanup function
* **No direct equivalent**: Some class methods have no direct hook equivalent

```jsx
import { useState, useEffect, useLayoutEffect, useRef } from 'react';

function FunctionalLifecycle({ userId }) {
  const [count, setCount] = useState(0);
  const [user, setUser] = useState(null);
  const mountedRef = useRef(true);
  const prevUserId = useRef();

  useEffect(() => {
    console.log('componentDidMount');
    const timer = setInterval(() => setCount(prev => prev + 1), 1000);
    return () => {
      clearInterval(timer);
      mountedRef.current = false;
    };
  }, []);

  useEffect(() => {
    if (prevUserId.current !== undefined) {
      console.log('userId changed');
    }
    prevUserId.current = userId;
  }, [userId]);

  useEffect(() => {
    if (userId) {
      fetchUser(userId).then(userData => {
        if (mountedRef.current) {
          setUser(userData);
        }
      });
    }
  }, [userId]);

  return (
    <div>
      <h1>Count: {count}</h1>
      <p>User: {user?.name || 'Loading...'}</p>
      <button onClick={() => setCount(count + 1)}>Increment</button>
    </div>
  );
}

async function fetchUser(userId) {
  const response = await fetch(`/api/users/${userId}`);
  return response.json();
}
---

### 3. Difference between `componentDidMount` and `useEffect`

**componentDidMount runs once after mount, useEffect can run on mount, updates, or both depending on dependencies.**

* **componentDidMount**: Always runs once after initial render
* **useEffect**: Runs based on dependency array
* **Timing**: Both run after DOM updates (asynchronous)
* **Cleanup**: useEffect can return cleanup function

```jsx
// Class component - componentDidMount
class ClassExample extends React.Component {
  componentDidMount() {
    console.log('Runs once after mount');
    // API calls, subscriptions
    this.fetchData();
    
    // No built-in cleanup - need componentWillUnmount
    this.timer = setInterval(() => {
      console.log('Timer tick');
    }, 1000);
  }
  
  componentWillUnmount() {
    clearInterval(this.timer); // Manual cleanup
  }
  
  fetchData = async () => {
    const data = await fetch('/api/data');
    this.setState({ data });
  }
  
  render() {
    return <div>Class Component</div>;
  }
}

// function component 
function FunctionalExample({ userId }) {
  const [data, setData] = useState(null);

  useEffect(() => {
    console.log('Runs once after mount');
    fetchData();
    
    const timer = setInterval(() => {
      console.log('Timer tick');
    }, 1000);
    
    return () => clearInterval(timer);
  }, []);

  useEffect(() => {
    if (userId) {
      fetchUserData(userId);
    }
  }, [userId]);

  useEffect(() => {
    document.title = `Data: ${data?.length || 0} items`;
  });

  const fetchData = async () => {
    const response = await fetch('/api/data');
    const result = await response.json();
    setData(result);
  };

  const fetchUserData = async (id) => {
    const response = await fetch(`/api/users/${id}`);
    const user = await response.json();
    console.log('User data:', user);
  };

  return <div>Functional Component</div>;
}
```

### 4. Difference between `componentWillMount`, `componentDidMount`, and `getDerivedStateFromProps`

**These methods serve different purposes: componentWillMount is deprecated, componentDidMount for side effects, getDerivedStateFromProps for state synchronization.**

* **componentWillMount**: DEPRECATED - don't use
* **componentDidMount**: Side effects after initial render
* **getDerivedStateFromProps**: Sync state with props changes
* **Timing**: getDerivedStateFromProps runs before render, componentDidMount after

```jsx
class LifecycleMethods extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      count: 0,
      derivedValue: props.initialValue || 0
    };
  }
  
  static getDerivedStateFromProps(nextProps, prevState) {
    if (nextProps.initialValue !== prevState.derivedValue) {
      return { derivedValue: nextProps.initialValue };
    }
    return null;
  }
  
  componentDidMount() {
    this.fetchData();
    this.setupEventListeners();
    this.timer = setInterval(() => {
      this.setState(prev => ({ count: prev.count + 1 }));
    }, 1000);
  }
  
  componentWillUnmount() {
    clearInterval(this.timer);
    this.cleanupEventListeners();
  }
  
  fetchData = async () => {
    try {
      const response = await fetch('/api/data');
      const data = await response.json();
      this.setState({ data });
    } catch (error) {
      this.setState({ error: error.message });
    }
  }
  
  setupEventListeners = () => {
    window.addEventListener('resize', this.handleResize);
  }
  
  cleanupEventListeners = () => {
    window.removeEventListener('resize', this.handleResize);
  }
  
  handleResize = () => {
    this.setState({ windowWidth: window.innerWidth });
  }
  
  render() {
    return (
      <div>
        <p>Count: {this.state.count}</p>
        <p>Derived Value: {this.state.derivedValue}</p>
        <p>Window Width: {this.state.windowWidth}</p>
      </div>
    );
  }
}
---

### 5. What is React reconciliation?

**Reconciliation is React's algorithm for comparing virtual DOM trees and updating only what changed.**

* **Diffing algorithm**: Compares old and new virtual DOM trees
* **Minimal updates**: Only updates changed elements in real DOM
* **Key optimization**: Uses keys to identify moved/added/removed elements
* **Performance**: Makes React fast by avoiding unnecessary DOM operations

````jsx
function ReconciliationExample() {
  const [items, setItems] = useState([
    { id: 1, name: 'Apple' },
    { id: 2, name: 'Banana' },
    { id: 3, name: 'Cherry' }
  ]);
  
  const [showDetails, setShowDetails] = useState(false);
  
  const addItem = () => {
    setItems(prev => [...prev, { 
      id: Date.now(), 
      name: 'New Item' 
    }]);
  };
  
  const removeItem = (id) => {
    setItems(prev => prev.filter(item => item.id !== id));
  };
  
  return (
    <div>
      <h1>Items ({items.length})</h1>
      <ul>
        {items.map(item => (
          <li key={item.id}>
            {item.name}
            {showDetails && <span> - ID: {item.id}</span>}
            <button onClick={() => removeItem(item.id)}>Remove</button>
          </li>
        ))}
      </ul>
      <button onClick={addItem}>Add Item</button>
      <button onClick={() => setShowDetails(!showDetails)}>
        {showDetails ? 'Hide' : 'Show'} Details
      </button>
    </div>
  );
}
```

### 6. What is React Fiber?

**React Fiber is the new reconciliation algorithm that enables incremental rendering and better performance.**

* **Incremental rendering**: Break work into chunks, pause and resume
* **Priority-based**: High priority updates interrupt low priority ones
* **Better UX**: Keeps UI responsive during heavy computations
* **Concurrent features**: Enables Suspense, concurrent mode, time slicing

```jsx
import { useState, useTransition, useDeferredValue } from 'react';

function FiberExample() {
  const [query, setQuery] = useState('');
  const [items, setItems] = useState(generateItems(10000));
  const [isPending, startTransition] = useTransition();
  
  const deferredQuery = useDeferredValue(query);
  
  const filteredItems = items.filter(item => 
    item.name.toLowerCase().includes(deferredQuery.toLowerCase())
  );
  
  const handleSearch = (value) => {
    setQuery(value);
    startTransition(() => {
      setItems(generateItems(20000));
    });
  };
  
  return (
    <div>
      <h1>React Fiber Demo</h1>
      <input
        value={query}
        onChange={(e) => handleSearch(e.target.value)}
        placeholder="Search items..."
      />
      {isPending && <div>Updating items...</div>}
      <div style={{ opacity: isPending ? 0.7 : 1 }}>
        <p>Found {filteredItems.length} items</p>
        <ul>
          {filteredItems.slice(0, 100).map(item => (
            <li key={item.id}>{item.name}</li>
          ))}
        </ul>
      </div>
    </div>
  );
}

function generateItems(count) {
  return Array.from({ length: count }, (_, i) => ({
    id: i,
    name: `Item ${i}`,
    value: Math.random()
  }));
}
---

### 7. What causes a component to re-render?

**Components re-render when state changes, props change, parent re-renders, or context changes.**

* **State changes**: useState, useReducer updates trigger re-render
* **Props changes**: New props from parent cause re-render
* **Parent re-renders**: Child components re-render by default
* **Context changes**: useContext consumers re-render when context updates

````jsx
import { useState, useContext, createContext, memo, useCallback } from 'react';

const ThemeContext = createContext();

function ReRenderExample() {
  const [count, setCount] = useState(0);
  const [name, setName] = useState('John');
  const [theme, setTheme] = useState('light');
  
  console.log('Parent re-rendered');
  
  return (
    <ThemeContext.Provider value={{ theme, setTheme }}>
      <div>
        <h1>Re-render Causes</h1>
        
        <p>Count: {count}</p>
        <button onClick={() => setCount(count + 1)}>
          Increment (causes re-render)
        </button>
        
        <ChildWithProps name={name} count={count} />
        <ChildWithoutProps />
        <ChildUsingContext />
        
        <input 
          value={name} 
          onChange={(e) => setName(e.target.value)}
          placeholder="Change name"
        />
        
        <button onClick={() => setTheme(theme === 'light' ? 'dark' : 'light')}>
          Toggle Theme
        </button>
      </div>
    </ThemeContext.Provider>
  );
}

function ChildWithProps({ name, count }) {
  console.log('ChildWithProps re-rendered');
  
  return (
    <div>
      <h3>Child with Props</h3>
      <p>Name: {name}, Count: {count}</p>
    </div>
  );
}

function ChildWithoutProps() {
  console.log('ChildWithoutProps re-rendered');
  
  return (
    <div>
      <h3>Child without Props</h3>
      <p>I re-render when parent re-renders!</p>
    </div>
  );
}

function ChildUsingContext() {
  const { theme } = useContext(ThemeContext);
  console.log('ChildUsingContext re-rendered');
  
  return (
    <div style={{ background: theme === 'light' ? 'white' : 'black', color: theme === 'light' ? 'black' : 'white' }}>
      <h3>Child using Context</h3>
      <p>Current theme: {theme}</p>
    </div>
  );
}
---


## 🟡 4. Rendering & Performance Optimization (High Priority)

---

### 1. What is automatic batching in React 18?

**Automatic batching groups multiple state updates into a single re-render for better performance.**

* **React 18 improvement**: Batches all updates, not just event handlers
* **Better performance**: Fewer re-renders mean better performance
* **Automatic**: Works without any code changes
* **Opt-out available**: Use flushSync to force immediate updates

```jsx
import { useState } from 'react';
import { flushSync } from 'react-dom';

function AutomaticBatchingExample() {
  const [count, setCount] = useState(0);
  const [name, setName] = useState('John');
  const [loading, setLoading] = useState(false);
  
  console.log('Component rendered');
  
  // React 17: Multiple re-renders in timeouts/promises
  // React 18: Single re-render (automatic batching)
  const handleAsyncUpdate = () => {
    setTimeout(() => {
      setCount(c => c + 1);    // Batched
      setName('Jane');         // Batched
      setLoading(false);       // Batched
      // Only 1 re-render in React 18!
    }, 1000);
  };
  
  const handlePromiseUpdate = () => {
    fetch('/api/data').then(() => {
      setCount(c => c + 1);    // Batched
      setName('Bob');          // Batched
      setLoading(false);       // Batched
      // Only 1 re-render in React 18!
    });
  };
  
  // Event handlers were always batched (React 17 & 18)
  const handleSyncUpdate = () => {
    setCount(c => c + 1);      // Batched
    setName('Alice');          // Batched
    setLoading(true);          // Batched
    // Always 1 re-render
  };
  
  // Opt-out of batching when needed
  const handleImmediateUpdate = () => {
    flushSync(() => {
      setCount(c => c + 1);    // Immediate re-render
    });
    
    flushSync(() => {
      setName('Immediate');    // Another immediate re-render
    });
    // 2 separate re-renders
  };
  
  return (
    <div>
      <h1>Automatic Batching Demo</h1>
      <p>Count: {count}</p>
      <p>Name: {name}</p>
      <p>Loading: {loading ? 'Yes' : 'No'}</p>
      
      <button onClick={handleSyncUpdate}>Sync Update</button>
      <button onClick={handleAsyncUpdate}>Async Update</button>
      <button onClick={handlePromiseUpdate}>Promise Update</button>
      <button onClick={handleImmediateUpdate}>Immediate Update</button>
    </div>
  );
}

// Comparison: React 17 vs React 18
function BatchingComparison() {
  const [count, setCount] = useState(0);
  const [flag, setFlag] = useState(false);
  
  const handleClick = () => {
    // React 17: 2 re-renders in setTimeout
    // React 18: 1 re-render (batched)
    setTimeout(() => {
      setCount(c => c + 1);
      setFlag(f => !f);
    }, 100);
  };
  
  console.log('Rendered with count:', count, 'flag:', flag);
  
  return (
    <div>
      <p>Count: {count}, Flag: {flag.toString()}</p>
      <button onClick={handleClick}>Update in setTimeout</button>
    </div>
  );
}
```

---

### 2. What is Concurrent Rendering?

**Concurrent rendering allows React to pause, resume, and prioritize work for better user experience.**

* **Interruptible**: React can pause work and handle urgent updates
* **Priority-based**: High priority updates interrupt low priority ones
* **Better UX**: Keeps interface responsive during heavy computations
* **Opt-in**: Use concurrent features like useTransition, useDeferredValue

```jsx
import { useState, useTransition, useDeferredValue, Suspense } from 'react';

function ConcurrentRenderingExample() {
  const [query, setQuery] = useState('');
  const [items, setItems] = useState(generateItems(5000));
  const [isPending, startTransition] = useTransition();
  
  // Deferred value - lower priority
  const deferredQuery = useDeferredValue(query);
  
  // Filter items based on deferred query
  const filteredItems = items.filter(item =>
    item.name.toLowerCase().includes(deferredQuery.toLowerCase())
  );
  
  const handleSearch = (value) => {
    // High priority - immediate update
    setQuery(value);
    
    // Low priority - can be interrupted
    startTransition(() => {
      // Simulate expensive operation
      const newItems = generateItems(10000);
      setItems(newItems);
    });
  };
  
  return (
    <div>
      <h1>Concurrent Rendering Demo</h1>
      
      {/* Input stays responsive */}
      <input
        value={query}
        onChange={(e) => handleSearch(e.target.value)}
        placeholder="Search items..."
        style={{ fontSize: '16px', padding: '8px' }}
      />
      
      {isPending && <div>🔄 Updating items...</div>}
      
      {/* List updates with lower priority */}
      <div style={{ opacity: isPending ? 0.7 : 1 }}>
        <p>Found {filteredItems.length} items</p>
        <ItemList items={filteredItems.slice(0, 100)} />
      </div>
    </div>
  );
}

// Heavy component that benefits from concurrent rendering
function ItemList({ items }) {
  return (
    <ul>
      {items.map(item => (
        <li key={item.id}>
          {item.name} - {item.category}
        </li>
      ))}
    </ul>
  );
}

// Concurrent rendering with Suspense
function ConcurrentWithSuspense() {
  const [showHeavy, setShowHeavy] = useState(false);
  
  return (
    <div>
      <h2>Concurrent Rendering with Suspense</h2>
      <button onClick={() => setShowHeavy(!showHeavy)}>
        {showHeavy ? 'Hide' : 'Show'} Heavy Component
      </button>
      
      {showHeavy && (
        <Suspense fallback={<div>Loading heavy component...</div>}>
          <HeavyComponent />
        </Suspense>
      )}
    </div>
  );
}

// Simulate heavy component
function HeavyComponent() {
  const items = generateItems(1000);
  
  return (
    <div>
      <h3>Heavy Component</h3>
      {items.slice(0, 50).map(item => (
        <div key={item.id} style={{ padding: '4px', border: '1px solid #ccc' }}>
          {item.name} - {expensiveCalculation(item.id)}
        </div>
      ))}
    </div>
  );
}

function generateItems(count) {
  return Array.from({ length: count }, (_, i) => ({
    id: i,
    name: `Item ${i}`,
    category: `Category ${i % 5}`
  }));
}

function expensiveCalculation(n) {
  let result = 0;
  for (let i = 0; i < 1000; i++) {
    result += Math.sin(n + i);
  }
  return result.toFixed(2);
}
````

### 3. Difference between legacy and concurrent rendering

**Legacy rendering is synchronous and blocking, concurrent rendering is interruptible and prioritized.**

* **Legacy**: Synchronous, blocking, all-or-nothing updates
* **Concurrent**: Asynchronous, interruptible, priority-based updates
* **Performance**: Concurrent keeps UI responsive during heavy work
* **Backwards compatible**: Legacy mode still available

```jsx
import { useState, useTransition, useDeferredValue } from 'react';
import { createRoot } from 'react-dom/client';

// Legacy rendering behavior (React 17 style)
function LegacyBehaviorExample() {
  const [count, setCount] = useState(0);
  const [heavyList, setHeavyList] = useState([]);
  
  // In legacy mode, this blocks the UI
  const updateHeavyList = () => {
    const newList = [];
    
    // Expensive synchronous operation
    for (let i = 0; i < 10000; i++) {
      newList.push({
        id: i,
        value: Math.random(),
        computed: expensiveComputation(i)
      });
    }
    
    setHeavyList(newList);
    // UI is blocked until this completes
  };
  
  return (
    <div>
      <h2>Legacy Rendering (Blocking)</h2>
      
      {/* This button becomes unresponsive during heavy updates */}
      <button onClick={() => setCount(count + 1)}>
        Count: {count} (Try clicking during heavy update)
      </button>
      
      <button onClick={updateHeavyList}>
        Update Heavy List (Blocks UI)
      </button>
      
      <div>Items: {heavyList.length}</div>
    </div>
  );
}

// Concurrent rendering behavior (React 18)
function ConcurrentBehaviorExample() {
  const [count, setCount] = useState(0);
  const [heavyList, setHeavyList] = useState([]);
  const [isPending, startTransition] = useTransition();
  
  // In concurrent mode, this doesn't block the UI
  const updateHeavyList = () => {
    startTransition(() => {
      const newList = [];
      
      // Same expensive operation, but non-blocking
      for (let i = 0; i < 10000; i++) {
        newList.push({
          id: i,
          value: Math.random(),
          computed: expensiveComputation(i)
        });
      }
      
      setHeavyList(newList);
      // UI stays responsive!
    });
  };
  
  return (
    <div>
      <h2>Concurrent Rendering (Non-blocking)</h2>
      
      {/* This button stays responsive */}
      <button onClick={() => setCount(count + 1)}>
        Count: {count} (Always responsive!)
      </button>
      
      <button onClick={updateHeavyList} disabled={isPending}>
        {isPending ? 'Updating...' : 'Update Heavy List (Non-blocking)'}
      </button>
      
      <div>Items: {heavyList.length}</div>
    </div>
  );
}

// Comparison of rendering modes
function RenderingComparison() {
  const [mode, setMode] = useState('concurrent');
  const [input, setInput] = useState('');
  const [list, setList] = useState([]);
  const [isPending, startTransition] = useTransition();
  
  const deferredInput = useDeferredValue(input);
  
  const updateList = (value) => {
    const updateFn = () => {
      // Simulate expensive filtering
      const filtered = generateLargeList().filter(item =>
        item.toLowerCase().includes(value.toLowerCase())
      );
      setList(filtered);
    };
    
    if (mode === 'legacy') {
      // Legacy: Immediate, blocking update
      updateFn();
    } else {
      // Concurrent: Non-blocking update
      startTransition(updateFn);
    }
  };
  
  // Use deferred value in concurrent mode
  const displayValue = mode === 'concurrent' ? deferredInput : input;
  
  return (
    <div>
      <h2>Rendering Mode Comparison</h2>
      
      <div>
        <label>
          <input
            type="radio"
            checked={mode === 'legacy'}
            onChange={() => setMode('legacy')}
          />
          Legacy Mode (Blocking)
        </label>
        <label>
          <input
            type="radio"
            checked={mode === 'concurrent'}
            onChange={() => setMode('concurrent')}
          />
          Concurrent Mode (Non-blocking)
        </label>
      </div>
      
      <input
        value={input}
        onChange={(e) => {
          setInput(e.target.value);
          updateList(e.target.value);
        }}
        placeholder="Type to filter..."
      />
      
      {isPending && mode === 'concurrent' && <div>🔄 Filtering...</div>}
      
      <div>
        <p>Results for: "{displayValue}"</p>
        <p>Found: {list.length} items</p>
        <ul>
          {list.slice(0, 20).map((item, index) => (
            <li key={index}>{item}</li>
          ))}
        </ul>
      </div>
    </div>
  );
}

// How to enable concurrent rendering
function AppWithConcurrentMode() {
  return (
    <div>
      <LegacyBehaviorExample />
      <hr />
      <ConcurrentBehaviorExample />
      <hr />
      <RenderingComparison />
    </div>
  );
}

// Enable concurrent mode
const container = document.getElementById('root');
const root = createRoot(container); // Concurrent mode
root.render(<AppWithConcurrentMode />);

// Legacy mode (for comparison)
// ReactDOM.render(<App />, container); // Legacy mode

function expensiveComputation(n) {
  let result = 0;
  for (let i = 0; i < 100; i++) {
    result += Math.sin(n + i);
  }
  return result.toFixed(2);
}

function generateLargeList() {
  return Array.from({ length: 5000 }, (_, i) => `Item ${i} with some text`);
}
```

---

### 4. What is `startTransition` and `useTransition`?

**startTransition marks updates as non-urgent, useTransition provides pending state for transitions.**

* **Non-urgent updates**: Mark state updates as low priority
* **Interruptible**: Can be interrupted by urgent updates
* **Pending state**: useTransition provides loading state
* **Better UX**: Keeps interface responsive during heavy updates

```jsx
import { useState, useTransition, startTransition } from 'react';

function TransitionExample() {
  const [query, setQuery] = useState('');
  const [results, setResults] = useState([]);
  const [isPending, startTransition] = useTransition();
  
  const handleSearch = (value) => {
    // Urgent update - immediate
    setQuery(value);
    
    // Non-urgent update - can be interrupted
    startTransition(() => {
      // Expensive search operation
      const searchResults = performExpensiveSearch(value);
      setResults(searchResults);
    });
  };
  
  return (
    <div>
      <h2>useTransition Example</h2>
      
      {/* Input stays responsive */}
      <input
        value={query}
        onChange={(e) => handleSearch(e.target.value)}
        placeholder="Search..."
      />
      
      {/* Show pending state */}
      {isPending && <div>🔍 Searching...</div>}
      
      {/* Results update with lower priority */}
      <div style={{ opacity: isPending ? 0.5 : 1 }}>
        <p>Results for "{query}": {results.length} found</p>
        <ul>
          {results.slice(0, 50).map(result => (
            <li key={result.id}>{result.title}</li>
          ))}
        </ul>
      </div>
    </div>
  );
}

// Using startTransition without hook
function StartTransitionExample() {
  const [tab, setTab] = useState('home');
  const [content, setContent] = useState('Home content');
  
  const switchTab = (newTab) => {
    // Urgent update - immediate tab switch
    setTab(newTab);
    
    // Non-urgent update - content loading
    startTransition(() => {
      // Simulate expensive content loading
      const newContent = loadTabContent(newTab);
      setContent(newContent);
    });
  };
  
  return (
    <div>
      <h2>startTransition Example</h2>
      
      {/* Tabs switch immediately */}
      <div>
        {['home', 'about', 'contact'].map(tabName => (
          <button
            key={tabName}
            onClick={() => switchTab(tabName)}
            style={{
              background: tab === tabName ? '#007bff' : '#f8f9fa',
              color: tab === tabName ? 'white' : 'black'
            }}
          >
            {tabName.charAt(0).toUpperCase() + tabName.slice(1)}
          </button>
        ))}\n      </div>\n      \n      {/* Content loads with lower priority */}\n      <div>\n        <h3>Current Tab: {tab}</h3>\n        <p>{content}</p>\n      </div>\n    </div>\n  );\n}\n\n// Complex example with multiple transitions\nfunction MultipleTransitionsExample() {\n  const [filter, setFilter] = useState('all');\n  const [sort, setSort] = useState('name');\n  const [items, setItems] = useState(generateItems(1000));\n  const [isPending, startTransition] = useTransition();\n  \n  const updateView = (newFilter, newSort) => {\n    // Urgent updates - immediate UI feedback\n    setFilter(newFilter);\n    setSort(newSort);\n    \n    // Non-urgent update - expensive filtering and sorting\n    startTransition(() => {\n      let filtered = generateItems(5000);\n      \n      // Expensive filtering\n      if (newFilter !== 'all') {\n        filtered = filtered.filter(item => item.category === newFilter);\n      }\n      \n      // Expensive sorting\n      filtered.sort((a, b) => {\n        if (newSort === 'name') return a.name.localeCompare(b.name);\n        if (newSort === 'price') return a.price - b.price;\n        return a.id - b.id;\n      });\n      \n      setItems(filtered);\n    });\n  };\n  \n  return (\n    <div>\n      <h2>Multiple Transitions</h2>\n      \n      {/* Controls update immediately */}\n      <div>\n        <select \n          value={filter} \n          onChange={(e) => updateView(e.target.value, sort)}\n        >\n          <option value=\"all\">All Categories</option>\n          <option value=\"electronics\">Electronics</option>\n          <option value=\"clothing\">Clothing</option>\n          <option value=\"books\">Books</option>\n        </select>\n        \n        <select \n          value={sort} \n          onChange={(e) => updateView(filter, e.target.value)}\n        >\n          <option value=\"name\">Sort by Name</option>\n          <option value=\"price\">Sort by Price</option>\n          <option value=\"id\">Sort by ID</option>\n        </select>\n      </div>\n      \n      {isPending && <div>🔄 Updating results...</div>}\n      \n      {/* Results update with lower priority */}\n      <div style={{ opacity: isPending ? 0.7 : 1 }}>\n        <p>Filter: {filter}, Sort: {sort}</p>\n        <p>Items: {items.length}</p>\n        <ul>\n          {items.slice(0, 100).map(item => (\n            <li key={item.id}>\n              {item.name} - ${item.price} ({item.category})\n            </li>\n          ))}\n        </ul>\n      </div>\n    </div>\n  );\n}\n\nfunction performExpensiveSearch(query) {\n  const allItems = generateItems(10000);\n  \n  // Simulate expensive search\n  return allItems.filter(item => \n    item.name.toLowerCase().includes(query.toLowerCase()) ||\n    item.category.toLowerCase().includes(query.toLowerCase())\n  );\n}\n\nfunction loadTabContent(tab) {\n  // Simulate expensive content loading\n  const content = {\n    home: 'Welcome to the home page with lots of dynamic content...',\n    about: 'About us page with company information and history...',\n    contact: 'Contact information and form with validation...'\n  };\n  \n  // Simulate delay\n  for (let i = 0; i < 1000000; i++) {\n    Math.random();\n  }\n  \n  return content[tab] || 'Loading...';\n}\n\nfunction generateItems(count) {\n  const categories = ['electronics', 'clothing', 'books', 'home', 'sports'];\n  \n  return Array.from({ length: count }, (_, i) => ({\n    id: i,\n    name: `Item ${i}`,\n    price: Math.floor(Math.random() * 1000) + 10,\n    category: categories[i % categories.length]\n  }));\n}
```

---

### 5. Why does React Strict Mode double render in development?

**Strict Mode intentionally double-renders to help detect side effects and ensure components are pure.**

* **Side effect detection**: Helps find impure functions and side effects
* **Development only**: Only happens in development, not production
* **Pure components**: Ensures render functions don't cause side effects
* **Better debugging**: Makes bugs more obvious during development

```jsx
import React, { useState, useEffect } from 'react';

function StrictModeExample() {\n  const [count, setCount] = useState(0);\n  const [renderCount, setRenderCount] = useState(0);\n  \n  // This will run twice in Strict Mode\n  console.log('Component rendering, count:', count);\n  \n  // ❌ BAD - Side effect in render (Strict Mode will catch this)\n  // document.title = `Count: ${count}`; // Don't do this!\n  \n  // ✅ GOOD - Side effects in useEffect\n  useEffect(() => {\n    document.title = `Count: ${count}`;\n    console.log('useEffect running');\n  }, [count]);\n  \n  // This will also run twice in Strict Mode\n  const handleIncrement = () => {\n    console.log('Button clicked');\n    setCount(prev => prev + 1);\n  };\n  \n  // Track render count (will show double rendering)\n  React.useLayoutEffect(() => {\n    setRenderCount(prev => prev + 1);\n  });\n  \n  return (\n    <div>\n      <h2>Strict Mode Double Rendering</h2>\n      <p>Count: {count}</p>\n      <p>Render count: {renderCount}</p>\n      <button onClick={handleIncrement}>Increment</button>\n      <p>Check console to see double rendering</p>\n    </div>\n  );\n}\n\n// Example of component with side effects (bad)\nfunction ComponentWithSideEffects() {\n  const [items, setItems] = useState([]);\n  \n  // ❌ BAD - Side effect in render\n  // Strict Mode will make this obvious by running twice\n  if (items.length === 0) {\n    // This is a side effect in render - bad!\n    fetch('/api/items')\n      .then(res => res.json())\n      .then(setItems);\n  }\n  \n  console.log('ComponentWithSideEffects rendered');\n  \n  return (\n    <div>\n      <h3>Component with Side Effects (Bad)</h3>\n      <p>Items: {items.length}</p>\n    </div>\n  );\n}\n\n// Fixed version without side effects\nfunction ComponentWithoutSideEffects() {\n  const [items, setItems] = useState([]);\n  \n  // ✅ GOOD - Side effect in useEffect\n  useEffect(() => {\n    fetch('/api/items')\n      .then(res => res.json())\n      .then(setItems)\n      .catch(err => console.error('Failed to fetch items'));\n  }, []);\n  \n  console.log('ComponentWithoutSideEffects rendered');\n  \n  return (\n    <div>\n      <h3>Component without Side Effects (Good)</h3>\n      <p>Items: {items.length}</p>\n    </div>\n  );\n}\n\n// Example showing pure vs impure functions\nfunction PureVsImpureExample() {\n  const [count, setCount] = useState(0);\n  \n  // ❌ IMPURE - Modifies external state\n  let externalCounter = 0;\n  const impureFunction = () => {\n    externalCounter++; // Side effect!\n    return count * 2;\n  };\n  \n  // ✅ PURE - No side effects\n  const pureFunction = (value) => {\n    return value * 2;\n  };\n  \n  // Strict Mode will call these twice, making impure function obvious\n  const impureResult = impureFunction();\n  const pureResult = pureFunction(count);\n  \n  console.log('External counter:', externalCounter); // Will increment twice in Strict Mode\n  \n  return (\n    <div>\n      <h3>Pure vs Impure Functions</h3>\n      <p>Count: {count}</p>\n      <p>Pure result: {pureResult}</p>\n      <p>Impure result: {impureResult}</p>\n      <p>External counter: {externalCounter}</p>\n      <button onClick={() => setCount(count + 1)}>Increment</button>\n    </div>\n  );\n}\n\n// How to enable/disable Strict Mode\nfunction App() {\n  return (\n    <div>\n      <h1>React Strict Mode Examples</h1>\n      \n      {/* Components inside StrictMode will double render */}\n      <React.StrictMode>\n        <StrictModeExample />\n        <ComponentWithSideEffects />\n        <ComponentWithoutSideEffects />\n        <PureVsImpureExample />\n      </React.StrictMode>\n      \n      {/* Components outside StrictMode render normally */}\n      <div>\n        <h2>Outside Strict Mode (Normal Rendering)</h2>\n        <StrictModeExample />\n      </div>\n    </div>\n  );\n}\n\n// Benefits of Strict Mode double rendering:\n// 1. Detects side effects in render functions\n// 2. Ensures components are pure and predictable\n// 3. Helps identify unsafe lifecycle methods\n// 4. Makes bugs more obvious during development\n// 5. Prepares code for concurrent rendering
```

---

### 6. What is code splitting?

**Code splitting breaks your bundle into smaller chunks that load on-demand for better performance.**

* **Smaller bundles**: Load only what's needed initially
* **Lazy loading**: Load components/routes when needed
* **Better performance**: Faster initial page load
* **Dynamic imports**: Use import() for code splitting

```jsx
import React, { Suspense, lazy, useState } from 'react';\n\n// Lazy load components\nconst LazyDashboard = lazy(() => import('./Dashboard'));\nconst LazyProfile = lazy(() => import('./Profile'));\nconst LazySettings = lazy(() => import('./Settings'));\n\n// Route-based code splitting\nfunction CodeSplittingExample() {\n  const [currentView, setCurrentView] = useState('home');\n  \n  const renderView = () => {\n    switch (currentView) {\n      case 'dashboard':\n        return (\n          <Suspense fallback={<div>Loading Dashboard...</div>}>\n            <LazyDashboard />\n          </Suspense>\n        );\n      case 'profile':\n        return (\n          <Suspense fallback={<div>Loading Profile...</div>}>\n            <LazyProfile />\n          </Suspense>\n        );\n      case 'settings':\n        return (\n          <Suspense fallback={<div>Loading Settings...</div>}>\n            <LazySettings />\n          </Suspense>\n        );\n      default:\n        return <div>Welcome to Home Page</div>;\n    }\n  };\n  \n  return (\n    <div>\n      <h2>Code Splitting Example</h2>\n      \n      <nav>\n        <button onClick={() => setCurrentView('home')}>Home</button>\n        <button onClick={() => setCurrentView('dashboard')}>Dashboard</button>\n        <button onClick={() => setCurrentView('profile')}>Profile</button>\n        <button onClick={() => setCurrentView('settings')}>Settings</button>\n      </nav>\n      \n      <div style={{ padding: '20px', border: '1px solid #ccc' }}>\n        {renderView()}\n      </div>\n    </div>\n  );\n}\n\n// Dynamic import with loading states\nfunction DynamicImportExample() {\n  const [component, setComponent] = useState(null);\n  const [loading, setLoading] = useState(false);\n  const [error, setError] = useState(null);\n  \n  const loadComponent = async (componentName) => {\n    setLoading(true);\n    setError(null);\n    \n    try {\n      let ComponentModule;\n      \n      // Dynamic import based on component name\n      switch (componentName) {\n        case 'chart':\n          ComponentModule = await import('./ChartComponent');\n          break;\n        case 'table':\n          ComponentModule = await import('./TableComponent');\n          break;\n        case 'form':\n          ComponentModule = await import('./FormComponent');\n          break;\n        default:\n          throw new Error('Unknown component');\n      }\n      \n      setComponent(() => ComponentModule.default);\n    } catch (err) {\n      setError(err.message);\n    } finally {\n      setLoading(false);\n    }\n  };\n  \n  return (\n    <div>\n      <h3>Dynamic Import Example</h3>\n      \n      <div>\n        <button onClick={() => loadComponent('chart')}>Load Chart</button>\n        <button onClick={() => loadComponent('table')}>Load Table</button>\n        <button onClick={() => loadComponent('form')}>Load Form</button>\n      </div>\n      \n      {loading && <div>Loading component...</div>}\n      {error && <div>Error: {error}</div>}\n      {component && React.createElement(component)}\n    </div>\n  );\n}\n\n// Feature-based code splitting\nfunction FeatureBasedSplitting() {\n  const [features, setFeatures] = useState(new Set());\n  \n  const loadFeature = async (featureName) => {\n    if (features.has(featureName)) return;\n    \n    try {\n      // Load feature module dynamically\n      const featureModule = await import(`./features/${featureName}`);\n      \n      // Initialize feature\n      if (featureModule.init) {\n        featureModule.init();\n      }\n      \n      setFeatures(prev => new Set([...prev, featureName]));\n    } catch (error) {\n      console.error(`Failed to load feature: ${featureName}`, error);\n    }\n  };\n  \n  return (\n    <div>\n      <h3>Feature-based Code Splitting</h3>\n      \n      <div>\n        <button onClick={() => loadFeature('analytics')}>\n          Load Analytics {features.has('analytics') && '✓'}\n        </button>\n        <button onClick={() => loadFeature('reporting')}>\n          Load Reporting {features.has('reporting') && '✓'}\n        </button>\n        <button onClick={() => loadFeature('admin')}>\n          Load Admin {features.has('admin') && '✓'}\n        </button>\n      </div>\n      \n      <div>\n        <p>Loaded features: {Array.from(features).join(', ') || 'None'}</p>\n      </div>\n    </div>\n  );\n}\n\n// Library code splitting\nfunction LibraryCodeSplitting() {\n  const [chart, setChart] = useState(null);\n  const [data] = useState([1, 2, 3, 4, 5]);\n  \n  const loadChart = async () => {\n    // Only load heavy chart library when needed\n    const { Chart } = await import('chart.js/auto');\n    setChart(Chart);\n  };\n  \n  return (\n    <div>\n      <h3>Library Code Splitting</h3>\n      \n      {!chart ? (\n        <button onClick={loadChart}>Load Chart Library</button>\n      ) : (\n        <div>\n          <p>Chart library loaded!</p>\n          <canvas id=\"myChart\" width=\"400\" height=\"200\"></canvas>\n        </div>\n      )}\n    </div>\n  );\n}\n\n// Complete code splitting app\nfunction CodeSplittingApp() {\n  return (\n    <div>\n      <h1>Code Splitting Examples</h1>\n      \n      <CodeSplittingExample />\n      <hr />\n      <DynamicImportExample />\n      <hr />\n      <FeatureBasedSplitting />\n      <hr />\n      <LibraryCodeSplitting />\n    </div>\n  );\n}\n\n// Webpack bundle analysis\n// Run: npm run build -- --analyze\n// Or use webpack-bundle-analyzer\n\nexport default CodeSplittingApp;
```

---

### 7. What is lazy loading?

**Lazy loading defers loading of components until they're actually needed, improving initial load performance.**

* **On-demand loading**: Load components when needed, not upfront
* **Better performance**: Faster initial page load
* **React.lazy**: Built-in lazy loading for components
* **Suspense**: Handles loading states for lazy components

```jsx
import React, { Suspense, lazy, useState, useEffect } from 'react';\n\n// Basic lazy loading\nconst LazyComponent = lazy(() => import('./HeavyComponent'));\n\nfunction BasicLazyLoading() {\n  const [showComponent, setShowComponent] = useState(false);\n  \n  return (\n    <div>\n      <h2>Basic Lazy Loading</h2>\n      \n      <button onClick={() => setShowComponent(!showComponent)}>\n        {showComponent ? 'Hide' : 'Show'} Heavy Component\n      </button>\n      \n      {showComponent && (\n        <Suspense fallback={<div>Loading heavy component...</div>}>\n          <LazyComponent />\n        </Suspense>\n      )}\n    </div>\n  );\n}\n\n// Lazy loading with error boundary\nclass LazyErrorBoundary extends React.Component {\n  constructor(props) {\n    super(props);\n    this.state = { hasError: false };\n  }\n  \n  static getDerivedStateFromError(error) {\n    return { hasError: true };\n  }\n  \n  componentDidCatch(error, errorInfo) {\n    console.error('Lazy loading error:', error, errorInfo);\n  }\n  \n  render() {\n    if (this.state.hasError) {\n      return <div>Failed to load component. Please try again.</div>;\n    }\n    \n    return this.props.children;\n  }\n}\n\n// Lazy loading with retry\nfunction LazyWithRetry() {\n  const [Component, setComponent] = useState(null);\n  const [loading, setLoading] = useState(false);\n  const [error, setError] = useState(null);\n  \n  const loadComponent = async () => {\n    setLoading(true);\n    setError(null);\n    \n    try {\n      const module = await import('./HeavyComponent');\n      setComponent(() => module.default);\n    } catch (err) {\n      setError(err);\n    } finally {\n      setLoading(false);\n    }\n  };\n  \n  const retry = () => {\n    setError(null);\n    loadComponent();\n  };\n  \n  return (\n    <div>\n      <h3>Lazy Loading with Retry</h3>\n      \n      {!Component && !loading && !error && (\n        <button onClick={loadComponent}>Load Component</button>\n      )}\n      \n      {loading && <div>Loading...</div>}\n      \n      {error && (\n        <div>\n          <p>Failed to load component</p>\n          <button onClick={retry}>Retry</button>\n        </div>\n      )}\n      \n      {Component && <Component />}\n    </div>\n  );\n}\n\n// Intersection Observer lazy loading\nfunction IntersectionLazyLoading() {\n  const [isVisible, setIsVisible] = useState(false);\n  const [hasLoaded, setHasLoaded] = useState(false);\n  const ref = React.useRef();\n  \n  useEffect(() => {\n    const observer = new IntersectionObserver(\n      ([entry]) => {\n        if (entry.isIntersecting && !hasLoaded) {\n          setIsVisible(true);\n          setHasLoaded(true);\n        }\n      },\n      { threshold: 0.1 }\n    );\n    \n    if (ref.current) {\n      observer.observe(ref.current);\n    }\n    \n    return () => observer.disconnect();\n  }, [hasLoaded]);\n  \n  return (\n    <div>\n      <h3>Intersection Observer Lazy Loading</h3>\n      <div style={{ height: '1000px', background: '#f0f0f0' }}>\n        <p>Scroll down to load component...</p>\n      </div>\n      \n      <div ref={ref} style={{ minHeight: '200px', border: '2px dashed #ccc' }}>\n        {isVisible ? (\n          <Suspense fallback={<div>Loading when visible...</div>}>\n            <LazyComponent />\n          </Suspense>\n        ) : (\n          <div>Component will load when visible</div>\n        )}\n      </div>\n    </div>\n  );\n}\n\n// Image lazy loading\nfunction ImageLazyLoading() {\n  const [images, setImages] = useState([\n    { id: 1, src: 'https://picsum.photos/300/200?random=1', loaded: false },\n    { id: 2, src: 'https://picsum.photos/300/200?random=2', loaded: false },\n    { id: 3, src: 'https://picsum.photos/300/200?random=3', loaded: false },\n  ]);\n  \n  const LazyImage = ({ src, alt, id }) => {\n    const [isVisible, setIsVisible] = useState(false);\n    const [isLoaded, setIsLoaded] = useState(false);\n    const imgRef = React.useRef();\n    \n    useEffect(() => {\n      const observer = new IntersectionObserver(\n        ([entry]) => {\n          if (entry.isIntersecting) {\n            setIsVisible(true);\n            observer.disconnect();\n          }\n        },\n        { threshold: 0.1 }\n      );\n      \n      if (imgRef.current) {\n        observer.observe(imgRef.current);\n      }\n      \n      return () => observer.disconnect();\n    }, []);\n    \n    return (\n      <div ref={imgRef} style={{ minHeight: '200px', background: '#f5f5f5' }}>\n        {isVisible ? (\n          <img\n            src={src}\n            alt={alt}\n            onLoad={() => setIsLoaded(true)}\n            style={{\n              opacity: isLoaded ? 1 : 0,\n              transition: 'opacity 0.3s'\n            }}\n          />\n        ) : (\n          <div>Image will load when visible</div>\n        )}\n      </div>\n    );\n  };\n  \n  return (\n    <div>\n      <h3>Image Lazy Loading</h3>\n      <div style={{ height: '500px', overflow: 'auto' }}>\n        {images.map(image => (\n          <LazyImage\n            key={image.id}\n            id={image.id}\n            src={image.src}\n            alt={`Image ${image.id}`}\n          />\n        ))}\n      </div>\n    </div>\n  );\n}\n\n// Route-based lazy loading\nconst LazyHome = lazy(() => import('./pages/Home'));\nconst LazyAbout = lazy(() => import('./pages/About'));\nconst LazyContact = lazy(() => import('./pages/Contact'));\n\nfunction RouteLazyLoading() {\n  const [currentRoute, setCurrentRoute] = useState('home');\n  \n  const renderRoute = () => {\n    const routes = {\n      home: LazyHome,\n      about: LazyAbout,\n      contact: LazyContact\n    };\n    \n    const Component = routes[currentRoute];\n    \n    return (\n      <LazyErrorBoundary>\n        <Suspense fallback={<div>Loading page...</div>}>\n          <Component />\n        </Suspense>\n      </LazyErrorBoundary>\n    );\n  };\n  \n  return (\n    <div>\n      <h3>Route-based Lazy Loading</h3>\n      \n      <nav>\n        <button onClick={() => setCurrentRoute('home')}>Home</button>\n        <button onClick={() => setCurrentRoute('about')}>About</button>\n        <button onClick={() => setCurrentRoute('contact')}>Contact</button>\n      </nav>\n      \n      <div style={{ padding: '20px', border: '1px solid #ddd' }}>\n        {renderRoute()}\n      </div>\n    </div>\n  );\n}\n\n// Complete lazy loading app\nfunction LazyLoadingApp() {\n  return (\n    <div>\n      <h1>Lazy Loading Examples</h1>\n      \n      <BasicLazyLoading />\n      <hr />\n      <LazyWithRetry />\n      <hr />\n      <IntersectionLazyLoading />\n      <hr />\n      <ImageLazyLoading />\n      <hr />\n      <RouteLazyLoading />\n    </div>\n  );\n}\n\nexport default LazyLoadingApp;
```

---

### 8. How does `React.memo` work internally?

**React.memo is a higher-order component that memoizes the result and only re-renders when props change.**

* **Shallow comparison**: Compares props using Object.is()
* **Memoization**: Caches the rendered result
* **Performance optimization**: Prevents unnecessary re-renders
* **Custom comparison**: Can provide custom comparison function

```jsx
import React, { useState, memo, useCallback, useMemo } from 'react';\n\n// Basic React.memo usage\nconst BasicMemoComponent = memo(function BasicMemoComponent({ name, count }) {\n  console.log('BasicMemoComponent rendered');\n  \n  return (\n    <div>\n      <h3>Basic Memo Component</h3>\n      <p>Name: {name}</p>\n      <p>Count: {count}</p>\n    </div>\n  );\n});\n\n// React.memo with custom comparison\nconst CustomMemoComponent = memo(\n  function CustomMemoComponent({ user, settings }) {\n    console.log('CustomMemoComponent rendered');\n    \n    return (\n      <div>\n        <h3>Custom Memo Component</h3>\n        <p>User: {user.name}</p>\n        <p>Theme: {settings.theme}</p>\n      </div>\n    );\n  },\n  // Custom comparison function\n  (prevProps, nextProps) => {\n    // Return true if props are equal (skip re-render)\n    // Return false if props are different (re-render)\n    return (\n      prevProps.user.name === nextProps.user.name &&\n      prevProps.settings.theme === nextProps.settings.theme\n    );\n  }\n);\n\n// Component that shows memo behavior\nfunction MemoInternalExample() {\n  const [count, setCount] = useState(0);\n  const [name, setName] = useState('John');\n  const [unrelatedState, setUnrelatedState] = useState(0);\n  \n  const user = { name, id: 1 };\n  const settings = { theme: 'dark', lang: 'en' };\n  \n  console.log('Parent component rendered');\n  \n  return (\n    <div>\n      <h2>React.memo Internal Behavior</h2>\n      \n      <div>\n        <p>Count: {count}</p>\n        <p>Name: {name}</p>\n        <p>Unrelated: {unrelatedState}</p>\n        \n        <button onClick={() => setCount(count + 1)}>Update Count</button>\n        <button onClick={() => setName(name === 'John' ? 'Jane' : 'John')}>Toggle Name</button>\n        <button onClick={() => setUnrelatedState(unrelatedState + 1)}>Update Unrelated</button>\n      </div>\n      \n      {/* This will re-render when count or name changes */}\n      <BasicMemoComponent name={name} count={count} />\n      \n      {/* This will re-render based on custom comparison */}\n      <CustomMemoComponent user={user} settings={settings} />\n    </div>\n  );\n}\n\n// Demonstrating memo with object props\nfunction MemoWithObjectProps() {\n  const [count, setCount] = useState(0);\n  const [name, setName] = useState('John');\n  \n  // ❌ BAD - New object every render\n  const badUser = { name, id: 1 };\n  \n  // ✅ GOOD - Memoized object\n  const goodUser = useMemo(() => ({ name, id: 1 }), [name]);\n  \n  // ❌ BAD - New function every render\n  const badCallback = () => console.log('clicked');\n  \n  // ✅ GOOD - Memoized callback\n  const goodCallback = useCallback(() => console.log('clicked'), []);\n  \n  return (\n    <div>\n      <h3>Memo with Object Props</h3>\n      \n      <p>Count: {count}</p>\n      <button onClick={() => setCount(count + 1)}>Update Count</button>\n      \n      {/* Will re-render every time due to new object */}\n      <MemoChildWithBadProps user={badUser} onClick={badCallback} />\n      \n      {/* Will only re-render when user actually changes */}\n      <MemoChildWithGoodProps user={goodUser} onClick={goodCallback} />\n    </div>\n  );\n}\n\nconst MemoChildWithBadProps = memo(({ user, onClick }) => {\n  console.log('MemoChildWithBadProps rendered (always re-renders)');\n  \n  return (\n    <div style={{ border: '1px solid red', padding: '10px', margin: '5px' }}>\n      <p>Bad Props Child: {user.name}</p>\n      <button onClick={onClick}>Click</button>\n    </div>\n  );\n});\n\nconst MemoChildWithGoodProps = memo(({ user, onClick }) => {\n  console.log('MemoChildWithGoodProps rendered (only when needed)');\n  \n  return (\n    <div style={{ border: '1px solid green', padding: '10px', margin: '5px' }}>\n      <p>Good Props Child: {user.name}</p>\n      <button onClick={onClick}>Click</button>\n    </div>\n  );\n});\n\n// How React.memo works internally (simplified)\nfunction createMemoComponent(Component, compare) {\n  function MemoComponent(props) {\n    const ref = React.useRef();\n    \n    // First render or no previous props\n    if (!ref.current) {\n      ref.current = {\n        props,\n        result: React.createElement(Component, props)\n      };\n      return ref.current.result;\n    }\n    \n    // Compare props\n    const areEqual = compare \n      ? compare(ref.current.props, props)\n      : shallowEqual(ref.current.props, props);\n    \n    // If props are equal, return cached result\n    if (areEqual) {\n      return ref.current.result;\n    }\n    \n    // Props changed, re-render and cache\n    ref.current.props = props;\n    ref.current.result = React.createElement(Component, props);\n    return ref.current.result;\n  }\n  \n  return MemoComponent;\n}\n\n// Shallow comparison (simplified version of React's implementation)\nfunction shallowEqual(objA, objB) {\n  if (Object.is(objA, objB)) {\n    return true;\n  }\n  \n  if (typeof objA !== 'object' || objA === null ||\n      typeof objB !== 'object' || objB === null) {\n    return false;\n  }\n  \n  const keysA = Object.keys(objA);\n  const keysB = Object.keys(objB);\n  \n  if (keysA.length !== keysB.length) {\n    return false;\n  }\n  \n  for (let i = 0; i < keysA.length; i++) {\n    const key = keysA[i];\n    if (!Object.prototype.hasOwnProperty.call(objB, key) ||\n        !Object.is(objA[key], objB[key])) {\n      return false;\n    }\n  }\n  \n  return true;\n}\n\n// Performance comparison\nfunction PerformanceComparison() {\n  const [count, setCount] = useState(0);\n  const [renderCount, setRenderCount] = useState(0);\n  \n  React.useEffect(() => {\n    setRenderCount(prev => prev + 1);\n  });\n  \n  return (\n    <div>\n      <h3>Performance Comparison</h3>\n      <p>Parent renders: {renderCount}</p>\n      <p>Count: {count}</p>\n      \n      <button onClick={() => setCount(count + 1)}>Update Count</button>\n      \n      {/* Regular component - always re-renders */}\n      <RegularChild name=\"Regular\" />\n      \n      {/* Memoized component - only re-renders when props change */}\n      <MemoizedChild name=\"Memoized\" />\n    </div>\n  );\n}\n\nfunction RegularChild({ name }) {\n  console.log(`${name} child rendered`);\n  return <div>{name} Child Component</div>;\n}\n\nconst MemoizedChild = memo(function MemoizedChild({ name }) {\n  console.log(`${name} child rendered`);\n  return <div>{name} Child Component</div>;\n});\n\n// Complete React.memo demo\nfunction ReactMemoApp() {\n  return (\n    <div>\n      <h1>React.memo Internal Behavior</h1>\n      \n      <MemoInternalExample />\n      <hr />\n      <MemoWithObjectProps />\n      <hr />\n      <PerformanceComparison />\n    </div>\n  );\n}\n\nexport default ReactMemoApp;
```

---

### 9. How do you prevent unnecessary re-renders?

**Use React.memo, useMemo, useCallback, and proper state structure to prevent unnecessary re-renders.**

* **React.memo**: Memoize components to skip re-renders when props unchanged
* **useMemo**: Memoize expensive calculations
* **useCallback**: Memoize functions to maintain reference equality
* **State structure**: Keep state minimal and avoid derived state

```jsx
import { useState, memo, useMemo, useCallback } from 'react';

function PreventReRendersExample() {
  const [count, setCount] = useState(0);
  const [name, setName] = useState('John');
  const [items, setItems] = useState(['A', 'B', 'C']);
  
  // ✅ Memoized expensive calculation
  const expensiveValue = useMemo(() => {
    console.log('Computing expensive value...');
    return items.reduce((sum, item) => sum + item.length, 0);
  }, [items]);
  
  // ✅ Memoized callback
  const handleItemClick = useCallback((item) => {
    console.log('Item clicked:', item);
  }, []);
  
  // ✅ Memoized object to prevent child re-renders
  const userConfig = useMemo(() => ({
    name,
    preferences: { theme: 'dark' }
  }), [name]);
  
  return (
    <div>
      <h2>Prevent Unnecessary Re-renders</h2>
      
      <p>Count: {count}</p>
      <p>Expensive Value: {expensiveValue}</p>
      
      <button onClick={() => setCount(count + 1)}>Increment Count</button>
      <input value={name} onChange={(e) => setName(e.target.value)} />
      
      {/* These components won't re-render unnecessarily */}
      <MemoizedChild config={userConfig} onItemClick={handleItemClick} />
      <ItemList items={items} onItemClick={handleItemClick} />
    </div>
  );
}

// ✅ Memoized component
const MemoizedChild = memo(({ config, onItemClick }) => {
  console.log('MemoizedChild rendered');
  
  return (
    <div>
      <h3>User: {config.name}</h3>
      <p>Theme: {config.preferences.theme}</p>
      <button onClick={() => onItemClick('test')}>Test Click</button>
    </div>
  );
});

const ItemList = memo(({ items, onItemClick }) => {
  console.log('ItemList rendered');
  
  return (
    <ul>
      {items.map(item => (
        <li key={item} onClick={() => onItemClick(item)}>
          {item}
        </li>
      ))}
    </ul>
  );
});

// ❌ Common mistakes that cause re-renders
function CommonMistakes() {
  const [count, setCount] = useState(0);
  
  return (
    <div>
      <h3>Common Re-render Mistakes</h3>
      <p>Count: {count}</p>
      <button onClick={() => setCount(count + 1)}>Increment</button>
      
      {/* ❌ BAD - New object every render */}
      <BadChild config={{ theme: 'dark' }} />
      
      {/* ❌ BAD - Inline function every render */}
      <BadChild onClick={() => console.log('clicked')} />
      
      {/* ❌ BAD - New array every render */}
      <BadChild items={['A', 'B', 'C']} />
    </div>
  );
}

const BadChild = memo(({ config, onClick, items }) => {
  console.log('BadChild rendered (always re-renders)');
  return <div>Bad Child Component</div>;
});
```

---

### 10. Why do inline functions cause re-renders?

**Inline functions create new function references on every render, breaking memoization and causing child re-renders.**

* **New reference**: Inline functions create new references each render
* **Breaks memoization**: React.memo sees different function props
* **Performance impact**: Causes unnecessary child re-renders
* **Solution**: Use useCallback or define functions outside render

```jsx
import { useState, memo, useCallback } from 'react';

function InlineFunctionExample() {
  const [count, setCount] = useState(0);
  const [name, setName] = useState('John');
  
  console.log('Parent rendered');
  
  // ✅ GOOD - Memoized callback
  const handleGoodClick = useCallback((id) => {
    console.log('Good click:', id);
  }, []);
  
  // ✅ GOOD - Stable function reference
  const handleStableClick = (id) => {
    console.log('Stable click:', id);
  };
  
  return (
    <div>
      <h2>Inline Functions and Re-renders</h2>
      
      <p>Count: {count}</p>
      <p>Name: {name}</p>
      
      <button onClick={() => setCount(count + 1)}>Increment</button>
      <input value={name} onChange={(e) => setName(e.target.value)} />
      
      {/* ❌ BAD - Inline function causes re-render */}
      <MemoChild 
        name="Bad Child"
        onClick={() => console.log('inline function')} // New function every render
      />
      
      {/* ✅ GOOD - Memoized callback */}
      <MemoChild 
        name="Good Child"
        onClick={handleGoodClick}
      />
      
      {/* ✅ GOOD - Stable reference (if function doesn't use state) */}
      <MemoChild 
        name="Stable Child"
        onClick={handleStableClick}
      />
    </div>
  );
}

const MemoChild = memo(({ name, onClick }) => {
  console.log(`${name} rendered`);
  
  return (
    <div style={{ border: '1px solid #ccc', margin: '5px', padding: '10px' }}>
      <h4>{name}</h4>
      <button onClick={() => onClick('test')}>Click Me</button>
    </div>
  );
});

// Demonstrating the problem with inline functions
function InlineFunctionProblem() {
  const [parentState, setParentState] = useState(0);
  const [childRenders, setChildRenders] = useState(0);
  
  return (
    <div>
      <h3>Inline Function Problem Demo</h3>
      
      <p>Parent State: {parentState}</p>
      <p>Child Renders: {childRenders}</p>
      
      <button onClick={() => setParentState(parentState + 1)}>
        Update Parent (Watch child re-renders)
      </button>
      
      {/* This child will re-render every time parent updates */}
      <ProblematicChild 
        onRender={() => setChildRenders(prev => prev + 1)}
        onClick={() => console.log('This inline function causes re-renders')}
      />
    </div>
  );
}

const ProblematicChild = memo(({ onRender, onClick }) => {
  React.useEffect(() => {
    onRender();
  });
  
  return (
    <div style={{ background: '#ffe6e6', padding: '10px' }}>
      <p>I re-render every time parent updates!</p>
      <button onClick={onClick}>Click</button>
    </div>
  );
});

// Solution with useCallback
function SolutionWithCallback() {
  const [parentState, setParentState] = useState(0);
  const [childRenders, setChildRenders] = useState(0);
  
  // ✅ Memoized callbacks
  const handleRender = useCallback(() => {
    setChildRenders(prev => prev + 1);
  }, []);
  
  const handleClick = useCallback(() => {
    console.log('Memoized function - no unnecessary re-renders');
  }, []);
  
  return (
    <div>
      <h3>Solution with useCallback</h3>
      
      <p>Parent State: {parentState}</p>
      <p>Child Renders: {childRenders}</p>
      
      <button onClick={() => setParentState(parentState + 1)}>
        Update Parent (Child won't re-render)
      </button>
      
      {/* This child will only re-render when actually needed */}
      <OptimizedChild 
        onRender={handleRender}
        onClick={handleClick}
      />
    </div>
  );
}

const OptimizedChild = memo(({ onRender, onClick }) => {
  React.useEffect(() => {
    onRender();
  });
  
  return (
    <div style={{ background: '#e6ffe6', padding: '10px' }}>
      <p>I only re-render when props actually change!</p>
      <button onClick={onClick}>Click</button>
    </div>
  );
});
```

---

### 11. What is render thrashing?

**Render thrashing occurs when components re-render excessively due to rapid state changes or poor optimization.**

* **Excessive re-renders**: Components render more than necessary
* **Performance degradation**: UI becomes slow and unresponsive
* **Common causes**: Rapid state updates, missing memoization, unstable dependencies
* **Solutions**: Debouncing, batching, proper memoization

```jsx
import { useState, useEffect, useMemo, useCallback, useTransition } from 'react';

// ❌ Example of render thrashing
function RenderThrashingExample() {
  const [query, setQuery] = useState('');
  const [results, setResults] = useState([]);
  const [renderCount, setRenderCount] = useState(0);
  
  // This causes render thrashing - runs on every keystroke
  useEffect(() => {
    setRenderCount(prev => prev + 1);
    
    // Expensive operation on every character typed
    const filtered = expensiveSearch(query);
    setResults(filtered);
  }, [query]); // Runs on every query change
  
  console.log('Component rendered:', renderCount);
  
  return (
    <div>
      <h2>❌ Render Thrashing Example</h2>
      <p>Renders: {renderCount}</p>
      
      {/* Every keystroke causes expensive re-render */}
      <input 
        value={query}
        onChange={(e) => setQuery(e.target.value)}
        placeholder="Type to see thrashing..."
      />
      
      <div>
        <p>Results: {results.length}</p>
        <ul>
          {results.slice(0, 10).map(result => (
            <li key={result.id}>{result.name}</li>
          ))}
        </ul>
      </div>
    </div>
  );
}

// ✅ Solution 1: Debouncing
function DebouncedSolution() {
  const [query, setQuery] = useState('');
  const [debouncedQuery, setDebouncedQuery] = useState('');
  const [results, setResults] = useState([]);
  const [renderCount, setRenderCount] = useState(0);
  
  // Debounce the query
  useEffect(() => {
    const timer = setTimeout(() => {
      setDebouncedQuery(query);
    }, 300);
    
    return () => clearTimeout(timer);
  }, [query]);
  
  // Only search when debounced query changes
  useEffect(() => {
    setRenderCount(prev => prev + 1);
    
    if (debouncedQuery) {
      const filtered = expensiveSearch(debouncedQuery);
      setResults(filtered);
    }
  }, [debouncedQuery]);
  
  return (
    <div>
      <h2>✅ Debounced Solution</h2>
      <p>Renders: {renderCount}</p>
      
      <input 
        value={query}
        onChange={(e) => setQuery(e.target.value)}
        placeholder="Type to see debouncing..."
      />
      
      <div>
        <p>Searching for: "{debouncedQuery}"</p>
        <p>Results: {results.length}</p>
        <ul>
          {results.slice(0, 10).map(result => (
            <li key={result.id}>{result.name}</li>
          ))}
        </ul>
      </div>
    </div>
  );
}

// ✅ Solution 2: useTransition for non-urgent updates
function TransitionSolution() {
  const [query, setQuery] = useState('');
  const [results, setResults] = useState([]);
  const [isPending, startTransition] = useTransition();
  
  const handleSearch = (value) => {
    setQuery(value); // Urgent update - immediate
    
    // Non-urgent update - won't block UI
    startTransition(() => {
      const filtered = expensiveSearch(value);
      setResults(filtered);
    });
  };
  
  return (
    <div>
      <h2>✅ Transition Solution</h2>
      
      <input 
        value={query}
        onChange={(e) => handleSearch(e.target.value)}
        placeholder="Type to see smooth updates..."
      />
      
      {isPending && <div>🔍 Searching...</div>}
      
      <div style={{ opacity: isPending ? 0.7 : 1 }}>
        <p>Results: {results.length}</p>
        <ul>
          {results.slice(0, 10).map(result => (
            <li key={result.id}>{result.name}</li>
          ))}
        </ul>
      </div>
    </div>
  );
}

// ✅ Solution 3: Memoization to prevent thrashing
function MemoizedSolution() {
  const [query, setQuery] = useState('');
  const [filter, setFilter] = useState('all');
  const [renderCount, setRenderCount] = useState(0);
  
  // Memoized expensive calculation
  const results = useMemo(() => {
    setRenderCount(prev => prev + 1);
    console.log('Expensive search running...');
    
    let filtered = expensiveSearch(query);
    
    if (filter !== 'all') {
      filtered = filtered.filter(item => item.category === filter);
    }
    
    return filtered;
  }, [query, filter]); // Only recalculate when these change
  
  return (
    <div>
      <h2>✅ Memoized Solution</h2>
      <p>Expensive calculations: {renderCount}</p>
      
      <input 
        value={query}
        onChange={(e) => setQuery(e.target.value)}
        placeholder="Search..."
      />
      
      <select value={filter} onChange={(e) => setFilter(e.target.value)}>
        <option value="all">All</option>
        <option value="electronics">Electronics</option>
        <option value="books">Books</option>
      </select>
      
      <div>
        <p>Results: {results.length}</p>
        <ul>
          {results.slice(0, 10).map(result => (
            <li key={result.id}>{result.name} ({result.category})</li>
          ))}
        </ul>
      </div>
    </div>
  );
}

// Simulate expensive search operation
function expensiveSearch(query) {
  const allItems = Array.from({ length: 10000 }, (_, i) => ({
    id: i,
    name: `Item ${i}`,
    category: ['electronics', 'books', 'clothing'][i % 3]
  }));
  
  // Simulate expensive filtering
  return allItems.filter(item => 
    item.name.toLowerCase().includes(query.toLowerCase())
  );
}
```

---

### 12. How do keys affect reconciliation?

**Keys help React identify which items have changed, moved, or been removed, enabling efficient reconciliation.**

* **Element identity**: Keys give React a way to track elements across renders
* **Efficient updates**: React can reuse DOM nodes instead of recreating them
* **Preserve state**: Component state is preserved when elements move
* **Performance**: Reduces DOM manipulation and improves rendering speed

```jsx
import { useState } from 'react';

function KeysReconciliationExample() {
  const [items, setItems] = useState([
    { id: 1, name: 'Apple', color: 'red' },
    { id: 2, name: 'Banana', color: 'yellow' },
    { id: 3, name: 'Cherry', color: 'red' }
  ]);
  
  const addItem = () => {
    const newItem = {
      id: Date.now(),
      name: `Item ${items.length + 1}`,
      color: ['red', 'blue', 'green'][Math.floor(Math.random() * 3)]
    };
    setItems([newItem, ...items]); // Add to beginning
  };
  
  const removeItem = (id) => {
    setItems(items.filter(item => item.id !== id));
  };
  
  const shuffleItems = () => {
    setItems([...items].sort(() => Math.random() - 0.5));
  };
  
  return (
    <div>
      <h2>Keys and Reconciliation</h2>
      
      <div>
        <button onClick={addItem}>Add Item</button>
        <button onClick={shuffleItems}>Shuffle Items</button>
      </div>
      
      <div style={{ display: 'flex', gap: '20px' }}>
        {/* ❌ BAD - Using index as key */}
        <div>
          <h3>❌ Bad Keys (Index)</h3>
          <ul>
            {items.map((item, index) => (
              <ItemWithState 
                key={index} // BAD - index as key
                item={item}
                onRemove={removeItem}
              />
            ))}
          </ul>
        </div>
        
        {/* ✅ GOOD - Using stable unique keys */}
        <div>
          <h3>✅ Good Keys (ID)</h3>
          <ul>
            {items.map(item => (
              <ItemWithState 
                key={item.id} // GOOD - stable unique key
                item={item}
                onRemove={removeItem}
              />
            ))}
          </ul>
        </div>
      </div>
    </div>
  );
}

// Component with internal state to demonstrate key behavior
function ItemWithState({ item, onRemove }) {
  const [count, setCount] = useState(0);
  const [focused, setFocused] = useState(false);
  
  console.log(`ItemWithState rendered for ${item.name}`);
  
  return (
    <li style={{ 
      border: '1px solid #ccc', 
      margin: '5px', 
      padding: '10px',
      background: focused ? '#e6f3ff' : 'white'
    }}>
      <div>
        <strong>{item.name}</strong> ({item.color})
      </div>
      <div>
        <span>Count: {count}</span>
        <button onClick={() => setCount(count + 1)}>+</button>
        <button onClick={() => setFocused(!focused)}>
          {focused ? 'Unfocus' : 'Focus'}
        </button>
        <button onClick={() => onRemove(item.id)}>Remove</button>
      </div>
    </li>
  );
}

// Demonstrating reconciliation with different key strategies
function ReconciliationComparison() {
  const [list, setList] = useState(['A', 'B', 'C']);
  
  const reverseList = () => {
    setList([...list].reverse());
  };
  
  const addToBeginning = () => {
    setList(['NEW', ...list]);
  };
  
  return (
    <div>
      <h3>Reconciliation Comparison</h3>
      
      <div>
        <button onClick={reverseList}>Reverse List</button>
        <button onClick={addToBeginning}>Add to Beginning</button>
      </div>
      
      <div style={{ display: 'flex', gap: '20px' }}>
        {/* Without keys - React recreates all elements */}
        <div>
          <h4>No Keys (Inefficient)</h4>
          <ul>
            {list.map(item => (
              <ExpensiveItem item={item} /> // No key
            ))}
          </ul>
        </div>
        
        {/* With index keys - Problems when order changes */}
        <div>
          <h4>Index Keys (Problematic)</h4>
          <ul>
            {list.map((item, index) => (
              <ExpensiveItem key={index} item={item} />
            ))}
          </ul>
        </div>
        
        {/* With stable keys - Efficient reconciliation */}
        <div>
          <h4>Stable Keys (Efficient)</h4>
          <ul>
            {list.map(item => (
              <ExpensiveItem key={item} item={item} />
            ))}
          </ul>
        </div>
      </div>
    </div>
  );
}

function ExpensiveItem({ item }) {
  const [renderTime] = useState(Date.now());
  
  // Simulate expensive rendering
  React.useEffect(() => {
    console.log(`ExpensiveItem ${item} mounted at ${renderTime}`);
    
    return () => {
      console.log(`ExpensiveItem ${item} unmounted`);
    };
  }, []);
  
  return (
    <li style={{ padding: '5px', border: '1px solid #ddd' }}>
      {item} (mounted: {new Date(renderTime).toLocaleTimeString()})
    </li>
  );
}
```

---

### 13. What happens if keys are not stable?

**Unstable keys cause React to unnecessarily recreate components, losing state and degrading performance.**

* **Component recreation**: React treats elements as different when keys change
* **State loss**: Component state is lost when keys are unstable
* **Performance impact**: Unnecessary DOM creation and destruction
* **Common mistake**: Using array index or Math.random() as keys

```jsx
import { useState } from 'react';

function UnstableKeysExample() {
  const [items, setItems] = useState([
    { name: 'Apple', category: 'fruit' },
    { name: 'Carrot', category: 'vegetable' },
    { name: 'Banana', category: 'fruit' }
  ]);
  
  const [keyStrategy, setKeyStrategy] = useState('stable');
  
  const addItem = () => {
    const newItem = {
      name: `Item ${items.length + 1}`,
      category: Math.random() > 0.5 ? 'fruit' : 'vegetable'
    };
    setItems([newItem, ...items]);
  };
  
  const removeFirst = () => {
    setItems(items.slice(1));
  };
  
  const shuffleItems = () => {
    setItems([...items].sort(() => Math.random() - 0.5));
  };
  
  const getKey = (item, index) => {
    switch (keyStrategy) {
      case 'random':
        return Math.random(); // ❌ VERY BAD - always unstable
      case 'index':
        return index; // ❌ BAD - unstable when order changes
      case 'name':
        return item.name; // ✅ GOOD - stable and unique
      default:
        return `${item.name}-${item.category}`; // ✅ BEST - stable and unique
    }
  };
  
  return (
    <div>
      <h2>Unstable Keys Problems</h2>
      
      <div>
        <label>
          Key Strategy:
          <select value={keyStrategy} onChange={(e) => setKeyStrategy(e.target.value)}>
            <option value="stable">Stable (name + category)</option>
            <option value="name">Name only</option>
            <option value="index">Index</option>
            <option value="random">Random (Very Bad)</option>
          </select>
        </label>
      </div>
      
      <div>
        <button onClick={addItem}>Add Item</button>
        <button onClick={removeFirst}>Remove First</button>
        <button onClick={shuffleItems}>Shuffle</button>
      </div>
      
      <div>
        <h3>Items with {keyStrategy} keys:</h3>
        <ul>
          {items.map((item, index) => (
            <StatefulItem 
              key={getKey(item, index)}
              item={item}
              keyStrategy={keyStrategy}
            />
          ))}
        </ul>
      </div>
    </div>
  );
}

// Component with state to demonstrate key stability issues
function StatefulItem({ item, keyStrategy }) {
  const [count, setCount] = useState(0);
  const [inputValue, setInputValue] = useState('');
  const [mountTime] = useState(Date.now());
  
  console.log(`StatefulItem ${item.name} rendered with ${keyStrategy} key`);
  
  React.useEffect(() => {
    console.log(`StatefulItem ${item.name} mounted`);
    
    return () => {
      console.log(`StatefulItem ${item.name} unmounted (state lost!)`);
    };
  }, [item.name]);
  
  return (
    <li style={{ 
      border: '1px solid #ccc', 
      margin: '5px', 
      padding: '10px',
      background: keyStrategy === 'random' ? '#ffe6e6' : 
                 keyStrategy === 'index' ? '#fff3e6' : '#e6ffe6'
    }}>
      <div>
        <strong>{item.name}</strong> ({item.category})
        <small> - Mounted: {new Date(mountTime).toLocaleTimeString()}</small>
      </div>
      
      <div>
        <span>Count: {count}</span>
        <button onClick={() => setCount(count + 1)}>+</button>
        
        <input 
          value={inputValue}
          onChange={(e) => setInputValue(e.target.value)}
          placeholder="Type something..."
          style={{ marginLeft: '10px', width: '120px' }}
        />
      </div>
    </li>
  );
}

// Demonstrating the performance impact of unstable keys
function PerformanceImpactDemo() {
  const [items, setItems] = useState(
    Array.from({ length: 100 }, (_, i) => ({ id: i, name: `Item ${i}` }))
  );
  const [useStableKeys, setUseStableKeys] = useState(true);
  const [renderCount, setRenderCount] = useState(0);
  
  const shuffleItems = () => {
    setItems([...items].sort(() => Math.random() - 0.5));
    setRenderCount(prev => prev + 1);
  };
  
  return (
    <div>
      <h3>Performance Impact of Unstable Keys</h3>
      
      <div>
        <label>
          <input 
            type="checkbox" 
            checked={useStableKeys}
            onChange={(e) => setUseStableKeys(e.target.checked)}
          />
          Use Stable Keys
        </label>
        
        <button onClick={shuffleItems}>Shuffle Items (Render #{renderCount})</button>
      </div>
      
      <div style={{ height: '200px', overflow: 'auto', border: '1px solid #ccc' }}>
        {items.slice(0, 20).map((item, index) => (
          <PerformanceItem 
            key={useStableKeys ? item.id : Math.random()} // Stable vs unstable
            item={item}
            isStable={useStableKeys}
          />
        ))}
      </div>
    </div>
  );
}

function PerformanceItem({ item, isStable }) {
  const [mountTime] = useState(Date.now());
  
  React.useEffect(() => {
    // Simulate expensive mount operation
    const start = performance.now();
    while (performance.now() - start < 1) {
      // Busy wait for 1ms
    }
    
    console.log(`${isStable ? 'Stable' : 'Unstable'} item ${item.name} mounted`);
    
    return () => {
      console.log(`${isStable ? 'Stable' : 'Unstable'} item ${item.name} unmounted`);
    };
  }, []);
  
  return (
    <div style={{ 
      padding: '5px', 
      border: '1px solid #ddd',
      background: isStable ? '#e6ffe6' : '#ffe6e6'
    }}>
      {item.name} - Mounted: {new Date(mountTime).toLocaleTimeString()}
    </div>
  );
}
```

---

### 14. How do you handle memory leaks in React?

**Prevent memory leaks by cleaning up subscriptions, timers, and event listeners in useEffect cleanup functions.**

* **Cleanup functions**: Return cleanup function from useEffect
* **Event listeners**: Remove event listeners on unmount
* **Timers**: Clear timeouts and intervals
* **Subscriptions**: Unsubscribe from external data sources

```jsx
import { useState, useEffect, useRef } from 'react';

function MemoryLeakExamples() {
  const [showComponent, setShowComponent] = useState(false);
  
  return (
    <div>
      <h2>Memory Leak Prevention</h2>
      
      <button onClick={() => setShowComponent(!showComponent)}>
        {showComponent ? 'Hide' : 'Show'} Component
      </button>
      
      {showComponent && (
        <div>
          <TimerLeakExample />
          <EventListenerLeakExample />
          <SubscriptionLeakExample />
          <AsyncLeakExample />
        </div>
      )}
    </div>
  );
}

// ❌ Timer memory leak example
function BadTimerExample() {
  const [count, setCount] = useState(0);
  
  useEffect(() => {
    // ❌ BAD - Timer not cleaned up
    setInterval(() => {
      setCount(prev => prev + 1);
    }, 1000);
    // Missing cleanup - memory leak!
  }, []);
  
  return <div>Bad Timer Count: {count}</div>;
}

// ✅ Proper timer cleanup
function TimerLeakExample() {
  const [count, setCount] = useState(0);
  
  useEffect(() => {
    console.log('Timer started');
    
    const timer = setInterval(() => {
      setCount(prev => prev + 1);
    }, 1000);
    
    // ✅ GOOD - Cleanup timer
    return () => {
      console.log('Timer cleaned up');
      clearInterval(timer);
    };
  }, []);
  
  return <div>Timer Count: {count}</div>;
}

// ✅ Event listener cleanup
function EventListenerLeakExample() {
  const [windowSize, setWindowSize] = useState({
    width: window.innerWidth,
    height: window.innerHeight
  });
  
  useEffect(() => {
    console.log('Event listener added');
    
    const handleResize = () => {
      setWindowSize({
        width: window.innerWidth,
        height: window.innerHeight
      });
    };
    
    window.addEventListener('resize', handleResize);
    
    // ✅ GOOD - Remove event listener
    return () => {
      console.log('Event listener removed');
      window.removeEventListener('resize', handleResize);
    };
  }, []);
  
  return (
    <div>
      Window Size: {windowSize.width} x {windowSize.height}
    </div>
  );
}

// ✅ Subscription cleanup
function SubscriptionLeakExample() {
  const [data, setData] = useState(null);
  
  useEffect(() => {
    console.log('Subscription started');
    
    // Simulate WebSocket or EventSource
    const subscription = {
      unsubscribe: null
    };
    
    // Simulate subscription
    const interval = setInterval(() => {
      setData({ timestamp: Date.now(), value: Math.random() });
    }, 2000);
    
    subscription.unsubscribe = () => clearInterval(interval);
    
    // ✅ GOOD - Cleanup subscription
    return () => {
      console.log('Subscription cleaned up');
      subscription.unsubscribe();
    };
  }, []);
  
  return (
    <div>
      Data: {data ? `${data.value.toFixed(2)} at ${new Date(data.timestamp).toLocaleTimeString()}` : 'Loading...'}
    </div>
  );
}

// ✅ Async operation cleanup
function AsyncLeakExample() {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(false);
  const mountedRef = useRef(true);
  
  useEffect(() => {
    // Track if component is still mounted
    mountedRef.current = true;
    
    return () => {
      mountedRef.current = false;
    };
  }, []);
  
  const fetchData = async () => {
    setLoading(true);
    
    try {
      // Simulate API call
      await new Promise(resolve => setTimeout(resolve, 2000));
      const result = { message: 'Data loaded', timestamp: Date.now() };
      
      // ✅ GOOD - Check if component is still mounted
      if (mountedRef.current) {
        setData(result);
        setLoading(false);
      }
    } catch (error) {
      if (mountedRef.current) {
        console.error('Error:', error);
        setLoading(false);
      }
    }
  };
  
  return (
    <div>
      <button onClick={fetchData} disabled={loading}>
        {loading ? 'Loading...' : 'Fetch Data'}
      </button>
      {data && <div>Data: {data.message}</div>}
    </div>
  );
}

// Custom hook for cleanup patterns
function useCleanup(cleanupFn) {
  const cleanupRef = useRef(cleanupFn);
  
  useEffect(() => {
    cleanupRef.current = cleanupFn;
  });
  
  useEffect(() => {
    return () => {
      if (cleanupRef.current) {
        cleanupRef.current();
      }
    };
  }, []);
}

// AbortController for fetch cleanup
function FetchWithAbortExample() {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(false);
  
  const fetchData = async () => {
    setLoading(true);
    
    // ✅ GOOD - Use AbortController
    const abortController = new AbortController();
    
    try {
      const response = await fetch('/api/data', {
        signal: abortController.signal
      });
      
      if (!response.ok) throw new Error('Failed to fetch');
      
      const result = await response.json();
      setData(result);
    } catch (error) {
      if (error.name !== 'AbortError') {
        console.error('Fetch error:', error);
      }
    } finally {
      setLoading(false);
    }
    
    // Return cleanup function
    return () => {
      abortController.abort();
    };
  };
  
  useEffect(() => {
    const cleanup = fetchData();
    
    return () => {
      cleanup.then(fn => fn && fn());
    };
  }, []);
  
  return (
    <div>
      {loading ? 'Loading...' : data ? JSON.stringify(data) : 'No data'}
    </div>
  );
}
```

---

### 15. What is virtualization (windowing)?

**Virtualization renders only visible items in large lists, dramatically improving performance by reducing DOM nodes.**

* **Render only visible**: Only render items currently in viewport
* **Performance boost**: Handles thousands of items smoothly
* **Memory efficient**: Reduces DOM nodes and memory usage
* **Libraries**: react-window, react-virtualized for implementation

```jsx
import { useState, useMemo, useRef, useEffect } from 'react';

// Simple virtualization implementation
function SimpleVirtualizedList({ items, itemHeight = 50, containerHeight = 300 }) {
  const [scrollTop, setScrollTop] = useState(0);
  const containerRef = useRef();
  
  // Calculate visible range
  const visibleRange = useMemo(() => {
    const startIndex = Math.floor(scrollTop / itemHeight);
    const endIndex = Math.min(
      startIndex + Math.ceil(containerHeight / itemHeight) + 1,
      items.length
    );
    
    return { startIndex, endIndex };
  }, [scrollTop, itemHeight, containerHeight, items.length]);
  
  // Get visible items
  const visibleItems = useMemo(() => {
    return items.slice(visibleRange.startIndex, visibleRange.endIndex);
  }, [items, visibleRange]);
  
  const handleScroll = (e) => {
    setScrollTop(e.target.scrollTop);
  };
  
  const totalHeight = items.length * itemHeight;
  const offsetY = visibleRange.startIndex * itemHeight;
  
  return (
    <div>
      <h3>Simple Virtualized List</h3>
      <p>Showing {visibleItems.length} of {items.length} items</p>
      
      <div
        ref={containerRef}
        style={{
          height: containerHeight,
          overflow: 'auto',
          border: '1px solid #ccc'
        }}
        onScroll={handleScroll}
      >
        {/* Total height container */}
        <div style={{ height: totalHeight, position: 'relative' }}>
          {/* Visible items container */}
          <div style={{ transform: `translateY(${offsetY}px)` }}>
            {visibleItems.map((item, index) => (
              <VirtualizedItem
                key={visibleRange.startIndex + index}
                item={item}
                height={itemHeight}
                index={visibleRange.startIndex + index}
              />
            ))}
          </div>
        </div>
      </div>
    </div>
  );
}

function VirtualizedItem({ item, height, index }) {
  return (
    <div
      style={{
        height,
        padding: '10px',
        borderBottom: '1px solid #eee',
        display: 'flex',
        alignItems: 'center',
        background: index % 2 === 0 ? '#f9f9f9' : 'white'
      }}
    >
      <strong>#{index}</strong>: {item.name} - {item.description}
    </div>
  );
}

// Performance comparison: Regular vs Virtualized
function PerformanceComparison() {
  const [itemCount, setItemCount] = useState(1000);
  const [showVirtualized, setShowVirtualized] = useState(true);
  
  // Generate large dataset
  const items = useMemo(() => {
    return Array.from({ length: itemCount }, (_, i) => ({
      id: i,
      name: `Item ${i}`,
      description: `Description for item ${i} with some additional text`,
      value: Math.floor(Math.random() * 1000)
    }));
  }, [itemCount]);
  
  return (
    <div>
      <h2>Virtualization Performance Comparison</h2>
      
      <div>
        <label>
          Item Count:
          <input 
            type="number" 
            value={itemCount}
            onChange={(e) => setItemCount(Number(e.target.value))}
            min="100"
            max="100000"
            step="1000"
          />
        </label>
        
        <label style={{ marginLeft: '20px' }}>
          <input 
            type="checkbox"
            checked={showVirtualized}
            onChange={(e) => setShowVirtualized(e.target.checked)}
          />
          Use Virtualization
        </label>
      </div>
      
      {showVirtualized ? (
        <SimpleVirtualizedList 
          items={items}
          itemHeight={60}
          containerHeight={400}
        />
      ) : (
        <RegularList items={items.slice(0, 100)} /> // Limit for performance
      )}
    </div>
  );
}

function RegularList({ items }) {
  return (
    <div>
      <h3>Regular List (Not Virtualized)</h3>
      <p>Showing {items.length} items (limited for performance)</p>
      
      <div style={{ height: 400, overflow: 'auto', border: '1px solid #ccc' }}>
        {items.map((item, index) => (
          <div
            key={item.id}
            style={{
              height: 60,
              padding: '10px',
              borderBottom: '1px solid #eee',
              display: 'flex',
              alignItems: 'center',
              background: index % 2 === 0 ? '#f9f9f9' : 'white'
            }}
          >
            <strong>#{index}</strong>: {item.name} - {item.description}
          </div>
        ))}
      </div>
    </div>
  );
}

// Advanced virtualization with dynamic heights
function DynamicHeightVirtualization() {
  const [items] = useState(
    Array.from({ length: 1000 }, (_, i) => ({
      id: i,
      name: `Item ${i}`,
      content: `Content for item ${i}. `.repeat(Math.floor(Math.random() * 5) + 1)
    }))
  );
  
  const [scrollTop, setScrollTop] = useState(0);
  const [itemHeights, setItemHeights] = useState(new Map());
  const containerRef = useRef();
  
  // Estimate item height (simplified)
  const estimateHeight = (item) => {
    const baseHeight = 60;
    const contentHeight = Math.ceil(item.content.length / 50) * 20;
    return baseHeight + contentHeight;
  };
  
  // Calculate visible items with dynamic heights
  const visibleItems = useMemo(() => {
    let currentHeight = 0;
    let startIndex = 0;
    let endIndex = 0;
    
    // Find start index
    for (let i = 0; i < items.length; i++) {
      const height = itemHeights.get(i) || estimateHeight(items[i]);
      if (currentHeight + height > scrollTop) {
        startIndex = i;
        break;
      }
      currentHeight += height;
    }
    
    // Find end index
    currentHeight = 0;
    for (let i = startIndex; i < items.length; i++) {
      const height = itemHeights.get(i) || estimateHeight(items[i]);
      currentHeight += height;
      endIndex = i;
      if (currentHeight > 400) break; // Container height
    }
    
    return items.slice(startIndex, endIndex + 1).map((item, index) => ({
      ...item,
      index: startIndex + index
    }));
  }, [items, scrollTop, itemHeights]);
  
  return (
    <div>
      <h3>Dynamic Height Virtualization</h3>
      
      <div
        ref={containerRef}
        style={{ height: 400, overflow: 'auto', border: '1px solid #ccc' }}
        onScroll={(e) => setScrollTop(e.target.scrollTop)}
      >
        {visibleItems.map(item => (
          <DynamicHeightItem
            key={item.id}
            item={item}
            onHeightChange={(height) => {
              setItemHeights(prev => new Map(prev).set(item.index, height));
            }}
          />
        ))}
      </div>
    </div>
  );
}

function DynamicHeightItem({ item, onHeightChange }) {
  const ref = useRef();
  
  useEffect(() => {
    if (ref.current) {
      const height = ref.current.offsetHeight;
      onHeightChange(height);
    }
  });
  
  return (
    <div
      ref={ref}
      style={{
        padding: '15px',
        borderBottom: '1px solid #eee',
        background: item.index % 2 === 0 ? '#f9f9f9' : 'white'
      }}
    >
      <h4>{item.name}</h4>
      <p>{item.content}</p>
    </div>
  );
}
```

---

### 16. How does React efficiently handle large lists?

**React uses keys for reconciliation, but large lists need virtualization, memoization, and pagination for optimal performance.**

* **Keys for reconciliation**: Efficient updates when items change
* **Virtualization**: Render only visible items
* **Memoization**: Prevent unnecessary re-renders of list items
* **Pagination**: Break large datasets into smaller chunks

```jsx
import { useState, useMemo, memo, useCallback } from 'react';

// Efficient large list with all optimizations
function EfficientLargeList() {
  const [items, setItems] = useState(
    Array.from({ length: 10000 }, (_, i) => ({
      id: i,
      name: `Item ${i}`,
      category: ['A', 'B', 'C'][i % 3],
      value: Math.floor(Math.random() * 1000),
      selected: false
    }))
  );
  
  const [filter, setFilter] = useState('');
  const [sortBy, setSortBy] = useState('name');
  const [currentPage, setCurrentPage] = useState(1);
  const itemsPerPage = 100;
  
  // ✅ Memoized filtering and sorting
  const processedItems = useMemo(() => {
    console.log('Processing items...');
    
    let filtered = items;
    
    // Filter
    if (filter) {
      filtered = filtered.filter(item => 
        item.name.toLowerCase().includes(filter.toLowerCase())
      );
    }
    
    // Sort
    filtered.sort((a, b) => {
      if (sortBy === 'name') return a.name.localeCompare(b.name);
      if (sortBy === 'value') return a.value - b.value;
      return a.category.localeCompare(b.category);
    });
    
    return filtered;
  }, [items, filter, sortBy]);
  
  // ✅ Pagination
  const paginatedItems = useMemo(() => {
    const startIndex = (currentPage - 1) * itemsPerPage;
    return processedItems.slice(startIndex, startIndex + itemsPerPage);
  }, [processedItems, currentPage, itemsPerPage]);
  
  const totalPages = Math.ceil(processedItems.length / itemsPerPage);
  
  // ✅ Memoized callbacks
  const handleToggleItem = useCallback((id) => {
    setItems(prev => prev.map(item => 
      item.id === id ? { ...item, selected: !item.selected } : item
    ));
  }, []);
  
  const handleDeleteItem = useCallback((id) => {
    setItems(prev => prev.filter(item => item.id !== id));
  }, []);
  
  return (
    <div>
      <h2>Efficient Large List Handling</h2>
      
      {/* Controls */}
      <div style={{ marginBottom: '20px' }}>
        <input 
          value={filter}
          onChange={(e) => setFilter(e.target.value)}
          placeholder="Filter items..."
          style={{ marginRight: '10px' }}
        />
        
        <select value={sortBy} onChange={(e) => setSortBy(e.target.value)}>
          <option value="name">Sort by Name</option>
          <option value="value">Sort by Value</option>
          <option value="category">Sort by Category</option>
        </select>
      </div>
      
      {/* Stats */}
      <div style={{ marginBottom: '10px' }}>
        <p>
          Showing {paginatedItems.length} of {processedItems.length} items 
          (Total: {items.length})
        </p>
      </div>
      
      {/* List */}
      <div style={{ minHeight: '400px' }}>
        {paginatedItems.map(item => (
          <MemoizedListItem
            key={item.id}
            item={item}
            onToggle={handleToggleItem}
            onDelete={handleDeleteItem}
          />
        ))}
      </div>
      
      {/* Pagination */}
      <Pagination 
        currentPage={currentPage}
        totalPages={totalPages}
        onPageChange={setCurrentPage}
      />
    </div>
  );
}

// ✅ Memoized list item component
const MemoizedListItem = memo(function ListItem({ item, onToggle, onDelete }) {
  console.log(`Rendering item ${item.id}`);
  
  return (
    <div style={{
      padding: '10px',
      border: '1px solid #eee',
      margin: '2px 0',
      background: item.selected ? '#e6f3ff' : 'white',
      display: 'flex',
      justifyContent: 'space-between',
      alignItems: 'center'
    }}>
      <div>
        <strong>{item.name}</strong> - {item.category} (Value: {item.value})
      </div>
      
      <div>
        <button onClick={() => onToggle(item.id)}>
          {item.selected ? 'Deselect' : 'Select'}
        </button>
        <button onClick={() => onDelete(item.id)} style={{ marginLeft: '5px' }}>
          Delete
        </button>
      </div>
    </div>
  );
});

// Pagination component
function Pagination({ currentPage, totalPages, onPageChange }) {
  const getPageNumbers = () => {
    const pages = [];
    const maxVisible = 5;
    
    let start = Math.max(1, currentPage - Math.floor(maxVisible / 2));
    let end = Math.min(totalPages, start + maxVisible - 1);
    
    if (end - start + 1 < maxVisible) {
      start = Math.max(1, end - maxVisible + 1);
    }
    
    for (let i = start; i <= end; i++) {
      pages.push(i);
    }
    
    return pages;
  };
  
  return (
    <div style={{ marginTop: '20px', textAlign: 'center' }}>
      <button 
        onClick={() => onPageChange(1)}
        disabled={currentPage === 1}
      >
        First
      </button>
      
      <button 
        onClick={() => onPageChange(currentPage - 1)}
        disabled={currentPage === 1}
        style={{ marginLeft: '5px' }}
      >
        Previous
      </button>
      
      {getPageNumbers().map(page => (
        <button
          key={page}
          onClick={() => onPageChange(page)}
          style={{
            marginLeft: '5px',
            background: page === currentPage ? '#007bff' : 'white',
            color: page === currentPage ? 'white' : 'black'
          }}
        >
          {page}
        </button>
      ))}
      
      <button 
        onClick={() => onPageChange(currentPage + 1)}
        disabled={currentPage === totalPages}
        style={{ marginLeft: '5px' }}
      >
        Next
      </button>
      
      <button 
        onClick={() => onPageChange(totalPages)}
        disabled={currentPage === totalPages}
        style={{ marginLeft: '5px' }}
      >
        Last
      </button>
    </div>
  );
}

// Infinite scroll implementation
function InfiniteScrollList() {
  const [items, setItems] = useState(
    Array.from({ length: 50 }, (_, i) => ({ id: i, name: `Item ${i}` }))
  );
  const [loading, setLoading] = useState(false);
  const [hasMore, setHasMore] = useState(true);
  
  const loadMore = useCallback(async () => {
    if (loading || !hasMore) return;
    
    setLoading(true);
    
    // Simulate API call
    await new Promise(resolve => setTimeout(resolve, 1000));
    
    const newItems = Array.from({ length: 20 }, (_, i) => ({
      id: items.length + i,
      name: `Item ${items.length + i}`
    }));
    
    setItems(prev => [...prev, ...newItems]);
    setLoading(false);
    
    // Stop loading after 500 items
    if (items.length >= 500) {
      setHasMore(false);
    }
  }, [items.length, loading, hasMore]);
  
  // Intersection observer for infinite scroll
  const lastItemRef = useCallback((node) => {
    if (loading) return;
    
    const observer = new IntersectionObserver(entries => {
      if (entries[0].isIntersecting && hasMore) {
        loadMore();
      }
    });
    
    if (node) observer.observe(node);
    
    return () => observer.disconnect();
  }, [loading, hasMore, loadMore]);
  
  return (
    <div>
      <h3>Infinite Scroll List</h3>
      
      <div style={{ height: '400px', overflow: 'auto', border: '1px solid #ccc' }}>
        {items.map((item, index) => (
          <div
            key={item.id}
            ref={index === items.length - 1 ? lastItemRef : null}
            style={{
              padding: '10px',
              borderBottom: '1px solid #eee'
            }}
          >
            {item.name}
          </div>
        ))}
        
        {loading && <div style={{ padding: '20px', textAlign: 'center' }}>Loading...</div>}
        {!hasMore && <div style={{ padding: '20px', textAlign: 'center' }}>No more items</div>}
      </div>
    </div>
  );
}

// Complete large list demo
function LargeListDemo() {
  return (
    <div>
      <h1>Efficient Large List Handling</h1>
      
      <EfficientLargeList />
      <hr />
      <InfiniteScrollList />
    </div>
  );
}

export default LargeListDemo;
```

---

## 🟡 5. State Management

### 1. Local State vs Global State

**Local state** is component-specific data that only affects that component and its children. **Global state** is shared across multiple components throughout the app.

**When to use local state:**
- Form inputs, toggles, component-specific UI state
- Data that doesn't need to be shared

**When to use global state:**
- User authentication, theme settings, shopping cart
- Data needed by multiple unrelated components

```jsx
// Local State
function Counter() {
  const [count, setCount] = useState(0);
  return <button onClick={() => setCount(count + 1)}>{count}</button>;
}

// Global State (Context)
const UserContext = createContext();
function App() {
  const [user, setUser] = useState(null);
  return (
    <UserContext.Provider value={{ user, setUser }}>
      <Header />
      <Profile />
    </UserContext.Provider>
  );
}
```

### 2. What is Context API and When Should You Use It?

**Context API** lets you share data across components without prop drilling. It creates a global state that any component can access.

**Use Context when:**
- Passing props through many levels (prop drilling)
- Theme, authentication, language settings
- Medium-sized apps with moderate state sharing needs

```jsx
// Create Context
const ThemeContext = createContext();

// Provider Component
function App() {
  const [theme, setTheme] = useState('light');
  
  return (
    <ThemeContext.Provider value={{ theme, setTheme }}>
      <Header />
      <Main />
    </ThemeContext.Provider>
  );
}

// Consumer Component
function Header() {
  const { theme, setTheme } = useContext(ThemeContext);
  
  return (
    <header className={theme}>
      <button onClick={() => setTheme(theme === 'light' ? 'dark' : 'light')}>
        Toggle Theme
      </button>
    </header>
  );
}
```

### 3. Difference Between State and Context

**State** is component data that can change over time. **Context** is a delivery mechanism for sharing state across components.

**Key differences:**
- State holds the actual data, Context delivers it
- State triggers re-renders when changed, Context passes state to consumers
- State is local by default, Context makes it globally accessible

```jsx
// State - holds the data
function App() {
  const [user, setUser] = useState({ name: 'John', role: 'admin' });
  
  // Context - delivers the state
  return (
    <UserContext.Provider value={{ user, setUser }}>
      <Dashboard />
    </UserContext.Provider>
  );
}

// Context consumer gets the state
function Dashboard() {
  const { user } = useContext(UserContext); // Receiving state via context
  return <h1>Welcome {user.name}</h1>;
}
```

### 4. Context vs Redux – How Do You Decide?

**Use Context when:**
- Small to medium apps
- Simple state updates
- Few components need the data
- Learning curve matters

**Use Redux when:**
- Large, complex apps
- Complex state logic with multiple actions
- Time-travel debugging needed
- Predictable state updates required

```jsx
// Context - Simple
const CartContext = createContext();
function CartProvider({ children }) {
  const [items, setItems] = useState([]);
  const addItem = (item) => setItems([...items, item]);
  
  return (
    <CartContext.Provider value={{ items, addItem }}>
      {children}
    </CartContext.Provider>
  );
}

// Redux - Complex
const cartSlice = createSlice({
  name: 'cart',
  initialState: { items: [], total: 0 },
  reducers: {
    addItem: (state, action) => {
      state.items.push(action.payload);
      state.total += action.payload.price;
    },
    removeItem: (state, action) => {
      state.items = state.items.filter(item => item.id !== action.payload);
    }
  }
});
```

### 5. What is Redux and Why is it Used?

**Redux** is a predictable state container for JavaScript apps. It centralizes application state in a single store with strict rules for updates.

**Why use Redux:**
- Predictable state updates through pure functions
- Time-travel debugging and dev tools
- Centralized state management
- Great for complex apps with lots of state interactions

**Core concepts:**
- **Store**: Holds the state
- **Actions**: Describe what happened
- **Reducers**: Specify how state changes

```jsx
// Action
const increment = () => ({ type: 'INCREMENT' });
const decrement = () => ({ type: 'DECREMENT' });

// Reducer
function counterReducer(state = { count: 0 }, action) {
  switch (action.type) {
    case 'INCREMENT':
      return { count: state.count + 1 };
    case 'DECREMENT':
      return { count: state.count - 1 };
    default:
      return state;
  }
}

// Store
const store = createStore(counterReducer);

// Component
function Counter() {
  const count = useSelector(state => state.count);
  const dispatch = useDispatch();
  
  return (
    <div>
      <span>{count}</span>
      <button onClick={() => dispatch(increment())}>+</button>
      <button onClick={() => dispatch(decrement())}>-</button>
    </div>
  );
}
```

### 6. Redux Core Principles (Actions, Reducers, Store)

**Three core principles:**
1. **Single source of truth** - One store for entire app state
2. **State is read-only** - Only way to change state is dispatching actions
3. **Changes made with pure functions** - Reducers are pure functions

**Components:**
- **Actions**: Plain objects describing what happened
- **Reducers**: Pure functions that specify how state changes
- **Store**: Holds state, allows access via getState(), dispatch actions

```jsx
// Action
const addTodo = (text) => ({
  type: 'ADD_TODO',
  payload: { id: Date.now(), text, completed: false }
});

// Reducer
function todosReducer(state = [], action) {
  switch (action.type) {
    case 'ADD_TODO':
      return [...state, action.payload];
    case 'TOGGLE_TODO':
      return state.map(todo => 
        todo.id === action.payload ? { ...todo, completed: !todo.completed } : todo
      );
    default:
      return state;
  }
}

// Store
const store = createStore(todosReducer);
store.dispatch(addTodo('Learn Redux'));
```

### 7. How Do You Handle Async Actions in Redux?

**Redux is synchronous by default.** For async operations like API calls, you need middleware like **redux-thunk** or **redux-saga**.

**With Redux Thunk:**
- Action creators return functions instead of plain objects
- Functions receive dispatch and getState as arguments
- Can dispatch multiple actions based on async results

```jsx
// Async action creator with thunk
const fetchUser = (userId) => {
  return async (dispatch, getState) => {
    dispatch({ type: 'FETCH_USER_START' });
    
    try {
      const response = await fetch(`/api/users/${userId}`);
      const user = await response.json();
      dispatch({ type: 'FETCH_USER_SUCCESS', payload: user });
    } catch (error) {
      dispatch({ type: 'FETCH_USER_ERROR', payload: error.message });
    }
  };
};

// Usage
dispatch(fetchUser(123));

// Reducer handles all states
function userReducer(state = { loading: false, user: null, error: null }, action) {
  switch (action.type) {
    case 'FETCH_USER_START':
      return { ...state, loading: true, error: null };
    case 'FETCH_USER_SUCCESS':
      return { loading: false, user: action.payload, error: null };
    case 'FETCH_USER_ERROR':
      return { loading: false, user: null, error: action.payload };
    default:
      return state;
  }
}
```

### 8. What are Redux Middlewares?

**Middleware** sits between dispatching an action and reaching the reducer. It intercepts actions and can modify, delay, or stop them.

**Common use cases:**
- Logging actions
- Handling async operations
- API calls
- Error handling

```jsx
// Custom logging middleware
const loggerMiddleware = (store) => (next) => (action) => {
  console.log('Dispatching:', action);
  const result = next(action);
  console.log('New state:', store.getState());
  return result;
};

// Apply middleware
const store = createStore(
  rootReducer,
  applyMiddleware(loggerMiddleware, thunk)
);

// Built-in Redux Thunk middleware
const fetchData = () => (dispatch) => {
  fetch('/api/data')
    .then(response => response.json())
    .then(data => dispatch({ type: 'SET_DATA', payload: data }));
};
```

### 9. Difference Between Redux-Thunk and Redux-Saga

**Redux-Thunk:**
- Simple, lightweight
- Action creators return functions
- Good for basic async operations
- Easy to learn and implement

**Redux-Saga:**
- More powerful, uses ES6 generators
- Better for complex async flows
- Built-in effects for testing
- Steeper learning curve

```jsx
// Redux-Thunk
const fetchUser = (id) => async (dispatch) => {
  const response = await fetch(`/users/${id}`);
  const user = await response.json();
  dispatch({ type: 'SET_USER', payload: user });
};

// Redux-Saga
function* fetchUserSaga(action) {
  try {
    const user = yield call(fetch, `/users/${action.payload}`);
    const userData = yield call([user, 'json']);
    yield put({ type: 'SET_USER', payload: userData });
  } catch (error) {
    yield put({ type: 'SET_ERROR', payload: error.message });
  }
}

function* watchFetchUser() {
  yield takeEvery('FETCH_USER_REQUEST', fetchUserSaga);
}
```

### 10. How Would You Structure Redux in a Large Application?

**Feature-based structure** is recommended for large apps:

```
src/
├── store/
│   ├── index.js          // Store configuration
│   └── rootReducer.js    // Combine all reducers
├── features/
│   ├── auth/
│   │   ├── authSlice.js  // Actions + reducer
│   │   └── authAPI.js    // API calls
│   ├── products/
│   │   ├── productsSlice.js
│   │   └── productsAPI.js
│   └── cart/
│       ├── cartSlice.js
│       └── cartSelectors.js
```

**Best practices:**
- Use Redux Toolkit for less boilerplate
- Create feature slices with createSlice()
- Keep selectors with related features
- Normalize complex state structures

```jsx
// Feature slice example
import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';

// Async thunk
export const fetchProducts = createAsyncThunk(
  'products/fetchProducts',
  async () => {
    const response = await fetch('/api/products');
    return response.json();
  }
);

// Slice
const productsSlice = createSlice({
  name: 'products',
  initialState: { items: [], loading: false },
  reducers: {
    clearProducts: (state) => {
      state.items = [];
    }
  },
  extraReducers: (builder) => {
    builder
      .addCase(fetchProducts.pending, (state) => {
        state.loading = true;
      })
      .addCase(fetchProducts.fulfilled, (state, action) => {
        state.loading = false;
        state.items = action.payload;
      });
  }
});

export const { clearProducts } = productsSlice.actions;
export default productsSlice.reducer;
```

## 🟠 6. Routing (React Router v6)

### 1. What is React Router and How Does It Work?

**React Router** is a library for handling client-side routing in React applications. It enables navigation between different components without page refreshes.

**How it works:**
- Maps URL paths to React components
- Uses browser's History API for navigation
- Renders components based on current URL
- Maintains browser history for back/forward buttons

```jsx
import { BrowserRouter, Routes, Route } from 'react-router-dom';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/about" element={<About />} />
        <Route path="/contact" element={<Contact />} />
      </Routes>
    </BrowserRouter>
  );
}

function Home() {
  return <h1>Home Page</h1>;
}

function About() {
  return <h1>About Page</h1>;
}
```

### 2. How Do You Implement Routing in React?

**Step-by-step implementation:**
1. Install React Router: `npm install react-router-dom`
2. Wrap app with BrowserRouter
3. Define Routes with path and element
4. Use Link or NavLink for navigation

```jsx
import { BrowserRouter, Routes, Route, Link } from 'react-router-dom';

function App() {
  return (
    <BrowserRouter>
      <nav>
        <Link to="/">Home</Link>
        <Link to="/products">Products</Link>
        <Link to="/profile">Profile</Link>
      </nav>
      
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/products" element={<Products />} />
        <Route path="/profile" element={<Profile />} />
        <Route path="*" element={<NotFound />} />
      </Routes>
    </BrowserRouter>
  );
}

function NotFound() {
  return <h1>404 - Page Not Found</h1>;
}
```

### 3. What are Dynamic Routes?

**Dynamic routes** use URL parameters to render different content based on the URL. They're perfect for pages like user profiles, product details, or blog posts.

**Key features:**
- Use colon syntax `:id` for parameters
- Access parameters with `useParams` hook
- Can have multiple parameters in one route

```jsx
import { Routes, Route, useParams } from 'react-router-dom';

function App() {
  return (
    <Routes>
      <Route path="/user/:id" element={<UserProfile />} />
      <Route path="/product/:category/:id" element={<ProductDetail />} />
      <Route path="/blog/:slug" element={<BlogPost />} />
    </Routes>
  );
}

function UserProfile() {
  const { id } = useParams();
  return <h1>User Profile: {id}</h1>;
}

function ProductDetail() {
  const { category, id } = useParams();
  return (
    <div>
      <h1>Product {id}</h1>
      <p>Category: {category}</p>
    </div>
  );
}

// URLs: /user/123, /product/electronics/456, /blog/react-tutorial
```

### 4. How Do You Implement Nested Routes?

**Nested routes** allow you to render components inside other components, creating hierarchical layouts. Perfect for dashboards, admin panels, or multi-level navigation.

**Key concepts:**
- Parent route uses `<Outlet />` to render child routes
- Child routes are defined inside parent route
- URLs build upon parent path

```jsx
import { Routes, Route, Outlet, Link } from 'react-router-dom';

function App() {
  return (
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/dashboard" element={<Dashboard />}>
        <Route path="analytics" element={<Analytics />} />
        <Route path="users" element={<Users />} />
        <Route path="settings" element={<Settings />} />
      </Route>
    </Routes>
  );
}

function Dashboard() {
  return (
    <div>
      <h1>Dashboard</h1>
      <nav>
        <Link to="/dashboard/analytics">Analytics</Link>
        <Link to="/dashboard/users">Users</Link>
        <Link to="/dashboard/settings">Settings</Link>
      </nav>
      <Outlet /> {/* Child routes render here */}
    </div>
  );
}

function Analytics() {
  return <h2>Analytics Content</h2>;
}

// URLs: /dashboard/analytics, /dashboard/users, /dashboard/settings
```

### 5. What are `useParams`, `useLocation`, and `useNavigate`?

**Three essential React Router hooks:**

**`useParams`** - Gets URL parameters from dynamic routes
**`useLocation`** - Gets current location object with pathname, search, etc.
**`useNavigate`** - Programmatically navigate to different routes

```jsx
import { useParams, useLocation, useNavigate } from 'react-router-dom';

function ProductPage() {
  // Get URL parameters
  const { id, category } = useParams();
  
  // Get location information
  const location = useLocation();
  
  // Get navigation function
  const navigate = useNavigate();
  
  const handleGoBack = () => {
    navigate(-1); // Go back one page
  };
  
  const handleGoHome = () => {
    navigate('/'); // Navigate to home
  };
  
  return (
    <div>
      <h1>Product {id}</h1>
      <p>Category: {category}</p>
      <p>Current path: {location.pathname}</p>
      <p>Search params: {location.search}</p>
      
      <button onClick={handleGoBack}>Go Back</button>
      <button onClick={handleGoHome}>Go Home</button>
      <button onClick={() => navigate('/products')}>All Products</button>
    </div>
  );
}

// URL: /product/electronics/123?color=red
// useParams(): { id: '123', category: 'electronics' }
// useLocation(): { pathname: '/product/electronics/123', search: '?color=red' }
```

### 6. What are Router Components in React Router v6?

**Main Router components in v6:**

**`BrowserRouter`** - Uses HTML5 history API, clean URLs
**`HashRouter`** - Uses hash portion of URL, works without server config
**`MemoryRouter`** - Keeps history in memory, good for testing
**`Routes`** - Container for Route components (replaces Switch)
**`Route`** - Defines path-to-component mapping

```jsx
import { 
  BrowserRouter, 
  HashRouter, 
  MemoryRouter, 
  Routes, 
  Route 
} from 'react-router-dom';

// BrowserRouter - Most common, clean URLs
function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/about" element={<About />} />
      </Routes>
    </BrowserRouter>
  );
}

// HashRouter - For static hosting
function AppWithHash() {
  return (
    <HashRouter>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/about" element={<About />} />
      </Routes>
    </HashRouter>
  );
}

// MemoryRouter - For testing
function TestApp() {
  return (
    <MemoryRouter initialEntries={['/']}>
      <Routes>
        <Route path="/" element={<Home />} />
      </Routes>
    </MemoryRouter>
  );
}

// URLs:
// BrowserRouter: /about
// HashRouter: /#/about
// MemoryRouter: No URL changes
```

## 🟠 7. Data Fetching & Side Effects

### 1. How Do You Handle API Calls in React?

**Three main approaches:**
1. **useEffect + fetch/axios** - Traditional approach
2. **Custom hooks** - Reusable data fetching logic
3. **Data fetching libraries** - React Query, SWR, Apollo

**Basic pattern with useEffect:**
- Fetch on component mount
- Handle loading, success, and error states
- Clean up to prevent memory leaks

```jsx
import { useState, useEffect } from 'react';

function UserProfile({ userId }) {
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchUser = async () => {
      try {
        setLoading(true);
        const response = await fetch(`/api/users/${userId}`);
        if (!response.ok) throw new Error('Failed to fetch');
        const userData = await response.json();
        setUser(userData);
      } catch (err) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    };

    fetchUser();
  }, [userId]);

  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error}</div>;
  return <div>Welcome {user?.name}</div>;
}
```

### 2. What Problems Does `useEffect` Have with Data Fetching?

**Major problems:**
- **Race conditions** - Multiple requests can complete out of order
- **Memory leaks** - Setting state after component unmounts
- **No caching** - Refetches on every mount
- **Complex cleanup** - Manual request cancellation needed
- **Boilerplate code** - Repetitive loading/error handling

```jsx
// Problematic useEffect data fetching
function UserList() {
  const [users, setUsers] = useState([]);
  const [query, setQuery] = useState('');

  useEffect(() => {
    // Problem: No cleanup, race conditions possible
    fetch(`/api/users?search=${query}`)
      .then(res => res.json())
      .then(setUsers); // Could set stale data if query changed
  }, [query]);

  return (
    <div>
      <input value={query} onChange={(e) => setQuery(e.target.value)} />
      {users.map(user => <div key={user.id}>{user.name}</div>)}
    </div>
  );
}

// Better approach with cleanup
function UserListFixed() {
  const [users, setUsers] = useState([]);
  const [query, setQuery] = useState('');

  useEffect(() => {
    const controller = new AbortController();
    
    fetch(`/api/users?search=${query}`, { 
      signal: controller.signal 
    })
      .then(res => res.json())
      .then(setUsers)
      .catch(err => {
        if (err.name !== 'AbortError') {
          console.error(err);
        }
      });

    return () => controller.abort(); // Cleanup
  }, [query]);

  return (
    <div>
      <input value={query} onChange={(e) => setQuery(e.target.value)} />
      {users.map(user => <div key={user.id}>{user.name}</div>)}
    </div>
  );
}
```

### 3. How Do You Fetch Data Without `useEffect`?

**Modern alternatives:**
1. **Event handlers** - Fetch on user actions
2. **React Query** - Declarative data fetching
3. **Suspense + libraries** - Concurrent features
4. **Server components** - Next.js, Remix

```jsx
// 1. Event-driven fetching
function SearchUsers() {
  const [users, setUsers] = useState([]);
  const [loading, setLoading] = useState(false);

  const handleSearch = async (query) => {
    setLoading(true);
    const response = await fetch(`/api/users?search=${query}`);
    const users = await response.json();
    setUsers(users);
    setLoading(false);
  };

  return (
    <div>
      <button onClick={() => handleSearch('john')}>
        Search for John
      </button>
      {loading ? 'Loading...' : users.map(user => 
        <div key={user.id}>{user.name}</div>
      )}
    </div>
  );
}

// 2. React Query approach
import { useQuery } from '@tanstack/react-query';

function UserProfile({ userId }) {
  const { data: user, isLoading, error } = useQuery({
    queryKey: ['user', userId],
    queryFn: () => fetch(`/api/users/${userId}`).then(res => res.json()),
    enabled: !!userId // Only fetch if userId exists
  });

  if (isLoading) return <div>Loading...</div>;
  if (error) return <div>Error: {error.message}</div>;
  return <div>Welcome {user?.name}</div>;
}
```

### 4. What is React Query / TanStack Query?

**React Query** is a powerful data fetching library that handles server state management with built-in caching, synchronization, and background updates.

**Key features:**
- **Automatic caching** - Stores and reuses data
- **Background refetching** - Keeps data fresh
- **Optimistic updates** - Instant UI feedback
- **Error handling** - Built-in retry logic

```jsx
import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query';

// Basic query
function Posts() {
  const { data: posts, isLoading } = useQuery({
    queryKey: ['posts'],
    queryFn: () => fetch('/api/posts').then(res => res.json()),
    staleTime: 5 * 60 * 1000, // 5 minutes
  });

  if (isLoading) return <div>Loading...</div>;
  return posts.map(post => <div key={post.id}>{post.title}</div>);
}

// Mutation for creating posts
function CreatePost() {
  const queryClient = useQueryClient();
  
  const mutation = useMutation({
    mutationFn: (newPost) => 
      fetch('/api/posts', {
        method: 'POST',
        body: JSON.stringify(newPost),
        headers: { 'Content-Type': 'application/json' }
      }),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['posts'] });
    }
  });

  return (
    <button onClick={() => mutation.mutate({ title: 'New Post' })}>
      {mutation.isPending ? 'Creating...' : 'Create Post'}
    </button>
  );
}
```

### 5. Difference Between Client-Side Caching and Server State

**Client-side caching:**
- Stores data in browser memory/localStorage
- Controlled by your application
- Persists until manually cleared or page refresh
- Good for user preferences, form data

**Server state:**
- Data that lives on the server
- Can become stale quickly
- Shared between users
- Needs synchronization strategies

```jsx
// Client-side caching (localStorage)
function useLocalStorage(key, initialValue) {
  const [value, setValue] = useState(() => {
    const stored = localStorage.getItem(key);
    return stored ? JSON.parse(stored) : initialValue;
  });

  const setStoredValue = (newValue) => {
    setValue(newValue);
    localStorage.setItem(key, JSON.stringify(newValue));
  };

  return [value, setStoredValue];
}

// Server state management (React Query)
function UserDashboard() {
  // Server state - can change on server
  const { data: notifications } = useQuery({
    queryKey: ['notifications'],
    queryFn: fetchNotifications,
    refetchInterval: 30000, // Refetch every 30 seconds
  });

  // Client state - only changes locally
  const [theme, setTheme] = useLocalStorage('theme', 'light');

  return (
    <div className={theme}>
      <button onClick={() => setTheme(theme === 'light' ? 'dark' : 'light')}>
        Toggle Theme
      </button>
      <div>Notifications: {notifications?.length || 0}</div>
    </div>
  );
}
```

### 6. How Do You Cancel API Requests?

**Use AbortController** to cancel fetch requests and prevent memory leaks when components unmount or dependencies change.

**Why cancel requests:**
- Prevent setting state on unmounted components
- Avoid race conditions
- Save bandwidth and resources

```jsx
import { useState, useEffect } from 'react';

function SearchResults({ query }) {
  const [results, setResults] = useState([]);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    if (!query) return;

    const controller = new AbortController();
    setLoading(true);

    fetch(`/api/search?q=${query}`, {
      signal: controller.signal
    })
      .then(response => response.json())
      .then(data => {
        setResults(data);
        setLoading(false);
      })
      .catch(error => {
        if (error.name !== 'AbortError') {
          console.error('Search failed:', error);
          setLoading(false);
        }
        // AbortError is expected when cancelling
      });

    // Cleanup function cancels the request
    return () => {
      controller.abort();
    };
  }, [query]);

  return (
    <div>
      {loading && <div>Searching...</div>}
      {results.map(item => <div key={item.id}>{item.title}</div>)}
    </div>
  );
}

// Custom hook with cancellation
function useApi(url) {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const controller = new AbortController();

    fetch(url, { signal: controller.signal })
      .then(res => res.json())
      .then(setData)
      .finally(() => setLoading(false));

    return () => controller.abort();
  }, [url]);

  return { data, loading };
}
```

### 7. How Do You Handle Race Conditions?

**Race conditions** occur when multiple async operations complete in unexpected order. Common solutions include request cancellation, ignore flags, and proper state management.

**Strategies:**
- **AbortController** - Cancel previous requests
- **Ignore flag** - Ignore stale responses
- **Request ID** - Track latest request
- **React Query** - Handles automatically

```jsx
// Problem: Race condition
function BadUserSearch() {
  const [users, setUsers] = useState([]);
  const [query, setQuery] = useState('');

  useEffect(() => {
    // If user types fast, older requests might complete after newer ones
    fetch(`/api/users?search=${query}`)
      .then(res => res.json())
      .then(setUsers); // Wrong data if requests complete out of order
  }, [query]);
}

// Solution 1: AbortController
function GoodUserSearch() {
  const [users, setUsers] = useState([]);
  const [query, setQuery] = useState('');

  useEffect(() => {
    const controller = new AbortController();

    fetch(`/api/users?search=${query}`, { 
      signal: controller.signal 
    })
      .then(res => res.json())
      .then(setUsers)
      .catch(err => {
        if (err.name !== 'AbortError') {
          console.error(err);
        }
      });

    return () => controller.abort(); // Cancel previous request
  }, [query]);
}

// Solution 2: Ignore flag
function UserSearchWithIgnore() {
  const [users, setUsers] = useState([]);
  const [query, setQuery] = useState('');

  useEffect(() => {
    let ignore = false;

    fetch(`/api/users?search=${query}`)
      .then(res => res.json())
      .then(data => {
        if (!ignore) { // Only update if this is still the latest request
          setUsers(data);
        }
      });

    return () => {
      ignore = true; // Mark this request as stale
    };
  }, [query]);
}

// Solution 3: React Query (handles race conditions automatically)
function ReactQueryUserSearch() {
  const [query, setQuery] = useState('');
  
  const { data: users = [] } = useQuery({
    queryKey: ['users', query],
    queryFn: () => fetch(`/api/users?search=${query}`).then(res => res.json()),
    enabled: query.length > 0
  });

  return (
    <div>
      <input value={query} onChange={(e) => setQuery(e.target.value)} />
      {users.map(user => <div key={user.id}>{user.name}</div>)}
    </div>
  );
}
```

## 🟠 8. Architecture & Design Patterns

### 1. How Do You Structure a Large-Scale React Application?

**Key principles for large React apps:**
- **Feature-based organization** - Group by business features, not file types
- **Layered architecture** - Separate concerns (UI, business logic, data)
- **Shared resources** - Common components, utilities, and constants
- **Clear boundaries** - Well-defined interfaces between modules

```
src/
├── components/           # Shared UI components
│   ├── Button/
│   ├── Modal/
│   └── Layout/
├── features/            # Business features
│   ├── auth/
│   │   ├── components/
│   │   ├── hooks/
│   │   ├── services/
│   │   └── index.js
│   ├── dashboard/
│   └── products/
├── shared/              # Shared utilities
│   ├── hooks/
│   ├── utils/
│   ├── constants/
│   └── types/
├── services/            # API layer
├── store/              # Global state
└── App.js
```

```jsx
// Feature module example - features/auth/index.js
export { LoginForm } from './components/LoginForm';
export { useAuth } from './hooks/useAuth';
export { authService } from './services/authService';

// Clean imports in other files
import { LoginForm, useAuth } from '../features/auth';

// App.js - High-level structure
function App() {
  return (
    <BrowserRouter>
      <Layout>
        <Routes>
          <Route path="/auth/*" element={<AuthFeature />} />
          <Route path="/dashboard/*" element={<DashboardFeature />} />
          <Route path="/products/*" element={<ProductsFeature />} />
        </Routes>
      </Layout>
    </BrowserRouter>
  );
}
```

### 2. What is Feature-Based Folder Structure?

**Feature-based structure** organizes code by business features rather than technical layers. Each feature contains all related files - components, hooks, services, and tests.

**Benefits:**
- **Better maintainability** - Everything related is together
- **Team scalability** - Teams can own entire features
- **Easier testing** - Feature-specific tests are co-located
- **Clear boundaries** - Reduces coupling between features

```
src/
├── features/
│   ├── authentication/
│   │   ├── components/
│   │   │   ├── LoginForm.jsx
│   │   │   ├── SignupForm.jsx
│   │   │   └── PasswordReset.jsx
│   │   ├── hooks/
│   │   │   ├── useAuth.js
│   │   │   └── useLogin.js
│   │   ├── services/
│   │   │   └── authAPI.js
│   │   ├── __tests__/
│   │   └── index.js
│   ├── user-profile/
│   │   ├── components/
│   │   ├── hooks/
│   │   └── services/
│   └── shopping-cart/
└── shared/
    ├── components/      # Reusable UI components
    ├── hooks/          # Generic hooks
    └── utils/          # Helper functions
```

```jsx
// Feature entry point - features/authentication/index.js
export { LoginForm, SignupForm } from './components';
export { useAuth, useLogin } from './hooks';
export { authAPI } from './services';

// Usage in App
import { LoginForm, useAuth } from './features/authentication';
import { UserProfile } from './features/user-profile';

function App() {
  const { user, isAuthenticated } = useAuth();
  
  return isAuthenticated ? <UserProfile /> : <LoginForm />;
}
```

### 3. Smart vs Dumb Components

**Smart components (Container):**
- Manage state and business logic
- Handle data fetching and side effects
- Connect to global state or APIs
- Pass data down to dumb components

**Dumb components (Presentational):**
- Only receive props and render UI
- No state management or side effects
- Highly reusable and testable
- Focus purely on presentation

```jsx
// Smart Component - Manages state and logic
function UserListContainer() {
  const [users, setUsers] = useState([]);
  const [loading, setLoading] = useState(true);
  const [searchTerm, setSearchTerm] = useState('');

  useEffect(() => {
    fetchUsers().then(data => {
      setUsers(data);
      setLoading(false);
    });
  }, []);

  const filteredUsers = users.filter(user => 
    user.name.toLowerCase().includes(searchTerm.toLowerCase())
  );

  return (
    <UserListPresentation
      users={filteredUsers}
      loading={loading}
      searchTerm={searchTerm}
      onSearchChange={setSearchTerm}
    />
  );
}

// Dumb Component - Pure presentation
function UserListPresentation({ users, loading, searchTerm, onSearchChange }) {
  if (loading) return <div>Loading...</div>;

  return (
    <div>
      <SearchInput 
        value={searchTerm} 
        onChange={onSearchChange} 
        placeholder="Search users..." 
      />
      <div>
        {users.map(user => (
          <UserCard key={user.id} user={user} />
        ))}
      </div>
    </div>
  );
}

// Reusable dumb components
function SearchInput({ value, onChange, placeholder }) {
  return (
    <input
      type="text"
      value={value}
      onChange={(e) => onChange(e.target.value)}
      placeholder={placeholder}
    />
  );
}

function UserCard({ user }) {
  return (
    <div className="user-card">
      <h3>{user.name}</h3>
      <p>{user.email}</p>
    </div>
  );
}
```

### 4. Container-Presentational Pattern

**Container-Presentational pattern** separates data logic from UI rendering. Containers handle the "how it works" while presentational components handle "how it looks."

**Container responsibilities:**
- Data fetching and state management
- Business logic and side effects
- Connecting to external services

**Presentational responsibilities:**
- Rendering UI based on props
- Handling user interactions via callbacks
- Styling and layout

```jsx
// Container - Handles data and logic
function ProductsContainer() {
  const [products, setProducts] = useState([]);
  const [cart, setCart] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    loadProducts().then(data => {
      setProducts(data);
      setLoading(false);
    });
  }, []);

  const addToCart = (product) => {
    setCart(prev => [...prev, product]);
  };

  const removeFromCart = (productId) => {
    setCart(prev => prev.filter(item => item.id !== productId));
  };

  return (
    <ProductsPresentation
      products={products}
      cart={cart}
      loading={loading}
      onAddToCart={addToCart}
      onRemoveFromCart={removeFromCart}
    />
  );
}

// Presentation - Pure UI rendering
function ProductsPresentation({ 
  products, 
  cart, 
  loading, 
  onAddToCart, 
  onRemoveFromCart 
}) {
  if (loading) return <LoadingSpinner />;

  return (
    <div className="products-page">
      <CartSummary 
        items={cart} 
        onRemoveItem={onRemoveFromCart} 
      />
      <ProductGrid 
        products={products} 
        onAddToCart={onAddToCart} 
      />
    </div>
  );
}

// Presentational components
function ProductGrid({ products, onAddToCart }) {
  return (
    <div className="product-grid">
      {products.map(product => (
        <ProductCard
          key={product.id}
          product={product}
          onAddToCart={() => onAddToCart(product)}
        />
      ))}
    </div>
  );
}

function ProductCard({ product, onAddToCart }) {
  return (
    <div className="product-card">
      <img src={product.image} alt={product.name} />
      <h3>{product.name}</h3>
      <p>${product.price}</p>
      <button onClick={onAddToCart}>Add to Cart</button>
    </div>
  );
}
```

### 5. How Do You Design Reusable Components?

**Key principles for reusable components:**
- **Single responsibility** - One clear purpose
- **Flexible props API** - Accept configuration via props
- **Composition over inheritance** - Use children and render props
- **Consistent naming** - Clear, descriptive prop names

```jsx
// Flexible Button component
function Button({ 
  children, 
  variant = 'primary', 
  size = 'medium', 
  disabled = false,
  loading = false,
  onClick,
  ...props 
}) {
  const className = `btn btn--${variant} btn--${size} ${disabled ? 'btn--disabled' : ''}`;
  
  return (
    <button
      className={className}
      disabled={disabled || loading}
      onClick={onClick}
      {...props}
    >
      {loading ? <Spinner /> : children}
    </button>
  );
}

// Usage examples
<Button onClick={handleSave}>Save</Button>
<Button variant="secondary" size="small">Cancel</Button>
<Button loading={isSubmitting}>Submit</Button>

// Flexible Modal component with composition
function Modal({ isOpen, onClose, children }) {
  if (!isOpen) return null;

  return (
    <div className="modal-overlay" onClick={onClose}>
      <div className="modal-content" onClick={(e) => e.stopPropagation()}>
        <button className="modal-close" onClick={onClose}>×</button>
        {children}
      </div>
    </div>
  );
}

// Modal subcomponents for composition
Modal.Header = function ModalHeader({ children }) {
  return <div className="modal-header">{children}</div>;
};

Modal.Body = function ModalBody({ children }) {
  return <div className="modal-body">{children}</div>;
};

Modal.Footer = function ModalFooter({ children }) {
  return <div className="modal-footer">{children}</div>;
};

// Usage with composition
function DeleteConfirmation({ isOpen, onClose, onConfirm }) {
  return (
    <Modal isOpen={isOpen} onClose={onClose}>
      <Modal.Header>
        <h2>Confirm Delete</h2>
      </Modal.Header>
      <Modal.Body>
        <p>Are you sure you want to delete this item?</p>
      </Modal.Body>
      <Modal.Footer>
        <Button variant="secondary" onClick={onClose}>Cancel</Button>
        <Button variant="danger" onClick={onConfirm}>Delete</Button>
      </Modal.Footer>
    </Modal>
  );
}

// Flexible Input component with validation
function Input({ 
  label, 
  error, 
  helperText, 
  required = false,
  ...inputProps 
}) {
  const inputId = inputProps.id || inputProps.name;
  
  return (
    <div className="input-group">
      {label && (
        <label htmlFor={inputId} className="input-label">
          {label} {required && <span className="required">*</span>}
        </label>
      )}
      <input
        id={inputId}
        className={`input ${error ? 'input--error' : ''}`}
        {...inputProps}
      />
      {error && <span className="input-error">{error}</span>}
      {helperText && <span className="input-helper">{helperText}</span>}
    </div>
  );
}

// Usage
<Input 
  label="Email" 
  name="email" 
  type="email" 
  required 
  error={errors.email}
  helperText="We'll never share your email"
/>
```

### 6. How Do You Handle Shared Logic Across Pages?

**Four main approaches for sharing logic:**
1. **Custom hooks** - Share stateful logic between components
2. **Context providers** - Share state and functions globally
3. **Higher-order components** - Wrap components with shared behavior
4. **Utility functions** - Share pure functions and calculations

```jsx
// 1. Custom hooks for shared logic
function useAuth() {
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    checkAuthStatus().then(userData => {
      setUser(userData);
      setLoading(false);
    });
  }, []);

  const login = async (credentials) => {
    const userData = await authAPI.login(credentials);
    setUser(userData);
  };

  const logout = () => {
    authAPI.logout();
    setUser(null);
  };

  return { user, loading, login, logout };
}

// Usage across multiple pages
function Dashboard() {
  const { user, logout } = useAuth();
  return <div>Welcome {user?.name} <button onClick={logout}>Logout</button></div>;
}

function Profile() {
  const { user } = useAuth();
  return <div>Profile: {user?.email}</div>;
}

// 2. Context for global shared state
const AppContext = createContext();

function AppProvider({ children }) {
  const [theme, setTheme] = useState('light');
  const [notifications, setNotifications] = useState([]);
  
  const addNotification = (message) => {
    setNotifications(prev => [...prev, { id: Date.now(), message }]);
  };

  return (
    <AppContext.Provider value={{ theme, setTheme, notifications, addNotification }}>
      {children}
    </AppContext.Provider>
  );
}

// 3. Utility functions for pure logic
export const formatCurrency = (amount) => 
  new Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD' }).format(amount);

export const validateEmail = (email) => 
  /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);
```

### 7. What is Render Props Pattern?

**Render props** is a pattern where a component receives a function as a prop that returns JSX. It allows sharing stateful logic while giving consumers control over what to render.

**Key benefits:**
- **Flexible rendering** - Consumer decides what to render
- **Logic reuse** - Share behavior without UI assumptions
- **Inversion of control** - Component provides data, consumer provides UI

```jsx
// Render props component for mouse tracking
function MouseTracker({ render }) {
  const [position, setPosition] = useState({ x: 0, y: 0 });

  useEffect(() => {
    const handleMouseMove = (e) => {
      setPosition({ x: e.clientX, y: e.clientY });
    };

    window.addEventListener('mousemove', handleMouseMove);
    return () => window.removeEventListener('mousemove', handleMouseMove);
  }, []);

  return render(position); // Call the render prop with data
}

// Usage with different UI implementations
function App() {
  return (
    <div>
      {/* Render as coordinates */}
      <MouseTracker 
        render={({ x, y }) => (
          <p>Mouse position: {x}, {y}</p>
        )}
      />
      
      {/* Render as following dot */}
      <MouseTracker 
        render={({ x, y }) => (
          <div 
            style={{
              position: 'absolute',
              left: x,
              top: y,
              width: 10,
              height: 10,
              backgroundColor: 'red',
              borderRadius: '50%'
            }}
          />
        )}
      />
    </div>
  );
}

// Data fetching with render props
function DataFetcher({ url, render }) {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    fetch(url)
      .then(res => res.json())
      .then(setData)
      .catch(setError)
      .finally(() => setLoading(false));
  }, [url]);

  return render({ data, loading, error });
}

// Usage
<DataFetcher 
  url="/api/users" 
  render={({ data, loading, error }) => {
    if (loading) return <div>Loading...</div>;
    if (error) return <div>Error: {error.message}</div>;
    return <UserList users={data} />;
  }}
/>
```

### 8. What is Compound Component Pattern?

**Compound components** work together as a cohesive unit, sharing implicit state. The parent component manages state while child components have specific roles.

**Benefits:**
- **Flexible composition** - Arrange child components as needed
- **Implicit communication** - Children access parent state automatically
- **Clean API** - Intuitive component relationships

```jsx
// Compound component for tabs
const TabsContext = createContext();

function Tabs({ children, defaultTab = 0 }) {
  const [activeTab, setActiveTab] = useState(defaultTab);
  
  return (
    <TabsContext.Provider value={{ activeTab, setActiveTab }}>
      <div className="tabs">{children}</div>
    </TabsContext.Provider>
  );
}

function TabList({ children }) {
  return <div className="tab-list">{children}</div>;
}

function Tab({ index, children }) {
  const { activeTab, setActiveTab } = useContext(TabsContext);
  const isActive = activeTab === index;
  
  return (
    <button 
      className={`tab ${isActive ? 'tab--active' : ''}`}
      onClick={() => setActiveTab(index)}
    >
      {children}
    </button>
  );
}

function TabPanels({ children }) {
  return <div className="tab-panels">{children}</div>;
}

function TabPanel({ index, children }) {
  const { activeTab } = useContext(TabsContext);
  
  if (activeTab !== index) return null;
  
  return <div className="tab-panel">{children}</div>;
}

// Attach child components to parent
Tabs.List = TabList;
Tabs.Tab = Tab;
Tabs.Panels = TabPanels;
Tabs.Panel = TabPanel;

// Usage - flexible composition
function App() {
  return (
    <Tabs defaultTab={0}>
      <Tabs.List>
        <Tabs.Tab index={0}>Profile</Tabs.Tab>
        <Tabs.Tab index={1}>Settings</Tabs.Tab>
        <Tabs.Tab index={2}>Billing</Tabs.Tab>
      </Tabs.List>
      
      <Tabs.Panels>
        <Tabs.Panel index={0}>
          <ProfileContent />
        </Tabs.Panel>
        <Tabs.Panel index={1}>
          <SettingsContent />
        </Tabs.Panel>
        <Tabs.Panel index={2}>
          <BillingContent />
        </Tabs.Panel>
      </Tabs.Panels>
    </Tabs>
  );
}

// Accordion compound component
function Accordion({ children }) {
  const [openItems, setOpenItems] = useState(new Set());
  
  const toggle = (id) => {
    setOpenItems(prev => {
      const newSet = new Set(prev);
      if (newSet.has(id)) {
        newSet.delete(id);
      } else {
        newSet.add(id);
      }
      return newSet;
    });
  };
  
  return (
    <AccordionContext.Provider value={{ openItems, toggle }}>
      <div className="accordion">{children}</div>
    </AccordionContext.Provider>
  );
}

Accordion.Item = function AccordionItem({ id, children }) {
  return <div className="accordion-item">{children}</div>;
};

Accordion.Header = function AccordionHeader({ id, children }) {
  const { openItems, toggle } = useContext(AccordionContext);
  const isOpen = openItems.has(id);
  
  return (
    <button 
      className="accordion-header" 
      onClick={() => toggle(id)}
    >
      {children}
      <span>{isOpen ? '−' : '+'}</span>
    </button>
  );
};

Accordion.Content = function AccordionContent({ id, children }) {
  const { openItems } = useContext(AccordionContext);
  const isOpen = openItems.has(id);
  
  return isOpen ? <div className="accordion-content">{children}</div> : null;
};
```

### 9. What are Higher-Order Components (HOCs)?

**Higher-Order Components** are functions that take a component and return a new component with additional props or behavior. They're used for cross-cutting concerns like authentication, logging, or data fetching.

**Common use cases:**
- **Authentication** - Protect routes and components
- **Loading states** - Add loading behavior
- **Error boundaries** - Wrap components with error handling
- **Analytics** - Track component usage

```jsx
// Authentication HOC
function withAuth(WrappedComponent) {
  return function AuthenticatedComponent(props) {
    const [user, setUser] = useState(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
      checkAuthStatus().then(userData => {
        setUser(userData);
        setLoading(false);
      });
    }, []);

    if (loading) return <div>Loading...</div>;
    if (!user) return <LoginForm />;
    
    return <WrappedComponent {...props} user={user} />;
  };
}

// Usage
const ProtectedDashboard = withAuth(Dashboard);
const ProtectedProfile = withAuth(Profile);

function Dashboard({ user }) {
  return <h1>Welcome to Dashboard, {user.name}!</h1>;
}

// Loading HOC
function withLoading(WrappedComponent) {
  return function LoadingComponent({ isLoading, ...props }) {
    if (isLoading) {
      return <div className="spinner">Loading...</div>;
    }
    
    return <WrappedComponent {...props} />;
  };
}

// Error boundary HOC
function withErrorBoundary(WrappedComponent) {
  return class extends Component {
    constructor(props) {
      super(props);
      this.state = { hasError: false, error: null };
    }

    static getDerivedStateFromError(error) {
      return { hasError: true, error };
    }

    componentDidCatch(error, errorInfo) {
      console.error('Error caught by HOC:', error, errorInfo);
    }

    render() {
      if (this.state.hasError) {
        return (
          <div className="error-fallback">
            <h2>Something went wrong</h2>
            <button onClick={() => this.setState({ hasError: false, error: null })}>
              Try again
            </button>
          </div>
        );
      }

      return <WrappedComponent {...this.props} />;
    }
  };
}

// Compose multiple HOCs
const EnhancedUserList = withErrorBoundary(
  withAuth(
    withLoading(UserList)
  )
);

// Modern alternative with custom hooks
function useAuth() {
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    checkAuthStatus().then(userData => {
      setUser(userData);
      setLoading(false);
    });
  }, []);

  return { user, loading };
}

// Component using hook instead of HOC
function Dashboard() {
  const { user, loading } = useAuth();
  
  if (loading) return <div>Loading...</div>;
  if (!user) return <LoginForm />;
  
  return <h1>Welcome to Dashboard, {user.name}!</h1>;
}
```

### Pattern Comparison

| Pattern | Best For | Pros | Cons |
|---------|----------|------|------|
| Custom Hooks | Stateful logic sharing | Simple, composable | React-specific |
| Render Props | Flexible UI sharing | Very flexible | Can be verbose |
| Compound Components | Related component groups | Intuitive API | More complex setup |
| HOCs | Cross-cutting concerns | Reusable, composable | Wrapper hell, prop drilling |

### Best Practices Checklist

```jsx
// ✅ Use custom hooks for modern React
const { data, loading } = useApi('/api/users');

// ✅ Render props for flexible rendering
<DataFetcher render={({ data }) => <UserList users={data} />} />

// ✅ Compound components for related UI
<Tabs>
  <Tabs.List>
    <Tabs.Tab>Tab 1</Tabs.Tab>
  </Tabs.List>
</Tabs>

// ⚠️ HOCs are legacy but still useful
const ProtectedComponent = withAuth(MyComponent);

// ❌ Avoid deep nesting
withAuth(withLoading(withError(Component))) // Too many wrappers
```

## 🔵 9. Testing (Modern React)

### 1. What Testing Libraries Are Used in React?

**Popular React testing libraries:**
- **Jest** - Test runner and assertion library (built into Create React App)
- **React Testing Library** - Component testing utilities (recommended)
- **Vitest** - Fast alternative to Jest
- **Cypress** - End-to-end testing
- **Playwright** - Modern E2E testing

**Testing stack setup:**
```json
// package.json
{
  "devDependencies": {
    "@testing-library/react": "^13.4.0",
    "@testing-library/jest-dom": "^5.16.5",
    "@testing-library/user-event": "^14.4.3",
    "jest": "^29.0.0"
  }
}
```

```jsx
// Basic test setup
import { render, screen } from '@testing-library/react';
import '@testing-library/jest-dom';
import Button from './Button';

test('renders button with text', () => {
  render(<Button>Click me</Button>);
  expect(screen.getByRole('button', { name: /click me/i })).toBeInTheDocument();
});

// Test utilities setup
// setupTests.js
import '@testing-library/jest-dom';

// Custom render function
import { render } from '@testing-library/react';
import { BrowserRouter } from 'react-router-dom';

export function renderWithRouter(ui, options = {}) {
  return render(ui, {
    wrapper: ({ children }) => <BrowserRouter>{children}</BrowserRouter>,
    ...options
  });
}
```

### 2. Why React Testing Library Over Enzyme?

**React Testing Library advantages:**
- **User-focused testing** - Tests how users interact with components
- **Better maintainability** - Less brittle tests
- **Modern React support** - Works with hooks and concurrent features
- **Encourages best practices** - Focuses on behavior, not implementation

**Key differences:**
```jsx
// ❌ Enzyme - Implementation details
import { shallow } from 'enzyme';

const wrapper = shallow(<Counter />);
expect(wrapper.find('.count').text()).toBe('0');
wrapper.find('button').simulate('click');
expect(wrapper.state('count')).toBe(1); // Testing internal state

// ✅ React Testing Library - User behavior
import { render, screen } from '@testing-library/react';
import userEvent from '@testing-library/user-event';

test('counter increments when button clicked', async () => {
  const user = userEvent.setup();
  render(<Counter />);
  
  expect(screen.getByText('0')).toBeInTheDocument();
  await user.click(screen.getByRole('button', { name: /increment/i }));
  expect(screen.getByText('1')).toBeInTheDocument();
});

// Component being tested
function Counter() {
  const [count, setCount] = useState(0);
  return (
    <div>
      <span>{count}</span>
      <button onClick={() => setCount(count + 1)}>Increment</button>
    </div>
  );
}
```

### 3. What Should You Test vs Not Test?

**✅ What TO test:**
- **User interactions** - Clicks, form submissions, navigation
- **Conditional rendering** - Show/hide based on props or state
- **Error handling** - Error states and boundaries
- **Integration** - Component communication and data flow

**❌ What NOT to test:**
- **Implementation details** - Internal state, private methods
- **Third-party libraries** - Assume they work correctly
- **Styling** - CSS classes, inline styles (unless critical)
- **Trivial code** - Simple getters, basic utilities

```jsx
// ✅ Good tests - User behavior
test('shows error message when form submission fails', async () => {
  const user = userEvent.setup();
  render(<LoginForm />);
  
  await user.type(screen.getByLabelText(/email/i), 'invalid-email');
  await user.click(screen.getByRole('button', { name: /login/i }));
  
  expect(screen.getByText(/invalid email format/i)).toBeInTheDocument();
});

test('navigates to dashboard after successful login', async () => {
  const user = userEvent.setup();
  render(<LoginForm />);
  
  await user.type(screen.getByLabelText(/email/i), 'user@example.com');
  await user.type(screen.getByLabelText(/password/i), 'password123');
  await user.click(screen.getByRole('button', { name: /login/i }));
  
  expect(screen.getByText(/welcome to dashboard/i)).toBeInTheDocument();
});

// ❌ Bad tests - Implementation details
test('sets loading state to true when submitting', () => {
  // Don't test internal state directly
});

test('calls handleSubmit function when form submitted', () => {
  // Don't test function calls unless they have observable effects
});
```

### 4. How Do You Test Components?

**Component testing focuses on:**
- **Rendering** - Component displays correctly
- **Props** - Handles different prop values
- **Events** - User interactions work as expected
- **Accessibility** - Screen readers and keyboard navigation

```jsx
// Component to test
function UserCard({ user, onEdit, onDelete }) {
  return (
    <div data-testid="user-card">
      <h2>{user.name}</h2>
      <p>{user.email}</p>
      <button onClick={() => onEdit(user.id)}>Edit</button>
      <button onClick={() => onDelete(user.id)}>Delete</button>
    </div>
  );
}

// Test file
import { render, screen } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import UserCard from './UserCard';

const mockUser = {
  id: 1,
  name: 'John Doe',
  email: 'john@example.com'
};

test('renders user information', () => {
  render(<UserCard user={mockUser} onEdit={jest.fn()} onDelete={jest.fn()} />);
  
  expect(screen.getByText('John Doe')).toBeInTheDocument();
  expect(screen.getByText('john@example.com')).toBeInTheDocument();
});

test('calls onEdit when edit button clicked', async () => {
  const user = userEvent.setup();
  const mockOnEdit = jest.fn();
  
  render(<UserCard user={mockUser} onEdit={mockOnEdit} onDelete={jest.fn()} />);
  
  await user.click(screen.getByRole('button', { name: /edit/i }));
  expect(mockOnEdit).toHaveBeenCalledWith(1);
});

test('calls onDelete when delete button clicked', async () => {
  const user = userEvent.setup();
  const mockOnDelete = jest.fn();
  
  render(<UserCard user={mockUser} onEdit={jest.fn()} onDelete={mockOnDelete} />);
  
  await user.click(screen.getByRole('button', { name: /delete/i }));
  expect(mockOnDelete).toHaveBeenCalledWith(1);
});
```
## 🔵 10. Build Tools & Tooling

### 1. What is Vite and Why is it Faster than Webpack?

**Vite** is a modern build tool that uses native ES modules during development and Rollup for production builds. It's significantly faster than traditional bundlers.

**Why Vite is faster:**
- **No bundling in dev** - Serves files directly via ES modules
- **Hot Module Replacement (HMR)** - Updates only changed modules
- **Native ES modules** - Browser handles module loading
- **Esbuild** - Uses Go-based bundler for dependencies

```jsx
// Vite setup - vite.config.js
import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';

export default defineConfig({
  plugins: [react()],
  server: {
    port: 3000,
    open: true
  },
  build: {
    outDir: 'dist',
    sourcemap: true
  }
});

// package.json scripts
{
  "scripts": {
    "dev": "vite",
    "build": "vite build",
    "preview": "vite preview"
  }
}

// Vite development - No bundling
// Browser directly imports ES modules
import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App.jsx'; // Direct ES module import

ReactDOM.createRoot(document.getElementById('root')).render(<App />);

// Webpack development - Everything bundled
// All modules processed and bundled before serving
// Slower startup and updates
```

**Performance comparison:**
```bash
# Vite
npm run dev     # ~200ms startup
# File change   # ~50ms HMR update

# Webpack (Create React App)
npm start       # ~3-5s startup  
# File change   # ~500ms-2s update
```

### 2. What is Webpack and What Does it Do?

**Webpack** is a module bundler that takes modules with dependencies and generates static assets. It's the traditional build tool for React applications.

**What Webpack does:**
- **Module bundling** - Combines all files into bundles
- **Asset processing** - Handles CSS, images, fonts
- **Code transformation** - Babel, TypeScript compilation
- **Optimization** - Minification, tree shaking, code splitting

```javascript
// webpack.config.js
const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = {
  entry: './src/index.js',
  output: {
    path: path.resolve(__dirname, 'dist'),
    filename: '[name].[contenthash].js',
    clean: true
  },
  module: {
    rules: [
      {
        test: /\.(js|jsx)$/,
        exclude: /node_modules/,
        use: {
          loader: 'babel-loader',
          options: {
            presets: ['@babel/preset-react']
          }
        }
      },
      {
        test: /\.css$/,
        use: ['style-loader', 'css-loader']
      },
      {
        test: /\.(png|jpg|gif)$/,
        type: 'asset/resource'
      }
    ]
  },
  plugins: [
    new HtmlWebpackPlugin({
      template: './public/index.html'
    })
  ],
  devServer: {
    port: 3000,
    hot: true
  }
};

// Webpack processes everything into bundles
// Input: Multiple JS/CSS/image files
// Output: Optimized bundles
/*
dist/
├── main.a1b2c3.js     # Main application bundle
├── vendor.d4e5f6.js   # Third-party libraries
├── main.g7h8i9.css    # Styles bundle
└── index.html         # HTML with injected script tags
*/

// package.json with webpack
{
  "scripts": {
    "start": "webpack serve --mode development",
    "build": "webpack --mode production"
  }
}
```

### 3. What is Babel and Why is it Required?

**Babel** is a JavaScript compiler that transforms modern JavaScript (ES6+) and JSX into browser-compatible code. It's essential for React development.

**Why Babel is needed:**
- **JSX transformation** - Converts JSX to React.createElement calls
- **ES6+ support** - Transforms modern syntax for older browsers
- **Polyfills** - Adds missing features for target browsers
- **Plugin ecosystem** - Extensible with custom transformations

```jsx
// Before Babel (JSX + ES6)
const App = () => {
  const [count, setCount] = useState(0);
  
  return (
    <div className="app">
      <h1>Count: {count}</h1>
      <button onClick={() => setCount(count + 1)}>
        Increment
      </button>
    </div>
  );
};

// After Babel transformation
var App = function App() {
  var _useState = useState(0),
      count = _useState[0],
      setCount = _useState[1];
  
  return React.createElement(
    "div",
    { className: "app" },
    React.createElement("h1", null, "Count: ", count),
    React.createElement(
      "button",
      { onClick: function onClick() { return setCount(count + 1); } },
      "Increment"
    )
  );
};

// Babel configuration - .babelrc
{
  "presets": [
    ["@babel/preset-env", {
      "targets": {
        "browsers": ["> 1%", "last 2 versions"]
      }
    }],
    "@babel/preset-react"
  ],
  "plugins": [
    "@babel/plugin-proposal-class-properties"
  ]
}

// Modern syntax transformation
// Input (ES6+)
const users = await fetch('/api/users').then(res => res.json());
const names = users.map(user => user.name);

// Output (ES5 compatible)
function _asyncToGenerator(fn) { /* polyfill */ }
var users = _asyncToGenerator(function* () {
  return fetch('/api/users').then(function(res) { return res.json(); });
})();
var names = users.map(function(user) { return user.name; });
```

### 4. What is Tree Shaking?

**Tree shaking** eliminates unused code from the final bundle. It analyzes import/export statements to remove dead code, reducing bundle size.

**How it works:**
- **Static analysis** - Analyzes ES6 import/export statements
- **Dead code elimination** - Removes unused functions and variables
- **Bundle optimization** - Smaller final bundle size
- **Build-time process** - Happens during production build

```jsx
// Library with multiple exports - utils.js
export const formatDate = (date) => {
  return new Intl.DateTimeFormat('en-US').format(date);
};

export const formatCurrency = (amount) => {
  return new Intl.NumberFormat('en-US', { 
    style: 'currency', 
    currency: 'USD' 
  }).format(amount);
};

export const formatNumber = (num) => {
  return new Intl.NumberFormat('en-US').format(num);
};

export const validateEmail = (email) => {
  return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);
};

// App only imports what it needs
import { formatDate, formatCurrency } from './utils';

function App() {
  const price = formatCurrency(29.99);
  const date = formatDate(new Date());
  
  return <div>{price} - {date}</div>;
}

// Tree shaking result:
// ✅ formatDate and formatCurrency included in bundle
// ❌ formatNumber and validateEmail removed from bundle

// Webpack tree shaking configuration
module.exports = {
  mode: 'production', // Enables tree shaking
  optimization: {
    usedExports: true,
    sideEffects: false // Mark package as side-effect free
  }
};

// package.json - Mark as side-effect free
{
  "sideEffects": false,
  // Or specify files with side effects
  "sideEffects": ["*.css", "*.scss"]
}

// Bad for tree shaking - Default import
import * as utils from './utils'; // Imports everything
utils.formatDate(new Date());

// Good for tree shaking - Named imports
import { formatDate } from './utils'; // Only imports formatDate
formatDate(new Date());

// Lodash example
// ❌ Bad - Imports entire library
import _ from 'lodash';
_.debounce(fn, 300);

// ✅ Good - Imports only needed function
import debounce from 'lodash/debounce';
debounce(fn, 300);
```

### 5. How Does Dynamic Import Work?

**Dynamic imports** load modules on-demand at runtime, enabling code splitting and lazy loading. They return promises and help reduce initial bundle size.

**Benefits:**
- **Code splitting** - Split code into smaller chunks
- **Lazy loading** - Load components when needed
- **Reduced initial bundle** - Faster app startup
- **Route-based splitting** - Load pages on navigation

```jsx
// Dynamic import for lazy loading
import { lazy, Suspense } from 'react';

// Lazy load components
const Dashboard = lazy(() => import('./Dashboard'));
const Profile = lazy(() => import('./Profile'));
const Settings = lazy(() => import('./Settings'));

function App() {
  return (
    <Router>
      <Suspense fallback={<div>Loading...</div>}>
        <Routes>
          <Route path="/dashboard" element={<Dashboard />} />
          <Route path="/profile" element={<Profile />} />
          <Route path="/settings" element={<Settings />} />
        </Routes>
      </Suspense>
    </Router>
  );
}

// Dynamic import with conditions
function AdminPanel() {
  const [showAdvanced, setShowAdvanced] = useState(false);
  const [AdvancedComponent, setAdvancedComponent] = useState(null);

  const loadAdvancedFeatures = async () => {
    if (!AdvancedComponent) {
      const module = await import('./AdvancedFeatures');
      setAdvancedComponent(() => module.default);
    }
    setShowAdvanced(true);
  };

  return (
    <div>
      <h1>Admin Panel</h1>
      <button onClick={loadAdvancedFeatures}>
        Load Advanced Features
      </button>
      {showAdvanced && AdvancedComponent && <AdvancedComponent />}
    </div>
  );
}

// Dynamic import for utilities
async function processData(data) {
  // Only load heavy processing library when needed
  const { processLargeDataset } = await import('./heavyProcessing');
  return processLargeDataset(data);
}

// Webpack magic comments for chunk naming
const Dashboard = lazy(() => 
  import(
    /* webpackChunkName: "dashboard" */ 
    './Dashboard'
  )
);

// Preload for better UX
const Settings = lazy(() => 
  import(
    /* webpackChunkName: "settings" */
    /* webpackPreload: true */
    './Settings'
  )
);

// Bundle analysis result:
/*
main.js          - 50KB  (Core app)
dashboard.js     - 30KB  (Loaded when /dashboard visited)
profile.js       - 20KB  (Loaded when /profile visited)
settings.js      - 15KB  (Loaded when /settings visited)
*/

// Error handling with dynamic imports
function LazyComponent() {
  return (
    <Suspense 
      fallback={<div>Loading component...</div>}
    >
      <ErrorBoundary 
        fallback={<div>Failed to load component</div>}
      >
        <DynamicallyLoadedComponent />
      </ErrorBoundary>
    </Suspense>
  );
}
```

### 6. What is the Role of `.env` Files?

**Environment files** store configuration variables that change between environments (development, staging, production). They keep sensitive data out of source code.

**Common use cases:**
- **API endpoints** - Different URLs for dev/prod
- **Feature flags** - Enable/disable features per environment
- **API keys** - Store secrets securely
- **Build configuration** - Environment-specific settings

```bash
# .env (default environment)
REACT_APP_API_URL=http://localhost:3001/api
REACT_APP_APP_NAME=My React App
REACT_APP_VERSION=1.0.0

# .env.development
REACT_APP_API_URL=http://localhost:3001/api
REACT_APP_DEBUG=true
REACT_APP_MOCK_API=true

# .env.production
REACT_APP_API_URL=https://api.myapp.com
REACT_APP_DEBUG=false
REACT_APP_ANALYTICS_ID=GA-123456789

# .env.local (ignored by git, for local overrides)
REACT_APP_API_URL=http://192.168.1.100:3001/api
REACT_APP_DEBUG=true
```

```jsx
// Using environment variables in React
function App() {
  const apiUrl = process.env.REACT_APP_API_URL;
  const appName = process.env.REACT_APP_APP_NAME;
  const isDebug = process.env.REACT_APP_DEBUG === 'true';

  useEffect(() => {
    if (isDebug) {
      console.log('Debug mode enabled');
      console.log('API URL:', apiUrl);
    }
  }, []);

  return (
    <div>
      <h1>{appName}</h1>
      <ApiProvider baseUrl={apiUrl}>
        <Router />
      </ApiProvider>
    </div>
  );
}

// API service using environment variables
class ApiService {
  constructor() {
    this.baseUrl = process.env.REACT_APP_API_URL;
    this.timeout = process.env.REACT_APP_API_TIMEOUT || 5000;
  }

  async get(endpoint) {
    const response = await fetch(`${this.baseUrl}${endpoint}`, {
      timeout: this.timeout
    });
    return response.json();
  }
}

// Feature flags with environment variables
function FeatureComponent() {
  const showBetaFeature = process.env.REACT_APP_ENABLE_BETA === 'true';
  const showAnalytics = process.env.REACT_APP_ANALYTICS_ID;

  return (
    <div>
      <MainContent />
      {showBetaFeature && <BetaFeature />}
      {showAnalytics && <Analytics id={process.env.REACT_APP_ANALYTICS_ID} />}
    </div>
  );
}

// .gitignore - Don't commit sensitive files
.env.local
.env.*.local

// Environment loading priority (highest to lowest):
// 1. .env.local
// 2. .env.development / .env.production
// 3. .env

// Validation and defaults
const config = {
  apiUrl: process.env.REACT_APP_API_URL || 'http://localhost:3001/api',
  appName: process.env.REACT_APP_APP_NAME || 'React App',
  version: process.env.REACT_APP_VERSION || '1.0.0',
  debug: process.env.NODE_ENV === 'development'
};

// Type safety with TypeScript
declare global {
  namespace NodeJS {
    interface ProcessEnv {
      REACT_APP_API_URL: string;
      REACT_APP_APP_NAME: string;
      REACT_APP_DEBUG: 'true' | 'false';
    }
  }
}
```

### 7. Explain the npm start → build Workflow

**Development to production workflow** involves different processes for development server and production builds, each optimized for their specific needs.

**Development (npm start):**
- **Fast startup** - Quick development server
- **Hot reloading** - Instant updates on file changes
- **Source maps** - Easy debugging
- **No optimization** - Faster builds

**Production (npm run build):**
- **Optimization** - Minification, compression
- **Bundle splitting** - Separate vendor and app code
- **Asset hashing** - Cache busting
- **Static files** - Ready for deployment

```json
// package.json scripts
{
  "scripts": {
    "start": "react-scripts start",
    "build": "react-scripts build",
    "test": "react-scripts test",
    "eject": "react-scripts eject"
  }
}

// Development workflow (npm start)
/*
1. Start development server on port 3000
2. Enable hot module replacement (HMR)
3. Serve files from memory (no disk writes)
4. Include source maps for debugging
5. Watch for file changes and reload
*/

// Development output structure (in memory)
/*
http://localhost:3000/
├── /static/js/bundle.js        # All JS in one file
├── /static/css/main.css        # All CSS in one file
└── /                           # HTML served from memory
*/
```

```bash
# Production build workflow (npm run build)

# 1. Clean previous build
rm -rf build/

# 2. Compile and optimize
# - Babel transforms JSX and ES6+
# - Webpack bundles and optimizes
# - CSS is extracted and minified
# - Images are optimized and hashed

# 3. Generate static files
build/
├── static/
│   ├── js/
│   │   ├── main.a1b2c3.js      # App code (minified)
│   │   └── vendor.d4e5f6.js    # Third-party libraries
│   ├── css/
│   │   └── main.g7h8i9.css     # Styles (minified)
│   └── media/
│       └── logo.j1k2l3.png     # Images (optimized)
├── index.html                   # HTML with injected assets
└── asset-manifest.json         # Asset mapping for deployment

# 4. Build analysis
npm run build

# Output:
# File sizes after gzip:
#   41.2 KB  build/static/js/main.a1b2c3.js
#   15.3 KB  build/static/js/vendor.d4e5f6.js
#   2.1 KB   build/static/css/main.g7h8i9.css
```

```jsx
// Build optimizations applied

// 1. Code minification
// Before:
function calculateTotal(items) {
  return items.reduce((total, item) => {
    return total + (item.price * item.quantity);
  }, 0);
}

// After minification:
const calculateTotal=items=>items.reduce((total,item)=>total+item.price*item.quantity,0);

// 2. Tree shaking - removes unused code
// 3. Bundle splitting
const config = {
  optimization: {
    splitChunks: {
      chunks: 'all',
      cacheGroups: {
        vendor: {
          test: /[\\/]node_modules[\\/]/,
          name: 'vendors',
          chunks: 'all',
        }
      }
    }
  }
};

// 4. Asset optimization
// Images compressed and hashed
// CSS autoprefixed and minified
// HTML minified

// Deployment-ready build
// All files have content hashes for caching
// Can be served from CDN
// Optimized for production performance
```

### 8. What is CI/CD for React Applications?

**CI/CD (Continuous Integration/Continuous Deployment)** automates the process of testing, building, and deploying React applications. It ensures code quality and enables rapid, reliable releases.

**CI (Continuous Integration):**
- **Automated testing** - Run tests on every commit
- **Code quality checks** - Linting, formatting
- **Build verification** - Ensure code compiles
- **Security scanning** - Check for vulnerabilities

**CD (Continuous Deployment):**
- **Automated builds** - Create production bundles
- **Environment deployment** - Deploy to staging/production
- **Rollback capability** - Quick revert if issues
- **Monitoring** - Track deployment success

```yaml
# GitHub Actions CI/CD - .github/workflows/deploy.yml
name: Deploy React App

on:
  push:
    branches: [main]
  pull_request:
    branches: [main]

jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      
      - name: Setup Node.js
        uses: actions/setup-node@v3
        with:
          node-version: '18'
          cache: 'npm'
      
      - name: Install dependencies
        run: npm ci
      
      - name: Run tests
        run: npm test -- --coverage --watchAll=false
      
      - name: Run linting
        run: npm run lint
      
      - name: Type check
        run: npm run type-check

  build:
    needs: test
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      
      - name: Setup Node.js
        uses: actions/setup-node@v3
        with:
          node-version: '18'
          cache: 'npm'
      
      - name: Install dependencies
        run: npm ci
      
      - name: Build application
        run: npm run build
        env:
          REACT_APP_API_URL: ${{ secrets.REACT_APP_API_URL }}
          REACT_APP_ANALYTICS_ID: ${{ secrets.REACT_APP_ANALYTICS_ID }}
      
      - name: Upload build artifacts
        uses: actions/upload-artifact@v3
        with:
          name: build-files
          path: build/

  deploy:
    needs: build
    runs-on: ubuntu-latest
    if: github.ref == 'refs/heads/main'
    steps:
      - name: Download build artifacts
        uses: actions/download-artifact@v3
        with:
          name: build-files
          path: build/
      
      - name: Deploy to S3
        run: |
          aws s3 sync build/ s3://${{ secrets.S3_BUCKET }} --delete
        env:
          AWS_ACCESS_KEY_ID: ${{ secrets.AWS_ACCESS_KEY_ID }}
          AWS_SECRET_ACCESS_KEY: ${{ secrets.AWS_SECRET_ACCESS_KEY }}
      
      - name: Invalidate CloudFront
        run: |
          aws cloudfront create-invalidation \
            --distribution-id ${{ secrets.CLOUDFRONT_DISTRIBUTION_ID }} \
            --paths "/*"
```

```javascript
// Deployment configurations

// Netlify deployment - netlify.toml
[build]
  command = "npm run build"
  publish = "build"

[build.environment]
  REACT_APP_API_URL = "https://api.myapp.com"

[[redirects]]
  from = "/*"
  to = "/index.html"
  status = 200

// Vercel deployment - vercel.json
{
  "builds": [
    {
      "src": "package.json",
      "use": "@vercel/static-build",
      "config": {
        "buildCommand": "npm run build",
        "outputDirectory": "build"
      }
    }
  ],
  "routes": [
    {
      "src": "/(.*)",
      "dest": "/index.html"
    }
  ]
}

// Docker deployment - Dockerfile
FROM node:18-alpine as build

WORKDIR /app
COPY package*.json ./
RUN npm ci

COPY . .
RUN npm run build

FROM nginx:alpine
COPY --from=build /app/build /usr/share/nginx/html
COPY nginx.conf /etc/nginx/nginx.conf

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]

// Environment-specific builds
// package.json
{
  "scripts": {
    "build:dev": "REACT_APP_ENV=development npm run build",
    "build:staging": "REACT_APP_ENV=staging npm run build",
    "build:prod": "REACT_APP_ENV=production npm run build"
  }
}
```

### Build Tools Comparison

| Tool | Startup Time | HMR Speed | Bundle Size | Learning Curve |
|------|-------------|-----------|-------------|----------------|
| Vite | ~200ms | ~50ms | Small | Easy |
| Webpack | ~3-5s | ~500ms-2s | Medium | Moderate |
| Parcel | ~1-2s | ~200ms | Small | Easy |
| Rollup | N/A (build only) | N/A | Smallest | Moderate |

### Best Practices Summary

```jsx
// ✅ Good practices
// 1. Use environment variables for configuration
const apiUrl = process.env.REACT_APP_API_URL;

// 2. Implement code splitting
const LazyComponent = lazy(() => import('./LazyComponent'));

// 3. Optimize imports for tree shaking
import { debounce } from 'lodash-es';

// 4. Use proper build scripts
{
  "scripts": {
    "start": "vite",
    "build": "vite build",
    "preview": "vite preview"
  }
}

// 5. Set up CI/CD pipeline
// Automated testing, building, and deployment

// ❌ Avoid
// 1. Committing .env files with secrets
// 2. Importing entire libraries
import * as _ from 'lodash';

// 3. No code splitting for large apps
// 4. Manual deployment processes
```

## 🔵 11. SSR, Next.js & Modern Web

### 1. What is SSR in React?

**Server-Side Rendering (SSR)** renders React components on the server and sends fully-formed HTML to the browser. The client then "hydrates" the HTML to make it interactive.

**Benefits:**
- **Better SEO** - Search engines see complete HTML
- **Faster initial load** - Users see content immediately
- **Social sharing** - Meta tags work for link previews
- **Performance on slow devices** - Less JavaScript processing on client

```jsx
// Traditional CSR (Client-Side Rendering)
// Server sends empty HTML, React renders everything on client
<!DOCTYPE html>
<html>
<head><title>My App</title></head>
<body>
  <div id="root"></div> <!-- Empty initially -->
  <script src="bundle.js"></script>
</body>
</html>

// SSR - Server renders complete HTML
<!DOCTYPE html>
<html>
<head><title>User Profile - John Doe</title></head>
<body>
  <div id="root">
    <div class="profile">
      <h1>John Doe</h1>
      <p>Software Engineer</p>
      <img src="/avatar.jpg" alt="John's avatar" />
    </div>
  </div>
  <script src="bundle.js"></script>
</body>
</html>

// Basic SSR implementation with Express
import express from 'express';
import React from 'react';
import { renderToString } from 'react-dom/server';
import App from './App';

const server = express();

server.get('*', (req, res) => {
  const appHtml = renderToString(<App url={req.url} />);
  
  const html = `
    <!DOCTYPE html>
    <html>
      <head>
        <title>My SSR App</title>
      </head>
      <body>
        <div id="root">${appHtml}</div>
        <script src="/bundle.js"></script>
      </body>
    </html>
  `;
  
  res.send(html);
});

// Client-side hydration
import { hydrateRoot } from 'react-dom/client';
import App from './App';

hydrateRoot(document.getElementById('root'), <App />);
```

### 2. Difference Between CSR, SSR, SSG, and ISR

**Four rendering strategies** each optimized for different use cases:

**CSR (Client-Side Rendering):**
- Renders everything on the client
- Fast navigation after initial load
- Poor SEO and slow initial load

**SSR (Server-Side Rendering):**
- Renders on server for each request
- Good SEO, fast initial load
- Higher server load

**SSG (Static Site Generation):**
- Pre-renders pages at build time
- Fastest loading, great SEO
- Content must be known at build time

**ISR (Incremental Static Regeneration):**
- Combines SSG with on-demand regeneration
- Fast loading with fresh content
- Best of both worlds

```jsx
// CSR - Client-Side Rendering
function BlogPost({ id }) {
  const [post, setPost] = useState(null);
  
  useEffect(() => {
    fetch(`/api/posts/${id}`)
      .then(res => res.json())
      .then(setPost);
  }, [id]);
  
  if (!post) return <div>Loading...</div>;
  return <article>{post.content}</article>;
}

// SSR - Server-Side Rendering (Next.js)
export async function getServerSideProps({ params }) {
  const post = await fetch(`${API_URL}/posts/${params.id}`).then(res => res.json());
  
  return {
    props: { post }
  };
}

function BlogPost({ post }) {
  return <article>{post.content}</article>;
}

// SSG - Static Site Generation (Next.js)
export async function getStaticProps({ params }) {
  const post = await fetch(`${API_URL}/posts/${params.id}`).then(res => res.json());
  
  return {
    props: { post },
    revalidate: false // Never revalidate
  };
}

export async function getStaticPaths() {
  const posts = await fetch(`${API_URL}/posts`).then(res => res.json());
  
  return {
    paths: posts.map(post => ({ params: { id: post.id } })),
    fallback: false
  };
}

// ISR - Incremental Static Regeneration (Next.js)
export async function getStaticProps({ params }) {
  const post = await fetch(`${API_URL}/posts/${params.id}`).then(res => res.json());
  
  return {
    props: { post },
    revalidate: 3600 // Revalidate every hour
  };
}

export async function getStaticPaths() {
  return {
    paths: [], // Generate pages on-demand
    fallback: 'blocking'
  };
}
```

**Comparison Table:**

| Strategy | When to Use | Pros | Cons |
|----------|-------------|------|------|
| **CSR** | SPAs, dashboards | Fast navigation, simple deployment | Poor SEO, slow initial load |
| **SSR** | Dynamic content, personalized pages | Good SEO, fast initial load | Higher server costs |
| **SSG** | Blogs, marketing sites | Fastest loading, cheap hosting | Build time increases with pages |
| **ISR** | E-commerce, news sites | Fast + fresh content | More complex setup |

### 3. What is Selective Hydration?

**Selective Hydration** allows React to hydrate components independently and prioritize interactive components based on user interactions. It's part of React 18's concurrent features.

**Benefits:**
- **Faster interactivity** - Critical components hydrate first
- **Better user experience** - Responds to user actions immediately
- **Progressive enhancement** - Page becomes interactive gradually
- **Automatic prioritization** - React decides what to hydrate first

```jsx
// React 18 Selective Hydration with Suspense
import { Suspense } from 'react';
import { hydrateRoot } from 'react-dom/client';

function App() {
  return (
    <div>
      {/* This hydrates immediately */}
      <Header />
      
      {/* These hydrate when needed or when resources are available */}
      <Suspense fallback={<div>Loading comments...</div>}>
        <Comments />
      </Suspense>
      
      <Suspense fallback={<div>Loading sidebar...</div>}>
        <Sidebar />
      </Suspense>
      
      {/* This hydrates immediately (critical) */}
      <MainContent />
    </div>
  );
}

// Hydration prioritization
hydrateRoot(document.getElementById('root'), <App />);

// How it works:
// 1. User sees server-rendered HTML immediately
// 2. React starts hydrating Header and MainContent first
// 3. If user clicks on Comments, React prioritizes Comments hydration
// 4. Sidebar hydrates when resources are available
// 5. Each component becomes interactive as it hydrates

// Before React 18 - All or nothing hydration
function OldApp() {
  // Everything must hydrate before anything is interactive
  return (
    <div>
      <Header />
      <Comments /> {/* Blocks interactivity */}
      <Sidebar />  {/* Blocks interactivity */}
      <MainContent />
    </div>
  );
}

// Lazy component with selective hydration
const LazyChart = lazy(() => import('./Chart'));

function Dashboard() {
  return (
    <div>
      <QuickActions /> {/* Hydrates first - user needs this */}
      
      <Suspense fallback={<ChartSkeleton />}>
        <LazyChart /> {/* Hydrates when visible or needed */}
      </Suspense>
      
      <RecentActivity /> {/* Hydrates progressively */}
    </div>
  );
}
```

### 4. What is Lazy Hydration?

**Lazy Hydration** defers the hydration of components until they're needed, typically when they become visible or when user interacts with them. It reduces initial JavaScript execution.

**Strategies:**
- **Intersection Observer** - Hydrate when component enters viewport
- **Interaction-based** - Hydrate on first user interaction
- **Idle time** - Hydrate when browser is idle
- **Manual triggers** - Hydrate based on custom conditions

```jsx
// Intersection Observer lazy hydration
import { useState, useEffect, useRef } from 'react';

function LazyHydratedComponent({ children, fallback }) {
  const [shouldHydrate, setShouldHydrate] = useState(false);
  const ref = useRef();

  useEffect(() => {
    const observer = new IntersectionObserver(
      ([entry]) => {
        if (entry.isIntersecting) {
          setShouldHydrate(true);
          observer.disconnect();
        }
      },
      { threshold: 0.1 }
    );

    if (ref.current) {
      observer.observe(ref.current);
    }

    return () => observer.disconnect();
  }, []);

  return (
    <div ref={ref}>
      {shouldHydrate ? children : fallback}
    </div>
  );
}

// Usage
function App() {
  return (
    <div>
      <Header /> {/* Hydrates immediately */}
      
      <LazyHydratedComponent fallback={<div>Chart placeholder</div>}>
        <ExpensiveChart /> {/* Hydrates when visible */}
      </LazyHydratedComponent>
      
      <LazyHydratedComponent fallback={<div>Comments placeholder</div>}>
        <Comments /> {/* Hydrates when scrolled into view */}
      </LazyHydratedComponent>
    </div>
  );
}

// Interaction-based lazy hydration
function InteractionLazyComponent({ children }) {
  const [isHydrated, setIsHydrated] = useState(false);
  
  const handleInteraction = () => {
    if (!isHydrated) {
      setIsHydrated(true);
    }
  };

  if (!isHydrated) {
    return (
      <div 
        onMouseEnter={handleInteraction}
        onFocus={handleInteraction}
        onClick={handleInteraction}
      >
        {/* Static HTML version */}
        <div className="interactive-placeholder">
          Click to activate
        </div>
      </div>
    );
  }

  return children;
}

// Idle time hydration
function IdleLazyComponent({ children, fallback }) {
  const [shouldHydrate, setShouldHydrate] = useState(false);

  useEffect(() => {
    const timeoutId = setTimeout(() => {
      setShouldHydrate(true);
    }, 2000); // Hydrate after 2 seconds of idle time

    return () => clearTimeout(timeoutId);
  }, []);

  return shouldHydrate ? children : fallback;
}

// Custom hook for lazy hydration
function useLazyHydration(strategy = 'intersection') {
  const [shouldHydrate, setShouldHydrate] = useState(false);
  const ref = useRef();

  useEffect(() => {
    if (strategy === 'intersection') {
      const observer = new IntersectionObserver(([entry]) => {
        if (entry.isIntersecting) {
          setShouldHydrate(true);
        }
      });
      
      if (ref.current) observer.observe(ref.current);
      return () => observer.disconnect();
    }
    
    if (strategy === 'idle') {
      const id = requestIdleCallback(() => setShouldHydrate(true));
      return () => cancelIdleCallback(id);
    }
  }, [strategy]);

  return { shouldHydrate, ref };
}
```

### 5. What is Next.js and Why Use It?

**Next.js** is a React framework that provides production-ready features like SSR, SSG, routing, and optimization out of the box. It simplifies React development with conventions and built-in features.

**Key features:**
- **Multiple rendering modes** - SSR, SSG, ISR, CSR
- **File-based routing** - No need for route configuration
- **API routes** - Full-stack development
- **Built-in optimizations** - Images, fonts, scripts
- **TypeScript support** - Zero configuration setup

```jsx
// File-based routing structure
pages/
├── index.js           // Route: /
├── about.js          // Route: /about
├── blog/
│   ├── index.js      // Route: /blog
│   └── [slug].js     // Route: /blog/[slug]
└── api/
    └── users.js      // API endpoint: /api/users

// Dynamic routing - pages/blog/[slug].js
export default function BlogPost({ post }) {
  return (
    <article>
      <h1>{post.title}</h1>
      <p>{post.content}</p>
    </article>
  );
}

export async function getStaticProps({ params }) {
  const post = await fetch(`${API_URL}/posts/${params.slug}`)
    .then(res => res.json());
  
  return {
    props: { post },
    revalidate: 3600 // ISR - revalidate every hour
  };
}

export async function getStaticPaths() {
  const posts = await fetch(`${API_URL}/posts`).then(res => res.json());
  
  return {
    paths: posts.map(post => ({ params: { slug: post.slug } })),
    fallback: 'blocking'
  };
}

// API Routes - pages/api/users.js
export default function handler(req, res) {
  if (req.method === 'GET') {
    res.status(200).json({ users: [] });
  } else if (req.method === 'POST') {
    // Create user logic
    res.status(201).json({ message: 'User created' });
  }
}

// Built-in optimizations
import Image from 'next/image';
import Link from 'next/link';
import Head from 'next/head';

function HomePage() {
  return (
    <>
      <Head>
        <title>My Next.js App</title>
        <meta name="description" content="Built with Next.js" />
      </Head>
      
      <main>
        {/* Optimized image loading */}
        <Image
          src="/hero.jpg"
          alt="Hero image"
          width={800}
          height={400}
          priority
        />
        
        {/* Client-side navigation */}
        <Link href="/about">
          <a>About Us</a>
        </Link>
      </main>
    </>
  );
}

// Next.js configuration - next.config.js
module.exports = {
  reactStrictMode: true,
  images: {
    domains: ['example.com'],
  },
  env: {
    CUSTOM_KEY: process.env.CUSTOM_KEY,
  },
  async redirects() {
    return [
      {
        source: '/old-page',
        destination: '/new-page',
        permanent: true,
      },
    ];
  },
};
```

### 6. What are React Server Components (RSC)?

**React Server Components** run on the server and render to a special format that can be streamed to the client. They have zero bundle size impact and can directly access server resources.

**Benefits:**
- **Zero bundle size** - Server components don't ship to client
- **Direct data access** - Can read files, databases directly
- **Better performance** - Less JavaScript on client
- **Automatic code splitting** - Only client components bundled

```jsx
// Server Component (runs on server)
// No 'use client' directive = Server Component
async function BlogPost({ slug }) {
  // Direct database access (only possible on server)
  const post = await db.posts.findUnique({
    where: { slug }
  });
  
  // Direct file system access
  const readingTime = await calculateReadingTime(post.content);
  
  return (
    <article>
      <h1>{post.title}</h1>
      <p>Reading time: {readingTime} minutes</p>
      <div>{post.content}</div>
      
      {/* Client Component for interactivity */}
      <LikeButton postId={post.id} />
    </article>
  );
}

// Client Component (runs on client)
'use client';

import { useState } from 'react';

function LikeButton({ postId }) {
  const [liked, setLiked] = useState(false);
  const [count, setCount] = useState(0);
  
  const handleLike = async () => {
    setLiked(!liked);
    // API call to update likes
    const response = await fetch(`/api/posts/${postId}/like`, {
      method: 'POST'
    });
    const data = await response.json();
    setCount(data.likes);
  };
  
  return (
    <button onClick={handleLike}>
      {liked ? '❤️' : '🤍'} {count} likes
    </button>
  );
}

// Mixed Server and Client Components
function UserDashboard({ userId }) {
  // Server Component - runs on server
  return (
    <div>
      <UserProfile userId={userId} /> {/* Server Component */}
      <UserStats userId={userId} />    {/* Server Component */}
      <InteractiveChart />             {/* Client Component */}
    </div>
  );
}

// Server Component with data fetching
async function UserProfile({ userId }) {
  // This runs on the server, no fetch needed
  const user = await getUserFromDatabase(userId);
  
  return (
    <div>
      <h2>{user.name}</h2>
      <p>{user.email}</p>
      <img src={user.avatar} alt={user.name} />
    </div>
  );
}

// Client Component for interactivity
'use client';

function InteractiveChart() {
  const [data, setData] = useState([]);
  
  useEffect(() => {
    // Client-side data fetching
    fetch('/api/chart-data')
      .then(res => res.json())
      .then(setData);
  }, []);
  
  return <Chart data={data} />;
}
```

### 7. Difference Between `use client` and `use server`

**Directives** that tell React where components should run:

**`'use client'`:**
- Component runs on the client (browser)
- Can use hooks, event handlers, browser APIs
- Included in JavaScript bundle
- Interactive and stateful

**`'use server'`:**
- Function runs on the server
- Used for Server Actions (form submissions, mutations)
- Never sent to client
- Can access server resources directly

```jsx
// 'use client' - Client Component
'use client';

import { useState, useEffect } from 'react';

function SearchBox() {
  const [query, setQuery] = useState('');
  const [results, setResults] = useState([]);
  
  // Browser APIs available
  useEffect(() => {
    const params = new URLSearchParams(window.location.search);
    setQuery(params.get('q') || '');
  }, []);
  
  // Event handlers work
  const handleSearch = (e) => {
    setQuery(e.target.value);
    // Client-side search logic
  };
  
  return (
    <div>
      <input 
        value={query} 
        onChange={handleSearch}
        placeholder="Search..."
      />
      <SearchResults results={results} />
    </div>
  );
}

// Server Component (no directive needed)
async function ProductList({ category }) {
  // Direct database access
  const products = await db.products.findMany({
    where: { category }
  });
  
  return (
    <div>
      {products.map(product => (
        <ProductCard key={product.id} product={product} />
      ))}
    </div>
  );
}

// 'use server' - Server Action
'use server';

async function createPost(formData) {
  // This function runs on the server
  const title = formData.get('title');
  const content = formData.get('content');
  
  // Direct database access
  const post = await db.posts.create({
    data: { title, content }
  });
  
  // Server-side redirect
  redirect(`/posts/${post.id}`);
}

// Form using Server Action
function CreatePostForm() {
  return (
    <form action={createPost}>
      <input name="title" placeholder="Post title" />
      <textarea name="content" placeholder="Post content" />
      <button type="submit">Create Post</button>
    </form>
  );
}

// Mixed usage example
function BlogApp() {
  return (
    <div>
      {/* Server Component - renders on server */}
      <BlogHeader />
      
      {/* Server Component with data */}
      <RecentPosts />
      
      {/* Client Component - interactive */}
      <SearchBox />
      
      {/* Form with Server Action */}
      <CreatePostForm />
    </div>
  );
}

// Component boundaries
'use client';

function ClientWrapper({ children }) {
  // This is a Client Component
  const [mounted, setMounted] = useState(false);
  
  useEffect(() => {
    setMounted(true);
  }, []);
  
  if (!mounted) return null;
  
  return (
    <div>
      {/* children can be Server Components */}
      {children}
    </div>
  );
}

// Server Component passed as children
function App() {
  return (
    <ClientWrapper>
      <ServerComponentContent /> {/* Still runs on server */}
    </ClientWrapper>
  );
}
```

### Rendering Strategies Comparison

| Strategy | Execution | Bundle Impact | Use Cases |
|----------|-----------|---------------|-----------|
| **Client Components** | Browser | Increases bundle | Interactive UI, hooks, events |
| **Server Components** | Server | Zero impact | Data fetching, static content |
| **Server Actions** | Server | Zero impact | Form submissions, mutations |
| **Static Generation** | Build time | Zero runtime | Static content, blogs |

### Best Practices Summary

```jsx
// ✅ Good practices

// 1. Use Server Components by default
function BlogPost({ slug }) {
  // No 'use client' = Server Component
  return <article>...</article>;
}

// 2. Add 'use client' only when needed
'use client';
function InteractiveButton() {
  const [clicked, setClicked] = useState(false);
  return <button onClick={() => setClicked(true)}>...</button>;
}

// 3. Keep Client Components small and focused
'use client';
function LikeButton({ postId }) {
  // Minimal interactive functionality
}

// 4. Use Server Actions for mutations
'use server';
async function updateUser(formData) {
  // Server-side logic
}

// ❌ Avoid
// 1. Don't use 'use client' everywhere
'use client'; // Only add when you need interactivity

// 2. Don't fetch data in Client Components when Server Components can do it
// Bad: Client Component fetching data
// Good: Server Component with direct access
```

## 🔴 12. Security & Real-World Scenarios

### 1. How Does React Prevent XSS?

**React prevents XSS (Cross-Site Scripting)** by automatically escaping values in JSX and providing safe APIs for dynamic content. However, developers must still follow security best practices.

**Built-in protections:**
- **Automatic escaping** - JSX expressions are escaped by default
- **Safe by default** - String values can't execute as code
- **No innerHTML by default** - Prevents script injection
- **Controlled rendering** - React controls DOM updates

```jsx
// ✅ Safe - React automatically escapes
function UserProfile({ user }) {
  const maliciousInput = "<script>alert('XSS')</script>";
  
  return (
    <div>
      {/* This is safe - React escapes the content */}
      <h1>{user.name}</h1>
      <p>{maliciousInput}</p> {/* Renders as text, not HTML */}
    </div>
  );
}

// ❌ Dangerous - dangerouslySetInnerHTML bypasses protection
function UnsafeComponent({ htmlContent }) {
  return (
    <div 
      dangerouslySetInnerHTML={{ __html: htmlContent }} 
    />
  );
}

// ✅ Safe alternative - Use a sanitization library
import DOMPurify from 'dompurify';

function SafeHtmlComponent({ htmlContent }) {
  const sanitizedHtml = DOMPurify.sanitize(htmlContent);
  
  return (
    <div 
      dangerouslySetInnerHTML={{ __html: sanitizedHtml }} 
    />
  );
}

// ✅ Safe URL handling
function LinkComponent({ url, children }) {
  // Validate URLs to prevent javascript: protocol
  const isValidUrl = (url) => {
    try {
      const parsed = new URL(url);
      return ['http:', 'https:', 'mailto:'].includes(parsed.protocol);
    } catch {
      return false;
    }
  };
  
  if (!isValidUrl(url)) {
    return <span>{children}</span>; // Don't render invalid links
  }
  
  return <a href={url}>{children}</a>;
}

// ✅ Safe form handling
function CommentForm() {
  const [comment, setComment] = useState('');
  
  const handleSubmit = (e) => {
    e.preventDefault();
    
    // Validate and sanitize on both client and server
    const sanitizedComment = comment.trim().slice(0, 500);
    
    if (sanitizedComment) {
      submitComment(sanitizedComment);
    }
  };
  
  return (
    <form onSubmit={handleSubmit}>
      <textarea 
        value={comment}
        onChange={(e) => setComment(e.target.value)}
        maxLength={500}
      />
      <button type="submit">Submit</button>
    </form>
  );
}
```

### 2. Is React Safe from SQL Injection?

**React itself doesn't interact with databases** - it's a frontend library. SQL injection happens on the backend when user input is directly concatenated into SQL queries. React can help by validating input before sending to the server.

**Frontend responsibilities:**
- **Input validation** - Validate data before sending
- **Type checking** - Ensure correct data types
- **Sanitization** - Clean user input
- **Proper API calls** - Use parameterized requests

```jsx
// ✅ Frontend validation and sanitization
function UserSearchForm() {
  const [searchTerm, setSearchTerm] = useState('');
  const [users, setUsers] = useState([]);
  
  const handleSearch = async (e) => {
    e.preventDefault();
    
    // Frontend validation
    const sanitizedTerm = searchTerm.trim();
    if (sanitizedTerm.length < 2) {
      alert('Search term must be at least 2 characters');
      return;
    }
    
    // Validate input format
    if (!/^[a-zA-Z0-9\s]+$/.test(sanitizedTerm)) {
      alert('Invalid characters in search term');
      return;
    }
    
    try {
      // Send clean data to backend
      const response = await fetch('/api/users/search', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ 
          searchTerm: sanitizedTerm,
          limit: 10 
        })
      });
      
      const data = await response.json();
      setUsers(data.users);
    } catch (error) {
      console.error('Search failed:', error);
    }
  };
  
  return (
    <form onSubmit={handleSearch}>
      <input 
        type="text"
        value={searchTerm}
        onChange={(e) => setSearchTerm(e.target.value)}
        placeholder="Search users..."
        maxLength={50}
      />
      <button type="submit">Search</button>
    </form>
  );
}

// ✅ Backend protection (Node.js example)
// This is where SQL injection is actually prevented
app.post('/api/users/search', async (req, res) => {
  const { searchTerm } = req.body;
  
  // Server-side validation
  if (!searchTerm || typeof searchTerm !== 'string') {
    return res.status(400).json({ error: 'Invalid search term' });
  }
  
  // ✅ Safe - Using parameterized queries
  const users = await db.query(
    'SELECT id, name, email FROM users WHERE name LIKE ? LIMIT ?',
    [`%${searchTerm}%`, 10]
  );
  
  res.json({ users });
});

// ❌ Vulnerable backend code (DON'T DO THIS)
app.post('/api/users/search', async (req, res) => {
  const { searchTerm } = req.body;
  
  // DANGEROUS - Direct string concatenation
  const query = `SELECT * FROM users WHERE name = '${searchTerm}'`;
  const users = await db.query(query);
  
  res.json({ users });
});

// ✅ Input validation hook
function useInputValidation() {
  const validateEmail = (email) => {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
  };
  
  const validatePassword = (password) => {
    return password.length >= 8 && /[A-Z]/.test(password) && /[0-9]/.test(password);
  };
  
  const sanitizeString = (str) => {
    return str.trim().replace(/[<>\"']/g, '');
  };
  
  return { validateEmail, validatePassword, sanitizeString };
}
```

### 3. How Do You Securely Store Tokens?

**Token storage** requires balancing security and usability. Each storage method has different security implications and use cases.

**Storage options:**
- **Memory (state)** - Most secure, lost on refresh
- **HttpOnly cookies** - Secure, handled by server
- **localStorage** - Persistent, vulnerable to XSS
- **sessionStorage** - Session-only, vulnerable to XSS

```jsx
// ✅ Most secure - Memory storage with refresh token
function useAuth() {
  const [accessToken, setAccessToken] = useState(null);
  const [user, setUser] = useState(null);
  
  // Store access token in memory only
  const login = async (credentials) => {
    const response = await fetch('/api/auth/login', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(credentials),
      credentials: 'include' // Include HttpOnly refresh token cookie
    });
    
    const data = await response.json();
    
    // Store access token in memory
    setAccessToken(data.accessToken);
    setUser(data.user);
  };
  
  // Refresh token automatically
  useEffect(() => {
    const refreshToken = async () => {
      try {
        const response = await fetch('/api/auth/refresh', {
          method: 'POST',
          credentials: 'include' // Send HttpOnly cookie
        });
        
        if (response.ok) {
          const data = await response.json();
          setAccessToken(data.accessToken);
          setUser(data.user);
        }
      } catch (error) {
        // Redirect to login
        setAccessToken(null);
        setUser(null);
      }
    };
    
    // Refresh on app load
    refreshToken();
    
    // Refresh periodically
    const interval = setInterval(refreshToken, 14 * 60 * 1000); // 14 minutes
    
    return () => clearInterval(interval);
  }, []);
  
  const logout = async () => {
    await fetch('/api/auth/logout', {
      method: 'POST',
      credentials: 'include'
    });
    
    setAccessToken(null);
    setUser(null);
  };
  
  return { accessToken, user, login, logout };
}

// ✅ Secure API client with token
function createApiClient(getAccessToken) {
  return {
    async request(url, options = {}) {
      const token = getAccessToken();
      
      const response = await fetch(url, {
        ...options,
        headers: {
          'Content-Type': 'application/json',
          ...(token && { Authorization: `Bearer ${token}` }),
          ...options.headers
        }
      });
      
      if (response.status === 401) {
        // Token expired, trigger refresh
        window.dispatchEvent(new CustomEvent('auth:token-expired'));
      }
      
      return response;
    }
  };
}

// ⚠️ Less secure but practical - localStorage with encryption
class SecureStorage {
  constructor(secretKey) {
    this.secretKey = secretKey;
  }
  
  encrypt(data) {
    // Simple encryption (use a proper library in production)
    return btoa(JSON.stringify(data));
  }
  
  decrypt(encryptedData) {
    try {
      return JSON.parse(atob(encryptedData));
    } catch {
      return null;
    }
  }
  
  setToken(token) {
    const encrypted = this.encrypt({ token, timestamp: Date.now() });
    localStorage.setItem('auth_token', encrypted);
  }
  
  getToken() {
    const encrypted = localStorage.getItem('auth_token');
    if (!encrypted) return null;
    
    const decrypted = this.decrypt(encrypted);
    if (!decrypted) return null;
    
    // Check if token is expired (24 hours)
    const isExpired = Date.now() - decrypted.timestamp > 24 * 60 * 60 * 1000;
    if (isExpired) {
      this.removeToken();
      return null;
    }
    
    return decrypted.token;
  }
  
  removeToken() {
    localStorage.removeItem('auth_token');
  }
}

// Usage
const secureStorage = new SecureStorage('your-secret-key');

function useSecureAuth() {
  const [token, setToken] = useState(() => secureStorage.getToken());
  
  const login = async (credentials) => {
    const response = await fetch('/api/auth/login', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(credentials)
    });
    
    const data = await response.json();
    
    secureStorage.setToken(data.token);
    setToken(data.token);
  };
  
  const logout = () => {
    secureStorage.removeToken();
    setToken(null);
  };
  
  return { token, login, logout };
}
```

### 4. Difference Between localStorage, sessionStorage, and Cookies

**Three main client-side storage options** each with different characteristics, security implications, and use cases.

**Comparison:**

| Feature | localStorage | sessionStorage | Cookies |
|---------|-------------|----------------|---------|
| **Persistence** | Until cleared | Session only | Configurable expiry |
| **Capacity** | ~5-10MB | ~5-10MB | ~4KB |
| **Server Access** | No | No | Yes (automatic) |
| **XSS Vulnerable** | Yes | Yes | Yes (unless HttpOnly) |
| **CSRF Vulnerable** | No | No | Yes |

```jsx
// localStorage - Persistent storage
function useLocalStorage(key, initialValue) {
  const [value, setValue] = useState(() => {
    try {
      const item = localStorage.getItem(key);
      return item ? JSON.parse(item) : initialValue;
    } catch {
      return initialValue;
    }
  });
  
  const setStoredValue = (newValue) => {
    try {
      setValue(newValue);
      localStorage.setItem(key, JSON.stringify(newValue));
    } catch (error) {
      console.error('Failed to save to localStorage:', error);
    }
  };
  
  return [value, setStoredValue];
}

// sessionStorage - Session-only storage
function useSessionStorage(key, initialValue) {
  const [value, setValue] = useState(() => {
    try {
      const item = sessionStorage.getItem(key);
      return item ? JSON.parse(item) : initialValue;
    } catch {
      return initialValue;
    }
  });
  
  const setStoredValue = (newValue) => {
    try {
      setValue(newValue);
      sessionStorage.setItem(key, JSON.stringify(newValue));
    } catch (error) {
      console.error('Failed to save to sessionStorage:', error);
    }
  };
  
  return [value, setStoredValue];
}

// Cookies - Server-accessible storage
function useCookies() {
  const getCookie = (name) => {
    const value = `; ${document.cookie}`;
    const parts = value.split(`; ${name}=`);
    if (parts.length === 2) {
      return parts.pop().split(';').shift();
    }
    return null;
  };
  
  const setCookie = (name, value, days = 7) => {
    const expires = new Date();
    expires.setTime(expires.getTime() + days * 24 * 60 * 60 * 1000);
    document.cookie = `${name}=${value};expires=${expires.toUTCString()};path=/;SameSite=Strict`;
  };
  
  const removeCookie = (name) => {
    document.cookie = `${name}=;expires=Thu, 01 Jan 1970 00:00:00 UTC;path=/;`;
  };
  
  return { getCookie, setCookie, removeCookie };
}

// Practical usage examples
function UserPreferences() {
  // Theme persists across sessions
  const [theme, setTheme] = useLocalStorage('theme', 'light');
  
  // Form data only for current session
  const [formData, setFormData] = useSessionStorage('formData', {});
  
  // Cookies for server communication
  const { getCookie, setCookie } = useCookies();
  
  const handleThemeChange = (newTheme) => {
    setTheme(newTheme);
    // Also set cookie for server-side rendering
    setCookie('theme', newTheme, 365);
  };
  
  return (
    <div className={`app theme-${theme}`}>
      <button onClick={() => handleThemeChange(theme === 'light' ? 'dark' : 'light')}>
        Toggle Theme
      </button>
    </div>
  );
}

// Security considerations
function SecureStorageExample() {
  // ✅ Good for: User preferences, UI state
  const [preferences, setPreferences] = useLocalStorage('preferences', {});
  
  // ✅ Good for: Temporary form data, session state
  const [sessionData, setSessionData] = useSessionStorage('sessionData', {});
  
  // ⚠️ Avoid storing sensitive data in any client storage
  // ❌ Don't store: passwords, credit cards, SSNs
  // ❌ Don't store: API keys, secrets
  
  // ✅ OK to store: user ID, non-sensitive settings
  const [userId, setUserId] = useLocalStorage('userId', null);
  
  return (
    <div>
      <p>User ID: {userId}</p>
      <p>Theme: {preferences.theme}</p>
    </div>
  );
}

// Storage cleanup
function useStorageCleanup() {
  useEffect(() => {
    const cleanup = () => {
      // Clear expired data
      const expiredKeys = [];
      
      for (let i = 0; i < localStorage.length; i++) {
        const key = localStorage.key(i);
        if (key.startsWith('temp_')) {
          expiredKeys.push(key);
        }
      }
      
      expiredKeys.forEach(key => localStorage.removeItem(key));
    };
    
    // Cleanup on app start
    cleanup();
    
    // Cleanup periodically
    const interval = setInterval(cleanup, 60 * 60 * 1000); // Every hour
    
    return () => clearInterval(interval);
  }, []);
}
```

### 5. How Do You Implement Role-Based Authorization?

**Role-based authorization** controls access to features and routes based on user roles and permissions. It involves both frontend UI control and backend API protection.

**Key concepts:**
- **Roles** - User categories (admin, user, moderator)
- **Permissions** - Specific actions (read, write, delete)
- **Route protection** - Restrict access to pages
- **Component protection** - Hide/show UI elements

```jsx
// Auth context with roles
const AuthContext = createContext();

function AuthProvider({ children }) {
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);
  
  useEffect(() => {
    checkAuthStatus().then(userData => {
      setUser(userData);
      setLoading(false);
    });
  }, []);
  
  const hasRole = (role) => {
    return user?.roles?.includes(role);
  };
  
  const hasPermission = (permission) => {
    return user?.permissions?.includes(permission);
  };
  
  const hasAnyRole = (roles) => {
    return roles.some(role => hasRole(role));
  };
  
  return (
    <AuthContext.Provider value={{ 
      user, 
      loading, 
      hasRole, 
      hasPermission, 
      hasAnyRole 
    }}>
      {children}
    </AuthContext.Provider>
  );
}

// Protected Route component
function ProtectedRoute({ children, roles = [], permissions = [], fallback = null }) {
  const { user, loading, hasAnyRole, hasPermission } = useContext(AuthContext);
  
  if (loading) {
    return <div>Loading...</div>;
  }
  
  if (!user) {
    return <Navigate to="/login" replace />;
  }
  
  // Check roles
  if (roles.length > 0 && !hasAnyRole(roles)) {
    return fallback || <div>Access denied</div>;
  }
  
  // Check permissions
  if (permissions.length > 0 && !permissions.every(hasPermission)) {
    return fallback || <div>Insufficient permissions</div>;
  }
  
  return children;
}

// Route configuration with protection
function App() {
  return (
    <AuthProvider>
      <Router>
        <Routes>
          <Route path="/login" element={<Login />} />
          <Route path="/" element={<Home />} />
          
          {/* Admin only routes */}
          <Route 
            path="/admin/*" 
            element={
              <ProtectedRoute roles={['admin']}>
                <AdminDashboard />
              </ProtectedRoute>
            } 
          />
          
          {/* Moderator or Admin routes */}
          <Route 
            path="/moderation" 
            element={
              <ProtectedRoute roles={['admin', 'moderator']}>
                <ModerationPanel />
              </ProtectedRoute>
            } 
          />
          
          {/* Permission-based route */}
          <Route 
            path="/users" 
            element={
              <ProtectedRoute permissions={['users:read']}>
                <UserList />
              </ProtectedRoute>
            } 
          />
        </Routes>
      </Router>
    </AuthProvider>
  );
}

// Component-level authorization
function UserActions({ userId }) {
  const { user, hasRole, hasPermission } = useContext(AuthContext);
  
  const canEdit = hasPermission('users:write') || user.id === userId;
  const canDelete = hasRole('admin') || hasPermission('users:delete');
  
  return (
    <div>
      {canEdit && (
        <button onClick={() => editUser(userId)}>
          Edit
        </button>
      )}
      
      {canDelete && (
        <button onClick={() => deleteUser(userId)}>
          Delete
        </button>
      )}
      
      {hasRole('admin') && (
        <button onClick={() => impersonateUser(userId)}>
          Impersonate
        </button>
      )}
    </div>
  );
}

// Custom authorization hooks
function useAuth() {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error('useAuth must be used within AuthProvider');
  }
  return context;
}

function useRequireAuth(redirectTo = '/login') {
  const { user, loading } = useAuth();
  const navigate = useNavigate();
  
  useEffect(() => {
    if (!loading && !user) {
      navigate(redirectTo);
    }
  }, [user, loading, navigate, redirectTo]);
  
  return { user, loading };
}

function useRequireRole(requiredRoles) {
  const { user, hasAnyRole } = useAuth();
  const [hasAccess, setHasAccess] = useState(false);
  
  useEffect(() => {
    if (user) {
      setHasAccess(hasAnyRole(requiredRoles));
    }
  }, [user, requiredRoles, hasAnyRole]);
  
  return hasAccess;
}

// Usage in components
function AdminPanel() {
  const hasAccess = useRequireRole(['admin']);
  
  if (!hasAccess) {
    return <div>Access denied</div>;
  }
  
  return (
    <div>
      <h1>Admin Panel</h1>
      {/* Admin content */}
    </div>
  );
}

// API integration with roles
function useApiWithAuth() {
  const { user } = useAuth();
  
  const apiCall = async (url, options = {}) => {
    const response = await fetch(url, {
      ...options,
      headers: {
        'Authorization': `Bearer ${user?.token}`,
        'Content-Type': 'application/json',
        ...options.headers
      }
    });
    
    if (response.status === 403) {
      throw new Error('Insufficient permissions');
    }
    
    return response;
  };
  
  return { apiCall };
}
```

### 6. How Would You Build a Reusable Modal?

**A reusable modal** should be flexible, accessible, and easy to use across different parts of the application. It needs proper focus management, keyboard navigation, and portal rendering.

**Key features:**
- **Portal rendering** - Render outside component tree
- **Focus management** - Trap focus within modal
- **Keyboard navigation** - ESC to close, tab navigation
- **Backdrop click** - Close on outside click
- **Accessibility** - ARIA attributes, screen reader support

```jsx
// Modal component with portal
import { createPortal } from 'react-dom';
import { useEffect, useRef } from 'react';

function Modal({ 
  isOpen, 
  onClose, 
  title, 
  children, 
  size = 'medium',
  closeOnBackdrop = true,
  closeOnEscape = true 
}) {
  const modalRef = useRef();
  const previousFocusRef = useRef();
  
  // Focus management
  useEffect(() => {
    if (isOpen) {
      // Store previously focused element
      previousFocusRef.current = document.activeElement;
      
      // Focus modal
      modalRef.current?.focus();
      
      // Trap focus within modal
      const handleTabKey = (e) => {
        const focusableElements = modalRef.current?.querySelectorAll(
          'button, [href], input, select, textarea, [tabindex]:not([tabindex="-1"])'
        );
        
        if (!focusableElements?.length) return;
        
        const firstElement = focusableElements[0];
        const lastElement = focusableElements[focusableElements.length - 1];
        
        if (e.shiftKey && document.activeElement === firstElement) {
          e.preventDefault();
          lastElement.focus();
        } else if (!e.shiftKey && document.activeElement === lastElement) {
          e.preventDefault();
          firstElement.focus();
        }
      };
      
      const handleKeyDown = (e) => {
        if (e.key === 'Tab') {
          handleTabKey(e);
        } else if (e.key === 'Escape' && closeOnEscape) {
          onClose();
        }
      };
      
      document.addEventListener('keydown', handleKeyDown);
      
      return () => {
        document.removeEventListener('keydown', handleKeyDown);
        // Restore focus
        previousFocusRef.current?.focus();
      };
    }
  }, [isOpen, onClose, closeOnEscape]);
  
  // Prevent body scroll when modal is open
  useEffect(() => {
    if (isOpen) {
      document.body.style.overflow = 'hidden';
      return () => {
        document.body.style.overflow = 'unset';
      };
    }
  }, [isOpen]);
  
  if (!isOpen) return null;
  
  const handleBackdropClick = (e) => {
    if (e.target === e.currentTarget && closeOnBackdrop) {
      onClose();
    }
  };
  
  const modalContent = (
    <div 
      className="modal-overlay" 
      onClick={handleBackdropClick}
      role="dialog"
      aria-modal="true"
      aria-labelledby={title ? "modal-title" : undefined}
    >
      <div 
        ref={modalRef}
        className={`modal-content modal-${size}`}
        tabIndex={-1}
      >
        <div className="modal-header">
          {title && <h2 id="modal-title">{title}</h2>}
          <button 
            className="modal-close"
            onClick={onClose}
            aria-label="Close modal"
          >
            ×
          </button>
        </div>
        
        <div className="modal-body">
          {children}
        </div>
      </div>
    </div>
  );
  
  // Render in portal
  return createPortal(modalContent, document.body);
}

// Modal hook for easier state management
function useModal() {
  const [isOpen, setIsOpen] = useState(false);
  
  const openModal = () => setIsOpen(true);
  const closeModal = () => setIsOpen(false);
  const toggleModal = () => setIsOpen(!isOpen);
  
  return {
    isOpen,
    openModal,
    closeModal,
    toggleModal
  };
}

// Specialized modal components
function ConfirmModal({ isOpen, onClose, onConfirm, title, message }) {
  return (
    <Modal isOpen={isOpen} onClose={onClose} title={title} size="small">
      <p>{message}</p>
      <div className="modal-actions">
        <button onClick={onClose}>Cancel</button>
        <button onClick={onConfirm} className="btn-danger">
          Confirm
        </button>
      </div>
    </Modal>
  );
}

function FormModal({ isOpen, onClose, title, children, onSubmit }) {
  const handleSubmit = (e) => {
    e.preventDefault();
    onSubmit(e);
  };
  
  return (
    <Modal isOpen={isOpen} onClose={onClose} title={title}>
      <form onSubmit={handleSubmit}>
        {children}
        <div className="modal-actions">
          <button type="button" onClick={onClose}>
            Cancel
          </button>
          <button type="submit">
            Save
          </button>
        </div>
      </form>
    </Modal>
  );
}

// Usage examples
function App() {
  const confirmModal = useModal();
  const formModal = useModal();
  const [userToDelete, setUserToDelete] = useState(null);
  
  const handleDeleteUser = (user) => {
    setUserToDelete(user);
    confirmModal.openModal();
  };
  
  const confirmDelete = () => {
    deleteUser(userToDelete.id);
    confirmModal.closeModal();
    setUserToDelete(null);
  };
  
  const handleCreateUser = (formData) => {
    createUser(formData);
    formModal.closeModal();
  };
  
  return (
    <div>
      <button onClick={formModal.openModal}>
        Create User
      </button>
      
      <UserList onDeleteUser={handleDeleteUser} />
      
      {/* Confirmation Modal */}
      <ConfirmModal
        isOpen={confirmModal.isOpen}
        onClose={confirmModal.closeModal}
        onConfirm={confirmDelete}
        title="Delete User"
        message={`Are you sure you want to delete ${userToDelete?.name}?`}
      />
      
      {/* Form Modal */}
      <FormModal
        isOpen={formModal.isOpen}
        onClose={formModal.closeModal}
        onSubmit={handleCreateUser}
        title="Create New User"
      >
        <input name="name" placeholder="Name" required />
        <input name="email" type="email" placeholder="Email" required />
      </FormModal>
    </div>
  );
}

// CSS for modal styling
const modalStyles = `
.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 1000;
}

.modal-content {
  background: white;
  border-radius: 8px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  max-height: 90vh;
  overflow-y: auto;
}

.modal-small { width: 400px; }
.modal-medium { width: 600px; }
.modal-large { width: 800px; }

.modal-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 1rem;
  border-bottom: 1px solid #eee;
}

.modal-body {
  padding: 1rem;
}

.modal-actions {
  display: flex;
  gap: 0.5rem;
  justify-content: flex-end;
  margin-top: 1rem;
}
`;
```

### 7. How Would You Implement Infinite Scrolling?

**Infinite scrolling** loads more content as the user scrolls down, providing a seamless browsing experience. It uses Intersection Observer API to detect when to load more data.

**Key components:**
- **Intersection Observer** - Detect when sentinel element is visible
- **Loading states** - Show loading indicators
- **Error handling** - Handle failed requests
- **Performance** - Virtualization for large lists

```jsx
// Basic infinite scrolling hook
function useInfiniteScroll(fetchMore) {
  const [loading, setLoading] = useState(false);
  const [hasMore, setHasMore] = useState(true);
  const sentinelRef = useRef();

  useEffect(() => {
    const observer = new IntersectionObserver(
      async ([entry]) => {
        if (entry.isIntersecting && hasMore && !loading) {
          setLoading(true);
          try {
            const moreData = await fetchMore();
            if (!moreData || moreData.length === 0) {
              setHasMore(false);
            }
          } catch (error) {
            console.error('Failed to load more:', error);
          } finally {
            setLoading(false);
          }
        }
      },
      { threshold: 0.1 }
    );

    if (sentinelRef.current) {
      observer.observe(sentinelRef.current);
    }

    return () => observer.disconnect();
  }, [fetchMore, hasMore, loading]);

  return { loading, hasMore, sentinelRef };
}

// Infinite scrolling component
function InfinitePostList() {
  const [posts, setPosts] = useState([]);
  const [page, setPage] = useState(1);

  const fetchMorePosts = async () => {
    const response = await fetch(`/api/posts?page=${page}&limit=10`);
    const newPosts = await response.json();
    
    setPosts(prev => [...prev, ...newPosts]);
    setPage(prev => prev + 1);
    
    return newPosts;
  };

  const { loading, hasMore, sentinelRef } = useInfiniteScroll(fetchMorePosts);

  // Load initial data
  useEffect(() => {
    fetchMorePosts();
  }, []);

  return (
    <div className="post-list">
      {posts.map(post => (
        <PostCard key={post.id} post={post} />
      ))}
      
      {/* Sentinel element */}
      <div ref={sentinelRef} className="sentinel">
        {loading && <div>Loading more posts...</div>}
        {!hasMore && <div>No more posts to load</div>}
      </div>
    </div>
  );
}

// Advanced infinite scroll with search
function SearchableInfiniteList() {
  const [items, setItems] = useState([]);
  const [query, setQuery] = useState('');
  const [page, setPage] = useState(1);
  const [loading, setLoading] = useState(false);
  const [hasMore, setHasMore] = useState(true);
  const sentinelRef = useRef();

  const fetchItems = async (searchQuery, pageNum, reset = false) => {
    setLoading(true);
    try {
      const response = await fetch(
        `/api/search?q=${searchQuery}&page=${pageNum}&limit=20`
      );
      const newItems = await response.json();
      
      setItems(prev => reset ? newItems : [...prev, ...newItems]);
      setHasMore(newItems.length === 20); // Assuming 20 is page size
      
      return newItems;
    } finally {
      setLoading(false);
    }
  };

  // Reset when search query changes
  useEffect(() => {
    setItems([]);
    setPage(1);
    setHasMore(true);
    fetchItems(query, 1, true);
  }, [query]);

  // Intersection observer for infinite scroll
  useEffect(() => {
    const observer = new IntersectionObserver(
      ([entry]) => {
        if (entry.isIntersecting && hasMore && !loading) {
          const nextPage = page + 1;
          setPage(nextPage);
          fetchItems(query, nextPage);
        }
      },
      { threshold: 0.1 }
    );

    if (sentinelRef.current) {
      observer.observe(sentinelRef.current);
    }

    return () => observer.disconnect();
  }, [hasMore, loading, page, query]);

  return (
    <div>
      <input 
        type="text"
        value={query}
        onChange={(e) => setQuery(e.target.value)}
        placeholder="Search items..."
      />
      
      <div className="item-list">
        {items.map(item => (
          <ItemCard key={item.id} item={item} />
        ))}
      </div>
      
      <div ref={sentinelRef}>
        {loading && <div>Loading...</div>}
        {!hasMore && items.length > 0 && <div>End of results</div>}
      </div>
    </div>
  );
}

// Virtualized infinite scroll for performance
import { FixedSizeList as List } from 'react-window';
import InfiniteLoader from 'react-window-infinite-loader';

function VirtualizedInfiniteList() {
  const [items, setItems] = useState([]);
  const [hasNextPage, setHasNextPage] = useState(true);
  const [isNextPageLoading, setIsNextPageLoading] = useState(false);

  const loadNextPage = async () => {
    setIsNextPageLoading(true);
    const newItems = await fetchMoreItems();
    setItems(prev => [...prev, ...newItems]);
    setHasNextPage(newItems.length > 0);
    setIsNextPageLoading(false);
  };

  const isItemLoaded = (index) => !!items[index];
  const itemCount = hasNextPage ? items.length + 1 : items.length;

  const Item = ({ index, style }) => {
    const item = items[index];
    
    if (!item) {
      return <div style={style}>Loading...</div>;
    }
    
    return (
      <div style={style}>
        <ItemCard item={item} />
      </div>
    );
  };

  return (
    <InfiniteLoader
      isItemLoaded={isItemLoaded}
      itemCount={itemCount}
      loadMoreItems={loadNextPage}
    >
      {({ onItemsRendered, ref }) => (
        <List
          ref={ref}
          height={600}
          itemCount={itemCount}
          itemSize={100}
          onItemsRendered={onItemsRendered}
        >
          {Item}
        </List>
      )}
    </InfiniteLoader>
  );
}
```

### 8. How Would You Build a Multi-Step Form?

**Multi-step forms** break complex forms into manageable steps, improving user experience and completion rates. They need state management, validation, and navigation between steps.

**Key features:**
- **Step management** - Track current step and progress
- **Data persistence** - Maintain form data across steps
- **Validation** - Validate each step before proceeding
- **Navigation** - Allow going back and forth between steps

```jsx
// Multi-step form hook
function useMultiStepForm(steps) {
  const [currentStep, setCurrentStep] = useState(0);
  const [formData, setFormData] = useState({});
  const [completedSteps, setCompletedSteps] = useState(new Set());

  const nextStep = () => {
    if (currentStep < steps.length - 1) {
      setCompletedSteps(prev => new Set([...prev, currentStep]));
      setCurrentStep(prev => prev + 1);
    }
  };

  const prevStep = () => {
    if (currentStep > 0) {
      setCurrentStep(prev => prev - 1);
    }
  };

  const goToStep = (stepIndex) => {
    if (stepIndex >= 0 && stepIndex < steps.length) {
      setCurrentStep(stepIndex);
    }
  };

  const updateFormData = (stepData) => {
    setFormData(prev => ({ ...prev, ...stepData }));
  };

  const isStepCompleted = (stepIndex) => {
    return completedSteps.has(stepIndex);
  };

  const isFirstStep = currentStep === 0;
  const isLastStep = currentStep === steps.length - 1;

  return {
    currentStep,
    formData,
    nextStep,
    prevStep,
    goToStep,
    updateFormData,
    isStepCompleted,
    isFirstStep,
    isLastStep,
    totalSteps: steps.length
  };
}

// Multi-step form component
function MultiStepForm() {
  const steps = [
    { title: 'Personal Info', component: PersonalInfoStep },
    { title: 'Address', component: AddressStep },
    { title: 'Payment', component: PaymentStep },
    { title: 'Review', component: ReviewStep }
  ];

  const {
    currentStep,
    formData,
    nextStep,
    prevStep,
    goToStep,
    updateFormData,
    isStepCompleted,
    isFirstStep,
    isLastStep
  } = useMultiStepForm(steps);

  const [errors, setErrors] = useState({});

  const validateStep = (stepData) => {
    const stepErrors = {};
    
    switch (currentStep) {
      case 0: // Personal Info
        if (!stepData.firstName) stepErrors.firstName = 'Required';
        if (!stepData.email) stepErrors.email = 'Required';
        break;
      case 1: // Address
        if (!stepData.street) stepErrors.street = 'Required';
        if (!stepData.city) stepErrors.city = 'Required';
        break;
      case 2: // Payment
        if (!stepData.cardNumber) stepErrors.cardNumber = 'Required';
        break;
    }
    
    return stepErrors;
  };

  const handleNext = (stepData) => {
    const stepErrors = validateStep(stepData);
    
    if (Object.keys(stepErrors).length === 0) {
      updateFormData(stepData);
      setErrors({});
      nextStep();
    } else {
      setErrors(stepErrors);
    }
  };

  const handleSubmit = async (stepData) => {
    const finalData = { ...formData, ...stepData };
    
    try {
      await submitForm(finalData);
      alert('Form submitted successfully!');
    } catch (error) {
      alert('Submission failed. Please try again.');
    }
  };

  const CurrentStepComponent = steps[currentStep].component;

  return (
    <div className="multi-step-form">
      {/* Progress indicator */}
      <div className="step-indicator">
        {steps.map((step, index) => (
          <div 
            key={index}
            className={`step ${
              index === currentStep ? 'active' : 
              isStepCompleted(index) ? 'completed' : ''
            }`}
            onClick={() => goToStep(index)}
          >
            <span className="step-number">{index + 1}</span>
            <span className="step-title">{step.title}</span>
          </div>
        ))}
      </div>

      {/* Current step content */}
      <div className="step-content">
        <CurrentStepComponent
          data={formData}
          errors={errors}
          onNext={handleNext}
          onPrev={prevStep}
          onSubmit={handleSubmit}
          isFirstStep={isFirstStep}
          isLastStep={isLastStep}
        />
      </div>
    </div>
  );
}

// Individual step components
function PersonalInfoStep({ data, errors, onNext, isFirstStep }) {
  const [stepData, setStepData] = useState({
    firstName: data.firstName || '',
    lastName: data.lastName || '',
    email: data.email || ''
  });

  const handleSubmit = (e) => {
    e.preventDefault();
    onNext(stepData);
  };

  return (
    <form onSubmit={handleSubmit}>
      <h2>Personal Information</h2>
      
      <input
        type="text"
        placeholder="First Name"
        value={stepData.firstName}
        onChange={(e) => setStepData(prev => ({ ...prev, firstName: e.target.value }))}
      />
      {errors.firstName && <span className="error">{errors.firstName}</span>}
      
      <input
        type="text"
        placeholder="Last Name"
        value={stepData.lastName}
        onChange={(e) => setStepData(prev => ({ ...prev, lastName: e.target.value }))}
      />
      
      <input
        type="email"
        placeholder="Email"
        value={stepData.email}
        onChange={(e) => setStepData(prev => ({ ...prev, email: e.target.value }))}
      />
      {errors.email && <span className="error">{errors.email}</span>}
      
      <div className="form-actions">
        <button type="submit">Next</button>
      </div>
    </form>
  );
}

function AddressStep({ data, errors, onNext, onPrev }) {
  const [stepData, setStepData] = useState({
    street: data.street || '',
    city: data.city || '',
    zipCode: data.zipCode || ''
  });

  const handleSubmit = (e) => {
    e.preventDefault();
    onNext(stepData);
  };

  return (
    <form onSubmit={handleSubmit}>
      <h2>Address Information</h2>
      
      <input
        type="text"
        placeholder="Street Address"
        value={stepData.street}
        onChange={(e) => setStepData(prev => ({ ...prev, street: e.target.value }))}
      />
      {errors.street && <span className="error">{errors.street}</span>}
      
      <input
        type="text"
        placeholder="City"
        value={stepData.city}
        onChange={(e) => setStepData(prev => ({ ...prev, city: e.target.value }))}
      />
      {errors.city && <span className="error">{errors.city}</span>}
      
      <input
        type="text"
        placeholder="ZIP Code"
        value={stepData.zipCode}
        onChange={(e) => setStepData(prev => ({ ...prev, zipCode: e.target.value }))}
      />
      
      <div className="form-actions">
        <button type="button" onClick={onPrev}>Previous</button>
        <button type="submit">Next</button>
      </div>
    </form>
  );
}

function ReviewStep({ data, onSubmit, onPrev, isLastStep }) {
  const handleSubmit = (e) => {
    e.preventDefault();
    onSubmit({});
  };

  return (
    <form onSubmit={handleSubmit}>
      <h2>Review Your Information</h2>
      
      <div className="review-section">
        <h3>Personal Information</h3>
        <p>Name: {data.firstName} {data.lastName}</p>
        <p>Email: {data.email}</p>
      </div>
      
      <div className="review-section">
        <h3>Address</h3>
        <p>{data.street}</p>
        <p>{data.city}, {data.zipCode}</p>
      </div>
      
      <div className="form-actions">
        <button type="button" onClick={onPrev}>Previous</button>
        <button type="submit">Submit</button>
      </div>
    </form>
  );
}
```

### 9. How Do You Handle Form Validation in Large Forms?

**Large form validation** requires a structured approach with field-level validation, error management, and performance optimization. Use libraries like Formik or React Hook Form for complex scenarios.

**Strategies:**
- **Field-level validation** - Validate individual fields
- **Schema validation** - Use Yup or Zod for validation rules
- **Debounced validation** - Avoid excessive validation calls
- **Error boundaries** - Isolate validation errors

```jsx
// Using React Hook Form with Yup validation
import { useForm } from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';
import * as yup from 'yup';

// Validation schema
const userSchema = yup.object({
  personalInfo: yup.object({
    firstName: yup.string().required('First name is required'),
    lastName: yup.string().required('Last name is required'),
    email: yup.string().email('Invalid email').required('Email is required'),
    phone: yup.string().matches(/^\d{10}$/, 'Phone must be 10 digits')
  }),
  address: yup.object({
    street: yup.string().required('Street is required'),
    city: yup.string().required('City is required'),
    state: yup.string().required('State is required'),
    zipCode: yup.string().matches(/^\d{5}$/, 'ZIP must be 5 digits')
  }),
  preferences: yup.object({
    newsletter: yup.boolean(),
    notifications: yup.boolean(),
    theme: yup.string().oneOf(['light', 'dark'])
  })
});

// Large form component
function LargeUserForm() {
  const {
    register,
    handleSubmit,
    formState: { errors, isSubmitting, isDirty },
    watch,
    setValue,
    trigger
  } = useForm({
    resolver: yupResolver(userSchema),
    defaultValues: {
      personalInfo: {
        firstName: '',
        lastName: '',
        email: '',
        phone: ''
      },
      address: {
        street: '',
        city: '',
        state: '',
        zipCode: ''
      },
      preferences: {
        newsletter: false,
        notifications: true,
        theme: 'light'
      }
    }
  });

  // Watch for changes to trigger dependent validations
  const email = watch('personalInfo.email');

  // Debounced email validation
  useEffect(() => {
    const timeoutId = setTimeout(() => {
      if (email) {
        trigger('personalInfo.email');
      }
    }, 500);

    return () => clearTimeout(timeoutId);
  }, [email, trigger]);

  const onSubmit = async (data) => {
    try {
      await submitUserData(data);
      alert('User created successfully!');
    } catch (error) {
      alert('Failed to create user');
    }
  };

  return (
    <form onSubmit={handleSubmit(onSubmit)} className="large-form">
      {/* Personal Information Section */}
      <FormSection title="Personal Information">
        <FormField
          label="First Name"
          error={errors.personalInfo?.firstName}
        >
          <input
            {...register('personalInfo.firstName')}
            type="text"
          />
        </FormField>

        <FormField
          label="Last Name"
          error={errors.personalInfo?.lastName}
        >
          <input
            {...register('personalInfo.lastName')}
            type="text"
          />
        </FormField>

        <FormField
          label="Email"
          error={errors.personalInfo?.email}
        >
          <input
            {...register('personalInfo.email')}
            type="email"
          />
        </FormField>

        <FormField
          label="Phone"
          error={errors.personalInfo?.phone}
        >
          <input
            {...register('personalInfo.phone')}
            type="tel"
          />
        </FormField>
      </FormSection>

      {/* Address Section */}
      <FormSection title="Address">
        <FormField
          label="Street Address"
          error={errors.address?.street}
        >
          <input
            {...register('address.street')}
            type="text"
          />
        </FormField>

        <div className="form-row">
          <FormField
            label="City"
            error={errors.address?.city}
          >
            <input
              {...register('address.city')}
              type="text"
            />
          </FormField>

          <FormField
            label="State"
            error={errors.address?.state}
          >
            <select {...register('address.state')}>
              <option value="">Select State</option>
              <option value="CA">California</option>
              <option value="NY">New York</option>
              <option value="TX">Texas</option>
            </select>
          </FormField>

          <FormField
            label="ZIP Code"
            error={errors.address?.zipCode}
          >
            <input
              {...register('address.zipCode')}
              type="text"
            />
          </FormField>
        </div>
      </FormSection>

      {/* Preferences Section */}
      <FormSection title="Preferences">
        <FormField error={errors.preferences?.newsletter}>
          <label>
            <input
              {...register('preferences.newsletter')}
              type="checkbox"
            />
            Subscribe to newsletter
          </label>
        </FormField>

        <FormField error={errors.preferences?.notifications}>
          <label>
            <input
              {...register('preferences.notifications')}
              type="checkbox"
            />
            Enable notifications
          </label>
        </FormField>

        <FormField
          label="Theme"
          error={errors.preferences?.theme}
        >
          <select {...register('preferences.theme')}>
            <option value="light">Light</option>
            <option value="dark">Dark</option>
          </select>
        </FormField>
      </FormSection>

      <div className="form-actions">
        <button 
          type="submit" 
          disabled={isSubmitting || !isDirty}
        >
          {isSubmitting ? 'Saving...' : 'Save User'}
        </button>
      </div>
    </form>
  );
}

// Reusable form components
function FormSection({ title, children }) {
  return (
    <div className="form-section">
      <h3>{title}</h3>
      <div className="form-fields">
        {children}
      </div>
    </div>
  );
}

function FormField({ label, error, children }) {
  return (
    <div className="form-field">
      {label && <label>{label}</label>}
      {children}
      {error && <span className="error">{error.message}</span>}
    </div>
  );
}

// Custom validation hook for complex scenarios
function useFormValidation(schema, defaultValues) {
  const [values, setValues] = useState(defaultValues);
  const [errors, setErrors] = useState({});
  const [touched, setTouched] = useState({});

  const validateField = async (name, value) => {
    try {
      await schema.validateAt(name, { [name]: value });
      setErrors(prev => ({ ...prev, [name]: null }));
    } catch (error) {
      setErrors(prev => ({ ...prev, [name]: error.message }));
    }
  };

  const validateForm = async () => {
    try {
      await schema.validate(values, { abortEarly: false });
      setErrors({});
      return true;
    } catch (error) {
      const formErrors = {};
      error.inner.forEach(err => {
        formErrors[err.path] = err.message;
      });
      setErrors(formErrors);
      return false;
    }
  };

  const setValue = (name, value) => {
    setValues(prev => ({ ...prev, [name]: value }));
    if (touched[name]) {
      validateField(name, value);
    }
  };

  const setTouchedField = (name) => {
    setTouched(prev => ({ ...prev, [name]: true }));
  };

  return {
    values,
    errors,
    touched,
    setValue,
    setTouchedField,
    validateForm
  };
}
```

### 10. How Do You Optimize a Slow React Page?

**React performance optimization** involves identifying bottlenecks and applying appropriate optimization techniques. Use React DevTools Profiler to identify performance issues.

**Common optimizations:**
- **Memoization** - React.memo, useMemo, useCallback
- **Code splitting** - Lazy loading components
- **Virtualization** - For large lists
- **Bundle optimization** - Tree shaking, compression

```jsx
// Before optimization - Slow component
function SlowUserList({ users, searchTerm, sortBy }) {
  // ❌ Expensive calculation on every render
  const filteredUsers = users.filter(user => 
    user.name.toLowerCase().includes(searchTerm.toLowerCase())
  ).sort((a, b) => {
    if (sortBy === 'name') return a.name.localeCompare(b.name);
    if (sortBy === 'email') return a.email.localeCompare(b.email);
    return 0;
  });

  return (
    <div>
      {filteredUsers.map(user => (
        // ❌ New object created on every render
        <UserCard 
          key={user.id} 
          user={user}
          onClick={() => console.log('Clicked', user.id)}
        />
      ))}
    </div>
  );
}

// After optimization - Fast component
const OptimizedUserList = React.memo(function UserList({ users, searchTerm, sortBy }) {
  // ✅ Memoized expensive calculation
  const filteredUsers = useMemo(() => {
    return users
      .filter(user => 
        user.name.toLowerCase().includes(searchTerm.toLowerCase())
      )
      .sort((a, b) => {
        if (sortBy === 'name') return a.name.localeCompare(b.name);
        if (sortBy === 'email') return a.email.localeCompare(b.email);
        return 0;
      });
  }, [users, searchTerm, sortBy]);

  // ✅ Memoized callback
  const handleUserClick = useCallback((userId) => {
    console.log('Clicked', userId);
  }, []);

  return (
    <div>
      {filteredUsers.map(user => (
        <MemoizedUserCard 
          key={user.id} 
          user={user}
          onClick={handleUserClick}
        />
      ))}
    </div>
  );
});

// Memoized child component
const MemoizedUserCard = React.memo(function UserCard({ user, onClick }) {
  return (
    <div className="user-card" onClick={() => onClick(user.id)}>
      <img src={user.avatar} alt={user.name} loading="lazy" />
      <h3>{user.name}</h3>
      <p>{user.email}</p>
    </div>
  );
});

// Virtualized list for large datasets
import { FixedSizeList as List } from 'react-window';

function VirtualizedUserList({ users }) {
  const Row = ({ index, style }) => {
    const user = users[index];
    return (
      <div style={style}>
        <UserCard user={user} />
      </div>
    );
  };

  return (
    <List
      height={600}
      itemCount={users.length}
      itemSize={80}
      width="100%"
    >
      {Row}
    </List>
  );
}

// Lazy loading with Suspense
const LazyDashboard = lazy(() => import('./Dashboard'));
const LazyReports = lazy(() => import('./Reports'));

function App() {
  return (
    <Suspense fallback={<div>Loading...</div>}>
      <Routes>
        <Route path="/dashboard" element={<LazyDashboard />} />
        <Route path="/reports" element={<LazyReports />} />
      </Routes>
    </Suspense>
  );
}

// Image optimization
function OptimizedImage({ src, alt, ...props }) {
  const [imageSrc, setImageSrc] = useState(null);
  const [loading, setLoading] = useState(true);
  const imgRef = useRef();

  useEffect(() => {
    const observer = new IntersectionObserver(
      ([entry]) => {
        if (entry.isIntersecting) {
          setImageSrc(src);
          observer.disconnect();
        }
      },
      { threshold: 0.1 }
    );

    if (imgRef.current) {
      observer.observe(imgRef.current);
    }

    return () => observer.disconnect();
  }, [src]);

  return (
    <div ref={imgRef} className="image-container">
      {loading && <div className="image-placeholder">Loading...</div>}
      {imageSrc && (
        <img
          src={imageSrc}
          alt={alt}
          onLoad={() => setLoading(false)}
          {...props}
        />
      )}
    </div>
  );
}

// Performance monitoring hook
function usePerformanceMonitor(componentName) {
  useEffect(() => {
    const startTime = performance.now();
    
    return () => {
      const endTime = performance.now();
      const renderTime = endTime - startTime;
      
      if (renderTime > 16) { // More than one frame (60fps)
        console.warn(`${componentName} took ${renderTime.toFixed(2)}ms to render`);
      }
    };
  });
}

// Usage
function MonitoredComponent() {
  usePerformanceMonitor('MonitoredComponent');
  
  // Component logic
  return <div>Content</div>;
}

// Bundle size optimization
// Use dynamic imports for large libraries
const loadChartLibrary = async () => {
  const { Chart } = await import('chart.js');
  return Chart;
};

function ChartComponent({ data }) {
  const [Chart, setChart] = useState(null);
  
  useEffect(() => {
    loadChartLibrary().then(setChart);
  }, []);
  
  if (!Chart) return <div>Loading chart...</div>;
  
  return <Chart data={data} />;
}
```

### 11. How Would You Migrate Class Components to Hooks?

**Class to hooks migration** involves converting lifecycle methods to useEffect, state to useState, and class methods to functions. It's a systematic process that improves code readability and performance.

**Migration steps:**
1. **Convert state** - this.state to useState
2. **Convert lifecycle methods** - componentDidMount to useEffect
3. **Convert methods** - class methods to functions
4. **Handle refs** - this.refs to useRef

```jsx
// Before: Class component
class UserProfile extends Component {
  constructor(props) {
    super(props);
    this.state = {
      user: null,
      loading: true,
      error: null,
      posts: [],
      followersCount: 0
    };
    
    this.handleFollow = this.handleFollow.bind(this);
    this.handleUnfollow = this.handleUnfollow.bind(this);
  }

  async componentDidMount() {
    try {
      const [userResponse, postsResponse] = await Promise.all([
        fetch(`/api/users/${this.props.userId}`),
        fetch(`/api/users/${this.props.userId}/posts`)
      ]);
      
      const user = await userResponse.json();
      const posts = await postsResponse.json();
      
      this.setState({ 
        user, 
        posts, 
        loading: false,
        followersCount: user.followersCount 
      });
    } catch (error) {
      this.setState({ error: error.message, loading: false });
    }
  }

  componentDidUpdate(prevProps) {
    if (prevProps.userId !== this.props.userId) {
      this.fetchUserData();
    }
  }

  componentWillUnmount() {
    // Cleanup
    if (this.timeoutId) {
      clearTimeout(this.timeoutId);
    }
  }

  fetchUserData = async () => {
    this.setState({ loading: true });
    // Fetch logic...
  }

  handleFollow = async () => {
    try {
      await fetch(`/api/users/${this.props.userId}/follow`, { method: 'POST' });
      this.setState(prevState => ({
        followersCount: prevState.followersCount + 1
      }));
    } catch (error) {
      console.error('Follow failed:', error);
    }
  }

  handleUnfollow = async () => {
    try {
      await fetch(`/api/users/${this.props.userId}/unfollow`, { method: 'POST' });
      this.setState(prevState => ({
        followersCount: prevState.followersCount - 1
      }));
    } catch (error) {
      console.error('Unfollow failed:', error);
    }
  }

  render() {
    const { user, loading, error, posts, followersCount } = this.state;

    if (loading) return <div>Loading...</div>;
    if (error) return <div>Error: {error}</div>;

    return (
      <div className="user-profile">
        <h1>{user.name}</h1>
        <p>Followers: {followersCount}</p>
        <button onClick={this.handleFollow}>Follow</button>
        <button onClick={this.handleUnfollow}>Unfollow</button>
        
        <div className="posts">
          {posts.map(post => (
            <PostCard key={post.id} post={post} />
          ))}
        </div>
      </div>
    );
  }
}

// After: Hooks component
function UserProfile({ userId }) {
  // Convert state
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [posts, setPosts] = useState([]);
  const [followersCount, setFollowersCount] = useState(0);
  
  // Convert refs if needed
  const timeoutRef = useRef();

  // Convert methods to functions
  const fetchUserData = useCallback(async () => {
    setLoading(true);
    setError(null);
    
    try {
      const [userResponse, postsResponse] = await Promise.all([
        fetch(`/api/users/${userId}`),
        fetch(`/api/users/${userId}/posts`)
      ]);
      
      const userData = await userResponse.json();
      const postsData = await postsResponse.json();
      
      setUser(userData);
      setPosts(postsData);
      setFollowersCount(userData.followersCount);
    } catch (err) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  }, [userId]);

  const handleFollow = useCallback(async () => {
    try {
      await fetch(`/api/users/${userId}/follow`, { method: 'POST' });
      setFollowersCount(prev => prev + 1);
    } catch (error) {
      console.error('Follow failed:', error);
    }
  }, [userId]);

  const handleUnfollow = useCallback(async () => {
    try {
      await fetch(`/api/users/${userId}/unfollow`, { method: 'POST' });
      setFollowersCount(prev => prev - 1);
    } catch (error) {
      console.error('Unfollow failed:', error);
    }
  }, [userId]);

  // Convert componentDidMount and componentDidUpdate
  useEffect(() => {
    fetchUserData();
  }, [fetchUserData]);

  // Convert componentWillUnmount
  useEffect(() => {
    return () => {
      if (timeoutRef.current) {
        clearTimeout(timeoutRef.current);
      }
    };
  }, []);

  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error}</div>;

  return (
    <div className="user-profile">
      <h1>{user.name}</h1>
      <p>Followers: {followersCount}</p>
      <button onClick={handleFollow}>Follow</button>
      <button onClick={handleUnfollow}>Unfollow</button>
      
      <div className="posts">
        {posts.map(post => (
          <PostCard key={post.id} post={post} />
        ))}
      </div>
    </div>
  );
}

// Complex lifecycle migration example
// Before: Class with complex lifecycle
class DataFetcher extends Component {
  state = { data: null, loading: false };
  
  componentDidMount() {
    this.fetchData();
  }
  
  componentDidUpdate(prevProps) {
    if (prevProps.url !== this.props.url) {
      this.fetchData();
    }
  }
  
  componentWillUnmount() {
    this.controller?.abort();
  }
  
  fetchData = async () => {
    this.controller = new AbortController();
    this.setState({ loading: true });
    
    try {
      const response = await fetch(this.props.url, {
        signal: this.controller.signal
      });
      const data = await response.json();
      this.setState({ data, loading: false });
    } catch (error) {
      if (error.name !== 'AbortError') {
        this.setState({ loading: false });
      }
    }
  }
  
  render() {
    return this.props.children(this.state);
  }
}

// After: Custom hook
function useDataFetcher(url) {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(false);
  const controllerRef = useRef();

  useEffect(() => {
    const fetchData = async () => {
      // Abort previous request
      controllerRef.current?.abort();
      
      controllerRef.current = new AbortController();
      setLoading(true);
      
      try {
        const response = await fetch(url, {
          signal: controllerRef.current.signal
        });
        const result = await response.json();
        setData(result);
      } catch (error) {
        if (error.name !== 'AbortError') {
          console.error('Fetch failed:', error);
        }
      } finally {
        setLoading(false);
      }
    };

    if (url) {
      fetchData();
    }

    // Cleanup on unmount
    return () => {
      controllerRef.current?.abort();
    };
  }, [url]);

  return { data, loading };
}

// Usage of custom hook
function DataComponent({ url }) {
  const { data, loading } = useDataFetcher(url);
  
  if (loading) return <div>Loading...</div>;
  
  return (
    <div>
      {data && <pre>{JSON.stringify(data, null, 2)}</pre>}
    </div>
  );
}

// Migration checklist
/*
✅ Convert this.state to useState
✅ Convert componentDidMount to useEffect with empty dependency array
✅ Convert componentDidUpdate to useEffect with dependencies
✅ Convert componentWillUnmount to useEffect cleanup function
✅ Convert class methods to functions with useCallback if needed
✅ Convert this.refs to useRef
✅ Extract reusable logic into custom hooks
✅ Add proper dependency arrays to useEffect
✅ Handle cleanup properly (abort controllers, timeouts, etc.)
*/
```

### Performance Optimization Summary

| Technique | Use Case | Impact |
|-----------|----------|--------|
| **React.memo** | Prevent unnecessary re-renders | High |
| **useMemo** | Expensive calculations | Medium |
| **useCallback** | Stable function references | Medium |
| **Code splitting** | Large bundles | High |
| **Virtualization** | Large lists | High |
| **Image optimization** | Heavy images | Medium |
| **Bundle analysis** | Identify bloat | High |

### Quick Reference

```jsx
// Performance patterns
// Memoization
const MemoComponent = React.memo(Component);
const memoizedValue = useMemo(() => expensiveCalc(), [deps]);
const memoizedCallback = useCallback(() => {}, [deps]);

// Code splitting
const LazyComponent = lazy(() => import('./Component'));

// Migration patterns
// State: this.state → useState
// Lifecycle: componentDidMount → useEffect(() => {}, [])
// Methods: class methods → useCallback
// Refs: this.refs → useRef
```

### Security Best Practices Summary

| Security Concern | React Protection | Additional Measures |
|------------------|------------------|-------------------|
| **XSS** | Automatic escaping | Sanitize HTML, validate URLs |
| **SQL Injection** | N/A (frontend) | Backend parameterized queries |
| **Token Storage** | N/A | HttpOnly cookies + memory storage |
| **CSRF** | N/A | CSRF tokens, SameSite cookies |
| **Authorization** | UI-level only | Backend validation required |

### Quick Reference

```jsx
// Security checklist
✅ Escape user input (React does this automatically)
✅ Validate and sanitize data before API calls
✅ Use HttpOnly cookies for sensitive tokens
✅ Implement proper role-based authorization
✅ Use HTTPS in production
✅ Validate on both client and server
✅ Use Content Security Policy (CSP)

// Common vulnerabilities to avoid
❌ Using dangerouslySetInnerHTML without sanitization
❌ Storing sensitive data in localStorage
❌ Trusting client-side validation only
❌ Exposing API keys in frontend code
❌ Not implementing proper authentication
```
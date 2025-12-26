# React Fundamentals - Interview Q&A

## 1. What is React and why is it used?

React is a JavaScript library for building user interfaces, especially web applications. It's used because:

• **Component-based architecture** - Build encapsulated components that manage their own state
• **Virtual DOM** - Efficient updates and rendering performance
• **Declarative** - Describe what the UI should look like, React handles the how
• **Reusable components** - Write once, use anywhere
• **Large ecosystem** - Huge community and third-party libraries

```jsx
// Simple React component
function Welcome() {
  return <h1>Hello, World!</h1>;
}
```

## 2. What is JSX and how does it work?

JSX is a syntax extension for JavaScript that looks like HTML but gets compiled to JavaScript function calls.

• **Syntax sugar** - Makes writing React elements easier and more readable
• **Compiled by Babel** - Transforms JSX into React.createElement calls
• **JavaScript expressions** - Use curly braces {} to embed JS code
• **Must return single parent** - Wrap multiple elements in a fragment or div

```jsx
// JSX syntax
const element = <h1>Hello, {name}!</h1>;

// What it compiles to
const element = React.createElement('h1', null, 'Hello, ', name, '!');
```

## 3. Difference between JSX and React.createElement

JSX is the syntactic sugar, React.createElement is the underlying function call.

• **JSX** - More readable, HTML-like syntax
• **React.createElement** - Verbose but shows what's actually happening
• **Same output** - Both create React elements
• **Performance** - No difference, JSX compiles to createElement

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

## 4. Difference between React and ReactDOM

React and ReactDOM are separate packages with different responsibilities.

• **React** - Core library for creating components and elements
• **ReactDOM** - Renders React components to the DOM
• **Separation of concerns** - React can target different platforms
• **React Native** - Uses React core but different renderer

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

## 5. What are components in React?

Components are independent, reusable pieces of UI that return JSX elements.

• **Building blocks** - Like custom HTML elements
• **Encapsulation** - Own state and logic
• **Reusable** - Use the same component multiple times
• **Composable** - Combine components to build complex UIs

```jsx
// Simple component
function Button({ text, onClick }) {
  return <button onClick={onClick}>{text}</button>;
}

// Using the component
<Button text="Click me" onClick={() => alert('Clicked!')} />
```

## 6. Types of components in React

There are two main types of components in React.

• **Functional components** - JavaScript functions that return JSX
• **Class components** - ES6 classes that extend React.Component
• **Modern preference** - Functional components with hooks
• **Legacy** - Class components still supported but less common

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

## 7. Functional vs Class components

Key differences between the two component types.

• **Syntax** - Functions vs ES6 classes
• **State management** - Hooks vs this.state
• **Lifecycle** - useEffect vs lifecycle methods
• **Performance** - Functional components are slightly more optimized
• **Modern standard** - Hooks make functional components more powerful

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

## 8. What is the Virtual DOM?

The Virtual DOM is React's in-memory representation of the real DOM.

• **JavaScript object** - Lightweight copy of the actual DOM
• **Diffing algorithm** - Compares old and new virtual DOM trees
• **Reconciliation** - Updates only changed parts in real DOM
• **Performance** - Batch updates and minimize expensive DOM operations
• **Predictable** - Makes UI updates more predictable and debuggable

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

## 9. What are props?

Props are arguments passed to React components, like function parameters.

• **Read-only data** - Components cannot modify their props
• **Parent to child** - Data flows down from parent components
• **Any data type** - Strings, numbers, objects, functions, etc.
• **Default values** - Can set defaultProps for missing props
• **Destructuring** - Extract props for cleaner code

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

## 10. What is state and how is it different from props?

State is internal component data that can change, props are external data passed from parent.

• **State** - Mutable data owned by the component
• **Props** - Immutable data passed from parent to child
• **State changes** - Triggers re-renders when updated
• **Props are read-only** - Cannot be modified by the receiving component
• **Data flow** - Props flow down, state changes bubble up via callbacks

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

## 11. What is the `render()` method?

The render method returns JSX that describes what the UI should look like.

• **Class components** - Required method that returns JSX
• **Functional components** - The entire function body is like render
• **Pure function** - Should not modify state or cause side effects
• **Called automatically** - React calls it when state or props change
• **Must return** - JSX, null, or array of elements

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

## 12. What is the `key` prop and why is it important?

The key prop helps React identify which list items have changed, added, or removed.

• **Unique identifier** - Should be unique among siblings
• **Performance optimization** - Helps React reuse DOM elements
• **Avoid array index** - Use stable, unique values when possible
• **Required for lists** - React warns if missing in mapped arrays
• **Reconciliation** - Helps Virtual DOM diffing algorithm

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

## 13. What are controlled components?

Controlled components have their form data handled by React state.

• **React controls value** - Input value comes from state
• **onChange handler** - Updates state when user types
• **Single source of truth** - State is the authoritative data source
• **Predictable** - You always know the current value
• **Validation** - Easy to add real-time validation

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

## 14. What are uncontrolled components?

Uncontrolled components manage their own state internally, like traditional HTML forms.

• **DOM controls value** - Input manages its own state
• **Refs for access** - Use refs to get current value when needed
• **Less React code** - Simpler for basic forms
• **Integration** - Good for integrating with non-React libraries
• **Default values** - Use defaultValue instead of value

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

## 15. What are React Fragments?

Fragments let you group multiple elements without adding extra DOM nodes.

• **No wrapper div** - Avoid unnecessary DOM elements
• **Two syntaxes** - React.Fragment or shorthand <></>
• **Key prop** - Only React.Fragment supports key prop
• **Cleaner HTML** - Keeps DOM structure clean
• **Performance** - Slightly better performance, less memory

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

## 16. What are synthetic events?

Synthetic events are React's wrapper around native DOM events for cross-browser compatibility.

• **Cross-browser** - Same API across all browsers
• **Event pooling** - Reuses event objects for performance (React 16 and below)
• **Same interface** - Same methods as native events (preventDefault, stopPropagation)
• **Automatic binding** - No need to worry about 'this' context
• **Access native event** - Use e.nativeEvent if needed

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

## 17. What is `React.StrictMode` and why is it used?

StrictMode is a development tool that highlights potential problems in your application.

• **Development only** - No impact on production builds
• **Double rendering** - Helps find side effects in render methods
• **Deprecated warnings** - Warns about unsafe lifecycle methods
• **Future-proofing** - Prepares code for future React versions
• **Wrapper component** - Wrap parts of your app to enable checks

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

# React Hooks Guide - Core + Advanced Concepts
## 🟢 2. React Hooks (Core + Advanced)

---

## 1. What are React Hooks and why were they introduced?

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

## 2. Rules of Hooks and why they exist

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

## 3. What is `useState` and how does it work?

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

## 4. What is `useEffect` and how does it work?

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

## 5. Difference between `useState` and `useEffect`

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

## 6. Difference between `useEffect`, `useLayoutEffect`, and `useInsertionEffect`

**Three effect hooks that run at different phases of the render cycle.**

* **useEffect**: Runs after DOM updates (asynchronous)
* **useLayoutEffect**: Runs before browser paint (synchronous)
* **useInsertionEffect**: Runs before DOM mutations (CSS-in-JS libraries)

```jsx
import { useState, useEffect, useLayoutEffect, useInsertionEffect } from 'react';

function EffectTimingExample() {
  const [count, setCount] = useState(0);
  
  // 3. useEffect - Runs AFTER browser paint (asynchronous)
  useEffect(() => {
    console.log('3. useEffect - after paint');
    // Good for: API calls, subscriptions, async operations
  }, [count]);
  
  // 2. useLayoutEffect - Runs BEFORE browser paint (synchronous)
  useLayoutEffect(() => {
    console.log('2. useLayoutEffect - before paint');
    // Good for: DOM measurements, synchronous DOM updates
    
    // Example: Measure element and update position
    const element = document.getElementById('measured-element');
    if (element) {
      const rect = element.getBoundingClientRect();
      console.log('Element width:', rect.width);
    }
  }, [count]);
  
  // 1. useInsertionEffect - Runs BEFORE DOM mutations (earliest)
  useInsertionEffect(() => {
    console.log('1. useInsertionEffect - before DOM mutations');
    // Good for: CSS-in-JS libraries, dynamic style injection
    
    // Example: Inject critical CSS
    const style = document.createElement('style');
    style.textContent = '.dynamic-style { color: red; }';
    document.head.appendChild(style);
    
    return () => {
      document.head.removeChild(style);
    };
  }, []);
  
  return (
    <div>
      <p id="measured-element">Count: {count}</p>
      <button onClick={() => setCount(count + 1)}>
        Increment (check console for timing)
      </button>
    </div>
  );
}

// Practical example showing when to use each
function PracticalExample() {
  const [width, setWidth] = useState(0);
  const [data, setData] = useState(null);
  
  // useInsertionEffect - CSS injection
  useInsertionEffect(() => {
    const css = `
      .tooltip { 
        position: absolute; 
        background: black; 
        color: white; 
        padding: 4px; 
      }
    `;
    const style = document.createElement('style');
    style.textContent = css;
    document.head.appendChild(style);
    
    return () => document.head.removeChild(style);
  }, []);
  
  // useLayoutEffect - DOM measurements
  useLayoutEffect(() => {
    const element = document.getElementById('content');
    if (element) {
      setWidth(element.offsetWidth);
    }
  });
  
  // useEffect - Data fetching
  useEffect(() => {
    fetch('/api/data')
      .then(res => res.json())
      .then(setData);
  }, []);
  
  return (
    <div>
      <div id="content">Content width: {width}px</div>
      <div>Data: {data?.message || 'Loading...'}</div>
    </div>
  );
}
```

---

## 7. When should you avoid `useEffect`?

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

## 8. How does the dependency array work internally?

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

## 9. What is stale closure in hooks?

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

## 10. How do you fix stale state issues?

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

## 11. When would you use `useRef` instead of `useState`?

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

## 12. What is `useMemo` and when should you use it?

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

## 13. What is `useCallback` and when should you use it?

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

## 14. Difference between `useMemo` and `useCallback`

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

## 15. What is `useReducer` and how is it different from `useState`?

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

## 16. Can hooks replace all lifecycle methods? (mapping)

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

## 17. How do you create custom hooks?

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

## 18. How do you share logic without HOCs?

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

## Quick Reference

```jsx
import { useState, useEffect, useLayoutEffect, useInsertionEffect } from 'react';

function HooksReference() {
  // State management
  const [state, setState] = useState(initialValue);
  
  // Side effects (after render)
  useEffect(() => {
    // API calls, subscriptions, async work
    return () => {
      // Cleanup
    };
  }, [dependencies]);
  
  // Synchronous effects (before paint)
  useLayoutEffect(() => {
    // DOM measurements, synchronous DOM updates
  }, [dependencies]);
  
  // CSS injection (before DOM mutations)
  useInsertionEffect(() => {
    // CSS-in-JS, dynamic styles
  }, [dependencies]);
  
  return <div>Component content</div>;
}

// Execution order:
// 1. useInsertionEffect
// 2. useLayoutEffect  
// 3. Browser paint
// 4. useEffect
```

# React Component Lifecycle & Internals Guide

## 🟡 3. Component Lifecycle & Internals

---

## 1. Lifecycle of class components

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

## 2. Lifecycle of functional components

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

## 3. Difference between `componentDidMount` and `useEffect`

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

## 4. Difference between `componentWillMount`, `componentDidMount`, and `getDerivedStateFromProps`

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

## 5. What is React reconciliation?

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

## 6. What is React Fiber?

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

## 7. What causes a component to re-render?

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

## Quick Reference

````jsx
// Class Component Lifecycle Order
// MOUNTING: constructor → getDerivedStateFromProps → render → componentDidMount
// UPDATING: getDerivedStateFromProps → shouldComponentUpdate → render → getSnapshotBeforeUpdate → componentDidUpdate
// UNMOUNTING: componentWillUnmount
// ERROR: getDerivedStateFromError → componentDidCatch

// Functional Component Lifecycle with Hooks
useEffect(() => {
  // componentDidMount
  return () => {
    // componentWillUnmount
  };
}, []); // Empty deps = mount/unmount only

useEffect(() => {
  // componentDidUpdate
}, [dependency]); // Runs when dependency changes

// Re-render Triggers
// 1. State changes: setState, useState, useReducer
// 2. Props changes: New props from parent
// 3. Parent re-renders: Unless component is memoized
// 4. Context changes: useContext value updates
// 5. Force re-render: New object references

// Reconciliation Process
// 1. State/props change
// 2. Create new Virtual DOM
// 3. Compare with previous Virtual DOM (diffing)
// 4. Update only changed elements in real DOM

// React Fiber Benefits
// - Incremental rendering (pausable/resumable work)
// - Priority-based updates
// - Better performance and user experience
// - Enables concurrent features (Suspense, time slicing)
```

# React Rendering & Performance Optimization Guide

## 🟡 4. Rendering & Performance Optimization (High Priority)

---

## 1. What is automatic batching in React 18?

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

## 2. What is Concurrent Rendering?

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

## 3. Difference between legacy and concurrent rendering

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

## 4. What is `startTransition` and `useTransition`?

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

## 5. Why does React Strict Mode double render in development?

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

## 6. What is code splitting?

**Code splitting breaks your bundle into smaller chunks that load on-demand for better performance.**

* **Smaller bundles**: Load only what's needed initially
* **Lazy loading**: Load components/routes when needed
* **Better performance**: Faster initial page load
* **Dynamic imports**: Use import() for code splitting

```jsx
import React, { Suspense, lazy, useState } from 'react';\n\n// Lazy load components\nconst LazyDashboard = lazy(() => import('./Dashboard'));\nconst LazyProfile = lazy(() => import('./Profile'));\nconst LazySettings = lazy(() => import('./Settings'));\n\n// Route-based code splitting\nfunction CodeSplittingExample() {\n  const [currentView, setCurrentView] = useState('home');\n  \n  const renderView = () => {\n    switch (currentView) {\n      case 'dashboard':\n        return (\n          <Suspense fallback={<div>Loading Dashboard...</div>}>\n            <LazyDashboard />\n          </Suspense>\n        );\n      case 'profile':\n        return (\n          <Suspense fallback={<div>Loading Profile...</div>}>\n            <LazyProfile />\n          </Suspense>\n        );\n      case 'settings':\n        return (\n          <Suspense fallback={<div>Loading Settings...</div>}>\n            <LazySettings />\n          </Suspense>\n        );\n      default:\n        return <div>Welcome to Home Page</div>;\n    }\n  };\n  \n  return (\n    <div>\n      <h2>Code Splitting Example</h2>\n      \n      <nav>\n        <button onClick={() => setCurrentView('home')}>Home</button>\n        <button onClick={() => setCurrentView('dashboard')}>Dashboard</button>\n        <button onClick={() => setCurrentView('profile')}>Profile</button>\n        <button onClick={() => setCurrentView('settings')}>Settings</button>\n      </nav>\n      \n      <div style={{ padding: '20px', border: '1px solid #ccc' }}>\n        {renderView()}\n      </div>\n    </div>\n  );\n}\n\n// Dynamic import with loading states\nfunction DynamicImportExample() {\n  const [component, setComponent] = useState(null);\n  const [loading, setLoading] = useState(false);\n  const [error, setError] = useState(null);\n  \n  const loadComponent = async (componentName) => {\n    setLoading(true);\n    setError(null);\n    \n    try {\n      let ComponentModule;\n      \n      // Dynamic import based on component name\n      switch (componentName) {\n        case 'chart':\n          ComponentModule = await import('./ChartComponent');\n          break;\n        case 'table':\n          ComponentModule = await import('./TableComponent');\n          break;\n        case 'form':\n          ComponentModule = await import('./FormComponent');\n          break;\n        default:\n          throw new Error('Unknown component');\n      }\n      \n      setComponent(() => ComponentModule.default);\n    } catch (err) {\n      setError(err.message);\n    } finally {\n      setLoading(false);\n    }\n  };\n  \n  return (\n    <div>\n      <h3>Dynamic Import Example</h3>\n      \n      <div>\n        <button onClick={() => loadComponent('chart')}>Load Chart</button>\n        <button onClick={() => loadComponent('table')}>Load Table</button>\n        <button onClick={() => loadComponent('form')}>Load Form</button>\n      </div>\n      \n      {loading && <div>Loading component...</div>}\n      {error && <div>Error: {error}</div>}\n      {component && React.createElement(component)}\n    </div>\n  );\n}\n\n// Feature-based code splitting\nfunction FeatureBasedSplitting() {\n  const [features, setFeatures] = useState(new Set());\n  \n  const loadFeature = async (featureName) => {\n    if (features.has(featureName)) return;\n    \n    try {\n      // Load feature module dynamically\n      const featureModule = await import(`./features/${featureName}`);\n      \n      // Initialize feature\n      if (featureModule.init) {\n        featureModule.init();\n      }\n      \n      setFeatures(prev => new Set([...prev, featureName]));\n    } catch (error) {\n      console.error(`Failed to load feature: ${featureName}`, error);\n    }\n  };\n  \n  return (\n    <div>\n      <h3>Feature-based Code Splitting</h3>\n      \n      <div>\n        <button onClick={() => loadFeature('analytics')}>\n          Load Analytics {features.has('analytics') && '✓'}\n        </button>\n        <button onClick={() => loadFeature('reporting')}>\n          Load Reporting {features.has('reporting') && '✓'}\n        </button>\n        <button onClick={() => loadFeature('admin')}>\n          Load Admin {features.has('admin') && '✓'}\n        </button>\n      </div>\n      \n      <div>\n        <p>Loaded features: {Array.from(features).join(', ') || 'None'}</p>\n      </div>\n    </div>\n  );\n}\n\n// Library code splitting\nfunction LibraryCodeSplitting() {\n  const [chart, setChart] = useState(null);\n  const [data] = useState([1, 2, 3, 4, 5]);\n  \n  const loadChart = async () => {\n    // Only load heavy chart library when needed\n    const { Chart } = await import('chart.js/auto');\n    setChart(Chart);\n  };\n  \n  return (\n    <div>\n      <h3>Library Code Splitting</h3>\n      \n      {!chart ? (\n        <button onClick={loadChart}>Load Chart Library</button>\n      ) : (\n        <div>\n          <p>Chart library loaded!</p>\n          <canvas id=\"myChart\" width=\"400\" height=\"200\"></canvas>\n        </div>\n      )}\n    </div>\n  );\n}\n\n// Complete code splitting app\nfunction CodeSplittingApp() {\n  return (\n    <div>\n      <h1>Code Splitting Examples</h1>\n      \n      <CodeSplittingExample />\n      <hr />\n      <DynamicImportExample />\n      <hr />\n      <FeatureBasedSplitting />\n      <hr />\n      <LibraryCodeSplitting />\n    </div>\n  );\n}\n\n// Webpack bundle analysis\n// Run: npm run build -- --analyze\n// Or use webpack-bundle-analyzer\n\nexport default CodeSplittingApp;
```

---

## 7. What is lazy loading?

**Lazy loading defers loading of components until they're actually needed, improving initial load performance.**

* **On-demand loading**: Load components when needed, not upfront
* **Better performance**: Faster initial page load
* **React.lazy**: Built-in lazy loading for components
* **Suspense**: Handles loading states for lazy components

```jsx
import React, { Suspense, lazy, useState, useEffect } from 'react';\n\n// Basic lazy loading\nconst LazyComponent = lazy(() => import('./HeavyComponent'));\n\nfunction BasicLazyLoading() {\n  const [showComponent, setShowComponent] = useState(false);\n  \n  return (\n    <div>\n      <h2>Basic Lazy Loading</h2>\n      \n      <button onClick={() => setShowComponent(!showComponent)}>\n        {showComponent ? 'Hide' : 'Show'} Heavy Component\n      </button>\n      \n      {showComponent && (\n        <Suspense fallback={<div>Loading heavy component...</div>}>\n          <LazyComponent />\n        </Suspense>\n      )}\n    </div>\n  );\n}\n\n// Lazy loading with error boundary\nclass LazyErrorBoundary extends React.Component {\n  constructor(props) {\n    super(props);\n    this.state = { hasError: false };\n  }\n  \n  static getDerivedStateFromError(error) {\n    return { hasError: true };\n  }\n  \n  componentDidCatch(error, errorInfo) {\n    console.error('Lazy loading error:', error, errorInfo);\n  }\n  \n  render() {\n    if (this.state.hasError) {\n      return <div>Failed to load component. Please try again.</div>;\n    }\n    \n    return this.props.children;\n  }\n}\n\n// Lazy loading with retry\nfunction LazyWithRetry() {\n  const [Component, setComponent] = useState(null);\n  const [loading, setLoading] = useState(false);\n  const [error, setError] = useState(null);\n  \n  const loadComponent = async () => {\n    setLoading(true);\n    setError(null);\n    \n    try {\n      const module = await import('./HeavyComponent');\n      setComponent(() => module.default);\n    } catch (err) {\n      setError(err);\n    } finally {\n      setLoading(false);\n    }\n  };\n  \n  const retry = () => {\n    setError(null);\n    loadComponent();\n  };\n  \n  return (\n    <div>\n      <h3>Lazy Loading with Retry</h3>\n      \n      {!Component && !loading && !error && (\n        <button onClick={loadComponent}>Load Component</button>\n      )}\n      \n      {loading && <div>Loading...</div>}\n      \n      {error && (\n        <div>\n          <p>Failed to load component</p>\n          <button onClick={retry}>Retry</button>\n        </div>\n      )}\n      \n      {Component && <Component />}\n    </div>\n  );\n}\n\n// Intersection Observer lazy loading\nfunction IntersectionLazyLoading() {\n  const [isVisible, setIsVisible] = useState(false);\n  const [hasLoaded, setHasLoaded] = useState(false);\n  const ref = React.useRef();\n  \n  useEffect(() => {\n    const observer = new IntersectionObserver(\n      ([entry]) => {\n        if (entry.isIntersecting && !hasLoaded) {\n          setIsVisible(true);\n          setHasLoaded(true);\n        }\n      },\n      { threshold: 0.1 }\n    );\n    \n    if (ref.current) {\n      observer.observe(ref.current);\n    }\n    \n    return () => observer.disconnect();\n  }, [hasLoaded]);\n  \n  return (\n    <div>\n      <h3>Intersection Observer Lazy Loading</h3>\n      <div style={{ height: '1000px', background: '#f0f0f0' }}>\n        <p>Scroll down to load component...</p>\n      </div>\n      \n      <div ref={ref} style={{ minHeight: '200px', border: '2px dashed #ccc' }}>\n        {isVisible ? (\n          <Suspense fallback={<div>Loading when visible...</div>}>\n            <LazyComponent />\n          </Suspense>\n        ) : (\n          <div>Component will load when visible</div>\n        )}\n      </div>\n    </div>\n  );\n}\n\n// Image lazy loading\nfunction ImageLazyLoading() {\n  const [images, setImages] = useState([\n    { id: 1, src: 'https://picsum.photos/300/200?random=1', loaded: false },\n    { id: 2, src: 'https://picsum.photos/300/200?random=2', loaded: false },\n    { id: 3, src: 'https://picsum.photos/300/200?random=3', loaded: false },\n  ]);\n  \n  const LazyImage = ({ src, alt, id }) => {\n    const [isVisible, setIsVisible] = useState(false);\n    const [isLoaded, setIsLoaded] = useState(false);\n    const imgRef = React.useRef();\n    \n    useEffect(() => {\n      const observer = new IntersectionObserver(\n        ([entry]) => {\n          if (entry.isIntersecting) {\n            setIsVisible(true);\n            observer.disconnect();\n          }\n        },\n        { threshold: 0.1 }\n      );\n      \n      if (imgRef.current) {\n        observer.observe(imgRef.current);\n      }\n      \n      return () => observer.disconnect();\n    }, []);\n    \n    return (\n      <div ref={imgRef} style={{ minHeight: '200px', background: '#f5f5f5' }}>\n        {isVisible ? (\n          <img\n            src={src}\n            alt={alt}\n            onLoad={() => setIsLoaded(true)}\n            style={{\n              opacity: isLoaded ? 1 : 0,\n              transition: 'opacity 0.3s'\n            }}\n          />\n        ) : (\n          <div>Image will load when visible</div>\n        )}\n      </div>\n    );\n  };\n  \n  return (\n    <div>\n      <h3>Image Lazy Loading</h3>\n      <div style={{ height: '500px', overflow: 'auto' }}>\n        {images.map(image => (\n          <LazyImage\n            key={image.id}\n            id={image.id}\n            src={image.src}\n            alt={`Image ${image.id}`}\n          />\n        ))}\n      </div>\n    </div>\n  );\n}\n\n// Route-based lazy loading\nconst LazyHome = lazy(() => import('./pages/Home'));\nconst LazyAbout = lazy(() => import('./pages/About'));\nconst LazyContact = lazy(() => import('./pages/Contact'));\n\nfunction RouteLazyLoading() {\n  const [currentRoute, setCurrentRoute] = useState('home');\n  \n  const renderRoute = () => {\n    const routes = {\n      home: LazyHome,\n      about: LazyAbout,\n      contact: LazyContact\n    };\n    \n    const Component = routes[currentRoute];\n    \n    return (\n      <LazyErrorBoundary>\n        <Suspense fallback={<div>Loading page...</div>}>\n          <Component />\n        </Suspense>\n      </LazyErrorBoundary>\n    );\n  };\n  \n  return (\n    <div>\n      <h3>Route-based Lazy Loading</h3>\n      \n      <nav>\n        <button onClick={() => setCurrentRoute('home')}>Home</button>\n        <button onClick={() => setCurrentRoute('about')}>About</button>\n        <button onClick={() => setCurrentRoute('contact')}>Contact</button>\n      </nav>\n      \n      <div style={{ padding: '20px', border: '1px solid #ddd' }}>\n        {renderRoute()}\n      </div>\n    </div>\n  );\n}\n\n// Complete lazy loading app\nfunction LazyLoadingApp() {\n  return (\n    <div>\n      <h1>Lazy Loading Examples</h1>\n      \n      <BasicLazyLoading />\n      <hr />\n      <LazyWithRetry />\n      <hr />\n      <IntersectionLazyLoading />\n      <hr />\n      <ImageLazyLoading />\n      <hr />\n      <RouteLazyLoading />\n    </div>\n  );\n}\n\nexport default LazyLoadingApp;
```

---

## 8. How does `React.memo` work internally?

**React.memo is a higher-order component that memoizes the result and only re-renders when props change.**

* **Shallow comparison**: Compares props using Object.is()
* **Memoization**: Caches the rendered result
* **Performance optimization**: Prevents unnecessary re-renders
* **Custom comparison**: Can provide custom comparison function

```jsx
import React, { useState, memo, useCallback, useMemo } from 'react';\n\n// Basic React.memo usage\nconst BasicMemoComponent = memo(function BasicMemoComponent({ name, count }) {\n  console.log('BasicMemoComponent rendered');\n  \n  return (\n    <div>\n      <h3>Basic Memo Component</h3>\n      <p>Name: {name}</p>\n      <p>Count: {count}</p>\n    </div>\n  );\n});\n\n// React.memo with custom comparison\nconst CustomMemoComponent = memo(\n  function CustomMemoComponent({ user, settings }) {\n    console.log('CustomMemoComponent rendered');\n    \n    return (\n      <div>\n        <h3>Custom Memo Component</h3>\n        <p>User: {user.name}</p>\n        <p>Theme: {settings.theme}</p>\n      </div>\n    );\n  },\n  // Custom comparison function\n  (prevProps, nextProps) => {\n    // Return true if props are equal (skip re-render)\n    // Return false if props are different (re-render)\n    return (\n      prevProps.user.name === nextProps.user.name &&\n      prevProps.settings.theme === nextProps.settings.theme\n    );\n  }\n);\n\n// Component that shows memo behavior\nfunction MemoInternalExample() {\n  const [count, setCount] = useState(0);\n  const [name, setName] = useState('John');\n  const [unrelatedState, setUnrelatedState] = useState(0);\n  \n  const user = { name, id: 1 };\n  const settings = { theme: 'dark', lang: 'en' };\n  \n  console.log('Parent component rendered');\n  \n  return (\n    <div>\n      <h2>React.memo Internal Behavior</h2>\n      \n      <div>\n        <p>Count: {count}</p>\n        <p>Name: {name}</p>\n        <p>Unrelated: {unrelatedState}</p>\n        \n        <button onClick={() => setCount(count + 1)}>Update Count</button>\n        <button onClick={() => setName(name === 'John' ? 'Jane' : 'John')}>Toggle Name</button>\n        <button onClick={() => setUnrelatedState(unrelatedState + 1)}>Update Unrelated</button>\n      </div>\n      \n      {/* This will re-render when count or name changes */}\n      <BasicMemoComponent name={name} count={count} />\n      \n      {/* This will re-render based on custom comparison */}\n      <CustomMemoComponent user={user} settings={settings} />\n    </div>\n  );\n}\n\n// Demonstrating memo with object props\nfunction MemoWithObjectProps() {\n  const [count, setCount] = useState(0);\n  const [name, setName] = useState('John');\n  \n  // ❌ BAD - New object every render\n  const badUser = { name, id: 1 };\n  \n  // ✅ GOOD - Memoized object\n  const goodUser = useMemo(() => ({ name, id: 1 }), [name]);\n  \n  // ❌ BAD - New function every render\n  const badCallback = () => console.log('clicked');\n  \n  // ✅ GOOD - Memoized callback\n  const goodCallback = useCallback(() => console.log('clicked'), []);\n  \n  return (\n    <div>\n      <h3>Memo with Object Props</h3>\n      \n      <p>Count: {count}</p>\n      <button onClick={() => setCount(count + 1)}>Update Count</button>\n      \n      {/* Will re-render every time due to new object */}\n      <MemoChildWithBadProps user={badUser} onClick={badCallback} />\n      \n      {/* Will only re-render when user actually changes */}\n      <MemoChildWithGoodProps user={goodUser} onClick={goodCallback} />\n    </div>\n  );\n}\n\nconst MemoChildWithBadProps = memo(({ user, onClick }) => {\n  console.log('MemoChildWithBadProps rendered (always re-renders)');\n  \n  return (\n    <div style={{ border: '1px solid red', padding: '10px', margin: '5px' }}>\n      <p>Bad Props Child: {user.name}</p>\n      <button onClick={onClick}>Click</button>\n    </div>\n  );\n});\n\nconst MemoChildWithGoodProps = memo(({ user, onClick }) => {\n  console.log('MemoChildWithGoodProps rendered (only when needed)');\n  \n  return (\n    <div style={{ border: '1px solid green', padding: '10px', margin: '5px' }}>\n      <p>Good Props Child: {user.name}</p>\n      <button onClick={onClick}>Click</button>\n    </div>\n  );\n});\n\n// How React.memo works internally (simplified)\nfunction createMemoComponent(Component, compare) {\n  function MemoComponent(props) {\n    const ref = React.useRef();\n    \n    // First render or no previous props\n    if (!ref.current) {\n      ref.current = {\n        props,\n        result: React.createElement(Component, props)\n      };\n      return ref.current.result;\n    }\n    \n    // Compare props\n    const areEqual = compare \n      ? compare(ref.current.props, props)\n      : shallowEqual(ref.current.props, props);\n    \n    // If props are equal, return cached result\n    if (areEqual) {\n      return ref.current.result;\n    }\n    \n    // Props changed, re-render and cache\n    ref.current.props = props;\n    ref.current.result = React.createElement(Component, props);\n    return ref.current.result;\n  }\n  \n  return MemoComponent;\n}\n\n// Shallow comparison (simplified version of React's implementation)\nfunction shallowEqual(objA, objB) {\n  if (Object.is(objA, objB)) {\n    return true;\n  }\n  \n  if (typeof objA !== 'object' || objA === null ||\n      typeof objB !== 'object' || objB === null) {\n    return false;\n  }\n  \n  const keysA = Object.keys(objA);\n  const keysB = Object.keys(objB);\n  \n  if (keysA.length !== keysB.length) {\n    return false;\n  }\n  \n  for (let i = 0; i < keysA.length; i++) {\n    const key = keysA[i];\n    if (!Object.prototype.hasOwnProperty.call(objB, key) ||\n        !Object.is(objA[key], objB[key])) {\n      return false;\n    }\n  }\n  \n  return true;\n}\n\n// Performance comparison\nfunction PerformanceComparison() {\n  const [count, setCount] = useState(0);\n  const [renderCount, setRenderCount] = useState(0);\n  \n  React.useEffect(() => {\n    setRenderCount(prev => prev + 1);\n  });\n  \n  return (\n    <div>\n      <h3>Performance Comparison</h3>\n      <p>Parent renders: {renderCount}</p>\n      <p>Count: {count}</p>\n      \n      <button onClick={() => setCount(count + 1)}>Update Count</button>\n      \n      {/* Regular component - always re-renders */}\n      <RegularChild name=\"Regular\" />\n      \n      {/* Memoized component - only re-renders when props change */}\n      <MemoizedChild name=\"Memoized\" />\n    </div>\n  );\n}\n\nfunction RegularChild({ name }) {\n  console.log(`${name} child rendered`);\n  return <div>{name} Child Component</div>;\n}\n\nconst MemoizedChild = memo(function MemoizedChild({ name }) {\n  console.log(`${name} child rendered`);\n  return <div>{name} Child Component</div>;\n});\n\n// Complete React.memo demo\nfunction ReactMemoApp() {\n  return (\n    <div>\n      <h1>React.memo Internal Behavior</h1>\n      \n      <MemoInternalExample />\n      <hr />\n      <MemoWithObjectProps />\n      <hr />\n      <PerformanceComparison />\n    </div>\n  );\n}\n\nexport default ReactMemoApp;
```

---

## 9. How do you prevent unnecessary re-renders?

**Use React.memo, useMemo, useCallback, and proper state structure to prevent unnecessary re-renders.**

• **React.memo**: Memoize components to skip re-renders when props unchanged
• **useMemo**: Memoize expensive calculations
• **useCallback**: Memoize functions to maintain reference equality
• **State structure**: Keep state minimal and avoid derived state

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

## 10. Why do inline functions cause re-renders?

**Inline functions create new function references on every render, breaking memoization and causing child re-renders.**

• **New reference**: Inline functions create new references each render
• **Breaks memoization**: React.memo sees different function props
• **Performance impact**: Causes unnecessary child re-renders
• **Solution**: Use useCallback or define functions outside render

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

## 11. What is render thrashing?

**Render thrashing occurs when components re-render excessively due to rapid state changes or poor optimization.**

• **Excessive re-renders**: Components render more than necessary
• **Performance degradation**: UI becomes slow and unresponsive
• **Common causes**: Rapid state updates, missing memoization, unstable dependencies
• **Solutions**: Debouncing, batching, proper memoization

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

## 12. How do keys affect reconciliation?

**Keys help React identify which items have changed, moved, or been removed, enabling efficient reconciliation.**

• **Element identity**: Keys give React a way to track elements across renders
• **Efficient updates**: React can reuse DOM nodes instead of recreating them
• **Preserve state**: Component state is preserved when elements move
• **Performance**: Reduces DOM manipulation and improves rendering speed

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

## 13. What happens if keys are not stable?

**Unstable keys cause React to unnecessarily recreate components, losing state and degrading performance.**

• **Component recreation**: React treats elements as different when keys change
• **State loss**: Component state is lost when keys are unstable
• **Performance impact**: Unnecessary DOM creation and destruction
• **Common mistake**: Using array index or Math.random() as keys

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

## 14. How do you handle memory leaks in React?

**Prevent memory leaks by cleaning up subscriptions, timers, and event listeners in useEffect cleanup functions.**

• **Cleanup functions**: Return cleanup function from useEffect
• **Event listeners**: Remove event listeners on unmount
• **Timers**: Clear timeouts and intervals
• **Subscriptions**: Unsubscribe from external data sources

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

## 15. What is virtualization (windowing)?

**Virtualization renders only visible items in large lists, dramatically improving performance by reducing DOM nodes.**

• **Render only visible**: Only render items currently in viewport
• **Performance boost**: Handles thousands of items smoothly
• **Memory efficient**: Reduces DOM nodes and memory usage
• **Libraries**: react-window, react-virtualized for implementation

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

## 16. How does React efficiently handle large lists?

**React uses keys for reconciliation, but large lists need virtualization, memoization, and pagination for optimal performance.**

• **Keys for reconciliation**: Efficient updates when items change
• **Virtualization**: Render only visible items
• **Memoization**: Prevent unnecessary re-renders of list items
• **Pagination**: Break large datasets into smaller chunks

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

## Quick Reference

```jsx
// React 18 Features
// 1. Automatic Batching - Groups multiple state updates
setTimeout(() => {
  setState1(value1); // Batched
  setState2(value2); // Batched
}, 1000); // Single re-render in React 18

// 2. Concurrent Rendering - Interruptible, priority-based
const [isPending, startTransition] = useTransition();
startTransition(() => {
  setExpensiveState(newValue); // Low priority, interruptible
});

// 3. Code Splitting - Load components on demand
const LazyComponent = lazy(() => import('./Component'));
<Suspense fallback={<Loading />}>
  <LazyComponent />
</Suspense>

// 4. React.memo - Prevent unnecessary re-renders
const MemoComponent = memo(({ prop1, prop2 }) => {
  return <div>{prop1} {prop2}</div>;
});

// 5. Performance Optimization Patterns
// - Use useMemo for expensive calculations
// - Use useCallback for stable function references
// - Use React.memo for component memoization
// - Use code splitting for large bundles
// - Use lazy loading for better initial load times

// Rendering Modes
// Legacy: ReactDOM.render(<App />, container)
// Concurrent: createRoot(container).render(<App />)
```


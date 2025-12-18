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

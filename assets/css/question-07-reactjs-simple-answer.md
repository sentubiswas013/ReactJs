## **Basic React.js Concepts**

### 1. What is React?

React is a JavaScript library for building user interfaces, especially web applications. It helps you create interactive UIs by breaking them into reusable components. Think of it like building blocks - you create small pieces and combine them to build complex applications.

```jsx
function App() {
  return <h1>Hello React!</h1>;
}
```

### 2. What is JSX?

JSX is a syntax extension that lets you write HTML-like code inside JavaScript. It makes React components easier to read and write. Under the hood, JSX gets converted to regular JavaScript function calls.

```jsx
const element = <h1>Hello, {name}!</h1>;
// Becomes: React.createElement('h1', null, 'Hello, ', name, '!')
```

### 3. What is the difference between React and ReactDOM?

React is the core library that defines components and their behavior. ReactDOM is responsible for rendering React components to the actual DOM in web browsers. React can work with other platforms, but ReactDOM is specifically for web.

```jsx
import React from 'react';
import ReactDOM from 'react-dom/client';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(<App />);
```

### 4. How does the React development workflow (npm start, Webpack, Babel) work?

When you run `npm start`, Webpack bundles your files and serves them locally. Babel transforms your JSX and modern JavaScript into browser-compatible code. The dev server provides hot reloading, so changes appear instantly without losing state.

```bash
npm start  # Starts development server
npm run build  # Creates production bundle
```

### 5. What are components in React?

Components are reusable pieces of UI that return JSX. They're like JavaScript functions that accept inputs (props) and return what should appear on screen. Components help you split your UI into independent, reusable pieces.

```jsx
function Welcome(props) {
  return <h1>Hello, {props.name}</h1>;
}
```

### 6. What are the types of components in React?

There are two main types: functional components and class components. Functional components are simpler functions that return JSX. Class components extend React.Component and have more features, but hooks made functional components more powerful.

```jsx
// Functional Component
function Button() {
  return <button>Click me</button>;
}

// Class Component
class Button extends React.Component {
  render() {
    return <button>Click me</button>;
  }
}
```

### 7. What is the difference between functional and class components?

Functional components are simpler functions that return JSX. Class components have lifecycle methods and state built-in. With hooks, functional components can now handle state and side effects, making them the preferred choice for new code.

```jsx
// Functional with hooks
function Counter() {
  const [count, setCount] = useState(0);
  return <button onClick={() => setCount(count + 1)}>{count}</button>;
}

// Class component
class Counter extends React.Component {
  state = { count: 0 };
  render() {
    return <button onClick={() => this.setState({count: this.state.count + 1})}>{this.state.count}</button>;
  }
}
```

### 8. What is the Virtual DOM?

The Virtual DOM is React's in-memory representation of the real DOM. When state changes, React creates a new virtual DOM tree, compares it with the previous one, and updates only the parts that changed. This makes updates faster and more efficient.

```jsx
// React efficiently updates only what changed
function App() {
  const [count, setCount] = useState(0);
  return (
    <div>
      <h1>Static title</h1>  {/* Won't re-render */}
      <p>{count}</p>         {/* Only this updates */}
    </div>
  );
}
```

### 9. What are props in React?

Props are inputs passed to components, like function parameters. They're read-only and flow down from parent to child components. Props make components reusable by allowing different data to be passed in.

```jsx
function Greeting(props) {
  return <h1>Hello, {props.name}!</h1>;
}

// Usage
<Greeting name="Alice" />
<Greeting name="Bob" />
```

### 10. What are state and how does it differ from props?

State is internal data that a component manages and can change over time. Props are external data passed from parent components and are read-only. State triggers re-renders when it changes, while props come from outside.

```jsx
function Counter() {
  const [count, setCount] = useState(0); // State - internal, mutable
  
  return (
    <div>
      <Display value={count} />  {/* Props - external, read-only */}
      <button onClick={() => setCount(count + 1)}>+</button>
    </div>
  );
}
```

### 11. What is a key in React and why is it important?

Keys help React identify which list items have changed, been added, or removed. They should be unique among siblings. Keys improve performance by helping React reuse existing DOM elements instead of recreating them.

```jsx
function TodoList({ todos }) {
  return (
    <ul>
      {todos.map(todo => 
        <li key={todo.id}>{todo.text}</li>  // Use unique ID, not index
      )}
    </ul>
  );
}
```

### 12. What are controlled components in React?

Controlled components have their form data handled by React state. The component controls the input value through props and updates it via event handlers. This gives you full control over form behavior and validation.

```jsx
function LoginForm() {
  const [email, setEmail] = useState('');
  
  return (
    <input 
      value={email}  // Controlled by React state
      onChange={(e) => setEmail(e.target.value)}
    />
  );
}
```

### 13. What are uncontrolled components in React?

Uncontrolled components manage their own state internally, like regular HTML form elements. You access their values using refs instead of state. They're simpler but give you less control over the form behavior.

```jsx
function LoginForm() {
  const emailRef = useRef();
  
  const handleSubmit = () => {
    console.log(emailRef.current.value);  // Access via ref
  };
  
  return <input ref={emailRef} />;  // Uncontrolled
}
```

### 14. What is the purpose of the `render()` method in React?

The render() method in class components returns the JSX that should be displayed. It's called whenever the component needs to update. It should be pure - same props and state should always return the same JSX.

```jsx
class Welcome extends React.Component {
  render() {
    return <h1>Hello, {this.props.name}!</h1>;  // Must return JSX
  }
}
```

### 15. What are React hooks? Can you name some of them?

Hooks are functions that let you use state and other React features in functional components. They start with "use" and follow specific rules. Common hooks include useState for state, useEffect for side effects, and useContext for consuming context.

```jsx
function MyComponent() {
  const [count, setCount] = useState(0);        // State hook
  const [name, setName] = useState('');
  
  useEffect(() => {                             // Effect hook
    document.title = `Count: ${count}`;
  }, [count]);
  
  return <div>{count}</div>;
}
```

### 16. What is the difference between `useState` and `useEffect` hooks?

useState manages component state - it gives you a value and a function to update it. useEffect handles side effects like API calls, subscriptions, or DOM manipulation. useState is for data, useEffect is for actions that happen after rendering.

```jsx
function Timer() {
  const [count, setCount] = useState(0);  // State management
  
  useEffect(() => {                       // Side effect
    const timer = setInterval(() => setCount(c => c + 1), 1000);
    return () => clearInterval(timer);    // Cleanup
  }, []);
  
  return <div>{count}</div>;
}
```

### 17. What is the `useEffect` hook and how does it work?

useEffect runs side effects after render. It combines componentDidMount, componentDidUpdate, and componentWillUnmount from class components. The dependency array controls when it runs - empty array runs once, no array runs every render, with dependencies runs when they change.

```jsx
function UserProfile({ userId }) {
  const [user, setUser] = useState(null);
  
  useEffect(() => {
    fetch(`/api/users/${userId}`)
      .then(res => res.json())
      .then(setUser);
  }, [userId]);  // Runs when userId changes
  
  return <div>{user?.name}</div>;
}
```

### 18. What is `useRef` in React?

useRef creates a mutable reference that persists across renders without causing re-renders when changed. It's commonly used to access DOM elements directly or store values that don't need to trigger updates, like timers or previous values.

```jsx
function FocusInput() {
  const inputRef = useRef();
  
  const focusInput = () => {
    inputRef.current.focus();  // Direct DOM access
  };
  
  return (
    <div>
      <input ref={inputRef} />
      <button onClick={focusInput}>Focus Input</button>
    </div>
  );
}
```

### 19. What is the purpose of `useMemo` and `useCallback` hooks?

useMemo memoizes expensive calculations to avoid recalculating on every render. useCallback memoizes functions to prevent child components from re-rendering unnecessarily. Both are performance optimizations that should be used when you have actual performance issues.

```jsx
function ExpensiveComponent({ items, onItemClick }) {
  const expensiveValue = useMemo(() => {
    return items.reduce((sum, item) => sum + item.value, 0);
  }, [items]);  // Only recalculate when items change
  
  const handleClick = useCallback((id) => {
    onItemClick(id);
  }, [onItemClick]);  // Stable function reference
  
  return <div>{expensiveValue}</div>;
}
```

### 20. What is the difference between `React.createElement` and JSX?

JSX is syntactic sugar that gets compiled to React.createElement calls. JSX is more readable and looks like HTML, while createElement is the underlying function that actually creates React elements. Babel transforms JSX into createElement calls.

```jsx
// JSX syntax
const element = <h1 className="title">Hello World</h1>;

// Equivalent React.createElement
const element = React.createElement(
  'h1',
  { className: 'title' },
  'Hello World'
);
```

### 21. What is the role of `React.StrictMode`?

StrictMode is a development tool that helps identify potential problems in your application. It enables additional checks and warnings, like detecting unsafe lifecycles, deprecated APIs, and side effects. It only runs in development mode and doesn't affect production builds.

```jsx
function App() {
  return (
    <React.StrictMode>
      <Header />
      <Main />
      <Footer />
    </React.StrictMode>
  );
}
// Enables extra checks for components inside StrictMode
```

## **Intermediate React.js Concepts**

### 22. What are higher-order components (HOCs) in React?

HOCs are functions that take a component and return a new enhanced component. They're used for code reuse, like adding authentication, logging, or styling to multiple components. Think of them as component wrappers that add extra functionality.

```jsx
function withAuth(Component) {
  return function AuthenticatedComponent(props) {
    const isLoggedIn = useAuth();
    return isLoggedIn ? <Component {...props} /> : <Login />;
  };
}

const ProtectedPage = withAuth(Dashboard);
```

### 23. What is context in React and why is it used?

Context provides a way to share data across components without passing props through every level. It's perfect for global data like themes, user authentication, or language settings. It solves the "prop drilling" problem where you pass props through many components.

```jsx
const ThemeContext = createContext();

function App() {
  return (
    <ThemeContext.Provider value="dark">
      <Header />
      <Main />
    </ThemeContext.Provider>
  );
}

function Header() {
  const theme = useContext(ThemeContext);
  return <div className={theme}>Header</div>;
}
```

### 24. What is the difference between `React.createContext` and `useContext`?

createContext creates the context object that holds the shared data. useContext is a hook that consumes that context data inside components. createContext sets up the "container," useContext accesses what's inside it.

```jsx
// Create the context
const UserContext = createContext();

// Provide the context
<UserContext.Provider value={user}>
  <App />
</UserContext.Provider>

// Consume the context
function Profile() {
  const user = useContext(UserContext);
  return <div>{user.name}</div>;
}
```

### 25. What are the advantages of using functional components over class components?

Functional components are simpler, have less boilerplate code, and perform better. With hooks, they can handle state and lifecycle events just like classes. They're easier to test, understand, and the React team recommends them for new development.

```jsx
// Functional - cleaner and simpler
function Counter() {
  const [count, setCount] = useState(0);
  return <button onClick={() => setCount(count + 1)}>{count}</button>;
}

// Class - more verbose
class Counter extends React.Component {
  state = { count: 0 };
  render() {
    return <button onClick={() => this.setState({count: this.state.count + 1})}>{this.state.count}</button>;
  }
}
```

### 26. What is `setState` and how does it work in React?

setState is the method used in class components to update state. It's asynchronous and may batch multiple updates for performance. It triggers a re-render when the state changes. In functional components, we use the setter function from useState instead.

```jsx
class Counter extends React.Component {
  state = { count: 0 };
  
  increment = () => {
    this.setState({ count: this.state.count + 1 });  // Async update
    // Or with function for safer updates
    this.setState(prevState => ({ count: prevState.count + 1 }));
  };
  
  render() {
    return <button onClick={this.increment}>{this.state.count}</button>;
  }
}
```

### 27. What is the difference between `componentDidMount` and `useEffect`?

componentDidMount runs once after the component mounts in class components. useEffect can replicate this behavior with an empty dependency array, but it's more flexible - it can run on every render, on specific changes, or just once.

```jsx
// Class component
class MyComponent extends React.Component {
  componentDidMount() {
    fetchData();  // Runs once after mount
  }
}

// Functional component
function MyComponent() {
  useEffect(() => {
    fetchData();  // Runs once after mount
  }, []);  // Empty array = componentDidMount behavior
}
```

### 28. How can you optimize performance in React applications?

Use React.memo to prevent unnecessary re-renders, useMemo for expensive calculations, and useCallback for stable function references. Implement code splitting with lazy loading, optimize images, and use the React DevTools Profiler to identify bottlenecks.

```jsx
// Memoize component
const ExpensiveComponent = React.memo(({ data }) => {
  return <div>{data.map(item => <Item key={item.id} {...item} />)}</div>;
});

// Memoize calculations
const total = useMemo(() => items.reduce((sum, item) => sum + item.price, 0), [items]);

// Lazy load components
const LazyComponent = lazy(() => import('./LazyComponent'));
```

### 29. What are React fragments?

Fragments let you group multiple elements without adding extra DOM nodes. Instead of wrapping elements in a div, you can use React.Fragment or the shorthand syntax <>. This keeps your DOM clean and avoids unnecessary wrapper elements.

```jsx
// Without Fragment - adds extra div
function App() {
  return (
    <div>
      <h1>Title</h1>
      <p>Content</p>
    </div>
  );
}

// With Fragment - no extra DOM node
function App() {
  return (
    <>
      <h1>Title</h1>
      <p>Content</p>
    </>
  );
}
```

### 30. What are error boundaries in React?

Error boundaries are class components that catch JavaScript errors in their child component tree and display fallback UI instead of crashing the whole app. They're like try-catch blocks for React components, helping create more resilient applications.

```jsx
class ErrorBoundary extends React.Component {
  state = { hasError: false };
  
  static getDerivedStateFromError(error) {
    return { hasError: true };
  }
  
  render() {
    if (this.state.hasError) {
      return <h1>Something went wrong.</h1>;
    }
    return this.props.children;
  }
}

// Usage
<ErrorBoundary>
  <MyComponent />
</ErrorBoundary>
```

### 31. What is the purpose of `shouldComponentUpdate`?

shouldComponentUpdate is a lifecycle method in class components that controls whether a component should re-render. It receives new props and state, and returns true or false. It's a performance optimization to prevent unnecessary renders when data hasn't actually changed.

```jsx
class MyComponent extends React.Component {
  shouldComponentUpdate(nextProps, nextState) {
    return nextProps.id !== this.props.id || nextState.count !== this.state.count;
  }
  
  render() {
    return <div>{this.props.id}: {this.state.count}</div>;
  }
}

// Modern equivalent: React.memo
const MyComponent = React.memo(({ id, count }) => {
  return <div>{id}: {count}</div>;
});
```

### 32. What are portals in React?

Portals let you render components outside their parent DOM hierarchy while maintaining the React component tree relationship. They're perfect for modals, tooltips, or dropdowns that need to escape overflow constraints or z-index issues.

```jsx
import { createPortal } from 'react-dom';

function Modal({ children, isOpen }) {
  if (!isOpen) return null;
  
  return createPortal(
    <div className="modal-overlay">
      <div className="modal">{children}</div>
    </div>,
    document.getElementById('modal-root')
  );
}

// Usage
<Modal isOpen={showModal}>
  <p>This renders outside the parent DOM tree!</p>
</Modal>
```

### 33. How do you handle events in React?

React uses synthetic events that wrap native DOM events for consistency across browsers. Event handlers receive the event object as a parameter. React uses event delegation, so you attach handlers to JSX elements, not directly to DOM nodes.

```jsx
function Button() {
  const handleClick = (event) => {
    event.preventDefault();
    console.log('Button clicked!', event.target);
  };
  
  const handleSubmit = (event) => {
    event.preventDefault();
    const formData = new FormData(event.target);
  };
  
  return (
    <form onSubmit={handleSubmit}>
      <button onClick={handleClick}>Click me</button>
    </form>
  );
}
```

### 34. What is React Router and how do you use it?

React Router enables client-side routing in single-page applications. It lets you navigate between different views without full page reloads. You define routes that map URLs to components, creating a seamless navigation experience.

```jsx
import { BrowserRouter, Routes, Route, Link } from 'react-router-dom';

function App() {
  return (
    <BrowserRouter>
      <nav>
        <Link to="/">Home</Link>
        <Link to="/about">About</Link>
      </nav>
      
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/about" element={<About />} />
        <Route path="/users/:id" element={<UserProfile />} />
      </Routes>
    </BrowserRouter>
  );
}
```

### 35. What are the `<Router>` components of React Router v6?

React Router v6 has BrowserRouter for standard web apps, HashRouter for static hosting, and MemoryRouter for testing. BrowserRouter uses HTML5 history API, HashRouter uses URL hash, and MemoryRouter keeps history in memory.

```jsx
import { BrowserRouter, HashRouter, MemoryRouter } from 'react-router-dom';

// Standard web apps
<BrowserRouter>
  <App />
</BrowserRouter>

// Static hosting (GitHub Pages)
<HashRouter>
  <App />
</HashRouter>

// Testing or React Native
<MemoryRouter>
  <App />
</MemoryRouter>
```

### 36. What is lazy loading in React?

Lazy loading delays loading components until they're needed, reducing initial bundle size. React.lazy() creates a lazy component that's loaded dynamically. You wrap lazy components in Suspense to show fallback content while loading.

```jsx
import { lazy, Suspense } from 'react';

const LazyComponent = lazy(() => import('./LazyComponent'));

function App() {
  return (
    <div>
      <Suspense fallback={<div>Loading...</div>}>
        <LazyComponent />
      </Suspense>
    </div>
  );
}

// Code splitting at route level
const Home = lazy(() => import('./pages/Home'));
const About = lazy(() => import('./pages/About'));
```

### 37. What is the difference between state and context?

State is local data that belongs to a specific component and triggers re-renders when changed. Context is for sharing data across multiple components without prop drilling. State is for component-specific data, context is for global or widely-shared data.

```jsx
// State - local to component
function Counter() {
  const [count, setCount] = useState(0);  // Local state
  return <button onClick={() => setCount(count + 1)}>{count}</button>;
}

// Context - shared across components
const ThemeContext = createContext();
function App() {
  return (
    <ThemeContext.Provider value="dark">  {/* Global context */}
      <Header />
      <Main />
    </ThemeContext.Provider>
  );
}
```

### 38. How can you manage global state in a React application?

You can use React Context for simple global state, Redux for complex state management, or Zustand for a lighter alternative. Context works well for themes and user data, while Redux handles complex business logic with time-travel debugging.

```jsx
// Context for simple global state
const AppContext = createContext();
function AppProvider({ children }) {
  const [user, setUser] = useState(null);
  return (
    <AppContext.Provider value={{ user, setUser }}>
      {children}
    </AppContext.Provider>
  );
}

// Redux for complex state
import { configureStore } from '@reduxjs/toolkit';
const store = configureStore({
  reducer: { user: userReducer, posts: postsReducer }
});
```

## **Advanced React.js Concepts**

### 39. What is Server-Side Rendering (SSR) in React?

SSR renders React components on the server and sends HTML to the browser, improving initial load time and SEO. The client then "hydrates" the HTML to make it interactive. Frameworks like Next.js make SSR easy by handling the server setup automatically.

```jsx
// Next.js SSR example
export async function getServerSideProps() {
  const data = await fetch('https://api.example.com/posts');
  return { props: { posts: await data.json() } };
}

function Posts({ posts }) {
  return (
    <div>
      {posts.map(post => <div key={post.id}>{post.title}</div>)}
    </div>
  );
}
```

### 40. What are React Suspense and Concurrent Mode?

Suspense lets you show fallback content while waiting for components to load, like with lazy loading or data fetching. Concurrent Mode allows React to interrupt rendering to handle high-priority updates, making apps more responsive to user interactions.

```jsx
import { Suspense, lazy } from 'react';

const LazyComponent = lazy(() => import('./LazyComponent'));

function App() {
  return (
    <Suspense fallback={<div>Loading...</div>}>
      <LazyComponent />
    </Suspense>
  );
}

// With data fetching (experimental)
<Suspense fallback={<Spinner />}>
  <UserProfile userId={123} />
</Suspense>
```

### 41. What is code splitting in React?

Code splitting breaks your bundle into smaller chunks that load on demand, reducing initial load time. React.lazy() and dynamic imports make this easy. You can split at the route level or component level based on user interactions.

```jsx
import { lazy, Suspense } from 'react';

// Route-level splitting
const Home = lazy(() => import('./pages/Home'));
const About = lazy(() => import('./pages/About'));

// Component-level splitting
const handleClick = async () => {
  const { heavyFunction } = await import('./heavyModule');
  heavyFunction();
};

<Suspense fallback={<div>Loading...</div>}>
  <Routes>
    <Route path="/" element={<Home />} />
    <Route path="/about" element={<About />} />
  </Routes>
</Suspense>
```

### 42. What is React Fiber?

React Fiber is the new reconciliation engine that replaced the old stack reconciler. It enables features like time slicing, where React can pause work and resume later, making apps more responsive. It's the foundation for Concurrent Mode and Suspense.

```jsx
// Fiber enables interrupting low-priority updates
function App() {
  const [count, setCount] = useState(0);
  const [text, setText] = useState('');
  
  // High priority - user input
  const handleInput = (e) => setText(e.target.value);
  
  // Low priority - can be interrupted
  const heavyRender = () => {
    return Array.from({length: 1000}, (_, i) => <div key={i}>{count}</div>);
  };
  
  return (
    <div>
      <input onChange={handleInput} value={text} />
      {heavyRender()}
    </div>
  );
}
```

### 43. What is React's reconciliation algorithm?

Reconciliation is how React updates the DOM efficiently by comparing the new virtual DOM tree with the previous one. It uses a diffing algorithm that assumes components of different types produce different trees and uses keys to identify list items.

```jsx
// React efficiently updates only what changed
function TodoList({ todos }) {
  return (
    <ul>
      {todos.map(todo => (
        <li key={todo.id} className={todo.completed ? 'done' : ''}>
          {todo.text}
        </li>
      ))}
    </ul>
  );
}

// Keys help React identify which items changed, moved, or were removed
```

### 44. How does React handle forms?

React handles forms through controlled components where form data is managed by React state, or uncontrolled components using refs. Controlled components give you full control over validation and user input, while uncontrolled components are simpler for basic forms.

```jsx
// Controlled form
function LoginForm() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  
  const handleSubmit = (e) => {
    e.preventDefault();
    login({ email, password });
  };
  
  return (
    <form onSubmit={handleSubmit}>
      <input value={email} onChange={(e) => setEmail(e.target.value)} />
      <input value={password} onChange={(e) => setPassword(e.target.value)} />
      <button type="submit">Login</button>
    </form>
  );
}
```

### 45. What is the significance of the `key` prop in lists?

Keys help React identify which list items have changed, been added, or removed during re-renders. They should be stable and unique among siblings. Using array indices as keys can cause issues when the list order changes, so use unique IDs when possible.

```jsx
// Good - stable unique keys
function UserList({ users }) {
  return (
    <ul>
      {users.map(user => (
        <li key={user.id}>{user.name}</li>  // Stable ID
      ))}
    </ul>
  );
}

// Avoid - index as key when order can change
{users.map((user, index) => (
  <li key={index}>{user.name}</li>  // Can cause issues
))}
```

### 46. How do you implement custom hooks in React?

Custom hooks are functions that start with "use" and can call other hooks. They let you extract component logic into reusable functions. They're perfect for sharing stateful logic between components without changing the component hierarchy.

```jsx
// Custom hook for API data fetching
function useApi(url) {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);
  
  useEffect(() => {
    fetch(url)
      .then(res => res.json())
      .then(data => {
        setData(data);
        setLoading(false);
      });
  }, [url]);
  
  return { data, loading };
}

// Usage
function UserProfile({ userId }) {
  const { data: user, loading } = useApi(`/api/users/${userId}`);
  return loading ? <div>Loading...</div> : <div>{user.name}</div>;
}
```

### 47. What is the difference between `componentWillMount`, `componentDidMount`, and `getDerivedStateFromProps`?

componentWillMount is deprecated and unsafe. componentDidMount runs after the component mounts, perfect for API calls. getDerivedStateFromProps is a static method that returns new state based on props changes, replacing componentWillReceiveProps.

```jsx
class MyComponent extends React.Component {
  // Deprecated - don't use
  // componentWillMount() { }
  
  componentDidMount() {
    // Safe for API calls, DOM access
    this.fetchData();
  }
  
  static getDerivedStateFromProps(props, state) {
    // Pure function - no side effects
    if (props.userId !== state.prevUserId) {
      return { prevUserId: props.userId, user: null };
    }
    return null;
  }
}
```

### 48. What are React DevTools and how do they help in debugging?

React DevTools is a browser extension that lets you inspect React component trees, view props and state, and profile performance. You can see component hierarchies, track state changes, and identify performance bottlenecks in your React applications.

```jsx
// DevTools shows component tree like this:
// App
//   ├── Header (props: {title: "My App"})
//   ├── UserList (state: {users: [...], loading: false})
//   └── Footer

// You can also add displayName for better debugging
function MyComponent() {
  return <div>Hello</div>;
}
MyComponent.displayName = 'MyCustomComponent';
```

### 49. How do you test React components?

Use testing libraries like Jest and React Testing Library. Write tests that focus on user behavior rather than implementation details. Test what users see and do - render components, simulate user interactions, and assert on the results.

```jsx
import { render, screen, fireEvent } from '@testing-library/react';

function Counter() {
  const [count, setCount] = useState(0);
  return (
    <div>
      <span>Count: {count}</span>
      <button onClick={() => setCount(count + 1)}>Increment</button>
    </div>
  );
}

test('increments counter on button click', () => {
  render(<Counter />);
  expect(screen.getByText('Count: 0')).toBeInTheDocument();
  fireEvent.click(screen.getByText('Increment'));
  expect(screen.getByText('Count: 1')).toBeInTheDocument();
});
```

### 50. What is the `useReducer` hook and how does it differ from `useState`?

useReducer manages complex state logic with a reducer function, similar to Redux. It's better than useState when you have multiple related state values or complex state transitions. The reducer receives current state and an action, returning new state.

```jsx
function counterReducer(state, action) {
  switch (action.type) {
    case 'increment': return { count: state.count + 1 };
    case 'decrement': return { count: state.count - 1 };
    case 'reset': return { count: 0 };
    default: return state;
  }
}

function Counter() {
  const [state, dispatch] = useReducer(counterReducer, { count: 0 });
  
  return (
    <div>
      <span>{state.count}</span>
      <button onClick={() => dispatch({ type: 'increment' })}>+</button>
      <button onClick={() => dispatch({ type: 'decrement' })}>-</button>
    </div>
  );
}
```

### 51. What are synthetic events in React?

Synthetic events are React's wrapper around native DOM events that provide consistent behavior across different browsers. They have the same interface as native events but work identically everywhere. React uses event delegation, attaching one listener to the document root.

```jsx
function Button() {
  const handleClick = (event) => {
    // event is a SyntheticEvent, not native Event
    event.preventDefault();
    event.stopPropagation();
    console.log(event.type); // "click"
    console.log(event.target); // button element
    
    // Access native event if needed
    console.log(event.nativeEvent);
  };
  
  return <button onClick={handleClick}>Click me</button>;
}
```

### 52. How do you handle side effects in React?

Side effects are handled with useEffect in functional components or lifecycle methods in class components. Side effects include API calls, subscriptions, timers, or DOM manipulation. Always clean up side effects to prevent memory leaks.

```jsx
function UserProfile({ userId }) {
  const [user, setUser] = useState(null);
  
  useEffect(() => {
    // Side effect: API call
    const fetchUser = async () => {
      const response = await fetch(`/api/users/${userId}`);
      setUser(await response.json());
    };
    
    fetchUser();
    
    // Cleanup function
    return () => {
      // Cancel requests, clear timers, etc.
    };
  }, [userId]);
  
  return <div>{user?.name}</div>;
}
```

### 53. What is the lifecycle of a React component? Explain both class-based and functional components' lifecycles.

Class components have mounting, updating, and unmounting phases with specific lifecycle methods. Functional components use useEffect to replicate this behavior. useEffect with different dependency arrays mimics componentDidMount, componentDidUpdate, and componentWillUnmount.

```jsx
// Class component lifecycle
class MyComponent extends React.Component {
  componentDidMount() { /* after mount */ }
  componentDidUpdate() { /* after update */ }
  componentWillUnmount() { /* before unmount */ }
}

// Functional component equivalent
function MyComponent() {
  useEffect(() => {
    // componentDidMount
    return () => {
      // componentWillUnmount
    };
  }, []); // Empty array = mount/unmount only
  
  useEffect(() => {
    // componentDidUpdate for specific values
  }, [someValue]);
}
```

### 54. Explain React's Context API and when you would use it instead of prop drilling.

Context API provides a way to share data across components without passing props through every level. Use it for global data like themes, authentication, or language settings. It's perfect when many components need the same data, avoiding the tedious prop drilling pattern.

```jsx
// Create context
const ThemeContext = createContext();

// Provider component
function App() {
  const [theme, setTheme] = useState('dark');
  
  return (
    <ThemeContext.Provider value={{ theme, setTheme }}>
      <Header />
      <Main />
      <Footer />
    </ThemeContext.Provider>
  );
}

// Consumer component (deep in the tree)
function ThemeButton() {
  const { theme, setTheme } = useContext(ThemeContext);
  return <button onClick={() => setTheme(theme === 'dark' ? 'light' : 'dark')}>{theme}</button>;
}
```
## **React Router Questions**
### 55. Explain the concept of "render props" and provide an example.

Render props is a pattern where a component receives a function as a prop that returns JSX. This function gets called with data from the component, allowing you to share stateful logic between components. It's an alternative to HOCs for code reuse.

```jsx
// Component with render prop
function MouseTracker({ render }) {
  const [position, setPosition] = useState({ x: 0, y: 0 });
  
  useEffect(() => {
    const handleMouseMove = (e) => {
      setPosition({ x: e.clientX, y: e.clientY });
    };
    
    window.addEventListener('mousemove', handleMouseMove);
    return () => window.removeEventListener('mousemove', handleMouseMove);
  }, []);
  
  return render(position);
}

// Usage
<MouseTracker render={({ x, y }) => (
  <div>Mouse position: {x}, {y}</div>
)} />
```

### 56. How would you implement routing in a React application? What is React Router, and how does it work?

React Router enables client-side routing in SPAs by matching URLs to components without full page reloads. Install react-router-dom, wrap your app in BrowserRouter, define Routes with path-to-component mappings, and use Link for navigation.

```jsx
import { BrowserRouter, Routes, Route, Link } from 'react-router-dom';

function App() {
  return (
    <BrowserRouter>
      <nav>
        <Link to="/">Home</Link>
        <Link to="/about">About</Link>
        <Link to="/users">Users</Link>
      </nav>
      
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/about" element={<About />} />
        <Route path="/users" element={<Users />} />
        <Route path="/users/:id" element={<UserDetail />} />
      </Routes>
    </BrowserRouter>
  );
}
```

### 57. What are dynamic routes, and how would you implement them in React Router?

Dynamic routes use URL parameters to render different content based on the URL. Use colon syntax like `:id` to define parameters, then access them with useParams hook. This lets you create flexible routes for user profiles, product pages, or any parameterized content.

```jsx
import { useParams } from 'react-router-dom';

// Route definition
<Route path="/users/:userId" element={<UserProfile />} />
<Route path="/posts/:postId/comments/:commentId" element={<Comment />} />

// Component using parameters
function UserProfile() {
  const { userId } = useParams();
  const [user, setUser] = useState(null);
  
  useEffect(() => {
    fetch(`/api/users/${userId}`)
      .then(res => res.json())
      .then(setUser);
  }, [userId]);
  
  return <div>User: {user?.name}</div>;
}
```

### 58. How can you handle nested routes in React Router?

Nested routes let you render components inside other components based on URL structure. Define child routes inside parent route components using Routes and Route. Use Outlet in the parent to render matched child components.

```jsx
import { Outlet } from 'react-router-dom';

// Parent component
function Dashboard() {
  return (
    <div>
      <h1>Dashboard</h1>
      <nav>
        <Link to="profile">Profile</Link>
        <Link to="settings">Settings</Link>
      </nav>
      <Outlet /> {/* Child routes render here */}
    </div>
  );
}

// Route configuration
<Routes>
  <Route path="/dashboard" element={<Dashboard />}>
    <Route path="profile" element={<Profile />} />
    <Route path="settings" element={<Settings />} />
  </Route>
</Routes>
```

## **Performance Optimization Questions**
### 59. What is the purpose of useParams, useLocation, and useHistory (or useNavigate in React Router v6)?

useParams extracts URL parameters, useLocation gives current location info including pathname and search params, and useNavigate (v6) or useHistory (v5) enables programmatic navigation. These hooks provide access to routing information and navigation control.

```jsx
import { useParams, useLocation, useNavigate } from 'react-router-dom';

function MyComponent() {
  const { id } = useParams(); // Get URL parameters
  const location = useLocation(); // Current location info
  const navigate = useNavigate(); // Navigation function
  
  const handleClick = () => {
    navigate('/dashboard'); // Programmatic navigation
    // navigate(-1); // Go back
    // navigate('/users', { replace: true }); // Replace current entry
  };
  
  console.log(location.pathname); // "/users/123"
  console.log(location.search); // "?tab=profile"
  
  return <button onClick={handleClick}>Go to Dashboard</button>;
}
```

### 60. What strategies can you use to avoid unnecessary re-renders in React?

Use React.memo to memoize components, useMemo for expensive calculations, and useCallback for stable function references. Split components to isolate state changes, move state down closer to where it's used, and use proper key props in lists to help React identify changes efficiently.

```jsx
// Memoize component
const ExpensiveComponent = React.memo(({ data }) => {
  return <div>{data.map(item => <Item key={item.id} {...item} />)}</div>;
});

// Memoize calculations and callbacks
function Parent({ items }) {
  const total = useMemo(() => items.reduce((sum, item) => sum + item.price, 0), [items]);
  const handleClick = useCallback((id) => console.log(id), []);
  
  return <ExpensiveComponent data={items} onClick={handleClick} />;
}
```

### 61. How would you deal with memory leaks in React components?

Clean up subscriptions, timers, and event listeners in useEffect cleanup functions or componentWillUnmount. Cancel pending API requests when components unmount. Use AbortController for fetch requests and clear intervals/timeouts to prevent memory leaks.

```jsx
function MyComponent() {
  useEffect(() => {
    const controller = new AbortController();
    const timer = setInterval(() => console.log('tick'), 1000);
    
    // API call with abort signal
    fetch('/api/data', { signal: controller.signal })
      .then(res => res.json())
      .catch(err => {
        if (err.name !== 'AbortError') console.error(err);
      });
    
    // Cleanup function
    return () => {
      controller.abort();
      clearInterval(timer);
    };
  }, []);
}
```

## **State Management Questions**
### 62. What is Redux, and how does it help in state management in React?

Redux is a predictable state container that centralizes application state in a single store. It helps manage complex state across multiple components using actions to describe changes and reducers to handle state updates. It provides time-travel debugging and makes state changes predictable.

```jsx
// Store setup
import { configureStore } from '@reduxjs/toolkit';

const store = configureStore({
  reducer: {
    counter: counterReducer,
    user: userReducer
  }
});

// Component usage
import { useSelector, useDispatch } from 'react-redux';

function Counter() {
  const count = useSelector(state => state.counter.value);
  const dispatch = useDispatch();
  
  return (
    <button onClick={() => dispatch({ type: 'counter/increment' })}>
      {count}
    </button>
  );
}
```

### 63. What are the principles of Redux (actions, reducers, store)?

Redux follows three principles: single source of truth (one store), state is read-only (only actions can change it), and changes are made with pure functions (reducers). Actions describe what happened, reducers specify how state changes, and the store holds the state tree.

```jsx
// Action
const increment = () => ({ type: 'INCREMENT' });

// Reducer
function counterReducer(state = { count: 0 }, action) {
  switch (action.type) {
    case 'INCREMENT':
      return { count: state.count + 1 };
    default:
      return state;
  }
}

// Store
const store = createStore(counterReducer);

// Usage
store.dispatch(increment());
console.log(store.getState()); // { count: 1 }
```

### 64. Explain the difference between local component state and global state in React applications.

Local state belongs to a specific component and is managed with useState or this.state. Global state is shared across multiple components and managed with Context, Redux, or other state management libraries. Use local state for component-specific data, global state for shared data.

```jsx
// Local state - component specific
function Counter() {
  const [count, setCount] = useState(0); // Local to this component
  return <button onClick={() => setCount(count + 1)}>{count}</button>;
}

// Global state - shared across app
const UserContext = createContext();
function App() {
  const [user, setUser] = useState(null); // Global state
  return (
    <UserContext.Provider value={{ user, setUser }}>
      <Header />
      <Profile />
    </UserContext.Provider>
  );
}
```

### 65. How do you handle asynchronous actions in Redux?

Use middleware like Redux Thunk or Redux Saga to handle async actions. Thunk allows action creators to return functions instead of plain objects, enabling async operations like API calls. Dispatch actions for loading, success, and error states.

```jsx
// Redux Thunk action creator
const fetchUser = (userId) => {
  return async (dispatch) => {
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
```

### 66. What are Redux middleware, and can you give an example of how you'd use `redux-thunk` or `redux-saga`?

Middleware sits between dispatching an action and reaching the reducer, enabling side effects like logging, async operations, or routing. Redux Thunk allows functions as actions, while Redux Saga uses generator functions for complex async flows with better testing and cancellation.

```jsx
// Redux Thunk setup
import { configureStore } from '@reduxjs/toolkit';
import thunk from 'redux-thunk';

const store = configureStore({
  reducer: rootReducer,
  middleware: [thunk]
});

// Thunk action
const loginUser = (credentials) => async (dispatch) => {
  const response = await fetch('/api/login', {
    method: 'POST',
    body: JSON.stringify(credentials)
  });
  const user = await response.json();
  dispatch({ type: 'LOGIN_SUCCESS', payload: user });
};
```

## **Testing Questions**
### 67. Explain how you would structure your Redux store in a large application.

Organize by feature domains, not by data type. Each feature has its own slice with actions, reducers, and selectors. Use Redux Toolkit's createSlice for cleaner code. Keep the store flat, normalize nested data, and use selectors to compute derived state.

```jsx
// Feature-based structure
// store/
//   ├── userSlice.js
//   ├── postsSlice.js
//   └── index.js

// userSlice.js
import { createSlice } from '@reduxjs/toolkit';

const userSlice = createSlice({
  name: 'user',
  initialState: { profile: null, loading: false },
  reducers: {
    setUser: (state, action) => {
      state.profile = action.payload;
    },
    setLoading: (state, action) => {
      state.loading = action.payload;
    }
  }
});

// store/index.js
export const store = configureStore({
  reducer: {
    user: userSlice.reducer,
    posts: postsSlice.reducer
  }
});
```

### 68. What testing libraries are commonly used in React applications, and how do you write unit tests for components?

Jest and React Testing Library are the most popular choices. Jest provides the test runner and assertions, while React Testing Library focuses on testing user behavior rather than implementation details. Write tests that simulate user interactions and verify what users see.

```jsx
import { render, screen, fireEvent } from '@testing-library/react';
import Counter from './Counter';

test('increments counter when button is clicked', () => {
  render(<Counter />);
  
  const button = screen.getByRole('button', { name: /increment/i });
  const count = screen.getByText('0');
  
  fireEvent.click(button);
  
  expect(screen.getByText('1')).toBeInTheDocument();
});
```

### 69. How would you test a component that interacts with external APIs or services?

Mock the API calls using Jest's mocking capabilities or MSW (Mock Service Worker). Test the loading states, success responses, and error handling. Focus on how the component behaves with different API responses rather than testing the actual API.

```jsx
import { render, screen, waitFor } from '@testing-library/react';
import UserProfile from './UserProfile';

// Mock the fetch function
global.fetch = jest.fn();

test('displays user data after loading', async () => {
  fetch.mockResolvedValueOnce({
    json: async () => ({ name: 'John Doe', email: 'john@example.com' })
  });
  
  render(<UserProfile userId="123" />);
  
  expect(screen.getByText('Loading...')).toBeInTheDocument();
  
  await waitFor(() => {
    expect(screen.getByText('John Doe')).toBeInTheDocument();
  });
});
```

### 70. What is the difference between shallow rendering and full rendering in tests, and when would you use each?

Shallow rendering only renders the component itself without its children, while full rendering renders the entire component tree. Use shallow rendering for unit tests focused on a single component, and full rendering for integration tests that need child components.

```jsx
// React Testing Library (full rendering by default)
import { render } from '@testing-library/react';

test('full rendering - tests complete component tree', () => {
  render(<App />); // Renders App and all its children
});

// Enzyme shallow rendering (less common now)
import { shallow } from 'enzyme';

test('shallow rendering - tests only App component', () => {
  const wrapper = shallow(<App />); // Only renders App, not children
});
```

### 71. Explain the concept of "snapshot testing" in the context of React.

Snapshot testing captures the rendered output of a component and saves it as a reference. Future test runs compare against this snapshot to detect unexpected changes. It's useful for catching unintended UI changes but should be combined with behavioral tests.

```jsx
import { render } from '@testing-library/react';
import Button from './Button';

test('Button component matches snapshot', () => {
  const { container } = render(<Button>Click me</Button>);
  expect(container.firstChild).toMatchSnapshot();
});

// Creates __snapshots__/Button.test.js.snap file
// exports[`Button component matches snapshot 1`] = `
// <button>
//   Click me
// </button>
// `;
```

### 72. What is the role of mocking in React tests, and how do you mock modules or APIs in Jest?

Mocking replaces real dependencies with controlled fake implementations, allowing you to test components in isolation. Mock external APIs, complex child components, or third-party libraries to focus on the component's logic without external dependencies.

```jsx
// Mock a module
jest.mock('./api', () => ({
  fetchUser: jest.fn()
}));

// Mock a React component
jest.mock('./ComplexChild', () => {
  return function MockedComplexChild() {
    return <div>Mocked Complex Child</div>;
  };
});

// Use mocked functions in tests
import { fetchUser } from './api';

test('handles API error', async () => {
  fetchUser.mockRejectedValue(new Error('API Error'));
  
  render(<UserProfile />);
  
  await waitFor(() => {
    expect(screen.getByText('Error loading user')).toBeInTheDocument();
  });
});
```

## **Build Tools & Development Workflow Questions**
### 73. What build tools or bundlers are you familiar with (e.g., Webpack, Vite)? Explain their roles in a React project.

Webpack bundles JavaScript modules, handles assets, and provides development server with hot reloading. Vite is faster for development with native ES modules and instant server start. Both transform JSX, handle CSS, optimize for production, and manage dependencies.

```jsx
// Webpack config example
module.exports = {
  entry: './src/index.js',
  module: {
    rules: [
      {
        test: /\.jsx?$/,
        use: 'babel-loader',
        exclude: /node_modules/
      },
      {
        test: /\.css$/,
        use: ['style-loader', 'css-loader']
      }
    ]
  }
};

// Vite config
export default {
  plugins: [react()],
  server: { port: 3000 }
};
```

### 74. How do you configure Babel and Webpack for a React application?

Babel transforms JSX and modern JavaScript to browser-compatible code using presets like @babel/preset-react. Webpack bundles files using loaders like babel-loader for JavaScript and css-loader for styles. Create React App handles this configuration automatically.

```jsx
// .babelrc
{
  "presets": [
    "@babel/preset-env",
    "@babel/preset-react"
  ]
}

// webpack.config.js
module.exports = {
  module: {
    rules: [
      {
        test: /\.jsx?$/,
        exclude: /node_modules/,
        use: {
          loader: 'babel-loader'
        }
      }
    ]
  }
};
```

### 75. What is the role of `.env` files in React development? How do you manage environment variables in a React app?

Environment files store configuration like API URLs, keys, and feature flags for different environments. React requires variables to start with REACT_APP_ for security. Use different .env files for development, staging, and production environments.

```jsx
// .env.development
REACT_APP_API_URL=http://localhost:3001
REACT_APP_DEBUG_MODE=true

// .env.production
REACT_APP_API_URL=https://api.myapp.com
REACT_APP_DEBUG_MODE=false

// Usage in React
function App() {
  const apiUrl = process.env.REACT_APP_API_URL;
  const isDebug = process.env.REACT_APP_DEBUG_MODE === 'true';
  
  return <div>API: {apiUrl}</div>;
}
```

### 76. What is CI/CD, and how would you set up continuous integration and deployment for a React application?

CI/CD automates testing, building, and deploying code changes. Continuous Integration runs tests on every commit, Continuous Deployment automatically deploys passing builds. Use GitHub Actions, Jenkins, or similar tools to run tests, build the app, and deploy to hosting platforms.

```yaml
# .github/workflows/deploy.yml
name: Deploy React App

on:
  push:
    branches: [main]

jobs:
  deploy:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
      
      - name: Setup Node.js
        uses: actions/setup-node@v2
        with:
          node-version: '16'
          
      - name: Install dependencies
        run: npm ci
        
      - name: Run tests
        run: npm test -- --coverage --watchAll=false
        
      - name: Build app
        run: npm run build
        
      - name: Deploy to Netlify
        run: npx netlify-cli deploy --prod --dir=build
```
# React.js Interview Questions

## **Basic React.js Concepts**
## **1. What is React?**

* React is a **JavaScript library** used to build **fast and interactive user interfaces**
* It focuses on the **view layer** of an application
* React is **component-based**, so UIs are built by combining small reusable pieces
* It’s maintained by **Meta (Facebook)** and widely used in real-world apps

```jsx
function App() {
  return <h1>Hello React</h1>;
}
```

---

## **2. What is JSX?**

* JSX stands for **JavaScript XML**
* It lets us write **HTML-like code inside JavaScript**
* JSX makes UI code easier to read and understand
* Browsers don’t understand JSX, so it’s converted to JavaScript by Babel

```jsx
const element = <h1>Hello, {name}!</h1>;
// Becomes: React.createElement('h1', null, 'Hello, ', name, '!')
```

---

## **3. What is the difference between React and ReactDOM**

* **React** is used to **create components and manage logic**
* **ReactDOM** is used to **render React components to the browser**
* React works behind the scenes, ReactDOM interacts with the DOM

```jsx
import React from 'react';
import ReactDOM from 'react-dom/client';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(<App />);
```

---

## **4. How does the React development workflow (npm start, Webpack, Babel) work?**

* `npm start` runs the development server
* **Webpack** bundles all JS, CSS, and assets into one optimized file
* **Babel** converts modern JavaScript and JSX into browser-compatible code
* This setup gives fast reloads and optimized builds

```bash
npm start  # Starts development server
npm run build  # Creates production bundle
```

---

## **5. What are components in React?**

* Components are **reusable building blocks** of the UI
* Each component represents a **part of the screen**
* Components can accept **props** and manage their own **state**

```jsx
function Header() {
  return <h1>My App</h1>;
}
```

---

## **6. What are the types of components in React?**

* **Functional Components** – simple functions, most commonly used
* **Class Components** – older style, uses classes and lifecycle methods
* Today, functional components with hooks are preferred

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

---

## **7. What is the difference between functional and class components?**

* Functional components are **simpler and cleaner**
* Class components use `this` and lifecycle methods
* Hooks allow functional components to use state and effects
* Functional components are the modern standard

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

---

## **8. What is the Virtual DOM?**

* The Virtual DOM is a **lightweight copy of the real DOM**
* React updates the Virtual DOM first
* It compares changes and updates only what’s needed in the real DOM
* This makes React **fast and efficient**

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

---

## **9. What are props in React?**

* Props are **inputs passed from parent to child components**
* They are **read-only** and cannot be modified by the child
* Props help make components **reusable and dynamic**

```jsx
function Greeting(props) {
  return <h1>Hello, {props.name}!</h1>;
}

// Usage
<Greeting name="Alice" />
<Greeting name="Bob" />
```

---

## **10. What are state and how does it differ from props?**

* **State** is managed inside a component
* **Props** come from parent components
* State can change; props are read-only
* State is used for dynamic data like user input

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

---

## **11. What is a key in React and why is it important?**

* A key is a **unique identifier** used when rendering lists
* It helps React understand **which items changed, added, or removed**
* Keys improve **performance** and prevent UI bugs
* Usually, we use an **id**, not an index

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

---

## **12. What are controlled components in React?**

* Controlled components are form inputs **controlled by React state**
* The input value comes from `useState`
* Every change updates the state
* This gives better control and validation

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

---

## **13. What are uncontrolled components in React?**

* Uncontrolled components store form data **in the DOM, not state**
* We use `useRef` to access values
* Less code, but less control
* Useful for quick or simple forms

```jsx
function LoginForm() {
  const emailRef = useRef();
  
  const handleSubmit = () => {
    console.log(emailRef.current.value);  // Access via ref
  };
  
  return <input ref={emailRef} />;  // Uncontrolled
}
```

---

## **14. What is the purpose of the `render()` method in React?**

* `render()` defines **what UI should appear on the screen**
* It returns JSX
* React calls it whenever state or props change
* Used only in **class components**

```jsx
class Welcome extends React.Component {
  render() {
    return <h1>Hello, {this.props.name}!</h1>;  // Must return JSX
  }
}
```

---

## **15. What are React hooks? Can you name some of them?**

* Hooks let functional components **use state and lifecycle features**
* They remove the need for class components
* Common hooks include:

  * `useState`
  * `useEffect`
  * `useRef`
  * `useContext`

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

---

## **16. What is the difference between `useState` and `useEffect` hooks?**

* `useState` manages **data**
* `useEffect` manages **side effects**
* `useState` updates UI
* `useEffect` runs code after render

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

---

## **17. What is the `useEffect` hook and how does it work?**

* `useEffect` runs **after the component renders**
* Used for API calls, timers, subscriptions
* Dependency array controls when it runs
* Cleanup prevents memory leaks

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

---

## **18. What is `useRef` in React?**

* `useRef` stores a **mutable value** without causing re-renders
* Often used to access **DOM elements**
* Value persists between renders

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

---

## **19. What is the purpose of `useMemo` and `useCallback` hooks?**

* Both are used for **performance optimization**
* `useMemo` memoizes a **value** - useMemo memoizes expensive calculations to avoid recalculating on every render
* `useCallback` memoizes a **function** - useCallback memoizes functions to prevent child components from re-rendering unnecessarily.
* Helps avoid unnecessary re-renders

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

---

## **20. What is the difference between `React.createElement` and JSX?**

* JSX is **syntactic sugar** for `React.createElement`
* JSX is easier to read and write
* Both do the same thing internally

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

---

## **21. What is the role of `React.StrictMode`?**

* `StrictMode` helps find **potential problems**
* It runs checks in development only
* Helps detect unsafe lifecycle methods
* Does not affect production

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

---

## **22. What are Higher-Order Components (HOCs)?**

* A Higher-Order Component is a **function that takes a component and returns a new component**
* Used to **reuse logic** like authentication or logging
* Does not modify the original component
* Common in older React codebases

```jsx
function withAuth(Component) {
  return function AuthenticatedComponent(props) {
    const isLoggedIn = useAuth();
    return isLoggedIn ? <Component {...props} /> : <Login />;
  };
}

const ProtectedPage = withAuth(Dashboard);
```

---

## **23. What is Context in React and why is it used?**

* Context allows data to be **shared across components**
* Avoids **prop drilling**
* Commonly used for theme, auth, language
* Makes global state easier to manage

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

---

## **24. What is the difference between `React.createContext` and `useContext`?**

* `createContext` **creates** a context - createContext creates the context object that holds the shared data
* `useContext` **consumes** the context inside a component - useContext is a hook that consumes that context data inside components.
* They work together
* `useContext` makes code cleaner

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

---

## **25. What are the advantages of using functional components over class components?**

* Less code and easier to read
* No `this` keyword confusion
* Hooks provide full lifecycle support
* Better performance and easier testing

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

---

## **26. What is `setState` and how does it work in React?**

* `setState` updates component state
* It is **asynchronous**
* Causes re-render when state changes
* Used mainly in class components

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

---

## **27. What is the difference between `componentDidMount` and `useEffect`?**

* `componentDidMount` runs **once after render** in class components
* `useEffect` does the same in functional components
* `useEffect` can also handle updates and cleanup
* Hooks are more flexible

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

---

## **28. How can you optimize performance in React applications?**

* Use `React.memo` to avoid unnecessary re-renders
* Use `useMemo` and `useCallback`
* Avoid inline functions in JSX
* Use keys properly in lists

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

---

## **29. What are React Fragments?**

* Fragments let you group elements **without adding extra DOM nodes**
* Keeps the DOM clean
* Useful when returning multiple elements

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

---

## ** 30.What are error boundaries in React?**

* Error boundaries are components that **catch JavaScript errors** in child components
* They prevent the entire app from crashing
* Used to show a **fallback UI**
* Only work in **class components**

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

---

## **31. What is the purpose of `shouldComponentUpdate`?**

* Controls whether a component should **re-render**
* Helps improve **performance**
* Runs before re-rendering
* Used in class components only

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

---

## **32. What are portals in React?**

* Portals render components **outside the normal DOM hierarchy**
* Commonly used for modals and tooltips
* Keeps UI logic clean

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

---

## **33. How do you handle events in React?**

* Events are handled using **camelCase syntax**
* Functions are passed as event handlers
* Uses synthetic events for consistency

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

---

## **34. What is React Router and how do you use it?**

* React Router enables **client-side navigation**
* Allows switching pages without reloading
* Uses routes and links

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

---

## **35. What are the `<Router>` components of React Router v6?**

* `BrowserRouter` – used for web apps
* `Routes` – replaces `Switch`
* `Route` – defines path and component
* More simple and predictable than v5

```jsx
import { BrowserRouter, HashRouter, MemoryRouter } from 'react-router-dom';

// Standard web apps
<BrowserRouter>
  <Routes>
    <Route path="/" element={<Home />} />
  </Routes>
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

---

## **36. What is lazy loading in React?**

* Lazy loading loads components **only when needed**
* Improves initial load performance
* Uses `React.lazy` and `Suspense`

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

---

## **37. What is the difference between state and context?**

* **State** is local to a component
* **Context** is global and shared
* Context avoids prop drilling
* State is simpler for small data

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

---

## **38.  How can you manage global state in a React application?

* Use **Context API** for small apps
* Use **Redux / Zustand** for complex apps
* Keep global state minimal
* Choose based on app size

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

---

## **Advanced React.js Concepts**

## **39. What is Server-Side Rendering (SSR) in React?**

* **SSR** means rendering React components **on the server instead of the browser**.
* The server sends **fully rendered HTML** to the client.
* This improves **SEO**, **initial page load speed**, and **performance on slow devices**.
* Frameworks like **Next.js** make SSR easy.

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

---

## **40. What are React Suspense and Concurrent Mode?**

* **Suspense** lets React **wait for something** like data or lazy-loaded components.
* It shows a **fallback UI** while loading.
* **Concurrent Mode** allows React to **pause, resume, and prioritize rendering**.
* Together, they improve **user experience and responsiveness**.

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

---

## **41. What is Code Splitting in React?**

* Code splitting breaks the app into **smaller bundles**.
* Only the required code is loaded when needed.
* This reduces **initial load time**.
* Commonly done using **React.lazy** and **Suspense**.

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

---

## **42. What is React Fiber?**

* **React Fiber** is the **new reconciliation engine** introduced in React 16.
* It allows React to **break rendering work into small units**.
* React can **pause, resume, or cancel work**.
* This enables **Concurrent Mode and smoother UI updates**.

➡️ In short: Fiber makes React **faster and more responsive**.

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

---

## **43. What is React’s Reconciliation Algorithm?**

* Reconciliation is how React **updates the DOM efficiently**.
* React compares the **previous Virtual DOM** with the **new Virtual DOM**.
* It updates **only the changed parts** in the real DOM.
* This process improves **performance**.

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

---

## **44. How does React handle forms?**

* React uses **controlled components** for forms.
* Form values are stored in **state**.
* Input changes are handled using **onChange events**.
* This gives full **control and validation** over inputs.

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

---

## **45. What is the significance of the `key` prop in lists?**

* `key` helps React **identify list items uniquely**.
* It improves **performance and correct re-rendering**.
* Keys should be **stable and unique**, not array indexes.
* Wrong keys can cause **UI bugs**

```jsx
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

---

## **46. How do you implement Custom Hooks in React?**

* Custom hooks let you **reuse logic across components**.
* They are **regular functions** that use React hooks.
* Must start with the word **`use`**.
* They improve **cleanliness and reusability**.

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

---

## **47. Difference between `componentWillMount`, `componentDidMount`, and `getDerivedStateFromProps`**

* `componentWillMount` runs **before rendering**, but it is **deprecated** and should not be used.
* `componentDidMount` runs **after the component is added to the DOM**.
* It’s commonly used for **API calls and subscriptions**.
* `getDerivedStateFromProps` is a **static method** used to **sync state with props**.

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

---

## **48. What are React DevTools and how do they help in debugging?**

* React DevTools is a **browser extension** for Chrome and Firefox.
* It lets you **inspect component hierarchy**.
* You can view and modify **props, state, and hooks** in real time.
* Helps identify **unnecessary re-renders and performance issues**.

➡️ It’s essential for **debugging React apps efficiently**.

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

---

## **49. How do you test React components?**

* React components are commonly tested using **Jest** and **React Testing Library**.
* Focus is on **testing behavior, not implementation**.
* Tests simulate **user actions** and check the output.

```jsx
render(<Button />);
fireEvent.click(screen.getByText("Save"));
```

---

## **50. What is the `useReducer` hook and how does it differ from `useState`?**

* `useReducer` is used for **complex state logic**.
* It works like **Redux**, using actions and a reducer function.
* `useState` is simpler and better for **small state changes**.
* `useReducer` improves **readability for complex updates**.

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

---

## **51. What are synthetic events in React?**

* Synthetic events are **React’s wrapper around native browser events**.
* They work **consistently across all browsers**.
* They improve **performance** by using event pooling.
* Example: `onClick`, `onChange`.

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

---

## **52. How do you handle side effects in React?**

* Side effects include **API calls, timers, subscriptions**, etc.
* In functional components, side effects are handled using **`useEffect`**.
* Cleanup is done by returning a function from `useEffect`.

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

---

## **53. What is the lifecycle of a React component? Explain both class-based and functional components' lifecycles.**

### **Class-based lifecycle**

* **Mounting**: constructor → render → componentDidMount
* **Updating**: render → componentDidUpdate
* **Unmounting**: componentWillUnmount

### **Functional lifecycle**

* `useEffect` handles **mount, update, and unmount** logic.

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

---

## **54. Explain React's Context API and when to use it instead of prop drilling**

* Context API allows **global data sharing**.
* Avoids passing props through multiple layers.
* Best used for **theme, auth, language settings**.
* Not recommended for **frequently changing data**.

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

---

## **55. Explain the concept of "render props" and provide an example.**

* Render props is a pattern where a component **shares logic via a function prop**.
* The function returns **UI based on shared logic**.
* Helps in **logic reuse without HOCs**.

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

---

## **React Router Questions**

## **56. How would you implement routing in a React application? What is React Router, and how does it work?**

* Routing lets you show different components based on the URL without reloading the page
* **React Router** is the standard library for handling routing in React
* It listens to browser URL changes and matches them to defined routes
* Each route renders a specific component when its path matches

**Example (React Router v6):**

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

---

## **57. What are dynamic routes, and how would you implement them in React Router?**

* Dynamic routes allow URLs to contain **variables**, like IDs or usernames
* They are useful for pages like user profiles or product details
* You define them using `:paramName` in the route path
* The value is accessed using `useParams()`

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

---

## **58. How can you handle nested routes in React Router?**

* Nested routes are used when pages have **child views**
* React Router supports this using parent routes and the `Outlet` component
* The parent layout stays visible while child routes change

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

---

## **59. What is the purpose of useParams, useLocation, and useHistory / useNavigate?**

* `useParams` → gets dynamic route values
* `useLocation` → gives current URL info like pathname or state
* `useNavigate` → programmatic navigation (replacement for `useHistory`)

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

---

## **Performance Optimization Questions**

## **60. What strategies can you use to avoid unnecessary re-renders in React?**

* Use `React.memo` to prevent re-rendering unchanged components
* Use `useCallback` and `useMemo` to memoize functions and values
* Avoid passing new object or function references unnecessarily
* Keep state as local as possible

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

---

## **61. How would you deal with memory leaks in React components?**

* Memory leaks happen when effects continue after a component unmounts
* Always clean up subscriptions, timers, or event listeners
* Use the cleanup function inside `useEffect`
* Cancel API calls or intervals when the component unmounts

**Example:**

```jsx
useEffect(() => {
  const timer = setInterval(() => {
    console.log("Running");
  }, 1000);

  return () => clearInterval(timer);
}, []);
```

---

## **State Management Questions**

## **62.  What is Redux, and how does it help in state management in React?**

* Redux is a **predictable state management library** for JavaScript apps
* It stores the entire app state in **one central store**
* Makes state changes predictable by enforcing strict rules
* Helps manage complex state shared across many components
* Improves debugging using tools like Redux DevTools

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

---

## **63. What are the principles of Redux (actions, reducers, store)?**

* **Single source of truth** → entire state lives in one store
* **State is read-only** → only actions can change state
* **Changes are made with pure functions** called reducers

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

---

## **64. Explain the difference between local component state and global state in React applications.**

* **Local state** is used inside one component
* Managed using `useState` or `useReducer`
* **Global state** is shared across many components
* Managed using Redux, Context API, or Zustand
* Use local state for UI logic, global state for app-wide data

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

---

## **65. How do you handle asynchronous actions in Redux?**

* Redux itself handles only synchronous actions
* Async logic is handled using **middleware**
* Common solutions include `redux-thunk` and `redux-saga`
* Middleware lets you dispatch actions after async work

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

---

## **66. What are Redux middleware, and can you give an example of redux-thunk or redux-saga?**

* Middleware sits **between dispatch and reducer**
* Used for logging, async logic, API calls, or side effects
* `redux-thunk` allows actions to be functions
* `redux-saga` uses generator functions for complex flows

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

---

## **67. Explain how you would structure your Redux store in a large application.**

* Split state into **feature-based slices**
* Each slice has its own reducer and actions
* Combine reducers using `combineReducers`
* Keep logic modular and scalable

**Example structure:**

```js
store/
 ├── user/
 │   ├── userSlice.js
 ├── products/
 │   ├── productSlice.js
 └── store.js
```

```js
const rootReducer = combineReducers({
  user: userReducer,
  products: productReducer,
});
```

---

## **Testing Questions**

## **68. What testing libraries are commonly used in React applications, and how do you write unit tests for components?**

* **Jest** is the test runner and assertion library
* **React Testing Library (RTL)** is used to test components like a real user would
* Focus is on behavior, not implementation details
* Tests render components and check text, clicks, or state changes

**Example:**

```jsx
import { render, screen } from "@testing-library/react";

test("renders button text", () => {
  render(<Button />);
  expect(screen.getByText("Click")).toBeInTheDocument();
});
```

---

## **69. How would you test a component that interacts with external APIs or services?**

* Never call real APIs in tests
* Mock the API response using Jest or MSW
* Test loading, success, and error states
* Ensure UI updates correctly based on response

**Example (mock fetch):**

```js
global.fetch = jest.fn(() =>
  Promise.resolve({
    json: () => Promise.resolve({ name: "John" }),
  })
);
```

```jsx
expect(await screen.findByText("John")).toBeInTheDocument();
```

---

## **70. What is the difference between shallow rendering and full rendering, and when would you use each?**

* **Shallow rendering** tests one component only
* Child components are not rendered
* Used mainly with Enzyme (less common now)
* **Full rendering** renders the entire component tree
* React Testing Library focuses on full rendering

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

---

## **71. Explain the concept of snapshot testing in the context of React.**

* Snapshot testing captures the rendered output of a component
* The snapshot is saved and compared in future test runs
* Helps detect unexpected UI changes
* Best for stable UI, not frequently changing components

**Example:**

```js
import renderer from "react-test-renderer";

test("matches snapshot", () => {
  const tree = renderer.create(<Header />).toJSON();
  expect(tree).toMatchSnapshot();
});
```

---

## **72. What is the role of mocking in React tests, and how do you mock modules or APIs in Jest?**

* Mocking replaces real dependencies with fake ones
* Keeps tests fast, reliable, and isolated
* Used for APIs, timers, libraries, or functions
* Jest provides `jest.mock()` for this

**Example:**

```js
jest.mock("./api", () => ({
  fetchData: jest.fn(() => Promise.resolve("data")),
}));
```

```js
expect(fetchData).toHaveBeenCalled();
```

---


## **Build Tools & Development Workflow Questions**

## **73. What build tools or bundlers are you familiar with (e.g., Webpack, Vite)? Explain their roles in a React project.**

* Build tools bundle and optimize code for the browser
* **Webpack** is powerful and highly configurable
* **Vite** is modern, fast, and uses native ES modules
* They handle bundling, dev servers, code splitting, and assets
* Vite is preferred today for faster startup and hot reload

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

---

## **74. How do you configure Babel and Webpack for a React application?**

* **Babel** converts modern JavaScript and JSX into browser-compatible code
* **Webpack** bundles files and applies loaders
* Babel uses presets like `@babel/preset-react`
* Webpack uses Babel loader to process JS files

**Example (webpack.config.js):**

```js
module.exports = {
  module: {
    rules: [
      {
        test: /\.js$/,
        use: "babel-loader",
        exclude: /node_modules/,
      },
    ],
  },
};
```

```js
// .babelrc
{
  "presets": ["@babel/preset-env", "@babel/preset-react"]
}
```

---

## **75. What is the role of `.env` files in React development? How do you manage environment variables?**

* `.env` files store environment-specific variables
* Used for API URLs, keys, and configuration
* Keeps sensitive values out of source code
* In React, variables must start with `REACT_APP_` (CRA) or `VITE_` (Vite)

**Example:**

```env
VITE_API_URL=https://api.example.com
```

```js
const apiUrl = import.meta.env.VITE_API_URL;
```

---

## **76. What is CI/CD, and how would you set up continuous integration and deployment for a React application?**

* **CI/CD** automates testing, building, and deployment
* CI runs tests on every code push
* CD deploys the app automatically if tests pass
* Common tools: GitHub Actions, GitLab CI, Netlify, Vercel

**Example (GitHub Actions):**

```yaml
name: React CI
on: [push]
jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
      - run: npm install
      - run: npm test
      - run: npm run build
```

---

# 🟢 React Fundamentals – Must‑Know (Interview Ready)
## 1. What is React and why is it used?

* React is a **JavaScript library** used to build **fast, interactive user interfaces**, mainly for single‑page applications.
* It lets us build UI using **reusable components**, so code becomes cleaner and easier to maintain.
* React updates only the **changed part of the UI**, not the whole page, which improves performance.
* It’s widely used because it’s **simple, scalable**, and backed by a large ecosystem.

```jsx
function App() {
  return <h1>Hello React</h1>;
}
```

---

## 2. What is JSX and how does it work?

* JSX stands for **JavaScript XML**.
* It lets us write **HTML‑like syntax inside JavaScript**, which makes UI code readable.
* JSX is **not understood by browsers directly**.
* Behind the scenes, Babel converts JSX into `React.createElement()` calls.

```jsx
const element = <h1>Welcome</h1>;
```

---

## 3. Difference between JSX and `React.createElement`

* JSX is **syntactic sugar** for `React.createElement`.
* JSX looks like HTML and is easier to write and understand.
* `React.createElement` is plain JavaScript but becomes messy for complex UI.
* Both produce the **same React element** internally.

```jsx
// JSX
const el1 = <h1>Hello</h1>;

// Without JSX
const el2 = React.createElement('h1', null, 'Hello');
```

---

## 4. Difference between React and ReactDOM

* **React** is used to **create components and UI logic**.
* **ReactDOM** is used to **render those components into the browser DOM**.
* React focuses on *what* the UI is.
* ReactDOM focuses on *where* the UI appears.

```jsx
ReactDOM.createRoot(document.getElementById('root'))
  .render(<App />);
```

---

## 5. What are components in React?

* Components are **reusable building blocks** of a React application.
* Each component controls a **small part of the UI**.
* Components can accept **props** and manage **state**.
* This makes applications modular and easy to maintain.

```jsx
function Greeting() {
  return <p>Hello User</p>;
}
```

---

## 6. Types of components in React

* There are mainly **two types of components**.
* **Functional components** – simple JavaScript functions (most commonly used).
* **Class components** – ES6 classes (older approach).
* Today, functional components with hooks are preferred.

```jsx
// Functional Component
function Header() {
  return <h2>Header</h2>;
}
```

---

## 7. Functional vs Class components

* Functional components are **simpler and cleaner**.
* Class components use `this` and lifecycle methods, which adds complexity.
* With hooks, functional components can manage **state and side effects**.
* Modern React apps mostly use **functional components**.

```jsx
// Functional
function Counter() {
  return <h1>Count</h1>;
}

// Class
class Counter extends React.Component {
  render() {
    return <h1>Count</h1>;
  }
}
```

---

## 8. What is the Virtual DOM?

* The Virtual DOM is a **lightweight copy of the real DOM**.
* React updates the Virtual DOM first when state changes.
* It then compares it with the previous version (**diffing**).
* Only the changed parts are updated in the real DOM, improving performance.

```jsx
setState({ name: 'React' }); // triggers Virtual DOM update
```

---

✅ **Tip for Interviews:**

> “React improves performance by updating only what changes using the Virtual DOM.”

---

📌 **End of React Fundamentals (1–8)**

---

# 🟢 React Core Concepts – State, Events & Rendering

## 10. What is state and how is it different from props?

* **State** is internal data managed by a component.
* **Props** are inputs passed from a parent component.
* State can change over time; props are **read‑only**.
* When state changes, the component re‑renders automatically.

```jsx
function Counter() {
  const [count, setCount] = React.useState(0);
  return <button onClick={() => setCount(count + 1)}>{count}</button>;
}
```

---

## 11. What is the `render()` method?

* `render()` is used in **class components** to define what UI should display.
* It returns JSX that React converts into DOM elements.
* It runs whenever **state or props change**.
* Functional components don’t need it—they directly return JSX.

```jsx
class App extends React.Component {
  render() {
    return <h1>Hello</h1>;
  }
}
```

---

## 12. What is the `key` prop and why is it important?

* `key` is a **unique identifier** for list items.
* It helps React track which items changed, added, or removed.
* Improves performance and prevents incorrect UI updates.
* Keys should be **stable and unique**, not array indexes.

```jsx
items.map(item => <li key={item.id}>{item.name}</li>)
```

---

## 13. What are controlled components?

* Controlled components are **form elements controlled by React state**.
* The input value comes from state and updates via `onChange`.
* This gives full control and validation capability.
* Commonly used in real applications.

```jsx
<input value={name} onChange={e => setName(e.target.value)} />
```

---

## 14. What are uncontrolled components?

* Uncontrolled components store form data in the **DOM itself**.
* React accesses values using `ref`.
* Less code, but less control.
* Useful for simple or legacy forms.

```jsx
const inputRef = React.useRef();
<input ref={inputRef} />
```

---

## 15. What are React Fragments?

* Fragments let you group elements **without adding extra DOM nodes**.
* Useful when returning multiple elements.
* Helps keep the DOM clean.

```jsx
<>
  <h1>Title</h1>
  <p>Description</p>
</>
```

---

## 16. What are synthetic events?

* Synthetic events are **React’s cross‑browser event wrappers**.
* They work the same across all browsers.
* They improve performance and consistency.
* Syntax looks like normal JavaScript events.

```jsx
<button onClick={handleClick}>Click</button>
```

---

## 17. What is `React.StrictMode` and why is it used?

* `StrictMode` is a **development‑only tool**.
* It helps detect unsafe lifecycle methods and side effects.
* It does not affect production builds.
* Useful for writing future‑proof React code.

```jsx
<React.StrictMode>
  <App />
</React.StrictMode>
```

---

# 🟢 React Hooks – Core + Advanced (Interview Ready)

## 1. What are React Hooks and why were they introduced?

* React Hooks are **functions that let functional components use state and lifecycle features**.
* Before hooks, only class components could manage state and side effects.
* Hooks reduce boilerplate and remove the need for `this` keyword.
* They make code **cleaner, reusable, and easier to test**.

```jsx
function App() {
  const [count, setCount] = React.useState(0);
  return <button onClick={() => setCount(count + 1)}>{count}</button>;
}
```

---

## 2. Rules of Hooks and why they exist

* Hooks must be called **at the top level**, not inside loops or conditions.
* Hooks can only be called from **React functions or custom hooks**.
* These rules ensure React can track hook order correctly.
* Breaking rules can cause unpredictable bugs.

```jsx
// ❌ Wrong
if (x) useState(0);

// ✅ Correct
const [x, setX] = useState(0);
```

---

## 3. What is `useState` and how does it work?

* `useState` is used to add **state to functional components**.
* It returns the current value and a function to update it.
* Updating state triggers a re-render.
* State updates are asynchronous.

```jsx
const [name, setName] = useState('React');
```

---

## 4. What is `useEffect` and how does it work?

* `useEffect` is used to handle **side effects** like API calls or subscriptions.
* It runs after the component renders.
* Cleanup logic runs before the next effect or unmount.
* Dependency array controls when it runs.

```jsx
useEffect(() => {
  fetchData();
}, []);
```

---

## 5. Difference between `useState` and `useEffect`

* `useState` manages **data**.
* `useEffect` manages **side effects**.
* `useState` triggers rendering; `useEffect` runs after render.
* They serve different responsibilities.

```jsx
useState(0);
useEffect(() => console.log('Rendered'));
```

---

## 6. Difference between `useEffect`, `useLayoutEffect`, and `useInsertionEffect`

* `useEffect` runs **after paint** and is non-blocking.
* `useLayoutEffect` runs **before paint**, blocking UI.
* `useInsertionEffect` runs before DOM mutations, mainly for CSS-in-JS.
* Use `useEffect` by default.

```jsx
useEffect(() => {});
useLayoutEffect(() => {});
```

---

## 7. When should you avoid `useEffect`?

* Avoid `useEffect` for **derived state**.
* Don’t use it for simple calculations.
* Prefer computing values directly during render.
* Overusing effects causes unnecessary complexity.

```jsx
// ❌ Avoid
useEffect(() => setTotal(a + b), [a, b]);

// ✅ Better
const total = a + b;
```

---

## 8. How does the dependency array work internally?

* React compares dependencies using **Object.is**.
* If any dependency changes, the effect runs again.
* Empty array runs once on mount.
* Missing dependencies can cause bugs.

```jsx
useEffect(() => {
  console.log(count);
}, [count]);
```

---

## 9. What is stale closure in hooks?

* A stale closure happens when an effect captures **old state values**.
* Occurs due to missing dependencies.
* Leads to outdated logic running.
* Fix by adding dependencies or using functional updates.

```jsx
setCount(prev => prev + 1);
```

---

📌 **End of React Hooks – Core + Advanced**

---

# 🟢 React Hooks – Advanced Patterns & Performance

## 10. How do you fix stale state issues?

* Stale state happens when hooks use **old state values**.
* It’s common inside `useEffect`, timers, or callbacks.
* Fix it using **functional state updates** or correct dependencies.
* This ensures React always uses the latest state.

```jsx
setCount(prev => prev + 1);
```

---

## 11. When would you use `useRef` instead of `useState`?

* Use `useRef` when you need a value that **doesn’t trigger re-render**.
* Common for DOM access, timers, or previous values.
* `useState` causes re-render; `useRef` does not.
* Improves performance for non-UI data.

```jsx
const inputRef = useRef();
```

---

## 12. What is `useMemo` and when should you use it?

* `useMemo` memoizes **expensive calculations**.
* It recalculates only when dependencies change.
* Prevents unnecessary recalculations on re-render.
* Use it for heavy computations, not simple logic.

```jsx
const total = useMemo(() => calc(a, b), [a, b]);
```

---

## 13. What is `useCallback` and when should you use it?

* `useCallback` memoizes **functions**.
* Prevents function recreation on every render.
* Useful when passing callbacks to child components.
* Helps avoid unnecessary re-renders.

```jsx
const handleClick = useCallback(() => setCount(c => c + 1), []);
```

---

## 14. Difference between `useMemo` and `useCallback`

* `useMemo` memoizes a **value**.
* `useCallback` memoizes a **function**.
* `useCallback(fn)` is equivalent to `useMemo(() => fn)`.
* Both are performance optimizations.

```jsx
useMemo(() => value, []);
useCallback(() => fn(), []);
```

---

## 15. What is `useReducer` and how is it different from `useState`?

* `useReducer` manages **complex state logic**.
* It uses reducer functions similar to Redux.
* Better for multiple related state updates.
* `useState` is simpler for basic state.

```jsx
const [state, dispatch] = useReducer(reducer, initialState);
```

---

## 16. Can hooks replace all lifecycle methods? (mapping)

* Yes, hooks can replace most lifecycle methods.
* `useEffect` covers mount, update, and unmount.
* `useLayoutEffect` replaces `componentDidMount` before paint.
* Hooks simplify lifecycle management.

```jsx
useEffect(() => {
  return () => cleanup();
}, []);
```

---

## 17. How do you create custom hooks?

* Custom hooks are **reusable hook logic**.
* They start with `use` keyword.
* They can use other hooks internally.
* Helps share logic cleanly.

```jsx
function useCounter() {
  const [count, setCount] = useState(0);
  return { count, setCount };
}
```

---

## 18. How do you share logic without HOCs?

* Use **custom hooks** instead of HOCs.
* Hooks avoid wrapper components.
* Logic stays reusable and readable.
* This is the modern React approach.

```jsx
const { count } = useCounter();
```

---

# 🟡 Component Lifecycle & Internals (React.js)

## 1️⃣ Lifecycle of Class Components

**Answer (Spoken Style):**

* Class components go through **three main phases**: *Mounting, Updating, and Unmounting*.
* In **mounting**, the component is created and added to the DOM using methods like `constructor`, `render`, and `componentDidMount`.
* In **updating**, it re-renders when props or state change, using methods like `shouldComponentUpdate` and `componentDidUpdate`.
* In **unmounting**, `componentWillUnmount` is used to clean up resources like timers or subscriptions.
* This lifecycle gives full control but is more verbose than hooks.

```jsx
class MyComponent extends React.Component {
  componentDidMount() {
    console.log('Mounted');
  }

  componentWillUnmount() {
    console.log('Cleanup');
  }

  render() {
    return <h1>Hello</h1>;
  }
}
```

---

## 2️⃣ Lifecycle of Functional Components

**Answer (Spoken Style):**

* Functional components don’t have lifecycle methods like classes.
* Instead, they use **Hooks**, mainly `useEffect`, to manage lifecycle behavior.
* `useEffect` can handle mounting, updating, and unmounting based on its dependency array.
* This makes functional components **simpler, cleaner, and easier to reuse**.
* Today, hooks are the recommended approach in React.

```jsx
useEffect(() => {
  console.log('Mounted');

  return () => {
    console.log('Unmounted');
  };
}, []);
```

---

## 3️⃣ Difference between `componentDidMount` and `useEffect`

**Answer (Spoken Style):**

* `componentDidMount` is used in **class components** and runs once after the component mounts.
* `useEffect` is used in **functional components** and is more flexible.
* With `useEffect`, behavior depends on the dependency array.
* `useEffect` can replace **multiple lifecycle methods**.
* Hooks reduce boilerplate and improve readability.

```jsx
// Class
componentDidMount() {
  fetchData();
}

// Function
useEffect(() => {
  fetchData();
}, []);
```

---

## 4️⃣ Difference between `componentWillMount`, `componentDidMount`, and `getDerivedStateFromProps`

**Answer (Spoken Style):**

* `componentWillMount` runs **before render** but is **deprecated** and unsafe.
* `componentDidMount` runs **after render** and is used for API calls or DOM access.
* `getDerivedStateFromProps` is a **static method** used to update state based on props.
* It should be used carefully to avoid unnecessary re-renders.
* Modern React avoids `componentWillMount` entirely.

```jsx
static getDerivedStateFromProps(props, state) {
  if (props.value !== state.value) {
    return { value: props.value };
  }
  return null;
}
```

---

## 5️⃣ What is React Reconciliation?

**Answer (Spoken Style):**

* Reconciliation is React’s process of updating the UI efficiently.
* When state or props change, React creates a **new Virtual DOM tree**.
* It compares it with the previous one using a **diffing algorithm**.
* Only the changed parts are updated in the real DOM.
* This makes React **fast and performant**.

```jsx
setCount(count + 1); // Triggers reconciliation
```

---

## 6️⃣ What is React Fiber?

**Answer (Spoken Style):**

* React Fiber is the **new rendering engine** introduced in React 16.
* It allows React to split rendering work into small units.
* This helps pause, resume, or prioritize updates.
* Fiber improves performance for animations and large applications.
* It enables features like **Concurrent Rendering**.

```jsx
// Fiber works internally, no direct code usage
```

---

## 7️⃣ What Causes a Component to Re-render?

**Answer (Spoken Style):**

* A component re-renders when **state changes**, **props change**, or **parent re-renders**.
* Calling `setState` or state setter from `useState` triggers a re-render.
* Context value changes also cause re-renders.
* React avoids unnecessary DOM updates using reconciliation.
* Memoization can reduce unwanted re-renders.

```jsx
const [count, setCount] = useState(0);
setCount(count + 1); // Re-render
```

---

# 🟡 Rendering & Performance Optimization (React.js)

## 1️⃣ What is Automatic Batching in React 18?

**Answer (Spoken Style):**

* Automatic batching means React groups multiple state updates into **one re-render**.
* Before React 18, batching mostly worked only inside React events.
* In React 18, batching also works inside promises, timeouts, and async code.
* This reduces unnecessary re-renders and improves performance.
* It works by default with `createRoot`.

```jsx
setCount(c => c + 1);
setValue(v => v + 1); // Only one re-render in React 18
```

---

## 2️⃣ What is Concurrent Rendering?

**Answer (Spoken Style):**

* Concurrent Rendering lets React prepare multiple UI updates at the same time.
* Rendering work can be **paused, resumed, or discarded**.
* High‑priority updates like typing get handled first.
* This keeps the UI responsive even during heavy rendering.
* It’s enabled gradually using features like `startTransition`.

```jsx
// Enabled internally via createRoot
const root = createRoot(document.getElementById('root'));
```

---

## 3️⃣ Difference between Legacy and Concurrent Rendering

**Answer (Spoken Style):**

* Legacy rendering is **synchronous** and blocking.
* Once rendering starts, it must finish before handling other tasks.
* Concurrent rendering is **interruptible and priority‑based**.
* It improves user experience for large or complex UIs.
* Legacy is the old behavior; concurrent is the future.

```jsx
// Legacy
ReactDOM.render(<App />, root);

// Concurrent
createRoot(root).render(<App />);
```

---

## 4️⃣ What is `startTransition` and `useTransition`?

**Answer (Spoken Style):**

* These APIs mark updates as **non‑urgent**.
* Urgent updates like typing stay fast.
* Non‑urgent updates like filtering large lists run in background.
* `useTransition` also provides a loading state.
* This improves perceived performance.

```jsx
const [isPending, startTransition] = useTransition();

startTransition(() => {
  setItems(filteredItems);
});
```

---

## 5️⃣ Why Does React Strict Mode Double Render in Development?

**Answer (Spoken Style):**

* React Strict Mode intentionally renders components twice in development.
* This helps detect **side effects** and unsafe logic.
* It ensures components are pure and predictable.
* This behavior does **not happen in production**.
* It improves long‑term app stability.

```jsx
<React.StrictMode>
  <App />
</React.StrictMode>
```

---

## 6️⃣ What is Code Splitting?

**Answer (Spoken Style):**

* Code splitting breaks the app into smaller bundles.
* Only the required code is loaded when needed.
* This reduces initial load time.
* It’s commonly done using dynamic imports.
* Very useful for large applications.

```jsx
const Admin = React.lazy(() => import('./Admin'));
```

---

## 7️⃣ What is Lazy Loading?

**Answer (Spoken Style):**

* Lazy loading loads components **only when they are needed**.
* It improves performance and reduces bundle size.
* Usually combined with `Suspense`.
* Common for routes and heavy components.
* Enhances user experience on slow networks.

```jsx
<Suspense fallback={<Loading />}>
  <Admin />
</Suspense>
```

---

## 8️⃣ How Does `React.memo` Work Internally?

**Answer (Spoken Style):**

* `React.memo` is a higher‑order component for optimization.
* It prevents re-renders if props haven’t changed.
* React performs a **shallow comparison** of props.
* It’s useful for pure, frequently rendered components.
* Overuse can add unnecessary complexity.

```jsx
const MyComponent = React.memo(({ value }) => {
  return <div>{value}</div>;
});
```

---

## 9️⃣ How Do You Prevent Unnecessary Re-renders?

**Answer (Spoken Style):**

* Unnecessary re-renders happen when React updates components without real UI changes.
* We can prevent them using `React.memo` for components.
* Hooks like `useCallback` and `useMemo` help keep function and value references stable.
* Proper key usage and avoiding unnecessary state updates also help.
* Optimization should be done only when performance issues exist.

```jsx
const Button = React.memo(({ onClick }) => (
  <button onClick={onClick}>Click</button>
));
```

---

## 🔟 Why Do Inline Functions Cause Re-renders?

**Answer (Spoken Style):**

* Inline functions create a **new function reference** on every render.
* React compares props by reference, not by logic.
* A new function means props look changed.
* This causes child components to re-render.
* `useCallback` helps by memoizing the function.

```jsx
const handleClick = useCallback(() => {
  console.log('Clicked');
}, []);
```

---

## 1️⃣1️⃣ What Is Render Thrashing?

**Answer (Spoken Style):**

* Render thrashing happens when too many re-renders occur rapidly.
* It often comes from repeated state updates inside loops or effects.
* This causes UI lag and poor performance.
* Batching and debouncing help reduce it.
* React 18’s automatic batching minimizes this issue.

```jsx
setState(prev => prev + 1); // Avoid calling repeatedly in loops
```

---

## 1️⃣2️⃣ How Do Keys Affect Reconciliation?

**Answer (Spoken Style):**

* Keys help React identify which list items changed.
* They improve efficiency during reconciliation.
* Stable keys allow React to reuse DOM elements.
* Without proper keys, React may re-render unnecessarily.
* Keys should be unique and consistent.

```jsx
items.map(item => <li key={item.id}>{item.name}</li>)
```

---

## 1️⃣3️⃣ What Happens If Keys Are Not Stable?

**Answer (Spoken Style):**

* Using unstable keys like array index can confuse React.
* Components may lose state unexpectedly.
* DOM updates become inefficient.
* UI bugs like wrong animations can appear.
* Stable IDs should always be preferred.

```jsx
// Bad
items.map((item, i) => <Item key={i} />)
```

---

## 1️⃣4️⃣ How Do You Handle Memory Leaks in React?

**Answer (Spoken Style):**

* Memory leaks happen when resources aren’t cleaned up.
* Common causes are timers, subscriptions, and event listeners.
* Cleanup is done using `useEffect` return function.
* Class components use `componentWillUnmount`.
* Proper cleanup prevents performance issues.

```jsx
useEffect(() => {
  const timer = setInterval(() => {}, 1000);
  return () => clearInterval(timer);
}, []);
```

---

## 1️⃣5️⃣ What Is Virtualization (Windowing)?

**Answer (Spoken Style):**

* Virtualization renders only visible items in a list.
* Off-screen items are not rendered.
* This improves performance for large lists.
* Libraries like `react-window` are commonly used.
* It reduces memory and DOM load.

```jsx
// Conceptual example
<List height={400} itemCount={1000} itemSize={35} />
```

---

## 1️⃣6️⃣ How Does React Efficiently Handle Large Lists?

**Answer (Spoken Style):**

* React uses Virtual DOM and reconciliation.
* Proper keys help reuse DOM nodes.
* Virtualization limits rendered elements.
* Memoization prevents unnecessary updates.
* Combined, these techniques keep apps fast.

```jsx
const Row = React.memo(({ item }) => <div>{item.name}</div>);
```

---

## 🟡 5. State Management (React Interview – Spoken Style Answers)

### 1. Local state vs Global state

**Spoken Answer:**

* Local state is data used by a single component, like form input or toggle state.
* Global state is shared across many components, like user login or cart data.
* If state is only needed in one place, keep it local.
* If many unrelated components need it, move it to global state.

**Example:**

```jsx
// Local state
const [count, setCount] = useState(0);

// Global state example (Redux selector)
const user = useSelector(state => state.user);
```

---

### 2. What is Context API and when should you use it?

**Spoken Answer:**

* Context API lets you pass data without prop drilling.
* It’s best for low‑frequency updates like theme, language, or auth user.
* It’s built into React, so no extra library is needed.
* Avoid it for highly dynamic or large-scale state.

**Example:**

```jsx
const ThemeContext = createContext();

<ThemeContext.Provider value="dark">
  <App />
</ThemeContext.Provider>
```

---

### 3. Difference between state and context

**Spoken Answer:**

* State belongs to a component and controls its behavior.
* Context is a way to share state across components.
* Context doesn’t replace state—it just distributes it.
* Usually, state lives inside context.

**Example:**

```jsx
const [user, setUser] = useState(null);
<UserContext.Provider value={user} />
```

---

### 4. Context vs Redux – how do you decide?

**Spoken Answer:**

* Use Context for small apps or simple shared data.
* Use Redux for large apps with complex logic and async flows.
* Redux gives better debugging, structure, and scalability.
* Context can cause re-render issues if overused.

**Rule of Thumb:**

* Theme/Auth → Context
* Cart/Orders/Dashboard → Redux

---

### 5. What is Redux and why is it used?

**Spoken Answer:**

* Redux is a predictable state management library.
* It keeps all global state in one central store.
* State changes happen in a controlled and traceable way.
* It’s useful for large applications with shared state.

**Example:**

```js
const store = createStore(reducer);
```

---

### 6. Redux core principles (actions, reducers, store)

**Spoken Answer:**

* Store holds the entire app state.
* Actions describe what happened.
* Reducers decide how state changes.
* Data flow is always one-directional.

**Example:**

```js
// Action
{ type: "ADD_TODO" }

// Reducer
function reducer(state, action) {
  if (action.type === "ADD_TODO") {
    return { ...state, count: state.count + 1 };
  }
}
```

---

### 7. How do you handle async actions in Redux?

**Spoken Answer:**

* Redux itself is synchronous.
* Async logic is handled using middleware.
* Most commonly with redux‑thunk or redux‑saga.
* This keeps reducers pure and clean.

**Example (Thunk):**

```js
const fetchUsers = () => async dispatch => {
  const res = await fetch('/api/users');
  dispatch({ type: 'SET_USERS', payload: await res.json() });
};
```

---

### 8. What are Redux middlewares?

**Spoken Answer:**

* Middleware sits between action and reducer.
* It intercepts actions before they reach reducers.
* Used for logging, async calls, or error handling.
* Common examples are thunk and saga.

**Example:**

```js
const logger = store => next => action => {
  console.log(action);
  return next(action);
};
```

---

### 9. Difference between redux-thunk and redux-saga

**Spoken Answer:**

* Thunk uses functions for async logic.
* Saga uses generator functions.
* Thunk is simple and easy to learn.
* Saga is powerful for complex workflows.

**Quick Compare:**

* Small app → Thunk
* Complex async flows → Saga

---

### 10. How would you structure Redux in a large application?

**Spoken Answer:**

* Use feature‑based folder structure.
* Group actions, reducers, and slices together.
* Use Redux Toolkit to reduce boilerplate.
* Keep API logic separate from UI components.

**Example Structure:**

```
/store
  /auth
    authSlice.js
  /cart
    cartSlice.js
  store.js
```

---


# 🟠 6. Routing (React Router v6)

## 1. What is React Router and how does it work?

- React Router is a **client-side routing library** for React.
- It lets us build **single-page applications (SPA)** with multiple views.
- Instead of reloading pages, it **changes the URL and renders components** dynamically.
- It listens to URL changes and matches them with defined routes.

**Example:**
```jsx
import { BrowserRouter, Routes, Route } from "react-router-dom";

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
````

---

## 2. How do you implement routing in React?

* Install React Router using `npm install react-router-dom`.
* Wrap the app with `<BrowserRouter>`.
* Use `<Routes>` and `<Route>` to define paths.
* Each route renders a specific component.

**Example:**

```jsx
<BrowserRouter>
  <Routes>
    <Route path="/" element={<Home />} />
    <Route path="/login" element={<Login />} />
  </Routes>
</BrowserRouter>
```

---

## 3. What are dynamic routes?

* Dynamic routes handle **variable URL values**.
* They are useful for user profiles, product pages, etc.
* We define them using `:paramName`.
* Values are accessed using `useParams()`.

**Example:**

```jsx
<Route path="/users/:id" element={<User />} />

function User() {
  const { id } = useParams();
  return <h2>User ID: {id}</h2>;
}
```

---

## 4. How do you implement nested routes?

* Nested routes allow **child routes inside parent routes**.
* Commonly used for dashboards and layouts.
* Use `<Outlet />` to render child components.

**Example:**

```jsx
<Route path="/dashboard" element={<Dashboard />}>
  <Route path="profile" element={<Profile />} />
  <Route path="settings" element={<Settings />} />
</Route>

function Dashboard() {
  return (
    <>
      <h1>Dashboard</h1>
      <Outlet />
    </>
  );
}
```

---

## 5. What are `useParams`, `useLocation`, and `useNavigate`?

* `useParams()` → gets dynamic route parameters.
* `useLocation()` → gives current URL info.
* `useNavigate()` → programmatic navigation.

**Example:**

```jsx
const params = useParams();
const location = useLocation();
const navigate = useNavigate();

<button onClick={() => navigate("/login")}>Go Login</button>
```

---

## 6. What are `<Router>` components in React Router v6?

* Router components manage routing behavior.
* `BrowserRouter` → most common, uses browser history.
* `HashRouter` → uses hash (`#`) in URL.
* `MemoryRouter` → used in testing or non-browser apps.

**Example:**

```jsx
import { BrowserRouter } from "react-router-dom";

<BrowserRouter>
  <App />
</BrowserRouter>
```

---

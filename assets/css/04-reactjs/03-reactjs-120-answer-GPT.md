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

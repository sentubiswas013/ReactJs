

200 Questions — Hooks, Architecture, State Management
# React Basics (Q1–Q30)

## 1. What is React?

React is an open-source JavaScript library for building user interfaces, developed by Facebook. It uses a component-based architecture and a virtual DOM for efficient updates. React is declarative — you describe what the UI should look like, and React handles the DOM updates.

---

## 2. Why use React?

1. Component reusability
2. Virtual DOM for performance
3. Unidirectional data flow (predictable)
4. Rich ecosystem (React Router, Redux, etc.)
5. Large community
6. Server-side rendering (Next.js)
7. React Native for mobile

---

## 3. What is SPA?

A Single Page Application loads a single HTML page and dynamically updates content via JavaScript without full page reloads. Navigation feels instant, no server round-trips for each page. React is commonly used to build SPAs.

Drawback: initial load time and SEO concerns.

---

## 4. What is a component?

A component is a reusable, self-contained piece of UI. In React, components are functions (or classes) that accept props and return JSX. They can manage their own state and compose with other components.

```jsx
const Button = ({ label, onClick }) => (
  <button onClick={onClick}>{label}</button>
);
```

---

## 5. Functional vs class components?

- **Functional** — plain JS functions returning JSX. With hooks, they can manage state and lifecycle. Preferred today — simpler, no `this`, hooks support, better performance.
- **Class** — extend `React.Component`, use `this.state`, and have lifecycle methods.

```jsx
// Functional
const Hello = ({ name }) => <h1>Hello, {name}</h1>;

// Class
class Hello extends React.Component {
  render() {
    return <h1>Hello, {this.props.name}</h1>;
  }
}
```

---

## 6. What is JSX?

JSX (JavaScript XML) is a syntax extension allowing HTML-like code in JavaScript. It compiles to `React.createElement()` calls. Not required but makes code much more readable. Expressions go in `{}`; attributes use camelCase (`className`, `onClick`).

```jsx
// JSX
const elem = <div className="box">{2 + 2}</div>;

// Compiles to:
const elem = React.createElement('div', { className: 'box' }, 4);
```

---

## 7. How JSX works internally?

Babel transforms JSX into `React.createElement(type, props, ...children)` calls. With React 17+, the new JSX transform imports `jsx()` from `react/jsx-runtime` automatically — no need to import React in every file.

---

## 8. What is Virtual DOM?

The Virtual DOM is an in-memory JavaScript representation of the real DOM tree. React uses it to compute differences (diffing) between renders and then applies only the minimal necessary changes to the real DOM (reconciliation). This batches and optimizes DOM updates.

---

## 9. Virtual DOM vs Real DOM?

- **Real DOM** — heavy, slow to update, re-renders the whole tree.
- **Virtual DOM** — lightweight JS objects, fast comparison via diffing algorithm, updates only changed parts of the real DOM.

Virtual DOM acts as a buffer — React computes changes cheaply in JS, then applies them efficiently to the real DOM.

---

## 10. What is reconciliation?

Reconciliation is React's algorithm for comparing the current virtual DOM tree with the new one (after state/props change) and computing the minimal set of DOM operations to update the UI. Uses a diffing algorithm with O(n) complexity by making assumptions about component keys and types.

---

## 11. What is key in React?

Keys are special attributes that help React identify which items in a list have changed, been added, or removed. Keys must be stable, unique among siblings, and not array indices (unless the list is static). They make reconciliation efficient for list updates.

```jsx
<ul>
  {items.map(item => <li key={item.id}>{item.name}</li>)}
</ul>
```

> Never use array index as key for dynamic lists — causes bugs with reordering.

---

## 12. Why are keys important?

Without keys, React re-renders all list items on any change. With stable keys, React knows exactly which item changed and updates only that one. Wrong keys (like indices) cause state mismatch bugs — components don't reset properly when list order changes.

---

## 13. What is props?

Props (properties) are read-only inputs passed from parent to child components. They are immutable from the child's perspective. They flow down the component tree (unidirectional). Anything can be passed as props: primitives, objects, functions, JSX.

```jsx
const Greeting = ({ name, age }) => <p>{name} is {age}</p>;

<Greeting name="Alice" age={30} />
```

---

## 14. Props vs state?

- **Props** — data passed from parent, read-only in child, controlled externally. Used for configuration.
- **State** — data managed internally by a component, can change over time (triggers re-render). Used for dynamic data the component owns.

---

## 15. What is state?

State is local, mutable data managed by a component. When state changes, React re-renders the component and its children. In functional components, managed with the `useState` hook. State triggers UI updates — it's the source of dynamism in React apps.

```js
const [count, setCount] = useState(0);
// count    = current value
// setCount = updater function
```

---

## 16. What is setState?

`setState` (in class components) or the setter from `useState` (functional) schedules a state update and triggers a re-render. State updates may be batched. The setter can take a new value or an updater function receiving the previous state.

```js
setCount(count + 1);          // direct
setCount(prev => prev + 1);   // functional update (safe for batching)
```

---

## 17. Is setState synchronous?

No. `setState` and `useState` setters are asynchronous — React batches multiple state updates and applies them together for performance. You cannot read the updated state immediately after calling `setState`. Use functional updates or `useEffect` to react to state changes.

```js
setCount(count + 1);
console.log(count); // still old value!

// Use functional update instead:
setCount(prev => {
  console.log(prev);
  return prev + 1;
});
```

---

## 18. What is lifting state up?

Lifting state up means moving shared state to the nearest common ancestor component. Child components receive state as props and call callbacks to update it. This is the primary way to share state between sibling components without external state management.

---

## 19. What is prop drilling?

Prop drilling is passing props through many layers of components that don't need them, just to reach a deeply nested child. Problems: verbose, hard to refactor, tightly coupled components.

Solutions: Context API, Redux, Zustand, component composition.

---

## 20. What are controlled components?

A controlled component has its value/state managed by React state. Input value is bound to state; changes go through `onChange` → `setState` → re-render. The form element's value always reflects React state — single source of truth.

```jsx
const [value, setValue] = useState('');

<input value={value} onChange={e => setValue(e.target.value)} />
```

---

## 21. What are uncontrolled components?

Uncontrolled components manage their own state internally (like the DOM does traditionally). Use a `ref` to read the value when needed. Simpler for basic forms but harder to validate/transform in real time.

```jsx
const inputRef = useRef();
const handleSubmit = () => console.log(inputRef.current.value);

<input ref={inputRef} defaultValue="initial" />
```

---

## 22. What is conditional rendering?

Rendering different UI based on conditions. Methods:

1. `if/else`
2. Ternary operator
3. Logical `&&`
4. `switch`
5. Early return

```jsx
// Ternary
{isLoggedIn ? <Dashboard /> : <Login />}

// Logical &&
{error && <ErrorMessage msg={error} />}
```

---

## 23. What is list rendering?

Rendering arrays of data as lists of components. Use `.map()` to transform data arrays into JSX arrays. Each element MUST have a unique `key` prop for efficient reconciliation.

```jsx
{users.map(user => (
  <UserCard key={user.id} name={user.name} email={user.email} />
))}
```

---

## 24. What are fragments?

Fragments (`<React.Fragment>` or `<>`) let you group elements without adding an extra DOM node. Useful when a component must return multiple elements. The short syntax `<> </>` doesn't support the `key` attribute.

```jsx
<>
  <h1>Title</h1>
  <p>Paragraph</p>
</>
// No extra div in DOM
```

---

## 25. What is React.StrictMode?

`StrictMode` is a development tool that highlights potential issues. It:

1. Double-invokes certain functions to detect side effects.
2. Warns about deprecated APIs.
3. Detects unexpected side effects.

Has NO effect in production.

```jsx
<React.StrictMode>
  <App />
</React.StrictMode>
```

---

## 26. What is React root?

React root is the entry point — the top-level container connecting React to the real DOM. Created with `createRoot()` in React 18 (or `ReactDOM.render()` in older versions).

```js
import { createRoot } from 'react-dom/client';

const root = createRoot(document.getElementById('root'));
root.render(<App />);
```

---

## 27. createRoot vs render?

- `createRoot()` — React 18 API enabling concurrent features (automatic batching, transitions, etc.).
- `ReactDOM.render()` — legacy, uses blocking rendering mode.

`createRoot` also renders into a container element.

---

## 28. What is hydration in React?

In SSR, the server sends pre-rendered HTML. The browser displays it immediately (fast first paint). React then "hydrates" the static HTML — attaches event listeners and makes it interactive. `hydrateRoot()` is the React 18 API for this.

```js
import { hydrateRoot } from 'react-dom/client';

hydrateRoot(document.getElementById('root'), <App />, initialData);
```

---

## 29. What is React lifecycle?

React components go through phases:

1. **Mounting** — component created and inserted into DOM.
2. **Updating** — state or props change, component re-renders.
3. **Unmounting** — component removed from DOM.

Hooks map to these phases via `useEffect`.

---

# Hooks (Q31–Q80)

## 30. Mounting vs updating vs unmounting?

- **Mounting** — `constructor` → `render` → DOM update → `componentDidMount` / `useEffect([])`
- **Updating** — state/props change → `render` → DOM update → `componentDidUpdate` / `useEffect([dep])`
- **Unmounting** — `componentWillUnmount` / `useEffect` cleanup runs

## 31. What are hooks?

Hooks are functions (prefixed with `use`) that let functional components use React state, lifecycle, context, and other features. Introduced in React 16.8.

Rules:
- Only call at top level (not in loops/conditions).
- Only call from React functions or custom hooks.

---

## 32. Why were hooks introduced?

Problems hooks solve:

1. Reusing stateful logic (no HOCs or render props needed).
2. Complex components with split logic (lifecycle methods mixed concerns).
3. Classes are confusing (`this`, binding).

Hooks let you extract and share stateful logic as custom hooks.

---

## 33. What is useState?

`useState(initialValue)` returns `[state, setter]`. The setter triggers a re-render. Initial value can be a primitive, object, or a function (lazy initialization — called once). The setter **replaces** state (doesn't merge like class `setState`).

```js
const [user, setUser] = useState(null);
const [count, setCount] = useState(() => expensiveInit()); // lazy init
```

---

## 34. How useState works internally?

React maintains state in a linked list associated with each component instance (fiber). Each `useState` call appends to this list. The call order must be stable — this is why hooks can't be in conditionals. The setter schedules a re-render of the component.

---

## 35. What is useEffect?

`useEffect(fn, deps)` runs a side effect after render. It replaces `componentDidMount`, `componentDidUpdate`, and `componentWillUnmount`. Returns an optional cleanup function. The dependency array controls when it runs.

---

## 36. When does useEffect run?

1. No deps array — after **every** render.
2. Empty `[]` — after **first** render only (mount).
3. `[dep1, dep2]` — after first render AND whenever `dep1` or `dep2` changes.

Cleanup runs before the next effect and on unmount.

```js
useEffect(() => {});          // every render
useEffect(() => {}, []);      // mount only
useEffect(() => {}, [count]); // when count changes
```

---

## 37. Dependency array?

The dependency array tells React when to re-run the effect. React compares deps with shallow equality. Include all reactive values used inside the effect (exhaustive-deps ESLint rule). Omitting a dep causes stale closure bugs.

> Use the `eslint-plugin-react-hooks/exhaustive-deps` rule to avoid missed dependencies.

---

## 38. Cleanup function in useEffect?

Return a function from `useEffect` to clean up side effects before the next effect runs or on unmount. Used for: unsubscribing, clearing timers, canceling requests, removing event listeners.

```js
useEffect(() => {
  const sub = dataStream.subscribe(handleData);
  return () => sub.unsubscribe(); // cleanup on unmount/update
}, []);
```

---

## 39. useEffect vs componentDidMount?

- `componentDidMount` — runs once after mount (class component).
- `useEffect` with `[]` — runs once after mount (functional component).

`useEffect` is more general — it also handles cleanup (return fn) and re-runs on dep changes.

---

## 40. useEffect vs useLayoutEffect?

- `useEffect` — runs **asynchronously** after DOM is painted. No visual flicker but slightly delayed.
- `useLayoutEffect` — runs **synchronously** after DOM update but **before** paint. Use for measuring DOM layout, avoiding flicker.

Prefer `useEffect`; use `useLayoutEffect` only for DOM measurements.

---

## 41. What is useRef?

`useRef(initialValue)` returns a mutable object `{ current: value }`. Persists across renders without causing re-renders. Used for:

1. Accessing DOM elements.
2. Storing mutable values (like previous state, timer IDs).
3. Instance variables (not in state).

```jsx
const inputRef = useRef(null);
<input ref={inputRef} />
const focusInput = () => inputRef.current.focus();
```

---

## 42. useRef vs state?

- `useRef` — changes don't trigger re-renders, value persists across renders. Used for DOM refs and mutable values.
- `state` — changes trigger re-renders. Used for values that should cause UI updates.

Use ref for values that shouldn't cause re-renders.

---

## 43. What is useMemo?

`useMemo(fn, deps)` memoizes the **return value** of a computation. Recalculates only when deps change. Used for expensive calculations to avoid re-computation on every render.

```js
const sortedList = useMemo(
  () => [...items].sort((a, b) => a.name.localeCompare(b.name)),
  [items]
);
```

---

## 44. When to use useMemo?

Use when:
1. Computation is expensive (sorting large arrays, complex math).
2. Result is used as a dependency in other hooks (to maintain referential equality).

Do NOT overuse — `useMemo` itself has overhead and clutters code.

---

## 45. What is useCallback?

`useCallback(fn, deps)` memoizes the **function reference**. Returns the same function object across renders if deps don't change. Used when passing callbacks to child components wrapped in `React.memo`.

```js
const handleClick = useCallback(() => {
  onSelect(id);
}, [id, onSelect]);
```

---

## 46. useCallback vs useMemo?

`useCallback(fn, deps)` === `useMemo(() => fn, deps)`.

- `useCallback` — memoizes a function reference. Use to prevent child re-renders when passing callbacks.
- `useMemo` — memoizes a computed value. Use to prevent expensive re-computations.

---

## 47. What is useContext?

`useContext(Context)` reads the current value of a Context. Re-renders when context value changes. Avoids prop drilling for globally shared data (theme, auth, locale). Use with `createContext()` and `Provider`.

```jsx
const ThemeContext = createContext('light');

function App() {
  return (
    <ThemeContext.Provider value="dark">
      <Child />
    </ThemeContext.Provider>
  );
}

function Child() {
  const theme = useContext(ThemeContext);
  return <div>{theme}</div>;
}
```

---

## 48. Context API?

Context provides a way to share values between components without passing props at every level. Created with `createContext()`, provided via `<Context.Provider value={}>`, consumed via `useContext()`. All consumers re-render when context value changes.

---

## 49. When to use Context?

Use Context for: theme, locale, authentication, current user, feature flags.

Do NOT use for:
- Frequently changing values (causes all consumers to re-render).
- Replacing Redux for complex state with many actions.

Context is for global configuration, not state management.

---

## 50. What is a custom hook?

A custom hook is a function starting with `use` that calls other hooks. Lets you extract and reuse stateful logic across components. It's the primary code reuse mechanism in hooks-based React.

```js
function useWindowSize() {
  const [size, setSize] = useState({ w: window.innerWidth, h: window.innerHeight });

  useEffect(() => {
    const update = () => setSize({ w: window.innerWidth, h: window.innerHeight });
    window.addEventListener('resize', update);
    return () => window.removeEventListener('resize', update);
  }, []);

  return size;
}
```

---

## 51. Rules of hooks?

1. Only call hooks at the **top level** (not in conditions, loops, or nested functions).
2. Only call hooks from React function components or custom hooks (not regular JS functions).

These rules ensure the hook call order is consistent across renders.

---

## 52. Why must hooks be at top level?

React stores hook state in a linked list. The call order determines which state belongs to which hook. If hooks were inside conditions, the order could change between renders, causing React to associate state with the wrong hook — bugs and crashes.

---

## 53. What is useReducer?

`useReducer(reducer, initialState)` is like `useState` but for complex state logic. Takes a reducer function `(state, action) => newState` and returns `[state, dispatch]`.

Better than `useState` when:
- State has many sub-values.
- Next state depends on previous state.
- Complex update logic.

```js
const [state, dispatch] = useReducer(reducer, { count: 0 });

dispatch({ type: 'INCREMENT' });
dispatch({ type: 'SET', payload: 10 });
```

---

## 54. useReducer vs useState?

- `useState` — simple scalar or object state.
- `useReducer` — complex state with multiple sub-values, many update actions, state transitions based on previous state. Makes complex state logic readable and testable.

---

## 55. What is dispatch?

`dispatch` is the function returned by `useReducer` (and Redux's `store.dispatch`). Calling `dispatch(action)` sends an action object to the reducer function, which returns the new state. Actions typically have `type` and optional `payload`.

---

## 56. What is useImperativeHandle?

`useImperativeHandle(ref, createHandle, deps)` customizes the instance value exposed when a parent uses `ref` on a child (with `forwardRef`). Lets you expose a limited API instead of the full DOM element.

```jsx
const Input = forwardRef((props, ref) => {
  const inputRef = useRef();

  useImperativeHandle(ref, () => ({
    focus: () => inputRef.current.focus(),
    clear: () => { inputRef.current.value = ''; }
  }));

  return <input ref={inputRef} />;
});
```

---

## 57. What is forwardRef?

`React.forwardRef()` lets a component receive a `ref` from its parent and pass it to a DOM element or child component. Needed because refs don't work like regular props — they're handled specially by React.

```jsx
const FancyInput = React.forwardRef((props, ref) => (
  <input ref={ref} {...props} className="fancy" />
));

// Parent:
const ref = useRef();
<FancyInput ref={ref} />
```

---

## 58. What is useId?

`useId()` generates a unique, stable ID for a component instance. Useful for accessibility attributes (`aria-labelledby`, `htmlFor`) where IDs must be unique and consistent across server/client renders.

```jsx
const id = useId();

return (
  <>
    <label htmlFor={id}>Name:</label>
    <input id={id} />
  </>
);
```

---

## 59. What is useTransition?

`useTransition()` returns `[isPending, startTransition]`. Wrap non-urgent state updates in `startTransition` to mark them as lower priority. React can interrupt these to keep the UI responsive (concurrent mode).

```jsx
const [isPending, startTransition] = useTransition();

const search = (q) => {
  startTransition(() => setResults(filter(q))); // non-urgent
};

{isPending && <Spinner />}
```

---

## 60. What is useDeferredValue?

`useDeferredValue(value)` returns a deferred version of the value. React may use the old value first to keep the UI responsive, then update with the new value when it has time. Similar to `useTransition` but for values you don't control.

## 61. What is concurrent rendering?

Concurrent rendering (React 18) lets React prepare multiple versions of the UI simultaneously. React can interrupt, pause, and resume rendering. Enables: `useTransition`, `Suspense`, `useDeferredValue`. Keeps the UI responsive during heavy rendering.

---

## 62. What is batching?

Batching groups multiple state updates into a single re-render.

- **React 17** — only batched inside event handlers.
- **React 18** (automatic batching) — batches ALL state updates everywhere (async, timeouts, promises, native events).

```js
// React 18 - all updates batched in one render
setTimeout(() => {
  setA(1);
  setB(2); // only ONE re-render, not two
}, 0);
```

---

## 63. Automatic batching in React 18?

React 18 automatically batches state updates in ALL contexts: event handlers, timeouts, promises, native event listeners. In React 17, async code would cause separate re-renders. Use `ReactDOM.flushSync()` to opt out of batching when needed.

---

## 64. What is Suspense?

`Suspense` lets components "wait" for something (lazy loading, data fetching) and show a fallback while waiting. Used with `React.lazy()` for code splitting and with data fetching libraries that support Suspense.

```jsx
<Suspense fallback={<Spinner />}>
  <LazyComponent />
</Suspense>
```

---

## 65. What is lazy loading in React?

`React.lazy(() => import('./Component'))` dynamically imports a component only when first rendered. Must be wrapped in `Suspense`. Enables route-based code splitting — reduces initial bundle size.

```jsx
const Chart = React.lazy(() => import('./Chart'));

function App() {
  return (
    <Suspense fallback={<Loading />}>
      <Chart />
    </Suspense>
  );
}
```

---

## 66. React.lazy vs dynamic import?

- `React.lazy()` — React-specific wrapper around `import()` that returns a special component. Works with `Suspense` for loading states.
- Dynamic `import()` — general JS feature returning a Promise.

`React.lazy` is built on top of dynamic `import`.

---

## 67. What is error boundary?

Error boundaries are class components (or libraries like `react-error-boundary`) that catch JS errors in their child tree, log them, and display a fallback UI instead of crashing the whole app. Hooks cannot create error boundaries — must use a class component.

```jsx
class ErrorBoundary extends React.Component {
  state = { hasError: false };

  static getDerivedStateFromError() {
    return { hasError: true };
  }

  componentDidCatch(error, info) {
    logError(error, info);
  }

  render() {
    return this.state.hasError ? <Fallback /> : this.props.children;
  }
}
```

---

## 68. Can hooks replace lifecycle methods?

Yes:

- `useEffect([])` → `componentDidMount`
- `useEffect([dep])` → `componentDidUpdate`
- `useEffect` cleanup → `componentWillUnmount`
- `React.memo` + `useMemo` → `shouldComponentUpdate`

Custom hooks can encapsulate complex lifecycle logic more cleanly than class methods.

---

## 69. What are stale closures?

A stale closure captures an outdated value of a variable. In `useEffect` or callbacks, if deps are missing, the function sees the old value of state/props from when it was created, not the current value.

```js
// Bug: stale closure
const [count, setCount] = useState(0);

useEffect(() => {
  const id = setInterval(() => console.log(count), 1000);
  return () => clearInterval(id);
}, []); // count is always 0 (stale!)

// Fix: add count to deps, or use ref
```

---

## 70. How to avoid infinite re-renders?

1. Don't create new object/array literals or functions in the dependency array without memoization.
2. Use `useMemo`/`useCallback` for stable references.
3. Use functional updates to avoid depending on state.
4. Check that effects don't set state unconditionally.

---

## 71. What is dependency optimization?

Ensure `useEffect`/`useMemo`/`useCallback` deps are minimal and stable.

- **Primitives** — compared by value (safe).
- **Objects/arrays/functions** — compared by reference (use `useMemo`/`useCallback` to stabilize).

Omitting deps causes stale closures; over-including causes too many re-runs.

---

## 72. Why do functions re-create on render?

In JS, every function defined inside a component body is a new function object on every render. This means child components or effects depending on that function see a "changed" value every render. Use `useCallback` to return the same function reference when deps haven't changed.

---

## 73. Memoization in React?

Three tools:

1. `React.memo` — memoizes component render (shallow props comparison).
2. `useMemo` — memoizes computed values.
3. `useCallback` — memoizes function references.

Over-memoizing adds overhead and code complexity — memoize only bottlenecks.

---

## 74. When NOT to use useMemo?

1. Simple/fast computations — overhead of `useMemo` exceeds savings.
2. Values that change almost every render.
3. When code clarity matters more.
4. Premature optimization.

Only memoize when profiling shows a real performance problem.

---

## 75. When NOT to use useCallback?

1. When the child doesn't use `React.memo`.
2. When the function changes every render anyway (deps change every time).
3. Simple inline functions.
4. Premature optimization.

`useCallback` only helps when paired with `React.memo` on the receiving component.

---

## 76. What is hook composition?

Hook composition is building complex behavior by combining multiple hooks inside a custom hook. Like function composition — each hook handles one concern, the custom hook combines them. Enables clean, reusable, testable logic extraction.

---

## 77. What is a side effect in React?

A side effect is anything that affects the outside world from inside a component: data fetching, DOM manipulation, subscriptions, timers, logging. React expects render to be pure — manage side effects in `useEffect`.

---

## 78. Sync vs async effects?

- `useEffect` — always runs **asynchronously** after paint.
- `useLayoutEffect` — runs **synchronously** after DOM updates but before paint (use for DOM measurements).

For async operations in `useEffect`, define an async function inside and call it — don't make the effect callback async directly.

```js
useEffect(() => {
  async function fetchData() {
    const data = await api.get('/data');
    setData(data);
  }
  fetchData();
}, []);
```

---

## 79. How React schedules updates?

React 18 uses a priority-based scheduler (Scheduler package). Update priorities:

- `SyncLane` — user input
- `InputContinuousLane` — scroll/drag
- `DefaultLane` — data fetching
- `TransitionLane` — `useTransition`
- `IdleLane` — idle work

Higher priority updates can interrupt lower priority ones (concurrent mode).

---

## 80. What is Fiber architecture?

React Fiber (React 16+) is the internal reconciliation engine. It represents the component tree as a linked list of fiber nodes. Enables:

- Incremental rendering (pause/resume)
- Priority-based scheduling
- Concurrent mode
- Error boundaries
- Suspense

# Advanced React (Q81–Q100)

---

## 81. What is React Fiber?

Fiber is the reimplementation of React's reconciliation algorithm (React 16+). It represents each component as a unit of work (fiber node). Enables incremental rendering — React can split work into chunks, pause, prioritize, and abort work. Foundation for concurrent features.

---

## 82. How does reconciliation work internally?

React performs a tree traversal comparing the new fiber tree with the current fiber tree (work-in-progress tree). It creates, updates, or deletes fibers. This happens in two phases:

1. **Render phase** (interruptible) — compute what changed.
2. **Commit phase** (synchronous) — apply changes to DOM.

---

## 83. What is the diffing algorithm?

React's diffing rules:

1. Different element types → destroy old tree, build new tree.
2. Same type elements → update attributes in place.
3. List items → use `key` to match items.

This achieves O(n) complexity vs O(n³) for general tree diffing.

---

## 84. Render phase vs commit phase?

- **Render phase** — pure computation, interruptible, can be paused/resumed/abandoned. Determines what changed.
- **Commit phase** — applies mutations to the real DOM, runs effects, not interruptible. Happens synchronously.

---

## 85. What is concurrent mode?

Concurrent mode lets React prepare multiple UI versions simultaneously and interrupt rendering. Enables:

- `useTransition` (non-blocking updates)
- `Suspense` for data fetching
- Automatic batching
- `useDeferredValue`

Keeps UI responsive during heavy computation.

---

## 86. What is time slicing?

Time slicing is React's ability to split rendering work into small chunks (slices) across multiple frames. React checks if there's higher-priority work (user input) and yields control to the browser between slices. Prevents long tasks from blocking the UI.

---

## 87. What is Suspense for data fetching?

Suspense can pause rendering while data loads. The library must support Suspense (React Query, Relay, etc.) by throwing a Promise. React shows the fallback until the Promise resolves. Simplifies loading states compared to conditional rendering.

---

## 88. What are server components?

React Server Components (RSC) run on the server during build/request time. They can directly access databases, file systems, and secrets. They render to a special format (not HTML), and their code is never sent to the client — zero JavaScript bundle contribution.

---

## 89. Client vs server components?

- **Server components** — render on server, no state/effects/browser APIs, can access backend, not in bundle.
- **Client components** (`'use client'`) — render on client (and optionally server for hydration), full React capabilities (hooks, events, browser APIs).

Default in Next.js 13+ App Router is server component.

---

## 90. What is streaming SSR?

Streaming SSR sends HTML in chunks (HTTP streaming). The server can send the shell (layout, above-fold content) immediately, while still rendering slower parts. Suspense boundaries define what to stream — fallback shown until each part resolves.

---

## 91. What is hydration mismatch?

A hydration mismatch occurs when server-rendered HTML differs from what React expects on the client. Causes: date/time rendering, random values, browser-only APIs, different data between server and client. React logs a warning and re-renders from scratch (slower).

---

## 92. What is React.memo?

`React.memo(Component)` is a HOC that memoizes a functional component. If props are shallowly equal to the previous render, React skips re-rendering. Use for expensive components that receive the same props frequently.

```jsx
const MemoCard = React.memo(({ user }) => (
  <div>{user.name}</div>
));
// Only re-renders when user prop reference changes
```

---

## 93. React.memo vs useMemo?

- `React.memo` — wraps a **component** to prevent re-renders when props are unchanged.
- `useMemo` — memoizes a **computed value** inside a component.

Both use memoization but at different levels — component level vs value level.

---

## 94. PureComponent?

`React.PureComponent` is the class component equivalent of `React.memo`. It implements `shouldComponentUpdate` with a shallow comparison of props and state. Prevents re-renders when props/state haven't changed shallowly.

---

## 95. What is a HOC (Higher-Order Component)?

A HOC is a function that takes a component and returns an enhanced component. Pattern for cross-cutting concerns (logging, auth, data fetching). Convention: name starts with `with`. Now largely replaced by hooks.

```jsx
function withAuth(WrappedComponent) {
  return function AuthComponent(props) {
    const { isAuthenticated } = useAuth();
    return isAuthenticated
      ? <WrappedComponent {...props} />
      : <Redirect to="/login" />;
  };
}
```

---

## 96. HOC vs hooks?

- **HOCs** — wrap components (nesting, harder to debug, props collision risk).
- **Hooks** — encapsulate logic inside a component (flat, no nesting, composable).

Hooks are preferred for new code. HOCs still useful for third-party library integration and class components.

---

## 97. What is render props?

Render props is a pattern where a component accepts a function prop that returns JSX. The component calls the function (sharing internal state as arguments). Enabled code sharing before hooks. Now largely replaced by custom hooks.

```jsx
function Mouse({ render }) {
  const [pos, setPos] = useState({ x: 0, y: 0 });
  return (
    <div onMouseMove={e => setPos({ x: e.clientX, y: e.clientY })}>
      {render(pos)}
    </div>
  );
}

<Mouse render={(pos) => <p>Position: {pos.x}, {pos.y}</p>} />
```

---

## 98. Render props vs HOC?

Both are code-sharing patterns:

- **Render props** — explicit (you see the data passed).
- **HOCs** — wrap and inject props implicitly (less visible).

Both cause "wrapper hell" with deep nesting. Custom hooks are the preferred modern alternative.

---

## 99. What is compound components?

Compound components is a pattern where a parent component implicitly shares state with child components, making them work together as a unit. Uses `React.Children` or Context internally. Examples: `<Select>`, `<Tabs>`, `<Accordion>`.

```jsx
<Select value={v} onChange={fn}>
  <Select.Option value="a">A</Select.Option>
  <Select.Option value="b">B</Select.Option>
</Select>
```

---

## 100. What is a portal?

`ReactDOM.createPortal(child, container)` renders a child component into a different DOM node outside the parent's hierarchy. The component still behaves like a React child (events bubble through React tree). Used for: modals, tooltips, dropdowns.

```jsx
function Modal({ children }) {
  return ReactDOM.createPortal(
    <div className="modal-overlay">{children}</div>,
    document.getElementById('modal-root')
  );
}
```

## 101. Use cases of portals?

1. **Modals** — avoid z-index and overflow issues.
2. **Tooltips** — position relative to viewport.
3. **Dropdown menus** — `overflow: hidden` parent.
4. **Toast notifications** — always on top.

All need to render outside the normal DOM flow.

---

## 102. What is ref forwarding?

Ref forwarding allows passing a `ref` through a component to one of its children (usually a DOM element). Required when you want to access a DOM element inside a library component. Uses `React.forwardRef()`.

---

## 103. What are synthetic events?

React wraps native browser events in `SyntheticEvent` — a cross-browser wrapper. Provides a consistent API across browsers. Events are pooled (pre-React 17) or non-pooled (React 17+). Access event properties directly; don't access them in async callbacks without persisting.

---

## 104. Event pooling?

Pre-React 17, `SyntheticEvent` objects were pooled for performance — properties were nullified after the event handler. In React 17+, event pooling was removed. Now event objects are regular JS objects and can be accessed asynchronously.

---

## 105. What is batching updates?

React batches multiple state updates together and performs a single re-render.

- **React 18** — batches automatically everywhere.
- **React 17** — only batched inside React event handlers.

---

## 106. What is priority scheduling?

React 18's scheduler assigns priorities to updates:

- **User interactions** (clicks, keystrokes) — highest priority.
- **Background updates** (data fetching, transitions) — lower priority.

High-priority updates can interrupt low-priority ones.

---

## 107. What is React Profiler?

React Profiler is a DevTools feature (and `<Profiler>` component) that measures rendering performance. Shows which components render, how often, and how long. Identifies unnecessary re-renders and performance bottlenecks.

```jsx
<Profiler id="App" onRender={(id, phase, actualDuration) => {
  console.log(id, phase, actualDuration + 'ms');
}}>
  <App />
</Profiler>
```

---

## 108. Performance optimization techniques in React?

1. `React.memo` + `useCallback` to prevent unnecessary renders.
2. `useMemo` for expensive calculations.
3. Virtualize long lists (`react-window`).
4. Code splitting with `React.lazy`.
5. Avoid anonymous functions in JSX.
6. Use production build.
7. Debounce/throttle handlers.

---

## 109. Code splitting in React?

- `React.lazy` + `Suspense` — component-level splitting.
- React Router with `lazy()` — route-level splitting.

Each route loads its JS only when visited — dramatically reduces initial bundle size.

---

## 110. Lazy loading images in React?

1. Native `loading="lazy"` attribute.
2. `IntersectionObserver` API.
3. Libraries like `react-lazyload` or `react-intersection-observer`.
4. Next.js `<Image>` component (automatic optimization).

---

## 111. What is tree structure diffing?

React uses heuristics:

- Components of different types produce different trees (replace entirely).
- Keys help match list items.
- React assumes sibling components don't move — only same-type, same-position updates are reconciled.

---

## 112. How React handles large lists?

Long lists cause slow renders. Solutions:

1. Virtualization (`react-window`, `react-virtualized`) — only renders visible items.
2. Pagination.
3. Infinite scroll with `IntersectionObserver`.
4. `React.memo` on list items.
5. Key optimization.

---

## 113. Windowing / virtualization?

Virtualization renders only the visible items in a list (the "window"). As the user scrolls, items are recycled. Reduces DOM nodes from thousands to a dozen. Critical for large datasets (10k+ rows).

---

## 114. react-window vs react-virtualized?

- **react-window** — smaller, faster, modern API. Recommended for most use cases.
- **react-virtualized** — larger, more features (grids, masonry, auto-sizers), but heavier.

`react-window` is a rewrite of `react-virtualized` by the same author.

---

## 115. What is hydration performance?

Slow hydration blocks the main thread. Selective hydration (React 18) hydrates in priority order — `Suspense` boundaries let React hydrate visible parts first. Progressive hydration defers hydration of off-screen content.

---

## 116. What is SSR?

Server-Side Rendering renders React components on the server to an HTML string. The browser receives fully-formed HTML (good for SEO, fast first paint). Then React hydrates it. Used with Next.js, Remix.

---

## 117. CSR vs SSR vs SSG?

- **CSR** — client renders everything in browser. Good DX, poor initial load/SEO.
- **SSR** — server renders per request. Good SEO, fast FCP, higher server load.
- **SSG** — pre-rendered at build time. Fastest, best SEO, but content is static until next build.

---

## 118. What is Next.js?

Next.js is a React framework by Vercel providing: SSR, SSG, ISR, App Router, API routes, image optimization, font optimization, built-in TypeScript, automatic code splitting, and deployment infrastructure.

---

## 119. When to use SSR?

Use SSR when:

1. SEO is critical (content must be crawled).
2. Content changes per request (personalized dashboards).
3. First-paint performance matters.
4. Auth-gated pages.

Use SSG for content that doesn't change per user.

---

## 120. What are render phases?

React's two-phase commit:

- **Render phase** (pure, interruptible) — creates a fiber work-in-progress tree describing what needs to change.
- **Commit phase** (synchronous, side-effectful) — applies DOM changes, runs effects (`useLayoutEffect`, `useEffect`) in order.

# Redux (Q121–Q145)

---

## 121. What is Redux?

Redux is a predictable state container for JavaScript apps. State lives in a single store. State is read-only (only actions can change it). Changes are made with pure reducer functions. Works with React via `react-redux`.

---

## 122. Why Redux?

1. Centralized state — single source of truth.
2. Predictable state transitions.
3. Time-travel debugging (DevTools).
4. State is serializable.
5. Easy testing (pure reducers).
6. Useful for large apps with complex state sharing.

---

## 123. Core principles of Redux?

1. **Single source of truth** — one store.
2. **State is read-only** — only actions change state.
3. **Changes via pure functions** — reducers are pure functions that return new state from old state + action.

---

## 124. What is a Redux store?

The store holds the complete state tree. Created with `configureStore()` (Redux Toolkit) or `createStore()`. Provides `getState()`, `dispatch()`, and `subscribe()` methods.

```js
const store = configureStore({
  reducer: {
    counter: counterReducer,
    users: usersReducer
  }
});
console.log(store.getState());
```

---

## 125. What is a Redux action?

An action is a plain JS object with a `type` property describing what happened. Optionally has a `payload`. Dispatched to the store to trigger state changes.

```js
const increment = { type: 'counter/increment' };
const setUser   = { type: 'user/set', payload: { id: 1, name: 'Alice' } };
```

---

## 126. What is a reducer?

A reducer is a pure function `(state, action) => newState`. It determines how state changes in response to an action. Must not mutate state directly — return a new state object. Must have no side effects.

```js
function counterReducer(state = { value: 0 }, action) {
  switch (action.type) {
    case 'increment': return { value: state.value + 1 };
    case 'decrement': return { value: state.value - 1 };
    default:          return state;
  }
}
```

---

## 127. What is Redux dispatch?

`dispatch(action)` is the only way to trigger a state change. It sends an action to the store, which passes it through middleware, then to the reducer.

```js
// In react-redux:
const dispatch = useDispatch();
dispatch(increment());
```

---

## 128. What is Redux middleware?

Middleware is a plugin for the dispatch pipeline. It can inspect actions, modify them, dispatch additional actions, or perform side effects (API calls, logging). Applied with `applyMiddleware()` or `configureStore`'s `middleware` option.

---

## 129. Redux flow?

```
User interaction
  → dispatch(action)
  → middleware (thunk, saga)
  → reducer
  → new state
  → store updates
  → connected components re-render
```

---

## 130. What is immutability in Redux?

Reducers must return new state objects, not mutate the existing state. This enables time-travel debugging, change detection, and predictability. Redux Toolkit uses Immer under the hood, allowing "mutating" syntax that produces new objects.

```js
// Redux Toolkit (Immer) - looks like mutation but isn't
const slice = createSlice({
  reducers: {
    increment: state => { state.value += 1; } // Immer handles immutability
  }
});
```

---

## 131. Why must reducers be pure?

1. Produce predictable, testable behavior.
2. Enable time-travel debugging (replay actions).
3. Allow change detection via shallow equality.
4. Enable multiple instances of a store.
5. Prevent unexpected side effects.

---

## 132. What is combineReducers?

`combineReducers({ key: reducer })` merges multiple reducers into one root reducer. Each reducer manages its own slice of state. Equivalent to Redux Toolkit's automatically combined slice reducers in `configureStore`.

---

## 133. What is Redux Toolkit (RTK)?

RTK is the official, opinionated toolset for Redux. It includes:

- `configureStore` — setup with DevTools
- `createSlice` — actions + reducer
- `createAsyncThunk` — async actions
- `createEntityAdapter` — normalized state
- `RTK Query` — data fetching

Reduces boilerplate significantly.

---

## 134. Why use Redux Toolkit?

1. Much less boilerplate.
2. Immer for safe mutations.
3. Built-in DevTools.
4. `createAsyncThunk` for async.
5. RTK Query for data fetching.
6. Official recommendation from Redux team.

---

## 135. What is createSlice?

`createSlice` auto-generates action creators and action types from reducer functions. Reduces the boilerplate of separate action type constants and action creators.

```js
const counterSlice = createSlice({
  name: 'counter',
  initialState: { value: 0 },
  reducers: {
    increment: state => { state.value += 1; },
    setCount:  (state, action) => { state.value = action.payload; }
  }
});

export const { increment, setCount } = counterSlice.actions;
```

---

## 136. What is createAsyncThunk?

`createAsyncThunk` generates a thunk that dispatches `pending`/`fulfilled`/`rejected` action types automatically when an async operation runs.

```js
export const fetchUser = createAsyncThunk('users/fetch', async (userId) => {
  const res = await axios.get(`/users/${userId}`);
  return res.data;
});

// In slice extraReducers:
builder.addCase(fetchUser.fulfilled, (state, action) => {
  state.users.push(action.payload);
});
```

---

## 137. What is RTK Query?

RTK Query is a data fetching and caching tool built into Redux Toolkit. Automatically generates hooks for fetching, caching, invalidating, and re-fetching data. Eliminates the need for manual loading/error/data state management.

```js
const api = createApi({
  reducerPath: 'api',
  baseQuery: fetchBaseQuery({ baseUrl: '/api' }),
  endpoints: (builder) => ({
    getUser: builder.query({ query: (id) => `users/${id}` }),
  })
});

const { data, isLoading } = useGetUserQuery(userId);
```

---

## 138. Redux vs Context API?

- **Redux** — robust for large apps, DevTools, middleware, time-travel, predictable. Uses selectors to avoid unnecessary re-renders.
- **Context** — built-in, simpler, good for low-frequency updates (theme, auth). Re-renders all consumers on every change.

---

## 139. When NOT to use Redux?

1. Simple apps with minimal state.
2. State is local to one component.
3. Server state only (use React Query instead).
4. Adds unnecessary complexity for small projects.

> Don't use Redux just because you're using React.

---

## 140. What is a selector?

A selector is a function that extracts and derives data from the Redux state. Keeps components decoupled from state shape. Memoized selectors (`reselect`) prevent expensive re-computations.

```js
const selectUserById = (state, id) => state.users.entities[id];

const user = useSelector(state => selectUserById(state, userId));
```

---

## 141. What is reselect?

Reselect is a library for creating memoized selectors. `createSelector(inputSelectors, resultFn)` returns a selector that only recomputes when inputs change. RTK includes `createSelector` from reselect.

```js
const selectExpensiveData = createSelector(
  [selectRawData, selectFilter],
  (raw, filter) => raw.filter(item => item.type === filter) // memoized
);
```

---

## 142. What is normalization in Redux?

Normalizing state stores entities by ID in a flat dictionary (like a database table) instead of nested arrays. Prevents duplicate data, simplifies updates, and makes lookups O(1). `createEntityAdapter` in RTK helps with this.

```js
// Normalized:
{ ids: [1, 2], entities: { 1: { ... }, 2: { ... } } }

// Not normalized:
[{ id: 1, ... }, { id: 2, ... }]
```

---

## 143. What is entity adapter?

`createEntityAdapter()` provides a standardized way to store and manage normalized data in Redux. Auto-generates CRUD operations (`addOne`, `removeOne`, `updateOne`, `setAll`, etc.) and selectors.

---

## 144. What is Redux DevTools?

Redux DevTools is a browser extension providing:

- Action history
- State diff viewer
- Time-travel debugging (replay/rewind)
- Action dispatching
- Import/export state

Automatically integrated with `configureStore`.

---

## 145. What is a thunk?

A thunk is a function returned by an action creator that receives `dispatch` (and `getState`) as arguments. Allows async logic (API calls) inside Redux. `redux-thunk` middleware intercepts functions dispatched to the store.

```js
export const fetchUsers = () => async (dispatch, getState) => {
  dispatch(setLoading(true));
  const data = await api.getUsers();
  dispatch(setUsers(data));
  dispatch(setLoading(false));
};
```

## 146. Thunk vs Saga?

- **Thunk** — simpler, async/await, less boilerplate, good for most cases.
- **Saga** — uses generators, more complex, but better for advanced flows: cancellation, debouncing, race conditions, complex orchestration.

Use thunk by default; saga for complex async requirements.

---

## 147. What is Redux Saga?

`redux-saga` is middleware using generator functions to manage side effects. Sagas are long-lived background processes that listen for actions and react accordingly. Supports: `takeLatest`, `takeEvery`, `put`, `call`, `fork`, `cancel`, `race`.

---

## 148. Generator in Saga?

Sagas use `function*` generators:

- `yield put(action)` — dispatches an action.
- `yield call(fn, args)` — calls a function (supports cancellation).
- `yield take(actionType)` — waits for an action.

Generators make async code look synchronous and are easy to test.

---

## 149. takeLatest vs takeEvery?

- `takeLatest` — cancels the previous running saga when a new action of the same type arrives. Good for search (only process the last request).
- `takeEvery` — spawns a new saga for every action. Good for logging all events.

---

## 150. What is Redux Persist?

`redux-persist` serializes and saves Redux state to storage (`localStorage`, `AsyncStorage`) and rehydrates it on app startup. Configure with `persistReducer` and `persistStore`.

---

## 151. What is store hydration?

Store hydration is the process of initializing Redux state from persisted/SSR data. On first render, preloaded state (from `localStorage` or server) populates the store. Redux Persist handles this automatically.

---

## 152. How to structure a Redux app?

- **Feature-based** (recommended) — each feature has its own slice, selectors, and async thunks in one folder.
- **Ducks pattern** — actions + reducer in one file per feature.

Avoid global state for local UI state.

---

## 153. Ducks pattern?

Ducks bundles actions, action creators, and reducer for a feature into a single file ("duck"). Redux Toolkit's `createSlice` naturally implements the ducks pattern.

---

## 154. Feature-based structure?

```
/features/users/usersSlice.js
/features/users/usersApi.js
/features/users/UserList.jsx
```

Each feature is self-contained. Scales well for large apps.

---

## 155. What is global vs local state?

- **Global state** — shared across many components (user auth, cart, app settings). Use Redux or Context.
- **Local state** — relevant to one component only (form input, toggle). Use `useState`.

Don't put everything in Redux.

---

## 156. What is derived state?

Derived state is computed from existing state (not stored separately). Use memoized selectors (`reselect`) to compute derived values. Storing derived state creates sync problems — compute it on demand.

---

## 157. What is side effects handling in Redux?

Side effects (API calls, timers, navigation) don't belong in reducers (pure functions). Use: thunks, sagas, Redux observables, or RTK Query. They intercept actions and run async logic before/after state updates.

---

## 158. Async flow in Redux?

1. Dispatch async action creator (thunk).
2. Thunk makes API call.
3. On success, dispatch fulfilled action with data.
4. Reducer updates state.
5. Component re-renders with new data.

`createAsyncThunk` automates this pattern.

---

## 159. How to avoid re-renders in Redux?

1. Use specific selectors (not the entire state).
2. Memoize selectors with `reselect`.
3. Use `shallowEqual` in `useSelector` for object returns.
4. Split large reducers.
5. Normalize state to avoid deeply nested subscriptions.

```js
const count = useSelector(state => state.counter.value); // specific
const { a, b } = useSelector(selector, shallowEqual);    // object comparison
```

---

## 160. Performance issues in Redux?

1. Selectors returning new object references every call.
2. Too many `useSelector` calls.
3. Large state causing expensive comparisons.
4. Rendering entire component trees on small changes.

Fix: memoized selectors, `React.memo`, splitting components.

---

## 161. What is batching in Redux?

Redux Toolkit uses `unstable_batchedUpdates` from `react-dom` to batch multiple dispatch calls into a single React re-render. React 18 automatic batching removes the need for this in most cases.

---

## 162. What is optimistic update?

An optimistic update immediately updates the UI assuming the API call will succeed, then reverts if it fails. Provides a fast, responsive feel. RTK Query has built-in optimistic update support via `onQueryStarted`.

```js
// RTK Query optimistic update
onQueryStarted: async (arg, { dispatch, queryFulfilled }) => {
  const patch = dispatch(
    api.util.updateQueryData('getPosts', undefined, draft => {
      draft.push(newPost);
    })
  );
  try {
    await queryFulfilled;
  } catch {
    patch.undo(); // revert on failure
  }
}
```

---

## 163. What is caching strategy in Redux?

RTK Query caches responses per endpoint + args. Cache entries have a `keepUnusedDataFor` lifetime. Invalidation via tags (`providesTags`, `invalidatesTags`) automatically refetches related queries when mutations occur.

---

## 164. What is stale data problem?

After a mutation, cached query data becomes stale. RTK Query solves this with tag-based invalidation — mutations declare which tags they invalidate; queries that provide those tags automatically refetch.

---

## 165. What is state normalization?

Storing entities in a flat structure by ID prevents nested data, duplication, and complex update logic. Makes state lookups O(1) and updates surgical (change one entity without re-creating arrays).

---

## 166. What is selector memoization?

`reselect`'s `createSelector` caches the last result. If the input selectors return the same values (by reference), the output function is skipped and the cached result is returned. Prevents unnecessary re-renders.

---

## 167. What is middleware chaining?

Redux middleware is composed as a chain. Each middleware calls `next(action)` to pass to the next middleware. The order matters: `logger → thunk → devtools`. `configureStore`'s `middleware` array controls the order.

---

## 168. Logging middleware?

A simple logging middleware logs every action and the resulting state. Useful for debugging.

```js
const loggerMiddleware = store => next => action => {
  console.log('dispatching:', action);
  const result = next(action);
  console.log('next state:', store.getState());
  return result;
};
```

---

## 169. Error handling in Redux?

1. In thunks: `try/catch` and dispatch error action.
2. `createAsyncThunk` automatically handles rejected state.
3. Global error handler middleware.
4. RTK Query's `isError`, `error` from `useQuery`.

---

## 170. Testing Redux?

- **Reducers** — test as pure functions (given state + action, assert new state).
- **Thunks** — test with mock `dispatch` and `getState`.
- **Connected components** — test with a mock store (`redux-mock-store` or `configureStore` with test data).

# React + Redux Interview Scenarios (Q171–Q200)

---

## 171. When to use Redux vs Context?

- **Context** — theme, locale, auth state (low-frequency updates).
- **Redux** — large apps, complex state logic, frequent updates, need DevTools, sharing state across many distant components.

Default to Context/useState, add Redux when you feel the pain.

---

## 172. How to optimize a React app?

1. Profile first (DevTools).
2. `React.memo` for expensive components.
3. `useMemo`/`useCallback` for stable references.
4. Code split with `lazy()`.
5. Virtualize long lists.
6. Debounce heavy handlers.
7. Optimize images.
8. Reduce bundle size.

---

## 173. How to prevent unnecessary renders?

1. `React.memo` on child components.
2. `useCallback` for function props.
3. `useMemo` for object/array props.
4. Specific `useSelector` selectors (not whole state).
5. Split large components.
6. Avoid inline object literals in JSX.

---

## 174. How React handles large-scale apps?

1. Feature-based folder structure.
2. Code splitting per route.
3. RTK Query for server state.
4. `React.memo` and virtualization for performance.
5. TypeScript for type safety.
6. Storybook for component docs.
7. Micro-frontend architecture for very large teams.

---

## 175. Folder structure for scalable app?

```
src/
  features/[feature]/{component, slice, api, hooks, types}
  components/ui/       # shared UI
  app/                 # store, router, providers
  hooks/               # global custom hooks
  utils/               # helpers
```

---

## 176. How to design reusable components?

1. Single responsibility.
2. Configurable via props.
3. Compound component pattern for complex widgets.
4. Forward refs when needed.
5. No hard-coded data.
6. Document with Storybook.
7. Prop types / TypeScript interfaces.

---

## 177. How to handle API calls in React?

1. `useEffect` + `useState` (basic).
2. Custom `useApi` hook.
3. React Query / SWR (server state).
4. RTK Query (with Redux).

RTK Query or React Query is recommended — handles caching, revalidation, loading/error states automatically.

---

## 178. Axios vs fetch?

- **fetch** — built-in, no dependency, returns `Response` object (need `.json()`), no request cancellation (use `AbortController`).
- **Axios** — automatic JSON parsing, request/response interceptors, better error handling (rejects on 4xx/5xx), request cancellation, timeout support.

---

## 179. How to manage global state?

1. `useState`/`useReducer` — local state.
2. Context — low-frequency global (theme, auth).
3. Redux / Zustand / Jotai — complex global state.
4. React Query / RTK Query — server state.

> Don't put server state in Redux.

---

## 180. What is the best state management?

No universal best. Guidelines:

- Local state → `useState`
- Shared simple → Context
- Complex global → Redux Toolkit / Zustand
- Server data → React Query / RTK Query
- Atomic state → Jotai / Recoil

Choose based on team, app complexity, and specific needs.

---

## 181. How to design a dashboard app?

1. Route-based code splitting.
2. RTK Query for data.
3. Memoized chart components.
4. Virtualized tables.
5. Skeleton loaders.
6. Real-time: WebSocket + Redux.
7. Role-based widget visibility.
8. Error boundaries per widget.

---

## 182. How to handle forms at scale?

React Hook Form (minimal re-renders, validation) or Formik. Schema validation with Zod or Yup. For complex multi-step forms: `useReducer` or form state machine. Uncontrolled inputs with refs for performance in large forms.

---

## 183. Controlled vs uncontrolled forms?

- **Controlled** — every keystroke goes through React state (instant validation, formatting, disabled states).
- **Uncontrolled** — refs read value on submit (less code, better performance for simple forms).

React Hook Form uses uncontrolled inputs by default.

---

## 184. Form libraries: Formik vs React Hook Form?

- **React Hook Form** — minimal re-renders (uncontrolled), smaller bundle, better performance, simpler API, growing adoption.
- **Formik** — controlled, more established, larger ecosystem.

React Hook Form recommended for new projects.

---

## 185. How to handle authentication in React?

1. Login → receive JWT + refresh token.
2. Store access token in memory (or `HttpOnly` cookie).
3. Auth context/Redux for `isAuthenticated`, `user`.
4. `PrivateRoute` protects routes.
5. Axios interceptor attaches token.
6. Token refresh on 401 response.

---

## 186. JWT storage best practices?

- **Best** — `HttpOnly` cookie (inaccessible to XSS).
- **Acceptable** — memory (state variable — lost on refresh).
- **Avoid** — `localStorage`/`sessionStorage` (vulnerable to XSS).

Use `SameSite=Strict/Lax` + HTTPS for cookies.

---

## 187. How to handle role-based UI?

1. Fetch user roles after auth.
2. Store in auth context/Redux.
3. Create a `Permission` component or `usePermission` hook.
4. Conditionally render based on role.
5. Server validates permissions for actual data — client-side is just UX.

---

## 188. How to protect routes?

Create a `RequireAuth` component that checks auth state and redirects to login if not authenticated. In React Router v6: wrap routes with `element={<RequireAuth><Page /></RequireAuth>}`.

```jsx
function RequireAuth({ children }) {
  const { user } = useAuth();
  const location = useLocation();

  if (!user) return <Navigate to="/login" state={{ from: location }} replace />;
  return children;
}
```

---

## 189. Lazy loading routes in React?

Import route components with `React.lazy()`. Wrap the route outlet with `Suspense`. Each route chunk loads only when the user navigates to that route.

---

## 190. Error handling strategy in React?

1. Error boundaries for unexpected render errors.
2. `try/catch` in event handlers.
3. `useEffect` async error handling.
4. RTK Query / React Query built-in error states.
5. Global axios interceptor for API errors.
6. Toast notifications for user-facing errors.

---

## 191. Retry failed API calls?

Use `axios-retry` or implement manually: on 5xx or network error, retry with exponential backoff. RTK Query has retry capability. React Query has a `retry` option. Always limit retry count to avoid infinite loops.

---

## 192. Debounce search implementation?

Debounce the search input: wait for user to stop typing (300ms) before making API call. Use a custom `useDebounce` hook or third-party (`use-debounce`).

```js
function useDebounce(value, delay) {
  const [debouncedValue, setDebouncedValue] = useState(value);

  useEffect(() => {
    const handler = setTimeout(() => setDebouncedValue(value), delay);
    return () => clearTimeout(handler);
  }, [value, delay]);

  return debouncedValue;
}
```

---

## 193. Infinite scrolling implementation?

Use `IntersectionObserver` to detect when a sentinel element (last item) enters the viewport. On intersection, fetch the next page and append. RTK Query handles pagination automatically with `fetchNextPage`.

---

## 194. Pagination vs infinite scroll?

- **Pagination** — user explicitly navigates pages, easy to bookmark, less memory, good for search results.
- **Infinite scroll** — continuous feed, no friction, hard to bookmark/return, high memory over time.

Use pagination for task-oriented UIs; infinite scroll for content feeds.

---

## 195. SEO in React (SPA)?

SPAs have poor SEO because crawlers see empty HTML. Solutions:

1. SSR (Next.js).
2. SSG for static content.
3. Prerendering (`react-snap`, `Prerender.io`).
4. Meta tags with `react-helmet`.
5. Sitemaps.

SSR/SSG is the best long-term solution.

---

## 196. Performance bottlenecks in React?

1. Unnecessary re-renders.
2. Missing virtualization for long lists.
3. Large bundle (no code splitting).
4. Blocking API calls on mount.
5. Memory leaks (unremoved listeners).
6. Expensive inline calculations without memoization.

---

## 197. Bundle size optimization?

1. Code splitting (`React.lazy`).
2. Tree shaking (ESM imports).
3. Analyze with `webpack-bundle-analyzer`.
4. Lazy import large libraries.
5. Use lighter alternatives (`day.js` vs `moment.js`).
6. Compress assets.
7. CDN for vendor chunks.

---

## 198. Monitoring tools for React?

1. Sentry — error tracking.
2. DataDog / New Relic — APM.
3. LogRocket — session replay.
4. React DevTools Profiler — render performance.
5. Lighthouse — Core Web Vitals.
6. Web Vitals library (CLS, LCP, FID).

---

## 199. Logging strategy in React?

1. Client errors: Sentry or custom error boundary logging.
2. User actions: analytics events (GA, Mixpanel).
3. Performance: Web Vitals logging.
4. API errors: log in axios interceptor.
5. Different log levels by environment.

---

## 200. Real-world architecture of React app?

```
UI (components)
  → hooks (business logic)
  → services (API)
  → store (Redux / Query)
  → Router
```

- Feature folders per domain.
- TypeScript throughout.
- Storybook for components.
- MSW for API mocking.
- Cypress for e2e.
- CI/CD via GitHub Actions.

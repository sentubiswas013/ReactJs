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
const element = <h1>Welcome</h1>;
```

---

## **3. Difference between React and ReactDOM**

* **React** is used to **create components and manage logic**
* **ReactDOM** is used to **render React components to the browser**
* React works behind the scenes, ReactDOM interacts with the DOM

```jsx
ReactDOM.createRoot(document.getElementById("root")).render(<App />);
```

---

## **4. React Development Workflow (npm, Webpack, Babel)**

* `npm start` runs the development server
* **Webpack** bundles all JS, CSS, and assets into one optimized file
* **Babel** converts modern JavaScript and JSX into browser-compatible code
* This setup gives fast reloads and optimized builds

```bash
npm start
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

## **6. Types of components in React**

* **Functional Components** – simple functions, most commonly used
* **Class Components** – older style, uses classes and lifecycle methods
* Today, functional components with hooks are preferred

```jsx
// Functional Component
function Button() {
  return <button>Click</button>;
```

---

## **7. Functional vs Class Components**

* Functional components are **simpler and cleaner**
* Class components use `this` and lifecycle methods
* Hooks allow functional components to use state and effects
* Functional components are the modern standard

```jsx
// Functional
const App = () => <h1>Hello</h1>;
```

---

## **8. What is the Virtual DOM?**

* The Virtual DOM is a **lightweight copy of the real DOM**
* React updates the Virtual DOM first
* It compares changes and updates only what’s needed in the real DOM
* This makes React **fast and efficient**

```jsx
setState({ count: count + 1 });
```

---

## **9. What are props in React?**

* Props are **inputs passed from parent to child components**
* They are **read-only** and cannot be modified by the child
* Props help make components **reusable and dynamic**

```jsx
function Greeting({ name }) {
  return <h1>Hello {name}</h1>;
}
```

---

## **10. State vs Props**

* **State** is managed inside a component
* **Props** come from parent components
* State can change; props are read-only
* State is used for dynamic data like user input

```jsx
const [count, setCount] = React.useState(0);
```

---

## **11. What is a key in React and why is it important?**

* A key is a **unique identifier** used when rendering lists
* It helps React understand **which items changed, added, or removed**
* Keys improve **performance** and prevent UI bugs
* Usually, we use an **id**, not an index

```jsx
items.map(item => <li key={item.id}>{item.name}</li>);
```

---

## **12. What are controlled components?**

* Controlled components are form inputs **controlled by React state**
* The input value comes from `useState`
* Every change updates the state
* This gives better control and validation

```jsx
const [name, setName] = useState("");

<input value={name} onChange={e => setName(e.target.value)} />
```

---

## **13. What are uncontrolled components?**

* Uncontrolled components store form data **in the DOM, not state**
* We use `useRef` to access values
* Less code, but less control
* Useful for quick or simple forms

```jsx
const inputRef = useRef();

<input ref={inputRef} />
```

---

## **14. Purpose of the `render()` method**

* `render()` defines **what UI should appear on the screen**
* It returns JSX
* React calls it whenever state or props change
* Used only in **class components**

```jsx
render() {
  return <h1>Hello</h1>;
}
```

---

## **15. What are React Hooks? Name some**

* Hooks let functional components **use state and lifecycle features**
* They remove the need for class components
* Common hooks include:

  * `useState`
  * `useEffect`
  * `useRef`
  * `useContext`

```jsx
const [count, setCount] = useState(0);
```

---

## **16. Difference between `useState` and `useEffect`**

* `useState` manages **data**
* `useEffect` manages **side effects**
* `useState` updates UI
* `useEffect` runs code after render

```jsx
useEffect(() => {
  console.log("Component mounted");
}, []);
```

---

## **17. What is `useEffect` and how does it work?**

* `useEffect` runs **after the component renders**
* Used for API calls, timers, subscriptions
* Dependency array controls when it runs
* Cleanup prevents memory leaks

```jsx
useEffect(() => {
  fetchData();
}, []);
```

---

## **18. What is `useRef` in React?**

* `useRef` stores a **mutable value** without causing re-renders
* Often used to access **DOM elements**
* Value persists between renders

```jsx
const inputRef = useRef();

<input ref={inputRef} />
```

---

## **19. Purpose of `useMemo` and `useCallback`**

* Both are used for **performance optimization**
* `useMemo` memoizes a **value**
* `useCallback` memoizes a **function**
* Helps avoid unnecessary re-renders

```jsx
const memoValue = useMemo(() => compute(), [data]);
```

---

## **20. React.createElement vs JSX**

* JSX is **syntactic sugar** for `React.createElement`
* JSX is easier to read and write
* Both do the same thing internally

```jsx
// JSX
<h1>Hello</h1>

// Without JSX
React.createElement("h1", null, "Hello");
```

---

## **21. Role of `React.StrictMode`**

* `StrictMode` helps find **potential problems**
* It runs checks in development only
* Helps detect unsafe lifecycle methods
* Does not affect production

```jsx
<React.StrictMode>
  <App />
</React.StrictMode>
```

---

## **22. What are Higher-Order Components (HOCs)?**

* A Higher-Order Component is a **function that takes a component and returns a new component**
* Used to **reuse logic** like authentication or logging
* Does not modify the original component
* Common in older React codebases

```jsx
const withAuth = (Component) => {
  return () => <Component isLoggedIn={true} />;
};
```

---

## **23. What is Context in React and why is it used?**

* Context allows data to be **shared across components**
* Avoids **prop drilling**
* Commonly used for theme, auth, language
* Makes global state easier to manage

```jsx
const ThemeContext = React.createContext();
```

---

## **24. Difference between `createContext` and `useContext`**

* `createContext` **creates** a context
* `useContext` **consumes** the context inside a component
* They work together
* `useContext` makes code cleaner

```jsx
const theme = useContext(ThemeContext);
```

---

## **25. Advantages of Functional Components**

* Less code and easier to read
* No `this` keyword confusion
* Hooks provide full lifecycle support
* Better performance and easier testing

```jsx
const App = () => <h1>Hello</h1>;
```

---

## **26. What is `setState` and how does it work?**

* `setState` updates component state
* It is **asynchronous**
* Causes re-render when state changes
* Used mainly in class components

```jsx
this.setState({ count: this.state.count + 1 });
```

---

## **27. `componentDidMount` vs `useEffect`**

* `componentDidMount` runs **once after render** in class components
* `useEffect` does the same in functional components
* `useEffect` can also handle updates and cleanup
* Hooks are more flexible

```jsx
useEffect(() => {
  fetchData();
}, []);
```

---

## **28. How to optimize performance in React**

* Use `React.memo` to avoid unnecessary re-renders
* Use `useMemo` and `useCallback`
* Avoid inline functions in JSX
* Use keys properly in lists

```jsx
const MemoComp = React.memo(Component);
```

---

## **29. What are React Fragments?**

* Fragments let you group elements **without adding extra DOM nodes**
* Keeps the DOM clean
* Useful when returning multiple elements

```jsx
<>
  <h1>Title</h1>
  <p>Description</p>
</>
```

---

## **30. What are error boundaries in React?**

* Error boundaries are components that **catch JavaScript errors** in child components
* They prevent the entire app from crashing
* Used to show a **fallback UI**
* Only work in **class components**

```jsx
class ErrorBoundary extends React.Component {
  componentDidCatch(error) {
    console.log(error);
  }
  render() {
    return this.props.children;
  }
}
```

---

## **31. Purpose of `shouldComponentUpdate`**

* Controls whether a component should **re-render**
* Helps improve **performance**
* Runs before re-rendering
* Used in class components only

```jsx
shouldComponentUpdate(nextProps) {
  return nextProps.value !== this.props.value;
}
```

---

## **32. What are portals in React?**

* Portals render components **outside the normal DOM hierarchy**
* Commonly used for modals and tooltips
* Keeps UI logic clean

```jsx
ReactDOM.createPortal(
  <Modal />,
  document.getElementById("modal-root")
);
```

---

## **33. How do you handle events in React?**

* Events are handled using **camelCase syntax**
* Functions are passed as event handlers
* Uses synthetic events for consistency

```jsx
<button onClick={handleClick}>Click</button>
```

---

## **34. What is React Router and how do you use it?**

* React Router enables **client-side navigation**
* Allows switching pages without reloading
* Uses routes and links

```jsx
<Route path="/about" element={<About />} />
```

---

## **35. `<Router>` components in React Router v6**

* `BrowserRouter` – used for web apps
* `Routes` – replaces `Switch`
* `Route` – defines path and component
* More simple and predictable than v5

```jsx
<BrowserRouter>
  <Routes>
    <Route path="/" element={<Home />} />
  </Routes>
</BrowserRouter>
```

---

## **36. What is lazy loading in React?**

* Lazy loading loads components **only when needed**
* Improves initial load performance
* Uses `React.lazy` and `Suspense`

```jsx
const Page = React.lazy(() => import("./Page"));
```

---

## **37. Difference between state and context**

* **State** is local to a component
* **Context** is global and shared
* Context avoids prop drilling
* State is simpler for small data

```jsx
const value = useContext(MyContext);
```

---

## **38. Managing global state in React**

* Use **Context API** for small apps
* Use **Redux / Zustand** for complex apps
* Keep global state minimal
* Choose based on app size

```jsx
const GlobalContext = React.createContext();
```

---

## **Advanced React.js Concepts**

## **39. What is Server-Side Rendering (SSR) in React?**

* **SSR** means rendering React components **on the server instead of the browser**.
* The server sends **fully rendered HTML** to the client.
* This improves **SEO**, **initial page load speed**, and **performance on slow devices**.
* Frameworks like **Next.js** make SSR easy.

```jsx
export async function getServerSideProps() {
  return { props: { data: "Hello" } };
}
```

---

## **40. What are React Suspense and Concurrent Mode?**

* **Suspense** lets React **wait for something** like data or lazy-loaded components.
* It shows a **fallback UI** while loading.
* **Concurrent Mode** allows React to **pause, resume, and prioritize rendering**.
* Together, they improve **user experience and responsiveness**.

```jsx
<Suspense fallback={<Loading />}>
  <LazyComponent />
</Suspense>
```

---

## **41. What is Code Splitting in React?**

* Code splitting breaks the app into **smaller bundles**.
* Only the required code is loaded when needed.
* This reduces **initial load time**.
* Commonly done using **React.lazy** and **Suspense**.

```jsx
const Dashboard = React.lazy(() => import('./Dashboard'));
```

---

## **42. What is React Fiber?**

* **React Fiber** is the **new reconciliation engine** introduced in React 16.
* It allows React to **break rendering work into small units**.
* React can **pause, resume, or cancel work**.
* This enables **Concurrent Mode and smoother UI updates**.

➡️ In short: Fiber makes React **faster and more responsive**.

---

## **43. What is React’s Reconciliation Algorithm?**

* Reconciliation is how React **updates the DOM efficiently**.
* React compares the **previous Virtual DOM** with the **new Virtual DOM**.
* It updates **only the changed parts** in the real DOM.
* This process improves **performance**.

```jsx
// Only changed elements are re-rendered
setState({ count: count + 1 });
```

---

## **44. How does React handle forms?**

* React uses **controlled components** for forms.
* Form values are stored in **state**.
* Input changes are handled using **onChange events**.
* This gives full **control and validation** over inputs.

```jsx
<input
  value={name}
  onChange={e => setName(e.target.value)}
/>
```

---

## **45. What is the significance of the `key` prop in lists?**

* `key` helps React **identify list items uniquely**.
* It improves **performance and correct re-rendering**.
* Keys should be **stable and unique**, not array indexes.
* Wrong keys can cause **UI bugs**.

```jsx
items.map(item => <li key={item.id}>{item.name}</li>);
```

---

## **46. How do you implement Custom Hooks in React?**

* Custom hooks let you **reuse logic across components**.
* They are **regular functions** that use React hooks.
* Must start with the word **`use`**.
* They improve **cleanliness and reusability**.

```jsx
function useCounter() {
  const [count, setCount] = useState(0);
  return { count, increment: () => setCount(count + 1) };
}
```

---

## **47. Difference between `componentWillMount`, `componentDidMount`, and `getDerivedStateFromProps`**

* `componentWillMount` runs **before rendering**, but it is **deprecated** and should not be used.
* `componentDidMount` runs **after the component is added to the DOM**.
* It’s commonly used for **API calls and subscriptions**.
* `getDerivedStateFromProps` is a **static method** used to **sync state with props**.

```jsx
static getDerivedStateFromProps(props, state) {
  return props.value !== state.value ? { value: props.value } : null;
}
```

---

## **48. What are React DevTools and how do they help in debugging?**

* React DevTools is a **browser extension** for Chrome and Firefox.
* It lets you **inspect component hierarchy**.
* You can view and modify **props, state, and hooks** in real time.
* Helps identify **unnecessary re-renders and performance issues**.

➡️ It’s essential for **debugging React apps efficiently**.

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
const [state, dispatch] = useReducer(reducer, initialState);
```

---

## **51. What are synthetic events in React?**

* Synthetic events are **React’s wrapper around native browser events**.
* They work **consistently across all browsers**.
* They improve **performance** by using event pooling.
* Example: `onClick`, `onChange`.

```jsx
<button onClick={handleClick}>Click</button>
```

---

## **52. How do you handle side effects in React?**

* Side effects include **API calls, timers, subscriptions**, etc.
* In functional components, side effects are handled using **`useEffect`**.
* Cleanup is done by returning a function from `useEffect`.

```jsx
useEffect(() => {
  const timer = setInterval(() => {}, 1000);
  return () => clearInterval(timer);
}, []);
```

---

## **53. What is the lifecycle of a React component?**

### **Class-based lifecycle**

* **Mounting**: constructor → render → componentDidMount
* **Updating**: render → componentDidUpdate
* **Unmounting**: componentWillUnmount

### **Functional lifecycle**

* `useEffect` handles **mount, update, and unmount** logic.

```jsx
useEffect(() => {
  console.log("Mounted");
  return () => console.log("Unmounted");
}, []);
```

---

## **54. Explain React's Context API and when to use it instead of prop drilling**

* Context API allows **global data sharing**.
* Avoids passing props through multiple layers.
* Best used for **theme, auth, language settings**.
* Not recommended for **frequently changing data**.

```jsx
const ThemeContext = React.createContext("light");
```

---

## **55. What are Render Props? Explain with example**

* Render props is a pattern where a component **shares logic via a function prop**.
* The function returns **UI based on shared logic**.
* Helps in **logic reuse without HOCs**.

```jsx
<Mouse render={pos => <h1>{pos.x}, {pos.y}</h1>} />
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
```

---

## **57. What are dynamic routes, and how would you implement them in React Router?**

* Dynamic routes allow URLs to contain **variables**, like IDs or usernames
* They are useful for pages like user profiles or product details
* You define them using `:paramName` in the route path
* The value is accessed using `useParams()`

**Example:**

```jsx
<Route path="/users/:id" element={<User />} />
```

```jsx
import { useParams } from "react-router-dom";

function User() {
  const { id } = useParams();
  return <h2>User ID: {id}</h2>;
}
```

---

## **58. How can you handle nested routes in React Router?**

* Nested routes are used when pages have **child views**
* React Router supports this using parent routes and the `Outlet` component
* The parent layout stays visible while child routes change

**Example:**

```jsx
<Route path="/dashboard" element={<Dashboard />}>
  <Route path="profile" element={<Profile />} />
  <Route path="settings" element={<Settings />} />
</Route>
```

```jsx
import { Outlet } from "react-router-dom";

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

## **59. What is the purpose of useParams, useLocation, and useHistory / useNavigate?**

* `useParams` → gets dynamic route values
* `useLocation` → gives current URL info like pathname or state
* `useNavigate` → programmatic navigation (replacement for `useHistory`)

**Example:**

```jsx
import { useNavigate, useLocation } from "react-router-dom";

function Page() {
  const navigate = useNavigate();
  const location = useLocation();

  return (
    <button onClick={() => navigate("/login")}>
      Go to Login
    </button>
  );
}
```

---

## **Performance Optimization Questions**

## **60. What strategies can you use to avoid unnecessary re-renders in React?**

* Use `React.memo` to prevent re-rendering unchanged components
* Use `useCallback` and `useMemo` to memoize functions and values
* Avoid passing new object or function references unnecessarily
* Keep state as local as possible

**Example:**

```jsx
const Button = React.memo(({ onClick }) => {
  return <button onClick={onClick}>Click</button>;
});
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

## **62. What is Redux, and how does it help in state management in React?**

* Redux is a **predictable state management library** for JavaScript apps
* It stores the entire app state in **one central store**
* Makes state changes predictable by enforcing strict rules
* Helps manage complex state shared across many components
* Improves debugging using tools like Redux DevTools

**Example:**

```js
const store = createStore(reducer);
```

```jsx
<Provider store={store}>
  <App />
</Provider>
```

---

## **63. What are the principles of Redux (actions, reducers, store)?**

* **Single source of truth** → entire state lives in one store
* **State is read-only** → only actions can change state
* **Changes are made with pure functions** called reducers

**Core pieces:**

```js
// Action
{ type: "INCREMENT" }

// Reducer
function counter(state = 0, action) {
  if (action.type === "INCREMENT") return state + 1;
  return state;
}
```

---

## **64. Explain the difference between local component state and global state in React applications.**

* **Local state** is used inside one component
* Managed using `useState` or `useReducer`
* **Global state** is shared across many components
* Managed using Redux, Context API, or Zustand
* Use local state for UI logic, global state for app-wide data

**Example:**

```jsx
// Local state
const [open, setOpen] = useState(false);
```

```js
// Global state (Redux)
state.user.isLoggedIn
```

---

## **65. How do you handle asynchronous actions in Redux?**

* Redux itself handles only synchronous actions
* Async logic is handled using **middleware**
* Common solutions include `redux-thunk` and `redux-saga`
* Middleware lets you dispatch actions after async work

**Example using thunk:**

```js
const fetchUsers = () => async (dispatch) => {
  const res = await fetch("/users");
  dispatch({ type: "SET_USERS", payload: await res.json() });
};
```

---

## **66. What are Redux middleware, and can you give an example of redux-thunk or redux-saga?**

* Middleware sits **between dispatch and reducer**
* Used for logging, async logic, API calls, or side effects
* `redux-thunk` allows actions to be functions
* `redux-saga` uses generator functions for complex flows

**Thunk example:**

```js
const thunkAction = () => (dispatch) => {
  setTimeout(() => {
    dispatch({ type: "SUCCESS" });
  }, 1000);
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

**Example:**

```js
render(<Parent />); // Full render (RTL)
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

**Example:**

```js
// Vite dev server
npm run dev
```

```js
// Webpack bundles JS, CSS, images into build files
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

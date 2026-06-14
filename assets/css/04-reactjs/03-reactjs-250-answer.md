# ✅ **1. React Fundamentals**

### **1. What is React?**

**React** is an **open-source JavaScript library** used to build **fast, interactive, and reusable User Interfaces (UI)**, especially for **Single Page Applications (SPAs)**. It was developed by **Facebook (Meta)**.

**Key Features:**

* **Component-Based Architecture**
* **Virtual DOM**
* **Reusable UI Components**
* **One-Way Data Binding**
* **High Performance**

**How it works:**

* The UI is divided into **small reusable components**.
* Each component manages its own data (**state**) and receives data through **props**.
* When data changes, React updates only the required parts of the page using the **Virtual DOM**.

**When to use:**

* Building **dynamic web applications**
* Creating **SPAs**
* Developing **large and scalable front-end projects**

**Code Example:**

```jsx
function App() {
  return <h1>Hello, React!</h1>;
}

export default App;
```

### **2. Features of React**

**React** provides several features that make front-end development easier and faster.

**Key Features:**

* **Component-Based Design** – Build reusable UI parts.
* **Virtual DOM** – Improves rendering performance.
* **JSX (JavaScript XML)** – Write HTML-like syntax inside JavaScript.
* **One-Way Data Flow** – Data flows from parent to child components.
* **Reusable Components** – Reduces duplicate code.
* **Fast Rendering** – Updates only changed elements.
* **Easy Integration** – Can be added to existing projects.

**When to use:**

* When you need a **maintainable**, **high-performance**, and **interactive** UI.

### **3. SPA vs MPA**

| Feature             | **SPA (Single Page Application)**   | **MPA (Multi Page Application)**     |
| ------------------- | ----------------------------------- | ------------------------------------ |
| **Page Reload**     | No full page reload                 | Reloads every page request           |
| **Performance**     | Faster after initial load           | Slower due to multiple reloads       |
| **User Experience** | Smooth and interactive              | Traditional navigation               |
| **Data Loading**    | Fetches data dynamically using APIs | Loads a new HTML page each time      |
| **Example**         | Gmail, Facebook                     | Traditional e-commerce or news sites |

**How it works:**

* **SPA:** Loads one HTML page and updates content dynamically using JavaScript.
* **MPA:** Requests a completely new page from the server for every navigation.

**When to use:**

* **SPA:** For **modern web applications** with rich user interactions.
* **MPA:** For **large content-driven websites** or applications requiring separate pages.

### **4. Virtual DOM**

**Virtual DOM (VDOM)** is a **lightweight in-memory copy of the Real DOM**. React uses it to improve performance by updating only the changed elements instead of re-rendering the entire page.

**How it works:**

1. React creates a **Virtual DOM** copy.
2. When data changes, a new Virtual DOM is created.
3. React compares the old and new Virtual DOMs using the **Diffing Algorithm**.
4. Only the changed elements are updated in the **Real DOM**.

**Key Features:**

* **Faster UI Updates**
* **Efficient DOM Manipulation**
* **Better Application Performance**
* **Reduces Unnecessary Re-rendering**

**When to use:**

* In applications with **frequent UI updates** and **dynamic data changes**.

**Code Example:**

```jsx
function Counter() {
  const [count, setCount] = React.useState(0);

  return (
    <button onClick={() => setCount(count + 1)}>
      Count: {count}
    </button>
  );
}
```

### **5. Real DOM vs Virtual DOM**

| Feature          | **Real DOM**                        | **Virtual DOM**                      |
| ---------------- | ----------------------------------- | ------------------------------------ |
| **Definition**   | Actual DOM in the browser           | Lightweight copy of the Real DOM     |
| **Update Speed** | Slower                              | Faster                               |
| **Rendering**    | Re-renders the entire affected tree | Updates only changed elements        |
| **Performance**  | Less efficient for frequent updates | Optimized with **Diffing Algorithm** |
| **Used By**      | Traditional JavaScript, jQuery      | React                                |

**How it works:**

* **Real DOM:** Every UI change directly updates the browser DOM.
* **Virtual DOM:** React first updates the Virtual DOM, compares it with the previous version, and changes only the necessary parts in the Real DOM.

**When to use:**

* **Virtual DOM** is ideal for **dynamic and interactive applications** with frequent UI updates.

### **6. JSX**

**JSX (JavaScript XML)** is a syntax extension for JavaScript that allows you to write **HTML-like code inside JavaScript**. React converts JSX into regular JavaScript using **Babel**.

**Key Features:**

* **Easy to read and write**
* **Combines HTML and JavaScript**
* **Supports JavaScript expressions using `{}`**
* **Improves code maintainability**

**How it works:**

* JSX is compiled into `React.createElement()` calls before running in the browser.

**When to use:**

* When building **React components** with a clean and readable UI structure.

**Code Example:**

```jsx
const element = <h1>Welcome to React</h1>;

function App() {
  return element;
}

export default App;
```

### **7. React Architecture**

**React Architecture** is based on a **Component-Based Architecture**, where the application is divided into small, reusable, and independent components.

**Key Features:**

* **Reusable Components**
* **One-Way Data Flow**
* **State and Props Management**
* **Virtual DOM for Performance**
* **Separation of UI and Business Logic**

**How it works:**

* The application starts from a **root component**.
* Components can contain child components, creating a **component tree**.
* Data is passed from **parent to child** using **props**, while each component can manage its own **state**.

**When to use:**

* For **large, scalable, and maintainable** front-end applications.

**Simple Structure:**

```text
App
 ├── Header
 ├── Sidebar
 ├── Content
 └── Footer
```

### **8. React Rendering Process**

The **React Rendering Process** is the way React updates the UI when **state** or **props** change.

**How it works:**

1. A component's **state** or **props** changes.
2. React creates a **new Virtual DOM**.
3. React compares the new and old Virtual DOM using the **Diffing Algorithm**.
4. React identifies the changed elements.
5. Only those changes are applied to the **Real DOM** (**Reconciliation**).

**Key Features:**

* **Efficient UI Updates**
* **Minimal DOM Manipulation**
* **Better Performance**
* **Automatic Re-rendering**

**When to use:**

* This process happens automatically in every **React application** whenever data changes.

**Code Example:**

```jsx
function Counter() {
  const [count, setCount] = React.useState(0);

  return (
    <button onClick={() => setCount(count + 1)}>
      {count}
    </button>
  );
}
```

### **9. React 18 Features**

**React 18** introduced several improvements for better **performance**, **user experience**, and **concurrent rendering**.

**Key Features:**

* **Concurrent Rendering** – React can prepare multiple UI updates in the background.
* **Automatic Batching** – Groups multiple state updates into a single render.
* **`createRoot()` API** – New rendering API replacing `ReactDOM.render()`.
* **Transitions (`startTransition`)** – Marks non-urgent updates to keep the UI responsive.
* **Suspense Improvements** – Better support for asynchronous data loading.
* **Streaming Server-Side Rendering (SSR)** – Faster page loading on the server.

**How it works:**

* React prioritizes urgent tasks (like user input) and delays non-critical updates for a smoother experience.

**When to use:**

* In **modern React applications** that require **better performance**, **faster rendering**, and **improved user interactions**.

**Code Example:**

```jsx
import ReactDOM from "react-dom/client";
import App from "./App";

const root = ReactDOM.createRoot(
  document.getElementById("root")
);

root.render(<App />);
```


# ✅ **2. Components**

### **1. Functional Components**

**Functional Components** are simple JavaScript functions that return **JSX**. They are the modern and recommended way to create React components and can use **Hooks** for state and lifecycle features.

**Key Features:**

* **Simple and easy to write**
* **Use Hooks (`useState`, `useEffect`, etc.)**
* **Better performance and readability**
* **No `this` keyword**

**How it works:**

* It receives **props** as input and returns the UI as JSX.
* React re-renders the component when **props** or **state** change.

**When to use:**

* For **new React applications** and most modern development.

**Code Example:**

```jsx id="eg6m9f"
function Welcome(props) {
  return <h1>Hello, {props.name}</h1>;
}

export default Welcome;
```

### **2. Class Components**

**Class Components** are ES6 classes that extend `React.Component`. They were used before Hooks were introduced and support **state** and **lifecycle methods**.

**Key Features:**

* **Uses `class` syntax**
* **Supports state and lifecycle methods**
* **Requires `this` keyword**
* **Can manage complex logic**

**How it works:**

* The component extends `React.Component` and must have a `render()` method that returns JSX.

**When to use:**

* Mainly when working with **legacy React projects**.

**Code Example:**

```jsx id="aolynj"
class Welcome extends React.Component {
  render() {
    return <h1>Hello, {this.props.name}</h1>;
  }
}

export default Welcome;
```

### **3. Component Lifecycle**

The **Component Lifecycle** is the sequence of stages a React component goes through from creation to removal.

**Lifecycle Phases:**

1. **Mounting** – Component is created and added to the DOM.
2. **Updating** – Component re-renders when **state** or **props** change.
3. **Unmounting** – Component is removed from the DOM.

**Common Lifecycle Methods (Class Components):**

* `componentDidMount()`
* `componentDidUpdate()`
* `componentWillUnmount()`

**How it works:**

* React automatically calls these methods at different stages of a component's life.

**When to use:**

* For tasks like **API calls**, **event listeners**, and **cleanup operations**.

**Code Example:**

```jsx id="sk4vw1"
class App extends React.Component {
  componentDidMount() {
    console.log("Component Mounted");
  }

  render() {
    return <h1>React Lifecycle</h1>;
  }
}
```

### **4. Pure Components**

A **Pure Component** is a special type of class component that prevents unnecessary re-rendering by performing a **shallow comparison** of **props** and **state**.

**Key Features:**

* **Improves performance**
* **Shallow comparison of props and state**
* **Avoids unnecessary UI updates**

**How it works:**

* React compares the current and previous values of **props** and **state**.
* If there is no change, the component does not re-render.

**When to use:**

* When component data changes rarely and you want to **optimize performance**.

**Code Example:**

```jsx id="2tx7wp"
class User extends React.PureComponent {
  render() {
    return <h1>{this.props.name}</h1>;
  }
}
```

### **5. Higher Order Components (HOC)**

A **Higher Order Component (HOC)** is a function that takes a component as input and returns a **new enhanced component** with additional functionality.

**Key Features:**

* **Code Reusability**
* **Separates Common Logic**
* **Enhances Existing Components**
* **Does Not Modify the Original Component**

**How it works:**

* A HOC wraps a component and adds extra behavior such as **authentication**, **logging**, or **data fetching**.

**When to use:**

* When multiple components need the **same functionality** without duplicating code.

**Code Example:**

```jsx id="x0g5be"
function withMessage(Component) {
  return function EnhancedComponent() {
    return (
      <>
        <h2>Welcome!</h2>
        <Component />
      </>
    );
  };
}

function Home() {
  return <h1>Home Page</h1>;
}

export default withMessage(Home);
```

### **6. Controlled Components**

A **Controlled Component** is a form element whose value is controlled by **React State**. React manages the form data and updates the UI.

**Key Features:**

* **State-driven form data**
* **Single source of truth**
* **Easy validation and error handling**
* **Better control over user input**

**How it works:**

* The input value is stored in **state**.
* `onChange` updates the state whenever the user types.
* The input displays the value from the state.

**When to use:**

* When you need **form validation**, **dynamic updates**, or **full control** over form inputs.

**Code Example:**

```jsx id="7j5m4t"
import { useState } from "react";

function Form() {
  const [name, setName] = useState("");

  return (
    <input
      type="text"
      value={name}
      onChange={(e) => setName(e.target.value)}
    />
  );
}
```

### **7. Uncontrolled Components**

An **Uncontrolled Component** is a form element where the data is handled by the **DOM itself** instead of React state. It uses **Refs** to access the input value.

**Key Features:**

* **DOM manages form data**
* **Uses `useRef()` or `ref`**
* **Less code for simple forms**
* **No state updates on every keystroke**

**How it works:**

* The input stores its own value.
* React accesses the value only when needed using a **ref**.

**When to use:**

* For **simple forms** or when integrating with **non-React libraries**.

**Code Example:**

```jsx id="5o8jkp"
import { useRef } from "react";

function Form() {
  const inputRef = useRef();

  const handleSubmit = () => {
    alert(inputRef.current.value);
  };

  return (
    <>
      <input type="text" ref={inputRef} />
      <button onClick={handleSubmit}>Submit</button>
    </>
  );
}
```

### **8. Reusable Components**

**Reusable Components** are components that can be used multiple times with different data by passing **props**. They reduce code duplication and improve maintainability.

**Key Features:**

* **Write once, use many times**
* **Accept dynamic data through props**
* **Easy to maintain and update**
* **Promotes clean code**

**How it works:**

* Create a generic component.
* Pass different values using **props** whenever the component is used.

**When to use:**

* When the same UI pattern is needed in multiple places.

**Code Example:**

```jsx id="f9x4lq"
function Button({ text }) {
  return <button>{text}</button>;
}

function App() {
  return (
    <>
      <Button text="Save" />
      <Button text="Cancel" />
    </>
  );
}
```

### **9. Component Composition**

**Component Composition** is the process of building complex UIs by combining **small, reusable components** instead of using inheritance.

**Key Features:**

* **Builds UI from smaller components**
* **Promotes code reusability**
* **Easy to extend and maintain**
* **Preferred over inheritance in React**

**How it works:**

* A parent component includes one or more child components.
* Components can also use the special **`children`** prop to render nested content.

**When to use:**

* When creating **modular**, **scalable**, and **maintainable** React applications.

**Code Example:**

```jsx id="w3z8nu"
function Card({ children }) {
  return <div className="card">{children}</div>;
}

function App() {
  return (
    <Card>
      <h2>Welcome</h2>
      <p>This is component composition.</p>
    </Card>
  );
}
```

**Example:**

* **App**

  * **Header**
  * **Sidebar**
  * **Content**
  * **Footer**

This approach makes the application **modular**, **reusable**, and **easy to manage**.


# ✅ **3. Props & State**

### **1. Props**

**Props (Properties)** are **read-only inputs** used to pass data from a **parent component** to a **child component**.

**Key Features:**

* **Read-only**
* **Passed from parent to child**
* **Makes components reusable**
* **Cannot be modified by the child**

**How it works:**

* The parent sends data using attributes.
* The child receives the data through the `props` object.

**When to use:**

* When you need to **share data between components**.

**Code Example:**

```jsx id="c1v9pa"
function Welcome(props) {
  return <h1>Hello, {props.name}</h1>;
}

function App() {
  return <Welcome name="John" />;
}
```

### **2. State**

**State** is a built-in React object used to store and manage **dynamic data** inside a component. When the state changes, React automatically re-renders the component.

**Key Features:**

* **Managed inside the component**
* **Mutable (can be updated)**
* **Triggers re-rendering**
* **Used for dynamic UI data**

**How it works:**

* State is created using `useState()`.
* Updating the state refreshes the UI automatically.

**When to use:**

* When data changes over time, such as **counters, forms, or user interactions**.

**Code Example:**

```jsx id="w6f8rm"
import { useState } from "react";

function Counter() {
  const [count, setCount] = useState(0);

  return (
    <button onClick={() => setCount(count + 1)}>
      {count}
    </button>
  );
}
```

### **3. Props vs State**

| Feature        | **Props**                        | **State**                          |
| -------------- | -------------------------------- | ---------------------------------- |
| **Definition** | Data passed from parent to child | Data managed inside the component  |
| **Mutability** | **Read-only**                    | **Can be updated**                 |
| **Ownership**  | Controlled by parent             | Controlled by the component itself |
| **Purpose**    | Share data                       | Manage dynamic data                |
| **Re-render**  | Changes when parent updates      | Changes when state updates         |

**When to use:**

* Use **Props** to **pass data** between components.
* Use **State** to **store and update local data**.

### **4. State Updates**

**State Updates** are the process of changing a component's state using the **state updater function** returned by `useState()`.

**Key Features:**

* **Triggers component re-render**
* **Uses updater function**
* **Can be asynchronous**
* **Supports functional updates**

**How it works:**

* Call the updater function to change the state.
* React updates the UI with the new value.

**When to use:**

* Whenever the UI needs to change based on **user actions** or **data updates**.

**Code Example:**

```jsx id="e5m2nt"
const [count, setCount] = useState(0);

setCount(count + 1);

// Functional update
setCount(prevCount => prevCount + 1);
```

### **5. State Lifting**

**State Lifting** means moving the **shared state** from child components to their **closest common parent** so multiple components can use the same data.

**Key Features:**

* **Single source of truth**
* **Shares data between sibling components**
* **Improves data consistency**
* **Uses props to pass data down**

**How it works:**

* Store the state in the parent component.
* Pass the state and update functions to child components through **props**.

**When to use:**

* When **multiple components need access to the same data**.

**Code Example:**

```jsx id="z8k3py"
function Parent() {
  const [message, setMessage] = useState("Hello");

  return <Child text={message} />;
}

function Child({ text }) {
  return <h1>{text}</h1>;
}
```

### **6. Prop Drilling**

**Prop Drilling** is the process of passing **props through multiple intermediate components** to reach a deeply nested child component.

**Key Features:**

* **Data flows through multiple levels**
* **Can increase code complexity**
* **May cause unnecessary prop passing**
* **Can be solved using Context API or state management libraries**

**How it works:**

* The parent passes props to a child.
* The child forwards the same props to another child until the target component receives them.

**When to use:**

* Suitable for **small component hierarchies**.
* Avoid in **deeply nested applications**.

**Code Example:**

```jsx id="u2h9xq"
function App() {
  return <Parent name="John" />;
}

function Parent({ name }) {
  return <Child name={name} />;
}

function Child({ name }) {
  return <h1>{name}</h1>;
}
```

### **7. Immutable State Updates**

**Immutable State Updates** mean creating a **new copy of the state** instead of modifying the existing state directly.

**Key Features:**

* **Do not modify original state**
* **Create a new object or array**
* **Helps React detect changes**
* **Improves performance and predictability**

**How it works:**

* Use the **spread operator (`...`)** or array methods like `map()` and `filter()` to create updated copies.

**When to use:**

* Always use immutable updates when changing **objects** or **arrays** in React state.

**Code Example:**

```jsx id="p4n7lb"
const [user, setUser] = useState({
  name: "John",
  age: 25
});

setUser({
  ...user,
  age: 26
});
```

**Incorrect (Mutable Update):**

```jsx id="n8r5dw"
user.age = 26;
setUser(user);
```

**Correct (Immutable Update):**

```jsx id="h3v1kc"
setUser({
  ...user,
  age: 26
});
```


# ✅ **4. React Hooks**

### **1. useState**

**`useState`** is a React Hook used to add and manage **state** in a **functional component**.

**Key Features:**

* **Manages local component state**
* **Triggers re-render on update**
* **Simple and easy to use**
* **Returns current state and updater function**

**How it works:**

* `useState(initialValue)` returns an array with the current state and a function to update it.
* Calling the updater function changes the state and re-renders the component.

**When to use:**

* For managing **dynamic data** like counters, form inputs, and toggle buttons.

**Code Example:**

```jsx id="j7q4ma"
import { useState } from "react";

function Counter() {
  const [count, setCount] = useState(0);

  return (
    <button onClick={() => setCount(count + 1)}>
      {count}
    </button>
  );
}
```

### **2. useEffect**

**`useEffect`** is a React Hook used to perform **side effects** in functional components, such as API calls, timers, or event listeners.

**Key Features:**

* **Handles side effects**
* **Runs after component rendering**
* **Supports cleanup functions**
* **Can run once or on dependency changes**

**How it works:**

* React executes the `useEffect()` function after rendering.
* The dependency array controls when the effect runs.

**When to use:**

* For **API requests**, **subscriptions**, **timers**, and **DOM updates**.

**Code Example:**

```jsx id="v8x2lf"
import { useEffect } from "react";

function App() {
  useEffect(() => {
    console.log("Component Mounted");
  }, []);

  return <h1>React App</h1>;
}
```

### **3. useContext**

**`useContext`** is a React Hook used to access data from the **Context API** without passing props manually through every component.

**Key Features:**

* **Avoids prop drilling**
* **Shares global data**
* **Simple access to context values**
* **Works with Context API**

**How it works:**

* Create a context using `createContext()`.
* Wrap components with a `Provider`.
* Access the shared data using `useContext()`.

**When to use:**

* For sharing data like **theme**, **language**, **logged-in user**, or **application settings**.

**Code Example:**

```jsx id="d5m9tk"
import { createContext, useContext } from "react";

const UserContext = createContext();

function Child() {
  const user = useContext(UserContext);
  return <h1>{user}</h1>;
}
```

### **4. useReducer**

**`useReducer`** is a React Hook used for **complex state management**. It works similarly to a Redux reducer by using **actions** and a **reducer function**.

**Key Features:**

* **Manages complex state logic**
* **Uses reducer and actions**
* **Predictable state updates**
* **Good alternative to `useState`**

**How it works:**

* A **reducer function** receives the current state and an action.
* `dispatch()` sends an action to the reducer, which returns the updated state.

**When to use:**

* When state has **multiple related values** or **complex update logic**.

**Code Example:**

```jsx id="r4p8wy"
import { useReducer } from "react";

function reducer(state, action) {
  switch (action.type) {
    case "increment":
      return { count: state.count + 1 };
    default:
      return state;
  }
}

function Counter() {
  const [state, dispatch] = useReducer(reducer, { count: 0 });

  return (
    <button onClick={() => dispatch({ type: "increment" })}>
      {state.count}
    </button>
  );
}
```

### **5. useRef**

**`useRef`** is a React Hook used to create a **mutable reference** that persists across renders without causing a re-render.

**Key Features:**

* **Accesses DOM elements directly**
* **Stores mutable values**
* **Value persists between renders**
* **Does not trigger re-render on update**

**How it works:**

* `useRef()` creates a reference object with a `.current` property.
* The reference can be attached to a DOM element or used to store values.

**When to use:**

* For **focusing input fields**, **storing previous values**, or **working with DOM elements**.

**Code Example:**

```jsx id="k2n6bx"
import { useRef } from "react";

function App() {
  const inputRef = useRef();

  const focusInput = () => {
    inputRef.current.focus();
  };

  return (
    <>
      <input ref={inputRef} />
      <button onClick={focusInput}>Focus</button>
    </>
  );
}
```
### **6. useMemo**

**`useMemo`** is a React Hook used to **memoize (cache) the result of an expensive calculation** so it is not recomputed on every render.

**Key Features:**

* **Improves performance**
* **Caches computed values**
* **Recalculates only when dependencies change**
* **Reduces unnecessary computations**

**How it works:**

* `useMemo()` stores the result of a function.
* React returns the cached value unless one of the dependencies changes.

**When to use:**

* For **expensive calculations**, **filtering**, **sorting**, or **large data processing**.

**Code Example:**

```jsx id="u8n3kp"
import { useMemo } from "react";

function App({ numbers }) {
  const total = useMemo(() => {
    return numbers.reduce((sum, num) => sum + num, 0);
  }, [numbers]);

  return <h1>{total}</h1>;
}
```

### **7. useCallback**

**`useCallback`** is a React Hook used to **memoize a function** so that it is not recreated on every render.

**Key Features:**

* **Caches function references**
* **Improves performance**
* **Prevents unnecessary child re-renders**
* **Works well with `React.memo`**

**How it works:**

* React returns the same function instance unless the dependency values change.

**When to use:**

* When passing functions to **child components** or optimizing performance.

**Code Example:**

```jsx id="q4v7my"
import { useCallback } from "react";

function App() {
  const handleClick = useCallback(() => {
    console.log("Button Clicked");
  }, []);

  return <button onClick={handleClick}>Click</button>;
}
```

### **8. useLayoutEffect**

**`useLayoutEffect`** is a React Hook that works like `useEffect`, but it runs **synchronously after the DOM update and before the browser paints the screen**.

**Key Features:**

* **Runs before browser repaint**
* **Can read and update the DOM**
* **Useful for layout measurements**
* **Blocks screen painting until completed**

**How it works:**

* React updates the DOM.
* `useLayoutEffect()` runs immediately before the browser displays the changes.

**When to use:**

* For **measuring DOM elements**, **animations**, or **avoiding UI flickering**.

**Code Example:**

```jsx id="t9k2rx"
import { useLayoutEffect, useRef } from "react";

function App() {
  const inputRef = useRef();

  useLayoutEffect(() => {
    inputRef.current.focus();
  }, []);

  return <input ref={inputRef} />;
}
```

### **9. useImperativeHandle**

**`useImperativeHandle`** is a React Hook used with **`forwardRef`** to customize the values or methods exposed to a parent component through a **ref**.

**Key Features:**

* **Works with `forwardRef`**
* **Exposes custom methods**
* **Provides controlled access to child components**
* **Encapsulates internal logic**

**How it works:**

* The child component defines which methods the parent can access using `useImperativeHandle()`.

**When to use:**

* When a parent needs to call functions like **focus**, **reset**, or **open** on a child component.

**Code Example:**

```jsx id="m6z8cf"
import { forwardRef, useImperativeHandle, useRef } from "react";

const Input = forwardRef((props, ref) => {
  const inputRef = useRef();

  useImperativeHandle(ref, () => ({
    focusInput() {
      inputRef.current.focus();
    }
  }));

  return <input ref={inputRef} />;
});
```

### **10. Custom Hooks**

A **Custom Hook** is a reusable JavaScript function that uses one or more **React Hooks** to share logic between components.

**Key Features:**

* **Reuses stateful logic**
* **Reduces code duplication**
* **Improves code organization**
* **Always starts with `use`**

**How it works:**

* Create a function whose name starts with `use`.
* Inside the function, use React Hooks and return the required values.

**When to use:**

* When the **same logic** is needed in **multiple components**.

**Code Example:**

```jsx id="h5p1jw"
import { useState } from "react";

function useCounter() {
  const [count, setCount] = useState(0);

  const increment = () => setCount(count + 1);

  return { count, increment };
}

function App() {
  const { count, increment } = useCounter();

  return (
    <button onClick={increment}>
      {count}
    </button>
  );
}
```


# ✅ **5. Event Handling**

### **1. Synthetic Events**

**Synthetic Events** are React's **cross-browser wrapper** around native browser events. They provide a consistent event handling API across all browsers.

**Key Features:**

* **Cross-browser compatibility**
* **Same API as native events**
* **Better performance**
* **Works with React event system**

**How it works:**

* React creates a `SyntheticEvent` object and passes it to the event handler instead of the native browser event.

**When to use:**

* Automatically used whenever you handle events in **React components**.

**Code Example:**

```jsx id="b7k2qp"
function App() {
  const handleClick = (event) => {
    console.log(event.type);
  };

  return <button onClick={handleClick}>Click</button>;
}
```

### **2. Event Binding**

**Event Binding** is the process of connecting an **event handler function** to a React element so it executes when the event occurs.

**Key Features:**

* **Handles user interactions**
* **Uses camelCase event names**
* **Passes function reference, not function call**
* **Works with functional and class components**

**How it works:**

* Use attributes like `onClick`, `onChange`, or `onSubmit` to bind an event handler.

**When to use:**

* For handling **button clicks**, **form inputs**, and other user actions.

**Code Example:**

```jsx id="f4x8mn"
function App() {
  const handleClick = () => {
    alert("Button Clicked");
  };

  return <button onClick={handleClick}>Click Me</button>;
}
```

### **3. Event Bubbling**

**Event Bubbling** is the process where an event starts from the **target element** and then moves **upward** through its parent elements.

**Key Features:**

* **Default event behavior**
* **Moves from child to parent**
* **Allows parent elements to handle child events**
* **Can be stopped using `stopPropagation()`**

**How it works:**

* If a child element is clicked, its event handler runs first, then the parent's event handler is triggered.

**When to use:**

* When you want to handle events at a **parent component** instead of multiple child components.

**Code Example:**

```jsx id="m9v3rt"
<div onClick={() => console.log("Parent")}>
  <button onClick={() => console.log("Child")}>
    Click
  </button>
</div>
```

**Output:**

```text id="a2w5jd"
Child
Parent
```

### **4. Event Capturing**

**Event Capturing** is the process where an event travels from the **outer parent element** down to the **target element** before bubbling starts.

**Key Features:**

* **Moves from parent to child**
* **Runs before bubbling**
* **Uses `onClickCapture` in React**
* **Useful for intercepting events early**

**How it works:**

* React triggers the capturing event handler before the normal bubbling event handler.

**When to use:**

* When you need to process an event **before it reaches the target element**.

**Code Example:**

```jsx id="n6y1pk"
<div onClickCapture={() => console.log("Parent Capture")}>
  <button onClick={() => console.log("Child Click")}>
    Click
  </button>
</div>
```

### **5. Prevent Default**

**`preventDefault()`** is a method used to stop the browser's **default behavior** for an event.

**Key Features:**

* **Prevents default browser action**
* **Commonly used with forms and links**
* **Keeps control inside React**

**How it works:**

* Call `event.preventDefault()` inside the event handler.

**When to use:**

* To stop **form submission**, **page refresh**, or **link navigation**.

**Code Example:**

```jsx id="q8c4zx"
function App() {
  const handleSubmit = (event) => {
    event.preventDefault();
    alert("Form Submitted");
  };

  return (
    <form onSubmit={handleSubmit}>
      <button type="submit">Submit</button>
    </form>
  );
}
```

### **6. Stop Propagation**

**`stopPropagation()`** is a method used to stop an event from **continuing to bubble or capture** through parent elements.

**Key Features:**

* **Stops event propagation**
* **Prevents parent event handlers from running**
* **Works with bubbling and capturing phases**

**How it works:**

* Call `event.stopPropagation()` inside the event handler.
* The event stops at the current element and does not move further.

**When to use:**

* When a child component's event should **not trigger** the parent component's event handler.

**Code Example:**

```jsx id="p3j7wh"
function App() {
  const parentClick = () => {
    console.log("Parent Clicked");
  };

  const childClick = (event) => {
    event.stopPropagation();
    console.log("Child Clicked");
  };

  return (
    <div onClick={parentClick}>
      <button onClick={childClick}>
        Click
      </button>
    </div>
  );
}
```

**Output:**

```text id="z5n8lv"
Child Clicked
```


# ✅ **6. Forms**

### **1. Controlled Forms**

A **Controlled Form** is a form where React **controls the input values** using **state**.

**Key Features:**

* **Form data stored in React state**
* **Single source of truth**
* **Easy validation and error handling**
* **Real-time UI updates**

**How it works:**

* The input value is linked to a state variable.
* `onChange` updates the state whenever the user types.

**When to use:**

* For forms that require **validation**, **dynamic updates**, or **custom logic**.

**Code Example:**

```jsx id="a7k3mx"
import { useState } from "react";

function Form() {
  const [name, setName] = useState("");

  return (
    <input
      value={name}
      onChange={(e) => setName(e.target.value)}
    />
  );
}
```

### **2. Uncontrolled Forms**

An **Uncontrolled Form** is a form where the **DOM manages the input values** instead of React state. It uses **Refs** to access the form data.

**Key Features:**

* **DOM controls form data**
* **Uses `useRef()`**
* **Less code for simple forms**
* **No state update on every keystroke**

**How it works:**

* A `ref` is attached to the input field.
* The input value is accessed through `ref.current.value`.

**When to use:**

* For **simple forms** or when integrating with **third-party libraries**.

**Code Example:**

```jsx id="v4p8jy"
import { useRef } from "react";

function Form() {
  const inputRef = useRef();

  const handleSubmit = () => {
    alert(inputRef.current.value);
  };

  return (
    <>
      <input ref={inputRef} />
      <button onClick={handleSubmit}>Submit</button>
    </>
  );
}
```

### **3. Form Validation**

**Form Validation** is the process of checking whether the user input meets the required rules before submitting the form.

**Key Features:**

* **Validates user input**
* **Displays error messages**
* **Prevents invalid form submission**
* **Improves data quality**

**How it works:**

* Validate fields during input or form submission.
* Show errors if validation rules fail.

**When to use:**

* In **registration**, **login**, **payment**, or any user input form.

**Code Example:**

```jsx id="q2n6wb"
function Form() {
  const handleSubmit = (e) => {
    e.preventDefault();

    if (!e.target.name.value) {
      alert("Name is required");
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <input name="name" />
      <button type="submit">Submit</button>
    </form>
  );
}
```

### **4. React Hook Form**

**React Hook Form** is a popular library for handling forms in React with **less code** and **better performance**.

**Key Features:**

* **Uses Hooks**
* **Minimal re-rendering**
* **Built-in validation**
* **Easy integration with UI libraries**

**How it works:**

* The `useForm()` Hook manages form state and validation.
* Inputs are registered using the `register()` function.

**When to use:**

* For **large forms** or applications requiring **high-performance form handling**.

**Code Example:**

```jsx id="m5r1xt"
import { useForm } from "react-hook-form";

function Form() {
  const { register, handleSubmit } = useForm();

  return (
    <form onSubmit={handleSubmit(data => console.log(data))}>
      <input {...register("name")} />
      <button type="submit">Submit</button>
    </form>
  );
}
```

### **5. Formik**

**Formik** is a React library that simplifies **form state management**, **validation**, and **submission handling**.

**Key Features:**

* **Manages form state**
* **Built-in validation support**
* **Reduces boilerplate code**
* **Works well with Yup validation**

**How it works:**

* Formik stores form values and handles changes automatically.
* Validation can be added using custom logic or **Yup**.

**When to use:**

* For **complex forms** with multiple fields and validations.

**Code Example:**

```jsx id="k9f4pu"
import { Formik, Form, Field } from "formik";

function App() {
  return (
    <Formik initialValues={{ name: "" }} onSubmit={console.log}>
      <Form>
        <Field name="name" />
        <button type="submit">Submit</button>
      </Form>
    </Formik>
  );
}
```

### **6. Dynamic Forms**

**Dynamic Forms** are forms where input fields are **created, removed, or updated dynamically** based on user actions or data.

**Key Features:**

* **Dynamic field generation**
* **Supports add/remove operations**
* **Flexible and reusable**
* **Ideal for variable-length data**

**How it works:**

* Store form fields in an array.
* Use `map()` to render the fields dynamically.
* Update the array when fields are added or removed.

**When to use:**

* For forms like **survey forms**, **employee details**, **multiple addresses**, or **dynamic questionnaires**.

**Code Example:**

```jsx id="x8t2zl"
import { useState } from "react";

function App() {
  const [fields, setFields] = useState([""]);

  return (
    <>
      {fields.map((field, index) => (
        <input key={index} />
      ))}

      <button
        onClick={() => setFields([...fields, ""])}
      >
        Add Field
      </button>
    </>
  );
}
```

# ✅ **7. Conditional Rendering**

### **1. if/else Rendering**

**if/else Rendering** is a way to display different UI elements based on a condition using the standard JavaScript `if...else` statement.

**Key Features:**

* **Simple and easy to understand**
* **Uses normal JavaScript syntax**
* **Suitable for complex conditions**
* **Returns different JSX based on logic**

**How it works:**

* Check a condition using `if...else`.
* Return the appropriate JSX block.

**When to use:**

* When the rendering logic is **complex** or involves **multiple conditions**.

**Code Example:**

```jsx id="a7m4kp"
function User({ isLoggedIn }) {
  if (isLoggedIn) {
    return <h1>Welcome User</h1>;
  } else {
    return <h1>Please Login</h1>;
  }
}
```

### **2. Ternary Operator**

The **Ternary Operator (`condition ? true : false`)** is a short way to perform conditional rendering in JSX.

**Key Features:**

* **Compact syntax**
* **Easy to read for simple conditions**
* **Works directly inside JSX**
* **Returns one of two values**

**How it works:**

* If the condition is `true`, the first expression is rendered.
* Otherwise, the second expression is rendered.

**When to use:**

* For **simple if/else conditions** inside JSX.

**Code Example:**

```jsx id="p5x9wr"
function User({ isLoggedIn }) {
  return (
    <h1>
      {isLoggedIn ? "Welcome User" : "Please Login"}
    </h1>
  );
}
```

### **3. Logical AND (`&&`)**

The **Logical AND (`&&`)** operator is used to render a component **only when a condition is true**.

**Key Features:**

* **Short and clean syntax**
* **Renders content only for `true` conditions**
* **No `else` block required**
* **Commonly used for optional UI elements**

**How it works:**

* If the condition is `true`, React renders the JSX after `&&`.
* If the condition is `false`, React renders nothing.

**When to use:**

* When you want to show or hide a component based on a single condition.

**Code Example:**

```jsx id="t2q6ny"
function Notification({ hasMessage }) {
  return (
    <div>
      {hasMessage && <h2>New Message</h2>}
    </div>
  );
}
```

### **4. Switch Rendering**

**Switch Rendering** uses the JavaScript `switch` statement to render different UI elements based on multiple conditions.

**Key Features:**

* **Handles multiple cases**
* **Cleaner than many `if...else` statements**
* **Easy to maintain**
* **Good for menu or status-based rendering**

**How it works:**

* The `switch` statement checks a value and returns the matching JSX for that case.

**When to use:**

* When there are **multiple possible rendering options**.

**Code Example:**

```jsx id="n8v3jl"
function Status({ role }) {
  switch (role) {
    case "admin":
      return <h1>Admin Panel</h1>;
    case "user":
      return <h1>User Dashboard</h1>;
    default:
      return <h1>Guest Page</h1>;
  }
}
```

### **5. Dynamic UI Rendering**

**Dynamic UI Rendering** means creating or updating UI elements dynamically based on **data**, **state**, or **props**.

**Key Features:**

* **UI changes automatically with data**
* **Uses `map()` for lists**
* **Supports reusable components**
* **Makes applications interactive**

**How it works:**

* Store data in an array or state.
* Use `map()` to generate UI elements dynamically.
* React updates the UI whenever the data changes.

**When to use:**

* For rendering **lists**, **tables**, **menus**, **cards**, or any data-driven UI.

**Code Example:**

```jsx id="k4r8bz"
function App() {
  const users = ["John", "Alice", "Bob"];

  return (
    <ul>
      {users.map((user, index) => (
        <li key={index}>{user}</li>
      ))}
    </ul>
  );
}
```


# ✅ **8. Lists & Keys**

### **1. Rendering Lists**

**Rendering Lists** in React means displaying multiple items by looping through an array and converting each item into a JSX element, usually using the **`map()`** method.

**Key Features:**

* **Uses `map()` to create UI elements**
* **Efficiently renders collections of data**
* **Supports dynamic data updates**
* **Requires a unique `key` for each item**

**How it works:**

* Store data in an array.
* Use `map()` to iterate through the array and return JSX for each item.

**When to use:**

* For displaying **lists, tables, menus, cards, or API data**.

**Code Example:**

```jsx id="f8m3wp"
function App() {
  const users = ["John", "Alice", "Bob"];

  return (
    <ul>
      {users.map((user) => (
        <li key={user}>{user}</li>
      ))}
    </ul>
  );
}
```

### **2. Keys in React**

A **Key** is a special attribute used to uniquely identify elements in a list. It helps React track which items have changed, been added, or removed.

**Key Features:**

* **Provides a unique identity**
* **Improves rendering performance**
* **Helps React update the correct element**
* **Required when rendering lists**

**How it works:**

* React uses the `key` value during the **reconciliation process** to compare old and new elements.

**When to use:**

* Whenever you render a list using **`map()`**.

**Code Example:**

```jsx id="q4k7zt"
const users = [
  { id: 1, name: "John" },
  { id: 2, name: "Alice" }
];

function App() {
  return (
    <ul>
      {users.map((user) => (
        <li key={user.id}>{user.name}</li>
      ))}
    </ul>
  );
}
```

### **3. Why Keys Are Important**

**Keys** are important because they help React identify which list items have changed, making updates **faster and more efficient**.

**Key Features:**

* **Improves UI update performance**
* **Prevents unnecessary re-rendering**
* **Maintains component state correctly**
* **Helps React's reconciliation algorithm**

**How it works:**

* React compares the old and new list using the `key` values.
* It updates only the changed items instead of re-rendering the entire list.

**When to use:**

* In any **dynamic list** where items can be **added, removed, or reordered**.

**Code Example:**

```jsx id="x2p9mn"
const products = [
  { id: 101, name: "Laptop" },
  { id: 102, name: "Mobile" }
];

products.map((product) => (
  <li key={product.id}>{product.name}</li>
));
```

### **4. Index as Key Problems**

Using the **array index as a key** can cause incorrect UI updates when list items are **added, deleted, or reordered**.

**Key Features:**

* **Can cause rendering issues**
* **May lose component state**
* **Leads to incorrect item updates**
* **Not recommended for dynamic lists**

**How it works:**

* When the list order changes, the index also changes.
* React may reuse the wrong component because the key is no longer associated with the correct item.

**When to use:**

* Use the **array index as a key only for static lists** where the order and data never change.

**Code Example (Not Recommended):**

```jsx id="j6r1yv"
const users = ["John", "Alice", "Bob"];

users.map((user, index) => (
  <li key={index}>{user}</li>
));
```

**Better Approach:**

```jsx id="n9w5kx"
const users = [
  { id: 1, name: "John" },
  { id: 2, name: "Alice" }
];

users.map((user) => (
  <li key={user.id}>{user.name}</li>
));
```

# ✅ **9. Component Communication**

### **1. Parent to Child**

**Parent to Child Communication** is the process of passing data from a parent component to a child component using **Props**.

**Key Features:**

* **Uses Props**
* **One-way data flow**
* **Simple and reusable**
* **Parent controls the data**

**How it works:**

* The parent sends data as attributes.
* The child receives the data through the `props` object.

**When to use:**

* When a child component needs to display or use data from its parent.

**Code Example:**

```jsx id="x4m7pq"
function Child(props) {
  return <h1>{props.message}</h1>;
}

function App() {
  return <Child message="Hello React" />;
}
```

### **2. Child to Parent**

**Child to Parent Communication** is achieved by passing a **callback function** from the parent to the child through props. The child calls this function to send data back.

**Key Features:**

* **Uses callback functions**
* **Allows child to update parent state**
* **Maintains one-way data flow**
* **Easy to implement**

**How it works:**

* The parent passes a function as a prop.
* The child invokes that function and sends data as an argument.

**When to use:**

* When a child component needs to notify or update its parent.

**Code Example:**

```jsx id="k8r2nv"
function Child({ sendData }) {
  return (
    <button onClick={() => sendData("Hello Parent")}>
      Send
    </button>
  );
}

function App() {
  const handleData = (msg) => {
    console.log(msg);
  };

  return <Child sendData={handleData} />;
}
```

### **3. Sibling Communication**

**Sibling Communication** is the process of sharing data between components that have the **same parent**.

**Key Features:**

* **Uses a common parent**
* **State is lifted to the parent**
* **Data flows through props**
* **Keeps components synchronized**

**How it works:**

* The shared state is stored in the parent component.
* The parent passes data and update functions to both sibling components.

**When to use:**

* When two or more sibling components need to share the same data.

**Code Example:**

```jsx id="m3t9wb"
function Parent() {
  const [message] = React.useState("Hello");

  return (
    <>
      <ChildA text={message} />
      <ChildB text={message} />
    </>
  );
}
```

### **4. Context API**

**Context API** is a React feature used to share data across multiple components **without passing props manually through every level**.

**Key Features:**

* **Avoids prop drilling**
* **Provides global data access**
* **Built into React**
* **Works with `useContext` Hook**

**How it works:**

* Create a context using `createContext()`.
* Wrap components with a `Provider`.
* Access the shared data using `useContext()`.

**When to use:**

* For sharing data like **theme**, **language**, **user information**, or **application settings**.

**Code Example:**

```jsx id="p7v5yx"
import { createContext, useContext } from "react";

const UserContext = createContext();

function Child() {
  const user = useContext(UserContext);
  return <h1>{user}</h1>;
}
```

### **5. Global State Sharing**

**Global State Sharing** is the process of storing and accessing common data across the entire application from a central location.

**Key Features:**

* **Centralized state management**
* **Accessible by multiple components**
* **Reduces prop drilling**
* **Keeps data consistent**

**How it works:**

* Store shared data in a global state solution like **Context API**, **Redux**, or **Zustand**.
* Components can read and update the shared state from anywhere in the application.

**When to use:**

* When many components need access to the **same data**, such as **user authentication**, **shopping cart**, or **application theme**.

**Code Example:**

```jsx id="d2n8ql"
import { createContext, useContext } from "react";

const ThemeContext = createContext("Light");

function App() {
  return (
    <ThemeContext.Provider value="Dark">
      <Home />
    </ThemeContext.Provider>
  );
}

function Home() {
  const theme = useContext(ThemeContext);
  return <h1>{theme} Theme</h1>;
}
```


# ✅ **10. React Lifecycle**

### **1. Mounting**

**Mounting** is the first phase of the React component lifecycle, where a component is **created and inserted into the DOM**.

**Key Features:**

* **Component is initialized**
* **State and props are set up**
* **UI is rendered for the first time**
* **`componentDidMount()` is called**

**How it works:**

* React creates the component and adds it to the browser DOM.

**When to use:**

* For **API calls**, **initial data loading**, or **setting up event listeners**.

**Code Example:**

```jsx id="m7q2kx"
class App extends React.Component {
  componentDidMount() {
    console.log("Component Mounted");
  }

  render() {
    return <h1>Hello React</h1>;
  }
}
```

### **2. Updating**

**Updating** is the lifecycle phase that occurs when a component's **state** or **props** change, causing the component to re-render.

**Key Features:**

* **Triggered by state or props changes**
* **Updates the UI automatically**
* **Calls `componentDidUpdate()`**
* **Uses React's reconciliation process**

**How it works:**

* React compares the new and previous values and updates only the changed parts of the UI.

**When to use:**

* Whenever the displayed data changes because of **user actions** or **API responses**.

**Code Example:**

```jsx id="v4n8py"
this.setState({
  count: this.state.count + 1
});
```

### **3. Unmounting**

**Unmounting** is the final phase of the lifecycle when a component is **removed from the DOM**.

**Key Features:**

* **Component is destroyed**
* **Performs cleanup tasks**
* **Removes event listeners and timers**
* **Calls `componentWillUnmount()`**

**How it works:**

* React removes the component and executes any cleanup logic before it disappears.

**When to use:**

* For cleaning up **timers**, **subscriptions**, or **event listeners**.

**Code Example:**

```jsx id="r9x5mt"
class App extends React.Component {
  componentWillUnmount() {
    console.log("Component Removed");
  }

  render() {
    return <h1>React</h1>;
  }
}
```

### **4. ComponentDidMount**

**`componentDidMount()`** is a lifecycle method that runs **once after the component is mounted**.

**Key Features:**

* **Runs only once**
* **Executes after first render**
* **Ideal for API calls**
* **Can initialize subscriptions or timers**

**How it works:**

* React automatically calls this method after the component is added to the DOM.

**When to use:**

* For **fetching data**, **starting timers**, or **adding event listeners**.

**Code Example:**

```jsx id="k3w7lb"
componentDidMount() {
  console.log("Fetch API Data");
}
```

### **5. ComponentDidUpdate**

**`componentDidUpdate()`** is a lifecycle method that runs **after a component updates** because of changes in **state** or **props**.

**Key Features:**

* **Runs after every update**
* **Triggered by state or props changes**
* **Can perform additional side effects**
* **Can compare previous and current values**

**How it works:**

* React calls this method after the UI has been updated.

**When to use:**

* For **updating data**, **making API calls based on changes**, or **syncing with external systems**.

**Code Example:**

```jsx id="t6p4qz"
componentDidUpdate(prevProps) {
  if (prevProps.id !== this.props.id) {
    console.log("Props Updated");
  }
}
```

### **6. ComponentWillUnmount**

**`componentWillUnmount()`** is a lifecycle method that runs **just before a component is removed from the DOM**.

**Key Features:**

* **Runs before component destruction**
* **Performs cleanup operations**
* **Removes timers and event listeners**
* **Prevents memory leaks**

**How it works:**

* React calls this method before deleting the component from the DOM.

**When to use:**

* For cleaning up **intervals**, **subscriptions**, or **external resources**.

**Code Example:**

```jsx id="y8m1rv"
componentWillUnmount() {
  clearInterval(this.timer);
}
```

### **7. Lifecycle Equivalent in Hooks**

In **Functional Components**, the **`useEffect()` Hook** replaces traditional class component lifecycle methods.

**Key Features:**

* **Replaces lifecycle methods**
* **Handles mounting, updating, and unmounting**
* **Supports cleanup functions**
* **Simplifies side effect management**

**How it works:**

* `useEffect(() => {}, [])` → Runs once after mounting.
* `useEffect(() => {}, [dependency])` → Runs on dependency updates.
* `return () => {}` inside `useEffect` → Runs during unmounting.

**When to use:**

* In all **modern React functional components** for handling lifecycle behavior.

**Code Example:**

```jsx id="c5n9jk"
import { useEffect } from "react";

function App() {
  useEffect(() => {
    console.log("Mounted");

    return () => {
      console.log("Unmounted");
    };
  }, []);

  return <h1>React Hooks</h1>;
}
```

**Lifecycle Mapping:**

| **Class Lifecycle Method**   | **Hooks Equivalent**                       |
| ---------------------------- | ------------------------------------------ |
| **`componentDidMount()`**    | **`useEffect(() => {}, [])`**              |
| **`componentDidUpdate()`**   | **`useEffect(() => {}, [dependency])`**    |
| **`componentWillUnmount()`** | **`return () => {}` inside `useEffect()`** |


# ✅ **11. Context API**

### **1. createContext**

**`createContext`** is a React API used to **create a Context object** for sharing data globally without **prop drilling**.

**Key Features:**

* Creates a **shared state container**.
* Avoids passing props through multiple components.
* Works with **Provider** and **useContext**.

**How it works:**

* Call `createContext()`.
* Export the created context.
* Wrap components with a **Provider**.

**When to use:**

* Sharing **theme**, **language**, **logged-in user**, or **global settings**.

**Code Example:**

```jsx
import { createContext } from "react";

export const UserContext = createContext();
```

---

### **2. Provider**

**`Provider`** is a component that **supplies data** to all child components using the Context.

**Key Features:**

* Stores and shares **global data**.
* Any nested component can access the value.
* Updates automatically when the value changes.

**How it works:**

* Wrap the component tree with `<Context.Provider>`.
* Pass data using the `value` prop.

**When to use:**

* When multiple components need the **same data**.

**Code Example:**

```jsx
import { UserContext } from "./UserContext";

function App() {
  return (
    <UserContext.Provider value="John">
      <Home />
    </UserContext.Provider>
  );
}
```

---

### **3. Consumer**

**`Consumer`** is a component used to **read Context data** before Hooks were introduced.

**Key Features:**

* Accesses data from the nearest **Provider**.
* Uses the **render props** pattern.
* Mostly replaced by **`useContext`** in functional components.

**How it works:**

* Wrap UI inside `<Context.Consumer>`.
* Get the value through a callback function.

**When to use:**

* Mainly in **class components** or legacy React code.

**Code Example:**

```jsx
<UserContext.Consumer>
  {(user) => <h1>{user}</h1>}
</UserContext.Consumer>
```

---

### **4. useContext**

**`useContext`** is a React Hook used to **access Context data directly** inside functional components.

**Key Features:**

* Simple and clean syntax.
* Eliminates the need for **Consumer**.
* Automatically gets the latest context value.

**How it works:**

* Pass the Context object to `useContext()`.
* Receive the current value from the nearest Provider.

**When to use:**

* In **functional components** that need shared/global data.

**Code Example:**

```jsx
import { useContext } from "react";
import { UserContext } from "./UserContext";

function Home() {
  const user = useContext(UserContext);
  return <h1>{user}</h1>;
}
```

---

### **5. Context vs Redux**

| Feature                | **Context API**               | **Redux**                           |
| ---------------------- | ----------------------------- | ----------------------------------- |
| **Purpose**            | Share simple global state     | Manage complex application state    |
| **Setup**              | Easy, built into React        | Requires additional library         |
| **Best For**           | Theme, auth, language         | Large apps with complex data flow   |
| **Performance**        | Can re-render many components | Better optimized with selectors     |
| **Middleware Support** | No                            | Yes (e.g., Redux Thunk, Redux Saga) |

**When to use Context:**

* Small to medium applications.
* Simple shared state like **theme** or **user information**.

**When to use Redux:**

* Large applications with **complex state management**, **multiple updates**, and **predictable data flow**.


# ✅ **12. State Management**

### **1. Local State**

**Local State** is data managed inside a single component using **`useState`** or **`useReducer`**.

**Key Features:**

* State belongs to one component.
* Simple and easy to manage.
* Does not need external libraries.

**How it works:**

* Store state with `useState()`.
* Update state using the setter function.
* Component re-renders when state changes.

**When to use:**

* Forms, toggles, counters, modal visibility, and component-specific data.

**Code Example:**

```jsx
import { useState } from "react";

function Counter() {
  const [count, setCount] = useState(0);

  return (
    <button onClick={() => setCount(count + 1)}>
      {count}
    </button>
  );
}
```

### **2. Context API**

**Context API** is React's built-in solution for sharing **global data** without passing props manually.

**Key Features:**

* Avoids **prop drilling**.
* Built into React.
* Works well with **`useContext`**.

**How it works:**

* Create a context using `createContext()`.
* Wrap components with a **Provider**.
* Access data using **`useContext`**.

**When to use:**

* Theme, authentication, language settings, or shared user data.

**Code Example:**

```jsx
const UserContext = createContext();

function App() {
  return (
    <UserContext.Provider value="John">
      <Home />
    </UserContext.Provider>
  );
}
```

### **3. Redux**

**Redux** is a predictable **state management library** for handling complex application state.

**Key Features:**

* Uses a **single global store**.
* State is updated through **actions** and **reducers**.
* Provides predictable data flow.

**How it works:**

* Dispatch an action.
* Reducer updates the store.
* Components receive updated state.

**When to use:**

* Large applications with complex shared state.

**Code Example:**

```jsx
dispatch({ type: "increment" });
const count = useSelector((state) => state.count);
```

### **4. Redux Toolkit (RTK)**

**Redux Toolkit (RTK)** is the official and recommended way to write Redux code with less boilerplate.

**Key Features:**

* Simplified Redux setup.
* Includes **`createSlice`** and **`configureStore`**.
* Supports immutable updates automatically.

**How it works:**

* Create a slice with state and reducers.
* Configure the store.
* Use `useSelector` and `useDispatch`.

**When to use:**

* Modern React applications using Redux.

**Code Example:**

```jsx
const counterSlice = createSlice({
  name: "counter",
  initialState: { value: 0 },
  reducers: {
    increment: (state) => {
      state.value++;
    },
  },
});
```

### **5. Zustand**

**Zustand** is a lightweight state management library with a simple Hook-based API.

**Key Features:**

* Minimal setup.
* No Provider required.
* Fast and easy to use.

**How it works:**

* Create a store using `create()`.
* Access and update state with custom hooks.

**When to use:**

* Small to medium React applications needing global state.

**Code Example:**

```jsx
import { create } from "zustand";

const useStore = create((set) => ({
  count: 0,
  increment: () => set((state) => ({ count: state.count + 1 })),
}));
```

### **6. Recoil**

**Recoil** is a state management library developed for React that manages state using **atoms** and **selectors**.

**Key Features:**

* Fine-grained state updates.
* Easy sharing of state between components.
* Built specifically for React.

**How it works:**

* Create an **atom** for state.
* Access it using `useRecoilState()`.
* Update only affected components.

**When to use:**

* Applications requiring flexible and scalable shared state.

**Code Example:**

```jsx
const countState = atom({
  key: "countState",
  default: 0,
});

const [count, setCount] = useRecoilState(countState);
```

### **7. MobX**

**MobX** is a reactive state management library that automatically updates the UI when state changes.

**Key Features:**

* Simple and less boilerplate.
* Automatic state tracking.
* Uses **observable** state and **actions**.

**How it works:**

* Make state observable.
* Update state through actions.
* UI reacts automatically to changes.

**When to use:**

* Applications needing simple and reactive state management.

**Code Example:**

```jsx
import { makeAutoObservable } from "mobx";

class CounterStore {
  count = 0;

  constructor() {
    makeAutoObservable(this);
  }

  increment() {
    this.count++;
  }
}
```


# ✅ **13. Redux & Redux Toolkit**

### **1. Store**

**Store** is the **central place** where the entire Redux application state is stored.

**Key Features:**

* Holds the **global state**.
* Only **one store** is usually used in an application.
* State can only be changed through **actions** and **reducers**.

**How it works:**

* Create the store using `configureStore()` (Redux Toolkit).
* Components read data from the store using `useSelector()`.

**When to use:**

* When multiple components need to share and manage common data.

**Code Example:**

```jsx id="6gtjlwm"
import { configureStore } from "@reduxjs/toolkit";
import counterReducer from "./counterSlice";

export const store = configureStore({
  reducer: {
    counter: counterReducer,
  },
});
```

### **2. Actions**

**Actions** are plain JavaScript objects that **describe what happened** in the application.

**Key Features:**

* Contain a **`type`** property.
* Can also carry additional data called **payload**.
* Tell Redux what state update is needed.

**How it works:**

* Create an action.
* Send it to the store using **dispatch**.
* The reducer processes the action.

**When to use:**

* Whenever you want to update the Redux state.

**Code Example:**

```jsx id="7t4dlw"
const increment = {
  type: "counter/increment",
};
```

Or with Redux Toolkit:

```jsx id="k4sqcp"
dispatch(increment());
```

### **3. Reducers**

**Reducers** are pure functions that **update the state** based on the received action.

**Key Features:**

* Take **current state** and **action** as input.
* Return a **new updated state**.
* Should not perform side effects like API calls.

**How it works:**

* Redux sends the current state and action to the reducer.
* Reducer decides how to update the state.

**When to use:**

* To define the logic for state changes.

**Code Example:**

```jsx id="vb7a7v"
const counterSlice = createSlice({
  name: "counter",
  initialState: { value: 0 },
  reducers: {
    increment: (state) => {
      state.value++;
    },
  },
});
```

### **4. Dispatch**

**`dispatch`** is a Redux function used to **send an action** to the store.

**Key Features:**

* Triggers state updates.
* Connects components with Redux.
* Can dispatch synchronous or asynchronous actions.

**How it works:**

* Call `dispatch(action)`.
* Redux forwards the action to the reducer.
* The store updates and components re-render.

**When to use:**

* Whenever a user action or event needs to modify the state.

**Code Example:**

```jsx id="y8d9tw"
import { useDispatch } from "react-redux";

const dispatch = useDispatch();

dispatch(increment());
```

### **5. Middleware**

**Middleware** is a function that runs **between dispatching an action and updating the reducer**.

**Key Features:**

* Handles **asynchronous operations**.
* Used for **API calls**, logging, and error handling.
* Extends Redux functionality without changing reducers.

**How it works:**

* Intercepts dispatched actions.
* Performs extra logic if needed.
* Passes the action to the reducer.

**When to use:**

* For API requests, authentication, logging, or side effects.

**Code Example:**

```jsx id="9u3wre"
const logger = (store) => (next) => (action) => {
  console.log(action);
  return next(action);
};
```

### **6. Thunk**

**Redux Thunk** is a **middleware** that allows you to write **asynchronous logic** in Redux.

**Key Features:**

* Handles **API calls** and async operations.
* Lets actions return a **function** instead of an object.
* Included by default in **Redux Toolkit**.

**How it works:**

* Dispatch a thunk function.
* Perform async tasks (e.g., fetch data).
* Dispatch normal actions after completion.

**When to use:**

* API requests, delayed actions, or asynchronous business logic.

**Code Example:**

```jsx id="2zq7nk"
export const fetchUsers = () => async (dispatch) => {
  const res = await fetch("/api/users");
  const data = await res.json();
  dispatch(setUsers(data));
};
```

### **7. Redux Saga**

**Redux Saga** is a Redux **middleware** that manages complex **asynchronous side effects** using generator functions.

**Key Features:**

* Uses **generator functions (`function*`)**.
* Handles complex async workflows.
* Supports task cancellation, retries, and parallel execution.

**How it works:**

* Watches for dispatched actions.
* Executes saga functions.
* Dispatches new actions based on the result.

**When to use:**

* Large applications with complex asynchronous flows.

**Code Example:**

```jsx id="7cwk3d"
function* fetchUsers() {
  const users = yield call(api.getUsers);
  yield put({ type: "SET_USERS", payload: users });
}
```

### **8. RTK Query**

**RTK Query** is a powerful data fetching and caching tool built into **Redux Toolkit**.

**Key Features:**

* Automatic **API fetching and caching**.
* Reduces boilerplate code.
* Handles loading and error states automatically.

**How it works:**

* Define an API using `createApi`.
* Generate Hooks automatically.
* Use generated Hooks inside components.

**When to use:**

* Fetching and caching server data in Redux applications.

**Code Example:**

```jsx id="cslzvo"
const { data, isLoading } = useGetUsersQuery();
```

### **9. createSlice**

**`createSlice`** is a Redux Toolkit function used to create **state, reducers, and actions** in one place.

**Key Features:**

* Reduces Redux boilerplate.
* Automatically creates action creators.
* Supports immutable updates internally.

**How it works:**

* Define `name`, `initialState`, and `reducers`.
* Redux Toolkit generates actions and reducer automatically.

**When to use:**

* In almost every modern Redux Toolkit application.

**Code Example:**

```jsx id="llk0ck"
const counterSlice = createSlice({
  name: "counter",
  initialState: { value: 0 },
  reducers: {
    increment: (state) => {
      state.value++;
    },
  },
});

export const { increment } = counterSlice.actions;
export default counterSlice.reducer;
```

### **10. configureStore**

**`configureStore`** is a Redux Toolkit function used to **create and configure the Redux store**.

**Key Features:**

* Simplifies store setup.
* Automatically adds **Thunk middleware**.
* Enables Redux DevTools by default.

**How it works:**

* Import reducers.
* Pass them to `configureStore`.
* Export the configured store.

**When to use:**

* To create the Redux store in any Redux Toolkit application.

**Code Example:**

```jsx id="v7s95m"
import { configureStore } from "@reduxjs/toolkit";
import counterReducer from "./counterSlice";

export const store = configureStore({
  reducer: {
    counter: counterReducer,
  },
});
```

# ✅ **14. Routing**

### **1. React Router**

**React Router** is a **routing library** for React that allows users to navigate between different pages **without reloading the browser**.

**Key Features:**

* **Client-side routing**
* Supports **dynamic routes**
* Enables **nested routing**
* Works with **URL parameters** and **navigation history**

**How it works:**

* Maps a **URL path** to a React **component**.
* When the URL changes, React Router renders the corresponding component.

**When to use:**

* When building a **Single Page Application (SPA)** with multiple pages like Home, About, Dashboard, etc.

**Example:**

```jsx
import { BrowserRouter, Routes, Route } from "react-router-dom";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<h1>Home</h1>} />
        <Route path="/about" element={<h1>About</h1>} />
      </Routes>
    </BrowserRouter>
  );
}
```

### **2. BrowserRouter**

**BrowserRouter** is a component that enables **routing using the browser's History API**. It wraps the entire application and manages URL changes.

**Key Features:**

* Uses the **HTML5 History API**
* Keeps the UI in sync with the URL
* Removes the need for page refreshes

**How it works:**

* Monitors the browser URL.
* Renders the matching route whenever the URL changes.

**When to use:**

* Use it as the **root router component** in most React web applications.

**Example:**

```jsx
import { BrowserRouter } from "react-router-dom";

function App() {
  return (
    <BrowserRouter>
      <h1>My React App</h1>
    </BrowserRouter>
  );
}
```

### **3. Routes**

**Routes** is a container component that holds multiple **Route** components and renders the **first matching route**.

**Key Features:**

* Groups all route definitions
* Matches the current URL
* Renders only the best matching route

**How it works:**

* Checks all child `<Route>` components.
* Displays the component whose path matches the current URL.

**When to use:**

* Whenever your application contains **multiple routes**.

**Example:**

```jsx
<Routes>
  <Route path="/" element={<Home />} />
  <Route path="/contact" element={<Contact />} />
</Routes>
```

### **4. Route**

**Route** defines a mapping between a **URL path** and the **component** that should be displayed.

**Key Features:**

* Connects a URL to a React component
* Supports **dynamic parameters** (`:id`)
* Can be nested inside other routes

**How it works:**

* If the current URL matches the `path`, the component provided in the `element` prop is rendered.

**When to use:**

* Use it to create individual pages or views in your application.

**Example:**

```jsx
<Route path="/profile" element={<Profile />} />

<Route path="/user/:id" element={<User />} />
```

### **5. Navigate**

**Navigate** is a component used to **redirect users programmatically** from one route to another.

**Key Features:**

* Performs automatic page redirection
* Replaces or pushes a new entry in browser history
* Commonly used for **authentication** and **protected routes**

**How it works:**

* When rendered, it changes the current URL to the specified destination.

**When to use:**

* Redirect users after **login**, **logout**, or when access is denied.

**Example:**

```jsx
import { Navigate } from "react-router-dom";

function Dashboard({ isLoggedIn }) {
  return isLoggedIn ? (
    <h1>Dashboard</h1>
  ) : (
    <Navigate to="/login" replace />
  );
}
```

### **6. Nested Routes**

**Nested Routes** allow you to define a **child route inside a parent route**, making it easy to build layouts with shared components.

**Key Features:**

* Supports **parent-child routing**
* Reuses common layouts (Navbar, Sidebar, etc.)
* Improves code organization

**How it works:**

* A parent `<Route>` contains child `<Route>` components.
* The parent component uses `<Outlet />` to display the matched child route.

**When to use:**

* When multiple pages share the same layout, such as a **Dashboard** with Profile, Settings, and Orders pages.

**Example:**

```jsx
import { Routes, Route, Outlet } from "react-router-dom";

function Dashboard() {
  return (
    <>
      <h1>Dashboard</h1>
      <Outlet />
    </>
  );
}

<Routes>
  <Route path="/dashboard" element={<Dashboard />}>
    <Route path="profile" element={<h2>Profile</h2>} />
    <Route path="settings" element={<h2>Settings</h2>} />
  </Route>
</Routes>
```

### **7. Dynamic Routes**

**Dynamic Routes** are routes that contain **variable values** in the URL, allowing one route to handle multiple similar pages.

**Key Features:**

* Uses **dynamic segments** (`:id`, `:name`)
* Reduces duplicate route definitions
* Useful for data-driven pages

**How it works:**

* A placeholder is defined in the route path.
* The actual value is extracted at runtime.

**When to use:**

* For pages like **User Details**, **Product Details**, or **Blog Posts**.

**Example:**

```jsx
<Routes>
  <Route path="/user/:id" element={<User />} />
</Routes>
```

For `/user/101`, the value of `id` will be `101`.

### **8. Route Parameters**

**Route Parameters** are **dynamic values** passed through the URL and accessed inside a component using the `useParams()` hook.

**Key Features:**

* Reads values directly from the URL
* Supports multiple parameters
* Works with dynamic routes

**How it works:**

* Define a parameter using `:parameterName`.
* Access it using `useParams()`.

**When to use:**

* When you need to load data based on a URL value, such as a specific user or product.

**Example:**

```jsx
import { useParams } from "react-router-dom";

function User() {
  const { id } = useParams();
  return <h1>User ID: {id}</h1>;
}
```

If the URL is `/user/101`, `useParams()` returns `{ id: "101" }`.

### **9. Protected Routes**

**Protected Routes** restrict access to certain pages and allow only **authenticated or authorized users** to view them.

**Key Features:**

* Provides **authentication-based access control**
* Redirects unauthorized users
* Commonly used with **login systems**

**How it works:**

* Check if the user is authenticated.
* If authenticated, render the requested page; otherwise, redirect to the login page using `<Navigate />`.

**When to use:**

* To protect pages like **Dashboard**, **Profile**, **Admin Panel**, or **Settings**.

**Example:**

```jsx
import { Navigate } from "react-router-dom";

function ProtectedRoute({ isLoggedIn, children }) {
  return isLoggedIn ? children : <Navigate to="/login" replace />;
}

// Usage
<Route
  path="/dashboard"
  element={
    <ProtectedRoute isLoggedIn={true}>
      <Dashboard />
    </ProtectedRoute>
  }
/>
```


# ✅ **15. API Integration**

### **1. Fetch API**

**Fetch API** is a built-in JavaScript API used to make **HTTP requests** to a server and retrieve or send data.

**Key Features:**

* Built into modern browsers
* Supports **Promises** and `async/await`
* Used for **GET, POST, PUT, DELETE** requests
* Lightweight and easy to use

**How it works:**

* Sends a request to a server.
* Returns a **Promise** that resolves with the response object.
* The response is usually converted to JSON using `.json()`.

**When to use:**

* When you need to fetch or send data to a backend API without using any external library.

**Example:**

```javascript
async function getUsers() {
  const response = await fetch("https://api.example.com/users");
  const data = await response.json();
  console.log(data);
}
```

### **2. Axios**

**Axios** is a popular **HTTP client library** used to make API requests in React and JavaScript applications.

**Key Features:**

* Supports **Promises** and `async/await`
* Automatically converts JSON data
* Better **error handling**
* Supports **request/response interceptors**
* Allows setting default headers and base URLs

**How it works:**

* Sends an HTTP request and returns the response data directly.
* Automatically parses JSON responses.

**When to use:**

* When you need advanced features like interceptors, request cancellation, or easier API management.

**Example:**

```javascript
import axios from "axios";

async function getUsers() {
  const response = await axios.get("https://api.example.com/users");
  console.log(response.data);
}
```

### **3. REST APIs**

**REST API (Representational State Transfer API)** is a web service architecture that allows applications to communicate using **HTTP methods**.

**Key Features:**

* Uses standard **HTTP methods**
* Stateless communication
* Exchanges data mainly in **JSON** format
* Easy to integrate with frontend and backend applications

**How it works:**

* The client sends an HTTP request to a server endpoint.
* The server processes the request and returns a response.

**Common HTTP Methods:**

* **GET** – Retrieve data
* **POST** – Create new data
* **PUT** – Update existing data
* **DELETE** – Remove data

**When to use:**

* When a frontend application needs to communicate with a backend server or database.

**Example:**

```javascript
// GET all users
fetch("https://api.example.com/users");

// POST a new user
fetch("https://api.example.com/users", {
  method: "POST",
  headers: {
    "Content-Type": "application/json"
  },
  body: JSON.stringify({ name: "John" })
});
```

### **4. Error Handling**

**Error Handling** is the process of detecting and managing errors that occur during API requests to prevent application crashes.

**Key Features:**

* Handles network and server errors
* Improves application reliability
* Displays meaningful messages to users
* Uses `try...catch` with `async/await`

**How it works:**

* Wrap the API call inside a `try` block.
* If an error occurs, control moves to the `catch` block where the error can be handled.

**When to use:**

* Whenever making API calls or performing operations that may fail.

**Example:**

```javascript
async function getUsers() {
  try {
    const response = await fetch("https://api.example.com/users");
    const data = await response.json();
    console.log(data);
  } catch (error) {
    console.log("Error:", error.message);
  }
}
```

### **5. Loading States**

**Loading State** is a UI state that informs users that data is being fetched or processed in the background.

**Key Features:**

* Improves **user experience**
* Displays a **loading spinner** or message
* Prevents showing incomplete data
* Controlled using React state

**How it works:**

* Set a loading variable (e.g., `loading = true`) before making an API call.
* Once data is received, update it to `false` and display the data.

**When to use:**

* Whenever fetching data from an API or performing a long-running operation.

**Example:**

```jsx id="kl6r4w"
const [loading, setLoading] = useState(true);
const [users, setUsers] = useState([]);

useEffect(() => {
  fetch("https://api.example.com/users")
    .then(res => res.json())
    .then(data => {
      setUsers(data);
      setLoading(false);
    });
}, []);

return loading ? <p>Loading...</p> : <UserList users={users} />;
```

### **6. API Caching**

**API Caching** is the process of storing previously fetched API data so it can be reused without making repeated network requests.

**Key Features:**

* Reduces **API calls**
* Improves **application performance**
* Speeds up data loading
* Reduces server load

**How it works:**

* Data is stored in memory or local storage after the first request.
* Future requests use the cached data until it expires or is refreshed.

**When to use:**

* For frequently accessed data that does not change often, such as user profiles or product lists.

**Example:**

```javascript id="8u2m3q"
const cachedData = localStorage.getItem("users");

if (cachedData) {
  console.log(JSON.parse(cachedData));
} else {
  fetch("https://api.example.com/users")
    .then(res => res.json())
    .then(data => {
      localStorage.setItem("users", JSON.stringify(data));
    });
}
```

### **7. Pagination**

**Pagination** is a technique used to display a large amount of data by dividing it into **smaller pages**.

**Key Features:**

* Loads data page by page
* Improves performance
* Reduces initial loading time
* Makes large datasets easier to navigate

**How it works:**

* The client requests a specific page from the server using parameters like `page` and `limit`.
* The server returns only the required records.

**When to use:**

* When displaying large lists such as users, products, or blog posts.

**Example:**

```javascript id="e29d5p"
const page = 1;
const limit = 10;

fetch(
  `https://api.example.com/users?page=${page}&limit=${limit}`
)
  .then(res => res.json())
  .then(data => console.log(data));
```

### **8. Infinite Scroll**

**Infinite Scroll** is a UI pattern where new data is automatically loaded as the user scrolls down the page.

**Key Features:**

* Loads content continuously
* Provides a smooth user experience
* Reduces the need for page navigation
* Often combined with API pagination

**How it works:**

* Detect when the user reaches the bottom of the page.
* Trigger an API call to fetch the next set of data and append it to the existing list.

**When to use:**

* For social media feeds, news websites, or product catalogs where users browse large amounts of content.

**Example:**

```jsx id="f1s7kt"
const loadMore = async () => {
  const res = await fetch(
    `https://api.example.com/users?page=${page}`
  );
  const data = await res.json();
  setUsers(prev => [...prev, ...data]);
  setPage(page + 1);
};

window.onscroll = () => {
  if (
    window.innerHeight + window.scrollY >=
    document.body.offsetHeight
  ) {
    loadMore();
  }
};
```


# ✅ **16. React Query / TanStack Query**

### **1. useQuery**

**`useQuery`** is a React Query hook used to **fetch, cache, and manage server data** automatically.

**Key Features:**

* Automatic **data fetching**
* Built-in **caching**
* Handles **loading** and **error** states
* Supports automatic **refetching**

**How it works:**

* Takes a **query key** and a **fetch function**.
* Fetches data and stores it in the cache for reuse.

**When to use:**

* When you need to **read or fetch data** from an API.

**Example:**

```jsx id="b3m8qw"
import { useQuery } from "@tanstack/react-query";
import axios from "axios";

function Users() {
  const { data, isLoading, error } = useQuery({
    queryKey: ["users"],
    queryFn: () =>
      axios.get("/api/users").then(res => res.data),
  });

  if (isLoading) return <p>Loading...</p>;
  if (error) return <p>Error!</p>;

  return <div>{JSON.stringify(data)}</div>;
}
```

### **2. useMutation**

**`useMutation`** is a React Query hook used to **create, update, or delete data** on the server.

**Key Features:**

* Handles **POST, PUT, PATCH, DELETE** requests
* Tracks **loading** and **error** states
* Can trigger **refetching** after success
* Supports **optimistic updates**

**How it works:**

* Executes a mutation function when `mutate()` is called.
* Updates the server and optionally refreshes related queries.

**When to use:**

* When sending data to the server, such as adding, editing, or deleting records.

**Example:**

```jsx id="p9x4zk"
import { useMutation } from "@tanstack/react-query";
import axios from "axios";

const mutation = useMutation({
  mutationFn: newUser =>
    axios.post("/api/users", newUser),
});

mutation.mutate({ name: "John" });
```

### **3. Query Caching**

**Query Caching** is the process of **storing fetched API data** in memory so it can be reused without making unnecessary requests.

**Key Features:**

* Reduces duplicate API calls
* Improves application performance
* Provides faster page loading
* Automatically manages cache updates

**How it works:**

* When `useQuery` fetches data, React Query stores it in a cache using the **query key**.
* Future requests with the same key use cached data until it becomes stale.

**When to use:**

* For frequently accessed server data like users, products, or settings.

**Example:**

```jsx id="k7t2nm"
useQuery({
  queryKey: ["users"],
  queryFn: fetchUsers,
});
```

If another component uses the same `queryKey`, React Query returns the **cached data** instead of making a new request.

### **4. Refetching**

**Refetching** is the process of **fetching fresh data again** to keep the UI synchronized with the server.

**Key Features:**

* Updates stale data automatically
* Supports manual and automatic refetching
* Keeps the UI up to date
* Works seamlessly with query caching

**How it works:**

* React Query can refetch data on page reload, window focus, or after a successful mutation.
* You can also call the `refetch()` function manually.

**When to use:**

* When data changes frequently or after performing create, update, or delete operations.

**Example:**

```jsx id="z5v8ld"
const { data, refetch } = useQuery({
  queryKey: ["users"],
  queryFn: fetchUsers,
});

<button onClick={() => refetch()}>
  Refresh Data
</button>;
```

### **5. Optimistic Updates**

**Optimistic Updates** update the UI **before the server confirms the request**, making the application feel faster.

**Key Features:**

* Instant UI updates
* Better user experience
* Automatically rolls back changes if the request fails
* Commonly used with `useMutation`

**How it works:**

* Temporarily update the cached data.
* Send the API request in the background.
* If the request fails, restore the previous data.

**When to use:**

* For actions like adding a comment, liking a post, or deleting an item where immediate feedback is important.

**Example:**

```jsx id="q2f7my"
const mutation = useMutation({
  mutationFn: addTodo,
  onSuccess: () => {
    queryClient.invalidateQueries(["todos"]);
  },
});

mutation.mutate({ title: "New Task" });
```

### **6. Server State Management**

**Server State Management** is the process of **fetching, caching, synchronizing, and updating data that comes from a server**.

**Key Features:**

* Manages API data automatically
* Built-in caching and refetching
* Handles loading and error states
* Keeps server data synchronized across components

**How it works:**

* Libraries like **React Query** store and manage server data separately from local UI state.
* Components using the same query automatically share updated data.

**When to use:**

* When your application depends heavily on backend API data, such as dashboards, e-commerce sites, or admin panels.

**Example:**

```jsx id="n8r6cv"
const { data, isLoading } = useQuery({
  queryKey: ["products"],
  queryFn: fetchProducts,
});
```

# ✅ **17. Performance Optimization**

### **1. React.memo**

**`React.memo`** is a **Higher-Order Component (HOC)** that prevents a functional component from re-rendering if its **props have not changed**.

**Key Features:**

* Optimizes component rendering
* Compares previous and new props
* Prevents unnecessary re-renders
* Improves application performance

**How it works:**

* Wraps a component and performs a **shallow comparison** of its props.
* If the props are the same, React reuses the previous rendered output.

**When to use:**

* For components that render frequently but receive the same props, such as lists or UI elements.

**Example:**

```jsx id="s8k4nv"
import React from "react";

const Child = React.memo(({ name }) => {
  console.log("Rendered");
  return <h1>{name}</h1>;
});

export default Child;
```

### **2. useMemo**

**`useMemo`** is a React hook that **memoizes a calculated value** and recalculates it only when its dependencies change.

**Key Features:**

* Caches expensive calculations
* Avoids unnecessary recomputation
* Improves rendering performance
* Works with dependency arrays

**How it works:**

* Stores the computed value in memory.
* Reuses the cached value until a dependency changes.

**When to use:**

* For expensive operations like filtering, sorting, or complex calculations.

**Example:**

```jsx id="m2w7pq"
import { useMemo } from "react";

const total = useMemo(() => {
  return items.reduce((sum, item) => sum + item.price, 0);
}, [items]);
```

### **3. useCallback**

**`useCallback`** is a React hook that **memoizes a function** and returns the same function instance unless its dependencies change.

**Key Features:**

* Prevents unnecessary function recreation
* Helps optimize child component rendering
* Often used with `React.memo`
* Uses a dependency array

**How it works:**

* Caches a function reference.
* Creates a new function only when a dependency changes.

**When to use:**

* When passing callback functions to memoized child components or event handlers.

**Example:**

```jsx id="g5x1tr"
import { useCallback } from "react";

const handleClick = useCallback(() => {
  console.log("Button clicked");
}, []);
```

### **4. Lazy Loading**

**Lazy Loading** is a technique where components or resources are **loaded only when they are needed**, reducing the initial bundle size.

**Key Features:**

* Faster initial page load
* Reduces JavaScript bundle size
* Loads components on demand
* Uses `React.lazy()` and `Suspense`

**How it works:**

* React dynamically imports a component.
* `Suspense` displays a fallback UI until the component is loaded.

**When to use:**

* For large applications with multiple pages or heavy components.

**Example:**

```jsx id="j9v6ld"
import React, { Suspense } from "react";

const Dashboard = React.lazy(() => import("./Dashboard"));

function App() {
  return (
    <Suspense fallback={<p>Loading...</p>}>
      <Dashboard />
    </Suspense>
  );
}
```

### **5. Code Splitting**

**Code Splitting** is a performance optimization technique that divides the application into **smaller JavaScript bundles**, loading them only when required.

**Key Features:**

* Reduces initial bundle size
* Improves application loading speed
* Loads code on demand
* Works with `React.lazy()` and dynamic imports

**How it works:**

* Instead of downloading the entire application at once, React loads only the code needed for the current page.
* Additional bundles are downloaded when users navigate to other pages.

**When to use:**

* In large applications with multiple routes or feature modules.

**Example:**

```jsx id="d4n8uy"
const Profile = React.lazy(() => import("./Profile"));

<Routes>
  <Route path="/profile" element={<Profile />} />
</Routes>
```
### **6. Suspense**

**`Suspense`** is a React component used to **display a fallback UI while waiting for a lazy-loaded component or asynchronous resource to load**.

**Key Features:**

* Works with **`React.lazy()`**
* Displays a **loading fallback**
* Improves user experience
* Supports asynchronous rendering

**How it works:**

* Wraps a lazy-loaded component.
* Shows the `fallback` content until the component finishes loading.

**When to use:**

* When using **Lazy Loading** or loading asynchronous components.

**Example:**

```jsx id="v3k7np"
import React, { Suspense } from "react";

const Dashboard = React.lazy(() => import("./Dashboard"));

function App() {
  return (
    <Suspense fallback={<p>Loading...</p>}>
      <Dashboard />
    </Suspense>
  );
}
```

### **7. Virtualization**

**Virtualization** is a technique that renders **only the visible items** in a large list, improving application performance.

**Key Features:**

* Renders only required elements
* Reduces DOM size
* Improves scrolling performance
* Ideal for large datasets

**How it works:**

* Instead of rendering thousands of items, only the items currently visible on the screen are rendered.
* As the user scrolls, new items are loaded and old ones are removed.

**When to use:**

* For large tables, long lists, or data grids with hundreds or thousands of records.

**Example:**

```jsx id="q8f2cw"
import { FixedSizeList } from "react-window";

<FixedSizeList
  height={300}
  itemCount={1000}
  itemSize={35}
  width={300}
>
  {({ index, style }) => (
    <div style={style}>Item {index}</div>
  )}
</FixedSizeList>
```

### **8. Debouncing**

**Debouncing** is a technique that delays the execution of a function until the user **stops triggering an event for a specified time**.

**Key Features:**

* Reduces unnecessary function calls
* Improves application performance
* Commonly used with search inputs
* Prevents excessive API requests

**How it works:**

* Every new event resets the timer.
* The function executes only after the delay period ends.

**When to use:**

* For search bars, form validation, or autocomplete features.

**Example:**

```javascript id="r5n4hy"
let timer;

function handleSearch(value) {
  clearTimeout(timer);

  timer = setTimeout(() => {
    console.log("Searching:", value);
  }, 500);
}
```

### **9. Throttling**

**Throttling** is a technique that limits a function to execute **at most once within a specified time interval**.

**Key Features:**

* Controls function execution frequency
* Improves performance
* Prevents event flooding
* Useful for continuous events

**How it works:**

* Executes the function immediately.
* Ignores additional calls until the defined time interval has passed.

**When to use:**

* For scroll events, window resizing, or mouse movement tracking.

**Example:**

```javascript id="k2p9mz"
let canRun = true;

function handleScroll() {
  if (!canRun) return;

  console.log("Scroll event");
  canRun = false;

  setTimeout(() => {
    canRun = true;
  }, 500);
}

window.addEventListener("scroll", handleScroll);
```

### **10. Bundle Optimization**

**Bundle Optimization** is the process of reducing the **size of JavaScript bundles** to improve application loading and performance.

**Key Features:**

* Reduces bundle size
* Improves page load speed
* Eliminates unused code
* Works with **Code Splitting** and **Tree Shaking**

**How it works:**

* Removes unused code and libraries.
* Splits large bundles into smaller chunks that load only when needed.

**When to use:**

* In large React applications where performance and loading time are important.

**Example:**

```jsx id="h7x3qd"
const Profile = React.lazy(() => import("./Profile"));

function App() {
  return (
    <Suspense fallback={<p>Loading...</p>}>
      <Profile />
    </Suspense>
  );
}
```

# ✅ **18. React 18 Features**

### **1. Concurrent Rendering**

**Concurrent Rendering** is a React 18 feature that allows React to **prepare and render multiple UI updates in the background** without blocking the user interface.

**Key Features:**

* Improves application responsiveness
* Allows interruptible rendering
* Prioritizes important UI updates
* Provides a smoother user experience

**How it works:**

* React can pause, resume, or discard rendering work before updating the DOM.
* High-priority updates (like user input) are processed before less important ones.

**When to use:**

* In applications with heavy rendering or complex UI updates where responsiveness is important.

**Example:**

```jsx id="d4p7mx"
import { createRoot } from "react-dom/client";
import App from "./App";

const root = createRoot(document.getElementById("root"));
root.render(<App />);
```

### **2. Automatic Batching**

**Automatic Batching** is a React 18 feature that groups **multiple state updates into a single re-render**, improving performance.

**Key Features:**

* Reduces unnecessary re-renders
* Improves application performance
* Works inside events, promises, and timeouts
* Enabled automatically in React 18

**How it works:**

* React collects multiple `setState` calls and processes them together in one render cycle.

**When to use:**

* Automatically available in React 18 whenever multiple state updates occur.

**Example:**

```jsx id="q9w3kt"
function handleClick() {
  setCount(c => c + 1);
  setFlag(true);
}
```

Both state updates are **batched** and cause only one re-render.

### **3. Suspense Improvements**

**Suspense Improvements** in React 18 make **asynchronous rendering and lazy loading** more efficient and user-friendly.

**Key Features:**

* Better support for asynchronous data loading
* Improved lazy loading experience
* Works with Concurrent Rendering
* Displays fallback UI while content loads

**How it works:**

* React shows the `fallback` component until the required component or data is ready.

**When to use:**

* When loading components or asynchronous resources that may take time.

**Example:**

```jsx id="m5x8rv"
import React, { Suspense } from "react";

const Profile = React.lazy(() => import("./Profile"));

<Suspense fallback={<p>Loading...</p>}>
  <Profile />
</Suspense>;
```

### **4. useTransition**

**`useTransition`** is a React hook that allows you to mark certain state updates as **low-priority**, keeping the UI responsive during expensive operations.

**Key Features:**

* Supports non-blocking UI updates
* Prioritizes urgent tasks
* Provides `isPending` loading state
* Improves user experience

**How it works:**

* Wrap non-urgent updates inside `startTransition()`.
* React processes urgent updates first and deferred updates later.

**When to use:**

* For search filtering, large lists, or expensive UI rendering tasks.

**Example:**

```jsx id="n7z2cp"
import { useTransition } from "react";

const [isPending, startTransition] = useTransition();

function handleChange(value) {
  startTransition(() => {
    setSearch(value);
  });
}
```

### **5. useDeferredValue**

**`useDeferredValue`** is a React hook that delays updating a value, allowing high-priority UI updates to happen first.

**Key Features:**

* Defers non-urgent updates
* Improves typing and scrolling performance
* Works well with expensive rendering
* Easy to integrate

**How it works:**

* React returns a deferred version of a value.
* The UI updates immediately, while expensive rendering waits until the browser is free.

**When to use:**

* For search inputs, filtering large datasets, or rendering heavy components.

**Example:**

```jsx id="y6k4lh"
import { useDeferredValue } from "react";

const deferredSearch = useDeferredValue(searchText);

const filteredUsers = users.filter(user =>
  user.name.includes(deferredSearch)
);
```

### **6. Streaming SSR**

**Streaming SSR (Server-Side Rendering)** is a React 18 feature that sends **HTML to the browser in small chunks** instead of waiting for the entire page to be ready.

**Key Features:**

* Faster initial page display
* Improves perceived performance
* Works with `Suspense`
* Reduces server response delays

**How it works:**

* The server starts sending available HTML immediately.
* Remaining content is streamed as it becomes ready.

**When to use:**

* In large server-rendered applications where faster page loading and SEO are important.

**Example:**

```javascript id="r2v9xd"
import { renderToPipeableStream } from "react-dom/server";

renderToPipeableStream(<App />, {
  onShellReady() {
    // Stream HTML to the browser
  },
});
```


# ✅ **19. Error Handling**

### **1. Error Boundaries**

**Error Boundaries** are React components that **catch JavaScript errors in the component tree** and display a fallback UI instead of crashing the entire application.

**Key Features:**

* Catches rendering errors
* Prevents the whole app from crashing
* Displays a custom fallback UI
* Logs error details for debugging

**How it works:**

* A class component implements `static getDerivedStateFromError()` and `componentDidCatch()`.
* If a child component throws an error, the Error Boundary catches it and renders a fallback UI.

**When to use:**

* Around critical sections like dashboards, forms, or page layouts.

**Example:**

```jsx id="e7m3qx"
class ErrorBoundary extends React.Component {
  state = { hasError: false };

  static getDerivedStateFromError() {
    return { hasError: true };
  }

  render() {
    return this.state.hasError
      ? <h2>Something went wrong.</h2>
      : this.props.children;
  }
}
```

### **2. Try-Catch with Async APIs**

**`try...catch`** is used to handle **errors in asynchronous operations**, such as API calls made with `fetch` or `axios`.

**Key Features:**

* Handles API and network errors
* Prevents application crashes
* Displays meaningful error messages
* Works with `async/await`

**How it works:**

* Place the asynchronous code inside the `try` block.
* If an error occurs, execution moves to the `catch` block.

**When to use:**

* Whenever making API calls or performing asynchronous operations.

**Example:**

```javascript id="w9k4lt"
async function fetchUsers() {
  try {
    const response = await fetch("/api/users");
    const data = await response.json();
    console.log(data);
  } catch (error) {
    console.log("Error:", error.message);
  }
}
```

### **3. Global Error Handling**

**Global Error Handling** is a centralized approach for **capturing and managing application-wide errors**.

**Key Features:**

* Handles unexpected errors globally
* Simplifies debugging and logging
* Improves application reliability
* Can integrate with monitoring tools

**How it works:**

* Capture unhandled JavaScript errors using global event listeners or a global error handler.
* Log the error and show a user-friendly message.

**When to use:**

* In large applications where errors should be tracked and managed from one place.

**Example:**

```javascript id="p5x8zn"
window.onerror = function (message, source, line) {
  console.log("Global Error:", message);
};
```

### **4. Fallback UI**

**Fallback UI** is a backup interface displayed when an error occurs or while content is loading, ensuring a better user experience.

**Key Features:**

* Prevents blank or broken screens
* Improves user experience
* Used with **Error Boundaries** and **Suspense**
* Can show loading or error messages

**How it works:**

* If an error is caught or a lazy-loaded component is still loading, React renders the fallback component instead of the main content.

**When to use:**

* During lazy loading, asynchronous rendering, or when handling runtime errors.

**Example:**

```jsx id="n2v7rh"
import React, { Suspense } from "react";

<Suspense fallback={<p>Loading...</p>}>
  <Dashboard />
</Suspense>;
```

# ✅ **20. React Design Patterns**

### **1. HOC (Higher-Order Component)**

**HOC (Higher-Order Component)** is a function that **takes a component as input and returns a new enhanced component** with additional functionality.

**Key Features:**

* Reuses component logic
* Enhances existing components
* Keeps code modular and reusable
* Does not modify the original component

**How it works:**

* A function wraps a component and adds extra behavior, then returns the updated component.

**When to use:**

* When multiple components need the same functionality, such as authentication, logging, or permissions.

**Example:**

```jsx id="m4k8pv"
function withMessage(Component) {
  return function EnhancedComponent() {
    return (
      <>
        <h2>Welcome!</h2>
        <Component />
      </>
    );
  };
}

const Home = () => <h1>Home Page</h1>;
export default withMessage(Home);
```

### **2. Render Props**

**Render Props** is a pattern where a component shares logic by passing a **function as a prop** that returns JSX.

**Key Features:**

* Promotes code reuse
* Shares logic between components
* Provides flexible UI rendering
* Avoids duplicate code

**How it works:**

* A component receives a function through a prop (commonly called `render` or `children`) and calls it to render content.

**When to use:**

* When different components need the same logic but display different UI.

**Example:**

```jsx id="q7n3tx"
function DataProvider({ render }) {
  const user = "John";
  return render(user);
}

<DataProvider
  render={(user) => <h1>Hello, {user}</h1>}
/>;
```

### **3. Compound Components**

**Compound Components** are components that **work together and share state internally**, giving users a flexible API.

**Key Features:**

* Components communicate through shared state
* Flexible and reusable design
* Cleaner and more readable JSX
* Commonly used in UI libraries

**How it works:**

* A parent component manages the state, and child components access or update that shared state.

**When to use:**

* For building reusable UI components like Tabs, Accordions, Menus, or Modals.

**Example:**

```jsx id="z2v9kr"
function Tabs({ children }) {
  return <div>{children}</div>;
}

Tabs.Tab = function Tab({ label }) {
  return <button>{label}</button>;
};

<Tabs>
  <Tabs.Tab label="Home" />
  <Tabs.Tab label="Profile" />
</Tabs>;
```

### **4. Custom Hooks**

**Custom Hooks** are JavaScript functions that **reuse stateful logic** by combining React hooks into a reusable function.

**Key Features:**

* Reuses business logic
* Keeps components clean
* Can use built-in React hooks
* Follows the `use` naming convention

**How it works:**

* Create a function starting with `use` that contains reusable hook logic.
* Components call the custom hook to share functionality.

**When to use:**

* When the same hook logic is used across multiple components, such as API calls or form handling.

**Example:**

```jsx id="x6p1mw"
import { useState } from "react";

function useCounter() {
  const [count, setCount] = useState(0);

  const increment = () => setCount(count + 1);

  return { count, increment };
}
```

### **5. Container-Presenter Pattern**

**Container-Presenter Pattern** separates **business logic** from **UI rendering** by dividing components into two types.

**Key Features:**

* Improves code organization
* Separates logic from presentation
* Makes components easier to test
* Encourages component reusability

**How it works:**

* The **Container Component** handles data fetching and state management.
* The **Presenter Component** receives data through props and displays the UI.

**When to use:**

* In medium or large applications where you want a clear separation between logic and UI.

**Example:**

```jsx id="h8r5cy"
// Presenter Component
function UserList({ users }) {
  return users.map(user => <p key={user.id}>{user.name}</p>);
}

// Container Component
function UserContainer() {
  const users = [{ id: 1, name: "John" }];
  return <UserList users={users} />;
}
```

# ✅ **21. Authentication & Authorization**

### **1. JWT Authentication**

**JWT (JSON Web Token) Authentication** is a method of verifying users by using a **signed token** instead of storing session data on the server.

**Key Features:**

* **Stateless authentication**
* Securely transfers user information
* Easy to use with APIs and SPAs
* Contains encoded user data and expiration time

**How it works:**

* The user logs in with valid credentials.
* The server generates a **JWT token** and sends it to the client.
* The client includes the token in the `Authorization` header for future requests.
* The server validates the token before granting access.

**When to use:**

* For modern web applications, REST APIs, and microservices that require stateless authentication.

**Example:**

```javascript id="jwt1ex"
axios.get("/api/profile", {
  headers: {
    Authorization: `Bearer ${token}`,
  },
});
```

### **2. Token Storage**

**Token Storage** is the process of saving the authentication token on the client so it can be used for future API requests.

**Key Features:**

* Stores login session information
* Supports automatic authentication
* Common options: **HttpOnly Cookies**, `localStorage`, and `sessionStorage`
* HttpOnly cookies provide better security against XSS attacks

**How it works:**

* After login, the token is stored on the client.
* The application reads the token and sends it with protected API requests.

**When to use:**

* Whenever JWT or token-based authentication is implemented.

**Example:**

```javascript id="tok2st"
localStorage.setItem("token", jwtToken);

const token = localStorage.getItem("token");
```

### **3. Refresh Tokens**

**Refresh Tokens** are long-lived tokens used to **generate a new access token** when the current access token expires.

**Key Features:**

* Extends user sessions securely
* Reduces the need for frequent logins
* Works alongside short-lived access tokens
* Improves security and user experience

**How it works:**

* The client sends the refresh token when the access token expires.
* The server validates it and issues a new access token.

**When to use:**

* In applications where users should stay logged in for extended periods.

**Example:**

```javascript id="ref3tk"
axios.post("/api/refresh-token", {
  refreshToken: storedRefreshToken,
});
```

### **4. Protected Routes**

**Protected Routes** restrict access to certain pages and allow only **authenticated users** to view them.

**Key Features:**

* Protects private pages
* Redirects unauthorized users
* Commonly used with JWT authentication
* Integrates with React Router

**How it works:**

* Check whether a valid token or authentication state exists.
* If authenticated, render the page; otherwise, redirect to the login page.

**When to use:**

* For Dashboard, Profile, Admin Panel, or any page that requires login.

**Example:**

```jsx id="prot4rt"
import { Navigate } from "react-router-dom";

function ProtectedRoute({ children }) {
  const token = localStorage.getItem("token");

  return token ? children : <Navigate to="/login" />;
}
```

### **5. Role-Based Access Control (RBAC)**

**Role-Based Access Control (RBAC)** is an authorization mechanism that grants or restricts access based on a user's **role**, such as Admin, Manager, or User.

**Key Features:**

* Controls access based on roles
* Improves application security
* Simplifies permission management
* Supports multiple user roles

**How it works:**

* The user's role is stored in the token or retrieved from the server.
* Before rendering a page or action, the application checks if the user has the required role.

**When to use:**

* In applications with different permission levels, such as admin dashboards or enterprise systems.

**Example:**

```jsx id="rbac5ex"
function AdminPage({ user }) {
  return user.role === "admin"
    ? <h1>Admin Dashboard</h1>
    : <h1>Access Denied</h1>;
}
```

# ✅ **22. Testing**

### **1. Jest**

**Jest** is a popular **JavaScript testing framework** used to write and run tests for React applications.

**Key Features:**

* Fast and easy to use
* Built-in **test runner** and **assertion library**
* Supports **mocking** and **snapshot testing**
* Generates code coverage reports

**How it works:**

* Test files are created using `test()` or `it()`.
* Jest executes the tests and compares the actual result with the expected result.

**When to use:**

* For testing JavaScript functions, React components, and application logic.

**Example:**

```javascript id="jst1ex"
test("adds two numbers", () => {
  expect(2 + 3).toBe(5);
});
```

### **2. React Testing Library**

**React Testing Library (RTL)** is a library for testing React components by focusing on **how users interact with the UI**.

**Key Features:**

* Encourages user-centric testing
* Works seamlessly with Jest
* Provides utilities for rendering and querying components
* Promotes better testing practices

**How it works:**

* Render a component using `render()`.
* Find elements using queries like `getByText()` or `getByRole()`.
* Simulate user actions and verify the results.

**When to use:**

* For testing React component rendering and user interactions.

**Example:**

```jsx id="rtl2ex"
import { render, screen } from "@testing-library/react";

render(<button>Submit</button>);

expect(screen.getByText("Submit")).toBeInTheDocument();
```

### **3. Unit Testing**

**Unit Testing** is the process of testing the **smallest individual unit of code**, such as a function or component, in isolation.

**Key Features:**

* Tests one unit at a time
* Easy to debug
* Fast execution
* Improves code quality

**How it works:**

* Write test cases for a single function or component.
* Verify that the output matches the expected result.

**When to use:**

* For utility functions, custom hooks, and individual React components.

**Example:**

```javascript id="unit3ex"
function add(a, b) {
  return a + b;
}

test("adds correctly", () => {
  expect(add(2, 3)).toBe(5);
});
```

### **4. Integration Testing**

**Integration Testing** verifies that **multiple components or modules work together correctly**.

**Key Features:**

* Tests component interaction
* Verifies complete workflows
* Detects integration issues
* Simulates real user behavior

**How it works:**

* Render related components together.
* Simulate user actions and verify that all connected parts behave correctly.

**When to use:**

* For testing forms, routing, API integration, and component communication.

**Example:**

```jsx id="int4ex"
import { render, screen, fireEvent } from "@testing-library/react";

render(<button onClick={() => alert("Saved")}>Save</button>);

fireEvent.click(screen.getByText("Save"));
```

### **5. Mocking APIs**

**Mocking APIs** means creating **fake API responses** during testing instead of making real network requests.

**Key Features:**

* Makes tests independent of external APIs
* Faster and more reliable testing
* Simulates different API scenarios
* Prevents network-related failures

**How it works:**

* Replace the real API call with a mock function that returns predefined data.

**When to use:**

* When testing components or functions that depend on API responses.

**Example:**

```javascript id="mock5ex"
global.fetch = jest.fn(() =>
  Promise.resolve({
    json: () => Promise.resolve([{ id: 1, name: "John" }]),
  })
);
```

### **6. Snapshot Testing**

**Snapshot Testing** is a technique that compares the **current UI output** of a component with a previously saved snapshot file.

**Key Features:**

* Detects unexpected UI changes
* Easy to maintain
* Integrated with Jest
* Useful for stable UI components

**How it works:**

* Jest generates a snapshot of the rendered component.
* Future test runs compare the new output with the saved snapshot.

**When to use:**

* For components whose UI should remain consistent over time.

**Example:**

```jsx id="snap6ex"
import { render } from "@testing-library/react";

test("matches snapshot", () => {
  const { asFragment } = render(<button>Click Me</button>);
  expect(asFragment()).toMatchSnapshot();
});
```

# ✅ **23. React Security**

### **1. XSS Prevention**

**XSS (Cross-Site Scripting) Prevention** is the practice of protecting an application from malicious scripts injected by attackers into web pages.

**Key Features:**

* Prevents execution of malicious JavaScript
* Protects user data and sessions
* Improves application security
* Uses safe rendering techniques

**How it works:**

* React automatically **escapes JSX content**, preventing scripts from being executed.
* Avoid rendering raw HTML unless it is properly sanitized.

**When to use:**

* Whenever displaying user-generated or external content.

**Example:**

```jsx id="xss1ex"
function App() {
  const userInput = "<script>alert('XSS')</script>";

  return <div>{userInput}</div>;
}
```

React safely renders the text instead of executing the script.

### **2. Sanitization**

**Sanitization** is the process of **cleaning and filtering user input** to remove potentially harmful content before displaying or storing it.

**Key Features:**

* Removes malicious scripts
* Protects against XSS attacks
* Cleans HTML input
* Improves application security

**How it works:**

* User input is passed through a sanitization library before rendering.
* Unsafe tags and attributes are removed.

**When to use:**

* When accepting rich text, HTML content, or any user-generated input.

**Example:**

```javascript id="san2ex"
import DOMPurify from "dompurify";

const cleanHTML = DOMPurify.sanitize(userInput);
```

### **3. Secure Token Storage**

**Secure Token Storage** is the practice of storing authentication tokens in a way that minimizes security risks.

**Key Features:**

* Protects authentication tokens
* Reduces the risk of XSS attacks
* Supports secure user sessions
* Commonly uses **HttpOnly cookies**

**How it works:**

* Store access or refresh tokens in **HttpOnly, Secure cookies** whenever possible.
* Avoid exposing sensitive tokens to JavaScript.

**When to use:**

* In any application that uses JWT or token-based authentication.

**Example:**

```javascript id="tok3ex"
// Preferred approach:
// Server sets a Secure, HttpOnly cookie
// Browser automatically sends it with requests.
```

### **4. CSRF Protection**

**CSRF (Cross-Site Request Forgery) Protection** prevents attackers from forcing authenticated users to perform unwanted actions on a website.

**Key Features:**

* Prevents unauthorized requests
* Protects authenticated sessions
* Commonly uses **CSRF tokens**
* Works well with cookie-based authentication

**How it works:**

* The server generates a unique CSRF token.
* The client sends the token with every sensitive request.
* The server validates the token before processing the request.

**When to use:**

* When using cookie-based authentication for login and API requests.

**Example:**

```javascript id="csrf4ex"
fetch("/api/profile", {
  method: "POST",
  headers: {
    "X-CSRF-Token": csrfToken,
  },
});
```

### **5. Authentication Security**

**Authentication Security** is the set of practices used to **protect user login and authentication mechanisms** from attacks.

**Key Features:**

* Uses secure password handling
* Implements token expiration
* Supports HTTPS communication
* Uses refresh tokens and secure storage

**How it works:**

* Authenticate users with secure credentials.
* Issue short-lived access tokens and long-lived refresh tokens.
* Store tokens securely and validate them on every protected request.

**When to use:**

* In every application that requires user login and protected resources.

**Example:**

```javascript id="auth5ex"
axios.get("/api/dashboard", {
  headers: {
    Authorization: `Bearer ${token}`,
  },
});
```

# ✅ **24. Build & Deployment**

### **1. Vite**

**Vite** is a modern **frontend build tool** that provides **fast development** and **optimized production builds**.

**Key Features:**

* **Instant server start**
* **Hot Module Replacement (HMR)**
* **Fast production build using Rollup**
* Supports **React, Vue, Angular, and Vanilla JS**

**How it works:**

* During development, Vite serves files directly using the browser's **native ES Modules (ESM)**.
* For production, it bundles and optimizes the app using **Rollup**.

**When to use:**

* For **modern web applications** where fast startup and quick code updates are important.

**Example:**

```bash
npm create vite@latest my-app
cd my-app
npm install
npm run dev
```

---

### **2. Webpack Basics**

**Webpack** is a **module bundler** that combines JavaScript, CSS, images, and other assets into optimized bundles.

**Key Features:**

* **Module bundling**
* Supports **loaders** and **plugins**
* **Code splitting**
* **Tree shaking** for removing unused code

**How it works:**

* Starts from an **entry file**, analyzes all dependencies, and creates one or more **bundle files** for the browser.

**When to use:**

* For projects requiring **custom build configuration** and advanced asset management.

**Basic Configuration:**

```javascript
module.exports = {
  entry: "./src/index.js",
  output: {
    filename: "bundle.js"
  }
};
```

---

### **3. Environment Variables**

**Environment Variables** are configuration values used to store data like **API URLs**, **tokens**, or **application settings** without hardcoding them.

**Key Features:**

* Keeps configuration **separate from code**
* Easy to change for **development**, **testing**, and **production**
* Improves **security and maintainability**

**How it works:**

* Variables are stored in a `.env` file and accessed inside the application.

**When to use:**

* When different environments need different configuration values.

**Example (.env):**

```text
VITE_API_URL=https://api.example.com
```

**Access in Vite:**

```javascript
const apiUrl = import.meta.env.VITE_API_URL;
console.log(apiUrl);
```

---

### **4. Production Builds**

A **Production Build** is the optimized version of an application that is deployed to users.

**Key Features:**

* **Minifies** JavaScript and CSS
* Removes **unused code (tree shaking)**
* Optimizes images and assets
* Improves **performance and loading speed**

**How it works:**

* The build tool analyzes the project, bundles files, applies optimizations, and generates deployable output.

**When to use:**

* Before deploying the application to a **production server**.

**Example:**

```bash
npm run build
```

**Output (Vite):**

```text
dist/
 ├── assets/
 ├── index.html
 └── ...
```

The generated **`dist`** folder contains the optimized files that can be deployed to a web server.

### **5. CI/CD**

**CI/CD (Continuous Integration / Continuous Deployment)** is a process that **automatically builds, tests, and deploys** an application whenever code is pushed to the repository.

**Key Features:**

* **Automatic build and testing**
* **Fast and reliable deployments**
* Detects bugs early
* Reduces **manual work**

**How it works:**

1. Developer pushes code to a Git repository.
2. The CI/CD pipeline automatically **builds** and **tests** the application.
3. If all tests pass, the application is **deployed** to the target environment.

**When to use:**

* For projects with **frequent code changes** and **team collaboration**.
* To achieve **faster and safer releases**.

**Example (GitHub Actions):**

```yaml
name: Build and Deploy

on: [push]

jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
      - run: npm install
      - run: npm run build
```

---

### **6. Deployment to AWS, Azure, Netlify, Vercel**

**Deployment** is the process of publishing an application so users can access it online.

**Key Features:**

* **Automated deployment**
* **Scalable hosting**
* **Custom domains and HTTPS**
* Easy integration with **CI/CD pipelines**

**How it works:**

* After the application is built, the generated files (for example, the `dist` folder) are uploaded to a hosting platform, which serves the application to users.

**When to use:**

* Use **[AWS](https://aws.amazon.com/?utm_source=chatgpt.com)** or **[Azure](https://azure.microsoft.com/?utm_source=chatgpt.com)** for enterprise and cloud-based applications.
* Use **[Netlify](https://www.netlify.com/?utm_source=chatgpt.com)** or **[Vercel](https://vercel.com/?utm_source=chatgpt.com)** for static websites and modern frontend frameworks like React, Angular, and Vue.

**Example (Vite Production Build):**

```bash
npm run build
```

**Deploy Flow:**

```text
Code → Build → Test → Deploy → Live Application
```


# ✅ **25. SSR & Modern React**

### **1. Server-Side Rendering (SSR)**

**Server-Side Rendering (SSR)** is a rendering technique where the **HTML is generated on the server** and sent to the browser before JavaScript loads.

**Key Features:**

* **Faster initial page load**
* Better **SEO**
* Improved performance for slow devices
* Content is visible immediately

**How it works:**

1. User requests a page.
2. The server renders the React application into HTML.
3. The browser receives the HTML and displays it.
4. JavaScript loads and makes the page interactive.

**When to use:**

* For **SEO-critical** websites like blogs, e-commerce, and marketing pages.
* When a fast **first contentful paint** is important.

**Example (Next.js):**

```javascript
export async function getServerSideProps() {
  return {
    props: { message: "Hello SSR" }
  };
}
```

---

### **2. CSR vs SSR**

**CSR (Client-Side Rendering)** and **SSR (Server-Side Rendering)** are two different ways to render web pages.

**Key Differences:**

| Feature       | **CSR**            | **SSR**                            |
| ------------- | ------------------ | ---------------------------------- |
| Rendering     | In the **browser** | On the **server**                  |
| Initial Load  | Slower             | Faster                             |
| SEO           | Limited            | Better                             |
| Interactivity | After JS loads     | After hydration                    |
| Best For      | Dashboards, SPAs   | Blogs, e-commerce, public websites |

**How it works:**

* **CSR:** Browser downloads JavaScript and renders the page.
* **SSR:** Server sends ready-to-display HTML, then JavaScript adds interactivity.

**When to use:**

* Use **CSR** for highly interactive applications.
* Use **SSR** when **SEO** and initial loading speed are important.

**Example:**

```text
CSR: Browser → JS → Render UI
SSR: Server → HTML → Browser → Hydration
```

---

### **3. Hydration**

**Hydration** is the process where React attaches **JavaScript event handlers** to the HTML generated by the server.

**Key Features:**

* Makes SSR pages **interactive**
* Reuses existing HTML
* Improves initial loading performance

**How it works:**

1. Server sends pre-rendered HTML.
2. Browser displays the HTML.
3. React loads JavaScript and connects the UI with event listeners.

**When to use:**

* Automatically used in **SSR frameworks** like Next.js.

**Example:**

```javascript
import { hydrateRoot } from "react-dom/client";

hydrateRoot(
  document.getElementById("root"),
  <App />
);
```

---

### **4. React Server Components**

**React Server Components (RSC)** are components that run **only on the server** and send the rendered result to the client without including their JavaScript bundle.

**Key Features:**

* Reduces **bundle size**
* Improves **performance**
* Can directly access **server-side data**
* Works together with **Client Components**

**How it works:**

* Server Components fetch data and generate UI on the server.
* Only the required output is sent to the browser.
* Interactive parts are handled by Client Components.

**When to use:**

* For pages that need **data fetching** but minimal client-side JavaScript.
* Commonly used in **Next.js App Router**.

**Example:**

```javascript
async function Products() {
  const data = await fetch("https://api.example.com/products");
  const products = await data.json();

  return <h1>{products.length} Products</h1>;
}

export default Products;
```

---

### **5. SEO Optimization**

**SEO (Search Engine Optimization)** is the process of improving a website so that **search engines can easily find, understand, and rank it**.

**Key Features:**

* Better **search engine ranking**
* Faster page loading
* Proper **meta tags**
* Improved user experience

**How it works:**

* Use **SSR or Static Site Generation (SSG)**.
* Add **title**, **meta description**, and structured content.
* Optimize images and page performance.

**When to use:**

* For public-facing websites such as **blogs, portfolios, e-commerce, and business sites**.

**Example (React Helmet):**

```javascript
import { Helmet } from "react-helmet";

<Helmet>
  <title>My React App</title>
  <meta
    name="description"
    content="React SEO Example"
  />
</Helmet>;
```


# ✅ **26. Next.js (Highly Recommended)**

### **1. App Router**

**App Router** is the **new routing system** in **Next.js 13+** that uses the **`app/`** directory to define routes and supports **Server Components** by default.

**Key Features:**

* Uses the **`app/`** folder
* Supports **nested layouts**
* **Server Components** by default
* Built-in **loading** and **error** UI
* Better **data fetching** and **streaming**

**How it works:**

* Each folder inside the `app/` directory represents a route.
* A `page.js` file defines the UI for that route.

**When to use:**

* For **new Next.js projects** that need modern features and better performance.

**Example:**

```text
app/
 ├── page.js        // Home page
 └── about/
      └── page.js   // /about route
```

---

### **2. Pages Router**

**Pages Router** is the **traditional routing system** in Next.js that uses the **`pages/`** directory.

**Key Features:**

* Simple **file-based routing**
* Supports **API routes**
* Uses **getServerSideProps** and **getStaticProps**
* Easy to learn and maintain

**How it works:**

* Every file inside the `pages/` folder automatically becomes a route.

**When to use:**

* For **existing Next.js applications** or projects using the older routing system.

**Example:**

```text
pages/
 ├── index.js      // Home page
 └── about.js      // /about route
```

---

### **3. Server Components**

**Server Components** are React components that run **only on the server** and send rendered HTML to the browser.

**Key Features:**

* **No client-side JavaScript** for the component
* Smaller **bundle size**
* Direct **server-side data fetching**
* Better **performance**

**How it works:**

* The component executes on the server, fetches data if needed, and returns the rendered output to the client.

**When to use:**

* For components that display data and do not require browser interactions like `useState` or `useEffect`.

**Example:**

```javascript
export default async function Users() {
  const res = await fetch("https://api.example.com/users");
  const users = await res.json();

  return <h1>{users.length} Users</h1>;
}
```

---

### **4. Client Components**

**Client Components** are React components that run in the **browser** and support interactivity.

**Key Features:**

* Supports **useState** and **useEffect**
* Handles **user events**
* Enables dynamic UI updates

**How it works:**

* Add the **`"use client"`** directive at the top of the file to make it a Client Component.

**When to use:**

* For forms, buttons, modals, and any UI that requires user interaction.

**Example:**

```javascript
"use client";

import { useState } from "react";

export default function Counter() {
  const [count, setCount] = useState(0);

  return (
    <button onClick={() => setCount(count + 1)}>
      {count}
    </button>
  );
}
```

---

### **5. Dynamic Routes**

**Dynamic Routes** allow you to create pages with **dynamic URL parameters** instead of fixed paths.

**Key Features:**

* Creates reusable pages
* Supports **URL parameters**
* Useful for blogs, products, and user profiles

**How it works:**

* Create a folder or file with square brackets (`[]`) to define a dynamic segment.
* The value is available as a route parameter.

**When to use:**

* When multiple pages follow the same structure, such as `/products/1`, `/products/2`, or `/users/john`.

**Example:**

```text
app/
 └── products/
      └── [id]/
           └── page.js
```

```javascript
export default function Product({ params }) {
  return <h1>Product ID: {params.id}</h1>;
}
```

### **6. Middleware**

**Middleware** is a function that runs **before a request reaches a route or page**, allowing you to modify the request or response.

**Key Features:**

* Runs **before rendering**
* Supports **authentication and authorization**
* Can perform **redirects** and **rewrites**
* Executes at the **Edge Runtime**

**How it works:**

* The middleware intercepts incoming requests, checks conditions, and then allows, redirects, or modifies the request.

**When to use:**

* For **route protection**, localization, logging, or URL rewriting.

**Example:**

```javascript
import { NextResponse } from "next/server";

export function middleware(request) {
  return NextResponse.next();
}
```

---

### **7. API Routes**

**API Routes** allow you to create **backend APIs** directly inside a Next.js application without a separate server.

**Key Features:**

* Build **REST APIs**
* Server-side execution
* Secure access to databases and external APIs
* No need for a separate backend service

**How it works:**

* Files inside the `pages/api/` or `app/api/` directory become API endpoints automatically.

**When to use:**

* For form handling, authentication, database operations, or custom backend logic.

**Example:**

```javascript
export async function GET() {
  return Response.json({
    message: "Hello API"
  });
}
```

---

### **8. SSR**

**SSR (Server-Side Rendering)** is a rendering method where the **HTML is generated on the server** for every request.

**Key Features:**

* **Fresh data** on every request
* Better **SEO**
* Faster initial page display
* Dynamic content support

**How it works:**

* The server fetches data, generates HTML, and sends it to the browser before the page becomes interactive.

**When to use:**

* For pages with **frequently changing data**, such as dashboards or news websites.

**Example:**

```javascript
export async function getServerSideProps() {
  return {
    props: {
      message: "SSR Page"
    }
  };
}
```

---

### **9. SSG**

**SSG (Static Site Generation)** is a rendering method where pages are **generated at build time** and served as static HTML files.

**Key Features:**

* Very **fast loading**
* Better **SEO**
* Lower server load
* Easy deployment with CDN

**How it works:**

* During the build process, Next.js generates static HTML pages that are reused for every user request.

**When to use:**

* For blogs, documentation sites, portfolios, or pages with content that rarely changes.

**Example:**

```javascript
export async function getStaticProps() {
  return {
    props: {
      message: "SSG Page"
    }
  };
}
```

---

### **10. ISR**

**ISR (Incremental Static Regeneration)** is a feature that combines the benefits of **SSG** and **SSR** by updating static pages after deployment.

**Key Features:**

* Static pages with **automatic updates**
* Better **performance**
* Reduced server load
* No need to rebuild the entire application

**How it works:**

* The page is generated statically and cached.
* After the specified time, the next request triggers a background regeneration with updated data.

**When to use:**

* For pages that need **fast performance** but also require **periodic data updates**, such as product catalogs or news articles.

**Example:**

```javascript
export async function getStaticProps() {
  return {
    props: {
      message: "ISR Page"
    },
    revalidate: 60
  };
}
```

# ✅ **27. TypeScript with React**

### **1. React with TypeScript**

**React with TypeScript** combines React with **static typing** to catch errors during development and improve code quality.

**Key Features:**

* **Static type checking**
* Better **IDE support** and auto-completion
* Early error detection
* Easier code maintenance

**How it works:**

* TypeScript adds **types** to variables, props, state, and functions, helping developers write safer code.

**When to use:**

* For **large-scale applications**, team projects, or when maintainability is important.

**Example:**

```tsx
function App(): JSX.Element {
  return <h1>Hello TypeScript</h1>;
}
```

---

### **2. Props Typing**

**Props Typing** defines the **type and structure** of data passed from a parent component to a child component.

**Key Features:**

* Prevents invalid props
* Improves code readability
* Provides editor auto-suggestions

**How it works:**

* Create a TypeScript interface or type and use it to define component props.

**When to use:**

* Whenever a component receives data through **props**.

**Example:**

```tsx
type UserProps = {
  name: string;
  age: number;
};

function User({ name, age }: UserProps) {
  return <p>{name} - {age}</p>;
}
```

---

### **3. State Typing**

**State Typing** specifies the data type stored in a React component's **state**.

**Key Features:**

* Prevents invalid state updates
* Improves type safety
* Makes state management easier

**How it works:**

* Pass the state type as a generic to the `useState` hook.

**When to use:**

* When using **useState** with objects, arrays, or custom data types.

**Example:**

```tsx
import { useState } from "react";

const [count, setCount] = useState<number>(0);
```

---

### **4. Generic Components**

**Generic Components** are reusable components that can work with **different data types** using TypeScript generics.

**Key Features:**

* **Reusable** and flexible
* Strong type safety
* Reduces duplicate code

**How it works:**

* Use a generic type parameter (`<T>`) to make the component accept any data type.

**When to use:**

* For reusable tables, lists, dropdowns, or forms.

**Example:**

```tsx
type ListProps<T> = {
  items: T[];
};

function List<T>({ items }: ListProps<T>) {
  return <div>{items.length}</div>;
}
```

---

### **5. Custom Hook Typing**

**Custom Hook Typing** defines the types for the **parameters and return values** of a custom React hook.

**Key Features:**

* Improves hook reusability
* Ensures type-safe data sharing
* Better developer experience

**How it works:**

* Add TypeScript types to the hook's input arguments and returned object or value.

**When to use:**

* When creating reusable logic with **custom hooks**.

**Example:**

```tsx
function useCounter(initial: number): [number, () => void] {
  const [count, setCount] = useState(initial);

  const increment = () => setCount(count + 1);

  return [count, increment];
}
```

---

### **6. API Response Typing**

**API Response Typing** defines the expected structure of data returned from an API.

**Key Features:**

* Prevents runtime errors
* Ensures correct data access
* Improves code readability

**How it works:**

* Create an interface or type for the API response and use it when fetching data.

**When to use:**

* Whenever consuming data from **REST APIs** or **GraphQL APIs**.

**Example:**

```tsx
type User = {
  id: number;
  name: string;
};

async function getUser(): Promise<User> {
  const res = await fetch("/api/user");
  return res.json();
}
```


# ✅ **28. React Architecture & System Design**

### **1. Folder Structure**

**Folder Structure** is the way files and directories are organized in a React project to keep the code clean and maintainable.

**Key Features:**

* **Easy navigation**
* Better **code organization**
* Simplifies maintenance
* Supports team collaboration

**How it works:**

* Group related files like components, hooks, services, and pages into separate folders.

**When to use:**

* In every React project, especially as the application grows.

**Example:**

```text
src/
 ├── components/
 ├── pages/
 ├── hooks/
 ├── services/
 └── assets/
```

---

### **2. Feature-Based Architecture**

**Feature-Based Architecture** organizes code by **business features** instead of file types.

**Key Features:**

* High **modularity**
* Better scalability
* Easier feature maintenance
* Reduces code coupling

**How it works:**

* Each feature contains its own components, hooks, APIs, and styles.

**When to use:**

* For **medium and large-scale applications** with multiple modules.

**Example:**

```text
src/
 ├── features/
 │    ├── auth/
 │    ├── dashboard/
 │    └── products/
```

---

### **3. Component Design**

**Component Design** is the practice of creating **small, reusable, and independent UI components**.

**Key Features:**

* **Reusable**
* Easy to test
* Easy to maintain
* Follows the **Single Responsibility Principle**

**How it works:**

* Break large UI sections into smaller components that handle one specific task.

**When to use:**

* For building maintainable and scalable user interfaces.

**Example:**

```tsx
function Button({ text }: { text: string }) {
  return <button>{text}</button>;
}
```

---

### **4. Shared Components**

**Shared Components** are reusable UI components that can be used across multiple features or pages.

**Key Features:**

* Reduces code duplication
* Ensures consistent UI
* Easy to update globally

**How it works:**

* Common components like buttons, modals, and input fields are stored in a shared folder and imported wherever needed.

**When to use:**

* When the same UI element is used in multiple places.

**Example:**

```text
src/
 └── components/
      ├── Button/
      ├── Input/
      └── Modal/
```

---

### **5. Reusable Hooks**

**Reusable Hooks** are custom React hooks that encapsulate **shared business logic** and can be used by multiple components.

**Key Features:**

* Promotes **code reuse**
* Separates logic from UI
* Improves readability
* Easier testing

**How it works:**

* Create a custom hook using existing React hooks and return the required data or functions.

**When to use:**

* For shared logic like API calls, authentication, or form handling.

**Example:**

```tsx
import { useState } from "react";

function useCounter() {
  const [count, setCount] = useState(0);

  return {
    count,
    increment: () => setCount(count + 1)
  };
}
```

---

### **6. API Layer Design**

**API Layer Design** is the practice of keeping all **API calls and backend communication** in a separate service layer.

**Key Features:**

* Centralized API management
* Better maintainability
* Reusable API functions
* Easier error handling

**How it works:**

* Create service files that contain all API requests and call them from components or hooks.

**When to use:**

* In applications that interact with **REST APIs** or **GraphQL APIs**.

**Example:**

```tsx
// services/userApi.ts
export const getUsers = () => {
  return fetch("/api/users").then(res => res.json());
};
```

---

### **7. Large Scale React Applications**

**Large Scale React Applications** are projects designed with a modular architecture to support **multiple features, teams, and growing codebases**.

**Key Features:**

* **Feature-based structure**
* Reusable components and hooks
* Centralized state management
* Separate API and utility layers
* Easy scalability and maintenance

**How it works:**

* The application is divided into independent modules, with shared resources placed in common folders.

**When to use:**

* For enterprise applications, dashboards, e-commerce platforms, or any project expected to grow over time.

**Example Structure:**

```text
src/
 ├── features/
 ├── components/
 ├── hooks/
 ├── services/
 ├── store/
 ├── utils/
 └── assets/
```


# ✅ **29. Real-Time Interview Scenarios**

### **1. Login & Authentication Flow**

**Login & Authentication Flow** is the process of **verifying a user’s identity** and allowing access to protected resources.

**Key Features:**

* **User login and logout**
* **JWT/Token-based authentication**
* Protected routes
* Session management

**How it works:**

1. User enters credentials.
2. Backend validates the data.
3. A **token** is returned and stored.
4. The token is sent with future API requests to verify the user.

**When to use:**

* In applications that require **secure user access**, such as dashboards or e-commerce sites.

**Example:**

```javascript id="4bkr71"
const handleLogin = async () => {
  const res = await fetch("/api/login");
  const data = await res.json();
  localStorage.setItem("token", data.token);
};
```

---

### **2. Search with Debounce**

**Search with Debounce** delays an API call until the user stops typing for a short time, reducing unnecessary requests.

**Key Features:**

* Fewer API calls
* Better performance
* Improved user experience
* Reduces server load

**How it works:**

* A timer waits after each keystroke. If the user types again, the previous timer is cleared.

**When to use:**

* For search bars, autocomplete fields, and live filtering.

**Example:**

```javascript id="18jhlx"
useEffect(() => {
  const timer = setTimeout(() => {
    fetchResults(searchText);
  }, 500);

  return () => clearTimeout(timer);
}, [searchText]);
```

---

### **3. Infinite Scroll**

**Infinite Scroll** is a technique that automatically loads more data when the user reaches the bottom of the page.

**Key Features:**

* Smooth user experience
* Loads data on demand
* Reduces initial loading time
* Ideal for large datasets

**How it works:**

* Detect when the user scrolls near the end of the page and request the next set of data from the API.

**When to use:**

* For social media feeds, product lists, or news applications.

**Example:**

```javascript id="z72m6q"
window.addEventListener("scroll", () => {
  if (
    window.innerHeight + window.scrollY >=
    document.body.offsetHeight
  ) {
    loadMoreData();
  }
});
```

---

### **4. Shopping Cart**

**Shopping Cart** is a feature that allows users to **add, update, and remove products** before checkout.

**Key Features:**

* Add and remove items
* Update product quantity
* Calculate total price
* Persistent cart state

**How it works:**

* Products are stored in **state management** (Context API, Redux, etc.) and updated whenever the user performs cart actions.

**When to use:**

* In e-commerce and online ordering applications.

**Example:**

```javascript id="6v67lu"
const addToCart = (product) => {
  setCart([...cart, product]);
};
```

---

### **5. Dashboard Development**

**Dashboard Development** involves building a central interface that displays **important data, charts, and user information**.

**Key Features:**

* Data visualization
* Responsive layout
* Real-time updates
* Role-based access

**How it works:**

* The dashboard fetches data from APIs and displays it using reusable components like cards, tables, and charts.

**When to use:**

* For admin panels, analytics systems, CRM tools, and business applications.

**Example:**

```javascript id="uywtt6"
useEffect(() => {
  fetch("/api/dashboard")
    .then(res => res.json())
    .then(data => setDashboardData(data));
}, []);
```

### **6. Role-Based Access Control (RBAC)**

**Role-Based Access Control (RBAC)** is a security model where users get access to features based on their **assigned roles**.

**Key Features:**

* **Role-based permissions**
* Protected routes and UI
* Improved security
* Easy user management

**How it works:**

* After login, the user's role (e.g., **Admin**, **User**) is checked before granting access to pages or actions.

**When to use:**

* In admin panels, dashboards, and applications with multiple user types.

**Example:**

```javascript
const isAdmin = user.role === "admin";

return isAdmin ? <AdminPanel /> : <AccessDenied />;
```

---

### **7. API Caching**

**API Caching** stores API responses temporarily so repeated requests can use cached data instead of calling the server again.

**Key Features:**

* Faster data loading
* Reduced API requests
* Better application performance
* Lower server load

**How it works:**

* The first request fetches data from the API.
* Later requests return cached data until the cache expires or is refreshed.

**When to use:**

* For frequently accessed data like product lists, user profiles, or dashboard statistics.

**Example:**

```javascript
const { data } = useQuery({
  queryKey: ["users"],
  queryFn: fetchUsers
});
```

---

### **8. File Upload**

**File Upload** allows users to send files such as images, documents, or videos from the browser to the server.

**Key Features:**

* Upload images and documents
* Supports multiple file types
* Progress tracking
* FormData integration

**How it works:**

* The user selects a file, which is added to a **FormData** object and sent to the backend using an API request.

**When to use:**

* For profile pictures, document submissions, or media management systems.

**Example:**

```javascript
const formData = new FormData();
formData.append("file", selectedFile);

fetch("/api/upload", {
  method: "POST",
  body: formData
});
```

---

### **9. Multi-Step Form**

**Multi-Step Form** divides a long form into **multiple smaller steps** to improve the user experience.

**Key Features:**

* Better usability
* Step-by-step validation
* Easy navigation
* Reduces form complexity

**How it works:**

* The form state is maintained while users move between different steps until final submission.

**When to use:**

* For registration forms, checkout processes, or survey applications.

**Example:**

```javascript
const [step, setStep] = useState(1);

<button onClick={() => setStep(step + 1)}>
  Next
</button>
```

---

### **10. Real-Time Notifications**

**Real-Time Notifications** instantly update users when new events occur without requiring a page refresh.

**Key Features:**

* Instant updates
* Better user engagement
* Uses **WebSocket** or **Server-Sent Events (SSE)**
* No manual refresh needed

**How it works:**

* The client maintains a live connection with the server, and the server pushes new notifications whenever an event occurs.

**When to use:**

* For chat applications, dashboards, stock updates, or order status tracking.

**Example:**

```javascript
const socket = new WebSocket("wss://example.com");

socket.onmessage = (event) => {
  console.log(event.data);
};
```

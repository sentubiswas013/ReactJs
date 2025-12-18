# React Hooks - Complete Interview Guide

## 1. What are React Hooks and why were they introduced?

**Answer:** Hooks are functions that let you use state and lifecycle features in functional components. They were introduced to solve three main problems:

- **Reusing stateful logic** between components was difficult
- **Complex components** became hard to understand with lifecycle methods
- **Classes confused** both people and machines

```jsx
// Before Hooks (Class Component)
class Counter extends React.Component {
  constructor(props) {
    super(props);
    this.state = { count: 0 };
  }
  
  render() {
    return <button onClick={() => this.setState({count: this.state.count + 1})}>
      {this.state.count}
    </button>;
  }
}

// After Hooks (Functional Component)
function Counter() {
  const [count, setCount] = useState(0);
  return <button onClick={() => setCount(count + 1)}>{count}</button>;
}
```

---

## 2. Rules of Hooks and why they exist

**Answer:** There are two main rules that ensure hooks work correctly:

- **Only call hooks at the top level** - never inside loops, conditions, or nested functions
- **Only call hooks from React functions** - components or custom hooks

These rules exist because React relies on the **order of hook calls** to maintain state between renders.

```jsx
// ❌ Wrong - Conditional hook
function BadComponent({ condition }) {
  if (condition) {
    const [state, setState] = useState(0); // Breaks hook order
  }
}

// ✅ Correct - Hook at top level
function GoodComponent({ condition }) {
  const [state, setState] = useState(0);
  
  if (condition) {
    // Use state here instead
  }
}
```

---

## 3. What is useState and how does it work?

**Answer:** useState is a hook that adds state to functional components. It returns an array with the current state value and a setter function. React re-renders the component when state changes.

```jsx
function Counter() {
  const [count, setCount] = useState(0); // Initial value: 0
  
  const increment = () => {
    setCount(count + 1); // Updates state
    // setCount(prev => prev + 1); // Functional update (safer)
  };
  
  return <button onClick={increment}>Count: {count}</button>;
}
```

**Key points:**
- State updates are **asynchronous**
- Use functional updates for state that depends on previous value
- React batches multiple setState calls

---

## 4. What is useEffect and how does it work?

**Answer:** useEffect handles side effects in functional components - like data fetching, subscriptions, or DOM manipulation. It runs after every render by default, but you can control when it runs with dependencies.

```jsx
function UserProfile({ userId }) {
  const [user, setUser] = useState(null);
  
  useEffect(() => {
    // Side effect: fetch user data
    fetchUser(userId).then(setUser);
    
    // Cleanup function (optional)
    return () => {
      // Cancel request or cleanup
    };
  }, [userId]); // Dependency array
  
  return <div>{user?.name}</div>;
}
```

**Three patterns:**
- `useEffect(() => {})` - runs after every render
- `useEffect(() => {}, [])` - runs once (mount)
- `useEffect(() => {}, [dep])` - runs when dep changes

---

## 5. Difference between useState and useEffect

**Answer:** They serve completely different purposes:

- **useState** manages component state - what data the component holds
- **useEffect** handles side effects - what the component does

```jsx
function TodoApp() {
  // useState: Managing state
  const [todos, setTodos] = useState([]);
  const [loading, setLoading] = useState(false);
  
  // useEffect: Side effects
  useEffect(() => {
    setLoading(true);
    fetchTodos().then(data => {
      setTodos(data);
      setLoading(false);
    });
  }, []); // Runs once on mount
  
  return loading ? <div>Loading...</div> : <TodoList todos={todos} />;
}
```

**Key difference:** useState is for **data**, useEffect is for **actions**.

---

## 6. Difference between useEffect, useLayoutEffect, and useInsertionEffect

**Answer:** All three handle side effects but run at different times in the render cycle:

```jsx
function Component() {
  // 1. useInsertionEffect - runs before DOM mutations (rare use)
  useInsertionEffect(() => {
    // Inject CSS-in-JS styles
  });
  
  // 2. useLayoutEffect - runs synchronously after DOM mutations
  useLayoutEffect(() => {
    // Measure DOM, animate elements
    const element = ref.current;
    element.style.transform = 'translateX(100px)';
  });
  
  // 3. useEffect - runs asynchronously after paint
  useEffect(() => {
    // Data fetching, subscriptions
    fetchData();
  });
}
```

**When to use:**
- **useEffect** - 99% of cases (data fetching, subscriptions)
- **useLayoutEffect** - DOM measurements, animations
- **useInsertionEffect** - CSS-in-JS libraries only

---

## 7. When should you avoid useEffect?

**Answer:** Avoid useEffect when you don't actually need side effects. Common mistakes include:

```jsx
// ❌ Don't use useEffect for calculations
function BadExample({ items }) {
  const [expensiveValue, setExpensiveValue] = useState();
  
  useEffect(() => {
    setExpensiveValue(items.filter(item => item.price > 100));
  }, [items]);
}

// ✅ Use useMemo instead
function GoodExample({ items }) {
  const expensiveValue = useMemo(() => 
    items.filter(item => item.price > 100), [items]
  );
}

// ❌ Don't use useEffect for event handlers
function BadButton() {
  useEffect(() => {
    const handleClick = () => console.log('clicked');
    // This creates new handler every render
  });
}

// ✅ Define handlers directly
function GoodButton() {
  const handleClick = () => console.log('clicked');
  return <button onClick={handleClick}>Click</button>;
}
```

---

## 8. How does the dependency array work internally?

**Answer:** React compares each dependency with its previous value using `Object.is()` comparison. If any dependency changed, the effect runs.

```jsx
function Component({ user }) {
  // React internally does something like:
  // if (user !== prevUser || posts !== prevPosts) runEffect()
  
  useEffect(() => {
    console.log('Effect runs');
  }, [user, posts]);
  
  // ❌ Object dependency - always different reference
  useEffect(() => {
    fetchData();
  }, [{ userId: user.id }]); // New object every render!
  
  // ✅ Primitive dependency
  useEffect(() => {
    fetchData();
  }, [user.id]); // Only changes when ID changes
}
```

**Key insight:** Dependencies must be **stable references** or **primitive values**.

---

## 9. What is stale closure in hooks?

**Answer:** Stale closure happens when a function captures an old value from a previous render, typically in async operations or intervals.

```jsx
function Counter() {
  const [count, setCount] = useState(0);
  
  // ❌ Stale closure problem
  useEffect(() => {
    const interval = setInterval(() => {
      setCount(count + 1); // Always uses count = 0!
    }, 1000);
    return () => clearInterval(interval);
  }, []); // Empty deps cause stale closure
  
  // ✅ Solution 1: Functional update
  useEffect(() => {
    const interval = setInterval(() => {
      setCount(prev => prev + 1); // Always gets latest value
    }, 1000);
    return () => clearInterval(interval);
  }, []);
  
  // ✅ Solution 2: Include dependency
  useEffect(() => {
    const interval = setInterval(() => {
      setCount(count + 1);
    }, 1000);
    return () => clearInterval(interval);
  }, [count]); // Re-creates interval when count changes
}
```

**Fix strategies:**
- Use **functional updates** (`setState(prev => prev + 1)`)
- Include **all dependencies** in the array
- Use **useRef** for mutable values that don't trigger re-renders

---

## Summary

React Hooks revolutionized how we write components by making functional components as powerful as class components. The key is understanding:

1. **Rules** - Call hooks at top level only
2. **useState** - For component state
3. **useEffect** - For side effects with proper dependencies
4. **Timing** - Different effect hooks run at different times
5. **Optimization** - Avoid unnecessary effects
6. **Dependencies** - Use stable references
7. **Closures** - Watch out for stale values in async code

Master these concepts and you'll write clean, efficient React code!
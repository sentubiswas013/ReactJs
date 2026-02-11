# ReactJS – Top 75 Most Asked Real-Time Interview Questions

## Performance Optimization (10 questions)

### 1. Your component fetches data from an API and re-renders multiple times unnecessarily. How do you optimize it?

**Problem**: Component re-renders on every parent update, refetching data unnecessarily.

**Solution**:
- Use empty dependency array `[]` in useEffect to fetch only once on mount
- Add cleanup flag `isMounted` to prevent state updates after unmount
- Combine multiple state updates into single setState call

**Code**:
```jsx
function DataComponent() {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    let isMounted = true;
    
    fetch('/api/data')
      .then(res => res.json())
      .then(result => {
        if (isMounted) {
          setData(result);
          setLoading(false);
        }
      });
    
    return () => { isMounted = false; };
  }, []); // Empty array - runs once

  if (loading) return <div>Loading...</div>;
  return <div>{data.name}</div>;
}
```

**Spoken Answer**: "Use empty dependency array in useEffect so it only runs once on mount. Add an isMounted flag to prevent setting state after the component unmounts. This stops unnecessary re-renders and API calls."

---

### 2. You have a parent component with multiple children. Updating parent state causes all children to re-render. How do you prevent it?

**Problem**: All children re-render when parent state changes, even if their props didn't change.

**Solution**:
- Wrap child components with `React.memo()`
- Use `useCallback()` for function props
- Split state into separate components if needed

**Code**:
```jsx
const ChildComponent = memo(({ name, onClick }) => {
  return <button onClick={onClick}>{name}</button>;
});

function ParentComponent() {
  const [count, setCount] = useState(0);
  const [text, setText] = useState('');

  const handleClick = useCallback(() => {
    console.log('Clicked');
  }, []);

  return (
    <div>
      <input value={text} onChange={(e) => setText(e.target.value)} />
      <ChildComponent name="Button 1" onClick={handleClick} />
      <ChildComponent name="Button 2" onClick={handleClick} />
    </div>
  );
}
```

**Spoken Answer**: "Wrap child components with React.memo so they only re-render when their props actually change. Use useCallback for function props to maintain the same reference. This prevents unnecessary child re-renders when parent state updates."

---

### 3. Your app displays a **large list of items**, and performance drops when interacting with other components. How do you optimize rendering?

**Problem**: Rendering thousands of list items slows down the entire app.

**Solution**:
- Use virtualization with `react-window` or `react-virtualized`
- Only render visible items in viewport
- Memoize list items with `memo()`

**Code**:
```jsx
import { FixedSizeList } from 'react-window';

function VirtualizedList({ items }) {
  const Row = ({ index, style }) => (
    <div style={style}>{items[index].name}</div>
  );

  return (
    <FixedSizeList
      height={600}
      itemCount={items.length}
      itemSize={50}
      width="100%"
    >
      {Row}
    </FixedSizeList>
  );
}
```

**Spoken Answer**: "Use react-window for virtualization. It only renders items that are visible in the viewport, not all 10,000 items. This dramatically improves performance because you're only rendering maybe 20 items at a time instead of thousands."

---

### 5. A component renders a list of 10,000 items causing lag. What techniques would you use?

**Problem**: Massive list causes browser to freeze.

**Solution**:
- **Virtual scrolling**: Use react-window (best for continuous lists)
- **Pagination**: Show 50 items per page
- **Lazy loading**: Infinite scroll pattern

**Code**:
```jsx
// Virtual Scrolling
import { FixedSizeList } from 'react-window';

function MassiveList({ data }) {
  const Row = ({ index, style }) => (
    <div style={style}>{data[index].name}</div>
  );

  return (
    <FixedSizeList
      height={800}
      itemCount={data.length}
      itemSize={35}
      width="100%"
    >
      {Row}
    </FixedSizeList>
  );
}

// Pagination Alternative
function PaginatedList({ data }) {
  const [page, setPage] = useState(1);
  const itemsPerPage = 50;
  
  const currentItems = data.slice(
    (page - 1) * itemsPerPage,
    page * itemsPerPage
  );

  return (
    <div>
      {currentItems.map(item => <div key={item.id}>{item.name}</div>)}
      <button onClick={() => setPage(page - 1)} disabled={page === 1}>Previous</button>
      <button onClick={() => setPage(page + 1)}>Next</button>
    </div>
  );
}
```

**Spoken Answer**: "Three options: Virtual scrolling with react-window only renders visible items. Pagination shows 50 items per page with next/previous buttons. Or infinite scroll that loads more as you scroll down. Virtual scrolling is best for continuous lists."

---

### 6. Your app has expensive calculations in render. How do you prevent recalculating on every render?

**Problem**: Heavy computations run on every render, slowing down the app.

**Solution**:
- Use `useMemo()` to cache calculation results
- Only recalculates when dependencies change
- Don't overuse - only for truly expensive operations

**Code**:
```jsx
function ExpensiveComponent({ items, filter }) {
  const filteredItems = useMemo(() => {
    console.log('Filtering items...');
    return items.filter(item => item.category === filter);
  }, [items, filter]);

  const total = useMemo(() => {
    console.log('Calculating total...');
    return filteredItems.reduce((sum, item) => sum + item.price, 0);
  }, [filteredItems]);

  return (
    <div>
      <p>Total: ${total}</p>
      {filteredItems.map(item => <div key={item.id}>{item.name}</div>)}
    </div>
  );
}
```

**Spoken Answer**: "Use useMemo to cache expensive calculations. It only recalculates when dependencies change. For example, filtering 10,000 items - wrap it in useMemo with items and filter as dependencies. It won't recalculate unless those values change."

---

### 7. Multiple components subscribe to the same context, but only one value changes. How do you prevent unnecessary re-renders?

**Problem**: All context consumers re-render when any context value changes.

**Solution**:
- Split context by update frequency
- Memoize context values
- Use separate contexts for different data

**Code**:
```jsx
const UserContext = createContext();
const ThemeContext = createContext();

function AppProvider({ children }) {
  const [user, setUser] = useState(null);
  const [theme, setTheme] = useState('light');

  const userValue = useMemo(() => ({ user, setUser }), [user]);
  const themeValue = useMemo(() => ({ theme, setTheme }), [theme]);

  return (
    <UserContext.Provider value={userValue}>
      <ThemeContext.Provider value={themeValue}>
        {children}
      </ThemeContext.Provider>
    </UserContext.Provider>
  );
}

// Components only subscribe to what they need
function UserProfile() {
  const { user } = useContext(UserContext);
  return <div>{user?.name}</div>;
}

function ThemeToggle() {
  const { theme, setTheme } = useContext(ThemeContext);
  return <button onClick={() => setTheme(theme === 'light' ? 'dark' : 'light')}>Toggle</button>;
}
```

**Spoken Answer**: "Split your context into multiple contexts based on how often they update. User data in one context, theme in another. Components only subscribe to what they need. When theme changes, only ThemeToggle re-renders, not UserProfile."

---

### 8. How would you optimize a component that renders based on window scroll position?

**Problem**: Scroll events fire hundreds of times per second, causing performance issues.

**Solution**:
- Use `requestAnimationFrame` for smooth updates
- Or throttle scroll events (100-200ms)
- Use `passive: true` for better performance

**Code**:
```jsx
function ScrollComponent() {
  const [scrollY, setScrollY] = useState(0);

  useEffect(() => {
    let ticking = false;

    const handleScroll = () => {
      if (!ticking) {
        window.requestAnimationFrame(() => {
          setScrollY(window.scrollY);
          ticking = false;
        });
        ticking = true;
      }
    };

    window.addEventListener('scroll', handleScroll, { passive: true });
    return () => window.removeEventListener('scroll', handleScroll);
  }, []);

  const isVisible = scrollY > 300;
  return isVisible ? <button className="scroll-to-top">↑</button> : null;
}
```

**Spoken Answer**: "Use requestAnimationFrame to batch scroll updates with the browser's paint cycle. Or throttle the scroll handler to run at most once every 100ms. Add passive: true to the event listener for better performance. This prevents hundreds of state updates per second."

---

### 9. Your React app has slow initial load time. What strategies would you implement?

**Problem**: App takes 10 seconds to load initially.

**Solution**:
- Code splitting with `React.lazy()` and `Suspense`
- Split routes into separate bundles
- Preload critical resources
- Optimize images and use CDN

**Code**:
```jsx
import { lazy, Suspense } from 'react';

const Dashboard = lazy(() => import('./Dashboard'));
const Profile = lazy(() => import('./Profile'));
const Settings = lazy(() => import('./Settings'));

function App() {
  return (
    <Suspense fallback={<div>Loading...</div>}>
      <Routes>
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/profile" element={<Profile />} />
        <Route path="/settings" element={<Settings />} />
      </Routes>
    </Suspense>
  );
}

// Preload on hover
function Navigation() {
  const preloadDashboard = () => {
    import('./Dashboard');
  };

  return (
    <a href="/dashboard" onMouseEnter={preloadDashboard}>
      Dashboard
    </a>
  );
}
```

**Spoken Answer**: "Use React.lazy to code-split your routes. Each route becomes a separate bundle that only loads when needed. User visits homepage, they only download homepage code, not the entire app. Preload routes on hover for instant navigation. This can reduce initial bundle from 2MB to 200KB."

---

### 10. How do you identify which components are causing performance bottlenecks?

**Problem**: App is slow but you don't know which components are the problem.

**Solution**:
- Use React DevTools Profiler (primary tool)
- Use built-in Profiler component
- Create custom render counter hook
- Check bundle size with webpack-bundle-analyzer

**Code**:
```jsx
// 1. React DevTools Profiler
// Open DevTools > Profiler tab > Record > Interact > Stop
// Look for yellow/red bars (slow components)

// 2. Built-in Profiler
import { Profiler } from 'react';

function onRender(id, phase, actualDuration) {
  if (actualDuration > 10) {
    console.warn(`${id} took ${actualDuration}ms`);
  }
}

function App() {
  return (
    <Profiler id="Dashboard" onRender={onRender}>
      <Dashboard />
    </Profiler>
  );
}

// 3. Custom Hook
function useWhyDidYouRender(componentName, props) {
  const previousProps = useRef();
  const renderCount = useRef(0);

  useEffect(() => {
    renderCount.current += 1;
    
    if (previousProps.current) {
      const changedProps = Object.keys(props).filter(
        key => previousProps.current[key] !== props[key]
      );
      console.log(`[${componentName}] Render #${renderCount.current}`);
      console.log('Changed props:', changedProps);
    }
    
    previousProps.current = props;
  });
}
```

**Spoken Answer**: "Open React DevTools Profiler, click record, interact with your app, then stop. The flame graph shows which components are slow - yellow and red bars. Look for components that render frequently or take more than 16ms. That's your bottleneck. Fix it with memo, useMemo, or useCallback."

---

### 41. How do you handle **debouncing or throttling** input changes (e.g., search box) to avoid unnecessary re-renders or API calls?

**Problem**: Every keystroke triggers an API call - typing "react" makes 5 API calls.

**Solution**:
- **Debounce**: Wait until user stops typing (300-500ms)
- **Throttle**: Execute at most once per interval
- Use custom hook for reusability

**Code**:
```jsx
// Debounce Hook
function useDebounce(value, delay) {
  const [debouncedValue, setDebouncedValue] = useState(value);

  useEffect(() => {
    const timer = setTimeout(() => setDebouncedValue(value), delay);
    return () => clearTimeout(timer);
  }, [value, delay]);

  return debouncedValue;
}

// Usage
function SearchBox() {
  const [query, setQuery] = useState('');
  const debouncedQuery = useDebounce(query, 500);

  useEffect(() => {
    if (debouncedQuery) {
      fetch(`/api/search?q=${debouncedQuery}`);
    }
  }, [debouncedQuery]);

  return <input value={query} onChange={(e) => setQuery(e.target.value)} />;
}

// Throttle Hook
function useThrottle(value, delay) {
  const [throttledValue, setThrottledValue] = useState(value);
  const lastRan = useRef(Date.now());

  useEffect(() => {
    const timer = setTimeout(() => {
      if (Date.now() - lastRan.current >= delay) {
        setThrottledValue(value);
        lastRan.current = Date.now();
      }
    }, delay - (Date.now() - lastRan.current));

    return () => clearTimeout(timer);
  }, [value, delay]);

  return throttledValue;
}
```

**Spoken Answer**: "Use debouncing for search boxes. Create a useDebounce hook that waits 500ms after the user stops typing before triggering the API call. So typing 'react' only makes 1 API call instead of 5. Use throttling for scroll events - it executes at most once every 100ms even if the event fires 100 times."

---

## State Management (12 questions)

### 11. How would you manage a **multi-step form** where users can navigate back and forth without losing data?

**Problem**: User fills step 1, goes to step 2, goes back - data is lost.

**Solution**: Store all form data in single state object, track current step separately.

**Code**:
```jsx
function MultiStepForm() {
  const [step, setStep] = useState(1);
  const [formData, setFormData] = useState({
    name: '',
    email: '',
    address: '',
    payment: ''
  });

  const updateField = (field, value) => {
    setFormData(prev => ({ ...prev, [field]: value }));
  };

  const nextStep = () => setStep(step + 1);
  const prevStep = () => setStep(step - 1);

  return (
    <div>
      {step === 1 && (
        <Step1 data={formData} updateField={updateField} onNext={nextStep} />
      )}
      {step === 2 && (
        <Step2 data={formData} updateField={updateField} onNext={nextStep} onBack={prevStep} />
      )}
      {step === 3 && (
        <Step3 data={formData} onBack={prevStep} onSubmit={() => console.log(formData)} />
      )}
    </div>
  );
}
```

**Spoken Answer**: "Store all form data in a single state object at the parent level. Track the current step separately. Each step component receives the data and an update function. When user navigates back and forth, the data persists because it's stored in the parent."

---

### 12. A child component deep in the component tree needs to update global state. How do you achieve this without prop drilling?

**Problem**: Passing setState function through 5 levels of components.

**Solution**: Use Context API to provide state and updater functions globally.

**Code**:
```jsx
const AppContext = createContext();

function AppProvider({ children }) {
  const [user, setUser] = useState(null);

  return (
    <AppContext.Provider value={{ user, setUser }}>
      {children}
    </AppContext.Provider>
  );
}

// Deep child component
function DeepChildComponent() {
  const { setUser } = useContext(AppContext);
  
  return (
    <button onClick={() => setUser({ name: 'John' })}>
      Update User
    </button>
  );
}
```

**Spoken Answer**: "Create a Context with state and setter functions. Wrap your app with the Provider. Any component, no matter how deep, can use useContext to access and update the state directly. No prop drilling needed."

---

### 13. Your app has shared state, some frequently updated, some rarely. How do you decide between **Context API, Redux, or local state**?

**Decision Guide**:

**Use Local State when**:
- State only used in one component
- Simple UI state (modals, dropdowns)

**Use Context API when**:
- State shared across multiple components
- Small to medium apps
- Theme, auth, language preferences
- Updates are infrequent

**Use Redux when**:
- Large app with complex state
- State updated frequently from many places
- Need time-travel debugging
- Need middleware

**Spoken Answer**: "Start with local state. If you need to share across components, use Context API. Only use Redux for large apps with complex state that updates frequently. Context is perfect for theme and auth. Redux is overkill for most small to medium apps."

---

### 14. How do you decide which values should be **props** vs **local state** in a component?

**Decision Rules**:

**Use Props when**:
- Value comes from parent
- Component doesn't control the value
- Value is configuration/settings

**Use State when**:
- Component controls the value
- Value changes based on user interaction
- Value is temporary/UI-specific

**Code**:
```jsx
// Props - Parent controls
function Button({ label, onClick }) {
  return <button onClick={onClick}>{label}</button>;
}

// State - Component controls
function Dropdown({ options }) {
  const [isOpen, setIsOpen] = useState(false);
  const [selected, setSelected] = useState(null);

  return (
    <div>
      <button onClick={() => setIsOpen(!isOpen)}>{selected || 'Select'}</button>
      {isOpen && <ul>{options.map(opt => <li key={opt}>{opt}</li>)}</ul>}
    </div>
  );
}
```

**Spoken Answer**: "If the parent needs to control it, use props. If only the component cares about it, use state. For example, a dropdown's open/closed state is local state, but the selected value might be a prop if the parent needs to know it."

---

### 15. How would you **share state** across multiple unrelated components without prop drilling?

**Solution**: Use Context API.

**Code**:
```jsx
const CartContext = createContext();

function CartProvider({ children }) {
  const [items, setItems] = useState([]);

  const addItem = (item) => setItems(prev => [...prev, item]);
  const removeItem = (id) => setItems(prev => prev.filter(i => i.id !== id));

  return (
    <CartContext.Provider value={{ items, addItem, removeItem }}>
      {children}
    </CartContext.Provider>
  );
}

export const useCart = () => useContext(CartContext);

// Component A
function ProductList() {
  const { addItem } = useCart();
  return <button onClick={() => addItem({ id: 1 })}>Add</button>;
}

// Component B (unrelated)
function CartSummary() {
  const { items } = useCart();
  return <div>Items: {items.length}</div>;
}
```

**Spoken Answer**: "Create a Context Provider that holds the shared state. Wrap your app with it. Any component can use useContext to access and update the state. Perfect for shopping carts, user data, or any shared state."

---

### 16. You need to sync state between localStorage and React state. How do you implement this?

**Solution**: Custom hook that reads from localStorage on mount and writes on change.

**Code**:
```jsx
function useLocalStorage(key, initialValue) {
  const [value, setValue] = useState(() => {
    const stored = localStorage.getItem(key);
    return stored ? JSON.parse(stored) : initialValue;
  });

  useEffect(() => {
    localStorage.setItem(key, JSON.stringify(value));
  }, [key, value]);

  return [value, setValue];
}

// Usage
function App() {
  const [user, setUser] = useLocalStorage('user', null);
  const [theme, setTheme] = useLocalStorage('theme', 'light');

  return (
    <div>
      <button onClick={() => setUser({ name: 'John' })}>Set User</button>
      <button onClick={() => setTheme(theme === 'light' ? 'dark' : 'light')}>
        Toggle Theme
      </button>
    </div>
  );
}
```

**Spoken Answer**: "Create a useLocalStorage hook. On mount, read from localStorage. Whenever state changes, write to localStorage using useEffect. It works just like useState but persists across page refreshes."

---

### 17. How do you manage state for a shopping cart that persists across page refreshes?

**Solution**: Combine Context API with localStorage.

**Code**:
```jsx
function CartProvider({ children }) {
  const [items, setItems] = useState(() => {
    const saved = localStorage.getItem('cart');
    return saved ? JSON.parse(saved) : [];
  });

  useEffect(() => {
    localStorage.setItem('cart', JSON.stringify(items));
  }, [items]);

  const addItem = (product) => {
    setItems(prev => {
      const existing = prev.find(item => item.id === product.id);
      if (existing) {
        return prev.map(item => 
          item.id === product.id 
            ? { ...item, quantity: item.quantity + 1 }
            : item
        );
      }
      return [...prev, { ...product, quantity: 1 }];
    });
  };

  return (
    <CartContext.Provider value={{ items, addItem }}>
      {children}
    </CartContext.Provider>
  );
}
```

**Spoken Answer**: "Use Context for global access and localStorage for persistence. Initialize state from localStorage on mount. Save to localStorage whenever items change using useEffect. Cart persists across refreshes and is accessible from any component."

---

### 18. Multiple components need to access and modify the same array. What's the best approach?

**Solution**: Context with reducer for complex updates.

**Code**:
```jsx
function todoReducer(state, action) {
  switch (action.type) {
    case 'ADD':
      return [...state, { id: Date.now(), text: action.text }];
    case 'DELETE':
      return state.filter(todo => todo.id !== action.id);
    default:
      return state;
  }
}

function TodoProvider({ children }) {
  const [todos, dispatch] = useReducer(todoReducer, []);

  return (
    <TodoContext.Provider value={{ todos, dispatch }}>
      {children}
    </TodoContext.Provider>
  );
}

// Usage
function TodoList() {
  const { todos, dispatch } = useTodos();
  return (
    <ul>
      {todos.map(todo => (
        <li key={todo.id}>
          {todo.text}
          <button onClick={() => dispatch({ type: 'DELETE', id: todo.id })}>Delete</button>
        </li>
      ))}
    </ul>
  );
}
```

**Spoken Answer**: "Use Context with useReducer. The reducer handles all array operations in one place. Components dispatch actions instead of directly modifying the array. This keeps logic centralized and makes updates predictable."

---

### 19. How would you implement undo/redo functionality in a React application?

**Solution**: Keep history stack of past states.

**Code**:
```jsx
function useUndoRedo(initialState) {
  const [history, setHistory] = useState([initialState]);
  const [currentIndex, setCurrentIndex] = useState(0);

  const currentState = history[currentIndex];

  const setState = (newState) => {
    const newHistory = history.slice(0, currentIndex + 1);
    setHistory([...newHistory, newState]);
    setCurrentIndex(currentIndex + 1);
  };

  const undo = () => {
    if (currentIndex > 0) setCurrentIndex(currentIndex - 1);
  };

  const redo = () => {
    if (currentIndex < history.length - 1) setCurrentIndex(currentIndex + 1);
  };

  return { state: currentState, setState, undo, redo };
}

// Usage
function DrawingApp() {
  const { state, setState, undo, redo } = useUndoRedo([]);

  return (
    <div>
      <button onClick={undo}>Undo</button>
      <button onClick={redo}>Redo</button>
      <button onClick={() => setState([...state, 'new'])}>Add</button>
    </div>
  );
}
```

**Spoken Answer**: "Keep an array of all past states and track current index. When user makes a change, add new state to history. Undo decrements index, redo increments it. When user makes a new change after undo, discard the future states."

---

### 20. Your form has 20+ fields. How do you manage state efficiently?

**Solution**: Single state object or use form library.

**Code**:
```jsx
// Manual approach
function LargeForm() {
  const [formData, setFormData] = useState({
    firstName: '',
    lastName: '',
    email: '',
    // ... 17 more fields
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prev => ({ ...prev, [name]: value }));
  };

  return (
    <form>
      <input name="firstName" value={formData.firstName} onChange={handleChange} />
      <input name="lastName" value={formData.lastName} onChange={handleChange} />
    </form>
  );
}

// With React Hook Form (better)
import { useForm } from 'react-hook-form';

function LargeForm() {
  const { register, handleSubmit } = useForm();

  return (
    <form onSubmit={handleSubmit(data => console.log(data))}>
      <input {...register('firstName', { required: true })} />
      <input {...register('email', { required: true })} />
      <button type="submit">Submit</button>
    </form>
  );
}
```

**Spoken Answer**: "Use a single state object with all fields. Create one handleChange function that updates any field using the input's name attribute. Or better yet, use React Hook Form library - it handles state, validation, and errors automatically."

---

### 117. How would you implement controlled vs uncontrolled components?

**Controlled**: React controls the value
**Uncontrolled**: DOM controls the value

**Code**:
```jsx
// Controlled Component
function ControlledInput() {
  const [value, setValue] = useState('');

  return (
    <input 
      value={value} 
      onChange={(e) => setValue(e.target.value)} 
    />
  );
}

// Uncontrolled Component
function UncontrolledInput() {
  const inputRef = useRef();

  const handleSubmit = () => {
    console.log(inputRef.current.value);
  };

  return (
    <div>
      <input ref={inputRef} defaultValue="" />
      <button onClick={handleSubmit}>Submit</button>
    </div>
  );
}
```

**Spoken Answer**: "Controlled components have value and onChange - React controls the state. Uncontrolled use refs and defaultValue - DOM controls the state. Use controlled when you need validation or instant feedback. Use uncontrolled for simple forms where you only need the value on submit."

---

### 144. How would you migrate from Redux to Context API?

**Migration Steps**:
1. Create Context for each Redux slice
2. Move reducers to useReducer
3. Replace useSelector with useContext
4. Replace dispatch with context dispatch

**Code**:
```jsx
// Before: Redux
function UserProfile() {
  const user = useSelector(state => state.user.data);
  const dispatch = useDispatch();
  
  return <div onClick={() => dispatch(setUser({ name: 'John' }))}>{user?.name}</div>;
}

// After: Context API
const UserContext = createContext();

function userReducer(state, action) {
  switch (action.type) {
    case 'SET_USER':
      return { ...state, data: action.payload };
    default:
      return state;
  }
}

function UserProvider({ children }) {
  const [state, dispatch] = useReducer(userReducer, { data: null });

  return (
    <UserContext.Provider value={{ state, dispatch }}>
      {children}
    </UserContext.Provider>
  );
}

function UserProfile() {
  const { state, dispatch } = useContext(UserContext);
  
  return (
    <div onClick={() => dispatch({ type: 'SET_USER', payload: { name: 'John' } })}>
      {state.data?.name}
    </div>
  );
}
```

**Spoken Answer**: "Create a Context for each Redux slice. Convert Redux reducers to useReducer. Replace useSelector with useContext. Replace Redux dispatch with context dispatch. Migrate one feature at a time, test thoroughly. Context API is simpler for most apps."

## Data Fetching & API Integration (10 questions)

### 21. How do you implement conditional rendering for loading, error, and success states in a component?

**Problem**: Need to show different UI based on API call status.

**Solution**: Three-state pattern - loading, error, data.

**Code**:
```jsx
function DataComponent() {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    fetch('/api/data')
      .then(res => res.json())
      .then(data => {
        setData(data);
        setLoading(false);
      })
      .catch(err => {
        setError(err.message);
        setLoading(false);
      });
  }, []);

  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error}</div>;
  return <div>{data.name}</div>;
}

// Custom Hook
function useFetch(url) {
  const [state, setState] = useState({
    data: null,
    loading: true,
    error: null
  });

  useEffect(() => {
    fetch(url)
      .then(res => res.json())
      .then(data => setState({ data, loading: false, error: null }))
      .catch(error => setState({ data: null, loading: false, error: error.message }));
  }, [url]);

  return state;
}
```

**Spoken Answer**: "Use three pieces of state: loading, error, and data. Start with loading true. On success, set data and loading false. On error, set error and loading false. Check loading first, then error, then render data. This gives you proper loading states and error handling."

---

### 22. You need to fetch data from **multiple APIs simultaneously** and display it once all are ready. How do you manage this in React?

**Problem**: Need to wait for all API calls to complete before rendering.

**Solution**: Use Promise.all() to wait for all requests.

**Code**:
```jsx
function MultiAPIComponent() {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    Promise.all([
      fetch('/api/users').then(res => res.json()),
      fetch('/api/posts').then(res => res.json()),
      fetch('/api/comments').then(res => res.json())
    ])
      .then(([users, posts, comments]) => {
        setData({ users, posts, comments });
        setLoading(false);
      })
      .catch(err => {
        console.error(err);
        setLoading(false);
      });
  }, []);

  if (loading) return <div>Loading...</div>;

  return (
    <div>
      <div>Users: {data.users.length}</div>
      <div>Posts: {data.posts.length}</div>
      <div>Comments: {data.comments.length}</div>
    </div>
  );
}
```

**Spoken Answer**: "Use Promise.all with an array of fetch calls. It waits for ALL to complete before resolving. Destructure the results in the same order you made the requests. Single loading state for all APIs. If you want to show data as it arrives, use separate state for each API instead."

---

### 23. Your component fetches data every few seconds (polling). How do you ensure it doesn't cause memory leaks or unnecessary re-renders?

**Problem**: Polling can cause memory leaks if not cleaned up.

**Solution**: Use setInterval with cleanup and isMounted flag.

**Code**:
```jsx
function PollingComponent() {
  const [data, setData] = useState(null);

  useEffect(() => {
    let isMounted = true;

    const fetchData = async () => {
      const res = await fetch('/api/data');
      const result = await res.json();
      if (isMounted) setData(result);
    };

    fetchData(); // Initial fetch
    const interval = setInterval(fetchData, 5000); // Poll every 5s

    return () => {
      isMounted = false;
      clearInterval(interval);
    };
  }, []);

  return <div>{data?.value}</div>;
}
```

**Spoken Answer**: "Use setInterval in useEffect to poll every 5 seconds. Always clear the interval in the cleanup function. Use an isMounted flag to prevent setting state after the component unmounts. Empty dependency array ensures you only create one interval."

---

### 24. How do you handle **error handling and fallback UI** for API failures in a React application?

**Problem**: Need graceful error handling with user-friendly UI.

**Solution**: Check response.ok, show error UI with retry button.

**Code**:
```jsx
function DataComponent() {
  const [data, setData] = useState(null);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(true);

  const fetchData = () => {
    setLoading(true);
    setError(null);
    
    fetch('/api/data')
      .then(res => {
        if (!res.ok) throw new Error('Failed to fetch');
        return res.json();
      })
      .then(data => {
        setData(data);
        setLoading(false);
      })
      .catch(err => {
        setError(err.message);
        setLoading(false);
      });
  };

  useEffect(() => {
    fetchData();
  }, []);

  if (loading) return <div>Loading...</div>;
  
  if (error) {
    return (
      <div className="error">
        <p>Error: {error}</p>
        <button onClick={fetchData}>Retry</button>
      </div>
    );
  }

  return <div>{data.name}</div>;
}
```

**Spoken Answer**: "Check response.ok before parsing JSON to catch HTTP errors. Show user-friendly error message with a Retry button. Extract fetch logic into a function so you can call it again on retry. Always handle errors gracefully - never show raw error messages to users."

---

### 25. You need to fetch data based on user input with a delay. How do you implement this?

**Problem**: Avoid API calls on every keystroke (debouncing).

**Solution**: Use setTimeout to delay API call.

**Code**:
```jsx
function SearchComponent() {
  const [query, setQuery] = useState('');
  const [results, setResults] = useState([]);

  useEffect(() => {
    if (!query) return;

    const timer = setTimeout(() => {
      fetch(`/api/search?q=${query}`)
        .then(res => res.json())
        .then(setResults);
    }, 500); // 500ms delay

    return () => clearTimeout(timer);
  }, [query]);

  return (
    <div>
      <input 
        value={query} 
        onChange={(e) => setQuery(e.target.value)} 
        placeholder="Search..."
      />
      {results.map(item => (
        <div key={item.id}>{item.name}</div>
      ))}
    </div>
  );
}
```

**Spoken Answer**: "Use setTimeout in useEffect to delay the API call by 500ms. Clear the timeout on every input change. Only when the user stops typing for 500ms does the API call fire. This reduces API calls from hundreds to just one per search."

---

### 26. How would you implement infinite scrolling with API pagination?

**Problem**: Load more data as user scrolls down.

**Solution**: Use IntersectionObserver to detect when user reaches bottom.

**Code**:
```jsx
function InfiniteScrollList() {
  const [items, setItems] = useState([]);
  const [page, setPage] = useState(1);
  const [loading, setLoading] = useState(false);
  const [hasMore, setHasMore] = useState(true);
  const observerRef = useRef();

  useEffect(() => {
    setLoading(true);
    fetch(`/api/items?page=${page}`)
      .then(res => res.json())
      .then(data => {
        setItems(prev => [...prev, ...data.items]);
        setHasMore(data.hasMore);
        setLoading(false);
      });
  }, [page]);

  useEffect(() => {
    const observer = new IntersectionObserver(entries => {
      if (entries[0].isIntersecting && hasMore && !loading) {
        setPage(prev => prev + 1);
      }
    });

    if (observerRef.current) observer.observe(observerRef.current);
    return () => observer.disconnect();
  }, [hasMore, loading]);

  return (
    <div>
      {items.map(item => (
        <div key={item.id}>{item.name}</div>
      ))}
      {hasMore && <div ref={observerRef}>Loading...</div>}
    </div>
  );
}
```

**Spoken Answer**: "Use IntersectionObserver to watch a loading div at the bottom. When it becomes visible, increment the page number. Append new items to existing array with spread operator. Track hasMore to stop loading when no more data. This gives you smooth infinite scrolling."

---

### 28. How do you handle race conditions when making multiple API calls?

**Problem**: Fast typing can cause older API responses to overwrite newer ones.

**Solution**: Use AbortController to cancel previous requests.

**Code**:
```jsx
function SearchComponent() {
  const [query, setQuery] = useState('');
  const [results, setResults] = useState([]);

  useEffect(() => {
    if (!query) return;

    const controller = new AbortController();

    fetch(`/api/search?q=${query}`, { signal: controller.signal })
      .then(res => res.json())
      .then(setResults)
      .catch(err => {
        if (err.name !== 'AbortError') console.error(err);
      });

    return () => controller.abort(); // Cancel previous request
  }, [query]);

  return (
    <div>
      <input value={query} onChange={(e) => setQuery(e.target.value)} />
      {results.map(item => <div key={item.id}>{item.name}</div>)}
    </div>
  );
}

// Alternative: Request ID
function SearchComponent() {
  const [query, setQuery] = useState('');
  const [results, setResults] = useState([]);
  const requestIdRef = useRef(0);

  useEffect(() => {
    if (!query) return;

    const requestId = ++requestIdRef.current;

    fetch(`/api/search?q=${query}`)
      .then(res => res.json())
      .then(data => {
        if (requestId === requestIdRef.current) {
          setResults(data);
        }
      });
  }, [query]);

  return <div>...</div>;
}
```

**Spoken Answer**: "Use AbortController to cancel previous requests when a new one starts. Pass the signal to fetch. In cleanup, call abort. This cancels old requests and saves bandwidth. Alternative: use a request ID counter and only update state if it's the latest request."

---

### 30. How would you implement optimistic updates when posting data to an API?

**Problem**: Make UI feel instant by updating before API confirms.

**Solution**: Update UI immediately, rollback on error.

**Code**:
```jsx
function TodoList() {
  const [todos, setTodos] = useState([]);

  const addTodo = (text) => {
    const tempId = Date.now();
    const newTodo = { id: tempId, text, pending: true };

    // Optimistic update
    setTodos(prev => [...prev, newTodo]);

    fetch('/api/todos', {
      method: 'POST',
      body: JSON.stringify({ text }),
      headers: { 'Content-Type': 'application/json' }
    })
      .then(res => res.json())
      .then(savedTodo => {
        // Replace temp with real data
        setTodos(prev => 
          prev.map(t => t.id === tempId ? savedTodo : t)
        );
      })
      .catch(() => {
        // Rollback on error
        setTodos(prev => prev.filter(t => t.id !== tempId));
        alert('Failed to add todo');
      });
  };

  return (
    <div>
      {todos.map(todo => (
        <div key={todo.id} style={{ opacity: todo.pending ? 0.5 : 1 }}>
          {todo.text}
        </div>
      ))}
      <button onClick={() => addTodo('New Todo')}>Add</button>
    </div>
  );
}
```

**Spoken Answer**: "Update UI immediately with temporary data. Send API request in background. When API responds, replace temp data with real data. If API fails, remove the temp item and show error. Show pending state visually with reduced opacity. This makes the UI feel instant."

---

### 42. You have a search input that should trigger API calls. How do you optimize this?

**Problem**: Every keystroke triggers API call.

**Solution**: Combine debouncing + AbortController + empty query check.

**Code**:
```jsx
function OptimizedSearch() {
  const [query, setQuery] = useState('');
  const [results, setResults] = useState([]);
  const [loading, setLoading] = useState(false);
  const abortControllerRef = useRef(null);

  useEffect(() => {
    if (!query) {
      setResults([]);
      return;
    }

    const timer = setTimeout(() => {
      // Cancel previous request
      if (abortControllerRef.current) {
        abortControllerRef.current.abort();
      }

      abortControllerRef.current = new AbortController();
      setLoading(true);

      fetch(`/api/search?q=${query}`, {
        signal: abortControllerRef.current.signal
      })
        .then(res => res.json())
        .then(data => {
          setResults(data);
          setLoading(false);
        })
        .catch(err => {
          if (err.name !== 'AbortError') {
            console.error(err);
            setLoading(false);
          }
        });
    }, 500);

    return () => {
      clearTimeout(timer);
      if (abortControllerRef.current) {
        abortControllerRef.current.abort();
      }
    };
  }, [query]);

  return (
    <div>
      <input 
        value={query} 
        onChange={(e) => setQuery(e.target.value)} 
        placeholder="Search..."
      />
      {loading && <div>Loading...</div>}
      {results.map(item => (
        <div key={item.id}>{item.name}</div>
      ))}
    </div>
  );
}
```

**Spoken Answer**: "Three optimizations: First, debounce with 500ms delay. Second, cancel previous requests with AbortController. Third, clear results when query is empty. This reduces API calls from hundreds to just one per search, cancels stale requests, and handles edge cases."

---

### 107. How would you implement retry logic for failed API calls?

**Problem**: API call fails due to network issue, need to retry.

**Solution**: Retry with exponential backoff.

**Code**:
```jsx
function useApiWithRetry(url, maxRetries = 3) {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    let retries = 0;

    const fetchWithRetry = async () => {
      try {
        const res = await fetch(url);
        if (!res.ok) throw new Error('Failed');
        const result = await res.json();
        setData(result);
        setLoading(false);
      } catch (err) {
        if (retries < maxRetries) {
          retries++;
          const delay = Math.pow(2, retries) * 1000; // Exponential backoff
          console.log(`Retry ${retries}/${maxRetries} in ${delay}ms`);
          setTimeout(fetchWithRetry, delay);
        } else {
          setError(err.message);
          setLoading(false);
        }
      }
    };

    fetchWithRetry();
  }, [url, maxRetries]);

  return { data, loading, error };
}

// Usage
function Component() {
  const { data, loading, error } = useApiWithRetry('/api/data', 3);

  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error}</div>;
  return <div>{data.name}</div>;
}

// Manual retry button
function ComponentWithManualRetry() {
  const [data, setData] = useState(null);
  const [error, setError] = useState(null);

  const fetchData = async () => {
    try {
      const res = await fetch('/api/data');
      const result = await res.json();
      setData(result);
      setError(null);
    } catch (err) {
      setError(err.message);
    }
  };

  useEffect(() => {
    fetchData();
  }, []);

  if (error) {
    return (
      <div>
        <p>Error: {error}</p>
        <button onClick={fetchData}>Retry</button>
      </div>
    );
  }

  return <div>{data?.name}</div>;
}
```

**Spoken Answer**: "Implement automatic retry with exponential backoff. First retry after 2 seconds, second after 4 seconds, third after 8 seconds. Track retry count and stop after max retries. Or provide a manual Retry button that calls the fetch function again. Exponential backoff prevents overwhelming the server."

---

## Component Architecture & Design (8 questions)

### 31. How do you manage a complex form with **dependent fields** and validation rules in React?

**Problem**: Form fields depend on each other, complex validation logic.

**Solution**: Single state object with dynamic validation, or use React Hook Form.

**Code**:
```jsx
// Manual approach
function ComplexForm() {
  const [form, setForm] = useState({
    country: '',
    state: '',
    city: '',
    zipCode: ''
  });
  const [errors, setErrors] = useState({});

  const validate = (name, value) => {
    if (name === 'zipCode' && form.country === 'US') {
      return value.length === 5 ? '' : 'US zip must be 5 digits';
    }
    return '';
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setForm(prev => ({ ...prev, [name]: value }));
    
    const error = validate(name, value);
    setErrors(prev => ({ ...prev, [name]: error }));
  };

  return (
    <form>
      <select name="country" value={form.country} onChange={handleChange}>
        <option value="">Select Country</option>
        <option value="US">USA</option>
        <option value="CA">Canada</option>
      </select>

      {form.country && (
        <input name="state" value={form.state} onChange={handleChange} placeholder="State" />
      )}

      {form.state && (
        <input name="city" value={form.city} onChange={handleChange} placeholder="City" />
      )}

      <input name="zipCode" value={form.zipCode} onChange={handleChange} />
      {errors.zipCode && <span>{errors.zipCode}</span>}
    </form>
  );
}

// With React Hook Form (better)
import { useForm } from 'react-hook-form';

function ComplexForm() {
  const { register, watch, formState: { errors } } = useForm();
  const country = watch('country');

  return (
    <form>
      <select {...register('country', { required: true })}>
        <option value="">Select Country</option>
        <option value="US">USA</option>
      </select>

      {country && <input {...register('state', { required: true })} />}

      <input 
        {...register('zipCode', {
          validate: v => country === 'US' ? v.length === 5 : true
        })} 
      />
      {errors.zipCode && <span>Invalid zip</span>}
    </form>
  );
}
```

**Spoken Answer**: "Store all form data in a single state object. Use conditional rendering to show/hide dependent fields. Validate fields based on other field values. For complex forms, use React Hook Form library - it has built-in watch() to track field changes and conditional validation. Much cleaner than managing everything manually."

---

### 32. A component may throw errors during rendering. How do you prevent it from crashing the whole app?

**Problem**: One component error crashes entire app.

**Solution**: Error Boundary component.

**Code**:
```jsx
import { Component } from 'react';

class ErrorBoundary extends Component {
  state = { hasError: false, error: null };

  static getDerivedStateFromError(error) {
    return { hasError: true, error };
  }

  componentDidCatch(error, errorInfo) {
    console.error('Error caught:', error, errorInfo);
  }

  render() {
    if (this.state.hasError) {
      return (
        <div>
          <h2>Something went wrong</h2>
          <button onClick={() => this.setState({ hasError: false })}>
            Try again
          </button>
        </div>
      );
    }

    return this.props.children;
  }
}

// Usage - Wrap risky components
function App() {
  return (
    <div>
      <ErrorBoundary>
        <Header />
      </ErrorBoundary>
      
      <ErrorBoundary>
        <MainContent />
      </ErrorBoundary>
      
      <ErrorBoundary>
        <Sidebar />
      </ErrorBoundary>
    </div>
  );
}
```

**Spoken Answer**: "Create an Error Boundary class component with getDerivedStateFromError and componentDidCatch. Wrap risky components with it. When an error occurs, show fallback UI instead of crashing. Use multiple boundaries - if sidebar breaks, header and main content still work. Error Boundaries must be class components, not functional."

---

### 34. How do you implement **lazy loading** for large components to improve initial load time?

**Problem**: Large components slow down initial page load.

**Solution**: Use React.lazy() and Suspense for code splitting.

**Code**:
```jsx
import { lazy, Suspense } from 'react';

// Lazy load components
const Dashboard = lazy(() => import('./Dashboard'));
const Profile = lazy(() => import('./Profile'));
const Settings = lazy(() => import('./Settings'));

function App() {
  return (
    <Suspense fallback={<div>Loading...</div>}>
      <Routes>
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/profile" element={<Profile />} />
        <Route path="/settings" element={<Settings />} />
      </Routes>
    </Suspense>
  );
}

// Preload on hover for better UX
function Navigation() {
  const preloadDashboard = () => {
    import('./Dashboard');
  };

  return (
    <nav>
      <a href="/dashboard" onMouseEnter={preloadDashboard}>
        Dashboard
      </a>
    </nav>
  );
}
```

**Spoken Answer**: "Use React.lazy to import components dynamically. Wrap with Suspense and provide a fallback. Each lazy component creates a separate bundle file that only loads when needed. User visits homepage, they only download homepage code, not the entire app. Preload on hover for instant navigation. This can reduce initial bundle from 2MB to 200KB."

---

### 35. You have a modal that needs to be triggered from multiple places. How do you structure this?

**Problem**: Modal needs to be opened from different components.

**Solution**: Context API to manage modal state globally.

**Code**:
```jsx
const ModalContext = createContext();

function ModalProvider({ children }) {
  const [isOpen, setIsOpen] = useState(false);
  const [content, setContent] = useState(null);

  const openModal = (modalContent) => {
    setContent(modalContent);
    setIsOpen(true);
  };

  const closeModal = () => {
    setIsOpen(false);
    setContent(null);
  };

  return (
    <ModalContext.Provider value={{ openModal, closeModal }}>
      {children}
      {isOpen && (
        <div className="modal">
          <div className="modal-content">
            {content}
            <button onClick={closeModal}>Close</button>
          </div>
        </div>
      )}
    </ModalContext.Provider>
  );
}

export const useModal = () => useContext(ModalContext);

// Usage from any component
function ComponentA() {
  const { openModal } = useModal();
  
  return (
    <button onClick={() => openModal(<div>Modal from A</div>)}>
      Open Modal
    </button>
  );
}

function ComponentB() {
  const { openModal } = useModal();
  
  return (
    <button onClick={() => openModal(<div>Modal from B</div>)}>
      Open Modal
    </button>
  );
}
```

**Spoken Answer**: "Create a ModalContext with openModal and closeModal functions. Single modal component at the top level. Any component can call openModal with custom content. The modal state is global, but triggers are local. This avoids duplicating modal code everywhere."

---

### 36. How would you build a reusable table component with sorting, filtering, and pagination?

**Problem**: Need flexible table for different data types.

**Solution**: Accept data and columns as props, use useMemo for performance.

**Code**:
```jsx
function Table({ data, columns }) {
  const [sortKey, setSortKey] = useState(null);
  const [sortOrder, setSortOrder] = useState('asc');
  const [filter, setFilter] = useState('');
  const [page, setPage] = useState(1);
  const pageSize = 10;

  const filteredData = useMemo(() => {
    return data.filter(row =>
      Object.values(row).some(val =>
        String(val).toLowerCase().includes(filter.toLowerCase())
      )
    );
  }, [data, filter]);

  const sortedData = useMemo(() => {
    if (!sortKey) return filteredData;
    
    return [...filteredData].sort((a, b) => {
      if (a[sortKey] < b[sortKey]) return sortOrder === 'asc' ? -1 : 1;
      if (a[sortKey] > b[sortKey]) return sortOrder === 'asc' ? 1 : -1;
      return 0;
    });
  }, [filteredData, sortKey, sortOrder]);

  const paginatedData = useMemo(() => {
    const start = (page - 1) * pageSize;
    return sortedData.slice(start, start + pageSize);
  }, [sortedData, page]);

  const handleSort = (key) => {
    if (sortKey === key) {
      setSortOrder(sortOrder === 'asc' ? 'desc' : 'asc');
    } else {
      setSortKey(key);
      setSortOrder('asc');
    }
  };

  return (
    <div>
      <input
        placeholder="Filter..."
        value={filter}
        onChange={(e) => setFilter(e.target.value)}
      />

      <table>
        <thead>
          <tr>
            {columns.map(col => (
              <th key={col.key} onClick={() => handleSort(col.key)}>
                {col.label} {sortKey === col.key && (sortOrder === 'asc' ? '↑' : '↓')}
              </th>
            ))}
          </tr>
        </thead>
        <tbody>
          {paginatedData.map((row, i) => (
            <tr key={i}>
              {columns.map(col => (
                <td key={col.key}>{row[col.key]}</td>
              ))}
            </tr>
          ))}
        </tbody>
      </table>

      <button onClick={() => setPage(p => p - 1)} disabled={page === 1}>Previous</button>
      <button onClick={() => setPage(p => p + 1)}>Next</button>
    </div>
  );
}

// Usage
function App() {
  const data = [
    { id: 1, name: 'John', age: 30 },
    { id: 2, name: 'Jane', age: 25 }
  ];

  const columns = [
    { key: 'id', label: 'ID' },
    { key: 'name', label: 'Name' },
    { key: 'age', label: 'Age' }
  ];

  return <Table data={data} columns={columns} />;
}
```

**Spoken Answer**: "Accept data and columns as props to keep it generic. Use useMemo for filtering, sorting, and pagination to avoid recalculating on every render. Click column header to sort. Single filter input searches all columns. Pagination shows 10 items per page. This pattern works for any data structure."

---

### 38. How do you handle deeply nested component props (prop drilling)?

**Problem**: Passing props through 5 levels of components.

**Solution**: Use Context API or component composition.

**Code**:
```jsx
// Solution 1: Context API
const UserContext = createContext();

function App() {
  const user = { name: 'John', role: 'admin' };
  
  return (
    <UserContext.Provider value={user}>
      <Layout />
    </UserContext.Provider>
  );
}

function Layout() {
  return <Sidebar />; // No props!
}

function Sidebar() {
  return <UserProfile />; // No props!
}

function UserProfile() {
  const user = useContext(UserContext); // Direct access!
  return <div>{user.name}</div>;
}

// Solution 2: Component Composition
function App() {
  const user = { name: 'John' };
  
  return (
    <Layout sidebar={<UserProfile user={user} />}>
      <MainContent />
    </Layout>
  );
}

function Layout({ sidebar, children }) {
  return (
    <div>
      {sidebar}
      {children}
    </div>
  );
}
```

**Spoken Answer**: "Two solutions: Context API or component composition. Context lets any component access data directly without passing through every level. Composition passes components as props instead of data. Use Context for truly global data like user or theme. Use composition when you can. Avoid passing props through more than 3 levels."

---

### 40. You need to build a component library. What patterns would you follow?

**Problem**: Create reusable, consistent components.

**Solution**: Follow these key patterns.

**Code**:
```jsx
// 1. Consistent Props Pattern
function Button({ variant = 'primary', size = 'medium', children, ...props }) {
  return (
    <button 
      className={`btn btn-${variant} btn-${size}`}
      {...props}
    >
      {children}
    </button>
  );
}

// 2. Compound Components
function Card({ children }) {
  return <div className="card">{children}</div>;
}

Card.Header = ({ children }) => <div className="card-header">{children}</div>;
Card.Body = ({ children }) => <div className="card-body">{children}</div>;
Card.Footer = ({ children }) => <div className="card-footer">{children}</div>;

// Usage
function App() {
  return (
    <Card>
      <Card.Header>Title</Card.Header>
      <Card.Body>Content</Card.Body>
      <Card.Footer>Footer</Card.Footer>
    </Card>
  );
}

// 3. Forward Refs
import { forwardRef } from 'react';

const Input = forwardRef((props, ref) => {
  return <input ref={ref} {...props} />;
});

// 4. Spread Props for Flexibility
function CustomButton({ label, ...props }) {
  return <button {...props}>{label}</button>;
}

// Usage - any HTML button prop works
<CustomButton label="Click" onClick={handleClick} disabled={true} />
```

**Spoken Answer**: "Key patterns: Use consistent prop names like variant and size. Spread remaining props with {...props} for flexibility. Use compound components for related UI like Card.Header and Card.Body. Forward refs so users can access DOM elements. Provide sensible defaults. Document with Storybook. Export everything from a single index file."

---

### 142. How would you migrate a class component to functional component with hooks?

**Problem**: Need to convert old class components to modern hooks.

**Migration Guide**:

**Code**:
```jsx
// Before: Class Component
class UserProfile extends Component {
  constructor(props) {
    super(props);
    this.state = {
      user: null,
      loading: true
    };
  }

  componentDidMount() {
    fetch(`/api/users/${this.props.userId}`)
      .then(res => res.json())
      .then(user => this.setState({ user, loading: false }));
  }

  componentDidUpdate(prevProps) {
    if (prevProps.userId !== this.props.userId) {
      this.setState({ loading: true });
      fetch(`/api/users/${this.props.userId}`)
        .then(res => res.json())
        .then(user => this.setState({ user, loading: false }));
    }
  }

  componentWillUnmount() {
    // Cleanup
  }

  handleClick = () => {
    this.setState({ user: null });
  }

  render() {
    const { user, loading } = this.state;
    
    if (loading) return <div>Loading...</div>;
    
    return (
      <div>
        <h1>{user.name}</h1>
        <button onClick={this.handleClick}>Clear</button>
      </div>
    );
  }
}

// After: Functional Component with Hooks
function UserProfile({ userId }) {
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    setLoading(true);
    
    fetch(`/api/users/${userId}`)
      .then(res => res.json())
      .then(user => {
        setUser(user);
        setLoading(false);
      });

    return () => {
      // Cleanup (componentWillUnmount)
    };
  }, [userId]); // Runs on mount and when userId changes

  const handleClick = () => {
    setUser(null);
  };

  if (loading) return <div>Loading...</div>;
  
  return (
    <div>
      <h1>{user.name}</h1>
      <button onClick={handleClick}>Clear</button>
    </div>
  );
}
```

**Migration Checklist**:
```
1. constructor + this.state → useState
2. componentDidMount → useEffect with []
3. componentDidUpdate → useEffect with dependencies
4. componentWillUnmount → useEffect return function
5. this.setState → setState from useState
6. this.props → function parameters
7. class methods → regular functions
8. this.method → just method
```

**Spoken Answer**: "Replace constructor and this.state with useState. Replace componentDidMount with useEffect and empty array. Replace componentDidUpdate with useEffect and dependencies. Replace componentWillUnmount with useEffect return function. Remove all 'this' references. Class methods become regular functions. Props come as function parameters. Much cleaner and less code!"

---

## Lifecycle & Side Effects (8 questions)

### 51. You have a component that subscribes to a WebSocket for live updates. How do you handle cleanup when the component unmounts?

**Problem**: WebSocket connection stays open after component unmounts, causing memory leaks.

**Solution**: Close WebSocket in cleanup function.

**Code**:
```jsx
function LiveUpdates() {
  const [messages, setMessages] = useState([]);

  useEffect(() => {
    const ws = new WebSocket('ws://localhost:8080');

    ws.onopen = () => {
      console.log('Connected');
    };

    ws.onmessage = (event) => {
      setMessages(prev => [...prev, event.data]);
    };

    ws.onerror = (error) => {
      console.error('WebSocket error:', error);
    };

    // Cleanup function
    return () => {
      ws.close();
      console.log('WebSocket closed');
    };
  }, []); // Empty array - connect once

  return (
    <div>
      {messages.map((msg, i) => (
        <div key={i}>{msg}</div>
      ))}
    </div>
  );
}
```

**Spoken Answer**: "Create WebSocket in useEffect. Return a cleanup function that calls ws.close(). Use empty dependency array so it only connects once on mount. When component unmounts, cleanup runs and closes the connection. This prevents memory leaks from open connections."

---

### 52. How do you run an effect only when a specific prop changes, not on every render?

**Problem**: Effect runs on every render, but should only run when specific prop changes.

**Solution**: Add only that prop to dependency array.

**Code**:
```jsx
function UserProfile({ userId, theme }) {
  const [user, setUser] = useState(null);

  // Only runs when userId changes, NOT when theme changes
  useEffect(() => {
    fetch(`/api/users/${userId}`)
      .then(res => res.json())
      .then(setUser);
  }, [userId]); // Only userId in dependency array

  return <div style={{ background: theme }}>{user?.name}</div>;
}
```

**Spoken Answer**: "Put only the specific prop in the dependency array. Effect runs when ANY dependency changes. So if you only include userId, it won't run when theme changes. Empty array means run once on mount. No array means run on every render."

---

### 53. You need to fetch data when component mounts and update when a prop changes. How do you structure this?

**Problem**: Need to fetch data initially and refetch when prop changes.

**Solution**: Include prop in dependency array - runs on mount AND when prop changes.

**Code**:
```jsx
function DataComponent({ userId }) {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    let isMounted = true;
    const controller = new AbortController();

    setLoading(true);
    
    fetch(`/api/users/${userId}`, { signal: controller.signal })
      .then(res => res.json())
      .then(data => {
        if (isMounted) {
          setData(data);
          setLoading(false);
        }
      })
      .catch(err => {
        if (err.name !== 'AbortError' && isMounted) {
          console.error(err);
          setLoading(false);
        }
      });

    return () => {
      isMounted = false;
      controller.abort();
    };
  }, [userId]); // Runs on mount AND when userId changes

  if (loading) return <div>Loading...</div>;
  return <div>{data?.name}</div>;
}
```

**Spoken Answer**: "Include the prop in the dependency array. Effect runs on mount and whenever that prop changes. Use AbortController to cancel previous requests when prop changes. Use isMounted flag to prevent setting state after unmount. This handles both initial load and updates cleanly."

---

### 54. How do you handle cleanup for event listeners added in useEffect?

**Problem**: Event listeners stay attached after component unmounts.

**Solution**: Remove listeners in cleanup function.

**Code**:
```jsx
function WindowSize() {
  const [size, setSize] = useState({ 
    width: window.innerWidth, 
    height: window.innerHeight 
  });

  useEffect(() => {
    const handleResize = () => {
      setSize({ 
        width: window.innerWidth, 
        height: window.innerHeight 
      });
    };

    window.addEventListener('resize', handleResize);

    // Cleanup
    return () => {
      window.removeEventListener('resize', handleResize);
    };
  }, []);

  return <div>{size.width} x {size.height}</div>;
}

// Multiple listeners
function KeyboardHandler() {
  useEffect(() => {
    const handleKeyDown = (e) => console.log('Key down:', e.key);
    const handleKeyUp = (e) => console.log('Key up:', e.key);

    window.addEventListener('keydown', handleKeyDown);
    window.addEventListener('keyup', handleKeyUp);

    return () => {
      window.removeEventListener('keydown', handleKeyDown);
      window.removeEventListener('keyup', handleKeyUp);
    };
  }, []);

  return <div>Press any key</div>;
}
```

**Spoken Answer**: "Add event listener in useEffect. Return cleanup function that removes the listener. Use the same function reference for both add and remove. Empty dependency array for global listeners. Always cleanup to prevent memory leaks."

---

### 56. How would you implement a timer that starts when component mounts and cleans up on unmount?

**Problem**: Timer keeps running after component unmounts.

**Solution**: Use setInterval with clearInterval in cleanup.

**Code**:
```jsx
// Simple timer
function Timer() {
  const [seconds, setSeconds] = useState(0);

  useEffect(() => {
    const interval = setInterval(() => {
      setSeconds(prev => prev + 1);
    }, 1000);

    return () => clearInterval(interval);
  }, []);

  return <div>Seconds: {seconds}</div>;
}

// Countdown timer
function Countdown({ initialSeconds }) {
  const [seconds, setSeconds] = useState(initialSeconds);

  useEffect(() => {
    if (seconds <= 0) return;

    const interval = setInterval(() => {
      setSeconds(prev => prev - 1);
    }, 1000);

    return () => clearInterval(interval);
  }, [seconds]);

  return <div>{seconds > 0 ? `${seconds}s remaining` : "Time's up!"}</div>;
}

// Controllable timer
function useTimer() {
  const [seconds, setSeconds] = useState(0);
  const [isRunning, setIsRunning] = useState(false);

  useEffect(() => {
    if (!isRunning) return;

    const interval = setInterval(() => {
      setSeconds(prev => prev + 1);
    }, 1000);

    return () => clearInterval(interval);
  }, [isRunning]);

  return { 
    seconds, 
    start: () => setIsRunning(true), 
    stop: () => setIsRunning(false),
    reset: () => setSeconds(0)
  };
}
```

**Spoken Answer**: "Use setInterval in useEffect. Always clear the interval in the cleanup function. Use functional setState (prev => prev + 1) for accurate counting. Empty dependency array creates interval once. Return cleanup function that calls clearInterval."

---

### 58. How do you prevent useEffect from running on initial render?

**Problem**: Effect should only run on updates, not on mount.

**Solution**: Use ref to track first render.

**Code**:
```jsx
function Component({ value }) {
  const isFirstRender = useRef(true);

  useEffect(() => {
    if (isFirstRender.current) {
      isFirstRender.current = false;
      return; // Skip first render
    }

    console.log('Value changed:', value);
  }, [value]);

  return <div>{value}</div>;
}

// Custom hook for reusability
function useUpdateEffect(effect, dependencies) {
  const isFirstRender = useRef(true);

  useEffect(() => {
    if (isFirstRender.current) {
      isFirstRender.current = false;
      return;
    }

    return effect();
  }, dependencies);
}

// Usage
function Component({ value }) {
  useUpdateEffect(() => {
    console.log('Value updated:', value);
  }, [value]);

  return <div>{value}</div>;
}
```

**Spoken Answer**: "Use a ref to track if it's the first render. Check the ref in useEffect and return early if true. Set ref to false after first render. Create a custom useUpdateEffect hook for reusability. Refs persist across renders but don't trigger re-renders."

---

### 60. How do you handle async operations in useEffect properly?

**Problem**: Can't make useEffect callback async directly.

**Solution**: Create async function inside useEffect.

**Code**:
```jsx
// Method 1: Async function inside
function Component() {
  const [data, setData] = useState(null);

  useEffect(() => {
    async function fetchData() {
      try {
        const response = await fetch('/api/data');
        const result = await response.json();
        setData(result);
      } catch (error) {
        console.error(error);
      }
    }

    fetchData();
  }, []);

  return <div>{data?.name}</div>;
}

// Method 2: With cleanup
function Component({ userId }) {
  const [data, setData] = useState(null);

  useEffect(() => {
    let isMounted = true;

    async function fetchData() {
      try {
        const response = await fetch(`/api/users/${userId}`);
        const result = await response.json();
        
        if (isMounted) {
          setData(result);
        }
      } catch (error) {
        if (isMounted) {
          console.error(error);
        }
      }
    }

    fetchData();

    return () => {
      isMounted = false;
    };
  }, [userId]);

  return <div>{data?.name}</div>;
}

// Method 3: With AbortController
function Component({ userId }) {
  const [data, setData] = useState(null);

  useEffect(() => {
    const controller = new AbortController();

    async function fetchData() {
      try {
        const response = await fetch(`/api/users/${userId}`, {
          signal: controller.signal
        });
        const result = await response.json();
        setData(result);
      } catch (error) {
        if (error.name !== 'AbortError') {
          console.error(error);
        }
      }
    }

    fetchData();

    return () => controller.abort();
  }, [userId]);

  return <div>{data?.name}</div>;
}
```

**Spoken Answer**: "Create an async function inside useEffect and call it immediately. Can't make the useEffect callback itself async. Use isMounted flag or AbortController for cleanup. Always handle errors with try-catch. The async function can use await, but the useEffect callback cannot."

---

### 104. You have a memory leak in your React app. How do you identify and fix it?

**Problem**: App gets slower over time, memory usage increases.

**Solution**: Identify and fix common memory leak sources.

**Common Causes & Fixes**:

```jsx
// 1. LEAK: Forgot to cleanup interval
function BadComponent() {
  useEffect(() => {
    setInterval(() => {
      console.log('Running');
    }, 1000);
    // Missing cleanup!
  }, []);
}

// FIX: Clear interval
function GoodComponent() {
  useEffect(() => {
    const interval = setInterval(() => {
      console.log('Running');
    }, 1000);

    return () => clearInterval(interval);
  }, []);
}

// 2. LEAK: Event listener not removed
function BadComponent() {
  useEffect(() => {
    window.addEventListener('resize', handleResize);
    // Missing cleanup!
  }, []);
}

// FIX: Remove listener
function GoodComponent() {
  useEffect(() => {
    const handleResize = () => console.log('Resized');
    window.addEventListener('resize', handleResize);

    return () => window.removeEventListener('resize', handleResize);
  }, []);
}

// 3. LEAK: setState after unmount
function BadComponent() {
  const [data, setData] = useState(null);

  useEffect(() => {
    fetch('/api/data')
      .then(res => res.json())
      .then(setData); // Runs even after unmount!
  }, []);
}

// FIX: Use isMounted flag
function GoodComponent() {
  const [data, setData] = useState(null);

  useEffect(() => {
    let isMounted = true;

    fetch('/api/data')
      .then(res => res.json())
      .then(data => {
        if (isMounted) setData(data);
      });

    return () => {
      isMounted = false;
    };
  }, []);
}

// 4. LEAK: WebSocket not closed
function BadComponent() {
  useEffect(() => {
    const ws = new WebSocket('ws://localhost:8080');
    ws.onmessage = (e) => console.log(e.data);
    // Missing cleanup!
  }, []);
}

// FIX: Close WebSocket
function GoodComponent() {
  useEffect(() => {
    const ws = new WebSocket('ws://localhost:8080');
    ws.onmessage = (e) => console.log(e.data);

    return () => ws.close();
  }, []);
}
```

**How to Identify**:
```jsx
// Use Chrome DevTools
// 1. Open DevTools > Memory tab
// 2. Take heap snapshot
// 3. Interact with app
// 4. Take another snapshot
// 5. Compare - look for detached DOM nodes

// Add logging to track component lifecycle
function Component() {
  useEffect(() => {
    console.log('Component mounted');
    
    return () => {
      console.log('Component unmounted');
    };
  }, []);
}

// Check for warnings in console
// React warns: "Can't perform a React state update on an unmounted component"
```

**Spoken Answer**: "Common memory leaks: forgot to clear intervals, didn't remove event listeners, setState after unmount, WebSocket not closed. Always return cleanup function from useEffect. Use Chrome DevTools Memory tab to take heap snapshots and compare. Look for detached DOM nodes. React warns you about setState on unmounted components - fix those warnings."

---

## Authentication & Authorization (6 questions)

### 61. How would you implement authentication in React where the token persists across refreshes and user data is accessible globally?

**Problem**: Token and user data lost on page refresh.

**Solution**: Store token in localStorage, use Context for global access.

**Code**:
```jsx
const AuthContext = createContext();

export function AuthProvider({ children }) {
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const token = localStorage.getItem('token');
    if (token) {
      fetch('/api/me', {
        headers: { Authorization: `Bearer ${token}` }
      })
        .then(res => res.json())
        .then(user => {
          setUser(user);
          setLoading(false);
        })
        .catch(() => {
          localStorage.removeItem('token');
          setLoading(false);
        });
    } else {
      setLoading(false);
    }
  }, []);

  const login = async (email, password) => {
    const res = await fetch('/api/login', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ email, password })
    });
    const data = await res.json();
    
    localStorage.setItem('token', data.token);
    setUser(data.user);
  };

  const logout = () => {
    localStorage.removeItem('token');
    setUser(null);
  };

  return (
    <AuthContext.Provider value={{ user, login, logout, loading }}>
      {children}
    </AuthContext.Provider>
  );
}

export const useAuth = () => useContext(AuthContext);

// Usage
function Dashboard() {
  const { user, logout } = useAuth();
  
  return (
    <div>
      <h1>Welcome {user?.name}</h1>
      <button onClick={logout}>Logout</button>
    </div>
  );
}
```

**Spoken Answer**: "Store token in localStorage so it persists across refreshes. Create AuthContext with user state and login/logout functions. On mount, check for token and fetch user data. Wrap app with AuthProvider. Any component can use useAuth hook to access user data and auth functions."

---

### 62. You need to protect certain routes from unauthenticated users. How do you implement this?

**Problem**: Prevent unauthenticated users from accessing protected routes.

**Solution**: Create ProtectedRoute component that checks authentication.

**Code**:
```jsx
import { Navigate } from 'react-router-dom';
import { useAuth } from './AuthContext';

function ProtectedRoute({ children }) {
  const { user, loading } = useAuth();

  if (loading) return <div>Loading...</div>;
  
  if (!user) return <Navigate to="/login" replace />;
  
  return children;
}

// Usage
function App() {
  return (
    <Routes>
      <Route path="/login" element={<Login />} />
      <Route 
        path="/dashboard" 
        element={
          <ProtectedRoute>
            <Dashboard />
          </ProtectedRoute>
        } 
      />
      <Route 
        path="/profile" 
        element={
          <ProtectedRoute>
            <Profile />
          </ProtectedRoute>
        } 
      />
    </Routes>
  );
}

// Alternative: Layout wrapper
function ProtectedLayout() {
  const { user, loading } = useAuth();

  if (loading) return <div>Loading...</div>;
  if (!user) return <Navigate to="/login" />;

  return (
    <div>
      <Navbar />
      <Outlet /> {/* Renders child routes */}
    </div>
  );
}

// Usage with layout
function App() {
  return (
    <Routes>
      <Route path="/login" element={<Login />} />
      <Route element={<ProtectedLayout />}>
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/profile" element={<Profile />} />
      </Route>
    </Routes>
  );
}
```

**Spoken Answer**: "Create ProtectedRoute component that checks if user exists. If not authenticated, redirect to login. If loading, show loading state. Wrap protected routes with this component. Alternative: use layout wrapper with Outlet for nested routes. This keeps auth logic in one place."

---

### 63. How do you handle token refresh when it expires during user session?

**Problem**: Token expires, API calls fail with 401.

**Solution**: Use axios interceptors to refresh token automatically.

**Code**:
```jsx
import axios from 'axios';

const api = axios.create({
  baseURL: '/api'
});

// Add token to requests
api.interceptors.request.use((config) => {
  const token = localStorage.getItem('token');
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

// Handle token refresh on 401
api.interceptors.response.use(
  (response) => response,
  async (error) => {
    const originalRequest = error.config;

    if (error.response?.status === 401 && !originalRequest._retry) {
      originalRequest._retry = true;

      try {
        const refreshToken = localStorage.getItem('refreshToken');
        const res = await axios.post('/api/refresh', { refreshToken });
        
        const { token } = res.data;
        localStorage.setItem('token', token);
        
        originalRequest.headers.Authorization = `Bearer ${token}`;
        return api(originalRequest);
      } catch (err) {
        localStorage.removeItem('token');
        localStorage.removeItem('refreshToken');
        window.location.href = '/login';
        return Promise.reject(err);
      }
    }

    return Promise.reject(error);
  }
);

export default api;

// Usage
import api from './api';

function Component() {
  useEffect(() => {
    api.get('/users').then(res => console.log(res.data));
  }, []);
}
```

**Spoken Answer**: "Use axios interceptors. Add token to all requests in request interceptor. In response interceptor, catch 401 errors. Attempt to refresh token using refreshToken. If successful, retry original request with new token. If refresh fails, logout user. This handles token expiration transparently."

---

### 67. How do you handle unauthorized API responses (401) globally?

**Problem**: Need to logout user on any 401 response.

**Solution**: Use axios response interceptor.

**Code**:
```jsx
import axios from 'axios';

const api = axios.create({
  baseURL: '/api'
});

// Add token to all requests
api.interceptors.request.use((config) => {
  const token = localStorage.getItem('token');
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

// Handle 401 globally
api.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response?.status === 401) {
      localStorage.removeItem('token');
      window.location.href = '/login';
    }
    return Promise.reject(error);
  }
);

export default api;

// With Auth Context
import { useAuth } from './AuthContext';

function setupInterceptors(logout) {
  api.interceptors.response.use(
    (response) => response,
    (error) => {
      if (error.response?.status === 401) {
        logout();
      }
      return Promise.reject(error);
    }
  );
}

export function AuthProvider({ children }) {
  const [user, setUser] = useState(null);

  const logout = () => {
    localStorage.removeItem('token');
    setUser(null);
  };

  useEffect(() => {
    setupInterceptors(logout);
  }, []);

  return (
    <AuthContext.Provider value={{ user, logout }}>
      {children}
    </AuthContext.Provider>
  );
}
```

**Spoken Answer**: "Use axios response interceptor to catch all 401 errors. Clear token and redirect to login. Or call logout function from AuthContext. This handles unauthorized responses globally - one place to manage all 401s instead of checking in every component."

---

### 68. You need to show different UI based on user permissions. How do you implement this?

**Problem**: Show/hide features based on user permissions.

**Solution**: Create Can component or usePermission hook.

**Code**:
```jsx
import { useAuth } from './AuthContext';

// Can component
function Can({ permission, children }) {
  const { user } = useAuth();
  
  if (!user?.permissions?.includes(permission)) {
    return null;
  }
  
  return children;
}

// Usage
function Dashboard() {
  return (
    <div>
      <h1>Dashboard</h1>
      
      <Can permission="view_users">
        <UsersList />
      </Can>
      
      <Can permission="edit_settings">
        <button>Edit Settings</button>
      </Can>
      
      <Can permission="delete_posts">
        <button>Delete</button>
      </Can>
    </div>
  );
}

// Custom hook
function usePermission(permission) {
  const { user } = useAuth();
  return user?.permissions?.includes(permission) || false;
}

// Usage with hook
function Dashboard() {
  const canEdit = usePermission('edit_settings');
  const canDelete = usePermission('delete_posts');

  return (
    <div>
      {canEdit && <button>Edit</button>}
      {canDelete && <button>Delete</button>}
    </div>
  );
}

// Role-based
function hasRole(user, roles) {
  return roles.includes(user?.role);
}

function Dashboard() {
  const { user } = useAuth();

  return (
    <div>
      {hasRole(user, ['admin', 'manager']) && <AdminPanel />}
      {hasRole(user, ['admin']) && <button>Delete All</button>}
    </div>
  );
}
```

**Spoken Answer**: "Create a Can component that checks if user has the permission. Return null if they don't. Wrap protected UI with Can component. Or use usePermission hook that returns boolean. For role-based, check if user role is in allowed roles array. This keeps permission logic clean and reusable."

---

### 75. You need to redirect users based on authentication status. How do you structure this?

**Problem**: Redirect authenticated users away from login, unauthenticated to login.

**Solution**: Check auth status and redirect accordingly.

**Code**:
```jsx
import { Navigate } from 'react-router-dom';
import { useAuth } from './AuthContext';

// Redirect authenticated users away from login
function Login() {
  const { user, loading } = useAuth();

  if (loading) return <div>Loading...</div>;
  if (user) return <Navigate to="/dashboard" replace />;

  return (
    <form>
      {/* Login form */}
    </form>
  );
}

// Redirect to intended page after login
function Login() {
  const { login } = useAuth();
  const navigate = useNavigate();
  const location = useLocation();

  const handleLogin = async (email, password) => {
    await login(email, password);
    const from = location.state?.from?.pathname || '/dashboard';
    navigate(from, { replace: true });
  };

  return <form onSubmit={handleLogin}>...</form>;
}

// Protected route that remembers intended destination
function ProtectedRoute({ children }) {
  const { user, loading } = useAuth();
  const location = useLocation();

  if (loading) return <div>Loading...</div>;
  
  if (!user) {
    return <Navigate to="/login" state={{ from: location }} replace />;
  }
  
  return children;
}

// Role-based redirect
function Login() {
  const { login } = useAuth();
  const navigate = useNavigate();

  const handleLogin = async (email, password) => {
    const user = await login(email, password);
    
    if (user.role === 'admin') {
      navigate('/admin');
    } else if (user.role === 'manager') {
      navigate('/manager');
    } else {
      navigate('/dashboard');
    }
  };

  return <form onSubmit={handleLogin}>...</form>;
}
```

**Spoken Answer**: "In Login component, check if user exists. If yes, redirect to dashboard. In ProtectedRoute, if not authenticated, redirect to login with current location in state. After login, redirect to intended page or default dashboard. For role-based, check user role and navigate accordingly."

---

## Routing & Navigation (5 questions)

### 71. You need to prevent navigation if form has unsaved changes. How do you implement this?

**Problem**: User navigates away, loses unsaved form data.

**Solution**: Use beforeunload event and React Router's useBlocker.

**Code**:
```jsx
import { useEffect, useState } from 'react';
import { useBlocker } from 'react-router-dom';

function FormWithUnsavedChanges() {
  const [formData, setFormData] = useState({ name: '', email: '' });
  const [isDirty, setIsDirty] = useState(false);

  // Block navigation if form is dirty
  const blocker = useBlocker(
    ({ currentLocation, nextLocation }) =>
      isDirty && currentLocation.pathname !== nextLocation.pathname
  );

  // Warn on browser close/refresh
  useEffect(() => {
    const handleBeforeUnload = (e) => {
      if (isDirty) {
        e.preventDefault();
        e.returnValue = '';
      }
    };

    window.addEventListener('beforeunload', handleBeforeUnload);
    return () => window.removeEventListener('beforeunload', handleBeforeUnload);
  }, [isDirty]);

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
    setIsDirty(true);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    // Save data
    setIsDirty(false);
  };

  return (
    <div>
      <form onSubmit={handleSubmit}>
        <input name="name" value={formData.name} onChange={handleChange} />
        <input name="email" value={formData.email} onChange={handleChange} />
        <button type="submit">Save</button>
      </form>

      {blocker.state === 'blocked' && (
        <div className="modal">
          <p>You have unsaved changes. Are you sure you want to leave?</p>
          <button onClick={() => blocker.proceed()}>Leave</button>
          <button onClick={() => blocker.reset()}>Stay</button>
        </div>
      )}
    </div>
  );
}

// Custom hook
function useUnsavedChangesWarning(isDirty) {
  useEffect(() => {
    const handleBeforeUnload = (e) => {
      if (isDirty) {
        e.preventDefault();
        e.returnValue = '';
      }
    };

    window.addEventListener('beforeunload', handleBeforeUnload);
    return () => window.removeEventListener('beforeunload', handleBeforeUnload);
  }, [isDirty]);
}
```

**Spoken Answer**: "Use useBlocker from React Router to block navigation when form is dirty. Show confirmation modal. Use beforeunload event to warn on browser close or refresh. Track isDirty state - set true on change, false on save. This prevents accidental data loss."

---

### 74. How do you implement nested routing with shared layouts?

**Problem**: Multiple routes need same layout (header, sidebar).

**Solution**: Use Outlet for nested routes.

**Code**:
```jsx
import { Outlet } from 'react-router-dom';

// Layout component
function DashboardLayout() {
  return (
    <div>
      <header>Dashboard Header</header>
      <nav>
        <Link to="/dashboard">Overview</Link>
        <Link to="/dashboard/users">Users</Link>
        <Link to="/dashboard/settings">Settings</Link>
      </nav>
      <main>
        <Outlet /> {/* Child routes render here */}
      </main>
    </div>
  );
}

// Routes
function App() {
  return (
    <Routes>
      <Route path="/" element={<Home />} />
      
      {/* Nested routes with shared layout */}
      <Route path="/dashboard" element={<DashboardLayout />}>
        <Route index element={<Overview />} />
        <Route path="users" element={<Users />} />
        <Route path="settings" element={<Settings />} />
      </Route>
    </Routes>
  );
}

// Multiple nested levels
function App() {
  return (
    <Routes>
      <Route path="/" element={<RootLayout />}>
        <Route index element={<Home />} />
        
        <Route path="dashboard" element={<DashboardLayout />}>
          <Route index element={<Overview />} />
          <Route path="users" element={<UsersLayout />}>
            <Route index element={<UsersList />} />
            <Route path=":id" element={<UserDetail />} />
          </Route>
        </Route>
      </Route>
    </Routes>
  );
}
```

**Spoken Answer**: "Create layout component with Outlet. Outlet renders child routes. Define parent route with layout as element. Nest child routes inside. All children share the same layout. Can nest multiple levels. This keeps layout code DRY and organized."

---

### 76. How would you implement route-based code splitting?

**Problem**: All routes load on initial page load.

**Solution**: Use React.lazy for route components.

**Code**:
```jsx
import { lazy, Suspense } from 'react';

// Lazy load route components
const Home = lazy(() => import('./pages/Home'));
const Dashboard = lazy(() => import('./pages/Dashboard'));
const Profile = lazy(() => import('./pages/Profile'));
const Settings = lazy(() => import('./pages/Settings'));

function App() {
  return (
    <Suspense fallback={<div>Loading...</div>}>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/profile" element={<Profile />} />
        <Route path="/settings" element={<Settings />} />
      </Routes>
    </Suspense>
  );
}

// With loading component
function LoadingFallback() {
  return (
    <div className="loading">
      <Spinner />
      <p>Loading page...</p>
    </div>
  );
}

function App() {
  return (
    <Suspense fallback={<LoadingFallback />}>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/dashboard" element={<Dashboard />} />
      </Routes>
    </Suspense>
  );
}
```

**Spoken Answer**: "Use React.lazy to import route components dynamically. Wrap Routes with Suspense and provide fallback. Each route becomes a separate bundle that only loads when visited. User visits homepage, they only download homepage code. This dramatically reduces initial bundle size."

---

### 78. How do you handle 404 pages and invalid routes?

**Problem**: Need to show 404 for invalid URLs.

**Solution**: Use catch-all route with asterisk.

**Code**:
```jsx
function App() {
  return (
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/about" element={<About />} />
      <Route path="/users/:id" element={<UserDetail />} />
      
      {/* Catch-all route for 404 */}
      <Route path="*" element={<NotFound />} />
    </Routes>
  );
}

function NotFound() {
  const navigate = useNavigate();
  
  return (
    <div>
      <h1>404 - Page Not Found</h1>
      <p>The page you're looking for doesn't exist.</p>
      <button onClick={() => navigate('/')}>Go Home</button>
    </div>
  );
}

// With nested routes
function App() {
  return (
    <Routes>
      <Route path="/" element={<Home />} />
      
      <Route path="/dashboard" element={<DashboardLayout />}>
        <Route index element={<Overview />} />
        <Route path="users" element={<Users />} />
        {/* 404 for invalid dashboard routes */}
        <Route path="*" element={<DashboardNotFound />} />
      </Route>
      
      {/* Global 404 */}
      <Route path="*" element={<NotFound />} />
    </Routes>
  );
}
```

**Spoken Answer**: "Use asterisk path='*' as the last route. It catches all unmatched URLs. Show 404 page with link to go home. Can have nested 404s for different sections. Always put catch-all route last so it doesn't override valid routes."

---

### 79. You need to implement route guards that check permissions. How do you structure this?

**Problem**: Some routes require specific permissions or roles.

**Solution**: Create permission-based route guard.

**Code**:
```jsx
import { Navigate } from 'react-router-dom';
import { useAuth } from './AuthContext';

function PermissionRoute({ children, requiredPermission }) {
  const { user, loading } = useAuth();

  if (loading) return <div>Loading...</div>;
  if (!user) return <Navigate to="/login" />;
  
  if (!user.permissions?.includes(requiredPermission)) {
    return <Navigate to="/unauthorized" />;
  }
  
  return children;
}

// Usage
function App() {
  return (
    <Routes>
      <Route path="/login" element={<Login />} />
      
      <Route 
        path="/admin" 
        element={
          <PermissionRoute requiredPermission="admin_access">
            <AdminPanel />
          </PermissionRoute>
        } 
      />
      
      <Route 
        path="/users/delete" 
        element={
          <PermissionRoute requiredPermission="delete_users">
            <DeleteUsers />
          </PermissionRoute>
        } 
      />
    </Routes>
  );
}

// Role-based guard
function RoleRoute({ children, allowedRoles }) {
  const { user, loading } = useAuth();

  if (loading) return <div>Loading...</div>;
  if (!user) return <Navigate to="/login" />;
  
  if (!allowedRoles.includes(user.role)) {
    return <Navigate to="/unauthorized" />;
  }
  
  return children;
}

// Usage
function App() {
  return (
    <Routes>
      <Route 
        path="/admin" 
        element={
          <RoleRoute allowedRoles={['admin']}>
            <AdminPanel />
          </RoleRoute>
        } 
      />
      
      <Route 
        path="/dashboard" 
        element={
          <RoleRoute allowedRoles={['user', 'admin', 'manager']}>
            <Dashboard />
          </RoleRoute>
        } 
      />
    </Routes>
  );
}
```

**Spoken Answer**: "Create PermissionRoute component that checks if user has required permission. If not, redirect to unauthorized page. Pass requiredPermission as prop. For role-based, check if user role is in allowedRoles array. This centralizes permission logic and keeps routes clean."

---

## Testing Scenarios (5 questions)

### 81. How would you test a component that makes API calls?

**Problem**: Need to test component without making real API calls.

**Solution**: Mock fetch with jest.

**Code**:
```jsx
// Component
function UserProfile({ userId }) {
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetch(`/api/users/${userId}`)
      .then(res => res.json())
      .then(data => {
        setUser(data);
        setLoading(false);
      });
  }, [userId]);

  if (loading) return <div>Loading...</div>;
  return <div>{user.name}</div>;
}

// Test
import { render, screen, waitFor } from '@testing-library/react';

test('displays user data after loading', async () => {
  global.fetch = jest.fn(() =>
    Promise.resolve({
      json: () => Promise.resolve({ name: 'John Doe' })
    })
  );

  render(<UserProfile userId={1} />);

  expect(screen.getByText('Loading...')).toBeInTheDocument();

  await waitFor(() => {
    expect(screen.getByText('John Doe')).toBeInTheDocument();
  });

  expect(fetch).toHaveBeenCalledWith('/api/users/1');
});
```

**Spoken Answer**: "Mock fetch with jest.fn(). Return a Promise that resolves with mock data. Use waitFor to wait for async updates. Check loading state first, then check final rendered data. Verify fetch was called with correct URL."

---

### 82. You need to test a component that uses Context. How do you set this up?

**Solution**: Wrap component with Context Provider in test.

**Code**:
```jsx
// Component
function UserGreeting() {
  const { user } = useAuth();
  return <div>Hello {user.name}</div>;
}

// Test
import { render, screen } from '@testing-library/react';

test('displays user name from context', () => {
  const mockUser = { name: 'John' };

  render(
    <AuthContext.Provider value={{ user: mockUser }}>
      <UserGreeting />
    </AuthContext.Provider>
  );

  expect(screen.getByText('Hello John')).toBeInTheDocument();
});

// Helper function for reusability
function renderWithAuth(component, user) {
  return render(
    <AuthContext.Provider value={{ user }}>
      {component}
    </AuthContext.Provider>
  );
}

test('with helper', () => {
  renderWithAuth(<UserGreeting />, { name: 'John' });
  expect(screen.getByText('Hello John')).toBeInTheDocument();
});
```

**Spoken Answer**: "Wrap component with Context Provider in render. Pass mock values to Provider. Create helper function to avoid repeating Provider setup in every test."

---

### 83. How do you test custom hooks in isolation?

**Solution**: Use renderHook from testing library.

**Code**:
```jsx
// Hook
function useCounter(initialValue = 0) {
  const [count, setCount] = useState(initialValue);
  const increment = () => setCount(c => c + 1);
  const decrement = () => setCount(c => c - 1);
  return { count, increment, decrement };
}

// Test
import { renderHook, act } from '@testing-library/react';

test('increments counter', () => {
  const { result } = renderHook(() => useCounter(0));

  expect(result.current.count).toBe(0);

  act(() => {
    result.current.increment();
  });

  expect(result.current.count).toBe(1);
});

test('starts with initial value', () => {
  const { result } = renderHook(() => useCounter(10));
  expect(result.current.count).toBe(10);
});
```

**Spoken Answer**: "Use renderHook to test hooks in isolation. Wrap state updates in act(). Access hook return value with result.current. Test initial state and state updates separately."

---

### 84. You need to test user interactions like clicks and form submissions. What's your approach?

**Solution**: Use fireEvent or userEvent.

**Code**:
```jsx
// Component
function LoginForm({ onSubmit }) {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    onSubmit({ email, password });
  };

  return (
    <form onSubmit={handleSubmit}>
      <input value={email} onChange={(e) => setEmail(e.target.value)} placeholder="Email" />
      <input value={password} onChange={(e) => setPassword(e.target.value)} placeholder="Password" type="password" />
      <button type="submit">Login</button>
    </form>
  );
}

// Test
import { render, screen } from '@testing-library/react';
import userEvent from '@testing-library/user-event';

test('submits form with user input', async () => {
  const mockSubmit = jest.fn();
  const user = userEvent.setup();

  render(<LoginForm onSubmit={mockSubmit} />);

  await user.type(screen.getByPlaceholderText('Email'), 'test@example.com');
  await user.type(screen.getByPlaceholderText('Password'), 'password123');
  await user.click(screen.getByText('Login'));

  expect(mockSubmit).toHaveBeenCalledWith({
    email: 'test@example.com',
    password: 'password123'
  });
});
```

**Spoken Answer**: "Use userEvent for realistic user interactions. Type into inputs with user.type(). Click buttons with user.click(). Mock callback functions with jest.fn(). Verify callbacks were called with correct arguments."

---

### 89. How would you test a component with complex state logic?

**Solution**: Test state transitions and edge cases.

**Code**:
```jsx
// Component
function TodoList() {
  const [todos, setTodos] = useState([]);
  const [input, setInput] = useState('');

  const addTodo = () => {
    if (input.trim()) {
      setTodos([...todos, { id: Date.now(), text: input, done: false }]);
      setInput('');
    }
  };

  const toggleTodo = (id) => {
    setTodos(todos.map(t => t.id === id ? { ...t, done: !t.done } : t));
  };

  return (
    <div>
      <input value={input} onChange={(e) => setInput(e.target.value)} />
      <button onClick={addTodo}>Add</button>
      {todos.map(todo => (
        <div key={todo.id}>
          <span style={{ textDecoration: todo.done ? 'line-through' : 'none' }}>
            {todo.text}
          </span>
          <button onClick={() => toggleTodo(todo.id)}>Toggle</button>
        </div>
      ))}
    </div>
  );
}

// Test
import { render, screen } from '@testing-library/react';
import userEvent from '@testing-library/user-event';

test('adds and toggles todos', async () => {
  const user = userEvent.setup();
  render(<TodoList />);

  const input = screen.getByRole('textbox');
  const addButton = screen.getByText('Add');

  await user.type(input, 'Buy milk');
  await user.click(addButton);

  expect(screen.getByText('Buy milk')).toBeInTheDocument();
  expect(input).toHaveValue('');

  await user.click(screen.getByText('Toggle'));
  expect(screen.getByText('Buy milk')).toHaveStyle({ textDecoration: 'line-through' });
});

test('does not add empty todos', async () => {
  const user = userEvent.setup();
  render(<TodoList />);

  await user.click(screen.getByText('Add'));
  expect(screen.queryByRole('listitem')).not.toBeInTheDocument();
});
```

**Spoken Answer**: "Test each state transition separately. Test adding items, toggling items, edge cases like empty input. Use queryBy for elements that shouldn't exist. Check styles and attributes change correctly."


## Event Handling & User Interactions (4 questions)

### 43. How would you implement drag-and-drop functionality in React?

**Solution**: Use HTML5 drag events or react-beautiful-dnd library.

**Code**:
```jsx
// Native HTML5
function DragDropList() {
  const [items, setItems] = useState(['Item 1', 'Item 2', 'Item 3']);
  const [draggedIndex, setDraggedIndex] = useState(null);

  const handleDragStart = (index) => {
    setDraggedIndex(index);
  };

  const handleDragOver = (e) => {
    e.preventDefault();
  };

  const handleDrop = (dropIndex) => {
    const newItems = [...items];
    const draggedItem = newItems[draggedIndex];
    newItems.splice(draggedIndex, 1);
    newItems.splice(dropIndex, 0, draggedItem);
    setItems(newItems);
    setDraggedIndex(null);
  };

  return (
    <div>
      {items.map((item, index) => (
        <div
          key={index}
          draggable
          onDragStart={() => handleDragStart(index)}
          onDragOver={handleDragOver}
          onDrop={() => handleDrop(index)}
          style={{ padding: '10px', margin: '5px', background: '#f0f0f0' }}
        >
          {item}
        </div>
      ))}
    </div>
  );
}
```

**Spoken Answer**: "Use draggable attribute and drag events. Track dragged index on dragStart. Prevent default on dragOver. On drop, splice array to reorder items. Or use react-beautiful-dnd library for better UX with animations."

---

### 45. How do you prevent multiple form submissions when user clicks submit button repeatedly?

**Solution**: Disable button while submitting.

**Code**:
```jsx
function Form() {
  const [submitting, setSubmitting] = useState(false);
  const [formData, setFormData] = useState({ name: '', email: '' });

  const handleSubmit = async (e) => {
    e.preventDefault();
    
    if (submitting) return;
    
    setSubmitting(true);

    try {
      await fetch('/api/submit', {
        method: 'POST',
        body: JSON.stringify(formData),
        headers: { 'Content-Type': 'application/json' }
      });
      alert('Success!');
    } catch (error) {
      alert('Error!');
    } finally {
      setSubmitting(false);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <input value={formData.name} onChange={(e) => setFormData({ ...formData, name: e.target.value })} disabled={submitting} />
      <button type="submit" disabled={submitting}>
        {submitting ? 'Submitting...' : 'Submit'}
      </button>
    </form>
  );
}
```

**Spoken Answer**: "Use submitting state flag. Check flag at start of handler and return early if true. Disable button while submitting. Reset flag in finally block so it always resets even on error."

---

### 48. How do you handle file uploads with progress tracking in React?

**Solution**: Use XMLHttpRequest with progress event.

**Code**:
```jsx
function FileUpload() {
  const [file, setFile] = useState(null);
  const [progress, setProgress] = useState(0);
  const [uploading, setUploading] = useState(false);

  const handleUpload = () => {
    if (!file) return;

    const formData = new FormData();
    formData.append('file', file);

    const xhr = new XMLHttpRequest();

    xhr.upload.addEventListener('progress', (e) => {
      if (e.lengthComputable) {
        const percentComplete = (e.loaded / e.total) * 100;
        setProgress(percentComplete);
      }
    });

    xhr.addEventListener('load', () => {
      setUploading(false);
      alert('Upload complete!');
    });

    xhr.open('POST', '/api/upload');
    xhr.send(formData);
    setUploading(true);
  };

  return (
    <div>
      <input type="file" onChange={(e) => setFile(e.target.files[0])} disabled={uploading} />
      <button onClick={handleUpload} disabled={!file || uploading}>Upload</button>
      
      {uploading && (
        <div>
          <div style={{ width: `${progress}%`, height: '20px', background: 'blue' }} />
          <p>{Math.round(progress)}%</p>
        </div>
      )}
    </div>
  );
}
```

**Spoken Answer**: "Use XMLHttpRequest with upload.addEventListener for progress. Calculate percentage: loaded divided by total times 100. Update progress state. Show progress bar. Or use axios with onUploadProgress callback."

---

### 49. How would you implement click-outside detection for a dropdown menu?

**Solution**: Use ref and mousedown event listener.

**Code**:
```jsx
function Dropdown() {
  const [isOpen, setIsOpen] = useState(false);
  const dropdownRef = useRef(null);

  useEffect(() => {
    const handleClickOutside = (event) => {
      if (dropdownRef.current && !dropdownRef.current.contains(event.target)) {
        setIsOpen(false);
      }
    };

    document.addEventListener('mousedown', handleClickOutside);
    return () => document.removeEventListener('mousedown', handleClickOutside);
  }, []);

  return (
    <div ref={dropdownRef}>
      <button onClick={() => setIsOpen(!isOpen)}>Toggle</button>
      {isOpen && (
        <ul>
          <li>Option 1</li>
          <li>Option 2</li>
        </ul>
      )}
    </div>
  );
}
```

**Spoken Answer**: "Use ref on dropdown container. Listen for mousedown on document. Check if click target is inside ref using contains(). If outside, close dropdown. Cleanup listener on unmount."

---

## Error Handling & Debugging (3 questions)

### 101. Your production app crashes but works in development. How do you debug this?

**Solution**: Check for common production-only issues.

**Checklist**:
```
1. Check browser console for errors
2. Enable source maps in production
3. Check for environment-specific code
4. Verify API URLs are correct for production
5. Check for missing environment variables
6. Look for minification issues
7. Test production build locally
8. Check for CORS issues
9. Verify all dependencies are in package.json
10. Check Error Boundary logs
```

**Code**:
```jsx
// Add error logging
class ErrorBoundary extends Component {
  componentDidCatch(error, errorInfo) {
    // Log to error tracking service
    console.error('Error:', error);
    console.error('Error Info:', errorInfo);
    
    // Send to monitoring service
    if (process.env.NODE_ENV === 'production') {
      logErrorToService(error, errorInfo);
    }
  }
}

// Test production build locally
// npm run build
// npx serve -s build
```

**Spoken Answer**: "Check browser console first. Enable source maps. Test production build locally with serve. Check for environment-specific code. Verify API URLs and env variables. Add error logging to Error Boundary. Use error monitoring service like Sentry."

---

### 102. You need to implement global error handling for all API calls. How do you structure this?

**Solution**: Use axios interceptors.

**Code**:
```jsx
import axios from 'axios';

const api = axios.create({ baseURL: '/api' });

api.interceptors.response.use(
  (response) => response,
  (error) => {
    const message = error.response?.data?.message || 'An error occurred';
    
    // Show toast notification
    toast.error(message);
    
    // Log to monitoring service
    console.error('API Error:', error);
    
    // Handle specific status codes
    if (error.response?.status === 401) {
      window.location.href = '/login';
    }
    
    return Promise.reject(error);
  }
);

export default api;
```

**Spoken Answer**: "Use axios response interceptor to catch all errors. Show user-friendly error message with toast. Log errors to monitoring service. Handle specific status codes like 401 for logout. Return rejected promise so components can still handle errors if needed."

---

### 109. How do you debug why a component is re-rendering unnecessarily?

**Solution**: Use React DevTools Profiler and console logs.

**Code**:
```jsx
// Add logging
function Component({ prop1, prop2 }) {
  console.log('Component rendered');
  
  useEffect(() => {
    console.log('prop1 changed:', prop1);
  }, [prop1]);
  
  useEffect(() => {
    console.log('prop2 changed:', prop2);
  }, [prop2]);
  
  return <div>Content</div>;
}

// Custom hook to track renders
function useWhyDidYouUpdate(name, props) {
  const previousProps = useRef();

  useEffect(() => {
    if (previousProps.current) {
      const allKeys = Object.keys({ ...previousProps.current, ...props });
      const changedProps = {};
      
      allKeys.forEach(key => {
        if (previousProps.current[key] !== props[key]) {
          changedProps[key] = {
            from: previousProps.current[key],
            to: props[key]
          };
        }
      });

      if (Object.keys(changedProps).length > 0) {
        console.log('[why-did-you-update]', name, changedProps);
      }
    }

    previousProps.current = props;
  });
}

// Usage
function Component(props) {
  useWhyDidYouUpdate('Component', props);
  return <div>Content</div>;
}
```

**Spoken Answer**: "Use React DevTools Profiler to record renders. Look for components that render frequently. Add console logs to track which props change. Create useWhyDidYouUpdate hook to see exactly which props changed. Check if functions are recreated on every render - wrap with useCallback."

---

## Advanced Patterns (2 questions)

### 111. How would you implement a compound component pattern?

**Solution**: Create parent component with child components as properties.

**Code**:
```jsx
function Card({ children }) {
  return <div className="card">{children}</div>;
}

Card.Header = ({ children }) => <div className="card-header">{children}</div>;
Card.Body = ({ children }) => <div className="card-body">{children}</div>;
Card.Footer = ({ children }) => <div className="card-footer">{children}</div>;

// Usage
function App() {
  return (
    <Card>
      <Card.Header>Title</Card.Header>
      <Card.Body>Content goes here</Card.Body>
      <Card.Footer>Footer text</Card.Footer>
    </Card>
  );
}
```

**Spoken Answer**: "Create parent component. Add child components as properties on parent. Each child handles its own styling and logic. Users compose components together. This gives flexibility while maintaining consistency."

---

### 112. You need to share logic between multiple components. What patterns would you use?

**Solution**: Custom hooks, HOCs, or render props.

**Code**:
```jsx
// 1. Custom Hook (Best)
function useWindowSize() {
  const [size, setSize] = useState({ width: window.innerWidth, height: window.innerHeight });

  useEffect(() => {
    const handleResize = () => {
      setSize({ width: window.innerWidth, height: window.innerHeight });
    };
    window.addEventListener('resize', handleResize);
    return () => window.removeEventListener('resize', handleResize);
  }, []);

  return size;
}

// Usage
function ComponentA() {
  const { width } = useWindowSize();
  return <div>Width: {width}</div>;
}

// 2. HOC
function withWindowSize(Component) {
  return function WrappedComponent(props) {
    const size = useWindowSize();
    return <Component {...props} windowSize={size} />;
  };
}

// 3. Render Props
function WindowSize({ children }) {
  const size = useWindowSize();
  return children(size);
}

// Usage
<WindowSize>
  {({ width }) => <div>Width: {width}</div>}
</WindowSize>
```

**Spoken Answer**: "Custom hooks are best for sharing logic. Extract logic into hook, return values. Any component can use the hook. HOCs wrap components with shared logic. Render props pass data through function children. Hooks are cleanest and most flexible."

---

## Mobile & Responsive (2 questions)

### 121. How do you handle responsive design in React components?

**Solution**: Use CSS media queries, window size hook, or CSS-in-JS.

**Code**:
```jsx
// 1. CSS Media Queries
function Component() {
  return <div className="responsive-container">Content</div>;
}

// styles.css
.responsive-container {
  padding: 20px;
}

@media (max-width: 768px) {
  .responsive-container {
    padding: 10px;
  }
}

// 2. Window Size Hook
function useWindowSize() {
  const [windowSize, setWindowSize] = useState({
    width: window.innerWidth,
    height: window.innerHeight
  });

  useEffect(() => {
    const handleResize = () => {
      setWindowSize({ width: window.innerWidth, height: window.innerHeight });
    };
    window.addEventListener('resize', handleResize);
    return () => window.removeEventListener('resize', handleResize);
  }, []);

  return windowSize;
}

function Component() {
  const { width } = useWindowSize();
  const isMobile = width < 768;

  return (
    <div style={{ padding: isMobile ? '10px' : '20px' }}>
      Content
    </div>
  );
}
```

**Spoken Answer**: "Use CSS media queries for styling. Use window size hook for conditional rendering. Check width to determine if mobile. Apply different styles or render different components based on screen size."

---

### 122. You need to render different components for mobile vs desktop. How do you implement this?

**Solution**: Check window width and render conditionally.

**Code**:
```jsx
function useIsMobile() {
  const [isMobile, setIsMobile] = useState(window.innerWidth < 768);

  useEffect(() => {
    const handleResize = () => {
      setIsMobile(window.innerWidth < 768);
    };
    window.addEventListener('resize', handleResize);
    return () => window.removeEventListener('resize', handleResize);
  }, []);

  return isMobile;
}

function App() {
  const isMobile = useIsMobile();

  return (
    <div>
      {isMobile ? <MobileNav /> : <DesktopNav />}
      {isMobile ? <MobileContent /> : <DesktopContent />}
    </div>
  );
}
```

**Spoken Answer**: "Create useIsMobile hook that checks window width. Return boolean. Conditionally render mobile or desktop components. Listen for resize events to update on screen rotation or window resize."

---
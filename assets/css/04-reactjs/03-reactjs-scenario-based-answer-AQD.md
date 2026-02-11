# ReactJS Performance Optimization - Questions 1-10 Solutions

## Question 1: Your component fetches data from an API and re-renders multiple times unnecessarily. How do you optimize it?

**Problem**: Unnecessary re-renders causing performance issues.

**Solution**:
```jsx
import { useState, useEffect } from 'react';

function DataComponent() {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    let isMounted = true;
    
    async function fetchData() {
      const response = await fetch('/api/data');
      const result = await response.json();
      
      if (isMounted) {
        setData(result);
        setLoading(false);
      }
    }
    
    fetchData();
    return () => { isMounted = false; };
  }, []); // Empty dependency array - runs once

  if (loading) return <div>Loading...</div>;
  return <div>{data.name}</div>;
}
```

**Key Points**:
- Use empty dependency array `[]` to fetch only once
- Add cleanup flag to prevent state updates after unmount
- Combine multiple state updates into single setState call

---

## Question 2: You have a parent component with multiple children. Updating parent state causes all children to re-render. How do you prevent it?

**Problem**: All child components re-render when parent state changes.

**Solution**:
```jsx
import { memo } from 'react';

// Wrap child components with memo
const ChildComponent = memo(({ name, onClick }) => {
  console.log('Child rendered:', name);
  return <button onClick={onClick}>{name}</button>;
});

function ParentComponent() {
  const [count, setCount] = useState(0);
  const [text, setText] = useState('');

  // Memoize callbacks
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

**Key Points**:
- Use `React.memo()` to prevent re-renders when props don't change
- Use `useCallback()` for function props
- Split state into separate components if needed

---

## Question 3: Your app displays a **large list of items**, and performance drops when interacting with other components. How do you optimize rendering?

**Problem**: Rendering large lists slows down the entire app.

**Solution**:
```jsx
import { memo } from 'react';

// Memoize list items
const ListItem = memo(({ item }) => {
  return <div className="item">{item.name}</div>;
});

function LargeList({ items }) {
  return (
    <div>
      {items.map(item => (
        <ListItem key={item.id} item={item} />
      ))}
    </div>
  );
}
```

**Better Solution with Virtualization**:
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

**Key Points**:
- Use `react-window` or `react-virtualized` for large lists
- Only render visible items
- Use `memo()` for list items

---

## Question 4: How do you optimize performance for **re-rendering charts or data-heavy visualizations** in React?

**Problem**: Charts re-render on every parent update, causing lag.

**Solution**:
```jsx
import { memo, useMemo } from 'react';
import { LineChart, Line, XAxis, YAxis } from 'recharts';

// Memoize the chart component
const Chart = memo(({ data, width, height }) => {
  console.log('Chart rendered');
  
  return (
    <LineChart width={width} height={height} data={data}>
      <XAxis dataKey="name" />
      <YAxis />
      <Line type="monotone" dataKey="value" stroke="#8884d8" />
    </LineChart>
  );
});

function Dashboard() {
  const [filter, setFilter] = useState('');
  const [rawData, setRawData] = useState([]);

  // Memoize processed data
  const chartData = useMemo(() => {
    return rawData.map(item => ({
      name: item.label,
      value: item.count
    }));
  }, [rawData]);

  return (
    <div>
      <input value={filter} onChange={(e) => setFilter(e.target.value)} />
      <Chart data={chartData} width={600} height={300} />
    </div>
  );
}
```

**Advanced - Debounce Updates**:
```jsx
import { useState, useEffect, useMemo, memo } from 'react';

const Chart = memo(({ data }) => (
  <div className="chart">
    {/* Your chart library component */}
  </div>
));

function DataVisualization({ liveData }) {
  const [debouncedData, setDebouncedData] = useState(liveData);

  // Debounce rapid data updates
  useEffect(() => {
    const timer = setTimeout(() => {
      setDebouncedData(liveData);
    }, 300);

    return () => clearTimeout(timer);
  }, [liveData]);

  return <Chart data={debouncedData} />;
}
```

**Key Points**:
- Use `memo()` to prevent unnecessary chart re-renders
- Use `useMemo()` for data transformations
- Debounce rapid data updates (300ms)
- Consider using canvas-based charts for large datasets (Chart.js, D3)
- Update only changed data points, not entire dataset

## Question 5: A component renders a list of 10,000 items causing lag. What techniques would you use?

**Problem**: Massive list causing browser lag.

**Solution**:
```jsx
import { FixedSizeList } from 'react-window';

function MassiveList({ data }) {
  const Row = ({ index, style }) => (
    <div style={style} className="row">
      <span>{data[index].id}</span>
      <span>{data[index].name}</span>
    </div>
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
```

**Alternative - Pagination**:
```jsx
function PaginatedList({ data }) {
  const [page, setPage] = useState(1);
  const itemsPerPage = 50;
  
  const currentItems = data.slice(
    (page - 1) * itemsPerPage,
    page * itemsPerPage
  );

  return (
    <div>
      {currentItems.map(item => (
        <div key={item.id}>{item.name}</div>
      ))}
      <button onClick={() => setPage(page - 1)} disabled={page === 1}>
        Previous
      </button>
      <button onClick={() => setPage(page + 1)}>Next</button>
    </div>
  );
}
```

**Key Points**:
- Virtual scrolling for continuous lists
- Pagination for discrete pages
- Lazy loading for infinite scroll

---

## Question 6: Your app has expensive calculations in render. How do you prevent recalculating on every render?

**Problem**: Heavy computations run on every render.

**Solution**:
```jsx
import { useMemo } from 'react';

function ExpensiveComponent({ items, filter }) {
  // Without useMemo - runs every render
  // const filtered = items.filter(item => item.category === filter);
  
  // With useMemo - only recalculates when dependencies change
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
      {filteredItems.map(item => (
        <div key={item.id}>{item.name}</div>
      ))}
    </div>
  );
}
```

**Key Points**:
- Use `useMemo()` for expensive calculations
- Only recalculates when dependencies change
- Don't overuse - only for truly expensive operations

---

## Question 7: Multiple components subscribe to the same context, but only one value changes. How do you prevent unnecessary re-renders?

**Problem**: All context consumers re-render when any value changes.

**Solution**:
```jsx
import { createContext, useContext, useMemo } from 'react';

// Split contexts by update frequency
const UserContext = createContext();
const ThemeContext = createContext();

function AppProvider({ children }) {
  const [user, setUser] = useState(null);
  const [theme, setTheme] = useState('light');

  // Memoize context values
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
  return <button onClick={() => setTheme(theme === 'light' ? 'dark' : 'light')}>
    Toggle Theme
  </button>;
}
```

**Key Points**:
- Split context by update frequency
- Memoize context values
- Use context selectors or state management library

---

## Question 8: How would you optimize a component that renders based on window scroll position?

**Problem**: Scroll events fire rapidly causing performance issues.

**Solution**:
```jsx
import { useState, useEffect } from 'react';

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

**With Throttle**:
```jsx
import { useState, useEffect } from 'react';

function throttle(func, delay) {
  let lastCall = 0;
  return (...args) => {
    const now = Date.now();
    if (now - lastCall >= delay) {
      lastCall = now;
      func(...args);
    }
  };
}

function ScrollComponent() {
  const [scrollY, setScrollY] = useState(0);

  useEffect(() => {
    const handleScroll = throttle(() => {
      setScrollY(window.scrollY);
    }, 100);

    window.addEventListener('scroll', handleScroll, { passive: true });
    return () => window.removeEventListener('scroll', handleScroll);
  }, []);

  return <div>Scroll Position: {scrollY}</div>;
}
```

**Key Points**:
- Use `requestAnimationFrame` for smooth updates
- Throttle scroll events (100-200ms)
- Use `passive: true` for better performance

---

## Question 8: Slow initial load time

**Problem**: App takes too long to load initially.

**Solution**:
```jsx
import { lazy, Suspense } from 'react';

// Code splitting with lazy loading
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
```

**Additional Optimizations**:
```jsx
// Preload critical routes
import { useEffect } from 'react';

function HomePage() {
  useEffect(() => {
    // Preload dashboard on hover
    const link = document.querySelector('a[href="/dashboard"]');
    link?.addEventListener('mouseenter', () => {
      import('./Dashboard');
    });
  }, []);

  return <div>Home Page</div>;
}
```

**Key Points**:
- Use `React.lazy()` and `Suspense` for code splitting
- Split routes into separate bundles
- Preload critical resources
- Optimize images (WebP, lazy loading)
- Use CDN for static assets
- Enable gzip/brotli compression

---

## Question 9: Your React app has slow initial load time. What strategies would you implement?

**Problem**: Need to find which components are slow.

**Solution**:
```jsx
// 1. Use React DevTools Profiler
// Open React DevTools > Profiler tab > Record

// 2. Add custom profiling
import { Profiler } from 'react';

function onRenderCallback(
  id,
  phase,
  actualDuration,
  baseDuration,
  startTime,
  commitTime
) {
  console.log(`${id} (${phase}) took ${actualDuration}ms`);
}

function App() {
  return (
    <Profiler id="App" onRender={onRenderCallback}>
      <Dashboard />
    </Profiler>
  );
}

// 3. Custom hook for component timing
function useRenderCount(componentName) {
  const renderCount = useRef(0);
  
  useEffect(() => {
    renderCount.current += 1;
    console.log(`${componentName} rendered ${renderCount.current} times`);
  });
}

function MyComponent() {
  useRenderCount('MyComponent');
  return <div>Content</div>;
}
```

**Key Points**:
- Use React DevTools Profiler
- Use `Profiler` component for custom metrics
- Check for unnecessary re-renders
- Monitor bundle size with webpack-bundle-analyzer

---

## Question 10: How do you identify which components are causing performance bottlenecks?

**Problem**: App is slow but you don't know which components are the culprits.

**Solution**:

**1. React DevTools Profiler (Primary Tool)**:
```jsx
// Steps:
// 1. Install React DevTools browser extension
// 2. Open DevTools > Profiler tab
// 3. Click Record button
// 4. Interact with your app
// 5. Stop recording
// 6. Look for:
//    - Components with long render times (yellow/red bars)
//    - Components that render frequently
//    - Flame graph shows render hierarchy
```

**2. Built-in Profiler Component**:
```jsx
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
```

**3. Custom Render Counter Hook**:
```jsx
import { useEffect, useRef } from 'react';

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

// Usage
function ExpensiveComponent({ data, filter, onUpdate }) {
  useWhyDidYouRender('ExpensiveComponent', { data, filter, onUpdate });
  return <div>...</div>;
}
```

**4. Simple Console Logging**:
```jsx
function MyComponent({ data }) {
  console.log('MyComponent rendered');
  
  useEffect(() => {
    console.log('MyComponent mounted/updated');
  });
  
  return <div>{data}</div>;
}
```

**5. Performance Timing**:
```jsx
function SlowComponent() {
  const startTime = performance.now();
  
  // Your component logic
  
  useEffect(() => {
    const endTime = performance.now();
    console.log(`Render took ${endTime - startTime}ms`);
  });
  
  return <div>Content</div>;
}
```

# ReactJS State Management - Questions 11-22 Solutions

## Question 11: How would you manage a **multi-step form** where users can navigate back and forth without losing data?

**Problem**: Users need to navigate between form steps without losing entered data.

**Solution**:
```jsx
import { useState } from 'react';

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

  const nextStep = () => setStep(prev => prev + 1);
  const prevStep = () => setStep(prev => prev - 1);

  return (
    <div>
      {step === 1 && (
        <div>
          <input 
            value={formData.name} 
            onChange={(e) => updateField('name', e.target.value)} 
            placeholder="Name"
          />
          <input 
            value={formData.email} 
            onChange={(e) => updateField('email', e.target.value)} 
            placeholder="Email"
          />
          <button onClick={nextStep}>Next</button>
        </div>
      )}
      
      {step === 2 && (
        <div>
          <input 
            value={formData.address} 
            onChange={(e) => updateField('address', e.target.value)} 
            placeholder="Address"
          />
          <button onClick={prevStep}>Back</button>
          <button onClick={nextStep}>Next</button>
        </div>
      )}
      
      {step === 3 && (
        <div>
          <input 
            value={formData.payment} 
            onChange={(e) => updateField('payment', e.target.value)} 
            placeholder="Payment"
          />
          <button onClick={prevStep}>Back</button>
          <button onClick={() => console.log('Submit:', formData)}>Submit</button>
        </div>
      )}
    </div>
  );
}
```

**Key Points**:
- Single state object holds all form data
- Step number tracks current position
- Data persists across step changes
- Can add localStorage for persistence across refreshes

---

## Question 12: A child component deep in the component tree needs to update global state. How do you achieve this without prop drilling?

**Problem**: Passing props through many levels is cumbersome.

**Solution**:
```jsx
import { createContext, useContext, useState } from 'react';

const AppContext = createContext();

function AppProvider({ children }) {
  const [user, setUser] = useState(null);
  
  return (
    <AppContext.Provider value={{ user, setUser }}>
      {children}
    </AppContext.Provider>
  );
}

// Deep nested component
function DeepChildComponent() {
  const { user, setUser } = useContext(AppContext);
  
  return (
    <button onClick={() => setUser({ name: 'John' })}>
      Update User
    </button>
  );
}

function App() {
  return (
    <AppProvider>
      <Parent>
        <Child>
          <DeepChildComponent />
        </Child>
      </Parent>
    </AppProvider>
  );
}
```

**Key Points**:
- Use Context API for global state
- No need to pass props through intermediate components
- Any component can access and update state

---

## Question 13: Your app has shared state, some frequently updated, some rarely. How do you decide between **Context API, Redux, or local state**?

**Problem**: Choosing the right state management solution.

**Decision Guide**:

```jsx
// LOCAL STATE - Use when:
// - State only used in one component
// - Simple UI state (toggles, form inputs)
function Counter() {
  const [count, setCount] = useState(0);
  return <button onClick={() => setCount(count + 1)}>{count}</button>;
}

// CONTEXT API - Use when:
// - State shared across multiple components
// - Not frequently updated
// - Theme, user auth, language settings
const ThemeContext = createContext();

function App() {
  const [theme, setTheme] = useState('light');
  return (
    <ThemeContext.Provider value={{ theme, setTheme }}>
      <Header />
      <Content />
    </ThemeContext.Provider>
  );
}

// REDUX/ZUSTAND - Use when:
// - Complex state logic
// - Frequently updated shared state
// - Need middleware (logging, async)
// - Large applications with many state interactions
import { create } from 'zustand';

const useStore = create((set) => ({
  items: [],
  addItem: (item) => set((state) => ({ items: [...state.items, item] })),
  removeItem: (id) => set((state) => ({ 
    items: state.items.filter(i => i.id !== id) 
  }))
}));
```

**Decision Matrix**:
- **Local State**: Component-specific, simple
- **Context API**: Shared, rarely updated (theme, auth)
- **Redux/Zustand**: Complex, frequently updated, needs debugging tools

---

## Question 14: How do you decide which values should be **props** vs **local state** in a component?

**Problem**: When to use props vs state in a component.

**Solution**:
```jsx
// PROPS - Data from parent, component doesn't own it
function UserCard({ name, email, onEdit }) {
  return (
    <div>
      <h3>{name}</h3>
      <p>{email}</p>
      <button onClick={onEdit}>Edit</button>
    </div>
  );
}

// LOCAL STATE - Component owns and manages it
function ToggleButton() {
  const [isOn, setIsOn] = useState(false);
  
  return (
    <button onClick={() => setIsOn(!isOn)}>
      {isOn ? 'ON' : 'OFF'}
    </button>
  );
}

// COMBINATION - Props for data, state for UI
function SearchableList({ items }) {
  const [search, setSearch] = useState('');
  
  const filtered = items.filter(item => 
    item.name.toLowerCase().includes(search.toLowerCase())
  );
  
  return (
    <div>
      <input value={search} onChange={(e) => setSearch(e.target.value)} />
      {filtered.map(item => <div key={item.id}>{item.name}</div>)}
    </div>
  );
}
```

**Rules**:
- **Props**: Data passed from parent, callbacks, configuration
- **State**: UI state, user input, component-specific data
- If parent needs to know → Props
- If only component needs to know → State

---

## Question 15: How would you **share state** across multiple unrelated components without prop drilling?

**Problem**: Components in different parts of the tree need same state.

**Solution**:
```jsx
import { create } from 'zustand';

// Simple state management with Zustand
const useCartStore = create((set) => ({
  items: [],
  addItem: (item) => set((state) => ({ 
    items: [...state.items, item] 
  })),
  removeItem: (id) => set((state) => ({ 
    items: state.items.filter(i => i.id !== id) 
  }))
}));

// Component A
function ProductList() {
  const addItem = useCartStore((state) => state.addItem);
  
  return (
    <button onClick={() => addItem({ id: 1, name: 'Product' })}>
      Add to Cart
    </button>
  );
}

// Component B (completely unrelated)
function CartSummary() {
  const items = useCartStore((state) => state.items);
  
  return <div>Cart Items: {items.length}</div>;
}
```

**Alternative with Context**:
```jsx
const CartContext = createContext();

export function CartProvider({ children }) {
  const [items, setItems] = useState([]);
  
  const addItem = (item) => setItems(prev => [...prev, item]);
  
  return (
    <CartContext.Provider value={{ items, addItem }}>
      {children}
    </CartContext.Provider>
  );
}

export const useCart = () => useContext(CartContext);
```

**Key Points**:
- Zustand/Redux for complex state
- Context API for simpler cases
- Both avoid prop drilling

---

## Question 16: You need to sync state between localStorage and React state. How do you implement this?

**Problem**: Persist state across page refreshes.

**Solution**:
```jsx
import { useState, useEffect } from 'react';

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
      <input 
        value={user?.name || ''} 
        onChange={(e) => setUser({ name: e.target.value })} 
      />
      <button onClick={() => setTheme(theme === 'light' ? 'dark' : 'light')}>
        Toggle Theme
      </button>
    </div>
  );
}
```

**Key Points**:
- Read from localStorage on mount
- Write to localStorage on every update
- Handle JSON serialization
- Custom hook for reusability

---

## Question 17: How do you manage state for a shopping cart that persists across page refreshes?

**Problem**: Cart should survive page reloads.

**Solution**:
```jsx
import { create } from 'zustand';
import { persist } from 'zustand/middleware';

const useCartStore = create(
  persist(
    (set) => ({
      items: [],
      addItem: (product) => set((state) => ({
        items: [...state.items, { ...product, quantity: 1 }]
      })),
      removeItem: (id) => set((state) => ({
        items: state.items.filter(item => item.id !== id)
      })),
      updateQuantity: (id, quantity) => set((state) => ({
        items: state.items.map(item =>
          item.id === id ? { ...item, quantity } : item
        )
      })),
      clearCart: () => set({ items: [] })
    }),
    { name: 'cart-storage' }
  )
);

function Cart() {
  const { items, addItem, removeItem, updateQuantity } = useCartStore();

  return (
    <div>
      {items.map(item => (
        <div key={item.id}>
          <span>{item.name}</span>
          <input 
            type="number" 
            value={item.quantity}
            onChange={(e) => updateQuantity(item.id, +e.target.value)}
          />
          <button onClick={() => removeItem(item.id)}>Remove</button>
        </div>
      ))}
    </div>
  );
}
```

**Key Points**:
- Use Zustand with persist middleware
- Automatically syncs with localStorage
- Handles serialization/deserialization

---

## Question 18: Multiple components need to access and modify the same array. What's the best approach?

**Problem**: Shared array state across components.

**Solution**:
```jsx
import { createContext, useContext, useReducer } from 'react';

const TodoContext = createContext();

function todoReducer(state, action) {
  switch (action.type) {
    case 'ADD':
      return [...state, action.payload];
    case 'REMOVE':
      return state.filter(item => item.id !== action.payload);
    case 'UPDATE':
      return state.map(item =>
        item.id === action.payload.id ? action.payload : item
      );
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

const useTodos = () => useContext(TodoContext);

// Component A
function TodoList() {
  const { todos, dispatch } = useTodos();
  
  return (
    <div>
      {todos.map(todo => (
        <div key={todo.id}>
          {todo.text}
          <button onClick={() => dispatch({ type: 'REMOVE', payload: todo.id })}>
            Delete
          </button>
        </div>
      ))}
    </div>
  );
}

// Component B
function AddTodo() {
  const { dispatch } = useTodos();
  
  return (
    <button onClick={() => dispatch({ 
      type: 'ADD', 
      payload: { id: Date.now(), text: 'New Todo' }
    })}>
      Add Todo
    </button>
  );
}
```

**Key Points**:
- Use useReducer for complex array operations
- Context provides access to all components
- Centralized state logic

---

## Question 19: How would you implement undo/redo functionality in a React application?

**Problem**: Allow users to undo and redo actions.

**Solution**:
```jsx
import { useState } from 'react';

function useUndoRedo(initialState) {
  const [history, setHistory] = useState([initialState]);
  const [currentIndex, setCurrentIndex] = useState(0);

  const current = history[currentIndex];

  const setState = (newState) => {
    const newHistory = history.slice(0, currentIndex + 1);
    setHistory([...newHistory, newState]);
    setCurrentIndex(newHistory.length);
  };

  const undo = () => {
    if (currentIndex > 0) {
      setCurrentIndex(currentIndex - 1);
    }
  };

  const redo = () => {
    if (currentIndex < history.length - 1) {
      setCurrentIndex(currentIndex + 1);
    }
  };

  return {
    state: current,
    setState,
    undo,
    redo,
    canUndo: currentIndex > 0,
    canRedo: currentIndex < history.length - 1
  };
}

// Usage
function DrawingApp() {
  const { state, setState, undo, redo, canUndo, canRedo } = useUndoRedo([]);

  const addShape = (shape) => {
    setState([...state, shape]);
  };

  return (
    <div>
      <button onClick={undo} disabled={!canUndo}>Undo</button>
      <button onClick={redo} disabled={!canRedo}>Redo</button>
      <button onClick={() => addShape({ type: 'circle' })}>Add Circle</button>
      <div>Shapes: {state.length}</div>
    </div>
  );
}
```

**Key Points**:
- Maintain history array of states
- Track current position in history
- Slice history when new action after undo

---

## Question 20: Your form has 20+ fields. How do you manage state efficiently?

**Problem**: Managing many form fields becomes unwieldy.

**Solution**:
```jsx
import { useReducer } from 'react';

function formReducer(state, action) {
  switch (action.type) {
    case 'UPDATE_FIELD':
      return { ...state, [action.field]: action.value };
    case 'RESET':
      return action.payload;
    default:
      return state;
  }
}

function LargeForm() {
  const initialState = {
    firstName: '', lastName: '', email: '', phone: '',
    address: '', city: '', state: '', zip: '',
    company: '', position: '', salary: '', startDate: ''
    // ... 20+ fields
  };

  const [formData, dispatch] = useReducer(formReducer, initialState);

  const handleChange = (field) => (e) => {
    dispatch({ type: 'UPDATE_FIELD', field, value: e.target.value });
  };

  return (
    <form>
      <input value={formData.firstName} onChange={handleChange('firstName')} />
      <input value={formData.lastName} onChange={handleChange('lastName')} />
      <input value={formData.email} onChange={handleChange('email')} />
      {/* ... more fields */}
      <button type="button" onClick={() => dispatch({ type: 'RESET', payload: initialState })}>
        Reset
      </button>
    </form>
  );
}
```

**Better with React Hook Form**:
```jsx
import { useForm } from 'react-hook-form';

function LargeForm() {
  const { register, handleSubmit, reset } = useForm();

  const onSubmit = (data) => {
    console.log(data);
  };

  return (
    <form onSubmit={handleSubmit(onSubmit)}>
      <input {...register('firstName')} />
      <input {...register('lastName')} />
      <input {...register('email')} />
      {/* ... more fields */}
      <button type="submit">Submit</button>
      <button type="button" onClick={() => reset()}>Reset</button>
    </form>
  );
}
```

**Key Points**:
- Use useReducer for manual management
- React Hook Form for better performance
- Single handler function with field parameter


# ReactJS Data Fetching & API Integration - Questions 23-32 Solutions

## Question 21: How do you implement conditional rendering for loading, error, and success states in a component?

**Problem**: Need to show different UI based on API call status.

**Solution**:
```jsx
import { useState, useEffect } from 'react';

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
```

**Custom Hook Approach**:
```jsx
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

// Usage
function App() {
  const { data, loading, error } = useFetch('/api/users');

  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error}</div>;
  return <div>{data.name}</div>;
}
```

**Key Points**:
- Three states: loading, error, success
- Check loading first, then error, then render data
- Set loading to false in both success and error cases

---

## Question 22: You need to fetch data from **multiple APIs simultaneously** and display it once all are ready. How do you manage this in React?

**Problem**: Need to wait for all API calls to complete before rendering.

**Solution**:
```jsx
import { useState, useEffect } from 'react';

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

**Show Data As It Loads**:
```jsx
function MultiAPIComponent() {
  const [users, setUsers] = useState(null);
  const [posts, setPosts] = useState(null);
  const [comments, setComments] = useState(null);

  useEffect(() => {
    fetch('/api/users').then(res => res.json()).then(setUsers);
    fetch('/api/posts').then(res => res.json()).then(setPosts);
    fetch('/api/comments').then(res => res.json()).then(setComments);
  }, []);

  return (
    <div>
      <div>{users ? `Users: ${users.length}` : 'Loading users...'}</div>
      <div>{posts ? `Posts: ${posts.length}` : 'Loading posts...'}</div>
      <div>{comments ? `Comments: ${comments.length}` : 'Loading comments...'}</div>
    </div>
  );
}
```

**Key Points**:
- Use `Promise.all()` to wait for all APIs
- Destructure results in same order as requests
- Alternative: Load independently and show as ready

---


## Question 23: Your component fetches data every few seconds (polling). How do you ensure it doesn't cause memory leaks or unnecessary re-renders?

**Problem**: Polling can cause memory leaks if not cleaned up properly.

**Solution**:
```jsx
import { useState, useEffect } from 'react';

function PollingComponent() {
  const [data, setData] = useState(null);

  useEffect(() => {
    const fetchData = () => {
      fetch('/api/data')
        .then(res => res.json())
        .then(setData);
    };

    fetchData(); // Initial fetch
    const interval = setInterval(fetchData, 5000); // Poll every 5s

    return () => clearInterval(interval); // Cleanup!
  }, []);

  return <div>{data?.value}</div>;
}
```

**With Abort Controller**:
```jsx
function PollingComponent() {
  const [data, setData] = useState(null);

  useEffect(() => {
    let isMounted = true;

    const fetchData = async () => {
      try {
        const res = await fetch('/api/data');
        const result = await res.json();
        if (isMounted) setData(result);
      } catch (err) {
        console.error(err);
      }
    };

    fetchData();
    const interval = setInterval(fetchData, 5000);

    return () => {
      isMounted = false;
      clearInterval(interval);
    };
  }, []);

  return <div>{data?.value}</div>;
}
```

**Key Points**:
- Always clear interval in cleanup function
- Use `isMounted` flag to prevent state updates after unmount
- Empty dependency array to avoid recreating interval

---

## Question 24: How do you handle **error handling and fallback UI** for API failures in a React application?

**Problem**: Need graceful error handling with user-friendly UI.

**Solution**:
```jsx
import { useState, useEffect } from 'react';

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

**Error Boundary**:
```jsx
import { Component } from 'react';

class ErrorBoundary extends Component {
  state = { hasError: false };

  static getDerivedStateFromError() {
    return { hasError: true };
  }

  render() {
    if (this.state.hasError) {
      return <div>Something went wrong. <button onClick={() => window.location.reload()}>Reload</button></div>;
    }
    return this.props.children;
  }
}

// Usage
function App() {
  return (
    <ErrorBoundary>
      <DataComponent />
    </ErrorBoundary>
  );
}
```

**Key Points**:
- Check `res.ok` before parsing JSON
- Provide retry button
- Use Error Boundary for component errors
- Show user-friendly error messages

---

## Question 25: You need to fetch data based on user input with a delay. How do you implement this?

**Problem**: Avoid API calls on every keystroke (debouncing).

**Solution**:
```jsx
import { useState, useEffect } from 'react';

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

    return () => clearTimeout(timer); // Cleanup
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

**Custom Hook**:
```jsx
function useDebounce(value, delay) {
  const [debouncedValue, setDebouncedValue] = useState(value);

  useEffect(() => {
    const timer = setTimeout(() => setDebouncedValue(value), delay);
    return () => clearTimeout(timer);
  }, [value, delay]);

  return debouncedValue;
}

// Usage
function SearchComponent() {
  const [query, setQuery] = useState('');
  const [results, setResults] = useState([]);
  const debouncedQuery = useDebounce(query, 500);

  useEffect(() => {
    if (debouncedQuery) {
      fetch(`/api/search?q=${debouncedQuery}`)
        .then(res => res.json())
        .then(setResults);
    }
  }, [debouncedQuery]);

  return (
    <div>
      <input value={query} onChange={(e) => setQuery(e.target.value)} />
      {results.map(item => <div key={item.id}>{item.name}</div>)}
    </div>
  );
}
```

**Key Points**:
- Use `setTimeout` to delay API call
- Clear timeout on every input change
- Typical delay: 300-500ms

---

## Question 26: How would you implement infinite scrolling with API pagination?

**Problem**: Load more data as user scrolls down.

**Solution**:
```jsx
import { useState, useEffect, useRef } from 'react';

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

**Key Points**:
- Use IntersectionObserver to detect scroll
- Append new items to existing array
- Track `hasMore` to stop loading

---

## Question 27: Your API returns paginated data. How do you manage loading more data while keeping previous data?

**Problem**: Need to append new data without losing old data.

**Solution**:
```jsx
import { useState } from 'react';

function PaginatedList() {
  const [items, setItems] = useState([]);
  const [page, setPage] = useState(1);
  const [loading, setLoading] = useState(false);

  const loadMore = () => {
    setLoading(true);
    fetch(`/api/items?page=${page}`)
      .then(res => res.json())
      .then(data => {
        setItems(prev => [...prev, ...data]); // Append!
        setPage(prev => prev + 1);
        setLoading(false);
      });
  };

  return (
    <div>
      {items.map(item => (
        <div key={item.id}>{item.name}</div>
      ))}
      <button onClick={loadMore} disabled={loading}>
        {loading ? 'Loading...' : 'Load More'}
      </button>
    </div>
  );
}
```

**Key Points**:
- Use spread operator to append: `[...prev, ...data]`
- Increment page number after successful load
- Disable button while loading

---

## Question 28: How do you handle race conditions when making multiple API calls?

**Problem**: Fast typing can cause older API responses to overwrite newer ones.

**Solution**:
```jsx
import { useState, useEffect, useRef } from 'react';

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
        // Only update if this is the latest request
        if (requestId === requestIdRef.current) {
          setResults(data);
        }
      });
  }, [query]);

  return (
    <div>
      <input value={query} onChange={(e) => setQuery(e.target.value)} />
      {results.map(item => <div key={item.id}>{item.name}</div>)}
    </div>
  );
}
```

**With AbortController**:
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
```

**Key Points**:
- Use request ID to ignore stale responses
- Or use AbortController to cancel previous requests
- AbortController is cleaner and saves bandwidth

---

## Question 29: You need to prefetch data for the next page before user navigates. How do you implement this?

**Problem**: Improve perceived performance by loading data early.

**Solution**:
```jsx
import { useState, useEffect } from 'react';

const cache = {};

function usePrefetch(url) {
  useEffect(() => {
    if (!cache[url]) {
      fetch(url)
        .then(res => res.json())
        .then(data => {
          cache[url] = data;
        });
    }
  }, [url]);
}

function PageComponent({ page }) {
  const [data, setData] = useState(null);
  const nextPage = page + 1;
  
  // Prefetch next page
  usePrefetch(`/api/page/${nextPage}`);

  useEffect(() => {
    const url = `/api/page/${page}`;
    
    if (cache[url]) {
      setData(cache[url]);
    } else {
      fetch(url)
        .then(res => res.json())
        .then(data => {
          cache[url] = data;
          setData(data);
        });
    }
  }, [page]);

  return <div>{data?.content}</div>;
}
```

**On Hover Prefetch**:
```jsx
function Navigation() {
  const prefetch = (url) => {
    fetch(url).then(res => res.json()).then(data => {
      cache[url] = data;
    });
  };

  return (
    <nav>
      <a 
        href="/dashboard" 
        onMouseEnter={() => prefetch('/api/dashboard')}
      >
        Dashboard
      </a>
    </nav>
  );
}
```

**Key Points**:
- Cache fetched data in memory
- Prefetch on hover or when nearby
- Check cache before fetching

---

## Question 30: How would you implement optimistic updates when posting data to an API?

**Problem**: Make UI feel instant by updating before API confirms.

**Solution**:
```jsx
import { useState } from 'react';

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

**Key Points**:
- Update UI immediately with temporary data
- Replace with real data when API responds
- Rollback if API fails
- Show pending state visually (opacity, spinner)


## Component Architecture & Design (Questions 33-40)

## Question 31: How do you manage a complex form with **dependent fields** and validation rules in React?

**Problem**: Form fields depend on each other, complex validation logic.

**Solution**:
```jsx
import { useState } from 'react';

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
        <input
          name="state"
          value={form.state}
          onChange={handleChange}
          placeholder="State"
        />
      )}

      {form.state && (
        <input
          name="city"
          value={form.city}
          onChange={handleChange}
          placeholder="City"
        />
      )}

      <input
        name="zipCode"
        value={form.zipCode}
        onChange={handleChange}
        placeholder="Zip Code"
      />
      {errors.zipCode && <span>{errors.zipCode}</span>}
    </form>
  );
}
```

**With Form Library (React Hook Form)**:
```jsx
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

      {country && (
        <input {...register('state', { required: true })} />
      )}

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

**Key Points**:
- Store all form data in single state object
- Use `watch()` to track field changes for dependencies
- Validate on change, not just on submit
- Consider React Hook Form for complex forms

---

## Question 32: A component may throw errors during rendering. How do you prevent it from crashing the whole app?

**Problem**: One component error crashes entire app.

**Solution - Error Boundary**:
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

// Usage
function App() {
  return (
    <ErrorBoundary>
      <ProblematicComponent />
    </ErrorBoundary>
  );
}
```

**Multiple Boundaries**:
```jsx
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

**Key Points**:
- Error Boundaries must be class components
- Wrap risky components separately
- Doesn't catch errors in event handlers (use try-catch)
- Provide fallback UI and retry option

---

## Question 33: How would you integrate a **third-party library** that manipulates the DOM into a React component safely?

**Problem**: Libraries like Chart.js, D3 directly manipulate DOM, conflicting with React.

**Solution**:
```jsx
import { useEffect, useRef } from 'react';
import Chart from 'chart.js/auto';

function ChartComponent({ data }) {
  const canvasRef = useRef(null);
  const chartRef = useRef(null);

  useEffect(() => {
    // Initialize chart
    chartRef.current = new Chart(canvasRef.current, {
      type: 'bar',
      data: data
    });

    // Cleanup
    return () => {
      chartRef.current.destroy();
    };
  }, []); // Empty array - create once

  useEffect(() => {
    // Update chart when data changes
    if (chartRef.current) {
      chartRef.current.data = data;
      chartRef.current.update();
    }
  }, [data]);

  return <canvas ref={canvasRef} />;
}
```

**With jQuery Plugin**:
```jsx
import { useEffect, useRef } from 'react';
import $ from 'jquery';
import 'jquery-plugin';

function JQueryComponent({ options }) {
  const elementRef = useRef(null);

  useEffect(() => {
    const $element = $(elementRef.current);
    $element.pluginName(options);

    return () => {
      $element.pluginName('destroy');
    };
  }, [options]);

  return <div ref={elementRef} />;
}
```

**Key Points**:
- Use `useRef` to get DOM element
- Initialize library in useEffect
- Always cleanup/destroy in return function
- Separate initialization from updates

---

## Question 34: How do you implement **lazy loading** for large components to improve initial load time?

**Problem**: Large components slow down initial page load.

**Solution**:
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
```

**Preload on Hover**:
```jsx
import { lazy, Suspense } from 'react';

const Dashboard = lazy(() => import('./Dashboard'));

function Navigation() {
  const preloadDashboard = () => {
    import('./Dashboard');
  };

  return (
    <nav>
      <a 
        href="/dashboard" 
        onMouseEnter={preloadDashboard}
      >
        Dashboard
      </a>
    </nav>
  );
}
```

**Key Points**:
- Use `React.lazy()` for code splitting
- Wrap with `Suspense` and provide fallback
- Each lazy component creates separate bundle
- Preload on hover for better UX

---

## Question 35: You have a modal that needs to be triggered from multiple places. How do you structure this?

**Problem**: Modal needs to be opened from different components.

**Solution - Context API**:
```jsx
import { createContext, useContext, useState } from 'react';

const ModalContext = createContext();

export function ModalProvider({ children }) {
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

// Usage
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

**Key Points**:
- Use Context to manage modal state globally
- Single modal component, multiple triggers
- Pass content dynamically

---

## Question 36: How would you build a reusable table component with sorting, filtering, and pagination?

**Problem**: Need flexible table for different data types.

**Solution**:
```jsx
import { useState, useMemo } from 'react';

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

      <button onClick={() => setPage(p => p - 1)} disabled={page === 1}>
        Previous
      </button>
      <button onClick={() => setPage(p => p + 1)}>
        Next
      </button>
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

**Key Points**:
- Accept `data` and `columns` as props
- Use `useMemo` for filtering, sorting, pagination
- Keep component generic and reusable

---

## Question 37: You need to create a form component that works with different data structures. How do you make it flexible?

**Problem**: Form needs to handle various field types and structures.

**Solution**:
```jsx
import { useState } from 'react';

function DynamicForm({ fields, onSubmit }) {
  const [formData, setFormData] = useState({});

  const handleChange = (name, value) => {
    setFormData(prev => ({ ...prev, [name]: value }));
  };

  const renderField = (field) => {
    switch (field.type) {
      case 'text':
      case 'email':
        return (
          <input
            type={field.type}
            value={formData[field.name] || ''}
            onChange={(e) => handleChange(field.name, e.target.value)}
            placeholder={field.placeholder}
          />
        );
      
      case 'select':
        return (
          <select
            value={formData[field.name] || ''}
            onChange={(e) => handleChange(field.name, e.target.value)}
          >
            {field.options.map(opt => (
              <option key={opt.value} value={opt.value}>
                {opt.label}
              </option>
            ))}
          </select>
        );
      
      case 'checkbox':
        return (
          <input
            type="checkbox"
            checked={formData[field.name] || false}
            onChange={(e) => handleChange(field.name, e.target.checked)}
          />
        );
      
      default:
        return null;
    }
  };

  return (
    <form onSubmit={(e) => {
      e.preventDefault();
      onSubmit(formData);
    }}>
      {fields.map(field => (
        <div key={field.name}>
          <label>{field.label}</label>
          {renderField(field)}
        </div>
      ))}
      <button type="submit">Submit</button>
    </form>
  );
}

// Usage
function App() {
  const fields = [
    { name: 'email', type: 'email', label: 'Email', placeholder: 'Enter email' },
    { name: 'country', type: 'select', label: 'Country', options: [
      { value: 'us', label: 'USA' },
      { value: 'ca', label: 'Canada' }
    ]},
    { name: 'subscribe', type: 'checkbox', label: 'Subscribe' }
  ];

  return <DynamicForm fields={fields} onSubmit={console.log} />;
}
```

**Key Points**:
- Accept field configuration as props
- Render fields dynamically based on type
- Single state object for all fields
- Flexible and reusable

---

## Question 38: How do you handle deeply nested component props (prop drilling)?

**Problem**: Passing props through many levels is tedious.

**Solution - Context API**:
```jsx
import { createContext, useContext } from 'react';

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
```

**Component Composition**:
```jsx
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

**Key Points**:
- Use Context for global/shared data
- Use composition to pass components as props
- Avoid passing props through 3+ levels

---

## Question 39: How would you structure a dashboard with multiple widgets that fetch their own data?

**Problem**: Dashboard with independent widgets, each loading data.

**Solution**:
```jsx
import { Suspense, lazy } from 'react';

const SalesWidget = lazy(() => import('./widgets/SalesWidget'));
const UsersWidget = lazy(() => import('./widgets/UsersWidget'));
const RevenueWidget = lazy(() => import('./widgets/RevenueWidget'));

function Dashboard() {
  return (
    <div className="dashboard">
      <Suspense fallback={<WidgetSkeleton />}>
        <SalesWidget />
      </Suspense>
      
      <Suspense fallback={<WidgetSkeleton />}>
        <UsersWidget />
      </Suspense>
      
      <Suspense fallback={<WidgetSkeleton />}>
        <RevenueWidget />
      </Suspense>
    </div>
  );
}

function WidgetSkeleton() {
  return <div className="skeleton">Loading...</div>;
}
```

**Individual Widget**:
```jsx
import { useState, useEffect } from 'react';

function SalesWidget() {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetch('/api/sales')
      .then(res => res.json())
      .then(data => {
        setData(data);
        setLoading(false);
      });
  }, []);

  if (loading) return <div>Loading sales...</div>;

  return (
    <div className="widget">
      <h3>Sales</h3>
      <p>{data.total}</p>
    </div>
  );
}
```

**Key Points**:
- Each widget is independent component
- Each fetches its own data
- Use Suspense for lazy loading
- Widgets can fail independently

---

## Question 40: You need to build a component library. What patterns would you follow?

**Problem**: Create reusable, consistent components.

**Solution**:
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

// 3. Render Props Pattern
function DataProvider({ url, render }) {
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

  return render({ data, loading });
}

// Usage
function App() {
  return (
    <DataProvider 
      url="/api/users" 
      render={({ data, loading }) => (
        loading ? <div>Loading...</div> : <div>{data.name}</div>
      )}
    />
  );
}

// 4. Forward Refs
import { forwardRef } from 'react';

const Input = forwardRef((props, ref) => {
  return <input ref={ref} {...props} />;
});

// 5. TypeScript for Props (if using TS)
interface ButtonProps {
  variant?: 'primary' | 'secondary';
  size?: 'small' | 'medium' | 'large';
  children: React.ReactNode;
}
```

**Key Patterns**:
- Consistent prop naming (variant, size, etc.)
- Spread remaining props with `{...props}`
- Compound components for related UI
- Forward refs for DOM access
- Document with Storybook
- Export from single index file

## Event Handling & User Interactions

## Question 41: How do you handle **debouncing or throttling** input changes (e.g., search box) to avoid unnecessary re-renders or API calls?

**Problem**: Input fires onChange on every keystroke, causing excessive API calls.

**Solution - Debouncing**:
```jsx
import { useState, useEffect } from 'react';

function SearchBox() {
  const [input, setInput] = useState('');
  const [debouncedValue, setDebouncedValue] = useState('');

  useEffect(() => {
    const timer = setTimeout(() => {
      setDebouncedValue(input);
    }, 500);

    return () => clearTimeout(timer);
  }, [input]);

  useEffect(() => {
    if (debouncedValue) {
      // API call here
      console.log('API call:', debouncedValue);
    }
  }, [debouncedValue]);

  return (
    <input 
      value={input} 
      onChange={(e) => setInput(e.target.value)} 
      placeholder="Search..."
    />
  );
}
```

**Custom Hook**:
```jsx
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
```

**Throttling**:
```jsx
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

**Key Points**:
- Debounce: Wait until user stops typing (search)
- Throttle: Execute at regular intervals (scroll)
- Typical debounce delay: 300-500ms

---

## Question 42: You have a search input that should trigger API calls. How do you optimize this?

**Problem**: Every keystroke triggers API call.

**Solution**:
```jsx
import { useState, useEffect, useRef } from 'react';

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

**Key Points**:
- Debounce input (500ms)
- Cancel previous requests with AbortController
- Clear results when query is empty
- Show loading state

---

## Question 43: How would you implement drag-and-drop functionality in React?

**Problem**: Need to reorder list items with drag and drop.

**Solution - Native HTML5**:
```jsx
import { useState } from 'react';

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
          style={{
            padding: '10px',
            margin: '5px',
            background: draggedIndex === index ? '#ddd' : '#fff',
            cursor: 'move'
          }}
        >
          {item}
        </div>
      ))}
    </div>
  );
}
```

**With Library (react-beautiful-dnd)**:
```jsx
import { DragDropContext, Droppable, Draggable } from 'react-beautiful-dnd';

function DragDropList() {
  const [items, setItems] = useState(['Item 1', 'Item 2', 'Item 3']);

  const handleDragEnd = (result) => {
    if (!result.destination) return;

    const newItems = Array.from(items);
    const [removed] = newItems.splice(result.source.index, 1);
    newItems.splice(result.destination.index, 0, removed);
    setItems(newItems);
  };

  return (
    <DragDropContext onDragEnd={handleDragEnd}>
      <Droppable droppableId="list">
        {(provided) => (
          <div {...provided.droppableProps} ref={provided.innerRef}>
            {items.map((item, index) => (
              <Draggable key={item} draggableId={item} index={index}>
                {(provided) => (
                  <div
                    ref={provided.innerRef}
                    {...provided.draggableProps}
                    {...provided.dragHandleProps}
                  >
                    {item}
                  </div>
                )}
              </Draggable>
            ))}
            {provided.placeholder}
          </div>
        )}
      </Droppable>
    </DragDropContext>
  );
}
```

**Key Points**:
- Native: Use draggable, onDragStart, onDrop
- Library: react-beautiful-dnd for complex scenarios
- Always prevent default on dragOver

---

## Question 44: You need to handle keyboard shortcuts globally in your app. How do you implement this?

**Problem**: Ctrl+K should open search, Esc should close modals.

**Solution**:
```jsx
import { useEffect } from 'react';

function useKeyboardShortcut(key, callback, modifiers = {}) {
  useEffect(() => {
    const handleKeyDown = (e) => {
      const { ctrl, shift, alt } = modifiers;
      
      if (
        e.key === key &&
        (!ctrl || e.ctrlKey) &&
        (!shift || e.shiftKey) &&
        (!alt || e.altKey)
      ) {
        e.preventDefault();
        callback();
      }
    };

    window.addEventListener('keydown', handleKeyDown);
    return () => window.removeEventListener('keydown', handleKeyDown);
  }, [key, callback, modifiers]);
}

// Usage
function App() {
  const [searchOpen, setSearchOpen] = useState(false);

  useKeyboardShortcut('k', () => setSearchOpen(true), { ctrl: true });
  useKeyboardShortcut('Escape', () => setSearchOpen(false));

  return (
    <div>
      {searchOpen && <SearchModal onClose={() => setSearchOpen(false)} />}
      <p>Press Ctrl+K to search</p>
    </div>
  );
}
```

**Multiple Shortcuts**:
```jsx
function useKeyboardShortcuts(shortcuts) {
  useEffect(() => {
    const handleKeyDown = (e) => {
      const key = e.key.toLowerCase();
      const combo = `${e.ctrlKey ? 'ctrl+' : ''}${e.shiftKey ? 'shift+' : ''}${key}`;
      
      if (shortcuts[combo]) {
        e.preventDefault();
        shortcuts[combo]();
      }
    };

    window.addEventListener('keydown', handleKeyDown);
    return () => window.removeEventListener('keydown', handleKeyDown);
  }, [shortcuts]);
}

// Usage
function App() {
  useKeyboardShortcuts({
    'ctrl+k': () => console.log('Search'),
    'ctrl+s': () => console.log('Save'),
    'escape': () => console.log('Close')
  });

  return <div>App</div>;
}
```

**Key Points**:
- Listen on window for global shortcuts
- Check modifier keys (ctrl, shift, alt)
- Always preventDefault to avoid browser defaults
- Cleanup listener on unmount

---

## Question 45: How do you prevent multiple form submissions when user clicks submit button repeatedly?

**Problem**: User clicks submit 5 times, creates 5 duplicate records.

**Solution**:
```jsx
import { useState } from 'react';

function Form() {
  const [submitting, setSubmitting] = useState(false);
  const [formData, setFormData] = useState({ name: '', email: '' });

  const handleSubmit = async (e) => {
    e.preventDefault();
    
    if (submitting) return; // Prevent duplicate submissions
    
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
      <input
        value={formData.name}
        onChange={(e) => setFormData({ ...formData, name: e.target.value })}
        disabled={submitting}
      />
      <button type="submit" disabled={submitting}>
        {submitting ? 'Submitting...' : 'Submit'}
      </button>
    </form>
  );
}
```

**Key Points**:
- Use `submitting` state flag
- Disable button while submitting
- Check flag at start of submit handler
- Reset flag in finally block

---

## Question 46: How would you implement a real-time search with autocomplete suggestions?

**Problem**: Show suggestions as user types.

**Solution**:
```jsx
import { useState, useEffect, useRef } from 'react';

function Autocomplete() {
  const [query, setQuery] = useState('');
  const [suggestions, setSuggestions] = useState([]);
  const [showSuggestions, setShowSuggestions] = useState(false);
  const wrapperRef = useRef(null);

  useEffect(() => {
    if (!query) {
      setSuggestions([]);
      return;
    }

    const timer = setTimeout(() => {
      fetch(`/api/autocomplete?q=${query}`)
        .then(res => res.json())
        .then(data => {
          setSuggestions(data);
          setShowSuggestions(true);
        });
    }, 300);

    return () => clearTimeout(timer);
  }, [query]);

  // Click outside to close
  useEffect(() => {
    const handleClickOutside = (e) => {
      if (wrapperRef.current && !wrapperRef.current.contains(e.target)) {
        setShowSuggestions(false);
      }
    };

    document.addEventListener('mousedown', handleClickOutside);
    return () => document.removeEventListener('mousedown', handleClickOutside);
  }, []);

  const handleSelect = (suggestion) => {
    setQuery(suggestion);
    setShowSuggestions(false);
  };

  return (
    <div ref={wrapperRef}>
      <input
        value={query}
        onChange={(e) => setQuery(e.target.value)}
        onFocus={() => setShowSuggestions(true)}
        placeholder="Search..."
      />
      {showSuggestions && suggestions.length > 0 && (
        <ul className="suggestions">
          {suggestions.map((item, index) => (
            <li key={index} onClick={() => handleSelect(item)}>
              {item}
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}
```

**Key Points**:
- Debounce API calls (300ms)
- Show suggestions on focus
- Hide on click outside
- Update input on selection

---

## Question 47: You need to track user activity and send analytics. How do you implement this without affecting performance?

**Problem**: Track clicks, page views without slowing down app.

**Solution**:
```jsx
import { useEffect } from 'react';

// Analytics utility
const analytics = {
  queue: [],
  
  track(event, data) {
    this.queue.push({ event, data, timestamp: Date.now() });
    
    // Batch send every 5 seconds
    if (!this.timer) {
      this.timer = setInterval(() => this.flush(), 5000);
    }
  },
  
  flush() {
    if (this.queue.length === 0) return;
    
    const events = [...this.queue];
    this.queue = [];
    
    // Send in background
    navigator.sendBeacon('/api/analytics', JSON.stringify(events));
  }
};

// Hook for tracking
function useAnalytics() {
  const track = (event, data) => {
    analytics.track(event, data);
  };

  return { track };
}

// Usage
function Component() {
  const { track } = useAnalytics();

  useEffect(() => {
    track('page_view', { page: '/dashboard' });
  }, []);

  const handleClick = () => {
    track('button_click', { button: 'submit' });
  };

  return <button onClick={handleClick}>Submit</button>;
}
```

**Page View Tracking**:
```jsx
import { useEffect } from 'react';
import { useLocation } from 'react-router-dom';

function AnalyticsTracker() {
  const location = useLocation();

  useEffect(() => {
    analytics.track('page_view', { path: location.pathname });
  }, [location]);

  return null;
}

// In App.js
function App() {
  return (
    <>
      <AnalyticsTracker />
      <Routes>...</Routes>
    </>
  );
}
```

**Key Points**:
- Batch events, send every 5 seconds
- Use navigator.sendBeacon for reliability
- Don't block UI with analytics
- Queue events in memory

---

## Question 48: How do you handle file uploads with progress tracking in React?

**Problem**: Upload file and show progress bar.

**Solution**:
```jsx
import { useState } from 'react';

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

    xhr.addEventListener('error', () => {
      setUploading(false);
      alert('Upload failed!');
    });

    xhr.open('POST', '/api/upload');
    xhr.send(formData);
    setUploading(true);
  };

  return (
    <div>
      <input
        type="file"
        onChange={(e) => setFile(e.target.files[0])}
        disabled={uploading}
      />
      <button onClick={handleUpload} disabled={!file || uploading}>
        Upload
      </button>
      
      {uploading && (
        <div>
          <div className="progress-bar">
            <div style={{ width: `${progress}%` }} />
          </div>
          <p>{Math.round(progress)}%</p>
        </div>
      )}
    </div>
  );
}
```

**With Axios**:
```jsx
import axios from 'axios';

function FileUpload() {
  const [progress, setProgress] = useState(0);

  const handleUpload = async (file) => {
    const formData = new FormData();
    formData.append('file', file);

    try {
      await axios.post('/api/upload', formData, {
        onUploadProgress: (progressEvent) => {
          const percentCompleted = Math.round(
            (progressEvent.loaded * 100) / progressEvent.total
          );
          setProgress(percentCompleted);
        }
      });
      alert('Success!');
    } catch (error) {
      alert('Failed!');
    }
  };

  return <input type="file" onChange={(e) => handleUpload(e.target.files[0])} />;
}
```

**Key Points**:
- Use XMLHttpRequest for progress tracking
- Or use Axios with onUploadProgress
- Calculate percentage: (loaded / total) * 100
- Disable input while uploading

---

## Question 49: How would you implement click-outside detection for a dropdown menu?

**Problem**: Close dropdown when clicking outside.

**Solution**:
```jsx
import { useState, useEffect, useRef } from 'react';

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
        <ul className="dropdown-menu">
          <li>Option 1</li>
          <li>Option 2</li>
          <li>Option 3</li>
        </ul>
      )}
    </div>
  );
}
```

**Custom Hook**:
```jsx
function useClickOutside(ref, callback) {
  useEffect(() => {
    const handleClick = (e) => {
      if (ref.current && !ref.current.contains(e.target)) {
        callback();
      }
    };

    document.addEventListener('mousedown', handleClick);
    return () => document.removeEventListener('mousedown', handleClick);
  }, [ref, callback]);
}

// Usage
function Dropdown() {
  const [isOpen, setIsOpen] = useState(false);
  const dropdownRef = useRef(null);

  useClickOutside(dropdownRef, () => setIsOpen(false));

  return (
    <div ref={dropdownRef}>
      <button onClick={() => setIsOpen(!isOpen)}>Toggle</button>
      {isOpen && <ul>...</ul>}
    </div>
  );
}
```

**Key Points**:
- Use ref to track dropdown element
- Listen for mousedown on document
- Check if click target is inside ref
- Cleanup listener on unmount

---

## Question 50: You need to implement a complex gesture (e.g., swipe) on mobile. How do you approach this?

**Problem**: Detect swipe left/right on mobile.

**Solution**:
```jsx
import { useState } from 'react';

function SwipeComponent() {
  const [touchStart, setTouchStart] = useState(0);
  const [touchEnd, setTouchEnd] = useState(0);

  const minSwipeDistance = 50;

  const onTouchStart = (e) => {
    setTouchEnd(0);
    setTouchStart(e.targetTouches[0].clientX);
  };

  const onTouchMove = (e) => {
    setTouchEnd(e.targetTouches[0].clientX);
  };

  const onTouchEnd = () => {
    if (!touchStart || !touchEnd) return;
    
    const distance = touchStart - touchEnd;
    const isLeftSwipe = distance > minSwipeDistance;
    const isRightSwipe = distance < -minSwipeDistance;

    if (isLeftSwipe) {
      console.log('Swiped left');
    }
    if (isRightSwipe) {
      console.log('Swiped right');
    }
  };

  return (
    <div
      onTouchStart={onTouchStart}
      onTouchMove={onTouchMove}
      onTouchEnd={onTouchEnd}
      style={{ padding: '50px', background: '#f0f0f0' }}
    >
      Swipe me!
    </div>
  );
}
```

**Custom Hook**:
```jsx
function useSwipe(onSwipeLeft, onSwipeRight) {
  const [touchStart, setTouchStart] = useState(0);
  const [touchEnd, setTouchEnd] = useState(0);

  const minSwipeDistance = 50;

  const onTouchStart = (e) => {
    setTouchEnd(0);
    setTouchStart(e.targetTouches[0].clientX);
  };

  const onTouchMove = (e) => {
    setTouchEnd(e.targetTouches[0].clientX);
  };

  const onTouchEnd = () => {
    if (!touchStart || !touchEnd) return;
    
    const distance = touchStart - touchEnd;
    const isLeftSwipe = distance > minSwipeDistance;
    const isRightSwipe = distance < -minSwipeDistance;

    if (isLeftSwipe && onSwipeLeft) onSwipeLeft();
    if (isRightSwipe && onSwipeRight) onSwipeRight();
  };

  return {
    onTouchStart,
    onTouchMove,
    onTouchEnd
  };
}

// Usage
function ImageGallery() {
  const [index, setIndex] = useState(0);

  const swipeHandlers = useSwipe(
    () => setIndex(i => i + 1), // Swipe left
    () => setIndex(i => Math.max(0, i - 1)) // Swipe right
  );

  return (
    <div {...swipeHandlers}>
      <img src={`/images/${index}.jpg`} alt="Gallery" />
    </div>
  );
}
```

**With Library (react-swipeable)**:
```jsx
import { useSwipeable } from 'react-swipeable';

function SwipeComponent() {
  const handlers = useSwipeable({
    onSwipedLeft: () => console.log('Swiped left'),
    onSwipedRight: () => console.log('Swiped right'),
    preventDefaultTouchmoveEvent: true,
    trackMouse: true
  });

  return <div {...handlers}>Swipe me!</div>;
}
```

**Key Points**:
- Track touchStart and touchEnd positions
- Calculate distance to determine swipe direction
- Set minimum swipe distance (50px)
- Use library for complex gestures (pinch, rotate)


## Lifecycle & Side Effects

## Question 51: You have a component that subscribes to a WebSocket for live updates. How do you handle cleanup when the component unmounts?

**Problem**: WebSocket connection stays open after component unmounts, causing memory leaks.

**Solution**:
```jsx
import { useEffect, useState } from 'react';

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

**With Reconnection Logic**:
```jsx
function LiveUpdates() {
  const [messages, setMessages] = useState([]);
  const wsRef = useRef(null);

  useEffect(() => {
    let reconnectTimer;

    const connect = () => {
      const ws = new WebSocket('ws://localhost:8080');
      wsRef.current = ws;

      ws.onmessage = (event) => {
        setMessages(prev => [...prev, event.data]);
      };

      ws.onclose = () => {
        // Reconnect after 3 seconds
        reconnectTimer = setTimeout(connect, 3000);
      };
    };

    connect();

    return () => {
      clearTimeout(reconnectTimer);
      if (wsRef.current) {
        wsRef.current.close();
      }
    };
  }, []);

  return <div>{messages.map((msg, i) => <div key={i}>{msg}</div>)}</div>;
}
```

**Key Points**:
- Always close WebSocket in cleanup function
- Use empty dependency array to connect once
- Clear reconnection timers on unmount
- Store WebSocket in ref for access in cleanup

---

## Question 52: How do you run an effect only when a specific prop changes, not on every render?

**Problem**: Effect runs on every render, but should only run when specific prop changes.

**Solution**:
```jsx
import { useEffect } from 'react';

function UserProfile({ userId, theme }) {
  const [user, setUser] = useState(null);

  // Only runs when userId changes
  useEffect(() => {
    fetch(`/api/users/${userId}`)
      .then(res => res.json())
      .then(setUser);
  }, [userId]); // Only userId in dependency array

  return <div>{user?.name}</div>;
}
```

**Multiple Dependencies**:
```jsx
function Component({ userId, filter, theme }) {
  useEffect(() => {
    // Runs when userId OR filter changes
    // Does NOT run when theme changes
    fetchData(userId, filter);
  }, [userId, filter]);

  return <div>Content</div>;
}
```

**Key Points**:
- Add only specific dependencies to array
- Effect runs when ANY dependency changes
- Omit dependencies you want to ignore
- Empty array `[]` = run once on mount

---

## Question 53: You need to fetch data when component mounts and update when a prop changes. How do you structure this?

**Problem**: Need to fetch data initially and refetch when prop changes.

**Solution**:
```jsx
import { useEffect, useState } from 'react';

function DataComponent({ userId }) {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    setLoading(true);
    
    fetch(`/api/users/${userId}`)
      .then(res => res.json())
      .then(data => {
        setData(data);
        setLoading(false);
      });
  }, [userId]); // Runs on mount AND when userId changes

  if (loading) return <div>Loading...</div>;
  return <div>{data.name}</div>;
}
```

**With Cleanup**:
```jsx
function DataComponent({ userId }) {
  const [data, setData] = useState(null);

  useEffect(() => {
    let isMounted = true;
    const controller = new AbortController();

    fetch(`/api/users/${userId}`, { signal: controller.signal })
      .then(res => res.json())
      .then(data => {
        if (isMounted) setData(data);
      })
      .catch(err => {
        if (err.name !== 'AbortError') console.error(err);
      });

    return () => {
      isMounted = false;
      controller.abort();
    };
  }, [userId]);

  return <div>{data?.name}</div>;
}
```

**Key Points**:
- Include prop in dependency array
- Effect runs on mount and when prop changes
- Use AbortController to cancel previous requests
- Use isMounted flag to prevent state updates after unmount

---

## Question 54: How do you handle cleanup for event listeners added in useEffect?

**Problem**: Event listeners stay attached after component unmounts.

**Solution**:
```jsx
import { useEffect, useState } from 'react';

function WindowSize() {
  const [size, setSize] = useState({ width: window.innerWidth, height: window.innerHeight });

  useEffect(() => {
    const handleResize = () => {
      setSize({ width: window.innerWidth, height: window.innerHeight });
    };

    window.addEventListener('resize', handleResize);

    // Cleanup
    return () => {
      window.removeEventListener('resize', handleResize);
    };
  }, []);

  return <div>{size.width} x {size.height}</div>;
}
```

**Multiple Listeners**:
```jsx
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

**Key Points**:
- Always remove listeners in cleanup function
- Use same function reference for add and remove
- Empty dependency array for global listeners
- Cleanup runs before component unmounts

---

## Question 55: You have multiple useEffect hooks that depend on each other. How do you manage this?

**Problem**: One effect needs data from another effect.

**Solution - Sequential Effects**:
```jsx
import { useEffect, useState } from 'react';

function Component() {
  const [user, setUser] = useState(null);
  const [posts, setPosts] = useState([]);

  // First effect: Fetch user
  useEffect(() => {
    fetch('/api/user')
      .then(res => res.json())
      .then(setUser);
  }, []);

  // Second effect: Fetch posts when user is available
  useEffect(() => {
    if (!user) return;

    fetch(`/api/posts?userId=${user.id}`)
      .then(res => res.json())
      .then(setPosts);
  }, [user]); // Depends on user

  return (
    <div>
      <h1>{user?.name}</h1>
      {posts.map(post => <div key={post.id}>{post.title}</div>)}
    </div>
  );
}
```

**Combined in Single Effect**:
```jsx
function Component() {
  const [data, setData] = useState({ user: null, posts: [] });

  useEffect(() => {
    async function fetchData() {
      const userRes = await fetch('/api/user');
      const user = await userRes.json();

      const postsRes = await fetch(`/api/posts?userId=${user.id}`);
      const posts = await postsRes.json();

      setData({ user, posts });
    }

    fetchData();
  }, []);

  return (
    <div>
      <h1>{data.user?.name}</h1>
      {data.posts.map(post => <div key={post.id}>{post.title}</div>)}
    </div>
  );
}
```

**Key Points**:
- Use separate effects with dependencies
- Or combine into single async effect
- Check if dependency exists before using it
- Avoid circular dependencies

---

## Question 56: How would you implement a timer that starts when component mounts and cleans up on unmount?

**Problem**: Timer keeps running after component unmounts.

**Solution - Interval**:
```jsx
import { useEffect, useState } from 'react';

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
```

**Countdown Timer**:
```jsx
function Countdown({ initialSeconds }) {
  const [seconds, setSeconds] = useState(initialSeconds);

  useEffect(() => {
    if (seconds <= 0) return;

    const interval = setInterval(() => {
      setSeconds(prev => prev - 1);
    }, 1000);

    return () => clearInterval(interval);
  }, [seconds]);

  return <div>{seconds > 0 ? `${seconds}s remaining` : 'Time\'s up!'}</div>;
}
```

**Custom Hook**:
```jsx
function useTimer(initialSeconds = 0) {
  const [seconds, setSeconds] = useState(initialSeconds);
  const [isRunning, setIsRunning] = useState(false);

  useEffect(() => {
    if (!isRunning) return;

    const interval = setInterval(() => {
      setSeconds(prev => prev + 1);
    }, 1000);

    return () => clearInterval(interval);
  }, [isRunning]);

  return { seconds, start: () => setIsRunning(true), stop: () => setIsRunning(false) };
}

// Usage
function Component() {
  const { seconds, start, stop } = useTimer();
  
  return (
    <div>
      <div>{seconds}s</div>
      <button onClick={start}>Start</button>
      <button onClick={stop}>Stop</button>
    </div>
  );
}
```

**Key Points**:
- Always clear interval in cleanup
- Use functional setState for accurate counting
- Return cleanup function from useEffect

---

## Question 57: You need to call an API when component mounts, but only if a condition is true. How do you structure this?

**Problem**: Conditionally fetch data on mount.

**Solution**:
```jsx
import { useEffect, useState } from 'react';

function Component({ shouldFetch, userId }) {
  const [data, setData] = useState(null);

  useEffect(() => {
    if (!shouldFetch) return; // Early return if condition false

    fetch(`/api/users/${userId}`)
      .then(res => res.json())
      .then(setData);
  }, [shouldFetch, userId]);

  return <div>{data?.name}</div>;
}
```

**With Multiple Conditions**:
```jsx
function Component({ isAuthenticated, userId }) {
  const [data, setData] = useState(null);

  useEffect(() => {
    if (!isAuthenticated || !userId) return;

    fetch(`/api/users/${userId}`)
      .then(res => res.json())
      .then(setData);
  }, [isAuthenticated, userId]);

  return <div>{data?.name}</div>;
}
```

**Key Points**:
- Use early return for conditions
- Include condition in dependency array
- Effect re-runs when condition changes
- Check all required conditions before API call

---

## Question 58: How do you prevent useEffect from running on initial render?

**Problem**: Effect should only run on updates, not on mount.

**Solution**:
```jsx
import { useEffect, useRef } from 'react';

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
```

**Custom Hook**:
```jsx
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

**Key Points**:
- Use ref to track first render
- Return early on first render
- Ref persists across renders
- Create custom hook for reusability

---

## Question 59: You have an effect that should run after state updates are committed to DOM. How do you ensure this?

**Problem**: Need to access DOM after React updates it.

**Solution - useLayoutEffect**:
```jsx
import { useLayoutEffect, useRef, useState } from 'react';

function Component() {
  const [height, setHeight] = useState(0);
  const divRef = useRef(null);

  useLayoutEffect(() => {
    // Runs synchronously after DOM updates
    if (divRef.current) {
      setHeight(divRef.current.offsetHeight);
    }
  }, []);

  return (
    <div>
      <div ref={divRef}>Content</div>
      <p>Height: {height}px</p>
    </div>
  );
}
```

**Measuring Element**:
```jsx
function MeasuredComponent() {
  const [dimensions, setDimensions] = useState({ width: 0, height: 0 });
  const elementRef = useRef(null);

  useLayoutEffect(() => {
    if (elementRef.current) {
      const { width, height } = elementRef.current.getBoundingClientRect();
      setDimensions({ width, height });
    }
  });

  return (
    <div ref={elementRef}>
      <p>Width: {dimensions.width}px</p>
      <p>Height: {dimensions.height}px</p>
    </div>
  );
}
```

**Key Points**:
- Use `useLayoutEffect` for DOM measurements
- Runs synchronously after DOM updates
- Blocks browser paint until complete
- Use `useEffect` for most cases, `useLayoutEffect` only when needed

---

## Question 60: How do you handle async operations in useEffect properly?

**Problem**: Can't make useEffect callback async directly.

**Solution - Async Function Inside**:
```jsx
import { useEffect, useState } from 'react';

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
```

**With Cleanup**:
```jsx
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
```

**With AbortController**:
```jsx
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

**IIFE Pattern**:
```jsx
function Component() {
  const [data, setData] = useState(null);

  useEffect(() => {
    (async () => {
      const response = await fetch('/api/data');
      const result = await response.json();
      setData(result);
    })();
  }, []);

  return <div>{data?.name}</div>;
}
```

**Key Points**:
- Create async function inside useEffect
- Can't make useEffect callback async directly
- Use isMounted flag or AbortController for cleanup
- Handle errors with try-catch
- IIFE pattern is more concise but less clear


## Authentication & Authorization

## Question 61: How would you implement authentication in React where the token persists across refreshes and user data is accessible globally?

**Problem**: Token and user data lost on page refresh.

**Solution**:
```jsx
import { createContext, useContext, useState, useEffect } from 'react';

const AuthContext = createContext();

export function AuthProvider({ children }) {
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    // Check for token on mount
    const token = localStorage.getItem('token');
    if (token) {
      // Fetch user data
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

// Usage in App.js
function App() {
  return (
    <AuthProvider>
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/dashboard" element={<Dashboard />} />
      </Routes>
    </AuthProvider>
  );
}

// Usage in components
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

**Key Points**:
- Store token in localStorage
- Use Context for global access
- Fetch user data on mount if token exists
- Provide login/logout functions

---

## Question 62: You need to protect certain routes from unauthenticated users. How do you implement this?

**Problem**: Prevent unauthenticated users from accessing protected routes.

**Solution**:
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
```

**Alternative - Layout Wrapper**:
```jsx
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

// Usage
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

**Key Points**:
- Check user in ProtectedRoute component
- Redirect to login if not authenticated
- Show loading state while checking auth
- Use Outlet for nested routes

---

## Question 63: How do you handle token refresh when it expires during user session?

**Problem**: Token expires, API calls fail with 401.

**Solution**:
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
        // Refresh failed, logout
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

**Key Points**:
- Use axios interceptors
- Catch 401 errors
- Attempt token refresh
- Retry original request with new token
- Logout if refresh fails

---

## Question 64: You need to redirect users based on their role after login. How do you structure this?

**Problem**: Admin goes to admin panel, user goes to dashboard.

**Solution**:
```jsx
import { useNavigate } from 'react-router-dom';
import { useAuth } from './AuthContext';

function Login() {
  const navigate = useNavigate();
  const { login } = useAuth();

  const handleLogin = async (email, password) => {
    const user = await login(email, password);
    
    // Redirect based on role
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

**Role-Based Route Protection**:
```jsx
function RoleProtectedRoute({ children, allowedRoles }) {
  const { user, loading } = useAuth();

  if (loading) return <div>Loading...</div>;
  if (!user) return <Navigate to="/login" />;
  if (!allowedRoles.includes(user.role)) return <Navigate to="/unauthorized" />;
  
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
          <RoleProtectedRoute allowedRoles={['admin']}>
            <AdminPanel />
          </RoleProtectedRoute>
        } 
      />
      <Route 
        path="/dashboard" 
        element={
          <RoleProtectedRoute allowedRoles={['user', 'admin']}>
            <Dashboard />
          </RoleProtectedRoute>
        } 
      />
    </Routes>
  );
}
```

**Key Points**:
- Check user role after login
- Navigate based on role
- Protect routes with role checks
- Show unauthorized page for wrong roles

---

## Question 65: How would you implement "Remember Me" functionality?

**Problem**: Keep user logged in even after browser closes.

**Solution**:
```jsx
function Login() {
  const [rememberMe, setRememberMe] = useState(false);
  const { login } = useAuth();

  const handleLogin = async (email, password) => {
    const data = await fetch('/api/login', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ email, password })
    }).then(res => res.json());

    // Store based on remember me
    const storage = rememberMe ? localStorage : sessionStorage;
    storage.setItem('token', data.token);
    
    login(data.user);
  };

  return (
    <form onSubmit={handleLogin}>
      <input type="email" />
      <input type="password" />
      <label>
        <input 
          type="checkbox" 
          checked={rememberMe} 
          onChange={(e) => setRememberMe(e.target.checked)} 
        />
        Remember Me
      </label>
      <button type="submit">Login</button>
    </form>
  );
}
```

**Auth Context Update**:
```jsx
export function AuthProvider({ children }) {
  const [user, setUser] = useState(null);

  useEffect(() => {
    // Check both storages
    const token = localStorage.getItem('token') || sessionStorage.getItem('token');
    
    if (token) {
      fetch('/api/me', {
        headers: { Authorization: `Bearer ${token}` }
      })
        .then(res => res.json())
        .then(setUser);
    }
  }, []);

  const logout = () => {
    localStorage.removeItem('token');
    sessionStorage.removeItem('token');
    setUser(null);
  };

  return (
    <AuthContext.Provider value={{ user, logout }}>
      {children}
    </AuthContext.Provider>
  );
}
```

**Key Points**:
- localStorage persists after browser close
- sessionStorage clears when browser closes
- Use checkbox to let user choose
- Check both storages on mount

---

## Question 66: You need to logout user across all tabs when they logout in one. How do you achieve this?

**Problem**: User logs out in one tab, still logged in other tabs.

**Solution**:
```jsx
import { useEffect } from 'react';
import { useAuth } from './AuthContext';

export function AuthProvider({ children }) {
  const [user, setUser] = useState(null);

  useEffect(() => {
    // Listen for storage changes
    const handleStorageChange = (e) => {
      if (e.key === 'token' && e.newValue === null) {
        // Token removed, logout
        setUser(null);
      }
    };

    window.addEventListener('storage', handleStorageChange);
    return () => window.removeEventListener('storage', handleStorageChange);
  }, []);

  const logout = () => {
    localStorage.removeItem('token');
    setUser(null);
    // Storage event will trigger in other tabs
  };

  return (
    <AuthContext.Provider value={{ user, logout }}>
      {children}
    </AuthContext.Provider>
  );
}
```

**With BroadcastChannel**:
```jsx
export function AuthProvider({ children }) {
  const [user, setUser] = useState(null);
  const channelRef = useRef(null);

  useEffect(() => {
    channelRef.current = new BroadcastChannel('auth');
    
    channelRef.current.onmessage = (event) => {
      if (event.data === 'logout') {
        setUser(null);
        localStorage.removeItem('token');
      }
    };

    return () => channelRef.current.close();
  }, []);

  const logout = () => {
    localStorage.removeItem('token');
    setUser(null);
    channelRef.current.postMessage('logout');
  };

  return (
    <AuthContext.Provider value={{ user, logout }}>
      {children}
    </AuthContext.Provider>
  );
}
```

**Key Points**:
- Listen to storage events for cross-tab sync
- Or use BroadcastChannel API
- Storage event only fires in OTHER tabs
- BroadcastChannel works in all modern browsers

---

## Question 67: How do you handle unauthorized API responses (401) globally?

**Problem**: Need to logout user on any 401 response.

**Solution**:
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
      // Clear token and redirect
      localStorage.removeItem('token');
      window.location.href = '/login';
    }
    return Promise.reject(error);
  }
);

export default api;
```

**With Auth Context**:
```jsx
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

**Key Points**:
- Use axios interceptors
- Catch all 401 responses
- Clear token and redirect to login
- Or call logout from context

---

## Question 68: You need to show different UI based on user permissions. How do you implement this?

**Problem**: Show/hide features based on user permissions.

**Solution**:
```jsx
import { useAuth } from './AuthContext';

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
```

**Custom Hook**:
```jsx
function usePermission(permission) {
  const { user } = useAuth();
  return user?.permissions?.includes(permission) || false;
}

// Usage
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
```

**Role-Based**:
```jsx
function hasRole(user, roles) {
  return roles.includes(user?.role);
}

function Dashboard() {
  const { user } = useAuth();

  return (
    <div>
      {hasRole(user, ['admin', 'manager']) && (
        <AdminPanel />
      )}
      
      {hasRole(user, ['admin']) && (
        <button>Delete All</button>
      )}
    </div>
  );
}
```

**Key Points**:
- Create Can component for permission checks
- Or use custom hook
- Check permissions array or role
- Return null to hide UI

---

## Question 69: How would you implement social login (Google, Facebook) in React?

**Problem**: Allow users to login with Google/Facebook.

**Solution - Google**:
```jsx
import { GoogleOAuthProvider, GoogleLogin } from '@react-oauth/google';

function App() {
  return (
    <GoogleOAuthProvider clientId="YOUR_GOOGLE_CLIENT_ID">
      <Login />
    </GoogleOAuthProvider>
  );
}

function Login() {
  const { login } = useAuth();

  const handleGoogleSuccess = async (credentialResponse) => {
    // Send token to backend
    const res = await fetch('/api/auth/google', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ token: credentialResponse.credential })
    });
    
    const data = await res.json();
    localStorage.setItem('token', data.token);
    login(data.user);
  };

  return (
    <div>
      <GoogleLogin
        onSuccess={handleGoogleSuccess}
        onError={() => console.log('Login Failed')}
      />
    </div>
  );
}
```

**Manual Implementation**:
```jsx
function Login() {
  const handleGoogleLogin = () => {
    const clientId = 'YOUR_GOOGLE_CLIENT_ID';
    const redirectUri = 'http://localhost:3000/auth/callback';
    const scope = 'email profile';
    
    const authUrl = `https://accounts.google.com/o/oauth2/v2/auth?client_id=${clientId}&redirect_uri=${redirectUri}&response_type=code&scope=${scope}`;
    
    window.location.href = authUrl;
  };

  return <button onClick={handleGoogleLogin}>Login with Google</button>;
}

// Callback component
function AuthCallback() {
  const navigate = useNavigate();
  const { login } = useAuth();

  useEffect(() => {
    const code = new URLSearchParams(window.location.search).get('code');
    
    if (code) {
      fetch('/api/auth/google/callback', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ code })
      })
        .then(res => res.json())
        .then(data => {
          localStorage.setItem('token', data.token);
          login(data.user);
          navigate('/dashboard');
        });
    }
  }, []);

  return <div>Logging in...</div>;
}
```

**Key Points**:
- Use library like @react-oauth/google
- Or redirect to OAuth provider
- Send token to backend for verification
- Backend creates user and returns JWT

---

## Question 70: You need to implement two-factor authentication flow. How do you structure the components?

**Problem**: After password, user enters 6-digit code.

**Solution**:
```jsx
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';

function Login() {
  const [step, setStep] = useState('password'); // 'password' or '2fa'
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [tempToken, setTempToken] = useState('');
  const navigate = useNavigate();

  const handlePasswordSubmit = async (e) => {
    e.preventDefault();
    
    const res = await fetch('/api/login', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ email, password })
    });
    
    const data = await res.json();
    
    if (data.requires2FA) {
      setTempToken(data.tempToken);
      setStep('2fa');
    } else {
      localStorage.setItem('token', data.token);
      navigate('/dashboard');
    }
  };

  const handleCodeSubmit = async (code) => {
    const res = await fetch('/api/verify-2fa', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ tempToken, code })
    });
    
    const data = await res.json();
    localStorage.setItem('token', data.token);
    navigate('/dashboard');
  };

  if (step === '2fa') {
    return <TwoFactorInput onSubmit={handleCodeSubmit} />;
  }

  return (
    <form onSubmit={handlePasswordSubmit}>
      <input 
        type="email" 
        value={email} 
        onChange={(e) => setEmail(e.target.value)} 
      />
      <input 
        type="password" 
        value={password} 
        onChange={(e) => setPassword(e.target.value)} 
      />
      <button type="submit">Login</button>
    </form>
  );
}

function TwoFactorInput({ onSubmit }) {
  const [code, setCode] = useState(['', '', '', '', '', '']);
  const inputRefs = useRef([]);

  const handleChange = (index, value) => {
    if (value.length > 1) return;
    
    const newCode = [...code];
    newCode[index] = value;
    setCode(newCode);

    // Auto-focus next input
    if (value && index < 5) {
      inputRefs.current[index + 1].focus();
    }

    // Auto-submit when complete
    if (index === 5 && value) {
      onSubmit(newCode.join(''));
    }
  };

  const handleKeyDown = (index, e) => {
    if (e.key === 'Backspace' && !code[index] && index > 0) {
      inputRefs.current[index - 1].focus();
    }
  };

  return (
    <div>
      <h2>Enter 6-digit code</h2>
      <div style={{ display: 'flex', gap: '10px' }}>
        {code.map((digit, index) => (
          <input
            key={index}
            ref={(el) => (inputRefs.current[index] = el)}
            type="text"
            maxLength={1}
            value={digit}
            onChange={(e) => handleChange(index, e.target.value)}
            onKeyDown={(e) => handleKeyDown(index, e)}
            style={{ width: '40px', height: '40px', textAlign: 'center', fontSize: '20px' }}
          />
        ))}
      </div>
    </div>
  );
}
```

**Key Points**:
- Two-step flow: password then 2FA code
- Backend returns tempToken after password
- Use tempToken to verify 2FA code
- Auto-focus next input on digit entry
- Auto-submit when all 6 digits entered


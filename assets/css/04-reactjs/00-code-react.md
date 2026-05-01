// Create a traffic signal in React where:
// Red stays on for 7s, Yellow for 2s, and Green for 5s.a
// The lights should change automatically.
----------------------------------------------------------------------
```jsx
import React, { useState, useEffect } from "react";

function App() {
  const [light, setLight] = useState("red");

  useEffect(() => {
    let timer;

    if (light === "red") {
      timer = setTimeout(() => setLight("green"), 7000);
    } else if (light === "green") {
      timer = setTimeout(() => setLight("yellow"), 5000);
    } else if (light === "yellow") {
      timer = setTimeout(() => setLight("red"), 2000);
    }

    return () => clearTimeout(timer);
  }, [light]);

  const getColor = (color) => (light === color ? color : "gray");

  return (
    <div style={{ padding: "20px", textAlign: "center" }}>
      <h1>Traffic Signal</h1>

      <div style={{ width: "100px", margin: "auto" }}>
        <div
          style={{
            height: "50px",
            background: getColor("red"),
            margin: "10px",
          }}
        />
        <div
          style={{
            height: "50px",
            background: getColor("yellow"),
            margin: "10px",
          }}
        />
        <div
          style={{
            height: "50px",
            background: getColor("green"),
            margin: "10px",
          }}
        />
      </div>

      <h2>Current: {light.toUpperCase()}</h2>
    </div>
  );
}

export default App;
```

=====================================================================
// Implement  Debounce Search
----------------------------------------------------------------------
```jsx
import { useRef } from "react";

function App() {
  const timerRef = useRef(null);

  const debounce = (func, delay) => {
    if (timerRef.current) {
      clearTimeout(timerRef.current);
    }

    timerRef.current = setTimeout(() => {
      func();
    }, delay);
  };

  const search = (event) => {
    const str = event.target.value.toLowerCase();
    setSearchStr(str);

    debounce(() => {
      console.log("API call with:", str);
      // call your API here
    }, 1000);
  };

  return <input onChange={search} />;
}
```

=====================================================================
// Implement Basic Timer / Stop Watch (Start / Stop / Reset)
----------------------------------------------------------------------
```jsx
import React, { useState, useEffect } from "react";

export default function Timer() {
  const [seconds, setSeconds] = useState(0);
  const [isRunning, setIsRunning] = useState(false);

  useEffect(() => {
    let interval;

    if (isRunning) {
      interval = setInterval(() => {
        setSeconds((prev) => prev + 1);
      }, 1000);
    }

    return () => clearInterval(interval); // cleanup
  }, [isRunning]);

  return (
    <div style={{ textAlign: "center", marginTop: "50px" }}>
      <h1>⏱ Timer: {seconds}s</h1>

      <button onClick={() => setIsRunning(true)}>Start</button>
      <button onClick={() => setIsRunning(false)}>Stop</button>
      <button onClick={() => {
        setSeconds(0);
        setIsRunning(false);
      }}>
        Reset
      </button>
    </div>
  );
}
```
=====================================================================
// How Do You Design Reusable Components?
----------------------------------------------------------------------
```jsx
// Flexible Button component
function Button({ 
  children, 
  variant = 'primary', 
  size = 'medium', 
  disabled = false,
  loading = false,
  onClick,
  ...props 
}) {
  const className = `btn btn--${variant} btn--${size} ${disabled ? 'btn--disabled' : ''}`;
  
  return (
    <button
      className={className}
      disabled={disabled || loading}
      onClick={onClick}
      {...props}
    >
      {loading ? <Spinner /> : children}
    </button>
  );
}

// Usage examples
<Button onClick={handleSave}>Save</Button>
<Button variant="secondary" size="small">Cancel</Button>
<Button loading={isSubmitting}>Submit</Button>

// Flexible Modal component with composition
function Modal({ isOpen, onClose, children }) {
  if (!isOpen) return null;

  return (
    <div className="modal-overlay" onClick={onClose}>
      <div className="modal-content" onClick={(e) => e.stopPropagation()}>
        <button className="modal-close" onClick={onClose}>×</button>
        {children}
      </div>
    </div>
  );
}

// Modal subcomponents for composition
Modal.Header = function ModalHeader({ children }) {
  return <div className="modal-header">{children}</div>;
};

Modal.Body = function ModalBody({ children }) {
  return <div className="modal-body">{children}</div>;
};

Modal.Footer = function ModalFooter({ children }) {
  return <div className="modal-footer">{children}</div>;
};

// Usage with composition
function DeleteConfirmation({ isOpen, onClose, onConfirm }) {
  return (
    <Modal isOpen={isOpen} onClose={onClose}>
      <Modal.Header>
        <h2>Confirm Delete</h2>
      </Modal.Header>
      <Modal.Body>
        <p>Are you sure you want to delete this item?</p>
      </Modal.Body>
      <Modal.Footer>
        <Button variant="secondary" onClick={onClose}>Cancel</Button>
        <Button variant="danger" onClick={onConfirm}>Delete</Button>
      </Modal.Footer>
    </Modal>
  );
}

// Flexible Input component with validation
function Input({ 
  label, 
  error, 
  helperText, 
  required = false,
  ...inputProps 
}) {
  const inputId = inputProps.id || inputProps.name;
  
  return (
    <div className="input-group">
      {label && (
        <label htmlFor={inputId} className="input-label">
          {label} {required && <span className="required">*</span>}
        </label>
      )}
      <input
        id={inputId}
        className={`input ${error ? 'input--error' : ''}`}
        {...inputProps}
      />
      {error && <span className="input-error">{error}</span>}
      {helperText && <span className="input-helper">{helperText}</span>}
    </div>
  );
}

// Usage
<Input 
  label="Email" 
  name="email" 
  type="email" 
  required 
  error={errors.email}
  helperText="We'll never share your email"
/>
```
=====================================================================
// How to implement lazy loading
----------------------------------------------------------------------
```jsx
import React, { lazy, Suspense, useState } from "react";

const HeavyComponent = lazy(() => import("./HeavyComponent"));

function App() {
  const [show, setShow] = useState(false);

  return (
    <div>
      <button onClick={() => setShow(true)}>Load Component</button>

      {show && (
        <Suspense fallback={<p>Loading...</p>}>
          <HeavyComponent />
        </Suspense>
      )}
    </div>
  );
}

export default App;
```

=====================================================================
// How to implement Handle API Calls
----------------------------------------------------------------------
```jsx
import { useState, useEffect } from 'react';

function UserProfile({ userId }) {
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchUser = async () => {
      try {
        setLoading(true);
        const response = await fetch(`/api/users/${userId}`);
        if (!response.ok) throw new Error('Failed to fetch');
        const userData = await response.json();
        setUser(userData);
      } catch (err) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    };

    fetchUser();
  }, [userId]);

  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error}</div>;
  return <div>Welcome {user?.name}</div>;
}
```
=====================================================================
// How to implement Context API
----------------------------------------------------------------------
```jsx
// Create Context
const ThemeContext = createContext();

// Provider Component
function App() {
  const [theme, setTheme] = useState('light');
  
  return (
    <ThemeContext.Provider value={{ theme, setTheme }}>
      <Header />
      <Main />
    </ThemeContext.Provider>
  );
}

// Consumer Component
function Header() {
  const { theme, setTheme } = useContext(ThemeContext);
  
  return (
    <header className={theme}>
      <button onClick={() => setTheme(theme === 'light' ? 'dark' : 'light')}>
        Toggle Theme
      </button>
    </header>
  );
}
```
=====================================================================
// How to implement custom hooks for Data fetching 
----------------------------------------------------------------------
```jsx
// Data fetching hook
const useApi = (url) => {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  
  useEffect(() => {
    const fetchData = async () => {
      try {
        setLoading(true);
        setError(null);
        const response = await fetch(url);
        const result = await response.json();
        setData(result);
      } catch (err) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    };
    
    fetchData();
  }, [url]);
  
  return { data, loading, error };
}


// 1. Custom hooks for shared logic
function useAuth() {
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    checkAuthStatus().then(userData => {
      setUser(userData);
      setLoading(false);
    });
  }, []);

  const login = async (credentials) => {
    const userData = await authAPI.login(credentials);
    setUser(userData);
  };

  const logout = () => {
    authAPI.logout();
    setUser(null);
  };

  return { user, loading, login, logout };
}

// Usage across multiple pages
function Dashboard() {
  const { user, logout } = useAuth();
  return <div>Welcome {user?.name} <button onClick={logout}>Logout</button></div>;
}
```

=====================================================================
// How to implement Higher-Order Components (HOC);
----------------------------------------------------------------------
```jsx
// Authentication HOC
function withAuth(WrappedComponent) {
  return function AuthenticatedComponent(props) {
    const [user, setUser] = useState(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
      checkAuthStatus().then(userData => {
        setUser(userData);
        setLoading(false);
      });
    }, []);

    if (loading) return <div>Loading...</div>;
    if (!user) return <LoginForm />;
    
    return <WrappedComponent {...props} user={user} />;
  };
}

// Usage
const ProtectedDashboard = withAuth(Dashboard);
const ProtectedProfile = withAuth(Profile);

function Dashboard({ user }) {
  return <h1>Welcome to Dashboard, {user.name}!</h1>;
}

// Loading HOC
function withLoading(WrappedComponent) {
  return function LoadingComponent({ isLoading, ...props }) {
    if (isLoading) {
      return <div className="spinner">Loading...</div>;
    }
    
    return <WrappedComponent {...props} />;
  };
}


// Compose multiple HOCs
const EnhancedUserList = withErrorBoundary(
  withAuth(
    withLoading(UserList)
  )
);

// Modern alternative with custom hooks
function useAuth() {
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    checkAuthStatus().then(userData => {
      setUser(userData);
      setLoading(false);
    });
  }, []);

  return { user, loading };
}

// Component using hook instead of HOC
function Dashboard() {
  const { user, loading } = useAuth();
  
  if (loading) return <div>Loading...</div>;
  if (!user) return <LoginForm />;
  
  return <h1>Welcome to Dashboard, {user.name}!</h1>;
}
```
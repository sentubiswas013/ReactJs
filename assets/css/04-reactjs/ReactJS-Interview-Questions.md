# ReactJS – Real-Time Interview Questions (Category-Wise)

---

## 1. React Fundamentals

1. What is React and why is it used?
2. What is JSX and how does it work?
3. What is the difference between React and ReactDOM?
4. What are components in React? What are the types?
5. What is the difference between functional and class components?
6. What is the Virtual DOM and how does React use it?
7. What are props? How are they different from state?
8. What is state in React and how do you update it?
9. What is the `key` prop and why is it important in lists?
10. What are controlled vs uncontrolled components?
11. What are React Fragments and when do you use them?
12. What are synthetic events in React?
13. What is `React.StrictMode` and why is it used?
14. What is the purpose of the `render()` method?
15. What is the difference between `React.createElement` and JSX?

---

## 2. React Hooks

1. What are React Hooks and why were they introduced?
2. What are the rules of Hooks?
3. What is `useState` and how does it work?
4. What is `useEffect` and how does it work?
5. What is the difference between `useState` and `useEffect`?
6. How does the dependency array in `useEffect` work internally?
7. What is a stale closure in hooks and how do you fix it?
8. What is `useRef` and when would you use it instead of `useState`?
9. What is `useMemo` and when should you use it?
10. What is `useCallback` and when should you use it?
11. What is the difference between `useMemo` and `useCallback`?
12. What is `useReducer` and how is it different from `useState`?
13. What is `useContext` and how does it work?
14. How do you create custom hooks? Give an example.
15. What is the difference between `useEffect`, `useLayoutEffect`, and `useInsertionEffect`?
16. When should you avoid using `useEffect`?

---

## 3. Component Lifecycle & Internals

1. What is the lifecycle of a class component?
2. How does the functional component lifecycle map to class lifecycle methods?
3. What is the difference between `componentDidMount` and `useEffect`?
4. What is the difference between `componentWillMount`, `componentDidMount`, and `getDerivedStateFromProps`?
5. What is React reconciliation?
6. What is React Fiber and what problem does it solve?
7. What causes a component to re-render?
8. What is the purpose of `shouldComponentUpdate`?

---

## 4. Performance Optimization

1. How do you prevent unnecessary re-renders in React?
2. How does `React.memo` work internally?
3. Why do inline functions in JSX cause re-renders?
4. What is automatic batching in React 18?
5. What is Concurrent Rendering and how does it help?
6. What is `startTransition` and `useTransition`?
7. What is code splitting and how do you implement it?
8. What is lazy loading in React? How do you use `React.lazy` and `Suspense`?
9. What is virtualization (windowing) and when do you use it?
10. How do you handle memory leaks in React?
11. How do you identify which components are causing performance bottlenecks?
12. How do you optimize a component that renders based on window scroll position?
13. How do you handle debouncing or throttling for a search input?
14. How do you optimize a slow React page?

---

## 5. State Management

1. What is the difference between local state and global state?
2. What is the Context API and when should you use it?
3. What is the difference between state and context?
4. When would you choose Context API over Redux?
5. What is Redux and what are its core principles (actions, reducers, store)?
6. How do you handle asynchronous actions in Redux?
7. What are Redux middlewares? What is the difference between `redux-thunk` and `redux-saga`?
8. How would you structure a Redux store in a large application?
9. How would you manage a multi-step form without losing data?
10. How do you sync state with `localStorage`?
11. How would you implement undo/redo functionality?
12. How would you migrate from Redux to Context API?

---

## 6. Data Fetching & Side Effects

1. How do you handle API calls in React?
2. What problems does `useEffect` have with data fetching?
3. How do you fetch data from multiple APIs simultaneously?
4. How do you handle race conditions in API calls?
5. How do you cancel API requests to avoid memory leaks?
6. How do you implement polling without memory leaks?
7. How do you implement optimistic updates?
8. How do you implement retry logic for failed API calls?
9. What is React Query / TanStack Query and why use it?
10. How do you implement conditional rendering for loading, error, and success states?

---

## 7. Routing (React Router v6)

1. What is React Router and how does it work?
2. What are dynamic routes and how do you implement them?
3. How do you implement nested routes with shared layouts?
4. What are `useParams`, `useLocation`, and `useNavigate`?
5. What is the difference between `useHistory` (v5) and `useNavigate` (v6)?
6. How do you implement route-based code splitting?
7. How do you implement route guards for authentication and permissions?
8. How do you prevent navigation when a form has unsaved changes?
9. How do you handle 404 pages and invalid routes?

---

## 8. Component Architecture & Design Patterns

1. What are Higher-Order Components (HOCs)?
2. What is the render props pattern?
3. What is the compound component pattern?
4. What is the container-presentational (smart vs dumb) component pattern?
5. How do you design reusable components?
6. How do you handle prop drilling?
7. How do you build a reusable modal triggered from multiple places?
8. How would you build a reusable table with sorting, filtering, and pagination?
9. How do you structure a large-scale React application (feature-based folder structure)?
10. How would you build a component library?

---

## 9. Authentication & Authorization

1. How do you implement authentication where the token persists across refreshes?
2. How do you protect routes from unauthenticated users?
3. How do you handle token refresh when it expires during a session?
4. How do you handle unauthorized API responses (401) globally?
5. How do you show different UI based on user roles/permissions?
6. How do you redirect users based on authentication status?

---

## 10. Event Handling & User Interactions

1. How do you handle events in React (differences from HTML)?
2. How do you prevent multiple form submissions on repeated clicks?
3. How do you implement click-outside detection for a dropdown?
4. How do you implement drag-and-drop in React?
5. How do you handle file uploads with progress tracking?

---

## 11. Error Handling & Debugging

1. What are Error Boundaries and how do you implement them?
2. How do you implement global error handling for all API calls?
3. How do you debug why a component is re-rendering unnecessarily?
4. Your production app crashes but works in development — how do you debug this?
5. How do you identify and fix a memory leak in a React app?

---

## 12. Testing

1. What testing libraries are commonly used in React?
2. Why prefer React Testing Library over Enzyme?
3. How do you write unit tests for React components?
4. How do you test a component that makes API calls (mocking)?
5. How do you test a component that uses Context?
6. How do you test custom hooks in isolation?
7. How do you test user interactions like clicks and form submissions?
8. What is snapshot testing and when do you use it?
9. What is MSW (Mock Service Worker)?
10. What is the difference between shallow rendering and full rendering?

---

## 13. Build Tools & Tooling

1. What is Webpack and what does it do in a React project?
2. What is Vite and why is it faster than Webpack?
3. What is Babel and why is it required?
4. What is tree shaking?
5. How does dynamic import work?
6. What is the role of `.env` files in React?
7. How do you manage environment variables across environments?
8. What is CI/CD and how do you set it up for a React application?

---

## 14. SSR, Next.js & Modern React

1. What is Server-Side Rendering (SSR) in React?
2. What is the difference between CSR, SSR, SSG, and ISR?
3. What is Selective Hydration and lazy hydration?
4. What is Next.js and why use it over plain React?
5. What are React Server Components (RSC)?
6. What is the difference between `use client` and `use server` in Next.js?

---

## 15. Security

1. How does React prevent XSS attacks?
2. Is React safe from SQL Injection?
3. How do you securely store tokens (localStorage vs cookies)?
4. What is the difference between localStorage, sessionStorage, and cookies?
5. How do you implement role-based authorization in React?

---

## 16. Real-World Scenario Questions

1. Your component fetches data and re-renders multiple times unnecessarily — how do you fix it?
2. Updating parent state causes all children to re-render — how do you prevent it?
3. A component renders 10,000 items causing lag — what techniques do you use?
4. Multiple components subscribe to the same context but only one value changes — how do you prevent unnecessary re-renders?
5. A child deep in the tree needs to update global state — how do you do it without prop drilling?
6. How do you implement infinite scrolling with API pagination?
7. How would you implement a multi-step form with back/forward navigation?
8. How do you handle a WebSocket subscription and clean it up on unmount?
9. How do you integrate a third-party DOM-manipulating library safely in React?
10. How would you migrate class components to functional components with hooks?

## 🟢 1. React Fundamentals (Must-Know)

1. What is React and why is it used?
2. What is JSX and how does it work?
3. Difference between JSX and `React.createElement`
4. Difference between React and ReactDOM
5. What are components in React?
6. Types of components in React
7. Functional vs Class components
8. What is the Virtual DOM?
9. What are props?
10. What is state and how is it different from props?
11. What is the `render()` method?
12. What is the `key` prop and why is it important?
13. What are controlled components?
14. What are uncontrolled components?
15. What are React Fragments?
16. What are synthetic events?
17. What is `React.StrictMode` and why is it used?

---

## 🟢 2. React Hooks (Core + Advanced)

1. What are React Hooks and why were they introduced?
2. Rules of Hooks and why they exist
3. What is `useState` and how does it work?
4. What is `useEffect` and how does it work?
5. Difference between `useState` and `useEffect`
6. Difference between `useEffect`, `useLayoutEffect`, and `useInsertionEffect`
7. When should you avoid `useEffect`?
8. How does the dependency array work internally?
9. What is stale closure in hooks?
10. How do you fix stale state issues?
11. When would you use `useRef` instead of `useState`?
12. What is `useMemo` and when should you use it?
13. What is `useCallback` and when should you use it?
14. Difference between `useMemo` and `useCallback`
15. What is `useReducer` and how is it different from `useState`?
16. Can hooks replace all lifecycle methods? (mapping)
17. How do you create custom hooks?
18. How do you share logic without HOCs?

---

## 🟡 3. Component Lifecycle & Internals

1. Lifecycle of class components
2. Lifecycle of functional components
3. Difference between `componentDidMount` and `useEffect`
4. Difference between `componentWillMount`, `componentDidMount`, and `getDerivedStateFromProps`
5. What is React reconciliation?
6. What is React Fiber?
7. What causes a component to re-render?

---

## 🟡 4. Rendering & Performance Optimization (High Priority)

1. What is automatic batching in React 18?
2. What is Concurrent Rendering?
3. Difference between legacy and concurrent rendering
4. What is `startTransition` and `useTransition`?
5. Why does React Strict Mode double render in development?
6. What is code splitting?
7. What is lazy loading?
8. How does `React.memo` work internally?
9. How do you prevent unnecessary re-renders?
10. Why do inline functions cause re-renders?
11. What is render thrashing?
12. How do keys affect reconciliation?
13. What happens if keys are not stable?
14. How do you handle memory leaks in React?
15. What is virtualization (windowing)?
16. How does React efficiently handle large lists?

---

## 🟡 5. State Management

1. Local state vs global state
2. What is Context API and when should you use it?
3. Difference between state and context
4. Context vs Redux – how do you decide?
5. What is Redux and why is it used?
6. Redux core principles (actions, reducers, store)
7. How do you handle async actions in Redux?
8. What are Redux middlewares?
9. Difference between redux-thunk and redux-saga
10. How would you structure Redux in a large application?

---

## 🟠 6. Routing (React Router v6)

1. What is React Router and how does it work?
2. How do you implement routing in React?
3. What are dynamic routes?
4. How do you implement nested routes?
5. What are `useParams`, `useLocation`, and `useNavigate`?
6. What are `<Router>` components in React Router v6?

---

## 🟠 7. Data Fetching & Side Effects

1. How do you handle API calls in React?
2. What problems does `useEffect` have with data fetching?
3. How do you fetch data without `useEffect`?
4. What is React Query / TanStack Query?
5. Difference between client-side caching and server state
6. How do you cancel API requests?
7. How do you handle race conditions?

---

## 🟠 8. Architecture & Design Patterns

1. How do you structure a large-scale React application?
2. What is feature-based folder structure?
3. Smart vs dumb components
4. Container-presentational pattern
5. How do you design reusable components?
6. How do you handle shared logic across pages?
7. What is render props pattern?
8. What is compound component pattern?
9. What are Higher-Order Components (HOCs)?

---

## 🔵 9. Testing (Modern React)

1. What testing libraries are used in React?
2. Why React Testing Library over Enzyme?
3. What should you test vs not test?
4. How do you test components?
5. How do you test async components?
6. How do you test custom hooks?
7. How do you test Context Providers?
8. What is snapshot testing?
9. What is mocking and how do you mock APIs?
10. What is MSW (Mock Service Worker)?

---

## 🔵 10. Build Tools & Tooling

1. What is Vite and why is it faster than Webpack?
2. What is Webpack and what does it do?
3. What is Babel and why is it required?
4. What is tree shaking?
5. How does dynamic import work?
6. What is the role of `.env` files?
7. Explain the npm start → build workflow
8. What is CI/CD for React applications?

---

## 🔵 11. SSR, Next.js & Modern Web

1. What is SSR in React?
2. Difference between CSR, SSR, SSG, and ISR
3. What is Selective Hydration?
4. What is lazy hydration?
5. What is Next.js and why use it?
6. What are React Server Components (RSC)?
7. Difference between `use client` and `use server`

---

## 🔴 12. Security & Real-World Scenarios

1. How does React prevent XSS?
2. Is React safe from SQL Injection?
3. How do you securely store tokens?
4. Difference between localStorage, sessionStorage, and cookies
5. How do you implement role-based authorization?
6. How would you build a reusable modal?
7. How would you implement infinite scrolling?
8. How would you build a multi-step form?
9. How do you handle form validation in large forms?
10. How do you optimize a slow React page?
11. How would you migrate class components to hooks?

## 🟢 1. React Fundamentals (Must‑Know)

1. What is React and why is it used?
2. What is JSX and how does it work?
3. Difference between JSX and `React.createElement`
4. Difference between React and ReactDOM
5. What are components in React?
6. Types of components in React
7. Functional vs Class components
8. What is Virtual DOM?
9. What are props?
10. What is state and how is it different from props?
11. What is the `render()` method?
12. What is `key` prop and why is it important?
13. What are controlled components?
14. What are uncontrolled components?
15. What are React Fragments?
16. What are synthetic events?
17. What is `React.StrictMode` and why is it used?

---

## 🟢 2. React Hooks (Core + Advanced) ⭐

18. What are React Hooks?
19. Rules of Hooks and why they exist
20. What is `useState` and how does it work?
21. What is `useEffect` and how does it work?
22. Difference between `useState` and `useEffect`
23. Difference between `useEffect`, `useLayoutEffect`, and `useInsertionEffect`
24. When should you avoid `useEffect`?
25. How does dependency array comparison work?
26. What is stale closure in hooks?
27. How do you fix stale state issues?
28. When would you use `useRef` instead of `useState`?
29. What is `useMemo` and when should you use it?
30. What is `useCallback` and when should you use it?
31. Difference between `useMemo` and `useCallback`
32. What is `useReducer` and how is it different from `useState`?
33. Can hooks replace all lifecycle methods? Explain mapping
34. How do you create custom hooks?
35. How do you share logic without HOCs?

---

## 🟡 3. Component Lifecycle & Internals

36. Lifecycle of class components
37. Lifecycle of functional components
38. Difference between `componentDidMount` and `useEffect`
39. Difference between `componentWillMount`, `componentDidMount`, and `getDerivedStateFromProps`
40. What is React reconciliation?
41. What is React Fiber?
42. What causes a component to re-render?

---

## 🟡 4. Rendering & Performance Optimization ⭐

43. What is automatic batching in React 18?
44. What is Concurrent Rendering?
45. Difference between legacy and concurrent rendering
46. What is `startTransition` and `useTransition`?
47. What is React Strict Mode double rendering?
48. What is code splitting?
49. What is lazy loading in React?
50. How does `React.memo` work?
51. How do you prevent unnecessary re-renders?
52. Why do inline functions cause re-renders?
53. What is render thrashing?
54. How do keys affect reconciliation?
55. What happens if keys are not stable?
56. How do you handle memory leaks in React?
57. What is virtualization (windowing)?
58. How does React efficiently handle large lists?

---

## 🟡 5. State Management

59. Local state vs global state
60. What is Context API and when should you use it?
61. Difference between state and context
62. Context vs Redux – how do you decide?
63. What is Redux and why is it used?
64. Redux core principles (actions, reducers, store)
65. How do you handle async actions in Redux?
66. What are Redux middlewares?
67. Difference between redux‑thunk and redux‑saga
68. How would you structure Redux in a large app?

---

## 🟠 6. Routing (React Router v6)

69. What is React Router and how does it work?
70. How do you implement routing in React?
71. What are dynamic routes?
72. How do you implement nested routes?
73. What are `useParams`, `useLocation`, and `useNavigate`?

---

## 🟠 7. Data Fetching & Side Effects

74. How do you handle API calls in React?
75. What problems does `useEffect` have with data fetching?
76. How do you fetch data without `useEffect`?
77. What is React Query / TanStack Query?
78. Client‑side caching vs server state
79. How do you cancel API requests?
80. How do you handle race conditions?

---

## 🟠 8. Architecture & Design Patterns

81. How do you structure a large‑scale React application?
82. What is feature‑based folder structure?
83. Smart vs dumb components
84. Container‑presentational pattern
85. How do you design reusable components?
86. How do you handle shared logic across pages?
87. What is render props pattern?
88. What is compound component pattern?
89. What are Higher‑Order Components (HOCs)?

---

## 🔵 9. Testing (Modern React)

90. What testing libraries are used in React?
91. Why React Testing Library over Enzyme?
92. What should you test vs not test?
93. How do you test components?
94. How do you test async components?
95. How do you test custom hooks?
96. How do you test Context Providers?
97. What is snapshot testing?
98. What is mocking and how do you mock APIs?
99. What is MSW (Mock Service Worker)?

---

## 🔵 10. Build Tools & Tooling

100. What is Vite and why is it faster than Webpack?
101. What is Webpack and what does it do?
102. What is Babel and why is it required?
103. What is tree shaking?
104. How does dynamic import work?
105. What is the role of `.env` files?
106. Explain npm start build workflow
107. What is CI/CD for React applications?

---

## 🔵 11. SSR, Next.js & Modern Web

108. What is SSR in React?
109. Difference between CSR, SSR, SSG, ISR
110. What is Selective Hydration?
111. What is lazy hydration?
112. What is Next.js and why use it?
113. What are React Server Components?
114. Difference between `use client` and `use server`

---

## 🔴 12. Security & Real‑World Scenarios

115. How does React prevent XSS?
116. Is React safe from SQL Injection?
117. How do you securely store tokens?
118. Difference between localStorage, sessionStorage, and cookies
119. How do you implement role‑based authorization?
120. How would you build a reusable modal?
121. How would you implement infinite scrolling?
122. How would you build a multi‑step form?
123. How do you handle form validation in large forms?
124. How do you optimize a slow React page?
125. How would you migrate class components to hooks?



## **Basic React Concepts**

1. What is React?
2. What is JSX?
3. What is the difference between React and ReactDOM?
4. What are components in React?
5. What is the difference between functional and class components?
6. What is the Virtual DOM?
7. What are props in React?
8. What are state and how does it differ from props?
9. What is a key in React and why is it important?
10. What are controlled components in React?
11. What are uncontrolled components in React?
12. What is the purpose of the `render()` method in React?
13. What are React hooks? Can you name some of them?
14. What is the difference between `useState` and `useEffect` hooks?
15. What is the `useEffect` hook and how does it work?
16. What is `useRef` in React?
17. What is the purpose of `useMemo` and `useCallback` hooks?
18. What is the difference between `React.createElement` and JSX?
19. What is the role of `React.StrictMode`?
20. How does the React development workflow (npm start, Webpack, Babel) work?

## **Intermediate React Concepts**

21. What are higher-order components (HOCs) in React?
22. What is context in React and why is it used?
23. What is the difference between `React.createContext` and `useContext`?
24. What are the advantages of using functional components over class components?
25. What is `setState` and how does it work in React?
26. What is the difference between `componentDidMount` and `useEffect`?
27. What are React fragments?
28. What are error boundaries in React?
29. What is the purpose of `shouldComponentUpdate`?
30. What are portals in React?
31. How do you handle events in React?
32. What is lazy loading in React?
33. What is the difference between state and context?
34. How can you manage global state in a React application?
35. What is the `useReducer` hook and how does it differ from `useState`?
36. What are synthetic events in React?
37. How do you handle side effects in React?

## **Advanced React Concepts**

38. What is Server-Side Rendering (SSR) in React?
39. What are React Suspense and Concurrent Mode?
40. What is code splitting in React?
41. What is React Fiber?
42. What is React's reconciliation algorithm?
43. How does React handle forms?
44. What is the significance of the `key` prop in lists?
45. How do you implement custom hooks in React?
46. What is the difference between `componentWillMount`, `componentDidMount`, and `getDerivedStateFromProps`?
47. What are React DevTools and how do they help in debugging?
48. What is the lifecycle of a React component? Explain both class-based and functional components' lifecycles.
49. Explain React's Context API and when you would use it instead of prop drilling.
50. Explain the concept of "render props" and provide an example.

## **React 18 & Modern React Features**

51. What is **automatic batching** in React 18?
52. What is **Concurrent Rendering**, and why was it introduced?
53. What is the `startTransition()` API and when would you use it?
54. What problem does `useTransition` solve?
55. What is **React Strict Mode double rendering** in development?
56. How does React 18 improve performance compared to React 17?
57. What is **Selective Hydration** in React?
58. Difference between **legacy rendering** and **concurrent rendering**?

## **Hooks - Deep Dive**

59. What rules must hooks follow, and **why**?
60. What problems can occur if hooks are used conditionally?
61. Difference between `useEffect`, `useLayoutEffect`, and `useInsertionEffect`
62. When should you avoid `useEffect`?
63. How does dependency array comparison work internally?
64. What is a **stale closure** problem in hooks?
65. How do you fix stale state issues in `useEffect`?
66. When would you use `useRef` instead of `useState`?
67. Can hooks replace all lifecycle methods? Explain mapping.
68. How do you share logic between components **without HOCs**?

## **Performance Optimization**

69. How can you optimize performance in React applications?
70. What strategies can you use to avoid unnecessary re-renders in React?
71. How would you deal with memory leaks in React components?
72. What causes a React component to re-render?
73. How do you prevent child re-renders when parent state changes?
74. How does `React.memo` work internally?
75. Difference between `useMemo` and `useCallback` with real use cases
76. Why does passing inline functions cause re-renders?
77. What is **render thrashing**?
78. How do keys affect reconciliation performance?
79. What happens if keys are not stable?
80. What is virtualization (windowing)?
81. How does React handle large lists efficiently?
82. What is debounce vs throttle in React?
83. How do you optimize first contentful paint (FCP)?
84. What is lazy hydration?

## **React Router**

85. What is React Router and how do you use it?
86. How would you implement routing in a React application? What is React Router, and how does it work?
87. What are dynamic routes, and how would you implement them in React Router?
88. How can you handle nested routes in React Router?
89. What is the purpose of useParams, useLocation, and useHistory (or useNavigate in React Router v6)?
90. What are the `<Router>` components of React Router v6?

## **State Management**

91. What is Redux, and how does it help in state management in React?
92. What are the principles of Redux (actions, reducers, store)?
93. Explain the difference between local component state and global state in React applications.
94. How do you handle asynchronous actions in Redux?
95. What are Redux middleware, and can you give an example of how you'd use `redux-thunk` or `redux-saga`?
96. Explain how you would structure your Redux store in a large application.
97. How do you decide between **context vs Redux**?

## **Data Fetching & Side Effects**

98. How do you handle API calls in React without `useEffect`?
99. What problems does `useEffect` have with data fetching?
100. What is **React Query / TanStack Query**, and why is it preferred?
101. Difference between **client-side caching** and **server state**
102. How do you cancel API requests in React?
103. How do you handle race conditions in API calls?

## **Architecture & Best Practices**

104. How do you structure a **large-scale React application**?
105. What is **feature-based folder structure**?
106. What are **smart vs dumb components**?
107. What is **container-presentational pattern**?
108. How do you handle shared logic across multiple pages?
109. How do you design reusable components?
110. What is **compound component pattern**?

## **Security**

111. How do you prevent XSS attacks in React?
112. Is React safe from SQL injection?
113. How do you securely store tokens in React?
114. Difference between **localStorage, sessionStorage, and cookies**
115. How do you handle role-based authorization in React UI?


## **Build Tools & Development Workflow**

128. What build tools or bundlers are you familiar with (e.g., Webpack, Vite)? Explain their roles in a React project.
129. How do you configure Babel and Webpack for a React application?
130. What is the role of `.env` files in React development? How do you manage environment variables in a React app?
131. What is CI/CD, and how would you set up continuous integration and deployment for a React application?
132. Why is **Vite** faster than Webpack?
133. What is **tree shaking**?
134. How does code splitting work with dynamic imports?

## **Modern Ecosystem & Frameworks**

135. Difference between **CSR, SSR, SSG, and ISR**
136. What is **Next.js**, and why is it used with React?
137. Difference between `use client` and `use server` in Next.js 13+
138. What are React Server Components (RSC)?

## **Real-World Scenario Questions**

139. How would you build a reusable modal component?
140. How would you implement infinite scrolling?
141. How would you handle form validation in large forms?
142. How would you build a multi-step form?
143. How would you optimize a slow React page?
144. How would you handle global error handling?
145. How would you migrate a class-based app to hooks?

---

## **Categories Summary:**
- **Basic React Concepts**: 20 questions
- **Intermediate React Concepts**: 17 questions  
- **Advanced React Concepts**: 13 questions
- **React 18 & Modern React Features**: 8 questions
- **Hooks - Deep Dive**: 10 questions
- **Performance Optimization**: 16 questions
- **React Router**: 6 questions
- **State Management**: 7 questions
- **Data Fetching & Side Effects**: 6 questions
- **Architecture & Best Practices**: 7 questions
- **Security**: 5 questions
- **Testing**: 12 questions
- **Build Tools & Development Workflow**: 7 questions
- **Modern Ecosystem & Frameworks**: 4 questions
- **Real-World Scenario Questions**: 7 questions



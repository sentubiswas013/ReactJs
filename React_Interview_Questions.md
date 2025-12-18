# React Interview Questions - Comprehensive Guide

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

51. What is automatic batching in React 18?
52. What is Concurrent Rendering, and why was it introduced?
53. What is the `startTransition()` API and when would you use it?
54. What problem does `useTransition` solve?
55. What is React Strict Mode double rendering in development?
56. How does React 18 improve performance compared to React 17?
57. What is Selective Hydration in React?
58. Difference between legacy rendering and concurrent rendering?

## **Hooks - Deep Dive**

59. What rules must hooks follow, and why?
60. What problems can occur if hooks are used conditionally?
61. Difference between `useEffect`, `useLayoutEffect`, and `useInsertionEffect`
62. When should you avoid `useEffect`?
63. How does dependency array comparison work internally?
64. What is a stale closure problem in hooks?
65. How do you fix stale state issues in `useEffect`?
66. When would you use `useRef` instead of `useState`?
67. Can hooks replace all lifecycle methods? Explain mapping.
68. How do you share logic between components without HOCs?

## **Performance Optimization**

69. How can you optimize performance in React applications?
70. What strategies can you use to avoid unnecessary re-renders in React?
71. How would you deal with memory leaks in React components?
72. What causes a React component to re-render?
73. How do you prevent child re-renders when parent state changes?
74. How does `React.memo` work internally?
75. Difference between `useMemo` and `useCallback` with real use cases
76. Why does passing inline functions cause re-renders?
77. What is render thrashing?
78. How do keys affect reconciliation performance?
79. What happens if keys are not stable?
80. What is virtualization (windowing)?
81. How does React handle large lists efficiently?
82. What is debounce vs throttle in React?
83. How do you optimize first contentful paint (FCP)?
84. What is lazy hydration?

## **React Router**

85. What is React Router and how do you use it?
86. How would you implement routing in a React application?
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
97. How do you decide between context vs Redux?

## **Data Fetching & Side Effects**

98. How do you handle API calls in React without `useEffect`?
99. What problems does `useEffect` have with data fetching?
100. What is React Query / TanStack Query, and why is it preferred?
101. Difference between client-side caching and server state
102. How do you cancel API requests in React?
103. How do you handle race conditions in API calls?

## **Architecture & Best Practices**

104. How do you structure a large-scale React application?
105. What is feature-based folder structure?
106. What are smart vs dumb components?
107. What is container-presentational pattern?
108. How do you handle shared logic across multiple pages?
109. How do you design reusable components?
110. What is compound component pattern?

## **Security**

111. How do you prevent XSS attacks in React?
112. Is React safe from SQL injection?
113. How do you securely store tokens in React?
114. Difference between localStorage, sessionStorage, and cookies
115. How do you handle role-based authorization in React UI?

## **Testing**

116. What testing libraries do you use for React applications?
117. How do you test React components?
118. What is the difference between unit testing and integration testing in React?
119. How do you mock API calls in React tests?
120. What is snapshot testing in React?
121. How do you test React hooks?
122. What is the React Testing Library and how does it differ from Enzyme?
123. How do you test user interactions in React components?
124. What are the best practices for writing testable React components?
125. How do you test asynchronous operations in React?
126. What is end-to-end testing and how do you implement it for React apps?
127. How do you test React Router navigation?

## **Build Tools & Development Workflow**

128. What build tools or bundlers are you familiar with (e.g., Webpack, Vite)?
129. How do you configure Babel and Webpack for a React application?
130. What is the role of `.env` files in React development?
131. What is CI/CD, and how would you set up continuous integration and deployment for a React application?
132. Why is Vite faster than Webpack?
133. What is tree shaking?
134. How does code splitting work with dynamic imports?

## **Modern Ecosystem & Frameworks**

135. Difference between CSR, SSR, SSG, and ISR
136. What is Next.js, and why is it used with React?
137. Difference between `use client` and `use server` in Next.js 13+
138. What are React Server Components (RSC)?

## **Real-World Scenario Questions**

139. How would you build a reusable modal component?
140. How would you implement infinite scrolling?
141. How would you handle form validation in large forms?
142. How would you build a multi-step form?
143. How do you optimize a slow React page?
144. How would you migrate class components to hooks?
145. How would you handle global error handling?

---

## **Question Count by Category**

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

**Total Questions**: 145

---

## **Interview Preparation Tips**

### **For Junior Developers (0-2 years)**
Focus on: Basic React Concepts, Hooks basics, Component lifecycle, Props vs State

### **For Mid-Level Developers (2-5 years)**
Focus on: Performance Optimization, State Management, React Router, Testing, Architecture patterns

### **For Senior Developers (5+ years)**
Focus on: Advanced concepts, Modern React features, Architecture decisions, Real-world scenarios, Team leadership aspects

### **Most Frequently Asked Categories**
1. **Basic React Concepts** - Always asked
2. **Hooks** - Essential for modern React
3. **Performance Optimization** - Critical for production apps
4. **State Management** - Redux/Context API questions
5. **Real-World Scenarios** - Problem-solving abilities
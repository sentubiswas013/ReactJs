# ReactJS – Top 75 Most Asked Real-Time Interview Questions

## Performance Optimization (10 questions)

1. Your component fetches data from an API and re-renders multiple times unnecessarily. How do you optimize it?

2. You have a parent component with multiple children. Updating parent state causes all children to re-render. How do you prevent it?

3. Your app displays a **large list of items**, and performance drops when interacting with other components. How do you optimize rendering?

5. A component renders a list of 10,000 items causing lag. What techniques would you use?

6. Your app has expensive calculations in render. How do you prevent recalculating on every render?

7. Multiple components subscribe to the same context, but only one value changes. How do you prevent unnecessary re-renders?

8. How would you optimize a component that renders based on window scroll position?

9. Your React app has slow initial load time. What strategies would you implement?

10. How do you identify which components are causing performance bottlenecks?

41. How do you handle **debouncing or throttling** input changes (e.g., search box) to avoid unnecessary re-renders or API calls?

## State Management (12 questions)

11. How would you manage a **multi-step form** where users can navigate back and forth without losing data?

12. A child component deep in the component tree needs to update global state. How do you achieve this without prop drilling?

13. Your app has shared state, some frequently updated, some rarely. How do you decide between **Context API, Redux, or local state**?

14. How do you decide which values should be **props** vs **local state** in a component?

15. How would you **share state** across multiple unrelated components without prop drilling?

16. You need to sync state between localStorage and React state. How do you implement this?

17. How do you manage state for a shopping cart that persists across page refreshes?

18. Multiple components need to access and modify the same array. What's the best approach?

19. How would you implement undo/redo functionality in a React application?

20. Your form has 20+ fields. How do you manage state efficiently?

117. How would you implement controlled vs uncontrolled components?

144. How would you migrate from Redux to Context API?

## Data Fetching & API Integration (10 questions)

21. How do you implement conditional rendering for loading, error, and success states in a component?

22. You need to fetch data from **multiple APIs simultaneously** and display it once all are ready. How do you manage this in React?

23. Your component fetches data every few seconds (polling). How do you ensure it doesn't cause memory leaks or unnecessary re-renders?

24. How do you handle **error handling and fallback UI** for API failures in a React application?

25. You need to fetch data based on user input with a delay. How do you implement this?

26. How would you implement infinite scrolling with API pagination?

28. How do you handle race conditions when making multiple API calls?

30. How would you implement optimistic updates when posting data to an API?

42. You have a search input that should trigger API calls. How do you optimize this?

107. How would you implement retry logic for failed API calls?

## Component Architecture & Design (8 questions)

31. How do you manage a complex form with **dependent fields** and validation rules in React?

32. A component may throw errors during rendering. How do you prevent it from crashing the whole app?

34. How do you implement **lazy loading** for large components to improve initial load time?

35. You have a modal that needs to be triggered from multiple places. How do you structure this?

36. How would you build a reusable table component with sorting, filtering, and pagination?

38. How do you handle deeply nested component props (prop drilling)?

40. You need to build a component library. What patterns would you follow?

142. How would you migrate a class component to functional component with hooks?

## Lifecycle & Side Effects (8 questions)

51. You have a component that subscribes to a WebSocket for live updates. How do you handle cleanup when the component unmounts?

52. How do you run an effect only when a specific prop changes, not on every render?

53. You need to fetch data when component mounts and update when a prop changes. How do you structure this?

54. How do you handle cleanup for event listeners added in useEffect?

56. How would you implement a timer that starts when component mounts and cleans up on unmount?

58. How do you prevent useEffect from running on initial render?

60. How do you handle async operations in useEffect properly?

104. You have a memory leak in your React app. How do you identify and fix it?

## Authentication & Authorization (6 questions)

61. How would you implement authentication in React where the token persists across refreshes and user data is accessible globally?

62. You need to protect certain routes from unauthenticated users. How do you implement this?

63. How do you handle token refresh when it expires during user session?

67. How do you handle unauthorized API responses (401) globally?

68. You need to show different UI based on user permissions. How do you implement this?

75. You need to redirect users based on authentication status. How do you structure this?

## Routing & Navigation (5 questions)

71. You need to prevent navigation if form has unsaved changes. How do you implement this?

74. How do you implement nested routing with shared layouts?

76. How would you implement route-based code splitting?

78. How do you handle 404 pages and invalid routes?

79. You need to implement route guards that check permissions. How do you structure this?

## Testing Scenarios (5 questions)

81. How would you test a component that makes API calls?

82. You need to test a component that uses Context. How do you set this up?

83. How do you test custom hooks in isolation?

84. You need to test user interactions like clicks and form submissions. What's your approach?

89. How would you test a component with complex state logic?

## Event Handling & User Interactions (4 questions)

43. How would you implement drag-and-drop functionality in React?

45. How do you prevent multiple form submissions when user clicks submit button repeatedly?

48. How do you handle file uploads with progress tracking in React?

49. How would you implement click-outside detection for a dropdown menu?

## Error Handling & Debugging (3 questions)

101. Your production app crashes but works in development. How do you debug this?

102. You need to implement global error handling for all API calls. How do you structure this?

109. How do you debug why a component is re-rendering unnecessarily?

## Advanced Patterns (2 questions)

111. How would you implement a compound component pattern?

112. You need to share logic between multiple components. What patterns would you use?

## Mobile & Responsive (2 questions)

121. How do you handle responsive design in React components?

122. You need to render different components for mobile vs desktop. How do you implement this?

---

## Tips for Answering These Questions

1. **Start with the problem**: Acknowledge the issue or requirement
2. **Explain your approach**: Describe the solution strategy
3. **Provide code examples**: Show practical implementation
4. **Discuss trade-offs**: Mention pros/cons of your approach
5. **Consider edge cases**: Show you think about potential issues
6. **Mention alternatives**: Demonstrate knowledge of multiple solutions
7. **Talk about testing**: How would you verify the solution works
8. **Consider performance**: Discuss optimization strategies
9. **Think about maintainability**: Is the solution scalable and readable?
10. **Real-world context**: Relate to actual project experiences if possible

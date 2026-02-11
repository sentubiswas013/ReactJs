# ReactJS – Top 75 Most Asked Real-Time Interview Questions

## Performance Optimization (10 Questions)

1. Your component fetches data from an API and re-renders multiple times unnecessarily. How do you optimize it?

2. You have a parent component with multiple children. Updating parent state causes all children to re-render. How do you prevent it?

3. Your app displays a **large list of items**, and performance drops when interacting with other components. How do you optimize rendering?

4. A component renders a list of 10,000 items causing lag. What techniques would you use?

5. Your app has expensive calculations in render. How do you prevent recalculating on every render?

6. Multiple components subscribe to the same context, but only one value changes. How do you prevent unnecessary re-renders?

7. How would you optimize a component that renders based on window scroll position?

8. Your React app has slow initial load time. What strategies would you implement?

9. How do you identify which components are causing performance bottlenecks?

10. How do you optimize performance for **re-rendering charts or data-heavy visualizations** in React?

## State Management (12 Questions)

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

21. How do you handle deeply nested component props (prop drilling)?

22. How would you migrate from Redux to Context API?

## Data Fetching & API Integration (10 Questions)

23. How do you implement conditional rendering for loading, error, and success states in a component?

24. You need to fetch data from **multiple APIs simultaneously** and display it once all are ready. How do you manage this in React?

25. Your component fetches data every few seconds (polling). How do you ensure it doesn't cause memory leaks or unnecessary re-renders?

26. How do you handle **error handling and fallback UI** for API failures in a React application?

27. You need to fetch data based on user input with a delay. How do you implement this?

28. How would you implement infinite scrolling with API pagination?

29. How do you handle race conditions when making multiple API calls?

30. How would you implement optimistic updates when posting data to an API?

31. You need to implement global error handling for all API calls. How do you structure this?

32. How would you implement retry logic for failed API calls?

## Component Architecture & Design (8 Questions)

33. How do you manage a complex form with **dependent fields** and validation rules in React?

34. A component may throw errors during rendering. How do you prevent it from crashing the whole app?

35. How would you integrate a **third-party library** that manipulates the DOM into a React component safely?

36. How do you implement **lazy loading** for large components to improve initial load time?

37. You have a modal that needs to be triggered from multiple places. How do you structure this?

38. How would you build a reusable table component with sorting, filtering, and pagination?

39. How would you structure a dashboard with multiple widgets that fetch their own data?

40. You need to build a component library. What patterns would you follow?

## Event Handling & User Interactions (6 Questions)

41. How do you handle **debouncing or throttling** input changes (e.g., search box) to avoid unnecessary re-renders or API calls?

42. You have a search input that should trigger API calls. How do you optimize this?

43. How would you implement drag-and-drop functionality in React?

44. How do you prevent multiple form submissions when user clicks submit button repeatedly?

45. How would you implement a real-time search with autocomplete suggestions?

46. How would you implement click-outside detection for a dropdown menu?

## Lifecycle & Side Effects (8 Questions)

47. You have a component that subscribes to a WebSocket for live updates. How do you handle cleanup when the component unmounts?

48. How do you run an effect only when a specific prop changes, not on every render?

49. You need to fetch data when component mounts and update when a prop changes. How do you structure this?

50. How do you handle cleanup for event listeners added in useEffect?

51. How would you implement a timer that starts when component mounts and cleans up on unmount?

52. You need to call an API when component mounts, but only if a condition is true. How do you structure this?

53. How do you prevent useEffect from running on initial render?

54. How do you handle async operations in useEffect properly?

## Authentication & Authorization (6 Questions)

55. How would you implement authentication in React where the token persists across refreshes and user data is accessible globally?

56. You need to protect certain routes from unauthenticated users. How do you implement this?

57. How do you handle token refresh when it expires during user session?

58. How do you handle unauthorized API responses (401) globally?

59. You need to show different UI based on user permissions. How do you implement this?

60. How would you implement social login (Google, Facebook) in React?

## Routing & Navigation (5 Questions)

61. You need to prevent navigation if form has unsaved changes. How do you implement this?

62. How would you implement breadcrumbs that update based on current route?

63. How do you implement nested routing with shared layouts?

64. You need to redirect users based on authentication status. How do you structure this?

65. How do you handle 404 pages and invalid routes?

## Testing Scenarios (4 Questions)

66. How would you test a component that makes API calls?

67. You need to test a component that uses Context. How do you set this up?

68. How do you test custom hooks in isolation?

69. You need to test user interactions like clicks and form submissions. What's your approach?

## Advanced Patterns (3 Questions)

70. How would you implement a compound component pattern?

71. You need to share logic between multiple components. What patterns would you use?

72. How would you implement controlled vs uncontrolled components?

## Integration & Migration (3 Questions)

73. How would you migrate a class component to functional component with hooks?

74. You need to integrate React into an existing jQuery application. How do you approach this?

75. How would you gradually adopt TypeScript in a JavaScript React project?

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

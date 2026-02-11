# Angular – Top 75 Most Asked Real-Time Interview Questions

## Data Fetching & HTTP

1. You need to load data from an API and display it in a component. How do you handle the asynchronous call and errors?

2. Your component needs to subscribe to multiple Observables and perform actions when all emit data. How do you manage it?

3. You need to handle **global error handling** for HTTP requests in your Angular app. How do you do it?

4. How would you implement retry logic for failed HTTP requests?

5. You need to cancel pending HTTP requests when user navigates away. How do you implement this?

6. You need to implement request/response interceptors for adding auth tokens. How do you structure this?

7. How would you implement caching for HTTP requests to reduce API calls?

8. How do you handle parallel API calls and wait for all to complete before proceeding?

## Performance Optimization

9. How would you optimize performance for a component that renders a **large list of items**?

10. Your Angular app is slow due to frequent change detection cycles. How do you optimize it?

11. Your app has several frequently updated UI elements. How do you **optimize change detection** to avoid unnecessary re-renders?

12. How would you implement virtual scrolling for a list with thousands of items?

13. You have a component with expensive computations in the template. How do you optimize it?

14. How do you implement OnPush change detection strategy effectively?

15. Your app has a slow initial load time. What strategies would you implement?

16. How would you optimize bundle size in an Angular application?

## Forms & Validation

17. You have a form with multiple steps and validations. How do you manage the form state across steps?

18. How do you decide when to use **Reactive Forms vs Template-Driven Forms** in Angular?

19. How do you implement **dynamic forms** where fields change based on previous user input?

20. You need to create custom validators that make async API calls. How do you implement this?

21. How would you implement cross-field validation (e.g., password confirmation)?

22. You have a form with 50+ fields. How do you manage it efficiently?

23. You need to handle nested form groups and form arrays. How do you structure this?

## Component Communication & Architecture

24. A child component needs to communicate with a deeply nested parent component. How do you handle this in Angular?

25. How do you manage **state across multiple unrelated components** in Angular?

26. You have a service that provides shared data across multiple components. How do you ensure it stays updated correctly?

27. How would you implement a notification system that can be triggered from anywhere in the app?

28. You need to pass data between sibling components. What are your options?

29. How do you implement a modal service that can be opened from any component?

30. How would you implement content projection for flexible component composition?

## Routing & Navigation

31. How would you handle **lazy loading** of modules to improve app startup time?

32. How would you implement **role-based access control** in Angular routes and components?

33. You need to prevent navigation if form has unsaved changes. How do you implement this?

34. How do you implement route guards that make async API calls?

35. You need to preload certain lazy-loaded modules. How do you configure this?

36. How do you handle 404 pages and route redirects?

37. You need to implement nested routing with shared layouts. How do you structure this?

## RxJS & Observables

38. How do you prevent memory leaks when subscribing to Observables in components?

39. How would you handle **debouncing or throttling** user input (e.g., search box) to avoid excessive API calls?

40. You need to combine data from multiple Observables. What operators would you use?

41. How do you handle errors in Observable streams without breaking the stream?

42. You need to implement a search with autocomplete using Observables. How do you structure this?

43. You need to chain multiple dependent API calls. How do you implement this with RxJS?

44. How do you share Observable subscriptions across multiple subscribers?

## Authentication & Authorization

45. How do you implement authentication with JWT in an Angular application?

46. You need to protect routes from unauthenticated users. How do you implement this?

47. How do you handle token refresh when it expires during user session?

48. You need to redirect users based on their role after login. How do you structure this?

49. How do you handle unauthorized responses (401/403) globally?

50. You need to show/hide UI elements based on user permissions. How do you implement this?

## State Management

51. You need to implement global state management. When would you use NgRx vs Services?

52. You need to persist state across page refreshes. How do you implement this?

53. How do you handle optimistic updates when posting data to API?

54. You need to sync state between localStorage and application state. How do you implement this?

55. How would you implement a shopping cart that persists across sessions?

## Testing

56. How would you test a component that makes HTTP calls?

57. You need to test a service that depends on other services. How do you set this up?

58. How do you test components with async operations?

59. You need to test route guards. How do you structure your tests?

60. How would you test custom validators?

## Change Detection & Lifecycle

61. How do you implement **conditional rendering** for loading, error, and success states in Angular templates?

62. You need to run code after view is fully initialized. Which lifecycle hook do you use?

63. How do you detect changes in @Input properties?

64. You need to perform cleanup when component is destroyed. How do you implement this?

65. How would you trigger change detection manually when needed?

66. You need to run code only once when component initializes. What's the best approach?

## Directives & Pipes

67. A third-party library directly manipulates the DOM. How do you safely integrate it with Angular?

68. You need to create a directive that adds behavior to multiple elements. How do you implement this?

69. How would you create a structural directive similar to *ngIf?

70. You need to create a custom pipe that makes async operations. How do you handle this?

## Error Handling & Debugging

71. How do you implement global error handling in Angular?

72. You need to log errors to an external service. How do you implement this?

73. You have a memory leak in your Angular app. How do you identify and fix it?

## Migration & Integration

74. How would you **migrate a large AngularJS application to Angular** while minimizing downtime and errors?

## Advanced Patterns & Architecture

75. You need to implement server-side rendering (SSR) with Angular Universal. What are the considerations?

---

## Tips for Answering These Questions

1. **Understand the problem**: Clarify requirements before jumping to solution
2. **Explain your approach**: Describe the Angular-specific solution strategy
3. **Use Angular terminology**: Mention services, components, modules, decorators, etc.
4. **Provide code examples**: Show practical implementation with TypeScript
5. **Discuss RxJS**: Many Angular solutions involve Observables
6. **Consider change detection**: Discuss performance implications
7. **Mention lifecycle hooks**: Show understanding of component lifecycle
8. **Talk about dependency injection**: Explain service architecture
9. **Consider testing**: How would you test the solution?
10. **Discuss alternatives**: Show knowledge of multiple approaches (e.g., NgRx vs Services)
11. **Think about scalability**: Will the solution work in a large application?
12. **Address TypeScript**: Leverage strong typing in your solutions
13. **Consider Angular CLI**: Mention relevant CLI commands and configurations
14. **Discuss best practices**: Follow Angular style guide recommendations
15. **Real-world context**: Relate to actual project experiences if possible

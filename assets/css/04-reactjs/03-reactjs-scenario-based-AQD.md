# ReactJS – Practical / Situation-Based / Real-World Questions

## Performance Optimization

1. Your component fetches data from an API and re-renders multiple times unnecessarily. How do you optimize it?

2. You have a parent component with multiple children. Updating parent state causes all children to re-render. How do you prevent it?

3. Your app displays a **large list of items**, and performance drops when interacting with other components. How do you optimize rendering?

4. How do you optimize performance for **re-rendering charts or data-heavy visualizations** in React?

5. A component renders a list of 10,000 items causing lag. What techniques would you use?

6. Your app has expensive calculations in render. How do you prevent recalculating on every render?

7. Multiple components subscribe to the same context, but only one value changes. How do you prevent unnecessary re-renders?

8. How would you optimize a component that renders based on window scroll position?

9. Your React app has slow initial load time. What strategies would you implement?

10. How do you identify which components are causing performance bottlenecks?

## State Management

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

## Data Fetching & API Integration

21. How do you implement conditional rendering for loading, error, and success states in a component?

22. You need to fetch data from **multiple APIs simultaneously** and display it once all are ready. How do you manage this in React?

23. Your component fetches data every few seconds (polling). How do you ensure it doesn't cause memory leaks or unnecessary re-renders?

24. How do you handle **error handling and fallback UI** for API failures in a React application?

25. You need to fetch data based on user input with a delay. How do you implement this?

26. How would you implement infinite scrolling with API pagination?

27. Your API returns paginated data. How do you manage loading more data while keeping previous data?

28. How do you handle race conditions when making multiple API calls?

29. You need to prefetch data for the next page before user navigates. How do you implement this?

30. How would you implement optimistic updates when posting data to an API?

## Component Architecture & Design

31. How do you manage a complex form with **dependent fields** and validation rules in React?

32. A component may throw errors during rendering. How do you prevent it from crashing the whole app?

33. How would you integrate a **third-party library** that manipulates the DOM into a React component safely?

34. How do you implement **lazy loading** for large components to improve initial load time?

35. You have a modal that needs to be triggered from multiple places. How do you structure this?

36. How would you build a reusable table component with sorting, filtering, and pagination?

37. You need to create a form component that works with different data structures. How do you make it flexible?

38. How do you handle deeply nested component props (prop drilling)?

39. How would you structure a dashboard with multiple widgets that fetch their own data?

40. You need to build a component library. What patterns would you follow?

## Event Handling & User Interactions

41. How do you handle **debouncing or throttling** input changes (e.g., search box) to avoid unnecessary re-renders or API calls?

42. You have a search input that should trigger API calls. How do you optimize this?

43. How would you implement drag-and-drop functionality in React?

44. You need to handle keyboard shortcuts globally in your app. How do you implement this?

45. How do you prevent multiple form submissions when user clicks submit button repeatedly?

46. How would you implement a real-time search with autocomplete suggestions?

47. You need to track user activity and send analytics. How do you implement this without affecting performance?

48. How do you handle file uploads with progress tracking in React?

49. How would you implement click-outside detection for a dropdown menu?

50. You need to implement a complex gesture (e.g., swipe) on mobile. How do you approach this?

## Lifecycle & Side Effects

51. You have a component that subscribes to a WebSocket for live updates. How do you handle cleanup when the component unmounts?

52. How do you run an effect only when a specific prop changes, not on every render?

53. You need to fetch data when component mounts and update when a prop changes. How do you structure this?

54. How do you handle cleanup for event listeners added in useEffect?

55. You have multiple useEffect hooks that depend on each other. How do you manage this?

56. How would you implement a timer that starts when component mounts and cleans up on unmount?

57. You need to call an API when component mounts, but only if a condition is true. How do you structure this?

58. How do you prevent useEffect from running on initial render?

59. You have an effect that should run after state updates are committed to DOM. How do you ensure this?

60. How do you handle async operations in useEffect properly?

## Authentication & Authorization

61. How would you implement authentication in React where the token persists across refreshes and user data is accessible globally?

62. You need to protect certain routes from unauthenticated users. How do you implement this?

63. How do you handle token refresh when it expires during user session?

64. You need to redirect users based on their role after login. How do you structure this?

65. How would you implement "Remember Me" functionality?

66. You need to logout user across all tabs when they logout in one. How do you achieve this?

67. How do you handle unauthorized API responses (401) globally?

68. You need to show different UI based on user permissions. How do you implement this?

69. How would you implement social login (Google, Facebook) in React?

70. You need to implement two-factor authentication flow. How do you structure the components?

## Routing & Navigation

71. You need to prevent navigation if form has unsaved changes. How do you implement this?

72. How would you implement breadcrumbs that update based on current route?

73. You need to pass state between routes without URL parameters. How do you do this?

74. How do you implement nested routing with shared layouts?

75. You need to redirect users based on authentication status. How do you structure this?

76. How would you implement route-based code splitting?

77. You need to preserve scroll position when navigating back. How do you implement this?

78. How do you handle 404 pages and invalid routes?

79. You need to implement route guards that check permissions. How do you structure this?

80. How would you implement dynamic routing based on API data?

## Testing Scenarios

81. How would you test a component that makes API calls?

82. You need to test a component that uses Context. How do you set this up?

83. How do you test custom hooks in isolation?

84. You need to test user interactions like clicks and form submissions. What's your approach?

85. How would you test a component that uses timers or intervals?

86. You need to test error boundaries. How do you trigger errors in tests?

87. How do you test components that use third-party libraries?

88. You need to test routing behavior. How do you set this up?

89. How would you test a component with complex state logic?

90. You need to achieve high test coverage. What's your testing strategy?

## Real-Time Features

91. How would you implement a real-time chat application in React?

92. You need to show live notifications when data changes on server. How do you implement this?

93. How would you implement collaborative editing (like Google Docs)?

94. You need to show online/offline status of users. How do you implement this?

95. How would you implement a live dashboard that updates every few seconds?

96. You need to handle WebSocket reconnection when connection drops. How do you manage this?

97. How would you implement presence indicators (who's viewing the page)?

98. You need to sync data across multiple browser tabs. How do you achieve this?

99. How would you implement real-time form validation with server checks?

100. You need to implement live search results as user types. How do you optimize this?

## Error Handling & Debugging

101. Your production app crashes but works in development. How do you debug this?

102. You need to implement global error handling for all API calls. How do you structure this?

103. How would you implement error logging and reporting in production?

104. You have a memory leak in your React app. How do you identify and fix it?

105. How do you handle errors in async operations (promises)?

106. You need to show user-friendly error messages for different error types. How do you implement this?

107. How would you implement retry logic for failed API calls?

108. You need to track and report JavaScript errors to a service. How do you implement this?

109. How do you debug why a component is re-rendering unnecessarily?

110. You have a race condition causing bugs. How do you identify and fix it?

## Advanced Patterns

111. How would you implement a compound component pattern?

112. You need to share logic between multiple components. What patterns would you use?

113. How would you implement render props pattern and when would you use it?

114. You need to enhance a component with additional functionality. How do you use HOCs?

115. How would you implement a provider pattern for a feature?

116. You need to build a form library. What patterns would you implement?

117. How would you implement controlled vs uncontrolled components?

118. You need to implement a plugin system in React. How do you structure this?

119. How would you implement dependency injection in React?

120. You need to implement a state machine for complex UI flows. How do you approach this?

## Mobile & Responsive

121. How do you handle responsive design in React components?

122. You need to render different components for mobile vs desktop. How do you implement this?

123. How would you implement touch gestures in a React app?

124. You need to optimize your React app for mobile performance. What strategies would you use?

125. How do you handle orientation changes in React?

126. You need to implement a mobile-first navigation. How do you structure this?

127. How would you implement lazy loading images for mobile?

128. You need to handle different screen sizes efficiently. What's your approach?

129. How do you implement pull-to-refresh functionality?

130. You need to optimize bundle size for mobile. What techniques would you use?

## Accessibility

131. How do you ensure your React components are accessible?

132. You need to implement keyboard navigation for a custom dropdown. How do you do this?

133. How would you handle focus management in a modal?

134. You need to implement screen reader support. What considerations do you make?

135. How do you test accessibility in React components?

136. You need to implement skip links for keyboard users. How do you structure this?

137. How would you handle ARIA attributes dynamically?

138. You need to ensure color contrast meets standards. How do you implement this?

139. How do you handle form validation messages for screen readers?

140. You need to implement accessible data tables. What's your approach?

## Integration & Migration

141. You need to integrate React into an existing jQuery application. How do you approach this?

142. How would you migrate a class component to functional component with hooks?

143. You need to integrate a non-React library that uses global state. How do you handle this?

144. How would you migrate from Redux to Context API?

145. You need to integrate React with a backend framework's templating. How do you do this?

146. How would you gradually adopt TypeScript in a JavaScript React project?

147. You need to integrate React with a CMS. What's your approach?

148. How would you migrate from Create React App to Vite?

149. You need to integrate React with Web Components. How do you handle this?

150. How would you implement micro-frontends with React?

## Bonus: Tricky Scenarios

151. A component needs to measure its own dimensions after render. How do you implement this?

152. You need to implement a virtualized list with variable item heights. How do you approach this?

153. How would you implement a time-travel debugger for state changes?

154. You need to implement a component that works with both React and React Native. How do you structure this?

155. How would you implement server-side rendering for a React app?

156. You need to implement progressive enhancement. How do you ensure the app works without JavaScript?

157. How would you implement A/B testing in a React application?

158. You need to implement feature flags. How do you structure this?

159. How would you implement internationalization (i18n) with dynamic language switching?

160. You need to implement a design system with theme switching. How do you structure this?

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
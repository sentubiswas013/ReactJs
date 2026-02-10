# Angular – Practical / Situation-Based / Real-World Questions

## Data Fetching & HTTP

1. You need to load data from an API and display it in a component. How do you handle the asynchronous call and errors?

2. Your component needs to subscribe to multiple Observables and perform actions when all emit data. How do you manage it?

3. You need to handle **global error handling** for HTTP requests in your Angular app. How do you do it?

4. How would you implement retry logic for failed HTTP requests?

5. You need to cancel pending HTTP requests when user navigates away. How do you implement this?

6. How do you handle race conditions when making multiple sequential API calls?

7. You need to implement request/response interceptors for adding auth tokens. How do you structure this?

8. How would you implement caching for HTTP requests to reduce API calls?

9. You need to upload files with progress tracking. How do you implement this in Angular?

10. How do you handle parallel API calls and wait for all to complete before proceeding?

## Performance Optimization

11. How would you optimize performance for a component that renders a **large list of items**?

12. Your Angular app is slow due to frequent change detection cycles. How do you optimize it?

13. Your app has several frequently updated UI elements. How do you **optimize change detection** to avoid unnecessary re-renders?

14. How would you implement virtual scrolling for a list with thousands of items?

15. You have a component with expensive computations in the template. How do you optimize it?

16. How do you identify which components are causing performance bottlenecks?

17. Your app has a slow initial load time. What strategies would you implement?

18. How would you optimize bundle size in an Angular application?

19. You have multiple components subscribing to the same Observable. How do you prevent duplicate API calls?

20. How do you implement OnPush change detection strategy effectively?

## Forms & Validation

21. You have a form with multiple steps and validations. How do you manage the form state across steps?

22. How do you decide when to use **Reactive Forms vs Template-Driven Forms** in Angular?

23. How do you implement **dynamic forms** where fields change based on previous user input?

24. You need to create custom validators that make async API calls. How do you implement this?

25. How would you implement cross-field validation (e.g., password confirmation)?

26. You have a form with 50+ fields. How do you manage it efficiently?

27. How do you implement conditional validation based on other field values?

28. You need to save form data as draft while user fills it. How do you implement auto-save?

29. How would you implement a form builder that generates forms from JSON configuration?

30. You need to handle nested form groups and form arrays. How do you structure this?

## Component Communication & Architecture

31. A child component needs to communicate with a deeply nested parent component. How do you handle this in Angular?

32. How do you manage **state across multiple unrelated components** in Angular?

33. You have a service that provides shared data across multiple components. How do you ensure it stays updated correctly?

34. How would you implement a notification system that can be triggered from anywhere in the app?

35. You need to pass data between sibling components. What are your options?

36. How do you implement a modal service that can be opened from any component?

37. You need to create a reusable data table component with sorting and filtering. How do you structure it?

38. How would you implement content projection for flexible component composition?

39. You have a component that needs to render different templates based on input. How do you implement this?

40. How do you handle circular dependencies between services?

## Routing & Navigation

41. How would you handle **lazy loading** of modules to improve app startup time?

42. How would you implement **role-based access control** in Angular routes and components?

43. You need to prevent navigation if form has unsaved changes. How do you implement this?

44. How do you implement route guards that make async API calls?

45. You need to preload certain lazy-loaded modules. How do you configure this?

46. How would you implement breadcrumbs that update based on current route?

47. You need to pass complex data between routes without URL parameters. How do you do this?

48. How do you handle 404 pages and route redirects?

49. You need to implement nested routing with shared layouts. How do you structure this?

50. How would you implement route-based animations?

## RxJS & Observables

51. How do you prevent memory leaks when subscribing to Observables in components?

52. How would you handle **debouncing or throttling** user input (e.g., search box) to avoid excessive API calls?

53. You need to combine data from multiple Observables. What operators would you use?

54. How do you handle errors in Observable streams without breaking the stream?

55. You need to implement a search with autocomplete using Observables. How do you structure this?

56. How would you implement polling that stops when component is destroyed?

57. You need to chain multiple dependent API calls. How do you implement this with RxJS?

58. How do you share Observable subscriptions across multiple subscribers?

59. You need to implement undo/redo functionality using RxJS. How do you approach this?

60. How would you implement a real-time data stream with WebSocket using Observables?

## Authentication & Authorization

61. How do you implement authentication with JWT in an Angular application?

62. You need to protect routes from unauthenticated users. How do you implement this?

63. How do you handle token refresh when it expires during user session?

64. You need to redirect users based on their role after login. How do you structure this?

65. How would you implement "Remember Me" functionality?

66. You need to logout user across all tabs when they logout in one. How do you achieve this?

67. How do you handle unauthorized responses (401/403) globally?

68. You need to show/hide UI elements based on user permissions. How do you implement this?

69. How would you implement OAuth2 authentication flow?

70. You need to implement session timeout with warning. How do you structure this?

## State Management

71. You need to implement global state management. When would you use NgRx vs Services?

72. How would you implement undo/redo functionality in your application?

73. You need to persist state across page refreshes. How do you implement this?

74. How do you handle optimistic updates when posting data to API?

75. You need to sync state between localStorage and application state. How do you implement this?

76. How would you implement a shopping cart that persists across sessions?

77. You need to manage complex state with multiple nested objects. What's your approach?

78. How do you handle state for a multi-step wizard with validation?

79. You need to implement time-travel debugging for state changes. How do you approach this?

80. How would you structure state for a real-time collaborative application?

## Testing

81. How would you test a component that makes HTTP calls?

82. You need to test a service that depends on other services. How do you set this up?

83. How do you test components with async operations?

84. You need to test route guards. How do you structure your tests?

85. How would you test custom validators?

86. You need to test components with complex user interactions. What's your approach?

87. How do you test pipes with different input scenarios?

88. You need to test error handling in services. How do you trigger errors in tests?

89. How would you test components that use OnPush change detection?

90. You need to achieve high test coverage. What's your testing strategy?

## Change Detection & Lifecycle

91. How do you implement **conditional rendering** for loading, error, and success states in Angular templates?

92. You need to run code after view is fully initialized. Which lifecycle hook do you use?

93. How do you detect changes in @Input properties?

94. You need to perform cleanup when component is destroyed. How do you implement this?

95. How would you trigger change detection manually when needed?

96. You need to access child components after they're initialized. How do you do this?

97. How do you handle changes in projected content?

98. You need to perform actions after every change detection cycle. How do you implement this?

99. How would you optimize a component that receives frequent input changes?

100. You need to run code only once when component initializes. What's the best approach?

## Directives & Pipes

101. A third-party library directly manipulates the DOM. How do you safely integrate it with Angular?

102. You need to create a directive that adds behavior to multiple elements. How do you implement this?

103. How would you create a structural directive similar to *ngIf?

104. You need to create a custom pipe that makes async operations. How do you handle this?

105. How do you create a directive that listens to multiple events?

106. You need to create a reusable tooltip directive. How do you structure it?

107. How would you create a directive that validates form inputs?

108. You need to create a pipe that formats data based on user locale. How do you implement this?

109. How do you create a directive that modifies host element styles dynamically?

110. You need to create a highlight directive that works with search terms. How do you implement this?

## Modules & Dependency Injection

111. How do you organize feature modules in a large Angular application?

112. You need to provide different implementations of a service based on environment. How do you do this?

113. How would you implement a plugin architecture using Angular modules?

114. You need to share services between lazy-loaded modules. How do you structure this?

115. How do you implement tree-shakable providers?

116. You need to inject different service implementations based on runtime conditions. How do you do this?

117. How would you structure a shared module that's used across the app?

118. You need to provide a service at component level vs module level. When would you choose each?

119. How do you handle circular dependencies between modules?

120. You need to dynamically load modules at runtime. How do you implement this?

## Error Handling & Debugging

121. How do you implement global error handling in Angular?

122. Your production app crashes but works in development. How do you debug this?

123. You need to log errors to an external service. How do you implement this?

124. How do you handle errors in resolvers?

125. You need to show user-friendly error messages for different error types. How do you implement this?

126. How would you implement retry logic with exponential backoff?

127. You have a memory leak in your Angular app. How do you identify and fix it?

128. How do you debug why change detection is running too frequently?

129. You need to handle errors in lazy-loaded modules. How do you structure this?

130. How would you implement error boundaries similar to React?

## Real-Time Features

131. How would you implement WebSocket communication in Angular?

132. You need to show live notifications when data changes on server. How do you implement this?

133. How would you implement a real-time chat application?

134. You need to handle WebSocket reconnection when connection drops. How do you manage this?

135. How would you implement Server-Sent Events (SSE) in Angular?

136. You need to sync data across multiple browser tabs. How do you achieve this?

137. How would you implement presence indicators (who's viewing the page)?

138. You need to implement collaborative editing features. How do you structure this?

139. How would you implement a live dashboard that updates every few seconds?

140. You need to handle real-time form validation with server checks. How do you implement this?

## Internationalization & Localization

141. How do you implement multi-language support in Angular?

142. You need to switch languages dynamically without page reload. How do you implement this?

143. How would you handle date and number formatting for different locales?

144. You need to load translations lazily for better performance. How do you structure this?

145. How do you handle right-to-left (RTL) languages?

146. You need to translate dynamic content from API. How do you handle this?

147. How would you implement language detection based on user browser?

148. You need to handle pluralization rules for different languages. How do you implement this?

149. How do you manage translation files for a large application?

150. You need to implement currency conversion with localization. How do you structure this?

## Accessibility

151. How do you ensure your Angular components are accessible?

152. You need to implement keyboard navigation for a custom component. How do you do this?

153. How would you handle focus management in a modal?

154. You need to implement screen reader support. What considerations do you make?

155. How do you test accessibility in Angular components?

156. You need to implement ARIA attributes dynamically. How do you structure this?

157. How would you ensure form validation messages are accessible?

158. You need to implement skip navigation links. How do you structure this?

159. How do you handle accessible data tables with sorting and filtering?

160. You need to ensure color contrast meets WCAG standards. How do you implement this?

## Migration & Integration

161. How would you **migrate a large AngularJS application to Angular** while minimizing downtime and errors?

162. You need to integrate Angular with a legacy backend system. How do you approach this?

163. How would you gradually migrate from JavaScript to TypeScript?

164. You need to integrate a React component into Angular. How do you handle this?

165. How would you migrate from template-driven forms to reactive forms?

166. You need to integrate Angular with a CMS. What's your approach?

167. How would you upgrade from an older Angular version to the latest?

168. You need to integrate Angular with Web Components. How do you handle this?

169. How would you migrate from NgRx to a simpler state management solution?

170. You need to integrate Angular with a micro-frontend architecture. How do you structure this?

## Advanced Patterns & Architecture

171. How would you implement a micro-frontend architecture with Angular?

172. You need to implement a plugin system. How do you structure this?

173. How would you implement feature flags in Angular?

174. You need to implement A/B testing. How do you structure this?

175. How would you implement a design system with theming?

176. You need to implement server-side rendering (SSR) with Angular Universal. What are the considerations?

177. How would you implement progressive web app (PWA) features?

178. You need to implement code splitting beyond lazy loading. How do you approach this?

179. How would you implement a monorepo structure for multiple Angular apps?

180. You need to implement dynamic component loading. How do you structure this?

## Mobile & Responsive

181. How do you handle responsive design in Angular applications?

182. You need to render different components for mobile vs desktop. How do you implement this?

183. How would you optimize Angular app for mobile performance?

184. You need to implement touch gestures. How do you handle this?

185. How do you handle orientation changes?

186. You need to implement pull-to-refresh functionality. How do you structure this?

187. How would you implement lazy loading images for mobile?

188. You need to detect device capabilities and adjust UI. How do you implement this?

189. How do you optimize bundle size for mobile networks?

190. You need to implement offline functionality. How do you structure this?

## Build & Deployment

191. How do you optimize build time for a large Angular application?

192. You need to implement environment-specific configurations. How do you structure this?

193. How would you implement continuous deployment for Angular apps?

194. You need to implement feature toggles that work across environments. How do you do this?

195. How do you handle secrets and API keys in Angular applications?

196. You need to implement source maps for production debugging. What are the considerations?

197. How would you implement differential loading for modern vs legacy browsers?

198. You need to analyze and reduce bundle size. What tools and techniques would you use?

199. How do you implement caching strategies for Angular apps?

200. You need to implement blue-green deployment. How do you structure this?

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
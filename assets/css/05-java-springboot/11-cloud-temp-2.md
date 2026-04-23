# React Interview Questions — React + Redux Interview Scenarios (Q171–Q200)

---

## 171. When to use Redux vs Context?

- **Context** — theme, locale, auth state (low-frequency updates).
- **Redux** — large apps, complex state logic, frequent updates, need DevTools, sharing state across many distant components.

Default to Context/useState, add Redux when you feel the pain.

---

## 172. How to optimize a React app?

1. Profile first (DevTools).
2. `React.memo` for expensive components.
3. `useMemo`/`useCallback` for stable references.
4. Code split with `lazy()`.
5. Virtualize long lists.
6. Debounce heavy handlers.
7. Optimize images.
8. Reduce bundle size.

---

## 173. How to prevent unnecessary renders?

1. `React.memo` on child components.
2. `useCallback` for function props.
3. `useMemo` for object/array props.
4. Specific `useSelector` selectors (not whole state).
5. Split large components.
6. Avoid inline object literals in JSX.

---

## 174. How React handles large-scale apps?

1. Feature-based folder structure.
2. Code splitting per route.
3. RTK Query for server state.
4. `React.memo` and virtualization for performance.
5. TypeScript for type safety.
6. Storybook for component docs.
7. Micro-frontend architecture for very large teams.

---

## 175. Folder structure for scalable app?

```
src/
  features/[feature]/{component, slice, api, hooks, types}
  components/ui/       # shared UI
  app/                 # store, router, providers
  hooks/               # global custom hooks
  utils/               # helpers
```

---

## 176. How to design reusable components?

1. Single responsibility.
2. Configurable via props.
3. Compound component pattern for complex widgets.
4. Forward refs when needed.
5. No hard-coded data.
6. Document with Storybook.
7. Prop types / TypeScript interfaces.

---

## 177. How to handle API calls in React?

1. `useEffect` + `useState` (basic).
2. Custom `useApi` hook.
3. React Query / SWR (server state).
4. RTK Query (with Redux).

RTK Query or React Query is recommended — handles caching, revalidation, loading/error states automatically.

---

## 178. Axios vs fetch?

- **fetch** — built-in, no dependency, returns `Response` object (need `.json()`), no request cancellation (use `AbortController`).
- **Axios** — automatic JSON parsing, request/response interceptors, better error handling (rejects on 4xx/5xx), request cancellation, timeout support.

---

## 179. How to manage global state?

1. `useState`/`useReducer` — local state.
2. Context — low-frequency global (theme, auth).
3. Redux / Zustand / Jotai — complex global state.
4. React Query / RTK Query — server state.

> Don't put server state in Redux.

---

## 180. What is the best state management?

No universal best. Guidelines:

- Local state → `useState`
- Shared simple → Context
- Complex global → Redux Toolkit / Zustand
- Server data → React Query / RTK Query
- Atomic state → Jotai / Recoil

Choose based on team, app complexity, and specific needs.

---

## 181. How to design a dashboard app?

1. Route-based code splitting.
2. RTK Query for data.
3. Memoized chart components.
4. Virtualized tables.
5. Skeleton loaders.
6. Real-time: WebSocket + Redux.
7. Role-based widget visibility.
8. Error boundaries per widget.

---

## 182. How to handle forms at scale?

React Hook Form (minimal re-renders, validation) or Formik. Schema validation with Zod or Yup. For complex multi-step forms: `useReducer` or form state machine. Uncontrolled inputs with refs for performance in large forms.

---

## 183. Controlled vs uncontrolled forms?

- **Controlled** — every keystroke goes through React state (instant validation, formatting, disabled states).
- **Uncontrolled** — refs read value on submit (less code, better performance for simple forms).

React Hook Form uses uncontrolled inputs by default.

---

## 184. Form libraries: Formik vs React Hook Form?

- **React Hook Form** — minimal re-renders (uncontrolled), smaller bundle, better performance, simpler API, growing adoption.
- **Formik** — controlled, more established, larger ecosystem.

React Hook Form recommended for new projects.

---

## 185. How to handle authentication in React?

1. Login → receive JWT + refresh token.
2. Store access token in memory (or `HttpOnly` cookie).
3. Auth context/Redux for `isAuthenticated`, `user`.
4. `PrivateRoute` protects routes.
5. Axios interceptor attaches token.
6. Token refresh on 401 response.

---

## 186. JWT storage best practices?

- **Best** — `HttpOnly` cookie (inaccessible to XSS).
- **Acceptable** — memory (state variable — lost on refresh).
- **Avoid** — `localStorage`/`sessionStorage` (vulnerable to XSS).

Use `SameSite=Strict/Lax` + HTTPS for cookies.

---

## 187. How to handle role-based UI?

1. Fetch user roles after auth.
2. Store in auth context/Redux.
3. Create a `Permission` component or `usePermission` hook.
4. Conditionally render based on role.
5. Server validates permissions for actual data — client-side is just UX.

---

## 188. How to protect routes?

Create a `RequireAuth` component that checks auth state and redirects to login if not authenticated. In React Router v6: wrap routes with `element={<RequireAuth><Page /></RequireAuth>}`.

```jsx
function RequireAuth({ children }) {
  const { user } = useAuth();
  const location = useLocation();

  if (!user) return <Navigate to="/login" state={{ from: location }} replace />;
  return children;
}
```

---

## 189. Lazy loading routes in React?

Import route components with `React.lazy()`. Wrap the route outlet with `Suspense`. Each route chunk loads only when the user navigates to that route.

---

## 190. Error handling strategy in React?

1. Error boundaries for unexpected render errors.
2. `try/catch` in event handlers.
3. `useEffect` async error handling.
4. RTK Query / React Query built-in error states.
5. Global axios interceptor for API errors.
6. Toast notifications for user-facing errors.

---

## 191. Retry failed API calls?

Use `axios-retry` or implement manually: on 5xx or network error, retry with exponential backoff. RTK Query has retry capability. React Query has a `retry` option. Always limit retry count to avoid infinite loops.

---

## 192. Debounce search implementation?

Debounce the search input: wait for user to stop typing (300ms) before making API call. Use a custom `useDebounce` hook or third-party (`use-debounce`).

```js
function useDebounce(value, delay) {
  const [debouncedValue, setDebouncedValue] = useState(value);

  useEffect(() => {
    const handler = setTimeout(() => setDebouncedValue(value), delay);
    return () => clearTimeout(handler);
  }, [value, delay]);

  return debouncedValue;
}
```

---

## 193. Infinite scrolling implementation?

Use `IntersectionObserver` to detect when a sentinel element (last item) enters the viewport. On intersection, fetch the next page and append. RTK Query handles pagination automatically with `fetchNextPage`.

---

## 194. Pagination vs infinite scroll?

- **Pagination** — user explicitly navigates pages, easy to bookmark, less memory, good for search results.
- **Infinite scroll** — continuous feed, no friction, hard to bookmark/return, high memory over time.

Use pagination for task-oriented UIs; infinite scroll for content feeds.

---

## 195. SEO in React (SPA)?

SPAs have poor SEO because crawlers see empty HTML. Solutions:

1. SSR (Next.js).
2. SSG for static content.
3. Prerendering (`react-snap`, `Prerender.io`).
4. Meta tags with `react-helmet`.
5. Sitemaps.

SSR/SSG is the best long-term solution.

---

## 196. Performance bottlenecks in React?

1. Unnecessary re-renders.
2. Missing virtualization for long lists.
3. Large bundle (no code splitting).
4. Blocking API calls on mount.
5. Memory leaks (unremoved listeners).
6. Expensive inline calculations without memoization.

---

## 197. Bundle size optimization?

1. Code splitting (`React.lazy`).
2. Tree shaking (ESM imports).
3. Analyze with `webpack-bundle-analyzer`.
4. Lazy import large libraries.
5. Use lighter alternatives (`day.js` vs `moment.js`).
6. Compress assets.
7. CDN for vendor chunks.

---

## 198. Monitoring tools for React?

1. Sentry — error tracking.
2. DataDog / New Relic — APM.
3. LogRocket — session replay.
4. React DevTools Profiler — render performance.
5. Lighthouse — Core Web Vitals.
6. Web Vitals library (CLS, LCP, FID).

---

## 199. Logging strategy in React?

1. Client errors: Sentry or custom error boundary logging.
2. User actions: analytics events (GA, Mixpanel).
3. Performance: Web Vitals logging.
4. API errors: log in axios interceptor.
5. Different log levels by environment.

---

## 200. Real-world architecture of React app?

```
UI (components)
  → hooks (business logic)
  → services (API)
  → store (Redux / Query)
  → Router
```

- Feature folders per domain.
- TypeScript throughout.
- Storybook for components.
- MSW for API mocking.
- Cypress for e2e.
- CI/CD via GitHub Actions.

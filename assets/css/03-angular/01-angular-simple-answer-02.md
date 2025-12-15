# Angular Interview Questions

## **Basic Angular Questions**
## **1. What is Angular?**

* Angular is a **TypeScript-based frontend framework** by Google.
* It’s used to build **single-page applications (SPAs)**.
* Angular provides built-in features like **routing, forms, HTTP, dependency injection**, and **state management**.
* It follows a **component-based architecture**, which makes apps scalable and maintainable.

```typescript
// Simple Angular component
@Component({
  selector: 'app-hello',
  template: '<h1>Hello {{name}}!</h1>'
})
export class HelloComponent {
  name = 'Angular';
}
```

---

## **2. Difference between Angular and AngularJS**

* **AngularJS** is the **old version (Angular 1)** and is JavaScript-based.
* **Angular (2+)** is rewritten completely using **TypeScript**.
* Angular uses **components**, AngularJS used **controllers and $scope**.
* Angular is **faster**, more secure, and mobile-friendly.

```typescript
// AngularJS (1.x)
angular.module('app').controller('MyCtrl', function($scope) {
  $scope.message = 'Hello';
});

// Angular (2+)
@Component({
  template: '<p>{{message}}</p>'
})
export class MyComponent {
  message = 'Hello';
}
```

---

## **3. New features added in Angular 18**

* **Signals are stable** for reactive state management.
* **Zoneless change detection** support for better performance.
* Built-in **control flow syntax** like `@if`, `@for`.
* Improved **SSR and hydration** performance.
* Better developer experience and faster builds.

```html
<!-- New control flow syntax -->
@if (user.isLoggedIn) {
  <p>Welcome {{user.name}}</p>
}

@for (item of items; track item.id) {
  <div>{{item.name}}</div>
}
```

---

## **4. Key components of Angular**

* **Components** – UI building blocks.
* **Templates** – HTML views.
* **Directives** – behavior changes.
* **Services** – business logic.
* **Modules** – app organization.
* **Dependency Injection** – manages services.

```typescript
// Component
@Component({...})
export class AppComponent {}

// Service
@Injectable()
export class DataService {}

// Module
@NgModule({...})
export class AppModule {}
```

---

## **5. What is data binding in Angular?**

* Data binding connects **component data and the UI**.
* Angular supports:

  * **Interpolation** `{{ }}`
  * **Property binding** `[ ]`
  * **Event binding** `( )`
  * **Two-way binding** `[( )]`

```html
<!-- Interpolation -->
<h1>{{title}}</h1>

<!-- Property binding -->
<img [src]="imageUrl">

<!-- Event binding -->
<button (click)="onClick()">Click</button>

<!-- Two-way binding -->
<input [(ngModel)]="username">
```

---

## **6. What is an Angular lifecycle hook?**

* Lifecycle hooks let you run code at **specific stages** of a component.
* Common hooks:

 * **ngOnInit()** – Runs once when the component loads.
    *Example:* 
    Fetch API data → `this.getUsers();`

  * **ngOnChanges()** – Runs when an @Input() value changes.
    *Example:* 
    Update UI when parent sends new data.

  * **ngDoCheck()** – Runs on every change detection cycle.
    *Example:* 
    Manual change tracking.

  * **ngAfterViewInit()** – Runs after the view/child components are ready.
    *Example:* 
    Access a ViewChild and set focus.

  * **ngAfterViewChecked()** – Runs after Angular checks the view.
    *Example:* 
    Debug or adjust UI layout.

  * **ngOnDestroy()** – Runs before the component is removed.
    *Example:* 
    Unsubscribe from Observables or clear intervals.

```typescript
export class MyComponent implements OnInit, OnDestroy {
  ngOnInit() {
    console.log('Component initialized');
  }
  
  ngOnDestroy() {
    console.log('Component destroyed');
  }
}
```

---

## **7. What are decorators in Angular?**

* Decorators add **metadata** to classes.
* They tell Angular **how to treat a class**.
* Common decorators:

  * `@Component`
  * `@Injectable`
  * `@Directive`
  * `@Input`

```typescript
@Component({
  selector: 'app-user',
  templateUrl: './user.component.html'
})
export class UserComponent {
  @Input() userId: string;
  @Output() userSelected = new EventEmitter();
}
```

---

## **8. What is a directive? Types of directives**

* Directives change the **behavior or appearance** of elements.
* Types:

  * **Structural** – change DOM (`*ngIf`, `*ngFor`)
  * **Attribute** – change styles/behavior (`ngClass`)
  * **Custom directives**

```html
<p *ngIf="isLoggedIn">Welcome</p>
```

---

## **9. What are components?**

* Components are the **main UI building blocks** in Angular.
* Each component has:

  * HTML template
  * CSS styles
  * TypeScript logic
* Components are **reusable and independent**.

```ts
@Component({
  selector: 'app-user',
  template: `<h2>User Component</h2>`
})
export class UserComponent {}
```

---

## **10. What are modules in Angular?**

* Modules group related components, services, and directives.
* They help organize large applications.
* Root module is `AppModule`.
* Angular now also supports **standalone components** (less module usage).

```ts
@NgModule({
  declarations: [AppComponent],
  imports: [BrowserModule],
  bootstrap: [AppComponent]
})
export class AppModule {}
```
---

## **11. What is a service in Angular?**

* A service is a **reusable class** used to handle **business logic**, API calls, or shared data.
* It helps keep components **clean and lightweight**.
* Services are usually injected into components using **dependency injection**.
* Common use cases: HTTP requests, authentication, logging.

```ts
@Injectable({ providedIn: 'root' })
export class UserService {
  getUser() {
    return 'John';
  }
}
```

---

## **12. What is dependency injection in Angular?**

* Dependency Injection (DI) is a design pattern where Angular **provides objects automatically**.
* Instead of creating services manually, Angular **injects them where needed**.
* This improves **code reusability, testing, and maintainability**.

```ts
constructor(private userService: UserService) {}
```

---

## **13. What are Observables and how are they used in Angular?**

* Observables are used to handle **asynchronous data streams**.
* Angular uses Observables heavily with **HttpClient**, events, and state changes.
* They can emit **multiple values over time**.
* You subscribe to Observables to get data.

```ts
this.http.get('/api/users').subscribe(data => {
  console.log(data);
});
```

---

## **14. What is a provider?**

* A provider tells Angular **how to create or supply a service**.
* It defines **where and how** a dependency is available.
* Providers can be registered at:

  * Root level
  * Module level
  * Component level

```ts
providers: [UserService]
```

---

## **15. What are pipes in Angular? Can you create custom pipes?**

* Pipes transform data **before displaying it in the UI**.
* Angular provides built-in pipes like `date`, `uppercase`, `currency`.
* Yes, we can create **custom pipes**.

```ts
@Pipe({ name: 'reverse' })
export class ReversePipe implements PipeTransform {
  transform(value: string) {
    return value.split('').reverse().join('');
  }
}
```

```html
<p>{{ 'Angular' | reverse }}</p>
```

---

## **16. What is an Observable?**

* An Observable is a **data producer** that emits values over time.
* It can emit:

  * Data
  * Errors
  * Completion signals
* Observables are **lazy** — they run only when subscribed.

```ts
const obs$ = new Observable(observer => {
  observer.next('Hello');
});
```

---

## **17. What is an Observer?**

* An Observer is a **consumer of data** from an Observable.
* It listens for:

  * `next` – data
  * `error` – errors
  * `complete` – completion
* Observers are passed into `subscribe()`.

```ts
obs$.subscribe({
  next: val => console.log(val),
  error: err => console.error(err),
  complete: () => console.log('Done')
});
```

---

## **18. What is multicasting?**

* Multicasting means **multiple subscribers share the same Observable execution**.
* By default, Observables are **unicast**.
* Multicasting is done using `Subject`.
* Useful for sharing data like user state or events.

```ts
const subject = new Subject<number>();

subject.subscribe(v => console.log(v));
subject.subscribe(v => console.log(v));

subject.next(1);
```

---

## **19. What is a bootstrapping module?**

* The bootstrapping module is the **starting point** of an Angular app.
* It loads the **root component**.
* Usually done in `AppModule` using `bootstrap`.

```ts
@NgModule({
  bootstrap: [AppComponent]
})
export class AppModule {}
```

---

## **20. Which file loads first in an Angular application?**

* The first file that loads is **`main.ts`**.
* `main.ts` bootstraps the root module or root component.
* From there, Angular loads the entire application.

```ts
platformBrowserDynamic()
  .bootstrapModule(AppModule);
```

---

## **21. If I rename `main.ts`, will the application load?**

* No, the app **will not load by default**.
* Angular looks for `main.ts` as the **entry point**.
* If renamed, Angular won’t know where to start unless we **update configuration**.
* We’d need to update the build config to point to the new file.

```ts
// main.ts bootstraps the app
platformBrowserDynamic()
  .bootstrapModule(AppModule);
```

---

## **22. What is a template?**

* A template defines the **HTML view** of a component.
* It controls **what the user sees**.
* Templates can include Angular features like:

  * Data binding
  * Directives
  * Pipes

```ts
@Component({
  template: `<h1>{{ title }}</h1>`
})
export class AppComponent {
  title = 'Angular App';
}
```

---

## **23. Difference between Promise and Observable**

* **Promise** handles a **single value** and executes immediately.
* **Observable** can emit **multiple values over time**.
* Observables are **cancelable** and support operators like `map`, `filter`.
* Angular prefers Observables, especially with HTTP.

```ts
// Observable
this.http.get('/api/data').subscribe();
```

---

## **24. What is Angular Router and why is it used?**

* Angular Router is used for **navigation between views**.
* It enables **single-page application routing**.
* It loads components **without reloading the page**.
* Routes are defined using paths and components.

```ts
const routes = [
  { path: 'home', component: HomeComponent }
];
```

---

## **25. Purpose of `ngOnInit`**

* `ngOnInit` runs **once after component initialization**.
* Used to:

  * Fetch data
  * Initialize variables
* It runs **after input properties are set**.

```ts
ngOnInit() {
  this.loadData();
}
```

---

## **26. How does Angular handle event binding?**

* Event binding allows the app to **respond to user actions**.
* Uses parentheses `( )`.
* Common events: `click`, `keyup`, `submit`.

```html
<button (click)="save()">Save</button>
```

```ts
save() {
  console.log('Saved');
}
```

---

## **27. How can you make an HTTP request in Angular?**

* Angular uses **HttpClient** for HTTP calls.
* It returns **Observables**.
* First, import `HttpClientModule`.

```ts
this.http.get('/api/users').subscribe(data => {
  console.log(data);
});
```

---

## **28. What is `ngIf` used for?**

* `ngIf` is a **structural directive**.
* It conditionally **adds or removes elements** from the DOM.
* Useful for showing or hiding content.

```html
<p *ngIf="isLoggedIn">Welcome</p>
```

---

## **29. What is `ngFor` used for?**

* `ngFor` is used to **loop through collections**.
* It dynamically renders elements based on data.
* Commonly used with arrays.

```html
<li *ngFor="let user of users">
  {{ user }}
</li>
```

---

## **30. What is `ngClass` used for?**

* `ngClass` dynamically **adds or removes CSS classes**.
* Used for conditional styling.
* Helps keep templates clean.

```html
<div [ngClass]="{ active: isActive }">Box</div>
```

---

## **31. What is a template reference variable?**

* A template reference variable gives **direct access to a DOM element**.
* Defined using `#`.
* Useful for reading input values or element properties.

```html
<input #userInput>
<button (click)="print(userInput.value)">Click</button>
```

```ts
print(value: string) {
  console.log(value);
}
```

---

## **Intermediate Angular Questions**
### **32. What are Angular forms? What are the two types of forms in Angular?**

* Angular forms are used to **capture, validate, and process user input**.
* They help manage form state, validation rules, and submission.
* Angular provides **two types of forms**:

  * **Template-driven forms** – simple, use HTML and `ngModel`
  * **Reactive forms** – more scalable, use `FormGroup` and `FormControl`
* Template-driven is good for small forms, reactive is preferred for complex forms.

**Example (Reactive Form):**

```ts
this.form = new FormGroup({
  name: new FormControl('')
});
```

---

### **33. What is the purpose of the `ngModel` directive in Angular?**

* `ngModel` enables **two-way data binding** between the template and component.
* It automatically keeps **UI and component data in sync**.
* Commonly used in **template-driven forms**.
* When the input value changes, the component updates, and vice versa.

**Example:**

```html
<input [(ngModel)]="username">
<p>{{ username }}</p>
```

---

### **34. What is the `async` pipe in Angular, and how does it work with Observables?**

* The `async` pipe **subscribes to an Observable or Promise automatically**.
* It updates the UI when new data arrives.
* It also **handles unsubscribe automatically**, preventing memory leaks.
* This reduces boilerplate code in components.

**Example:**

```html
<p>{{ user$ | async }}</p>
```

---

### **35. What is lazy loading in Angular? How does it improve application performance?**

* Lazy loading means **loading modules only when they are needed**.
* It reduces **initial bundle size** and **faster app startup**.
* Commonly used for large feature modules like Admin or Dashboard.
* Improves performance especially in big applications.

**Example:**

```ts
{
  path: 'admin',
  loadChildren: () =>
    import('./admin/admin.module').then(m => m.AdminModule)
}
```

---

### **36. What is change detection in Angular? How does it work?**

* Change detection keeps the **UI in sync with component data**.
* Angular checks for changes after:

  * Events (click, input)
  * HTTP responses
  * Timers
* It compares previous and current values and updates the DOM if needed.
* Uses a **tree-based mechanism** starting from the root component.

**Example:**

```ts
this.count++;
```

---

### **37. Explain the concept of zones in Angular.**

* Angular uses **Zone.js** to track asynchronous operations.
* Zones notify Angular when something async finishes.
* This triggers **automatic change detection**.
* Developers don’t need to manually refresh the UI after async tasks.

**Example:**

```ts
setTimeout(() => {
  this.message = 'Updated';
}, 1000);
```

---

### **38. What are `@Input` and `@Output` decorators in Angular?**

* `@Input` allows **parent to child data flow**.
* `@Output` allows **child to parent communication** using events.
* Together, they enable **component interaction**.

**Example:**

```ts
@Input() title: string;
@Output() clicked = new EventEmitter<void>();
```

---

### **39. What is the difference between `localStorage` and `sessionStorage` in Angular?**

* Both are **browser storage mechanisms**.
* `localStorage`:

  * Data persists even after browser close
* `sessionStorage`:

  * Data is cleared when the tab is closed
* Angular accesses them using standard Web APIs.

**Example:**

```ts
localStorage.setItem('token', 'abc123');
sessionStorage.setItem('user', 'John');
```

---

### **40. How does Angular handle cross-site request forgery (CSRF)?**

* Angular protects against CSRF using **XSRF tokens**.
* The server sends a token as a cookie.
* Angular reads it and sends it in HTTP headers automatically.
* This ensures requests are coming from a trusted source.

**Example:**

```http
X-XSRF-TOKEN: abc123
```

---

### **41. What are Angular modules and how do they help in organizing an application?**

* Angular modules group **related components, directives, pipes, and services**.
* They help keep the app **organized, modular, and scalable**.
* Modules support **lazy loading**, improving performance.
* Every Angular app has at least one module: `AppModule`.

**Example:**

```ts
@NgModule({
  declarations: [HomeComponent],
  imports: [CommonModule]
})
export class HomeModule {}
```

---

### **42. What is a custom directive, and how do you create one?**

* A custom directive lets you **manipulate DOM behavior or appearance**.
* Used when you want reusable UI logic.
* Created using Angular CLI and decorated with `@Directive`.

**Example:**

```ts
@Directive({
  selector: '[appHighlight]'
})
export class HighlightDirective {
  constructor(el: ElementRef) {
    el.nativeElement.style.background = 'yellow';
  }
}
```

---

### **43. How do you create and use services in Angular?**

* Services are used for **business logic, API calls, and shared data**.
* They promote **code reusability and separation of concerns**.
* Created with `@Injectable` and injected using Dependency Injection.

**Example:**

```ts
@Injectable({ providedIn: 'root' })
export class UserService {
  getUsers() { return ['John', 'Jane']; }
}
```

---

### **44. What is the role of the `RouterModule` in Angular?**

* `RouterModule` enables **navigation between views**.
* It maps URLs to components.
* Supports lazy loading, route guards, and parameters.

**Example:**

```ts
RouterModule.forRoot([
  { path: 'home', component: HomeComponent }
]);
```

---

### **45. How would you handle HTTP errors in Angular?**

* Angular handles HTTP errors using **RxJS `catchError`**.
* Errors can be handled globally using **HTTP interceptors**.
* This ensures better user feedback and logging.

**Example:**

```ts
this.http.get(url).pipe(
  catchError(error => {
    console.error(error);
    return throwError(() => error);
  })
);
```

---

### **46. How can you optimize the performance of an Angular application?**

* Use **lazy loading** for feature modules.
* Enable **OnPush change detection**.
* Use **trackBy** in `*ngFor`.
* Avoid unnecessary DOM updates.

**Example:**

```ts
changeDetection: ChangeDetectionStrategy.OnPush
```

---

### **47. What is the role of the Angular CLI in the development process?**

* Angular CLI automates **project setup and configuration**.
* Used for generating components, services, and modules.
* Handles build, test, and deployment efficiently.

**Example:**

```bash
ng generate component dashboard
```

---

### **48. How can you handle routing with route guards in Angular?**

* Route guards control **access to routes**.
* Used for authentication, authorization, and unsaved changes.
* Common guards: `CanActivate`, `CanDeactivate`.

**Example:**

```ts
canActivate(): boolean {
  return this.authService.isLoggedIn();
}
```

---

### **49. What are interceptors in Angular? How would you use them for adding headers or logging API requests?**

* Interceptors intercept **all HTTP requests and responses**.
* Used for adding auth headers, logging, or error handling.
* Applied globally.

**Example:**

```ts
req.clone({
  headers: req.headers.set('Authorization', 'Bearer token')
});
```

---

### **50. What is the purpose of the `ng-content` and `ng-template` directive in Angular?**

* `ng-content` enables **content projection**.
* `ng-template` defines **reusable or conditional templates**.
* Useful for building reusable UI components.

**Example:**

```html
<ng-content></ng-content>

<ng-template #loading>
  <p>Loading...</p>
</ng-template>
```

---

### **51. What is a resolver in Angular, and when would you use one?**

* A resolver **fetches data before route loads**.
* Ensures the component has data on initialization.
* Improves user experience by avoiding empty screens.

**Example:**

```ts
resolve() {
  return this.userService.getUsers();
}
```

---

### **52. How does Angular support internationalization (i18n)?**

* Angular supports i18n using **built-in localization tools**.
* Text is marked using `i18n` attribute.
* Translations are loaded based on locale.

**Example:**

```html
<h1 i18n>Welcome</h1>
```

---

## **Advanced Angular Questions**
### **53. What is a singleton service in Angular?**

* A singleton service means **only one instance** of the service exists in the app.
* It is shared across all components.
* Achieved by providing the service at the **root level**.
* Useful for shared state, caching, and authentication.

**Example:**

```ts
@Injectable({ providedIn: 'root' })
export class AuthService {}
```

---

### **54. Difference between `ngOnInit` and `constructor` in Angular components**

* `constructor` is used for **dependency injection only**.
* `ngOnInit` is called **after component initialization**.
* Business logic, API calls should go in `ngOnInit`.
* `constructor` runs first, `ngOnInit` runs once after inputs are set.

**Example:**

```ts
constructor(private service: DataService) {}

ngOnInit() {
  this.loadData();
}
```

---

### **55. What is the RxJS library, and how is it used in Angular?**

* RxJS is a library for **reactive programming using Observables**.
* Angular uses RxJS heavily for **HTTP calls, events, and async data**.
* It helps manage streams of data over time.

**Example:**

```ts
this.http.get(url).subscribe(data => console.log(data));
```

---

### **56. Explain operators like `map`, `filter`, `merge`, and `switchMap`**

* `map` transforms data.
* `filter` removes unwanted values.
* `merge` combines multiple streams.
* `switchMap` switches to a new observable and cancels the previous one.

**Example:**

```ts
source$.pipe(
  filter(x => x > 5),
  map(x => x * 2)
);
```

---

### **57. How do you handle error handling in RxJS streams?**

* RxJS uses `catchError` to handle errors in streams.
* Errors can be handled without breaking the app.
* You can return a fallback value or rethrow the error.

**Example:**

```ts
this.http.get(url).pipe(
  catchError(err => {
    console.error(err);
    return of([]);
  })
);
```

---

### **58. Difference between `switchMap`, `concatMap`, and `mergeMap`**

* `switchMap`: cancels previous request (search box)
* `concatMap`: executes one after another (order matters)
* `mergeMap`: runs in parallel (no order guarantee)

**Example:**

```ts
search$.pipe(
  switchMap(term => this.api.search(term))
);
```

---

### **59. How to handle parallel service calls in Angular?**

* Use RxJS `forkJoin` for parallel API calls.
* All requests run together.
* Emits result only when all complete.

**Example:**

```ts
forkJoin({
  users: this.userService.getUsers(),
  orders: this.orderService.getOrders()
}).subscribe(result => {
  console.log(result.users, result.orders);
});
```

---

## **Testing Questions**
### **60. How does Angular handle state management, and what libraries can be used?**

* Angular itself manages **local state** using **services + RxJS (Observables/BehaviorSubject)**.
* For **large applications**, external state libraries are used.
* Common libraries:

  * **NgRx** (Redux pattern – actions, reducers, effects)
  * **NGXS** (simpler, class-based)
  * **Akita** (store-focused, less boilerplate)

**Example (Service-based state):**

```ts
@Injectable({ providedIn: 'root' })
export class UserService {
  user$ = new BehaviorSubject<User | null>(null);
}
```

---

### **61. What is AOT compilation vs JIT compilation?**

* **AOT (Ahead-of-Time)**:

  * Compiles Angular code **at build time**
  * Faster app startup
  * Smaller bundle, better security
* **JIT (Just-in-Time)**:

  * Compiles code **in the browser**
  * Slower startup
  * Mainly used during development

**Example:**

```bash
ng build --aot
```

---

### **62. What are Angular Universal applications?**

* Angular Universal enables **Server-Side Rendering (SSR)**.
* HTML is rendered on the **server**, then sent to the browser.
* Benefits:

  * Faster first load
  * Better SEO
  * Better performance on slow devices

**Flow:**

```
Request → Server renders HTML → Browser hydrates Angular app
```

---

### **63. What are Angular decorators and their role?**

* Decorators are **metadata annotations** that tell Angular how to process a class.
* They define components, services, modules, etc.

**Common decorators:**

* `@Component`
* `@Injectable`
* `@NgModule`
* `@Input`, `@Output`

**Example:**

```ts
@Component({
  selector: 'app-header',
  template: `<h1>Hello</h1>`
})
export class HeaderComponent {}
```

---

### **64. How do you configure environment-specific settings in Angular?**

* Angular uses **environment files**.
* Different values for dev, test, and production.

**Files:**

```
environment.ts
environment.prod.ts
```

**Example:**

```ts
export const environment = {
  production: false,
  apiUrl: 'http://localhost:3000'
};
```

**Usage:**

```ts
this.http.get(environment.apiUrl);
```

---

### **65. Advantages and disadvantages of Angular**

**Advantages:**

* Strong structure & scalability
* TypeScript support
* Two-way data binding
* Built-in routing, forms, HTTP

**Disadvantages:**

* Steep learning curve
* More boilerplate
* Heavier compared to React/Vue

**Best for:** Large enterprise applications

---

### **66. How to implement AuthGuard in Angular?**

* AuthGuard protects routes based on authentication.

**Example:**

```ts
@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate {
  canActivate() {
    return !!localStorage.getItem('token');
  }
}
```

**Routing:**

```ts
{
  path: 'dashboard',
  canActivate: [AuthGuard],
  component: DashboardComponent
}
```

---

### **67. Difference between Authentication and Authorization**

* **Authentication** → *Who are you?*

  * Login, credentials, tokens
* **Authorization** → *What can you access?*

  * Roles, permissions

**Example:**

* Authentication: User logs in
* Authorization: Admin can access `/admin`, user cannot

---

### **68. How would you troubleshoot performance issues in Angular?**

**Tools:**

* Angular DevTools
* Chrome DevTools
* Lighthouse
* Webpack Bundle Analyzer

**Techniques:**

* Use **OnPush** change detection
* Lazy load modules
* TrackBy in `*ngFor`
* Avoid heavy logic in templates
* Unsubscribe from observables

**Example:**

```ts
changeDetection: ChangeDetectionStrategy.OnPush
```

---
### **60. How does Angular handle state management, and what libraries can be used?**

* Angular itself manages **local state** using **services + RxJS (Observables/BehaviorSubject)**.
* For **large applications**, external state libraries are used.
* Common libraries:

  * **NgRx** (Redux pattern – actions, reducers, effects)
  * **NGXS** (simpler, class-based)
  * **Akita** (store-focused, less boilerplate)

**Example (Service-based state):**

```ts
@Injectable({ providedIn: 'root' })
export class UserService {
  user$ = new BehaviorSubject<User | null>(null);
}
```

---

### **61. What is AOT compilation vs JIT compilation?**

* **AOT (Ahead-of-Time)**:

  * Compiles Angular code **at build time**
  * Faster app startup
  * Smaller bundle, better security
* **JIT (Just-in-Time)**:

  * Compiles code **in the browser**
  * Slower startup
  * Mainly used during development

**Example:**

```bash
ng build --aot
```

---

### **62. What are Angular Universal applications?**

* Angular Universal enables **Server-Side Rendering (SSR)**.
* HTML is rendered on the **server**, then sent to the browser.
* Benefits:

  * Faster first load
  * Better SEO
  * Better performance on slow devices

**Flow:**

```
Request → Server renders HTML → Browser hydrates Angular app
```

---

### **63. What are Angular decorators and their role?**

* Decorators are **metadata annotations** that tell Angular how to process a class.
* They define components, services, modules, etc.

**Common decorators:**

* `@Component`
* `@Injectable`
* `@NgModule`
* `@Input`, `@Output`

**Example:**

```ts
@Component({
  selector: 'app-header',
  template: `<h1>Hello</h1>`
})
export class HeaderComponent {}
```

---

### **64. How do you configure environment-specific settings in Angular?**

* Angular uses **environment files**.
* Different values for dev, test, and production.

**Files:**

```
environment.ts
environment.prod.ts
```

**Example:**

```ts
export const environment = {
  production: false,
  apiUrl: 'http://localhost:3000'
};
```

**Usage:**

```ts
this.http.get(environment.apiUrl);
```

---

### **65. Advantages and disadvantages of Angular**

**Advantages:**

* Strong structure & scalability
* TypeScript support
* Two-way data binding
* Built-in routing, forms, HTTP

**Disadvantages:**

* Steep learning curve
* More boilerplate
* Heavier compared to React/Vue

**Best for:** Large enterprise applications

---

### **66. How to implement AuthGuard in Angular?**

* AuthGuard protects routes based on authentication.

**Example:**

```ts
@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate {
  canActivate() {
    return !!localStorage.getItem('token');
  }
}
```

**Routing:**

```ts
{
  path: 'dashboard',
  canActivate: [AuthGuard],
  component: DashboardComponent
}
```

---

### **67. Difference between Authentication and Authorization**

* **Authentication** → *Who are you?*

  * Login, credentials, tokens
* **Authorization** → *What can you access?*

  * Roles, permissions

**Example:**

* Authentication: User logs in
* Authorization: Admin can access `/admin`, user cannot

---

### **68. How would you troubleshoot performance issues in Angular?**

**Tools:**

* Angular DevTools
* Chrome DevTools
* Lighthouse
* Webpack Bundle Analyzer

**Techniques:**

* Use **OnPush** change detection
* Lazy load modules
* TrackBy in `*ngFor`
* Avoid heavy logic in templates
* Unsubscribe from observables

**Example:**

```ts
changeDetection: ChangeDetectionStrategy.OnPush
```

---


## **Testing Questions**

---

### **69. How do you approach unit testing in Angular?**

* I focus on **testing components, services, and pipes in isolation**.
* Angular uses **Jasmine** for assertions and **Karma** as the test runner.
* I mock dependencies to avoid real HTTP calls or services.
* Goal is to test **logic, not UI behavior**.

**Example:**

```ts
it('should add numbers', () => {
  expect(2 + 2).toBe(4);
});
```

---

### **70. What is the purpose of TestBed in Angular testing?**

* `TestBed` configures a **testing module**, similar to `NgModule`.
* It allows me to declare components, provide services, and import modules.
* Used to create component instances for testing.

**Example:**

```ts
TestBed.configureTestingModule({
  declarations: [MyComponent],
  providers: [MyService]
});
```

---

### **71. How do you mock services and HTTP requests in Angular tests?**

* I mock services using **Jasmine spies**.
* For HTTP calls, I use **HttpClientTestingModule** and `HttpTestingController`.

**Example:**

```ts
const spy = jasmine.createSpyObj('UserService', ['getUsers']);
spy.getUsers.and.returnValue(of([]));
```

---

### **72. How do you test a component with dependencies?**

* I mock dependent services instead of using real ones.
* For observables, I return mock data using `of()`.
* Child components can be stubbed if needed.

**Example:**

```ts
providers: [
  { provide: UserService, useValue: mockUserService }
]
```

---

### **73. What is a spy in Jasmine?**

* A **spy** tracks function calls and controls return values.
* Useful to verify if a method was called or not.

**Example:**

```ts
spyOn(service, 'getData').and.returnValue(of([]));

expect(service.getData).toHaveBeenCalled();
```

---

## **Architecture & Design Patterns Questions**

---

### **74. How would you organize a large Angular application?**

* I use **feature-based modules**, not file-type-based.
* Shared components go into a **SharedModule**.
* Core services go into a **CoreModule**.
* Lazy loading for better performance.

**Structure:**

```
/core
/shared
/features/user
/features/admin
```

---

### **75. What is state management in Angular?**

* State management handles **shared and global data**.
* Simple state: Services + RxJS.
* Large apps: **NgRx, NGXS, Akita**.

**Example:**

```ts
user$ = new BehaviorSubject<User | null>(null);
```

---

### **76. How do you debug an Angular application?**

* Use **Chrome DevTools** for console and network.
* Use **Angular DevTools** for component tree and change detection.
* Add breakpoints in TypeScript files.
* Use `console.log()` for quick checks.

**Example:**

```ts
debugger;
```

---

### **77. What is the Singleton pattern and Angular services?**

* Singleton means **only one instance** exists.
* Angular services are singletons when provided in `root`.
* Used for shared data and logic.

**Example:**

```ts
@Injectable({ providedIn: 'root' })
export class AuthService {}
```

---

### **78. How do you handle component communication in Angular?**

* **Parent → Child:** `@Input()`
* **Child → Parent:** `@Output()`
* **Sibling / Unrelated:** Shared service with observables

**Example:**

```ts
@Input() title: string;
@Output() clicked = new EventEmitter<void>();
```

---

## **Backend Integration Questions**
## **79. How would you handle authentication and authorization in Angular? Explain JWT**

* Authentication verifies **who the user is**, authorization checks **what they can access**.
* Angular uses **JWT tokens** issued by the backend after login.
* Token is stored securely (usually memory or `localStorage`).
* Interceptors attach the token to every request.
* Route guards protect restricted routes.

**JWT Flow:**

```text
Login → Server returns JWT → Store token → Send token in headers
```

**Example (Interceptor):**

```ts
req.clone({
  headers: req.headers.set('Authorization', `Bearer ${token}`)
});
```

---

## **80. How would you handle API calls in Angular using `HttpClient`?**

* Angular uses `HttpClient` for REST API communication.
* It returns **Observables**, making async handling easy.
* Use services for API logic.
* Handle errors using `catchError`.
* Interceptors manage headers and logging globally.

**Example:**

```ts
this.http.get('/api/users').pipe(
  catchError(err => {
    console.error(err);
    return throwError(() => err);
  })
);
```

---

## **81. How would you handle file uploads in Angular? Best practices for large files**

* Use `FormData` to send files.
* Track upload progress using `HttpEventType`.
* Validate file size and type before upload.
* Use chunking or backend streaming for large files.

**Example:**

```ts
const formData = new FormData();
formData.append('file', file);

this.http.post('/upload', formData, { reportProgress: true });
```

---

# **Security Questions**

## **82. What are common security concerns in Angular and how do you mitigate them?**

* XSS attacks → Angular’s built-in sanitization
* CSRF → XSRF tokens
* Token theft → Secure storage and HTTPS
* API exposure → Route guards & server-side validation

**Best practice:**

* Never trust frontend alone
* Always validate on backend

---

## **83. How does Angular prevent Cross-Site Scripting (XSS)?**

* Angular **automatically sanitizes** values in templates.
* It escapes HTML, scripts, and URLs.
* Prevents injecting malicious code into the DOM.
* Direct DOM access is discouraged.

**Example:**

```html
<p>{{ userInput }}</p> <!-- Safe by default -->
```

---

## **84. How do you secure an Angular application in production?**

* Use **HTTPS only**
* Enable **Content Security Policy (CSP)**
* Remove debug tools and console logs
* Use environment files for secrets
* Secure APIs with authentication & authorization

**Example:**

```ts
ng build --configuration production
```

---

### ✅ Interview Tip (Important)

> Angular handles **UI security**, but **backend must enforce real security**.

---

## **Miscellaneous Questions**
### **85. What is Angular Universal, and how does it enable SSR?**

* Angular Universal allows **server-side rendering (SSR)** of Angular apps.
* Instead of rendering in the browser only, the **HTML is generated on the server**.
* This improves **SEO**, **first load speed**, and **performance on slow devices**.
* After loading, Angular switches to **client-side rendering** (hydration).

**Example**

```ts
ng add @nguniversal/express-engine
```

```ts
// server.ts
app.get('*', (req, res) => {
  res.render(indexHtml, { req });
});
```

---

### **86. Purpose of `angular.json` and its configurations**

* `angular.json` is the **main workspace configuration file**.
* It controls **build, serve, test, lint, and deploy settings**.
* Used to configure **assets, styles, scripts, environments**, and optimizations.
* Different configs for **dev, prod, staging**.

**Example**

```json
"build": {
  "options": {
    "outputPath": "dist/app",
    "styles": ["src/styles.css"],
    "scripts": []
  }
}
```

---

### **87. Creating custom Angular schematics**

* Schematics automate **code generation and project rules**.
* Used to create **custom components, modules, or standards**.
* Helpful for large teams to keep consistency.
* Created using **@angular-devkit/schematics**.

**Example**

```bash
schematics blank --name=my-schematic
```

```ts
export function mySchematic(): Rule {
  return (tree: Tree) => {
    tree.create('hello.txt', 'Hello Angular');
    return tree;
  };
}
```

---

### **88. Experience integrating Angular with other technologies**

* Integrated Angular with **REST APIs, GraphQL, Firebase, and .NET/Node backends**.
* Used **Micro-frontends (Module Federation)** with React apps.
* Embedded Angular into **legacy systems** gradually.
* Used **WebSockets** for real-time updates.

**Example**

```ts
this.http.get('/api/users').subscribe(data => {
  this.users = data;
});
```

---

### **89. Challenging Angular problems and solutions**

* Biggest challenge: **performance with large data tables**.
* Solved using **OnPush change detection**, **virtual scrolling**, and **lazy loading**.
* Also faced **memory leaks**, fixed by unsubscribing properly.

**Example**

```ts
@Component({
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class UserComponent {}
```

---

### **90. Staying up to date with Angular**

* Follow **Angular official blog and GitHub releases**.
* Regularly read **RFCs and migration guides**.
* Upgrade projects using `ng update`.
* Practice new features in **side projects**.

**Example**

```bash
ng update @angular/core @angular/cli
```

---

### **91. Angular project solving a complex problem**

* Built a **role-based enterprise dashboard** with real-time data.
* Used **lazy-loaded modules**, **guards**, and **dynamic forms**.
* SSR improved SEO and initial load time.
* Result: **faster app, better UX, scalable architecture**.

**Example**

```ts
{
  path: 'admin',
  loadChildren: () =>
    import('./admin/admin.module').then(m => m.AdminModule),
  canActivate: [AuthGuard]
}
```

---

## **RxJS Specific Questions**
### **92. What is a `Subject` and `BehaviorSubject` in Angular RxJS? How are they different?**

* A **`Subject`** is both an **observable** and an **observer**.
* It allows multicasting to multiple observers, so changes are broadcasted to all subscribers.
* A **`BehaviorSubject`** is a type of `Subject` that requires an **initial value** and always returns the **current value** to new subscribers.

**Difference**:

* **Subject**: No initial value, emits only on subscription.
* **BehaviorSubject**: Emits the latest value immediately on subscription, even before new values come in.

**Example**

```ts
import { Subject, BehaviorSubject } from 'rxjs';

const subject = new Subject();
subject.next('Hello'); // no value emitted to unsubscribed observers

const behaviorSubject = new BehaviorSubject('Initial');
behaviorSubject.next('Hello'); // emits latest value even to new subscribers
```

---

### **93. What is the difference between `ngFor` and `ngForOf`?**

* `ngFor` is an **Angular directive** used for **iterating** over a collection of items.
* `ngForOf` is the **internal implementation** of the `ngFor` directive.

**Difference**:

* There’s **no functional difference** between them; `ngFor` uses `ngForOf` under the hood.
* `ngFor` is just the **shortened version**.

**Example**

```html
<ul>
  <li *ngFor="let item of items">{{ item }}</li>
</ul>
```

The above example uses **`ngFor`**, which is shorthand for `ngForOf`.

---

### **94. What is the role of `ngZone` in Angular, and how does it help with performance optimization?**

* `ngZone` allows you to **run code outside Angular's change detection** and only re-trigger change detection when necessary.
* Helps with **performance** by reducing the frequency of Angular’s **change detection cycle**.
* Useful for working with non-Angular code like **WebSockets, external libraries**, or **setTimeout**.

**Example**

```ts
import { NgZone } from '@angular/core';

constructor(private ngZone: NgZone) {}

runOutsideAngular() {
  this.ngZone.runOutsideAngular(() => {
    setTimeout(() => {
      console.log('No change detection triggered');
    }, 1000);
  });
}
```

---

### **95. What is the purpose of Angular's `Renderer2`?**

* `Renderer2` provides an **abstraction** for manipulating DOM elements.
* Helps with **DOM operations** in a safe way, especially when rendering in **server-side** or **platform-specific** environments.
* Avoids direct interaction with the DOM, keeping the app **platform agnostic**.

**Example**

```ts
import { Renderer2, ElementRef } from '@angular/core';

constructor(private renderer: Renderer2, private el: ElementRef) {}

ngOnInit() {
  this.renderer.setStyle(this.el.nativeElement, 'background-color', 'blue');
}
```

---

### **96. Explain the difference between `ngOnChanges` and `ngDoCheck`.**

* `ngOnChanges` is called when **input properties** change. It checks the **previous and current values** of input bindings.
* `ngDoCheck` is a **manual check** for any changes, giving you control over change detection. It’s more granular but less efficient than `ngOnChanges`.

**Difference**:

* `ngOnChanges` is **automatic** and runs when an input property changes.
* `ngDoCheck` requires you to **define custom change detection logic**.

**Example**

```ts
// ngOnChanges
ngOnChanges(changes: SimpleChanges) {
  console.log(changes);
}

// ngDoCheck
ngDoCheck() {
  console.log('Change detection ran');
}
```



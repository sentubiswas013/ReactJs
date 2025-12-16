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

## **4. What are the key components of Angular**

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

## **8. What is a directive in Angular? Can you name the different types of directives?**

* Directives change the **behavior or appearance** of elements.
* Types:

  * **Structural** – change DOM (`*ngIf`, `*ngFor`)
  * **Attribute** – change styles/behavior (`ngClass`)
  * **Custom directives**

```typescript
// Attribute directive
@Directive({
  selector: '[appHighlight]'
})
export class HighlightDirective {
  @HostListener('mouseenter') onMouseEnter() {
    this.el.nativeElement.style.backgroundColor = 'yellow';
  }
}
```

```html
<!-- Usage -->
<p appHighlight>Hover me!</p>
<div *ngIf="showContent">Content</div>
```

---

## **9. What are components?**

* Components are the **main UI building blocks** in Angular.
* Each component has:

  * HTML template
  * CSS styles
  * TypeScript logic
* Components are **reusable and independent**.

```typescript
@Component({
  selector: 'app-product',
  template: `
    <div class="product">
      <h3>{{product.name}}</h3>
      <p>Price: {{product.price | currency}}</p>
    </div>
  `,
  styles: ['.product { border: 1px solid #ccc; }']
})
export class ProductComponent {
  product = { name: 'Laptop', price: 999 };
}
```

---

## **10. What are modules in Angular?**

* Modules group related components, services, and directives.
* They help organize large applications.
* Root module is `AppModule`.
* Angular now also supports **standalone components** (less module usage).

```typescript
@NgModule({
  declarations: [AppComponent, HeaderComponent],
  imports: [BrowserModule, FormsModule],
  providers: [DataService],
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

```typescript
@Injectable({
  providedIn: 'root'
})
export class UserService {
  getUsers() {
    return this.http.get<User[]>('/api/users');
  }
}

// Usage in component
constructor(private userService: UserService) {}
```

---

## **12. What is dependency injection in Angular?**

* Dependency Injection (DI) is a design pattern where Angular **provides objects automatically**.
* Instead of creating services manually, Angular **injects them where needed**.
* This improves **code reusability, testing, and maintainability**.

```typescript
@Component({...})
export class OrderComponent {
  constructor(
    private orderService: OrderService,
    private router: Router
  ) {}
}
```

---

## **13. What are Observables and how are they used in Angular?**

* Observables are used to handle **asynchronous data streams**.
* Angular uses Observables heavily with **HttpClient**, events, and state changes.
* They can emit **multiple values over time**.
* You subscribe to Observables to get data.

```typescript
// HTTP request returns Observable
getUsers(): Observable<User[]> {
  return this.http.get<User[]>('/api/users');
}

// Subscribe to get data
this.userService.getUsers().subscribe(users => {
  this.users = users;
});
```

---

### 14. What is a provider?

* A provider tells Angular **how to create or supply a service**.
* It defines **where and how** a dependency is available.
* Providers can be registered at

```typescript
// Class provider
providers: [UserService]

// Value provider
providers: [{ provide: API_URL, useValue: 'https://api.example.com' }]

// Factory provider
providers: [{ 
  provide: Logger, 
  useFactory: () => new Logger(environment.production) 
}]
```

---

## **15. What are pipes in Angular? Can you create custom pipes?**

* Pipes transform data **before displaying it in the UI**.
* Angular provides built-in pipes like `date`, `uppercase`, `currency`.
* Yes, we can create **custom pipes**.

```typescript
// Built-in pipes
{{ user.birthDate | date:'short' }}
{{ product.price | currency:'USD' }}
{{ message | uppercase }}

// Custom pipe
@Pipe({ name: 'truncate' })
export class TruncatePipe implements PipeTransform {
  transform(value: string, limit: number = 10): string {
    return value.length > limit ? value.substring(0, limit) + '...' : value;
  }
}
```

```html
<p>{{ 'longText' | truncate:20 }}</p>
```

---

## **16. What is an Observable?**

* An Observable is a **data producer** that emits values over time.
* It can emit:

  * Data
  * Errors
  * Completion signals
* Observables are **lazy** — they run only when subscribed.

```typescript
// Creating an observable
const data$ = new Observable(observer => {
  observer.next('Hello');
  observer.next('World');
  observer.complete();
});

// Subscribe to receive data
data$.subscribe(value => console.log(value));
```

---

## **17. What is an Observer?**

* An Observer is a **consumer of data** from an Observable.
* It listens for:

  * `next` – data
  * `error` – errors
  * `complete` – completion
* Observers are passed into `subscribe()`.

```typescript
const observer = {
  next: value => console.log('Received:', value),
  error: err => console.error('Error:', err),
  complete: () => console.log('Stream completed')
};

observable$.subscribe(observer);
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

```typescript
// main.ts
platformBrowserDynamic().bootstrapModule(AppModule);

// app.module.ts
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

```typescript
// main.ts - Entry point
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { AppModule } from './app/app.module';

platformBrowserDynamic().bootstrapModule(AppModule);
```

---

## **21. If I rename `main.ts`, will the application load?**

* No, the app **will not load by default**.
* Angular looks for `main.ts` as the **entry point**.
* If renamed, Angular won’t know where to start unless we **update configuration**.
* We’d need to update the build config to point to the new file.

```json
// angular.json
"build": {
  "options": {
    "main": "src/new-main.ts"
  }
}
```

---

## **22. What is a template?**

* A template defines the **HTML view** of a component.
* It controls **what the user sees**.
* Templates can include Angular features like:

  * Data binding
  * Directives
  * Pipes

```typescript
@Component({
  template: `
    <h1>{{title}}</h1>
    <button (click)="onClick()">Click me</button>
    <div *ngIf="showMessage">{{message}}</div>
  `
})
export class MyComponent {
  title = 'Hello Angular';
  showMessage = true;
}
```

---

## **23. What is the difference between promise and observable**

* **Promise** handles a **single value** and executes immediately.
* **Observable** can emit **multiple values over time**.
* Observables are **cancelable** and support operators like `map`, `filter`.
* Angular prefers Observables, especially with HTTP.

```typescript
// Promise - single value, eager
const promise = fetch('/api/data').then(res => res.json());

// Observable - multiple values, lazy
const observable$ = this.http.get('/api/data');
const subscription = observable$.subscribe(data => console.log(data));
subscription.unsubscribe(); // Can cancel
```

---

## **24. What is Angular Router and why is it used?**

* Angular Router is used for **navigation between views**.
* It enables **single-page application routing**.
* It loads components **without reloading the page**.
* Routes are defined using paths and components.

```typescript
// Route configuration
const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'users/:id', component: UserComponent },
  { path: '', redirectTo: '/home', pathMatch: 'full' }
];

// Navigation
this.router.navigate(['/users', userId]);
```

---

## **25. What is the purpose of the `ngOnInit` method in Angular components?**

* `ngOnInit` runs **once after component initialization**.
* Used to:

  * Fetch data
  * Initialize variables
* It runs **after input properties are set**.

```typescript
export class UserComponent implements OnInit {
  users: User[] = [];
  
  ngOnInit() {
    this.loadUsers();
    this.setupSubscriptions();
  }
  
  private loadUsers() {
    this.userService.getUsers().subscribe(users => this.users = users);
  }
}
```

---

## **26. How does Angular handle event binding?**

* Event binding allows the app to **respond to user actions**.
* Uses parentheses `( )`.
* Common events: `click`, `keyup`, `submit`.

```typescript
// Component
export class ButtonComponent {
  onClick(event: Event) {
    console.log('Button clicked!', event);
  }
  
  onInputChange(value: string) {
    console.log('Input changed:', value);
  }
}
```

```html
<!-- Template -->
<button (click)="onClick($event)">Click me</button>
<input (input)="onInputChange($event.target.value)">
```

---

## **27. How can you make an HTTP request in Angular?**

* Angular uses **HttpClient** for HTTP calls.
* It returns **Observables**.
* First, import `HttpClientModule`.

```typescript
@Injectable()
export class DataService {
  constructor(private http: HttpClient) {}
  
  getUsers() {
    return this.http.get<User[]>('/api/users');
  }
  
  createUser(user: User) {
    return this.http.post<User>('/api/users', user);
  }
}
```

---

## **28. What is `ngIf` used for?**

* `ngIf` is a **structural directive**.
* It conditionally **adds or removes elements** from the DOM.
* Useful for showing or hiding content.

```html
<!-- Traditional syntax -->
<div *ngIf="isLoggedIn">Welcome back!</div>
<div *ngIf="users.length > 0; else noUsers">User list</div>

<ng-template #noUsers>
  <p>No users found</p>
</ng-template>

<!-- New control flow (Angular 17+) -->
@if (isLoggedIn) {
  <div>Welcome back!</div>
}
```

---

## **29. What is the `ngFor` directive used for in Angular**

* `ngFor` is used to **loop through collections**.
* It dynamically renders elements based on data.
* Commonly used with arrays.

```html
<!-- Basic usage -->
<li *ngFor="let user of users">{{user.name}}</li>

<!-- With index and tracking -->
<li *ngFor="let user of users; let i = index; trackBy: trackByUserId">
  {{i + 1}}. {{user.name}}
</li>

<!-- New control flow (Angular 17+) -->
@for (user of users; track user.id) {
  <li>{{user.name}}</li>
}
```

```typescript
trackByUserId(index: number, user: User) {
  return user.id;
}
```

---

## **30. What is the `ngClass` directive used for in Angular?**

* `ngClass` dynamically **adds or removes CSS classes**.
* Used for conditional styling.
* Helps keep templates clean.

```html
<!-- Object syntax -->
<div [ngClass]="{'active': isActive, 'disabled': !isEnabled}">Content</div>

<!-- Array syntax -->
<div [ngClass]="['btn', isActive ? 'btn-primary' : 'btn-secondary']">Button</div>

<!-- String syntax -->
<div [ngClass]="getClasses()">Dynamic classes</div>
```

```typescript
export class MyComponent {
  isActive = true;
  isEnabled = false;
  
  getClasses() {
    return this.isActive ? 'highlight bold' : 'normal';
  }
}
```

---

## **31. What is a template reference variable in Angular**

* A template reference variable gives **direct access to a DOM element**.
* Defined using `#`.
* Useful for reading input values or element properties.

```html
<!-- Reference to input element -->
<input #nameInput type="text">
<button (click)="greet(nameInput.value)">Greet</button>

<!-- Reference to component -->
<app-child #childComponent></app-child>
<button (click)="childComponent.doSomething()">Call Child Method</button>
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

```typescript
// Reactive Form
export class UserComponent {
  userForm = new FormGroup({
    name: new FormControl('', Validators.required),
    email: new FormControl('', [Validators.required, Validators.email])
  });
}
```

```html
<!-- Template-driven -->
<form #userForm="ngForm">
  <input [(ngModel)]="user.name" name="name" required>
</form>

<!-- Reactive -->
<form [formGroup]="userForm">
  <input formControlName="name">
</form>
```

---

### **33. What is the purpose of the `ngModel` directive in Angular?**

* `ngModel` enables **two-way data binding** between the template and component.
* It automatically keeps **UI and component data in sync**.
* Commonly used in **template-driven forms**.
* When the input value changes, the component updates, and vice versa.


```typescript
export class LoginComponent {
  username = '';
  password = '';
}
```

```html
<input [(ngModel)]="username" placeholder="Username">
<input [(ngModel)]="password" type="password" placeholder="Password">
<p>Hello {{username}}!</p>
```

---

### **34. What is the `async` pipe in Angular, and how does it work with Observables?**

* The `async` pipe **subscribes to an Observable or Promise automatically**.
* It updates the UI when new data arrives.
* It also **handles unsubscribe automatically**, preventing memory leaks.
* This reduces boilerplate code in components.


```typescript
export class UserComponent {
  users$ = this.userService.getUsers();
  currentTime$ = interval(1000).pipe(map(() => new Date()));
}
```

```html
<div *ngFor="let user of users$ | async">{{user.name}}</div>
<p>Current time: {{currentTime$ | async | date:'medium'}}</p>
```

---

### **35. What is lazy loading in Angular? How does it improve application performance?**

* Lazy loading means **loading modules only when they are needed**.
* It reduces **initial bundle size** and **faster app startup**.
* Commonly used for large feature modules like Admin or Dashboard.
* Improves performance especially in big applications.


```typescript
// App routing
const routes: Routes = [
  {
    path: 'admin',
    loadChildren: () => import('./admin/admin.module').then(m => m.AdminModule)
  },
  {
    path: 'users',
    loadChildren: () => import('./users/users.module').then(m => m.UsersModule)
  }
];
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


```typescript
export class CounterComponent {
  count = 0;
  
  // Angular detects this change automatically
  increment() {
    this.count++; // DOM updates automatically
  }
  
  // Manual change detection
  constructor(private cdr: ChangeDetectorRef) {}
  
  manualUpdate() {
    this.cdr.detectChanges();
  }
}
```

---

### **37. Explain the concept of zones in Angular.**

* Angular uses **Zone.js** to track asynchronous operations.
* Zones notify Angular when something async finishes.
* This triggers **automatic change detection**.
* Developers don’t need to manually refresh the UI after async tasks.


```typescript
import { NgZone } from '@angular/core';

export class MyComponent {
  constructor(private ngZone: NgZone) {}
  
  // Run outside Angular zone (no change detection)
  heavyComputation() {
    this.ngZone.runOutsideAngular(() => {
      setTimeout(() => {
        // Heavy work here
      }, 1000);
    });
  }
}
```

---

### **38. What are `@Input` and `@Output` decorators in Angular?**

* `@Input` allows **parent to child data flow**.
* `@Output` allows **child to parent communication** using events.
* Together, they enable **component interaction**.


```typescript
// Child component
export class ChildComponent {
  @Input() message: string;
  @Output() buttonClick = new EventEmitter<string>();
  
  onClick() {
    this.buttonClick.emit('Button clicked!');
  }
}
```

```html
<!-- Parent template -->
<app-child 
  [message]="parentMessage" 
  (buttonClick)="handleChildClick($event)">
</app-child>
```

---

### **39. What is the difference between `localStorage` and `sessionStorage` in Angular?**

* Both are **browser storage mechanisms**.
* `localStorage`:

  * Data persists even after browser close
* `sessionStorage`:

  * Data is cleared when the tab is closed
* Angular accesses them using standard Web APIs.


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


```typescript
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable()
export class MyHttpInterceptor {
  intercept(req, next) {
    const token = localStorage.getItem('csrf_token');
    const cloned = req.clone({
      headers: req.headers.set('X-CSRF-TOKEN', token)
    });
    return next.handle(cloned);
  }
}
```

---

### **41. What are Angular modules and how do they help in organizing an application?**

* Angular modules group **related components, directives, pipes, and services**.
* They help keep the app **organized, modular, and scalable**.
* Modules support **lazy loading**, improving performance.
* Every Angular app has at least one module: `AppModule`.


```typescript
@NgModule({
  declarations: [UserListComponent, UserDetailComponent],
  imports: [CommonModule, FormsModule],
  providers: [UserService],
  exports: [UserListComponent]
})
export class UserModule {}

// Feature module with routing
@NgModule({
  imports: [RouterModule.forChild([
    { path: '', component: UserListComponent }
  ])]
})
export class UserRoutingModule {}
```

---

### **42. What is a custom directive, and how do you create one?**

* A custom directive lets you **manipulate DOM behavior or appearance**.
* Used when you want reusable UI logic.
* Created using Angular CLI and decorated with `@Directive`.


```typescript
import { Directive, ElementRef, Renderer2 } from '@angular/core';

@Directive({
  selector: '[appHighlight]'
})
export class HighlightDirective {
  constructor(private el: ElementRef, private renderer: Renderer2) {
    this.renderer.setStyle(this.el.nativeElement, 'color', 'blue');
  }
}
```

Usage in the template:
```html
<p appHighlight>Text will be highlighted in blue</p>
```

---

### **43. How do you create and use services in Angular?**

* Services are used for **business logic, API calls, and shared data**.
* They promote **code reusability and separation of concerns**.
* Created with `@Injectable` and injected using Dependency Injection.

```bash
ng generate service my-service
```
```typescript
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor(private http: HttpClient) {}
  private users: User[] = [];
  
  getUsers(): Observable<User[]> {
    return this.http.get<User[]>('/api/users');
  }
  
  addUser(user: User): Observable<User> {
    return this.http.post<User>('/api/users', user);
  }
}

// Usage in component
export class UserComponent {
  constructor(private userService: UserService) {}
  
  loadUsers() {
    this.userService.getUsers().subscribe(users => this.users = users);
  }
}
```

---

### **44. What is the role of the `RouterModule` in Angular?**

* `RouterModule` enables **navigation between views**.
* It maps URLs to components.
* Supports lazy loading, route guards, and parameters.


```typescript
// App routing module
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { AboutComponent } from './about/about.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'users/:id', component: UserDetailComponent },
  { path: '', redirectTo: '/home', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
```

```html
<nav>
  <a routerLink="/home" routerLinkActive="active">Home</a>
  <a routerLink="/users" routerLinkActive="active">Users</a>
</nav>
<router-outlet></router-outlet>
```

---

### **45. How would you handle HTTP errors in Angular?**

* Angular handles HTTP errors using **RxJS `catchError`**.
* Errors can be handled globally using **HTTP interceptors**.
* This ensures better user feedback and logging.


```typescript
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class MyService {
  constructor(private http: HttpClient) {}

  fetchData(): Observable<any> {
    return this.http.get('https://api.example.com/data')
      .pipe(
        catchError(error => {
          console.error('An error occurred:', error);
          return throwError(error); // rethrow the error to be handled by the component
        })
      );
  }
}
```

In the component:

```typescript
this.myService.fetchData().subscribe(
  data => this.handleData(data),
  error => this.handleError(error)
);
```

---

### **46. How can you optimize the performance of an Angular application?**

1.  **Lazy Loading**: Load modules only when required, reducing the initial bundle size.
2.  **Ahead-of-Time (AOT) Compilation**: Pre-compile Angular templates and components, resulting in faster application startup.
3.  **Tree Shaking**: Eliminate unused code from the final bundle by ensuring only the necessary code is included.
4.  **Change Detection Strategy**: Use `OnPush` change detection to reduce unnecessary checks for components with immutable input data.
5.  **TrackBy in `ngFor`**: Use `trackBy` to improve the performance of list rendering by minimizing DOM manipulations.
6.  **Use Web Workers**: Offload heavy computations to background threads using web workers.
7.  **Optimizing Images and Assets**: Compress images and assets, use lazy loading for images, and serve them in modern formats like WebP.
8.  **Service Workers and PWA**: Implement service workers for caching assets and making the app available offline.


```typescript
// OnPush strategy
@Component({
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class OptimizedComponent {}

// TrackBy function
trackByUserId(index: number, user: User) {
  return user.id;
}

// Preloading strategy
RouterModule.forRoot(routes, {
  preloadingStrategy: PreloadAllModules
})
```

---

### **47. What is the role of the Angular CLI in the development process?**

* Angular CLI automates **project setup and configuration**.
* Used for generating components, services, and modules.
* Handles build, test, and deployment efficiently.


```bash
# Generate components and services
ng generate component user-list
ng generate service user

# Build and serve
ng serve --open
ng build --prod

# Run tests
ng test
ng e2e
```

---

### **48. How can you handle routing with route guards in Angular?**

* Route guards control **access to routes**.
* Used for authentication, authorization, and unsaved changes.
* Common guards: `CanActivate`, `CanDeactivate`.


```typescript
import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(private authService: AuthService, private router: Router) {}

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): boolean {
    if (this.authService.isAuthenticated()) {
      return true;
    } else {
      this.router.navigate(['/login']);
      return false;
    }
  }
}
```

In the routing configuration:

```typescript
const routes: Routes = [
  { path: 'dashboard', component: DashboardComponent, canActivate: [AuthGuard] },
];
```

---

### **49. What are interceptors in Angular? How would you use them for adding headers or logging API requests?**

* Interceptors intercept **all HTTP requests and responses**.
* Used for adding auth headers, logging, or error handling.
* Applied globally.


1. **Create the Interceptor**:

   ```typescript
   import  { Injectable } from '@angular/core';
   import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
   import { Observable } from 'rxjs';

   @Injectable()
   export class AuthInterceptor implements HttpInterceptor {
     intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
       const token = localStorage.getItem('authToken');  // Retrieve token from storage

       if (token) {
         // Clone the request and add the Authorization header
         const clonedRequest = req.clone({
           setHeaders: {
             Authorization: `Bearer ${token}`
           }
         });
         return next.handle(clonedRequest);
       }

       return next.handle(req);  // Return the original request if no token
     }
   }
   ```

2. **Register the Interceptor**:

   ```typescript
   import  { NgModule } from '@angular/core';
   import { HTTP_INTERCEPTORS } from '@angular/common/http';
   import { AuthInterceptor } from './auth.interceptor';

   @NgModule({
     providers: [
       { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }
     ]
   })
   export class AppModule { }
   ```

---

### **50. What is the purpose of the `ng-content` and `ng-template` directive in Angular?**

* `ng-content` enables **content projection**.
* `ng-template` defines **reusable or conditional templates**.
* Useful for building reusable UI components.


```typescript
// Child component with projection
@Component({
  selector: 'app-card',
  template: `
    <div class="card">
      <ng-content select="[slot=header]"></ng-content>
      <ng-content></ng-content>
    </div>
  `
})
export class CardComponent {}
```

```html
<!-- Parent usage -->
<app-card>
  <h2 slot="header">Card Title</h2>
  <p>Card content goes here</p>
</app-card>
```

```html
<!-- ng-template -->
<div *ngIf="false; else noAccess">
  <p>Admin Panel</p>
</div>

<ng-template #noAccess>
  <p>You don’t have permission.</p>
</ng-template>
```

---

### **51. What is a resolver in Angular, and when would you use one?**

* A resolver **fetches data before route loads**.
* Ensures the component has data on initialization.
* Improves user experience by avoiding empty screens.


```typescript
@Injectable()
export class UserResolver implements Resolve<User> {
  constructor(private userService: UserService) {}
  
  resolve(route: ActivatedRouteSnapshot): Observable<User> {
    const userId = route.params['id'];
    return this.userService.getUser(userId);
  }
}

// Route configuration
{
  path: 'user/:id',
  component: UserDetailComponent,
  resolve: { user: UserResolver }
}

// Access resolved data
ngOnInit() {
  this.user = this.route.snapshot.data['user'];
}
```

---

### **52. How does Angular support internationalization (i18n)?**

* Angular supports i18n using **built-in localization tools**.
* Text is marked using `i18n` attribute.
* Translations are loaded based on locale.

```html
<!-- Mark text for translation -->
<p i18n="@@welcome-message">Welcome to our app!</p>
<p i18n="user.greeting">Hello {{name}}!</p>

<!-- Pluralization -->
<span i18n>{count, plural, =0 {no items} =1 {one item} other {{{count}} items}}</span>
```

```bash
# Extract messages
ng extract-i18n

# Build for specific locale
ng build --localize
```

---

## **Advanced Angular Questions**
### **53. What is a singleton service in Angular?**

* A singleton service means **only one instance** of the service exists in the app.
* It is shared across all components.
* Achieved by providing the service at the **root level**.
* Useful for shared state, caching, and authentication.


```typescript
@Injectable({
  providedIn: 'root' // Creates singleton
})
export class UserService {
  private currentUser: User | null = null;
  
  setCurrentUser(user: User) {
    this.currentUser = user; // Shared across app
  }
  
  getCurrentUser(): User | null {
    return this.currentUser;
  }
}
```

---

### **54. Difference between `ngOnInit` and `constructor` in Angular components**

* `constructor` is used for **dependency injection only**.
* `ngOnInit` is called **after component initialization**.
* Business logic, API calls should go in `ngOnInit`.
* `constructor` runs first, `ngOnInit` runs once after inputs are set.


```typescript
export class UserComponent implements OnInit {
  @Input() userId: string;
  user: User;
  
  constructor(private userService: UserService) {
    // Only dependency injection here
    console.log(this.userId); // undefined - inputs not set yet
  }
  
  ngOnInit() {
    // Component initialization logic
    console.log(this.userId); // Available now
    this.loadUser();
  }
  
  private loadUser() {
    this.userService.getUser(this.userId).subscribe(user => this.user = user);
  }
}
```

---

### **55. What is the RxJS library, and how is it used in Angular?**

* RxJS is a library for **reactive programming using Observables**.
* Angular uses RxJS heavily for **HTTP calls, events, and async data**.
* It helps manage streams of data over time.

```typescript
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class DataService {
  constructor(private http: HttpClient) {}

  getData(): Observable<any> {
    return this.http.get('https://api.example.com/data').pipe(
      catchError(err => {
        console.error('Error occurred:', err);
        throw err;
      })
    );
  }
}
```

Learn Operators like:
- **`𝘀𝘄𝗶𝘁𝗰𝗵𝗠𝗮𝗽`** : For cancelling previous requests and switching to new ones.
- **`𝗺𝗲𝗿𝗴𝗲𝗠𝗮𝗽`** : To handle multiple concurrent requests.
- **`𝗱𝗲𝗯𝗼𝘂𝗻𝗰𝗲𝗧𝗶𝗺𝗲`** : For waiting before reacting to user input (perfect for search boxes).
- **`𝘁𝗮𝗸𝗲𝗨𝗻𝘁𝗶𝗹`** : To unsubscribe cleanly when the component is destroyed.
- **`𝘀𝗵𝗮𝗿𝗲𝗥𝗲𝗽𝗹𝗮𝘆`** : To share the same observable without re-subscribing multiple times.
- **`𝗰𝗼𝗺𝗯𝗶𝗻𝗲𝗟𝗮𝘁𝗲𝘀𝘁`** : When you need the latest value from multiple observables.
- **`𝗳𝗼𝗿𝗸𝗝𝗼𝗶𝗻`** : For executing multiple observables in parallel and waiting for all to complete.

---

### **56. Explain operators like `map`, `filter`, `merge`, and `switchMap`**

- **`map`**: Transforms emitted values.
- **`filter`**: Filters emitted values based on a condition.
- **`merge`**: Combines multiple observables, emitting values from all of them.
- **`switchMap`**: Switches to a new observable and cancels the previous one whenever a new value is emitted.

```typescript
// Transform data
users$.pipe(map(users => users.length))

// Filter values
numbers$.pipe(filter(n => n > 10))

// Combine streams
merge(stream1$, stream2$)

// Switch to new observable
searchTerm$.pipe(
  switchMap(term => this.http.get(`/search?q=${term}`))
)
```

---

### **57. How do you handle error handling in RxJS streams?**

* RxJS uses `catchError` to handle errors in streams.
* Errors can be handled without breaking the app.
* You can return a fallback value or rethrow the error.

- **`catchError`**: This operator catches errors in the observable stream and allows you to recover from the error or return an alternative observable (e.g., an empty observable, a default value, etc.).
- **`retry`**: Retries an operation a given number of times if it fails, useful for transient errors.
- **`finalize`**: Executes cleanup code or logic when an observable completes or errors out.

```typescript
import  { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';

this.dataService.getData().pipe(
  catchError(error => {
    console.error('Error occurred:', error);
    return of([]); // Return an empty array as fallback data
  })
).subscribe(data => {
  console.log(data);
});
```

---

### **58. Difference between `switchMap`, `concatMap`, and `mergeMap`**

* **switchMap:** Use when you only care about the latest emitted value and want to cancel previous requests.
* **concatMap:** Use when tasks must run one after another in a guaranteed order.
* **mergeMap:** Use when you want all tasks to run concurrently without waiting.

**Use case**: Fetching the latest search results.

```typescript
codesearchTerm$ = new Subject<string>();

this.searchTerm$.pipe(
  switchMap(term => this.searchService.search(term))
).subscribe(results => {
  console.log(results);
});
```

  **Use case**: Uploading files one after the other.

```typescript
codefileQueue$.pipe(
  concatMap(file => this.uploadFile(file))
).subscribe(response => {
  console.log('File uploaded:', response);
});
```

**Use case**: Fetching multiple resources concurrently (e.g., loading user data, comments, and posts in parallel).

```typescript
codeuser$.pipe(
  mergeMap(user => this.loadUserPosts(user.id))
).subscribe(posts => {
  console.log(posts);
});
```

---

### **59. How to handle parallel service calls in Angular?**

* Use RxJS `forkJoin` for parallel API calls.
* All requests run together.
* Emits result only when all complete.

**forkJoin** runs multiple HTTP requests **in parallel** but gives the result **only when all requests finish**. It's perfect when you want everything loaded together—for example, loading user details, orders, and profile data at once.

**combineLatest** also runs calls in parallel, but it gives you the **latest values** whenever any request updates. It doesn’t wait for all to finish again. It's useful when you're working with live or continuously updating data.

#### ✅ **1. forkJoin Example (Waits for all API calls)**
```typescript
import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { forkJoin } from 'rxjs';

@Component({
  selector: 'app-parallel-requests',
  templateUrl: './parallel-requests.component.html',
  styleUrls: ['./parallel-requests.component.css']
})
export class ParallelRequestsComponent implements OnInit {

  constructor(private http: HttpClient) { }

  ngOnInit() {
    this.getDataFromServices();
  }

  getDataFromServices() {
    // Define the API calls (services)
    const request1 = this.http.get('https://api.example.com/data1');
    const request2 = this.http.get('https://api.example.com/data2');
    const request3 = this.http.get('https://api.example.com/data3');

    // Use forkJoin to make the requests in parallel
    forkJoin([request1, request2, request3]).subscribe(
      ([response1, response2, response3]) => {
        console.log('Response from API 1:', response1);
        console.log('Response from API 2:', response2);
        console.log('Response from API 3:', response3);
      },
      (error) => {
        console.error('Error:', error);
      }
    );
  }
}
```

#### ✅ **2. combineLatest Example (Gives latest values)**

```typescript
import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { combineLatest } from 'rxjs';

@Component({
  selector: 'app-combine-latest',
  templateUrl: './combine-latest.component.html',
  styleUrls: ['./combine-latest.component.css']
})
export class CombineLatestComponent implements OnInit {

  constructor(private http: HttpClient) { }

  ngOnInit() {
    this.getCombinedData();
  }

  getCombinedData() {
    const request1 = this.http.get('https://api.example.com/data1');
    const request2 = this.http.get('https://api.example.com/data2');

    // Use combineLatest to get the latest data from both
    combineLatest([request1, request2]).subscribe(
      ([response1, response2]) => {
        console.log('Response from API 1:', response1);
        console.log('Response from API 2:', response2);
      },
      (error) => {
        console.error('Error:', error);
      }
    );
  }
}
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

```typescript
// Simple service-based state
@Injectable({ providedIn: 'root' })
export class AppStateService {
  private state = new BehaviorSubject({ users: [], loading: false });
  
  getState() {
    return this.state.asObservable();
  }
  
  updateUsers(users: User[]) {
    this.state.next({ ...this.state.value, users });
  }
}

// NgRx example
import { Store } from '@ngrx/store';
import { AppState } from './store/reducers';
import { loadData } from './store/actions';

constructor(private store: Store<AppState>) {}

ngOnInit() {
  this.store.dispatch(loadData());
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

**AOT (Ahead-of-Time) compilation** means Angular compiles your code **during the build**, before it reaches the browser. This makes the app load faster, reduces bundle size, and catches template errors early. It's what we use for **production**.

**JIT (Just-in-Time) compilation** compiles the app **in the browser at runtime**. It’s mainly used in **development** because builds are faster, but the app loads slower since the browser has to compile everything.


```typescript
// AOT benefits:
// - Templates compiled at build time
// - Tree shaking removes unused code
// - Early template error detection
// - Better security (no eval() statements)

// Build with AOT (default in production)
ng build --prod

// JIT compilation (deprecated in Angular 9+)
// Templates compiled in browser at runtime
```

---

### **62. What are Angular Universal applications?**

* Angular Universal enables **Server-Side Rendering (SSR)**.
* HTML is rendered on the **server**, then sent to the browser.
* Benefits:

  * Faster first load
  * Better SEO
  * Better performance on slow devices

**In short:**
Angular Universal provides **faster first load**, **better SEO**, and **better performance**, especially for content-heavy websites.

**Flow:**

```
Request → Server renders HTML → Browser hydrates Angular app
```

```typescript
// Install Angular Universal
ng add @nguniversal/express-engine

// Build and serve SSR
npm run build:ssr
npm run serve:ssr

// Check if running in browser
import { isPlatformBrowser } from '@angular/common';

constructor(@Inject(PLATFORM_ID) private platformId: Object) {}

ngOnInit() {
  if (isPlatformBrowser(this.platformId)) {
    // Browser-only code
  }
}
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


```typescript
// Class decorators
@Component({ selector: 'app-user' })
@Injectable({ providedIn: 'root' })
@NgModule({ imports: [CommonModule] })

// Property decorators
@Input() userId: string;
@Output() userSelected = new EventEmitter();
@ViewChild('template') template: TemplateRef<any>;

// Method decorators
@HostListener('click', ['$event'])
onClick(event: Event) {}
```

---

### **64. How do you configure environment-specific settings in Angular?**

* Angular uses **environment files**.
* Different values for dev, test, and production.

```typescript
// environment.ts (development)
export const environment = {
  production: false,
  apiUrl: 'http://localhost:3000/api',
  enableLogging: true
};

// environment.prod.ts (production)
export const environment = {
  production: true,
  apiUrl: 'https://api.myapp.com',
  enableLogging: false
};

// Usage in service
import { environment } from '../environments/environment';

@Injectable()
export class ApiService {
  private apiUrl = environment.apiUrl;
}
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
In Angular, an `AuthGuard` is used to protect routes from unauthorized access by ensuring the user is authenticated before they can access specific parts of the application. If the user is not authenticated, the guard can redirect them to a login page or a specific route.


```typescript
import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root',
})
export class AuthGuard implements CanActivate {
  constructor(private authService: AuthService, private router: Router) {}

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<boolean> | Promise<boolean> | boolean {
    // Check if the user is authenticated
    if (this.authService.isLoggedIn()) {
      return true; // User is authenticated, allow access to the route
    } else {
      // User is not authenticated, redirect to login page
      this.router.navigate(['/login']);
      return false;
    }
  }
}

// Route configuration
{
  path: 'dashboard',
  component: DashboardComponent,
  canActivate: [AuthGuard]
}
```

---

### **67. Difference between Authentication and Authorization**
Authentication verifies who the user is (login credentials), while Authorization determines what the user can access (permissions). Authentication comes first, then authorization checks permissions.

* **Authentication** → *Who are you?*

  * Login, credentials, tokens
* **Authorization** → *What can you access?*

  * Roles, permissions

```typescript
// Authentication - verify identity
login(credentials: LoginCredentials) {
  return this.http.post('/auth/login', credentials).pipe(
    tap(response => this.storeToken(response.token))
  );
}

// Authorization - check permissions
@Injectable()
export class RoleGuard implements CanActivate {
  canActivate(route: ActivatedRouteSnapshot): boolean {
    const requiredRole = route.data['role'];
    return this.authService.hasRole(requiredRole);
  }
}
```

---

### **68. How would you troubleshoot performance issues in Angular?**

* **Use Angular DevTools** to check component tree, change detection cycles, and spot heavy components.
* **Use Chrome DevTools Performance tab** to find slow JavaScript execution, long tasks, and rendering issues.
* **Profile change detection** to see which components are running too often and causing unnecessary checks.
* **Check for memory leaks** using Chrome DevTools Memory tab and clean up subscriptions in `ngOnDestroy`.
* **Analyze API calls** in the Network tab to find slow, repeated, or unnecessary HTTP requests.
* **Improve bundle size** using lazy loading, code splitting, and tools like webpack-bundle-analyzer.

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


```typescript
// Performance debugging tools:
// 1. Angular DevTools (Chrome extension)
// 2. ng build --stats-json && npx webpack-bundle-analyzer

// Performance optimizations:
@Component({
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class OptimizedComponent {
  // Use trackBy functions
  trackByFn(index: number, item: any) {
    return item.id;
  }
  
  // Lazy load images
  // Use virtual scrolling for large lists
}

---


## **Testing Questions**

---

### **69. How do you approach unit testing in Angular?**

* I focus on **testing components, services, and pipes in isolation**.
* Angular uses **Jasmine** for assertions and **Karma** as the test runner.
* I mock dependencies to avoid real HTTP calls or services.
* Goal is to test **logic, not UI behavior**.


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


```ts
const spy = jasmine.createSpyObj('UserService', ['getUsers']);
spy.getUsers.and.returnValue(of([]));
```

---

### **72. How do you test a component with dependencies?**

* I mock dependent services instead of using real ones.
* For observables, I return mock data using `of()`.
* Child components can be stubbed if needed.


```ts
providers: [
  { provide: UserService, useValue: mockUserService }
]
```

---

### **73. What is a spy in Jasmine?**

* A **spy** tracks function calls and controls return values.
* Useful to verify if a method was called or not.


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

```typescript
// Simple state service
@Injectable({ providedIn: 'root' })
export class AppStateService {
  private state$ = new BehaviorSubject({
    users: [],
    loading: false,
    error: null
  });

  getState() {
    return this.state$.asObservable();
  }

  updateUsers(users: User[]) {
    this.state$.next({
      ...this.state$.value,
      users,
      loading: false
    });
  }
}

// NgRx for complex state
// Actions, Reducers, Effects, Selectors
```

---

### **76. How do you debug an Angular application?**

* Use **Chrome DevTools** for console and network.
* Use **Angular DevTools** for component tree and change detection.
* Add breakpoints in TypeScript files.
* Use `console.log()` for quick checks.


```ts
debugger;
```

---

### **77. What is the Singleton pattern and how does it relate to Angular services?**

* Singleton means **only one instance** exists.
* Angular services are singletons when provided in `root`.
* Used for shared data and logic.

```typescript
// Singleton service (default behavior)
@Injectable({
  providedIn: 'root' // Creates singleton
})
export class DataService {
  private data: any[] = [];
  
  addData(item: any) {
    this.data.push(item); // Shared across all components
  }
}

// Non-singleton (provided at component level)
@Component({
  providers: [DataService] // New instance per component
})
export class MyComponent {}
```

---

### **78. How do you handle component communication in Angular?**

* **Parent → Child:** `@Input()`
* **Child → Parent:** `@Output()`
* **Sibling / Unrelated:** Shared service with observables


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

```typescript
@Injectable({ providedIn: 'root' })
export class AuthService {
  login(credentials: any): Observable<any> {
    return this.http.post('/auth/login', credentials).pipe(
      tap(response => {
        localStorage.setItem('token', response.token);
      })
    );
  }
  
  getToken(): string {
    return localStorage.getItem('token') || '';
  }
  
  isAuthenticated(): boolean {
    const token = this.getToken();
    return token && !this.isTokenExpired(token);
  }
}

// HTTP Interceptor for JWT
@Injectable()
export class JwtInterceptor implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler) {
    const token = this.authService.getToken();
    if (token) {
      req = req.clone({
        setHeaders: { Authorization: `Bearer ${token}` }
      });
    }
    return next.handle(req);
  }
}
```

---

## **80. How would you handle API calls in Angular using `HttpClient`?**

* Angular uses `HttpClient` for REST API communication.
* It returns **Observables**, making async handling easy.
* Use services for API logic.
* Handle errors using `catchError`.
* Interceptors manage headers and logging globally.

```typescript
@Injectable({ providedIn: 'root' })
export class ApiService {
  constructor(private http: HttpClient) {}
  
  getUsers(): Observable<User[]> {
    return this.http.get<User[]>('/api/users').pipe(
      retry(3),
      catchError(error => {
        console.error('API Error:', error);
        return throwError(() => new Error('Failed to load users'));
      })
    );
  }
  
  createUser(user: User): Observable<User> {
    return this.http.post<User>('/api/users', user);
  }
}

// Component usage
export class UserComponent implements OnDestroy {
  private destroy$ = new Subject<void>();
  
  loadUsers() {
    this.apiService.getUsers().pipe(
      takeUntil(this.destroy$)
    ).subscribe({
      next: users => this.users = users,
      error: error => this.handleError(error)
    });
  }
  
  ngOnDestroy() {
    this.destroy$.next();
    this.destroy$.complete();
  }
}
```

---

## **81. How would you handle file uploads in Angular? Best practices for large files**

* Use `FormData` to send files.
* Track upload progress using `HttpEventType`.
* Validate file size and type before upload.
* Use chunking or backend streaming for large files.


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

```typescript
// Angular's built-in protections:
// - Automatic HTML sanitization
// - CSRF protection with HttpClient
// - Content Security Policy (CSP)

// Additional security measures:
@Injectable()
export class SecurityService {
  // Sanitize user input
  sanitizeHtml(html: string): SafeHtml {
    return this.sanitizer.sanitize(SecurityContext.HTML, html) || '';
  }
  
  // Validate file uploads
  validateFile(file: File): boolean {
    const allowedTypes = ['image/jpeg', 'image/png'];
    return allowedTypes.includes(file.type) && file.size < 5000000;
  }
}

// Environment-based security
export const environment = {
  production: true,
  enableDevTools: false,
  apiUrl: 'https://secure-api.com' // Always use HTTPS
};
```

---

## **83. How does Angular prevent Cross-Site Scripting (XSS)?**

* Angular **automatically sanitizes** values in templates.
* It escapes HTML, scripts, and URLs.
* Prevents injecting malicious code into the DOM.
* Direct DOM access is discouraged.


```typescript
// Angular automatically sanitizes:
export class SafeComponent {
  userInput = '<script>alert("XSS")</script>'; // Automatically escaped
  
  // Safe interpolation
  template = `<div>{{userInput}}</div>`; // Script tags rendered as text
  
  // Bypass sanitization (use carefully)
  constructor(private sanitizer: DomSanitizer) {}
  
  getTrustedHtml(html: string): SafeHtml {
    return this.sanitizer.bypassSecurityTrustHtml(html);
  }
}

// Content Security Policy (CSP)
// Add to index.html:
// <meta http-equiv="Content-Security-Policy" 
//       content="default-src 'self'; script-src 'self'">
```

---

## **84. How do you secure an Angular application in production?**

* **Use HTTPS** so all data between client and server is encrypted.
* **Build in production mode** to minify and obfuscate code.
* **Set up a strong Content Security Policy (CSP)** to prevent XSS attacks.
* **Rely on Angular’s built-in XSS protection** by using safe data binding.
* **Secure API communication** using JWT/OAuth, secure cookies (HttpOnly, Secure, SameSite), and rate limiting.
* **Implement proper authentication and authorization** (MFA, RBAC/ABAC).
* **Keep dependencies updated** and run security audits (npm audit).
* **Validate all inputs on the server side**, not just in Angular.


```typescript
// Production build optimizations
ng build --prod --aot --build-optimizer

// Enable production mode
import { enableProdMode } from '@angular/core';
if (environment.production) {
  enableProdMode();
}

// Security headers (server-side)
// Content-Security-Policy
// X-Frame-Options: DENY
// X-Content-Type-Options: nosniff
// Strict-Transport-Security

// Environment security
export const environment = {
  production: true,
  apiUrl: 'https://api.example.com', // HTTPS only
  enableLogging: false // Disable in production
};
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

```json
{
  "projects": {
    "my-app": {
      "architect": {
        "build": {
          "options": {
            "outputPath": "dist/my-app",
            "index": "src/index.html",
            "main": "src/main.ts",
            "assets": ["src/favicon.ico", "src/assets"],
            "styles": ["src/styles.css"],
            "scripts": []
          },
          "configurations": {
            "production": {
              "budgets": [{"type": "initial", "maximumWarning": "2mb"}],
              "outputHashing": "all"
            }
          }
        }
      }
    }
  }
}
```

---

### **87. Creating custom Angular schematics**

* Schematics automate **code generation and project rules**.
* Used to create **custom components, modules, or standards**.
* Helpful for large teams to keep consistency.
* Created using **@angular-devkit/schematics**.

```typescript
// Install schematics CLI
npm install -g @angular-devkit/schematics-cli

// Create schematic collection
schematics blank my-schematic

// Define schematic rule
import { Rule, SchematicContext, Tree } from '@angular-devkit/schematics';

export function mySchematic(options: any): Rule {
  return (tree: Tree, context: SchematicContext) => {
    // Create component file
    tree.create('/src/app/my-component.ts', `
      @Component({
        selector: 'app-${options.name}',
        template: '<h1>${options.title}</h1>'
      })
      export class ${options.name}Component {}
    `);
    return tree;
  };
}

// Use custom schematic
ng generate my-schematic:component --name=user --title="User Page"
```

---

### **88. Experience integrating Angular with other technologies**

* Integrated Angular with **REST APIs, GraphQL, Firebase, and .NET/Node backends**.
* Used **Micro-frontends (Module Federation)** with React apps.
* Embedded Angular into **legacy systems** gradually.
* Used **WebSockets** for real-time updates.

```typescript
// Angular Elements for micro-frontends
import { createCustomElement } from '@angular/elements';

@NgModule({
  declarations: [MyWidgetComponent],
  entryComponents: [MyWidgetComponent]
})
export class MyWidgetModule {
  constructor(private injector: Injector) {
    const element = createCustomElement(MyWidgetComponent, { injector });
    customElements.define('my-widget', element);
  }
}

// Integration patterns:
// - REST APIs with HttpClient
// - GraphQL with Apollo
// - WebSockets with Socket.io
// - State management with NgRx
// - UI libraries (Material, PrimeNG)
```

---

### **89. What are some of the most challenging problems you've faced while working with Angular, and how did you solve them**

* Biggest challenge: **performance with large data tables**.
* Solved using **OnPush change detection**, **virtual scrolling**, and **lazy loading**.
* Also faced **memory leaks**, fixed by unsubscribing properly.

```typescript
// Memory leak prevention
export class MyComponent implements OnDestroy {
  private destroy$ = new Subject<void>();
  
  ngOnInit() {
    this.dataService.getData().pipe(
      takeUntil(this.destroy$) // Automatic unsubscribe
    ).subscribe(data => this.data = data);
  }
  
  ngOnDestroy() {
    this.destroy$.next();
    this.destroy$.complete();
  }
}

// Performance optimization
@Component({
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class OptimizedComponent {
  trackByFn = (index: number, item: any) => item.id;
}

// Migration strategies:
// - ng update for automated updates
// - Incremental migration approach
// - Comprehensive testing after updates
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



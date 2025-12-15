## **Basic Angular Questions**
### 1. What is Angular?

Angular is a TypeScript-based web framework developed by Google for building dynamic single-page applications. It provides a complete solution with features like two-way data binding, dependency injection, and component-based architecture.

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

### 2. What is the difference between Angular and AngularJS?

AngularJS is the first version built with JavaScript, while Angular (2+) is completely rewritten in TypeScript. Angular offers better performance, mobile support, and uses component-based architecture instead of MVC.

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

### 3. What are new features added in Angular 18?

Angular 18 introduced Material 3 support, experimental esbuild support for dev server, built-in control flow syntax (@if, @for), and improved hydration for better SSR performance.

```html
<!-- New control flow syntax -->
@if (user.isLoggedIn) {
  <p>Welcome {{user.name}}</p>
}

@for (item of items; track item.id) {
  <div>{{item.name}}</div>
}
```

### 4. What are the key components of Angular?

The main building blocks are Components (UI logic), Services (business logic), Modules (organize features), Directives (DOM manipulation), and Pipes (data transformation).

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

### 5. What is data binding in Angular? Explain the different types of data binding.

Data binding connects component data to the template. Types include: Interpolation `{{}}`, Property binding `[property]`, Event binding `(event)`, and Two-way binding `[(ngModel)]`.

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

### 6. What is an Angular lifecycle hook? Can you name some common lifecycle hooks?

Angular lifecycle hooks are special methods that run at different stages of a component’s life — from creation to destruction.
They allow us to write code at the right moment, like initializing data, reacting to input changes, or cleaning up.

**Common lifecycle hooks:**

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

### 7. What are decorators in Angular?

Decorators are TypeScript features that add metadata to classes, methods, or properties. Angular uses them to define components, services, and inject dependencies.

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

### 8. What is a directive in Angular? Can you name the different types of directives?

Directives are classes that add behavior to DOM elements. Three types: Component directives (with templates), Structural directives (change DOM structure), and Attribute directives (change appearance/behavior).

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

### 9. What are components?

Components are the basic building blocks of Angular applications. They control a part of the screen called a view, combining HTML template, TypeScript class, and CSS styles.

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

### 10. What are modules in Angular?

Modules are containers that group related components, services, and other code. They help organize the application and enable lazy loading for better performance.

```typescript
@NgModule({
  declarations: [AppComponent, HeaderComponent],
  imports: [BrowserModule, FormsModule],
  providers: [DataService],
  bootstrap: [AppComponent]
})
export class AppModule {}
```

### 11. What is a service in Angular?

Services are classes that handle business logic, data access, and shared functionality. They're typically injected into components using dependency injection.

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

### 12. What is dependency injection in Angular?

Dependency injection is a design pattern where Angular provides dependencies to a class instead of the class creating them. It promotes loose coupling and testability.

```typescript
@Component({...})
export class OrderComponent {
  constructor(
    private orderService: OrderService,
    private router: Router
  ) {}
}
```

### 13. What are Observables and how are they used in Angular?

Observables are streams that emit data over time. Angular uses them for HTTP requests, event handling, and reactive programming with RxJS operators.

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

### 14. What is a provider?

A provider tells Angular how to create and inject dependencies. It can be a class, value, or factory function registered in the injector.

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

### 15. What are pipes in Angular? Can you create custom pipes?

Pipes transform data in templates for display purposes. Angular provides built-in pipes like date, currency, and uppercase. You can create custom pipes for specific transformations.

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

// Usage
{{ longText | truncate:20 }}
```

### 16. What is an observable?

An Observable is a stream that emits data over time. It's lazy - meaning it doesn't execute until you subscribe to it. Unlike promises, observables can emit multiple values and can be cancelled.

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

### 17. What is an observer?

An Observer is an object that defines callback functions to handle the three types of notifications from an Observable: next (data), error (error handling), and complete (completion).

```typescript
const observer = {
  next: value => console.log('Received:', value),
  error: err => console.error('Error:', err),
  complete: () => console.log('Stream completed')
};

observable$.subscribe(observer);
```

### 18. What is multicasting?

Multicasting allows multiple subscribers to share the same Observable execution. By default, observables are unicast (each subscription creates a new execution). Use Subject or operators like share() for multicasting.

```typescript
// Unicast (default)
const source$ = interval(1000);

// Multicast with Subject
const subject = new Subject();
source$.subscribe(subject);
subject.subscribe(val => console.log('Sub1:', val));
subject.subscribe(val => console.log('Sub2:', val));
```

### 19. What is a bootstrapping module?

The bootstrapping module is the root module that Angular uses to start the application. It's typically AppModule, defined in main.ts, and contains the root component that gets loaded first.

```typescript
// main.ts
platformBrowserDynamic().bootstrapModule(AppModule);

// app.module.ts
@NgModule({
  bootstrap: [AppComponent]
})
export class AppModule {}
```

### 20. Which file loads first in Angular application?

The main.ts file loads first in an Angular application. It bootstraps the AppModule, which then loads the root component. The loading sequence is: main.ts → AppModule → AppComponent.

```typescript
// main.ts - Entry point
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { AppModule } from './app/app.module';

platformBrowserDynamic().bootstrapModule(AppModule);
```

### 21. If I rename `main.ts`, will application load?

No, the application won't load if you just rename main.ts because Angular CLI looks for this specific file as the entry point. You'd need to update the angular.json configuration to point to the new filename.

```json
// angular.json
"build": {
  "options": {
    "main": "src/new-main.ts"
  }
}
```

### 22. What is a template?

A template is the HTML view with Angular markup that defines how a component should be rendered. It can include data binding, directives, and pipes to create dynamic content.

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

### 23. What is the difference between promise and observable?

Promises handle single async values and are eager (execute immediately). Observables handle multiple values over time, are lazy (execute on subscription), and can be cancelled.

```typescript
// Promise - single value, eager
const promise = fetch('/api/data').then(res => res.json());

// Observable - multiple values, lazy
const observable$ = this.http.get('/api/data');
const subscription = observable$.subscribe(data => console.log(data));
subscription.unsubscribe(); // Can cancel
```

### 24. What is an Angular router and why is it used?

Angular Router enables navigation between different views or components in a single-page application. It manages the browser URL and displays the appropriate component based on the route configuration.

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

### 25. What is the purpose of the `ngOnInit` method in Angular components?

ngOnInit is a lifecycle hook that runs after Angular initializes the component's data-bound properties. It's the ideal place for component initialization logic, API calls, and setting up subscriptions.

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

### 26. How does Angular handle event binding?

Angular uses event binding with parentheses syntax to listen to DOM events. When an event occurs, Angular executes the specified method in the component class.

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

### 27. How can you make an HTTP request in Angular?

Use Angular's HttpClient service to make HTTP requests. It returns observables and provides methods for GET, POST, PUT, DELETE operations with built-in error handling.

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

### 28. What is the `ngIf` directive used for in Angular?

ngIf is a structural directive that conditionally includes or removes an element from the DOM based on a boolean expression. It completely removes the element when false, not just hides it.

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

### 29. What is the `ngFor` directive used for in Angular?

ngFor is a structural directive that repeats a template for each item in a collection. It's essential for rendering lists and provides local variables like index and tracking for performance.

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

### 30. What is the `ngClass` directive used for in Angular?

ngClass dynamically adds or removes CSS classes based on expressions. It accepts strings, arrays, or objects to conditionally apply styles to elements.

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

### 31. What is a template reference variable in Angular?

Template reference variables are references to DOM elements or Angular components in templates. You create them with the # symbol and can access their properties and methods directly in the template.

```html
<!-- Reference to input element -->
<input #nameInput type="text">
<button (click)="greet(nameInput.value)">Greet</button>

<!-- Reference to component -->
<app-child #childComponent></app-child>
<button (click)="childComponent.doSomething()">Call Child Method</button>
```

## **Intermediate Angular Questions**
### 32. What are Angular forms? What are the two types of forms in Angular?

Angular forms handle user input and validation. There are two types: Template-driven forms (using ngModel and template syntax) and Reactive forms (using FormControl and FormGroup for programmatic control).

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

### 33. What is the purpose of the `ngModel` directive in Angular?

ngModel creates two-way data binding between form controls and component properties. It automatically syncs the input value with the component property and updates the UI when the property changes.

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

### 34. What is the `async` pipe in Angular, and how does it work with Observables?

The async pipe automatically subscribes to Observables or Promises and returns the latest emitted value. It also handles unsubscription when the component is destroyed, preventing memory leaks.

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

### 35. What is lazy loading in Angular? How does it improve application performance?

Lazy loading loads feature modules only when needed, reducing initial bundle size and improving startup performance. Routes are configured to load modules on-demand when users navigate to them.

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

### 36. What is change detection in Angular? How does it work?

Change detection is Angular's mechanism to check if component data has changed and update the DOM accordingly. It runs automatically after events, HTTP requests, and timers using Zone.js.

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

### 37. Explain the concept of zones in Angular.

Zones are execution contexts that persist across async operations. Angular uses Zone.js to patch browser APIs and automatically trigger change detection when async operations complete.

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

### 38. What are `@Input` and `@Output` decorators in Angular?

@Input allows parent components to pass data to child components. @Output allows child components to emit events to parent components using EventEmitter.

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

### 39. What is the difference between `localStorage` and `sessionStorage` in Angular?

localStorage persists data until explicitly cleared, while sessionStorage only lasts for the browser session. Both store key-value pairs as strings in the browser.

```typescript
export class StorageService {
  // Persists across browser sessions
  setUserPreference(key: string, value: any) {
    localStorage.setItem(key, JSON.stringify(value));
  }
  
  // Only for current session
  setTempData(key: string, value: any) {
    sessionStorage.setItem(key, JSON.stringify(value));
  }
  
  getUserPreference(key: string) {
    return JSON.parse(localStorage.getItem(key) || 'null');
  }
}
```

### 40. How does Angular handle cross-site request forgery (CSRF)?

**Cross-Site Request Forgery (CSRF)** is a type of security vulnerability where an attacker tricks a user into making an unwanted request to a different site using the user's credentials. 

To prevent CSRF, most servers generate a **CSRF token** that must be included with every request that alters data (e.g., POST, PUT, DELETE). This token is typically set as a cookie or in the response header.

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

### 41. What are Angular modules and how do they help in organizing an application?

Modules group related components, services, and other code into cohesive blocks. They help organize large applications, enable lazy loading, and provide clear boundaries between features.

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

### 42. What is a custom directive, and how do you create one?

Custom directives add behavior to DOM elements. You create them using @Directive decorator and can make attribute directives (modify behavior) or structural directives (modify DOM structure).

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

### 43. How do you create and use services in Angular?

Services are created with @Injectable decorator and handle business logic, data access, and shared functionality. They're injected into components through dependency injection.

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

### 44. What is the role of the `RouterModule` in Angular?

RouterModule configures navigation and routing in Angular applications. It provides directives like routerLink and router-outlet, and services like Router for programmatic navigation.


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

### 45. How would you handle HTTP errors in Angular?

Handle HTTP errors using RxJS operators like catchError, or in the subscribe error callback. Create error interceptors for global error handling and user-friendly error messages.

Example of handling HTTP errors:

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

### 46. How can you optimize the performance of an Angular application?

Key optimizations include lazy loading modules, OnPush change detection strategy, trackBy functions in ngFor, preloading strategies, and tree shaking. Also use Angular DevTools to identify performance bottlenecks.

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

### 47. What is the role of the Angular CLI in the development process?

Angular CLI streamlines development by generating components, services, and modules, managing builds, running tests, and handling deployments. It provides consistent project structure and automates common tasks.

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

### 48. How can you handle routing with route guards in Angular?

Route guards control navigation by implementing interfaces like CanActivate, CanDeactivate, or CanLoad. They're used for authentication, authorization, and preventing navigation from unsaved forms.

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

### 49. What are interceptors in Angular? How would you use them for adding headers or logging API requests?

Interceptors intercept HTTP requests and responses globally. They're perfect for adding authentication headers, logging, error handling, and request/response transformation.

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

### 50. What is the purpose of the `ng-content` and `ng-template` directive in Angular?

ng-content enables content projection (transclusion) to pass content from parent to child components. ng-template defines template fragments that can be conditionally rendered or reused.

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

### 51. What is a resolver in Angular, and when would you use one?

Resolvers pre-fetch data before route activation, ensuring components have required data when they load. They improve user experience by preventing empty states during data loading.

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

### 52. How does Angular support internationalization (i18n)?

**Internationalization (i18n)** in Angular is the process of designing your application so that it can be easily adapted to different languages and regions without engineering changes. Angular provides built-in tools for internationalization, making it easier to translate and format data (such as dates, numbers, and currencies) based on the user's locale.

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

## **Advanced Angular Questions**
### 53. What is a singleton service in Angular?

A singleton service has only one instance throughout the application lifecycle. Services provided in root or module level are singletons by default, sharing state across all components.

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

### 54. Explain the difference between `ngOnInit` and `constructor` in Angular components.

Constructor is for dependency injection and basic initialization. ngOnInit runs after Angular initializes data-bound properties and is the proper place for component setup and API calls.

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

### 55. What is the `RxJS` library, and how is it used in Angular?

RxJS is a reactive programming library for handling asynchronous data streams. Angular uses it extensively for HTTP requests, event handling, and reactive programming patterns with Observables.

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

Mastering 𝗥𝘅𝗝𝗦 makes your Angular skills skyrocket.

𝗥𝘅𝗝𝗦 isn’t just for making requests – it’s a whole mindset. Dive deep into it and your Angular apps will never be the same.

### 56. Explain operators like `map`, `filter`, `merge`, and `switchMap`

These RxJS operators transform data streams: map transforms values, filter selects values based on conditions, merge combines multiple streams, and switchMap switches to new inner observables.

#### Summary:

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

### 57. How do you handle error handling in RxJS streams?

Use catchError operator to handle errors gracefully, retry for transient failures, and throwError to re-throw errors. Always provide fallback values or user-friendly error messages.

- **`catchError`**: This operator catches errors in the observable stream and allows you to recover from the error or return an alternative observable (e.g., an empty observable, a default value, etc.).
- **`retry`**: Retries an operation a given number of times if it fails, useful for transient errors.
- **`finalize`**: Executes cleanup code or logic when an observable completes or errors out.

#### Example: Using `catchError` for error handling

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

### 58. What is the difference between `switchMap`, `concatMap`, and `mergeMap` in RxJS?

switchMap cancels previous inner observables when new ones arrive, concatMap waits for completion before processing next, and mergeMap runs all inner observables concurrently.

#### ✅ **Summary: switchMap vs concatMap vs mergeMap**

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

### 59. How to handle parallel service calls in Angular?

Use forkJoin for parallel calls that must all complete, combineLatest for ongoing streams, or merge for independent parallel operations. Handle errors appropriately for each pattern.

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
**Use when:** You want all API results together.

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
**Use when:** You want updates whenever any request changes.

### 60. How does Angular handle state management, and what libraries can be used for state management in Angular applications?

Angular uses services for simple state management. For complex apps, use NgRx (Redux pattern), Akita, or NGXS. These provide predictable state updates, time-travel debugging, and better scalability.

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

### 61. What is Ahead-of-Time (AOT) compilation, and how does it differ from Just-in-Time (JIT) compilation?

**AOT (Ahead-of-Time) compilation** means Angular compiles your code **during the build**, before it reaches the browser. This makes the app load faster, reduces bundle size, and catches template errors early. It's what we use for **production**.

**JIT (Just-in-Time) compilation** compiles the app **in the browser at runtime**. It’s mainly used in **development** because builds are faster, but the app loads slower since the browser has to compile everything.

**In short:**
**AOT** → compiles at build time, faster runtime, better for production.
**JIT** → compiles in the browser, faster development cycle.


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

### 62. What are Angular Universal applications, and how does server-side rendering work in Angular?

Angular Universal enables server-side rendering (SSR), pre-rendering HTML on the server before sending to browsers. This improves SEO, faster initial page loads, and better performance on slow devices.

**In short:**
Angular Universal provides **faster first load**, **better SEO**, and **better performance**, especially for content-heavy websites.

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

### 63. What are Angular decorators, and what role do they play in Angular development?

Decorators are TypeScript features that add metadata to classes, methods, and properties. Angular uses them extensively to define components, services, dependency injection, and configure behavior.

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

### 64. How do you configure Angular to work with environment-specific settings?

Use environment files to manage different configurations for development, staging, and production. Angular CLI automatically replaces environment imports during build.

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

### 65. What are the advantages and disadvantages of using Angular for web development?

Advantages: TypeScript support, comprehensive framework, strong CLI, excellent tooling, enterprise-ready. Disadvantages: steep learning curve, large bundle size, frequent updates, complexity for simple apps.

```typescript
// Advantages demonstrated:
// - Strong typing with TypeScript
// - Dependency injection
// - Comprehensive ecosystem
// - Enterprise features (testing, i18n, SSR)

// Considerations:
// - Bundle size optimization needed
// - Learning curve for RxJS
// - Migration effort between versions
```

### 66. How to implement AuthGuard in Angular?

AuthGuard implements CanActivate interface to protect routes based on authentication status. It checks user authentication and redirects to login if unauthorized.

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

### 67. What is the difference between Authentication and Authorization?

Authentication verifies who the user is (login credentials), while Authorization determines what the user can access (permissions). Authentication comes first, then authorization checks permissions.

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

## **Testing Questions**
### 68. How would you troubleshoot performance issues in Angular? What tools and techniques would you use?

Use Angular DevTools for change detection profiling, Chrome DevTools for performance analysis, bundle analyzer for size optimization, and OnPush strategy for performance improvements.

#### **Simple Summary (Interview-Friendly):**

* **Use Angular DevTools** to check component tree, change detection cycles, and spot heavy components.
* **Use Chrome DevTools Performance tab** to find slow JavaScript execution, long tasks, and rendering issues.
* **Profile change detection** to see which components are running too often and causing unnecessary checks.
* **Check for memory leaks** using Chrome DevTools Memory tab and clean up subscriptions in `ngOnDestroy`.
* **Analyze API calls** in the Network tab to find slow, repeated, or unnecessary HTTP requests.
* **Improve bundle size** using lazy loading, code splitting, and tools like webpack-bundle-analyzer.

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
```

### 69. How do you approach unit testing in Angular? What tools and libraries do you use for testing?

Angular uses Jasmine for test framework and Karma for test runner. Write tests for components, services, and pipes using TestBed for configuration and dependency injection.

```typescript
describe('UserService', () => {
  let service: UserService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [UserService]
    });
    service = TestBed.inject(UserService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  it('should fetch users', () => {
    const mockUsers = [{ id: 1, name: 'John' }];
    
    service.getUsers().subscribe(users => {
      expect(users).toEqual(mockUsers);
    });

    const req = httpMock.expectOne('/api/users');
    req.flush(mockUsers);
  });
});
```

### 70. What is the purpose of `TestBed` in Angular testing, and how do you use it to configure tests?

TestBed creates a testing module that configures and creates components for testing. It provides dependency injection, mocking capabilities, and isolated testing environment.

```typescript
describe('UserComponent', () => {
  let component: UserComponent;
  let fixture: ComponentFixture<UserComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserComponent],
      imports: [HttpClientTestingModule],
      providers: [
        { provide: UserService, useValue: mockUserService }
      ]
    });

    fixture = TestBed.createComponent(UserComponent);
    component = fixture.componentInstance;
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
```

### 71. How do you mock services and HTTP requests during unit tests in Angular?

Use HttpClientTestingModule for HTTP mocking, jasmine.createSpyObj for service mocking, and TestBed providers to inject mocks into components.

```typescript
// Mock HTTP requests
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

// Mock services
const mockUserService = jasmine.createSpyObj('UserService', ['getUsers']);
mockUserService.getUsers.and.returnValue(of([{ id: 1, name: 'John' }]));

// Configure TestBed with mocks
TestBed.configureTestingModule({
  imports: [HttpClientTestingModule],
  providers: [
    { provide: UserService, useValue: mockUserService }
  ]
});
```

### 72. Explain how to test an Angular component that has dependencies like services, observables, or other components.

Mock dependencies using TestBed providers, use fixture.detectChanges() to trigger change detection, and test component behavior through inputs, outputs, and DOM interactions.

```typescript
describe('UserListComponent', () => {
  let component: UserListComponent;
  let userService: jasmine.SpyObj<UserService>;

  beforeEach(() => {
    const spy = jasmine.createSpyObj('UserService', ['getUsers']);
    
    TestBed.configureTestingModule({
      declarations: [UserListComponent],
      providers: [{ provide: UserService, useValue: spy }]
    });

    userService = TestBed.inject(UserService) as jasmine.SpyObj<UserService>;
    userService.getUsers.and.returnValue(of([{ id: 1, name: 'John' }]));
  });

  it('should load users on init', () => {
    component.ngOnInit();
    expect(userService.getUsers).toHaveBeenCalled();
    expect(component.users.length).toBe(1);
  });
});
```

### 73. Can you explain the concept of a "spy" in Jasmine? How would you use it in unit testing?

Spies are Jasmine's way to mock functions and track their calls. They can replace method implementations, return specific values, and verify if methods were called with correct parameters.

```typescript
describe('Component with spy', () => {
  let component: MyComponent;
  let userService: UserService;

  beforeEach(() => {
    userService = TestBed.inject(UserService);
  });

  it('should call service method', () => {
    // Create spy on existing method
    spyOn(userService, 'getUsers').and.returnValue(of([]));
    
    component.loadUsers();
    
    expect(userService.getUsers).toHaveBeenCalled();
    expect(userService.getUsers).toHaveBeenCalledWith({ active: true });
  });

  it('should handle service errors', () => {
    spyOn(userService, 'getUsers').and.throwError('Network error');
    
    component.loadUsers();
    
    expect(component.errorMessage).toBe('Failed to load users');
  });
});
```

## **Architecture & Design Patterns Questions**
### 74. How would you organize a large Angular application?

Use feature modules, shared modules, core module for singletons, lazy loading for performance, and consistent folder structure. Follow Angular style guide and implement barrel exports.

```typescript
// Project structure:
// src/
//   app/
//     core/          (singleton services)
//     shared/        (common components, pipes)
//     features/      (feature modules)
//       user/
//         components/
//         services/
//         user.module.ts
//         user-routing.module.ts

// Feature module
@NgModule({
  declarations: [UserListComponent, UserDetailComponent],
  imports: [CommonModule, UserRoutingModule, SharedModule],
  providers: [UserService]
})
export class UserModule {}

// Barrel exports (index.ts)
export * from './user.service';
export * from './user.model';
```

### 75. What is state management in Angular?

State management handles application data flow and storage. Simple apps use services with BehaviorSubject, while complex apps use NgRx, Akita, or NGXS for predictable state updates and debugging.

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

### 76. How to debug Angular application?

Use Angular DevTools for component inspection, Chrome DevTools for performance, console.log for basic debugging, and Angular CLI source maps for TypeScript debugging. Set breakpoints in browser dev tools.

```typescript
// Enable source maps in angular.json
"sourceMap": true

// Debug in component
export class MyComponent {
  ngOnInit() {
    console.log('Component initialized', this.data);
    debugger; // Browser breakpoint
  }
}

// Angular DevTools (Chrome extension)
// - Inspect component tree
// - View component properties
// - Profile change detection
```

### 77. What is the Singleton pattern and how does it relate to Angular services?

Singleton ensures only one instance exists throughout the application. Angular services provided in root are singletons by default, sharing the same instance across all components.

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

### 78. How do you handle component communication in Angular?

Use @Input/@Output for parent-child communication, services with observables for sibling communication, and ViewChild for direct component access. EventEmitter handles custom events.

```typescript
// Parent-Child with @Input/@Output
@Component({
  template: '<child [data]="parentData" (childEvent)="handleEvent($event)"></child>'
})
export class ParentComponent {
  parentData = 'Hello';
  handleEvent(data: any) { console.log(data); }
}

// Service for sibling communication
@Injectable({ providedIn: 'root' })
export class CommunicationService {
  private subject = new Subject<any>();
  
  sendMessage(message: any) {
    this.subject.next(message);
  }
  
  getMessage() {
    return this.subject.asObservable();
  }
}
```

## **Backend Integration Questions**
### 79. How would you handle authentication and authorization in an Angular application? Can you explain JWT (JSON Web Tokens) and how it works with Angular?

JWT stores user credentials as encoded tokens. Store JWT in localStorage/sessionStorage, send in Authorization header, and use guards for route protection. Implement token refresh for security.

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

### 80. How would you handle API calls in Angular? Explain the use of `HttpClient` and how you would manage request/response handling and error handling.

HttpClient handles HTTP requests with observables. Use interceptors for global headers, catchError for error handling, and retry for transient failures. Always unsubscribe to prevent memory leaks.

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

### 81. How would you handle file uploads in Angular? What are the best practices for handling large files?

Use FormData for file uploads, track upload progress, implement chunked uploads for large files, and validate file types/sizes. Use reactive forms for better control.

1. **Basic File Upload**:

   ```typescript
   codeuploadFile(event: any) {
     const file = event.target.files[0];
     const formData = new FormData();
     formData.append('file', file, file.name);

     this.http.post('https://api.example.com/upload', formData).subscribe(
       (response) => {
         console.log('File uploaded successfully', response);
       },
       (error) => {
         console.error('File upload error', error);
       }
     );
   }
   ```

2. **HTML Input for File Selection**:

   ```html
   <input type="file" (change)="uploadFile($event)" />
   ```

## **Security Questions**
### 82. What are common security concerns in Angular applications and how do you mitigate them?

Main concerns: XSS, CSRF, insecure dependencies, and data exposure. Angular provides built-in XSS protection, CSRF tokens, and sanitization. Always validate inputs and use HTTPS.

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

### 83. How does Angular prevent Cross-Site Scripting (XSS)?

Angular automatically sanitizes values before displaying them in the DOM. It treats all values as untrusted by default and escapes HTML, preventing malicious script injection.

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

### 84. How do you secure an Angular application in a production environment?

Enable production mode, use HTTPS, implement CSP headers, minimize bundle size, validate all inputs, use environment variables for secrets, and regularly update dependencies.

**Simple Summary for Securing an Angular App**
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

## **Miscellaneous Questions**
### 85. What is Angular Universal, and how does it enable server-side rendering (SSR)?

Angular Universal renders Angular applications on the server, generating static HTML before sending to browsers. This improves SEO, initial load performance, and social media sharing.

```typescript
// Install Angular Universal
ng add @nguniversal/express-engine

// Build for SSR
npm run build:ssr
npm run serve:ssr

// Platform-specific code
import { isPlatformBrowser } from '@angular/common';

export class MyComponent {
  constructor(@Inject(PLATFORM_ID) private platformId: Object) {}
  
  ngOnInit() {
    if (isPlatformBrowser(this.platformId)) {
      // Browser-only code (localStorage, window, etc.)
      localStorage.setItem('key', 'value');
    }
  }
}

// Transfer state for hydration
constructor(
  private transferState: TransferState,
  @Inject(PLATFORM_ID) private platformId: Object
) {}
```

### 86. Can you explain the purpose of `angular.json`? What kind of configurations can be done there?

angular.json is the workspace configuration file that defines build settings, asset paths, environment files, and CLI options for projects. It controls how Angular CLI builds and serves applications.

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

### 87. How do you create custom Angular schematics using Angular CLI?

Schematics are code generators that create or modify files. Create custom schematics to automate repetitive tasks like generating components with specific patterns or configurations.

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

### 88. What is your experience with integrating Angular with other frameworks or technologies?

Angular integrates well with various technologies: micro-frontends with single-spa, mobile with Ionic, desktop with Electron, and backend APIs. Use Angular Elements for embedding in other frameworks.

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

### 89. What are some of the most challenging problems you've faced while working with Angular, and how did you solve them?

Common challenges include performance optimization, memory leaks, complex state management, and migration between versions. Solutions involve profiling, proper subscription management, and following best practices.

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

### 90. How do you stay up to date with new releases and changes in the Angular framework?

Follow Angular blog, subscribe to Angular newsletters, attend conferences, participate in community forums, and regularly check Angular GitHub repository for updates and roadmaps.

```typescript
// Resources for staying updated:
// - Angular Blog (blog.angular.io)
// - Angular Weekly newsletter
// - Angular conferences (ng-conf, AngularConnect)
// - GitHub repository (angular/angular)
// - Angular DevTools updates
// - Community forums (Reddit, Stack Overflow)

// Update strategy:
// 1. Read release notes thoroughly
// 2. Test in development environment
// 3. Update dependencies gradually
// 4. Use ng update for automated migration
ng update @angular/core @angular/cli
ng update @angular/material

// Version compatibility checking
npm outdated
ng version
```

### 91. Can you walk us through a specific project where you applied Angular to solve a complex problem?

I built an e-commerce dashboard with real-time inventory tracking. Used NgRx for state management, lazy loading for performance, WebSocket integration for live updates, and implemented role-based access control with guards.

```typescript
// Real-time inventory with WebSocket
@Injectable()
export class InventoryService {
  private socket = io('ws://localhost:3000');
  
  getInventoryUpdates(): Observable<InventoryUpdate> {
    return new Observable(observer => {
      this.socket.on('inventory-update', data => observer.next(data));
    });
  }
}

// NgRx state management
@Injectable()
export class InventoryEffects {
  loadInventory$ = createEffect(() =>
    this.actions$.pipe(
      ofType(loadInventory),
      switchMap(() => this.inventoryService.getInventory().pipe(
        map(items => loadInventorySuccess({ items }))
      ))
    )
  );
}

// Lazy loaded feature module
const routes: Routes = [
  {
    path: 'inventory',
    loadChildren: () => import('./inventory/inventory.module').then(m => m.InventoryModule),
    canActivate: [AdminGuard]
  }
];
```

## **RxJS Specific Questions**
### 92. What is a `Subject` and `BehaviorSubject` in Angular RxJS? How are they different?

Subject is a multicast observable that can emit values to multiple subscribers. BehaviorSubject stores the last emitted value and immediately sends it to new subscribers, while Subject doesn't store values.

```typescript
// Subject - no initial value
const subject = new Subject<string>();
subject.subscribe(val => console.log('Sub1:', val));
subject.next('Hello'); // Only current subscribers receive this

// BehaviorSubject - has initial value and stores last value
const behaviorSubject = new BehaviorSubject<string>('Initial');
behaviorSubject.subscribe(val => console.log('Sub1:', val)); // Gets 'Initial'
behaviorSubject.next('Updated');

// Late subscriber gets last value
behaviorSubject.subscribe(val => console.log('Sub2:', val)); // Gets 'Updated'

// Common use case - current user state
@Injectable()
export class AuthService {
  private currentUser$ = new BehaviorSubject<User | null>(null);
  
  getCurrentUser() {
    return this.currentUser$.asObservable();
  }
}
```

### 93. What is the difference between `ngFor` and `ngForOf`?

There's no difference - `ngForOf` is the actual directive name, while `*ngFor` is syntactic sugar. The asterisk (*) is Angular's structural directive shorthand that gets expanded to `ngForOf`.

```html
<!-- Syntactic sugar (what we write) -->
<li *ngFor="let item of items; let i = index">{{i}}: {{item.name}}</li>

<!-- What Angular actually creates -->
<ng-template ngFor let-item [ngForOf]="items" let-i="index">
  <li>{{i}}: {{item.name}}</li>
</ng-template>

<!-- Both are equivalent -->
<!-- *ngFor is just a shorthand for the template syntax -->
```

```typescript
// The directive is actually called NgForOf
import { NgForOf } from '@angular/common';

// Usage in component
export class ListComponent {
  items = [
    { id: 1, name: 'Item 1' },
    { id: 2, name: 'Item 2' }
  ];
}
```

### 94. What is the role of `ngZone` in Angular, and how does it help with performance optimization?

NgZone wraps asynchronous operations to trigger change detection automatically. You can run code outside Angular's zone to prevent unnecessary change detection cycles, improving performance for heavy operations.

```typescript
import { NgZone } from '@angular/core';

export class PerformanceComponent {
  constructor(private ngZone: NgZone) {}
  
  // Run outside Angular zone (no change detection)
  startHeavyComputation() {
    this.ngZone.runOutsideAngular(() => {
      setInterval(() => {
        // Heavy computation that doesn't affect UI
        this.processData();
      }, 100);
    });
  }
  
  // Run inside Angular zone (triggers change detection)
  updateUI() {
    this.ngZone.run(() => {
      this.displayData = this.processedData;
    });
  }
  
  // Check if running in Angular zone
  checkZone() {
    console.log('In Angular zone:', NgZone.isInAngularZone());
  }
}
```

### 95. What is the purpose of Angular's `Renderer2`?

Renderer2 provides a safe way to manipulate DOM elements without directly accessing them. It's platform-agnostic, works with server-side rendering, and provides security by preventing direct DOM manipulation.

```typescript
import { Renderer2, ElementRef } from '@angular/core';

@Directive({
  selector: '[appHighlight]'
})
export class HighlightDirective {
  constructor(
    private renderer: Renderer2,
    private el: ElementRef
  ) {}
  
  @HostListener('mouseenter') onMouseEnter() {
    // Safe DOM manipulation
    this.renderer.setStyle(this.el.nativeElement, 'backgroundColor', 'yellow');
    this.renderer.addClass(this.el.nativeElement, 'highlighted');
  }
  
  @HostListener('mouseleave') onMouseLeave() {
    this.renderer.removeStyle(this.el.nativeElement, 'backgroundColor');
    this.renderer.removeClass(this.el.nativeElement, 'highlighted');
  }
  
  // Create and append elements safely
  addElement() {
    const div = this.renderer.createElement('div');
    const text = this.renderer.createText('Dynamic content');
    this.renderer.appendChild(div, text);
    this.renderer.appendChild(this.el.nativeElement, div);
  }
}
```

### 96. Explain the difference between `ngOnChanges` and `ngDoCheck`.

ngOnChanges fires when @Input properties change and receives a SimpleChanges object. ngDoCheck runs on every change detection cycle, allowing custom change detection logic but requiring manual comparison.

```typescript
export class MyComponent implements OnChanges, DoCheck {
  @Input() user: User;
  @Input() count: number;
  
  private previousUser: User;
  
  // Fires only when @Input properties change
  ngOnChanges(changes: SimpleChanges) {
    if (changes['user']) {
      console.log('User changed:', changes['user'].currentValue);
    }
    
    if (changes['count']) {
      console.log('Count changed from', changes['count'].previousValue, 
                  'to', changes['count'].currentValue);
    }
  }
  
  // Runs on every change detection cycle
  ngDoCheck() {
    // Manual change detection for complex objects
    if (this.user && this.previousUser) {
      if (this.user.name !== this.previousUser.name) {
        console.log('User name changed manually detected');
      }
    }
    this.previousUser = { ...this.user };
  }
}
```

```typescript
// Usage example
@Component({
  template: `
    <child-component 
      [user]="currentUser" 
      [count]="counter">
    </child-component>
    <button (click)="updateUser()">Update User</button>
    <button (click)="counter++">Increment</button>
  `
})
export class ParentComponent {
  currentUser = { name: 'John', age: 30 };
  counter = 0;
  
  updateUser() {
    // This will trigger ngOnChanges
    this.currentUser = { name: 'Jane', age: 25 };
  }
}
```

# Angular Interview Questions & Answers

## 8. What is a directive in Angular? Can you name the different types of directives?

**Answer:**
A directive is a class that adds behavior to elements in your Angular applications.

**Three types of directives:**
* **Component directives** - Directives with templates
* **Structural directives** - Change DOM layout (add/remove elements)
* **Attribute directives** - Change appearance or behavior


**Examples:**

```typescript
// Structural directive
<div *ngIf="isVisible">Show me</div>
<li *ngFor="let item of items">{{item}}</li>

// Attribute directive
<p [ngClass]="{'active': isActive}">Text</p>
<div [ngStyle]="{'color': textColor}">Styled text</div>

// Custom attribute directive
@Directive({
  selector: '[appHighlight]'
})
export class HighlightDirective {
  @HostListener('mouseenter') onMouseEnter() {
    this.el.nativeElement.style.backgroundColor = 'yellow';
  }
}
```

---

## 9. What are components?

**Answer:**
Components are the building blocks of Angular applications. They control a part of the screen called a view.

**Key features:**
* Has a TypeScript class with logic
* HTML template for the view
* CSS styles for appearance
* Decorator that defines metadata

**Example:**

```typescript
@Component({
  selector: 'app-user',
  template: `
    <h2>{{user.name}}</h2>
    <p>Age: {{user.age}}</p>
    <button (click)="updateAge()">Update Age</button>
  `,
  styles: [`
    h2 { color: blue; }
  `]
})
export class UserComponent {
  user = { name: 'John', age: 25 };
  
  updateAge() {
    this.user.age++;
  }
}
```

---

## 10. What are modules in Angular?

**Answer:**
Modules are containers that group related components, directives, pipes, and services together.

**Key points:**
* Every Angular app has at least one module (AppModule)
* Use @NgModule decorator
* Helps organize and lazy load features
* Defines compilation context

**Example:**

```typescript
@NgModule({
  declarations: [
    AppComponent,
    UserComponent,
    HeaderComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [UserService],
  bootstrap: [AppComponent]
})
export class AppModule { }

// Feature module
@NgModule({
  declarations: [ProductComponent],
  imports: [CommonModule],
  exports: [ProductComponent]
})
export class ProductModule { }
```

---

## 11. What is a service in Angular?

**Answer:**
A service is a class that provides specific functionality that can be shared across components.

**Use cases:**
* Data fetching from APIs
* Business logic
* Shared state management
* Utility functions

**Example:**

```typescript
@Injectable({
  providedIn: 'root'
})
export class UserService {
  private users: User[] = [];
  
  getUsers(): Observable<User[]> {
    return this.http.get<User[]>('/api/users');
  }
  
  addUser(user: User): void {
    this.users.push(user);
  }
  
  getUserById(id: number): User {
    return this.users.find(u => u.id === id);
  }
}

// Using in component
export class UserComponent {
  users: User[] = [];
  
  constructor(private userService: UserService) {}
  
  ngOnInit() {
    this.userService.getUsers().subscribe(
      users => this.users = users
    );
  }
}
```

---

## 12. What is dependency injection in Angular?

**Answer:**
Dependency injection is a design pattern where dependencies are provided to a class rather than creating them inside the class.

**Benefits:**
* Loose coupling
* Easy testing with mocks
* Better code reusability
* Angular handles object creation

**Example:**

```typescript
// Service
@Injectable({
  providedIn: 'root'
})
export class DataService {
  getData() {
    return ['item1', 'item2'];
  }
}

// Component with DI
export class MyComponent {
  data: string[] = [];
  
  // Angular injects DataService automatically
  constructor(private dataService: DataService) {}
  
  ngOnInit() {
    this.data = this.dataService.getData();
  }
}

// Testing with mock
class MockDataService {
  getData() {
    return ['mock1', 'mock2'];
  }
}

// In test
TestBed.configureTestingModule({
  providers: [
    { provide: DataService, useClass: MockDataService }
  ]
});
```

---

## 13. What are Observables and how are they used in Angular?

**Answer:**
Observables are streams of data that you can subscribe to. They handle asynchronous operations and events.

**Key features:**
* Handle multiple values over time
* Lazy execution
* Cancellable
* Rich operators for data transformation

**Example:**

```typescript
// HTTP service returning Observable
@Injectable()
export class ApiService {
  constructor(private http: HttpClient) {}
  
  getUsers(): Observable<User[]> {
    return this.http.get<User[]>('/api/users');
  }
}

// Component consuming Observable
export class UserComponent implements OnDestroy {
  users$ = this.apiService.getUsers();
  private destroy$ = new Subject<void>();
  
  constructor(private apiService: ApiService) {}
  
  ngOnInit() {
    // Subscribe with pipe
    this.users$.pipe(
      map(users => users.filter(u => u.active)),
      takeUntil(this.destroy$)
    ).subscribe(activeUsers => {
      console.log(activeUsers);
    });
  }
  
  ngOnDestroy() {
    this.destroy$.next();
    this.destroy$.complete();
  }
}

// Template with async pipe
// <div *ngFor="let user of users$ | async">{{user.name}}</div>
```

---

## 14. What is a provider?

**Answer:**
A provider tells Angular how to create and provide dependencies for dependency injection.

**Types of providers:**
* Class providers
* Value providers
* Factory providers
* Existing providers

**Example:**

```typescript
// Class provider (default)
providers: [UserService]
// Same as: { provide: UserService, useClass: UserService }

// Value provider
providers: [
  { provide: 'API_URL', useValue: 'https://api.example.com' }
]

// Factory provider
providers: [
  {
    provide: UserService,
    useFactory: (http: HttpClient) => new UserService(http),
    deps: [HttpClient]
  }
]

// Existing provider (alias)
providers: [
  { provide: NewService, useExisting: OldService }
]

// Using in component
constructor(
  private userService: UserService,
  @Inject('API_URL') private apiUrl: string
) {}
```

---

## 15. What are pipes in Angular? Can you create custom pipes?

**Answer:**
Pipes transform data in templates. They take input and return formatted output.

**Built-in pipes:**
* date, currency, uppercase, lowercase
* json, slice, async

**Custom pipes:**
* Use @Pipe decorator
* Implement PipeTransform interface

**Example:**

```typescript
// Using built-in pipes
// {{ user.name | uppercase }}
// {{ user.salary | currency:'USD' }}
// {{ user.birthDate | date:'shortDate' }}

// Custom pipe
@Pipe({
  name: 'truncate'
})
export class TruncatePipe implements PipeTransform {
  transform(value: string, limit: number = 10): string {
    if (!value) return '';
    return value.length > limit ? 
      value.substring(0, limit) + '...' : value;
  }
}

// Register in module
@NgModule({
  declarations: [TruncatePipe]
})

// Use in template
// {{ longText | truncate:20 }}

// Pure vs Impure pipes
@Pipe({
  name: 'filter',
  pure: false  // Impure pipe - runs on every change detection
})
export class FilterPipe implements PipeTransform {
  transform(items: any[], searchTerm: string): any[] {
    return items.filter(item => 
      item.name.toLowerCase().includes(searchTerm.toLowerCase())
    );
  }
}
```

# Angular Interview Questions & Answers

## 16. What is an observable?

An observable is like a data stream that can emit multiple values over time. Think of it as a pipe where data flows through.

**Key Points:**
- Handles asynchronous data
- Can emit multiple values
- Lazy - only executes when subscribed
- Can be cancelled

```typescript
import { Observable } from 'rxjs';

// Creating an observable
const myObservable = new Observable(observer => {
  observer.next('Hello');
  observer.next('World');
  observer.complete();
});

// Using it
myObservable.subscribe(data => console.log(data));
```

## 17. What is an observer?

An observer is the consumer that listens to observable data. It's like having three callbacks ready to handle different situations.

**Key Points:**
- Has three methods: next(), error(), complete()
- Receives data from observables
- Handles success, error, and completion

```typescript
const observer = {
  next: (value) => console.log('Got value:', value),
  error: (err) => console.log('Error:', err),
  complete: () => console.log('Stream completed')
};

myObservable.subscribe(observer);
```

## 18. What is multicasting?

Multicasting allows one observable to share its execution with multiple subscribers. Instead of creating separate executions, everyone gets the same data.

**Key Points:**
- Shares single execution among multiple subscribers
- Uses subjects for implementation
- More efficient than multiple subscriptions

```typescript
import { Subject } from 'rxjs';

const subject = new Subject();

// Multiple subscribers share same execution
subject.subscribe(data => console.log('Subscriber 1:', data));
subject.subscribe(data => console.log('Subscriber 2:', data));

subject.next('Shared data'); // Both get same data
```

## 19. What is a bootstrapping module?

The bootstrapping module is the root module that starts your Angular application. It's like the main entry point that kicks everything off.

**Key Points:**
- Usually AppModule
- Contains bootstrap array with root component
- Loaded first when app starts

```typescript
@NgModule({
  declarations: [AppComponent],
  imports: [BrowserModule],
  providers: [],
  bootstrap: [AppComponent] // This component starts the app
})
export class AppModule { }
```

## 20. Which file loads first in Angular application?

The `main.ts` file loads first. It's the entry point that bootstraps your entire application.

**Key Points:**
- main.ts is the starting point
- Calls platformBrowserDynamic()
- Bootstraps the AppModule

```typescript
// main.ts
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { AppModule } from './app/app.module';

platformBrowserDynamic()
  .bootstrapModule(AppModule)
  .catch(err => console.error(err));
```

## 21. If I rename `main.ts`, will application load?

No, the application won't load because Angular CLI looks for `main.ts` by default. You'd need to update the configuration.

**Key Points:**
- main.ts is configured in angular.json
- Must update "main" property if renamed
- Build process depends on this configuration

```json
// angular.json
"build": {
  "builder": "@angular-devkit/build-angular:browser",
  "options": {
    "main": "src/main.ts", // Change this if renamed
    "polyfills": "src/polyfills.ts"
  }
}
```

## 22. What is a template?

A template is the HTML view with Angular markup that defines how the component should be displayed. It's your component's face.

**Key Points:**
- HTML with Angular directives
- Uses data binding
- Can include pipes and expressions

```typescript
@Component({
  selector: 'app-user',
  template: `
    <h1>{{title}}</h1>
    <p *ngIf="showMessage">{{message}}</p>
    <button (click)="onClick()">Click me</button>
  `
})
export class UserComponent {
  title = 'Welcome';
  message = 'Hello World';
  showMessage = true;
  
  onClick() {
    this.showMessage = !this.showMessage;
  }
}
```

## 23. What is the difference between promise and observable?

Promises handle single async values, while observables handle streams of multiple values over time.

**Key Differences:**

| Promise | Observable |
|---------|------------|
| Single value | Multiple values |
| Eager execution | Lazy execution |
| Not cancellable | Cancellable |
| .then() | .subscribe() |

```typescript
// Promise - single value
const promise = new Promise(resolve => {
  setTimeout(() => resolve('Done'), 1000);
});
promise.then(data => console.log(data));

// Observable - multiple values
const observable = new Observable(observer => {
  let count = 0;
  setInterval(() => observer.next(count++), 1000);
});
const subscription = observable.subscribe(data => console.log(data));
// Can cancel: subscription.unsubscribe();
```

## 24. What is an Angular router and why is it used?

Angular Router enables navigation between different views/components in a single-page application. It's like having multiple pages without page refreshes.

**Key Points:**
- Manages navigation between components
- Updates URL without page reload
- Supports route guards and lazy loading
- Handles parameters and query strings

```typescript
// app-routing.module.ts
const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'users/:id', component: UserComponent },
  { path: 'about', component: AboutComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

// Navigation in component
export class NavComponent {
  constructor(private router: Router) {}
  
  goToUser(id: number) {
    this.router.navigate(['/users', id]);
  }
}
```

```html
<!-- Template navigation -->
<nav>
  <a routerLink="/">Home</a>
  <a routerLink="/about">About</a>
</nav>
<router-outlet></router-outlet>
```

# Angular Interview Questions & Answers

## 25. What is the purpose of the `ngOnInit` method in Angular components?

**Answer:**
- `ngOnInit` is a lifecycle hook that runs after component initialization
- Perfect place for component setup logic and API calls
- Runs once after the first `ngOnChanges`

**Example:**
```typescript
export class UserComponent implements OnInit {
  users: User[] = [];

  ngOnInit() {
    this.loadUsers();
    this.setupFormValidation();
  }

  loadUsers() {
    this.userService.getUsers().subscribe(users => {
      this.users = users;
    });
  }
}
```

## 26. How does Angular handle event binding?

**Answer:**
- Use parentheses `()` to bind DOM events to component methods
- Angular listens for events and executes the bound method
- Can pass event object using `$event`

**Example:**
```typescript
// Component
export class ButtonComponent {
  handleClick(event: Event) {
    console.log('Button clicked!', event);
  }

  handleInput(value: string) {
    console.log('Input value:', value);
  }
}
```

```html
<!-- Template -->
<button (click)="handleClick($event)">Click Me</button>
<input (input)="handleInput($event.target.value)" />
<form (submit)="onSubmit($event)">Submit</form>
```

## 27. How can you make an HTTP request in Angular?

**Answer:**
- Use `HttpClient` service from `@angular/common/http`
- Import `HttpClientModule` in your module
- Inject `HttpClient` in your service or component

**Example:**
```typescript
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable()
export class DataService {
  constructor(private http: HttpClient) {}

  // GET request
  getUsers() {
    return this.http.get<User[]>('/api/users');
  }

  // POST request
  createUser(user: User) {
    return this.http.post<User>('/api/users', user);
  }

  // PUT request
  updateUser(id: number, user: User) {
    return this.http.put<User>(`/api/users/${id}`, user);
  }
}
```

```typescript
// In component
export class UserComponent {
  constructor(private dataService: DataService) {}

  loadUsers() {
    this.dataService.getUsers().subscribe({
      next: users => this.users = users,
      error: err => console.error(err)
    });
  }
}
```

## 28. What is the `ngIf` directive used for in Angular?

**Answer:**
- Conditionally adds or removes elements from the DOM
- Takes a boolean expression
- Element is completely removed when condition is false

**Example:**
```html
<!-- Basic usage -->
<div *ngIf="isLoggedIn">Welcome back!</div>

<!-- With else condition -->
<div *ngIf="users.length > 0; else noUsers">
  <p>Found {{users.length}} users</p>
</div>
<ng-template #noUsers>
  <p>No users found</p>
</ng-template>

<!-- With async pipe -->
<div *ngIf="user$ | async as user">
  Hello, {{user.name}}!
</div>
```

```typescript
export class AppComponent {
  isLoggedIn = true;
  users: User[] = [];
  user$ = this.userService.getCurrentUser();
}
```

## 29. What is the `ngFor` directive used for in Angular?

**Answer:**
- Repeats a template for each item in a collection
- Creates a new element for each item
- Provides local variables like `index`, `first`, `last`

**Example:**
```html
<!-- Basic loop -->
<ul>
  <li *ngFor="let user of users">{{user.name}}</li>
</ul>

<!-- With index and tracking -->
<div *ngFor="let item of items; let i = index; trackBy: trackByFn">
  {{i + 1}}. {{item.title}}
</div>

<!-- With additional variables -->
<div *ngFor="let user of users; let first = first; let last = last">
  <span [class.highlight]="first">{{user.name}}</span>
  <hr *ngIf="!last">
</div>
```

```typescript
export class ListComponent {
  users = [
    { id: 1, name: 'John' },
    { id: 2, name: 'Jane' }
  ];

  trackByFn(index: number, item: any) {
    return item.id;
  }
}
```

## 30. What is the `ngClass` directive used for in Angular?

**Answer:**
- Dynamically adds or removes CSS classes
- Accepts string, array, or object expressions
- Great for conditional styling

**Example:**
```html
<!-- String binding -->
<div [ngClass]="currentClass">Content</div>

<!-- Object binding -->
<div [ngClass]="{
  'active': isActive,
  'disabled': !isEnabled,
  'highlight': score > 80
}">Status</div>

<!-- Array binding -->
<div [ngClass]="['btn', 'btn-primary', extraClass]">Button</div>

<!-- Method binding -->
<div [ngClass]="getClasses()">Dynamic</div>
```

```typescript
export class StyleComponent {
  currentClass = 'featured';
  isActive = true;
  isEnabled = false;
  score = 85;
  extraClass = 'large';

  getClasses() {
    return {
      'success': this.score >= 70,
      'warning': this.score >= 50 && this.score < 70,
      'danger': this.score < 50
    };
  }
}
```

## 31. What is a template reference variable in Angular?

**Answer:**
- Variables that reference DOM elements or Angular components
- Created using `#variableName` syntax
- Accessible within the template scope

**Example:**
```html
<!-- Reference to input element -->
<input #userInput type="text" />
<button (click)="processInput(userInput.value)">Submit</button>

<!-- Reference to component -->
<app-child #childComponent></app-child>
<button (click)="childComponent.reset()">Reset Child</button>

<!-- Reference in ngFor -->
<div *ngFor="let item of items">
  <input #itemInput [value]="item.name" />
  <button (click)="updateItem(item.id, itemInput.value)">Update</button>
</div>

<!-- Reference with ngModel -->
<input #phone ngModel placeholder="Phone number" />
<p>{{phone.value}}</p>
<p *ngIf="phone.invalid && phone.touched">Invalid phone number</p>
```

```typescript
export class FormComponent {
  processInput(value: string) {
    console.log('Input value:', value);
  }

  updateItem(id: number, newValue: string) {
    // Update logic here
  }
}
```

# Intermediate Angular Interview Questions & Answers

## 32. What are Angular forms? What are the two types of forms in Angular?

**Angular forms** handle user input and validation in web applications.

**Two types:**
* **Template-driven forms** - Uses directives in template
* **Reactive forms** - Uses FormControl and FormGroup in component

```typescript
// Template-driven
<form #userForm="ngForm">
  <input name="email" ngModel required>
</form>

// Reactive
export class MyComponent {
  userForm = new FormGroup({
    email: new FormControl('', Validators.required)
  });
}
```

---

## 33. What is the purpose of the `ngModel` directive in Angular?

**ngModel** creates two-way data binding between form controls and component properties.

* Binds input values to component data
* Updates UI when data changes
* Provides form validation states

```typescript
export class Component {
  username = '';
}
```

```html
<input [(ngModel)]="username" name="username">
<p>Hello {{username}}</p>
```

---

## 34. What is the `async` pipe in Angular, and how does it work with Observables?

**Async pipe** automatically subscribes to Observables and unsubscribes when component destroys.

* Handles subscription lifecycle
* Updates view when Observable emits
* Prevents memory leaks

```typescript
export class Component {
  users$ = this.http.get<User[]>('/api/users');
}
```

```html
<div *ngFor="let user of users$ | async">
  {{user.name}}
</div>
```

---

## 35. What is lazy loading in Angular? How does it improve application performance?

**Lazy loading** loads feature modules only when needed, not at startup.

**Performance benefits:**
* Reduces initial bundle size
* Faster app startup
* Better user experience

```typescript
// app-routing.module.ts
const routes: Routes = [
  {
    path: 'products',
    loadChildren: () => import('./products/products.module').then(m => m.ProductsModule)
  }
];
```

---

## 36. What is change detection in Angular? How does it work?

**Change detection** checks if component data changed and updates the DOM accordingly.

**How it works:**
* Runs after events (click, HTTP, timers)
* Compares current vs previous values
* Updates DOM if changes found

```typescript
export class Component {
  count = 0;
  
  increment() {
    this.count++; // Triggers change detection
  }
}
```

```html
<button (click)="increment()">Count: {{count}}</button>
```

---

## 37. Explain the concept of zones in Angular.

**Zone.js** patches browser APIs to detect asynchronous operations and trigger change detection.

* Monitors async operations
* Automatically runs change detection
* Handles promises, events, timers

```typescript
// Zone automatically detects this
setTimeout(() => {
  this.message = 'Updated'; // Change detection runs
}, 1000);

// Outside zone - no detection
this.ngZone.runOutsideAngular(() => {
  setTimeout(() => {
    // Manual trigger needed
    this.ngZone.run(() => {
      this.message = 'Updated';
    });
  }, 1000);
});
```

---

## 38. What are `@Input` and `@Output` decorators in Angular?

* **@Input** receives data from parent component
* **@Output** sends data to parent component

```typescript
// Child Component
export class ChildComponent {
  @Input() title: string;
  @Output() clicked = new EventEmitter<string>();
  
  onClick() {
    this.clicked.emit('Button clicked!');
  }
}
```

```html
<!-- Parent Template -->
<app-child 
  [title]="parentTitle" 
  (clicked)="handleClick($event)">
</app-child>
```

```typescript
// Parent Component
export class ParentComponent {
  parentTitle = 'Hello Child';
  
  handleClick(message: string) {
    console.log(message);
  }
}
```

## 39. What is the difference between `localStorage` and `sessionStorage` in Angular?

**Answer:**
Both are browser storage APIs that Angular can use, but they differ in persistence:

* **localStorage** - Data persists until manually cleared
* **sessionStorage** - Data clears when browser tab closes
* Both store key-value pairs as strings
* Same API methods for both

```typescript
// localStorage - persists across sessions
localStorage.setItem('user', JSON.stringify({name: 'John'}));
const user = JSON.parse(localStorage.getItem('user'));

// sessionStorage - cleared on tab close
sessionStorage.setItem('token', 'abc123');
const token = sessionStorage.getItem('token');

// Angular service example
@Injectable()
export class StorageService {
  setUser(user: any) {
    localStorage.setItem('user', JSON.stringify(user));
  }
  
  getUser() {
    return JSON.parse(localStorage.getItem('user') || '{}');
  }
}
```

---

## 40. How does Angular handle cross-site request forgery (CSRF)?

**Answer:**
Angular provides built-in CSRF protection through interceptors:

* Uses **XSRF tokens** automatically
* Reads token from cookies
* Adds token to request headers
* Works with backend CSRF implementations

```typescript
// Automatic CSRF protection (default)
import { HttpClientXsrfModule } from '@angular/common/http';

@NgModule({
  imports: [
    HttpClientXsrfModule.withOptions({
      cookieName: 'XSRF-TOKEN',
      headerName: 'X-XSRF-TOKEN'
    })
  ]
})
export class AppModule {}

// Custom CSRF interceptor
@Injectable()
export class CsrfInterceptor implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler) {
    const token = this.getCSRFToken();
    const csrfReq = req.clone({
      setHeaders: { 'X-CSRF-TOKEN': token }
    });
    return next.handle(csrfReq);
  }
}
```

---

## 41. What are Angular modules and how do they help in organizing an application?

**Answer:**
Modules are containers that group related components, services, and features:

* **Root module** (AppModule) - bootstraps the app
* **Feature modules** - organize specific functionality
* **Shared modules** - contain reusable components
* **Core module** - singleton services

```typescript
// Feature module example
@NgModule({
  declarations: [
    UserListComponent,
    UserDetailComponent
  ],
  imports: [
    CommonModule,
    RouterModule.forChild(routes)
  ],
  providers: [UserService]
})
export class UserModule {}

// Shared module
@NgModule({
  declarations: [LoaderComponent, ConfirmDialogComponent],
  imports: [CommonModule],
  exports: [LoaderComponent, ConfirmDialogComponent]
})
export class SharedModule {}

// Lazy loading with modules
const routes: Routes = [
  {
    path: 'users',
    loadChildren: () => import('./user/user.module').then(m => m.UserModule)
  }
];
```

---

## 42. What is a custom directive, and how do you create one?

**Answer:**
Custom directives extend HTML elements with custom behavior:

* **Attribute directives** - change element appearance/behavior
* **Structural directives** - modify DOM structure
* Use `@Directive` decorator
* Access element through `ElementRef`

```typescript
// Attribute directive - highlight text
@Directive({
  selector: '[appHighlight]'
})
export class HighlightDirective {
  constructor(private el: ElementRef) {}
  
  @Input() appHighlight: string = 'yellow';
  
  @HostListener('mouseenter') onMouseEnter() {
    this.el.nativeElement.style.backgroundColor = this.appHighlight;
  }
  
  @HostListener('mouseleave') onMouseLeave() {
    this.el.nativeElement.style.backgroundColor = '';
  }
}

// Usage in template
<p appHighlight="lightblue">Hover over me!</p>

// Structural directive - custom *ngIf
@Directive({
  selector: '[appUnless]'
})
export class UnlessDirective {
  @Input() set appUnless(condition: boolean) {
    if (!condition) {
      this.vcRef.createEmbeddedView(this.templateRef);
    } else {
      this.vcRef.clear();
    }
  }
  
  constructor(
    private templateRef: TemplateRef<any>,
    private vcRef: ViewContainerRef
  ) {}
}
```

---

## 43. How do you create and use services in Angular?

**Answer:**
Services handle business logic and data sharing between components:

* Use `@Injectable` decorator
* Provide in root or specific modules
* Inject using dependency injection
* Perfect for HTTP calls, data management

```typescript
// Create service
@Injectable({
  providedIn: 'root'
})
export class DataService {
  private apiUrl = 'https://api.example.com';
  
  constructor(private http: HttpClient) {}
  
  getUsers(): Observable<User[]> {
    return this.http.get<User[]>(`${this.apiUrl}/users`);
  }
  
  createUser(user: User): Observable<User> {
    return this.http.post<User>(`${this.apiUrl}/users`, user);
  }
}

// Use in component
@Component({
  selector: 'app-user-list',
  template: `
    <div *ngFor="let user of users">{{user.name}}</div>
  `
})
export class UserListComponent implements OnInit {
  users: User[] = [];
  
  constructor(private dataService: DataService) {}
  
  ngOnInit() {
    this.dataService.getUsers().subscribe(
      users => this.users = users
    );
  }
}
```

---

## 44. What is the role of the `RouterModule` in Angular?

**Answer:**
RouterModule enables navigation and routing in Angular applications:

* **forRoot()** - configures root routing
* **forChild()** - configures feature routing
* Handles URL mapping to components
* Supports guards, resolvers, lazy loading

```typescript
// App routing module
const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'users/:id', component: UserDetailComponent },
  { path: 'admin', loadChildren: () => import('./admin/admin.module').then(m => m.AdminModule) }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}

// Feature routing
const userRoutes: Routes = [
  { path: '', component: UserListComponent },
  { path: 'new', component: UserFormComponent },
  { path: ':id/edit', component: UserFormComponent }
];

@NgModule({
  imports: [RouterModule.forChild(userRoutes)],
  exports: [RouterModule]
})
export class UserRoutingModule {}

// Navigation in component
constructor(private router: Router) {}

navigateToUser(id: number) {
  this.router.navigate(['/users', id]);
}
```

---

## 45. How would you handle HTTP errors in Angular?

**Answer:**
Handle HTTP errors using operators and interceptors:

* Use **catchError** operator with observables
* Create **error interceptors** for global handling
* Implement **retry logic** for failed requests
* Show user-friendly error messages

```typescript
// Service with error handling
@Injectable()
export class ApiService {
  constructor(private http: HttpClient) {}
  
  getUsers(): Observable<User[]> {
    return this.http.get<User[]>('/api/users').pipe(
      retry(2),
      catchError(this.handleError)
    );
  }
  
  private handleError(error: HttpErrorResponse) {
    let errorMessage = 'An error occurred';
    
    if (error.error instanceof ErrorEvent) {
      // Client-side error
      errorMessage = error.error.message;
    } else {
      // Server-side error
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    
    return throwError(() => errorMessage);
  }
}

// Global error interceptor
@Injectable()
export class ErrorInterceptor implements HttpInterceptor {
  constructor(private snackBar: MatSnackBar) {}
  
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(req).pipe(
      catchError((error: HttpErrorResponse) => {
        if (error.status === 401) {
          // Handle unauthorized
          this.router.navigate(['/login']);
        } else if (error.status >= 500) {
          // Handle server errors
          this.snackBar.open('Server error occurred', 'Close');
        }
        return throwError(() => error);
      })
    );
  }
}

// Component usage
export class UserComponent {
  users: User[] = [];
  errorMessage = '';
  
  loadUsers() {
    this.apiService.getUsers().subscribe({
      next: users => this.users = users,
      error: error => this.errorMessage = error
    });
  }
}
```

## 46. How can you optimize the performance of an Angular application?

**Answer:** There are several key strategies to optimize Angular performance:

* **OnPush Change Detection Strategy**
  - Reduces unnecessary change detection cycles
  - Only checks component when inputs change

```typescript
@Component({
  selector: 'app-user',
  changeDetection: ChangeDetectionStrategy.OnPush,
  template: `<div>{{user.name}}</div>`
})
export class UserComponent {
  @Input() user: User;
}
```

* **Lazy Loading Modules**
  - Load feature modules only when needed

```typescript
const routes: Routes = [
  {
    path: 'admin',
    loadChildren: () => import('./admin/admin.module').then(m => m.AdminModule)
  }
];
```

* **TrackBy Functions**
  - Optimize *ngFor performance

```typescript
trackByUserId(index: number, user: User): number {
  return user.id;
}
```

```html
<div *ngFor="let user of users; trackBy: trackByUserId">
  {{user.name}}
</div>
```

* **Virtual Scrolling**
  - Handle large lists efficiently

```html
<cdk-virtual-scroll-viewport itemSize="50" class="viewport">
  <div *cdkVirtualFor="let item of items">{{item}}</div>
</cdk-virtual-scroll-viewport>
```

---

## 47. What is the role of the Angular CLI in the development process?

**Answer:** Angular CLI is the command-line interface that streamlines Angular development:

* **Project Setup**
  - Creates new projects with best practices

```bash
ng new my-app
ng generate component user-list
ng generate service user
```

* **Development Server**
  - Hot reload and live development

```bash
ng serve
ng serve --port 4200 --open
```

* **Build & Deployment**
  - Optimized production builds

```bash
ng build --prod
ng build --configuration=production
```

* **Testing**
  - Run unit and e2e tests

```bash
ng test
ng e2e
```

* **Code Generation**
  - Scaffolds components, services, modules

```bash
ng generate module feature --routing
ng generate guard auth
```

---

## 48. How can you handle routing with route guards in Angular?

**Answer:** Route guards control navigation and protect routes:

* **CanActivate Guard**
  - Controls if route can be activated


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

## 49. What are interceptors in Angular? How would you use them for adding headers or logging API requests?

**Answer:** Interceptors intercept HTTP requests and responses globally:

* **Auth Header Interceptor**

```typescript
@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = localStorage.getItem('token');
    
    if (token) {
      const authReq = req.clone({
        headers: req.headers.set('Authorization', `Bearer ${token}`)
      });
      return next.handle(authReq);
    }
    
    return next.handle(req);
  }
}
```

* **Logging Interceptor**

```typescript
@Injectable()
export class LoggingInterceptor implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    console.log('Request:', req.url, req.method);
    
    return next.handle(req).pipe(
      tap(event => {
        if (event instanceof HttpResponse) {
          console.log('Response:', event.status, event.body);
        }
      })
    );
  }
}
```

* **Provider Registration**

```typescript
@NgModule({
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    }
  ]
})
export class AppModule {}
```

---

## 50. What is the purpose of the `ng-content` and `ng-template` directive in Angular?

**Answer:** Both enable content projection and dynamic templates:

* **ng-content - Content Projection**
  - Projects content from parent to child component

```typescript
// Modal Component
@Component({
  selector: 'app-modal',
  template: `
    <div class="modal">
      <div class="header">
        <ng-content select="[slot=header]"></ng-content>
      </div>
      <div class="body">
        <ng-content></ng-content>
      </div>
    </div>
  `
})
export class ModalComponent {}
```

```html
<!-- Usage -->
<app-modal>
  <h2 slot="header">User Details</h2>
  <p>User information goes here</p>
</app-modal>
```

* **ng-template - Template Reference**
  - Defines reusable template blocks

```html
<div *ngIf="showUsers; else noUsers">
  <div *ngFor="let user of users">{{user.name}}</div>
</div>

<ng-template #noUsers>
  <p>No users found</p>
</ng-template>
```

* **Dynamic Template Usage**

```typescript
@Component({
  template: `
    <ng-container *ngTemplateOutlet="selectedTemplate"></ng-container>
    <ng-template #userTemplate>User View</ng-template>
    <ng-template #adminTemplate>Admin View</ng-template>
  `
})
export class DynamicComponent {
  @ViewChild('userTemplate') userTemplate: TemplateRef<any>;
  selectedTemplate: TemplateRef<any>;
}
```

---

## 51. What is a resolver in Angular, and when would you use one?

**Answer:** Resolvers pre-fetch data before route activation:

* **User Resolver**

```typescript
@Injectable()
export class UserResolver implements Resolve<User> {
  constructor(private userService: UserService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<User> {
    const userId = route.params['id'];
    
    return this.userService.getUser(userId).pipe(
      catchError(() => {
        this.router.navigate(['/users']);
        return EMPTY;
      })
    );
  }
}
```

* **Route Configuration**

```typescript
const routes: Routes = [
  {
    path: 'user/:id',
    component: UserDetailComponent,
    resolve: { user: UserResolver }
  }
];
```

* **Component Usage**

```typescript
@Component({
  template: `<div>{{user.name}}</div>`
})
export class UserDetailComponent implements OnInit {
  user: User;

  constructor(private route: ActivatedRoute) {}

  ngOnInit() {
    this.user = this.route.snapshot.data['user'];
  }
}
```

**When to use:**
- Pre-load critical data
- Prevent empty component states
- Handle data loading errors before navigation

---

## 52. How does Angular support internationalization (i18n)?

**Answer:** Angular provides built-in i18n support for multi-language applications:

* **Mark Text for Translation**

```html
<p i18n="@@welcome-message">Welcome to our app!</p>
<button i18n="button.save|Save button">Save</button>
```

* **Extract Messages**

```bash
ng extract-i18n
```

* **Translation Files**
  - Creates `messages.xlf` file

```xml
<trans-unit id="welcome-message">
  <source>Welcome to our app!</source>
  <target>¡Bienvenido a nuestra aplicación!</target>
</trans-unit>
```

* **Build Configuration**

```json
{
  "build": {
    "configurations": {
      "es": {
        "aot": true,
        "outputPath": "dist/es/",
        "i18nFile": "src/locale/messages.es.xlf",
        "i18nFormat": "xlf",
        "i18nLocale": "es"
      }
    }
  }
}
```

* **Build for Different Locales**

```bash
ng build --configuration=es
```

* **Pluralization**

```html
<span i18n>{count, plural, =0 {no items} =1 {one item} other {{{count}} items}}</span>
```

* **Date/Number Pipes with Locale**

```html
<p>{{today | date:'short'}}</p>
<p>{{price | currency}}</p>
```

# Advanced Angular Interview Questions & Answers

## 53. What is a singleton service in Angular?

A singleton service is a service that has only one instance throughout the entire application lifecycle.

**Key Points:**
- Angular services are singleton by default when provided in root
- Same instance is shared across all components
- Maintains state across the application

```typescript
@Injectable({
  providedIn: 'root'  // Makes it singleton
})
export class DataService {
  private data: string[] = [];
  
  addData(item: string) {
    this.data.push(item);
  }
  
  getData() {
    return this.data;
  }
}
```

---

## 54. Explain the difference between `ngOnInit` and `constructor` in Angular components.

**Constructor:**
- Runs first, before Angular initializes the component
- Used for dependency injection
- Input properties are not available yet

**ngOnInit:**
- Runs after Angular initializes the component
- Input properties are available
- Best place for initialization logic

```typescript
export class MyComponent implements OnInit {
  @Input() name: string;
  
  constructor(private service: DataService) {
    // Dependency injection only
    console.log(this.name); // undefined
  }
  
  ngOnInit() {
    // Initialization logic here
    console.log(this.name); // Available now
    this.loadData();
  }
  
  loadData() {
    this.service.getData();
  }
}
```

---

## 55. What is the `RxJS` library, and how is it used in Angular?

RxJS is a reactive programming library for handling asynchronous operations using Observables.

**Key Uses in Angular:**
- HTTP requests
- Event handling
- Form validation
- State management

```typescript
import { Observable } from 'rxjs';

@Injectable()
export class ApiService {
  constructor(private http: HttpClient) {}
  
  getUsers(): Observable<User[]> {
    return this.http.get<User[]>('/api/users');
  }
}

// Component usage
export class UserComponent {
  users$ = this.apiService.getUsers();
  
  constructor(private apiService: ApiService) {}
}
```

---

## 56. Explain operators like `map`, `filter`, `merge`, and `switchMap`

**map:** Transforms each emitted value
```typescript
this.http.get<User>('/api/user/1')
  .pipe(
    map(user => user.name.toUpperCase())
  )
```

**filter:** Filters values based on condition
```typescript
this.searchInput.valueChanges
  .pipe(
    filter(text => text.length > 2)
  )
```

**merge:** Combines multiple observables
```typescript
const obs1$ = of(1, 2, 3);
const obs2$ = of(4, 5, 6);
merge(obs1$, obs2$); // Emits: 1,2,3,4,5,6
```

**switchMap:** Switches to new observable, canceling previous
```typescript
this.searchInput.valueChanges
  .pipe(
    switchMap(query => this.apiService.search(query))
  )
```

---

## 57. How do you handle error handling in RxJS streams?

**Using catchError operator:**
```typescript
import { catchError } from 'rxjs/operators';
import { of } from 'rxjs';

this.http.get('/api/data')
  .pipe(
    catchError(error => {
      console.error('Error occurred:', error);
      return of([]); // Return fallback value
    })
  )
```

**Global error handling:**
```typescript
@Injectable()
export class ErrorInterceptor implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler) {
    return next.handle(req).pipe(
      catchError(error => {
        // Handle error globally
        this.notificationService.showError(error.message);
        return throwError(error);
      })
    );
  }
}
```

---

## 58. What is the difference between `switchMap`, `concatMap`, and `mergeMap` in RxJS?

**switchMap:** Cancels previous, switches to new
- Use for: Search, latest request only
```typescript
searchInput.pipe(
  switchMap(query => this.search(query))
)
```

**concatMap:** Waits for previous to complete
- Use for: Sequential operations
```typescript
requests.pipe(
  concatMap(req => this.processRequest(req))
)
```

**mergeMap:** Runs all concurrently
- Use for: Independent parallel operations
```typescript
userIds.pipe(
  mergeMap(id => this.getUserDetails(id))
)
```

---

## 59. How to handle parallel service calls in Angular?

**Using forkJoin for parallel calls:**
```typescript
import { forkJoin } from 'rxjs';

loadData() {
  const users$ = this.apiService.getUsers();
  const posts$ = this.apiService.getPosts();
  const comments$ = this.apiService.getComments();
  
  forkJoin({
    users: users$,
    posts: posts$,
    comments: comments$
  }).subscribe(result => {
    this.users = result.users;
    this.posts = result.posts;
    this.comments = result.comments;
  });
}
```

**Using combineLatest:**
```typescript
import { combineLatest } from 'rxjs';

combineLatest([
  this.service1.getData(),
  this.service2.getData()
]).subscribe(([data1, data2]) => {
  // Both data available
});
```

---

## 60. How does Angular handle state management, and what libraries can be used for state management in Angular applications?

**Built-in State Management:**
- Services with BehaviorSubject
- Simple and effective for small apps

```typescript
@Injectable({
  providedIn: 'root'
})
export class StateService {
  private userSubject = new BehaviorSubject<User | null>(null);
  user$ = this.userSubject.asObservable();
  
  setUser(user: User) {
    this.userSubject.next(user);
  }
}
```

**Popular State Management Libraries:**

**1. NgRx (Redux pattern):**
```typescript
// Action
export const loadUsers = createAction('[User] Load Users');

// Reducer
const userReducer = createReducer(
  initialState,
  on(loadUsers, state => ({ ...state, loading: true }))
);

// Effect
@Injectable()
export class UserEffects {
  loadUsers$ = createEffect(() =>
    this.actions$.pipe(
      ofType(loadUsers),
      switchMap(() => this.apiService.getUsers())
    )
  );
}
```

**2. Akita:**
- Simpler than NgRx
- Built-in dev tools

**3. NGXS:**
- Decorator-based
- Less boilerplate than NgRx

**When to use:**
- Simple apps: Services + BehaviorSubject
- Complex apps: NgRx/NGXS/Akita
- Medium apps: Akita or simple state services


## 61. What is Ahead-of-Time (AOT) compilation, and how does it differ from Just-in-Time (JIT) compilation?

**AOT vs JIT - Simple breakdown:**

* **AOT (Ahead-of-Time)**: Templates and components are compiled during the build process
* **JIT (Just-in-Time)**: Templates are compiled in the browser at runtime

**Key differences:**
* **Performance**: AOT is faster - no compilation overhead in browser
* **Bundle size**: AOT produces smaller bundles - unused code is tree-shaken
* **Error detection**: AOT catches template errors at build time
* **Security**: AOT is more secure - templates are pre-compiled

```typescript
// AOT compilation (default in Angular 9+)
ng build --aot

// JIT compilation (legacy)
ng build --jit
```

---

## 62. What are Angular Universal applications, and how does server-side rendering work in Angular?

**Angular Universal enables Server-Side Rendering (SSR):**

* **Purpose**: Renders Angular apps on the server before sending to browser
* **Benefits**: Better SEO, faster initial page load, improved performance on slow devices

**How SSR works:**
* Server renders the initial HTML
* Browser receives pre-rendered content
* Angular app hydrates and takes over

```bash
# Install Angular Universal
ng add @nguniversal/express-engine

# Build and serve SSR app
npm run build:ssr
npm run serve:ssr
```

```typescript
// app.server.module.ts
import { NgModule } from '@angular/core';
import { ServerModule } from '@angular/platform-server';
import { AppModule } from './app.module';
import { AppComponent } from './app.component';

@NgModule({
  imports: [AppModule, ServerModule],
  bootstrap: [AppComponent]
})
export class AppServerModule {}
```

---

## 63. What are Angular decorators, and what role do they play in Angular development?

**Decorators are metadata functions that configure classes:**

* **@Component**: Defines a component with template and styles
* **@Injectable**: Marks a class as available for dependency injection
* **@Directive**: Creates custom directives
* **@Pipe**: Creates custom pipes
* **@Input/@Output**: Property and event binding

```typescript
// Component decorator
@Component({
  selector: 'app-user',
  template: '<h1>{{name}}</h1>',
  styleUrls: ['./user.component.css']
})
export class UserComponent {
  @Input() name: string;
  @Output() userClick = new EventEmitter();
}

// Service decorator
@Injectable({
  providedIn: 'root'
})
export class UserService {
  getUsers() { return []; }
}

// Pipe decorator
@Pipe({ name: 'capitalize' })
export class CapitalizePipe implements PipeTransform {
  transform(value: string): string {
    return value.charAt(0).toUpperCase() + value.slice(1);
  }
}
```

---

## 64. How do you configure Angular to work with environment-specific settings?

**Environment files handle different configurations:**

* **Development**: `environment.ts`
* **Production**: `environment.prod.ts`
* **Custom environments**: `environment.staging.ts`

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
```

```typescript
// Using environment in service
import { environment } from '../environments/environment';

@Injectable()
export class ApiService {
  private apiUrl = environment.apiUrl;
  
  getUsers() {
    return this.http.get(`${this.apiUrl}/users`);
  }
}
```

```json
// angular.json - configure builds
"configurations": {
  "production": {
    "fileReplacements": [{
      "replace": "src/environments/environment.ts",
      "with": "src/environments/environment.prod.ts"
    }]
  }
}
```

---

## 65. What are the advantages and disadvantages of using Angular for web development?

**Advantages:**
* **Full framework**: Complete solution with routing, forms, HTTP client
* **TypeScript**: Strong typing and better tooling
* **Dependency injection**: Clean, testable code architecture
* **CLI**: Powerful development tools and scaffolding
* **Enterprise-ready**: Mature ecosystem and long-term support

**Disadvantages:**
* **Learning curve**: Complex for beginners
* **Bundle size**: Can be large for simple apps
* **Frequent updates**: Major versions every 6 months
* **Opinionated**: Less flexibility compared to libraries like React

```typescript
// Example of Angular's opinionated structure
@Component({
  selector: 'app-example',
  template: `<div>{{message}}</div>`
})
export class ExampleComponent implements OnInit {
  message = 'Hello Angular';
  
  constructor(private service: DataService) {}
  
  ngOnInit() {
    this.loadData();
  }
  
  private loadData() {
    this.service.getData().subscribe(data => {
      this.message = data.message;
    });
  }
}
```

---

## 66. How to implement AuthGuard in Angular?

**AuthGuard protects routes from unauthorized access:**

```typescript
// auth.guard.ts
import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  
  constructor(
    private authService: AuthService,
    private router: Router
  ) {}
  
  canActivate(): boolean {
    if (this.authService.isLoggedIn()) {
      return true;
    }
    
    this.router.navigate(['/login']);
    return false;
  }
}
```

```typescript
// auth.service.ts
@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private loggedIn = false;
  
  login(username: string, password: string): boolean {
    // Simulate login logic
    if (username === 'admin' && password === 'password') {
      this.loggedIn = true;
      localStorage.setItem('token', 'fake-jwt-token');
      return true;
    }
    return false;
  }
  
  isLoggedIn(): boolean {
    return this.loggedIn || !!localStorage.getItem('token');
  }
  
  logout() {
    this.loggedIn = false;
    localStorage.removeItem('token');
  }
}
```

```typescript
// app-routing.module.ts
const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { 
    path: 'dashboard', 
    component: DashboardComponent,
    canActivate: [AuthGuard]
  },
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' }
];
```

---

## 67. What is the difference between Authentication and Authorization?

**Authentication vs Authorization - Clear distinction:**

* **Authentication**: "Who are you?" - Verifying user identity
* **Authorization**: "What can you do?" - Checking user permissions

```typescript
// Authentication example
@Injectable()
export class AuthenticationService {
  authenticate(username: string, password: string): Observable<User> {
    return this.http.post<User>('/api/login', { username, password })
      .pipe(
        tap(user => {
          localStorage.setItem('currentUser', JSON.stringify(user));
        })
      );
  }
  
  isAuthenticated(): boolean {
    return !!localStorage.getItem('currentUser');
  }
}

// Authorization example
@Injectable()
export class AuthorizationService {
  hasPermission(permission: string): boolean {
    const user = JSON.parse(localStorage.getItem('currentUser') || '{}');
    return user.permissions?.includes(permission) || false;
  }
  
  hasRole(role: string): boolean {
    const user = JSON.parse(localStorage.getItem('currentUser') || '{}');
    return user.roles?.includes(role) || false;
  }
}
```

```typescript
// Role-based guard
@Injectable()
export class RoleGuard implements CanActivate {
  constructor(
    private authService: AuthenticationService,
    private authzService: AuthorizationService,
    private router: Router
  ) {}
  
  canActivate(route: ActivatedRouteSnapshot): boolean {
    // First check authentication
    if (!this.authService.isAuthenticated()) {
      this.router.navigate(['/login']);
      return false;
    }
    
    // Then check authorization
    const requiredRole = route.data['role'];
    if (requiredRole && !this.authzService.hasRole(requiredRole)) {
      this.router.navigate(['/unauthorized']);
      return false;
    }
    
    return true;
  }
}
```

---

## 68. How would you troubleshoot performance issues in Angular? What tools and techniques would you use?

**Performance troubleshooting toolkit:**

**Tools:**
* **Angular DevTools**: Component profiling and change detection
* **Chrome DevTools**: Performance tab, memory profiling
* **Lighthouse**: Overall performance audit
* **Bundle Analyzer**: Analyze bundle size

**Techniques:**

```typescript
// 1. OnPush Change Detection Strategy
@Component({
  selector: 'app-optimized',
  changeDetection: ChangeDetectionStrategy.OnPush,
  template: `<div>{{data.name}}</div>`
})
export class OptimizedComponent {
  @Input() data: any;
}

// 2. TrackBy functions for *ngFor
@Component({
  template: `
    <div *ngFor="let item of items; trackBy: trackByFn">
      {{item.name}}
    </div>
  `
})
export class ListComponent {
  trackByFn(index: number, item: any): any {
    return item.id; // Use unique identifier
  }
}

// 3. Lazy loading modules
const routes: Routes = [
  {
    path: 'feature',
    loadChildren: () => import('./feature/feature.module').then(m => m.FeatureModule)
  }
];

// 4. Async pipe instead of manual subscriptions
@Component({
  template: `<div>{{data$ | async}}</div>`
})
export class AsyncComponent {
  data$ = this.service.getData();
  
  constructor(private service: DataService) {}
}

// 5. Unsubscribe to prevent memory leaks
export class ComponentWithSubscription implements OnDestroy {
  private destroy$ = new Subject<void>();
  
  ngOnInit() {
    this.service.getData()
      .pipe(takeUntil(this.destroy$))
      .subscribe(data => {
        // Handle data
      });
  }
  
  ngOnDestroy() {
    this.destroy$.next();
    this.destroy$.complete();
  }
}
```

**Performance checklist:**
* Use OnPush change detection
* Implement trackBy for large lists
* Lazy load feature modules
* Optimize bundle size with tree shaking
* Use async pipe for observables
* Properly unsubscribe from observables
* Minimize DOM manipulations
* Use virtual scrolling for large datasets


# Angular Testing Interview Questions & Answers

## 69. How do you approach unit testing in Angular? What tools and libraries do you use for testing?

**Answer:**
- I follow the AAA pattern: Arrange, Act, Assert
- Focus on testing component logic, not implementation details
- Test user interactions and component outputs

**Tools I use:**
- **Jasmine** - Testing framework
- **Karma** - Test runner
- **Angular Testing Utilities** - TestBed, ComponentFixture
- **Jest** - Alternative to Karma (faster)

```typescript
describe('UserComponent', () => {
  let component: UserComponent;
  let fixture: ComponentFixture<UserComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserComponent]
    });
    fixture = TestBed.createComponent(UserComponent);
    component = fixture.componentInstance;
  });

  it('should display user name', () => {
    // Arrange
    component.user = { name: 'John', age: 30 };
    
    // Act
    fixture.detectChanges();
    
    // Assert
    expect(fixture.nativeElement.textContent).toContain('John');
  });
});
```

---

## 70. What is the purpose of `TestBed` in Angular testing, and how do you use it to configure tests?

**Answer:**
- TestBed creates a testing module that mimics Angular's @NgModule
- It configures the testing environment for components and services
- Provides dependency injection for tests

**Key methods:**
- `configureTestingModule()` - Set up the testing module
- `createComponent()` - Create component instances
- `inject()` - Get service instances

```typescript
beforeEach(() => {
  TestBed.configureTestingModule({
    declarations: [MyComponent],
    imports: [FormsModule, HttpClientTestingModule],
    providers: [
      { provide: UserService, useClass: MockUserService },
      { provide: API_URL, useValue: 'http://test-api.com' }
    ]
  });
});

// Create component
fixture = TestBed.createComponent(MyComponent);
component = fixture.componentInstance;

// Inject service
userService = TestBed.inject(UserService);
```

---

## 71. How do you mock services and HTTP requests during unit tests in Angular?

**Answer:**
- Use `HttpClientTestingModule` for HTTP mocking
- Create mock services with jasmine spies
- Use `HttpTestingController` to intercept requests

**Service Mocking:**
```typescript
// Mock service
const mockUserService = {
  getUsers: jasmine.createSpy('getUsers').and.returnValue(of([{ id: 1, name: 'John' }]))
};

TestBed.configureTestingModule({
  providers: [{ provide: UserService, useValue: mockUserService }]
});
```

**HTTP Mocking:**
```typescript
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

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
    expect(req.request.method).toBe('GET');
    req.flush(mockUsers);
  });
});
```

---

## 72. Explain how to test an Angular component that has dependencies like services, observables, or other components.

**Answer:**
- Mock all external dependencies
- Use TestBed to provide mock services
- Test component behavior, not dependency implementation
- Handle async operations with `fakeAsync` and `tick`

```typescript
describe('UserListComponent', () => {
  let component: UserListComponent;
  let fixture: ComponentFixture<UserListComponent>;
  let mockUserService: jasmine.SpyObj<UserService>;

  beforeEach(() => {
    const spy = jasmine.createSpyObj('UserService', ['getUsers', 'deleteUser']);

    TestBed.configureTestingModule({
      declarations: [UserListComponent, UserCardComponent],
      providers: [{ provide: UserService, useValue: spy }]
    });

    fixture = TestBed.createComponent(UserListComponent);
    component = fixture.componentInstance;
    mockUserService = TestBed.inject(UserService) as jasmine.SpyObj<UserService>;
  });

  it('should load users on init', fakeAsync(() => {
    const mockUsers = [{ id: 1, name: 'John' }, { id: 2, name: 'Jane' }];
    mockUserService.getUsers.and.returnValue(of(mockUsers));

    component.ngOnInit();
    tick();

    expect(component.users).toEqual(mockUsers);
    expect(mockUserService.getUsers).toHaveBeenCalled();
  }));

  it('should delete user', () => {
    mockUserService.deleteUser.and.returnValue(of({}));
    
    component.deleteUser(1);
    
    expect(mockUserService.deleteUser).toHaveBeenCalledWith(1);
  });
});
```

---

## 73. Can you explain the concept of a "spy" in Jasmine? How would you use it in unit testing?

**Answer:**
- Spies are test doubles that track function calls
- They can replace method implementations
- Useful for verifying method calls and controlling return values

**Types of spies:**
- `jasmine.createSpy()` - Standalone spy
- `spyOn()` - Spy on existing methods
- `jasmine.createSpyObj()` - Object with multiple spies

```typescript
describe('Spy Examples', () => {
  let userService: UserService;

  beforeEach(() => {
    userService = new UserService();
  });

  it('should spy on method calls', () => {
    // Spy on existing method
    spyOn(userService, 'getUsers').and.returnValue(of([]));
    
    userService.getUsers();
    
    expect(userService.getUsers).toHaveBeenCalled();
    expect(userService.getUsers).toHaveBeenCalledTimes(1);
  });

  it('should create spy object', () => {
    // Create spy object
    const mockService = jasmine.createSpyObj('UserService', {
      getUsers: of([{ id: 1, name: 'John' }]),
      deleteUser: of({})
    });

    expect(mockService.getUsers()).toBeDefined();
  });

  it('should track call arguments', () => {
    spyOn(userService, 'getUserById');
    
    userService.getUserById(123);
    
    expect(userService.getUserById).toHaveBeenCalledWith(123);
  });

  it('should control return values', () => {
    const spy = jasmine.createSpy('calculate')
      .and.returnValues(10, 20, 30);
    
    expect(spy()).toBe(10);
    expect(spy()).toBe(20);
    expect(spy()).toBe(30);
  });
});
```

**Common spy methods:**
- `.and.returnValue()` - Return specific value
- `.and.callThrough()` - Call original method
- `.and.throwError()` - Throw error
- `.and.callFake()` - Custom implementation


# Angular Architecture & Design Patterns - Interview Answers

## 74. How would you organize a large Angular application?

**Answer:**
* Use **feature modules** to break down functionality
* Follow **lazy loading** for better performance
* Implement **shared modules** for common components
* Use **core module** for singleton services
* Apply **barrel exports** for clean imports

```typescript
// Feature module structure
src/
├── app/
│   ├── core/           // Singleton services
│   ├── shared/         // Common components
│   ├── features/
│   │   ├── user/
│   │   │   ├── user.module.ts
│   │   │   ├── components/
│   │   │   └── services/
│   │   └── product/
│   └── app-routing.module.ts

// Lazy loading example
const routes: Routes = [
  {
    path: 'users',
    loadChildren: () => import('./features/user/user.module').then(m => m.UserModule)
  }
];
```

---

## 75. What is state management in Angular?

**Answer:**
* **Services with BehaviorSubject** for simple state
* **NgRx** for complex applications
* **Akita** as alternative state management
* **RxJS operators** for reactive state handling

```typescript
// Simple state service
@Injectable()
export class UserStateService {
  private userSubject = new BehaviorSubject<User[]>([]);
  users$ = this.userSubject.asObservable();

  addUser(user: User) {
    const current = this.userSubject.value;
    this.userSubject.next([...current, user]);
  }
}

// NgRx example
@Injectable()
export class UserEffects {
  loadUsers$ = createEffect(() =>
    this.actions$.pipe(
      ofType(loadUsers),
      switchMap(() =>
        this.userService.getUsers().pipe(
          map(users => loadUsersSuccess({ users }))
        )
      )
    )
  );
}
```

---

## 76. How to debug Angular application?

**Answer:**
* Use **Angular DevTools** browser extension
* **Console.log** and **debugger** statements
* **Angular CLI** with source maps
* **Network tab** for HTTP requests
* **Performance profiler** for optimization

```typescript
// Debugging techniques
export class UserComponent {
  ngOnInit() {
    console.log('Component initialized');
    debugger; // Browser will pause here
    
    this.userService.getUsers().pipe(
      tap(users => console.log('Users loaded:', users)),
      catchError(error => {
        console.error('Error loading users:', error);
        return of([]);
      })
    ).subscribe();
  }
}

// Angular DevTools usage
// 1. Install Angular DevTools extension
// 2. Open browser dev tools
// 3. Navigate to Angular tab
// 4. Inspect component tree and profiler
```

---

## 77. What is the Singleton pattern and how does it relate to Angular services?

**Answer:**
* **Singleton ensures single instance** across application
* Angular services are **singletons by default** when provided in root
* **Shared state** and **memory efficiency**
* Use `providedIn: 'root'` for singleton services

```typescript
// Singleton service
@Injectable({
  providedIn: 'root' // Creates singleton instance
})
export class DataService {
  private data: any[] = [];
  
  getData() {
    return this.data;
  }
  
  addData(item: any) {
    this.data.push(item);
  }
}

// Multiple instances (non-singleton)
@Component({
  providers: [DataService] // New instance per component
})
export class MyComponent {
  constructor(private dataService: DataService) {}
}
```

---

## 78. How do you handle component communication in Angular?

**Answer:**
* **@Input/@Output** for parent-child communication
* **Services with observables** for sibling components
* **ViewChild/ViewChildren** for direct access
* **Event emitters** for custom events

```typescript
// Parent-Child communication
@Component({
  template: `<child [data]="parentData" (notify)="onChildEvent($event)"></child>`
})
export class ParentComponent {
  parentData = 'Hello Child';
  
  onChildEvent(data: string) {
    console.log('Child says:', data);
  }
}

@Component({
  selector: 'child'
})
export class ChildComponent {
  @Input() data: string;
  @Output() notify = new EventEmitter<string>();
  
  sendToParent() {
    this.notify.emit('Hello Parent');
  }
}

// Service communication
@Injectable()
export class CommunicationService {
  private messageSource = new Subject<string>();
  message$ = this.messageSource.asObservable();
  
  sendMessage(message: string) {
    this.messageSource.next(message);
  }
}

// Sibling components
export class Component1 {
  constructor(private comm: CommunicationService) {}
  
  sendMessage() {
    this.comm.sendMessage('Hello from Component1');
  }
}

export class Component2 {
  constructor(private comm: CommunicationService) {
    this.comm.message$.subscribe(msg => console.log(msg));
  }
}
```

---

## Key Takeaways

* **Modular architecture** improves maintainability
* **State management** keeps data consistent
* **Debugging tools** speed up development
* **Singleton pattern** ensures single service instances
* **Multiple communication patterns** for different scenarios


# Angular Backend Integration Interview Answers

## 79. How would you handle authentication and authorization in an Angular application? Can you explain JWT (JSON Web Tokens) and how it works with Angular?

**Answer:**
Authentication and authorization in Angular involves managing user credentials and access control throughout the application.

### Key Points:
* **Authentication** - Verifying user identity (login)
* **Authorization** - Controlling access to resources based on user roles
* **JWT** - Stateless token containing user info and claims
* **Guards** - Protect routes based on authentication status

### JWT Structure:
* Header: Algorithm and token type
* Payload: User data and claims (roles, permissions)
* Signature: Verification hash

### Implementation Example:

```typescript
// auth.service.ts
@Injectable()
export class AuthService {
  private tokenKey = 'auth-token';

  login(credentials: LoginData): Observable<AuthResponse> {
    return this.http.post<AuthResponse>('/api/login', credentials)
      .pipe(
        tap(response => {
          localStorage.setItem(this.tokenKey, response.token);
        })
      );
  }

  getToken(): string | null {
    return localStorage.getItem(this.tokenKey);
  }

  isAuthenticated(): boolean {
    const token = this.getToken();
    return token ? !this.isTokenExpired(token) : false;
  }

  logout(): void {
    localStorage.removeItem(this.tokenKey);
  }
}

// auth.guard.ts
@Injectable()
export class AuthGuard implements CanActivate {
  constructor(private auth: AuthService, private router: Router) {}

  canActivate(): boolean {
    if (this.auth.isAuthenticated()) {
      return true;
    }
    this.router.navigate(['/login']);
    return false;
  }
}

// auth.interceptor.ts
@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private auth: AuthService) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = this.auth.getToken();
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

## 80. How would you handle API calls in Angular? Explain the use of `HttpClient` and how you would manage request/response handling and error handling.

**Answer:**
HttpClient is Angular's built-in service for making HTTP requests. It provides a powerful, flexible way to communicate with backend APIs.

### Key Features:
* **Type Safety** - Generic methods with TypeScript interfaces
* **Interceptors** - Middleware for requests/responses
* **Observables** - Reactive programming with RxJS
* **Error Handling** - Centralized error management

### Implementation Example:

```typescript
// user.service.ts
@Injectable()
export class UserService {
  private apiUrl = 'https://api.example.com/users';

  constructor(private http: HttpClient) {}

  getUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.apiUrl)
      .pipe(
        retry(2),
        catchError(this.handleError)
      );
  }

  createUser(user: User): Observable<User> {
    return this.http.post<User>(this.apiUrl, user)
      .pipe(
        catchError(this.handleError)
      );
  }

  updateUser(id: number, user: User): Observable<User> {
    return this.http.put<User>(`${this.apiUrl}/${id}`, user)
      .pipe(
        catchError(this.handleError)
      );
  }

  private handleError(error: HttpErrorResponse): Observable<never> {
    let errorMessage = 'An error occurred';
    
    if (error.error instanceof ErrorEvent) {
      // Client-side error
      errorMessage = error.error.message;
    } else {
      // Server-side error
      switch (error.status) {
        case 400:
          errorMessage = 'Bad Request';
          break;
        case 401:
          errorMessage = 'Unauthorized';
          break;
        case 500:
          errorMessage = 'Server Error';
          break;
        default:
          errorMessage = `Error Code: ${error.status}`;
      }
    }
    
    return throwError(() => errorMessage);
  }
}

// component usage
export class UserComponent implements OnInit {
  users: User[] = [];
  loading = false;
  error: string | null = null;

  constructor(private userService: UserService) {}

  ngOnInit(): void {
    this.loadUsers();
  }

  loadUsers(): void {
    this.loading = true;
    this.userService.getUsers().subscribe({
      next: (users) => {
        this.users = users;
        this.loading = false;
      },
      error: (error) => {
        this.error = error;
        this.loading = false;
      }
    });
  }
}

// global error interceptor
@Injectable()
export class ErrorInterceptor implements HttpInterceptor {
  constructor(private toastr: ToastrService) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(req).pipe(
      catchError((error: HttpErrorResponse) => {
        if (error.status === 401) {
          // Handle unauthorized
          this.toastr.error('Session expired');
        }
        return throwError(() => error);
      })
    );
  }
}
```

---

## 81. How would you handle file uploads in Angular? What are the best practices for handling large files?

**Answer:**
File uploads in Angular can be handled using FormData with HttpClient. For large files, implement progress tracking, chunking, and proper error handling.

### Key Considerations:
* **FormData** - For multipart file uploads
* **Progress Tracking** - Monitor upload progress
* **File Validation** - Size, type, and format checks
* **Chunking** - Split large files into smaller pieces
* **Error Handling** - Network failures and timeouts

### Implementation Example:

```typescript
// file-upload.service.ts
@Injectable()
export class FileUploadService {
  private uploadUrl = 'https://api.example.com/upload';

  constructor(private http: HttpClient) {}

  uploadFile(file: File): Observable<HttpEvent<any>> {
    const formData = new FormData();
    formData.append('file', file);

    const req = new HttpRequest('POST', this.uploadUrl, formData, {
      reportProgress: true
    });

    return this.http.request(req);
  }

  uploadMultipleFiles(files: File[]): Observable<any> {
    const formData = new FormData();
    files.forEach(file => {
      formData.append('files', file);
    });

    return this.http.post(this.uploadUrl, formData, {
      reportProgress: true,
      observe: 'events'
    });
  }

  // For large files - chunked upload
  uploadLargeFile(file: File, chunkSize = 1024 * 1024): Observable<any> {
    const chunks = Math.ceil(file.size / chunkSize);
    const uploadPromises: Observable<any>[] = [];

    for (let i = 0; i < chunks; i++) {
      const start = i * chunkSize;
      const end = Math.min(start + chunkSize, file.size);
      const chunk = file.slice(start, end);

      const formData = new FormData();
      formData.append('chunk', chunk);
      formData.append('chunkIndex', i.toString());
      formData.append('totalChunks', chunks.toString());
      formData.append('fileName', file.name);

      uploadPromises.push(
        this.http.post(`${this.uploadUrl}/chunk`, formData)
      );
    }

    return forkJoin(uploadPromises);
  }
}

// file-upload.component.ts
export class FileUploadComponent {
  selectedFiles: File[] = [];
  uploadProgress = 0;
  uploading = false;
  maxFileSize = 10 * 1024 * 1024; // 10MB
  allowedTypes = ['image/jpeg', 'image/png', 'application/pdf'];

  constructor(private uploadService: FileUploadService) {}

  onFileSelect(event: any): void {
    const files = event.target.files;
    this.selectedFiles = [];

    for (let file of files) {
      if (this.validateFile(file)) {
        this.selectedFiles.push(file);
      }
    }
  }

  validateFile(file: File): boolean {
    // Size validation
    if (file.size > this.maxFileSize) {
      alert(`File ${file.name} is too large`);
      return false;
    }

    // Type validation
    if (!this.allowedTypes.includes(file.type)) {
      alert(`File type ${file.type} not allowed`);
      return false;
    }

    return true;
  }

  uploadFiles(): void {
    if (this.selectedFiles.length === 0) return;

    this.uploading = true;
    this.uploadProgress = 0;

    this.selectedFiles.forEach(file => {
      this.uploadService.uploadFile(file).subscribe({
        next: (event) => {
          if (event.type === HttpEventType.UploadProgress) {
            this.uploadProgress = Math.round(100 * event.loaded / event.total!);
          } else if (event.type === HttpEventType.Response) {
            console.log('Upload complete:', event.body);
          }
        },
        error: (error) => {
          console.error('Upload failed:', error);
          this.uploading = false;
        },
        complete: () => {
          this.uploading = false;
          this.uploadProgress = 100;
        }
      });
    });
  }

  // Drag and drop functionality
  onDragOver(event: DragEvent): void {
    event.preventDefault();
  }

  onDrop(event: DragEvent): void {
    event.preventDefault();
    const files = event.dataTransfer?.files;
    if (files) {
      this.processFiles(files);
    }
  }

  private processFiles(files: FileList): void {
    for (let i = 0; i < files.length; i++) {
      if (this.validateFile(files[i])) {
        this.selectedFiles.push(files[i]);
      }
    }
  }
}
```

### Best Practices for Large Files:
* **Chunked Upload** - Split files into smaller pieces
* **Resume Capability** - Allow resuming interrupted uploads
* **Progress Indication** - Show upload progress to users
* **File Validation** - Check size and type before upload
* **Error Recovery** - Retry failed chunks automatically
* **Background Upload** - Use service workers for background processing
* **Compression** - Compress files before upload when possible

# Angular Security Interview Questions & Answers

## 82. What are common security concerns in Angular applications and how do you mitigate them?

**Answer:**
The main security concerns in Angular apps are XSS, CSRF, injection attacks, and insecure dependencies. Here's how I handle them:

* **Cross-Site Scripting (XSS)**
  - Use Angular's built-in sanitization
  - Avoid `innerHTML` with user data
  - Use `textContent` instead

```typescript
// Bad - vulnerable to XSS
element.innerHTML = userInput;

// Good - safe approach
element.textContent = userInput;
// Or use Angular's sanitization
this.sanitizer.sanitize(SecurityContext.HTML, userInput);
```

* **Cross-Site Request Forgery (CSRF)**
  - Use Angular's HttpClientXsrfModule
  - Implement CSRF tokens

```typescript
// app.module.ts
import { HttpClientXsrfModule } from '@angular/common/http';

@NgModule({
  imports: [
    HttpClientXsrfModule.withOptions({
      cookieName: 'XSRF-TOKEN',
      headerName: 'X-XSRF-TOKEN'
    })
  ]
})
```

* **Injection Attacks**
  - Validate all inputs
  - Use parameterized queries on backend
  - Sanitize user data

```typescript
// Input validation
validateInput(input: string): boolean {
  const pattern = /^[a-zA-Z0-9\s]+$/;
  return pattern.test(input);
}
```

## 83. How does Angular prevent Cross-Site Scripting (XSS)?

**Answer:**
Angular has built-in XSS protection through automatic sanitization and safe practices:

* **Automatic Sanitization**
  - Angular sanitizes values before displaying them
  - Works with interpolation and property binding

```typescript
// Template - automatically sanitized
<div>{{ userInput }}</div>
<div [innerHTML]="trustedHtml"></div>

// Component
export class MyComponent {
  userInput = '<script>alert("XSS")</script>'; // Automatically sanitized
  
  constructor(private sanitizer: DomSanitizer) {}
  
  get trustedHtml() {
    return this.sanitizer.sanitize(SecurityContext.HTML, this.rawHtml);
  }
}
```

* **Security Contexts**
  - HTML, Style, Script, URL, Resource URL contexts
  - Each context has specific sanitization rules

```typescript
// Different security contexts
sanitizeHtml(html: string) {
  return this.sanitizer.sanitize(SecurityContext.HTML, html);
}

sanitizeUrl(url: string) {
  return this.sanitizer.sanitize(SecurityContext.URL, url);
}
```

* **Bypass Sanitization (Use Carefully)**
  - Only when you trust the content completely

```typescript
// Only use when absolutely necessary
bypassSanitization(html: string) {
  return this.sanitizer.bypassSecurityTrustHtml(html);
}
```

## 84. How do you secure an Angular application in a production environment?

**Answer:**
Production security involves multiple layers - build optimization, server configuration, and runtime protection:

* **Build Security**
  - Enable production mode
  - Use AOT compilation
  - Minimize and obfuscate code

```typescript
// main.ts
import { enableProdMode } from '@angular/core';

if (environment.production) {
  enableProdMode();
}

// angular.json - AOT enabled by default in production
"build": {
  "builder": "@angular-devkit/build-angular:browser",
  "options": {
    "aot": true,
    "optimization": true
  }
}
```

* **Content Security Policy (CSP)**
  - Prevent code injection
  - Restrict resource loading

```html
<!-- index.html -->
<meta http-equiv="Content-Security-Policy" 
      content="default-src 'self'; 
               script-src 'self' 'unsafe-eval'; 
               style-src 'self' 'unsafe-inline';">
```

* **HTTPS and Security Headers**
  - Force HTTPS in production
  - Set security headers

```typescript
// Server configuration (Express example)
app.use((req, res, next) => {
  res.setHeader('X-Frame-Options', 'DENY');
  res.setHeader('X-Content-Type-Options', 'nosniff');
  res.setHeader('Strict-Transport-Security', 'max-age=31536000');
  next();
});
```

* **Environment Variables**
  - Keep sensitive data out of client code
  - Use environment-specific configs

```typescript
// environment.prod.ts
export const environment = {
  production: true,
  apiUrl: 'https://api.myapp.com', // No secrets here
  enableLogging: false
};

// Use backend proxy for sensitive operations
getSecureData() {
  return this.http.get('/api/secure-endpoint'); // Backend handles auth
}
```

* **Authentication & Authorization**
  - Implement proper JWT handling
  - Use guards for route protection

```typescript
// Auth guard
@Injectable()
export class AuthGuard implements CanActivate {
  constructor(private auth: AuthService, private router: Router) {}
  
  canActivate(): boolean {
    if (this.auth.isAuthenticated()) {
      return true;
    }
    this.router.navigate(['/login']);
    return false;
  }
}

// JWT interceptor
@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler) {
    const token = localStorage.getItem('token');
    if (token) {
      req = req.clone({
        setHeaders: { Authorization: `Bearer ${token}` }
      });
    }
    return next.handle(req);
  }
}
```

# Angular Miscellaneous Interview Questions & Answers

## 85. What is Angular Universal, and how does it enable server-side rendering (SSR)?

Angular Universal is Angular's solution for server-side rendering that renders your app on the server before sending it to the browser.

**Key Benefits:**
* Faster initial page load
* Better SEO optimization
* Improved social media sharing
* Enhanced performance on slow devices

**How it works:**
* Renders Angular components on the server
* Sends pre-rendered HTML to browser
* Hydrates the app on client-side

```typescript
// Install Angular Universal
ng add @nguniversal/express-engine

// Build and serve
npm run build:ssr
npm run serve:ssr

// app.server.module.ts
import { NgModule } from '@angular/core';
import { ServerModule } from '@angular/platform-server';
import { AppModule } from './app.module';
import { AppComponent } from './app.component';

@NgModule({
  imports: [AppModule, ServerModule],
  bootstrap: [AppComponent]
})
export class AppServerModule {}
```

## 86. Can you explain the purpose of `angular.json`? What kind of configurations can be done there?

`angular.json` is the workspace configuration file that defines build and development settings for Angular projects.

**Main purposes:**
* Project structure definition
* Build configurations
* Development server settings
* Testing configurations

**Key configurations:**
* Build options and environments
* Asset management
* Style preprocessing
* Bundle optimization

```json
{
  "projects": {
    "my-app": {
      "architect": {
        "build": {
          "builder": "@angular-devkit/build-angular:browser",
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

## 87. How do you create custom Angular schematics using Angular CLI?

Angular schematics are templates that generate or modify code. You can create custom ones for repetitive tasks.

**Steps to create:**
* Generate schematic collection
* Define templates and rules
* Implement transformation logic
* Test and publish

```typescript
// Install schematics CLI
npm install -g @angular-devkit/schematics-cli

// Create new schematic
schematics blank my-schematic

// src/my-schematic/index.ts
import { Rule, SchematicContext, Tree, apply, template, url, move, mergeWith } from '@angular-devkit/schematics';
import { strings } from '@angular-devkit/core';

export function mySchematic(options: any): Rule {
  return (tree: Tree, context: SchematicContext) => {
    const templateSource = apply(url('./files'), [
      template({
        ...strings,
        ...options
      }),
      move('src/app')
    ]);
    
    return mergeWith(templateSource);
  };
}

// collection.json
{
  "schematics": {
    "my-schematic": {
      "description": "Custom component generator",
      "factory": "./my-schematic/index#mySchematic"
    }
  }
}
```

## 88. What is your experience with integrating Angular with other frameworks or technologies?

I've integrated Angular with various technologies for different project needs.

**Common integrations:**
* Backend APIs (REST, GraphQL)
* UI libraries (Material, PrimeNG)
* State management (NgRx, Akita)
* Micro-frontends (Module Federation)

**Real examples:**
* Angular + Node.js/Express APIs
* Angular + Firebase for real-time features
* Angular + Electron for desktop apps
* Angular + Ionic for mobile apps

```typescript
// Integration with GraphQL
npm install apollo-angular @apollo/client graphql

// app.module.ts
import { ApolloModule, APOLLO_OPTIONS } from 'apollo-angular';
import { HttpLinkModule, HttpLink } from 'apollo-angular-link-http';

@NgModule({
  imports: [ApolloModule, HttpLinkModule],
  providers: [{
    provide: APOLLO_OPTIONS,
    useFactory: (httpLink: HttpLink) => ({
      cache: new InMemoryCache(),
      link: httpLink.create({ uri: 'http://localhost:4000/graphql' })
    }),
    deps: [HttpLink]
  }]
})
export class AppModule {}

// Using in component
export class UserComponent {
  users$ = this.apollo.watchQuery({
    query: gql`query GetUsers { users { id name email } }`
  }).valueChanges;
  
  constructor(private apollo: Apollo) {}
}
```

## 89. What are some of the most challenging problems you've faced while working with Angular, and how did you solve them?

**Challenge 1: Performance with large datasets**
* Problem: Slow rendering with 10k+ items
* Solution: Virtual scrolling and OnPush strategy

```typescript
// Virtual scrolling solution
<cdk-virtual-scroll-viewport itemSize="50" class="viewport">
  <div *cdkVirtualFor="let item of items">{{item.name}}</div>
</cdk-virtual-scroll-viewport>

// OnPush optimization
@Component({
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class OptimizedComponent {
  @Input() data: any[];
  
  trackByFn(index: number, item: any) {
    return item.id;
  }
}
```

**Challenge 2: Memory leaks with subscriptions**
* Problem: Components not unsubscribing properly
* Solution: takeUntil pattern and async pipe

```typescript
export class ComponentWithSubscriptions implements OnDestroy {
  private destroy$ = new Subject<void>();
  
  ngOnInit() {
    this.dataService.getData()
      .pipe(takeUntil(this.destroy$))
      .subscribe(data => this.processData(data));
  }
  
  ngOnDestroy() {
    this.destroy$.next();
    this.destroy$.complete();
  }
}
```

## 90. How do you stay up to date with new releases and changes in the Angular framework?

**My approach to staying current:**
* Follow official Angular blog and GitHub
* Subscribe to Angular newsletters
* Attend conferences and meetups
* Practice with new features in side projects

**Key resources:**
* Angular.io official documentation
* Angular Weekly newsletter
* ng-conf and AngularConnect conferences
* Angular community on Twitter/LinkedIn

**Practical steps:**
* Update projects regularly using `ng update`
* Read migration guides thoroughly
* Test new features in isolated environments
* Share learnings with team through tech talks

```bash
# Regular update process
ng update @angular/cli @angular/core
ng update @angular/material

# Check for available updates
ng update

# Update specific packages
ng update @angular/core@15 --force
```

## 91. Can you walk us through a specific project where you applied Angular to solve a complex problem?

**Project: Real-time Dashboard for E-commerce Analytics**

**Problem:** Client needed a real-time dashboard showing sales, inventory, and user behavior with complex filtering and data visualization.

**Solution approach:**
* Used Angular with WebSocket connections
* Implemented NgRx for state management
* Created reusable chart components
* Added real-time notifications

**Key implementations:**

```typescript
// WebSocket service for real-time data
@Injectable()
export class RealtimeService {
  private socket = io('ws://localhost:3000');
  
  getRealtimeData() {
    return new Observable(observer => {
      this.socket.on('sales-update', data => observer.next(data));
      return () => this.socket.disconnect();
    });
  }
}

// NgRx state management
interface DashboardState {
  sales: Sale[];
  inventory: Product[];
  loading: boolean;
}

@Injectable()
export class DashboardEffects {
  loadSales$ = createEffect(() =>
    this.actions$.pipe(
      ofType(loadSales),
      switchMap(() =>
        this.realtimeService.getRealtimeData().pipe(
          map(data => loadSalesSuccess({ sales: data }))
        )
      )
    )
  );
}

// Reusable chart component
@Component({
  selector: 'app-chart',
  template: `<canvas #chartCanvas></canvas>`
})
export class ChartComponent implements OnInit {
  @Input() data: any[];
  @Input() type: 'line' | 'bar' = 'line';
  
  ngOnInit() {
    this.renderChart();
  }
  
  private renderChart() {
    // Chart.js implementation
    new Chart(this.chartCanvas.nativeElement, {
      type: this.type,
      data: this.processData(),
      options: this.getChartOptions()
    });
  }
}
```

**Results achieved:**
* 40% faster data visualization
* Real-time updates without page refresh
* Modular architecture for easy maintenance
* Scalable solution handling 1000+ concurrent users

**Key learnings:**
* Proper state management is crucial for complex apps
* WebSocket connections need careful error handling
* Component reusability saves significant development time
* Performance optimization is essential for real-time features

# Angular RxJS Interview Questions & Answers

## 92. What is a `Subject` and `BehaviorSubject` in Angular RxJS? How are they different?

**Subject:**
- Acts as both Observable and Observer
- Multicasts to multiple subscribers
- No initial value, only emits after subscription

**BehaviorSubject:**
- Extends Subject with initial value
- Always has a current value
- New subscribers get the latest value immediately

```typescript
// Subject
const subject = new Subject<string>();
subject.subscribe(val => console.log('Sub1:', val));
subject.next('Hello'); // Sub1: Hello

subject.subscribe(val => console.log('Sub2:', val));
subject.next('World'); // Sub1: World, Sub2: World

// BehaviorSubject
const behaviorSubject = new BehaviorSubject<string>('Initial');
behaviorSubject.subscribe(val => console.log('BS1:', val)); // BS1: Initial

behaviorSubject.next('Updated');
behaviorSubject.subscribe(val => console.log('BS2:', val)); // BS2: Updated
```

**Key Differences:**
- Subject: No initial value, cold start
- BehaviorSubject: Has initial value, hot start
- BehaviorSubject stores last emitted value

---

## 93. What is the difference between `ngFor` and `ngForOf`?

**Answer:** They're the same thing! `ngForOf` is the actual directive name, `ngFor` is just syntactic sugar.

```typescript
// Both are identical
<div *ngFor="let item of items">{{item}}</div>
<div *ngForOf="let item of items">{{item}}</div>

// Expanded form shows ngForOf
<ng-template ngFor let-item [ngForOf]="items">
  <div>{{item}}</div>
</ng-template>
```

**Key Points:**
- `*ngFor` is microsyntax for `ngForOf`
- Angular transforms `*ngFor` to `ngForOf` internally
- Use `*ngFor` in practice - it's cleaner

---

## 94. What is the role of `ngZone` in Angular, and how does it help with performance optimization?

**NgZone manages Angular's change detection cycle:**
- Patches async operations (setTimeout, Promise, events)
- Triggers change detection when async operations complete
- Provides zone-aware execution context

```typescript
import { NgZone } from '@angular/core';

constructor(private ngZone: NgZone) {}

// Run outside Angular zone - no change detection
runOutsideZone() {
  this.ngZone.runOutsideAngular(() => {
    setInterval(() => {
      // Heavy computation - won't trigger change detection
      this.heavyCalculation();
    }, 100);
  });
}

// Manually trigger change detection when needed
updateUI() {
  this.ngZone.run(() => {
    this.data = 'Updated'; // This will trigger change detection
  });
}
```

**Performance Benefits:**
- Avoid unnecessary change detection cycles
- Control when Angular checks for updates
- Optimize heavy computations and animations

---

## 95. What is the purpose of Angular's `Renderer2`?

**Renderer2 provides safe DOM manipulation:**
- Platform-agnostic DOM operations
- Works in server-side rendering (SSR)
- Sanitizes operations for security

```typescript
import { Renderer2, ElementRef } from '@angular/core';

constructor(
  private renderer: Renderer2,
  private el: ElementRef
) {}

manipulateDOM() {
  // Create element
  const div = this.renderer.createElement('div');
  this.renderer.addClass(div, 'my-class');
  
  // Set attributes
  this.renderer.setAttribute(div, 'data-id', '123');
  
  // Add event listener
  this.renderer.listen(div, 'click', (event) => {
    console.log('Clicked!');
  });
  
  // Append to parent
  this.renderer.appendChild(this.el.nativeElement, div);
}
```

**Key Benefits:**
- Safe for SSR and Web Workers
- Built-in XSS protection
- Consistent API across platforms

---

## 96. Explain the difference between `ngOnChanges` and `ngDoCheck`.

**ngOnChanges:**
- Triggered when input properties change
- Receives SimpleChanges object
- Only for @Input properties

**ngDoCheck:**
- Runs on every change detection cycle
- Manual change detection logic
- No parameters provided

```typescript
export class MyComponent implements OnChanges, DoCheck {
  @Input() data: string;
  @Input() user: User;
  
  ngOnChanges(changes: SimpleChanges) {
    // Only runs when @Input properties change
    if (changes['data']) {
      console.log('Data changed:', changes['data'].currentValue);
    }
  }
  
  ngDoCheck() {
    // Runs on every change detection cycle
    // Use for custom change detection logic
    if (this.hasUserChanged()) {
      console.log('User object changed internally');
    }
  }
  
  private hasUserChanged(): boolean {
    // Custom logic to detect changes
    return this.user && this.user.lastModified !== this.lastChecked;
  }
}
```

**When to Use:**
- **ngOnChanges**: Input property changes, simple data types
- **ngDoCheck**: Complex objects, custom change detection, performance-critical scenarios

**Performance Note:**
- ngDoCheck runs frequently - keep logic lightweight
- ngOnChanges is more efficient for simple input changes
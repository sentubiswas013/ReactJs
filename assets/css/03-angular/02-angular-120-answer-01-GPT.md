## 🔹 1. Angular Fundamentals

## 🔹 1. What is Angular?

* Angular is a **TypeScript-based frontend framework** developed by Google
* It’s used to build **single-page applications (SPAs)**
* Angular follows a **component-based architecture**
* It provides built-in features like **routing, forms, HTTP, dependency injection**

**Example**

```ts
// app.component.ts
@Component({
  selector: 'app-root',
  template: `<h1>Hello Angular</h1>`
})
export class AppComponent {}
```

👉 *In simple terms: Angular helps us build structured, scalable UI applications.*

---

## 🔹 2. Difference between Angular and AngularJS?

* **AngularJS** is the old version (Angular 1.x), based on **JavaScript**
* **Angular (2+)** is completely rewritten, based on **TypeScript**
* AngularJS uses **MVC**, Angular uses **component-based architecture**
* Angular is **faster**, **modular**, and **mobile-friendly**

**Example**

```js
// AngularJS
$scope.message = "Hello";
```

```ts
// Angular
message = 'Hello';
```

👉 *Angular is modern, AngularJS is legacy.*

---

## 🔹 3. Key features introduced till Angular 18

* **Standalone components** (no need for NgModules)
* **Signals** for fine-grained reactivity
* **Improved SSR & hydration**
* **Better performance & smaller bundles**
* **Deferrable views** for lazy UI loading

**Example (Signal)**

```ts
count = signal(0);

increment() {
  this.count.update(v => v + 1);
}
```

👉 *Angular 18 focuses on performance, simplicity, and reactivity.*

---

## 🔹 4. What are components?

* Components are the **basic building blocks** of Angular apps
* Each component has:

  * HTML (view)
  * TypeScript (logic)
  * CSS (style)
* Components control a **part of the UI**

**Example**

```ts
@Component({
  selector: 'app-user',
  template: `<p>{{name}}</p>`
})
export class UserComponent {
  name = 'Pintu';
}
```

👉 *UI in Angular is nothing but a tree of components.*

---

## 🔹 5. What are modules and why are they needed?

* Modules are used to **group related components, directives, services**
* They help in **organizing large applications**
* Enable **lazy loading**
* In older Angular, every app had at least one `AppModule`

**Example**

```ts
@NgModule({
  declarations: [UserComponent],
  imports: [BrowserModule],
  bootstrap: [AppComponent]
})
export class AppModule {}
```

👉 *Note: With standalone components, modules are now optional.*

---

## 🔹 6. What is a template?

* A template defines the **HTML view** of a component
* It supports:

  * Data binding
  * Directives
  * Events
* Templates connect UI with component logic

**Example**

```html
<h2>{{ title }}</h2>
<button (click)="sayHello()">Click</button>
```

```ts
title = 'Angular App';
sayHello() {
  alert('Hello!');
}
```

👉 *Template = UI + bindings.*

---

## 🔹 7. What are decorators in Angular?

* Decorators are **metadata annotations**
* They tell Angular **how a class should behave**
* Common decorators:

  * `@Component`
  * `@NgModule`
  * `@Injectable`
  * `@Input`

**Example**

```ts
@Component({
  selector: 'app-demo',
  template: `<p>Demo</p>`
})
export class DemoComponent {}
```

👉 *Decorators make Angular understand our code.*

---

## 🔹 8. What is a directive? Types of directives

* Directives are used to **manipulate DOM elements**
* They change **appearance or behavior** of elements

### Types of Directives:

1. **Structural** – change DOM structure (`*ngIf`, `*ngFor`)
2. **Attribute** – change style/behavior (`ngClass`, `ngStyle`)
3. **Custom** – user-defined

**Example**

```html
<p *ngIf="isLoggedIn">Welcome</p>
<div [ngClass]="{active: isActive}"></div>
```

👉 *Directives are powerful tools for dynamic UI.*

---

## 🔹 9. What are services?

* Services are **classes used to share business logic or data**
* They help keep components **clean and reusable**
* Commonly used for **API calls, shared state, utilities**
* Services are injected into components using **Dependency Injection**

**Example**

```ts
@Injectable({ providedIn: 'root' })
export class UserService {
  getUser() {
    return 'Pintu';
  }
}
```

```ts
constructor(private userService: UserService) {
  console.log(this.userService.getUser());
}
```

👉 *Services separate logic from UI.*

---

## 🔹 10. What is dependency injection?

* Dependency Injection (DI) is a design pattern
* Angular **automatically provides required objects**
* Improves **testability, modularity, and loose coupling**
* Angular has a **built-in DI container**

**Example**

```ts
constructor(private http: HttpClient) {}
```

👉 *We don’t create objects manually—Angular injects them.*

---

## 🔹 11. What is bootstrapping?

* Bootstrapping is the **process of starting an Angular app**
* Angular loads the **root component**
* It connects Angular with the browser DOM
* Defined in `main.ts`

**Example**

```ts
platformBrowserDynamic()
  .bootstrapModule(AppModule);
```

👉 *Bootstrapping = app startup.*

---

## 🔹 12. Which file loads first in Angular?

* The **first file that loads is `main.ts`**
* It bootstraps the root module or root component
* From there, Angular loads everything else

**Example**

```ts
bootstrapApplication(AppComponent);
```

👉 *`main.ts` is the entry point.*

---

## 🔹 13. Can we rename `main.ts`?

* Yes, we **can rename `main.ts`**
* But we must update:

  * `angular.json`
  * build configuration
* Otherwise, the app will not load

**Example**

```json
"main": "src/start.ts"
```

👉 *Renaming is possible but not recommended.*

---

## 🔹 14. What is data binding? Types of data binding

* Data binding connects **component logic with the template**
* Keeps UI and data **in sync**

### Types:

1. **Interpolation** – `{{ }}`
2. **Property Binding** – `[ ]`
3. **Event Binding** – `( )`
4. **Two-way Binding** – `[( )]`

**Example**

```html
<h1>{{ title }}</h1>
<input [(ngModel)]="name" />
<button (click)="save()">Save</button>
```

👉 *Data binding makes Angular reactive.*

---

## 🔹 15. What are lifecycle hooks?

* Lifecycle hooks are **methods triggered at different stages**
* Used to control **component behavior**
* Common hooks:

  * `ngOnInit`
  * `ngOnChanges`
  * `ngOnDestroy`

**Example**

```ts
ngOnInit() {
  console.log('Component initialized');
}
```

👉 *Hooks let us tap into component life.*

---

## 🔹 16. Difference between constructor and `ngOnInit`

* **Constructor**

  * Used for **dependency injection**
  * Runs when class is created
* **ngOnInit**

  * Used for **initialization logic**
  * Runs after data binding

**Example**

```ts
constructor(private service: UserService) {}

ngOnInit() {
  this.loadData();
}
```

👉 *Constructor = setup, `ngOnInit` = start logic.*

---

## 🔹 2. Templates & Directives
## 🔹 1. What is `ngIf`?

* `ngIf` is a **structural directive**
* It **adds or removes elements from the DOM**
* Used for **conditional rendering**
* Better than CSS hiding because DOM is destroyed

**Example**

```html
<p *ngIf="isLoggedIn">Welcome User</p>
```

```ts
isLoggedIn = true;
```

👉 *If condition is false, element doesn’t exist in DOM.*

---

## 🔹 2. What is `ngFor`?

* `ngFor` is a **structural directive**
* Used to **loop through collections**
* Creates a template instance for each item
* Commonly used to render lists

**Example**

```html
<li *ngFor="let user of users">{{ user }}</li>
```

```ts
users = ['Pintu', 'Rahul', 'Amit'];
```

👉 *Angular repeats the element for each item.*

---

## 🔹 3. Difference between `ngFor` and `ngForOf`?

* `ngFor` is the **directive name**
* `ngForOf` is the **input property**
* `*ngFor` is shorthand syntax

**Example (expanded syntax)**

```html
<li *ngFor="let user of users"></li>
```

```html
<ng-template ngFor let-user [ngForOf]="users">
  <li>{{ user }}</li>
</ng-template>
```

👉 *Both do the same thing.*

---

## 🔹 4. What is `ngClass`?

* `ngClass` is an **attribute directive**
* Used to **add/remove CSS classes dynamically**
* Works with strings, arrays, or objects

**Example**

```html
<div [ngClass]="{ active: isActive, error: hasError }"></div>
```

```ts
isActive = true;
hasError = false;
```

👉 *Perfect for conditional styling.*

---

## 🔹 5. What is a template reference variable?

* Used to **reference a DOM element**
* Defined using `#`
* Accessible only inside the template
* Often used with forms or inputs

**Example**

```html
<input #nameInput />
<button (click)="show(nameInput.value)">Show</button>
```

```ts
show(value: string) {
  console.log(value);
}
```

👉 *Direct access to elements without ViewChild.*

---

## 🔹 6. What is `ng-content`?

* Used for **content projection**
* Allows passing HTML from parent to child
* Similar to slots in web components

**Example**

```html
<!-- parent -->
<app-card>
  <h2>Title</h2>
</app-card>
```

```html
<!-- child -->
<ng-content></ng-content>
```

👉 *Helps create reusable components.*

---

## 🔹 7. What is `ng-template`?

* `ng-template` defines **template blocks**
* Not rendered unless explicitly used
* Commonly used with `ngIf` and `ngFor`

**Example**

```html
<ng-template #noData>
  <p>No data available</p>
</ng-template>

<div *ngIf="data; else noData"></div>
```

👉 *Acts as a hidden template.*

---

## 🔹 8. What is `trackBy` and why is it important?

* `trackBy` improves **performance**
* Helps Angular **track items uniquely**
* Prevents unnecessary DOM re-creation

**Example**

```html
<li *ngFor="let user of users; trackBy: trackUser">
  {{ user.name }}
</li>
```

```ts
trackUser(index: number, user: any) {
  return user.id;
}
```

👉 *Very important for large lists.*

---

## 🔹 9. What are pure vs impure pipes?

### Pure Pipes

* Execute only when input changes
* Faster and recommended
* Default behavior

### Impure Pipes

* Execute on every change detection
* Slower, use carefully

**Example**

```ts
@Pipe({ name: 'custom', pure: false })
export class CustomPipe {}
```

👉 *Use impure pipes only when needed.*

---

## 🔹 3. Forms

## 🔹 1. What are Angular forms?

* Angular forms are used to **capture, validate, and submit user input**
* They provide **state management** for form controls
* Angular supports **two types of forms**

  * Template-driven
  * Reactive
* Forms track values, errors, and touched states

**Example**

```html
<form>
  <input type="text" />
</form>
```

👉 *Angular forms make handling user input structured and safe.*

---

## 🔹 2. Template-driven vs Reactive forms

### Template-driven Forms

* Logic written mostly in **HTML**
* Easy to use, less code
* Best for **simple forms**

### Reactive Forms

* Logic written in **TypeScript**
* More control and scalability
* Best for **complex forms**

**Example**

```html
<!-- Template-driven -->
<input [(ngModel)]="name" name="name" />
```

```ts
// Reactive
form = new FormGroup({
  name: new FormControl('')
});
```

👉 *Reactive forms are preferred in real projects.*

---

## 🔹 3. What is `ngModel`?

* `ngModel` enables **two-way data binding**
* Used in **template-driven forms**
* Automatically syncs input and component data
* Requires `FormsModule`

**Example**

```html
<input [(ngModel)]="username" name="username" />
<p>{{ username }}</p>
```

```ts
username = '';
```

👉 *Any change in UI updates the model and vice versa.*

---

## 🔹 4. How do you validate forms?

* Angular provides **built-in validators**
* Validation can be done in:

  * Template
  * Reactive form
* Common validators:

  * `required`
  * `minLength`
  * `email`

**Example (Reactive)**

```ts
form = new FormGroup({
  email: new FormControl('', [Validators.required, Validators.email])
});
```

```html
<div *ngIf="form.get('email')?.invalid">
  Invalid Email
</div>
```

👉 *Validation prevents bad data.*

---

## 🔹 5. How do you create custom validators?

* Custom validators are **functions**
* They return:

  * `null` → valid
  * error object → invalid
* Used when built-in validators are not enough

**Example**

```ts
function noSpace(control: AbstractControl) {
  return control.value?.includes(' ')
    ? { spaceError: true }
    : null;
}
```

```ts
name = new FormControl('', [noSpace]);
```

👉 *Custom validators add business rules.*

---

## 🔹 4. Routing & Navigation

## **1. What is Angular Router?**

**Spoken answer (25–60 sec):**

* Angular Router is used to **navigate between views** in a single-page application.
* It updates the **URL without reloading the page**.
* Each route maps a **URL path to a component**.
* It helps build **multi-page-like behavior** in Angular apps.
* Common use cases are dashboards, login pages, and detail views.

**Example:**

```ts
// app.routes.ts
import { Routes } from '@angular/router';
import { HomeComponent } from './home.component';
import { AboutComponent } from './about.component';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'about', component: AboutComponent }
];
```

```html
<!-- app.component.html -->
<a routerLink="/">Home</a>
<a routerLink="/about">About</a>

<router-outlet></router-outlet>
```

---

## **2. What is RouterModule?**

**Spoken answer:**

* `RouterModule` is the **Angular module that provides routing features**.
* It registers routes and enables navigation.
* `forRoot()` is used in the **root module**.
* `forChild()` is used in **feature or lazy-loaded modules**.
* It exposes directives like `routerLink` and `router-outlet`.

**Example:**

```ts
// app.module.ts
import { RouterModule } from '@angular/router';
import { routes } from './app.routes';

@NgModule({
  imports: [RouterModule.forRoot(routes)],
})
export class AppModule {}
```

---

## **3. What is Lazy Loading?**

**Spoken answer:**

* Lazy loading loads a module **only when the route is accessed**.
* It improves **initial load time and performance**.
* Very useful for large apps with admin or dashboard modules.
* Angular downloads the module **on demand**.

**Example:**

```ts
// app.routes.ts
export const routes: Routes = [
  {
    path: 'admin',
    loadChildren: () =>
      import('./admin/admin.module').then(m => m.AdminModule)
  }
];
```

```ts
// admin-routing.module.ts
const routes: Routes = [
  { path: '', component: AdminDashboardComponent }
];
```

---

## **4. What are Route Guards?**

**Spoken answer:**

* Route guards **control access** to routes.
* They decide whether navigation should **allow or block**.
* Common guards are `CanActivate`, `CanDeactivate`, `CanLoad`.
* Mostly used for **authentication and permissions**.

**Example:**

```ts
{
  path: 'dashboard',
  component: DashboardComponent,
  canActivate: [AuthGuard]
}
```

---

## **5. How to implement AuthGuard?**

**Spoken answer:**

* AuthGuard checks if the user is **logged in**.
* If authenticated → allow navigation.
* If not → redirect to login page.
* It usually uses a service to check login status.

**Example:**

```ts
// auth.guard.ts
@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate {
  constructor(private auth: AuthService, private router: Router) {}

  canActivate(): boolean {
    if (this.auth.isLoggedIn()) {
      return true;
    }
    this.router.navigate(['/login']);
    return false;
  }
}
```

```ts
// auth.service.ts
isLoggedIn(): boolean {
  return !!localStorage.getItem('token');
}
```

---

## **6. What is a Resolver?**

**Spoken answer:**

* A resolver **fetches data before the route loads**.
* The component loads **only after data is ready**.
* It avoids loading spinners inside components.
* Commonly used for edit or detail pages.

**Example:**

```ts
// user.resolver.ts
@Injectable({ providedIn: 'root' })
export class UserResolver implements Resolve<any> {
  resolve() {
    return this.userService.getUser();
  }
}
```

```ts
{
  path: 'profile',
  component: ProfileComponent,
  resolve: { user: UserResolver }
}
```

```ts
// profile.component.ts
ngOnInit() {
  this.user = this.route.snapshot.data['user'];
}
```

---

## **7. Difference between Authentication & Authorization**

**Spoken answer:**

* Authentication checks **who the user is**.
* Authorization checks **what the user can access**.
* Authentication happens **first**.
* Authorization is based on **roles or permissions**.
* Both are commonly enforced using **route guards**.

**Example:**

```ts
// Authentication
isLoggedIn() {
  return !!token;
}

// Authorization
hasAdminRole() {
  return user.role === 'ADMIN';
}
```

```ts
{
  path: 'admin',
  canActivate: [AuthGuard, AdminGuard]
}
```

---

## 🔹 5. HTTP & Backend Integration

## 🔹 1. How do you make HTTP calls in Angular?

**Answer (spoken style):**

* In Angular, we make HTTP calls using **HttpClient** from `@angular/common/http`
* It supports **GET, POST, PUT, DELETE**
* HTTP methods return **Observables**
* We usually keep API calls inside **services**, not components
* Then we subscribe to get the response

**Example Code:**

```ts
// app.module.ts
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  imports: [HttpClientModule]
})
export class AppModule {}
```

```ts
// user.service.ts
import { HttpClient } from '@angular/common/http';

constructor(private http: HttpClient) {}

getUsers() {
  return this.http.get('https://api.example.com/users');
}
```

```ts
// component.ts
this.userService.getUsers().subscribe(data => {
  console.log(data);
});
```

---

## 🔹 2. What is HttpClient?

**Answer (spoken style):**

* **HttpClient** is Angular’s built-in service for making HTTP requests
* It’s based on **RxJS Observables**
* Automatically converts **JSON responses**
* Supports **interceptors, headers, error handling**
* Replaces the old `HttpModule`

**Example Code:**

```ts
import { HttpClient } from '@angular/common/http';

constructor(private http: HttpClient) {}

getProducts() {
  return this.http.get<any[]>('/api/products');
}
```

---

## 🔹 3. How do you handle HTTP errors?

**Answer (spoken style):**

* Angular handles errors using **RxJS catchError**
* Errors can be **client-side or server-side**
* We handle them inside the service
* This keeps components clean
* We return a user-friendly message

**Example Code:**

```ts
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

getUsers() {
  return this.http.get('/api/users').pipe(
    catchError(error => {
      console.error(error);
      return throwError(() => 'Something went wrong!');
    })
  );
}
```

---

## 🔹 4. What are interceptors?

**Answer (spoken style):**

* Interceptors sit **between request and response**
* Used for **auth tokens, logging, error handling**
* They work globally
* No need to repeat logic in every service
* Very useful for JWT authentication

**Example Code:**

```ts
@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler) {
    const authReq = req.clone({
      setHeaders: { Authorization: 'Bearer token123' }
    });
    return next.handle(authReq);
  }
}
```

```ts
providers: [
  { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }
]
```

---

## 🔹 5. How do you add headers globally?

**Answer (spoken style):**

* We add global headers using **HTTP Interceptors**
* This avoids repeating headers in every request
* Common headers: **Authorization, Content-Type**
* Cleaner and more maintainable

**Example Code:**

```ts
intercept(req: HttpRequest<any>, next: HttpHandler) {
  const cloned = req.clone({
    setHeaders: {
      'Content-Type': 'application/json'
    }
  });
  return next.handle(cloned);
}
```

---

## 🔹 6. How do you handle file uploads?

**Answer (spoken style):**

* File uploads use **FormData**
* We append the file to FormData
* Send it using **HttpClient POST**
* Angular handles multipart automatically
* No need to set Content-Type manually

**Example Code:**

```ts
uploadFile(file: File) {
  const formData = new FormData();
  formData.append('file', file);

  return this.http.post('/api/upload', formData);
}
```

```ts
onFileSelect(event: any) {
  const file = event.target.files[0];
  this.uploadService.uploadFile(file).subscribe();
}
```

---

## 🔹 7. How do you make parallel API calls?

**Answer (spoken style):**

* Angular uses **forkJoin** from RxJS
* It runs multiple APIs in parallel
* Executes only when all calls complete
* Best for dashboards or combined data
* Cleaner than nested subscriptions

**Example Code:**

```ts
import { forkJoin } from 'rxjs';

forkJoin({
  users: this.http.get('/api/users'),
  products: this.http.get('/api/products')
}).subscribe(result => {
  console.log(result.users);
  console.log(result.products);
});
```

---

## 🔹 6. RxJS (Very Important)

## **1️⃣ What is RxJS?**

**Spoken explanation (25–40 sec):**

* RxJS stands for **Reactive Extensions for JavaScript**
* It’s a library used in Angular to **handle async data streams**
* Things like **HTTP calls, user clicks, form value changes, WebSockets**
* RxJS works using **Observables** and **operators** like `map`, `filter`, `switchMap`
* Angular uses RxJS heavily in **HttpClient, Router, Forms**

**Example code:**

```ts
import { of } from 'rxjs';

of(1, 2, 3).subscribe(value => {
  console.log(value);
});
```

---

## **2️⃣ What is an Observable?**

**Spoken explanation (30–45 sec):**

* An Observable represents a **stream of data over time**
* It can emit **multiple values**, not just one
* It’s **lazy** → runs only when someone subscribes
* Used for HTTP responses, events, timers
* You can **cancel** it using `unsubscribe`

**Example code:**

```ts
import { Observable } from 'rxjs';

const obs$ = new Observable(observer => {
  observer.next('Hello');
  observer.next('World');
  observer.complete();
});

obs$.subscribe(data => console.log(data));
```

---

## **3️⃣ What is an Observer?**

**Spoken explanation (25–35 sec):**

* An Observer is the **listener** of an Observable
* It has **three optional methods**

  * `next` → receives data
  * `error` → handles error
  * `complete` → stream finished
* When we call `subscribe()`, we pass an Observer

**Example code:**

```ts
obs$.subscribe({
  next: value => console.log(value),
  error: err => console.error(err),
  complete: () => console.log('Done')
});
```

---

## **4️⃣ What is multicasting?**

**Spoken explanation (30–45 sec):**

* Multicasting means **sharing one data source with multiple subscribers**
* By default, Observables are **unicast**
* RxJS uses **Subjects** for multicasting
* Very useful when multiple components need the same data
* Example: user data, theme changes, notifications

**Example code:**

```ts
import { Subject } from 'rxjs';

const subject = new Subject();

subject.subscribe(v => console.log('A:', v));
subject.subscribe(v => console.log('B:', v));

subject.next(100);
```

---

## **5️⃣ Subject vs BehaviorSubject vs ReplaySubject**

### **Subject**

**Spoken (25–30 sec):**

* No initial value
* New subscribers **don’t get old values**
* Acts like an event emitter

```ts
const subject = new Subject<number>();
subject.next(1);
subject.subscribe(v => console.log(v)); // ❌ won't get 1
```

---

### **BehaviorSubject**

**Spoken (30–40 sec):**

* Requires **initial value**
* Always emits **latest value** to new subscribers
* Commonly used for **state management**

```ts
const bs = new BehaviorSubject<number>(0);
bs.next(5);
bs.subscribe(v => console.log(v)); // ✅ 5
```

---

### **ReplaySubject**

**Spoken (30–40 sec):**

* Replays **previous values** to new subscribers
* You can control how many values to replay
* Useful for caching data

```ts
const rs = new ReplaySubject<number>(2);
rs.next(1);
rs.next(2);
rs.next(3);

rs.subscribe(v => console.log(v)); // 2, 3
```

---

## **6️⃣ Promise vs Observable**

**Spoken explanation (45–60 sec):**

* Promise emits **only one value**
* Observable can emit **multiple values**
* Promise executes immediately
* Observable is **lazy** → runs on subscribe
* Promise **cannot be cancelled**
* Observable **can be cancelled** using unsubscribe
* Angular prefers Observables for HTTP and events

**Example comparison:**

```ts
// Promise
fetch('/api/data')
  .then(res => res.json())
  .then(data => console.log(data));

// Observable (Angular)
this.http.get('/api/data')
  .subscribe(data => console.log(data));
```

---

## **7️⃣ `map`, `filter`, `switchMap`, `mergeMap`, `concatMap`**

### **Spoken explanation (45–60 sec):**

* `map` → transforms data
* `filter` → removes unwanted values
* `switchMap` → cancels previous request, keeps latest
* `mergeMap` → runs multiple requests in parallel
* `concatMap` → runs requests **one by one in order**
* Most commonly used in **HTTP calls, search, form changes**

**Example code:**

```ts
from([1, 2, 3, 4]).pipe(
  map(v => v * 10),
  filter(v => v > 20)
).subscribe(console.log);
```

```ts
this.search$.pipe(
  switchMap(term => this.http.get(`/api/search?q=${term}`))
).subscribe();
```

---

## **8️⃣ Difference between `switchMap`, `mergeMap`, `concatMap`**

### **Spoken explanation (50–60 sec):**

* `switchMap`

  * Cancels previous request
  * Best for **search inputs**
* `mergeMap`

  * Runs all requests together
  * Best for **parallel API calls**
* `concatMap`

  * Executes one after another
  * Best for **ordered operations**

**Example code:**

```ts
// switchMap (search)
this.search$.pipe(
  switchMap(term => this.http.get(`/api?q=${term}`))
).subscribe();
```

```ts
// mergeMap (parallel)
from([1,2,3]).pipe(
  mergeMap(id => this.http.get(`/api/${id}`))
).subscribe();
```

```ts
// concatMap (sequence)
from([1,2,3]).pipe(
  concatMap(id => this.http.get(`/api/${id}`))
).subscribe();
```

---

## **9️⃣ How do you handle errors in RxJS?**

### **Spoken explanation (30–45 sec):**

* RxJS uses `catchError` operator
* Helps prevent app crash
* We can return fallback data or show error message
* Mostly used in **HTTP calls**

**Example code:**

```ts
this.http.get('/api/data').pipe(
  catchError(error => {
    console.error(error);
    return of([]);
  })
).subscribe(data => console.log(data));
```

---

## **🔟 How to unsubscribe properly?**

### **Spoken explanation (30–45 sec):**

* Unsubscribing prevents **memory leaks**
* Use `unsubscribe()` manually
* Best practice is `takeUntil`
* Angular async pipe unsubscribes automatically

**Example code:**

```ts
subscription!: Subscription;

ngOnInit() {
  this.subscription = interval(1000).subscribe();
}

ngOnDestroy() {
  this.subscription.unsubscribe();
}
```

---

## **1️⃣1️⃣ What is `takeUntil`?**

### **Spoken explanation (40–60 sec):**

* `takeUntil` automatically unsubscribes
* It listens to a **destroy notifier**
* Very clean and scalable solution
* Commonly used with `ngOnDestroy`

**Example code:**

```ts
destroy$ = new Subject<void>();

ngOnInit() {
  interval(1000).pipe(
    takeUntil(this.destroy$)
  ).subscribe(console.log);
}

ngOnDestroy() {
  this.destroy$.next();
  this.destroy$.complete();
}
```

---

## ✅ **One-Line Interview Summary**

> **switchMap cancels, mergeMap parallel, concatMap sequence.
> Use takeUntil for safe unsubscription.**

---

## 🔹 7. Change Detection & Performance

## 1️⃣ What is Change Detection?

**Spoken answer (bullet style):**

* Change detection is how Angular **keeps the UI in sync with data**
* Whenever component data changes, Angular checks:

  * Did any value used in the template change?
  * If yes → update the DOM
* This happens automatically for:

  * User events
  * HTTP responses
  * Timers like `setTimeout`
* Without change detection, Angular wouldn’t know **when to re-render**

**Example:**

```ts
@Component({
  selector: 'app-counter',
  template: `<h1>{{ count }}</h1>`
})
export class CounterComponent {
  count = 0;

  increment() {
    this.count++;
  }
}
```

👉 When `count` changes, Angular detects it and updates the view.

---

## 2️⃣ How Does Angular Change Detection Work?

**Spoken answer:**

* Angular uses a **component tree**
* Change detection starts at the **root component**
* It checks every component **top → bottom**
* For each component, Angular:

  * Re-evaluates template expressions
  * Compares old vs new values
* If something changed → DOM updates
* This cycle runs **very frequently**

**Example flow:**

```ts
<button (click)="update()">Update</button>
<p>{{ value }}</p>
```

```ts
update() {
  this.value = 'Updated';
}
```

👉 Click triggers an event → Angular runs change detection → view updates.

---

## 3️⃣ What is Zone.js?

**Spoken answer:**

* Zone.js is a **library Angular uses behind the scenes**
* It **monitors async operations** like:

  * Click events
  * HTTP calls
  * Timers
* When an async task finishes, Zone.js tells Angular:
  👉 “Something might have changed”
* Angular then runs change detection automatically

**Example:**

```ts
setTimeout(() => {
  this.message = 'Hello';
}, 1000);
```

👉 Zone.js intercepts `setTimeout` and triggers change detection after it runs.

---

## 4️⃣ What is `ngZone`?

**Spoken answer:**

* `ngZone` is Angular’s **wrapper around Zone.js**
* It lets us:

  * Run code **inside Angular** (with change detection)
  * Run code **outside Angular** (without change detection)
* Useful for **performance optimization**

**Example:**

```ts
constructor(private ngZone: NgZone) {}

runHeavyTask() {
  this.ngZone.runOutsideAngular(() => {
    for (let i = 0; i < 1000000; i++) {}
  });
}
```

👉 Heavy logic runs without triggering unnecessary change detection.

---

## 5️⃣ ChangeDetectionStrategy.Default vs OnPush

**Spoken answer:**

* `Default`:

  * Angular checks the component **every time**
  * Safer, but slower
* `OnPush`:

  * Angular checks only when:

    * `@Input()` reference changes
    * Event happens inside component
  * Much **better performance**

**Example:**

```ts
@Component({
  selector: 'app-user',
  changeDetection: ChangeDetectionStrategy.OnPush,
  template: `<p>{{ user.name }}</p>`
})
export class UserComponent {
  @Input() user!: { name: string };
}
```

👉 Changing the object reference updates the UI, not internal mutations.

---

## 6️⃣ How to Improve Angular Performance?

**Spoken answer:**

* Use `OnPush` change detection
* Use `trackBy` in `*ngFor`
* Avoid heavy logic in templates
* Unsubscribe from observables
* Lazy load modules
* Run heavy tasks outside Angular

**Example (`trackBy`):**

```html
<li *ngFor="let item of items; trackBy: trackById">
  {{ item.name }}
</li>
```

```ts
trackById(index: number, item: any) {
  return item.id;
}
```

👉 Prevents unnecessary DOM re-rendering.

---

## 7️⃣ How to Troubleshoot Performance Issues?

**Spoken answer:**

* Use **Angular DevTools**

  * Detect excessive change detection
* Check for:

  * Too many subscriptions
  * Large lists without `trackBy`
* Look for:

  * Functions called in templates
  * Unnecessary re-renders
* Use browser Performance tab

**Bad example:**

```html
<p>{{ getValue() }}</p>
```

**Fix:**

```ts
value = this.calculateOnce();
```

👉 Template functions run **on every change detection cycle**.

---
* Provide **real-world project scenarios**

## 🔹 8. Modern Angular (Must-Know)

## 1️⃣ What are Standalone Components?

**Spoken answer (bullet style):**

* Standalone components are components that **don’t need NgModules**
* Everything the component needs is declared **inside the component itself**
* This makes Angular apps:

  * Easier to understand
  * Faster to bootstrap
  * More tree-shakable
* Introduced to simplify Angular and reduce boilerplate

**Example:**

```ts
@Component({
  selector: 'app-header',
  standalone: true,
  imports: [CommonModule],
  template: `<h1>Header</h1>`
})
export class HeaderComponent {}
```

**Bootstrap example:**

```ts
bootstrapApplication(AppComponent);
```

👉 No `AppModule` required.

---

## 2️⃣ What are Signals?

**Spoken answer:**

* Signals are a **new reactive state system** in Angular
* They represent **reactive values**
* When a signal changes, Angular:

  * Automatically updates only what depends on it
* Signals are:

  * Synchronous
  * Simple
  * Great for component state

**Example:**

```ts
import { signal } from '@angular/core';

count = signal(0);

increment() {
  this.count.set(this.count() + 1);
}
```

```html
<p>{{ count() }}</p>
<button (click)="increment()">+</button>
```

👉 No subscriptions, no async pipes.

---

## 3️⃣ Difference Between Signals and Observables

**Spoken answer:**

* Signals are for **local UI state**
* Observables are for **async streams**
* Signals:

  * Synchronous
  * Simple API
  * No unsubscribe needed
* Observables:

  * Asynchronous
  * Powerful operators
  * Need unsubscribe handling

**Example (Signal):**

```ts
name = signal('Angular');
```

**Example (Observable):**

```ts
name$ = this.http.get<string>('api/name');
```

```html
<p>{{ name$ | async }}</p>
```

👉 Signals replace simple state, not RxJS.

---

## 4️⃣ Advantages of Standalone Architecture

**Spoken answer:**

* Removes NgModule complexity
* Better performance and tree shaking
* Easier lazy loading
* Cleaner project structure
* Faster onboarding for new developers

**Lazy loading example:**

```ts
{
  path: 'profile',
  loadComponent: () =>
    import('./profile.component').then(m => m.ProfileComponent)
}
```

**Standalone component:**

```ts
@Component({
  standalone: true,
  imports: [CommonModule],
  template: `<h2>Profile</h2>`
})
export class ProfileComponent {}
```

👉 Cleaner routes, smaller bundles, simpler code.

---

For **Angular Interview Preparation**, study topics in this order:

### **1. Angular Fundamentals**

**What is Angular?**

**Angular** is a **TypeScript-based, open-source front-end framework** developed by **Google** for building **single-page applications (SPAs)** and dynamic web applications.

**Key Features:**

* **Component-based architecture**
* **Two-way data binding**
* **Dependency Injection (DI)**
* **Routing support**
* **Built-in form and HTTP modules**

**How it works:**

* The application is divided into **components**.
* Components interact with **templates** to display data.
* Angular updates the UI automatically when data changes.

**When to use:**

* Building **large-scale**, **maintainable**, and **enterprise-level** web applications.

**Example:**

```bash
ng new my-app
ng serve
```

---

**Angular Architecture**

**Angular Architecture** is based on **Modules**, **Components**, **Templates**, **Services**, and **Dependency Injection** working together to build an application.

**Main Parts:**

* **Modules** – Organize the application.
* **Components** – Control a part of the UI.
* **Templates** – Define the HTML view.
* **Services** – Contain reusable business logic.
* **Dependency Injection** – Provides services to components.

**How it works:**

* The **root module (`AppModule`)** bootstraps the application.
* Components render the UI using templates.
* Services share data and logic across components.

**When to use:**

* To create **modular**, **scalable**, and **easy-to-maintain** applications.

---

**Components**

A **Component** is the **basic building block** of an Angular application. It controls a section of the UI and contains **HTML, CSS, and TypeScript logic**.

**Key Features:**

* Reusable and independent.
* Encapsulates UI and business logic.
* Uses a template for rendering.

**How it works:**

* A component class holds data and methods.
* The template displays the data using data binding.
* Angular updates the view whenever the data changes.

**When to use:**

* Whenever you need to create a **reusable UI section**, like a header, login form, or dashboard card.

**Example:**

```typescript
import { Component } from '@angular/core';

@Component({
  selector: 'app-hello',
  template: `<h2>Hello Angular!</h2>`
})
export class HelloComponent {}
```

---

**Modules**

A **Module (`NgModule`)** is a container that groups related **components, directives, pipes, and services** into a single unit.

**Key Features:**

* Organizes application functionality.
* Improves maintainability.
* Supports **lazy loading** for better performance.

**How it works:**

* The **AppModule** is the root module.
* Modules declare components and import other required modules.

**When to use:**

* To separate large applications into **feature-based modules**.

**Example:**

```typescript
import { NgModule } from '@angular/core';

@NgModule({
  declarations: [HelloComponent],
  imports: [],
  bootstrap: [HelloComponent]
})
export class AppModule {}
```

---

**Templates**

A **Template** is the **HTML view** of an Angular component. It defines what the user sees on the screen.

**Key Features:**

* Supports **data binding**.
* Uses **directives** like `*ngIf` and `*ngFor`.
* Automatically updates when component data changes.

**How it works:**

* The template is linked to the component class.
* Angular binds component data to HTML elements.

**When to use:**

* To create dynamic and interactive user interfaces.

**Example:**

```html
<h2>{{ title }}</h2>
<button (click)="showMessage()">Click Me</button>
```

---

**Decorators**

**Decorators** are **special TypeScript annotations** that provide metadata to Angular classes and tell Angular how they should behave.

**Common Decorators:**

* **`@Component`** – Defines a component.
* **`@NgModule`** – Defines a module.
* **`@Injectable`** – Marks a class as a service for DI.
* **`@Input`** – Receives data from a parent component.
* **`@Output`** – Sends data to a parent component.

**How it works:**

* Angular reads the metadata provided by decorators during compilation and configures the application accordingly.

**When to use:**

* To define and configure **components, modules, services, and data communication**.

**Example:**

```typescript
import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-user',
  template: `<p>{{name}}</p>`
})
export class UserComponent {
  @Input() name!: string;
}
```

**Directives**

**Directives** are Angular classes that **modify the behavior or appearance of DOM elements**.

**Key Features:**

* Extend HTML functionality.
* Reusable and easy to maintain.
* Types: **Component Directives**, **Structural Directives** (`*ngIf`, `*ngFor`), and **Attribute Directives** (`ngClass`, `ngStyle`).

**How it works:**

* Angular applies directives to HTML elements and changes the DOM dynamically.

**When to use:**

* To **show/hide elements**, **loop through data**, or **change styles and behavior**.

**Example:**

```html
<div *ngIf="isLoggedIn">Welcome User!</div>

<li *ngFor="let item of items">
  {{ item }}
</li>
```

---

**Services**

A **Service** is a class used to store **reusable business logic, data access, or shared functionality**.

**Key Features:**

* Promotes **code reuse**.
* Keeps components lightweight.
* Commonly used for API calls and shared data.

**How it works:**

* Services are created once and injected into components using **Dependency Injection (DI)**.

**When to use:**

* When multiple components need the same logic or data.

**Example:**

```typescript
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  getUser() {
    return "John";
  }
}
```

---

**Dependency Injection (DI)**

**Dependency Injection** is a design pattern where Angular **automatically provides required objects (services) to a class**.

**Key Features:**

* Reduces tight coupling.
* Improves testability and maintainability.
* Built into Angular.

**How it works:**

* Angular creates the service instance and injects it through the component's constructor.

**When to use:**

* Whenever a component needs a **service or shared dependency**.

**Example:**

```typescript
constructor(private userService: UserService) {}

ngOnInit() {
  console.log(this.userService.getUser());
}
```

---

**Bootstrapping**

**Bootstrapping** is the process of **starting an Angular application** by loading the root module and root component.

**Key Features:**

* Initializes the application.
* Loads the **AppModule** and **AppComponent**.
* Entry point is `main.ts`.

**How it works:**

* Angular bootstraps `AppModule`, which then loads the root component into the browser.

**When to use:**

* Automatically happens when the application starts.

**Example:**

```typescript
platformBrowserDynamic()
  .bootstrapModule(AppModule);
```

---

**Data Binding**

**Data Binding** is the mechanism that **connects component data with the HTML template**.

**Key Features:**

* Keeps UI and data synchronized.
* Reduces manual DOM manipulation.
* Supports different binding types.

**Types of Data Binding:**

1. **Interpolation:** `{{ title }}`
2. **Property Binding:** `[src]="imageUrl"`
3. **Event Binding:** `(click)="save()"`
4. **Two-way Binding:** `[(ngModel)]="name"`

**How it works:**

* Angular automatically updates the UI when data changes and can update the component when the user interacts with the UI.

**When to use:**

* For displaying data and handling user input.

**Example:**

```html
<input [(ngModel)]="username">
<p>{{ username }}</p>
```

---

**Lifecycle Hooks**

**Lifecycle Hooks** are special Angular methods that allow you to **execute code at different stages of a component's lifecycle**.

**Common Hooks:**

* **`ngOnChanges()`** – Called when input data changes.
* **`ngOnInit()`** – Called once after component initialization.
* **`ngDoCheck()`** – Called during every change detection cycle.
* **`ngAfterViewInit()`** – Called after the view is initialized.
* **`ngOnDestroy()`** – Called before the component is destroyed.

**How it works:**

* Angular automatically invokes these methods at the appropriate stage.

**When to use:**

* For initialization, data loading, custom change detection, and cleanup tasks.

**Example:**

```typescript
import { OnInit, OnDestroy } from '@angular/core';

export class AppComponent implements OnInit, OnDestroy {

  ngOnInit() {
    console.log("Component Initialized");
  }

  ngOnDestroy() {
    console.log("Component Destroyed");
  }
}
```

---

**Constructor vs `ngOnInit`**

| **Constructor**                                      | **`ngOnInit()`**                                |
| ---------------------------------------------------- | ----------------------------------------------- |
| A **TypeScript** feature.                            | An **Angular Lifecycle Hook**.                  |
| Used for **Dependency Injection**.                   | Used for **component initialization logic**.    |
| Called **before Angular initializes the component**. | Called **after Angular sets input properties**. |
| Avoid heavy logic here.                              | Best place for API calls and initialization.    |

**How it works:**

* The **constructor** creates the component instance and injects dependencies.
* **`ngOnInit()`** runs after Angular finishes setting up the component.

**When to use:**

* **Constructor:** Inject services.
* **`ngOnInit()`:** Fetch data, call APIs, and initialize variables.

**Example:**

```typescript
export class UserComponent implements OnInit {

  constructor(private userService: UserService) {
    console.log("Constructor Called");
  }

  ngOnInit(): void {
    console.log("ngOnInit Called");
    this.userService.getUser();
  }
}
```


### **2. Templates & Directives**

1. `ngIf`
2. `ngFor`
3. `@if`, `@for` (Angular 17+)
4. `ngSwitch`
5. `ngClass`
6. `ngStyle`
7. Template Reference Variables
8. `ng-template`
9. `ng-container`
10. `ng-content`
11. Custom Directives
12. `trackBy`
13. Pipes (Pure vs Impure)


### **3. Forms**

1. Template-Driven Forms
2. Reactive Forms
3. `ngModel`
4. FormGroup
5. FormControl
6. FormBuilder
7. Validators
8. Custom Validators
9. Async Validators
10. Dynamic Forms


### **4. Routing & Navigation**

1. Angular Router
2. RouterModule
3. Route Parameters
4. Query Parameters
5. Child Routes
6. Lazy Loading
7. Route Guards
8. AuthGuard
9. Resolvers
10. Authentication vs Authorization


### **5. HTTP & Backend Integration**

1. HttpClient
2. GET, POST, PUT, DELETE
3. HTTP Headers
4. Query Parameters
5. Error Handling
6. Interceptors
7. JWT Authentication
8. File Upload
9. CORS


### **6. RxJS**

1. Observable
2. Observer
3. Subject
4. BehaviorSubject
5. ReplaySubject
6. AsyncSubject
7. Subscription
8. Operators (`map`, `filter`, `switchMap`, `mergeMap`, `concatMap`)
9. `forkJoin`
10. `combineLatest`
11. `debounceTime`
12. `distinctUntilChanged`


### **7. Component Communication**

1. `@Input`
2. `@Output`
3. EventEmitter
4. ViewChild
5. ContentChild
6. Parent → Child Communication
7. Child → Parent Communication
8. Sibling Communication
9. Shared Service Communication


### **8. Dependency Injection (Advanced)**

1. Injector
2. Provider
3. Injection Token
4. Hierarchical DI
5. Singleton Services
6. Multi Providers


### **9. Change Detection**

1. Change Detection Strategy
2. Default Strategy
3. OnPush Strategy
4. Zone.js
5. Manual Change Detection
6. Signals


### **10. Angular Signals (Important)**

1. `signal()`
2. `computed()`
3. `effect()`
4. Signal vs Observable
5. Signal-based State Management


### **11. State Management**

1. Services + BehaviorSubject
2. Signals Store
3. NgRx Basics
4. Store
5. Actions
6. Reducers
7. Effects
8. Selectors


### **12. Performance Optimization**

1. Lazy Loading
2. Preloading Strategy
3. `trackBy`
4. OnPush
5. Memoization
6. Tree Shaking
7. Bundle Optimization
8. SSR
9. Hydration


### **13. Angular Security**

1. XSS
2. CSRF
3. Sanitization
4. JWT Storage
5. Route Protection
6. Secure HTTP Calls


### **14. Testing**

1. Jasmine
2. Karma
3. Unit Testing
4. Component Testing
5. Service Testing
6. Mocking Dependencies
7. TestBed


### **15. Angular Build & Deployment**

1. Angular CLI
2. Environment Files
3. Build Configurations
4. Production Build
5. AOT Compilation
6. Ahead-of-Time vs JIT
7. Deployment Strategies


### **16. Angular 17+ Features**

1. Standalone Components
2. Standalone Routing
3. Signals
4. Control Flow (`@if`, `@for`, `@switch`)
5. Deferrable Views (`@defer`)
6. SSR & Hydration


### **17. Angular Architecture & System Design**

1. Folder Structure
2. Feature Modules
3. Shared Modules
4. Core Modules
5. Reusable Components
6. Micro Frontends
7. Large Scale Angular Architecture


### **18. Real-Time Interview Scenarios**

1. Auth Flow Implementation
2. API Caching
3. Infinite Scrolling
4. Search with Debounce
5. File Upload
6. Dynamic Forms
7. Role-Based Access Control (RBAC)
8. Dashboard Design
9. Performance Optimization Cases



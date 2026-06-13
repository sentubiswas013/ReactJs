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

* `ngIf`
* `ngFor`
* `@if`, `@for` (Angular 17+)
* `ngSwitch`
* `ngClass`
* `ngStyle`
* Template Reference Variables
* `ng-template`
* `ng-container`
* `ng-content`
* Custom Directives
* `trackBy`
* Pipes (Pure vs Impure)

### **3. Forms**

* Template-Driven Forms
* Reactive Forms
* `ngModel`
* FormGroup
* FormControl
* FormBuilder
* Validators
* Custom Validators
* Async Validators
* Dynamic Forms

### **4. Routing & Navigation**

* Angular Router
* RouterModule
* Route Parameters
* Query Parameters
* Child Routes
* Lazy Loading
* Route Guards
* AuthGuard
* Resolvers
* Authentication vs Authorization

### **5. HTTP & Backend Integration**

* HttpClient
* GET, POST, PUT, DELETE
* HTTP Headers
* Query Parameters
* Error Handling
* Interceptors
* JWT Authentication
* File Upload
* CORS

### **6. RxJS**

* Observable
* Observer
* Subject
* BehaviorSubject
* ReplaySubject
* AsyncSubject
* Subscription
* Operators (`map`, `filter`, `switchMap`, `mergeMap`, `concatMap`)
* `forkJoin`
* `combineLatest`
* `debounceTime`
* `distinctUntilChanged`

### **7. Component Communication**

* `@Input`
* `@Output`
* EventEmitter
* ViewChild
* ContentChild
* Parent → Child Communication
* Child → Parent Communication
* Sibling Communication
* Shared Service Communication

### **8. Dependency Injection (Advanced)**

* Injector
* Provider
* Injection Token
* Hierarchical DI
* Singleton Services
* Multi Providers

### **9. Change Detection**

* Change Detection Strategy
* Default Strategy
* OnPush Strategy
* Zone.js
* Manual Change Detection
* Signals

### **10. Angular Signals (Important)**

* `signal()`
* `computed()`
* `effect()`
* Signal vs Observable
* Signal-based State Management

### **11. State Management**

* Services + BehaviorSubject
* Signals Store
* NgRx Basics
* Store
* Actions
* Reducers
* Effects
* Selectors

### **12. Performance Optimization**

* Lazy Loading
* Preloading Strategy
* `trackBy`
* OnPush
* Memoization
* Tree Shaking
* Bundle Optimization
* SSR
* Hydration

### **13. Angular Security**

* XSS
* CSRF
* Sanitization
* JWT Storage
* Route Protection
* Secure HTTP Calls

### **14. Testing**

* Jasmine
* Karma
* Unit Testing
* Component Testing
* Service Testing
* Mocking Dependencies
* TestBed

### **15. Angular Build & Deployment**

* Angular CLI
* Environment Files
* Build Configurations
* Production Build
* AOT Compilation
* Ahead-of-Time vs JIT
* Deployment Strategies

### **16. Angular 17+ Features**

* Standalone Components
* Standalone Routing
* Signals
* Control Flow (`@if`, `@for`, `@switch`)
* Deferrable Views (`@defer`)
* SSR & Hydration

### **17. Angular Architecture & System Design**

* Folder Structure
* Feature Modules
* Shared Modules
* Core Modules
* Reusable Components
* Micro Frontends
* Large Scale Angular Architecture

### **18. Real-Time Interview Scenarios**

* Auth Flow Implementation
* API Caching
* Infinite Scrolling
* Search with Debounce
* File Upload
* Dynamic Forms
* Role-Based Access Control (RBAC)
* Dashboard Design
* Performance Optimization Cases


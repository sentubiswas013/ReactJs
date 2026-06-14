For **Angular Interview Preparation**, study topics in this order:

# ✅ **1. Angular Fundamentals**

### **1. What is Angular?**

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

### **2. Angular Architecture**

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

### **3. Components**

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

### **4. Modules**

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

### **5. Templates**

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

### **6. Decorators**

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

### **7. Directives**

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

### **8. Services**

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

### **9. Dependency Injection (DI)**

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

### **10. Bootstrapping**

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

### **11. Data Binding**

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

### **12. Lifecycle Hooks**

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

### **13. Constructor vs `ngOnInit`**

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


# ✅ **2. Templates & Directives**

### **1. `ngIf`**

**`ngIf`** is a **structural directive** used to **conditionally display or remove an element** from the DOM.

**Key Features:**

* Shows or hides elements based on a condition.
* Removes the element from the DOM when `false`.
* Improves performance by rendering only required content.

**How it works:**

* Angular evaluates the condition and creates or destroys the element.

**When to use:**

* When content should appear only under specific conditions, like login status or data availability.

**Example:**

```html
<div *ngIf="isLoggedIn">
  Welcome User!
</div>
```

### **2. `ngFor`**

**`ngFor`** is a **structural directive** used to **repeat an element for each item in a collection**.

**Key Features:**

* Iterates through arrays or lists.
* Dynamically creates HTML elements.
* Supports `index`, `first`, `last`, `even`, and `odd`.

**How it works:**

* Angular loops through the collection and renders one element for each item.

**When to use:**

* To display lists such as users, products, or menu items.

**Example:**

```html
<ul>
  <li *ngFor="let user of users">
    {{ user }}
  </li>
</ul>
```

### **3. `@if`, `@for` (Angular 17+)**

**`@if`** and **`@for`** are the **new built-in control flow statements** introduced in **Angular 17** as a cleaner replacement for `*ngIf` and `*ngFor`.

**Key Features:**

* Modern and more readable syntax.
* Better performance and optimized rendering.
* No need to use the `*` symbol.

**How it works:**

* Angular compiles these control flow blocks directly for efficient rendering.

**When to use:**

* In **Angular 17+** applications for cleaner and more maintainable templates.

**Example:**

```html
@if (isLoggedIn) {
  <p>Welcome User!</p>
}

@for (user of users; track user.id) {
  <p>{{ user.name }}</p>
}
```

### **4. `ngSwitch`**

**`ngSwitch`** is a **structural directive** used to **display one block of content from multiple conditions**.

**Key Features:**

* Works like the Java `switch-case` statement.
* Improves readability over multiple `ngIf` conditions.
* Uses `ngSwitchCase` and `ngSwitchDefault`.

**How it works:**

* Angular compares the expression value and renders the matching case.

**When to use:**

* When handling multiple conditions, such as user roles or application states.

**Example:**

```html
<div [ngSwitch]="role">
  <p *ngSwitchCase="'admin'">Admin Panel</p>
  <p *ngSwitchCase="'user'">User Panel</p>
  <p *ngSwitchDefault>Guest Panel</p>
</div>
```

### **5. `ngClass`**

**`ngClass`** is an **attribute directive** used to **dynamically add or remove CSS classes**.

**Key Features:**

* Applies classes based on conditions.
* Supports strings, arrays, and objects.
* Makes styling flexible and reusable.

**How it works:**

* Angular evaluates the expression and updates the CSS classes of the element.

**When to use:**

* For conditional styling, such as highlighting active items or validation errors.

**Example:**

```html
<p [ngClass]="{'active': isActive}">
  Angular Example
</p>
```

### **6. `ngStyle`**

**`ngStyle`** is an **attribute directive** used to **apply inline CSS styles dynamically**.

**Key Features:**

* Changes styles at runtime.
* Accepts a JavaScript object of CSS properties.
* Useful for dynamic UI customization.

**How it works:**

* Angular evaluates the style object and applies the styles to the element.

**When to use:**

* When styles need to change based on data or user interaction.

**Example:**

```html
<p [ngStyle]="{'color': textColor, 'font-size': fontSize}">
  Dynamic Styling
</p>
```

### **7. Template Reference Variables**

**Template Reference Variables (`#`)** are local variables used to **reference an HTML element, Angular component, or directive directly in the template**.

**Key Features:**

* Declared using the `#` symbol.
* Accessible only within the same template.
* No need to use component code for simple DOM access.

**How it works:**

* Angular creates a local reference that can be used in bindings or event handlers.

**When to use:**

* To access input values or call methods on child components directly from the template.

**Example:**

```html id="9d7gk1"
<input #userName>
<button (click)="show(userName.value)">Submit</button>
```

### **8. `ng-template`**

**`ng-template`** is a special Angular element used to **define a block of HTML that is not rendered until needed**.

**Key Features:**

* Content is not displayed by default.
* Used with structural directives like `ngIf`.
* Helps create reusable template blocks.

**How it works:**

* Angular stores the template and renders it only when instructed.

**When to use:**

* For conditional or reusable UI sections.

**Example:**

```html id="2fj8nm"
<div *ngIf="isLoggedIn; else loginBlock">
  Welcome User!
</div>

<ng-template #loginBlock>
  Please Login.
</ng-template>
```

### **9. `ng-container`**

**`ng-container`** is a logical container that **groups elements without adding an extra HTML element to the DOM**.

**Key Features:**

* Does not create a real DOM element.
* Useful with structural directives.
* Keeps the HTML structure clean.

**How it works:**

* Angular processes the directives inside `ng-container` but does not render the container itself.

**When to use:**

* When you need to apply directives without adding unnecessary wrapper tags.

**Example:**

```html id="v7r3lm"
<ng-container *ngIf="isLoggedIn">
  <h3>Welcome</h3>
  <p>User Dashboard</p>
</ng-container>
```

### **10. `ng-content`**

**`ng-content`** is used for **content projection**, allowing a parent component to pass HTML content into a child component.

**Key Features:**

* Similar to slots in Web Components.
* Makes components reusable and flexible.
* Supports single and multiple content projection.

**How it works:**

* Angular inserts the content placed between the child component tags into the `ng-content` location.

**When to use:**

* When building reusable components like cards, dialogs, or layouts.

**Example:**

```html id="4y2wkc"
<!-- Child Component -->
<div class="card">
  <ng-content></ng-content>
</div>

<!-- Parent Component -->
<app-card>
  <h2>Hello Angular</h2>
</app-card>
```

### **11. Custom Directives**

**Custom Directives** are user-defined directives used to **add custom behavior to HTML elements**.

**Key Features:**

* Extend existing HTML functionality.
* Promote code reuse.
* Can be structural or attribute directives.

**How it works:**

* Create a directive class using the `@Directive` decorator and apply it to an element.

**When to use:**

* When the same UI behavior is needed in multiple places.

**Example:**

```typescript id="5aqm9z"
import { Directive, ElementRef } from '@angular/core';

@Directive({
  selector: '[appHighlight]'
})
export class HighlightDirective {
  constructor(el: ElementRef) {
    el.nativeElement.style.backgroundColor = 'yellow';
  }
}
```

```html id="r8v4np"
<p appHighlight>Highlighted Text</p>
```

### **12. `trackBy`**

**`trackBy`** is a function used with **`ngFor`** to help Angular **identify list items uniquely**, improving rendering performance.

**Key Features:**

* Avoids unnecessary DOM recreation.
* Improves performance for large lists.
* Uses a unique identifier like `id`.

**How it works:**

* Angular tracks items by the value returned from the `trackBy` function instead of object references.

**When to use:**

* When displaying or updating large dynamic lists.

**Example:**

```html id="c6f1pt"
<li *ngFor="let user of users; trackBy: trackById">
  {{ user.name }}
</li>
```

```typescript id="g1p5mu"
trackById(index: number, user: any) {
  return user.id;
}
```

### **13. Pipes (Pure vs Impure)**

**Pipes** are used to **transform data before displaying it in the template**.

**Key Features:**

* Improve code readability.
* Reusable and easy to maintain.
* Types: **Pure Pipes** and **Impure Pipes**.

**How it works:**

* Angular passes data through a pipe and displays the transformed output.

**Pure Pipe:**

* Runs **only when the input value changes**.
* Better performance.
* Default behavior in Angular.

**Impure Pipe:**

* Runs during **every change detection cycle**.
* Detects changes inside objects and arrays.
* Lower performance compared to pure pipes.

**When to use:**

* **Pure Pipes:** For immutable data and better performance.
* **Impure Pipes:** When object or array contents change frequently without changing the reference.

**Example:**

```html id="w9e3sk"
<p>{{ today | date:'dd/MM/yyyy' }}</p>
<p>{{ name | uppercase }}</p>
```

```typescript id="j4t2bx"
@Pipe({
  name: 'customPipe',
  pure: false
})
export class CustomPipe {}
```

# ✅ **3. Forms**

### **1. Template-Driven Forms**

**Template-Driven Forms** are forms where **most of the form logic is handled in the HTML template** using Angular directives.

**Key Features:**

* Easy to create and use.
* Uses **`ngModel`** for data binding.
* Requires less TypeScript code.
* Suitable for simple forms.

**How it works:**

* Angular automatically creates and manages the form model from the template.

**When to use:**

* For **simple forms** like login, registration, or contact forms.

**Example:**

```html id="9xw1kb"
<form #userForm="ngForm">
  <input type="text" name="username" [(ngModel)]="username">
</form>
```

### **2. Reactive Forms**

**Reactive Forms** are forms where **the form structure and logic are defined in the TypeScript component class**.

**Key Features:**

* More scalable and maintainable.
* Supports complex validations.
* Provides better testing and control.
* Uses **`FormGroup`** and **`FormControl`**.

**How it works:**

* The component creates the form model, and the template binds to it.

**When to use:**

* For **complex forms** with dynamic fields and custom validations.

**Example:**

```typescript id="7v3mcn"
userForm = new FormGroup({
  username: new FormControl('')
});
```

```html id="w2n8pz"
<form [formGroup]="userForm">
  <input formControlName="username">
</form>
```

### **3. `ngModel`**

**`ngModel`** is a directive used for **two-way data binding** between a form field and a component property.

**Key Features:**

* Synchronizes data between UI and component.
* Mainly used in **Template-Driven Forms**.
* Reduces manual event handling.

**How it works:**

* Updates the component property when the input changes and updates the UI when the property changes.

**When to use:**

* For simple forms requiring automatic data synchronization.

**Example:**

```html id="4f7jmr"
<input [(ngModel)]="username">
<p>{{ username }}</p>
```

### **4. FormGroup**

**`FormGroup`** is a class that **groups multiple `FormControl` objects into a single form model**.

**Key Features:**

* Manages the state of a complete form.
* Tracks validation and form values.
* Supports nested groups.

**How it works:**

* It holds a collection of `FormControl` instances and manages them as one unit.

**When to use:**

* In **Reactive Forms** to organize and manage multiple form fields.

**Example:**

```typescript id="3h8qyt"
userForm = new FormGroup({
  username: new FormControl(''),
  email: new FormControl('')
});
```

### **5. FormControl**

**`FormControl`** is a class that **represents and manages a single form input field**.

**Key Features:**

* Stores the value and validation state.
* Tracks changes and user interactions.
* Supports synchronous and asynchronous validation.

**How it works:**

* Each input field is connected to a `FormControl` object that manages its data and status.

**When to use:**

* In **Reactive Forms** to control individual form fields.

**Example:**

```typescript id="8p6zlv"
nameControl = new FormControl('');
```

```html id="n5d2kw"
<input [formControl]="nameControl">
```

### **6. FormBuilder**

**`FormBuilder`** is an Angular service that **simplifies the creation of Reactive Forms** by reducing boilerplate code.

**Key Features:**

* Creates `FormGroup`, `FormControl`, and `FormArray` easily.
* Makes code shorter and cleaner.
* Supports validators directly.

**How it works:**

* Angular injects the `FormBuilder` service, which is used to build the form model.

**When to use:**

* When working with **Reactive Forms**, especially for forms with many fields.

**Example:**

```typescript id="2m8vkp"
constructor(private fb: FormBuilder) {}

userForm = this.fb.group({
  username: [''],
  email: ['']
});
```

### **7. Validators**

**Validators** are built-in Angular functions used to **check whether form data is valid**.

**Key Features:**

* Supports common validations like `required`, `minLength`, and `email`.
* Improves data quality.
* Works with Reactive and Template-Driven Forms.

**How it works:**

* Angular checks the input value against the specified validation rules and updates the form status.

**When to use:**

* To validate user input before form submission.

**Example:**

```typescript id="9r4jtw"
userForm = new FormGroup({
  email: new FormControl('', [
    Validators.required,
    Validators.email
  ])
});
```

### **8. Custom Validators**

**Custom Validators** are user-defined functions used to **implement validation rules that are not provided by Angular**.

**Key Features:**

* Reusable validation logic.
* Supports business-specific rules.
* Works with Reactive Forms.

**How it works:**

* The validator function returns `null` if valid or an error object if invalid.

**When to use:**

* When built-in validators are not sufficient, such as password strength or age validation.

**Example:**

```typescript id="k6n1hy"
import { AbstractControl } from '@angular/forms';

export function noSpaceValidator(
  control: AbstractControl
) {
  return control.value.includes(' ')
    ? { noSpace: true }
    : null;
}
```

```typescript id="4b2xze"
username: new FormControl('', [noSpaceValidator])
```

### **9. Async Validators**

**Async Validators** are validators that **perform validation asynchronously**, usually by calling a server or database.

**Key Features:**

* Supports API-based validation.
* Returns `Promise` or `Observable`.
* Useful for checking unique values.

**How it works:**

* Angular waits for the asynchronous operation to complete before updating the validation status.

**When to use:**

* To validate data against the server, such as checking whether a username or email already exists.

**Example:**

```typescript id="t7p3vf"
username: new FormControl(
  '',
  [],
  [this.userService.checkUsername]
)
```

### **10. Dynamic Forms**

**Dynamic Forms** are forms where **form fields are created or modified at runtime based on data or user actions**.

**Key Features:**

* Flexible and reusable.
* Supports adding or removing fields dynamically.
* Commonly uses `FormArray`.

**How it works:**

* Angular builds the form structure programmatically using `FormGroup`, `FormControl`, and `FormArray`.

**When to use:**

* For forms with variable fields, such as surveys, questionnaires, or multiple address entries.

**Example:**

```typescript id="g8c5mr"
userForm = this.fb.group({
  skills: this.fb.array([
    this.fb.control('')
  ])
});
```

```typescript id="u1w9qd"
get skills() {
  return this.userForm.get('skills');
}
```


# ✅ **4. Routing & Navigation**

### **1. Angular Router**

**Angular Router** is a built-in feature that enables **navigation** between different views or components in a **Single Page Application (SPA)** without reloading the page.

**Key Features:**

* **URL-based navigation**
* Supports **lazy loading**
* Handles **route guards** and **route parameters**
* Enables **nested (child) routes**

**How it works:**

* The user navigates to a URL.
* The **Router** matches the URL with a configured route.
* The corresponding **component** is loaded inside `<router-outlet>`.

**When to use:**

* When your application has **multiple pages or views**.
* To provide a smooth **SPA navigation experience**.

**Code Example:**

```typescript
const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'about', component: AboutComponent }
];
```

```html
<a routerLink="/home">Home</a>
<router-outlet></router-outlet>
```

### **2. RouterModule**

**RouterModule** is an Angular module that provides the **routing infrastructure** and registers route configurations.

**Key Features:**

* Configures application routes.
* Provides routing directives like **`routerLink`** and **`router-outlet`**.
* Supports **root** and **feature module** routing.

**How it works:**

* Import `RouterModule.forRoot()` in the root module.
* Import `RouterModule.forChild()` in feature modules.

**When to use:**

* **`forRoot()`** for the main application routing.
* **`forChild()`** for lazy-loaded or feature modules.

**Code Example:**

```typescript
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
```

### **3. Route Parameters**

**Route Parameters** are dynamic values passed as part of the **URL path** to identify a specific resource.

**Key Features:**

* Passed directly in the URL.
* Used for dynamic pages like **user details** or **product details**.
* Accessed using **`ActivatedRoute`**.

**How it works:**

* Define a parameter using `:parameterName`.
* Read the value from `ActivatedRoute`.

**When to use:**

* When the page needs a unique identifier like **ID**.

**Code Example:**

```typescript
const routes: Routes = [
  { path: 'user/:id', component: UserComponent }
];
```

```typescript
constructor(private route: ActivatedRoute) {}

ngOnInit() {
  const id = this.route.snapshot.paramMap.get('id');
}
```

**Example URL:** `localhost:4200/user/101`

### **4. Query Parameters**

**Query Parameters** are optional key-value pairs added after `?` in the URL to pass additional information.

**Key Features:**

* Do not affect route matching.
* Useful for **filtering**, **sorting**, and **searching**.
* Can be accessed using **`ActivatedRoute`**.

**How it works:**

* Pass query parameters during navigation.
* Retrieve them using `queryParamMap`.

**When to use:**

* For optional data like **search text**, **page number**, or **sort order**.

**Code Example:**

```typescript
this.router.navigate(['/products'], {
  queryParams: { category: 'mobile', page: 1 }
});
```

```typescript
const category =
  this.route.snapshot.queryParamMap.get('category');
```

**Example URL:** `localhost:4200/products?category=mobile&page=1`

### **5. Child Routes**

**Child Routes** allow you to define **nested routes** inside a parent route, making it easy to organize related pages.

**Key Features:**

* Supports **nested navigation**.
* Parent and child components can be displayed together.
* Improves application structure.

**How it works:**

* Define a parent route with a **`children`** array.
* Add a `<router-outlet>` inside the parent component template.

**When to use:**

* When multiple pages belong to the same section, such as **Dashboard**, **Admin**, or **User Profile**.

**Code Example:**

```typescript
const routes: Routes = [
  {
    path: 'dashboard',
    component: DashboardComponent,
    children: [
      { path: 'profile', component: ProfileComponent },
      { path: 'settings', component: SettingsComponent }
    ]
  }
];
```

```html
<!-- dashboard.component.html -->
<h2>Dashboard</h2>
<router-outlet></router-outlet>
```

**Example URLs:**

* `localhost:4200/dashboard/profile`
* `localhost:4200/dashboard/settings`

### **6. Lazy Loading**

**Lazy Loading** is an Angular feature that loads a **module only when it is needed**, instead of loading the entire application at startup.

**Key Features:**

* Improves **application performance**.
* Reduces **initial loading time**.
* Loads feature modules **on demand**.

**How it works:**

* Angular loads the module only when the user navigates to its route using **`loadChildren`**.

**When to use:**

* For **large applications** with multiple feature modules.
* To optimize startup performance.

**Code Example:**

```typescript
const routes: Routes = [
  {
    path: 'admin',
    loadChildren: () =>
      import('./admin/admin.module')
        .then(m => m.AdminModule)
  }
];
```

---

### **7. Route Guards**

**Route Guards** are Angular services that control whether a user can **access**, **leave**, or **load** a route.

**Key Features:**

* Protects routes from unauthorized access.
* Supports different guard types like **`CanActivate`**, **`CanDeactivate`**, **`CanLoad`**, and **`CanActivateChild`**.
* Returns **`true`**, **`false`**, or a **`UrlTree`**.

**How it works:**

* Before navigation, Angular calls the guard.
* If the guard returns `true`, navigation continues; otherwise, it is blocked or redirected.

**When to use:**

* To secure pages, prevent data loss, or control module loading.

**Code Example:**

```typescript
const routes: Routes = [
  {
    path: 'dashboard',
    component: DashboardComponent,
    canActivate: [AuthGuard]
  }
];
```

---

### **8. AuthGuard**

**AuthGuard** is a common **Route Guard** used to check whether a user is **logged in** before allowing access to a route.

**Key Features:**

* Implements **`CanActivate`**.
* Verifies user authentication status.
* Redirects unauthenticated users to the **login page**.

**How it works:**

* The guard checks if the user has a valid login session or token.
* If authenticated, it returns `true`; otherwise, it redirects to `/login`.

**When to use:**

* To protect pages like **Dashboard**, **Profile**, or **Admin Panel**.

**Code Example:**

```typescript
@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate {

  canActivate(): boolean {
    return !!localStorage.getItem('token');
  }
}
```

```typescript
{
  path: 'dashboard',
  component: DashboardComponent,
  canActivate: [AuthGuard]
}
```

---

### **9. Resolvers**

**Resolvers** are Angular services that **fetch data before a route is activated**, ensuring the component receives the required data immediately.

**Key Features:**

* Loads data before navigation completes.
* Improves user experience by avoiding empty screens.
* Implements the **`Resolve`** interface.

**How it works:**

* Angular executes the resolver before opening the route.
* The resolved data is available through **`ActivatedRoute`**.

**When to use:**

* When a page requires data before rendering, such as **user details** or **product information**.

**Code Example:**

```typescript
@Injectable({ providedIn: 'root' })
export class UserResolver implements Resolve<any> {
  resolve() {
    return this.userService.getUsers();
  }
}
```

```typescript
{
  path: 'users',
  component: UserComponent,
  resolve: { users: UserResolver }
}
```

```typescript
this.route.data.subscribe(data => {
  console.log(data['users']);
});
```

---

### **10. Authentication vs Authorization**

| **Authentication**                       | **Authorization**                                  |
| ---------------------------------------- | -------------------------------------------------- |
| Verifies **who the user is**.            | Verifies **what the user can access**.             |
| Happens **first**.                       | Happens **after authentication**.                  |
| Checks **username/password or token**.   | Checks **roles and permissions**.                  |
| Example: User logs into the application. | Example: Only **Admin** can access the Admin page. |

**Key Difference:**

* **Authentication** = **Identity verification**.
* **Authorization** = **Access control**.

**When to use:**

* Use **Authentication** to validate the user.
* Use **Authorization** to control access to resources based on roles or permissions.

**Code Example:**

```typescript
// Authentication
if (userLoggedIn) {
  // Authorization
  if (user.role === 'ADMIN') {
    // Allow access
  }
}
```


# ✅ **5. HTTP & Backend Integration**

### **1. HttpClient**

**HttpClient** is an Angular service used to communicate with a **backend API** and perform HTTP requests such as **GET**, **POST**, **PUT**, and **DELETE**.

**Key Features:**

* Supports all **HTTP methods**.
* Returns data as **RxJS Observables**.
* Supports **headers**, **query parameters**, and **error handling**.
* Easy integration with REST APIs.

**How it works:**

* Import `HttpClientModule`.
* Inject `HttpClient` into a service or component.
* Call methods like `get()`, `post()`, etc.

**When to use:**

* When fetching or sending data between the Angular application and a server.

**Code Example:**

```typescript
import { HttpClient } from '@angular/common/http';

constructor(private http: HttpClient) {}

getUsers() {
  return this.http.get('https://api.example.com/users');
}
```

### **2. GET, POST, PUT, DELETE**

These are the most common **HTTP methods** used to perform **CRUD (Create, Read, Update, Delete)** operations.

| **Method** | **Purpose**          |
| ---------- | -------------------- |
| **GET**    | Retrieve data        |
| **POST**   | Create new data      |
| **PUT**    | Update existing data |
| **DELETE** | Remove data          |

**Key Features:**

* Used for REST API communication.
* Each method represents a specific operation.
* Supported directly by `HttpClient`.

**How it works:**

* Angular sends the appropriate HTTP request to the backend.
* The server processes the request and returns a response.

**When to use:**

* **GET** for reading data.
* **POST** for adding records.
* **PUT** for updating records.
* **DELETE** for removing records.

**Code Example:**

```typescript
// GET
this.http.get('/api/users');

// POST
this.http.post('/api/users', user);

// PUT
this.http.put('/api/users/1', user);

// DELETE
this.http.delete('/api/users/1');
```

### **3. HTTP Headers**

**HTTP Headers** are additional information sent with an HTTP request or response, such as **authentication tokens** or **content type**.

**Key Features:**

* Send **Authorization** tokens.
* Specify **Content-Type**.
* Add custom request metadata.

**How it works:**

* Create an `HttpHeaders` object.
* Pass it as part of the request options.

**When to use:**

* When sending **JWT tokens**, API keys, or custom headers.

**Code Example:**

```typescript
import { HttpHeaders } from '@angular/common/http';

const headers = new HttpHeaders({
  'Authorization': 'Bearer token123',
  'Content-Type': 'application/json'
});

this.http.get('/api/users', { headers });
```

### **4. Query Parameters**

**Query Parameters** are key-value pairs added to the URL to send optional data to the server.

**Key Features:**

* Used for **searching**, **filtering**, and **pagination**.
* Keeps the URL dynamic and readable.
* Managed using **`HttpParams`**.

**How it works:**

* Create an `HttpParams` object.
* Add parameters and include them in the request.

**When to use:**

* When sending optional values like **page number**, **search text**, or **filters**.

**Code Example:**

```typescript
import { HttpParams } from '@angular/common/http';

const params = new HttpParams()
  .set('page', '1')
  .set('search', 'angular');

this.http.get('/api/users', { params });
```

**Example URL:**
`https://api.example.com/users?page=1&search=angular`

### **5. Error Handling**

**Error Handling** is the process of catching and managing errors that occur during HTTP requests.

**Key Features:**

* Handles server and network errors.
* Uses RxJS **`catchError()`** operator.
* Prevents application crashes.

**How it works:**

* Pipe the HTTP request through `catchError()`.
* Return a user-friendly error message or fallback value.

**When to use:**

* For all API calls to handle failures gracefully.

**Code Example:**

```typescript
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

getUsers() {
  return this.http.get('/api/users').pipe(
    catchError(error => {
      console.log('Error:', error);
      return throwError(() => error);
    })
  );
}
```

**Common Errors:**

* **400** – Bad Request
* **401** – Unauthorized
* **404** – Not Found
* **500** – Internal Server Error

### **6. Interceptors**

**Interceptors** are Angular services that **intercept and modify HTTP requests and responses** before they reach the server or the application.

**Key Features:**

* Add **authentication tokens** automatically.
* Handle **global error logging**.
* Modify requests or responses centrally.
* Reduce duplicate HTTP-related code.

**How it works:**

* Implement the **`HttpInterceptor`** interface.
* Angular executes the interceptor for every HTTP request.

**When to use:**

* To add **JWT tokens**, log requests, or handle common API errors globally.

**Code Example:**

```typescript
import { HttpInterceptor } from '@angular/common/http';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  intercept(req: any, next: any) {
    const modifiedReq = req.clone({
      setHeaders: {
        Authorization: 'Bearer token123'
      }
    });

    return next.handle(modifiedReq);
  }
}
```

```typescript
providers: [
  {
    provide: HTTP_INTERCEPTORS,
    useClass: AuthInterceptor,
    multi: true
  }
]
```

### **7. JWT Authentication**

**JWT (JSON Web Token) Authentication** is a secure method where the server sends a **token** after successful login, and the client sends it with every protected request.

**Key Features:**

* **Stateless authentication**.
* Token contains encoded user information.
* Commonly sent in the **Authorization** header.
* Works well with REST APIs.

**How it works:**

1. User logs in with credentials.
2. Server validates the user and returns a **JWT token**.
3. Angular stores the token (usually in `localStorage` or `sessionStorage`).
4. An **Interceptor** attaches the token to future requests.

**When to use:**

* For secure authentication in **Single Page Applications (SPA)** and REST APIs.

**Code Example:**

```typescript
localStorage.setItem('token', response.token);
```

```typescript
const headers = new HttpHeaders({
  Authorization: `Bearer ${localStorage.getItem('token')}`
});

this.http.get('/api/users', { headers });
```

### **8. File Upload**

**File Upload** is the process of sending files from the Angular application to the backend server.

**Key Features:**

* Supports uploading **images**, **documents**, and other files.
* Uses **`FormData`** to send multipart data.
* Can track upload progress.

**How it works:**

* Select a file using an HTML input.
* Add the file to a `FormData` object.
* Send it using `HttpClient.post()`.

**When to use:**

* When users need to upload profile pictures, PDFs, or attachments.

**Code Example:**

```html
<input type="file" (change)="onFileSelected($event)">
```

```typescript
onFileSelected(event: any) {
  const file = event.target.files[0];
  const formData = new FormData();
  formData.append('file', file);

  this.http.post('/api/upload', formData)
    .subscribe(response => console.log(response));
}
```

### **9. CORS**

**CORS (Cross-Origin Resource Sharing)** is a browser security mechanism that controls whether a frontend application can access resources from a different domain.

**Key Features:**

* Prevents unauthorized cross-origin requests.
* Controlled by the **backend server**.
* Uses HTTP headers like **`Access-Control-Allow-Origin`**.

**How it works:**

* When Angular calls an API from another domain, the browser checks the server's CORS policy.
* If the server allows the origin, the request succeeds; otherwise, it is blocked.

**When to use:**

* Whenever the **frontend and backend run on different domains or ports**.

**Code Example (Spring Boot Backend):**

```java
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserController {
}
```

**Example:**

* **Angular:** `http://localhost:4200`
* **Backend API:** `http://localhost:8080`

Without proper **CORS** configuration, the browser blocks the request even if the API is working correctly.


# ✅ **6. RxJS**

### **1. Observable**

**Observable** is an RxJS object that represents a stream of **asynchronous data** that can emit multiple values over time.

**Key Features:**

* Supports **asynchronous** operations.
* Can emit **multiple values**.
* Supports **lazy execution** (starts only after subscription).
* Used heavily with **HttpClient** and **event handling**.

**How it works:**

* An Observable produces data.
* An **Observer** subscribes to it and receives emitted values.

**When to use:**

* For API calls, user events, timers, and real-time data streams.

**Code Example:**

```typescript
import { Observable } from 'rxjs';

const observable = new Observable(observer => {
  observer.next('Hello');
  observer.next('Angular');
  observer.complete();
});

observable.subscribe(data => console.log(data));
```

### **2. Observer**

**Observer** is an object that **subscribes to an Observable** and receives the emitted values, errors, and completion notifications.

**Key Features:**

* Receives data using **`next()`**.
* Handles errors using **`error()`**.
* Detects completion using **`complete()`**.

**How it works:**

* The Observer subscribes to an Observable.
* Whenever the Observable emits data, the Observer processes it.

**When to use:**

* When you want to consume data from an Observable.

**Code Example:**

```typescript
const observer = {
  next: (value: any) => console.log(value),
  error: (err: any) => console.log(err),
  complete: () => console.log('Completed')
};

observable.subscribe(observer);
```

### **3. Subject**

**Subject** is a special type of Observable that acts as both an **Observable** and an **Observer**.

**Key Features:**

* Can **emit** and **receive** values.
* Supports **multicasting** (multiple subscribers receive the same value).
* Does not store previous values.

**How it works:**

* Data is pushed into the Subject using `next()`.
* All active subscribers receive the emitted value.

**When to use:**

* For communication between components or broadcasting events.

**Code Example:**

```typescript
import { Subject } from 'rxjs';

const subject = new Subject<string>();

subject.subscribe(data => console.log('A:', data));
subject.subscribe(data => console.log('B:', data));

subject.next('Hello');
```

**Output:**

```
A: Hello
B: Hello
```

### **4. BehaviorSubject**

**BehaviorSubject** is a type of Subject that **stores the latest value** and immediately sends it to new subscribers.

**Key Features:**

* Requires an **initial value**.
* New subscribers always receive the **current value**.
* Ideal for state management.

**How it works:**

* Holds the most recent value internally.
* Any new subscriber instantly gets the last emitted value.

**When to use:**

* For sharing application state like **logged-in user**, **theme**, or **cart data**.

**Code Example:**

```typescript
import { BehaviorSubject } from 'rxjs';

const subject = new BehaviorSubject('Guest');

subject.subscribe(data => console.log(data));

subject.next('Admin');

subject.subscribe(data => console.log(data));
```

**Output:**

```
Guest
Admin
Admin
```

### **5. ReplaySubject**

**ReplaySubject** is a type of Subject that stores a specified number of previous values and replays them to new subscribers.

**Key Features:**

* Can store multiple previous values.
* New subscribers receive past emitted values.
* Buffer size can be configured.

**How it works:**

* Keeps a history of emitted values.
* Replays the stored values to any new subscriber.

**When to use:**

* When new subscribers need access to previous events or messages.

**Code Example:**

```typescript
import { ReplaySubject } from 'rxjs';

const subject = new ReplaySubject(2);

subject.next('A');
subject.next('B');
subject.next('C');

subject.subscribe(data => console.log(data));
```

**Output:**

```
B
C
```

### **6. AsyncSubject**

**AsyncSubject** is a type of Subject that emits **only the last value** and only after the Observable is **completed**.

**Key Features:**

* Emits only the **final value**.
* No value is sent until `complete()` is called.
* All subscribers receive the same last value.

**How it works:**

* Multiple values can be emitted.
* Only the last emitted value is delivered when the Subject completes.

**When to use:**

* When only the final result matters, such as a one-time calculation or completion event.

**Code Example:**

```typescript
import { AsyncSubject } from 'rxjs';

const subject = new AsyncSubject<number>();

subject.subscribe(data => console.log(data));

subject.next(10);
subject.next(20);
subject.next(30);

subject.complete();
```

**Output:**

```
30
```
### **7. Subscription**

**Subscription** is the process of connecting an **Observer** to an **Observable** to receive emitted data.

**Key Features:**

* Starts the execution of an Observable.
* Receives **data**, **errors**, and **completion** events.
* Can be **unsubscribed** to avoid memory leaks.

**How it works:**

* Call `subscribe()` on an Observable.
* The callback function executes whenever new data is emitted.

**When to use:**

* For API calls, event handling, and any asynchronous data stream.

**Code Example:**

```typescript
const subscription = this.http.get('/api/users')
  .subscribe(data => {
    console.log(data);
  });

// Stop listening
subscription.unsubscribe();
```

### **8. Operators (`map`, `filter`, `switchMap`, `mergeMap`, `concatMap`)**

**Operators** are RxJS functions used to **transform**, **filter**, or **combine** Observable data streams.

**Key Features:**

* Make data processing easy and readable.
* Can chain multiple operations using `pipe()`.
* Help manage asynchronous workflows.

**How it works:**

* Operators are applied inside the `pipe()` method before subscribing.

**When to use:**

* When you need to modify, filter, or flatten Observable data.

| **Operator**      | **Purpose**                                                     | **When to Use**                                               |
| ----------------- | --------------------------------------------------------------- | ------------------------------------------------------------- |
| **`map()`**       | Transforms each emitted value.                                  | Convert or modify data.                                       |
| **`filter()`**    | Emits only values that match a condition.                       | Filter unwanted data.                                         |
| **`switchMap()`** | Cancels the previous Observable and switches to the latest one. | Search box or API calls where only the latest result matters. |
| **`mergeMap()`**  | Runs multiple Observables in parallel.                          | Multiple independent API calls.                               |
| **`concatMap()`** | Executes Observables one after another in sequence.             | When request order must be maintained.                        |

**Code Example:**

```typescript
import { map, filter } from 'rxjs/operators';

of(1, 2, 3, 4).pipe(
  filter(x => x > 2),
  map(x => x * 10)
).subscribe(data => console.log(data));
```

**Output:**

```
30
40
```

### **9. `forkJoin`**

**`forkJoin`** is an RxJS operator that waits for **all Observables to complete** and then returns their final values together.

**Key Features:**

* Executes multiple Observables in parallel.
* Returns data only after all requests are completed.
* Similar to **`Promise.all()`**.

**How it works:**

* Accepts an array or object of Observables.
* Emits a single combined result when every Observable completes.

**When to use:**

* When loading multiple independent API calls at the same time.

**Code Example:**

```typescript
import { forkJoin } from 'rxjs';

forkJoin({
  users: this.http.get('/api/users'),
  products: this.http.get('/api/products')
}).subscribe(result => {
  console.log(result.users);
  console.log(result.products);
});
```

### **10. `combineLatest`**

**`combineLatest`** combines multiple Observables and emits the **latest value from each** whenever any Observable emits a new value.

**Key Features:**

* Tracks the latest values from all Observables.
* Updates automatically when any source changes.
* Requires each Observable to emit at least once.

**How it works:**

* Waits for all Observables to emit their first value.
* Then emits a new combined value whenever one of them changes.

**When to use:**

* For combining multiple live data streams, such as filters and search inputs.

**Code Example:**

```typescript
import { combineLatest } from 'rxjs';

combineLatest([
  this.user$,
  this.role$
]).subscribe(([user, role]) => {
  console.log(user, role);
});
```

### **11. `debounceTime`**

**`debounceTime`** delays the emission of values until there is no new value for a specified time.

**Key Features:**

* Reduces unnecessary API calls.
* Ignores rapid repeated events.
* Commonly used with search inputs.

**How it works:**

* Waits for the specified time interval.
* Emits only the latest value if no new event occurs.

**When to use:**

* For search boxes, autocomplete, and user input validation.

**Code Example:**

```typescript
import { debounceTime } from 'rxjs/operators';

this.searchControl.valueChanges.pipe(
  debounceTime(500)
).subscribe(value => {
  console.log(value);
});
```

### **12. `distinctUntilChanged`**

**`distinctUntilChanged`** emits a value only if it is **different from the previous emitted value**.

**Key Features:**

* Prevents duplicate consecutive values.
* Reduces unnecessary processing and API calls.
* Often used together with **`debounceTime`**.

**How it works:**

* Compares the current value with the previous one.
* Emits only when the value changes.

**When to use:**

* For search inputs or forms where repeated identical values should be ignored.

**Code Example:**

```typescript
import { debounceTime, distinctUntilChanged } from 'rxjs/operators';

this.searchControl.valueChanges.pipe(
  debounceTime(300),
  distinctUntilChanged()
).subscribe(value => {
  console.log(value);
});
```


# ✅ **7. Component Communication**

### **1. `@Input`**

**`@Input`** is a decorator used to **pass data from a parent component to a child component**.

**Key Features:**

* Supports **one-way data binding**.
* Receives values from the parent component.
* Makes child components reusable.

**How it works:**

* Declare a property with `@Input()` in the child component.
* Bind a value from the parent using **property binding** (`[]`).

**When to use:**

* When a child component needs data from its parent.

**Code Example:**

**Child Component**

```typescript
import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-child',
  template: `<h3>{{ message }}</h3>`
})
export class ChildComponent {
  @Input() message!: string;
}
```

**Parent Component**

```html
<app-child [message]="'Hello Angular'"></app-child>
```

---

### **2. `@Output`**

**`@Output`** is a decorator used to **send data or events from a child component to a parent component**.

**Key Features:**

* Supports **child-to-parent communication**.
* Works with **`EventEmitter`**.
* Enables event binding using `()`.

**How it works:**

* Declare an `@Output()` property with `EventEmitter`.
* Emit an event from the child.
* Capture the event in the parent.

**When to use:**

* When a child component needs to notify the parent about an action.

**Code Example:**

**Child Component**

```typescript
import { Component, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-child',
  template: `<button (click)="sendData()">Send</button>`
})
export class ChildComponent {
  @Output() notify = new EventEmitter<string>();

  sendData() {
    this.notify.emit('Data from Child');
  }
}
```

**Parent Component**

```html
<app-child (notify)="receiveData($event)"></app-child>
```

```typescript
receiveData(data: string) {
  console.log(data);
}
```

---

### **3. EventEmitter**

**`EventEmitter`** is an Angular class used with **`@Output`** to emit custom events from a child component.

**Key Features:**

* Sends data from child to parent.
* Works with **event binding**.
* Can emit any type of value.

**How it works:**

* Create an `EventEmitter` object.
* Call `emit()` to send data.
* Parent component listens for the emitted event.

**When to use:**

* When implementing **component communication** from child to parent.

**Code Example:**

```typescript
@Output() save = new EventEmitter<boolean>();

saveData() {
  this.save.emit(true);
}
```

```html
<app-child (save)="onSave($event)"></app-child>
```

---

### **4. ViewChild**

**`ViewChild`** is a decorator used to access a **child component, directive, or DOM element** inside the same component's template.

**Key Features:**

* Accesses child components directly.
* Can access HTML elements using template references.
* Available after **`ngAfterViewInit()`**.

**How it works:**

* Add a template reference variable (`#`).
* Use `@ViewChild()` to get its reference.

**When to use:**

* When you need to call child component methods or manipulate DOM elements.

**Code Example:**

```html
<input #userInput type="text">
```

```typescript
import { ViewChild, ElementRef, AfterViewInit } from '@angular/core';

export class AppComponent implements AfterViewInit {
  @ViewChild('userInput') input!: ElementRef;

  ngAfterViewInit() {
    console.log(this.input.nativeElement.value);
  }
}
```

---

### **5. ContentChild**

**`ContentChild`** is a decorator used to access **projected content** that is passed into a component using **`<ng-content>`**.

**Key Features:**

* Accesses content projected from the parent.
* Works with **`<ng-content>`**.
* Available after **`ngAfterContentInit()`**.

**How it works:**

* Parent passes content inside the child component tags.
* Child component uses `@ContentChild()` to access that projected content.

**When to use:**

* When building reusable wrapper or layout components with content projection.

**Code Example:**

**Parent Component**

```html
<app-card>
  <h2 #title>Welcome</h2>
</app-card>
```

**Child Component**

```html
<ng-content></ng-content>
```

```typescript
import { ContentChild, ElementRef, AfterContentInit } from '@angular/core';

export class CardComponent implements AfterContentInit {
  @ContentChild('title') title!: ElementRef;

  ngAfterContentInit() {
    console.log(this.title.nativeElement.textContent);
  }
}
```
### **6. Parent → Child Communication**

**Parent → Child Communication** is the process of sending data from a **parent component** to a **child component** using the **`@Input`** decorator.

**Key Features:**

* Supports **one-way data binding**.
* Parent controls the data passed to the child.
* Makes child components reusable.

**How it works:**

* Define an `@Input()` property in the child component.
* Bind the value from the parent using **property binding (`[]`)**.

**When to use:**

* When a child component needs data from its parent.

**Code Example:**

**Child Component**

```typescript
@Input() message!: string;
```

**Parent Component**

```html
<app-child [message]="'Hello Angular'"></app-child>
```

---

### **7. Child → Parent Communication**

**Child → Parent Communication** is the process of sending data or events from a **child component** to a **parent component** using **`@Output`** and **`EventEmitter`**.

**Key Features:**

* Supports **event-based communication**.
* Child notifies the parent about actions.
* Uses **custom events**.

**How it works:**

* Create an `@Output()` property with `EventEmitter`.
* Call `emit()` in the child component.
* Parent listens to the event using **event binding (`()`)**.

**When to use:**

* When a child component needs to send user actions or data back to the parent.

**Code Example:**

**Child Component**

```typescript
@Output() notify = new EventEmitter<string>();

sendData() {
  this.notify.emit('Data from Child');
}
```

**Parent Component**

```html
<app-child (notify)="receiveData($event)"></app-child>
```

```typescript
receiveData(data: string) {
  console.log(data);
}
```

---

### **8. Sibling Communication**

**Sibling Communication** is the exchange of data between two components that have the **same parent**.

**Key Features:**

* Components do not communicate directly.
* Uses a **shared service** as a mediator.
* Supports real-time data sharing.

**How it works:**

* One sibling sends data to a shared service.
* The other sibling subscribes to that data using an Observable or `BehaviorSubject`.

**When to use:**

* When sibling components need to share or update common data.

**Code Example:**

**Shared Service**

```typescript
import { BehaviorSubject } from 'rxjs';

export class DataService {
  private message = new BehaviorSubject<string>('');
  currentMessage = this.message.asObservable();

  updateMessage(data: string) {
    this.message.next(data);
  }
}
```

**Sibling 1**

```typescript
this.dataService.updateMessage('Hello');
```

**Sibling 2**

```typescript
this.dataService.currentMessage.subscribe(
  data => console.log(data)
);
```

---

### **9. Shared Service Communication**

**Shared Service Communication** is a common Angular technique where multiple components communicate through a **shared service** using **RxJS Subjects or BehaviorSubjects**.

**Key Features:**

* Enables communication between unrelated components.
* Centralizes shared data.
* Uses **Dependency Injection (DI)**.
* Supports reactive updates.

**How it works:**

* Create a shared service with a `Subject` or `BehaviorSubject`.
* Inject the service into multiple components.
* One component updates the data, and others subscribe to receive changes.

**When to use:**

* For communication between **sibling**, **unrelated**, or **distant components**.

**Code Example:**

**Shared Service**

```typescript
import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class SharedService {
  message = new Subject<string>();
}
```

**Sender Component**

```typescript
this.sharedService.message.next('Angular');
```

**Receiver Component**

```typescript
this.sharedService.message.subscribe(
  data => console.log(data)
);
```

# ✅ **8. Dependency Injection (Advanced)**

### **1. Injector**

**Injector** is the Angular object responsible for **creating and providing service instances** to components or other services.

**Key Features:**

* Creates and manages dependencies.
* Automatically injects required services.
* Core part of Angular's **Dependency Injection (DI)** system.

**How it works:**

* When a component requests a service, the Injector checks if an instance exists.
* If not, it creates one and provides it.

**When to use:**

* Used internally by Angular whenever a service is injected through the constructor.

**Code Example:**

```typescript
import { Component } from '@angular/core';
import { UserService } from './user.service';

@Component({
  selector: 'app-home',
  template: `Home`
})
export class HomeComponent {
  constructor(private userService: UserService) {}
}
```

---

### **2. Provider**

**Provider** tells Angular **how to create or supply a dependency** for the Injector.

**Key Features:**

* Registers services with Angular.
* Supports `useClass`, `useValue`, `useFactory`, and `useExisting`.
* Can be configured at the module, component, or root level.

**How it works:**

* Angular reads the provider configuration.
* The Injector uses it to create the required service instance.

**When to use:**

* When registering services or providing custom implementations.

**Code Example:**

```typescript
@Injectable({
  providedIn: 'root'
})
export class UserService {}
```

Or:

```typescript
providers: [UserService]
```

---

### **3. Injection Token**

**Injection Token** is used to inject values or objects that **do not have a class type**, such as configuration values or interfaces.

**Key Features:**

* Supports injection of constants and configuration objects.
* Avoids naming conflicts.
* Works with Angular's DI system.

**How it works:**

* Create an `InjectionToken`.
* Register it with a provider.
* Inject it using `@Inject()`.

**When to use:**

* For API URLs, configuration settings, or interface-based dependencies.

**Code Example:**

```typescript
import { InjectionToken, Inject } from '@angular/core';

export const API_URL =
  new InjectionToken<string>('API_URL');

providers: [
  { provide: API_URL, useValue: 'https://api.example.com' }
];
```

```typescript
constructor(
  @Inject(API_URL) private apiUrl: string
) {}
```

---

### **4. Hierarchical DI**

**Hierarchical Dependency Injection (DI)** means Angular maintains a **tree of Injectors**, allowing services to be shared or scoped at different levels.

**Key Features:**

* Parent Injector can share services with children.
* Child Injector can override parent services.
* Improves modularity and encapsulation.

**How it works:**

* Angular first looks for a service in the current Injector.
* If not found, it searches up the Injector hierarchy.

**When to use:**

* When different modules or components need different service instances.

**Code Example:**

```typescript
@Component({
  selector: 'app-child',
  template: `Child`,
  providers: [UserService]
})
export class ChildComponent {}
```

Here, `ChildComponent` gets its own `UserService` instance instead of using the parent one.

---

### **5. Singleton Services**

**Singleton Services** are services for which Angular creates **only one instance** and shares it across the entire application.

**Key Features:**

* Single shared instance.
* Saves memory and resources.
* Maintains shared application state.

**How it works:**

* Register the service with **`providedIn: 'root'`**.
* Angular creates one instance and reuses it everywhere.

**When to use:**

* For authentication, logging, user sessions, or shared application data.

**Code Example:**

```typescript
@Injectable({
  providedIn: 'root'
})
export class AuthService {
}
```

All components injecting `AuthService` will use the same instance.

---

### **6. Multi Providers**

**Multi Providers** allow multiple values or services to be associated with the **same injection token**.

**Key Features:**

* Supports multiple implementations.
* Returns an array of provided values.
* Commonly used with **HTTP Interceptors**.

**How it works:**

* Set `multi: true` in the provider configuration.
* Angular collects all providers under the same token.

**When to use:**

* For registering multiple interceptors, validators, or plugin-like services.

**Code Example:**

```typescript
providers: [
  {
    provide: HTTP_INTERCEPTORS,
    useClass: AuthInterceptor,
    multi: true
  },
  {
    provide: HTTP_INTERCEPTORS,
    useClass: LoggingInterceptor,
    multi: true
  }
]
```



# ✅ **9. Change Detection**

### **1. Change Detection Strategy**

**Change Detection Strategy** is the Angular mechanism that determines **when and how the UI is updated** when application data changes.

**Key Features:**

* Automatically synchronizes **data and view**.
* Supports **Default** and **OnPush** strategies.
* Improves application performance.

**How it works:**

* Angular checks component data for changes.
* If data changes, Angular updates the DOM automatically.

**When to use:**

* In every Angular application to keep the UI synchronized with the model.

**Code Example:**

```typescript
import { ChangeDetectionStrategy, Component } from '@angular/core';

@Component({
  selector: 'app-user',
  template: `{{name}}`,
  changeDetection: ChangeDetectionStrategy.Default
})
export class UserComponent {
  name = 'Angular';
}
```

### **2. Default Strategy**

**Default Strategy** is the standard Angular change detection mode where Angular checks **all components** whenever an event occurs.

**Key Features:**

* Checks the entire component tree.
* Automatically detects all changes.
* Easy to use with no extra configuration.

**How it works:**

* Events like button clicks, HTTP responses, or timers trigger Angular to run change detection for every component.

**When to use:**

* For small to medium applications where performance is not a major concern.

**Code Example:**

```typescript
import { ChangeDetectionStrategy } from '@angular/core';

@Component({
  selector: 'app-home',
  template: `{{count}}`,
  changeDetection: ChangeDetectionStrategy.Default
})
export class HomeComponent {
  count = 0;
}
```

### **3. OnPush Strategy**

**OnPush Strategy** tells Angular to run change detection **only when necessary**, improving application performance.

**Key Features:**

* Reduces unnecessary UI checks.
* Improves performance for large applications.
* Works well with **immutable data** and **Observables**.

**How it works:**

* Angular checks the component only when:

  * An **`@Input()`** reference changes.
  * An event occurs inside the component.
  * An Observable emits a new value.
  * `markForCheck()` or `detectChanges()` is called manually.

**When to use:**

* For large applications or performance-critical components.

**Code Example:**

```typescript
import { ChangeDetectionStrategy } from '@angular/core';

@Component({
  selector: 'app-user',
  template: `{{user.name}}`,
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class UserComponent {
  user = { name: 'Angular' };
}
```

### **4. Zone.js**

**Zone.js** is a library used by Angular to **detect asynchronous operations** and automatically trigger change detection.

**Key Features:**

* Tracks asynchronous tasks like timers and HTTP calls.
* Automatically updates the UI.
* Eliminates the need for manual UI refresh in most cases.

**How it works:**

* Zone.js intercepts async operations such as `setTimeout()`, promises, and events.
* After the task completes, Angular runs change detection.

**When to use:**

* It works automatically in Angular and is used behind the scenes for change detection.

**Code Example:**

```typescript
setTimeout(() => {
  this.message = 'Updated';
}, 1000);
```

Here, **Zone.js** detects the `setTimeout()` completion and Angular automatically updates the UI.

### **5. Manual Change Detection**

**Manual Change Detection** allows developers to explicitly trigger UI updates when Angular does not detect changes automatically.

**Key Features:**

* Gives full control over UI updates.
* Used with **`ChangeDetectorRef`**.
* Useful with **OnPush** strategy or external libraries.

**How it works:**

* Inject `ChangeDetectorRef`.
* Call `detectChanges()` or `markForCheck()` to refresh the view.

**When to use:**

* When updates happen outside Angular's zone or with `OnPush` components.

**Code Example:**

```typescript
import { ChangeDetectorRef } from '@angular/core';

constructor(private cdr: ChangeDetectorRef) {}

updateData() {
  this.message = 'Hello';
  this.cdr.detectChanges();
}
```

### **6. Signals**

**Signals** are a new Angular reactive feature that automatically tracks data changes and updates the UI efficiently.

**Key Features:**

* Built-in **reactive state management**.
* Automatically tracks dependencies.
* Reduces unnecessary change detection.
* Simpler alternative to many Observable-based state use cases.

**How it works:**

* Create a signal using `signal()`.
* Read its value by calling it like a function.
* Update the value using `set()` or `update()`.
* Angular automatically refreshes dependent UI.

**When to use:**

* For managing local component state and building high-performance Angular applications.

**Code Example:**

```typescript
import { Component, signal } from '@angular/core';

@Component({
  selector: 'app-counter',
  template: `<button (click)="increment()">{{count()}}</button>`
})
export class CounterComponent {
  count = signal(0);

  increment() {
    this.count.set(this.count() + 1);
  }
}
```


# ✅ **10. Angular Signals (Important)**

### **1. `signal()`**

**`signal()`** is an Angular API used to create a **reactive state variable** that automatically updates the UI when its value changes.

**Key Features:**

* Built-in **reactive state management**.
* Automatically triggers UI updates.
* Simple and lightweight.
* No need for manual subscriptions.

**How it works:**

* Create a signal with an initial value.
* Read the value by calling it like a function.
* Update it using `set()` or `update()`.

**When to use:**

* For managing local component state such as counters, user data, or UI flags.

**Code Example:**

```typescript
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

---

### **2. `computed()`**

**`computed()`** is used to create a **derived signal** whose value is automatically calculated from one or more other signals.

**Key Features:**

* Creates computed or derived values.
* Automatically recalculates when dependencies change.
* Read-only and optimized.

**How it works:**

* Define a computed signal based on existing signals.
* Angular tracks dependencies automatically.

**When to use:**

* When a value depends on other signal values, such as totals or filtered data.

**Code Example:**

```typescript
import { signal, computed } from '@angular/core';

price = signal(100);
quantity = signal(2);

total = computed(() => this.price() * this.quantity());
```

```html
<p>Total: {{ total() }}</p>
```

---

### **3. `effect()`**

**`effect()`** is used to perform **side effects** whenever one or more signals change.

**Key Features:**

* Automatically reacts to signal changes.
* Tracks dependencies without manual configuration.
* Useful for logging, API calls, or local storage updates.

**How it works:**

* Define an `effect()`.
* Angular reruns it whenever any accessed signal value changes.

**When to use:**

* For actions that should happen automatically when state changes.

**Code Example:**

```typescript
import { signal, effect } from '@angular/core';

count = signal(0);

constructor() {
  effect(() => {
    console.log('Count:', this.count());
  });
}
```

Whenever `count` changes, the `effect()` runs automatically.

---

### **4. Signal vs Observable**

**Signals** and **Observables** are both used for reactive programming, but they solve different problems.

**Key Features:**

* **Signals** are simpler and ideal for local UI state.
* **Observables** are powerful for asynchronous data streams and API calls.
* Signals do not require subscriptions.

**How it works:**

* Signals update automatically when their value changes.
* Observables emit values over time and require `subscribe()`.

**When to use:**

* Use **Signals** for component state.
* Use **Observables** for HTTP requests, events, and real-time streams.

| **Signals**                               | **Observables**                              |
| ----------------------------------------- | -------------------------------------------- |
| Built-in Angular feature                  | RxJS feature                                 |
| No `subscribe()` required                 | Requires `subscribe()`                       |
| Best for local state                      | Best for async streams                       |
| Synchronous updates                       | Supports asynchronous data                   |
| Uses `signal()`, `computed()`, `effect()` | Uses RxJS operators like `map()`, `filter()` |

**Code Example:**

```typescript
// Signal
count = signal(0);
count.set(10);

// Observable
this.http.get('/api/users')
  .subscribe(data => console.log(data));
```

---

### **5. Signal-based State Management**

**Signal-based State Management** is a way of managing application or component state using **Signals** instead of external state libraries.

**Key Features:**

* Lightweight and easy to implement.
* Automatic UI updates.
* No manual subscriptions or unsubscriptions.
* Integrates naturally with Angular change detection.

**How it works:**

* Store state in signals.
* Use `computed()` for derived values.
* Use `effect()` for side effects.
* Components read signal values directly.

**When to use:**

* For managing shared or local application state in modern Angular applications.

**Code Example:**

```typescript
import { Injectable, signal } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CounterService {
  count = signal(0);

  increment() {
    this.count.update(value => value + 1);
  }
}
```

```typescript
constructor(public counterService: CounterService) {}
```

```html
<p>{{ counterService.count() }}</p>
<button (click)="counterService.increment()">
  Increment
</button>
```

# ✅ **11. State Management**

### **1. Services + BehaviorSubject**

**Services + `BehaviorSubject`** is a simple Angular state management approach where a service stores shared data and `BehaviorSubject` broadcasts updates to components.

**Key Features:**

* Centralized shared state.
* New subscribers receive the **latest value** immediately.
* Easy to implement without external libraries.

**How it works:**

* Create a `BehaviorSubject` inside a service.
* Components update the value using `next()`.
* Other components subscribe to receive updates.

**When to use:**

* For small to medium applications with simple shared state requirements.

**Code Example:**

```typescript
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class UserService {
  private user = new BehaviorSubject<string>('Guest');
  user$ = this.user.asObservable();

  updateUser(name: string) {
    this.user.next(name);
  }
}
```

```typescript
this.userService.user$.subscribe(data => console.log(data));
```

---

### **2. Signals Store**

**Signals Store** is a state management approach that uses Angular **Signals** to manage and share application state reactively.

**Key Features:**

* Uses **`signal()`**, **`computed()`**, and **`effect()`**.
* No manual subscriptions.
* Automatic UI updates.
* Lightweight and high performance.

**How it works:**

* Store application state in signals inside a service.
* Components read and update signal values directly.

**When to use:**

* For modern Angular applications that need simple and efficient state management.

**Code Example:**

```typescript
import { Injectable, signal } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class CounterStore {
  count = signal(0);

  increment() {
    this.count.update(value => value + 1);
  }
}
```

```html
<p>{{ counterStore.count() }}</p>
```

---

### **3. NgRx Basics**

**NgRx** is a Redux-inspired state management library for Angular that manages application state in a **single centralized Store**.

**Key Features:**

* Predictable state management.
* Unidirectional data flow.
* Uses **Store**, **Actions**, **Reducers**, **Effects**, and **Selectors**.

**How it works:**

1. Component dispatches an **Action**.
2. **Reducer** updates the **Store**.
3. Component reads updated data using **Selectors**.
4. **Effects** handle API calls or other side effects.

**When to use:**

* For large applications with complex shared state and many components.

**Code Example:**

```typescript
this.store.dispatch(loadUsers());
```

```typescript
this.users$ = this.store.select(selectUsers);
```

---

### **4. Store**

**Store** is the central container that holds the **entire application state** in NgRx.

**Key Features:**

* Single source of truth.
* Immutable state.
* Accessible throughout the application.

**How it works:**

* State is updated only through **Actions** and **Reducers**.
* Components read state using **Selectors**.

**When to use:**

* When multiple components need access to the same application state.

**Code Example:**

```typescript
constructor(private store: Store) {}

this.users$ = this.store.select(selectUsers);
```

---

### **5. Actions**

**Actions** are plain objects that describe **what happened** in the application.

**Key Features:**

* Trigger state changes.
* Carry optional data called **payload**.
* Created using `createAction()`.

**How it works:**

* Components dispatch actions.
* Reducers and Effects listen for those actions.

**When to use:**

* Whenever an event should update the application state.

**Code Example:**

```typescript
import { createAction } from '@ngrx/store';

export const loadUsers =
  createAction('[User] Load Users');
```

```typescript
this.store.dispatch(loadUsers());
```

---

### **6. Reducers**

**Reducers** are pure functions that take the current state and an action, then return a **new updated state**.

**Key Features:**

* Pure and predictable functions.
* Never modify the existing state directly.
* Return a new immutable state object.

**How it works:**

* Reducer receives the current state and dispatched action.
* Based on the action type, it returns the updated state.

**When to use:**

* To define how application state changes.

**Code Example:**

```typescript
import { createReducer, on } from '@ngrx/store';

export const counterReducer = createReducer(
  0,
  on(increment, state => state + 1)
);
```

---

### **7. Effects**

**Effects** handle **side effects** such as API calls, logging, or asynchronous operations outside the Reducer.

**Key Features:**

* Manages asynchronous tasks.
* Keeps Reducers pure.
* Listens for dispatched Actions.

**How it works:**

* Effect listens for an Action.
* Executes an API call or async operation.
* Dispatches a success or failure Action.

**When to use:**

* For HTTP requests, local storage operations, or external service calls.

**Code Example:**

```typescript
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
```

---

### **8. Selectors**

**Selectors** are functions used to **read and retrieve specific data** from the NgRx Store.

**Key Features:**

* Provides efficient state access.
* Can compute derived data.
* Improves code reusability.

**How it works:**

* Define a selector using `createSelector()`.
* Components use `store.select()` to get state data.

**When to use:**

* When components need to read data from the Store.

**Code Example:**

```typescript
import { createSelector } from '@ngrx/store';

export const selectUsers = createSelector(
  selectUserState,
  state => state.users
);
```

```typescript
this.users$ = this.store.select(selectUsers);
```

# ✅ **12. Performance Optimization**

### **1. Lazy Loading**

**Lazy Loading** is an Angular feature that loads a **module only when it is required**, instead of loading the entire application at startup.

**Key Features:**

* Improves **application performance**.
* Reduces **initial bundle size**.
* Loads feature modules **on demand**.

**How it works:**

* Angular uses **`loadChildren`** to load a module only when the user navigates to its route.

**When to use:**

* In large applications with multiple feature modules.

**Code Example:**

```typescript
const routes: Routes = [
  {
    path: 'admin',
    loadChildren: () =>
      import('./admin/admin.module')
        .then(m => m.AdminModule)
  }
];
```

---

### **2. Preloading Strategy**

**Preloading Strategy** is an Angular feature that loads **lazy-loaded modules in the background** after the application starts.

**Key Features:**

* Combines benefits of lazy loading and faster navigation.
* Improves user experience.
* Supports built-in and custom preloading strategies.

**How it works:**

* Angular initially loads the main application.
* After startup, it preloads lazy modules without waiting for user navigation.

**When to use:**

* When you want faster access to lazy-loaded pages after the initial load.

**Code Example:**

```typescript
import { PreloadAllModules, RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forRoot(routes, {
      preloadingStrategy: PreloadAllModules
    })
  ]
})
export class AppModule {}
```

---

### **3. `trackBy`**

**`trackBy`** is an Angular optimization feature used with **`*ngFor`** to uniquely identify list items and avoid unnecessary DOM updates.

**Key Features:**

* Improves rendering performance.
* Prevents recreation of unchanged DOM elements.
* Uses a unique identifier like `id`.

**How it works:**

* Angular uses the value returned by `trackBy` to track items instead of comparing the entire object.

**When to use:**

* When displaying large or frequently updated lists.

**Code Example:**

```html
<li *ngFor="let user of users; trackBy: trackById">
  {{ user.name }}
</li>
```

```typescript
trackById(index: number, user: any) {
  return user.id;
}
```

---

### **4. OnPush**

**OnPush** is an Angular **Change Detection Strategy** that checks a component only when specific events occur, improving performance.

**Key Features:**

* Reduces unnecessary change detection cycles.
* Improves application speed.
* Works well with **immutable objects**, **Signals**, and **Observables**.

**How it works:**

* Angular updates the component only when:

  * An **`@Input()`** reference changes.
  * An event occurs inside the component.
  * An Observable or Signal updates.
  * `markForCheck()` or `detectChanges()` is called.

**When to use:**

* For large applications or performance-critical components.

**Code Example:**

```typescript
import { ChangeDetectionStrategy } from '@angular/core';

@Component({
  selector: 'app-user',
  template: `{{user.name}}`,
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class UserComponent {
  user = { name: 'Angular' };
}
```

---

### **5. Memoization**

**Memoization** is a performance optimization technique that **stores the result of a computation** and reuses it when the same input occurs again.

**Key Features:**

* Avoids repeated expensive calculations.
* Improves application performance.
* Frequently used in **NgRx Selectors** and computed values.

**How it works:**

* The function caches the result for a given input.
* If the same input is received again, the cached result is returned instead of recalculating.

**When to use:**

* For expensive calculations or derived state that is accessed repeatedly.

**Code Example:**

```typescript
const cache: any = {};

function square(num: number): number {
  if (cache[num]) {
    return cache[num];
  }

  cache[num] = num * num;
  return cache[num];
}
```

```typescript
console.log(square(5)); // Calculates and stores
console.log(square(5)); // Returns cached value
```
### **6. Tree Shaking**

**Tree Shaking** is a build optimization technique that **removes unused code** from the final JavaScript bundle.

**Key Features:**

* Reduces **bundle size**.
* Improves **application loading speed**.
* Removes unused imports and modules automatically.

**How it works:**

* During the production build, Angular and the bundler analyze the code.
* Any code that is never used is excluded from the final output.

**When to use:**

* It works automatically in **production builds** and is useful for optimizing large applications.

**Code Example:**

```typescript
// math.ts
export function add(a: number, b: number) {
  return a + b;
}

export function subtract(a: number, b: number) {
  return a - b;
}
```

```typescript
// app.component.ts
import { add } from './math';

console.log(add(10, 20));
```

Here, **`subtract()`** is not used, so **Tree Shaking** removes it from the final bundle.

---

### **7. Bundle Optimization**

**Bundle Optimization** is the process of reducing the **size and loading time** of the Angular application bundle.

**Key Features:**

* Reduces JavaScript and CSS bundle size.
* Improves startup and page load performance.
* Includes techniques like **Lazy Loading**, **Tree Shaking**, and **code minification**.

**How it works:**

* Angular optimizes, compresses, and minifies application files during the production build.

**When to use:**

* For production deployments to improve performance and user experience.

**Code Example:**

```bash
ng build --configuration production
```

This command enables production optimizations such as **minification**, **Tree Shaking**, and **bundle optimization**.

---

### **8. SSR**

**SSR (Server-Side Rendering)** is a technique where Angular renders the application **on the server** and sends the generated HTML to the browser.

**Key Features:**

* Faster **initial page load**.
* Improves **SEO (Search Engine Optimization)**.
* Better performance for slow networks and low-powered devices.

**How it works:**

* The server generates the HTML for the requested page.
* The browser displays the rendered page immediately before Angular becomes interactive.

**When to use:**

* For SEO-friendly applications, public websites, blogs, and e-commerce platforms.

**Code Example:**

```bash
ng add @angular/ssr
```

```bash
ng serve
```

After enabling **SSR**, Angular renders the initial page on the server before sending it to the client.

---

### **9. Hydration**

**Hydration** is the process where Angular **reuses the HTML generated by SSR** and attaches event listeners instead of rebuilding the page from scratch.

**Key Features:**

* Reuses server-rendered HTML.
* Reduces page flickering.
* Improves startup performance.
* Works together with **SSR**.

**How it works:**

* SSR sends pre-rendered HTML to the browser.
* Angular "hydrates" the page by attaching component logic and event handlers to the existing HTML.

**When to use:**

* When using **SSR** to provide a faster and smoother user experience.

**Code Example:**

```typescript
import { bootstrapApplication } from '@angular/platform-browser';
import { provideClientHydration } from '@angular/platform-browser';

bootstrapApplication(AppComponent, {
  providers: [
    provideClientHydration()
  ]
});
```


# ✅ **13. Angular Security**

### **1. XSS (Cross-Site Scripting)**

**Definition:**
**XSS** is a security attack where a malicious script is injected into a web page and executed in another user's browser.

**How it works:**

* Attacker inserts malicious **JavaScript** into user input.
* If the application displays it without validation, the script runs in the browser.
* It can steal **cookies**, **tokens**, or manipulate the page.

**Key Features:**

* Targets the **client-side**.
* Common types: **Stored XSS**, **Reflected XSS**, **DOM-based XSS**.
* Prevented using **input validation** and **output encoding**.

**When to use:**
Use **Angular's built-in sanitization** and avoid rendering untrusted HTML directly.

**Code Example:**

```html
<!-- Safe -->
<p>{{ userInput }}</p>

<!-- Unsafe -->
<div [innerHTML]="userInput"></div>
```

---

### **2. CSRF (Cross-Site Request Forgery)**

**Definition:**
**CSRF** is an attack where a malicious website tricks an authenticated user into performing unwanted actions.

**How it works:**

* User is logged into a trusted application.
* Attacker sends a fake request from another site.
* Browser automatically includes authentication cookies.

**Key Features:**

* Mainly affects **cookie-based authentication**.
* Prevented using **CSRF tokens** and **SameSite cookies**.
* Less common with **JWT in Authorization header**.

**When to use:**
Enable **CSRF protection** when using **session or cookie authentication**.

**Code Example:**

```typescript
provideHttpClient(withXsrfConfiguration({
  cookieName: 'XSRF-TOKEN',
  headerName: 'X-XSRF-TOKEN'
}));
```

---

### **3. Sanitization**

**Definition:**
**Sanitization** removes or escapes unsafe content before rendering it in the browser.

**How it works:**

* Angular automatically sanitizes values bound to **HTML**, **URL**, and **Style** contexts.
* Prevents execution of malicious scripts.

**Key Features:**

* Built-in **XSS protection**.
* Automatically cleans dangerous HTML.
* Avoid unnecessary use of `bypassSecurityTrustHtml()`.

**When to use:**
Use whenever displaying **dynamic or user-provided content**.

**Code Example:**

```html
<div [innerHTML]="content"></div>
```

Angular sanitizes `content` before rendering.

---

### **4. JWT Storage**

**Definition:**
**JWT (JSON Web Token)** is used to securely store user authentication information.

**Best Practice:**

* Store JWT in **HttpOnly Secure Cookies** (recommended).
* Avoid storing sensitive tokens in **localStorage** because they can be accessed by XSS attacks.

**Key Features:**

* Stateless authentication.
* Token sent with each request.
* Can include user roles and permissions.

**When to use:**
Use **JWT** for **token-based authentication** in REST APIs.

**Code Example:**

```typescript
const headers = new HttpHeaders({
  Authorization: `Bearer ${token}`
});
```

---

### **5. Route Protection**

**Definition:**
**Route Protection** restricts access to certain pages based on authentication or user roles.

**How it works:**

* Angular uses **Route Guards** like `CanActivate`.
* Guard checks if the user is authenticated before navigation.

**Key Features:**

* Protects private routes.
* Supports **role-based access control**.
* Prevents unauthorized navigation.

**When to use:**
Use for **dashboard**, **admin**, or any protected pages.

**Code Example:**

```typescript
export const authGuard: CanActivateFn = () => {
  return !!localStorage.getItem('token');
};
```

```typescript
{
  path: 'dashboard',
  component: DashboardComponent,
  canActivate: [authGuard]
}
```

---

### **6. Secure HTTP Calls**

**Definition:**
Secure HTTP calls ensure data is transmitted and accessed safely between the client and server.

**How it works:**

* Use **HTTPS** for encrypted communication.
* Send JWT using the **Authorization** header.
* Add authentication automatically using an **HTTP Interceptor**.

**Key Features:**

* Uses **HTTPS** encryption.
* Automatically attaches authentication tokens.
* Centralized error and security handling.

**When to use:**
Use for all API communication, especially when sending **sensitive data**.

**Code Example:**

```typescript
export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const token = localStorage.getItem('token');

  const authReq = req.clone({
    setHeaders: {
      Authorization: `Bearer ${token}`
    }
  });

  return next(authReq);
};
```


# ✅ **14. Testing**

### **1. Jasmine**

**Definition:**
**Jasmine** is a **JavaScript testing framework** used to write and run unit tests in Angular applications.

**Key Features:**

* Supports **BDD (Behavior-Driven Development)** style.
* Uses functions like **`describe()`**, **`it()`**, and **`expect()`**.
* No dependency on browsers or external libraries.

**How it works:**

* `describe()` groups test cases.
* `it()` defines an individual test.
* `expect()` checks the expected result.

**When to use:**
Use **Jasmine** to write and organize **unit tests** for components, services, and pipes.

**Code Example:**

```typescript
describe('Calculator', () => {
  it('should add two numbers', () => {
    expect(2 + 3).toBe(5);
  });
});
```

### **2. Karma**

**Definition:**
**Karma** is a **test runner** that executes Angular tests in real browsers.

**Key Features:**

* Runs tests automatically after code changes.
* Supports multiple browsers like **Chrome** and **Firefox**.
* Integrates easily with **Jasmine**.

**How it works:**

* Karma launches a browser.
* Executes Jasmine test files.
* Displays test results in the terminal or browser.

**When to use:**
Use **Karma** to run and automate Angular test execution.

**Code Example:**

```bash
ng test
```

This command starts **Karma** and runs all Jasmine tests.

### **3. Unit Testing**

**Definition:**
**Unit Testing** tests a small piece of code, such as a method or function, in isolation.

**Key Features:**

* Tests a single unit of functionality.
* Fast and easy to automate.
* Helps catch bugs early.

**How it works:**

* Provide input to a function.
* Verify the output using assertions.

**When to use:**
Use **Unit Testing** for methods, utilities, pipes, and services.

**Code Example:**

```typescript
it('should return true', () => {
  expect(service.isLoggedIn()).toBeTrue();
});
```

### **4. Component Testing**

**Definition:**
**Component Testing** verifies that an Angular component works correctly, including its template and logic.

**Key Features:**

* Tests **UI rendering** and **user interactions**.
* Checks component properties and events.
* Uses **TestBed** and **ComponentFixture**.

**How it works:**

* Create the component.
* Trigger change detection.
* Verify rendered output or behavior.

**When to use:**
Use for testing **buttons, forms, events, and template bindings**.

**Code Example:**

```typescript
it('should create the component', () => {
  expect(component).toBeTruthy();
});
```

### **5. Service Testing**

**Definition:**
**Service Testing** verifies the business logic inside Angular services.

**Key Features:**

* Tests methods independently.
* Does not require UI.
* Can mock API calls and dependencies.

**How it works:**

* Create the service instance.
* Call service methods.
* Verify returned values.

**When to use:**
Use for testing **data processing**, **API logic**, and **utility methods**.

**Code Example:**

```typescript
it('should return user name', () => {
  expect(service.getUserName()).toBe('John');
});
```

### **6. Mocking Dependencies**

**Definition:**
**Mocking** means replacing real dependencies with fake objects during testing.

**Key Features:**

* Isolates the unit being tested.
* Avoids real API or database calls.
* Makes tests faster and predictable.

**How it works:**

* Create a mock object or use `spyOn()`.
* Return fake values instead of calling real methods.

**When to use:**
Use when a component or service depends on **HTTP services**, **other services**, or external resources.

**Code Example:**

```typescript
spyOn(userService, 'getUser').and.returnValue({
  id: 1,
  name: 'John'
});
```

### **7. TestBed**

**Definition:**
**TestBed** is Angular's primary utility for configuring and creating a testing environment.

**Key Features:**

* Creates a testing module.
* Configures components, services, and dependencies.
* Supports dependency injection during tests.

**How it works:**

* Configure the testing module.
* Create component or service instances.
* Run assertions.

**When to use:**
Use **TestBed** for **component testing** and when Angular features like **dependency injection** are required.

**Code Example:**

```typescript
beforeEach(() => {
  TestBed.configureTestingModule({
    declarations: [AppComponent]
  });

  const fixture = TestBed.createComponent(AppComponent);
  const component = fixture.componentInstance;
});
```


# ✅ **15. Angular Build & Deployment**

### **1. Angular CLI**

**Definition:**
**Angular CLI (Command Line Interface)** is a tool that helps developers create, build, test, and manage Angular applications.

**Key Features:**

* Generates **components**, **services**, and **modules**.
* Supports **build**, **test**, and **deployment** commands.
* Reduces manual configuration.

**How it works:**

* Runs commands from the terminal.
* Automatically generates project structure and configuration files.

**When to use:**
Use **Angular CLI** for everyday Angular development and project management.

**Code Example:**

```bash
ng new my-app
ng generate component home
ng serve
```

### **2. Environment Files**

**Definition:**
**Environment Files** store configuration values for different environments like **development** and **production**.

**Key Features:**

* Separate settings for each environment.
* Easy API URL and feature flag management.
* Automatically replaced during build.

**How it works:**

* Angular uses `environment.ts` by default.
* During production build, it replaces it with `environment.prod.ts`.

**When to use:**
Use when API endpoints or configuration values differ between environments.

**Code Example:**

```typescript
// environment.ts
export const environment = {
  production: false,
  apiUrl: 'http://localhost:8080/api'
};
```

```typescript
// environment.prod.ts
export const environment = {
  production: true,
  apiUrl: 'https://api.example.com'
};
```

### **3. Build Configurations**

**Definition:**
**Build Configurations** allow different build settings for development, testing, and production.

**Key Features:**

* Defined in `angular.json`.
* Supports multiple custom configurations.
* Controls optimization, file replacements, and assets.

**How it works:**

* Angular reads the selected configuration during the build process.
* Applies the corresponding settings automatically.

**When to use:**
Use when different environments require different build options.

**Code Example:**

```bash
ng build --configuration=production
```

```json
"configurations": {
  "production": {
    "optimization": true
  }
}
```

### **4. Production Build**

**Definition:**
A **Production Build** creates an optimized version of the Angular application for deployment.

**Key Features:**

* Enables **minification** and **tree shaking**.
* Reduces bundle size.
* Improves application performance.

**How it works:**

* Removes unused code.
* Compresses JavaScript and CSS files.
* Uses production environment settings.

**When to use:**
Use before deploying the application to production servers.

**Code Example:**

```bash
ng build --configuration=production
```

### **5. AOT Compilation**

**Definition:**
**AOT (Ahead-of-Time) Compilation** compiles Angular templates into JavaScript during the build process.

**Key Features:**

* Faster application startup.
* Smaller bundle size.
* Detects template errors at build time.

**How it works:**

* Angular compiler converts templates before the application runs in the browser.
* Browser downloads only compiled code.

**When to use:**
Use for **production builds** to improve performance and security.

**Code Example:**

```bash
ng build --aot
```

### **6. Ahead-of-Time (AOT) vs JIT (Just-in-Time)**

| Feature          | **AOT**          | **JIT**                       |
| ---------------- | ---------------- | ----------------------------- |
| Compilation Time | During **build** | In the **browser** at runtime |
| Startup Speed    | **Faster**       | Slower                        |
| Bundle Size      | **Smaller**      | Larger                        |
| Error Detection  | Build time       | Runtime                       |
| Best Use         | **Production**   | Development                   |

**How it works:**

* **AOT** compiles before deployment.
* **JIT** compiles after the application loads in the browser.

**When to use:**

* Use **AOT** for production.
* Use **JIT** for development and debugging.

**Code Example:**

```bash
# AOT
ng build --aot

# JIT (default in development)
ng serve
```

### **7. Deployment Strategies**

**Definition:**
**Deployment Strategies** are methods used to publish an Angular application to a web server or cloud platform.

**Key Features:**

* Deploy static files from the `dist/` folder.
* Supports cloud platforms like **Netlify**, **Vercel**, and **Firebase Hosting**.
* Can use **CI/CD pipelines** for automated deployment.

**How it works:**

* Build the application.
* Upload the generated `dist` folder to the hosting server.
* Configure server routing for Angular SPA.

**When to use:**
Use after creating a **production build** to make the application available to users.

**Code Example:**

```bash
ng build --configuration=production
```

Deploy the generated files from:

```text
dist/my-app/
```


# ✅ **16. Angular 17+ Features**

### **1. Standalone Components**

**Definition:**
**Standalone Components** are Angular components that work **without an NgModule** by setting `standalone: true`.

**Key Features:**

* No need for **AppModule** or feature modules.
* Directly import required dependencies.
* Simpler and cleaner project structure.

**How it works:**

* Add `standalone: true` in the component decorator.
* Import other standalone components, directives, or pipes in the `imports` array.

**When to use:**
Use for **new Angular applications** or when migrating from module-based architecture.

**Code Example:**

```typescript
import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule],
  template: `<h2>Home Component</h2>`
})
export class HomeComponent {}
```

### **2. Standalone Routing**

**Definition:**
**Standalone Routing** configures Angular routes without using `AppRoutingModule`.

**Key Features:**

* Uses `provideRouter()`.
* Supports **lazy loading** with standalone components.
* Reduces boilerplate code.

**How it works:**

* Define routes in a separate file.
* Register routes using `provideRouter()` during application bootstrap.

**When to use:**
Use in applications built with **Standalone Components**.

**Code Example:**

```typescript
import { bootstrapApplication } from '@angular/platform-browser';
import { provideRouter } from '@angular/router';

const routes = [
  {
    path: '',
    loadComponent: () =>
      import('./home.component').then(m => m.HomeComponent)
  }
];

bootstrapApplication(AppComponent, {
  providers: [provideRouter(routes)]
});
```

### **3. Signals**

**Definition:**
**Signals** are Angular's reactive state management feature that automatically tracks and updates UI when data changes.

**Key Features:**

* Simple and lightweight reactivity.
* Automatic UI updates.
* Reduces the need for complex RxJS usage.

**How it works:**

* Create a signal using `signal()`.
* Read its value by calling it like a function.
* Update it using `set()` or `update()`.

**When to use:**
Use for **local component state** and reactive data updates.

**Code Example:**

```typescript
import { signal } from '@angular/core';

count = signal(0);

increment() {
  this.count.update(value => value + 1);
}
```

```html
<p>Count: {{ count() }}</p>
<button (click)="increment()">Increment</button>
```

### **4. Control Flow (`@if`, `@for`, `@switch`)**

**Definition:**
**Control Flow** introduces a new template syntax that replaces traditional structural directives like `*ngIf` and `*ngFor`.

**Key Features:**

* Cleaner and more readable syntax.
* Better performance.
* Built directly into Angular templates.

**How it works:**

* `@if` for conditional rendering.
* `@for` for loops.
* `@switch` for multiple conditions.

**When to use:**
Use in modern Angular applications instead of `*ngIf`, `*ngFor`, and `ngSwitch`.

**Code Example:**

```html
@if (isLoggedIn) {
  <h2>Welcome User!</h2>
} @else {
  <h2>Please Login</h2>
}

@for (item of items; track item.id) {
  <p>{{ item.name }}</p>
}
```

### **5. Deferrable Views (`@defer`)**

**Definition:**
**Deferrable Views** allow parts of the UI to load only when needed, improving initial page load performance.

**Key Features:**

* Lazy loads template content.
* Reduces initial bundle size.
* Supports loading and placeholder states.

**How it works:**

* Wrap content inside `@defer`.
* Angular loads it when a condition or trigger is met.

**When to use:**
Use for **heavy components**, **charts**, or content that is not immediately visible.

**Code Example:**

```html
@defer {
  <app-dashboard-chart />
} @placeholder {
  <p>Loading chart...</p>
}
```

### **6. SSR & Hydration**

**Definition:**
**SSR (Server-Side Rendering)** renders the Angular application on the server, while **Hydration** reuses that HTML and activates it in the browser without rebuilding the page.

**Key Features:**

* Faster first page load.
* Better **SEO**.
* Improved user experience with smoother rendering.

**How it works:**

* Server generates the initial HTML.
* Browser receives and displays it immediately.
* Hydration attaches Angular functionality to the existing HTML.

**When to use:**
Use for **SEO-friendly websites**, **e-commerce**, **blogs**, and applications requiring fast initial rendering.

**Code Example:**

```bash
ng add @angular/ssr
```

This command adds **SSR** and **Hydration** support to the Angular application.


# ✅ **17. Angular Architecture & System Design**

### **1. Folder Structure**

**Definition:**
A good **Folder Structure** organizes Angular files in a clean and scalable way, making the application easier to maintain.

**Key Features:**

* Separates code by **feature** and **responsibility**.
* Improves readability and maintainability.
* Makes large projects easier to manage.

**How it works:**

* Keep components, services, models, and routes inside their related feature folders.
* Store common code in **shared** and **core** folders.

**When to use:**
Use in every Angular project, especially for **medium and large applications**.

**Example Structure:**

```text
src/
 ├── app/
 │   ├── core/
 │   ├── shared/
 │   ├── features/
 │   │   ├── user/
 │   │   └── admin/
 │   ├── app.component.ts
 │   └── app.routes.ts
 └── assets/
```

### **2. Feature Modules**

**Definition:**
A **Feature Module** groups all files related to a specific business feature, such as **User**, **Admin**, or **Product**.

**Key Features:**

* Encapsulates feature-specific functionality.
* Supports **lazy loading**.
* Improves modularity and code organization.

**How it works:**

* Each feature module contains its own components, services, and routing.
* Can be loaded only when needed.

**When to use:**
Use for **large features** that can be developed and maintained independently.

**Code Example:**

```typescript
import { NgModule } from '@angular/core';
import { UserComponent } from './user.component';

@NgModule({
  declarations: [UserComponent]
})
export class UserModule {}
```

### **3. Shared Modules**

**Definition:**
A **Shared Module** contains reusable components, directives, pipes, and common Angular modules that are used across multiple features.

**Key Features:**

* Promotes code reuse.
* Reduces duplicate code.
* Centralizes common functionality.

**How it works:**

* Declare and export reusable items.
* Import the shared module into any feature module that needs them.

**When to use:**
Use for common UI components like **Header**, **Footer**, **Custom Pipes**, and **Directives**.

**Code Example:**

```typescript
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from './header.component';

@NgModule({
  declarations: [HeaderComponent],
  imports: [CommonModule],
  exports: [HeaderComponent, CommonModule]
})
export class SharedModule {}
```

### **4. Core Modules**

**Definition:**
A **Core Module** contains application-wide singleton services and components that should be loaded only once.

**Key Features:**

* Holds **global services** and application configuration.
* Loaded only in the root module.
* Avoids creating multiple service instances.

**How it works:**

* Place singleton services like **AuthService**, **LoggerService**, and **HTTP Interceptors** in the core module.
* Import it only once in the root application.

**When to use:**
Use for services and functionality shared across the entire application.

**Code Example:**

```typescript
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  imports: [HttpClientModule]
})
export class CoreModule {}
```
### **5. Reusable Components**

**Definition:**
**Reusable Components** are generic Angular components that can be used in multiple places without rewriting the same code.

**Key Features:**

* Promotes **code reuse**.
* Reduces code duplication.
* Easy to maintain and update.
* Configurable using **`@Input()`** and **`@Output()`**.

**How it works:**

* Create a generic component.
* Pass data using `@Input()`.
* Send events to the parent using `@Output()`.

**When to use:**
Use for common UI elements like **buttons**, **tables**, **cards**, **modals**, and **forms**.

**Code Example:**

```typescript
import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-button',
  template: `<button>{{ label }}</button>`
})
export class ButtonComponent {
  @Input() label = '';
}
```

```html
<app-button [label]="'Save'"></app-button>
```

### **6. Micro Frontends**

**Definition:**
**Micro Frontends** is an architecture where a large frontend application is divided into smaller, independent applications that work together.

**Key Features:**

* Independent **development** and **deployment**.
* Teams can work on different modules separately.
* Improves scalability and maintainability.
* Commonly implemented using **Webpack Module Federation**.

**How it works:**

* Each micro frontend is built and deployed independently.
* A host application loads remote applications at runtime.

**When to use:**
Use for **large enterprise applications** where multiple teams manage different business domains.

**Code Example:**

```javascript
// webpack.config.js
new ModuleFederationPlugin({
  name: "shell",
  remotes: {
    products: "products@http://localhost:4201/remoteEntry.js"
  }
});
```

### **7. Large Scale Angular Architecture**

**Definition:**
**Large Scale Angular Architecture** is a structured approach for organizing and managing complex Angular applications with many features and teams.

**Key Features:**

* Uses **Feature Modules** or **Standalone Features**.
* Separates **Core**, **Shared**, and **Feature** layers.
* Supports **lazy loading** and **state management**.
* Encourages reusable and maintainable code.

**How it works:**

* Divide the application into independent business modules.
* Keep common services in the **Core Module** and reusable UI elements in the **Shared Module**.
* Load feature modules only when required.

**When to use:**
Use for **enterprise-level** or **large multi-team projects** with many features.

**Example Structure:**

```text
app/
├── core/
├── shared/
├── features/
│   ├── user/
│   ├── product/
│   └── admin/
├── layout/
└── app.routes.ts
```


# ✅ **18. Real-Time Interview Scenarios**

### **1. Auth Flow Implementation**

**Definition:**
**Authentication Flow** is the process of verifying a user and controlling access to protected resources.

**Key Features:**

* User **login** and **logout**.
* Uses **JWT token** or session.
* Protects routes with **Auth Guards**.
* Adds token to API requests using **HTTP Interceptors**.

**How it works:**

1. User logs in with credentials.
2. Server returns a **JWT token**.
3. Token is stored securely.
4. Interceptor attaches the token to every API request.
5. Route Guard checks authentication before navigation.

**When to use:**
Use in applications that require **user authentication and authorization**.

**Code Example:**

```typescript
login(user: any) {
  return this.http.post('/api/login', user);
}
```

```typescript
export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const token = localStorage.getItem('token');
  return next(req.clone({
    setHeaders: { Authorization: `Bearer ${token}` }
  }));
};
```

### **2. API Caching**

**Definition:**
**API Caching** stores API responses temporarily to avoid unnecessary network requests.

**Key Features:**

* Improves application performance.
* Reduces server load.
* Avoids repeated API calls for the same data.

**How it works:**

* Save the API response in memory or a service.
* Return cached data if available.
* Call the API again only when data expires or changes.

**When to use:**
Use for **static or rarely changing data** like product lists, settings, or lookup values.

**Code Example:**

```typescript
private users$?: Observable<any>;

getUsers() {
  if (!this.users$) {
    this.users$ = this.http.get('/api/users').pipe(
      shareReplay(1)
    );
  }
  return this.users$;
}
```

### **3. Infinite Scrolling**

**Definition:**
**Infinite Scrolling** loads additional data automatically as the user scrolls down the page.

**Key Features:**

* Improves user experience.
* Loads data in small chunks.
* Reduces initial page loading time.

**How it works:**

* Detect when the user reaches the bottom of the page.
* Request the next set of records from the API.
* Append new data to the existing list.

**When to use:**
Use for **social feeds**, **product catalogs**, and **large datasets**.

**Code Example:**

```typescript
onScroll() {
  this.page++;
  this.loadUsers(this.page);
}
```

```html
<div (scroll)="onScroll()">
  <!-- User list -->
</div>
```

### **4. Search with Debounce**

**Definition:**
**Debounce** delays the search request until the user stops typing for a specified time.

**Key Features:**

* Reduces unnecessary API calls.
* Improves performance.
* Provides a smoother search experience.

**How it works:**

* Listen to input changes.
* Wait for a short delay using `debounceTime()`.
* Send the API request only after the delay.

**When to use:**
Use for **search boxes**, **autocomplete**, and **live filtering**.

**Code Example:**

```typescript
searchControl.valueChanges.pipe(
  debounceTime(300),
  distinctUntilChanged()
).subscribe(value => {
  this.search(value);
});
```

### **5. File Upload**

**Definition:**
**File Upload** allows users to send files from the browser to the server.

**Key Features:**

* Uses **`FormData`**.
* Supports multiple file types.
* Can show upload progress.

**How it works:**

* User selects a file.
* Create a `FormData` object.
* Append the file and send it using `HttpClient`.

**When to use:**
Use for uploading **images**, **documents**, **PDFs**, or other user files.

**Code Example:**

```typescript
uploadFile(event: any) {
  const file = event.target.files[0];
  const formData = new FormData();

  formData.append('file', file);

  this.http.post('/api/upload', formData)
    .subscribe();
}
```

```html
<input type="file" (change)="uploadFile($event)">
```

### **6. Dynamic Forms**

**Definition:**
**Dynamic Forms** are forms that are created at runtime based on configuration or data instead of hardcoded HTML.

**Key Features:**

* Generates form fields dynamically.
* Easy to add or remove controls.
* Works well with **Reactive Forms**.
* Reduces duplicate code.

**How it works:**

* Store form field definitions in a JSON or configuration object.
* Loop through the configuration and create controls using `FormBuilder`.

**When to use:**
Use when form structure changes frequently, such as **survey forms**, **registration forms**, or **admin panels**.

**Code Example:**

```typescript
fields = [
  { name: 'firstName', value: '' },
  { name: 'email', value: '' }
];

this.form = this.fb.group(
  this.fields.reduce((acc, field) => {
    acc[field.name] = [field.value];
    return acc;
  }, {} as any)
);
```

```html
<div *ngFor="let field of fields">
  <input [formControlName]="field.name">
</div>
```

### **7. Role-Based Access Control (RBAC)**

**Definition:**
**RBAC (Role-Based Access Control)** restricts access to features and pages based on the user's role.

**Key Features:**

* Supports roles like **Admin**, **User**, and **Manager**.
* Protects routes and UI elements.
* Improves application security.

**How it works:**

* Store the user's role after login.
* Check the role using **Route Guards** or conditional rendering.
* Allow or deny access based on permissions.

**When to use:**
Use in applications where different users have different access levels.

**Code Example:**

```typescript
export const adminGuard: CanActivateFn = () => {
  return localStorage.getItem('role') === 'ADMIN';
};
```

```html
<button *ngIf="userRole === 'ADMIN'">
  Delete User
</button>
```

### **8. Dashboard Design**

**Definition:**
**Dashboard Design** is the process of organizing and displaying important data and actions in a single, user-friendly interface.

**Key Features:**

* Modular and reusable widgets.
* Responsive layout.
* Displays charts, tables, and summary cards.
* Supports lazy loading and API integration.

**How it works:**

* Divide the dashboard into reusable components.
* Fetch data from APIs.
* Update widgets dynamically based on user actions.

**When to use:**
Use for **admin panels**, **analytics pages**, and **business monitoring systems**.

**Code Example:**

```html
<div class="dashboard">
  <app-summary-card></app-summary-card>
  <app-chart></app-chart>
  <app-user-table></app-user-table>
</div>
```

### **9. Performance Optimization Cases**

**Definition:**
**Performance Optimization** improves the speed and efficiency of an Angular application by reducing unnecessary rendering and network usage.

**Key Features:**

* **Lazy Loading** for modules.
* **OnPush Change Detection**.
* **TrackBy** with `*ngFor` or `@for`.
* **API Caching** and **Debounce**.
* **AOT Compilation** and **Tree Shaking**.

**How it works:**

* Load modules only when required.
* Re-render components only when input data changes.
* Reuse DOM elements with `trackBy`.
* Minimize API calls and bundle size.

**When to use:**
Use in **large applications**, **dashboards**, or whenever performance issues occur.

**Code Example:**

```typescript
@Component({
  selector: 'app-users',
  changeDetection: ChangeDetectionStrategy.OnPush,
  templateUrl: './users.component.html'
})
export class UsersComponent {}
```

```html
<li *ngFor="let user of users; trackBy: trackById">
  {{ user.name }}
</li>
```

```typescript
trackById(index: number, user: any) {
  return user.id;
}
```


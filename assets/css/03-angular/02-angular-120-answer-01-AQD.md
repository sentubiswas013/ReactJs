## 🔹 1. Angular Fundamentals
### 1. What is Angular?

Angular is a **TypeScript-based web framework** developed by Google for building dynamic single-page applications. It's a complete platform that includes:
- Component-based architecture
- Built-in routing and HTTP client
- Powerful CLI tools
- Two-way data binding
- Dependency injection

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

### 2. Difference between Angular and AngularJS?

**AngularJS (1.x)** is the old JavaScript framework, while **Angular (2+)** is a complete rewrite:

| AngularJS | Angular |
|-----------|---------|
| JavaScript | TypeScript |
| MVC architecture | Component-based |
| No mobile support | Mobile-first |
| Slower performance | Better performance |
| No CLI | Powerful CLI |

```javascript
// AngularJS (Old)
angular.module('app', [])
  .controller('MyCtrl', function($scope) {
    $scope.message = 'Hello';
  });
```

```typescript
// Angular (New)
@Component({
  selector: 'app-my',
  template: '<p>{{message}}</p>'
})
export class MyComponent {
  message = 'Hello';
}
```

---

### 3. Key Features Introduced Till Angular 18

**Major features across versions:**
- **Angular 2-4**: Component architecture, TypeScript, CLI
- **Angular 5-6**: Angular Elements, Tree-shakable providers
- **Angular 7-8**: Bundle budgets, Dynamic imports
- **Angular 9-11**: Ivy renderer, Strict mode
- **Angular 12-14**: Standalone components, Optional injectors
- **Angular 15-16**: Image optimization, Required inputs
- **Angular 17-18**: New control flow, Material 3, SSR improvements

```typescript
// Angular 17+ New Control Flow
@Component({
  template: `
    @if (user) {
      <p>Welcome {{user.name}}</p>
    } @else {
      <p>Please login</p>
    }
    
    @for (item of items; track item.id) {
      <div>{{item.name}}</div>
    }
  `
})
export class ModernComponent {
  user = { name: 'John' };
  items = [{ id: 1, name: 'Item 1' }];
}
```

---

### 4. What are Components?

Components are the **building blocks** of Angular applications. Each component controls a part of the screen called a view. They consist of:
- **TypeScript class** (logic)
- **HTML template** (view)
- **CSS styles** (styling)

```typescript
@Component({
  selector: 'app-user-card',
  template: `
    <div class="card">
      <h3>{{user.name}}</h3>
      <p>{{user.email}}</p>
      <button (click)="editUser()">Edit</button>
    </div>
  `,
  styles: [`
    .card { 
      border: 1px solid #ccc; 
      padding: 16px; 
      margin: 8px; 
    }
  `]
})
export class UserCardComponent {
  user = { name: 'John Doe', email: 'john@example.com' };
  
  editUser() {
    console.log('Editing user:', this.user.name);
  }
}
```

---

### 5. What are Modules and Why Are They Needed?

Modules are **containers** that group related components, services, and other code. They help organize applications and enable lazy loading. Every Angular app has at least one root module.

**Why needed:**
- Code organization
- Feature separation
- Lazy loading
- Dependency management

```typescript
// Feature module
@NgModule({
  declarations: [
    UserListComponent,
    UserCardComponent
  ],
  imports: [
    CommonModule,
    FormsModule
  ],
  providers: [UserService],
  exports: [UserListComponent]
})
export class UserModule { }

// App module
@NgModule({
  declarations: [AppComponent],
  imports: [
    BrowserModule,
    UserModule
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
```

---

### 6. What is a Template?

A template is the **HTML view** of a component with Angular-specific markup. It defines how the component renders and includes:
- Data binding
- Directives
- Event handling
- Template expressions

```typescript
@Component({
  selector: 'app-todo',
  template: `
    <!-- Interpolation -->
    <h2>{{title}}</h2>
    
    <!-- Property binding -->
    <input [value]="newTodo" [disabled]="isLoading">
    
    <!-- Event binding -->
    <button (click)="addTodo()">Add Todo</button>
    
    <!-- Two-way binding -->
    <input [(ngModel)]="newTodo" placeholder="Enter todo">
    
    <!-- Structural directive -->
    <ul>
      <li *ngFor="let todo of todos">
        {{todo.text}}
      </li>
    </ul>
  `
})
export class TodoComponent {
  title = 'My Todos';
  newTodo = '';
  isLoading = false;
  todos = [{ text: 'Learn Angular' }];
  
  addTodo() {
    if (this.newTodo.trim()) {
      this.todos.push({ text: this.newTodo });
      this.newTodo = '';
    }
  }
}
```

---

### 7. What are Decorators in Angular?

Decorators are **TypeScript features** that add metadata to classes, methods, or properties. Angular uses them to define components, services, and other elements.

**Common Angular decorators:**
- `@Component` - Defines a component
- `@Injectable` - Makes a class injectable
- `@Input` - Defines input properties
- `@Output` - Defines output events

```typescript
// Component decorator
@Component({
  selector: 'app-child',
  template: `
    <p>{{message}}</p>
    <button (click)="sendData()">Send</button>
  `
})
export class ChildComponent {
  // Input decorator
  @Input() message: string = '';
  
  // Output decorator
  @Output() dataEvent = new EventEmitter<string>();
  
  sendData() {
    this.dataEvent.emit('Hello from child');
  }
}

// Injectable decorator
@Injectable({
  providedIn: 'root'
})
export class DataService {
  getData() {
    return ['item1', 'item2'];
  }
}
```

---

### 8. What is a Directive? Types of Directives

Directives are **classes that add behavior** to elements in Angular templates. They extend HTML with custom functionality.

**Three types:**
1. **Component directives** - Directives with templates
2. **Structural directives** - Change DOM layout (*ngIf, *ngFor)
3. **Attribute directives** - Change element behavior (ngClass, ngStyle)

```typescript
// 1. Component Directive (already shown above)

// 2. Structural Directive Usage
@Component({
  template: `
    <!-- *ngIf - Conditional rendering -->
    <div *ngIf="showMessage">Message is visible</div>
    
    <!-- *ngFor - Loop through items -->
    <ul>
      <li *ngFor="let item of items; index as i">
        {{i + 1}}. {{item}}
      </li>
    </ul>
    
    <!-- *ngSwitch - Multiple conditions -->
    <div [ngSwitch]="status">
      <p *ngSwitchCase="'loading'">Loading...</p>
      <p *ngSwitchCase="'success'">Success!</p>
      <p *ngSwitchDefault>Unknown status</p>
    </div>
  `
})
export class StructuralExample {
  showMessage = true;
  items = ['Apple', 'Banana', 'Orange'];
  status = 'loading';
}

// 3. Attribute Directive Usage
@Component({
  template: `
    <!-- ngClass - Dynamic CSS classes -->
    <div [ngClass]="{'active': isActive, 'disabled': !isEnabled}">
      Dynamic classes
    </div>
    
    <!-- ngStyle - Dynamic styles -->
    <p [ngStyle]="{'color': textColor, 'font-size': fontSize + 'px'}">
      Styled text
    </p>
  `
})
export class AttributeExample {
  isActive = true;
  isEnabled = false;
  textColor = 'blue';
  fontSize = 16;
}

// Custom Attribute Directive
@Directive({
  selector: '[appHighlight]'
})
export class HighlightDirective {
  constructor(private el: ElementRef) {}
  
  @HostListener('mouseenter') onMouseEnter() {
    this.el.nativeElement.style.backgroundColor = 'yellow';
  }
  
  @HostListener('mouseleave') onMouseLeave() {
    this.el.nativeElement.style.backgroundColor = null;
  }
}

// Usage: <p appHighlight>Hover me!</p>
```

---

### 9. What are Services?

Services are **singleton classes** that handle business logic, data operations, and shared functionality across components. They keep components lean and focused on the view.

**Key benefits:**
- Code reusability
- Separation of concerns
- Centralized data management
- Easy testing

```typescript
// User service
@Injectable({
  providedIn: 'root'
})
export class UserService {
  private users: User[] = [];
  
  getUsers(): User[] {
    return this.users;
  }
  
  addUser(user: User): void {
    this.users.push(user);
  }
  
  getUserById(id: number): User | undefined {
    return this.users.find(u => u.id === id);
  }
}

// Using service in component
@Component({
  selector: 'app-user-list',
  template: `
    <div *ngFor="let user of users">
      {{user.name}} - {{user.email}}
    </div>
  `
})
export class UserListComponent implements OnInit {
  users: User[] = [];
  
  constructor(private userService: UserService) {}
  
  ngOnInit() {
    this.users = this.userService.getUsers();
  }
}
```

---

### 10. What is Dependency Injection?

Dependency Injection (DI) is a **design pattern** where Angular provides dependencies to a class instead of the class creating them. It promotes loose coupling and easier testing.

**Benefits:**
- Loose coupling
- Easy testing with mocks
- Better code organization
- Automatic instance management

```typescript
// Service to be injected
@Injectable({
  providedIn: 'root'
})
export class ApiService {
  getData() {
    return ['data1', 'data2'];
  }
}

// Component receiving dependency
@Component({
  selector: 'app-data',
  template: '<div *ngFor="let item of data">{{item}}</div>'
})
export class DataComponent {
  data: string[] = [];
  
  // DI happens here - Angular provides ApiService instance
  constructor(private apiService: ApiService) {
    this.data = this.apiService.getData();
  }
}

// Manual provider configuration (if needed)
@NgModule({
  providers: [
    { provide: ApiService, useClass: ApiService },
    { provide: 'API_URL', useValue: 'https://api.example.com' }
  ]
})
export class AppModule {}
```

---

### 11. What is Bootstrapping?

Bootstrapping is the **process of starting** an Angular application. It initializes the root module and component, setting up the entire application.

**Process:**
1. Load `main.ts`
2. Bootstrap root module
3. Create root component
4. Render to DOM

```typescript
// main.ts - Application entry point
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { AppModule } from './app/app.module';

// Bootstrap the root module
platformBrowserDynamic()
  .bootstrapModule(AppModule)
  .catch(err => console.error(err));

// app.module.ts - Root module
@NgModule({
  declarations: [AppComponent],
  imports: [BrowserModule],
  providers: [],
  bootstrap: [AppComponent] // Root component to bootstrap
})
export class AppModule {}

// Standalone bootstrap (Angular 14+)
// main.ts
import { bootstrapApplication } from '@angular/platform-browser';
import { AppComponent } from './app/app.component';

bootstrapApplication(AppComponent, {
  providers: [
    // providers here
  ]
});
```

---

### 12. Which File Loads First in Angular?

**`main.ts`** is the **first file** that loads in an Angular application. It's the entry point defined in `angular.json`.

**Loading sequence:**
1. `main.ts` (entry point)
2. Root module (AppModule)
3. Root component (AppComponent)
4. Other components as needed

```json
// angular.json - Defines entry point
{
  "projects": {
    "my-app": {
      "architect": {
        "build": {
          "options": {
            "main": "src/main.ts", // First file to load
            "polyfills": "src/polyfills.ts",
            "tsConfig": "tsconfig.app.json"
          }
        }
      }
    }
  }
}
```

```typescript
// main.ts - Application starts here
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { AppModule } from './app/app.module';

// This runs first
platformBrowserDynamic()
  .bootstrapModule(AppModule)
  .catch(err => console.error(err));
```

---

### 13. Can We Rename `main.ts`?

**Yes**, you can rename `main.ts`, but you must **update the configuration** in `angular.json` to point to the new filename.

```json
// angular.json - Update main entry point
{
  "projects": {
    "my-app": {
      "architect": {
        "build": {
          "options": {
            "main": "src/app-start.ts", // Renamed from main.ts
            "polyfills": "src/polyfills.ts"
          }
        }
      }
    }
  }
}
```

```typescript
// app-start.ts (renamed from main.ts)
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { AppModule } from './app/app.module';

platformBrowserDynamic()
  .bootstrapModule(AppModule)
  .catch(err => console.error(err));
```

**Note:** It's **not recommended** to rename `main.ts` as it's a standard convention that developers expect.

---

### 14. What is Data Binding? Types of Data Binding

Data binding is **communication between component and template**. It synchronizes data between TypeScript code and HTML view.

**Four types:**
1. **Interpolation** - Component to view
2. **Property binding** - Component to view
3. **Event binding** - View to component
4. **Two-way binding** - Both directions

```typescript
@Component({
  selector: 'app-binding-demo',
  template: `
    <!-- 1. Interpolation {{}} -->
    <h2>{{title}}</h2>
    <p>Count: {{count}}</p>
    
    <!-- 2. Property Binding [] -->
    <input [value]="inputValue" [disabled]="isDisabled">
    <img [src]="imageUrl" [alt]="imageAlt">
    
    <!-- 3. Event Binding () -->
    <button (click)="increment()">Click Me</button>
    <input (keyup)="onKeyUp($event)">
    
    <!-- 4. Two-way Binding [()] -->
    <input [(ngModel)]="name" placeholder="Enter name">
    <p>Hello {{name}}!</p>
  `
})
export class BindingDemoComponent {
  // Data properties
  title = 'Data Binding Demo';
  count = 0;
  inputValue = 'Initial value';
  isDisabled = false;
  imageUrl = 'assets/logo.png';
  imageAlt = 'Logo';
  name = '';
  
  // Event handlers
  increment() {
    this.count++;
  }
  
  onKeyUp(event: any) {
    console.log('Key pressed:', event.target.value);
  }
}
```

---

### 15. What are Lifecycle Hooks?

Lifecycle hooks are **methods** that Angular calls at specific moments in a component's lifecycle. They allow you to tap into key events from creation to destruction.

**Common hooks:**
- `ngOnInit` - After component initialization
- `ngOnDestroy` - Before component destruction
- `ngOnChanges` - When input properties change
- `ngAfterViewInit` - After view initialization

```typescript
@Component({
  selector: 'app-lifecycle',
  template: `
    <p>{{message}}</p>
    <input [(ngModel)]="userInput">
  `
})
export class LifecycleComponent implements OnInit, OnDestroy, OnChanges {
  @Input() data: string = '';
  message = 'Component loaded';
  userInput = '';
  private timer: any;
  
  // 1. Called after component creation
  ngOnInit() {
    console.log('Component initialized');
    this.timer = setInterval(() => {
      console.log('Timer tick');
    }, 1000);
  }
  
  // 2. Called when @Input properties change
  ngOnChanges(changes: SimpleChanges) {
    console.log('Input changed:', changes);
    if (changes['data']) {
      this.message = `Data updated: ${changes['data'].currentValue}`;
    }
  }
  
  // 3. Called after view initialization
  ngAfterViewInit() {
    console.log('View initialized');
  }
  
  // 4. Called before component destruction
  ngOnDestroy() {
    console.log('Component destroyed');
    if (this.timer) {
      clearInterval(this.timer);
    }
  }
}
```

---

### 16. Difference Between Constructor and `ngOnInit`

**Constructor** is for **dependency injection** and basic initialization, while **`ngOnInit`** is for **component-specific logic** after Angular sets up the component.

| Constructor | ngOnInit |
|-------------|----------|
| TypeScript feature | Angular lifecycle hook |
| Dependency injection | Component initialization |
| Before Angular setup | After Angular setup |
| No access to @Input | Access to @Input values |
| Called once | Called once after constructor |

```typescript
@Component({
  selector: 'app-init-demo',
  template: `
    <p>{{message}}</p>
    <p>Input value: {{inputData}}</p>
  `
})
export class InitDemoComponent implements OnInit {
  @Input() inputData: string = '';
  message = '';
  
  // Constructor - for dependency injection
  constructor(
    private userService: UserService,
    private router: Router
  ) {
    console.log('Constructor called');
    // ❌ Don't do heavy operations here
    // ❌ @Input values not available yet
    console.log('Input data:', this.inputData); // undefined
    
    // ✅ Good for basic setup
    this.message = 'Component created';
  }
  
  // ngOnInit - for component logic
  ngOnInit() {
    console.log('ngOnInit called');
    // ✅ @Input values are available
    console.log('Input data:', this.inputData); // actual value
    
    // ✅ Good for:
    // - API calls
    // - Complex initialization
    // - Setting up subscriptions
    this.loadUserData();
    this.message = `Initialized with: ${this.inputData}`;
  }
  
  private loadUserData() {
    this.userService.getUsers().subscribe(users => {
      console.log('Users loaded:', users);
    });
  }
}
```

---

## 📝 Summary

Angular is a powerful TypeScript framework that uses:
- **Components** as building blocks with lifecycle hooks
- **Services** for business logic and dependency injection
- **Modules** for organization and bootstrapping
- **Templates** with four types of data binding
- **Decorators** for metadata
- **Directives** for DOM manipulation

Key concepts:
- **Constructor** for DI, **ngOnInit** for component logic
- **main.ts** loads first and bootstraps the application
- **Services** provide shared functionality across components
- **Data binding** enables communication between component and template

Each concept works together to create maintainable, scalable web applications with clear separation of concerns and powerful data binding capabilities.

## 🔹 2. Templates & Directives

#### 1. What is `ngIf`?

`ngIf` is a **structural directive** that conditionally adds or removes elements from the DOM based on a boolean expression. It completely removes elements when false, not just hides them.

**Key points:**
- Removes/adds DOM elements
- Better performance than hiding with CSS
- Can use with `else` template

```typescript
@Component({
  template: `
    <!-- Basic ngIf -->
    <div *ngIf="isLoggedIn">Welcome back!</div>
    
    <!-- ngIf with else -->
    <div *ngIf="user; else loginTemplate">
      Hello {{user.name}}
    </div>
    <ng-template #loginTemplate>
      <button>Please Login</button>
    </ng-template>
    
    <!-- ngIf with condition -->
    <p *ngIf="items.length > 0">You have {{items.length}} items</p>
  `
})
export class NgIfComponent {
  isLoggedIn = true;
  user = { name: 'John' };
  items = ['item1', 'item2'];
}
```

---

#### 2. What is `ngFor`?

`ngFor` is a **structural directive** that repeats a template for each item in a collection. It's Angular's way of creating loops in templates.

**Features:**
- Iterates over arrays, objects
- Provides index and other variables
- Tracks items for performance

```typescript
@Component({
  template: `
    <!-- Basic ngFor -->
    <li *ngFor="let item of items">{{item}}</li>
    
    <!-- ngFor with index -->
    <div *ngFor="let user of users; index as i">
      {{i + 1}}. {{user.name}}
    </div>
    
    <!-- ngFor with multiple variables -->
    <p *ngFor="let item of products; 
              index as i; 
              first as isFirst; 
              last as isLast">
      <span [class.highlight]="isFirst">{{item.name}}</span>
    </p>
  `
})
export class NgForComponent {
  items = ['Apple', 'Banana', 'Orange'];
  users = [
    { name: 'John' },
    { name: 'Jane' }
  ];
  products = [
    { name: 'Laptop' },
    { name: 'Phone' }
  ];
}
```

---

#### 3. Difference Between `ngFor` and `ngForOf`?

**There's no difference** - `ngForOf` is the **actual directive name**, while `*ngFor` is **syntactic sugar**. Angular transforms `*ngFor` into `ngForOf` internally.

```typescript
@Component({
  template: `
    <!-- These are IDENTICAL -->
    
    <!-- Syntactic sugar (what we write) -->
    <div *ngFor="let item of items">{{item}}</div>
    
    <!-- What Angular actually creates -->
    <ng-template ngFor let-item [ngForOf]="items">
      <div>{{item}}</div>
    </ng-template>
  `
})
export class NgForVsNgForOfComponent {
  items = ['item1', 'item2', 'item3'];
}
```

**Key point:** Always use `*ngFor` - it's cleaner and more readable.

---

#### 4. What is `ngClass`?

`ngClass` is an **attribute directive** that dynamically adds or removes CSS classes based on expressions. It's perfect for conditional styling.

**Three ways to use:**
- String
- Array
- Object (most common)

```typescript
@Component({
  template: `
    <!-- Object syntax (most common) -->
    <div [ngClass]="{
      'active': isActive,
      'disabled': !isEnabled,
      'highlight': score > 80
    }">Dynamic Classes</div>
    
    <!-- Array syntax -->
    <p [ngClass]="['class1', 'class2', dynamicClass]">Array Classes</p>
    
    <!-- String syntax -->
    <span [ngClass]="getClasses()">Method Classes</span>
    
    <!-- Conditional class -->
    <button [ngClass]="buttonType">{{buttonText}}</button>
  `,
  styles: [`
    .active { background: green; }
    .disabled { opacity: 0.5; }
    .highlight { border: 2px solid yellow; }
  `]
})
export class NgClassComponent {
  isActive = true;
  isEnabled = false;
  score = 85;
  dynamicClass = 'special';
  buttonType = 'btn-primary';
  buttonText = 'Click Me';
  
  getClasses() {
    return this.isActive ? 'active highlight' : 'inactive';
  }
}
```

---

#### 5. What is a Template Reference Variable?

Template reference variables are **references to DOM elements or components** in templates. They're created with `#` and can be used anywhere in the template.

**Uses:**
- Access DOM elements
- Call component methods
- Pass to other components

```typescript
@Component({
  template: `
    <!-- Reference to input element -->
    <input #nameInput type="text" placeholder="Enter name">
    <button (click)="greet(nameInput.value)">Greet</button>
    
    <!-- Reference to component -->
    <app-child #childComp></app-child>
    <button (click)="childComp.doSomething()">Call Child Method</button>
    
    <!-- Reference in ngFor -->
    <div *ngFor="let item of items">
      <input #itemInput [value]="item">
      <button (click)="updateItem(itemInput.value)">Update</button>
    </div>
    
    <!-- Reference with ngIf -->
    <div *ngIf="showForm">
      <form #userForm="ngForm">
        <input name="email" ngModel required>
        <p>Form valid: {{userForm.valid}}</p>
      </form>
    </div>
  `
})
export class TemplateRefComponent {
  items = ['item1', 'item2'];
  showForm = true;
  
  greet(name: string) {
    alert(`Hello ${name}!`);
  }
  
  updateItem(value: string) {
    console.log('Updated:', value);
  }
}
```

---

#### 6. What is `ng-content`?

`ng-content` enables **content projection** - it allows you to pass HTML content from parent to child component. It's Angular's way of creating reusable component templates.

**Types:**
- Single slot projection
- Multi-slot projection with `select`

```typescript
// Child component with ng-content
@Component({
  selector: 'app-card',
  template: `
    <div class="card">
      <div class="header">
        <ng-content select="[slot=header]"></ng-content>
      </div>
      <div class="body">
        <ng-content></ng-content>
      </div>
      <div class="footer">
        <ng-content select="[slot=footer]"></ng-content>
      </div>
    </div>
  `,
  styles: [`
    .card { border: 1px solid #ccc; padding: 16px; }
    .header { font-weight: bold; }
    .footer { font-size: 12px; color: gray; }
  `]
})
export class CardComponent {}

// Parent component using projection
@Component({
  template: `
    <app-card>
      <h2 slot="header">Card Title</h2>
      <p>This is the main content of the card.</p>
      <small slot="footer">Last updated: today</small>
    </app-card>
  `
})
export class ParentComponent {}
```

---

#### 7. What is `ng-template`?

`ng-template` is a **template element** that defines reusable template blocks. It doesn't render by default - you need to use it with structural directives or `ngTemplateOutlet`.

**Uses:**
- Conditional templates with `ngIf`
- Reusable template blocks
- Dynamic template rendering

```typescript
@Component({
  template: `
    <!-- Template with ngIf else -->
    <div *ngIf="isLoggedIn; else loginTemplate">
      Welcome back!
    </div>
    <ng-template #loginTemplate>
      <button (click)="login()">Login</button>
    </ng-template>
    
    <!-- Reusable template -->
    <ng-template #userTemplate let-user="user" let-index="index">
      <div class="user-card">
        {{index + 1}}. {{user.name}} - {{user.email}}
      </div>
    </ng-template>
    
    <!-- Using template with ngFor -->
    <div *ngFor="let user of users; index as i">
      <ng-container 
        [ngTemplateOutlet]="userTemplate"
        [ngTemplateOutletContext]="{user: user, index: i}">
      </ng-container>
    </div>
    
    <!-- Conditional template rendering -->
    <ng-container [ngTemplateOutlet]="currentTemplate"></ng-container>
    <button (click)="switchTemplate()">Switch Template</button>
    
    <ng-template #template1>
      <p>This is template 1</p>
    </ng-template>
    <ng-template #template2>
      <p>This is template 2</p>
    </ng-template>
  `
})
export class NgTemplateComponent {
  isLoggedIn = false;
  users = [
    { name: 'John', email: 'john@example.com' },
    { name: 'Jane', email: 'jane@example.com' }
  ];
  
  @ViewChild('template1') template1!: TemplateRef<any>;
  @ViewChild('template2') template2!: TemplateRef<any>;
  currentTemplate!: TemplateRef<any>;
  
  ngAfterViewInit() {
    this.currentTemplate = this.template1;
  }
  
  login() {
    this.isLoggedIn = true;
  }
  
  switchTemplate() {
    this.currentTemplate = this.currentTemplate === this.template1 
      ? this.template2 : this.template1;
  }
}
```

---

#### 8. What is `trackBy` and Why is it Important?

`trackBy` is a **performance optimization** for `ngFor` that tells Angular how to identify list items. It prevents unnecessary DOM manipulation when the list changes.

**Benefits:**
- Improves performance with large lists
- Prevents flickering during updates
- Maintains component state

```typescript
@Component({
  template: `
    <!-- Without trackBy (poor performance) -->
    <div *ngFor="let user of users">
      {{user.name}} - {{user.email}}
    </div>
    
    <!-- With trackBy (optimized) -->
    <div *ngFor="let user of users; trackBy: trackByUserId">
      {{user.name}} - {{user.email}}
      <input [(ngModel)]="user.note" placeholder="Add note">
    </div>
    
    <button (click)="addUser()">Add User</button>
    <button (click)="updateUsers()">Update List</button>
  `
})
export class TrackByComponent {
  users = [
    { id: 1, name: 'John', email: 'john@example.com', note: '' },
    { id: 2, name: 'Jane', email: 'jane@example.com', note: '' }
  ];
  
  // TrackBy function - returns unique identifier
  trackByUserId(index: number, user: any): number {
    return user.id; // Track by unique ID, not index
  }
  
  addUser() {
    const newId = this.users.length + 1;
    this.users.push({
      id: newId,
      name: `User ${newId}`,
      email: `user${newId}@example.com`,
      note: ''
    });
  }
  
  updateUsers() {
    // Simulate data update - trackBy preserves input state
    this.users = [...this.users].reverse();
  }
}
```

**Without trackBy:** Angular recreates all DOM elements
**With trackBy:** Angular only updates changed elements

---

#### 9. What are Pure vs Impure Pipes?

Pipes transform data in templates. **Pure pipes** only execute when input changes, while **impure pipes** execute on every change detection cycle.

**Pure pipes (default):**
- Better performance
- Only run when input changes
- Most built-in pipes are pure

**Impure pipes:**
- Run on every change detection
- Use for dynamic data
- Set `pure: false`

```typescript
// Pure pipe (default)
@Pipe({
  name: 'multiply'
})
export class MultiplyPipe implements PipeTransform {
  transform(value: number, factor: number = 1): number {
    console.log('Pure pipe executed'); // Only when input changes
    return value * factor;
  }
}

// Impure pipe
@Pipe({
  name: 'currentTime',
  pure: false // Makes it impure
})
export class CurrentTimePipe implements PipeTransform {
  transform(): string {
    console.log('Impure pipe executed'); // Every change detection
    return new Date().toLocaleTimeString();
  }
}

@Component({
  template: `
    <!-- Pure pipe - only updates when 'number' changes -->
    <p>{{number | multiply:3}}</p>
    
    <!-- Impure pipe - updates constantly -->
    <p>Current time: {{'' | currentTime}}</p>
    
    <!-- Built-in pure pipes -->
    <p>{{name | uppercase}}</p>
    <p>{{price | currency:'USD'}}</p>
    
    <button (click)="increment()">Increment</button>
    <input [(ngModel)]="name" placeholder="Enter name">
  `
})
export class PipeComponent {
  number = 5;
  name = 'angular';
  price = 99.99;
  
  increment() {
    this.number++;
  }
}
```

**Performance tip:** Use pure pipes whenever possible. Only use impure pipes when you need real-time updates.

---

## 📝 Summary

**Templates & Directives** provide powerful ways to create dynamic UIs:

- **`ngIf`** - Conditional DOM rendering
- **`ngFor`** - List iteration with performance optimization via `trackBy`
- **`ngClass`** - Dynamic CSS class management
- **Template reference variables** - Access DOM elements and components
- **`ng-content`** - Content projection for reusable components
- **`ng-template`** - Reusable template blocks
- **Pipes** - Data transformation (prefer pure for performance)

These features enable you to build flexible, performant Angular applications with clean, maintainable templates.


## 🔹 3. Forms

#### 1. What are Angular Forms?

Angular forms are **powerful tools** for handling user input, validation, and data binding. They provide two approaches: **template-driven** and **reactive forms** for different use cases.

**Key features:**
- Two-way data binding
- Built-in validation
- Custom validators
- Form state tracking
- Error handling

```typescript
// Basic form setup
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
  imports: [
    FormsModule,        // For template-driven forms
    ReactiveFormsModule // For reactive forms
  ]
})
export class AppModule {}

// Simple form component
@Component({
  template: `
    <form>
      <input type="text" placeholder="Enter name">
      <input type="email" placeholder="Enter email">
      <button type="submit">Submit</button>
    </form>
  `
})
export class BasicFormComponent {}
```

---

#### 2. Template-driven vs Reactive Forms

**Template-driven forms** use directives in templates, while **reactive forms** use FormBuilder in components. Choose based on complexity and control needs.

| Template-driven | Reactive |
|----------------|----------|
| Simple forms | Complex forms |
| Less code | More control |
| Template-based | Component-based |
| Async validation harder | Easy async validation |

```typescript
// TEMPLATE-DRIVEN FORM
@Component({
  template: `
    <form #userForm="ngForm" (ngSubmit)="onSubmit(userForm)">
      <input 
        name="name" 
        [(ngModel)]="user.name" 
        required 
        #name="ngModel">
      <div *ngIf="name.invalid && name.touched">Name required</div>
      
      <input 
        name="email" 
        [(ngModel)]="user.email" 
        email 
        required>
      <button [disabled]="userForm.invalid">Submit</button>
    </form>
  `
})
export class TemplateDrivenComponent {
  user = { name: '', email: '' };
  
  onSubmit(form: NgForm) {
    console.log('Form data:', this.user);
  }
}

// REACTIVE FORM
@Component({
  template: `
    <form [formGroup]="userForm" (ngSubmit)="onSubmit()">
      <input formControlName="name">
      <div *ngIf="userForm.get('name')?.invalid && userForm.get('name')?.touched">
        Name required
      </div>
      
      <input formControlName="email">
      <button [disabled]="userForm.invalid">Submit</button>
    </form>
  `
})
export class ReactiveFormComponent {
  userForm = this.fb.group({
    name: ['', Validators.required],
    email: ['', [Validators.required, Validators.email]]
  });
  
  constructor(private fb: FormBuilder) {}
  
  onSubmit() {
    console.log('Form data:', this.userForm.value);
  }
}
```

---

#### 3. What is `ngModel`?

`ngModel` is a **directive** that creates two-way data binding between form controls and component properties. It's the core of template-driven forms.

**Features:**
- Two-way binding with `[(ngModel)]`
- Form validation states
- Tracks user interaction
- Works with form controls

```typescript
@Component({
  template: `
    <!-- Basic two-way binding -->
    <input [(ngModel)]="username" name="username">
    <p>Hello {{username}}!</p>
    
    <!-- With validation -->
    <input 
      [(ngModel)]="email" 
      name="email" 
      email 
      required 
      #emailRef="ngModel">
    
    <!-- Show validation states -->
    <div *ngIf="emailRef.invalid && emailRef.touched">
      <span *ngIf="emailRef.errors?.['required']">Email required</span>
      <span *ngIf="emailRef.errors?.['email']">Invalid email</span>
    </div>
    
    <!-- Form states -->
    <p>Valid: {{emailRef.valid}}</p>
    <p>Touched: {{emailRef.touched}}</p>
    <p>Dirty: {{emailRef.dirty}}</p>
    
    <!-- Standalone ngModel (without form) -->
    <input [(ngModel)]="searchTerm" placeholder="Search...">
  `
})
export class NgModelComponent {
  username = '';
  email = '';
  searchTerm = '';
}
```

---

#### 4. How Do You Validate Forms?

Angular provides **built-in validators** and supports **custom validation**. Validation works differently in template-driven vs reactive forms.

**Built-in validators:**
- `required`, `email`, `minlength`, `maxlength`
- `min`, `max`, `pattern`

```typescript
// TEMPLATE-DRIVEN VALIDATION
@Component({
  template: `
    <form #userForm="ngForm">
      <!-- Built-in validators -->
      <input 
        name="name" 
        [(ngModel)]="user.name"
        required 
        minlength="3"
        #name="ngModel">
      
      <input 
        name="age" 
        [(ngModel)]="user.age"
        type="number"
        min="18"
        max="100"
        #age="ngModel">
      
      <input 
        name="phone" 
        [(ngModel)]="user.phone"
        pattern="[0-9]{10}"
        #phone="ngModel">
      
      <!-- Error messages -->
      <div *ngIf="name.errors && name.touched">
        <span *ngIf="name.errors['required']">Name required</span>
        <span *ngIf="name.errors['minlength']">Min 3 characters</span>
      </div>
      
      <button [disabled]="userForm.invalid">Submit</button>
    </form>
  `
})
export class TemplateValidationComponent {
  user = { name: '', age: null, phone: '' };
}

// REACTIVE FORM VALIDATION
@Component({
  template: `
    <form [formGroup]="userForm" (ngSubmit)="onSubmit()">
      <input formControlName="name">
      <div *ngIf="getError('name')">{{getError('name')}}</div>
      
      <input formControlName="email">
      <div *ngIf="getError('email')">{{getError('email')}}</div>
      
      <input formControlName="age" type="number">
      <div *ngIf="getError('age')">{{getError('age')}}</div>
      
      <button [disabled]="userForm.invalid">Submit</button>
    </form>
  `
})
export class ReactiveValidationComponent {
  userForm = this.fb.group({
    name: ['', [Validators.required, Validators.minLength(3)]],
    email: ['', [Validators.required, Validators.email]],
    age: ['', [Validators.required, Validators.min(18), Validators.max(100)]]
  });
  
  constructor(private fb: FormBuilder) {}
  
  getError(field: string): string {
    const control = this.userForm.get(field);
    if (control?.errors && control.touched) {
      if (control.errors['required']) return `${field} is required`;
      if (control.errors['email']) return 'Invalid email';
      if (control.errors['minlength']) return `Min ${control.errors['minlength'].requiredLength} characters`;
      if (control.errors['min']) return `Min value ${control.errors['min'].min}`;
    }
    return '';
  }
  
  onSubmit() {
    if (this.userForm.valid) {
      console.log(this.userForm.value);
    }
  }
}
```

---

#### 5. How Do You Create Custom Validators?

Custom validators are **functions** that return validation errors or null. They can be synchronous or asynchronous for complex validation logic.

**Types:**
- Sync validators (immediate validation)
- Async validators (API calls, database checks)
- Cross-field validators

```typescript
// CUSTOM SYNC VALIDATORS
export class CustomValidators {
  // Simple custom validator
  static noSpaces(control: AbstractControl): ValidationErrors | null {
    if (control.value && control.value.includes(' ')) {
      return { noSpaces: { message: 'Spaces not allowed' } };
    }
    return null;
  }
  
  // Parameterized validator
  static minAge(minAge: number): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      if (control.value && control.value < minAge) {
        return { minAge: { requiredAge: minAge, actualAge: control.value } };
      }
      return null;
    };
  }
}

// CUSTOM ASYNC VALIDATOR
@Injectable()
export class AsyncValidators {
  constructor(private http: HttpClient) {}
  
  // Check if username exists
  usernameExists(control: AbstractControl): Observable<ValidationErrors | null> {
    if (!control.value) return of(null);
    
    return this.http.get(`/api/check-username/${control.value}`).pipe(
      map((exists: any) => exists ? { usernameExists: true } : null),
      catchError(() => of(null))
    );
  }
}

// USING CUSTOM VALIDATORS
@Component({
  template: `
    <form [formGroup]="userForm">
      <input formControlName="username" placeholder="Username">
      <div *ngIf="getError('username')">{{getError('username')}}</div>
      
      <input formControlName="age" type="number" placeholder="Age">
      <div *ngIf="getError('age')">{{getError('age')}}</div>
      
      <input formControlName="password" type="password" placeholder="Password">
      <input formControlName="confirmPassword" type="password" placeholder="Confirm Password">
      <div *ngIf="userForm.errors?.['passwordMismatch']">Passwords don't match</div>
      
      <button [disabled]="userForm.invalid || userForm.pending">Submit</button>
    </form>
  `
})
export class CustomValidatorComponent {
  userForm = this.fb.group({
    username: ['', 
      [Validators.required, CustomValidators.noSpaces],
      [this.asyncValidators.usernameExists.bind(this.asyncValidators)]
    ],
    age: ['', [Validators.required, CustomValidators.minAge(18)]],
    password: ['', Validators.required],
    confirmPassword: ['', Validators.required]
  }, { 
    validators: this.passwordMatchValidator 
  });
  
  constructor(
    private fb: FormBuilder,
    private asyncValidators: AsyncValidators
  ) {}
  
  // Cross-field validator
  passwordMatchValidator(form: AbstractControl): ValidationErrors | null {
    const password = form.get('password');
    const confirmPassword = form.get('confirmPassword');
    
    if (password?.value !== confirmPassword?.value) {
      return { passwordMismatch: true };
    }
    return null;
  }
  
  getError(field: string): string {
    const control = this.userForm.get(field);
    if (control?.errors && control.touched) {
      if (control.errors['required']) return `${field} is required`;
      if (control.errors['noSpaces']) return control.errors['noSpaces'].message;
      if (control.errors['minAge']) return `Must be at least ${control.errors['minAge'].requiredAge} years old`;
      if (control.errors['usernameExists']) return 'Username already exists';
    }
    return '';
  }
}
```

---

## 📝 Summary

**Angular Forms** provide powerful tools for user input handling:

- **Two approaches**: Template-driven (simple) vs Reactive (complex)
- **`ngModel`**: Two-way data binding for template-driven forms
- **Built-in validation**: Required, email, min/max, pattern validators
- **Custom validators**: Sync, async, and cross-field validation
- **Form states**: Valid, invalid, touched, dirty, pending

**Choose template-driven** for simple forms with basic validation.
**Choose reactive** for complex forms with dynamic validation and better testing.

Both approaches provide robust form handling with comprehensive validation capabilities for building user-friendly applications.

## 🔹 4. Routing & Navigation

#### 1. What is Angular Router?

Angular Router is a **navigation service** that enables navigation between different views/components in single-page applications. It manages URL changes and component rendering without full page reloads.

**Key features:**
- URL-based navigation
- Component rendering
- Route parameters
- Nested routing
- Navigation guards

```typescript
// Basic routing setup
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'about', component: AboutComponent },
  { path: 'users/:id', component: UserComponent },
  { path: '**', component: NotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}

// App component with router outlet
@Component({
  template: `
    <nav>
      <a routerLink="/">Home</a>
      <a routerLink="/about">About</a>
      <a [routerLink]="['/users', userId]">User Profile</a>
    </nav>
    <router-outlet></router-outlet>
  `
})
export class AppComponent {
  userId = 123;
}
```

---

#### 2. What is RouterModule?

RouterModule is an **Angular module** that provides routing functionality. It exports router directives, services, and configuration methods for setting up navigation.

**Two methods:**
- `forRoot()` - Root module configuration
- `forChild()` - Feature module configuration

```typescript
// ROOT MODULE (app-routing.module.ts)
const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'products', loadChildren: () => import('./products/products.module').then(m => m.ProductsModule) },
  { path: 'users', loadChildren: () => import('./users/users.module').then(m => m.UsersModule) }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)], // Root configuration
  exports: [RouterModule]
})
export class AppRoutingModule {}

// FEATURE MODULE (products-routing.module.ts)
const productRoutes: Routes = [
  { path: '', component: ProductListComponent },
  { path: ':id', component: ProductDetailComponent },
  { path: ':id/edit', component: ProductEditComponent }
];

@NgModule({
  imports: [RouterModule.forChild(productRoutes)], // Child configuration
  exports: [RouterModule]
})
export class ProductsRoutingModule {}

// Using Router service for programmatic navigation
@Component({
  template: `
    <button (click)="goToProduct(123)">View Product</button>
    <button (click)="goBack()">Go Back</button>
  `
})
export class NavigationComponent {
  constructor(private router: Router, private location: Location) {}
  
  goToProduct(id: number) {
    this.router.navigate(['/products', id]);
  }
  
  goBack() {
    this.location.back();
  }
}
```

---

#### 3. What is Lazy Loading?

Lazy loading **loads feature modules on-demand** instead of at application startup. It improves initial load time by splitting the application into smaller chunks.

**Benefits:**
- Faster initial load
- Smaller bundle size
- Better performance
- Code splitting

```typescript
// APP ROUTING - Lazy loaded modules
const routes: Routes = [
  { path: '', component: HomeComponent },
  { 
    path: 'admin', 
    loadChildren: () => import('./admin/admin.module').then(m => m.AdminModule),
    canLoad: [AuthGuard] // Optional guard
  },
  { 
    path: 'products', 
    loadChildren: () => import('./products/products.module').then(m => m.ProductsModule)
  }
];

// FEATURE MODULE (admin.module.ts)
const adminRoutes: Routes = [
  { path: '', component: AdminDashboardComponent },
  { path: 'users', component: AdminUsersComponent },
  { path: 'settings', component: AdminSettingsComponent }
];

@NgModule({
  declarations: [
    AdminDashboardComponent,
    AdminUsersComponent,
    AdminSettingsComponent
  ],
  imports: [
    CommonModule,
    RouterModule.forChild(adminRoutes)
  ]
})
export class AdminModule {}

// STANDALONE COMPONENT LAZY LOADING (Angular 14+)
const routes: Routes = [
  {
    path: 'profile',
    loadComponent: () => import('./profile/profile.component').then(c => c.ProfileComponent)
  }
];
```

---

#### 4. What are Route Guards?

Route guards are **interfaces** that control navigation to and from routes. They provide hooks to implement authentication, authorization, and data validation.

**Types of guards:**
- `CanActivate` - Can enter route
- `CanDeactivate` - Can leave route
- `CanLoad` - Can load lazy module
- `Resolve` - Pre-load data

```typescript
// CAN ACTIVATE GUARD
@Injectable()
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

// CAN DEACTIVATE GUARD
@Injectable()
export class UnsavedChangesGuard implements CanDeactivate<any> {
  canDeactivate(component: any): boolean {
    if (component.hasUnsavedChanges) {
      return confirm('You have unsaved changes. Do you want to leave?');
    }
    return true;
  }
}

// USING GUARDS IN ROUTES
const routes: Routes = [
  { 
    path: 'dashboard', 
    component: DashboardComponent,
    canActivate: [AuthGuard]
  },
  { 
    path: 'edit-profile', 
    component: EditProfileComponent,
    canActivate: [AuthGuard],
    canDeactivate: [UnsavedChangesGuard]
  },
  {
    path: 'admin',
    loadChildren: () => import('./admin/admin.module').then(m => m.AdminModule),
    canLoad: [AuthGuard]
  }
];
```

---

#### 5. How to Implement AuthGuard?

AuthGuard **protects routes** by checking user authentication status. It redirects unauthorized users to login and allows authenticated users to proceed.

**Implementation steps:**
- Create guard service
- Implement CanActivate interface
- Check authentication status
- Handle redirection

```typescript
// AUTH SERVICE
@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private isAuthenticated = false;
  
  login(username: string, password: string): boolean {
    // Simulate login logic
    if (username === 'admin' && password === 'password') {
      this.isAuthenticated = true;
      localStorage.setItem('token', 'fake-jwt-token');
      return true;
    }
    return false;
  }
  
  logout() {
    this.isAuthenticated = false;
    localStorage.removeItem('token');
  }
  
  isLoggedIn(): boolean {
    return this.isAuthenticated || !!localStorage.getItem('token');
  }
}

// AUTH GUARD
@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(
    private authService: AuthService,
    private router: Router
  ) {}
  
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): boolean {
    if (this.authService.isLoggedIn()) {
      return true;
    }
    
    // Store attempted URL for redirecting after login
    this.router.navigate(['/login'], { 
      queryParams: { returnUrl: state.url }
    });
    return false;
  }
}

// LOGIN COMPONENT
@Component({
  template: `
    <form (ngSubmit)="login()">
      <input [(ngModel)]="username" placeholder="Username" name="username">
      <input [(ngModel)]="password" type="password" placeholder="Password" name="password">
      <button type="submit">Login</button>
    </form>
  `
})
export class LoginComponent {
  username = '';
  password = '';
  
  constructor(
    private authService: AuthService,
    private router: Router,
    private route: ActivatedRoute
  ) {}
  
  login() {
    if (this.authService.login(this.username, this.password)) {
      const returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/dashboard';
      this.router.navigate([returnUrl]);
    } else {
      alert('Invalid credentials');
    }
  }
}

// PROTECTED ROUTES
const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { 
    path: 'dashboard', 
    component: DashboardComponent,
    canActivate: [AuthGuard]
  },
  { 
    path: 'profile', 
    component: ProfileComponent,
    canActivate: [AuthGuard]
  }
];
```

---

#### 6. What is a Resolver?

A resolver **pre-loads data** before a route activates. It ensures components receive data immediately when they load, improving user experience by avoiding loading states.

**Benefits:**
- Pre-loaded data
- No loading spinners needed
- Error handling before navigation
- Cleaner component code

```typescript
// DATA SERVICE
@Injectable({
  providedIn: 'root'
})
export class UserService {
  getUser(id: number): Observable<User> {
    return this.http.get<User>(`/api/users/${id}`);
  }
}

// RESOLVER
@Injectable({
  providedIn: 'root'
})
export class UserResolver implements Resolve<User> {
  constructor(private userService: UserService, private router: Router) {}
  
  resolve(route: ActivatedRouteSnapshot): Observable<User> {
    const userId = +route.params['id'];
    
    return this.userService.getUser(userId).pipe(
      catchError(error => {
        console.error('Error loading user:', error);
        this.router.navigate(['/users']);
        return EMPTY;
      })
    );
  }
}

// ROUTE CONFIGURATION
const routes: Routes = [
  {
    path: 'users/:id',
    component: UserDetailComponent,
    resolve: { user: UserResolver }
  }
];

// COMPONENT USING RESOLVED DATA
@Component({
  template: `
    <div *ngIf="user">
      <h2>{{user.name}}</h2>
      <p>{{user.email}}</p>
      <p>{{user.phone}}</p>
    </div>
  `
})
export class UserDetailComponent implements OnInit {
  user: User;
  
  constructor(private route: ActivatedRoute) {}
  
  ngOnInit() {
    // Data is already loaded by resolver
    this.user = this.route.snapshot.data['user'];
    
    // Or subscribe to data changes
    this.route.data.subscribe(data => {
      this.user = data['user'];
    });
  }
}
```

---

#### 7. Difference Between Authentication & Authorization

**Authentication** verifies **who you are** (identity), while **Authorization** determines **what you can do** (permissions). Both are crucial for application security.

| Authentication | Authorization |
|----------------|---------------|
| Who are you? | What can you do? |
| Login process | Permission check |
| Identity verification | Access control |
| Username/password | Roles/permissions |

```typescript
// AUTHENTICATION SERVICE
@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private currentUser: User | null = null;
  
  // AUTHENTICATION - Who are you?
  login(credentials: LoginCredentials): Observable<User> {
    return this.http.post<User>('/api/login', credentials).pipe(
      tap(user => {
        this.currentUser = user;
        localStorage.setItem('token', user.token);
      })
    );
  }
  
  isAuthenticated(): boolean {
    return !!this.currentUser || !!localStorage.getItem('token');
  }
  
  // AUTHORIZATION - What can you do?
  hasRole(role: string): boolean {
    return this.currentUser?.roles.includes(role) || false;
  }
  
  hasPermission(permission: string): boolean {
    return this.currentUser?.permissions.includes(permission) || false;
  }
}

// ROLE-BASED GUARD (Authorization)
@Injectable()
export class RoleGuard implements CanActivate {
  constructor(private authService: AuthService, private router: Router) {}
  
  canActivate(route: ActivatedRouteSnapshot): boolean {
    const requiredRole = route.data['role'];
    
    if (!this.authService.isAuthenticated()) {
      this.router.navigate(['/login']);
      return false;
    }
    
    if (!this.authService.hasRole(requiredRole)) {
      this.router.navigate(['/unauthorized']);
      return false;
    }
    
    return true;
  }
}

// ROUTES WITH ROLE-BASED ACCESS
const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { 
    path: 'dashboard', 
    component: DashboardComponent,
    canActivate: [AuthGuard] // Authentication only
  },
  { 
    path: 'admin', 
    component: AdminComponent,
    canActivate: [AuthGuard, RoleGuard], // Authentication + Authorization
    data: { role: 'admin' }
  },
  { 
    path: 'manager', 
    component: ManagerComponent,
    canActivate: [AuthGuard, RoleGuard],
    data: { role: 'manager' }
  }
];

// COMPONENT WITH CONDITIONAL FEATURES
@Component({
  template: `
    <div>
      <h2>Dashboard</h2>
      <button *ngIf="canEdit" (click)="editData()">Edit</button>
      <button *ngIf="canDelete" (click)="deleteData()">Delete</button>
      <button *ngIf="isAdmin" (click)="adminPanel()">Admin Panel</button>
    </div>
  `
})
export class DashboardComponent {
  canEdit = this.authService.hasPermission('edit');
  canDelete = this.authService.hasPermission('delete');
  isAdmin = this.authService.hasRole('admin');
  
  constructor(private authService: AuthService) {}
}
```

---

## 📝 Summary

**Angular Routing & Navigation** provides comprehensive navigation solutions:

- **Router**: URL-based navigation service for SPAs
- **RouterModule**: Configuration with `forRoot()` and `forChild()`
- **Lazy Loading**: On-demand module loading for better performance
- **Route Guards**: Control navigation with authentication and authorization
- **AuthGuard**: Protect routes from unauthorized access
- **Resolver**: Pre-load data before route activation
- **Authentication vs Authorization**: Identity verification vs permission control

**Key concepts:**
- Use **guards** for security and data validation
- Implement **lazy loading** for better performance
- Use **resolvers** to pre-load critical data
- Separate **authentication** (who) from **authorization** (what)

These features enable building secure, performant single-page applications with sophisticated navigation patterns.


## 🔹 5. HTTP & Backend Integration

#### 1. How Do You Make HTTP Calls?

Angular uses **HttpClient** to make HTTP requests. Import `HttpClientModule` in your module and inject `HttpClient` in services to perform GET, POST, PUT, DELETE operations.

**Key points:**
- Returns Observables
- Automatic JSON parsing
- Type-safe responses
- Built-in error handling

```typescript
// Module setup
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  imports: [HttpClientModule]
})
export class AppModule {}

// Service with HTTP calls
@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private baseUrl = 'https://api.example.com';
  
  constructor(private http: HttpClient) {}
  
  // GET request
  getUsers(): Observable<User[]> {
    return this.http.get<User[]>(`${this.baseUrl}/users`);
  }
  
  // POST request
  createUser(user: User): Observable<User> {
    return this.http.post<User>(`${this.baseUrl}/users`, user);
  }
  
  // PUT request
  updateUser(id: number, user: User): Observable<User> {
    return this.http.put<User>(`${this.baseUrl}/users/${id}`, user);
  }
  
  // DELETE request
  deleteUser(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/users/${id}`);
  }
}

// Component using the service
@Component({
  template: `
    <div *ngFor="let user of users">{{user.name}}</div>
    <button (click)="loadUsers()">Load Users</button>
  `
})
export class UserComponent {
  users: User[] = [];
  
  constructor(private apiService: ApiService) {}
  
  loadUsers() {
    this.apiService.getUsers().subscribe(users => {
      this.users = users;
    });
  }
}
```

---

#### 2. What is HttpClient?

HttpClient is Angular's **HTTP service** that provides a simplified API for making HTTP requests. It's built on top of XMLHttpRequest and returns RxJS Observables.

**Features:**
- Type-safe requests
- Request/response interceptors
- Automatic JSON handling
- Error handling
- Request cancellation

```typescript
// Basic HttpClient usage
@Injectable()
export class DataService {
  constructor(private http: HttpClient) {}
  
  // Simple GET with type safety
  getData(): Observable<ApiResponse> {
    return this.http.get<ApiResponse>('/api/data');
  }
  
  // GET with parameters
  searchUsers(query: string): Observable<User[]> {
    const params = new HttpParams().set('q', query);
    return this.http.get<User[]>('/api/users', { params });
  }
  
  // POST with headers
  saveData(data: any): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer token'
    });
    return this.http.post('/api/data', data, { headers });
  }
  
  // GET with full response
  getFullResponse(): Observable<HttpResponse<any>> {
    return this.http.get('/api/data', { observe: 'response' });
  }
}

// Using in component
@Component({
  template: `<div>{{data | json}}</div>`
})
export class DataComponent implements OnInit {
  data: any;
  
  constructor(private dataService: DataService) {}
  
  ngOnInit() {
    this.dataService.getData().subscribe(
      response => this.data = response,
      error => console.error('Error:', error)
    );
  }
}
```

---

#### 3. How Do You Handle HTTP Errors?

HTTP errors are handled using **RxJS operators** like `catchError`, `retry`, and `finalize`. Angular provides structured error objects with status codes and messages.

**Error handling strategies:**
- Global error interceptor
- Service-level error handling
- Component-level error handling
- User-friendly error messages

```typescript
// Service with error handling
@Injectable()
export class ApiService {
  constructor(private http: HttpClient) {}
  
  getUsers(): Observable<User[]> {
    return this.http.get<User[]>('/api/users').pipe(
      retry(2), // Retry failed requests 2 times
      catchError(this.handleError<User[]>('getUsers', []))
    );
  }
  
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(`${operation} failed:`, error);
      
      // Handle different error types
      if (error.status === 404) {
        console.log('Resource not found');
      } else if (error.status === 500) {
        console.log('Server error');
      }
      
      return of(result as T);
    };
  }
}

// Component with error handling
@Component({
  template: `
    <div *ngIf="loading">Loading...</div>
    <div *ngIf="error" class="error">{{error}}</div>
    <div *ngFor="let user of users">{{user.name}}</div>
  `
})
export class UserListComponent {
  users: User[] = [];
  loading = false;
  error: string | null = null;
  
  constructor(private apiService: ApiService) {}
  
  loadUsers() {
    this.loading = true;
    this.error = null;
    
    this.apiService.getUsers().pipe(
      finalize(() => this.loading = false)
    ).subscribe({
      next: users => this.users = users,
      error: err => {
        this.error = 'Failed to load users. Please try again.';
        console.error('Error loading users:', err);
      }
    });
  }
}

// Global error handler
@Injectable()
export class GlobalErrorHandler implements ErrorHandler {
  handleError(error: any): void {
    if (error instanceof HttpErrorResponse) {
      console.error('HTTP Error:', error.status, error.message);
      // Show user-friendly message
      this.showErrorMessage(error);
    } else {
      console.error('Application Error:', error);
    }
  }
  
  private showErrorMessage(error: HttpErrorResponse) {
    let message = 'An error occurred';
    
    switch (error.status) {
      case 401:
        message = 'Please log in again';
        break;
      case 403:
        message = 'Access denied';
        break;
      case 404:
        message = 'Resource not found';
        break;
      case 500:
        message = 'Server error. Please try again later';
        break;
    }
    
    // Show toast or alert
    alert(message);
  }
}
```

---

#### 4. What are Interceptors?

Interceptors are **middleware** that intercept HTTP requests and responses. They're perfect for adding authentication headers, logging, error handling, and request/response transformation.

**Common uses:**
- Add authentication tokens
- Log requests/responses
- Handle errors globally
- Show loading indicators
- Transform data

```typescript
// Auth interceptor
@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private authService: AuthService) {}
  
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    // Add auth token to requests
    const token = this.authService.getToken();
    
    if (token) {
      const authReq = req.clone({
        headers: req.headers.set('Authorization', `Bearer ${token}`)
      });
      return next.handle(authReq);
    }
    
    return next.handle(req);
  }
}

// Loading interceptor
@Injectable()
export class LoadingInterceptor implements HttpInterceptor {
  constructor(private loadingService: LoadingService) {}
  
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    this.loadingService.show();
    
    return next.handle(req).pipe(
      finalize(() => this.loadingService.hide())
    );
  }
}

// Error interceptor
@Injectable()
export class ErrorInterceptor implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(req).pipe(
      catchError((error: HttpErrorResponse) => {
        if (error.status === 401) {
          // Redirect to login
          window.location.href = '/login';
        }
        return throwError(error);
      })
    );
  }
}

// Register interceptors
@NgModule({
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: LoadingInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true }
  ]
})
export class AppModule {}
```

---

#### 5. How to Add Headers Globally?

Use **HTTP interceptors** to add headers globally to all requests. This is the cleanest way to handle authentication tokens, content types, and other common headers.

**Methods:**
- HTTP Interceptors (recommended)
- HttpClient defaults
- Service-level headers

```typescript
// Global headers interceptor
@Injectable()
export class HeadersInterceptor implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    // Add global headers
    const modifiedReq = req.clone({
      headers: req.headers
        .set('Content-Type', 'application/json')
        .set('Accept', 'application/json')
        .set('X-API-Version', '1.0')
        .set('X-Client', 'Angular-App')
    });
    
    return next.handle(modifiedReq);
  }
}

// Conditional headers interceptor
@Injectable()
export class ConditionalHeadersInterceptor implements HttpInterceptor {
  constructor(private authService: AuthService) {}
  
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let headers = req.headers;
    
    // Add auth token if available
    const token = this.authService.getToken();
    if (token) {
      headers = headers.set('Authorization', `Bearer ${token}`);
    }
    
    // Add CSRF token for state-changing requests
    if (['POST', 'PUT', 'DELETE'].includes(req.method)) {
      const csrfToken = this.getCsrfToken();
      if (csrfToken) {
        headers = headers.set('X-CSRF-Token', csrfToken);
      }
    }
    
    const modifiedReq = req.clone({ headers });
    return next.handle(modifiedReq);
  }
  
  private getCsrfToken(): string | null {
    return document.querySelector('meta[name="csrf-token"]')?.getAttribute('content') || null;
  }
}

// Service with default headers
@Injectable()
export class ApiService {
  private defaultHeaders = new HttpHeaders({
    'Content-Type': 'application/json',
    'Accept': 'application/json'
  });
  
  constructor(private http: HttpClient) {}
  
  getData(): Observable<any> {
    return this.http.get('/api/data', { headers: this.defaultHeaders });
  }
  
  // Override headers for specific requests
  uploadFile(file: File): Observable<any> {
    const headers = new HttpHeaders(); // No Content-Type for file upload
    const formData = new FormData();
    formData.append('file', file);
    
    return this.http.post('/api/upload', formData, { headers });
  }
}
```

---

#### 6. How Do You Handle File Uploads?

File uploads use **FormData** with HttpClient. Remove the Content-Type header to let the browser set the correct boundary for multipart/form-data.

**Key points:**
- Use FormData for files
- Don't set Content-Type header
- Handle upload progress
- Support multiple files

```typescript
// File upload service
@Injectable()
export class FileUploadService {
  constructor(private http: HttpClient) {}
  
  // Single file upload
  uploadFile(file: File): Observable<any> {
    const formData = new FormData();
    formData.append('file', file);
    
    return this.http.post('/api/upload', formData);
  }
  
  // Multiple files upload
  uploadMultipleFiles(files: File[]): Observable<any> {
    const formData = new FormData();
    files.forEach(file => {
      formData.append('files', file);
    });
    
    return this.http.post('/api/upload-multiple', formData);
  }
  
  // Upload with progress tracking
  uploadWithProgress(file: File): Observable<HttpEvent<any>> {
    const formData = new FormData();
    formData.append('file', file);
    
    return this.http.post('/api/upload', formData, {
      reportProgress: true,
      observe: 'events'
    });
  }
  
  // Upload with additional data
  uploadWithData(file: File, metadata: any): Observable<any> {
    const formData = new FormData();
    formData.append('file', file);
    formData.append('metadata', JSON.stringify(metadata));
    
    return this.http.post('/api/upload', formData);
  }
}

// Upload component
@Component({
  template: `
    <input type="file" (change)="onFileSelect($event)" multiple>
    <button (click)="upload()" [disabled]="!selectedFiles.length">Upload</button>
    <div *ngIf="uploadProgress > 0">Progress: {{uploadProgress}}%</div>
  `
})
export class FileUploadComponent {
  selectedFiles: File[] = [];
  uploadProgress = 0;
  
  constructor(private uploadService: FileUploadService) {}
  
  onFileSelect(event: any) {
    this.selectedFiles = Array.from(event.target.files);
  }
  
  upload() {
    if (this.selectedFiles.length === 1) {
      // Single file with progress
      this.uploadService.uploadWithProgress(this.selectedFiles[0]).subscribe(
        event => {
          if (event.type === HttpEventType.UploadProgress) {
            this.uploadProgress = Math.round(100 * event.loaded / event.total!);
          } else if (event.type === HttpEventType.Response) {
            console.log('Upload complete:', event.body);
            this.uploadProgress = 0;
          }
        }
      );
    } else {
      // Multiple files
      this.uploadService.uploadMultipleFiles(this.selectedFiles).subscribe(
        response => console.log('Upload complete:', response)
      );
    }
  }
}
```

---

#### 7. How Do You Make Parallel API Calls?

Use **RxJS operators** like `forkJoin`, `combineLatest`, or `merge` to make parallel API calls. This improves performance by executing requests simultaneously instead of sequentially.

**Operators:**
- `forkJoin` - Wait for all to complete
- `combineLatest` - Emit when any completes
- `merge` - Merge all streams
- `concat` - Sequential execution

```typescript
// Service with parallel calls
@Injectable()
export class DataService {
  constructor(private http: HttpClient) {}
  
  getUser(id: number): Observable<User> {
    return this.http.get<User>(`/api/users/${id}`);
  }
  
  getUserPosts(userId: number): Observable<Post[]> {
    return this.http.get<Post[]>(`/api/users/${userId}/posts`);
  }
  
  getUserComments(userId: number): Observable<Comment[]> {
    return this.http.get<Comment[]>(`/api/users/${userId}/comments`);
  }
  
  // Parallel calls with forkJoin
  getUserProfile(userId: number): Observable<UserProfile> {
    return forkJoin({
      user: this.getUser(userId),
      posts: this.getUserPosts(userId),
      comments: this.getUserComments(userId)
    }).pipe(
      map(({ user, posts, comments }) => ({
        ...user,
        posts,
        comments,
        totalPosts: posts.length,
        totalComments: comments.length
      }))
    );
  }
  
  // Multiple independent calls
  getDashboardData(): Observable<DashboardData> {
    return forkJoin({
      users: this.http.get<User[]>('/api/users'),
      posts: this.http.get<Post[]>('/api/posts'),
      stats: this.http.get<Stats>('/api/stats')
    });
  }
}

// Component using parallel calls
@Component({
  template: `
    <div *ngIf="loading">Loading profile...</div>
    <div *ngIf="profile">
      <h2>{{profile.name}}</h2>
      <p>Posts: {{profile.totalPosts}}</p>
      <p>Comments: {{profile.totalComments}}</p>
    </div>
  `
})
export class UserProfileComponent implements OnInit {
  profile: UserProfile | null = null;
  loading = false;
  
  constructor(
    private dataService: DataService,
    private route: ActivatedRoute
  ) {}
  
  ngOnInit() {
    const userId = +this.route.snapshot.params['id'];
    this.loadUserProfile(userId);
  }
  
  loadUserProfile(userId: number) {
    this.loading = true;
    
    this.dataService.getUserProfile(userId).subscribe({
      next: profile => {
        this.profile = profile;
        this.loading = false;
      },
      error: err => {
        console.error('Error loading profile:', err);
        this.loading = false;
      }
    });
  }
}

// Advanced parallel patterns
@Injectable()
export class AdvancedApiService {
  constructor(private http: HttpClient) {}
  
  // Race condition - first response wins
  getDataFromMultipleSources(): Observable<any> {
    const source1 = this.http.get('/api/data/source1');
    const source2 = this.http.get('/api/data/source2');
    
    return race(source1, source2);
  }
  
  // Sequential with dependency
  createUserWithProfile(userData: any, profileData: any): Observable<any> {
    return this.http.post('/api/users', userData).pipe(
      switchMap(user => 
        this.http.post(`/api/users/${user.id}/profile`, profileData)
      )
    );
  }
  
  // Batch processing
  processMultipleItems(items: any[]): Observable<any[]> {
    const requests = items.map(item => 
      this.http.post('/api/process', item)
    );
    
    return forkJoin(requests);
  }
}
```

---

## 📝 Summary

**Angular HTTP & Backend Integration** provides powerful tools for API communication:

- **HttpClient**: Type-safe HTTP service with Observable-based responses
- **HTTP Methods**: GET, POST, PUT, DELETE with automatic JSON handling
- **Error Handling**: Structured error handling with retry and user-friendly messages
- **Interceptors**: Middleware for authentication, logging, and global configurations
- **Global Headers**: Consistent header management across all requests
- **File Uploads**: FormData-based uploads with progress tracking
- **Parallel Calls**: Efficient API calls using RxJS operators like forkJoin

**Best practices:**
- Use **interceptors** for cross-cutting concerns
- Implement **proper error handling** at multiple levels
- Leverage **RxJS operators** for complex data flows
- Handle **loading states** and **user feedback**
- Use **type safety** with TypeScript interfaces

These patterns enable building robust, performant applications with excellent user experience and maintainable code.


## 🔹 6. RxJS (Very Important)

#### 1. What is RxJS?

RxJS is a **reactive programming library** for JavaScript that uses Observables to handle asynchronous data streams. It's the foundation of Angular's reactive architecture.

**Key features:**
- Asynchronous data handling
- Functional programming approach
- Powerful operators for data transformation
- Built-in error handling
- Memory leak prevention

```typescript
import { Observable, of, from, interval } from 'rxjs';
import { map, filter, take } from 'rxjs/operators';

// Basic RxJS usage
const numbers$ = of(1, 2, 3, 4, 5);
const doubled$ = numbers$.pipe(
  filter(n => n % 2 === 0),
  map(n => n * 2)
);

doubled$.subscribe(value => console.log(value)); // 4, 8

// Real-world example
@Injectable()
export class DataService {
  getData(): Observable<User[]> {
    return this.http.get<User[]>('/api/users').pipe(
      map(users => users.filter(user => user.active)),
      catchError(error => of([]))
    );
  }
}
```

---

#### 2. What is an Observable?

An Observable is a **data stream** that emits values over time. It's lazy (doesn't execute until subscribed) and can emit multiple values, unlike Promises.

**Characteristics:**
- Lazy execution
- Multiple values over time
- Cancellable
- Composable with operators

```typescript
// Creating Observables
const simple$ = new Observable(observer => {
  observer.next('Hello');
  observer.next('World');
  observer.complete();
});

const fromArray$ = from([1, 2, 3]);
const fromPromise$ = from(fetch('/api/data'));
const timer$ = interval(1000);

// Using Observable
simple$.subscribe({
  next: value => console.log(value),
  error: err => console.error(err),
  complete: () => console.log('Done')
});

// Component example
@Component({
  template: `
    <div *ngFor="let item of items$ | async">{{item.name}}</div>
  `
})
export class ItemsComponent {
  items$ = this.apiService.getItems();
  
  constructor(private apiService: ApiService) {}
}
```

---

#### 3. What is an Observer?

An Observer is an **object** that defines how to handle values emitted by an Observable. It has three methods: `next`, `error`, and `complete`.

**Observer methods:**
- `next()` - Handle emitted values
- `error()` - Handle errors
- `complete()` - Handle completion

```typescript
// Observer object
const observer = {
  next: (value: any) => console.log('Received:', value),
  error: (error: any) => console.error('Error:', error),
  complete: () => console.log('Stream completed')
};

// Subscribe with observer
observable$.subscribe(observer);

// Subscribe with functions
observable$.subscribe(
  value => console.log(value),        // next
  error => console.error(error),     // error
  () => console.log('Complete')      // complete
);

// Partial observer
observable$.subscribe({
  next: value => console.log(value)
  // error and complete are optional
});

// Component observer pattern
@Component({
  template: `
    <div *ngIf="loading">Loading...</div>
    <div *ngIf="error">Error: {{error}}</div>
    <div *ngFor="let user of users">{{user.name}}</div>
  `
})
export class UsersComponent implements OnInit {
  users: User[] = [];
  loading = false;
  error: string | null = null;
  
  ngOnInit() {
    this.loading = true;
    
    this.userService.getUsers().subscribe({
      next: users => {
        this.users = users;
        this.loading = false;
      },
      error: err => {
        this.error = 'Failed to load users';
        this.loading = false;
      }
    });
  }
}
```

---

#### 4. What is Multicasting?

Multicasting allows **multiple subscribers** to share the same Observable execution. By default, Observables are unicast (each subscription creates a new execution).

**Multicasting operators:**
- `share()` - Share execution among subscribers
- `shareReplay()` - Share and replay values
- `multicast()` - Custom multicasting

```typescript
// Unicast (default) - each subscription creates new execution
const unicast$ = new Observable(observer => {
  console.log('Observable executed'); // Logs for each subscription
  observer.next(Math.random());
});

unicast$.subscribe(val => console.log('Sub 1:', val));
unicast$.subscribe(val => console.log('Sub 2:', val)); // Different values

// Multicast with share()
const multicast$ = new Observable(observer => {
  console.log('Observable executed'); // Logs only once
  observer.next(Math.random());
}).pipe(share());

multicast$.subscribe(val => console.log('Sub 1:', val));
multicast$.subscribe(val => console.log('Sub 2:', val)); // Same value

// Real-world example - API call sharing
@Injectable()
export class UserService {
  private users$ = this.http.get<User[]>('/api/users').pipe(
    shareReplay(1) // Cache and share the result
  );
  
  getUsers(): Observable<User[]> {
    return this.users$; // Multiple components can subscribe without multiple API calls
  }
}
```

---

#### 5. Subject vs BehaviorSubject vs ReplaySubject

**Subjects** are special Observables that can multicast and act as both Observable and Observer. Each type has different behavior for new subscribers.

| Subject | BehaviorSubject | ReplaySubject |
|---------|----------------|---------------|
| No initial value | Has initial value | Replays N values |
| New subscribers get future values | New subscribers get current value | New subscribers get last N values |
| Hot Observable | Hot Observable | Hot Observable |

```typescript
// Subject - no initial value
const subject = new Subject<string>();
subject.subscribe(val => console.log('Sub 1:', val));
subject.next('Hello'); // Sub 1: Hello
subject.subscribe(val => console.log('Sub 2:', val)); // Gets nothing from past
subject.next('World'); // Sub 1: World, Sub 2: World

// BehaviorSubject - has initial value
const behaviorSubject = new BehaviorSubject<string>('Initial');
behaviorSubject.subscribe(val => console.log('Sub 1:', val)); // Sub 1: Initial
behaviorSubject.next('Hello'); // Sub 1: Hello
behaviorSubject.subscribe(val => console.log('Sub 2:', val)); // Sub 2: Hello (current value)

// ReplaySubject - replays last N values
const replaySubject = new ReplaySubject<string>(2); // Buffer size 2
replaySubject.next('First');
replaySubject.next('Second');
replaySubject.next('Third');
replaySubject.subscribe(val => console.log('Sub 1:', val)); // Sub 1: Second, Third

// Real-world usage
@Injectable()
export class StateService {
  private userSubject = new BehaviorSubject<User | null>(null);
  user$ = this.userSubject.asObservable();
  
  setUser(user: User) {
    this.userSubject.next(user);
  }
  
  getCurrentUser(): User | null {
    return this.userSubject.value;
  }
}
```

---

#### 6. Promise vs Observable

**Promises** handle single async values, while **Observables** handle multiple values over time. Observables are lazy, cancellable, and composable.

| Promise | Observable |
|---------|------------|
| Single value | Multiple values |
| Eager execution | Lazy execution |
| Not cancellable | Cancellable |
| `.then()` | `.pipe()` operators |

```typescript
// Promise - single value, eager
const promise = fetch('/api/data')
  .then(response => response.json())
  .then(data => console.log(data));

// Observable - multiple values, lazy
const observable$ = this.http.get('/api/data').pipe(
  map(data => data.items),
  filter(items => items.length > 0)
);

// Promise to Observable
const fromPromise$ = from(fetch('/api/data'));

// Observable to Promise
const toPromise = observable$.pipe(take(1)).toPromise();

// Real-world comparison
@Component({
  template: `
    <button (click)="loadWithPromise()">Load with Promise</button>
    <button (click)="loadWithObservable()">Load with Observable</button>
    <button (click)="cancelObservable()">Cancel Observable</button>
  `
})
export class ComparisonComponent {
  private subscription?: Subscription;
  
  // Promise approach
  async loadWithPromise() {
    try {
      const data = await this.apiService.getData().toPromise();
      console.log('Promise data:', data);
    } catch (error) {
      console.error('Promise error:', error);
    }
  }
  
  // Observable approach
  loadWithObservable() {
    this.subscription = this.apiService.getData().subscribe({
      next: data => console.log('Observable data:', data),
      error: error => console.error('Observable error:', error)
    });
  }
  
  cancelObservable() {
    this.subscription?.unsubscribe(); // Can cancel Observable
  }
}
```

---

#### 7. `map`, `filter`, `switchMap`, `mergeMap`, `concatMap`

These are **transformation operators** that modify Observable streams. Each serves different purposes for data transformation and flattening.

**Operators:**
- `map` - Transform values
- `filter` - Filter values
- `switchMap` - Switch to new Observable (cancel previous)
- `mergeMap` - Merge multiple Observables
- `concatMap` - Concatenate Observables sequentially

```typescript
// map - transform each value
const numbers$ = of(1, 2, 3, 4);
const doubled$ = numbers$.pipe(
  map(n => n * 2)
); // 2, 4, 6, 8

// filter - filter values
const evens$ = numbers$.pipe(
  filter(n => n % 2 === 0)
); // 2, 4

// switchMap - switch to new Observable (cancels previous)
const searchTerm$ = new Subject<string>();
const searchResults$ = searchTerm$.pipe(
  switchMap(term => this.apiService.search(term))
); // Cancels previous search when new term arrives

// mergeMap - merge all inner Observables
const userIds$ = of(1, 2, 3);
const users$ = userIds$.pipe(
  mergeMap(id => this.apiService.getUser(id))
); // All requests run in parallel

// concatMap - concatenate inner Observables sequentially
const sequential$ = userIds$.pipe(
  concatMap(id => this.apiService.getUser(id))
); // Requests run one after another

// Real-world example
@Component({
  template: `
    <input (keyup)="search($event)" placeholder="Search users">
    <div *ngFor="let user of searchResults$ | async">{{user.name}}</div>
  `
})
export class SearchComponent {
  private searchSubject = new Subject<string>();
  
  searchResults$ = this.searchSubject.pipe(
    filter(term => term.length > 2),
    debounceTime(300),
    distinctUntilChanged(),
    switchMap(term => this.userService.searchUsers(term))
  );
  
  search(event: any) {
    this.searchSubject.next(event.target.value);
  }
}
```

---

#### 8. Difference Between `switchMap`, `mergeMap`, `concatMap`

These **flattening operators** handle inner Observables differently: **switchMap** cancels previous, **mergeMap** runs parallel, **concatMap** runs sequential.

| switchMap | mergeMap | concatMap |
|-----------|----------|-----------|
| Cancels previous | Runs parallel | Runs sequential |
| Latest wins | All complete | Maintains order |
| Search, navigation | Independent requests | Ordered operations |

```typescript
// switchMap - cancels previous inner Observable
const switchExample$ = of(1, 2, 3).pipe(
  switchMap(n => interval(1000).pipe(
    map(i => `${n}-${i}`),
    take(3)
  ))
); // Only emits from the last (3): 3-0, 3-1, 3-2

// mergeMap - all inner Observables run in parallel
const mergeExample$ = of(1, 2, 3).pipe(
  mergeMap(n => interval(1000).pipe(
    map(i => `${n}-${i}`),
    take(3)
  ))
); // Emits from all: 1-0, 2-0, 3-0, 1-1, 2-1, 3-1, etc.

// concatMap - inner Observables run sequentially
const concatExample$ = of(1, 2, 3).pipe(
  concatMap(n => interval(1000).pipe(
    map(i => `${n}-${i}`),
    take(3)
  ))
); // Emits in order: 1-0, 1-1, 1-2, then 2-0, 2-1, 2-2, then 3-0, 3-1, 3-2

// Real-world use cases
@Injectable()
export class ApiService {
  // switchMap - for search (cancel previous searches)
  searchUsers(term: string): Observable<User[]> {
    return this.searchSubject.pipe(
      switchMap(searchTerm => 
        this.http.get<User[]>(`/api/users?search=${searchTerm}`)
      )
    );
  }
  
  // mergeMap - for independent parallel requests
  getUsersWithDetails(userIds: number[]): Observable<User[]> {
    return from(userIds).pipe(
      mergeMap(id => this.http.get<User>(`/api/users/${id}`)),
      toArray()
    );
  }
  
  // concatMap - for sequential operations (order matters)
  processUsersSequentially(users: User[]): Observable<User[]> {
    return from(users).pipe(
      concatMap(user => this.http.post<User>('/api/process', user)),
      toArray()
    );
  }
}
```

---

#### 9. How Do You Handle Errors in RxJS?

RxJS provides **error handling operators** like `catchError`, `retry`, and `retryWhen`. Errors terminate the stream unless handled properly.

**Error handling strategies:**
- `catchError` - Handle and recover from errors
- `retry` - Retry failed operations
- `retryWhen` - Conditional retry logic
- `throwError` - Re-throw errors

```typescript
// Basic error handling with catchError
const apiCall$ = this.http.get('/api/data').pipe(
  catchError(error => {
    console.error('API Error:', error);
    return of([]); // Return fallback value
  })
);

// Retry with exponential backoff
const retryCall$ = this.http.get('/api/data').pipe(
  retry(3), // Retry 3 times
  catchError(error => {
    console.error('Failed after retries:', error);
    return throwError(error);
  })
);

// Advanced retry with retryWhen
const advancedRetry$ = this.http.get('/api/data').pipe(
  retryWhen(errors => 
    errors.pipe(
      delay(1000), // Wait 1 second between retries
      take(3), // Max 3 retries
      concat(throwError('Max retries exceeded'))
    )
  )
);

// Service with comprehensive error handling
@Injectable()
export class DataService {
  getData(): Observable<any[]> {
    return this.http.get<any[]>('/api/data').pipe(
      retry(2),
      catchError(this.handleError('getData', []))
    );
  }
  
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(`${operation} failed:`, error);
      
      // Log to external service
      this.logError(error);
      
      // Return safe fallback
      return of(result as T);
    };
  }
  
  private logError(error: any) {
    // Send to logging service
    console.error('Logging error:', error);
  }
}

// Component error handling
@Component({
  template: `
    <div *ngIf="error" class="error">{{error}}</div>
    <div *ngFor="let item of data">{{item.name}}</div>
  `
})
export class DataComponent implements OnInit {
  data: any[] = [];
  error: string | null = null;
  
  ngOnInit() {
    this.dataService.getData().pipe(
      catchError(error => {
        this.error = 'Failed to load data';
        return of([]);
      })
    ).subscribe(data => {
      this.data = data;
    });
  }
}
```

---

#### 10. How to Unsubscribe Properly?

Proper unsubscription **prevents memory leaks**. Use `takeUntil`, manual unsubscription, or async pipe for automatic cleanup.

**Unsubscription methods:**
- `takeUntil` with destroy subject
- Manual `unsubscribe()`
- `async` pipe (automatic)
- `takeWhile` with conditions

```typescript
// Method 1: takeUntil with destroy subject (Recommended)
@Component({
  template: `<div *ngFor="let item of items">{{item.name}}</div>`
})
export class ComponentWithTakeUntil implements OnInit, OnDestroy {
  private destroy$ = new Subject<void>();
  items: any[] = [];
  
  ngOnInit() {
    this.dataService.getData().pipe(
      takeUntil(this.destroy$)
    ).subscribe(data => {
      this.items = data;
    });
    
    // Multiple subscriptions
    this.userService.getUsers().pipe(
      takeUntil(this.destroy$)
    ).subscribe(users => console.log(users));
  }
  
  ngOnDestroy() {
    this.destroy$.next();
    this.destroy$.complete();
  }
}

// Method 2: Manual unsubscription
@Component({
  template: `<div>Manual unsubscription</div>`
})
export class ManualUnsubscribeComponent implements OnInit, OnDestroy {
  private subscriptions = new Subscription();
  
  ngOnInit() {
    const sub1 = this.dataService.getData().subscribe(data => {
      console.log(data);
    });
    
    const sub2 = this.userService.getUsers().subscribe(users => {
      console.log(users);
    });
    
    this.subscriptions.add(sub1);
    this.subscriptions.add(sub2);
  }
  
  ngOnDestroy() {
    this.subscriptions.unsubscribe();
  }
}

// Method 3: Async pipe (Automatic - Recommended for templates)
@Component({
  template: `
    <div *ngFor="let item of items$ | async">{{item.name}}</div>
    <div *ngFor="let user of users$ | async">{{user.name}}</div>
  `
})
export class AsyncPipeComponent {
  items$ = this.dataService.getData();
  users$ = this.userService.getUsers();
  
  // No ngOnDestroy needed - async pipe handles unsubscription
}

// Base class for takeUntil pattern
export abstract class BaseComponent implements OnDestroy {
  protected destroy$ = new Subject<void>();
  
  ngOnDestroy() {
    this.destroy$.next();
    this.destroy$.complete();
  }
}

@Component({
  template: `<div>Extended component</div>`
})
export class ExtendedComponent extends BaseComponent implements OnInit {
  ngOnInit() {
    this.dataService.getData().pipe(
      takeUntil(this.destroy$)
    ).subscribe(data => console.log(data));
  }
}
```

---

#### 11. What is `takeUntil`?

`takeUntil` is an operator that **completes the source Observable** when another Observable emits. It's the preferred method for unsubscription in Angular components.

**Benefits:**
- Automatic unsubscription
- Clean code
- Prevents memory leaks
- Works with multiple subscriptions

```typescript
// Basic takeUntil usage
const source$ = interval(1000);
const stop$ = new Subject();

source$.pipe(
  takeUntil(stop$)
).subscribe(val => console.log(val));

// Stop after 5 seconds
setTimeout(() => stop$.next(), 5000);

// Component pattern with takeUntil
@Component({
  template: `
    <div>{{currentTime}}</div>
    <div *ngFor="let notification of notifications">{{notification.message}}</div>
  `
})
export class NotificationComponent implements OnInit, OnDestroy {
  private destroy$ = new Subject<void>();
  currentTime = '';
  notifications: Notification[] = [];
  
  ngOnInit() {
    // Timer subscription
    interval(1000).pipe(
      map(() => new Date().toLocaleTimeString()),
      takeUntil(this.destroy$)
    ).subscribe(time => {
      this.currentTime = time;
    });
    
    // Notification subscription
    this.notificationService.getNotifications().pipe(
      takeUntil(this.destroy$)
    ).subscribe(notifications => {
      this.notifications = notifications;
    });
    
    // HTTP request subscription
    this.dataService.getData().pipe(
      takeUntil(this.destroy$)
    ).subscribe(data => {
      console.log('Data received:', data);
    });
  }
  
  ngOnDestroy() {
    this.destroy$.next();
    this.destroy$.complete();
  }
}

// Advanced takeUntil patterns
@Injectable()
export class AdvancedService {
  private destroy$ = new Subject<void>();
  
  // Service-level cleanup
  startPolling(): Observable<any> {
    return interval(5000).pipe(
      switchMap(() => this.http.get('/api/status')),
      takeUntil(this.destroy$)
    );
  }
  
  stopPolling() {
    this.destroy$.next();
  }
}

// Conditional takeUntil
@Component({
  template: `<div>Conditional subscription</div>`
})
export class ConditionalComponent implements OnInit, OnDestroy {
  private destroy$ = new Subject<void>();
  private stopCondition$ = new BehaviorSubject<boolean>(false);
  
  ngOnInit() {
    this.dataService.getData().pipe(
      takeUntil(
        merge(
          this.destroy$,
          this.stopCondition$.pipe(filter(stop => stop))
        )
      )
    ).subscribe(data => console.log(data));
  }
  
  stopSubscription() {
    this.stopCondition$.next(true);
  }
  
  ngOnDestroy() {
    this.destroy$.next();
    this.destroy$.complete();
  }
}
```

---

## 📝 Summary

**RxJS** is fundamental to Angular's reactive architecture:

- **Observable**: Lazy data streams that emit multiple values over time
- **Observer**: Objects that handle emitted values with next, error, complete methods
- **Subjects**: Special Observables for multicasting (Subject, BehaviorSubject, ReplaySubject)
- **Operators**: Transform data streams (map, filter, switchMap, mergeMap, concatMap)
- **Error Handling**: Use catchError, retry, and retryWhen for robust applications
- **Unsubscription**: Prevent memory leaks with takeUntil, manual unsubscribe, or async pipe

**Best practices:**
- Use **takeUntil** for component unsubscription
- Choose the right **flattening operator** (switchMap for latest, mergeMap for parallel, concatMap for sequential)
- Handle **errors gracefully** with appropriate fallbacks
- Leverage **async pipe** for automatic subscription management
- Use **BehaviorSubject** for state management

RxJS enables building reactive, performant Angular applications with clean, maintainable asynchronous code.

## 🔹 7. Change Detection & Performance

#### 1. What is Change Detection?

Change detection is Angular's **mechanism** to check if data has changed and update the DOM accordingly. It runs automatically after certain events to keep the view synchronized with the model.

**Key points:**
- Synchronizes model and view
- Runs after DOM events, HTTP requests, timers
- Checks all components in the tree
- Updates only changed DOM elements

```typescript
// Basic change detection example
@Component({
  template: `
    <p>Counter: {{counter}}</p>
    <button (click)="increment()">Increment</button>
  `
})
export class CounterComponent {
  counter = 0;
  
  increment() {
    this.counter++; // Change detection runs after click event
  }
}

// Manual change detection
@Component({
  template: `
    <p>Time: {{currentTime}}</p>
    <button (click)="updateTime()">Update Time</button>
  `
})
export class TimeComponent {
  currentTime = new Date();
  
  constructor(private cdr: ChangeDetectorRef) {}
  
  updateTime() {
    this.currentTime = new Date();
    this.cdr.detectChanges(); // Manual trigger
  }
}
```

---

#### 2. How Does Angular Change Detection Work?

Angular uses a **change detection tree** that mirrors the component tree. It checks each component from root to leaves, comparing current values with previous values using dirty checking.

**Process:**
1. Event triggers change detection
2. Angular checks all components top-down
3. Compares current vs previous values
4. Updates DOM for changed values
5. Runs lifecycle hooks

```typescript
// Change detection cycle
@Component({
  template: `
    <div>{{name}}</div>
    <div>{{getRandomNumber()}}</div> <!-- Called every cycle -->
    <child-component [data]="userData"></child-component>
  `
})
export class ParentComponent {
  name = 'Angular';
  userData = { id: 1, name: 'John' };
  
  getRandomNumber() {
    console.log('Function called during change detection');
    return Math.random(); // Avoid expensive operations here
  }
}

// Child component receives input changes
@Component({
  selector: 'child-component',
  template: `<div>Child: {{data.name}}</div>`
})
export class ChildComponent implements OnChanges {
  @Input() data: any;
  
  ngOnChanges(changes: SimpleChanges) {
    console.log('Input changed:', changes);
  }
}

// Triggering change detection
@Component({
  template: `
    <button (click)="onClick()">Click</button> <!-- DOM event -->
    <div>{{asyncData | async}}</div> <!-- HTTP request -->
  `
})
export class TriggerComponent {
  asyncData = this.http.get('/api/data');
  
  constructor(private http: HttpClient) {
    // Timer also triggers change detection
    setInterval(() => {
      console.log('Timer tick');
    }, 1000);
  }
  
  onClick() {
    console.log('Click event triggers change detection');
  }
}
```

---

#### 3. What is Zone.js?

Zone.js is a **library** that patches asynchronous operations to automatically trigger Angular's change detection. It creates execution contexts that track async operations.

**What Zone.js patches:**
- DOM events
- Promises
- Timers (setTimeout, setInterval)
- HTTP requests
- Microtasks

```typescript
// Zone.js automatically triggers change detection
@Component({
  template: `
    <div>{{message}}</div>
    <button (click)="asyncOperation()">Start Async</button>
  `
})
export class ZoneComponent {
  message = 'Initial';
  
  asyncOperation() {
    // Zone.js patches setTimeout - change detection runs automatically
    setTimeout(() => {
      this.message = 'Updated after timeout';
    }, 1000);
    
    // Zone.js patches Promise - change detection runs automatically
    Promise.resolve().then(() => {
      this.message = 'Updated from Promise';
    });
    
    // HTTP requests are also patched
    this.http.get('/api/data').subscribe(data => {
      this.message = 'Updated from HTTP';
    });
  }
}

// Running outside Zone.js
@Component({
  template: `<div>{{counter}}</div>`
})
export class OutsideZoneComponent {
  counter = 0;
  
  constructor(private ngZone: NgZone) {}
  
  startCounterOutsideZone() {
    // Runs outside Zone.js - no automatic change detection
    this.ngZone.runOutsideAngular(() => {
      setInterval(() => {
        this.counter++; // Won't update UI automatically
      }, 1000);
    });
  }
  
  startCounterInsideZone() {
    // Runs inside Zone.js - automatic change detection
    this.ngZone.run(() => {
      setInterval(() => {
        this.counter++; // Will update UI automatically
      }, 1000);
    });
  }
}
```

---

#### 4. What is `ngZone`?

`ngZone` is Angular's **wrapper around Zone.js** that provides methods to run code inside or outside the Angular zone, controlling when change detection runs.

**Key methods:**
- `run()` - Run inside Angular zone
- `runOutsideAngular()` - Run outside Angular zone
- `onStable` - Emits when zone becomes stable

```typescript
@Component({
  template: `
    <div>{{progress}}%</div>
    <button (click)="startProgress()">Start Progress</button>
  `
})
export class NgZoneComponent {
  progress = 0;
  
  constructor(private ngZone: NgZone) {}
  
  startProgress() {
    this.progress = 0;
    
    // Run outside Angular zone for performance
    this.ngZone.runOutsideAngular(() => {
      const interval = setInterval(() => {
        this.progress += 10;
        
        if (this.progress >= 100) {
          clearInterval(interval);
          
          // Run inside zone to update UI
          this.ngZone.run(() => {
            console.log('Progress complete!');
          });
        }
      }, 100);
    });
  }
  
  // Listen for zone stability
  ngOnInit() {
    this.ngZone.onStable.subscribe(() => {
      console.log('Zone is stable - all async operations complete');
    });
  }
}

// Performance optimization with ngZone
@Component({
  template: `
    <canvas #canvas width="400" height="400"></canvas>
    <button (click)="startAnimation()">Animate</button>
  `
})
export class AnimationComponent {
  @ViewChild('canvas') canvas!: ElementRef<HTMLCanvasElement>;
  
  constructor(private ngZone: NgZone) {}
  
  startAnimation() {
    const ctx = this.canvas.nativeElement.getContext('2d')!;
    let x = 0;
    
    // Animation runs outside Angular zone for better performance
    this.ngZone.runOutsideAngular(() => {
      const animate = () => {
        ctx.clearRect(0, 0, 400, 400);
        ctx.fillRect(x, 100, 50, 50);
        x = (x + 2) % 400;
        
        requestAnimationFrame(animate);
      };
      animate();
    });
  }
}
```

---

#### 5. ChangeDetectionStrategy.Default vs OnPush

**Default strategy** checks all components on every change detection cycle. **OnPush strategy** only checks when inputs change or events occur, improving performance.

| Default | OnPush |
|---------|--------|
| Checks every cycle | Checks only when needed |
| Higher CPU usage | Better performance |
| Simpler to use | Requires careful input management |
| Good for small apps | Essential for large apps |

```typescript
// Default strategy (default behavior)
@Component({
  selector: 'default-component',
  template: `
    <div>{{data.name}} - {{getCurrentTime()}}</div>
  `,
  changeDetection: ChangeDetectionStrategy.Default
})
export class DefaultComponent {
  @Input() data: any;
  
  getCurrentTime() {
    console.log('Default: getCurrentTime called');
    return new Date().toLocaleTimeString();
  }
}

// OnPush strategy (optimized)
@Component({
  selector: 'onpush-component',
  template: `
    <div>{{data.name}} - {{currentTime}}</div>
    <button (click)="updateTime()">Update Time</button>
  `,
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class OnPushComponent {
  @Input() data: any;
  currentTime = new Date().toLocaleTimeString();
  
  constructor(private cdr: ChangeDetectorRef) {}
  
  updateTime() {
    this.currentTime = new Date().toLocaleTimeString();
    // Manual change detection needed for OnPush
    this.cdr.markForCheck();
  }
}

// OnPush with immutable data
@Component({
  selector: 'parent-component',
  template: `
    <onpush-component [data]="userData"></onpush-component>
    <button (click)="updateUser()">Update User</button>
    <button (click)="updateUserWrong()">Update User (Wrong)</button>
  `
})
export class ParentComponent {
  userData = { id: 1, name: 'John', age: 30 };
  
  updateUser() {
    // Correct: Create new object (OnPush will detect change)
    this.userData = { ...this.userData, age: this.userData.age + 1 };
  }
  
  updateUserWrong() {
    // Wrong: Mutate existing object (OnPush won't detect change)
    this.userData.age++;
  }
}

// OnPush with Observables
@Component({
  selector: 'observable-component',
  template: `
    <div *ngFor="let item of items$ | async">{{item.name}}</div>
  `,
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class ObservableComponent {
  items$ = this.dataService.getItems(); // async pipe triggers change detection
  
  constructor(private dataService: DataService) {}
}
```

---

#### 6. How to Improve Angular Performance?

Angular performance can be improved through **OnPush strategy**, **lazy loading**, **trackBy functions**, **pure pipes**, and **code splitting**.

**Key optimizations:**
- Use OnPush change detection
- Implement trackBy for ngFor
- Lazy load modules
- Use pure pipes
- Optimize bundle size

```typescript
// 1. OnPush strategy
@Component({
  changeDetection: ChangeDetectionStrategy.OnPush,
  template: `<div>Optimized component</div>`
})
export class OptimizedComponent {}

// 2. TrackBy function for ngFor
@Component({
  template: `
    <div *ngFor="let item of items; trackBy: trackByFn">
      {{item.name}}
    </div>
  `
})
export class TrackByComponent {
  items = [{ id: 1, name: 'Item 1' }];
  
  trackByFn(index: number, item: any): any {
    return item.id; // Track by unique identifier
  }
}

// 3. Pure pipes for expensive operations
@Pipe({
  name: 'expensiveFilter',
  pure: true // Default, but explicit for clarity
})
export class ExpensiveFilterPipe implements PipeTransform {
  transform(items: any[], filter: string): any[] {
    console.log('Pipe executed'); // Only when inputs change
    return items.filter(item => item.name.includes(filter));
  }
}

// 4. Lazy loading routes
const routes: Routes = [
  {
    path: 'feature',
    loadChildren: () => import('./feature/feature.module').then(m => m.FeatureModule)
  }
];

// 5. Virtual scrolling for large lists
@Component({
  template: `
    <cdk-virtual-scroll-viewport itemSize="50" class="viewport">
      <div *cdkVirtualFor="let item of items">{{item.name}}</div>
    </cdk-virtual-scroll-viewport>
  `,
  styles: ['.viewport { height: 400px; }']
})
export class VirtualScrollComponent {
  items = Array.from({length: 10000}, (_, i) => ({id: i, name: `Item ${i}`}));
}

// 6. Preloading strategies
@NgModule({
  imports: [RouterModule.forRoot(routes, {
    preloadingStrategy: PreloadAllModules // Preload all lazy modules
  })]
})
export class AppRoutingModule {}

// 7. Service worker for caching
@NgModule({
  imports: [
    ServiceWorkerModule.register('ngsw-worker.js', {
      enabled: environment.production
    })
  ]
})
export class AppModule {}
```

---

#### 7. How to Troubleshoot Performance Issues?

Use **Angular DevTools**, **Chrome DevTools**, **performance profiling**, and **bundle analysis** to identify and fix performance bottlenecks.

**Debugging tools:**
- Angular DevTools
- Chrome Performance tab
- Webpack Bundle Analyzer
- Angular CLI build analyzer

```typescript
// 1. Performance monitoring component
@Component({
  template: `
    <div>Performance Monitor</div>
    <button (click)="measurePerformance()">Measure</button>
  `
})
export class PerformanceComponent {
  measurePerformance() {
    const start = performance.now();
    
    // Expensive operation
    this.expensiveOperation();
    
    const end = performance.now();
    console.log(`Operation took ${end - start} milliseconds`);
  }
  
  expensiveOperation() {
    // Simulate expensive work
    for (let i = 0; i < 1000000; i++) {
      Math.random();
    }
  }
}

// 2. Change detection profiling
@Component({
  template: `<div>{{getValue()}}</div>`
})
export class ProfilingComponent {
  private callCount = 0;
  
  getValue() {
    this.callCount++;
    console.log(`getValue called ${this.callCount} times`);
    return 'value';
  }
}

// 3. Memory leak detection
@Component({
  template: `<div>Memory Leak Test</div>`
})
export class MemoryLeakComponent implements OnInit, OnDestroy {
  private subscription?: Subscription;
  
  ngOnInit() {
    // Potential memory leak - not unsubscribed
    this.subscription = interval(1000).subscribe(val => {
      console.log('Interval:', val);
    });
  }
  
  ngOnDestroy() {
    // Fix: Unsubscribe to prevent memory leak
    this.subscription?.unsubscribe();
  }
}

// 4. Bundle analysis commands
// ng build --stats-json
// npx webpack-bundle-analyzer dist/stats.json

// 5. Performance budget in angular.json
{
  "budgets": [
    {
      "type": "initial",
      "maximumWarning": "2mb",
      "maximumError": "5mb"
    }
  ]
}

// 6. Custom performance interceptor
@Injectable()
export class PerformanceInterceptor implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const start = performance.now();
    
    return next.handle(req).pipe(
      finalize(() => {
        const end = performance.now();
        console.log(`HTTP ${req.method} ${req.url} took ${end - start}ms`);
      })
    );
  }
}

// 7. Component performance decorator
function MeasurePerformance(target: any, propertyName: string, descriptor: PropertyDescriptor) {
  const method = descriptor.value;
  
  descriptor.value = function (...args: any[]) {
    const start = performance.now();
    const result = method.apply(this, args);
    const end = performance.now();
    
    console.log(`${propertyName} took ${end - start}ms`);
    return result;
  };
}

@Component({
  template: `<button (click)="slowMethod()">Slow Method</button>`
})
export class DecoratedComponent {
  @MeasurePerformance
  slowMethod() {
    // Expensive operation
    for (let i = 0; i < 1000000; i++) {
      Math.random();
    }
  }
}
```

---

## 📝 Summary

**Angular Change Detection & Performance** optimization is crucial for scalable applications:

- **Change Detection**: Automatic mechanism to sync model and view after events
- **Zone.js**: Patches async operations to trigger change detection automatically
- **NgZone**: Controls when change detection runs with run() and runOutsideAngular()
- **OnPush Strategy**: Optimizes performance by checking components only when needed
- **Performance Optimization**: Use trackBy, lazy loading, pure pipes, and virtual scrolling
- **Troubleshooting**: Leverage Angular DevTools, profiling, and bundle analysis

**Best practices:**
- Use **OnPush** for better performance in large applications
- Implement **trackBy** functions for ngFor loops
- Run **expensive operations** outside Angular zone
- Use **immutable data** patterns with OnPush
- **Profile regularly** and monitor bundle sizes
- **Lazy load** feature modules to reduce initial bundle size

Understanding change detection is essential for building performant Angular applications that scale well with complexity and user load.

## 🔹 8. Modern Angular (Must-Know)

#### 1. What are Standalone Components?

Standalone components are **self-contained components** that don't need to be declared in NgModules. They import their own dependencies directly, simplifying the architecture and reducing boilerplate.

**Benefits:**
- No NgModule required
- Direct dependency imports
- Simpler architecture
- Better tree-shaking
- Easier testing

```typescript
// Traditional component (with NgModule)
@Component({
  selector: 'app-user',
  template: `<div>{{user.name}}</div>`
})
export class UserComponent {
  user = { name: 'John' };
}

@NgModule({
  declarations: [UserComponent],
  imports: [CommonModule],
  exports: [UserComponent]
})
export class UserModule {}

// Standalone component (Angular 14+)
@Component({
  selector: 'app-user-standalone',
  standalone: true,
  imports: [CommonModule, FormsModule], // Direct imports
  template: `
    <div>{{user.name}}</div>
    <input [(ngModel)]="user.name">
  `
})
export class UserStandaloneComponent {
  user = { name: 'John' };
}

// Using standalone component
@Component({
  selector: 'app-root',
  standalone: true,
  imports: [UserStandaloneComponent], // Import component directly
  template: `<app-user-standalone></app-user-standalone>`
})
export class AppComponent {}

// Bootstrap standalone app
bootstrapApplication(AppComponent, {
  providers: [
    provideRouter(routes),
    provideHttpClient()
  ]
});
```

---

#### 2. What are Signals?

Signals are **reactive primitives** that hold values and notify consumers when values change. They provide fine-grained reactivity and better performance than traditional change detection.

**Key features:**
- Reactive values
- Automatic dependency tracking
- Fine-grained updates
- Better performance
- Computed values

```typescript
// Basic signal usage
@Component({
  selector: 'app-counter',
  standalone: true,
  template: `
    <p>Count: {{count()}}</p>
    <p>Double: {{doubleCount()}}</p>
    <button (click)="increment()">+</button>
    <button (click)="decrement()">-</button>
  `
})
export class CounterComponent {
  // Writable signal
  count = signal(0);
  
  // Computed signal (derived value)
  doubleCount = computed(() => this.count() * 2);
  
  increment() {
    this.count.update(value => value + 1);
  }
  
  decrement() {
    this.count.set(this.count() - 1);
  }
}

// Signal with objects
@Component({
  selector: 'app-user-profile',
  standalone: true,
  imports: [JsonPipe],
  template: `
    <div>{{user() | json}}</div>
    <button (click)="updateName()">Update Name</button>
  `
})
export class UserProfileComponent {
  user = signal({ id: 1, name: 'John', age: 30 });
  
  updateName() {
    this.user.update(user => ({
      ...user,
      name: 'Jane'
    }));
  }
}

// Effect for side effects
@Component({
  selector: 'app-logger',
  standalone: true,
  template: `<p>Check console for logs</p>`
})
export class LoggerComponent {
  count = signal(0);
  
  constructor() {
    // Effect runs when signal changes
    effect(() => {
      console.log('Count changed to:', this.count());
    });
    
    // Simulate changes
    setInterval(() => {
      this.count.update(c => c + 1);
    }, 2000);
  }
}
```

---

#### 3. Difference Between Signals and Observables

**Signals** are synchronous reactive primitives, while **Observables** are asynchronous streams. Signals provide simpler state management, while Observables handle complex async operations.

| Signals | Observables |
|---------|-------------|
| Synchronous | Asynchronous |
| Always has current value | May not have current value |
| Simpler API | Rich operator ecosystem |
| Fine-grained reactivity | Stream-based reactivity |
| Built-in to Angular | RxJS library |

```typescript
// Observable approach
@Component({
  selector: 'app-observable-example',
  template: `
    <div>Count: {{count$ | async}}</div>
    <div>Double: {{doubleCount$ | async}}</div>
  `
})
export class ObservableComponent {
  private countSubject = new BehaviorSubject(0);
  count$ = this.countSubject.asObservable();
  
  doubleCount$ = this.count$.pipe(
    map(count => count * 2)
  );
  
  increment() {
    this.countSubject.next(this.countSubject.value + 1);
  }
}

// Signal approach (simpler)
@Component({
  selector: 'app-signal-example',
  template: `
    <div>Count: {{count()}}</div>
    <div>Double: {{doubleCount()}}</div>
  `
})
export class SignalComponent {
  count = signal(0);
  doubleCount = computed(() => this.count() * 2);
  
  increment() {
    this.count.update(c => c + 1);
  }
}

// Interoperability between Signals and Observables
@Component({
  selector: 'app-interop',
  standalone: true,
  template: `
    <div>Signal: {{signalValue()}}</div>
    <div>From Observable: {{observableValue() || 'Loading...'}}</div>
  `
})
export class InteropComponent {
  signalValue = signal(0);
  
  // Convert Observable to Signal
  observableValue = toSignal(
    this.http.get<number>('/api/value'),
    { initialValue: 0 }
  );
  
  // Convert Signal to Observable
  signalAsObservable$ = toObservable(this.signalValue);
  
  constructor(private http: HttpClient) {
    // Use signal in Observable chain
    this.signalAsObservable$.pipe(
      debounceTime(300),
      switchMap(value => this.http.post('/api/update', { value }))
    ).subscribe();
  }
}
```

---

#### 4. Advantages of Standalone Architecture

Standalone architecture **simplifies Angular applications** by removing NgModule complexity, improving tree-shaking, and making components more portable and testable.

**Key advantages:**
- Reduced boilerplate
- Better tree-shaking
- Simpler testing
- Improved portability
- Clearer dependencies

```typescript
// Before: Complex NgModule setup
@NgModule({
  declarations: [
    FeatureComponent,
    FeatureChildComponent,
    FeaturePipe
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule.forChild(routes)
  ],
  providers: [FeatureService],
  exports: [FeatureComponent]
})
export class FeatureModule {}

// After: Standalone components
@Component({
  selector: 'app-feature',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    FeatureChildComponent,
    FeaturePipe
  ],
  providers: [FeatureService],
  template: `
    <app-feature-child [data]="data | featurePipe"></app-feature-child>
  `
})
export class FeatureComponent {
  data = 'feature data';
}

// Lazy loading with standalone
const routes: Routes = [
  {
    path: 'feature',
    loadComponent: () => import('./feature.component').then(c => c.FeatureComponent)
  }
];

// Simplified testing
describe('FeatureComponent', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [FeatureComponent] // Just import the component
    });
  });
  
  it('should create', () => {
    const fixture = TestBed.createComponent(FeatureComponent);
    expect(fixture.componentInstance).toBeTruthy();
  });
});

// Micro-frontend friendly
export { FeatureComponent }; // Easy to export and reuse

// Better tree-shaking
@Component({
  selector: 'app-optimized',
  standalone: true,
  imports: [
    // Only import what you need
    NgIf,
    NgFor,
    AsyncPipe
  ],
  template: `
    <div *ngIf="show">
      <div *ngFor="let item of items">{{item | async}}</div>
    </div>
  `
})
export class OptimizedComponent {
  show = true;
  items = [of('item1'), of('item2')];
}
```

---

## 🔹 9. State Management

#### 1. What is State Management?

State management is the **practice of managing application data** that needs to be shared across components. It provides a centralized store for application state with predictable updates.

**Key concepts:**
- Centralized state store
- Predictable state updates
- Time-travel debugging
- Component communication
- Data consistency

```typescript
// Simple state management with service
@Injectable({
  providedIn: 'root'
})
export class StateService {
  private state = {
    users: [],
    currentUser: null,
    loading: false
  };
  
  getState() {
    return { ...this.state };
  }
  
  updateUsers(users: User[]) {
    this.state = { ...this.state, users };
  }
  
  setCurrentUser(user: User) {
    this.state = { ...this.state, currentUser: user };
  }
}

// Component using state service
@Component({
  selector: 'app-user-list',
  template: `
    <div *ngFor="let user of users">
      {{user.name}}
      <button (click)="selectUser(user)">Select</button>
    </div>
  `
})
export class UserListComponent implements OnInit {
  users: User[] = [];
  
  constructor(private stateService: StateService) {}
  
  ngOnInit() {
    this.users = this.stateService.getState().users;
  }
  
  selectUser(user: User) {
    this.stateService.setCurrentUser(user);
  }
}
```

---

#### 2. How Does Angular Handle State?

Angular handles state through **services with dependency injection**, **component communication** via inputs/outputs, and **reactive patterns** with RxJS or Signals.

**State handling patterns:**
- Service-based state
- Component state
- Reactive state with RxJS
- Signal-based state (new)

```typescript
// 1. Service-based state with RxJS
@Injectable({
  providedIn: 'root'
})
export class UserStateService {
  private usersSubject = new BehaviorSubject<User[]>([]);
  users$ = this.usersSubject.asObservable();
  
  private currentUserSubject = new BehaviorSubject<User | null>(null);
  currentUser$ = this.currentUserSubject.asObservable();
  
  loadUsers() {
    this.http.get<User[]>('/api/users').subscribe(users => {
      this.usersSubject.next(users);
    });
  }
  
  selectUser(user: User) {
    this.currentUserSubject.next(user);
  }
}

// 2. Signal-based state (Angular 16+)
@Injectable({
  providedIn: 'root'
})
export class SignalStateService {
  users = signal<User[]>([]);
  currentUser = signal<User | null>(null);
  
  // Computed values
  selectedUserName = computed(() => 
    this.currentUser()?.name || 'No user selected'
  );
  
  loadUsers() {
    this.http.get<User[]>('/api/users').subscribe(users => {
      this.users.set(users);
    });
  }
  
  selectUser(user: User) {
    this.currentUser.set(user);
  }
}

// 3. Component communication
@Component({
  selector: 'app-parent',
  template: `
    <app-user-list 
      [users]="users" 
      (userSelected)="onUserSelected($event)">
    </app-user-list>
    <app-user-detail [user]="selectedUser"></app-user-detail>
  `
})
export class ParentComponent {
  users: User[] = [];
  selectedUser: User | null = null;
  
  onUserSelected(user: User) {
    this.selectedUser = user;
  }
}
```

---

#### 3. NgRx vs Akita vs Signals

**NgRx** is Redux-pattern state management, **Akita** is simpler OOP-based state, and **Signals** provide built-in reactive state. Choose based on complexity and team preferences.

| NgRx | Akita | Signals |
|------|-------|---------|
| Redux pattern | OOP-based | Built-in Angular |
| Complex setup | Simpler setup | Minimal setup |
| Rich ecosystem | Moderate ecosystem | Growing ecosystem |
| Large apps | Medium apps | Small-medium apps |

```typescript
// NgRx approach
// State
interface AppState {
  users: User[];
  loading: boolean;
}

// Actions
export const loadUsers = createAction('[User] Load Users');
export const loadUsersSuccess = createAction(
  '[User] Load Users Success',
  props<{ users: User[] }>()
);

// Reducer
const userReducer = createReducer(
  initialState,
  on(loadUsers, state => ({ ...state, loading: true })),
  on(loadUsersSuccess, (state, { users }) => ({ 
    ...state, 
    users, 
    loading: false 
  }))
);

// Effects
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

// Akita approach
// Store
@StoreConfig({ name: 'users' })
export class UserStore extends EntityStore<UserState> {
  constructor() {
    super();
  }
}

// Service
@Injectable()
export class UserService {
  constructor(private store: UserStore) {}
  
  loadUsers() {
    this.http.get<User[]>('/api/users').subscribe(users => {
      this.store.set(users);
    });
  }
}

// Signals approach (simplest)
@Injectable({
  providedIn: 'root'
})
export class UserSignalService {
  users = signal<User[]>([]);
  loading = signal(false);
  
  async loadUsers() {
    this.loading.set(true);
    try {
      const users = await this.http.get<User[]>('/api/users').toPromise();
      this.users.set(users);
    } finally {
      this.loading.set(false);
    }
  }
}
```

---

#### 4. When Would You NOT Use NgRx?

**Don't use NgRx** for simple applications, small teams without Redux experience, or when local component state suffices. NgRx adds complexity that may not be justified.

**Skip NgRx when:**
- Simple applications
- Small team without Redux knowledge
- Tight deadlines
- Local state is sufficient
- Over-engineering concerns

```typescript
// Simple app - use component state
@Component({
  selector: 'app-simple-todo',
  template: `
    <input [(ngModel)]="newTodo" (keyup.enter)="addTodo()">
    <div *ngFor="let todo of todos">
      {{todo.text}}
      <button (click)="removeTodo(todo.id)">Remove</button>
    </div>
  `
})
export class SimpleTodoComponent {
  todos: Todo[] = [];
  newTodo = '';
  
  addTodo() {
    if (this.newTodo.trim()) {
      this.todos.push({
        id: Date.now(),
        text: this.newTodo,
        completed: false
      });
      this.newTodo = '';
    }
  }
  
  removeTodo(id: number) {
    this.todos = this.todos.filter(todo => todo.id !== id);
  }
}

// Medium complexity - use service with signals
@Injectable({
  providedIn: 'root'
})
export class TodoService {
  todos = signal<Todo[]>([]);
  filter = signal<'all' | 'active' | 'completed'>('all');
  
  filteredTodos = computed(() => {
    const todos = this.todos();
    const filter = this.filter();
    
    switch (filter) {
      case 'active': return todos.filter(t => !t.completed);
      case 'completed': return todos.filter(t => t.completed);
      default: return todos;
    }
  });
  
  addTodo(text: string) {
    this.todos.update(todos => [...todos, {
      id: Date.now(),
      text,
      completed: false
    }]);
  }
}

// Use NgRx only for complex scenarios
// - Multiple data sources
// - Complex state interactions
// - Time-travel debugging needed
// - Large team with Redux experience
// - Audit trail requirements

// Alternative: Simple reactive service
@Injectable({
  providedIn: 'root'
})
export class SimpleStateService {
  private state$ = new BehaviorSubject({
    users: [],
    loading: false,
    error: null
  });
  
  getState() {
    return this.state$.asObservable();
  }
  
  updateState(updates: Partial<any>) {
    this.state$.next({
      ...this.state$.value,
      ...updates
    });
  }
}
```

---

## 📝 Summary

**Modern Angular & State Management** brings powerful new features and patterns:

### Modern Angular:
- **Standalone Components**: Self-contained components without NgModules
- **Signals**: Reactive primitives for fine-grained reactivity
- **Signal vs Observable**: Synchronous vs asynchronous reactive patterns
- **Standalone Architecture**: Simplified, more portable component architecture

### State Management:
- **State Management**: Centralized application data management
- **Angular State Handling**: Services, DI, reactive patterns, and signals
- **NgRx vs Akita vs Signals**: Choose based on complexity and team needs
- **When NOT to use NgRx**: Simple apps, small teams, tight deadlines

**Best practices:**
- Use **standalone components** for new projects
- Adopt **signals** for simpler reactive state
- Choose **appropriate state management** based on app complexity
- Start **simple** and add complexity only when needed
- Consider **team expertise** when choosing state management solutions

Modern Angular provides more choices and simpler patterns while maintaining the power and flexibility needed for complex applications.

## 🔹 10. Security

#### 1. What are Common Angular Security Issues?

Common Angular security issues include **XSS attacks**, **CSRF vulnerabilities**, **insecure HTTP requests**, **exposed sensitive data**, and **improper authentication handling**.

**Main security threats:**
- Cross-Site Scripting (XSS)
- Cross-Site Request Forgery (CSRF)
- Insecure data transmission
- Authentication bypass
- Dependency vulnerabilities

```typescript
// ❌ Security vulnerabilities
@Component({
  template: `
    <!-- XSS vulnerability - unsafe HTML -->
    <div [innerHTML]="userInput"></div>
    
    <!-- Exposed sensitive data -->
    <div>API Key: {{apiKey}}</div>
    
    <!-- Insecure HTTP request -->
    <img [src]="'http://api.example.com/user/' + userId">
  `
})
export class VulnerableComponent {
  userInput = '<script>alert("XSS")</script>'; // Dangerous
  apiKey = 'sk-1234567890abcdef'; // Exposed
  userId = 123;
}

// ✅ Secure practices
@Component({
  template: `
    <!-- Safe HTML rendering -->
    <div>{{userInput}}</div>
    
    <!-- Sanitized HTML if needed -->
    <div [innerHTML]="sanitizedHtml"></div>
    
    <!-- Secure HTTPS requests -->
    <img [src]="secureImageUrl">
  `
})
export class SecureComponent {
  userInput = 'Safe user input';
  sanitizedHtml: SafeHtml;
  secureImageUrl = 'https://secure-api.example.com/image/123';
  
  constructor(private sanitizer: DomSanitizer) {
    // Sanitize HTML content
    this.sanitizedHtml = this.sanitizer.sanitize(
      SecurityContext.HTML, 
      '<p>Safe HTML</p>'
    );
  }
}

// Secure service implementation
@Injectable()
export class SecureApiService {
  private apiUrl = environment.apiUrl; // From environment
  
  constructor(private http: HttpClient) {}
  
  // Secure HTTP with proper headers
  getData(): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'X-Requested-With': 'XMLHttpRequest'
    });
    
    return this.http.get(`${this.apiUrl}/data`, { headers });
  }
}
```

---

#### 2. How Does Angular Prevent XSS?

Angular prevents XSS through **automatic sanitization** of untrusted values, **safe interpolation**, and **Content Security Policy** support. It sanitizes HTML, styles, and URLs by default.

**XSS prevention mechanisms:**
- Automatic sanitization
- Safe interpolation
- Trusted types
- Content Security Policy
- DomSanitizer service

```typescript
// Angular's built-in XSS protection
@Component({
  template: `
    <!-- Safe: Angular sanitizes automatically -->
    <div>{{userInput}}</div>
    
    <!-- Safe: Property binding sanitizes -->
    <div [textContent]="userInput"></div>
    
    <!-- Sanitized: innerHTML is cleaned -->
    <div [innerHTML]="htmlContent"></div>
    
    <!-- Safe: URL sanitization -->
    <a [href]="userUrl">Link</a>
  `
})
export class XssProtectionComponent {
  userInput = '<script>alert("XSS")</script>'; // Rendered as text
  htmlContent = '<p>Safe paragraph</p><script>alert("blocked")</script>';
  userUrl = 'javascript:alert("blocked")'; // Sanitized to unsafe:
  
  constructor(private sanitizer: DomSanitizer) {}
  
  // Bypass sanitization (use carefully)
  getTrustedHtml(html: string): SafeHtml {
    return this.sanitizer.bypassSecurityTrustHtml(html);
  }
  
  // Sanitize manually if needed
  sanitizeHtml(html: string): string {
    return this.sanitizer.sanitize(SecurityContext.HTML, html) || '';
  }
}

// Content Security Policy setup
// In index.html
/*
<meta http-equiv="Content-Security-Policy" 
      content="default-src 'self'; 
               script-src 'self' 'unsafe-inline'; 
               style-src 'self' 'unsafe-inline';">
*/

// Secure pipe for trusted content
@Pipe({ name: 'safeHtml' })
export class SafeHtmlPipe implements PipeTransform {
  constructor(private sanitizer: DomSanitizer) {}
  
  transform(html: string): SafeHtml {
    return this.sanitizer.bypassSecurityTrustHtml(html);
  }
}
```

---

#### 3. How Does Angular Handle CSRF?

Angular handles CSRF through **HttpClientXsrfModule** that automatically reads CSRF tokens from cookies and adds them to HTTP headers for state-changing requests.

**CSRF protection features:**
- Automatic token handling
- Cookie-based tokens
- Configurable header names
- Built-in interceptor

```typescript
// Enable CSRF protection
@NgModule({
  imports: [
    HttpClientModule,
    HttpClientXsrfModule.withOptions({
      cookieName: 'XSRF-TOKEN',     // Cookie name (default)
      headerName: 'X-XSRF-TOKEN'   // Header name (default)
    })
  ]
})
export class AppModule {}

// Custom CSRF configuration
@NgModule({
  imports: [
    HttpClientXsrfModule.withOptions({
      cookieName: 'CSRF-TOKEN',
      headerName: 'X-CSRF-TOKEN'
    })
  ]
})
export class CustomCsrfModule {}

// CSRF token service
@Injectable({
  providedIn: 'root'
})
export class CsrfService {
  constructor(private http: HttpClient) {}
  
  // Get CSRF token manually
  getCsrfToken(): string | null {
    return this.getCookie('XSRF-TOKEN');
  }
  
  private getCookie(name: string): string | null {
    const value = `; ${document.cookie}`;
    const parts = value.split(`; ${name}=`);
    if (parts.length === 2) {
      return parts.pop()?.split(';').shift() || null;
    }
    return null;
  }
  
  // Manual CSRF header
  makeSecureRequest(data: any): Observable<any> {
    const token = this.getCsrfToken();
    const headers = new HttpHeaders({
      'X-CSRF-TOKEN': token || ''
    });
    
    return this.http.post('/api/secure-endpoint', data, { headers });
  }
}

// Backend CSRF setup (Node.js example)
/*
const csrf = require('csurf');
app.use(csrf({ cookie: true }));

app.get('/api/csrf-token', (req, res) => {
  res.json({ token: req.csrfToken() });
});
*/
```

---

#### 4. How to Secure Angular Apps in Production?

Secure Angular apps in production by **enabling HTTPS**, **implementing CSP**, **securing build process**, **environment configuration**, and **regular security audits**.

**Production security checklist:**
- HTTPS everywhere
- Content Security Policy
- Secure build configuration
- Environment variables
- Security headers

```typescript
// Environment configuration
// environment.prod.ts
export const environment = {
  production: true,
  apiUrl: 'https://secure-api.example.com',
  enableLogging: false,
  // Don't expose sensitive data
};

// Security headers interceptor
@Injectable()
export class SecurityHeadersInterceptor implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const secureReq = req.clone({
      setHeaders: {
        'X-Content-Type-Options': 'nosniff',
        'X-Frame-Options': 'DENY',
        'X-XSS-Protection': '1; mode=block',
        'Strict-Transport-Security': 'max-age=31536000; includeSubDomains'
      }
    });
    
    return next.handle(secureReq);
  }
}

// Secure build configuration
// angular.json
{
  "configurations": {
    "production": {
      "optimization": true,
      "sourceMap": false,
      "namedChunks": false,
      "extractLicenses": true,
      "vendorChunk": false,
      "buildOptimizer": true
    }
  }
}

// Security service
@Injectable({
  providedIn: 'root'
})
export class SecurityService {
  // Check if running in secure context
  isSecureContext(): boolean {
    return window.isSecureContext;
  }
  
  // Validate environment
  validateEnvironment(): void {
    if (!environment.production) {
      console.warn('Running in development mode');
    }
    
    if (!this.isSecureContext()) {
      console.error('Not running in secure context (HTTPS)');
    }
  }
  
  // Clear sensitive data on logout
  clearSensitiveData(): void {
    localStorage.clear();
    sessionStorage.clear();
    // Clear any cached data
  }
}

// Content Security Policy (in index.html)
/*
<meta http-equiv="Content-Security-Policy" 
      content="default-src 'self'; 
               script-src 'self'; 
               style-src 'self' 'unsafe-inline'; 
               img-src 'self' data: https:; 
               font-src 'self' https:; 
               connect-src 'self' https://api.example.com;">
*/
```

---

#### 5. JWT Authentication Flow in Angular

JWT authentication involves **storing tokens securely**, **adding tokens to requests**, **handling token expiration**, and **implementing refresh token logic**.

**JWT flow steps:**
1. Login and receive JWT
2. Store token securely
3. Add token to requests
4. Handle token expiration
5. Refresh tokens when needed

```typescript
// Auth service with JWT
@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private tokenKey = 'auth_token';
  private refreshTokenKey = 'refresh_token';
  
  constructor(private http: HttpClient, private router: Router) {}
  
  // Login and store JWT
  login(credentials: LoginCredentials): Observable<AuthResponse> {
    return this.http.post<AuthResponse>('/api/login', credentials).pipe(
      tap(response => {
        this.storeTokens(response.token, response.refreshToken);
      })
    );
  }
  
  // Store tokens securely
  private storeTokens(token: string, refreshToken: string): void {
    localStorage.setItem(this.tokenKey, token);
    localStorage.setItem(this.refreshTokenKey, refreshToken);
  }
  
  // Get stored token
  getToken(): string | null {
    return localStorage.getItem(this.tokenKey);
  }
  
  // Check if token is expired
  isTokenExpired(): boolean {
    const token = this.getToken();
    if (!token) return true;
    
    try {
      const payload = JSON.parse(atob(token.split('.')[1]));
      return payload.exp * 1000 < Date.now();
    } catch {
      return true;
    }
  }
  
  // Refresh token
  refreshToken(): Observable<AuthResponse> {
    const refreshToken = localStorage.getItem(this.refreshTokenKey);
    
    return this.http.post<AuthResponse>('/api/refresh', { refreshToken }).pipe(
      tap(response => {
        this.storeTokens(response.token, response.refreshToken);
      }),
      catchError(() => {
        this.logout();
        return throwError('Token refresh failed');
      })
    );
  }
  
  // Logout
  logout(): void {
    localStorage.removeItem(this.tokenKey);
    localStorage.removeItem(this.refreshTokenKey);
    this.router.navigate(['/login']);
  }
}

// JWT interceptor
@Injectable()
export class JwtInterceptor implements HttpInterceptor {
  constructor(private authService: AuthService) {}
  
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = this.authService.getToken();
    
    if (token && !this.authService.isTokenExpired()) {
      const authReq = req.clone({
        headers: req.headers.set('Authorization', `Bearer ${token}`)
      });
      return next.handle(authReq);
    }
    
    return next.handle(req);
  }
}

// Auth guard with JWT
@Injectable()
export class JwtAuthGuard implements CanActivate {
  constructor(private authService: AuthService, private router: Router) {}
  
  canActivate(): Observable<boolean> | boolean {
    if (this.authService.isTokenExpired()) {
      // Try to refresh token
      return this.authService.refreshToken().pipe(
        map(() => true),
        catchError(() => {
          this.router.navigate(['/login']);
          return of(false);
        })
      );
    }
    
    return true;
  }
}
```

---

## 🔹 11. Testing

#### 1. How Do You Unit Test Angular Apps?

Angular unit testing uses **Jasmine** for test framework and **Karma** for test runner. Tests focus on individual components, services, and pipes in isolation with mocked dependencies.

**Testing tools:**
- Jasmine (test framework)
- Karma (test runner)
- TestBed (Angular testing utility)
- Spies for mocking

```typescript
// Basic component test
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
  
  it('should create', () => {
    expect(component).toBeTruthy();
  });
  
  it('should display user name', () => {
    component.user = { name: 'John', email: 'john@example.com' };
    fixture.detectChanges();
    
    const compiled = fixture.nativeElement;
    expect(compiled.textContent).toContain('John');
  });
  
  it('should emit user selected event', () => {
    spyOn(component.userSelected, 'emit');
    const user = { name: 'John', email: 'john@example.com' };
    
    component.selectUser(user);
    
    expect(component.userSelected.emit).toHaveBeenCalledWith(user);
  });
});

// Service test
describe('UserService', () => {
  let service: UserService;
  
  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UserService);
  });
  
  it('should be created', () => {
    expect(service).toBeTruthy();
  });
  
  it('should add user', () => {
    const user = { name: 'John', email: 'john@example.com' };
    
    service.addUser(user);
    
    expect(service.getUsers()).toContain(user);
  });
});

// Pipe test
describe('CapitalizePipe', () => {
  let pipe: CapitalizePipe;
  
  beforeEach(() => {
    pipe = new CapitalizePipe();
  });
  
  it('should capitalize first letter', () => {
    expect(pipe.transform('hello')).toBe('Hello');
  });
  
  it('should handle empty string', () => {
    expect(pipe.transform('')).toBe('');
  });
});
```

---

#### 2. What is TestBed?

TestBed is Angular's **testing utility** that creates a testing module to configure and create components for testing. It provides methods to configure dependencies, compile components, and create test instances.

**TestBed features:**
- Configure testing module
- Create component instances
- Inject dependencies
- Override providers
- Compile components

```typescript
// Basic TestBed setup
describe('ComponentTest', () => {
  let component: MyComponent;
  let fixture: ComponentFixture<MyComponent>;
  
  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MyComponent],
      imports: [FormsModule, HttpClientTestingModule],
      providers: [MyService]
    });
    
    fixture = TestBed.createComponent(MyComponent);
    component = fixture.componentInstance;
  });
});

// TestBed with overrides
describe('ComponentWithDependencies', () => {
  let component: UserComponent;
  let fixture: ComponentFixture<UserComponent>;
  let mockUserService: jasmine.SpyObj<UserService>;
  
  beforeEach(() => {
    const spy = jasmine.createSpyObj('UserService', ['getUsers', 'addUser']);
    
    TestBed.configureTestingModule({
      declarations: [UserComponent],
      providers: [
        { provide: UserService, useValue: spy }
      ]
    });
    
    fixture = TestBed.createComponent(UserComponent);
    component = fixture.componentInstance;
    mockUserService = TestBed.inject(UserService) as jasmine.SpyObj<UserService>;
  });
  
  it('should load users on init', () => {
    const users = [{ name: 'John' }];
    mockUserService.getUsers.and.returnValue(of(users));
    
    component.ngOnInit();
    
    expect(mockUserService.getUsers).toHaveBeenCalled();
    expect(component.users).toEqual(users);
  });
});

// TestBed with child components
describe('ParentComponent', () => {
  let component: ParentComponent;
  let fixture: ComponentFixture<ParentComponent>;
  
  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [
        ParentComponent,
        ChildComponent // Include child components
      ],
      schemas: [NO_ERRORS_SCHEMA] // Or ignore unknown elements
    });
    
    fixture = TestBed.createComponent(ParentComponent);
    component = fixture.componentInstance;
  });
});

// Standalone component testing
describe('StandaloneComponent', () => {
  let component: StandaloneComponent;
  let fixture: ComponentFixture<StandaloneComponent>;
  
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [StandaloneComponent] // Import standalone component
    });
    
    fixture = TestBed.createComponent(StandaloneComponent);
    component = fixture.componentInstance;
  });
});
```

---

#### 3. How Do You Mock Services?

Mock services using **jasmine.createSpyObj**, **provide overrides**, or **custom mock classes**. This isolates components from their dependencies for focused unit testing.

**Mocking approaches:**
- Jasmine spy objects
- Custom mock classes
- Provider overrides
- Partial mocks

```typescript
// 1. Jasmine spy object (recommended)
describe('ComponentWithService', () => {
  let component: UserListComponent;
  let mockUserService: jasmine.SpyObj<UserService>;
  
  beforeEach(() => {
    const spy = jasmine.createSpyObj('UserService', ['getUsers', 'deleteUser']);
    
    TestBed.configureTestingModule({
      declarations: [UserListComponent],
      providers: [
        { provide: UserService, useValue: spy }
      ]
    });
    
    mockUserService = TestBed.inject(UserService) as jasmine.SpyObj<UserService>;
  });
  
  it('should load users', () => {
    const users = [{ id: 1, name: 'John' }];
    mockUserService.getUsers.and.returnValue(of(users));
    
    component.loadUsers();
    
    expect(mockUserService.getUsers).toHaveBeenCalled();
    expect(component.users).toEqual(users);
  });
});

// 2. Custom mock class
class MockUserService {
  getUsers() {
    return of([{ id: 1, name: 'Mock User' }]);
  }
  
  deleteUser(id: number) {
    return of(null);
  }
}

describe('ComponentWithMockClass', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        { provide: UserService, useClass: MockUserService }
      ]
    });
  });
});

// 3. Partial mock with spyOn
describe('ComponentWithPartialMock', () => {
  let userService: UserService;
  
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [UserService]
    });
    
    userService = TestBed.inject(UserService);
  });
  
  it('should call service method', () => {
    spyOn(userService, 'getUsers').and.returnValue(of([]));
    
    component.loadUsers();
    
    expect(userService.getUsers).toHaveBeenCalled();
  });
});

// 4. Mock with different return values
describe('ServiceWithMultipleScenarios', () => {
  let mockService: jasmine.SpyObj<ApiService>;
  
  beforeEach(() => {
    const spy = jasmine.createSpyObj('ApiService', ['getData']);
    mockService = spy;
  });
  
  it('should handle success', () => {
    mockService.getData.and.returnValue(of({ success: true }));
    
    component.loadData();
    
    expect(component.loading).toBeFalse();
    expect(component.error).toBeNull();
  });
  
  it('should handle error', () => {
    mockService.getData.and.returnValue(throwError('API Error'));
    
    component.loadData();
    
    expect(component.loading).toBeFalse();
    expect(component.error).toBeTruthy();
  });
});
```

---

#### 4. How Do You Mock HTTP Requests?

Mock HTTP requests using **HttpClientTestingModule** and **HttpTestingController**. This allows testing HTTP interactions without making actual network calls.

**HTTP testing tools:**
- HttpClientTestingModule
- HttpTestingController
- expectOne, expectNone
- flush, error methods

```typescript
// HTTP service testing
describe('ApiService', () => {
  let service: ApiService;
  let httpMock: HttpTestingController;
  
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [ApiService]
    });
    
    service = TestBed.inject(ApiService);
    httpMock = TestBed.inject(HttpTestingController);
  });
  
  afterEach(() => {
    httpMock.verify(); // Verify no outstanding requests
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
  
  it('should handle HTTP error', () => {
    service.getUsers().subscribe({
      next: () => fail('Should have failed'),
      error: (error) => {
        expect(error.status).toBe(500);
      }
    });
    
    const req = httpMock.expectOne('/api/users');
    req.error(new ErrorEvent('Network error'), { status: 500 });
  });
  
  it('should send POST request', () => {
    const newUser = { name: 'Jane', email: 'jane@example.com' };
    
    service.createUser(newUser).subscribe(user => {
      expect(user.id).toBeTruthy();
    });
    
    const req = httpMock.expectOne('/api/users');
    expect(req.request.method).toBe('POST');
    expect(req.request.body).toEqual(newUser);
    req.flush({ id: 1, ...newUser });
  });
});

// Component with HTTP testing
describe('UserListComponent', () => {
  let component: UserListComponent;
  let httpMock: HttpTestingController;
  
  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserListComponent],
      imports: [HttpClientTestingModule],
      providers: [UserService]
    });
    
    httpMock = TestBed.inject(HttpTestingController);
  });
  
  it('should load users on init', () => {
    const mockUsers = [{ id: 1, name: 'John' }];
    
    component.ngOnInit();
    
    const req = httpMock.expectOne('/api/users');
    req.flush(mockUsers);
    
    expect(component.users).toEqual(mockUsers);
    expect(component.loading).toBeFalse();
  });
});

// Multiple HTTP requests
describe('DashboardComponent', () => {
  it('should load dashboard data', () => {
    component.loadDashboard();
    
    // Expect multiple requests
    const usersReq = httpMock.expectOne('/api/users');
    const postsReq = httpMock.expectOne('/api/posts');
    const statsReq = httpMock.expectOne('/api/stats');
    
    // Respond to requests
    usersReq.flush([{ id: 1, name: 'John' }]);
    postsReq.flush([{ id: 1, title: 'Post 1' }]);
    statsReq.flush({ totalUsers: 100 });
    
    expect(component.dashboardData).toBeTruthy();
  });
});
```

---

#### 5. What is a Spy in Jasmine?

A spy in Jasmine is a **test double** that tracks function calls and can return controlled values. Spies allow you to monitor method calls and mock return values for testing.

**Spy features:**
- Track function calls
- Mock return values
- Verify call arguments
- Control behavior
- Call through to original

```typescript
// Basic spy usage
describe('SpyExamples', () => {
  let service: UserService;
  
  beforeEach(() => {
    service = new UserService();
  });
  
  it('should spy on method calls', () => {
    spyOn(service, 'getUsers').and.returnValue(of([]));
    
    service.getUsers();
    
    expect(service.getUsers).toHaveBeenCalled();
    expect(service.getUsers).toHaveBeenCalledTimes(1);
  });
  
  it('should spy with arguments', () => {
    spyOn(service, 'getUser').and.returnValue(of({ id: 1, name: 'John' }));
    
    service.getUser(1);
    
    expect(service.getUser).toHaveBeenCalledWith(1);
  });
  
  it('should spy and call through', () => {
    spyOn(service, 'getUsers').and.callThrough();
    
    service.getUsers();
    
    expect(service.getUsers).toHaveBeenCalled();
    // Original method is also called
  });
});

// Spy object creation
describe('SpyObjects', () => {
  let mockService: jasmine.SpyObj<UserService>;
  
  beforeEach(() => {
    mockService = jasmine.createSpyObj('UserService', [
      'getUsers',
      'getUser',
      'createUser'
    ]);
  });
  
  it('should use spy object', () => {
    mockService.getUsers.and.returnValue(of([]));
    
    const result = mockService.getUsers();
    
    expect(mockService.getUsers).toHaveBeenCalled();
  });
});

// Property spies
describe('PropertySpies', () => {
  let obj: any;
  
  beforeEach(() => {
    obj = { prop: 'original' };
  });
  
  it('should spy on property', () => {
    spyOnProperty(obj, 'prop', 'get').and.returnValue('mocked');
    
    expect(obj.prop).toBe('mocked');
  });
  
  it('should spy on property setter', () => {
    spyOnProperty(obj, 'prop', 'set');
    
    obj.prop = 'new value';
    
    expect(Object.getOwnPropertyDescriptor(obj, 'prop')?.set).toHaveBeenCalledWith('new value');
  });
});

// Spy with different behaviors
describe('SpyBehaviors', () => {
  let service: ApiService;
  
  beforeEach(() => {
    service = new ApiService();
  });
  
  it('should return different values', () => {
    spyOn(service, 'getData')
      .and.returnValues(
        of('first'),
        of('second'),
        throwError('error')
      );
    
    service.getData().subscribe(val => expect(val).toBe('first'));
    service.getData().subscribe(val => expect(val).toBe('second'));
    service.getData().subscribe({
      error: err => expect(err).toBe('error')
    });
  });
  
  it('should use fake implementation', () => {
    spyOn(service, 'processData').and.callFake((data) => {
      return `processed: ${data}`;
    });
    
    const result = service.processData('test');
    
    expect(result).toBe('processed: test');
  });
});
```

---

#### 6. How to Test Components with Observables?

Test components with Observables using **async/await**, **fakeAsync/tick**, **marble testing**, or **manual subscription**. Handle asynchronous operations properly in tests.

**Observable testing patterns:**
- async/await with toPromise
- fakeAsync/tick for timers
- Marble testing for complex streams
- Manual subscription management

```typescript
// Testing with async/await
describe('ObservableComponent', () => {
  let component: UserComponent;
  let userService: jasmine.SpyObj<UserService>;
  
  beforeEach(() => {
    const spy = jasmine.createSpyObj('UserService', ['getUsers']);
    userService = spy;
  });
  
  it('should load users async', async () => {
    const users = [{ id: 1, name: 'John' }];
    userService.getUsers.and.returnValue(of(users));
    
    await component.loadUsers();
    
    expect(component.users).toEqual(users);
  });
});

// Testing with fakeAsync
describe('TimerComponent', () => {
  it('should update after delay', fakeAsync(() => {
    component.startTimer();
    
    expect(component.counter).toBe(0);
    
    tick(1000); // Advance time by 1 second
    
    expect(component.counter).toBe(1);
    
    tick(5000); // Advance time by 5 seconds
    
    expect(component.counter).toBe(6);
  }));
  
  it('should handle intervals', fakeAsync(() => {
    component.startInterval();
    
    tick(3000); // 3 intervals
    
    expect(component.ticks).toBe(3);
    
    component.stopInterval();
    tick(1000);
    
    expect(component.ticks).toBe(3); // No more ticks
  }));
});

// Testing with marble diagrams
describe('SearchComponent', () => {
  let scheduler: TestScheduler;
  
  beforeEach(() => {
    scheduler = new TestScheduler((actual, expected) => {
      expect(actual).toEqual(expected);
    });
  });
  
  it('should debounce search input', () => {
    scheduler.run(({ cold, expectObservable }) => {
      const input$ = cold('a-b-c---|');
      const expected = '   ----c---|';
      
      const result$ = input$.pipe(debounceTime(30));
      
      expectObservable(result$).toBe(expected);
    });
  });
});

// Testing component with Observable properties
describe('DataComponent', () => {
  it('should handle observable data', () => {
    const testData = [{ id: 1, name: 'Test' }];
    component.data$ = of(testData);
    
    component.data$.subscribe(data => {
      expect(data).toEqual(testData);
    });
  });
  
  it('should handle errors in observables', () => {
    component.data$ = throwError('Test error');
    
    component.data$.subscribe({
      error: (error) => {
        expect(error).toBe('Test error');
        expect(component.errorMessage).toBeTruthy();
      }
    });
  });
});

// Testing with done callback
describe('AsyncOperations', () => {
  it('should complete async operation', (done) => {
    component.asyncOperation().subscribe({
      next: (result) => {
        expect(result).toBeTruthy();
      },
      complete: () => {
        done();
      }
    });
  });
});

// Testing subscription management
describe('SubscriptionComponent', () => {
  it('should unsubscribe on destroy', () => {
    const subscription = jasmine.createSpyObj('Subscription', ['unsubscribe']);
    component.subscription = subscription;
    
    component.ngOnDestroy();
    
    expect(subscription.unsubscribe).toHaveBeenCalled();
  });
});
```

---

## 📝 Summary

**Angular Security & Testing** are essential for production-ready applications:

### Security:
- **Common Issues**: XSS, CSRF, insecure data transmission, authentication bypass
- **XSS Prevention**: Automatic sanitization, safe interpolation, CSP
- **CSRF Protection**: HttpClientXsrfModule with automatic token handling
- **Production Security**: HTTPS, security headers, secure build, environment config
- **JWT Authentication**: Secure token storage, automatic refresh, proper expiration handling

### Testing:
- **Unit Testing**: Jasmine framework with Karma runner for isolated component testing
- **TestBed**: Angular's testing utility for configuring test modules and dependencies
- **Service Mocking**: Jasmine spies, custom mocks, and provider overrides
- **HTTP Mocking**: HttpClientTestingModule for testing API interactions
- **Jasmine Spies**: Test doubles for tracking calls and controlling behavior
- **Observable Testing**: async/await, fakeAsync/tick, marble testing for reactive code

**Best practices:**
- **Security first**: Implement multiple layers of security protection
- **Test coverage**: Aim for high test coverage with meaningful tests
- **Mock dependencies**: Isolate units under test from external dependencies
- **Handle async**: Properly test asynchronous operations and error scenarios
- **Regular audits**: Continuously monitor and update security measures

These practices ensure building secure, reliable Angular applications with comprehensive test coverage.

## 🔹 12. SSR & Build

#### 1. What is AOT vs JIT?

**AOT (Ahead-of-Time)** compiles templates during build time, while **JIT (Just-in-Time)** compiles templates in the browser at runtime. AOT is the default and preferred approach for production.

| AOT | JIT |
|-----|-----|
| Build-time compilation | Runtime compilation |
| Smaller bundle size | Larger bundle size |
| Better performance | Slower startup |
| Early error detection | Runtime errors |
| Default in Angular | Legacy approach |

```typescript
// AOT compilation (default)
// Templates compiled during ng build
@Component({
  template: `<div>{{name}}</div>` // Compiled to render functions
})
export class AotComponent {
  name = 'AOT Compiled';
}

// JIT compilation (legacy)
// Requires Angular compiler in bundle
import { Compiler } from '@angular/core';

@Component({
  template: `<div>{{name}}</div>` // Compiled at runtime
})
export class JitComponent {
  name = 'JIT Compiled';
}

// Build configuration
// angular.json
{
  "projects": {
    "my-app": {
      "architect": {
        "build": {
          "options": {
            "aot": true, // AOT enabled (default)
            "buildOptimizer": true,
            "optimization": true
          }
        }
      }
    }
  }
}

// AOT benefits in action
// Error caught at build time (AOT)
@Component({
  template: `<div>{{nonExistentProperty}}</div>` // Build error
})
export class ErrorComponent {
  name = 'test';
}

// Tree-shaking with AOT
// Unused code removed during build
export class UnusedService {
  unusedMethod() {
    return 'This will be removed';
  }
}
```

---

#### 2. What is Angular Universal?

Angular Universal enables **Server-Side Rendering (SSR)** for Angular applications. It renders Angular apps on the server, improving SEO, performance, and initial page load times.

**Benefits:**
- Better SEO
- Faster initial load
- Social media sharing
- Improved perceived performance

```typescript
// Install Angular Universal
// ng add @nguniversal/express-engine

// Server-side main file (main.server.ts)
import { ngExpressEngine } from '@nguniversal/express-engine';
import { APP_BASE_HREF } from '@angular/common';
import { existsSync } from 'fs';
import express from 'express';

import { AppServerModule } from './src/main.server';

const app = express();
const PORT = process.env['PORT'] || 4000;

// Universal engine
app.engine('html', ngExpressEngine({
  bootstrap: AppServerModule,
}));

app.set('view engine', 'html');
app.set('views', './dist/my-app');

// Serve static files
app.get('*.*', express.static('./dist/my-app'));

// Universal route
app.get('*', (req, res) => {
  res.render('index', { req });
});

app.listen(PORT, () => {
  console.log(`Server running on http://localhost:${PORT}`);
});

// App server module
import { NgModule } from '@angular/core';
import { ServerModule } from '@angular/platform-server';
import { AppModule } from './app/app.module';
import { AppComponent } from './app/app.component';

@NgModule({
  imports: [AppModule, ServerModule],
  bootstrap: [AppComponent]
})
export class AppServerModule {}

// Platform-specific code
import { isPlatformBrowser } from '@angular/common';
import { PLATFORM_ID, Inject } from '@angular/core';

@Component({
  template: `<div>{{message}}</div>`
})
export class UniversalComponent {
  message = '';
  
  constructor(@Inject(PLATFORM_ID) private platformId: Object) {
    if (isPlatformBrowser(this.platformId)) {
      // Browser-only code
      this.message = 'Running in browser';
      localStorage.setItem('key', 'value');
    } else {
      // Server-only code
      this.message = 'Running on server';
    }
  }
}
```

---

#### 3. What is SSR and How It Works?

**SSR (Server-Side Rendering)** renders Angular components on the server and sends HTML to the client. The client then hydrates the app, making it interactive.

**SSR Process:**
1. Server renders components to HTML
2. HTML sent to client
3. Client downloads Angular bundle
4. App hydrates and becomes interactive

```typescript
// SSR-compatible component
@Component({
  selector: 'app-product',
  template: `
    <div>
      <h1>{{product.name}}</h1>
      <p>{{product.description}}</p>
      <button (click)="addToCart()" [disabled]="!isBrowser">
        Add to Cart
      </button>
    </div>
  `
})
export class ProductComponent implements OnInit {
  product: Product = {};
  isBrowser: boolean;
  
  constructor(
    @Inject(PLATFORM_ID) private platformId: Object,
    private productService: ProductService
  ) {
    this.isBrowser = isPlatformBrowser(this.platformId);
  }
  
  ngOnInit() {
    // This runs on both server and client
    this.loadProduct();
  }
  
  loadProduct() {
    this.productService.getProduct(1).subscribe(product => {
      this.product = product;
    });
  }
  
  addToCart() {
    if (this.isBrowser) {
      // Browser-only functionality
      console.log('Added to cart');
    }
  }
}

// SSR-compatible service
@Injectable({
  providedIn: 'root'
})
export class ProductService {
  constructor(
    private http: HttpClient,
    @Inject(PLATFORM_ID) private platformId: Object
  ) {}
  
  getProduct(id: number): Observable<Product> {
    // Works on both server and client
    return this.http.get<Product>(`/api/products/${id}`);
  }
  
  // Browser-only method
  saveToLocalStorage(data: any) {
    if (isPlatformBrowser(this.platformId)) {
      localStorage.setItem('product', JSON.stringify(data));
    }
  }
}

// Build and serve commands
// Build for SSR
// npm run build:ssr

// Serve SSR app
// npm run serve:ssr

// Package.json scripts
{
  "scripts": {
    "build:ssr": "ng build && ng run my-app:server",
    "serve:ssr": "node dist/my-app/server/main.js",
    "prerender": "ng run my-app:prerender"
  }
}
```

---

#### 4. Purpose of `angular.json`

`angular.json` is the **workspace configuration file** that defines build settings, project structure, and CLI commands for Angular applications and libraries.

**Key sections:**
- Project configuration
- Build targets
- Serve options
- Test settings
- Lint configuration

```json
{
  "version": 1,
  "newProjectRoot": "projects",
  "projects": {
    "my-app": {
      "projectType": "application",
      "schematics": {
        "@schematics/angular:component": {
          "style": "scss",
          "skipTests": false
        }
      },
      "root": "",
      "sourceRoot": "src",
      "prefix": "app",
      "architect": {
        "build": {
          "builder": "@angular-devkit/build-angular:browser",
          "options": {
            "outputPath": "dist/my-app",
            "index": "src/index.html",
            "main": "src/main.ts",
            "polyfills": "src/polyfills.ts",
            "tsConfig": "tsconfig.app.json",
            "assets": [
              "src/favicon.ico",
              "src/assets"
            ],
            "styles": [
              "src/styles.scss"
            ],
            "scripts": []
          },
          "configurations": {
            "production": {
              "budgets": [
                {
                  "type": "initial",
                  "maximumWarning": "2mb",
                  "maximumError": "5mb"
                }
              ],
              "fileReplacements": [
                {
                  "replace": "src/environments/environment.ts",
                  "with": "src/environments/environment.prod.ts"
                }
              ],
              "outputHashing": "all"
            },
            "development": {
              "buildOptimizer": false,
              "optimization": false,
              "vendorChunk": true,
              "extractLicenses": false,
              "sourceMap": true,
              "namedChunks": true
            }
          }
        },
        "serve": {
          "builder": "@angular-devkit/build-angular:dev-server",
          "configurations": {
            "production": {
              "browserTarget": "my-app:build:production"
            },
            "development": {
              "browserTarget": "my-app:build:development"
            }
          }
        },
        "test": {
          "builder": "@angular-devkit/build-angular:karma",
          "options": {
            "main": "src/test.ts",
            "polyfills": "src/polyfills.ts",
            "tsConfig": "tsconfig.spec.json",
            "karmaConfig": "karma.conf.js",
            "codeCoverage": true
          }
        }
      }
    }
  }
}
```

---

#### 5. Environment-Specific Configuration

Environment configuration allows **different settings** for development, staging, and production builds using file replacement and environment variables.

**Environment setup:**
- Multiple environment files
- File replacement during build
- Runtime configuration
- Feature flags

```typescript
// src/environments/environment.ts (development)
export const environment = {
  production: false,
  apiUrl: 'http://localhost:3000/api',
  enableLogging: true,
  features: {
    newFeature: true,
    betaFeature: false
  }
};

// src/environments/environment.prod.ts (production)
export const environment = {
  production: true,
  apiUrl: 'https://api.myapp.com',
  enableLogging: false,
  features: {
    newFeature: true,
    betaFeature: false
  }
};

// src/environments/environment.staging.ts (staging)
export const environment = {
  production: false,
  apiUrl: 'https://staging-api.myapp.com',
  enableLogging: true,
  features: {
    newFeature: true,
    betaFeature: true
  }
};

// Using environment in services
@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private baseUrl = environment.apiUrl;
  
  constructor(private http: HttpClient) {
    if (environment.enableLogging) {
      console.log('API Service initialized with URL:', this.baseUrl);
    }
  }
  
  getData(): Observable<any> {
    return this.http.get(`${this.baseUrl}/data`);
  }
}

// Feature flag service
@Injectable({
  providedIn: 'root'
})
export class FeatureService {
  isFeatureEnabled(feature: string): boolean {
    return environment.features[feature] || false;
  }
}

// Component using environment
@Component({
  template: `
    <div *ngIf="showNewFeature">
      <h2>New Feature!</h2>
    </div>
  `
})
export class FeatureComponent {
  showNewFeature = this.featureService.isFeatureEnabled('newFeature');
  
  constructor(private featureService: FeatureService) {}
}

// Build configurations in angular.json
{
  "configurations": {
    "staging": {
      "fileReplacements": [
        {
          "replace": "src/environments/environment.ts",
          "with": "src/environments/environment.staging.ts"
        }
      ]
    }
  }
}

// Build commands
// ng build --configuration=production
// ng build --configuration=staging
// ng serve --configuration=development
```

---

## 🔹 13. Architecture & Real-World Scenarios

#### 1. How Do You Organize a Large Angular App?

Large Angular apps use **feature modules**, **shared modules**, **core module**, and **lazy loading** for maintainable architecture with clear separation of concerns.

**Architecture patterns:**
- Feature-based modules
- Shared and core modules
- Lazy loading
- Barrel exports
- Folder structure conventions

```typescript
// Project structure
/*
src/
├── app/
│   ├── core/
│   │   ├── services/
│   │   ├── guards/
│   │   ├── interceptors/
│   │   └── core.module.ts
│   ├── shared/
│   │   ├── components/
│   │   ├── pipes/
│   │   ├── directives/
│   │   └── shared.module.ts
│   ├── features/
│   │   ├── user-management/
│   │   │   ├── components/
│   │   │   ├── services/
│   │   │   ├── user-management-routing.module.ts
│   │   │   └── user-management.module.ts
│   │   └── product-catalog/
│   └── app-routing.module.ts
*/

// Core module (singleton services)
@NgModule({
  providers: [
    AuthService,
    ApiService,
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }
  ]
})
export class CoreModule {
  constructor(@Optional() @SkipSelf() parentModule: CoreModule) {
    if (parentModule) {
      throw new Error('CoreModule is already loaded. Import only once.');
    }
  }
}

// Shared module (reusable components)
@NgModule({
  declarations: [
    LoadingSpinnerComponent,
    ConfirmDialogComponent,
    CustomPipe
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule
  ],
  exports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    LoadingSpinnerComponent,
    ConfirmDialogComponent,
    CustomPipe
  ]
})
export class SharedModule {}

// Feature module
@NgModule({
  declarations: [
    UserListComponent,
    UserDetailComponent,
    UserFormComponent
  ],
  imports: [
    SharedModule,
    UserManagementRoutingModule
  ]
})
export class UserManagementModule {}

// Lazy loading routes
const routes: Routes = [
  {
    path: 'users',
    loadChildren: () => import('./features/user-management/user-management.module')
      .then(m => m.UserManagementModule)
  },
  {
    path: 'products',
    loadChildren: () => import('./features/product-catalog/product-catalog.module')
      .then(m => m.ProductCatalogModule)
  }
];

// Barrel exports (index.ts)
// src/app/shared/index.ts
export * from './components/loading-spinner/loading-spinner.component';
export * from './pipes/custom.pipe';
export * from './shared.module';
```

---

#### 2. How Do Components Communicate?

Components communicate through **Input/Output properties**, **services with observables**, **ViewChild/ContentChild**, and **state management** patterns.

**Communication patterns:**
- Parent to child: @Input
- Child to parent: @Output
- Sibling components: Service
- Unrelated components: State management

```typescript
// 1. Parent to Child (@Input)
@Component({
  selector: 'app-parent',
  template: `
    <app-child [userData]="user" [isActive]="active"></app-child>
  `
})
export class ParentComponent {
  user = { name: 'John', age: 30 };
  active = true;
}

@Component({
  selector: 'app-child',
  template: `<div>{{userData.name}} - {{isActive ? 'Active' : 'Inactive'}}</div>`
})
export class ChildComponent {
  @Input() userData: User;
  @Input() isActive: boolean;
}

// 2. Child to Parent (@Output)
@Component({
  selector: 'app-child',
  template: `<button (click)="sendData()">Send to Parent</button>`
})
export class ChildComponent {
  @Output() dataEvent = new EventEmitter<string>();
  
  sendData() {
    this.dataEvent.emit('Data from child');
  }
}

@Component({
  template: `
    <app-child (dataEvent)="handleChildData($event)"></app-child>
    <p>{{childMessage}}</p>
  `
})
export class ParentComponent {
  childMessage = '';
  
  handleChildData(data: string) {
    this.childMessage = data;
  }
}

// 3. Service-based communication
@Injectable({
  providedIn: 'root'
})
export class CommunicationService {
  private messageSubject = new Subject<string>();
  message$ = this.messageSubject.asObservable();
  
  sendMessage(message: string) {
    this.messageSubject.next(message);
  }
}

@Component({
  template: `<button (click)="sendMessage()">Send Message</button>`
})
export class SenderComponent {
  constructor(private commService: CommunicationService) {}
  
  sendMessage() {
    this.commService.sendMessage('Hello from sender');
  }
}

@Component({
  template: `<div>{{message}}</div>`
})
export class ReceiverComponent implements OnInit {
  message = '';
  
  constructor(private commService: CommunicationService) {}
  
  ngOnInit() {
    this.commService.message$.subscribe(msg => {
      this.message = msg;
    });
  }
}

// 4. ViewChild communication
@Component({
  template: `
    <app-child #childRef></app-child>
    <button (click)="callChildMethod()">Call Child</button>
  `
})
export class ParentComponent {
  @ViewChild('childRef') childComponent: ChildComponent;
  
  callChildMethod() {
    this.childComponent.childMethod();
  }
}
```

---

#### 3. How to Avoid Memory Leaks?

Avoid memory leaks by **unsubscribing from observables**, **removing event listeners**, **clearing timers**, and **using takeUntil pattern** for automatic cleanup.

**Memory leak prevention:**
- Unsubscribe from observables
- Clear timers and intervals
- Remove DOM event listeners
- Use takeUntil pattern
- Avoid circular references

```typescript
// ❌ Memory leak examples
@Component({
  template: `<div>Memory Leak Component</div>`
})
export class LeakyComponent implements OnInit {
  ngOnInit() {
    // ❌ Never unsubscribed
    this.dataService.getData().subscribe(data => {
      console.log(data);
    });
    
    // ❌ Timer never cleared
    setInterval(() => {
      console.log('Timer tick');
    }, 1000);
    
    // ❌ Event listener never removed
    document.addEventListener('click', this.handleClick);
  }
  
  handleClick = () => {
    console.log('Clicked');
  }
}

// ✅ Memory leak prevention
@Component({
  template: `<div>Clean Component</div>`
})
export class CleanComponent implements OnInit, OnDestroy {
  private destroy$ = new Subject<void>();
  private timerId: any;
  
  ngOnInit() {
    // ✅ Automatic unsubscription with takeUntil
    this.dataService.getData().pipe(
      takeUntil(this.destroy$)
    ).subscribe(data => {
      console.log(data);
    });
    
    // ✅ Store timer ID for cleanup
    this.timerId = setInterval(() => {
      console.log('Timer tick');
    }, 1000);
    
    // ✅ Event listener with cleanup
    document.addEventListener('click', this.handleClick);
  }
  
  ngOnDestroy() {
    // ✅ Complete destroy subject
    this.destroy$.next();
    this.destroy$.complete();
    
    // ✅ Clear timer
    if (this.timerId) {
      clearInterval(this.timerId);
    }
    
    // ✅ Remove event listener
    document.removeEventListener('click', this.handleClick);
  }
  
  handleClick = () => {
    console.log('Clicked');
  }
}

// ✅ Base class for automatic cleanup
export abstract class BaseComponent implements OnDestroy {
  protected destroy$ = new Subject<void>();
  
  ngOnDestroy() {
    this.destroy$.next();
    this.destroy$.complete();
  }
}

@Component({
  template: `<div>Extended Component</div>`
})
export class ExtendedComponent extends BaseComponent implements OnInit {
  ngOnInit() {
    this.dataService.getData().pipe(
      takeUntil(this.destroy$)
    ).subscribe(data => console.log(data));
  }
}

// ✅ Async pipe (automatic cleanup)
@Component({
  template: `
    <div *ngFor="let item of items$ | async">{{item.name}}</div>
  `
})
export class AsyncPipeComponent {
  items$ = this.dataService.getItems(); // No manual subscription needed
}
```

---

#### 4. How to Debug Angular Apps?

Debug Angular apps using **Angular DevTools**, **browser developer tools**, **console logging**, **breakpoints**, and **performance profiling**.

**Debugging tools:**
- Angular DevTools
- Chrome DevTools
- Console logging
- Breakpoints
- Network tab
- Performance profiler

```typescript
// Console debugging
@Component({
  template: `<div>Debug Component</div>`
})
export class DebugComponent implements OnInit {
  data: any[] = [];
  
  ngOnInit() {
    console.log('Component initialized');
    console.group('Data Loading');
    
    this.loadData();
    
    console.groupEnd();
  }
  
  loadData() {
    console.time('Data Load Time');
    
    this.dataService.getData().subscribe({
      next: (data) => {
        console.log('Data received:', data);
        console.table(data); // Table format
        this.data = data;
        console.timeEnd('Data Load Time');
      },
      error: (error) => {
        console.error('Data load error:', error);
        console.trace(); // Stack trace
      }
    });
  }
  
  // Debug method calls
  onButtonClick() {
    debugger; // Breakpoint
    console.log('Button clicked');
  }
}

// Debug service with logging
@Injectable({
  providedIn: 'root'
})
export class DebugService {
  private debug = !environment.production;
  
  log(message: string, data?: any) {
    if (this.debug) {
      console.log(`[${new Date().toISOString()}] ${message}`, data);
    }
  }
  
  error(message: string, error?: any) {
    console.error(`[${new Date().toISOString()}] ${message}`, error);
  }
}

// Performance debugging
@Component({
  template: `<div>Performance Component</div>`
})
export class PerformanceComponent {
  @HostListener('click', ['$event'])
  onClick(event: Event) {
    console.time('Click Handler');
    
    // Expensive operation
    this.expensiveOperation();
    
    console.timeEnd('Click Handler');
  }
  
  expensiveOperation() {
    performance.mark('expensive-start');
    
    // Simulate work
    for (let i = 0; i < 1000000; i++) {
      Math.random();
    }
    
    performance.mark('expensive-end');
    performance.measure('expensive-operation', 'expensive-start', 'expensive-end');
    
    const measures = performance.getEntriesByType('measure');
    console.log('Performance measures:', measures);
  }
}

// Error boundary service
@Injectable({
  providedIn: 'root'
})
export class ErrorService {
  handleError(error: any, context?: string) {
    const errorInfo = {
      message: error.message,
      stack: error.stack,
      context: context,
      timestamp: new Date().toISOString(),
      userAgent: navigator.userAgent,
      url: window.location.href
    };
    
    console.error('Application Error:', errorInfo);
    
    // Send to logging service
    this.sendToLoggingService(errorInfo);
  }
  
  private sendToLoggingService(errorInfo: any) {
    // Send error to external logging service
    console.log('Sending error to logging service:', errorInfo);
  }
}

// Debug interceptor
@Injectable()
export class DebugInterceptor implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    console.log('HTTP Request:', req.method, req.url);
    
    const start = performance.now();
    
    return next.handle(req).pipe(
      tap({
        next: (event) => {
          if (event instanceof HttpResponse) {
            const duration = performance.now() - start;
            console.log(`HTTP Response: ${req.url} (${duration.toFixed(2)}ms)`, event);
          }
        },
        error: (error) => {
          console.error('HTTP Error:', req.url, error);
        }
      })
    );
  }
}
```

---

#### 5. What Design Patterns Does Angular Follow?

Angular follows **Dependency Injection**, **Observer pattern**, **MVC/MVVM**, **Decorator pattern**, and **Factory pattern** for maintainable and testable architecture.

**Key patterns:**
- Dependency Injection
- Observer (RxJS)
- MVC/MVVM
- Decorator
- Factory
- Singleton

```typescript
// 1. Dependency Injection Pattern
@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor(private http: HttpClient) {} // DI
}

@Component({
  template: `<div>DI Component</div>`
})
export class DIComponent {
  constructor(private userService: UserService) {} // DI
}

// 2. Observer Pattern (RxJS)
@Injectable({
  providedIn: 'root'
})
export class NotificationService {
  private notificationSubject = new Subject<string>();
  notifications$ = this.notificationSubject.asObservable();
  
  notify(message: string) {
    this.notificationSubject.next(message); // Notify observers
  }
}

@Component({
  template: `<div *ngFor="let notification of notifications">{{notification}}</div>`
})
export class ObserverComponent implements OnInit {
  notifications: string[] = [];
  
  constructor(private notificationService: NotificationService) {}
  
  ngOnInit() {
    // Subscribe to notifications (Observer)
    this.notificationService.notifications$.subscribe(notification => {
      this.notifications.push(notification);
    });
  }
}

// 3. MVC/MVVM Pattern
// Model
export interface User {
  id: number;
  name: string;
  email: string;
}

// View (Template)
// Component acts as Controller/ViewModel
@Component({
  selector: 'app-user',
  template: `
    <div>
      <h2>{{user.name}}</h2>
      <p>{{user.email}}</p>
      <button (click)="updateUser()">Update</button>
    </div>
  `
})
export class UserComponent {
  user: User = { id: 1, name: 'John', email: 'john@example.com' }; // Model
  
  updateUser() {
    // Controller logic
    this.user = { ...this.user, name: 'Updated Name' };
  }
}

// 4. Decorator Pattern
@Component({
  selector: 'app-decorated',
  template: `<div>Decorated Component</div>`
})
export class DecoratedComponent {
  @Input() data: any; // Property decorator
  @Output() event = new EventEmitter(); // Property decorator
  
  @HostListener('click', ['$event']) // Method decorator
  onClick(event: Event) {
    console.log('Clicked');
  }
}

// 5. Factory Pattern
@Injectable({
  providedIn: 'root'
})
export class LoggerFactory {
  createLogger(type: 'console' | 'file' | 'remote'): Logger {
    switch (type) {
      case 'console':
        return new ConsoleLogger();
      case 'file':
        return new FileLogger();
      case 'remote':
        return new RemoteLogger();
      default:
        return new ConsoleLogger();
    }
  }
}

interface Logger {
  log(message: string): void;
}

class ConsoleLogger implements Logger {
  log(message: string) {
    console.log(message);
  }
}

// 6. Strategy Pattern
interface ValidationStrategy {
  validate(value: any): boolean;
}

class EmailValidationStrategy implements ValidationStrategy {
  validate(value: string): boolean {
    return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(value);
  }
}

class RequiredValidationStrategy implements ValidationStrategy {
  validate(value: any): boolean {
    return value != null && value !== '';
  }
}

@Injectable()
export class ValidationService {
  validate(value: any, strategy: ValidationStrategy): boolean {
    return strategy.validate(value);
  }
}
```

---

#### 6. Singleton Pattern in Angular Services

Angular services are **singletons by default** when provided in root or module level. The same instance is shared across the entire application or module scope.

**Singleton characteristics:**
- Single instance per injector
- Shared state
- Memory efficient
- Global access point

```typescript
// Singleton service (default behavior)
@Injectable({
  providedIn: 'root' // Creates singleton instance
})
export class SingletonService {
  private counter = 0;
  private data: any[] = [];
  
  increment() {
    this.counter++;
    console.log('Counter:', this.counter);
  }
  
  getCounter() {
    return this.counter;
  }
  
  addData(item: any) {
    this.data.push(item);
  }
  
  getData() {
    return [...this.data]; // Return copy to prevent mutation
  }
}

// Components sharing singleton instance
@Component({
  selector: 'app-component-a',
  template: `
    <div>Component A - Counter: {{counter}}</div>
    <button (click)="increment()">Increment</button>
  `
})
export class ComponentA {
  constructor(private singletonService: SingletonService) {}
  
  get counter() {
    return this.singletonService.getCounter();
  }
  
  increment() {
    this.singletonService.increment(); // Affects all components
  }
}

@Component({
  selector: 'app-component-b',
  template: `
    <div>Component B - Counter: {{counter}}</div>
    <button (click)="increment()">Increment</button>
  `
})
export class ComponentB {
  constructor(private singletonService: SingletonService) {} // Same instance
  
  get counter() {
    return this.singletonService.getCounter();
  }
  
  increment() {
    this.singletonService.increment(); // Affects all components
  }
}

// Non-singleton service (multiple instances)
@Injectable() // No providedIn: 'root'
export class NonSingletonService {
  private id = Math.random();
  
  getId() {
    return this.id;
  }
}

@Component({
  providers: [NonSingletonService], // New instance per component
  template: `<div>Instance ID: {{serviceId}}</div>`
})
export class ComponentWithOwnInstance {
  serviceId: number;
  
  constructor(private service: NonSingletonService) {
    this.serviceId = this.service.getId(); // Different ID per component
  }
}

// Singleton with lazy initialization
@Injectable({
  providedIn: 'root'
})
export class LazyInitializationService {
  private _expensiveResource: any;
  
  get expensiveResource() {
    if (!this._expensiveResource) {
      console.log('Initializing expensive resource...');
      this._expensiveResource = this.createExpensiveResource();
    }
    return this._expensiveResource;
  }
  
  private createExpensiveResource() {
    // Expensive initialization
    return { data: 'expensive data' };
  }
}

// Testing singleton behavior
describe('SingletonService', () => {
  let service1: SingletonService;
  let service2: SingletonService;
  
  beforeEach(() => {
    TestBed.configureTestingModule({});
    service1 = TestBed.inject(SingletonService);
    service2 = TestBed.inject(SingletonService);
  });
  
  it('should be singleton', () => {
    expect(service1).toBe(service2); // Same instance
    
    service1.increment();
    expect(service2.getCounter()).toBe(1); // Shared state
  });
});
```

---

## 📝 Summary

**Angular SSR & Build + Architecture & Real-World Scenarios** covers essential production concepts:

### SSR & Build:
- **AOT vs JIT**: Build-time vs runtime compilation with AOT as default
- **Angular Universal**: Server-side rendering for better SEO and performance
- **SSR Process**: Server rendering, client hydration, and platform-specific code
- **angular.json**: Workspace configuration for builds, serves, and deployments
- **Environment Config**: Multiple environments with file replacement and feature flags

### Architecture & Real-World:
- **Large App Organization**: Feature modules, shared/core modules, lazy loading
- **Component Communication**: Input/Output, services, ViewChild, state management
- **Memory Leak Prevention**: Unsubscription patterns, cleanup, takeUntil usage
- **Debugging**: DevTools, console logging, performance profiling, error handling
- **Design Patterns**: DI, Observer, MVC/MVVM, Decorator, Factory patterns
- **Singleton Services**: Default behavior, shared state, lazy initialization

**Best practices:**
- **Modular architecture** with clear separation of concerns
- **Proper cleanup** to prevent memory leaks
- **Environment-specific** configurations for different deployment stages
- **SSR implementation** for better SEO and initial load performance
- **Design patterns** for maintainable and testable code
- **Comprehensive debugging** strategies for production issues

These concepts are essential for building scalable, maintainable Angular applications that perform well in production environments.

## 🔹 14. Behavioral / Experience-Based

#### 1. Most Challenging Angular Issue You Faced?

**Challenge:** Memory leaks in a large dashboard application with real-time data updates causing browser crashes after extended use.

**Problem:** Multiple WebSocket connections, unsubscribed observables, and DOM event listeners were accumulating over time, leading to memory consumption growing from 50MB to over 2GB.

**Solution approach:**
- Implemented systematic subscription management
- Created base component for automatic cleanup
- Added memory monitoring and leak detection
- Established coding standards for observable handling

```typescript
// Problem: Memory leaks everywhere
@Component({
  template: `<div>Leaky Dashboard</div>`
})
export class LeakyDashboardComponent implements OnInit {
  ngOnInit() {
    // ❌ Never unsubscribed
    this.websocketService.connect().subscribe(data => {
      this.updateChart(data);
    });
    
    // ❌ Timer never cleared
    setInterval(() => {
      this.refreshData();
    }, 5000);
    
    // ❌ Event listener never removed
    window.addEventListener('resize', this.onResize);
  }
}

// Solution: Systematic cleanup
@Component({
  template: `<div>Clean Dashboard</div>`
})
export class CleanDashboardComponent extends BaseComponent implements OnInit {
  private timerId: any;
  
  ngOnInit() {
    // ✅ Automatic unsubscription
    this.websocketService.connect().pipe(
      takeUntil(this.destroy$)
    ).subscribe(data => {
      this.updateChart(data);
    });
    
    // ✅ Tracked timer
    this.timerId = setInterval(() => {
      this.refreshData();
    }, 5000);
    
    // ✅ Managed event listener
    this.addEventListenerWithCleanup(window, 'resize', this.onResize);
  }
  
  ngOnDestroy() {
    super.ngOnDestroy();
    if (this.timerId) clearInterval(this.timerId);
  }
}

// Base component for automatic cleanup
export abstract class BaseComponent implements OnDestroy {
  protected destroy$ = new Subject<void>();
  private eventListeners: Array<{target: any, event: string, handler: any}> = [];
  
  protected addEventListenerWithCleanup(target: any, event: string, handler: any) {
    target.addEventListener(event, handler);
    this.eventListeners.push({ target, event, handler });
  }
  
  ngOnDestroy() {
    this.destroy$.next();
    this.destroy$.complete();
    
    // Clean up event listeners
    this.eventListeners.forEach(({ target, event, handler }) => {
      target.removeEventListener(event, handler);
    });
  }
}

// Memory monitoring service
@Injectable({
  providedIn: 'root'
})
export class MemoryMonitorService {
  startMonitoring() {
    if ('memory' in performance) {
      setInterval(() => {
        const memory = (performance as any).memory;
        console.log(`Memory: ${(memory.usedJSHeapSize / 1048576).toFixed(2)}MB`);
        
        if (memory.usedJSHeapSize > 500 * 1048576) { // 500MB threshold
          console.warn('High memory usage detected');
        }
      }, 10000);
    }
  }
}
```

**Result:** Reduced memory usage by 85%, eliminated browser crashes, and established reusable patterns for the entire team.

---

#### 2. How Did You Optimize Performance?

**Scenario:** E-commerce application with 10,000+ products was experiencing slow rendering, poor scroll performance, and 3-second load times.

**Optimization strategies implemented:**
- OnPush change detection strategy
- Virtual scrolling for large lists
- Lazy loading and code splitting
- Image optimization and caching
- Bundle analysis and tree-shaking

```typescript
// Before: Poor performance
@Component({
  template: `
    <div *ngFor="let product of products">
      <img [src]="product.imageUrl">
      <h3>{{product.name}}</h3>
      <p>{{formatPrice(product.price)}}</p>
    </div>
  `
})
export class SlowProductListComponent {
  products: Product[] = []; // 10,000 items
  
  formatPrice(price: number): string {
    // Called on every change detection cycle
    return new Intl.NumberFormat('en-US', {
      style: 'currency',
      currency: 'USD'
    }).format(price);
  }
}

// After: Optimized performance
@Component({
  selector: 'app-product-list',
  changeDetection: ChangeDetectionStrategy.OnPush,
  template: `
    <cdk-virtual-scroll-viewport itemSize="200" class="viewport">
      <div *cdkVirtualFor="let product of products; trackBy: trackByProductId">
        <img [src]="product.imageUrl" 
             loading="lazy" 
             [alt]="product.name">
        <h3>{{product.name}}</h3>
        <p>{{product.price | currency}}</p>
      </div>
    </cdk-virtual-scroll-viewport>
  `,
  styles: ['.viewport { height: 600px; }']
})
export class OptimizedProductListComponent {
  @Input() products: Product[] = [];
  
  constructor(private cdr: ChangeDetectorRef) {}
  
  trackByProductId(index: number, product: Product): number {
    return product.id;
  }
  
  // Manual change detection when needed
  updateProducts(newProducts: Product[]) {
    this.products = newProducts;
    this.cdr.markForCheck();
  }
}

// Lazy loading implementation
const routes: Routes = [
  {
    path: 'products',
    loadChildren: () => import('./products/products.module').then(m => m.ProductsModule)
  },
  {
    path: 'checkout',
    loadComponent: () => import('./checkout/checkout.component').then(c => c.CheckoutComponent)
  }
];

// Image optimization service
@Injectable({
  providedIn: 'root'
})
export class ImageOptimizationService {
  private imageCache = new Map<string, string>();
  
  getOptimizedImageUrl(originalUrl: string, width: number = 300): string {
    const cacheKey = `${originalUrl}_${width}`;
    
    if (this.imageCache.has(cacheKey)) {
      return this.imageCache.get(cacheKey)!;
    }
    
    // Generate optimized URL (WebP format, specific dimensions)
    const optimizedUrl = `${originalUrl}?w=${width}&f=webp&q=80`;
    this.imageCache.set(cacheKey, optimizedUrl);
    
    return optimizedUrl;
  }
}

// Performance monitoring
@Injectable({
  providedIn: 'root'
})
export class PerformanceService {
  measureComponentRender(componentName: string, renderFn: () => void) {
    performance.mark(`${componentName}-start`);
    renderFn();
    performance.mark(`${componentName}-end`);
    
    performance.measure(
      `${componentName}-render`,
      `${componentName}-start`,
      `${componentName}-end`
    );
    
    const measure = performance.getEntriesByName(`${componentName}-render`)[0];
    if (measure.duration > 16) { // 60fps threshold
      console.warn(`Slow render detected: ${componentName} (${measure.duration.toFixed(2)}ms)`);
    }
  }
}

// Bundle optimization (webpack-bundle-analyzer results)
// Before: 2.5MB initial bundle
// After: 450KB initial bundle + lazy chunks
```

**Results:** 
- Load time: 3s → 800ms
- First Contentful Paint: 2.1s → 600ms
- Bundle size: 2.5MB → 450KB initial
- Scroll performance: 15fps → 60fps

---

#### 3. How Do You Stay Updated with Angular?

**My learning strategy combines official sources, community engagement, and hands-on practice:**

**Official channels:**
- Angular blog and release notes
- Angular YouTube channel
- Official documentation updates
- Angular DevKit releases

**Community engagement:**
- Angular conferences (ng-conf, AngularConnect)
- Twitter Angular community
- Reddit r/Angular2
- Stack Overflow contributions

**Hands-on learning:**
- Personal projects with new features
- Contributing to open source
- Code reviews and team knowledge sharing
- Building proof-of-concepts

```typescript
// Example: Staying current with Angular 17 features
// New control flow syntax (Angular 17)
@Component({
  template: `
    <!-- New @if syntax -->
    @if (user) {
      <div>Welcome {{user.name}}</div>
    } @else {
      <div>Please login</div>
    }
    
    <!-- New @for syntax -->
    @for (item of items; track item.id) {
      <div>{{item.name}}</div>
    } @empty {
      <div>No items found</div>
    }
    
    <!-- New @switch syntax -->
    @switch (status) {
      @case ('loading') {
        <div>Loading...</div>
      }
      @case ('error') {
        <div>Error occurred</div>
      }
      @default {
        <div>Ready</div>
      }
    }
  `
})
export class ModernSyntaxComponent {
  user = signal<User | null>(null);
  items = signal<Item[]>([]);
  status = signal<'loading' | 'error' | 'ready'>('ready');
}

// Experimenting with new features
@Component({
  selector: 'app-signals-demo',
  standalone: true,
  template: `
    <div>Count: {{count()}}</div>
    <div>Double: {{doubleCount()}}</div>
    <button (click)="increment()">+</button>
  `
})
export class SignalsDemoComponent {
  count = signal(0);
  doubleCount = computed(() => this.count() * 2);
  
  increment() {
    this.count.update(c => c + 1);
  }
  
  constructor() {
    // Effect for side effects
    effect(() => {
      console.log('Count changed:', this.count());
    });
  }
}

// Learning through migration
// Migrating from NgModules to standalone
// Before (NgModule approach)
@NgModule({
  declarations: [FeatureComponent],
  imports: [CommonModule, FormsModule],
  exports: [FeatureComponent]
})
export class FeatureModule {}

// After (Standalone approach)
@Component({
  selector: 'app-feature',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `<div>Standalone Feature</div>`
})
export class FeatureComponent {}
```

**Knowledge sharing practices:**
- Weekly team tech talks
- Internal blog posts
- Mentoring junior developers
- Code review discussions
- Architecture decision documentation

**Continuous learning schedule:**
- 30 minutes daily: Documentation/articles
- 2 hours weekly: Hands-on experimentation
- Monthly: Conference talks/webinars
- Quarterly: Major version migration testing

---

#### 4. Describe a Real Project Architecture You Built

**Project:** Enterprise HR Management System serving 50,000+ employees across multiple countries.

**Requirements:**
- Multi-tenant architecture
- Role-based access control
- Real-time notifications
- Offline capability
- Multiple languages/currencies
- High availability (99.9% uptime)

**Architecture decisions and implementation:**

```typescript
// 1. Modular Architecture
/*
src/
├── app/
│   ├── core/                    # Singleton services
│   │   ├── auth/
│   │   ├── api/
│   │   ├── interceptors/
│   │   └── guards/
│   ├── shared/                  # Reusable components
│   │   ├── components/
│   │   ├── pipes/
│   │   ├── directives/
│   │   └── utils/
│   ├── features/                # Feature modules
│   │   ├── employee-management/
│   │   ├── payroll/
│   │   ├── time-tracking/
│   │   ├── performance/
│   │   └── reports/
│   └── layout/                  # Shell components
*/

// 2. Multi-tenant Service
@Injectable({
  providedIn: 'root'
})
export class TenantService {
  private currentTenant = signal<Tenant | null>(null);
  
  setTenant(tenant: Tenant) {
    this.currentTenant.set(tenant);
    this.updateApiBaseUrl(tenant.apiEndpoint);
    this.loadTenantConfiguration(tenant.id);
  }
  
  getCurrentTenant(): Tenant | null {
    return this.currentTenant();
  }
  
  private updateApiBaseUrl(endpoint: string) {
    // Update HTTP interceptor base URL
    this.configService.setApiBaseUrl(endpoint);
  }
}

// 3. Role-based Access Control
@Injectable({
  providedIn: 'root'
})
export class RbacService {
  private userPermissions = signal<string[]>([]);
  
  hasPermission(permission: string): boolean {
    return this.userPermissions().includes(permission);
  }
  
  hasRole(role: string): boolean {
    return this.userPermissions().some(p => p.startsWith(`${role}:`));
  }
}

@Directive({
  selector: '[appHasPermission]'
})
export class HasPermissionDirective {
  @Input() set appHasPermission(permission: string) {
    const hasAccess = this.rbacService.hasPermission(permission);
    if (!hasAccess) {
      this.viewContainer.clear();
    }
  }
  
  constructor(
    private rbacService: RbacService,
    private viewContainer: ViewContainerRef
  ) {}
}

// 4. State Management with Signals
@Injectable({
  providedIn: 'root'
})
export class EmployeeStateService {
  // State signals
  private employees = signal<Employee[]>([]);
  private loading = signal(false);
  private selectedEmployee = signal<Employee | null>(null);
  
  // Computed values
  activeEmployees = computed(() => 
    this.employees().filter(emp => emp.status === 'active')
  );
  
  employeeCount = computed(() => this.employees().length);
  
  // Actions
  async loadEmployees() {
    this.loading.set(true);
    try {
      const employees = await this.apiService.getEmployees();
      this.employees.set(employees);
    } finally {
      this.loading.set(false);
    }
  }
  
  selectEmployee(employee: Employee) {
    this.selectedEmployee.set(employee);
  }
  
  // Getters for reactive access
  getEmployees = this.employees.asReadonly();
  getLoading = this.loading.asReadonly();
  getSelectedEmployee = this.selectedEmployee.asReadonly();
}

// 5. Real-time Notifications
@Injectable({
  providedIn: 'root'
})
export class NotificationService {
  private socket = io(environment.websocketUrl);
  private notifications = signal<Notification[]>([]);
  
  constructor() {
    this.socket.on('notification', (notification: Notification) => {
      this.notifications.update(notifications => 
        [notification, ...notifications].slice(0, 50) // Keep last 50
      );
      
      this.showToast(notification);
    });
  }
  
  getNotifications = this.notifications.asReadonly();
  
  private showToast(notification: Notification) {
    // Show browser notification if permission granted
    if (Notification.permission === 'granted') {
      new Notification(notification.title, {
        body: notification.message,
        icon: '/assets/icons/notification.png'
      });
    }
  }
}

// 6. Offline Capability
@Injectable({
  providedIn: 'root'
})
export class OfflineService {
  private isOnline = signal(navigator.onLine);
  private pendingRequests = signal<PendingRequest[]>([]);
  
  constructor() {
    window.addEventListener('online', () => {
      this.isOnline.set(true);
      this.processPendingRequests();
    });
    
    window.addEventListener('offline', () => {
      this.isOnline.set(false);
    });
  }
  
  queueRequest(request: PendingRequest) {
    if (!this.isOnline()) {
      this.pendingRequests.update(requests => [...requests, request]);
      return of(null); // Return cached data if available
    }
    return this.executeRequest(request);
  }
  
  private async processPendingRequests() {
    const requests = this.pendingRequests();
    this.pendingRequests.set([]);
    
    for (const request of requests) {
      try {
        await this.executeRequest(request).toPromise();
      } catch (error) {
        console.error('Failed to process pending request:', error);
      }
    }
  }
}

// 7. Internationalization
@Injectable({
  providedIn: 'root'
})
export class I18nService {
  private currentLocale = signal('en-US');
  private translations = signal<Record<string, string>>({});
  
  async setLocale(locale: string) {
    this.currentLocale.set(locale);
    const translations = await import(`../assets/i18n/${locale}.json`);
    this.translations.set(translations.default);
  }
  
  translate(key: string, params?: Record<string, any>): string {
    let translation = this.translations()[key] || key;
    
    if (params) {
      Object.keys(params).forEach(param => {
        translation = translation.replace(`{{${param}}}`, params[param]);
      });
    }
    
    return translation;
  }
}

// 8. Performance Monitoring
@Injectable({
  providedIn: 'root'
})
export class PerformanceMonitoringService {
  private metrics = signal<PerformanceMetric[]>([]);
  
  trackPageLoad(pageName: string) {
    const navigationEntry = performance.getEntriesByType('navigation')[0] as PerformanceNavigationTiming;
    
    const metric: PerformanceMetric = {
      page: pageName,
      loadTime: navigationEntry.loadEventEnd - navigationEntry.loadEventStart,
      domContentLoaded: navigationEntry.domContentLoadedEventEnd - navigationEntry.domContentLoadedEventStart,
      timestamp: Date.now()
    };
    
    this.metrics.update(metrics => [...metrics, metric]);
    this.sendToAnalytics(metric);
  }
  
  private sendToAnalytics(metric: PerformanceMetric) {
    // Send to analytics service
    console.log('Performance metric:', metric);
  }
}
```

**Key architectural decisions:**
- **Micro-frontend approach** with feature modules
- **Signal-based state management** for reactive UI
- **Multi-tenant architecture** with dynamic configuration
- **Progressive Web App** with offline capabilities
- **Real-time updates** via WebSocket connections
- **Comprehensive monitoring** and error tracking

**Results achieved:**
- 99.95% uptime over 2 years
- Sub-second page load times
- Support for 15 languages
- 50,000+ concurrent users
- Zero data loss during offline periods
- 40% reduction in support tickets due to intuitive UX

**Technologies used:**
- Angular 17 with standalone components
- Signals for state management
- Angular Material for UI components
- Socket.io for real-time features
- Service Worker for offline capability
- Docker for containerization
- Kubernetes for orchestration

---

## 📝 Summary

**Behavioral / Experience-Based** questions demonstrate real-world Angular expertise:

### Key Experiences Shared:
- **Complex Problem Solving**: Memory leak resolution with systematic cleanup patterns
- **Performance Optimization**: Comprehensive optimization strategy reducing load times by 75%
- **Continuous Learning**: Multi-channel approach to staying current with Angular evolution
- **Enterprise Architecture**: Large-scale HR system with advanced features and high availability

### Demonstrated Skills:
- **Problem Analysis**: Identifying root causes and implementing systematic solutions
- **Performance Engineering**: Using profiling tools and optimization techniques
- **Architecture Design**: Building scalable, maintainable enterprise applications
- **Team Leadership**: Knowledge sharing and mentoring practices
- **Technology Adoption**: Staying current with Angular's rapid evolution

### Best Practices Highlighted:
- **Systematic approach** to problem-solving and optimization
- **Continuous learning** through multiple channels and hands-on practice
- **Knowledge sharing** within teams and community
- **Performance monitoring** and proactive optimization
- **Scalable architecture** patterns for enterprise applications

These examples demonstrate the depth of experience and practical knowledge that senior Angular developers bring to complex projects and challenging technical problems.
# Angular – Practical / Situation-Based Questions

## 1. You need to load data from an API and display it in a component. How do you handle the asynchronous call and errors?

I use a **service with HttpClient** to fetch data and handle it with RxJS operators like catchError. In the component, I subscribe to the Observable and manage loading, error, and success states.

```typescript
// Service
@Injectable({ providedIn: 'root' })
export class UserService {
  constructor(private http: HttpClient) {}

  getUsers(): Observable<User[]> {
    return this.http.get<User[]>('/api/users').pipe(
      catchError(error => {
        console.error('Error:', error);
        return throwError(() => error);
      })
    );
  }
}

// Component
@Component({
  selector: 'app-users',
  template: `
    <div *ngIf="loading">Loading...</div>
    <div *ngIf="error">Error: {{ error }}</div>
    <div *ngFor="let user of users">{{ user.name }}</div>
  `
})
export class UsersComponent implements OnInit {
  users: User[] = [];
  loading = true;
  error = '';

  constructor(private userService: UserService) {}

  ngOnInit() {
    this.userService.getUsers().subscribe({
      next: (data) => {
        this.users = data;
        this.loading = false;
      },
      error: (err) => {
        this.error = err.message;
        this.loading = false;
      }
    });
  }
}
```

**Using async pipe (cleaner approach):**
```typescript
@Component({
  template: `
    <div *ngIf="users$ | async as users; else loading">
      <div *ngFor="let user of users">{{ user.name }}</div>
    </div>
    <ng-template #loading>Loading...</ng-template>
  `
})
export class UsersComponent {
  users$ = this.userService.getUsers();
  
  constructor(private userService: UserService) {}
}
```

**Key points:**
- Use HttpClient for API calls
- catchError for error handling
- async pipe automatically subscribes/unsubscribes

---

## 2. How would you optimize performance for a component that renders a large list of items?

I use **trackBy** with ngFor to help Angular identify which items changed, and **OnPush change detection** to reduce unnecessary checks. For very large lists, I use **virtual scrolling** from Angular CDK.

```typescript
// Component
@Component({
  selector: 'app-list',
  template: `
    <div *ngFor="let item of items; trackBy: trackById">
      {{ item.name }}
    </div>
  `,
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class ListComponent {
  @Input() items: Item[] = [];

  trackById(index: number, item: Item) {
    return item.id;
  }
}
```

**Virtual scrolling for very large lists:**
```typescript
import { ScrollingModule } from '@angular/cdk/scrolling';

@Component({
  template: `
    <cdk-virtual-scroll-viewport itemSize="50" style="height: 500px">
      <div *cdkVirtualFor="let item of items">
        {{ item.name }}
      </div>
    </cdk-virtual-scroll-viewport>
  `,
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class VirtualListComponent {
  items: Item[] = [];
}
```

**Key optimizations:**
- trackBy prevents unnecessary DOM updates
- OnPush change detection reduces checks
- Virtual scrolling renders only visible items

---

## 3. You have a form with multiple steps and validations. How do you manage the form state across steps?

I use **Reactive Forms** with a single FormGroup that contains all steps' data. I track the current step separately and show/hide sections based on it. This keeps all form data in one place.

```typescript
@Component({
  selector: 'app-multi-step-form',
  template: `
    <form [formGroup]="form" (ngSubmit)="onSubmit()">
      <div *ngIf="currentStep === 1">
        <input formControlName="name" placeholder="Name">
        <div *ngIf="form.get('name')?.invalid && form.get('name')?.touched">
          Name is required
        </div>
      </div>

      <div *ngIf="currentStep === 2">
        <input formControlName="email" placeholder="Email">
        <div *ngIf="form.get('email')?.invalid && form.get('email')?.touched">
          Valid email required
        </div>
      </div>

      <button type="button" (click)="prevStep()" [disabled]="currentStep === 1">
        Back
      </button>
      <button type="button" (click)="nextStep()" *ngIf="currentStep < 2">
        Next
      </button>
      <button type="submit" *ngIf="currentStep === 2">Submit</button>
    </form>
  `
})
export class MultiStepFormComponent {
  currentStep = 1;
  
  form = this.fb.group({
    name: ['', Validators.required],
    email: ['', [Validators.required, Validators.email]],
    address: ['']
  });

  constructor(private fb: FormBuilder) {}

  nextStep() {
    if (this.currentStep === 1 && this.form.get('name')?.valid) {
      this.currentStep++;
    }
  }

  prevStep() {
    if (this.currentStep > 1) this.currentStep--;
  }

  onSubmit() {
    if (this.form.valid) {
      console.log(this.form.value);
    }
  }
}
```

**Key points:**
- Single FormGroup holds all step data
- Validate specific fields before moving to next step
- Form state persists when navigating between steps

---

## 4. A child component needs to communicate with a deeply nested parent component. How do you handle this in Angular?

I use a **shared service** with a Subject or BehaviorSubject to communicate between components without prop drilling. Any component can subscribe to updates or emit events through the service.

```typescript
// Shared Service
@Injectable({ providedIn: 'root' })
export class MessageService {
  private messageSubject = new Subject<string>();
  message$ = this.messageSubject.asObservable();

  sendMessage(message: string) {
    this.messageSubject.next(message);
  }
}

// Deep Child Component
@Component({
  selector: 'app-deep-child',
  template: `<button (click)="notify()">Notify Parent</button>`
})
export class DeepChildComponent {
  constructor(private messageService: MessageService) {}

  notify() {
    this.messageService.sendMessage('Hello from deep child!');
  }
}

// Parent Component
@Component({
  selector: 'app-parent',
  template: `<div>Message: {{ message }}</div>`
})
export class ParentComponent implements OnInit, OnDestroy {
  message = '';
  private subscription!: Subscription;

  constructor(private messageService: MessageService) {}

  ngOnInit() {
    this.subscription = this.messageService.message$.subscribe(
      msg => this.message = msg
    );
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }
}
```

**Alternative with BehaviorSubject (holds current value):**
```typescript
@Injectable({ providedIn: 'root' })
export class StateService {
  private state = new BehaviorSubject<any>(null);
  state$ = this.state.asObservable();

  updateState(data: any) {
    this.state.next(data);
  }
}
```

**Key points:**
- Service acts as communication bridge
- Subject for events, BehaviorSubject for state
- Always unsubscribe to prevent memory leaks


---

## 5. How do you implement conditional rendering for loading, error, and success states in Angular templates?

I use **ngIf** with else blocks or the **async pipe** to conditionally render different states. The async pipe automatically handles subscription and displays loading/error/success states cleanly.

```typescript
// Component
@Component({
  selector: 'app-data',
  template: `
    <div *ngIf="loading">Loading...</div>
    <div *ngIf="error" class="error">Error: {{ error }}</div>
    <div *ngIf="!loading && !error && data">
      <div *ngFor="let item of data">{{ item.name }}</div>
    </div>
  `
})
export class DataComponent implements OnInit {
  data: any[] | null = null;
  loading = true;
  error = '';

  constructor(private service: DataService) {}

  ngOnInit() {
    this.service.getData().subscribe({
      next: (result) => {
        this.data = result;
        this.loading = false;
      },
      error: (err) => {
        this.error = err.message;
        this.loading = false;
      }
    });
  }
}
```

**Using async pipe with ng-template (cleaner):**
```typescript
@Component({
  template: `
    <ng-container *ngIf="data$ | async as data; else loading">
      <div *ngFor="let item of data">{{ item.name }}</div>
    </ng-container>

    <ng-template #loading>
      <div>Loading...</div>
    </ng-template>
  `
})
export class DataComponent {
  data$ = this.service.getData().pipe(
    catchError(err => {
      console.error(err);
      return of([]);
    })
  );

  constructor(private service: DataService) {}
}
```

**Key points:**
- ngIf with else for conditional rendering
- async pipe handles subscription automatically
- Use ng-template for cleaner fallback UI

---

## 6. Your Angular app is slow due to frequent change detection cycles. How do you optimize it?

I use **OnPush change detection strategy** to run change detection only when inputs change or events fire. I also use **trackBy** with ngFor and avoid function calls in templates.

```typescript
@Component({
  selector: 'app-optimized',
  template: `
    <div *ngFor="let item of items; trackBy: trackById">
      {{ item.name }}
    </div>
  `,
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class OptimizedComponent {
  @Input() items: Item[] = [];

  trackById(index: number, item: Item) {
    return item.id;
  }
}
```

**Detach change detection for specific components:**
```typescript
@Component({
  selector: 'app-manual-detection',
  template: `<div>{{ data }}</div>`
})
export class ManualDetectionComponent implements OnInit {
  data = '';

  constructor(private cdr: ChangeDetectorRef) {
    this.cdr.detach(); // Detach from change detection
  }

  updateData(newData: string) {
    this.data = newData;
    this.cdr.detectChanges(); // Manually trigger
  }
}
```

**Avoid in templates:**
```typescript
// BAD - function called on every change detection
<div>{{ calculateTotal() }}</div>

// GOOD - use pipe or compute once
<div>{{ total }}</div>
```

**Key optimizations:**
- OnPush change detection strategy
- trackBy with ngFor
- Avoid function calls in templates
- Use pure pipes for transformations
- Detach change detection when needed

---

## 7. You have a service that provides shared data across multiple components. How do you ensure it stays updated correctly?

I use **BehaviorSubject** in the service to hold the current state and emit updates. Components subscribe to the Observable, and any component can update the shared data through service methods.

```typescript
// Shared Service
@Injectable({ providedIn: 'root' })
export class DataService {
  private dataSubject = new BehaviorSubject<User[]>([]);
  data$ = this.dataSubject.asObservable();

  updateData(newData: User[]) {
    this.dataSubject.next(newData);
  }

  addItem(item: User) {
    const current = this.dataSubject.value;
    this.dataSubject.next([...current, item]);
  }
}

// Component 1 - Displays data
@Component({
  selector: 'app-list',
  template: `
    <div *ngFor="let user of users$ | async">{{ user.name }}</div>
  `
})
export class ListComponent {
  users$ = this.dataService.data$;

  constructor(private dataService: DataService) {}
}

// Component 2 - Updates data
@Component({
  selector: 'app-form',
  template: `<button (click)="add()">Add User</button>`
})
export class FormComponent {
  constructor(private dataService: DataService) {}

  add() {
    this.dataService.addItem({ id: 1, name: 'John' });
  }
}
```

**Key points:**
- BehaviorSubject holds current value
- All subscribers get updates automatically
- Service methods control state changes
- Use async pipe to auto-subscribe/unsubscribe

---

## 8. How would you handle lazy loading of modules to improve app startup time?

I use **loadChildren** in the routing configuration to lazy load feature modules. This splits the bundle and loads modules only when the user navigates to that route.

```typescript
// app-routing.module.ts
const routes: Routes = [
  { path: '', component: HomeComponent },
  {
    path: 'admin',
    loadChildren: () => import('./admin/admin.module').then(m => m.AdminModule)
  },
  {
    path: 'dashboard',
    loadChildren: () => import('./dashboard/dashboard.module').then(m => m.DashboardModule)
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
```

**Feature module with its own routing:**
```typescript
// admin/admin-routing.module.ts
const routes: Routes = [
  { path: '', component: AdminComponent },
  { path: 'users', component: UsersComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule {}

// admin/admin.module.ts
@NgModule({
  declarations: [AdminComponent, UsersComponent],
  imports: [CommonModule, AdminRoutingModule]
})
export class AdminModule {}
```

**Preloading strategy for better UX:**
```typescript
import { PreloadAllModules } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forRoot(routes, {
      preloadingStrategy: PreloadAllModules // Preload in background
    })
  ]
})
export class AppRoutingModule {}
```

**Key points:**
- loadChildren lazy loads modules on demand
- Reduces initial bundle size significantly
- Use PreloadAllModules to load in background after initial load
- Each feature module has its own routing


---

## 9. How do you implement authentication with JWT in an Angular application?

I use an **HTTP Interceptor** to automatically attach the JWT token to requests and an **AuthService** to handle login/logout. I store the token in localStorage and use a guard to protect routes.

```typescript
// Auth Service
@Injectable({ providedIn: 'root' })
export class AuthService {
  private tokenKey = 'auth_token';

  constructor(private http: HttpClient) {}

  login(credentials: any): Observable<any> {
    return this.http.post('/api/login', credentials).pipe(
      tap((response: any) => {
        localStorage.setItem(this.tokenKey, response.token);
      })
    );
  }

  logout() {
    localStorage.removeItem(this.tokenKey);
  }

  getToken(): string | null {
    return localStorage.getItem(this.tokenKey);
  }

  isAuthenticated(): boolean {
    return !!this.getToken();
  }
}

// HTTP Interceptor
@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private authService: AuthService) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = this.authService.getToken();
    
    if (token) {
      req = req.clone({
        setHeaders: { Authorization: `Bearer ${token}` }
      });
    }
    
    return next.handle(req);
  }
}

// Auth Guard
@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate {
  constructor(private authService: AuthService, private router: Router) {}

  canActivate(): boolean {
    if (this.authService.isAuthenticated()) {
      return true;
    }
    this.router.navigate(['/login']);
    return false;
  }
}

// App Module
@NgModule({
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }
  ]
})
export class AppModule {}

// Routing
const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'dashboard', component: DashboardComponent, canActivate: [AuthGuard] }
];
```

**Key points:**
- Interceptor adds token to all requests automatically
- Guard protects routes from unauthorized access
- Store token in localStorage for persistence

---

## 10. How do you decide when to use Reactive Forms vs Template-Driven Forms in Angular?

I use **Reactive Forms** for complex forms with dynamic fields, custom validation, or when I need programmatic control. I use **Template-Driven Forms** for simple forms with basic validation.

**Use Reactive Forms when:**
- Complex validation logic
- Dynamic form fields
- Need to test form logic
- Cross-field validation
- Programmatic form manipulation

**Use Template-Driven Forms when:**
- Simple forms with basic validation
- Quick prototypes
- Minimal logic needed

```typescript
// Reactive Forms - Complex, testable
@Component({
  selector: 'app-reactive-form',
  template: `
    <form [formGroup]="form" (ngSubmit)="onSubmit()">
      <input formControlName="email">
      <div *ngIf="form.get('email')?.invalid && form.get('email')?.touched">
        Invalid email
      </div>
      <input formControlName="password">
      <button type="submit" [disabled]="form.invalid">Submit</button>
    </form>
  `
})
export class ReactiveFormComponent {
  form = this.fb.group({
    email: ['', [Validators.required, Validators.email]],
    password: ['', [Validators.required, Validators.minLength(6)]]
  });

  constructor(private fb: FormBuilder) {}

  onSubmit() {
    if (this.form.valid) {
      console.log(this.form.value);
    }
  }
}

// Template-Driven - Simple, quick
@Component({
  selector: 'app-template-form',
  template: `
    <form #f="ngForm" (ngSubmit)="onSubmit(f)">
      <input name="email" [(ngModel)]="user.email" required email>
      <input name="password" [(ngModel)]="user.password" required>
      <button type="submit" [disabled]="f.invalid">Submit</button>
    </form>
  `
})
export class TemplateFormComponent {
  user = { email: '', password: '' };

  onSubmit(form: NgForm) {
    console.log(form.value);
  }
}
```

**Key decision factors:**
- Reactive: Better for complex, dynamic, testable forms
- Template-Driven: Better for simple, static forms

---

## 11. How would you handle debouncing or throttling user input (e.g., search box) to avoid excessive API calls?

I use **debounceTime** from RxJS with a FormControl or Subject to delay API calls until the user stops typing. This reduces unnecessary requests.

```typescript
@Component({
  selector: 'app-search',
  template: `
    <input [formControl]="searchControl" placeholder="Search...">
    <div *ngFor="let result of results">{{ result.name }}</div>
  `
})
export class SearchComponent implements OnInit {
  searchControl = new FormControl('');
  results: any[] = [];

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.searchControl.valueChanges.pipe(
      debounceTime(500),
      distinctUntilChanged(),
      switchMap(query => 
        query ? this.http.get(`/api/search?q=${query}`) : of([])
      )
    ).subscribe(results => this.results = results);
  }
}
```

**With Subject for more control:**
```typescript
@Component({
  selector: 'app-search',
  template: `<input (input)="onSearch($event)" placeholder="Search...">`
})
export class SearchComponent implements OnInit, OnDestroy {
  private searchSubject = new Subject<string>();
  private subscription!: Subscription;

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.subscription = this.searchSubject.pipe(
      debounceTime(500),
      distinctUntilChanged(),
      switchMap(term => this.http.get(`/api/search?q=${term}`))
    ).subscribe(results => console.log(results));
  }

  onSearch(event: any) {
    this.searchSubject.next(event.target.value);
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }
}
```

**Key operators:**
- **debounceTime(500)**: Wait 500ms after user stops typing
- **distinctUntilChanged()**: Only emit if value changed
- **switchMap()**: Cancel previous request if new one starts

---

## 12. Your component needs to subscribe to multiple Observables and perform actions when all emit data. How do you manage it?

I use **combineLatest** or **forkJoin** from RxJS. Use **combineLatest** when you need the latest values from all streams, or **forkJoin** when you need all to complete once.

```typescript
// combineLatest - emits when any Observable emits
@Component({
  selector: 'app-combined',
  template: `
    <div *ngIf="data">
      Users: {{ data.users.length }}, 
      Posts: {{ data.posts.length }}
    </div>
  `
})
export class CombinedComponent implements OnInit {
  data: any;

  constructor(
    private userService: UserService,
    private postService: PostService
  ) {}

  ngOnInit() {
    combineLatest([
      this.userService.getUsers(),
      this.postService.getPosts()
    ]).subscribe(([users, posts]) => {
      this.data = { users, posts };
    });
  }
}

// forkJoin - waits for all to complete (like Promise.all)
@Component({
  selector: 'app-fork',
  template: `<div>{{ message }}</div>`
})
export class ForkComponent implements OnInit {
  message = '';

  constructor(private http: HttpClient) {}

  ngOnInit() {
    forkJoin({
      users: this.http.get('/api/users'),
      posts: this.http.get('/api/posts'),
      comments: this.http.get('/api/comments')
    }).subscribe(({ users, posts, comments }) => {
      this.message = `Loaded ${users.length} users`;
    });
  }
}
```

**With async pipe (cleaner):**
```typescript
@Component({
  template: `
    <div *ngIf="data$ | async as data">
      Users: {{ data.users.length }}, Posts: {{ data.posts.length }}
    </div>
  `
})
export class AsyncComponent {
  data$ = combineLatest([
    this.userService.getUsers(),
    this.postService.getPosts()
  ]).pipe(
    map(([users, posts]) => ({ users, posts }))
  );

  constructor(
    private userService: UserService,
    private postService: PostService
  ) {}
}
```

**Key differences:**
- **combineLatest**: Emits whenever any Observable emits (for streams)
- **forkJoin**: Emits once when all complete (for HTTP requests)
- **zip**: Emits when all emit at same index


---

## 13. How would you implement role-based access control in Angular routes and components?

I use a **role-based guard** that checks user roles before allowing route access, and **structural directives** to show/hide elements based on roles in components.

```typescript
// Auth Service with roles
@Injectable({ providedIn: 'root' })
export class AuthService {
  private currentUser: any = null;

  login(user: any) {
    this.currentUser = user;
    localStorage.setItem('user', JSON.stringify(user));
  }

  getUser() {
    if (!this.currentUser) {
      const stored = localStorage.getItem('user');
      this.currentUser = stored ? JSON.parse(stored) : null;
    }
    return this.currentUser;
  }

  hasRole(role: string): boolean {
    const user = this.getUser();
    return user?.roles?.includes(role) || false;
  }
}

// Role Guard
@Injectable({ providedIn: 'root' })
export class RoleGuard implements CanActivate {
  constructor(private authService: AuthService, private router: Router) {}

  canActivate(route: ActivatedRouteSnapshot): boolean {
    const requiredRoles = route.data['roles'] as string[];
    
    if (requiredRoles.some(role => this.authService.hasRole(role))) {
      return true;
    }
    
    this.router.navigate(['/unauthorized']);
    return false;
  }
}

// Routing with role protection
const routes: Routes = [
  { path: 'admin', component: AdminComponent, canActivate: [RoleGuard], data: { roles: ['admin'] } },
  { path: 'user', component: UserComponent, canActivate: [RoleGuard], data: { roles: ['user', 'admin'] } }
];

// Directive for template-level control
@Directive({ selector: '[hasRole]' })
export class HasRoleDirective implements OnInit {
  @Input() hasRole!: string;

  constructor(
    private templateRef: TemplateRef<any>,
    private viewContainer: ViewContainerRef,
    private authService: AuthService
  ) {}

  ngOnInit() {
    if (this.authService.hasRole(this.hasRole)) {
      this.viewContainer.createEmbeddedView(this.templateRef);
    } else {
      this.viewContainer.clear();
    }
  }
}

// Usage in template
@Component({
  template: `
    <button *hasRole="'admin'">Delete User</button>
    <button *hasRole="'user'">Edit Profile</button>
  `
})
export class DashboardComponent {}
```

**Key points:**
- Guard checks roles before route activation
- Custom directive controls element visibility
- Store roles with user data in localStorage or token

---

## 14. A third-party library directly manipulates the DOM. How do you safely integrate it with Angular?

I use **ElementRef** to get a DOM reference and initialize the library in **ngAfterViewInit**. I clean up in **ngOnDestroy** to prevent memory leaks and avoid Angular's change detection conflicts.

```typescript
import * as Chart from 'chart.js';

@Component({
  selector: 'app-chart',
  template: `<canvas #chartCanvas></canvas>`
})
export class ChartComponent implements AfterViewInit, OnDestroy {
  @ViewChild('chartCanvas', { static: false }) canvasRef!: ElementRef;
  private chart: any;

  ngAfterViewInit() {
    const ctx = this.canvasRef.nativeElement.getContext('2d');
    this.chart = new Chart(ctx, {
      type: 'bar',
      data: {
        labels: ['Jan', 'Feb', 'Mar'],
        datasets: [{ data: [10, 20, 30] }]
      }
    });
  }

  updateChart(newData: number[]) {
    if (this.chart) {
      this.chart.data.datasets[0].data = newData;
      this.chart.update();
    }
  }

  ngOnDestroy() {
    if (this.chart) {
      this.chart.destroy();
    }
  }
}
```

**With Renderer2 for safer DOM manipulation:**
```typescript
@Component({
  selector: 'app-datepicker',
  template: `<div #datepicker></div>`
})
export class DatepickerComponent implements AfterViewInit, OnDestroy {
  @ViewChild('datepicker') pickerRef!: ElementRef;
  private picker: any;

  constructor(private renderer: Renderer2) {}

  ngAfterViewInit() {
    // Initialize third-party library
    this.picker = $(this.pickerRef.nativeElement).datepicker({
      onSelect: (date: string) => {
        console.log('Selected:', date);
      }
    });
  }

  ngOnDestroy() {
    if (this.picker) {
      $(this.pickerRef.nativeElement).datepicker('destroy');
    }
  }
}
```

**Key points:**
- Use ViewChild to get DOM reference
- Initialize in ngAfterViewInit (after view is ready)
- Always clean up in ngOnDestroy
- Avoid direct DOM manipulation when possible

---

## 15. How do you prevent memory leaks when subscribing to Observables in components?

I **unsubscribe in ngOnDestroy** or use the **async pipe** which automatically handles subscriptions. I can also use **takeUntil** with a Subject to unsubscribe from multiple Observables at once.

```typescript
// Method 1: Manual unsubscribe
@Component({
  selector: 'app-manual',
  template: `<div>{{ data }}</div>`
})
export class ManualComponent implements OnInit, OnDestroy {
  data = '';
  private subscription!: Subscription;

  constructor(private service: DataService) {}

  ngOnInit() {
    this.subscription = this.service.getData().subscribe(
      data => this.data = data
    );
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }
}

// Method 2: takeUntil pattern (multiple subscriptions)
@Component({
  selector: 'app-takeuntil',
  template: `<div>{{ data }}</div>`
})
export class TakeUntilComponent implements OnInit, OnDestroy {
  data = '';
  private destroy$ = new Subject<void>();

  constructor(private service: DataService) {}

  ngOnInit() {
    this.service.getData()
      .pipe(takeUntil(this.destroy$))
      .subscribe(data => this.data = data);

    this.service.getOtherData()
      .pipe(takeUntil(this.destroy$))
      .subscribe(data => console.log(data));
  }

  ngOnDestroy() {
    this.destroy$.next();
    this.destroy$.complete();
  }
}

// Method 3: async pipe (best practice)
@Component({
  selector: 'app-async',
  template: `<div>{{ data$ | async }}</div>`
})
export class AsyncComponent {
  data$ = this.service.getData();

  constructor(private service: DataService) {}
}
```

**Key points:**
- async pipe automatically unsubscribes
- takeUntil pattern for multiple subscriptions
- Always unsubscribe in ngOnDestroy
- HTTP requests complete automatically (but still good practice)

---

## 16. How do you implement dynamic forms where fields change based on previous user input?

I use **Reactive Forms** with **valueChanges** to listen for changes and dynamically add/remove form controls based on user input.

```typescript
@Component({
  selector: 'app-dynamic-form',
  template: `
    <form [formGroup]="form">
      <select formControlName="country">
        <option value="">Select Country</option>
        <option value="USA">USA</option>
        <option value="Canada">Canada</option>
      </select>

      <select formControlName="state" *ngIf="states.length > 0">
        <option *ngFor="let state of states" [value]="state">{{ state }}</option>
      </select>

      <label>
        <input type="checkbox" formControlName="hasLicense">
        Do you have a license?
      </label>

      <input 
        *ngIf="form.get('hasLicense')?.value" 
        formControlName="licenseNumber" 
        placeholder="License Number"
      >

      <button [disabled]="form.invalid">Submit</button>
    </form>
  `
})
export class DynamicFormComponent implements OnInit {
  form = this.fb.group({
    country: ['', Validators.required],
    state: [''],
    hasLicense: [false],
    licenseNumber: ['']
  });

  states: string[] = [];

  constructor(private fb: FormBuilder) {}

  ngOnInit() {
    // Watch country changes
    this.form.get('country')?.valueChanges.subscribe(country => {
      this.states = this.getStates(country);
      this.form.patchValue({ state: '' });
    });

    // Watch license checkbox
    this.form.get('hasLicense')?.valueChanges.subscribe(hasLicense => {
      const licenseControl = this.form.get('licenseNumber');
      if (hasLicense) {
        licenseControl?.setValidators([Validators.required]);
      } else {
        licenseControl?.clearValidators();
        licenseControl?.setValue('');
      }
      licenseControl?.updateValueAndValidity();
    });
  }

  getStates(country: string): string[] {
    const stateMap: any = {
      'USA': ['California', 'Texas', 'New York'],
      'Canada': ['Ontario', 'Quebec', 'Alberta']
    };
    return stateMap[country] || [];
  }
}
```

**Dynamic FormArray for repeating fields:**
```typescript
@Component({
  template: `
    <form [formGroup]="form">
      <div formArrayName="items">
        <div *ngFor="let item of items.controls; let i = index" [formGroupName]="i">
          <input formControlName="name">
          <button (click)="removeItem(i)">Remove</button>
        </div>
      </div>
      <button (click)="addItem()">Add Item</button>
    </form>
  `
})
export class DynamicArrayComponent {
  form = this.fb.group({
    items: this.fb.array([])
  });

  get items() {
    return this.form.get('items') as FormArray;
  }

  constructor(private fb: FormBuilder) {}

  addItem() {
    this.items.push(this.fb.group({
      name: ['', Validators.required]
    }));
  }

  removeItem(index: number) {
    this.items.removeAt(index);
  }
}
```

**Key points:**
- Use valueChanges to react to input changes
- Add/remove validators dynamically
- FormArray for repeating dynamic fields
- patchValue to update dependent fields


---

## 17. You need to handle global error handling for HTTP requests in your Angular app. How do you do it?

I use an **HTTP Interceptor** to catch all HTTP errors globally and an **ErrorHandler** for application-level errors. This centralizes error handling and provides consistent user feedback.

```typescript
// HTTP Error Interceptor
@Injectable()
export class ErrorInterceptor implements HttpInterceptor {
  constructor(private router: Router) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(req).pipe(
      catchError((error: HttpErrorResponse) => {
        let errorMessage = '';

        if (error.error instanceof ErrorEvent) {
          // Client-side error
          errorMessage = `Error: ${error.error.message}`;
        } else {
          // Server-side error
          errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
          
          if (error.status === 401) {
            this.router.navigate(['/login']);
          }
        }

        console.error(errorMessage);
        return throwError(() => new Error(errorMessage));
      })
    );
  }
}

// Global Error Handler
@Injectable()
export class GlobalErrorHandler implements ErrorHandler {
  handleError(error: any) {
    console.error('Global error:', error);
    // Log to external service, show toast, etc.
  }
}

// App Module
@NgModule({
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },
    { provide: ErrorHandler, useClass: GlobalErrorHandler }
  ]
})
export class AppModule {}
```

**With notification service:**
```typescript
@Injectable()
export class ErrorInterceptor implements HttpInterceptor {
  constructor(private notificationService: NotificationService) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(req).pipe(
      catchError((error: HttpErrorResponse) => {
        const message = error.error?.message || 'An error occurred';
        this.notificationService.showError(message);
        return throwError(() => error);
      })
    );
  }
}
```

**Key points:**
- Interceptor catches all HTTP errors
- Handle different error types (401, 404, 500)
- Centralized error logging and user notifications

---

## 18. How do you manage state across multiple unrelated components in Angular?

I use a **state service with BehaviorSubject** for simple state or **NgRx** for complex state management. The service pattern works well for most applications without adding extra complexity.

```typescript
// State Service
@Injectable({ providedIn: 'root' })
export class StateService {
  private userState = new BehaviorSubject<User | null>(null);
  user$ = this.userState.asObservable();

  private cartState = new BehaviorSubject<CartItem[]>([]);
  cart$ = this.cartState.asObservable();

  updateUser(user: User) {
    this.userState.next(user);
  }

  addToCart(item: CartItem) {
    const current = this.cartState.value;
    this.cartState.next([...current, item]);
  }

  clearCart() {
    this.cartState.next([]);
  }
}

// Component 1
@Component({
  selector: 'app-header',
  template: `
    <div>User: {{ user$ | async | json }}</div>
    <div>Cart: {{ (cart$ | async)?.length }} items</div>
  `
})
export class HeaderComponent {
  user$ = this.stateService.user$;
  cart$ = this.stateService.cart$;

  constructor(private stateService: StateService) {}
}

// Component 2
@Component({
  selector: 'app-product',
  template: `<button (click)="addToCart()">Add to Cart</button>`
})
export class ProductComponent {
  constructor(private stateService: StateService) {}

  addToCart() {
    this.stateService.addToCart({ id: 1, name: 'Product' });
  }
}
```

**With NgRx for complex state:**
```typescript
// State
export interface AppState {
  user: User | null;
  cart: CartItem[];
}

// Actions
export const updateUser = createAction('[User] Update', props<{ user: User }>());
export const addToCart = createAction('[Cart] Add Item', props<{ item: CartItem }>());

// Reducer
export const userReducer = createReducer(
  null,
  on(updateUser, (state, { user }) => user)
);

// Component
@Component({
  template: `<div>{{ user$ | async | json }}</div>`
})
export class Component {
  user$ = this.store.select(state => state.user);

  constructor(private store: Store<AppState>) {}

  update() {
    this.store.dispatch(updateUser({ user: { id: 1, name: 'John' } }));
  }
}
```

**Key points:**
- BehaviorSubject for simple shared state
- NgRx for complex state with many actions
- Use async pipe to auto-subscribe

---

## 19. Your app has several frequently updated UI elements. How do you optimize change detection to avoid unnecessary re-renders?

I use **OnPush change detection strategy** to run change detection only when inputs change or events fire. I also use **ChangeDetectorRef** to manually control when to check for changes.

```typescript
@Component({
  selector: 'app-optimized',
  template: `
    <div>{{ data.value }}</div>
    <div *ngFor="let item of items; trackBy: trackById">{{ item.name }}</div>
  `,
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class OptimizedComponent {
  @Input() data: any;
  @Input() items: Item[] = [];

  trackById(index: number, item: Item) {
    return item.id;
  }
}
```

**Manual change detection control:**
```typescript
@Component({
  selector: 'app-manual',
  template: `<div>{{ counter }}</div>`,
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class ManualComponent implements OnInit {
  counter = 0;

  constructor(private cdr: ChangeDetectorRef) {}

  ngOnInit() {
    setInterval(() => {
      this.counter++;
      this.cdr.markForCheck(); // Trigger change detection
    }, 1000);
  }
}
```

**Detach and reattach:**
```typescript
@Component({
  selector: 'app-detached',
  template: `<div>{{ value }}</div>`
})
export class DetachedComponent implements OnInit {
  value = 0;

  constructor(private cdr: ChangeDetectorRef) {}

  ngOnInit() {
    this.cdr.detach(); // Stop automatic change detection

    setInterval(() => {
      this.value++;
      this.cdr.detectChanges(); // Manual check
    }, 1000);
  }
}
```

**Key optimizations:**
- OnPush strategy reduces checks significantly
- trackBy prevents unnecessary DOM updates
- markForCheck() schedules check for next cycle
- detectChanges() runs check immediately
- Detach for complete manual control

---

## 20. How would you migrate a large AngularJS application to Angular while minimizing downtime and errors?

I use the **ngUpgrade** library to run AngularJS and Angular side-by-side, migrating components incrementally. This allows gradual migration without rewriting everything at once.

```typescript
// Step 1: Setup hybrid app
import { UpgradeModule } from '@angular/upgrade/static';

@NgModule({
  imports: [
    BrowserModule,
    UpgradeModule
  ]
})
export class AppModule {
  constructor(private upgrade: UpgradeModule) {}

  ngDoBootstrap() {
    this.upgrade.bootstrap(document.body, ['angularjsApp'], { strictDi: true });
  }
}

// Step 2: Downgrade Angular component for use in AngularJS
@Component({
  selector: 'app-new-component',
  template: `<div>New Angular Component</div>`
})
export class NewComponent {}

// Register downgraded component
angular.module('angularjsApp')
  .directive('appNewComponent', downgradeComponent({ component: NewComponent }));

// Use in AngularJS template
// <app-new-component></app-new-component>

// Step 3: Upgrade AngularJS component for use in Angular
angular.module('angularjsApp')
  .component('oldComponent', {
    template: '<div>Old AngularJS Component</div>'
  });

// In Angular module
@NgModule({
  declarations: [
    NewComponent
  ],
  entryComponents: [NewComponent],
  providers: [
    {
      provide: 'oldComponent',
      useFactory: (i: any) => i.get('oldComponent'),
      deps: ['$injector']
    }
  ]
})
export class AppModule {}
```

**Migration strategy:**
```typescript
// 1. Upgrade AngularJS services to Angular
@Injectable()
export class UserService {
  constructor(@Inject('$http') private $http: any) {}

  getUsers() {
    return this.$http.get('/api/users');
  }
}

// 2. Migrate routes incrementally
const routes: Routes = [
  { path: 'new-feature', component: NewFeatureComponent },
  { path: 'old-feature', component: UpgradedOldComponent }
];

// 3. Share services between AngularJS and Angular
angular.module('app')
  .factory('userService', downgradeInjectable(UserService));
```

**Migration steps:**
1. **Setup hybrid app** with ngUpgrade
2. **Migrate services first** (shared logic)
3. **Migrate components incrementally** (leaf components first)
4. **Update routing** gradually
5. **Remove AngularJS** when all migrated
6. **Test thoroughly** at each step

**Key points:**
- Use ngUpgrade for gradual migration
- Downgrade Angular components for AngularJS
- Upgrade AngularJS components for Angular
- Migrate bottom-up (leaf components first)
- Keep both frameworks running during transition

---

## Summary

These 20 questions cover essential Angular patterns:
- **API & Data**: HTTP calls, error handling, Observables
- **Performance**: Change detection, lazy loading, virtualization
- **Forms**: Reactive vs Template-Driven, multi-step, dynamic fields
- **State Management**: Services, BehaviorSubject, NgRx
- **Security**: JWT auth, role-based access control
- **RxJS**: debounceTime, combineLatest, forkJoin, takeUntil
- **Integration**: Third-party libraries, DOM manipulation
- **Best Practices**: Memory leak prevention, optimization strategies
- **Migration**: AngularJS to Angular upgrade path

Master these patterns and you'll be well-prepared for Angular interviews and real-world development challenges!

# Angular Interview Questions - Answers (Questions 1-8)
## Data Fetching & HTTP

---

## 1. You need to load data from an API and display it in a component. How do you handle the asynchronous call and errors?

**Approach:**
- Use HttpClient in a service
- Subscribe in component
- Handle loading, success, and error states

**Implementation:**

```typescript
// data.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class DataService {
  constructor(private http: HttpClient) {}

  getData(): Observable<any> {
    return this.http.get('https://api.example.com/data')
      .pipe(catchError(error => throwError(() => error)));
  }
}

// component.ts
import { Component, OnInit } from '@angular/core';
import { DataService } from './data.service';

@Component({
  selector: 'app-data',
  template: `
    <div *ngIf="loading">Loading...</div>
    <div *ngIf="error">Error: {{error}}</div>
    <div *ngIf="data">{{data | json}}</div>
  `
})
export class DataComponent implements OnInit {
  data: any;
  loading = false;
  error: string = '';

  constructor(private dataService: DataService) {}

  ngOnInit() {
    this.loading = true;
    this.dataService.getData().subscribe({
      next: (response) => {
        this.data = response;
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

---

## 2. Your component needs to subscribe to multiple Observables and perform actions when all emit data. How do you manage it?

**Approach:**
- Use `forkJoin` for parallel execution (all must complete)
- Use `combineLatest` for latest values from all
- Use `zip` for sequential pairing

**Implementation:**

```typescript
import { Component, OnInit } from '@angular/core';
import { forkJoin, combineLatest } from 'rxjs';
import { DataService } from './data.service';

@Component({
  selector: 'app-multi-data',
  template: `<div>{{result | json}}</div>`
})
export class MultiDataComponent implements OnInit {
  result: any;

  constructor(private dataService: DataService) {}

  ngOnInit() {
    // forkJoin - waits for all to complete
    forkJoin({
      users: this.dataService.getUsers(),
      posts: this.dataService.getPosts(),
      comments: this.dataService.getComments()
    }).subscribe(result => {
      this.result = result;
      // result = { users: [...], posts: [...], comments: [...] }
    });

    // combineLatest - emits when any Observable emits
    combineLatest([
      this.dataService.getUsers(),
      this.dataService.getPosts()
    ]).subscribe(([users, posts]) => {
      console.log(users, posts);
    });
  }
}
```

---

## 3. You need to handle **global error handling** for HTTP requests in your Angular app. How do you do it?

**Approach:**
- Create HTTP Interceptor
- Catch all HTTP errors
- Display or log globally

**Implementation:**

```typescript
// error.interceptor.ts
import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpErrorResponse } from '@angular/common/http';
import { catchError, throwError } from 'rxjs';

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler) {
    return next.handle(req).pipe(
      catchError((error: HttpErrorResponse) => {
        let errorMessage = '';
        if (error.error instanceof ErrorEvent) {
          errorMessage = `Client Error: ${error.error.message}`;
        } else {
          errorMessage = `Server Error: ${error.status} - ${error.message}`;
        }
        alert(errorMessage); // Or use a notification service
        return throwError(() => errorMessage);
      })
    );
  }
}

// app.module.ts
import { HTTP_INTERCEPTORS } from '@angular/common/http';

@NgModule({
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true }
  ]
})
export class AppModule {}
```

---

## 4. How would you implement retry logic for failed HTTP requests?

**Approach:**
- Use `retry` operator for simple retries
- Use `retryWhen` for advanced retry with delays

**Implementation:**

```typescript
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { retry, retryWhen, delay, take, concat, throwError } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class DataService {
  constructor(private http: HttpClient) {}

  // Simple retry - 3 attempts
  getDataWithRetry() {
    return this.http.get('https://api.example.com/data')
      .pipe(retry(3));
  }

  // Advanced retry with delay
  getDataWithDelayedRetry() {
    return this.http.get('https://api.example.com/data')
      .pipe(
        retryWhen(errors =>
          errors.pipe(
            delay(1000), // Wait 1 second between retries
            take(3), // Max 3 retries
            concat(throwError(() => 'Max retries reached'))
          )
        )
      );
  }
}
```

---

## 5. You need to cancel pending HTTP requests when user navigates away. How do you implement this?

**Approach:**
- Use `takeUntil` with Subject in ngOnDestroy
- Or use async pipe for automatic unsubscription

**Implementation:**

```typescript
import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subject, takeUntil } from 'rxjs';
import { DataService } from './data.service';

@Component({
  selector: 'app-data',
  template: `<div>{{data | json}}</div>`
})
export class DataComponent implements OnInit, OnDestroy {
  data: any;
  private destroy$ = new Subject<void>();

  constructor(private dataService: DataService) {}

  ngOnInit() {
    this.dataService.getData()
      .pipe(takeUntil(this.destroy$))
      .subscribe(data => this.data = data);
  }

  ngOnDestroy() {
    this.destroy$.next();
    this.destroy$.complete();
  }
}

// Alternative: Using async pipe (automatic unsubscription)
@Component({
  selector: 'app-data-async',
  template: `<div>{{data$ | async | json}}</div>`
})
export class DataAsyncComponent {
  data$ = this.dataService.getData();
  
  constructor(private dataService: DataService) {}
}
```

---

## 6. You need to implement request/response interceptors for adding auth tokens. How do you structure this?

**Approach:**
- Create interceptor
- Clone request and add Authorization header
- Handle token refresh on 401 errors

**Implementation:**

```typescript
// auth.interceptor.ts
import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler } from '@angular/common/http';
import { AuthService } from './auth.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private authService: AuthService) {}

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    const token = this.authService.getToken();
    
    if (token) {
      const cloned = req.clone({
        headers: req.headers.set('Authorization', `Bearer ${token}`)
      });
      return next.handle(cloned);
    }
    
    return next.handle(req);
  }
}

// auth.service.ts
@Injectable({ providedIn: 'root' })
export class AuthService {
  getToken(): string | null {
    return localStorage.getItem('token');
  }
}

// app.module.ts
@NgModule({
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }
  ]
})
export class AppModule {}
```

---

## 7. How would you implement caching for HTTP requests to reduce API calls?

**Approach:**
- Create cache service or interceptor
- Store responses in Map with URL as key
- Check cache before making requests

**Implementation:**

```typescript
// cache.interceptor.ts
import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpResponse } from '@angular/common/http';
import { of, tap } from 'rxjs';

@Injectable()
export class CacheInterceptor implements HttpInterceptor {
  private cache = new Map<string, HttpResponse<any>>();

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    // Only cache GET requests
    if (req.method !== 'GET') {
      return next.handle(req);
    }

    const cachedResponse = this.cache.get(req.url);
    if (cachedResponse) {
      return of(cachedResponse);
    }

    return next.handle(req).pipe(
      tap(event => {
        if (event instanceof HttpResponse) {
          this.cache.set(req.url, event);
        }
      })
    );
  }
}

// Alternative: Cache Service
@Injectable({ providedIn: 'root' })
export class CacheService {
  private cache = new Map<string, { data: any, timestamp: number }>();
  private cacheDuration = 5 * 60 * 1000; // 5 minutes

  get(key: string): any | null {
    const cached = this.cache.get(key);
    if (!cached) return null;
    
    if (Date.now() - cached.timestamp > this.cacheDuration) {
      this.cache.delete(key);
      return null;
    }
    
    return cached.data;
  }

  set(key: string, data: any): void {
    this.cache.set(key, { data, timestamp: Date.now() });
  }
}
```

---

## 8. How do you handle parallel API calls and wait for all to complete before proceeding?

**Approach:**
- Use `forkJoin` operator
- Executes all Observables in parallel
- Emits single value when all complete

**Implementation:**

```typescript
import { Component, OnInit } from '@angular/core';
import { forkJoin } from 'rxjs';
import { DataService } from './data.service';

@Component({
  selector: 'app-parallel',
  template: `
    <div *ngIf="loading">Loading all data...</div>
    <div *ngIf="allData">
      <h3>Users: {{allData.users.length}}</h3>
      <h3>Posts: {{allData.posts.length}}</h3>
      <h3>Comments: {{allData.comments.length}}</h3>
    </div>
  `
})
export class ParallelComponent implements OnInit {
  allData: any;
  loading = false;

  constructor(private dataService: DataService) {}

  ngOnInit() {
    this.loading = true;
    
    forkJoin({
      users: this.dataService.getUsers(),
      posts: this.dataService.getPosts(),
      comments: this.dataService.getComments()
    }).subscribe({
      next: (results) => {
        this.allData = results;
        this.loading = false;
      },
      error: (err) => {
        console.error('One or more requests failed', err);
        this.loading = false;
      }
    });
  }
}

// Alternative: Array syntax
ngOnInit() {
  forkJoin([
    this.dataService.getUsers(),
    this.dataService.getPosts(),
    this.dataService.getComments()
  ]).subscribe(([users, posts, comments]) => {
    console.log(users, posts, comments);
  });
}
```

# Angular Interview Questions - Answers (Questions 9-16)
## Performance Optimization

---

## 9. How would you optimize performance for a component that renders a **large list of items**?

**Approach:**
- Use trackBy with ngFor
- Implement virtual scrolling
- Use OnPush change detection

**Implementation:**

```typescript
// component.ts
import { Component } from '@angular/core';

@Component({
  selector: 'app-list',
  template: `
    <div *ngFor="let item of items; trackBy: trackById">
      {{item.name}}
    </div>
  `,
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class ListComponent {
  items = Array.from({length: 10000}, (_, i) => ({id: i, name: `Item ${i}`}));

  trackById(index: number, item: any): number {
    return item.id; // Track by unique ID, not object reference
  }
}
```

---

## 10. Your Angular app is slow due to frequent change detection cycles. How do you optimize it?

**Approach:**
- Use OnPush change detection
- Run operations outside Angular zone
- Avoid function calls in templates

**Implementation:**

```typescript
import { Component, ChangeDetectionStrategy, NgZone } from '@angular/core';

@Component({
  selector: 'app-optimized',
  template: `<div>{{data}}</div>`,
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class OptimizedComponent {
  data: string = '';

  constructor(private ngZone: NgZone) {}

  // Run outside Angular zone for non-UI operations
  heavyOperation() {
    this.ngZone.runOutsideAngular(() => {
      // Heavy computation here
      const result = this.compute();
      
      // Re-enter Angular zone only when updating UI
      this.ngZone.run(() => {
        this.data = result;
      });
    });
  }

  private compute(): string {
    // Expensive operation
    return 'result';
  }
}
```

---

## 11. Your app has several frequently updated UI elements. How do you **optimize change detection** to avoid unnecessary re-renders?

**Approach:**
- Use OnPush strategy
- Leverage async pipe
- Manual change detection control

**Implementation:**

```typescript
import { Component, ChangeDetectionStrategy, ChangeDetectorRef } from '@angular/core';
import { Observable, interval } from 'rxjs';
import { map } from 'rxjs/operators';

@Component({
  selector: 'app-frequent-updates',
  template: `
    <div>Time: {{time$ | async}}</div>
    <div>Counter: {{counter}}</div>
  `,
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class FrequentUpdatesComponent {
  time$: Observable<Date> = interval(1000).pipe(map(() => new Date()));
  counter = 0;

  constructor(private cdr: ChangeDetectorRef) {}

  updateCounter() {
    this.counter++;
    this.cdr.markForCheck(); // Manually trigger change detection
  }
}
```

---

## 12. How would you implement virtual scrolling for a list with thousands of items?

**Approach:**
- Use Angular CDK ScrollingModule
- Render only visible items

**Implementation:**

```typescript
// app.module.ts
import { ScrollingModule } from '@angular/cdk/scrolling';

@NgModule({
  imports: [ScrollingModule]
})
export class AppModule {}

// component.ts
import { Component } from '@angular/core';

@Component({
  selector: 'app-virtual-scroll',
  template: `
    <cdk-virtual-scroll-viewport itemSize="50" style="height: 400px">
      <div *cdkVirtualFor="let item of items" class="item">
        {{item.name}}
      </div>
    </cdk-virtual-scroll-viewport>
  `,
  styles: ['.item { height: 50px; }']
})
export class VirtualScrollComponent {
  items = Array.from({length: 100000}, (_, i) => ({
    id: i,
    name: `Item ${i}`
  }));
}
```

---

## 13. You have a component with expensive computations in the template. How do you optimize it?

**Approach:**
- Move computations to component class
- Use pure pipes
- Memoize results

**Implementation:**

```typescript
import { Component, OnInit } from '@angular/core';
import { Pipe, PipeTransform } from '@angular/core';

// Pure pipe for transformations
@Pipe({ name: 'expensive', pure: true })
export class ExpensivePipe implements PipeTransform {
  transform(value: number): number {
    return value * 2; // Expensive calculation
  }
}

@Component({
  selector: 'app-computed',
  template: `
    <!-- BAD: Function call in template -->
    <!-- <div>{{calculateValue(data)}}</div> -->
    
    <!-- GOOD: Pre-computed value -->
    <div>{{computedValue}}</div>
    
    <!-- GOOD: Pure pipe -->
    <div>{{data | expensive}}</div>
  `
})
export class ComputedComponent implements OnInit {
  data = 100;
  computedValue: number = 0;

  ngOnInit() {
    this.computedValue = this.calculateValue(this.data);
  }

  private calculateValue(value: number): number {
    // Expensive computation
    return value * 2;
  }
}
```

---

## 14. How do you implement OnPush change detection strategy effectively?

**Approach:**
- Use OnPush strategy
- Immutable data patterns
- Manual detection when needed

**Implementation:**

```typescript
import { Component, Input, ChangeDetectionStrategy, ChangeDetectorRef } from '@angular/core';

interface User {
  id: number;
  name: string;
}

@Component({
  selector: 'app-onpush',
  template: `
    <div>{{user.name}}</div>
    <div>{{data$ | async}}</div>
  `,
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class OnPushComponent {
  @Input() user!: User;
  data$ = this.dataService.getData();

  constructor(
    private cdr: ChangeDetectorRef,
    private dataService: DataService
  ) {}

  // When updating from parent, create new object reference
  updateUser() {
    // BAD: this.user.name = 'New Name';
    
    // GOOD: Create new reference
    this.user = { ...this.user, name: 'New Name' };
  }

  // Manual detection for non-Input changes
  manualUpdate() {
    // Some operation
    this.cdr.markForCheck();
  }
}

// Parent component
@Component({
  selector: 'app-parent',
  template: `<app-onpush [user]="user"></app-onpush>`
})
export class ParentComponent {
  user: User = { id: 1, name: 'John' };

  updateUser() {
    // Create new reference for OnPush to detect
    this.user = { ...this.user, name: 'Jane' };
  }
}
```

---

## 15. Your app has a slow initial load time. What strategies would you implement?

**Approach:**
- Lazy loading modules
- Preloading strategies
- AOT compilation
- Code splitting

**Implementation:**

```typescript
// app-routing.module.ts
import { NgModule } from '@angular/core';
import { RouterModule, Routes, PreloadAllModules } from '@angular/router';

const routes: Routes = [
  { path: '', component: HomeComponent },
  {
    path: 'admin',
    loadChildren: () => import('./admin/admin.module').then(m => m.AdminModule)
  },
  {
    path: 'user',
    loadChildren: () => import('./user/user.module').then(m => m.UserModule)
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {
    preloadingStrategy: PreloadAllModules // Preload after initial load
  })],
  exports: [RouterModule]
})
export class AppRoutingModule {}

// Custom preloading strategy
import { PreloadingStrategy, Route } from '@angular/router';
import { Observable, of } from 'rxjs';

export class CustomPreloadingStrategy implements PreloadingStrategy {
  preload(route: Route, load: () => Observable<any>): Observable<any> {
    return route.data?.['preload'] ? load() : of(null);
  }
}

// Use in routing
const routes: Routes = [
  {
    path: 'important',
    loadChildren: () => import('./important/important.module').then(m => m.ImportantModule),
    data: { preload: true }
  }
];

// angular.json - Enable production optimizations
{
  "configurations": {
    "production": {
      "optimization": true,
      "buildOptimizer": true,
      "aot": true
    }
  }
}
```

---

## 16. How would you optimize bundle size in an Angular application?

**Approach:**
- Lazy loading
- Remove unused code
- Analyze bundle
- Tree-shakeable providers

**Implementation:**

```typescript
// 1. Tree-shakeable providers
@Injectable({ providedIn: 'root' })
export class DataService {
  // Automatically tree-shaken if not used
}

// 2. Lazy load heavy components
@Component({
  selector: 'app-heavy',
  template: `
    <ng-container *ngIf="showChart">
      <ng-container *ngComponentOutlet="chartComponent"></ng-container>
    </ng-container>
  `
})
export class HeavyComponent {
  chartComponent: any;
  showChart = false;

  async loadChart() {
    const { ChartComponent } = await import('./chart/chart.component');
    this.chartComponent = ChartComponent;
    this.showChart = true;
  }
}

// 3. Analyze bundle size
// Run: ng build --stats-json
// Then: npx webpack-bundle-analyzer dist/stats.json

// 4. Remove unused imports
// Before
import * as _ from 'lodash'; // Imports entire library

// After
import debounce from 'lodash-es/debounce'; // Import only what you need

// 5. Use lighter alternatives
// Instead of moment.js (large), use date-fns (smaller)
import { format } from 'date-fns';

// 6. Configure optimization in angular.json
{
  "configurations": {
    "production": {
      "optimization": true,
      "buildOptimizer": true,
      "sourceMap": false,
      "namedChunks": false,
      "extractLicenses": true,
      "vendorChunk": false
    }
  }
}
```

# Angular Interview Questions - Answers (Questions 17-23)
## Forms & Validation

---

## 17. You have a form with multiple steps and validations. How do you manage the form state across steps?

**Approach:**
- Use service to store form data
- Single FormGroup with multiple sections
- Validate each step before progression

**Implementation:**

```typescript
// form-state.service.ts
import { Injectable } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Injectable({ providedIn: 'root' })
export class FormStateService {
  private formData: any = {};

  constructor(private fb: FormBuilder) {}

  getFormData() {
    return this.formData;
  }

  setFormData(step: string, data: any) {
    this.formData[step] = data;
  }
}

// step1.component.ts
@Component({
  selector: 'app-step1',
  template: `
    <form [formGroup]="form" (ngSubmit)="onNext()">
      <input formControlName="name" placeholder="Name">
      <button type="submit" [disabled]="form.invalid">Next</button>
    </form>
  `
})
export class Step1Component {
  form: FormGroup;

  constructor(
    private fb: FormBuilder,
    private formState: FormStateService,
    private router: Router
  ) {
    this.form = this.fb.group({
      name: ['', Validators.required]
    });
    
    // Load saved data
    const saved = this.formState.getFormData()['step1'];
    if (saved) this.form.patchValue(saved);
  }

  onNext() {
    if (this.form.valid) {
      this.formState.setFormData('step1', this.form.value);
      this.router.navigate(['/step2']);
    }
  }
}
```

---

## 18. How do you decide when to use **Reactive Forms vs Template-Driven Forms** in Angular?

**When to use Reactive Forms:**
- Complex validation logic
- Dynamic form fields
- Better testability
- More control in TypeScript
- Large forms

**When to use Template-Driven Forms:**
- Simple forms
- Basic validation
- Quick prototypes
- Minimal logic

**Implementation:**

```typescript
// REACTIVE FORMS (Recommended for most cases)
@Component({
  selector: 'app-reactive',
  template: `
    <form [formGroup]="form" (ngSubmit)="onSubmit()">
      <input formControlName="email">
      <span *ngIf="form.get('email')?.errors?.['required']">Required</span>
      <button [disabled]="form.invalid">Submit</button>
    </form>
  `
})
export class ReactiveComponent {
  form = this.fb.group({
    email: ['', [Validators.required, Validators.email]],
    password: ['', [Validators.required, Validators.minLength(6)]]
  });

  constructor(private fb: FormBuilder) {}

  onSubmit() {
    console.log(this.form.value);
  }
}

// TEMPLATE-DRIVEN FORMS (For simple cases)
@Component({
  selector: 'app-template',
  template: `
    <form #f="ngForm" (ngSubmit)="onSubmit(f)">
      <input name="email" ngModel required email>
      <button [disabled]="f.invalid">Submit</button>
    </form>
  `
})
export class TemplateComponent {
  onSubmit(form: NgForm) {
    console.log(form.value);
  }
}
```

---

## 19. How do you implement **dynamic forms** where fields change based on previous user input?

**Approach:**
- Subscribe to valueChanges
- Dynamically add/remove controls
- Use FormArray for repeating fields

**Implementation:**

```typescript
@Component({
  selector: 'app-dynamic-form',
  template: `
    <form [formGroup]="form">
      <select formControlName="userType">
        <option value="individual">Individual</option>
        <option value="company">Company</option>
      </select>

      <input *ngIf="showCompanyField" formControlName="companyName" placeholder="Company Name">
      
      <div formArrayName="phones">
        <div *ngFor="let phone of phones.controls; let i = index">
          <input [formControlName]="i" placeholder="Phone">
          <button (click)="removePhone(i)">Remove</button>
        </div>
      </div>
      <button (click)="addPhone()">Add Phone</button>
    </form>
  `
})
export class DynamicFormComponent implements OnInit {
  form: FormGroup;
  showCompanyField = false;

  constructor(private fb: FormBuilder) {
    this.form = this.fb.group({
      userType: ['individual'],
      phones: this.fb.array([])
    });
  }

  ngOnInit() {
    this.form.get('userType')?.valueChanges.subscribe(value => {
      if (value === 'company') {
        this.form.addControl('companyName', this.fb.control('', Validators.required));
        this.showCompanyField = true;
      } else {
        this.form.removeControl('companyName');
        this.showCompanyField = false;
      }
    });
  }

  get phones() {
    return this.form.get('phones') as FormArray;
  }

  addPhone() {
    this.phones.push(this.fb.control(''));
  }

  removePhone(index: number) {
    this.phones.removeAt(index);
  }
}
```

---

## 20. You need to create custom validators that make async API calls. How do you implement this?

**Approach:**
- Create AsyncValidatorFn
- Return Observable or Promise
- Add debounceTime to reduce API calls

**Implementation:**

```typescript
import { AbstractControl, AsyncValidatorFn, ValidationErrors } from '@angular/forms';
import { Observable, of } from 'rxjs';
import { map, catchError, debounceTime, switchMap } from 'rxjs/operators';

// Validator function
export function usernameValidator(userService: UserService): AsyncValidatorFn {
  return (control: AbstractControl): Observable<ValidationErrors | null> => {
    if (!control.value) {
      return of(null);
    }

    return of(control.value).pipe(
      debounceTime(500), // Wait 500ms after user stops typing
      switchMap(username => 
        userService.checkUsername(username).pipe(
          map(exists => exists ? { usernameTaken: true } : null),
          catchError(() => of(null))
        )
      )
    );
  };
}

// Component usage
@Component({
  selector: 'app-register',
  template: `
    <form [formGroup]="form">
      <input formControlName="username">
      <span *ngIf="form.get('username')?.errors?.['usernameTaken']">
        Username already taken
      </span>
      <span *ngIf="form.get('username')?.pending">Checking...</span>
    </form>
  `
})
export class RegisterComponent {
  form: FormGroup;

  constructor(private fb: FormBuilder, private userService: UserService) {
    this.form = this.fb.group({
      username: ['', 
        [Validators.required], 
        [usernameValidator(this.userService)] // Async validator
      ]
    });
  }
}

// Service
@Injectable({ providedIn: 'root' })
export class UserService {
  constructor(private http: HttpClient) {}

  checkUsername(username: string): Observable<boolean> {
    return this.http.get<boolean>(`/api/check-username/${username}`);
  }
}
```

---

## 21. How would you implement cross-field validation (e.g., password confirmation)?

**Approach:**
- Create validator at FormGroup level
- Access multiple controls
- Return errors on group or specific control

**Implementation:**

```typescript
import { AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';

// Custom validator function
export function passwordMatchValidator(): ValidatorFn {
  return (control: AbstractControl): ValidationErrors | null => {
    const password = control.get('password');
    const confirmPassword = control.get('confirmPassword');

    if (!password || !confirmPassword) {
      return null;
    }

    return password.value === confirmPassword.value ? null : { passwordMismatch: true };
  };
}

// Component
@Component({
  selector: 'app-password-form',
  template: `
    <form [formGroup]="form">
      <input type="password" formControlName="password" placeholder="Password">
      
      <input type="password" formControlName="confirmPassword" placeholder="Confirm Password">
      
      <div *ngIf="form.errors?.['passwordMismatch'] && form.get('confirmPassword')?.touched">
        Passwords do not match
      </div>
      
      <button [disabled]="form.invalid">Submit</button>
    </form>
  `
})
export class PasswordFormComponent {
  form: FormGroup;

  constructor(private fb: FormBuilder) {
    this.form = this.fb.group({
      password: ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['', Validators.required]
    }, { validators: passwordMatchValidator() }); // Apply at group level
  }
}
```

---

## 22. You have a form with 50+ fields. How do you manage it efficiently?

**Approach:**
- Break into logical FormGroups
- Split into multiple components
- Use FormBuilder for cleaner syntax
- Consider dynamic form generation

**Implementation:**

```typescript
// form-config.ts
export interface FieldConfig {
  name: string;
  type: string;
  label: string;
  validators?: any[];
}

// main-form.component.ts
@Component({
  selector: 'app-large-form',
  template: `
    <form [formGroup]="form">
      <div formGroupName="personal">
        <app-personal-info [formGroup]="personalGroup"></app-personal-info>
      </div>
      
      <div formGroupName="address">
        <app-address-info [formGroup]="addressGroup"></app-address-info>
      </div>
      
      <div formGroupName="employment">
        <app-employment-info [formGroup]="employmentGroup"></app-employment-info>
      </div>
      
      <button (click)="onSubmit()">Submit</button>
    </form>
  `
})
export class LargeFormComponent {
  form: FormGroup;

  constructor(private fb: FormBuilder) {
    this.form = this.fb.group({
      personal: this.createPersonalGroup(),
      address: this.createAddressGroup(),
      employment: this.createEmploymentGroup()
    });
  }

  get personalGroup() {
    return this.form.get('personal') as FormGroup;
  }

  get addressGroup() {
    return this.form.get('address') as FormGroup;
  }

  get employmentGroup() {
    return this.form.get('employment') as FormGroup;
  }

  private createPersonalGroup(): FormGroup {
    return this.fb.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      phone: ['']
    });
  }

  private createAddressGroup(): FormGroup {
    return this.fb.group({
      street: [''],
      city: [''],
      state: [''],
      zip: ['']
    });
  }

  private createEmploymentGroup(): FormGroup {
    return this.fb.group({
      company: [''],
      position: [''],
      salary: ['']
    });
  }

  onSubmit() {
    if (this.form.valid) {
      console.log(this.form.value);
    }
  }
}

// personal-info.component.ts
@Component({
  selector: 'app-personal-info',
  template: `
    <div [formGroup]="formGroup">
      <input formControlName="firstName" placeholder="First Name">
      <input formControlName="lastName" placeholder="Last Name">
      <input formControlName="email" placeholder="Email">
      <input formControlName="phone" placeholder="Phone">
    </div>
  `
})
export class PersonalInfoComponent {
  @Input() formGroup!: FormGroup;
}
```

---

## 23. You need to handle nested form groups and form arrays. How do you structure this?

**Approach:**
- Use FormBuilder for nested structures
- FormGroup for nested objects
- FormArray for repeating items
- Access with get() and dot notation

**Implementation:**

```typescript
@Component({
  selector: 'app-nested-form',
  template: `
    <form [formGroup]="form">
      <!-- Nested FormGroup -->
      <div formGroupName="user">
        <input formControlName="name" placeholder="Name">
        
        <div formGroupName="address">
          <input formControlName="street" placeholder="Street">
          <input formControlName="city" placeholder="City">
        </div>
      </div>

      <!-- FormArray -->
      <div formArrayName="skills">
        <div *ngFor="let skill of skills.controls; let i = index" [formGroupName]="i">
          <input formControlName="name" placeholder="Skill Name">
          <input formControlName="level" placeholder="Level">
          <button (click)="removeSkill(i)">Remove</button>
        </div>
      </div>
      <button (click)="addSkill()">Add Skill</button>

      <button (click)="onSubmit()">Submit</button>
    </form>
  `
})
export class NestedFormComponent {
  form: FormGroup;

  constructor(private fb: FormBuilder) {
    this.form = this.fb.group({
      user: this.fb.group({
        name: ['', Validators.required],
        address: this.fb.group({
          street: [''],
          city: ['']
        })
      }),
      skills: this.fb.array([])
    });
  }

  get skills() {
    return this.form.get('skills') as FormArray;
  }

  addSkill() {
    const skillGroup = this.fb.group({
      name: ['', Validators.required],
      level: ['', Validators.required]
    });
    this.skills.push(skillGroup);
  }

  removeSkill(index: number) {
    this.skills.removeAt(index);
  }

  onSubmit() {
    console.log(this.form.value);
    // Output: { user: { name: '...', address: { street: '...', city: '...' }}, skills: [...] }
  }

  // Access nested values
  getUserName() {
    return this.form.get('user.name')?.value;
  }

  getCity() {
    return this.form.get('user.address.city')?.value;
  }
}
```

# Angular Interview Questions - Answers (Questions 24-30)
## Component Communication & Architecture

---

## 24. A child component needs to communicate with a deeply nested parent component. How do you handle this in Angular?

**Approach:**
- Use shared service with Subject/BehaviorSubject
- Inject service in both components
- Avoid chaining Output events

**Implementation:**

```typescript
// communication.service.ts
import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class CommunicationService {
  private messageSource = new Subject<string>();
  message$ = this.messageSource.asObservable();

  sendMessage(message: string) {
    this.messageSource.next(message);
  }
}

// deeply-nested-child.component.ts
@Component({
  selector: 'app-nested-child',
  template: `<button (click)="notify()">Notify Parent</button>`
})
export class NestedChildComponent {
  constructor(private comm: CommunicationService) {}

  notify() {
    this.comm.sendMessage('Hello from nested child');
  }
}

// parent.component.ts
@Component({
  selector: 'app-parent',
  template: `<div>Message: {{message}}</div>`
})
export class ParentComponent implements OnInit, OnDestroy {
  message = '';
  private destroy$ = new Subject<void>();

  constructor(private comm: CommunicationService) {}

  ngOnInit() {
    this.comm.message$
      .pipe(takeUntil(this.destroy$))
      .subscribe(msg => this.message = msg);
  }

  ngOnDestroy() {
    this.destroy$.next();
    this.destroy$.complete();
  }
}
```

---

## 25. How do you manage **state across multiple unrelated components** in Angular?

**Approach:**
- Shared service with BehaviorSubject
- Singleton service (providedIn: 'root')
- Expose Observable, update through methods

**Implementation:**

```typescript
// state.service.ts
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

interface AppState {
  user: any;
  cart: any[];
}

@Injectable({ providedIn: 'root' })
export class StateService {
  private state = new BehaviorSubject<AppState>({
    user: null,
    cart: []
  });

  state$: Observable<AppState> = this.state.asObservable();

  updateUser(user: any) {
    this.state.next({ ...this.state.value, user });
  }

  addToCart(item: any) {
    const cart = [...this.state.value.cart, item];
    this.state.next({ ...this.state.value, cart });
  }

  getState(): AppState {
    return this.state.value;
  }
}

// component-a.component.ts
@Component({
  selector: 'app-component-a',
  template: `<div>User: {{(state$ | async)?.user?.name}}</div>`
})
export class ComponentAComponent {
  state$ = this.stateService.state$;

  constructor(private stateService: StateService) {}

  updateUser() {
    this.stateService.updateUser({ name: 'John' });
  }
}

// component-b.component.ts
@Component({
  selector: 'app-component-b',
  template: `<div>Cart: {{(state$ | async)?.cart.length}}</div>`
})
export class ComponentBComponent {
  state$ = this.stateService.state$;

  constructor(private stateService: StateService) {}
}
```

---

## 26. You have a service that provides shared data across multiple components. How do you ensure it stays updated correctly?

**Approach:**
- Use BehaviorSubject for current + future values
- Expose as Observable with asObservable()
- Update only through service methods

**Implementation:**

```typescript
// data.service.ts
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class DataService {
  private dataSubject = new BehaviorSubject<any[]>([]);
  
  // Expose as Observable to prevent external next() calls
  data$: Observable<any[]> = this.dataSubject.asObservable();

  // Update methods
  setData(data: any[]) {
    this.dataSubject.next(data);
  }

  addItem(item: any) {
    const current = this.dataSubject.value;
    this.dataSubject.next([...current, item]);
  }

  removeItem(id: number) {
    const current = this.dataSubject.value;
    this.dataSubject.next(current.filter(item => item.id !== id));
  }

  // Get current value synchronously
  getCurrentData(): any[] {
    return this.dataSubject.value;
  }
}

// component.ts
@Component({
  selector: 'app-data-display',
  template: `
    <div *ngFor="let item of data$ | async">{{item.name}}</div>
    <button (click)="add()">Add Item</button>
  `
})
export class DataDisplayComponent {
  data$ = this.dataService.data$;

  constructor(private dataService: DataService) {}

  add() {
    this.dataService.addItem({ id: Date.now(), name: 'New Item' });
  }
}
```

---

## 27. How would you implement a notification system that can be triggered from anywhere in the app?

**Approach:**
- Notification service with Subject
- Global notification component
- Trigger from anywhere in app

**Implementation:**

```typescript
// notification.service.ts
import { Injectable } from '@angular/core';
import { Subject, Observable } from 'rxjs';

export interface Notification {
  message: string;
  type: 'success' | 'error' | 'info';
}

@Injectable({ providedIn: 'root' })
export class NotificationService {
  private notificationSubject = new Subject<Notification>();
  notifications$: Observable<Notification> = this.notificationSubject.asObservable();

  show(message: string, type: 'success' | 'error' | 'info' = 'info') {
    this.notificationSubject.next({ message, type });
  }

  success(message: string) {
    this.show(message, 'success');
  }

  error(message: string) {
    this.show(message, 'error');
  }
}

// notification.component.ts
@Component({
  selector: 'app-notification',
  template: `
    <div *ngIf="notification" [class]="notification.type">
      {{notification.message}}
    </div>
  `,
  styles: [`
    div { padding: 10px; margin: 10px; }
    .success { background: green; color: white; }
    .error { background: red; color: white; }
    .info { background: blue; color: white; }
  `]
})
export class NotificationComponent implements OnInit {
  notification: Notification | null = null;

  constructor(private notificationService: NotificationService) {}

  ngOnInit() {
    this.notificationService.notifications$.subscribe(notification => {
      this.notification = notification;
      setTimeout(() => this.notification = null, 3000);
    });
  }
}

// app.component.ts
@Component({
  selector: 'app-root',
  template: `
    <app-notification></app-notification>
    <router-outlet></router-outlet>
  `
})
export class AppComponent {}

// any-component.ts - Usage
@Component({
  selector: 'app-any',
  template: `<button (click)="save()">Save</button>`
})
export class AnyComponent {
  constructor(private notification: NotificationService) {}

  save() {
    // Save logic
    this.notification.success('Saved successfully!');
  }
}
```

---

## 28. You need to pass data between sibling components. What are your options?

**Approach:**
- Shared service (recommended)
- Parent as mediator
- Route parameters

**Implementation:**

```typescript
// METHOD 1: Shared Service (Recommended)
@Injectable({ providedIn: 'root' })
export class SiblingService {
  private dataSubject = new BehaviorSubject<any>(null);
  data$ = this.dataSubject.asObservable();

  setData(data: any) {
    this.dataSubject.next(data);
  }
}

@Component({
  selector: 'app-sibling-a',
  template: `<button (click)="send()">Send to Sibling B</button>`
})
export class SiblingAComponent {
  constructor(private service: SiblingService) {}

  send() {
    this.service.setData({ message: 'Hello from A' });
  }
}

@Component({
  selector: 'app-sibling-b',
  template: `<div>{{data$ | async | json}}</div>`
})
export class SiblingBComponent {
  data$ = this.service.data$;
  constructor(private service: SiblingService) {}
}

// METHOD 2: Parent as Mediator
@Component({
  selector: 'app-parent',
  template: `
    <app-sibling-a (dataEvent)="onData($event)"></app-sibling-a>
    <app-sibling-b [data]="sharedData"></app-sibling-b>
  `
})
export class ParentComponent {
  sharedData: any;

  onData(data: any) {
    this.sharedData = data;
  }
}
```

---

## 29. How do you implement a modal service that can be opened from any component?

**Approach:**
- Service to dynamically create components
- Use ViewContainerRef for rendering
- Methods to open/close modals

**Implementation:**

```typescript
// modal.service.ts
import { Injectable, ComponentRef, ViewContainerRef, Type } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class ModalService {
  private modalContainer?: ViewContainerRef;
  private componentRef?: ComponentRef<any>;

  setContainer(container: ViewContainerRef) {
    this.modalContainer = container;
  }

  open<T>(component: Type<T>, data?: any): ComponentRef<T> {
    if (!this.modalContainer) {
      throw new Error('Modal container not set');
    }

    this.close();
    this.componentRef = this.modalContainer.createComponent(component);
    
    if (data && this.componentRef.instance) {
      Object.assign(this.componentRef.instance, data);
    }

    return this.componentRef;
  }

  close() {
    if (this.componentRef) {
      this.componentRef.destroy();
      this.componentRef = undefined;
    }
  }
}

// app.component.ts
@Component({
  selector: 'app-root',
  template: `
    <router-outlet></router-outlet>
    <ng-container #modalContainer></ng-container>
  `
})
export class AppComponent implements AfterViewInit {
  @ViewChild('modalContainer', { read: ViewContainerRef }) 
  modalContainer!: ViewContainerRef;

  constructor(private modalService: ModalService) {}

  ngAfterViewInit() {
    this.modalService.setContainer(this.modalContainer);
  }
}

// modal-content.component.ts
@Component({
  selector: 'app-modal-content',
  template: `
    <div class="modal">
      <h2>{{title}}</h2>
      <p>{{message}}</p>
      <button (click)="close()">Close</button>
    </div>
  `,
  styles: [`
    .modal {
      position: fixed;
      top: 50%;
      left: 50%;
      transform: translate(-50%, -50%);
      background: white;
      padding: 20px;
      box-shadow: 0 0 10px rgba(0,0,0,0.5);
    }
  `]
})
export class ModalContentComponent {
  title = '';
  message = '';

  constructor(private modalService: ModalService) {}

  close() {
    this.modalService.close();
  }
}

// Usage in any component
@Component({
  selector: 'app-any',
  template: `<button (click)="openModal()">Open Modal</button>`
})
export class AnyComponent {
  constructor(private modalService: ModalService) {}

  openModal() {
    this.modalService.open(ModalContentComponent, {
      title: 'Hello',
      message: 'This is a modal'
    });
  }
}
```

---

## 30. How would you implement content projection for flexible component composition?

**Approach:**
- Use ng-content in child component
- Use select attribute for multiple slots
- Parent provides content to project

**Implementation:**

```typescript
// card.component.ts - Child with projection
@Component({
  selector: 'app-card',
  template: `
    <div class="card">
      <div class="header">
        <ng-content select="[card-header]"></ng-content>
      </div>
      <div class="body">
        <ng-content></ng-content>
      </div>
      <div class="footer">
        <ng-content select="[card-footer]"></ng-content>
      </div>
    </div>
  `,
  styles: [`
    .card { border: 1px solid #ccc; padding: 10px; }
    .header { font-weight: bold; }
    .footer { text-align: right; }
  `]
})
export class CardComponent {}

// parent.component.ts - Using projection
@Component({
  selector: 'app-parent',
  template: `
    <app-card>
      <h2 card-header>Card Title</h2>
      <p>This is the main content of the card</p>
      <button card-footer>Action</button>
    </app-card>
  `
})
export class ParentComponent {}

// Advanced: Conditional projection
@Component({
  selector: 'app-tabs',
  template: `
    <div class="tabs">
      <button *ngFor="let tab of tabs" (click)="selectTab(tab)">
        {{tab}}
      </button>
    </div>
    <div class="content">
      <ng-content></ng-content>
    </div>
  `
})
export class TabsComponent {
  @Input() tabs: string[] = [];
  selectedTab = '';

  selectTab(tab: string) {
    this.selectedTab = tab;
  }
}
```

# Angular Interview Questions - Answers (Questions 31-37)
## Routing & Navigation

---

## 31. How would you handle **lazy loading** of modules to improve app startup time?

**Approach:**
- Use loadChildren with dynamic import
- Load modules only when route is accessed
- Reduces initial bundle size

**Implementation:**

```typescript
// app-routing.module.ts
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  { path: '', component: HomeComponent },
  {
    path: 'admin',
    loadChildren: () => import('./admin/admin.module').then(m => m.AdminModule)
  },
  {
    path: 'users',
    loadChildren: () => import('./users/users.module').then(m => m.UsersModule)
  },
  {
    path: 'products',
    loadChildren: () => import('./products/products.module').then(m => m.ProductsModule)
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}

// admin/admin-routing.module.ts
const routes: Routes = [
  { path: '', component: AdminDashboardComponent },
  { path: 'users', component: AdminUsersComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule {}

// Generate lazy module with CLI
// ng generate module admin --route admin --module app.module
```

---

## 32. How would you implement **role-based access control** in Angular routes and components?

**Approach:**
- Create CanActivate guard
- Check user role from auth service
- Store role requirements in route data

**Implementation:**

```typescript
// auth.guard.ts
import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, Router } from '@angular/router';
import { AuthService } from './auth.service';

@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate {
  constructor(private auth: AuthService, private router: Router) {}

  canActivate(route: ActivatedRouteSnapshot): boolean {
    const requiredRoles = route.data['roles'] as string[];
    const userRole = this.auth.getUserRole();

    if (!this.auth.isAuthenticated()) {
      this.router.navigate(['/login']);
      return false;
    }

    if (requiredRoles && !requiredRoles.includes(userRole)) {
      this.router.navigate(['/unauthorized']);
      return false;
    }

    return true;
  }
}

// app-routing.module.ts
const routes: Routes = [
  {
    path: 'admin',
    component: AdminComponent,
    canActivate: [AuthGuard],
    data: { roles: ['admin'] }
  },
  {
    path: 'dashboard',
    component: DashboardComponent,
    canActivate: [AuthGuard],
    data: { roles: ['admin', 'user'] }
  },
  { path: 'login', component: LoginComponent },
  { path: 'unauthorized', component: UnauthorizedComponent }
];

// auth.service.ts
@Injectable({ providedIn: 'root' })
export class AuthService {
  isAuthenticated(): boolean {
    return !!localStorage.getItem('token');
  }

  getUserRole(): string {
    return localStorage.getItem('role') || '';
  }
}
```

---

## 33. You need to prevent navigation if form has unsaved changes. How do you implement this?

**Approach:**
- Implement CanDeactivate guard
- Check form dirty state
- Show confirmation dialog

**Implementation:**

```typescript
// can-deactivate.guard.ts
import { Injectable } from '@angular/core';
import { CanDeactivate } from '@angular/router';
import { Observable } from 'rxjs';

export interface CanComponentDeactivate {
  canDeactivate: () => boolean | Observable<boolean>;
}

@Injectable({ providedIn: 'root' })
export class CanDeactivateGuard implements CanDeactivate<CanComponentDeactivate> {
  canDeactivate(component: CanComponentDeactivate): boolean | Observable<boolean> {
    return component.canDeactivate ? component.canDeactivate() : true;
  }
}

// form.component.ts
@Component({
  selector: 'app-form',
  template: `
    <form [formGroup]="form">
      <input formControlName="name">
      <button (click)="save()">Save</button>
    </form>
  `
})
export class FormComponent implements CanComponentDeactivate {
  form: FormGroup;
  saved = false;

  constructor(private fb: FormBuilder) {
    this.form = this.fb.group({ name: [''] });
  }

  canDeactivate(): boolean {
    if (this.form.dirty && !this.saved) {
      return confirm('You have unsaved changes. Do you want to leave?');
    }
    return true;
  }

  save() {
    this.saved = true;
    // Save logic
  }
}

// app-routing.module.ts
const routes: Routes = [
  {
    path: 'form',
    component: FormComponent,
    canDeactivate: [CanDeactivateGuard]
  }
];
```

---

## 34. How do you implement route guards that make async API calls?

**Approach:**
- Return Observable or Promise from guard
- Make API call inside guard
- Angular waits for completion

**Implementation:**

```typescript
// permission.guard.ts
import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { map, catchError } from 'rxjs/operators';
import { of } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class PermissionGuard implements CanActivate {
  constructor(
    private permissionService: PermissionService,
    private router: Router
  ) {}

  canActivate(route: ActivatedRouteSnapshot): Observable<boolean> {
    const requiredPermission = route.data['permission'];

    return this.permissionService.checkPermission(requiredPermission).pipe(
      map(hasPermission => {
        if (!hasPermission) {
          this.router.navigate(['/access-denied']);
          return false;
        }
        return true;
      }),
      catchError(() => {
        this.router.navigate(['/error']);
        return of(false);
      })
    );
  }
}

// permission.service.ts
@Injectable({ providedIn: 'root' })
export class PermissionService {
  constructor(private http: HttpClient) {}

  checkPermission(permission: string): Observable<boolean> {
    return this.http.get<boolean>(`/api/permissions/${permission}`);
  }
}

// app-routing.module.ts
const routes: Routes = [
  {
    path: 'protected',
    component: ProtectedComponent,
    canActivate: [PermissionGuard],
    data: { permission: 'view_protected' }
  }
];
```

---

## 35. You need to preload certain lazy-loaded modules. How do you configure this?

**Approach:**
- Use PreloadAllModules strategy
- Or create custom preloading strategy
- Configure in RouterModule.forRoot

**Implementation:**

```typescript
// METHOD 1: Preload all modules
import { PreloadAllModules } from '@angular/router';

@NgModule({
  imports: [RouterModule.forRoot(routes, {
    preloadingStrategy: PreloadAllModules
  })],
  exports: [RouterModule]
})
export class AppRoutingModule {}

// METHOD 2: Custom selective preloading
import { PreloadingStrategy, Route } from '@angular/router';
import { Observable, of } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class CustomPreloadingStrategy implements PreloadingStrategy {
  preload(route: Route, load: () => Observable<any>): Observable<any> {
    // Preload if route data has preload: true
    return route.data?.['preload'] ? load() : of(null);
  }
}

// app-routing.module.ts
const routes: Routes = [
  {
    path: 'important',
    loadChildren: () => import('./important/important.module').then(m => m.ImportantModule),
    data: { preload: true } // Will be preloaded
  },
  {
    path: 'rarely-used',
    loadChildren: () => import('./rarely/rarely.module').then(m => m.RarelyModule)
    // No preload flag - won't be preloaded
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {
    preloadingStrategy: CustomPreloadingStrategy
  })],
  exports: [RouterModule]
})
export class AppRoutingModule {}

// METHOD 3: Network-aware preloading
@Injectable({ providedIn: 'root' })
export class NetworkAwarePreloadingStrategy implements PreloadingStrategy {
  preload(route: Route, load: () => Observable<any>): Observable<any> {
    const connection = (navigator as any).connection;
    
    if (connection && connection.effectiveType === '4g') {
      return route.data?.['preload'] ? load() : of(null);
    }
    
    return of(null); // Don't preload on slow connections
  }
}
```

---

## 36. How do you handle 404 pages and route redirects?

**Approach:**
- Wildcard route for 404
- redirectTo for simple redirects
- Order matters - wildcard last

**Implementation:**

```typescript
// app-routing.module.ts
const routes: Routes = [
  // Default redirect
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  
  // Regular routes
  { path: 'home', component: HomeComponent },
  { path: 'about', component: AboutComponent },
  
  // Redirect old URLs
  { path: 'old-path', redirectTo: '/new-path', pathMatch: 'full' },
  
  // Route with parameter
  { path: 'user/:id', component: UserComponent },
  
  // 404 - Must be last
  { path: '404', component: NotFoundComponent },
  { path: '**', redirectTo: '/404' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}

// not-found.component.ts
@Component({
  selector: 'app-not-found',
  template: `
    <div class="not-found">
      <h1>404 - Page Not Found</h1>
      <p>The page you're looking for doesn't exist.</p>
      <a routerLink="/home">Go Home</a>
    </div>
  `,
  styles: [`
    .not-found {
      text-align: center;
      padding: 50px;
    }
  `]
})
export class NotFoundComponent {}

// Programmatic navigation to 404
@Component({
  selector: 'app-user',
  template: `<div>User: {{user?.name}}</div>`
})
export class UserComponent implements OnInit {
  user: any;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private userService: UserService
  ) {}

  ngOnInit() {
    const id = this.route.snapshot.params['id'];
    this.userService.getUser(id).subscribe({
      next: user => this.user = user,
      error: () => this.router.navigate(['/404'])
    });
  }
}
```

---

## 37. You need to implement nested routing with shared layouts. How do you structure this?

**Approach:**
- Parent route with router-outlet
- Children array for child routes
- Shared layout in parent component

**Implementation:**

```typescript
// app-routing.module.ts
const routes: Routes = [
  {
    path: 'dashboard',
    component: DashboardLayoutComponent,
    children: [
      { path: '', redirectTo: 'overview', pathMatch: 'full' },
      { path: 'overview', component: OverviewComponent },
      { path: 'analytics', component: AnalyticsComponent },
      { path: 'reports', component: ReportsComponent }
    ]
  },
  {
    path: 'admin',
    component: AdminLayoutComponent,
    canActivate: [AuthGuard],
    children: [
      { path: '', component: AdminHomeComponent },
      { path: 'users', component: AdminUsersComponent },
      { path: 'settings', component: AdminSettingsComponent }
    ]
  }
];

// dashboard-layout.component.ts
@Component({
  selector: 'app-dashboard-layout',
  template: `
    <div class="layout">
      <nav class="sidebar">
        <a routerLink="overview" routerLinkActive="active">Overview</a>
        <a routerLink="analytics" routerLinkActive="active">Analytics</a>
        <a routerLink="reports" routerLinkActive="active">Reports</a>
      </nav>
      <main class="content">
        <router-outlet></router-outlet>
      </main>
    </div>
  `,
  styles: [`
    .layout { display: flex; }
    .sidebar { width: 200px; background: #f0f0f0; }
    .content { flex: 1; padding: 20px; }
    .active { font-weight: bold; }
  `]
})
export class DashboardLayoutComponent {}

// Nested children navigation
@Component({
  selector: 'app-overview',
  template: `
    <h2>Overview</h2>
    <button (click)="goToAnalytics()">View Analytics</button>
  `
})
export class OverviewComponent {
  constructor(private router: Router) {}

  goToAnalytics() {
    // Relative navigation
    this.router.navigate(['../analytics'], { relativeTo: this.route });
    
    // Or absolute navigation
    // this.router.navigate(['/dashboard/analytics']);
  }
}

// Multi-level nesting
const routes: Routes = [
  {
    path: 'app',
    component: AppLayoutComponent,
    children: [
      {
        path: 'settings',
        component: SettingsLayoutComponent,
        children: [
          { path: 'profile', component: ProfileComponent },
          { path: 'security', component: SecurityComponent }
        ]
      }
    ]
  }
];
```

# Angular Interview Questions - Answers (Questions 38-44)
## RxJS & Observables

---

## 38. How do you prevent memory leaks when subscribing to Observables in components?

**Approach:**
- Use takeUntil with Subject in ngOnDestroy
- Use async pipe (automatic unsubscription)
- Manual unsubscribe in ngOnDestroy

**Implementation:**

```typescript
// METHOD 1: takeUntil (Recommended)
import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subject, takeUntil } from 'rxjs';

@Component({
  selector: 'app-data',
  template: `<div>{{data}}</div>`
})
export class DataComponent implements OnInit, OnDestroy {
  data: any;
  private destroy$ = new Subject<void>();

  constructor(private dataService: DataService) {}

  ngOnInit() {
    this.dataService.getData()
      .pipe(takeUntil(this.destroy$))
      .subscribe(data => this.data = data);

    this.dataService.getOtherData()
      .pipe(takeUntil(this.destroy$))
      .subscribe(data => console.log(data));
  }

  ngOnDestroy() {
    this.destroy$.next();
    this.destroy$.complete();
  }
}

// METHOD 2: Async pipe (Best for templates)
@Component({
  selector: 'app-data-async',
  template: `<div>{{data$ | async | json}}</div>`
})
export class DataAsyncComponent {
  data$ = this.dataService.getData();
  constructor(private dataService: DataService) {}
}

// METHOD 3: Manual unsubscribe
@Component({
  selector: 'app-data-manual',
  template: `<div>{{data}}</div>`
})
export class DataManualComponent implements OnInit, OnDestroy {
  data: any;
  private subscription = new Subscription();

  constructor(private dataService: DataService) {}

  ngOnInit() {
    this.subscription.add(
      this.dataService.getData().subscribe(data => this.data = data)
    );
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }
}
```

---

## 39. How would you handle **debouncing or throttling** user input (e.g., search box) to avoid excessive API calls?

**Approach:**
- Use debounceTime to wait for pause in typing
- Use distinctUntilChanged to avoid duplicates
- Use switchMap to cancel previous requests

**Implementation:**

```typescript
import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { debounceTime, distinctUntilChanged, switchMap, filter } from 'rxjs/operators';

@Component({
  selector: 'app-search',
  template: `
    <input [formControl]="searchControl" placeholder="Search...">
    <div *ngFor="let result of results">{{result.name}}</div>
  `
})
export class SearchComponent implements OnInit {
  searchControl = new FormControl('');
  results: any[] = [];

  constructor(private searchService: SearchService) {}

  ngOnInit() {
    this.searchControl.valueChanges.pipe(
      debounceTime(300),              // Wait 300ms after user stops typing
      distinctUntilChanged(),         // Only if value changed
      filter(term => term.length > 2), // Only search if 3+ characters
      switchMap(term => this.searchService.search(term)) // Cancel previous, start new
    ).subscribe(results => this.results = results);
  }
}

// throttleTime example - limit frequency
import { throttleTime } from 'rxjs/operators';

@Component({
  selector: 'app-scroll',
  template: `<div (scroll)="onScroll($event)">Content</div>`
})
export class ScrollComponent implements OnInit {
  private scrollSubject = new Subject<Event>();

  ngOnInit() {
    this.scrollSubject.pipe(
      throttleTime(1000) // Emit at most once per second
    ).subscribe(event => {
      console.log('Scroll event processed');
    });
  }

  onScroll(event: Event) {
    this.scrollSubject.next(event);
  }
}
```

---

## 40. You need to combine data from multiple Observables. What operators would you use?

**Approach:**
- forkJoin: Wait for all to complete (parallel)
- combineLatest: Emit when any emits
- merge: Combine into single stream
- zip: Pair values in sequence

**Implementation:**

```typescript
import { Component, OnInit } from '@angular/core';
import { forkJoin, combineLatest, merge, zip } from 'rxjs';

@Component({
  selector: 'app-combine',
  template: `<div>{{result | json}}</div>`
})
export class CombineComponent implements OnInit {
  result: any;

  constructor(private dataService: DataService) {}

  ngOnInit() {
    // forkJoin - Wait for all to complete (like Promise.all)
    forkJoin({
      users: this.dataService.getUsers(),
      posts: this.dataService.getPosts(),
      comments: this.dataService.getComments()
    }).subscribe(result => {
      this.result = result;
      // result = { users: [...], posts: [...], comments: [...] }
    });

    // combineLatest - Emit when any Observable emits
    combineLatest([
      this.dataService.getUsers(),
      this.dataService.getPosts()
    ]).subscribe(([users, posts]) => {
      console.log('Latest users and posts', users, posts);
    });

    // merge - Combine emissions into single stream
    merge(
      this.dataService.getUsers(),
      this.dataService.getPosts()
    ).subscribe(data => {
      console.log('Data from either source', data);
    });

    // zip - Pair values in sequence
    zip(
      this.dataService.getUsers(),
      this.dataService.getPosts()
    ).subscribe(([users, posts]) => {
      console.log('Paired values', users, posts);
    });

    // withLatestFrom - Combine with latest from another
    this.dataService.getUsers().pipe(
      withLatestFrom(this.dataService.getPosts())
    ).subscribe(([users, posts]) => {
      console.log('Users with latest posts', users, posts);
    });
  }
}
```

---

## 41. How do you handle errors in Observable streams without breaking the stream?

**Approach:**
- Use catchError to handle errors
- Return fallback Observable
- Place catchError strategically

**Implementation:**

```typescript
import { Component, OnInit } from '@angular/core';
import { catchError, retry, of } from 'rxjs';

@Component({
  selector: 'app-error-handling',
  template: `
    <div *ngFor="let item of items">{{item.name}}</div>
    <div *ngIf="error">{{error}}</div>
  `
})
export class ErrorHandlingComponent implements OnInit {
  items: any[] = [];
  error = '';

  constructor(private dataService: DataService) {}

  ngOnInit() {
    // METHOD 1: Return fallback value
    this.dataService.getData().pipe(
      catchError(error => {
        console.error('Error occurred', error);
        return of([]); // Return empty array to keep stream alive
      })
    ).subscribe(data => this.items = data);

    // METHOD 2: Retry then fallback
    this.dataService.getData().pipe(
      retry(3), // Retry 3 times
      catchError(error => {
        this.error = 'Failed to load data';
        return of([]);
      })
    ).subscribe(data => this.items = data);

    // METHOD 3: Handle error per operation (keeps stream alive)
    this.searchControl.valueChanges.pipe(
      debounceTime(300),
      switchMap(term => 
        this.dataService.search(term).pipe(
          catchError(error => {
            console.error('Search failed', error);
            return of([]); // Return empty results, stream continues
          })
        )
      )
    ).subscribe(results => this.items = results);
  }
}

// Service with error handling
@Injectable({ providedIn: 'root' })
export class DataService {
  constructor(private http: HttpClient) {}

  getData(): Observable<any[]> {
    return this.http.get<any[]>('/api/data').pipe(
      catchError(error => {
        console.error('API Error', error);
        return throwError(() => new Error('Failed to fetch data'));
      })
    );
  }
}
```

---

## 42. You need to implement a search with autocomplete using Observables. How do you structure this?

**Approach:**
- FormControl for input
- valueChanges with debounceTime
- switchMap for API calls
- Handle errors and empty states

**Implementation:**

```typescript
import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { debounceTime, distinctUntilChanged, switchMap, filter, catchError } from 'rxjs/operators';
import { of } from 'rxjs';

@Component({
  selector: 'app-autocomplete',
  template: `
    <div class="search-box">
      <input 
        [formControl]="searchControl" 
        placeholder="Search..."
        (focus)="showResults = true"
        (blur)="hideResults()">
      
      <div class="results" *ngIf="showResults && (results.length > 0 || loading)">
        <div *ngIf="loading">Loading...</div>
        <div *ngFor="let result of results" 
             (mousedown)="selectResult(result)"
             class="result-item">
          {{result.name}}
        </div>
        <div *ngIf="!loading && results.length === 0">No results found</div>
      </div>
    </div>
  `,
  styles: [`
    .search-box { position: relative; }
    .results { 
      position: absolute; 
      background: white; 
      border: 1px solid #ccc;
      width: 100%;
      max-height: 300px;
      overflow-y: auto;
    }
    .result-item { 
      padding: 10px; 
      cursor: pointer;
    }
    .result-item:hover { background: #f0f0f0; }
  `]
})
export class AutocompleteComponent implements OnInit {
  searchControl = new FormControl('');
  results: any[] = [];
  loading = false;
  showResults = false;

  constructor(private searchService: SearchService) {}

  ngOnInit() {
    this.searchControl.valueChanges.pipe(
      debounceTime(300),
      distinctUntilChanged(),
      filter(term => term && term.length >= 2),
      switchMap(term => {
        this.loading = true;
        return this.searchService.search(term).pipe(
          catchError(() => {
            this.loading = false;
            return of([]);
          })
        );
      })
    ).subscribe(results => {
      this.results = results;
      this.loading = false;
      this.showResults = true;
    });
  }

  selectResult(result: any) {
    this.searchControl.setValue(result.name, { emitEvent: false });
    this.showResults = false;
  }

  hideResults() {
    setTimeout(() => this.showResults = false, 200);
  }
}

// Search service
@Injectable({ providedIn: 'root' })
export class SearchService {
  constructor(private http: HttpClient) {}

  search(term: string): Observable<any[]> {
    return this.http.get<any[]>(`/api/search?q=${term}`);
  }
}
```

---

## 43. You need to chain multiple dependent API calls. How do you implement this with RxJS?

**Approach:**
- Use switchMap for dependent calls
- Use mergeMap for parallel processing
- Use concatMap to maintain order

**Implementation:**

```typescript
import { Component, OnInit } from '@angular/core';
import { switchMap, mergeMap, concatMap } from 'rxjs/operators';

@Component({
  selector: 'app-chained-calls',
  template: `<div>{{result | json}}</div>`
})
export class ChainedCallsComponent implements OnInit {
  result: any;

  constructor(private apiService: ApiService) {}

  ngOnInit() {
    // switchMap - Cancel previous, use latest
    this.apiService.getUser(1).pipe(
      switchMap(user => this.apiService.getPosts(user.id)),
      switchMap(posts => this.apiService.getComments(posts[0].id))
    ).subscribe(comments => this.result = comments);

    // mergeMap - Process in parallel
    this.apiService.getUsers().pipe(
      mergeMap(users => this.apiService.getUserDetails(users[0].id))
    ).subscribe(details => console.log(details));

    // concatMap - Maintain order
    this.apiService.getUsers().pipe(
      concatMap(users => this.apiService.processUser(users[0]))
    ).subscribe(result => console.log(result));
  }

  // Complex example: Get user, then posts, then comments for each post
  loadUserData(userId: number) {
    this.apiService.getUser(userId).pipe(
      switchMap(user => 
        this.apiService.getPosts(user.id).pipe(
          map(posts => ({ user, posts }))
        )
      ),
      switchMap(({ user, posts }) => 
        forkJoin(posts.map(post => this.apiService.getComments(post.id))).pipe(
          map(comments => ({ user, posts, comments }))
        )
      )
    ).subscribe(result => {
      console.log('User:', result.user);
      console.log('Posts:', result.posts);
      console.log('Comments:', result.comments);
    });
  }
}

// API Service
@Injectable({ providedIn: 'root' })
export class ApiService {
  constructor(private http: HttpClient) {}

  getUser(id: number): Observable<any> {
    return this.http.get(`/api/users/${id}`);
  }

  getPosts(userId: number): Observable<any[]> {
    return this.http.get<any[]>(`/api/users/${userId}/posts`);
  }

  getComments(postId: number): Observable<any[]> {
    return this.http.get<any[]>(`/api/posts/${postId}/comments`);
  }
}
```

---

## 44. How do you share Observable subscriptions across multiple subscribers?

**Approach:**
- Use share operator for multicasting
- Use shareReplay to cache values
- Prevents multiple HTTP requests

**Implementation:**

```typescript
import { Component, OnInit } from '@angular/core';
import { share, shareReplay } from 'rxjs/operators';
import { Observable } from 'rxjs';

// Service with shared Observable
@Injectable({ providedIn: 'root' })
export class DataService {
  private data$?: Observable<any[]>;

  constructor(private http: HttpClient) {}

  // Without sharing - makes new request for each subscription
  getDataBad(): Observable<any[]> {
    return this.http.get<any[]>('/api/data');
  }

  // With share - single request, multiple subscribers
  getDataGood(): Observable<any[]> {
    if (!this.data$) {
      this.data$ = this.http.get<any[]>('/api/data').pipe(
        shareReplay(1) // Cache last value for late subscribers
      );
    }
    return this.data$;
  }

  // share vs shareReplay
  getDataWithShare(): Observable<any[]> {
    return this.http.get<any[]>('/api/data').pipe(
      share() // Shares subscription, but no replay for late subscribers
    );
  }

  getDataWithShareReplay(): Observable<any[]> {
    return this.http.get<any[]>('/api/data').pipe(
      shareReplay({ bufferSize: 1, refCount: true }) // Cache 1 value, unsubscribe when no subscribers
    );
  }
}

// Component A
@Component({
  selector: 'app-component-a',
  template: `<div>{{data$ | async | json}}</div>`
})
export class ComponentAComponent {
  data$ = this.dataService.getDataGood(); // Shares same request
  constructor(private dataService: DataService) {}
}

// Component B
@Component({
  selector: 'app-component-b',
  template: `<div>{{data$ | async | json}}</div>`
})
export class ComponentBComponent {
  data$ = this.dataService.getDataGood(); // Shares same request
  constructor(private dataService: DataService) {}
}

// Example: Multiple subscriptions without sharing
@Component({
  selector: 'app-without-share',
  template: `
    <div>Count A: {{countA}}</div>
    <div>Count B: {{countB}}</div>
  `
})
export class WithoutShareComponent implements OnInit {
  countA = 0;
  countB = 0;

  ngOnInit() {
    const source$ = interval(1000); // Creates new interval for each subscription
    
    source$.subscribe(val => this.countA = val); // Independent stream
    source$.subscribe(val => this.countB = val); // Independent stream
  }
}

// Example: Multiple subscriptions with sharing
@Component({
  selector: 'app-with-share',
  template: `
    <div>Count A: {{countA}}</div>
    <div>Count B: {{countB}}</div>
  `
})
export class WithShareComponent implements OnInit {
  countA = 0;
  countB = 0;

  ngOnInit() {
    const source$ = interval(1000).pipe(share()); // Shared stream
    
    source$.subscribe(val => this.countA = val); // Same stream
    source$.subscribe(val => this.countB = val); // Same stream
  }
}
```

# Angular Interview Questions - Answers (Questions 45-50)
## Authentication & Authorization

---

## 45. How do you implement authentication with JWT in an Angular application?

**Approach:**
- Store JWT in localStorage/sessionStorage
- Auth service for login/logout
- HTTP interceptor to attach token
- Decode token for user info

**Implementation:**

```typescript
// auth.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, BehaviorSubject, tap } from 'rxjs';
import { Router } from '@angular/router';

interface AuthResponse {
  token: string;
  user: any;
}

@Injectable({ providedIn: 'root' })
export class AuthService {
  private currentUserSubject = new BehaviorSubject<any>(null);
  currentUser$ = this.currentUserSubject.asObservable();

  constructor(private http: HttpClient, private router: Router) {
    const user = this.getUserFromToken();
    if (user) this.currentUserSubject.next(user);
  }

  login(email: string, password: string): Observable<AuthResponse> {
    return this.http.post<AuthResponse>('/api/auth/login', { email, password })
      .pipe(
        tap(response => {
          localStorage.setItem('token', response.token);
          this.currentUserSubject.next(response.user);
        })
      );
  }

  logout() {
    localStorage.removeItem('token');
    this.currentUserSubject.next(null);
    this.router.navigate(['/login']);
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  isAuthenticated(): boolean {
    const token = this.getToken();
    if (!token) return false;
    
    // Check if token is expired
    const payload = this.decodeToken(token);
    return payload.exp * 1000 > Date.now();
  }

  private decodeToken(token: string): any {
    try {
      return JSON.parse(atob(token.split('.')[1]));
    } catch {
      return null;
    }
  }

  private getUserFromToken(): any {
    const token = this.getToken();
    if (!token) return null;
    return this.decodeToken(token);
  }
}

// auth.interceptor.ts
import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler } from '@angular/common/http';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private auth: AuthService) {}

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    const token = this.auth.getToken();
    
    if (token) {
      req = req.clone({
        setHeaders: { Authorization: `Bearer ${token}` }
      });
    }
    
    return next.handle(req);
  }
}

// login.component.ts
@Component({
  selector: 'app-login',
  template: `
    <form [formGroup]="form" (ngSubmit)="onSubmit()">
      <input formControlName="email" placeholder="Email">
      <input formControlName="password" type="password" placeholder="Password">
      <button [disabled]="form.invalid">Login</button>
      <div *ngIf="error">{{error}}</div>
    </form>
  `
})
export class LoginComponent {
  form = this.fb.group({
    email: ['', [Validators.required, Validators.email]],
    password: ['', Validators.required]
  });
  error = '';

  constructor(
    private fb: FormBuilder,
    private auth: AuthService,
    private router: Router
  ) {}

  onSubmit() {
    if (this.form.valid) {
      const { email, password } = this.form.value;
      this.auth.login(email!, password!).subscribe({
        next: () => this.router.navigate(['/dashboard']),
        error: err => this.error = 'Invalid credentials'
      });
    }
  }
}
```

---

## 46. You need to protect routes from unauthenticated users. How do you implement this?

**Approach:**
- Create CanActivate guard
- Check authentication status
- Redirect to login if not authenticated

**Implementation:**

```typescript
// auth.guard.ts
import { Injectable } from '@angular/core';
import { CanActivate, Router, UrlTree } from '@angular/router';
import { AuthService } from './auth.service';

@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate {
  constructor(private auth: AuthService, private router: Router) {}

  canActivate(): boolean | UrlTree {
    if (this.auth.isAuthenticated()) {
      return true;
    }
    
    return this.router.createUrlTree(['/login']);
  }
}

// app-routing.module.ts
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
  },
  {
    path: 'admin',
    loadChildren: () => import('./admin/admin.module').then(m => m.AdminModule),
    canActivate: [AuthGuard]
  }
];

// Redirect to intended URL after login
@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate {
  constructor(private auth: AuthService, private router: Router) {}

  canActivate(route: ActivatedRouteSnapshot): boolean {
    if (this.auth.isAuthenticated()) {
      return true;
    }
    
    // Store intended URL
    this.router.navigate(['/login'], {
      queryParams: { returnUrl: route.url }
    });
    return false;
  }
}

// login.component.ts - Redirect after login
onSubmit() {
  this.auth.login(email, password).subscribe({
    next: () => {
      const returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/dashboard';
      this.router.navigateByUrl(returnUrl);
    }
  });
}
```

---

## 47. How do you handle token refresh when it expires during user session?

**Approach:**
- Interceptor catches 401 errors
- Attempt token refresh
- Retry original request
- Logout if refresh fails

**Implementation:**

```typescript
// auth.service.ts
@Injectable({ providedIn: 'root' })
export class AuthService {
  private refreshTokenInProgress = false;
  private refreshTokenSubject = new BehaviorSubject<string | null>(null);

  constructor(private http: HttpClient) {}

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  getRefreshToken(): string | null {
    return localStorage.getItem('refreshToken');
  }

  refreshToken(): Observable<any> {
    return this.http.post('/api/auth/refresh', {
      refreshToken: this.getRefreshToken()
    }).pipe(
      tap((response: any) => {
        localStorage.setItem('token', response.token);
      })
    );
  }

  logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('refreshToken');
  }
}

// token-refresh.interceptor.ts
import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpErrorResponse } from '@angular/common/http';
import { catchError, switchMap, filter, take } from 'rxjs/operators';
import { throwError, BehaviorSubject } from 'rxjs';

@Injectable()
export class TokenRefreshInterceptor implements HttpInterceptor {
  private isRefreshing = false;
  private refreshTokenSubject = new BehaviorSubject<string | null>(null);

  constructor(private auth: AuthService, private router: Router) {}

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    return next.handle(req).pipe(
      catchError((error: HttpErrorResponse) => {
        if (error.status === 401 && !req.url.includes('/auth/refresh')) {
          return this.handle401Error(req, next);
        }
        return throwError(() => error);
      })
    );
  }

  private handle401Error(req: HttpRequest<any>, next: HttpHandler) {
    if (!this.isRefreshing) {
      this.isRefreshing = true;
      this.refreshTokenSubject.next(null);

      return this.auth.refreshToken().pipe(
        switchMap((response: any) => {
          this.isRefreshing = false;
          this.refreshTokenSubject.next(response.token);
          return next.handle(this.addToken(req, response.token));
        }),
        catchError(err => {
          this.isRefreshing = false;
          this.auth.logout();
          this.router.navigate(['/login']);
          return throwError(() => err);
        })
      );
    } else {
      // Wait for token refresh to complete
      return this.refreshTokenSubject.pipe(
        filter(token => token !== null),
        take(1),
        switchMap(token => next.handle(this.addToken(req, token!)))
      );
    }
  }

  private addToken(req: HttpRequest<any>, token: string): HttpRequest<any> {
    return req.clone({
      setHeaders: { Authorization: `Bearer ${token}` }
    });
  }
}
```

---

## 48. You need to redirect users based on their role after login. How do you structure this?

**Approach:**
- Get user role from token/API
- Navigate based on role
- Store role in auth service

**Implementation:**

```typescript
// auth.service.ts
@Injectable({ providedIn: 'root' })
export class AuthService {
  constructor(private http: HttpClient) {}

  login(email: string, password: string): Observable<AuthResponse> {
    return this.http.post<AuthResponse>('/api/auth/login', { email, password })
      .pipe(
        tap(response => {
          localStorage.setItem('token', response.token);
          localStorage.setItem('role', response.user.role);
        })
      );
  }

  getRole(): string {
    return localStorage.getItem('role') || '';
  }

  hasRole(role: string): boolean {
    return this.getRole() === role;
  }

  hasAnyRole(roles: string[]): boolean {
    return roles.includes(this.getRole());
  }
}

// login.component.ts
@Component({
  selector: 'app-login',
  template: `
    <form [formGroup]="form" (ngSubmit)="onSubmit()">
      <input formControlName="email">
      <input formControlName="password" type="password">
      <button>Login</button>
    </form>
  `
})
export class LoginComponent {
  form = this.fb.group({
    email: ['', Validators.required],
    password: ['', Validators.required]
  });

  constructor(
    private fb: FormBuilder,
    private auth: AuthService,
    private router: Router
  ) {}

  onSubmit() {
    const { email, password } = this.form.value;
    this.auth.login(email!, password!).subscribe({
      next: (response) => {
        this.redirectByRole(response.user.role);
      }
    });
  }

  private redirectByRole(role: string) {
    const routes: { [key: string]: string } = {
      'admin': '/admin/dashboard',
      'manager': '/manager/dashboard',
      'user': '/user/dashboard'
    };
    
    this.router.navigate([routes[role] || '/dashboard']);
  }
}

// role.guard.ts - Guard for role-based access
@Injectable({ providedIn: 'root' })
export class RoleGuard implements CanActivate {
  constructor(private auth: AuthService, private router: Router) {}

  canActivate(route: ActivatedRouteSnapshot): boolean {
    const requiredRoles = route.data['roles'] as string[];
    
    if (this.auth.hasAnyRole(requiredRoles)) {
      return true;
    }
    
    this.router.navigate(['/unauthorized']);
    return false;
  }
}

// app-routing.module.ts
const routes: Routes = [
  {
    path: 'admin',
    component: AdminComponent,
    canActivate: [AuthGuard, RoleGuard],
    data: { roles: ['admin'] }
  },
  {
    path: 'manager',
    component: ManagerComponent,
    canActivate: [AuthGuard, RoleGuard],
    data: { roles: ['admin', 'manager'] }
  }
];
```

---

## 49. How do you handle unauthorized responses (401/403) globally?

**Approach:**
- HTTP interceptor catches errors
- Handle 401 (unauthenticated) and 403 (forbidden)
- Redirect or show appropriate message

**Implementation:**

```typescript
// error.interceptor.ts
import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpErrorResponse } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {
  constructor(
    private auth: AuthService,
    private router: Router,
    private notification: NotificationService
  ) {}

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    return next.handle(req).pipe(
      catchError((error: HttpErrorResponse) => {
        if (error.status === 401) {
          // Unauthorized - token invalid or expired
          this.auth.logout();
          this.router.navigate(['/login']);
          this.notification.error('Session expired. Please login again.');
        } else if (error.status === 403) {
          // Forbidden - user doesn't have permission
          this.router.navigate(['/access-denied']);
          this.notification.error('You do not have permission to access this resource.');
        }
        
        return throwError(() => error);
      })
    );
  }
}

// app.module.ts
@NgModule({
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true }
  ]
})
export class AppModule {}

// access-denied.component.ts
@Component({
  selector: 'app-access-denied',
  template: `
    <div class="access-denied">
      <h1>403 - Access Denied</h1>
      <p>You don't have permission to access this resource.</p>
      <button (click)="goBack()">Go Back</button>
      <a routerLink="/dashboard">Go to Dashboard</a>
    </div>
  `
})
export class AccessDeniedComponent {
  constructor(private location: Location) {}

  goBack() {
    this.location.back();
  }
}
```

---

## 50. You need to show/hide UI elements based on user permissions. How do you implement this?

**Approach:**
- Create directive for permission check
- Auth service provides permission methods
- Apply directive to elements
- Alternative: use *ngIf with permission check

**Implementation:**

```typescript
// has-permission.directive.ts
import { Directive, Input, TemplateRef, ViewContainerRef, OnInit } from '@angular/core';
import { AuthService } from './auth.service';

@Directive({
  selector: '[hasPermission]'
})
export class HasPermissionDirective implements OnInit {
  @Input() hasPermission: string | string[] = [];

  constructor(
    private templateRef: TemplateRef<any>,
    private viewContainer: ViewContainerRef,
    private auth: AuthService
  ) {}

  ngOnInit() {
    const permissions = Array.isArray(this.hasPermission) 
      ? this.hasPermission 
      : [this.hasPermission];

    if (this.auth.hasAnyPermission(permissions)) {
      this.viewContainer.createEmbeddedView(this.templateRef);
    } else {
      this.viewContainer.clear();
    }
  }
}

// auth.service.ts
@Injectable({ providedIn: 'root' })
export class AuthService {
  private permissions: string[] = [];

  constructor() {
    this.loadPermissions();
  }

  private loadPermissions() {
    const token = this.getToken();
    if (token) {
      const decoded = this.decodeToken(token);
      this.permissions = decoded.permissions || [];
    }
  }

  hasPermission(permission: string): boolean {
    return this.permissions.includes(permission);
  }

  hasAnyPermission(permissions: string[]): boolean {
    return permissions.some(p => this.permissions.includes(p));
  }

  hasAllPermissions(permissions: string[]): boolean {
    return permissions.every(p => this.permissions.includes(p));
  }
}

// Usage in templates
@Component({
  selector: 'app-dashboard',
  template: `
    <!-- Using directive -->
    <button *hasPermission="'delete_user'">Delete User</button>
    
    <div *hasPermission="['edit_post', 'delete_post']">
      <button>Edit</button>
      <button>Delete</button>
    </div>

    <!-- Using ngIf -->
    <button *ngIf="canDelete()">Delete</button>
    
    <!-- Multiple permissions -->
    <div *ngIf="auth.hasAllPermissions(['view_reports', 'export_data'])">
      <button>Export Report</button>
    </div>
  `
})
export class DashboardComponent {
  constructor(public auth: AuthService) {}

  canDelete(): boolean {
    return this.auth.hasPermission('delete_user');
  }
}

// has-role.directive.ts - Alternative for role-based
@Directive({
  selector: '[hasRole]'
})
export class HasRoleDirective implements OnInit {
  @Input() hasRole: string | string[] = [];

  constructor(
    private templateRef: TemplateRef<any>,
    private viewContainer: ViewContainerRef,
    private auth: AuthService
  ) {}

  ngOnInit() {
    const roles = Array.isArray(this.hasRole) ? this.hasRole : [this.hasRole];

    if (this.auth.hasAnyRole(roles)) {
      this.viewContainer.createEmbeddedView(this.templateRef);
    } else {
      this.viewContainer.clear();
    }
  }
}

// Usage
@Component({
  template: `
    <div *hasRole="'admin'">Admin Only Content</div>
    <div *hasRole="['admin', 'manager']">Admin or Manager Content</div>
  `
})
export class ExampleComponent {}
```

# Angular Interview Questions - Answers (Questions 51-60)
## State Management & Testing

---

## 51. You need to implement global state management. When would you use NgRx vs Services?

**When to use Services:**
- Simple to medium complexity
- Limited shared state
- Quick development
- Small team

**When to use NgRx:**
- Large applications
- Complex state interactions
- Time-travel debugging needed
- Strict unidirectional data flow
- Multiple sources updating same state

**Implementation:**

```typescript
// SERVICE-BASED STATE (Simple approach)
@Injectable({ providedIn: 'root' })
export class StateService {
  private state = new BehaviorSubject<AppState>({
    users: [],
    loading: false
  });

  state$ = this.state.asObservable();

  updateUsers(users: any[]) {
    this.state.next({ ...this.state.value, users });
  }
}

// NGRX-BASED STATE (Complex approach)
// actions
export const loadUsers = createAction('[User] Load Users');
export const loadUsersSuccess = createAction(
  '[User] Load Users Success',
  props<{ users: any[] }>()
);

// reducer
export const userReducer = createReducer(
  initialState,
  on(loadUsers, state => ({ ...state, loading: true })),
  on(loadUsersSuccess, (state, { users }) => ({ ...state, users, loading: false }))
);

// selector
export const selectUsers = createSelector(
  (state: AppState) => state.users,
  users => users
);

// effects
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

  constructor(private actions$: Actions, private userService: UserService) {}
}

// Usage in component
@Component({
  selector: 'app-users',
  template: `<div *ngFor="let user of users$ | async">{{user.name}}</div>`
})
export class UsersComponent {
  users$ = this.store.select(selectUsers);

  constructor(private store: Store) {
    this.store.dispatch(loadUsers());
  }
}
```

---

## 52. You need to persist state across page refreshes. How do you implement this?

**Approach:**
- Store state in localStorage
- Load on initialization
- Save on state changes

**Implementation:**

```typescript
// state.service.ts
@Injectable({ providedIn: 'root' })
export class StateService {
  private readonly STORAGE_KEY = 'app_state';
  private state = new BehaviorSubject<AppState>(this.loadState());

  state$ = this.state.asObservable();

  constructor() {
    // Save state on every change
    this.state$.subscribe(state => this.saveState(state));
  }

  private loadState(): AppState {
    const saved = localStorage.getItem(this.STORAGE_KEY);
    return saved ? JSON.parse(saved) : this.getDefaultState();
  }

  private saveState(state: AppState) {
    localStorage.setItem(this.STORAGE_KEY, JSON.stringify(state));
  }

  private getDefaultState(): AppState {
    return { users: [], cart: [], preferences: {} };
  }

  updateState(updates: Partial<AppState>) {
    this.state.next({ ...this.state.value, ...updates });
  }

  clearState() {
    localStorage.removeItem(this.STORAGE_KEY);
    this.state.next(this.getDefaultState());
  }
}

// With NgRx - use meta-reducer
export function localStorageSyncReducer(reducer: ActionReducer<any>): ActionReducer<any> {
  return (state, action) => {
    const nextState = reducer(state, action);
    localStorage.setItem('app_state', JSON.stringify(nextState));
    return nextState;
  };
}

export const metaReducers: MetaReducer<any>[] = [localStorageSyncReducer];
```

---

## 53. How do you handle optimistic updates when posting data to API?

**Approach:**
- Update UI immediately
- Make API call
- Revert on error

**Implementation:**

```typescript
@Injectable({ providedIn: 'root' })
export class TodoService {
  private todos = new BehaviorSubject<Todo[]>([]);
  todos$ = this.todos.asObservable();

  constructor(private http: HttpClient) {}

  addTodo(todo: Todo) {
    const previousState = this.todos.value;
    
    // Optimistic update
    this.todos.next([...previousState, todo]);

    // API call
    this.http.post<Todo>('/api/todos', todo).subscribe({
      next: (savedTodo) => {
        // Update with server response (has ID)
        const updated = this.todos.value.map(t => 
          t === todo ? savedTodo : t
        );
        this.todos.next(updated);
      },
      error: () => {
        // Revert on error
        this.todos.next(previousState);
        alert('Failed to add todo');
      }
    });
  }

  deleteTodo(id: number) {
    const previousState = this.todos.value;
    
    // Optimistic delete
    this.todos.next(previousState.filter(t => t.id !== id));

    this.http.delete(`/api/todos/${id}`).subscribe({
      error: () => {
        // Revert on error
        this.todos.next(previousState);
        alert('Failed to delete todo');
      }
    });
  }

  updateTodo(updated: Todo) {
    const previousState = this.todos.value;
    
    // Optimistic update
    this.todos.next(
      previousState.map(t => t.id === updated.id ? updated : t)
    );

    this.http.put<Todo>(`/api/todos/${updated.id}`, updated).subscribe({
      error: () => {
        this.todos.next(previousState);
        alert('Failed to update todo');
      }
    });
  }
}
```

---

## 54. You need to sync state between localStorage and application state. How do you implement this?

**Approach:**
- Listen to storage events
- Update state on changes
- Handle cross-tab synchronization

**Implementation:**

```typescript
@Injectable({ providedIn: 'root' })
export class SyncStateService {
  private readonly STORAGE_KEY = 'app_state';
  private state = new BehaviorSubject<AppState>(this.loadFromStorage());
  state$ = this.state.asObservable();

  constructor() {
    // Save to localStorage on state changes
    this.state$.subscribe(state => {
      localStorage.setItem(this.STORAGE_KEY, JSON.stringify(state));
    });

    // Listen for changes from other tabs
    window.addEventListener('storage', (event) => {
      if (event.key === this.STORAGE_KEY && event.newValue) {
        const newState = JSON.parse(event.newValue);
        this.state.next(newState);
      }
    });
  }

  private loadFromStorage(): AppState {
    const saved = localStorage.getItem(this.STORAGE_KEY);
    return saved ? JSON.parse(saved) : { items: [] };
  }

  updateState(updates: Partial<AppState>) {
    this.state.next({ ...this.state.value, ...updates });
  }
}

// Component usage
@Component({
  selector: 'app-sync-demo',
  template: `
    <div>Items: {{(state$ | async)?.items.length}}</div>
    <button (click)="addItem()">Add Item</button>
  `
})
export class SyncDemoComponent {
  state$ = this.syncState.state$;

  constructor(private syncState: SyncStateService) {}

  addItem() {
    const current = this.syncState.state$.value;
    this.syncState.updateState({
      items: [...current.items, { id: Date.now(), name: 'New Item' }]
    });
  }
}
```

---

## 55. How would you implement a shopping cart that persists across sessions?

**Approach:**
- Cart service with BehaviorSubject
- Store in localStorage
- Load on initialization

**Implementation:**

```typescript
interface CartItem {
  id: number;
  name: string;
  price: number;
  quantity: number;
}

@Injectable({ providedIn: 'root' })
export class CartService {
  private readonly CART_KEY = 'shopping_cart';
  private cart = new BehaviorSubject<CartItem[]>(this.loadCart());
  
  cart$ = this.cart.asObservable();
  total$ = this.cart$.pipe(
    map(items => items.reduce((sum, item) => sum + item.price * item.quantity, 0))
  );
  itemCount$ = this.cart$.pipe(
    map(items => items.reduce((sum, item) => sum + item.quantity, 0))
  );

  constructor() {
    this.cart$.subscribe(cart => this.saveCart(cart));
  }

  private loadCart(): CartItem[] {
    const saved = localStorage.getItem(this.CART_KEY);
    return saved ? JSON.parse(saved) : [];
  }

  private saveCart(cart: CartItem[]) {
    localStorage.setItem(this.CART_KEY, JSON.stringify(cart));
  }

  addItem(product: any) {
    const current = this.cart.value;
    const existing = current.find(item => item.id === product.id);

    if (existing) {
      this.updateQuantity(product.id, existing.quantity + 1);
    } else {
      this.cart.next([...current, { ...product, quantity: 1 }]);
    }
  }

  removeItem(id: number) {
    this.cart.next(this.cart.value.filter(item => item.id !== id));
  }

  updateQuantity(id: number, quantity: number) {
    if (quantity <= 0) {
      this.removeItem(id);
      return;
    }

    this.cart.next(
      this.cart.value.map(item =>
        item.id === id ? { ...item, quantity } : item
      )
    );
  }

  clearCart() {
    this.cart.next([]);
  }
}

// Component
@Component({
  selector: 'app-cart',
  template: `
    <div *ngFor="let item of cart$ | async">
      {{item.name}} - ${{item.price}} x {{item.quantity}}
      <button (click)="updateQty(item.id, item.quantity - 1)">-</button>
      <button (click)="updateQty(item.id, item.quantity + 1)">+</button>
      <button (click)="remove(item.id)">Remove</button>
    </div>
    <div>Total: ${{total$ | async}}</div>
    <button (click)="clear()">Clear Cart</button>
  `
})
export class CartComponent {
  cart$ = this.cartService.cart$;
  total$ = this.cartService.total$;

  constructor(private cartService: CartService) {}

  updateQty(id: number, qty: number) {
    this.cartService.updateQuantity(id, qty);
  }

  remove(id: number) {
    this.cartService.removeItem(id);
  }

  clear() {
    this.cartService.clearCart();
  }
}
```

---

## 56. How would you test a component that makes HTTP calls?

**Approach:**
- Use HttpClientTestingModule
- Mock HTTP requests
- Verify component behavior

**Implementation:**

```typescript
// user.component.ts
@Component({
  selector: 'app-user',
  template: `<div *ngFor="let user of users">{{user.name}}</div>`
})
export class UserComponent implements OnInit {
  users: any[] = [];

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.http.get<any[]>('/api/users').subscribe(users => this.users = users);
  }
}

// user.component.spec.ts
describe('UserComponent', () => {
  let component: UserComponent;
  let fixture: ComponentFixture<UserComponent>;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserComponent],
      imports: [HttpClientTestingModule]
    });

    fixture = TestBed.createComponent(UserComponent);
    component = fixture.componentInstance;
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify(); // Verify no outstanding requests
  });

  it('should load users on init', () => {
    const mockUsers = [{ id: 1, name: 'John' }, { id: 2, name: 'Jane' }];

    fixture.detectChanges(); // Triggers ngOnInit

    const req = httpMock.expectOne('/api/users');
    expect(req.request.method).toBe('GET');
    
    req.flush(mockUsers); // Send mock response

    expect(component.users).toEqual(mockUsers);
  });

  it('should handle error', () => {
    fixture.detectChanges();

    const req = httpMock.expectOne('/api/users');
    req.error(new ErrorEvent('Network error'));

    expect(component.users).toEqual([]);
  });
});
```

---

## 57. You need to test a service that depends on other services. How do you set this up?

**Approach:**
- Mock dependencies with spies
- Use TestBed for injection
- Test service methods

**Implementation:**

```typescript
// user.service.ts
@Injectable({ providedIn: 'root' })
export class UserService {
  constructor(private http: HttpClient, private auth: AuthService) {}

  getUsers(): Observable<any[]> {
    return this.http.get<any[]>('/api/users');
  }

  getCurrentUser(): Observable<any> {
    const userId = this.auth.getUserId();
    return this.http.get(`/api/users/${userId}`);
  }
}

// user.service.spec.ts
describe('UserService', () => {
  let service: UserService;
  let httpMock: HttpTestingController;
  let authSpy: jasmine.SpyObj<AuthService>;

  beforeEach(() => {
    const spy = jasmine.createSpyObj('AuthService', ['getUserId']);

    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [
        UserService,
        { provide: AuthService, useValue: spy }
      ]
    });

    service = TestBed.inject(UserService);
    httpMock = TestBed.inject(HttpTestingController);
    authSpy = TestBed.inject(AuthService) as jasmine.SpyObj<AuthService>;
  });

  it('should get users', () => {
    const mockUsers = [{ id: 1, name: 'John' }];

    service.getUsers().subscribe(users => {
      expect(users).toEqual(mockUsers);
    });

    const req = httpMock.expectOne('/api/users');
    req.flush(mockUsers);
  });

  it('should get current user', () => {
    authSpy.getUserId.and.returnValue(123);
    const mockUser = { id: 123, name: 'John' };

    service.getCurrentUser().subscribe(user => {
      expect(user).toEqual(mockUser);
    });

    expect(authSpy.getUserId).toHaveBeenCalled();
    const req = httpMock.expectOne('/api/users/123');
    req.flush(mockUser);
  });
});
```

---

## 58. How do you test components with async operations?

**Approach:**
- Use fakeAsync and tick
- Use async and whenStable
- Use done callback

**Implementation:**

```typescript
// async.component.ts
@Component({
  selector: 'app-async',
  template: `<div>{{message}}</div>`
})
export class AsyncComponent {
  message = '';

  loadData() {
    setTimeout(() => {
      this.message = 'Loaded';
    }, 1000);
  }

  loadDataPromise(): Promise<string> {
    return Promise.resolve('Data');
  }
}

// async.component.spec.ts
describe('AsyncComponent', () => {
  let component: AsyncComponent;
  let fixture: ComponentFixture<AsyncComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AsyncComponent]
    });
    fixture = TestBed.createComponent(AsyncComponent);
    component = fixture.componentInstance;
  });

  // METHOD 1: fakeAsync with tick
  it('should load data after timeout', fakeAsync(() => {
    component.loadData();
    expect(component.message).toBe('');

    tick(1000); // Advance time by 1000ms

    expect(component.message).toBe('Loaded');
  }));

  // METHOD 2: async with whenStable
  it('should load data from promise', async(() => {
    component.loadDataPromise().then(data => {
      component.message = data;
    });

    fixture.whenStable().then(() => {
      expect(component.message).toBe('Data');
    });
  }));

  // METHOD 3: done callback
  it('should load data with done', (done) => {
    component.loadDataPromise().then(data => {
      expect(data).toBe('Data');
      done();
    });
  });
});
```

---

## 59. You need to test route guards. How do you structure your tests?

**Approach:**
- Mock dependencies
- Call canActivate directly
- Test navigation behavior

**Implementation:**

```typescript
// auth.guard.ts
@Injectable({ providedIn: 'root' })
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

// auth.guard.spec.ts
describe('AuthGuard', () => {
  let guard: AuthGuard;
  let authSpy: jasmine.SpyObj<AuthService>;
  let routerSpy: jasmine.SpyObj<Router>;

  beforeEach(() => {
    const authSpyObj = jasmine.createSpyObj('AuthService', ['isAuthenticated']);
    const routerSpyObj = jasmine.createSpyObj('Router', ['navigate']);

    TestBed.configureTestingModule({
      providers: [
        AuthGuard,
        { provide: AuthService, useValue: authSpyObj },
        { provide: Router, useValue: routerSpyObj }
      ]
    });

    guard = TestBed.inject(AuthGuard);
    authSpy = TestBed.inject(AuthService) as jasmine.SpyObj<AuthService>;
    routerSpy = TestBed.inject(Router) as jasmine.SpyObj<Router>;
  });

  it('should allow access when authenticated', () => {
    authSpy.isAuthenticated.and.returnValue(true);

    expect(guard.canActivate()).toBe(true);
    expect(routerSpy.navigate).not.toHaveBeenCalled();
  });

  it('should deny access and redirect when not authenticated', () => {
    authSpy.isAuthenticated.and.returnValue(false);

    expect(guard.canActivate()).toBe(false);
    expect(routerSpy.navigate).toHaveBeenCalledWith(['/login']);
  });
});
```

---

## 60. How would you test custom validators?

**Approach:**
- Create FormControl with validator
- Test valid and invalid values
- Check errors object

**Implementation:**

```typescript
// validators.ts
export function emailValidator(): ValidatorFn {
  return (control: AbstractControl): ValidationErrors | null => {
    const email = control.value;
    if (!email) return null;
    
    const valid = /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);
    return valid ? null : { invalidEmail: true };
  };
}

export function asyncUsernameValidator(userService: UserService): AsyncValidatorFn {
  return (control: AbstractControl): Observable<ValidationErrors | null> => {
    if (!control.value) return of(null);
    
    return userService.checkUsername(control.value).pipe(
      map(exists => exists ? { usernameTaken: true } : null),
      catchError(() => of(null))
    );
  };
}

// validators.spec.ts
describe('Validators', () => {
  // Sync validator test
  it('should validate email', () => {
    const control = new FormControl('');
    const validator = emailValidator();

    control.setValue('invalid');
    expect(validator(control)).toEqual({ invalidEmail: true });

    control.setValue('valid@email.com');
    expect(validator(control)).toBeNull();
  });

  // Async validator test
  it('should validate username availability', fakeAsync(() => {
    const userService = jasmine.createSpyObj('UserService', ['checkUsername']);
    userService.checkUsername.and.returnValue(of(true));

    const control = new FormControl('', [], [asyncUsernameValidator(userService)]);
    control.setValue('taken');

    tick();

    expect(control.errors).toEqual({ usernameTaken: true });
    expect(control.status).toBe('INVALID');
  }));

  it('should pass when username available', fakeAsync(() => {
    const userService = jasmine.createSpyObj('UserService', ['checkUsername']);
    userService.checkUsername.and.returnValue(of(false));

    const control = new FormControl('', [], [asyncUsernameValidator(userService)]);
    control.setValue('available');

    tick();

    expect(control.errors).toBeNull();
    expect(control.status).toBe('VALID');
  }));
});
```

# Angular Interview Questions - Answers (Questions 61-70)
## Change Detection & Lifecycle, Directives & Pipes

---

## 61. How do you implement **conditional rendering** for loading, error, and success states in Angular templates?

**Approach:**
- Use boolean flags for states
- ngIf with else templates
- Or use state enum with ngSwitch

**Implementation:**

```typescript
// METHOD 1: Boolean flags
@Component({
  selector: 'app-data',
  template: `
    <div *ngIf="loading">Loading...</div>
    <div *ngIf="error">Error: {{error}}</div>
    <div *ngIf="data && !loading">
      <div *ngFor="let item of data">{{item.name}}</div>
    </div>
  `
})
export class DataComponent implements OnInit {
  data: any[] = [];
  loading = false;
  error = '';

  constructor(private dataService: DataService) {}

  ngOnInit() {
    this.loading = true;
    this.dataService.getData().subscribe({
      next: (data) => {
        this.data = data;
        this.loading = false;
      },
      error: (err) => {
        this.error = err.message;
        this.loading = false;
      }
    });
  }
}

// METHOD 2: Using ng-template with else
@Component({
  selector: 'app-data-template',
  template: `
    <div *ngIf="loading; else content">Loading...</div>
    
    <ng-template #content>
      <div *ngIf="error; else success">Error: {{error}}</div>
    </ng-template>
    
    <ng-template #success>
      <div *ngFor="let item of data">{{item.name}}</div>
    </ng-template>
  `
})
export class DataTemplateComponent {}

// METHOD 3: State enum with ngSwitch
enum LoadState {
  Loading = 'loading',
  Success = 'success',
  Error = 'error'
}

@Component({
  selector: 'app-data-switch',
  template: `
    <div [ngSwitch]="state">
      <div *ngSwitchCase="'loading'">Loading...</div>
      <div *ngSwitchCase="'error'">Error: {{error}}</div>
      <div *ngSwitchCase="'success'">
        <div *ngFor="let item of data">{{item.name}}</div>
      </div>
    </div>
  `
})
export class DataSwitchComponent implements OnInit {
  state = LoadState.Loading;
  data: any[] = [];
  error = '';

  constructor(private dataService: DataService) {}

  ngOnInit() {
    this.dataService.getData().subscribe({
      next: (data) => {
        this.data = data;
        this.state = LoadState.Success;
      },
      error: (err) => {
        this.error = err.message;
        this.state = LoadState.Error;
      }
    });
  }
}
```

---

## 62. You need to run code after view is fully initialized. Which lifecycle hook do you use?

**Approach:**
- Use ngAfterViewInit lifecycle hook
- Access ViewChild elements here
- For content projection, use ngAfterContentInit

**Implementation:**

```typescript
@Component({
  selector: 'app-chart',
  template: `<div #chartContainer></div>`
})
export class ChartComponent implements AfterViewInit {
  @ViewChild('chartContainer') chartContainer!: ElementRef;
  @ViewChild(ChildComponent) childComponent!: ChildComponent;

  ngAfterViewInit() {
    // View is fully initialized, safe to access DOM
    console.log(this.chartContainer.nativeElement);
    
    // Initialize third-party library
    this.initChart();
    
    // Access child component
    this.childComponent.doSomething();
  }

  private initChart() {
    // Chart library initialization
  }
}

// For content projection
@Component({
  selector: 'app-wrapper',
  template: `
    <div class="wrapper">
      <ng-content></ng-content>
    </div>
  `
})
export class WrapperComponent implements AfterContentInit {
  @ContentChild(ChildComponent) projectedChild!: ChildComponent;

  ngAfterContentInit() {
    // Projected content is initialized
    console.log(this.projectedChild);
  }
}
```

---

## 63. How do you detect changes in @Input properties?

**Approach:**
- Use ngOnChanges lifecycle hook
- Or use Input setter
- Check SimpleChanges for previous/current values

**Implementation:**

```typescript
// METHOD 1: ngOnChanges
@Component({
  selector: 'app-user',
  template: `<div>{{user?.name}}</div>`
})
export class UserComponent implements OnChanges {
  @Input() user: any;
  @Input() settings: any;

  ngOnChanges(changes: SimpleChanges) {
    if (changes['user']) {
      const change = changes['user'];
      console.log('Previous:', change.previousValue);
      console.log('Current:', change.currentValue);
      console.log('First change:', change.firstChange);
      
      if (!change.firstChange) {
        this.onUserChanged(change.currentValue);
      }
    }

    if (changes['settings']) {
      this.updateSettings(changes['settings'].currentValue);
    }
  }

  private onUserChanged(user: any) {
    // Handle user change
  }

  private updateSettings(settings: any) {
    // Handle settings change
  }
}

// METHOD 2: Input setter
@Component({
  selector: 'app-user-setter',
  template: `<div>{{userName}}</div>`
})
export class UserSetterComponent {
  private _user: any;
  userName = '';

  @Input()
  set user(value: any) {
    this._user = value;
    this.userName = value?.name || '';
    this.processUser(value);
  }

  get user(): any {
    return this._user;
  }

  private processUser(user: any) {
    // Process immediately when input changes
  }
}
```

---

## 64. You need to perform cleanup when component is destroyed. How do you implement this?

**Approach:**
- Use ngOnDestroy lifecycle hook
- Unsubscribe from Observables
- Clear timers and event listeners

**Implementation:**

```typescript
@Component({
  selector: 'app-cleanup',
  template: `<div>{{data}}</div>`
})
export class CleanupComponent implements OnInit, OnDestroy {
  data: any;
  private destroy$ = new Subject<void>();
  private intervalId?: number;

  constructor(private dataService: DataService) {}

  ngOnInit() {
    // Subscription with takeUntil
    this.dataService.getData()
      .pipe(takeUntil(this.destroy$))
      .subscribe(data => this.data = data);

    // Timer
    this.intervalId = window.setInterval(() => {
      console.log('Tick');
    }, 1000);

    // Event listener
    window.addEventListener('resize', this.onResize);
  }

  ngOnDestroy() {
    // Unsubscribe from all observables
    this.destroy$.next();
    this.destroy$.complete();

    // Clear timer
    if (this.intervalId) {
      clearInterval(this.intervalId);
    }

    // Remove event listener
    window.removeEventListener('resize', this.onResize);
  }

  private onResize = () => {
    console.log('Window resized');
  };
}

// Alternative: Manual subscription management
@Component({
  selector: 'app-manual-cleanup',
  template: `<div>{{data}}</div>`
})
export class ManualCleanupComponent implements OnInit, OnDestroy {
  data: any;
  private subscriptions = new Subscription();

  ngOnInit() {
    this.subscriptions.add(
      this.dataService.getData().subscribe(data => this.data = data)
    );

    this.subscriptions.add(
      this.dataService.getOther().subscribe(other => console.log(other))
    );
  }

  ngOnDestroy() {
    this.subscriptions.unsubscribe();
  }
}
```

---

## 65. How would you trigger change detection manually when needed?

**Approach:**
- Inject ChangeDetectorRef
- Use detectChanges() or markForCheck()
- Use ApplicationRef.tick() for global

**Implementation:**

```typescript
@Component({
  selector: 'app-manual-cd',
  template: `<div>{{count}}</div>`,
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class ManualCDComponent implements OnInit {
  count = 0;

  constructor(
    private cdr: ChangeDetectorRef,
    private ngZone: NgZone
  ) {}

  ngOnInit() {
    // Operation outside Angular zone
    this.ngZone.runOutsideAngular(() => {
      setInterval(() => {
        this.count++;
        
        // Manually trigger change detection
        this.ngZone.run(() => {
          this.cdr.detectChanges(); // Immediate check
        });
      }, 1000);
    });
  }

  updateCount() {
    this.count++;
    this.cdr.markForCheck(); // Mark for next cycle
  }

  // Detach and reattach
  pauseCD() {
    this.cdr.detach(); // Stop change detection
  }

  resumeCD() {
    this.cdr.reattach(); // Resume change detection
    this.cdr.detectChanges();
  }
}

// Global change detection
@Component({
  selector: 'app-global-cd',
  template: `<div>{{data}}</div>`
})
export class GlobalCDComponent {
  data: any;

  constructor(private appRef: ApplicationRef) {}

  updateData() {
    this.data = 'Updated';
    this.appRef.tick(); // Trigger global change detection
  }
}
```

---

## 66. You need to run code only once when component initializes. What's the best approach?

**Approach:**
- Use ngOnInit for initialization
- Constructor for dependency injection only
- Avoid ngOnChanges for one-time init

**Implementation:**

```typescript
@Component({
  selector: 'app-init',
  template: `<div>{{data}}</div>`
})
export class InitComponent implements OnInit {
  data: any;

  // Constructor: Dependency injection only
  constructor(
    private dataService: DataService,
    private route: ActivatedRoute
  ) {
    // Avoid initialization logic here
    // View is not ready yet
  }

  // ngOnInit: One-time initialization
  ngOnInit() {
    // Load data once
    this.loadData();
    
    // Subscribe to route params
    this.route.params.subscribe(params => {
      console.log(params);
    });
    
    // Initialize component state
    this.initializeState();
  }

  private loadData() {
    this.dataService.getData().subscribe(data => this.data = data);
  }

  private initializeState() {
    // One-time setup
  }
}

// Avoid this pattern for one-time init
@Component({
  selector: 'app-wrong',
  template: `<div>{{data}}</div>`
})
export class WrongComponent implements OnChanges {
  @Input() config: any;

  // BAD: ngOnChanges runs on every input change
  ngOnChanges(changes: SimpleChanges) {
    // This runs multiple times
    this.initialize();
  }

  // GOOD: Use flag for one-time init if needed
  private initialized = false;

  ngOnChanges(changes: SimpleChanges) {
    if (!this.initialized && changes['config']) {
      this.initialize();
      this.initialized = true;
    }
  }

  private initialize() {
    // One-time initialization
  }
}
```

---

## 67. A third-party library directly manipulates the DOM. How do you safely integrate it with Angular?

**Approach:**
- Use ngAfterViewInit
- Access DOM with ViewChild and ElementRef
- Run outside Angular zone

**Implementation:**

```typescript
@Component({
  selector: 'app-chart',
  template: `<div #chartContainer style="width: 100%; height: 400px;"></div>`
})
export class ChartComponent implements AfterViewInit, OnDestroy {
  @ViewChild('chartContainer') chartContainer!: ElementRef;
  private chart: any;

  constructor(private ngZone: NgZone) {}

  ngAfterViewInit() {
    // Run outside Angular zone to avoid change detection
    this.ngZone.runOutsideAngular(() => {
      this.initChart();
    });
  }

  private initChart() {
    // Initialize third-party library (e.g., Chart.js, D3)
    const element = this.chartContainer.nativeElement;
    this.chart = new ThirdPartyChart(element, {
      data: [1, 2, 3, 4, 5]
    });
  }

  updateChart(data: number[]) {
    // Update from Angular - run outside zone
    this.ngZone.runOutsideAngular(() => {
      this.chart.update(data);
    });
  }

  onChartClick(callback: Function) {
    // When library triggers callback, re-enter Angular zone
    this.chart.onClick(() => {
      this.ngZone.run(() => {
        callback();
      });
    });
  }

  ngOnDestroy() {
    // Clean up library
    if (this.chart) {
      this.chart.destroy();
    }
  }
}
```

---

## 68. You need to create a directive that adds behavior to multiple elements. How do you implement this?

**Approach:**
- Create attribute directive
- Use HostListener for events
- Use Renderer2 for DOM manipulation

**Implementation:**

```typescript
// highlight.directive.ts
@Directive({
  selector: '[appHighlight]'
})
export class HighlightDirective {
  @Input() appHighlight = 'yellow';
  @Input() defaultColor = 'transparent';

  constructor(private el: ElementRef, private renderer: Renderer2) {}

  @HostListener('mouseenter') onMouseEnter() {
    this.highlight(this.appHighlight);
  }

  @HostListener('mouseleave') onMouseLeave() {
    this.highlight(this.defaultColor);
  }

  private highlight(color: string) {
    this.renderer.setStyle(this.el.nativeElement, 'backgroundColor', color);
  }
}

// Usage
@Component({
  template: `
    <p appHighlight>Hover me (default yellow)</p>
    <p appHighlight="lightblue">Hover me (blue)</p>
    <p [appHighlight]="color" [defaultColor]="'white'">Dynamic color</p>
  `
})
export class AppComponent {
  color = 'lightgreen';
}

// click-outside.directive.ts
@Directive({
  selector: '[clickOutside]'
})
export class ClickOutsideDirective {
  @Output() clickOutside = new EventEmitter<void>();

  constructor(private elementRef: ElementRef) {}

  @HostListener('document:click', ['$event.target'])
  onClick(target: HTMLElement) {
    const clickedInside = this.elementRef.nativeElement.contains(target);
    if (!clickedInside) {
      this.clickOutside.emit();
    }
  }
}

// Usage
@Component({
  template: `
    <div class="dropdown" (clickOutside)="close()">
      <button (click)="toggle()">Menu</button>
      <ul *ngIf="isOpen">
        <li>Item 1</li>
        <li>Item 2</li>
      </ul>
    </div>
  `
})
export class DropdownComponent {
  isOpen = false;

  toggle() {
    this.isOpen = !this.isOpen;
  }

  close() {
    this.isOpen = false;
  }
}
```

---

## 69. How would you create a structural directive similar to *ngIf?

**Approach:**
- Inject TemplateRef and ViewContainerRef
- Use Input setter for condition
- Create or clear embedded view

**Implementation:**

```typescript
// unless.directive.ts
@Directive({
  selector: '[appUnless]'
})
export class UnlessDirective {
  private hasView = false;

  constructor(
    private templateRef: TemplateRef<any>,
    private viewContainer: ViewContainerRef
  ) {}

  @Input() set appUnless(condition: boolean) {
    if (!condition && !this.hasView) {
      this.viewContainer.createEmbeddedView(this.templateRef);
      this.hasView = true;
    } else if (condition && this.hasView) {
      this.viewContainer.clear();
      this.hasView = false;
    }
  }
}

// Usage
@Component({
  template: `
    <p *appUnless="false">Show this (condition is false)</p>
    <p *appUnless="true">Don't show this (condition is true)</p>
    <p *appUnless="isHidden">Toggle visibility</p>
  `
})
export class AppComponent {
  isHidden = false;
}

// Advanced: Structural directive with context
@Directive({
  selector: '[appRepeat]'
})
export class RepeatDirective {
  constructor(
    private templateRef: TemplateRef<any>,
    private viewContainer: ViewContainerRef
  ) {}

  @Input() set appRepeat(times: number) {
    this.viewContainer.clear();
    for (let i = 0; i < times; i++) {
      this.viewContainer.createEmbeddedView(this.templateRef, {
        $implicit: i,
        index: i
      });
    }
  }
}

// Usage with context
@Component({
  template: `
    <div *appRepeat="3; let i; let idx = index">
      Item {{i}} at index {{idx}}
    </div>
  `
})
export class RepeatComponent {}
```

---

## 70. You need to create a custom pipe that makes async operations. How do you handle this?

**Approach:**
- Pipes should be pure and synchronous
- Return Observable and use async pipe
- Or create impure pipe (not recommended)

**Implementation:**

```typescript
// METHOD 1: Return Observable (Recommended)
@Pipe({ name: 'fetchData' })
export class FetchDataPipe implements PipeTransform {
  constructor(private http: HttpClient) {}

  transform(url: string): Observable<any> {
    return this.http.get(url);
  }
}

// Usage with async pipe
@Component({
  template: `
    <div *ngIf="'/api/users' | fetchData | async as users">
      <div *ngFor="let user of users">{{user.name}}</div>
    </div>
  `
})
export class AppComponent {}

// METHOD 2: Handle async in component (Better)
@Pipe({ name: 'format', pure: true })
export class FormatPipe implements PipeTransform {
  transform(value: string, format: string): string {
    // Synchronous transformation
    return format === 'upper' ? value.toUpperCase() : value.toLowerCase();
  }
}

@Component({
  template: `
    <div *ngIf="data$ | async as data">
      {{data | format:'upper'}}
    </div>
  `
})
export class BetterComponent {
  data$ = this.http.get<string>('/api/data');
  constructor(private http: HttpClient) {}
}

// METHOD 3: Impure pipe (Not recommended - performance impact)
@Pipe({ name: 'asyncTransform', pure: false })
export class AsyncTransformPipe implements PipeTransform, OnDestroy {
  private cache = new Map<string, any>();
  private subscriptions = new Map<string, Subscription>();

  constructor(private http: HttpClient) {}

  transform(url: string): any {
    if (this.cache.has(url)) {
      return this.cache.get(url);
    }

    if (!this.subscriptions.has(url)) {
      const sub = this.http.get(url).subscribe(data => {
        this.cache.set(url, data);
      });
      this.subscriptions.set(url, sub);
    }

    return null; // Return null until data loads
  }

  ngOnDestroy() {
    this.subscriptions.forEach(sub => sub.unsubscribe());
  }
}
```

# Angular Interview Questions - Answers (Questions 71-75)
## Error Handling & Debugging, Migration & Integration, Advanced Patterns

---

## 71. How do you implement global error handling in Angular?

**Approach:**
- Create custom ErrorHandler
- Override handleError method
- Log and display user-friendly messages

**Implementation:**

```typescript
// global-error-handler.ts
import { ErrorHandler, Injectable, Injector } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';

@Injectable()
export class GlobalErrorHandler implements ErrorHandler {
  constructor(private injector: Injector) {}

  handleError(error: Error | HttpErrorResponse) {
    const router = this.injector.get(Router);
    const notificationService = this.injector.get(NotificationService);

    if (error instanceof HttpErrorResponse) {
      // Server error
      console.error('Server Error:', error);
      notificationService.error(`Server Error: ${error.message}`);
    } else {
      // Client error
      console.error('Client Error:', error);
      notificationService.error(`An error occurred: ${error.message}`);
    }

    // Log to console in development
    if (!environment.production) {
      console.error('Error details:', error);
    }

    // Optionally navigate to error page
    // router.navigate(['/error']);
  }
}

// app.module.ts
@NgModule({
  providers: [
    { provide: ErrorHandler, useClass: GlobalErrorHandler }
  ]
})
export class AppModule {}

// notification.service.ts
@Injectable({ providedIn: 'root' })
export class NotificationService {
  error(message: string) {
    alert(message); // Or use toast/snackbar
  }

  success(message: string) {
    alert(message);
  }
}
```

---

## 72. You need to log errors to an external service. How do you implement this?

**Approach:**
- Extend ErrorHandler to send errors to logging service
- Include context and user information
- Handle network failures

**Implementation:**

```typescript
// error-logging.service.ts
@Injectable({ providedIn: 'root' })
export class ErrorLoggingService {
  constructor(private http: HttpClient) {}

  logError(error: any) {
    const errorLog = {
      message: error.message || error.toString(),
      stack: error.stack,
      timestamp: new Date().toISOString(),
      url: window.location.href,
      userAgent: navigator.userAgent,
      userId: this.getUserId()
    };

    // Send to external service (e.g., Sentry, LogRocket)
    this.http.post('/api/logs/error', errorLog)
      .subscribe({
        error: (err) => console.error('Failed to log error:', err)
      });
  }

  private getUserId(): string | null {
    return localStorage.getItem('userId');
  }
}

// global-error-handler.ts
@Injectable()
export class GlobalErrorHandler implements ErrorHandler {
  constructor(
    private injector: Injector,
    private errorLogging: ErrorLoggingService
  ) {}

  handleError(error: Error | HttpErrorResponse) {
    // Log to external service
    this.errorLogging.logError(error);

    // Show user notification
    const notification = this.injector.get(NotificationService);
    
    if (error instanceof HttpErrorResponse) {
      notification.error('A server error occurred. Please try again.');
    } else {
      notification.error('An unexpected error occurred.');
    }

    // Log to console
    console.error('Error:', error);
  }
}

// Integration with Sentry (example)
import * as Sentry from '@sentry/angular';

@Injectable()
export class SentryErrorHandler implements ErrorHandler {
  handleError(error: any) {
    Sentry.captureException(error);
    console.error(error);
  }
}

// app.module.ts
Sentry.init({
  dsn: 'YOUR_SENTRY_DSN',
  environment: environment.production ? 'production' : 'development'
});

@NgModule({
  providers: [
    { provide: ErrorHandler, useClass: SentryErrorHandler }
  ]
})
export class AppModule {}
```

---

## 73. You have a memory leak in your Angular app. How do you identify and fix it?

**Approach:**
- Use Chrome DevTools Memory profiler
- Check for unsubscribed Observables
- Remove event listeners
- Use takeUntil pattern

**Implementation:**

```typescript
// COMMON MEMORY LEAK CAUSES AND FIXES

// 1. UNSUBSCRIBED OBSERVABLES (BAD)
@Component({
  selector: 'app-leak',
  template: `<div>{{data}}</div>`
})
export class LeakComponent implements OnInit {
  data: any;

  constructor(private dataService: DataService) {}

  ngOnInit() {
    // BAD: No unsubscribe
    this.dataService.getData().subscribe(data => this.data = data);
  }
}

// FIX: Use takeUntil
@Component({
  selector: 'app-fixed',
  template: `<div>{{data}}</div>`
})
export class FixedComponent implements OnInit, OnDestroy {
  data: any;
  private destroy$ = new Subject<void>();

  constructor(private dataService: DataService) {}

  ngOnInit() {
    this.dataService.getData()
      .pipe(takeUntil(this.destroy$))
      .subscribe(data => this.data = data);
  }

  ngOnDestroy() {
    this.destroy$.next();
    this.destroy$.complete();
  }
}

// 2. EVENT LISTENERS NOT REMOVED (BAD)
@Component({
  selector: 'app-listener-leak',
  template: `<div>Scroll position: {{scrollPos}}</div>`
})
export class ListenerLeakComponent implements OnInit {
  scrollPos = 0;

  ngOnInit() {
    // BAD: Listener not removed
    window.addEventListener('scroll', () => {
      this.scrollPos = window.scrollY;
    });
  }
}

// FIX: Remove in ngOnDestroy
@Component({
  selector: 'app-listener-fixed',
  template: `<div>Scroll position: {{scrollPos}}</div>`
})
export class ListenerFixedComponent implements OnInit, OnDestroy {
  scrollPos = 0;
  private scrollHandler = () => {
    this.scrollPos = window.scrollY;
  };

  ngOnInit() {
    window.addEventListener('scroll', this.scrollHandler);
  }

  ngOnDestroy() {
    window.removeEventListener('scroll', this.scrollHandler);
  }
}

// 3. TIMERS NOT CLEARED (BAD)
@Component({
  selector: 'app-timer-leak',
  template: `<div>{{count}}</div>`
})
export class TimerLeakComponent implements OnInit {
  count = 0;

  ngOnInit() {
    // BAD: Timer not cleared
    setInterval(() => {
      this.count++;
    }, 1000);
  }
}

// FIX: Clear in ngOnDestroy
@Component({
  selector: 'app-timer-fixed',
  template: `<div>{{count}}</div>`
})
export class TimerFixedComponent implements OnInit, OnDestroy {
  count = 0;
  private intervalId?: number;

  ngOnInit() {
    this.intervalId = window.setInterval(() => {
      this.count++;
    }, 1000);
  }

  ngOnDestroy() {
    if (this.intervalId) {
      clearInterval(this.intervalId);
    }
  }
}

// 4. DETACHED CHANGE DETECTOR (BAD)
@Component({
  selector: 'app-cd-leak',
  template: `<div>{{data}}</div>`
})
export class CDLeakComponent implements OnInit {
  data: any;

  constructor(private cdr: ChangeDetectorRef) {}

  ngOnInit() {
    this.cdr.detach();
    // BAD: Never reattached
  }
}

// FIX: Reattach or manage properly
@Component({
  selector: 'app-cd-fixed',
  template: `<div>{{data}}</div>`
})
export class CDFixedComponent implements OnInit, OnDestroy {
  data: any;

  constructor(private cdr: ChangeDetectorRef) {}

  ngOnInit() {
    this.cdr.detach();
  }

  ngOnDestroy() {
    this.cdr.reattach();
  }
}

// DEBUGGING MEMORY LEAKS
// 1. Chrome DevTools > Memory > Take Heap Snapshot
// 2. Perform action that might leak
// 3. Take another snapshot
// 4. Compare snapshots to find retained objects
// 5. Look for detached DOM nodes and unexpected object retention

// Angular DevTools
// - Install Angular DevTools extension
// - Check component tree for unexpected instances
// - Monitor change detection cycles
```

---

## 74. How would you **migrate a large AngularJS application to Angular** while minimizing downtime and errors?

**Approach:**
- Use ngUpgrade for hybrid approach
- Migrate incrementally
- Downgrade/upgrade components
- Unified routing

**Implementation:**

```typescript
// STEP 1: Setup hybrid application
// Install dependencies
// npm install @angular/upgrade

// main.ts - Bootstrap hybrid app
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { UpgradeModule } from '@angular/upgrade/static';
import { AppModule } from './app/app.module';

platformBrowserDynamic()
  .bootstrapModule(AppModule)
  .then(platformRef => {
    const upgrade = platformRef.injector.get(UpgradeModule);
    upgrade.bootstrap(document.body, ['angularjsApp']);
  });

// app.module.ts - Hybrid module
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
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
    // Bootstrap AngularJS
    this.upgrade.bootstrap(document.body, ['angularjsApp'], { strictDi: true });
  }
}

// STEP 2: Downgrade Angular component for use in AngularJS
// new-angular.component.ts
@Component({
  selector: 'app-new-angular',
  template: `<div>New Angular Component</div>`
})
export class NewAngularComponent {
  @Input() data: any;
  @Output() onAction = new EventEmitter();
}

// Downgrade for AngularJS
import { downgradeComponent } from '@angular/upgrade/static';

angular.module('angularjsApp')
  .directive('appNewAngular', 
    downgradeComponent({ component: NewAngularComponent })
  );

// Use in AngularJS template
// <app-new-angular [data]="$ctrl.data" (on-action)="$ctrl.handleAction()"></app-new-angular>

// STEP 3: Upgrade AngularJS component for use in Angular
// old-angularjs.component.js
angular.module('angularjsApp')
  .component('oldAngularjs', {
    template: '<div>Old AngularJS Component</div>',
    bindings: {
      data: '<',
      onAction: '&'
    }
  });

// Upgrade for Angular
import { Directive, ElementRef, Injector } from '@angular/core';
import { UpgradeComponent } from '@angular/upgrade/static';

@Directive({
  selector: 'old-angularjs'
})
export class OldAngularjsDirective extends UpgradeComponent {
  constructor(elementRef: ElementRef, injector: Injector) {
    super('oldAngularjs', elementRef, injector);
  }
}

// Use in Angular template
// <old-angularjs [data]="data" (onAction)="handleAction()"></old-angularjs>

// STEP 4: Migrate services
// AngularJS service
angular.module('angularjsApp')
  .factory('dataService', function($http) {
    return {
      getData: function() {
        return $http.get('/api/data');
      }
    };
  });

// Upgrade AngularJS service for Angular
import { InjectionToken } from '@angular/core';

export const DATA_SERVICE = new InjectionToken('dataService');

export function dataServiceFactory(i: any) {
  return i.get('dataService');
}

export const dataServiceProvider = {
  provide: DATA_SERVICE,
  useFactory: dataServiceFactory,
  deps: ['$injector']
};

// Use in Angular
@Component({
  selector: 'app-component'
})
export class AppComponent {
  constructor(@Inject(DATA_SERVICE) private dataService: any) {
    this.dataService.getData().then(data => console.log(data));
  }
}

// STEP 5: Migration strategy
// 1. Start with leaf components (no dependencies)
// 2. Migrate services to Angular
// 3. Migrate shared components
// 4. Migrate routes incrementally
// 5. Remove AngularJS when all migrated

// STEP 6: Unified routing
import { UrlHandlingStrategy } from '@angular/router';

export class HybridUrlHandlingStrategy implements UrlHandlingStrategy {
  shouldProcessUrl(url: any) {
    // Process Angular routes
    return url.toString().startsWith('/angular');
  }

  extract(url: any) {
    return url;
  }

  merge(url: any, whole: any) {
    return url;
  }
}

@NgModule({
  providers: [
    { provide: UrlHandlingStrategy, useClass: HybridUrlHandlingStrategy }
  ]
})
export class AppModule {}
```

---

## 75. You need to implement server-side rendering (SSR) with Angular Universal. What are the considerations?

**Approach:**
- Add Angular Universal
- Avoid direct DOM manipulation
- Check platform before browser APIs
- Handle state transfer

**Implementation:**

```typescript
// STEP 1: Add Angular Universal
// ng add @nguniversal/express-engine

// STEP 2: Check platform before using browser APIs
import { isPlatformBrowser } from '@angular/common';
import { PLATFORM_ID, Inject } from '@angular/core';

@Component({
  selector: 'app-browser-only',
  template: `<div>{{data}}</div>`
})
export class BrowserOnlyComponent implements OnInit {
  data: any;

  constructor(@Inject(PLATFORM_ID) private platformId: Object) {}

  ngOnInit() {
    if (isPlatformBrowser(this.platformId)) {
      // Safe to use window, document, localStorage
      this.data = localStorage.getItem('data');
      console.log(window.innerWidth);
    }
  }
}

// STEP 3: Use Renderer2 instead of direct DOM manipulation
@Component({
  selector: 'app-dom-safe',
  template: `<div #myDiv>Content</div>`
})
export class DomSafeComponent implements AfterViewInit {
  @ViewChild('myDiv') myDiv!: ElementRef;

  constructor(private renderer: Renderer2) {}

  ngAfterViewInit() {
    // BAD: Direct DOM manipulation
    // this.myDiv.nativeElement.style.color = 'red';

    // GOOD: Use Renderer2
    this.renderer.setStyle(this.myDiv.nativeElement, 'color', 'red');
  }
}

// STEP 4: Transfer state from server to browser
import { TransferState, makeStateKey } from '@angular/platform-browser';

const DATA_KEY = makeStateKey<any>('data');

@Component({
  selector: 'app-transfer-state',
  template: `<div *ngFor="let item of data">{{item.name}}</div>`
})
export class TransferStateComponent implements OnInit {
  data: any[] = [];

  constructor(
    private http: HttpClient,
    private transferState: TransferState,
    @Inject(PLATFORM_ID) private platformId: Object
  ) {}

  ngOnInit() {
    // Check if data already transferred from server
    const cachedData = this.transferState.get(DATA_KEY, null);

    if (cachedData) {
      // Use cached data from server
      this.data = cachedData;
    } else {
      // Fetch data
      this.http.get<any[]>('/api/data').subscribe(data => {
        this.data = data;
        
        // Store for transfer to browser
        if (!isPlatformBrowser(this.platformId)) {
          this.transferState.set(DATA_KEY, data);
        }
      });
    }
  }
}

// STEP 5: Handle third-party libraries
@Component({
  selector: 'app-third-party',
  template: `<div #chart></div>`
})
export class ThirdPartyComponent implements AfterViewInit {
  @ViewChild('chart') chartElement!: ElementRef;

  constructor(@Inject(PLATFORM_ID) private platformId: Object) {}

  ngAfterViewInit() {
    if (isPlatformBrowser(this.platformId)) {
      // Only load library in browser
      import('chart.js').then(Chart => {
        new Chart.default(this.chartElement.nativeElement, {
          type: 'bar',
          data: { labels: [], datasets: [] }
        });
      });
    }
  }
}

// STEP 6: Server configuration (server.ts)
import 'zone.js/node';
import { ngExpressEngine } from '@nguniversal/express-engine';
import * as express from 'express';
import { AppServerModule } from './src/main.server';

const app = express();

app.engine('html', ngExpressEngine({
  bootstrap: AppServerModule
}));

app.set('view engine', 'html');
app.set('views', './dist/browser');

app.get('*.*', express.static('./dist/browser'));

app.get('*', (req, res) => {
  res.render('index', { req });
});

app.listen(4000, () => {
  console.log('Server listening on http://localhost:4000');
});

// STEP 7: Prerendering for static pages
// angular.json
{
  "projects": {
    "app": {
      "architect": {
        "prerender": {
          "builder": "@nguniversal/builders:prerender",
          "options": {
            "routes": ["/", "/about", "/contact"]
          }
        }
      }
    }
  }
}

// Build and prerender
// npm run build:ssr
// npm run prerender

// CONSIDERATIONS:
// 1. Avoid window, document, localStorage on server
// 2. Use TransferState to avoid duplicate API calls
// 3. Handle async operations carefully
// 4. Test both server and browser rendering
// 5. Consider caching strategies
// 6. Monitor server performance
// 7. Use lazy loading for better performance
```
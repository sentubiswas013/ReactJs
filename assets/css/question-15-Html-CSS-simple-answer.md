# 🎯 **HTML Interview Questions & Answers**

### **1. What is HTML and why is it used?**

HTML stands for HyperText Markup Language. It's the standard markup language for creating web pages. HTML uses tags to structure content like headings, paragraphs, links, and images. It's the foundation of every website.

```html
<!DOCTYPE html>
<html>
<head>
    <title>My Page</title>
</head>
<body>
    <h1>Welcome</h1>
    <p>This is HTML content</p>
</body>
</html>
```

---

### **2. What is the difference between HTML and HTML5?**

HTML5 is the latest version with new semantic tags, multimedia support, and APIs. HTML5 added `<video>`, `<audio>`, `<canvas>`, local storage, and better form controls. It's more mobile-friendly and supports modern web applications.

```html
<!-- HTML5 Features -->
<video controls>
    <source src="movie.mp4" type="video/mp4">
</video>
<canvas id="myCanvas"></canvas>
<input type="email" placeholder="Enter email">
```

---

### **3. What are semantic HTML tags? Give examples.**

Semantic tags clearly describe their meaning to both browsers and developers. They improve SEO and accessibility by providing structure and context to content.

```html
<header>Site header</header>
<nav>Navigation menu</nav>
<main>
    <article>Blog post content</article>
    <aside>Sidebar content</aside>
</main>
<footer>Site footer</footer>
```

---

### **4. What is the purpose of `<!DOCTYPE html>`?**

DOCTYPE tells the browser which HTML version to use and ensures the page renders in standards mode. Without it, browsers might use quirks mode, causing layout issues.

```html
<!DOCTYPE html> <!-- HTML5 DOCTYPE -->
<html>
    <!-- Your content here -->
</html>
```

---

### **5. Difference between `<div>` and `<span>`?**

`<div>` is a block-level element that takes full width and starts on a new line. `<span>` is inline and only takes necessary space without breaking the line.

```html
<div>This is a block element</div>
<div>This starts on new line</div>

<span>This is inline</span> <span>Same line</span>
```

---

### **6. What are block-level and inline elements?**

Block elements take full width and start on new lines (div, p, h1). Inline elements take only necessary space and stay on the same line (span, a, strong).

```html
<!-- Block elements -->
<h1>Heading</h1>
<p>Paragraph</p>

<!-- Inline elements -->
<a href="#">Link</a> <strong>Bold text</strong>
```

---

### **7. What is the use of `<meta>` tags?**

Meta tags provide metadata about the HTML document. They're used for SEO, responsive design, character encoding, and social media sharing.

```html
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="Page description for SEO">
<meta name="keywords" content="html, css, javascript">
```

---

### **8. What is the `alt` attribute in images?**

The `alt` attribute provides alternative text for images when they can't be displayed. It's crucial for accessibility and SEO, helping screen readers describe images to visually impaired users.

```html
<img src="logo.png" alt="Company logo">
<img src="chart.jpg" alt="Sales data chart showing 20% growth">
```

---

### **9. What is the difference between ID and Class?**

ID is unique and used once per page, while Class can be used multiple times. ID has higher CSS specificity and is used for JavaScript targeting.

```html
<div id="header">Unique header</div>
<div class="button">Button 1</div>
<div class="button">Button 2</div>

<style>
#header { color: blue; }
.button { padding: 10px; }
</style>
```

---

### **10. What is the use of `<header>`, `<footer>`, `<section>`?**

These are semantic HTML5 tags. `<header>` contains introductory content, `<footer>` contains closing information, and `<section>` groups related content together.

```html
<header>
    <h1>Site Title</h1>
    <nav>Menu</nav>
</header>
<section>
    <h2>Article Section</h2>
    <p>Content here</p>
</section>
<footer>
    <p>&copy; 2024 Company</p>
</footer>
```

---

### **11. What are self-closing tags? Give examples.**

Self-closing tags don't need closing tags because they don't contain content. They're also called void elements.

```html
<img src="image.jpg" alt="Image">
<br>
<hr>
<input type="text" name="username">
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css">
```

---

### **12. How to link external CSS and JS in HTML?**

Use `<link>` for CSS in the head section and `<script>` for JavaScript, preferably before closing body tag for better performance.

```html
<head>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <!-- Content -->
    <script src="script.js"></script>
</body>
```

---

### **13. What is the purpose of `<form>` and its attributes?**

Forms collect user input and send it to servers. Key attributes include action (where to send data), method (GET/POST), and various input types for different data.

```html
<form action="/submit" method="POST">
    <input type="text" name="username" required>
    <input type="email" name="email" required>
    <input type="password" name="password" required>
    <button type="submit">Submit</button>
</form>
```

---

### **14. Difference between `<b>` vs `<strong>`, `<i>` vs `<em>`?**

`<b>` and `<i>` are presentational (just styling). `<strong>` and `<em>` are semantic (convey importance and emphasis), which is better for accessibility and SEO.

```html
<!-- Presentational -->
<b>Bold text</b>
<i>Italic text</i>

<!-- Semantic (preferred) -->
<strong>Important text</strong>
<em>Emphasized text</em>
```

---

### **15. What are HTML5 local storage and session storage?**

Both store data in the browser. localStorage persists until manually cleared, sessionStorage clears when the tab closes. They're used for offline functionality and user preferences.

```html
<script>
// localStorage - persists
localStorage.setItem('username', 'john');
let user = localStorage.getItem('username');

// sessionStorage - temporary
sessionStorage.setItem('token', 'abc123');
let token = sessionStorage.getItem('token');
</script>
```

---

### **16. What is the purpose of `<iframe>`?**

iframe embeds another HTML document within the current page. It's used for embedding videos, maps, external content, or creating sandboxed environments.

```html
<iframe 
    src="https://www.youtube.com/embed/video-id" 
    width="560" 
    height="315"
    frameborder="0">
</iframe>

<iframe src="https://maps.google.com/embed" width="100%" height="300"></iframe>
```

---

### **17. What is lazy loading? How do you apply it?**

Lazy loading delays loading of images until they're needed (when user scrolls near them). It improves page performance by reducing initial load time.

```html
<!-- Native lazy loading -->
<img src="image.jpg" alt="Description" loading="lazy">

<!-- For older browsers -->
<img data-src="image.jpg" alt="Description" class="lazy">

<script>
// Intersection Observer for lazy loading
const images = document.querySelectorAll('.lazy');
const observer = new IntersectionObserver((entries) => {
    entries.forEach(entry => {
        if (entry.isIntersecting) {
            entry.target.src = entry.target.dataset.src;
        }
    });
});
images.forEach(img => observer.observe(img));
</script>
```

---

### **18. What is the `<canvas>` tag used for?**

Canvas creates a drawing area for graphics, animations, games, and data visualizations using JavaScript. It's like a blank canvas where you can draw programmatically.

```html
<canvas id="myCanvas" width="400" height="200"></canvas>

<script>
const canvas = document.getElementById('myCanvas');
const ctx = canvas.getContext('2d');

// Draw a rectangle
ctx.fillStyle = 'blue';
ctx.fillRect(10, 10, 100, 50);

// Draw a circle
ctx.beginPath();
ctx.arc(200, 50, 30, 0, 2 * Math.PI);
ctx.fillStyle = 'red';
ctx.fill();
</script>
```

---

### **19. What are data-* attributes in HTML?**

Data attributes store custom data in HTML elements. They start with "data-" and can hold any information you need for JavaScript processing without affecting the page display.

```html
<div data-user-id="123" data-role="admin" data-theme="dark">
    User Profile
</div>

<script>
const element = document.querySelector('div');
console.log(element.dataset.userId);    // "123"
console.log(element.dataset.role);      // "admin"
console.log(element.dataset.theme);     // "dark"

// Set data attribute
element.dataset.status = 'active';
</script>
```

---

### **20. What is ARIA and why is it used?**

ARIA (Accessible Rich Internet Applications) provides accessibility information to assistive technologies like screen readers. It helps make web content accessible to users with disabilities.

```html
<!-- ARIA labels and roles -->
<button aria-label="Close dialog" onclick="closeModal()">×</button>

<div role="alert" aria-live="polite">
    Form submitted successfully!
</div>

<input type="text" aria-describedby="password-help" aria-required="true">
<div id="password-help">Password must be 8+ characters</div>

<!-- Skip navigation for screen readers -->
<a href="#main-content" class="skip-link">Skip to main content</a>
```

## 🎯 **CSS Interview Questions & Answers**

### **1. What is CSS and why is it used?**

CSS stands for Cascading Style Sheets. It styles HTML elements - colors, fonts, layouts, animations. It separates content from presentation, making websites look good and maintainable.

```css
h1 { color: blue; font-size: 24px; }
.button { background: red; padding: 10px; }
```

---

### **2. What are the types of CSS (inline, internal, external)?**

Three types: Inline CSS uses style attribute, Internal CSS uses style tag in head, External CSS uses separate .css files. External is best for maintainability.

```html
<!-- Inline -->
<p style="color: red;">Text</p>

<!-- Internal -->
<style>p { color: blue; }</style>

<!-- External -->
<link rel="stylesheet" href="style.css">
```

---

### **3. What is the CSS Box Model?**

Every element is a box with content, padding, border, and margin. Total width = content + padding + border + margin. Box-sizing controls how width is calculated.

```css
.box {
  width: 200px;
  padding: 20px;
  border: 5px solid black;
  margin: 10px;
  box-sizing: border-box; /* includes padding/border in width */
}
```

---

### **4. What is specificity in CSS?**

Specificity determines which CSS rule applies when multiple rules target the same element. Order: inline styles > IDs > classes > elements. Higher specificity wins.

```css
p { color: black; }           /* specificity: 1 */
.text { color: blue; }        /* specificity: 10 */
#header { color: red; }       /* specificity: 100 */
```

---

### **5. What are pseudo-classes and pseudo-elements?**

Pseudo-classes target element states like :hover, :focus. Pseudo-elements target parts of elements like ::before, ::after. Single colon for classes, double for elements.

```css
/* Pseudo-classes */
a:hover { color: red; }
input:focus { border: 2px solid blue; }

/* Pseudo-elements */
p::before { content: "→ "; }
p::first-line { font-weight: bold; }
```

---

### **6. Difference between `display: inline`, `block`, and `inline-block`?**

Block takes full width, starts new line. Inline takes only needed space, no width/height. Inline-block combines both - respects width/height but stays inline.

```css
.block { display: block; width: 100%; }
.inline { display: inline; }
.inline-block { display: inline-block; width: 200px; }
```

---

### **7. What is Flexbox? Why is it used?**

Flexbox is a layout method for arranging items in rows or columns. It handles alignment, spacing, and responsive design easily. Great for navigation bars and centering content.

```css
.container {
  display: flex;
  justify-content: center; /* horizontal alignment */
  align-items: center;     /* vertical alignment */
  gap: 20px;
}
```

---

### **8. What is CSS Grid?**

CSS Grid creates two-dimensional layouts with rows and columns. It's perfect for complex layouts like dashboards, image galleries, and webpage structures.

```css
.grid {
  display: grid;
  grid-template-columns: 1fr 2fr 1fr;
  grid-template-rows: auto 1fr auto;
  gap: 20px;
}
```

---

### **9. What is `position` property? Explain all values.**

Position controls element placement. Static is default, relative moves from normal position, absolute positions relative to parent, fixed stays on screen, sticky combines relative and fixed.

```css
.relative { position: relative; top: 10px; }
.absolute { position: absolute; top: 0; right: 0; }
.fixed { position: fixed; bottom: 20px; }
.sticky { position: sticky; top: 0; }
```

---

### **10. What is `z-index`?**

Z-index controls stacking order of positioned elements. Higher values appear on top. Only works with positioned elements (not static). Default is auto.

```css
.modal { position: fixed; z-index: 1000; }
.overlay { position: absolute; z-index: 999; }
.content { position: relative; z-index: 1; }
```

---

### **11. What is the difference between `visibility: hidden` and `display: none`?**

Display none removes element completely from layout. Visibility hidden keeps space but makes element invisible. Display none affects layout, visibility doesn't.

```css
.hidden { visibility: hidden; } /* keeps space */
.gone { display: none; }        /* removes from layout */
```

---

### **12. What is the purpose of `overflow`?**

Overflow controls what happens when content exceeds container size. Visible shows all, hidden clips content, scroll adds scrollbars, auto adds scrollbars when needed.

```css
.container {
  width: 200px;
  height: 100px;
  overflow: hidden;  /* clips content */
  overflow-y: scroll; /* vertical scroll only */
}
```

---

### **13. What are media queries?**

Media queries apply CSS based on device characteristics like screen size, orientation. They're essential for responsive design, allowing different styles for mobile, tablet, desktop.

```css
/* Mobile first */
.container { width: 100%; }

@media (min-width: 768px) {
  .container { width: 750px; }
}

@media (min-width: 1200px) {
  .container { width: 1140px; }
}
```

---

### **14. What is responsive design?**

Responsive design makes websites work on all devices by adapting layout, images, and content to different screen sizes. Uses flexible grids, media queries, and relative units.

```css
.responsive {
  width: 100%;
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 20px;
}

img { max-width: 100%; height: auto; }
```

---

### **15. What is the difference between `rem`, `em`, and `px` units?**

Px is fixed pixels. Em is relative to parent font size. Rem is relative to root font size. Rem is better for consistent scaling, em for component-based sizing.

```css
html { font-size: 16px; }
.parent { font-size: 20px; }

.child {
  font-size: 1em;   /* 20px (parent size) */
  margin: 1rem;     /* 16px (root size) */
  width: 200px;     /* 200px (fixed) */
}
```

---

### **16. What is the `transition` property?**

Transition creates smooth animations between CSS property changes. You specify which properties to animate, duration, timing function, and delay.

```css
.button {
  background: blue;
  transition: all 0.3s ease;
}

.button:hover {
  background: red;
  transform: scale(1.1);
}
```

---

### **17. What is the `transform` property?**

Transform applies 2D/3D transformations like rotate, scale, translate, skew without affecting document flow. Great for animations and interactive effects.

```css
.card {
  transform: rotate(45deg) scale(1.2) translateX(50px);
  transform-origin: center;
}

.flip { transform: rotateY(180deg); }
```

---

### **18. What are CSS variables?**

CSS variables (custom properties) store reusable values. Define with -- prefix, use with var(). Great for themes, consistent colors, and dynamic styling.

```css
:root {
  --primary-color: #007bff;
  --spacing: 20px;
}

.button {
  background: var(--primary-color);
  padding: var(--spacing);
}
```

---

### **19. What are vendor prefixes?**

Vendor prefixes are browser-specific CSS properties for experimental features. -webkit- for Chrome/Safari, -moz- for Firefox, -ms- for IE. Modern browsers need fewer prefixes.

```css
.element {
  -webkit-transform: rotate(45deg);
  -moz-transform: rotate(45deg);
  -ms-transform: rotate(45deg);
  transform: rotate(45deg);
}
```

---

### **20. What is the difference between Flexbox and Grid?**

Flexbox is one-dimensional (row or column), great for components and alignment. Grid is two-dimensional (rows and columns), perfect for page layouts and complex structures.

```css
/* Flexbox - one dimension */
.flex { display: flex; justify-content: space-between; }

/* Grid - two dimensions */
.grid {
  display: grid;
  grid-template: "header header" "sidebar main" "footer footer";
}
```
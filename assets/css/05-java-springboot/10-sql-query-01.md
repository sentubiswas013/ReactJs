
# Top 20 SQL Queries Asked in TCS / Infosys / Accenture

# 1. Find the Second Highest Salary

```sql
SELECT MAX(Salary)
FROM Employees
WHERE Salary < (SELECT MAX(Salary) FROM Employees);
```

---

# 2. Find the Third Highest Salary

```sql
SELECT DISTINCT Salary
FROM Employees
ORDER BY Salary DESC
LIMIT 1 OFFSET 2;
```

---

# 3. Find Duplicate Employees (Same FirstName + LastName)

```sql
SELECT FirstName, LastName, COUNT(*)
FROM Employees
GROUP BY FirstName, LastName
HAVING COUNT(*) > 1;
```

---

# 4. Delete Duplicate Records

(Keep lowest EmployeeID)

```sql
DELETE FROM Employees
WHERE EmployeeID NOT IN (
    SELECT MIN(EmployeeID)
    FROM Employees
    GROUP BY FirstName, LastName
);
```

---

# 5. Employees With Salary Greater Than Average

```sql
SELECT *
FROM Employees
WHERE Salary > (SELECT AVG(Salary) FROM Employees);
```

---

# 6. Department-wise Highest Salary

```sql
SELECT Department, MAX(Salary) AS HighestSalary
FROM Employees
GROUP BY Department;
```

---

# 7. Employees With Highest Salary in Each Department

```sql
SELECT e.*
FROM Employees e
JOIN (
    SELECT Department, MAX(Salary) AS max_sal
    FROM Employees
    GROUP BY Department
) d
ON e.Department = d.Department
AND e.Salary = d.max_sal;
```

---

# 8. Employees Without Department

```sql
SELECT *
FROM Employees
WHERE Department IS NULL;
```

---

# 9. Count Employees in Each Department

```sql
SELECT Department, COUNT(*) AS TotalEmployees
FROM Employees
GROUP BY Department;
```

---

# 10. Top 5 Highest Paid Employees

```sql
SELECT *
FROM Employees
ORDER BY Salary DESC
LIMIT 5;
```

---

# 11. Employees With Same Salary

```sql
SELECT Salary, COUNT(*)
FROM Employees
GROUP BY Salary
HAVING COUNT(*) > 1;
```

---

# 12. Even Employee IDs

```sql
SELECT *
FROM Employees
WHERE EmployeeID % 2 = 0;
```

---

# 13. Employees Whose First Name Starts With 'A'

```sql
SELECT *
FROM Employees
WHERE FirstName LIKE 'A%';
```

---

# 14. Rank Employees by Salary

```sql
SELECT FirstName, LastName, Salary,
RANK() OVER (ORDER BY Salary DESC) AS RankPosition
FROM Employees;
```

---

# 15. Running Total of Salary

```sql
SELECT EmployeeID, FirstName, Salary,
SUM(Salary) OVER (ORDER BY EmployeeID) AS RunningTotal
FROM Employees;
```


# 30 Scenario-Based SQL Interview Questions

## 1. Get all customers with their orders

```sql
SELECT c.CustomerName, o.OrderID, o.OrderDate
FROM Customers c
JOIN Orders o 
ON c.CustomerID = o.CustomerID;
```

---

# 2. Count total orders placed by each customer

```sql
SELECT c.CustomerName, COUNT(o.OrderID) AS TotalOrders
FROM Customers c
LEFT JOIN Orders o
ON c.CustomerID = o.CustomerID
GROUP BY c.CustomerName;
```

---

# 3. Find customers who never placed any orders

```sql
SELECT c.CustomerName
FROM Customers c
LEFT JOIN Orders o
ON c.CustomerID = o.CustomerID
WHERE o.OrderID IS NULL;
```

---

# 4. Get all products with their category names

```sql
SELECT p.ProductName, c.CategoryName
FROM Products p
JOIN Categories c
ON p.CategoryID = c.CategoryID;
```

---

# 5. Find the most expensive product

```sql
SELECT ProductName, Price
FROM Products
ORDER BY Price DESC
LIMIT 1;
```

---

# 6. Find top 5 expensive products

```sql
SELECT ProductName, Price
FROM Products
ORDER BY Price DESC
LIMIT 5;
```

---

# 7. Get products whose price is greater than average price

```sql
SELECT ProductName, Price
FROM Products
WHERE Price > (SELECT AVG(Price) FROM Products);
```

---

# 8. Count products in each category

```sql
SELECT c.CategoryName, COUNT(p.ProductID) AS TotalProducts
FROM Categories c
LEFT JOIN Products p
ON c.CategoryID = p.CategoryID
GROUP BY c.CategoryName;
```

---

# 9. Total quantity sold per product

```sql
SELECT p.ProductName, SUM(od.Quantity) AS TotalSold
FROM Products p
JOIN OrderDetails od
ON p.ProductID = od.ProductID
GROUP BY p.ProductName;
```

---

# 10. Get employees who handled orders

```sql
SELECT DISTINCT e.FirstName, e.LastName
FROM Employees e
JOIN Orders o
ON e.EmployeeID = o.EmployeeID;
```

---

# 11. Count orders handled by each employee

```sql
SELECT e.FirstName, COUNT(o.OrderID) AS TotalOrders
FROM Employees e
LEFT JOIN Orders o
ON e.EmployeeID = o.EmployeeID
GROUP BY e.FirstName;
```

---

# 12. Find latest order

```sql
SELECT *
FROM Orders
ORDER BY OrderDate DESC
LIMIT 1;
```

---

# 13. Find suppliers and number of products they supply

```sql
SELECT s.SupplierName, COUNT(p.ProductID) AS TotalProducts
FROM Suppliers s
LEFT JOIN Products p
ON s.SupplierID = p.SupplierID
GROUP BY s.SupplierName;
```

---

# 14. Get order details with product names

```sql
SELECT od.OrderID, p.ProductName, od.Quantity
FROM OrderDetails od
JOIN Products p
ON od.ProductID = p.ProductID;
```

---

# 15. Total items in each order

```sql
SELECT OrderID, SUM(Quantity) AS TotalItems
FROM OrderDetails
GROUP BY OrderID;
```

---

# 16. Orders with quantity greater than 20

```sql
SELECT OrderID, SUM(Quantity) AS TotalQty
FROM OrderDetails
GROUP BY OrderID
HAVING SUM(Quantity) > 20;
```

---

# 17. Customer who placed the highest number of orders

```sql
SELECT c.CustomerName, COUNT(o.OrderID) AS TotalOrders
FROM Customers c
JOIN Orders o
ON c.CustomerID = o.CustomerID
GROUP BY c.CustomerName
ORDER BY TotalOrders DESC
LIMIT 1;
```

---

# 18. Products never ordered

```sql
SELECT p.ProductName
FROM Products p
LEFT JOIN OrderDetails od
ON p.ProductID = od.ProductID
WHERE od.ProductID IS NULL;
```

---

# 19. Suppliers from a specific country

```sql
SELECT SupplierName, Country
FROM Suppliers
WHERE Country = 'USA';
```

---

# 20. Total sales amount per product

```sql
SELECT p.ProductName,
SUM(od.Quantity * p.Price) AS TotalSales
FROM Products p
JOIN OrderDetails od
ON p.ProductID = od.ProductID
GROUP BY p.ProductName;
```

---

# 21. Top 3 selling products

```sql
SELECT p.ProductName, SUM(od.Quantity) AS TotalSold
FROM Products p
JOIN OrderDetails od
ON p.ProductID = od.ProductID
GROUP BY p.ProductName
ORDER BY TotalSold DESC
LIMIT 3;
```

---

# 22. Orders placed in a specific year

```sql
SELECT *
FROM Orders
WHERE EXTRACT(YEAR FROM OrderDate) = 2024;
```

---

# 23. Customers and total products they ordered

```sql
SELECT c.CustomerName, SUM(od.Quantity) AS TotalProducts
FROM Customers c
JOIN Orders o
ON c.CustomerID = o.CustomerID
JOIN OrderDetails od
ON o.OrderID = od.OrderID
GROUP BY c.CustomerName;
```

---

# 24. Average product price per category

```sql
SELECT c.CategoryName, AVG(p.Price) AS AvgPrice
FROM Categories c
JOIN Products p
ON c.CategoryID = p.CategoryID
GROUP BY c.CategoryName;
```

---

# 25. Employees earning above average salary

```sql
SELECT FirstName, Salary
FROM Employees
WHERE Salary > (SELECT AVG(Salary) FROM Employees);
```

---

# 26. Second highest salary

```sql
SELECT DISTINCT Salary
FROM Employees
ORDER BY Salary DESC
LIMIT 1 OFFSET 1;
```

---

# 27. Cities having more than 1 customer

```sql
SELECT City, COUNT(*) AS TotalCustomers
FROM Customers
GROUP BY City
HAVING COUNT(*) > 1;
```

---

# 28. Orders with customer and employee information

```sql
SELECT o.OrderID, c.CustomerName, e.FirstName, o.OrderDate
FROM Orders o
JOIN Customers c
ON o.CustomerID = c.CustomerID
JOIN Employees e
ON o.EmployeeID = e.EmployeeID;
```

---

# 29. Total revenue generated per order

```sql
SELECT od.OrderID,
SUM(od.Quantity * p.Price) AS Revenue
FROM OrderDetails od
JOIN Products p
ON od.ProductID = p.ProductID
GROUP BY od.OrderID;
```

---

# 30. List orders with shipper name

```sql
SELECT o.OrderID, s.ShipperName, o.OrderDate
FROM Orders o
JOIN Shippers s
ON o.ShipperID = s.ShipperID;
```


# Top 20 SQL Queries Asked in TCS / Infosys / Accenture

## 1. Find the Second Highest Salary

```sql
SELECT MAX(salary)
FROM employee
WHERE salary < (SELECT MAX(salary) FROM employee);
```

---

# 2. Find the Third Highest Salary

```sql
SELECT DISTINCT salary
FROM employee
ORDER BY salary DESC
LIMIT 1 OFFSET 2;
```

---

# 3. Find Duplicate Records in a Table

```sql
SELECT name, COUNT(*)
FROM employee
GROUP BY name
HAVING COUNT(*) > 1;
```

---

# 4. Delete Duplicate Records

```sql
DELETE FROM employee
WHERE id NOT IN (
   SELECT MIN(id)
   FROM employee
   GROUP BY name
);
```

---

# 5. Find Employees With Salary Greater Than Average

```sql
SELECT *
FROM employee
WHERE salary > (SELECT AVG(salary) FROM employee);
```

---

# 6. Find Department-wise Highest Salary

```sql
SELECT department_id, MAX(salary)
FROM employee
GROUP BY department_id;
```

---

# 7. Show Employee Who Has Highest Salary in Each Department

```sql
SELECT e.*
FROM employee e
JOIN (
     SELECT department_id, MAX(salary) AS max_sal
     FROM employee
     GROUP BY department_id
) d
ON e.department_id = d.department_id
AND e.salary = d.max_sal;
```

---

# 8. Find Employees Who Earn More Than Their Manager

```sql
SELECT e.name
FROM employee e
JOIN employee m
ON e.manager_id = m.employee_id
WHERE e.salary > m.salary;
```

---

# 9. Find Employees Without Department

```sql
SELECT *
FROM employee
WHERE department_id IS NULL;
```

---

# 10. Count Employees in Each Department

```sql
SELECT department_id, COUNT(*)
FROM employee
GROUP BY department_id;
```

---

# 11. Find Top 5 Highest Paid Employees

```sql
SELECT *
FROM employee
ORDER BY salary DESC
LIMIT 5;
```

---

# 12. Find Employees Joined in Last 30 Days

```sql
SELECT *
FROM employee
WHERE hire_date >= CURDATE() - INTERVAL 30 DAY;
```

---

# 13. Find Employees With Same Salary

```sql
SELECT salary, COUNT(*)
FROM employee
GROUP BY salary
HAVING COUNT(*) > 1;
```

---

# 14. Find Employees Who Are Managers

```sql
SELECT *
FROM employee
WHERE employee_id IN (
   SELECT DISTINCT manager_id
   FROM employee
);
```

---

# 15. Find Even Employee IDs

```sql
SELECT *
FROM employee
WHERE employee_id % 2 = 0;
```

---

# 16. Find Employees Whose Name Starts With 'A'

```sql
SELECT *
FROM employee
WHERE name LIKE 'A%';
```

---

# 17. Find Employees Who Never Received Bonus

```sql
SELECT e.*
FROM employee e
LEFT JOIN bonus b
ON e.employee_id = b.employee_id
WHERE b.employee_id IS NULL;
```

---

# 18. Find Customers Who Ordered Every Month

```sql
SELECT customer_id
FROM orders
GROUP BY customer_id
HAVING COUNT(DISTINCT MONTH(order_date)) = 12;
```

---

# 19. Rank Employees by Salary

```sql
SELECT name, salary,
RANK() OVER (ORDER BY salary DESC) AS rank
FROM employee;
```

---

# 20. Find Running Total of Salary

```sql
SELECT name, salary,
SUM(salary) OVER (ORDER BY employee_id) AS running_total
FROM employee;
```


# 30 Scenario-Based SQL Interview Questions

## 1. Find the second highest salary in the employee table.

```sql
SELECT MAX(salary)
FROM employee
WHERE salary < (SELECT MAX(salary) FROM employee);
```

---

# 2. Find the Nth highest salary.

```sql
SELECT salary
FROM employee
ORDER BY salary DESC
LIMIT 1 OFFSET N-1;
```

---

# 3. Find employees earning more than their manager.

```sql
SELECT e.name
FROM employee e
JOIN employee m
ON e.manager_id = m.employee_id
WHERE e.salary > m.salary;
```

---

# 4. Find duplicate records in a table.

```sql
SELECT name, COUNT(*)
FROM employee
GROUP BY name
HAVING COUNT(*) > 1;
```

---

# 5. Delete duplicate records.

```sql
DELETE FROM employee
WHERE id NOT IN (
   SELECT MIN(id)
   FROM employee
   GROUP BY name
);
```

---

# 6. Find the highest salary in each department.

```sql
SELECT department_id, MAX(salary)
FROM employee
GROUP BY department_id;
```

---

# 7. Find employees with the highest salary in each department.

```sql
SELECT *
FROM employee e
WHERE salary = (
   SELECT MAX(salary)
   FROM employee
   WHERE department_id = e.department_id
);
```

---

# 8. Find departments with more than 5 employees.

```sql
SELECT department_id
FROM employee
GROUP BY department_id
HAVING COUNT(*) > 5;
```

---

# 9. Find employees who joined in the last 6 months.

```sql
SELECT *
FROM employee
WHERE hire_date >= CURDATE() - INTERVAL 6 MONTH;
```

---

# 10. Find employees whose salary is greater than the department average.

```sql
SELECT *
FROM employee e
WHERE salary > (
   SELECT AVG(salary)
   FROM employee
   WHERE department_id = e.department_id
);
```

---

# 11. Find the total salary paid in each department.

```sql
SELECT department_id, SUM(salary)
FROM employee
GROUP BY department_id;
```

---

# 12. Find the employee with the longest tenure.

```sql
SELECT *
FROM employee
ORDER BY hire_date
LIMIT 1;
```

---

# 13. Find employees who do not have a manager.

```sql
SELECT *
FROM employee
WHERE manager_id IS NULL;
```

---

# 14. Find employees who are managers.

```sql
SELECT *
FROM employee
WHERE employee_id IN (
   SELECT DISTINCT manager_id
   FROM employee
);
```

---

# 15. Find employees hired in the same year.

```sql
SELECT YEAR(hire_date), COUNT(*)
FROM employee
GROUP BY YEAR(hire_date);
```

---

# 16. Find employees with same salary.

```sql
SELECT salary, COUNT(*)
FROM employee
GROUP BY salary
HAVING COUNT(*) > 1;
```

---

# 17. Find the top 3 highest salaries.

```sql
SELECT DISTINCT salary
FROM employee
ORDER BY salary DESC
LIMIT 3;
```

---

# 18. Find the department with the highest average salary.

```sql
SELECT department_id, AVG(salary) avg_salary
FROM employee
GROUP BY department_id
ORDER BY avg_salary DESC
LIMIT 1;
```

---

# 19. Rank employees based on salary.

```sql
SELECT name, salary,
RANK() OVER (ORDER BY salary DESC) AS rank
FROM employee;
```

---

# 20. Find running total of salary.

```sql
SELECT name, salary,
SUM(salary) OVER (ORDER BY employee_id) AS running_total
FROM employee;
```

---

# 21. Find employees who joined before their manager.

```sql
SELECT e.name
FROM employee e
JOIN employee m
ON e.manager_id = m.employee_id
WHERE e.hire_date < m.hire_date;
```

---

# 22. Find employees who never received bonus.

```sql
SELECT e.*
FROM employee e
LEFT JOIN bonus b
ON e.employee_id = b.employee_id
WHERE b.employee_id IS NULL;
```

---

# 23. Find departments without employees.

```sql
SELECT d.department_id
FROM department d
LEFT JOIN employee e
ON d.department_id = e.department_id
WHERE e.employee_id IS NULL;
```

---

# 24. Find customers who placed more than 5 orders.

```sql
SELECT customer_id, COUNT(*)
FROM orders
GROUP BY customer_id
HAVING COUNT(*) > 5;
```

---

# 25. Find customers who never placed orders.

```sql
SELECT c.*
FROM customers c
LEFT JOIN orders o
ON c.customer_id = o.customer_id
WHERE o.customer_id IS NULL;
```

---

# 26. Find the first order placed by each customer.

```sql
SELECT customer_id, MIN(order_date)
FROM orders
GROUP BY customer_id;
```

---

# 27. Find employees with odd employee IDs.

```sql
SELECT *
FROM employee
WHERE employee_id % 2 <> 0;
```

---

# 28. Find employees whose name starts with 'S'.

```sql
SELECT *
FROM employee
WHERE name LIKE 'S%';
```

---

# 29. Find employees whose salary is between 50k and 80k.

```sql
SELECT *
FROM employee
WHERE salary BETWEEN 50000 AND 80000;
```

---

# 30. Find employees working in multiple departments.

```sql
SELECT employee_id
FROM employee_department
GROUP BY employee_id
HAVING COUNT(DISTINCT department_id) > 1;
```


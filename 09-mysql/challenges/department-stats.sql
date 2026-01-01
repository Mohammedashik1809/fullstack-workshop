CREATE TABLE employees (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    department VARCHAR(50),
    salary DECIMAL(10,2),
    hire_date DATE,
    manager_id INT
);

INSERT INTO employees(name, department, salary, hire_date, manager_id)
VALUES ('Arun Kumar', 'IT', 55000.00, '2022-03-15', NULL),
('Priya Sharma', 'IT', 62000.00, '2021-07-10', 1),
('Rahul Verma', 'IT', 58000.00, '2023-01-20', 1);

SELECT
    department,
    COUNT(id) AS employee_count,
    ROUND(AVG(salary), 2) AS avg_salary,
    MAX(salary) AS max_salary
FROM employees
GROUP BY department
HAVING COUNT(id) > 2;
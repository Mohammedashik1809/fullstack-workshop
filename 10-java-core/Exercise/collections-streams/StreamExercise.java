package collections_streams;

import java.util.*;
import java.util.stream.*;

public class StreamExercise {

    record Employee(
            Long id,
            String name,
            String department,
            double salary,
            int yearsOfService,
            List<String> skills
    ) {}

    public static void main(String[] args) {

        List<Employee> employees = List.of(
            new Employee(1L, "Alice", "Engineering", 85000, 5, List.of("Java", "Python")),
            new Employee(2L, "Bob", "Engineering", 75000, 3, List.of("Java", "JavaScript")),
            new Employee(3L, "Charlie", "Sales", 65000, 7, List.of("Communication", "CRM")),
            new Employee(4L, "Diana", "Engineering", 95000, 8, List.of("Java", "Kotlin", "Go")),
            new Employee(5L, "Eve", "HR", 55000, 2, List.of("Recruiting", "Communication")),
            new Employee(6L, "Frank", "Sales", 70000, 4, List.of("Negotiation", "CRM"))
        );

        // 1. Filter & Sort
        System.out.println(
            employees.stream()
                    .filter(e -> e.department().equals("Engineering"))
                    .sorted(Comparator.comparing(Employee::salary).reversed())
                    .map(Employee::name)
                    .toList()
        );

        // 2. Map
        System.out.println(
            employees.stream()
                    .map(e -> e.name().toUpperCase())
                    .toList()
        );

        // 3. Grouping
        System.out.println(
            employees.stream()
                    .collect(Collectors.groupingBy(
                            Employee::department,
                            Collectors.mapping(Employee::name, Collectors.toList())
                    ))
        );

        // 4. Statistics
        double totalSalary = employees.stream().mapToDouble(Employee::salary).sum();
        System.out.println("Total: $" + totalSalary);

        System.out.println(
            employees.stream()
                    .collect(Collectors.groupingBy(
                            Employee::department,
                            Collectors.averagingDouble(Employee::salary)
                    ))
        );

        System.out.println(
            employees.stream()
                    .max(Comparator.comparing(Employee::salary))
                    .get()
        );

        // 5. FlatMap
        System.out.println(
            employees.stream()
                    .flatMap(e -> e.skills().stream())
                    .distinct()
                    .toList()
        );

        // 6. Partitioning
        System.out.println(
            employees.stream()
                    .collect(Collectors.partitioningBy(e -> e.salary() > 70000))
        );

        // 7. Reduce
        int totalYears = employees.stream()
                .map(Employee::yearsOfService)
                .reduce(0, Integer::sum);
        System.out.println(totalYears);

        // 8. Complex
        System.out.println(
            employees.stream()
                    .collect(Collectors.groupingBy(
                            Employee::department,
                            Collectors.averagingDouble(Employee::salary)
                    ))
                    .entrySet().stream()
                    .filter(e -> e.getValue() > 70000)
                    .map(Map.Entry::getKey)
                    .toList()
        );
    }
}

package file_processing;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CsvFileProcessor {

    public List<Employee> readEmployees(Path filePath) {
        if (!Files.exists(filePath)) {
            throw new CsvProcessingException("CSV file not found: " + filePath, null);
        }

        List<Employee> employees = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
            if (lines.size() <= 1) return employees;

            for (int i = 1; i < lines.size(); i++) {
                String line = lines.get(i);
                try {
                    String[] cols = line.split(",");
                    if (cols.length != 6) {
                        System.err.println("Invalid column count at line " + (i + 1));
                        continue;
                    }

                    Employee emp = new Employee(
                            Integer.parseInt(cols[0].trim()),
                            cols[1].trim(),
                            cols[2].trim(),
                            cols[3].trim(),
                            Double.parseDouble(cols[4].trim()),
                            LocalDate.parse(cols[5].trim())
                    );
                    employees.add(emp);

                } catch (NumberFormatException | DateTimeParseException e) {
                    System.err.println("Invalid data at line " + (i + 1) + ": " + e.getMessage());
                }
            }
        } catch (IOException e) {
            throw new CsvProcessingException("Error reading CSV file", e);
        }

        return employees;
    }

    public void writeEmployees(Path filePath, List<Employee> employees) {
        try (BufferedWriter writer =
                     Files.newBufferedWriter(filePath, StandardCharsets.UTF_8)) {

            writer.write("id,firstName,lastName,department,salary,hireDate");
            writer.newLine();

            for (Employee e : employees) {
                writer.write(String.format(
                        "%d,%s,%s,%s,%.2f,%s",
                        e.id(), e.firstName(), e.lastName(),
                        e.department(), e.salary(), e.hireDate()
                ));
                writer.newLine();
            }
        } catch (IOException e) {
            throw new CsvProcessingException("Error writing CSV file", e);
        }
    }

    public void filterAndWrite(Path source, Path destination,
                               Predicate<Employee> filter) {
        List<Employee> filtered = readEmployees(source).stream()
                .filter(filter)
                .collect(Collectors.toList());
        writeEmployees(destination, filtered);
    }

    public void generateDepartmentReport(Path source, Path reportPath) {
        List<Employee> employees = readEmployees(source);

        Map<String, List<Employee>> byDept =
                employees.stream().collect(Collectors.groupingBy(Employee::department));

        try (BufferedWriter writer =
                     Files.newBufferedWriter(reportPath, StandardCharsets.UTF_8)) {

            writer.write("Department,EmployeeCount,TotalSalary,AverageSalary");
            writer.newLine();

            for (var entry : byDept.entrySet()) {
                double total = entry.getValue().stream()
                        .mapToDouble(Employee::salary).sum();
                int count = entry.getValue().size();
                double avg = total / count;

                writer.write(String.format(
                        "%s,%d,%.2f,%.2f",
                        entry.getKey(), count, total, avg
                ));
                writer.newLine();
            }
        } catch (IOException e) {
            throw new CsvProcessingException("Error writing report", e);
        }
    }
}

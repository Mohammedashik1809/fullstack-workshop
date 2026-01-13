package file_processing;

import java.time.LocalDate;

public record Employee(
        int id,
        String firstName,
        String lastName,
        String department,
        double salary,
        LocalDate hireDate
) {}

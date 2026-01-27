package com.example.taskmanager.ui;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.model.TaskStatus;
import com.example.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class ConsoleUI {

    private final TaskService taskService;
    private final Scanner scanner;

    @Autowired
    public ConsoleUI(TaskService taskService) {
        this.taskService = taskService;
        this.scanner = new Scanner(System.in);
        System.out.println("ConsoleUI initialized by Spring");
    }

    // ENTRY POINT FOR UI
    public void run() {
        boolean running = true;

        System.out.println("\n=================================");
        System.out.println("      TASK MANAGER SYSTEM");
        System.out.println("=================================");

        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();

            try {
                switch (choice) {
                    case "1" -> createTask();
                    case "2" -> listAllTasks();
                    case "3" -> viewTaskById();
                    case "4" -> updateTask();
                    case "5" -> changeTaskStatus();
                    case "6" -> deleteTask();
                    case "7" -> listTasksByStatus();
                    case "0" -> {
                        running = false;
                        System.out.println("Exiting Task Manager. Goodbye!");
                    }
                    default -> System.out.println("Invalid option. Try again.");
                }
            }
            catch (Exception e) {
                System.out.println("Error occurred while processing request");
                e.printStackTrace();
            }

        }
    }

    // ================= MENU =================

    private void printMenu() {
        System.out.println("\n--- MENU ---");
        System.out.println("1. Create Task");
        System.out.println("2. List All Tasks");
        System.out.println("3. View Task by ID");
        System.out.println("4. Update Task");
        System.out.println("5. Change Task Status");
        System.out.println("6. Delete Task");
        System.out.println("7. List Tasks by Status");
        System.out.println("0. Exit");
        System.out.print("Choose option: ");
    }

    // ================= ACTIONS =================

    private void createTask() {
        System.out.print("Enter task title: ");
        String title = scanner.nextLine();

        System.out.print("Enter task description: ");
        String description = scanner.nextLine();

        Task task = taskService.createTask(title, description);
        System.out.println("Task created successfully:");
        System.out.println(task);
    }

    private void listAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }
        System.out.println("\n--- ALL TASKS ---");
        tasks.forEach(System.out::println);
    }

    private void viewTaskById() {
        Long id = readLong("Enter task ID: ");
        Optional<Task> task = taskService.getTaskById(id);

        if (task.isPresent()) {
            System.out.println("\n--- TASK DETAILS ---");
            System.out.println(task.get());
        } else {
            System.out.println("Task not found with ID: " + id);
        }
    }

    private void updateTask() {
        Long id = readLong("Enter task ID: ");

        System.out.print("New title (press Enter to skip): ");
        String title = scanner.nextLine();

        System.out.print("New description (press Enter to skip): ");
        String description = scanner.nextLine();

        Task updated = taskService.updateTask(
                id,
                title.isBlank() ? null : title,
                description.isBlank() ? null : description
        );

        System.out.println("Task updated:");
        System.out.println(updated);
    }

    private void changeTaskStatus() {
        Long id = readLong("Enter task ID: ");
        TaskStatus status = readStatus();

        Task updated = taskService.updateTaskStatus(id, status);
        System.out.println("Task status updated:");
        System.out.println(updated);
    }

    private void deleteTask() {
        Long id = readLong("Enter task ID: ");
        System.out.print("Are you sure you want to delete? (y/n): ");

        if (scanner.nextLine().equalsIgnoreCase("y")) {
            taskService.deleteTask(id);
            System.out.println("Task deleted successfully.");
        } else {
            System.out.println("Delete cancelled.");
        }
    }

    private void listTasksByStatus() {
        TaskStatus status = readStatus();
        List<Task> tasks = taskService.getTasksByStatus(status);

        if (tasks.isEmpty()) {
            System.out.println("No tasks found with status: " + status);
        } else {
            tasks.forEach(System.out::println);
        }
    }

    // ================= HELPERS =================

    private Long readLong(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Long.parseLong(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private TaskStatus readStatus() {
        System.out.println("1. PENDING");
        System.out.println("2. IN_PROGRESS");
        System.out.println("3. COMPLETED");
        System.out.println("4. CANCELLED");
        System.out.print("Choose status: ");

        return switch (scanner.nextLine()) {
            case "1" -> TaskStatus.PENDING;
            case "2" -> TaskStatus.IN_PROGRESS;
            case "3" -> TaskStatus.COMPLETED;
            case "4" -> TaskStatus.CANCELLED;
            default -> throw new IllegalArgumentException("Invalid status option");
        };
    }
}

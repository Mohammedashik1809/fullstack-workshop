package com.example.taskmanager;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.example.taskmanager.config.AppConfig;
import com.example.taskmanager.ui.ConsoleUI;

public class Main {

    public static void main(String[] args) {

        // 1. Start Spring container
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        System.out.println("Spring started successfully");

        // 2. Get ConsoleUI bean from Spring
        ConsoleUI consoleUI = context.getBean(ConsoleUI.class);

        // 3. Run the console menu
        consoleUI.run();

        // 4. Close Spring container after exit
        context.close();
    }
}



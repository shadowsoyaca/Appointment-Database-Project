package com.appointmentProject.backend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.sql.Connection;
import java.sql.DriverManager;
/*
    A quick test to see if backend connects to database.

 */

@Component
public class DatabaseTest implements CommandLineRunner {

    @Override
    public void run(String... args) {
        // Replace with your same connection info (from application.properties)
        String url = "jdbc:mysql://localhost:3306/appointmentproject?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String pass = "admin";

        try (Connection conn = DriverManager.getConnection(url, user, pass)) {
            if (conn != null && !conn.isClosed()) {
                System.out.println("Database connected successfully!");
            }
        } catch (Exception e) {
            System.out.println("Database connection failed: " + e.getMessage());
        }
    }
}


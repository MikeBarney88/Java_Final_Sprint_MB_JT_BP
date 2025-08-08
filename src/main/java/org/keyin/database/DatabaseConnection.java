package org.keyin.database;

import org.keyin.customlogger.CustomLogger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DatabaseConnection {
    // You only need to change the name of the database in the URL, unless PG runs on another port on your system. Default port is 5432 
    private static final String URL = "jdbc:postgresql://localhost:5432/s3javafinal";
    private static final String USER = "postgres";
    private static final String PASSWORD = "2024";

    public static Connection getConnection() throws SQLException, SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void main(String[] args) throws IOException {
        try {
            DatabaseConnection.getConnection();
            System.out.println("Connection successful");
            CustomLogger.logInfo("Connected to database at" + DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm:ss").format(LocalDateTime.now()));
        } catch (SQLException e) {
            CustomLogger.logError(e.getMessage());
            e.printStackTrace();
        }
    }
}

package org.keyin.user;

import org.keyin.customlogger.CustomLogger;
import org.keyin.database.DatabaseConnection;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class UserDAO {
    public static ArrayList<User> selectAllUsers() throws SQLException {
        String sql = "SELECT * FROM users";
        DriverManager DatabaseConnector;
        ArrayList<User> allUserResults = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    allUserResults.add(new User(
                            rs.getInt("user_id"),
                            rs.getString("user_username"),
                            rs.getString("user_password"),
                            rs.getString("user_email"),
                            rs.getString("user_phonenumber"),
                            rs.getString("user_address"),
                            rs.getString("user_role")
                    ));
                }
            }
        }

        return allUserResults;
    }


    public static User selectUserByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM users WHERE user_username = ?";
        DriverManager DatabaseConnector;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getInt("user_id"),
                            rs.getString("user_username"),
                            rs.getString("user_password"),
                            rs.getString("user_email"),
                            rs.getString("user_phonenumber"),
                            rs.getString("user_address"),
                            rs.getString("user_role")
                    );
                }
            }
        }

       return null;
    }


    public static void insertNewUser(User newUser) throws SQLException, IOException {
        String sql = "INSERT INTO users(user_username, user_password, user_email, user_phonenumber, user_address, user_role) VALUES (?, ?, ?, ?, ?, ?) WHERE NOT EXISTS (SELECT 1 FROM users WHERE user_username = ?)RETURNING *";
        DriverManager DatabaseConnector;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newUser.getUsername());
            pstmt.setString(2, newUser.getPassword());
            pstmt.setString(3, newUser.getEmail());
            pstmt.setString(4, newUser.getPhoneNumber());
            pstmt.setString(5, newUser.getAddress());
            pstmt.setString(6, newUser.getRole());
            pstmt.setString(7, newUser.getUsername());
            try {
                pstmt.executeQuery();
            } catch (SQLException e) {
                CustomLogger.logError(e.getMessage());
            }
        }
    }


    public static void deleteUser(User user) throws SQLException, IOException {
        String sql = "DELETE FROM users WHERE user_id = ?";
        DriverManager DatabaseConnector;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, user.getID());
            try {
                pstmt.execute();
            } catch (SQLException e) {
                CustomLogger.logError(e.getMessage());
            }
        }
    }
}

package org.keyin.user;

import org.keyin.database.DatabaseConnection;

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
                            rs.getString("user_name"),
                            rs.getString("user_password"),
                            rs.getString("user_email"),
                            rs.getString("user_phone_number"),
                            rs.getString("user_address"),
                            rs.getString("user_role")
                    ));
                }
            }
        }

        return allUserResults;
    }


    public static User selectUserByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM users WHERE user_name = ?";
        DriverManager DatabaseConnector;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getInt("user_id"),
                            rs.getString("user_name"),
                            rs.getString("user_password"),
                            rs.getString("user_email"),
                            rs.getString("user_phone_number"),
                            rs.getString("user_address"),
                            rs.getString("user_role")
                    );
                }
            }
        }

       return null;
    }
}

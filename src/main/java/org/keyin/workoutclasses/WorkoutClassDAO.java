package org.keyin.workoutclasses;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO for handling workout class-related database operations.
 */
public class WorkoutClassDAO {
    private Connection conn;

    /**
     * Initializes the DAO with a database connection.
     *
     * @param conn the database connection
     */
    public WorkoutClassDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * Adds a new workout class to the database.
     *
     * @param workoutClass the class to be added
     * @throws SQLException if any SQL error occurs
     */
    public void addWorkoutClass(WorkoutClass workoutClass) throws SQLException {
        String sql = "INSERT INTO workoutclasses (workoutClassType, workoutClassDescription, trainerID) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, workoutClass.getWorkoutClassType());
            stmt.setString(2, workoutClass.getWorkoutClassDescription());
            stmt.setInt(3, workoutClass.getTrainerId());
            stmt.executeUpdate();
        }
    }

    /**
     * Retrieves all workout classes from the database.
     *
     * @return a list of WorkoutClass objects
     * @throws SQLException if any SQL error occurs
     */
    public List<WorkoutClass> getAllWorkoutClasses() throws SQLException {
        List<WorkoutClass> classes = new ArrayList<>();
        String sql = "SELECT * FROM workoutclasses";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                WorkoutClass wc = new WorkoutClass(
                        rs.getInt("workoutClassID"),
                        rs.getString("workoutClassType"),
                        rs.getString("workoutClassDescription"),
                        rs.getInt("trainerID")
                );
                classes.add(wc);
            }
        }
        return classes;
    }

    /**
     * Deletes a workout class by its ID.
     *
     * @param id the class ID to delete
     * @throws SQLException if any SQL error occurs
     */
    public void deleteWorkoutClass(int id) throws SQLException {
        String sql = "DELETE FROM workoutclasses WHERE workoutClassID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}


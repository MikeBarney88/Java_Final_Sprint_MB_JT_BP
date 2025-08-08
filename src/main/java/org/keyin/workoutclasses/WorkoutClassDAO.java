package org.keyin.workoutclasses;

import org.keyin.user.childclasses.Trainer;

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
        String sql = "INSERT INTO workout_classes (workoutClass_type, workoutClass_description, trainer_id) VALUES (?, ?, ?)";
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
        String sql = "SELECT * FROM workout_classes";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                WorkoutClass wc = new WorkoutClass(
                        rs.getInt("workoutClass_id"),
                        rs.getString("workoutClass_type"),
                        rs.getString("workoutClass_description"),
                        rs.getInt("trainer_id")
                );
                classes.add(wc);
            }
        }
        return classes;
    }

    /**
     * Retrieves all workout classes taught by a specific Trainer from the database.
     *
     * @param user the trainer to retrieve classes for
     *
     * @return a list of WorkoutClass objects
     * @throws SQLException if any SQL error occurs
     */
    public List<WorkoutClass> getAllTrainerWorkoutClasses(Trainer user) throws SQLException {
        List<WorkoutClass> classes = new ArrayList<>();
        String sql = "SELECT * FROM workout_classes WHERE trainer_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, user.getID());
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                WorkoutClass wc = new WorkoutClass(
                        rs.getInt("workoutClass_id"),
                        rs.getString("workoutClass_type"),
                        rs.getString("workoutClass_description"),
                        rs.getInt("trainer_id")
                );
                classes.add(wc);
            }
        }
        return classes;
    }

    /**
     * Updates a workout class by its ID.
     *
     * @param id the class ID to delete
     * @param updatedClassType the new type of the updated class
     * @param updatedClassDescription the new description of the updated class
     * @throws SQLException if any SQL error occurs
     */
    public void updateWorkoutClass(int id, String updatedClassType, String updatedClassDescription) throws SQLException {
        String sql = "UPDATE workout_classes WHERE workoutClass_id = ? SET workoutClass_type = ?, workoutClass_description = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.setString(2, updatedClassType);
            stmt.setString(3, updatedClassDescription);
            stmt.executeUpdate();
        }
    }

    /**
     * Deletes a workout class by its ID.
     *
     * @param id the class ID to delete
     * @throws SQLException if any SQL error occurs
     */
    public void deleteWorkoutClass(int id) throws SQLException {
        String sql = "DELETE FROM workout_classes WHERE workoutClass_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}


package org.keyin.workoutclasses;

import org.keyin.customlogger.CustomLogger;
import org.keyin.user.childclasses.Trainer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Service layer containing business logic for workout classes.
 */
public class WorkoutClassService {
    private WorkoutClassDAO dao;

    /**
     * Constructor to initialize the service with DAO.
     */
    public WorkoutClassService(WorkoutClassDAO dao) {
        this.dao = dao;
    }

    /**
     * Adds a new workout class through DAO.
     *
     * @param wc the WorkoutClass to add
     */
    public void createWorkoutClass(WorkoutClass wc) throws IOException {
        try {
            dao.addWorkoutClass(wc);
            System.out.println("Workout class added.");
        } catch (Exception e) {
            System.out.println("Failed to add workout class: " + e.getMessage());
            CustomLogger.logError(e.getMessage());
        }
    }

    /**
     * Updates a workout class through DAO.
     *
     * @param id the workoutClass_id of the class to update
     * @param updatedType the new type for the updated class
     * @param updatedDescription the new description for the updated class
     */
    public void updateWorkoutClass(int id, String updatedType, String updatedDescription) throws SQLException {
        dao.updateWorkoutClass(id, updatedType, updatedDescription);
    }

    /**
     * Deletes a workout class through DAO.
     *
     * @param id the workoutClass_id of the class to delete.
     */
    public void deleteWorkoutClass(int id) throws SQLException {
        dao.deleteWorkoutClass(id);
    }

    /**
     * Lists all workout classes in the system.
     */
    public void listAllWorkoutClasses() throws IOException {
        try {
            List<WorkoutClass> classes = dao.getAllWorkoutClasses();
            for (WorkoutClass wc : classes) {
                System.out.printf("ID: %d | Type: %s | Description: %s | Trainer ID: %d%n",
                        wc.getWorkoutClassId(), wc.getWorkoutClassType(), wc.getWorkoutClassDescription(), wc.getTrainerId());
            }
        } catch (Exception e) {
            System.out.println("Error listing workout classes: " + e.getMessage());
            CustomLogger.logError(e.getMessage());
        }
    }

    /**
     * Lists all workout classes in the system taught by a specific trainer.
     */
    public void listAllTrainerWorkoutClasses(Trainer user) throws IOException {
        try {
            List<WorkoutClass> classes = dao.getAllTrainerWorkoutClasses(user);
            for (WorkoutClass wc : classes) {
                System.out.printf("ID: %d | Type: %s | Description: %s | Trainer ID: %d%n",
                        wc.getWorkoutClassId(), wc.getWorkoutClassType(), wc.getWorkoutClassDescription(), wc.getTrainerId());
            }
        } catch (Exception e) {
            System.out.println("Error listing workout classes: " + e.getMessage());
            CustomLogger.logError(e.getMessage());
        }
    }
}
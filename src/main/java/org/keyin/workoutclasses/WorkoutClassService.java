package org.keyin.workoutclasses;

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
    public void createWorkoutClass(WorkoutClass wc) {
        try {
            dao.addWorkoutClass(wc);
            System.out.println("Workout class added.");
        } catch (Exception e) {
            System.out.println("Failed to add workout class: " + e.getMessage());
        }
    }

    /**
     * Lists all workout classes in the system.
     */
    public void listAllWorkoutClasses() {
        try {
            List<WorkoutClass> classes = dao.getAllWorkoutClasses();
            for (WorkoutClass wc : classes) {
                System.out.printf("ID: %d | Type: %s | Description: %s | Trainer ID: %d%n",
                        wc.getWorkoutClassId(), wc.getWorkoutClassType(), wc.getWorkoutClassDescription(), wc.getTrainerId());
            }
        } catch (Exception e) {
            System.out.println("Error listing workout classes: " + e.getMessage());
        }
    }
}


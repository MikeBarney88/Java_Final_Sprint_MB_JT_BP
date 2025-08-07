package org.keyin.workoutclasses;

/**
 * Represents a workout class held by a trainer.
 */
public class WorkoutClass {
    private int workoutClassId;
    private String workoutClassType;
    private String workoutClassDescription;
    private int trainerId;

    /**
     * Full constructor.
     *
     * @param id         the class ID
     * @param type       the type of workout
     * @param description the description of the class
     * @param trainerId  the trainer's user ID
     */
    public WorkoutClass(int id, String type, String description, int trainerId) {
        this.workoutClassId = id;
        this.workoutClassType = type;
        this.workoutClassDescription = description;
        this.trainerId = trainerId;
    }

    /** Default constructor */
    public WorkoutClass() {}

    /** Getters and setters */

    public int getWorkoutClassId() { return workoutClassId; }

    public void setWorkoutClassId(int workoutClassId) { this.workoutClassId = workoutClassId; }

    public String getWorkoutClassType() { return workoutClassType; }

    public void setWorkoutClassType(String workoutClassType) { this.workoutClassType = workoutClassType; }

    public String getWorkoutClassDescription() { return workoutClassDescription; }

    public void setWorkoutClassDescription(String workoutClassDescription) { this.workoutClassDescription = workoutClassDescription; }

    public int getTrainerId() { return trainerId; }

    public void setTrainerId(int trainerId) { this.trainerId = trainerId; }
}


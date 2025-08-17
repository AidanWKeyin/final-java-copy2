package service.models;

public class WorkoutClass {
    private int workoutClassId;
    private String type;
    private String description;
    private int trainerId;

    public WorkoutClass(int workoutClassId, String type, String description, int trainerId) {
        this.workoutClassId = workoutClassId;
        this.type = type;
        this.description = description;
        this.trainerId = trainerId;
    }

    // Getters
    public int getWorkoutClassId() { return workoutClassId; }
    public String getType() { return type; }
    public String getDescription() { return description; }
    public int getTrainerId() { return trainerId; }

    // Setters
    public void setType(String type) { this.type = type; }
    public void setDescription(String description) { this.description = description; }
}

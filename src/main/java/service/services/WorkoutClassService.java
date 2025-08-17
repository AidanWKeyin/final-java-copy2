package service.services;

import service.dao.WorkoutClassDAO;
import service.models.WorkoutClass;
import service.utils.LoggerUtil;

import java.util.List;
import java.util.Scanner;

public class WorkoutClassService {

    private WorkoutClassDAO workoutClassDAO = new WorkoutClassDAO();

    // View all classes
    public void viewAllClasses() {
        List<WorkoutClass> classes = workoutClassDAO.getAllClasses();
        if (classes.isEmpty()) {
            System.out.println("No workout classes found.");
        } else {
            System.out.println("\n--- All Workout Classes ---");
            for (WorkoutClass c : classes) {
                System.out.println("ID: " + c.getWorkoutClassId() +
                        ", Type: " + c.getType() +
                        ", Description: " + c.getDescription() +
                        ", Trainer ID: " + c.getTrainerId());
            }
        }
        LoggerUtil.logInfo("Viewed all workout classes.");
    }

    // Add a new workout class
    public void addClass(Scanner scanner, int trainerId) {
        System.out.print("Enter class type: ");
        String type = scanner.nextLine();

        System.out.print("Enter class description: ");
        String description = scanner.nextLine();

        WorkoutClass workoutClass = new WorkoutClass(0, type, description, trainerId);
        workoutClassDAO.createClass(workoutClass);

        System.out.println("Workout class added successfully!");
        LoggerUtil.logInfo("Added new workout class: " + type + " by trainer ID: " + trainerId);
    }

    // Update an existing class
    public void updateClass(Scanner scanner) {
        System.out.print("Enter the class ID to update: ");
        int id = readInt(scanner);

        WorkoutClass workoutClass = workoutClassDAO.getClassById(id);
        if (workoutClass == null) {
            System.out.println("Workout class not found.");
            return;
        }

        System.out.println("Updating class: " + workoutClass.getType());

        System.out.print("Enter new class type (current: " + workoutClass.getType() + "): ");
        String type = scanner.nextLine();
        if (!type.isEmpty()) workoutClass.setType(type);

        System.out.print("Enter new description (current: " + workoutClass.getDescription() + "): ");
        String description = scanner.nextLine();
        if (!description.isEmpty()) workoutClass.setDescription(description);

        workoutClassDAO.updateClass(workoutClass);
        System.out.println("Workout class updated successfully!");
        LoggerUtil.logInfo("Updated workout class ID: " + id);
    }

    // Delete a class
    public void deleteClass(Scanner scanner) {
        System.out.print("Enter the class ID to delete: ");
        int id = readInt(scanner);

        workoutClassDAO.deleteClass(id);
        System.out.println("Workout class deleted successfully!");
        LoggerUtil.logInfo("Deleted workout class ID: " + id);
    }

    // ------------------ Utility ------------------
    private int readInt(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Enter a number: ");
            scanner.nextLine();
        }
        int num = scanner.nextInt();
        scanner.nextLine(); // consume newline
        return num;
    }
}

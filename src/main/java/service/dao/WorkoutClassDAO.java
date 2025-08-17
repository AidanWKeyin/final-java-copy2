package service.dao;

import service.models.WorkoutClass;
import service.utils.DBConnection;
import service.utils.LoggerUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkoutClassDAO {

    public void createClass(WorkoutClass workoutClass) {
        String sql = "INSERT INTO workoutclasses(type,description,trainerid) VALUES (?,?,?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, workoutClass.getType());
            stmt.setString(2, workoutClass.getDescription());
            stmt.setInt(3, workoutClass.getTrainerId());
            stmt.executeUpdate();
            LoggerUtil.logInfo("Workout class added: " + workoutClass.getType());

        } catch (SQLException e) {
            LoggerUtil.logError("Error creating class: " + e.getMessage());
        }
    }

    public List<WorkoutClass> getAllClasses() {
        List<WorkoutClass> classes = new ArrayList<>();
        String sql = "SELECT * FROM workoutclasses";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                WorkoutClass workoutClass = new WorkoutClass(
                        rs.getInt("workoutclassid"),
                        rs.getString("type"),
                        rs.getString("description"),
                        rs.getInt("trainerid")
                );
                classes.add(workoutClass);
            }

        } catch (SQLException e) {
            LoggerUtil.logError("Error fetching classes: " + e.getMessage());
        }
        return classes;
    }

    // New method: get class by ID
    public WorkoutClass getClassById(int id) {
        String sql = "SELECT * FROM workoutclasses WHERE workoutclassid = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new WorkoutClass(
                        rs.getInt("workoutclassid"),
                        rs.getString("type"),
                        rs.getString("description"),
                        rs.getInt("trainerid")
                );
            }

        } catch (SQLException e) {
            LoggerUtil.logError("Error fetching class by ID: " + e.getMessage());
        }
        return null;
    }

    public void updateClass(WorkoutClass workoutClass) {
        String sql = "UPDATE workoutclasses SET type=?, description=?, trainerid=? WHERE workoutclassid=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, workoutClass.getType());
            stmt.setString(2, workoutClass.getDescription());
            stmt.setInt(3, workoutClass.getTrainerId());
            stmt.setInt(4, workoutClass.getWorkoutClassId());
            stmt.executeUpdate();
            LoggerUtil.logInfo("Workout class updated: " + workoutClass.getType());

        } catch (SQLException e) {
            LoggerUtil.logError("Error updating class: " + e.getMessage());
        }
    }

    public void deleteClass(int classId) {
        String sql = "DELETE FROM workoutclasses WHERE workoutclassid=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, classId);
            stmt.executeUpdate();
            LoggerUtil.logInfo("Workout class deleted ID: " + classId);

        } catch (SQLException e) {
            LoggerUtil.logError("Error deleting class: " + e.getMessage());
        }
    }
}

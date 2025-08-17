package service.dao;

import service.models.User;
import service.utils.DBConnection;
import service.utils.LoggerUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public void addUser(User user) {
        String sql = "INSERT INTO users(userid, username, password, email, phonenumber, address, role) VALUES (?,?,?,?,?,?,?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, user.getUserId());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getPhoneNumber());
            stmt.setString(6, user.getAddress());
            stmt.setString(7, user.getRole());

            stmt.executeUpdate();
            LoggerUtil.logInfo("User added: " + user.getUsername());

        } catch (SQLException e) {
            LoggerUtil.logError("Error adding user: " + e.getMessage());
        }
    }

    public User getUserByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("userid"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("phonenumber"),
                        rs.getString("address"),
                        rs.getString("role")
                );
            }
        } catch (SQLException e) {
            LoggerUtil.logError("Error fetching user: " + e.getMessage());
        }
        return null;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                users.add(new User(
                        rs.getInt("userid"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("phonenumber"),
                        rs.getString("address"),
                        rs.getString("role")
                ));
            }
        } catch (SQLException e) {
            LoggerUtil.logError("Error fetching users: " + e.getMessage());
        }
        return users;
    }

    public void deleteUser(int userId) {
        String sql = "DELETE FROM users WHERE userid=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            stmt.executeUpdate();
            LoggerUtil.logInfo("Deleted user ID: " + userId);

        } catch (SQLException e) {
            LoggerUtil.logError("Error deleting user: " + e.getMessage());
        }
    }

    public int generateNewId() {
        String sql = "SELECT MAX(userid) AS maxid FROM users";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) return rs.getInt("maxid") + 1;
        } catch (SQLException e) {
            LoggerUtil.logError("Error generating new user ID: " + e.getMessage());
        }
        return 1;
    }
}

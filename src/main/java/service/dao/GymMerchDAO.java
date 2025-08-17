package service.dao;

import service.models.GymMerch;
import service.utils.DBConnection;
import service.utils.LoggerUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GymMerchDAO {

    public void addMerch(GymMerch merch) {
        String sql = "INSERT INTO gymmerch(merchid, merchname, merchtype, merchprice, quantityinstock) VALUES (?,?,?,?,?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, merch.getMerchId());
            stmt.setString(2, merch.getName());
            stmt.setString(3, merch.getType());
            stmt.setDouble(4, merch.getPrice());
            stmt.setInt(5, merch.getQuantityInStock());
            stmt.executeUpdate();
            LoggerUtil.logInfo("Merch added: " + merch.getName());

        } catch (SQLException e) {
            LoggerUtil.logError("Error adding merch: " + e.getMessage());
        }
    }

    public List<GymMerch> getAllMerch() {
        List<GymMerch> list = new ArrayList<>();
        String sql = "SELECT * FROM gymmerch";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new GymMerch(
                        rs.getInt("merchid"),
                        rs.getString("merchname"),
                        rs.getString("merchtype"),
                        rs.getDouble("merchprice"),
                        rs.getInt("quantityinstock")
                ));
            }
        } catch (SQLException e) {
            LoggerUtil.logError("Error fetching merch: " + e.getMessage());
        }
        return list;
    }

    public void updateMerch(GymMerch merch) {
        String sql = "UPDATE gymmerch SET merchname=?, merchtype=?, merchprice=?, quantityinstock=? WHERE merchid=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, merch.getName());
            stmt.setString(2, merch.getType());
            stmt.setDouble(3, merch.getPrice());
            stmt.setInt(4, merch.getQuantityInStock());
            stmt.setInt(5, merch.getMerchId());
            stmt.executeUpdate();

            LoggerUtil.logInfo("Updated merch ID: " + merch.getMerchId());
        } catch (SQLException e) {
            LoggerUtil.logError("Error updating merch: " + e.getMessage());
        }
    }

    public void deleteMerch(int merchId) {
        String sql = "DELETE FROM gymmerch WHERE merchid=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, merchId);
            stmt.executeUpdate();
            LoggerUtil.logInfo("Deleted merch ID: " + merchId);
        } catch (SQLException e) {
            LoggerUtil.logError("Error deleting merch: " + e.getMessage());
        }
    }

    public int generateNewId() {
        String sql = "SELECT MAX(merchid) AS maxid FROM gymmerch";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) return rs.getInt("maxid") + 1;
        } catch (SQLException e) {
            LoggerUtil.logError("Error generating merch ID: " + e.getMessage());
        }
        return 1;
    }
}

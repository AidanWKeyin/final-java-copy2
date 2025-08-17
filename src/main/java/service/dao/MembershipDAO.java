package service.dao;

import service.models.Membership;
import service.utils.DBConnection;
import service.utils.LoggerUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MembershipDAO {

    public void addMembership(Membership membership) {
        String sql = "INSERT INTO memberships(membershipid, membershiptype, description, membershipcost, memberid) VALUES (?,?,?,?,?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, membership.getMembershipId());
            stmt.setString(2, membership.getMembershipType());
            stmt.setString(3, membership.getDescription());
            stmt.setDouble(4, membership.getCost());
            stmt.setInt(5, membership.getMemberId());

            stmt.executeUpdate();
            LoggerUtil.logInfo("Membership added: " + membership.getMembershipType());

        } catch (SQLException e) {
            LoggerUtil.logError("Error adding membership: " + e.getMessage());
        }
    }

    public List<Membership> getAllMemberships() {
        List<Membership> list = new ArrayList<>();
        String sql = "SELECT * FROM memberships";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Membership(
                        rs.getInt("membershipid"),
                        rs.getString("membershiptype"),
                        rs.getString("description"),
                        rs.getDouble("membershipcost"),
                        rs.getInt("memberid")
                ));
            }
        } catch (SQLException e) {
            LoggerUtil.logError("Error fetching memberships: " + e.getMessage());
        }
        return list;
    }

    public int generateNewId() {
        String sql = "SELECT MAX(membershipid) AS maxid FROM memberships";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) return rs.getInt("maxid") + 1;
        } catch (SQLException e) {
            LoggerUtil.logError("Error generating membership ID: " + e.getMessage());
        }
        return 1;
    }
}

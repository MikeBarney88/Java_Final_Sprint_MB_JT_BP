package org.keyin.memberships;

import org.keyin.database.DatabaseConnection;
import org.keyin.user.User;

import java.sql.*;
import java.util.ArrayList;

/**
 * DAO class for performing database operations related to Membership.
 */
public class MembershipDAO {
    private Connection conn;

    /**
     * Initializes the DAO with a database connection.
     *
     * @param conn the database connection
     */
    public MembershipDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * Adds a new membership to the database.
     *
     * @param membership the Membership object to add
     * @throws SQLException if any database error occurs
     */
    public void addMembership(Membership membership) throws SQLException {
        String sql = "INSERT INTO memberships (membership_type, membership_description, membership_cost, user_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, membership.getMembershipType());
            stmt.setString(2, membership.getMembershipDescription());
            stmt.setDouble(3, membership.getMembershipCost());
            stmt.setInt(4, membership.getMemberId());
            stmt.executeUpdate();
        }
    }

    /**
     * Calculates and returns the total revenue from all memberships.
     *
     * @return the total revenue as a double
     * @throws SQLException if any database error occurs
     */
    public double getTotalRevenue() throws SQLException {
        String sql = "SELECT SUM(membership_cost) FROM memberships";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) return rs.getDouble(1);
        }
        return 0.0;
    }

    /**
     * Calculates and returns the total revenue from memberships bought by a specific User.
     *
     * @return the total revenue as a double
     * @throws SQLException if any database error occurs
     */
    public double getTotalUserExpenses(int user_id) throws SQLException {
        String sql = "SELECT SUM(membership_cost) FROM memberships WHERE user_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, user_id);
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) return rs.getDouble(1);
        }
        return 0.0;
    }

    public ArrayList<Membership> selectAllMemberships() throws SQLException {
        String sql = "SELECT * FROM memberships";
        ArrayList<Membership> allMembershipResults = new ArrayList<>();

        try (Statement pstmt = conn.createStatement()) {
            try (ResultSet rs = pstmt.executeQuery(sql)) {
                while (rs.next()) {
                    allMembershipResults.add(new Membership(
                            rs.getInt("membership_id"),
                            rs.getString("membership_type"),
                            rs.getString("membership_description"),
                            rs.getInt("membership_cost"),
                            rs.getInt("user_id")
                    ));
                }
            }
        }
        return allMembershipResults;
    }
}
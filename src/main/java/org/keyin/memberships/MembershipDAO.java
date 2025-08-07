package org.keyin.memberships;

import java.sql.*;

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
        String sql = "INSERT INTO memberships (membershipType, membershipDescription, membershipCost, memberID) VALUES (?, ?, ?, ?)";
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
        String sql = "SELECT SUM(membershipCost) FROM memberships";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) return rs.getDouble(1);
        }
        return 0.0;
    }
}


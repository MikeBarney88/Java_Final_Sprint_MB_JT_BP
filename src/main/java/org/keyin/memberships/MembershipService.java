package org.keyin.memberships;

import org.keyin.customlogger.CustomLogger;
import org.keyin.user.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Service class containing business logic for Memberships.
 */
public class MembershipService {
    private MembershipDAO dao;

    /**
     * Constructor to initialize the service with a DAO instance.
     *
     * @param dao the MembershipDAO instance
     */
    public MembershipService(MembershipDAO dao) {
        this.dao = dao;
    }

    /**
     * Processes the purchase of a membership.
     *
     * @param membership the Membership to be purchased
     */
    public void purchaseMembership(Membership membership) throws IOException {
        try {
            dao.addMembership(membership);
            System.out.println("Membership purchased successfully.");
        } catch (Exception e) {
            System.out.println("Failed to purchase membership: " + e.getMessage());
            CustomLogger.logError(e.getMessage());
        }
    }

    /**
     * Displays the total revenue generated from memberships.
     */
    public void displayTotalRevenue() throws IOException {
        try {
            double revenue = dao.getTotalRevenue();
            System.out.println("Total revenue from memberships: $" + revenue);
        } catch (Exception e) {
            System.out.println("Error retrieving revenue: " + e.getMessage());
            CustomLogger.logError(e.getMessage());
        }
    }

    /**
     * Displays the membership expenses of one user.
     *
     * @param id the user_id to get the total expenses of
     */
    public void displayUserTotalExpenses(int id) throws IOException {
        try {
            double expenses = dao.getTotalUserExpenses(id);
            System.out.println("Total expenses of your memberships: $" + expenses);
        } catch (SQLException e) {
            System.out.println("Error retrieving expenses: " + e.getMessage());
            CustomLogger.logError(e.getMessage());
        }
    }

    /**
     * ADMIN METHOD - Displays an ArrayList of all gym memberships.
     */
    public ArrayList<Membership> getAllMemberships(User user) throws SQLException {
        if (!user.getRole().equals("admin")) {
            System.out.println("   Access Denied: You do not have the required role to use this method.");
            return new ArrayList<>();
        } else {
            return dao.selectAllMemberships();
        }
    }
}


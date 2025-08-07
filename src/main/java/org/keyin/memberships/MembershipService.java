package org.keyin.memberships;

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
    public void purchaseMembership(Membership membership) {
        try {
            dao.addMembership(membership);
            System.out.println("Membership purchased successfully.");
        } catch (Exception e) {
            System.out.println("Failed to purchase membership: " + e.getMessage());
        }
    }

    /**
     * Displays the total revenue generated from memberships.
     */
    public void displayTotalRevenue() {
        try {
            double revenue = dao.getTotalRevenue();
            System.out.println("Total revenue from memberships: $" + revenue);
        } catch (Exception e) {
            System.out.println("Error retrieving revenue: " + e.getMessage());
        }
    }
}


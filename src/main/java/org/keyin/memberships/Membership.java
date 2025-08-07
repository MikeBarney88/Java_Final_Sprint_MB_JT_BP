package org.keyin.memberships;

/**
 * Represents a gym membership.
 * Each membership is linked to a member and contains details about type, description, and cost.
 */
public class Membership {
    private int membershipId;
    private String membershipType;
    private String membershipDescription;
    private double membershipCost;
    private int memberId;

    /**
     * Constructs a full Membership object with all attributes.
     *
     * @param membershipId         the ID of the membership
     * @param membershipType       the type of membership (e.g., Monthly, Yearly)
     * @param membershipDescription description of the membership
     * @param membershipCost       cost of the membership
     * @param memberId             ID of the member who owns this membership
     */
    public Membership(int membershipId, String membershipType, String membershipDescription, double membershipCost, int memberId) {
        this.membershipId = membershipId;
        this.membershipType = membershipType;
        this.membershipDescription = membershipDescription;
        this.membershipCost = membershipCost;
        this.memberId = memberId;
    }

    /** Default constructor */
    public Membership() {}

    /** Getters and setters */

    public int getMembershipId() { return membershipId; }

    public void setMembershipId(int membershipId) { this.membershipId = membershipId; }

    public String getMembershipType() { return membershipType; }

    public void setMembershipType(String membershipType) { this.membershipType = membershipType; }

    public String getMembershipDescription() { return membershipDescription; }

    public void setMembershipDescription(String membershipDescription) { this.membershipDescription = membershipDescription; }

    public double getMembershipCost() { return membershipCost; }

    public void setMembershipCost(double membershipCost) { this.membershipCost = membershipCost; }

    public int getMemberId() { return memberId; }

    public void setMemberId(int memberId) { this.memberId = memberId; }
}


package service.models;

public class Membership {
    private int membershipId;
    private String membershipType;
    private String description;
    private double cost;
    private int memberId;

    public Membership(int membershipId, String membershipType, String description, double cost, int memberId) {
        this.membershipId = membershipId;
        this.membershipType = membershipType;
        this.description = description;
        this.cost = cost;
        this.memberId = memberId;
    }

    public int getMembershipId() { return membershipId; }
    public String getMembershipType() { return membershipType; }
    public String getDescription() { return description; }
    public double getCost() { return cost; }
    public int getMemberId() { return memberId; }
}

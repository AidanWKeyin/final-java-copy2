package service.services;

import service.dao.MembershipDAO;
import service.models.Membership;
import service.utils.LoggerUtil;

import java.util.List;
import java.util.Scanner;

public class MembershipService {

    private MembershipDAO membershipDAO = new MembershipDAO();

    public void viewAllMemberships() {
        List<Membership> memberships = membershipDAO.getAllMemberships();
        if (memberships.isEmpty()) {
            System.out.println("No memberships found.");
            return;
        }

        double totalRevenue = 0;
        System.out.println("\n--- Memberships ---");
        for (Membership m : memberships) {
            System.out.println("ID: " + m.getMembershipId() + ", Type: " + m.getMembershipType() +
                    ", Description: " + m.getDescription() + ", Cost: " + m.getCost() +
                    ", Member ID: " + m.getMemberId());
            totalRevenue += m.getCost();
        }
        System.out.println("Total revenue: $" + totalRevenue);
        LoggerUtil.logInfo("Viewed all memberships and total revenue.");
    }

    public void purchaseMembership(Scanner scanner, int memberId) {
        int id = membershipDAO.generateNewId();
        System.out.print("Enter membership type: ");
        String type = scanner.nextLine();
        System.out.print("Enter description: ");
        String desc = scanner.nextLine();
        System.out.print("Enter cost: ");
        double cost = readDouble(scanner);

        Membership membership = new Membership(id, type, desc, cost, memberId);
        membershipDAO.addMembership(membership);
        System.out.println("Membership purchased successfully!");
        LoggerUtil.logInfo("Member ID " + memberId + " purchased membership: " + type);
    }

    private double readDouble(Scanner scanner) {
        while (!scanner.hasNextDouble()) {
            System.out.print("Invalid input. Enter a number: ");
            scanner.nextLine();
        }
        double val = scanner.nextDouble();
        scanner.nextLine();
        return val;
    }
}

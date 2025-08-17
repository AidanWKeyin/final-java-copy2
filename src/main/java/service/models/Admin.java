package service.models;

import java.util.Scanner;

public class Admin extends User {

    public Admin(int userId, String username, String password, String email, String phoneNumber, String address) {
        super(userId, username, password, email, phoneNumber, address, "Admin");
    }

    public void showAdminMenu() {
        System.out.println("\n--- Admin Menu ---");
        System.out.println("1. View all users");
        System.out.println("2. Delete user");
        System.out.println("3. View memberships and revenue");
        System.out.println("4. Manage merchandise");
        System.out.println("5. Logout");
        System.out.print("Choose an option: ");
    }
}

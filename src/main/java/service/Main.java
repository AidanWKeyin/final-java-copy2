package service;

import service.models.*;
import service.services.*;
import service.dao.UserDAO;
import service.utils.LoggerUtil;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static UserService userService = new UserService();
    private static GymMerchService merchService = new GymMerchService();
    private static MembershipService membershipService = new MembershipService();
    private static WorkoutClassService classService = new WorkoutClassService();
    private static UserDAO userDAO = new UserDAO();

    public static void main(String[] args) {
        LoggerUtil.logInfo("Gym Management System Started.");
        System.out.println("Welcome to Gym Management System!");

        while (true) {
            System.out.println("\n1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = readInt();

            switch (choice) {
                case 1 -> userService.register(scanner);
                case 2 -> loginFlow();
                case 3 -> {
                    System.out.println("Exiting...");
                    LoggerUtil.logInfo("Gym Management System Exited.");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // ---------------------- LOGIN ----------------------
    private static void loginFlow() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = userDAO.getUserByUsername(username);
        if (user == null || !UserService.verifyPassword(password, user.getPassword())) {
            System.out.println("Invalid credentials!");
            LoggerUtil.logInfo("Failed login attempt: " + username);
            return;
        }

        LoggerUtil.logInfo("User logged in: " + username);
        System.out.println("Login successful! Welcome, " + user.getRole());

        switch (user.getRole()) {
            case "Admin" -> adminMenu(new Admin(user.getUserId(), user.getUsername(), user.getPassword(),
                    user.getEmail(), user.getPhoneNumber(), user.getAddress()));
            case "Trainer" -> trainerMenu(new Trainer(user.getUserId(), user.getUsername(), user.getPassword(),
                    user.getEmail(), user.getPhoneNumber(), user.getAddress()));
            case "Member" -> memberMenu(new Member(user.getUserId(), user.getUsername(), user.getPassword(),
                    user.getEmail(), user.getPhoneNumber(), user.getAddress()));
            default -> System.out.println("Unknown role.");
        }
    }

    // ---------------------- ADMIN MENU ----------------------
    private static void adminMenu(Admin admin) {
        while (true) {
            admin.showAdminMenu();
            int choice = readInt();

            switch (choice) {
                case 1 -> viewAllUsers();
                case 2 -> deleteUser();
                case 3 -> membershipService.viewAllMemberships();
                case 4 -> manageMerchAdmin();
                case 5 -> { System.out.println("Logging out..."); return; }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static void viewAllUsers() {
        List<User> users = userDAO.getAllUsers();
        System.out.println("\n--- All Users ---");
        for (User u : users) {
            System.out.println("ID: " + u.getUserId() + ", Username: " + u.getUsername() +
                    ", Email: " + u.getEmail() + ", Phone: " + u.getPhoneNumber() +
                    ", Address: " + u.getAddress() + ", Role: " + u.getRole());
        }
        LoggerUtil.logInfo("Admin viewed all users.");
    }

    private static void deleteUser() {
        System.out.print("Enter User ID to delete: ");
        int id = readInt();
        userDAO.deleteUser(id);
        System.out.println("User deleted successfully!");
        LoggerUtil.logInfo("Admin deleted user with ID: " + id);
    }

    private static void manageMerchAdmin() {
        while (true) {
            System.out.println("\n--- Gym Merchandise Management ---");
            System.out.println("1. View all merch");
            System.out.println("2. Add merch");
            System.out.println("3. Update merch");
            System.out.println("4. Delete merch");
            System.out.println("5. Back");
            System.out.print("Choose an option: ");
            int choice = readInt();

            switch (choice) {
                case 1 -> merchService.viewAllMerch();
                case 2 -> merchService.addMerch(scanner);
                case 3 -> merchService.updateMerch(scanner);
                case 4 -> merchService.deleteMerch(scanner);
                case 5 -> { return; }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    // ---------------------- TRAINER MENU ----------------------
    private static void trainerMenu(Trainer trainer) {
        while (true) {
            trainer.showTrainerMenu();
            int choice = readInt();

            switch (choice) {
                case 1 -> manageWorkoutClasses(trainer.getUserId());
                case 2 -> membershipService.purchaseMembership(scanner, trainer.getUserId());
                case 3 -> merchService.viewAllMerch();
                case 4 -> { System.out.println("Logging out..."); return; }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static void manageWorkoutClasses(int trainerId) {
        while (true) {
            System.out.println("\n--- Workout Class Management ---");
            System.out.println("1. View all classes");
            System.out.println("2. Add class");
            System.out.println("3. Update class");
            System.out.println("4. Delete class");
            System.out.println("5. Back");
            System.out.print("Choose an option: ");
            int choice = readInt();

            switch (choice) {
                case 1 -> classService.viewAllClasses();
                case 2 -> classService.addClass(scanner, trainerId);
                case 3 -> classService.updateClass(scanner);
                case 4 -> classService.deleteClass(scanner);
                case 5 -> { return; }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    // ---------------------- MEMBER MENU ----------------------
    private static void memberMenu(Member member) {
        while (true) {
            member.showMemberMenu();
            int choice = readInt();

            switch (choice) {
                case 1 -> classService.viewAllClasses();
                case 2 -> membershipService.purchaseMembership(scanner, member.getUserId());
                case 3 -> merchService.viewAllMerch();
                case 4 -> { System.out.println("Logging out..."); return; }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    // ---------------------- UTILITY ----------------------
    private static int readInt() {
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Enter a number: ");
            scanner.nextLine();
        }
        int num = scanner.nextInt();
        scanner.nextLine(); // consume newline
        return num;
    }
}

package service.models;

public class Trainer extends User {

    public Trainer(int userId, String username, String password, String email, String phoneNumber, String address) {
        super(userId, username, password, email, phoneNumber, address, "Trainer");
    }

    public void showTrainerMenu() {
        System.out.println("\n--- Trainer Menu ---");
        System.out.println("1. Manage workout classes");
        System.out.println("2. Purchase membership");
        System.out.println("3. View gym merchandise");
        System.out.println("4. Logout");
        System.out.print("Choose an option: ");
    }
}


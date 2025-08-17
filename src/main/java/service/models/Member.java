package service.models;

public class Member extends User {

    public Member(int userId, String username, String password, String email, String phoneNumber, String address) {
        super(userId, username, password, email, phoneNumber, address, "Member");
    }

    public void showMemberMenu() {
        System.out.println("\n--- Member Menu ---");
        System.out.println("1. View workout classes");
        System.out.println("2. Purchase membership");
        System.out.println("3. View gym merchandise");
        System.out.println("4. Logout");
        System.out.print("Choose an option: ");
    }
}


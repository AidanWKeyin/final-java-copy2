package service.services;

import org.mindrot.jbcrypt.BCrypt;
import service.dao.UserDAO;
import service.models.User;
import service.utils.LoggerUtil;

import java.util.Scanner;

public class UserService {

    private UserDAO userDAO = new UserDAO();

    public void register(Scanner scanner) {
        int id = userDAO.generateNewId();
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = hashPassword(scanner.nextLine());
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine();
        System.out.print("Enter address: ");
        String address = scanner.nextLine();
        System.out.print("Enter role (Admin/Trainer/Member): ");
        String role = scanner.nextLine();

        User user = new User(id, username, password, email, phone, address, role);
        userDAO.addUser(user);
        System.out.println("User registered successfully!");
        LoggerUtil.logInfo("New user registered: " + username);
    }

    public static boolean verifyPassword(String plain, String hashed) {
        return BCrypt.checkpw(plain, hashed);
    }

    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}



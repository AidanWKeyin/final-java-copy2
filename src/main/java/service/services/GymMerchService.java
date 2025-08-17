package service.services;

import service.dao.GymMerchDAO;
import service.models.GymMerch;
import service.utils.LoggerUtil;

import java.util.List;
import java.util.Scanner;

public class GymMerchService {

    private GymMerchDAO merchDAO = new GymMerchDAO();

    public void viewAllMerch() {
        List<GymMerch> list = merchDAO.getAllMerch();
        if (list.isEmpty()) {
            System.out.println("No merch found.");
            return;
        }
        System.out.println("\n--- Gym Merchandise ---");
        for (GymMerch m : list) {
            System.out.println("ID: " + m.getMerchId() + ", Name: " + m.getName() +
                    ", Type: " + m.getType() + ", Price: " + m.getPrice() +
                    ", Quantity: " + m.getQuantityInStock());
        }
        LoggerUtil.logInfo("Viewed all gym merchandise.");
    }

    public void addMerch(Scanner scanner) {
        int id = merchDAO.generateNewId();
        System.out.print("Enter merch name: ");
        String name = scanner.nextLine();
        System.out.print("Enter type: ");
        String type = scanner.nextLine();
        System.out.print("Enter price: ");
        double price = readDouble(scanner);
        System.out.print("Enter quantity: ");
        int qty = readInt(scanner);

        GymMerch merch = new GymMerch(id, name, type, price, qty);
        merchDAO.addMerch(merch);
        System.out.println("Merch added successfully!");
        LoggerUtil.logInfo("Added merch: " + name);
    }

    public void updateMerch(Scanner scanner) {
        System.out.print("Enter merch ID to update: ");
        int id = readInt(scanner);

        List<GymMerch> list = merchDAO.getAllMerch();
        GymMerch merch = list.stream().filter(m -> m.getMerchId() == id).findFirst().orElse(null);
        if (merch == null) {
            System.out.println("Merch not found.");
            return;
        }

        System.out.print("Enter new name (current: " + merch.getName() + "): ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) merch = new GymMerch(merch.getMerchId(), name, merch.getType(), merch.getPrice(), merch.getQuantityInStock());

        System.out.print("Enter new type (current: " + merch.getType() + "): ");
        String type = scanner.nextLine();
        if (!type.isEmpty()) merch = new GymMerch(merch.getMerchId(), merch.getName(), type, merch.getPrice(), merch.getQuantityInStock());

        System.out.print("Enter new price (current: " + merch.getPrice() + "): ");
        String priceStr = scanner.nextLine();
        if (!priceStr.isEmpty()) merch = new GymMerch(merch.getMerchId(), merch.getName(), merch.getType(), Double.parseDouble(priceStr), merch.getQuantityInStock());

        System.out.print("Enter new quantity (current: " + merch.getQuantityInStock() + "): ");
        String qtyStr = scanner.nextLine();
        if (!qtyStr.isEmpty()) merch = new GymMerch(merch.getMerchId(), merch.getName(), merch.getType(), merch.getPrice(), Integer.parseInt(qtyStr));

        merchDAO.updateMerch(merch);
        System.out.println("Merch updated successfully!");
        LoggerUtil.logInfo("Updated merch ID: " + id);
    }

    public void deleteMerch(Scanner scanner) {
        System.out.print("Enter merch ID to delete: ");
        int id = readInt(scanner);
        merchDAO.deleteMerch(id);
        System.out.println("Merch deleted successfully!");
        LoggerUtil.logInfo("Deleted merch ID: " + id);
    }

    private int readInt(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Enter a number: ");
            scanner.nextLine();
        }
        int val = scanner.nextInt();
        scanner.nextLine();
        return val;
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

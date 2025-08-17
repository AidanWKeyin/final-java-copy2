package service.models;

public class GymMerch {
    private int merchId;
    private String name;
    private String type;
    private double price;
    private int quantityInStock;

    public GymMerch(int merchId, String name, String type, double price, int quantityInStock) {
        this.merchId = merchId;
        this.name = name;
        this.type = type;
        this.price = price;
        this.quantityInStock = quantityInStock;
    }

    public int getMerchId() { return merchId; }
    public String getName() { return name; }
    public String getType() { return type; }
    public double getPrice() { return price; }
    public int getQuantityInStock() { return quantityInStock; }
}

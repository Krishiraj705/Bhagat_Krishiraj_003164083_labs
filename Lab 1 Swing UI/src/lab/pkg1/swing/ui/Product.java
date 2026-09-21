package lab.pkg1.swing.ui;

public class Product {
    private String name;
    private String description;
    private int availNum;
    private double price;
    private Address manufactureAddress;
    private Address shippingAddress;

    public Product(String name, String description, int availNum, double price,
                   Address manufactureAddress, Address shippingAddress) {
        this.name = name;
        this.description = description;
        this.availNum = availNum;
        this.price = price;
        this.manufactureAddress = manufactureAddress;
        this.shippingAddress = shippingAddress;
    }

    @Override
    public String toString() {
        return "Product Name: " + name
                + "\nDescription: " + description
                + "\nAvailable Number: " + availNum
                + "\nPrice: $" + price
                + "\nManufacture Address: " + manufactureAddress
                + "\nShipping Address: " + shippingAddress;
    }
}
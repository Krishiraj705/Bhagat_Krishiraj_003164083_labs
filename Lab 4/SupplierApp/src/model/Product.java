package model;

import java.util.ArrayList;

public class Product {
    private static int count = 1;

    private int id;
    private String name;
    private double price;
    private String description;
    private ArrayList<Feature> features;

    public Product() {
        id = count++;
        features = new ArrayList<>();
    }

    public Feature addNewFeature() {
        Feature f = new Feature();
        features.add(f);
        return f;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public ArrayList<Feature> getFeatures() { return features; }

    @Override
    public String toString() { return name; }
}
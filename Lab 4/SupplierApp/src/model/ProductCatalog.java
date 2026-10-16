package model;

import java.util.ArrayList;

public class ProductCatalog {
    private ArrayList<Product> products = new ArrayList<>();

    public ArrayList<Product> getProducts() { return products; }

    public Product addProduct() {
        Product p = new Product();
        products.add(p);
        return p;
    }

    public void removeProduct(Product p) { products.remove(p); }

    public Product searchProduct(int id) {
        for (Product p : products) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }
}
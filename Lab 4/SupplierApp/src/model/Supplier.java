package model;

public class Supplier {
    private String supplierName;
    private ProductCatalog productCatalog = new ProductCatalog();

    public String getSupplierName() { return supplierName; }
    public void setSupplierName(String supplierName) { this.supplierName = supplierName; }
    public ProductCatalog getProductCatalog() { return productCatalog; }

    @Override
    public String toString() { return supplierName; }
}
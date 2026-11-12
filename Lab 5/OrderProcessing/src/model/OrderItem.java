package model;

/**
 * One line in a customer's order: a product, the agreed sales price and the quantity.
 *
 * @author Krishiraj Bhagat
 */
public class OrderItem {

    private Product product;
    private double salesPrice;
    private int quantity;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(double salesPrice) {
        this.salesPrice = salesPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /** Total amount for this line = sales price x quantity. */
    public double getTotal() {
        return salesPrice * quantity;
    }

    @Override
    public String toString() {
        return product == null ? "" : product.getProdName();
    }
}

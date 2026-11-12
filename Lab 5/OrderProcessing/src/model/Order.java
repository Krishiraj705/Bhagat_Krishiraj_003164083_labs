package model;

import java.util.ArrayList;
import java.util.List;

/**
 * A customer's order (the shopping cart). It holds a list of order items.
 *
 * @author Krishiraj Bhagat
 */
public class Order {

    private List<OrderItem> orderItemList;
    private int orderNumber;

    private static int count = 0;

    public Order() {
        orderItemList = new ArrayList<OrderItem>();
        count++;
        orderNumber = count;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    /** Adds a new order item for the given product, price and quantity. */
    public OrderItem addNewOrderItem(Product product, double salesPrice, int quantity) {
        OrderItem item = new OrderItem();
        item.setProduct(product);
        item.setSalesPrice(salesPrice);
        item.setQuantity(quantity);
        orderItemList.add(item);
        return item;
    }

    /** Returns the order item for this product, or null if it is not in the order yet. */
    public OrderItem findOrderItem(Product product) {
        for (OrderItem item : orderItemList) {
            if (item.getProduct() == product) {
                return item;
            }
        }
        return null;
    }

    public void removeOrderItem(OrderItem item) {
        orderItemList.remove(item);
    }

    /** Sum of all order item totals. */
    public double getOrderTotal() {
        double total = 0;
        for (OrderItem item : orderItemList) {
            total += item.getTotal();
        }
        return total;
    }
}

package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Keeps every order that customers have checked out.
 *
 * @author Krishiraj Bhagat
 */
public class MasterOrderList {

    private List<Order> orderList;

    public MasterOrderList() {
        orderList = new ArrayList<Order>();
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    /** Saves a checked-out order. */
    public void addOrder(Order order) {
        orderList.add(order);
    }
}

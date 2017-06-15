package project.dao;

import project.entity.Order;

public interface OrderDao extends Dao<Order> {
    int insertOrder(Order order, Integer userId);
    boolean cancelTheOrders(int id);

}

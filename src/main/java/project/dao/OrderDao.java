package project.dao;

import project.entity.Order;

import java.sql.SQLException;
import java.util.List;

public interface OrderDao extends Dao<Order> {
    int insertOrder(Order order, Integer userId);
    boolean cancelTheOrders(int id) throws SQLException;
    boolean acceptOrder(int idOrder);
    boolean rejectOrder(int idOrder);
    List<Order> returnTheWaitingOrders() throws SQLException;
}

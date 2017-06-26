package project.dao;

import project.entity.Order;

import java.sql.SQLException;
import java.util.List;

public interface OrderDao extends Dao<Order> {
    int insertOrder(Order order, Integer userId);
    boolean cancelTheOrders(int id) throws SQLException;
    boolean acceptOrder (int idOrder) throws SQLException;
    boolean rejectOrder(int idOrder) throws SQLException;
    List<Order> returnTheWaitingOrders() throws SQLException;
}

package project.dao;

import project.entity.Order;

import java.sql.SQLException;
import java.util.List;

public interface OrderDao extends Dao<Order> {
    int insertOrder(String street,String house,String numberOfApartment,String time, Integer userId) throws SQLException;
    boolean cancelTheOrders(int id) throws SQLException;
    boolean acceptOrder (int idOrder) throws SQLException;
    boolean rejectOrder(int idOrder) throws SQLException;
    List<Order> returnTheWaitingOrders() throws SQLException;
}

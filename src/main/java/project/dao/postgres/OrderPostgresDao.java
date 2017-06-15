package project.dao.postgres;

import project.dao.OrderDao;
import project.dao.managerDao.ManagerDao;
import project.entity.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static project.constants.Constants.*;

public class OrderPostgresDao extends AbstractPostgresDao<Order> implements OrderDao {

    private Connection connection;
    private ManagerDao managerDao;

    public OrderPostgresDao(Connection connection, ManagerDao managerDao) {
        super(connection);
        this.connection = connection;
        this.managerDao = managerDao;
    }

    public int insertOrder(Order order, Integer userId) {
        return this.insert("orders", order.getStreet() + order.getNumberOfHouse()
                + order.getNumberOfApartment() + userId + order.getDate());

    }

    @Override
    public boolean cancelTheOrders(int id) {
        LinkedHashMap<String, Object> conditions = new LinkedHashMap<>();
        LinkedHashMap<String, Object> params = new LinkedHashMap<>();
        conditions.put(ID, id);
        params.put(FK_STATUS, ORDER_STATUS_REJECT);
        return this.updateEntity(ORDERS, params, conditions);
    }

    public List<Order> returnTheWaitingOrders() {
        LinkedHashMap<String, Object> conditions = new LinkedHashMap<>();
        conditions.put(FK_STATUS, ORDER_STATUS_WAITING);
        ResultSet r = this.get(ORDERS, conditions);
        List<Order> orders = new ArrayList<>();
        try {
            while (r.next()) {
                Order order = new Order();
                order.setId(r.getInt(ID));
                order.setStreet(r.getString(STREET));
                order.setStreet(r.getString(NUMBER_OF_HOUSE));
                order.setStreet(r.getString(NUMBER_OF_APARTMENT));
                order.setTime(r.getString(TIME));
                order.setFkUser(r.getInt(FK_USERS));
                orders.add(order);
            }
            return orders;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

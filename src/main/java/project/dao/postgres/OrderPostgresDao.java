package project.dao.postgres;

import project.dao.OrderDao;
import project.dao.managerDao.ManagerDao;
import project.entity.Order;
import project.entity.OrderStatus;

import java.sql.*;
import java.util.*;

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

        return this.insert(ORDERS,
                order.getStreet(),
                order.getNumberOfHouse(),
                order.getNumberOfApartment(),
                userId,
                ORDER_STATUS_WAITING_INT,
                order.getTime());
    }

    @Override
    public boolean cancelTheOrders(int id) throws SQLException {
        String sql = "UPDATE orders SET fk_status = '" + ORDER_STATUS_REJECT + "' WHERE fk_users = '" + id + "'";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        int result = preparedStatement.executeUpdate();
        if (result == 1) {
            return true;
        } else return false;
    }

    @Override
    public boolean acceptOrder (int idOrder) throws SQLException {
        LinkedHashMap<String, Object> conditions = new LinkedHashMap<>();
        LinkedHashMap<String, Object> params = new LinkedHashMap<>();
        params.put(FK_STATUS, ORDER_STATUS_ACCEPT_INT);
        conditions.put(ID, idOrder);
        return this.updateEntity(ORDERS, params, conditions);
    }

    @Override
    public boolean rejectOrder(int idOrder) throws SQLException {
        LinkedHashMap<String, Object> conditions = new LinkedHashMap<>();
        LinkedHashMap<String, Object> params = new LinkedHashMap<>();
        params.put(FK_STATUS, ORDER_STATUS_REJECT_INT);
        conditions.put(ID, idOrder);
        return this.updateEntity(ORDERS, params, conditions);
    }

    public List<Order> returnTheWaitingOrders() throws SQLException {
        List<Order> orders = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement
        ("select * from orders join order_status on orders.fk_status=order_status.id where order_status.id="+ORDER_STATUS_WAITING);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Order order = new Order();
            OrderStatus orderStatus = new OrderStatus();
            orderStatus.setStatus(resultSet.getString("name_of_status"));
            order.setId(resultSet.getInt(ID));
            order.setStreet(resultSet.getString(STREET));
            order.setNumberOfHouse(resultSet.getString(NUMBER_OF_HOUSE));
            order.setNumberOfApartment(resultSet.getString(NUMBER_OF_APARTMENT));
            order.setTime(resultSet.getString(TIME));
            order.setFkUser(resultSet.getInt(FK_USERS));
            order.setStatusOfOrder(orderStatus);
            orders.add(order);
        }
        return orders;
    }
}


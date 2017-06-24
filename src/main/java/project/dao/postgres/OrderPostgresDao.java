package project.dao.postgres;

import project.dao.OrderDao;
import project.dao.managerDao.ManagerDao;
import project.entity.Order;

import java.sql.*;
import java.util.*;

import static project.constants.Constants.*;

public class OrderPostgresDao extends AbstractPostgresDao<Order> implements OrderDao {
    private static final String GET_ORDERS = "select * from orders where ";

    private Connection connection;
    private ManagerDao managerDao;

    public OrderPostgresDao(Connection connection, ManagerDao managerDao) {
        super(connection);
        this.connection = connection;
        this.managerDao = managerDao;
    }

    public int insertOrder(Order order, Integer userId) {
        int defaultStatus = 12;
        return this.insert("orders",
                order.getStreet(),
                order.getNumberOfHouse(),
                order.getNumberOfApartment(),
                userId,
                defaultStatus,
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
    public boolean acceptOrder(int idOrder) {
        LinkedHashMap<String, Object> conditions = new LinkedHashMap<>();
        LinkedHashMap<String, Object> params = new LinkedHashMap<>();
        params.put(FK_STATUS, ORDER_STATUS_ACCEPT);
        conditions.put(ID, idOrder);
        return this.updateEntity(ORDERS, params, conditions);
    }

    @Override
    public boolean rejectOrder(int idOrder) {
        LinkedHashMap<String, Object> conditions = new LinkedHashMap<>();
        LinkedHashMap<String, Object> params = new LinkedHashMap<>();
        params.put(FK_STATUS, ORDER_STATUS_REJECT);
        conditions.put(ID, idOrder);
        return this.updateEntity(ORDERS, params, conditions);
    }

    public List<Order> returnTheWaitingOrders() throws SQLException {
        List<Order> orders = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDERS + FK_STATUS + "=" + ORDER_STATUS_WAITING);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Order order = new Order();
            order.setId(resultSet.getInt(ID));
            order.setStreet(resultSet.getString(STREET));
            order.setNumberOfHouse(resultSet.getString(NUMBER_OF_HOUSE));
            order.setNumberOfApartment(resultSet.getString(NUMBER_OF_APARTMENT));
            order.setTime(resultSet.getString(TIME));
            order.setFkUser(resultSet.getInt(FK_USERS));
            orders.add(order);
        }
        return orders;
    }
}


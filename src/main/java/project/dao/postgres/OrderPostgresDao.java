package project.dao.postgres;

import org.apache.log4j.Logger;
import project.dao.OrderDao;
import project.entity.Order;
import project.entity.OrderStatus;
import project.entity.User;

import java.sql.*;
import java.util.*;

import static project.constants.Constants.*;

public class OrderPostgresDao extends AbstractPostgresDao<Order> implements OrderDao {
    private static final Logger LOGGER = Logger.getLogger(OrderPostgresDao.class.getName());
    private Connection connection;

    public OrderPostgresDao(Connection connection) {
        super(connection);
        this.connection = connection;
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
    public boolean cancelTheOrders(int userId) throws SQLException {
        getParams().put(FK_STATUS, ORDER_STATUS_REJECT_INT);
        getConditions().put(FK_USERS, userId);
        return this.updateEntity(ORDERS, this.params, this.conditions);
    }

    @Override
    public boolean acceptOrder(int idOrder) throws SQLException {
        getParams().put(FK_STATUS, ORDER_STATUS_ACCEPT_INT);
        getConditions().put(ID, idOrder);
        return this.updateEntity(ORDERS, this.params, this.conditions);
    }

    @Override
    public boolean rejectOrder(int idOrder) throws SQLException {
        getParams().put(FK_STATUS, ORDER_STATUS_REJECT_INT);
        getConditions().put(ID, idOrder);
        return this.updateEntity(ORDERS, this.params, this.conditions);
    }

    public List<Order> returnTheWaitingOrders() throws SQLException {
        List<Order> orders = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement
                ("select * from users inner join orders on users.id = orders.fk_users inner join" +
                        " order_status on orders.fk_status = order_status.id where order_status.id=" +
                        ORDER_STATUS_WAITING_INT + " and users.activity_status='active'")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                OrderStatus orderStatus = new OrderStatus();
                User userPhone = new User();
                userPhone.setPhone(resultSet.getString(PHONE));
                orderStatus.setStatus(resultSet.getString(NAME_OF_STATUS));
                order.setId(resultSet.getInt(16));
                order.setStreet(resultSet.getString(STREET));
                order.setNumberOfHouse(resultSet.getString(NUMBER_OF_HOUSE));
                order.setNumberOfApartment(resultSet.getString(NUMBER_OF_APARTMENT));
                order.setTime(resultSet.getString(TIME));
                order.setFkUser(resultSet.getInt(FK_USERS));
                order.setStatusOfOrder(orderStatus);
                order.setPhone(userPhone);
                orders.add(order);
            }
        }

        return orders;
    }

}


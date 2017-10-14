package project.services;

import project.dao.OrderDao;
import project.dao.managerDao.DaoManager;
import project.dao.postgres.FactoryDao;
import project.entity.Order;
import project.entity.User;

import java.sql.SQLException;

public class OrderService {
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(OrderService.class.getName());

    public void createOrder(Order order, User user) throws SQLException {
        DaoManager daoManager = FactoryDao.getInstance().getDaoManager();
        OrderDao orderPostgresDao = daoManager.getOrderPostgresDao();
        daoManager.beginTransaction();
        try {
            orderPostgresDao.insertOrder(order.getStreet(), order.getNumberOfHouse(), order.getNumberOfApartment(), order.getTime(), user.getId());
            daoManager.commit();
        } catch (SQLException e) {
            daoManager.rollback();
            LOGGER.error("can't create order", e);
        } finally {
            FactoryDao.getInstance().putBackConnection(daoManager.returnConnection());
        }
    }

    public void cancelOrder(User user) throws SQLException {
        DaoManager daoManager = FactoryDao.getInstance().getDaoManager();
        OrderDao orderPostgresDao = daoManager.getOrderPostgresDao();
        daoManager.beginTransaction();
        try {
            orderPostgresDao.cancelTheOrders(user.getId());
            daoManager.commit();
        } catch (SQLException e) {
            daoManager.rollback();
            LOGGER.error("can't create order", e);
        } finally {
            FactoryDao.getInstance().putBackConnection(daoManager.returnConnection());
        }
    }

    public void acceptOrder(int id) throws SQLException {
        DaoManager daoManager = FactoryDao.getInstance().getDaoManager();
        OrderDao orderDao = daoManager.getOrderPostgresDao();
        daoManager.beginTransaction();
        try {
            orderDao.acceptOrder(id);
            daoManager.commit();
        } catch (SQLException e) {
            daoManager.rollback();
            LOGGER.error("can't accept order", e);
        }
    }
    public void rejectOrder(int id) throws SQLException{
        DaoManager daoManager = FactoryDao.getInstance().getDaoManager();
        OrderDao orderDao = daoManager.getOrderPostgresDao();
        daoManager.beginTransaction();
        try {
            orderDao.rejectOrder(id);
            daoManager.commit();
        } catch (SQLException e) {
            daoManager.rollback();
            LOGGER.error("can't accept order", e);
        }
    }
}

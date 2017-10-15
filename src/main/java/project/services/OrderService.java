package project.services;

import project.dao.OrderDao;
import project.dao.managerDao.DaoManager;
import project.dao.postgres.FactoryDao;
import project.entity.Order;
import project.entity.User;
import project.util.Validator;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

import static project.constants.Constants.*;
import static project.constants.Constants.NUMBER_OF_APARTMENT;
import static project.constants.Constants.TIME;

public class OrderService {
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(OrderService.class.getName());

    public String createOrder(HttpServletRequest req) {
        DaoManager daoManager = FactoryDao.getInstance().getDaoManager();
        OrderDao orderPostgresDao = daoManager.getOrderPostgresDao();
        Validator validator = validateFieldsOrder(req);
        if (validator.isValide()) {
            Order order = getOrderFromRequest(req);
            User user = getUserFromRequest(req);
            daoManager.beginTransaction();
            try {
                orderPostgresDao.insertOrder(order.getStreet(), order.getNumberOfHouse(), order.getNumberOfApartment(), order.getTime(), user.getId());
                daoManager.commit();
            } catch (SQLException e) {
                daoManager.rollback();
                LOGGER.error("can't create order", e);
                return ERROR;
            } finally {
                FactoryDao.getInstance().putBackConnection(daoManager.returnConnection());
            }
        } else {
            return ADDRESS_OR_TIME_IS_NOT_CORRECT;
        }
        return ORDER_CREATED_PAGE;
    }

    private Validator validateFieldsOrder(HttpServletRequest req) {
        Validator validator = new Validator();
        validator.checkTime(req.getParameter(TIME));
        validator.checkAddress(req.getParameter(STREET));
        validator.checkAddress(req.getParameter(NUMBER_OF_HOUSE));
        validator.checkAddress(req.getParameter(NUMBER_OF_APARTMENT));
        return validator;
    }

    private Order getOrderFromRequest(HttpServletRequest req) {
        Order order = new Order();
        order.setStreet(req.getParameter(STREET));
        order.setNumberOfHouse(req.getParameter(NUMBER_OF_HOUSE));
        order.setNumberOfApartment(req.getParameter(NUMBER_OF_APARTMENT));
        order.setTime(req.getParameter(TIME));
        return order;
    }


    public String cancelOrder(HttpServletRequest req){
        DaoManager daoManager = FactoryDao.getInstance().getDaoManager();
        OrderDao orderPostgresDao = daoManager.getOrderPostgresDao();
        daoManager.beginTransaction();
        try {
            orderPostgresDao.cancelTheOrders(getUserFromRequest(req).getId());
            daoManager.commit();
        } catch (SQLException e) {
            daoManager.rollback();
            LOGGER.error("can't create order", e);
            return ERROR;
        } finally {
            FactoryDao.getInstance().putBackConnection(daoManager.returnConnection());
        }
        return ORDERS_WERE_CANCELED;
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

    public void rejectOrder(int id) throws SQLException {
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

    private User getUserFromRequest(HttpServletRequest req) {
        User user = new User();
        user.setId(((User) req.getSession().getAttribute(USER)).getId());
        return user;
    }


}

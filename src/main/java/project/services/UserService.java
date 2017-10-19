package project.services;

import project.dao.managerDao.DaoManager;
import project.dao.postgres.FactoryDao;
import project.dao.postgres.OrderPostgresDao;
import project.dao.postgres.UserPostgresDao;
import project.entity.Order;
import project.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

import static project.constants.Constants.*;


public class UserService {
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(UserService.class.getName());

    public String deleteUser(HttpServletRequest req) {
        DaoManager daoManager = FactoryDao.getInstance().getDaoManager();
        UserPostgresDao userPostgresDao = daoManager.getUserPostgresDao();
        try {
            daoManager.beginTransaction();
            userPostgresDao.deleteUser(getUserFromRequest(req).getId());
            daoManager.commit();
        } catch (SQLException e) {
            daoManager.rollback();
            LOGGER.error("can't delete user", e);
            return ERROR;
        } finally {
            FactoryDao.getInstance().putBackConnection(daoManager.returnConnection());
        }
        return USER_IS_DELETED_PAGE;
    }

    private User getUserFromRequest(HttpServletRequest req) {
        User user = new User();
        user.setId(((User) req.getSession().getAttribute(USER)).getId());
        return user;
    }

    public String adminPage(HttpServletRequest req) {
        DaoManager daoManager = FactoryDao.getInstance().getDaoManager();
        try {
            UserPostgresDao userPostgresDao = daoManager.getUserPostgresDao();
            List users = userPostgresDao.returnAllUsers();
            if (users != null) {
                req.setAttribute(USERS, users);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error("can't show users", e);
            return ERROR;
        } finally {
            FactoryDao.getInstance().putBackConnection(daoManager.returnConnection());
        }
        return ADMIN_PAGE;
    }

    public String dispatcherPage(HttpServletRequest req) {
        DaoManager daoManager = FactoryDao.getInstance().getDaoManager();
        OrderPostgresDao orderPostgresDao = daoManager.getOrderPostgresDao();
        List<Order> orderList;
        try {
            orderList = orderPostgresDao.returnTheWaitingOrders();
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error("can't get orders", e);
            return ERROR;
        } finally {
            FactoryDao.getInstance().putBackConnection(daoManager.returnConnection());
        }
        req.setAttribute(ORDERS, orderList);

        return DISPATCHER;
    }
}

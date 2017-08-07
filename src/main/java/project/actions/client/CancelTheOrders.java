package project.actions.client;

import org.apache.log4j.Logger;
import project.actions.Action;
import project.actions.ActionResult;

import project.dao.managerDao.ManagerDao;

import project.dao.postgres.FactoryDao;
import project.dao.postgres.OrderPostgresDao;
import project.entity.User;

import javax.servlet.http.HttpServletRequest;


import java.sql.SQLException;

import static project.constants.Constants.*;

public class CancelTheOrders implements Action {
    private static final Logger LOGGER = Logger.getLogger(CancelTheOrders.class.getName());
    private ActionResult error = new ActionResult(ERROR, true);
    private ActionResult ordersWereCanceled = new ActionResult(ORDERS_WERE_CANCELED);
    private boolean result;

    @Override
    public ActionResult execute(HttpServletRequest req) {
        ManagerDao managerDao = FactoryDao.getInstance().getDaoManager();
        Integer userId = ((User) req.getSession().getAttribute(USER)).getId();
        managerDao.beginTransaction();
        try {
            OrderPostgresDao orderPostgresDao = managerDao.getOrderPostgresDao();
            result = orderPostgresDao.cancelTheOrders(userId);
            if (result) {
                managerDao.commit();
            } else return error;
        } catch (SQLException e) {
            managerDao.rollback();
            LOGGER.error("can't cancel the order", e);
            return error;
        } finally {
            FactoryDao.getInstance().putBackConnection(managerDao.returnConnection());
        }
        return ordersWereCanceled;
    }
}

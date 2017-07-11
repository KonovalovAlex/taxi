package project.actions.client;

import org.apache.log4j.Logger;
import project.actions.Action;
import project.actions.ActionResult;
import project.actions.admin.DeleteUser;
import project.actions.registration.DoRegistration;
import project.dao.managerDao.ManagerDao;
import project.dao.postgres.ExceptionDao;
import project.dao.postgres.FactoryDao;
import project.dao.postgres.OrderPostgresDao;
import project.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.sql.SQLException;

import static project.constants.Constants.*;

public class CancelTheOrders implements Action {
    private static final Logger LOGGER = Logger.getLogger(CancelTheOrders.class.getName());
    ActionResult error = new ActionResult(ERROR, true);
    @Override
    public ActionResult execute(HttpServletRequest req) {
        ActionResult ordersWereCanceled = new ActionResult(ORDERS_WERE_CANCELED);
        ManagerDao managerDao = FactoryDao.getInstance().getDaoManager();
        Integer userId = ((User) req.getSession().getAttribute(USER)).getId();
        try {
            OrderPostgresDao orderPostgresDao = managerDao.getOrderPostgresDao();
            orderPostgresDao.cancelTheOrders(userId);
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error("can't cancel the order",e);
            return error;
        } finally {
            FactoryDao.getInstance().putBackConnection(managerDao.returnConnection());
        }
        return ordersWereCanceled;
    }
}

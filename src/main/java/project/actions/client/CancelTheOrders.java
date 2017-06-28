package project.actions.client;

import org.apache.log4j.Logger;
import project.actions.Action;
import project.actions.ActionResult;
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
    @Override
    public ActionResult execute(HttpServletRequest req) {
        ManagerDao managerDao = FactoryDao.getInstance().getDaoManager();
        Integer userId = ((User) req.getSession().getAttribute(USER)).getId();
        try {
            OrderPostgresDao orderPostgresDao = managerDao.getOrderPostgresDao();
            orderPostgresDao.cancelTheOrders(userId);
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error("can't cancel the order",e);
        } finally {
            FactoryDao.getInstance().putBackConnection(managerDao.returnConnection());
        }
        return null;
    }
}

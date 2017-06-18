package project.actions.dispatcher;

import org.apache.log4j.Logger;
import project.actions.Action;
import project.actions.ActionResult;
import project.dao.managerDao.ManagerDao;
import project.dao.postgres.ExceptionDao;
import project.dao.postgres.FactoryDao;
import project.dao.postgres.OrderPostgresDao;
import project.entity.Order;
import project.util.Validator;

import javax.servlet.http.HttpServletRequest;

import java.sql.SQLException;
import java.util.List;

import static project.constants.Constants.*;

public class ShowWaitingOrders implements Action {
    private static final Logger LOGGER = Logger.getLogger(ShowWaitingOrders.class.getName());
    private ActionResult dispatcher = new ActionResult(DISPATCHER, true);
    private ActionResult error = new ActionResult(ERROR, true);

    @Override
    public ActionResult execute(HttpServletRequest req) {
        ManagerDao managerDao = FactoryDao.getInstance().getDaoManager();
        OrderPostgresDao orderPostgresDao = managerDao.getOrderDaoPostgres();
        List<Order> orders = null;
        try {
            orders = orderPostgresDao.returnTheWaitingOrders();
            if (orders == null) {
                return error;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error("can't get orders", e);
            return error;
        } finally {
            FactoryDao.getInstance().putBackConnection(managerDao.returnConnection());
        }
        req.setAttribute(ORDERS, orders);
        req.setAttribute("aaa","aaa");
        return dispatcher;
    }
}

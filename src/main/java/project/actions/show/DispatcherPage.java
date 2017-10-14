package project.actions.show;

import org.apache.log4j.Logger;
import project.actions.Action;
import project.actions.ActionException;
import project.actions.ActionResult;
import project.dao.managerDao.DaoManager;
import project.dao.postgres.FactoryDao;
import project.dao.postgres.OrderPostgresDao;
import project.entity.Order;

import javax.servlet.http.HttpServletRequest;

import java.sql.SQLException;
import java.util.List;

import static project.constants.Constants.*;

public class DispatcherPage implements Action {
    private static final Logger LOGGER = Logger.getLogger(DispatcherPage.class.getName());
    private ActionResult dispatcher = new ActionResult(DISPATCHER);
    private ActionResult error = new ActionResult(ERROR, true);
    private List<Order> orderList;

    public ActionResult execute(HttpServletRequest req) throws ActionException {

        DaoManager daoManager = FactoryDao.getInstance().getDaoManager();
        OrderPostgresDao orderPostgresDao = daoManager.getOrderPostgresDao();
        try {
            orderList = orderPostgresDao.returnTheWaitingOrders();
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error("can't get orders", e);
            return error;
        } finally {
            FactoryDao.getInstance().putBackConnection(daoManager.returnConnection());
        }
        req.setAttribute(ORDERS, orderList);

        return dispatcher;
    }
}

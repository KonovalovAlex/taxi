package project.actions.show;

import org.apache.log4j.Logger;
import project.actions.Action;
import project.actions.ActionException;
import project.actions.ActionResult;
import project.dao.managerDao.ManagerDao;
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

    public ActionResult execute(HttpServletRequest req) throws ActionException {
        ManagerDao managerDao = FactoryDao.getInstance().getDaoManager();
        OrderPostgresDao orderPostgresDao = managerDao.getOrderPostgresDao();
        List<Order> orderList = null;
        try {
            orderList = orderPostgresDao.returnTheWaitingOrders();
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error("can't get orders", e);
            return error;
        } finally {
            FactoryDao.getInstance().putBackConnection(managerDao.returnConnection());
        }
        req.setAttribute(ORDERS, orderList);

        return dispatcher;
}}

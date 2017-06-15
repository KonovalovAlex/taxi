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

import java.util.List;

import static project.constants.Constants.ORDERS;
import static project.constants.Constants.TIME;

public class ShowWaitingOrders implements Action {
    private static final Logger LOGGER = Logger.getLogger(ShowWaitingOrders.class.getName());
    private ActionResult dispatcher = new ActionResult();
    private Validator validator = new Validator();

    @Override
    public ActionResult execute(HttpServletRequest req) {
        ManagerDao managerDao = FactoryDao.getInstance().getDaoManager();
        boolean time = validator.checkTime(req.getParameter(TIME));
        if (time) {
            managerDao.beginTransaction();
            try {
                OrderPostgresDao orderPostgresDao = managerDao.getOrderDaoPostgres();
                List<Order> orders = orderPostgresDao.returnTheWaitingOrders();
                managerDao.commit();
                req.setAttribute(ORDERS, orders);
            } catch (ExceptionDao e) {
                LOGGER.error("can't get orders", e);
            }
        } else {
            req.setAttribute("timeNotCorrect", "time is not correct try again");
        }
        return dispatcher;
    }
}

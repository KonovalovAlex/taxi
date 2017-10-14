package project.actions.dispatcher;

import org.apache.log4j.Logger;
import project.actions.Action;
import project.actions.ActionResult;
import project.actions.registration.DoRegistration;
import project.dao.OrderDao;
import project.dao.managerDao.DaoManager;
import project.dao.postgres.FactoryDao;
import project.services.OrderService;

import javax.servlet.http.HttpServletRequest;

import java.sql.SQLException;

import static project.constants.Constants.*;

public class RejectOrder implements Action {
    private static final Logger LOGGER = Logger.getLogger(DoRegistration.class.getName());
    private ActionResult orderRejected = new ActionResult(ORDER_REJECTED_PAGE);
    private ActionResult error = new ActionResult(ERROR, true);
    private OrderService orderService = new OrderService();
    @Override
    public ActionResult execute(HttpServletRequest req) {
        int idOrder = Integer.parseInt(req.getParameter(REJECT_ORDER));
        try {
            orderService.rejectOrder(idOrder);
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error("can't accept order", e);
            return error;
        }
        return orderRejected;
    }
}

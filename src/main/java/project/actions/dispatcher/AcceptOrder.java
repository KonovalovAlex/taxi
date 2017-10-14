package project.actions.dispatcher;

import org.apache.log4j.Logger;
import project.actions.Action;
import project.actions.ActionResult;

import project.dao.OrderDao;
import project.dao.managerDao.DaoManager;

import project.dao.postgres.FactoryDao;
import project.services.OrderService;

import javax.servlet.http.HttpServletRequest;

import java.sql.SQLException;

import static project.constants.Constants.*;

public class AcceptOrder implements Action {
    private static final Logger LOGGER = Logger.getLogger(AcceptOrder.class.getName());
    private ActionResult error = new ActionResult(ERROR, true);
    private ActionResult orderAccept = new ActionResult(ORDER_ACCEPTED_PAGE);
    private OrderService orderService = new OrderService();
    @Override
    public ActionResult execute(HttpServletRequest req) {
        int idOrder = Integer.parseInt(req.getParameter(ACCEPT_ORDER));
        try {
            orderService.acceptOrder(idOrder);
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error("can't accept order", e);
            return error;
        }
        return orderAccept;
    }
}

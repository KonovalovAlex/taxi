package project.actions.client;


import project.actions.Action;
import project.actions.ActionResult;


import project.entity.User;
import project.services.OrderService;

import javax.servlet.http.HttpServletRequest;


import java.sql.SQLException;

import static project.constants.Constants.*;

public class CancelTheOrders implements Action {

    private ActionResult error = new ActionResult(ERROR, true);
    private ActionResult ordersWereCanceled = new ActionResult(ORDERS_WERE_CANCELED);
    private OrderService orderService = new OrderService();

    @Override
    public ActionResult execute(HttpServletRequest req) {
        try {
            orderService.cancelOrder(getUserFromRequest(req));
        } catch (SQLException e) {
            return error;
        }
        return ordersWereCanceled;
    }
    private User getUserFromRequest(HttpServletRequest req) {
        User user = new User();
        user.setId(((User) req.getSession().getAttribute(USER)).getId());
        return user;
    }
}

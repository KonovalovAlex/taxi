package project.actions.client;


import project.actions.Action;
import project.actions.ActionResult;
import project.entity.Order;
import project.entity.User;
import project.services.OrderService;
import project.util.Validator;

import javax.servlet.http.HttpServletRequest;


import java.sql.SQLException;

import static project.constants.Constants.*;

public class MakeAnOrder implements Action {
    private ActionResult error = new ActionResult(ERROR, true);
    private ActionResult orderCreated = new ActionResult(ORDER_CREATED_PAGE);
    private ActionResult addressOrTimeIsNotCorrect = new ActionResult(ADDRESS_OR_TIME_IS_NOT_CORRECT);
    private OrderService orderService = new OrderService();

    @Override
    public ActionResult execute(HttpServletRequest req) {

        Validator validator = validateFields(req);
        if (validator.isValide()) {
            Order order = getOrderFromRequest(req);
            User user = getUserFromRequest(req);
            try {
                orderService.createOrder(order,user);
            } catch (SQLException e) {
                return error;
            }
        } else {
            return addressOrTimeIsNotCorrect;
        }
        return orderCreated;
    }

    private Validator validateFields(HttpServletRequest req) {
        Validator validator = new Validator();
        validator.checkTime(req.getParameter(TIME));
        validator.checkAddress(req.getParameter(STREET));
        validator.checkAddress(req.getParameter(NUMBER_OF_HOUSE));
        validator.checkAddress(req.getParameter(NUMBER_OF_APARTMENT));
        return validator;
    }

    private Order getOrderFromRequest(HttpServletRequest req) {
        Order order = new Order();
        order.setStreet(req.getParameter(STREET));
        order.setNumberOfHouse(req.getParameter(NUMBER_OF_HOUSE));
        order.setNumberOfApartment(req.getParameter(NUMBER_OF_APARTMENT));
        order.setTime(req.getParameter(TIME));
        return order;
    }

    private User getUserFromRequest(HttpServletRequest req) {
        User user = new User();
        user.setId(((User) req.getSession().getAttribute(USER)).getId());
        return user;
    }
}


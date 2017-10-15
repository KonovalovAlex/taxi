package project.actions.client;


import project.actions.Action;
import project.actions.ActionResult;

import project.services.OrderService;

import javax.servlet.http.HttpServletRequest;


public class CancelTheOrders implements Action {

    private OrderService orderService = new OrderService();
    private ActionResult actionResult = new ActionResult();

    @Override
    public ActionResult execute(HttpServletRequest req) {
        String resultFromService = orderService.cancelOrder(req);
        boolean redirect = actionResult.redirectResult(resultFromService);
        return new ActionResult(resultFromService, redirect);
    }
}

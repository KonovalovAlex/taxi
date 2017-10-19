package project.actions.dispatcher;


import project.actions.Action;
import project.actions.ActionResult;

import project.services.OrderService;

import javax.servlet.http.HttpServletRequest;


public class AcceptOrder implements Action {
    private OrderService orderService = new OrderService();
    private ActionResult actionResult = new ActionResult();
    @Override
    public ActionResult execute(HttpServletRequest req) {
        String resultFromService = orderService.acceptOrder(req);
        boolean redirect = actionResult.redirectResult(resultFromService);
        return new ActionResult(resultFromService, redirect);
    }
}

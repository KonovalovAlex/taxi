package project.actions.client;


import project.actions.Action;
import project.actions.ActionResult;
import project.services.OrderService;

import javax.servlet.http.HttpServletRequest;

public class MakeAnOrder implements Action {
    private OrderService orderService = new OrderService();
    private ActionResult actionResult = new ActionResult();

    @Override
    public ActionResult execute(HttpServletRequest req) {

        String resultFromService = orderService.createOrder(req);
        boolean redirect = actionResult.redirectResult(resultFromService);
        return new ActionResult(resultFromService, redirect);
    }
}





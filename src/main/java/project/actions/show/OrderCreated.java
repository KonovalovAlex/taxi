package project.actions.show;


import project.actions.Action;
import project.actions.ActionException;
import project.actions.ActionResult;

import javax.servlet.http.HttpServletRequest;

import static project.constants.Constants.ORDER_ACCEPTED_PAGE;
import static project.constants.Constants.ORDER_CREATED_PAGE;

public class OrderCreated implements Action {
    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {
        return new ActionResult(ORDER_CREATED_PAGE);
    }
}

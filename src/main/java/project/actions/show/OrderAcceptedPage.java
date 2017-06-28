package project.actions.show;

import project.actions.Action;
import project.actions.ActionException;
import project.actions.ActionResult;

import javax.servlet.http.HttpServletRequest;

import static project.constants.Constants.ORDER_ACCEPTED_PAGE;


public class OrderAcceptedPage implements Action {

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {
        return new ActionResult(ORDER_ACCEPTED_PAGE);
    }
}

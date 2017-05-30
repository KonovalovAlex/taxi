package project.actions.show;

import project.actions.Action;
import project.actions.ActionException;
import project.actions.ActionResult;

import javax.servlet.http.HttpServletRequest;

import static project.constants.Constants.REGISTRATION;

public class Registration implements Action {
    private ActionResult registration = new ActionResult(REGISTRATION);

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {

        return registration;
    }
}

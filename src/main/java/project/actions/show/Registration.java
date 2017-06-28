package project.actions.show;

import project.actions.Action;
import project.actions.ActionException;
import project.actions.ActionResult;

import javax.servlet.http.HttpServletRequest;

import static project.constants.Constants.REGISTRATION;

public class Registration implements Action {

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {
        return new ActionResult(REGISTRATION);
    }
}

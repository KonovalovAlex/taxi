package project.actions.show;

import project.actions.Action;
import project.actions.ActionException;
import project.actions.ActionResult;

import javax.servlet.http.HttpServletRequest;

import static project.constants.Constants.REGISTRATION_PAGE;

public class RegistrationPage implements Action {
    private ActionResult registrationPage = new ActionResult(REGISTRATION_PAGE);

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {

        return registrationPage;
    }
}

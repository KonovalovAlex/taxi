package project.actions.registration;

import project.actions.Action;
import project.actions.ActionResult;


import project.services.RegistrationService;


import javax.servlet.http.HttpServletRequest;

public class DoRegistration implements Action {
    private RegistrationService registration = new RegistrationService();
    private ActionResult actionResult = new ActionResult();

    @Override
    public ActionResult execute(HttpServletRequest req) {
        String resultFromService = registration.tryToRegistration(req);
        boolean redirect = actionResult.redirectResult(resultFromService);
        return new ActionResult(resultFromService, redirect);
    }
}



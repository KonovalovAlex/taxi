package project.actions.login;

import project.actions.Action;
import project.actions.ActionResult;
import project.services.LoginService;

import javax.servlet.http.HttpServletRequest;

public class ActionLogin implements Action {

    private LoginService loginService = new LoginService();
    private ActionResult actionResult = new ActionResult();

    public ActionResult execute(HttpServletRequest req) {

        String resultFromService = loginService.returnRole(req);
        boolean redirect = actionResult.redirectResult(resultFromService);

        return new ActionResult(resultFromService,redirect);
    }
}







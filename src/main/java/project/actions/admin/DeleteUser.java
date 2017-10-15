package project.actions.admin;


import project.actions.Action;
import project.actions.ActionResult;


import project.services.UserService;

import javax.servlet.http.HttpServletRequest;


public class DeleteUser implements Action {
    private ActionResult actionResult = new ActionResult();
    private UserService userService = new UserService();

    @Override
    public ActionResult execute(HttpServletRequest req) {
        String resultFromService = userService.deleteUser(req);
        boolean redirect = actionResult.redirectResult(resultFromService);
        return new ActionResult(resultFromService, redirect);
    }

}

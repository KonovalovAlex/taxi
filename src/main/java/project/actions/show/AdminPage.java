package project.actions.show;


import project.actions.Action;

import project.actions.ActionResult;


import project.services.UserService;

import javax.servlet.http.HttpServletRequest;

import static project.constants.Constants.ERROR;

public class AdminPage implements Action {
    private UserService userService = new UserService();

    public ActionResult execute(HttpServletRequest req) {
        String result = userService.adminPage(req);
        if (result.equals(ERROR)) return new ActionResult(ERROR, true);

        return new ActionResult(result);

    }
}
package project.actions.show;


import project.actions.Action;
import project.actions.ActionException;
import project.actions.ActionResult;

import project.services.UserService;

import javax.servlet.http.HttpServletRequest;


import static project.constants.Constants.*;

public class DispatcherPage implements Action {

    private UserService userService = new UserService();

    public ActionResult execute(HttpServletRequest req) throws ActionException {
        String result = userService.dispatcherPage(req);
        if (result.equals(ERROR)) return new ActionResult(ERROR, true);

        return new ActionResult(result);
}}

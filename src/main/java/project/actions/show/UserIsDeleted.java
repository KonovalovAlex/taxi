package project.actions.show;


import project.actions.Action;
import project.actions.ActionException;
import project.actions.ActionResult;

import javax.servlet.http.HttpServletRequest;

import static project.constants.Constants.USER_IS_DELETED_PAGE;

public class UserIsDeleted implements Action {
    private ActionResult userIsDeleted = new ActionResult(USER_IS_DELETED_PAGE);

    public ActionResult execute(HttpServletRequest request) throws ActionException {

        return userIsDeleted;
    }
}

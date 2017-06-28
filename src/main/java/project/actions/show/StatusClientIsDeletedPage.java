package project.actions.show;


import project.actions.Action;
import project.actions.ActionResult;

import javax.servlet.http.HttpServletRequest;

import static project.constants.Constants.STATUS_CLIENT_IS_DELETED_PAGE;

public class StatusClientIsDeletedPage implements Action {

    public ActionResult execute(HttpServletRequest req) {
        return new ActionResult(STATUS_CLIENT_IS_DELETED_PAGE);
    }
}

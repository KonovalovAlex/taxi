package project.actions.show;


import project.actions.Action;
import project.actions.ActionResult;

import javax.servlet.http.HttpServletRequest;

import static project.constants.Constants.STATUS_CLIENT_IS_DELETED_PAGE;

public class StatusClientIsDeletedPage implements Action {

    @Override
    public ActionResult execute(HttpServletRequest req) {
        ActionResult statusClientIsDeleted = new ActionResult(STATUS_CLIENT_IS_DELETED_PAGE);

        return statusClientIsDeleted;
    }
}

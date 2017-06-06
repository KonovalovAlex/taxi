package project.actions.show;

import project.actions.Action;
import project.actions.ActionException;
import project.actions.ActionResult;

import javax.servlet.http.HttpServletRequest;

import static project.constants.Constants.DISPATCHER_PAGE;

public class DispatcherPage implements Action {
    private ActionResult dispatcherPage = new ActionResult(DISPATCHER_PAGE);

    public ActionResult execute(HttpServletRequest request) throws ActionException {

        return dispatcherPage;
}}

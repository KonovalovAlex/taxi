package project.actions.show;


import project.actions.Action;
import project.actions.ActionException;
import project.actions.ActionResult;

import javax.servlet.http.HttpServletRequest;

import static project.constants.Constants.ERROR;


public class ErrorPage implements Action {
    private ActionResult errorPage = new ActionResult(ERROR);

    public ActionResult execute(HttpServletRequest request) throws ActionException {

        return errorPage;
}}

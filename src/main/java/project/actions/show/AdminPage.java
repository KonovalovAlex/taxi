package project.actions.show;


import project.actions.Action;
import project.actions.ActionException;
import project.actions.ActionResult;

import javax.servlet.http.HttpServletRequest;

import static project.constants.Constants.ADMIN_PAGE;

public class AdminPage implements Action{
    private ActionResult adminPage = new ActionResult(ADMIN_PAGE);

    public ActionResult execute(HttpServletRequest request) throws ActionException {

        return adminPage;
}}

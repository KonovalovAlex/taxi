package project.actions.show;

import project.actions.Action;
import project.actions.ActionException;
import project.actions.ActionResult;

import javax.servlet.http.HttpServletRequest;

import static project.constants.Constants.LOGIN;


public class LoginPage implements Action {
    private ActionResult login = new ActionResult(LOGIN);

    public ActionResult execute(HttpServletRequest request) throws ActionException {

        return login;
}}

package project.actions.show;

import project.actions.Action;
import project.actions.ActionException;
import project.actions.ActionResult;

import javax.servlet.http.HttpServletRequest;

import static project.constants.Constants.LOGIN;


public class LoginPage implements Action {

    public ActionResult execute(HttpServletRequest request) throws ActionException {
        return new ActionResult(LOGIN);
}}

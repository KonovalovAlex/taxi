package project.actions.show;

import project.actions.ActionException;
import project.actions.ActionResult;

import javax.servlet.http.HttpServletRequest;

import static project.constants.Constants.CLIENT_PAGE;


public class ClientPage extends ActionResult {
    private ActionResult clientPage = new ActionResult(CLIENT_PAGE);

    public ActionResult execute(HttpServletRequest request) throws ActionException {

        return clientPage;
}}

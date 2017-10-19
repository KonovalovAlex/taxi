package project.controller;

import org.apache.log4j.Logger;
import project.actions.*;
import project.actions.ActionResult;
import project.util.Validator;


import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(Controller.class.getName());
    private ActionResult actionResult = new ActionResult();
    private Validator validator = new Validator();

    @Override
    public void init() throws ServletException {
        actionResult.initRedirectFields();
        validator.initValidationFields();
        super.init();
    }

    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Action action = ActionFactory.getAction(req);
        ActionResult result = null;
        if (action != null) {
            try {
                result = action.execute(req);
            } catch (ActionException e) {
                LOGGER.error("error from controller", e);
                throw new ActionException(e);
            }

            if (result.isRedirect()) {
                resp.sendRedirect(req.getContextPath() + "/Controller/" + result.getView());
                return;
            }
            req.getRequestDispatcher("/WEB-INF/" + result.getView() + ".jsp").forward(req, resp);
        }
    }
}

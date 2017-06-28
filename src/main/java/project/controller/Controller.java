package project.controller;

import org.apache.log4j.Logger;
import project.actions.*;
import project.actions.ActionResult;
import project.actions.registration.DoRegistration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(DoRegistration.class.getName());
    /*SerialVersionUID универсальный идентификатор версии для сериализации класса.
             Десериализация использует этот номер, чтобы убедиться, что загруженный класс в точности соответствует
              упорядоченному объекту.
             Иначе InvalidClassException.*/

    @Override
    public void init() throws ServletException {
    }
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Action action = ActionFactory.getAction(req);
        ActionResult result = null;
        if (action != null) {
            try {
                result = action.execute(req);
            } catch (ActionException e) {
                LOGGER.error("epic fail from controller",e);
                throw new ActionException(e);

            }

            if (result.isRedirect()) {
                 resp.sendRedirect(req.getContextPath() + "/Controller/" + result.getView());
                return;
            }
            req.getRequestDispatcher("/WEB-INF/" + result.getView() + ".jsp").forward(req, resp);
        }
    }
    @Override
    public void destroy() {
        super.destroy();
    }
}


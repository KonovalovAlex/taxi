package project.controller;

import project.actions.*;
import project.actions.ActionResult;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;
    /*SerialVersionUID универсальный идентификатор версии для сериализации класса.
             Десериализация использует этот номер, чтобы убедиться, что загруженный класс в точности соответствует
              упорядоченному объекту.
             Иначе InvalidClassException.*/

    @Override
    public void init() throws ServletException {
        super.init();
    }

    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        Action action = ActionFactory.getAction(req);
        ActionResult result = null;
        try {
            result = action.execute(req);
        } catch (ActionException e) {
            throw new ActionException(e);
        }

        if (result.isRedirect()) {
            resp.sendRedirect(req.getContextPath() + "/Controller/" + result.getView());
            return;
        }
         req.getRequestDispatcher("/WEB-INF/" + result.getView() + ".jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}


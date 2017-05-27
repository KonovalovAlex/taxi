package project.actions.login;

import project.actions.Action;
import project.actions.ActionResult;
import project.dao.ClientDao;
import project.dao.managerDao.ManagerDao;
import project.dao.postgres.FactoryDao;
import project.entity.Client;

import javax.servlet.http.HttpServletRequest;

import java.sql.SQLException;

import static project.constants.Constants.*;

public class EnterTheOffice implements Action {

    ActionResult client = new ActionResult();
    ActionResult welcome = new ActionResult();
    ActionResult admin = new ActionResult();
    ActionResult dispatcher = new ActionResult();
    Client client = new Client();

    @Override
    public ActionResult execute(HttpServletRequest req) {
    }

    public String findClient(HttpServletRequest request) {
        if (request.getParameter(LOGIN) == null || request.getParameter(PASSWORD) == null)
            return null;
        else {
            client.setEmail(request.getParameter(LOGIN));
            client.setPassword(request.getParameter(PASSWORD));
            ManagerDao managerDao = FactoryDao.getInstance().getDaoManager();
            managerDao.beginTransaction();
            try {
                ClientDao clientDao = managerDao.getClientPostgresDao();
                clientDao.
            } catch (SQLException e) {

            }
        }
    }
}





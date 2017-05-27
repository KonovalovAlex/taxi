package project.actions.registration;

import project.actions.Action;
import project.actions.ActionResult;
import project.connectionPool.ConnectionPool;
import project.dao.ClientDao;
import project.dao.postgres.ExceptionDao;
import project.dao.postgres.FactoryDao;
import project.dao.managerDao.ManagerDao;
import project.entity.Client;

import javax.servlet.http.HttpServletRequest;

import static project.constants.Constants.*;

public class DoRegistration implements Action {
    ActionResult doRegistration = new ActionResult(DO_REGISTRATION);
    ConnectionPool connectionPool = null;

    @Override
    public ActionResult execute(HttpServletRequest req) {

        ManagerDao daoManager = FactoryDao.getInstance().getDaoManager();
        Client client = createClient(req);
        if (client != null)
            daoManager.beginTransaction();
        try {
            ClientDao daoClient = daoManager.getClientPostgresDao();
            daoClient.insert(client);
            daoManager.commit();
        } catch (ExceptionDao e) {
            daoManager.rollback();
        } finally {
            daoManager.returnConnection();
        }
        return doRegistration;
    }

    private Client createClient(HttpServletRequest req) {
        Client client = new Client();
        client.setLogin(req.getParameter(LOGIN));
        client.setPassword(req.getParameter(PASSWORD));
        client.setFirstName(req.getParameter(FIRST_NAME));
        client.setLastName(req.getParameter(LAST_NAME));
        client.setEmail(req.getParameter(EMAIL));
        client.setMobile(req.getParameter(PHONE));
        return client;
    }
}

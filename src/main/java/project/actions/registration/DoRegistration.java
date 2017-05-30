package project.actions.registration;

import project.actions.Action;
import project.actions.ActionResult;
import project.connectionPool.ConnectionPool;
import project.dao.ClientDao;
import project.dao.postgres.ExceptionDao;
import project.dao.postgres.FactoryDao;
import project.dao.managerDao.ManagerDao;
import project.entity.Client;
import project.util.Validator;

import javax.servlet.http.HttpServletRequest;

import static project.constants.Constants.*;

public class DoRegistration implements Action {
    ActionResult doRegistration = new ActionResult(DO_REGISTRATION);
    ConnectionPool connectionPool = null;
    ManagerDao daoManager = FactoryDao.getInstance().getDaoManager();
    Validator validator = new Validator(daoManager);
    @Override
    public ActionResult execute(HttpServletRequest req) {


        Client client = createClient(req);
        if (client != null)
            daoManager.beginTransaction();
        try {
            ClientDao daoClient = daoManager.getClientPostgresDao();
            daoClient.insertClient(client);
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
        boolean login = validator.checkUserName(req.getParameter("login"));
        boolean password = validator.checkUserPassword(req.getParameter("password"));
        boolean firstname = validator.checkUserFirstName(req.getParameter("firstName"));
        boolean lastname = validator.checkUserLastName(req.getParameter("lastName"));
        boolean phone = validator.checkUserPhone(req.getParameter("phone"));
        boolean email = validator.checkEmail(req.getParameter("email"));
            if (validator.isValide())

                client.setLogin(req.getParameter(LOGIN));
                client.setPassword(req.getParameter(PASSWORD));
                client.setFirstName(req.getParameter(FIRST_NAME));
                client.setLastName(req.getParameter(LAST_NAME));
                client.setEmail(req.getParameter(EMAIL));
                client.setMobile(req.getParameter(PHONE));

        return client;
    }
}

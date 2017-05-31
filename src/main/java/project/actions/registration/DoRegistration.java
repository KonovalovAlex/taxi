package project.actions.registration;

import project.actions.Action;
import project.actions.ActionResult;
import project.dao.ClientDao;
import project.dao.postgres.ExceptionDao;
import project.dao.postgres.FactoryDao;
import project.dao.managerDao.ManagerDao;
import project.entity.Client;
import project.util.Validator;

import javax.servlet.http.HttpServletRequest;

import java.util.LinkedList;
import java.util.Map;

import static project.constants.Constants.*;

public class DoRegistration implements Action {
    ActionResult doRegistration = new ActionResult(DO_REGISTRATION);
    ActionResult regisrtrationFailed = new ActionResult("error");
    ManagerDao daoManager = FactoryDao.getInstance().getDaoManager();
    Validator validator = new Validator(daoManager);
    LinkedList<String> linkedList = new LinkedList();

    @Override
    public ActionResult execute(HttpServletRequest req) {


        Client client = createClient(req);
        if (client != null) {
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
        }else {
//            LOGGER.info("Creation of a client failed, {}", client);
        return regisrtrationFailed;
        }
    }

    private Client createClient(HttpServletRequest req) {

        Client client = new Client();
        boolean login = validator.checkUserName(req.getParameter(LOGIN));
        boolean password = validator.checkUserPassword(req.getParameter(PASSWORD));
        boolean firstname = validator.checkUserFirstName(req.getParameter(FIRST_NAME));
        boolean lastname = validator.checkUserLastName(req.getParameter(LAST_NAME));
        boolean phone = validator.checkUserPhone(req.getParameter(PHONE));
        boolean email = validator.checkEmail(req.getParameter(EMAIL));

        if (validator.isValide()) {
            client.setLogin(req.getParameter(LOGIN));
            client.setPassword(req.getParameter(PASSWORD));
            client.setFirstName(req.getParameter(FIRST_NAME));
            client.setLastName(req.getParameter(LAST_NAME));
            client.setEmail(req.getParameter(EMAIL));
            client.setMobile(req.getParameter(PHONE));
        } else {
            Map<String, String> invalidFields = validator.getInvalidFields();
            for (Map.Entry<String, String> field : invalidFields.entrySet()) {
                req.setAttribute(field.getKey(), field.getValue());
                return null;
            }

        }
        return client;
    }
}

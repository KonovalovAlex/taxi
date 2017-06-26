package project.actions.registration;

import org.apache.log4j.Logger;
import project.actions.Action;
import project.actions.ActionResult;
import project.dao.UserDao;
import project.dao.postgres.ExceptionDao;
import project.dao.postgres.FactoryDao;
import project.dao.managerDao.ManagerDao;
import project.entity.User;

import project.util.Validator;

import javax.servlet.http.HttpServletRequest;

import java.lang.reflect.Array;
import java.util.*;


import static project.constants.Constants.*;

public class DoRegistration implements Action {
    private static final Logger LOGGER = Logger.getLogger(DoRegistration.class.getName());
    ActionResult doRegistration = new ActionResult(DO_REGISTRATION);
    ActionResult registrationFailed = new ActionResult(ERROR, true);
    ManagerDao daoManager = FactoryDao.getInstance().getDaoManager();
    Validator validator = new Validator(daoManager);

    @Override
    public ActionResult execute(HttpServletRequest req) {

        User user = createClient(req);
        if (user != null) {
            daoManager.beginTransaction();
            try {
                UserDao userDao = daoManager.getUserPostgresDao();
                userDao.insert(user);
                daoManager.commit();
            } catch (ExceptionDao e) {
                daoManager.rollback();
                LOGGER.error("Creation of a client failed", e);
                return registrationFailed;
            } finally {
                FactoryDao.getInstance().putBackConnection(daoManager.returnConnection());
            }
            LOGGER.info("Customer registered" + user);
            return doRegistration;
        } else {
            LOGGER.warn("Creation of a client failed");
            FactoryDao.getInstance().putBackConnection(daoManager.returnConnection());
            return registrationFailed;
        }
    }

    private User createClient(HttpServletRequest req) {
        User user = new User();
        boolean login = validator.checkUserName(req.getParameter(LOGIN));
        boolean password = validator.checkUserPassword(req.getParameter(PASSWORD));
        boolean firstname = validator.checkUserFirstName(req.getParameter(FIRST_NAME));
        boolean lastname = validator.checkUserLastName(req.getParameter(LAST_NAME));
        boolean phone = validator.checkUserPhone(req.getParameter(PHONE));
        boolean email = validator.checkEmail(req.getParameter(EMAIL));

        if (validator.isValide()) {
            user.setLogin(req.getParameter(LOGIN));
            user.setPassword(req.getParameter(PASSWORD));
            user.setFirstName(req.getParameter(FIRST_NAME));
            user.setLastName(req.getParameter(LAST_NAME));
            user.setEmail(req.getParameter(EMAIL));
            user.setPhone(req.getParameter(PHONE));
        } else {
            Map<String, String> invalidFields = validator.getInvalidFields();
            req.setAttribute("invalidFieldsMap", invalidFields);
            return null;
        }
        return user;
    }
}


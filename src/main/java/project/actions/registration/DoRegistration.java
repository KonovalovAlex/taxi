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

import java.util.*;


import static project.constants.Constants.*;

public class DoRegistration implements Action {
    private static final Logger LOGGER = Logger.getLogger(DoRegistration.class.getName());
    ActionResult registration = new ActionResult(REGISTRATION);
    ActionResult error = new ActionResult(ERROR,true);
    Validator validator;


    @Override
    public ActionResult execute(HttpServletRequest req) {
        ManagerDao daoManager = FactoryDao.getInstance().getDaoManager();
        this.validator = new Validator(daoManager);

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
                return error;
            } finally {
                FactoryDao.getInstance().putBackConnection(daoManager.returnConnection());
            }
            LOGGER.info("Customer registered" + user);
            req.setAttribute("youWereRegistered", "You were registered");
            return registration;
        } else {
            LOGGER.info("Creation of a client failed");
            FactoryDao.getInstance().putBackConnection(daoManager.returnConnection());
            return registration;
        }
    }

    private User createClient(HttpServletRequest req) {
        User user = new User();
        validator.checkLogin(req.getParameter(LOGIN));
        validator.checkUserPassword(req.getParameter(PASSWORD));
        validator.checkUserFirstName(req.getParameter(FIRST_NAME));
        validator.checkUserLastName(req.getParameter(LAST_NAME));
        validator.checkUserPhone(req.getParameter(PHONE));
        validator.checkEmail(req.getParameter(EMAIL));

        if (validator.isValide()) {
            user.setLogin(req.getParameter(LOGIN));
            user.setPassword(req.getParameter(PASSWORD));
            user.setFirstName(req.getParameter(FIRST_NAME));
            user.setLastName(req.getParameter(LAST_NAME));
            user.setEmail(req.getParameter(EMAIL));
            user.setPhone(req.getParameter(PHONE));
        } else {
            Map<String, String> invalidFields = validator.getInvalidFields();
            CustomMap<String, String> customMap = new CustomMap<>(invalidFields);
            req.setAttribute("invalidFields", customMap.getValues());
            return null;
        }
        return user;
    }

    private class CustomMap<K, V> {
        Map<K, V> someMap = new HashMap<>();

        public CustomMap(Map<K, V> map) {
            this.someMap = map;
        }

        public ArrayList<V> getValues() {
            return new ArrayList<>(someMap.values());
        }

    }
}



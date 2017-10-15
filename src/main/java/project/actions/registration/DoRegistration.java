package project.actions.registration;

import org.apache.log4j.Logger;
import project.actions.Action;
import project.actions.ActionResult;
import project.dao.managerDao.DaoManager;
import project.dao.postgres.FactoryDao;
import project.dao.postgres.UserPostgresDao;
import project.entity.User;

import project.util.Validator;

import javax.servlet.http.HttpServletRequest;

import java.sql.SQLException;
import java.util.*;


import static project.constants.Constants.*;

public class DoRegistration implements Action {
    private static final Logger LOGGER = Logger.getLogger(DoRegistration.class.getName());
    private Validator validator;
    private ActionResult registration = new ActionResult(REGISTRATION);
    private ActionResult error = new ActionResult(ERROR, true);

    @Override
    public ActionResult execute(HttpServletRequest req) {
        DaoManager daoManager = FactoryDao.getInstance().getDaoManager();
        UserPostgresDao userPostgresDao = daoManager.getUserPostgresDao();
        this.validator = new Validator(daoManager);
        if (validateFieldsOfClient(req)) {
            User user = createUser(req);
            daoManager.beginTransaction();
            try {
                userPostgresDao.insert(user);
                daoManager.commit();
            } catch (SQLException e) {
                daoManager.rollback();
                LOGGER.error("Creation of a client failed", e);
                return error;
            }
            LOGGER.info("Customer registered" + user);
            req.setAttribute("youWereRegistered", "You were registered");
            return registration;
        } else {
            LOGGER.info("Creation of a client failed");
            return registration;
        }
    }

    private User createUser(HttpServletRequest req) {
        User user = new User();
        user.setLogin(req.getParameter(LOGIN));
        user.setPassword(req.getParameter(PASSWORD));
        user.setFirstName(req.getParameter(FIRST_NAME));
        user.setLastName(req.getParameter(LAST_NAME));
        user.setEmail(req.getParameter(EMAIL));
        user.setPhone(req.getParameter(PHONE));
        return user;
    }

    private boolean validateFieldsOfClient(HttpServletRequest req) {
        validator.init();
        validator.checkLogin(req.getParameter(LOGIN));
        validator.checkUserPassword(req.getParameter(PASSWORD));
        validator.checkUserFirstName(req.getParameter(FIRST_NAME));
        validator.checkUserLastName(req.getParameter(LAST_NAME));
        validator.checkUserPhone(req.getParameter(PHONE));
        validator.checkEmail(req.getParameter(EMAIL));
        if (validator.isValide()) {
            return true;
        } else {
            Map<String, String> invalidFields = validator.getInvalidFields();
            CustomMap<String, String> customMap = new CustomMap<>(invalidFields);
            req.setAttribute(INVALID_FIELDS, customMap.getValues());
            return false;
        }
    }

    private class CustomMap<K, V> {
        Map<K, V> someMap = new HashMap<>();

        private CustomMap(Map<K, V> map) {
            this.someMap = map;
        }

        private ArrayList<V> getValues() {
            return new ArrayList<>(someMap.values());
        }
    }
}



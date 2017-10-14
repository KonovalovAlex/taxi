package project.services;

import org.apache.log4j.Logger;
import project.actions.ActionResult;
import project.actions.login.ActionLogin;
import project.dao.managerDao.DaoManager;
import project.dao.postgres.FactoryDao;
import project.dao.postgres.UserPostgresDao;
import project.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

import static project.constants.Constants.*;
import static project.constants.Constants.STATUS_USER_IS_DELETED;
import static project.constants.Constants.USER;

public class LoginService {
    private static final Logger LOGGER = Logger.getLogger(LoginService.class.getName());
    private ActionResult error = new ActionResult(ERROR, true);
    private ActionResult wrongData = new ActionResult(WRONG_DATA);
    private ActionResult admin = new ActionResult(ADMIN, true);
    private ActionResult dispatcherPage = new ActionResult(DISPATCHER, true);
    private ActionResult client = new ActionResult(CLIENT, true);
    private ActionResult statusClientIsDeleted = new ActionResult(STATUS_CLIENT_IS_DELETED_PAGE);

    public ActionResult returnRolePage(HttpServletRequest req) throws SQLException {
        HttpSession session = req.getSession();
        DaoManager daoManager = FactoryDao.getInstance().getDaoManager();
        UserPostgresDao userPostgresDao = daoManager.getUserPostgresDao();
        try {
            User user = userPostgresDao.getUserByLogin(req.getParameter(LOGIN));
            if (user != null) {
                if ((userPostgresDao.checkCredentials(user, req.getParameter(PASSWORD)))) {

                    if (user.getRole().getName().equalsIgnoreCase(ADMIN)) {
                        session.setAttribute(USER, user);
                        return admin;
                    }
                    if (user.getRole().getName().equalsIgnoreCase(DISPATCHER)) {
                        session.setAttribute(USER, user);
                        return dispatcherPage;
                    }
                    if (user.getRole().getName().equalsIgnoreCase(CLIENT)) {
                        if (user.getActivityStatus().equals(STATUS_USER_IS_DELETED)) {
                            return statusClientIsDeleted;
                        } else {
                            session.setAttribute(USER, user);
                            return client;
                        }
                    }
                }
            } else return wrongData;
        } catch (SQLException e) {
            LOGGER.error("action login false", e);
            return error;
        } finally {
            FactoryDao.getInstance().putBackConnection(daoManager.returnConnection());
        }
        return wrongData;
    }
}

package project.actions.login;

import org.apache.log4j.Logger;
import project.actions.Action;
import project.actions.ActionResult;
import project.dao.managerDao.ManagerDao;
import project.dao.postgres.FactoryDao;
import project.dao.postgres.UserPostgresDao;
import project.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.sql.SQLException;

import static project.constants.Constants.*;

public class ActionLogin implements Action {
    private static final Logger LOGGER = Logger.getLogger(ActionLogin.class.getName());

    public ActionResult execute(HttpServletRequest req) {

        ActionResult error = new ActionResult(ERROR, true);
        ActionResult wrongData = new ActionResult(WRONG_DATA);
        ActionResult admin = new ActionResult(ADMIN, true);
        ActionResult dispatcherPage = new ActionResult(DISPATCHER, true);
        ActionResult client = new ActionResult(CLIENT, true);
        ActionResult statusClientIsDeleted = new ActionResult(STATUS_CLIENT_IS_DELETED_PAGE);

        HttpSession session = req.getSession();
        ManagerDao managerDao = FactoryDao.getInstance().getDaoManager();
        UserPostgresDao userPostgresDao = managerDao.getUserPostgresDao();
        try {
            User user = userPostgresDao.findUserByLogin(req.getParameter(LOGIN));
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
            e.printStackTrace();
            LOGGER.error("action login false", e);
            return error;
        } finally {
            FactoryDao.getInstance().putBackConnection(managerDao.returnConnection());
        }
        return wrongData;
    }
}






package project.actions.show;


import org.apache.log4j.Logger;
import project.actions.Action;

import project.actions.ActionResult;

import project.dao.managerDao.DaoManager;
import project.dao.postgres.FactoryDao;
import project.dao.postgres.UserPostgresDao;

import javax.servlet.http.HttpServletRequest;

import java.sql.SQLException;
import java.util.List;

import static project.constants.Constants.ADMIN_PAGE;
import static project.constants.Constants.ERROR;
import static project.constants.Constants.USERS;

public class AdminPage implements Action {
    private static final Logger LOGGER = Logger.getLogger(AdminPage.class.getName());
    private ActionResult adminPage = new ActionResult(ADMIN_PAGE);
    private ActionResult error = new ActionResult(ERROR, true);

    public ActionResult execute(HttpServletRequest req) {

        DaoManager daoManager = FactoryDao.getInstance().getDaoManager();
        try {
            UserPostgresDao userPostgresDao = daoManager.getUserPostgresDao();
            List users = userPostgresDao.returnAllUsers();
            if (users != null) {
                req.setAttribute(USERS, users);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error("can't show users", e);
            return error;
        } finally {
            FactoryDao.getInstance().putBackConnection(daoManager.returnConnection());
        }
        return adminPage;
    }
}
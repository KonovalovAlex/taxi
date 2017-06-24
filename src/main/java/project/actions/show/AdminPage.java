package project.actions.show;


import project.actions.Action;
import project.actions.ActionException;
import project.actions.ActionResult;
import project.dao.managerDao.ManagerDao;
import project.dao.postgres.FactoryDao;
import project.dao.postgres.UserPostgresDao;

import javax.servlet.http.HttpServletRequest;

import java.sql.SQLException;
import java.util.List;

import static project.constants.Constants.ADMIN_PAGE;
import static project.constants.Constants.ERROR_PAGE;

public class AdminPage implements Action {
    private ActionResult adminPage = new ActionResult(ADMIN_PAGE);
    private ActionResult error = new ActionResult(ERROR_PAGE,true);

    public ActionResult execute(HttpServletRequest req) {
        try {
            ManagerDao managerDao = FactoryDao.getInstance().getDaoManager();
            UserPostgresDao userPostgresDao = managerDao.getUserPostgresDao();
            List users = userPostgresDao.returnAllUsers();
            if (users != null) {
                req.setAttribute("users", users);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return error;
        } finally {
            FactoryDao.getInstance().putBackConnection();
        }
        return adminPage;
    }
}
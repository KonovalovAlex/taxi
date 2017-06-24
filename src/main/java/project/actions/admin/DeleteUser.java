package project.actions.admin;

import project.actions.Action;
import project.actions.ActionResult;
import project.dao.managerDao.ManagerDao;
import project.dao.postgres.FactoryDao;
import project.dao.postgres.UserPostgresDao;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

import static project.constants.Constants.ERROR;
import static project.constants.Constants.ID;
import static project.constants.Constants.USER_DELETED_PAGE;


public class DeleteUser implements Action {
    ActionResult userDeleted = new ActionResult(USER_DELETED_PAGE,true);
    ActionResult error = new ActionResult(ERROR,true);

    @Override
    public ActionResult execute(HttpServletRequest req) {
        int userId = Integer.parseInt(req.getParameter(ID));
        ManagerDao managerDao = FactoryDao.getInstance().getDaoManager();
        UserPostgresDao userPostgresDao = managerDao.getUserPostgresDao();
        try {
            if (userPostgresDao.deleteUser(userId))return userDeleted;
        } catch (SQLException e) {
            e.printStackTrace();
            return error;
        } finally {
            FactoryDao.getInstance().putBackConnection();
        }
        return null;
    }
}

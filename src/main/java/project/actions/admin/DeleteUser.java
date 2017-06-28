package project.actions.admin;

import org.apache.log4j.Logger;
import project.actions.Action;
import project.actions.ActionResult;
import project.actions.registration.DoRegistration;
import project.dao.managerDao.ManagerDao;
import project.dao.postgres.FactoryDao;
import project.dao.postgres.UserPostgresDao;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

import static project.constants.Constants.ERROR;
import static project.constants.Constants.ID;
import static project.constants.Constants.USER_IS_DELETED_PAGE;


public class DeleteUser implements Action {
    private static final Logger LOGGER = Logger.getLogger(DeleteUser.class.getName());
    ActionResult userIsDeleted = new ActionResult(USER_IS_DELETED_PAGE);
    ActionResult error = new ActionResult(ERROR, true);

    @Override
    public ActionResult execute(HttpServletRequest req) {
        Integer userId = Integer.valueOf(req.getParameter(ID));
        ManagerDao managerDao = FactoryDao.getInstance().getDaoManager();
        UserPostgresDao userPostgresDao = managerDao.getUserPostgresDao();
        managerDao.beginTransaction();
        try {
            userPostgresDao.deleteUser(userId);
            managerDao.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            managerDao.rollback();
            LOGGER.error("can't delete user",e);
            return error;
        } finally {
            FactoryDao.getInstance().putBackConnection(managerDao.returnConnection());
        }
        return userIsDeleted;
    }
}

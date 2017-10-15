package project.services;

import project.dao.managerDao.DaoManager;
import project.dao.postgres.FactoryDao;
import project.dao.postgres.UserPostgresDao;
import project.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

import static project.constants.Constants.ERROR;
import static project.constants.Constants.USER;
import static project.constants.Constants.USER_IS_DELETED_PAGE;


public class UserService {
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(UserService.class.getName());

    public String deleteUser(HttpServletRequest req) {
        DaoManager daoManager = FactoryDao.getInstance().getDaoManager();
        UserPostgresDao userPostgresDao = daoManager.getUserPostgresDao();
        try {
            daoManager.beginTransaction();
            userPostgresDao.deleteUser(getUserFromRequest(req).getId());
            daoManager.commit();
        } catch (SQLException e) {
            daoManager.rollback();
            LOGGER.error("can't delete user", e);
            return ERROR;
        } finally {
            FactoryDao.getInstance().putBackConnection(daoManager.returnConnection());
        }
        return USER_IS_DELETED_PAGE;
    }

    private User getUserFromRequest(HttpServletRequest req) {
        User user = new User();
        user.setId(((User) req.getSession().getAttribute(USER)).getId());
        return user;
    }
}

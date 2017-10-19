package project.services;

import org.apache.log4j.Logger;

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

    public String returnRole(HttpServletRequest req){
        HttpSession session = req.getSession();
        DaoManager daoManager = FactoryDao.getInstance().getDaoManager();
        UserPostgresDao userPostgresDao = daoManager.getUserPostgresDao();
        try {
            User user = userPostgresDao.getUserByLogin(req.getParameter(LOGIN));
            if (user != null) {
                if ((userPostgresDao.checkCredentials(user, req.getParameter(PASSWORD)))) {

                    if (user.getRole().getName().equalsIgnoreCase(ADMIN)) {
                        session.setAttribute(USER, user);
                        return ADMIN;
                    }
                    if (user.getRole().getName().equalsIgnoreCase(DISPATCHER)) {
                        session.setAttribute(USER, user);
                        return DISPATCHER_PAGE;
                    }
                    if (user.getRole().getName().equalsIgnoreCase(CLIENT)) {
                        if (user.getActivityStatus().equals(STATUS_USER_IS_DELETED)) {
                            return STATUS_CLIENT_IS_DELETED_PAGE;
                        } else {
                            session.setAttribute(USER, user);
                            return CLIENT;
                        }
                    }
                }
            } else return WRONG_DATA;
        } catch (SQLException e) {
            LOGGER.error("action login false", e);
            return ERROR;
        } finally {
            FactoryDao.getInstance().putBackConnection(daoManager.returnConnection());
        }
        return WRONG_DATA;
    }
}

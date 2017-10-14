package project.services;

import project.dao.managerDao.DaoManager;
import project.dao.postgres.FactoryDao;
import project.dao.postgres.UserPostgresDao;

import java.sql.SQLException;


public class UserService {
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(UserService.class.getName());

    public void deleteUser(int id) throws SQLException {
        DaoManager daoManager = FactoryDao.getInstance().getDaoManager();
        UserPostgresDao userPostgresDao = daoManager.getUserPostgresDao();
        try {
            daoManager.beginTransaction();
            userPostgresDao.deleteUser(id);
            daoManager.commit();
        } catch (SQLException e) {
            daoManager.rollback();
            LOGGER.error("can't delete user", e);
        } finally {
            FactoryDao.getInstance().putBackConnection(daoManager.returnConnection());
        }
    }

}

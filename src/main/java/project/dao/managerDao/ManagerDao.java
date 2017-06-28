package project.dao.managerDao;

import org.apache.log4j.Logger;
import project.actions.registration.DoRegistration;
import project.dao.postgres.UserPostgresDao;
import project.dao.postgres.ExceptionDao;
import project.dao.postgres.OrderPostgresDao;

import java.sql.Connection;
import java.sql.SQLException;

public class ManagerDao {
    private static final Logger LOGGER = Logger.getLogger(ManagerDao.class.getName());
    private final Connection connection;

    public ManagerDao(Connection connection) {
        this.connection = connection;

    }

    public void beginTransaction() throws ExceptionDao {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.error("connection auto commit",e);
            throw new ExceptionDao(e);
        }
    }

    public void commit() throws ExceptionDao {
        try {
            connection.commit();
        } catch (SQLException e) {
            LOGGER.error("connection commit",e);
            throw new ExceptionDao(e);
        }
    }

    public void rollback() throws ExceptionDao {
        try {
            connection.rollback();
        } catch (SQLException e) {
            LOGGER.error("connection rollback",e);
            throw new ExceptionDao(e);
        }
    }
    public Connection returnConnection (){
        return connection;
    }

    public UserPostgresDao getUserPostgresDao() {
        return new UserPostgresDao(connection, this);
    }

    public OrderPostgresDao getOrderPostgresDao() {
        return new OrderPostgresDao(connection, this);
    }
}

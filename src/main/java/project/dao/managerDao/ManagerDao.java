package project.dao.managerDao;

import project.connectionPool.ConnectionPool;
import project.dao.Dao;
import project.dao.postgres.AbstractPostgresDao;
import project.dao.postgres.ClientPostgresDao;
import project.dao.postgres.ExceptionDao;
import project.dao.postgres.MakeAnOrderDaoPostgres;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ManagerDao {
    private final Connection connection;
    private static Map<String, AbstractPostgresDao> daoMap = new HashMap<>();

    public ManagerDao(Connection connection, Map<String, AbstractPostgresDao> daoMap) {
        this.connection = connection;
        this.daoMap = daoMap;
    }

    public void beginTransaction() throws ExceptionDao {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new ExceptionDao(e);
        }
    }

    public void commit() throws ExceptionDao {
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new ExceptionDao(e);
        }
    }

    public void rollback() throws ExceptionDao {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new ExceptionDao(e);
        }
    }
    public Connection returnConnection(){
        return connection;
    }

    public void closeConnection() throws ExceptionDao {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new ExceptionDao(e);
        }
    }

    public ClientPostgresDao getClientPostgresDao() {
        return new ClientPostgresDao(connection,this);
    }
    public MakeAnOrderDaoPostgres getMakeAnOrderDaoPostgres(){
        return new MakeAnOrderDaoPostgres(connection,this);
    }
}

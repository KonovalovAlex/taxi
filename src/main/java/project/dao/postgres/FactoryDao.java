package project.dao.postgres;

import org.apache.log4j.Logger;
import project.connectionPool.ConnectionPool;
import project.dao.managerDao.ManagerDao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class FactoryDao {
    private static final Logger LOGGER = Logger.getLogger(FactoryDao.class.getName());
    private ConnectionPool pool;
    private String url;
    private String user;
    private String password;
    private int maxConnection;

    public FactoryDao() {
        Properties properties = new Properties();
        String fileName = "db.properties";
        InputStream inputStream = ConnectionPool.class.getClassLoader().getResourceAsStream(fileName);
        try {
            properties.load(inputStream);
            this.url = properties.getProperty("jdbc.url");
            this.user = properties.getProperty("user");
            this.password = properties.getProperty("password");
            this.maxConnection = Integer.parseInt(properties.getProperty("maxConnection"));
            this.pool = ConnectionPool.getInstance(url, user, password, maxConnection);
        } catch (IOException e) {
            LOGGER.error("connection property error",e);
            e.printStackTrace();
        }
    }

    public ManagerDao getDaoManager() throws ExceptionDao {
        Connection connection;
        try {
            connection = pool.getConnection();
        } catch (ExceptionDao e) {
            LOGGER.error("error in FactoryDao method getDaoManager",e);
            throw new ExceptionDao(e);
        }
        return new ManagerDao(connection);
    }
    public void putBackConnection(Connection connection){
        pool.returnConnection(connection);
    }

    public static FactoryDao getInstance() {
        return InstanceHolder.instance;
    }
    //singleton
    private static class InstanceHolder {
        private static FactoryDao instance = new FactoryDao();
    }
}





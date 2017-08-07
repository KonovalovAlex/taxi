package project.connectionPool;

import org.apache.log4j.Logger;
import project.actions.registration.DoRegistration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static project.constants.Constants.DRIVER;

public class ConnectionPool {
    private static final Logger LOGGER = Logger.getLogger(DoRegistration.class.getName());
    private static ConnectionPool instance;
    private String url;
    private String user;
    private String password;
    private int maxСonnection;
    private List<Connection> freeConnections = null;

    private ConnectionPool(String url, String user, String password, int maxСonnection) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.maxСonnection = maxСonnection;
        this.freeConnections = new ArrayList<>();
        this.fillConnectionPool();
    }

    public static ConnectionPool getInstance(String url, String user, String password, int maxConnection) {
        if (instance == null) {
            instance = new ConnectionPool(url, user, password, maxConnection);
        }
        return instance;
    }

    private void fillConnectionPool() {
        try {
            Class.forName(DRIVER);
            Connection con = null;
            for (int i = 0; i < maxСonnection; i++) {
                con = DriverManager.getConnection(url, user, password);
                freeConnections.add(con);
                LOGGER.debug("connection pool completely filled");
            }
        } catch ( SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            LOGGER.error("connection pool",e);
        }
    }

    public Connection getConnection() {
        Connection connection = freeConnections.get(freeConnections.size()-1);
        freeConnections.remove(freeConnections.size()-1);
        return connection;
    }

    public void returnConnection(Connection connection){
            freeConnections.add(connection);
        }
    }

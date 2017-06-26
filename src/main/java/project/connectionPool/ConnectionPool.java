package project.connectionPool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static project.constants.Constants.DRIVER;

public class ConnectionPool {
    private static ConnectionPool instance;
    private String url;
    private String user;
    private String password;
    private int maxСonnection;
    private List<Connection> freeConnections = null;
    //TODO need to change collection

    private ConnectionPool(String url, String user, String password, int maxСonnection) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.maxСonnection = maxСonnection;
        freeConnections = new ArrayList<>();
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
            for (int i = 0; i < maxСonnection; i++ ) {
                con = DriverManager.getConnection(url, user, password);
                freeConnections.add(con);
            }
        } catch ( SQLException | ClassNotFoundException e) {
            e.printStackTrace();
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

package project.connectionPool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static project.constants.Constants.DRIVER;

public class ConnectionPool {
    private static ConnectionPool instance;
    private String url;
    private String user;
    private String password;
    private int maxСonnection;
    private BlockingQueue<Connection> freeConnections = null;
    //TODO need to change collection

    private ConnectionPool(String url, String user, String password, int maxСonnection) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.maxСonnection = maxСonnection;
        freeConnections = new ArrayBlockingQueue<>(maxСonnection);
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
                freeConnections.put(con);
            }
        } catch (InterruptedException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        Connection con = null;
        try {
            con = freeConnections.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return con;
    }

    public void returnConnection(Connection connection) {
        try {
            freeConnections.put(connection);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
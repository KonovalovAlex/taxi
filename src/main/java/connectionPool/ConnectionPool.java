package connectionPool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
    private static ConnectionPool instance;
    private String url = "jdbc:postgresql://localhost:5432/taxiDB";
    private String user = "postgres";
    private String password = "alex";
    private int maxСonn = 10;

    private BlockingQueue<Connection> freeConnections = new ArrayBlockingQueue<>(maxСonn);

    private ConnectionPool(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.fillConnectionPool();
    }

    public static synchronized ConnectionPool getInstance(String url, String user, String password) {
        if (instance == null) {
            instance = new ConnectionPool(url, user, password);
        }
        return instance;
    }

    public void fillConnectionPool() {
        for (int i = 0; i < maxСonn; i++) {
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(url, user, password);
                freeConnections.put(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized Connection getConnection() {
            Connection con = null;
                try {
                    con = freeConnections.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
            }
        return con;
    }
    }
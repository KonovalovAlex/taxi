
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {

    DataSource dataSource = null;

    public void init() throws SQLException {
        try {
            Context envCtx = (Context) new InitialContext().lookup("java:comp/env");
            DataSource ds = (DataSource) envCtx.lookup("jdbc/testphones");
            Connection cn = ds.getConnection();
            cn.close();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}




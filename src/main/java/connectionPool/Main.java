package connectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        ConnectionPool pool = null;
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            pool = ConnectionPool.getInstance(
                    "org.postgresql.Driver", "jdbc:postgresql://localhost:5432/taxiDB",
                    "postgres", "alex", 5);
            con = pool.getConnection();
            st = con.createStatement();
            rs = st.executeQuery(
                    "SELECT * FROM USERS");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
            }
            con.close();
            pool.freeConnection(con);
            pool.release();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) st.close();
                if (rs != null) rs.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }
}

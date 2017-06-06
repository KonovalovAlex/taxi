package project.dao.postgres;

import project.dao.MakeAnOrderDao;
import project.dao.managerDao.ManagerDao;
import project.entity.Address;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MakeAnOrderDaoPostgres extends AbstractPostgresDao<Address> implements MakeAnOrderDao {
    public static final String GET_ADDRESSES = "SELECT AT_THE_POINT_OF FROM ADDRESSES";
    private Connection connection;
    private ManagerDao managerDao;

    public MakeAnOrderDaoPostgres(Connection connection, ManagerDao managerDao) {
        super(connection);
        this.connection = connection;
        this.managerDao = managerDao;
    }
    public void getAddress(HttpServletRequest request) {
        List<String> allAddresses = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ADDRESSES);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                allAddresses.add(resultSet.getString("AT_THE_POINT_OF"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("addresses",allAddresses);
    }
}

package project.dao.postgres;

import project.dao.ClientDao;
import project.dao.managerDao.ManagerDao;
import project.entity.Client;

import java.sql.*;

public class ClientPostgresDao extends AbstractPostgresDao<Client> implements ClientDao {
    private static final String ADD_CLIENT = "INSERT INTO USERS VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String DELETE_POSITION = "UPDATE USERS SET DELETED = ? WHERE ID = ?";
    private static final String TABLE = "CLIENTS";
    Connection connection;
    ManagerDao managerDao;

    public ClientPostgresDao() {
    }

    public ClientPostgresDao(Connection connection, ManagerDao managerDao) {
        this.connection = connection;
        this.managerDao = managerDao;
    }

    public ClientPostgresDao insert(Client client) {

        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_CLIENT)) {
            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, client.getLogin());
            preparedStatement.setString(3, client.getPassword());
            preparedStatement.setString(4, client.getFirstName());
            preparedStatement.setString(5, client.getLastName());
            preparedStatement.setString(6, client.getEmail());
            preparedStatement.setString(7, client.getMobile());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean findByEmail() {
        Client client = new Client();
        String email = client.getEmail();
        String slq = "SELECT * FROM CLIENTS WHERE EMAIL =" + "'" + email + "'";
        try (Statement statement = connection.createStatement();) {
            ResultSet resultSet = statement.executeQuery(slq);
            if (resultSet.next()) return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean findByPassword() {
        Client client = new Client();
        String email = client.getEmail();
        String slq = "SELECT * FROM CLIENTS WHERE PASSWORD =" + "'" + email + "'";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(slq);
            if (resultSet.next()) return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }
}

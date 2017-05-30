package project.dao.postgres;

import project.dao.ClientDao;
import project.dao.managerDao.ManagerDao;
import project.entity.Client;

import java.sql.*;

public class ClientPostgresDao extends AbstractPostgresDao<Client> implements ClientDao {
    Connection connection;
    ManagerDao managerDao;

    public ClientPostgresDao(Connection connection, ManagerDao managerDao) {
        super(connection);
        this.connection = connection;
        this.managerDao = managerDao;
    }

    @Override
    public Client get(String sql) {
        return null;
    }

    @Override
    public int insertClient(Client client) {
        return this.insert("users",
                client.getLogin(),
                client.getPassword(),
                client.getFirstName(),
                client.getLastName(),
                client.getEmail(),
                client.getMobile());
    }
    @Override
    public boolean updateEntity() {
        return false;
    }

    @Override
    public boolean deleteEntity() {
        return false;
    }

//    public boolean findByEmail() {
//        Client client = new Client();
//        String email = client.getEmail();
//        String slq = "SELECT * FROM USERS WHERE EMAIL =" + "'" + email + "'";
//        return false;
//    }
//
//    public boolean findByPassword() {
//        Client client = new Client();
//        String email = client.getEmail();
//        String slq = "SELECT * FROM USERS WHERE PASSWORD =" + "'" + email + "'";
//        try (Statement statement = connection.createStatement()) {
//            ResultSet resultSet = statement.executeQuery(slq);
//            if (resultSet.next()) return true;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
public boolean alreadyExist(String login) {

    String sql = "SELECT * FROM USERS where LOGIN = " + "'" + login + "'";
    try (PreparedStatement prstm = connection.prepareStatement(sql)) {
        try (ResultSet resultSet = prstm.executeQuery()) {
//            LOGGER.info("Client is already exist{}",
                    resultSet.next();
            return resultSet.next();
        }
    } catch (Exception e) {
//        LOGGER.error("Error of Client finding by exist{}", e);
        throw new ExceptionDao(e);

    }}}

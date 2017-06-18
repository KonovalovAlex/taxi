package project.dao.postgres;

import project.dao.UserDao;
import project.dao.managerDao.ManagerDao;
import project.entity.User;

import java.sql.*;

import project.entity.UserRole;

public class UserPostgresDao extends AbstractPostgresDao<User> implements UserDao {
    Connection connection;
    ManagerDao managerDao;
//    private static final Logger LOGGER = Logger.getLogger(UserPostgresDao.class);

    public UserPostgresDao(Connection connection, ManagerDao managerDao) {
        super(connection);
        this.connection = connection;
        this.managerDao = managerDao;
    }

    public User getUserByLogin(String login) throws SQLException {
        User user = new User();
        UserRole userRole = new UserRole();
        String slq = "select * from users inner join rols on users.fk_rols=rols.id where users.login ='" + login + "'";
        try (PreparedStatement preparedStatement = connection.prepareStatement(slq)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            userRole.setId(resultSet.getInt(9));
            userRole.setName(resultSet.getString("user_role"));
            user.setFirstName(resultSet.getString("first_name"));
            user.setLastName(resultSet.getString("last_name"));
            user.setLogin(resultSet.getString("login"));
            user.setPassword(resultSet.getString("password"));
            user.setEmail(resultSet.getString("email"));
            user.setPhone(resultSet.getString("phone"));
            user.setId(resultSet.getInt(7));
            user.setRole(userRole);

//            LOGGER.info("role is" + user);
        }
        return user;
    }


    @Override
    public int insert(User user) {
        return this.insert("users",
                user.getLogin(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhone());
    }

    public boolean findByPassword(String password) {
        String slq = "select * from users where password =" + "'" + password + "'";
        try (PreparedStatement preparedStatement = connection.prepareStatement(slq)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean alreadyExist(String login) {

        String sql = "select * from users where login =" + "'" + login + "'";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                LOGGER.info("Client is already exist{}", );
                return resultSet.next();
            }
        } catch (Exception e) {
//        LOGGER.error("Error of Client finding by exist{}", e);
            throw new ExceptionDao(e);
        }
    }

    //if user = null - return(false) else check password if password is equal return true else false
    public boolean checkCredentials(User user, String password) {
        return user != null && user.getPassword().equals(password);
    }
}

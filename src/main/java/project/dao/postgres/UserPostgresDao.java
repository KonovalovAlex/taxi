package project.dao.postgres;

import org.apache.log4j.Logger;
import project.actions.show.AdminPage;
import project.dao.UserDao;
import project.dao.managerDao.ManagerDao;
import project.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import project.entity.UserRole;

import static project.constants.Constants.*;

public class UserPostgresDao extends AbstractPostgresDao<User> implements UserDao {
    private static final Logger LOGGER = Logger.getLogger(UserPostgresDao.class.getName());
    Connection connection;
    ManagerDao managerDao;

    public UserPostgresDao(Connection connection, ManagerDao managerDao) {
        super(connection);
        this.connection = connection;
        this.managerDao = managerDao;
    }

    public User getUserByLogin(String login) throws SQLException {
        User user = new User();
        UserRole userRole = new UserRole();
        String sql = "select * from users inner join rols on users.fk_rols=rols.id where users.login ='" + login + "'";
        try (PreparedStatement prstm = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = prstm.executeQuery()) {
                if (resultSet.next()) {
                    user.setActivityStatus(resultSet.getString(ACTIVE_STATUS_COLUMN));
                    userRole.setId(resultSet.getInt("fk_rols"));
                    userRole.setName(resultSet.getString("user_role"));
                    user.setFirstName(resultSet.getString("first_name"));
                    user.setLastName(resultSet.getString("last_name"));
                    user.setLogin(resultSet.getString(LOGIN));
                    user.setPassword(resultSet.getString(PASSWORD));
                    user.setEmail(resultSet.getString(EMAIL));
                    user.setPhone(resultSet.getString(PHONE));
                    user.setId(resultSet.getInt(ID));
                    user.setRole(userRole);
                    LOGGER.info("user is" + user);
                    return user;
                } else return null;
            }
        } catch (ExceptionDao e) {
            LOGGER.error("Error of Client finding by login", e);
            throw new ExceptionDao(e);
        }
    }

    @Override
    public int insert(User user) {
        return this.insert(USERS,
                user.getLogin(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhone(),
                STATUS_USER_ACTIVE,
                FK_ROLE_CLIENT);
    }

    public boolean alreadyExist(String login) {
        String sql = "select * from users where login =" + "'" + login + "'";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                LOGGER.info("Client is already exist{}");
                return resultSet.next();
            }
        } catch (Exception e) {
            LOGGER.error("Error of Client finding by exist{}", e);
            throw new ExceptionDao(e);
        }
    }

    //if user = null - return(false) else check password if password is equal return true else false
    public boolean checkCredentials(User user, String password) {
        return user != null & user.getPassword().equals(password);
    }

    public List<User> returnAllUsers() throws SQLException {
        String sql = "select * from users full join rols on users.fk_rols = rols.id";
        List<User> users = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                UserRole userRole = new UserRole();
                user.setLogin(resultSet.getString(LOGIN));
                user.setEmail(resultSet.getString(EMAIL));
                user.setPassword(resultSet.getString(PASSWORD));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setPhone(resultSet.getString(PHONE));
                user.setId(resultSet.getInt(9));
                user.setActivityStatus(resultSet.getString("activity_status"));
                userRole.setName(resultSet.getString("user_role"));
                user.setRole(userRole);
                users.add(user);
            }
        }
        return users;
    }

    public boolean deleteUser(int userId) throws SQLException {
        getParams().put(ACTIVE_STATUS_COLUMN, STATUS_USER_IS_DELETED);
        getConditions().put(ID, userId);
        return this.updateEntity(USERS, params, conditions);
    }
}

package project.dao;

import project.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao extends Dao<User> {
    int insert(User user);
    boolean findByPassword(String password);
    boolean alreadyExist(String login);
    boolean checkCredentials(User user, String password);
    List<User> returnAllUsers() throws SQLException;
    boolean deleteUser(String userId) throws SQLException;
}

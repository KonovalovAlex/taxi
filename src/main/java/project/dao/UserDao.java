package project.dao;

import project.entity.User;

public interface UserDao extends Dao<User> {
    int insert(User user);
}

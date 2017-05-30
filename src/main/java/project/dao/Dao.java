package project.dao;

import project.entity.AbstractEntity;

import java.sql.Connection;

public interface Dao<T extends AbstractEntity> {
    void setConn(Connection connection);

    T get(String sql);

    int insertClient(T entity);

    boolean updateEntity();

    boolean deleteEntity();

}

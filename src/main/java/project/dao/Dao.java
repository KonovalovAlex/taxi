package project.dao;

import project.entity.AbstractEntity;

import java.sql.Connection;

public interface Dao<T extends AbstractEntity> {
    void setConn(Connection connection);

    void findById(int ID);

    void insert();

    void updateEntity();

    void deleteEntity();
}

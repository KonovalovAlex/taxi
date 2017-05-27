package project.dao.postgres;

import project.dao.Dao;
import project.entity.AbstractEntity;

import java.sql.Connection;

public abstract class AbstractPostgresDao<T extends AbstractEntity> {
    public void setConn(Connection connection) {
    }
    public void findById(int ID) {
    }
    public void insert() {
    }
    public void updateEntity() {
    }
    public void deleteEntity() {
    }
}

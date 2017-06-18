package project.dao;

import project.entity.AbstractEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public interface Dao<T extends AbstractEntity> {
    void setConn(Connection connection);

    ResultSet get(String tableName, LinkedHashMap<String, Object> conditions);

    Integer insert(String tableName, Object... params);

    boolean updateEntity(String tableName, LinkedHashMap<String, Object> params, LinkedHashMap<String, Object> conditions);

    boolean deleteEntity(String tableName, LinkedHashMap<String, Object> conditions);

}

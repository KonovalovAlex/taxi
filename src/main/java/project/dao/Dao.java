package project.dao;

import project.entity.AbstractEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public interface Dao<T extends AbstractEntity> {
    void setConn(Connection connection);

    ResultSet get(String tableName, Map<String, Object> conditions);

    Integer insert(String tableName, Object... params);

    boolean updateEntity(String tableName, Map<String, Object> params, Map<String, Object> conditions);

    boolean deleteEntity(String tableName, Map<String, Object> conditions);

}

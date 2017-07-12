package project.dao;

import project.entity.AbstractEntity;


import java.sql.SQLException;

import java.util.Map;

public interface Dao<T extends AbstractEntity> {

    Integer insert(String tableName, Object... params) throws SQLException;

    boolean updateEntity(String tableName, Map<String, Object> params, Map<String, Object> conditions) throws SQLException;

    boolean deleteEntity(String tableName, Map<String, Object> conditions) throws SQLException;

}

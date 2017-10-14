package project.dao.postgres;


import org.apache.log4j.Logger;

import project.entity.AbstractEntity;

import java.sql.*;
import java.util.*;

import static project.constants.Constants.*;

public abstract class AbstractPostgresDao<T extends AbstractEntity> {
    private static final Logger LOGGER = Logger.getLogger(AbstractPostgresDao.class.getName());
    private static final String INSERT = "INSERT INTO %s VALUES %s";
    private static final String UPDATE = "UPDATE %s SET %s WHERE %s";
    private static final String DELETE = "UPDATE %s SET DELETED = ? WHERE ID = ?";
    private Connection connection = null;

    protected HashMap<String, Object> conditions;
    protected HashMap<String, Object> params;

    protected Map<String, Object> getConditions() {
        return conditions = new HashMap<>();
    }

    protected Map<String, Object> getParams() {
        return params = new HashMap<>();
    }

    public AbstractPostgresDao(Connection connection) {
        this.connection = connection;
    }

    //dynamic insert
    public Integer insert(String tableName, Object... params) throws SQLException {
        String queryString = String.format(INSERT, tableName, this.generateValuesCount(params));
        int id = 0;
        try (PreparedStatement preparedStatement =
                     fillFromArgumentsPreparedStatement(connection.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS), params)) {
            preparedStatement.executeUpdate();
            ResultSet setId = preparedStatement.getGeneratedKeys();
            if (setId.next()) {
                id = setId.getInt(ID);
            }
        } catch (SQLException e) {
            LOGGER.error("insert method failed", e);

        }
        return id;
    }

    //dynamic update
    public boolean updateEntity(String tableName, Map<String, Object> params, Map<String, Object> conditions) throws SQLException {
        String queryString = String.format(UPDATE, tableName, this.generateUpdateParamsPattern(params), this.generateConditions(conditions));
        Map<String, Object> combinedMap = new HashMap<>();
        combinedMap.putAll(params);
        combinedMap.putAll(conditions);
        try (PreparedStatement preparedStatement = fillFromMapPreparedStatement(connection.prepareStatement(queryString), combinedMap)) {
            if (0 < preparedStatement.executeUpdate()) return true;//will return quantity of updated rows
            else {
                LOGGER.error("updateEntity returned nothing");
                return false;
            }
        }
    }

    //dynamic delete
    public boolean deleteEntity(String tableName, Map<String, Object> conditions) {
        String queryString = String.format(DELETE, tableName, this.generateConditions(conditions));
        try (PreparedStatement preparedStatement = fillFromArgumentsPreparedStatement(connection.prepareStatement(queryString), conditions)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error("method failed", e);
            return false;
        }
        return true;
    }

    private PreparedStatement fillFromMapPreparedStatement(PreparedStatement preparedStatement, Map<String, Object> conditions) throws SQLException {
        return this.fillFromArgumentsPreparedStatement(preparedStatement, conditions.values().toArray());
    }

    private PreparedStatement fillFromArgumentsPreparedStatement
            (PreparedStatement preparedStatement, Object... conditions) throws SQLException {
        int index = 1;
//insert data instead of "?"
        for (Object value : conditions) {
            if (value == null) {
                preparedStatement.setNull(index++, 0);
            } else if (value instanceof String) {
                preparedStatement.setString(index++, (String) value);
            } else if (value instanceof Integer) {
                preparedStatement.setInt(index++, (Integer) value);
            }
        }
        return preparedStatement;
    }

    private String generateValuesCount(Object... params) {
        StringBuilder sb = new StringBuilder("( ");
        for (int i = 0; i < params.length; i++) {
            if (i < params.length - 1) {
                sb.append("?, ");
            } else {
                sb.append("? ");
            }
        }
        sb.append(")");
        return sb.toString();
    }

    private String generateConditions(Map<String, Object> conditions) {
        StringBuilder sb = new StringBuilder("");
        Object[] conditionsArray = conditions.keySet().toArray();
        for (int i = 0; i < conditionsArray.length; i++) {
            sb.append(conditionsArray[i]);
            if (i < conditionsArray.length - 1) {
                sb.append(" = ?, ");
            } else {
                sb.append(" = ? ");
            }
        }
        return sb.toString();
    }

    private String generateUpdateParamsPattern(Map<String, Object> conditions) {
        StringBuilder sb = new StringBuilder("");
        for (String s : conditions.keySet()) {
            sb.append(s);
            sb.append(" = ? ");
        }
        return sb.toString();
    }
}

package project.dao.postgres;

import com.sun.org.apache.regexp.internal.RE;
import org.apache.log4j.Logger;
import project.dao.managerDao.ManagerDao;
import project.entity.AbstractEntity;
import project.entity.User;

import java.sql.*;
import java.util.*;

import static project.constants.Constants.*;

public abstract class AbstractPostgresDao<T extends AbstractEntity> {
    private static final Logger LOGGER = Logger.getLogger(AbstractPostgresDao.class.getName());
    private static final String INSERT = "INSERT INTO %s VALUES %s";
    private static final String UPDATE = "UPDATE %s SET %s WHERE %s";
    private static final String DELETE = "UPDATE %s SET DELETED = ? WHERE ID = ?";
    private Connection connection = null;
    LinkedHashMap<String, Object> conditions;
    LinkedHashMap<String, Object> params;

    public LinkedHashMap<String, Object> getConditions() {
        return conditions = new LinkedHashMap<>();
    }

    public LinkedHashMap<String, Object> getParams() {
        return params = new LinkedHashMap<>();
    }

    AbstractPostgresDao(Connection connection) {
        this.connection = connection;
    }

    public AbstractPostgresDao() {
    }

    //dynamic insert
    public Integer insert(String tableName, Object... params) {
        String queryString = String.format(INSERT, tableName, this.generateValuesCount(params));
        int id = 0;
        try (PreparedStatement preparedStatement =
                     fillFromArgumentsPreparedStatement(connection.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS), params)) {
            preparedStatement.executeQuery();
            ResultSet setId = preparedStatement.getGeneratedKeys();
            if (setId.next()) {
                id = setId.getInt(ID);
            }
        } catch (SQLException e) {
            LOGGER.error("insert method failed", e);
            e.printStackTrace();
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
            if (preparedStatement.executeUpdate() == 1) return true;
            else {
                LOGGER.error("updateEntity return nothing");
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

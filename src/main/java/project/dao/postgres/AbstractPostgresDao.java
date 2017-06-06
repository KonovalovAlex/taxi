package project.dao.postgres;

import project.entity.AbstractEntity;
import project.entity.User;

import java.sql.*;
import java.util.LinkedHashMap;

public abstract class AbstractPostgresDao<T extends AbstractEntity> {
    private static final String INSERT = "INSERT INTO %s VALUES %s";
    private static final String SELECT = "SELECT FROM %s %s";
    private static final String UPDATE = "UPDATE %s SET %s WHERE %s";
    private static final String DELETE = "UPDATE %s SET DELETED = ? WHERE ID = ?";


    private Connection connection = null;

    AbstractPostgresDao(Connection connection) {
        this.connection = connection;
    }

    public AbstractPostgresDao() {

    }

    public void setConn(Connection connection) {
    }

    public ResultSet get(String tableName, LinkedHashMap<String, Object> conditions) {
        String queryString = String.format(SELECT, tableName, this.generateConditions(conditions));

        try (PreparedStatement preparedStatement = fillFromMapPreparedStatement(connection.prepareStatement(queryString), conditions)) {
           return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Integer insert(String tableName, Object... params) {
        String queryString = String.format(INSERT, tableName, this.generateValuesCount(params));
        int id = 0;
        try (PreparedStatement preparedStatement =
                     fillFromArgumentsPreparedStatement(connection.prepareStatement(queryString,Statement.RETURN_GENERATED_KEYS), params)) {
            preparedStatement.execute();
            ResultSet setId = preparedStatement.getGeneratedKeys();
            if (setId.next()) {
                id = setId.getInt(7);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    private PreparedStatement fillFromArgumentsPreparedStatement
            (PreparedStatement preparedStatement, Object... conditions) throws SQLException {
        int index = 1;
//insert data instead of "?"
        for (Object value : conditions) {
            if (value == null) {
                preparedStatement.setNull(index++,0);
            } else if (value instanceof String) {
                preparedStatement.setString(index++, (String) value);
            } else if (value instanceof Integer) {
                preparedStatement.setInt(index++, (Integer) value);
            }
        }
        return preparedStatement;
    }

    public Boolean updateEntity(String tableName, LinkedHashMap<String, Object> params, LinkedHashMap<String, Object> conditions) {
        String queryString = String.format(UPDATE, tableName, this.generateUpdateParamsPattern(params), this.generateConditions(conditions));
        LinkedHashMap<String, Object> combinedMap = new LinkedHashMap<>();
        combinedMap.putAll(params);
        combinedMap.putAll(conditions);
        try (PreparedStatement preparedStatement = fillFromArgumentsPreparedStatement(connection.prepareStatement(queryString), combinedMap)) {
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Boolean deleteEntity(String tableName, LinkedHashMap<String, Object> conditions) {
        String queryString = String.format(DELETE, tableName, this.generateConditions(conditions));
        try (PreparedStatement preparedStatement = fillFromArgumentsPreparedStatement(connection.prepareStatement(queryString), conditions)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    private PreparedStatement fillFromMapPreparedStatement(PreparedStatement preparedStatement, LinkedHashMap<String, Object> conditions) throws SQLException {
        return this.fillFromArgumentsPreparedStatement(preparedStatement, conditions.entrySet());
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

    private String generateConditions(LinkedHashMap<String, Object> conditions) {
        StringBuilder sb = new StringBuilder("");
        Object[] conditionsArray = conditions.keySet().toArray();
        for (int i = 0; i < conditionsArray.length; i++) {
            sb.append(conditionsArray[i]);
            if (i < conditionsArray.length) {
                sb.append(" = ?, ");
            } else {
                sb.append(" = ? ");
            }
        }
        return sb.toString();
    }

    private String generateUpdateParamsPattern(LinkedHashMap<String, Object> conditions) {
        StringBuilder sb = new StringBuilder("");
        for (String s : conditions.keySet()) {
            sb.append(s);
            sb.append(" = ?, ");
        }
        return sb.toString();
    }

    public User get(String sql) {
        return null;
    }
}

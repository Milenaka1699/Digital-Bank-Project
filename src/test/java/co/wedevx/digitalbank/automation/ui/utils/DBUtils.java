package co.wedevx.digitalbank.automation.ui.utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBUtils {
    // Database connection variables
    private static Connection connection = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;

    // Method to establish connection with DB
    public static void establishConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    ConfigReader.getPropertiesValue("digitalbank.db.url"),
                    ConfigReader.getPropertiesValue("digitalbank.db.username"),
                    ConfigReader.getPropertiesValue("digitalbank.db.password")
            );

            if (connection == null) {
                throw new SQLException("Failed to establish database connection.");
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Database connection error: " + e.getMessage(), e);
        }
    }

    // Ensure connection is established before running queries
    public static void ensureConnection() {
        if (connection == null) {
            establishConnection();
        }
    }

    // Method to execute SELECT queries and return result as a List of Maps
    public static List<Map<String, Object>> runSQLSelectQuery(String sqlQuery) {
        ensureConnection(); // Ensure connection is established

        List<Map<String, Object>> dbResultList = new ArrayList<>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);

            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();

            while (resultSet.next()) {
                Map<String, Object> rowMap = new HashMap<>();
                for (int col = 1; col <= columnCount; col++) {
                    rowMap.put(resultSetMetaData.getColumnName(col), resultSet.getObject(col));
                }
                dbResultList.add(rowMap);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error executing SELECT query: " + e.getMessage(), e);
        }
        return dbResultList;
    }

    // Method to execute INSERT, UPDATE, or DELETE queries
    public static int runSQLUpdateQuery(String sqlQuery) {
        ensureConnection(); // Ensure connection is established

        int rowsAffected = 0;
        try {
            statement = connection.createStatement();
            rowsAffected = statement.executeUpdate(sqlQuery);
        } catch (SQLException e) {
            throw new RuntimeException("Error executing UPDATE query: " + e.getMessage(), e);
        }
        return rowsAffected;
    }

    // Close database connection
    public static void closeConnection() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
                connection = null; // Set connection to null to prevent reuse after closing
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error closing database connection: " + e.getMessage(), e);
        }
    }
}



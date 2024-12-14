package com.example.cookify.DataSrc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
    private static Connection getConnection() throws SQLException {
        Connection conn = null;
        try {
            DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
            String dbURL = "jdbc:sqlserver://spotify-server.database.windows.net;encrypt=true;trustServerCertificate=true;databaseName=cookify_Databse";
            String user = "asa_pos";
            String pass = "68vBq@9wZvSMhRV";

            conn= DriverManager.getConnection(dbURL, user, pass);
            System.out.println("Connection established successfully");
        } catch (SQLException err) {
            System.out.println("An error occurred while connecting to the database");
        }
        return conn;
    }

    public static int executeUpdate(String query, Object... params) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            setParameters(statement, params);
            return statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("An error occurred while executing SQL statement" + query);
        }
        return -1;
    }

    public static <T> T executeQuery(String query, ResultSetHandler<T> handler, Object... params) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            setParameters(statement, params);
            try (ResultSet resultSet = statement.executeQuery()) {
                return handler.handle(resultSet);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("An error occurred while executing SQL statement" + query);
        }

        return null;
    }

    private static void setParameters(PreparedStatement statement, Object... params) {
        try {
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }
    public interface ResultSetHandler<T>  {
        T handle(ResultSet resultSet) throws SQLException;
    }
}
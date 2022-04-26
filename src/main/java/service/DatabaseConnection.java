package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static DatabaseConnection dbConnection;
    Connection connection;

    private DatabaseConnection() {
        try {
            String URL = "jdbc:mysql://localhost:3306/airport";
            String username = "root";
            String password = "qwerty33";
            this.connection = DriverManager.getConnection(URL, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static DatabaseConnection getDbConnection() {
        if (dbConnection == null) {
            dbConnection = new DatabaseConnection();
        }
        return dbConnection;
    }

    public Connection getConnection() {
        return connection;
    }
}




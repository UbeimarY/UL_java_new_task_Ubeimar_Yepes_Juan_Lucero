package com.ucc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    
    private static final String URL = "jdbc:mysql://localhost:3306/sakila?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "contrasenaroot";
    
    private static Connection connection;

    public static Connection getInstanceConnection() throws SQLException {
        if (connection == null || connection.isClosed())
            connection = DriverManager.getConnection(URL, USER, PASS);
        return connection;
    }
}

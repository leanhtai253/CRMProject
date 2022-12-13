package com.crmproject.config;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlConfig {
    public static Connection getConnection() {
        Connection connection = null;
        try {
            String dbDriver = "com.mysql.cj.jdbc.Driver";
            String dbUrl = "jdbc:mysql://localhost:3307/";
            String dbName = "crm";
            String dbUsername = "root";
            String dbPassword = "crmMysqlCybersoftJava19";
            Class.forName(dbDriver);
            connection = DriverManager.getConnection(dbUrl+dbName, dbUsername, dbPassword);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}

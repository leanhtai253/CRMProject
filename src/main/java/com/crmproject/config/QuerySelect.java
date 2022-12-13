package com.crmproject.config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuerySelect {
    public static ResultSet selectQuery(String query) throws SQLException {
        Connection connection = MysqlConfig.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery(query);

        preparedStatement.close();
        connection.close();

        return resultSet;
    }
}

package com.crmproject.repository;

import com.crmproject.config.MysqlConfig;
import com.crmproject.model.StatusModel;
import com.crmproject.model.TaskModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatusRepositoryImp implements StatusRepository{
    @Override
    public List<StatusModel> getAllStatus() throws SQLException {
        List<StatusModel> list = new ArrayList<>();
        String query = String.format("SELECT * FROM Status;");
        Connection connection = MysqlConfig.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery(query);

        while (resultSet.next()) {
            StatusModel status = fillInStatusInfo(resultSet);
            list.add(status);
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();

        return list;
    }

    private StatusModel fillInStatusInfo(ResultSet resultSet) throws SQLException {
        StatusModel status = new StatusModel();
        status.setStatusID(resultSet.getInt("statusID"));
        status.setName(resultSet.getString("name"));
        return status;
    }
}

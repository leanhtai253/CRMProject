package com.crmproject.repository;

import com.crmproject.config.MysqlConfig;
import com.crmproject.model.RoleModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleRepositoryImp implements RoleRepository{
    @Override
    public List<RoleModel> getAllRoles() throws SQLException {
        List<RoleModel> list = new ArrayList<>();
        String query = String.format("SELECT * FROM Role;");
        Connection connection = MysqlConfig.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery(query);

        while (resultSet.next()) {
            RoleModel role = fillInRoleInfo(resultSet);
            list.add(role);
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();

        return list;
    }

    @Override
    public boolean addRole(RoleModel role) throws SQLException {
        try {
            String query = String.format("insert into Role (name, descr)\n" +
                    "values ('%s','%s');", role.getName(), role.getDescr());
            Connection connection = MysqlConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            int result = preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error add role " + e);
            return false;
        }
    }

    private RoleModel fillInRoleInfo(ResultSet resultSet) throws SQLException {
        RoleModel role = new RoleModel();
        role.setRoleID(resultSet.getInt("roleID"));
        role.setName(resultSet.getString("name"));
        role.setDescr(resultSet.getString("descr"));
        return role;
    }
}

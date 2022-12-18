package com.crmproject.repository;

import com.crmproject.config.MysqlConfig;
import com.crmproject.config.QuerySelect;
import com.crmproject.model.*;
import com.crmproject.model.inheritance.SummaryByStatus;
import com.crmproject.services.TaskService;
import com.crmproject.services.TaskServiceImp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImp implements UserRepository{

    private TaskService taskService = new TaskServiceImp();

    @Override
    public List<UserModel> findUserByEmailAndPassword(String email, String password) throws SQLException {
        List<UserModel> list = new ArrayList<>();

        String query = String.format("select * from User where email='%s' and pwd='%s';", email, password);

        Connection connection = MysqlConfig.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery(query);

        while (resultSet.next()) {
            UserModel userModel = fillInUserInfo(resultSet);
            list.add(userModel);
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();

        return list;
    }

    @Override
    public List<UserModel> findUserById(int id) throws SQLException {
        List<UserModel> list = new ArrayList<>();
        String query = String.format("select * from User where userID = %d;", id);
        Connection connection = MysqlConfig.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery(query);

        while (resultSet.next()) {
            UserModel userModel = fillInUserInfo(resultSet);
            list.add(userModel);
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();

        return list;
    }

    @Override
    public List<UserModel> findUserByIdWithPwd(int id) throws SQLException {
        List<UserModel> list = new ArrayList<>();
        String query = String.format("select * from User where userID = %d;", id);
        Connection connection = MysqlConfig.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery(query);

        while (resultSet.next()) {
            UserModel userModel = fillInUserInfo(resultSet);
            userModel = fillInUserInfoWithPwd(resultSet, userModel);
            list.add(userModel);
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();

        return list;
    }

    @Override
    public List<TasksByStatus> countTasksByStatus(int id) throws SQLException {
        String query = String.format("SELECT st.statusId as status_id, st.name as status, count \n" +
                "FROM Status st\n" +
                "LEFT JOIN (\n" +
                "\tSELECT t.status, COUNT(taskID) as count\n" +
                "\tFROM User u\n" +
                "\tJOIN Task t ON u.userID=t.member\n" +
                "\tWHERE u.userID=%d\n" +
                "\tGROUP BY t.status\n" +
                "\tORDER BY t.status\n" +
                ") as t ON st.statusId=t.status \n" +
                "ORDER BY st.statusId;", id);
        Connection connection = MysqlConfig.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery(query);
        List<TasksByStatus> list = new ArrayList<>();
        while (resultSet.next()) {
            TasksByStatus record = new TasksByStatus();
            int statusId = resultSet.getInt("status_id");
            String status = resultSet.getString("status");
            int count = resultSet.getInt("count");
            record.setStatusId(statusId);
            record.setStatus(status);
            record.setCount(count);

            list.add(record);
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return list;
    }

    @Override
    public List<Members> findAllMembers() throws SQLException {
        String query = String.format("select userID, u.roleID, lastName, firstName, \n" +
                "\t\tusername, r.name as role\n" +
                "from User u\n" +
                "join Role r on u.roleID = r.roleID\n" +
                "order by u.roleID;");
        Connection connection = MysqlConfig.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery(query);
        List<Members> list = new ArrayList<>();
        while (resultSet.next()) {
            Members member = new Members();
            member.setUserID(resultSet.getInt("userID"));
            member.setRoleID(resultSet.getInt("roleID"));
            member.setLastName(resultSet.getString("lastName"));
            member.setFirstName(resultSet.getString("firstName"));
            member.setUsername(resultSet.getString("username"));
            member.setRole(resultSet.getString("role"));

            list.add(member);
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return list;
    }

//    @Override
//    public UserDetails findUserDetails(int userId) throws SQLException {
//        // Find basic user details
//        UserModel userModel = findUserById(userId).get(0);
//        UserDetails userDetails = new UserDetails();
//        userDetails.setUserID(userModel.getUserID());
//        userDetails.setUsername(userModel.getUsername());
//        userDetails.setEmail(userModel.getEmail());
//        userDetails.setLastName(userModel.getLastName());
//        userDetails.setFirstName(userModel.getFirstName());
//        userDetails.setAvatar(userModel.getAvatar());
//        userDetails.setBgImg(userModel.getBgImg());
//        // Find tasks summary (tasks count per status)
//        List<TasksByStatus> tasksByStatus = countTasksByStatus(userId);
//        // Find all tasks for each status
//        for (int i = 0; i < tasksByStatus.size(); i++) {
//            System.out.println("Kiem tra tasksByStatus");
//            TasksByStatus el = tasksByStatus.get(i);
//            List<TaskModel> taskModelList = taskService.findTasksByUserAndStatus(userId,el.getStatusId());
////            el.setTasks(taskModelList);
//            tasksByStatus.set(i, el);
//        }
//        userDetails.setTaskLists(tasksByStatus);
//
//        return userDetails;
//    }

    @Override
    public List<CountryModel> getCountries() throws SQLException {
        String query = String.format("select name from countries;");
        Connection connection = MysqlConfig.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery(query);
        List<CountryModel> list = new ArrayList<>();
        while (resultSet.next()) {
            CountryModel country = new CountryModel();
            country.setName(resultSet.getString("name"));

            list.add(country);
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return list;
    }

    @Override
    public boolean addUser(UserModel userModel) throws SQLException {
        try {
            String query = String.format("INSERT INTO User (lastName, firstName, username, email, roleID, pwd, avatar, bgImg, phone, country)\n" +
                            "VALUES ('%s', '%s', '' ,'%s', 3, '%s', 'plugins/images/users/default.png',\n" +
                            "'plugins/images/large/img1.jpg', '%s', '%s');",
                    userModel.getLastName(), userModel.getFirstName(), userModel.getEmail(),
                    userModel.getPwd(), userModel.getPhone(), userModel.getCountry());
            Connection connection = MysqlConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            int i = preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error add user " + e);
            return false;
        }

    }

    @Override
    public boolean modifyUser(UserModel userModel) {
        try {
            String query = String.format("update User\n" +
                            "set lastName='%s', firstName='%s', username='%s',\n" +
                            "email='%s',phone='%s',country='%s'\n" +
                            "where userID=%d",
                    userModel.getLastName(), userModel.getFirstName(),
                    userModel.getUsername(),userModel.getEmail(),
                    userModel.getPhone(), userModel.getCountry(), userModel.getUserID());
            Connection connection = MysqlConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            int i = preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error modify user " + e);
            return false;
        }
    }

    private UserModel fillInUserInfo(ResultSet resultSet) throws SQLException {
        UserModel userModel = new UserModel();
        userModel.setUserID(resultSet.getInt("userID"));
        userModel.setUsername(resultSet.getString("username"));
        userModel.setEmail(resultSet.getString("email"));
//        userModel.setPwd(resultSet.getString("pwd"));
        userModel.setLastName(resultSet.getString("lastName"));
        userModel.setFirstName(resultSet.getString("firstName"));
        userModel.setRoleID(resultSet.getInt("roleID"));
        userModel.setAvatar(resultSet.getString("avatar"));
        userModel.setBgImg(resultSet.getString("bgImg"));
        userModel.setPhone(resultSet.getString("phone"));
        userModel.setCountry(resultSet.getString("country"));

        return userModel;
    }
    private UserModel fillInUserInfoWithPwd(ResultSet resultSet, UserModel userModel) throws SQLException {
        userModel.setPwd(resultSet.getString("pwd"));
        return userModel;
    }
}

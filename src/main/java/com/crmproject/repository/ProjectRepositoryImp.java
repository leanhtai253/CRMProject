package com.crmproject.repository;

import com.crmproject.config.MysqlConfig;
import com.crmproject.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectRepositoryImp implements ProjectRepository{
    @Override
    public List<ProjectModel> getAllProjects() throws SQLException {
        List<ProjectModel> list = new ArrayList<>();
        String query = String.format("SELECT * FROM Project;");
        Connection connection = MysqlConfig.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery(query);

        while (resultSet.next()) {
            ProjectModel project = fillInProjectInfo(resultSet);
            list.add(project);
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();

        return list;
    }

    @Override
    public List<TasksByStatus> countTasksByStatus(int id) throws SQLException {
        String query = String.format("\n" +
                "SELECT st.statusId as status_id, st.name as status, count\n" +
                "FROM Status st\n" +
                "LEFT JOIN (\n" +
                "\tSELECT t.status, COUNT(taskID) as count\n" +
                "\tFROM Project p\n" +
                "\tJOIN Task t ON p.projectID=t.projectID\n" +
                "\tWHERE p.projectID=%d\n" +
                "\tGROUP BY t.status\n" +
                "\tORDER BY t.status\n" +
                ") as t ON st.statusId=t.status\n" +
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
    public List<TaskComplete> getMembersTasksByStatus(int uid, int pid, int stid) throws SQLException {
        String query = String.format("select taskID, t.name as task, p.name as project,\n" +
                "\t\tstart_date as startD, end_date as endD,\n" +
                "        st.name as status\n" +
                "from Task t\n" +
                "join User u on u.userID=t.member\n" +
                "join Status st on t.status=st.statusID\n" +
                "join Project p on t.projectID=p.projectID\n" +
                "where st.statusID=%d and t.member=%d and p.projectID=%d;\n",
                stid,uid,pid);
        Connection connection = MysqlConfig.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery(query);
        List<TaskComplete> list = new ArrayList<>();
        while (resultSet.next()) {
            TaskComplete record = new TaskComplete();
            record.setTaskID(resultSet.getInt("taskID"));
            record.setTask(resultSet.getString("task"));
            record.setProject(resultSet.getString("project"));
            record.setStartD(resultSet.getString("startD"));
            record.setEndD(resultSet.getString("endD"));
            record.setStatus(resultSet.getString("status"));

            list.add(record);
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return list;
    }

    @Override
    public List<UserModel> getMembersOfProject(int pid) throws SQLException {
        String query = String.format("select distinct u.userID, u.firstName,\n" +
                "\t\t\tu.lastName, u.avatar\n" +
                "from Task t\n" +
                "join Project p on t.projectID=p.projectID\n" +
                "join User u on u.userID=t.member\n" +
                "where p.projectID=%d\n" +
                "order by u.userID;", pid);
        Connection connection = MysqlConfig.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery(query);
        List<UserModel> list = new ArrayList<>();
        while (resultSet.next()) {
            UserModel record = new UserModel();
            record.setUserID(resultSet.getInt("userID"));
            record.setFirstName(resultSet.getString("firstName"));
            record.setLastName(resultSet.getString("lastName"));
            record.setAvatar(resultSet.getString("avatar"));

            list.add(record);
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return list;
    }

    private ProjectModel fillInProjectInfo(ResultSet resultSet) throws SQLException {
        ProjectModel project = new ProjectModel();
        project.setProjectID(resultSet.getInt("projectID"));
        project.setName(resultSet.getString("name"));
        project.setStartD(resultSet.getString("startD"));
        project.setEndD(resultSet.getString("endD"));
        project.setLeader(resultSet.getInt("leader"));

        return project;
    }
}

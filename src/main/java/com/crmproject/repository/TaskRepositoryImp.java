package com.crmproject.repository;

import com.crmproject.config.MysqlConfig;
import com.crmproject.model.TaskComplete;
import com.crmproject.model.TaskModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskRepositoryImp implements TaskRepository{
    @Override
    public List<TaskModel> getTasksList(int id) throws SQLException {
        List<TaskModel> list = new ArrayList<>();
        String query = String.format("SELECT t.taskID as id, t.name as task, p.name as project, \n" +
                "start_date, end_date, st.name as status\n" +
                "FROM Task t\n" +
                "JOIN Project p ON t.projectID=p.projectID\n" +
                "JOIN Status st ON t.status=st.statusID\n" +
                "WHERE t.member=%d \n" +
                "ORDER BY t.taskID",id);
        Connection connection = MysqlConfig.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery(query);

        while (resultSet.next()) {
            TaskModel task = fillInTaskInfo(resultSet);
            list.add(task);
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();

        return list;
    }

    @Override
    public List<TaskModel> getTaskById(int id) throws SQLException {
        List<TaskModel> list = new ArrayList<>();
        String query = String.format("SELECT t.taskID as id, t.name as task, p.name as project, \n" +
                "start_date, end_date, st.name as status\n" +
                "FROM Task t\n" +
                "JOIN Project p ON t.projectID=p.projectID\n" +
                "JOIN Status st ON t.status=st.statusID\n" +
                "WHERE t.taskID=%d;", id);
        Connection connection = MysqlConfig.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery(query);

        while (resultSet.next()) {
            TaskModel task = fillInTaskInfo(resultSet);
            list.add(task);
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();

        return list;
    }

    @Override
    public TaskComplete getCompleteTaskById(int id) throws SQLException {
        List<TaskComplete> tasks = new ArrayList<>();
        try {
            String query = String.format("select taskID, t.name as task, p.name as project, \n" +
                    "\t\tconcat(u.firstName,space(1),u.lastName) as member,\n" +
                    "        t.start_date as startD, t.end_date as endD,\n" +
                    "        st.name as status\n" +
                    "from Task t\n" +
                    "JOIN Project p on t.projectID=p.projectID\n" +
                    "JOIN User u on t.member=u.userID\n" +
                    "JOIN Status st on st.statusID=t.status\n" +
                    "where t.taskID=%d;",id);
            Connection connection = MysqlConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery(query);
            while (resultSet.next()) {
                TaskComplete task = fillInTaskCompleteInfo(resultSet);
                tasks.add(task);
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
            return tasks.get(0);
        } catch (Exception e) {
            System.out.println("Error querying task complete by id " + e);
            return null;
        }

    }

    @Override
    public boolean updateTaskStatus(int taskID, int statusID) throws SQLException {
        List<TaskModel> list = new ArrayList<>();
        String query = String.format("UPDATE Task\n" +
                "SET status = %d\n" +
                "WHERE taskID = %d;", statusID, taskID);
        Connection connection = MysqlConfig.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        return (result > 0 ? true : false);
    }

    @Override
    public List<TaskModel> findTasksByUserAndStatus(int userId, int statusId) throws SQLException {
        List<TaskModel> list = new ArrayList<>();
        String query = String.format("select t.taskID as id, t.name as task,\n" +
                "\t\tp.name as project, start_date, end_date,\n" +
                "        st.name as status\n" +
                "from Task t\n" +
                "JOIN Project p ON t.projectID=p.projectID\n" +
                "JOIN Status st ON t.status=st.statusID\n" +
                "where member=%d and status=%d;", userId, statusId);
        Connection connection = MysqlConfig.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery(query);

        while (resultSet.next()) {
            TaskModel task = fillInTaskInfo(resultSet);
            list.add(task);
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return list;
    }

    @Override
    public List<TaskComplete> getCompleteTasksList() throws SQLException {
        List<TaskComplete> list = new ArrayList<>();
        String query = String.format("select taskID, t.name as task, p.name as project, \n" +
                "\t\tconcat(u.firstName,space(1),u.lastName) as member,\n" +
                "        t.start_date as startD, t.end_date as endD,\n" +
                "        st.name as status \n" +
                "from Task t\n" +
                "JOIN Project p on t.projectID=p.projectID\n" +
                "JOIN User u on t.member=u.userID\n" +
                "JOIN Status st on st.statusID=t.status\n" +
                "order by t.taskID;");
        Connection connection = MysqlConfig.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery(query);

        while (resultSet.next()) {
            TaskComplete task = fillInTaskCompleteInfo(resultSet);
            list.add(task);
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();

        return list;
    }

    @Override
    public boolean addTask(TaskComplete task) throws SQLException {
        try {
            String query = String.format("insert into Task (projectID,member,name,start_date,end_date,status)\n" +
                            "values (%d,%d,'%s','%s','%s',1);\n",
                    Integer.parseInt(task.getProject()),
                    Integer.parseInt(task.getMember()),
                    task.getTask(),
                    task.getStartD(), task.getEndD());
            Connection connection = MysqlConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            int result = preparedStatement.executeUpdate();
            connection.close();
            preparedStatement.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error add task " + e);
            return false;
        }
    }

    @Override
    public boolean updateTask(TaskModel task) throws SQLException {
        try {
            String query = String.format("update Task\n" +
                            "set name='%s', start_date='%s', end_date='%s', status=%d\n" +
                            "where taskID=%d;",
                    task.getTask(), task.getStart_date(), task.getEnd_date(),
                    Integer.parseInt(task.getStatus()), task.getId());
            Connection connection = MysqlConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            int result = preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error updating task " + e);
            return false;
        }
    }

    private TaskModel fillInTaskInfo(ResultSet resultSet) throws SQLException {
        TaskModel task = new TaskModel();
        task.setId(resultSet.getInt("id"));
        task.setTask(resultSet.getString("task"));
        task.setProject(resultSet.getString("project"));
        task.setStart_date(resultSet.getString("start_date"));
        task.setEnd_date(resultSet.getString("end_date"));
        task.setStatus(resultSet.getString("status"));
        return task;
    }

    private TaskComplete fillInTaskCompleteInfo(ResultSet resultSet) throws SQLException {
        TaskComplete task = new TaskComplete();
        task.setTaskID(resultSet.getInt("taskID"));
        task.setTask(resultSet.getString("task"));
        task.setProject(resultSet.getString("project"));
        task.setMember(resultSet.getString("member"));
        task.setStartD(resultSet.getString("startD"));
        task.setEndD(resultSet.getString("endD"));
        task.setStatus(resultSet.getString("status"));
        return task;
    }
}

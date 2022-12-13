package com.crmproject.services;

import com.crmproject.model.TaskComplete;
import com.crmproject.model.TaskModel;

import java.sql.SQLException;
import java.util.List;

public interface TaskService {
    public List<TaskModel> getTasksList(int id) throws SQLException;
    public List<TaskModel> getTaskById(int id) throws SQLException;
    public boolean updateTaskStatus(int taskID, int statusID) throws SQLException;
    public List<TaskModel> findTasksByUserAndStatus(int userId, int statusId) throws SQLException;
    public List<TaskComplete> getCompleteTasksList() throws SQLException;
}

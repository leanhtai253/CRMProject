package com.crmproject.services;

import com.crmproject.model.TaskComplete;
import com.crmproject.model.TaskModel;
import com.crmproject.repository.TaskRepository;
import com.crmproject.repository.TaskRepositoryImp;

import java.sql.SQLException;
import java.util.List;

public class TaskServiceImp implements TaskService{
    TaskRepository taskRepository = new TaskRepositoryImp();

    @Override
    public List<TaskModel> getTasksList(int id) throws SQLException {
        return taskRepository.getTasksList(id);
    }

    @Override
    public List<TaskModel> getTaskById(int id) throws SQLException {
        return taskRepository.getTaskById(id);
    }

    @Override
    public boolean updateTaskStatus(int taskID, int statusID) throws SQLException {
        return taskRepository.updateTaskStatus(taskID, statusID);
    }

    @Override
    public List<TaskModel> findTasksByUserAndStatus(int userId, int statusId) throws SQLException {
        return taskRepository.findTasksByUserAndStatus(userId, statusId);
    }

    @Override
    public List<TaskComplete> getCompleteTasksList() throws SQLException {
        return taskRepository.getCompleteTasksList();
    }

    @Override
    public boolean addTask(TaskComplete task) throws SQLException {
        return taskRepository.addTask(task);
    }
}

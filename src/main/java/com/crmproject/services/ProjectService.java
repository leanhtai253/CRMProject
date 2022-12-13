package com.crmproject.services;

import com.crmproject.model.ProjectModel;
import com.crmproject.model.TaskComplete;
import com.crmproject.model.TasksByStatus;
import com.crmproject.model.UserModel;

import java.sql.SQLException;
import java.util.List;

public interface ProjectService {
    public List<ProjectModel> getAllProjects() throws SQLException;
    public List<TasksByStatus> countTasksByStatus(int id) throws SQLException;
    public List<TaskComplete> getMembersTasksByStatus(int uid, int pid, int stid) throws SQLException;
    public List<UserModel> getMembersOfProject(int pid) throws SQLException;
}

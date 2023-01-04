package com.crmproject.repository;

import com.crmproject.model.ProjectModel;
import com.crmproject.model.TaskComplete;
import com.crmproject.model.TasksByStatus;
import com.crmproject.model.UserModel;

import java.sql.SQLException;
import java.util.List;

public interface ProjectRepository {
    public List<ProjectModel> getAllProjects() throws SQLException;
    public List<TasksByStatus> countTasksByStatus(int id) throws SQLException;
    public List<TaskComplete> getMembersTasksByStatus(int uid, int pid, int stid) throws SQLException;
    public List<UserModel> getMembersOfProject(int pid) throws SQLException;
    public boolean addProject(ProjectModel project);
    public ProjectModel getProjectById(int id);
    public boolean updateProject(int id, ProjectModel project);
    public boolean deleteProjectById(int id);
}

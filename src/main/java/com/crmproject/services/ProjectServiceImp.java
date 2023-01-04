package com.crmproject.services;

import com.crmproject.model.ProjectModel;
import com.crmproject.model.TaskComplete;
import com.crmproject.model.TasksByStatus;
import com.crmproject.model.UserModel;
import com.crmproject.repository.ProjectRepository;
import com.crmproject.repository.ProjectRepositoryImp;

import java.sql.SQLException;
import java.util.List;

public class ProjectServiceImp implements ProjectService{
    ProjectRepository projectRepository = new ProjectRepositoryImp();
    @Override
    public List<ProjectModel> getAllProjects() throws SQLException {
        return projectRepository.getAllProjects();
    }

    @Override
    public List<TasksByStatus> countTasksByStatus(int id) throws SQLException {
        return projectRepository.countTasksByStatus(id);
    }

    @Override
    public List<TaskComplete> getMembersTasksByStatus(int uid, int pid, int stid) throws SQLException {
        return projectRepository.getMembersTasksByStatus(uid,pid,stid);
    }

    @Override
    public List<UserModel> getMembersOfProject(int pid) throws SQLException {
        return projectRepository.getMembersOfProject(pid);
    }

    @Override
    public boolean addProject(ProjectModel project) {
        return projectRepository.addProject(project);
    }

    @Override
    public ProjectModel getProjectById(int id) {
        return projectRepository.getProjectById(id);
    }

    @Override
    public boolean updateProject(int id, ProjectModel project) {
        return projectRepository.updateProject(id, project);
    }

    @Override
    public boolean deleteProjectById(int id) {
        return projectRepository.deleteProjectById(id);
    }
}

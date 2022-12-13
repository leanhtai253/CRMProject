package com.crmproject.services;

import com.crmproject.model.Members;
import com.crmproject.model.TasksByStatus;
import com.crmproject.model.UserDetails;
import com.crmproject.model.UserModel;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    public List<UserModel> findUserByEmailAndPassword(String email, String password) throws SQLException;
    public List<UserModel> findUserById(int id) throws SQLException;
    public List<TasksByStatus> countTasksByStatus(int id) throws SQLException;
    public List<Members> findAllMembers() throws SQLException;
    public UserDetails findUserDetailsById(int id) throws SQLException;
}

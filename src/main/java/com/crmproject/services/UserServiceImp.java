package com.crmproject.services;

import com.crmproject.model.Members;
import com.crmproject.model.TasksByStatus;
import com.crmproject.model.UserDetails;
import com.crmproject.model.UserModel;
import com.crmproject.repository.UserRepository;
import com.crmproject.repository.UserRepositoryImp;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImp implements UserService{
    UserRepository userRepository = new UserRepositoryImp();
    @Override
    public List<UserModel> findUserByEmailAndPassword(String email, String password) throws SQLException {
        return userRepository.findUserByEmailAndPassword(email, password);
    }

    @Override
    public List<UserModel> findUserById(int id) throws SQLException {
        return userRepository.findUserById(id);
    }

    @Override
    public List<TasksByStatus> countTasksByStatus(int id) throws SQLException {
        return userRepository.countTasksByStatus(id);
    }

    @Override
    public List<Members> findAllMembers() throws SQLException {
        return userRepository.findAllMembers();
    }

    @Override
    public UserDetails findUserDetailsById(int id) throws SQLException {
        return userRepository.findUserDetails(id);
    }
}

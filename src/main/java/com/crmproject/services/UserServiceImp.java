package com.crmproject.services;

import com.crmproject.model.*;
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
    public List<UserModel> findUserByIdWithPwd(int id) throws SQLException {
        return userRepository.findUserByIdWithPwd(id);
    }

    @Override
    public List<TasksByStatus> countTasksByStatus(int id) throws SQLException {
        return userRepository.countTasksByStatus(id);
    }

    @Override
    public List<Members> findAllMembers() throws SQLException {
        return userRepository.findAllMembers();
    }

//    @Override
//    public UserDetails findUserDetailsById(int id) throws SQLException {
//        return userRepository.findUserDetails(id);
//    }

    @Override
    public List<CountryModel> getCountries() throws SQLException {
        return userRepository.getCountries();
    }

    @Override
    public boolean addUser(UserModel user) throws SQLException {
        return userRepository.addUser(user);
    }

    @Override
    public boolean modifyUser(UserModel userModel) {
        return userRepository.modifyUser(userModel);
    }

    @Override
    public boolean deleteUserById(int id) {
        return userRepository.deleteUserById(id);
    }
}

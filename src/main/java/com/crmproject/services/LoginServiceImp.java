package com.crmproject.services;

import com.crmproject.model.UserModel;
import com.crmproject.repository.UserRepository;
import com.crmproject.repository.UserRepositoryImp;

import java.sql.SQLException;
import java.util.List;

public class LoginServiceImp implements LoginService{
    private UserRepository userRepository = new UserRepositoryImp();
    @Override
    public boolean checkLoginByEmailAndPassword(String email, String password) throws SQLException {
        List<UserModel> list = userRepository.findUserByEmailAndPassword(email, password);
        return (list.size() == 0 ? false : true);
    }

    @Override
    public List<UserModel> findUserByEmailAndPassword(String email, String password) throws SQLException {
        return userRepository.findUserByEmailAndPassword(email, password);
    }
}

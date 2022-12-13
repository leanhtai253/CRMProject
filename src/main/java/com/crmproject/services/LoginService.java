package com.crmproject.services;

import com.crmproject.model.UserModel;

import java.sql.SQLException;
import java.util.List;

public interface LoginService {
    public boolean checkLoginByEmailAndPassword(String email, String password) throws SQLException;
    public List<UserModel> findUserByEmailAndPassword(String email, String password) throws SQLException;
}

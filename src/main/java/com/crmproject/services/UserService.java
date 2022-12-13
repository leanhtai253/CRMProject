package com.crmproject.services;

import com.crmproject.model.*;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    public List<UserModel> findUserByEmailAndPassword(String email, String password) throws SQLException;
    public List<UserModel> findUserById(int id) throws SQLException;
    public List<TasksByStatus> countTasksByStatus(int id) throws SQLException;
    public List<Members> findAllMembers() throws SQLException;
    public UserDetails findUserDetailsById(int id) throws SQLException;
    public List<CountryModel> getCountries() throws SQLException;
    public boolean addUser(UserModel user) throws SQLException;
}

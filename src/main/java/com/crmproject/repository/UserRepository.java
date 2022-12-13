package com.crmproject.repository;

import com.crmproject.model.*;

import java.sql.SQLException;
import java.util.List;

public interface UserRepository {
    public List<UserModel> findUserByEmailAndPassword(String email, String password) throws SQLException;
    public List<UserModel> findUserById(int id) throws SQLException;
    public List<TasksByStatus> countTasksByStatus(int id) throws SQLException;
    public List<Members> findAllMembers() throws SQLException;
    public UserDetails findUserDetails(int userId) throws SQLException;
    public List<CountryModel> getCountries() throws SQLException;
    public boolean addUser(UserModel userModel) throws SQLException;
}

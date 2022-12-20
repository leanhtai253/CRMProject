package com.crmproject.repository;

import com.crmproject.model.RoleModel;

import java.sql.SQLException;
import java.util.List;

public interface RoleRepository {
    public List<RoleModel> getAllRoles() throws SQLException;
    public boolean addRole(RoleModel role) throws SQLException;
    public RoleModel findRoleById(int id);
    public boolean updateRole(int id, RoleModel role);
    public boolean deleteRoleById(int id);
}

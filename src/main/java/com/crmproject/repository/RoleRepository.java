package com.crmproject.repository;

import com.crmproject.model.RoleModel;

import java.sql.SQLException;
import java.util.List;

public interface RoleRepository {
    public List<RoleModel> getAllRoles() throws SQLException;
}

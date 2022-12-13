package com.crmproject.services;

import com.crmproject.model.RoleModel;

import java.sql.SQLException;
import java.util.List;

public interface RoleService {
    public List<RoleModel> getAllRoles() throws SQLException;
}

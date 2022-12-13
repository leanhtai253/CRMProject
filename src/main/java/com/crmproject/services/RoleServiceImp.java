package com.crmproject.services;

import com.crmproject.model.RoleModel;
import com.crmproject.repository.RoleRepository;
import com.crmproject.repository.RoleRepositoryImp;

import java.sql.SQLException;
import java.util.List;

public class RoleServiceImp implements RoleService{
    RoleRepository roleRepository = new RoleRepositoryImp();
    @Override
    public List<RoleModel> getAllRoles() throws SQLException {
        return roleRepository.getAllRoles();
    }
}

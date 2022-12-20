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

    @Override
    public RoleModel findRoleById(int id) {
        return roleRepository.findRoleById(id);
    }

    @Override
    public boolean addRole(RoleModel role) throws SQLException {
        return roleRepository.addRole(role);
    }

    @Override
    public boolean updateRole(int id, RoleModel role) {
        return roleRepository.updateRole(id, role);
    }

    @Override
    public boolean deleteRoleById(int id) {
        return roleRepository.deleteRoleById(id);
    }
}

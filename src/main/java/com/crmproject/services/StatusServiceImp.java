package com.crmproject.services;

import com.crmproject.model.StatusModel;
import com.crmproject.repository.StatusRepository;
import com.crmproject.repository.StatusRepositoryImp;

import java.sql.SQLException;
import java.util.List;

public class StatusServiceImp implements StatusService{
    StatusRepository statusRepository = new StatusRepositoryImp();
    @Override
    public List<StatusModel> getAllStatus() throws SQLException {
        return statusRepository.getAllStatus();
    }
}

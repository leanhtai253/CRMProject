package com.crmproject.services;

import com.crmproject.model.StatusModel;

import java.sql.SQLException;
import java.util.List;

public interface StatusService {
    public List<StatusModel> getAllStatus() throws SQLException;
}

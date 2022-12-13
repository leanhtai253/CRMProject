package com.crmproject.repository;

import com.crmproject.model.StatusModel;

import java.sql.SQLException;
import java.util.List;

public interface StatusRepository {
    public List<StatusModel> getAllStatus() throws SQLException;
}

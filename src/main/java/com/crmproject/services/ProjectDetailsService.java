package com.crmproject.services;

import com.crmproject.model.MembersTasksByStatus;

import java.sql.SQLException;
import java.util.List;

public interface ProjectDetailsService {
    List<MembersTasksByStatus> getProjectMembersDetails(int pid) throws SQLException;
}

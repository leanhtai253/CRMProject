package com.crmproject.services;

import com.crmproject.model.MembersTasksByStatus;
import com.crmproject.model.StatusModel;
import com.crmproject.model.TaskComplete;
import com.crmproject.model.UserModel;
import com.crmproject.model.inheritance.SummaryByStatus;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectDetailsServiceImp implements ProjectDetailsService{
    @Override
    public List<MembersTasksByStatus> getProjectMembersDetails(int pid) throws SQLException {
        ProjectService projectService = new ProjectServiceImp();
        StatusService statusService = new StatusServiceImp();
        List<UserModel> members = projectService.getMembersOfProject(pid);
        List<StatusModel> statuses = statusService.getAllStatus();
        List<MembersTasksByStatus> data = new ArrayList<>();
        for (UserModel member : members) {
            List<SummaryByStatus> summaryList = new ArrayList<>();
            MembersTasksByStatus record = new MembersTasksByStatus();
            record.setMember(member);

            int uid = member.getUserID();
            for (StatusModel status : statuses) {
                SummaryByStatus summary = new SummaryByStatus();
                summary.setStatusID(status.getStatusID());
                summary.setStatus(status.getName());

                int stid = status.getStatusID();
                List<TaskComplete> tasks = projectService.getMembersTasksByStatus(
                        uid, pid, stid
                );
                summary.setTasks(tasks);
                summaryList.add(summary);
            }
            record.setSummaryList(summaryList);
            data.add(record);
        }
        return data;
    }
}

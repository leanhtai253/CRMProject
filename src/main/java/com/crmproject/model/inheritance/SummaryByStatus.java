package com.crmproject.model.inheritance;

import java.util.List;

public class SummaryByStatus {
    private int statusID;
    private String status;
    private List<?> tasks;

    public int getStatusID() {
        return statusID;
    }

    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<?> getTasks() {
        return tasks;
    }

    public void setTasks(List<?> tasks) {
        this.tasks = tasks;
    }
}

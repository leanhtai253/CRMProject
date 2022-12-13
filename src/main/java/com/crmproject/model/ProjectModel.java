package com.crmproject.model;

public class ProjectModel {
    private int projectID;
    private String name;
    private String startD;
    private String endD;
    private int leader;

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartD() {
        return startD;
    }

    public void setStartD(String starD) {
        this.startD = starD;
    }

    public String getEndD() {
        return endD;
    }

    public void setEndD(String endD) {
        this.endD = endD;
    }

    public int getLeader() {
        return leader;
    }

    public void setLeader(int leader) {
        this.leader = leader;
    }
}

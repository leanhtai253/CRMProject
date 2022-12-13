package com.crmproject.model;

import com.crmproject.model.inheritance.SummaryByStatus;

import java.util.List;

public class MembersTasksByStatus {
    private UserModel member;
    private List<SummaryByStatus> summaryList;

    public List<SummaryByStatus> getSummaryList() {
        return summaryList;
    }

    public void setSummaryList(List<SummaryByStatus> summaryList) {
        this.summaryList = summaryList;
    }

    public UserModel getMember() {
        return member;
    }

    public void setMember(UserModel member) {
        this.member = member;
    }
}

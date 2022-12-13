package com.crmproject.model;

import java.util.List;

public class UserDetails {
    private List<TasksByStatus> taskLists;
    private int userID;
    private String lastName;
    private String firstName;
    private String username;
    private String email;
    private int roleID;
    private String avatar;
    private String bgImg;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBgImg() {
        return bgImg;
    }

    public void setBgImg(String bgImg) {
        this.bgImg = bgImg;
    }

    public List<TasksByStatus> getTaskLists() {
        return taskLists;
    }

    public void setTaskLists(List<TasksByStatus> taskLists) {
        this.taskLists = taskLists;
    }
}

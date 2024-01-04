package com.bulentoral.pawpal.model;

import com.google.firebase.Timestamp;

public class UserModel {
    private String eMail;
    private String username;
    private Timestamp createdTimestamp;
    private String userId;
    private String name;
    private String surName;
    private String fcmToken;

    public UserModel() {
    }

    public UserModel(String eMail, String username, Timestamp createdTimestamp, String userId, String name, String surName) {
        this.eMail = eMail;
        this.username = username;
        this.createdTimestamp = createdTimestamp;
        this.userId = userId;
        this.name = name;
        this.surName = surName;
    }


    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Timestamp getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(Timestamp createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFcmToken() {
        return fcmToken;
    }

    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }

    public String getName() {
        return name;
    }

    public String getSurName() {
        return surName;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "eMail='" + eMail + '\'' +
                ", username='" + username + '\'' +
                ", createdTimestamp=" + createdTimestamp +
                ", userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", surName='" + surName + '\'' +
                ", fcmToken='" + fcmToken + '\'' +
                '}';
    }
}

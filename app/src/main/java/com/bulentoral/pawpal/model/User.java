package com.bulentoral.pawpal.model;

public class User {

    private String UserID;
    private String name;
    private String surname;
    private String email;

    //private String userType;
    private User(){

    }

    public User(String userID, String name, String surname, String email) {
        UserID = userID;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }



    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }





    @Override
    public String toString() {
        return "User{" +
                "UserID='" + UserID + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

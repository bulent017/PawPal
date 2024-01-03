package com.bulentoral.pawpal.model;

import com.google.firebase.Timestamp;

import java.util.Date;

public class Post {
    private String postID;
    private String userID;
    private String name;
    private String type;
    private String genus;
    private int age;
    private String gender;
    private String description;
    //private String healthStatus;
    private String imageUri;
    private Timestamp datePosted;
    private String address;

    //read
    public Post(String postID, String userID, String name, String type, String genus, int age, String gender, String description, String imageUri, Timestamp datePosted, String address) {
        this.postID = postID;
        this.userID = userID;
        this.name = name;
        this.type = type;
        this.genus = genus;
        this.age = age;
        this.gender = gender;
        this.description = description;
        this.imageUri = imageUri;
        this.datePosted = datePosted;
        this.address = address;
    }
    //write
    public Post(String name, String type, String genus, int age, String gender, String description, String imageUri, String address) {
        this.name = name;
        this.type = type;
        this.genus = genus;
        this.age = age;
        this.gender = gender;
        this.description = description;
        this.imageUri = imageUri;
        this.datePosted = Timestamp.now();
        this.address = address;
    }




    public Post(){

    }

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGenus() {
        return genus;
    }

    public void setGenus(String genus) {
        this.genus = genus;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public Timestamp getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(Timestamp datePosted) {
        this.datePosted = datePosted;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}




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
    private PostType postType;

    //String postID, String userID, PostType postType, String name, String type, String genus, int age, String gender, String description, String photos, Date datePosted, String address
    // Adoption attributes
    private boolean adoptionStatus;
   // private Date dateAvailable;

    // Lost aniaml attributes
    private Date dateLost;
    private String address;
    private double award = 0.0; // Opsiyonel

    public Post(PostType postType,String name , String type, String genus, int age, String gender, String description,  String photos,  String address) {

        this.name = name;
        this.type = type;
        this.genus = genus;
        this.age = age;
        this.gender = gender;
        this.description = description;
        this.imageUri = photos;
        this.datePosted = Timestamp.now();
        // Adoption-specific attributes
        this.adoptionStatus = false;
        this.postType = postType;
        this.address = address;

        // Initialize lost animal attributes as null or default
        this.dateLost = null;

        this.award = 0.0;
    }

    public Post(String postID,String userID,PostType postType, String name, String type, String genus, int age, String gender, String description,  String photos, Date dateLost, String address, double award,Timestamp datePosted) {
        this.userID = userID;
        this.postID =postID;
        this.name = name;
        this.type = type;
        this.genus = genus;
        this.age = age;
        this.gender = gender;
        this.description = description;
        this.imageUri = photos;
        this.datePosted = datePosted;
        this.postType = postType;
        // Initialize adoption attributes as null or default
        this.adoptionStatus = false;
        // Lost animal-specific attributes
        this.dateLost = dateLost;
        this.address = address;
        this.award = award;
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

    public PostType getPostType() {
        return postType;
    }

    public void setPostType(PostType postType) {
        this.postType = postType;
    }

    public boolean isAdoptionStatus() {
        return adoptionStatus;
    }

    public void setAdoptionStatus(boolean adoptionStatus) {
        this.adoptionStatus = adoptionStatus;
    }

    public Date getDateLost() {
        return dateLost;
    }

    public void setDateLost(Date dateLost) {
        this.dateLost = dateLost;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getAward() {
        return award;
    }

    public void setAward(double award) {
        this.award = award;
    }
}




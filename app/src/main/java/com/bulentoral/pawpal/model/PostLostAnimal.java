package com.bulentoral.pawpal.model;

import com.google.firebase.Timestamp;

import java.util.Date;

public class PostLostAnimal extends Post {
    private String dateLost;
    private double award; // Opsiyonel

    private boolean lostStatus;

    //read
    public PostLostAnimal(String postID, String userID, String name, String type, String genus, int age, String gender, String description, String imageUri, Timestamp datePosted, String address, String dateLost, double award,boolean lostStatus) {
        super(postID, userID, name, type, genus, age, gender, description, imageUri, datePosted, address);
        this.dateLost = dateLost;
        this.award = award;
        this.lostStatus = lostStatus;
    }
    //write
    public PostLostAnimal(String name, String type, String genus, int age, String gender, String description, String imageUri, String address, String dateLost, double award) {
        super(name, type, genus, age, gender, description, imageUri, address);
        this.dateLost = dateLost;
        this.award = award;
        this.lostStatus = false;
    }

    public PostLostAnimal() {

    }

    public String getDateLost() {
        return dateLost;
    }

    public void setDateLost(String dateLost) {
        this.dateLost = dateLost;
    }

    public double getAward() {
        return award;
    }

    public void setAward(double award) {
        this.award = award;
    }

    public boolean isLostStatus() {
        return lostStatus;
    }

    public void setLostStatus(boolean lostStatus) {
        this.lostStatus = lostStatus;
    }
}

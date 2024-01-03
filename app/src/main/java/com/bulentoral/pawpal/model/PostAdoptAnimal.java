package com.bulentoral.pawpal.model;

import com.google.firebase.Timestamp;

public class PostAdoptAnimal extends Post{

    private boolean adoptionStatus;

    //write
    public PostAdoptAnimal( String name, String type, String genus, int age, String gender, String description, String imageUri,String address) {
        super( name, type, genus, age, gender, description, imageUri,  address);
        this.adoptionStatus = false;

    }

    //read
    public PostAdoptAnimal(String postID, String userID, String name, String type, String genus, int age, String gender, String description, String imageUri, Timestamp datePosted, String address, boolean adoptionStatus) {
        super(postID, userID, name, type, genus, age, gender, description, imageUri, datePosted, address);
        this.adoptionStatus = adoptionStatus;
    }
    public PostAdoptAnimal() {
    }
    public boolean isAdoptionStatus() {
        return adoptionStatus;
    }

    public void setAdoptionStatus(boolean adoptionStatus) {
        this.adoptionStatus = adoptionStatus;
    }
}

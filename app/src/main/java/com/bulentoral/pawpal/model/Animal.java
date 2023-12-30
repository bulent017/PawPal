package com.bulentoral.pawpal.model;

import java.util.List;

public class Animal {
    private String animalID;
    private String name;
    private String type;
    private String genus;
    private int age;
    private String gender;
    private String description;
    private String healthStatus;
    private List<String> photos;
    public Animal(String animalID, String name, String type, String genus, int age, String gender, String description, String healthStatus, List<String> photos) {
        this.animalID = animalID;
        this.name = name;
        this.type = type;
        this.genus = genus;
        this.age = age;
        this.gender = gender;
        this.description = description;
        this.healthStatus = healthStatus;
        this.photos = photos;
    }

    public Animal(){

    }

    public String getAnimalID() {
        return animalID;
    }

    public void setAnimalID(String animalID) {
        this.animalID = animalID;
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

    public String getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }
}

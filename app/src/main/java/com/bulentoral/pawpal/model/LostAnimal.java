package com.bulentoral.pawpal.model;

import java.util.Date;
import java.util.List;

public class LostAnimal extends Animal{
    private Date dateLost;
    private String address;
    private double award = 0.0; // Opsiyonel


    public LostAnimal(String animalID, String name, String type, String genus, int age, String gender, String description, String healthStatus, List<String> photos, Date dateLost, String address, double award) {
        super(animalID, name, type, genus, age, gender, description, healthStatus, photos);
        this.dateLost = dateLost;
        this.address = address;
        this.award = award;
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

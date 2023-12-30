package com.bulentoral.pawpal.model;

import java.util.Date;
import java.util.List;

public class AdoptionAnimal extends Animal{
    private boolean adoptionStatus;
    private Date dateAvailable;

    public AdoptionAnimal(String animalId, String name, String type, String breed, int age,
                          String gender, String description, String healthStatus,
                          List<String> photos, boolean adoptionStatus, Date dateAvailable) {
        super(animalId, name, type, breed, age, gender, description, healthStatus, photos);
        this.adoptionStatus = adoptionStatus;
        this.dateAvailable = dateAvailable;
    }

    public boolean getAdoptionStatus() {
        return adoptionStatus;
    }

    public void setAdoptionStatus(boolean adoptionStatus) {
        this.adoptionStatus = adoptionStatus;
    }

    public Date getDateAvailable() {
        return dateAvailable;
    }

    public void setDateAvailable(Date dateAvailable) {
        this.dateAvailable = dateAvailable;
    }
}

package com.bulentoral.pawpal.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Characteristics {
    @JsonProperty("prey")
    private String prey;

    @JsonProperty("habitat")
    private String habitat;

    @JsonProperty("color")
    private String color;

    @JsonProperty("lifespan")
    private String lifespan;

    @JsonProperty("ageOfSexualMaturity")
    private String ageOfSexualMaturity;

    @JsonProperty("skinType")
    private String skinType;

    @JsonProperty("numberOfSpecies")
    private String numberOfSpecies;

    @JsonProperty("ageOfWeaning")
    private String ageOfWeaning;

    @JsonProperty("nameOfYoung")
    private String nameOfYoung;

    @JsonProperty("gestationPeriod")
    private String gestationPeriod;

    @JsonProperty("topSpeed")
    private String topSpeed;

    @JsonProperty("diet")
    private String diet;

    @JsonProperty("commonName")
    private String commonName;

    @JsonProperty("group")
    private String group;

    @JsonProperty("mostDistinctiveFeature")
    private String mostDistinctiveFeature;

    @JsonProperty("predators")
    private String predators;

    @JsonProperty("averageLitterSize")
    private String averageLitterSize;

    @JsonProperty("groupBehavior")
    private String groupBehavior;

    @JsonProperty("estimatedPopulationSize")
    private String estimatedPopulationSize;

    @JsonProperty("length")
    private String length;

    @JsonProperty("biggest_threat")
    private String biggest_threat;

    @JsonProperty("weight")
    private String weight;

    @JsonProperty("lifestyle")
    private String lifestyle;

    @JsonProperty("otherNameS")
    private String otherNameS;

    @JsonProperty("location")
    private String location;

    @JsonProperty("slogan")
    private String slogan;

    public String getPrey() {
        return prey;
    }

    public void setPrey(String value) {
        this.prey = value;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String value) {
        this.habitat = value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String value) {
        this.color = value;
    }

    public String getLifespan() {
        return lifespan;
    }

    public void setLifespan(String value) {
        this.lifespan = value;
    }

    public String getAgeOfSexualMaturity() {
        return ageOfSexualMaturity;
    }

    public void setAgeOfSexualMaturity(String value) {
        this.ageOfSexualMaturity = value;
    }

    public String getSkinType() {
        return skinType;
    }

    public void setSkinType(String value) {
        this.skinType = value;
    }

    public String getNumberOfSpecies() {
        return numberOfSpecies;
    }

    public void setNumberOfSpecies(String value) {
        this.numberOfSpecies = value;
    }

    public String getAgeOfWeaning() {
        return ageOfWeaning;
    }

    public void setAgeOfWeaning(String value) {
        this.ageOfWeaning = value;
    }

    public String getNameOfYoung() {
        return nameOfYoung;
    }

    public void setNameOfYoung(String value) {
        this.nameOfYoung = value;
    }

    public String getGestationPeriod() {
        return gestationPeriod;
    }

    public void setGestationPeriod(String value) {
        this.gestationPeriod = value;
    }

    public String getTopSpeed() {
        return topSpeed;
    }

    public void setTopSpeed(String value) {
        this.topSpeed = value;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String value) {
        this.diet = value;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String value) {
        this.commonName = value;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String value) {
        this.group = value;
    }

    public String getMostDistinctiveFeature() {
        return mostDistinctiveFeature;
    }

    public void setMostDistinctiveFeature(String value) {
        this.mostDistinctiveFeature = value;
    }

    public String getPredators() {
        return predators;
    }

    public void setPredators(String value) {
        this.predators = value;
    }

    public String getAverageLitterSize() {
        return averageLitterSize;
    }

    public void setAverageLitterSize(String value) {
        this.averageLitterSize = value;
    }

    public String getGroupBehavior() {
        return groupBehavior;
    }

    public void setGroupBehavior(String value) {
        this.groupBehavior = value;
    }

    public String getEstimatedPopulationSize() {
        return estimatedPopulationSize;
    }

    public void setEstimatedPopulationSize(String value) {
        this.estimatedPopulationSize = value;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String value) {
        this.length = value;
    }

    public String getBiggest_threat() {
        return biggest_threat;
    }

    public void setBiggest_threat(String value) {
        this.biggest_threat = value;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String value) {
        this.weight = value;
    }

    public String getLifestyle() {
        return lifestyle;
    }

    public void setLifestyle(String value) {
        this.lifestyle = value;
    }

    public String getOtherNameS() {
        return otherNameS;
    }

    public void setOtherNameS(String value) {
        this.otherNameS = value;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String value) {
        this.location = value;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String value) {
        this.slogan = value;
    }
}

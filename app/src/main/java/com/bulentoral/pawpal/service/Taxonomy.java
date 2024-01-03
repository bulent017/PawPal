package com.bulentoral.pawpal.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Taxonomy {
    @JsonProperty("phylum")
    private String phylum;
    @JsonProperty("genus")
    private String genus;
    @JsonProperty("scientific_name")
    private String scientific_name;
    @JsonProperty("family")
    private String family;
    @JsonProperty("kingdom")
    private String kingdom;
    @JsonProperty("taxonomyClass")
    private String taxonomyClass;
    @JsonProperty("order")
    private String order;

    public String getPhylum() {
        return phylum;
    }

    public void setPhylum(String value) {
        this.phylum = value;
    }

    public String getGenus() {
        return genus;
    }

    public void setGenus(String value) {
        this.genus = value;
    }

    public String getScientific_name() {
        return scientific_name;
    }

    public void setScientific_name(String value) {
        this.scientific_name = value;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String value) {
        this.family = value;
    }

    public String getKingdom() {
        return kingdom;
    }

    public void setKingdom(String value) {
        this.kingdom = value;
    }

    public String getTaxonomyClass() {
        return taxonomyClass;
    }

    public void setTaxonomyClass(String value) {
        this.taxonomyClass = value;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String value) {
        this.order = value;
    }
}

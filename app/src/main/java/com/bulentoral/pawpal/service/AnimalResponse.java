// AnimalResponse.java

// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation

package com.bulentoral.pawpal.service;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AnimalResponse {

    private Characteristics characteristics;
    @JsonProperty("name")
    private String name;

    @JsonProperty("locations")
    private List<String> locations;

    private Taxonomy taxonomy;

    public Characteristics getCharacteristics() { return characteristics; }
    public void setCharacteristics(Characteristics value) { this.characteristics = value; }

    public String getName() { return name; }
    public void setName(String value) { this.name = value; }

    public List<String> getLocations() { return locations; }
    public void setLocations(List<String> value) { this.locations = value; }

    public Taxonomy getTaxonomy() { return taxonomy; }
    public void setTaxonomy(Taxonomy value) { this.taxonomy = value; }
}

// Characteristics.java

// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation

// Taxonomy.java

// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation


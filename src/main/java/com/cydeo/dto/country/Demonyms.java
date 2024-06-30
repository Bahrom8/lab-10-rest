
package com.cydeo.dto.country;

import com.fasterxml.jackson.annotation.*;
import jakarta.annotation.Generated;

import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "eng",
    "fra"
})
@Generated("jsonschema2pojo")
public class Demonyms {

    @JsonProperty("eng")
    private Eng eng;
    @JsonProperty("fra")
    private Fra__2 fra;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("eng")
    public Eng getEng() {
        return eng;
    }

    @JsonProperty("eng")
    public void setEng(Eng eng) {
        this.eng = eng;
    }

    @JsonProperty("fra")
    public Fra__2 getFra() {
        return fra;
    }

    @JsonProperty("fra")
    public void setFra(Fra__2 fra) {
        this.fra = fra;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
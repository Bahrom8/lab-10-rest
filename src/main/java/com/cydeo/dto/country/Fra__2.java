
package com.cydeo.dto.country;

import com.fasterxml.jackson.annotation.*;
import jakarta.annotation.Generated;

import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "f",
    "m"
})
@Generated("jsonschema2pojo")
public class Fra__2 {

    @JsonProperty("f")
    private String f;
    @JsonProperty("m")
    private String m;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("f")
    public String getF() {
        return f;
    }

    @JsonProperty("f")
    public void setF(String f) {
        this.f = f;
    }

    @JsonProperty("m")
    public String getM() {
        return m;
    }

    @JsonProperty("m")
    public void setM(String m) {
        this.m = m;
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
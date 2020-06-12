
package com.rampal.abhi.model;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "category",
    "beds",
    "bedType"
})
public class TypeEstimated {

    @JsonProperty("category")
    private String category;
    @JsonProperty("beds")
    private Integer beds;
    @JsonProperty("bedType")
    private String bedType;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("category")
    public String getCategory() {
        return category;
    }

    @JsonProperty("category")
    public void setCategory(String category) {
        this.category = category;
    }

    @JsonProperty("beds")
    public Integer getBeds() {
        return beds;
    }

    @JsonProperty("beds")
    public void setBeds(Integer beds) {
        this.beds = beds;
    }

    @JsonProperty("bedType")
    public String getBedType() {
        return bedType;
    }

    @JsonProperty("bedType")
    public void setBedType(String bedType) {
        this.bedType = bedType;
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

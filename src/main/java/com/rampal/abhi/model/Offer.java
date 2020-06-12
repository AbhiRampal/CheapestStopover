
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
    "id",
    "rateCode",
    "room",
    "guests",
    "price"
})
public class Offer {

    @JsonProperty("id")
    private String id;
    @JsonProperty("rateCode")
    private String rateCode;
    @JsonProperty("room")
    private Room room;
    @JsonProperty("guests")
    private Guests guests;
    @JsonProperty("price")
    private Price price;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("rateCode")
    public String getRateCode() {
        return rateCode;
    }

    @JsonProperty("rateCode")
    public void setRateCode(String rateCode) {
        this.rateCode = rateCode;
    }

    @JsonProperty("room")
    public Room getRoom() {
        return room;
    }

    @JsonProperty("room")
    public void setRoom(Room room) {
        this.room = room;
    }

    @JsonProperty("guests")
    public Guests getGuests() {
        return guests;
    }

    @JsonProperty("guests")
    public void setGuests(Guests guests) {
        this.guests = guests;
    }

    @JsonProperty("price")
    public Price getPrice() {
        return price;
    }

    @JsonProperty("price")
    public void setPrice(Price price) {
        this.price = price;
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

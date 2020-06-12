
package com.rampal.abhi.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "type",
    "hotelId",
    "chainCode",
    "dupeId",
    "name",
    "rating",
    "cityCode",
    "latitude",
    "longitude",
    "hotelDistance",
    "address",
    "contact",
    "amenities",
    "media"
})
public class Hotel {

    @JsonProperty("type")
    private String type;
    @JsonProperty("hotelId")
    private String hotelId;
    @JsonProperty("chainCode")
    private String chainCode;
    @JsonProperty("dupeId")
    private String dupeId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("rating")
    private String rating;
    @JsonProperty("cityCode")
    private String cityCode;
    @JsonProperty("latitude")
    private Double latitude;
    @JsonProperty("longitude")
    private Double longitude;
    @JsonProperty("hotelDistance")
    private HotelDistance hotelDistance;
    @JsonProperty("address")
    private Address address;
    @JsonProperty("contact")
    private Contact contact;
    @JsonProperty("amenities")
    private List<String> amenities = null;
    @JsonProperty("media")
    private List<Medium> media = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("hotelId")
    public String getHotelId() {
        return hotelId;
    }

    @JsonProperty("hotelId")
    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    @JsonProperty("chainCode")
    public String getChainCode() {
        return chainCode;
    }

    @JsonProperty("chainCode")
    public void setChainCode(String chainCode) {
        this.chainCode = chainCode;
    }

    @JsonProperty("dupeId")
    public String getDupeId() {
        return dupeId;
    }

    @JsonProperty("dupeId")
    public void setDupeId(String dupeId) {
        this.dupeId = dupeId;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("rating")
    public String getRating() {
        return rating;
    }

    @JsonProperty("rating")
    public void setRating(String rating) {
        this.rating = rating;
    }

    @JsonProperty("cityCode")
    public String getCityCode() {
        return cityCode;
    }

    @JsonProperty("cityCode")
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    @JsonProperty("latitude")
    public Double getLatitude() {
        return latitude;
    }

    @JsonProperty("latitude")
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @JsonProperty("longitude")
    public Double getLongitude() {
        return longitude;
    }

    @JsonProperty("longitude")
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @JsonProperty("hotelDistance")
    public HotelDistance getHotelDistance() {
        return hotelDistance;
    }

    @JsonProperty("hotelDistance")
    public void setHotelDistance(HotelDistance hotelDistance) {
        this.hotelDistance = hotelDistance;
    }

    @JsonProperty("address")
    public Address getAddress() {
        return address;
    }

    @JsonProperty("address")
    public void setAddress(Address address) {
        this.address = address;
    }

    @JsonProperty("contact")
    public Contact getContact() {
        return contact;
    }

    @JsonProperty("contact")
    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @JsonProperty("amenities")
    public List<String> getAmenities() {
        return amenities;
    }

    @JsonProperty("amenities")
    public void setAmenities(List<String> amenities) {
        this.amenities = amenities;
    }

    @JsonProperty("media")
    public List<Medium> getMedia() {
        return media;
    }

    @JsonProperty("media")
    public void setMedia(List<Medium> media) {
        this.media = media;
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

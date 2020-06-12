package com.rampal.abhi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class HotelOfferList {

    @JsonProperty("data")
    private List<HotelOffers> hotelOffers;

    public HotelOfferList() {
        hotelOffers = new ArrayList<>();
    }

    public List<HotelOffers> getHotelOffers() {
        return hotelOffers;
    }

    public void setHotelOffers(List<HotelOffers> hotelOffers) {
        this.hotelOffers = hotelOffers;
    }
}

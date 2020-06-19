package com.rampal.abhi.service;

import com.rampal.abhi.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class BookingsService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    OAuth2RestOperations oAuth2RestTemplate;

    public static final String findHotelURL = "https://test.api.amadeus.com/v2/shopping/hotel-offers?";
    public static final String findHotelRoomURL = "https://test.api.amadeus.com/v2/shopping/hotel-offers/by-hotel?";


    public List<RoomResponse> getHotelOffers(String cityCode, String checkInDate, String checkOutDate){

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(findHotelURL)
                .queryParam("cityCode", cityCode)
                .queryParam("includeClosed", false)
                .queryParam("bestRateOnly", true)
                .queryParam("checkInDate", checkInDate)
                .queryParam("checkOutDate", checkOutDate)
                .queryParam("sort", "PRICE");


        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        OAuth2AccessToken oAuth2AccessToken = oAuth2RestTemplate.getAccessToken();
        headers.setBearerAuth(oAuth2AccessToken.getValue());

        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        ResponseEntity<HotelOfferList> response = restTemplate.exchange(builder.toUriString(),
                HttpMethod.GET, entity, HotelOfferList.class);
        HotelOfferList offers = response.getBody();
        List<RoomResponse> roomResponses = new ArrayList<>();
        for (HotelOffers hotelOffer: offers.getHotelOffers()){
            roomResponses = mergeSortedLists(roomResponses, hotelOffer.getOffers(), hotelOffer);
        }
        return roomResponses;
    }


    public static List<RoomResponse> mergeSortedLists(List<RoomResponse> roomResponses, List<Offer> offerList, HotelOffers hotelOffer) {
        List<RoomResponse> output = new ArrayList<>();
        int i, j;
        for (i = 0, j = 0; i < roomResponses.size() && j < offerList.size();) {
            BigDecimal roomPrice = new BigDecimal(roomResponses.get(i).getRoomRate());
            //sometimes total price from json response is null. In that scenario using the base price.
            BigDecimal offerPrice = new BigDecimal(offerList.get(j).getPrice().getTotal() != null ?
                    offerList.get(j).getPrice().getTotal() : offerList.get(j).getPrice().getBase());
            if (roomPrice.compareTo(offerPrice) < 0) {
                output.add(roomResponses.get(i));
                i++;
            } else {
                output.add(createRoom(hotelOffer, offerPrice.toString()));
                j++;
            }
        }

        while(i < roomResponses.size() && output.size() < 3) {
            output.add(roomResponses.get(i++));
        }
        while(j < offerList.size() && output.size() < 3) {
            output.add(createRoom(hotelOffer, offerList.get(j).getPrice().getTotal() != null ?
                    offerList.get(j).getPrice().getTotal() : offerList.get(j).getPrice().getBase()));
            j++;
        }
        return output;
    }

    private static RoomResponse createRoom(HotelOffers hotelOffer, String price){
        RoomResponse room = new RoomResponse();
        room.setHotelName(hotelOffer.getHotel().getName());
        room.setAddress(hotelOffer.getHotel().getAddress().getLines().get(0)+" "+ hotelOffer.getHotel().getAddress().getCityName());
        room.setPhoneNumber(hotelOffer.getHotel().getContact().getPhone());
        room.setRoomRate(price);
        return room;
    }
}

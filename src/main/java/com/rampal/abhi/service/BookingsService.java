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
            merge(roomResponses, hotelOffer.getOffers(), hotelOffer);
        }
        return roomResponses;
    }

    public static void merge(List<RoomResponse> roomResponses, List<Offer> offerList, HotelOffers hotelOffer) {
        for (int index1 = 0, index2 = 0; index2 < offerList.size(); index1++) {
            BigDecimal roomPrice = new BigDecimal(roomResponses.get(index1).getRoomRate());
            //sometimes total price from json response is null. In that scenario using the base price.
            BigDecimal offerPrice = new BigDecimal(offerList.get(index2).getPrice().getTotal() != null ?
                    offerList.get(index2).getPrice().getTotal() : offerList.get(index2).getPrice().getBase());
            if (index1 == roomResponses.size() || roomPrice.compareTo(offerPrice) > 0) {
                RoomResponse room = new RoomResponse();
                room.setHotelName(hotelOffer.getHotel().getName());
                room.setAddress(hotelOffer.getHotel().getAddress().getLines().get(0));
                room.setPhoneNumber(hotelOffer.getHotel().getContact().getPhone());
                room.setRoomRate( offerList.get(index2).getPrice().getTotal() != null? offerList.get(index2).getPrice().getTotal(): offerList.get(index2).getPrice().getBase());
                room.setRoomRateCurreny(offerList.get(index2++).getPrice().getCurrency());
                roomResponses.add(index1, room);
            }
        }

        if (roomResponses.size() > 3) {
            roomResponses.subList(3, roomResponses.size()).clear();
        }
    }


    public List<Offer> getHotelRooms(String hotelID){

        LocalDateTime currentTime = LocalDateTime.now();

        LocalDate checkIn = currentTime.toLocalDate();
        currentTime = currentTime.plusDays(1);
        LocalDate checkOut = currentTime.toLocalDate();

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(findHotelRoomURL)
                .queryParam("hotelId", hotelID)
                .queryParam("checkInDate", checkIn)
                .queryParam("checkOutDate", checkOut);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        OAuth2AccessToken oAuth2AccessToken = oAuth2RestTemplate.getAccessToken();
        headers.setBearerAuth(oAuth2AccessToken.getValue());

        HttpEntity<String> entity = new HttpEntity<>("body", headers);
        System.out.println("URI: "+builder.toUriString());
        ResponseEntity<Example> response = restTemplate.exchange(builder.toUriString(),
                HttpMethod.GET, entity, Example.class);
        List<Offer> offers = response.getBody().getData().getOffers();

        return offers;
    }
}

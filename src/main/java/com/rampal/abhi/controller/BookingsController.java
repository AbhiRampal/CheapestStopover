package com.rampal.abhi.controller;

import com.rampal.abhi.model.*;
import com.rampal.abhi.service.BookingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BookingsController {

    @Autowired
    BookingsService bookingsService;

    @GetMapping("/getCheapestRooms")
    public List<RoomResponse> getCheapestRooms(@RequestParam(value = "cityCode") String cityCode,
                                               @RequestParam(value = "checkInDate", required=false) String checkInDate,
                                               @RequestParam(value = "checkOutDate", required = false) String checkOutDate) throws Exception {
        // Sending get request
        List<HotelOffers> hotelOffersList = bookingsService.getHotelOffers(cityCode, checkInDate, checkOutDate);

        int i=0;
        List<RoomResponse> roomResponses = new ArrayList<>();
            for (HotelOffers hotelOffer: hotelOffersList){
                for (Offer offer: hotelOffer.getOffers()){
                    if (i<3) {
                        RoomResponse room = new RoomResponse();
                        room.setHotelName(hotelOffer.getHotel().getName());
                        room.setAddress(hotelOffer.getHotel().getAddress().getLines().get(0));
                        room.setPhoneNumber(hotelOffer.getHotel().getContact().getPhone());
                        room.setRoomRate(offer.getPrice().getCurrency() + " " + offer.getPrice().getTotal());
                        roomResponses.add(room);
                        i++;
                    } else {
                        break;
                    }
            }
        }

        return roomResponses;
    }




}

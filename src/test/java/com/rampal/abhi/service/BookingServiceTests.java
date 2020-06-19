package com.rampal.abhi.service;

import com.rampal.abhi.controller.BookingsController;
import com.rampal.abhi.model.RoomResponse;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class BookingServiceTests {

    @Autowired
    private BookingsService bookingsService;

    @Test
    void testGetHotelOffersParis(){
        List<RoomResponse> hotelOffersList = bookingsService.getHotelOffers("NYC",
                "2020-06-16", "2020-06-17");
        assertNotNull(hotelOffersList);
        assertFalse(hotelOffersList.isEmpty());
        for (RoomResponse room: hotelOffersList){
            assertEquals("USD", room.getRoomRateCurreny());
        }
    }

    @Test
    void testGetHotelOffersLondon(){
        List<RoomResponse> hotelOffersList = bookingsService.getHotelOffers("LON",
                "2020-06-16", "2020-06-17");
        assertNotNull(hotelOffersList);
        for (RoomResponse room: hotelOffersList){
            assertEquals("GBP", room.getRoomRateCurreny());
        }
    }
}
